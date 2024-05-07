package org.mycorp.models;

import eu.chargetime.ocpp.model.core.ChargePointStatus;

import java.time.ZoneId;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//This class stores variable station parameters in RAM, which are set during charging.
//This is a thread-safe singleton, use atomics for extension.
public class StationVariables {
    private final AtomicInteger transactionId;
    private final AtomicInteger meterCurrent;
    private final AtomicReference<ZoneId> zoneId;
    private final AtomicReference<String> idTag;
    private ChargePointStatus chargePointStatusCurrent;
    private ChargePointStatus chargePointStatusPast;
    private final ReadWriteLock chargePointStatusLock;

    private StationVariables() {
        transactionId = new AtomicInteger();
        meterCurrent = new AtomicInteger();
        zoneId = new AtomicReference<>();
        idTag = new AtomicReference<>();
        chargePointStatusLock = new ReentrantReadWriteLock();
    }

    public static StationVariables getInstance() {
        return Holder.instance;
    }

    public int getTransactionId() {
        return transactionId.get();
    }

    public void setTransactionId(int transactionId) {
        this.transactionId.set(transactionId);
    }

    public int getMeterCurrent() {
        return meterCurrent.get();
    }

    public void setMeterCurrent(int meterCurrent) {
        this.meterCurrent.set(meterCurrent);
    }

    public ZoneId getZoneId() {
        return zoneId.get();
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId.set(zoneId);
    }

    public String getIdTag() {
        return idTag.get();
    }

    public void setIdTag(String idTag) {
        this.idTag.set(idTag);
    }

    public void setChargePointStatus(ChargePointStatus chargePointStatusCurrent, ChargePointStatus chargePointStatusPast) {
        chargePointStatusLock.writeLock().lock();
        try {
            this.chargePointStatusCurrent = chargePointStatusCurrent;
            this.chargePointStatusPast = chargePointStatusPast;
        } finally {
            chargePointStatusLock.writeLock().unlock();
        }
    }

    public ChargePointStatus getChargePointStatusCurrent() {
        chargePointStatusLock.readLock().lock();
        try {
            return chargePointStatusCurrent;
        } finally {
            chargePointStatusLock.readLock().unlock();
        }
    }

    public ChargePointStatus getChargePointStatusPast() {
        chargePointStatusLock.readLock().lock();
        try {
            return chargePointStatusPast;
        } finally {
            chargePointStatusLock.readLock().unlock();
        }
    }

    private static class Holder {
        static StationVariables instance = new StationVariables();
    }
}
