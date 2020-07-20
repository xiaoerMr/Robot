package com.sai.robot.dome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sai.robot.dome.data.SearchRepository

class ViewModelFactory(private val repository: SearchRepository) :ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }
}