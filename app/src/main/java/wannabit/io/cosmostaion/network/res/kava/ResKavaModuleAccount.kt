package wannabit.io.cosmostaion.network.res.kava

data class ResKavaModuleAccount(val accounts: List<AccountValue>)

data class AccountValue(
    val account_number: Int, val address: String, val name: String, val permissions: List<String>, val public_key: String, val sequence: Int
)