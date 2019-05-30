package wannabit.io.cosmostaion.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.base.BaseConstant;
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
            if(coin.denom.equals(BaseConstant.COSMOS_ATOM)|| coin.denom.equals(BaseConstant.COSMOS_MUON)) {
                result = new BigDecimal(coin.amount).setScale(0, RoundingMode.DOWN);
                break;
            }
        }
        return result;
    }

    public BigDecimal getPhotonAmount() {
        BigDecimal result = BigDecimal.ZERO;
        for(Coin coin:amount) {
            if(coin.denom.equals(BaseConstant.COSMOS_PHOTON) || coin.denom.equals(BaseConstant.COSMOS_PHOTINO)) {
                result = new BigDecimal(coin.amount).setScale(0, RoundingMode.DOWN);
                break;
            }
        }
        return result;
    }
}
