package sdp.Model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Hp on 10/31/2017.
 */
@Entity
@Table(name = "tbl_base")
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;
    @Column(name = "mdn")
    String mdn;
    @Column(name = "vmdn")
    String vmdn;
    @ManyToOne
    BaseTypeModel baseTypeModel;

    @Column (name ="subscription_list")
    String subscriptionList;
    @Column(name = "created_at")
    Timestamp createdAt;
    @Column(name = "updated_at")
    Timestamp updatedAt;

    public BaseModel() {
    }

    public BaseModel(long id){
        this.id = id;
    }

    public BaseModel(String mdn, String vmdn, BaseTypeModel baseTypeModel,String subscriptionList, Timestamp createdAt, Timestamp updatedAt) {
        this.mdn = mdn;
        this.vmdn = vmdn;
        this.baseTypeModel = baseTypeModel;

        this.subscriptionList=subscriptionList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMdn() {
        return mdn;
    }

    public void setMdn(String mdn) {
        this.mdn = mdn;
    }

    public String getVmdn() {
        return vmdn;
    }

    public void setVmdn(String vmdn) {
        this.vmdn = vmdn;
    }

    public BaseTypeModel getBaseTypeModel() {
        return baseTypeModel;
    }

    public void setBaseTypeModel(BaseTypeModel baseTypeModel) {
        this.baseTypeModel = baseTypeModel;
    }



    public String getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(String subscriptionList) {
        this.subscriptionList = subscriptionList;
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
