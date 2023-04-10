package com.muazzeznihalbahadir.invio_case.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.muazzeznihalbahadir.invio_case.api.Repository

class SharedViewModelFactory (private val repository: Repository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SharedViewModel(repository) as T
    }
}