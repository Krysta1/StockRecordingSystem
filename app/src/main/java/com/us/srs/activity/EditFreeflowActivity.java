package com.us.srs.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.us.srs.R;
import com.us.srs.base.BaseActivity;
import com.us.srs.db.LiteDbUtils;
import com.us.srs.db.bean.FreeFlowBean;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;


public class EditFreeflowActivity extends BaseActivity {
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.edit_title)
    EditText editTitle;
    @BindView(R.id.edit_content)
    EditText editContent;
    private TextView textSave;
    private FreeFlowBean freeFlowBean;

    @Override
    protected int setLayoutViewById() {
        return R.layout.activity_add_free_flow;
    }

    @Override
    protected void initView() {
        textSave = findViewById(R.id.tv_save);
        textSave.setText("Edit");
        freeFlowBean = (FreeFlowBean) getIntent().getExtras().getSerializable("data");
        if (freeFlowBean != null) {
            editTitle.setText(freeFlowBean.getTitle());
            editTitle.setSelection(freeFlowBean.getTitle().length());
            editContent.setText(freeFlowBean.getContent());
            editContent.setSelection(freeFlowBean.getContent().length());
            editTitle.setEnabled(false);
            editContent.setEnabled(false);
        }
    }

    @Override
    protected void initListenter() {

    }

    @OnClick(R.id.tv_save)
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                if ("Save".equals(tvSave.getText().toString().trim())) {
                    String title = editTitle.getText().toString().trim();
                    String content = editContent.getText().toString().trim();
                    if (TextUtils.isEmpty(title)) {
                        Toast.makeText(this, "Title cannot empty", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (TextUtils.isEmpty(content)) {
                        Toast.makeText(this, "Please input content", Toast.LENGTH_LONG).show();
                        return;
                    }
                    freeFlowBean.setTitle(title);
                    freeFlowBean.setContent(content);
                    freeFlowBean.setTime(getTime());
                    LiteDbUtils.updateFreeFlow(freeFlowBean);
                    finish();
                    Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
                } else {
                    tvSave.setText("Save");
                    editContent.setEnabled(true);
                    editTitle.setEnabled(true);
                }
                break;
        }
    }

    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        return sdf.format(date);
    }
}

