package sdp.Endpoints;

import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import sdp.Model.BaseModel;
import sdp.Model.BaseTypeModel;
import sdp.Model.Header.RequestSOAPHeader;
import sdp.Model.SyncRelation;
import sdp.Service.*;
import sdp.Util.Error.ServiceException;
import sdp.Util.Error.ServiceFaultException;
import sdp.Util.KeyWord;
import sdp.Util.Utility;
import sdp.Xsd.SyncRelation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;


/**
 * Created by Hp on 10/31/2017.
 */

/**
    * FOLLOWING DataSyncEndpoints Class ARE GENERATED FOR
    * =================================
    * Documentation -> SDP Solution API Reference (DataSync, SOAP)
    * Impact -> Synchronize subscription relation
    *
    * */

 @Endpoint
public class DataSyncEndpoints {

    @Autowired
    BaseService baseService;
    @Autowired
    BaseTypeService baseTypeService;
    @Autowired
    ChargeModeService chargeModeService;
    @Autowired
    ExpirationModeService expirationModeService;
    @Autowired
    ObjectTypeService objectTypeService;
    @Autowired
    UpdateTypeService updateTypeService;
    @Autowired
    ChannelService channelService;
    @Autowired
    LanguageService languageService;
    @Autowired
    SyncRelationService syncRelationService;
    @Autowired
    Utility utility;

