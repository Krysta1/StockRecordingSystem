package com.us.srs.impl;

public interface SubscriberOnNextListener<T>{
    void OnSuccess(T t);
    void OnFailure(int code, String msg);
}
