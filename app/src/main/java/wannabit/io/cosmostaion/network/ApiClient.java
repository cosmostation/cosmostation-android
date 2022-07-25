package wannabit.io.cosmostaion.network;

import android.content.Context;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import io.grpc.ManagedChannel;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;

public class ApiClient {

    //Services for station wallet api
    private static Station station = null;
    public static Station getStation(Context c) {
        if (station == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_station))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                station = retrofit.create(Station.class);
            }
        }
        return station;
    }

    //Services for station wallet test api
    private static Station stationTest = null;
    public static Station getStationTest(Context c) {
        if (stationTest == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_station_testnet))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                stationTest = retrofit.create(Station.class);
            }
        }
        return stationTest;
    }

    //Services for station mintscan api
    private static Station mintscan = null;
    public static Station getMintscan(Context c) {
        if (mintscan == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_mintscan))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                mintscan = retrofit.create(Station.class);
            }
        }
        return mintscan;
    }

    //Services for station airdrop api
    private static Station airdrop = null;
    public static Station getAirDrop(Context c) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (airdrop == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_airdrop_desmos))
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                airdrop = retrofit.create(Station.class);
            }
        }
        return airdrop;
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

    //Services for Binance net
    private static BinanceChain service_binance = null;
    public static BinanceChain getBnbChain(Context c) {
        if (service_binance == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_main_bnb))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_binance = retrofit.create(BinanceChain.class);
            }
        }
        return service_binance;
    }

    private static KavaChain service_kava = null;
    public static KavaChain getKavaChain(Context c) {
        if (service_kava == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_kava_main))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_kava = retrofit.create(KavaChain.class);
            }
        }
        return service_kava;
    }

    //Services for Certik chain
    private static Station service_certik = null;
    public static Station getCertikChain(Context c) {
        if (service_certik == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_certik_main))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_certik = retrofit.create(Station.class);
            }
        }
        return service_certik;
    }

    //Services for OEC mainnet chain
    private static OkChain service_ok = null;
    public static OkChain getOkexChain(Context c) {
        if (service_ok == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_ok))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_ok = retrofit.create(OkChain.class);
            }
        }
        return service_ok;
    }

    //Services for OEC mainnet api
    private static OkChain api_oec = null;
    public static OkChain getOecApi(Context c) {
        if (api_oec == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_ok))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_oec = retrofit.create(OkChain.class);
            }
        }
        return api_oec;
    }
}
