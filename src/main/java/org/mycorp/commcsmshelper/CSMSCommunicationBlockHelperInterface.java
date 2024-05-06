package org.mycorp.commcsmshelper;

public interface CSMSCommunicationBlockHelperInterface {
    void tryConnecting();

    void sendBootNotification();

    void startMeterValuesRequester();

    void stopMeterValuesRequester();
}
