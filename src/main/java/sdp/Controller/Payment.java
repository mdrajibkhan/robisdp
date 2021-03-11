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
import sdp.Service.ApiService;
import sdp.Service.PortfolioService;
import sdp.Util.Response;
import sdp.Util.Utility;
import sdp.Xsd.Amount.ChargeAmount;
import sdp.Xsd.Amount.RefundAmount;
import sdp.Xsd.ReserveAmountCharging.ChargeReservationReq;
import sdp.Xsd.ReserveAmountCharging.ReleaseReservationReq;
import sdp.Xsd.ReserveAmountCharging.ReserveAmountReq;
import sdp.Xsd.Volume.ChargeVolume;
import sdp.Xsd.Volume.RefundVolume;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;

/**
 * Created by Tanzil on 1/1/2018.
 */
@RestController
public class Payment {


    @Autowired
    Utility utility;


    private HttpServletRequest httpServletRequest;

    @Autowired
    PortfolioService portfolioService;
    @Autowired
    ApiService apiService;

    @Autowired
    ClientServiceConfig config;



    @RequestMapping(value = "/cg/ondemand", method = RequestMethod.POST)
    public Response chargeAmount(HttpServletRequest request, @RequestBody String body) throws IOException {
        Response response = new Response();
        String auth = request.getHeader("Authorization");
        String authentication = utility.getBasicAuth();

        if (auth.equals(authentication)) {
            JSONObject jsonObject = new JSONObject(body);
            ChargeAmount req = new ChargeAmount();
            req.setEndUserIdentifier(jsonObject.optString("mdn"));
            try {
                ApiModel apiModel = apiService.getApiModelByName(utility.CHARGEAMOUNT);
                String amountUri = utility.getTargetUri(apiModel);
                String amountContext = apiModel.getJaxb();
                response = config.chargeAmountResponse(amountContext, amountUri, new GsonBuilder().create().toJson(req));
                return response;
            } catch (Exception ex) {
                //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
                utility.showMessage(ex.getMessage() + " " + ex.toString());
                return utility.createResponse(500, utility.ERROR, utility.NOTCHARGE);
            }
        } else {
            utility.showMessage("Authentication fail");
            response = utility.createResponse(500, utility.ERROR, utility.AUTHENTICATION);
        }
        return response;
    }


