package com.github.hiratasatoshi.sample.aaccontributors.data.entity

// ユーザー情報
data class UserInfo (
    val name: String,
    val login: String,
    val avatar_url: String,
    val followers: String,
    val following: String,
    val company: String,
    val location: String
)