package org.mycorp.charge_transfer;

import org.mycorp.models.Charge;
import org.mycorp.models.MeterValues;
import org.mycorp.models.SampledValue;
import org.mycorp.models.StationCharacteristics;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ChargeTransferBlock implements Runnable{
    private Charge charge;
    private MeterValues meterValues;
    private List<SampledValue> valueList;

    public ChargeTransferBlock(){
        this.charge= new Charge(100);
        this.valueList = new ArrayList<>();
        this.valueList.add(new SampledValue(0,"Energy","Outlet", "Wh"));
        this.meterValues= new MeterValues(Instant.now(), valueList);
    }

    @Override
    public void run() {
        Duration durationOfCharging = setDurationTime(charge.kWh());
        Instant startTime = Instant.now();
        while(Duration.between(startTime, Instant.now()).compareTo(durationOfCharging) < 0){
            try{
                Thread.sleep(1000);
                updateSampledValue(Duration.between(startTime, Instant.now()).toMinutes());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Duration setDurationTime(float charge){
        float floatDuration = charge / StationCharacteristics.ratedPower;
        long longDuration = (long) floatDuration;
        return Duration.ofMinutes(longDuration);
    }

    private void updateSampledValue(float durationFromStart){
        SampledValue powerValue = valueList.get(0);
        powerValue.setValue(StationCharacteristics.ratedPower * durationFromStart);
    }

    public MeterValues getMeterValues(){
        meterValues.setTimestamp(Instant.now());
        return this.meterValues;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }
}
