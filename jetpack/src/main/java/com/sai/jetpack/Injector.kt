package com.sai.jetpack

import com.sai.jetpack.repository.RepositoryMain
import com.sai.jetpack.vm.ViewModelFactory

object Injector {

    fun getMainViewModelFactory(repository: RepositoryMain) = ViewModelFactory(repository)
}