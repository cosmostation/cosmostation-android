package bitsong.fantoken.v1beta1;

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
 * Query creates service with fantoken as RPC
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: bitsong/fantoken/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "bitsong.fantoken.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenRequest,
      bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenResponse> getFanTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FanToken",
      requestType = bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenRequest.class,
      responseType = bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenRequest,
      bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenResponse> getFanTokenMethod() {
    io.grpc.MethodDescriptor<bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenRequest, bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenResponse> getFanTokenMethod;
    if ((getFanTokenMethod = QueryGrpc.getFanTokenMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFanTokenMethod = QueryGrpc.getFanTokenMethod) == null) {
          QueryGrpc.getFanTokenMethod = getFanTokenMethod =
              io.grpc.MethodDescriptor.<bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenRequest, bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FanToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FanToken"))
              .build();
        }
      }
    }
    return getFanTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensRequest,
      bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensResponse> getFanTokensMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FanTokens",
      requestType = bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensRequest.class,
      responseType = bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensRequest,
      bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensResponse> getFanTokensMethod() {
    io.grpc.MethodDescriptor<bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensRequest, bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensResponse> getFanTokensMethod;
    if ((getFanTokensMethod = QueryGrpc.getFanTokensMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFanTokensMethod = QueryGrpc.getFanTokensMethod) == null) {
          QueryGrpc.getFanTokensMethod = getFanTokensMethod =
              io.grpc.MethodDescriptor.<bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensRequest, bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FanTokens"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FanTokens"))
              .build();
        }
      }
    }
    return getFanTokensMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsRequest,
      bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsRequest,
      bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsRequest, bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsRequest, bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnRequest,
      bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnResponse> getTotalBurnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalBurn",
      requestType = bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnRequest.class,
      responseType = bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnRequest,
      bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnResponse> getTotalBurnMethod() {
    io.grpc.MethodDescriptor<bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnRequest, bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnResponse> getTotalBurnMethod;
    if ((getTotalBurnMethod = QueryGrpc.getTotalBurnMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalBurnMethod = QueryGrpc.getTotalBurnMethod) == null) {
          QueryGrpc.getTotalBurnMethod = getTotalBurnMethod =
              io.grpc.MethodDescriptor.<bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnRequest, bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalBurn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnResponse.getDefaultInstance()))
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
   * Query creates service with fantoken as RPC
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * FanToken returns fantoken with fantoken name
     * </pre>
     */
    public void fanToken(bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenRequest request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFanTokenMethod(), responseObserver);
    }

    /**
     * <pre>
     * FanTokens returns the fantoken list
     * </pre>
     */
    public void fanTokens(bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensRequest request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFanTokensMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries the fantoken parameters
     * </pre>
     */
    public void params(bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalBurn queries all the burnt coins
     * </pre>
     */
    public void totalBurn(bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnRequest request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTotalBurnMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFanTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenRequest,
                bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenResponse>(
                  this, METHODID_FAN_TOKEN)))
          .addMethod(
            getFanTokensMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensRequest,
                bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensResponse>(
                  this, METHODID_FAN_TOKENS)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsRequest,
                bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getTotalBurnMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnRequest,
                bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnResponse>(
                  this, METHODID_TOTAL_BURN)))
          .build();
    }
  }

  /**
   * <pre>
   * Query creates service with fantoken as RPC
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
     * FanToken returns fantoken with fantoken name
     * </pre>
     */
    public void fanToken(bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenRequest request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFanTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * FanTokens returns the fantoken list
     * </pre>
     */
    public void fanTokens(bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensRequest request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFanTokensMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries the fantoken parameters
     * </pre>
     */
    public void params(bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalBurn queries all the burnt coins
     * </pre>
     */
    public void totalBurn(bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnRequest request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTotalBurnMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query creates service with fantoken as RPC
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
     * FanToken returns fantoken with fantoken name
     * </pre>
     */
    public bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenResponse fanToken(bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenRequest request) {
      return blockingUnaryCall(
          getChannel(), getFanTokenMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * FanTokens returns the fantoken list
     * </pre>
     */
    public bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensResponse fanTokens(bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensRequest request) {
      return blockingUnaryCall(
          getChannel(), getFanTokensMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries the fantoken parameters
     * </pre>
     */
    public bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsResponse params(bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalBurn queries all the burnt coins
     * </pre>
     */
    public bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnResponse totalBurn(bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnRequest request) {
      return blockingUnaryCall(
          getChannel(), getTotalBurnMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query creates service with fantoken as RPC
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
     * FanToken returns fantoken with fantoken name
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenResponse> fanToken(
        bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFanTokenMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * FanTokens returns the fantoken list
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensResponse> fanTokens(
        bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFanTokensMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries the fantoken parameters
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalBurn queries all the burnt coins
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnResponse> totalBurn(
        bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTotalBurnMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FAN_TOKEN = 0;
  private static final int METHODID_FAN_TOKENS = 1;
  private static final int METHODID_PARAMS = 2;
  private static final int METHODID_TOTAL_BURN = 3;

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
        case METHODID_FAN_TOKEN:
          serviceImpl.fanToken((bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenRequest) request,
              (io.grpc.stub.StreamObserver<bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokenResponse>) responseObserver);
          break;
        case METHODID_FAN_TOKENS:
          serviceImpl.fanTokens((bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensRequest) request,
              (io.grpc.stub.StreamObserver<bitsong.fantoken.v1beta1.QueryOuterClass.QueryFanTokensResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<bitsong.fantoken.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_TOTAL_BURN:
          serviceImpl.totalBurn((bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnRequest) request,
              (io.grpc.stub.StreamObserver<bitsong.fantoken.v1beta1.QueryOuterClass.QueryTotalBurnResponse>) responseObserver);
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
      return bitsong.fantoken.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getFanTokenMethod())
              .addMethod(getFanTokensMethod())
              .addMethod(getParamsMethod())
              .addMethod(getTotalBurnMethod())
              .build();
        }
      }
    }
    return result;
  }
}
