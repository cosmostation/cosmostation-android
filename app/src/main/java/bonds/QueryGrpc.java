package bonds;

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
    comments = "Source: bonds/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "bonds.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryBondsRequest,
      bonds.QueryOuterClass.QueryBondsResponse> getBondsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Bonds",
      requestType = bonds.QueryOuterClass.QueryBondsRequest.class,
      responseType = bonds.QueryOuterClass.QueryBondsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryBondsRequest,
      bonds.QueryOuterClass.QueryBondsResponse> getBondsMethod() {
    io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryBondsRequest, bonds.QueryOuterClass.QueryBondsResponse> getBondsMethod;
    if ((getBondsMethod = QueryGrpc.getBondsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBondsMethod = QueryGrpc.getBondsMethod) == null) {
          QueryGrpc.getBondsMethod = getBondsMethod =
              io.grpc.MethodDescriptor.<bonds.QueryOuterClass.QueryBondsRequest, bonds.QueryOuterClass.QueryBondsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Bonds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryBondsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryBondsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Bonds"))
              .build();
        }
      }
    }
    return getBondsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryBondsDetailedRequest,
      bonds.QueryOuterClass.QueryBondsDetailedResponse> getBondsDetailedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BondsDetailed",
      requestType = bonds.QueryOuterClass.QueryBondsDetailedRequest.class,
      responseType = bonds.QueryOuterClass.QueryBondsDetailedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryBondsDetailedRequest,
      bonds.QueryOuterClass.QueryBondsDetailedResponse> getBondsDetailedMethod() {
    io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryBondsDetailedRequest, bonds.QueryOuterClass.QueryBondsDetailedResponse> getBondsDetailedMethod;
    if ((getBondsDetailedMethod = QueryGrpc.getBondsDetailedMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBondsDetailedMethod = QueryGrpc.getBondsDetailedMethod) == null) {
          QueryGrpc.getBondsDetailedMethod = getBondsDetailedMethod =
              io.grpc.MethodDescriptor.<bonds.QueryOuterClass.QueryBondsDetailedRequest, bonds.QueryOuterClass.QueryBondsDetailedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BondsDetailed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryBondsDetailedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryBondsDetailedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BondsDetailed"))
              .build();
        }
      }
    }
    return getBondsDetailedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryParamsRequest,
      bonds.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = bonds.QueryOuterClass.QueryParamsRequest.class,
      responseType = bonds.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryParamsRequest,
      bonds.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryParamsRequest, bonds.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<bonds.QueryOuterClass.QueryParamsRequest, bonds.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryBondRequest,
      bonds.QueryOuterClass.QueryBondResponse> getBondMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Bond",
      requestType = bonds.QueryOuterClass.QueryBondRequest.class,
      responseType = bonds.QueryOuterClass.QueryBondResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryBondRequest,
      bonds.QueryOuterClass.QueryBondResponse> getBondMethod() {
    io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryBondRequest, bonds.QueryOuterClass.QueryBondResponse> getBondMethod;
    if ((getBondMethod = QueryGrpc.getBondMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBondMethod = QueryGrpc.getBondMethod) == null) {
          QueryGrpc.getBondMethod = getBondMethod =
              io.grpc.MethodDescriptor.<bonds.QueryOuterClass.QueryBondRequest, bonds.QueryOuterClass.QueryBondResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Bond"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryBondRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryBondResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Bond"))
              .build();
        }
      }
    }
    return getBondMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryBatchRequest,
      bonds.QueryOuterClass.QueryBatchResponse> getBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Batch",
      requestType = bonds.QueryOuterClass.QueryBatchRequest.class,
      responseType = bonds.QueryOuterClass.QueryBatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryBatchRequest,
      bonds.QueryOuterClass.QueryBatchResponse> getBatchMethod() {
    io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryBatchRequest, bonds.QueryOuterClass.QueryBatchResponse> getBatchMethod;
    if ((getBatchMethod = QueryGrpc.getBatchMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchMethod = QueryGrpc.getBatchMethod) == null) {
          QueryGrpc.getBatchMethod = getBatchMethod =
              io.grpc.MethodDescriptor.<bonds.QueryOuterClass.QueryBatchRequest, bonds.QueryOuterClass.QueryBatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Batch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryBatchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryBatchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Batch"))
              .build();
        }
      }
    }
    return getBatchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryLastBatchRequest,
      bonds.QueryOuterClass.QueryLastBatchResponse> getLastBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastBatch",
      requestType = bonds.QueryOuterClass.QueryLastBatchRequest.class,
      responseType = bonds.QueryOuterClass.QueryLastBatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryLastBatchRequest,
      bonds.QueryOuterClass.QueryLastBatchResponse> getLastBatchMethod() {
    io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryLastBatchRequest, bonds.QueryOuterClass.QueryLastBatchResponse> getLastBatchMethod;
    if ((getLastBatchMethod = QueryGrpc.getLastBatchMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastBatchMethod = QueryGrpc.getLastBatchMethod) == null) {
          QueryGrpc.getLastBatchMethod = getLastBatchMethod =
              io.grpc.MethodDescriptor.<bonds.QueryOuterClass.QueryLastBatchRequest, bonds.QueryOuterClass.QueryLastBatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryLastBatchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryLastBatchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastBatch"))
              .build();
        }
      }
    }
    return getLastBatchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryCurrentPriceRequest,
      bonds.QueryOuterClass.QueryCurrentPriceResponse> getCurrentPriceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CurrentPrice",
      requestType = bonds.QueryOuterClass.QueryCurrentPriceRequest.class,
      responseType = bonds.QueryOuterClass.QueryCurrentPriceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryCurrentPriceRequest,
      bonds.QueryOuterClass.QueryCurrentPriceResponse> getCurrentPriceMethod() {
    io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryCurrentPriceRequest, bonds.QueryOuterClass.QueryCurrentPriceResponse> getCurrentPriceMethod;
    if ((getCurrentPriceMethod = QueryGrpc.getCurrentPriceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCurrentPriceMethod = QueryGrpc.getCurrentPriceMethod) == null) {
          QueryGrpc.getCurrentPriceMethod = getCurrentPriceMethod =
              io.grpc.MethodDescriptor.<bonds.QueryOuterClass.QueryCurrentPriceRequest, bonds.QueryOuterClass.QueryCurrentPriceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CurrentPrice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryCurrentPriceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryCurrentPriceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CurrentPrice"))
              .build();
        }
      }
    }
    return getCurrentPriceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryCurrentReserveRequest,
      bonds.QueryOuterClass.QueryCurrentReserveResponse> getCurrentReserveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CurrentReserve",
      requestType = bonds.QueryOuterClass.QueryCurrentReserveRequest.class,
      responseType = bonds.QueryOuterClass.QueryCurrentReserveResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryCurrentReserveRequest,
      bonds.QueryOuterClass.QueryCurrentReserveResponse> getCurrentReserveMethod() {
    io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryCurrentReserveRequest, bonds.QueryOuterClass.QueryCurrentReserveResponse> getCurrentReserveMethod;
    if ((getCurrentReserveMethod = QueryGrpc.getCurrentReserveMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCurrentReserveMethod = QueryGrpc.getCurrentReserveMethod) == null) {
          QueryGrpc.getCurrentReserveMethod = getCurrentReserveMethod =
              io.grpc.MethodDescriptor.<bonds.QueryOuterClass.QueryCurrentReserveRequest, bonds.QueryOuterClass.QueryCurrentReserveResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CurrentReserve"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryCurrentReserveRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryCurrentReserveResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CurrentReserve"))
              .build();
        }
      }
    }
    return getCurrentReserveMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryAvailableReserveRequest,
      bonds.QueryOuterClass.QueryAvailableReserveResponse> getAvailableReserveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AvailableReserve",
      requestType = bonds.QueryOuterClass.QueryAvailableReserveRequest.class,
      responseType = bonds.QueryOuterClass.QueryAvailableReserveResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryAvailableReserveRequest,
      bonds.QueryOuterClass.QueryAvailableReserveResponse> getAvailableReserveMethod() {
    io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryAvailableReserveRequest, bonds.QueryOuterClass.QueryAvailableReserveResponse> getAvailableReserveMethod;
    if ((getAvailableReserveMethod = QueryGrpc.getAvailableReserveMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAvailableReserveMethod = QueryGrpc.getAvailableReserveMethod) == null) {
          QueryGrpc.getAvailableReserveMethod = getAvailableReserveMethod =
              io.grpc.MethodDescriptor.<bonds.QueryOuterClass.QueryAvailableReserveRequest, bonds.QueryOuterClass.QueryAvailableReserveResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AvailableReserve"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryAvailableReserveRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryAvailableReserveResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AvailableReserve"))
              .build();
        }
      }
    }
    return getAvailableReserveMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryCustomPriceRequest,
      bonds.QueryOuterClass.QueryCustomPriceResponse> getCustomPriceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CustomPrice",
      requestType = bonds.QueryOuterClass.QueryCustomPriceRequest.class,
      responseType = bonds.QueryOuterClass.QueryCustomPriceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryCustomPriceRequest,
      bonds.QueryOuterClass.QueryCustomPriceResponse> getCustomPriceMethod() {
    io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryCustomPriceRequest, bonds.QueryOuterClass.QueryCustomPriceResponse> getCustomPriceMethod;
    if ((getCustomPriceMethod = QueryGrpc.getCustomPriceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCustomPriceMethod = QueryGrpc.getCustomPriceMethod) == null) {
          QueryGrpc.getCustomPriceMethod = getCustomPriceMethod =
              io.grpc.MethodDescriptor.<bonds.QueryOuterClass.QueryCustomPriceRequest, bonds.QueryOuterClass.QueryCustomPriceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CustomPrice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryCustomPriceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryCustomPriceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CustomPrice"))
              .build();
        }
      }
    }
    return getCustomPriceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryBuyPriceRequest,
      bonds.QueryOuterClass.QueryBuyPriceResponse> getBuyPriceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BuyPrice",
      requestType = bonds.QueryOuterClass.QueryBuyPriceRequest.class,
      responseType = bonds.QueryOuterClass.QueryBuyPriceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryBuyPriceRequest,
      bonds.QueryOuterClass.QueryBuyPriceResponse> getBuyPriceMethod() {
    io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryBuyPriceRequest, bonds.QueryOuterClass.QueryBuyPriceResponse> getBuyPriceMethod;
    if ((getBuyPriceMethod = QueryGrpc.getBuyPriceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBuyPriceMethod = QueryGrpc.getBuyPriceMethod) == null) {
          QueryGrpc.getBuyPriceMethod = getBuyPriceMethod =
              io.grpc.MethodDescriptor.<bonds.QueryOuterClass.QueryBuyPriceRequest, bonds.QueryOuterClass.QueryBuyPriceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BuyPrice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryBuyPriceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryBuyPriceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BuyPrice"))
              .build();
        }
      }
    }
    return getBuyPriceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bonds.QueryOuterClass.QuerySellReturnRequest,
      bonds.QueryOuterClass.QuerySellReturnResponse> getSellReturnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SellReturn",
      requestType = bonds.QueryOuterClass.QuerySellReturnRequest.class,
      responseType = bonds.QueryOuterClass.QuerySellReturnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bonds.QueryOuterClass.QuerySellReturnRequest,
      bonds.QueryOuterClass.QuerySellReturnResponse> getSellReturnMethod() {
    io.grpc.MethodDescriptor<bonds.QueryOuterClass.QuerySellReturnRequest, bonds.QueryOuterClass.QuerySellReturnResponse> getSellReturnMethod;
    if ((getSellReturnMethod = QueryGrpc.getSellReturnMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSellReturnMethod = QueryGrpc.getSellReturnMethod) == null) {
          QueryGrpc.getSellReturnMethod = getSellReturnMethod =
              io.grpc.MethodDescriptor.<bonds.QueryOuterClass.QuerySellReturnRequest, bonds.QueryOuterClass.QuerySellReturnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SellReturn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QuerySellReturnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QuerySellReturnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SellReturn"))
              .build();
        }
      }
    }
    return getSellReturnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bonds.QueryOuterClass.QuerySwapReturnRequest,
      bonds.QueryOuterClass.QuerySwapReturnResponse> getSwapReturnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SwapReturn",
      requestType = bonds.QueryOuterClass.QuerySwapReturnRequest.class,
      responseType = bonds.QueryOuterClass.QuerySwapReturnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bonds.QueryOuterClass.QuerySwapReturnRequest,
      bonds.QueryOuterClass.QuerySwapReturnResponse> getSwapReturnMethod() {
    io.grpc.MethodDescriptor<bonds.QueryOuterClass.QuerySwapReturnRequest, bonds.QueryOuterClass.QuerySwapReturnResponse> getSwapReturnMethod;
    if ((getSwapReturnMethod = QueryGrpc.getSwapReturnMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSwapReturnMethod = QueryGrpc.getSwapReturnMethod) == null) {
          QueryGrpc.getSwapReturnMethod = getSwapReturnMethod =
              io.grpc.MethodDescriptor.<bonds.QueryOuterClass.QuerySwapReturnRequest, bonds.QueryOuterClass.QuerySwapReturnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SwapReturn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QuerySwapReturnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QuerySwapReturnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SwapReturn"))
              .build();
        }
      }
    }
    return getSwapReturnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryAlphaMaximumsRequest,
      bonds.QueryOuterClass.QueryAlphaMaximumsResponse> getAlphaMaximumsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AlphaMaximums",
      requestType = bonds.QueryOuterClass.QueryAlphaMaximumsRequest.class,
      responseType = bonds.QueryOuterClass.QueryAlphaMaximumsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryAlphaMaximumsRequest,
      bonds.QueryOuterClass.QueryAlphaMaximumsResponse> getAlphaMaximumsMethod() {
    io.grpc.MethodDescriptor<bonds.QueryOuterClass.QueryAlphaMaximumsRequest, bonds.QueryOuterClass.QueryAlphaMaximumsResponse> getAlphaMaximumsMethod;
    if ((getAlphaMaximumsMethod = QueryGrpc.getAlphaMaximumsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAlphaMaximumsMethod = QueryGrpc.getAlphaMaximumsMethod) == null) {
          QueryGrpc.getAlphaMaximumsMethod = getAlphaMaximumsMethod =
              io.grpc.MethodDescriptor.<bonds.QueryOuterClass.QueryAlphaMaximumsRequest, bonds.QueryOuterClass.QueryAlphaMaximumsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AlphaMaximums"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryAlphaMaximumsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bonds.QueryOuterClass.QueryAlphaMaximumsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AlphaMaximums"))
              .build();
        }
      }
    }
    return getAlphaMaximumsMethod;
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
     * Bonds returns all existing bonds.
     * </pre>
     */
    public void bonds(bonds.QueryOuterClass.QueryBondsRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryBondsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBondsMethod(), responseObserver);
    }

    /**
     * <pre>
     * BondsDetailed returns a list of all existing bonds with some details about their current state.
     * </pre>
     */
    public void bondsDetailed(bonds.QueryOuterClass.QueryBondsDetailedRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryBondsDetailedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBondsDetailedMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries the paramaters of x/bonds module.
     * </pre>
     */
    public void params(bonds.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Bond queries info of a specific bond.
     * </pre>
     */
    public void bond(bonds.QueryOuterClass.QueryBondRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryBondResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBondMethod(), responseObserver);
    }

    /**
     * <pre>
     * Batch queries info of a specific bond's current batch.
     * </pre>
     */
    public void batch(bonds.QueryOuterClass.QueryBatchRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryBatchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchMethod(), responseObserver);
    }

    /**
     * <pre>
     * LastBatch queries info of a specific bond's last batch.
     * </pre>
     */
    public void lastBatch(bonds.QueryOuterClass.QueryLastBatchRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryLastBatchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLastBatchMethod(), responseObserver);
    }

    /**
     * <pre>
     * CurrentPrice queries the current price/s of a specific bond.
     * </pre>
     */
    public void currentPrice(bonds.QueryOuterClass.QueryCurrentPriceRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryCurrentPriceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCurrentPriceMethod(), responseObserver);
    }

    /**
     * <pre>
     * CurrentReserve queries the current balance/s of the reserve pool for a specific bond.
     * </pre>
     */
    public void currentReserve(bonds.QueryOuterClass.QueryCurrentReserveRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryCurrentReserveResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCurrentReserveMethod(), responseObserver);
    }

    /**
     * <pre>
     * AvailableReserve queries current available balance/s of the reserve pool for a specific bond.
     * </pre>
     */
    public void availableReserve(bonds.QueryOuterClass.QueryAvailableReserveRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryAvailableReserveResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAvailableReserveMethod(), responseObserver);
    }

    /**
     * <pre>
     * CustomPrice queries price/s of a specific bond at a specific supply.
     * </pre>
     */
    public void customPrice(bonds.QueryOuterClass.QueryCustomPriceRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryCustomPriceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCustomPriceMethod(), responseObserver);
    }

    /**
     * <pre>
     * BuyPrice queries price/s of buying an amount of tokens from a specific bond.
     * </pre>
     */
    public void buyPrice(bonds.QueryOuterClass.QueryBuyPriceRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryBuyPriceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBuyPriceMethod(), responseObserver);
    }

    /**
     * <pre>
     * SellReturn queries return/s on selling an amount of tokens of a specific bond.
     * </pre>
     */
    public void sellReturn(bonds.QueryOuterClass.QuerySellReturnRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QuerySellReturnResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSellReturnMethod(), responseObserver);
    }

    /**
     * <pre>
     * SwapReturn queries return/s on swapping an amount of tokens to another token of a specific bond.
     * </pre>
     */
    public void swapReturn(bonds.QueryOuterClass.QuerySwapReturnRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QuerySwapReturnResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwapReturnMethod(), responseObserver);
    }

    /**
     * <pre>
     * AlphaMaximums queries alpha maximums for a specific augmented bonding curve.
     * </pre>
     */
    public void alphaMaximums(bonds.QueryOuterClass.QueryAlphaMaximumsRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryAlphaMaximumsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAlphaMaximumsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getBondsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bonds.QueryOuterClass.QueryBondsRequest,
                bonds.QueryOuterClass.QueryBondsResponse>(
                  this, METHODID_BONDS)))
          .addMethod(
            getBondsDetailedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bonds.QueryOuterClass.QueryBondsDetailedRequest,
                bonds.QueryOuterClass.QueryBondsDetailedResponse>(
                  this, METHODID_BONDS_DETAILED)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bonds.QueryOuterClass.QueryParamsRequest,
                bonds.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getBondMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bonds.QueryOuterClass.QueryBondRequest,
                bonds.QueryOuterClass.QueryBondResponse>(
                  this, METHODID_BOND)))
          .addMethod(
            getBatchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bonds.QueryOuterClass.QueryBatchRequest,
                bonds.QueryOuterClass.QueryBatchResponse>(
                  this, METHODID_BATCH)))
          .addMethod(
            getLastBatchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bonds.QueryOuterClass.QueryLastBatchRequest,
                bonds.QueryOuterClass.QueryLastBatchResponse>(
                  this, METHODID_LAST_BATCH)))
          .addMethod(
            getCurrentPriceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bonds.QueryOuterClass.QueryCurrentPriceRequest,
                bonds.QueryOuterClass.QueryCurrentPriceResponse>(
                  this, METHODID_CURRENT_PRICE)))
          .addMethod(
            getCurrentReserveMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bonds.QueryOuterClass.QueryCurrentReserveRequest,
                bonds.QueryOuterClass.QueryCurrentReserveResponse>(
                  this, METHODID_CURRENT_RESERVE)))
          .addMethod(
            getAvailableReserveMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bonds.QueryOuterClass.QueryAvailableReserveRequest,
                bonds.QueryOuterClass.QueryAvailableReserveResponse>(
                  this, METHODID_AVAILABLE_RESERVE)))
          .addMethod(
            getCustomPriceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bonds.QueryOuterClass.QueryCustomPriceRequest,
                bonds.QueryOuterClass.QueryCustomPriceResponse>(
                  this, METHODID_CUSTOM_PRICE)))
          .addMethod(
            getBuyPriceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bonds.QueryOuterClass.QueryBuyPriceRequest,
                bonds.QueryOuterClass.QueryBuyPriceResponse>(
                  this, METHODID_BUY_PRICE)))
          .addMethod(
            getSellReturnMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bonds.QueryOuterClass.QuerySellReturnRequest,
                bonds.QueryOuterClass.QuerySellReturnResponse>(
                  this, METHODID_SELL_RETURN)))
          .addMethod(
            getSwapReturnMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bonds.QueryOuterClass.QuerySwapReturnRequest,
                bonds.QueryOuterClass.QuerySwapReturnResponse>(
                  this, METHODID_SWAP_RETURN)))
          .addMethod(
            getAlphaMaximumsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bonds.QueryOuterClass.QueryAlphaMaximumsRequest,
                bonds.QueryOuterClass.QueryAlphaMaximumsResponse>(
                  this, METHODID_ALPHA_MAXIMUMS)))
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
     * Bonds returns all existing bonds.
     * </pre>
     */
    public void bonds(bonds.QueryOuterClass.QueryBondsRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryBondsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBondsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BondsDetailed returns a list of all existing bonds with some details about their current state.
     * </pre>
     */
    public void bondsDetailed(bonds.QueryOuterClass.QueryBondsDetailedRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryBondsDetailedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBondsDetailedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries the paramaters of x/bonds module.
     * </pre>
     */
    public void params(bonds.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Bond queries info of a specific bond.
     * </pre>
     */
    public void bond(bonds.QueryOuterClass.QueryBondRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryBondResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBondMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Batch queries info of a specific bond's current batch.
     * </pre>
     */
    public void batch(bonds.QueryOuterClass.QueryBatchRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryBatchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * LastBatch queries info of a specific bond's last batch.
     * </pre>
     */
    public void lastBatch(bonds.QueryOuterClass.QueryLastBatchRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryLastBatchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLastBatchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CurrentPrice queries the current price/s of a specific bond.
     * </pre>
     */
    public void currentPrice(bonds.QueryOuterClass.QueryCurrentPriceRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryCurrentPriceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCurrentPriceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CurrentReserve queries the current balance/s of the reserve pool for a specific bond.
     * </pre>
     */
    public void currentReserve(bonds.QueryOuterClass.QueryCurrentReserveRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryCurrentReserveResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCurrentReserveMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AvailableReserve queries current available balance/s of the reserve pool for a specific bond.
     * </pre>
     */
    public void availableReserve(bonds.QueryOuterClass.QueryAvailableReserveRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryAvailableReserveResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAvailableReserveMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CustomPrice queries price/s of a specific bond at a specific supply.
     * </pre>
     */
    public void customPrice(bonds.QueryOuterClass.QueryCustomPriceRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryCustomPriceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCustomPriceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BuyPrice queries price/s of buying an amount of tokens from a specific bond.
     * </pre>
     */
    public void buyPrice(bonds.QueryOuterClass.QueryBuyPriceRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryBuyPriceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBuyPriceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SellReturn queries return/s on selling an amount of tokens of a specific bond.
     * </pre>
     */
    public void sellReturn(bonds.QueryOuterClass.QuerySellReturnRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QuerySellReturnResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSellReturnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SwapReturn queries return/s on swapping an amount of tokens to another token of a specific bond.
     * </pre>
     */
    public void swapReturn(bonds.QueryOuterClass.QuerySwapReturnRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QuerySwapReturnResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwapReturnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AlphaMaximums queries alpha maximums for a specific augmented bonding curve.
     * </pre>
     */
    public void alphaMaximums(bonds.QueryOuterClass.QueryAlphaMaximumsRequest request,
        io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryAlphaMaximumsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAlphaMaximumsMethod(), getCallOptions()), request, responseObserver);
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
     * Bonds returns all existing bonds.
     * </pre>
     */
    public bonds.QueryOuterClass.QueryBondsResponse bonds(bonds.QueryOuterClass.QueryBondsRequest request) {
      return blockingUnaryCall(
          getChannel(), getBondsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BondsDetailed returns a list of all existing bonds with some details about their current state.
     * </pre>
     */
    public bonds.QueryOuterClass.QueryBondsDetailedResponse bondsDetailed(bonds.QueryOuterClass.QueryBondsDetailedRequest request) {
      return blockingUnaryCall(
          getChannel(), getBondsDetailedMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries the paramaters of x/bonds module.
     * </pre>
     */
    public bonds.QueryOuterClass.QueryParamsResponse params(bonds.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Bond queries info of a specific bond.
     * </pre>
     */
    public bonds.QueryOuterClass.QueryBondResponse bond(bonds.QueryOuterClass.QueryBondRequest request) {
      return blockingUnaryCall(
          getChannel(), getBondMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Batch queries info of a specific bond's current batch.
     * </pre>
     */
    public bonds.QueryOuterClass.QueryBatchResponse batch(bonds.QueryOuterClass.QueryBatchRequest request) {
      return blockingUnaryCall(
          getChannel(), getBatchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * LastBatch queries info of a specific bond's last batch.
     * </pre>
     */
    public bonds.QueryOuterClass.QueryLastBatchResponse lastBatch(bonds.QueryOuterClass.QueryLastBatchRequest request) {
      return blockingUnaryCall(
          getChannel(), getLastBatchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CurrentPrice queries the current price/s of a specific bond.
     * </pre>
     */
    public bonds.QueryOuterClass.QueryCurrentPriceResponse currentPrice(bonds.QueryOuterClass.QueryCurrentPriceRequest request) {
      return blockingUnaryCall(
          getChannel(), getCurrentPriceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CurrentReserve queries the current balance/s of the reserve pool for a specific bond.
     * </pre>
     */
    public bonds.QueryOuterClass.QueryCurrentReserveResponse currentReserve(bonds.QueryOuterClass.QueryCurrentReserveRequest request) {
      return blockingUnaryCall(
          getChannel(), getCurrentReserveMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AvailableReserve queries current available balance/s of the reserve pool for a specific bond.
     * </pre>
     */
    public bonds.QueryOuterClass.QueryAvailableReserveResponse availableReserve(bonds.QueryOuterClass.QueryAvailableReserveRequest request) {
      return blockingUnaryCall(
          getChannel(), getAvailableReserveMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CustomPrice queries price/s of a specific bond at a specific supply.
     * </pre>
     */
    public bonds.QueryOuterClass.QueryCustomPriceResponse customPrice(bonds.QueryOuterClass.QueryCustomPriceRequest request) {
      return blockingUnaryCall(
          getChannel(), getCustomPriceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BuyPrice queries price/s of buying an amount of tokens from a specific bond.
     * </pre>
     */
    public bonds.QueryOuterClass.QueryBuyPriceResponse buyPrice(bonds.QueryOuterClass.QueryBuyPriceRequest request) {
      return blockingUnaryCall(
          getChannel(), getBuyPriceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SellReturn queries return/s on selling an amount of tokens of a specific bond.
     * </pre>
     */
    public bonds.QueryOuterClass.QuerySellReturnResponse sellReturn(bonds.QueryOuterClass.QuerySellReturnRequest request) {
      return blockingUnaryCall(
          getChannel(), getSellReturnMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SwapReturn queries return/s on swapping an amount of tokens to another token of a specific bond.
     * </pre>
     */
    public bonds.QueryOuterClass.QuerySwapReturnResponse swapReturn(bonds.QueryOuterClass.QuerySwapReturnRequest request) {
      return blockingUnaryCall(
          getChannel(), getSwapReturnMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AlphaMaximums queries alpha maximums for a specific augmented bonding curve.
     * </pre>
     */
    public bonds.QueryOuterClass.QueryAlphaMaximumsResponse alphaMaximums(bonds.QueryOuterClass.QueryAlphaMaximumsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAlphaMaximumsMethod(), getCallOptions(), request);
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
     * Bonds returns all existing bonds.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bonds.QueryOuterClass.QueryBondsResponse> bonds(
        bonds.QueryOuterClass.QueryBondsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBondsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BondsDetailed returns a list of all existing bonds with some details about their current state.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bonds.QueryOuterClass.QueryBondsDetailedResponse> bondsDetailed(
        bonds.QueryOuterClass.QueryBondsDetailedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBondsDetailedMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries the paramaters of x/bonds module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bonds.QueryOuterClass.QueryParamsResponse> params(
        bonds.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Bond queries info of a specific bond.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bonds.QueryOuterClass.QueryBondResponse> bond(
        bonds.QueryOuterClass.QueryBondRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBondMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Batch queries info of a specific bond's current batch.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bonds.QueryOuterClass.QueryBatchResponse> batch(
        bonds.QueryOuterClass.QueryBatchRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * LastBatch queries info of a specific bond's last batch.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bonds.QueryOuterClass.QueryLastBatchResponse> lastBatch(
        bonds.QueryOuterClass.QueryLastBatchRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLastBatchMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CurrentPrice queries the current price/s of a specific bond.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bonds.QueryOuterClass.QueryCurrentPriceResponse> currentPrice(
        bonds.QueryOuterClass.QueryCurrentPriceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCurrentPriceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CurrentReserve queries the current balance/s of the reserve pool for a specific bond.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bonds.QueryOuterClass.QueryCurrentReserveResponse> currentReserve(
        bonds.QueryOuterClass.QueryCurrentReserveRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCurrentReserveMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AvailableReserve queries current available balance/s of the reserve pool for a specific bond.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bonds.QueryOuterClass.QueryAvailableReserveResponse> availableReserve(
        bonds.QueryOuterClass.QueryAvailableReserveRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAvailableReserveMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CustomPrice queries price/s of a specific bond at a specific supply.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bonds.QueryOuterClass.QueryCustomPriceResponse> customPrice(
        bonds.QueryOuterClass.QueryCustomPriceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCustomPriceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BuyPrice queries price/s of buying an amount of tokens from a specific bond.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bonds.QueryOuterClass.QueryBuyPriceResponse> buyPrice(
        bonds.QueryOuterClass.QueryBuyPriceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBuyPriceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SellReturn queries return/s on selling an amount of tokens of a specific bond.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bonds.QueryOuterClass.QuerySellReturnResponse> sellReturn(
        bonds.QueryOuterClass.QuerySellReturnRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSellReturnMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SwapReturn queries return/s on swapping an amount of tokens to another token of a specific bond.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bonds.QueryOuterClass.QuerySwapReturnResponse> swapReturn(
        bonds.QueryOuterClass.QuerySwapReturnRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSwapReturnMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AlphaMaximums queries alpha maximums for a specific augmented bonding curve.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bonds.QueryOuterClass.QueryAlphaMaximumsResponse> alphaMaximums(
        bonds.QueryOuterClass.QueryAlphaMaximumsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAlphaMaximumsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BONDS = 0;
  private static final int METHODID_BONDS_DETAILED = 1;
  private static final int METHODID_PARAMS = 2;
  private static final int METHODID_BOND = 3;
  private static final int METHODID_BATCH = 4;
  private static final int METHODID_LAST_BATCH = 5;
  private static final int METHODID_CURRENT_PRICE = 6;
  private static final int METHODID_CURRENT_RESERVE = 7;
  private static final int METHODID_AVAILABLE_RESERVE = 8;
  private static final int METHODID_CUSTOM_PRICE = 9;
  private static final int METHODID_BUY_PRICE = 10;
  private static final int METHODID_SELL_RETURN = 11;
  private static final int METHODID_SWAP_RETURN = 12;
  private static final int METHODID_ALPHA_MAXIMUMS = 13;

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
        case METHODID_BONDS:
          serviceImpl.bonds((bonds.QueryOuterClass.QueryBondsRequest) request,
              (io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryBondsResponse>) responseObserver);
          break;
        case METHODID_BONDS_DETAILED:
          serviceImpl.bondsDetailed((bonds.QueryOuterClass.QueryBondsDetailedRequest) request,
              (io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryBondsDetailedResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((bonds.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_BOND:
          serviceImpl.bond((bonds.QueryOuterClass.QueryBondRequest) request,
              (io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryBondResponse>) responseObserver);
          break;
        case METHODID_BATCH:
          serviceImpl.batch((bonds.QueryOuterClass.QueryBatchRequest) request,
              (io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryBatchResponse>) responseObserver);
          break;
        case METHODID_LAST_BATCH:
          serviceImpl.lastBatch((bonds.QueryOuterClass.QueryLastBatchRequest) request,
              (io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryLastBatchResponse>) responseObserver);
          break;
        case METHODID_CURRENT_PRICE:
          serviceImpl.currentPrice((bonds.QueryOuterClass.QueryCurrentPriceRequest) request,
              (io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryCurrentPriceResponse>) responseObserver);
          break;
        case METHODID_CURRENT_RESERVE:
          serviceImpl.currentReserve((bonds.QueryOuterClass.QueryCurrentReserveRequest) request,
              (io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryCurrentReserveResponse>) responseObserver);
          break;
        case METHODID_AVAILABLE_RESERVE:
          serviceImpl.availableReserve((bonds.QueryOuterClass.QueryAvailableReserveRequest) request,
              (io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryAvailableReserveResponse>) responseObserver);
          break;
        case METHODID_CUSTOM_PRICE:
          serviceImpl.customPrice((bonds.QueryOuterClass.QueryCustomPriceRequest) request,
              (io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryCustomPriceResponse>) responseObserver);
          break;
        case METHODID_BUY_PRICE:
          serviceImpl.buyPrice((bonds.QueryOuterClass.QueryBuyPriceRequest) request,
              (io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryBuyPriceResponse>) responseObserver);
          break;
        case METHODID_SELL_RETURN:
          serviceImpl.sellReturn((bonds.QueryOuterClass.QuerySellReturnRequest) request,
              (io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QuerySellReturnResponse>) responseObserver);
          break;
        case METHODID_SWAP_RETURN:
          serviceImpl.swapReturn((bonds.QueryOuterClass.QuerySwapReturnRequest) request,
              (io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QuerySwapReturnResponse>) responseObserver);
          break;
        case METHODID_ALPHA_MAXIMUMS:
          serviceImpl.alphaMaximums((bonds.QueryOuterClass.QueryAlphaMaximumsRequest) request,
              (io.grpc.stub.StreamObserver<bonds.QueryOuterClass.QueryAlphaMaximumsResponse>) responseObserver);
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
      return bonds.QueryOuterClass.getDescriptor();
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
              .addMethod(getBondsMethod())
              .addMethod(getBondsDetailedMethod())
              .addMethod(getParamsMethod())
              .addMethod(getBondMethod())
              .addMethod(getBatchMethod())
              .addMethod(getLastBatchMethod())
              .addMethod(getCurrentPriceMethod())
              .addMethod(getCurrentReserveMethod())
              .addMethod(getAvailableReserveMethod())
              .addMethod(getCustomPriceMethod())
              .addMethod(getBuyPriceMethod())
              .addMethod(getSellReturnMethod())
              .addMethod(getSwapReturnMethod())
              .addMethod(getAlphaMaximumsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
