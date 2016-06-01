package com.sally.fanguubao.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.sally.fanguubao.R;
import com.sally.fanguubao.bean.FenQiLuxProductDetail;
import com.sally.fanguubao.util.Constant;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sally on 16/6/1.
 */
public class FenQiLuxProductShowActivity extends AppCompatActivity {

    private FenQiLuxProductDetail product;

    private ViewPager mViewPager;
    private LinearLayout mPoints;
    private List<ImageView> mPointImageViews;
    private int lastPosition;
    private int mPointImgSize;

    private TextView mName;
    private TextView mPrice;
    private TextView mFqPrice;
    private TextView mNo;
    private TextView mMaterial;
    private TextView mInwardly;
    private TextView mSize;
    private TextView mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fq_lux_product_show);

        product = (FenQiLuxProductDetail) getIntent().getBundleExtra(Constant.FENQI_LUX_PRODUCT_SHOW_BUNDLE).getSerializable(Constant.FENQI_LUX_PRODUCT_SHOW_DESC);
        mPointImgSize = product.getAttos_product_logos().size();
        initView();
        initEvent();
        setData();
    }

    private void initView() {
        mName = (TextView) findViewById(R.id.id_fq_lux_show_name);
        mPrice = (TextView) findViewById(R.id.id_fq_lux_show_price);
        mFqPrice = (TextView) findViewById(R.id.id_fq_lux_show_fq_price);
        mNo = (TextView) findViewById(R.id.id_fq_lux_show_no);
        mMaterial = (TextView) findViewById(R.id.id_fq_lux_show_material);
        mInwardly = (TextView) findViewById(R.id.id_fq_lux_show_inwardly);
        mSize = (TextView) findViewById(R.id.id_fq_lux_show_size);
        mLocation = (TextView) findViewById(R.id.id_fq_lux_show_location);

        mViewPager = (ViewPager) findViewById(R.id.id_good_banner_view_pager);
        mPoints = (LinearLayout) findViewById(R.id.id_good_banner_points);
        mPointImageViews = new ArrayList<>();
        for(int i=0; i<mPointImgSize; i++) {
            ImageView imageView = new ImageView(this);
            UrlImageViewHelper.setUrlDrawable(imageView, product.getAttos_product_logos().get(i).getLogo().getUrl(), R.mipmap.ic_launcher);
            mPointImageViews.add(imageView);

            ImageView point = new ImageView(this);
            point.setImageDrawable(getResources().getDrawable(R.drawable.point_selector));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.rightMargin = 20;
            point.setLayoutParams(lp);
            mPoints.addView(point);

            if(i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
        }
    }

    private void initEvent() {
        mViewPager.setAdapter(new PagerAdapter() {
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
                container.addView(mPointImageViews.get(position % mPointImgSize));
                return mPointImageViews.get(position % mPointImgSize);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                position = position % mPointImgSize;
                mPoints.getChildAt(position).setEnabled(true);
                mPoints.getChildAt(lastPosition).setEnabled(false);
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setData() {
        mName.setText(product.getName());
        mPrice.setText(Constant.REN_MIN_BI + product.getPrice());
        mFqPrice.setText(Constant.REN_MIN_BI + new DecimalFormat("#.##").format(Integer.valueOf(product.getPrice()) / 12));
        String[] detail = product.getDetails().split(",");
        mNo.setText(detail[0]);
        mMaterial.setText(detail[1]);
        mInwardly.setText(detail[2]);
        mSize.setText(detail[3]);
        mLocation.setText(detail[4]);
    }
}
