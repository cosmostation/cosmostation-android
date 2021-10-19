package irismod.token;

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
 * Query creates service with token as RPC
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: token/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "irismod.token.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<irismod.token.QueryOuterClass.QueryTokenRequest,
      irismod.token.QueryOuterClass.QueryTokenResponse> getTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Token",
      requestType = irismod.token.QueryOuterClass.QueryTokenRequest.class,
      responseType = irismod.token.QueryOuterClass.QueryTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.token.QueryOuterClass.QueryTokenRequest,
      irismod.token.QueryOuterClass.QueryTokenResponse> getTokenMethod() {
    io.grpc.MethodDescriptor<irismod.token.QueryOuterClass.QueryTokenRequest, irismod.token.QueryOuterClass.QueryTokenResponse> getTokenMethod;
    if ((getTokenMethod = QueryGrpc.getTokenMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTokenMethod = QueryGrpc.getTokenMethod) == null) {
          QueryGrpc.getTokenMethod = getTokenMethod =
              io.grpc.MethodDescriptor.<irismod.token.QueryOuterClass.QueryTokenRequest, irismod.token.QueryOuterClass.QueryTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Token"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.QueryOuterClass.QueryTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.QueryOuterClass.QueryTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Token"))
              .build();
        }
      }
    }
    return getTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.token.QueryOuterClass.QueryTokensRequest,
      irismod.token.QueryOuterClass.QueryTokensResponse> getTokensMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Tokens",
      requestType = irismod.token.QueryOuterClass.QueryTokensRequest.class,
      responseType = irismod.token.QueryOuterClass.QueryTokensResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.token.QueryOuterClass.QueryTokensRequest,
      irismod.token.QueryOuterClass.QueryTokensResponse> getTokensMethod() {
    io.grpc.MethodDescriptor<irismod.token.QueryOuterClass.QueryTokensRequest, irismod.token.QueryOuterClass.QueryTokensResponse> getTokensMethod;
    if ((getTokensMethod = QueryGrpc.getTokensMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTokensMethod = QueryGrpc.getTokensMethod) == null) {
          QueryGrpc.getTokensMethod = getTokensMethod =
              io.grpc.MethodDescriptor.<irismod.token.QueryOuterClass.QueryTokensRequest, irismod.token.QueryOuterClass.QueryTokensResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Tokens"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.QueryOuterClass.QueryTokensRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.QueryOuterClass.QueryTokensResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Tokens"))
              .build();
        }
      }
    }
    return getTokensMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.token.QueryOuterClass.QueryFeesRequest,
      irismod.token.QueryOuterClass.QueryFeesResponse> getFeesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Fees",
      requestType = irismod.token.QueryOuterClass.QueryFeesRequest.class,
      responseType = irismod.token.QueryOuterClass.QueryFeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.token.QueryOuterClass.QueryFeesRequest,
      irismod.token.QueryOuterClass.QueryFeesResponse> getFeesMethod() {
    io.grpc.MethodDescriptor<irismod.token.QueryOuterClass.QueryFeesRequest, irismod.token.QueryOuterClass.QueryFeesResponse> getFeesMethod;
    if ((getFeesMethod = QueryGrpc.getFeesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFeesMethod = QueryGrpc.getFeesMethod) == null) {
          QueryGrpc.getFeesMethod = getFeesMethod =
              io.grpc.MethodDescriptor.<irismod.token.QueryOuterClass.QueryFeesRequest, irismod.token.QueryOuterClass.QueryFeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Fees"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.QueryOuterClass.QueryFeesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.QueryOuterClass.QueryFeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Fees"))
              .build();
        }
      }
    }
    return getFeesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.token.QueryOuterClass.QueryParamsRequest,
      irismod.token.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = irismod.token.QueryOuterClass.QueryParamsRequest.class,
      responseType = irismod.token.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.token.QueryOuterClass.QueryParamsRequest,
      irismod.token.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<irismod.token.QueryOuterClass.QueryParamsRequest, irismod.token.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<irismod.token.QueryOuterClass.QueryParamsRequest, irismod.token.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.token.QueryOuterClass.QueryTotalBurnRequest,
      irismod.token.QueryOuterClass.QueryTotalBurnResponse> getTotalBurnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalBurn",
      requestType = irismod.token.QueryOuterClass.QueryTotalBurnRequest.class,
      responseType = irismod.token.QueryOuterClass.QueryTotalBurnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.token.QueryOuterClass.QueryTotalBurnRequest,
      irismod.token.QueryOuterClass.QueryTotalBurnResponse> getTotalBurnMethod() {
    io.grpc.MethodDescriptor<irismod.token.QueryOuterClass.QueryTotalBurnRequest, irismod.token.QueryOuterClass.QueryTotalBurnResponse> getTotalBurnMethod;
    if ((getTotalBurnMethod = QueryGrpc.getTotalBurnMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalBurnMethod = QueryGrpc.getTotalBurnMethod) == null) {
          QueryGrpc.getTotalBurnMethod = getTotalBurnMethod =
              io.grpc.MethodDescriptor.<irismod.token.QueryOuterClass.QueryTotalBurnRequest, irismod.token.QueryOuterClass.QueryTotalBurnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalBurn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.QueryOuterClass.QueryTotalBurnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.QueryOuterClass.QueryTotalBurnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalBurn"))
              .build();
        }
      }
    }
    return getTotalBurnMethod;
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
   * Query creates service with token as RPC
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Token returns token with token name
     * </pre>
     */
    public void token(irismod.token.QueryOuterClass.QueryTokenRequest request,
        io.grpc.stub.StreamObserver<irismod.token.QueryOuterClass.QueryTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTokenMethod(), responseObserver);
    }

    /**
     * <pre>
     * Tokens returns the token list
     * </pre>
     */
    public void tokens(irismod.token.QueryOuterClass.QueryTokensRequest request,
        io.grpc.stub.StreamObserver<irismod.token.QueryOuterClass.QueryTokensResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTokensMethod(), responseObserver);
    }

    /**
     * <pre>
     * Fees returns the fees to issue or mint a token
     * </pre>
     */
    public void fees(irismod.token.QueryOuterClass.QueryFeesRequest request,
        io.grpc.stub.StreamObserver<irismod.token.QueryOuterClass.QueryFeesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFeesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries the token parameters
     * </pre>
     */
    public void params(irismod.token.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<irismod.token.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalBurn queries all the burnt coins
     * </pre>
     */
    public void totalBurn(irismod.token.QueryOuterClass.QueryTotalBurnRequest request,
        io.grpc.stub.StreamObserver<irismod.token.QueryOuterClass.QueryTotalBurnResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTotalBurnMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.token.QueryOuterClass.QueryTokenRequest,
                irismod.token.QueryOuterClass.QueryTokenResponse>(
                  this, METHODID_TOKEN)))
          .addMethod(
            getTokensMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.token.QueryOuterClass.QueryTokensRequest,
                irismod.token.QueryOuterClass.QueryTokensResponse>(
                  this, METHODID_TOKENS)))
          .addMethod(
            getFeesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.token.QueryOuterClass.QueryFeesRequest,
                irismod.token.QueryOuterClass.QueryFeesResponse>(
                  this, METHODID_FEES)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.token.QueryOuterClass.QueryParamsRequest,
                irismod.token.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getTotalBurnMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.token.QueryOuterClass.QueryTotalBurnRequest,
                irismod.token.QueryOuterClass.QueryTotalBurnResponse>(
                  this, METHODID_TOTAL_BURN)))
          .build();
    }
  }

  /**
   * <pre>
   * Query creates service with token as RPC
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
     * Token returns token with token name
     * </pre>
     */
    public void token(irismod.token.QueryOuterClass.QueryTokenRequest request,
        io.grpc.stub.StreamObserver<irismod.token.QueryOuterClass.QueryTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Tokens returns the token list
     * </pre>
     */
    public void tokens(irismod.token.QueryOuterClass.QueryTokensRequest request,
        io.grpc.stub.StreamObserver<irismod.token.QueryOuterClass.QueryTokensResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTokensMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Fees returns the fees to issue or mint a token
     * </pre>
     */
    public void fees(irismod.token.QueryOuterClass.QueryFeesRequest request,
        io.grpc.stub.StreamObserver<irismod.token.QueryOuterClass.QueryFeesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFeesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries the token parameters
     * </pre>
     */
    public void params(irismod.token.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<irismod.token.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalBurn queries all the burnt coins
     * </pre>
     */
    public void totalBurn(irismod.token.QueryOuterClass.QueryTotalBurnRequest request,
        io.grpc.stub.StreamObserver<irismod.token.QueryOuterClass.QueryTotalBurnResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTotalBurnMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query creates service with token as RPC
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
     * Token returns token with token name
     * </pre>
     */
    public irismod.token.QueryOuterClass.QueryTokenResponse token(irismod.token.QueryOuterClass.QueryTokenRequest request) {
      return blockingUnaryCall(
          getChannel(), getTokenMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Tokens returns the token list
     * </pre>
     */
    public irismod.token.QueryOuterClass.QueryTokensResponse tokens(irismod.token.QueryOuterClass.QueryTokensRequest request) {
      return blockingUnaryCall(
          getChannel(), getTokensMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Fees returns the fees to issue or mint a token
     * </pre>
     */
    public irismod.token.QueryOuterClass.QueryFeesResponse fees(irismod.token.QueryOuterClass.QueryFeesRequest request) {
      return blockingUnaryCall(
          getChannel(), getFeesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries the token parameters
     * </pre>
     */
    public irismod.token.QueryOuterClass.QueryParamsResponse params(irismod.token.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalBurn queries all the burnt coins
     * </pre>
     */
    public irismod.token.QueryOuterClass.QueryTotalBurnResponse totalBurn(irismod.token.QueryOuterClass.QueryTotalBurnRequest request) {
      return blockingUnaryCall(
          getChannel(), getTotalBurnMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query creates service with token as RPC
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
     * Token returns token with token name
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.token.QueryOuterClass.QueryTokenResponse> token(
        irismod.token.QueryOuterClass.QueryTokenRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTokenMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Tokens returns the token list
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.token.QueryOuterClass.QueryTokensResponse> tokens(
        irismod.token.QueryOuterClass.QueryTokensRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTokensMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Fees returns the fees to issue or mint a token
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.token.QueryOuterClass.QueryFeesResponse> fees(
        irismod.token.QueryOuterClass.QueryFeesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFeesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries the token parameters
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.token.QueryOuterClass.QueryParamsResponse> params(
        irismod.token.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalBurn queries all the burnt coins
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.token.QueryOuterClass.QueryTotalBurnResponse> totalBurn(
        irismod.token.QueryOuterClass.QueryTotalBurnRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTotalBurnMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TOKEN = 0;
  private static final int METHODID_TOKENS = 1;
  private static final int METHODID_FEES = 2;
  private static final int METHODID_PARAMS = 3;
  private static final int METHODID_TOTAL_BURN = 4;

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
        case METHODID_TOKEN:
          serviceImpl.token((irismod.token.QueryOuterClass.QueryTokenRequest) request,
              (io.grpc.stub.StreamObserver<irismod.token.QueryOuterClass.QueryTokenResponse>) responseObserver);
          break;
        case METHODID_TOKENS:
          serviceImpl.tokens((irismod.token.QueryOuterClass.QueryTokensRequest) request,
              (io.grpc.stub.StreamObserver<irismod.token.QueryOuterClass.QueryTokensResponse>) responseObserver);
          break;
        case METHODID_FEES:
          serviceImpl.fees((irismod.token.QueryOuterClass.QueryFeesRequest) request,
              (io.grpc.stub.StreamObserver<irismod.token.QueryOuterClass.QueryFeesResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((irismod.token.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<irismod.token.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_TOTAL_BURN:
          serviceImpl.totalBurn((irismod.token.QueryOuterClass.QueryTotalBurnRequest) request,
              (io.grpc.stub.StreamObserver<irismod.token.QueryOuterClass.QueryTotalBurnResponse>) responseObserver);
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
      return irismod.token.QueryOuterClass.getDescriptor();
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
              .addMethod(getTokenMethod())
              .addMethod(getTokensMethod())
              .addMethod(getFeesMethod())
              .addMethod(getParamsMethod())
              .addMethod(getTotalBurnMethod())
              .build();
        }
      }
    }
    return result;
  }
}
