package org.mycorp;

import eu.chargetime.ocpp.AuthenticationException;
import eu.chargetime.ocpp.ServerEvents;
import eu.chargetime.ocpp.model.SessionInformation;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class ServerEventsListener implements ServerEvents {
    @Override
    public void authenticateSession(SessionInformation information, String username, byte[] password) throws AuthenticationException {

    }

    @Override
    public void newSession(UUID sessionIndex, SessionInformation information) {
        log.info("New Session established, session index: " + sessionIndex);
    }

    @Override
    public void lostSession(UUID sessionIndex) {
        log.info("Session lost, session index: " + sessionIndex);
    }
}
