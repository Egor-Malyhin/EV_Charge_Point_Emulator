package org.mycorp.ev_communication.message_builders;

import org.mycorp.models.v2g_messages.*;
import org.mycorp.models.v2g_messages.types.ResponseCode;

import static org.mycorp.models.v2g_messages.types.ResponseCode.OK;

public abstract class MessageBuilderImpl implements MessageBuilder{
    protected V2GBodyAbstractType v2GBodyAbstractType;
    protected V2GHeader v2GHeader;
    protected V2GBody v2GBody;
    protected ResponseCode responseCode;

    public MessageBuilderImpl(ResponseCode responseCode){
        this.responseCode = responseCode;
    }

    @Override
    public MessageBuilder createBody(){
        this.v2GBody = new V2GBody(v2GBodyAbstractType);
        return this;
    }

    @Override
    public MessageBuilder createHeader() {
        this.v2GHeader=new V2GHeader(V2GSessionIdCounter.getInstance().getSessionId());
        return this;
    }

    @Override
    public V2GMessage create(){
        return new V2GMessage(this.v2GHeader, this.v2GBody);
    }
}
