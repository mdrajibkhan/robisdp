package sdp.Model;

import javax.persistence.*;
import java.security.Timestamp;
import java.sql.Time;

/**
 * Created by Atiq on 12/5/2017.
 */
@Entity
@Table(name = "tbl_log")
public class LogModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "platform_type")
    private String platformType;
    @Column(name = "module_name")
    private String moduleName;
    @Column(name = "type")
    private String type;
    @Column(name = "endpoint")
    private String endpoint;
    @Column(name = "interface")
    private String interfaceName;
    @Column(name = "request")
    private String request;
    @Column(name = "response")
    private  String response;
    @Column(name = "travel_time")
    private  String travelTime;
    @Column(name = "status")
    private  String status;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public LogModel() {
    }

    public LogModel(Long id) {
        this.id = id;
    }

    public LogModel(String platformType ,String moduleName, String type, String endpoint, String interfaceName, String request, String response, String travelTime, String status, Timestamp createdAt, Timestamp updatedAt) {
        this.platformType = platformType;
        this.moduleName = moduleName;
        this.type = type;
        this.endpoint = endpoint;
        this.interfaceName = interfaceName;
        this.request = request;
        this.response = response;
        this.travelTime = travelTime;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
