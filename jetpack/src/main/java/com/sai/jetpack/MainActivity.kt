package com.sai.jetpack

import android.R.attr.banner
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.sai.jetpack.adapter.AdapterBanner
import com.sai.jetpack.bean.ResBannerItem
import com.sai.jetpack.vm.MainViewModel
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val adapterBanner by lazy {
        AdapterBanner(emptyList())
    }

    private val mViewModel by lazy {
        ViewModelProvider(
            this,
            Injector.getMainViewModelFactory()
        ).get(MainViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setContentView(R.layout.view_behavior)

        //请求banner 数据
        mViewModel.resultBanner.observe(this, Observer { result ->
            result.map {
                vBanner.let { banner ->
                    banner.addBannerLifecycleObserver(this)
                    banner.setIndicator(CircleIndicator(this))
                    banner.setBannerRound(20f)

                    adapterBanner.setDatas(it)
                    banner.adapter = adapterBanner
                }
            }
        })
        mViewModel.onRefreshBanner()

    }

}
