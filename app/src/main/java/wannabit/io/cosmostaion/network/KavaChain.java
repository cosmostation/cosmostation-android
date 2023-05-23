package wannabit.io.cosmostaion.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.network.res.ResCdpDepositStatus;
import wannabit.io.cosmostaion.network.res.ResKavaBep3Param;
import wannabit.io.cosmostaion.network.res.ResKavaSwapInfo;
import wannabit.io.cosmostaion.network.res.ResKavaSwapSupply;
import wannabit.io.cosmostaion.network.res.kava.ResKavaModuleAccount;

public interface KavaChain {

    @GET("kava/bep3/v1beta1/params")
    Call<ResKavaBep3Param> getSwapParams2();

    @GET("kava/bep3/v1beta1/atomicswap/{swapId}")
    Call<ResKavaSwapInfo> getSwapById(@Path("swapId") String swapId);

    @GET("kava/bep3/v1beta1/assetsupplies")
    Call<ResKavaSwapSupply> getSupplies2();

    @GET("kava/cdp/v1beta1/cdps/deposits/{address}/{denom}")
    Call<ResCdpDepositStatus> getCdpDepositStatus(@Path("address") String address, @Path("denom") String denom);

    @GET("kava/incentive/v1beta1/rewards")
    Call<IncentiveReward> getIncentiveReward5(@Query("owner") String owner);

    @GET("kava/hard/v1beta1/accounts")
    Call<ResKavaModuleAccount> getHardModuleAccount();

}
