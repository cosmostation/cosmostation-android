package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqTx;
import wannabit.io.cosmostaion.network.res.ResHistory;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class HistoryTask extends CommonTask {

    private ReqTx   mReq;
    private BaseChain mChain;

    public HistoryTask(BaseApplication app, TaskListener listener, ReqTx mReq, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_HISTORY;
        this.mReq = mReq;
        this.mChain = chain;
    }

//    private static String url = "https://search-cosmostation-explorer-prod-ycwrdnmjwpn2mzjycmqjgjfboi.ap-northeast-2.es.amazonaws.com/txs/_search";
//    private static String host = "search-cosmostation-explorer-prod-ycwrdnmjwpn2mzjycmqjgjfboi.ap-northeast-2.es.amazonaws.com";
//    private static String ACCESS_KEY = "AKIAJAEUOYIPPOF4UA6A";
//    private static String SECRET_KEY = "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";
//    private static String REGION = "ap-northeast-2";
//    private static String SERVICE_NAME = "es";
//    private static String  query = "/txs/_search";

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {

            Response<ResHistory> response = ApiClient.getEsService(mApp, mChain).getTx(mReq).execute();
            if(response.isSuccessful() && response.body() != null) {
                mResult.resultData = response.body().hits.hits;
                mResult.isSuccess = true;
            } else {
                WLog.w("HistoryTask : NOk");

            }

//            if(mChain.equals(BaseChain.COSMOS_MAIN)) {
//                TreeMap<String, String> awsHeaders = new TreeMap<String, String>();
//                awsHeaders.put("host", host);
//
//
//                AWSV4Auth aWSV4Auth = new AWSV4Auth.Builder(ACCESS_KEY, SECRET_KEY)
//                        .regionName(REGION)
//                        .serviceName(SERVICE_NAME) // es - elastic search. use your service name
//                        .httpMethodName("GET") //GET, PUT, POST, DELETE, etc...
//                        .canonicalURI(query) //end point
//                        .queryParametes(null) //query parameters if any
//                        .awsHeaders(awsHeaders) //aws header parameters
//                        .payload(null) // payload if any
//                        .debug() // turn on the debug mode
//                        .build();
//
//                final Map<String, String> header = aWSV4Auth.getHeaders();
//
//                OkHttpClient client = new OkHttpClient();
//                HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
//                String requestUrl = urlBuilder.build().toString();
//                Request request = new Request.Builder()
//                        .url(requestUrl)
//                        .addHeader("Host", host)
//                        .addHeader("X-Amz-Date", header.get("x-amz-date"))
//                        .addHeader("Authorization", header.get("Authorization"))
//                        .build();
//
//                client.newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(okhttp3.Call call, IOException e) {
//                        WLog.w("onFailure");
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        WLog.w("onResponse : " + response.toString());
//                        WLog.w("onResponse : " + response.body().string());
//                    }
//                });
//
//
//            } else {
//
//            }
//            Response<ResHistory> response = ApiClient.getEsService(mApp, mChain).getTx(mReq).execute();
//            if(response.isSuccessful() && response.body() != null) {
//                mResult.resultData = response.body().hits.hits;
//                mResult.isSuccess = true;
//            } else {
//                WLog.w("HistoryTask : NOk");
//
//            }
//            ApiClient.getEsService(mApp, mChain).getTx2(mReq).enqueue(new Callback<JsonObject>() {
//                @Override
//                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                    WLog.w("HistoryTask : Ok : ");
////                    WLog.w("HistoryTask : Ok : " + response.body().hits.hits.size());
//                    if(response == null) {
//                        WLog.w("HistoryTask 111");
//                    }
//                    WLog.w("HistoryTask response : " + response);
//
//                    if(response.body() == null) {
//                        WLog.w("HistoryTask 2222");
//                    }
//
//                    WLog.w("call : " + call.request().url().toString());
//                    WLog.w("call : " + call.request().body());
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
//                public void onFailure(Call<JsonObject> call, Throwable t) {
//                    WLog.w("HistoryTask : NOk");
//                }
//            });

        } catch (Exception e) {
            WLog.w("HistoryTask Error " + e.getMessage());
        }
        return mResult;
    }
}
