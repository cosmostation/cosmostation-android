package wannabit.io.cosmostaion.network;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wannabit.io.cosmostaion.R;

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

    //Services for Persistence mainnet api
    private static HistoryApi api_persis = null;
    public static HistoryApi getPersisApi(Context c) {
        if (api_persis == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_persis))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_persis = retrofit.create(HistoryApi.class);
            }
        }
        return api_persis;
    }


    //Services for Sentinel mainnet api
    private static HistoryApi api_sentinel = null;
    public static HistoryApi getSentinelApi(Context c) {
        if (api_sentinel == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_sentinel))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_sentinel = retrofit.create(HistoryApi.class);
            }
        }
        return api_sentinel;
    }

    //Services for fetchAi mainnet
    private static FetchAiChain service_fetch = null;
    public static FetchAiChain getFetchChain(Context c) {
        if (service_fetch == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_fetch))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_fetch = retrofit.create(FetchAiChain.class);
            }
        }
        return service_fetch;
    }

    //Services for fetchAi mainnet api
    private static HistoryApi api_fetch = null;
    public static HistoryApi getFetchApi(Context c) {
        if (api_fetch == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_fetch))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_fetch = retrofit.create(HistoryApi.class);
            }
        }
        return api_fetch;
    }



    //Services for Crypto.org mainnet api
    private static HistoryApi api_crypto = null;
    public static HistoryApi getCryptoApi(Context c) {
        if (api_crypto == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_crypto))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_crypto = retrofit.create(HistoryApi.class);
            }
        }
        return api_crypto;
    }

    //Services for sifChain mainnet api
    private static HistoryApi api_sif = null;
    public static HistoryApi getSifApi(Context c) {
        if (api_sif == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_sif))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_sif = retrofit.create(HistoryApi.class);
            }
        }
        return api_sif;
    }

    //Services for sifChain incentive api
    private static SifChain incentive_sif = null;
    public static SifChain getSifIncentiveApi(Context c) {
        if (incentive_sif == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_sif_finance))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                incentive_sif = retrofit.create(SifChain.class);
            }
        }
        return incentive_sif;
    }

    //Services for kifoundation mainnet
    private static KiChain service_ki = null;
    public static KiChain getKiChain(Context c) {
        if (service_ki == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_ki))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_ki = retrofit.create(KiChain.class);
            }
        }
        return service_ki;
    }

    //Services for kifoundation mainnet api
    private static HistoryApi api_ki = null;
    public static HistoryApi getKiApi(Context c) {
        if (api_ki == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_ki))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_ki = retrofit.create(HistoryApi.class);
            }
        }
        return api_ki;
    }

    //Services for osmosis mainnet api
    private static HistoryApi api_osmosis = null;
    public static HistoryApi getOsmosisApi(Context c) {
        if (api_osmosis == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_osmosis))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_osmosis = retrofit.create(HistoryApi.class);
            }
        }
        return api_osmosis;
    }

    //Services for Medi mainnet api
    private static HistoryApi api_medi = null;
    public static HistoryApi getMediblocApi(Context c) {
        if (api_medi == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_medibloc))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_medi = retrofit.create(HistoryApi.class);
            }
        }
        return api_medi;
    }

    //Services for Rizon test api
    private static HistoryApi api_rizon_test = null;
    public static HistoryApi getRizonTestApi(Context c) {
        if (api_rizon_test == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_rizon_test))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_rizon_test = retrofit.create(HistoryApi.class);
            }
        }
        return api_rizon_test;
    }

    //Services for Rizon swap_status test api
    private static HdacChain api_rizon_swap_test_status = null;
    public static HdacChain getRizonSwapTestStatus(Context c) {
        if (api_rizon_swap_test_status == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_rizon_swap_status_testnet))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_rizon_swap_test_status = retrofit.create(HdacChain.class);
            }
        }
        return api_rizon_swap_test_status;
    }

    //Services for Althea test api
    private static HistoryApi api_althea_test = null;
    public static HistoryApi getAltheaTestApi(Context c) {
        if (api_althea_test == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_althea_test))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_althea_test = retrofit.create(HistoryApi.class);
            }
        }
        return api_althea_test;
    }

    //Services for Umee test api
    private static HistoryApi api_umee_test = null;
    public static HistoryApi getUmeeTestApi(Context c) {
        if (api_umee_test == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_umee_test))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_umee_test = retrofit.create(HistoryApi.class);
            }
        }
        return api_umee_test;
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



    //Services for hdac testnet chain
    private static HdacChain service_hdac_mainnet = null;
    public static HdacChain getMainHdac(Context c) {
        if (service_hdac_mainnet == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_hdac_mainnet))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_hdac_mainnet = retrofit.create(HdacChain.class);
            }
        }
        return service_hdac_mainnet;
    }

    //Services for hdac testnet chain
    private static HdacChain service_hdac_testnet = null;
    public static HdacChain getTestHdac(Context c) {
        if (service_hdac_testnet == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_hdac_testnet))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_hdac_testnet = retrofit.create(HdacChain.class);
            }
        }
        return service_hdac_testnet;
    }
}
