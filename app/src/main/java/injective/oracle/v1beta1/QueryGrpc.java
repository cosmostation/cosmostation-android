package injective.oracle.v1beta1;

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
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: injective/oracle/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "injective.oracle.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryParamsRequest,
      injective.oracle.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = injective.oracle.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = injective.oracle.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryParamsRequest,
      injective.oracle.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryParamsRequest, injective.oracle.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<injective.oracle.v1beta1.QueryOuterClass.QueryParamsRequest, injective.oracle.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersRequest,
      injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersResponse> getBandRelayersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BandRelayers",
      requestType = injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersRequest.class,
      responseType = injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersRequest,
      injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersResponse> getBandRelayersMethod() {
    io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersRequest, injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersResponse> getBandRelayersMethod;
    if ((getBandRelayersMethod = QueryGrpc.getBandRelayersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBandRelayersMethod = QueryGrpc.getBandRelayersMethod) == null) {
          QueryGrpc.getBandRelayersMethod = getBandRelayersMethod =
              io.grpc.MethodDescriptor.<injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersRequest, injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BandRelayers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BandRelayers"))
              .build();
        }
      }
    }
    return getBandRelayersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesRequest,
      injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesResponse> getBandPriceStatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BandPriceStates",
      requestType = injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesRequest.class,
      responseType = injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesRequest,
      injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesResponse> getBandPriceStatesMethod() {
    io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesRequest, injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesResponse> getBandPriceStatesMethod;
    if ((getBandPriceStatesMethod = QueryGrpc.getBandPriceStatesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBandPriceStatesMethod = QueryGrpc.getBandPriceStatesMethod) == null) {
          QueryGrpc.getBandPriceStatesMethod = getBandPriceStatesMethod =
              io.grpc.MethodDescriptor.<injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesRequest, injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BandPriceStates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BandPriceStates"))
              .build();
        }
      }
    }
    return getBandPriceStatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesRequest,
      injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesResponse> getBandIBCPriceStatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BandIBCPriceStates",
      requestType = injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesRequest.class,
      responseType = injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesRequest,
      injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesResponse> getBandIBCPriceStatesMethod() {
    io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesRequest, injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesResponse> getBandIBCPriceStatesMethod;
    if ((getBandIBCPriceStatesMethod = QueryGrpc.getBandIBCPriceStatesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBandIBCPriceStatesMethod = QueryGrpc.getBandIBCPriceStatesMethod) == null) {
          QueryGrpc.getBandIBCPriceStatesMethod = getBandIBCPriceStatesMethod =
              io.grpc.MethodDescriptor.<injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesRequest, injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BandIBCPriceStates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BandIBCPriceStates"))
              .build();
        }
      }
    }
    return getBandIBCPriceStatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesRequest,
      injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesResponse> getPriceFeedPriceStatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PriceFeedPriceStates",
      requestType = injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesRequest.class,
      responseType = injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesRequest,
      injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesResponse> getPriceFeedPriceStatesMethod() {
    io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesRequest, injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesResponse> getPriceFeedPriceStatesMethod;
    if ((getPriceFeedPriceStatesMethod = QueryGrpc.getPriceFeedPriceStatesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPriceFeedPriceStatesMethod = QueryGrpc.getPriceFeedPriceStatesMethod) == null) {
          QueryGrpc.getPriceFeedPriceStatesMethod = getPriceFeedPriceStatesMethod =
              io.grpc.MethodDescriptor.<injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesRequest, injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PriceFeedPriceStates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PriceFeedPriceStates"))
              .build();
        }
      }
    }
    return getPriceFeedPriceStatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesRequest,
      injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesResponse> getCoinbasePriceStatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CoinbasePriceStates",
      requestType = injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesRequest.class,
      responseType = injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesRequest,
      injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesResponse> getCoinbasePriceStatesMethod() {
    io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesRequest, injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesResponse> getCoinbasePriceStatesMethod;
    if ((getCoinbasePriceStatesMethod = QueryGrpc.getCoinbasePriceStatesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCoinbasePriceStatesMethod = QueryGrpc.getCoinbasePriceStatesMethod) == null) {
          QueryGrpc.getCoinbasePriceStatesMethod = getCoinbasePriceStatesMethod =
              io.grpc.MethodDescriptor.<injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesRequest, injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CoinbasePriceStates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CoinbasePriceStates"))
              .build();
        }
      }
    }
    return getCoinbasePriceStatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateRequest,
      injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateResponse> getOracleModuleStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OracleModuleState",
      requestType = injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateRequest.class,
      responseType = injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateRequest,
      injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateResponse> getOracleModuleStateMethod() {
    io.grpc.MethodDescriptor<injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateRequest, injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateResponse> getOracleModuleStateMethod;
    if ((getOracleModuleStateMethod = QueryGrpc.getOracleModuleStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOracleModuleStateMethod = QueryGrpc.getOracleModuleStateMethod) == null) {
          QueryGrpc.getOracleModuleStateMethod = getOracleModuleStateMethod =
              io.grpc.MethodDescriptor.<injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateRequest, injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OracleModuleState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OracleModuleState"))
              .build();
        }
      }
    }
    return getOracleModuleStateMethod;
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
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Retrieves oracle params
     * </pre>
     */
    public void params(injective.oracle.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the band relayers
     * </pre>
     */
    public void bandRelayers(injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersRequest request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBandRelayersMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all band price feeds
     * </pre>
     */
    public void bandPriceStates(injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesRequest request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBandPriceStatesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all band ibc price feeds
     * </pre>
     */
    public void bandIBCPriceStates(injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesRequest request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBandIBCPriceStatesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all price feeds
     * </pre>
     */
    public void priceFeedPriceStates(injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesRequest request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPriceFeedPriceStatesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all coinbase price feeds
     * </pre>
     */
    public void coinbasePriceStates(injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesRequest request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCoinbasePriceStatesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire oracle module's state
     * </pre>
     */
    public void oracleModuleState(injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOracleModuleStateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.oracle.v1beta1.QueryOuterClass.QueryParamsRequest,
                injective.oracle.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getBandRelayersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersRequest,
                injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersResponse>(
                  this, METHODID_BAND_RELAYERS)))
          .addMethod(
            getBandPriceStatesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesRequest,
                injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesResponse>(
                  this, METHODID_BAND_PRICE_STATES)))
          .addMethod(
            getBandIBCPriceStatesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesRequest,
                injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesResponse>(
                  this, METHODID_BAND_IBCPRICE_STATES)))
          .addMethod(
            getPriceFeedPriceStatesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesRequest,
                injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesResponse>(
                  this, METHODID_PRICE_FEED_PRICE_STATES)))
          .addMethod(
            getCoinbasePriceStatesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesRequest,
                injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesResponse>(
                  this, METHODID_COINBASE_PRICE_STATES)))
          .addMethod(
            getOracleModuleStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateRequest,
                injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateResponse>(
                  this, METHODID_ORACLE_MODULE_STATE)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * Retrieves oracle params
     * </pre>
     */
    public void params(injective.oracle.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the band relayers
     * </pre>
     */
    public void bandRelayers(injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersRequest request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBandRelayersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all band price feeds
     * </pre>
     */
    public void bandPriceStates(injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesRequest request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBandPriceStatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all band ibc price feeds
     * </pre>
     */
    public void bandIBCPriceStates(injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesRequest request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBandIBCPriceStatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all price feeds
     * </pre>
     */
    public void priceFeedPriceStates(injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesRequest request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPriceFeedPriceStatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all coinbase price feeds
     * </pre>
     */
    public void coinbasePriceStates(injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesRequest request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCoinbasePriceStatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire oracle module's state
     * </pre>
     */
    public void oracleModuleState(injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOracleModuleStateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * Retrieves oracle params
     * </pre>
     */
    public injective.oracle.v1beta1.QueryOuterClass.QueryParamsResponse params(injective.oracle.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the band relayers
     * </pre>
     */
    public injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersResponse bandRelayers(injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersRequest request) {
      return blockingUnaryCall(
          getChannel(), getBandRelayersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the state for all band price feeds
     * </pre>
     */
    public injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesResponse bandPriceStates(injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesRequest request) {
      return blockingUnaryCall(
          getChannel(), getBandPriceStatesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the state for all band ibc price feeds
     * </pre>
     */
    public injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesResponse bandIBCPriceStates(injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesRequest request) {
      return blockingUnaryCall(
          getChannel(), getBandIBCPriceStatesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the state for all price feeds
     * </pre>
     */
    public injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesResponse priceFeedPriceStates(injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesRequest request) {
      return blockingUnaryCall(
          getChannel(), getPriceFeedPriceStatesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the state for all coinbase price feeds
     * </pre>
     */
    public injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesResponse coinbasePriceStates(injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesRequest request) {
      return blockingUnaryCall(
          getChannel(), getCoinbasePriceStatesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the entire oracle module's state
     * </pre>
     */
    public injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateResponse oracleModuleState(injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateRequest request) {
      return blockingUnaryCall(
          getChannel(), getOracleModuleStateMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * Retrieves oracle params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.oracle.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        injective.oracle.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the band relayers
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersResponse> bandRelayers(
        injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBandRelayersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the state for all band price feeds
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesResponse> bandPriceStates(
        injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBandPriceStatesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the state for all band ibc price feeds
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesResponse> bandIBCPriceStates(
        injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBandIBCPriceStatesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the state for all price feeds
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesResponse> priceFeedPriceStates(
        injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPriceFeedPriceStatesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the state for all coinbase price feeds
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesResponse> coinbasePriceStates(
        injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCoinbasePriceStatesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the entire oracle module's state
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateResponse> oracleModuleState(
        injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOracleModuleStateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_BAND_RELAYERS = 1;
  private static final int METHODID_BAND_PRICE_STATES = 2;
  private static final int METHODID_BAND_IBCPRICE_STATES = 3;
  private static final int METHODID_PRICE_FEED_PRICE_STATES = 4;
  private static final int METHODID_COINBASE_PRICE_STATES = 5;
  private static final int METHODID_ORACLE_MODULE_STATE = 6;

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
          serviceImpl.params((injective.oracle.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_BAND_RELAYERS:
          serviceImpl.bandRelayers((injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersRequest) request,
              (io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryBandRelayersResponse>) responseObserver);
          break;
        case METHODID_BAND_PRICE_STATES:
          serviceImpl.bandPriceStates((injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesRequest) request,
              (io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryBandPriceStatesResponse>) responseObserver);
          break;
        case METHODID_BAND_IBCPRICE_STATES:
          serviceImpl.bandIBCPriceStates((injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesRequest) request,
              (io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryBandIBCPriceStatesResponse>) responseObserver);
          break;
        case METHODID_PRICE_FEED_PRICE_STATES:
          serviceImpl.priceFeedPriceStates((injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesRequest) request,
              (io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryPriceFeedPriceStatesResponse>) responseObserver);
          break;
        case METHODID_COINBASE_PRICE_STATES:
          serviceImpl.coinbasePriceStates((injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesRequest) request,
              (io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryCoinbasePriceStatesResponse>) responseObserver);
          break;
        case METHODID_ORACLE_MODULE_STATE:
          serviceImpl.oracleModuleState((injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateRequest) request,
              (io.grpc.stub.StreamObserver<injective.oracle.v1beta1.QueryOuterClass.QueryModuleStateResponse>) responseObserver);
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
      return injective.oracle.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getBandRelayersMethod())
              .addMethod(getBandPriceStatesMethod())
              .addMethod(getBandIBCPriceStatesMethod())
              .addMethod(getPriceFeedPriceStatesMethod())
              .addMethod(getCoinbasePriceStatesMethod())
              .addMethod(getOracleModuleStateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
