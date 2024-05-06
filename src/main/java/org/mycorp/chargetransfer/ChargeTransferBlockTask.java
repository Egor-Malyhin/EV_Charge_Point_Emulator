package org.mycorp.chargetransfer;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Component
public class ChargeTransferBlockTask implements Runnable {
    private final ReadWriteLock meterValuesLock;
    private final ReadWriteLock stopChargingLock;
    private final MeterValues meterValues;
    @Setter
    private Charge charge;
    private boolean isRunning;
    private String shutdownInitiator;

    public ChargeTransferBlockTask() {
        this.charge = new Charge("Wh", 0);
        List<SampledValue> valueList = new ArrayList<>();
        valueList.add(new SampledValue(0, "Energy", "Outlet", "kWh"));
        this.meterValues = new MeterValues(Instant.now(), valueList);
        this.meterValuesLock = new ReentrantReadWriteLock();
        this.stopChargingLock = new ReentrantReadWriteLock();
    }

    @Override
    public void run() {
        start();
        Duration durationOfCharging = setDurationTime(charge.value());
        Instant startTime = Instant.now();
        log.info("Charging start");
        while(isRunning()) {
            while (Duration.between(startTime, Instant.now()).compareTo(durationOfCharging) < 0) {
                try {
                    Thread.sleep(100);
                    Duration durationFromStart = Duration.between(startTime, Instant.now());
                    updateSampledValue(durationFromStart.toMillis());
                    log.info("Charging proceed: " + meterValues.getSampledValue().get(0).getValue() + meterValues.getSampledValue().get(0).getUnit() + ", Seconds from start: " + durationFromStart.toSeconds());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            stop("None");
        }
        log.info("Charging stop, shutdown initiator: " + shutdownInitiator + ", total charging time: " + Duration.between(startTime, Instant.now()).toSeconds() + " seconds.");
    }

    private Duration setDurationTime(float charge) {
        float floatDuration = charge / StationCharacteristics.ratedPower;
        long longDuration = (long) (floatDuration * 1_000_000_000);
        return Duration.ofNanos(longDuration);
    }

    private void updateSampledValue(long durationFromStart) {
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

    public void stop(String shutdownInitiator) {
        stopChargingLock.writeLock().lock();
        try {
            isRunning = false;
            this.shutdownInitiator = shutdownInitiator;
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

    private void start() {
        stopChargingLock.writeLock().lock();
        try {
            isRunning = true;
        } finally {
            stopChargingLock.writeLock().unlock();
        }
    }
}
