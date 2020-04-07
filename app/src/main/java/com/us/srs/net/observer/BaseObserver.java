package com.us.srs.net.observer;

import android.app.Activity;

import com.us.srs.impl.SubscriberOnNextListener;
import com.us.srs.net.exception.CustomException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class BaseObserver<T> implements Observer<T> {
    private Activity context;
    private SubscriberOnNextListener onNextListener;
    private Disposable disposable;

    public BaseObserver(Activity context, SubscriberOnNextListener onNextListener) {
        this.context = context;
        this.onNextListener = onNextListener;
    }

    private void cancelSubscriber() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        disposable = d;
    }
    @Override
    public void onNext(@NonNull T t) {
        if (onNextListener != null) {
            if (context.isFinishing())
                return;
            onNextListener.OnSuccess(t);
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        CustomException exception = new CustomException(e);
        if (exception.getError() != null && onNextListener != null) {
            onNextListener.OnFailure(exception.getError().getErrorCode(), exception.getError().getErrorMsg());
        }
        cancelSubscriber();
    }

    @Override
    public void onComplete() {
        cancelSubscriber();
    }
}
