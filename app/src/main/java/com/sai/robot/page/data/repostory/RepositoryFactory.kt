package com.sai.robot.page.data.repostory

//import androidx.paging.PagingConfig
import com.sai.robot.page.data.local.AppDataBase

/**
 * 该数据源类，应该注入到ViewModel中
 */
class RepositoryFactory (val appDataBase: AppDataBase){
    //本地数据库 AppDataBase



    // 不同数据源 = 数据源实现类
//    fun testLocalRepository():Repository = TestRepositoryImpl(appDataBase,pagingConfig)
//
//    fun test1LocalRepository():Repository = TestRepositoryImpl(appDataBase,pagingConfig)

    // 分页参数
//    val pagingConfig = PagingConfig(
//        // 每页显示的数据的大小
//        pageSize = 15,
//
//        // 开启占位符
//        enablePlaceholders = true,
//
//        // 预刷新的距离，距离最后一个 item 多远时加载数据
//        prefetchDistance = 3,
//
//
//        /**
//         * 初始化加载数量，默认为 pageSize * 3
//         *
//         * internal const val DEFAULT_INITIAL_PAGE_MULTIPLIER = 3
//         * val initialLoadSize: Int = pageSize * DEFAULT_INITIAL_PAGE_MULTIPLIER
//         */
//        initialLoadSize = 45,
//
//        /**
//         * 一次应在内存中保存的最大数据
//         * 这个数字将会触发，滑动加载更多的数据
//         */
//        maxSize = 45
//    )

//    作者：HiDhl
//    链接：https://juejin.im/post/6844904193468137486
//    来源：掘金
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}