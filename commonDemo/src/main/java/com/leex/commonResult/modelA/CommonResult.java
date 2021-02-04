package com.leex.commonResult.modelA;

/**
 * @Author : 86167
 * @Description : commonResult-2021/2/3 21:49-86167
 */
public class CommonResult<T> {

    /** 是否成功 */
    private Boolean success;

    /** 错误码 */
    private String errorCode;

    /** 错误信息 */
    private String errorDesp;

    /** 返回值信息 */
    private T result;

    public CommonResult(T result) {
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesp() {
        return errorDesp;
    }

    public void setErrorDesp(String errorDesp) {
        this.errorDesp = errorDesp;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
