package com.us.srs.fragment;

import android.view.View;

import com.us.srs.R;
import com.us.srs.activity.AssetActivity;
import com.us.srs.activity.BuyInActivity;
import com.us.srs.activity.FreeflowActivity;
import com.us.srs.activity.SellOutActivity;
import com.us.srs.base.BaseFragment;
import com.us.srs.utils.Router;

import butterknife.OnClick;

public class Fragment_Me extends BaseFragment {
    public static Fragment_Me newInstance() {
        Fragment_Me contentFragment = new Fragment_Me();
        return contentFragment;
    }
    @Override
    protected int setLayoutViewById() {
        return R.layout.fragment_me;
    }
    @Override
    protected void initView() {
    }
    @Override
    protected void initListenter() {
    }
    @OnClick({R.id.Buyin,R.id.Sellout,R.id.bu_detail,R.id.bu_free_flow})
    public void  onClick(View view){
        switch (view.getId()){
            case R.id.Buyin:
                Router.starIntent(activity, BuyInActivity.class);
                break;
            case R.id.Sellout:
                Router.starIntent(activity, SellOutActivity.class);
                break;
            case R.id.bu_detail:
                Router.starIntent(activity, AssetActivity.class);
                break;
            case R.id.bu_free_flow:
                Router.starIntent(activity, FreeflowActivity.class);
                break;
        }
    }
}
