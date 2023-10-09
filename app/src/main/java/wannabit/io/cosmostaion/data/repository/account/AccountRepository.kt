package wannabit.io.cosmostaion.data.repository.account

import wannabit.io.cosmostaion.database.model.BaseAccount

interface AccountRepository {

    suspend fun insertAccount(baseAccount: BaseAccount)

    suspend fun deleteAccount(baseAccount: BaseAccount)
}