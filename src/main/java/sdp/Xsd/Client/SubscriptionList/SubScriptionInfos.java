
package sdp.Xsd.Client.SubscriptionList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subScriptionInfos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subScriptionInfos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subScriptionInfo" type="{http://www.sdp.com/schema/subscription/v1_0/local}subScriptionInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subScriptionInfos", namespace = "http://www.sdp.com/schema/subscription/v1_0/local", propOrder = {
    "subScriptionInfo"
})
public class SubScriptionInfos {

    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected SubScriptionInfo subScriptionInfo;

    /**
     * Gets the value of the subScriptionInfo property.
     * 
     * @return
     *     possible object is
     *     {@link SubScriptionInfo }
     *     
     */
    public SubScriptionInfo getSubScriptionInfo() {
        return subScriptionInfo;
    }

    /**
     * Sets the value of the subScriptionInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubScriptionInfo }
     *     
     */
    public void setSubScriptionInfo(SubScriptionInfo value) {
        this.subScriptionInfo = value;
    }

}
