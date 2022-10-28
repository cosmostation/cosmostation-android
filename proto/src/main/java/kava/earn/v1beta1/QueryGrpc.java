package kava.earn.v1beta1;

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
 * Query defines the gRPC querier service for earn module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/earn/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "kava.earn.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.earn.v1beta1.QueryOuterClass.QueryParamsRequest,
      kava.earn.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = kava.earn.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = kava.earn.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.earn.v1beta1.QueryOuterClass.QueryParamsRequest,
      kava.earn.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<kava.earn.v1beta1.QueryOuterClass.QueryParamsRequest, kava.earn.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<kava.earn.v1beta1.QueryOuterClass.QueryParamsRequest, kava.earn.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.earn.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.earn.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.earn.v1beta1.QueryOuterClass.QueryVaultsRequest,
      kava.earn.v1beta1.QueryOuterClass.QueryVaultsResponse> getVaultsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Vaults",
      requestType = kava.earn.v1beta1.QueryOuterClass.QueryVaultsRequest.class,
      responseType = kava.earn.v1beta1.QueryOuterClass.QueryVaultsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.earn.v1beta1.QueryOuterClass.QueryVaultsRequest,
      kava.earn.v1beta1.QueryOuterClass.QueryVaultsResponse> getVaultsMethod() {
    io.grpc.MethodDescriptor<kava.earn.v1beta1.QueryOuterClass.QueryVaultsRequest, kava.earn.v1beta1.QueryOuterClass.QueryVaultsResponse> getVaultsMethod;
    if ((getVaultsMethod = QueryGrpc.getVaultsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getVaultsMethod = QueryGrpc.getVaultsMethod) == null) {
          QueryGrpc.getVaultsMethod = getVaultsMethod =
              io.grpc.MethodDescriptor.<kava.earn.v1beta1.QueryOuterClass.QueryVaultsRequest, kava.earn.v1beta1.QueryOuterClass.QueryVaultsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Vaults"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.earn.v1beta1.QueryOuterClass.QueryVaultsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.earn.v1beta1.QueryOuterClass.QueryVaultsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Vaults"))
              .build();
        }
      }
    }
    return getVaultsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.earn.v1beta1.QueryOuterClass.QueryVaultRequest,
      kava.earn.v1beta1.QueryOuterClass.QueryVaultResponse> getVaultMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Vault",
      requestType = kava.earn.v1beta1.QueryOuterClass.QueryVaultRequest.class,
      responseType = kava.earn.v1beta1.QueryOuterClass.QueryVaultResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.earn.v1beta1.QueryOuterClass.QueryVaultRequest,
      kava.earn.v1beta1.QueryOuterClass.QueryVaultResponse> getVaultMethod() {
    io.grpc.MethodDescriptor<kava.earn.v1beta1.QueryOuterClass.QueryVaultRequest, kava.earn.v1beta1.QueryOuterClass.QueryVaultResponse> getVaultMethod;
    if ((getVaultMethod = QueryGrpc.getVaultMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getVaultMethod = QueryGrpc.getVaultMethod) == null) {
          QueryGrpc.getVaultMethod = getVaultMethod =
              io.grpc.MethodDescriptor.<kava.earn.v1beta1.QueryOuterClass.QueryVaultRequest, kava.earn.v1beta1.QueryOuterClass.QueryVaultResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Vault"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.earn.v1beta1.QueryOuterClass.QueryVaultRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.earn.v1beta1.QueryOuterClass.QueryVaultResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Vault"))
              .build();
        }
      }
    }
    return getVaultMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.earn.v1beta1.QueryOuterClass.QueryDepositsRequest,
      kava.earn.v1beta1.QueryOuterClass.QueryDepositsResponse> getDepositsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposits",
      requestType = kava.earn.v1beta1.QueryOuterClass.QueryDepositsRequest.class,
      responseType = kava.earn.v1beta1.QueryOuterClass.QueryDepositsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.earn.v1beta1.QueryOuterClass.QueryDepositsRequest,
      kava.earn.v1beta1.QueryOuterClass.QueryDepositsResponse> getDepositsMethod() {
    io.grpc.MethodDescriptor<kava.earn.v1beta1.QueryOuterClass.QueryDepositsRequest, kava.earn.v1beta1.QueryOuterClass.QueryDepositsResponse> getDepositsMethod;
    if ((getDepositsMethod = QueryGrpc.getDepositsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositsMethod = QueryGrpc.getDepositsMethod) == null) {
          QueryGrpc.getDepositsMethod = getDepositsMethod =
              io.grpc.MethodDescriptor.<kava.earn.v1beta1.QueryOuterClass.QueryDepositsRequest, kava.earn.v1beta1.QueryOuterClass.QueryDepositsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposits"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.earn.v1beta1.QueryOuterClass.QueryDepositsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.earn.v1beta1.QueryOuterClass.QueryDepositsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Deposits"))
              .build();
        }
      }
    }
    return getDepositsMethod;
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
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Params queries all parameters of the earn module.
     * </pre>
     */
    public void params(kava.earn.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<kava.earn.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Vaults queries all vaults
     * </pre>
     */
    public void vaults(kava.earn.v1beta1.QueryOuterClass.QueryVaultsRequest request,
        io.grpc.stub.StreamObserver<kava.earn.v1beta1.QueryOuterClass.QueryVaultsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVaultsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Vault queries a single vault based on the vault denom
     * </pre>
     */
    public void vault(kava.earn.v1beta1.QueryOuterClass.QueryVaultRequest request,
        io.grpc.stub.StreamObserver<kava.earn.v1beta1.QueryOuterClass.QueryVaultResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVaultMethod(), responseObserver);
    }

    /**
     * <pre>
     * Deposits queries deposit details based on depositor address and vault
     * </pre>
     */
    public void deposits(kava.earn.v1beta1.QueryOuterClass.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<kava.earn.v1beta1.QueryOuterClass.QueryDepositsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.earn.v1beta1.QueryOuterClass.QueryParamsRequest,
                kava.earn.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getVaultsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.earn.v1beta1.QueryOuterClass.QueryVaultsRequest,
                kava.earn.v1beta1.QueryOuterClass.QueryVaultsResponse>(
                  this, METHODID_VAULTS)))
          .addMethod(
            getVaultMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.earn.v1beta1.QueryOuterClass.QueryVaultRequest,
                kava.earn.v1beta1.QueryOuterClass.QueryVaultResponse>(
                  this, METHODID_VAULT)))
          .addMethod(
            getDepositsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.earn.v1beta1.QueryOuterClass.QueryDepositsRequest,
                kava.earn.v1beta1.QueryOuterClass.QueryDepositsResponse>(
                  this, METHODID_DEPOSITS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for earn module
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
     * Params queries all parameters of the earn module.
     * </pre>
     */
    public void params(kava.earn.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<kava.earn.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Vaults queries all vaults
     * </pre>
     */
    public void vaults(kava.earn.v1beta1.QueryOuterClass.QueryVaultsRequest request,
        io.grpc.stub.StreamObserver<kava.earn.v1beta1.QueryOuterClass.QueryVaultsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVaultsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Vault queries a single vault based on the vault denom
     * </pre>
     */
    public void vault(kava.earn.v1beta1.QueryOuterClass.QueryVaultRequest request,
        io.grpc.stub.StreamObserver<kava.earn.v1beta1.QueryOuterClass.QueryVaultResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVaultMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Deposits queries deposit details based on depositor address and vault
     * </pre>
     */
    public void deposits(kava.earn.v1beta1.QueryOuterClass.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<kava.earn.v1beta1.QueryOuterClass.QueryDepositsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for earn module
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
     * Params queries all parameters of the earn module.
     * </pre>
     */
    public kava.earn.v1beta1.QueryOuterClass.QueryParamsResponse params(kava.earn.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Vaults queries all vaults
     * </pre>
     */
    public kava.earn.v1beta1.QueryOuterClass.QueryVaultsResponse vaults(kava.earn.v1beta1.QueryOuterClass.QueryVaultsRequest request) {
      return blockingUnaryCall(
          getChannel(), getVaultsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Vault queries a single vault based on the vault denom
     * </pre>
     */
    public kava.earn.v1beta1.QueryOuterClass.QueryVaultResponse vault(kava.earn.v1beta1.QueryOuterClass.QueryVaultRequest request) {
      return blockingUnaryCall(
          getChannel(), getVaultMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Deposits queries deposit details based on depositor address and vault
     * </pre>
     */
    public kava.earn.v1beta1.QueryOuterClass.QueryDepositsResponse deposits(kava.earn.v1beta1.QueryOuterClass.QueryDepositsRequest request) {
      return blockingUnaryCall(
          getChannel(), getDepositsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for earn module
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
     * Params queries all parameters of the earn module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.earn.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        kava.earn.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Vaults queries all vaults
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.earn.v1beta1.QueryOuterClass.QueryVaultsResponse> vaults(
        kava.earn.v1beta1.QueryOuterClass.QueryVaultsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVaultsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Vault queries a single vault based on the vault denom
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.earn.v1beta1.QueryOuterClass.QueryVaultResponse> vault(
        kava.earn.v1beta1.QueryOuterClass.QueryVaultRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVaultMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Deposits queries deposit details based on depositor address and vault
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.earn.v1beta1.QueryOuterClass.QueryDepositsResponse> deposits(
        kava.earn.v1beta1.QueryOuterClass.QueryDepositsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_VAULTS = 1;
  private static final int METHODID_VAULT = 2;
  private static final int METHODID_DEPOSITS = 3;

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
          serviceImpl.params((kava.earn.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<kava.earn.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_VAULTS:
          serviceImpl.vaults((kava.earn.v1beta1.QueryOuterClass.QueryVaultsRequest) request,
              (io.grpc.stub.StreamObserver<kava.earn.v1beta1.QueryOuterClass.QueryVaultsResponse>) responseObserver);
          break;
        case METHODID_VAULT:
          serviceImpl.vault((kava.earn.v1beta1.QueryOuterClass.QueryVaultRequest) request,
              (io.grpc.stub.StreamObserver<kava.earn.v1beta1.QueryOuterClass.QueryVaultResponse>) responseObserver);
          break;
        case METHODID_DEPOSITS:
          serviceImpl.deposits((kava.earn.v1beta1.QueryOuterClass.QueryDepositsRequest) request,
              (io.grpc.stub.StreamObserver<kava.earn.v1beta1.QueryOuterClass.QueryDepositsResponse>) responseObserver);
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
      return kava.earn.v1beta1.QueryOuterClass.getDescriptor();
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
              .build();
        }
      }
    }
    return result;
  }
}
