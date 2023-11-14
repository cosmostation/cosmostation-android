package com.kava.bep3.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service for bep3 module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: kava/bep3/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "kava.bep3.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.QueryProto.QueryParamsRequest,
      com.kava.bep3.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.kava.bep3.v1beta1.QueryProto.QueryParamsRequest.class,
      responseType = com.kava.bep3.v1beta1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.QueryProto.QueryParamsRequest,
      com.kava.bep3.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.QueryProto.QueryParamsRequest, com.kava.bep3.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.kava.bep3.v1beta1.QueryProto.QueryParamsRequest, com.kava.bep3.v1beta1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.bep3.v1beta1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.bep3.v1beta1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyRequest,
      com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyResponse> getAssetSupplyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AssetSupply",
      requestType = com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyRequest.class,
      responseType = com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyRequest,
      com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyResponse> getAssetSupplyMethod() {
    io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyRequest, com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyResponse> getAssetSupplyMethod;
    if ((getAssetSupplyMethod = QueryGrpc.getAssetSupplyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAssetSupplyMethod = QueryGrpc.getAssetSupplyMethod) == null) {
          QueryGrpc.getAssetSupplyMethod = getAssetSupplyMethod =
              io.grpc.MethodDescriptor.<com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyRequest, com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AssetSupply"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AssetSupply"))
              .build();
        }
      }
    }
    return getAssetSupplyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesRequest,
      com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesResponse> getAssetSuppliesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AssetSupplies",
      requestType = com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesRequest.class,
      responseType = com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesRequest,
      com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesResponse> getAssetSuppliesMethod() {
    io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesRequest, com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesResponse> getAssetSuppliesMethod;
    if ((getAssetSuppliesMethod = QueryGrpc.getAssetSuppliesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAssetSuppliesMethod = QueryGrpc.getAssetSuppliesMethod) == null) {
          QueryGrpc.getAssetSuppliesMethod = getAssetSuppliesMethod =
              io.grpc.MethodDescriptor.<com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesRequest, com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AssetSupplies"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AssetSupplies"))
              .build();
        }
      }
    }
    return getAssetSuppliesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapRequest,
      com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapResponse> getAtomicSwapMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AtomicSwap",
      requestType = com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapRequest.class,
      responseType = com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapRequest,
      com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapResponse> getAtomicSwapMethod() {
    io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapRequest, com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapResponse> getAtomicSwapMethod;
    if ((getAtomicSwapMethod = QueryGrpc.getAtomicSwapMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAtomicSwapMethod = QueryGrpc.getAtomicSwapMethod) == null) {
          QueryGrpc.getAtomicSwapMethod = getAtomicSwapMethod =
              io.grpc.MethodDescriptor.<com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapRequest, com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AtomicSwap"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AtomicSwap"))
              .build();
        }
      }
    }
    return getAtomicSwapMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsRequest,
      com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsResponse> getAtomicSwapsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AtomicSwaps",
      requestType = com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsRequest.class,
      responseType = com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsRequest,
      com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsResponse> getAtomicSwapsMethod() {
    io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsRequest, com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsResponse> getAtomicSwapsMethod;
    if ((getAtomicSwapsMethod = QueryGrpc.getAtomicSwapsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAtomicSwapsMethod = QueryGrpc.getAtomicSwapsMethod) == null) {
          QueryGrpc.getAtomicSwapsMethod = getAtomicSwapsMethod =
              io.grpc.MethodDescriptor.<com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsRequest, com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AtomicSwaps"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AtomicSwaps"))
              .build();
        }
      }
    }
    return getAtomicSwapsMethod;
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
   * Query defines the gRPC querier service for bep3 module
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Params queries module params
     * </pre>
     */
    default void params(com.kava.bep3.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * AssetSupply queries info about an asset's supply
     * </pre>
     */
    default void assetSupply(com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyRequest request,
        io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAssetSupplyMethod(), responseObserver);
    }

    /**
     * <pre>
     * AssetSupplies queries a list of asset supplies
     * </pre>
     */
    default void assetSupplies(com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesRequest request,
        io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAssetSuppliesMethod(), responseObserver);
    }

    /**
     * <pre>
     * AtomicSwap queries info about an atomic swap
     * </pre>
     */
    default void atomicSwap(com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapRequest request,
        io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAtomicSwapMethod(), responseObserver);
    }

    /**
     * <pre>
     * AtomicSwaps queries a list of atomic swaps
     * </pre>
     */
    default void atomicSwaps(com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsRequest request,
        io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAtomicSwapsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service for bep3 module
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
   * Query defines the gRPC querier service for bep3 module
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
     * Params queries module params
     * </pre>
     */
    public void params(com.kava.bep3.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AssetSupply queries info about an asset's supply
     * </pre>
     */
    public void assetSupply(com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyRequest request,
        io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAssetSupplyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AssetSupplies queries a list of asset supplies
     * </pre>
     */
    public void assetSupplies(com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesRequest request,
        io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAssetSuppliesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AtomicSwap queries info about an atomic swap
     * </pre>
     */
    public void atomicSwap(com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapRequest request,
        io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAtomicSwapMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AtomicSwaps queries a list of atomic swaps
     * </pre>
     */
    public void atomicSwaps(com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsRequest request,
        io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAtomicSwapsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for bep3 module
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
     * Params queries module params
     * </pre>
     */
    public com.kava.bep3.v1beta1.QueryProto.QueryParamsResponse params(com.kava.bep3.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AssetSupply queries info about an asset's supply
     * </pre>
     */
    public com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyResponse assetSupply(com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAssetSupplyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AssetSupplies queries a list of asset supplies
     * </pre>
     */
    public com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesResponse assetSupplies(com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAssetSuppliesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AtomicSwap queries info about an atomic swap
     * </pre>
     */
    public com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapResponse atomicSwap(com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAtomicSwapMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AtomicSwaps queries a list of atomic swaps
     * </pre>
     */
    public com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsResponse atomicSwaps(com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAtomicSwapsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for bep3 module
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
     * Params queries module params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.bep3.v1beta1.QueryProto.QueryParamsResponse> params(
        com.kava.bep3.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AssetSupply queries info about an asset's supply
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyResponse> assetSupply(
        com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAssetSupplyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AssetSupplies queries a list of asset supplies
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesResponse> assetSupplies(
        com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAssetSuppliesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AtomicSwap queries info about an atomic swap
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapResponse> atomicSwap(
        com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAtomicSwapMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AtomicSwaps queries a list of atomic swaps
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsResponse> atomicSwaps(
        com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAtomicSwapsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_ASSET_SUPPLY = 1;
  private static final int METHODID_ASSET_SUPPLIES = 2;
  private static final int METHODID_ATOMIC_SWAP = 3;
  private static final int METHODID_ATOMIC_SWAPS = 4;

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
          serviceImpl.params((com.kava.bep3.v1beta1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_ASSET_SUPPLY:
          serviceImpl.assetSupply((com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyResponse>) responseObserver);
          break;
        case METHODID_ASSET_SUPPLIES:
          serviceImpl.assetSupplies((com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesResponse>) responseObserver);
          break;
        case METHODID_ATOMIC_SWAP:
          serviceImpl.atomicSwap((com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapResponse>) responseObserver);
          break;
        case METHODID_ATOMIC_SWAPS:
          serviceImpl.atomicSwaps((com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsResponse>) responseObserver);
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
              com.kava.bep3.v1beta1.QueryProto.QueryParamsRequest,
              com.kava.bep3.v1beta1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getAssetSupplyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyRequest,
              com.kava.bep3.v1beta1.QueryProto.QueryAssetSupplyResponse>(
                service, METHODID_ASSET_SUPPLY)))
        .addMethod(
          getAssetSuppliesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesRequest,
              com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesResponse>(
                service, METHODID_ASSET_SUPPLIES)))
        .addMethod(
          getAtomicSwapMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapRequest,
              com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapResponse>(
                service, METHODID_ATOMIC_SWAP)))
        .addMethod(
          getAtomicSwapsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsRequest,
              com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapsResponse>(
                service, METHODID_ATOMIC_SWAPS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kava.bep3.v1beta1.QueryProto.getDescriptor();
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
              .addMethod(getAssetSupplyMethod())
              .addMethod(getAssetSuppliesMethod())
              .addMethod(getAtomicSwapMethod())
              .addMethod(getAtomicSwapsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
