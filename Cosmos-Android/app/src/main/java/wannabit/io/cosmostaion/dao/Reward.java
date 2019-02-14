package wannabit.io.cosmostaion.dao;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class Reward {
    public Long                 accountId;
    public String               validatorAddress;
    public ArrayList<Coin>      amount;
    public Long                 fetchTime;

    public Reward() {
    }

    public Reward(Long accountId, String validatorAddress, ArrayList<Coin> amount, Long fetchTime) {
        this.accountId = accountId;
        this.validatorAddress = validatorAddress;
        this.amount = amount;
        this.fetchTime = fetchTime;
    }
}
