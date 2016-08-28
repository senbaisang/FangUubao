package com.sally.fanguubao.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.sally.fanguubao.R;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.Utilities;

/**
 * Created by sally on 16/5/29.
 */
public class MyTouristActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] mDays = {"4~6天", "7~9天", "10～12天", "13～15天", "15天以上"};
    private String[] mLevels = {"三星级", "四星级", "五星级"};

    private TextView mTitle;
    private TextView mDestination;
    private TextView mEarliestLeave;
    private TextView mLatestLeave;
    private TextView mDayCount;
    private TextView mLodgingLevel;
    private SeekBar mSeekBar;
    private TextView mBudget;
    private TextView mLoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tourist);

        initView();
    }

    private void initView() {
        mTitle = (TextView) findViewById(R.id.id_item_top_bar_title);
        mTitle.setText(getIntent().getStringExtra(Constant.ACTIVITY_TITLE));

        mDestination = (TextView) findViewById(R.id.id_ly_destination);
        mEarliestLeave = (TextView) findViewById(R.id.id_ly_earliest_leave);
        mLatestLeave = (TextView) findViewById(R.id.id_ly_latest_leave);
        mDayCount = (TextView) findViewById(R.id.id_ly_day_count);
        mLodgingLevel = (TextView) findViewById(R.id.id_ly_lodging_level);
        mSeekBar = (SeekBar) findViewById(R.id.id_ly_seek_bar);
        mSeekBar.setMax(100000);
        mBudget = (TextView) findViewById(R.id.id_ly_budge);
        mLoan = (TextView) findViewById(R.id.id_item_btn_loan);

        mDestination.setOnClickListener(this);
        mEarliestLeave.setOnClickListener(this);
        mLatestLeave.setOnClickListener(this);
        mDayCount.setOnClickListener(this);
        mLodgingLevel.setOnClickListener(this);
        mLoan.setOnClickListener(this);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < 3000) {
                    progress = 3000;
                }
                mBudget.setText(Constant.REN_MIN_BI + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ly_destination: {
                Intent intent = new Intent(MyTouristActivity.this, CountrySelectActivity.class);
                startActivityForResult(intent, Constant.SELECT_COUNTRY);
            }
            break;
            case R.id.id_ly_earliest_leave:
                break;
            case R.id.id_ly_latest_leave:
                break;
            case R.id.id_ly_day_count: {
                Utilities.showDialog(MyTouristActivity.this, mDayCount, mDays, "请选择您的出行天数");
            }
            break;
            case R.id.id_ly_lodging_level: {
                Utilities.showDialog(MyTouristActivity.this, mLodgingLevel, mLevels, "请选择您的住宿等级");
            }
            break;
            case R.id.id_item_btn_loan:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.SELECT_COUNTRY && resultCode == Constant.GET_CITY_RESPONSE_CODE) {
            mDestination.setText(data.getStringExtra("address"));
        }
    }


}
