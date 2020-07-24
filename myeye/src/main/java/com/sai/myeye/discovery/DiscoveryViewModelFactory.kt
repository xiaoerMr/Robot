package com.sai.myeye.discovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DiscoveryViewModelFactory(private val repository: DiscoveryRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DiscoveryViewModel(repository) as T
    }
}