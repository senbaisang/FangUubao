package com.sally.fanguubao.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sally.fanguubao.R;

/**
 * Created by sally on 16/5/28.
 */
public class CustomDoubleTextView extends LinearLayout {

    private TextView mTopTv;
    private TextView mBottomTv;

    private String mTopTitle;
    private String mBottomTitle;

    public CustomDoubleTextView(Context context) {
        this(context, null);
    }

    public CustomDoubleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomDoubleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomDoubleTextView);
        mTopTitle = typedArray.getString(R.styleable.CustomDoubleTextView_my_top_title);
        mBottomTitle = typedArray.getString(R.styleable.CustomDoubleTextView_my_bottom_title);
        typedArray.recycle();

        mTopTv.setText(mTopTitle);
        mBottomTv.setText(mBottomTitle);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.item_double_text_view, this);
        mTopTv = (TextView) this.findViewById(R.id.id_item_double_tv_top);
        mBottomTv = (TextView) this.findViewById(R.id.id_item_double_tv_bottom);
    }


}
