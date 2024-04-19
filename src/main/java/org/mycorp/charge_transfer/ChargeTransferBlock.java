package org.mycorp.charge_transfer;

import org.mycorp.mediators.senders.Sender;
import org.mycorp.models.Charge;
import org.mycorp.models.MeterValues;
import org.mycorp.models.SampledValue;
import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.station_messages.charge_transfer_messages.StopChargingByStationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class ChargeTransferBlock implements Runnable {

    //TODO сохранять значения после выключения програмы
    private final Sender senderChargeTransfer;
    private Charge charge;
    private final MeterValues meterValues;
    private boolean isRunning;
    private final ReadWriteLock lock;

    @Autowired
    public ChargeTransferBlock(@Qualifier("sender") Sender senderChargeTransfer) {
        this.charge = new Charge("Wh", 0);
        List<SampledValue> valueList = new ArrayList<>();
        valueList.add(new SampledValue(0, "Energy", "Outlet", "Wh"));
        this.meterValues = new MeterValues(Instant.now(), valueList);
        this.isRunning = true;
        this.senderChargeTransfer = senderChargeTransfer;
        this.lock = new ReentrantReadWriteLock();
    }

    @Override
    public void run() {
        isRunning = true;
        Duration durationOfCharging = setDurationTime(charge.value());
        Instant startTime = Instant.now();
        while (isRunning) {
            while (Duration.between(startTime, Instant.now()).compareTo(durationOfCharging) < 0) {
                try {
                    Thread.sleep(10);
                    updateSampledValue(Duration.between(startTime, Instant.now()).toMillis());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            stop();
            senderChargeTransfer.sendMessage(new StopChargingByStationMessage());
        }
    }

    private Duration setDurationTime(float charge) {
        float floatDuration = charge / StationCharacteristics.ratedPower;
        long longDuration = (long) floatDuration;
        return Duration.ofSeconds(longDuration);
    }

    private void updateSampledValue(float durationFromStart) {
        lock.writeLock().lock();
        try {
            SampledValue powerValue = meterValues.getSampledValue().get(0);
            powerValue.setValue(StationCharacteristics.ratedPower * 0.001f * durationFromStart);
            meterValues.setTimestamp(Instant.now());
        } finally {
            lock.writeLock().unlock();
        }
    }

    public MeterValues getMeterValues() {
        lock.readLock().lock();
        try {
            return meterValues;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public void stop() {
        isRunning = false;
    }
}
