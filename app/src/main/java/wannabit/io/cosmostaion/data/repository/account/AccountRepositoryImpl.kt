package wannabit.io.cosmostaion.data.repository.account

import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount

class AccountRepositoryImpl : AccountRepository {

    override suspend fun insertAccount(baseAccount: BaseAccount) {
        AppDatabase.getInstance().baseAccountDao().insert(baseAccount)
    }

    override suspend fun deleteAccount(baseAccount: BaseAccount) {
        AppDatabase.getInstance().baseAccountDao().delete(baseAccount)
    }

    override suspend fun createByMnemonic(name: String, mnemonic: String, lastHDPath: String) {
        val account = BaseAccount.createByMnemonic(name, mnemonic, lastHDPath)
        account?.let {
            val id = AppDatabase.getInstance().baseAccountDao().insert(account)
            Prefs.lastAccountId = id
            BaseData.baseAccount = BaseData.getLastAccount()
        }
    }

    override suspend fun createByPrivate(name: String, privateKey: String) {
        val account = BaseAccount.createByPrivate(name, privateKey)
        account?.let {
            val id = AppDatabase.getInstance().baseAccountDao().insert(account)
            Prefs.lastAccountId = id
            BaseData.baseAccount = BaseData.getLastAccount()
        }
    }
}