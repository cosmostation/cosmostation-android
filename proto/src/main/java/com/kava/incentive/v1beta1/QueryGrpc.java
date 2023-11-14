package com.kava.incentive.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service for incentive module.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: kava/incentive/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "kava.incentive.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.QueryProto.QueryParamsRequest,
      com.kava.incentive.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.kava.incentive.v1beta1.QueryProto.QueryParamsRequest.class,
      responseType = com.kava.incentive.v1beta1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.QueryProto.QueryParamsRequest,
      com.kava.incentive.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.QueryProto.QueryParamsRequest, com.kava.incentive.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.kava.incentive.v1beta1.QueryProto.QueryParamsRequest, com.kava.incentive.v1beta1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.QueryProto.QueryRewardsRequest,
      com.kava.incentive.v1beta1.QueryProto.QueryRewardsResponse> getRewardsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Rewards",
      requestType = com.kava.incentive.v1beta1.QueryProto.QueryRewardsRequest.class,
      responseType = com.kava.incentive.v1beta1.QueryProto.QueryRewardsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.QueryProto.QueryRewardsRequest,
      com.kava.incentive.v1beta1.QueryProto.QueryRewardsResponse> getRewardsMethod() {
    io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.QueryProto.QueryRewardsRequest, com.kava.incentive.v1beta1.QueryProto.QueryRewardsResponse> getRewardsMethod;
    if ((getRewardsMethod = QueryGrpc.getRewardsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRewardsMethod = QueryGrpc.getRewardsMethod) == null) {
          QueryGrpc.getRewardsMethod = getRewardsMethod =
              io.grpc.MethodDescriptor.<com.kava.incentive.v1beta1.QueryProto.QueryRewardsRequest, com.kava.incentive.v1beta1.QueryProto.QueryRewardsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Rewards"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.QueryProto.QueryRewardsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.QueryProto.QueryRewardsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Rewards"))
              .build();
        }
      }
    }
    return getRewardsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsRequest,
      com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsResponse> getRewardFactorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RewardFactors",
      requestType = com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsRequest.class,
      responseType = com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsRequest,
      com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsResponse> getRewardFactorsMethod() {
    io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsRequest, com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsResponse> getRewardFactorsMethod;
    if ((getRewardFactorsMethod = QueryGrpc.getRewardFactorsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRewardFactorsMethod = QueryGrpc.getRewardFactorsMethod) == null) {
          QueryGrpc.getRewardFactorsMethod = getRewardFactorsMethod =
              io.grpc.MethodDescriptor.<com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsRequest, com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RewardFactors"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RewardFactors"))
              .build();
        }
      }
    }
    return getRewardFactorsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.QueryProto.QueryApyRequest,
      com.kava.incentive.v1beta1.QueryProto.QueryApyResponse> getApyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Apy",
      requestType = com.kava.incentive.v1beta1.QueryProto.QueryApyRequest.class,
      responseType = com.kava.incentive.v1beta1.QueryProto.QueryApyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.QueryProto.QueryApyRequest,
      com.kava.incentive.v1beta1.QueryProto.QueryApyResponse> getApyMethod() {
    io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.QueryProto.QueryApyRequest, com.kava.incentive.v1beta1.QueryProto.QueryApyResponse> getApyMethod;
    if ((getApyMethod = QueryGrpc.getApyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getApyMethod = QueryGrpc.getApyMethod) == null) {
          QueryGrpc.getApyMethod = getApyMethod =
              io.grpc.MethodDescriptor.<com.kava.incentive.v1beta1.QueryProto.QueryApyRequest, com.kava.incentive.v1beta1.QueryProto.QueryApyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Apy"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.QueryProto.QueryApyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.QueryProto.QueryApyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Apy"))
              .build();
        }
      }
    }
    return getApyMethod;
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
   * Query defines the gRPC querier service for incentive module.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Params queries module params.
     * </pre>
     */
    default void params(com.kava.incentive.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Rewards queries reward information for a given user.
     * </pre>
     */
    default void rewards(com.kava.incentive.v1beta1.QueryProto.QueryRewardsRequest request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.QueryProto.QueryRewardsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRewardsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Rewards queries the reward factors.
     * </pre>
     */
    default void rewardFactors(com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsRequest request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRewardFactorsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Apy queries incentive reward apy for a reward.
     * </pre>
     */
    default void apy(com.kava.incentive.v1beta1.QueryProto.QueryApyRequest request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.QueryProto.QueryApyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getApyMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service for incentive module.
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
   * Query defines the gRPC querier service for incentive module.
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
     * Params queries module params.
     * </pre>
     */
    public void params(com.kava.incentive.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Rewards queries reward information for a given user.
     * </pre>
     */
    public void rewards(com.kava.incentive.v1beta1.QueryProto.QueryRewardsRequest request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.QueryProto.QueryRewardsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRewardsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Rewards queries the reward factors.
     * </pre>
     */
    public void rewardFactors(com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsRequest request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRewardFactorsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Apy queries incentive reward apy for a reward.
     * </pre>
     */
    public void apy(com.kava.incentive.v1beta1.QueryProto.QueryApyRequest request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.QueryProto.QueryApyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getApyMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for incentive module.
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
     * Params queries module params.
     * </pre>
     */
    public com.kava.incentive.v1beta1.QueryProto.QueryParamsResponse params(com.kava.incentive.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Rewards queries reward information for a given user.
     * </pre>
     */
    public com.kava.incentive.v1beta1.QueryProto.QueryRewardsResponse rewards(com.kava.incentive.v1beta1.QueryProto.QueryRewardsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRewardsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Rewards queries the reward factors.
     * </pre>
     */
    public com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsResponse rewardFactors(com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRewardFactorsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Apy queries incentive reward apy for a reward.
     * </pre>
     */
    public com.kava.incentive.v1beta1.QueryProto.QueryApyResponse apy(com.kava.incentive.v1beta1.QueryProto.QueryApyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getApyMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for incentive module.
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
     * Params queries module params.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.incentive.v1beta1.QueryProto.QueryParamsResponse> params(
        com.kava.incentive.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Rewards queries reward information for a given user.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.incentive.v1beta1.QueryProto.QueryRewardsResponse> rewards(
        com.kava.incentive.v1beta1.QueryProto.QueryRewardsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRewardsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Rewards queries the reward factors.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsResponse> rewardFactors(
        com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRewardFactorsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Apy queries incentive reward apy for a reward.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.incentive.v1beta1.QueryProto.QueryApyResponse> apy(
        com.kava.incentive.v1beta1.QueryProto.QueryApyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getApyMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_REWARDS = 1;
  private static final int METHODID_REWARD_FACTORS = 2;
  private static final int METHODID_APY = 3;

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
          serviceImpl.params((com.kava.incentive.v1beta1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_REWARDS:
          serviceImpl.rewards((com.kava.incentive.v1beta1.QueryProto.QueryRewardsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.QueryProto.QueryRewardsResponse>) responseObserver);
          break;
        case METHODID_REWARD_FACTORS:
          serviceImpl.rewardFactors((com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsResponse>) responseObserver);
          break;
        case METHODID_APY:
          serviceImpl.apy((com.kava.incentive.v1beta1.QueryProto.QueryApyRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.QueryProto.QueryApyResponse>) responseObserver);
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
              com.kava.incentive.v1beta1.QueryProto.QueryParamsRequest,
              com.kava.incentive.v1beta1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getRewardsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.incentive.v1beta1.QueryProto.QueryRewardsRequest,
              com.kava.incentive.v1beta1.QueryProto.QueryRewardsResponse>(
                service, METHODID_REWARDS)))
        .addMethod(
          getRewardFactorsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsRequest,
              com.kava.incentive.v1beta1.QueryProto.QueryRewardFactorsResponse>(
                service, METHODID_REWARD_FACTORS)))
        .addMethod(
          getApyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.incentive.v1beta1.QueryProto.QueryApyRequest,
              com.kava.incentive.v1beta1.QueryProto.QueryApyResponse>(
                service, METHODID_APY)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kava.incentive.v1beta1.QueryProto.getDescriptor();
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
              .addMethod(getRewardsMethod())
              .addMethod(getRewardFactorsMethod())
              .addMethod(getApyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
