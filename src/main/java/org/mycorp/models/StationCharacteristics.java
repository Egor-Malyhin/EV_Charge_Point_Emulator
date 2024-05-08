package org.mycorp.models;

//A static class with station characteristics.
//It contains constants such as, for example, manufacturer, maximum outgoing current, maximum output voltage, etc.
public class StationCharacteristics {
    public static final String evseId = "1";
    public static final String chargePointVendor = "MyCorp";
    public static final String chargePointModel = "MyEVSEVirtualModel";
    public static final int connectorId = 1;
    public static final int meterId = 1;
    public static final int maxCurrent = 38;
    public static final int ratedCurrent = 32;
    public static final int ratedVoltage = 230;
    public static final int maxVoltage = 276;
    public static final int minVoltage = 184;
    public static final int ratedPower = 7;
}
