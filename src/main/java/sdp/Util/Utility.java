package sdp.Util;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sdp.Model.*;
import sdp.Service.ApiService;
import sdp.Service.HeaderService;
import sdp.Service.LogService;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Hp on 9/25/2017.
 */
@Component
public class Utility {

    @Value("${save.log}")
    private boolean saveLog;
    @Autowired
    HeaderService headerService;
    @Autowired
    LogService logService;
    @Autowired
    ApiService apiService;

    public static final String ERROR = "Error";
    public static final String NONE = "None";
    public static final String SUCCESS = "Success";
    public static final String FOUND = "Found";

    public static final String SUBSCRIBE = "Subscribed";
    public static final String NOTSUBSCRIBE = "Not Subscribed";
    public static final String UNSUBSCRIBE = "Unsubscribed";
    public static final String NOTUNSUBSCRIBE = "Not Unsubscribed";
    public static final String SYNCSUBSCRIBE = "Addition";
    public static final String SYNCUNSUBSCRIBE = "Deletion";
    public static final String CHARGE = "Charged";
    public static final String NOTCHARGE = "Not Charged";
    public static final String AUTHENTICATION = "Authentication Fail";

    public static final String NOTIFYSMS = "NOTIFY_SMS";
    public static final String RECEIVEDSMS = "RECEIVED_SMS";
    public static final String SENDSMS = "SEND_SMS";
    public static final String SUBSCRIPTIONLIST = "SUBSCRIPTION_LIST";
    public static final String RESERVECHARGE = "RESERVE_CHARGE";
    public static final String UNSUBSCRIBEAPI = "UNSUBSCRIBE";
    public static final String SUBSCRIBEAPI = "SUBSCRIBE";
    public static final String CHARGEAMOUNT = "CHARGE_AMOUNT";
    public static final String CHARGEVOLUME = "CHARGE_VOLUME";

    public static final String APIUSER = "API_USER";
    public static final String APIPASSWORD = "API_PASSWORD";
    public static final String SAVELOG = "SAVE_LOG";
    public static final String BASEURL = "BASE_URL";
    public static final String THREADTIME = "THREAD_TIME";

    public static final String AUTHORIZATION = "Authorization";


    public void showMessage(String message) {
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        Format format = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        String logMessage = format.format(date) + " SDP -> " + message;
        System.out.println(logMessage);
        if (isSaveLog()) {
            logToFile(logMessage);
        }
    }

    public boolean isSaveLog() {
        String savelog = headerService.getValue(SAVELOG);
        if(savelog.trim().equalsIgnoreCase("yes"))
            return true;
        else
            return false;
    }

    public String genRandomCorrelator() {
        Random r = new Random( System.currentTimeMillis() );
        return Integer.toString(10000 + r.nextInt(20000));
    }

    public String generateReference() {
        String token = "";
        try {
            Random random = new Random();
            int number = random.nextInt(10000) + 1000;
            long currentTime = System.currentTimeMillis();
            String innerToken = String.valueOf(currentTime) + number;
            byte[] bytesOfMessage = Base64.getEncoder().encode(innerToken.getBytes());
            token = new String(bytesOfMessage);
        } catch (Exception ex) {
            showMessage(ex.toString());
        }
        return token;
    }

    public String generateRandomNumber() {
        String token = "";
        try {
            Random random = new Random();
            int number = random.nextInt(10000) + 1000;
            long currentTime = System.currentTimeMillis();
            token = String.valueOf(currentTime) + number;
            //byte[] bytesOfMessage = Base64.getEncoder().encode(innerToken.getBytes());
            //token = new String(bytesOfMessage);
        } catch (Exception ex) {
            showMessage(ex.toString());
        }
        return token;
    }

    public String getTimeStamp() {
        try {
            long currentTime = System.currentTimeMillis();
            Date date = new Date(currentTime);
            Format format = new SimpleDateFormat("yyyyMMddHHmmss");
            return format.format(date);
        } catch (Exception ex) {
            showMessage(ex.toString());
            return "0";
        }
    }

    public String getTrvlTime(long startTime, long endTime) {
        long trvlTime = (endTime - startTime);
        return  String.valueOf(trvlTime)+ "ms";
    }

    public long getExpireTime(SyncRelation syncRelation, int day) {
        Timestamp dbTimestamp = syncRelation.getCreatedAt();
        long longTime = (long) (System.currentTimeMillis()) + ((long) 60*60*24*1000*day);
        return longTime;
    }


    public HashMap<String, String> getFields(Object o) {
        HashMap<String, String> map = new HashMap<>();
        try {
            for (Field field : o.getClass().getDeclaredFields()) {
                field.setAccessible(true);     // you also get non-public fields
                System.out.print(field.getName() + " = " + field.get(o) + ", ");
                map.put(field.getName(), String.valueOf(field.get(o)));
            }
        } catch (Exception ex) {
            showMessage(ex.toString());
        }
        return map;
    }

