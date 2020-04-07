package com.us.srs.net.exception;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;


public class CustomException extends RuntimeException {
    private Throwable e;

    public CustomException(Throwable e) {
        super(e);
        this.e = e;
    }

    public ApiException getError() {
        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            if(ApiErrorCode.errorPassCode==apiException.getErrorCode())
                apiException.setErrorMsg("密码错误");
            return apiException;
        } else {
            return null;
        }
    }

    public String getErrorMessage() {
        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            if (exception.code() > 500) {
                return "服务器异常";
            } else {
                return "网络异常,请检查网络设置";
            }
        } else if (e instanceof SocketTimeoutException) {
            return "网络超时";
        } else if (e instanceof UnknownHostException) {
            return "网络异常,请检查网络设置";
        } else if (e instanceof JsonParseException
                || e instanceof JSONException) {
            return "服务器异常";
        } else if (e instanceof ConnectException) {
            return "网络异常,请检查网络设置";
        }
        return null;
    }
}
