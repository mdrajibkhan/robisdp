package sdp.Cms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sdp.Model.BaseModel;
import sdp.Model.BaseTypeModel;
import sdp.Model.LogModel;
import sdp.Service.BaseService;
import sdp.Service.BaseTypeService;
import sdp.Util.KeyWord;
import sdp.Util.Utility;

import java.io.IOException;
import java.util.List;

/**
 * Created by Suvonkar Kundu on 1/29/2018.
 */
@RestController
@RequestMapping(value = "/cms")
public class BaseController {
    @Autowired
    Utility utility;
    @Autowired
    BaseService baseService;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    BaseTypeService baseTypeService;

    @RequestMapping(value = "/base", method = RequestMethod.POST)
    public JsonNode createBase(HttpServletRequest httpRequest, @RequestBody String body) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {
                JSONObject request = new JSONObject(body);
                BaseModel basemodel = new BaseModel();
                BaseTypeModel baseTypeModel = baseTypeService.getIdByBaseTypeId(request.optInt("base_type_model_id"));
                if(baseTypeModel!=null) {
                    basemodel.setMdn((String) request.get("mdn"));
                    basemodel.setVmdn((String) request.get("vmdn"));
                    basemodel.setBaseTypeModel(baseTypeModel);
                    utility.logCDR(KeyWord.CREATE_BASE,
                                            basemodel.getMdn()+ "|" +
                                            basemodel.getVmdn()+ "|" +
                                            basemodel.getBaseTypeModel().getId()
                    );
                    BaseModel baseModel = baseService.getBaseById(baseService.addBase(basemodel));
                    if (baseModel != null) {
                        response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(baseModel)));
                    } else {
                        response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
                    }
                    LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.BASE_Module, httpRequest, KeyWord.CREATE_BASE, String.valueOf(response.getInt("code")), request, response, currentTime);
                    if (log != null) {
                        utility.showMessage("Log inserted successfully with ID " + log.getId());
                    } else {
                        utility.showMessage("Log not inserted");
                    }
                }
                else{
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


    @RequestMapping(value = "/base/{id}", method = RequestMethod.POST)
    public JsonNode updateBase(HttpServletRequest httpRequest, @PathVariable("id") long id, @RequestBody String body) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {
                JSONObject request = new JSONObject(body);
                BaseModel basemodel = baseService.getBaseById(id);
                if(basemodel !=null) {
                    BaseTypeModel baseTypeModel = baseTypeService.getIdByBaseTypeId(request.optInt("base_type_model_id"));
                      if (baseTypeModel != null) {
                        basemodel.setId(id);
                        basemodel.setMdn((String) request.get("mdn"));
                        basemodel.setVmdn((String) request.get("vmdn"));
                        basemodel.setBaseTypeModel(baseTypeModel);
                        utility.logCDR(KeyWord.UPDATE_BASE,
                                basemodel.getMdn()
                                        + "|" + basemodel.getVmdn()
                                        + "|" + basemodel.getBaseTypeModel().getId()

                        );
                        BaseModel baseModel = baseService.getBaseById(baseService.addBase(basemodel));
                        if (baseModel != null) {
                            response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(baseModel)));
                        } else {
                            response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
                        }
                        LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.BASE_Module, httpRequest, KeyWord.UPDATE_BASE, String.valueOf(response.getInt("code")), request, response, currentTime);
                        if (log != null) {
                            utility.showMessage("Log inserted successfully with ID " + log.getId());
                        } else {
                            utility.showMessage("Log not inserted");
                        }
                    } else {
                        response = utility.createResponse1(500, KeyWord.ERROR, "Base Type not found");
                    }
                } else {
                    response = utility.createResponse1(500, KeyWord.ERROR, "Base model not found");
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


    @RequestMapping(value = "/base/all", method = RequestMethod.GET)
    public JsonNode getAllBase(HttpServletRequest httpRequest) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {
                List<BaseModel> baseList = baseService.getAllListBase();
                if (baseList.size() == 0) {
                    response = utility.createResponseArray(500, KeyWord.ERROR, new JSONArray());

                } else {
                    response = utility.createResponseArray(200, KeyWord.SUCCESS, new JSONArray(utility.convertPOJOtoString(baseList)));
                }
                LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.BASE_Module, httpRequest, KeyWord.List_BASE, String.valueOf(response.getInt("code")), new JSONObject(), response, currentTime);
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
