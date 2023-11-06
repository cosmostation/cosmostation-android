package com.ibc.core.connection.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query provides defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: ibc/core/connection/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "ibc.core.connection.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryConnectionRequest,
      com.ibc.core.connection.v1.QueryProto.QueryConnectionResponse> getConnectionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Connection",
      requestType = com.ibc.core.connection.v1.QueryProto.QueryConnectionRequest.class,
      responseType = com.ibc.core.connection.v1.QueryProto.QueryConnectionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryConnectionRequest,
      com.ibc.core.connection.v1.QueryProto.QueryConnectionResponse> getConnectionMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryConnectionRequest, com.ibc.core.connection.v1.QueryProto.QueryConnectionResponse> getConnectionMethod;
    if ((getConnectionMethod = QueryGrpc.getConnectionMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getConnectionMethod = QueryGrpc.getConnectionMethod) == null) {
          QueryGrpc.getConnectionMethod = getConnectionMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.connection.v1.QueryProto.QueryConnectionRequest, com.ibc.core.connection.v1.QueryProto.QueryConnectionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Connection"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.QueryProto.QueryConnectionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.QueryProto.QueryConnectionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Connection"))
              .build();
        }
      }
    }
    return getConnectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryConnectionsRequest,
      com.ibc.core.connection.v1.QueryProto.QueryConnectionsResponse> getConnectionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Connections",
      requestType = com.ibc.core.connection.v1.QueryProto.QueryConnectionsRequest.class,
      responseType = com.ibc.core.connection.v1.QueryProto.QueryConnectionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryConnectionsRequest,
      com.ibc.core.connection.v1.QueryProto.QueryConnectionsResponse> getConnectionsMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryConnectionsRequest, com.ibc.core.connection.v1.QueryProto.QueryConnectionsResponse> getConnectionsMethod;
    if ((getConnectionsMethod = QueryGrpc.getConnectionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getConnectionsMethod = QueryGrpc.getConnectionsMethod) == null) {
          QueryGrpc.getConnectionsMethod = getConnectionsMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.connection.v1.QueryProto.QueryConnectionsRequest, com.ibc.core.connection.v1.QueryProto.QueryConnectionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Connections"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.QueryProto.QueryConnectionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.QueryProto.QueryConnectionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Connections"))
              .build();
        }
      }
    }
    return getConnectionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsRequest,
      com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsResponse> getClientConnectionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClientConnections",
      requestType = com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsRequest.class,
      responseType = com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsRequest,
      com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsResponse> getClientConnectionsMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsRequest, com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsResponse> getClientConnectionsMethod;
    if ((getClientConnectionsMethod = QueryGrpc.getClientConnectionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClientConnectionsMethod = QueryGrpc.getClientConnectionsMethod) == null) {
          QueryGrpc.getClientConnectionsMethod = getClientConnectionsMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsRequest, com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClientConnections"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClientConnections"))
              .build();
        }
      }
    }
    return getClientConnectionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateRequest,
      com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateResponse> getConnectionClientStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConnectionClientState",
      requestType = com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateRequest.class,
      responseType = com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateRequest,
      com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateResponse> getConnectionClientStateMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateRequest, com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateResponse> getConnectionClientStateMethod;
    if ((getConnectionClientStateMethod = QueryGrpc.getConnectionClientStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getConnectionClientStateMethod = QueryGrpc.getConnectionClientStateMethod) == null) {
          QueryGrpc.getConnectionClientStateMethod = getConnectionClientStateMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateRequest, com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConnectionClientState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ConnectionClientState"))
              .build();
        }
      }
    }
    return getConnectionClientStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateRequest,
      com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateResponse> getConnectionConsensusStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConnectionConsensusState",
      requestType = com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateRequest.class,
      responseType = com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateRequest,
      com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateResponse> getConnectionConsensusStateMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateRequest, com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateResponse> getConnectionConsensusStateMethod;
    if ((getConnectionConsensusStateMethod = QueryGrpc.getConnectionConsensusStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getConnectionConsensusStateMethod = QueryGrpc.getConnectionConsensusStateMethod) == null) {
          QueryGrpc.getConnectionConsensusStateMethod = getConnectionConsensusStateMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateRequest, com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConnectionConsensusState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ConnectionConsensusState"))
              .build();
        }
      }
    }
    return getConnectionConsensusStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsRequest,
      com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsResponse> getConnectionParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConnectionParams",
      requestType = com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsRequest.class,
      responseType = com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsRequest,
      com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsResponse> getConnectionParamsMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsRequest, com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsResponse> getConnectionParamsMethod;
    if ((getConnectionParamsMethod = QueryGrpc.getConnectionParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getConnectionParamsMethod = QueryGrpc.getConnectionParamsMethod) == null) {
          QueryGrpc.getConnectionParamsMethod = getConnectionParamsMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsRequest, com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConnectionParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ConnectionParams"))
              .build();
        }
      }
    }
    return getConnectionParamsMethod;
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
   * Query provides defines the gRPC querier service
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Connection queries an IBC connection end.
     * </pre>
     */
    default void connection(com.ibc.core.connection.v1.QueryProto.QueryConnectionRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryConnectionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConnectionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Connections queries all the IBC connections of a chain.
     * </pre>
     */
    default void connections(com.ibc.core.connection.v1.QueryProto.QueryConnectionsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryConnectionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConnectionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClientConnections queries the connection paths associated with a client
     * state.
     * </pre>
     */
    default void clientConnections(com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClientConnectionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConnectionClientState queries the client state associated with the
     * connection.
     * </pre>
     */
    default void connectionClientState(com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConnectionClientStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConnectionConsensusState queries the consensus state associated with the
     * connection.
     * </pre>
     */
    default void connectionConsensusState(com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConnectionConsensusStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConnectionParams queries all parameters of the ibc connection submodule.
     * </pre>
     */
    default void connectionParams(com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConnectionParamsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query provides defines the gRPC querier service
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
   * Query provides defines the gRPC querier service
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
     * Connection queries an IBC connection end.
     * </pre>
     */
    public void connection(com.ibc.core.connection.v1.QueryProto.QueryConnectionRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryConnectionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConnectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Connections queries all the IBC connections of a chain.
     * </pre>
     */
    public void connections(com.ibc.core.connection.v1.QueryProto.QueryConnectionsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryConnectionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConnectionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClientConnections queries the connection paths associated with a client
     * state.
     * </pre>
     */
    public void clientConnections(com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClientConnectionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConnectionClientState queries the client state associated with the
     * connection.
     * </pre>
     */
    public void connectionClientState(com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConnectionClientStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConnectionConsensusState queries the consensus state associated with the
     * connection.
     * </pre>
     */
    public void connectionConsensusState(com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConnectionConsensusStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConnectionParams queries all parameters of the ibc connection submodule.
     * </pre>
     */
    public void connectionParams(com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConnectionParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query provides defines the gRPC querier service
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
     * Connection queries an IBC connection end.
     * </pre>
     */
    public com.ibc.core.connection.v1.QueryProto.QueryConnectionResponse connection(com.ibc.core.connection.v1.QueryProto.QueryConnectionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConnectionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Connections queries all the IBC connections of a chain.
     * </pre>
     */
    public com.ibc.core.connection.v1.QueryProto.QueryConnectionsResponse connections(com.ibc.core.connection.v1.QueryProto.QueryConnectionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConnectionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClientConnections queries the connection paths associated with a client
     * state.
     * </pre>
     */
    public com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsResponse clientConnections(com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClientConnectionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConnectionClientState queries the client state associated with the
     * connection.
     * </pre>
     */
    public com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateResponse connectionClientState(com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConnectionClientStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConnectionConsensusState queries the consensus state associated with the
     * connection.
     * </pre>
     */
    public com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateResponse connectionConsensusState(com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConnectionConsensusStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConnectionParams queries all parameters of the ibc connection submodule.
     * </pre>
     */
    public com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsResponse connectionParams(com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConnectionParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query provides defines the gRPC querier service
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
     * Connection queries an IBC connection end.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.connection.v1.QueryProto.QueryConnectionResponse> connection(
        com.ibc.core.connection.v1.QueryProto.QueryConnectionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConnectionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Connections queries all the IBC connections of a chain.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.connection.v1.QueryProto.QueryConnectionsResponse> connections(
        com.ibc.core.connection.v1.QueryProto.QueryConnectionsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConnectionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClientConnections queries the connection paths associated with a client
     * state.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsResponse> clientConnections(
        com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClientConnectionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConnectionClientState queries the client state associated with the
     * connection.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateResponse> connectionClientState(
        com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConnectionClientStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConnectionConsensusState queries the consensus state associated with the
     * connection.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateResponse> connectionConsensusState(
        com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConnectionConsensusStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConnectionParams queries all parameters of the ibc connection submodule.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsResponse> connectionParams(
        com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConnectionParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CONNECTION = 0;
  private static final int METHODID_CONNECTIONS = 1;
  private static final int METHODID_CLIENT_CONNECTIONS = 2;
  private static final int METHODID_CONNECTION_CLIENT_STATE = 3;
  private static final int METHODID_CONNECTION_CONSENSUS_STATE = 4;
  private static final int METHODID_CONNECTION_PARAMS = 5;

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
        case METHODID_CONNECTION:
          serviceImpl.connection((com.ibc.core.connection.v1.QueryProto.QueryConnectionRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryConnectionResponse>) responseObserver);
          break;
        case METHODID_CONNECTIONS:
          serviceImpl.connections((com.ibc.core.connection.v1.QueryProto.QueryConnectionsRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryConnectionsResponse>) responseObserver);
          break;
        case METHODID_CLIENT_CONNECTIONS:
          serviceImpl.clientConnections((com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsResponse>) responseObserver);
          break;
        case METHODID_CONNECTION_CLIENT_STATE:
          serviceImpl.connectionClientState((com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateResponse>) responseObserver);
          break;
        case METHODID_CONNECTION_CONSENSUS_STATE:
          serviceImpl.connectionConsensusState((com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateResponse>) responseObserver);
          break;
        case METHODID_CONNECTION_PARAMS:
          serviceImpl.connectionParams((com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsResponse>) responseObserver);
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
          getConnectionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.connection.v1.QueryProto.QueryConnectionRequest,
              com.ibc.core.connection.v1.QueryProto.QueryConnectionResponse>(
                service, METHODID_CONNECTION)))
        .addMethod(
          getConnectionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.connection.v1.QueryProto.QueryConnectionsRequest,
              com.ibc.core.connection.v1.QueryProto.QueryConnectionsResponse>(
                service, METHODID_CONNECTIONS)))
        .addMethod(
          getClientConnectionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsRequest,
              com.ibc.core.connection.v1.QueryProto.QueryClientConnectionsResponse>(
                service, METHODID_CLIENT_CONNECTIONS)))
        .addMethod(
          getConnectionClientStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateRequest,
              com.ibc.core.connection.v1.QueryProto.QueryConnectionClientStateResponse>(
                service, METHODID_CONNECTION_CLIENT_STATE)))
        .addMethod(
          getConnectionConsensusStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateRequest,
              com.ibc.core.connection.v1.QueryProto.QueryConnectionConsensusStateResponse>(
                service, METHODID_CONNECTION_CONSENSUS_STATE)))
        .addMethod(
          getConnectionParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsRequest,
              com.ibc.core.connection.v1.QueryProto.QueryConnectionParamsResponse>(
                service, METHODID_CONNECTION_PARAMS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ibc.core.connection.v1.QueryProto.getDescriptor();
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
              .addMethod(getConnectionMethod())
              .addMethod(getConnectionsMethod())
              .addMethod(getClientConnectionsMethod())
              .addMethod(getConnectionClientStateMethod())
              .addMethod(getConnectionConsensusStateMethod())
              .addMethod(getConnectionParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
