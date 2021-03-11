package sdp.Model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Atiq on 11/26/2017.
 */
@Entity
@Table(name = "tbl_sms_notification")
public class StartSmsNotificationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @ManyToOne
    private PortfolioModel portfolioModel;
    @Column(name = "interface_name")
    private String interfaceName;
    @Column(name = "correlator")
    private String correlator;
    @Column(name = "status")
    private int status;
    @Column(name = "endpoint")
    private String endpoint;
    @Column(name = "sms_service_activation_num")
    private String smsServiceActivationNumber;
    @Column(name = "criteria")
    private String criteria;
    @Column(name = "created_at")
    private Timestamp created_at;
    @Column(name = "updated_at")
    private Timestamp updated_at;

    public StartSmsNotificationModel() {
    }

    public StartSmsNotificationModel(PortfolioModel portfolioModel, String interfaceName, String correlator, int status, String endpoint, String smsServiceActivationNumber, String criteria, Timestamp created_at, Timestamp updated_at) {
        this.portfolioModel = portfolioModel;
        this.interfaceName = interfaceName;
        this.correlator = correlator;
        this.status = status;
        this.endpoint = endpoint;
        this.smsServiceActivationNumber = smsServiceActivationNumber;
        this.criteria = criteria;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getSmsServiceActivationNumber() {
        return smsServiceActivationNumber;
    }

    public void setSmsServiceActivationNumber(String smsServiceActivationNumber) {
        this.smsServiceActivationNumber = smsServiceActivationNumber;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
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
