package com.sai.robot.addview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class ViewTypeAdapter<VH : ViewTypeViewHolder> {


    abstract fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): VH

    abstract fun onBindViewHolder(holder: VH)
}

open class ViewTypeViewHolder(val rootView: View) {

    internal var viewType: Any? = null

    var onReloadListener: OnReloadListener? = null
        internal set


    interface OnReloadListener {
        fun onReload()
    }
}

enum class ViewType {
    TITLE, LOADING, CONTENT, ERROR, EMPTY
}