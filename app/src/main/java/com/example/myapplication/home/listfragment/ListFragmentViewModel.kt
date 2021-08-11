package com.example.myapplication.home.listfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.ApiDataRepository

class ListFragmentViewModel(private val apiRepository: ApiDataRepository) : ViewModel() {

    val isUserDataFetching = MutableLiveData(false)

    val userList = apiRepository.apiUserList()

    class Factory(
        private val apiRepository: ApiDataRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ListFragmentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ListFragmentViewModel(apiRepository) as T
            }
            throw IllegalArgumentException("Unable to construct ViewModel")
        }

    }

}