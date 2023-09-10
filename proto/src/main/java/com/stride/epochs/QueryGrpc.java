package com.stride.epochs;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: stride/epochs/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "stride.epochs.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.stride.epochs.QueryProto.QueryEpochsInfoRequest,
      com.stride.epochs.QueryProto.QueryEpochsInfoResponse> getEpochInfosMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochInfos",
      requestType = com.stride.epochs.QueryProto.QueryEpochsInfoRequest.class,
      responseType = com.stride.epochs.QueryProto.QueryEpochsInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.epochs.QueryProto.QueryEpochsInfoRequest,
      com.stride.epochs.QueryProto.QueryEpochsInfoResponse> getEpochInfosMethod() {
    io.grpc.MethodDescriptor<com.stride.epochs.QueryProto.QueryEpochsInfoRequest, com.stride.epochs.QueryProto.QueryEpochsInfoResponse> getEpochInfosMethod;
    if ((getEpochInfosMethod = QueryGrpc.getEpochInfosMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochInfosMethod = QueryGrpc.getEpochInfosMethod) == null) {
          QueryGrpc.getEpochInfosMethod = getEpochInfosMethod =
              io.grpc.MethodDescriptor.<com.stride.epochs.QueryProto.QueryEpochsInfoRequest, com.stride.epochs.QueryProto.QueryEpochsInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochInfos"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.epochs.QueryProto.QueryEpochsInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.epochs.QueryProto.QueryEpochsInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochInfos"))
              .build();
        }
      }
    }
    return getEpochInfosMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.epochs.QueryProto.QueryCurrentEpochRequest,
      com.stride.epochs.QueryProto.QueryCurrentEpochResponse> getCurrentEpochMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CurrentEpoch",
      requestType = com.stride.epochs.QueryProto.QueryCurrentEpochRequest.class,
      responseType = com.stride.epochs.QueryProto.QueryCurrentEpochResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.epochs.QueryProto.QueryCurrentEpochRequest,
      com.stride.epochs.QueryProto.QueryCurrentEpochResponse> getCurrentEpochMethod() {
    io.grpc.MethodDescriptor<com.stride.epochs.QueryProto.QueryCurrentEpochRequest, com.stride.epochs.QueryProto.QueryCurrentEpochResponse> getCurrentEpochMethod;
    if ((getCurrentEpochMethod = QueryGrpc.getCurrentEpochMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCurrentEpochMethod = QueryGrpc.getCurrentEpochMethod) == null) {
          QueryGrpc.getCurrentEpochMethod = getCurrentEpochMethod =
              io.grpc.MethodDescriptor.<com.stride.epochs.QueryProto.QueryCurrentEpochRequest, com.stride.epochs.QueryProto.QueryCurrentEpochResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CurrentEpoch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.epochs.QueryProto.QueryCurrentEpochRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.epochs.QueryProto.QueryCurrentEpochResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CurrentEpoch"))
              .build();
        }
      }
    }
    return getCurrentEpochMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.epochs.QueryProto.QueryEpochInfoRequest,
      com.stride.epochs.QueryProto.QueryEpochInfoResponse> getEpochInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochInfo",
      requestType = com.stride.epochs.QueryProto.QueryEpochInfoRequest.class,
      responseType = com.stride.epochs.QueryProto.QueryEpochInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.epochs.QueryProto.QueryEpochInfoRequest,
      com.stride.epochs.QueryProto.QueryEpochInfoResponse> getEpochInfoMethod() {
    io.grpc.MethodDescriptor<com.stride.epochs.QueryProto.QueryEpochInfoRequest, com.stride.epochs.QueryProto.QueryEpochInfoResponse> getEpochInfoMethod;
    if ((getEpochInfoMethod = QueryGrpc.getEpochInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochInfoMethod = QueryGrpc.getEpochInfoMethod) == null) {
          QueryGrpc.getEpochInfoMethod = getEpochInfoMethod =
              io.grpc.MethodDescriptor.<com.stride.epochs.QueryProto.QueryEpochInfoRequest, com.stride.epochs.QueryProto.QueryEpochInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.epochs.QueryProto.QueryEpochInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.epochs.QueryProto.QueryEpochInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochInfo"))
              .build();
        }
      }
    }
    return getEpochInfoMethod;
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
     * EpochInfos provide running epochInfos
     * </pre>
     */
    default void epochInfos(com.stride.epochs.QueryProto.QueryEpochsInfoRequest request,
        io.grpc.stub.StreamObserver<com.stride.epochs.QueryProto.QueryEpochsInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEpochInfosMethod(), responseObserver);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    default void currentEpoch(com.stride.epochs.QueryProto.QueryCurrentEpochRequest request,
        io.grpc.stub.StreamObserver<com.stride.epochs.QueryProto.QueryCurrentEpochResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCurrentEpochMethod(), responseObserver);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    default void epochInfo(com.stride.epochs.QueryProto.QueryEpochInfoRequest request,
        io.grpc.stub.StreamObserver<com.stride.epochs.QueryProto.QueryEpochInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEpochInfoMethod(), responseObserver);
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
     * EpochInfos provide running epochInfos
     * </pre>
     */
    public void epochInfos(com.stride.epochs.QueryProto.QueryEpochsInfoRequest request,
        io.grpc.stub.StreamObserver<com.stride.epochs.QueryProto.QueryEpochsInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEpochInfosMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public void currentEpoch(com.stride.epochs.QueryProto.QueryCurrentEpochRequest request,
        io.grpc.stub.StreamObserver<com.stride.epochs.QueryProto.QueryCurrentEpochResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCurrentEpochMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public void epochInfo(com.stride.epochs.QueryProto.QueryEpochInfoRequest request,
        io.grpc.stub.StreamObserver<com.stride.epochs.QueryProto.QueryEpochInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEpochInfoMethod(), getCallOptions()), request, responseObserver);
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
     * EpochInfos provide running epochInfos
     * </pre>
     */
    public com.stride.epochs.QueryProto.QueryEpochsInfoResponse epochInfos(com.stride.epochs.QueryProto.QueryEpochsInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEpochInfosMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public com.stride.epochs.QueryProto.QueryCurrentEpochResponse currentEpoch(com.stride.epochs.QueryProto.QueryCurrentEpochRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCurrentEpochMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public com.stride.epochs.QueryProto.QueryEpochInfoResponse epochInfo(com.stride.epochs.QueryProto.QueryEpochInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEpochInfoMethod(), getCallOptions(), request);
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
     * EpochInfos provide running epochInfos
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.epochs.QueryProto.QueryEpochsInfoResponse> epochInfos(
        com.stride.epochs.QueryProto.QueryEpochsInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEpochInfosMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.epochs.QueryProto.QueryCurrentEpochResponse> currentEpoch(
        com.stride.epochs.QueryProto.QueryCurrentEpochRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCurrentEpochMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.epochs.QueryProto.QueryEpochInfoResponse> epochInfo(
        com.stride.epochs.QueryProto.QueryEpochInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEpochInfoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EPOCH_INFOS = 0;
  private static final int METHODID_CURRENT_EPOCH = 1;
  private static final int METHODID_EPOCH_INFO = 2;

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
        case METHODID_EPOCH_INFOS:
          serviceImpl.epochInfos((com.stride.epochs.QueryProto.QueryEpochsInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.epochs.QueryProto.QueryEpochsInfoResponse>) responseObserver);
          break;
        case METHODID_CURRENT_EPOCH:
          serviceImpl.currentEpoch((com.stride.epochs.QueryProto.QueryCurrentEpochRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.epochs.QueryProto.QueryCurrentEpochResponse>) responseObserver);
          break;
        case METHODID_EPOCH_INFO:
          serviceImpl.epochInfo((com.stride.epochs.QueryProto.QueryEpochInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.epochs.QueryProto.QueryEpochInfoResponse>) responseObserver);
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
          getEpochInfosMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.epochs.QueryProto.QueryEpochsInfoRequest,
              com.stride.epochs.QueryProto.QueryEpochsInfoResponse>(
                service, METHODID_EPOCH_INFOS)))
        .addMethod(
          getCurrentEpochMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.epochs.QueryProto.QueryCurrentEpochRequest,
              com.stride.epochs.QueryProto.QueryCurrentEpochResponse>(
                service, METHODID_CURRENT_EPOCH)))
        .addMethod(
          getEpochInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.epochs.QueryProto.QueryEpochInfoRequest,
              com.stride.epochs.QueryProto.QueryEpochInfoResponse>(
                service, METHODID_EPOCH_INFO)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.stride.epochs.QueryProto.getDescriptor();
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
              .addMethod(getEpochInfosMethod())
              .addMethod(getCurrentEpochMethod())
              .addMethod(getEpochInfoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
