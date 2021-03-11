package sdp.Endpoints;

import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import sdp.Config.ClientServiceConfig;
import sdp.Model.Header.NotifySOAPHeader;
import sdp.Service.BaseService;
import sdp.Service.ChannelService;
import sdp.Service.SubscribeProductService;
import sdp.Service.SyncRelationService;
import sdp.Util.Error.ServiceException;
import sdp.Util.Error.ServiceFaultException;
import sdp.Util.Response;
import sdp.Util.Utility;
import sdp.Xsd.SubScriptionList.SubscriptionListReq;
import sdp.Xsd.SubscribeProduct.UnSubscribeProductReq;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Created by Atiq on 11/15/2017.
 */

/**
 * FOLLOWING SubscribeEndPoint CLASS IS GENERATED FOR
 * ==================================================
 * Documentation -> SDP Solution API Reference (Subscribe, SOAP)
 * Impact ->
 */
@Endpoint
public class SubscribeEndPoint {

    @Autowired
    Utility utility;

    @Value("${subscription.uri}")
    private String subscriptionUri;
    @Value("${subscription.context}")
    private String subscriptionContext;
    @Value("${unsubscribe.uri}")
    private String unsubscribeUri;
    @Value("${unsubscribe.context}")
    private String unsubcribeContext;
    @Value("${subscriptionList.uri}")
    private String subscriptionListUri;
    @Value("${subscriptionList.context}")
    private String subscriptionListContext;
    private HttpServletRequest httpServletRequest;
    private static final String subscribeNS = "http://www.csapi.org/schema/parlayx/subscribe/manage/v1_0/local";
    private static final String subscriptionListNS = "http://www.sdp.com/schema/subscription/v1_0/local";
    @Autowired
    ClientServiceConfig config;
    @Autowired
    SubscribeProductService subscribeProductService;
    @Autowired
    BaseService baseService;

    @Autowired
    ChannelService channelService;
    @Autowired
    SyncRelationService syncRelationService;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.httpServletRequest = request;
    }

    /**
     * FOLLOWING subscribeProductResponse METHOD IS GENERATED FOR
     * ======================================================
     * Documentation -> SDP Solution API Reference (Subscribe, SOAP)
     * Content Number -> [2.2 subscribeProduct]
     * Base URL -> http://IP:Port/SubscribeManageService/services/SubscribeManage
     * Namespace -> http://www.csapi.org/schema/parlayx/subscribe/manage/v1_0/local
     * Local Part -> subscribeProductReq
     * Type -> Performing as a client
     * Impact -> The SP portal or the carrier customer service system (functioning as the client)
     * invokes the subscribeProduct API to send service subscription messages to the SDP (functioning as the provider).
     */
    @PayloadRoot(namespace = subscribeNS, localPart = "subscribeProductReq")
    @ResponsePayload
    public Response subscribeProductResponse(@RequestPayload sdp.Xsd.SubscribeProduct.SubscribeProductReq request) {
        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(request));
        try {
            Response response = new Response();
            response = config.subscribeProductResponse(subscriptionContext, subscriptionUri, new GsonBuilder().create().toJson(request));
            utility.showMessage(new GsonBuilder().create().toJson(response));
            return response;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
        }

    }

    /**
     * FOLLOWING unsubscribeProductResponse METHOD IS GENERATED FOR
     * ======================================================
     * Documentation -> SDP Solution API Reference (Subscribe, SOAP)
     * Content Number -> [3.2 unSubscribeProduct]
     * Base URL -> http://IP:Port/SubscribeManageService/services/SubscribeManage
     * Namespace -> http://www.csapi.org/schema/parlayx/subscribe/manage/v1_0/local
     * Local Part -> unSubscribeProductReq
     * Type -> Performing as a client
     * Impact -> The SP portal or the carrier customer service system (functioning as the client) invokes
     * the unSubscribeProduct API to send service unsubscription messages to the SDP (functioning as the server).
     */
    @PayloadRoot(namespace = subscribeNS, localPart = "unSubscribeProductReq")
    @ResponsePayload
    public Response unsubscribeProductResponse(@RequestPayload UnSubscribeProductReq request) {
        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(request));
        Response response = new Response();
        try {
            response = config.getUnsubscribeProductResponse(unsubcribeContext, unsubscribeUri, new GsonBuilder().create().toJson(request));
            utility.showMessage(utility.responseFormat(new GsonBuilder().create().toJson(response)));
            return response;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
        }

    }

    /**
     * FOLLOWING getSubListRes METHOD IS GENERATED FOR
     * ===============================================
     * Documentation -> SDP Solution API Reference (Subscribe, SOAP)
     * Content Number -> [4.2 getSubscriptionList]
     * Base URL -> http://IP:Port/SubScriptionService/services/SubScription
     * Namespace -> http://www.sdp.com/schema/subscription/v1_0/local
     * Local Part -> subscriptionListReq
     * Type -> Performing as a client
     * Impact -> The carrier customer service system functions as the client and sends a getSubscriptionListRequest message
     * to the SDP to obtain the subscription relationship list.
     */
    @PayloadRoot(namespace = subscriptionListNS, localPart = "subscriptionListReq")
    @ResponsePayload

    public Response getSubListRes(@RequestPayload SubscriptionListReq request) {
        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(request));
        Response response = new Response();
        try {
            response = config.getSubScriptnListResponse(subscriptionListContext, subscriptionListUri, new GsonBuilder().create().toJson(request));
            utility.showMessage(utility.responseFormat(new GsonBuilder().create().toJson(response)));
            return response;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
        }
    }

    public NotifySOAPHeader getAuthentication(SoapHeaderElement header) {
        NotifySOAPHeader authentication = null;
        try {
            JAXBContext context = JAXBContext.newInstance(NotifySOAPHeader.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            authentication = (NotifySOAPHeader) unmarshaller.unmarshal(header.getSource());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authentication;
    }
}
