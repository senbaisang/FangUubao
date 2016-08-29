package com.sally.fanguubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.sally.fanguubao.R;
import com.sally.fanguubao.adapter.FenQiIphoneAdapter;
import com.sally.fanguubao.bean.FenQiIphoneProduct;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by sally on 16/5/29.
 */
public class MyIphoneActivity extends AppCompatActivity {

    private TextView mTitle;
    private ListView mListView;
    private FenQiIphoneAdapter mFenQiIphoneAdapter;
    private List<FenQiIphoneProduct> mLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_iphone);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mTitle = (TextView) findViewById(R.id.id_item_top_bar_title);
        mTitle.setText(getIntent().getStringExtra(Constant.ACTIVITY_TITLE));
        mListView = (ListView) findViewById(R.id.id_iphone_list_view);

        View headerView = View.inflate(this, R.layout.item_top_mxfq, null);
        mListView.addHeaderView(headerView);
    }

    private void initData() {
        OkHttpUtils.get().url(Constant.DEBUG_FENQI_IPHONE).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                mLists = GsonUtil.fenQiIphoneJson(response);
                mFenQiIphoneAdapter = new FenQiIphoneAdapter(mLists, MyIphoneActivity.this);
                mListView.setAdapter(mFenQiIphoneAdapter);
            }
        });
    }

    private void initEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyIphoneActivity.this, FenQiIphoneProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.FENQI_IPHONE_PRODUCT_KEY, mLists.get((int) parent.getAdapter().getItemId(position)));
                intent.putExtra(Constant.FENQI_IPHONE_PRODUCT_BUNDLE, bundle);
                startActivity(intent);
            }
        });
    }
}
