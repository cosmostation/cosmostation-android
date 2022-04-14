package com.fulldive.wallet.models.local

import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.dao.Balance

class AccountWithBalances(
    val account: Account,
    val balances: List<Balance>
)
