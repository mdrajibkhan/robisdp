
package sdp.Xsd.Client.SubscriptionList;

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
 *         &lt;element name="userID" type="{http://www.sdp.com/schema/subscription/v1_0/local}user"/>
 *         &lt;element name="actionType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="langInfo" type="{http://www.sdp.com/schema/subscription/v1_0/local}lang"/>
 *         &lt;element name="recordIndex" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="batchSize" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extensionInfos" type="{http://www.sdp.com/schema/subscription/v1_0/local}extense"/>
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
    "userID",
    "actionType",
    "langInfo",
    "recordIndex",
    "batchSize",
    "extensionInfos"
})
@XmlRootElement(name = "getSubScriptionListReq", namespace = "http://www.sdp.com/schema/subscription/v1_0/local")
public class GetSubScriptionListReq {

    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected User userID;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String actionType;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected Lang langInfo;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String recordIndex;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String batchSize;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected Extense extensionInfos;

    /**
     * Gets the value of the userID property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUserID(User value) {
        this.userID = value;
    }

    /**
     * Gets the value of the actionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * Sets the value of the actionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionType(String value) {
        this.actionType = value;
    }

    /**
     * Gets the value of the langInfo property.
     * 
     * @return
     *     possible object is
     *     {@link Lang }
     *     
     */
    public Lang getLangInfo() {
        return langInfo;
    }

    /**
     * Sets the value of the langInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Lang }
     *     
     */
    public void setLangInfo(Lang value) {
        this.langInfo = value;
    }

    /**
     * Gets the value of the recordIndex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordIndex() {
        return recordIndex;
    }

    /**
     * Sets the value of the recordIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordIndex(String value) {
        this.recordIndex = value;
    }

    /**
     * Gets the value of the batchSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchSize() {
        return batchSize;
    }

    /**
     * Sets the value of the batchSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchSize(String value) {
        this.batchSize = value;
    }

    /**
     * Gets the value of the extensionInfos property.
     * 
     * @return
     *     possible object is
     *     {@link Extense }
     *     
     */
    public Extense getExtensionInfos() {
        return extensionInfos;
    }

    /**
     * Sets the value of the extensionInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Extense }
     *     
     */
    public void setExtensionInfos(Extense value) {
        this.extensionInfos = value;
    }

}
