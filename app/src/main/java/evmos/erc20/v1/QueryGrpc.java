package evmos.erc20.v1;

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
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: evmos/erc20/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "evmos.erc20.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<evmos.erc20.v1.QueryOuterClass.QueryTokenPairsRequest,
      evmos.erc20.v1.QueryOuterClass.QueryTokenPairsResponse> getTokenPairsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TokenPairs",
      requestType = evmos.erc20.v1.QueryOuterClass.QueryTokenPairsRequest.class,
      responseType = evmos.erc20.v1.QueryOuterClass.QueryTokenPairsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<evmos.erc20.v1.QueryOuterClass.QueryTokenPairsRequest,
      evmos.erc20.v1.QueryOuterClass.QueryTokenPairsResponse> getTokenPairsMethod() {
    io.grpc.MethodDescriptor<evmos.erc20.v1.QueryOuterClass.QueryTokenPairsRequest, evmos.erc20.v1.QueryOuterClass.QueryTokenPairsResponse> getTokenPairsMethod;
    if ((getTokenPairsMethod = QueryGrpc.getTokenPairsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTokenPairsMethod = QueryGrpc.getTokenPairsMethod) == null) {
          QueryGrpc.getTokenPairsMethod = getTokenPairsMethod =
              io.grpc.MethodDescriptor.<evmos.erc20.v1.QueryOuterClass.QueryTokenPairsRequest, evmos.erc20.v1.QueryOuterClass.QueryTokenPairsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TokenPairs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.erc20.v1.QueryOuterClass.QueryTokenPairsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.erc20.v1.QueryOuterClass.QueryTokenPairsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TokenPairs"))
              .build();
        }
      }
    }
    return getTokenPairsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<evmos.erc20.v1.QueryOuterClass.QueryTokenPairRequest,
      evmos.erc20.v1.QueryOuterClass.QueryTokenPairResponse> getTokenPairMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TokenPair",
      requestType = evmos.erc20.v1.QueryOuterClass.QueryTokenPairRequest.class,
      responseType = evmos.erc20.v1.QueryOuterClass.QueryTokenPairResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<evmos.erc20.v1.QueryOuterClass.QueryTokenPairRequest,
      evmos.erc20.v1.QueryOuterClass.QueryTokenPairResponse> getTokenPairMethod() {
    io.grpc.MethodDescriptor<evmos.erc20.v1.QueryOuterClass.QueryTokenPairRequest, evmos.erc20.v1.QueryOuterClass.QueryTokenPairResponse> getTokenPairMethod;
    if ((getTokenPairMethod = QueryGrpc.getTokenPairMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTokenPairMethod = QueryGrpc.getTokenPairMethod) == null) {
          QueryGrpc.getTokenPairMethod = getTokenPairMethod =
              io.grpc.MethodDescriptor.<evmos.erc20.v1.QueryOuterClass.QueryTokenPairRequest, evmos.erc20.v1.QueryOuterClass.QueryTokenPairResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TokenPair"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.erc20.v1.QueryOuterClass.QueryTokenPairRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.erc20.v1.QueryOuterClass.QueryTokenPairResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TokenPair"))
              .build();
        }
      }
    }
    return getTokenPairMethod;
  }

  private static volatile io.grpc.MethodDescriptor<evmos.erc20.v1.QueryOuterClass.QueryParamsRequest,
      evmos.erc20.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = evmos.erc20.v1.QueryOuterClass.QueryParamsRequest.class,
      responseType = evmos.erc20.v1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<evmos.erc20.v1.QueryOuterClass.QueryParamsRequest,
      evmos.erc20.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<evmos.erc20.v1.QueryOuterClass.QueryParamsRequest, evmos.erc20.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<evmos.erc20.v1.QueryOuterClass.QueryParamsRequest, evmos.erc20.v1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.erc20.v1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.erc20.v1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
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
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Retrieves registered token pairs
     * </pre>
     */
    public void tokenPairs(evmos.erc20.v1.QueryOuterClass.QueryTokenPairsRequest request,
        io.grpc.stub.StreamObserver<evmos.erc20.v1.QueryOuterClass.QueryTokenPairsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTokenPairsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves a registered token pair
     * </pre>
     */
    public void tokenPair(evmos.erc20.v1.QueryOuterClass.QueryTokenPairRequest request,
        io.grpc.stub.StreamObserver<evmos.erc20.v1.QueryOuterClass.QueryTokenPairResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTokenPairMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params retrieves the erc20 module params
     * </pre>
     */
    public void params(evmos.erc20.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<evmos.erc20.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getTokenPairsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                evmos.erc20.v1.QueryOuterClass.QueryTokenPairsRequest,
                evmos.erc20.v1.QueryOuterClass.QueryTokenPairsResponse>(
                  this, METHODID_TOKEN_PAIRS)))
          .addMethod(
            getTokenPairMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                evmos.erc20.v1.QueryOuterClass.QueryTokenPairRequest,
                evmos.erc20.v1.QueryOuterClass.QueryTokenPairResponse>(
                  this, METHODID_TOKEN_PAIR)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                evmos.erc20.v1.QueryOuterClass.QueryParamsRequest,
                evmos.erc20.v1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * Retrieves registered token pairs
     * </pre>
     */
    public void tokenPairs(evmos.erc20.v1.QueryOuterClass.QueryTokenPairsRequest request,
        io.grpc.stub.StreamObserver<evmos.erc20.v1.QueryOuterClass.QueryTokenPairsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTokenPairsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves a registered token pair
     * </pre>
     */
    public void tokenPair(evmos.erc20.v1.QueryOuterClass.QueryTokenPairRequest request,
        io.grpc.stub.StreamObserver<evmos.erc20.v1.QueryOuterClass.QueryTokenPairResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTokenPairMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params retrieves the erc20 module params
     * </pre>
     */
    public void params(evmos.erc20.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<evmos.erc20.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * Retrieves registered token pairs
     * </pre>
     */
    public evmos.erc20.v1.QueryOuterClass.QueryTokenPairsResponse tokenPairs(evmos.erc20.v1.QueryOuterClass.QueryTokenPairsRequest request) {
      return blockingUnaryCall(
          getChannel(), getTokenPairsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves a registered token pair
     * </pre>
     */
    public evmos.erc20.v1.QueryOuterClass.QueryTokenPairResponse tokenPair(evmos.erc20.v1.QueryOuterClass.QueryTokenPairRequest request) {
      return blockingUnaryCall(
          getChannel(), getTokenPairMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params retrieves the erc20 module params
     * </pre>
     */
    public evmos.erc20.v1.QueryOuterClass.QueryParamsResponse params(evmos.erc20.v1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * Retrieves registered token pairs
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<evmos.erc20.v1.QueryOuterClass.QueryTokenPairsResponse> tokenPairs(
        evmos.erc20.v1.QueryOuterClass.QueryTokenPairsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTokenPairsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves a registered token pair
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<evmos.erc20.v1.QueryOuterClass.QueryTokenPairResponse> tokenPair(
        evmos.erc20.v1.QueryOuterClass.QueryTokenPairRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTokenPairMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params retrieves the erc20 module params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<evmos.erc20.v1.QueryOuterClass.QueryParamsResponse> params(
        evmos.erc20.v1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TOKEN_PAIRS = 0;
  private static final int METHODID_TOKEN_PAIR = 1;
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
        case METHODID_TOKEN_PAIRS:
          serviceImpl.tokenPairs((evmos.erc20.v1.QueryOuterClass.QueryTokenPairsRequest) request,
              (io.grpc.stub.StreamObserver<evmos.erc20.v1.QueryOuterClass.QueryTokenPairsResponse>) responseObserver);
          break;
        case METHODID_TOKEN_PAIR:
          serviceImpl.tokenPair((evmos.erc20.v1.QueryOuterClass.QueryTokenPairRequest) request,
              (io.grpc.stub.StreamObserver<evmos.erc20.v1.QueryOuterClass.QueryTokenPairResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((evmos.erc20.v1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<evmos.erc20.v1.QueryOuterClass.QueryParamsResponse>) responseObserver);
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
      return evmos.erc20.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getTokenPairsMethod())
              .addMethod(getTokenPairMethod())
              .addMethod(getParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
