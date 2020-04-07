package com.us.srs;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.us.srs.base.BaseActivity;
import com.us.srs.base.BaseFragment;
import com.us.srs.fragment.Fragment_Me;
import com.us.srs.fragment.Fragment_News;
import com.us.srs.fragment.Fragment_Work;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindViews({R.id.image_work,R.id.image_news,R.id.image_me})
    ImageView [] images;
    @BindViews({R.id.tv_work,R.id.tv_news,R.id.tv_me})
    TextView [] tvs;
    @BindView(R.id.rg_group)
    LinearLayout rgGroup;
    @BindView(R.id.fr_root)
    LinearLayout frRoot;
    private int position=0;
    private BaseFragment selectFragment;
    private List<BaseFragment> baseFragmentList = new ArrayList<>();
    public static final String INDEX = "index";
    private onBackPressedLinstenter  onBackPressedLinstenter;

    public MainActivity.onBackPressedLinstenter getOnBackPressedLinstenter() {
        return onBackPressedLinstenter;
    }

    public void setOnBackPressedLinstenter(MainActivity.onBackPressedLinstenter onBackPressedLinstenter) {
        this.onBackPressedLinstenter = onBackPressedLinstenter;
    }

    @Override
    protected int setLayoutViewById() {
        return R.layout.activity_main;
    }
    @Override
    protected void initView() {
        baseFragmentList.clear();
        baseFragmentList.add(Fragment_Work.newInstance());
        baseFragmentList.add(Fragment_News.newInstance());
        baseFragmentList.add(Fragment_Me.newInstance());
        switchFragment(baseFragmentList.get(0));
        selectTab(0);
    }
    @Override
    protected void initListenter() {

    }
    @OnClick({R.id.lin_work,R.id.lin_news,R.id.lin_personal})
    public  void onClikView(View view){
        switch (view.getId()){
            case R.id.lin_work:
                switchFragment(baseFragmentList.get(0));
                selectTab(0);
                break;
            case R.id.lin_news:
                switchFragment(baseFragmentList.get(1));
                selectTab(1);
                break;
            case R.id.lin_personal:
                switchFragment(baseFragmentList.get(2));
                selectTab(2);
                break;
        }
    }
    private void switchFragment(BaseFragment to) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (selectFragment != null) {
            if (selectFragment != to) {
                if (!to.isAdded()) {
                    transaction.hide(selectFragment).add(R.id.fl_content, to).commitAllowingStateLoss();
                } else {
                    transaction.hide(selectFragment).show(to).commit();
                }
                selectFragment = to;
            }
        } else {
            transaction.add(R.id.fl_content, to);
            selectFragment = to;
            transaction.commit();
        }
    }
    public void selectTab(int index) {
        position = index;
       switch (index){
           case 0:
               images[0].setImageResource(R.drawable.icon_work);
               images[1].setImageResource(R.drawable.icon_new_select);
               images[2].setImageResource(R.drawable.icon_me_select);
               tvs[0].setTextColor(ContextCompat.getColor(this,R.color.color_sys));
               tvs[1].setTextColor(ContextCompat.getColor(this,R.color.color_bottom_text));
               tvs[2].setTextColor(ContextCompat.getColor(this,R.color.color_bottom_text));
               break;
           case 1:
               images[0].setImageResource(R.drawable.icon_work_select);
               images[1].setImageResource(R.drawable.icon_new);
               images[2].setImageResource(R.drawable.icon_me_select);
               tvs[0].setTextColor(ContextCompat.getColor(this,R.color.color_bottom_text));
               tvs[1].setTextColor(ContextCompat.getColor(this,R.color.color_sys));
               tvs[2].setTextColor(ContextCompat.getColor(this,R.color.color_bottom_text));
               break;
           case 2:
               images[0].setImageResource(R.drawable.icon_work_select);
               images[1].setImageResource(R.drawable.icon_new_select);
               images[2].setImageResource(R.drawable.icon_me);
               tvs[0].setTextColor(ContextCompat.getColor(this,R.color.color_bottom_text));
               tvs[1].setTextColor(ContextCompat.getColor(this,R.color.color_bottom_text));
               tvs[2].setTextColor(ContextCompat.getColor(this,R.color.color_sys));
               break;
       }
    }
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INDEX, position);
    }

    @Override
    public void onBackPressed() {
        if(onBackPressedLinstenter!=null){
            if(!onBackPressedLinstenter.onBackPressed()){
                super.onBackPressed();
            }
        }
    }
    public interface onBackPressedLinstenter{
        public boolean onBackPressed();
    }
}
