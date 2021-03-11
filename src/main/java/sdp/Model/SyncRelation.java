package sdp.Model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Hp on 10/31/2017.
 */
@Entity
@Table(name = "tbl_sync_relation")
public class SyncRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;
    @ManyToOne
    BaseModel baseModel;
    @Column(name = "partner_id")
    String partnerId;
    @Column(name = "product_id")
    String productId;
    @Column(name = "service_id")
    String serviceId;
    @Column(name = "service_list")
    String serviceList;
    @Column(name = "update_type")
    long updateType;
    @Column(name = "update_time")
    String updateTime;
    @Column(name = "update_desc")
    String updateDesc;
    @Column(name = "effective_time")
    String effectiveTime;
    @Column(name = "expiry_time")
    String expiryTime;
    @Column(name = "access_code")
    String accessCode;
    @ManyToOne
    ChargeModeModel chargeModeModel;
    @ManyToOne
    ExpirationModeModel expirationModeModel;
    @ManyToOne
    ObjectTypeModel objectTypeModel;
    @Column(name = "free_period")
    boolean freePeriod;
    @Column(name = "pay_type")
    long payType;
    @Column(name = "transaction_id")
    String transactionId;
    @Column(name = "order_key")
    String orderKey;
    @Column(name = "keyword")
    String keyword;
    @Column(name = "cycle_end_time")
    String cycleEndTime;
    @Column(name = "duration_of_grace")
    String durationOfGrace;
    @ManyToOne
    UpdateTypeModel updateTypeModel;
    @Column(name = "service_availbility")
    String serviceAvailbility;
    @ManyToOne
    ChannelModel channelModel;
    @Column(name = "trace_id")
    String traceId;
    @ManyToOne
    LanguageModel languageModel;
    @Column(name = "rent_success")
    boolean rentSuccess;
    @Column(name = "try")
    boolean tryValue;
    @Column(name = "short_message")
    String shortMessage;
    @Column(name = "created_at")
    Timestamp createdAt;
    @Column(name = "updated_at")
    Timestamp updatedAt;
    @Column(name = "update_reason")
    String updateReason;
    public SyncRelation() {
    }


    public SyncRelation(long id){
        this.id = id;
    }



    public SyncRelation(BaseModel baseModel, String partnerId, String productId, String serviceId, String serviceList, long updateType, String updateTime, String updateDesc, String effectiveTime, String expiryTime, String accessCode, ChargeModeModel chargeModeModel, ExpirationModeModel expirationModeModel, ObjectTypeModel objectTypeModel, boolean freePeriod, long payType, String transactionId, String orderKey, String keyword, String cycleEndTime, String durationOfGrace, UpdateTypeModel updateTypeModel, String serviceAvailbility, ChannelModel channelModel, String traceId, LanguageModel languageModel, boolean rentSuccess, boolean tryValue, String shortMessage, Timestamp createdAt, Timestamp updatedAt,String updateReason) {
        this.baseModel = baseModel;
        this.partnerId = partnerId;
        this.productId = productId;
        this.serviceId = serviceId;
        this.serviceList = serviceList;
        this.updateType = updateType;
        this.updateTime = updateTime;
        this.updateDesc = updateDesc;
        this.effectiveTime = effectiveTime;
        this.expiryTime = expiryTime;
        this.accessCode = accessCode;
        this.chargeModeModel = chargeModeModel;
        this.expirationModeModel = expirationModeModel;
        this.objectTypeModel = objectTypeModel;
        this.freePeriod = freePeriod;
        this.payType = payType;
        this.transactionId = transactionId;
        this.orderKey = orderKey;
        this.keyword = keyword;
        this.cycleEndTime = cycleEndTime;
        this.durationOfGrace = durationOfGrace;
        this.updateTypeModel = updateTypeModel;
        this.serviceAvailbility = serviceAvailbility;
        this.channelModel = channelModel;
        this.traceId = traceId;
        this.languageModel = languageModel;
        this.rentSuccess = rentSuccess;
        this.tryValue = tryValue;
        this.shortMessage = shortMessage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.updateReason=updateReason;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BaseModel getBaseModel() {
        return baseModel;
    }

    public void setBaseModel(BaseModel baseModel) {
        this.baseModel = baseModel;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceList() {
        return serviceList;
    }

    public void setServiceList(String serviceList) {
        this.serviceList = serviceList;
    }

    public long getUpdateType() {
        return updateType;
    }

    public void setUpdateType(long updateType) {
        this.updateType = updateType;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateDesc() {
        return updateDesc;
    }

    public void setUpdateDesc(String updateDesc) {
        this.updateDesc = updateDesc;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public ChargeModeModel getChargeModeModel() {
        return chargeModeModel;
    }

    public void setChargeModeModel(ChargeModeModel chargeModeModel) {
        this.chargeModeModel = chargeModeModel;
    }

    public ExpirationModeModel getExpirationModeModel() {
        return expirationModeModel;
    }

    public void setExpirationModeModel(ExpirationModeModel expirationModeModel) {
        this.expirationModeModel = expirationModeModel;
    }

    public ObjectTypeModel getObjectTypeModel() {
        return objectTypeModel;
    }

    public void setObjectTypeModel(ObjectTypeModel objectTypeModel) {
        this.objectTypeModel = objectTypeModel;
    }

    public boolean isFreePeriod() {
        return freePeriod;
    }

    public void setFreePeriod(boolean freePeriod) {
        this.freePeriod = freePeriod;
    }

    public long getPayType() {
        return payType;
    }

    public void setPayType(long payType) {
        this.payType = payType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCycleEndTime() {
        return cycleEndTime;
    }

    public void setCycleEndTime(String cycleEndTime) {
        this.cycleEndTime = cycleEndTime;
    }

    public String getDurationOfGrace() {
        return durationOfGrace;
    }

    public void setDurationOfGrace(String durationOfGrace) {
        this.durationOfGrace = durationOfGrace;
    }

    public UpdateTypeModel getUpdateTypeModel() {
        return updateTypeModel;
    }

    public void setUpdateTypeModel(UpdateTypeModel updateTypeModel) {
        this.updateTypeModel = updateTypeModel;
    }

    public String getServiceAvailbility() {
        return serviceAvailbility;
    }

    public void setServiceAvailbility(String serviceAvailbility) {
        this.serviceAvailbility = serviceAvailbility;
    }

    public ChannelModel getChannelModel() {
        return channelModel;
    }

    public void setChannelModel(ChannelModel channelModel) {
        this.channelModel = channelModel;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public LanguageModel getLanguageModel() {
        return languageModel;
    }

    public void setLanguageModel(LanguageModel languageModel) {
        this.languageModel = languageModel;
    }

    public boolean isRentSuccess() {
        return rentSuccess;
    }

    public void setRentSuccess(boolean rentSuccess) {
        this.rentSuccess = rentSuccess;
    }

    public boolean isTryValue() {
        return tryValue;
    }

    public void setTryValue(boolean tryValue) {
        this.tryValue = tryValue;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdateReason() {
        return updateReason;
    }

    public void setUpdateReason(String updateReason) {
        this.updateReason = updateReason;
    }
}
