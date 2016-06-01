package com.sally.fanguubao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.sally.fanguubao.R;
import com.sally.fanguubao.activity.FenQiLuxProductShowActivity;
import com.sally.fanguubao.adapter.FenQiLuxProductGvAdapter;
import com.sally.fanguubao.bean.FenQiLuxProductDetail;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by sally on 16/6/1.
 */
public class FenQiLuxProductFragment extends Fragment {
    public static final String TAG = "FenQiLuxProductFragment";

    private String mTitle;
    private String mCategory;

    private TextView mTvAttention;
    private GridView mGridView;
    private FenQiLuxProductGvAdapter mAdapter;

    private List<FenQiLuxProductDetail> mLists;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if(bundle != null) {
            mTitle = bundle.getString(Constant.FENQI_LUX_PRODUCT_CATEGORY_TITLE);
            mCategory = bundle.getString(Constant.FENQI_LUX_PRODUCT_CATEGORY_INDEX);
        }

        View view = inflater.inflate(R.layout.fragment_fq_lux_product, null);
        mGridView = (GridView) view.findViewById(R.id.id_fq_lux_grid_view);
        mTvAttention = (TextView) view.findViewById(R.id.id_item_fq_lux_tv_attention);

        initData();
        initEvent();
        return view;
    }

    private void initEvent() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), FenQiLuxProductShowActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.FENQI_LUX_PRODUCT_SHOW_DESC, mLists.get(position));
                intent.putExtra(Constant.FENQI_LUX_PRODUCT_SHOW_BUNDLE, bundle);
                getActivity().startActivity(intent);
            }
        });
    }

    private void initData() {
        OkHttpUtils.get().url(Constant.DEBUG_FENQI_LUX_DETAIL + mCategory + "&key=" + mTitle).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, mCategory + ", key = " + mTitle);
                mLists = GsonUtil.fenqiLuxProductJson(response);

                if(mLists == null || mLists.size() <= 0) {
                    mGridView.setVisibility(View.GONE);
                    mTvAttention.setVisibility(View.VISIBLE);
                    return;
                }
                mAdapter = new FenQiLuxProductGvAdapter(mLists, getContext());
                mGridView.setAdapter(mAdapter);
            }
        });
    }

    public static FenQiLuxProductFragment newInstance(String title, String categoty) {
        Bundle args = new Bundle();
        args.putString(Constant.FENQI_LUX_PRODUCT_CATEGORY_TITLE, title);
        args.putString(Constant.FENQI_LUX_PRODUCT_CATEGORY_INDEX, categoty);

        FenQiLuxProductFragment fragment = new FenQiLuxProductFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
