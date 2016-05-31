package com.sally.fanguubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.sally.fanguubao.R;
import com.sally.fanguubao.bean.FenQiJdCategories;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by sally on 16/5/29.
 */
public class MyJdActivity extends AppCompatActivity {

    private ListView mListView;
    private List<FenQiJdCategories> mLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_jd);

        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyJdActivity.this, FenQiJdDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        OkHttpUtils.get().url(Constant.DEBUG_FENQI_JD).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                mLists = GsonUtil.fenQiJdJson(response);
                mListView.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return mLists.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return mLists.get(position);
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        convertView = View.inflate(MyJdActivity.this, R.layout.item_jd_text_view, null);
                        TextView tv = (TextView) convertView.findViewById(R.id.id_item_jd_tv);
                        tv.setText(mLists.get(position).getTitle());
                        return convertView;
                    }
                });
            }
        });
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.id_fq_jd_list_view);
        View headerView = View.inflate(this, R.layout.item_jd_header_view, null);
        mListView.addHeaderView(headerView);
    }
}
