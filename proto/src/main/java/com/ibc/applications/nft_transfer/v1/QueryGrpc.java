package com.ibc.applications.nft_transfer.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query provides defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: ibc/applications/nft_transfer/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "ibc.applications.nft_transfer.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceRequest,
      com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceResponse> getClassTraceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClassTrace",
      requestType = com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceRequest.class,
      responseType = com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceRequest,
      com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceResponse> getClassTraceMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceRequest, com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceResponse> getClassTraceMethod;
    if ((getClassTraceMethod = QueryGrpc.getClassTraceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClassTraceMethod = QueryGrpc.getClassTraceMethod) == null) {
          QueryGrpc.getClassTraceMethod = getClassTraceMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceRequest, com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClassTrace"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClassTrace"))
              .build();
        }
      }
    }
    return getClassTraceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesRequest,
      com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesResponse> getClassTracesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClassTraces",
      requestType = com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesRequest.class,
      responseType = com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesRequest,
      com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesResponse> getClassTracesMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesRequest, com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesResponse> getClassTracesMethod;
    if ((getClassTracesMethod = QueryGrpc.getClassTracesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClassTracesMethod = QueryGrpc.getClassTracesMethod) == null) {
          QueryGrpc.getClassTracesMethod = getClassTracesMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesRequest, com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClassTraces"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClassTraces"))
              .build();
        }
      }
    }
    return getClassTracesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashRequest,
      com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashResponse> getClassHashMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClassHash",
      requestType = com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashRequest.class,
      responseType = com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashRequest,
      com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashResponse> getClassHashMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashRequest, com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashResponse> getClassHashMethod;
    if ((getClassHashMethod = QueryGrpc.getClassHashMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClassHashMethod = QueryGrpc.getClassHashMethod) == null) {
          QueryGrpc.getClassHashMethod = getClassHashMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashRequest, com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClassHash"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClassHash"))
              .build();
        }
      }
    }
    return getClassHashMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressRequest,
      com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressResponse> getEscrowAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EscrowAddress",
      requestType = com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressRequest.class,
      responseType = com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressRequest,
      com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressResponse> getEscrowAddressMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressRequest, com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressResponse> getEscrowAddressMethod;
    if ((getEscrowAddressMethod = QueryGrpc.getEscrowAddressMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEscrowAddressMethod = QueryGrpc.getEscrowAddressMethod) == null) {
          QueryGrpc.getEscrowAddressMethod = getEscrowAddressMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressRequest, com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EscrowAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EscrowAddress"))
              .build();
        }
      }
    }
    return getEscrowAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsRequest,
      com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsRequest.class,
      responseType = com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsRequest,
      com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsRequest, com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsRequest, com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
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
   * Query provides defines the gRPC querier service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * ClassTrace queries a denomination trace information.
     * </pre>
     */
    default void classTrace(com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClassTraceMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClassTraces queries all denomination traces.
     * </pre>
     */
    default void classTraces(com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClassTracesMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClassHash queries a class id hash information.
     * </pre>
     */
    default void classHash(com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClassHashMethod(), responseObserver);
    }

    /**
     * <pre>
     * EscrowAddress returns the escrow address for a particular port and channel id.
     * </pre>
     */
    default void escrowAddress(com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEscrowAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries all parameters of the ibc-transfer module.
     * </pre>
     */
    default void params(com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query provides defines the gRPC querier service.
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
   * Query provides defines the gRPC querier service.
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
     * ClassTrace queries a denomination trace information.
     * </pre>
     */
    public void classTrace(com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClassTraceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClassTraces queries all denomination traces.
     * </pre>
     */
    public void classTraces(com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClassTracesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClassHash queries a class id hash information.
     * </pre>
     */
    public void classHash(com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClassHashMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EscrowAddress returns the escrow address for a particular port and channel id.
     * </pre>
     */
    public void escrowAddress(com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEscrowAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries all parameters of the ibc-transfer module.
     * </pre>
     */
    public void params(com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query provides defines the gRPC querier service.
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
     * ClassTrace queries a denomination trace information.
     * </pre>
     */
    public com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceResponse classTrace(com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClassTraceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClassTraces queries all denomination traces.
     * </pre>
     */
    public com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesResponse classTraces(com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClassTracesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClassHash queries a class id hash information.
     * </pre>
     */
    public com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashResponse classHash(com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClassHashMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EscrowAddress returns the escrow address for a particular port and channel id.
     * </pre>
     */
    public com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressResponse escrowAddress(com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEscrowAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries all parameters of the ibc-transfer module.
     * </pre>
     */
    public com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsResponse params(com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query provides defines the gRPC querier service.
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
     * ClassTrace queries a denomination trace information.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceResponse> classTrace(
        com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClassTraceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClassTraces queries all denomination traces.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesResponse> classTraces(
        com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClassTracesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClassHash queries a class id hash information.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashResponse> classHash(
        com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClassHashMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EscrowAddress returns the escrow address for a particular port and channel id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressResponse> escrowAddress(
        com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEscrowAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries all parameters of the ibc-transfer module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsResponse> params(
        com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CLASS_TRACE = 0;
  private static final int METHODID_CLASS_TRACES = 1;
  private static final int METHODID_CLASS_HASH = 2;
  private static final int METHODID_ESCROW_ADDRESS = 3;
  private static final int METHODID_PARAMS = 4;

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
        case METHODID_CLASS_TRACE:
          serviceImpl.classTrace((com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceResponse>) responseObserver);
          break;
        case METHODID_CLASS_TRACES:
          serviceImpl.classTraces((com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesResponse>) responseObserver);
          break;
        case METHODID_CLASS_HASH:
          serviceImpl.classHash((com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashResponse>) responseObserver);
          break;
        case METHODID_ESCROW_ADDRESS:
          serviceImpl.escrowAddress((com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsResponse>) responseObserver);
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
          getClassTraceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceRequest,
              com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTraceResponse>(
                service, METHODID_CLASS_TRACE)))
        .addMethod(
          getClassTracesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesRequest,
              com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassTracesResponse>(
                service, METHODID_CLASS_TRACES)))
        .addMethod(
          getClassHashMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashRequest,
              com.ibc.applications.nft_transfer.v1.QueryProto.QueryClassHashResponse>(
                service, METHODID_CLASS_HASH)))
        .addMethod(
          getEscrowAddressMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressRequest,
              com.ibc.applications.nft_transfer.v1.QueryProto.QueryEscrowAddressResponse>(
                service, METHODID_ESCROW_ADDRESS)))
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsRequest,
              com.ibc.applications.nft_transfer.v1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ibc.applications.nft_transfer.v1.QueryProto.getDescriptor();
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
              .addMethod(getClassTraceMethod())
              .addMethod(getClassTracesMethod())
              .addMethod(getClassHashMethod())
              .addMethod(getEscrowAddressMethod())
              .addMethod(getParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
