package sdp.Model.Header;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Hp on 11/2/2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = NotifySOAPHeader.AUTH_NS, name = "NotifySOAPHeader")
public class NotifySOAPHeader {

    public static final String AUTH_NS = "http://www.huawei.com.cn/schema/common/v2_1";

    @XmlElement(namespace = AUTH_NS)
    private String spRevId;
    @XmlElement(namespace = AUTH_NS)
    private String spRevpassword;
    @XmlElement(namespace = AUTH_NS)
    private String spId;
    @XmlElement(namespace = AUTH_NS)
    private String serviceId;
    @XmlElement(namespace = AUTH_NS)
    private String timeStamp;
    @XmlElement(namespace = AUTH_NS)
    private String linkid;
    @XmlElement(namespace = AUTH_NS)
    private String traceUniqueID;

    public NotifySOAPHeader() {
    }

    public NotifySOAPHeader(String spRevId, String spRevPassword, String spId, String serviceId, String timeStamp, String linkid, String traceUniqueID) {

        this.spRevId = spRevId;
        this.spRevpassword = spRevpassword;
        this.spId = spId;
        this.serviceId = serviceId;
        this.timeStamp = timeStamp;
        this.linkid = linkid;
        this.traceUniqueID = traceUniqueID;
    }

    public static String getAuthNs() {
        return AUTH_NS;
    }


    public String getSpRevId() {
        return spRevId;
    }

    public void setSpRevId(String spRevId) {
        this.spRevId = spRevId;
    }

    public String getSpRevPassword() {
        return spRevpassword;
    }

    public void setSpRevPassword(String spRevPassword) {
        this.spRevpassword = spRevPassword;
    }

    public String getLinkid() {
        return linkid;
    }

    public void setLinkid(String linkid) {
        this.linkid = linkid;
    }

    public String getTraceUniqueID() {
        return traceUniqueID;
    }

    public void setTraceUniqueID(String traceUniqueID) {
        this.traceUniqueID = traceUniqueID;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
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
}
