package com.babylon.incentive;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: babylon/incentive/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "babylon.incentive.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.babylon.incentive.QueryProto.QueryParamsRequest,
      com.babylon.incentive.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.babylon.incentive.QueryProto.QueryParamsRequest.class,
      responseType = com.babylon.incentive.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.incentive.QueryProto.QueryParamsRequest,
      com.babylon.incentive.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.babylon.incentive.QueryProto.QueryParamsRequest, com.babylon.incentive.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.babylon.incentive.QueryProto.QueryParamsRequest, com.babylon.incentive.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.incentive.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.incentive.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.incentive.QueryProto.QueryRewardGaugesRequest,
      com.babylon.incentive.QueryProto.QueryRewardGaugesResponse> getRewardGaugesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RewardGauges",
      requestType = com.babylon.incentive.QueryProto.QueryRewardGaugesRequest.class,
      responseType = com.babylon.incentive.QueryProto.QueryRewardGaugesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.incentive.QueryProto.QueryRewardGaugesRequest,
      com.babylon.incentive.QueryProto.QueryRewardGaugesResponse> getRewardGaugesMethod() {
    io.grpc.MethodDescriptor<com.babylon.incentive.QueryProto.QueryRewardGaugesRequest, com.babylon.incentive.QueryProto.QueryRewardGaugesResponse> getRewardGaugesMethod;
    if ((getRewardGaugesMethod = QueryGrpc.getRewardGaugesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRewardGaugesMethod = QueryGrpc.getRewardGaugesMethod) == null) {
          QueryGrpc.getRewardGaugesMethod = getRewardGaugesMethod =
              io.grpc.MethodDescriptor.<com.babylon.incentive.QueryProto.QueryRewardGaugesRequest, com.babylon.incentive.QueryProto.QueryRewardGaugesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RewardGauges"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.incentive.QueryProto.QueryRewardGaugesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.incentive.QueryProto.QueryRewardGaugesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RewardGauges"))
              .build();
        }
      }
    }
    return getRewardGaugesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.incentive.QueryProto.QueryBTCStakingGaugeRequest,
      com.babylon.incentive.QueryProto.QueryBTCStakingGaugeResponse> getBTCStakingGaugeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BTCStakingGauge",
      requestType = com.babylon.incentive.QueryProto.QueryBTCStakingGaugeRequest.class,
      responseType = com.babylon.incentive.QueryProto.QueryBTCStakingGaugeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.incentive.QueryProto.QueryBTCStakingGaugeRequest,
      com.babylon.incentive.QueryProto.QueryBTCStakingGaugeResponse> getBTCStakingGaugeMethod() {
    io.grpc.MethodDescriptor<com.babylon.incentive.QueryProto.QueryBTCStakingGaugeRequest, com.babylon.incentive.QueryProto.QueryBTCStakingGaugeResponse> getBTCStakingGaugeMethod;
    if ((getBTCStakingGaugeMethod = QueryGrpc.getBTCStakingGaugeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBTCStakingGaugeMethod = QueryGrpc.getBTCStakingGaugeMethod) == null) {
          QueryGrpc.getBTCStakingGaugeMethod = getBTCStakingGaugeMethod =
              io.grpc.MethodDescriptor.<com.babylon.incentive.QueryProto.QueryBTCStakingGaugeRequest, com.babylon.incentive.QueryProto.QueryBTCStakingGaugeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BTCStakingGauge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.incentive.QueryProto.QueryBTCStakingGaugeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.incentive.QueryProto.QueryBTCStakingGaugeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BTCStakingGauge"))
              .build();
        }
      }
    }
    return getBTCStakingGaugeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressRequest,
      com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressResponse> getDelegatorWithdrawAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegatorWithdrawAddress",
      requestType = com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressRequest.class,
      responseType = com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressRequest,
      com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressResponse> getDelegatorWithdrawAddressMethod() {
    io.grpc.MethodDescriptor<com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressRequest, com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressResponse> getDelegatorWithdrawAddressMethod;
    if ((getDelegatorWithdrawAddressMethod = QueryGrpc.getDelegatorWithdrawAddressMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegatorWithdrawAddressMethod = QueryGrpc.getDelegatorWithdrawAddressMethod) == null) {
          QueryGrpc.getDelegatorWithdrawAddressMethod = getDelegatorWithdrawAddressMethod =
              io.grpc.MethodDescriptor.<com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressRequest, com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegatorWithdrawAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegatorWithdrawAddress"))
              .build();
        }
      }
    }
    return getDelegatorWithdrawAddressMethod;
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
  public interface AsyncService {

    /**
     * <pre>
     * Parameters queries the parameters of the module.
     * </pre>
     */
    default void params(com.babylon.incentive.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.incentive.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * RewardGauge queries the reward gauge of a given stakeholder address
     * </pre>
     */
    default void rewardGauges(com.babylon.incentive.QueryProto.QueryRewardGaugesRequest request,
        io.grpc.stub.StreamObserver<com.babylon.incentive.QueryProto.QueryRewardGaugesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRewardGaugesMethod(), responseObserver);
    }

    /**
     * <pre>
     * BTCStakingGauge queries the BTC staking gauge of a given height
     * </pre>
     */
    default void bTCStakingGauge(com.babylon.incentive.QueryProto.QueryBTCStakingGaugeRequest request,
        io.grpc.stub.StreamObserver<com.babylon.incentive.QueryProto.QueryBTCStakingGaugeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBTCStakingGaugeMethod(), responseObserver);
    }

    /**
     * <pre>
     * DelegatorWithdrawAddress queries withdraw address of a delegator.
     * </pre>
     */
    default void delegatorWithdrawAddress(com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressRequest request,
        io.grpc.stub.StreamObserver<com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDelegatorWithdrawAddressMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service.
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
   * Query defines the gRPC querier service.
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public void params(com.babylon.incentive.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.incentive.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RewardGauge queries the reward gauge of a given stakeholder address
     * </pre>
     */
    public void rewardGauges(com.babylon.incentive.QueryProto.QueryRewardGaugesRequest request,
        io.grpc.stub.StreamObserver<com.babylon.incentive.QueryProto.QueryRewardGaugesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRewardGaugesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BTCStakingGauge queries the BTC staking gauge of a given height
     * </pre>
     */
    public void bTCStakingGauge(com.babylon.incentive.QueryProto.QueryBTCStakingGaugeRequest request,
        io.grpc.stub.StreamObserver<com.babylon.incentive.QueryProto.QueryBTCStakingGaugeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBTCStakingGaugeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DelegatorWithdrawAddress queries withdraw address of a delegator.
     * </pre>
     */
    public void delegatorWithdrawAddress(com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressRequest request,
        io.grpc.stub.StreamObserver<com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDelegatorWithdrawAddressMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service.
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public com.babylon.incentive.QueryProto.QueryParamsResponse params(com.babylon.incentive.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RewardGauge queries the reward gauge of a given stakeholder address
     * </pre>
     */
    public com.babylon.incentive.QueryProto.QueryRewardGaugesResponse rewardGauges(com.babylon.incentive.QueryProto.QueryRewardGaugesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRewardGaugesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BTCStakingGauge queries the BTC staking gauge of a given height
     * </pre>
     */
    public com.babylon.incentive.QueryProto.QueryBTCStakingGaugeResponse bTCStakingGauge(com.babylon.incentive.QueryProto.QueryBTCStakingGaugeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBTCStakingGaugeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DelegatorWithdrawAddress queries withdraw address of a delegator.
     * </pre>
     */
    public com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressResponse delegatorWithdrawAddress(com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDelegatorWithdrawAddressMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service.
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.incentive.QueryProto.QueryParamsResponse> params(
        com.babylon.incentive.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RewardGauge queries the reward gauge of a given stakeholder address
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.incentive.QueryProto.QueryRewardGaugesResponse> rewardGauges(
        com.babylon.incentive.QueryProto.QueryRewardGaugesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRewardGaugesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BTCStakingGauge queries the BTC staking gauge of a given height
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.incentive.QueryProto.QueryBTCStakingGaugeResponse> bTCStakingGauge(
        com.babylon.incentive.QueryProto.QueryBTCStakingGaugeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBTCStakingGaugeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DelegatorWithdrawAddress queries withdraw address of a delegator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressResponse> delegatorWithdrawAddress(
        com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDelegatorWithdrawAddressMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_REWARD_GAUGES = 1;
  private static final int METHODID_BTCSTAKING_GAUGE = 2;
  private static final int METHODID_DELEGATOR_WITHDRAW_ADDRESS = 3;

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
          serviceImpl.params((com.babylon.incentive.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.incentive.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_REWARD_GAUGES:
          serviceImpl.rewardGauges((com.babylon.incentive.QueryProto.QueryRewardGaugesRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.incentive.QueryProto.QueryRewardGaugesResponse>) responseObserver);
          break;
        case METHODID_BTCSTAKING_GAUGE:
          serviceImpl.bTCStakingGauge((com.babylon.incentive.QueryProto.QueryBTCStakingGaugeRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.incentive.QueryProto.QueryBTCStakingGaugeResponse>) responseObserver);
          break;
        case METHODID_DELEGATOR_WITHDRAW_ADDRESS:
          serviceImpl.delegatorWithdrawAddress((com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressResponse>) responseObserver);
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
              com.babylon.incentive.QueryProto.QueryParamsRequest,
              com.babylon.incentive.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getRewardGaugesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.incentive.QueryProto.QueryRewardGaugesRequest,
              com.babylon.incentive.QueryProto.QueryRewardGaugesResponse>(
                service, METHODID_REWARD_GAUGES)))
        .addMethod(
          getBTCStakingGaugeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.incentive.QueryProto.QueryBTCStakingGaugeRequest,
              com.babylon.incentive.QueryProto.QueryBTCStakingGaugeResponse>(
                service, METHODID_BTCSTAKING_GAUGE)))
        .addMethod(
          getDelegatorWithdrawAddressMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressRequest,
              com.babylon.incentive.QueryProto.QueryDelegatorWithdrawAddressResponse>(
                service, METHODID_DELEGATOR_WITHDRAW_ADDRESS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.babylon.incentive.QueryProto.getDescriptor();
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
              .addMethod(getRewardGaugesMethod())
              .addMethod(getBTCStakingGaugeMethod())
              .addMethod(getDelegatorWithdrawAddressMethod())
              .build();
        }
      }
    }
    return result;
  }
}
