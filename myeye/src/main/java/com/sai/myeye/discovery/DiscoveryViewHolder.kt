package com.sai.myeye.discovery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sai.myeye.R

class DiscoveryViewHolder (view: View) : RecyclerView.ViewHolder(view) {


    private val name: TextView = view.findViewById(R.id.item_discovery)


    fun bindView(title: String){
        name.text = title
    }

    companion object {
        fun create(parent: ViewGroup): DiscoveryViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_discovery, parent, false)
            return DiscoveryViewHolder(view)
        }
    }
}