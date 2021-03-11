package sdp.Model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Hp on 11/26/2017.
 */

@Entity
@Table(name = "tbl_subscribe_product")
public class SubscribeProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;

    @ManyToOne
    BaseModel baseModel;

    @ManyToOne
    BaseTypeModel baseTypeModel;

    @Column(name = "product_id")
    String productId;

    @Column(name = "oper_code")
    String operCode;

    @Column(name = "is_auto_extend")
    String isAutoExtend;



    @Column(name = "channel_model_id")
    String channelModel;



    @Column(name = "result")
    String result;

    @Column(name = "type")
    String type;
    @Column(name = "created_at")
    Timestamp createdAt;
    @Column(name = "update_at")
    Timestamp updatedAt;

    public SubscribeProductModel() {
    }

    public SubscribeProductModel(long id){
        this.id = id;
    }

    public SubscribeProductModel(BaseModel baseModel,BaseTypeModel baseTypeModel ,String  productId,String operCode,String isAutoExtend,String channelModel,String result,String type, Timestamp createdAt, Timestamp updatedAt) {
        this.baseModel=baseModel;
       this.baseTypeModel=baseTypeModel;
        this.productId=productId;
        this.operCode=operCode;
        this .isAutoExtend=isAutoExtend;
        this.channelModel=channelModel;

        this.result=result;
        this.type=type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public BaseTypeModel getBaseTypeModel() {
        return baseTypeModel;
    }

    public void setBaseTypeModel(BaseTypeModel baseTypeModel) {
        this.baseTypeModel = baseTypeModel;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOperCode() {
        return operCode;
    }

    public void setOperCode(String operCode) {
        this.operCode = operCode;
    }

    public String getIsAutoExtend() {
        return isAutoExtend;
    }

    public void setIsAutoExtend(String isAutoExtend) {
        this.isAutoExtend = isAutoExtend;
    }

    public String getChannelModel() {
        return channelModel;
    }

    public void setChannelModel(String channelModel) {
        this.channelModel = channelModel;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
