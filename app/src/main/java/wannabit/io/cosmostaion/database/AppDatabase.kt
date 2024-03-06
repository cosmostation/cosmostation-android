package wannabit.io.cosmostaion.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import wannabit.io.cosmostaion.database.dao.AddressBookDao
import wannabit.io.cosmostaion.database.dao.BaseAccountDao
import wannabit.io.cosmostaion.database.dao.PasswordDao
import wannabit.io.cosmostaion.database.dao.RefAddressDao
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.Password
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.ui.main.CosmostationApp

@Database(entities = [BaseAccount::class, RefAddress::class, AddressBook::class, Password::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun baseAccountDao(): BaseAccountDao
    abstract fun refAddressDao(): RefAddressDao
    abstract fun addressBookDao(): AddressBookDao
    abstract fun passwordDao(): PasswordDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    val builder = Room.databaseBuilder(CosmostationApp.instance, AppDatabase::class.java, "cosmostation_wallet.db")
                    val factory = SupportFactory(SQLiteDatabase.getBytes(Prefs.passphrase.toCharArray()))
                    builder.openHelperFactory(factory)

                    builder.addMigrations(MIGRATION_1_2)
                    instance = builder.build()
                }
            }

            return instance!!
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE refAddress ADD COLUMN evmAddress TEXT")
            }
        }
    }
}