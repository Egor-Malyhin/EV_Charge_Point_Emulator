package org.mycorp.messagebuilders;

import org.mycorp.models.SessionId;
import org.mycorp.v2g.*;

public abstract class MessageBuilderImpl implements MessageBuilder {
    protected V2GBodyAbstractType v2GBodyAbstractType;
    protected V2GHeader v2GHeader;
    protected V2GBody v2GBody;

    @Override
    public MessageBuilder createBody() {
        this.v2GBody = new V2GBody(v2GBodyAbstractType);
        return this;
    }

    @Override
    public MessageBuilder createHeader() {
        this.v2GHeader = new V2GHeader(SessionId.getInstance().getSessionIdValue());
        return this;
    }

    @Override
    public V2GMessage create() {
        return new V2GMessage(this.v2GHeader, this.v2GBody);
    }
}
