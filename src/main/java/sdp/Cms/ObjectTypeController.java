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
import sdp.Model.ObjectTypeModel;
import sdp.Model.PortfolioModel;
import sdp.Service.ApiService;
import sdp.Service.ContentService;
import sdp.Service.ObjectTypeService;
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
public class ObjectTypeController {

    @Autowired
    Utility utility;
    private HttpServletRequest httpServletRequest;
    @Autowired
    ObjectTypeService objectTypeService;
    @Autowired
    ApiService apiService;
    @Autowired
    ClientServiceConfig config;
    @Autowired
    ObjectMapper mapper;


    @RequestMapping(value = "/objecttype", method = RequestMethod.POST)
    public JsonNode createObjectType(HttpServletRequest httpRequest, @RequestBody String reqbody) throws IOException {
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
            ObjectTypeModel objectTypeModel = new ObjectTypeModel();
            ObjectTypeModel syncObjectType = syncObjectType(request, objectTypeModel);
            utility.logCDR(KeyWord.CREATE_OBJECTTYPE, syncObjectType.getCode() + "|" + syncObjectType.getTitle());
            ObjectTypeModel dbObjectType = objectTypeService.addObjectType(syncObjectType);
            if(dbObjectType == null) {
                utility.showMessage(KeyWord.NOTADDED);
                response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
            } else {
                utility.showMessage(KeyWord.ADDED);
                response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(dbObjectType)));
            }
            LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.OBJECTTYPE_MODULE, httpRequest, KeyWord.CREATE_OBJECTTYPE, response.optString("code"), request, response, Long.parseLong(currentTime));
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

    @RequestMapping(value = "/objecttype/{id}", method = RequestMethod.POST)
    public JsonNode updateObjectType(HttpServletRequest httpServletRequest,@PathVariable("id") String id, @RequestBody String reqBody) throws IOException {
        utility.printCallingUrl(httpServletRequest, new JSONObject(reqBody).toString());
        String currentTime = Long.toString(System.currentTimeMillis());
        JSONObject response = new JSONObject();
        String auth = httpServletRequest.getHeader(utility.AUTHORIZATION);
        String dbauth = utility.getBasicAuth();
        JSONObject request = new JSONObject(reqBody);
        long objectTypeId = Long.parseLong(id);
        if(!auth.equals(dbauth)) {
            utility.showMessage(utility.AUTHENTICATION);
            return mapper.readTree(utility.createJsonResponse(500, KeyWord.ERROR, utility.AUTHENTICATION).toString());
        }
        try {
            ObjectTypeModel dbObjectType = objectTypeService.findObjectType(objectTypeId);
            if(dbObjectType != null) {
                ObjectTypeModel syncObjectType = syncObjectType(request, dbObjectType);
                utility.logCDR(KeyWord.UPDATE_OBJECTTYPE, syncObjectType.getCode() + "|" + syncObjectType.getTitle());
                ObjectTypeModel dbUpdateObjectType = objectTypeService.addObjectType(syncObjectType);
                if(dbUpdateObjectType != null) {
                    utility.showMessage(KeyWord.UPDATED);
                    response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(dbUpdateObjectType)));
                } else {
                    utility.showMessage(KeyWord.NOTUPDATED);
                    response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject(utility.convertPOJOtoString(syncObjectType)));
                }
            } else {
                utility.showMessage(KeyWord.NOTFOUND);
                response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject(reqBody));
            }
            LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.OBJECTTYPE_MODULE, httpServletRequest, KeyWord.UPDATE_OBJECTTYPE ,response.optString("code"), request, response, Long.parseLong(currentTime));
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

    @RequestMapping(value = "/objecttype/all", method = RequestMethod.GET)
    public JsonNode getAllObjectType(HttpServletRequest httpServletRequest) throws IOException {
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
            Collection<ObjectTypeModel> dbObjectTypes = objectTypeService.findAllObjectTypes();
            if(dbObjectTypes.size() > 0 ) {
                utility.showMessage(KeyWord.FOUND);
                String objectTypeJson = new Gson().toJson(dbObjectTypes);
                JSONArray objectTypeInArray = new JSONArray(objectTypeJson);
                response = utility.createResponseArray(200, KeyWord.SUCCESS,objectTypeInArray);
            } else {
                utility.showMessage(KeyWord.NOTFOUND);
                response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
            }
            LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.OBJECTTYPE_MODULE, httpServletRequest, KeyWord.List_OBJECTTYPE ,response.optString("code"), new JSONObject(), response, Long.parseLong(currentTime));
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



    private ObjectTypeModel syncObjectType(JSONObject request, ObjectTypeModel objectTypeModel) {
        objectTypeModel.setCode(request.optString("code"));
        objectTypeModel.setTitle(request.optString("title"));
        return objectTypeModel;
    }
}
