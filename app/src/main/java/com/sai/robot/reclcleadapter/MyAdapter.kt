package com.sai.robot.reclcleadapter

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class MyAdapter<T> constructor(private val holder: ViewHolder): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private var data :List<T>? = null

    fun setNewData(data :List<T>){
        this.data = data
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(getItemLayoutId(), parent, false)
        return ViewHolder(inflate)
    }

    abstract fun getItemLayoutId(): Int

    override fun getItemCount(): Int {
        data?.size
        return if (data.isNullOrEmpty()) {
            0
        }else{
            data!!.size
        }
    }

    override fun getItemViewType(position: Int): Int {

        return setItemViewType(position)
    }

    abstract fun setItemViewType(position: Int): Int

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        getItemViewType(position)
        bindDataToViewHolder(holder,position)
    }

    abstract fun bindDataToViewHolder(holder: ViewHolder, position: Int)


    // ViewHolder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}