package com.us.srs.fragment;

import com.us.srs.R;
import com.us.srs.base.BaseFragment;

public class Fragment_News extends BaseFragment{
    public static Fragment_News newInstance() {
        Fragment_News contentFragment = new Fragment_News();
        return contentFragment;
    }
    @Override
    protected int setLayoutViewById() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListenter() {

    }
}
