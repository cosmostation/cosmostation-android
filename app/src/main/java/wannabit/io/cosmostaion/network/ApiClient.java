package wannabit.io.cosmostaion.network;

import android.content.Context;

import com.google.common.collect.Maps;

import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.network.res.neutron.Neutron;

public class ApiClient {

    //Services for station mintscan api
    private static Station mintscan = null;
    public static Station getMintscan() {
        if (mintscan == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BaseConstant.MINTSCAN_API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                mintscan = retrofit.create(Station.class);
            }
        }
        return mintscan;
    }

    private static Station devMintscan = null;
    public static Station getDevMintscan() {
        if (devMintscan == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BaseConstant.DEV_MINTSCAN_API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                devMintscan = retrofit.create(Station.class);
            }
        }
        return devMintscan;
    }

    private static Neutron neutron = null;
    public static Neutron getNeutron(){
        if (neutron == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BaseConstant.CHAIN_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                neutron = retrofit.create(Neutron.class);
            }
        }
        return neutron;
    }

    private static Station chainBase = null;
    public static Station getChainBase() {
        if (chainBase == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BaseConstant.CHAIN_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                chainBase = retrofit.create(Station.class);
            }
        }
        return chainBase;
    }

    //Services for Cosmostation wallet api
    private static Cosmostation cosmostation = null;
    public static Cosmostation getCosmostationOld(Context c) {
        if (cosmostation == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_station_old))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                cosmostation = retrofit.create(Cosmostation.class);
            }
        }
        return cosmostation;
    }

    static Map<String, HistoryApi> apiMap = Maps.newHashMap();
    public static HistoryApi getChainApi(BaseChain baseChain) {
        if (apiMap.containsKey(baseChain.getChain())) {
            return apiMap.get(baseChain.getChain());
        } else {
            ChainConfig chainConfig = ChainFactory.getChain(baseChain);
            HistoryApi historyApi = chainConfig.apiMain().create(HistoryApi.class);
            apiMap.put(baseChain.getChain(), historyApi);
            return historyApi;
        }
    }

    private static KavaChain service_kava = null;
    public static KavaChain getKavaChain() {
        if (service_kava == null) {
            ChainConfig chainConfig = ChainFactory.getChain(BaseChain.KAVA_MAIN);
            synchronized (ApiClient.class) {
                service_kava = chainConfig.lcdMain().create(KavaChain.class);
            }
        }
        return service_kava;
    }

    //Services for Binance net
    private static BinanceChain service_binance = null;
    public static BinanceChain getBnbChain() {
        ChainConfig chainConfig = ChainFactory.getChain(BaseChain.BNB_MAIN);
        if (service_binance == null) {
            synchronized (ApiClient.class) {
                service_binance = chainConfig.lcdMain().create(BinanceChain.class);
            }
        }
        return service_binance;
    }

    //Services for OEC mainnet chain
    private static OkChain service_ok = null;
    public static OkChain getOkexChain() {
        if (service_ok == null) {
            ChainConfig chainConfig = ChainFactory.getChain(BaseChain.OKEX_MAIN);
            synchronized (ApiClient.class) {
                service_ok = chainConfig.lcdMain().create(OkChain.class);
            }
        }
        return service_ok;
    }
}
