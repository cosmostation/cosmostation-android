package wannabit.io.cosmostaion.network;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.GAIA_12K;
import static wannabit.io.cosmostaion.base.BaseChain.GAIA_13K;

public class ApiClient {

    //TODO delete ssh guard
    private static WannabitChain service_wannabit_chain_12k = null;
    private static WannabitChain service_wannabit_chain_13k = null;
    private static WannabitChain service_wannabit_main = null;
    public static WannabitChain getWannabitChain(Context c, BaseChain chain) {
        String url = "";
        if (chain == GAIA_12K) {
            if (service_wannabit_chain_12k == null) {
                synchronized (ApiClient.class) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(c.getString(R.string.url_lcd_gaia))
//                            .client(WUtil.getUnsafeOkHttpClient().build())
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
//                            .client(WUtil.getUnsafeOkHttpClient().build())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    service_wannabit_chain_13k = retrofit.create(WannabitChain.class);
                }
            }
            return service_wannabit_chain_13k;

        } else if (chain == COSMOS_MAIN) {
            if (service_wannabit_main == null) {
                synchronized (ApiClient.class) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(c.getString(R.string.url_lcd_main))
//                            .client(WUtil.getUnsafeOkHttpClient().build())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    service_wannabit_main = retrofit.create(WannabitChain.class);
                }
            }
            return service_wannabit_main;
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
    private static EsService service_es_main = null;
    public static EsService getEsService(Context c, BaseChain chain) {
        if (chain == GAIA_12K|| chain == COSMOS_MAIN) {
            if (service_es_12K == null ) {
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
        /*
        else if (chain == COSMOS_MAIN) {
            if (service_es_main == null) {
                synchronized (ApiClient.class) {

                    TreeMap<String, String> awsHeaders = new TreeMap<String, String>();
                    awsHeaders.put("host", host);

                    AWSV4Auth aWSV4Auth = new AWSV4Auth.Builder(ACCESS_KEY, SECRET_KEY)
                            .regionName(REGION)
                            .serviceName(SERVICE_NAME) // es - elastic search. use your service name
                            .httpMethodName("GET") //GET, PUT, POST, DELETE, etc...
                            .canonicalURI(query) //end point
                            .queryParametes(null) //query parameters if any
                            .awsHeaders(awsHeaders) //aws header parameters
                            .payload(null) // payload if any
                            .debug() // turn on the debug mode
                            .build();

                    final Map<String, String> header = aWSV4Auth.getHeaders();

                    WLog.w("x-amz-date!!!   " + header.get("x-amz-date"));
                    WLog.w("Authorization   " + header.get("Authorization") );

                    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                    httpClient.addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Interceptor.Chain chain) throws IOException {
                            Request original = chain.request();
                            Request request = original.newBuilder()
                                    .header("Host", host)
                                    .header("X-Amz-Date", header.get("x-amz-date"))
                                    .header("Authorization", header.get("Authorization"))
                                    .method(original.method(), original.body())
                                    .build();
                            return chain.proceed(request);
                        }
                    });
                    OkHttpClient client = httpClient.build();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(c.getString(R.string.url_esearch_main))
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .build();
                    service_es_main = retrofit.create(EsService.class);
                }
            }
            return service_es_main;

        }
        */
        return  null;
    }


    private static String host = "search-cosmostation-explorer-prod-ycwrdnmjwpn2mzjycmqjgjfboi.ap-northeast-2.es.amazonaws.com";
    private static String ACCESS_KEY = "AKIAIZ33YYSDAH5ORLVA";
    private static String SECRET_KEY = "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";
    private static String REGION = "ap-northeast-2";
    private static String SERVICE_NAME = "es";
    private static String query = "/txs/_search";



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


    private static MarketCapService marketCapService = null;
    public static MarketCapService getCMCClient(Context c) {
        if (marketCapService == null) {
            synchronized (ApiClient.class) {
                if (marketCapService == null)  {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(c.getString(R.string.coinmarketcap_url))
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    marketCapService = retrofit.create(MarketCapService.class);
                }
            }
        }
        return marketCapService;
    }

//    public static Call<JsonObject> getAtomTic(Context c, int id, String currency) {
//        MarketCapService service = getCMCClient(c).create(MarketCapService.class);
//        return service.getEosTic(id, currency);
//    }

}
