package sdp.Cms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sdp.Config.ClientServiceConfig;
import sdp.Model.LogModel;
import sdp.Model.ObjectTypeModel;
import sdp.Model.UpdateTypeModel;
import sdp.Service.ApiService;
import sdp.Service.ObjectTypeService;
import sdp.Service.UpdateTypeService;
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
public class UpdateTypeController {


    @Autowired
    Utility utility;
    private HttpServletRequest httpServletRequest;
    @Autowired
    UpdateTypeService updateTypeService;
    @Autowired
    ApiService apiService;
    @Autowired
    ClientServiceConfig config;
    @Autowired
    ObjectMapper mapper;


    @RequestMapping(value = "/updatetype", method = RequestMethod.POST)
    public JsonNode createUpdateType(HttpServletRequest httpRequest, @RequestBody String reqbody) throws IOException {
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
            UpdateTypeModel updateTypeModel = new UpdateTypeModel();
            UpdateTypeModel syncUpdateType = syncUpdateType(request, updateTypeModel);
            utility.logCDR(KeyWord.CREATE_UPDATETYPE, syncUpdateType.getCode() + "|" + syncUpdateType.getDescription());
            UpdateTypeModel dbUpdateType = updateTypeService.addUpdateType(syncUpdateType);
            if(dbUpdateType == null) {
                utility.showMessage(KeyWord.NOTADDED);
                response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
            } else {
                utility.showMessage(KeyWord.ADDED);
                response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(dbUpdateType)));
            }
            LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.UPDATETYPE_Module, httpRequest, KeyWord.CREATE_UPDATETYPE, String.valueOf(response.getInt("code")), request, response, Long.parseLong(currentTime));
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

    @RequestMapping(value = "/updatetype/{id}", method = RequestMethod.POST)
    public JsonNode updateUpdateType(HttpServletRequest httpServletRequest,@PathVariable("id") String id, @RequestBody String reqBody) throws IOException {
        utility.printCallingUrl(httpServletRequest, new JSONObject(reqBody).toString());
        String currentTime = Long.toString(System.currentTimeMillis());
        JSONObject response = new JSONObject();
        String auth = httpServletRequest.getHeader(utility.AUTHORIZATION);
        String dbauth = utility.getBasicAuth();
        JSONObject request = new JSONObject(reqBody);
        long updateTypeId = Long.parseLong(id);
        if(!auth.equals(dbauth)) {
            utility.showMessage(utility.AUTHENTICATION);
            return mapper.readTree(utility.createJsonResponse(500, KeyWord.ERROR, utility.AUTHENTICATION).toString());
        }
        try {
            UpdateTypeModel dbUpdateType = updateTypeService.findUpdateType(updateTypeId);
            if(dbUpdateType != null) {
                UpdateTypeModel syncUpdateType = syncUpdateType(request, dbUpdateType);
                utility.logCDR(KeyWord.UPDATE_UPDATETYPE, syncUpdateType.getCode() + "|" + syncUpdateType.getDescription());
                UpdateTypeModel dbUpdateObjectType = updateTypeService.addUpdateType(syncUpdateType);
                if(dbUpdateObjectType != null) {
                    utility.showMessage(KeyWord.UPDATED);
                    response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(dbUpdateObjectType)));
                } else {
                    utility.showMessage(KeyWord.NOTUPDATED);
                    response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject(utility.convertPOJOtoString(syncUpdateType)));
                }
            } else {
                utility.showMessage(KeyWord.NOTFOUND);
                response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject(reqBody));
            }
            LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.UPDATETYPE_Module, httpServletRequest, KeyWord.UPDATE_UPDATETYPE, response.optString("code"), request, response, Long.parseLong(currentTime));
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

    @RequestMapping(value = "/updatetype/all", method = RequestMethod.GET)
    public JsonNode getAllUpdateType(HttpServletRequest httpServletRequest) throws IOException {
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
            Collection<UpdateTypeModel> dbUpdateTypes = updateTypeService.findAllUpdateType();
            if(dbUpdateTypes.size() > 0 ) {
                utility.showMessage(KeyWord.FOUND);
                String updateTypeJson = new Gson().toJson(dbUpdateTypes);
                JSONArray updateTypeInArray = new JSONArray(updateTypeJson);
                response = utility.createResponseArray(200, KeyWord.SUCCESS,updateTypeInArray);
            } else {
                utility.showMessage(KeyWord.NOTFOUND);
                response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
            }
            LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.UPDATETYPE_Module, httpServletRequest, KeyWord.List_UPDATETYPE, response.optString("code"), new JSONObject(), response, Long.parseLong(currentTime));
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



    private UpdateTypeModel syncUpdateType(JSONObject request, UpdateTypeModel updateTypeModel) {
        updateTypeModel.setCode(request.optString("code"));
        updateTypeModel.setDescription(request.optString("description"));
        return updateTypeModel;
    }

}
