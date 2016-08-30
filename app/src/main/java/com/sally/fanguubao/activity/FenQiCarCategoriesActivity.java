package com.sally.fanguubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sally.fanguubao.R;
import com.sally.fanguubao.adapter.FenQiICarBrandsAdapter;
import com.sally.fanguubao.adapter.FenQiIphoneAdapter;
import com.sally.fanguubao.bean.FenQiCarBrandsProduct;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * 品牌车辆的展示页， 该页品牌车型的展示， 与 分期－iphone的展示页，可以复用
 *
 * Created by sally on 16/5/31.
 */
public class FenQiCarCategoriesActivity extends AppCompatActivity {

    private ImageView mBack;
    private TextView mTitle;
    private ListView mListView;
    private List<FenQiCarBrandsProduct> mLists;
    private FenQiICarBrandsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenqi_car_catetories);

        mBack = (ImageView) findViewById(R.id.id_item_top_bar_back);
        mTitle = (TextView) findViewById(R.id.id_item_top_bar_title);
        mListView = (ListView) findViewById(R.id.id_fq_car_categories_lv);
        initData();
        initEvent();
    }

    private void initData() {
        String productName = getIntent().getStringExtra(Constant.FENQI_CAR_BRAND_PRODUCT_NAME);
        mTitle.setText(productName);
        String productId = getIntent().getStringExtra(Constant.FENQI_CAR_BRAND_PRODUCT_ID);
        OkHttpUtils.get().url(Constant.DEBUG_FENQI_CAR_BRAND_PRODUCT + productId).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                mLists = GsonUtil.fenQiCarBrandsProducts(response);
                mAdapter = new FenQiICarBrandsAdapter(mLists, FenQiCarCategoriesActivity.this);
                mListView.setAdapter(mAdapter);
            }
        });
    }

    private void initEvent() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FenQiCarCategoriesActivity.this.finish();
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FenQiCarCategoriesActivity.this, FenQiCarCategoriesDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.FENQI_CAR_BRAND_PRODUCT_DETAIL_DESC, mLists.get(position));
                intent.putExtra(Constant.FENQI_CAR_BRAND_PRODUCT_DETAIL_BUNDLE, bundle);
                startActivity(intent);
            }
        });
    }
}
