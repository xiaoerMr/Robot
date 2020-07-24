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
import com.sai.myeye.utils.LogUtils

class ColumnCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    private val ts: ImageView = view.findViewById(R.id.vColumnTs)
    private val te: ImageView = view.findViewById(R.id.vColumnTe)
    private val bs: ImageView = view.findViewById(R.id.vColumnBs)
    private val be: ImageView = view.findViewById(R.id.vColumnBe)

    private val tst: TextView = view.findViewById(R.id.vColumnTsT)
    private val tet: TextView = view.findViewById(R.id.vColumnTeT)
    private val bst: TextView = view.findViewById(R.id.vColumnBsT)
    private val bet: TextView = view.findViewById(R.id.vColumnBeT)


    fun bindView(item: Item) {
        Glide.with(ts.context)
            .load(item.data.itemList[0].data.image)
            .into(ts)
        Glide.with(te.context)
            .load(item.data.itemList[1].data.image)
            .into(te)
        Glide.with(bs.context)
            .load(item.data.itemList[2].data.image)
            .into(bs)
        Glide.with(be.context)
            .load(item.data.itemList[3].data.image)
            .into(be)
        tst.text=item.data.itemList[0].data.title
        tet.text=item.data.itemList[1].data.title
        bst.text=item.data.itemList[2].data.title
        bet.text=item.data.itemList[3].data.title
    }

    companion object {
        fun create(parent: ViewGroup): ColumnCardViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_discovery_card_column, parent, false)
            return ColumnCardViewHolder(view)
        }
    }
}