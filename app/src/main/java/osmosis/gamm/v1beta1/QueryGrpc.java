package osmosis.gamm.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: osmosis/gamm/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "osmosis.gamm.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsResponse> getPoolsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Pools",
      requestType = osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsRequest.class,
      responseType = osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsResponse> getPoolsMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsRequest, osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsResponse> getPoolsMethod;
    if ((getPoolsMethod = QueryGrpc.getPoolsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolsMethod = QueryGrpc.getPoolsMethod) == null) {
          QueryGrpc.getPoolsMethod = getPoolsMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsRequest, osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Pools"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Pools"))
              .build();
        }
      }
    }
    return getPoolsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsResponse> getNumPoolsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "NumPools",
      requestType = osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsRequest.class,
      responseType = osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsResponse> getNumPoolsMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsRequest, osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsResponse> getNumPoolsMethod;
    if ((getNumPoolsMethod = QueryGrpc.getNumPoolsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getNumPoolsMethod = QueryGrpc.getNumPoolsMethod) == null) {
          QueryGrpc.getNumPoolsMethod = getNumPoolsMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsRequest, osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NumPools"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("NumPools"))
              .build();
        }
      }
    }
    return getNumPoolsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityResponse> getTotalLiquidityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalLiquidity",
      requestType = osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityRequest.class,
      responseType = osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityResponse> getTotalLiquidityMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityRequest, osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityResponse> getTotalLiquidityMethod;
    if ((getTotalLiquidityMethod = QueryGrpc.getTotalLiquidityMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalLiquidityMethod = QueryGrpc.getTotalLiquidityMethod) == null) {
          QueryGrpc.getTotalLiquidityMethod = getTotalLiquidityMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityRequest, osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalLiquidity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalLiquidity"))
              .build();
        }
      }
    }
    return getTotalLiquidityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolResponse> getPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Pool",
      requestType = osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolRequest.class,
      responseType = osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolResponse> getPoolMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolRequest, osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolResponse> getPoolMethod;
    if ((getPoolMethod = QueryGrpc.getPoolMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolMethod = QueryGrpc.getPoolMethod) == null) {
          QueryGrpc.getPoolMethod = getPoolMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolRequest, osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Pool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Pool"))
              .build();
        }
      }
    }
    return getPoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsResponse> getPoolParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PoolParams",
      requestType = osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsRequest.class,
      responseType = osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsResponse> getPoolParamsMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsRequest, osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsResponse> getPoolParamsMethod;
    if ((getPoolParamsMethod = QueryGrpc.getPoolParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolParamsMethod = QueryGrpc.getPoolParamsMethod) == null) {
          QueryGrpc.getPoolParamsMethod = getPoolParamsMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsRequest, osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PoolParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PoolParams"))
              .build();
        }
      }
    }
    return getPoolParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesResponse> getTotalSharesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalShares",
      requestType = osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesRequest.class,
      responseType = osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesResponse> getTotalSharesMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesRequest, osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesResponse> getTotalSharesMethod;
    if ((getTotalSharesMethod = QueryGrpc.getTotalSharesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalSharesMethod = QueryGrpc.getTotalSharesMethod) == null) {
          QueryGrpc.getTotalSharesMethod = getTotalSharesMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesRequest, osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalShares"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalShares"))
              .build();
        }
      }
    }
    return getTotalSharesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsResponse> getPoolAssetsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PoolAssets",
      requestType = osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsRequest.class,
      responseType = osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsResponse> getPoolAssetsMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsRequest, osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsResponse> getPoolAssetsMethod;
    if ((getPoolAssetsMethod = QueryGrpc.getPoolAssetsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolAssetsMethod = QueryGrpc.getPoolAssetsMethod) == null) {
          QueryGrpc.getPoolAssetsMethod = getPoolAssetsMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsRequest, osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PoolAssets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PoolAssets"))
              .build();
        }
      }
    }
    return getPoolAssetsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceResponse> getSpotPriceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SpotPrice",
      requestType = osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceRequest.class,
      responseType = osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceResponse> getSpotPriceMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceRequest, osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceResponse> getSpotPriceMethod;
    if ((getSpotPriceMethod = QueryGrpc.getSpotPriceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSpotPriceMethod = QueryGrpc.getSpotPriceMethod) == null) {
          QueryGrpc.getSpotPriceMethod = getSpotPriceMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceRequest, osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SpotPrice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SpotPrice"))
              .build();
        }
      }
    }
    return getSpotPriceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInResponse> getEstimateSwapExactAmountInMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EstimateSwapExactAmountIn",
      requestType = osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInRequest.class,
      responseType = osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInResponse> getEstimateSwapExactAmountInMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInRequest, osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInResponse> getEstimateSwapExactAmountInMethod;
    if ((getEstimateSwapExactAmountInMethod = QueryGrpc.getEstimateSwapExactAmountInMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEstimateSwapExactAmountInMethod = QueryGrpc.getEstimateSwapExactAmountInMethod) == null) {
          QueryGrpc.getEstimateSwapExactAmountInMethod = getEstimateSwapExactAmountInMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInRequest, osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EstimateSwapExactAmountIn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EstimateSwapExactAmountIn"))
              .build();
        }
      }
    }
    return getEstimateSwapExactAmountInMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutResponse> getEstimateSwapExactAmountOutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EstimateSwapExactAmountOut",
      requestType = osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutRequest.class,
      responseType = osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutRequest,
      osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutResponse> getEstimateSwapExactAmountOutMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutRequest, osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutResponse> getEstimateSwapExactAmountOutMethod;
    if ((getEstimateSwapExactAmountOutMethod = QueryGrpc.getEstimateSwapExactAmountOutMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEstimateSwapExactAmountOutMethod = QueryGrpc.getEstimateSwapExactAmountOutMethod) == null) {
          QueryGrpc.getEstimateSwapExactAmountOutMethod = getEstimateSwapExactAmountOutMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutRequest, osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EstimateSwapExactAmountOut"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EstimateSwapExactAmountOut"))
              .build();
        }
      }
    }
    return getEstimateSwapExactAmountOutMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QueryStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryStub>() {
        @java.lang.Override
        public QueryStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryStub(channel, callOptions);
        }
      };
    return QueryStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QueryBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryBlockingStub>() {
        @java.lang.Override
        public QueryBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryBlockingStub(channel, callOptions);
        }
      };
    return QueryBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QueryFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryFutureStub>() {
        @java.lang.Override
        public QueryFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryFutureStub(channel, callOptions);
        }
      };
    return QueryFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     */
    public void pools(osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolsMethod(), responseObserver);
    }

    /**
     */
    public void numPools(osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNumPoolsMethod(), responseObserver);
    }

    /**
     */
    public void totalLiquidity(osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTotalLiquidityMethod(), responseObserver);
    }

    /**
     * <pre>
     * Per Pool gRPC Endpoints
     * </pre>
     */
    public void pool(osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolMethod(), responseObserver);
    }

    /**
     */
    public void poolParams(osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolParamsMethod(), responseObserver);
    }

    /**
     */
    public void totalShares(osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTotalSharesMethod(), responseObserver);
    }

    /**
     */
    public void poolAssets(osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolAssetsMethod(), responseObserver);
    }

    /**
     */
    public void spotPrice(osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSpotPriceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Estimate the swap.
     * </pre>
     */
    public void estimateSwapExactAmountIn(osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEstimateSwapExactAmountInMethod(), responseObserver);
    }

    /**
     */
    public void estimateSwapExactAmountOut(osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEstimateSwapExactAmountOutMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPoolsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsRequest,
                osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsResponse>(
                  this, METHODID_POOLS)))
          .addMethod(
            getNumPoolsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsRequest,
                osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsResponse>(
                  this, METHODID_NUM_POOLS)))
          .addMethod(
            getTotalLiquidityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityRequest,
                osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityResponse>(
                  this, METHODID_TOTAL_LIQUIDITY)))
          .addMethod(
            getPoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolRequest,
                osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolResponse>(
                  this, METHODID_POOL)))
          .addMethod(
            getPoolParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsRequest,
                osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsResponse>(
                  this, METHODID_POOL_PARAMS)))
          .addMethod(
            getTotalSharesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesRequest,
                osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesResponse>(
                  this, METHODID_TOTAL_SHARES)))
          .addMethod(
            getPoolAssetsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsRequest,
                osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsResponse>(
                  this, METHODID_POOL_ASSETS)))
          .addMethod(
            getSpotPriceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceRequest,
                osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceResponse>(
                  this, METHODID_SPOT_PRICE)))
          .addMethod(
            getEstimateSwapExactAmountInMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInRequest,
                osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInResponse>(
                  this, METHODID_ESTIMATE_SWAP_EXACT_AMOUNT_IN)))
          .addMethod(
            getEstimateSwapExactAmountOutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutRequest,
                osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutResponse>(
                  this, METHODID_ESTIMATE_SWAP_EXACT_AMOUNT_OUT)))
          .build();
    }
  }

  /**
   */
  public static final class QueryStub extends io.grpc.stub.AbstractAsyncStub<QueryStub> {
    private QueryStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryStub(channel, callOptions);
    }

    /**
     */
    public void pools(osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void numPools(osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNumPoolsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void totalLiquidity(osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTotalLiquidityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Per Pool gRPC Endpoints
     * </pre>
     */
    public void pool(osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void poolParams(osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void totalShares(osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTotalSharesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void poolAssets(osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolAssetsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void spotPrice(osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSpotPriceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Estimate the swap.
     * </pre>
     */
    public void estimateSwapExactAmountIn(osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEstimateSwapExactAmountInMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void estimateSwapExactAmountOut(osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutRequest request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEstimateSwapExactAmountOutMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class QueryBlockingStub extends io.grpc.stub.AbstractBlockingStub<QueryBlockingStub> {
    private QueryBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryBlockingStub(channel, callOptions);
    }

    /**
     */
    public osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsResponse pools(osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolsMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsResponse numPools(osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsRequest request) {
      return blockingUnaryCall(
          getChannel(), getNumPoolsMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityResponse totalLiquidity(osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityRequest request) {
      return blockingUnaryCall(
          getChannel(), getTotalLiquidityMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Per Pool gRPC Endpoints
     * </pre>
     */
    public osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolResponse pool(osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsResponse poolParams(osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesResponse totalShares(osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesRequest request) {
      return blockingUnaryCall(
          getChannel(), getTotalSharesMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsResponse poolAssets(osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolAssetsMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceResponse spotPrice(osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceRequest request) {
      return blockingUnaryCall(
          getChannel(), getSpotPriceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Estimate the swap.
     * </pre>
     */
    public osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInResponse estimateSwapExactAmountIn(osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInRequest request) {
      return blockingUnaryCall(
          getChannel(), getEstimateSwapExactAmountInMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutResponse estimateSwapExactAmountOut(osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutRequest request) {
      return blockingUnaryCall(
          getChannel(), getEstimateSwapExactAmountOutMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class QueryFutureStub extends io.grpc.stub.AbstractFutureStub<QueryFutureStub> {
    private QueryFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsResponse> pools(
        osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsResponse> numPools(
        osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNumPoolsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityResponse> totalLiquidity(
        osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTotalLiquidityMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Per Pool gRPC Endpoints
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolResponse> pool(
        osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsResponse> poolParams(
        osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesResponse> totalShares(
        osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTotalSharesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsResponse> poolAssets(
        osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolAssetsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceResponse> spotPrice(
        osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSpotPriceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Estimate the swap.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInResponse> estimateSwapExactAmountIn(
        osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEstimateSwapExactAmountInMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutResponse> estimateSwapExactAmountOut(
        osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEstimateSwapExactAmountOutMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_POOLS = 0;
  private static final int METHODID_NUM_POOLS = 1;
  private static final int METHODID_TOTAL_LIQUIDITY = 2;
  private static final int METHODID_POOL = 3;
  private static final int METHODID_POOL_PARAMS = 4;
  private static final int METHODID_TOTAL_SHARES = 5;
  private static final int METHODID_POOL_ASSETS = 6;
  private static final int METHODID_SPOT_PRICE = 7;
  private static final int METHODID_ESTIMATE_SWAP_EXACT_AMOUNT_IN = 8;
  private static final int METHODID_ESTIMATE_SWAP_EXACT_AMOUNT_OUT = 9;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final QueryImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(QueryImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_POOLS:
          serviceImpl.pools((osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolsResponse>) responseObserver);
          break;
        case METHODID_NUM_POOLS:
          serviceImpl.numPools((osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryNumPoolsResponse>) responseObserver);
          break;
        case METHODID_TOTAL_LIQUIDITY:
          serviceImpl.totalLiquidity((osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalLiquidityResponse>) responseObserver);
          break;
        case METHODID_POOL:
          serviceImpl.pool((osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolResponse>) responseObserver);
          break;
        case METHODID_POOL_PARAMS:
          serviceImpl.poolParams((osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolParamsResponse>) responseObserver);
          break;
        case METHODID_TOTAL_SHARES:
          serviceImpl.totalShares((osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryTotalSharesResponse>) responseObserver);
          break;
        case METHODID_POOL_ASSETS:
          serviceImpl.poolAssets((osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QueryPoolAssetsResponse>) responseObserver);
          break;
        case METHODID_SPOT_PRICE:
          serviceImpl.spotPrice((osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QuerySpotPriceResponse>) responseObserver);
          break;
        case METHODID_ESTIMATE_SWAP_EXACT_AMOUNT_IN:
          serviceImpl.estimateSwapExactAmountIn((osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountInResponse>) responseObserver);
          break;
        case METHODID_ESTIMATE_SWAP_EXACT_AMOUNT_OUT:
          serviceImpl.estimateSwapExactAmountOut((osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.QueryOuterClass.QuerySwapExactAmountOutResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return osmosis.gamm.v1beta1.QueryOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Query");
    }
  }

  private static final class QueryFileDescriptorSupplier
      extends QueryBaseDescriptorSupplier {
    QueryFileDescriptorSupplier() {}
  }

  private static final class QueryMethodDescriptorSupplier
      extends QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    QueryMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (QueryGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QueryFileDescriptorSupplier())
              .addMethod(getPoolsMethod())
              .addMethod(getNumPoolsMethod())
              .addMethod(getTotalLiquidityMethod())
              .addMethod(getPoolMethod())
              .addMethod(getPoolParamsMethod())
              .addMethod(getTotalSharesMethod())
              .addMethod(getPoolAssetsMethod())
              .addMethod(getSpotPriceMethod())
              .addMethod(getEstimateSwapExactAmountInMethod())
              .addMethod(getEstimateSwapExactAmountOutMethod())
              .build();
        }
      }
    }
    return result;
  }
}
