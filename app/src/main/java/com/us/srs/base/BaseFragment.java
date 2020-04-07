package com.us.srs.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    protected View parentView;
    protected ImageView backImage;
    protected TextView tvTitleName;
    protected Context activity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(setLayoutViewById(), null);
        ButterKnife.bind(this,parentView);
        return parentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListenter();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @LayoutRes
    protected abstract int setLayoutViewById();

    protected abstract void initView();

    protected abstract void initListenter();

    public Context getContext() {
        return getActivity();
    }
    public void setTitleName(String titleName){
        if(tvTitleName!=null){
            tvTitleName.setText(titleName);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.bind(this,parentView).unbind();
    }
}
