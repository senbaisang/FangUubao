package com.sally.fanguubao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sally.fanguubao.R;
import com.sally.fanguubao.bean.Address;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.List;

import okhttp3.Call;

/**
 * Created by sally on 16/6/2.
 */
public class CountrySelectActivity extends AppCompatActivity {

    private List<Address> mAddresses;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);

        initData();
        initView();
        initEvent();
    }

    private void initData() {
        OkHttpUtils.get().url(Constant.DEBUG_LY_ADDRESS).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                mAddresses = GsonUtil.fenQiLyAddress(response);
                mListView.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return mAddresses.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return mAddresses.get(position);
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        TextView tv = new TextView(CountrySelectActivity.this);
                        tv.setText(mAddresses.get(position).getName());
                        tv.setTextColor(Color.parseColor("#333333"));
                        tv.setLeft(20);
                        tv.setPadding(10, 10, 10, 10);
                        return tv;
                    }
                });
            }
        });
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.id_fq_country_list_view);
    }


    private void initEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CountrySelectActivity.this, CitySelectActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.COUNTRY, (Serializable) mAddresses.get(position).getCountries());
                intent.putExtra(Constant.COUNTRY_BUNDLE, bundle);
                startActivityForResult(intent, MyDecorateActivity.GET_CITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MyDecorateActivity.GET_CITY_REQUEST_CODE && resultCode == MyDecorateActivity.GET_CITY_RESPONSE_CODE) {
            String city = data.getStringExtra("city");
            String country = data.getStringExtra("country");
            Intent intent = new Intent();
            intent.putExtra("address", country + " - " + city);
            setResult(MyDecorateActivity.GET_COUNTRY_RESPONSE_CODE, intent);
            finish();
        }
    }
}
