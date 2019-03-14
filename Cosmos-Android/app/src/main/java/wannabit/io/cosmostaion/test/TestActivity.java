package wannabit.io.cosmostaion.test;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wannabit.io.cosmostaion.AWSV4Auth;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.EsService;
import wannabit.io.cosmostaion.network.req.ReqTx;
import wannabit.io.cosmostaion.utils.WLog;

public class TestActivity extends BaseActivity {

    private static String url2 = "https://search-cosmostation-explorer-prod-ycwrdnmjwpn2mzjycmqjgjfboi.ap-northeast-2.es.amazonaws.com/";
    private static String url = "https://search-cosmostation-explorer-prod-ycwrdnmjwpn2mzjycmqjgjfboi.ap-northeast-2.es.amazonaws.com/txs/_search";
//    private static String url = "https://search-cosmostation-explorer-prod-ycwrdnmjwpn2mzjycmqjgjfboi.ap-northeast-2.es.amazonaws.com/test/_search";
    private static String host = "search-cosmostation-explorer-prod-ycwrdnmjwpn2mzjycmqjgjfboi.ap-northeast-2.es.amazonaws.com";
    private static String ACCESS_KEY = "AKIAJYCEXW3A3FBCO5UA";
    private static String SECRET_KEY = "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";
    private static String REGION = "ap-northeast-2";
    private static String SERVICE_NAME = "es";
    private static String  query = "/txs/_search";
//    private static String  query = "/test/_search";
    private static String  payload = "/txs/_search";
    private static String  payload2 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        ReqTx req = new ReqTx(0, 0, true, "cosmos1clpqr4nrk4khgkxj78fcwwh6dl3uw4ep4tgu9q", BaseChain.COSMOS_MAIN);
//        WLog.w("req : " + new Gson().toJson(req));
//        RequestBody body = RequestBody.create(JSON, new Gson().toJson(req));


        TreeMap<String, String> awsHeaders = new TreeMap<String, String>();
        awsHeaders.put("host", host);


        AWSV4Auth aWSV4Auth = new AWSV4Auth.Builder(ACCESS_KEY, SECRET_KEY)
                .regionName(REGION)
                .serviceName(SERVICE_NAME) // es - elastic search. use your service name
                .httpMethodName("POST") //GET, PUT, POST, DELETE, etc...
                .canonicalURI(query) //end point
                .queryParametes(null) //query parameters if any
                .awsHeaders(awsHeaders) //aws header parameters
                .payload(null) // payload if any
//                .debug() // turn on the debug mode
                .build();

        final Map<String, String> header = aWSV4Auth.getHeaders();
        for (Map.Entry<String, String> entrySet : header.entrySet()) {
            String key = entrySet.getKey();
            String value = entrySet.getValue();
        }
        WLog.w("x-amz-date!!!   " + header.get("x-amz-date"));
        WLog.w("Authorization   " + header.get("Authorization") );

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        ReqTx req = new ReqTx(0, 0, true, "cosmos1clpqr4nrk4khgkxj78fcwwh6dl3uw4ep4tgu9q", BaseChain.COSMOS_MAIN);
        WLog.w("req : " + new Gson().toJson(req));
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(req));

//        StringEntity payload = new StringEntity(jsonDocument);


        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        String requestUrl = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(requestUrl)
                .method("POST", body)

//                .post(RequestBody.create(MediaType.parse("application/json"), new Gson().toJson("")))
//                .post("")
                .addHeader("Host", host)
                .addHeader("X-Amz-Date", header.get("x-amz-date"))
                .addHeader("Authorization", header.get("Authorization"))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                WLog.w("onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                WLog.w("onResponse : " + response.toString());
                WLog.w("onResponse : " + response.body().string());
            }
        });




////        long timeNow = Calendar.getInstance().getTimeInMillis();
////
////        String amzDate = convertDateTo(ISO8601,timeNow);
////        WLog.w("amzDate : " + amzDate);
//        try {
////            String signature = getSignature(url, timeNow, query);
////            WLog.w("Signature : " + signature);
////            String oauth = "AWS4-HMAC-SHA256 Credential="+ ACCESS_KEY+"/"+convertDateTo(SHORT_DATE,timeNow)+"/ap-northeast-2/es/aws4_request, SignedHeaders=content-type;host;x-amz-date, Signature="+ signature;
////            WLog.w("oauth : " + oauth);
//
//            ReqTx req = new ReqTx(0, 0, true, "cosmos1clpqr4nrk4khgkxj78fcwwh6dl3uw4ep4tgu9q", BaseChain.COSMOS_MAIN);
//
//            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//            httpClient.addInterceptor(new Interceptor() {
//                  @Override
//                  public Response intercept(Interceptor.Chain chain) throws IOException {
//                      Request original = chain.request();
//                      Request request = original.newBuilder()
//                              .header("Host", host)
//                              .header("X-Amz-Date", header.get("x-amz-date"))
//                              .header("Authorization", header.get("Authorization"))
////                              .method("POST", original.body())
//                              .build();
//                      WLog.w("original.method() : " + original.method());
//                      return chain.proceed(request);
//                  }
//            });
//            OkHttpClient client = httpClient.build();
//
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(url2)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(client)
//                    .build();
//            EsService es = retrofit.create(EsService.class);
////            Request.Builder builder =  es.getTx3(req).request().newBuilder().get();
////            Field field = Request.Builder.class.getField("method");
////            field.setAccessible(true);
////            field.set(builder, "GET");
//
//
////            es.getTest(req).enqueue(new retrofit2.Callback<JsonObject>() {
//            es.getTx3(req).enqueue(new retrofit2.Callback<JsonObject>() {
//                @Override
//                public void onResponse(retrofit2.Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
//                    WLog.w("code : "+response.code());
//
//                    if(response.body() != null) WLog.w("body" + response.body().toString());
//                    else WLog.w("body null");
//
//                    try {
//                        JSONObject jObjError = new JSONObject(response.errorBody().string());
//                        WLog.w("jObjError1 : " + jObjError.toString());
//                    } catch (Exception e) {
//                        WLog.w("jObjError3 : " + e.getMessage());
//                    }
//                }
//
//                @Override
//                public void onFailure(retrofit2.Call<JsonObject> call, Throwable t) {
//                    WLog.w("onFailure : " + t.getMessage());
//                }
//            });
//
//        }catch (Exception e) {
//            WLog.w("ERROR : " + e.getMessage());
//        }

