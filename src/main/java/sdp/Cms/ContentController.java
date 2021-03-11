package sdp.Cms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sdp.Config.ClientServiceConfig;
import sdp.Model.ContentModel;
import sdp.Model.LogModel;
import sdp.Model.PortfolioModel;
import sdp.Service.ApiService;
import sdp.Service.ContentService;
import sdp.Service.PortfolioService;
import sdp.Util.KeyWord;
import sdp.Util.Utility;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by Atiq on 1/30/2018.
 */
@RestController
@RequestMapping(value = "/cms")
public class ContentController {

    @Autowired
    Utility utility;
    private HttpServletRequest httpServletRequest;
    @Autowired
    ContentService contentService;
    @Autowired
    ApiService apiService;
    @Autowired
    ClientServiceConfig config;
    @Autowired
    ObjectMapper mapper;

    @RequestMapping(value = "/content", method = RequestMethod.POST)
    public JsonNode createContent(HttpServletRequest httpRequest, @RequestBody String reqbody) throws IOException {
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
            portfolioModel.setId(Long.parseLong(request.optString("portfolioModel")));
            ContentModel contentModel = new ContentModel();
            contentModel.setPortfolioModel(portfolioModel);
            ContentModel syncContentModel = syncContent(request, contentModel);
            utility.logCDR(KeyWord.CREATE_CONTENT, syncContentModel.getPortfolioModel().getId() + "|"
                    + syncContentModel.getVerbose()+"|"
                    + syncContentModel.getDateDelivery()+"|"
                    + syncContentModel.getDateExpiry());
            ContentModel dbContent = contentService.addContent(syncContentModel);
            if(dbContent == null) {
                utility.showMessage(KeyWord.NOTADDED);
                response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
            } else {
                utility.showMessage(KeyWord.ADDED);
                response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(dbContent)));
            }
            LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.CONTENT_Module, httpRequest, KeyWord.CREATE_CONTENT, response.optString("code"), request, response, Long.parseLong(currentTime));
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


    @RequestMapping(value = "/content/{id}", method = RequestMethod.POST)
    public JsonNode updateContent(HttpServletRequest httpServletRequest,@PathVariable("id") String id, @RequestBody String reqBody) throws IOException {
        utility.printCallingUrl(httpServletRequest, new JSONObject(reqBody).toString());
        String currentTime = Long.toString(System.currentTimeMillis());
        JSONObject response = new JSONObject();
        String auth = httpServletRequest.getHeader(utility.AUTHORIZATION);
        String dbauth = utility.getBasicAuth();
        JSONObject request = new JSONObject(reqBody);
        long contentId = Long.parseLong(id);
        if(!auth.equals(dbauth)) {
            utility.showMessage(utility.AUTHENTICATION);
            return mapper.readTree(utility.createJsonResponse(500, KeyWord.ERROR, utility.AUTHENTICATION).toString());
        }
        try {
            ContentModel dbContent = contentService.findContent(contentId);
            if(dbContent != null) {
                PortfolioModel portfolioModel = new PortfolioModel();
                portfolioModel.setId(Long.parseLong(request.optString("portfolioModel")));
                dbContent.setPortfolioModel(portfolioModel);
                ContentModel syncContentModel = syncContent(request, dbContent);
                utility.logCDR(KeyWord.UPDATE_CONTENT, syncContentModel.getPortfolioModel().getId() + "|"
                        + syncContentModel.getVerbose()+"|"
                        + syncContentModel.getDateDelivery()+"|"
                        + syncContentModel.getDateExpiry());
                ContentModel dbUpdateContent = contentService.addContent(syncContentModel);
                if(dbUpdateContent != null) {
                    utility.showMessage(KeyWord.UPDATED);
                    response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(dbUpdateContent)));
                } else {
                    utility.showMessage(KeyWord.NOTUPDATED);
                    response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject(utility.convertPOJOtoString(syncContentModel)));
                }
            } else {
                utility.showMessage(KeyWord.NOTFOUND);
                response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject(reqBody));
            }
            LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.CONTENT_Module,httpServletRequest, KeyWord.CREATE_CONTENT, response.optString("code"), request, response, Long.parseLong(currentTime));
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



    @RequestMapping(value = "/content/all", method = RequestMethod.GET)
    public JsonNode getAllContent(HttpServletRequest httpServletRequest) throws IOException {
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
            Collection<ContentModel> dbContents = contentService.findAllContent();
            if(dbContents.size() > 0 ) {
                utility.showMessage(KeyWord.FOUND);
                String jsonContent = new Gson().toJson(dbContents);
                JSONArray contentsInArray = new JSONArray(jsonContent);
                response = utility.createResponseArray(200, KeyWord.SUCCESS,contentsInArray);
            } else {
                utility.showMessage(KeyWord.NOTFOUND);
                response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
            }
            LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.CONTENT_Module,  httpServletRequest, KeyWord.List_CONTENT, response.optString("code"), new JSONObject(), response, Long.parseLong(currentTime));
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

    private ContentModel syncContent(JSONObject request, ContentModel contentModel) {
       // contentModel.setPortfolioModel(request.optString("portfolioModel"));
        contentModel.setVerbose(request.optString("verbose"));
        contentModel.setDateDelivery(utility.getTimeStamp(request.optString("dateDelivery")));
        contentModel.setDateExpiry(utility.getTimeStamp(request.optString("dateExpiry")));
        return contentModel;
    }

}
