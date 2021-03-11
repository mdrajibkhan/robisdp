
package sdp.Xsd.Client.SmsNotification;

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
 *         &lt;element name="reference" type="{http://www.csapi.org/schema/parlayx/sms/notification_manager/v2_3/local}reference"/>
 *         &lt;element name="smsServiceActivationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="criteria" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "reference",
    "smsServiceActivationNumber",
    "criteria"
})
@XmlRootElement(name = "startSmsNotification", namespace = "http://www.csapi.org/schema/parlayx/sms/notification_manager/v2_3/local")
public class StartSmsNotification {

    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/sms/notification_manager/v2_3/local", required = true)
    protected Reference reference;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/sms/notification_manager/v2_3/local", required = true)
    protected String smsServiceActivationNumber;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/sms/notification_manager/v2_3/local", required = true)
    protected String criteria;

    /**
     * Gets the value of the reference property.
     * 
     * @return
     *     possible object is
     *     {@link Reference }
     *     
     */
    public Reference getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reference }
     *     
     */
    public void setReference(Reference value) {
        this.reference = value;
    }

    /**
     * Gets the value of the smsServiceActivationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsServiceActivationNumber() {
        return smsServiceActivationNumber;
    }

    /**
     * Sets the value of the smsServiceActivationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsServiceActivationNumber(String value) {
        this.smsServiceActivationNumber = value;
    }

    /**
     * Gets the value of the criteria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCriteria() {
        return criteria;
    }

    /**
     * Sets the value of the criteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCriteria(String value) {
        this.criteria = value;
    }

}
