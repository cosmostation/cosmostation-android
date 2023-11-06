package com.cosmos.upgrade.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC upgrade querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmos/upgrade/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "cosmos.upgrade.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanRequest,
      com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanResponse> getCurrentPlanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CurrentPlan",
      requestType = com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanRequest.class,
      responseType = com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanRequest,
      com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanResponse> getCurrentPlanMethod() {
    io.grpc.MethodDescriptor<com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanRequest, com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanResponse> getCurrentPlanMethod;
    if ((getCurrentPlanMethod = QueryGrpc.getCurrentPlanMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCurrentPlanMethod = QueryGrpc.getCurrentPlanMethod) == null) {
          QueryGrpc.getCurrentPlanMethod = getCurrentPlanMethod =
              io.grpc.MethodDescriptor.<com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanRequest, com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CurrentPlan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CurrentPlan"))
              .build();
        }
      }
    }
    return getCurrentPlanMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanRequest,
      com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanResponse> getAppliedPlanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AppliedPlan",
      requestType = com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanRequest.class,
      responseType = com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanRequest,
      com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanResponse> getAppliedPlanMethod() {
    io.grpc.MethodDescriptor<com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanRequest, com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanResponse> getAppliedPlanMethod;
    if ((getAppliedPlanMethod = QueryGrpc.getAppliedPlanMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAppliedPlanMethod = QueryGrpc.getAppliedPlanMethod) == null) {
          QueryGrpc.getAppliedPlanMethod = getAppliedPlanMethod =
              io.grpc.MethodDescriptor.<com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanRequest, com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AppliedPlan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AppliedPlan"))
              .build();
        }
      }
    }
    return getAppliedPlanMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateRequest,
      com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateResponse> getUpgradedConsensusStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpgradedConsensusState",
      requestType = com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateRequest.class,
      responseType = com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateRequest,
      com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateResponse> getUpgradedConsensusStateMethod() {
    io.grpc.MethodDescriptor<com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateRequest, com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateResponse> getUpgradedConsensusStateMethod;
    if ((getUpgradedConsensusStateMethod = QueryGrpc.getUpgradedConsensusStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUpgradedConsensusStateMethod = QueryGrpc.getUpgradedConsensusStateMethod) == null) {
          QueryGrpc.getUpgradedConsensusStateMethod = getUpgradedConsensusStateMethod =
              io.grpc.MethodDescriptor.<com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateRequest, com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpgradedConsensusState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UpgradedConsensusState"))
              .build();
        }
      }
    }
    return getUpgradedConsensusStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsRequest,
      com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsResponse> getModuleVersionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ModuleVersions",
      requestType = com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsRequest.class,
      responseType = com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsRequest,
      com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsResponse> getModuleVersionsMethod() {
    io.grpc.MethodDescriptor<com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsRequest, com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsResponse> getModuleVersionsMethod;
    if ((getModuleVersionsMethod = QueryGrpc.getModuleVersionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getModuleVersionsMethod = QueryGrpc.getModuleVersionsMethod) == null) {
          QueryGrpc.getModuleVersionsMethod = getModuleVersionsMethod =
              io.grpc.MethodDescriptor.<com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsRequest, com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ModuleVersions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ModuleVersions"))
              .build();
        }
      }
    }
    return getModuleVersionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityRequest,
      com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityResponse> getAuthorityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Authority",
      requestType = com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityRequest.class,
      responseType = com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityRequest,
      com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityResponse> getAuthorityMethod() {
    io.grpc.MethodDescriptor<com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityRequest, com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityResponse> getAuthorityMethod;
    if ((getAuthorityMethod = QueryGrpc.getAuthorityMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAuthorityMethod = QueryGrpc.getAuthorityMethod) == null) {
          QueryGrpc.getAuthorityMethod = getAuthorityMethod =
              io.grpc.MethodDescriptor.<com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityRequest, com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Authority"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Authority"))
              .build();
        }
      }
    }
    return getAuthorityMethod;
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
   * Query defines the gRPC upgrade querier service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * CurrentPlan queries the current upgrade plan.
     * </pre>
     */
    default void currentPlan(com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCurrentPlanMethod(), responseObserver);
    }

    /**
     * <pre>
     * AppliedPlan queries a previously applied upgrade plan by its name.
     * </pre>
     */
    default void appliedPlan(com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAppliedPlanMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpgradedConsensusState queries the consensus state that will serve
     * as a trusted kernel for the next version of this chain. It will only be
     * stored at the last height of this chain.
     * UpgradedConsensusState RPC not supported with legacy querier
     * This rpc is deprecated now that IBC has its own replacement
     * (https://github.com/cosmos/ibc-go/blob/2c880a22e9f9cc75f62b527ca94aa75ce1106001/proto/ibc/core/client/v1/query.proto#L54)
     * </pre>
     */
    @java.lang.Deprecated
    default void upgradedConsensusState(com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpgradedConsensusStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * ModuleVersions queries the list of module versions from state.
     * Since: cosmos-sdk 0.43
     * </pre>
     */
    default void moduleVersions(com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getModuleVersionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns the account with authority to conduct upgrades
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    default void authority(com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAuthorityMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC upgrade querier service.
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
   * Query defines the gRPC upgrade querier service.
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
     * CurrentPlan queries the current upgrade plan.
     * </pre>
     */
    public void currentPlan(com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCurrentPlanMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AppliedPlan queries a previously applied upgrade plan by its name.
     * </pre>
     */
    public void appliedPlan(com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAppliedPlanMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpgradedConsensusState queries the consensus state that will serve
     * as a trusted kernel for the next version of this chain. It will only be
     * stored at the last height of this chain.
     * UpgradedConsensusState RPC not supported with legacy querier
     * This rpc is deprecated now that IBC has its own replacement
     * (https://github.com/cosmos/ibc-go/blob/2c880a22e9f9cc75f62b527ca94aa75ce1106001/proto/ibc/core/client/v1/query.proto#L54)
     * </pre>
     */
    @java.lang.Deprecated
    public void upgradedConsensusState(com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpgradedConsensusStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ModuleVersions queries the list of module versions from state.
     * Since: cosmos-sdk 0.43
     * </pre>
     */
    public void moduleVersions(com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getModuleVersionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns the account with authority to conduct upgrades
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public void authority(com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAuthorityMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC upgrade querier service.
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
     * CurrentPlan queries the current upgrade plan.
     * </pre>
     */
    public com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanResponse currentPlan(com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCurrentPlanMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AppliedPlan queries a previously applied upgrade plan by its name.
     * </pre>
     */
    public com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanResponse appliedPlan(com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAppliedPlanMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpgradedConsensusState queries the consensus state that will serve
     * as a trusted kernel for the next version of this chain. It will only be
     * stored at the last height of this chain.
     * UpgradedConsensusState RPC not supported with legacy querier
     * This rpc is deprecated now that IBC has its own replacement
     * (https://github.com/cosmos/ibc-go/blob/2c880a22e9f9cc75f62b527ca94aa75ce1106001/proto/ibc/core/client/v1/query.proto#L54)
     * </pre>
     */
    @java.lang.Deprecated
    public com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateResponse upgradedConsensusState(com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpgradedConsensusStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ModuleVersions queries the list of module versions from state.
     * Since: cosmos-sdk 0.43
     * </pre>
     */
    public com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsResponse moduleVersions(com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getModuleVersionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns the account with authority to conduct upgrades
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityResponse authority(com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAuthorityMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC upgrade querier service.
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
     * CurrentPlan queries the current upgrade plan.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanResponse> currentPlan(
        com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCurrentPlanMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AppliedPlan queries a previously applied upgrade plan by its name.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanResponse> appliedPlan(
        com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAppliedPlanMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpgradedConsensusState queries the consensus state that will serve
     * as a trusted kernel for the next version of this chain. It will only be
     * stored at the last height of this chain.
     * UpgradedConsensusState RPC not supported with legacy querier
     * This rpc is deprecated now that IBC has its own replacement
     * (https://github.com/cosmos/ibc-go/blob/2c880a22e9f9cc75f62b527ca94aa75ce1106001/proto/ibc/core/client/v1/query.proto#L54)
     * </pre>
     */
    @java.lang.Deprecated
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateResponse> upgradedConsensusState(
        com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpgradedConsensusStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ModuleVersions queries the list of module versions from state.
     * Since: cosmos-sdk 0.43
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsResponse> moduleVersions(
        com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getModuleVersionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns the account with authority to conduct upgrades
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityResponse> authority(
        com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAuthorityMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CURRENT_PLAN = 0;
  private static final int METHODID_APPLIED_PLAN = 1;
  private static final int METHODID_UPGRADED_CONSENSUS_STATE = 2;
  private static final int METHODID_MODULE_VERSIONS = 3;
  private static final int METHODID_AUTHORITY = 4;

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
        case METHODID_CURRENT_PLAN:
          serviceImpl.currentPlan((com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanResponse>) responseObserver);
          break;
        case METHODID_APPLIED_PLAN:
          serviceImpl.appliedPlan((com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanResponse>) responseObserver);
          break;
        case METHODID_UPGRADED_CONSENSUS_STATE:
          serviceImpl.upgradedConsensusState((com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateResponse>) responseObserver);
          break;
        case METHODID_MODULE_VERSIONS:
          serviceImpl.moduleVersions((com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsResponse>) responseObserver);
          break;
        case METHODID_AUTHORITY:
          serviceImpl.authority((com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityResponse>) responseObserver);
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
          getCurrentPlanMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanRequest,
              com.cosmos.upgrade.v1beta1.QueryProto.QueryCurrentPlanResponse>(
                service, METHODID_CURRENT_PLAN)))
        .addMethod(
          getAppliedPlanMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanRequest,
              com.cosmos.upgrade.v1beta1.QueryProto.QueryAppliedPlanResponse>(
                service, METHODID_APPLIED_PLAN)))
        .addMethod(
          getUpgradedConsensusStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateRequest,
              com.cosmos.upgrade.v1beta1.QueryProto.QueryUpgradedConsensusStateResponse>(
                service, METHODID_UPGRADED_CONSENSUS_STATE)))
        .addMethod(
          getModuleVersionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsRequest,
              com.cosmos.upgrade.v1beta1.QueryProto.QueryModuleVersionsResponse>(
                service, METHODID_MODULE_VERSIONS)))
        .addMethod(
          getAuthorityMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityRequest,
              com.cosmos.upgrade.v1beta1.QueryProto.QueryAuthorityResponse>(
                service, METHODID_AUTHORITY)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmos.upgrade.v1beta1.QueryProto.getDescriptor();
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
              .addMethod(getCurrentPlanMethod())
              .addMethod(getAppliedPlanMethod())
              .addMethod(getUpgradedConsensusStateMethod())
              .addMethod(getModuleVersionsMethod())
              .addMethod(getAuthorityMethod())
              .build();
        }
      }
    }
    return result;
  }
}
