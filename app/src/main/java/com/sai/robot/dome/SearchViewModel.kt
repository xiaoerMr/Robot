package com.sai.robot.dome

import androidx.lifecycle.*

import com.sai.robot.dome.data.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * 持有仓库 对象，由仓库提供 从http来的数据 和 从sql来到数据
 */
class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    private val queryLiveData = MutableLiveData<String>()

    //    //将输入LiveDate转成结果LiveDate
    val queryResult = Transformations.switchMap(queryLiveData) { query ->
        liveData {
            emit(request(query))
        }
    }


    fun getNewDataBySearch(query: String) {
        queryLiveData.postValue(query)
    }

    //与 launch、async、runBlocking 类似 withContext 也属于 Coroutine builders。
    // 不过与他们不同的是，其他几个都是创建一个新的协程，而 withContext 不会创建新的协程
    // withContext 允许更改协程的执行线程，withContext 在使用时需要传递一个 CoroutineContext 。
    private suspend fun request(query: String) = withContext(Dispatchers.IO) {

        val data = repository.getData(query)

        data
    }
}