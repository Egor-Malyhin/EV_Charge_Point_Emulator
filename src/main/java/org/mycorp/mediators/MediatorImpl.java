package org.mycorp.mediators;

import org.mycorp.mediators.receivers.*;
import org.mycorp.mediators.senders.Sender;
import org.mycorp.mediators.senders.SenderChargeControlSystem;
import org.mycorp.models.MeterValues;
import org.mycorp.models.station_messages.StationMessage;

import java.util.ArrayList;
import java.util.List;

public class MediatorImpl implements Mediator, MediatorChargeControlSystem {
    protected final List<Colleague> colleagues;

    public MediatorImpl() {
        this.colleagues = new ArrayList<>();
    }

    @Override
    public void notify(Sender sender, StationMessage message) {
        for (Colleague colleague : colleagues) {
            if (shouldProcessMessage(colleague, sender, message))
                colleague.getReceiver().receiveMessage(message);
        }
    }

    @Override
    public void addColleague(Colleague colleague) {
        colleagues.add(colleague);
    }

    @Override
    public MeterValues receiveMeterValues() {
        for (Colleague colleague : colleagues) {
            if (colleague.getReceiver() instanceof ChargeTransferReceiver)
                return ((ChargeTransferReceiver) colleague.getReceiver()).receiveMeterValues();
        }
        throw new RuntimeException("Not include ChargeTransfer Colleague");
    }

    private boolean shouldProcessMessage(Colleague colleague, Sender sender, StationMessage message) {
        if (!(sender instanceof SenderChargeControlSystem)) {
            switch (message.getType()) {
                case MESSAGE_FROM_EV_COMM:
                    return colleague.getReceiver() instanceof ChargeSystemReceiverEVComm;
                case MESSAGE_FROM_CHARGE_TRANSFER:
                    return colleague.getReceiver() instanceof ChargeSystemReceiverChargeTransfer;
                case MESSAGE_FROM_CSMS_COMM:
                    return colleague.getReceiver() instanceof ChargeSystemReceiverCSMSComm;
                default:
                    throw new RuntimeException("Wrong Message Type");
            }
        } else {
            switch (message.getType()) {
                case MESSAGE_TO_EV_COMM:
                    return colleague.getReceiver() instanceof EVCommReceiver;
                case MESSAGE_TO_CHARGE_TRANSFER:
                    return colleague.getReceiver() instanceof ChargeTransferReceiver;
                case MESSAGE_TO_CSMS_COMM:
                    return colleague.getReceiver() instanceof CSMSCommReceiver;
                default:
                    throw new RuntimeException("Wrong Message Type");
            }
        }
    }
}
