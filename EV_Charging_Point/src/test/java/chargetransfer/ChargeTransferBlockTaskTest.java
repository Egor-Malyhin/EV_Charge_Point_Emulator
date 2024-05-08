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

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ChargeTransferBlockTaskTest {
    @Autowired
    private ChargeTransferBlockTask chargeTransferBlockTask;

    @Test
    public void taskTest() throws InterruptedException, ExecutionException {
        chargeTransferBlockTask.setPreparedCharge(new Charge("Wh", 50));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(chargeTransferBlockTask);
        future.get();
        executorService.shutdown();
    }

    @Test
    public void getMeterValuesInTimeTest() throws InterruptedException {
        chargeTransferBlockTask.setPreparedCharge(new Charge("Wh", 1000));
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(chargeTransferBlockTask);

        Thread.sleep(100);

        MeterValues meterValues = chargeTransferBlockTask.getMeterValues();
        MeterValues oldMeterValues = meterValues.clone();

        Thread.sleep(150);

        assertEquals(meterValues, oldMeterValues);
    }

    @Test
    public void integratedTaskTest() throws ExecutionException, InterruptedException {
        chargeTransferBlockTask.setPreparedCharge(new Charge("Wh", 50));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ScheduledExecutorService additionalExecutorService = Executors.newScheduledThreadPool(3);
        Future<?> future = executorService.submit(chargeTransferBlockTask);
        for (int i = 0; i < 3; i++) {
            additionalExecutorService.scheduleAtFixedRate(() -> {
                System.out.println(chargeTransferBlockTask.getMeterValues());
            }, 2, 2, TimeUnit.SECONDS);
        }
        future.get();
        executorService.shutdown();
        additionalExecutorService.shutdown();
    }

    @Test
    public void taskCanceledTest() throws InterruptedException, ExecutionException {
        chargeTransferBlockTask.setPreparedCharge(new Charge("Wh", 300));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(chargeTransferBlockTask);
        Thread.sleep(1500);
        Instant beforeStop = Instant.now();
        chargeTransferBlockTask.stopNormally();
        future.get();
        System.out.println("Seconds before stop: " + Duration.between(beforeStop, Instant.now()).toMillis() * 0.001);
        executorService.shutdown();
    }

}
