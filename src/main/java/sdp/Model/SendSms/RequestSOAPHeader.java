package sdp.Model.SendSms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Hp on 11/2/2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = RequestSOAPHeader.AUTH_NS, name = "RequestSOAPHeader")
public class RequestSOAPHeader {

    public static final String AUTH_NS ="http://www.huawei.com.cn/schema/common/v2_1";

    @XmlElement(namespace = AUTH_NS)
    private String spId;
    @XmlElement(namespace = AUTH_NS)
    private String spPassword;
    @XmlElement(namespace = AUTH_NS)
    private String serviceId;
    @XmlElement(namespace = AUTH_NS)
    private String timeStamp;
    @XmlElement(namespace = AUTH_NS)
    private String OA;
    @XmlElement(namespace = AUTH_NS)
    private String FA;

    public RequestSOAPHeader() {
    }

    public RequestSOAPHeader(String spId, String spPassword, String serviceId, String timeStamp, String OA, String FA, String token) {
        this.spId = spId;
        this.spPassword = spPassword;
        this.serviceId = serviceId;
        this.timeStamp = timeStamp;
        this.OA = OA;
        this.FA = FA;
    }

    public RequestSOAPHeader(String spId, String spPassword, String serviceId, String timeStamp) {
        this.spId = spId;
        this.spPassword = spPassword;
        this.serviceId = serviceId;
        this.timeStamp = timeStamp;
    }

    public RequestSOAPHeader(String spId, String spPassword, String serviceId, String timeStamp, String OA, String FA, String linkid, String presentid) {
        this.spId = spId;
        this.spPassword = spPassword;
        this.serviceId = serviceId;
        this.timeStamp = timeStamp;
        this.OA = OA;
        this.FA = FA;
    }

    public static String getAuthNs() {
        return AUTH_NS;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }


    public String getSpPassword() {
        return spPassword;
    }

    public void setSpPassword(String spPassword) {
        this.spPassword = spPassword;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getOA() {
        return OA;
    }

    public void setOA(String OA) {
        this.OA = OA;
    }

    public String getFA() {
        return FA;
    }

    public void setFA(String FA) {
        this.FA = FA;
    }

}

