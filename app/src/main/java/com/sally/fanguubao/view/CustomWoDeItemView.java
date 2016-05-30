package com.sally.fanguubao.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sally.fanguubao.R;

/**
 * Created by sally on 16/5/28.
 */
public class CustomWoDeItemView extends RelativeLayout {

    private ImageView mIvLogo;
    private TextView mTvTitle;

    private String mTitle;
    private int mLogo;

    public CustomWoDeItemView(Context context) {
        this(context, null);
    }

    public CustomWoDeItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomWoDeItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomWoDeItemView);
        mTitle = typedArray.getString(R.styleable.CustomWoDeItemView_my_title);
        mLogo = typedArray.getResourceId(R.styleable.CustomWoDeItemView_my_logo, R.mipmap.ic_launcher);
        typedArray.recycle();

        mTvTitle.setText(mTitle);
        mIvLogo.setImageResource(mLogo);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.item_wode_view, this);
        mIvLogo = (ImageView) this.findViewById(R.id.id_wd_item_logo);
        mTvTitle = (TextView) this.findViewById(R.id.id_wd_item_title);
    }
}
