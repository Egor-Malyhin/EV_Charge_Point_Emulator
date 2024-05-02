package org.mycorp.chargetransfer;

import org.mycorp.models.Charge;
import org.mycorp.models.MeterValues;
import org.mycorp.models.SampledValue;
import org.mycorp.models.StationCharacteristics;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class ChargeTransferBlockTask implements Runnable {
    //TODO сохранять значения после выключения програмы
    private final ReadWriteLock meterValuesLock;
    private final ReadWriteLock stopChargingLock;
    private final MeterValues meterValues;
    private Charge charge;
    private boolean isRunning;
    private String shutdownInitiator;

    public ChargeTransferBlockTask() {
        this.charge = new Charge("Wh", 0);
        List<SampledValue> valueList = new ArrayList<>();
        valueList.add(new SampledValue(0, "Energy", "Outlet", "Wh"));
        this.meterValues = new MeterValues(Instant.now(), valueList);
        this.meterValuesLock = new ReentrantReadWriteLock();
        this.stopChargingLock = new ReentrantReadWriteLock();
    }

    @Override
    public void run() {
        isRunning = true;
        setShutdownInitiator("None");
        Duration durationOfCharging = setDurationTime(charge.value());
        Instant startTime = Instant.now();

        while (Duration.between(startTime, Instant.now()).compareTo(durationOfCharging) < 0 && isRunning()) {
            try {
                Thread.sleep(100);
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
        meterValuesLock.writeLock().lock();
        try {
            SampledValue powerValue = meterValues.getSampledValue().get(0);
            powerValue.setValue(StationCharacteristics.ratedPower * 0.001f * durationFromStart);
            meterValues.setTimestamp(Instant.now());
        } finally {
            meterValuesLock.writeLock().unlock();
        }
    }

    public MeterValues getMeterValues() {
        meterValuesLock.readLock().lock();
        try {
            return meterValues.clone();
        } finally {
            meterValuesLock.readLock().unlock();
        }
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public void stop(String shutdownInitiator) {
        stopChargingLock.writeLock().lock();
        try {
            setShutdownInitiator(shutdownInitiator);
            isRunning = false;
        } finally {
            stopChargingLock.writeLock().unlock();
        }
    }

    public boolean isRunning() {
        stopChargingLock.readLock().lock();
        try {
            return isRunning;
        } finally {
            stopChargingLock.readLock().unlock();
        }
    }

    public String getShutdownInitiator() {
        stopChargingLock.readLock().lock();
        try {
            return shutdownInitiator;
        } finally {
            stopChargingLock.readLock().unlock();
        }
    }

    private void setShutdownInitiator(String shutdownInitiator) {
        this.shutdownInitiator = shutdownInitiator;
    }
}
