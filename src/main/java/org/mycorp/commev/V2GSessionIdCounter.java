package org.mycorp.commev;

import lombok.Getter;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;

@Getter
public class V2GSessionIdCounter {
    private static V2GSessionIdCounter instance;
    private byte[] sessionId;

    private V2GSessionIdCounter() {
        sessionId = convertHexBinary(0);
    }

    public static V2GSessionIdCounter getInstance() {
        if (instance == null)
            instance = new V2GSessionIdCounter();
        return instance;
    }

    public void incrementCounter() {
        int intId = convertToInt(sessionId);
        intId++;
        sessionId = convertHexBinary(intId);
    }

    private byte[] convertHexBinary(int value) {
        String hexStringValue = Integer.toHexString(value);
        if (hexStringValue.length() % 2 != 0)
            hexStringValue = "0" + hexStringValue;
        return DatatypeConverter.parseHexBinary(hexStringValue);
    }

    private int convertToInt(byte[] byteArray) {
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        if (byteArray.length >= 4)
            return buffer.getInt();
        else if (byteArray.length > 1)
            return buffer.getShort();
        else
            return buffer.get();
    }
}
