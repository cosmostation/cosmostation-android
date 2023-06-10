package wannabit.io.cosmostaion.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import wannabit.io.cosmostaion.database.dao.PasswordDao
import wannabit.io.cosmostaion.database.dao.WalletDao
import wannabit.io.cosmostaion.database.model.Password
import wannabit.io.cosmostaion.database.model.Wallet
import wannabit.io.cosmostaion.ui.main.CosmostationApp

@Database(entities = [Wallet::class, Password::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun walletDao(): WalletDao
    abstract fun passwordDao(): PasswordDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(CosmostationApp.instance, AppDatabase::class.java, "cosmostation_wallet.db").build()
                }
            }

            return instance!!
        }
    }
}