
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
 *         &lt;element name="reservationIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charge" type="{http://www.csapi.org/schema/parlayx/payment/reserve_amount_charging/v2_1/local}charge"/>
 *         &lt;element name="referenceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "reservationIdentifier",
    "charge",
    "referenceCode"
})
@XmlRootElement(name = "chargeReservation", namespace = "http://www.csapi.org/schema/parlayx/payment/reserve_amount_charging/v2_1/local")
public class ChargeReservation {

    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/payment/reserve_amount_charging/v2_1/local", required = true)
    protected String reservationIdentifier;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/payment/reserve_amount_charging/v2_1/local", required = true)
    protected Charge charge;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/payment/reserve_amount_charging/v2_1/local", required = true)
    protected String referenceCode;

    /**
     * Gets the value of the reservationIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReservationIdentifier() {
        return reservationIdentifier;
    }

    /**
     * Sets the value of the reservationIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReservationIdentifier(String value) {
        this.reservationIdentifier = value;
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

    /**
     * Gets the value of the referenceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceCode() {
        return referenceCode;
    }

    /**
     * Sets the value of the referenceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceCode(String value) {
        this.referenceCode = value;
    }

}
