package com.sai.myeye.commend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sai.myeye.BaseFragment
import com.sai.myeye.R

class CommendFragment : BaseFragment(){
    companion object {
        fun newInstance() = CommendFragment()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater.inflate(R.layout.fragment_refresh_layout, container, false))
    }
}