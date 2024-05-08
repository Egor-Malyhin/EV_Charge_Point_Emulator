package org.mycorp.logging;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.mycorp.models.messages.v2g.V2GMessage;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EVMessagesLogger extends IoFilterAdapter {
    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) {
        V2GMessage v2GMessage = (V2GMessage) message;
        log.info("Received Message from EV: " + v2GMessage.getBody().getV2GBodyAbstractType().getClass().getSimpleName());
        nextFilter.messageReceived(session, message);
    }

    @Override
    public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) {
        V2GMessage v2GMessage = (V2GMessage) writeRequest.getOriginalMessage();
        log.info("Sent Message to EV: " + v2GMessage.getBody().getV2GBodyAbstractType().getClass().getSimpleName());
        nextFilter.messageSent(session, writeRequest);
    }
}
