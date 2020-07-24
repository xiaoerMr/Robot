package com.sai.myeye.discovery

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sai.myeye.bean.Item
import com.sai.myeye.discovery.holder.BriefCardViewHolder
import com.sai.myeye.discovery.holder.ColumnCardViewHolder
import com.sai.myeye.discovery.holder.HorizontalViewHolder
import com.sai.myeye.discovery.holder.TextCardViewHolder

// Item
class DiscoveryAdapter : ListAdapter<Item, RecyclerView.ViewHolder>(Discovery_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> TextCardViewHolder.create(parent)
            2 -> BriefCardViewHolder.create(parent)
            4 -> ColumnCardViewHolder.create(parent)
            else -> HorizontalViewHolder.create(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {

        return when (getItem(position).type) {
            "textCard" -> return 1
            "briefCard" -> return 2
            "horizontalScrollCard" -> return 3
            "columnCardList" -> return 4
            else -> 0
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TextCardViewHolder  -> { holder.bindView(getItem(position)) }
            is BriefCardViewHolder -> { holder.bindView(getItem(position)) }
            is ColumnCardViewHolder -> { holder.bindView(getItem(position)) }
            is HorizontalViewHolder -> { holder.bindView(getItem(position)) }
        }
    }

    companion object {
        private val Discovery_COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }
}