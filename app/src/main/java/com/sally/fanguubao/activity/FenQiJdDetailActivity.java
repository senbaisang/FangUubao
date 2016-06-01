package com.sally.fanguubao.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.sally.fanguubao.R;
import com.sally.fanguubao.bean.FenQiJdCategories;
import com.sally.fanguubao.fragment.FenQiJdProductFragment;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.view.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sally on 16/5/31.
 */
public class FenQiJdDetailActivity extends AppCompatActivity {

    private ViewPagerIndicator mIndicator;
    private ViewPager mViewPager;

    /**
     * 标题栏
     */
    private List<String> mTitles;
    private String currentCategories;
    private List<FenQiJdProductFragment> mFragments = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;

    private FenQiJdCategories product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fq_jd_detail);

        initData();
        initView();

        mIndicator.setVisableTabCount(3);
        mIndicator.setTabItemTitles(mTitles);
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager, 0);
//        mIndicator.setOnMyViewPagerChangeListener();
    }

    private void initData() {
        product = (FenQiJdCategories) getIntent().getBundleExtra(Constant.FENQI_JD_PRODUCT_DETAIL_BUNDLE).getSerializable(Constant.FENQI_JD_PRODUCT_DETAIL_DESC);
        currentCategories = product.getTitle();
        mTitles = Arrays.asList(product.getBrands().split(","));

        for(String title : mTitles) {
            mFragments.add((FenQiJdProductFragment) FenQiJdProductFragment.newInstance(title, currentCategories));
        }
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_fq_jd_detail_view_pager);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_fq_js_detail_indicator);
    }

}
