package com.sai.myeye.discovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sai.myeye.BaseFragment
import com.sai.myeye.R
import kotlinx.android.synthetic.main.fragment_refresh_layout.*

class DiscoveryFragment : BaseFragment() {
    companion object {
        fun newInstance() = DiscoveryFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(
            inflater.inflate(
                R.layout.fragment_refresh_layout,
                container,
                false
            )
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecycle()
    }

    private fun initRecycle() {
        vList.layoutManager = LinearLayoutManager(activity)
        vList.setHasFixedSize(true)
        vList.itemAnimator = null
        vList.adapter = DiscoveryAdapter()
    }
}