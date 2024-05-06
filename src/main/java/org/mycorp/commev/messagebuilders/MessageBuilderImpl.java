package org.mycorp.commev.messagebuilders;

import org.mycorp.commev.V2GSessionIdCounter;
import org.mycorp.models.messages.v2g.V2GBody;
import org.mycorp.models.messages.v2g.V2GBodyAbstractType;
import org.mycorp.models.messages.v2g.V2GHeader;
import org.mycorp.models.messages.v2g.V2GMessage;
import org.mycorp.models.messages.v2g.types.enums.ResponseCode;

public abstract class MessageBuilderImpl implements MessageBuilder {
    protected V2GBodyAbstractType v2GBodyAbstractType;
    protected V2GHeader v2GHeader;
    protected V2GBody v2GBody;
    protected ResponseCode responseCode;

    public MessageBuilderImpl(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public MessageBuilder createBody() {
        this.v2GBody = new V2GBody(v2GBodyAbstractType);
        return this;
    }

    @Override
    public MessageBuilder createHeader() {
        this.v2GHeader = new V2GHeader(V2GSessionIdCounter.getInstance().getSessionId());
        return this;
    }

    @Override
    public V2GMessage create() {
        return new V2GMessage(this.v2GHeader, this.v2GBody);
    }
}
