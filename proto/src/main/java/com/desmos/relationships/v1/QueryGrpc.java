package com.desmos.relationships.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: desmos/relationships/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "desmos.relationships.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.desmos.relationships.v1.QueryProto.QueryRelationshipsRequest,
      com.desmos.relationships.v1.QueryProto.QueryRelationshipsResponse> getRelationshipsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Relationships",
      requestType = com.desmos.relationships.v1.QueryProto.QueryRelationshipsRequest.class,
      responseType = com.desmos.relationships.v1.QueryProto.QueryRelationshipsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.relationships.v1.QueryProto.QueryRelationshipsRequest,
      com.desmos.relationships.v1.QueryProto.QueryRelationshipsResponse> getRelationshipsMethod() {
    io.grpc.MethodDescriptor<com.desmos.relationships.v1.QueryProto.QueryRelationshipsRequest, com.desmos.relationships.v1.QueryProto.QueryRelationshipsResponse> getRelationshipsMethod;
    if ((getRelationshipsMethod = QueryGrpc.getRelationshipsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRelationshipsMethod = QueryGrpc.getRelationshipsMethod) == null) {
          QueryGrpc.getRelationshipsMethod = getRelationshipsMethod =
              io.grpc.MethodDescriptor.<com.desmos.relationships.v1.QueryProto.QueryRelationshipsRequest, com.desmos.relationships.v1.QueryProto.QueryRelationshipsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Relationships"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.relationships.v1.QueryProto.QueryRelationshipsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.relationships.v1.QueryProto.QueryRelationshipsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Relationships"))
              .build();
        }
      }
    }
    return getRelationshipsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.relationships.v1.QueryProto.QueryBlocksRequest,
      com.desmos.relationships.v1.QueryProto.QueryBlocksResponse> getBlocksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Blocks",
      requestType = com.desmos.relationships.v1.QueryProto.QueryBlocksRequest.class,
      responseType = com.desmos.relationships.v1.QueryProto.QueryBlocksResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.relationships.v1.QueryProto.QueryBlocksRequest,
      com.desmos.relationships.v1.QueryProto.QueryBlocksResponse> getBlocksMethod() {
    io.grpc.MethodDescriptor<com.desmos.relationships.v1.QueryProto.QueryBlocksRequest, com.desmos.relationships.v1.QueryProto.QueryBlocksResponse> getBlocksMethod;
    if ((getBlocksMethod = QueryGrpc.getBlocksMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBlocksMethod = QueryGrpc.getBlocksMethod) == null) {
          QueryGrpc.getBlocksMethod = getBlocksMethod =
              io.grpc.MethodDescriptor.<com.desmos.relationships.v1.QueryProto.QueryBlocksRequest, com.desmos.relationships.v1.QueryProto.QueryBlocksResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Blocks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.relationships.v1.QueryProto.QueryBlocksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.relationships.v1.QueryProto.QueryBlocksResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Blocks"))
              .build();
        }
      }
    }
    return getBlocksMethod;
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
     * Relationships queries all relationships present inside a specific subspace
     * </pre>
     */
    default void relationships(com.desmos.relationships.v1.QueryProto.QueryRelationshipsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.relationships.v1.QueryProto.QueryRelationshipsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRelationshipsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Blocks queries the blocks for the given user, if provided.
     * Otherwise, it queries all the stored blocks.
     * </pre>
     */
    default void blocks(com.desmos.relationships.v1.QueryProto.QueryBlocksRequest request,
        io.grpc.stub.StreamObserver<com.desmos.relationships.v1.QueryProto.QueryBlocksResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBlocksMethod(), responseObserver);
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
     * Relationships queries all relationships present inside a specific subspace
     * </pre>
     */
    public void relationships(com.desmos.relationships.v1.QueryProto.QueryRelationshipsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.relationships.v1.QueryProto.QueryRelationshipsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRelationshipsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Blocks queries the blocks for the given user, if provided.
     * Otherwise, it queries all the stored blocks.
     * </pre>
     */
    public void blocks(com.desmos.relationships.v1.QueryProto.QueryBlocksRequest request,
        io.grpc.stub.StreamObserver<com.desmos.relationships.v1.QueryProto.QueryBlocksResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBlocksMethod(), getCallOptions()), request, responseObserver);
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
     * Relationships queries all relationships present inside a specific subspace
     * </pre>
     */
    public com.desmos.relationships.v1.QueryProto.QueryRelationshipsResponse relationships(com.desmos.relationships.v1.QueryProto.QueryRelationshipsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRelationshipsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Blocks queries the blocks for the given user, if provided.
     * Otherwise, it queries all the stored blocks.
     * </pre>
     */
    public com.desmos.relationships.v1.QueryProto.QueryBlocksResponse blocks(com.desmos.relationships.v1.QueryProto.QueryBlocksRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBlocksMethod(), getCallOptions(), request);
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
     * Relationships queries all relationships present inside a specific subspace
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.relationships.v1.QueryProto.QueryRelationshipsResponse> relationships(
        com.desmos.relationships.v1.QueryProto.QueryRelationshipsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRelationshipsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Blocks queries the blocks for the given user, if provided.
     * Otherwise, it queries all the stored blocks.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.relationships.v1.QueryProto.QueryBlocksResponse> blocks(
        com.desmos.relationships.v1.QueryProto.QueryBlocksRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBlocksMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RELATIONSHIPS = 0;
  private static final int METHODID_BLOCKS = 1;

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
        case METHODID_RELATIONSHIPS:
          serviceImpl.relationships((com.desmos.relationships.v1.QueryProto.QueryRelationshipsRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.relationships.v1.QueryProto.QueryRelationshipsResponse>) responseObserver);
          break;
        case METHODID_BLOCKS:
          serviceImpl.blocks((com.desmos.relationships.v1.QueryProto.QueryBlocksRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.relationships.v1.QueryProto.QueryBlocksResponse>) responseObserver);
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
          getRelationshipsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.relationships.v1.QueryProto.QueryRelationshipsRequest,
              com.desmos.relationships.v1.QueryProto.QueryRelationshipsResponse>(
                service, METHODID_RELATIONSHIPS)))
        .addMethod(
          getBlocksMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.relationships.v1.QueryProto.QueryBlocksRequest,
              com.desmos.relationships.v1.QueryProto.QueryBlocksResponse>(
                service, METHODID_BLOCKS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.desmos.relationships.v1.QueryProto.getDescriptor();
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
              .addMethod(getRelationshipsMethod())
              .addMethod(getBlocksMethod())
              .build();
        }
      }
    }
    return result;
  }
}
