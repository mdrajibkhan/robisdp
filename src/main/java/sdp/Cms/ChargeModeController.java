package sdp.Cms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sdp.Model.ChargeModeModel;
import sdp.Model.LogModel;
import sdp.Service.ChargeModeService;
import sdp.Util.KeyWord;
import sdp.Util.Utility;

import java.io.IOException;
import java.util.List;

/**
 * Created by Suvonkar Kundu on 1/30/2018.
 */
@RestController
@RequestMapping(value = "/cms")
public class ChargeModeController {
    @Autowired
    Utility utility;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    ChargeModeService chargeModeService;
    @RequestMapping(value = "/chargemode", method = RequestMethod.POST)
    public JsonNode createChargeMode(HttpServletRequest httpRequest, @RequestBody String body) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {
                JSONObject request = new JSONObject(body);
                ChargeModeModel chargeModeModel= new ChargeModeModel();
                chargeModeModel.setCode((String) request.get("code"));
                chargeModeModel.setTitle((String) request.get("title"));

                utility.logCDR(KeyWord.CHARGEMODE_Module,
                        chargeModeModel.getId() + "|" +
                                chargeModeModel.getCode()
                                + "|" + chargeModeModel.getTitle()


                );

                chargeModeModel = chargeModeService.addChargeMode(chargeModeModel);
                if (chargeModeModel != null) {
                    response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(chargeModeModel)));
                } else {
                    response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
                }
                LogModel log = utility.saveLog(KeyWord.CMS,KeyWord.CHARGEMODE_Module, httpRequest, KeyWord.CREATE_CHARGEMODE ,String.valueOf(response.getInt("code")), request, response, currentTime);
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

    @RequestMapping(value = "/chargemode/{id}", method = RequestMethod.POST)
    public JsonNode updateChargeMode(HttpServletRequest httpRequest,@PathVariable("id") long id, @RequestBody String body) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {
                JSONObject request = new JSONObject(body);
                ChargeModeModel chargeModeModel= chargeModeService.getChargeModeById(id);
                if(chargeModeModel!=null) {
                    chargeModeModel.setId(id);
                    chargeModeModel.setCode((String) request.get("code"));
                    chargeModeModel.setTitle((String) request.get("title"));

                    utility.logCDR(KeyWord.CHARGEMODE_Module,
                            chargeModeModel.getId() + "|" +
                                    chargeModeModel.getCode()
                                    + "|" + chargeModeModel.getTitle()


                    );

                    chargeModeModel = chargeModeService.addChargeMode(chargeModeModel);
                    if (chargeModeModel != null) {
                        response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(chargeModeModel)));
                    } else {
                        response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
                    }
                    LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.CHARGEMODE_Module, httpRequest, KeyWord.UPDATE_CHARGEMODE, String.valueOf(response.getInt("code")), request, response, currentTime);
                    if (log != null) {
                        utility.showMessage("Log inserted successfully with ID " + log.getId());
                    } else {
                        utility.showMessage("Log not inserted");
                    }
                }
                else
                {
                    response = utility.createResponse1(500, KeyWord.ERROR, "chargeMode not found");
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
    @RequestMapping(value = "/chargemode/all", method = RequestMethod.GET)
    public JsonNode getAllChargeMode(HttpServletRequest httpRequest) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {

                List<ChargeModeModel> chargemodelist = chargeModeService.getAllChargeModeData();

                if (chargemodelist.size()!=0) {
                    response = utility.createResponseArray(200, KeyWord.SUCCESS, new JSONArray(utility.convertPOJOtoString(chargemodelist)));
                } else {
                    response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
                }
                LogModel log = utility.saveLog(KeyWord.CMS,KeyWord.CHARGEMODE_Module, httpRequest, KeyWord.List_CHARGEMODE ,String.valueOf(response.getInt("code")), new JSONObject(), response, currentTime);
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
