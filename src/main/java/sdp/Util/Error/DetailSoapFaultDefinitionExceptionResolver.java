package sdp.Util.Error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import sdp.Util.Utility;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Hp on 11/13/2017.
 */
public class DetailSoapFaultDefinitionExceptionResolver extends SoapFaultMappingExceptionResolver {

    @Autowired
    Utility utility;

    private static final QName SERVICEEXCEPTION = new QName("http://www.radiogbd.com", "ServiceException", "ns1");

    @Override
    protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
        utility.showMessage("Exception processed -> " + ((ServiceFaultException) ex).getServiceFault().getCause());
        if (ex instanceof ServiceFaultException) {
            triggerResolvedException(fault,((ServiceFaultException) ex).getServiceFault());
/*              fault.getFaultDetail().addFaultDetailElement(SERVICEEXCEPTION).addText(ex.toString());*/
        } else if (ex instanceof NullPointerException) {
            triggerResolvedException(fault, new ServiceException("500", "Null Pointer Exception", ex.toString()));
        } else if (ex instanceof IllegalArgumentException) {
            triggerResolvedException(fault, new ServiceException("500", "Illegal Argument Exception", ex.toString()));
        }
        else if (ex instanceof IllegalStateException) {
            triggerResolvedException(fault, new ServiceException("500", "Illegal State Exception", ex.toString()));
        }
        else if (ex instanceof InvocationTargetException) {
            triggerResolvedException(fault, new ServiceException("500", "Invocation Target Exception", ex.toString()));
        }
        else{
            triggerResolvedException(fault, new ServiceException("500", "General Exception", ex.toString()));
        }
    }

    private void triggerResolvedException(SoapFault soapFault, ServiceException serviceException){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ServiceException.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            soapFault.addFaultDetail();
            jaxbMarshaller.marshal(serviceException, soapFault.getFaultDetail().getResult());
        }
        catch (Exception ex){
            utility.showMessage("Exception can not be resolved => "+ex.toString());
        }
    }
}
