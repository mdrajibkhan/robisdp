package sdp.Util;

/**
 * Created by Atiq on 5/23/2018.
 */
public class ResponseStatus {

    long code;
    String message;
    String cause;
    String expireTime;

    public ResponseStatus() {
    }


    public ResponseStatus(long code, String message, String cause, String expireTime) {
        this.code = code;
        this.message = message;
        this.cause = cause;
        this.expireTime = expireTime;
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

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
}
