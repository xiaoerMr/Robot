package com.sai.myeye.discovery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sai.myeye.BaseFragment
import com.sai.myeye.Injector
import com.sai.myeye.R
import kotlinx.android.synthetic.main.fragment_refresh_layout.*

class DiscoveryFragment : BaseFragment() {

    val adapter = DiscoveryAdapter()

    private val viewModel by lazy {
        ViewModelProvider(this, Injector.getDiscoveryViewModelFactory(DiscoveryRepository()))
            .get(DiscoveryViewModel::class.java)
    }

    companion object {
        fun newInstance() = DiscoveryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        viewModel.onRefresh()

        viewModel.result.observe(viewLifecycleOwner, Observer { result ->
            val response = result.getOrNull()
            response?.let {
                viewModel.nextPageUrl = it.nextPageUrl
                it.itemList.let { item ->
                    adapter.submitList(item)
                }
            }
        })
    }


    private fun initRecycle() {
        vList.layoutManager = LinearLayoutManager(activity)
        vList.setHasFixedSize(true)
        vList.itemAnimator = null
        vList.adapter = adapter
    }
}