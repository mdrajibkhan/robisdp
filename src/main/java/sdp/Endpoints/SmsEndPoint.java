package sdp.Endpoints;

import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;
import sdp.Config.ClientServiceConfig;
import sdp.Model.*;
import sdp.Model.Header.NotifySOAPHeader;
import sdp.Service.*;
import sdp.Util.Error.ServiceException;
import sdp.Util.Error.ServiceFaultException;
import sdp.Util.KeyWord;
import sdp.Util.Response;
import sdp.Util.Utility;
import sdp.Xsd.GetReceivedSms.GetReceivedSms;
import sdp.Xsd.GetSmsDelivery.GetSmsDeliveryStatusReq;
import sdp.Xsd.NotifySmsReceptionDelivery.*;
import sdp.Xsd.SendSms.SendSmsRequest;
import sdp.Xsd.SmsNotificationManager.StartSmsNotification;
import sdp.Xsd.SmsNotificationManager.StopSmsNotification;
import sdp.Xsd.SendSms.SendSmsRequest.*;


import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Created by Atiq on 11/15/2017.
 */

/**
 * FOLLOWING SmsEndPoint CLASS IS GENERATED FOR
 * ============================================
 * Documentation -> SDP Solution API Reference (SMS, ParlayX 2.1)
 * Impact -> The SDP provides SMS capability application programming interfaces (APIs)
 * to connect to it and use its SMS capability to send and receive SMS messages
 */
@Endpoint
public class SmsEndPoint {

    @Autowired
    NotifySmsService notifySmsService;
    @Autowired
    NotifyDeliveryService notifyDeliveryService;
    @Autowired
    BaseService baseService;
    @Autowired
    BaseTypeService baseTypeService;
    @Autowired
    PortfolioService portfolioService;
    @Autowired
    Utility utility;
    @Autowired
    SmsLogService smsLogService;
    @Autowired
    ApiService apiService;
    @Value("${notify.sms.uri}")
    private String notifySmsUri;
    @Value("${notify.sms.context}")
    private String notifySmsContext;
    @Value("${received.sms.uri}")
    private String receivedSmsUri;
    @Value("${received.sms.context}")
    private String receivedSmsContext;
    @Value("${send.sms.uri}")
    private String sendSmsUri;
    @Value("${send.sms.context}")
    private String sendSmsContext;

    private static final String notificationManagerNS = "http://www.csapi.org/schema/parlayx/sms/notification_manager/v2_3/local";
    private static final String notificationNS = "http://www.csapi.org/schema/parlayx/sms/notification/v2_2/local";
    private static final String smsReceiveNS = "http://www.csapi.org/schema/parlayx/sms/receive/v2_2/local";
    private static final String sendSmsNS = "http://www.csapi.org/schema/parlayx/sms/send/v2_2/local";

    public static final String PROVIDER = "PROVIDER";
    /*
    * Following Variable and Methods are used for
    * getting HttpServletRequest properties by
    * using JX-WS configuration
    * */
    private HttpServletRequest httpServletRequest;

