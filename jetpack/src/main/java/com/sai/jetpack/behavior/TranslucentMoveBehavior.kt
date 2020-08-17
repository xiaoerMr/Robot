package com.sai.jetpack.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout

class TranslucentMoveBehavior constructor(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<ImageView>() {

    // https://www.jianshu.com/p/7534b87958ce   支付宝首页 缩放头部效果
    // 原始位置
    var mOriginalHeaderX = 0
    var mOriginalHeaderY = 0

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: ImageView,
        dependency: View
    ): Boolean = dependency is ImageView

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: ImageView,
        dependency: View
    ): Boolean {

        //原始位置
        if (mOriginalHeaderX == 0) mOriginalHeaderX = dependency.width / 2 - child.width / 2
        if (mOriginalHeaderY == 0) mOriginalHeaderY = dependency.height - child.height

        // 百分比 x轴使用  dependency.y
        var mPercentX = dependency.y / mOriginalHeaderX
        var mPercentY = dependency.y / mOriginalHeaderY

        if (mPercentX >= 1) mPercentX = 1f
        if (mPercentY >= 1) mPercentY = 1f

        //计算当前位置
        var x = mOriginalHeaderX - mOriginalHeaderX * mPercentX
        var y = mOriginalHeaderY - mOriginalHeaderY * mPercentY

        if (x <= child.width) x = child.width.toFloat()

        //设置新位置
        child.x = x
        child.y = y

        //等比缩放
        var scale = 1-mPercentX / 2
        if (scale <= 0.8F) scale = 0.8F
        child.scaleX = scale
        child.scaleY = scale
        return true
    }
}