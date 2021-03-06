package com.sally.fanguubao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sally.fanguubao.R;
import com.sally.fanguubao.activity.FaqActivity;
import com.sally.fanguubao.activity.LoginActivity;
import com.sally.fanguubao.activity.MyCeoEmailActivity;
import com.sally.fanguubao.util.Constant;
import com.sally.fanguubao.view.CustomWoDeItemView;

/**
 * Created by sally on 16/5/26.
 */
public class WoDeFragment extends Fragment implements View.OnClickListener {

    private ImageView mAvatar;
    private TextView mLogin;
    private CustomWoDeItemView mFaq;
    private CustomWoDeItemView mCeo;
    private CustomWoDeItemView mShare;
    private CustomWoDeItemView mLike;
    private CustomWoDeItemView mCleanCache;
    private CustomWoDeItemView mAboutUs;

    public WoDeFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wode, null);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        mAvatar = (ImageView) view.findViewById(R.id.id_wd_avatar);
        mLogin = (TextView) view.findViewById(R.id.id_wd_login);
        mFaq = (CustomWoDeItemView) view.findViewById(R.id.id_wd_faq);
        mCeo = (CustomWoDeItemView) view.findViewById(R.id.id_wd_ceo);
        mShare = (CustomWoDeItemView) view.findViewById(R.id.id_wd_share);
        mLike = (CustomWoDeItemView) view.findViewById(R.id.id_wd_like);
        mCleanCache = (CustomWoDeItemView) view.findViewById(R.id.id_wd_clean_cache);
        mAboutUs = (CustomWoDeItemView) view.findViewById(R.id.id_wd_about_us);
    }


    private void initEvent() {
        mLogin.setOnClickListener(this);
        mFaq.setOnClickListener(this);
        mCeo.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mLike.setOnClickListener(this);
        mCleanCache.setOnClickListener(this);
        mAboutUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_wd_login: {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra(Constant.ACTIVITY_TITLE, "登陆");
                startActivity(intent);
            }
            break;
            case R.id.id_wd_faq: {
                Intent intent = new Intent(getActivity(), FaqActivity.class);
                getActivity().startActivity(intent);
            }
            break;
            case R.id.id_wd_ceo: {
                Intent intent = new Intent(getActivity(), MyCeoEmailActivity.class);
                intent.putExtra(Constant.ACTIVITY_TITLE, "CEO信箱");
                startActivity(intent);
            }
                break;
            case R.id.id_wd_share:
                showMsg("推荐给好友");
                break;
            case R.id.id_wd_like:
                showMsg("给个好评");
                break;
            case R.id.id_wd_clean_cache:
                showMsg("清空缓存");
                break;
            case R.id.id_wd_about_us:
                showMsg("关于我们");
                break;
        }
    }

    public void showMsg(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
