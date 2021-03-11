
package sdp.Xsd.SmsNotificationManager;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the sdp.Xsd.SmsNotificationManager package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: sdp.Xsd.SmsNotificationManager
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StopSmsNotification }
     * 
     */
    public StopSmsNotification createStopSmsNotification() {
        return new StopSmsNotification();
    }

    /**
     * Create an instance of {@link StartSmsResponse }
     * 
     */
    public StartSmsResponse createStartSmsResponse() {
        return new StartSmsResponse();
    }

    /**
     * Create an instance of {@link StopSmsResponse }
     * 
     */
    public StopSmsResponse createStopSmsResponse() {
        return new StopSmsResponse();
    }

    /**
     * Create an instance of {@link StartSmsNotification }
     * 
     */
    public StartSmsNotification createStartSmsNotification() {
        return new StartSmsNotification();
    }

}
