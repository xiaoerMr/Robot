package com.sai.myeye

import com.sai.myeye.discovery.DiscoveryRepository
import com.sai.myeye.discovery.DiscoveryViewModelFactory

object Injector {

    fun getDiscoveryViewModelFactory(repository: DiscoveryRepository) = DiscoveryViewModelFactory(repository)

}