package sdp.Config;

import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sdp.Model.*;
import sdp.Model.Header.RequestSOAPHeader;
import sdp.Service.*;
import sdp.Util.KeyWord;
import sdp.Util.Response;
import sdp.Util.Utility;
import sdp.Xsd.Client.Amount.Charge;
import sdp.Xsd.Client.Amount.*;
import sdp.Xsd.Client.ReceivedSms.GetReceivedSms;
import sdp.Xsd.Client.ReceivedSms.GetReceivedSmsResponse;
import sdp.Xsd.Client.Reserve.*;
import sdp.Xsd.Client.SmsNotification.*;
import sdp.Xsd.Client.SubscriptionList.GetSubScriptionListReq;
import sdp.Xsd.Client.SubscriptionList.GetSubScriptionListRsp;
import sdp.Xsd.Client.SubscriptionProduct.*;
import sdp.Xsd.Client.Volume.ChargeVolume;
import sdp.Xsd.Client.Volume.ChargeVolumeResponse;
import sdp.Xsd.Client.Volume.RefundVolume;
import sdp.Xsd.Client.Volume.RefundVolumeResponse;
import sdp.Xsd.Client.sendSms.*;

import java.sql.Timestamp;

/**
 * Created by Tanzil on 11/15/2017.
 */
@Component
public class ClientServiceConfig {

    @Autowired
    SoapClient soapClient;
    @Autowired
    HeaderService headerService;
    @Autowired
    PortfolioService portfolioService;
    @Autowired
    ContentService contentService;
    @Autowired
    BaseService baseService;
    @Autowired
    SmsLogService smsLogService;
    @Autowired
    BaseTypeService baseTypeService;
    @Autowired
    AmountService chargeAmountService;
    @Autowired
    VolumeService volumeService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    Utility utility;
    @Autowired
    SyncRelationService syncRelationService;
    @Autowired
    NotifySmsService notifySmsService;
    @Autowired
    StartSmsNotificationService startSmsNotificationService;
    @Autowired
    ChannelService channelService;
    @Autowired
    SubscribeProductService subscribeProductService;
    @Autowired
    LogService logservice;

    private static final String client = "CLIENT";




    /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [jaxbContext(String),defaultUri(String),value(String)]
    *  Workflow ->
    *  1. Converting [value] to JSONObject.
    *  2. Making POJO classes.
    *  3. Sent response to SOAPClient for un/marshalling.
    *  4. Typecasting response  to StartSmsNotificationResponse and return.
    * Content Number -> [2.2 startSmsNotification]
    * Base URL -> http://ip:port/SmsNotificationManagerService/services/SmsNotificationManager
    * Namespace -> http://www.csapi.org/schema/parlayx/sms/notification_manager/v2_3/local
    * Local Part -> startSmsNotification
    * Impact -> It starts subscription for App to receive MO SMS on Notify Mode
    *
    * */


    public Response startSmsNotification(String jaxbContext, String defaultUri, String value) throws Exception {
        StringBuffer cdr = new StringBuffer();
        Response response = new Response();
        JSONObject jsonObject = new JSONObject(value);
        String correlator = "";
        String interfaceName = "";
        String endPoint = "";
        PortfolioModel portfolioModel = portfolioService.getPortfolioModelByServiceId(jsonObject.optString("serviceId"));
        if (portfolioModel == null) {
            response = utility.createResponse(500, "Error", "Portfolio not found in Portfolio tbl for this ServiceId : " + jsonObject.optString("serviceId"));
            return response;
        }
        StartSmsNotificationModel dbsm = startSmsNotificationService.findStartSmsNotificationModelByPfm(portfolioModel);
        if (dbsm != null && dbsm.getStatus() == 1)
            return utility.createResponse(500, "Service Running", "Service(smsNotification) already started  hence not new service started for this ServiceId : " + jsonObject.optString("serviceId"));
        correlator = utility.generateReference();
        interfaceName = headerService.getValue("INTERFACE_NAME");
        endPoint = headerService.getValue("ENDPOINT");
        StartSmsNotification request = new StartSmsNotification();
        request.setSmsServiceActivationNumber(jsonObject.optString("smsServiceActivationNumber"));
        request.setCriteria(jsonObject.optString("criteria"));
        Reference reference = new Reference();
        reference.setCorrelator(correlator);
        reference.setInterfaceName(interfaceName);
        reference.setEndpoint(endPoint);
        request.setReference(reference);
        RequestSOAPHeader header = new RequestSOAPHeader();
        String timeStamp = utility.getTimeStamp();
        header.setSpId(portfolioModel.getSpId());
        header.setSpPassword(utility.convertMD5(portfolioModel.getSpId(), headerService.getValue("SP_PASSWORD"), timeStamp));
        header.setServiceId(portfolioModel.getServiceId());
        header.setTimeStamp(timeStamp);
        long startTime = System.currentTimeMillis();
        StartSmsNotificationResponse result = (StartSmsNotificationResponse) soapClient.executeAsync(jaxbContext, defaultUri, request, header);
        String trvlTime = utility.getTrvlTime(startTime, System.currentTimeMillis());
        String logReq = utility.convertPOJOtoXml(request);
        String logRes = utility.convertPOJOtoXml(result);
        if (result != null) {
            StartSmsNotificationModel startSmsNotificationModel = new StartSmsNotificationModel();
            startSmsNotificationModel.setCorrelator(correlator);
            startSmsNotificationModel.setEndpoint(endPoint);
            startSmsNotificationModel.setPortfolioModel(portfolioModel);
            startSmsNotificationModel.setInterfaceName(interfaceName);
            startSmsNotificationModel.setStatus(1);
            startSmsNotificationModel.setSmsServiceActivationNumber(portfolioModel.getAccessCode());
            long lastId = startSmsNotificationService.getLastId();
            utility.logToCDR(cdrFormat(startSmsNotificationModel), "startSmsNotification");
            long insertId = startSmsNotificationService.addStartSmsNotification(startSmsNotificationModel);
            if (insertId > lastId) {
                utility.showMessage("StartNotification info added in smsNotification tbl");
                response = utility.createResponse(200, "Success", "Data inserted for startSmsNotification");
            } else {
                response = utility.createResponse(500, "Error", "Data not inserted for startSmsNotification");
            }
        } else {
            response = utility.createResponse(500, "Error", "API calling error for StartSmsNotification");
        }
        utility.logAdd(KeyWord.PRODUCTION, "startSmsNotification", client, interfaceName, logReq, logRes, trvlTime, String.valueOf(response.getCode()));
        return response;
    }

     /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [jaxbContext(String),defaultUri(String),value(String)]
    *  Workflow ->
    *  1. Converting [value] to JSONObject.
    *  2. Making POJO classes.
    *  3. Sent response to SOAPClient for un/marshalling.
    *  4. Typecasting response  to StopSmsNotificationResponse and return.
    * Content Number -> [2.4 stopSmsNotification]
    * Base URL -> http://ip:port/SmsNotificationManagerService/services/SmsNotificationManager
    * Namespace -> http://www.csapi.org/schema/parlayx/sms/notification_manager/v2_3/local
    * Local Part -> stopSmsNotification
    * Impact -> It stops subscription for App to receive MO SMS on Notify Mode
    *
    * */

