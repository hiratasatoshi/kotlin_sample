package com.github.hiratasatoshi.sample.aaccontributors.presentation.viewmodel

import com.github.hiratasatoshi.sample.aaccontributors.data.remote.ContributorsDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.hiratasatoshi.sample.aaccontributors.data.entity.ContributorInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ContributorListViewModel: ViewModel() {
    private var _list = MutableLiveData<List<ContributorInfo>>()
    var list: LiveData<List<ContributorInfo>> = _list

    fun getContributors() {
        GlobalScope.launch(Dispatchers.IO) {
            val li = ContributorsDataStore().getContributors()
            _list.postValue(li)
        }
    }
}
