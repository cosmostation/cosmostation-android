package wannabit.io.cosmostaion.data.repository.account

import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.model.BaseAccount

class AccountRepositoryImpl : AccountRepository {

    override suspend fun insertAccount(baseAccount: BaseAccount) {
        AppDatabase.getInstance().baseAccountDao().insert(baseAccount)
    }

    override suspend fun deleteAccount(baseAccount: BaseAccount) {
        AppDatabase.getInstance().baseAccountDao().delete(baseAccount)
    }
}