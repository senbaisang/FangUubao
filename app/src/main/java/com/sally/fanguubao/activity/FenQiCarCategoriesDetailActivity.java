package com.sally.fanguubao.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.sally.fanguubao.R;
import com.sally.fanguubao.bean.FenQiCarBrandsProduct;
import com.sally.fanguubao.bean.FenQiCarProduct;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.Utilities;
import com.sally.fanguubao.view.NOScrollGridView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sally on 16/5/31.
 */
public class FenQiCarCategoriesDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private LinearLayout mPoints;
    private List<ImageView> mPointImageViews;
    private int lastPositioin;

    private TextView mName;
    private TextView mFactotyPrice;
    private TextView mUubPrice;
    private TextView mPayment;
    private TextView mFqPrice;
    private LinearLayout mLcolors;
    private NOScrollGridView mLmodels;

    private TextView mWangYi;
    private TextView mSina;
    private TextView mSouHu;

    private FenQiCarBrandsProduct product;
    private List<FenQiCarProduct.CarLogo> carLogos;
    private int carLogoSize;

    /**
     * 顶／底部按钮
     *
     * @param savedInstanceState
     */
    private ImageView mBack;
    private TextView mTitle;
    private Button mShare;
    private Button mQuery;
    private Button mApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fq_car_brand);

        initData();
        initView();
        setData();
        initEvent();
    }

    private void initData() {
        product = (FenQiCarBrandsProduct) getIntent().getBundleExtra(Constant.FENQI_CAR_BRAND_PRODUCT_DETAIL_BUNDLE).getSerializable(Constant.FENQI_CAR_BRAND_PRODUCT_DETAIL_DESC);
        carLogos = product.getCar_logos();
        carLogoSize = carLogos.size();
    }

    private void initView() {
        mName = (TextView) findViewById(R.id.id_fq_car_brand_name);
        mFactotyPrice = (TextView) findViewById(R.id.id_fq_car_brand_factory_price);
        mUubPrice = (TextView) findViewById(R.id.id_fq_car_brand_uub_price);
        mPayment = (TextView) findViewById(R.id.id_fq_car_brand_payment);
        mFqPrice = (TextView) findViewById(R.id.id_fq_car_brand_fq_price);
        mLcolors = (LinearLayout) findViewById(R.id.id_fq_car_brand_ll_color);
        mLmodels = (NOScrollGridView) findViewById(R.id.id_fq_car_brand_gd_model);

        mWangYi = (TextView) findViewById(R.id.id_fq_car_brand_wangyi);
        mSina = (TextView) findViewById(R.id.id_fq_car_brand_sina);
        mSouHu = (TextView) findViewById(R.id.id_fq_car_brand_souhu);

        mViewPager = (ViewPager) findViewById(R.id.id_good_banner_view_pager);
        mPoints = (LinearLayout) findViewById(R.id.id_good_banner_points);

        mPointImageViews = new ArrayList<>();
        for (int i = 0; i < carLogoSize; i++) {
            ImageView imageView = new ImageView(this);
            UrlImageViewHelper.setUrlDrawable(imageView, carLogos.get(i).getLogo().getUrl(), R.drawable.d);
            mPointImageViews.add(imageView);

            ImageView point = new ImageView(this);
            point.setImageResource(R.drawable.point_selector);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.rightMargin = 20;
            point.setLayoutParams(lp);
            mPoints.addView(point);

            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
        }

        mBack = (ImageView) findViewById(R.id.id_item_top_bar_back);
        mTitle = (TextView) findViewById(R.id.id_item_top_bar_title);
        mShare = (Button) findViewById(R.id.id_item_bottom_bar_share);
        mQuery = (Button) findViewById(R.id.id_item_bottom_bar_query);
        mApply = (Button) findViewById(R.id.id_item_bottom_bar_apply);
    }

    private void initEvent() {
        /*
         * 顶部轮播图事件
         */
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                position = position % carLogoSize;
                mPoints.getChildAt(position).setEnabled(true);
                mPoints.getChildAt(lastPositioin).setEnabled(false);
                lastPositioin = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return carLogoSize;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mPointImageViews.get(position % carLogoSize));
                return mPointImageViews.get(position % carLogoSize);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        /*
         * 底部按钮的点击事件
         */
        mWangYi.setOnClickListener(this);
        mSina.setOnClickListener(this);
        mSouHu.setOnClickListener(this);

        /*
         * 颜色 和 型号 事件
         */
        for (int i = 0; i < mLcolors.getChildCount(); i++) {
            final TextView tv = (TextView) mLcolors.getChildAt(i);
            Log.e("zhe li de dia", mLcolors.getChildCount() + "" + ((TextView) mLcolors.getChildAt(i)).getText().toString());
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utilities.showMsg(FenQiCarCategoriesDetailActivity.this, tv.getText().toString() + "show");
                }
            });
        }
        mLmodels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                Utilities.showMsg(FenQiCarCategoriesDetailActivity.this, tv.getText().toString());
            }
        });

        /**
         * 顶部／底部按钮
         */
        mBack.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mQuery.setOnClickListener(this);
        mApply.setOnClickListener(this);
    }

    private void setData() {
        mTitle.setText(product.getName());
        mName.setText(product.getName());
        mFactotyPrice.setText("厂家指导价格：" + Constant.REN_MIN_BI + product.getO_price() + " ~ " + product.getO_price2() + "万");
        mUubPrice.setText("优优宝价：" + Constant.REN_MIN_BI + product.getPrice() + "万");
        mPayment.setText("首付：" + Constant.REN_MIN_BI + product.getDownpayment() + "万");
        mFqPrice.setText(Constant.REN_MIN_BI + new DecimalFormat("#.##").format(product.getPrice() * 10000 / 12) + "");

        String[] colors = product.getColor().split(",");
        for (int i = 0; i < colors.length; i++) {
            TextView tv = new TextView(this);
            tv.setText(colors[i]);
            tv.setBackground(getResources().getDrawable(R.drawable.btn_select_shape));
            tv.setPadding(4, 12, 4, 12);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.rightMargin = 20;
            tv.setLayoutParams(lp);
            mLcolors.addView(tv);
        }
        final String[] models = product.getModel().split(",");
        mLmodels.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return models.length;
            }

            @Override
            public Object getItem(int position) {
                return models[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = new TextView(FenQiCarCategoriesDetailActivity.this);
                tv.setText(models[position]);
                tv.setBackground(getResources().getDrawable(R.drawable.btn_select_shape));
                tv.setPadding(4, 8, 4, 8);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                return tv;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_fq_car_brand_wangyi:
                Utilities.showMsg(FenQiCarCategoriesDetailActivity.this, "wang yi");
                break;
            case R.id.id_fq_car_brand_sina:
                Utilities.showMsg(FenQiCarCategoriesDetailActivity.this, "sina");
                break;
            case R.id.id_fq_car_brand_souhu:
                Utilities.showMsg(FenQiCarCategoriesDetailActivity.this, "sou hu");
                break;
            case R.id.id_item_bottom_bar_share:
                Utilities.showMsg(FenQiCarCategoriesDetailActivity.this, "分享");
                break;
            case R.id.id_item_bottom_bar_query:
                Utilities.showMsg(FenQiCarCategoriesDetailActivity.this, "咨询");
                break;
            case R.id.id_item_bottom_bar_apply:
                Utilities.showMsg(FenQiCarCategoriesDetailActivity.this, "申请");
                break;
            case R.id.id_item_top_bar_back:
                FenQiCarCategoriesDetailActivity.this.finish();
                break;
        }
    }

}
