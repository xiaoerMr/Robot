package com.sai.robot.addview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class ContentAdapter<VH : ViewTypeViewHolder> : ViewTypeAdapter<VH>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): VH {
        return onCreateViewHolder(View(parent.context))
    }

    abstract fun onCreateViewHolder(contentView: View): VH

}