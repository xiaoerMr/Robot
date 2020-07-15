package com.sai.robot.addview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class LinearDecorAdapter(private val views: List<View>) : DecorAdapter() {

    override fun onCreateDecorView(inflater: LayoutInflater): View {
//         LinearLayout(inflater.context).apply {
//            orientation = LinearLayout.VERTICAL
//            for (view in views) {
//                addView(view)
//            }
//        }

        //上面为简写方式
        val linearLayout = LinearLayout(inflater.context)
        linearLayout.orientation = LinearLayout.VERTICAL
        for (view in views) {
            linearLayout.addView(view)
        }
        return linearLayout
    }


    // 将 view 强转为 ViewGroup
    override fun getContentParent(decorView: View): ViewGroup {
        return  decorView as ViewGroup
    }
}