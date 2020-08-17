package com.sai.robot.page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sai.robot.R
import com.sai.robot.dome.adapter.RecycleAdapter
import kotlinx.android.synthetic.main.activity_page.*


//@MainThread
//inline fun <reified VM : ViewModel> ComponentActivity.viewModels(
//    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
//): Lazy<VM> {
//    val factoryPromise = factoryProducer ?: {
//        defaultViewModelProviderFactory
//    }
//
//    return ViewModelLazy(VM::class, { viewModelStore }, factoryPromise)
//}

class PageActivity : AppCompatActivity() {


    companion object {
        fun actionStart(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, PageActivity::class.java))
        }
    }

    //    private val mViewModel: TestViewModel by lazy {
//        ViewModelProvider(this, Injector.getDiscoveryViewModelFactory(DiscoveryRepository()))
//            .get(TestViewModel::class.java)
////        ViewModelProviders.of(this).get(TestViewModel::class.java)
//    }
//    private val recycleAdapter: RecycleAdapter by lazy { RecycleAdapter() }
    private val recycleAdapter: RecycleAdapter by lazy { RecycleAdapter() }

//    private val repositoryFactory: RepositoryFactory by lazy {
//        RepositoryFactory(
//            Room.databaseBuilder(
//                applicationContext,
//                AppDataBase::class.java, "database-name"
//            ).allowMainThreadQueries().build()
//        )
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page)

        initListView()

        //实例化vm

        //充填数据库数据
//        val aa: MutableList<TestEntity> = ArrayList()
//        for (x in 0..100) {
//            aa.add(TestEntity(x, " Test  Page3 分页数据 = ${x}"))
//        }
//        repositoryFactory.testLocalRepository().insertOfData(aa)

        // 获取数据
//        mViewModel.postOfData().observe(this, Observer {
//            recycleAdapter.submitData(lifecycle, it)
//        })
    }

    private fun initListView() {
        // item 装饰
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        vList.addItemDecoration(dividerItemDecoration)
        vList.adapter = recycleAdapter
        vList.layoutManager = LinearLayoutManager(this)

        val mList = listOf(
//            TestBean(" Test  Page3 分页数据 = 1"),
//            TestBean(" Test  Page3 分页数据 = 2"),
//            TestBean(" Test  Page3 分页数据 = 3"),
//            TestBean(" Test  Page3 分页数据 = 4"),
//            TestBean(" Test  Page3 分页数据 = 5"),
//            TestBean(" Test  Page3 分页数据 = 6"),
//            TestBean(" Test  Page3 分页数据 = 7"),
//            TestBean(" Test  Page3 分页数据 = 8")

            " Test  Page3 分页数据 = 1",
            " Test  Page3 分页数据 = 2",
            " Test  Page3 分页数据 = 3",
            " Test  Page3 分页数据 = 4",
            " Test  Page3 分页数据 = 5",
            " Test  Page3 分页数据 = 6",
            " Test  Page3 分页数据 = 7",
            " Test  Page3 分页数据 = 8"
        )
        recycleAdapter.submitList( mList)
    }
}