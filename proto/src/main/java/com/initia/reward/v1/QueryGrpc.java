package com.initia.reward.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query provides defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: initia/reward/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "initia.reward.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.initia.reward.v1.QueryProto.QueryParamsRequest,
      com.initia.reward.v1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.initia.reward.v1.QueryProto.QueryParamsRequest.class,
      responseType = com.initia.reward.v1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.reward.v1.QueryProto.QueryParamsRequest,
      com.initia.reward.v1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.initia.reward.v1.QueryProto.QueryParamsRequest, com.initia.reward.v1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.initia.reward.v1.QueryProto.QueryParamsRequest, com.initia.reward.v1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.reward.v1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.reward.v1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.reward.v1.QueryProto.QueryAnnualProvisionsRequest,
      com.initia.reward.v1.QueryProto.QueryAnnualProvisionsResponse> getAnnualProvisionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AnnualProvisions",
      requestType = com.initia.reward.v1.QueryProto.QueryAnnualProvisionsRequest.class,
      responseType = com.initia.reward.v1.QueryProto.QueryAnnualProvisionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.reward.v1.QueryProto.QueryAnnualProvisionsRequest,
      com.initia.reward.v1.QueryProto.QueryAnnualProvisionsResponse> getAnnualProvisionsMethod() {
    io.grpc.MethodDescriptor<com.initia.reward.v1.QueryProto.QueryAnnualProvisionsRequest, com.initia.reward.v1.QueryProto.QueryAnnualProvisionsResponse> getAnnualProvisionsMethod;
    if ((getAnnualProvisionsMethod = QueryGrpc.getAnnualProvisionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAnnualProvisionsMethod = QueryGrpc.getAnnualProvisionsMethod) == null) {
          QueryGrpc.getAnnualProvisionsMethod = getAnnualProvisionsMethod =
              io.grpc.MethodDescriptor.<com.initia.reward.v1.QueryProto.QueryAnnualProvisionsRequest, com.initia.reward.v1.QueryProto.QueryAnnualProvisionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AnnualProvisions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.reward.v1.QueryProto.QueryAnnualProvisionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.reward.v1.QueryProto.QueryAnnualProvisionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AnnualProvisions"))
              .build();
        }
      }
    }
    return getAnnualProvisionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampRequest,
      com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampResponse> getLastDilutionTimestampMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastDilutionTimestamp",
      requestType = com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampRequest.class,
      responseType = com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampRequest,
      com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampResponse> getLastDilutionTimestampMethod() {
    io.grpc.MethodDescriptor<com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampRequest, com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampResponse> getLastDilutionTimestampMethod;
    if ((getLastDilutionTimestampMethod = QueryGrpc.getLastDilutionTimestampMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastDilutionTimestampMethod = QueryGrpc.getLastDilutionTimestampMethod) == null) {
          QueryGrpc.getLastDilutionTimestampMethod = getLastDilutionTimestampMethod =
              io.grpc.MethodDescriptor.<com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampRequest, com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastDilutionTimestamp"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastDilutionTimestamp"))
              .build();
        }
      }
    }
    return getLastDilutionTimestampMethod;
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
     * Params returns the total set of minting parameters.
     * </pre>
     */
    default void params(com.initia.reward.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.initia.reward.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * AnnualProvisions returns current minting annual provisions value.
     * </pre>
     */
    default void annualProvisions(com.initia.reward.v1.QueryProto.QueryAnnualProvisionsRequest request,
        io.grpc.stub.StreamObserver<com.initia.reward.v1.QueryProto.QueryAnnualProvisionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAnnualProvisionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * LastDilutionTimestamp returns the time when the last release rate dilution occurred.
     * </pre>
     */
    default void lastDilutionTimestamp(com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampRequest request,
        io.grpc.stub.StreamObserver<com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastDilutionTimestampMethod(), responseObserver);
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
     * Params returns the total set of minting parameters.
     * </pre>
     */
    public void params(com.initia.reward.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.initia.reward.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AnnualProvisions returns current minting annual provisions value.
     * </pre>
     */
    public void annualProvisions(com.initia.reward.v1.QueryProto.QueryAnnualProvisionsRequest request,
        io.grpc.stub.StreamObserver<com.initia.reward.v1.QueryProto.QueryAnnualProvisionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAnnualProvisionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * LastDilutionTimestamp returns the time when the last release rate dilution occurred.
     * </pre>
     */
    public void lastDilutionTimestamp(com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampRequest request,
        io.grpc.stub.StreamObserver<com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastDilutionTimestampMethod(), getCallOptions()), request, responseObserver);
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
     * Params returns the total set of minting parameters.
     * </pre>
     */
    public com.initia.reward.v1.QueryProto.QueryParamsResponse params(com.initia.reward.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AnnualProvisions returns current minting annual provisions value.
     * </pre>
     */
    public com.initia.reward.v1.QueryProto.QueryAnnualProvisionsResponse annualProvisions(com.initia.reward.v1.QueryProto.QueryAnnualProvisionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAnnualProvisionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * LastDilutionTimestamp returns the time when the last release rate dilution occurred.
     * </pre>
     */
    public com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampResponse lastDilutionTimestamp(com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastDilutionTimestampMethod(), getCallOptions(), request);
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
     * Params returns the total set of minting parameters.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.reward.v1.QueryProto.QueryParamsResponse> params(
        com.initia.reward.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AnnualProvisions returns current minting annual provisions value.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.reward.v1.QueryProto.QueryAnnualProvisionsResponse> annualProvisions(
        com.initia.reward.v1.QueryProto.QueryAnnualProvisionsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAnnualProvisionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * LastDilutionTimestamp returns the time when the last release rate dilution occurred.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampResponse> lastDilutionTimestamp(
        com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastDilutionTimestampMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_ANNUAL_PROVISIONS = 1;
  private static final int METHODID_LAST_DILUTION_TIMESTAMP = 2;

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
        case METHODID_PARAMS:
          serviceImpl.params((com.initia.reward.v1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.reward.v1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_ANNUAL_PROVISIONS:
          serviceImpl.annualProvisions((com.initia.reward.v1.QueryProto.QueryAnnualProvisionsRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.reward.v1.QueryProto.QueryAnnualProvisionsResponse>) responseObserver);
          break;
        case METHODID_LAST_DILUTION_TIMESTAMP:
          serviceImpl.lastDilutionTimestamp((com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampResponse>) responseObserver);
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
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.reward.v1.QueryProto.QueryParamsRequest,
              com.initia.reward.v1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getAnnualProvisionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.reward.v1.QueryProto.QueryAnnualProvisionsRequest,
              com.initia.reward.v1.QueryProto.QueryAnnualProvisionsResponse>(
                service, METHODID_ANNUAL_PROVISIONS)))
        .addMethod(
          getLastDilutionTimestampMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampRequest,
              com.initia.reward.v1.QueryProto.QueryLastDilutionTimestampResponse>(
                service, METHODID_LAST_DILUTION_TIMESTAMP)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.initia.reward.v1.QueryProto.getDescriptor();
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
              .addMethod(getParamsMethod())
              .addMethod(getAnnualProvisionsMethod())
              .addMethod(getLastDilutionTimestampMethod())
              .build();
        }
      }
    }
    return result;
  }
}
