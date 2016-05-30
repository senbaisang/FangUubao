package com.sally.fanguubao.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.sally.fanguubao.R;
import com.sally.fanguubao.bean.FuLiSheProduct;
import com.sally.fanguubao.fragment.FuLiSheFragment;
import com.sally.fanguubao.util.ProductImage;
import com.sally.fanguubao.util.XmlPullParseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sally on 16/5/28.
 */
public class FuLiSheProductActivity extends AppCompatActivity {
    private static final String TAG = "FuLiSheProductActivity";

    private FuLiSheProduct mFuLiSheProduct;

    /**
     * 顶部轮播图相关变量
     */
    private ViewPager mViewPager;
    private LinearLayout mPoints;
    private int mPointImages;
    private List<ImageView> mBannerImageViews;
    private int lastPosition = 0;

    /**
     * 产品信息相关组件
     */
    private TextView mName;
    private TextView mIntroduce;
    private TextView mOriginPrice;
    private TextView mScore;
    private TextView mRemainNumber;
    private TextView mRemainDay;
    private LinearLayout mLlDetails;

    private List<ProductImage> mDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fulishe_product);

        initData();
        initView();
        initEvent();
        setData();
    }

    private void setData() {
        mName.setText(mFuLiSheProduct.getName());
        mIntroduce.setText(mFuLiSheProduct.getIntroduce());
        mOriginPrice.setText(mFuLiSheProduct.getO_price() + "");
        mScore.setText(mFuLiSheProduct.getScore() + "");
        mRemainNumber.setText(mFuLiSheProduct.getRemain() + "");
        mRemainDay.setText(mFuLiSheProduct.getEnd_at().toString());
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_fls_product_view_pager);
        mPoints = (LinearLayout) findViewById(R.id.id_fls_product_points);


        mName = (TextView) findViewById(R.id.id_fls_product_name);
        mIntroduce = (TextView) findViewById(R.id.id_fls_product_introduce);
        mOriginPrice = (TextView) findViewById(R.id.id_fls_product_origin_price);
        mScore = (TextView) findViewById(R.id.id_fls_product_score);
        mRemainNumber = (TextView) findViewById(R.id.id_fls_product_remain_number);
        mRemainDay = (TextView) findViewById(R.id.id_fls_product_remain_day);
        mLlDetails = (LinearLayout) findViewById(R.id.id_fls_product_details);
        mLlDetails.setOrientation(LinearLayout.VERTICAL);

        // 构建轮播图
        mPointImages = mFuLiSheProduct.getOneProducts().size();
        mBannerImageViews = new ArrayList<>();
        for(int i=0; i<mPointImages; i++) {
            ImageView pointIv = new ImageView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.rightMargin = 10;
            lp.gravity = Gravity.CENTER;
            pointIv.setLayoutParams(lp);
            pointIv.setImageResource(R.drawable.point_selector);
            mPoints.addView(pointIv);
            if(i == 0) {
                pointIv.setEnabled(true);
            } else {
                pointIv.setEnabled(false);
            }

            ImageView imageView = new ImageView(this);
            UrlImageViewHelper.setUrlDrawable(imageView, mFuLiSheProduct.getOneProducts().get(i).getLogo().getUrl());
            mBannerImageViews.add(imageView);
        }

        /*
         * 商品logo图
         */
        if(mDetails != null && mDetails.size() > 0) {
            for(int i=0; i<mDetails.size(); i++) {
                ImageView iv = new ImageView(this);
                UrlImageViewHelper.setUrlDrawable(iv, mDetails.get(i).getSrc(), R.drawable.a);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                iv.setLayoutParams(lp);
                mLlDetails.addView(iv);
            }
        }

    }

    private void initEvent() {
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mPointImages;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mBannerImageViews.get(position % mBannerImageViews.size()));
                return mBannerImageViews.get(position % mBannerImageViews.size());
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
                object = null;
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                position = position % mBannerImageViews.size();
                mPoints.getChildAt(position).setEnabled(true);
                mPoints.getChildAt(lastPosition).setEnabled(false);
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundleExtra = intent.getBundleExtra(FuLiSheFragment.FLS_BUNDLE);
        mFuLiSheProduct = (FuLiSheProduct) bundleExtra.getSerializable(FuLiSheFragment.FLS_PRODUCT);
        try {
            mDetails = XmlPullParseUtil.parse(mFuLiSheProduct.getDetail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
