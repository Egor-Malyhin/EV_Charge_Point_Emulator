package org.mycorp.commcsmshelper;

public interface CSMSCommunicationBlockHelperInterface {
    void sendBootNotification();

    void startMeterValuesRequester();

    void stopMeterValuesRequester();
}
