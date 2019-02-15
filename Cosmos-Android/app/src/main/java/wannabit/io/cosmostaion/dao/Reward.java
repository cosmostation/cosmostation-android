package wannabit.io.cosmostaion.dao;

import java.math.BigDecimal;
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

    public BigDecimal getAtomAmount() {
        BigDecimal result = BigDecimal.ZERO;
        for(Coin coin:amount) {
            if(coin.denom.equals("atom")) {
                result = new BigDecimal(coin.amount);
                break;
            }
        }
        return result;
    }

    public BigDecimal getPhotonAmount() {
        BigDecimal result = BigDecimal.ZERO;
        for(Coin coin:amount) {
            if(coin.denom.equals("photino")) {
                result = new BigDecimal(coin.amount);
                break;
            }
        }
        return result;
    }
}
