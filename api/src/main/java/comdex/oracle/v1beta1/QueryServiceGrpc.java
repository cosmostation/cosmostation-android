package comdex.oracle.v1beta1;

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
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: comdex/oracle/v1beta1/querier.proto")
public final class QueryServiceGrpc {

  private QueryServiceGrpc() {}

  public static final String SERVICE_NAME = "comdex.oracle.v1beta1.QueryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Querier.QueryMarketsRequest,
      comdex.oracle.v1beta1.Querier.QueryMarketsResponse> getQueryMarketsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryMarkets",
      requestType = comdex.oracle.v1beta1.Querier.QueryMarketsRequest.class,
      responseType = comdex.oracle.v1beta1.Querier.QueryMarketsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Querier.QueryMarketsRequest,
      comdex.oracle.v1beta1.Querier.QueryMarketsResponse> getQueryMarketsMethod() {
    io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Querier.QueryMarketsRequest, comdex.oracle.v1beta1.Querier.QueryMarketsResponse> getQueryMarketsMethod;
    if ((getQueryMarketsMethod = QueryServiceGrpc.getQueryMarketsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryMarketsMethod = QueryServiceGrpc.getQueryMarketsMethod) == null) {
          QueryServiceGrpc.getQueryMarketsMethod = getQueryMarketsMethod =
              io.grpc.MethodDescriptor.<comdex.oracle.v1beta1.Querier.QueryMarketsRequest, comdex.oracle.v1beta1.Querier.QueryMarketsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryMarkets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.oracle.v1beta1.Querier.QueryMarketsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.oracle.v1beta1.Querier.QueryMarketsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryMarkets"))
              .build();
        }
      }
    }
    return getQueryMarketsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Querier.QueryMarketRequest,
      comdex.oracle.v1beta1.Querier.QueryMarketResponse> getQueryMarketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryMarket",
      requestType = comdex.oracle.v1beta1.Querier.QueryMarketRequest.class,
      responseType = comdex.oracle.v1beta1.Querier.QueryMarketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Querier.QueryMarketRequest,
      comdex.oracle.v1beta1.Querier.QueryMarketResponse> getQueryMarketMethod() {
    io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Querier.QueryMarketRequest, comdex.oracle.v1beta1.Querier.QueryMarketResponse> getQueryMarketMethod;
    if ((getQueryMarketMethod = QueryServiceGrpc.getQueryMarketMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryMarketMethod = QueryServiceGrpc.getQueryMarketMethod) == null) {
          QueryServiceGrpc.getQueryMarketMethod = getQueryMarketMethod =
              io.grpc.MethodDescriptor.<comdex.oracle.v1beta1.Querier.QueryMarketRequest, comdex.oracle.v1beta1.Querier.QueryMarketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryMarket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.oracle.v1beta1.Querier.QueryMarketRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.oracle.v1beta1.Querier.QueryMarketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryMarket"))
              .build();
        }
      }
    }
    return getQueryMarketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Querier.QueryParamsRequest,
      comdex.oracle.v1beta1.Querier.QueryParamsResponse> getQueryParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryParams",
      requestType = comdex.oracle.v1beta1.Querier.QueryParamsRequest.class,
      responseType = comdex.oracle.v1beta1.Querier.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Querier.QueryParamsRequest,
      comdex.oracle.v1beta1.Querier.QueryParamsResponse> getQueryParamsMethod() {
    io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Querier.QueryParamsRequest, comdex.oracle.v1beta1.Querier.QueryParamsResponse> getQueryParamsMethod;
    if ((getQueryParamsMethod = QueryServiceGrpc.getQueryParamsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryParamsMethod = QueryServiceGrpc.getQueryParamsMethod) == null) {
          QueryServiceGrpc.getQueryParamsMethod = getQueryParamsMethod =
              io.grpc.MethodDescriptor.<comdex.oracle.v1beta1.Querier.QueryParamsRequest, comdex.oracle.v1beta1.Querier.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.oracle.v1beta1.Querier.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.oracle.v1beta1.Querier.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryParams"))
              .build();
        }
      }
    }
    return getQueryParamsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QueryServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceStub>() {
        @java.lang.Override
        public QueryServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceStub(channel, callOptions);
        }
      };
    return QueryServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QueryServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceBlockingStub>() {
        @java.lang.Override
        public QueryServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceBlockingStub(channel, callOptions);
        }
      };
    return QueryServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QueryServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceFutureStub>() {
        @java.lang.Override
        public QueryServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceFutureStub(channel, callOptions);
        }
      };
    return QueryServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class QueryServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void queryMarkets(comdex.oracle.v1beta1.Querier.QueryMarketsRequest request,
        io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Querier.QueryMarketsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryMarketsMethod(), responseObserver);
    }

    /**
     */
    public void queryMarket(comdex.oracle.v1beta1.Querier.QueryMarketRequest request,
        io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Querier.QueryMarketResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryMarketMethod(), responseObserver);
    }

    /**
     */
    public void queryParams(comdex.oracle.v1beta1.Querier.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Querier.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getQueryMarketsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.oracle.v1beta1.Querier.QueryMarketsRequest,
                comdex.oracle.v1beta1.Querier.QueryMarketsResponse>(
                  this, METHODID_QUERY_MARKETS)))
          .addMethod(
            getQueryMarketMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.oracle.v1beta1.Querier.QueryMarketRequest,
                comdex.oracle.v1beta1.Querier.QueryMarketResponse>(
                  this, METHODID_QUERY_MARKET)))
          .addMethod(
            getQueryParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.oracle.v1beta1.Querier.QueryParamsRequest,
                comdex.oracle.v1beta1.Querier.QueryParamsResponse>(
                  this, METHODID_QUERY_PARAMS)))
          .build();
    }
  }

  /**
   */
  public static final class QueryServiceStub extends io.grpc.stub.AbstractAsyncStub<QueryServiceStub> {
    private QueryServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceStub(channel, callOptions);
    }

    /**
     */
    public void queryMarkets(comdex.oracle.v1beta1.Querier.QueryMarketsRequest request,
        io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Querier.QueryMarketsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryMarketsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryMarket(comdex.oracle.v1beta1.Querier.QueryMarketRequest request,
        io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Querier.QueryMarketResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryMarketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryParams(comdex.oracle.v1beta1.Querier.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Querier.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class QueryServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<QueryServiceBlockingStub> {
    private QueryServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public comdex.oracle.v1beta1.Querier.QueryMarketsResponse queryMarkets(comdex.oracle.v1beta1.Querier.QueryMarketsRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryMarketsMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.oracle.v1beta1.Querier.QueryMarketResponse queryMarket(comdex.oracle.v1beta1.Querier.QueryMarketRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryMarketMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.oracle.v1beta1.Querier.QueryParamsResponse queryParams(comdex.oracle.v1beta1.Querier.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class QueryServiceFutureStub extends io.grpc.stub.AbstractFutureStub<QueryServiceFutureStub> {
    private QueryServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.oracle.v1beta1.Querier.QueryMarketsResponse> queryMarkets(
        comdex.oracle.v1beta1.Querier.QueryMarketsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryMarketsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.oracle.v1beta1.Querier.QueryMarketResponse> queryMarket(
        comdex.oracle.v1beta1.Querier.QueryMarketRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryMarketMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.oracle.v1beta1.Querier.QueryParamsResponse> queryParams(
        comdex.oracle.v1beta1.Querier.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_MARKETS = 0;
  private static final int METHODID_QUERY_MARKET = 1;
  private static final int METHODID_QUERY_PARAMS = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final QueryServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(QueryServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_QUERY_MARKETS:
          serviceImpl.queryMarkets((comdex.oracle.v1beta1.Querier.QueryMarketsRequest) request,
              (io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Querier.QueryMarketsResponse>) responseObserver);
          break;
        case METHODID_QUERY_MARKET:
          serviceImpl.queryMarket((comdex.oracle.v1beta1.Querier.QueryMarketRequest) request,
              (io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Querier.QueryMarketResponse>) responseObserver);
          break;
        case METHODID_QUERY_PARAMS:
          serviceImpl.queryParams((comdex.oracle.v1beta1.Querier.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Querier.QueryParamsResponse>) responseObserver);
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

  private static abstract class QueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return comdex.oracle.v1beta1.Querier.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("QueryService");
    }
  }

  private static final class QueryServiceFileDescriptorSupplier
      extends QueryServiceBaseDescriptorSupplier {
    QueryServiceFileDescriptorSupplier() {}
  }

  private static final class QueryServiceMethodDescriptorSupplier
      extends QueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    QueryServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (QueryServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QueryServiceFileDescriptorSupplier())
              .addMethod(getQueryMarketsMethod())
              .addMethod(getQueryMarketMethod())
              .addMethod(getQueryParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
