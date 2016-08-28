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
public class RecommandComboActivity extends AppCompatActivity implements View.OnClickListener {

    private String mRecommandCode;
    private Map<String, String> mDecorationInfos;

    private String mProductId;
    private String mProductTitle;
    private String mProductName;

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
        mRecommandCode = getIntent().getStringExtra(Constant.RECOMMAND_IV);
        mDecorationInfos = Utilities.decorationInfo(mRecommandCode);
        for (String key : mDecorationInfos.keySet()) {
            switch (key.trim()) {
                case "product_name":
                    mProductName = mDecorationInfos.get(key);
                    break;
                case "product_id":
                    mProductId = mDecorationInfos.get(key);
                    break;
                case "title":
                    mProductTitle = mDecorationInfos.get(key);
                    break;
            }
        }

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

                for(ProductImage image : mProductImgs) {
                    ImageView iv = new ImageView(RecommandComboActivity.this);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    UrlImageViewHelper.setUrlDrawable(iv, image.getSrc());
                    mLinearLayout.addView(iv);
                }

                mLeftArrow.setOnClickListener(RecommandComboActivity.this);
                mTitle.setText(mFenQiRecommandTaoCan.getName());
                mRightBtn.setOnClickListener(RecommandComboActivity.this);
                mShare.setOnClickListener(RecommandComboActivity.this);
                mQuery.setOnClickListener(RecommandComboActivity.this);
                mApply.setOnClickListener(RecommandComboActivity.this);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_item_top_bar_decoration_back:
                Utilities.showMsg(RecommandComboActivity.this, "back");
                break;
            case R.id.id_item_top_bar_decoration_another:
                Utilities.showMsg(RecommandComboActivity.this, "another");
                break;
            case R.id.id_item_bottom_bar_share:
                Utilities.showMsg(RecommandComboActivity.this, "share");
                break;
            case R.id.id_item_bottom_bar_query:
                Utilities.showMsg(RecommandComboActivity.this, "query");
                break;
            case R.id.id_item_bottom_bar_apply:
                Utilities.showMsg(RecommandComboActivity.this, "apply");
                break;
        }
    }
}
