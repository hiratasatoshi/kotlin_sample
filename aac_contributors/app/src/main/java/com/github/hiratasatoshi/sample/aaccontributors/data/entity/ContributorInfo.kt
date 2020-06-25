package com.github.hiratasatoshi.sample.aaccontributors.data.entity

// AACリポジトリのContributor情報
data class ContributorInfo (
    val login: String,
    val avatar_url: String,
    val contributions: Int,
    val url: String
)