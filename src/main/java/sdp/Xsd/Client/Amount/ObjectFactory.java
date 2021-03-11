
package sdp.Xsd.Client.Amount;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the sdp.Xsd.Client.Amount package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: sdp.Xsd.Client.Amount
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RefundAmountResponse }
     * 
     */
    public RefundAmountResponse createRefundAmountResponse() {
        return new RefundAmountResponse();
    }

    /**
     * Create an instance of {@link ChargeAmount }
     * 
     */
    public ChargeAmount createChargeAmount() {
        return new ChargeAmount();
    }

    /**
     * Create an instance of {@link Charge }
     * 
     */
    public Charge createCharge() {
        return new Charge();
    }

    /**
     * Create an instance of {@link ChargeAmountResponse }
     * 
     */
    public ChargeAmountResponse createChargeAmountResponse() {
        return new ChargeAmountResponse();
    }

    /**
     * Create an instance of {@link RefundAmount }
     * 
     */
    public RefundAmount createRefundAmount() {
        return new RefundAmount();
    }

}
