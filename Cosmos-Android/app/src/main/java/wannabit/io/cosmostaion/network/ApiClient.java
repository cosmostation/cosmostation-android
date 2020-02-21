package wannabit.io.cosmostaion.network;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wannabit.io.cosmostaion.R;

public class ApiClient {

    //Services for Cosmostation wallet api
    private static Cosmostation cosmostation = null;
    public static Cosmostation getCosmostation(Context c) {
        if (cosmostation == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_css))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                cosmostation = retrofit.create(Cosmostation.class);
            }
        }
        return cosmostation;
    }

    //Services for Cosmos main net
    private static CosmosChain service_cosmos = null;
    public static CosmosChain getCosmosChain(Context c) {
        if (service_cosmos == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_main))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_cosmos = retrofit.create(CosmosChain.class);
            }
        }
        return service_cosmos;
    }

    //Services for Iris main net
    private static IrisChain service_iris = null;
    public static IrisChain getIrisChain(Context c) {
        if (service_iris == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_main_iris))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_iris = retrofit.create(IrisChain.class);
            }
        }
        return service_iris;
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

    //Services for KAVA chain
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

    //Services for KAVATest chain
    private static KavaChain service_kava_test = null;
    public static KavaChain getKavaTestChain(Context c) {
        if (service_kava_test == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_kava_test))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_kava_test = retrofit.create(KavaChain.class);
            }
        }
        return service_kava_test;
    }

    //Services for KAVATest api
    private static KavaApi api_kava_test = null;
    public static KavaApi getKavaTestApi(Context c) {
        if (api_kava_test == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_kava_test))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_kava_test = retrofit.create(KavaApi.class);
            }
        }
        return api_kava_test;
    }

    //Services for IOV main net
    private static IovChain service_iov = null;
    public static IovChain getIovChain(Context c) {
        if (service_iov == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_rest_main_iov))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_iov = retrofit.create(IovChain.class);
            }
        }
        return service_iov;
    }


    private static CosmosEsService service_cosmos_es = null;
    public static CosmosEsService getCosmosEs(Context c) {
        if (service_cosmos_es == null ) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_es_proxy))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_cosmos_es = retrofit.create(CosmosEsService.class);
            }
        }
        return service_cosmos_es;
    }





//    private static KeyBaseService service_keybase = null;
//    public static KeyBaseService getKeybaseService(Context c) {
//        if (service_keybase == null) {
//            synchronized (ApiClient.class) {
//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl(c.getString(R.string.url_keybase))
//                        .client(WUtil.getUnsafeOkHttpClient().build())
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//                service_keybase = retrofit.create(KeyBaseService.class);
//            }
//        }
//        return service_keybase;
//    }

    private static MarketCapService marketCapService = null;
    public static MarketCapService getCMCClient(Context c) {
        if (marketCapService == null) {
            synchronized (ApiClient.class) {
                if (marketCapService == null)  {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(c.getString(R.string.url_coinmarketcap))
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    marketCapService = retrofit.create(MarketCapService.class);
                }
            }
        }
        return marketCapService;
    }

    private static CoinGeckoService coingeckoService = null;
    public static CoinGeckoService getCGCClient(Context c) {
        if (coingeckoService == null) {
            synchronized (ApiClient.class) {
                if (coingeckoService == null)  {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(c.getString(R.string.url_coingecko))
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    coingeckoService = retrofit.create(CoinGeckoService.class);
                }
            }
        }
        return coingeckoService;
    }

}
