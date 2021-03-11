package sdp.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import sdp.Config.ClientServiceConfig;
import sdp.Model.ApiModel;
import sdp.Model.BaseModel;
import sdp.Model.PortfolioModel;
import sdp.Model.SyncRelation;
import sdp.Service.*;
import sdp.Util.*;
import sdp.Util.ResponseStatus;
import sdp.Xsd.SubscribeProduct.SubscribeProductReq;
import sdp.Xsd.SubscribeProduct.UnSubscribeProductReq;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;


/**
 * Created by Atiq on 1/1/2018.
 */
@RestController
@RequestMapping(value = "/")
public class Subscription {

    @Autowired
    Utility utility;
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
    ObjectMapper mapper;
    @Autowired
    ChannelService channelService;
    @Autowired
    SyncRelationService syncRelationService;
    @Autowired
    PortfolioService portfolioService;
    @Autowired
    ApiService apiService;
    @Autowired
    BaseTypeService baseTypeService;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.httpServletRequest = request;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/url")
    public Response getUrl(HttpServletRequest request) {
        Response response = new Response();
        String craeted = Instant.now().toString();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss a z");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String nonce = dateFormat.format(date)+"00000";
        return response;
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

    @RequestMapping(method = RequestMethod.POST, value = "/{type}/subscription/{path}")
    public Response SubscribeProduct(HttpServletRequest request,@PathVariable("type") String type, @PathVariable("path") int path, @RequestBody String reqBody) {
        utility.printCallingUrl(this.httpServletRequest, new JSONObject(reqBody).toString());
        Response response = new Response();
        String auth = request.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        String productName = "";
        String productId = "";
        PortfolioModel portfolioModel = new PortfolioModel();
        SyncRelation syncRelation = new SyncRelation();
        if (auth.equals(authentication)) {
            SubscribeProductReq subscribeProductReq = new SubscribeProductReq();
            JSONObject jsonObject = new JSONObject(reqBody);
            subscribeProductReq.setID(jsonObject.optString("mdn"));
            switch(type){
                case "cg":
                    productName = "ClubG";
                    break;
                case "rg":
                    productName = "RadioG";
                    break;
                case "vg":
                    productName = "VideoG";
                    break;
                default:
                    utility.showMessage("service name path not vaild");
                    response = utility.createResponse(500, utility.ERROR, utility.NOTSUBSCRIBE);
                    return response;
            }
            switch (path) {
                case 1:
                    productName += " Daily";
                    portfolioModel = portfolioService.getPortfolioModelByProductName(productName);
                    break;
                case 7:
                    productName += " Weekly";
                    portfolioModel = portfolioService.getPortfolioModelByProductName(productName);
                    break;
                case 30:
                    productName += " Monthly";
                    portfolioModel = portfolioService.getPortfolioModelByProductName(productName);
                    break;
                default:
                    utility.showMessage("Request parameter of product plan not valid");
                    response = utility.createResponse(500, utility.ERROR, utility.NOTSUBSCRIBE);
                    return response;
            }

            if (portfolioModel != null && portfolioModel.getProductId() != null) {
                productId = portfolioModel.getProductId();
                subscribeProductReq.setProductID(productId);
            }else {
                utility.showMessage("ProductId not found");
                return utility.createResponse(500, utility.ERROR, utility.NOTSUBSCRIBE);
            }

   // following code snippet for checking subscribing same product name with different plan !!

            BaseModel baseModel = baseService.isBaseExists(jsonObject.optString("mdn"));
            String serviceId = portfolioModel.getServiceId();
            syncRelation = syncRelationService.findSynRelationModelByServiceAndBase(baseModel,serviceId);
            if(syncRelation != null) {
                if(syncRelation.getUpdateDesc().equalsIgnoreCase("Addition")) {
                    utility.showMessage("Already subscribed for " + portfolioModel.getProductName() );
                    return utility.createResponse(500, utility.ERROR, utility.NOTSUBSCRIBE);
                }
            }

            try {
                ApiModel apiModel = apiService.getApiModelByName(utility.SUBSCRIBEAPI);
                String subUri = utility.getTargetUri(apiModel);
                String subContext = apiModel.getJaxb();
                response = config.subscribeProductResponse(subContext, subUri, new GsonBuilder().create().toJson(subscribeProductReq));
                return response;
            } catch (Exception ex) {
                utility.showMessage(ex.getMessage() + " " + ex.toString());
                return utility.createResponse(500, utility.ERROR, utility.NOTSUBSCRIBE);
            }

        } else {
            utility.showMessage("Authentication Error");
            response = utility.createResponse(500, utility.ERROR, utility.AUTHENTICATION);
        }
        return response;
    }

    /**
     * Callback URL
     * ======================================================
     */

    @RequestMapping(method = RequestMethod.POST, value = "/getChargeUrl")
    public Response generateCallbackUrl(HttpServletRequest request, @RequestBody String body) {
        utility.printCallingUrl(this.httpServletRequest, "");
        Response response = new Response();
        String auth = request.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        try {
            if (auth.equals(authentication)) {
                JSONObject jsonObject = new JSONObject(body);
                String msisdn = jsonObject.optString("mdn");
                String productId = jsonObject.optString("productId");
                String callbackUrl = jsonObject.optString("callbackUrl");
                DateFormat nonceDF = new SimpleDateFormat("yyyyMMddHHmmss");
                DateFormat createDateDF = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat createTimeDF = new SimpleDateFormat("HH:mm:ss");
                Date date = new Date();
                String nonce = nonceDF.format(date) + "00000";
                String created = createDateDF.format(date)+"T"+createTimeDF.format(date)+"Z";
                String spId = "200037";
                String language = "en";
                String transacionId = utility.generateRandomNumber();
                callbackUrl = URLEncoder.encode(callbackUrl, "UTF-8");
                //String rawPasswordDigest = (nonce.trim()+created.trim()+"Robi1234").trim();
                String rawPasswordDigest = (nonce.trim()+created.trim()+"g25cH|\\|Ol0gIe$@082020").trim();
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(rawPasswordDigest.getBytes(StandardCharsets.UTF_8));
                // Convert byte array into signum representation
                BigInteger number = new BigInteger(1, hash);

                // Convert message digest into hex value
                StringBuilder hexString = new StringBuilder(number.toString(16));

                // Pad with leading zeros
                while (hexString.length() < 32)
                {
                    hexString.insert(0, '0');
                }
                System.out.println(hexString.toString());


                String encoded = Base64.getEncoder().encodeToString(hexString.toString().getBytes());
                String passwordDigest = URLEncoder.encode(encoded, "UTF-8");
              //  String url = "https://consent.robi.com.bd/store/wapconfirm?"+
                String url = "https://d-consent.robi.com.bd/Robi/asecure?"+
                        "spid="+spId+"&"+
                        "passwordDigest="+passwordDigest+"&"+
                        "msisdn="+msisdn+"&"+
                        "language="+language+"&"+
                        "transactionId="+transacionId+"&"+
                        "callbackURL="+callbackUrl+"&"+
                        "productID="+productId+"&"+
                        "nonce="+nonce+"&"+
                        "created="+created;
                response = utility.createResponse(200, utility.SUCCESS, url);
            } else {
                utility.showMessage("Authentication Error");
                response = utility.createResponse(500, utility.ERROR, utility.AUTHENTICATION);
            }
        }
        catch (Exception ex){
            response = utility.createResponse(500, utility.ERROR, ex.toString());
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{type}/unsubscription"/*, consumes = {MediaType.APPLICATION_JSON_VALUE}*/)
    public Response unSubscribeProduct(HttpServletRequest request,@PathVariable("type") String type, @RequestBody String reqBody) {
        utility.printCallingUrl(this.httpServletRequest, new JSONObject(reqBody).toString());
        Response response = new Response();
        String productName = "";
        PortfolioModel portfolioModel = new PortfolioModel();
        JSONObject jsonObject = new JSONObject(reqBody);
        String mdn = jsonObject.optString("mdn");
        SyncRelation syncRelation = new SyncRelation();
        String auth = request.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            switch(type){
                case "cg":
                    productName = "ClubG";
                    break;
                case "rg":
                    productName = "RadioG Streaming";
                    break;
                case "vg":
                    productName = "VideoG";
                    break;
                default:
                    utility.showMessage( "Service name path not valid");
                    response = utility.createResponse(500, utility.ERROR, utility.NOTUNSUBSCRIBE);
                    return response;
            }
            BaseModel baseModel = baseService.isBaseExists(mdn);
            if (baseModel == null) {
                utility.showMessage("mdn  not found");
                return utility.createResponse(500, utility.ERROR, utility.NOTUNSUBSCRIBE);
            }
            portfolioModel = portfolioService.getPortfolioModelByServiceName(productName);
            if(portfolioModel == null) {
                utility.showMessage("Portfolio not found for " + productName);
                return utility.createResponse(500, utility.ERROR, utility.NONE);
            }
            String serviceId = portfolioModel.getServiceId();
            syncRelation = syncRelationService.findSynRelationModelByServiceAndBase(baseModel, serviceId);
            if(syncRelation == null) {
                utility.showMessage("User not been subscribed, Please check plan type or mdn");
                return utility.createResponse(500, utility.ERROR, utility.NOTUNSUBSCRIBE);
            } else if(syncRelation.getUpdateDesc().equalsIgnoreCase(utility.SYNCUNSUBSCRIBE)) {
                utility.showMessage("User already Unsubscribed, Please check plan type or mdn");
                return utility.createResponse(500, utility.ERROR, utility.NOTUNSUBSCRIBE);
            }
            UnSubscribeProductReq unSubscribeProductReq = new UnSubscribeProductReq();
            unSubscribeProductReq.setID(mdn);
            unSubscribeProductReq.setProductID(syncRelation.getProductId());
            try {
                ApiModel apiModel = apiService.getApiModelByName(utility.UNSUBSCRIBEAPI);
                String unsubUri = utility.getTargetUri(apiModel);
                String unsubContext = apiModel.getJaxb();
                response = config.getUnsubscribeProductResponse(unsubContext, unsubUri, new GsonBuilder().create().toJson(unSubscribeProductReq));
                return response;
            } catch (Exception ex) {
                utility.showMessage(ex.getMessage() + " " + ex.toString());
                return utility.createResponse(500, ex.getMessage(), ex.toString());
            }

        } else {
            utility.showMessage("Authentication Error");
            response = utility.createResponse(500, utility.ERROR, utility.AUTHENTICATION);
        }

        return response;
    }

    @RequestMapping(value = "/{type}/subscriptionstatus", method = RequestMethod.POST)
    public sdp.Util.ResponseStatus SubscriptionStatus(HttpServletRequest request,@PathVariable("type") String type, @RequestBody String reqBody) throws IOException {
        utility.printCallingUrl(this.httpServletRequest, new JSONObject(reqBody).toString());
        ResponseStatus response = new ResponseStatus();
        long expirytime = 0;
        String productName = "";
        String dbProductName = "";
        PortfolioModel portfolioModel = new PortfolioModel();
        JSONObject jsonObject = new JSONObject(reqBody);
        String mdn = jsonObject.optString("mdn");
        SyncRelation syncRelation = new SyncRelation();
        String auth = request.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            switch(type){
                case "cg":
                    productName = "ClubG";
                    break;
                case "rg":
                    productName = "RadioG Streaming";
                    break;
                case "vg":
                    productName = "VideoG";
                    break;
                default:
                    utility.showMessage("Service name path not valid");
                    response = utility.createResponseStatus(500, utility.ERROR, utility.NONE, Long.toString(expirytime));
                    return response;

            }

            BaseModel baseModel = baseService.isBaseExists(mdn);
            if (baseModel == null) {
                baseModel = new BaseModel();
                baseModel.setMdn(jsonObject.optString("mdn"));
                baseModel.setBaseTypeModel(baseTypeService.getBaseTypeByCode(0));
                baseModel.setId(baseService.addBase(baseModel));
            }
            if (baseModel == null) {
                utility.showMessage("mdn not found");
                return utility.createResponseStatus(500, utility.ERROR, utility.NONE, Long.toString(expirytime));
            }
            portfolioModel = portfolioService.getPortfolioModelByServiceName(productName);
            if(portfolioModel == null) {
                utility.showMessage("Portfolio not found for " + productName);
                return utility.createResponseStatus(500, utility.ERROR, utility.NONE, Long.toString(expirytime));
            }
            String serviceId = portfolioModel.getServiceId();
            syncRelation = syncRelationService.findSynRelationModelByServiceAndBase(baseModel, serviceId);
            if(syncRelation == null) {
                utility.showMessage("sync data not found");
                return utility.createResponseStatus(200, utility.SUCCESS, utility.NONE, Long.toString(expirytime));
            }

            if(syncRelation.getUpdateDesc().equalsIgnoreCase("Addition")) {

                dbProductName = portfolioService.getPortfolioModelByProductId(syncRelation.getProductId()).getProductName();
                utility.showMessage("Subscribe for : " + portfolioService.getPortfolioModelByProductId(syncRelation.getProductId()).getProductName());
                if(dbProductName.contains("Daily")) {
                    expirytime = utility.getExpireTime(syncRelation,1);

                } else if (dbProductName.contains("Weekly")) {
                    expirytime = utility.getExpireTime(syncRelation,7);

                } else if (dbProductName.contains("Monthly")) {
                    expirytime = utility.getExpireTime(syncRelation,30);

                }

                return utility.createResponseStatus(200, utility.SUCCESS, portfolioService.getPortfolioModelByProductId(syncRelation.getProductId()).getProductName(), Long.toString(expirytime));
            }
            else if(syncRelation.getUpdateDesc().equalsIgnoreCase("Deletion")) {
                utility.showMessage("UnSubscribe for : " + portfolioService.getPortfolioModelByProductId(syncRelation.getProductId()).getProductName());
                return utility.createResponseStatus(200, utility.SUCCESS, utility.NONE, Long.toString(expirytime));
            } else {
                utility.showMessage("Sync updateDesc : " +syncRelation.getUpdateDesc());
                return utility.createResponseStatus(200, utility.SUCCESS, utility.NONE, Long.toString(expirytime));
            }

        } else {
            utility.showMessage("Authentication fail");
            response = utility.createResponseStatus(500, utility.ERROR, utility.NONE, Long.toString(expirytime));
        }
        return response;
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/subscriptionList"/*, consumes = {MediaType.APPLICATION_JSON_VALUE}*/)
//    public String getSubscriptionList(HttpServletRequest request, @RequestBody String reqBody) {
//        utility.printCallingUrl(this.httpServletRequest, new JSONObject(reqBody).toString());
//        Response response = new Response();
//        String auth = request.getHeader("Authorization");
//
//        if (auth.equals(authorization)) {
//            sdp.Xsd.SubscriptionList.SubscriptionListReq subscriptionListReq = new sdp.Xsd.SubscriptionList.SubscriptionListReq();
//            JSONObject jsonObject = new JSONObject(reqBody);
//            subscriptionListReq.setID(jsonObject.optString("id"));
//            try {
//                response = config.getSubScriptnListResponse(subscriptionListContext, subscriptionListUri, new GsonBuilder().create().toJson(subscriptionListReq));
//                return new GsonBuilder().create().toJson(response);
//            } catch (Exception ex) {
//                return utility.createJsonToResponse(500, ex.getMessage(), ex.toString());
//            }
//
//        } else {
//            response = utility.createResponse(500, "Error", "Authorization Error");
//        }
//      return new GsonBuilder().create().toJson(response);
//    }
}
