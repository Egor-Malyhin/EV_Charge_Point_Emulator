package org.mycorp.commcsms.websocketactionlistener;

import org.mycorp.models.events.commcsms.ClientConnected;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class WebSocketActionListener {
    private final ApplicationEventPublisher applicationEventPublisher;

    public WebSocketActionListener(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void clientConnected() {
        applicationEventPublisher.publishEvent(new ClientConnected());
    }
}