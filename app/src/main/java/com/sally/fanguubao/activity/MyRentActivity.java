package com.sally.fanguubao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sally.fanguubao.R;
import com.sally.fanguubao.util.Constant;

/**
 * Created by sally on 16/5/29.
 */
public class MyRentActivity extends AppCompatActivity {

    private ImageView mBack;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rent);

        initView();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.id_item_top_bar_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyRentActivity.this.finish();
            }
        });
        mTitle = (TextView) findViewById(R.id.id_item_top_bar_title);
        mTitle.setText(getIntent().getStringExtra(Constant.ACTIVITY_TITLE));
    }
}
