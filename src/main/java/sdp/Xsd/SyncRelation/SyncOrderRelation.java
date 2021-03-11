
package sdp.Xsd.SyncRelation;

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
 *         &lt;element name="userID" type="{http://www.csapi.org/schema/parlayx/data/sync/v1_0/local}user"/>
 *         &lt;element name="spID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceList" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="updateType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="updateTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="updateDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="effectiveTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expiryTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extensionInfo" type="{http://www.csapi.org/schema/parlayx/data/sync/v1_0/local}extenseInfo"/>
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
    "spID",
    "productID",
    "serviceID",
    "serviceList",
    "updateType",
    "updateTime",
    "updateDesc",
    "effectiveTime",
    "expiryTime",
    "extensionInfo"
})
@XmlRootElement(name = "syncOrderRelation", namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local")
public class SyncOrderRelation {

    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local", required = true)
    protected User userID;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local", required = true)
    protected String spID;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local", required = true)
    protected String productID;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local", required = true)
    protected String serviceID;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local", required = true)
    protected String serviceList;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local", required = true)
    protected String updateType;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local", required = true)
    protected String updateTime;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local", required = true)
    protected String updateDesc;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local", required = true)
    protected String effectiveTime;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local", required = true)
    protected String expiryTime;
    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local", required = true)
    protected ExtenseInfo extensionInfo;

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
     * Gets the value of the spID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpID() {
        return spID;
    }

    /**
     * Sets the value of the spID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpID(String value) {
        this.spID = value;
    }

    /**
     * Gets the value of the productID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductID() {
        return productID;
    }

    /**
     * Sets the value of the productID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductID(String value) {
        this.productID = value;
    }

    /**
     * Gets the value of the serviceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceID() {
        return serviceID;
    }

    /**
     * Sets the value of the serviceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceID(String value) {
        this.serviceID = value;
    }

    /**
     * Gets the value of the serviceList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceList() {
        return serviceList;
    }

    /**
     * Sets the value of the serviceList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceList(String value) {
        this.serviceList = value;
    }

    /**
     * Gets the value of the updateType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateType() {
        return updateType;
    }

    /**
     * Sets the value of the updateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateType(String value) {
        this.updateType = value;
    }

    /**
     * Gets the value of the updateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets the value of the updateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateTime(String value) {
        this.updateTime = value;
    }

    /**
     * Gets the value of the updateDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateDesc() {
        return updateDesc;
    }

    /**
     * Sets the value of the updateDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateDesc(String value) {
        this.updateDesc = value;
    }

    /**
     * Gets the value of the effectiveTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * Sets the value of the effectiveTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectiveTime(String value) {
        this.effectiveTime = value;
    }

    /**
     * Gets the value of the expiryTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpiryTime() {
        return expiryTime;
    }

    /**
     * Sets the value of the expiryTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpiryTime(String value) {
        this.expiryTime = value;
    }

    /**
     * Gets the value of the extensionInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ExtenseInfo }
     *     
     */
    public ExtenseInfo getExtensionInfo() {
        return extensionInfo;
    }

    /**
     * Sets the value of the extensionInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtenseInfo }
     *     
     */
    public void setExtensionInfo(ExtenseInfo value) {
        this.extensionInfo = value;
    }

}
