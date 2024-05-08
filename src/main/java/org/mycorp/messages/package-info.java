@XmlSchema(
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns = {
                @XmlNs(prefix = "v2gci_h", namespaceURI = "urn:iso:15118:2:2013:MsgHeader"),
                @XmlNs(prefix = "v2gci_d", namespaceURI = "urn:iso:15118:2:2013:MsgDef"),
                @XmlNs(prefix = "v2gci_t", namespaceURI = "urn:iso:15118:2:2013:MsgDataTypes"),
                @XmlNs(prefix = "v2gci_b", namespaceURI = "urn:iso:15118:2:2013:MsgBody")
        }
)
@XmlAccessorType(XmlAccessType.FIELD)
package org.mycorp.messages;

import javax.xml.bind.annotation.*;
