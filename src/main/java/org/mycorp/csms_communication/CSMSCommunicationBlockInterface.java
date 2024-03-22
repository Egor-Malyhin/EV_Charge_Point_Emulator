package org.mycorp.csms_communication;

import org.mycorp.models.MeterValues;

public interface CSMSCommunicationBlockInterface {
    void sendStartTransaction();
    void sendAuthorize(String idTag);
    void sendStopTransaction();
    void sendMeterValues(MeterValues meterValues);
    void sendBootNotification(StationConfiguration configuration);

}
