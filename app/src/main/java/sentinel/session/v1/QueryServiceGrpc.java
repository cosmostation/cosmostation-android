package sentinel.session.v1;

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
    comments = "Source: sentinel/session/v1/querier.proto")
public final class QueryServiceGrpc {

  private QueryServiceGrpc() {}

  public static final String SERVICE_NAME = "sentinel.session.v1.QueryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sentinel.session.v1.Querier.QuerySessionsRequest,
      sentinel.session.v1.Querier.QuerySessionsResponse> getQuerySessionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QuerySessions",
      requestType = sentinel.session.v1.Querier.QuerySessionsRequest.class,
      responseType = sentinel.session.v1.Querier.QuerySessionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.session.v1.Querier.QuerySessionsRequest,
      sentinel.session.v1.Querier.QuerySessionsResponse> getQuerySessionsMethod() {
    io.grpc.MethodDescriptor<sentinel.session.v1.Querier.QuerySessionsRequest, sentinel.session.v1.Querier.QuerySessionsResponse> getQuerySessionsMethod;
    if ((getQuerySessionsMethod = QueryServiceGrpc.getQuerySessionsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQuerySessionsMethod = QueryServiceGrpc.getQuerySessionsMethod) == null) {
          QueryServiceGrpc.getQuerySessionsMethod = getQuerySessionsMethod =
              io.grpc.MethodDescriptor.<sentinel.session.v1.Querier.QuerySessionsRequest, sentinel.session.v1.Querier.QuerySessionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QuerySessions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v1.Querier.QuerySessionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v1.Querier.QuerySessionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QuerySessions"))
              .build();
        }
      }
    }
    return getQuerySessionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.session.v1.Querier.QuerySessionsForAddressRequest,
      sentinel.session.v1.Querier.QuerySessionsForAddressResponse> getQuerySessionsForAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QuerySessionsForAddress",
      requestType = sentinel.session.v1.Querier.QuerySessionsForAddressRequest.class,
      responseType = sentinel.session.v1.Querier.QuerySessionsForAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.session.v1.Querier.QuerySessionsForAddressRequest,
      sentinel.session.v1.Querier.QuerySessionsForAddressResponse> getQuerySessionsForAddressMethod() {
    io.grpc.MethodDescriptor<sentinel.session.v1.Querier.QuerySessionsForAddressRequest, sentinel.session.v1.Querier.QuerySessionsForAddressResponse> getQuerySessionsForAddressMethod;
    if ((getQuerySessionsForAddressMethod = QueryServiceGrpc.getQuerySessionsForAddressMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQuerySessionsForAddressMethod = QueryServiceGrpc.getQuerySessionsForAddressMethod) == null) {
          QueryServiceGrpc.getQuerySessionsForAddressMethod = getQuerySessionsForAddressMethod =
              io.grpc.MethodDescriptor.<sentinel.session.v1.Querier.QuerySessionsForAddressRequest, sentinel.session.v1.Querier.QuerySessionsForAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QuerySessionsForAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v1.Querier.QuerySessionsForAddressRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v1.Querier.QuerySessionsForAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QuerySessionsForAddress"))
              .build();
        }
      }
    }
    return getQuerySessionsForAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.session.v1.Querier.QuerySessionRequest,
      sentinel.session.v1.Querier.QuerySessionResponse> getQuerySessionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QuerySession",
      requestType = sentinel.session.v1.Querier.QuerySessionRequest.class,
      responseType = sentinel.session.v1.Querier.QuerySessionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.session.v1.Querier.QuerySessionRequest,
      sentinel.session.v1.Querier.QuerySessionResponse> getQuerySessionMethod() {
    io.grpc.MethodDescriptor<sentinel.session.v1.Querier.QuerySessionRequest, sentinel.session.v1.Querier.QuerySessionResponse> getQuerySessionMethod;
    if ((getQuerySessionMethod = QueryServiceGrpc.getQuerySessionMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQuerySessionMethod = QueryServiceGrpc.getQuerySessionMethod) == null) {
          QueryServiceGrpc.getQuerySessionMethod = getQuerySessionMethod =
              io.grpc.MethodDescriptor.<sentinel.session.v1.Querier.QuerySessionRequest, sentinel.session.v1.Querier.QuerySessionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QuerySession"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v1.Querier.QuerySessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v1.Querier.QuerySessionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QuerySession"))
              .build();
        }
      }
    }
    return getQuerySessionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.session.v1.Querier.QueryParamsRequest,
      sentinel.session.v1.Querier.QueryParamsResponse> getQueryParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryParams",
      requestType = sentinel.session.v1.Querier.QueryParamsRequest.class,
      responseType = sentinel.session.v1.Querier.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.session.v1.Querier.QueryParamsRequest,
      sentinel.session.v1.Querier.QueryParamsResponse> getQueryParamsMethod() {
    io.grpc.MethodDescriptor<sentinel.session.v1.Querier.QueryParamsRequest, sentinel.session.v1.Querier.QueryParamsResponse> getQueryParamsMethod;
    if ((getQueryParamsMethod = QueryServiceGrpc.getQueryParamsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryParamsMethod = QueryServiceGrpc.getQueryParamsMethod) == null) {
          QueryServiceGrpc.getQueryParamsMethod = getQueryParamsMethod =
              io.grpc.MethodDescriptor.<sentinel.session.v1.Querier.QueryParamsRequest, sentinel.session.v1.Querier.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v1.Querier.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v1.Querier.QueryParamsResponse.getDefaultInstance()))
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
    public void querySessions(sentinel.session.v1.Querier.QuerySessionsRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v1.Querier.QuerySessionsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQuerySessionsMethod(), responseObserver);
    }

    /**
     */
    public void querySessionsForAddress(sentinel.session.v1.Querier.QuerySessionsForAddressRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v1.Querier.QuerySessionsForAddressResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQuerySessionsForAddressMethod(), responseObserver);
    }

    /**
     */
    public void querySession(sentinel.session.v1.Querier.QuerySessionRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v1.Querier.QuerySessionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQuerySessionMethod(), responseObserver);
    }

    /**
     */
    public void queryParams(sentinel.session.v1.Querier.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v1.Querier.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getQuerySessionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.session.v1.Querier.QuerySessionsRequest,
                sentinel.session.v1.Querier.QuerySessionsResponse>(
                  this, METHODID_QUERY_SESSIONS)))
          .addMethod(
            getQuerySessionsForAddressMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.session.v1.Querier.QuerySessionsForAddressRequest,
                sentinel.session.v1.Querier.QuerySessionsForAddressResponse>(
                  this, METHODID_QUERY_SESSIONS_FOR_ADDRESS)))
          .addMethod(
            getQuerySessionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.session.v1.Querier.QuerySessionRequest,
                sentinel.session.v1.Querier.QuerySessionResponse>(
                  this, METHODID_QUERY_SESSION)))
          .addMethod(
            getQueryParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.session.v1.Querier.QueryParamsRequest,
                sentinel.session.v1.Querier.QueryParamsResponse>(
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
    public void querySessions(sentinel.session.v1.Querier.QuerySessionsRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v1.Querier.QuerySessionsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQuerySessionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void querySessionsForAddress(sentinel.session.v1.Querier.QuerySessionsForAddressRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v1.Querier.QuerySessionsForAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQuerySessionsForAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void querySession(sentinel.session.v1.Querier.QuerySessionRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v1.Querier.QuerySessionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQuerySessionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryParams(sentinel.session.v1.Querier.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v1.Querier.QueryParamsResponse> responseObserver) {
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
    public sentinel.session.v1.Querier.QuerySessionsResponse querySessions(sentinel.session.v1.Querier.QuerySessionsRequest request) {
      return blockingUnaryCall(
          getChannel(), getQuerySessionsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.session.v1.Querier.QuerySessionsForAddressResponse querySessionsForAddress(sentinel.session.v1.Querier.QuerySessionsForAddressRequest request) {
      return blockingUnaryCall(
          getChannel(), getQuerySessionsForAddressMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.session.v1.Querier.QuerySessionResponse querySession(sentinel.session.v1.Querier.QuerySessionRequest request) {
      return blockingUnaryCall(
          getChannel(), getQuerySessionMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.session.v1.Querier.QueryParamsResponse queryParams(sentinel.session.v1.Querier.QueryParamsRequest request) {
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
    public com.google.common.util.concurrent.ListenableFuture<sentinel.session.v1.Querier.QuerySessionsResponse> querySessions(
        sentinel.session.v1.Querier.QuerySessionsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQuerySessionsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.session.v1.Querier.QuerySessionsForAddressResponse> querySessionsForAddress(
        sentinel.session.v1.Querier.QuerySessionsForAddressRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQuerySessionsForAddressMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.session.v1.Querier.QuerySessionResponse> querySession(
        sentinel.session.v1.Querier.QuerySessionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQuerySessionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.session.v1.Querier.QueryParamsResponse> queryParams(
        sentinel.session.v1.Querier.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_SESSIONS = 0;
  private static final int METHODID_QUERY_SESSIONS_FOR_ADDRESS = 1;
  private static final int METHODID_QUERY_SESSION = 2;
  private static final int METHODID_QUERY_PARAMS = 3;

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
        case METHODID_QUERY_SESSIONS:
          serviceImpl.querySessions((sentinel.session.v1.Querier.QuerySessionsRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.session.v1.Querier.QuerySessionsResponse>) responseObserver);
          break;
        case METHODID_QUERY_SESSIONS_FOR_ADDRESS:
          serviceImpl.querySessionsForAddress((sentinel.session.v1.Querier.QuerySessionsForAddressRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.session.v1.Querier.QuerySessionsForAddressResponse>) responseObserver);
          break;
        case METHODID_QUERY_SESSION:
          serviceImpl.querySession((sentinel.session.v1.Querier.QuerySessionRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.session.v1.Querier.QuerySessionResponse>) responseObserver);
          break;
        case METHODID_QUERY_PARAMS:
          serviceImpl.queryParams((sentinel.session.v1.Querier.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.session.v1.Querier.QueryParamsResponse>) responseObserver);
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
      return sentinel.session.v1.Querier.getDescriptor();
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
              .addMethod(getQuerySessionsMethod())
              .addMethod(getQuerySessionsForAddressMethod())
              .addMethod(getQuerySessionMethod())
              .addMethod(getQueryParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
