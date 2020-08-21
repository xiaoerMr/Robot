package com.sai.jetpack.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sai.jetpack.repository.RepositoryMain

class ViewModelFactory(val repository: RepositoryMain) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}