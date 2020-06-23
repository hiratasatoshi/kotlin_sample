package com.github.hiratasatoshi.sample.aaccontributors.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.hiratasatoshi.sample.aaccontributors.data.entity.Contributor

class ContributorListViewModel: ViewModel() {
    val list = mutableListOf<MutableLiveData<Contributor>>()

    fun getContributors() {
        // TODO Mockデータ
        for (i in 0..10) {
            list.add(MutableLiveData<Contributor>().apply {
                value = Contributor("test", "https://avatars1.githubusercontent.com/u/517315?v=4", i,"")
            })
        }
    }

}