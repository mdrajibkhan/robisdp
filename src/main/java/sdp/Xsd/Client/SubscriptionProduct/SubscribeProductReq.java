
package sdp.Xsd.Client.SubscriptionProduct;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subscribeProductReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subscribeProductReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userID" type="{http://www.csapi.org/schema/parlayx/subscribe/manage/v1_0/local}user" form="unqualified"/>
 *         &lt;element name="subInfo" type="{http://www.csapi.org/schema/parlayx/subscribe/manage/v1_0/local}info" form="unqualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subscribeProductReq", namespace = "http://www.csapi.org/schema/parlayx/subscribe/manage/v1_0/local", propOrder = {
    "userID",
    "subInfo"
})
public class SubscribeProductReq {

    @XmlElement(required = true)
    protected User userID;
    @XmlElement(required = true)
    protected Info subInfo;

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
     * Gets the value of the subInfo property.
     * 
     * @return
     *     possible object is
     *     {@link Info }
     *     
     */
    public Info getSubInfo() {
        return subInfo;
    }

    /**
     * Sets the value of the subInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Info }
     *     
     */
    public void setSubInfo(Info value) {
        this.subInfo = value;
    }

}
