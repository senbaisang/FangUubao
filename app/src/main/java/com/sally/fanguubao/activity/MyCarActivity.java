package com.sally.fanguubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.sally.fanguubao.R;
import com.sally.fanguubao.bean.FenQiCarProduct;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DecimalFormat;
import java.util.List;

import okhttp3.Call;

import static com.sally.fanguubao.R.id.id_fq_car_fq_price;

/**
 * 这里需要一个自动换行的 linearlayout
 *
 * Created by sally on 16/5/29.
 */
public class MyCarActivity extends AppCompatActivity {

    private ImageView mLogo;
    private TextView mName;
    private TextView mPrice;
    private TextView mFqPrice;
    private LinearLayout mRx;
    private LinearLayout mBrandsLogo;

    private FenQiCarProduct carProduct;
    private FenQiCarProduct.Recommands recommand;
    private List<FenQiCarProduct.Brands> brands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_car);

        initData();
        initView();
        initEvent();
    }

    private void setData() {
        UrlImageViewHelper.setUrlDrawable(mLogo, recommand.getLogo().getUrl(), R.drawable.a);
        mName.setText(recommand.getName());
        mPrice.setText(recommand.getPrice() + "");
        mFqPrice.setText(Constant.REN_MIN_BI + new DecimalFormat("#.##").format(recommand.getPrice() / 12) + " * 12月");

        for(int i=0; i<brands.size(); i++) {
            ImageView iv = new ImageView(this);
            UrlImageViewHelper.setUrlDrawable(iv, brands.get(i).getLogo().getUrl(), R.drawable.b);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.leftMargin = 10;
            lp.rightMargin = 10;
            iv.setLayoutParams(lp);
            mBrandsLogo.addView(iv);
        }
    }

    private void initData() {
        OkHttpUtils.get().url(Constant.DEBUG_FENQI_CAR).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                carProduct = GsonUtil.fenQiCarJson(response);
                recommand = carProduct.getRecommands().get(0);
                brands = carProduct.getBrands();

                setData();
            }
        });
    }

    private void initView() {
        mLogo = (ImageView) findViewById(R.id.id_fq_car_logo);
        mName = (TextView) findViewById(R.id.id_fq_car_name);
        mPrice = (TextView) findViewById(R.id.id_fq_car_price);
        mFqPrice = (TextView) findViewById(id_fq_car_fq_price);
        mRx = (LinearLayout) findViewById(R.id.id_fq_car_rx);
        mBrandsLogo = (LinearLayout) findViewById(R.id.id_fq_car_ll_brands);
        mBrandsLogo.setOrientation(LinearLayout.HORIZONTAL);
        mBrandsLogo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        mBrandsLogo.setPadding(10, 2, 10, 2);
    }

    private void initEvent() {
        mRx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCarActivity.this, FenQiCarHotActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.FENQI_CAR_HOT_PRODUCT, recommand);
                intent.putExtra(Constant.FENQI_CAR_HOT_BUNDLE, bundle);
                startActivity(intent);
            }
        });
    }
}
