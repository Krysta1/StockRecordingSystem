package com.us.srs.net.handle;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.us.srs.utils.KprogresshudOptition;


public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;
    private KProgressHUD kProgressHUD;
    private Context context;
    private boolean cancelable;

    public ProgressDialogHandler(Context context,
                                 boolean cancelable) {
        super();
        this.context = context;
        this.cancelable = cancelable;
    }

    private void initProgressDialog() {
        if (kProgressHUD == null) {
            kProgressHUD = KprogresshudOptition.getKProgressHUD(context);
            kProgressHUD.setCancellable(cancelable);
        }
        if (!kProgressHUD.isShowing()) {
            kProgressHUD.show();
        }
    }

    private void dismissProgressDialog() {
        if (kProgressHUD != null) {
            kProgressHUD.dismiss();
            kProgressHUD = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }

    public void setProgressDialogStyle(KProgressHUD kProgressHUD) {
        this.kProgressHUD = kProgressHUD;
    }
}
