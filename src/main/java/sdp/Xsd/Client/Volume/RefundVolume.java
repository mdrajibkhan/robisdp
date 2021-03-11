
package sdp.Xsd.Client.Volume;

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
 *         &lt;element name="volume" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="billingText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="referenceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="parameters" type="{http://www.csapi.org/schema/parlayx/payment/volume_charging/v2_1/local}parameters"/>
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
    "volume",
    "billingText",
    "referenceCode",
    "parameters"
})
@XmlRootElement(name = "refundVolume", namespace = "http://www.csapi.org/schema/parlayx/payment/volume_charging/v2_1/local")
public class RefundVolume {

    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/payment/volume_charging/v2_1/local", required = true)
    protected String endUserIdentifier;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/payment/volume_charging/v2_1/local", required = true)
    protected String volume;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/payment/volume_charging/v2_1/local", required = true)
    protected String billingText;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/payment/volume_charging/v2_1/local", required = true)
    protected String referenceCode;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/payment/volume_charging/v2_1/local", required = true)
    protected Parameters parameters;

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
     * Gets the value of the volume property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVolume() {
        return volume;
    }

    /**
     * Sets the value of the volume property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVolume(String value) {
        this.volume = value;
    }

    /**
     * Gets the value of the billingText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingText() {
        return billingText;
    }

    /**
     * Sets the value of the billingText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingText(String value) {
        this.billingText = value;
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

    /**
     * Gets the value of the parameters property.
     * 
     * @return
     *     possible object is
     *     {@link Parameters }
     *     
     */
    public Parameters getParameters() {
        return parameters;
    }

    /**
     * Sets the value of the parameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parameters }
     *     
     */
    public void setParameters(Parameters value) {
        this.parameters = value;
    }

}
