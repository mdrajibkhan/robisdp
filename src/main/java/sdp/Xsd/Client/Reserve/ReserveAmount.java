
package sdp.Xsd.Client.Reserve;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="endUserIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charge" type="{http://www.csapi.org/schema/parlayx/payment/reserve_amount_charging/v2_1/local}charge"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "endUserIdentifier",
    "charge"
})
@XmlRootElement(name = "reserveAmount", namespace = "http://www.csapi.org/schema/parlayx/payment/reserve_amount_charging/v2_1/local")
public class ReserveAmount {

    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/payment/reserve_amount_charging/v2_1/local", required = true)
    protected String endUserIdentifier;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/payment/reserve_amount_charging/v2_1/local", required = true)
    protected Charge charge;

    /**
     * Gets the value of the endUserIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndUserIdentifier() {
        return endUserIdentifier;
    }

    /**
     * Sets the value of the endUserIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndUserIdentifier(String value) {
        this.endUserIdentifier = value;
    }

    /**
     * Gets the value of the charge property.
     * 
     * @return
     *     possible object is
     *     {@link Charge }
     *     
     */
    public Charge getCharge() {
        return charge;
    }

    /**
     * Sets the value of the charge property.
     * 
     * @param value
     *     allowed object is
     *     {@link Charge }
     *     
     */
    public void setCharge(Charge value) {
        this.charge = value;
    }

}
