package com.sally.fanguubao.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.sally.fanguubao.R;
import com.sally.fanguubao.bean.FenQiCarProduct;
import com.sally.fanguubao.bean.ProductImage;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.XmlPullParseUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sally on 16/5/30.
 */
public class FenQiCarHotActivity extends AppCompatActivity implements View.OnClickListener{

    private FenQiCarProduct.Recommands recommand;
    private int mBannerSize = 0;

    private ViewPager mViewPager;
    private LinearLayout mPoints;
    private List<ImageView> mPointImageViews;
    private int lastPosition;
    private List<FenQiCarProduct.CarLogo> mCarLogos;

    private TextView mName;
    private TextView mFactoryPrice;
    private TextView mUubPrice;
    private TextView mPayment;
    private TextView mFq;
    private LinearLayout mLColor;
    private LinearLayout mLModel;
    private LinearLayout mLImages;
    private TextView mWangYi;
    private TextView mSina;
    private TextView mSouHu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fq_car_hot);

        initData();
        initView();
        initEvent();
        setData();
    }

    private void initData() {
        recommand = (FenQiCarProduct.Recommands) getIntent().getBundleExtra(Constant.FENQI_CAR_HOT_BUNDLE).getSerializable(Constant.FENQI_CAR_HOT_PRODUCT);
        mBannerSize = recommand.getCarLogos().size();
        mCarLogos = recommand.getCarLogos();
    }

    private void initView() {
        mName = (TextView) findViewById(R.id.id_fq_car_hot_name);
        mFactoryPrice = (TextView) findViewById(R.id.id_fq_car_hot_factory_price);
        mUubPrice = (TextView) findViewById(R.id.id_fq_car_hot_uub_price);
        mPayment = (TextView) findViewById(R.id.id_fq_car_hot_payment);
        mFq = (TextView) findViewById(R.id.id_fq_car_hot_fq_price);
        mLColor = (LinearLayout) findViewById(R.id.id_fq_car_hot_ll_color);
        mLModel = (LinearLayout) findViewById(R.id.id_fq_car_hot_ll_model);
        mLImages = (LinearLayout) findViewById(R.id.id_fq_car_hot_images);
        mLImages.setOrientation(LinearLayout.VERTICAL);
        mWangYi = (TextView) findViewById(R.id.id_fq_car_hot_wangyi);
        mSina = (TextView) findViewById(R.id.id_fq_car_hot_sina);
        mSouHu = (TextView) findViewById(R.id.id_fq_car_hot_souhu);

        mViewPager = (ViewPager) findViewById(R.id.id_good_banner_view_pager);
        mPoints = (LinearLayout) findViewById(R.id.id_good_banner_points);

        mPointImageViews = new ArrayList<>();
        for(int i=0; i<mBannerSize; i++) {
            ImageView imageView = new ImageView(this);
            UrlImageViewHelper.setUrlDrawable(imageView, mCarLogos.get(i).getLogo().getUrl(), R.drawable.b);
            mPointImageViews.add(imageView);

            ImageView point = new ImageView(this);
            point.setImageResource(R.drawable.point_selector);
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
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                position = position % mBannerSize;
                mPoints.getChildAt(position).setEnabled(true);
                mPoints.getChildAt(lastPosition).setEnabled(false);
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mCarLogos.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mPointImageViews.get(position % mBannerSize));
                return mPointImageViews.get(position % mBannerSize);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }

    private void setData() {
        mName.setText(recommand.getName());
        mFactoryPrice.setText("厂家指导价格：" + recommand.getO_price() + " ~ " + recommand.getO_price2());
        mUubPrice.setText("优优宝价格：" + recommand.getPrice());
        mPayment.setText("首付：" + recommand.getPrice() / 10);
        mFq.setText(new DecimalFormat("#.##").format(recommand.getPrice() / 12));
        String[] colors = recommand.getColor().split(",");
        for(int i=0; i<colors.length; i++) {
            TextView tv = new TextView(this);
            tv.setText(colors[i]);
            tv.setBackground(getResources().getDrawable(R.drawable.btn_select_shape));
            tv.setPadding(4, 8, 4, 8);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.rightMargin = 20;
            tv.setLayoutParams(lp);
            mLColor.addView(tv);
        }
        String[] models = recommand.getModel().split(",");
        for(int i=0; i<models.length; i++) {
            TextView tv = new TextView(this);
            tv.setText(models[i]);
            tv.setBackground(getResources().getDrawable(R.drawable.btn_select_shape));
            tv.setPadding(4, 8, 4, 8);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.rightMargin = 20;
            tv.setLayoutParams(lp);
            mLModel.addView(tv);
        }
        mWangYi.setOnClickListener(this);
        mSina.setOnClickListener(this);
        mSouHu.setOnClickListener(this);
        List<ProductImage> productImages = null;
        try {
            productImages = XmlPullParseUtil.parse(recommand.getDetail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0; i<productImages.size(); i++) {
            ImageView iv = new ImageView(this);
            UrlImageViewHelper.setUrlDrawable(iv, productImages.get(i).getSrc(), R.drawable.a);
            mLImages.addView(iv);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_fq_car_hot_wangyi:
                showMsg("wang yi");
                break;
            case R.id.id_fq_car_hot_sina:
                showMsg("sina");
                break;
            case R.id.id_fq_car_hot_souhu:
                showMsg("sou hu");
                break;
        }
    }

    public void showMsg(String text) {
        Toast.makeText(FenQiCarHotActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