    public Response stopSmsNotification(String jaxbContext, String defaultUri, String value) throws Exception {
        StringBuffer cdr = new StringBuffer();
        Response response = new Response();
        JSONObject jsonObject = new JSONObject(value);
        StopSmsNotification request = new StopSmsNotification();
        PortfolioModel portfolioModel = portfolioService.getPortfolioModelByServiceId(jsonObject.optString("serviceId"));
        if (portfolioModel == null) {
            response = utility.createResponse(500, "Error", "Portfolio not found in Portfolio tbl for this ServiceId : " + jsonObject.optString("serviceId"));
            return response;
        }
        StartSmsNotificationModel dbsm = startSmsNotificationService.findStartSmsNotificationModelByPfm(portfolioModel);
        if (dbsm != null && dbsm.getStatus() == 1) {
            request.setCorrelator(dbsm.getCorrelator());
        } else {
            return utility.createResponse(500, "Not found", "No correlator key found in SmsNotification tbl for this ServiceId : " + jsonObject.optString("serviceId"));
        }
        RequestSOAPHeader header = new RequestSOAPHeader();
        String timeStamp = utility.getTimeStamp();
        header.setSpId(portfolioModel.getSpId());
        header.setSpPassword(utility.convertMD5(portfolioModel.getSpId(), headerService.getValue("SP_PASSWORD"), timeStamp));
        header.setServiceId(portfolioModel.getServiceId());
        header.setTimeStamp(timeStamp);
        long startTime = System.currentTimeMillis();
        StopSmsNotificationResponse result = (StopSmsNotificationResponse) soapClient.executeAsync(jaxbContext, defaultUri, request, header);
        String trvlTime = utility.getTrvlTime(startTime, System.currentTimeMillis());
        String logReq = utility.convertPOJOtoXml(request);
        String logRes = utility.convertPOJOtoXml(result);
        if (result != null) {
            dbsm.setStatus(0);
            utility.logToCDR(cdrFormat(dbsm), "stopSmsNotification");
            if (startSmsNotificationService.updateStartSmsNotification(dbsm) > 0) {
                utility.showMessage("StopSMSNotification info added in smsNotification table");
                response = utility.createResponse(200, "Success", "Data updated for stopSmsNotification");
            } else {
                response = utility.createResponse(500, "Error", "Data not updated for stopSmsNotification");
            }
        } else {
            response = utility.createResponse(500, "Error", "API calling error for stopSmsNotification");

        }
        utility.logAdd(KeyWord.PRODUCTION, "stopSmsNotification", client, "stopSmsNotification", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
        return response;
    }

      /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [jaxbContext(String),defaultUri(String),value(String)]
    *  Workflow ->
    *  1. Converting [value] to JSONObject.
    *  2. Making POJO classes.
    *  3. Sent response to SOAPClient for un/marshalling.
    *  4. Typecasting response  to ChargeAmountResponse and return.
    * Content Number -> [2.2 chargeAmount]
    * Base URL -> http://IP:Port/AmountChargingService/services/AmountCharging
    * Namespace -> http://www.csapi.org/schema/parlayx/payment/amount_charging/v2_1/local
    * Local Part -> chargeAmount
    * Impact -> It invokes the chargeAmount API provided by the SDP to deduct by fee from users' accounts.
    *
    * */

    public Response chargeAmountResponse(String jaxbContext, String defaultUri, String value) throws Exception {
        StringBuffer cdr = new StringBuffer();
        Response response = new Response();
        JSONObject jsonObject = new JSONObject(value);
        String timeStamp = utility.getTimeStamp();

        String serviceNmae="ClubG";
        String type="OnDemand";
        PortfolioModel portfolioModel=portfolioService.findPortfolioModelOnDemand(serviceNmae,type);


        if (portfolioModel == null) {
            utility.showMessage("Portfolio not found");
            response = utility.createResponse(500, utility.ERROR, utility.NOTCHARGE);
            return response;
        }
        ChargeAmount chargeAmount = new ChargeAmount();
        sdp.Xsd.Client.Amount.Charge charge = new Charge();
        charge.setDescription("Charging for " + portfolioModel.getProductName());
        charge.setCurrency("RAND");
        charge.setAmount(String.valueOf(portfolioModel.getRentFee()));

        chargeAmount.setCharge(charge);
        String referenceCode = utility.generateReference();
        chargeAmount.setReferenceCode("Gtech" + timeStamp);
        chargeAmount.setEndUserIdentifier(jsonObject.optString("endUserIdentifier"));
        RequestSOAPHeader header = new RequestSOAPHeader();
        header.setSpId(portfolioModel.getSpId());

        header.setSpPassword(utility.convertMD5(portfolioModel.getSpId(), headerService.getValue("SP_PASSWORD"), timeStamp));
        header.setServiceId(portfolioModel.getServiceId().trim());
        header.setTimeStamp(timeStamp);
        header.setOA(jsonObject.optString("endUserIdentifier"));
        header.setFA(jsonObject.optString("endUserIdentifier"));
        header.setToken("");
        long startTime = System.currentTimeMillis();
        ChargeAmountResponse result = (ChargeAmountResponse) soapClient.executeAsync(jaxbContext, defaultUri, chargeAmount, header);
        String trvlTime = utility.getTrvlTime(startTime, System.currentTimeMillis());
        String logReq = utility.convertPOJOtoXml(chargeAmount);
        String logRes = utility.convertPOJOtoXml(result);
        if (result != null) {
            AmountModel chargeAmountModel = new AmountModel();
            chargeAmountModel.setPortfolioModel(portfolioModel);
            BaseModel baseModel = baseService.isBaseExists(jsonObject.optString("endUserIdentifier"));
            if (baseModel == null) {
                baseModel = new BaseModel();
                baseModel.setMdn(jsonObject.optString("endUserIdentifier"));
                baseModel.setBaseTypeModel(baseTypeService.getBaseTypeByCode(0));
                baseModel.setId(baseService.addBase(baseModel));
            }
            chargeAmountModel.setBaseModel(baseModel);
            chargeAmountModel.setAmount(String.valueOf(portfolioModel.getSubCharge()));
            chargeAmountModel.setCode("");
            chargeAmountModel.setCurrency("BDT");
            chargeAmountModel.setDescription("Charging for " + portfolioModel.getProductName());
            chargeAmountModel.setReferenceCode(referenceCode);
            chargeAmountModel.setStatus("Charged");
            long lastId = chargeAmountService.getLastId();
            utility.logToCDR(cdrFormat(chargeAmountModel), "chargeAmount");
            long insertId = chargeAmountService.addChargeAmount(chargeAmountModel);
            if (insertId > lastId) {
                utility.showMessage("Charge Amount added in chargAmount table for productId" + portfolioModel.getProductId() );
                response = utility.createResponse(200, utility.SUCCESS, utility.CHARGE);
            } else {
                utility.showMessage("Not charge , Product Id : " + portfolioModel.getProductId());
                response = utility.createResponse(500, utility.ERROR, utility.NOTCHARGE);
            }
        } else {
            utility.showMessage("Not charge , Api calling error");
            response = utility.createResponse(500, utility.ERROR, utility.NOTCHARGE);
        }
        utility.logAdd(KeyWord.PRODUCTION, "chargeAmount", client, "chargeAmount", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
        return response;
    }


     /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [jaxbContext(String),defaultUri(String),value(String)]
    *  Workflow ->
    *  1. Converting [value] to JSONObject.
    *  2. Making POJO classes.
    *  3. Sent response to SOAPClient for un/marshalling.
    *  4. Typecasting response  to  RefundAmountResponse and return.
    * Content Number -> [3.2 refundAmount]
    * Base URL -> http://IP:Port/AmountChargingService/services/AmountCharging
    * Namespace -> http://www.csapi.org/schema/parlayx/payment/amount_charging/v2_1/local
    * Local Part -> refundAmount
    * Impact -> It  invokes the refundAmount API provided by the SDP to refund by fees to users when charging fails.
    *
    * */


    public Response refundAmountResponse(String jaxbContext, String defaultUri, String value) throws Exception {
        Response response = new Response();
        StringBuffer cdr = new StringBuffer();
        JSONObject jsonObject = new JSONObject(value);
        PortfolioModel portfolioModel = portfolioService.getPortfolioModelByProductId(jsonObject.optString("productId"));
        if (portfolioModel == null) {
            utility.showMessage("Portfolio not found in db for this Product => " + jsonObject.optString("productId"));
            response = utility.createResponse(500, "Error", "Portfolio not found in portfolio tbl for this Product : " + jsonObject.optString("productId"));
            return response;

        }
        RefundAmount refundAmount = new RefundAmount();
        Charge charge = new Charge();
        charge.setDescription("Charging for " + portfolioModel.getProductName());
        charge.setCurrency("BDT");
        charge.setAmount(String.valueOf(portfolioModel.getSubCharge()));
        refundAmount.setCharge(charge);
        String referenceCode = utility.generateReference();
        refundAmount.setReferenceCode(referenceCode);
        refundAmount.setEndUserIdentifier(jsonObject.optString("endUserIdentifier").substring(4));
        RequestSOAPHeader header = new RequestSOAPHeader();
        header.setSpId(portfolioModel.getSpId());
        String timeStamp = utility.getTimeStamp();
        header.setSpPassword(utility.convertMD5(portfolioModel.getSpId(), headerService.getValue("SP_PASSWORD"), timeStamp));
        header.setServiceId(portfolioModel.getServiceId());
        header.setTimeStamp(timeStamp);
        header.setOA(jsonObject.optString("endUserIdentifier"));
        header.setFA(jsonObject.optString("endUserIdentifier"));
        header.setToken("");
        long startTime = System.currentTimeMillis();
        RefundAmountResponse result = (RefundAmountResponse) soapClient.executeAsync(jaxbContext, defaultUri, refundAmount, header);
        String trvlTime = utility.getTrvlTime(startTime, System.currentTimeMillis());
        String logReq = utility.convertPOJOtoXml(refundAmount);
        String logRes = utility.convertPOJOtoXml(result);
        if (result != null) {
            AmountModel chargeAmountModel = new AmountModel();
            chargeAmountModel.setPortfolioModel(portfolioModel);
            BaseModel baseModel = baseService.isBaseExists(jsonObject.optString("endUserIdentifier").substring(4));
            if (baseModel == null) {
                baseModel = new BaseModel();
                baseModel.setMdn(jsonObject.optString("endUserIdentifier").substring(4));
                baseModel.setBaseTypeModel(baseTypeService.getBaseTypeByCode(0));
                baseModel.setId(baseService.addBase(baseModel));
            }
            chargeAmountModel.setBaseModel(baseModel);
            chargeAmountModel.setAmount(String.valueOf(portfolioModel.getSubCharge()));
            chargeAmountModel.setCode("");
            chargeAmountModel.setCurrency("BDT");
            chargeAmountModel.setDescription("Charging for " + portfolioModel.getProductName());
            chargeAmountModel.setReferenceCode(referenceCode);
            chargeAmountModel.setStatus("Refunded");
            long lastId = chargeAmountService.getLastId();
            utility.logToCDR(cdrFormat(chargeAmountModel), "refundAmount");
            long insertId = chargeAmountService.addChargeAmount(chargeAmountModel);
            if (insertId > lastId) {
                utility.showMessage("refundAmount info added in chargeAmount table");
                response = utility.createResponse(200, "Success", "Data inserted for refundAmount");
            } else {
                response = utility.createResponse(500, "Error", "Data not inserted for refundAmount");
            }
        } else {
            response = utility.createResponse(500, "Error", "API calling error for refundAmount");
        }
        utility.logAdd(KeyWord.PRODUCTION, "refundAmount", client, "refundAmount", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
        return response;
    }


     /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [jaxbContext(String),defaultUri(String),value(String)]
    *  Workflow ->
    *  1. Converting [value] to JSONObject.
    *  2. Making POJO classes.
    *  3. Sent response to SOAPClient for un/marshalling.
    *  4. Typecasting response  to ChargeVolumeResponse and return.
    * Content Number -> [2.3 chargeVolume]
    * Base URL -> http://IP:Port/VolumeChargingService/services/VolumeCharging
    * Namespace -> http://www.csapi.org/schema/parlayx/payment/volume_charging/v2_1/local
    * Local Part -> chargeVolume
    * Impact -> It invokes the chargeVolume API provided by the SDP to deduct by volume from users' accounts.
    *
    * */


    public Response chargeVolumeResponse(String jaxbContext, String defaultUri, String value) throws Exception {
        Response response = new Response();
        StringBuffer cdr = new StringBuffer();
        JSONObject jsonObject = new JSONObject(value);
        PortfolioModel portfolioModel = portfolioService.getPortfolioModelByProductId(jsonObject.optString("productId"));
        if (portfolioModel == null) {
            utility.showMessage("Portfolio not found in porfolio tbl for this Product => " + jsonObject.optString("productId"));
            response = utility.createResponse(500, "Error", "Portfolio not found in portfolio tbl for this Product : " + jsonObject.optString("productId"));
            return response;
        }
        String endMobileUser = jsonObject.optString("endUserIdentifier").substring(4);
        ChargeVolume chargeVolume = new ChargeVolume();
        chargeVolume.setEndUserIdentifier(endMobileUser);
        chargeVolume.setVolume(jsonObject.optString("volume"));
        chargeVolume.setBillingText("Volume billing for " + portfolioModel.getProductName());
        String referenceCode = utility.generateReference();
        chargeVolume.setReferenceCode(referenceCode);
        String currentTimestamp = utility.getTimeStamp();
        RequestSOAPHeader header = new RequestSOAPHeader();
        header.setSpId(portfolioModel.getSpId());
        header.setSpPassword(utility.convertMD5(portfolioModel.getSpId(), headerService.getValue("SP_PASSWORD"), currentTimestamp));
        header.setServiceId(portfolioModel.getServiceId());
        header.setTimeStamp(currentTimestamp);
        header.setOA(endMobileUser);
        header.setFA(endMobileUser);
        long startTime = System.currentTimeMillis();
        ChargeVolumeResponse result = (ChargeVolumeResponse) soapClient.executeAsync(jaxbContext, defaultUri, chargeVolume, header);
        String trvlTime = utility.getTrvlTime(startTime, System.currentTimeMillis());
        String logReq = utility.convertPOJOtoXml(chargeVolume);
        String logRes = utility.convertPOJOtoXml(result);
        if (result != null) {
            VolumeModel volumeModel = new VolumeModel();
            volumeModel.setPortfolioModel(portfolioModel);
            BaseModel baseModel = baseService.isBaseExists(endMobileUser);
            if (baseModel == null) {
                baseModel = new BaseModel();
                baseModel.setMdn(endMobileUser);
                baseModel.setBaseTypeModel(baseTypeService.getBaseTypeByCode(0));
                baseModel.setId(baseService.addBase(baseModel));
            }
            volumeModel.setBaseModel(baseModel);
            volumeModel.setVolume(jsonObject.optString("volume"));
            volumeModel.setBillingText("Volume billing for " + portfolioModel.getProductName());
            volumeModel.setReferenceCode(referenceCode);
            volumeModel.setStatus("Charged");
            long lastId = volumeService.getLastId();
            utility.logToCDR(cdrFormat(volumeModel), "ChargeVolume");
            long insertId = volumeService.chargeVolume(volumeModel);
            if (insertId > lastId) {
                utility.showMessage("chargeVolume info added in chargeVolume tbl");
                response = utility.createResponse(200, "Success", "Data inserted for ChargeVolume");
            } else {
                response = utility.createResponse(500, "Error", "Data not inserted for ChargeVolume");
            }
        } else {
            response = utility.createResponse(500, "Error", "API calling error for ChargeVolume");
        }
        utility.logAdd(KeyWord.PRODUCTION, "chargeVolume", client, "chargeVolume", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
        return response;
    }



     /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [jaxbContext(String),defaultUri(String),value(String)]
    *  Workflow ->
    *  1. Converting [value] to JSONObject.
    *  2. Making POJO classes.
    *  3. Sent response to SOAPClient for un/marshalling.
    *  4. Typecasting response  to   RefundVolumeResponse and return.
    * Content Number -> [3.3 refundVolume]
    * Base URL -> http://IP:Port/VolumeChargingService/services/VolumeCharging
    * Namespace -> http://www.csapi.org/schema/parlayx/payment/volume_charging/v2_1/local
    * Local Part ->refundVolume
    * Impact ->  It sends a refundVolumeRequest message to the SDP to refund fees to users by volume.
    *
    * */


    public Response refundVolumeResponse(String jaxbContext, String defaultUri, String value) throws Exception {
        Response response = new Response();
        StringBuffer cdr = new StringBuffer();
        JSONObject jsonObject = new JSONObject(value);
        PortfolioModel portfolioModel = portfolioService.getPortfolioModelByProductId(jsonObject.optString("productId"));
        if (portfolioModel == null) {
            utility.showMessage("Portfolio not found in db for this Product => " + jsonObject.optString("productId"));
            response = utility.createResponse(500, "Error", "Portfolio not found in portfolio tbl for this Product : " + jsonObject.optString("productId"));
            return response;
        }
        String endMobileUser = jsonObject.optString("endUserIdentifier").substring(4);
        RefundVolume refundVolume = new RefundVolume();
        refundVolume.setEndUserIdentifier(endMobileUser);
        refundVolume.setVolume(jsonObject.optString("volume"));
        refundVolume.setBillingText("Volume billing for " + portfolioModel.getProductName());
        String referenceCode = utility.generateReference();
        refundVolume.setReferenceCode(referenceCode);
        String currentTimestamp = utility.getTimeStamp();
        RequestSOAPHeader header = new RequestSOAPHeader();
        header.setSpId(portfolioModel.getSpId());
        header.setSpPassword(utility.convertMD5(portfolioModel.getSpId(), headerService.getValue("SP_PASSWORD"), currentTimestamp));
        header.setServiceId(portfolioModel.getServiceId());
        header.setTimeStamp(currentTimestamp);
        header.setOA(endMobileUser);
        header.setFA(endMobileUser);
        long startTime = System.currentTimeMillis();
        RefundVolumeResponse result = (RefundVolumeResponse) soapClient.executeAsync(jaxbContext, defaultUri, refundVolume, header);
        String trvlTime = utility.getTrvlTime(startTime, System.currentTimeMillis());
        String logReq = utility.convertPOJOtoXml(refundVolume);
        String logRes = utility.convertPOJOtoXml(result);
        if (result != null) {
            VolumeModel volumeModel = new VolumeModel();
            volumeModel.setPortfolioModel(portfolioModel);
            BaseModel baseModel = baseService.isBaseExists(endMobileUser);
            if (baseModel == null) {
                baseModel = new BaseModel();
                baseModel.setMdn(endMobileUser);
                baseModel.setBaseTypeModel(baseTypeService.getBaseTypeByCode(0));
                baseModel.setId(baseService.addBase(baseModel));
            }
            volumeModel.setBaseModel(baseModel);
            volumeModel.setVolume(jsonObject.optString("volume"));
            volumeModel.setBillingText("Volume billing for " + portfolioModel.getProductName());
            volumeModel.setReferenceCode(referenceCode);
            volumeModel.setStatus("Refunded");
            long lastId = volumeService.getLastId();
            utility.logToCDR(cdrFormat(volumeModel), "RefundVolume");
            long insertId = volumeService.chargeVolume(volumeModel);
            if (insertId > lastId) {
                utility.showMessage("refundVolume info added in chargeVolume table");
                response = utility.createResponse(200, "Success", "Data inserted for RefundVolume");
            } else {
                response = utility.createResponse(500, "Error", "Data is not inserted for RefundVolume");
            }
        } else {
            response = utility.createResponse(500, "Error", "API calling error for RefundVolume");
        }
        utility.logAdd(KeyWord.PRODUCTION, "refundVolume", client, "refundVolume", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
        return response;
    }


     /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [jaxbContext(String),defaultUri(String),value(String)]
    *  Workflow ->
    *  1. Converting [value] to JSONObject.
    *  2. Making POJO classes.
    *  3. Sent response to SOAPClient for un/marshalling.
    *  4. Typecasting response  to  ReserveAmountResponse and return.
    * Content Number -> [4.2 reserveAmount]
    * Base URL -> http://IP:Port/ReserveAmountChargingService/services/ReserveAmountCharging
    * Namespace -> http://www.csapi.org/schema/parlayx/payment/reserve_amount_charging/v2_1/local
    * Local Part ->reserveAmountReq
    * Impact ->  It sends a reserveAmountRequest message to the SDP to reserve amount for users.
    *
    * */


    public Response getReserveAmntRspnse(String jaxbContext, String defaultUri, String value) throws Exception {
        Response response = new Response();
        StringBuffer cdr = new StringBuffer();
        ReserveAmountResponse result = new ReserveAmountResponse();
        JSONObject jsonObject = new JSONObject(value);
        PortfolioModel portfolioModel = portfolioService.getPortfolioModelByProductId(jsonObject.optString("productId"));
        if (portfolioModel == null) {
            utility.showMessage("Portfolio not found in db for this Product => " + jsonObject.optString("productId"));
            response = utility.createResponse(500, "Error", "Portfolio not found in porfolio tbl for this Product : " + jsonObject.optString("productId"));
            return response;

        }
        String endMobileUser = jsonObject.optString("endUserIdentifier").substring(4);
        BaseModel baseModel = baseService.isBaseExists(endMobileUser);
        if (baseModel == null) {
            baseModel = new BaseModel();
            baseModel.setMdn(endMobileUser);
            baseModel.setBaseTypeModel(baseTypeService.getBaseTypeByCode(0));
            baseModel.setId(baseService.addBase(baseModel));
        }
        ReservationModel reservationModel = reservationService.getReservationModelByPortfolioAndBase(portfolioModel, baseModel);
        String trvlTime = "";
        String logReq = "";
        String logRes = "";
        if (reservationModel == null || !reservationModel.getStatus().equals("Reserved")) {
            ReserveAmount request = new ReserveAmount();
            sdp.Xsd.Client.Reserve.Charge charge = new sdp.Xsd.Client.Reserve.Charge();
            charge.setAmount(String.valueOf(portfolioModel.getSubCharge()));
            charge.setCode("XCODE");
            charge.setCurrency("BDT");
            charge.setDescription("Reserve charge for " + portfolioModel.getProductName());
            request.setCharge(charge);
            request.setEndUserIdentifier(endMobileUser);
            String timeStamp = utility.getTimeStamp();
            RequestSOAPHeader header = new RequestSOAPHeader();
            header.setSpId(portfolioModel.getSpId());
            header.setSpPassword(utility.convertMD5(portfolioModel.getSpId(), headerService.getValue("SP_PASSWORD"), timeStamp));
            header.setServiceId(portfolioModel.getServiceId());
            header.setTimeStamp(timeStamp);
            header.setOA(endMobileUser);
            header.setFA(endMobileUser);
            long startTime = System.currentTimeMillis();
            result = (ReserveAmountResponse) soapClient.executeAsync(jaxbContext, defaultUri, request, header);
            trvlTime = utility.getTrvlTime(startTime, System.currentTimeMillis());
            logReq = utility.convertPOJOtoXml(request);
            logRes = utility.convertPOJOtoXml(result);
            if (result != null) {
                ReservationModel model = new ReservationModel();
                model.setPortfolioModel(portfolioModel);
                model.setBaseModel(baseModel);
                model.setDescription("Reserve charge for " + portfolioModel.getProductName());
                model.setCode("XCODE");
                model.setCurrency("BDT");
                model.setAmount(String.valueOf(portfolioModel.getSubCharge()));
                model.setResult(result.getResult());
                model.setStatus("Reserved");
                long lastId = reservationService.getLastId();
                utility.logToCDR(cdrFormat(model), "reserveAmount");
                long insertId = reservationService.addReservation(model);
                if (insertId > lastId) {
                    utility.showMessage("Amount Reserved in reserve table");
                    response = utility.createResponse(200, "Success", "Data inserted for reserveAmount");

                } else {
                    response = utility.createResponse(500, "Error", "Data not inserted for reserveAmount");
                }
                utility.logAdd(KeyWord.PRODUCTION, "reserveAmount", client, "reserveAmountReq", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
            } else {
                response = utility.createResponse(500, "Error", "API calling error for reserveAmount");
                utility.logAdd(KeyWord.PRODUCTION, "reserveAmount", client, "reserveAmountReq", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
            }
        } else {
            utility.showMessage("Already reserved amount for this user");
            response = utility.createResponse(500, "Error", "Already reserved amount for this user");
        }
        return response;
    }


     /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [jaxbContext(String),defaultUri(String),value(String)]
    *  Workflow ->
    *  1. Converting [value] to JSONObject.
    *  2. Making POJO classes.
    *  3. Sent response to SOAPClient for un/marshalling.
    *  4. Typecasting response  to  ChargeReservationResponse and return.
    * Content Number -> [4.3 chargeReservation]
    * Base URL -> http://IP:Port/ReserveAmountChargingService/services/ReserveAmountCharging
    * Namespace -> http://www.csapi.org/schema/parlayx/payment/reserve_amount_charging/v2_1/local
    * Local Part ->chargeReservationReq
    * Impact ->  It sends a chargeReservationRequest message to the SDP to deduct fees from users' reserved amount.
    *
    * */


    public Response getChargeResrvtnRespnse(String jaxbContext, String defaultUri, String value) throws Exception {
        Response response = new Response();
        StringBuffer cdr = new StringBuffer();
        ChargeReservationResponse result = new ChargeReservationResponse();
        JSONObject jsonObject = new JSONObject(value);
        PortfolioModel portfolioModel = portfolioService.getPortfolioModelByProductId(jsonObject.optString("productId"));
        if (portfolioModel == null) {
            utility.showMessage("Portfolio not found in db for this Product => " + jsonObject.optString("productId"));
            response = utility.createResponse(500, "Error", "Portfolio not found in db for this Product : " + jsonObject.optString("productId"));
            return response;
        }
        String endMobileUser = jsonObject.optString("endUserIdentifier").substring(4);
        BaseModel baseModel = baseService.isBaseExists(endMobileUser);
        if (baseModel == null) {
            baseModel = new BaseModel();
            baseModel.setMdn(endMobileUser);
            baseModel.setBaseTypeModel(baseTypeService.getBaseTypeByCode(0));
            baseModel.setId(baseService.addBase(baseModel));
        }
        String trvlTime = "";
        String logReq = "";
        String logRes = "";
        ReservationModel reservationModel = reservationService.getReservationModelByPortfolioAndBase(portfolioModel, baseModel);
        if (reservationModel != null) {
            if (reservationModel.getStatus().equals("Reserved")) {
                String referenceCode = utility.generateReference();
                ChargeReservation request = new ChargeReservation();
                request.setReservationIdentifier(reservationModel.getResult());
                sdp.Xsd.Client.Reserve.Charge charge = new sdp.Xsd.Client.Reserve.Charge();
                charge.setAmount(String.valueOf(portfolioModel.getSubCharge()));
                charge.setCode("XCODE");
                charge.setCurrency("BDT");
                charge.setDescription("Reserve charge for " + portfolioModel.getProductName());
                request.setCharge(charge);
                request.setReferenceCode(referenceCode);
                String timeStamp = utility.getTimeStamp();
                RequestSOAPHeader header = new RequestSOAPHeader();
                header.setSpId(portfolioModel.getSpId());
                header.setSpPassword(utility.convertMD5(portfolioModel.getSpId(), headerService.getValue("SP_PASSWORD"), timeStamp));
                header.setServiceId(portfolioModel.getServiceId());
                header.setTimeStamp(timeStamp);
                header.setOA(endMobileUser);
                header.setFA(endMobileUser);
                long startTime = System.currentTimeMillis();
                result = (ChargeReservationResponse) soapClient.executeAsync(jaxbContext, defaultUri, request, header);
                trvlTime = utility.getTrvlTime(startTime, System.currentTimeMillis());
                logReq = utility.convertPOJOtoXml(request);
                logRes = utility.convertPOJOtoXml(result);
                if (result != null) {
                    ReservationModel model = new ReservationModel();
                    model.setPortfolioModel(portfolioModel);
                    model.setBaseModel(baseModel);
                    model.setDescription("Charged reserve amount for " + portfolioModel.getProductName());
                    model.setCode("XCODE");
                    model.setCurrency("BDT");
                    model.setAmount(String.valueOf(portfolioModel.getSubCharge()));
                    model.setReference(referenceCode);
                    model.setResult(reservationModel.getResult());
                    model.setStatus("Charged");
                    long lastId = reservationService.getLastId();
                    utility.logToCDR(cdrFormat(model), "chargeReservation");
                    long insertId = reservationService.addReservation(model);
                    if (insertId > lastId) {
                        utility.showMessage("Amount Charged for Reservation(reservation tbl) with => " + new GsonBuilder().create().toJson(model));
                        response = utility.createResponse(200, "Success", "Data inserted for chargeReservation");
                    } else {
                        response = utility.createResponse(500, "Error", "Data not inserted for chargeReservation");
                    }
                    utility.logAdd(KeyWord.PRODUCTION, "chargeReservation", client, "chargeReservationReq", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
                } else {
                    response = utility.createResponse(500, "Error", "API calling error for chargeReservation");
                    utility.logAdd(KeyWord.PRODUCTION, "chargeReservation", client, "chargeReservationReq", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
                }
            } else {
                utility.showMessage("Amount is already " + reservationModel.getStatus());
                response = utility.createResponse(500, "Error", "Amount is already " + reservationModel.getStatus());
            }
        } else {
            response = utility.createResponse(500, "Error", "First you have to reserve your amount");
        }

        return response;
    }



     /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [jaxbContext(String),defaultUri(String),value(String)]
    *  Workflow ->
    *  1. Converting [value] to JSONObject.
    *  2. Making POJO classes.
    *  3.  Sent response to SOAPClient for un/marshalling.
    *  4. Typecasting response  to  ReleaseReservationResponse and return.
    * Content Number -> [4.4 releaseReservation]
    * Base URL -> http://IP:Port/ReserveAmountChargingService/services/ReserveAmountCharging
    * Namespace -> http://www.csapi.org/schema/parlayx/payment/reserve_amount_charging/v2_1/local
    * Local Part ->releaseReservationReq
    * Impact ->  It sends a releaseReservationRequest message to the SDP to cancel users' reserved amount.
    *
    * */


    public Response getReleaseResrvRspnse(String jaxbContext, String defaultUri, String value) throws Exception {
        Response response = new Response();
        StringBuffer cdr = new StringBuffer();
        ReleaseReservationResponse result = new ReleaseReservationResponse();
        JSONObject jsonObject = new JSONObject(value);
        PortfolioModel portfolioModel = portfolioService.getPortfolioModelByProductId(jsonObject.optString("productId"));
        if (portfolioModel == null) {
            utility.showMessage("Portfolio not found in db for this Product => " + jsonObject.optString("productId"));
            response = utility.createResponse(500, "Error", "Portfolio not found in db for this Product : " + jsonObject.optString("productId"));
            return response;
        }
        String endMobileUser = jsonObject.optString("endUserIdentifier").substring(4);
        BaseModel baseModel = baseService.isBaseExists(endMobileUser);
        if (baseModel == null) {
            baseModel = new BaseModel();
            baseModel.setMdn(endMobileUser);
            baseModel.setBaseTypeModel(baseTypeService.getBaseTypeByCode(0));
            baseModel.setId(baseService.addBase(baseModel));
        }
        String trvlTime = "";
        String logReq = "";
        String logRes = "";
        ReservationModel reservationModel = reservationService.getReservationModelByPortfolioAndBase(portfolioModel, baseModel);
        if (reservationModel != null) {
            if (reservationModel.getStatus().equals("Reserved")) {
                ReleaseReservation request = new ReleaseReservation();
                request.setReservationIdentifier(reservationModel.getResult());
                String timeStamp = utility.getTimeStamp();
                RequestSOAPHeader header = new RequestSOAPHeader();
                header.setSpId(portfolioModel.getSpId());
                header.setSpPassword(utility.convertMD5(portfolioModel.getSpId(), headerService.getValue("SP_PASSWORD"), timeStamp));
                header.setServiceId(portfolioModel.getServiceId());
                header.setTimeStamp(timeStamp);
                header.setOA(endMobileUser);
                header.setFA(endMobileUser);
                long startTime = System.currentTimeMillis();
                result = (ReleaseReservationResponse) soapClient.executeAsync(jaxbContext, defaultUri, request, header);
                trvlTime = utility.getTrvlTime(startTime, System.currentTimeMillis());
                logReq = utility.convertPOJOtoXml(request);
                logRes = utility.convertPOJOtoXml(result);
                if (result != null) {
                    ReservationModel model = new ReservationModel();
                    model.setPortfolioModel(portfolioModel);
                    model.setBaseModel(baseModel);
                    model.setDescription("Released reserve amount for " + portfolioModel.getProductName());
                    model.setCode("XCODE");
                    model.setCurrency("BDT");
                    model.setAmount(String.valueOf(portfolioModel.getSubCharge()));
                    model.setReference(reservationModel.getReference());
                    model.setResult(reservationModel.getResult());
                    model.setStatus("Released");
                    long lastId = reservationService.getLastId();
                    utility.logToCDR(cdrFormat(model), "releaseReservation");
                    long insertId = reservationService.addReservation(model);
                    if (insertId > lastId) {
                        utility.showMessage("Amount Released for Reservation (reservation tbl) with => " + new GsonBuilder().create().toJson(model));
                        response = utility.createResponse(200, "Success", "Data inserted for releaseReservation");
                    } else {
                        response = utility.createResponse(500, "Error", "Data not inserted for releaseReservation");
                    }
                    utility.logAdd(KeyWord.PRODUCTION, "releaseReservation", client, "releaseReservationReq", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
                } else {
                    response = utility.createResponse(500, "Error", "API calling error for releaseReservation");
                    utility.logAdd(KeyWord.PRODUCTION, "releaseReservation", client, "releaseReservationReq", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
                }
            } else {
                utility.showMessage("Amount is already " + reservationModel.getStatus());
                response = utility.createResponse(500, "Error", "Amount is already " + reservationModel.getStatus());
            }
        } else {
            response = utility.createResponse(500, "Error", "amount not been reserved for this user : " + endMobileUser);
            utility.logAdd(KeyWord.PRODUCTION, "releaseReservation", client, "releaseReservationReq", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
        }
        return response;
    }


       /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [jaxbContext(String),defaultUri(String),value(String)]
    *  Workflow ->
    *  1. Converting [value] to JSONObject.
    *  2. Making POJO classes.
    *  3. Sent response to SOAPClient for un/marshalling
    *  4. Typecasting response  to  SubscribeProductResponse and return.
    * Content Number -> [2.2 subscribeProduct]
    * Base URL -> http://IP:Port/SubscribeManageService/services/SubscribeManage
    * Namespace ->http://www.csapi.org/schema/parlayx/subscribe/manage/v1_0/local
    * Local Part ->subscribeProductReq
    * Impact ->  It sends a subscribeProductRequest message to the SDP to subscribe to a service.
    *
    * */


    public Response subscribeProductResponse(String jaxbContext, String defaultUri, String value) throws Exception {
        Response response = new Response();
        JSONObject jsonObject = new JSONObject(value);
        String userId = jsonObject.optString("id");
        String productId = jsonObject.optString("productID");
        BaseModel baseModel = baseService.isBaseExists(userId);
        SyncRelation syncRelation = new SyncRelation();
        int threadTime = Integer.parseInt(headerService.getValue(utility.THREADTIME));
        if (baseModel == null) {
            baseModel = new BaseModel();
            baseModel.setMdn(userId);
            BaseTypeModel baseTypeModel = baseTypeService.getBaseTypeByCode(0);
            baseModel.setBaseTypeModel(baseTypeModel);
            long lastId = baseService.getLastId();
            long insertId = baseService.addBase(baseModel);
            if (insertId > lastId) {
                baseModel = new BaseModel(insertId);
            }
        }

        PortfolioModel portfolio = portfolioService.getPortfolioModelByProductId(productId);
        if (portfolio == null) {
            utility.showMessage(productId + "portfolio not found " + productId);
            response = utility.createResponse(500, utility.ERROR,utility.NOTSUBSCRIBE);
            return response;
        }
        String serviceId = portfolio.getServiceId();
        //Making XML SOAP BODY REQUEST
        SubscribeProductRequest request = new SubscribeProductRequest();
        SubscribeProductReq subscribeProductReq = new SubscribeProductReq();
        User user = new User();
        Info info = new Info();
        user.setID(userId);
        user.setType("0");
        info.setProductID(productId.trim());
        info.setOperCode("zh");
        info.setIsAutoExtend("0");
        info.setChannelID("1");
        subscribeProductReq.setUserID(user);
        subscribeProductReq.setSubInfo(info);
        request.setSubscribeProductReq(subscribeProductReq);

        //Making XML REQUEST HEADER
        RequestSOAPHeader header = new RequestSOAPHeader();
        String timeStamp = utility.getTimeStamp();
        header.setSpId(portfolio.getSpId().trim());
        header.setSpPassword(utility.convertMD5(portfolio.getSpId(), headerService.getValue("SP_PASSWORD"), timeStamp));
        header.setTimeStamp(timeStamp);

        long startTime = System.currentTimeMillis();
        SubscribeProductResponse result = (SubscribeProductResponse) soapClient.executeAsync(jaxbContext, defaultUri, request, header);
        //test
//        SubscribeProductResponse result = new SubscribeProductResponse();
//        SubscribeProductRsp subscribeProductRsp = new SubscribeProductRsp();
//        subscribeProductRsp.setResult("223");
//        result.setSubscribeProductRsp(subscribeProductRsp);
        //
        String trvlTime = utility.getTrvlTime(startTime, System.currentTimeMillis());
        String logReq = utility.convertPOJOtoXml(request);
        String logRes = utility.convertPOJOtoXml(result);
        SubscribeProductModel subscribeProductModel = new SubscribeProductModel();
        if (result != null) {
            if (result.getSubscribeProductRsp().getResultDescription() == null && result.getSubscribeProductRsp().getResult() != null) {
                Thread.currentThread().sleep(threadTime*1000);
                syncRelation = syncRelationService.findSynRelationModelByService(baseModel,serviceId);
                if(syncRelation != null) {
                    if(!syncRelation.getUpdateDesc().equalsIgnoreCase(utility.SYNCSUBSCRIBE)) {
                        utility.showMessage("User not been Subscribe after 5s!");
                        response = utility.createResponse(500, utility.ERROR, utility.NOTSUBSCRIBE);
                        utility.logAdd(KeyWord.PRODUCTION, "SubscribeProduct", client, "subscribeProductReq", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
                        return response;
                    }
                } else {
                    utility.showMessage("no sync data found");
                    response = utility.createResponse(500, utility.ERROR, utility.NOTSUBSCRIBE);
                    utility.logAdd(KeyWord.PRODUCTION, "SubscribeProduct", client, "subscribeProductReq", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
                    return response;
                }
                subscribeProductModel.setBaseModel(baseModel);
                subscribeProductModel.setBaseTypeModel(new BaseTypeModel(1));
                subscribeProductModel.setProductId(productId);
                subscribeProductModel.setChannelModel("1");
                subscribeProductModel.setResult("Successful");
                subscribeProductModel.setType(utility.SUBSCRIBE);
                subscribeProductModel.setOperCode("zh");
                subscribeProductModel.setIsAutoExtend("0");
                long lastId = subscribeProductService.getLastId();
                utility.logToCDR(cdrFormat(subscribeProductModel), "subscribeProduct");
                long insertId = subscribeProductService.addSubscribeProduct(subscribeProductModel);
                if (insertId > lastId) {
                    utility.showMessage("SubscribeProduct inserted in subscribeProduct tbl with : " + new GsonBuilder().create().toJson(value));
                    response = utility.createResponse(200, utility.SUCCESS, utility.SUBSCRIBE);

                } else {
                    utility.showMessage("SubscribeProduct not inserted those data =>" + new GsonBuilder().create().toJson(value));
                    response = utility.createResponse(200, utility.SUCCESS,utility.SUBSCRIBE);
                }

            } else {
                utility.showMessage(result.getSubscribeProductRsp().getResultDescription());
                response = utility.createResponse(500, utility.ERROR, utility.NOTSUBSCRIBE);
            }
        } else {
            utility.showMessage("Api calling error for subscribeProduct");
            response = utility.createResponse(500, utility.ERROR, utility.NOTSUBSCRIBE);
        }
        utility.logAdd(KeyWord.PRODUCTION, "SubscribeProduct", client, "subscribeProductReq", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
        return response;
    }


     /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [jaxbContext(String),defaultUri(String),value(String)]
    *  Workflow ->
    *  1. Converting [value] to JSONObject.
    *  2. Making POJO classes.
    *  3. Sent response to SOAPClient for un/marshalling
    *  4. Typecasting response  to  UnsubscribeProductResponse and return.
    * Content Number -> [3.2 unSubscribeProduct]
    * Base URL -> http://IP:Port/SubscribeManageService/services/SubscribeManage
    * Namespace ->http://www.csapi.org/schema/parlayx/subscribe/manage/v1_0/local
    * Local Part -> unSubscribeProductReq
    * Impact ->  It sends a unSubscribeProductRequest message to the SDP to unsubscribe from service.
    *
    * */


    public Response getUnsubscribeProductResponse(String jaxbContext, String defaultUri, String value) throws Exception {
        Response response = new Response();
        StringBuffer cdr = new StringBuffer();
        JSONObject jsonObject = new JSONObject(value);
        String userId = jsonObject.optString("id");
        String productId = jsonObject.optString("productID");

        BaseModel base = baseService.getId(userId);

        UnSubscribeProductRequest request = new UnSubscribeProductRequest();
        SubscribeProductReq unSubProReq = new SubscribeProductReq();
        User user = new User();
        Info info = new Info();
        //Parameter parameter = new Parameter();
        user.setID(userId);
        user.setType("0");
        PortfolioModel portfolio = portfolioService.getPortfolioModelByProductId(productId);
        if (portfolio == null) {
            utility.showMessage("portfolio not found in db for this product Id : " + productId);
            response = utility.createResponse(500, utility.ERROR, utility.NOTUNSUBSCRIBE);
            return response;
        }
        info.setProductID(productId.trim());
        info.setOperCode("zh");
        info.setIsAutoExtend("0");
        info.setChannelID("1");
//        KeyValue keyValue = new KeyValue();
//        if (jsonObject.optString("key") != null && jsonObject.optString("value") != null) {
//            keyValue.setKey(jsonObject.optString("key"));
//            keyValue.setValue(jsonObject.optString("value"));
//            parameter.setNamedParameters(keyValue);
//        } else {
//            utility.showMessage("key value not provided  : " + new GsonBuilder().create().toJson(value));
//            response = utility.createResponse(500, "Error", "key value not provided");
//            return response;
//        }
        //info.setExtensionInfo(parameter);
        unSubProReq.setUserID(user);
        unSubProReq.setSubInfo(info);
        request.setUnSubscribeProductReq(unSubProReq);
        RequestSOAPHeader header = new RequestSOAPHeader();
        String timeStamp = utility.getTimeStamp();
        header.setSpId(portfolio.getSpId().trim());
        header.setSpPassword(utility.convertMD5(portfolio.getSpId(), headerService.getValue("SP_PASSWORD"), timeStamp));
        header.setTimeStamp(timeStamp);
        header.setOA(userId);
        header.setFA(userId);
        long startTime = System.currentTimeMillis();
        UnSubscribeProductResponse result = (UnSubscribeProductResponse) soapClient.executeAsync(jaxbContext, defaultUri, request, header);
        String trvlTime = utility.getTrvlTime(startTime, System.currentTimeMillis());
        String logReq = utility.convertPOJOtoXml(request);
        String logRes = utility.convertPOJOtoXml(result);
        SubscribeProductModel subscribeProductModel = new SubscribeProductModel();
        if (result != null && result.getUnSubscribeProductRsp() != null && result.getUnSubscribeProductRsp().getResultDescription().equalsIgnoreCase("Success")) {
            //long baseId = baseService.addBase(base);
            subscribeProductModel.setBaseModel(base);
            subscribeProductModel.setBaseTypeModel(base.getBaseTypeModel());
            subscribeProductModel.setProductId(productId);
            subscribeProductModel.setChannelModel("1");
            subscribeProductModel.setResult("Successful");
            subscribeProductModel.setType(utility.UNSUBSCRIBE);
            subscribeProductModel.setOperCode("zh");
            subscribeProductModel.setIsAutoExtend("0");
            long lastId = subscribeProductService.getLastId();
            utility.logToCDR(cdrFormat(subscribeProductModel), "unSubscribeProduct");
            long insertId = subscribeProductService.addSubscribeProduct(subscribeProductModel);
            if (insertId > lastId) {
                utility.showMessage("UnSubscribeProduct inserted in subscribeProduct tble with : " + new GsonBuilder().create().toJson(value));
                response = utility.createResponse(200, utility.SUCCESS,utility.UNSUBSCRIBE);
            } else {
                utility.showMessage("UnSubscribeProduct not inserted those data :" + new GsonBuilder().create().toJson(value));
                response = utility.createResponse(200, utility.SUCCESS, utility.UNSUBSCRIBE);
            }

        } else if (result != null){
            utility.showMessage(result.getUnSubscribeProductRsp().getResultDescription());
            response = utility.createResponse(500, utility.ERROR, utility.NOTUNSUBSCRIBE);
        } else {
            utility.showMessage("Api calling error for UnsubscribeProduct");
            response = utility.createResponse(500, utility.ERROR, utility.NOTUNSUBSCRIBE);
        }
        utility.logAdd(KeyWord.PRODUCTION, "unSubscribeProduct", client, "unSubscribeProductReq", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
        return response;
    }

     /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [jaxbContext(String),defaultUri(String),value(String)]
    *  Workflow ->
    *  1. Converting [value] to JSONObject.
    *  2.Create a loop for get item value.
    *  3. Making POJO classes.
    *  4. Sent response to SOAPClient for un/marshalling
    *  5. Typecasting response  to  GetSubScriptionListRsp and return.
    * Content Number -> [4.2 getSubscriptionList]
    * Base URL -> http://IP:Port/SubScriptionService/services/SubScription
    * Namespace ->http://www.sdp.com/schema/subscription/v1_0/local
    * Local Part -> subscriptionListReq
    * Impact ->  It sends a getSubscriptionListRequest message to the SDP to obtain the subscription relationship list.
    *
    * */

    public Response getSubScriptnListResponse(String jaxbContext, String defaultUri, String value) throws Exception {
        StringBuffer cdr = new StringBuffer();
        Response response = new Response();
        JSONObject jsonObject = new JSONObject(value);
        GetSubScriptionListReq request = new GetSubScriptionListReq();
        sdp.Xsd.Client.SubscriptionList.User user = new sdp.Xsd.Client.SubscriptionList.User();
        String mdn = jsonObject.optString("id");
        BaseModel base = baseService.getId(mdn);
        if (base == null) {
            utility.showMessage("base not found in base tbl for this mdn : " + mdn.trim());
            response = utility.createResponse(500, "Error", "base not found in base tbl for this mdn : " + mdn.trim());
            return response;
        }
        long baseId = base.getId();
        BaseTypeModel baseTypeModel = base.getBaseTypeModel();
        String base_type = String.valueOf(baseTypeModel.getId());
        user.setID(mdn);
        user.setType(base_type);
        SubscribeProductModel subscribeProductModel = subscribeProductService.getByPortfolio(base);
        if (subscribeProductModel == null) {
            utility.showMessage("Subscriber not found in subscribeProduct tbl for this Base Id " + baseId);
            response = utility.createResponse(500, "Error", "Subscriber not found in subscribeProduct tbl for this Base Id : " + baseId);
            return response;
        }
        String product = subscribeProductModel.getProductId();
        PortfolioModel portfolioModel = portfolioService.getPortfolioModelByProductId(product);
        if (portfolioModel == null) {
            utility.showMessage("Portfolio not found in db for this Product => " + product);
            response = utility.createResponse(500, "Error", "Portfolio not found in db for this Product : " + product);
            return response;
        }
        request.setUserID(user);
        RequestSOAPHeader header = new RequestSOAPHeader();
        String timeStamp = utility.getTimeStamp();
        String sp = portfolioModel.getSpId();
        header.setSpId(sp);
        header.setSpPassword(utility.convertMD5(portfolioModel.getSpId(), headerService.getValue("SP_PASSWORD"), timeStamp));
        header.setTimeStamp(timeStamp);
        long startTime = System.currentTimeMillis();
        GetSubScriptionListRsp result = (GetSubScriptionListRsp) soapClient.executeAsync(jaxbContext, defaultUri, request, header);
        String trvlTime = utility.getTrvlTime(startTime, System.currentTimeMillis());
        String logReq = utility.convertPOJOtoXml(request);
        String logRes = utility.convertPOJOtoXml(result);
        if (result != null) {
            JSONObject jsonObject1 = new JSONObject(result);
            String value1 = jsonObject1.toString();
            String mdn1 = jsonObject.optString("id");
            BaseModel base1 = baseService.getId(mdn1);
            base.setSubscriptionList(value1);
            cdr.append(base.getId());
            cdr.append("|");
            cdr.append(base.getSubscriptionList());
            cdr.append("\n");
            utility.logToCDR(cdr.toString(), "subscriptionList");
            baseService.addBase(base);
            response = utility.createResponse(200, "Success", "SubscriptionList Data inserted in Base for MDN : " + mdn.trim());
        } else
            response = utility.createResponse(500, "Error", "API calling error for SubscriptionList");

        utility.logAdd(KeyWord.PRODUCTION, "subscriptionList", client, "subscriptionListReq", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
        return response;

    }


     /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [jaxbContext(String),defaultUri(String),value(String)]
    *  Workflow ->
    *  1. Converting [value] to JSONObject.
    *  2. Making POJO classes.
    *  3. Sent response to SOAPClient for un/marshalling
    *  4. Typecasting response  to  GetReceivedSmsResponse and return.
    * Content Number -> [2.5 getReceivedSms]
    * Base URL -> http://IP:Port/ReceiveSmsService/services/ReceiveSms
    * Namespace ->http://www.csapi.org/schema/parlayx/sms/receive/v2_2/local
    * Local Part -> getReceivedSms
    * Impact ->  It  sends a getReceivedSmsRequest message to the SDP to receive MO SMS messages.
    *
    * */


    public Response getReceivedSms(String jaxbContext, String defaultUri, String value) throws Exception {
        Response response = new Response();
        StringBuffer cdr = new StringBuffer();
        JSONObject jsonObject = new JSONObject(value);
        GetReceivedSms request = new GetReceivedSms();
        String service = jsonObject.optString("serviceId");
        PortfolioModel portfolio = portfolioService.getPortfolioModelByServiceId(service);
        if (portfolio == null) {
            utility.showMessage("Portfolio not found in db for this ServiceId => " + service);
            response = utility.createResponse(500, "Error", "Portfolio not found in db for this ServiceId : " + service);
            return response;

        }
        String registration = portfolio.getAccessCode();
        request.setRegistrationIdentifier(registration);
        RequestSOAPHeader header = new RequestSOAPHeader();
        String timeStamp = utility.getTimeStamp();
        header.setSpId(portfolio.getSpId());
        header.setSpPassword(utility.convertMD5(portfolio.getSpId(), headerService.getValue("SP_PASSWORD"), timeStamp));
        header.setServiceId(portfolio.getServiceId());
        header.setTimeStamp(timeStamp);
        long startTime = System.currentTimeMillis();
        GetReceivedSmsResponse result = (GetReceivedSmsResponse) soapClient.executeAsync(jaxbContext, defaultUri, request, header);
        String trvlTime = utility.getTrvlTime(startTime, System.currentTimeMillis());
        String logReq = utility.convertPOJOtoXml(request);
        String logRes = utility.convertPOJOtoXml(result);
        if (result != null) {
            NotifySms notifySms = new NotifySms();
            PortfolioModel portfolioModle = portfolioService.getPortfolioModelByServiceId(jsonObject.optString("serviceId"));
            notifySms.setPortfolioModel(portfolioModle);
            notifySms.setMessage(result.getResult().getMessage());
            BaseModel baseModel = baseService.isBaseExists(result.getResult().getSenderAddress().substring(4));
            if (baseModel == null) {
                baseModel = new BaseModel();
                baseModel.setMdn(result.getResult().getSenderAddress().substring(4));
                baseModel.setBaseTypeModel(baseTypeService.getBaseTypeByCode(0));
                baseModel.setId(baseService.addBase(baseModel));
            }
            notifySms.setBaseModel(baseModel);
            notifySms.setSmsServiceActivationNumber(result.getResult().getSmsServiceActivationNumber());
            notifySms.setDateTime(result.getResult().getDateTime());
            notifySms.setType("get");
            long lastId = notifySmsService.getLastId();
            utility.logToCDR(utility.cdrFormat(notifySms), "notifySms");
            long insertId = notifySmsService.addNotify(notifySms);
            if (insertId > lastId) {
                utility.showMessage("receivedSmsReception(get) info added in notifySms tbl inserted with => " + new GsonBuilder().create().toJson(notifySms));
                response = utility.createResponse(200, "Success", "Data inserted for ReceivedSmsReception(get)");
            } else {
                response = utility.createResponse(500, "Error", "Data not inserted for ReceivedSmsReception(get)");
            }
        } else {
            response = utility.createResponse(500, "Error", "API calling error for ReceivedSmsReception(get)");
        }

        utility.logAdd(KeyWord.PRODUCTION,"getReceivedSms", client, "getReceivedSms", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
        return response;
    }

      /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [jaxbContext(String),defaultUri(String),value(String)]
    *  Workflow ->
    *  1. Converting [value] to JSONObject.
    *  2. Making POJO classes.
    *  3. Sent response to SOAPClient for un/marshalling
    *  4. Typecasting response  to  SendSmsResponse and return.
    * Content Number -> [3.2 sendSms]
    * Base URL -> http://IP:Port/SendSmsService/services/SendSms
    * Namespace ->http://www.csapi.org/schema/parlayx/sms/send/v2_2/local
    * Local Part ->sendSmsReq
    * Impact ->  It sends sendSmsRequest messages to the SDP.
    *
    * */


    public Response getSendSmsResponse(String jaxbContext, String defaultUri, String value) throws Exception {
        Response response = new Response();
        StringBuffer cdr = new StringBuffer();
        JSONObject jsonObject = new JSONObject(value);
        Receipt receipt = new Receipt();
        receipt.setEndpoint(headerService.getValue("DELIEVRY_STATUS_ENDPOINT"));
        receipt.setInterfaceName(headerService.getValue("DELIVERY_STATUS_LOCAL_PART"));
        String correlator = utility.generateReference();
        receipt.setCorrelator(jsonObject.optString("correlator"));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        PortfolioModel portfolioModel = portfolioService.getPortfolioModelByServiceId(jsonObject.optString("serviceId"));
//        if (portfolioModel == null) {
//            utility.showMessage("Portfolio not found in db for this Product => " + jsonObject.optString("serviceId"));
//            response = utility.createResponse(500, "Error", "Portfolio not found in db for this Product : " + jsonObject.optString("serviceId"));
//            return response;
//        }
//        ContentModel contentModel = contentService.getContentByDateAndPortfolio(timestamp, portfolioModel);
        String smsInfoApi = headerService.getValue(jsonObject.optString("keyWord"));
        smsInfoApi = smsInfoApi+"mdn="+jsonObject.optString("addresses");
        utility.showMessage("Calling Sms Content API URL -> "+smsInfoApi);
        RestTemplate restTemplate = new RestTemplate();
        String smsApiResult = restTemplate.getForObject(smsInfoApi, String.class);
        JSONArray jsonArrayArray = new JSONArray(smsApiResult);
        String sms = "";
        for(Object object : jsonArrayArray) {
            JSONObject smsObject = (JSONObject)object;
            sms = smsObject.getString("text");
        }
        utility.showMessage(sms);

            SendSms request = new SendSms();
            request.setAddresses(jsonObject.optString("addresses"));
            request.setSenderName(jsonObject.optString("senderName"));
            request.setMessage(sms);
            request.setReceiptRequest(receipt);
            sdp.Model.SendSms.RequestSOAPHeader header = new sdp.Model.SendSms.RequestSOAPHeader();
            header.setSpId(jsonObject.optString("spId"));
            String timeValue = utility.getTimeStamp();
            header.setSpPassword(utility.convertMD5(jsonObject.optString("spId"), headerService.getValue("SP_PASSWORD"), timeValue));
            header.setServiceId(jsonObject.optString("serviceId"));
            header.setTimeStamp(timeValue);
            header.setOA(jsonObject.optString("addresses"));
            header.setFA(jsonObject.optString("addresses"));
            long startTime = System.currentTimeMillis();
            SendSmsResponse result = (SendSmsResponse) soapClient.executeAsync(jaxbContext, defaultUri, request, header);

            /* test
            SendSmsResponse result = new SendSmsResponse();
            result.setResult("3434343");
            */

            String trvlTime = utility.getTrvlTime(startTime, System.currentTimeMillis());
            String logReq = utility.convertPOJOtoXml(request);
            String logRes = utility.convertPOJOtoXml(result);
            utility.showMessage(logRes);
            if (result != null) {
                SmsLogModel smsLogModel = new SmsLogModel();
                smsLogModel.setPortfolioModel(portfolioModel);
                BaseModel baseModel = baseService.isBaseExists(jsonObject.optString("addresses"));
                if (baseModel == null) {
                    baseModel = new BaseModel();
                    baseModel.setMdn(jsonObject.optString("addresses"));
                    baseModel.setBaseTypeModel(baseTypeService.getBaseTypeByCode(0));
                    baseModel.setId(baseService.addBase(baseModel));
                }
                smsLogModel.setBaseModel(baseModel);
                smsLogModel.setCorrelator(jsonObject.optString("correlator"));
                smsLogModel.setEndpoint(headerService.getValue("DELIEVRY_STATUS_ENDPOINT"));
                smsLogModel.setInterfaceName(headerService.getValue("DELIVERY_STATUS_LOCAL_PART"));
                smsLogModel.setMessage(sms);
                smsLogModel.setRequestIdentifier(result.getResult());
                smsLogModel.setOperationMode("send sms");
                long lastId = smsLogService.getLastId();
                utility.logToCDR(utility.cdrFormat(smsLogModel), "smsLog");
                long insertId = smsLogService.addSmsLog(smsLogModel);
                if (insertId > lastId) {
                    utility.showMessage("SmsLog added in smslog tbl for SendSms with => " + new GsonBuilder().create().toJson(smsLogModel));
                    response = utility.createResponse(200, "Success", "Data inserted for SendSms");

                } else {
                    utility.showMessage("SmsLog not added");
                    response = utility.createResponse(500, "Error", "Data not inserted for SendSms");
                }
                utility.logAdd(KeyWord.PRODUCTION, "sendSms", client, "sendSmsRequest", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
            } else {
                response = utility.createResponse(500, "Error", "API calling error for SendSms");
                utility.logAdd(KeyWord.PRODUCTION, "sendSms", client, "sendSmsRequest", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
            }

        return response;
    }


   /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [jaxbContext(String),defaultUri(String),value(String)]
    *  Workflow ->
    *  1. Converting [value] to JSONObject.
    *  2. Making POJO classes.
    *  3. Sent response to SOAPClient for un/marshalling
    *  4. Typecasting response  to  GetSmsDeliveryStatusResponse and return.
    * Content Number -> [3.3 getSmsDeliveryStatus]
    * Base URL -> http://IP:Port/SendSmsService/services/SendSms
    * Namespace ->http://www.csapi.org/schema/parlayx/sms/send/v2_2/local
    * Local Part ->getSmsDeliveryStatusReq
    * Impact ->  It sends a getSmsDeliveryStatusRequest message to the SDP to obtain status reports.
    *
    * */

    public Response getSmsDeliveryStatusResponse(String jaxbContext, String defaultUri, String value) throws Exception {
        Response response = new Response();
        StringBuffer cdr = new StringBuffer();
        JSONObject jsonObject = new JSONObject(value);
        GetSmsDeliveryStatus request = new GetSmsDeliveryStatus();
        PortfolioModel portfolio = portfolioService.getPortfolioModelByServiceId(jsonObject.optString("serviceId"));
        if (portfolio == null) {
            utility.showMessage("Portfolio not found in db for this ServiceId => " + jsonObject.optString("serviceId"));
            response = utility.createResponse(500, "Error", "Portfolio not found in db for this serviceId : " + jsonObject.optString("serviceId"));
            return response;
        }
        BaseModel baseModel = baseService.isBaseExists(jsonObject.optString("endUserId").substring(4));
        if (baseModel == null) {
            baseModel = new BaseModel();
            baseModel.setMdn(jsonObject.optString("endUserId").substring(4));
            baseModel.setBaseTypeModel(baseTypeService.getBaseTypeByCode(0));
            baseModel.setId(baseService.addBase(baseModel));
        }
        SmsLogModel smsLogModel = smsLogService.getByPortfolioAndBase(portfolio, baseModel);
        if (smsLogModel == null) {
            utility.showMessage("sms status not been found in smslog db for particular user => " + jsonObject.optString("endUserId"));
            response = utility.createResponse(500, "Error", "sms not been send for this particular user : " + jsonObject.optString("endUserId"));
            return response;
        }
        request.setRequestIdentifier(smsLogModel.getRequestIdentifier());
        RequestSOAPHeader header = new RequestSOAPHeader();
        String timeStamp = utility.getTimeStamp();
        header.setSpId(portfolio.getSpId());
        header.setSpPassword(utility.convertMD5(portfolio.getSpId(), headerService.getValue("SP_PASSWORD"), timeStamp));
        header.setServiceId(portfolio.getServiceId());
        header.setTimeStamp(timeStamp);
        long startTime = System.currentTimeMillis();
        GetSmsDeliveryStatusResponse result = (GetSmsDeliveryStatusResponse) soapClient.executeAsync(jaxbContext, defaultUri, request, header);
        String trvlTime = utility.getTrvlTime(startTime, System.currentTimeMillis());
        String logReq = utility.convertPOJOtoXml(request);
        String logRes = utility.convertPOJOtoXml(result);
        if (result != null) {
            SmsLogModel smsLog = new SmsLogModel();
            smsLog.setPortfolioModel(smsLogModel.getPortfolioModel());
            smsLog.setDeliveryStatus(result.getResult().getDeliveryStatus());
            smsLog.setOperationMode("get");
            smsLog.setEndpoint(smsLogModel.getEndpoint());
            smsLog.setMessage(smsLogModel.getMessage());
            smsLog.setCorrelator(smsLogModel.getCorrelator());
            smsLog.setBaseModel(smsLogModel.getBaseModel());
            smsLog.setInterfaceName(smsLogModel.getInterfaceName());
            smsLog.setRequestIdentifier(smsLogModel.getRequestIdentifier());
            long lastId = smsLogService.getLastId();
            utility.logToCDR(utility.cdrFormat(smsLog), "smsLog");
            long insertId = smsLogService.addSmsLog(smsLog);
            if (insertId > lastId) {
                utility.showMessage("SmsLog added in smslog tbl for SmsDeliveryStatus(get) with" + new GsonBuilder().create().toJson(smsLog));
                response = utility.createResponse(200, "Success", "Data Inserted for SmsDeliveryStatus(get) ");

            } else {
                response = utility.createResponse(500, "Error", "Data not inserted for SmsDeliveryStatus(get)");
            }
        } else {
            response = utility.createResponse(500, "Error", "API calling error for SmsDeliveryStatus(get)");
        }
        utility.logAdd(KeyWord.PRODUCTION, "getSmsDeliveryStatus", client, "getSmsDeliveryStatusReq", logReq, logRes, trvlTime, String.valueOf(response.getCode()));
        return response;
    }

    private String cdrFormat(StartSmsNotificationModel ssnm) {
        StringBuffer cdr = new StringBuffer();
        cdr.append(ssnm.getCorrelator());
        cdr.append("|");
        cdr.append(ssnm.getEndpoint());
        cdr.append("|");
        cdr.append(ssnm.getCriteria());
        cdr.append("|");
        cdr.append(ssnm.getInterfaceName());
        cdr.append("|");
        if (ssnm.getPortfolioModel() == null)
            cdr.append("null");
        else
            cdr.append(ssnm.getPortfolioModel().getId());
        cdr.append("|");
        cdr.append(ssnm.getStatus());
        cdr.append("|");
        cdr.append(ssnm.getSmsServiceActivationNumber());
        cdr.append("\n");
        return cdr.toString();
    }

    private String cdrFormat(AmountModel amount) {
        StringBuffer cdr = new StringBuffer();
        cdr.append(amount.getDescription());
        cdr.append("|");
        cdr.append(amount.getCurrency());
        cdr.append("|");
        cdr.append(amount.getAmount());
        cdr.append("|");
        cdr.append(amount.getReferenceCode());
        cdr.append("|");
        if (amount.getPortfolioModel() == null)
            cdr.append("null");
        else
            cdr.append(amount.getPortfolioModel().getId());
        cdr.append("|");
        cdr.append(amount.getStatus());
        cdr.append("|");
        if (amount.getBaseModel() == null)
            cdr.append("null");
        else
            cdr.append(amount.getBaseModel().getId());
        cdr.append("|");
        cdr.append(amount.getCode());
        cdr.append("\n");
        return cdr.toString();
    }

    private String cdrFormat(VolumeModel volume) {
        StringBuffer cdr = new StringBuffer();
        cdr.append(volume.getVolume());
        cdr.append("|");
        cdr.append(volume.getBillingText());
        cdr.append("|");
        cdr.append(volume.getReferenceCode());
        cdr.append("|");
        if (volume.getPortfolioModel() == null)
            cdr.append("null");
        else cdr.append(volume.getPortfolioModel().getId());
        cdr.append("|");
        cdr.append(volume.getStatus());
        cdr.append("|");

        cdr.append(volume.getBaseModel().getId());
        cdr.append("\n");
        return cdr.toString();
    }

    private String cdrFormat(ReservationModel reserve) {
        StringBuffer cdr = new StringBuffer();
        cdr.append(reserve.getDescription());
        cdr.append("|");
        cdr.append(reserve.getCurrency());
        cdr.append("|");
        cdr.append(reserve.getAmount());
        cdr.append("|");
        if (reserve.getPortfolioModel() == null)
            cdr.append("null");
        else
            cdr.append(reserve.getPortfolioModel().getId());
        cdr.append("|");
        cdr.append(reserve.getStatus());
        cdr.append("|");
        cdr.append(reserve.getResult());
        cdr.append("|");
        if (reserve.getBaseModel() == null)
            cdr.append("null");
        else
            cdr.append(reserve.getBaseModel().getId());
        cdr.append("|");
        cdr.append(reserve.getReference());
        cdr.append("|");
        cdr.append(reserve.getCode());
        cdr.append("\n");
        return cdr.toString();
    }

    private String cdrFormat(SubscribeProductModel subscribe) {
        StringBuffer cdr = new StringBuffer();
        if (subscribe.getBaseModel() == null)
            cdr.append("null");
        else
            cdr.append(subscribe.getBaseModel().getId());
        cdr.append("|");
        cdr.append(subscribe.getProductId());
        cdr.append("\n");
        return cdr.toString();
    }


}
