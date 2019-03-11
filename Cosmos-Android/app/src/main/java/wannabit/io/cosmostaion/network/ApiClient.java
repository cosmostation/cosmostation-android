package wannabit.io.cosmostaion.network;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.GAIA_12K;
import static wannabit.io.cosmostaion.base.BaseChain.GAIA_13K;

public class ApiClient {

    //TODO delete ssh guard
    private static WannabitChain service_wannabit_chain_12k = null;
    private static WannabitChain service_wannabit_chain_13k = null;
    public static WannabitChain getWannabitChain(Context c, BaseChain chain) {
        String url = "";
        if (chain == GAIA_12K) {
            if (service_wannabit_chain_12k == null) {
                synchronized (ApiClient.class) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(c.getString(R.string.url_lcd_gaia))
                            .client(WUtil.getUnsafeOkHttpClient().build())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    service_wannabit_chain_12k = retrofit.create(WannabitChain.class);
                }
            }
            return service_wannabit_chain_12k;

        } else if (chain == GAIA_13K) {
            if (service_wannabit_chain_13k == null) {
                synchronized (ApiClient.class) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(c.getString(R.string.url_lcd_gaia_13k))
                            .client(WUtil.getUnsafeOkHttpClient().build())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    service_wannabit_chain_13k = retrofit.create(WannabitChain.class);
                }
            }
            return service_wannabit_chain_13k;
        }
        return null;
    }


    private static KeyBaseService service_keybase = null;
    public static KeyBaseService getKeybaseService(Context c) {
        if (service_keybase == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_keybase))
                        .client(WUtil.getUnsafeOkHttpClient().build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_keybase = retrofit.create(KeyBaseService.class);
            }
        }
        return service_keybase;
    }


    private static EsService service_es_12K = null;
    private static EsService service_es_13k = null;
    public static EsService getEsService(Context c, BaseChain chain) {
        if (chain == GAIA_12K) {
            if (service_es_12K == null) {
                synchronized (ApiClient.class) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(c.getString(R.string.url_esearch))
                            .client(WUtil.getUnsafeOkHttpClient().build())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    service_es_12K = retrofit.create(EsService.class);
                }
            }
            return service_es_12K;

        } else if (chain == GAIA_13K) {
            if (service_es_13k == null) {
                synchronized (ApiClient.class) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(c.getString(R.string.url_esearch_13k))
                            .client(WUtil.getUnsafeOkHttpClient().build())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    service_es_13k = retrofit.create(EsService.class);
                }
            }
            return service_es_13k;

        }
        return  null;
    }

    private static Cosmostation cosmostation_12k = null;
    private static Cosmostation cosmostation_13k = null;
    public static Cosmostation getCSService(Context c, BaseChain chain) {
        if (chain == GAIA_12K) {
            if (cosmostation_12k == null) {
                synchronized (ApiClient.class) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(c.getString(R.string.url_cosmostation))
                            .client(WUtil.getUnsafeOkHttpClient().build())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    cosmostation_12k = retrofit.create(Cosmostation.class);
                }
            }
            return cosmostation_12k;
        } else if (chain == GAIA_13K) {
            if (cosmostation_13k == null) {
                synchronized (ApiClient.class) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(c.getString(R.string.url_cosmostation_13k))
                            .client(WUtil.getUnsafeOkHttpClient().build())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    cosmostation_13k = retrofit.create(Cosmostation.class);
                }
            }
            return cosmostation_13k;
        }
        return null;
    }
}
