
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
 *         &lt;element name="recordSum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subScriptionInfos" type="{http://www.sdp.com/schema/subscription/v1_0/local}subScriptionInfos"/>
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
    "recordSum",
    "subScriptionInfos"
})
@XmlRootElement(name = "getSubScriptionListRsp", namespace = "http://www.sdp.com/schema/subscription/v1_0/local")
public class GetSubScriptionListRsp {

    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String recordSum;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected SubScriptionInfos subScriptionInfos;

    /**
     * Gets the value of the recordSum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordSum() {
        return recordSum;
    }

    /**
     * Sets the value of the recordSum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordSum(String value) {
        this.recordSum = value;
    }

    /**
     * Gets the value of the subScriptionInfos property.
     * 
     * @return
     *     possible object is
     *     {@link SubScriptionInfos }
     *     
     */
    public SubScriptionInfos getSubScriptionInfos() {
        return subScriptionInfos;
    }

    /**
     * Sets the value of the subScriptionInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubScriptionInfos }
     *     
     */
    public void setSubScriptionInfos(SubScriptionInfos value) {
        this.subScriptionInfos = value;
    }

}
