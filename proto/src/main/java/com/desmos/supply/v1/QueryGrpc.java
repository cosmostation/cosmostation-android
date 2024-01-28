package com.desmos.supply.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: desmos/supply/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "desmos.supply.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.desmos.supply.v1.QueryProto.QueryTotalRequest,
      com.desmos.supply.v1.QueryProto.QueryTotalResponse> getTotalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Total",
      requestType = com.desmos.supply.v1.QueryProto.QueryTotalRequest.class,
      responseType = com.desmos.supply.v1.QueryProto.QueryTotalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.supply.v1.QueryProto.QueryTotalRequest,
      com.desmos.supply.v1.QueryProto.QueryTotalResponse> getTotalMethod() {
    io.grpc.MethodDescriptor<com.desmos.supply.v1.QueryProto.QueryTotalRequest, com.desmos.supply.v1.QueryProto.QueryTotalResponse> getTotalMethod;
    if ((getTotalMethod = QueryGrpc.getTotalMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalMethod = QueryGrpc.getTotalMethod) == null) {
          QueryGrpc.getTotalMethod = getTotalMethod =
              io.grpc.MethodDescriptor.<com.desmos.supply.v1.QueryProto.QueryTotalRequest, com.desmos.supply.v1.QueryProto.QueryTotalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Total"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.supply.v1.QueryProto.QueryTotalRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.supply.v1.QueryProto.QueryTotalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Total"))
              .build();
        }
      }
    }
    return getTotalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.supply.v1.QueryProto.QueryCirculatingRequest,
      com.desmos.supply.v1.QueryProto.QueryCirculatingResponse> getCirculatingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Circulating",
      requestType = com.desmos.supply.v1.QueryProto.QueryCirculatingRequest.class,
      responseType = com.desmos.supply.v1.QueryProto.QueryCirculatingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.supply.v1.QueryProto.QueryCirculatingRequest,
      com.desmos.supply.v1.QueryProto.QueryCirculatingResponse> getCirculatingMethod() {
    io.grpc.MethodDescriptor<com.desmos.supply.v1.QueryProto.QueryCirculatingRequest, com.desmos.supply.v1.QueryProto.QueryCirculatingResponse> getCirculatingMethod;
    if ((getCirculatingMethod = QueryGrpc.getCirculatingMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCirculatingMethod = QueryGrpc.getCirculatingMethod) == null) {
          QueryGrpc.getCirculatingMethod = getCirculatingMethod =
              io.grpc.MethodDescriptor.<com.desmos.supply.v1.QueryProto.QueryCirculatingRequest, com.desmos.supply.v1.QueryProto.QueryCirculatingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Circulating"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.supply.v1.QueryProto.QueryCirculatingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.supply.v1.QueryProto.QueryCirculatingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Circulating"))
              .build();
        }
      }
    }
    return getCirculatingMethod;
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
     * Total queries the total supply of the given denom
     * </pre>
     */
    default void total(com.desmos.supply.v1.QueryProto.QueryTotalRequest request,
        io.grpc.stub.StreamObserver<com.desmos.supply.v1.QueryProto.QueryTotalResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTotalMethod(), responseObserver);
    }

    /**
     * <pre>
     * Circulating queries the amount of tokens circulating in the market of the
     * given denom
     * </pre>
     */
    default void circulating(com.desmos.supply.v1.QueryProto.QueryCirculatingRequest request,
        io.grpc.stub.StreamObserver<com.desmos.supply.v1.QueryProto.QueryCirculatingResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCirculatingMethod(), responseObserver);
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
     * Total queries the total supply of the given denom
     * </pre>
     */
    public void total(com.desmos.supply.v1.QueryProto.QueryTotalRequest request,
        io.grpc.stub.StreamObserver<com.desmos.supply.v1.QueryProto.QueryTotalResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTotalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Circulating queries the amount of tokens circulating in the market of the
     * given denom
     * </pre>
     */
    public void circulating(com.desmos.supply.v1.QueryProto.QueryCirculatingRequest request,
        io.grpc.stub.StreamObserver<com.desmos.supply.v1.QueryProto.QueryCirculatingResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCirculatingMethod(), getCallOptions()), request, responseObserver);
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
     * Total queries the total supply of the given denom
     * </pre>
     */
    public com.desmos.supply.v1.QueryProto.QueryTotalResponse total(com.desmos.supply.v1.QueryProto.QueryTotalRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTotalMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Circulating queries the amount of tokens circulating in the market of the
     * given denom
     * </pre>
     */
    public com.desmos.supply.v1.QueryProto.QueryCirculatingResponse circulating(com.desmos.supply.v1.QueryProto.QueryCirculatingRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCirculatingMethod(), getCallOptions(), request);
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
     * Total queries the total supply of the given denom
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.supply.v1.QueryProto.QueryTotalResponse> total(
        com.desmos.supply.v1.QueryProto.QueryTotalRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTotalMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Circulating queries the amount of tokens circulating in the market of the
     * given denom
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.supply.v1.QueryProto.QueryCirculatingResponse> circulating(
        com.desmos.supply.v1.QueryProto.QueryCirculatingRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCirculatingMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TOTAL = 0;
  private static final int METHODID_CIRCULATING = 1;

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
        case METHODID_TOTAL:
          serviceImpl.total((com.desmos.supply.v1.QueryProto.QueryTotalRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.supply.v1.QueryProto.QueryTotalResponse>) responseObserver);
          break;
        case METHODID_CIRCULATING:
          serviceImpl.circulating((com.desmos.supply.v1.QueryProto.QueryCirculatingRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.supply.v1.QueryProto.QueryCirculatingResponse>) responseObserver);
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
          getTotalMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.supply.v1.QueryProto.QueryTotalRequest,
              com.desmos.supply.v1.QueryProto.QueryTotalResponse>(
                service, METHODID_TOTAL)))
        .addMethod(
          getCirculatingMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.supply.v1.QueryProto.QueryCirculatingRequest,
              com.desmos.supply.v1.QueryProto.QueryCirculatingResponse>(
                service, METHODID_CIRCULATING)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.desmos.supply.v1.QueryProto.getDescriptor();
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
              .addMethod(getTotalMethod())
              .addMethod(getCirculatingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
