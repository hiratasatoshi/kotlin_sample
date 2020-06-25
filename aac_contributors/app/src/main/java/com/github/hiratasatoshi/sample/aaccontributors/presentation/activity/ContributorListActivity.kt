package com.github.hiratasatoshi.sample.aaccontributors.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.hiratasatoshi.sample.aaccontributors.R
import com.github.hiratasatoshi.sample.aaccontributors.data.entity.ContributorInfo
import com.github.hiratasatoshi.sample.aaccontributors.databinding.ActivityContributorListBinding
import com.github.hiratasatoshi.sample.aaccontributors.presentation.adapter.ContributorListAdapter
import com.github.hiratasatoshi.sample.aaccontributors.presentation.viewmodel.ContributorListViewModel

// contributor一覧表示画面
class ContributorListActivity : AppCompatActivity(), ContributorListAdapter.ItemClickListener {

    lateinit var viewModel: ContributorListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewModel生成とデータバインディング
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
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.contributorList.addItemDecoration(itemDecoration)

        // contributor一覧取得
        viewModel.getContributors()
    }

    // contributor一覧クリック
    override fun onContributorClick(item: ContributorInfo?) {
        if (item == null) {
            Toast.makeText(this, "No available data", Toast.LENGTH_SHORT).show()
            return
        }

        // ユーザー詳細画面を表示
        val intent = Intent()
        intent.setClass(this, UserInfoActivity::class.java)
        intent.putExtra(UserInfoActivity.EXTRA_KEY_LOGIN, item.login)
        startActivity(intent)
    }

}