    /*
    * Following Variable and Methods are used for
    * getting HttpServletRequest properties by
    * using JX-WS configuration
    * */
    private HttpServletRequest httpServletRequest;
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PROVIDER = "PROVIDER";

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.httpServletRequest = request;
    }


    private static final String syncOrderRelationNS = "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local";

    /**
     * FOLLOWING METHOD IS GENERATED FOR
     * =================================
     * Documentation -> SDP Solution API Reference (DataSync, SOAP)
     * Content Number -> [2.2 SyncOrderRelation]
     * Base URL -> http://ip:port/robisdp
     * Namespace -> http://www.csapi.org/schema/parlayx/data/sync/v1_0/local
     * Local Part -> syncOrderRelation
     * Impact -> synchronizing subscription relation which means manipulate data of subscribe and unsubscribe user.
     * */
    @PayloadRoot(namespace = syncOrderRelationNS, localPart = "syncOrderRelation")
    @ResponsePayload
    public SyncOrderRelationResponse syncOrderRelationRequest(@RequestPayload SyncOrderRelation request) throws ServiceFaultException {
        utility.printCallingUrl(this.httpServletRequest, utility.convertPOJOtoXml(request));
        ObjectFactory factory = new ObjectFactory();
        SyncOrderRelationResponse response = factory.createSyncOrderRelationResponse();
        String logReq = utility.convertPOJOtoXml(request);
        String logRes = "";
        try {
            SyncRelation syncRelation = new SyncRelation();
            BaseModel baseModel = baseService.isBaseExists(request.getUserID().getID());
            if (baseModel == null) {
                baseModel = new BaseModel();
                baseModel.setMdn(request.getUserID().getID());
                BaseTypeModel baseTypeModel = baseTypeService.getBaseTypeByCode(Long.parseLong(request.getUserID().getType()));
                baseModel.setBaseTypeModel(baseTypeModel);
                long lastId = baseService.getLastId();
                long insertId = baseService.addBase(baseModel);
                if (insertId > lastId) {
                    syncRelation.setBaseModel(new BaseModel(insertId));
                }
            } else {
                syncRelation.setBaseModel(baseModel);
            }
            syncRelation.setPartnerId(request.getSpID());
            syncRelation.setProductId(request.getProductID());
            syncRelation.setServiceId(request.getServiceID());
            syncRelation.setServiceList(request.getServiceList());
            syncRelation.setUpdateType(Long.parseLong(request.getUpdateType()));
            syncRelation.setUpdateTime(request.getUpdateTime());
            syncRelation.setUpdateDesc(request.getUpdateDesc());
            syncRelation.setEffectiveTime(request.getEffectiveTime());
            syncRelation.setExpiryTime(request.getExpiryTime());
            ExtenseInfo extenseInfo = request.getExtensionInfo();
            for (int i = 0; i < extenseInfo.getItem().size(); i++) {
                Keyvalue keyvalue = extenseInfo.getItem().get(i);
                syncRelation = setData(syncRelation, keyvalue);
            }
            long lastId = syncRelationService.getLastId();
            utility.logToCDR(cdrFormat(syncRelation),"synRelation");
            long insertId = syncRelationService.addSyncRelation(syncRelation);
            if (insertId > lastId) {
                utility.showMessage("syncRelation added with => " + new GsonBuilder().create().toJson(syncRelation));
                response.setResult("0");
                response.setResultDescription("Ok");
            } else {
                response.setResult("-1");
                response.setResultDescription("Not Ok");
            }

            logRes = utility.convertPOJOtoXml(response);
            utility.logAdd(KeyWord.PRODUCTION, "DataSync",PROVIDER,"syncOrderRelation",logReq,logRes,"","200");
            utility.responseFormat(logRes);
            return response;
        } catch (Exception ex) {
            utility.responseFormat(ex.toString());
            utility.logAdd(KeyWord.PRODUCTION, "DataSync",PROVIDER,"syncOrderRelation",logReq,ex.toString(),"","500");
            throw new ServiceFaultException("ERROR", new ServiceException("500", ex.getMessage(), ex.toString()));
        }

    }

    public RequestSOAPHeader getAuthentication(SoapHeaderElement header) {
        RequestSOAPHeader authentication = null;
        try {

            JAXBContext context = JAXBContext.newInstance(RequestSOAPHeader.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            authentication = (RequestSOAPHeader) unmarshaller.unmarshal(header.getSource());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return authentication;
    }


    public SyncRelation setData(SyncRelation syncRelation, Keyvalue keyvalue) {
        switch (keyvalue.getKey()) {
            case "updateReason":
                syncRelation.setUpdateReason(keyvalue.getValue());
                break;
            case "accessCode":
                syncRelation.setAccessCode(keyvalue.getValue());
                break;
            case "chargeMode":
                syncRelation.setChargeModeModel(chargeModeService.getChargeModeByCode(keyvalue.getValue()));
                break;
            case "MDSPSUBEXPMODE":
                syncRelation.setExpirationModeModel(expirationModeService.getExpirationModeByCode(keyvalue.getValue()));
                break;
            case "objectType":
                syncRelation.setObjectTypeModel(objectTypeService.getObjectTypeByCode(keyvalue.getValue()));
                break;
            case "isFreePeriod":
                syncRelation.setFreePeriod(Boolean.parseBoolean(keyvalue.getValue()));
                break;
            case "payType":
                syncRelation.setPayType(Long.parseLong(keyvalue.getValue()));
                break;
            case "transactionID":
                syncRelation.setTransactionId(keyvalue.getValue());
                break;
            case "orderKey":
                syncRelation.setOrderKey(keyvalue.getValue());
                break;
            case "keyword":
                syncRelation.setKeyword(keyvalue.getValue());
                break;
            case "cycleEndTime":
                syncRelation.setCycleEndTime(keyvalue.getValue());
                break;
            case "durationOfGracePeriod":
                syncRelation.setDurationOfGrace(keyvalue.getValue());
                break;
            case "updateType":
                syncRelation.setUpdateTypeModel(updateTypeService.getUpdateTypeByCode(keyvalue.getValue()));
                break;
            case "serviceAvailability":
                syncRelation.setServiceAvailbility(keyvalue.getValue());
                break;
            case "channelID":
                syncRelation.setChannelModel(channelService.getChannelByCode(keyvalue.getValue()));
                break;
            case "TraceUniqueID":
                syncRelation.setTraceId(keyvalue.getValue());
                break;
            case "operCode":
                syncRelation.setLanguageModel(languageService.getLanguageByTitle(keyvalue.getValue()));
                break;
            case "rentSuccess":
                syncRelation.setRentSuccess(Boolean.parseBoolean(keyvalue.getValue()));
                break;
            case "try":
                syncRelation.setTryValue(Boolean.parseBoolean(keyvalue.getValue()));
                break;
            case "shortMessage":
                syncRelation.setShortMessage(keyvalue.getValue());
                break;
        }
        return syncRelation;
    }

    private String cdrFormat(SyncRelation dataSync) {
        StringBuffer cdr = new StringBuffer();
        if(dataSync.getBaseModel() == null)
            cdr.append("null");
        else
            cdr.append(dataSync.getBaseModel().getMdn());
        cdr.append("|");
        cdr.append(dataSync.getAccessCode());
        cdr.append("|");
        if(dataSync.getChannelModel() == null)
            cdr.append("null");
        else
            cdr.append(dataSync.getChannelModel().getCode());
        cdr.append("|");
        if(dataSync.getChargeModeModel() == null)
            cdr.append("null");
        else
            cdr.append(dataSync.getChargeModeModel().getCode());
        cdr.append("|");
        cdr.append(dataSync.getCycleEndTime());
        cdr.append("|");
        cdr.append(dataSync.getDurationOfGrace());
        cdr.append("|");
        cdr.append(dataSync.getEffectiveTime());
        cdr.append("|");
        cdr.append(dataSync.getKeyword());
        cdr.append("|");
        if(dataSync.getLanguageModel() == null)
            cdr.append("null");
        else
            cdr.append(dataSync.getLanguageModel().getTitle());
        cdr.append("|");
        if(dataSync.getExpirationModeModel() == null)
             cdr.append("null");
        else
            cdr.append(dataSync.getExpirationModeModel().getCode());
        cdr.append("|");
        cdr.append(dataSync.getOrderKey());
        cdr.append("|");
        cdr.append(dataSync.getPartnerId());
        cdr.append("|");
        cdr.append(dataSync.getPayType());
        cdr.append("|");
        cdr.append(dataSync.getProductId());
        cdr.append("|");
        cdr.append(dataSync.getServiceAvailbility());
        cdr.append("|");
        cdr.append(dataSync.getServiceId());
        cdr.append("|");
        cdr.append(dataSync.getServiceList());
        cdr.append("|");
        cdr.append(dataSync.getShortMessage());
        cdr.append("|");
        cdr.append(dataSync.getTransactionId());
        cdr.append("|");
        cdr.append(dataSync.getUpdateDesc());
        cdr.append("|");
        cdr.append(dataSync.getUpdateReason());
        cdr.append("|");
        if(dataSync.getUpdateTypeModel() == null)
            cdr.append("null");
        else
            cdr.append(dataSync.getUpdateTypeModel().getCode());
        cdr.append("\n");
        return cdr.toString();
    }
}
