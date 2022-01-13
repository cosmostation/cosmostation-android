package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ResAirdropClaimCheck {
    @SerializedName("staking_infos")
    public ArrayList<DesmosAirdropInfo> stakingInfos;

    @SerializedName("lp_infos")
    public ArrayList<DesmosAirdropInfo> lpInfos;

    public class DesmosAirdropInfo {
        @SerializedName("address")
        public String address;

        @SerializedName("chain_name")
        public String chain_name;

        @SerializedName("dsm_allotted")
        public BigDecimal dsm_allotted;

        @SerializedName("claimed")
        public boolean claimed;
    }

    public ResAirdropClaimCheck(ResAirdropClaimCheck data) {
        this.stakingInfos = data.stakingInfos;
        this.lpInfos = data.lpInfos;
    }

    public BigDecimal getUnclaimedAirdropAmount() {
        BigDecimal result = BigDecimal.ZERO;
        if (stakingInfos != null && stakingInfos.size() > 0) {
            for (DesmosAirdropInfo stakingInfo: stakingInfos) {
                if (!stakingInfo.claimed) {
                    result = result.add(stakingInfo.dsm_allotted);
                }
            }
        } else if (lpInfos != null && lpInfos.size() > 0) {
            for (DesmosAirdropInfo lpInfo: lpInfos) {
                if (!lpInfo.claimed) {
                    result = result.add(lpInfo.dsm_allotted);
                }
            }
        } else {
            return result;
        }
        return result;
    }
}
