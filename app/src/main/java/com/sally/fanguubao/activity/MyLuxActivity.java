package com.sally.fanguubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.sally.fanguubao.R;
import com.sally.fanguubao.util.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sally on 16/5/29.
 */
public class MyLuxActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBag;
    private ImageView mScarf;
    private ImageView mGlasses;

    /**
     * 包袋
     */
    private String mBagCategorory = "BA";
    private String[] mBags = {"BALENCIAGA", "BOTTEGA VENETA", "CHLOÉ", "DOLCE&GABBANA", "GUCCI", "ATTOS", "ALEXANDER MCQUEEN",
            "KENZO", "MCQ", "NINA RICCI", "REBECCA MINKOFF", "TRUSSARDI", "TRU-TRUSSARDI", "VERSACE", "VERSACE COLLECTION", "VERSACE JEANS", "VERSUS"};
    private List<String> mBagsTitles = new ArrayList<>();

    /**
     * 首饰
     */
    private String mJewelryCategory = "首饰";
    private String[] mJewelrys = {"卡地亚", "宝格丽", "香奈儿", "爱马仕", "蒂芙尼", "周大福", "伯爵", "梵克雅宝"};
    private List<String> mJewelryTitles = new ArrayList<>();

    /**
     * 眼镜
     */
    private String mGlassesCategory = "EY";
    private String[] mGlass = {"GUCCI", "ANA HICKMANN", "CALVIN KLEIN JEANS", "HICKMANN", "LACOSTE", "RAY BAN", "SALVATORE FERRAGAMO", "VALENTINO"};
    private List<String> mGlassesTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lux);

        mBag = (ImageView) findViewById(R.id.id_fq_lux_bag);
        mScarf = (ImageView) findViewById(R.id.id_fq_lux_scarf);
        mGlasses = (ImageView) findViewById(R.id.id_fq_lux_glasses);

        initData();
        initEvent();
    }

    private void initData() {
        for(String title : Arrays.asList(mBags)) {
            mBagsTitles.add(title);
        }
        for(String title : Arrays.asList(mJewelrys)) {
            mJewelryTitles.add(title);
        }
        for(String title : Arrays.asList(mGlass)) {
            mGlassesTitles.add(title);
        }
    }

    private void initEvent() {
        mBag.setOnClickListener(this);
        mScarf.setOnClickListener(this);
        mGlasses.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, FenQiLuxDetailActivity.class);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.id_fq_lux_bag:
                bundle.putStringArrayList(Constant.FENQI_LUX_TITLE, (ArrayList<String>) mBagsTitles);
                bundle.putString(Constant.FENQI_LUX_CATEGORY, mBagCategorory);
                intent.putExtra(Constant.FENQI_LUX_BUNDLE, bundle);
                startActivity(intent);
                break;
            case R.id.id_fq_lux_scarf:
                bundle.putStringArrayList(Constant.FENQI_LUX_TITLE, (ArrayList<String>) mJewelryTitles);
                bundle.putString(Constant.FENQI_LUX_CATEGORY, mJewelryCategory);
                intent.putExtra(Constant.FENQI_LUX_BUNDLE, bundle);
                startActivity(intent);
                break;
            case R.id.id_fq_lux_glasses:
                bundle.putStringArrayList(Constant.FENQI_LUX_TITLE, (ArrayList<String>) mGlassesTitles);
                bundle.putString(Constant.FENQI_LUX_CATEGORY, mGlassesCategory);
                intent.putExtra(Constant.FENQI_LUX_BUNDLE, bundle);
                startActivity(intent);
                break;
        }
    }
}
