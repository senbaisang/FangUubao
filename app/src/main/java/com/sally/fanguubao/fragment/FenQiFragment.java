package com.sally.fanguubao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sally.fanguubao.R;
import com.sally.fanguubao.activity.MyCarActivity;
import com.sally.fanguubao.activity.MyDecorateActivity;
import com.sally.fanguubao.activity.MyIphoneActivity;
import com.sally.fanguubao.activity.MyJdActivity;
import com.sally.fanguubao.activity.MyLuxActivity;
import com.sally.fanguubao.activity.MyMoreActivity;
import com.sally.fanguubao.activity.MyRentActivity;
import com.sally.fanguubao.activity.MyTouristActivity;
import com.sally.fanguubao.activity.MyWeddingActivity;
import com.sally.fanguubao.adapter.FenQiGridViewAdapter;
import com.sally.fanguubao.bean.FenQiButton;
import com.sally.fanguubao.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sally on 16/5/26.
 */
public class FenQiFragment extends Fragment {

    private ViewPager mViewPager;
    private MyPageAdapter myPageAdapter;
    private LinearLayout mPoints;
    private List<ImageView> mPointImageViews;
    private List<ImageView> mBannerImageViews;
    private GridView mGridView;
    private FenQiGridViewAdapter mFenQiGridViewAdapter;

    private int[] mBannerImgs = {R.drawable.a, R.drawable.b, R.drawable.d};
    private FenQiButton mFenqiButton;
    private List<FenQiButton> mBtns;

    private boolean isRunning;
    private final int RUNNING_MESSAGE = 0x110;
    private int lastPosition = 0;

    private List<AppCompatActivity> mGridActivities;

    /**
     * 轮播图的轮转
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RUNNING_MESSAGE:
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                    if(isRunning) {
                        mHandler.sendEmptyMessageDelayed(RUNNING_MESSAGE, 2000);
                    }
                    break;
            }
        }
    };

    public FenQiFragment() {
        super();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fenqi, null);
        mViewPager = (ViewPager) view.findViewById(R.id.id_fq_view_pager);
        mPoints = (LinearLayout) view.findViewById(R.id.id_fq_points);
        mGridView = (GridView) view.findViewById(R.id.id_fq_grid_view);

        initView();
        initData();
        initEvent();

        isRunning = true;
        mHandler.sendEmptyMessageDelayed(RUNNING_MESSAGE, 2000);

        return view;
    }

    private void initData() {
        mBtns = new ArrayList<>();
        mFenqiButton = new FenQiButton("房屋装修", R.drawable.function_zx);
        mBtns.add(mFenqiButton);
        mFenqiButton = new FenQiButton("出境旅游", R.drawable.function_ly);
        mBtns.add(mFenqiButton);
        mFenqiButton = new FenQiButton("婚礼婚庆", R.drawable.function_hq);
        mBtns.add(mFenqiButton);
        mFenqiButton = new FenQiButton("苹果手机", R.drawable.function_iphone);
        mBtns.add(mFenqiButton);
        mFenqiButton = new FenQiButton("家用电器", R.drawable.function_jd);
        mBtns.add(mFenqiButton);
        mFenqiButton = new FenQiButton("奢侈品牌", R.drawable.function_lux);
        mBtns.add(mFenqiButton);
        mFenqiButton = new FenQiButton("新车抢购", R.drawable.function_car);
        mBtns.add(mFenqiButton);
        mFenqiButton = new FenQiButton("白领租房", R.drawable.function_rent);
        mBtns.add(mFenqiButton);
        mFenqiButton = new FenQiButton("更    多", R.drawable.function_more);
        mBtns.add(mFenqiButton);
    }

    private void initEvent() {
        myPageAdapter = new MyPageAdapter();
        mViewPager.setAdapter(myPageAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                position = position % mBannerImgs.length;
                mPoints.getChildAt(position).setEnabled(true);
                mPoints.getChildAt(lastPosition).setEnabled(false);
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mFenQiGridViewAdapter = new FenQiGridViewAdapter(mBtns, getContext());
        mGridView.setAdapter(mFenQiGridViewAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), mGridActivities.get(position).getClass());
                getActivity().startActivity(intent);
            }
        });
    }

    private void initView() {
        mBannerImageViews = new ArrayList<>();
        mPointImageViews = new ArrayList<>();
        for(int i=0; i<mBannerImgs.length; i++) {
            ImageView bannerIv = new ImageView(getContext());
            bannerIv.setImageResource(mBannerImgs[i]);
            mBannerImageViews.add(bannerIv);

            ImageView point = new ImageView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.rightMargin = 10;
            point.setLayoutParams(lp);
            point.setImageResource(R.drawable.point_selector);
            mPoints.addView(point);

            if(i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
        }

        // 将gridview按钮涉及到的activity全部加载进来
        mGridActivities = new ArrayList<>();
        mGridActivities.add(new MyDecorateActivity());
        mGridActivities.add(new MyTouristActivity());
        mGridActivities.add(new MyWeddingActivity());
        mGridActivities.add(new MyIphoneActivity());
        mGridActivities.add(new MyJdActivity());
        mGridActivities.add(new MyLuxActivity());
        mGridActivities.add(new MyCarActivity());
        mGridActivities.add(new MyRentActivity());
        mGridActivities.add(new MyMoreActivity());
    }

    /**
     * 指示点point的adapter
     */
    private class MyPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Constant.BANNER_MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mBannerImageViews.get(position%mBannerImageViews.size()));
            return mBannerImageViews.get(position%mBannerImageViews.size());
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            object = null;
        }
    }

    /**
     * fragment销毁时，停止banner的轮转
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
}
