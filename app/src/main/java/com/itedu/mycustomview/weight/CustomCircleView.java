package com.itedu.mycustomview.weight;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.itedu.mycustomview.R;


/**
 * 进度条
 * 1.设置进度条类型（圆--矩形）
 * 2.设置进度条颜色
 * 3.设置圆的背景颜色
 * 4.设置进度条的最大值和初始值
 * 5.设置环型进度条是否为圆头，默认false
 * 6.设置进度条的大小
 * 7.设置进度字体颜色和字体大小
 * <p>
 * Created by Administrator on 2018/11/7.
 */

public class CustomCircleView extends View {
    private static final int DEFAULT_PROGRESS_WIDTH = (int) (40 * (Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    private final int DEFAULT_TEXT_SIZE = 20;
    private final int DEFAULT_TEXT_COLOR = Color.BLUE;
    private final int TYPE_CIRCLE = 1;
    public static int TYPE_RECT = 0;
    private int mType;//类型  0 为矩形（TYPE_RECT) 1为圆(TYPE_CIRCLE)
    private int progressValue;  //进度条的初始值
    private int progressMax;    //进度条的最大值
    private int progressColor; //进度条颜色
    private int circleBgColor;//圆背景颜色
    private int progressWidth;  //进度条大小
    private int mWidth;
    private int mHeight;
    private Paint mBackgroundPaint = new Paint();
    private Paint mPaint = new Paint();
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF mArcOval = new RectF();
    private CustomCircleViewProgressBarText mCustomCircleViewProgressBarText;
    private String mText = "";
    private RectF mBgRect;
    private RectF mProgressRect;
    private int mCircleRadius;
    private Point mCenterePoint;
    private boolean isAnimating = false;
    private ValueAnimator mAnimator;


    public CustomCircleView(Context context) {
        super(context);
        setup(context, null);
    }

    public CustomCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }

    public CustomCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context, attrs);
    }

    private void setup(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCircleProgress);
        //类型
        mType = typedArray.getInt(R.styleable.MyCircleProgress_progress_type, TYPE_CIRCLE);
        //圆的背景颜色
        circleBgColor = typedArray.getColor(R.styleable.MyCircleProgress_circleBg, Color.GRAY);
        //进度条颜色
        progressColor = typedArray.getColor(R.styleable.MyCircleProgress_progressColor, Color.BLUE);
        //进度条的初始值
        progressValue = typedArray.getInt(R.styleable.MyCircleProgress_progressValue, 0);
        //进度条的最大值
        progressMax = typedArray.getColor(R.styleable.MyCircleProgress_progressMax, 100);
        //进度条是否为圆角
        boolean isProgressRoundCap = typedArray.getBoolean(R.styleable.MyCircleProgress_progressRoundCap, false);

        //百分比字体大小
        int textSize = DEFAULT_TEXT_SIZE;
        if (typedArray.hasValue(R.styleable.MyCircleProgress_android_textSize)) {
            textSize = typedArray.getDimensionPixelSize(R.styleable.MyCircleProgress_android_textSize, DEFAULT_TEXT_SIZE);
        }

        //字体颜色
        int textColor = DEFAULT_TEXT_COLOR;
        if (typedArray.hasValue(R.styleable.MyCircleProgress_android_textColor)) {
            textColor = typedArray.getColor(R.styleable.MyCircleProgress_android_textColor, DEFAULT_TEXT_COLOR);
        }

        //进度条大小
        if (mType == TYPE_CIRCLE) {
            progressWidth = typedArray.getDimensionPixelSize(R.styleable.MyCircleProgress_progressWidth, DEFAULT_PROGRESS_WIDTH);
        }


        typedArray.recycle();
        configPaint(textColor, textSize, isProgressRoundCap);

        setProgressValue(progressValue);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        mHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        configShape();
        setMeasuredDimension(mWidth, mHeight);
    }

    /**
     * 配置形状 mType
     */
    private void configShape() {
        if (mType == TYPE_RECT) {
            mBgRect = new RectF(getPaddingLeft(), getPaddingTop(), mWidth + getPaddingLeft(), mHeight + getPaddingTop());
            mProgressRect = new RectF();
        } else {
            mCircleRadius = (Math.min(mWidth, mHeight) - progressWidth) / 2;
            mCenterePoint = new Point(mWidth / 2, mHeight / 2);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mCustomCircleViewProgressBarText != null) {
            mText = mCustomCircleViewProgressBarText.generateText(this, progressValue, progressMax);
        }
        if (mType == TYPE_RECT) {
            drawRect(canvas);
        } else {
            drawCircle(canvas);
        }
    }

    /**
     * 圆
     *
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(mCenterePoint.x, mCenterePoint.y, mCircleRadius, mBackgroundPaint);
        mArcOval.left = mCenterePoint.x - mCircleRadius;
        mArcOval.right = mCenterePoint.x + mCircleRadius;
        mArcOval.top = mCenterePoint.y - mCircleRadius;
        mArcOval.bottom = mCenterePoint.y + mCircleRadius;

        canvas.drawArc(mArcOval, 270, 360 * progressValue / progressMax, false, mPaint);
        if (mText != null && mText.length() > 0) {
            Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
            float baseline = mArcOval.top + (mArcOval.height() - fontMetricsInt.bottom + fontMetricsInt.top) / 2 - fontMetricsInt.top;
            canvas.drawText(mText, mCenterePoint.x, baseline, mTextPaint);
        }
    }

    /**
     * 矩形
     *
     * @param canvas
     */
    private void drawRect(Canvas canvas) {
        canvas.drawRect(mBgRect, mBackgroundPaint);
        mProgressRect.set(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + parseValueToWidth(), getPaddingTop() + mHeight);
        canvas.drawRect(mProgressRect, mPaint);
        if (mText != null && mText.length() > 0) {
            Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
            float baseline = mBgRect.top + (mBgRect.height() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
            canvas.drawText(mText, mBgRect.centerX(), baseline, mTextPaint);
        }

    }

    private int parseValueToWidth() {
        return mWidth * progressValue / progressMax;
    }

    /**
     * 配置画圆
     *
     * @param textColor
     * @param textSize
     * @param isProgressRoundCap
     */
    private void configPaint(int textColor, int textSize, boolean isProgressRoundCap) {
        mPaint.setColor(progressColor);
        mBackgroundPaint.setColor(circleBgColor);
        if (mType == TYPE_RECT) {
            mPaint.setStyle(Paint.Style.FILL);
            mBackgroundPaint.setStyle(Paint.Style.FILL);
        } else {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(progressWidth);
            mPaint.setAntiAlias(true);
            if (isProgressRoundCap) {
                mPaint.setStrokeCap(Paint.Cap.ROUND);
            }
            mBackgroundPaint.setStyle(Paint.Style.STROKE);
            mBackgroundPaint.setStrokeWidth(progressWidth);
            mBackgroundPaint.setAntiAlias(true);
        }

        mTextPaint.setColor(textColor);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    /**
     * 设置进度值
     *
     * @param progress
     */
    public void setProgressValue(int progress) {
        setProgress(progress, true);
    }

    public int setProgressDefValue() {
        return progressValue;
    }

    private void setProgress(int progress, boolean animated) {
        if (progress > progressMax && progress < 0) {
            return;
        }
        if (isAnimating) {
            isAnimating = false;
            mAnimator.cancel();
        }
        int oldValue = progressValue;
        progressValue = progress;
        if (animated) {
            startAnimation(oldValue, progress);
        } else {
            invalidate();
        }


    }

    public int getProgressMax() {
        return progressMax;
    }

    /**
     * 设置进度条最大值
     *
     * @param maxValue
     */
    public void setProgressMax(int maxValue) {
        progressMax = maxValue;
    }

    /**
     * 动画
     *
     * @param start
     * @param end
     */
    private void startAnimation(int start, int end) {
        mAnimator = ValueAnimator.ofInt(start, end);

        int duration = Math.abs(1000 * (end - start) / progressMax);

        mAnimator.setDuration(duration);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressValue = (int) animation.getAnimatedValue();
                invalidate();
            }
        });

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        mAnimator.start();
    }


    /**
     * 设置进度文案的文字大小
     *
     * @see #setTextColor(int)
     */
    public void setTextSize(int textSize) {
        mTextPaint.setTextSize(textSize);
        invalidate();
    }

    /**
     * 设置进度文案的文字颜色
     *
     * @see #setTextSize(int)
     */
    public void setTextColor(int textColor) {
        mTextPaint.setColor(textColor);
        invalidate();
    }

    /**
     * 设置环形进度条的两端是否有圆形的线帽，类型为{@link #TYPE_CIRCLE}时生效
     */
    public void setProgressRoundCap(boolean isRoundCap) {
        mPaint.setStrokeCap(isRoundCap ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        invalidate();
    }

    /**
     * 通过 {@link CustomCircleViewProgressBarText} 设置进度文案
     */
    public void setCustomCircleViewProgressBarText(CustomCircleViewProgressBarText CustomCircleViewProgressBarText) {
        mCustomCircleViewProgressBarText = CustomCircleViewProgressBarText;
    }

    public CustomCircleViewProgressBarText getCustomCircleViewProgressBarText() {
        return mCustomCircleViewProgressBarText;
    }

    public interface CustomCircleViewProgressBarText {
        /**
         * 设置进度文案, {@link CustomCircleView} 会在进度更新时调用该方法获取要显示的文案
         *
         * @param value    当前进度值
         * @param maxValue 最大进度值
         * @return 进度文案
         */
        String generateText(CustomCircleView progressBar, int value, int maxValue);
    }
}
