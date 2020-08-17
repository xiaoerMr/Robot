package com.sai.robot.page.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
//import androidx.paging.PagingData
import com.sai.robot.page.bean.TestBean
import com.sai.robot.page.data.repostory.Repository
import kotlinx.coroutines.flow.catch

class TestViewModel constructor(val repository: Repository) : ViewModel() {

//    fun postOfData(): LiveData<PagingData<TestBean>> =
//        repository.postOfData().catch { viewModelScope }.asLiveData()
}