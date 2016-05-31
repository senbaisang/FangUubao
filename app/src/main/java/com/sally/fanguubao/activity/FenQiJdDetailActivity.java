package com.sally.fanguubao.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.sally.fanguubao.R;
import com.sally.fanguubao.fragment.DemoFragment;
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

    private List<String> mTitles = Arrays.asList("标题1", "标题2", "标题3", "标题4", "标题5", "标题6");
    private List<DemoFragment> mFragments = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fq_jd_detail);

        initData();
        initView();
        initEvent();

        mIndicator.setVisableTabCount(3);
        mIndicator.setTabItemTitles(mTitles);
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager, 0);
//        mIndicator.setOnMyViewPagerChangeListener();
    }

    private void initData() {
        for(String title : mTitles) {
            mFragments.add((DemoFragment) DemoFragment.newInstance(title));
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

    private void initEvent() {

    }
}
