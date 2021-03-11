package sdp.Model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Hp on 11/21/2017.
 */
@Entity
@Table(name = "tbl_header")
public class HeaderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;
    @Column(name = "key_name")
    String key;
    @Column(name = "key_value")
    String value;
    @Column(name = "created_at")
    Timestamp createdAt;
    @Column(name = "updated_at")
    Timestamp updatedAt;

    public HeaderModel() {
    }

    public HeaderModel(long id){
        this.id = id;
    }

    public HeaderModel(String key, String value, Timestamp createdAt, Timestamp updatedAt) {
        this.key = key;
        this.value = value;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
