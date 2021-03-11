package sdp.Cms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sdp.Model.ChannelModel;
import sdp.Model.LogModel;
import sdp.Service.ChannelService;
import sdp.Util.KeyWord;
import sdp.Util.Utility;

import java.io.IOException;
import java.util.List;

/**
 * Created by Suvonkar Kundu on 1/30/2018.
 */
@RestController
@RequestMapping(value = "/cms")
public class ChannelController {
    @Autowired
    Utility utility;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    ChannelService channelService;
    @RequestMapping(value = "/channel", method = RequestMethod.POST)
    public JsonNode createChannel(HttpServletRequest httpRequest, @RequestBody String body) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {
                JSONObject request = new JSONObject(body);
                ChannelModel channelModel= new ChannelModel();
                channelModel.setCode((String) request.get("code"));
                channelModel.setTitle((String) request.get("title"));
                utility.logCDR(KeyWord.CREATE_CHANNEL,
                        channelModel.getId() + "|" +
                                channelModel.getCode()
                                + "|" + channelModel.getTitle()
                );
                channelModel = channelService.addChannel(channelModel);
                if (channelModel != null) {
                    response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(channelModel)));
                } else {
                    response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
                }
                LogModel log = utility.saveLog(KeyWord.CMS,KeyWord.CHANNEL_Module, httpRequest, KeyWord.CREATE_CHANNEL ,String.valueOf(response.getInt("code")), request, response, currentTime);
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


    @RequestMapping(value = "/channel/{id}", method = RequestMethod.POST)
    public JsonNode updateChannel(HttpServletRequest httpRequest,@PathVariable("id") long id, @RequestBody String body) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {
                JSONObject request = new JSONObject(body);
                ChannelModel channelModel= channelService.getChannelById(id);
                if(channelModel!=null) {
                    channelModel.setId(id);
                    channelModel.setCode((String) request.get("code"));
                    channelModel.setTitle((String) request.get("title"));

                    utility.logCDR(KeyWord.UPDATE_CHANNEL,
                            channelModel.getId() + "|" +
                                    channelModel.getCode()
                                    + "|" + channelModel.getTitle()
                    );

                    channelModel = channelService.addChannel(channelModel);
                    if (channelModel != null) {
                        response = utility.createResponseObject(200, KeyWord.SUCCESS, new JSONObject(utility.convertPOJOtoString(channelModel)));
                    } else {
                        response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
                    }
                    LogModel log = utility.saveLog(KeyWord.CMS, KeyWord.CHANNEL_Module, httpRequest, KeyWord.UPDATE_CHANNEL, String.valueOf(response.getInt("code")), request, response, currentTime);
                    if (log != null) {
                        utility.showMessage("Log inserted successfully with ID " + log.getId());
                    } else {
                        utility.showMessage("Log not inserted");
                    }
                } else {
                    response = utility.createResponse1(500, KeyWord.ERROR, "Channel not found");
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

    @RequestMapping(value = "/channel/all", method = RequestMethod.GET)
    public JsonNode getAllChannel(HttpServletRequest httpRequest) throws IOException {
        utility.printCallingUrl(httpRequest);
        long currentTime = System.currentTimeMillis();
        JSONObject response = new JSONObject();
        String auth = httpRequest.getHeader("Authorization");
        String authentication = utility.getBasicAuth();
        if (auth.equals(authentication)) {
            try {

                List<ChannelModel> channelList = channelService.getAllChannel();

                if (channelList.size()!=0) {
                    response = utility.createResponseArray(200, KeyWord.SUCCESS, new JSONArray(utility.convertPOJOtoString(channelList)));
                } else {
                    response = utility.createResponseObject(500, KeyWord.ERROR, new JSONObject());
                }
                LogModel log = utility.saveLog(KeyWord.CMS,KeyWord.CHANNEL_Module, httpRequest, KeyWord.List_CHANNEL ,String.valueOf(response.getInt("code")), new JSONObject(), response, currentTime);
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
