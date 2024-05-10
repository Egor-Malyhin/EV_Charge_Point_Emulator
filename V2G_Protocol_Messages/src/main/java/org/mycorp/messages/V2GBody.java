package org.mycorp.messages;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElementRef;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class V2GBody {
    @XmlElementRef
    private V2GBodyAbstractType v2GBodyAbstractType;
}
