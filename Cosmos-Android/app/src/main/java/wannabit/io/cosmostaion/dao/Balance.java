package wannabit.io.cosmostaion.dao;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.network.res.ResBnbTic;
import wannabit.io.cosmostaion.utils.WUtil;

public class Balance implements Parcelable {
    public Long         accountId;
    public String       symbol;
    public BigDecimal   balance;
    public Long         fetchTime;
    public BigDecimal   frozen;
    public BigDecimal   locked;

    public Balance() {
    }

    public Balance(Long accountId, String symbol, String balance, Long fetchTime, String frozen, String locked) {
        this.accountId = accountId;
        this.symbol = symbol;
        if (balance != null) {
            this.balance = new BigDecimal(balance);
        } else {
            this.balance = BigDecimal.ZERO;
        }
        this.fetchTime = fetchTime;
        if (frozen != null) {
            this.frozen = new BigDecimal(frozen);
        } else {
            this.frozen = BigDecimal.ZERO;
        }
        if (locked != null) {
            this.locked = new BigDecimal(locked);
        } else {
            this.locked = BigDecimal.ZERO;
        }
    }

    protected Balance(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        accountId = in.readLong();
        symbol = in.readString();
        balance = new BigDecimal(in.readString());
        fetchTime = in.readLong();
        frozen = new BigDecimal(in.readString());
        locked = new BigDecimal(in.readString());

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(accountId);
        dest.writeString(symbol);
        dest.writeString(balance.toPlainString());
        dest.writeLong(fetchTime);
        dest.writeString(frozen.toPlainString());
        dest.writeString(locked.toPlainString());
    }

    public static final Creator<Balance> CREATOR = new Creator<Balance>() {
        @Override
        public Balance createFromParcel(Parcel in) {
            return new Balance(in);
        }

        @Override
        public Balance[] newArray(int size) {
            return new Balance[size];
        }
    };


    public BigDecimal getAllBnbBalance() {
        return balance.add(locked);
    }

    public BigDecimal exchangeToBnbAmount(ResBnbTic tic) {
        if (WUtil.isBnbBaseMarketToken(symbol)) {
            return getAllBnbBalance().divide(new BigDecimal(tic.lastPrice), 8, RoundingMode.DOWN);
        } else {
            return getAllBnbBalance().multiply(new BigDecimal(tic.lastPrice)).setScale(8);
        }
    }
}
