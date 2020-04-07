package com.us.srs.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.us.srs.R;
import com.us.srs.adapter.RecycItemAdapter;
import com.us.srs.base.BaseActivity;
import com.us.srs.db.LiteDbUtils;
import com.us.srs.db.bean.TransactionItemBean;
import com.us.srs.utils.Router;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.*;
public class BuyInActivity extends BaseActivity {
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.recyc_content)
    RecyclerView recycContent;
    RecycItemAdapter recycItemAdapter;
    List<TransactionItemBean> transactionItemBeans;
    @Override
    protected int setLayoutViewById() {
        return R.layout.activity_buyin;
    }

    @Override
    protected void initView() {
        recycContent.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initListenter() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        transactionItemBeans= LiteDbUtils.getQuery(TransactionItemBean.class);
        Collections.sort(transactionItemBeans);
        if(transactionItemBeans!=null&&transactionItemBeans.size()>0){
            recycItemAdapter=new RecycItemAdapter(this,transactionItemBeans);
            recycContent.setAdapter(recycItemAdapter);
        }
    }

    @OnClick({R.id.tv_add})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.tv_add:
                Router.starIntent(this,AddItemActivity.class);
                break;
        }
    }
}
