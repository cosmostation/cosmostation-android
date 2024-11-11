package com.initia.tx.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query provides defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: initia/tx/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "initia.tx.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.initia.tx.v1.QueryProto.QueryGasPricesRequest,
      com.initia.tx.v1.QueryProto.QueryGasPricesResponse> getGasPricesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GasPrices",
      requestType = com.initia.tx.v1.QueryProto.QueryGasPricesRequest.class,
      responseType = com.initia.tx.v1.QueryProto.QueryGasPricesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.tx.v1.QueryProto.QueryGasPricesRequest,
      com.initia.tx.v1.QueryProto.QueryGasPricesResponse> getGasPricesMethod() {
    io.grpc.MethodDescriptor<com.initia.tx.v1.QueryProto.QueryGasPricesRequest, com.initia.tx.v1.QueryProto.QueryGasPricesResponse> getGasPricesMethod;
    if ((getGasPricesMethod = QueryGrpc.getGasPricesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGasPricesMethod = QueryGrpc.getGasPricesMethod) == null) {
          QueryGrpc.getGasPricesMethod = getGasPricesMethod =
              io.grpc.MethodDescriptor.<com.initia.tx.v1.QueryProto.QueryGasPricesRequest, com.initia.tx.v1.QueryProto.QueryGasPricesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GasPrices"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.tx.v1.QueryProto.QueryGasPricesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.tx.v1.QueryProto.QueryGasPricesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GasPrices"))
              .build();
        }
      }
    }
    return getGasPricesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.tx.v1.QueryProto.QueryGasPriceRequest,
      com.initia.tx.v1.QueryProto.QueryGasPriceResponse> getGasPriceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GasPrice",
      requestType = com.initia.tx.v1.QueryProto.QueryGasPriceRequest.class,
      responseType = com.initia.tx.v1.QueryProto.QueryGasPriceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.tx.v1.QueryProto.QueryGasPriceRequest,
      com.initia.tx.v1.QueryProto.QueryGasPriceResponse> getGasPriceMethod() {
    io.grpc.MethodDescriptor<com.initia.tx.v1.QueryProto.QueryGasPriceRequest, com.initia.tx.v1.QueryProto.QueryGasPriceResponse> getGasPriceMethod;
    if ((getGasPriceMethod = QueryGrpc.getGasPriceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGasPriceMethod = QueryGrpc.getGasPriceMethod) == null) {
          QueryGrpc.getGasPriceMethod = getGasPriceMethod =
              io.grpc.MethodDescriptor.<com.initia.tx.v1.QueryProto.QueryGasPriceRequest, com.initia.tx.v1.QueryProto.QueryGasPriceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GasPrice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.tx.v1.QueryProto.QueryGasPriceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.tx.v1.QueryProto.QueryGasPriceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GasPrice"))
              .build();
        }
      }
    }
    return getGasPriceMethod;
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
     * GasPrices returns the gas prices for the network.
     * </pre>
     */
    default void gasPrices(com.initia.tx.v1.QueryProto.QueryGasPricesRequest request,
        io.grpc.stub.StreamObserver<com.initia.tx.v1.QueryProto.QueryGasPricesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGasPricesMethod(), responseObserver);
    }

    /**
     * <pre>
     * GasPrice returns the gas price for the network.
     * </pre>
     */
    default void gasPrice(com.initia.tx.v1.QueryProto.QueryGasPriceRequest request,
        io.grpc.stub.StreamObserver<com.initia.tx.v1.QueryProto.QueryGasPriceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGasPriceMethod(), responseObserver);
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
     * GasPrices returns the gas prices for the network.
     * </pre>
     */
    public void gasPrices(com.initia.tx.v1.QueryProto.QueryGasPricesRequest request,
        io.grpc.stub.StreamObserver<com.initia.tx.v1.QueryProto.QueryGasPricesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGasPricesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GasPrice returns the gas price for the network.
     * </pre>
     */
    public void gasPrice(com.initia.tx.v1.QueryProto.QueryGasPriceRequest request,
        io.grpc.stub.StreamObserver<com.initia.tx.v1.QueryProto.QueryGasPriceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGasPriceMethod(), getCallOptions()), request, responseObserver);
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
     * GasPrices returns the gas prices for the network.
     * </pre>
     */
    public com.initia.tx.v1.QueryProto.QueryGasPricesResponse gasPrices(com.initia.tx.v1.QueryProto.QueryGasPricesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGasPricesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GasPrice returns the gas price for the network.
     * </pre>
     */
    public com.initia.tx.v1.QueryProto.QueryGasPriceResponse gasPrice(com.initia.tx.v1.QueryProto.QueryGasPriceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGasPriceMethod(), getCallOptions(), request);
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
     * GasPrices returns the gas prices for the network.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.tx.v1.QueryProto.QueryGasPricesResponse> gasPrices(
        com.initia.tx.v1.QueryProto.QueryGasPricesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGasPricesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GasPrice returns the gas price for the network.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.tx.v1.QueryProto.QueryGasPriceResponse> gasPrice(
        com.initia.tx.v1.QueryProto.QueryGasPriceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGasPriceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GAS_PRICES = 0;
  private static final int METHODID_GAS_PRICE = 1;

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
        case METHODID_GAS_PRICES:
          serviceImpl.gasPrices((com.initia.tx.v1.QueryProto.QueryGasPricesRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.tx.v1.QueryProto.QueryGasPricesResponse>) responseObserver);
          break;
        case METHODID_GAS_PRICE:
          serviceImpl.gasPrice((com.initia.tx.v1.QueryProto.QueryGasPriceRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.tx.v1.QueryProto.QueryGasPriceResponse>) responseObserver);
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
          getGasPricesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.tx.v1.QueryProto.QueryGasPricesRequest,
              com.initia.tx.v1.QueryProto.QueryGasPricesResponse>(
                service, METHODID_GAS_PRICES)))
        .addMethod(
          getGasPriceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.tx.v1.QueryProto.QueryGasPriceRequest,
              com.initia.tx.v1.QueryProto.QueryGasPriceResponse>(
                service, METHODID_GAS_PRICE)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.initia.tx.v1.QueryProto.getDescriptor();
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
              .addMethod(getGasPricesMethod())
              .addMethod(getGasPriceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
