
package sdp.Xsd.Client.sendSms;

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
 *         &lt;element name="addresses" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="senderName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receiptRequest" type="{http://www.csapi.org/schema/parlayx/sms/send/v2_2/local}receipt"/>
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
    "addresses",
    "senderName",
    "message",
    "receiptRequest"
})
@XmlRootElement(name = "sendSms", namespace = "http://www.csapi.org/schema/parlayx/sms/send/v2_2/local")
public class SendSms {

    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", required = true)
    protected String addresses;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", required = true)
    protected String senderName;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", required = true)
    protected String message;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", required = true)
    protected Receipt receiptRequest;

    /**
     * Gets the value of the addresses property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddresses() {
        return addresses;
    }

    /**
     * Sets the value of the addresses property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddresses(String value) {
        this.addresses = value;
    }

    /**
     * Gets the value of the senderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * Sets the value of the senderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderName(String value) {
        this.senderName = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the receiptRequest property.
     * 
     * @return
     *     possible object is
     *     {@link Receipt }
     *     
     */
    public Receipt getReceiptRequest() {
        return receiptRequest;
    }

    /**
     * Sets the value of the receiptRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link Receipt }
     *     
     */
    public void setReceiptRequest(Receipt value) {
        this.receiptRequest = value;
    }

}
