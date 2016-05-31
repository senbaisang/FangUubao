package com.sally.fanguubao.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by sally on 16/5/31.
 */
public class DemoFragment extends Fragment {

    private String title;
    private static final String BUNDLE_TITLE = "title";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        if(bundle != null) {
            title = (String) bundle.get(BUNDLE_TITLE);
        }

        TextView tv = new TextView(getActivity());
        tv.setText(title);
        tv.setTextColor(Color.parseColor("#cc000000"));
        tv.setTextSize(16);
        tv.setGravity(Gravity.CENTER);

        return tv;
    }

    public static Fragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);

        DemoFragment fragment = new DemoFragment();
        fragment.setArguments(bundle);

        return fragment;
    }
}
