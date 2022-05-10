package FacadeTemplate;

/**
 * @Author : leex
 * @Description : ResultDTO 2021/7/13 21:43 leex
 */
public class ResultDTO<T> {

    private boolean success;

    private T resultDate;

    private String errorCode;

    private String message;

    public ResultDTO(boolean success, T resultDate) {
        this.success = success;
        this.resultDate = resultDate;
    }

    public ResultDTO(boolean success, String errorCode, String message) {
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
    }

    public T getResultDate() {
        return resultDate;
    }

    public void setResultDate(T resultDate) {
        this.resultDate = resultDate;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
