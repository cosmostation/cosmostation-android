package publicawesome.stargaze.claim.v1beta1;

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
    comments = "Source: stargaze/claim/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "publicawesome.stargaze.claim.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceRequest,
      publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceResponse> getModuleAccountBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ModuleAccountBalance",
      requestType = publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceRequest.class,
      responseType = publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceRequest,
      publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceResponse> getModuleAccountBalanceMethod() {
    io.grpc.MethodDescriptor<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceRequest, publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceResponse> getModuleAccountBalanceMethod;
    if ((getModuleAccountBalanceMethod = QueryGrpc.getModuleAccountBalanceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getModuleAccountBalanceMethod = QueryGrpc.getModuleAccountBalanceMethod) == null) {
          QueryGrpc.getModuleAccountBalanceMethod = getModuleAccountBalanceMethod =
              io.grpc.MethodDescriptor.<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceRequest, publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ModuleAccountBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ModuleAccountBalance"))
              .build();
        }
      }
    }
    return getModuleAccountBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsRequest,
      publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsRequest,
      publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsRequest, publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsRequest, publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest,
      publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse> getClaimRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimRecord",
      requestType = publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest.class,
      responseType = publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest,
      publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse> getClaimRecordMethod() {
    io.grpc.MethodDescriptor<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest, publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse> getClaimRecordMethod;
    if ((getClaimRecordMethod = QueryGrpc.getClaimRecordMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClaimRecordMethod = QueryGrpc.getClaimRecordMethod) == null) {
          QueryGrpc.getClaimRecordMethod = getClaimRecordMethod =
              io.grpc.MethodDescriptor.<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest, publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClaimRecord"))
              .build();
        }
      }
    }
    return getClaimRecordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionRequest,
      publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionResponse> getClaimableForActionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimableForAction",
      requestType = publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionRequest.class,
      responseType = publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionRequest,
      publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionResponse> getClaimableForActionMethod() {
    io.grpc.MethodDescriptor<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionRequest, publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionResponse> getClaimableForActionMethod;
    if ((getClaimableForActionMethod = QueryGrpc.getClaimableForActionMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClaimableForActionMethod = QueryGrpc.getClaimableForActionMethod) == null) {
          QueryGrpc.getClaimableForActionMethod = getClaimableForActionMethod =
              io.grpc.MethodDescriptor.<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionRequest, publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimableForAction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClaimableForAction"))
              .build();
        }
      }
    }
    return getClaimableForActionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableRequest,
      publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableResponse> getTotalClaimableMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalClaimable",
      requestType = publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableRequest.class,
      responseType = publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableRequest,
      publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableResponse> getTotalClaimableMethod() {
    io.grpc.MethodDescriptor<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableRequest, publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableResponse> getTotalClaimableMethod;
    if ((getTotalClaimableMethod = QueryGrpc.getTotalClaimableMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalClaimableMethod = QueryGrpc.getTotalClaimableMethod) == null) {
          QueryGrpc.getTotalClaimableMethod = getTotalClaimableMethod =
              io.grpc.MethodDescriptor.<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableRequest, publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalClaimable"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalClaimable"))
              .build();
        }
      }
    }
    return getTotalClaimableMethod;
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
     * <pre>
     * this line is used by starport scaffolding # 2
     * </pre>
     */
    public void moduleAccountBalance(publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceRequest request,
        io.grpc.stub.StreamObserver<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getModuleAccountBalanceMethod(), responseObserver);
    }

    /**
     */
    public void params(publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     */
    public void claimRecord(publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest request,
        io.grpc.stub.StreamObserver<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClaimRecordMethod(), responseObserver);
    }

    /**
     */
    public void claimableForAction(publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionRequest request,
        io.grpc.stub.StreamObserver<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClaimableForActionMethod(), responseObserver);
    }

    /**
     */
    public void totalClaimable(publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableRequest request,
        io.grpc.stub.StreamObserver<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTotalClaimableMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getModuleAccountBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceRequest,
                publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceResponse>(
                  this, METHODID_MODULE_ACCOUNT_BALANCE)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsRequest,
                publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getClaimRecordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest,
                publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse>(
                  this, METHODID_CLAIM_RECORD)))
          .addMethod(
            getClaimableForActionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionRequest,
                publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionResponse>(
                  this, METHODID_CLAIMABLE_FOR_ACTION)))
          .addMethod(
            getTotalClaimableMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableRequest,
                publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableResponse>(
                  this, METHODID_TOTAL_CLAIMABLE)))
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
     * <pre>
     * this line is used by starport scaffolding # 2
     * </pre>
     */
    public void moduleAccountBalance(publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceRequest request,
        io.grpc.stub.StreamObserver<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getModuleAccountBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void params(publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void claimRecord(publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest request,
        io.grpc.stub.StreamObserver<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClaimRecordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void claimableForAction(publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionRequest request,
        io.grpc.stub.StreamObserver<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClaimableForActionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void totalClaimable(publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableRequest request,
        io.grpc.stub.StreamObserver<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTotalClaimableMethod(), getCallOptions()), request, responseObserver);
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
     * <pre>
     * this line is used by starport scaffolding # 2
     * </pre>
     */
    public publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceResponse moduleAccountBalance(publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceRequest request) {
      return blockingUnaryCall(
          getChannel(), getModuleAccountBalanceMethod(), getCallOptions(), request);
    }

    /**
     */
    public publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsResponse params(publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse claimRecord(publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest request) {
      return blockingUnaryCall(
          getChannel(), getClaimRecordMethod(), getCallOptions(), request);
    }

    /**
     */
    public publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionResponse claimableForAction(publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionRequest request) {
      return blockingUnaryCall(
          getChannel(), getClaimableForActionMethod(), getCallOptions(), request);
    }

    /**
     */
    public publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableResponse totalClaimable(publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableRequest request) {
      return blockingUnaryCall(
          getChannel(), getTotalClaimableMethod(), getCallOptions(), request);
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
     * <pre>
     * this line is used by starport scaffolding # 2
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceResponse> moduleAccountBalance(
        publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getModuleAccountBalanceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse> claimRecord(
        publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getClaimRecordMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionResponse> claimableForAction(
        publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getClaimableForActionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableResponse> totalClaimable(
        publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTotalClaimableMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MODULE_ACCOUNT_BALANCE = 0;
  private static final int METHODID_PARAMS = 1;
  private static final int METHODID_CLAIM_RECORD = 2;
  private static final int METHODID_CLAIMABLE_FOR_ACTION = 3;
  private static final int METHODID_TOTAL_CLAIMABLE = 4;

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
        case METHODID_MODULE_ACCOUNT_BALANCE:
          serviceImpl.moduleAccountBalance((publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceRequest) request,
              (io.grpc.stub.StreamObserver<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryModuleAccountBalanceResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_CLAIM_RECORD:
          serviceImpl.claimRecord((publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest) request,
              (io.grpc.stub.StreamObserver<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse>) responseObserver);
          break;
        case METHODID_CLAIMABLE_FOR_ACTION:
          serviceImpl.claimableForAction((publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionRequest) request,
              (io.grpc.stub.StreamObserver<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryClaimableForActionResponse>) responseObserver);
          break;
        case METHODID_TOTAL_CLAIMABLE:
          serviceImpl.totalClaimable((publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableRequest) request,
              (io.grpc.stub.StreamObserver<publicawesome.stargaze.claim.v1beta1.QueryOuterClass.QueryTotalClaimableResponse>) responseObserver);
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
      return publicawesome.stargaze.claim.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getModuleAccountBalanceMethod())
              .addMethod(getParamsMethod())
              .addMethod(getClaimRecordMethod())
              .addMethod(getClaimableForActionMethod())
              .addMethod(getTotalClaimableMethod())
              .build();
        }
      }
    }
    return result;
  }
}
