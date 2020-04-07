package com.us.srs.net.observer;

import android.app.Activity;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.us.srs.impl.SubscriberOnNextListener;
import com.us.srs.net.exception.CustomException;
import com.us.srs.net.handle.ProgressDialogHandler;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class ProgressObserver<T> implements Observer<T> {
    private SubscriberOnNextListener mSubscriberOnNextListener;
    private Activity context;
    private ProgressDialogHandler progressDialogHandler;
    private Disposable disposable;

    public ProgressObserver(Activity context, SubscriberOnNextListener mSubscriberOnNextListener) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        progressDialogHandler = new ProgressDialogHandler(context, true);
    }

    public ProgressObserver<T> setProgressDialogHandlerStyle(KProgressHUD kProgressHUD) {
        progressDialogHandler.setProgressDialogStyle(kProgressHUD);
        return this;
    }
    @Override
    public void onError(Throwable e) {
        cancelSubscriber();
        progressDialogHandler.obtainMessage(progressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
        CustomException exception = new CustomException(e);
        String mesg = exception.getErrorMessage();
        if (exception.getError() != null && mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.OnFailure(exception.getError().getErrorCode(), exception.getError().getErrorMsg());
        }else {
            mSubscriberOnNextListener.OnFailure(-1,mesg);
        }
    }

    @Override
    public void onComplete() {
        progressDialogHandler.obtainMessage(progressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
        cancelSubscriber();
    }
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        progressDialogHandler.obtainMessage(progressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        disposable = d;
    }

    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            if (context.isFinishing())
                return;
            mSubscriberOnNextListener.OnSuccess(t);
        }
    }

    private void cancelSubscriber() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}