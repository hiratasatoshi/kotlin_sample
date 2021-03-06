package com.github.hiratasatoshi.sample.aaccontributors.presentation.activity

import android.os.Bundle
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.hiratasatoshi.sample.aaccontributors.R
import com.github.hiratasatoshi.sample.aaccontributors.databinding.ActivityUserInfoBinding
import com.github.hiratasatoshi.sample.aaccontributors.presentation.viewmodel.UserInfoViewModel

// ユーザー詳細画面
class UserInfoActivity: AppCompatActivity() {

    lateinit var viewModel: UserInfoViewModel

    companion object {
        // ユーザーを識別するための値のキー(起動側で指定)
        const val EXTRA_KEY_LOGIN = "login"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewModel生成とデータバインディング
        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(UserInfoViewModel::class.java)

        val binding = DataBindingUtil.setContentView<ActivityUserInfoBinding>(this, R.layout.activity_user_info)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val login = intent.getStringExtra(EXTRA_KEY_LOGIN)
        viewModel.getContributorDetail(login)

        // ユーザー詳細情報のラベルと値が同タイミングで表示されるよう
        // LiveDataの更新契機でフェードイン
        viewModel.detail.observe(this, Observer {
            val animation = AlphaAnimation(0.0f, 1.0f)
            animation.duration = 200
            binding.detailInfoContainer.startAnimation(animation)
        })

    }

}
