package com.sai.myeye.discovery.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sai.myeye.R
import com.sai.myeye.bean.Item
import com.sai.myeye.bean.ItemX
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

class HorizontalViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val banner: Banner<List<ItemX>, BannerAdapter> = view.findViewById(R.id.vBanner)

    fun bindView(item: Item) {
        val itemList = item.data.itemList
        banner.adapter = BannerAdapter(itemList)
    }

    companion object {
        fun create(parent: ViewGroup): HorizontalViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_discovery, parent, false)
            return HorizontalViewHolder(view)
        }
    }

   inner class BannerAdapter(data: List<ItemX>) : BannerImageAdapter<ItemX>(data) {
        override fun onBindView(
            holder: BannerImageHolder?,
            data: ItemX?,
            position: Int,
            size: Int
        ) {

            holder?.let {
                Glide.with(it.imageView.context)
                    .load(data!!.data.image)
                    .into(it.imageView)
            }
        }
    }
}