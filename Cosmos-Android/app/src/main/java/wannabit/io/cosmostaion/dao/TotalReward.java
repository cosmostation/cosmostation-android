package wannabit.io.cosmostaion.dao;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.model.type.Coin;

public class TotalReward {
    public Long                 accountId;
    public ArrayList<Coin>      coins;

    public TotalReward(Long accountId, ArrayList<Coin> coins) {
        this.accountId = accountId;
        this.coins = coins;
    }

    public BigDecimal getAtomAmount() {
        BigDecimal result = BigDecimal.ZERO;
        for(Coin coin:coins) {
            if(coin.denom.equals(BaseConstant.TOKEN_ATOM) || coin.denom.equals(BaseConstant.COSMOS_MUON)) {
                result = new BigDecimal(coin.amount);
                break;
            }
        }
        return result;
    }

    public BigDecimal getPhotonAmount() {
        BigDecimal result = BigDecimal.ZERO;
        for(Coin coin:coins) {
            if(coin.denom.equals(BaseConstant.COSMOS_PHOTON) || coin.denom.equals(BaseConstant.COSMOS_PHOTINO)) {
                result = new BigDecimal(coin.amount);
                break;
            }
        }
        return result;
    }
}
