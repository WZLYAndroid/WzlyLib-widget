package com.wzly.widget.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * <pre>
 *   @author : wiggins
 *   Date: 2019/12/27
 *   Time: 11:00
 *   desc :用于给手机号加空格的输入框
 * </pre>
 */
public class MobilePhoneEditText extends RegexEditText {

    private boolean isMainlandPhone = true;

    private String space = " ";

    public MobilePhoneEditText(Context context) {
        super(context);
    }

    public MobilePhoneEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MobilePhoneEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void isMainlandPhone(boolean isMainlandPhone) {
        this.isMainlandPhone = isMainlandPhone;
    }

    /**
     * 去除空格后的手机号码
     */
    public String getPhoneNumber() {
        return getEditableText().toString().trim().replace(" ", "");
    }

    /**
     * 给手机号码加上空格
     */
    public void setPhoneNumber(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            return;
        }
        if (phoneNumber.length() == 11) {
            StringBuilder sb = new StringBuilder();
            sb.append(phoneNumber.substring(0, 3)).append(space).append(phoneNumber.substring(3, 7)).
                    append(" ").append(phoneNumber.substring(7, 11));
            setText(sb);
        } else {
            setText(phoneNumber);
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

        if (isMainlandPhone) {
            String contents = charSequence.toString();
            int length = contents.length();
            if (length == 4) {
                if (space.equals(contents.substring(3))) {
                    // -
                    contents = contents.substring(0, 3);
                    setText(contents);
                    moveCursorLast(this);
                } else { // +
                    contents = contents.substring(0, 3) + space + contents.substring(3);
                    setText(contents);
                    moveCursorLast(this);
                }
            } else if (length == 9) {
                if (space.equals(contents.substring(8))) {
                    // -
                    contents = contents.substring(0, 8);
                    setText(contents);
                    moveCursorLast(this);
                } else {// +
                    contents = contents.substring(0, 8) + space + contents.substring(8);
                    setText(contents);
                    moveCursorLast(this);
                }
            }
        }
    }

    /**
     * 移动光标到最后位置，如果没有获取焦点，则先获取
     */
    public static void moveCursorLast(EditText editText) {
        if (null != editText) {
            editText.setSelection(editText.getText().length());
        }
    }
}
