package rizonworld.rizon.tokenswap;

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
    comments = "Source: rizon/tokenswap/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "rizonworld.rizon.tokenswap.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapRequest,
      rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapResponse> getTokenswapMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Tokenswap",
      requestType = rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapRequest.class,
      responseType = rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapRequest,
      rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapResponse> getTokenswapMethod() {
    io.grpc.MethodDescriptor<rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapRequest, rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapResponse> getTokenswapMethod;
    if ((getTokenswapMethod = QueryGrpc.getTokenswapMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTokenswapMethod = QueryGrpc.getTokenswapMethod) == null) {
          QueryGrpc.getTokenswapMethod = getTokenswapMethod =
              io.grpc.MethodDescriptor.<rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapRequest, rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Tokenswap"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Tokenswap"))
              .build();
        }
      }
    }
    return getTokenswapMethod;
  }

  private static volatile io.grpc.MethodDescriptor<rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountRequest,
      rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountResponse> getSwappedAmountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SwappedAmount",
      requestType = rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountRequest.class,
      responseType = rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountRequest,
      rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountResponse> getSwappedAmountMethod() {
    io.grpc.MethodDescriptor<rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountRequest, rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountResponse> getSwappedAmountMethod;
    if ((getSwappedAmountMethod = QueryGrpc.getSwappedAmountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSwappedAmountMethod = QueryGrpc.getSwappedAmountMethod) == null) {
          QueryGrpc.getSwappedAmountMethod = getSwappedAmountMethod =
              io.grpc.MethodDescriptor.<rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountRequest, rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SwappedAmount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SwappedAmount"))
              .build();
        }
      }
    }
    return getSwappedAmountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsRequest,
      rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsRequest.class,
      responseType = rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsRequest,
      rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsRequest, rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsRequest, rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
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
     * Tokenswap queries tokenswap item for given tx hash
     * </pre>
     */
    public void tokenswap(rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTokenswapMethod(), responseObserver);
    }

    /**
     * <pre>
     * SwappedAmount queries current swapped amount of tokenswap
     * </pre>
     */
    public void swappedAmount(rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwappedAmountMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries parameters of tokenswap
     * </pre>
     */
    public void params(rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getTokenswapMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapRequest,
                rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapResponse>(
                  this, METHODID_TOKENSWAP)))
          .addMethod(
            getSwappedAmountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountRequest,
                rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountResponse>(
                  this, METHODID_SWAPPED_AMOUNT)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsRequest,
                rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsResponse>(
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
     * Tokenswap queries tokenswap item for given tx hash
     * </pre>
     */
    public void tokenswap(rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTokenswapMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SwappedAmount queries current swapped amount of tokenswap
     * </pre>
     */
    public void swappedAmount(rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwappedAmountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries parameters of tokenswap
     * </pre>
     */
    public void params(rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsResponse> responseObserver) {
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
     * Tokenswap queries tokenswap item for given tx hash
     * </pre>
     */
    public rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapResponse tokenswap(rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapRequest request) {
      return blockingUnaryCall(
          getChannel(), getTokenswapMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SwappedAmount queries current swapped amount of tokenswap
     * </pre>
     */
    public rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountResponse swappedAmount(rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountRequest request) {
      return blockingUnaryCall(
          getChannel(), getSwappedAmountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries parameters of tokenswap
     * </pre>
     */
    public rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsResponse params(rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsRequest request) {
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
     * Tokenswap queries tokenswap item for given tx hash
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapResponse> tokenswap(
        rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTokenswapMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SwappedAmount queries current swapped amount of tokenswap
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountResponse> swappedAmount(
        rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSwappedAmountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries parameters of tokenswap
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsResponse> params(
        rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TOKENSWAP = 0;
  private static final int METHODID_SWAPPED_AMOUNT = 1;
  private static final int METHODID_PARAMS = 2;

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
        case METHODID_TOKENSWAP:
          serviceImpl.tokenswap((rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapRequest) request,
              (io.grpc.stub.StreamObserver<rizonworld.rizon.tokenswap.QueryOuterClass.QueryTokenswapResponse>) responseObserver);
          break;
        case METHODID_SWAPPED_AMOUNT:
          serviceImpl.swappedAmount((rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountRequest) request,
              (io.grpc.stub.StreamObserver<rizonworld.rizon.tokenswap.QueryOuterClass.QuerySwappedAmountResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<rizonworld.rizon.tokenswap.QueryOuterClass.QueryParamsResponse>) responseObserver);
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
      return rizonworld.rizon.tokenswap.QueryOuterClass.getDescriptor();
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
              .addMethod(getTokenswapMethod())
              .addMethod(getSwappedAmountMethod())
              .addMethod(getParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
