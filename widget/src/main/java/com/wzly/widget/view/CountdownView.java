package com.wzly.widget.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 验证码倒计时
 */
public final class CountdownView extends AppCompatTextView implements Runnable {

    /**
     * 倒计时秒数
     */
    private int mTotalSecond = 60;
    /**
     * 倒计时的文字, 带占位符 :  %d S
     */
    private String mCountdownText = "%d S";

    /**
     * 当前秒数
     */
    private int mCurrentSecond;
    /**
     * 记录原有的文本
     */
    private CharSequence mRecordText;

    public CountdownView(Context context) {
        super(context);
    }

    public CountdownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CountdownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置倒计时总秒数
     */
    public void setTotalTime(int totalTime) {
        this.mTotalSecond = totalTime;
    }

    /**
     * 设置倒计时的文字, 必须带占位符， 如 :  %d S
     */
    public void setCountdownText(String mCountdownText) {
        this.mCountdownText = mCountdownText;
    }

    /**
     * 开始倒计时
     */
    public void start() {
        mRecordText = getText();
        setEnabled(false);
        mCurrentSecond = mTotalSecond;
        post(this);
    }

    /**
     * 结束倒计时
     */
    public void stop() {
        setText(mRecordText);
        setEnabled(true);
        setClickable(true);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 设置点击的属性
        setClickable(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        // 移除延迟任务，避免内存泄露
        removeCallbacks(this);
        super.onDetachedFromWindow();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void run() {
        if (mCurrentSecond == 0) {
            stop();
        } else {
            mCurrentSecond--;
            setText(String.format(mCountdownText, mCurrentSecond));
            postDelayed(this, 1000);
        }
    }
}