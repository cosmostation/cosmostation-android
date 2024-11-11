package com.initia.ibchooks.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query provides defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: initia/ibchooks/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "initia.ibchooks.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.initia.ibchooks.v1.QueryProto.QueryACLRequest,
      com.initia.ibchooks.v1.QueryProto.QueryACLResponse> getACLMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ACL",
      requestType = com.initia.ibchooks.v1.QueryProto.QueryACLRequest.class,
      responseType = com.initia.ibchooks.v1.QueryProto.QueryACLResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.ibchooks.v1.QueryProto.QueryACLRequest,
      com.initia.ibchooks.v1.QueryProto.QueryACLResponse> getACLMethod() {
    io.grpc.MethodDescriptor<com.initia.ibchooks.v1.QueryProto.QueryACLRequest, com.initia.ibchooks.v1.QueryProto.QueryACLResponse> getACLMethod;
    if ((getACLMethod = QueryGrpc.getACLMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getACLMethod = QueryGrpc.getACLMethod) == null) {
          QueryGrpc.getACLMethod = getACLMethod =
              io.grpc.MethodDescriptor.<com.initia.ibchooks.v1.QueryProto.QueryACLRequest, com.initia.ibchooks.v1.QueryProto.QueryACLResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ACL"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.ibchooks.v1.QueryProto.QueryACLRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.ibchooks.v1.QueryProto.QueryACLResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ACL"))
              .build();
        }
      }
    }
    return getACLMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.ibchooks.v1.QueryProto.QueryACLsRequest,
      com.initia.ibchooks.v1.QueryProto.QueryACLsResponse> getACLsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ACLs",
      requestType = com.initia.ibchooks.v1.QueryProto.QueryACLsRequest.class,
      responseType = com.initia.ibchooks.v1.QueryProto.QueryACLsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.ibchooks.v1.QueryProto.QueryACLsRequest,
      com.initia.ibchooks.v1.QueryProto.QueryACLsResponse> getACLsMethod() {
    io.grpc.MethodDescriptor<com.initia.ibchooks.v1.QueryProto.QueryACLsRequest, com.initia.ibchooks.v1.QueryProto.QueryACLsResponse> getACLsMethod;
    if ((getACLsMethod = QueryGrpc.getACLsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getACLsMethod = QueryGrpc.getACLsMethod) == null) {
          QueryGrpc.getACLsMethod = getACLsMethod =
              io.grpc.MethodDescriptor.<com.initia.ibchooks.v1.QueryProto.QueryACLsRequest, com.initia.ibchooks.v1.QueryProto.QueryACLsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ACLs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.ibchooks.v1.QueryProto.QueryACLsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.ibchooks.v1.QueryProto.QueryACLsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ACLs"))
              .build();
        }
      }
    }
    return getACLsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.ibchooks.v1.QueryProto.QueryParamsRequest,
      com.initia.ibchooks.v1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.initia.ibchooks.v1.QueryProto.QueryParamsRequest.class,
      responseType = com.initia.ibchooks.v1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.ibchooks.v1.QueryProto.QueryParamsRequest,
      com.initia.ibchooks.v1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.initia.ibchooks.v1.QueryProto.QueryParamsRequest, com.initia.ibchooks.v1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.initia.ibchooks.v1.QueryProto.QueryParamsRequest, com.initia.ibchooks.v1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.ibchooks.v1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.ibchooks.v1.QueryProto.QueryParamsResponse.getDefaultInstance()))
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
   * Query provides defines the gRPC querier service
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * ACL gets ACL entry of an address.
     * </pre>
     */
    default void aCL(com.initia.ibchooks.v1.QueryProto.QueryACLRequest request,
        io.grpc.stub.StreamObserver<com.initia.ibchooks.v1.QueryProto.QueryACLResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getACLMethod(), responseObserver);
    }

    /**
     * <pre>
     * ACLs gets ACL entries.
     * </pre>
     */
    default void aCLs(com.initia.ibchooks.v1.QueryProto.QueryACLsRequest request,
        io.grpc.stub.StreamObserver<com.initia.ibchooks.v1.QueryProto.QueryACLsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getACLsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries all parameters.
     * </pre>
     */
    default void params(com.initia.ibchooks.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.initia.ibchooks.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
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
     * ACL gets ACL entry of an address.
     * </pre>
     */
    public void aCL(com.initia.ibchooks.v1.QueryProto.QueryACLRequest request,
        io.grpc.stub.StreamObserver<com.initia.ibchooks.v1.QueryProto.QueryACLResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getACLMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ACLs gets ACL entries.
     * </pre>
     */
    public void aCLs(com.initia.ibchooks.v1.QueryProto.QueryACLsRequest request,
        io.grpc.stub.StreamObserver<com.initia.ibchooks.v1.QueryProto.QueryACLsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getACLsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries all parameters.
     * </pre>
     */
    public void params(com.initia.ibchooks.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.initia.ibchooks.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
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
     * ACL gets ACL entry of an address.
     * </pre>
     */
    public com.initia.ibchooks.v1.QueryProto.QueryACLResponse aCL(com.initia.ibchooks.v1.QueryProto.QueryACLRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getACLMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ACLs gets ACL entries.
     * </pre>
     */
    public com.initia.ibchooks.v1.QueryProto.QueryACLsResponse aCLs(com.initia.ibchooks.v1.QueryProto.QueryACLsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getACLsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries all parameters.
     * </pre>
     */
    public com.initia.ibchooks.v1.QueryProto.QueryParamsResponse params(com.initia.ibchooks.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
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
     * ACL gets ACL entry of an address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.ibchooks.v1.QueryProto.QueryACLResponse> aCL(
        com.initia.ibchooks.v1.QueryProto.QueryACLRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getACLMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ACLs gets ACL entries.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.ibchooks.v1.QueryProto.QueryACLsResponse> aCLs(
        com.initia.ibchooks.v1.QueryProto.QueryACLsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getACLsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries all parameters.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.ibchooks.v1.QueryProto.QueryParamsResponse> params(
        com.initia.ibchooks.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ACL = 0;
  private static final int METHODID_ACLS = 1;
  private static final int METHODID_PARAMS = 2;

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
        case METHODID_ACL:
          serviceImpl.aCL((com.initia.ibchooks.v1.QueryProto.QueryACLRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.ibchooks.v1.QueryProto.QueryACLResponse>) responseObserver);
          break;
        case METHODID_ACLS:
          serviceImpl.aCLs((com.initia.ibchooks.v1.QueryProto.QueryACLsRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.ibchooks.v1.QueryProto.QueryACLsResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((com.initia.ibchooks.v1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.ibchooks.v1.QueryProto.QueryParamsResponse>) responseObserver);
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
          getACLMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.ibchooks.v1.QueryProto.QueryACLRequest,
              com.initia.ibchooks.v1.QueryProto.QueryACLResponse>(
                service, METHODID_ACL)))
        .addMethod(
          getACLsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.ibchooks.v1.QueryProto.QueryACLsRequest,
              com.initia.ibchooks.v1.QueryProto.QueryACLsResponse>(
                service, METHODID_ACLS)))
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.ibchooks.v1.QueryProto.QueryParamsRequest,
              com.initia.ibchooks.v1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.initia.ibchooks.v1.QueryProto.getDescriptor();
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
              .addMethod(getACLMethod())
              .addMethod(getACLsMethod())
              .addMethod(getParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
