package wannabit.io.cosmostaion.network;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.utils.WUtil;

public class ApiClient {

    private static CosmosChain service_wannabit_main = null;
    public static CosmosChain getCosmosChain(Context c) {
        if (service_wannabit_main == null) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_lcd_main))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_wannabit_main = retrofit.create(CosmosChain.class);
            }
        }
        return service_wannabit_main;
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

    private static CosmosEsService service_es_proxy = null;
    public static CosmosEsService getCosmosEs(Context c) {
        if (service_es_proxy == null ) {
            synchronized (ApiClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url_es_proxy))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service_es_proxy = retrofit.create(CosmosEsService.class);
            }
        }
        return service_es_proxy;
    }

}
