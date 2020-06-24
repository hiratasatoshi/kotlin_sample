package com.github.hiratasatoshi.sample.aaccontributors.presentation.viewmodel

import ContributorsDataStore
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.hiratasatoshi.sample.aaccontributors.data.entity.Contributor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ContributorListViewModel: ViewModel() {
    var list = MutableLiveData<List<Contributor>>()

    fun getContributors() {
        GlobalScope.launch(Dispatchers.IO) {
            val li = ContributorsDataStore().getContributors()
            list.postValue(li)
        }
    }

}