package com.sally.fanguubao;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sally.fanguubao.fragment.FenQiFragment;
import com.sally.fanguubao.fragment.FuLiSheFragment;
import com.sally.fanguubao.fragment.WoDeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    public static final String TEXT_PRESSED_COLOR = "#33cc99";
    public static final String TEXT_DEFAULT_COLOR = "#999999";

    private ViewPager mViewPager;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private LinearLayout mLlFenQi, mLlFuLiShe, mLlWoDe;
    private TextView mTvFenQi, mTvFuLiShe, mTvWoDe;
    private ImageView mIvFenQi, mIvFuLiShe, mIvWoDe;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }


    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_view_pager);

        mLlFenQi = (LinearLayout) findViewById(R.id.id_ll_fq);
        mLlFuLiShe = (LinearLayout) findViewById(R.id.id_ll_fls);
        mLlWoDe = (LinearLayout) findViewById(R.id.id_ll_wd);
        mTvFenQi = (TextView) findViewById(R.id.id_tv_fq);
        mTvFuLiShe = (TextView) findViewById(R.id.id_tv_fls);
        mTvWoDe = (TextView) findViewById(R.id.id_tv_wd);
        mIvFenQi = (ImageView) findViewById(R.id.id_iv_fq);
        mIvFuLiShe = (ImageView) findViewById(R.id.id_iv_fls);
        mIvWoDe = (ImageView) findViewById(R.id.id_iv_wd);


        mFragments = new ArrayList<>();
        FenQiFragment fenqi = new FenQiFragment();
        FuLiSheFragment fulishe = new FuLiSheFragment();
        WoDeFragment wode = new WoDeFragment();
        mFragments.add(fenqi);
        mFragments.add(fulishe);
        mFragments.add(wode);
        mViewPager.setCurrentItem(0);
        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }
        };
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTvFenQi.setTextColor(Color.parseColor(TEXT_PRESSED_COLOR));
        mIvFenQi.setImageResource(R.drawable.fq_pressed);
    }

    private void initEvent() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                restoreTextViewState();
                switch (position) {
                    case 0:
                        mTvFenQi.setTextColor(Color.parseColor(TEXT_PRESSED_COLOR));
                        mIvFenQi.setImageResource(R.drawable.fq_pressed);
                        break;
                    case 1:
                        mTvFuLiShe.setTextColor(Color.parseColor(TEXT_PRESSED_COLOR));
                        mIvFuLiShe.setImageResource(R.drawable.fuls_pressed);
                        break;
                    case 2:
                        mTvWoDe.setTextColor(Color.parseColor(TEXT_PRESSED_COLOR));
                        mIvWoDe.setImageResource(R.drawable.wod_pressed);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mLlFenQi.setOnClickListener(this);
        mLlFuLiShe.setOnClickListener(this);
        mLlWoDe.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        restoreTextViewState();
        switch (v.getId()) {
            case R.id.id_ll_fq:
                mTvFenQi.setTextColor(Color.parseColor(TEXT_PRESSED_COLOR));
                mIvFenQi.setImageResource(R.drawable.fq_pressed);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.id_ll_fls:
                mTvFuLiShe.setTextColor(Color.parseColor(TEXT_PRESSED_COLOR));
                mIvFuLiShe.setImageResource(R.drawable.fuls_pressed);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.id_ll_wd:
                mTvWoDe.setTextColor(Color.parseColor(TEXT_PRESSED_COLOR));
                mIvWoDe.setImageResource(R.drawable.wod_pressed);
                mViewPager.setCurrentItem(2);
                break;
        }
    }

    /**
     * 恢复按钮文字的颜色
     */
    private void restoreTextViewState() {
        mTvFenQi.setTextColor(Color.parseColor(TEXT_DEFAULT_COLOR));
        mTvFuLiShe.setTextColor(Color.parseColor(TEXT_DEFAULT_COLOR));
        mTvWoDe.setTextColor(Color.parseColor(TEXT_DEFAULT_COLOR));
        mIvFenQi.setImageResource(R.drawable.fq_default);
        mIvFuLiShe.setImageResource(R.drawable.fuls_default);
        mIvWoDe.setImageResource(R.drawable.wod_default);
    }


}
