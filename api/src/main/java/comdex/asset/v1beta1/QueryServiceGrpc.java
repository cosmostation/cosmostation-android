package comdex.asset.v1beta1;

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
    comments = "Source: comdex/asset/v1beta1/querier.proto")
public final class QueryServiceGrpc {

  private QueryServiceGrpc() {}

  public static final String SERVICE_NAME = "comdex.asset.v1beta1.QueryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<comdex.asset.v1beta1.Querier.QueryAssetsRequest,
      comdex.asset.v1beta1.Querier.QueryAssetsResponse> getQueryAssetsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryAssets",
      requestType = comdex.asset.v1beta1.Querier.QueryAssetsRequest.class,
      responseType = comdex.asset.v1beta1.Querier.QueryAssetsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.asset.v1beta1.Querier.QueryAssetsRequest,
      comdex.asset.v1beta1.Querier.QueryAssetsResponse> getQueryAssetsMethod() {
    io.grpc.MethodDescriptor<comdex.asset.v1beta1.Querier.QueryAssetsRequest, comdex.asset.v1beta1.Querier.QueryAssetsResponse> getQueryAssetsMethod;
    if ((getQueryAssetsMethod = QueryServiceGrpc.getQueryAssetsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryAssetsMethod = QueryServiceGrpc.getQueryAssetsMethod) == null) {
          QueryServiceGrpc.getQueryAssetsMethod = getQueryAssetsMethod =
              io.grpc.MethodDescriptor.<comdex.asset.v1beta1.Querier.QueryAssetsRequest, comdex.asset.v1beta1.Querier.QueryAssetsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryAssets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Querier.QueryAssetsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Querier.QueryAssetsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryAssets"))
              .build();
        }
      }
    }
    return getQueryAssetsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.asset.v1beta1.Querier.QueryAssetRequest,
      comdex.asset.v1beta1.Querier.QueryAssetResponse> getQueryAssetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryAsset",
      requestType = comdex.asset.v1beta1.Querier.QueryAssetRequest.class,
      responseType = comdex.asset.v1beta1.Querier.QueryAssetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.asset.v1beta1.Querier.QueryAssetRequest,
      comdex.asset.v1beta1.Querier.QueryAssetResponse> getQueryAssetMethod() {
    io.grpc.MethodDescriptor<comdex.asset.v1beta1.Querier.QueryAssetRequest, comdex.asset.v1beta1.Querier.QueryAssetResponse> getQueryAssetMethod;
    if ((getQueryAssetMethod = QueryServiceGrpc.getQueryAssetMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryAssetMethod = QueryServiceGrpc.getQueryAssetMethod) == null) {
          QueryServiceGrpc.getQueryAssetMethod = getQueryAssetMethod =
              io.grpc.MethodDescriptor.<comdex.asset.v1beta1.Querier.QueryAssetRequest, comdex.asset.v1beta1.Querier.QueryAssetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryAsset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Querier.QueryAssetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Querier.QueryAssetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryAsset"))
              .build();
        }
      }
    }
    return getQueryAssetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.asset.v1beta1.Querier.QueryPairsRequest,
      comdex.asset.v1beta1.Querier.QueryPairsResponse> getQueryPairsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryPairs",
      requestType = comdex.asset.v1beta1.Querier.QueryPairsRequest.class,
      responseType = comdex.asset.v1beta1.Querier.QueryPairsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.asset.v1beta1.Querier.QueryPairsRequest,
      comdex.asset.v1beta1.Querier.QueryPairsResponse> getQueryPairsMethod() {
    io.grpc.MethodDescriptor<comdex.asset.v1beta1.Querier.QueryPairsRequest, comdex.asset.v1beta1.Querier.QueryPairsResponse> getQueryPairsMethod;
    if ((getQueryPairsMethod = QueryServiceGrpc.getQueryPairsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryPairsMethod = QueryServiceGrpc.getQueryPairsMethod) == null) {
          QueryServiceGrpc.getQueryPairsMethod = getQueryPairsMethod =
              io.grpc.MethodDescriptor.<comdex.asset.v1beta1.Querier.QueryPairsRequest, comdex.asset.v1beta1.Querier.QueryPairsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryPairs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Querier.QueryPairsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Querier.QueryPairsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryPairs"))
              .build();
        }
      }
    }
    return getQueryPairsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.asset.v1beta1.Querier.QueryPairRequest,
      comdex.asset.v1beta1.Querier.QueryPairResponse> getQueryPairMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryPair",
      requestType = comdex.asset.v1beta1.Querier.QueryPairRequest.class,
      responseType = comdex.asset.v1beta1.Querier.QueryPairResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.asset.v1beta1.Querier.QueryPairRequest,
      comdex.asset.v1beta1.Querier.QueryPairResponse> getQueryPairMethod() {
    io.grpc.MethodDescriptor<comdex.asset.v1beta1.Querier.QueryPairRequest, comdex.asset.v1beta1.Querier.QueryPairResponse> getQueryPairMethod;
    if ((getQueryPairMethod = QueryServiceGrpc.getQueryPairMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryPairMethod = QueryServiceGrpc.getQueryPairMethod) == null) {
          QueryServiceGrpc.getQueryPairMethod = getQueryPairMethod =
              io.grpc.MethodDescriptor.<comdex.asset.v1beta1.Querier.QueryPairRequest, comdex.asset.v1beta1.Querier.QueryPairResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryPair"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Querier.QueryPairRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Querier.QueryPairResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryPair"))
              .build();
        }
      }
    }
    return getQueryPairMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.asset.v1beta1.Querier.QueryParamsRequest,
      comdex.asset.v1beta1.Querier.QueryParamsResponse> getQueryParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryParams",
      requestType = comdex.asset.v1beta1.Querier.QueryParamsRequest.class,
      responseType = comdex.asset.v1beta1.Querier.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.asset.v1beta1.Querier.QueryParamsRequest,
      comdex.asset.v1beta1.Querier.QueryParamsResponse> getQueryParamsMethod() {
    io.grpc.MethodDescriptor<comdex.asset.v1beta1.Querier.QueryParamsRequest, comdex.asset.v1beta1.Querier.QueryParamsResponse> getQueryParamsMethod;
    if ((getQueryParamsMethod = QueryServiceGrpc.getQueryParamsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryParamsMethod = QueryServiceGrpc.getQueryParamsMethod) == null) {
          QueryServiceGrpc.getQueryParamsMethod = getQueryParamsMethod =
              io.grpc.MethodDescriptor.<comdex.asset.v1beta1.Querier.QueryParamsRequest, comdex.asset.v1beta1.Querier.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Querier.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Querier.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryParams"))
              .build();
        }
      }
    }
    return getQueryParamsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QueryServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceStub>() {
        @java.lang.Override
        public QueryServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceStub(channel, callOptions);
        }
      };
    return QueryServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QueryServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceBlockingStub>() {
        @java.lang.Override
        public QueryServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceBlockingStub(channel, callOptions);
        }
      };
    return QueryServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QueryServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceFutureStub>() {
        @java.lang.Override
        public QueryServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceFutureStub(channel, callOptions);
        }
      };
    return QueryServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class QueryServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void queryAssets(comdex.asset.v1beta1.Querier.QueryAssetsRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Querier.QueryAssetsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryAssetsMethod(), responseObserver);
    }

    /**
     */
    public void queryAsset(comdex.asset.v1beta1.Querier.QueryAssetRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Querier.QueryAssetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryAssetMethod(), responseObserver);
    }

    /**
     */
    public void queryPairs(comdex.asset.v1beta1.Querier.QueryPairsRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Querier.QueryPairsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryPairsMethod(), responseObserver);
    }

    /**
     */
    public void queryPair(comdex.asset.v1beta1.Querier.QueryPairRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Querier.QueryPairResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryPairMethod(), responseObserver);
    }

    /**
     */
    public void queryParams(comdex.asset.v1beta1.Querier.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Querier.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getQueryAssetsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.asset.v1beta1.Querier.QueryAssetsRequest,
                comdex.asset.v1beta1.Querier.QueryAssetsResponse>(
                  this, METHODID_QUERY_ASSETS)))
          .addMethod(
            getQueryAssetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.asset.v1beta1.Querier.QueryAssetRequest,
                comdex.asset.v1beta1.Querier.QueryAssetResponse>(
                  this, METHODID_QUERY_ASSET)))
          .addMethod(
            getQueryPairsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.asset.v1beta1.Querier.QueryPairsRequest,
                comdex.asset.v1beta1.Querier.QueryPairsResponse>(
                  this, METHODID_QUERY_PAIRS)))
          .addMethod(
            getQueryPairMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.asset.v1beta1.Querier.QueryPairRequest,
                comdex.asset.v1beta1.Querier.QueryPairResponse>(
                  this, METHODID_QUERY_PAIR)))
          .addMethod(
            getQueryParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.asset.v1beta1.Querier.QueryParamsRequest,
                comdex.asset.v1beta1.Querier.QueryParamsResponse>(
                  this, METHODID_QUERY_PARAMS)))
          .build();
    }
  }

  /**
   */
  public static final class QueryServiceStub extends io.grpc.stub.AbstractAsyncStub<QueryServiceStub> {
    private QueryServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceStub(channel, callOptions);
    }

    /**
     */
    public void queryAssets(comdex.asset.v1beta1.Querier.QueryAssetsRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Querier.QueryAssetsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryAssetsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryAsset(comdex.asset.v1beta1.Querier.QueryAssetRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Querier.QueryAssetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryAssetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryPairs(comdex.asset.v1beta1.Querier.QueryPairsRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Querier.QueryPairsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryPairsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryPair(comdex.asset.v1beta1.Querier.QueryPairRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Querier.QueryPairResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryPairMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryParams(comdex.asset.v1beta1.Querier.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Querier.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class QueryServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<QueryServiceBlockingStub> {
    private QueryServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public comdex.asset.v1beta1.Querier.QueryAssetsResponse queryAssets(comdex.asset.v1beta1.Querier.QueryAssetsRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryAssetsMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.asset.v1beta1.Querier.QueryAssetResponse queryAsset(comdex.asset.v1beta1.Querier.QueryAssetRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryAssetMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.asset.v1beta1.Querier.QueryPairsResponse queryPairs(comdex.asset.v1beta1.Querier.QueryPairsRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryPairsMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.asset.v1beta1.Querier.QueryPairResponse queryPair(comdex.asset.v1beta1.Querier.QueryPairRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryPairMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.asset.v1beta1.Querier.QueryParamsResponse queryParams(comdex.asset.v1beta1.Querier.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class QueryServiceFutureStub extends io.grpc.stub.AbstractFutureStub<QueryServiceFutureStub> {
    private QueryServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.asset.v1beta1.Querier.QueryAssetsResponse> queryAssets(
        comdex.asset.v1beta1.Querier.QueryAssetsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryAssetsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.asset.v1beta1.Querier.QueryAssetResponse> queryAsset(
        comdex.asset.v1beta1.Querier.QueryAssetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryAssetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.asset.v1beta1.Querier.QueryPairsResponse> queryPairs(
        comdex.asset.v1beta1.Querier.QueryPairsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryPairsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.asset.v1beta1.Querier.QueryPairResponse> queryPair(
        comdex.asset.v1beta1.Querier.QueryPairRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryPairMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.asset.v1beta1.Querier.QueryParamsResponse> queryParams(
        comdex.asset.v1beta1.Querier.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_ASSETS = 0;
  private static final int METHODID_QUERY_ASSET = 1;
  private static final int METHODID_QUERY_PAIRS = 2;
  private static final int METHODID_QUERY_PAIR = 3;
  private static final int METHODID_QUERY_PARAMS = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final QueryServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(QueryServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_QUERY_ASSETS:
          serviceImpl.queryAssets((comdex.asset.v1beta1.Querier.QueryAssetsRequest) request,
              (io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Querier.QueryAssetsResponse>) responseObserver);
          break;
        case METHODID_QUERY_ASSET:
          serviceImpl.queryAsset((comdex.asset.v1beta1.Querier.QueryAssetRequest) request,
              (io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Querier.QueryAssetResponse>) responseObserver);
          break;
        case METHODID_QUERY_PAIRS:
          serviceImpl.queryPairs((comdex.asset.v1beta1.Querier.QueryPairsRequest) request,
              (io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Querier.QueryPairsResponse>) responseObserver);
          break;
        case METHODID_QUERY_PAIR:
          serviceImpl.queryPair((comdex.asset.v1beta1.Querier.QueryPairRequest) request,
              (io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Querier.QueryPairResponse>) responseObserver);
          break;
        case METHODID_QUERY_PARAMS:
          serviceImpl.queryParams((comdex.asset.v1beta1.Querier.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Querier.QueryParamsResponse>) responseObserver);
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

  private static abstract class QueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return comdex.asset.v1beta1.Querier.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("QueryService");
    }
  }

  private static final class QueryServiceFileDescriptorSupplier
      extends QueryServiceBaseDescriptorSupplier {
    QueryServiceFileDescriptorSupplier() {}
  }

  private static final class QueryServiceMethodDescriptorSupplier
      extends QueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    QueryServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (QueryServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QueryServiceFileDescriptorSupplier())
              .addMethod(getQueryAssetsMethod())
              .addMethod(getQueryAssetMethod())
              .addMethod(getQueryPairsMethod())
              .addMethod(getQueryPairMethod())
              .addMethod(getQueryParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
