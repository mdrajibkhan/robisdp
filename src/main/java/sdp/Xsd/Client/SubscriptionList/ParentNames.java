
package sdp.Xsd.Client.SubscriptionList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parentNames complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parentNames">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parentName" type="{http://www.sdp.com/schema/subscription/v1_0/local}parentName"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parentNames", namespace = "http://www.sdp.com/schema/subscription/v1_0/local", propOrder = {
    "parentName"
})
public class ParentNames {

    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected ParentName parentName;

    /**
     * Gets the value of the parentName property.
     * 
     * @return
     *     possible object is
     *     {@link ParentName }
     *     
     */
    public ParentName getParentName() {
        return parentName;
    }

    /**
     * Sets the value of the parentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParentName }
     *     
     */
    public void setParentName(ParentName value) {
        this.parentName = value;
    }

}
