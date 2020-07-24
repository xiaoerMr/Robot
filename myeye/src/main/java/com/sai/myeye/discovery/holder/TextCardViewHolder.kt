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

class TextCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    private val title: TextView = view.findViewById(R.id.vTextTitle)


    fun bindView(item: Item) {
        title.text = item.data.text
    }

    companion object {
        fun create(parent: ViewGroup): TextCardViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_discovery_card_text, parent, false)
            return TextCardViewHolder(view)
        }
    }
}