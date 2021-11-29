package rizonworld.rizon.treasury;

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
 * Query defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: treasury/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "rizonworld.rizon.treasury.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesRequest,
      rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesResponse> getCurrenciesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Currencies",
      requestType = rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesRequest.class,
      responseType = rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesRequest,
      rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesResponse> getCurrenciesMethod() {
    io.grpc.MethodDescriptor<rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesRequest, rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesResponse> getCurrenciesMethod;
    if ((getCurrenciesMethod = QueryGrpc.getCurrenciesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCurrenciesMethod = QueryGrpc.getCurrenciesMethod) == null) {
          QueryGrpc.getCurrenciesMethod = getCurrenciesMethod =
              io.grpc.MethodDescriptor.<rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesRequest, rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Currencies"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Currencies"))
              .build();
        }
      }
    }
    return getCurrenciesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyRequest,
      rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyResponse> getCurrencyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Currency",
      requestType = rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyRequest.class,
      responseType = rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyRequest,
      rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyResponse> getCurrencyMethod() {
    io.grpc.MethodDescriptor<rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyRequest, rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyResponse> getCurrencyMethod;
    if ((getCurrencyMethod = QueryGrpc.getCurrencyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCurrencyMethod = QueryGrpc.getCurrencyMethod) == null) {
          QueryGrpc.getCurrencyMethod = getCurrencyMethod =
              io.grpc.MethodDescriptor.<rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyRequest, rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Currency"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Currency"))
              .build();
        }
      }
    }
    return getCurrencyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<rizonworld.rizon.treasury.QueryOuterClass.QueryMaxRequest,
      rizonworld.rizon.treasury.QueryOuterClass.QueryMaxResponse> getMaxAtoloSupplyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MaxAtoloSupply",
      requestType = rizonworld.rizon.treasury.QueryOuterClass.QueryMaxRequest.class,
      responseType = rizonworld.rizon.treasury.QueryOuterClass.QueryMaxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rizonworld.rizon.treasury.QueryOuterClass.QueryMaxRequest,
      rizonworld.rizon.treasury.QueryOuterClass.QueryMaxResponse> getMaxAtoloSupplyMethod() {
    io.grpc.MethodDescriptor<rizonworld.rizon.treasury.QueryOuterClass.QueryMaxRequest, rizonworld.rizon.treasury.QueryOuterClass.QueryMaxResponse> getMaxAtoloSupplyMethod;
    if ((getMaxAtoloSupplyMethod = QueryGrpc.getMaxAtoloSupplyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getMaxAtoloSupplyMethod = QueryGrpc.getMaxAtoloSupplyMethod) == null) {
          QueryGrpc.getMaxAtoloSupplyMethod = getMaxAtoloSupplyMethod =
              io.grpc.MethodDescriptor.<rizonworld.rizon.treasury.QueryOuterClass.QueryMaxRequest, rizonworld.rizon.treasury.QueryOuterClass.QueryMaxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MaxAtoloSupply"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.treasury.QueryOuterClass.QueryMaxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.treasury.QueryOuterClass.QueryMaxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("MaxAtoloSupply"))
              .build();
        }
      }
    }
    return getMaxAtoloSupplyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<rizonworld.rizon.treasury.QueryOuterClass.QueryParamsRequest,
      rizonworld.rizon.treasury.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = rizonworld.rizon.treasury.QueryOuterClass.QueryParamsRequest.class,
      responseType = rizonworld.rizon.treasury.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rizonworld.rizon.treasury.QueryOuterClass.QueryParamsRequest,
      rizonworld.rizon.treasury.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<rizonworld.rizon.treasury.QueryOuterClass.QueryParamsRequest, rizonworld.rizon.treasury.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<rizonworld.rizon.treasury.QueryOuterClass.QueryParamsRequest, rizonworld.rizon.treasury.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.treasury.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.treasury.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
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
   * Query defines the gRPC querier service
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Currencies queries all supported currency denom list
     * </pre>
     */
    public void currencies(rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCurrenciesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Currency queries a currency info
     * </pre>
     */
    public void currency(rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCurrencyMethod(), responseObserver);
    }

    /**
     * <pre>
     * MaxAtoloSupply queries maximum mintable amount of uatolo
     * </pre>
     */
    public void maxAtoloSupply(rizonworld.rizon.treasury.QueryOuterClass.QueryMaxRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.QueryOuterClass.QueryMaxResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMaxAtoloSupplyMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries parameters of treasury
     * </pre>
     */
    public void params(rizonworld.rizon.treasury.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCurrenciesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesRequest,
                rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesResponse>(
                  this, METHODID_CURRENCIES)))
          .addMethod(
            getCurrencyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyRequest,
                rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyResponse>(
                  this, METHODID_CURRENCY)))
          .addMethod(
            getMaxAtoloSupplyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rizonworld.rizon.treasury.QueryOuterClass.QueryMaxRequest,
                rizonworld.rizon.treasury.QueryOuterClass.QueryMaxResponse>(
                  this, METHODID_MAX_ATOLO_SUPPLY)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rizonworld.rizon.treasury.QueryOuterClass.QueryParamsRequest,
                rizonworld.rizon.treasury.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     * Currencies queries all supported currency denom list
     * </pre>
     */
    public void currencies(rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCurrenciesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Currency queries a currency info
     * </pre>
     */
    public void currency(rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCurrencyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MaxAtoloSupply queries maximum mintable amount of uatolo
     * </pre>
     */
    public void maxAtoloSupply(rizonworld.rizon.treasury.QueryOuterClass.QueryMaxRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.QueryOuterClass.QueryMaxResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMaxAtoloSupplyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries parameters of treasury
     * </pre>
     */
    public void params(rizonworld.rizon.treasury.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     * Currencies queries all supported currency denom list
     * </pre>
     */
    public rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesResponse currencies(rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesRequest request) {
      return blockingUnaryCall(
          getChannel(), getCurrenciesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Currency queries a currency info
     * </pre>
     */
    public rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyResponse currency(rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyRequest request) {
      return blockingUnaryCall(
          getChannel(), getCurrencyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MaxAtoloSupply queries maximum mintable amount of uatolo
     * </pre>
     */
    public rizonworld.rizon.treasury.QueryOuterClass.QueryMaxResponse maxAtoloSupply(rizonworld.rizon.treasury.QueryOuterClass.QueryMaxRequest request) {
      return blockingUnaryCall(
          getChannel(), getMaxAtoloSupplyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries parameters of treasury
     * </pre>
     */
    public rizonworld.rizon.treasury.QueryOuterClass.QueryParamsResponse params(rizonworld.rizon.treasury.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     * Currencies queries all supported currency denom list
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesResponse> currencies(
        rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCurrenciesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Currency queries a currency info
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyResponse> currency(
        rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCurrencyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MaxAtoloSupply queries maximum mintable amount of uatolo
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rizonworld.rizon.treasury.QueryOuterClass.QueryMaxResponse> maxAtoloSupply(
        rizonworld.rizon.treasury.QueryOuterClass.QueryMaxRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMaxAtoloSupplyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries parameters of treasury
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rizonworld.rizon.treasury.QueryOuterClass.QueryParamsResponse> params(
        rizonworld.rizon.treasury.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CURRENCIES = 0;
  private static final int METHODID_CURRENCY = 1;
  private static final int METHODID_MAX_ATOLO_SUPPLY = 2;
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
        case METHODID_CURRENCIES:
          serviceImpl.currencies((rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesRequest) request,
              (io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.QueryOuterClass.QueryCurrenciesResponse>) responseObserver);
          break;
        case METHODID_CURRENCY:
          serviceImpl.currency((rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyRequest) request,
              (io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.QueryOuterClass.QueryCurrencyResponse>) responseObserver);
          break;
        case METHODID_MAX_ATOLO_SUPPLY:
          serviceImpl.maxAtoloSupply((rizonworld.rizon.treasury.QueryOuterClass.QueryMaxRequest) request,
              (io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.QueryOuterClass.QueryMaxResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((rizonworld.rizon.treasury.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.QueryOuterClass.QueryParamsResponse>) responseObserver);
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
      return rizonworld.rizon.treasury.QueryOuterClass.getDescriptor();
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
              .addMethod(getCurrenciesMethod())
              .addMethod(getCurrencyMethod())
              .addMethod(getMaxAtoloSupplyMethod())
              .addMethod(getParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
