package sdp.Cms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sdp.Model.ExpirationModeModel;
import sdp.Model.LogModel;
import sdp.Service.ExpirationModeService;
import sdp.Util.KeyWord;
import sdp.Util.Utility;

import java.io.IOException;
import java.util.List;

/**
 * Created by Suvonkar Kundu on 1/30/2018.
 */
@RestController
@RequestMapping(value = "/cms")
public class ExpirationController {
    @Autowired
    Utility utility;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    ExpirationModeService expirationModeService;

    @RequestMapping(value = "/expiration", method = RequestMethod.POST)
    public JsonNode createExpirationMode(HttpServletRequest httpRequest, @RequestBody String body) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {
                JSONObject request = new JSONObject(body);
                ExpirationModeModel expirationModeModel= new ExpirationModeModel();
                expirationModeModel.setCode((String) request.get("code"));
                expirationModeModel.setTitle((String) request.get("title"));

                utility.logCDR(KeyWord.CREATE_EXPIRATIONMODE,
                        expirationModeModel.getId()+"|"+
                                expirationModeModel.getCode()
                                + "|" +expirationModeModel.getTitle()


                );

                expirationModeModel = expirationModeService.addExpirationMode(expirationModeModel);
                if (expirationModeModel != null) {
                    response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(expirationModeModel)));
                } else {
                    response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
                }
                LogModel log = utility.saveLog(KeyWord.CMS,KeyWord.EXPIRATIONMODE_Module, httpRequest, KeyWord.CREATE_EXPIRATIONMODE ,String.valueOf(response.getInt("code")), request, response, currentTime);
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
    @RequestMapping(value = "/expiration/{id}", method = RequestMethod.POST)
    public JsonNode updateExpirationMode(HttpServletRequest httpRequest,@PathVariable("id") long id, @RequestBody String body) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {
                JSONObject request = new JSONObject(body);
                ExpirationModeModel expirationModeModel= expirationModeService.getExpirationModeById(id);
                if(expirationModeModel!=null) {
                    expirationModeModel.setId(id);
                    expirationModeModel.setCode((String) request.get("code"));
                    expirationModeModel.setTitle((String) request.get("title"));

                    utility.logCDR(KeyWord.UPDATE_EXPIRATIONMODE,
                            expirationModeModel.getId() + "|" +
                                    expirationModeModel.getCode()
                                    + "|" + expirationModeModel.getTitle()


                    );

                    expirationModeModel = expirationModeService.addExpirationMode(expirationModeModel);
                    if (expirationModeModel != null) {
                        response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(expirationModeModel)));
                    } else {
                        response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
                    }
                    LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.EXPIRATIONMODE_Module, httpRequest, KeyWord.UPDATE_EXPIRATIONMODE, String.valueOf(response.getInt("code")), request, response, currentTime);
                    if (log != null) {
                        utility.showMessage("Log inserted successfully with ID " + log.getId());
                    } else {
                        utility.showMessage("Log not inserted");
                    }
                } else {
                    response = utility.createResponse1(500, KeyWord.ERROR, "Expiration not found");
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

    @RequestMapping(value = "/expiration/all", method = RequestMethod.GET)
    public JsonNode getAllExpirationMode(HttpServletRequest httpRequest) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {

                List<ExpirationModeModel> expirationModeModelList = expirationModeService.getAllExpirationMode();

                if (expirationModeModelList.size()!=0) {
                    response = utility.createResponseArray(200, KeyWord.SUCCESS, new JSONArray(utility.convertPOJOtoString(expirationModeModelList)));
                } else {
                    response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
                }
                LogModel log = utility.saveLog(KeyWord.CMS,KeyWord.EXPIRATIONMODE_Module, httpRequest, KeyWord.List_EXPIRATIONMODE ,String.valueOf(response.getInt("code")), new JSONObject(), response, currentTime);
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
