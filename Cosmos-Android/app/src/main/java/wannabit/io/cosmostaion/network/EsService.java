package wannabit.io.cosmostaion.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.req.ReqTx;
import wannabit.io.cosmostaion.network.req.ReqTxVal;
import wannabit.io.cosmostaion.network.res.ResHistory;

public interface EsService {

    @POST("/txs/_search")
    Call<ResHistory> getTx(@Body ReqTx data);


    @POST("/txs/_search")
    Call<ResHistory> getValTx(@Body ReqTxVal data);

    @GET("/test/_search")
    Call<JsonObject> getTest();


//    @Headers({
//            "Host: search-cosmostation-explorer-prod-ycwrdnmjwpn2mzjycmqjgjfboi.ap-northeast-2.es.amazonaws.com",
//            "X-Amz-Date: 20190313T061741Z",
//            "Authorization: AWS4-HMAC-SHA256 Credential=AKIAIZ33YYSDAH5ORLVA/20190313/ap-northeast-2/es/aws4_request, SignedHeaders=cache-control;content-type;host;postman-token;x-amz-date, Signature=b5477ff37f16cf522d3d2bd54fcf3156ab93245d0a6d9b7a1914359259da4e54",
//            "Content-Type: application/json"
//    })
//    @GET("/")
//    Call<JsonObject> getTest();

//    Call<JsonObject> getTest(@Header("Content-Type")String content_type,
//                             @Header("X-Amz-Date")String amz_date,
//                             @Header("Authorization")String auth,
//                             @Header("Host")String host);
//    Call<JsonObject> getTest(@Header("accept")String content_type,
//                             @Header("host")String host,
//                             @Header("x-amz-date")String amz_date,
//                             @Header("Authorization")String auth);
//    Call<JsonObject> getTest(@Header("Content-Type")String content_type,
//                             @Header("Host")String host,
//                             @Header("X-Amz-Date")String amz_date,
//                             @Header("Authorization")String auth);

//    Call<JsonObject> getTest(
//                             @Header("Host")String host,
//                             @Header("X-Amz-Date")String amz_date,
//                             @Header("Authorization")String auth);

//    Call<JsonObject> getTest(
//            @Header("X-Amz-Date")String amz_date,
//            @Header("Authorization")String auth);


}
