package sdp.Model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Hp on 11/26/2017.
 */
@Entity
@Table(name = "tbl_sms_log")
public class SmsLogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;
    @ManyToOne
    PortfolioModel  portfolioModel;
    @ManyToOne
    BaseModel baseModel;
    @Column(name = "message")
    String message;
    @Column(name = "endpoint")
    String endpoint;
    @Column(name = "interface_name")
    String interfaceName;
    @Column(name = "correlator")
    String correlator;
    @Column(name = "request_identifier")
    String requestIdentifier;
    @Column(name = "delivery_status")
    String deliveryStatus;
    @Column(name = "operation_mode")
    String operationMode;

    public SmsLogModel() {
    }

    public SmsLogModel(long id) {
        this.id = id;
    }

    public SmsLogModel(PortfolioModel portfolioModel, BaseModel baseModel, String message, String endpoint, String interfaceName, String correlator, String requestIdentifier, String deliveryStatus, String operationMode) {
        this.portfolioModel = portfolioModel;
        this.baseModel = baseModel;
        this.message = message;
        this.endpoint = endpoint;
        this.interfaceName = interfaceName;
        this.correlator = correlator;
        this.requestIdentifier = requestIdentifier;
        this.deliveryStatus = deliveryStatus;
        this.operationMode = operationMode;
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

    public BaseModel getBaseModel() {
        return baseModel;
    }

    public void setBaseModel(BaseModel baseModel) {
        this.baseModel = baseModel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getCorrelator() {
        return correlator;
    }

    public void setCorrelator(String correlator) {
        this.correlator = correlator;
    }

    public String getRequestIdentifier() {
        return requestIdentifier;
    }

    public void setRequestIdentifier(String requestIdentifier) {
        this.requestIdentifier = requestIdentifier;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getOperationMode() {
        return operationMode;
    }

    public void setOperationMode(String operationMode) {
        this.operationMode = operationMode;
    }
}
