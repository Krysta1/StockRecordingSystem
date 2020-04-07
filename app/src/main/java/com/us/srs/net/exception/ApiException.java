package com.us.srs.net.exception;


public class ApiException extends RuntimeException {
    private int errorCode;
    private String errorMsg;
    public int getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public ApiException(int code, String msg) {
        super(msg);
        this.errorCode = code;
        this.errorMsg=msg;
    }
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
