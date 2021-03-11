package sdp.Model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Hp on 10/31/2017.
 */
@Entity
@Table(name = "tbl_charge_mode")
public class ChargeModeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;
    @Column(name = "code")
    String code;
    @Column(name = "title")
    String title;
    @Column(name = "created_at")
    Timestamp createdAt;
    @Column(name = "updated_at")
    Timestamp updatedAt;

    public ChargeModeModel() {
    }

    public ChargeModeModel(long id){
        this.id = id;
    }

    public ChargeModeModel(String code, String title, Timestamp createdAt, Timestamp updatedAt) {
        this.code = code;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
