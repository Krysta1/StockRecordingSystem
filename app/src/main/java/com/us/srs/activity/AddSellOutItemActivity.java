package com.us.srs.activity;

import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.us.srs.R;
import com.us.srs.base.BaseActivity;
import com.us.srs.db.LiteDbUtils;
import com.us.srs.db.bean.TransactionItemBean;
import com.us.srs.db.bean.TransactionItemSellOutBean;
import com.us.srs.impl.SubscriberOnNextListener;
import com.us.srs.net.http.HttpEngine;
import com.us.srs.net.http.HttpWebServer;
import com.us.srs.net.observer.ProgressObserver;
import com.us.srs.utils.ToastUtils;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;


public class AddSellOutItemActivity extends BaseActivity {
    @BindView(R.id.edit_symbol)
    EditText editSymbol;
    @BindView(R.id.edit_amount)
    EditText editAmount;
    @BindView(R.id.edit_price)
    EditText editPrice;
    @BindView(R.id.edit_date)
    TextView editDate;
    private int a;
    private int b;
    private int c;

    @Override
    protected int setLayoutViewById() {
        return R.layout.activity_addselloutitme;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListenter() {

    }

    @OnClick({R.id.tv_sumbit, R.id.edit_date})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.tv_sumbit:
                String sym = editSymbol.getText().toString();
                String amout = editAmount.getText().toString();
                String price = editPrice.getText().toString();
                String date = editDate.getText().toString();
                if (TextUtils.isEmpty(sym)) {
                    ToastUtils.showShortToast("Symbol cannot be empty", AddSellOutItemActivity.this);
                    return;
                }
                if (TextUtils.isEmpty(amout)) {
                    ToastUtils.showShortToast("Amount cannot be empty", AddSellOutItemActivity.this);
                    return;
                }
                if (TextUtils.isEmpty(price)) {
                    ToastUtils.showShortToast("Price cannot be empty", AddSellOutItemActivity.this);
                    return;
                }
                if (TextUtils.isEmpty(date)) {
                    ToastUtils.showShortToast("Date cannot be empty", AddSellOutItemActivity.this);
                    return;
                }
                TransactionItemSellOutBean itemBean = new TransactionItemSellOutBean();
                itemBean.setSymBol(sym.toLowerCase());
                itemBean.setAmount(amout);
                itemBean.setUnitPrice(price);
                itemBean.setData(date);
                getBook(itemBean);
                break;
            case R.id.edit_date:
                showDateDialog(editDate);
                break;
        }
    }

    // 日期选择
    public void showDateDialog(final TextView tvTime) {
        Calendar cal = Calendar.getInstance();
        a = cal.get(Calendar.YEAR);
        b = cal.get(Calendar.MONTH);
        c = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                tvTime.setText(arg1 + "-" + (arg2 + 1) + "-" + arg3);
            }
        }, a, b, c);
        DatePicker datePicker = dialog.getDatePicker();
        datePicker.setMaxDate(new Date().getTime());
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void getBook(final TransactionItemSellOutBean itemBean) {
        HttpWebServer.toSubscribe(HttpEngine.getApiServer().getBooks(itemBean.getSymBol()), new ProgressObserver<JsonObject>(this,
                new SubscriberOnNextListener<JsonObject>() {
                    @Override
                    public void OnSuccess(JsonObject loginResponse) {
                        JsonObject jsonArray = loginResponse;
                        if (jsonArray != null) {
                            Gson gson = new Gson();
                            String s = jsonArray.toString();
                            LiteDbUtils.insertItem(itemBean);
                            ToastUtils.showShortToast("Purchase success", AddSellOutItemActivity.this);
                            finish();
                        } else {
                            ToastUtils.showShortToast("Please enter the correct Symbol", AddSellOutItemActivity.this);
                        }
                    }

                    @Override
                    public void OnFailure(int code, String msg) {
                        ToastUtils.showShortToast("Please enter the correct Symbol", AddSellOutItemActivity.this);
                    }
                }));
    }
}
