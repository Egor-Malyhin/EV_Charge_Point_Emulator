package org.mycorp.models;

import java.time.ZoneId;

public class StationVariables {
    private static StationVariables instance;
    private int transactionId;
    private int meterCurrent;
    private ZoneId zoneId;
    private String idTag;

    private StationVariables() {
    }

    public static StationVariables getInstance() {
        if (instance == null)
            instance = new StationVariables();
        return instance;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getMeterCurrent() {
        return meterCurrent;
    }

    public void setMeterCurrent(int meterCurrent) {
        this.meterCurrent = meterCurrent;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public String getIdTag() {
        return idTag;
    }

    public void setIdTag(String idTag) {
        this.idTag = idTag;
    }
}
