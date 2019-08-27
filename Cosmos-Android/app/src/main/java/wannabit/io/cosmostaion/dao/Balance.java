package wannabit.io.cosmostaion.dao;

import android.text.TextUtils;

import com.google.android.gms.common.util.NumberUtils;
import com.google.zxing.common.StringUtils;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.utils.WLog;

public class Balance {
    public Long         accountId;
    public String       symbol;
    public BigDecimal   balance;
    public Long         fetchTime;
    public BigDecimal   frozen;
    public BigDecimal   locked;

    public Balance() {
    }

//    public Balance(Long accountId, String symbol, BigDecimal balance, Long fetchTime) {
//        this.accountId = accountId;
//        this.symbol = symbol;
//        this.balance = balance;
//        this.fetchTime = fetchTime;
//    }


//    public Balance(Long accountId, String symbol, BigDecimal balance, Long fetchTime, BigDecimal frozen, BigDecimal locked) {
//        this.accountId = accountId;
//        this.symbol = symbol;
//        this.balance = balance;
//        this.fetchTime = fetchTime;
//        this.frozen = frozen;
//        this.locked = locked;
//    }

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
}
