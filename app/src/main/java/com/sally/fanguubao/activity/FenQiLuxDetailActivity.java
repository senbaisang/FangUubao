package com.sally.fanguubao.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sally.fanguubao.R;
import com.sally.fanguubao.fragment.FenQiLuxProductFragment;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.view.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sally on 16/6/1.
 */
public class FenQiLuxDetailActivity extends AppCompatActivity {

    private ViewPagerIndicator mIndicator;
    private ViewPager mViewPager;

    /**
     * 顶部标题，以及每个栏目的分类，用来查询数据的，比如：包，眼镜，饰品等
     */
    private String mCategoty;
    private List<String> mTitles;
    private List<FenQiLuxProductFragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fq_lux_detail);

        initData();
        initView();
    }

    private void initData() {
        Bundle bundle = getIntent().getBundleExtra(Constant.FENQI_LUX_BUNDLE);
        mTitles = bundle.getStringArrayList(Constant.FENQI_LUX_TITLE);
        mCategoty = bundle.getString(Constant.FENQI_LUX_CATEGORY);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_fq_lux_view_pager);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_fq_lux_detail_indicator);

        mIndicator.setVisableTabCount(3);
        mIndicator.setViewPager(mViewPager, 0);
        mIndicator.setTabItemTitles(mTitles);
        for(String title : mTitles) {
            FenQiLuxProductFragment fragment = FenQiLuxProductFragment.newInstance(title, mCategoty);
            mFragments.add(fragment);
        }
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
    }
}
