package com.github.hiratasatoshi.sample.aaccontributors.presentation.viewmodel

import com.github.hiratasatoshi.sample.aaccontributors.data.remote.ContributorsDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.hiratasatoshi.sample.aaccontributors.data.entity.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserInfoViewModel: ViewModel() {
    private val _detail = MutableLiveData<UserInfo>()
    val detail: LiveData<UserInfo> = _detail

    fun getContributorDetail(login: String?) {
        login ?: return
        
        GlobalScope.launch(Dispatchers.IO) {
            val data = ContributorsDataStore().getContributorDetail(login)
            _detail.postValue(data)
        }
    }
}
