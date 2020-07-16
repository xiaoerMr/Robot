package com.sai.robot.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * 输入框
 * 格式：
 *  ｜ 名称  ｜ 内容         ｜ 图片
 *  ｜ 名称  ｜ 内容         ｜
 */
class InputView extends LinearLayout {
    public InputView(Context context) {
        this(context,null);
    }

    public InputView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public InputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {

    }
}
