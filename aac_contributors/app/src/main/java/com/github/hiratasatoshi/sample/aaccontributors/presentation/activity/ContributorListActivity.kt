package com.github.hiratasatoshi.sample.aaccontributors.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.hiratasatoshi.sample.aaccontributors.R
import com.github.hiratasatoshi.sample.aaccontributors.databinding.ActivityContributorListBinding
import com.github.hiratasatoshi.sample.aaccontributors.presentation.adapter.ContributorListAdapter
import com.github.hiratasatoshi.sample.aaccontributors.presentation.viewmodel.ContributorListViewModel

class ContributorListActivity : AppCompatActivity() {

    lateinit var viewModel: ContributorListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(ContributorListViewModel::class.java)

        val binding = DataBindingUtil.setContentView<ActivityContributorListBinding>(this, R.layout.activity_contributor_list)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = ContributorListAdapter(viewModel, this)
        viewModel.list.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })

        binding.contributorList.layoutManager = LinearLayoutManager(this)
        binding.contributorList.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        viewModel.getContributors()
    }

}
