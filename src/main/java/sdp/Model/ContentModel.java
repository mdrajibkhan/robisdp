package sdp.Model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Hp on 11/26/2017.
 */
@Entity
@Table(name = "tbl_content")
public class ContentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;
    @ManyToOne
    PortfolioModel portfolioModel;
    @Column(name = "verbose")
    String verbose;
    @Column(name = "date_delivery")
    Timestamp dateDelivery;
    @Column(name = "date_expiry")
    Timestamp dateExpiry;
    @Column(name = "delivery_token")
    long deliveryToken;
    @Column(name = "created_at")
    Timestamp createdAt;
    @Column(name = "updated_at")
    Timestamp updatedAt;

    public ContentModel() {
    }

    public ContentModel(long id) {
        this.id = id;
    }

    public ContentModel(PortfolioModel portfolioModel, String verbose, Timestamp dateDelivery, Timestamp dateExpiry, long deliveryToken, Timestamp createdAt, Timestamp updatedAt) {
        this.portfolioModel = portfolioModel;
        this.verbose = verbose;
        this.dateDelivery = dateDelivery;
        this.dateExpiry = dateExpiry;
        this.deliveryToken = deliveryToken;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getVerbose() {
        return verbose;
    }

    public void setVerbose(String verbose) {
        this.verbose = verbose;
    }

    public Timestamp getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(Timestamp dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public Timestamp getDateExpiry() {
        return dateExpiry;
    }

    public void setDateExpiry(Timestamp dateExpiry) {
        this.dateExpiry = dateExpiry;
    }

    public long getDeliveryToken() {
        return deliveryToken;
    }

    public void setDeliveryToken(long deliveryToken) {
        this.deliveryToken = deliveryToken;
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
}
