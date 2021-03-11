package sdp.Cms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sdp.Model.HeaderModel;
import sdp.Model.LogModel;
import sdp.Service.HeaderService;
import sdp.Util.KeyWord;
import sdp.Util.Utility;

import java.io.IOException;
import java.util.List;

/**
 * Created by Suvonkar Kundu on 1/30/2018.
 */
@RestController
@RequestMapping(value = "/cms")
public class HeaderController {
    @Autowired
    Utility utility;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    HeaderService headerService;

    @RequestMapping(value = "/header", method = RequestMethod.POST)
    public JsonNode createHeader(HttpServletRequest httpRequest, @RequestBody String body) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {
                JSONObject request = new JSONObject(body);
                HeaderModel headerModel= new HeaderModel();
                headerModel.setKey((String) request.get("key"));
                headerModel.setValue((String) request.get("value"));

                utility.logCDR(KeyWord.CREATE_HEADER,
                        headerModel.getId()+"|"+
                                headerModel.getKey()
                                + "|" + headerModel.getValue()


                );

                headerModel = headerService.addHeader(headerModel);
                if (headerModel != null) {
                    response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(headerModel)));
                } else {
                    response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
                }
                LogModel log = utility.saveLog(KeyWord.CMS,KeyWord.HEADER_Module, httpRequest, KeyWord.CREATE_HEADER ,String.valueOf(response.getInt("code")), request, response, currentTime);
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

    @RequestMapping(value = "/header/{id}", method = RequestMethod.POST)
    public JsonNode updateHeader(HttpServletRequest httpRequest,@PathVariable("id") long id, @RequestBody String body) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {
                JSONObject request = new JSONObject(body);
                HeaderModel headerModel= headerService.getHeaderById(id);
                if(headerModel!=null) {
                    headerModel.setId(id);
                    headerModel.setKey((String) request.get("key"));
                    headerModel.setValue((String) request.get("value"));

                    utility.logCDR(KeyWord.UPDATE_HEADER,
                            headerModel.getId() + "|" +
                                    headerModel.getKey()
                                    + "|" + headerModel.getValue()
                    );

                    headerModel = headerService.addHeader(headerModel);
                    if (headerModel != null) {
                        response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(headerModel)));
                    } else {
                        response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
                    }
                    LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.HEADER_Module, httpRequest, KeyWord.UPDATE_HEADER, String.valueOf(response.getInt("code")), request, response, currentTime);
                    if (log != null) {
                        utility.showMessage("Log inserted successfully with ID " + log.getId());
                    } else {
                        utility.showMessage("Log not inserted");
                    }
                }else {
                    response = utility.createResponse1(500, KeyWord.ERROR, "Header not found");
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
    @RequestMapping(value = "/header/all", method = RequestMethod.GET)
    public JsonNode getAllHeader(HttpServletRequest httpRequest) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {
                List<HeaderModel> headerList = headerService.getAllHeader();
                if (headerList.size()!=0) {
                    response = utility.createResponseArray(200, KeyWord.SUCCESS, new JSONArray(utility.convertPOJOtoString(headerList)));
                } else {
                    response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
                }
                LogModel log = utility.saveLog(KeyWord.CMS,KeyWord.HEADER_Module, httpRequest, KeyWord.List_HEADER ,String.valueOf(response.getInt("code")), new JSONObject(), response, currentTime);
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
