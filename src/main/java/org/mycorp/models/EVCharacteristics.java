package org.mycorp.models;

import org.mycorp.v2g.types.PhysicalValue;

public class EVCharacteristics {
    public static byte[] evccId = "MyEVCCID1234".getBytes();
    public static PhysicalValue evMaxVoltage = new PhysicalValue(1, "V", 10000);
    public static PhysicalValue evMaxCurrent = new PhysicalValue(1, "kWh", 40000);
    public static PhysicalValue evMinCurrent = new PhysicalValue(1, "kWh", 1);
}
