package com.kava.pricefeed.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service for pricefeed module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: kava/pricefeed/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "kava.pricefeed.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryParamsRequest,
      com.kava.pricefeed.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.kava.pricefeed.v1beta1.QueryProto.QueryParamsRequest.class,
      responseType = com.kava.pricefeed.v1beta1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryParamsRequest,
      com.kava.pricefeed.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryParamsRequest, com.kava.pricefeed.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.kava.pricefeed.v1beta1.QueryProto.QueryParamsRequest, com.kava.pricefeed.v1beta1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.pricefeed.v1beta1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.pricefeed.v1beta1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryPriceRequest,
      com.kava.pricefeed.v1beta1.QueryProto.QueryPriceResponse> getPriceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Price",
      requestType = com.kava.pricefeed.v1beta1.QueryProto.QueryPriceRequest.class,
      responseType = com.kava.pricefeed.v1beta1.QueryProto.QueryPriceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryPriceRequest,
      com.kava.pricefeed.v1beta1.QueryProto.QueryPriceResponse> getPriceMethod() {
    io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryPriceRequest, com.kava.pricefeed.v1beta1.QueryProto.QueryPriceResponse> getPriceMethod;
    if ((getPriceMethod = QueryGrpc.getPriceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPriceMethod = QueryGrpc.getPriceMethod) == null) {
          QueryGrpc.getPriceMethod = getPriceMethod =
              io.grpc.MethodDescriptor.<com.kava.pricefeed.v1beta1.QueryProto.QueryPriceRequest, com.kava.pricefeed.v1beta1.QueryProto.QueryPriceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Price"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.pricefeed.v1beta1.QueryProto.QueryPriceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.pricefeed.v1beta1.QueryProto.QueryPriceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Price"))
              .build();
        }
      }
    }
    return getPriceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryPricesRequest,
      com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse> getPricesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Prices",
      requestType = com.kava.pricefeed.v1beta1.QueryProto.QueryPricesRequest.class,
      responseType = com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryPricesRequest,
      com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse> getPricesMethod() {
    io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryPricesRequest, com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse> getPricesMethod;
    if ((getPricesMethod = QueryGrpc.getPricesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPricesMethod = QueryGrpc.getPricesMethod) == null) {
          QueryGrpc.getPricesMethod = getPricesMethod =
              io.grpc.MethodDescriptor.<com.kava.pricefeed.v1beta1.QueryProto.QueryPricesRequest, com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Prices"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.pricefeed.v1beta1.QueryProto.QueryPricesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Prices"))
              .build();
        }
      }
    }
    return getPricesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesRequest,
      com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesResponse> getRawPricesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RawPrices",
      requestType = com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesRequest.class,
      responseType = com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesRequest,
      com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesResponse> getRawPricesMethod() {
    io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesRequest, com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesResponse> getRawPricesMethod;
    if ((getRawPricesMethod = QueryGrpc.getRawPricesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRawPricesMethod = QueryGrpc.getRawPricesMethod) == null) {
          QueryGrpc.getRawPricesMethod = getRawPricesMethod =
              io.grpc.MethodDescriptor.<com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesRequest, com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RawPrices"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RawPrices"))
              .build();
        }
      }
    }
    return getRawPricesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesRequest,
      com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesResponse> getOraclesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Oracles",
      requestType = com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesRequest.class,
      responseType = com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesRequest,
      com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesResponse> getOraclesMethod() {
    io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesRequest, com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesResponse> getOraclesMethod;
    if ((getOraclesMethod = QueryGrpc.getOraclesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOraclesMethod = QueryGrpc.getOraclesMethod) == null) {
          QueryGrpc.getOraclesMethod = getOraclesMethod =
              io.grpc.MethodDescriptor.<com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesRequest, com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Oracles"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Oracles"))
              .build();
        }
      }
    }
    return getOraclesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsRequest,
      com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsResponse> getMarketsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Markets",
      requestType = com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsRequest.class,
      responseType = com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsRequest,
      com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsResponse> getMarketsMethod() {
    io.grpc.MethodDescriptor<com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsRequest, com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsResponse> getMarketsMethod;
    if ((getMarketsMethod = QueryGrpc.getMarketsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getMarketsMethod = QueryGrpc.getMarketsMethod) == null) {
          QueryGrpc.getMarketsMethod = getMarketsMethod =
              io.grpc.MethodDescriptor.<com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsRequest, com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Markets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Markets"))
              .build();
        }
      }
    }
    return getMarketsMethod;
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
   * Query defines the gRPC querier service for pricefeed module
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Params queries all parameters of the pricefeed module.
     * </pre>
     */
    default void params(com.kava.pricefeed.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Price queries price details based on a market
     * </pre>
     */
    default void price(com.kava.pricefeed.v1beta1.QueryProto.QueryPriceRequest request,
        io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryPriceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPriceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Prices queries all prices
     * </pre>
     */
    default void prices(com.kava.pricefeed.v1beta1.QueryProto.QueryPricesRequest request,
        io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPricesMethod(), responseObserver);
    }

    /**
     * <pre>
     * RawPrices queries all raw prices based on a market
     * </pre>
     */
    default void rawPrices(com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesRequest request,
        io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRawPricesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Oracles queries all oracles based on a market
     * </pre>
     */
    default void oracles(com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesRequest request,
        io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOraclesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Markets queries all markets
     * </pre>
     */
    default void markets(com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsRequest request,
        io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMarketsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service for pricefeed module
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
   * Query defines the gRPC querier service for pricefeed module
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
     * Params queries all parameters of the pricefeed module.
     * </pre>
     */
    public void params(com.kava.pricefeed.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Price queries price details based on a market
     * </pre>
     */
    public void price(com.kava.pricefeed.v1beta1.QueryProto.QueryPriceRequest request,
        io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryPriceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPriceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Prices queries all prices
     * </pre>
     */
    public void prices(com.kava.pricefeed.v1beta1.QueryProto.QueryPricesRequest request,
        io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPricesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RawPrices queries all raw prices based on a market
     * </pre>
     */
    public void rawPrices(com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesRequest request,
        io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRawPricesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Oracles queries all oracles based on a market
     * </pre>
     */
    public void oracles(com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesRequest request,
        io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOraclesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Markets queries all markets
     * </pre>
     */
    public void markets(com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsRequest request,
        io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMarketsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for pricefeed module
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
     * Params queries all parameters of the pricefeed module.
     * </pre>
     */
    public com.kava.pricefeed.v1beta1.QueryProto.QueryParamsResponse params(com.kava.pricefeed.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Price queries price details based on a market
     * </pre>
     */
    public com.kava.pricefeed.v1beta1.QueryProto.QueryPriceResponse price(com.kava.pricefeed.v1beta1.QueryProto.QueryPriceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPriceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Prices queries all prices
     * </pre>
     */
    public com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse prices(com.kava.pricefeed.v1beta1.QueryProto.QueryPricesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPricesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RawPrices queries all raw prices based on a market
     * </pre>
     */
    public com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesResponse rawPrices(com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRawPricesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Oracles queries all oracles based on a market
     * </pre>
     */
    public com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesResponse oracles(com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOraclesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Markets queries all markets
     * </pre>
     */
    public com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsResponse markets(com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMarketsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for pricefeed module
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
     * Params queries all parameters of the pricefeed module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.pricefeed.v1beta1.QueryProto.QueryParamsResponse> params(
        com.kava.pricefeed.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Price queries price details based on a market
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.pricefeed.v1beta1.QueryProto.QueryPriceResponse> price(
        com.kava.pricefeed.v1beta1.QueryProto.QueryPriceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPriceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Prices queries all prices
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse> prices(
        com.kava.pricefeed.v1beta1.QueryProto.QueryPricesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPricesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RawPrices queries all raw prices based on a market
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesResponse> rawPrices(
        com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRawPricesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Oracles queries all oracles based on a market
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesResponse> oracles(
        com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOraclesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Markets queries all markets
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsResponse> markets(
        com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMarketsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_PRICE = 1;
  private static final int METHODID_PRICES = 2;
  private static final int METHODID_RAW_PRICES = 3;
  private static final int METHODID_ORACLES = 4;
  private static final int METHODID_MARKETS = 5;

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
          serviceImpl.params((com.kava.pricefeed.v1beta1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_PRICE:
          serviceImpl.price((com.kava.pricefeed.v1beta1.QueryProto.QueryPriceRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryPriceResponse>) responseObserver);
          break;
        case METHODID_PRICES:
          serviceImpl.prices((com.kava.pricefeed.v1beta1.QueryProto.QueryPricesRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse>) responseObserver);
          break;
        case METHODID_RAW_PRICES:
          serviceImpl.rawPrices((com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesResponse>) responseObserver);
          break;
        case METHODID_ORACLES:
          serviceImpl.oracles((com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesResponse>) responseObserver);
          break;
        case METHODID_MARKETS:
          serviceImpl.markets((com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsResponse>) responseObserver);
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
              com.kava.pricefeed.v1beta1.QueryProto.QueryParamsRequest,
              com.kava.pricefeed.v1beta1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getPriceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.pricefeed.v1beta1.QueryProto.QueryPriceRequest,
              com.kava.pricefeed.v1beta1.QueryProto.QueryPriceResponse>(
                service, METHODID_PRICE)))
        .addMethod(
          getPricesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.pricefeed.v1beta1.QueryProto.QueryPricesRequest,
              com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse>(
                service, METHODID_PRICES)))
        .addMethod(
          getRawPricesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesRequest,
              com.kava.pricefeed.v1beta1.QueryProto.QueryRawPricesResponse>(
                service, METHODID_RAW_PRICES)))
        .addMethod(
          getOraclesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesRequest,
              com.kava.pricefeed.v1beta1.QueryProto.QueryOraclesResponse>(
                service, METHODID_ORACLES)))
        .addMethod(
          getMarketsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsRequest,
              com.kava.pricefeed.v1beta1.QueryProto.QueryMarketsResponse>(
                service, METHODID_MARKETS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kava.pricefeed.v1beta1.QueryProto.getDescriptor();
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
              .addMethod(getPriceMethod())
              .addMethod(getPricesMethod())
              .addMethod(getRawPricesMethod())
              .addMethod(getOraclesMethod())
              .addMethod(getMarketsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
