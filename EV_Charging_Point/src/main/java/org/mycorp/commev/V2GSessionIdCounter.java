package org.mycorp.commev;

import lombok.Getter;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class V2GSessionIdCounter {
    private byte[] sessionId;
    private final ReadWriteLock sessionIdCounterLock;

    private V2GSessionIdCounter() {
        sessionId = convertHexBinary(0);
        sessionIdCounterLock = new ReentrantReadWriteLock();
    }

    public static V2GSessionIdCounter getInstance() {
        return Holder.instance;
    }

    public void incrementCounter() {
        try {
            sessionIdCounterLock.writeLock().lock();
            int intId = convertToInt(sessionId);
            intId++;
            sessionId = convertHexBinary(intId);
        } finally {
            sessionIdCounterLock.writeLock().unlock();
        }
    }

    public byte[] getSessionId() {
        sessionIdCounterLock.readLock().lock();
        try {
            return sessionId;
        } finally {
            sessionIdCounterLock.readLock().unlock();
        }
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
    private static class Holder {
        static V2GSessionIdCounter instance = new V2GSessionIdCounter();
    }
}
