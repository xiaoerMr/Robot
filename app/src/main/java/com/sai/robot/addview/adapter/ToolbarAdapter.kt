package com.sai.robot.addview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.sai.robot.R
import com.sai.robot.addview.adapter.ToolbarAdapter.ToolbarViewHolder

internal class ToolbarAdapter(private val title: String) : ViewTypeAdapter<ToolbarViewHolder>() {


    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ToolbarViewHolder {
        val inflate = inflater.inflate(R.layout.view_toolbar, parent, false)
        return ToolbarViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ToolbarViewHolder) {

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            holder.getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }
        holder.toolbar.title = title
    }

    internal class ToolbarViewHolder(rootView: View) : ViewTypeViewHolder(rootView) {
        val toolbar: Toolbar = rootView as Toolbar
    }

}