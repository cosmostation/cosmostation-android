package com.ibc.applications.transfer.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query provides defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: ibc/applications/transfer/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "ibc.applications.transfer.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceRequest,
      com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceResponse> getDenomTraceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomTrace",
      requestType = com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceRequest.class,
      responseType = com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceRequest,
      com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceResponse> getDenomTraceMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceRequest, com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceResponse> getDenomTraceMethod;
    if ((getDenomTraceMethod = QueryGrpc.getDenomTraceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomTraceMethod = QueryGrpc.getDenomTraceMethod) == null) {
          QueryGrpc.getDenomTraceMethod = getDenomTraceMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceRequest, com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomTrace"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomTrace"))
              .build();
        }
      }
    }
    return getDenomTraceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesRequest,
      com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesResponse> getDenomTracesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomTraces",
      requestType = com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesRequest.class,
      responseType = com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesRequest,
      com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesResponse> getDenomTracesMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesRequest, com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesResponse> getDenomTracesMethod;
    if ((getDenomTracesMethod = QueryGrpc.getDenomTracesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomTracesMethod = QueryGrpc.getDenomTracesMethod) == null) {
          QueryGrpc.getDenomTracesMethod = getDenomTracesMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesRequest, com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomTraces"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomTraces"))
              .build();
        }
      }
    }
    return getDenomTracesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryParamsRequest,
      com.ibc.applications.transfer.v1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.ibc.applications.transfer.v1.QueryProto.QueryParamsRequest.class,
      responseType = com.ibc.applications.transfer.v1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryParamsRequest,
      com.ibc.applications.transfer.v1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryParamsRequest, com.ibc.applications.transfer.v1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.transfer.v1.QueryProto.QueryParamsRequest, com.ibc.applications.transfer.v1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.transfer.v1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.transfer.v1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashRequest,
      com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashResponse> getDenomHashMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomHash",
      requestType = com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashRequest.class,
      responseType = com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashRequest,
      com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashResponse> getDenomHashMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashRequest, com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashResponse> getDenomHashMethod;
    if ((getDenomHashMethod = QueryGrpc.getDenomHashMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomHashMethod = QueryGrpc.getDenomHashMethod) == null) {
          QueryGrpc.getDenomHashMethod = getDenomHashMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashRequest, com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomHash"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomHash"))
              .build();
        }
      }
    }
    return getDenomHashMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressRequest,
      com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressResponse> getEscrowAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EscrowAddress",
      requestType = com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressRequest.class,
      responseType = com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressRequest,
      com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressResponse> getEscrowAddressMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressRequest, com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressResponse> getEscrowAddressMethod;
    if ((getEscrowAddressMethod = QueryGrpc.getEscrowAddressMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEscrowAddressMethod = QueryGrpc.getEscrowAddressMethod) == null) {
          QueryGrpc.getEscrowAddressMethod = getEscrowAddressMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressRequest, com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EscrowAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EscrowAddress"))
              .build();
        }
      }
    }
    return getEscrowAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomRequest,
      com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomResponse> getTotalEscrowForDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalEscrowForDenom",
      requestType = com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomRequest.class,
      responseType = com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomRequest,
      com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomResponse> getTotalEscrowForDenomMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomRequest, com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomResponse> getTotalEscrowForDenomMethod;
    if ((getTotalEscrowForDenomMethod = QueryGrpc.getTotalEscrowForDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalEscrowForDenomMethod = QueryGrpc.getTotalEscrowForDenomMethod) == null) {
          QueryGrpc.getTotalEscrowForDenomMethod = getTotalEscrowForDenomMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomRequest, com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalEscrowForDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalEscrowForDenom"))
              .build();
        }
      }
    }
    return getTotalEscrowForDenomMethod;
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
     * DenomTrace queries a denomination trace information.
     * </pre>
     */
    default void denomTrace(com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDenomTraceMethod(), responseObserver);
    }

    /**
     * <pre>
     * DenomTraces queries all denomination traces.
     * </pre>
     */
    default void denomTraces(com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDenomTracesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries all parameters of the ibc-transfer module.
     * </pre>
     */
    default void params(com.ibc.applications.transfer.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * DenomHash queries a denomination hash information.
     * </pre>
     */
    default void denomHash(com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDenomHashMethod(), responseObserver);
    }

    /**
     * <pre>
     * EscrowAddress returns the escrow address for a particular port and channel id.
     * </pre>
     */
    default void escrowAddress(com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEscrowAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalEscrowForDenom returns the total amount of tokens in escrow based on the denom.
     * </pre>
     */
    default void totalEscrowForDenom(com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTotalEscrowForDenomMethod(), responseObserver);
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
     * DenomTrace queries a denomination trace information.
     * </pre>
     */
    public void denomTrace(com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDenomTraceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DenomTraces queries all denomination traces.
     * </pre>
     */
    public void denomTraces(com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDenomTracesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries all parameters of the ibc-transfer module.
     * </pre>
     */
    public void params(com.ibc.applications.transfer.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DenomHash queries a denomination hash information.
     * </pre>
     */
    public void denomHash(com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDenomHashMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EscrowAddress returns the escrow address for a particular port and channel id.
     * </pre>
     */
    public void escrowAddress(com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEscrowAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalEscrowForDenom returns the total amount of tokens in escrow based on the denom.
     * </pre>
     */
    public void totalEscrowForDenom(com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTotalEscrowForDenomMethod(), getCallOptions()), request, responseObserver);
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
     * DenomTrace queries a denomination trace information.
     * </pre>
     */
    public com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceResponse denomTrace(com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDenomTraceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DenomTraces queries all denomination traces.
     * </pre>
     */
    public com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesResponse denomTraces(com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDenomTracesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries all parameters of the ibc-transfer module.
     * </pre>
     */
    public com.ibc.applications.transfer.v1.QueryProto.QueryParamsResponse params(com.ibc.applications.transfer.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DenomHash queries a denomination hash information.
     * </pre>
     */
    public com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashResponse denomHash(com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDenomHashMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EscrowAddress returns the escrow address for a particular port and channel id.
     * </pre>
     */
    public com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressResponse escrowAddress(com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEscrowAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalEscrowForDenom returns the total amount of tokens in escrow based on the denom.
     * </pre>
     */
    public com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomResponse totalEscrowForDenom(com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTotalEscrowForDenomMethod(), getCallOptions(), request);
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
     * DenomTrace queries a denomination trace information.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceResponse> denomTrace(
        com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDenomTraceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DenomTraces queries all denomination traces.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesResponse> denomTraces(
        com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDenomTracesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries all parameters of the ibc-transfer module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.transfer.v1.QueryProto.QueryParamsResponse> params(
        com.ibc.applications.transfer.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DenomHash queries a denomination hash information.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashResponse> denomHash(
        com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDenomHashMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EscrowAddress returns the escrow address for a particular port and channel id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressResponse> escrowAddress(
        com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEscrowAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalEscrowForDenom returns the total amount of tokens in escrow based on the denom.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomResponse> totalEscrowForDenom(
        com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTotalEscrowForDenomMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DENOM_TRACE = 0;
  private static final int METHODID_DENOM_TRACES = 1;
  private static final int METHODID_PARAMS = 2;
  private static final int METHODID_DENOM_HASH = 3;
  private static final int METHODID_ESCROW_ADDRESS = 4;
  private static final int METHODID_TOTAL_ESCROW_FOR_DENOM = 5;

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
        case METHODID_DENOM_TRACE:
          serviceImpl.denomTrace((com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceResponse>) responseObserver);
          break;
        case METHODID_DENOM_TRACES:
          serviceImpl.denomTraces((com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((com.ibc.applications.transfer.v1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_DENOM_HASH:
          serviceImpl.denomHash((com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashResponse>) responseObserver);
          break;
        case METHODID_ESCROW_ADDRESS:
          serviceImpl.escrowAddress((com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressResponse>) responseObserver);
          break;
        case METHODID_TOTAL_ESCROW_FOR_DENOM:
          serviceImpl.totalEscrowForDenom((com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomResponse>) responseObserver);
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
          getDenomTraceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceRequest,
              com.ibc.applications.transfer.v1.QueryProto.QueryDenomTraceResponse>(
                service, METHODID_DENOM_TRACE)))
        .addMethod(
          getDenomTracesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesRequest,
              com.ibc.applications.transfer.v1.QueryProto.QueryDenomTracesResponse>(
                service, METHODID_DENOM_TRACES)))
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.transfer.v1.QueryProto.QueryParamsRequest,
              com.ibc.applications.transfer.v1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getDenomHashMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashRequest,
              com.ibc.applications.transfer.v1.QueryProto.QueryDenomHashResponse>(
                service, METHODID_DENOM_HASH)))
        .addMethod(
          getEscrowAddressMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressRequest,
              com.ibc.applications.transfer.v1.QueryProto.QueryEscrowAddressResponse>(
                service, METHODID_ESCROW_ADDRESS)))
        .addMethod(
          getTotalEscrowForDenomMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomRequest,
              com.ibc.applications.transfer.v1.QueryProto.QueryTotalEscrowForDenomResponse>(
                service, METHODID_TOTAL_ESCROW_FOR_DENOM)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ibc.applications.transfer.v1.QueryProto.getDescriptor();
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
              .addMethod(getDenomTraceMethod())
              .addMethod(getDenomTracesMethod())
              .addMethod(getParamsMethod())
              .addMethod(getDenomHashMethod())
              .addMethod(getEscrowAddressMethod())
              .addMethod(getTotalEscrowForDenomMethod())
              .build();
        }
      }
    }
    return result;
  }
}
