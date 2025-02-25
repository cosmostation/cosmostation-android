package com.babylon.checkpointing.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: babylon/checkpointing/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "babylon.checkpointing.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListRequest,
      com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListResponse> getRawCheckpointListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RawCheckpointList",
      requestType = com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListRequest.class,
      responseType = com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListRequest,
      com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListResponse> getRawCheckpointListMethod() {
    io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListRequest, com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListResponse> getRawCheckpointListMethod;
    if ((getRawCheckpointListMethod = QueryGrpc.getRawCheckpointListMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRawCheckpointListMethod = QueryGrpc.getRawCheckpointListMethod) == null) {
          QueryGrpc.getRawCheckpointListMethod = getRawCheckpointListMethod =
              io.grpc.MethodDescriptor.<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListRequest, com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RawCheckpointList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RawCheckpointList"))
              .build();
        }
      }
    }
    return getRawCheckpointListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointRequest,
      com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointResponse> getRawCheckpointMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RawCheckpoint",
      requestType = com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointRequest.class,
      responseType = com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointRequest,
      com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointResponse> getRawCheckpointMethod() {
    io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointRequest, com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointResponse> getRawCheckpointMethod;
    if ((getRawCheckpointMethod = QueryGrpc.getRawCheckpointMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRawCheckpointMethod = QueryGrpc.getRawCheckpointMethod) == null) {
          QueryGrpc.getRawCheckpointMethod = getRawCheckpointMethod =
              io.grpc.MethodDescriptor.<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointRequest, com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RawCheckpoint"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RawCheckpoint"))
              .build();
        }
      }
    }
    return getRawCheckpointMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsRequest,
      com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsResponse> getRawCheckpointsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RawCheckpoints",
      requestType = com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsRequest.class,
      responseType = com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsRequest,
      com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsResponse> getRawCheckpointsMethod() {
    io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsRequest, com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsResponse> getRawCheckpointsMethod;
    if ((getRawCheckpointsMethod = QueryGrpc.getRawCheckpointsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRawCheckpointsMethod = QueryGrpc.getRawCheckpointsMethod) == null) {
          QueryGrpc.getRawCheckpointsMethod = getRawCheckpointsMethod =
              io.grpc.MethodDescriptor.<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsRequest, com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RawCheckpoints"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RawCheckpoints"))
              .build();
        }
      }
    }
    return getRawCheckpointsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListRequest,
      com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListResponse> getBlsPublicKeyListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BlsPublicKeyList",
      requestType = com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListRequest.class,
      responseType = com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListRequest,
      com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListResponse> getBlsPublicKeyListMethod() {
    io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListRequest, com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListResponse> getBlsPublicKeyListMethod;
    if ((getBlsPublicKeyListMethod = QueryGrpc.getBlsPublicKeyListMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBlsPublicKeyListMethod = QueryGrpc.getBlsPublicKeyListMethod) == null) {
          QueryGrpc.getBlsPublicKeyListMethod = getBlsPublicKeyListMethod =
              io.grpc.MethodDescriptor.<com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListRequest, com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BlsPublicKeyList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BlsPublicKeyList"))
              .build();
        }
      }
    }
    return getBlsPublicKeyListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusRequest,
      com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusResponse> getEpochStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochStatus",
      requestType = com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusRequest.class,
      responseType = com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusRequest,
      com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusResponse> getEpochStatusMethod() {
    io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusRequest, com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusResponse> getEpochStatusMethod;
    if ((getEpochStatusMethod = QueryGrpc.getEpochStatusMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochStatusMethod = QueryGrpc.getEpochStatusMethod) == null) {
          QueryGrpc.getEpochStatusMethod = getEpochStatusMethod =
              io.grpc.MethodDescriptor.<com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusRequest, com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochStatus"))
              .build();
        }
      }
    }
    return getEpochStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountRequest,
      com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountResponse> getRecentEpochStatusCountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RecentEpochStatusCount",
      requestType = com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountRequest.class,
      responseType = com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountRequest,
      com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountResponse> getRecentEpochStatusCountMethod() {
    io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountRequest, com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountResponse> getRecentEpochStatusCountMethod;
    if ((getRecentEpochStatusCountMethod = QueryGrpc.getRecentEpochStatusCountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRecentEpochStatusCountMethod = QueryGrpc.getRecentEpochStatusCountMethod) == null) {
          QueryGrpc.getRecentEpochStatusCountMethod = getRecentEpochStatusCountMethod =
              io.grpc.MethodDescriptor.<com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountRequest, com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RecentEpochStatusCount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RecentEpochStatusCount"))
              .build();
        }
      }
    }
    return getRecentEpochStatusCountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusRequest,
      com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusResponse> getLastCheckpointWithStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastCheckpointWithStatus",
      requestType = com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusRequest.class,
      responseType = com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusRequest,
      com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusResponse> getLastCheckpointWithStatusMethod() {
    io.grpc.MethodDescriptor<com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusRequest, com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusResponse> getLastCheckpointWithStatusMethod;
    if ((getLastCheckpointWithStatusMethod = QueryGrpc.getLastCheckpointWithStatusMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastCheckpointWithStatusMethod = QueryGrpc.getLastCheckpointWithStatusMethod) == null) {
          QueryGrpc.getLastCheckpointWithStatusMethod = getLastCheckpointWithStatusMethod =
              io.grpc.MethodDescriptor.<com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusRequest, com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastCheckpointWithStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastCheckpointWithStatus"))
              .build();
        }
      }
    }
    return getLastCheckpointWithStatusMethod;
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
     * RawCheckpointList queries all checkpoints that match the given status.
     * </pre>
     */
    default void rawCheckpointList(com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListRequest request,
        io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRawCheckpointListMethod(), responseObserver);
    }

    /**
     * <pre>
     * RawCheckpoint queries a checkpoints at a given epoch number.
     * </pre>
     */
    default void rawCheckpoint(com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointRequest request,
        io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRawCheckpointMethod(), responseObserver);
    }

    /**
     * <pre>
     * RawCheckpoints queries checkpoints for a epoch range specified in pagination params.
     * </pre>
     */
    default void rawCheckpoints(com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRawCheckpointsMethod(), responseObserver);
    }

    /**
     * <pre>
     * BlsPublicKeyList queries a list of bls public keys of the validators at a
     * given epoch number.
     * </pre>
     */
    default void blsPublicKeyList(com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListRequest request,
        io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBlsPublicKeyListMethod(), responseObserver);
    }

    /**
     * <pre>
     * EpochStatus queries the status of the checkpoint at a given epoch
     * </pre>
     */
    default void epochStatus(com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusRequest request,
        io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEpochStatusMethod(), responseObserver);
    }

    /**
     * <pre>
     * RecentEpochStatusCount queries the number of epochs with each status in
     * recent epochs
     * </pre>
     */
    default void recentEpochStatusCount(com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountRequest request,
        io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRecentEpochStatusCountMethod(), responseObserver);
    }

    /**
     * <pre>
     * LastCheckpointWithStatus queries the last checkpoint with a given status or
     * a more matured status
     * </pre>
     */
    default void lastCheckpointWithStatus(com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusRequest request,
        io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastCheckpointWithStatusMethod(), responseObserver);
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
     * RawCheckpointList queries all checkpoints that match the given status.
     * </pre>
     */
    public void rawCheckpointList(com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListRequest request,
        io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRawCheckpointListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RawCheckpoint queries a checkpoints at a given epoch number.
     * </pre>
     */
    public void rawCheckpoint(com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointRequest request,
        io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRawCheckpointMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RawCheckpoints queries checkpoints for a epoch range specified in pagination params.
     * </pre>
     */
    public void rawCheckpoints(com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRawCheckpointsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BlsPublicKeyList queries a list of bls public keys of the validators at a
     * given epoch number.
     * </pre>
     */
    public void blsPublicKeyList(com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListRequest request,
        io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBlsPublicKeyListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EpochStatus queries the status of the checkpoint at a given epoch
     * </pre>
     */
    public void epochStatus(com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusRequest request,
        io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEpochStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RecentEpochStatusCount queries the number of epochs with each status in
     * recent epochs
     * </pre>
     */
    public void recentEpochStatusCount(com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountRequest request,
        io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRecentEpochStatusCountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * LastCheckpointWithStatus queries the last checkpoint with a given status or
     * a more matured status
     * </pre>
     */
    public void lastCheckpointWithStatus(com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusRequest request,
        io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastCheckpointWithStatusMethod(), getCallOptions()), request, responseObserver);
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
     * RawCheckpointList queries all checkpoints that match the given status.
     * </pre>
     */
    public com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListResponse rawCheckpointList(com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRawCheckpointListMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RawCheckpoint queries a checkpoints at a given epoch number.
     * </pre>
     */
    public com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointResponse rawCheckpoint(com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRawCheckpointMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RawCheckpoints queries checkpoints for a epoch range specified in pagination params.
     * </pre>
     */
    public com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsResponse rawCheckpoints(com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRawCheckpointsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BlsPublicKeyList queries a list of bls public keys of the validators at a
     * given epoch number.
     * </pre>
     */
    public com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListResponse blsPublicKeyList(com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBlsPublicKeyListMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EpochStatus queries the status of the checkpoint at a given epoch
     * </pre>
     */
    public com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusResponse epochStatus(com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEpochStatusMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RecentEpochStatusCount queries the number of epochs with each status in
     * recent epochs
     * </pre>
     */
    public com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountResponse recentEpochStatusCount(com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRecentEpochStatusCountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * LastCheckpointWithStatus queries the last checkpoint with a given status or
     * a more matured status
     * </pre>
     */
    public com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusResponse lastCheckpointWithStatus(com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastCheckpointWithStatusMethod(), getCallOptions(), request);
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
     * RawCheckpointList queries all checkpoints that match the given status.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListResponse> rawCheckpointList(
        com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRawCheckpointListMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RawCheckpoint queries a checkpoints at a given epoch number.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointResponse> rawCheckpoint(
        com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRawCheckpointMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RawCheckpoints queries checkpoints for a epoch range specified in pagination params.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsResponse> rawCheckpoints(
        com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRawCheckpointsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BlsPublicKeyList queries a list of bls public keys of the validators at a
     * given epoch number.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListResponse> blsPublicKeyList(
        com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBlsPublicKeyListMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EpochStatus queries the status of the checkpoint at a given epoch
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusResponse> epochStatus(
        com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEpochStatusMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RecentEpochStatusCount queries the number of epochs with each status in
     * recent epochs
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountResponse> recentEpochStatusCount(
        com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRecentEpochStatusCountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * LastCheckpointWithStatus queries the last checkpoint with a given status or
     * a more matured status
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusResponse> lastCheckpointWithStatus(
        com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastCheckpointWithStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RAW_CHECKPOINT_LIST = 0;
  private static final int METHODID_RAW_CHECKPOINT = 1;
  private static final int METHODID_RAW_CHECKPOINTS = 2;
  private static final int METHODID_BLS_PUBLIC_KEY_LIST = 3;
  private static final int METHODID_EPOCH_STATUS = 4;
  private static final int METHODID_RECENT_EPOCH_STATUS_COUNT = 5;
  private static final int METHODID_LAST_CHECKPOINT_WITH_STATUS = 6;

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
        case METHODID_RAW_CHECKPOINT_LIST:
          serviceImpl.rawCheckpointList((com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListResponse>) responseObserver);
          break;
        case METHODID_RAW_CHECKPOINT:
          serviceImpl.rawCheckpoint((com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointResponse>) responseObserver);
          break;
        case METHODID_RAW_CHECKPOINTS:
          serviceImpl.rawCheckpoints((com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsResponse>) responseObserver);
          break;
        case METHODID_BLS_PUBLIC_KEY_LIST:
          serviceImpl.blsPublicKeyList((com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListResponse>) responseObserver);
          break;
        case METHODID_EPOCH_STATUS:
          serviceImpl.epochStatus((com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusResponse>) responseObserver);
          break;
        case METHODID_RECENT_EPOCH_STATUS_COUNT:
          serviceImpl.recentEpochStatusCount((com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountResponse>) responseObserver);
          break;
        case METHODID_LAST_CHECKPOINT_WITH_STATUS:
          serviceImpl.lastCheckpointWithStatus((com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusResponse>) responseObserver);
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
          getRawCheckpointListMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListRequest,
              com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointListResponse>(
                service, METHODID_RAW_CHECKPOINT_LIST)))
        .addMethod(
          getRawCheckpointMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointRequest,
              com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointResponse>(
                service, METHODID_RAW_CHECKPOINT)))
        .addMethod(
          getRawCheckpointsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsRequest,
              com.babylon.checkpointing.v1.QueryProto.QueryRawCheckpointsResponse>(
                service, METHODID_RAW_CHECKPOINTS)))
        .addMethod(
          getBlsPublicKeyListMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListRequest,
              com.babylon.checkpointing.v1.QueryProto.QueryBlsPublicKeyListResponse>(
                service, METHODID_BLS_PUBLIC_KEY_LIST)))
        .addMethod(
          getEpochStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusRequest,
              com.babylon.checkpointing.v1.QueryProto.QueryEpochStatusResponse>(
                service, METHODID_EPOCH_STATUS)))
        .addMethod(
          getRecentEpochStatusCountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountRequest,
              com.babylon.checkpointing.v1.QueryProto.QueryRecentEpochStatusCountResponse>(
                service, METHODID_RECENT_EPOCH_STATUS_COUNT)))
        .addMethod(
          getLastCheckpointWithStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusRequest,
              com.babylon.checkpointing.v1.QueryProto.QueryLastCheckpointWithStatusResponse>(
                service, METHODID_LAST_CHECKPOINT_WITH_STATUS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.babylon.checkpointing.v1.QueryProto.getDescriptor();
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
              .addMethod(getRawCheckpointListMethod())
              .addMethod(getRawCheckpointMethod())
              .addMethod(getRawCheckpointsMethod())
              .addMethod(getBlsPublicKeyListMethod())
              .addMethod(getEpochStatusMethod())
              .addMethod(getRecentEpochStatusCountMethod())
              .addMethod(getLastCheckpointWithStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
