package com.desmos.reactions.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: desmos/reactions/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "desmos.reactions.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.desmos.reactions.v1.QueryProto.QueryReactionsRequest,
      com.desmos.reactions.v1.QueryProto.QueryReactionsResponse> getReactionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Reactions",
      requestType = com.desmos.reactions.v1.QueryProto.QueryReactionsRequest.class,
      responseType = com.desmos.reactions.v1.QueryProto.QueryReactionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reactions.v1.QueryProto.QueryReactionsRequest,
      com.desmos.reactions.v1.QueryProto.QueryReactionsResponse> getReactionsMethod() {
    io.grpc.MethodDescriptor<com.desmos.reactions.v1.QueryProto.QueryReactionsRequest, com.desmos.reactions.v1.QueryProto.QueryReactionsResponse> getReactionsMethod;
    if ((getReactionsMethod = QueryGrpc.getReactionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getReactionsMethod = QueryGrpc.getReactionsMethod) == null) {
          QueryGrpc.getReactionsMethod = getReactionsMethod =
              io.grpc.MethodDescriptor.<com.desmos.reactions.v1.QueryProto.QueryReactionsRequest, com.desmos.reactions.v1.QueryProto.QueryReactionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Reactions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.QueryProto.QueryReactionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.QueryProto.QueryReactionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Reactions"))
              .build();
        }
      }
    }
    return getReactionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reactions.v1.QueryProto.QueryReactionRequest,
      com.desmos.reactions.v1.QueryProto.QueryReactionResponse> getReactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Reaction",
      requestType = com.desmos.reactions.v1.QueryProto.QueryReactionRequest.class,
      responseType = com.desmos.reactions.v1.QueryProto.QueryReactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reactions.v1.QueryProto.QueryReactionRequest,
      com.desmos.reactions.v1.QueryProto.QueryReactionResponse> getReactionMethod() {
    io.grpc.MethodDescriptor<com.desmos.reactions.v1.QueryProto.QueryReactionRequest, com.desmos.reactions.v1.QueryProto.QueryReactionResponse> getReactionMethod;
    if ((getReactionMethod = QueryGrpc.getReactionMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getReactionMethod = QueryGrpc.getReactionMethod) == null) {
          QueryGrpc.getReactionMethod = getReactionMethod =
              io.grpc.MethodDescriptor.<com.desmos.reactions.v1.QueryProto.QueryReactionRequest, com.desmos.reactions.v1.QueryProto.QueryReactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Reaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.QueryProto.QueryReactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.QueryProto.QueryReactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Reaction"))
              .build();
        }
      }
    }
    return getReactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsRequest,
      com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsResponse> getRegisteredReactionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisteredReactions",
      requestType = com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsRequest.class,
      responseType = com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsRequest,
      com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsResponse> getRegisteredReactionsMethod() {
    io.grpc.MethodDescriptor<com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsRequest, com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsResponse> getRegisteredReactionsMethod;
    if ((getRegisteredReactionsMethod = QueryGrpc.getRegisteredReactionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRegisteredReactionsMethod = QueryGrpc.getRegisteredReactionsMethod) == null) {
          QueryGrpc.getRegisteredReactionsMethod = getRegisteredReactionsMethod =
              io.grpc.MethodDescriptor.<com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsRequest, com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisteredReactions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RegisteredReactions"))
              .build();
        }
      }
    }
    return getRegisteredReactionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionRequest,
      com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionResponse> getRegisteredReactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisteredReaction",
      requestType = com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionRequest.class,
      responseType = com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionRequest,
      com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionResponse> getRegisteredReactionMethod() {
    io.grpc.MethodDescriptor<com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionRequest, com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionResponse> getRegisteredReactionMethod;
    if ((getRegisteredReactionMethod = QueryGrpc.getRegisteredReactionMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRegisteredReactionMethod = QueryGrpc.getRegisteredReactionMethod) == null) {
          QueryGrpc.getRegisteredReactionMethod = getRegisteredReactionMethod =
              io.grpc.MethodDescriptor.<com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionRequest, com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisteredReaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RegisteredReaction"))
              .build();
        }
      }
    }
    return getRegisteredReactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reactions.v1.QueryProto.QueryReactionsParamsRequest,
      com.desmos.reactions.v1.QueryProto.QueryReactionsParamsResponse> getReactionsParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReactionsParams",
      requestType = com.desmos.reactions.v1.QueryProto.QueryReactionsParamsRequest.class,
      responseType = com.desmos.reactions.v1.QueryProto.QueryReactionsParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reactions.v1.QueryProto.QueryReactionsParamsRequest,
      com.desmos.reactions.v1.QueryProto.QueryReactionsParamsResponse> getReactionsParamsMethod() {
    io.grpc.MethodDescriptor<com.desmos.reactions.v1.QueryProto.QueryReactionsParamsRequest, com.desmos.reactions.v1.QueryProto.QueryReactionsParamsResponse> getReactionsParamsMethod;
    if ((getReactionsParamsMethod = QueryGrpc.getReactionsParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getReactionsParamsMethod = QueryGrpc.getReactionsParamsMethod) == null) {
          QueryGrpc.getReactionsParamsMethod = getReactionsParamsMethod =
              io.grpc.MethodDescriptor.<com.desmos.reactions.v1.QueryProto.QueryReactionsParamsRequest, com.desmos.reactions.v1.QueryProto.QueryReactionsParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReactionsParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.QueryProto.QueryReactionsParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.QueryProto.QueryReactionsParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ReactionsParams"))
              .build();
        }
      }
    }
    return getReactionsParamsMethod;
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
     * Reactions allows to query the reactions for a given post
     * </pre>
     */
    default void reactions(com.desmos.reactions.v1.QueryProto.QueryReactionsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.QueryProto.QueryReactionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReactionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Reaction allows to query the reaction with the given id
     * </pre>
     */
    default void reaction(com.desmos.reactions.v1.QueryProto.QueryReactionRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.QueryProto.QueryReactionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * RegisteredReactions allows to query the registered reaction of a subspace
     * </pre>
     */
    default void registeredReactions(com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisteredReactionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * RegisteredReaction allows to query the registered reaction of a subspace
     * </pre>
     */
    default void registeredReaction(com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisteredReactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * ReactionsParams allows to query the reaction params of a subspace
     * </pre>
     */
    default void reactionsParams(com.desmos.reactions.v1.QueryProto.QueryReactionsParamsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.QueryProto.QueryReactionsParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReactionsParamsMethod(), responseObserver);
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
     * Reactions allows to query the reactions for a given post
     * </pre>
     */
    public void reactions(com.desmos.reactions.v1.QueryProto.QueryReactionsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.QueryProto.QueryReactionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReactionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Reaction allows to query the reaction with the given id
     * </pre>
     */
    public void reaction(com.desmos.reactions.v1.QueryProto.QueryReactionRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.QueryProto.QueryReactionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RegisteredReactions allows to query the registered reaction of a subspace
     * </pre>
     */
    public void registeredReactions(com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisteredReactionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RegisteredReaction allows to query the registered reaction of a subspace
     * </pre>
     */
    public void registeredReaction(com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisteredReactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ReactionsParams allows to query the reaction params of a subspace
     * </pre>
     */
    public void reactionsParams(com.desmos.reactions.v1.QueryProto.QueryReactionsParamsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.QueryProto.QueryReactionsParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReactionsParamsMethod(), getCallOptions()), request, responseObserver);
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
     * Reactions allows to query the reactions for a given post
     * </pre>
     */
    public com.desmos.reactions.v1.QueryProto.QueryReactionsResponse reactions(com.desmos.reactions.v1.QueryProto.QueryReactionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReactionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Reaction allows to query the reaction with the given id
     * </pre>
     */
    public com.desmos.reactions.v1.QueryProto.QueryReactionResponse reaction(com.desmos.reactions.v1.QueryProto.QueryReactionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RegisteredReactions allows to query the registered reaction of a subspace
     * </pre>
     */
    public com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsResponse registeredReactions(com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisteredReactionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RegisteredReaction allows to query the registered reaction of a subspace
     * </pre>
     */
    public com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionResponse registeredReaction(com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisteredReactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ReactionsParams allows to query the reaction params of a subspace
     * </pre>
     */
    public com.desmos.reactions.v1.QueryProto.QueryReactionsParamsResponse reactionsParams(com.desmos.reactions.v1.QueryProto.QueryReactionsParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReactionsParamsMethod(), getCallOptions(), request);
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
     * Reactions allows to query the reactions for a given post
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reactions.v1.QueryProto.QueryReactionsResponse> reactions(
        com.desmos.reactions.v1.QueryProto.QueryReactionsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReactionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Reaction allows to query the reaction with the given id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reactions.v1.QueryProto.QueryReactionResponse> reaction(
        com.desmos.reactions.v1.QueryProto.QueryReactionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RegisteredReactions allows to query the registered reaction of a subspace
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsResponse> registeredReactions(
        com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisteredReactionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RegisteredReaction allows to query the registered reaction of a subspace
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionResponse> registeredReaction(
        com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisteredReactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ReactionsParams allows to query the reaction params of a subspace
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reactions.v1.QueryProto.QueryReactionsParamsResponse> reactionsParams(
        com.desmos.reactions.v1.QueryProto.QueryReactionsParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReactionsParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REACTIONS = 0;
  private static final int METHODID_REACTION = 1;
  private static final int METHODID_REGISTERED_REACTIONS = 2;
  private static final int METHODID_REGISTERED_REACTION = 3;
  private static final int METHODID_REACTIONS_PARAMS = 4;

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
        case METHODID_REACTIONS:
          serviceImpl.reactions((com.desmos.reactions.v1.QueryProto.QueryReactionsRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.reactions.v1.QueryProto.QueryReactionsResponse>) responseObserver);
          break;
        case METHODID_REACTION:
          serviceImpl.reaction((com.desmos.reactions.v1.QueryProto.QueryReactionRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.reactions.v1.QueryProto.QueryReactionResponse>) responseObserver);
          break;
        case METHODID_REGISTERED_REACTIONS:
          serviceImpl.registeredReactions((com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsResponse>) responseObserver);
          break;
        case METHODID_REGISTERED_REACTION:
          serviceImpl.registeredReaction((com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionResponse>) responseObserver);
          break;
        case METHODID_REACTIONS_PARAMS:
          serviceImpl.reactionsParams((com.desmos.reactions.v1.QueryProto.QueryReactionsParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.reactions.v1.QueryProto.QueryReactionsParamsResponse>) responseObserver);
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
          getReactionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reactions.v1.QueryProto.QueryReactionsRequest,
              com.desmos.reactions.v1.QueryProto.QueryReactionsResponse>(
                service, METHODID_REACTIONS)))
        .addMethod(
          getReactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reactions.v1.QueryProto.QueryReactionRequest,
              com.desmos.reactions.v1.QueryProto.QueryReactionResponse>(
                service, METHODID_REACTION)))
        .addMethod(
          getRegisteredReactionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsRequest,
              com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionsResponse>(
                service, METHODID_REGISTERED_REACTIONS)))
        .addMethod(
          getRegisteredReactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionRequest,
              com.desmos.reactions.v1.QueryProto.QueryRegisteredReactionResponse>(
                service, METHODID_REGISTERED_REACTION)))
        .addMethod(
          getReactionsParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reactions.v1.QueryProto.QueryReactionsParamsRequest,
              com.desmos.reactions.v1.QueryProto.QueryReactionsParamsResponse>(
                service, METHODID_REACTIONS_PARAMS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.desmos.reactions.v1.QueryProto.getDescriptor();
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
              .addMethod(getReactionsMethod())
              .addMethod(getReactionMethod())
              .addMethod(getRegisteredReactionsMethod())
              .addMethod(getRegisteredReactionMethod())
              .addMethod(getReactionsParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
