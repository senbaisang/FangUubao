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
import com.sally.fanguubao.bean.FenQiIphoneProduct;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.bean.ProductImage;
import com.sally.fanguubao.util.XmlPullParseUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by sally on 16/5/30.
 */
public class FenQiMoreProductActivity extends AppCompatActivity {

    /**
     * 顶部轮播图变量
     */
    private ViewPager mViewPager;
    private LinearLayout mPoints;
    private List<ImageView> mPointsImageViews;
    private int mPointSize;
    private int lastPosition;

    private TextView mTvName;
    private TextView mTvPrice;
    private TextView mTvFqPrice;
    private TextView mTvRemark;
    private LinearLayout mLlDetails;

    private FenQiIphoneProduct product;
    private List<ProductImage> detailsUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenqi_more_product);

        initData();
        initView();
        initEvent();
        setData();
    }

    private void setData() {
        mTvName.setText(product.getName());
        mTvPrice.setText("原价：" + Constant.REN_MIN_BI + product.getPrice() + "");
        mTvFqPrice.setText(new DecimalFormat("#.##").format(product.getPrice()));
        mTvRemark.setText(product.getRemark());
    }

    private void initView() {
        mTvName = (TextView) findViewById(R.id.id_fenqi_more_product_name);
        mTvPrice = (TextView) findViewById(R.id.id_fenqi_more_product_price);
        mTvFqPrice = (TextView) findViewById(R.id.id_fenqi_more_product_fq_price);
        mTvRemark = (TextView) findViewById(R.id.id_fenqi_more_product_remark);
        mLlDetails = (LinearLayout) findViewById(R.id.id_fenqi_more_product_details);
        mLlDetails.setOrientation(LinearLayout.VERTICAL);

        mViewPager = (ViewPager) findViewById(R.id.id_good_banner_view_pager);
        mPoints = (LinearLayout) findViewById(R.id.id_good_banner_points);

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
         * 为该商品动态添加广告图
         */
        for(int i=0; i<detailsUrl.size(); i++) {
            ImageView iv = new ImageView(this);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            UrlImageViewHelper.setUrlDrawable(iv, detailsUrl.get(i).getSrc(), R.drawable.d);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            iv.setLayoutParams(lp);
            mLlDetails.addView(iv);
        }
    }

    private void initData() {
        product = (FenQiIphoneProduct) getIntent().getBundleExtra(Constant.FENQI_MORE_PRODUCT_BUNDLE).getSerializable(Constant.FENQI_MORE_PRODUCT_KEY);
        mPointSize = product.getProduct_logos().size();
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
