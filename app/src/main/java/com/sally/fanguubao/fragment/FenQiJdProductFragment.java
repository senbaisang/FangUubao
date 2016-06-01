package com.sally.fanguubao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sally.fanguubao.R;
import com.sally.fanguubao.adapter.FenQiJdProductAdapter;
import com.sally.fanguubao.bean.FenQiJdProduct;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by sally on 16/6/1.
 */
public class FenQiJdProductFragment extends Fragment {

    private String title;
    private String currentCategories;
    private List<FenQiJdProduct> mLists;

    private ListView mListView;
    private TextView mTvAttention;
    private FenQiJdProductAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if(bundle != null) {
            title = bundle.getString(Constant.FENQI_JD_PRODUCT_CATEGORIES_TITLE);
            currentCategories = bundle.getString(Constant.FENQI_JD_PRODUCT_CATEGORIES_INDEX);
        }

        View view = inflater.inflate(R.layout.fragment_fenqi_jd_product, null);
        mListView = (ListView) view.findViewById(R.id.id_fq_jd_list_view);
        mTvAttention = (TextView) view.findViewById(R.id.id_fq_jd_tv);

        initData(title);
        initEvent();
        return view;
    }

    private void initData(final String title) {
        currentCategories = "热水器".equals(currentCategories) ? "电热水器" : currentCategories;
        OkHttpUtils.get().url(Constant.DEBUG_FENQI_JD_DETAIL + currentCategories + "&key=" + title.split("（")[0]).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                mLists = GsonUtil.fenQiJdProductJson(response);
                if (mLists == null || mLists.size() <= 0) {
                    mListView.setVisibility(View.GONE);
                    mTvAttention.setVisibility(View.VISIBLE);
                    return;
                }
                mAdapter = new FenQiJdProductAdapter(mLists, getContext());
                mListView.setAdapter(mAdapter);
            }
        });
    }

    private void initEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "webView加载别人家的页面，这里先不写。", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 初始化方法
     * @param title  标题 ＋ url参数
     * @param currentCategories url参数
     * @return
     */
    public static FenQiJdProductFragment newInstance(String title, String currentCategories) {
        Bundle args = new Bundle();
        args.putString(Constant.FENQI_JD_PRODUCT_CATEGORIES_TITLE, title);
        args.putSerializable(Constant.FENQI_JD_PRODUCT_CATEGORIES_INDEX, currentCategories);

        FenQiJdProductFragment fragment = new FenQiJdProductFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
