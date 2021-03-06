package sdp.Config;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import sdp.Model.Header.RequestSOAPHeader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by Hp on 11/6/2017.
 */
public class HeaderWrapper implements WebServiceMessageCallback {

    private RequestSOAPHeader requestSOAPHeader;

    public HeaderWrapper(RequestSOAPHeader requestSOAPHeader) {
        this.requestSOAPHeader = requestSOAPHeader;
    }


   /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [webServiceMessage]
    *  Workflow ->
    *  1. It takes RequestSOAPHeader .
    *  2. Mashalled the Header Class.

     * Impact ->  It is used for Header value mashalling .
    *
    * */


    @Override
    public void doWithMessage(WebServiceMessage webServiceMessage) throws IOException, TransformerException {
        SoapHeader soapHeader = ((SoapMessage) webServiceMessage).getSoapHeader();
        try {
            JAXBContext context = JAXBContext.newInstance(RequestSOAPHeader.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(requestSOAPHeader, soapHeader.getResult());
        } catch (Exception ex) {
            throw new IOException("error while marshalling authentication.");
        }
    }
}
