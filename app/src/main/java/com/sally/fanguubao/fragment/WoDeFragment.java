package com.sally.fanguubao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sally.fanguubao.R;

/**
 * Created by sally on 16/5/26.
 */
public class WoDeFragment extends Fragment {

    public WoDeFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wode, null);

        return view;
    }
}
