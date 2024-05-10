package chargetransfer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mycorp.chargetransfer.ChargeTransferBlock;
import org.mycorp.chargetransfer.ChargeTransferBlockTask;
import org.mycorp.chargetransfer.eventpublishers.metervaluespresenters.CSMSMeterValuesPresenter;
import org.mycorp.chargetransfer.eventpublishers.metervaluespresenters.MeterValuesPresenter;
import org.mycorp.chargetransfer.eventpublishers.metervaluespresenters.MeterValuesPresenterContext;
import org.mycorp.chargetransfer.eventpublishers.stopcharging.StopChargingEventPublisher;
import org.mycorp.main.ApplicationConfiguration;
import org.mycorp.models.Charge;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ChargeTransferBlockTest {
    @Mock
    private ChargeTransferBlockTask chargeTransferBlockTask;

    @InjectMocks
    private ChargeTransferBlock chargeTransferBlock;

    @Test
    public void setPreparedChargeTest() {
        chargeTransferBlock.setPreparedCharge(new Charge("kwh", 100));
        Mockito.verify(chargeTransferBlockTask, Mockito.times(1)).setPreparedCharge(new Charge("kwh", 100));
    }

    @Test
    public void stopNormallyTest() {
        chargeTransferBlock.stopChargeTransferNormally();
        Mockito.verify(chargeTransferBlockTask, Mockito.times(1)).stopNormally();
    }

    @Test
    public void stopEmergencyTest() {
        chargeTransferBlock.stopChargeTransferEmergency();
        Mockito.verify(chargeTransferBlockTask, Mockito.times(1)).stopEmergency();
    }

    @Test
    public void startTest(){
        chargeTransferBlock.startChargeTransfer();
        Mockito.verify(chargeTransferBlockTask, Mockito.times(1)).run();
    }
}
