package sdp.Model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Hp on 11/5/2017.
 */

@Entity
@Table(name = "tbl_notify_sms")
public class NotifySms {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;
    @ManyToOne
    PortfolioModel portfolioModel;
    @Column(name = "linkid")
    String linkid;
    @Column(name = "trace_unique_id")
    String traceUniqueID;
    @Column(name = "correlator")
    String correlator;
    @Column(name = "message")
    String message;
    @ManyToOne
    BaseModel base;
    @Column(name = "sms_service_activation_number")
    String smsServiceActivationNumber;
    @Column(name = "date_time")
    String dateTime;
    @Column(name = "operation_mode")
    String type;
    @Column(name = "created_at")
    Timestamp created_at;
    @Column(name = "updated_at")
    Timestamp updated_at;


    public NotifySms() {
    }

    public NotifySms(long id) {
        this.id = id;
    }


    public NotifySms(PortfolioModel portfolioModel, String linkid, String traceUniqueID, String correlator, String message, BaseModel base, String smsServiceActivationNumber, String dateTime,String type, Timestamp created_at, Timestamp updated_at) {
        this.portfolioModel = portfolioModel;
        this.linkid = linkid;
        this.traceUniqueID = traceUniqueID;
        this.correlator = correlator;
        this.message = message;
        this.base = base;
        this.smsServiceActivationNumber = smsServiceActivationNumber;
        this.dateTime = dateTime;
        this.type=type;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public PortfolioModel getPortfolioModel() {
        return portfolioModel;
    }

    public void setPortfolioModel(PortfolioModel portfolioModel) {
        this.portfolioModel = portfolioModel;

    }

    public String getLinkid() {
        return linkid;
    }

    public void setLinkid(String linkid) {
        this.linkid = linkid;
    }

    public String getTraceUniqueID() {
        return traceUniqueID;
    }

    public void setTraceUniqueID(String traceUniqueID) {
        this.traceUniqueID = traceUniqueID;
    }

    public String getCorrelator() {
        return correlator;
    }

    public void setCorrelator(String correlator) {
        this.correlator = correlator;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public BaseModel getBaseModel() {
        return base;
    }

    public void setBaseModel(BaseModel baseModel) {
        this.base = baseModel;

    }

    public String getSmsServiceActivationNumber() {
        return smsServiceActivationNumber;
    }

    public void setSmsServiceActivationNumber(String smsServiceActivationNumber) {
        this.smsServiceActivationNumber = smsServiceActivationNumber;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BaseModel getBase() {
        return base;
    }

    public void setBase(BaseModel base) {
        this.base = base;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }


}
