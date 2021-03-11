
package sdp.Xsd.Client.SubscriptionList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for productDescs complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productDescs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productDesc" type="{http://www.sdp.com/schema/subscription/v1_0/local}productDesc"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productDescs", namespace = "http://www.sdp.com/schema/subscription/v1_0/local", propOrder = {
    "productDesc"
})
public class ProductDescs {

    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected ProductDesc productDesc;

    /**
     * Gets the value of the productDesc property.
     * 
     * @return
     *     possible object is
     *     {@link ProductDesc }
     *     
     */
    public ProductDesc getProductDesc() {
        return productDesc;
    }

    /**
     * Sets the value of the productDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductDesc }
     *     
     */
    public void setProductDesc(ProductDesc value) {
        this.productDesc = value;
    }

}
