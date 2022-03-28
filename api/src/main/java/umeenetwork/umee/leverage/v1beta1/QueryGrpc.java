package umeenetwork.umee.leverage.v1beta1;

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
    comments = "Source: umee/leverage/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "umeenetwork.umee.leverage.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokens,
      umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokensResponse> getRegisteredTokensMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisteredTokens",
      requestType = umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokens.class,
      responseType = umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokensResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokens,
      umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokensResponse> getRegisteredTokensMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokens, umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokensResponse> getRegisteredTokensMethod;
    if ((getRegisteredTokensMethod = QueryGrpc.getRegisteredTokensMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRegisteredTokensMethod = QueryGrpc.getRegisteredTokensMethod) == null) {
          QueryGrpc.getRegisteredTokensMethod = getRegisteredTokensMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokens, umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokensResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisteredTokens"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokens.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokensResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RegisteredTokens"))
              .build();
        }
      }
    }
    return getRegisteredTokensMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsRequest,
      umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsRequest,
      umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsRequest, umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsRequest, umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedRequest,
      umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedResponse> getBorrowedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Borrowed",
      requestType = umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedRequest.class,
      responseType = umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedRequest,
      umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedResponse> getBorrowedMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedRequest, umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedResponse> getBorrowedMethod;
    if ((getBorrowedMethod = QueryGrpc.getBorrowedMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBorrowedMethod = QueryGrpc.getBorrowedMethod) == null) {
          QueryGrpc.getBorrowedMethod = getBorrowedMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedRequest, umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Borrowed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Borrowed"))
              .build();
        }
      }
    }
    return getBorrowedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountRequest,
      umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountResponse> getReserveAmountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReserveAmount",
      requestType = umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountRequest.class,
      responseType = umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountRequest,
      umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountResponse> getReserveAmountMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountRequest, umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountResponse> getReserveAmountMethod;
    if ((getReserveAmountMethod = QueryGrpc.getReserveAmountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getReserveAmountMethod = QueryGrpc.getReserveAmountMethod) == null) {
          QueryGrpc.getReserveAmountMethod = getReserveAmountMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountRequest, umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReserveAmount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ReserveAmount"))
              .build();
        }
      }
    }
    return getReserveAmountMethod;
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
     * RegisteredTokens queries for all the registered tokens.
     * </pre>
     */
    public void registeredTokens(umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokens request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokensResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisteredTokensMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries the parameters of the x/leverage module.
     * </pre>
     */
    public void params(umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Borrowed queries for the borrowed amount of a user by token denomination.
     * If the denomination is not supplied, the total for each borrowed token is
     * returned.
     * </pre>
     */
    public void borrowed(umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBorrowedMethod(), responseObserver);
    }

    /**
     * <pre>
     * ReserveAmount queries for the amount reserved of a specified denomination.
     * If the token is not valid, the reserved amount is zero.
     * </pre>
     */
    public void reserveAmount(umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReserveAmountMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisteredTokensMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokens,
                umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokensResponse>(
                  this, METHODID_REGISTERED_TOKENS)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsRequest,
                umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getBorrowedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedRequest,
                umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedResponse>(
                  this, METHODID_BORROWED)))
          .addMethod(
            getReserveAmountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountRequest,
                umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountResponse>(
                  this, METHODID_RESERVE_AMOUNT)))
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
     * RegisteredTokens queries for all the registered tokens.
     * </pre>
     */
    public void registeredTokens(umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokens request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokensResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisteredTokensMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries the parameters of the x/leverage module.
     * </pre>
     */
    public void params(umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Borrowed queries for the borrowed amount of a user by token denomination.
     * If the denomination is not supplied, the total for each borrowed token is
     * returned.
     * </pre>
     */
    public void borrowed(umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBorrowedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ReserveAmount queries for the amount reserved of a specified denomination.
     * If the token is not valid, the reserved amount is zero.
     * </pre>
     */
    public void reserveAmount(umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReserveAmountMethod(), getCallOptions()), request, responseObserver);
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
     * RegisteredTokens queries for all the registered tokens.
     * </pre>
     */
    public umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokensResponse registeredTokens(umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokens request) {
      return blockingUnaryCall(
          getChannel(), getRegisteredTokensMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries the parameters of the x/leverage module.
     * </pre>
     */
    public umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsResponse params(umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Borrowed queries for the borrowed amount of a user by token denomination.
     * If the denomination is not supplied, the total for each borrowed token is
     * returned.
     * </pre>
     */
    public umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedResponse borrowed(umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedRequest request) {
      return blockingUnaryCall(
          getChannel(), getBorrowedMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ReserveAmount queries for the amount reserved of a specified denomination.
     * If the token is not valid, the reserved amount is zero.
     * </pre>
     */
    public umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountResponse reserveAmount(umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountRequest request) {
      return blockingUnaryCall(
          getChannel(), getReserveAmountMethod(), getCallOptions(), request);
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
     * RegisteredTokens queries for all the registered tokens.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokensResponse> registeredTokens(
        umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokens request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisteredTokensMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries the parameters of the x/leverage module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Borrowed queries for the borrowed amount of a user by token denomination.
     * If the denomination is not supplied, the total for each borrowed token is
     * returned.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedResponse> borrowed(
        umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBorrowedMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ReserveAmount queries for the amount reserved of a specified denomination.
     * If the token is not valid, the reserved amount is zero.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountResponse> reserveAmount(
        umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getReserveAmountMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTERED_TOKENS = 0;
  private static final int METHODID_PARAMS = 1;
  private static final int METHODID_BORROWED = 2;
  private static final int METHODID_RESERVE_AMOUNT = 3;

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
        case METHODID_REGISTERED_TOKENS:
          serviceImpl.registeredTokens((umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokens) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryRegisteredTokensResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_BORROWED:
          serviceImpl.borrowed((umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryBorrowedResponse>) responseObserver);
          break;
        case METHODID_RESERVE_AMOUNT:
          serviceImpl.reserveAmount((umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.QueryOuterClass.QueryReserveAmountResponse>) responseObserver);
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
      return umeenetwork.umee.leverage.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getRegisteredTokensMethod())
              .addMethod(getParamsMethod())
              .addMethod(getBorrowedMethod())
              .addMethod(getReserveAmountMethod())
              .build();
        }
      }
    }
    return result;
  }
}
