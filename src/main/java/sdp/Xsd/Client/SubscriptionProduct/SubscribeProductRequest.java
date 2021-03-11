
package sdp.Xsd.Client.SubscriptionProduct;

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
 *         &lt;element name="subscribeProductReq" type="{http://www.csapi.org/schema/parlayx/subscribe/manage/v1_0/local}subscribeProductReq"/>
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
    "subscribeProductReq"
})
@XmlRootElement(name = "subscribeProductRequest", namespace = "http://www.csapi.org/schema/parlayx/subscribe/manage/v1_0/local")
public class SubscribeProductRequest {

    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/subscribe/manage/v1_0/local", required = true)
    protected SubscribeProductReq subscribeProductReq;

    /**
     * Gets the value of the subscribeProductReq property.
     * 
     * @return
     *     possible object is
     *     {@link SubscribeProductReq }
     *     
     */
    public SubscribeProductReq getSubscribeProductReq() {
        return subscribeProductReq;
    }

    /**
     * Sets the value of the subscribeProductReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscribeProductReq }
     *     
     */
    public void setSubscribeProductReq(SubscribeProductReq value) {
        this.subscribeProductReq = value;
    }

}
