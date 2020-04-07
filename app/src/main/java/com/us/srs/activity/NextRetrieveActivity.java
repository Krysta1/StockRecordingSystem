package com.us.srs.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.us.srs.R;
import com.us.srs.base.BaseActivity;
import com.us.srs.db.LiteDbUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class NextRetrieveActivity extends BaseActivity {
    @BindView(R.id.edit_pas)
    EditText editPas;
    @BindView(R.id.edit_repas)
    EditText editRepas;
    @BindView(R.id.tv_sumbit)
    TextView tvSumbit;
    private String phone;
    @Override
    protected int setLayoutViewById() {
        return R.layout.activity_next_retrieve;
    }

    @Override
    protected void initView() {
        phone=getIntent().getStringExtra("PHONE");
    }

    @Override
    protected void initListenter() {

    }
    @OnClick({R.id.tv_sumbit})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.tv_sumbit:
                String pwd = editPas.getText().toString().trim();
                String repwd = editRepas.getText().toString().trim();
                if(TextUtils.isEmpty(pwd)){
                    Toast.makeText(NextRetrieveActivity.this,"Password  cannot be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(repwd)){
                    Toast.makeText(NextRetrieveActivity.this,"Please confirm your password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!pwd.equals(repwd)){
                    Toast.makeText(NextRetrieveActivity.this,"Inconsistent password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(LiteDbUtils.updatePwd(phone,pwd)){
                    Toast.makeText(NextRetrieveActivity.this,"Success",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(NextRetrieveActivity.this,"Failure",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
