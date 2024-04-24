package org.mycorp.chargetransfer;

import org.mycorp.models.Charge;
import org.mycorp.models.MeterValues;
import org.mycorp.models.SampledValue;
import org.mycorp.models.StationCharacteristics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class ChargeTransferBlockTask implements Runnable{

    //TODO сохранять значения после выключения програмы
    private Charge charge;
    private boolean isRunning;
    private String shutdownInitiator;
    private final ReadWriteLock lock;
    private final MeterValues meterValues;

    public ChargeTransferBlockTask() {
        this.charge = new Charge("Wh", 0);
        List<SampledValue> valueList = new ArrayList<>();
        valueList.add(new SampledValue(0, "Energy", "Outlet", "Wh"));
        this.meterValues = new MeterValues(Instant.now(), valueList);
        this.isRunning = true;
        this.lock = new ReentrantReadWriteLock();
    }

    @Override
    public void run() {
        isRunning = true;
        setShutdownInitiator("None");
        Duration durationOfCharging = setDurationTime(charge.value());
        Instant startTime = Instant.now();
        while (Duration.between(startTime, Instant.now()).compareTo(durationOfCharging) < 0 && isRunning) {
            try {
                Thread.sleep(1000);
                updateSampledValue(Duration.between(startTime, Instant.now()).toMillis());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Duration setDurationTime(float charge) {
        float floatDuration = charge / StationCharacteristics.ratedPower;
        long longDuration = (long) (floatDuration * 1_000_000_000);
        return Duration.ofNanos(longDuration);
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
            return meterValues.clone();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public void stop(String shutdownInitiator) {
        isRunning = false;
        setShutdownInitiator(shutdownInitiator);
    }

    public boolean isRunning(){
        return isRunning;
    }

    public String getShutdownInitiator() {
        return shutdownInitiator;
    }

    private void setShutdownInitiator(String shutdownInitiator) {
        this.shutdownInitiator = shutdownInitiator;
    }
}