    @Autowired
    ClientServiceConfig config;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.httpServletRequest = request;
    }


    /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    * Documentation -> SDP Solution API Reference (SMS, ParlayX 2.1)
    * Content Number -> [2.2 startSmsNotification]
    * Base URL -> http://ip:port/SmsNotificationManagerService/services/SmsNotificationManager
    * Namespace -> http://www.csapi.org/schema/parlayx/sms/notification_manager/v2_3/local
    * Local Part -> startSmsNotification
    * Impact -> It starts subscription for App to receive MO SMS on Notify Mode
    *
    * */
    @PayloadRoot(namespace = notificationManagerNS, localPart = "startSmsNotification")
    @ResponsePayload
    public Response startSmsNotification(@RequestPayload StartSmsNotification request) {
        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(request));
        Response response = new Response();
        try {
            response = config.startSmsNotification(notifySmsContext, notifySmsUri, new GsonBuilder().create().toJson(request));
            utility.showMessage(utility.responseFormat(new GsonBuilder().create().toJson(response)));
            return response;
        } catch (Exception ex) {
            return utility.createResponse(500, ex.getMessage(), ex.toString());
            //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
        }
    }

    /**
     * FOLLOWING stopSmsNotification METHOD IS GENERATED FOR
     * ======================================================
     * Documentation -> SDP Solution API Reference (SMS, ParlayX 2.1)
     * Content Number -> [2.4 stopSmsNotification]
     * Base URL -> http://IP:Port/SmsNotificationManagerService/services/SmsNotificationManager
     * Namespace -> http://www.csapi.org/schema/parlayx/sms/notification_manager/v2_3/locals
     * Local Part -> stopSmsNotification
     * Type -> Performing as a client
     * Impact -> Invoked the stopSmsNotification API to unsubscribe from MO SMS message
     * notification on the SDP (functioning as the provider).
     */
    @PayloadRoot(namespace = notificationManagerNS, localPart = "stopSmsNotification")
    @ResponsePayload
    public Response stopSmsNotification(@RequestPayload StopSmsNotification request) {
        Response response = new Response();
        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(request));
        try {
            response = config.stopSmsNotification(notifySmsContext, notifySmsUri, new GsonBuilder().create().toJson(request));
            utility.showMessage(utility.responseFormat(new GsonBuilder().create().toJson(response)));
            return response;
        } catch (Exception ex) {
            return  utility.createResponse(500, ex.getMessage(), ex.toString());
            //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
        }
    }

    /**
     * FOLLOWING notifySmsResponse METHOD IS GENERATED FOR
     * ======================================================
     * Documentation -> SDP Solution API Reference (SMS, ParlayX 2.1)
     * Content Number -> [2.3 notifySmsReception]
     * Base URL -> http://IP:Port/robisdp
     * Namespace -> http://www.csapi.org/schema/parlayx/sms/notification/v2_2/local
     * Local Part -> notifySmsReception
     * Type -> Performing as a provider
     * Impact -> The SDP (functioning as the client) invokes the notifySmsReception API
     * to send MO SMS messages to the App (functioning as the provider). [Notify Mode]
     */
    @PayloadRoot(namespace = notificationNS, localPart = "notifySmsReception")
    @ResponsePayload
    public NotifySmsReceptionResponse notifySmsResponse(@RequestPayload NotifySmsReception request, @SoapHeader("{" + NotifySOAPHeader.AUTH_NS + "}NotifySOAPHeader") SoapHeaderElement auth) {
        NotifySOAPHeader authentication = getAuthentication(auth);
        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(authentication), utility.convertPOJOtoXml(request));
        ObjectFactory factory = new ObjectFactory();
        String logReq = utility.convertPOJOtoXml(request);
        String logRes = "";
        JSONObject sendSms = new JSONObject();
        Response smsResponse = new Response();
        NotifySmsReceptionResponse response = factory.createNotifySmsReceptionResponse();
        try {
            NotifySOAPHeader notifySOAPHeader = new NotifySOAPHeader();
            PortfolioModel portfolioModel = new PortfolioModel();
            BaseModel base = new BaseModel();
            String mdn = request.getMessage().getSenderAddress();
            base.setMdn(mdn);
            long baseId = baseService.getBaseId(mdn);
            NotifySms notifySms = new NotifySms();
            notifySms.setPortfolioModel(portfolioService.getPortfolioModelByServiceId(authentication.getServiceId()));
            notifySms.setLinkid(authentication.getLinkid());
            notifySms.setTraceUniqueID(authentication.getTraceUniqueID());
            notifySms.setCorrelator(request.getCorrelator());
            notifySms.setMessage(request.getMessage().getMessage());
            BaseModel baseModel = baseService.isBaseExists(request.getMessage().getSenderAddress().substring(4));
            if (baseModel == null) {
                baseModel = new BaseModel();
                baseModel.setMdn(request.getMessage().getSenderAddress().substring(4));
                baseModel.setBaseTypeModel(baseTypeService.getBaseTypeByCode(0));
                baseModel.setId(baseService.addBase(baseModel));
            }
            notifySms.setBaseModel(baseModel);
            notifySms.setSmsServiceActivationNumber(request.getMessage().getSmsServiceActivationNumber());
            notifySms.setDateTime(request.getMessage().getDateTime());
            notifySms.setType("notify");

            long lastId = notifySmsService.getLastId();
            utility.logToCDR(utility.cdrFormat(notifySms),"notifySms");
            long insertId = notifySmsService.addNotify(notifySms);
            if(insertId>lastId){
                utility.showMessage("NotifySMS data inserted for notifySmsReception");
            }
            sendSms.put("portfolioId",portfolioModel.getId());
            sendSms.put("correlator",request.getCorrelator());
            sendSms.put("keyWord",request.getMessage().getMessage());
            sendSms.put("addresses",request.getMessage().getSenderAddress().substring(4));
            sendSms.put("serviceId",authentication.getServiceId());
            sendSms.put("spId",authentication.getSpId());
            sendSms.put("senderName",request.getMessage().getSmsServiceActivationNumber().substring(4));
            ApiModel apiModel = apiService.getApiModelByName(utility.SENDSMS);
            String sendSmsUri = utility.getTargetUri(apiModel);
            String sendSmsContext = apiModel.getJaxb();
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(6000);
                        config.getSendSmsResponse(sendSmsContext, sendSmsUri, sendSms.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }.start();
            response.setMessage("OK");
            logRes = utility.convertPOJOtoXml(response);
            utility.logAdd(KeyWord.PRODUCTION, "NotifySmsReception",PROVIDER,"notifySmsReception",logReq,logRes,"","200");
            utility.responseFormat(logRes);
            utility.showMessage("NotifySmsReception Done");

        } catch (Exception ex) {
            utility.responseFormat(ex.toString());
            utility.logAdd(KeyWord.PRODUCTION, "NotifySmsReception",PROVIDER,"notifySmsReception",logReq,logRes,"","500");

        }
        response.setMessage("OK");
        return response;

    }

    /**
     * FOLLOWING getReceivedSmsResponse METHOD IS GENERATED FOR
     * ========================================================
     * Documentation -> SDP Solution API Reference (SMS, ParlayX 2.1)
     * Content Number -> [2.5 getReceivedSms]
     * Base URL -> http://IP:Port/ReceiveSmsService/services/ReceiveSms
     * Namespace -> http://www.csapi.org/schema/parlayx/sms/receive/v2_2/local
     * Local Part -> getReceivedSms
     * Type -> Performing as a client
     * Impact -> Invoked the getReceivedSms API to
     * obtain MO SMS messages from the SDP (functioning as the provider).
     */
    @PayloadRoot(namespace = smsReceiveNS, localPart = "getReceivedSms")
    @ResponsePayload
    public Response getReceivedSmsResponse(@RequestPayload GetReceivedSms request) {
        Response response = new Response();

        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(request));
        try {
            response=config.getReceivedSms(receivedSmsContext,receivedSmsUri,new GsonBuilder().create().toJson(request));
            utility.showMessage(utility.responseFormat(new GsonBuilder().create().toJson(response)));
            return response;
        } catch (Exception ex) {
             return utility.createResponse(500, ex.getMessage(), ex.toString());
        }
    }

    /**
     * FOLLOWING getSendSmsRspnse METHOD IS GENERATED FOR
     * ========================================================
     * Documentation -> SDP Solution API Reference (SMS, ParlayX 2.1)
     * Content Number -> [3.2 sendSms]
     * Base URL -> http://IP:Port/SendSmsService/services/SendSms
     * Namespace -> http://www.csapi.org/schema/parlayx/sms/send/v2_2/local
     * Local Part -> sendSmsReq
     * Type -> Performing as a client
     * Impact -> This method (functioning as the client) invokes the sendSms API to send SMS messages
     * to the SDP (functioning as the provider).
     */

    @PayloadRoot(namespace = sendSmsNS, localPart = "sendSmsRequest")
    @ResponsePayload
    public Response getSendSmsRspnse(@RequestPayload sdp.Xsd.SendSms.SendSmsRequest request) {
        Response response = new Response();
        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(request));
        try {
            response = config.getSendSmsResponse(sendSmsContext, sendSmsUri, new GsonBuilder().create().toJson(request));
            utility.showMessage(utility.responseFormat(new GsonBuilder().create().toJson(response)));
            return response;
        } catch (Exception ex) {
            return utility.createResponse(500, ex.getMessage(), ex.toString());
            //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
        }
    }

    /**
     * FOLLOWING getSmsDeliveryStatusRspnse METHOD IS GENERATED FOR
     * ============================================================
     * Documentation -> SDP Solution API Reference (SMS, ParlayX 2.1)
     * Content Number -> [3.4 getSmsDeliveryStatus]
     * Base URL -> http://IP:Port/SendSmsService/services/SendSms
     * Namespace -> http://www.csapi.org/schema/parlayx/sms/send/v2_2/local
     * Local Part -> getSmsDeliveryStatusReq
     * Type -> Performing as a client
     * Impact ->  With 'requested' data invokes the getSmsDeliveryStatus API to
     * obtain status reports from the SDP (functioning as the provider).
     */
    @PayloadRoot(namespace = sendSmsNS, localPart = "getSmsDeliveryStatusReq")
    @ResponsePayload
    public Response getSmsDeliveryStatusRspnse(@RequestPayload GetSmsDeliveryStatusReq request) {
        Response response = new Response();
        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(request));
        try {
            response = config.getSmsDeliveryStatusResponse(sendSmsContext, sendSmsUri, new GsonBuilder().create().toJson(request));
            utility.showMessage(utility.responseFormat(new GsonBuilder().create().toJson(response)));
            return response;
        } catch (Exception ex) {
            return utility.createResponse(500, ex.getMessage(), ex.toString());
        }
    }

    /**
     * FOLLOWING notifySmsDeliveryReceiptReq METHOD IS GENERATED FOR
     * =============================================================
     * Documentation -> SDP Solution API Reference (SMS, ParlayX 2.1)
     * Content Number -> [3.3 notifySmsDeliveryReceipt]
     * Base URL -> http://IP:Port/robisdp
     * Namespace -> http://www.csapi.org/schema/parlayx/sms/notification/v2_2/local
     * Local Part -> notifySmsDeliveryReceipt
     * Type -> Performing as a provider
     * Impact -> The SDP (functioning as the client) invokes the notifySmsDeliveryReceipt API
     * to send status reports to the App (functioning as the client).[Notify Mode]
     */
    @PayloadRoot(namespace = notificationNS, localPart = "notifySmsDeliveryReceipt")
    @ResponsePayload
    public NotifySmsDeliveryReceiptResponse notifySmsDeliveryReceiptReq(@RequestPayload NotifySmsDeliveryReceipt request,
                                            @SoapHeader("{" + NotifySOAPHeader.AUTH_NS + "}NotifySOAPHeader") SoapHeaderElement auth) {

        //Response response = new Response();
        NotifySmsDeliveryReceiptResponse response = new NotifySmsDeliveryReceiptResponse();

        NotifySOAPHeader authentication = getAuthentication(auth);
        String logReq = utility.convertPOJOtoXml(request);
        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(authentication), utility.convertPOJOtoXml(request));
        try {
//            SmsLogModel smsLogModel = smsLogService.getSmsLogByCorrelator(request.getCorrelator());
//            if (smsLogModel != null) {
//                smsLogModel.setDeliveryStatus(request.getDeliveryStatus().getDeliveryStatus());
//                utility.logToCDR(utility.cdrFormat(smsLogModel), "smsLog");
//                if (smsLogService.updateDeliveryStatus(smsLogModel) > 0) {
//                    utility.showMessage("SmsLog Updated for notifySmsDeliveryReceipt => " + new GsonBuilder().create().toJson(smsLogModel));
//                }
//            }
//            else {
//                utility.showMessage("SmsLog info not inserted for notifySmsDelivery");
//        }
            utility.logAdd(KeyWord.PRODUCTION, "notifySmsDeliveryReceipt",PROVIDER,"notifySmsDeliveryReceipt",logReq,"","","200");
            utility.showMessage("notifySmsDeliveryReceipt Log added");
        } catch (Exception ex) {
            utility.responseFormat(ex.toString());
            utility.logAdd(KeyWord.PRODUCTION, "notifySmsDeliveryReceipt",PROVIDER,"notifySmsDeliveryReceipt",logReq,ex.toString(),"","500");

        }
        response.setMessage("OK");
        return  response;


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