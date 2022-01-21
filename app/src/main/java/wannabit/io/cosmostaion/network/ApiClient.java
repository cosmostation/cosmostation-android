package wannabit.io.cosmostaion.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
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

    //Services for Emoney mainnet api
    private static HistoryApi api_emoney = null;
    public static HistoryApi getEmoneyApi(Context c) {
        if (api_emoney == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_emoney))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_emoney = retrofit.create(HistoryApi.class);
            }
        }
        return api_emoney;
    }

    //Services for Juno mainnet api
    private static HistoryApi api_juno = null;
    public static HistoryApi getJunoApi(Context c) {
        if (api_juno == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_juno))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_juno = retrofit.create(HistoryApi.class);
            }
        }
        return api_juno;
    }

    //Services for Regen mainnet api
    private static HistoryApi api_regen = null;
    public static HistoryApi getRegenApi(Context c) {
        if (api_regen == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_regen))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_regen = retrofit.create(HistoryApi.class);
            }
        }
        return api_regen;
    }

    //Services for Bitcanna mainnet api
    private static HistoryApi api_bitcanna = null;
    public static HistoryApi getBitcannaApi(Context c) {
        if (api_bitcanna == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_bitcanna))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_bitcanna = retrofit.create(HistoryApi.class);
            }
        }
        return api_bitcanna;
    }

    //Services for Althea mainnet api
    private static HistoryApi api_althea = null;
    public static HistoryApi getAltheaApi(Context c) {
        if (api_althea == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_althea))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_althea = retrofit.create(HistoryApi.class);
            }
        }
        return api_althea;
    }

    //Services for Stargaze mainnet api
    private static HistoryApi api_stargaze = null;
    public static HistoryApi getStargazeApi(Context c) {
        if (api_stargaze == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_stargaze))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_stargaze = retrofit.create(HistoryApi.class);
            }
        }
        return api_stargaze;
    }

    //Services for gravity bridge mainnet api
    private static HistoryApi api_grabridge = null;
    public static HistoryApi getGraBridgeApi(Context c) {
        if (api_grabridge == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_grabridge))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_grabridge = retrofit.create(HistoryApi.class);
            }
        }
        return api_grabridge;
    }

    //Services for comdex mainnet api
    private static HistoryApi api_comdex = null;
    public static HistoryApi getComdexApi(Context c) {
        if (api_comdex == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_comdex))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_comdex = retrofit.create(HistoryApi.class);
            }
        }
        return api_comdex;
    }

    //Services for secret mainnet api
    private static HistoryApi api_secret = null;
    public static HistoryApi getSecretApi(Context c) {
        if (api_secret == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_secret))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_secret = retrofit.create(HistoryApi.class);
            }
        }
        return api_secret;
    }
  
    //Services for injective mainnet api
    private static HistoryApi api_inj = null;
    public static HistoryApi getInjApi(Context c) {
        if (api_inj == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_inj))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_inj = retrofit.create(HistoryApi.class);
            }
        }
        return api_inj;
    }

    //Services for bitsong mainnet api
    private static HistoryApi api_bitsong = null;
    public static HistoryApi getBitsongApi(Context c) {
        if (api_bitsong == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_bitsong))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_bitsong = retrofit.create(HistoryApi.class);
            }
        }
        return api_bitsong;
    }

    //Services for desmos mainnet api
    private static HistoryApi api_desmos = null;
    public static HistoryApi getDesmosApi(Context c) {
        if (api_desmos == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_desmos))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_desmos = retrofit.create(HistoryApi.class);
            }
        }
        return api_desmos;
    }

    //Services for lum mainnet api
    private static HistoryApi api_lum = null;
    public static HistoryApi getLumApi(Context c) {
        if (api_lum == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_lum))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_lum = retrofit.create(HistoryApi.class);
            }
        }
        return api_lum;
    }

    //Services for chihuahua mainnet api
    private static HistoryApi api_chihuahua = null;
    public static HistoryApi getChihuahuaApi(Context c) {
        if (api_chihuahua == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_chihuahua))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_chihuahua = retrofit.create(HistoryApi.class);
            }
        }
        return api_chihuahua;
    }

    //Services for axelar mainnet api
    private static HistoryApi api_axelar = null;
    public static HistoryApi getAxelarApi(Context c) {
        if (api_axelar == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_axelar))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_axelar = retrofit.create(HistoryApi.class);
            }
        }
        return api_axelar;
    }
  
    //Services for Rizon mainnet api
    private static HistoryApi api_rizon = null;
    public static HistoryApi getRizonApi(Context c) {
        if (api_rizon == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_rizon))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_rizon = retrofit.create(HistoryApi.class);
            }
        }
        return api_rizon;
    }

    //Services for Rizon swap_status api
    private static HdacChain api_rizon_swap_status = null;
    public static HdacChain getRizonSwapStatus(Context c) {
        if (api_rizon_swap_status == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_rizon_swap_status_mainnet))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_rizon_swap_status = retrofit.create(HdacChain.class);
            }
        }
        return api_rizon_swap_status;
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

    //Services for Axelar test api
    private static HistoryApi api_axelar_test = null;
    public static HistoryApi getAxelarTestApi(Context c) {
        if (api_axelar_test == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_api_axelar))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                api_axelar_test = retrofit.create(HistoryApi.class);
            }
        }
        return api_axelar_test;
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



    //Services for hdac mainnet chain
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
