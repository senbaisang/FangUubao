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
public class MyWeddingActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTitle;
    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wedding);

        mTitle = (TextView) findViewById(R.id.id_item_top_bar_title);
        mTitle.setText(getIntent().getStringExtra(Constant.ACTIVITY_TITLE));

        mBack = (ImageView) findViewById(R.id.id_item_top_bar_back);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_item_top_bar_back:
                MyWeddingActivity.this.finish();
                break;
        }
    }
}
