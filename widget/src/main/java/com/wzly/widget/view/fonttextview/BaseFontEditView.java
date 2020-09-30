package com.wzly.widget.view.fonttextview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextPaint;
import android.util.AttributeSet;

import com.wzly.widget.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

/**
 * @author jarylan on 2017/4/13.
 * 自定义字体 TextView
 * xml 定义方式
 * <com.gdswww.wzlyapplication.ui.view.FontTextView
 * app:font_type="regular"
 * />
 * 默认用系统字体
 */

public class BaseFontEditView extends AppCompatEditText {

    public BaseFontEditView(Context context) {
        super(context);
    }

    public BaseFontEditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
    }

    public BaseFontEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
    }

    protected void initAttr(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        @SuppressLint("CustomViewStyleable")
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BaseFontTextView);
        boolean bold = a.getBoolean(R.styleable.BaseFontTextView_font_bold, false);
        setBoldText(bold);
        a.recycle();
    }

    /**
     * 设置字体是否为粗体
     *
     * @param bold true 为粗
     */
    public void setBoldText(boolean bold) {
        TextPaint tp = getPaint();
        tp.setFakeBoldText(bold);
    }

    @Override
    public void setWidth(int width) {
        if (this.getLayoutParams().width == width) {
            return;
        }
        this.getLayoutParams().width = width;
        this.requestLayout();
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

}
