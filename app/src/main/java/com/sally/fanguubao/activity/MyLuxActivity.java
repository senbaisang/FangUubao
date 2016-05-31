package com.sally.fanguubao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.sally.fanguubao.R;

/**
 * Created by sally on 16/5/29.
 */
public class MyLuxActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBag;
    private ImageView mScarf;
    private ImageView mGlasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lux);

        mBag = (ImageView) findViewById(R.id.id_fq_lux_bag);
        mScarf = (ImageView) findViewById(R.id.id_fq_lux_scarf);
        mGlasses = (ImageView) findViewById(R.id.id_fq_lux_glasses);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_fq_lux_bag:
                break;
            case R.id.id_fq_lux_scarf:
                break;
            case R.id.id_fq_lux_glasses:
                break;
        }
    }
}
