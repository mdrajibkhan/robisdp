
package sdp.Xsd.Client.SubscriptionList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subScriptionInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subScriptionInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productNames" type="{http://www.sdp.com/schema/subscription/v1_0/local}productNames"/>
 *         &lt;element name="productDescs" type="{http://www.sdp.com/schema/subscription/v1_0/local}productDescs"/>
 *         &lt;element name="parentID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="parentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="parentNames" type="{http://www.sdp.com/schema/subscription/v1_0/local}parentNames"/>
 *         &lt;element name="spID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="spNames" type="{http://www.sdp.com/schema/subscription/v1_0/local}spNames"/>
 *         &lt;element name="subTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nextChargeTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isAutoExtend" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subScriptionState" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="payType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="srcProductID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="channelID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subApplyTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subExpireTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="amountInfos" type="{http://www.sdp.com/schema/subscription/v1_0/local}amountInfos"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subScriptionInfo", namespace = "http://www.sdp.com/schema/subscription/v1_0/local", propOrder = {
    "orderKey",
    "userID",
    "productID",
    "productNames",
    "productDescs",
    "parentID",
    "parentType",
    "parentNames",
    "spID",
    "spNames",
    "subTime",
    "nextChargeTime",
    "isAutoExtend",
    "subScriptionState",
    "payType",
    "srcProductID",
    "channelID",
    "subApplyTime",
    "subExpireTime",
    "amountInfos"
})
public class SubScriptionInfo {

    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String orderKey;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String userID;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String productID;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected ProductNames productNames;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected ProductDescs productDescs;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String parentID;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String parentType;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected ParentNames parentNames;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String spID;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected SpNames spNames;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String subTime;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String nextChargeTime;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String isAutoExtend;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String subScriptionState;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String payType;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String srcProductID;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String channelID;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String subApplyTime;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected String subExpireTime;
    @XmlElement(namespace = "http://www.sdp.com/schema/subscription/v1_0/local", required = true)
    protected AmountInfos amountInfos;

    /**
     * Gets the value of the orderKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderKey() {
        return orderKey;
    }

    /**
     * Sets the value of the orderKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderKey(String value) {
        this.orderKey = value;
    }

    /**
     * Gets the value of the userID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserID(String value) {
        this.userID = value;
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
     * Gets the value of the productNames property.
     * 
     * @return
     *     possible object is
     *     {@link ProductNames }
     *     
     */
    public ProductNames getProductNames() {
        return productNames;
    }

    /**
     * Sets the value of the productNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductNames }
     *     
     */
    public void setProductNames(ProductNames value) {
        this.productNames = value;
    }

    /**
     * Gets the value of the productDescs property.
     * 
     * @return
     *     possible object is
     *     {@link ProductDescs }
     *     
     */
    public ProductDescs getProductDescs() {
        return productDescs;
    }

    /**
     * Sets the value of the productDescs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductDescs }
     *     
     */
    public void setProductDescs(ProductDescs value) {
        this.productDescs = value;
    }

    /**
     * Gets the value of the parentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentID() {
        return parentID;
    }

    /**
     * Sets the value of the parentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentID(String value) {
        this.parentID = value;
    }

    /**
     * Gets the value of the parentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentType() {
        return parentType;
    }

    /**
     * Sets the value of the parentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentType(String value) {
        this.parentType = value;
    }

    /**
     * Gets the value of the parentNames property.
     * 
     * @return
     *     possible object is
     *     {@link ParentNames }
     *     
     */
    public ParentNames getParentNames() {
        return parentNames;
    }

    /**
     * Sets the value of the parentNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParentNames }
     *     
     */
    public void setParentNames(ParentNames value) {
        this.parentNames = value;
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
     * Gets the value of the spNames property.
     * 
     * @return
     *     possible object is
     *     {@link SpNames }
     *     
     */
    public SpNames getSpNames() {
        return spNames;
    }

    /**
     * Sets the value of the spNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpNames }
     *     
     */
    public void setSpNames(SpNames value) {
        this.spNames = value;
    }

    /**
     * Gets the value of the subTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubTime() {
        return subTime;
    }

    /**
     * Sets the value of the subTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubTime(String value) {
        this.subTime = value;
    }

    /**
     * Gets the value of the nextChargeTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextChargeTime() {
        return nextChargeTime;
    }

    /**
     * Sets the value of the nextChargeTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextChargeTime(String value) {
        this.nextChargeTime = value;
    }

    /**
     * Gets the value of the isAutoExtend property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsAutoExtend() {
        return isAutoExtend;
    }

    /**
     * Sets the value of the isAutoExtend property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsAutoExtend(String value) {
        this.isAutoExtend = value;
    }

    /**
     * Gets the value of the subScriptionState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubScriptionState() {
        return subScriptionState;
    }

    /**
     * Sets the value of the subScriptionState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubScriptionState(String value) {
        this.subScriptionState = value;
    }

    /**
     * Gets the value of the payType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayType() {
        return payType;
    }

    /**
     * Sets the value of the payType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayType(String value) {
        this.payType = value;
    }

    /**
     * Gets the value of the srcProductID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcProductID() {
        return srcProductID;
    }

    /**
     * Sets the value of the srcProductID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcProductID(String value) {
        this.srcProductID = value;
    }

    /**
     * Gets the value of the channelID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelID() {
        return channelID;
    }

    /**
     * Sets the value of the channelID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelID(String value) {
        this.channelID = value;
    }

    /**
     * Gets the value of the subApplyTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubApplyTime() {
        return subApplyTime;
    }

    /**
     * Sets the value of the subApplyTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubApplyTime(String value) {
        this.subApplyTime = value;
    }

    /**
     * Gets the value of the subExpireTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubExpireTime() {
        return subExpireTime;
    }

    /**
     * Sets the value of the subExpireTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubExpireTime(String value) {
        this.subExpireTime = value;
    }

    /**
     * Gets the value of the amountInfos property.
     * 
     * @return
     *     possible object is
     *     {@link AmountInfos }
     *     
     */
    public AmountInfos getAmountInfos() {
        return amountInfos;
    }

    /**
     * Sets the value of the amountInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountInfos }
     *     
     */
    public void setAmountInfos(AmountInfos value) {
        this.amountInfos = value;
    }

}
