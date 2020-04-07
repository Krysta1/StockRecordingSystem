package com.us.srs.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.us.srs.R;
import com.us.srs.base.BaseActivity;
import com.us.srs.db.LiteDbUtils;
import com.us.srs.db.bean.TransactionItemBean;
import com.us.srs.db.bean.TransactionItemSellOutBean;
import com.us.srs.utils.Router;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


public class AssetActivity extends BaseActivity {
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_gain_loss)
    TextView tvGainLoss;
    @BindView(R.id.edit_symbol)
    EditText editSymbol;
    @BindView(R.id.tv_sumbit_by_name)
    TextView tvSumbitByName;
    @BindView(R.id.lin_time_flag)
    LinearLayout linTimeFlag;
    @BindView(R.id.edit_start_time)
    TextView editStartTime;
    @BindView(R.id.edit_finish_time)
    TextView editFinishTime;
    @BindView(R.id.lin_edit)
    LinearLayout linEdit;
    @BindView(R.id.tv_sumbit_by_date)
    TextView tvSumbitByDate;
    private int a;
    private int b;
    private int c;
    private List<TransactionItemBean> transactionItemBeans;
    private List<TransactionItemSellOutBean> transactionItemSellOutBeans;
    private double totalSellInPrice, totalSellOutPrice;

    @Override
    protected int setLayoutViewById() {
        return R.layout.activity_asset;
    }

    @Override
    protected void initView() {
        DecimalFormat df = new DecimalFormat("#.###");
        transactionItemBeans = LiteDbUtils.getQuery(TransactionItemBean.class);
        transactionItemSellOutBeans = LiteDbUtils.getQuery(TransactionItemSellOutBean.class);
        for (TransactionItemBean itemBean : transactionItemBeans) {
            totalSellInPrice += Double.valueOf(itemBean.getUnitPrice()) * Double.valueOf(itemBean.getAmount());
        }
        for (TransactionItemSellOutBean itemSellOutBean : transactionItemSellOutBeans) {
            totalSellOutPrice += Double.valueOf(itemSellOutBean.getUnitPrice()) * Double.valueOf(itemSellOutBean.getAmount());
        }
        tvPrice.setText(df.format(totalSellInPrice));
        tvGainLoss.setText(df.format(totalSellOutPrice - totalSellInPrice) + "  " + df.format((100*(totalSellOutPrice - totalSellInPrice) / (totalSellInPrice))) + "%");

    }

    @Override
    protected void initListenter() {

    }

    @OnClick({R.id.tv_sumbit_by_name, R.id.tv_sumbit_by_date, R.id.edit_start_time, R.id.edit_finish_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sumbit_by_name:
                String name = editSymbol.getText().toString().toLowerCase().trim();
                if (!TextUtils.isEmpty(name)) {
                    List<TransactionItemBean> tSellInList = LiteDbUtils.getQueryByWhere(TransactionItemBean.class, "symbol", new String[]{name});
                    List<TransactionItemSellOutBean> tSellOutList = LiteDbUtils.getQueryByWhere(TransactionItemSellOutBean.class, "symbol", new String[]{name});
                    if (tSellInList.size() > 0 || tSellOutList.size() > 0) {
                        for (TransactionItemSellOutBean bean : tSellOutList) {
                            TransactionItemBean itemBean = new TransactionItemBean();
                            itemBean.setTypeFlag(1);
                            itemBean.setSymBol(bean.getSymBol());
                            itemBean.setAmount(bean.getAmount());
                            itemBean.setData(bean.getData());
                            itemBean.setUnitPrice(bean.getUnitPrice());
                            tSellInList.add(itemBean);
                        }
                        Intent intent = new Intent();
                        ArrayList<TransactionItemBean> tempList = new ArrayList<>();
                        tempList.addAll(tSellInList);
                        intent.putParcelableArrayListExtra("data", tempList);
                        Router.starIntent(this, StockInfoByNameActivity.class, intent);
                    } else {
                        Toast.makeText(AssetActivity.this, "Stock not exist", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AssetActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_sumbit_by_date:
                String beginTime = editStartTime.getText().toString().trim();
                String endTime = editFinishTime.getText().toString().trim();
                if (TextUtils.isEmpty(beginTime) || TextUtils.isEmpty(endTime)) {
                    Toast.makeText(AssetActivity.this, "begin or end time cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date beginDate = formatter.parse(beginTime);
                    Date endDate = formatter.parse(endTime);
                    List<TransactionItemBean> sellinList = new ArrayList<>();
                    List<TransactionItemSellOutBean> sellOutList = new ArrayList<>();
                    for (TransactionItemBean bean : transactionItemBeans) {
                        Date date = formatter.parse(bean.getData());
                        if (date.getTime() >= beginDate.getTime() && date.getTime() <= endDate.getTime()) {
                            sellinList.add(bean);
                        }
                    }
                    for (TransactionItemSellOutBean bean : transactionItemSellOutBeans) {
                        Date date = formatter.parse(bean.getData());
                        if (date.getTime() >= beginDate.getTime() && date.getTime() <= endDate.getTime()) {
                            sellOutList.add(bean);
                        }
                    }

                    if (sellinList.size() > 0 || sellOutList.size() > 0) {
                        for (TransactionItemSellOutBean bean : sellOutList) {
                            TransactionItemBean itemBean = new TransactionItemBean();
                            itemBean.setTypeFlag(1);
                            itemBean.setSymBol(bean.getSymBol());
                            itemBean.setAmount(bean.getAmount());
                            itemBean.setData(bean.getData());
                            itemBean.setUnitPrice(bean.getUnitPrice());
                            sellinList.add(itemBean);
                        }
                        Intent intent = new Intent();
                        ArrayList<TransactionItemBean> tempList = new ArrayList<>();
                        tempList.addAll(sellinList);
                        intent.putParcelableArrayListExtra("data", tempList);
                        Router.starIntent(this, StockInfoByDateActivity.class, intent);
                    } else {
                        Toast.makeText(AssetActivity.this, "Track no result", Toast.LENGTH_SHORT).show();
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.edit_start_time:
                showDateDialog(editStartTime);
                break;
            case R.id.edit_finish_time:
                showDateDialog(editFinishTime);
                break;
        }
    }

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
}
