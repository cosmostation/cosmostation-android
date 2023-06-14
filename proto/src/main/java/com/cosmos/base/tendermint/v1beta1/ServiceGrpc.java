package com.cosmos.base.tendermint.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Service defines the gRPC querier service for tendermint queries.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmos/base/tendermint/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ServiceGrpc {

  private ServiceGrpc() {}

  public static final String SERVICE_NAME = "cosmos.base.tendermint.v1beta1.Service";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoRequest,
      com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoResponse> getGetNodeInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetNodeInfo",
      requestType = com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoRequest.class,
      responseType = com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoRequest,
      com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoResponse> getGetNodeInfoMethod() {
    io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoRequest, com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoResponse> getGetNodeInfoMethod;
    if ((getGetNodeInfoMethod = ServiceGrpc.getGetNodeInfoMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getGetNodeInfoMethod = ServiceGrpc.getGetNodeInfoMethod) == null) {
          ServiceGrpc.getGetNodeInfoMethod = getGetNodeInfoMethod =
              io.grpc.MethodDescriptor.<com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoRequest, com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetNodeInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("GetNodeInfo"))
              .build();
        }
      }
    }
    return getGetNodeInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingRequest,
      com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingResponse> getGetSyncingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetSyncing",
      requestType = com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingRequest.class,
      responseType = com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingRequest,
      com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingResponse> getGetSyncingMethod() {
    io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingRequest, com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingResponse> getGetSyncingMethod;
    if ((getGetSyncingMethod = ServiceGrpc.getGetSyncingMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getGetSyncingMethod = ServiceGrpc.getGetSyncingMethod) == null) {
          ServiceGrpc.getGetSyncingMethod = getGetSyncingMethod =
              io.grpc.MethodDescriptor.<com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingRequest, com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetSyncing"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("GetSyncing"))
              .build();
        }
      }
    }
    return getGetSyncingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockRequest,
      com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockResponse> getGetLatestBlockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetLatestBlock",
      requestType = com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockRequest.class,
      responseType = com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockRequest,
      com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockResponse> getGetLatestBlockMethod() {
    io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockRequest, com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockResponse> getGetLatestBlockMethod;
    if ((getGetLatestBlockMethod = ServiceGrpc.getGetLatestBlockMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getGetLatestBlockMethod = ServiceGrpc.getGetLatestBlockMethod) == null) {
          ServiceGrpc.getGetLatestBlockMethod = getGetLatestBlockMethod =
              io.grpc.MethodDescriptor.<com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockRequest, com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLatestBlock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("GetLatestBlock"))
              .build();
        }
      }
    }
    return getGetLatestBlockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightRequest,
      com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightResponse> getGetBlockByHeightMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBlockByHeight",
      requestType = com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightRequest.class,
      responseType = com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightRequest,
      com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightResponse> getGetBlockByHeightMethod() {
    io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightRequest, com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightResponse> getGetBlockByHeightMethod;
    if ((getGetBlockByHeightMethod = ServiceGrpc.getGetBlockByHeightMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getGetBlockByHeightMethod = ServiceGrpc.getGetBlockByHeightMethod) == null) {
          ServiceGrpc.getGetBlockByHeightMethod = getGetBlockByHeightMethod =
              io.grpc.MethodDescriptor.<com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightRequest, com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBlockByHeight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("GetBlockByHeight"))
              .build();
        }
      }
    }
    return getGetBlockByHeightMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetRequest,
      com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetResponse> getGetLatestValidatorSetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetLatestValidatorSet",
      requestType = com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetRequest.class,
      responseType = com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetRequest,
      com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetResponse> getGetLatestValidatorSetMethod() {
    io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetRequest, com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetResponse> getGetLatestValidatorSetMethod;
    if ((getGetLatestValidatorSetMethod = ServiceGrpc.getGetLatestValidatorSetMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getGetLatestValidatorSetMethod = ServiceGrpc.getGetLatestValidatorSetMethod) == null) {
          ServiceGrpc.getGetLatestValidatorSetMethod = getGetLatestValidatorSetMethod =
              io.grpc.MethodDescriptor.<com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetRequest, com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLatestValidatorSet"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("GetLatestValidatorSet"))
              .build();
        }
      }
    }
    return getGetLatestValidatorSetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightRequest,
      com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightResponse> getGetValidatorSetByHeightMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetValidatorSetByHeight",
      requestType = com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightRequest.class,
      responseType = com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightRequest,
      com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightResponse> getGetValidatorSetByHeightMethod() {
    io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightRequest, com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightResponse> getGetValidatorSetByHeightMethod;
    if ((getGetValidatorSetByHeightMethod = ServiceGrpc.getGetValidatorSetByHeightMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getGetValidatorSetByHeightMethod = ServiceGrpc.getGetValidatorSetByHeightMethod) == null) {
          ServiceGrpc.getGetValidatorSetByHeightMethod = getGetValidatorSetByHeightMethod =
              io.grpc.MethodDescriptor.<com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightRequest, com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetValidatorSetByHeight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("GetValidatorSetByHeight"))
              .build();
        }
      }
    }
    return getGetValidatorSetByHeightMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryRequest,
      com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryResponse> getABCIQueryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ABCIQuery",
      requestType = com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryRequest.class,
      responseType = com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryRequest,
      com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryResponse> getABCIQueryMethod() {
    io.grpc.MethodDescriptor<com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryRequest, com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryResponse> getABCIQueryMethod;
    if ((getABCIQueryMethod = ServiceGrpc.getABCIQueryMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getABCIQueryMethod = ServiceGrpc.getABCIQueryMethod) == null) {
          ServiceGrpc.getABCIQueryMethod = getABCIQueryMethod =
              io.grpc.MethodDescriptor.<com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryRequest, com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ABCIQuery"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("ABCIQuery"))
              .build();
        }
      }
    }
    return getABCIQueryMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServiceStub>() {
        @java.lang.Override
        public ServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServiceStub(channel, callOptions);
        }
      };
    return ServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServiceBlockingStub>() {
        @java.lang.Override
        public ServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServiceBlockingStub(channel, callOptions);
        }
      };
    return ServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServiceFutureStub>() {
        @java.lang.Override
        public ServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServiceFutureStub(channel, callOptions);
        }
      };
    return ServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Service defines the gRPC querier service for tendermint queries.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * GetNodeInfo queries the current node info.
     * </pre>
     */
    default void getNodeInfo(com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetNodeInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * GetSyncing queries node syncing.
     * </pre>
     */
    default void getSyncing(com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSyncingMethod(), responseObserver);
    }

    /**
     * <pre>
     * GetLatestBlock returns the latest block.
     * </pre>
     */
    default void getLatestBlock(com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetLatestBlockMethod(), responseObserver);
    }

    /**
     * <pre>
     * GetBlockByHeight queries block for given height.
     * </pre>
     */
    default void getBlockByHeight(com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBlockByHeightMethod(), responseObserver);
    }

    /**
     * <pre>
     * GetLatestValidatorSet queries latest validator-set.
     * </pre>
     */
    default void getLatestValidatorSet(com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetLatestValidatorSetMethod(), responseObserver);
    }

    /**
     * <pre>
     * GetValidatorSetByHeight queries validator-set at a given height.
     * </pre>
     */
    default void getValidatorSetByHeight(com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetValidatorSetByHeightMethod(), responseObserver);
    }

    /**
     * <pre>
     * ABCIQuery defines a query handler that supports ABCI queries directly to the
     * application, bypassing Tendermint completely. The ABCI query must contain
     * a valid and supported path, including app, custom, p2p, and store.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    default void aBCIQuery(com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getABCIQueryMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Service.
   * <pre>
   * Service defines the gRPC querier service for tendermint queries.
   * </pre>
   */
  public static abstract class ServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Service.
   * <pre>
   * Service defines the gRPC querier service for tendermint queries.
   * </pre>
   */
  public static final class ServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ServiceStub> {
    private ServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * GetNodeInfo queries the current node info.
     * </pre>
     */
    public void getNodeInfo(com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetNodeInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GetSyncing queries node syncing.
     * </pre>
     */
    public void getSyncing(com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetSyncingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GetLatestBlock returns the latest block.
     * </pre>
     */
    public void getLatestBlock(com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetLatestBlockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GetBlockByHeight queries block for given height.
     * </pre>
     */
    public void getBlockByHeight(com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBlockByHeightMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GetLatestValidatorSet queries latest validator-set.
     * </pre>
     */
    public void getLatestValidatorSet(com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetLatestValidatorSetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GetValidatorSetByHeight queries validator-set at a given height.
     * </pre>
     */
    public void getValidatorSetByHeight(com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetValidatorSetByHeightMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ABCIQuery defines a query handler that supports ABCI queries directly to the
     * application, bypassing Tendermint completely. The ABCI query must contain
     * a valid and supported path, including app, custom, p2p, and store.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public void aBCIQuery(com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getABCIQueryMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Service.
   * <pre>
   * Service defines the gRPC querier service for tendermint queries.
   * </pre>
   */
  public static final class ServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ServiceBlockingStub> {
    private ServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * GetNodeInfo queries the current node info.
     * </pre>
     */
    public com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoResponse getNodeInfo(com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetNodeInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GetSyncing queries node syncing.
     * </pre>
     */
    public com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingResponse getSyncing(com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSyncingMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GetLatestBlock returns the latest block.
     * </pre>
     */
    public com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockResponse getLatestBlock(com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetLatestBlockMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GetBlockByHeight queries block for given height.
     * </pre>
     */
    public com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightResponse getBlockByHeight(com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBlockByHeightMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GetLatestValidatorSet queries latest validator-set.
     * </pre>
     */
    public com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetResponse getLatestValidatorSet(com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetLatestValidatorSetMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GetValidatorSetByHeight queries validator-set at a given height.
     * </pre>
     */
    public com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightResponse getValidatorSetByHeight(com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetValidatorSetByHeightMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ABCIQuery defines a query handler that supports ABCI queries directly to the
     * application, bypassing Tendermint completely. The ABCI query must contain
     * a valid and supported path, including app, custom, p2p, and store.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryResponse aBCIQuery(com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getABCIQueryMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Service.
   * <pre>
   * Service defines the gRPC querier service for tendermint queries.
   * </pre>
   */
  public static final class ServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ServiceFutureStub> {
    private ServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * GetNodeInfo queries the current node info.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoResponse> getNodeInfo(
        com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetNodeInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GetSyncing queries node syncing.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingResponse> getSyncing(
        com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetSyncingMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GetLatestBlock returns the latest block.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockResponse> getLatestBlock(
        com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetLatestBlockMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GetBlockByHeight queries block for given height.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightResponse> getBlockByHeight(
        com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBlockByHeightMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GetLatestValidatorSet queries latest validator-set.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetResponse> getLatestValidatorSet(
        com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetLatestValidatorSetMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GetValidatorSetByHeight queries validator-set at a given height.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightResponse> getValidatorSetByHeight(
        com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetValidatorSetByHeightMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ABCIQuery defines a query handler that supports ABCI queries directly to the
     * application, bypassing Tendermint completely. The ABCI query must contain
     * a valid and supported path, including app, custom, p2p, and store.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryResponse> aBCIQuery(
        com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getABCIQueryMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_NODE_INFO = 0;
  private static final int METHODID_GET_SYNCING = 1;
  private static final int METHODID_GET_LATEST_BLOCK = 2;
  private static final int METHODID_GET_BLOCK_BY_HEIGHT = 3;
  private static final int METHODID_GET_LATEST_VALIDATOR_SET = 4;
  private static final int METHODID_GET_VALIDATOR_SET_BY_HEIGHT = 5;
  private static final int METHODID_ABCIQUERY = 6;

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
        case METHODID_GET_NODE_INFO:
          serviceImpl.getNodeInfo((com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoResponse>) responseObserver);
          break;
        case METHODID_GET_SYNCING:
          serviceImpl.getSyncing((com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingResponse>) responseObserver);
          break;
        case METHODID_GET_LATEST_BLOCK:
          serviceImpl.getLatestBlock((com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockResponse>) responseObserver);
          break;
        case METHODID_GET_BLOCK_BY_HEIGHT:
          serviceImpl.getBlockByHeight((com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightResponse>) responseObserver);
          break;
        case METHODID_GET_LATEST_VALIDATOR_SET:
          serviceImpl.getLatestValidatorSet((com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetResponse>) responseObserver);
          break;
        case METHODID_GET_VALIDATOR_SET_BY_HEIGHT:
          serviceImpl.getValidatorSetByHeight((com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightResponse>) responseObserver);
          break;
        case METHODID_ABCIQUERY:
          serviceImpl.aBCIQuery((com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryResponse>) responseObserver);
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
          getGetNodeInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoRequest,
              com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoResponse>(
                service, METHODID_GET_NODE_INFO)))
        .addMethod(
          getGetSyncingMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingRequest,
              com.cosmos.base.tendermint.v1beta1.QueryProto.GetSyncingResponse>(
                service, METHODID_GET_SYNCING)))
        .addMethod(
          getGetLatestBlockMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockRequest,
              com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockResponse>(
                service, METHODID_GET_LATEST_BLOCK)))
        .addMethod(
          getGetBlockByHeightMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightRequest,
              com.cosmos.base.tendermint.v1beta1.QueryProto.GetBlockByHeightResponse>(
                service, METHODID_GET_BLOCK_BY_HEIGHT)))
        .addMethod(
          getGetLatestValidatorSetMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetRequest,
              com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestValidatorSetResponse>(
                service, METHODID_GET_LATEST_VALIDATOR_SET)))
        .addMethod(
          getGetValidatorSetByHeightMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightRequest,
              com.cosmos.base.tendermint.v1beta1.QueryProto.GetValidatorSetByHeightResponse>(
                service, METHODID_GET_VALIDATOR_SET_BY_HEIGHT)))
        .addMethod(
          getABCIQueryMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryRequest,
              com.cosmos.base.tendermint.v1beta1.QueryProto.ABCIQueryResponse>(
                service, METHODID_ABCIQUERY)))
        .build();
  }

  private static abstract class ServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmos.base.tendermint.v1beta1.QueryProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Service");
    }
  }

  private static final class ServiceFileDescriptorSupplier
      extends ServiceBaseDescriptorSupplier {
    ServiceFileDescriptorSupplier() {}
  }

  private static final class ServiceMethodDescriptorSupplier
      extends ServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ServiceFileDescriptorSupplier())
              .addMethod(getGetNodeInfoMethod())
              .addMethod(getGetSyncingMethod())
              .addMethod(getGetLatestBlockMethod())
              .addMethod(getGetBlockByHeightMethod())
              .addMethod(getGetLatestValidatorSetMethod())
              .addMethod(getGetValidatorSetByHeightMethod())
              .addMethod(getABCIQueryMethod())
              .build();
        }
      }
    }
    return result;
  }
}
