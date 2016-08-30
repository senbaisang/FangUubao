package com.sally.fanguubao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sally.fanguubao.MyApplication;
import com.sally.fanguubao.R;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.Utilities;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sally on 8/30/16.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTitle;
    private ImageView mBack;
    private EditText mPhone;
    private EditText mPassword;
    private TextView mGetIdentityCode;
    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initEvent();
    }

    private void initView() {
        mTitle = (TextView) findViewById(R.id.id_item_top_bar_title);
        mTitle.setText(getIntent().getStringExtra(Constant.ACTIVITY_TITLE));
        mBack = (ImageView) findViewById(R.id.id_item_top_bar_back);
        mPhone = (EditText) findViewById(R.id.id_login_phone);
        mPassword = (EditText) findViewById(R.id.id_login_password);
        mGetIdentityCode = (TextView) findViewById(R.id.id_login_get_identity_code);
        mLogin = (Button) findViewById(R.id.id_login_login);
    }

    private void initEvent() {
        mBack.setOnClickListener(this);
        mGetIdentityCode.setOnClickListener(this);
        mLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_item_top_bar_back:
                LoginActivity.this.finish();
                break;
            case R.id.id_login_get_identity_code:
                mPassword.setHint(getIndentityCode());
                break;
            case R.id.id_login_login:
                if (mPhone.getText().toString().isEmpty()) {
                    Utilities.showMsg(LoginActivity.this, "请输入手机号码");
                    return;
                }
                if (mPassword.getText().toString().isEmpty()) {
                    Utilities.showMsg(LoginActivity.this, "请输入验证码");
                    return;
                }
                if(Constant.KE_FU_DIAN_HUA.equals(mPhone.getText().toString().trim()) && mPassword.getHint().toString().equals(mPassword.getText().toString())) {
                    Utilities.showMsg(LoginActivity.this, "登陆成功，==做后续处理");
                    MyApplication.is_login = true;
                }
                break;
        }
    }

    /**
     * 产生验证码
     */
    public StringBuffer getIndentityCode() {

        new Timer().schedule(new TimerTask() {
            int time = 6;

            @Override
            public void run() {
                if (time > 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mGetIdentityCode.isClickable()) {
                                mGetIdentityCode.setClickable(false);
                                mGetIdentityCode.setBackgroundColor(getResources().getColor(R.color.greenLight));
                                mGetIdentityCode.setTextColor(getResources().getColor(R.color.black));
                            }
                            mGetIdentityCode.setText(--time + "");
                        }
                    });

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!mGetIdentityCode.isClickable()) {
                                mGetIdentityCode.setClickable(true);
                                mGetIdentityCode.setBackgroundColor(getResources().getColor(R.color.greenDark));
                                mGetIdentityCode.setText("获取验证码");
                                mGetIdentityCode.setTextColor(getResources().getColor(R.color.white));
                            }
                        }
                    });

                }
            }
        }, 0, 1000);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            sb.append(new Random().nextInt(10));
        }
        return sb;
    }
}