    @RequestMapping(value = "/refundamount", method = RequestMethod.POST)
    public Response refundAmount(HttpServletRequest request, @RequestBody String body) throws IOException {
        Response response = new Response();
        String auth = request.getHeader("Authorization");
       String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
           JSONObject jsonObject = new JSONObject(body);

            RefundAmount req = new RefundAmount();
            req.setEndUserIdentifier(jsonObject.optString("endUserIdentifier"));
            req.setProductId(jsonObject.optString("productId"));
            try {
                ApiModel apiModel = apiService.getApiModelByName(utility.CHARGEAMOUNT);
                String amountUri = utility.getTargetUri(apiModel);
                String amountContext = apiModel.getJaxb();
                response = config.refundAmountResponse(amountContext, amountUri, new GsonBuilder().create().toJson(req));
                return response;
            } catch (Exception ex) {
                //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
                return utility.createResponse(500, ex.getMessage(), ex.toString());
            }
        } else {
            response = utility.createResponse(500, "Error", "Authorization Error");
        }
        return response;
    }


    @RequestMapping(value = "/chargevolume", method = RequestMethod.POST)
    public Response chargeVolume(HttpServletRequest request, @RequestBody String body) throws IOException {
        Response response = new Response();
        String auth = request.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            JSONObject jsonObject = new JSONObject(body);
            ChargeVolume req = new ChargeVolume();
            req.setEndUserIdentifier(jsonObject.optString("endUserIdentifier"));
            req.setProductId(jsonObject.optString("productId"));
            req.setVolume(jsonObject.optString("volume"));
            try {
                ApiModel apiModel = apiService.getApiModelByName(utility.CHARGEVOLUME);
                String volumeUri = utility.getTargetUri(apiModel);
                String volumeContext = apiModel.getJaxb();
                response = config.chargeVolumeResponse(volumeContext, volumeUri, new GsonBuilder().create().toJson(req));
                return response;
            } catch (Exception ex) {
                //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
                return utility.createResponse(500, ex.getMessage(), ex.toString());
            }
        } else {
            response = utility.createResponse(500, "Error", "Authorization Error");
        }
        return response;
    }

    @RequestMapping(value = "/refundvolume", method = RequestMethod.POST)
    public Response refundVolume(HttpServletRequest request, @RequestBody String body) throws IOException {
        Response response = new Response();
        String auth = request.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            JSONObject jsonObject = new JSONObject(body);
            RefundVolume req = new RefundVolume();
            req.setEndUserIdentifier(jsonObject.optString("endUserIdentifier"));
            req.setProductId(jsonObject.optString("productId"));
            req.setVolume(jsonObject.optString("volume"));
            try {
                ApiModel apiModel = apiService.getApiModelByName(utility.CHARGEVOLUME);
                String volumeUri = utility.getTargetUri(apiModel);
                String volumeContext = apiModel.getJaxb();
                response = config.refundVolumeResponse(volumeContext, volumeUri, new GsonBuilder().create().toJson(req));
                return response;
            } catch (Exception ex) {
                //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
                return utility.createResponse(500, ex.getMessage(), ex.toString());
            }
        } else {
            response = utility.createResponse(500, "Error", "Authorization Error");
        }
        return response;
    }

    @RequestMapping(value = "/reserveamount", method = RequestMethod.POST)
    public Response reserveAmount(HttpServletRequest request, @RequestBody String body) throws IOException {
        Response response = new Response();
        String auth = request.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            JSONObject jsonObject = new JSONObject(body);
            ReserveAmountReq req = new ReserveAmountReq();
            req.setEndUserIdentifier(jsonObject.optString("endUserIdentifier"));
            req.setProductId(jsonObject.optString("productId"));
            try {
                ApiModel apiModel = apiService.getApiModelByName(utility.RESERVECHARGE);
                String rsrvAmntChargingUri = utility.getTargetUri(apiModel);
                String rsrvAmntChargingContext = apiModel.getJaxb();
                response = config.getReserveAmntRspnse(rsrvAmntChargingContext, rsrvAmntChargingUri, new GsonBuilder().create().toJson(req));
                return response;
            } catch (Exception ex) {
                //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
                return utility.createResponse(500, ex.getMessage(), ex.toString());
            }

        } else {
            response = utility.createResponse(500, "Error", "Authorization Error");
        }
        return response;
    }

    @RequestMapping(value = "/chargereserve", method = RequestMethod.POST)
    public Response chargeReserve(HttpServletRequest request, @RequestBody String body) throws IOException {
        Response response = new Response();
        String auth = request.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            JSONObject jsonObject = new JSONObject(body);
            ChargeReservationReq req = new ChargeReservationReq();
            req.setEndUserIdentifier(jsonObject.optString("endUserIdentifier"));
            req.setProductId(jsonObject.optString("productId"));
            try {
                ApiModel apiModel = apiService.getApiModelByName(utility.RESERVECHARGE);
                String rsrvAmntChargingUri = utility.getTargetUri(apiModel);
                String rsrvAmntChargingContext = apiModel.getJaxb();
                response = config.getChargeResrvtnRespnse(rsrvAmntChargingContext, rsrvAmntChargingUri, new GsonBuilder().create().toJson(req));
                return response;
            } catch (Exception ex) {
                //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
                return utility.createResponse(500, ex.getMessage(), ex.toString());
            }
        } else {
            response = utility.createResponse(500, "Error", "Authorization Error");
        }
        return response;
    }

    @RequestMapping(value = "/releasereserve", method = RequestMethod.POST)
    public Response releaseReserve(HttpServletRequest request, @RequestBody String body) throws IOException {
        Response response = new Response();
        String auth = request.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            JSONObject jsonObject = new JSONObject(body);
            ReleaseReservationReq req = new ReleaseReservationReq();
            req.setEndUserIdentifier(jsonObject.optString("endUserIdentifier"));
            req.setProductId(jsonObject.optString("productId"));
            try {
                ApiModel apiModel = apiService.getApiModelByName(utility.RESERVECHARGE);
                String rsrvAmntChargingUri = utility.getTargetUri(apiModel);
                String rsrvAmntChargingContext = apiModel.getJaxb();
                response = config.getReleaseResrvRspnse(rsrvAmntChargingContext, rsrvAmntChargingUri, new GsonBuilder().create().toJson(req));
                return response;
            } catch (Exception ex) {
                //throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
                return utility.createResponse(500, ex.getMessage(), ex.toString());
            }
        } else {
            response = utility.createResponse(500, "Error", "Authorization Error");
        }
        return response;
    }
}