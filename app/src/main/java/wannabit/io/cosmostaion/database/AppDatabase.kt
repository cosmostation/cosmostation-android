package wannabit.io.cosmostaion.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import wannabit.io.cosmostaion.database.dao.BaseAccountDao
import wannabit.io.cosmostaion.database.dao.PasswordDao
import wannabit.io.cosmostaion.database.dao.RefAddressDao
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.Password
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.ui.main.CosmostationApp

@Database(entities = [BaseAccount::class, RefAddress::class, Password::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun baseAccountDao(): BaseAccountDao
    abstract fun refAddressDao(): RefAddressDao
    abstract fun passwordDao(): PasswordDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    val builder = Room.databaseBuilder(CosmostationApp.instance, AppDatabase::class.java, "cosmostation_wallet.db")
                    val factory = SupportFactory(SQLiteDatabase.getBytes(Prefs.passphrase.toCharArray()))
                    builder.openHelperFactory(factory)
                    instance = builder.build()
                }
            }

            return instance!!
        }
    }
}