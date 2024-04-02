package org.mycorp.charge_transfer;

import org.mycorp.mediators.senders.Sender;
import org.mycorp.models.Charge;
import org.mycorp.models.MeterValues;
import org.mycorp.models.SampledValue;
import org.mycorp.models.StationCharacteristics;
import org.mycorp.models.station_messages.charge_transfer_messages.StopChargingByStationMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ChargeTransferBlock implements Runnable{
    private final Sender senderChargeTransfer;
    private Charge charge;
    private MeterValues meterValues;
    private boolean isRunning;
    @Autowired
    public ChargeTransferBlock(Sender senderChargeTransfer){
        this.charge= new Charge(0);
        List<SampledValue> valueList = new ArrayList<>();
        valueList.add(new SampledValue(0,"Energy","Outlet", "Wh"));
        this.meterValues= new MeterValues(Instant.now(), valueList);
        this.isRunning = true;
        this.senderChargeTransfer = senderChargeTransfer;
    }
    @Override
    public void run() {
        Duration durationOfCharging = setDurationTime(charge.kWh());
        Instant startTime = Instant.now();
        while (isRunning) {
            while (Duration.between(startTime, Instant.now()).compareTo(durationOfCharging) < 0) {
                try {
                    Thread.sleep(1000);
                    updateSampledValue(Duration.between(startTime, Instant.now()).toMinutes());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            stop();
            senderChargeTransfer.sendMessage(new StopChargingByStationMessage());
        }
    }
    private Duration setDurationTime(float charge){
        float floatDuration = charge / StationCharacteristics.ratedPower;
        long longDuration = (long) floatDuration;
        return Duration.ofMinutes(longDuration);
    }
    private void updateSampledValue(float durationFromStart){
        SampledValue powerValue = meterValues.getSampledValue().get(0);
        powerValue.setValue(StationCharacteristics.ratedPower * durationFromStart);
        meterValues.setTimestamp(Instant.now());
    }
    public MeterValues getMeterValues(){
        return meterValues.clone();
    }
    public void setCharge(Charge charge) {
        this.charge = charge;
    }
    public void stop(){
        isRunning = false;
    }
}
