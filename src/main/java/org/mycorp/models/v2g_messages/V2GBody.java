package org.mycorp.models.v2g_messages;

import javax.xml.bind.annotation.XmlElementRef;
import java.util.Objects;

public class V2GBody {
    @XmlElementRef
    private V2GBodyAbstractType v2GBodyAbstractType;

    public V2GBody(V2GBodyAbstractType v2GBodyAbstractType) {
        this.v2GBodyAbstractType = v2GBodyAbstractType;
    }

    public V2GBody() {
    }

    public V2GBodyAbstractType getV2GBodyAbstractType() {
        return v2GBodyAbstractType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof V2GBody v2GBody)) return false;
        return Objects.equals(getV2GBodyAbstractType(), v2GBody.getV2GBodyAbstractType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getV2GBodyAbstractType());
    }
}
