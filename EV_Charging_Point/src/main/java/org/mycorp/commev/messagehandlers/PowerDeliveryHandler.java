package org.mycorp.commev.messagehandlers;

import eu.chargetime.ocpp.model.core.ChargePointStatus;
import org.mycorp.messages.V2GBodyAbstractType;
import org.mycorp.messages.req.PowerDeliveryReq;
import org.mycorp.messages.types.enums.ChargeProgress;
import org.mycorp.models.StationVariables;
import org.mycorp.models.events.commev.EVPowerDeliveryRequest;
import org.mycorp.models.events.common.StopChargingNormally;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class PowerDeliveryHandler extends V2GMessageHandlerImpl {
    @Override
    public void handleMessage(V2GBodyAbstractType v2gMessageBody) {
        ChargeProgress chargeProgress = ((PowerDeliveryReq) v2gMessageBody).getChargeProgress();
        switch (chargeProgress) {
            case START:
                applicationEventPublisher.publishEvent(new EVPowerDeliveryRequest(this));
                break;
            case STOP:
                if (StationVariables.getInstance().getChargePointStatusCurrent() == ChargePointStatus.Charging)
                    applicationEventPublisher.publishEvent(new StopChargingNormally(this));
        }
    }
}
