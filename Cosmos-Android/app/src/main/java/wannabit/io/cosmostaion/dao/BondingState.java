package wannabit.io.cosmostaion.dao;

import java.math.BigDecimal;

public class BondingState {
    public Long         accountId;
    public String       validatorAddress;
    public BigDecimal   shares;
    public Long         fetchTime;

    public BondingState() {
    }

    public BondingState(Long accountId, String validatorAddress, BigDecimal shares, Long fetchTime) {
        this.accountId = accountId;
        this.validatorAddress = validatorAddress;
        this.shares = shares;
        this.fetchTime = fetchTime;
    }
}
