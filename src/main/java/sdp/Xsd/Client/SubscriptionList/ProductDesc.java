
package sdp.Xsd.Client.SubscriptionList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for productDesc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productDesc">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="langInfo" type="{http://www.sdp.com/schema/subscription/v1_0/local}langInfo" form="unqualified"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" form="unqualified"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" form="unqualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productDesc", namespace = "http://www.sdp.com/schema/subscription/v1_0/local", propOrder = {
    "langInfo",
    "name",
    "value"
})
public class ProductDesc {

    @XmlElement(required = true)
    protected LangInfo langInfo;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String value;

    /**
     * Gets the value of the langInfo property.
     * 
     * @return
     *     possible object is
     *     {@link LangInfo }
     *     
     */
    public LangInfo getLangInfo() {
        return langInfo;
    }

    /**
     * Sets the value of the langInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link LangInfo }
     *     
     */
    public void setLangInfo(LangInfo value) {
        this.langInfo = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

}
