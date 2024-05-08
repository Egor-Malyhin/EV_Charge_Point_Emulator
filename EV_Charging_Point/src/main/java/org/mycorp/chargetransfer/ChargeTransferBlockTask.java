package org.mycorp.chargetransfer;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.mycorp.models.Charge;
import org.mycorp.models.MeterValues;
import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.StationVariables;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@Component
public class ChargeTransferBlockTask implements Runnable {
    private final ReadWriteLock meterValuesLock;
    private final ReadWriteLock stopChargingLock;
    private final MeterValues meterValues;
    @Setter
    private Charge preparedCharge;
    private boolean isRunning;
    private boolean isEmergencyStopping;
    private int meterStartValue;

    public ChargeTransferBlockTask() {
        this.preparedCharge = new Charge("kWh", 0);
        this.meterValues = new MeterValues(Instant.now(), new Charge("kWh", 0));
        this.meterValuesLock = new ReentrantReadWriteLock();
        this.stopChargingLock = new ReentrantReadWriteLock();
    }

    @Override
    public void run() {
        start();
        meterStartValue = StationVariables.getInstance().getMeterCurrent();
        Duration durationOfCharging = setDurationTime(preparedCharge.getValue());
        Instant startTime = Instant.now();
        log.info("Charging start, start meter values: " + StationVariables.getInstance().getMeterCurrent() + "kWh");
        while ((Duration.between(startTime, Instant.now()).compareTo(durationOfCharging) < 0 && isRunning())) {
            try {
                Thread.sleep(100);
                Duration durationFromStart = Duration.between(startTime, Instant.now());
                updateSampledValue(durationFromStart.toMillis());
                log.info("Charging proceed: " + meterValues.getCharge().getValue() + meterValues.getCharge().getUnit() + ", common meter values: " + StationVariables.getInstance().getMeterCurrent() + "kWh, Seconds from start: " + durationFromStart.toMillis() * 0.001f);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (isRunning())
            stopNormally();

        String stoppingEmergency = isEmergencyStopping ? "emergency" : "normally";
        log.info("Charging stop " + stoppingEmergency + ", total charging time: " + Duration.between(startTime, Instant.now()).toMillis() * 0.001f + " seconds, power delivered: " + meterValues.getCharge().getValue() + meterValues.getCharge().getUnit() + ", finishing meterValues: " + StationVariables.getInstance().getMeterCurrent() + "kWh.");
    }

    private Duration setDurationTime(float charge) {
        float floatDuration = charge / StationCharacteristics.ratedPower;
        long longDuration = (long) (floatDuration * 1_000_000_000);
        return Duration.ofNanos(longDuration);
    }

    private void updateSampledValue(long durationFromStart) {
        meterValuesLock.writeLock().lock();
        try {
            Charge powerValue = meterValues.getCharge();
            powerValue.setValue(StationCharacteristics.ratedPower * 0.001f * durationFromStart);
            meterValues.setTimestamp(Instant.now());
            updateCommonMeterValues(powerValue.getValue());
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

    public void stopNormally() {
        stopChargingLock.writeLock().lock();
        try {
            isRunning = false;
        } finally {
            stopChargingLock.writeLock().unlock();
        }
    }

    public void stopEmergency() {
        stopChargingLock.writeLock().lock();
        try {
            isRunning = false;
            isEmergencyStopping = true;
        } finally {
            stopChargingLock.writeLock().unlock();
        }
    }

    public boolean isEmergencyStopping() {
        stopChargingLock.readLock().lock();
        try {
            return isEmergencyStopping;
        } finally {
            stopChargingLock.readLock().unlock();
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

    private void start() {
        stopChargingLock.writeLock().lock();
        try {
            isRunning = true;
            isEmergencyStopping = false;
        } finally {
            stopChargingLock.writeLock().unlock();
        }
    }

    private void updateCommonMeterValues(float currentPowerValue) {
        StationVariables.getInstance().setMeterCurrent((int) currentPowerValue + meterStartValue);
    }
}
