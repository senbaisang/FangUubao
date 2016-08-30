package com.sally.fanguubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sally.fanguubao.MyApplication;
import com.sally.fanguubao.R;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.Utilities;

/**
 * Created by sally on 8/30/16.
 */
public class FuLiSheEarnBeanActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBack;
    private TextView mTitle;

    private Button mDailySign;
    private Button mGoodComment;
    private Button mRecommendFriend;
    private Button mApplyStep;
    private Button mSystemDeliver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fls_earn_bean);

        initView();
        initEvent();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.id_item_top_bar_back);
        mTitle = (TextView) findViewById(R.id.id_item_top_bar_title);
        mTitle.setText(getIntent().getStringExtra(Constant.ACTIVITY_TITLE));

        mDailySign = (Button) findViewById(R.id.id_daily_sign);
        mGoodComment = (Button) findViewById(R.id.id_good_comment);
        mRecommendFriend = (Button) findViewById(R.id.id_recommend_friend);
        mApplyStep = (Button) findViewById(R.id.id_apply_step);
        mSystemDeliver = (Button) findViewById(R.id.id_system_deliver);
    }

    private void initEvent() {
        mBack.setOnClickListener(this);
        mDailySign.setOnClickListener(this);
        mGoodComment.setOnClickListener(this);
        mRecommendFriend.setOnClickListener(this);
        mApplyStep.setOnClickListener(this);
        mSystemDeliver.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_item_top_bar_back:
                FuLiSheEarnBeanActivity.this.finish();
                break;
            case R.id.id_daily_sign:
                clickEvent("每日签到");
                break;
            case R.id.id_good_comment:
                break;
            case R.id.id_recommend_friend:
               clickEvent("推荐好友");
                break;
            case R.id.id_apply_step:
                clickEvent("申请分期");
                break;
        }
    }

    private void clickEvent(String text) {
        if(MyApplication.check_login()) {
            Utilities.showMsg(FuLiSheEarnBeanActivity.this, text);
        } else {
            goToLogin();
        }
    }

    private void goToLogin() {
        Intent intent = new Intent(FuLiSheEarnBeanActivity.this, LoginActivity.class);
        intent.putExtra(Constant.ACTIVITY_TITLE, "登陆");
        startActivity(intent);
    }
}
