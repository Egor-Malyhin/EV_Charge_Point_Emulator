package org.mycorp.models;

import java.time.ZoneId;

public class StationVariables {
    private int transactionId;
    private int meterCurrent;
    private ZoneId zoneId;
    private String idTag;
    private static StationVariables instance;

    private StationVariables(){
    }

    public static StationVariables getInstance(){
        if(instance == null)
            instance = new StationVariables();
        return instance;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getMeterCurrent() {
        return meterCurrent;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public String getIdTag() {
        return idTag;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setMeterCurrent(int meterCurrent) {
        this.meterCurrent = meterCurrent;
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public void setIdTag(String idTag) {
        this.idTag = idTag;
    }
}
