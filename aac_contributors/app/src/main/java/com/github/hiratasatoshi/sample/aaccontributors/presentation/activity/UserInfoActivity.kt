package com.github.hiratasatoshi.sample.aaccontributors.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.github.hiratasatoshi.sample.aaccontributors.R
import com.github.hiratasatoshi.sample.aaccontributors.databinding.ActivityUserInfoBinding
import com.github.hiratasatoshi.sample.aaccontributors.presentation.viewmodel.ContributorDetailViewModel


class UserInfoActivity: AppCompatActivity() {

    lateinit var viewModel: ContributorDetailViewModel

    companion object {
        const val EXTRA_KEY_LOGIN = "login"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(ContributorDetailViewModel::class.java)

        val binding = DataBindingUtil.setContentView<ActivityUserInfoBinding>(this, R.layout.activity_user_info)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val login = intent.getStringExtra(EXTRA_KEY_LOGIN)

        viewModel.getContributorDetail(login)



    }

}
