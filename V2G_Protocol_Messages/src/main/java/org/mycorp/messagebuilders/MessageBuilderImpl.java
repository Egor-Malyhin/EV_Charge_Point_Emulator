package org.mycorp.messagebuilders;

import org.mycorp.messages.V2GBody;
import org.mycorp.messages.V2GBodyAbstractType;
import org.mycorp.messages.V2GHeader;
import org.mycorp.messages.V2GMessage;

public abstract class MessageBuilderImpl implements MessageBuilder {
    private final byte[] sessionId;
    protected V2GBodyAbstractType v2GBodyAbstractType;
    protected V2GHeader v2GHeader;
    protected V2GBody v2GBody;

    public MessageBuilderImpl(byte[] sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public MessageBuilder createBody() {
        this.v2GBody = new V2GBody(v2GBodyAbstractType);
        return this;
    }

    @Override
    public MessageBuilder createHeader() {
        this.v2GHeader = new V2GHeader(sessionId);
        return this;
    }

    @Override
    public V2GMessage create() {
        return new V2GMessage(this.v2GHeader, this.v2GBody);
    }
}
