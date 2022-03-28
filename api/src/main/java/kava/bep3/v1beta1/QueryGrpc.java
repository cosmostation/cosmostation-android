package kava.bep3.v1beta1;

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
 * Query defines the gRPC querier service for bep3 module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/bep3/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "kava.bep3.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.bep3.v1beta1.QueryOuterClass.QueryParamsRequest,
      kava.bep3.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = kava.bep3.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = kava.bep3.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.bep3.v1beta1.QueryOuterClass.QueryParamsRequest,
      kava.bep3.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<kava.bep3.v1beta1.QueryOuterClass.QueryParamsRequest, kava.bep3.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<kava.bep3.v1beta1.QueryOuterClass.QueryParamsRequest, kava.bep3.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.bep3.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.bep3.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyRequest,
      kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyResponse> getAssetSupplyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AssetSupply",
      requestType = kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyRequest.class,
      responseType = kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyRequest,
      kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyResponse> getAssetSupplyMethod() {
    io.grpc.MethodDescriptor<kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyRequest, kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyResponse> getAssetSupplyMethod;
    if ((getAssetSupplyMethod = QueryGrpc.getAssetSupplyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAssetSupplyMethod = QueryGrpc.getAssetSupplyMethod) == null) {
          QueryGrpc.getAssetSupplyMethod = getAssetSupplyMethod =
              io.grpc.MethodDescriptor.<kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyRequest, kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AssetSupply"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AssetSupply"))
              .build();
        }
      }
    }
    return getAssetSupplyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesRequest,
      kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesResponse> getAssetSuppliesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AssetSupplies",
      requestType = kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesRequest.class,
      responseType = kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesRequest,
      kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesResponse> getAssetSuppliesMethod() {
    io.grpc.MethodDescriptor<kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesRequest, kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesResponse> getAssetSuppliesMethod;
    if ((getAssetSuppliesMethod = QueryGrpc.getAssetSuppliesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAssetSuppliesMethod = QueryGrpc.getAssetSuppliesMethod) == null) {
          QueryGrpc.getAssetSuppliesMethod = getAssetSuppliesMethod =
              io.grpc.MethodDescriptor.<kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesRequest, kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AssetSupplies"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AssetSupplies"))
              .build();
        }
      }
    }
    return getAssetSuppliesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapRequest,
      kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapResponse> getAtomicSwapMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AtomicSwap",
      requestType = kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapRequest.class,
      responseType = kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapRequest,
      kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapResponse> getAtomicSwapMethod() {
    io.grpc.MethodDescriptor<kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapRequest, kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapResponse> getAtomicSwapMethod;
    if ((getAtomicSwapMethod = QueryGrpc.getAtomicSwapMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAtomicSwapMethod = QueryGrpc.getAtomicSwapMethod) == null) {
          QueryGrpc.getAtomicSwapMethod = getAtomicSwapMethod =
              io.grpc.MethodDescriptor.<kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapRequest, kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AtomicSwap"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AtomicSwap"))
              .build();
        }
      }
    }
    return getAtomicSwapMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsRequest,
      kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsResponse> getAtomicSwapsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AtomicSwaps",
      requestType = kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsRequest.class,
      responseType = kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsRequest,
      kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsResponse> getAtomicSwapsMethod() {
    io.grpc.MethodDescriptor<kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsRequest, kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsResponse> getAtomicSwapsMethod;
    if ((getAtomicSwapsMethod = QueryGrpc.getAtomicSwapsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAtomicSwapsMethod = QueryGrpc.getAtomicSwapsMethod) == null) {
          QueryGrpc.getAtomicSwapsMethod = getAtomicSwapsMethod =
              io.grpc.MethodDescriptor.<kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsRequest, kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AtomicSwaps"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsResponse.getDefaultInstance()))
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
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Params queries module params
     * </pre>
     */
    public void params(kava.bep3.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<kava.bep3.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * AssetSupply queries info about an asset's supply
     * </pre>
     */
    public void assetSupply(kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyRequest request,
        io.grpc.stub.StreamObserver<kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAssetSupplyMethod(), responseObserver);
    }

    /**
     * <pre>
     * AssetSupplies queries a list of asset supplies
     * </pre>
     */
    public void assetSupplies(kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesRequest request,
        io.grpc.stub.StreamObserver<kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAssetSuppliesMethod(), responseObserver);
    }

    /**
     * <pre>
     * AtomicSwap queries info about an atomic swap
     * </pre>
     */
    public void atomicSwap(kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapRequest request,
        io.grpc.stub.StreamObserver<kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAtomicSwapMethod(), responseObserver);
    }

    /**
     * <pre>
     * AtomicSwaps queries a list of atomic swaps
     * </pre>
     */
    public void atomicSwaps(kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsRequest request,
        io.grpc.stub.StreamObserver<kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAtomicSwapsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.bep3.v1beta1.QueryOuterClass.QueryParamsRequest,
                kava.bep3.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getAssetSupplyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyRequest,
                kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyResponse>(
                  this, METHODID_ASSET_SUPPLY)))
          .addMethod(
            getAssetSuppliesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesRequest,
                kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesResponse>(
                  this, METHODID_ASSET_SUPPLIES)))
          .addMethod(
            getAtomicSwapMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapRequest,
                kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapResponse>(
                  this, METHODID_ATOMIC_SWAP)))
          .addMethod(
            getAtomicSwapsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsRequest,
                kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsResponse>(
                  this, METHODID_ATOMIC_SWAPS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for bep3 module
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
     * Params queries module params
     * </pre>
     */
    public void params(kava.bep3.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<kava.bep3.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AssetSupply queries info about an asset's supply
     * </pre>
     */
    public void assetSupply(kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyRequest request,
        io.grpc.stub.StreamObserver<kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAssetSupplyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AssetSupplies queries a list of asset supplies
     * </pre>
     */
    public void assetSupplies(kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesRequest request,
        io.grpc.stub.StreamObserver<kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAssetSuppliesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AtomicSwap queries info about an atomic swap
     * </pre>
     */
    public void atomicSwap(kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapRequest request,
        io.grpc.stub.StreamObserver<kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAtomicSwapMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AtomicSwaps queries a list of atomic swaps
     * </pre>
     */
    public void atomicSwaps(kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsRequest request,
        io.grpc.stub.StreamObserver<kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAtomicSwapsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for bep3 module
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
     * Params queries module params
     * </pre>
     */
    public kava.bep3.v1beta1.QueryOuterClass.QueryParamsResponse params(kava.bep3.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AssetSupply queries info about an asset's supply
     * </pre>
     */
    public kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyResponse assetSupply(kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyRequest request) {
      return blockingUnaryCall(
          getChannel(), getAssetSupplyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AssetSupplies queries a list of asset supplies
     * </pre>
     */
    public kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesResponse assetSupplies(kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesRequest request) {
      return blockingUnaryCall(
          getChannel(), getAssetSuppliesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AtomicSwap queries info about an atomic swap
     * </pre>
     */
    public kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapResponse atomicSwap(kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapRequest request) {
      return blockingUnaryCall(
          getChannel(), getAtomicSwapMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AtomicSwaps queries a list of atomic swaps
     * </pre>
     */
    public kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsResponse atomicSwaps(kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAtomicSwapsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for bep3 module
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
     * Params queries module params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.bep3.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        kava.bep3.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AssetSupply queries info about an asset's supply
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyResponse> assetSupply(
        kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAssetSupplyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AssetSupplies queries a list of asset supplies
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesResponse> assetSupplies(
        kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAssetSuppliesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AtomicSwap queries info about an atomic swap
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapResponse> atomicSwap(
        kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAtomicSwapMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AtomicSwaps queries a list of atomic swaps
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsResponse> atomicSwaps(
        kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsRequest request) {
      return futureUnaryCall(
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
        case METHODID_PARAMS:
          serviceImpl.params((kava.bep3.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<kava.bep3.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_ASSET_SUPPLY:
          serviceImpl.assetSupply((kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyRequest) request,
              (io.grpc.stub.StreamObserver<kava.bep3.v1beta1.QueryOuterClass.QueryAssetSupplyResponse>) responseObserver);
          break;
        case METHODID_ASSET_SUPPLIES:
          serviceImpl.assetSupplies((kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesRequest) request,
              (io.grpc.stub.StreamObserver<kava.bep3.v1beta1.QueryOuterClass.QueryAssetSuppliesResponse>) responseObserver);
          break;
        case METHODID_ATOMIC_SWAP:
          serviceImpl.atomicSwap((kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapRequest) request,
              (io.grpc.stub.StreamObserver<kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapResponse>) responseObserver);
          break;
        case METHODID_ATOMIC_SWAPS:
          serviceImpl.atomicSwaps((kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsRequest) request,
              (io.grpc.stub.StreamObserver<kava.bep3.v1beta1.QueryOuterClass.QueryAtomicSwapsResponse>) responseObserver);
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
      return kava.bep3.v1beta1.QueryOuterClass.getDescriptor();
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
