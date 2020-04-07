package com.us.srs.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.us.srs.R;
import com.us.srs.base.BaseActivity;
import com.us.srs.db.LiteDbUtils;
import com.us.srs.db.bean.User;
import com.us.srs.utils.Router;

import butterknife.BindView;
import butterknife.OnClick;

public class RetrieveActivity extends BaseActivity {
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_pin)
    EditText editPin;
    @BindView(R.id.tv_sumbit)
    TextView tvSumbit;

    @Override
    protected int setLayoutViewById() {
        return R.layout.activity_retrieve;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initListenter() {

    }

    @OnClick({R.id.tv_sumbit})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.tv_sumbit:
                String phone = editPhone.getText().toString().trim();
                String pin = editPin.getText().toString().trim();
                if (!TextUtils.isEmpty(phone)) {
                    User user = LiteDbUtils.getFindUserByPhone(phone);
                    if (user != null) {
                        if (phone.equals(user.getUserName()) && pin.equals(user.getPin())) {
                            Router.starIntent(this, NextRetrieveActivity.class, "PHONE", phone);
                            finish();
                        } else {
                            Toast.makeText(RetrieveActivity.this, "Mobile phone number or Pin  is error", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RetrieveActivity.this, "Mobile phone number  or Pin is not registered", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RetrieveActivity.this, "Phone number cannot be empty", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
