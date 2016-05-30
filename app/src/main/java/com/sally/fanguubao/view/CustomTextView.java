package com.sally.fanguubao.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by sally on 16/4/26.
 */
public class CustomTextView extends TextView {
    public static final String TAG = "CustomTextView";

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] compoundDrawables = getCompoundDrawables();
        if(compoundDrawables != null) {
            Drawable drawableLeft = compoundDrawables[0];
//            Log.e(TAG, compoundDrawables + "");
            if(drawableLeft != null) {
                float textWidth = getPaint().measureText(getText().toString());
                int compoundDrawablePadding = getCompoundDrawablePadding();
                int intrinsicWidth = drawableLeft.getIntrinsicWidth(); //返回底层可画的对象本身的宽度。如果它没有内在的宽度，返回- 1，如具有坚实的颜色。
                float contentWidth = textWidth + compoundDrawablePadding;
                canvas.translate((getWidth()-contentWidth)/2, 0);
//                Log.e(TAG, "intrinsicWidth = " + intrinsicWidth);
//                Log.e(TAG, "textWidth = " + textWidth);
//                Log.e(TAG, "compound = " + compoundDrawablePadding);
//                Log.e(TAG, "contentWidth = " + contentWidth);
//                Log.e(TAG, "getWidth = " + getWidth());
//                Log.e(TAG, "translate = " + (getWidth()-contentWidth)/2);
            }
        }
        super.onDraw(canvas);
    }
}
