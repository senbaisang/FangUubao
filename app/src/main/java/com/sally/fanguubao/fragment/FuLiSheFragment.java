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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sally.fanguubao.R;
import com.sally.fanguubao.activity.FuLiSheProductActivity;
import com.sally.fanguubao.adapter.FuLiSheListViewAdapter;
import com.sally.fanguubao.bean.FuLiSheProduct;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.GsonUtil;
import com.sally.fanguubao.view.CustomTextView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * listView : addHeaderView()  setOnItemClickListener()
 *
 * Created by sally on 16/5/26.
 */
public class FuLiSheFragment extends Fragment implements View.OnClickListener {
    public static final String FLS_PRODUCT = "fls_product";
    public static final String FLS_BUNDLE = "fls_bundle";

    private Button mBtnSign;
    private CustomTextView mTv1, mTv2, mTv3;
    private ListView mListView;
    private FuLiSheListViewAdapter mFuLiSheListViewAdapter;

    /**
     * 顶部点击时间
     */
    private Button mSign;
    private CustomTextView mDou;
    private CustomTextView mGetDou;
    private CustomTextView mFuLi;

    /**
     * 福利社的数据
     */
    private List<FuLiSheProduct> mLists;

    /**
     * top_bar
     */
    private TextView mTopBarTitle;
    private ImageView mTopBarCall;

    public FuLiSheFragment() {
        super();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fulishe, null);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initView(View view) {
        View headerView = View.inflate(getActivity(), R.layout.item_fls_header, null);

        mBtnSign = (Button) headerView.findViewById(R.id.id_fls_btn_sign);
        mTv1 = (CustomTextView) headerView.findViewById(R.id.id_fls_tv1);
        mTv2 = (CustomTextView) headerView.findViewById(R.id.id_fls_tv2);
        mTv3 = (CustomTextView) headerView.findViewById(R.id.id_fls_tv3);
        mListView = (ListView) view.findViewById(R.id.id_fls_list_view);

        mSign = (Button) headerView.findViewById(R.id.id_fls_btn_sign);
        mDou = (CustomTextView) headerView.findViewById(R.id.id_fls_tv1);
        mGetDou = (CustomTextView) headerView.findViewById(R.id.id_fls_tv2);
        mFuLi = (CustomTextView) headerView.findViewById(R.id.id_fls_tv3);

        mTopBarTitle = (TextView) view.findViewById(R.id.id_item_top_bar_title);
        mTopBarTitle.setText("福利社");
        mTopBarCall = (ImageView) view.findViewById(R.id.id_item_top_bar_call);
        mTopBarCall.setOnClickListener(this);

        // 第三个参数，控制headerView是否可以被selected
        mListView.addHeaderView(headerView, null, false);
    }

    private void initData() {
        OkHttpUtils.get().url(Constant.DEBUG_FULISHE_DETAIL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                mLists = GsonUtil.fuLiSheJson(response);
                mFuLiSheListViewAdapter = new FuLiSheListViewAdapter(mLists, getContext());
                mListView.setAdapter(mFuLiSheListViewAdapter);
            }
        });
    }

    private void initEvent() {
        mBtnSign.setOnClickListener(this);
        mTv1.setOnClickListener(this);
        mTv2.setOnClickListener(this);
        mTv3.setOnClickListener(this);

        mSign.setOnClickListener(this);
        mDou.setOnClickListener(this);
        mGetDou.setOnClickListener(this);
        mFuLi.setOnClickListener(this);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), FuLiSheProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(FLS_PRODUCT, mLists.get((int) parent.getAdapter().getItemId(position)));
                intent.putExtra(FLS_BUNDLE, bundle);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_fls_btn_sign:
                showMsg("签到");
                break;
            case R.id.id_fls_tv1:
                showMsg("我的优豆");
                break;
            case R.id.id_fls_tv2:
                showMsg("赚取优豆");
                break;
            case R.id.id_fls_tv3:
                showMsg("我的福利");
                break;
            case R.id.id_item_top_bar_call:
                showMsg("phone call");
                break;
        }
    }

    public void showMsg(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
}
