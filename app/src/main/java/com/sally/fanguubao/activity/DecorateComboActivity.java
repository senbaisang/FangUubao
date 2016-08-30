package com.sally.fanguubao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.sally.fanguubao.R;
import com.sally.fanguubao.bean.FenQiRecommandTaoCan;
import com.sally.fanguubao.bean.ProductImage;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.GsonUtil;
import com.sally.fanguubao.util.Utilities;
import com.sally.fanguubao.util.XmlPullParseUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by sally on 8/26/16.
 */
public class DecorateComboActivity extends AppCompatActivity implements View.OnClickListener {

    private String mProductId;

    private FenQiRecommandTaoCan mFenQiRecommandTaoCan;
    private List<ProductImage> mProductImgs;

    private LinearLayout mLinearLayout;
    private ImageView mLeftArrow;
    private TextView mTitle;
    private TextView mRightBtn;
    private Button mShare;
    private Button mQuery;
    private Button mApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommand_combo);

        mLinearLayout = (LinearLayout) findViewById(R.id.id_linear_layout);
        mLeftArrow = (ImageView) findViewById(R.id.id_item_top_bar_decoration_back);
        mTitle = (TextView) findViewById(R.id.id_item_top_bar_decoration_title);
        mRightBtn = (TextView) findViewById(R.id.id_item_top_bar_decoration_another);
        mShare = (Button) findViewById(R.id.id_item_bottom_bar_share);
        mQuery = (Button) findViewById(R.id.id_item_bottom_bar_query);
        mApply = (Button) findViewById(R.id.id_item_bottom_bar_apply);

        initData();
    }

    private void initData() {
        mProductId = getIntent().getStringExtra(Constant.RECOMMAND_IV);

        OkHttpUtils.get().url(Constant.DEBUG_DECORATING_TAO_CAN + mProductId).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                mFenQiRecommandTaoCan = GsonUtil.fenQiRecommandTaoCan(response);
                try {
                    mProductImgs = XmlPullParseUtil.parse(mFenQiRecommandTaoCan.getDetail());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                for (ProductImage image : mProductImgs) {
                    ImageView iv = new ImageView(DecorateComboActivity.this);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    UrlImageViewHelper.setUrlDrawable(iv, image.getSrc());
                    mLinearLayout.addView(iv);
                }

                mLeftArrow.setOnClickListener(DecorateComboActivity.this);
                mTitle.setText(mFenQiRecommandTaoCan.getName());
                mRightBtn.setOnClickListener(DecorateComboActivity.this);
                mShare.setOnClickListener(DecorateComboActivity.this);
                mQuery.setOnClickListener(DecorateComboActivity.this);
                mApply.setOnClickListener(DecorateComboActivity.this);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_item_top_bar_decoration_back:
                DecorateComboActivity.this.finish();
                break;
            case R.id.id_item_top_bar_decoration_another:
                showMsg("another");
                break;
            case R.id.id_item_bottom_bar_share:
                showMsg("share");
                break;
            case R.id.id_item_bottom_bar_query:
                showMsg("query");
                break;
            case R.id.id_item_bottom_bar_apply:
                showMsg("apply");
                break;
        }
    }

    private void showMsg(String text) {
        Utilities.showMsg(DecorateComboActivity.this, text);
    }
}
