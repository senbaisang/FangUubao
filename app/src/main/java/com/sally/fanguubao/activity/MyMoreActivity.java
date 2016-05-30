package com.sally.fanguubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
 * 更多这个模块跟iphone模块展示的样式，数据一摸一样，可以服用
 *
 * Created by sally on 16/5/29.
 */
public class MyMoreActivity extends AppCompatActivity {

    private ListView mListView;
    private FenQiIphoneAdapter mAdapter;

    private List<FenQiIphoneProduct> mLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_more);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.id_fenqi_more_product_list_view);
        View headerView = View.inflate(this, R.layout.item_top_mxfq, null);
        mListView.addHeaderView(headerView);
    }

    private void initData() {
        OkHttpUtils.get().url(Constant.DEBUG_FENQI_MORE).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                mLists = GsonUtil.fenQiIphoneJson(response);
                mAdapter = new FenQiIphoneAdapter(mLists, MyMoreActivity.this);
                mListView.setAdapter(mAdapter);
            }
        });
    }

    private void initEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyMoreActivity.this, FenQiMoreProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.FENQI_MORE_PRODUCT_KEY, mLists.get((int) parent.getAdapter().getItemId(position)));
                intent.putExtra(Constant.FENQI_MORE_PRODUCT_BUNDLE, bundle);
                startActivity(intent);
            }
        });
    }
}
