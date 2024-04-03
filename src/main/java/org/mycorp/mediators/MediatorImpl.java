package org.mycorp.mediators;

import org.mycorp.mediators.receivers.*;
import org.mycorp.mediators.senders.Sender;
import org.mycorp.mediators.senders.SenderChargeControlSystem;
import org.mycorp.models.MeterValues;
import org.mycorp.models.station_messages.StationMessage;

import java.util.ArrayList;
import java.util.List;

public class MediatorImpl implements Mediator, MediatorChargeControlSystem {
    protected final List<Receiver> receivers;

    public MediatorImpl() {
        this.receivers = new ArrayList<>();
    }

    @Override
    public void notify(Sender sender, StationMessage message) {
        for (Receiver receiver : receivers) {
            if (shouldProcessMessage(receiver, sender, message))
                receiver.receiveMessage(message);
        }
    }

    @Override
    public void addReceiver(Receiver receiver) {
        receivers.add(receiver);
    }

    @Override
    public MeterValues receiveMeterValues() {
        for (Receiver receiver : receivers) {
            if (receiver instanceof ChargeTransferReceiver)
                return ((ChargeTransferReceiver) receiver).receiveMeterValues();
        }
        throw new RuntimeException("Not include ChargeTransfer Colleague");
    }

    private boolean shouldProcessMessage(Receiver receiver, Sender sender, StationMessage message) {
        if (!(sender instanceof SenderChargeControlSystem)) {
            switch (message.getType()) {
                case MESSAGE_FROM_EV_COMM:
                    return receiver instanceof ChargeSystemReceiverEVComm;
                case MESSAGE_FROM_CHARGE_TRANSFER:
                    return receiver instanceof ChargeSystemReceiverChargeTransfer;
                case MESSAGE_FROM_CSMS_COMM:
                    return receiver instanceof ChargeSystemReceiverCSMSComm;
                default:
                    throw new RuntimeException("Wrong Message Type");
            }
        } else {
            switch (message.getType()) {
                case MESSAGE_TO_EV_COMM:
                    return receiver instanceof EVCommReceiver;
                case MESSAGE_TO_CHARGE_TRANSFER:
                    return receiver instanceof ChargeTransferReceiver;
                case MESSAGE_TO_CSMS_COMM:
                    return receiver instanceof CSMSCommReceiver;
                default:
                    throw new RuntimeException("Wrong Message Type");
            }
        }
    }
}
