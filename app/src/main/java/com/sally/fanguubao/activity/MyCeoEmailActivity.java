package com.sally.fanguubao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sally.fanguubao.R;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.Utilities;

/**
 * Created by sally on 8/30/16.
 */
public class MyCeoEmailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTitle;
    private EditText mName;
    private EditText mAddress;
    private EditText mMessage;
    private Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceo_email);

        initView();
        initEvent();
    }

    private void initView() {
        mTitle = (TextView) findViewById(R.id.id_item_top_bar_title);
        mTitle.setText(getIntent().getStringExtra(Constant.ACTIVITY_TITLE));
        mName = (EditText) findViewById(R.id.id_ceo_email_name);
        mAddress = (EditText) findViewById(R.id.id_ceo_email_address);
        mMessage = (EditText) findViewById(R.id.id_ceo_email_msg);
        mSubmit = (Button) findViewById(R.id.id_ceo_email_submit);
    }

    private void initEvent() {
        mSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ceo_email_submit:
                if(mName.getText().toString().isEmpty()) {
                    Utilities.showMsg(MyCeoEmailActivity.this, "请填写真实姓名");
                    return;
                }
                if(mAddress.getText().toString().isEmpty()) {
                    Utilities.showMsg(MyCeoEmailActivity.this, "请填写联系方式");
                    return;
                }
                if(mMessage.getText().toString().isEmpty()) {
                    Utilities.showMsg(MyCeoEmailActivity.this, "说点悄悄话吧");
                    return;
                }
                Utilities.showMsg(MyCeoEmailActivity.this, mName.getText().toString() + "-" + mAddress.getText().toString() + "-" + mMessage.getText().toString());
                break;
        }
    }
}
