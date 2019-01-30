package wannabit.io.cosmostaion.dao;

import java.math.BigDecimal;

public class Balance {
    public Long         accountId;
    public String       symbol;
    public BigDecimal   balance;
    public Long         fetchTime;

    public Balance(Long accountId, String symbol, BigDecimal balance, Long fetchTime) {
        this.accountId = accountId;
        this.symbol = symbol;
        this.balance = balance;
        this.fetchTime = fetchTime;
    }
}
