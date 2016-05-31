package com.sally.fanguubao.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sally.fanguubao.R;

import java.util.List;

/**
 * Created by sally on 16/5/31.
 */
public class ViewPagerIndicator extends LinearLayout {

    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 三角形路径，宽高，大小比例
     */
    private Path mPath;
    private int mTriangleWidth;
    private int mTriangleHeight;
    private static final float RATIO_TRIANDLE = 1 / 6f;

    /**
     * 三角形最大宽度限制
     */
    private final int TRIANGLE_MAX_WIDTH = (int) (getScreenWidth() / 3 * RATIO_TRIANDLE);

    /**
     * 三角形的初始位置，以及移动时的偏移量
     */
    private int mOriginTranslationX;
    private int mTranslationX;

    /**
     * 自定义属性 ： 可见标题的个数
     */
    private int mVisableTabCount;
    private static final int DEFAULT_TAB_COUNT = 4;

    /**
     * 标题
     */
    private List<String> mTitles;

    /**
     * 颜色常量
     */
    private static final int DEFAULT_COLOR = 0x77ffffff;
    private static final int NOMAL_COLOR = 0x44ffffff;
    private static final int LIGHT_COLOR = 0x0000ff;

    /**
     * 与indicator 关联 的viewpager
     */
    private ViewPager mViewPager;

    public void setViewPager(ViewPager viewPager, int position) {
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                scroll(position, positionOffset);
                if (mListener != null) {
                    mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
                setHightLightTextView(position);
            }

            @Override
            public void onPageSelected(int position) {
                if (mListener != null) {
                    mListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mListener != null) {
                    mListener.onPageScrollStateChanged(state);
                }
            }
        });
        mViewPager.setCurrentItem(position);
        setHightLightTextView(position);
    }

    /**
     * 由于，将viewpager与indicator关联起来，内部使用了viewpager的onPageChangeListener接口
     * <p/>
     * 为了外部调用处方便，我们暴露一个同样功能的接口出去，方法直接复制onPageChangeListener接口的方法
     */
    public interface OnMyViewPagerChangeListener {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }

    public OnMyViewPagerChangeListener mListener;

    /**
     * 向外部 公布一个 设置onPageChangeListener 方法
     *
     * @param listener
     */
    public void setOnMyViewPagerChangeListener(OnMyViewPagerChangeListener listener) {
        mListener = listener;
    }

    /**
     * 构造方法们
     *
     * @param context
     */
    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
        mVisableTabCount = typedArray.getInt(R.styleable.ViewPagerIndicator_visable_tab_count, DEFAULT_TAB_COUNT);
        if (mVisableTabCount < 0) {
            mVisableTabCount = DEFAULT_TAB_COUNT;
        }
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(6));  // 画笔设置圆角效果
    }

    /**
     * 当控件的宽高发生变化时，都会调用该方法 获得控件的宽高，或者根据控件的宽高设置某些宽高
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mTriangleWidth = (int) (w / mVisableTabCount * RATIO_TRIANDLE);
        mTriangleWidth = Math.min(mTriangleWidth, TRIANGLE_MAX_WIDTH);
        mOriginTranslationX = (w / mVisableTabCount - mTriangleWidth) / 2;
        mTriangleHeight = mTriangleWidth / 2;
        initTriangle();
    }

    /**
     * 初始化三角形
     */
    private void initTriangle() {
        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
        mPath.close();
    }

    /**
     * 在该方法中绘制三角形
     *
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(mOriginTranslationX + mTranslationX, getHeight() + 2);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    /**
     * 给tab indicator 生成标题
     *
     * @param titles
     */
    public void setTabItemTitles(List<String> titles) {
        if (titles != null && titles.size() > 0) {
            removeAllViews();
            mTitles = titles;
            for (String title : mTitles) {
                addView(generateTitleTextView(title));
            }
        }

        /*
         * 设置标题点击事件
         */
        setTabItemClick();
    }

    /**
     * 生成标题tv的方法
     *
     * @param title
     * @return
     */
    private View generateTitleTextView(String title) {
        TextView tvTitle = new TextView(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.width = getScreenWidth() / mVisableTabCount;
        tvTitle.setText(title);
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tvTitle.setTextColor(DEFAULT_COLOR);
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setLayoutParams(lp);
        return tvTitle;
    }

    /**
     * 当xml布局文件加载完，会回调次方法
     * <p/>
     * 这里不需要布局中设置的宽高，将其置为0
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LinearLayout.LayoutParams lp = (LayoutParams) childView.getLayoutParams();
            lp.width = 0;
            lp.height = 0;
            childView.setLayoutParams(lp);
        }

        /*
         * 设置标题点击事件
         */
        setTabItemClick();
    }

    /**
     * 获得屏幕的宽度
     *
     * @return
     */
    public int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = wm.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }


    /**
     * 以下 ： 向外部公布的方法
     */

    /**
     * 设置当前屏幕可见标题的数量
     * <p/>
     * 该方法在setTableItemTitles() 之前调用，因为setTabItemTitles()方法中使用了 mVisableTabCount 这个变量
     *
     * @param count
     */
    public void setVisableTabCount(int count) {
        mVisableTabCount = count;
    }

    /**
     * 设置标题文字选中高亮
     *
     * @param position
     */
    public void setHightLightTextView(int position) {
        resetTextView();
        View childView = getChildAt(position);
        if (childView instanceof TextView) {
            ((TextView) childView).setTextColor(LIGHT_COLOR);
        }
    }

    /**
     * 重置标题文本的颜色
     */
    private void resetTextView() {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if (childView instanceof TextView) {
                ((TextView) childView).setTextColor(NOMAL_COLOR);
            }
        }
    }

    /**
     * 指示器跟随手指滑动
     *
     * @param position
     * @param offset
     */
    public void scroll(int position, float offset) {
        int tabWidth = getWidth() / mVisableTabCount;

        if (position >= mVisableTabCount - 2 && offset > 0 && getChildCount() > mVisableTabCount && position < getChildCount() - 2) {
            if (mVisableTabCount != 1) {
                this.scrollTo((position - (mVisableTabCount - 2)) * tabWidth + (int) (offset * tabWidth), 0);
            } else {
                this.scrollTo((position * tabWidth + (int) (offset * tabWidth)), 0);
            }
        }
        mTranslationX = (int) ((position + offset) * tabWidth);
        invalidate();
    }

    /**
     * 标题的点击事件
     * 1. 动态标题创建完成时
     * 2. 布局加载完成时
     */
    public void setTabItemClick() {
        for (int i = 0; i < getChildCount(); i++) {
            final int j = i;
            View childView = getChildAt(i);
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(j);
                }
            });
        }
    }
}
