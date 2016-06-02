package com.sally.fanguubao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sally.fanguubao.R;
import com.sally.fanguubao.bean.Address;
import com.sally.fanguubao.util.Constant;

import java.util.List;

/**
 * Created by sally on 16/6/2.
 */
public class CitySelectActivity extends AppCompatActivity {

    private List<Address.Country> countries;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.id_fq_city_list_view);
    }

    private void initData() {
        countries = (List<Address.Country>) getIntent().getBundleExtra(Constant.COUNTRY_BUNDLE).getSerializable(Constant.COUNTRY);
        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return countries.size();
            }

            @Override
            public Object getItem(int position) {
                return countries.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = new TextView(CitySelectActivity.this);
                tv.setText(countries.get(position).getName());
                tv.setTextColor(Color.parseColor("#333333"));
                tv.setLeft(20);
                tv.setPadding(10, 10, 10, 10);
                return tv;
            }
        });
    }

    private void initEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CitySelectActivity.this, CountrySelectActivity.class);
                intent.putExtra("city", countries.get(position).getName());
                intent.putExtra("country", countries.get(position).getState_name());
                setResult(MyDecorateActivity.GET_CITY_RESPONSE_CODE, intent);
                finish();
            }
        });
    }
}
