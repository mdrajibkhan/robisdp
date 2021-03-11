
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
 *         &lt;element name="subscribeProductRsp" type="{http://www.csapi.org/schema/parlayx/subscribe/manage/v1_0/local}subscribeProductRsp"/>
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
    "subscribeProductRsp"
})
@XmlRootElement(name = "subscribeProductResponse", namespace = "http://www.csapi.org/schema/parlayx/subscribe/manage/v1_0/local")
public class SubscribeProductResponse {

    @XmlElement(namespace = "http://www.csapi.org/schema/parlayx/subscribe/manage/v1_0/local", required = true)
    protected SubscribeProductRsp subscribeProductRsp;

    /**
     * Gets the value of the subscribeProductRsp property.
     * 
     * @return
     *     possible object is
     *     {@link SubscribeProductRsp }
     *     
     */
    public SubscribeProductRsp getSubscribeProductRsp() {
        return subscribeProductRsp;
    }

    /**
     * Sets the value of the subscribeProductRsp property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscribeProductRsp }
     *     
     */
    public void setSubscribeProductRsp(SubscribeProductRsp value) {
        this.subscribeProductRsp = value;
    }

}
