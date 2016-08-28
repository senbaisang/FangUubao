package com.sally.fanguubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sally.fanguubao.R;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.Utilities;

/**
 * Created by sally on 16/5/29.
 */
public class MyDecorateActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int GET_COUNTRY_REQUEST_CODE = 0x1;
    public static final int GET_COUNTRY_RESPONSE_CODE = 0x2;
    public static final int GET_CITY_REQUEST_CODE = 0x3;
    public static final int GET_CITY_RESPONSE_CODE = 0x4;

    private TextView mTitle;
    private ImageView mDecorate699;
    private ImageView mDecorate799;
    private ImageView mDecorate599;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_decorate);

        initView();
        initEvent();
    }

    private void initView() {
        mTitle = (TextView) findViewById(R.id.id_item_top_bar_title);
        mTitle.setText(getIntent().getStringExtra(Constant.ACTIVITY_TITLE));

        mDecorate599 = (ImageView) findViewById(R.id.id_decorate_599);
        mDecorate699 = (ImageView) findViewById(R.id.id_decorate_699);
        mDecorate799 = (ImageView) findViewById(R.id.id_decorate_799);
    }

    private void initEvent() {
        mDecorate599.setOnClickListener(this);
        mDecorate699.setOnClickListener(this);
        mDecorate799.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MyDecorateActivity.this, DecorateComboActivity.class);
        switch (v.getId()) {
            case R.id.id_decorate_599:
                intent.putExtra(Constant.RECOMMAND_IV, "53");
                startActivity(intent);
                break;
            case R.id.id_decorate_699:
                intent.putExtra(Constant.RECOMMAND_IV, "55");
                startActivity(intent);
                break;
            case R.id.id_decorate_799:
                intent.putExtra(Constant.RECOMMAND_IV, "57");
                startActivity(intent);
                break;
        }
    }

}
