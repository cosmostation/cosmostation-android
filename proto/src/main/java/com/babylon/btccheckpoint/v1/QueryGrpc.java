package com.babylon.btccheckpoint.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: babylon/btccheckpoint/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "babylon.btccheckpoint.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.QueryProto.QueryParamsRequest,
      com.babylon.btccheckpoint.v1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.babylon.btccheckpoint.v1.QueryProto.QueryParamsRequest.class,
      responseType = com.babylon.btccheckpoint.v1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.QueryProto.QueryParamsRequest,
      com.babylon.btccheckpoint.v1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.QueryProto.QueryParamsRequest, com.babylon.btccheckpoint.v1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.babylon.btccheckpoint.v1.QueryProto.QueryParamsRequest, com.babylon.btccheckpoint.v1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btccheckpoint.v1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btccheckpoint.v1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoRequest,
      com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoResponse> getBtcCheckpointInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BtcCheckpointInfo",
      requestType = com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoRequest.class,
      responseType = com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoRequest,
      com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoResponse> getBtcCheckpointInfoMethod() {
    io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoRequest, com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoResponse> getBtcCheckpointInfoMethod;
    if ((getBtcCheckpointInfoMethod = QueryGrpc.getBtcCheckpointInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBtcCheckpointInfoMethod = QueryGrpc.getBtcCheckpointInfoMethod) == null) {
          QueryGrpc.getBtcCheckpointInfoMethod = getBtcCheckpointInfoMethod =
              io.grpc.MethodDescriptor.<com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoRequest, com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BtcCheckpointInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BtcCheckpointInfo"))
              .build();
        }
      }
    }
    return getBtcCheckpointInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoRequest,
      com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoResponse> getBtcCheckpointsInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BtcCheckpointsInfo",
      requestType = com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoRequest.class,
      responseType = com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoRequest,
      com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoResponse> getBtcCheckpointsInfoMethod() {
    io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoRequest, com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoResponse> getBtcCheckpointsInfoMethod;
    if ((getBtcCheckpointsInfoMethod = QueryGrpc.getBtcCheckpointsInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBtcCheckpointsInfoMethod = QueryGrpc.getBtcCheckpointsInfoMethod) == null) {
          QueryGrpc.getBtcCheckpointsInfoMethod = getBtcCheckpointsInfoMethod =
              io.grpc.MethodDescriptor.<com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoRequest, com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BtcCheckpointsInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BtcCheckpointsInfo"))
              .build();
        }
      }
    }
    return getBtcCheckpointsInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsRequest,
      com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsResponse> getEpochSubmissionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochSubmissions",
      requestType = com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsRequest.class,
      responseType = com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsRequest,
      com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsResponse> getEpochSubmissionsMethod() {
    io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsRequest, com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsResponse> getEpochSubmissionsMethod;
    if ((getEpochSubmissionsMethod = QueryGrpc.getEpochSubmissionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochSubmissionsMethod = QueryGrpc.getEpochSubmissionsMethod) == null) {
          QueryGrpc.getEpochSubmissionsMethod = getEpochSubmissionsMethod =
              io.grpc.MethodDescriptor.<com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsRequest, com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochSubmissions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochSubmissions"))
              .build();
        }
      }
    }
    return getEpochSubmissionsMethod;
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
    default void params(com.babylon.btccheckpoint.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * BtcCheckpointInfo returns checkpoint info for a given epoch
     * </pre>
     */
    default void btcCheckpointInfo(com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBtcCheckpointInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * BtcCheckpointsInfo returns checkpoint info for a range of epochs
     * </pre>
     */
    default void btcCheckpointsInfo(com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBtcCheckpointsInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * EpochSubmissions returns all submissions for a given epoch
     * </pre>
     */
    default void epochSubmissions(com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEpochSubmissionsMethod(), responseObserver);
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
    public void params(com.babylon.btccheckpoint.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BtcCheckpointInfo returns checkpoint info for a given epoch
     * </pre>
     */
    public void btcCheckpointInfo(com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBtcCheckpointInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BtcCheckpointsInfo returns checkpoint info for a range of epochs
     * </pre>
     */
    public void btcCheckpointsInfo(com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBtcCheckpointsInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EpochSubmissions returns all submissions for a given epoch
     * </pre>
     */
    public void epochSubmissions(com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEpochSubmissionsMethod(), getCallOptions()), request, responseObserver);
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
    public com.babylon.btccheckpoint.v1.QueryProto.QueryParamsResponse params(com.babylon.btccheckpoint.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BtcCheckpointInfo returns checkpoint info for a given epoch
     * </pre>
     */
    public com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoResponse btcCheckpointInfo(com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBtcCheckpointInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BtcCheckpointsInfo returns checkpoint info for a range of epochs
     * </pre>
     */
    public com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoResponse btcCheckpointsInfo(com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBtcCheckpointsInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EpochSubmissions returns all submissions for a given epoch
     * </pre>
     */
    public com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsResponse epochSubmissions(com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEpochSubmissionsMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btccheckpoint.v1.QueryProto.QueryParamsResponse> params(
        com.babylon.btccheckpoint.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BtcCheckpointInfo returns checkpoint info for a given epoch
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoResponse> btcCheckpointInfo(
        com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBtcCheckpointInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BtcCheckpointsInfo returns checkpoint info for a range of epochs
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoResponse> btcCheckpointsInfo(
        com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBtcCheckpointsInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EpochSubmissions returns all submissions for a given epoch
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsResponse> epochSubmissions(
        com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEpochSubmissionsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_BTC_CHECKPOINT_INFO = 1;
  private static final int METHODID_BTC_CHECKPOINTS_INFO = 2;
  private static final int METHODID_EPOCH_SUBMISSIONS = 3;

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
          serviceImpl.params((com.babylon.btccheckpoint.v1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_BTC_CHECKPOINT_INFO:
          serviceImpl.btcCheckpointInfo((com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoResponse>) responseObserver);
          break;
        case METHODID_BTC_CHECKPOINTS_INFO:
          serviceImpl.btcCheckpointsInfo((com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoResponse>) responseObserver);
          break;
        case METHODID_EPOCH_SUBMISSIONS:
          serviceImpl.epochSubmissions((com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsResponse>) responseObserver);
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
              com.babylon.btccheckpoint.v1.QueryProto.QueryParamsRequest,
              com.babylon.btccheckpoint.v1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getBtcCheckpointInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoRequest,
              com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointInfoResponse>(
                service, METHODID_BTC_CHECKPOINT_INFO)))
        .addMethod(
          getBtcCheckpointsInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoRequest,
              com.babylon.btccheckpoint.v1.QueryProto.QueryBtcCheckpointsInfoResponse>(
                service, METHODID_BTC_CHECKPOINTS_INFO)))
        .addMethod(
          getEpochSubmissionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsRequest,
              com.babylon.btccheckpoint.v1.QueryProto.QueryEpochSubmissionsResponse>(
                service, METHODID_EPOCH_SUBMISSIONS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.babylon.btccheckpoint.v1.QueryProto.getDescriptor();
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
              .addMethod(getBtcCheckpointInfoMethod())
              .addMethod(getBtcCheckpointsInfoMethod())
              .addMethod(getEpochSubmissionsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
