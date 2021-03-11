package sdp.Util.Error;

import javax.xml.ws.WebFault;

/**
 * Created by Hp on 11/13/2017.
 */
public class ServiceFaultException extends RuntimeException {

    private ServiceException serviceFault;

    public ServiceFaultException(String message, ServiceException serviceFault){
        super(message);
        this.serviceFault = serviceFault;
    }

    public ServiceFaultException(String message, Throwable e, ServiceException serviceFault){
        super(message, e);
        this.serviceFault = serviceFault;
    }

    public ServiceException getServiceFault() {
        return serviceFault;
    }

    public void setServiceFault(ServiceException serviceFault) {
        this.serviceFault = serviceFault;
    }
}
