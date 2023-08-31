package wannabit.io.cosmostaion.database.legacy

import net.sqlcipher.database.SQLiteDatabase
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.CryptoHelper
import wannabit.io.cosmostaion.database.legacy.model.Account
import wannabit.io.cosmostaion.database.legacy.model.MWords
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.database.model.Password

object LegacyMigrationHelper {
    private fun getDatabase(): SQLiteDatabase {
        return LegacyDatabase.instance.getWritableDatabase(CosmostationConstants.LEGACY_DATABASE_KEY)
    }

    suspend fun migratePassword() {
        if (AppDatabase.getInstance().passwordDao().selectAll().isNotEmpty()) {
            return
        }

        val cursor = getDatabase().query(CosmostationConstants.LEGACY_DB_TABLE_PASSWORD, arrayOf("resource", "spec"), null, null, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            val resource = cursor.getString(0)
            AppDatabase.getInstance().passwordDao().insert(Password(0, resource))
        }
        cursor.close()
    }

    suspend fun migrateWallet() {
        if (AppDatabase.getInstance().baseAccountDao().selectAll().isNotEmpty()) {
            return
        }

        val legacyAllMnemonics = getLegacyAllMnemonics()
        val legacyAccounts = getLegacyAllAccounts()
        val legacyAccountsByPrivateKey = getLegacyAccountsByPrivateKey()

        val newBaseAccount = mutableListOf<BaseAccount>()
        legacyAllMnemonics.forEach {
            val hexEntropy = CryptoHelper.doDecryptData(CosmostationConstants.ENCRYPT_MNEMONIC_KEY + it.uuid, it.resource, it.spec)
            newBaseAccount.add(BaseAccount(it.uuid, it.resource, it.spec, it.nickName, BaseAccountType.MNEMONIC, "0"))
        }

        val pkeyList = mutableListOf<String>()
        legacyAccountsByPrivateKey.forEach {
            CryptoHelper.doDecryptData(CosmostationConstants.ENCRYPT_PRIVATE_KEY + it.uuid, it.resource, it.spec)?.let { pKey ->
                if (!pkeyList.contains(pKey)) {
                    pkeyList.add(pKey)
                    newBaseAccount.add(BaseAccount(it.uuid, it.resource, it.spec, it.nickName, BaseAccountType.PRIVATE_KEY, "0"))
                }
            }
        }

        legacyAccounts.filter { it.hasPrivateKey && !it.fromMnemonic }.forEach {
            //TODO private key accounts
        }
        legacyAccounts.filter { it.hasPrivateKey && it.customPath != 0 }.forEach {
            //TODO custom chains
        }
        legacyAccounts.filter { it.hasPrivateKey && "0" != it.path }.forEach {
            //TODO custom path
        }

        AppDatabase.getInstance().baseAccountDao().insertAll(newBaseAccount)
    }

    private fun getLegacyAllMnemonics(): MutableList<MWords> {
        val cursor = getDatabase().query(
            CosmostationConstants.LEGACY_DB_TABLE_MNEMONIC, arrayOf("id", "uuid", "resource", "spec", "nickName", "wordsCnt", "isFavo", "importTime"), null, null, null, null, null
        )
        val mnemonics = mutableListOf<MWords>()
        if (cursor != null && cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(0)
                val uuid = cursor.getString(1)
                val resource = cursor.getString(2)
                val spec = cursor.getString(3)
                val nickname = cursor.getString(4)
                val wordsCount = cursor.getInt(5)
                val isLedger = cursor.getInt(6) > 0
                val importTime = cursor.getLong(7)
                mnemonics.add(MWords(id, uuid, resource, spec, nickname, wordsCount, isLedger, importTime))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return mnemonics
    }

    private fun getLegacyAllAccounts(): MutableList<Account> {
        val cursor = getDatabase().query(
            CosmostationConstants.LEGACY_DB_TABLE_ACCOUNT, arrayOf(
                "id",
                "uuid",
                "nickName",
                "isFavo",
                "address",
                "baseChain",
                "hasPrivateKey",
                "resource",
                "spec",
                "fromMnemonic",
                "path",
                "isValidator",
                "sequenceNumber",
                "accountNumber",
                "fetchTime",
                "msize",
                "importTime",
                "lastTotal",
                "sortOrder",
                "pushAlarm",
                "newBip",
                "customPath",
                "mnemonicId"
            ), null, null, null, null, null
        )
        val accounts = mutableListOf<Account>()
        if (cursor != null && cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(0)
                val uuid = cursor.getString(1)
                val nickname = cursor.getString(2)
                val isLedger = cursor.getInt(3) > 0
                val address = cursor.getString(4)
                val baseChain = cursor.getString(5)
                val hasPrivateKey = cursor.getInt(6) > 0
                val resource = cursor.getString(7)
                val spec = cursor.getString(8)
                val fromMnemonic = cursor.getInt(9) > 0
                val path = cursor.getString(10)
                val isValidator = cursor.getInt(11) > 0
                val sequenceNumber = cursor.getInt(12)
                val accountNumber = cursor.getInt(13)
                val fetchTime = cursor.getLong(14)
                val mSize = cursor.getInt(15)
                val importTime = cursor.getLong(16)
                val sortOrder = cursor.getLong(18)
                val pushAlarm = cursor.getInt(19) > 0
                val newBip = cursor.getInt(20) > 0
                val customPath = cursor.getInt(21)
                val mnemonicId = cursor.getLong(22)
                accounts.add(
                    Account(
                        id,
                        uuid,
                        nickname,
                        isLedger,
                        address,
                        baseChain,
                        hasPrivateKey,
                        resource,
                        spec,
                        fromMnemonic,
                        path,
                        isValidator,
                        sequenceNumber,
                        accountNumber,
                        fetchTime,
                        mSize,
                        importTime,
                        sortOrder,
                        pushAlarm,
                        newBip,
                        customPath,
                        mnemonicId
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return accounts
    }

    private fun getLegacyAccountsByPrivateKey(): MutableList<Account> {
        val accounts = mutableListOf<Account>()
        for (account in getLegacyAllAccounts()) {
            if (account.hasPrivateKey && account.fromMnemonic) {
                accounts.add(account)
            }
        }
        return accounts
    }
}