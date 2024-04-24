package org.mycorp.commcsms.message_makers;

import org.mycorp.models.MeterValues;

public interface CoreProfileMessageMaker {
    void sendStartTransaction();

    void sendAuthorize(String idTag);

    void sendStopTransaction();

    void sendMeterValues(MeterValues meterValues);

    void sendBootNotification();
}