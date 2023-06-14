package com.cosmos.authz.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmos/authz/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "cosmos.authz.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.QueryProto.QueryGrantsRequest,
      com.cosmos.authz.v1beta1.QueryProto.QueryGrantsResponse> getGrantsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Grants",
      requestType = com.cosmos.authz.v1beta1.QueryProto.QueryGrantsRequest.class,
      responseType = com.cosmos.authz.v1beta1.QueryProto.QueryGrantsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.QueryProto.QueryGrantsRequest,
      com.cosmos.authz.v1beta1.QueryProto.QueryGrantsResponse> getGrantsMethod() {
    io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.QueryProto.QueryGrantsRequest, com.cosmos.authz.v1beta1.QueryProto.QueryGrantsResponse> getGrantsMethod;
    if ((getGrantsMethod = QueryGrpc.getGrantsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGrantsMethod = QueryGrpc.getGrantsMethod) == null) {
          QueryGrpc.getGrantsMethod = getGrantsMethod =
              io.grpc.MethodDescriptor.<com.cosmos.authz.v1beta1.QueryProto.QueryGrantsRequest, com.cosmos.authz.v1beta1.QueryProto.QueryGrantsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Grants"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.authz.v1beta1.QueryProto.QueryGrantsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.authz.v1beta1.QueryProto.QueryGrantsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Grants"))
              .build();
        }
      }
    }
    return getGrantsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsRequest,
      com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsResponse> getGranterGrantsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GranterGrants",
      requestType = com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsRequest.class,
      responseType = com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsRequest,
      com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsResponse> getGranterGrantsMethod() {
    io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsRequest, com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsResponse> getGranterGrantsMethod;
    if ((getGranterGrantsMethod = QueryGrpc.getGranterGrantsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGranterGrantsMethod = QueryGrpc.getGranterGrantsMethod) == null) {
          QueryGrpc.getGranterGrantsMethod = getGranterGrantsMethod =
              io.grpc.MethodDescriptor.<com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsRequest, com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GranterGrants"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GranterGrants"))
              .build();
        }
      }
    }
    return getGranterGrantsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsRequest,
      com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsResponse> getGranteeGrantsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GranteeGrants",
      requestType = com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsRequest.class,
      responseType = com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsRequest,
      com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsResponse> getGranteeGrantsMethod() {
    io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsRequest, com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsResponse> getGranteeGrantsMethod;
    if ((getGranteeGrantsMethod = QueryGrpc.getGranteeGrantsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGranteeGrantsMethod = QueryGrpc.getGranteeGrantsMethod) == null) {
          QueryGrpc.getGranteeGrantsMethod = getGranteeGrantsMethod =
              io.grpc.MethodDescriptor.<com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsRequest, com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GranteeGrants"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GranteeGrants"))
              .build();
        }
      }
    }
    return getGranteeGrantsMethod;
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
     * Returns list of `Authorization`, granted to the grantee by the granter.
     * </pre>
     */
    default void grants(com.cosmos.authz.v1beta1.QueryProto.QueryGrantsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.QueryProto.QueryGrantsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGrantsMethod(), responseObserver);
    }

    /**
     * <pre>
     * GranterGrants returns list of `GrantAuthorization`, granted by granter.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    default void granterGrants(com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGranterGrantsMethod(), responseObserver);
    }

    /**
     * <pre>
     * GranteeGrants returns a list of `GrantAuthorization` by grantee.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    default void granteeGrants(com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGranteeGrantsMethod(), responseObserver);
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
     * Returns list of `Authorization`, granted to the grantee by the granter.
     * </pre>
     */
    public void grants(com.cosmos.authz.v1beta1.QueryProto.QueryGrantsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.QueryProto.QueryGrantsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGrantsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GranterGrants returns list of `GrantAuthorization`, granted by granter.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public void granterGrants(com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGranterGrantsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GranteeGrants returns a list of `GrantAuthorization` by grantee.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public void granteeGrants(com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGranteeGrantsMethod(), getCallOptions()), request, responseObserver);
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
     * Returns list of `Authorization`, granted to the grantee by the granter.
     * </pre>
     */
    public com.cosmos.authz.v1beta1.QueryProto.QueryGrantsResponse grants(com.cosmos.authz.v1beta1.QueryProto.QueryGrantsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGrantsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GranterGrants returns list of `GrantAuthorization`, granted by granter.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsResponse granterGrants(com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGranterGrantsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GranteeGrants returns a list of `GrantAuthorization` by grantee.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsResponse granteeGrants(com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGranteeGrantsMethod(), getCallOptions(), request);
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
     * Returns list of `Authorization`, granted to the grantee by the granter.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.authz.v1beta1.QueryProto.QueryGrantsResponse> grants(
        com.cosmos.authz.v1beta1.QueryProto.QueryGrantsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGrantsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GranterGrants returns list of `GrantAuthorization`, granted by granter.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsResponse> granterGrants(
        com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGranterGrantsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GranteeGrants returns a list of `GrantAuthorization` by grantee.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsResponse> granteeGrants(
        com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGranteeGrantsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GRANTS = 0;
  private static final int METHODID_GRANTER_GRANTS = 1;
  private static final int METHODID_GRANTEE_GRANTS = 2;

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
        case METHODID_GRANTS:
          serviceImpl.grants((com.cosmos.authz.v1beta1.QueryProto.QueryGrantsRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.QueryProto.QueryGrantsResponse>) responseObserver);
          break;
        case METHODID_GRANTER_GRANTS:
          serviceImpl.granterGrants((com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsResponse>) responseObserver);
          break;
        case METHODID_GRANTEE_GRANTS:
          serviceImpl.granteeGrants((com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsResponse>) responseObserver);
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
          getGrantsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.authz.v1beta1.QueryProto.QueryGrantsRequest,
              com.cosmos.authz.v1beta1.QueryProto.QueryGrantsResponse>(
                service, METHODID_GRANTS)))
        .addMethod(
          getGranterGrantsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsRequest,
              com.cosmos.authz.v1beta1.QueryProto.QueryGranterGrantsResponse>(
                service, METHODID_GRANTER_GRANTS)))
        .addMethod(
          getGranteeGrantsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsRequest,
              com.cosmos.authz.v1beta1.QueryProto.QueryGranteeGrantsResponse>(
                service, METHODID_GRANTEE_GRANTS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmos.authz.v1beta1.QueryProto.getDescriptor();
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
              .addMethod(getGrantsMethod())
              .addMethod(getGranterGrantsMethod())
              .addMethod(getGranteeGrantsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
