package wannabit.io.cosmostaion.base;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.Mnemonic;
import wannabit.io.cosmostaion.dao.Password;

public class BaseData {

    private BaseApplication     mApp;
    private SharedPreferences   mSharedPreferences;
    private SQLiteDatabase      mSQLiteDatabase;


    public BaseData(BaseApplication apps) {
        this.mApp = apps;
        this.mSharedPreferences = getSharedPreferences();
        SQLiteDatabase.loadLibs(mApp);
    }

    private SharedPreferences getSharedPreferences() {
        if(mSharedPreferences == null)
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mApp);
        return mSharedPreferences;
    }

    public SQLiteDatabase getBaseDB() {
        if(mSQLiteDatabase == null) {
            mSQLiteDatabase = BaseDB.getInstance(mApp).getWritableDatabase(mApp.getString(R.string.db_password));
        }
        return mSQLiteDatabase;
    }



    public void setUsingFingerprint(boolean using) {
        getSharedPreferences().edit().putBoolean(BaseConstant.SET_USE_FINGERPRINT, using).commit();
    }

    public boolean getUsingFingerprint() {
        return getSharedPreferences().getBoolean(BaseConstant.SET_USE_FINGERPRINT, false);
    }











    public Password onSelectPassword() {
        Password result = null;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_PASSWORD, new String[]{"resource", "spec"}, null, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            result = new Password(cursor.getString(0), cursor.getString(1));
        }
        cursor.close();
        return result;
    }

    public boolean onHasPassword() {
        boolean existed = false;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_PASSWORD, new String[]{"resource", "spec"}, null, null, null, null, null);
        if(cursor != null && cursor.getCount() > 0) {
            existed = true;
        }
        cursor.close();
        return existed;
    }

    public long onInsertPassword(Password password) {
        long result = -1;
        if(onHasPassword()) return result;

        ContentValues values = new ContentValues();
        values.put("resource",  password.resource);
        values.put("spec",      password.spec);
        return getBaseDB().insertOrThrow(BaseConstant.DB_TABLE_PASSWORD, null, values);
    }



    public ArrayList<Mnemonic> onSelectMnemonics() {
        ArrayList<Mnemonic> result = new ArrayList<>();
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_MNEMONIC, new String[]{"id", "uuid", "resource", "spec", "dpMasterKey"}, null, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            do {
                Mnemonic mnemonic = new Mnemonic(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));
                result.add(mnemonic);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public Mnemonic onSelectMnemonic(String id) {
        Mnemonic result = null;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_MNEMONIC, new String[]{"id", "uuid", "resource", "spec", "dpMasterKey"}, "id == ?", new String[]{id}, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            result = new Mnemonic(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
        }
        return result;
    }

    public long onInsertMnemonic(Mnemonic mnemonic) {
        long result = -1;
        if(isDupleMnemonic(mnemonic.dpMasterKey)) return result;
        ContentValues values = new ContentValues();
        values.put("uuid",          mnemonic.uuid);
        values.put("resource",      mnemonic.resource);
        values.put("spec",          mnemonic.spec);
        values.put("dpMasterKey",   mnemonic.dpMasterKey);
        return getBaseDB().insertOrThrow(BaseConstant.DB_TABLE_MNEMONIC, null, values);
    }

    public boolean onHasMnemonic() {
        boolean existed = false;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_MNEMONIC, new String[]{"id"}, null, null, null, null, null);
        if(cursor != null && cursor.getCount() > 0) {
            existed = true;
        }
        cursor.close();
        return existed;
    }

    public boolean isDupleMnemonic(String dpMasterKey) {
        boolean existed = false;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_MNEMONIC, new String[]{"id"}, "dpMasterKey == ?", new String[]{dpMasterKey}, null, null, null);
        if(cursor != null && cursor.getCount() > 0) {
            existed = true;
        }
        cursor.close();
        return existed;
    }

    public boolean onDeleteMnemonic(String id) {
        return getBaseDB().delete(BaseConstant.DB_TABLE_MNEMONIC, "id = ?", new String[]{id}) > 0;
    }



    public ArrayList<Account> onSelectAccounts() {
        ArrayList<Account> result = new ArrayList<>();
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_ACCOUNT, new String[]{"id", "uuid", "nickName", "isFavo", "address", "baseChain",
                "hasPrivateKey", "resource", "spec", "fromMnemonic", "path",
                "isValidator", "sequenceNumber", "accountNumber", "fetchTime"}, null, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            do {
                Account account = new Account(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3) > 0,
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6) > 0,
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getInt(9) > 0,
                        cursor.getString(10),
                        cursor.getInt(11) > 0,
                        cursor.getInt(12),
                        cursor.getInt(13),
                        cursor.getLong(14)
                );
                account.setBalances(onSelectBalance(""+account.id));
                result.add(account);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public Account onSelectAccount(String id) {
        Account result = null;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_ACCOUNT, new String[]{"id", "uuid", "nickName", "isFavo", "address", "baseChain",
                "hasPrivateKey", "resource", "spec", "fromMnemonic", "path",
                "isValidator", "sequenceNumber", "accountNumber", "fetchTime"}, "id == ?", new String[]{id}, null, null, null);
        result = new Account(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3) > 0,
                cursor.getString(4),
                cursor.getString(5),
                cursor.getInt(6) > 0,
                cursor.getString(7),
                cursor.getString(8),
                cursor.getInt(9) > 0,
                cursor.getString(10),
                cursor.getInt(11) > 0,
                cursor.getInt(12),
                cursor.getInt(13),
                cursor.getLong(14)
        );
        result.setBalances(onSelectBalance(id));
        cursor.close();
        return result;
    }

    public long onInsertAccount(Account account) {
        long result = -1;
        if(isDupleAccount(account.address)) return result;
        ContentValues values = new ContentValues();
        values.put("uuid",              account.uuid);
        values.put("nickName",          account.nickName);
        values.put("isFavo",            account.isFavo);
        values.put("address",           account.address);
        values.put("baseChain",         account.baseChain);
        values.put("hasPrivateKey",     account.hasPrivateKey);
        values.put("resource",          account.resource);
        values.put("spec",              account.spec);
        values.put("fromMnemonic",      account.fromMnemonic);
        values.put("path",              account.path);
        values.put("isValidator",       account.isValidator);
        values.put("sequenceNumber",    account.sequenceNumber);
        values.put("accountNumber",     account.accountNumber);
        values.put("fetchTime",         account.fetchTime);
        return getBaseDB().insertOrThrow(BaseConstant.DB_TABLE_ACCOUNT, null, values);
    }

    public long onUpdateAccount(Account account) {
        ContentValues values = new ContentValues();
        values.put("nickName",          account.nickName);
        values.put("isFavo",            account.isFavo);
        values.put("sequenceNumber",    account.sequenceNumber);
        values.put("fetchTime",         account.fetchTime);
        return getBaseDB().update(BaseConstant.DB_TABLE_ACCOUNT, values, "id = ?", new String[]{""+account.id} );
    }

    public boolean isDupleAccount(String address) {
        boolean existed = false;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_ACCOUNT, new String[]{"id"}, "address == ?", new String[]{address}, null, null, null);
        if(cursor != null && cursor.getCount() > 0) {
            existed = true;
        }
        cursor.close();
        return existed;
    }

    public boolean onDeleteAccount(String id) {
        //TODO delete Tx or else data with this account
        onDeleteBalance(id);
        return getBaseDB().delete(BaseConstant.DB_TABLE_ACCOUNT, "id = ?", new String[]{id}) > 0;
    }



    public ArrayList<Balance> onSelectBalance(String accountId) {
        ArrayList<Balance> result = new ArrayList<>();
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_BALANCE, new String[]{"accountId", "symbol", "balance", "fetchTime"}, "accountId == ?", new String[]{accountId}, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            do {
                Balance balance = new Balance(
                        cursor.getLong(0),
                        cursor.getString(1),
                        new BigDecimal(cursor.getString(2)),
                        cursor.getLong(3));
                result.add(balance);
            } while (cursor.moveToNext());
        }
        return result;
    }

    public long onInsertBalance(Balance balance) {
        if(onHasBalance(balance)) {
            return onUpdateBalace(balance);
        } else {
            ContentValues values = new ContentValues();
            values.put("accountId",         balance.accountId);
            values.put("symbol",            balance.symbol);
            values.put("balance",           balance.balance.toPlainString());
            values.put("fetchTime",         balance.fetchTime);
            return getBaseDB().insertOrThrow(BaseConstant.DB_TABLE_BALANCE, null, values);
        }
    }

    public long onUpdateBalace(Balance balance) {
        ContentValues values = new ContentValues();
        values.put("balance",           balance.balance.toPlainString());
        values.put("fetchTime",         balance.fetchTime);
        return getBaseDB().update(BaseConstant.DB_TABLE_ACCOUNT, values, "accountId == ? && symbol == ? ", new String[]{""+balance.accountId, balance.symbol} );
    }

    public boolean onHasBalance(Balance balance) {
        boolean existed = false;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_BALANCE, new String[]{"accountId", "symbol", "balance", "fetchTime"}, "accountId == ? && symbol == ? ", new String[]{""+balance.accountId, balance.symbol}, null, null, null);
        if(cursor != null && cursor.getCount() > 0) {
            existed = true;
        }
        cursor.close();
        return existed;
    }

    public boolean onDeleteBalance(String accountId) {
        return getBaseDB().delete(BaseConstant.DB_TABLE_BALANCE, "accountId = ?", new String[]{accountId}) > 0;
    }
}
