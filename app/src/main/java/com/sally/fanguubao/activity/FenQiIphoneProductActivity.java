package com.sally.fanguubao.activity;

import android.os.Bundle;
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
import com.sally.fanguubao.bean.FenQiIphoneProduct;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.ProductImage;
import com.sally.fanguubao.util.XmlPullParseUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by sally on 16/5/30.
 */
public class FenQiIphoneProductActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private LinearLayout mPoints;
    private List<ImageView> mPointsImageViews;
    private int mPointSize;
    private int lastPosition;

    private TextView mTvName;
    private TextView mTvPrice;
    private TextView mTvFqPrice;
    private TextView mTvRemark;
    private TextView mTvColor;
    private LinearLayout mLlColor;
    private TextView mTvModel;
    private LinearLayout mLlModel;
    private LinearLayout mLlDetails;

    private FenQiIphoneProduct product;
    private String[] colors;
    private String[] models;
    private List<ProductImage> detailsUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenqi_iphone_product);

        initData();
        initView();
        initEvent();
        setData();
    }

    private void setData() {
        mTvName.setText(product.getName());
        mTvPrice.setText(product.getPrice() + "");
        mTvFqPrice.setText(new DecimalFormat("#.##").format(product.getPrice()));
        mTvRemark.setText(product.getRemark());
    }

    private void initView() {
        mTvName = (TextView) findViewById(R.id.id_fenqi_iphone_product_name);
        mTvPrice = (TextView) findViewById(R.id.id_fenqi_iphone_product_price);
        mTvFqPrice = (TextView) findViewById(R.id.id_fenqi_iphone_product_fq_price);
        mTvRemark = (TextView) findViewById(R.id.id_fenqi_iphone_product_remark);
        mTvColor = (TextView) findViewById(R.id.id_fenqi_iphone_product_color);
        mLlColor = (LinearLayout) findViewById(R.id.id_fenqi_iphone_product_color_layout);
        mTvModel = (TextView) findViewById(R.id.id_fenqi_iphone_product_model);
        mLlModel = (LinearLayout) findViewById(R.id.id_fenqi_iphone_product_model_layout);
        mLlDetails = (LinearLayout) findViewById(R.id.id_fenqi_iphone_product_details);
        mLlDetails.setOrientation(LinearLayout.VERTICAL);

        mViewPager = (ViewPager) findViewById(R.id.id_fq_iphone_view_pager);
        mPoints = (LinearLayout) findViewById(R.id.id_fq_iphone_points);

        /*
         * 创建顶部轮播图
         */
        mPointsImageViews = new ArrayList<>();
        for(int i=0; i<mPointSize; i++) {
            ImageView imageView = new ImageView(this);
            UrlImageViewHelper.setUrlDrawable(imageView, product.getProduct_logos().get(i).getLogo().getUrl(), R.drawable.d);
            mPointsImageViews.add(imageView);

            mPointSize = product.getProduct_logos().size();
            ImageView point = new ImageView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.rightMargin = 20;
            point.setLayoutParams(lp);
            point.setImageResource(R.drawable.point_selector);
            mPoints.addView(point);
            if(i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
        }

        /*
         * 判断该商品是否有color 和 model，决定布局文件的显示
         */
        if(colors.length <= 0) {
            mTvColor.setVisibility(View.GONE);
            mLlColor.setVisibility(View.GONE);
        } else {
            for(int i=0; i<colors.length; i++) {
                TextView tv = new TextView(this);
                tv.setText(colors[i]);
                tv.setBackground(getResources().getDrawable(R.drawable.btn_select_shape));
                tv.setPadding(8, 4, 8, 4);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.leftMargin = 10;
                lp.rightMargin = 10;
                lp.gravity = Gravity.CENTER;
                tv.setLayoutParams(lp);
                mLlColor.addView(tv);
            }
        }

        if(models.length <=0 ) {
            mTvModel.setVisibility(View.GONE);
            mLlModel.setVisibility(View.GONE);
        } else {
            for(int i=0; i<models.length; i++) {
                TextView tv = new TextView(this);
                tv.setText(models[i]);
                tv.setBackground(getResources().getDrawable(R.drawable.btn_select_shape));
                tv.setPadding(8, 4, 8, 4);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.leftMargin = 10;
                lp.rightMargin = 10;
                lp.gravity = Gravity.CENTER;
                tv.setLayoutParams(lp);
                mLlModel.addView(tv);
            }
        }

        /*
         * 为该商品动态添加广告图
         */
        for(int i=0; i<detailsUrl.size(); i++) {
            ImageView iv = new ImageView(this);
            UrlImageViewHelper.setUrlDrawable(iv, detailsUrl.get(i).getSrc(), R.drawable.d);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            iv.setLayoutParams(lp);
            mLlDetails.addView(iv);
            Log.e("好几个图片把", detailsUrl.get(i).getSrc() + "=========" + i);
        }
    }

    private void initData() {
        product = (FenQiIphoneProduct) getIntent().getBundleExtra(Constant.FENQI_IPHONE_PRODUCT_BUNDLE).getSerializable(Constant.FENQI_IPHONE_PRODUCT_KEY);
        mPointSize = product.getProduct_logos().size();
        colors = product.getModel1().split(" ");
        models = product.getModel().split(" ");
        try {
            detailsUrl = XmlPullParseUtil.parse(product.getDetail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initEvent() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                position = position % mPointsImageViews.size();
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
                return mPointSize;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mPointsImageViews.get(position % mPointsImageViews.size()));
                return mPointsImageViews.get(position % mPointsImageViews.size());
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
                object = null;
            }
        });
    }
}
