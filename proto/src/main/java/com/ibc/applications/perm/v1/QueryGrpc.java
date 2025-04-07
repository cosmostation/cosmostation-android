package com.ibc.applications.perm.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query provides defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: ibc/applications/perm/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "ibc.applications.perm.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelRequest,
      com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelResponse> getPermissionedRelayersByChannelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PermissionedRelayersByChannel",
      requestType = com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelRequest.class,
      responseType = com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelRequest,
      com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelResponse> getPermissionedRelayersByChannelMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelRequest, com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelResponse> getPermissionedRelayersByChannelMethod;
    if ((getPermissionedRelayersByChannelMethod = QueryGrpc.getPermissionedRelayersByChannelMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPermissionedRelayersByChannelMethod = QueryGrpc.getPermissionedRelayersByChannelMethod) == null) {
          QueryGrpc.getPermissionedRelayersByChannelMethod = getPermissionedRelayersByChannelMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelRequest, com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PermissionedRelayersByChannel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PermissionedRelayersByChannel"))
              .build();
        }
      }
    }
    return getPermissionedRelayersByChannelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersRequest,
      com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersResponse> getAllPermissionedRelayersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllPermissionedRelayers",
      requestType = com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersRequest.class,
      responseType = com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersRequest,
      com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersResponse> getAllPermissionedRelayersMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersRequest, com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersResponse> getAllPermissionedRelayersMethod;
    if ((getAllPermissionedRelayersMethod = QueryGrpc.getAllPermissionedRelayersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllPermissionedRelayersMethod = QueryGrpc.getAllPermissionedRelayersMethod) == null) {
          QueryGrpc.getAllPermissionedRelayersMethod = getAllPermissionedRelayersMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersRequest, com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllPermissionedRelayers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllPermissionedRelayers"))
              .build();
        }
      }
    }
    return getAllPermissionedRelayersMethod;
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
     * PermissionedRelayersByChannel queries a set of permissioned ibc relayers for the specific channel.
     * </pre>
     */
    default void permissionedRelayersByChannel(com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPermissionedRelayersByChannelMethod(), responseObserver);
    }

    /**
     * <pre>
     * AllPermissionedRelayers queries all sets of permissioned relayers for all channels.
     * </pre>
     */
    default void allPermissionedRelayers(com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAllPermissionedRelayersMethod(), responseObserver);
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
     * PermissionedRelayersByChannel queries a set of permissioned ibc relayers for the specific channel.
     * </pre>
     */
    public void permissionedRelayersByChannel(com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPermissionedRelayersByChannelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AllPermissionedRelayers queries all sets of permissioned relayers for all channels.
     * </pre>
     */
    public void allPermissionedRelayers(com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAllPermissionedRelayersMethod(), getCallOptions()), request, responseObserver);
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
     * PermissionedRelayersByChannel queries a set of permissioned ibc relayers for the specific channel.
     * </pre>
     */
    public com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelResponse permissionedRelayersByChannel(com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPermissionedRelayersByChannelMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AllPermissionedRelayers queries all sets of permissioned relayers for all channels.
     * </pre>
     */
    public com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersResponse allPermissionedRelayers(com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAllPermissionedRelayersMethod(), getCallOptions(), request);
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
     * PermissionedRelayersByChannel queries a set of permissioned ibc relayers for the specific channel.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelResponse> permissionedRelayersByChannel(
        com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPermissionedRelayersByChannelMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AllPermissionedRelayers queries all sets of permissioned relayers for all channels.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersResponse> allPermissionedRelayers(
        com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAllPermissionedRelayersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PERMISSIONED_RELAYERS_BY_CHANNEL = 0;
  private static final int METHODID_ALL_PERMISSIONED_RELAYERS = 1;

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
        case METHODID_PERMISSIONED_RELAYERS_BY_CHANNEL:
          serviceImpl.permissionedRelayersByChannel((com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelResponse>) responseObserver);
          break;
        case METHODID_ALL_PERMISSIONED_RELAYERS:
          serviceImpl.allPermissionedRelayers((com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersResponse>) responseObserver);
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
          getPermissionedRelayersByChannelMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelRequest,
              com.ibc.applications.perm.v1.QueryProto.QueryPermissionedRelayersByChannelResponse>(
                service, METHODID_PERMISSIONED_RELAYERS_BY_CHANNEL)))
        .addMethod(
          getAllPermissionedRelayersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersRequest,
              com.ibc.applications.perm.v1.QueryProto.QueryAllPermissionedRelayersResponse>(
                service, METHODID_ALL_PERMISSIONED_RELAYERS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ibc.applications.perm.v1.QueryProto.getDescriptor();
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
              .addMethod(getPermissionedRelayersByChannelMethod())
              .addMethod(getAllPermissionedRelayersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
