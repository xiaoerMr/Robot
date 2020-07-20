package com.sai.robot.dome.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sai.robot.R

class StringViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val name: TextView = view.findViewById(R.id.string_item_content)


    fun bindView(title: String){
        name.text = title
    }
    companion object {
        fun create(parent: ViewGroup): StringViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.string_view_item, parent, false)
            return StringViewHolder(view)
        }
    }
}
