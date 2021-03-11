package sdp.Cms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sdp.Model.BaseTypeModel;
import sdp.Model.LogModel;
import sdp.Service.BaseTypeService;
import sdp.Util.KeyWord;
import sdp.Util.Utility;

import java.io.IOException;
import java.util.List;

/**
 * Created by Suvonkar Kundu on 1/30/2018.
 */
@RestController
@RequestMapping(value = "/cms")
public class BaseTypeController {
    @Autowired
    Utility utility;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    BaseTypeService baseTypeService;
    @RequestMapping(value = "/basetype", method = RequestMethod.POST)
    public JsonNode createBaseType(HttpServletRequest httpRequest, @RequestBody String body) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {
                JSONObject request = new JSONObject(body);
                BaseTypeModel baseTypeModel= new BaseTypeModel();
                baseTypeModel.setCode( Long.valueOf((String) request.get("code")));
                baseTypeModel.setTitle((String) request.get("title"));
                utility.logCDR(KeyWord.CREATE_BASE_TYPE,
                                baseTypeModel.getId()+"|"+
                                baseTypeModel.getCode()
                                + "|" + baseTypeModel.getTitle()
                );

                baseTypeModel = baseTypeService.addBaseType(baseTypeModel);
                if (baseTypeModel != null) {
                    response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(baseTypeModel)));
                } else {
                    response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
                }
                LogModel log = utility.saveLog(KeyWord.CMS,KeyWord.BASETYPE_Module, httpRequest, KeyWord.CREATE_BASE_TYPE ,String.valueOf(response.getInt("code")), request, response, currentTime);
                if (log != null) {
                    utility.showMessage("Log inserted successfully with ID " + log.getId());
                } else {
                    utility.showMessage("Log not inserted");
                }
            } catch (Exception e) {
                response = utility.createResponse1(500, KeyWord.ERROR, e.toString());
                utility.showMessage(e.toString());
            }
        } else {
            response = utility.createResponse1(500, KeyWord.ERROR, utility.AUTHENTICATION);
        }
        return mapper.readTree(response.toString());
    }


    @RequestMapping(value = "/basetype/{id}", method = RequestMethod.POST)
    public JsonNode updateBaseType(HttpServletRequest httpRequest,@PathVariable("id") long id, @RequestBody String body) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {
                JSONObject request = new JSONObject(body);
                BaseTypeModel baseTypeModel= baseTypeService.getIdByBaseTypeId(id);
                if(baseTypeModel!=null) {
                    baseTypeModel.setId(id);
                    baseTypeModel.setCode(Long.valueOf((String) request.get("code")));
                    baseTypeModel.setTitle((String) request.get("title"));
                    utility.logCDR(KeyWord.UPDATE_BASE_TYPE,
                            baseTypeModel.getId() + "|" +
                                    baseTypeModel.getCode()
                                    + "|" + baseTypeModel.getTitle()

                    );
                    baseTypeModel = baseTypeService.addBaseType(baseTypeModel);
                    if (baseTypeModel != null) {
                        response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(baseTypeModel)));
                    } else {
                        response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
                    }
                    LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.BASETYPE_Module, httpRequest, KeyWord.UPDATE_BASE, String.valueOf(response.getInt("code")), request, response, currentTime);
                    if (log != null) {
                        utility.showMessage("Log inserted successfully with ID " + log.getId());
                    } else {
                        utility.showMessage("Log not inserted");
                    }
                } else {
                    response = utility.createResponse1(500, KeyWord.ERROR, "Base Type not found");
                }
            } catch (Exception e) {
                response = utility.createResponse1(500, KeyWord.ERROR, e.toString());
                utility.showMessage(e.toString());
            }
        } else {
            response = utility.createResponse1(500, KeyWord.ERROR, utility.AUTHENTICATION);
        }
        return mapper.readTree(response.toString());
    }

    @RequestMapping(value = "/basetype/all", method = RequestMethod.GET)
    public JsonNode getAllBaseType(HttpServletRequest httpRequest) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {

                List<BaseTypeModel> baseTypelist = baseTypeService.getAllListBaseType();

                if (baseTypelist.size()!=0) {
                    response = utility.createResponseArray(200, KeyWord.SUCCESS, new JSONArray(utility.convertPOJOtoString(baseTypelist)));
                } else {
                    response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
                }
                LogModel log = utility.saveLog(KeyWord.CMS,KeyWord.BASETYPE_Module, httpRequest, KeyWord.List_BASE_TYPE ,String.valueOf(response.getInt("code")), new JSONObject(), response, currentTime);
                if (log != null) {
                    utility.showMessage("Log inserted successfully with ID " + log.getId());
                } else {
                    utility.showMessage("Log not inserted");
                }
            } catch (Exception e) {
                response = utility.createResponse1(500, KeyWord.ERROR, e.toString());
                utility.showMessage(e.toString());
            }
        } else {
            response = utility.createResponse1(500, KeyWord.ERROR, utility.AUTHENTICATION);
        }
        return mapper.readTree(response.toString());
    }
}
