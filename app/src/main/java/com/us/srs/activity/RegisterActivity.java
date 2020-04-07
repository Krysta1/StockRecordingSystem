package com.us.srs.activity;

import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.us.srs.R;
import com.us.srs.base.BaseActivity;
import com.us.srs.db.LiteDbUtils;
import com.us.srs.db.bean.User;
import com.us.srs.utils.ValidatorUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_pas)
    EditText editPas;
    @BindView(R.id.edit_repas)
    EditText editRepas;
    @BindView(R.id.tv_sumbit)
    TextView tvSumbit;
    @BindView(R.id.edit_pin)
    EditText editPin;
    @Override
    protected int setLayoutViewById() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void initListenter() {

    }
    @OnClick({R.id.tv_sumbit})
    public void OnClickView(View view){
        switch (view.getId()){
            case R.id.tv_sumbit:
                String phone=editPhone.getText().toString().trim();
                String  pwd=editPas.getText().toString().trim();
                String repwd=editRepas.getText().toString().trim();
                String pin=editPin.getText().toString().trim();
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(RegisterActivity.this,"Phone number cannot be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    Toast.makeText(RegisterActivity.this,"Password  cannot be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(repwd)){
                    Toast.makeText(RegisterActivity.this,"Please confirm your password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!pwd.equals(repwd)){
                    Toast.makeText(RegisterActivity.this,"Inconsistent password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pin)){
                    Toast.makeText(RegisterActivity.this,"Pin  cannot be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(ValidatorUtil.isPhoneNumnber(phone)){
                    User user=new User();
                    user.setUserName(phone);
                    user.setPassWord(pwd);
                    user.setPin(pin);
                    user.setTime(String.valueOf(System.currentTimeMillis()));
                    if(LiteDbUtils.canRegister(phone)){
                        LiteDbUtils.insertUser(user);
                        Toast.makeText(RegisterActivity.this,"Registration success",Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(RegisterActivity.this,"The phone number is already in use",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(RegisterActivity.this,"Phone Error",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
