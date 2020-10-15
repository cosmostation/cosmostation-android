package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.KavaClaimMultiplier;
import wannabit.io.cosmostaion.model.type.Coin;

public class ResKavaHarvestParam {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public KavaHavestParamData result;


    public class KavaHavestParamData {
        @SerializedName("active")
        public boolean active;

        @SerializedName("liquidity_provider_schedules")
        public ArrayList<DistributionSchedule> liquidity_provider_schedules;

        @SerializedName("delegator_distribution_schedules")
        public ArrayList<DelegatorDistributionSchedule> delegator_distribution_schedules;

    }

    public class DistributionSchedule {
        @SerializedName("active")
        public boolean active;

        @SerializedName("deposit_denom")
        public String deposit_denom;

        @SerializedName("start")
        public String start;

        @SerializedName("end")
        public String end;

        @SerializedName("rewards_per_second")
        public Coin rewards_per_second;

        @SerializedName("claim_end")
        public String claim_end;

        @SerializedName("claim_multipliers")
        public ArrayList<KavaClaimMultiplier> claim_multipliers;

    }

    public class DelegatorDistributionSchedule {
        @SerializedName("distribution_schedule")
        public DistributionSchedule distribution_schedule;

        @SerializedName("distribution_frequency")
        public String distribution_frequency;

    }
}
