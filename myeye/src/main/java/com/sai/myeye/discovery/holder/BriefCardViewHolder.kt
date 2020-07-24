package com.sai.myeye.discovery.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sai.myeye.R
import com.sai.myeye.bean.Item

class BriefCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    private val title: TextView = view.findViewById(R.id.vBriefTitle)
    private val description: TextView = view.findViewById(R.id.vBriefDescription)
    private val icon: ImageView = view.findViewById(R.id.vBriefIcon)


    fun bindView(item: Item) {
        title.text = item.data.title
        description.text = item.data.description
        Glide.with(icon.context)
            .load(item.data.icon)
            .into(icon)

    }

    companion object {
        fun create(parent: ViewGroup): BriefCardViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_discovery_card_brief, parent, false)
            return BriefCardViewHolder(view)
        }
    }
}