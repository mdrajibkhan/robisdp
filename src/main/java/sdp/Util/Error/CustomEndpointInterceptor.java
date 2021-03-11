package sdp.Util.Error;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.context.DefaultMessageContext;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import sdp.Util.Utility;

/**
 * Created by Hp on 11/13/2017.
 */
public class CustomEndpointInterceptor implements EndpointInterceptor {

    @Autowired
    Utility utility;

    private static final Log LOG = LogFactory.getLog(CustomEndpointInterceptor.class);

    @Override
    public boolean handleRequest(MessageContext messageContext, Object o) throws Exception {
        LOG.info("Endpoint Request Handling");
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object o) throws Exception {
        LOG.info("Endpoint Response Handling");
        utility.showMessage(messageContext.toString());
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object o) throws Exception {
        LOG.info("Endpoint Exception Handling");
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object o, Exception e) throws Exception {
        LOG.info("Execute code after completion");
    }
}
