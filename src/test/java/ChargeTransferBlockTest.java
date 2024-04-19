import org.junit.Test;
import org.junit.runner.RunWith;
import org.mycorp.charge_transfer.ChargeTransferBlock;
import org.mycorp.main.MyConfiguration;
import org.mycorp.models.Charge;
import org.mycorp.models.MeterValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyConfiguration.class)
public class ChargeTransferBlockTest {
    @Autowired
    private ChargeTransferBlock chargeTransferBlock;
    @Test
    public void updateMeterValuesTest() throws InterruptedException {
        chargeTransferBlock.setCharge(new Charge(1000));
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(chargeTransferBlock);

        executorService.submit(() -> {
            while (true){
                    Thread.sleep(50);
                    System.out.println("1: " + chargeTransferBlock.getMeterValues());
            }

        });

        executorService.submit(() -> {
            while (true){
                    Thread.sleep(50);
                    System.out.println("2: " + chargeTransferBlock.getMeterValues());
                    //Thread.sleep(500);
            }

        });


        while(true){}

       /* while(chargeTransferBlock.getMeterValues().getSampledValue().get(0).getValue()<900){
            //Thread.sleep(1000);
            System.out.println(chargeTransferBlock.getMeterValues());
        }*/
       //executorService.shutdown();
    }

    @Test
    public void getMeterValuesInTimeTest() throws InterruptedException {
        chargeTransferBlock.setCharge(new Charge(1000));
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(chargeTransferBlock);

        Thread.sleep(100);

        MeterValues meterValues = chargeTransferBlock.getMeterValues();
        MeterValues oldMeterValues = meterValues.clone();

        Thread.sleep(150);

        assertEquals(meterValues, oldMeterValues);
    }
}
