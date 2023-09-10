package com.stride.icacallbacks;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: stride/icacallbacks/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "stride.icacallbacks.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.stride.icacallbacks.QueryProto.QueryParamsRequest,
      com.stride.icacallbacks.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.stride.icacallbacks.QueryProto.QueryParamsRequest.class,
      responseType = com.stride.icacallbacks.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.icacallbacks.QueryProto.QueryParamsRequest,
      com.stride.icacallbacks.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.stride.icacallbacks.QueryProto.QueryParamsRequest, com.stride.icacallbacks.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.stride.icacallbacks.QueryProto.QueryParamsRequest, com.stride.icacallbacks.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.icacallbacks.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.icacallbacks.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.icacallbacks.QueryProto.QueryGetCallbackDataRequest,
      com.stride.icacallbacks.QueryProto.QueryGetCallbackDataResponse> getCallbackDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CallbackData",
      requestType = com.stride.icacallbacks.QueryProto.QueryGetCallbackDataRequest.class,
      responseType = com.stride.icacallbacks.QueryProto.QueryGetCallbackDataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.icacallbacks.QueryProto.QueryGetCallbackDataRequest,
      com.stride.icacallbacks.QueryProto.QueryGetCallbackDataResponse> getCallbackDataMethod() {
    io.grpc.MethodDescriptor<com.stride.icacallbacks.QueryProto.QueryGetCallbackDataRequest, com.stride.icacallbacks.QueryProto.QueryGetCallbackDataResponse> getCallbackDataMethod;
    if ((getCallbackDataMethod = QueryGrpc.getCallbackDataMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCallbackDataMethod = QueryGrpc.getCallbackDataMethod) == null) {
          QueryGrpc.getCallbackDataMethod = getCallbackDataMethod =
              io.grpc.MethodDescriptor.<com.stride.icacallbacks.QueryProto.QueryGetCallbackDataRequest, com.stride.icacallbacks.QueryProto.QueryGetCallbackDataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CallbackData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.icacallbacks.QueryProto.QueryGetCallbackDataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.icacallbacks.QueryProto.QueryGetCallbackDataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CallbackData"))
              .build();
        }
      }
    }
    return getCallbackDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.icacallbacks.QueryProto.QueryAllCallbackDataRequest,
      com.stride.icacallbacks.QueryProto.QueryAllCallbackDataResponse> getCallbackDataAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CallbackDataAll",
      requestType = com.stride.icacallbacks.QueryProto.QueryAllCallbackDataRequest.class,
      responseType = com.stride.icacallbacks.QueryProto.QueryAllCallbackDataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.icacallbacks.QueryProto.QueryAllCallbackDataRequest,
      com.stride.icacallbacks.QueryProto.QueryAllCallbackDataResponse> getCallbackDataAllMethod() {
    io.grpc.MethodDescriptor<com.stride.icacallbacks.QueryProto.QueryAllCallbackDataRequest, com.stride.icacallbacks.QueryProto.QueryAllCallbackDataResponse> getCallbackDataAllMethod;
    if ((getCallbackDataAllMethod = QueryGrpc.getCallbackDataAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCallbackDataAllMethod = QueryGrpc.getCallbackDataAllMethod) == null) {
          QueryGrpc.getCallbackDataAllMethod = getCallbackDataAllMethod =
              io.grpc.MethodDescriptor.<com.stride.icacallbacks.QueryProto.QueryAllCallbackDataRequest, com.stride.icacallbacks.QueryProto.QueryAllCallbackDataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CallbackDataAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.icacallbacks.QueryProto.QueryAllCallbackDataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.icacallbacks.QueryProto.QueryAllCallbackDataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CallbackDataAll"))
              .build();
        }
      }
    }
    return getCallbackDataAllMethod;
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
  public interface AsyncService {

    /**
     * <pre>
     * Parameters queries the parameters of the module.
     * </pre>
     */
    default void params(com.stride.icacallbacks.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.stride.icacallbacks.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a CallbackData by index.
     * </pre>
     */
    default void callbackData(com.stride.icacallbacks.QueryProto.QueryGetCallbackDataRequest request,
        io.grpc.stub.StreamObserver<com.stride.icacallbacks.QueryProto.QueryGetCallbackDataResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCallbackDataMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of CallbackData items.
     * </pre>
     */
    default void callbackDataAll(com.stride.icacallbacks.QueryProto.QueryAllCallbackDataRequest request,
        io.grpc.stub.StreamObserver<com.stride.icacallbacks.QueryProto.QueryAllCallbackDataResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCallbackDataAllMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static abstract class QueryImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return QueryGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryStub
      extends io.grpc.stub.AbstractAsyncStub<QueryStub> {
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public void params(com.stride.icacallbacks.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.stride.icacallbacks.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a CallbackData by index.
     * </pre>
     */
    public void callbackData(com.stride.icacallbacks.QueryProto.QueryGetCallbackDataRequest request,
        io.grpc.stub.StreamObserver<com.stride.icacallbacks.QueryProto.QueryGetCallbackDataResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCallbackDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of CallbackData items.
     * </pre>
     */
    public void callbackDataAll(com.stride.icacallbacks.QueryProto.QueryAllCallbackDataRequest request,
        io.grpc.stub.StreamObserver<com.stride.icacallbacks.QueryProto.QueryAllCallbackDataResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCallbackDataAllMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<QueryBlockingStub> {
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public com.stride.icacallbacks.QueryProto.QueryParamsResponse params(com.stride.icacallbacks.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a CallbackData by index.
     * </pre>
     */
    public com.stride.icacallbacks.QueryProto.QueryGetCallbackDataResponse callbackData(com.stride.icacallbacks.QueryProto.QueryGetCallbackDataRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCallbackDataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of CallbackData items.
     * </pre>
     */
    public com.stride.icacallbacks.QueryProto.QueryAllCallbackDataResponse callbackDataAll(com.stride.icacallbacks.QueryProto.QueryAllCallbackDataRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCallbackDataAllMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryFutureStub
      extends io.grpc.stub.AbstractFutureStub<QueryFutureStub> {
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.icacallbacks.QueryProto.QueryParamsResponse> params(
        com.stride.icacallbacks.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a CallbackData by index.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.icacallbacks.QueryProto.QueryGetCallbackDataResponse> callbackData(
        com.stride.icacallbacks.QueryProto.QueryGetCallbackDataRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCallbackDataMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of CallbackData items.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.icacallbacks.QueryProto.QueryAllCallbackDataResponse> callbackDataAll(
        com.stride.icacallbacks.QueryProto.QueryAllCallbackDataRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCallbackDataAllMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_CALLBACK_DATA = 1;
  private static final int METHODID_CALLBACK_DATA_ALL = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PARAMS:
          serviceImpl.params((com.stride.icacallbacks.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.icacallbacks.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_CALLBACK_DATA:
          serviceImpl.callbackData((com.stride.icacallbacks.QueryProto.QueryGetCallbackDataRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.icacallbacks.QueryProto.QueryGetCallbackDataResponse>) responseObserver);
          break;
        case METHODID_CALLBACK_DATA_ALL:
          serviceImpl.callbackDataAll((com.stride.icacallbacks.QueryProto.QueryAllCallbackDataRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.icacallbacks.QueryProto.QueryAllCallbackDataResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.icacallbacks.QueryProto.QueryParamsRequest,
              com.stride.icacallbacks.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getCallbackDataMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.icacallbacks.QueryProto.QueryGetCallbackDataRequest,
              com.stride.icacallbacks.QueryProto.QueryGetCallbackDataResponse>(
                service, METHODID_CALLBACK_DATA)))
        .addMethod(
          getCallbackDataAllMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.icacallbacks.QueryProto.QueryAllCallbackDataRequest,
              com.stride.icacallbacks.QueryProto.QueryAllCallbackDataResponse>(
                service, METHODID_CALLBACK_DATA_ALL)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.stride.icacallbacks.QueryProto.getDescriptor();
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
              .addMethod(getCallbackDataMethod())
              .addMethod(getCallbackDataAllMethod())
              .build();
        }
      }
    }
    return result;
  }
}
