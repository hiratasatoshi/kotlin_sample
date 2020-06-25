package com.github.hiratasatoshi.sample.aaccontributors.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.hiratasatoshi.sample.aaccontributors.R
import com.github.hiratasatoshi.sample.aaccontributors.data.entity.ContributorInfo
import com.github.hiratasatoshi.sample.aaccontributors.databinding.ActivityContributorListBinding
import com.github.hiratasatoshi.sample.aaccontributors.presentation.adapter.ContributorListAdapter
import com.github.hiratasatoshi.sample.aaccontributors.presentation.viewmodel.ContributorListViewModel

class ContributorListActivity : AppCompatActivity(), ContributorListAdapter.ItemClickListener {

    lateinit var viewModel: ContributorListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(ContributorListViewModel::class.java)

        val binding = DataBindingUtil.setContentView<ActivityContributorListBinding>(this, R.layout.activity_contributor_list)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = ContributorListAdapter(viewModel, this, this)
        viewModel.list.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })

        binding.contributorList.layoutManager = LinearLayoutManager(this)
        binding.contributorList.adapter = adapter

        viewModel.getContributors()
    }


    override fun onClick(item: ContributorInfo?) {
        if (item == null) {
            Toast.makeText(this, "No available data", Toast.LENGTH_SHORT)
            return
        }
        val intent = Intent()
        intent.setClass(this, UserInfoActivity::class.java)
        intent.putExtra(UserInfoActivity.EXTRA_KEY_LOGIN, item.login)
        startActivity(intent)
    }

}
