package sentinel.swap.v1;

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
    comments = "Source: sentinel/swap/v1/querier.proto")
public final class QueryServiceGrpc {

  private QueryServiceGrpc() {}

  public static final String SERVICE_NAME = "sentinel.swap.v1.QueryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sentinel.swap.v1.Querier.QuerySwapsRequest,
      sentinel.swap.v1.Querier.QuerySwapsResponse> getQuerySwapsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QuerySwaps",
      requestType = sentinel.swap.v1.Querier.QuerySwapsRequest.class,
      responseType = sentinel.swap.v1.Querier.QuerySwapsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.swap.v1.Querier.QuerySwapsRequest,
      sentinel.swap.v1.Querier.QuerySwapsResponse> getQuerySwapsMethod() {
    io.grpc.MethodDescriptor<sentinel.swap.v1.Querier.QuerySwapsRequest, sentinel.swap.v1.Querier.QuerySwapsResponse> getQuerySwapsMethod;
    if ((getQuerySwapsMethod = QueryServiceGrpc.getQuerySwapsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQuerySwapsMethod = QueryServiceGrpc.getQuerySwapsMethod) == null) {
          QueryServiceGrpc.getQuerySwapsMethod = getQuerySwapsMethod =
              io.grpc.MethodDescriptor.<sentinel.swap.v1.Querier.QuerySwapsRequest, sentinel.swap.v1.Querier.QuerySwapsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QuerySwaps"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.swap.v1.Querier.QuerySwapsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.swap.v1.Querier.QuerySwapsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QuerySwaps"))
              .build();
        }
      }
    }
    return getQuerySwapsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.swap.v1.Querier.QuerySwapRequest,
      sentinel.swap.v1.Querier.QuerySwapResponse> getQuerySwapMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QuerySwap",
      requestType = sentinel.swap.v1.Querier.QuerySwapRequest.class,
      responseType = sentinel.swap.v1.Querier.QuerySwapResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.swap.v1.Querier.QuerySwapRequest,
      sentinel.swap.v1.Querier.QuerySwapResponse> getQuerySwapMethod() {
    io.grpc.MethodDescriptor<sentinel.swap.v1.Querier.QuerySwapRequest, sentinel.swap.v1.Querier.QuerySwapResponse> getQuerySwapMethod;
    if ((getQuerySwapMethod = QueryServiceGrpc.getQuerySwapMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQuerySwapMethod = QueryServiceGrpc.getQuerySwapMethod) == null) {
          QueryServiceGrpc.getQuerySwapMethod = getQuerySwapMethod =
              io.grpc.MethodDescriptor.<sentinel.swap.v1.Querier.QuerySwapRequest, sentinel.swap.v1.Querier.QuerySwapResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QuerySwap"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.swap.v1.Querier.QuerySwapRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.swap.v1.Querier.QuerySwapResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QuerySwap"))
              .build();
        }
      }
    }
    return getQuerySwapMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.swap.v1.Querier.QueryParamsRequest,
      sentinel.swap.v1.Querier.QueryParamsResponse> getQueryParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryParams",
      requestType = sentinel.swap.v1.Querier.QueryParamsRequest.class,
      responseType = sentinel.swap.v1.Querier.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.swap.v1.Querier.QueryParamsRequest,
      sentinel.swap.v1.Querier.QueryParamsResponse> getQueryParamsMethod() {
    io.grpc.MethodDescriptor<sentinel.swap.v1.Querier.QueryParamsRequest, sentinel.swap.v1.Querier.QueryParamsResponse> getQueryParamsMethod;
    if ((getQueryParamsMethod = QueryServiceGrpc.getQueryParamsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryParamsMethod = QueryServiceGrpc.getQueryParamsMethod) == null) {
          QueryServiceGrpc.getQueryParamsMethod = getQueryParamsMethod =
              io.grpc.MethodDescriptor.<sentinel.swap.v1.Querier.QueryParamsRequest, sentinel.swap.v1.Querier.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.swap.v1.Querier.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.swap.v1.Querier.QueryParamsResponse.getDefaultInstance()))
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
    public void querySwaps(sentinel.swap.v1.Querier.QuerySwapsRequest request,
        io.grpc.stub.StreamObserver<sentinel.swap.v1.Querier.QuerySwapsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQuerySwapsMethod(), responseObserver);
    }

    /**
     */
    public void querySwap(sentinel.swap.v1.Querier.QuerySwapRequest request,
        io.grpc.stub.StreamObserver<sentinel.swap.v1.Querier.QuerySwapResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQuerySwapMethod(), responseObserver);
    }

    /**
     */
    public void queryParams(sentinel.swap.v1.Querier.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<sentinel.swap.v1.Querier.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getQuerySwapsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.swap.v1.Querier.QuerySwapsRequest,
                sentinel.swap.v1.Querier.QuerySwapsResponse>(
                  this, METHODID_QUERY_SWAPS)))
          .addMethod(
            getQuerySwapMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.swap.v1.Querier.QuerySwapRequest,
                sentinel.swap.v1.Querier.QuerySwapResponse>(
                  this, METHODID_QUERY_SWAP)))
          .addMethod(
            getQueryParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.swap.v1.Querier.QueryParamsRequest,
                sentinel.swap.v1.Querier.QueryParamsResponse>(
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
    public void querySwaps(sentinel.swap.v1.Querier.QuerySwapsRequest request,
        io.grpc.stub.StreamObserver<sentinel.swap.v1.Querier.QuerySwapsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQuerySwapsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void querySwap(sentinel.swap.v1.Querier.QuerySwapRequest request,
        io.grpc.stub.StreamObserver<sentinel.swap.v1.Querier.QuerySwapResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQuerySwapMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryParams(sentinel.swap.v1.Querier.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<sentinel.swap.v1.Querier.QueryParamsResponse> responseObserver) {
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
    public sentinel.swap.v1.Querier.QuerySwapsResponse querySwaps(sentinel.swap.v1.Querier.QuerySwapsRequest request) {
      return blockingUnaryCall(
          getChannel(), getQuerySwapsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.swap.v1.Querier.QuerySwapResponse querySwap(sentinel.swap.v1.Querier.QuerySwapRequest request) {
      return blockingUnaryCall(
          getChannel(), getQuerySwapMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.swap.v1.Querier.QueryParamsResponse queryParams(sentinel.swap.v1.Querier.QueryParamsRequest request) {
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
    public com.google.common.util.concurrent.ListenableFuture<sentinel.swap.v1.Querier.QuerySwapsResponse> querySwaps(
        sentinel.swap.v1.Querier.QuerySwapsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQuerySwapsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.swap.v1.Querier.QuerySwapResponse> querySwap(
        sentinel.swap.v1.Querier.QuerySwapRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQuerySwapMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.swap.v1.Querier.QueryParamsResponse> queryParams(
        sentinel.swap.v1.Querier.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_SWAPS = 0;
  private static final int METHODID_QUERY_SWAP = 1;
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
        case METHODID_QUERY_SWAPS:
          serviceImpl.querySwaps((sentinel.swap.v1.Querier.QuerySwapsRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.swap.v1.Querier.QuerySwapsResponse>) responseObserver);
          break;
        case METHODID_QUERY_SWAP:
          serviceImpl.querySwap((sentinel.swap.v1.Querier.QuerySwapRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.swap.v1.Querier.QuerySwapResponse>) responseObserver);
          break;
        case METHODID_QUERY_PARAMS:
          serviceImpl.queryParams((sentinel.swap.v1.Querier.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.swap.v1.Querier.QueryParamsResponse>) responseObserver);
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
      return sentinel.swap.v1.Querier.getDescriptor();
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
              .addMethod(getQuerySwapsMethod())
              .addMethod(getQuerySwapMethod())
              .addMethod(getQueryParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
