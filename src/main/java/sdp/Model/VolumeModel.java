package sdp.Model;

import javax.persistence.*;

/**
 * Created by Hp on 11/27/2017.
 */
@Entity
@Table(name = "tbl_charge_volume")
public class VolumeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;
    @ManyToOne
    PortfolioModel portfolioModel;
    @ManyToOne
    BaseModel baseModel;
    @Column(name = "volume")
    String volume;
    @Column(name = "billing_text")
    String billingText;
    @Column(name = "reference_code")
    String referenceCode;
    @Column(name = "status")
    String status;

    public VolumeModel() {
    }

    public VolumeModel(long id) {
        this.id = id;
    }

    public VolumeModel(PortfolioModel portfolioModel, BaseModel baseModel, String volume, String billingText, String referenceCode, String status) {
        this.portfolioModel = portfolioModel;
        this.baseModel = baseModel;
        this.volume = volume;
        this.billingText = billingText;
        this.referenceCode = referenceCode;
        this.status = status;
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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getBillingText() {
        return billingText;
    }

    public void setBillingText(String billingText) {
        this.billingText = billingText;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
