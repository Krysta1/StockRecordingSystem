package com.us.srs.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.us.srs.R;
import com.us.srs.adapter.RecycItemStockInfoAdapter;
import com.us.srs.base.BaseActivity;
import com.us.srs.db.bean.TransactionItemBean;
import com.us.srs.db.bean.TransactionItemSellOutBean;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StockInfoByNameActivity extends BaseActivity {
    @BindView(R.id.recyc_content)
    RecyclerView recycContent;
    private ArrayList<TransactionItemBean> tempList;
    private RecycItemStockInfoAdapter recycItemStockInfoAdapter;
    @BindView(R.id.tv_gain_loss)
    TextView tvGainLoss;

    @Override
    protected int setLayoutViewById() {
        return R.layout.activity_stock_info;
    }

    @Override
    protected void initView() {
        tempList = getIntent().getParcelableArrayListExtra("data");
        recycContent.setLayoutManager(new LinearLayoutManager(this));
        recycItemStockInfoAdapter = new RecycItemStockInfoAdapter(this, tempList);
        recycContent.setAdapter(recycItemStockInfoAdapter);
        DecimalFormat df = new DecimalFormat("#.###");
        double totalSellInPrice = 0.0, totalSellOutPrice = 0.0;
        for (TransactionItemBean itemBean : tempList) {
            if (itemBean.getTypeFlag() == 0) {
                totalSellInPrice += Double.valueOf(itemBean.getUnitPrice()) * Double.valueOf(itemBean.getAmount());
            } else {
                totalSellOutPrice += Double.valueOf(itemBean.getUnitPrice()) * Double.valueOf(itemBean.getAmount());
            }
        }
        tvGainLoss.setText(df.format(totalSellOutPrice - totalSellInPrice) + "  " + df.format((100*(totalSellOutPrice - totalSellInPrice) / (totalSellInPrice))) + "%");
    }

    @Override
    protected void initListenter() {

    }
}
