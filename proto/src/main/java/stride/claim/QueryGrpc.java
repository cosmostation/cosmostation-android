package stride.claim;

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
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: stride/claim/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "stride.claim.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryDistributorAccountBalanceRequest,
      stride.claim.QueryOuterClass.QueryDistributorAccountBalanceResponse> getDistributorAccountBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DistributorAccountBalance",
      requestType = stride.claim.QueryOuterClass.QueryDistributorAccountBalanceRequest.class,
      responseType = stride.claim.QueryOuterClass.QueryDistributorAccountBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryDistributorAccountBalanceRequest,
      stride.claim.QueryOuterClass.QueryDistributorAccountBalanceResponse> getDistributorAccountBalanceMethod() {
    io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryDistributorAccountBalanceRequest, stride.claim.QueryOuterClass.QueryDistributorAccountBalanceResponse> getDistributorAccountBalanceMethod;
    if ((getDistributorAccountBalanceMethod = QueryGrpc.getDistributorAccountBalanceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDistributorAccountBalanceMethod = QueryGrpc.getDistributorAccountBalanceMethod) == null) {
          QueryGrpc.getDistributorAccountBalanceMethod = getDistributorAccountBalanceMethod =
              io.grpc.MethodDescriptor.<stride.claim.QueryOuterClass.QueryDistributorAccountBalanceRequest, stride.claim.QueryOuterClass.QueryDistributorAccountBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DistributorAccountBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.QueryOuterClass.QueryDistributorAccountBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.QueryOuterClass.QueryDistributorAccountBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DistributorAccountBalance"))
              .build();
        }
      }
    }
    return getDistributorAccountBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryParamsRequest,
      stride.claim.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = stride.claim.QueryOuterClass.QueryParamsRequest.class,
      responseType = stride.claim.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryParamsRequest,
      stride.claim.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryParamsRequest, stride.claim.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<stride.claim.QueryOuterClass.QueryParamsRequest, stride.claim.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryClaimRecordRequest,
      stride.claim.QueryOuterClass.QueryClaimRecordResponse> getClaimRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimRecord",
      requestType = stride.claim.QueryOuterClass.QueryClaimRecordRequest.class,
      responseType = stride.claim.QueryOuterClass.QueryClaimRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryClaimRecordRequest,
      stride.claim.QueryOuterClass.QueryClaimRecordResponse> getClaimRecordMethod() {
    io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryClaimRecordRequest, stride.claim.QueryOuterClass.QueryClaimRecordResponse> getClaimRecordMethod;
    if ((getClaimRecordMethod = QueryGrpc.getClaimRecordMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClaimRecordMethod = QueryGrpc.getClaimRecordMethod) == null) {
          QueryGrpc.getClaimRecordMethod = getClaimRecordMethod =
              io.grpc.MethodDescriptor.<stride.claim.QueryOuterClass.QueryClaimRecordRequest, stride.claim.QueryOuterClass.QueryClaimRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.QueryOuterClass.QueryClaimRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.QueryOuterClass.QueryClaimRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClaimRecord"))
              .build();
        }
      }
    }
    return getClaimRecordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryClaimableForActionRequest,
      stride.claim.QueryOuterClass.QueryClaimableForActionResponse> getClaimableForActionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimableForAction",
      requestType = stride.claim.QueryOuterClass.QueryClaimableForActionRequest.class,
      responseType = stride.claim.QueryOuterClass.QueryClaimableForActionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryClaimableForActionRequest,
      stride.claim.QueryOuterClass.QueryClaimableForActionResponse> getClaimableForActionMethod() {
    io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryClaimableForActionRequest, stride.claim.QueryOuterClass.QueryClaimableForActionResponse> getClaimableForActionMethod;
    if ((getClaimableForActionMethod = QueryGrpc.getClaimableForActionMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClaimableForActionMethod = QueryGrpc.getClaimableForActionMethod) == null) {
          QueryGrpc.getClaimableForActionMethod = getClaimableForActionMethod =
              io.grpc.MethodDescriptor.<stride.claim.QueryOuterClass.QueryClaimableForActionRequest, stride.claim.QueryOuterClass.QueryClaimableForActionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimableForAction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.QueryOuterClass.QueryClaimableForActionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.QueryOuterClass.QueryClaimableForActionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClaimableForAction"))
              .build();
        }
      }
    }
    return getClaimableForActionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryTotalClaimableRequest,
      stride.claim.QueryOuterClass.QueryTotalClaimableResponse> getTotalClaimableMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalClaimable",
      requestType = stride.claim.QueryOuterClass.QueryTotalClaimableRequest.class,
      responseType = stride.claim.QueryOuterClass.QueryTotalClaimableResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryTotalClaimableRequest,
      stride.claim.QueryOuterClass.QueryTotalClaimableResponse> getTotalClaimableMethod() {
    io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryTotalClaimableRequest, stride.claim.QueryOuterClass.QueryTotalClaimableResponse> getTotalClaimableMethod;
    if ((getTotalClaimableMethod = QueryGrpc.getTotalClaimableMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalClaimableMethod = QueryGrpc.getTotalClaimableMethod) == null) {
          QueryGrpc.getTotalClaimableMethod = getTotalClaimableMethod =
              io.grpc.MethodDescriptor.<stride.claim.QueryOuterClass.QueryTotalClaimableRequest, stride.claim.QueryOuterClass.QueryTotalClaimableResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalClaimable"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.QueryOuterClass.QueryTotalClaimableRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.QueryOuterClass.QueryTotalClaimableResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalClaimable"))
              .build();
        }
      }
    }
    return getTotalClaimableMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryUserVestingsRequest,
      stride.claim.QueryOuterClass.QueryUserVestingsResponse> getUserVestingsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UserVestings",
      requestType = stride.claim.QueryOuterClass.QueryUserVestingsRequest.class,
      responseType = stride.claim.QueryOuterClass.QueryUserVestingsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryUserVestingsRequest,
      stride.claim.QueryOuterClass.QueryUserVestingsResponse> getUserVestingsMethod() {
    io.grpc.MethodDescriptor<stride.claim.QueryOuterClass.QueryUserVestingsRequest, stride.claim.QueryOuterClass.QueryUserVestingsResponse> getUserVestingsMethod;
    if ((getUserVestingsMethod = QueryGrpc.getUserVestingsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUserVestingsMethod = QueryGrpc.getUserVestingsMethod) == null) {
          QueryGrpc.getUserVestingsMethod = getUserVestingsMethod =
              io.grpc.MethodDescriptor.<stride.claim.QueryOuterClass.QueryUserVestingsRequest, stride.claim.QueryOuterClass.QueryUserVestingsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UserVestings"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.QueryOuterClass.QueryUserVestingsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.QueryOuterClass.QueryUserVestingsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UserVestings"))
              .build();
        }
      }
    }
    return getUserVestingsMethod;
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
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     */
    public void distributorAccountBalance(stride.claim.QueryOuterClass.QueryDistributorAccountBalanceRequest request,
        io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryDistributorAccountBalanceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDistributorAccountBalanceMethod(), responseObserver);
    }

    /**
     */
    public void params(stride.claim.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     */
    public void claimRecord(stride.claim.QueryOuterClass.QueryClaimRecordRequest request,
        io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryClaimRecordResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClaimRecordMethod(), responseObserver);
    }

    /**
     */
    public void claimableForAction(stride.claim.QueryOuterClass.QueryClaimableForActionRequest request,
        io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryClaimableForActionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClaimableForActionMethod(), responseObserver);
    }

    /**
     */
    public void totalClaimable(stride.claim.QueryOuterClass.QueryTotalClaimableRequest request,
        io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryTotalClaimableResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTotalClaimableMethod(), responseObserver);
    }

    /**
     */
    public void userVestings(stride.claim.QueryOuterClass.QueryUserVestingsRequest request,
        io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryUserVestingsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUserVestingsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDistributorAccountBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.claim.QueryOuterClass.QueryDistributorAccountBalanceRequest,
                stride.claim.QueryOuterClass.QueryDistributorAccountBalanceResponse>(
                  this, METHODID_DISTRIBUTOR_ACCOUNT_BALANCE)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.claim.QueryOuterClass.QueryParamsRequest,
                stride.claim.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getClaimRecordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.claim.QueryOuterClass.QueryClaimRecordRequest,
                stride.claim.QueryOuterClass.QueryClaimRecordResponse>(
                  this, METHODID_CLAIM_RECORD)))
          .addMethod(
            getClaimableForActionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.claim.QueryOuterClass.QueryClaimableForActionRequest,
                stride.claim.QueryOuterClass.QueryClaimableForActionResponse>(
                  this, METHODID_CLAIMABLE_FOR_ACTION)))
          .addMethod(
            getTotalClaimableMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.claim.QueryOuterClass.QueryTotalClaimableRequest,
                stride.claim.QueryOuterClass.QueryTotalClaimableResponse>(
                  this, METHODID_TOTAL_CLAIMABLE)))
          .addMethod(
            getUserVestingsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.claim.QueryOuterClass.QueryUserVestingsRequest,
                stride.claim.QueryOuterClass.QueryUserVestingsResponse>(
                  this, METHODID_USER_VESTINGS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
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
    public void distributorAccountBalance(stride.claim.QueryOuterClass.QueryDistributorAccountBalanceRequest request,
        io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryDistributorAccountBalanceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDistributorAccountBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void params(stride.claim.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void claimRecord(stride.claim.QueryOuterClass.QueryClaimRecordRequest request,
        io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryClaimRecordResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClaimRecordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void claimableForAction(stride.claim.QueryOuterClass.QueryClaimableForActionRequest request,
        io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryClaimableForActionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClaimableForActionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void totalClaimable(stride.claim.QueryOuterClass.QueryTotalClaimableRequest request,
        io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryTotalClaimableResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTotalClaimableMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void userVestings(stride.claim.QueryOuterClass.QueryUserVestingsRequest request,
        io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryUserVestingsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUserVestingsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
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
    public stride.claim.QueryOuterClass.QueryDistributorAccountBalanceResponse distributorAccountBalance(stride.claim.QueryOuterClass.QueryDistributorAccountBalanceRequest request) {
      return blockingUnaryCall(
          getChannel(), getDistributorAccountBalanceMethod(), getCallOptions(), request);
    }

    /**
     */
    public stride.claim.QueryOuterClass.QueryParamsResponse params(stride.claim.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public stride.claim.QueryOuterClass.QueryClaimRecordResponse claimRecord(stride.claim.QueryOuterClass.QueryClaimRecordRequest request) {
      return blockingUnaryCall(
          getChannel(), getClaimRecordMethod(), getCallOptions(), request);
    }

    /**
     */
    public stride.claim.QueryOuterClass.QueryClaimableForActionResponse claimableForAction(stride.claim.QueryOuterClass.QueryClaimableForActionRequest request) {
      return blockingUnaryCall(
          getChannel(), getClaimableForActionMethod(), getCallOptions(), request);
    }

    /**
     */
    public stride.claim.QueryOuterClass.QueryTotalClaimableResponse totalClaimable(stride.claim.QueryOuterClass.QueryTotalClaimableRequest request) {
      return blockingUnaryCall(
          getChannel(), getTotalClaimableMethod(), getCallOptions(), request);
    }

    /**
     */
    public stride.claim.QueryOuterClass.QueryUserVestingsResponse userVestings(stride.claim.QueryOuterClass.QueryUserVestingsRequest request) {
      return blockingUnaryCall(
          getChannel(), getUserVestingsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
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
    public com.google.common.util.concurrent.ListenableFuture<stride.claim.QueryOuterClass.QueryDistributorAccountBalanceResponse> distributorAccountBalance(
        stride.claim.QueryOuterClass.QueryDistributorAccountBalanceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDistributorAccountBalanceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.claim.QueryOuterClass.QueryParamsResponse> params(
        stride.claim.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.claim.QueryOuterClass.QueryClaimRecordResponse> claimRecord(
        stride.claim.QueryOuterClass.QueryClaimRecordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getClaimRecordMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.claim.QueryOuterClass.QueryClaimableForActionResponse> claimableForAction(
        stride.claim.QueryOuterClass.QueryClaimableForActionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getClaimableForActionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.claim.QueryOuterClass.QueryTotalClaimableResponse> totalClaimable(
        stride.claim.QueryOuterClass.QueryTotalClaimableRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTotalClaimableMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.claim.QueryOuterClass.QueryUserVestingsResponse> userVestings(
        stride.claim.QueryOuterClass.QueryUserVestingsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUserVestingsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DISTRIBUTOR_ACCOUNT_BALANCE = 0;
  private static final int METHODID_PARAMS = 1;
  private static final int METHODID_CLAIM_RECORD = 2;
  private static final int METHODID_CLAIMABLE_FOR_ACTION = 3;
  private static final int METHODID_TOTAL_CLAIMABLE = 4;
  private static final int METHODID_USER_VESTINGS = 5;

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
        case METHODID_DISTRIBUTOR_ACCOUNT_BALANCE:
          serviceImpl.distributorAccountBalance((stride.claim.QueryOuterClass.QueryDistributorAccountBalanceRequest) request,
              (io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryDistributorAccountBalanceResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((stride.claim.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_CLAIM_RECORD:
          serviceImpl.claimRecord((stride.claim.QueryOuterClass.QueryClaimRecordRequest) request,
              (io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryClaimRecordResponse>) responseObserver);
          break;
        case METHODID_CLAIMABLE_FOR_ACTION:
          serviceImpl.claimableForAction((stride.claim.QueryOuterClass.QueryClaimableForActionRequest) request,
              (io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryClaimableForActionResponse>) responseObserver);
          break;
        case METHODID_TOTAL_CLAIMABLE:
          serviceImpl.totalClaimable((stride.claim.QueryOuterClass.QueryTotalClaimableRequest) request,
              (io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryTotalClaimableResponse>) responseObserver);
          break;
        case METHODID_USER_VESTINGS:
          serviceImpl.userVestings((stride.claim.QueryOuterClass.QueryUserVestingsRequest) request,
              (io.grpc.stub.StreamObserver<stride.claim.QueryOuterClass.QueryUserVestingsResponse>) responseObserver);
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
      return stride.claim.QueryOuterClass.getDescriptor();
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
              .addMethod(getDistributorAccountBalanceMethod())
              .addMethod(getParamsMethod())
              .addMethod(getClaimRecordMethod())
              .addMethod(getClaimableForActionMethod())
              .addMethod(getTotalClaimableMethod())
              .addMethod(getUserVestingsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
