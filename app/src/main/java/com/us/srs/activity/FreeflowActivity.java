package com.us.srs.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.us.srs.R;
import com.us.srs.adapter.RecycFreeflowAdapter;
import com.us.srs.adapter.RecycItemAdapter;
import com.us.srs.base.BaseActivity;
import com.us.srs.db.LiteDbUtils;
import com.us.srs.db.bean.FreeFlowBean;
import com.us.srs.db.bean.TransactionItemBean;
import com.us.srs.utils.Router;

import butterknife.OnClick;

import java.util.Collections;
import java.util.List;


public class FreeflowActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private TextView addText;
    private List<FreeFlowBean> freeFlowBeanList;
    private RecycFreeflowAdapter recycItemAdapter;

    @Override
    protected int setLayoutViewById() {
        return R.layout.activity_freeflow;
    }

    @Override
    protected void initView() {
        addText = findViewById(R.id.tv_add);
        recyclerView = findViewById(R.id.recyc_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initListenter() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        freeFlowBeanList = LiteDbUtils.getQuery(FreeFlowBean.class);
        if (freeFlowBeanList != null && freeFlowBeanList.size() > 0) {
            recycItemAdapter = new RecycFreeflowAdapter(this, freeFlowBeanList);
            recyclerView.setAdapter(recycItemAdapter);
        }
    }

    @OnClick(R.id.tv_add)
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.tv_add:
                Router.starIntent(this, AddFreeflowActivity.class);
                break;
        }
    }
}
