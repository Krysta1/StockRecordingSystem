package com.us.srs.base;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {
    protected ImageView backImage;
    protected TextView tvTitleName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutViewById());
        ButterKnife.bind(this);
        initView();
        initListenter();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    public void setTitleName(String titleName) {
        if (tvTitleName != null) {
            tvTitleName.setText(titleName);
        }
    }
    public void setBackListenter() {
        if (backImage != null) {
            backImage.setVisibility(View.VISIBLE);
            backImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
    public void setBackListenter(View.OnClickListener onClickListener) {
        if (backImage != null) {
            backImage.setVisibility(View.VISIBLE);
            backImage.setOnClickListener(onClickListener);
        }
    }
    protected abstract @LayoutRes
    int setLayoutViewById();

    protected abstract void initView();

    protected abstract void initListenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }

}
