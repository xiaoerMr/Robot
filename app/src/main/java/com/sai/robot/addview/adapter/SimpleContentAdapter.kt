package com.sai.robot.addview.adapter

import android.view.View

class SimpleContentAdapter : ContentAdapter<ViewTypeViewHolder>() {
    override fun onCreateViewHolder(contentView: View): ViewTypeViewHolder {
        return ViewTypeViewHolder(contentView);
    }

    override fun onBindViewHolder(holder: ViewTypeViewHolder) = Unit
}