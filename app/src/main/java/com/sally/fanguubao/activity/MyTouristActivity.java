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

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.sally.fanguubao.R;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.Utilities;
import com.sally.fanguubao.view.CustomCalculateView;

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
    private CustomCalculateView mPersonCount;
    private CustomCalculateView mChildrenCount;
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
        mPersonCount = (CustomCalculateView) findViewById(R.id.id_ly_person_count);
        mChildrenCount = (CustomCalculateView) findViewById(R.id.id_ly_children_count);
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
                Utilities.showCalendar(MyTouristActivity.this, mEarliestLeave);
                break;
            case R.id.id_ly_latest_leave:
                Utilities.showCalendar(MyTouristActivity.this, mLatestLeave);
                break;
            case R.id.id_ly_day_count:
                Utilities.showDialog(MyTouristActivity.this, mDayCount, mDays, "请选择您的出行天数");
                break;
            case R.id.id_ly_lodging_level:
                Utilities.showDialog(MyTouristActivity.this, mLodgingLevel, mLevels, "请选择您的住宿等级");
                break;
            case R.id.id_item_btn_loan:
                if (mDestination.getText().toString().isEmpty()) {
                    Utilities.showMsg(MyTouristActivity.this, "请选择目的地");
                    return;
                }
                if (mEarliestLeave.getText().toString().isEmpty() || mLatestLeave.getText().toString().isEmpty()) {
                    Utilities.showMsg(MyTouristActivity.this, "请选择出发时间");
                    return;
                }
                String[] earlistLeave = mEarliestLeave.getText().toString().split("-");
                String[] latestLeave = mLatestLeave.getText().toString().split("-");
                if (Integer.parseInt(latestLeave[0]) < Integer.parseInt(earlistLeave[0])) {
                    Utilities.showMsg(MyTouristActivity.this, "最晚出行时间不能比最早出行时间晚");
                    return;
                } else if (Integer.parseInt(latestLeave[0]) == Integer.parseInt(earlistLeave[0])) {
                    if (Integer.parseInt(latestLeave[1]) < Integer.parseInt(earlistLeave[1])) {
                        Utilities.showMsg(MyTouristActivity.this, "最晚出行时间不能比最早出行时间晚");
                        return;
                    } else if (Integer.parseInt(latestLeave[1]) > Integer.parseInt(earlistLeave[1])) {
                        if (Integer.parseInt(latestLeave[2]) < Integer.parseInt(earlistLeave[2])) {
                            Utilities.showMsg(MyTouristActivity.this, "最晚出行时间不能比最早出行时间晚");
                            return;
                        }
                    }
                }
                if (mDayCount.getText().toString().isEmpty()) {
                    Utilities.showMsg(MyTouristActivity.this, "请选择行程天数");
                    return;
                }
                if (mLodgingLevel.getText().toString().isEmpty()) {
                    Utilities.showMsg(MyTouristActivity.this, "请选择住宿等级");
                    return;
                }

                // 我能带多少
                Utilities.showMsg(MyTouristActivity.this, "budget");
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
