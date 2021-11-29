package injective.exchange.v1beta1;

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
    comments = "Source: injective/exchange/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "injective.exchange.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsResponse> getQueryExchangeParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryExchangeParams",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsResponse> getQueryExchangeParamsMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsRequest, injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsResponse> getQueryExchangeParamsMethod;
    if ((getQueryExchangeParamsMethod = QueryGrpc.getQueryExchangeParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getQueryExchangeParamsMethod = QueryGrpc.getQueryExchangeParamsMethod) == null) {
          QueryGrpc.getQueryExchangeParamsMethod = getQueryExchangeParamsMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsRequest, injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryExchangeParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("QueryExchangeParams"))
              .build();
        }
      }
    }
    return getQueryExchangeParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsRequest,
      injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsResponse> getSubaccountDepositsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubaccountDeposits",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsRequest,
      injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsResponse> getSubaccountDepositsMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsRequest, injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsResponse> getSubaccountDepositsMethod;
    if ((getSubaccountDepositsMethod = QueryGrpc.getSubaccountDepositsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSubaccountDepositsMethod = QueryGrpc.getSubaccountDepositsMethod) == null) {
          QueryGrpc.getSubaccountDepositsMethod = getSubaccountDepositsMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsRequest, injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubaccountDeposits"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SubaccountDeposits"))
              .build();
        }
      }
    }
    return getSubaccountDepositsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositRequest,
      injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositResponse> getSubaccountDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubaccountDeposit",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositRequest,
      injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositResponse> getSubaccountDepositMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositRequest, injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositResponse> getSubaccountDepositMethod;
    if ((getSubaccountDepositMethod = QueryGrpc.getSubaccountDepositMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSubaccountDepositMethod = QueryGrpc.getSubaccountDepositMethod) == null) {
          QueryGrpc.getSubaccountDepositMethod = getSubaccountDepositMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositRequest, injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubaccountDeposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SubaccountDeposit"))
              .build();
        }
      }
    }
    return getSubaccountDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesResponse> getExchangeBalancesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExchangeBalances",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesResponse> getExchangeBalancesMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesRequest, injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesResponse> getExchangeBalancesMethod;
    if ((getExchangeBalancesMethod = QueryGrpc.getExchangeBalancesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getExchangeBalancesMethod = QueryGrpc.getExchangeBalancesMethod) == null) {
          QueryGrpc.getExchangeBalancesMethod = getExchangeBalancesMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesRequest, injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExchangeBalances"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ExchangeBalances"))
              .build();
        }
      }
    }
    return getExchangeBalancesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsRequest,
      injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsResponse> getSpotMarketsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SpotMarkets",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsRequest,
      injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsResponse> getSpotMarketsMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsRequest, injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsResponse> getSpotMarketsMethod;
    if ((getSpotMarketsMethod = QueryGrpc.getSpotMarketsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSpotMarketsMethod = QueryGrpc.getSpotMarketsMethod) == null) {
          QueryGrpc.getSpotMarketsMethod = getSpotMarketsMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsRequest, injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SpotMarkets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SpotMarkets"))
              .build();
        }
      }
    }
    return getSpotMarketsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketRequest,
      injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketResponse> getSpotMarketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SpotMarket",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketRequest,
      injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketResponse> getSpotMarketMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketRequest, injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketResponse> getSpotMarketMethod;
    if ((getSpotMarketMethod = QueryGrpc.getSpotMarketMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSpotMarketMethod = QueryGrpc.getSpotMarketMethod) == null) {
          QueryGrpc.getSpotMarketMethod = getSpotMarketMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketRequest, injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SpotMarket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SpotMarket"))
              .build();
        }
      }
    }
    return getSpotMarketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookRequest,
      injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookResponse> getSpotOrderbookMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SpotOrderbook",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookRequest,
      injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookResponse> getSpotOrderbookMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookRequest, injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookResponse> getSpotOrderbookMethod;
    if ((getSpotOrderbookMethod = QueryGrpc.getSpotOrderbookMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSpotOrderbookMethod = QueryGrpc.getSpotOrderbookMethod) == null) {
          QueryGrpc.getSpotOrderbookMethod = getSpotOrderbookMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookRequest, injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SpotOrderbook"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SpotOrderbook"))
              .build();
        }
      }
    }
    return getSpotOrderbookMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersResponse> getTraderSpotOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TraderSpotOrders",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersResponse> getTraderSpotOrdersMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersRequest, injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersResponse> getTraderSpotOrdersMethod;
    if ((getTraderSpotOrdersMethod = QueryGrpc.getTraderSpotOrdersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTraderSpotOrdersMethod = QueryGrpc.getTraderSpotOrdersMethod) == null) {
          QueryGrpc.getTraderSpotOrdersMethod = getTraderSpotOrdersMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersRequest, injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TraderSpotOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TraderSpotOrders"))
              .build();
        }
      }
    }
    return getTraderSpotOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookResponse> getDerivativeOrderbookMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DerivativeOrderbook",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookResponse> getDerivativeOrderbookMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookRequest, injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookResponse> getDerivativeOrderbookMethod;
    if ((getDerivativeOrderbookMethod = QueryGrpc.getDerivativeOrderbookMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDerivativeOrderbookMethod = QueryGrpc.getDerivativeOrderbookMethod) == null) {
          QueryGrpc.getDerivativeOrderbookMethod = getDerivativeOrderbookMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookRequest, injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DerivativeOrderbook"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DerivativeOrderbook"))
              .build();
        }
      }
    }
    return getDerivativeOrderbookMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersResponse> getTraderDerivativeOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TraderDerivativeOrders",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersResponse> getTraderDerivativeOrdersMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersRequest, injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersResponse> getTraderDerivativeOrdersMethod;
    if ((getTraderDerivativeOrdersMethod = QueryGrpc.getTraderDerivativeOrdersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTraderDerivativeOrdersMethod = QueryGrpc.getTraderDerivativeOrdersMethod) == null) {
          QueryGrpc.getTraderDerivativeOrdersMethod = getTraderDerivativeOrdersMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersRequest, injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TraderDerivativeOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TraderDerivativeOrders"))
              .build();
        }
      }
    }
    return getTraderDerivativeOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsResponse> getDerivativeMarketsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DerivativeMarkets",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsResponse> getDerivativeMarketsMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsRequest, injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsResponse> getDerivativeMarketsMethod;
    if ((getDerivativeMarketsMethod = QueryGrpc.getDerivativeMarketsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDerivativeMarketsMethod = QueryGrpc.getDerivativeMarketsMethod) == null) {
          QueryGrpc.getDerivativeMarketsMethod = getDerivativeMarketsMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsRequest, injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DerivativeMarkets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DerivativeMarkets"))
              .build();
        }
      }
    }
    return getDerivativeMarketsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketResponse> getDerivativeMarketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DerivativeMarket",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketResponse> getDerivativeMarketMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketRequest, injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketResponse> getDerivativeMarketMethod;
    if ((getDerivativeMarketMethod = QueryGrpc.getDerivativeMarketMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDerivativeMarketMethod = QueryGrpc.getDerivativeMarketMethod) == null) {
          QueryGrpc.getDerivativeMarketMethod = getDerivativeMarketMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketRequest, injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DerivativeMarket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DerivativeMarket"))
              .build();
        }
      }
    }
    return getDerivativeMarketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceRequest,
      injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceResponse> getSubaccountTradeNonceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubaccountTradeNonce",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceRequest,
      injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceResponse> getSubaccountTradeNonceMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceRequest, injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceResponse> getSubaccountTradeNonceMethod;
    if ((getSubaccountTradeNonceMethod = QueryGrpc.getSubaccountTradeNonceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSubaccountTradeNonceMethod = QueryGrpc.getSubaccountTradeNonceMethod) == null) {
          QueryGrpc.getSubaccountTradeNonceMethod = getSubaccountTradeNonceMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceRequest, injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubaccountTradeNonce"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SubaccountTradeNonce"))
              .build();
        }
      }
    }
    return getSubaccountTradeNonceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateResponse> getExchangeModuleStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExchangeModuleState",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateResponse> getExchangeModuleStateMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateRequest, injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateResponse> getExchangeModuleStateMethod;
    if ((getExchangeModuleStateMethod = QueryGrpc.getExchangeModuleStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getExchangeModuleStateMethod = QueryGrpc.getExchangeModuleStateMethod) == null) {
          QueryGrpc.getExchangeModuleStateMethod = getExchangeModuleStateMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateRequest, injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExchangeModuleState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ExchangeModuleState"))
              .build();
        }
      }
    }
    return getExchangeModuleStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryPositionsRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryPositionsResponse> getPositionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Positions",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QueryPositionsRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QueryPositionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryPositionsRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryPositionsResponse> getPositionsMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryPositionsRequest, injective.exchange.v1beta1.QueryOuterClass.QueryPositionsResponse> getPositionsMethod;
    if ((getPositionsMethod = QueryGrpc.getPositionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPositionsMethod = QueryGrpc.getPositionsMethod) == null) {
          QueryGrpc.getPositionsMethod = getPositionsMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QueryPositionsRequest, injective.exchange.v1beta1.QueryOuterClass.QueryPositionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Positions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryPositionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryPositionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Positions"))
              .build();
        }
      }
    }
    return getPositionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsRequest,
      injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsResponse> getSubaccountPositionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubaccountPositions",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsRequest,
      injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsResponse> getSubaccountPositionsMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsRequest, injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsResponse> getSubaccountPositionsMethod;
    if ((getSubaccountPositionsMethod = QueryGrpc.getSubaccountPositionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSubaccountPositionsMethod = QueryGrpc.getSubaccountPositionsMethod) == null) {
          QueryGrpc.getSubaccountPositionsMethod = getSubaccountPositionsMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsRequest, injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubaccountPositions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SubaccountPositions"))
              .build();
        }
      }
    }
    return getSubaccountPositionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsResponse> getTradeRewardPointsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TradeRewardPoints",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsResponse> getTradeRewardPointsMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsRequest, injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsResponse> getTradeRewardPointsMethod;
    if ((getTradeRewardPointsMethod = QueryGrpc.getTradeRewardPointsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTradeRewardPointsMethod = QueryGrpc.getTradeRewardPointsMethod) == null) {
          QueryGrpc.getTradeRewardPointsMethod = getTradeRewardPointsMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsRequest, injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TradeRewardPoints"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TradeRewardPoints"))
              .build();
        }
      }
    }
    return getTradeRewardPointsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignResponse> getTradeRewardCampaignMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TradeRewardCampaign",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignResponse> getTradeRewardCampaignMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignRequest, injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignResponse> getTradeRewardCampaignMethod;
    if ((getTradeRewardCampaignMethod = QueryGrpc.getTradeRewardCampaignMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTradeRewardCampaignMethod = QueryGrpc.getTradeRewardCampaignMethod) == null) {
          QueryGrpc.getTradeRewardCampaignMethod = getTradeRewardCampaignMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignRequest, injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TradeRewardCampaign"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TradeRewardCampaign"))
              .build();
        }
      }
    }
    return getTradeRewardCampaignMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoResponse> getFeeDiscountAccountInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FeeDiscountAccountInfo",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoResponse> getFeeDiscountAccountInfoMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoRequest, injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoResponse> getFeeDiscountAccountInfoMethod;
    if ((getFeeDiscountAccountInfoMethod = QueryGrpc.getFeeDiscountAccountInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFeeDiscountAccountInfoMethod = QueryGrpc.getFeeDiscountAccountInfoMethod) == null) {
          QueryGrpc.getFeeDiscountAccountInfoMethod = getFeeDiscountAccountInfoMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoRequest, injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FeeDiscountAccountInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FeeDiscountAccountInfo"))
              .build();
        }
      }
    }
    return getFeeDiscountAccountInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleResponse> getFeeDiscountScheduleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FeeDiscountSchedule",
      requestType = injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleRequest.class,
      responseType = injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleRequest,
      injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleResponse> getFeeDiscountScheduleMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleRequest, injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleResponse> getFeeDiscountScheduleMethod;
    if ((getFeeDiscountScheduleMethod = QueryGrpc.getFeeDiscountScheduleMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFeeDiscountScheduleMethod = QueryGrpc.getFeeDiscountScheduleMethod) == null) {
          QueryGrpc.getFeeDiscountScheduleMethod = getFeeDiscountScheduleMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleRequest, injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FeeDiscountSchedule"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FeeDiscountSchedule"))
              .build();
        }
      }
    }
    return getFeeDiscountScheduleMethod;
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
     * Retrieves exchange params
     * </pre>
     */
    public void queryExchangeParams(injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryExchangeParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves a Subaccount's Deposits
     * </pre>
     */
    public void subaccountDeposits(injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubaccountDepositsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves a Subaccount's Deposits
     * </pre>
     */
    public void subaccountDeposit(injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubaccountDepositMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves all of the balances of all users on the exchange.
     * </pre>
     */
    public void exchangeBalances(injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getExchangeBalancesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves a list of spot markets.
     * </pre>
     */
    public void spotMarkets(injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSpotMarketsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves a spot market by ticker
     * </pre>
     */
    public void spotMarket(injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSpotMarketMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves a spot market's orderbook by marketID
     * </pre>
     */
    public void spotOrderbook(injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSpotOrderbookMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves a trader's spot orders
     * </pre>
     */
    public void traderSpotOrders(injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTraderSpotOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves a derivative market's orderbook by marketID
     * </pre>
     */
    public void derivativeOrderbook(injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDerivativeOrderbookMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves a trader's derivative orders
     * </pre>
     */
    public void traderDerivativeOrders(injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTraderDerivativeOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves a list of derivative markets.
     * </pre>
     */
    public void derivativeMarkets(injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDerivativeMarketsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves a derivative market by ticker
     * </pre>
     */
    public void derivativeMarket(injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDerivativeMarketMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves a subaccount's trade nonce
     * </pre>
     */
    public void subaccountTradeNonce(injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubaccountTradeNonceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire exchange module's state
     * </pre>
     */
    public void exchangeModuleState(injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getExchangeModuleStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire exchange module's positions
     * </pre>
     */
    public void positions(injective.exchange.v1beta1.QueryOuterClass.QueryPositionsRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryPositionsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPositionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves subaccount's positions
     * </pre>
     */
    public void subaccountPositions(injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubaccountPositionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the account and total liquidity mining points
     * </pre>
     */
    public void tradeRewardPoints(injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTradeRewardPointsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the trade reward campaign
     * </pre>
     */
    public void tradeRewardCampaign(injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTradeRewardCampaignMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the account's fee discount info
     * </pre>
     */
    public void feeDiscountAccountInfo(injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFeeDiscountAccountInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the fee discount schedule
     * </pre>
     */
    public void feeDiscountSchedule(injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFeeDiscountScheduleMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getQueryExchangeParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsRequest,
                injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsResponse>(
                  this, METHODID_QUERY_EXCHANGE_PARAMS)))
          .addMethod(
            getSubaccountDepositsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsRequest,
                injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsResponse>(
                  this, METHODID_SUBACCOUNT_DEPOSITS)))
          .addMethod(
            getSubaccountDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositRequest,
                injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositResponse>(
                  this, METHODID_SUBACCOUNT_DEPOSIT)))
          .addMethod(
            getExchangeBalancesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesRequest,
                injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesResponse>(
                  this, METHODID_EXCHANGE_BALANCES)))
          .addMethod(
            getSpotMarketsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsRequest,
                injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsResponse>(
                  this, METHODID_SPOT_MARKETS)))
          .addMethod(
            getSpotMarketMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketRequest,
                injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketResponse>(
                  this, METHODID_SPOT_MARKET)))
          .addMethod(
            getSpotOrderbookMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookRequest,
                injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookResponse>(
                  this, METHODID_SPOT_ORDERBOOK)))
          .addMethod(
            getTraderSpotOrdersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersRequest,
                injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersResponse>(
                  this, METHODID_TRADER_SPOT_ORDERS)))
          .addMethod(
            getDerivativeOrderbookMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookRequest,
                injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookResponse>(
                  this, METHODID_DERIVATIVE_ORDERBOOK)))
          .addMethod(
            getTraderDerivativeOrdersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersRequest,
                injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersResponse>(
                  this, METHODID_TRADER_DERIVATIVE_ORDERS)))
          .addMethod(
            getDerivativeMarketsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsRequest,
                injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsResponse>(
                  this, METHODID_DERIVATIVE_MARKETS)))
          .addMethod(
            getDerivativeMarketMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketRequest,
                injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketResponse>(
                  this, METHODID_DERIVATIVE_MARKET)))
          .addMethod(
            getSubaccountTradeNonceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceRequest,
                injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceResponse>(
                  this, METHODID_SUBACCOUNT_TRADE_NONCE)))
          .addMethod(
            getExchangeModuleStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateRequest,
                injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateResponse>(
                  this, METHODID_EXCHANGE_MODULE_STATE)))
          .addMethod(
            getPositionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QueryPositionsRequest,
                injective.exchange.v1beta1.QueryOuterClass.QueryPositionsResponse>(
                  this, METHODID_POSITIONS)))
          .addMethod(
            getSubaccountPositionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsRequest,
                injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsResponse>(
                  this, METHODID_SUBACCOUNT_POSITIONS)))
          .addMethod(
            getTradeRewardPointsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsRequest,
                injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsResponse>(
                  this, METHODID_TRADE_REWARD_POINTS)))
          .addMethod(
            getTradeRewardCampaignMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignRequest,
                injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignResponse>(
                  this, METHODID_TRADE_REWARD_CAMPAIGN)))
          .addMethod(
            getFeeDiscountAccountInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoRequest,
                injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoResponse>(
                  this, METHODID_FEE_DISCOUNT_ACCOUNT_INFO)))
          .addMethod(
            getFeeDiscountScheduleMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleRequest,
                injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleResponse>(
                  this, METHODID_FEE_DISCOUNT_SCHEDULE)))
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
     * Retrieves exchange params
     * </pre>
     */
    public void queryExchangeParams(injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryExchangeParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves a Subaccount's Deposits
     * </pre>
     */
    public void subaccountDeposits(injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubaccountDepositsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves a Subaccount's Deposits
     * </pre>
     */
    public void subaccountDeposit(injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubaccountDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves all of the balances of all users on the exchange.
     * </pre>
     */
    public void exchangeBalances(injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExchangeBalancesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves a list of spot markets.
     * </pre>
     */
    public void spotMarkets(injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSpotMarketsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves a spot market by ticker
     * </pre>
     */
    public void spotMarket(injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSpotMarketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves a spot market's orderbook by marketID
     * </pre>
     */
    public void spotOrderbook(injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSpotOrderbookMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves a trader's spot orders
     * </pre>
     */
    public void traderSpotOrders(injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTraderSpotOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves a derivative market's orderbook by marketID
     * </pre>
     */
    public void derivativeOrderbook(injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDerivativeOrderbookMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves a trader's derivative orders
     * </pre>
     */
    public void traderDerivativeOrders(injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTraderDerivativeOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves a list of derivative markets.
     * </pre>
     */
    public void derivativeMarkets(injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDerivativeMarketsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves a derivative market by ticker
     * </pre>
     */
    public void derivativeMarket(injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDerivativeMarketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves a subaccount's trade nonce
     * </pre>
     */
    public void subaccountTradeNonce(injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubaccountTradeNonceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire exchange module's state
     * </pre>
     */
    public void exchangeModuleState(injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExchangeModuleStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire exchange module's positions
     * </pre>
     */
    public void positions(injective.exchange.v1beta1.QueryOuterClass.QueryPositionsRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryPositionsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPositionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves subaccount's positions
     * </pre>
     */
    public void subaccountPositions(injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubaccountPositionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the account and total liquidity mining points
     * </pre>
     */
    public void tradeRewardPoints(injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTradeRewardPointsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the trade reward campaign
     * </pre>
     */
    public void tradeRewardCampaign(injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTradeRewardCampaignMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the account's fee discount info
     * </pre>
     */
    public void feeDiscountAccountInfo(injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFeeDiscountAccountInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the fee discount schedule
     * </pre>
     */
    public void feeDiscountSchedule(injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleRequest request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFeeDiscountScheduleMethod(), getCallOptions()), request, responseObserver);
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
     * Retrieves exchange params
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsResponse queryExchangeParams(injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryExchangeParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves a Subaccount's Deposits
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsResponse subaccountDeposits(injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsRequest request) {
      return blockingUnaryCall(
          getChannel(), getSubaccountDepositsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves a Subaccount's Deposits
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositResponse subaccountDeposit(injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositRequest request) {
      return blockingUnaryCall(
          getChannel(), getSubaccountDepositMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves all of the balances of all users on the exchange.
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesResponse exchangeBalances(injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesRequest request) {
      return blockingUnaryCall(
          getChannel(), getExchangeBalancesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves a list of spot markets.
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsResponse spotMarkets(injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsRequest request) {
      return blockingUnaryCall(
          getChannel(), getSpotMarketsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves a spot market by ticker
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketResponse spotMarket(injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketRequest request) {
      return blockingUnaryCall(
          getChannel(), getSpotMarketMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves a spot market's orderbook by marketID
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookResponse spotOrderbook(injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookRequest request) {
      return blockingUnaryCall(
          getChannel(), getSpotOrderbookMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves a trader's spot orders
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersResponse traderSpotOrders(injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersRequest request) {
      return blockingUnaryCall(
          getChannel(), getTraderSpotOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves a derivative market's orderbook by marketID
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookResponse derivativeOrderbook(injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookRequest request) {
      return blockingUnaryCall(
          getChannel(), getDerivativeOrderbookMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves a trader's derivative orders
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersResponse traderDerivativeOrders(injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersRequest request) {
      return blockingUnaryCall(
          getChannel(), getTraderDerivativeOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves a list of derivative markets.
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsResponse derivativeMarkets(injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsRequest request) {
      return blockingUnaryCall(
          getChannel(), getDerivativeMarketsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves a derivative market by ticker
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketResponse derivativeMarket(injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketRequest request) {
      return blockingUnaryCall(
          getChannel(), getDerivativeMarketMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves a subaccount's trade nonce
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceResponse subaccountTradeNonce(injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceRequest request) {
      return blockingUnaryCall(
          getChannel(), getSubaccountTradeNonceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the entire exchange module's state
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateResponse exchangeModuleState(injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateRequest request) {
      return blockingUnaryCall(
          getChannel(), getExchangeModuleStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the entire exchange module's positions
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QueryPositionsResponse positions(injective.exchange.v1beta1.QueryOuterClass.QueryPositionsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPositionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves subaccount's positions
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsResponse subaccountPositions(injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsRequest request) {
      return blockingUnaryCall(
          getChannel(), getSubaccountPositionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the account and total liquidity mining points
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsResponse tradeRewardPoints(injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsRequest request) {
      return blockingUnaryCall(
          getChannel(), getTradeRewardPointsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the trade reward campaign
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignResponse tradeRewardCampaign(injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignRequest request) {
      return blockingUnaryCall(
          getChannel(), getTradeRewardCampaignMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the account's fee discount info
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoResponse feeDiscountAccountInfo(injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoRequest request) {
      return blockingUnaryCall(
          getChannel(), getFeeDiscountAccountInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the fee discount schedule
     * </pre>
     */
    public injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleResponse feeDiscountSchedule(injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleRequest request) {
      return blockingUnaryCall(
          getChannel(), getFeeDiscountScheduleMethod(), getCallOptions(), request);
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
     * Retrieves exchange params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsResponse> queryExchangeParams(
        injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryExchangeParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves a Subaccount's Deposits
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsResponse> subaccountDeposits(
        injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSubaccountDepositsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves a Subaccount's Deposits
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositResponse> subaccountDeposit(
        injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSubaccountDepositMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves all of the balances of all users on the exchange.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesResponse> exchangeBalances(
        injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getExchangeBalancesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves a list of spot markets.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsResponse> spotMarkets(
        injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSpotMarketsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves a spot market by ticker
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketResponse> spotMarket(
        injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSpotMarketMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves a spot market's orderbook by marketID
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookResponse> spotOrderbook(
        injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSpotOrderbookMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves a trader's spot orders
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersResponse> traderSpotOrders(
        injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTraderSpotOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves a derivative market's orderbook by marketID
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookResponse> derivativeOrderbook(
        injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDerivativeOrderbookMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves a trader's derivative orders
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersResponse> traderDerivativeOrders(
        injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTraderDerivativeOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves a list of derivative markets.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsResponse> derivativeMarkets(
        injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDerivativeMarketsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves a derivative market by ticker
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketResponse> derivativeMarket(
        injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDerivativeMarketMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves a subaccount's trade nonce
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceResponse> subaccountTradeNonce(
        injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSubaccountTradeNonceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the entire exchange module's state
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateResponse> exchangeModuleState(
        injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getExchangeModuleStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the entire exchange module's positions
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QueryPositionsResponse> positions(
        injective.exchange.v1beta1.QueryOuterClass.QueryPositionsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPositionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves subaccount's positions
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsResponse> subaccountPositions(
        injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSubaccountPositionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the account and total liquidity mining points
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsResponse> tradeRewardPoints(
        injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTradeRewardPointsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the trade reward campaign
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignResponse> tradeRewardCampaign(
        injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTradeRewardCampaignMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the account's fee discount info
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoResponse> feeDiscountAccountInfo(
        injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFeeDiscountAccountInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the fee discount schedule
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleResponse> feeDiscountSchedule(
        injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFeeDiscountScheduleMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_EXCHANGE_PARAMS = 0;
  private static final int METHODID_SUBACCOUNT_DEPOSITS = 1;
  private static final int METHODID_SUBACCOUNT_DEPOSIT = 2;
  private static final int METHODID_EXCHANGE_BALANCES = 3;
  private static final int METHODID_SPOT_MARKETS = 4;
  private static final int METHODID_SPOT_MARKET = 5;
  private static final int METHODID_SPOT_ORDERBOOK = 6;
  private static final int METHODID_TRADER_SPOT_ORDERS = 7;
  private static final int METHODID_DERIVATIVE_ORDERBOOK = 8;
  private static final int METHODID_TRADER_DERIVATIVE_ORDERS = 9;
  private static final int METHODID_DERIVATIVE_MARKETS = 10;
  private static final int METHODID_DERIVATIVE_MARKET = 11;
  private static final int METHODID_SUBACCOUNT_TRADE_NONCE = 12;
  private static final int METHODID_EXCHANGE_MODULE_STATE = 13;
  private static final int METHODID_POSITIONS = 14;
  private static final int METHODID_SUBACCOUNT_POSITIONS = 15;
  private static final int METHODID_TRADE_REWARD_POINTS = 16;
  private static final int METHODID_TRADE_REWARD_CAMPAIGN = 17;
  private static final int METHODID_FEE_DISCOUNT_ACCOUNT_INFO = 18;
  private static final int METHODID_FEE_DISCOUNT_SCHEDULE = 19;

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
        case METHODID_QUERY_EXCHANGE_PARAMS:
          serviceImpl.queryExchangeParams((injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryExchangeParamsResponse>) responseObserver);
          break;
        case METHODID_SUBACCOUNT_DEPOSITS:
          serviceImpl.subaccountDeposits((injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositsResponse>) responseObserver);
          break;
        case METHODID_SUBACCOUNT_DEPOSIT:
          serviceImpl.subaccountDeposit((injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountDepositResponse>) responseObserver);
          break;
        case METHODID_EXCHANGE_BALANCES:
          serviceImpl.exchangeBalances((injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryExchangeBalancesResponse>) responseObserver);
          break;
        case METHODID_SPOT_MARKETS:
          serviceImpl.spotMarkets((injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketsResponse>) responseObserver);
          break;
        case METHODID_SPOT_MARKET:
          serviceImpl.spotMarket((injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySpotMarketResponse>) responseObserver);
          break;
        case METHODID_SPOT_ORDERBOOK:
          serviceImpl.spotOrderbook((injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySpotOrderbookResponse>) responseObserver);
          break;
        case METHODID_TRADER_SPOT_ORDERS:
          serviceImpl.traderSpotOrders((injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryTraderSpotOrdersResponse>) responseObserver);
          break;
        case METHODID_DERIVATIVE_ORDERBOOK:
          serviceImpl.derivativeOrderbook((injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeOrderbookResponse>) responseObserver);
          break;
        case METHODID_TRADER_DERIVATIVE_ORDERS:
          serviceImpl.traderDerivativeOrders((injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryTraderDerivativeOrdersResponse>) responseObserver);
          break;
        case METHODID_DERIVATIVE_MARKETS:
          serviceImpl.derivativeMarkets((injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketsResponse>) responseObserver);
          break;
        case METHODID_DERIVATIVE_MARKET:
          serviceImpl.derivativeMarket((injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryDerivativeMarketResponse>) responseObserver);
          break;
        case METHODID_SUBACCOUNT_TRADE_NONCE:
          serviceImpl.subaccountTradeNonce((injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountTradeNonceResponse>) responseObserver);
          break;
        case METHODID_EXCHANGE_MODULE_STATE:
          serviceImpl.exchangeModuleState((injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryModuleStateResponse>) responseObserver);
          break;
        case METHODID_POSITIONS:
          serviceImpl.positions((injective.exchange.v1beta1.QueryOuterClass.QueryPositionsRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryPositionsResponse>) responseObserver);
          break;
        case METHODID_SUBACCOUNT_POSITIONS:
          serviceImpl.subaccountPositions((injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QuerySubaccountPositionsResponse>) responseObserver);
          break;
        case METHODID_TRADE_REWARD_POINTS:
          serviceImpl.tradeRewardPoints((injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardPointsResponse>) responseObserver);
          break;
        case METHODID_TRADE_REWARD_CAMPAIGN:
          serviceImpl.tradeRewardCampaign((injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryTradeRewardCampaignResponse>) responseObserver);
          break;
        case METHODID_FEE_DISCOUNT_ACCOUNT_INFO:
          serviceImpl.feeDiscountAccountInfo((injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountAccountInfoResponse>) responseObserver);
          break;
        case METHODID_FEE_DISCOUNT_SCHEDULE:
          serviceImpl.feeDiscountSchedule((injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleRequest) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.QueryOuterClass.QueryFeeDiscountScheduleResponse>) responseObserver);
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
      return injective.exchange.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getQueryExchangeParamsMethod())
              .addMethod(getSubaccountDepositsMethod())
              .addMethod(getSubaccountDepositMethod())
              .addMethod(getExchangeBalancesMethod())
              .addMethod(getSpotMarketsMethod())
              .addMethod(getSpotMarketMethod())
              .addMethod(getSpotOrderbookMethod())
              .addMethod(getTraderSpotOrdersMethod())
              .addMethod(getDerivativeOrderbookMethod())
              .addMethod(getTraderDerivativeOrdersMethod())
              .addMethod(getDerivativeMarketsMethod())
              .addMethod(getDerivativeMarketMethod())
              .addMethod(getSubaccountTradeNonceMethod())
              .addMethod(getExchangeModuleStateMethod())
              .addMethod(getPositionsMethod())
              .addMethod(getSubaccountPositionsMethod())
              .addMethod(getTradeRewardPointsMethod())
              .addMethod(getTradeRewardCampaignMethod())
              .addMethod(getFeeDiscountAccountInfoMethod())
              .addMethod(getFeeDiscountScheduleMethod())
              .build();
        }
      }
    }
    return result;
  }
}
