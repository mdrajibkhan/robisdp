package sdp.Model;

import javax.persistence.*;

/**
 * Created by Hp on 11/27/2017.
 */
@Entity
@Table(name = "tbl_reservation")
public class ReservationModel {

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
    @Column(name = "result")
    String result;
    @Column(name = "reference")
    String reference;
    @Column(name = "status")
    String status;

    public ReservationModel() {
    }

    public ReservationModel(long id) {
        this.id = id;
    }

    public ReservationModel(PortfolioModel portfolioModel, BaseModel baseModel, String description, String currency, String amount, String code, String result, String reference, String status) {
        this.portfolioModel = portfolioModel;
        this.baseModel = baseModel;
        this.description = description;
        this.currency = currency;
        this.amount = amount;
        this.code = code;
        this.result = result;
        this.reference = reference;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
