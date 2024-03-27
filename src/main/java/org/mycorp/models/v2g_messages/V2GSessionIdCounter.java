package org.mycorp.models.v2g_messages;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;

public class V2GSessionIdCounter {
    private byte[] sessionId;
    private static V2GSessionIdCounter instance;

    private V2GSessionIdCounter(){
        sessionId =new byte[]{0x00};
    }

    public static V2GSessionIdCounter getInstance(){
        if(instance==null)
            instance = new V2GSessionIdCounter();
        return instance;
    }

    public byte[] getSessionId() {
        return sessionId;
    }

    public void incrementCounter(){
        int counterInt = ByteBuffer.wrap(sessionId).getInt();
        counterInt++;
        sessionId = DatatypeConverter.parseHexBinary(Integer.toHexString(counterInt));
    }
}
