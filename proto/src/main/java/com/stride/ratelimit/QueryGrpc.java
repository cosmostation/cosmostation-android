package com.stride.ratelimit;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: stride/ratelimit/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "stride.ratelimit.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.stride.ratelimit.QueryProto.QueryAllRateLimitsRequest,
      com.stride.ratelimit.QueryProto.QueryAllRateLimitsResponse> getAllRateLimitsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllRateLimits",
      requestType = com.stride.ratelimit.QueryProto.QueryAllRateLimitsRequest.class,
      responseType = com.stride.ratelimit.QueryProto.QueryAllRateLimitsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.ratelimit.QueryProto.QueryAllRateLimitsRequest,
      com.stride.ratelimit.QueryProto.QueryAllRateLimitsResponse> getAllRateLimitsMethod() {
    io.grpc.MethodDescriptor<com.stride.ratelimit.QueryProto.QueryAllRateLimitsRequest, com.stride.ratelimit.QueryProto.QueryAllRateLimitsResponse> getAllRateLimitsMethod;
    if ((getAllRateLimitsMethod = QueryGrpc.getAllRateLimitsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllRateLimitsMethod = QueryGrpc.getAllRateLimitsMethod) == null) {
          QueryGrpc.getAllRateLimitsMethod = getAllRateLimitsMethod =
              io.grpc.MethodDescriptor.<com.stride.ratelimit.QueryProto.QueryAllRateLimitsRequest, com.stride.ratelimit.QueryProto.QueryAllRateLimitsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllRateLimits"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.ratelimit.QueryProto.QueryAllRateLimitsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.ratelimit.QueryProto.QueryAllRateLimitsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllRateLimits"))
              .build();
        }
      }
    }
    return getAllRateLimitsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.ratelimit.QueryProto.QueryRateLimitRequest,
      com.stride.ratelimit.QueryProto.QueryRateLimitResponse> getRateLimitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RateLimit",
      requestType = com.stride.ratelimit.QueryProto.QueryRateLimitRequest.class,
      responseType = com.stride.ratelimit.QueryProto.QueryRateLimitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.ratelimit.QueryProto.QueryRateLimitRequest,
      com.stride.ratelimit.QueryProto.QueryRateLimitResponse> getRateLimitMethod() {
    io.grpc.MethodDescriptor<com.stride.ratelimit.QueryProto.QueryRateLimitRequest, com.stride.ratelimit.QueryProto.QueryRateLimitResponse> getRateLimitMethod;
    if ((getRateLimitMethod = QueryGrpc.getRateLimitMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRateLimitMethod = QueryGrpc.getRateLimitMethod) == null) {
          QueryGrpc.getRateLimitMethod = getRateLimitMethod =
              io.grpc.MethodDescriptor.<com.stride.ratelimit.QueryProto.QueryRateLimitRequest, com.stride.ratelimit.QueryProto.QueryRateLimitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RateLimit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.ratelimit.QueryProto.QueryRateLimitRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.ratelimit.QueryProto.QueryRateLimitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RateLimit"))
              .build();
        }
      }
    }
    return getRateLimitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdRequest,
      com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdResponse> getRateLimitsByChainIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RateLimitsByChainId",
      requestType = com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdRequest.class,
      responseType = com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdRequest,
      com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdResponse> getRateLimitsByChainIdMethod() {
    io.grpc.MethodDescriptor<com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdRequest, com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdResponse> getRateLimitsByChainIdMethod;
    if ((getRateLimitsByChainIdMethod = QueryGrpc.getRateLimitsByChainIdMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRateLimitsByChainIdMethod = QueryGrpc.getRateLimitsByChainIdMethod) == null) {
          QueryGrpc.getRateLimitsByChainIdMethod = getRateLimitsByChainIdMethod =
              io.grpc.MethodDescriptor.<com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdRequest, com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RateLimitsByChainId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RateLimitsByChainId"))
              .build();
        }
      }
    }
    return getRateLimitsByChainIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdRequest,
      com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdResponse> getRateLimitsByChannelIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RateLimitsByChannelId",
      requestType = com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdRequest.class,
      responseType = com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdRequest,
      com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdResponse> getRateLimitsByChannelIdMethod() {
    io.grpc.MethodDescriptor<com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdRequest, com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdResponse> getRateLimitsByChannelIdMethod;
    if ((getRateLimitsByChannelIdMethod = QueryGrpc.getRateLimitsByChannelIdMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRateLimitsByChannelIdMethod = QueryGrpc.getRateLimitsByChannelIdMethod) == null) {
          QueryGrpc.getRateLimitsByChannelIdMethod = getRateLimitsByChannelIdMethod =
              io.grpc.MethodDescriptor.<com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdRequest, com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RateLimitsByChannelId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RateLimitsByChannelId"))
              .build();
        }
      }
    }
    return getRateLimitsByChannelIdMethod;
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
     */
    default void allRateLimits(com.stride.ratelimit.QueryProto.QueryAllRateLimitsRequest request,
        io.grpc.stub.StreamObserver<com.stride.ratelimit.QueryProto.QueryAllRateLimitsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAllRateLimitsMethod(), responseObserver);
    }

    /**
     */
    default void rateLimit(com.stride.ratelimit.QueryProto.QueryRateLimitRequest request,
        io.grpc.stub.StreamObserver<com.stride.ratelimit.QueryProto.QueryRateLimitResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRateLimitMethod(), responseObserver);
    }

    /**
     */
    default void rateLimitsByChainId(com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdRequest request,
        io.grpc.stub.StreamObserver<com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRateLimitsByChainIdMethod(), responseObserver);
    }

    /**
     */
    default void rateLimitsByChannelId(com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdRequest request,
        io.grpc.stub.StreamObserver<com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRateLimitsByChannelIdMethod(), responseObserver);
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
     */
    public void allRateLimits(com.stride.ratelimit.QueryProto.QueryAllRateLimitsRequest request,
        io.grpc.stub.StreamObserver<com.stride.ratelimit.QueryProto.QueryAllRateLimitsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAllRateLimitsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rateLimit(com.stride.ratelimit.QueryProto.QueryRateLimitRequest request,
        io.grpc.stub.StreamObserver<com.stride.ratelimit.QueryProto.QueryRateLimitResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRateLimitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rateLimitsByChainId(com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdRequest request,
        io.grpc.stub.StreamObserver<com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRateLimitsByChainIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rateLimitsByChannelId(com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdRequest request,
        io.grpc.stub.StreamObserver<com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRateLimitsByChannelIdMethod(), getCallOptions()), request, responseObserver);
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
     */
    public com.stride.ratelimit.QueryProto.QueryAllRateLimitsResponse allRateLimits(com.stride.ratelimit.QueryProto.QueryAllRateLimitsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAllRateLimitsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.stride.ratelimit.QueryProto.QueryRateLimitResponse rateLimit(com.stride.ratelimit.QueryProto.QueryRateLimitRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRateLimitMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdResponse rateLimitsByChainId(com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRateLimitsByChainIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdResponse rateLimitsByChannelId(com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRateLimitsByChannelIdMethod(), getCallOptions(), request);
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
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.ratelimit.QueryProto.QueryAllRateLimitsResponse> allRateLimits(
        com.stride.ratelimit.QueryProto.QueryAllRateLimitsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAllRateLimitsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.ratelimit.QueryProto.QueryRateLimitResponse> rateLimit(
        com.stride.ratelimit.QueryProto.QueryRateLimitRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRateLimitMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdResponse> rateLimitsByChainId(
        com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRateLimitsByChainIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdResponse> rateLimitsByChannelId(
        com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRateLimitsByChannelIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ALL_RATE_LIMITS = 0;
  private static final int METHODID_RATE_LIMIT = 1;
  private static final int METHODID_RATE_LIMITS_BY_CHAIN_ID = 2;
  private static final int METHODID_RATE_LIMITS_BY_CHANNEL_ID = 3;

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
        case METHODID_ALL_RATE_LIMITS:
          serviceImpl.allRateLimits((com.stride.ratelimit.QueryProto.QueryAllRateLimitsRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.ratelimit.QueryProto.QueryAllRateLimitsResponse>) responseObserver);
          break;
        case METHODID_RATE_LIMIT:
          serviceImpl.rateLimit((com.stride.ratelimit.QueryProto.QueryRateLimitRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.ratelimit.QueryProto.QueryRateLimitResponse>) responseObserver);
          break;
        case METHODID_RATE_LIMITS_BY_CHAIN_ID:
          serviceImpl.rateLimitsByChainId((com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdResponse>) responseObserver);
          break;
        case METHODID_RATE_LIMITS_BY_CHANNEL_ID:
          serviceImpl.rateLimitsByChannelId((com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdResponse>) responseObserver);
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
          getAllRateLimitsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.ratelimit.QueryProto.QueryAllRateLimitsRequest,
              com.stride.ratelimit.QueryProto.QueryAllRateLimitsResponse>(
                service, METHODID_ALL_RATE_LIMITS)))
        .addMethod(
          getRateLimitMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.ratelimit.QueryProto.QueryRateLimitRequest,
              com.stride.ratelimit.QueryProto.QueryRateLimitResponse>(
                service, METHODID_RATE_LIMIT)))
        .addMethod(
          getRateLimitsByChainIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdRequest,
              com.stride.ratelimit.QueryProto.QueryRateLimitsByChainIdResponse>(
                service, METHODID_RATE_LIMITS_BY_CHAIN_ID)))
        .addMethod(
          getRateLimitsByChannelIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdRequest,
              com.stride.ratelimit.QueryProto.QueryRateLimitsByChannelIdResponse>(
                service, METHODID_RATE_LIMITS_BY_CHANNEL_ID)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.stride.ratelimit.QueryProto.getDescriptor();
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
              .addMethod(getAllRateLimitsMethod())
              .addMethod(getRateLimitMethod())
              .addMethod(getRateLimitsByChainIdMethod())
              .addMethod(getRateLimitsByChannelIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}
