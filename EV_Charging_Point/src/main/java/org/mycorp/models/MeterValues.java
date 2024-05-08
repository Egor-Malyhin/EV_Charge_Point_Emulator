package org.mycorp.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@EqualsAndHashCode
public class MeterValues implements Cloneable {
    private Instant timestamp;
    private Charge charge;

    public MeterValues(Instant timestamp, Charge charge) {
        this.timestamp = timestamp;
        this.charge = charge;
    }

    @Override
    public MeterValues clone() {
        try {
            MeterValues clone = (MeterValues) super.clone();
            clone.charge = this.charge.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
