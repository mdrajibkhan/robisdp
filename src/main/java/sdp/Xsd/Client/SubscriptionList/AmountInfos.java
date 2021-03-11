
package sdp.Xsd.Client.SubscriptionList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for amountInfos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="amountInfos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amountInfo" type="{http://www.sdp.com/schema/subscription/v1_0/local}amountInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "amountInfos", namespace = "http://www.sdp.com/schema/subscription/v1_0/local", propOrder = {
    "amountInfo"
})
public class AmountInfos {

    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected AmountInfo amountInfo;

    /**
     * Gets the value of the amountInfo property.
     * 
     * @return
     *     possible object is
     *     {@link AmountInfo }
     *     
     */
    public AmountInfo getAmountInfo() {
        return amountInfo;
    }

    /**
     * Sets the value of the amountInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountInfo }
     *     
     */
    public void setAmountInfo(AmountInfo value) {
        this.amountInfo = value;
    }

}
