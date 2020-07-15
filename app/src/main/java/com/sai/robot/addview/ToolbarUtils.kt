package com.sai.robot.addview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.sai.robot.addview.adapter.*
import java.util.HashMap

class ToolbarUtils constructor(
    private val contentView: View,
    private val activity: AppCompatActivity
) {

    private var currentViewHolder: ViewTypeViewHolder? = null

    private lateinit var contentParent: ViewGroup
    private val viewHolderLists: HashMap<Any, ViewTypeViewHolder> = HashMap()
    private var viewAdapterLists: HashMap<Any, ViewTypeAdapter<*>> = HashMap()
    lateinit var decorView: View
        private set

    // 父布局
    private var parent: ViewGroup = contentView.parent as ViewGroup

    init {
        //添加页面布局view 并显示
        register(ViewType.CONTENT, SimpleContentAdapter())
        setDecorAdapter(LinearDecorAdapter(listOf()))
    }

    constructor(activity: AppCompatActivity) : this(
        (activity.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0),
        activity
    )

    fun register(viewType: ViewType, adapter: ViewTypeAdapter<*>) {
        viewAdapterLists[viewType] = adapter
    }

    fun setDecorAdapter(decorAdapter: DecorAdapter) {
        currentViewHolder = null

        if (parent != null) {

            val indexOfChild = parent.indexOfChild(contentView)
            // 移除以前到view
            if (indexOfChild >= 0) {
                parent.removeView(contentView)
            } else {
//                LogUtils().e("decorView = ${decorView == null}")
                parent.removeView(decorView)
                (contentView.parent as ViewGroup).removeView(contentView)
            }
//            LogUtils().e("indexOfChild = $indexOfChild ")

            decorView = decorAdapter.decorView()
            parent.addView(decorView, indexOfChild)
        } else {
            decorView = decorAdapter.decorView()
        }
        contentParent = decorAdapter.getContentParent(decorView)

        //显示
        showView(ViewType.CONTENT)
    }
    private fun DecorAdapter.decorView():View{
        val onCreateDecorView = onCreateDecorView(LayoutInflater.from(contentView.context))

        onCreateDecorView.also { decorView ->
            if (contentView.layoutParams != null) {
                decorView.layoutParams = contentView.layoutParams
            }
        }
        return onCreateDecorView
    }


    private fun showView(type: ViewType) {
        if (currentViewHolder == null) {
            addView(type)
        } else {
            currentViewHolder?.let {
                if (type != it.viewType && it.rootView.parent !=null) {
                    contentParent.removeView(it.rootView)
                    addView(type)
                }
            }
        }
    }

    private fun addView(type: ViewType) {
        val viewHolder = getViewHolder(type)
        val rootView = viewHolder.rootView
        if (rootView.parent != null) {
            (rootView.parent as ViewGroup).removeView(rootView)
        }
        contentParent.addView(rootView)
        currentViewHolder = viewHolder

    }



    fun setDecorHeader(vararg viewType: ViewType) {
        val views = mutableListOf<View>()
        for (type in viewType) {
            views.add(getViewHolder(type).rootView)
        }

        // 添加到垂直布局中 LinearLayout
        val linearDecorAdapter = LinearDecorAdapter(views)
        //组装页面
        setDecorAdapter(linearDecorAdapter)
    }

    private fun getViewHolder(type: ViewType): ViewTypeViewHolder {
//        LogUtils().e("----viewHolderLists{${type}}= ${viewHolderLists[type] == null}")

        if (viewHolderLists[type] == null) {
            addViewTypeViewHolder(type)
        }
        return viewHolderLists[type] as ViewTypeViewHolder
    }


    //为了解决  addViewTypeViewHolder()#onBindViewHolder（） 报错
    @Suppress("UNCHECKED_CAST")
    fun <T : ViewTypeAdapter<out ViewTypeViewHolder>> getAdapter(viewType: Any) =
        viewAdapterLists[viewType] as T

    private fun addViewTypeViewHolder(type: ViewType) {
        val adapter: ViewTypeAdapter<ViewTypeViewHolder> = getAdapter(type)

        val holder = if (adapter is ContentAdapter<*>) {
            adapter.onCreateViewHolder(contentView)
        } else {
            adapter.onCreateViewHolder(
                LayoutInflater.from(contentParent.context),
                contentParent
            )
        }
        holder.viewType = type
        viewHolderLists[type] = holder
        adapter.onBindViewHolder(holder)
    }
}