package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResApiNewTxListCustom;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class ApiAccountTxsHistoryTask extends CommonTask {

    private String mAddress;
    private BaseChain mChain;

    public ApiAccountTxsHistoryTask(BaseApplication app, TaskListener listener, String address, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_API_ADDRESS_HISTORY;
        this.mAddress = address;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getKavaApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            }

            else if (mChain.equals(COSMOS_MAIN)) {
//                WLog.w("COSMOS_MAIN " + ApiClient.getCosmosApi(mApp).getAccountTxsCustom(mAddress, "50").request().url());
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getCosmosApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(IRIS_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getIrisApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.AKASH_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getAkashApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.SENTINEL_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getSentinelApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.PERSIS_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getPersisApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.CRYPTO_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getCryptoApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.OSMOSIS_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getOsmosisApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }
            } else if (mChain.equals(BaseChain.IOV_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getIovApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.SIF_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getSifApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.MEDI_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getMediblocApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }
            } else if (mChain.equals(BaseChain.CERTIK_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getCertikApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }
            } else if (mChain.equals(BaseChain.EMONEY_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getEmoneyApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }
            } else if (mChain.equals(BaseChain.FETCHAI_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getFetchApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.BAND_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getBandApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.RIZON_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getRizonApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.JUNO_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getJunoApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.REGEN_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getRegenApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.BITCANNA_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getBitcannaApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.ALTHEA_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getAltheaApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.STARGAZE_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getStargazeApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.GRABRIDGE_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getGraBridgeApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.KI_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getKiApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.COMDEX_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getComdexApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.SECRET_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getSecretApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.INJ_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getInjApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.BITSONG_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getBitsongApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.DESMOS_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getDesmosApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.LUM_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getLumApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.CHIHUAHUA_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getChihuahuaApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.AXELAR_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getAxelarApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.KONSTELL_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getKonstellApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.UMEE_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getUmeeApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(BaseChain.EVMOS_MAIN)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getEvmosApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            }

            else if (mChain.equals(COSMOS_TEST)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getCosmosTestApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(IRIS_TEST)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getIrisTestApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            } else if (mChain.equals(ALTHEA_TEST)) {
                Response<ArrayList<ResApiNewTxListCustom>> response = ApiClient.getAltheaTestApi(mApp).getNewAccountTxCustom(mAddress, "50").execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("HistoryTask : NOk");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            WLog.w("ApiAccountTxsHistoryTask Error " + e.getMessage());
        }
        return mResult;
    }
}
