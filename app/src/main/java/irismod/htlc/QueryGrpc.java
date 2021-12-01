package irismod.htlc;

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
 * Query provides defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: iris_mod/htlc/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "irismod.htlc.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<irismod.htlc.QueryOuterClass.QueryHTLCRequest,
      irismod.htlc.QueryOuterClass.QueryHTLCResponse> getHTLCMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HTLC",
      requestType = irismod.htlc.QueryOuterClass.QueryHTLCRequest.class,
      responseType = irismod.htlc.QueryOuterClass.QueryHTLCResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.htlc.QueryOuterClass.QueryHTLCRequest,
      irismod.htlc.QueryOuterClass.QueryHTLCResponse> getHTLCMethod() {
    io.grpc.MethodDescriptor<irismod.htlc.QueryOuterClass.QueryHTLCRequest, irismod.htlc.QueryOuterClass.QueryHTLCResponse> getHTLCMethod;
    if ((getHTLCMethod = QueryGrpc.getHTLCMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHTLCMethod = QueryGrpc.getHTLCMethod) == null) {
          QueryGrpc.getHTLCMethod = getHTLCMethod =
              io.grpc.MethodDescriptor.<irismod.htlc.QueryOuterClass.QueryHTLCRequest, irismod.htlc.QueryOuterClass.QueryHTLCResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HTLC"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.htlc.QueryOuterClass.QueryHTLCRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.htlc.QueryOuterClass.QueryHTLCResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("HTLC"))
              .build();
        }
      }
    }
    return getHTLCMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.htlc.QueryOuterClass.QueryAssetSupplyRequest,
      irismod.htlc.QueryOuterClass.QueryAssetSupplyResponse> getAssetSupplyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AssetSupply",
      requestType = irismod.htlc.QueryOuterClass.QueryAssetSupplyRequest.class,
      responseType = irismod.htlc.QueryOuterClass.QueryAssetSupplyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.htlc.QueryOuterClass.QueryAssetSupplyRequest,
      irismod.htlc.QueryOuterClass.QueryAssetSupplyResponse> getAssetSupplyMethod() {
    io.grpc.MethodDescriptor<irismod.htlc.QueryOuterClass.QueryAssetSupplyRequest, irismod.htlc.QueryOuterClass.QueryAssetSupplyResponse> getAssetSupplyMethod;
    if ((getAssetSupplyMethod = QueryGrpc.getAssetSupplyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAssetSupplyMethod = QueryGrpc.getAssetSupplyMethod) == null) {
          QueryGrpc.getAssetSupplyMethod = getAssetSupplyMethod =
              io.grpc.MethodDescriptor.<irismod.htlc.QueryOuterClass.QueryAssetSupplyRequest, irismod.htlc.QueryOuterClass.QueryAssetSupplyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AssetSupply"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.htlc.QueryOuterClass.QueryAssetSupplyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.htlc.QueryOuterClass.QueryAssetSupplyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AssetSupply"))
              .build();
        }
      }
    }
    return getAssetSupplyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.htlc.QueryOuterClass.QueryAssetSuppliesRequest,
      irismod.htlc.QueryOuterClass.QueryAssetSuppliesResponse> getAssetSuppliesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AssetSupplies",
      requestType = irismod.htlc.QueryOuterClass.QueryAssetSuppliesRequest.class,
      responseType = irismod.htlc.QueryOuterClass.QueryAssetSuppliesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.htlc.QueryOuterClass.QueryAssetSuppliesRequest,
      irismod.htlc.QueryOuterClass.QueryAssetSuppliesResponse> getAssetSuppliesMethod() {
    io.grpc.MethodDescriptor<irismod.htlc.QueryOuterClass.QueryAssetSuppliesRequest, irismod.htlc.QueryOuterClass.QueryAssetSuppliesResponse> getAssetSuppliesMethod;
    if ((getAssetSuppliesMethod = QueryGrpc.getAssetSuppliesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAssetSuppliesMethod = QueryGrpc.getAssetSuppliesMethod) == null) {
          QueryGrpc.getAssetSuppliesMethod = getAssetSuppliesMethod =
              io.grpc.MethodDescriptor.<irismod.htlc.QueryOuterClass.QueryAssetSuppliesRequest, irismod.htlc.QueryOuterClass.QueryAssetSuppliesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AssetSupplies"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.htlc.QueryOuterClass.QueryAssetSuppliesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.htlc.QueryOuterClass.QueryAssetSuppliesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AssetSupplies"))
              .build();
        }
      }
    }
    return getAssetSuppliesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.htlc.QueryOuterClass.QueryParamsRequest,
      irismod.htlc.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = irismod.htlc.QueryOuterClass.QueryParamsRequest.class,
      responseType = irismod.htlc.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.htlc.QueryOuterClass.QueryParamsRequest,
      irismod.htlc.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<irismod.htlc.QueryOuterClass.QueryParamsRequest, irismod.htlc.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<irismod.htlc.QueryOuterClass.QueryParamsRequest, irismod.htlc.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.htlc.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.htlc.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
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
   * Query provides defines the gRPC querier service
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * HTLC queries the HTLC by the specified hash lock
     * </pre>
     */
    public void hTLC(irismod.htlc.QueryOuterClass.QueryHTLCRequest request,
        io.grpc.stub.StreamObserver<irismod.htlc.QueryOuterClass.QueryHTLCResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHTLCMethod(), responseObserver);
    }

    /**
     * <pre>
     * AssetSupply queries the supply of an asset
     * </pre>
     */
    public void assetSupply(irismod.htlc.QueryOuterClass.QueryAssetSupplyRequest request,
        io.grpc.stub.StreamObserver<irismod.htlc.QueryOuterClass.QueryAssetSupplyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAssetSupplyMethod(), responseObserver);
    }

    /**
     * <pre>
     * AssetSupplies queries the supplies of all assets
     * </pre>
     */
    public void assetSupplies(irismod.htlc.QueryOuterClass.QueryAssetSuppliesRequest request,
        io.grpc.stub.StreamObserver<irismod.htlc.QueryOuterClass.QueryAssetSuppliesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAssetSuppliesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries the htlc parameters
     * </pre>
     */
    public void params(irismod.htlc.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<irismod.htlc.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getHTLCMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.htlc.QueryOuterClass.QueryHTLCRequest,
                irismod.htlc.QueryOuterClass.QueryHTLCResponse>(
                  this, METHODID_HTLC)))
          .addMethod(
            getAssetSupplyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.htlc.QueryOuterClass.QueryAssetSupplyRequest,
                irismod.htlc.QueryOuterClass.QueryAssetSupplyResponse>(
                  this, METHODID_ASSET_SUPPLY)))
          .addMethod(
            getAssetSuppliesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.htlc.QueryOuterClass.QueryAssetSuppliesRequest,
                irismod.htlc.QueryOuterClass.QueryAssetSuppliesResponse>(
                  this, METHODID_ASSET_SUPPLIES)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.htlc.QueryOuterClass.QueryParamsRequest,
                irismod.htlc.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service
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
     * HTLC queries the HTLC by the specified hash lock
     * </pre>
     */
    public void hTLC(irismod.htlc.QueryOuterClass.QueryHTLCRequest request,
        io.grpc.stub.StreamObserver<irismod.htlc.QueryOuterClass.QueryHTLCResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHTLCMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AssetSupply queries the supply of an asset
     * </pre>
     */
    public void assetSupply(irismod.htlc.QueryOuterClass.QueryAssetSupplyRequest request,
        io.grpc.stub.StreamObserver<irismod.htlc.QueryOuterClass.QueryAssetSupplyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAssetSupplyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AssetSupplies queries the supplies of all assets
     * </pre>
     */
    public void assetSupplies(irismod.htlc.QueryOuterClass.QueryAssetSuppliesRequest request,
        io.grpc.stub.StreamObserver<irismod.htlc.QueryOuterClass.QueryAssetSuppliesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAssetSuppliesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries the htlc parameters
     * </pre>
     */
    public void params(irismod.htlc.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<irismod.htlc.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service
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
     * HTLC queries the HTLC by the specified hash lock
     * </pre>
     */
    public irismod.htlc.QueryOuterClass.QueryHTLCResponse hTLC(irismod.htlc.QueryOuterClass.QueryHTLCRequest request) {
      return blockingUnaryCall(
          getChannel(), getHTLCMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AssetSupply queries the supply of an asset
     * </pre>
     */
    public irismod.htlc.QueryOuterClass.QueryAssetSupplyResponse assetSupply(irismod.htlc.QueryOuterClass.QueryAssetSupplyRequest request) {
      return blockingUnaryCall(
          getChannel(), getAssetSupplyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AssetSupplies queries the supplies of all assets
     * </pre>
     */
    public irismod.htlc.QueryOuterClass.QueryAssetSuppliesResponse assetSupplies(irismod.htlc.QueryOuterClass.QueryAssetSuppliesRequest request) {
      return blockingUnaryCall(
          getChannel(), getAssetSuppliesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries the htlc parameters
     * </pre>
     */
    public irismod.htlc.QueryOuterClass.QueryParamsResponse params(irismod.htlc.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service
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
     * HTLC queries the HTLC by the specified hash lock
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.htlc.QueryOuterClass.QueryHTLCResponse> hTLC(
        irismod.htlc.QueryOuterClass.QueryHTLCRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHTLCMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AssetSupply queries the supply of an asset
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.htlc.QueryOuterClass.QueryAssetSupplyResponse> assetSupply(
        irismod.htlc.QueryOuterClass.QueryAssetSupplyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAssetSupplyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AssetSupplies queries the supplies of all assets
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.htlc.QueryOuterClass.QueryAssetSuppliesResponse> assetSupplies(
        irismod.htlc.QueryOuterClass.QueryAssetSuppliesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAssetSuppliesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries the htlc parameters
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.htlc.QueryOuterClass.QueryParamsResponse> params(
        irismod.htlc.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HTLC = 0;
  private static final int METHODID_ASSET_SUPPLY = 1;
  private static final int METHODID_ASSET_SUPPLIES = 2;
  private static final int METHODID_PARAMS = 3;

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
        case METHODID_HTLC:
          serviceImpl.hTLC((irismod.htlc.QueryOuterClass.QueryHTLCRequest) request,
              (io.grpc.stub.StreamObserver<irismod.htlc.QueryOuterClass.QueryHTLCResponse>) responseObserver);
          break;
        case METHODID_ASSET_SUPPLY:
          serviceImpl.assetSupply((irismod.htlc.QueryOuterClass.QueryAssetSupplyRequest) request,
              (io.grpc.stub.StreamObserver<irismod.htlc.QueryOuterClass.QueryAssetSupplyResponse>) responseObserver);
          break;
        case METHODID_ASSET_SUPPLIES:
          serviceImpl.assetSupplies((irismod.htlc.QueryOuterClass.QueryAssetSuppliesRequest) request,
              (io.grpc.stub.StreamObserver<irismod.htlc.QueryOuterClass.QueryAssetSuppliesResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((irismod.htlc.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<irismod.htlc.QueryOuterClass.QueryParamsResponse>) responseObserver);
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
      return irismod.htlc.QueryOuterClass.getDescriptor();
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
              .addMethod(getHTLCMethod())
              .addMethod(getAssetSupplyMethod())
              .addMethod(getAssetSuppliesMethod())
              .addMethod(getParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
