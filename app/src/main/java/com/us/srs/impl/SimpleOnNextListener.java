package com.us.srs.impl;


public class SimpleOnNextListener<T> implements SubscriberOnNextListener<T> {
    @Override
    public void OnFailure(int code, String msg) {
    }
    @Override
    public void OnSuccess(T t) {
    }

}
