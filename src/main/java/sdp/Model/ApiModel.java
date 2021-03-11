package sdp.Model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Atiq on 1/8/2018.
 */
@Entity
@Table(name = "tbl_api")
public class ApiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;
    @Column(name = "name")
    String name;
    @Column(name = "uri")
    String uri;
    @Column(name = "jaxb")
    String jaxb;
    @Column(name = "created_at")
    Timestamp createdAt;
    @Column(name = "updated_at")
    Timestamp updatedAt;

    public ApiModel() {

    }
    public ApiModel(long id) {
        this.id = id;
    }

    public ApiModel(String name, String uri, String jaxb, Timestamp createdAt, Timestamp updatedAt) {
        this.name = name;
        this.uri = uri;
        this.jaxb = jaxb;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getJaxb() {
        return jaxb;
    }

    public void setJaxb(String jaxb) {
        this.jaxb = jaxb;
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
