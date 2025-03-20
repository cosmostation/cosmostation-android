package com.babylon.mint.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: babylon/mint/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "babylon.mint.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.babylon.mint.v1.QueryProto.QueryInflationRateRequest,
      com.babylon.mint.v1.QueryProto.QueryInflationRateResponse> getInflationRateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InflationRate",
      requestType = com.babylon.mint.v1.QueryProto.QueryInflationRateRequest.class,
      responseType = com.babylon.mint.v1.QueryProto.QueryInflationRateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.mint.v1.QueryProto.QueryInflationRateRequest,
      com.babylon.mint.v1.QueryProto.QueryInflationRateResponse> getInflationRateMethod() {
    io.grpc.MethodDescriptor<com.babylon.mint.v1.QueryProto.QueryInflationRateRequest, com.babylon.mint.v1.QueryProto.QueryInflationRateResponse> getInflationRateMethod;
    if ((getInflationRateMethod = QueryGrpc.getInflationRateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getInflationRateMethod = QueryGrpc.getInflationRateMethod) == null) {
          QueryGrpc.getInflationRateMethod = getInflationRateMethod =
              io.grpc.MethodDescriptor.<com.babylon.mint.v1.QueryProto.QueryInflationRateRequest, com.babylon.mint.v1.QueryProto.QueryInflationRateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InflationRate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.mint.v1.QueryProto.QueryInflationRateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.mint.v1.QueryProto.QueryInflationRateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("InflationRate"))
              .build();
        }
      }
    }
    return getInflationRateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsRequest,
      com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsResponse> getAnnualProvisionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AnnualProvisions",
      requestType = com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsRequest.class,
      responseType = com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsRequest,
      com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsResponse> getAnnualProvisionsMethod() {
    io.grpc.MethodDescriptor<com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsRequest, com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsResponse> getAnnualProvisionsMethod;
    if ((getAnnualProvisionsMethod = QueryGrpc.getAnnualProvisionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAnnualProvisionsMethod = QueryGrpc.getAnnualProvisionsMethod) == null) {
          QueryGrpc.getAnnualProvisionsMethod = getAnnualProvisionsMethod =
              io.grpc.MethodDescriptor.<com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsRequest, com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AnnualProvisions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AnnualProvisions"))
              .build();
        }
      }
    }
    return getAnnualProvisionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.mint.v1.QueryProto.QueryGenesisTimeRequest,
      com.babylon.mint.v1.QueryProto.QueryGenesisTimeResponse> getGenesisTimeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GenesisTime",
      requestType = com.babylon.mint.v1.QueryProto.QueryGenesisTimeRequest.class,
      responseType = com.babylon.mint.v1.QueryProto.QueryGenesisTimeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.mint.v1.QueryProto.QueryGenesisTimeRequest,
      com.babylon.mint.v1.QueryProto.QueryGenesisTimeResponse> getGenesisTimeMethod() {
    io.grpc.MethodDescriptor<com.babylon.mint.v1.QueryProto.QueryGenesisTimeRequest, com.babylon.mint.v1.QueryProto.QueryGenesisTimeResponse> getGenesisTimeMethod;
    if ((getGenesisTimeMethod = QueryGrpc.getGenesisTimeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGenesisTimeMethod = QueryGrpc.getGenesisTimeMethod) == null) {
          QueryGrpc.getGenesisTimeMethod = getGenesisTimeMethod =
              io.grpc.MethodDescriptor.<com.babylon.mint.v1.QueryProto.QueryGenesisTimeRequest, com.babylon.mint.v1.QueryProto.QueryGenesisTimeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GenesisTime"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.mint.v1.QueryProto.QueryGenesisTimeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.mint.v1.QueryProto.QueryGenesisTimeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GenesisTime"))
              .build();
        }
      }
    }
    return getGenesisTimeMethod;
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
     * InflationRate returns the current inflation rate.
     * </pre>
     */
    default void inflationRate(com.babylon.mint.v1.QueryProto.QueryInflationRateRequest request,
        io.grpc.stub.StreamObserver<com.babylon.mint.v1.QueryProto.QueryInflationRateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInflationRateMethod(), responseObserver);
    }

    /**
     * <pre>
     * AnnualProvisions returns the current annual provisions.
     * </pre>
     */
    default void annualProvisions(com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAnnualProvisionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * GenesisTime returns the genesis time.
     * </pre>
     */
    default void genesisTime(com.babylon.mint.v1.QueryProto.QueryGenesisTimeRequest request,
        io.grpc.stub.StreamObserver<com.babylon.mint.v1.QueryProto.QueryGenesisTimeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGenesisTimeMethod(), responseObserver);
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
     * InflationRate returns the current inflation rate.
     * </pre>
     */
    public void inflationRate(com.babylon.mint.v1.QueryProto.QueryInflationRateRequest request,
        io.grpc.stub.StreamObserver<com.babylon.mint.v1.QueryProto.QueryInflationRateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInflationRateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AnnualProvisions returns the current annual provisions.
     * </pre>
     */
    public void annualProvisions(com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAnnualProvisionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GenesisTime returns the genesis time.
     * </pre>
     */
    public void genesisTime(com.babylon.mint.v1.QueryProto.QueryGenesisTimeRequest request,
        io.grpc.stub.StreamObserver<com.babylon.mint.v1.QueryProto.QueryGenesisTimeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGenesisTimeMethod(), getCallOptions()), request, responseObserver);
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
     * InflationRate returns the current inflation rate.
     * </pre>
     */
    public com.babylon.mint.v1.QueryProto.QueryInflationRateResponse inflationRate(com.babylon.mint.v1.QueryProto.QueryInflationRateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInflationRateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AnnualProvisions returns the current annual provisions.
     * </pre>
     */
    public com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsResponse annualProvisions(com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAnnualProvisionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GenesisTime returns the genesis time.
     * </pre>
     */
    public com.babylon.mint.v1.QueryProto.QueryGenesisTimeResponse genesisTime(com.babylon.mint.v1.QueryProto.QueryGenesisTimeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGenesisTimeMethod(), getCallOptions(), request);
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
     * InflationRate returns the current inflation rate.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.mint.v1.QueryProto.QueryInflationRateResponse> inflationRate(
        com.babylon.mint.v1.QueryProto.QueryInflationRateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInflationRateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AnnualProvisions returns the current annual provisions.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsResponse> annualProvisions(
        com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAnnualProvisionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GenesisTime returns the genesis time.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.mint.v1.QueryProto.QueryGenesisTimeResponse> genesisTime(
        com.babylon.mint.v1.QueryProto.QueryGenesisTimeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGenesisTimeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INFLATION_RATE = 0;
  private static final int METHODID_ANNUAL_PROVISIONS = 1;
  private static final int METHODID_GENESIS_TIME = 2;

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
        case METHODID_INFLATION_RATE:
          serviceImpl.inflationRate((com.babylon.mint.v1.QueryProto.QueryInflationRateRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.mint.v1.QueryProto.QueryInflationRateResponse>) responseObserver);
          break;
        case METHODID_ANNUAL_PROVISIONS:
          serviceImpl.annualProvisions((com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsResponse>) responseObserver);
          break;
        case METHODID_GENESIS_TIME:
          serviceImpl.genesisTime((com.babylon.mint.v1.QueryProto.QueryGenesisTimeRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.mint.v1.QueryProto.QueryGenesisTimeResponse>) responseObserver);
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
          getInflationRateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.mint.v1.QueryProto.QueryInflationRateRequest,
              com.babylon.mint.v1.QueryProto.QueryInflationRateResponse>(
                service, METHODID_INFLATION_RATE)))
        .addMethod(
          getAnnualProvisionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsRequest,
              com.babylon.mint.v1.QueryProto.QueryAnnualProvisionsResponse>(
                service, METHODID_ANNUAL_PROVISIONS)))
        .addMethod(
          getGenesisTimeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.mint.v1.QueryProto.QueryGenesisTimeRequest,
              com.babylon.mint.v1.QueryProto.QueryGenesisTimeResponse>(
                service, METHODID_GENESIS_TIME)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.babylon.mint.v1.QueryProto.getDescriptor();
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
              .addMethod(getInflationRateMethod())
              .addMethod(getAnnualProvisionsMethod())
              .addMethod(getGenesisTimeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