    public Timestamp convertStringDateToTimestamp(String value) {
        Timestamp timestamp = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(value);
            timestamp = new Timestamp(parsedDate.getTime());
        } catch (Exception e) { //this generic but you can control another types of exception
            //timestamp = new java.sql.Timestamp("0000-00-00 00:00:00");
        }
        return timestamp;
    }

    public void printCallingUrl(HttpServletRequest request, String body) {
        String incoming = "Request coming from -> " + request.getRemoteAddr();
        showMessage(incoming);
        String message = request.getMethod().toString() + " -> " + request.getRequestURL().toString();
        showMessage(message);
        showMessage("Body -> " + body);
    }

    public void printCallingUrl(HttpServletRequest request, String header, String body) {
        String incoming = "Request coming from -> " + request.getRemoteAddr();
        showMessage(incoming);
        String message = request.getMethod().toString() + " -> " + request.getRequestURL().toString();
        showMessage(message);
        showMessage("Soap Header -> " + header);
        showMessage("Soap Body -> " + body);
    }

    public void logToFile(String message) {
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        Format format = new SimpleDateFormat("yyyy_MMMM_dd");
        String filename = format.format(date);
        try {
            String FILENAME = "logs/" + filename + ".log";
            File file = new File(FILENAME);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(message + "\r\n");
        } catch (Exception ex) {
            showMessage("File Not Created");
        } finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
                if (fileWriter != null)
                    fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void logToCDR(String message,String directory) {
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        Format format = new SimpleDateFormat("yyyy_MMMM_dd");
        String filename = format.format(date);
        Date logDate = new Date(currentTime);
        Format logFormat = new SimpleDateFormat("HH:mm:ss");
        String logMessage = logFormat.format(logDate) + " -> " + message;
        try {
            String DIRECTORY = "cdr/" + directory;
            File dir = new File(DIRECTORY);

            if (!dir.exists()) {

                dir.mkdir();
            }
            String FILENAME = DIRECTORY + "/" + filename + ".txt";
            File file = new File(FILENAME);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(logMessage + "\r\n");
        } catch (Exception ex) {
            showMessage("cdr text Not Created");
        } finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
                if (fileWriter != null)
                    fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void logCDR(String directory, String message) {
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        Format format = new SimpleDateFormat("yyyy_MMMM_dd");
        String filename = format.format(date);
        Date logDate = new Date(currentTime);
        Format logFormat = new SimpleDateFormat("HH:mm:ss");
        String logMessage = logFormat.format(logDate) + " -> " + message;
        try {
            String DIRECTORY = "cdr/" + directory;
            File dir = new File(DIRECTORY);

            if (!dir.exists()) {

                dir.mkdir();
            }
            String FILENAME = DIRECTORY + "/" + filename + ".txt";
            File file = new File(FILENAME);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(logMessage + "\r\n");
        } catch (Exception ex) {
            showMessage("File Not Created");
        } finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
                if (fileWriter != null)
                    fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    public Timestamp getTimeStamp(String timeString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        return timestamp;
    }


    public String convertPOJOtoXml(Object object) {
        if(object == null)
            return null;
        JSONObject jsonObject = new JSONObject(new GsonBuilder().create().toJson(object));
        String xml = XML.toString(jsonObject);
        String className = object.getClass().getName().substring(object.getClass().getName().lastIndexOf('.') + 1);
        className = Character.toLowerCase(className.charAt(0)) + className.substring(1);
        xml = "<" + className + ">" + xml + "</" + className + ">";
        return xml;
    }

    public SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
        return message;
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public String getEncrptPass(String spId, String pass, String timeStamp) {

        String encodedPass = MD5(spId+pass+timeStamp);
        return encodedPass;
    }

    public String convertPOJOtoString(Object object){
        Gson gson = new Gson();
        String value = gson.toJson(object);
        return value;
    }

    public String convertMD5(String spId, String password, String timeStamp) {
        String value = spId + password + timeStamp;
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(value.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (Exception ex) {
            return "";
        }
    }

    public String getTargetUri(ApiModel apiModel) {
        //ApiModel apiModel = apiService.getApiModelByName(contextName);
        String baseUrl = headerService.getValue(BASEURL);
        String relativePath = apiModel.getUri();
        String url = baseUrl.trim()+relativePath.trim();
        return url.trim();
    }

    public String getBasicAuth() {
       String username = headerService.getValue("API_USER");
       String password = headerService.getValue("API_PASSWORD");
       String authentication = "Basic "+Base64.getEncoder().encodeToString((username+":"+password).getBytes());
       return authentication;
    }

    public JSONObject createResponseObject(long code, String message, JSONObject jsonObject) {
        JSONObject response = new JSONObject();
        response.put("code", code);
        response.put("message", message);
        response.put("data", jsonObject);
        return response;
    }


     public void printCallingUrl(HttpServletRequest request) {
        String incoming = "Request coming from -> " + request.getRemoteAddr();
        showMessage(incoming);
        String message = request.getMethod().toString() + " -> " + request.getRequestURL().toString();
        showMessage(message);
    }

    public JSONObject createJsonResponse(long code, String message, String data) {
        JSONObject response = new JSONObject();
        response.put("code", code);
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    public Response createResponse(long code, String message, String cause){
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        response.setCause(cause);
        return response;
    }

    public ResponseStatus createResponseStatus(long code, String message, String cause, String expireTime){
        ResponseStatus response = new ResponseStatus();
        response.setCode(code);
        response.setMessage(message);
        response.setCause(cause);
        response.setExpireTime(expireTime);
        return response;
    }
    public JSONObject createResponse1(long code, String message, String data) {
        JSONObject response = new JSONObject();
        response.put("code", code);
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    public String createJsonToResponse(long code, String message, String cause){
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        response.setCause(cause);
        return new GsonBuilder().create().toJson(response);
    }


    public Response createResponse(long code, String message, String cause, String data){
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        response.setCause(cause);
//        response.setData(data);
        return response;
    }
    public String getTravelTime(long previousTime) {
        long currentTime = System.currentTimeMillis();
        long travelTime = currentTime - previousTime;
        return String.valueOf(travelTime);
    }
    public LogModel saveLog(String platfromType ,String moduleName, HttpServletRequest httpRequest,String interfaceName , String status, JSONObject request, JSONObject response, long startTime){
        LogModel log = new LogModel();
        log.setPlatformType(platfromType);
        log.setModuleName(moduleName);
        log.setType(httpRequest.getMethod().toString());
        log.setInterfaceName(interfaceName);
        log.setEndpoint(httpRequest.getRequestURL().toString());
        log.setStatus(status);
        log.setRequest(request.toString());
        log.setResponse(response.toString());
        log.setTravelTime(getTravelTime(startTime));
        return logService.addLog1(log);
    }

    public Response createResponse(long code, String message,String data, JsonNode jsonNode){
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
//        response.setData(data);
//        response.setJsonNode(jsonNode);
        return response;
    }


    public void logAdd(String platformType, String moduleName, String type, String interfaceName, String req,
                        String res, String trvlTime, String status) {
        String endPoint = headerService.getValue("ENDPOINT");
        LogModel log = new LogModel();
        log.setPlatformType(platformType);
        log.setModuleName(moduleName);
        log.setType(type);
        log.setEndpoint(endPoint);
        log.setInterfaceName(interfaceName);
        log.setRequest(req);
        if(res == null)
            res = "API calling error";
        log.setResponse(res);
        log.setTravelTime(trvlTime);
        log.setStatus(status);
        logService.addLog(log);
    }

    public JSONObject createResponseArray(long code, String message, JSONArray jsonArray) {
        JSONObject response = new JSONObject();
        response.put("code", code);
        response.put("message", message);
        response.put("data", jsonArray);
        return response;
    }

    public String cdrFormat(NotifySms notifySms) {
        StringBuffer cdr = new StringBuffer();
        if(notifySms.getPortfolioModel() == null)
            cdr.append("null");
        else
            cdr.append(notifySms.getPortfolioModel().getId());
        cdr.append("|");
        cdr.append(notifySms.getCorrelator());
        cdr.append("|");
        if(notifySms.getBaseModel() == null)
            cdr.append("null");
        else
            cdr.append(notifySms.getBaseModel().getId());
        cdr.append("|");
        cdr.append(notifySms.getDateTime());
        cdr.append("|");
        cdr.append(notifySms.getLinkid());
        cdr.append("|");
        cdr.append(notifySms.getMessage());
        cdr.append("|");
        cdr.append(notifySms.getSmsServiceActivationNumber());
        cdr.append("|");
        cdr.append(notifySms.getTraceUniqueID());
        cdr.append("|");
        cdr.append(notifySms.getType());
        cdr.append("\n");
        return  cdr.toString();
    }
    public String cdrFormat(SmsLogModel smslog) {
        StringBuffer cdr = new StringBuffer();
        cdr.append(smslog.getOperationMode());
        cdr.append("|");
        if(smslog.getBaseModel() == null)
            cdr.append("null");
        else
            cdr.append(smslog.getBaseModel().getId());
        cdr.append("|");
        cdr.append(smslog.getCorrelator());
        cdr.append("|");
        if(smslog.getPortfolioModel() == null)
            cdr.append("null");
        cdr.append(smslog.getPortfolioModel().getId());
        cdr.append("|");
        cdr.append(smslog.getDeliveryStatus());
        cdr.append("|");
        cdr.append(smslog.getEndpoint());
        cdr.append("|");
        cdr.append(smslog.getInterfaceName());
        cdr.append("|");
        cdr.append(smslog.getRequestIdentifier());
        cdr.append("|");
        cdr.append(smslog.getMessage());
        cdr.append("\n");
        return cdr.toString();
    }
    public String responseFormat(String res) {
        return "Response -> " + res;
    }


}
