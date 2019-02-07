package wannabit.io.cosmostaion.dao;

import java.math.BigDecimal;

public class UnBondingState {
    public Long         accountId;
    public String       validatorAddress;
    public String       creationHeight;
    public Long         completionTime;
    public BigDecimal   initialBalance;
    public BigDecimal   balance;
    public Long         fetchTime;

    public UnBondingState() {
    }

    public UnBondingState(Long accountId, String validatorAddress, String creationHeight, long completionTime, BigDecimal initialBalance, BigDecimal balance, Long fetchTime) {
        this.accountId = accountId;
        this.validatorAddress = validatorAddress;
        this.creationHeight = creationHeight;
        this.completionTime = completionTime;
        this.initialBalance = initialBalance;
        this.balance = balance;
        this.fetchTime = fetchTime;
    }
}
