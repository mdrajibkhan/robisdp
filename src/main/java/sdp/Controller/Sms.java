package sdp.Controller;

import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sdp.Config.ClientServiceConfig;
import sdp.Model.ApiModel;
import sdp.Service.*;
import sdp.Util.Response;
import sdp.Util.Utility;
import sdp.Xsd.GetReceivedSms.GetReceivedSms;
import sdp.Xsd.GetSmsDelivery.GetSmsDeliveryStatusReq;
import sdp.Xsd.SendSms.SendSmsRequest;
import sdp.Xsd.SmsNotificationManager.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Atiq on 1/1/2018.
 */
@RestController
@RequestMapping(value = "/sdp/sms")
public class Sms {

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
    ApiService apiService;
    @Autowired
    SmsLogService smsLogService;

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

    @RequestMapping(method = RequestMethod.POST, value = "/startSmsNotification"/*, consumes = {MediaType.APPLICATION_JSON_VALUE}*/)
    public Response startSmsNotification(HttpServletRequest request, @RequestBody String reqBody) {
        utility.printCallingUrl(this.httpServletRequest, new JSONObject(reqBody).toString());
        Response response = new Response();
        String auth = request.getHeader("Authorization");
        String authentication = utility.getBasicAuth();

        if (auth.equals(authentication)) {

            StartSmsNotification startSmsNotification = new StartSmsNotification();
            JSONObject jsonObject = new JSONObject(reqBody);
            startSmsNotification.setServiceId(jsonObject.optString("serviceId"));
            try {
                ApiModel apiModel = apiService.getApiModelByName(utility.NOTIFYSMS);
                String notifySmsUri = utility.getTargetUri(apiModel);
                String notifySmsContext = apiModel.getJaxb();
                response = config.startSmsNotification(notifySmsContext, notifySmsUri, new GsonBuilder().create().toJson(startSmsNotification));
                return response;
            } catch (Exception ex) {
                return utility.createResponse(500, ex.getMessage(), ex.toString());
            }


        } else {


            response = utility.createResponse(500, "Error", "Authorization Error");
        }


        return response;

    }

    @RequestMapping(method = RequestMethod.POST, value = "/stopSmsNotification"/*, consumes = {MediaType.APPLICATION_JSON_VALUE}*/)
    public Response stopSmsNotification(HttpServletRequest request, @RequestBody String reqBody) {
        utility.printCallingUrl(this.httpServletRequest, new JSONObject(reqBody).toString());
        Response response = new Response();
        String auth = request.getHeader("Authorization");
        String authentication = utility.getBasicAuth();

        if (auth.equals(authentication)) {

            StopSmsNotification stopSmsNotification = new StopSmsNotification();
            JSONObject jsonObject = new JSONObject(reqBody);
            stopSmsNotification.setServiceId(jsonObject.optString("serviceId"));
            try {
                ApiModel apiModel = apiService.getApiModelByName(utility.NOTIFYSMS);
                String notifySmsUri = utility.getTargetUri(apiModel);
                String notifySmsContext = apiModel.getJaxb();
                response = config.stopSmsNotification(notifySmsContext, notifySmsUri, new GsonBuilder().create().toJson(stopSmsNotification));
                return response;
            } catch (Exception ex) {
                return utility.createResponse(500, ex.getMessage(), ex.toString());
            }
        } else {


            response = utility.createResponse(500, "Error", "Authorization Error");
        }

        return response;


    }

    @RequestMapping(method = RequestMethod.POST, value = "/getRecievedSms"/*, consumes = {MediaType.APPLICATION_JSON_VALUE}*/)
    public Response getReceivedSms(HttpServletRequest request, @RequestBody String reqBody) {
        utility.printCallingUrl(this.httpServletRequest, new JSONObject(reqBody).toString());
        Response response = new Response();

        String auth = request.getHeader("Authorization");
        String authentication = utility.getBasicAuth();

        if (auth.equals(authentication)) {

            GetReceivedSms receivedSms = new GetReceivedSms();
            JSONObject jsonObject = new JSONObject(reqBody);
            receivedSms.setServiceId(jsonObject.optString("serviceId"));
            try {
                ApiModel apiModel = apiService.getApiModelByName(utility.RECEIVEDSMS);
                String receivedSmsUri = utility.getTargetUri(apiModel);
                String receivedSmsContext = apiModel.getJaxb();
                response = config.getReceivedSms(receivedSmsContext, receivedSmsUri, new GsonBuilder().create().toJson(receivedSms));
                return response;
            } catch (Exception ex) {
                return utility.createResponse(500, ex.getMessage(), ex.toString());
            }

        } else {


            response = utility.createResponse(500, "Error", "Authorization Error");
        }


        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sendSms"/*, consumes = {MediaType.APPLICATION_JSON_VALUE}*/)
    public Response sendSms(HttpServletRequest request, @RequestBody String reqBody) {
        utility.printCallingUrl(this.httpServletRequest, new JSONObject(reqBody).toString());
        Response response = new Response();
        String auth = request.getHeader("Authorization");
        String authentication = utility.getBasicAuth();

        if (auth.equals(authentication)) {

            SendSmsRequest sendSms = new SendSmsRequest();
            JSONObject jsonObject = new JSONObject(reqBody);
            sendSms.setServiceId(jsonObject.optString("serviceId"));
            sendSms.setAddresses(jsonObject.optString("addresses"));
            try {
                ApiModel apiModel = apiService.getApiModelByName(utility.SENDSMS);
                String sendSmsUri = utility.getTargetUri(apiModel);
                String sendSmsContext = apiModel.getJaxb();
                response = config.getSendSmsResponse(sendSmsContext, sendSmsUri, new GsonBuilder().create().toJson(sendSms));
                return response;
            } catch (Exception ex) {
                return utility.createResponse(500, ex.getMessage(), ex.toString());
            }

        } else {


            response = utility.createResponse(500, "Error", "Authorization Error");
        }


        return response;

    }

    @RequestMapping(method = RequestMethod.POST, value = "/getSmsDeliveryStatus"/*, consumes = {MediaType.APPLICATION_JSON_VALUE}*/)
    public Response getSmsDeliveryStatus(HttpServletRequest request, @RequestBody String reqBody) {
        utility.printCallingUrl(this.httpServletRequest, new JSONObject(reqBody).toString());
        Response response = new Response();
        String auth = request.getHeader("Authorization");
        String authentication = utility.getBasicAuth();

        if (auth.equals(authentication)) {


            GetSmsDeliveryStatusReq smsDeliveryStatus = new GetSmsDeliveryStatusReq();
            JSONObject jsonObject = new JSONObject(reqBody);
            smsDeliveryStatus.setServiceId(jsonObject.optString("serviceId"));
            smsDeliveryStatus.setEndUserId(jsonObject.optString("endUserId"));
            try {
                ApiModel apiModel = apiService.getApiModelByName(utility.SENDSMS);
                String sendSmsUri = utility.getTargetUri(apiModel);
                String sendSmsContext = apiModel.getJaxb();
                response = config.getSmsDeliveryStatusResponse(sendSmsContext, sendSmsUri, new GsonBuilder().create().toJson(smsDeliveryStatus));
                return response;
            } catch (Exception ex) {
                return utility.createResponse(500, ex.getMessage(), ex.toString());
            }
        } else {


            response = utility.createResponse(500, "Error", "Authorization Error");
        }


        return response;


    }
}
