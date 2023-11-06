package com.cosmos.orm.query.v1alpha1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query is a generic gRPC service for querying ORM data.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmos/orm/query/v1alpha1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "cosmos.orm.query.v1alpha1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmos.orm.query.v1alpha1.QueryProto.GetRequest,
      com.cosmos.orm.query.v1alpha1.QueryProto.GetResponse> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Get",
      requestType = com.cosmos.orm.query.v1alpha1.QueryProto.GetRequest.class,
      responseType = com.cosmos.orm.query.v1alpha1.QueryProto.GetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.orm.query.v1alpha1.QueryProto.GetRequest,
      com.cosmos.orm.query.v1alpha1.QueryProto.GetResponse> getGetMethod() {
    io.grpc.MethodDescriptor<com.cosmos.orm.query.v1alpha1.QueryProto.GetRequest, com.cosmos.orm.query.v1alpha1.QueryProto.GetResponse> getGetMethod;
    if ((getGetMethod = QueryGrpc.getGetMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetMethod = QueryGrpc.getGetMethod) == null) {
          QueryGrpc.getGetMethod = getGetMethod =
              io.grpc.MethodDescriptor.<com.cosmos.orm.query.v1alpha1.QueryProto.GetRequest, com.cosmos.orm.query.v1alpha1.QueryProto.GetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.orm.query.v1alpha1.QueryProto.GetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.orm.query.v1alpha1.QueryProto.GetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Get"))
              .build();
        }
      }
    }
    return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.orm.query.v1alpha1.QueryProto.ListRequest,
      com.cosmos.orm.query.v1alpha1.QueryProto.ListResponse> getListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "List",
      requestType = com.cosmos.orm.query.v1alpha1.QueryProto.ListRequest.class,
      responseType = com.cosmos.orm.query.v1alpha1.QueryProto.ListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.orm.query.v1alpha1.QueryProto.ListRequest,
      com.cosmos.orm.query.v1alpha1.QueryProto.ListResponse> getListMethod() {
    io.grpc.MethodDescriptor<com.cosmos.orm.query.v1alpha1.QueryProto.ListRequest, com.cosmos.orm.query.v1alpha1.QueryProto.ListResponse> getListMethod;
    if ((getListMethod = QueryGrpc.getListMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getListMethod = QueryGrpc.getListMethod) == null) {
          QueryGrpc.getListMethod = getListMethod =
              io.grpc.MethodDescriptor.<com.cosmos.orm.query.v1alpha1.QueryProto.ListRequest, com.cosmos.orm.query.v1alpha1.QueryProto.ListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "List"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.orm.query.v1alpha1.QueryProto.ListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.orm.query.v1alpha1.QueryProto.ListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("List"))
              .build();
        }
      }
    }
    return getListMethod;
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
   * Query is a generic gRPC service for querying ORM data.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Get queries an ORM table against an unique index.
     * </pre>
     */
    default void get(com.cosmos.orm.query.v1alpha1.QueryProto.GetRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.orm.query.v1alpha1.QueryProto.GetResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /**
     * <pre>
     * List queries an ORM table against an index.
     * </pre>
     */
    default void list(com.cosmos.orm.query.v1alpha1.QueryProto.ListRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.orm.query.v1alpha1.QueryProto.ListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query is a generic gRPC service for querying ORM data.
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
   * Query is a generic gRPC service for querying ORM data.
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
     * Get queries an ORM table against an unique index.
     * </pre>
     */
    public void get(com.cosmos.orm.query.v1alpha1.QueryProto.GetRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.orm.query.v1alpha1.QueryProto.GetResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * List queries an ORM table against an index.
     * </pre>
     */
    public void list(com.cosmos.orm.query.v1alpha1.QueryProto.ListRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.orm.query.v1alpha1.QueryProto.ListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query is a generic gRPC service for querying ORM data.
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
     * Get queries an ORM table against an unique index.
     * </pre>
     */
    public com.cosmos.orm.query.v1alpha1.QueryProto.GetResponse get(com.cosmos.orm.query.v1alpha1.QueryProto.GetRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * List queries an ORM table against an index.
     * </pre>
     */
    public com.cosmos.orm.query.v1alpha1.QueryProto.ListResponse list(com.cosmos.orm.query.v1alpha1.QueryProto.ListRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query is a generic gRPC service for querying ORM data.
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
     * Get queries an ORM table against an unique index.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.orm.query.v1alpha1.QueryProto.GetResponse> get(
        com.cosmos.orm.query.v1alpha1.QueryProto.GetRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * List queries an ORM table against an index.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.orm.query.v1alpha1.QueryProto.ListResponse> list(
        com.cosmos.orm.query.v1alpha1.QueryProto.ListRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET = 0;
  private static final int METHODID_LIST = 1;

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
        case METHODID_GET:
          serviceImpl.get((com.cosmos.orm.query.v1alpha1.QueryProto.GetRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.orm.query.v1alpha1.QueryProto.GetResponse>) responseObserver);
          break;
        case METHODID_LIST:
          serviceImpl.list((com.cosmos.orm.query.v1alpha1.QueryProto.ListRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.orm.query.v1alpha1.QueryProto.ListResponse>) responseObserver);
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
          getGetMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.orm.query.v1alpha1.QueryProto.GetRequest,
              com.cosmos.orm.query.v1alpha1.QueryProto.GetResponse>(
                service, METHODID_GET)))
        .addMethod(
          getListMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.orm.query.v1alpha1.QueryProto.ListRequest,
              com.cosmos.orm.query.v1alpha1.QueryProto.ListResponse>(
                service, METHODID_LIST)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmos.orm.query.v1alpha1.QueryProto.getDescriptor();
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
              .addMethod(getGetMethod())
              .addMethod(getListMethod())
              .build();
        }
      }
    }
    return result;
  }
}
