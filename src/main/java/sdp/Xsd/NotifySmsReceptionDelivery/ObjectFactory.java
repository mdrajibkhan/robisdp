
package sdp.Xsd.NotifySmsReceptionDelivery;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the sdp.Xsd.NotifySmsReceptionDelivery package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: sdp.Xsd.NotifySmsReceptionDelivery
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NotifySmsReceptionResponse }
     * 
     */
    public NotifySmsReceptionResponse createNotifySmsReceptionResponse() {
        return new NotifySmsReceptionResponse();
    }

    /**
     * Create an instance of {@link NotifySmsDeliveryReceiptResponse }
     * 
     */
    public NotifySmsDeliveryReceiptResponse createNotifySmsDeliveryReceiptResponse() {
        return new NotifySmsDeliveryReceiptResponse();
    }

    /**
     * Create an instance of {@link NotifySmsReception }
     * 
     */
    public NotifySmsReception createNotifySmsReception() {
        return new NotifySmsReception();
    }

    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link NotifySmsDeliveryReceipt }
     * 
     */
    public NotifySmsDeliveryReceipt createNotifySmsDeliveryReceipt() {
        return new NotifySmsDeliveryReceipt();
    }

    /**
     * Create an instance of {@link DeliveryStatus }
     * 
     */
    public DeliveryStatus createDeliveryStatus() {
        return new DeliveryStatus();
    }

}
