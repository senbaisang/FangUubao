package com.sally.fanguubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sally.fanguubao.R;
import com.sally.fanguubao.util.Constant;

/**
 * Created by sally on 16/5/29.
 */
public class MyDecorateActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int GET_COUNTRY_REQUEST_CODE = 0x1;
    public static final int GET_COUNTRY_RESPONSE_CODE = 0x2;
    public static final int GET_CITY_REQUEST_CODE = 0x3;
    public static final int GET_CITY_RESPONSE_CODE = 0x4;

    private TextView mSelectArea;
    private RadioButton mNewHouse;
    private RadioButton mOldBuild;
    private TextView mHouseArea;
    private TextView mStartDate;
    private TextView mBudget;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_decorate);

        initView();
        initEvent();
    }

    private void initView() {
        mSelectArea = (TextView) findViewById(R.id.id_fq_decorate_select_area);
        mNewHouse = (RadioButton) findViewById(R.id.id_fq_decorate_new_house);
        mOldBuild = (RadioButton) findViewById(R.id.id_fq_decorate_old_build);
        mHouseArea = (TextView) findViewById(R.id.id_fq_decorate_house_area);
        mStartDate = (TextView) findViewById(R.id.id_fq_decorate_start_date);

        mBudget = (TextView) findViewById(R.id.id_fq_decorate_seek_bar_text);
        mSeekBar = (SeekBar) findViewById(R.id.id_fq_decorate_seek_bar);
        mSeekBar.setMax(100000 - 3000);
        mBudget.setText(Constant.REN_MIN_BI + 3000);

    }

    private void initEvent() {
        mSelectArea.setOnClickListener(this);
        mNewHouse.setOnClickListener(this);
        mOldBuild.setOnClickListener(this);
        mHouseArea.setOnClickListener(this);
        mStartDate.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBudget.setText(Constant.REN_MIN_BI + (progress + 3000) + "");
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
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.id_fq_decorate_select_area:
                intent.setClass(this, CountrySelectActivity.class);
                startActivityForResult(intent, GET_COUNTRY_REQUEST_CODE);
                break;
            case R.id.id_fq_decorate_new_house:
                break;
            case R.id.id_fq_decorate_old_build:
                break;
            case R.id.id_fq_decorate_house_area:
                break;
            case R.id.id_fq_decorate_start_date:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MyDecorateActivity.GET_COUNTRY_REQUEST_CODE && resultCode == MyDecorateActivity.GET_COUNTRY_RESPONSE_CODE) {
            String address = data.getStringExtra("address");
            mSelectArea.setText(address);
        }
    }
}
