package org.mycorp.messagebuilders.res;

import org.mycorp.messagebuilders.MessageBuilderImpl;
import org.mycorp.messages.types.enums.ResponseCode;

public abstract class MessageBuilderRes extends MessageBuilderImpl {
    protected ResponseCode responseCode;

    public MessageBuilderRes(byte[] sessionId, ResponseCode responseCode) {
        super(sessionId);
        this.responseCode = responseCode;
    }
}
