package sdp.Cms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sdp.Config.ClientServiceConfig;

import sdp.Model.LogModel;
import sdp.Model.PortfolioModel;
import sdp.Service.ApiService;
import sdp.Service.PortfolioService;
import sdp.Util.KeyWord;
import sdp.Util.Response;
import sdp.Util.Utility;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by Atiq on 1/28/2018.
 */
@RestController
@RequestMapping(value = "/cms")
public class PortfolioController {

    @Autowired
    Utility utility;
    private HttpServletRequest httpServletRequest;
    @Autowired
    PortfolioService portfolioService;
    @Autowired
    ApiService apiService;
    @Autowired
    ClientServiceConfig config;
    @Autowired
    ObjectMapper mapper;

    @RequestMapping(value = "/portfolio", method = RequestMethod.POST)
    public JsonNode createPortfolio(HttpServletRequest httpRequest, @RequestBody String reqbody) throws IOException {
        utility.printCallingUrl(httpRequest, new JSONObject(reqbody).toString());
        String currentTime = Long.toString(System.currentTimeMillis());
        //Response response = new Response();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader(utility.AUTHORIZATION);
        String authentication = utility.getBasicAuth();
        JSONObject request = new JSONObject(reqbody);
        if (!auth.equals(authentication)) {
            utility.showMessage(utility.AUTHENTICATION);
            return mapper.readTree(utility.createJsonResponse(500, KeyWord.ERROR, utility.AUTHENTICATION).toString());
        }
        try {
            PortfolioModel portfolioModel = new PortfolioModel();
            PortfolioModel syncPortfolio = syncPortfolio(request,portfolioModel);
            utility.logCDR(KeyWord.CREATE_PORTFOLIO, syncPortfolio.getAccessCode() + "|"
                    + syncPortfolio.getProductId()+"|"+syncPortfolio.getProductName()+ "|"
                    + syncPortfolio.getAccessCode()+"|"+syncPortfolio.getSpId()+ "|"
                    + syncPortfolio.getShortCode()+"|"+syncPortfolio.getType());
            PortfolioModel dbPortfolio = portfolioService.addPortfolio(syncPortfolio);
            if(dbPortfolio == null) {
                utility.showMessage(KeyWord.NOTADDED);
                response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
            } else {
                utility.showMessage(KeyWord.ADDED);
                response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(dbPortfolio)));
            }
            LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.PORTFOLIO_Module,httpServletRequest , KeyWord.CREATE_PORTFOLIO, String.valueOf(response.getInt("code")), request, response, Long.parseLong(currentTime));
            if (log != null) {
                utility.showMessage("Log inserted successfully with ID " + log.getId());
            } else {
                utility.showMessage("Log not inserted");
            }

        } catch (Exception ex) {
            utility.showMessage(ex.toString());
            response = utility.createJsonResponse(500, KeyWord.ERROR, ex.toString());
        }

        return mapper.readTree(response.toString());

    }

    @RequestMapping(value = "/portfolio/{id}", method = RequestMethod.POST)
     public JsonNode updatePortfolio(HttpServletRequest httpServletRequest,@PathVariable("id") String id, @RequestBody String reqBody) throws IOException {
        utility.printCallingUrl(httpServletRequest, new JSONObject(reqBody).toString());
        String currentTime = Long.toString(System.currentTimeMillis());
        JSONObject response = new JSONObject();
        String auth = httpServletRequest.getHeader(utility.AUTHORIZATION);
        String dbauth = utility.getBasicAuth();
        JSONObject request = new JSONObject(reqBody);
        long portfolioId = Long.parseLong(id);
        if(!auth.equals(dbauth)) {
            utility.showMessage(utility.AUTHENTICATION);
            return mapper.readTree(utility.createJsonResponse(500, KeyWord.ERROR, utility.AUTHENTICATION).toString());
        }
        try {
            PortfolioModel dbPortfolioModel = portfolioService.findPortfolioModel(portfolioId);
            if(dbPortfolioModel != null) {
                PortfolioModel syncPortfolio = syncPortfolio(request, dbPortfolioModel);
                utility.logCDR(KeyWord.UPDATE_PORTFOLIO, syncPortfolio.getAccessCode()+ "|"
                        + syncPortfolio.getProductId()+ "|" +syncPortfolio.getProductName()+ "|"
                        + syncPortfolio.getAccessCode()+ "|" +syncPortfolio.getSpId()+ "|"
                        + syncPortfolio.getShortCode()+"|" +syncPortfolio.getType());
                PortfolioModel dbUpdatePortfolio = portfolioService.addPortfolio(syncPortfolio);
                if(dbUpdatePortfolio != null) {
                    utility.showMessage(KeyWord.UPDATED);
                    response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(dbUpdatePortfolio)));
                } else {
                    utility.showMessage(KeyWord.NOTUPDATED);
                    response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject(utility.convertPOJOtoString(syncPortfolio)));
                }
            } else {
                utility.showMessage(KeyWord.NOTFOUND);
                response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject(reqBody));
            }
            LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.PORTFOLIO_Module,httpServletRequest , KeyWord.UPDATE_PORTFOLIO, response.optString("code"), request, response, Long.parseLong(currentTime));
            if (log != null) {
                utility.showMessage("Log inserted successfully with ID " + log.getId());
            } else {
                utility.showMessage("Log not inserted");
            }
        } catch (Exception ex) {
            utility.showMessage(ex.toString());
            response = utility.createJsonResponse(500, KeyWord.ERROR, ex.toString());
        }

        return mapper.readTree(response.toString());

    }

    @RequestMapping(value = "/portfolio/all", method = RequestMethod.GET)
    public JsonNode getAllPortfolio(HttpServletRequest httpServletRequest) throws IOException {
        utility.printCallingUrl(httpServletRequest);
        String currentTime = Long.toString(System.currentTimeMillis());
        JSONObject response = new JSONObject();
        String auth = httpServletRequest.getHeader(utility.AUTHORIZATION);
        String dbauth = utility.getBasicAuth();
        if(!auth.equals(dbauth)) {
            utility.showMessage(utility.AUTHENTICATION);
            return mapper.readTree(utility.createJsonResponse(500, KeyWord.ERROR, utility.AUTHENTICATION).toString());
        }
        try {
            Collection<PortfolioModel> dbPortfolioModels = portfolioService.findAllPortfolios();
            if(dbPortfolioModels.size() > 0 ) {
                utility.showMessage(KeyWord.FOUND);
                String portfolioModelsJson = new Gson().toJson(dbPortfolioModels);
                JSONArray portfolioModelsArray = new JSONArray(portfolioModelsJson);
                response = utility.createResponseArray(200, KeyWord.SUCCESS,portfolioModelsArray);
            } else {
                utility.showMessage(KeyWord.NOTFOUND);
                response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
            }
            LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.PORTFOLIO_Module,httpServletRequest , KeyWord.List_PORTFOLIO, response.optString("code"), new JSONObject(), response, Long.parseLong(currentTime));
            if (log != null) {
                utility.showMessage("Log inserted successfully with ID " + log.getId());
            } else {
                utility.showMessage("Log not inserted");
            }
        } catch (Exception ex) {
            utility.showMessage(ex.toString());
            response = utility.createJsonResponse(500, KeyWord.ERROR, ex.toString());
        }

        return mapper.readTree(response.toString());

    }



    private PortfolioModel syncPortfolio(JSONObject request, PortfolioModel portfolioModel) {
        portfolioModel.setServiceName(request.optString("serviceName"));
        portfolioModel.setType(request.optString("type"));
        portfolioModel.setServiceId(request.optString("serviceId"));
        portfolioModel.setSpId(request.optString("spId"));
        portfolioModel.setCpName(request.optString("cpName"));
        portfolioModel.setProductName(request.optString("productName"));
        portfolioModel.setProductId(request.optString("productId"));
        portfolioModel.setAccessCode(request.optString("accessCode"));
        portfolioModel.setShortCode(Long.parseLong(request.optString("shortCode").trim()));
        portfolioModel.setUsage(Long.parseLong(request.optString("usage").trim()));
        portfolioModel.setRentFee(Long.parseLong(request.optString("rentFee").trim()));
        portfolioModel.setSubCharge(Long.parseLong(request.optString("subCharge").trim()));
        return portfolioModel;
    }
}
