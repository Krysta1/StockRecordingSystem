package com.us.srs.activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.us.srs.MainActivity;
import com.us.srs.R;
import com.us.srs.base.BaseActivity;
import com.us.srs.db.LiteDbUtils;
import com.us.srs.utils.Router;
import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity {
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.tv_sumbit)
    TextView tvSumbit;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_retrieve)
    TextView tvRetrieve;

    @Override
    protected int setLayoutViewById() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListenter() {

    }

    @OnClick({R.id.tv_sumbit, R.id.tv_register, R.id.tv_retrieve})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.tv_sumbit:
                String name=editPhone.getText().toString().trim();
                String pwd=editPwd.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(LoginActivity.this,"Phone number cannot be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    Toast.makeText(LoginActivity.this,"Password  cannot be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean can= LiteDbUtils.canUserLogin(name,pwd);
                if(can){
                    Router.starIntent(this, MainActivity.class);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"Incorrect username or password",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_register:
                Router.starIntent(this, RegisterActivity.class);
                break;
            case R.id.tv_retrieve:
                Router.starIntent(this, RetrieveActivity.class);
                break;
        }
    }

}
