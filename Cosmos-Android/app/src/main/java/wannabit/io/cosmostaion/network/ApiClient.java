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
    private static NetworkCosmos_V1 service_cosmos = null;
    public static NetworkCosmos_V1 getCosmosChain(Context c) {
        if (service_cosmos == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_cosmos_main))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_cosmos = retrofit.create(NetworkCosmos_V1.class);
            }
        }
        return service_cosmos;
    }

    //Services for Cosmos api
    private static HistoryApi api_cosmos = null;
    public static HistoryApi getCosmosApi(Context c) {
        if (api_cosmos == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_cosmos_main))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_cosmos = retrofit.create(HistoryApi.class);
            }
        }
        return api_cosmos;
    }

    //Services for Iris main net
    private static NetworkIris_V1 service_iris = null;
    public static NetworkIris_V1 getIrisChain(Context c) {
        if (service_iris == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_iris_main))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_iris = retrofit.create(NetworkIris_V1.class);
            }
        }
        return service_iris;
    }

    //Services for Iris history api mainnet
    private static HistoryApi api_iris = null;
    public static HistoryApi getIrisApi(Context c) {
        if (api_iris == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_iris_main))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_iris = retrofit.create(HistoryApi.class);
            }
        }
        return api_iris;
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

    //Services for BinanceTest net
    private static BinanceChain service_binance_test = null;
    public static BinanceChain getBnbTestChain(Context c) {
        if (service_binance_test == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_test_bnb))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_binance_test = retrofit.create(BinanceChain.class);
            }
        }
        return service_binance_test;
    }

    //Faucet for BinanceTest net
    private static BinanceChain service_binance_faucet = null;
    public static BinanceChain getBnbTestFaucet(Context c) {
        if (service_binance_faucet == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_test_bnb_faucet))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_binance_faucet = retrofit.create(BinanceChain.class);
            }
        }
        return service_binance_faucet;
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

    //Services for KAVA api
    private static HistoryApi api_kava = null;
    public static HistoryApi getKavaApi(Context c) {
        if (api_kava == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_kava_main))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_kava = retrofit.create(HistoryApi.class);
            }
        }
        return api_kava;
    }

    //Faucet for KAVA net
    private static KavaChain service_kava_faucet = null;
    public static KavaChain getKavaFaucet(Context c) {
        if (service_kava_faucet == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_kava_faucet))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_kava_faucet = retrofit.create(KavaChain.class);
            }
        }
        return service_kava_faucet;
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
    private static HistoryApi api_kava_test = null;
    public static HistoryApi getKavaTestApi(Context c) {
        if (api_kava_test == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_kava_test))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_kava_test = retrofit.create(HistoryApi.class);
            }
        }
        return api_kava_test;
    }

    //Faucet for KAVATest
    private static KavaChain service_kava_test_faucet = null;
    public static KavaChain getKavaTestFaucet(Context c) {
        if (service_kava_test_faucet == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_test_kava_faucet))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_kava_test_faucet = retrofit.create(KavaChain.class);
            }
        }
        return service_kava_test_faucet;
    }

    //Rest for IOV main net
    private static IovChain service_iov = null;
    public static IovChain getIovChain(Context c) {
        if (service_iov == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_iov_main))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_iov = retrofit.create(IovChain.class);
            }
        }
        return service_iov;
    }

    //Services for IOV api
    private static HistoryApi api_iov = null;
    public static HistoryApi getIovApi(Context c) {
        if (api_iov == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_iov_main))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_iov = retrofit.create(HistoryApi.class);
            }
        }
        return api_iov;
    }

    //Service for IOV testnet
    private static IovChain service_iov_test = null;
    public static IovChain getIovTestChain(Context c) {
        if (service_iov_test == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_iov_test))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_iov_test = retrofit.create(IovChain.class);
            }
        }
        return service_iov_test;
    }

    //Faucet for IOV Test
    private static IovChain service_iov_test_faucet = null;
    public static IovChain getIovTestFaucet(Context c) {
        if (service_iov_test_faucet == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_test_iov_faucet))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_iov_test_faucet = retrofit.create(IovChain.class);
            }
        }
        return service_iov_test_faucet;
    }

    //Rest for Band main net
    private static BandChain service_band = null;
    public static BandChain getBandChain(Context c) {
        if (service_band == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_band_main))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_band = retrofit.create(BandChain.class);
            }
        }
        return service_band;
    }

    //Services for Band api
    private static HistoryApi api_band = null;
    public static HistoryApi getBandApi(Context c) {
        if (api_band == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_band_main))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_band = retrofit.create(HistoryApi.class);
            }
        }
        return api_band;
    }


    //Services for Okex mainnet chain
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

    //Services for OkTest chain
    private static OkChain service_ok_test = null;
    public static OkChain getOkTestChain(Context c) {
        if (service_ok_test == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_ok_test))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_ok_test = retrofit.create(OkChain.class);
            }
        }
        return service_ok_test;
    }




    //Services for Certik mainnet
    private static CertikChain service_certik = null;
    public static CertikChain getCertikChain(Context c) {
        if (service_certik == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_certik))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_certik = retrofit.create(CertikChain.class);
            }
        }
        return service_certik;
    }

    //Services for Certik mainnet api
    private static HistoryApi api_certik = null;
    public static HistoryApi getCertikApi(Context c) {
        if (api_certik == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_certik))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_certik = retrofit.create(HistoryApi.class);
            }
        }
        return api_certik;
    }

    //Services for Certik test chain
    private static CertikChain service_certik_test = null;
    public static CertikChain getCertikTestChain(Context c) {
        if (service_certik_test == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_certik_test))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_certik_test = retrofit.create(CertikChain.class);
            }
        }
        return service_certik_test;
    }

    //Services for Certik test api
    private static HistoryApi api_certik_test = null;
    public static HistoryApi getCertikTestApi(Context c) {
        if (api_certik_test == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_certik_test))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_certik_test = retrofit.create(HistoryApi.class);
            }
        }
        return api_certik_test;
    }




    //Services for Secret mainnet
    private static SecretChain service_secret = null;
    public static SecretChain getSecretChain(Context c) {
        if (service_secret == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_secret))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_secret = retrofit.create(SecretChain.class);
            }
        }
        return service_secret;
    }

    //Services for Akash main net
    private static NetworkAkash_V1 service_akash = null;
    public static NetworkAkash_V1 getAkashChain(Context c) {
        if (service_akash == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_akash))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_akash = retrofit.create(NetworkAkash_V1.class);
            }
        }
        return service_akash;
    }

    //Services for Akash mainnet api
    private static HistoryApi api_akash = null;
    public static HistoryApi getAkashApi(Context c) {
        if (api_akash == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_akash))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_akash = retrofit.create(HistoryApi.class);
            }
        }
        return api_akash;
    }





    //Services for Cosmos test net
    private static NetworkCosmos_V1 service_cosmos_test = null;
    public static NetworkCosmos_V1 getCosmosTestChain(Context c) {
        if (service_cosmos_test == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_cosmos_test))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_cosmos_test = retrofit.create(NetworkCosmos_V1.class);
            }
        }
        return service_cosmos_test;
    }

    //Services for Cosmos Test api
    private static HistoryApi api_cosmos_test = null;
    public static HistoryApi getCosmosTestApi(Context c) {
        if (api_cosmos_test == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_cosmos_test))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_cosmos_test = retrofit.create(HistoryApi.class);
            }
        }
        return api_cosmos_test;
    }


    //Services for Iris test net
    private static NetworkIris_V1 service_iris_test = null;
    public static NetworkIris_V1 getIrisTestChain(Context c) {
        if (service_iris_test == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_iris_test))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_iris_test = retrofit.create(NetworkIris_V1.class);
            }
        }
        return service_iris_test;
    }

    //Services for Iris Test api
    private static HistoryApi api_iris_test = null;
    public static HistoryApi getIrisTestApi(Context c) {
        if (api_iris_test == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_iris_test))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_iris_test = retrofit.create(HistoryApi.class);
            }
        }
        return api_iris_test;
    }




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
