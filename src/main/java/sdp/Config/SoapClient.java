package sdp.Config;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import sdp.Model.Header.RequestSOAPHeader;
import sdp.Util.Error.ServiceException;
import sdp.Util.Error.ServiceFaultException;
import sdp.Util.Utility;

/**
 * Created by Hp on 11/7/2017.
 */
@Component
public class SoapClient {

    @Autowired
    Utility utility;

    Jaxb2Marshaller jaxb2Marshaller;
    WebServiceTemplate webServiceTemplate;
    String uri;


     /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [jaxbContext(String),defaultUri(String)]
    *  Workflow ->
    *  1. It passes  jaxbContext,defaultUri parameters .
    *  2. Un/Mashalled jaxbContext.
    *  3. Set defaultUri in webServiceTemplate.
    * Impact ->  It is used for configuration.
    *
    * */

    public void setConfigs(String jaxbContext, String defaultUri) {
        this.uri = defaultUri;
        jaxb2Marshaller = new Jaxb2Marshaller();
        webServiceTemplate = new WebServiceTemplate();
        jaxb2Marshaller.setContextPath(jaxbContext);
        webServiceTemplate.setMarshaller(jaxb2Marshaller);
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller);
        webServiceTemplate.setDefaultUri(defaultUri);
    }


     /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [jaxbContext(String),defaultUri(String),request(Object), header(RequestSOAPHeader)]
    *  Workflow ->
    *  1. It passes  jaxbContext,defaultUri request and header parameters .
    *  2. Pass the parameter data for converting POJO to xml in utility class.

    * Impact ->  It is used for coverting POJO to XML .
    *
    * */


    public <T> Object executeAsync(String jaxbContext, String defaultUri, Object request, RequestSOAPHeader header) throws Exception {
        try {
            setConfigs(jaxbContext, defaultUri);
            utility.showMessage("Calling ROBI SDP API -> " + this.uri);
            utility.showMessage("SOAP Header -> " + utility.convertPOJOtoXml(header));
            utility.showMessage("SOAP Body -> " + utility.convertPOJOtoXml(request));
            Object object = (Object) webServiceTemplate.marshalSendAndReceive(request, new HeaderWrapper(header));
            utility.showMessage("SOAP Response -> " + utility.convertPOJOtoXml(object));
            return object;
        } catch (Exception ex) {
            utility.showMessage("Exception -> " + ex.toString());
            //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
            return null;
        }

    }

    public <T> Object executeAsync(String jaxbContext, String defaultUri, Object request, sdp.Model.SendSms.RequestSOAPHeader header) throws Exception {
        try {
            setConfigs(jaxbContext, defaultUri);
            utility.showMessage("Calling ROBI SDP API -> " + this.uri);
            utility.showMessage("SOAP Header -> " + utility.convertPOJOtoXml(header));
            utility.showMessage("SOAP Body -> " + utility.convertPOJOtoXml(request));
            Object object = (Object) webServiceTemplate.marshalSendAndReceive(request, new SmsHeaderWrapper(header));
            utility.showMessage("SOAP Response -> " + utility.convertPOJOtoXml(object));
            return object;
        } catch (Exception ex) {
            utility.showMessage("Exception -> " + ex.toString());
            //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
            return null;
        }

    }


}
