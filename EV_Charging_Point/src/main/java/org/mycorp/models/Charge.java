package org.mycorp.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Charge implements Cloneable {
    private String unit;
    private float value;

    public Charge(String unit, long value) {
        this.unit = unit;
        this.value = value;
    }

    @Override
    public Charge clone() {
        try {
            return (Charge) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
