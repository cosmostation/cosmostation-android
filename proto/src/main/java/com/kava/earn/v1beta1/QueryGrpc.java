package com.kava.earn.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service for earn module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: kava/earn/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "kava.earn.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kava.earn.v1beta1.QueryProto.QueryParamsRequest,
      com.kava.earn.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.kava.earn.v1beta1.QueryProto.QueryParamsRequest.class,
      responseType = com.kava.earn.v1beta1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.earn.v1beta1.QueryProto.QueryParamsRequest,
      com.kava.earn.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.kava.earn.v1beta1.QueryProto.QueryParamsRequest, com.kava.earn.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.kava.earn.v1beta1.QueryProto.QueryParamsRequest, com.kava.earn.v1beta1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.earn.v1beta1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.earn.v1beta1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.earn.v1beta1.QueryProto.QueryVaultsRequest,
      com.kava.earn.v1beta1.QueryProto.QueryVaultsResponse> getVaultsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Vaults",
      requestType = com.kava.earn.v1beta1.QueryProto.QueryVaultsRequest.class,
      responseType = com.kava.earn.v1beta1.QueryProto.QueryVaultsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.earn.v1beta1.QueryProto.QueryVaultsRequest,
      com.kava.earn.v1beta1.QueryProto.QueryVaultsResponse> getVaultsMethod() {
    io.grpc.MethodDescriptor<com.kava.earn.v1beta1.QueryProto.QueryVaultsRequest, com.kava.earn.v1beta1.QueryProto.QueryVaultsResponse> getVaultsMethod;
    if ((getVaultsMethod = QueryGrpc.getVaultsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getVaultsMethod = QueryGrpc.getVaultsMethod) == null) {
          QueryGrpc.getVaultsMethod = getVaultsMethod =
              io.grpc.MethodDescriptor.<com.kava.earn.v1beta1.QueryProto.QueryVaultsRequest, com.kava.earn.v1beta1.QueryProto.QueryVaultsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Vaults"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.earn.v1beta1.QueryProto.QueryVaultsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.earn.v1beta1.QueryProto.QueryVaultsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Vaults"))
              .build();
        }
      }
    }
    return getVaultsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.earn.v1beta1.QueryProto.QueryVaultRequest,
      com.kava.earn.v1beta1.QueryProto.QueryVaultResponse> getVaultMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Vault",
      requestType = com.kava.earn.v1beta1.QueryProto.QueryVaultRequest.class,
      responseType = com.kava.earn.v1beta1.QueryProto.QueryVaultResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.earn.v1beta1.QueryProto.QueryVaultRequest,
      com.kava.earn.v1beta1.QueryProto.QueryVaultResponse> getVaultMethod() {
    io.grpc.MethodDescriptor<com.kava.earn.v1beta1.QueryProto.QueryVaultRequest, com.kava.earn.v1beta1.QueryProto.QueryVaultResponse> getVaultMethod;
    if ((getVaultMethod = QueryGrpc.getVaultMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getVaultMethod = QueryGrpc.getVaultMethod) == null) {
          QueryGrpc.getVaultMethod = getVaultMethod =
              io.grpc.MethodDescriptor.<com.kava.earn.v1beta1.QueryProto.QueryVaultRequest, com.kava.earn.v1beta1.QueryProto.QueryVaultResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Vault"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.earn.v1beta1.QueryProto.QueryVaultRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.earn.v1beta1.QueryProto.QueryVaultResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Vault"))
              .build();
        }
      }
    }
    return getVaultMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.earn.v1beta1.QueryProto.QueryDepositsRequest,
      com.kava.earn.v1beta1.QueryProto.QueryDepositsResponse> getDepositsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposits",
      requestType = com.kava.earn.v1beta1.QueryProto.QueryDepositsRequest.class,
      responseType = com.kava.earn.v1beta1.QueryProto.QueryDepositsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.earn.v1beta1.QueryProto.QueryDepositsRequest,
      com.kava.earn.v1beta1.QueryProto.QueryDepositsResponse> getDepositsMethod() {
    io.grpc.MethodDescriptor<com.kava.earn.v1beta1.QueryProto.QueryDepositsRequest, com.kava.earn.v1beta1.QueryProto.QueryDepositsResponse> getDepositsMethod;
    if ((getDepositsMethod = QueryGrpc.getDepositsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositsMethod = QueryGrpc.getDepositsMethod) == null) {
          QueryGrpc.getDepositsMethod = getDepositsMethod =
              io.grpc.MethodDescriptor.<com.kava.earn.v1beta1.QueryProto.QueryDepositsRequest, com.kava.earn.v1beta1.QueryProto.QueryDepositsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposits"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.earn.v1beta1.QueryProto.QueryDepositsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.earn.v1beta1.QueryProto.QueryDepositsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Deposits"))
              .build();
        }
      }
    }
    return getDepositsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyRequest,
      com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyResponse> getTotalSupplyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalSupply",
      requestType = com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyRequest.class,
      responseType = com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyRequest,
      com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyResponse> getTotalSupplyMethod() {
    io.grpc.MethodDescriptor<com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyRequest, com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyResponse> getTotalSupplyMethod;
    if ((getTotalSupplyMethod = QueryGrpc.getTotalSupplyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalSupplyMethod = QueryGrpc.getTotalSupplyMethod) == null) {
          QueryGrpc.getTotalSupplyMethod = getTotalSupplyMethod =
              io.grpc.MethodDescriptor.<com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyRequest, com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalSupply"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalSupply"))
              .build();
        }
      }
    }
    return getTotalSupplyMethod;
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
   * Query defines the gRPC querier service for earn module
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Params queries all parameters of the earn module.
     * </pre>
     */
    default void params(com.kava.earn.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.kava.earn.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Vaults queries all vaults
     * </pre>
     */
    default void vaults(com.kava.earn.v1beta1.QueryProto.QueryVaultsRequest request,
        io.grpc.stub.StreamObserver<com.kava.earn.v1beta1.QueryProto.QueryVaultsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getVaultsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Vault queries a single vault based on the vault denom
     * </pre>
     */
    default void vault(com.kava.earn.v1beta1.QueryProto.QueryVaultRequest request,
        io.grpc.stub.StreamObserver<com.kava.earn.v1beta1.QueryProto.QueryVaultResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getVaultMethod(), responseObserver);
    }

    /**
     * <pre>
     * Deposits queries deposit details based on depositor address and vault
     * </pre>
     */
    default void deposits(com.kava.earn.v1beta1.QueryProto.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<com.kava.earn.v1beta1.QueryProto.QueryDepositsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDepositsMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalSupply returns the total sum of all coins currently locked into the earn module.
     * </pre>
     */
    default void totalSupply(com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyRequest request,
        io.grpc.stub.StreamObserver<com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTotalSupplyMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service for earn module
   * </pre>
   */
  public static abstract class QueryImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return QueryGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for earn module
   * </pre>
   */
  public static final class QueryStub
      extends io.grpc.stub.AbstractAsyncStub<QueryStub> {
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
     * Params queries all parameters of the earn module.
     * </pre>
     */
    public void params(com.kava.earn.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.kava.earn.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Vaults queries all vaults
     * </pre>
     */
    public void vaults(com.kava.earn.v1beta1.QueryProto.QueryVaultsRequest request,
        io.grpc.stub.StreamObserver<com.kava.earn.v1beta1.QueryProto.QueryVaultsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getVaultsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Vault queries a single vault based on the vault denom
     * </pre>
     */
    public void vault(com.kava.earn.v1beta1.QueryProto.QueryVaultRequest request,
        io.grpc.stub.StreamObserver<com.kava.earn.v1beta1.QueryProto.QueryVaultResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getVaultMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Deposits queries deposit details based on depositor address and vault
     * </pre>
     */
    public void deposits(com.kava.earn.v1beta1.QueryProto.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<com.kava.earn.v1beta1.QueryProto.QueryDepositsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDepositsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalSupply returns the total sum of all coins currently locked into the earn module.
     * </pre>
     */
    public void totalSupply(com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyRequest request,
        io.grpc.stub.StreamObserver<com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTotalSupplyMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for earn module
   * </pre>
   */
  public static final class QueryBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<QueryBlockingStub> {
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
     * Params queries all parameters of the earn module.
     * </pre>
     */
    public com.kava.earn.v1beta1.QueryProto.QueryParamsResponse params(com.kava.earn.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Vaults queries all vaults
     * </pre>
     */
    public com.kava.earn.v1beta1.QueryProto.QueryVaultsResponse vaults(com.kava.earn.v1beta1.QueryProto.QueryVaultsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getVaultsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Vault queries a single vault based on the vault denom
     * </pre>
     */
    public com.kava.earn.v1beta1.QueryProto.QueryVaultResponse vault(com.kava.earn.v1beta1.QueryProto.QueryVaultRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getVaultMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Deposits queries deposit details based on depositor address and vault
     * </pre>
     */
    public com.kava.earn.v1beta1.QueryProto.QueryDepositsResponse deposits(com.kava.earn.v1beta1.QueryProto.QueryDepositsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDepositsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalSupply returns the total sum of all coins currently locked into the earn module.
     * </pre>
     */
    public com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyResponse totalSupply(com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTotalSupplyMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for earn module
   * </pre>
   */
  public static final class QueryFutureStub
      extends io.grpc.stub.AbstractFutureStub<QueryFutureStub> {
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
     * Params queries all parameters of the earn module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.earn.v1beta1.QueryProto.QueryParamsResponse> params(
        com.kava.earn.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Vaults queries all vaults
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.earn.v1beta1.QueryProto.QueryVaultsResponse> vaults(
        com.kava.earn.v1beta1.QueryProto.QueryVaultsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getVaultsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Vault queries a single vault based on the vault denom
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.earn.v1beta1.QueryProto.QueryVaultResponse> vault(
        com.kava.earn.v1beta1.QueryProto.QueryVaultRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getVaultMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Deposits queries deposit details based on depositor address and vault
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.earn.v1beta1.QueryProto.QueryDepositsResponse> deposits(
        com.kava.earn.v1beta1.QueryProto.QueryDepositsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDepositsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalSupply returns the total sum of all coins currently locked into the earn module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyResponse> totalSupply(
        com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTotalSupplyMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_VAULTS = 1;
  private static final int METHODID_VAULT = 2;
  private static final int METHODID_DEPOSITS = 3;
  private static final int METHODID_TOTAL_SUPPLY = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PARAMS:
          serviceImpl.params((com.kava.earn.v1beta1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.earn.v1beta1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_VAULTS:
          serviceImpl.vaults((com.kava.earn.v1beta1.QueryProto.QueryVaultsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.earn.v1beta1.QueryProto.QueryVaultsResponse>) responseObserver);
          break;
        case METHODID_VAULT:
          serviceImpl.vault((com.kava.earn.v1beta1.QueryProto.QueryVaultRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.earn.v1beta1.QueryProto.QueryVaultResponse>) responseObserver);
          break;
        case METHODID_DEPOSITS:
          serviceImpl.deposits((com.kava.earn.v1beta1.QueryProto.QueryDepositsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.earn.v1beta1.QueryProto.QueryDepositsResponse>) responseObserver);
          break;
        case METHODID_TOTAL_SUPPLY:
          serviceImpl.totalSupply((com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.earn.v1beta1.QueryProto.QueryParamsRequest,
              com.kava.earn.v1beta1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getVaultsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.earn.v1beta1.QueryProto.QueryVaultsRequest,
              com.kava.earn.v1beta1.QueryProto.QueryVaultsResponse>(
                service, METHODID_VAULTS)))
        .addMethod(
          getVaultMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.earn.v1beta1.QueryProto.QueryVaultRequest,
              com.kava.earn.v1beta1.QueryProto.QueryVaultResponse>(
                service, METHODID_VAULT)))
        .addMethod(
          getDepositsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.earn.v1beta1.QueryProto.QueryDepositsRequest,
              com.kava.earn.v1beta1.QueryProto.QueryDepositsResponse>(
                service, METHODID_DEPOSITS)))
        .addMethod(
          getTotalSupplyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyRequest,
              com.kava.earn.v1beta1.QueryProto.QueryTotalSupplyResponse>(
                service, METHODID_TOTAL_SUPPLY)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kava.earn.v1beta1.QueryProto.getDescriptor();
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
              .addMethod(getParamsMethod())
              .addMethod(getVaultsMethod())
              .addMethod(getVaultMethod())
              .addMethod(getDepositsMethod())
              .addMethod(getTotalSupplyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
