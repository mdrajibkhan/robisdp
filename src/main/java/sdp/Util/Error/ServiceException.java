package sdp.Util.Error;

import javax.xml.bind.annotation.*;

/**
 * Created by Hp on 11/13/2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceException", propOrder = {
        "code",
        "message",
        "cause"
})
@XmlRootElement(namespace = "http://www.huawei.com.cn/schema/common/v2_1")
public class ServiceException {

    private String code;
    private String message;
    private String cause;

    public ServiceException() {
    }

    public ServiceException(String code, String message, String cause) {
        this.code = code;
        this.message = message;
        this.cause = cause;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
