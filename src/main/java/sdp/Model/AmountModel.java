package sdp.Model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Hp on 11/26/2017.
 */
@Entity
@Table(name = "tbl_charge_amount")
public class AmountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;
    @ManyToOne
    PortfolioModel portfolioModel;
    @ManyToOne
    BaseModel baseModel;
    @Column(name = "description")
    String description;
    @Column(name = "currency")
    String currency;
    @Column(name = "amount")
    String amount;
    @Column(name = "code")
    String code;
    @Column(name = "reference_code")
    String referenceCode;
    @Column(name = "status")
    String status;

    public AmountModel() {
    }

    public AmountModel(long id) {
        this.id = id;
    }

    public AmountModel(PortfolioModel portfolioModel, BaseModel baseModel, String description, String currency, String amount, String code, String referenceCode, String status) {
        this.portfolioModel = portfolioModel;
        this.baseModel = baseModel;
        this.description = description;
        this.currency = currency;
        this.amount = amount;
        this.code = code;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
