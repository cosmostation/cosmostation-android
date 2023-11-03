package com.injective.oracle.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: injective/oracle/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "injective.oracle.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryParamsRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.injective.oracle.v1beta1.QueryProto.QueryParamsRequest.class,
      responseType = com.injective.oracle.v1beta1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryParamsRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryParamsRequest, com.injective.oracle.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.injective.oracle.v1beta1.QueryProto.QueryParamsRequest, com.injective.oracle.v1beta1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersResponse> getBandRelayersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BandRelayers",
      requestType = com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersRequest.class,
      responseType = com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersResponse> getBandRelayersMethod() {
    io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersRequest, com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersResponse> getBandRelayersMethod;
    if ((getBandRelayersMethod = QueryGrpc.getBandRelayersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBandRelayersMethod = QueryGrpc.getBandRelayersMethod) == null) {
          QueryGrpc.getBandRelayersMethod = getBandRelayersMethod =
              io.grpc.MethodDescriptor.<com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersRequest, com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BandRelayers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BandRelayers"))
              .build();
        }
      }
    }
    return getBandRelayersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesResponse> getBandPriceStatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BandPriceStates",
      requestType = com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesRequest.class,
      responseType = com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesResponse> getBandPriceStatesMethod() {
    io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesRequest, com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesResponse> getBandPriceStatesMethod;
    if ((getBandPriceStatesMethod = QueryGrpc.getBandPriceStatesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBandPriceStatesMethod = QueryGrpc.getBandPriceStatesMethod) == null) {
          QueryGrpc.getBandPriceStatesMethod = getBandPriceStatesMethod =
              io.grpc.MethodDescriptor.<com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesRequest, com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BandPriceStates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BandPriceStates"))
              .build();
        }
      }
    }
    return getBandPriceStatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesResponse> getBandIBCPriceStatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BandIBCPriceStates",
      requestType = com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesRequest.class,
      responseType = com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesResponse> getBandIBCPriceStatesMethod() {
    io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesRequest, com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesResponse> getBandIBCPriceStatesMethod;
    if ((getBandIBCPriceStatesMethod = QueryGrpc.getBandIBCPriceStatesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBandIBCPriceStatesMethod = QueryGrpc.getBandIBCPriceStatesMethod) == null) {
          QueryGrpc.getBandIBCPriceStatesMethod = getBandIBCPriceStatesMethod =
              io.grpc.MethodDescriptor.<com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesRequest, com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BandIBCPriceStates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BandIBCPriceStates"))
              .build();
        }
      }
    }
    return getBandIBCPriceStatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesResponse> getPriceFeedPriceStatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PriceFeedPriceStates",
      requestType = com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesRequest.class,
      responseType = com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesResponse> getPriceFeedPriceStatesMethod() {
    io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesRequest, com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesResponse> getPriceFeedPriceStatesMethod;
    if ((getPriceFeedPriceStatesMethod = QueryGrpc.getPriceFeedPriceStatesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPriceFeedPriceStatesMethod = QueryGrpc.getPriceFeedPriceStatesMethod) == null) {
          QueryGrpc.getPriceFeedPriceStatesMethod = getPriceFeedPriceStatesMethod =
              io.grpc.MethodDescriptor.<com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesRequest, com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PriceFeedPriceStates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PriceFeedPriceStates"))
              .build();
        }
      }
    }
    return getPriceFeedPriceStatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesResponse> getCoinbasePriceStatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CoinbasePriceStates",
      requestType = com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesRequest.class,
      responseType = com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesResponse> getCoinbasePriceStatesMethod() {
    io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesRequest, com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesResponse> getCoinbasePriceStatesMethod;
    if ((getCoinbasePriceStatesMethod = QueryGrpc.getCoinbasePriceStatesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCoinbasePriceStatesMethod = QueryGrpc.getCoinbasePriceStatesMethod) == null) {
          QueryGrpc.getCoinbasePriceStatesMethod = getCoinbasePriceStatesMethod =
              io.grpc.MethodDescriptor.<com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesRequest, com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CoinbasePriceStates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CoinbasePriceStates"))
              .build();
        }
      }
    }
    return getCoinbasePriceStatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesResponse> getPythPriceStatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PythPriceStates",
      requestType = com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesRequest.class,
      responseType = com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesResponse> getPythPriceStatesMethod() {
    io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesRequest, com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesResponse> getPythPriceStatesMethod;
    if ((getPythPriceStatesMethod = QueryGrpc.getPythPriceStatesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPythPriceStatesMethod = QueryGrpc.getPythPriceStatesMethod) == null) {
          QueryGrpc.getPythPriceStatesMethod = getPythPriceStatesMethod =
              io.grpc.MethodDescriptor.<com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesRequest, com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PythPriceStates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PythPriceStates"))
              .build();
        }
      }
    }
    return getPythPriceStatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateResponse> getProviderPriceStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProviderPriceState",
      requestType = com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateRequest.class,
      responseType = com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateResponse> getProviderPriceStateMethod() {
    io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateRequest, com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateResponse> getProviderPriceStateMethod;
    if ((getProviderPriceStateMethod = QueryGrpc.getProviderPriceStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProviderPriceStateMethod = QueryGrpc.getProviderPriceStateMethod) == null) {
          QueryGrpc.getProviderPriceStateMethod = getProviderPriceStateMethod =
              io.grpc.MethodDescriptor.<com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateRequest, com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ProviderPriceState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ProviderPriceState"))
              .build();
        }
      }
    }
    return getProviderPriceStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryModuleStateRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryModuleStateResponse> getOracleModuleStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OracleModuleState",
      requestType = com.injective.oracle.v1beta1.QueryProto.QueryModuleStateRequest.class,
      responseType = com.injective.oracle.v1beta1.QueryProto.QueryModuleStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryModuleStateRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryModuleStateResponse> getOracleModuleStateMethod() {
    io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryModuleStateRequest, com.injective.oracle.v1beta1.QueryProto.QueryModuleStateResponse> getOracleModuleStateMethod;
    if ((getOracleModuleStateMethod = QueryGrpc.getOracleModuleStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOracleModuleStateMethod = QueryGrpc.getOracleModuleStateMethod) == null) {
          QueryGrpc.getOracleModuleStateMethod = getOracleModuleStateMethod =
              io.grpc.MethodDescriptor.<com.injective.oracle.v1beta1.QueryProto.QueryModuleStateRequest, com.injective.oracle.v1beta1.QueryProto.QueryModuleStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OracleModuleState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryModuleStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryModuleStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OracleModuleState"))
              .build();
        }
      }
    }
    return getOracleModuleStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsResponse> getHistoricalPriceRecordsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HistoricalPriceRecords",
      requestType = com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsRequest.class,
      responseType = com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsResponse> getHistoricalPriceRecordsMethod() {
    io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsRequest, com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsResponse> getHistoricalPriceRecordsMethod;
    if ((getHistoricalPriceRecordsMethod = QueryGrpc.getHistoricalPriceRecordsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHistoricalPriceRecordsMethod = QueryGrpc.getHistoricalPriceRecordsMethod) == null) {
          QueryGrpc.getHistoricalPriceRecordsMethod = getHistoricalPriceRecordsMethod =
              io.grpc.MethodDescriptor.<com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsRequest, com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HistoricalPriceRecords"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("HistoricalPriceRecords"))
              .build();
        }
      }
    }
    return getHistoricalPriceRecordsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityResponse> getOracleVolatilityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OracleVolatility",
      requestType = com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityRequest.class,
      responseType = com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityResponse> getOracleVolatilityMethod() {
    io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityRequest, com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityResponse> getOracleVolatilityMethod;
    if ((getOracleVolatilityMethod = QueryGrpc.getOracleVolatilityMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOracleVolatilityMethod = QueryGrpc.getOracleVolatilityMethod) == null) {
          QueryGrpc.getOracleVolatilityMethod = getOracleVolatilityMethod =
              io.grpc.MethodDescriptor.<com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityRequest, com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OracleVolatility"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OracleVolatility"))
              .build();
        }
      }
    }
    return getOracleVolatilityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoResponse> getOracleProvidersInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OracleProvidersInfo",
      requestType = com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoRequest.class,
      responseType = com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoResponse> getOracleProvidersInfoMethod() {
    io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoRequest, com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoResponse> getOracleProvidersInfoMethod;
    if ((getOracleProvidersInfoMethod = QueryGrpc.getOracleProvidersInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOracleProvidersInfoMethod = QueryGrpc.getOracleProvidersInfoMethod) == null) {
          QueryGrpc.getOracleProvidersInfoMethod = getOracleProvidersInfoMethod =
              io.grpc.MethodDescriptor.<com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoRequest, com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OracleProvidersInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OracleProvidersInfo"))
              .build();
        }
      }
    }
    return getOracleProvidersInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesResponse> getOracleProviderPricesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OracleProviderPrices",
      requestType = com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesRequest.class,
      responseType = com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesResponse> getOracleProviderPricesMethod() {
    io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesRequest, com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesResponse> getOracleProviderPricesMethod;
    if ((getOracleProviderPricesMethod = QueryGrpc.getOracleProviderPricesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOracleProviderPricesMethod = QueryGrpc.getOracleProviderPricesMethod) == null) {
          QueryGrpc.getOracleProviderPricesMethod = getOracleProviderPricesMethod =
              io.grpc.MethodDescriptor.<com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesRequest, com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OracleProviderPrices"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OracleProviderPrices"))
              .build();
        }
      }
    }
    return getOracleProviderPricesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceResponse> getOraclePriceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OraclePrice",
      requestType = com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceRequest.class,
      responseType = com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceResponse> getOraclePriceMethod() {
    io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceRequest, com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceResponse> getOraclePriceMethod;
    if ((getOraclePriceMethod = QueryGrpc.getOraclePriceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOraclePriceMethod = QueryGrpc.getOraclePriceMethod) == null) {
          QueryGrpc.getOraclePriceMethod = getOraclePriceMethod =
              io.grpc.MethodDescriptor.<com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceRequest, com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OraclePrice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OraclePrice"))
              .build();
        }
      }
    }
    return getOraclePriceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryPythPriceRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryPythPriceResponse> getPythPriceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PythPrice",
      requestType = com.injective.oracle.v1beta1.QueryProto.QueryPythPriceRequest.class,
      responseType = com.injective.oracle.v1beta1.QueryProto.QueryPythPriceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryPythPriceRequest,
      com.injective.oracle.v1beta1.QueryProto.QueryPythPriceResponse> getPythPriceMethod() {
    io.grpc.MethodDescriptor<com.injective.oracle.v1beta1.QueryProto.QueryPythPriceRequest, com.injective.oracle.v1beta1.QueryProto.QueryPythPriceResponse> getPythPriceMethod;
    if ((getPythPriceMethod = QueryGrpc.getPythPriceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPythPriceMethod = QueryGrpc.getPythPriceMethod) == null) {
          QueryGrpc.getPythPriceMethod = getPythPriceMethod =
              io.grpc.MethodDescriptor.<com.injective.oracle.v1beta1.QueryProto.QueryPythPriceRequest, com.injective.oracle.v1beta1.QueryProto.QueryPythPriceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PythPrice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryPythPriceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.oracle.v1beta1.QueryProto.QueryPythPriceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PythPrice"))
              .build();
        }
      }
    }
    return getPythPriceMethod;
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
     * Retrieves oracle params
     * </pre>
     */
    default void params(com.injective.oracle.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the band relayers
     * </pre>
     */
    default void bandRelayers(com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBandRelayersMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all band price feeds
     * </pre>
     */
    default void bandPriceStates(com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBandPriceStatesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all band ibc price feeds
     * </pre>
     */
    default void bandIBCPriceStates(com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBandIBCPriceStatesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all price feeds
     * </pre>
     */
    default void priceFeedPriceStates(com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPriceFeedPriceStatesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all coinbase price feeds
     * </pre>
     */
    default void coinbasePriceStates(com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCoinbasePriceStatesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all pyth price feeds
     * </pre>
     */
    default void pythPriceStates(com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPythPriceStatesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all provider price feeds
     * </pre>
     */
    default void providerPriceState(com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getProviderPriceStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire oracle module's state
     * </pre>
     */
    default void oracleModuleState(com.injective.oracle.v1beta1.QueryProto.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryModuleStateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOracleModuleStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves historical price records for a given OracleType and Symbol
     * </pre>
     */
    default void historicalPriceRecords(com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHistoricalPriceRecordsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves mixed volatility value for the specified pair of base/quote
     * </pre>
     */
    default void oracleVolatility(com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOracleVolatilityMethod(), responseObserver);
    }

    /**
     */
    default void oracleProvidersInfo(com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOracleProvidersInfoMethod(), responseObserver);
    }

    /**
     */
    default void oracleProviderPrices(com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOracleProviderPricesMethod(), responseObserver);
    }

    /**
     */
    default void oraclePrice(com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOraclePriceMethod(), responseObserver);
    }

    /**
     */
    default void pythPrice(com.injective.oracle.v1beta1.QueryProto.QueryPythPriceRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryPythPriceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPythPriceMethod(), responseObserver);
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
     * Retrieves oracle params
     * </pre>
     */
    public void params(com.injective.oracle.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the band relayers
     * </pre>
     */
    public void bandRelayers(com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBandRelayersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all band price feeds
     * </pre>
     */
    public void bandPriceStates(com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBandPriceStatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all band ibc price feeds
     * </pre>
     */
    public void bandIBCPriceStates(com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBandIBCPriceStatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all price feeds
     * </pre>
     */
    public void priceFeedPriceStates(com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPriceFeedPriceStatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all coinbase price feeds
     * </pre>
     */
    public void coinbasePriceStates(com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCoinbasePriceStatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all pyth price feeds
     * </pre>
     */
    public void pythPriceStates(com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPythPriceStatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the state for all provider price feeds
     * </pre>
     */
    public void providerPriceState(com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getProviderPriceStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire oracle module's state
     * </pre>
     */
    public void oracleModuleState(com.injective.oracle.v1beta1.QueryProto.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryModuleStateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOracleModuleStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves historical price records for a given OracleType and Symbol
     * </pre>
     */
    public void historicalPriceRecords(com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHistoricalPriceRecordsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves mixed volatility value for the specified pair of base/quote
     * </pre>
     */
    public void oracleVolatility(com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOracleVolatilityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void oracleProvidersInfo(com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOracleProvidersInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void oracleProviderPrices(com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOracleProviderPricesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void oraclePrice(com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOraclePriceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void pythPrice(com.injective.oracle.v1beta1.QueryProto.QueryPythPriceRequest request,
        io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryPythPriceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPythPriceMethod(), getCallOptions()), request, responseObserver);
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
     * Retrieves oracle params
     * </pre>
     */
    public com.injective.oracle.v1beta1.QueryProto.QueryParamsResponse params(com.injective.oracle.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the band relayers
     * </pre>
     */
    public com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersResponse bandRelayers(com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBandRelayersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the state for all band price feeds
     * </pre>
     */
    public com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesResponse bandPriceStates(com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBandPriceStatesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the state for all band ibc price feeds
     * </pre>
     */
    public com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesResponse bandIBCPriceStates(com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBandIBCPriceStatesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the state for all price feeds
     * </pre>
     */
    public com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesResponse priceFeedPriceStates(com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPriceFeedPriceStatesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the state for all coinbase price feeds
     * </pre>
     */
    public com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesResponse coinbasePriceStates(com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCoinbasePriceStatesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the state for all pyth price feeds
     * </pre>
     */
    public com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesResponse pythPriceStates(com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPythPriceStatesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the state for all provider price feeds
     * </pre>
     */
    public com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateResponse providerPriceState(com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getProviderPriceStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the entire oracle module's state
     * </pre>
     */
    public com.injective.oracle.v1beta1.QueryProto.QueryModuleStateResponse oracleModuleState(com.injective.oracle.v1beta1.QueryProto.QueryModuleStateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOracleModuleStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves historical price records for a given OracleType and Symbol
     * </pre>
     */
    public com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsResponse historicalPriceRecords(com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHistoricalPriceRecordsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves mixed volatility value for the specified pair of base/quote
     * </pre>
     */
    public com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityResponse oracleVolatility(com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOracleVolatilityMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoResponse oracleProvidersInfo(com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOracleProvidersInfoMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesResponse oracleProviderPrices(com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOracleProviderPricesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceResponse oraclePrice(com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOraclePriceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.oracle.v1beta1.QueryProto.QueryPythPriceResponse pythPrice(com.injective.oracle.v1beta1.QueryProto.QueryPythPriceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPythPriceMethod(), getCallOptions(), request);
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
     * Retrieves oracle params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.oracle.v1beta1.QueryProto.QueryParamsResponse> params(
        com.injective.oracle.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the band relayers
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersResponse> bandRelayers(
        com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBandRelayersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the state for all band price feeds
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesResponse> bandPriceStates(
        com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBandPriceStatesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the state for all band ibc price feeds
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesResponse> bandIBCPriceStates(
        com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBandIBCPriceStatesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the state for all price feeds
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesResponse> priceFeedPriceStates(
        com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPriceFeedPriceStatesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the state for all coinbase price feeds
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesResponse> coinbasePriceStates(
        com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCoinbasePriceStatesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the state for all pyth price feeds
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesResponse> pythPriceStates(
        com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPythPriceStatesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the state for all provider price feeds
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateResponse> providerPriceState(
        com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getProviderPriceStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the entire oracle module's state
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.oracle.v1beta1.QueryProto.QueryModuleStateResponse> oracleModuleState(
        com.injective.oracle.v1beta1.QueryProto.QueryModuleStateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOracleModuleStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves historical price records for a given OracleType and Symbol
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsResponse> historicalPriceRecords(
        com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHistoricalPriceRecordsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves mixed volatility value for the specified pair of base/quote
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityResponse> oracleVolatility(
        com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOracleVolatilityMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoResponse> oracleProvidersInfo(
        com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOracleProvidersInfoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesResponse> oracleProviderPrices(
        com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOracleProviderPricesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceResponse> oraclePrice(
        com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOraclePriceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.oracle.v1beta1.QueryProto.QueryPythPriceResponse> pythPrice(
        com.injective.oracle.v1beta1.QueryProto.QueryPythPriceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPythPriceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_BAND_RELAYERS = 1;
  private static final int METHODID_BAND_PRICE_STATES = 2;
  private static final int METHODID_BAND_IBCPRICE_STATES = 3;
  private static final int METHODID_PRICE_FEED_PRICE_STATES = 4;
  private static final int METHODID_COINBASE_PRICE_STATES = 5;
  private static final int METHODID_PYTH_PRICE_STATES = 6;
  private static final int METHODID_PROVIDER_PRICE_STATE = 7;
  private static final int METHODID_ORACLE_MODULE_STATE = 8;
  private static final int METHODID_HISTORICAL_PRICE_RECORDS = 9;
  private static final int METHODID_ORACLE_VOLATILITY = 10;
  private static final int METHODID_ORACLE_PROVIDERS_INFO = 11;
  private static final int METHODID_ORACLE_PROVIDER_PRICES = 12;
  private static final int METHODID_ORACLE_PRICE = 13;
  private static final int METHODID_PYTH_PRICE = 14;

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
          serviceImpl.params((com.injective.oracle.v1beta1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_BAND_RELAYERS:
          serviceImpl.bandRelayers((com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersResponse>) responseObserver);
          break;
        case METHODID_BAND_PRICE_STATES:
          serviceImpl.bandPriceStates((com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesResponse>) responseObserver);
          break;
        case METHODID_BAND_IBCPRICE_STATES:
          serviceImpl.bandIBCPriceStates((com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesResponse>) responseObserver);
          break;
        case METHODID_PRICE_FEED_PRICE_STATES:
          serviceImpl.priceFeedPriceStates((com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesResponse>) responseObserver);
          break;
        case METHODID_COINBASE_PRICE_STATES:
          serviceImpl.coinbasePriceStates((com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesResponse>) responseObserver);
          break;
        case METHODID_PYTH_PRICE_STATES:
          serviceImpl.pythPriceStates((com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesResponse>) responseObserver);
          break;
        case METHODID_PROVIDER_PRICE_STATE:
          serviceImpl.providerPriceState((com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateResponse>) responseObserver);
          break;
        case METHODID_ORACLE_MODULE_STATE:
          serviceImpl.oracleModuleState((com.injective.oracle.v1beta1.QueryProto.QueryModuleStateRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryModuleStateResponse>) responseObserver);
          break;
        case METHODID_HISTORICAL_PRICE_RECORDS:
          serviceImpl.historicalPriceRecords((com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsResponse>) responseObserver);
          break;
        case METHODID_ORACLE_VOLATILITY:
          serviceImpl.oracleVolatility((com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityResponse>) responseObserver);
          break;
        case METHODID_ORACLE_PROVIDERS_INFO:
          serviceImpl.oracleProvidersInfo((com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoResponse>) responseObserver);
          break;
        case METHODID_ORACLE_PROVIDER_PRICES:
          serviceImpl.oracleProviderPrices((com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesResponse>) responseObserver);
          break;
        case METHODID_ORACLE_PRICE:
          serviceImpl.oraclePrice((com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceResponse>) responseObserver);
          break;
        case METHODID_PYTH_PRICE:
          serviceImpl.pythPrice((com.injective.oracle.v1beta1.QueryProto.QueryPythPriceRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.oracle.v1beta1.QueryProto.QueryPythPriceResponse>) responseObserver);
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
              com.injective.oracle.v1beta1.QueryProto.QueryParamsRequest,
              com.injective.oracle.v1beta1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getBandRelayersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersRequest,
              com.injective.oracle.v1beta1.QueryProto.QueryBandRelayersResponse>(
                service, METHODID_BAND_RELAYERS)))
        .addMethod(
          getBandPriceStatesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesRequest,
              com.injective.oracle.v1beta1.QueryProto.QueryBandPriceStatesResponse>(
                service, METHODID_BAND_PRICE_STATES)))
        .addMethod(
          getBandIBCPriceStatesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesRequest,
              com.injective.oracle.v1beta1.QueryProto.QueryBandIBCPriceStatesResponse>(
                service, METHODID_BAND_IBCPRICE_STATES)))
        .addMethod(
          getPriceFeedPriceStatesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesRequest,
              com.injective.oracle.v1beta1.QueryProto.QueryPriceFeedPriceStatesResponse>(
                service, METHODID_PRICE_FEED_PRICE_STATES)))
        .addMethod(
          getCoinbasePriceStatesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesRequest,
              com.injective.oracle.v1beta1.QueryProto.QueryCoinbasePriceStatesResponse>(
                service, METHODID_COINBASE_PRICE_STATES)))
        .addMethod(
          getPythPriceStatesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesRequest,
              com.injective.oracle.v1beta1.QueryProto.QueryPythPriceStatesResponse>(
                service, METHODID_PYTH_PRICE_STATES)))
        .addMethod(
          getProviderPriceStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateRequest,
              com.injective.oracle.v1beta1.QueryProto.QueryProviderPriceStateResponse>(
                service, METHODID_PROVIDER_PRICE_STATE)))
        .addMethod(
          getOracleModuleStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.oracle.v1beta1.QueryProto.QueryModuleStateRequest,
              com.injective.oracle.v1beta1.QueryProto.QueryModuleStateResponse>(
                service, METHODID_ORACLE_MODULE_STATE)))
        .addMethod(
          getHistoricalPriceRecordsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsRequest,
              com.injective.oracle.v1beta1.QueryProto.QueryHistoricalPriceRecordsResponse>(
                service, METHODID_HISTORICAL_PRICE_RECORDS)))
        .addMethod(
          getOracleVolatilityMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityRequest,
              com.injective.oracle.v1beta1.QueryProto.QueryOracleVolatilityResponse>(
                service, METHODID_ORACLE_VOLATILITY)))
        .addMethod(
          getOracleProvidersInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoRequest,
              com.injective.oracle.v1beta1.QueryProto.QueryOracleProvidersInfoResponse>(
                service, METHODID_ORACLE_PROVIDERS_INFO)))
        .addMethod(
          getOracleProviderPricesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesRequest,
              com.injective.oracle.v1beta1.QueryProto.QueryOracleProviderPricesResponse>(
                service, METHODID_ORACLE_PROVIDER_PRICES)))
        .addMethod(
          getOraclePriceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceRequest,
              com.injective.oracle.v1beta1.QueryProto.QueryOraclePriceResponse>(
                service, METHODID_ORACLE_PRICE)))
        .addMethod(
          getPythPriceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.oracle.v1beta1.QueryProto.QueryPythPriceRequest,
              com.injective.oracle.v1beta1.QueryProto.QueryPythPriceResponse>(
                service, METHODID_PYTH_PRICE)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.injective.oracle.v1beta1.QueryProto.getDescriptor();
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
              .addMethod(getPythPriceStatesMethod())
              .addMethod(getProviderPriceStateMethod())
              .addMethod(getOracleModuleStateMethod())
              .addMethod(getHistoricalPriceRecordsMethod())
              .addMethod(getOracleVolatilityMethod())
              .addMethod(getOracleProvidersInfoMethod())
              .addMethod(getOracleProviderPricesMethod())
              .addMethod(getOraclePriceMethod())
              .addMethod(getPythPriceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
