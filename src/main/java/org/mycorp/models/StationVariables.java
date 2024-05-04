package org.mycorp.models;

import java.time.ZoneId;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

//This class stores variable station parameters in RAM, which are set during charging.
//This is a thread-safe singleton, use atomics for extension.
public class StationVariables {
    private final AtomicInteger transactionId;
    private final AtomicInteger meterCurrent;
    private final AtomicReference<ZoneId> zoneId;
    private final AtomicReference<String> idTag;

    private StationVariables() {
        transactionId = new AtomicInteger();
        meterCurrent = new AtomicInteger();
        zoneId = new AtomicReference<>();
        idTag = new AtomicReference<>();
    }

    public static StationVariables getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        static StationVariables instance = new StationVariables();
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
}
