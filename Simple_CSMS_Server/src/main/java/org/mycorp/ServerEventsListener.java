package org.mycorp;

import eu.chargetime.ocpp.AuthenticationException;
import eu.chargetime.ocpp.ServerEvents;
import eu.chargetime.ocpp.model.SessionInformation;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Getter
@Slf4j
public class ServerEventsListener implements ServerEvents {
    private UUID sessionIndex;

    //I left this method empty because session authentication implementation is not mandatory for
    //a simple CSMS server.
    @Override
    public void authenticateSession(SessionInformation information, String username, byte[] password) throws AuthenticationException {
    }

    @Override
    public void newSession(UUID sessionIndex, SessionInformation information) {
        log.info("New Session established, session index: " + sessionIndex);
        this.sessionIndex = sessionIndex;
    }

    @Override
    public void lostSession(UUID sessionIndex) {
        log.info("Session lost, session index: " + sessionIndex);
    }

}
