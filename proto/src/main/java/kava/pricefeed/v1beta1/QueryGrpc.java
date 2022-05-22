package kava.pricefeed.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Query defines the gRPC querier service for pricefeed module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/pricefeed/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "kava.pricefeed.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsRequest,
      kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsRequest,
      kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsRequest, kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsRequest, kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceRequest,
      kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceResponse> getPriceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Price",
      requestType = kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceRequest.class,
      responseType = kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceRequest,
      kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceResponse> getPriceMethod() {
    io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceRequest, kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceResponse> getPriceMethod;
    if ((getPriceMethod = QueryGrpc.getPriceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPriceMethod = QueryGrpc.getPriceMethod) == null) {
          QueryGrpc.getPriceMethod = getPriceMethod =
              io.grpc.MethodDescriptor.<kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceRequest, kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Price"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Price"))
              .build();
        }
      }
    }
    return getPriceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesRequest,
      kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesResponse> getPricesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Prices",
      requestType = kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesRequest.class,
      responseType = kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesRequest,
      kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesResponse> getPricesMethod() {
    io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesRequest, kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesResponse> getPricesMethod;
    if ((getPricesMethod = QueryGrpc.getPricesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPricesMethod = QueryGrpc.getPricesMethod) == null) {
          QueryGrpc.getPricesMethod = getPricesMethod =
              io.grpc.MethodDescriptor.<kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesRequest, kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Prices"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Prices"))
              .build();
        }
      }
    }
    return getPricesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesRequest,
      kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesResponse> getRawPricesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RawPrices",
      requestType = kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesRequest.class,
      responseType = kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesRequest,
      kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesResponse> getRawPricesMethod() {
    io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesRequest, kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesResponse> getRawPricesMethod;
    if ((getRawPricesMethod = QueryGrpc.getRawPricesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRawPricesMethod = QueryGrpc.getRawPricesMethod) == null) {
          QueryGrpc.getRawPricesMethod = getRawPricesMethod =
              io.grpc.MethodDescriptor.<kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesRequest, kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RawPrices"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RawPrices"))
              .build();
        }
      }
    }
    return getRawPricesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesRequest,
      kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesResponse> getOraclesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Oracles",
      requestType = kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesRequest.class,
      responseType = kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesRequest,
      kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesResponse> getOraclesMethod() {
    io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesRequest, kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesResponse> getOraclesMethod;
    if ((getOraclesMethod = QueryGrpc.getOraclesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOraclesMethod = QueryGrpc.getOraclesMethod) == null) {
          QueryGrpc.getOraclesMethod = getOraclesMethod =
              io.grpc.MethodDescriptor.<kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesRequest, kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Oracles"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Oracles"))
              .build();
        }
      }
    }
    return getOraclesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsRequest,
      kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsResponse> getMarketsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Markets",
      requestType = kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsRequest.class,
      responseType = kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsRequest,
      kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsResponse> getMarketsMethod() {
    io.grpc.MethodDescriptor<kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsRequest, kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsResponse> getMarketsMethod;
    if ((getMarketsMethod = QueryGrpc.getMarketsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getMarketsMethod = QueryGrpc.getMarketsMethod) == null) {
          QueryGrpc.getMarketsMethod = getMarketsMethod =
              io.grpc.MethodDescriptor.<kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsRequest, kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Markets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsResponse.getDefaultInstance()))
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
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Params queries all parameters of the pricefeed module.
     * </pre>
     */
    public void params(kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Price queries price details based on a market
     * </pre>
     */
    public void price(kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceRequest request,
        io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPriceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Prices queries all prices
     * </pre>
     */
    public void prices(kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesRequest request,
        io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPricesMethod(), responseObserver);
    }

    /**
     * <pre>
     * RawPrices queries all raw prices based on a market
     * </pre>
     */
    public void rawPrices(kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesRequest request,
        io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRawPricesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Oracles queries all oracles based on a market
     * </pre>
     */
    public void oracles(kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesRequest request,
        io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOraclesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Markets queries all markets
     * </pre>
     */
    public void markets(kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsRequest request,
        io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMarketsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsRequest,
                kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getPriceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceRequest,
                kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceResponse>(
                  this, METHODID_PRICE)))
          .addMethod(
            getPricesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesRequest,
                kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesResponse>(
                  this, METHODID_PRICES)))
          .addMethod(
            getRawPricesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesRequest,
                kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesResponse>(
                  this, METHODID_RAW_PRICES)))
          .addMethod(
            getOraclesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesRequest,
                kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesResponse>(
                  this, METHODID_ORACLES)))
          .addMethod(
            getMarketsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsRequest,
                kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsResponse>(
                  this, METHODID_MARKETS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for pricefeed module
   * </pre>
   */
  public static final class QueryStub extends io.grpc.stub.AbstractAsyncStub<QueryStub> {
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
    public void params(kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Price queries price details based on a market
     * </pre>
     */
    public void price(kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceRequest request,
        io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPriceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Prices queries all prices
     * </pre>
     */
    public void prices(kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesRequest request,
        io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPricesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RawPrices queries all raw prices based on a market
     * </pre>
     */
    public void rawPrices(kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesRequest request,
        io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRawPricesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Oracles queries all oracles based on a market
     * </pre>
     */
    public void oracles(kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesRequest request,
        io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOraclesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Markets queries all markets
     * </pre>
     */
    public void markets(kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsRequest request,
        io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMarketsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for pricefeed module
   * </pre>
   */
  public static final class QueryBlockingStub extends io.grpc.stub.AbstractBlockingStub<QueryBlockingStub> {
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
    public kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsResponse params(kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Price queries price details based on a market
     * </pre>
     */
    public kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceResponse price(kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceRequest request) {
      return blockingUnaryCall(
          getChannel(), getPriceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Prices queries all prices
     * </pre>
     */
    public kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesResponse prices(kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesRequest request) {
      return blockingUnaryCall(
          getChannel(), getPricesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RawPrices queries all raw prices based on a market
     * </pre>
     */
    public kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesResponse rawPrices(kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesRequest request) {
      return blockingUnaryCall(
          getChannel(), getRawPricesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Oracles queries all oracles based on a market
     * </pre>
     */
    public kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesResponse oracles(kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesRequest request) {
      return blockingUnaryCall(
          getChannel(), getOraclesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Markets queries all markets
     * </pre>
     */
    public kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsResponse markets(kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsRequest request) {
      return blockingUnaryCall(
          getChannel(), getMarketsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for pricefeed module
   * </pre>
   */
  public static final class QueryFutureStub extends io.grpc.stub.AbstractFutureStub<QueryFutureStub> {
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
    public com.google.common.util.concurrent.ListenableFuture<kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Price queries price details based on a market
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceResponse> price(
        kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPriceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Prices queries all prices
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesResponse> prices(
        kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPricesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RawPrices queries all raw prices based on a market
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesResponse> rawPrices(
        kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRawPricesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Oracles queries all oracles based on a market
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesResponse> oracles(
        kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOraclesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Markets queries all markets
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsResponse> markets(
        kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsRequest request) {
      return futureUnaryCall(
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
    private final QueryImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(QueryImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PARAMS:
          serviceImpl.params((kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_PRICE:
          serviceImpl.price((kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceRequest) request,
              (io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryPriceResponse>) responseObserver);
          break;
        case METHODID_PRICES:
          serviceImpl.prices((kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesRequest) request,
              (io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryPricesResponse>) responseObserver);
          break;
        case METHODID_RAW_PRICES:
          serviceImpl.rawPrices((kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesRequest) request,
              (io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryRawPricesResponse>) responseObserver);
          break;
        case METHODID_ORACLES:
          serviceImpl.oracles((kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesRequest) request,
              (io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryOraclesResponse>) responseObserver);
          break;
        case METHODID_MARKETS:
          serviceImpl.markets((kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsRequest) request,
              (io.grpc.stub.StreamObserver<kava.pricefeed.v1beta1.QueryOuterClass.QueryMarketsResponse>) responseObserver);
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

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return kava.pricefeed.v1beta1.QueryOuterClass.getDescriptor();
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
