package com.fulldive.wallet.models.local

class AccountSecrets(
    val entropy: String,
    val mnemonic: List<String>,
    val address: String,
    val path: Int,
    val customPath: Int
)
