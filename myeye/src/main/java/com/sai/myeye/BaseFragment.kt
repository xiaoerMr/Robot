package com.sai.myeye

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseFragment :Fragment() {
    public val TAG = this.javaClass.simpleName

    /**
     * Fragment中显示加载等待的控件。
     */
    protected var loading: ProgressBar? = null

    lateinit var activity: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as AppCompatActivity
    }

    fun onCreateView(view:View): View {
//        loading = view.findViewById(R.id.loading)
        return view
    }

    fun loadFinished(){
        loading?.let {
            it.visibility = View.GONE
        }
    }
    fun loadStart(){
        loading?.let {
            it.visibility = View.VISIBLE
        }
    }
    fun loadFailed(){
        loading?.let {
            it.visibility = View.GONE
        }
    }
}