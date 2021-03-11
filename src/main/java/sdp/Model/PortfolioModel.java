package sdp.Model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Atiq on 11/22/2017.
 */
@Entity
@Table(name = "tbl_portfolio")
public class PortfolioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "type")
    private String type;
    @Column(name = "service_id")
    private String serviceId;
    @Column(name = "sp_id")
    private String spId;
    @Column(name = "cp_name")
    private String cpName;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_id")
    private String productId;
    @Column(name = "access_code")
    private String accessCode;
    @Column(name = "short_code")
    private long shortCode;
    @Column(name = "usage_code")
    private long usage;
    @Column(name = "rent_fee")
    private long rentFee;
    @Column(name = "sub_charge")
    private long subCharge;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public PortfolioModel() {
    }

    public PortfolioModel(long id) {
        this.id = id;
    }


    public PortfolioModel(String serviceName, String type, String serviceId, String spId, String cpName, String productName, String productId, String accessCode, long shortCode, long usage, long rentFee, long subCharge, Timestamp createdAt, Timestamp updatedAt) {
        this.serviceName = serviceName;
        this.type = type;
        this.serviceId = serviceId;
        this.spId = spId;
        this.cpName = cpName;
        this.productName = productName;
        this.productId = productId;
        this.accessCode = accessCode;
        this.shortCode = shortCode;
        this.usage = usage;
        this.rentFee = rentFee;
        this.subCharge = subCharge;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public long getShortCode() {
        return shortCode;
    }

    public void setShortCode(long shortCode) {
        this.shortCode = shortCode;
    }

    public long getUsage() {
        return usage;
    }

    public void setUsage(long usage) {
        this.usage = usage;
    }

    public long getRentFee() {
        return rentFee;
    }

    public void setRentFee(long rentFee) {
        this.rentFee = rentFee;
    }

    public long getSubCharge() {
        return subCharge;
    }

    public void setSubCharge(long subCharge) {
        this.subCharge = subCharge;
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
