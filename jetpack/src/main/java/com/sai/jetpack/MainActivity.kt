package com.sai.jetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sai.jetpack.adapter.AdapterBanner
import com.sai.jetpack.bean.ResBannerItem
import com.sai.jetpack.repository.RepositoryMain
import com.sai.jetpack.utils.ToastUtils
import com.sai.jetpack.vm.MainViewModel
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val adapterBanner by lazy {
        AdapterBanner(emptyList())
    }
//    private val mViewModel: MainViewModel by viewModels()

//    private val mViewModel by viewModel<MainViewModel>()
    private val mViewModel by lazy {
        ViewModelProvider(
            this,
            Injector.getMainViewModelFactory(RepositoryMain())
        ).get(MainViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setContentView(R.layout.view_behavior)

        //请求banner 数据
        mViewModel.resultBanner.observe(this, Observer { result ->
            if (result.isSuccess) {
                result.map {
                    setBanner(it)
                }
            } else {
                ToastUtils.show(vBanner, "暂时没有数据")
            }
        })
        mViewModel.onRefreshBanner()
        mViewModel.bann.observe(this, Observer {
        })
        mViewModel.dd()
        // 列表数据
    }

    private fun setBanner(it: List<ResBannerItem>) {
        vBanner.let { banner ->
            banner.addBannerLifecycleObserver(this)
            banner.setIndicator(CircleIndicator(this))
            banner.setBannerRound(20f)

            adapterBanner.setDatas(it)
            banner.adapter = adapterBanner
        }
    }

}
