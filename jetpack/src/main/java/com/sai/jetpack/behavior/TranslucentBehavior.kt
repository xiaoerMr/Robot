package com.sai.jetpack.behavior

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout

class TranslucentBehavior constructor(context: Context, attrs:AttributeSet): CoordinatorLayout.Behavior<Toolbar>() {

    var mToolBarHeight = 0

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: Toolbar,
        dependency: View
    ): Boolean {
        return dependency is TextView
    }

    // 必须要加上  layout_anchor，对方也要layout_collapseMode才能使用
    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: Toolbar,
        dependency: View
    ): Boolean {

        if (mToolBarHeight == 0) {
            // 速度慢点
            mToolBarHeight = child.bottom * 2
        }

        //计算toolbar 从开始到最后到百分比
        var percent = dependency.y / mToolBarHeight

        if (percent >= 1) {
            percent = 1f
        }

        var alpha = percent * 255

        //设置背景颜色
        child.setBackgroundColor(Color.argb(alpha.toInt(),98,0,238))

        return true
    }
}