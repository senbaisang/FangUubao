package com.sally.fanguubao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sally.fanguubao.R;

/**
 * Created by sally on 8/29/16.
 */
public class CustomCalculateView extends LinearLayout implements View.OnClickListener {

    private int max = 20;

    private TextView mSubtract;
    private TextView mNumber;
    private TextView mAdd;

    public CustomCalculateView(Context context) {
        this(context, null);
    }

    public CustomCalculateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCalculateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.item_calculate_view, this);

        mSubtract = (TextView) this.findViewById(R.id.id_item_cal_view_subtract);
        mNumber = (TextView) this.findViewById(R.id.id_item_cal_view_tv);
        mAdd = (TextView) this.findViewById(R.id.id_item_cal_view_add);

        mSubtract.setOnClickListener(this);
        mAdd.setOnClickListener(this);
        mNumber.setText(String.valueOf(1));
    }

    /**
     * 设置 加操作 的最大值
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * 返回number的值
     * @return
     */
    public String getText() {
        return mNumber.getText().toString();
    }

    @Override
    public void onClick(View v) {
        int number = Integer.parseInt(mNumber.getText().toString());
        switch (v.getId()) {
            case R.id.id_item_cal_view_subtract:
                if(number <= 0) {
                    number = 0;
                } else {
                    --number;
                }
                mNumber.setText(String.valueOf(number));
                break;
            case R.id.id_item_cal_view_add:
                if(number >= max) {
                    number = max;
                } else {
                    ++number;
                }
                mNumber.setText(String.valueOf(number));
                break;
        }
    }
}