//        RestClient esClient = esClient(serviceName, region);

    }
    private static String serviceName = "es";
    private static String region = "ap-northeast-2";
    private static String aesEndpoint = "https://search-cosmostation-explorer-prod-ycwrdnmjwpn2mzjycmqjgjfboi.ap-northeast-2.es.amazonaws.com";
//    static final AWSCredentialsProvider credentialsProvider = new DefaultAWSCredentialsProviderChain();
//
//    public static RestClient esClient(String serviceName, String region) {
//        AWS4Signer signer = new AWS4Signer();
//        signer.setServiceName(serviceName);
//        signer.setRegionName(region);
//        final HttpRequestInterceptor interceptor = new AWSRequestSigningApacheInterceptor(serviceName, signer, credentialsProvider);
//        return RestClient.builder(HttpHost.create(aesEndpoint)).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
//            @Override
//            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
//                return httpAsyncClientBuilder.addInterceptorLast(interceptor);
//            }
//        }).build();
//    }


    private static byte[] HmacSHA256(String data, byte[] key) throws Exception {
        String algorithm="HmacSHA256";
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(key, algorithm));
        return mac.doFinal(data.getBytes("UTF8"));
    }

    private static byte[] getSignatureKey(String key, String dateStamp, String regionName, String serviceName) throws Exception {
        byte[] kSecret = ("AWS4" + key).getBytes("UTF8");
        byte[] kDate = HmacSHA256(dateStamp, kSecret);
        byte[] kRegion = HmacSHA256(regionName, kDate);
        byte[] kService = HmacSHA256(serviceName, kRegion);
        byte[] kSigning = HmacSHA256("aws4_request", kService);
        return kSigning;
    }

    private String SHA256HEX(String content) throws Exception
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(content.getBytes(UTF_8));
        return encodeToBase16(hash);
    }

    public String getSignature(String url,long date,String payLoad) throws Exception
    {
        String signature = "";

        String amzDate = convertDateTo(ISO8601_HUMAN_READABLE,date);
        String amzDateShort = convertDateTo(ISO8601_HUMAN_READABLE,date).replace(":","").replace("-","").split("\\.")[0] + "Z";
        String dateStamp = convertDateTo(SHORT_DATE,date);

        String separator = File.separator;
        separator += separator;
        String host = url.split(separator)[1];

        String canonicalHeaders = "Content-type: application/json\nHost: " + host + "\nX-Amz-Date: " + amzDate + "\n";

        String signedHeaders = "content-type;host;x-amz-date";

        String payLoadHash = SHA256HEX(payLoad);

        String canonicalRequest = "POST\n" + query + "\n\n" + canonicalHeaders +"\n" + signedHeaders + "\n"+ payLoadHash;

        String algorithm = "AWS4-HMAC-SHA256";
        String credentialScope = dateStamp + "/" + REGION + "/" + SERVICE_NAME + "/aws4_request";
        String stringToSign = algorithm + "\n" +  amzDateShort + "\n" +  credentialScope + "\n" +  SHA256HEX(canonicalRequest);

        byte[] signingKey = getSignatureKey(SECRET_KEY,dateStamp,REGION,SERVICE_NAME);

        signature = encodeToBase16(HmacSHA256(stringToSign,signingKey)) ;

        return signature;
    }



    private static int LOG_PRIORITY = 3;

    public final static String UTF_8 = "UTF-8";

    public static int timeout = 10000;

    public static DateFormat ISO8601_HUMAN_READABLE = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") ;
    public static DateFormat ISO8601 = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'") ;
    public static DateFormat SHORT_DATE_SEPARATOR = new SimpleDateFormat("yyyy-MM-dd") ;
    public static DateFormat SHORT_DATE = new SimpleDateFormat("yyyyMMdd") ;

    public static String getUTCDate(DateFormat dateFormat)
    {
        return convertDateTo(dateFormat,System.currentTimeMillis());
    }

    /**
     * Get the ISO 8601 Format of the current UTC Time
     * @return current time
     */
    public static String getUTCDate()
    {
        return getUTCDate(ISO8601);
    }

    public static String getUTCDateLong()
    {
        return getUTCDate(ISO8601_HUMAN_READABLE);
    }

    public static String convertDateTo(DateFormat dateFormat, long date)
    {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        dateFormat.setTimeZone(tz);
        return dateFormat.format(new Date(date));
    }

    public static String encodeToBase16(byte[] byteArray) {

        final char[] HEX = new char[]{
                '0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

        StringBuffer hexBuffer = new StringBuffer(byteArray.length * 2);
        for (int i = 0; i < byteArray.length; i++)
            for (int j = 1; j >= 0; j--)
                hexBuffer.append(HEX[(byteArray[i] >> (j * 4)) & 0xF]);
        return hexBuffer.toString();
    }
}
