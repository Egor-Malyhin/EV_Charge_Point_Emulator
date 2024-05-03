package chargetransfer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mycorp.chargetransfer.ChargeTransferBlockTask;
import org.mycorp.main.ApplicationConfiguration;
import org.mycorp.models.Charge;
import org.mycorp.models.MeterValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ChargeTransferBlockTaskTest {
    @Autowired
    private ChargeTransferBlockTask chargeTransferBlockTask;
    @Test
    public void updateMeterValuesTest() throws InterruptedException {
        chargeTransferBlockTask.setCharge(new Charge("Wh",70));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(chargeTransferBlockTask);

       /* executorService.submit(() -> {
            while (true){
                    Thread.sleep(50);
                    System.out.println("1: " + chargeTransferBlockTask.getMeterValues());
            }

        });*/

        /*executorService.submit(() -> {
            while (true){
                    Thread.sleep(50);
                    System.out.println("2: " + chargeTransferBlockTask.getMeterValues());
            }

        });*/

       while(chargeTransferBlockTask.getMeterValues().getSampledValue().get(0).getValue()<70){
            Thread.sleep(1000);
            System.out.println(chargeTransferBlockTask.getMeterValues());
        }
       System.out.println(executorService.isTerminated());
       executorService.shutdown();
    }

    @Test
    public void getMeterValuesInTimeTest() throws InterruptedException {
        chargeTransferBlockTask.setCharge(new Charge("Wh",1000));
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(chargeTransferBlockTask);

        Thread.sleep(100);

        MeterValues meterValues = chargeTransferBlockTask.getMeterValues();
        MeterValues oldMeterValues = meterValues.clone();

        Thread.sleep(150);

        assertEquals(meterValues, oldMeterValues);
    }

    @Test
    public void instantTest() throws InterruptedException {
        String timeString = "2021-04-20T15:30:00Z";
        Instant customInstant = Instant.parse(timeString);

        Thread.sleep(2000);

        System.out.println(Instant.now());
        System.out.println(customInstant);
    }
}
