package com.sai.robot.dome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.sai.robot.R
import com.sai.robot.dome.adapter.RecycleAdapter
import com.sai.robot.dome.data.SearchRepository
import kotlinx.android.synthetic.main.activity_paging.*

/**
 *  MVVM 模式
 *  jectpack库
 */
class PagingActivity : AppCompatActivity() {


    companion object {
        fun actionStart(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, PagingActivity::class.java))
        }
    }

    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)

        // lifedata 模式  ViewModelProvider 需要 factory  vm 对象
        // 创建那个 ViewModel 对象，和 ViewModel 对象 持有那个仓库
         viewModel = ViewModelProvider(
            this,
            ViewModelFactory(SearchRepository())
        ).get(SearchViewModel::class.java)

        // item 装饰
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        vRecycler.addItemDecoration(dividerItemDecoration)

        //适配器
        initAdapter()

        initSearch()

    }

    private fun initSearch() {
        vSearch.setOnClickListener {

            viewModel.getNewData("")

            vRecycler.visibility = View.VISIBLE
            vEmpty.visibility = View.GONE
        }
    }

    private fun initAdapter() {
        vRecycler.visibility = View.GONE
        vEmpty.visibility = View.VISIBLE


        //初始化适配器
        val recycleAdapter = RecycleAdapter()
        vRecycler.adapter = recycleAdapter


        //订阅Livedata 并更新到 recycle view
        viewModel.queryLiveData.observe(this, Observer<List<String>>{
            recycleAdapter.submitList(it)
        })
    }
}