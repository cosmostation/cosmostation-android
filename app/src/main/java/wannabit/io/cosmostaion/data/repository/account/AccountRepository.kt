package wannabit.io.cosmostaion.data.repository.account

import wannabit.io.cosmostaion.database.model.BaseAccount

interface AccountRepository {

    suspend fun insertAccount(baseAccount: BaseAccount)

    suspend fun deleteAccount(baseAccount: BaseAccount)

    suspend fun createByMnemonic(name: String, mnemonic: String, lastHDPath: String)

    suspend fun createByPrivate(name: String, privateKey: String)
}