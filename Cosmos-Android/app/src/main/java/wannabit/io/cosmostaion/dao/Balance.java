package wannabit.io.cosmostaion.dao;

import android.text.TextUtils;

import com.google.android.gms.common.util.NumberUtils;
import com.google.zxing.common.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.network.res.ResBnbTic;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class Balance {
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
