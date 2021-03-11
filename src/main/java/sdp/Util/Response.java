package sdp.Util;

import com.fasterxml.jackson.databind.JsonNode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Hp on 11/28/2017.
 */
public class Response {

    long code;
    String message;
    String cause;

    public Response() {
    }


    public Response(long code, String message, String cause) {
        this.code = code;
        this.message = message;
        this.cause = cause;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
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
