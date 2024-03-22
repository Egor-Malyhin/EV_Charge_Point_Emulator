package org.mycorp.ev_communication;

public interface EvCommunicationBlockInterface {
    void sendPowerRes();
    void sendAuthorizeRes(boolean condition);
    void sendCannotStart();
}
