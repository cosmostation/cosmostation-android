package com.injective.exchange.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the exchange Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: injective/exchange/v1beta1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "injective.exchange.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgDeposit,
      com.injective.exchange.v1beta1.TxProto.MsgDepositResponse> getDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposit",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgDeposit.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgDeposit,
      com.injective.exchange.v1beta1.TxProto.MsgDepositResponse> getDepositMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgDeposit, com.injective.exchange.v1beta1.TxProto.MsgDepositResponse> getDepositMethod;
    if ((getDepositMethod = MsgGrpc.getDepositMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDepositMethod = MsgGrpc.getDepositMethod) == null) {
          MsgGrpc.getDepositMethod = getDepositMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgDeposit, com.injective.exchange.v1beta1.TxProto.MsgDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgDeposit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Deposit"))
              .build();
        }
      }
    }
    return getDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgWithdraw,
      com.injective.exchange.v1beta1.TxProto.MsgWithdrawResponse> getWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Withdraw",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgWithdraw.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgWithdrawResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgWithdraw,
      com.injective.exchange.v1beta1.TxProto.MsgWithdrawResponse> getWithdrawMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgWithdraw, com.injective.exchange.v1beta1.TxProto.MsgWithdrawResponse> getWithdrawMethod;
    if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
          MsgGrpc.getWithdrawMethod = getWithdrawMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgWithdraw, com.injective.exchange.v1beta1.TxProto.MsgWithdrawResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Withdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgWithdraw.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgWithdrawResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Withdraw"))
              .build();
        }
      }
    }
    return getWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunch,
      com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunchResponse> getInstantSpotMarketLaunchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InstantSpotMarketLaunch",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunch.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunch,
      com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunchResponse> getInstantSpotMarketLaunchMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunch, com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunchResponse> getInstantSpotMarketLaunchMethod;
    if ((getInstantSpotMarketLaunchMethod = MsgGrpc.getInstantSpotMarketLaunchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getInstantSpotMarketLaunchMethod = MsgGrpc.getInstantSpotMarketLaunchMethod) == null) {
          MsgGrpc.getInstantSpotMarketLaunchMethod = getInstantSpotMarketLaunchMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunch, com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InstantSpotMarketLaunch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("InstantSpotMarketLaunch"))
              .build();
        }
      }
    }
    return getInstantSpotMarketLaunchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunch,
      com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunchResponse> getInstantPerpetualMarketLaunchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InstantPerpetualMarketLaunch",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunch.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunch,
      com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunchResponse> getInstantPerpetualMarketLaunchMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunch, com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunchResponse> getInstantPerpetualMarketLaunchMethod;
    if ((getInstantPerpetualMarketLaunchMethod = MsgGrpc.getInstantPerpetualMarketLaunchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getInstantPerpetualMarketLaunchMethod = MsgGrpc.getInstantPerpetualMarketLaunchMethod) == null) {
          MsgGrpc.getInstantPerpetualMarketLaunchMethod = getInstantPerpetualMarketLaunchMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunch, com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InstantPerpetualMarketLaunch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("InstantPerpetualMarketLaunch"))
              .build();
        }
      }
    }
    return getInstantPerpetualMarketLaunchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunch,
      com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunchResponse> getInstantExpiryFuturesMarketLaunchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InstantExpiryFuturesMarketLaunch",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunch.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunch,
      com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunchResponse> getInstantExpiryFuturesMarketLaunchMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunch, com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunchResponse> getInstantExpiryFuturesMarketLaunchMethod;
    if ((getInstantExpiryFuturesMarketLaunchMethod = MsgGrpc.getInstantExpiryFuturesMarketLaunchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getInstantExpiryFuturesMarketLaunchMethod = MsgGrpc.getInstantExpiryFuturesMarketLaunchMethod) == null) {
          MsgGrpc.getInstantExpiryFuturesMarketLaunchMethod = getInstantExpiryFuturesMarketLaunchMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunch, com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InstantExpiryFuturesMarketLaunch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("InstantExpiryFuturesMarketLaunch"))
              .build();
        }
      }
    }
    return getInstantExpiryFuturesMarketLaunchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrderResponse> getCreateSpotLimitOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateSpotLimitOrder",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrder.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrderResponse> getCreateSpotLimitOrderMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrder, com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrderResponse> getCreateSpotLimitOrderMethod;
    if ((getCreateSpotLimitOrderMethod = MsgGrpc.getCreateSpotLimitOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateSpotLimitOrderMethod = MsgGrpc.getCreateSpotLimitOrderMethod) == null) {
          MsgGrpc.getCreateSpotLimitOrderMethod = getCreateSpotLimitOrderMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrder, com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateSpotLimitOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateSpotLimitOrder"))
              .build();
        }
      }
    }
    return getCreateSpotLimitOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrders,
      com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrdersResponse> getBatchCreateSpotLimitOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchCreateSpotLimitOrders",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrders.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrders,
      com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrdersResponse> getBatchCreateSpotLimitOrdersMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrders, com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrdersResponse> getBatchCreateSpotLimitOrdersMethod;
    if ((getBatchCreateSpotLimitOrdersMethod = MsgGrpc.getBatchCreateSpotLimitOrdersMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBatchCreateSpotLimitOrdersMethod = MsgGrpc.getBatchCreateSpotLimitOrdersMethod) == null) {
          MsgGrpc.getBatchCreateSpotLimitOrdersMethod = getBatchCreateSpotLimitOrdersMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrders, com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchCreateSpotLimitOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrders.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BatchCreateSpotLimitOrders"))
              .build();
        }
      }
    }
    return getBatchCreateSpotLimitOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrderResponse> getCreateSpotMarketOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateSpotMarketOrder",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrder.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrderResponse> getCreateSpotMarketOrderMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrder, com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrderResponse> getCreateSpotMarketOrderMethod;
    if ((getCreateSpotMarketOrderMethod = MsgGrpc.getCreateSpotMarketOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateSpotMarketOrderMethod = MsgGrpc.getCreateSpotMarketOrderMethod) == null) {
          MsgGrpc.getCreateSpotMarketOrderMethod = getCreateSpotMarketOrderMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrder, com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateSpotMarketOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateSpotMarketOrder"))
              .build();
        }
      }
    }
    return getCreateSpotMarketOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrderResponse> getCancelSpotOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelSpotOrder",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrder.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrderResponse> getCancelSpotOrderMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrder, com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrderResponse> getCancelSpotOrderMethod;
    if ((getCancelSpotOrderMethod = MsgGrpc.getCancelSpotOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelSpotOrderMethod = MsgGrpc.getCancelSpotOrderMethod) == null) {
          MsgGrpc.getCancelSpotOrderMethod = getCancelSpotOrderMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrder, com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelSpotOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelSpotOrder"))
              .build();
        }
      }
    }
    return getCancelSpotOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrders,
      com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrdersResponse> getBatchCancelSpotOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchCancelSpotOrders",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrders.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrders,
      com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrdersResponse> getBatchCancelSpotOrdersMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrders, com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrdersResponse> getBatchCancelSpotOrdersMethod;
    if ((getBatchCancelSpotOrdersMethod = MsgGrpc.getBatchCancelSpotOrdersMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBatchCancelSpotOrdersMethod = MsgGrpc.getBatchCancelSpotOrdersMethod) == null) {
          MsgGrpc.getBatchCancelSpotOrdersMethod = getBatchCancelSpotOrdersMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrders, com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchCancelSpotOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrders.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BatchCancelSpotOrders"))
              .build();
        }
      }
    }
    return getBatchCancelSpotOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrders,
      com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrdersResponse> getBatchUpdateOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchUpdateOrders",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrders.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrders,
      com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrdersResponse> getBatchUpdateOrdersMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrders, com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrdersResponse> getBatchUpdateOrdersMethod;
    if ((getBatchUpdateOrdersMethod = MsgGrpc.getBatchUpdateOrdersMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBatchUpdateOrdersMethod = MsgGrpc.getBatchUpdateOrdersMethod) == null) {
          MsgGrpc.getBatchUpdateOrdersMethod = getBatchUpdateOrdersMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrders, com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchUpdateOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrders.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BatchUpdateOrders"))
              .build();
        }
      }
    }
    return getBatchUpdateOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContract,
      com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContractResponse> getPrivilegedExecuteContractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PrivilegedExecuteContract",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContract.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContractResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContract,
      com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContractResponse> getPrivilegedExecuteContractMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContract, com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContractResponse> getPrivilegedExecuteContractMethod;
    if ((getPrivilegedExecuteContractMethod = MsgGrpc.getPrivilegedExecuteContractMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getPrivilegedExecuteContractMethod = MsgGrpc.getPrivilegedExecuteContractMethod) == null) {
          MsgGrpc.getPrivilegedExecuteContractMethod = getPrivilegedExecuteContractMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContract, com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContractResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PrivilegedExecuteContract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContractResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("PrivilegedExecuteContract"))
              .build();
        }
      }
    }
    return getPrivilegedExecuteContractMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrderResponse> getCreateDerivativeLimitOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateDerivativeLimitOrder",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrder.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrderResponse> getCreateDerivativeLimitOrderMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrder, com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrderResponse> getCreateDerivativeLimitOrderMethod;
    if ((getCreateDerivativeLimitOrderMethod = MsgGrpc.getCreateDerivativeLimitOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateDerivativeLimitOrderMethod = MsgGrpc.getCreateDerivativeLimitOrderMethod) == null) {
          MsgGrpc.getCreateDerivativeLimitOrderMethod = getCreateDerivativeLimitOrderMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrder, com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateDerivativeLimitOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateDerivativeLimitOrder"))
              .build();
        }
      }
    }
    return getCreateDerivativeLimitOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrders,
      com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrdersResponse> getBatchCreateDerivativeLimitOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchCreateDerivativeLimitOrders",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrders.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrders,
      com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrdersResponse> getBatchCreateDerivativeLimitOrdersMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrders, com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrdersResponse> getBatchCreateDerivativeLimitOrdersMethod;
    if ((getBatchCreateDerivativeLimitOrdersMethod = MsgGrpc.getBatchCreateDerivativeLimitOrdersMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBatchCreateDerivativeLimitOrdersMethod = MsgGrpc.getBatchCreateDerivativeLimitOrdersMethod) == null) {
          MsgGrpc.getBatchCreateDerivativeLimitOrdersMethod = getBatchCreateDerivativeLimitOrdersMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrders, com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchCreateDerivativeLimitOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrders.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BatchCreateDerivativeLimitOrders"))
              .build();
        }
      }
    }
    return getBatchCreateDerivativeLimitOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrderResponse> getCreateDerivativeMarketOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateDerivativeMarketOrder",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrder.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrderResponse> getCreateDerivativeMarketOrderMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrder, com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrderResponse> getCreateDerivativeMarketOrderMethod;
    if ((getCreateDerivativeMarketOrderMethod = MsgGrpc.getCreateDerivativeMarketOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateDerivativeMarketOrderMethod = MsgGrpc.getCreateDerivativeMarketOrderMethod) == null) {
          MsgGrpc.getCreateDerivativeMarketOrderMethod = getCreateDerivativeMarketOrderMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrder, com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateDerivativeMarketOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateDerivativeMarketOrder"))
              .build();
        }
      }
    }
    return getCreateDerivativeMarketOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrderResponse> getCancelDerivativeOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelDerivativeOrder",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrder.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrderResponse> getCancelDerivativeOrderMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrder, com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrderResponse> getCancelDerivativeOrderMethod;
    if ((getCancelDerivativeOrderMethod = MsgGrpc.getCancelDerivativeOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelDerivativeOrderMethod = MsgGrpc.getCancelDerivativeOrderMethod) == null) {
          MsgGrpc.getCancelDerivativeOrderMethod = getCancelDerivativeOrderMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrder, com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelDerivativeOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelDerivativeOrder"))
              .build();
        }
      }
    }
    return getCancelDerivativeOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrders,
      com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrdersResponse> getBatchCancelDerivativeOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchCancelDerivativeOrders",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrders.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrders,
      com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrdersResponse> getBatchCancelDerivativeOrdersMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrders, com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrdersResponse> getBatchCancelDerivativeOrdersMethod;
    if ((getBatchCancelDerivativeOrdersMethod = MsgGrpc.getBatchCancelDerivativeOrdersMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBatchCancelDerivativeOrdersMethod = MsgGrpc.getBatchCancelDerivativeOrdersMethod) == null) {
          MsgGrpc.getBatchCancelDerivativeOrdersMethod = getBatchCancelDerivativeOrdersMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrders, com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchCancelDerivativeOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrders.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BatchCancelDerivativeOrders"))
              .build();
        }
      }
    }
    return getBatchCancelDerivativeOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunch,
      com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunchResponse> getInstantBinaryOptionsMarketLaunchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InstantBinaryOptionsMarketLaunch",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunch.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunch,
      com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunchResponse> getInstantBinaryOptionsMarketLaunchMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunch, com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunchResponse> getInstantBinaryOptionsMarketLaunchMethod;
    if ((getInstantBinaryOptionsMarketLaunchMethod = MsgGrpc.getInstantBinaryOptionsMarketLaunchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getInstantBinaryOptionsMarketLaunchMethod = MsgGrpc.getInstantBinaryOptionsMarketLaunchMethod) == null) {
          MsgGrpc.getInstantBinaryOptionsMarketLaunchMethod = getInstantBinaryOptionsMarketLaunchMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunch, com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InstantBinaryOptionsMarketLaunch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("InstantBinaryOptionsMarketLaunch"))
              .build();
        }
      }
    }
    return getInstantBinaryOptionsMarketLaunchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrderResponse> getCreateBinaryOptionsLimitOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateBinaryOptionsLimitOrder",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrder.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrderResponse> getCreateBinaryOptionsLimitOrderMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrder, com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrderResponse> getCreateBinaryOptionsLimitOrderMethod;
    if ((getCreateBinaryOptionsLimitOrderMethod = MsgGrpc.getCreateBinaryOptionsLimitOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateBinaryOptionsLimitOrderMethod = MsgGrpc.getCreateBinaryOptionsLimitOrderMethod) == null) {
          MsgGrpc.getCreateBinaryOptionsLimitOrderMethod = getCreateBinaryOptionsLimitOrderMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrder, com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateBinaryOptionsLimitOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateBinaryOptionsLimitOrder"))
              .build();
        }
      }
    }
    return getCreateBinaryOptionsLimitOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrderResponse> getCreateBinaryOptionsMarketOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateBinaryOptionsMarketOrder",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrder.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrderResponse> getCreateBinaryOptionsMarketOrderMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrder, com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrderResponse> getCreateBinaryOptionsMarketOrderMethod;
    if ((getCreateBinaryOptionsMarketOrderMethod = MsgGrpc.getCreateBinaryOptionsMarketOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateBinaryOptionsMarketOrderMethod = MsgGrpc.getCreateBinaryOptionsMarketOrderMethod) == null) {
          MsgGrpc.getCreateBinaryOptionsMarketOrderMethod = getCreateBinaryOptionsMarketOrderMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrder, com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateBinaryOptionsMarketOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateBinaryOptionsMarketOrder"))
              .build();
        }
      }
    }
    return getCreateBinaryOptionsMarketOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrderResponse> getCancelBinaryOptionsOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelBinaryOptionsOrder",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrder.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrder,
      com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrderResponse> getCancelBinaryOptionsOrderMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrder, com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrderResponse> getCancelBinaryOptionsOrderMethod;
    if ((getCancelBinaryOptionsOrderMethod = MsgGrpc.getCancelBinaryOptionsOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelBinaryOptionsOrderMethod = MsgGrpc.getCancelBinaryOptionsOrderMethod) == null) {
          MsgGrpc.getCancelBinaryOptionsOrderMethod = getCancelBinaryOptionsOrderMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrder, com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelBinaryOptionsOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelBinaryOptionsOrder"))
              .build();
        }
      }
    }
    return getCancelBinaryOptionsOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrders,
      com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrdersResponse> getBatchCancelBinaryOptionsOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchCancelBinaryOptionsOrders",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrders.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrders,
      com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrdersResponse> getBatchCancelBinaryOptionsOrdersMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrders, com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrdersResponse> getBatchCancelBinaryOptionsOrdersMethod;
    if ((getBatchCancelBinaryOptionsOrdersMethod = MsgGrpc.getBatchCancelBinaryOptionsOrdersMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBatchCancelBinaryOptionsOrdersMethod = MsgGrpc.getBatchCancelBinaryOptionsOrdersMethod) == null) {
          MsgGrpc.getBatchCancelBinaryOptionsOrdersMethod = getBatchCancelBinaryOptionsOrdersMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrders, com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchCancelBinaryOptionsOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrders.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BatchCancelBinaryOptionsOrders"))
              .build();
        }
      }
    }
    return getBatchCancelBinaryOptionsOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransfer,
      com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransferResponse> getSubaccountTransferMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubaccountTransfer",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransfer.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransferResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransfer,
      com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransferResponse> getSubaccountTransferMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransfer, com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransferResponse> getSubaccountTransferMethod;
    if ((getSubaccountTransferMethod = MsgGrpc.getSubaccountTransferMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSubaccountTransferMethod = MsgGrpc.getSubaccountTransferMethod) == null) {
          MsgGrpc.getSubaccountTransferMethod = getSubaccountTransferMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransfer, com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransferResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubaccountTransfer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransfer.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransferResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SubaccountTransfer"))
              .build();
        }
      }
    }
    return getSubaccountTransferMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgExternalTransfer,
      com.injective.exchange.v1beta1.TxProto.MsgExternalTransferResponse> getExternalTransferMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExternalTransfer",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgExternalTransfer.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgExternalTransferResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgExternalTransfer,
      com.injective.exchange.v1beta1.TxProto.MsgExternalTransferResponse> getExternalTransferMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgExternalTransfer, com.injective.exchange.v1beta1.TxProto.MsgExternalTransferResponse> getExternalTransferMethod;
    if ((getExternalTransferMethod = MsgGrpc.getExternalTransferMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getExternalTransferMethod = MsgGrpc.getExternalTransferMethod) == null) {
          MsgGrpc.getExternalTransferMethod = getExternalTransferMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgExternalTransfer, com.injective.exchange.v1beta1.TxProto.MsgExternalTransferResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExternalTransfer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgExternalTransfer.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgExternalTransferResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ExternalTransfer"))
              .build();
        }
      }
    }
    return getExternalTransferMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgLiquidatePosition,
      com.injective.exchange.v1beta1.TxProto.MsgLiquidatePositionResponse> getLiquidatePositionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiquidatePosition",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgLiquidatePosition.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgLiquidatePositionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgLiquidatePosition,
      com.injective.exchange.v1beta1.TxProto.MsgLiquidatePositionResponse> getLiquidatePositionMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgLiquidatePosition, com.injective.exchange.v1beta1.TxProto.MsgLiquidatePositionResponse> getLiquidatePositionMethod;
    if ((getLiquidatePositionMethod = MsgGrpc.getLiquidatePositionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLiquidatePositionMethod = MsgGrpc.getLiquidatePositionMethod) == null) {
          MsgGrpc.getLiquidatePositionMethod = getLiquidatePositionMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgLiquidatePosition, com.injective.exchange.v1beta1.TxProto.MsgLiquidatePositionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LiquidatePosition"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgLiquidatePosition.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgLiquidatePositionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("LiquidatePosition"))
              .build();
        }
      }
    }
    return getLiquidatePositionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMargin,
      com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMarginResponse> getIncreasePositionMarginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IncreasePositionMargin",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMargin.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMarginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMargin,
      com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMarginResponse> getIncreasePositionMarginMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMargin, com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMarginResponse> getIncreasePositionMarginMethod;
    if ((getIncreasePositionMarginMethod = MsgGrpc.getIncreasePositionMarginMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getIncreasePositionMarginMethod = MsgGrpc.getIncreasePositionMarginMethod) == null) {
          MsgGrpc.getIncreasePositionMarginMethod = getIncreasePositionMarginMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMargin, com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMarginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IncreasePositionMargin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMargin.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMarginResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("IncreasePositionMargin"))
              .build();
        }
      }
    }
    return getIncreasePositionMarginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOut,
      com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOutResponse> getRewardsOptOutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RewardsOptOut",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOut.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOut,
      com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOutResponse> getRewardsOptOutMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOut, com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOutResponse> getRewardsOptOutMethod;
    if ((getRewardsOptOutMethod = MsgGrpc.getRewardsOptOutMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRewardsOptOutMethod = MsgGrpc.getRewardsOptOutMethod) == null) {
          MsgGrpc.getRewardsOptOutMethod = getRewardsOptOutMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOut, com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RewardsOptOut"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOut.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOutResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RewardsOptOut"))
              .build();
        }
      }
    }
    return getRewardsOptOutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarket,
      com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarketResponse> getAdminUpdateBinaryOptionsMarketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AdminUpdateBinaryOptionsMarket",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarket.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarket,
      com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarketResponse> getAdminUpdateBinaryOptionsMarketMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarket, com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarketResponse> getAdminUpdateBinaryOptionsMarketMethod;
    if ((getAdminUpdateBinaryOptionsMarketMethod = MsgGrpc.getAdminUpdateBinaryOptionsMarketMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAdminUpdateBinaryOptionsMarketMethod = MsgGrpc.getAdminUpdateBinaryOptionsMarketMethod) == null) {
          MsgGrpc.getAdminUpdateBinaryOptionsMarketMethod = getAdminUpdateBinaryOptionsMarketMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarket, com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AdminUpdateBinaryOptionsMarket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarket.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AdminUpdateBinaryOptionsMarket"))
              .build();
        }
      }
    }
    return getAdminUpdateBinaryOptionsMarketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFunds,
      com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFundsResponse> getReclaimLockedFundsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReclaimLockedFunds",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFunds.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFundsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFunds,
      com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFundsResponse> getReclaimLockedFundsMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFunds, com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFundsResponse> getReclaimLockedFundsMethod;
    if ((getReclaimLockedFundsMethod = MsgGrpc.getReclaimLockedFundsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getReclaimLockedFundsMethod = MsgGrpc.getReclaimLockedFundsMethod) == null) {
          MsgGrpc.getReclaimLockedFundsMethod = getReclaimLockedFundsMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFunds, com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFundsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReclaimLockedFunds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFunds.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFundsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ReclaimLockedFunds"))
              .build();
        }
      }
    }
    return getReclaimLockedFundsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgUpdateParams,
      com.injective.exchange.v1beta1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateParams",
      requestType = com.injective.exchange.v1beta1.TxProto.MsgUpdateParams.class,
      responseType = com.injective.exchange.v1beta1.TxProto.MsgUpdateParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgUpdateParams,
      com.injective.exchange.v1beta1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod() {
    io.grpc.MethodDescriptor<com.injective.exchange.v1beta1.TxProto.MsgUpdateParams, com.injective.exchange.v1beta1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;
    if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
          MsgGrpc.getUpdateParamsMethod = getUpdateParamsMethod =
              io.grpc.MethodDescriptor.<com.injective.exchange.v1beta1.TxProto.MsgUpdateParams, com.injective.exchange.v1beta1.TxProto.MsgUpdateParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgUpdateParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.exchange.v1beta1.TxProto.MsgUpdateParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateParams"))
              .build();
        }
      }
    }
    return getUpdateParamsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MsgStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgStub>() {
        @java.lang.Override
        public MsgStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgStub(channel, callOptions);
        }
      };
    return MsgStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MsgBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgBlockingStub>() {
        @java.lang.Override
        public MsgBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgBlockingStub(channel, callOptions);
        }
      };
    return MsgBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MsgFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgFutureStub>() {
        @java.lang.Override
        public MsgFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgFutureStub(channel, callOptions);
        }
      };
    return MsgFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Msg defines the exchange Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Deposit defines a method for transferring coins from the sender's bank
     * balance into the subaccount's exchange deposits
     * </pre>
     */
    default void deposit(com.injective.exchange.v1beta1.TxProto.MsgDeposit request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgDepositResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDepositMethod(), responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing coins from a subaccount's
     * deposits to the user's bank balance
     * </pre>
     */
    default void withdraw(com.injective.exchange.v1beta1.TxProto.MsgWithdraw request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgWithdrawResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWithdrawMethod(), responseObserver);
    }

    /**
     * <pre>
     * InstantSpotMarketLaunch defines method for creating a spot market by paying
     * listing fee without governance
     * </pre>
     */
    default void instantSpotMarketLaunch(com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunch request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInstantSpotMarketLaunchMethod(), responseObserver);
    }

    /**
     * <pre>
     * InstantPerpetualMarketLaunch defines a method for creating a new perpetual
     * futures market by paying listing fee without governance
     * </pre>
     */
    default void instantPerpetualMarketLaunch(com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunch request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInstantPerpetualMarketLaunchMethod(), responseObserver);
    }

    /**
     * <pre>
     * InstantExpiryFuturesMarketLaunch defines a method for creating a new expiry
     * futures market by paying listing fee without governance
     * </pre>
     */
    default void instantExpiryFuturesMarketLaunch(com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunch request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInstantExpiryFuturesMarketLaunchMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateSpotLimitOrder defines a method for creating a new spot limit order.
     * </pre>
     */
    default void createSpotLimitOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateSpotLimitOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * BatchCreateSpotLimitOrder defines a method for creating a new batch of spot
     * limit orders.
     * </pre>
     */
    default void batchCreateSpotLimitOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrders request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrdersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchCreateSpotLimitOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateSpotMarketOrder defines a method for creating a new spot market
     * order.
     * </pre>
     */
    default void createSpotMarketOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateSpotMarketOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * MsgCancelSpotOrder defines a method for cancelling a spot order.
     * </pre>
     */
    default void cancelSpotOrder(com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCancelSpotOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * BatchCancelSpotOrders defines a method for cancelling a batch of spot
     * orders in a given market.
     * </pre>
     */
    default void batchCancelSpotOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrders request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrdersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchCancelSpotOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * BatchUpdateOrders defines a method for updating a batch of orders.
     * </pre>
     */
    default void batchUpdateOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrders request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrdersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchUpdateOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * PrivilegedExecuteContract defines a method for executing a Cosmwasm
     * contract from the exchange module with privileged capabilities.
     * </pre>
     */
    default void privilegedExecuteContract(com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContract request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContractResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPrivilegedExecuteContractMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateDerivativeLimitOrder defines a method for creating a new derivative
     * limit order.
     * </pre>
     */
    default void createDerivativeLimitOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateDerivativeLimitOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * BatchCreateDerivativeLimitOrders defines a method for creating a new batch
     * of derivative limit orders.
     * </pre>
     */
    default void batchCreateDerivativeLimitOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrders request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrdersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchCreateDerivativeLimitOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * MsgCreateDerivativeLimitOrder defines a method for creating a new
     * derivative market order.
     * </pre>
     */
    default void createDerivativeMarketOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateDerivativeMarketOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * MsgCancelDerivativeOrder defines a method for cancelling a derivative
     * order.
     * </pre>
     */
    default void cancelDerivativeOrder(com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCancelDerivativeOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * MsgBatchCancelDerivativeOrders defines a method for cancelling a batch of
     * derivative limit orders.
     * </pre>
     */
    default void batchCancelDerivativeOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrders request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrdersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchCancelDerivativeOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * InstantBinaryOptionsMarketLaunch defines method for creating a binary
     * options market by paying listing fee without governance
     * </pre>
     */
    default void instantBinaryOptionsMarketLaunch(com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunch request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInstantBinaryOptionsMarketLaunchMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateBinaryOptionsLimitOrder defines a method for creating a new binary
     * options limit order.
     * </pre>
     */
    default void createBinaryOptionsLimitOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateBinaryOptionsLimitOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateBinaryOptionsMarketOrder defines a method for creating a new binary
     * options market order.
     * </pre>
     */
    default void createBinaryOptionsMarketOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateBinaryOptionsMarketOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * MsgCancelBinaryOptionsOrder defines a method for cancelling a binary
     * options order.
     * </pre>
     */
    default void cancelBinaryOptionsOrder(com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCancelBinaryOptionsOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * BatchCancelBinaryOptionsOrders defines a method for cancelling a batch of
     * binary options limit orders.
     * </pre>
     */
    default void batchCancelBinaryOptionsOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrders request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrdersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchCancelBinaryOptionsOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * SubaccountTransfer defines a method for transfer between subaccounts
     * </pre>
     */
    default void subaccountTransfer(com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransfer request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransferResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubaccountTransferMethod(), responseObserver);
    }

    /**
     * <pre>
     * ExternalTransfer defines a method for transfer between external accounts
     * </pre>
     */
    default void externalTransfer(com.injective.exchange.v1beta1.TxProto.MsgExternalTransfer request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgExternalTransferResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExternalTransferMethod(), responseObserver);
    }

    /**
     * <pre>
     * LiquidatePosition defines a method for liquidating a position
     * </pre>
     */
    default void liquidatePosition(com.injective.exchange.v1beta1.TxProto.MsgLiquidatePosition request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgLiquidatePositionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLiquidatePositionMethod(), responseObserver);
    }

    /**
     * <pre>
     * IncreasePositionMargin defines a method for increasing margin of a position
     * </pre>
     */
    default void increasePositionMargin(com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMargin request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMarginResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getIncreasePositionMarginMethod(), responseObserver);
    }

    /**
     * <pre>
     * RewardsOptOut defines a method for opting out of rewards
     * </pre>
     */
    default void rewardsOptOut(com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOut request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOutResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRewardsOptOutMethod(), responseObserver);
    }

    /**
     * <pre>
     * AdminUpdateBinaryOptionsMarket defines method for updating a binary options
     * market by admin
     * </pre>
     */
    default void adminUpdateBinaryOptionsMarket(com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarket request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarketResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAdminUpdateBinaryOptionsMarketMethod(), responseObserver);
    }

    /**
     */
    default void reclaimLockedFunds(com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFunds request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFundsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReclaimLockedFundsMethod(), responseObserver);
    }

    /**
     */
    default void updateParams(com.injective.exchange.v1beta1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateParamsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the exchange Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MsgGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the exchange Msg service.
   * </pre>
   */
  public static final class MsgStub
      extends io.grpc.stub.AbstractAsyncStub<MsgStub> {
    private MsgStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgStub(channel, callOptions);
    }

    /**
     * <pre>
     * Deposit defines a method for transferring coins from the sender's bank
     * balance into the subaccount's exchange deposits
     * </pre>
     */
    public void deposit(com.injective.exchange.v1beta1.TxProto.MsgDeposit request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgDepositResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing coins from a subaccount's
     * deposits to the user's bank balance
     * </pre>
     */
    public void withdraw(com.injective.exchange.v1beta1.TxProto.MsgWithdraw request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgWithdrawResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * InstantSpotMarketLaunch defines method for creating a spot market by paying
     * listing fee without governance
     * </pre>
     */
    public void instantSpotMarketLaunch(com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunch request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInstantSpotMarketLaunchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * InstantPerpetualMarketLaunch defines a method for creating a new perpetual
     * futures market by paying listing fee without governance
     * </pre>
     */
    public void instantPerpetualMarketLaunch(com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunch request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInstantPerpetualMarketLaunchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * InstantExpiryFuturesMarketLaunch defines a method for creating a new expiry
     * futures market by paying listing fee without governance
     * </pre>
     */
    public void instantExpiryFuturesMarketLaunch(com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunch request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInstantExpiryFuturesMarketLaunchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateSpotLimitOrder defines a method for creating a new spot limit order.
     * </pre>
     */
    public void createSpotLimitOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateSpotLimitOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BatchCreateSpotLimitOrder defines a method for creating a new batch of spot
     * limit orders.
     * </pre>
     */
    public void batchCreateSpotLimitOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrders request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrdersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchCreateSpotLimitOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateSpotMarketOrder defines a method for creating a new spot market
     * order.
     * </pre>
     */
    public void createSpotMarketOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateSpotMarketOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MsgCancelSpotOrder defines a method for cancelling a spot order.
     * </pre>
     */
    public void cancelSpotOrder(com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCancelSpotOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BatchCancelSpotOrders defines a method for cancelling a batch of spot
     * orders in a given market.
     * </pre>
     */
    public void batchCancelSpotOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrders request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrdersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchCancelSpotOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BatchUpdateOrders defines a method for updating a batch of orders.
     * </pre>
     */
    public void batchUpdateOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrders request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrdersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchUpdateOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PrivilegedExecuteContract defines a method for executing a Cosmwasm
     * contract from the exchange module with privileged capabilities.
     * </pre>
     */
    public void privilegedExecuteContract(com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContract request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContractResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPrivilegedExecuteContractMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateDerivativeLimitOrder defines a method for creating a new derivative
     * limit order.
     * </pre>
     */
    public void createDerivativeLimitOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateDerivativeLimitOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BatchCreateDerivativeLimitOrders defines a method for creating a new batch
     * of derivative limit orders.
     * </pre>
     */
    public void batchCreateDerivativeLimitOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrders request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrdersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchCreateDerivativeLimitOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MsgCreateDerivativeLimitOrder defines a method for creating a new
     * derivative market order.
     * </pre>
     */
    public void createDerivativeMarketOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateDerivativeMarketOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MsgCancelDerivativeOrder defines a method for cancelling a derivative
     * order.
     * </pre>
     */
    public void cancelDerivativeOrder(com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCancelDerivativeOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MsgBatchCancelDerivativeOrders defines a method for cancelling a batch of
     * derivative limit orders.
     * </pre>
     */
    public void batchCancelDerivativeOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrders request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrdersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchCancelDerivativeOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * InstantBinaryOptionsMarketLaunch defines method for creating a binary
     * options market by paying listing fee without governance
     * </pre>
     */
    public void instantBinaryOptionsMarketLaunch(com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunch request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInstantBinaryOptionsMarketLaunchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateBinaryOptionsLimitOrder defines a method for creating a new binary
     * options limit order.
     * </pre>
     */
    public void createBinaryOptionsLimitOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateBinaryOptionsLimitOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateBinaryOptionsMarketOrder defines a method for creating a new binary
     * options market order.
     * </pre>
     */
    public void createBinaryOptionsMarketOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateBinaryOptionsMarketOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MsgCancelBinaryOptionsOrder defines a method for cancelling a binary
     * options order.
     * </pre>
     */
    public void cancelBinaryOptionsOrder(com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrder request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCancelBinaryOptionsOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BatchCancelBinaryOptionsOrders defines a method for cancelling a batch of
     * binary options limit orders.
     * </pre>
     */
    public void batchCancelBinaryOptionsOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrders request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrdersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchCancelBinaryOptionsOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SubaccountTransfer defines a method for transfer between subaccounts
     * </pre>
     */
    public void subaccountTransfer(com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransfer request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransferResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSubaccountTransferMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ExternalTransfer defines a method for transfer between external accounts
     * </pre>
     */
    public void externalTransfer(com.injective.exchange.v1beta1.TxProto.MsgExternalTransfer request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgExternalTransferResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExternalTransferMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * LiquidatePosition defines a method for liquidating a position
     * </pre>
     */
    public void liquidatePosition(com.injective.exchange.v1beta1.TxProto.MsgLiquidatePosition request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgLiquidatePositionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLiquidatePositionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * IncreasePositionMargin defines a method for increasing margin of a position
     * </pre>
     */
    public void increasePositionMargin(com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMargin request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMarginResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getIncreasePositionMarginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RewardsOptOut defines a method for opting out of rewards
     * </pre>
     */
    public void rewardsOptOut(com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOut request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOutResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRewardsOptOutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AdminUpdateBinaryOptionsMarket defines method for updating a binary options
     * market by admin
     * </pre>
     */
    public void adminUpdateBinaryOptionsMarket(com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarket request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarketResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAdminUpdateBinaryOptionsMarketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reclaimLockedFunds(com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFunds request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFundsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReclaimLockedFundsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateParams(com.injective.exchange.v1beta1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the exchange Msg service.
   * </pre>
   */
  public static final class MsgBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MsgBlockingStub> {
    private MsgBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Deposit defines a method for transferring coins from the sender's bank
     * balance into the subaccount's exchange deposits
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgDepositResponse deposit(com.injective.exchange.v1beta1.TxProto.MsgDeposit request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDepositMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing coins from a subaccount's
     * deposits to the user's bank balance
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgWithdrawResponse withdraw(com.injective.exchange.v1beta1.TxProto.MsgWithdraw request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getWithdrawMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * InstantSpotMarketLaunch defines method for creating a spot market by paying
     * listing fee without governance
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunchResponse instantSpotMarketLaunch(com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunch request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInstantSpotMarketLaunchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * InstantPerpetualMarketLaunch defines a method for creating a new perpetual
     * futures market by paying listing fee without governance
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunchResponse instantPerpetualMarketLaunch(com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunch request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInstantPerpetualMarketLaunchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * InstantExpiryFuturesMarketLaunch defines a method for creating a new expiry
     * futures market by paying listing fee without governance
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunchResponse instantExpiryFuturesMarketLaunch(com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunch request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInstantExpiryFuturesMarketLaunchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateSpotLimitOrder defines a method for creating a new spot limit order.
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrderResponse createSpotLimitOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrder request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateSpotLimitOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BatchCreateSpotLimitOrder defines a method for creating a new batch of spot
     * limit orders.
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrdersResponse batchCreateSpotLimitOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrders request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchCreateSpotLimitOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateSpotMarketOrder defines a method for creating a new spot market
     * order.
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrderResponse createSpotMarketOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrder request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateSpotMarketOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MsgCancelSpotOrder defines a method for cancelling a spot order.
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrderResponse cancelSpotOrder(com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrder request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCancelSpotOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BatchCancelSpotOrders defines a method for cancelling a batch of spot
     * orders in a given market.
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrdersResponse batchCancelSpotOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrders request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchCancelSpotOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BatchUpdateOrders defines a method for updating a batch of orders.
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrdersResponse batchUpdateOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrders request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchUpdateOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PrivilegedExecuteContract defines a method for executing a Cosmwasm
     * contract from the exchange module with privileged capabilities.
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContractResponse privilegedExecuteContract(com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContract request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPrivilegedExecuteContractMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateDerivativeLimitOrder defines a method for creating a new derivative
     * limit order.
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrderResponse createDerivativeLimitOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrder request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateDerivativeLimitOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BatchCreateDerivativeLimitOrders defines a method for creating a new batch
     * of derivative limit orders.
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrdersResponse batchCreateDerivativeLimitOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrders request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchCreateDerivativeLimitOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MsgCreateDerivativeLimitOrder defines a method for creating a new
     * derivative market order.
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrderResponse createDerivativeMarketOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrder request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateDerivativeMarketOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MsgCancelDerivativeOrder defines a method for cancelling a derivative
     * order.
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrderResponse cancelDerivativeOrder(com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrder request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCancelDerivativeOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MsgBatchCancelDerivativeOrders defines a method for cancelling a batch of
     * derivative limit orders.
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrdersResponse batchCancelDerivativeOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrders request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchCancelDerivativeOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * InstantBinaryOptionsMarketLaunch defines method for creating a binary
     * options market by paying listing fee without governance
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunchResponse instantBinaryOptionsMarketLaunch(com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunch request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInstantBinaryOptionsMarketLaunchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateBinaryOptionsLimitOrder defines a method for creating a new binary
     * options limit order.
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrderResponse createBinaryOptionsLimitOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrder request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateBinaryOptionsLimitOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateBinaryOptionsMarketOrder defines a method for creating a new binary
     * options market order.
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrderResponse createBinaryOptionsMarketOrder(com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrder request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateBinaryOptionsMarketOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MsgCancelBinaryOptionsOrder defines a method for cancelling a binary
     * options order.
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrderResponse cancelBinaryOptionsOrder(com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrder request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCancelBinaryOptionsOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BatchCancelBinaryOptionsOrders defines a method for cancelling a batch of
     * binary options limit orders.
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrdersResponse batchCancelBinaryOptionsOrders(com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrders request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchCancelBinaryOptionsOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SubaccountTransfer defines a method for transfer between subaccounts
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransferResponse subaccountTransfer(com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransfer request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSubaccountTransferMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ExternalTransfer defines a method for transfer between external accounts
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgExternalTransferResponse externalTransfer(com.injective.exchange.v1beta1.TxProto.MsgExternalTransfer request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExternalTransferMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * LiquidatePosition defines a method for liquidating a position
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgLiquidatePositionResponse liquidatePosition(com.injective.exchange.v1beta1.TxProto.MsgLiquidatePosition request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLiquidatePositionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * IncreasePositionMargin defines a method for increasing margin of a position
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMarginResponse increasePositionMargin(com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMargin request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getIncreasePositionMarginMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RewardsOptOut defines a method for opting out of rewards
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOutResponse rewardsOptOut(com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOut request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRewardsOptOutMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AdminUpdateBinaryOptionsMarket defines method for updating a binary options
     * market by admin
     * </pre>
     */
    public com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarketResponse adminUpdateBinaryOptionsMarket(com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarket request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAdminUpdateBinaryOptionsMarketMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFundsResponse reclaimLockedFunds(com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFunds request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReclaimLockedFundsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.exchange.v1beta1.TxProto.MsgUpdateParamsResponse updateParams(com.injective.exchange.v1beta1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the exchange Msg service.
   * </pre>
   */
  public static final class MsgFutureStub
      extends io.grpc.stub.AbstractFutureStub<MsgFutureStub> {
    private MsgFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Deposit defines a method for transferring coins from the sender's bank
     * balance into the subaccount's exchange deposits
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgDepositResponse> deposit(
        com.injective.exchange.v1beta1.TxProto.MsgDeposit request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing coins from a subaccount's
     * deposits to the user's bank balance
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgWithdrawResponse> withdraw(
        com.injective.exchange.v1beta1.TxProto.MsgWithdraw request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * InstantSpotMarketLaunch defines method for creating a spot market by paying
     * listing fee without governance
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunchResponse> instantSpotMarketLaunch(
        com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunch request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInstantSpotMarketLaunchMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * InstantPerpetualMarketLaunch defines a method for creating a new perpetual
     * futures market by paying listing fee without governance
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunchResponse> instantPerpetualMarketLaunch(
        com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunch request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInstantPerpetualMarketLaunchMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * InstantExpiryFuturesMarketLaunch defines a method for creating a new expiry
     * futures market by paying listing fee without governance
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunchResponse> instantExpiryFuturesMarketLaunch(
        com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunch request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInstantExpiryFuturesMarketLaunchMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateSpotLimitOrder defines a method for creating a new spot limit order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrderResponse> createSpotLimitOrder(
        com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrder request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateSpotLimitOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BatchCreateSpotLimitOrder defines a method for creating a new batch of spot
     * limit orders.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrdersResponse> batchCreateSpotLimitOrders(
        com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrders request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchCreateSpotLimitOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateSpotMarketOrder defines a method for creating a new spot market
     * order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrderResponse> createSpotMarketOrder(
        com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrder request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateSpotMarketOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MsgCancelSpotOrder defines a method for cancelling a spot order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrderResponse> cancelSpotOrder(
        com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrder request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCancelSpotOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BatchCancelSpotOrders defines a method for cancelling a batch of spot
     * orders in a given market.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrdersResponse> batchCancelSpotOrders(
        com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrders request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchCancelSpotOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BatchUpdateOrders defines a method for updating a batch of orders.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrdersResponse> batchUpdateOrders(
        com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrders request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchUpdateOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PrivilegedExecuteContract defines a method for executing a Cosmwasm
     * contract from the exchange module with privileged capabilities.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContractResponse> privilegedExecuteContract(
        com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContract request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPrivilegedExecuteContractMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateDerivativeLimitOrder defines a method for creating a new derivative
     * limit order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrderResponse> createDerivativeLimitOrder(
        com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrder request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateDerivativeLimitOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BatchCreateDerivativeLimitOrders defines a method for creating a new batch
     * of derivative limit orders.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrdersResponse> batchCreateDerivativeLimitOrders(
        com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrders request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchCreateDerivativeLimitOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MsgCreateDerivativeLimitOrder defines a method for creating a new
     * derivative market order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrderResponse> createDerivativeMarketOrder(
        com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrder request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateDerivativeMarketOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MsgCancelDerivativeOrder defines a method for cancelling a derivative
     * order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrderResponse> cancelDerivativeOrder(
        com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrder request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCancelDerivativeOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MsgBatchCancelDerivativeOrders defines a method for cancelling a batch of
     * derivative limit orders.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrdersResponse> batchCancelDerivativeOrders(
        com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrders request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchCancelDerivativeOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * InstantBinaryOptionsMarketLaunch defines method for creating a binary
     * options market by paying listing fee without governance
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunchResponse> instantBinaryOptionsMarketLaunch(
        com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunch request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInstantBinaryOptionsMarketLaunchMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateBinaryOptionsLimitOrder defines a method for creating a new binary
     * options limit order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrderResponse> createBinaryOptionsLimitOrder(
        com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrder request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateBinaryOptionsLimitOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateBinaryOptionsMarketOrder defines a method for creating a new binary
     * options market order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrderResponse> createBinaryOptionsMarketOrder(
        com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrder request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateBinaryOptionsMarketOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MsgCancelBinaryOptionsOrder defines a method for cancelling a binary
     * options order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrderResponse> cancelBinaryOptionsOrder(
        com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrder request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCancelBinaryOptionsOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BatchCancelBinaryOptionsOrders defines a method for cancelling a batch of
     * binary options limit orders.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrdersResponse> batchCancelBinaryOptionsOrders(
        com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrders request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchCancelBinaryOptionsOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SubaccountTransfer defines a method for transfer between subaccounts
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransferResponse> subaccountTransfer(
        com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransfer request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSubaccountTransferMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ExternalTransfer defines a method for transfer between external accounts
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgExternalTransferResponse> externalTransfer(
        com.injective.exchange.v1beta1.TxProto.MsgExternalTransfer request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExternalTransferMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * LiquidatePosition defines a method for liquidating a position
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgLiquidatePositionResponse> liquidatePosition(
        com.injective.exchange.v1beta1.TxProto.MsgLiquidatePosition request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLiquidatePositionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * IncreasePositionMargin defines a method for increasing margin of a position
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMarginResponse> increasePositionMargin(
        com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMargin request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getIncreasePositionMarginMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RewardsOptOut defines a method for opting out of rewards
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOutResponse> rewardsOptOut(
        com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOut request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRewardsOptOutMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AdminUpdateBinaryOptionsMarket defines method for updating a binary options
     * market by admin
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarketResponse> adminUpdateBinaryOptionsMarket(
        com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarket request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAdminUpdateBinaryOptionsMarketMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFundsResponse> reclaimLockedFunds(
        com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFunds request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReclaimLockedFundsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.exchange.v1beta1.TxProto.MsgUpdateParamsResponse> updateParams(
        com.injective.exchange.v1beta1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DEPOSIT = 0;
  private static final int METHODID_WITHDRAW = 1;
  private static final int METHODID_INSTANT_SPOT_MARKET_LAUNCH = 2;
  private static final int METHODID_INSTANT_PERPETUAL_MARKET_LAUNCH = 3;
  private static final int METHODID_INSTANT_EXPIRY_FUTURES_MARKET_LAUNCH = 4;
  private static final int METHODID_CREATE_SPOT_LIMIT_ORDER = 5;
  private static final int METHODID_BATCH_CREATE_SPOT_LIMIT_ORDERS = 6;
  private static final int METHODID_CREATE_SPOT_MARKET_ORDER = 7;
  private static final int METHODID_CANCEL_SPOT_ORDER = 8;
  private static final int METHODID_BATCH_CANCEL_SPOT_ORDERS = 9;
  private static final int METHODID_BATCH_UPDATE_ORDERS = 10;
  private static final int METHODID_PRIVILEGED_EXECUTE_CONTRACT = 11;
  private static final int METHODID_CREATE_DERIVATIVE_LIMIT_ORDER = 12;
  private static final int METHODID_BATCH_CREATE_DERIVATIVE_LIMIT_ORDERS = 13;
  private static final int METHODID_CREATE_DERIVATIVE_MARKET_ORDER = 14;
  private static final int METHODID_CANCEL_DERIVATIVE_ORDER = 15;
  private static final int METHODID_BATCH_CANCEL_DERIVATIVE_ORDERS = 16;
  private static final int METHODID_INSTANT_BINARY_OPTIONS_MARKET_LAUNCH = 17;
  private static final int METHODID_CREATE_BINARY_OPTIONS_LIMIT_ORDER = 18;
  private static final int METHODID_CREATE_BINARY_OPTIONS_MARKET_ORDER = 19;
  private static final int METHODID_CANCEL_BINARY_OPTIONS_ORDER = 20;
  private static final int METHODID_BATCH_CANCEL_BINARY_OPTIONS_ORDERS = 21;
  private static final int METHODID_SUBACCOUNT_TRANSFER = 22;
  private static final int METHODID_EXTERNAL_TRANSFER = 23;
  private static final int METHODID_LIQUIDATE_POSITION = 24;
  private static final int METHODID_INCREASE_POSITION_MARGIN = 25;
  private static final int METHODID_REWARDS_OPT_OUT = 26;
  private static final int METHODID_ADMIN_UPDATE_BINARY_OPTIONS_MARKET = 27;
  private static final int METHODID_RECLAIM_LOCKED_FUNDS = 28;
  private static final int METHODID_UPDATE_PARAMS = 29;

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
        case METHODID_DEPOSIT:
          serviceImpl.deposit((com.injective.exchange.v1beta1.TxProto.MsgDeposit) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgDepositResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW:
          serviceImpl.withdraw((com.injective.exchange.v1beta1.TxProto.MsgWithdraw) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgWithdrawResponse>) responseObserver);
          break;
        case METHODID_INSTANT_SPOT_MARKET_LAUNCH:
          serviceImpl.instantSpotMarketLaunch((com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunch) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunchResponse>) responseObserver);
          break;
        case METHODID_INSTANT_PERPETUAL_MARKET_LAUNCH:
          serviceImpl.instantPerpetualMarketLaunch((com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunch) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunchResponse>) responseObserver);
          break;
        case METHODID_INSTANT_EXPIRY_FUTURES_MARKET_LAUNCH:
          serviceImpl.instantExpiryFuturesMarketLaunch((com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunch) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunchResponse>) responseObserver);
          break;
        case METHODID_CREATE_SPOT_LIMIT_ORDER:
          serviceImpl.createSpotLimitOrder((com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrder) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrderResponse>) responseObserver);
          break;
        case METHODID_BATCH_CREATE_SPOT_LIMIT_ORDERS:
          serviceImpl.batchCreateSpotLimitOrders((com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrders) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrdersResponse>) responseObserver);
          break;
        case METHODID_CREATE_SPOT_MARKET_ORDER:
          serviceImpl.createSpotMarketOrder((com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrder) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrderResponse>) responseObserver);
          break;
        case METHODID_CANCEL_SPOT_ORDER:
          serviceImpl.cancelSpotOrder((com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrder) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrderResponse>) responseObserver);
          break;
        case METHODID_BATCH_CANCEL_SPOT_ORDERS:
          serviceImpl.batchCancelSpotOrders((com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrders) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrdersResponse>) responseObserver);
          break;
        case METHODID_BATCH_UPDATE_ORDERS:
          serviceImpl.batchUpdateOrders((com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrders) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrdersResponse>) responseObserver);
          break;
        case METHODID_PRIVILEGED_EXECUTE_CONTRACT:
          serviceImpl.privilegedExecuteContract((com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContract) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContractResponse>) responseObserver);
          break;
        case METHODID_CREATE_DERIVATIVE_LIMIT_ORDER:
          serviceImpl.createDerivativeLimitOrder((com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrder) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrderResponse>) responseObserver);
          break;
        case METHODID_BATCH_CREATE_DERIVATIVE_LIMIT_ORDERS:
          serviceImpl.batchCreateDerivativeLimitOrders((com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrders) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrdersResponse>) responseObserver);
          break;
        case METHODID_CREATE_DERIVATIVE_MARKET_ORDER:
          serviceImpl.createDerivativeMarketOrder((com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrder) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrderResponse>) responseObserver);
          break;
        case METHODID_CANCEL_DERIVATIVE_ORDER:
          serviceImpl.cancelDerivativeOrder((com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrder) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrderResponse>) responseObserver);
          break;
        case METHODID_BATCH_CANCEL_DERIVATIVE_ORDERS:
          serviceImpl.batchCancelDerivativeOrders((com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrders) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrdersResponse>) responseObserver);
          break;
        case METHODID_INSTANT_BINARY_OPTIONS_MARKET_LAUNCH:
          serviceImpl.instantBinaryOptionsMarketLaunch((com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunch) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunchResponse>) responseObserver);
          break;
        case METHODID_CREATE_BINARY_OPTIONS_LIMIT_ORDER:
          serviceImpl.createBinaryOptionsLimitOrder((com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrder) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrderResponse>) responseObserver);
          break;
        case METHODID_CREATE_BINARY_OPTIONS_MARKET_ORDER:
          serviceImpl.createBinaryOptionsMarketOrder((com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrder) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrderResponse>) responseObserver);
          break;
        case METHODID_CANCEL_BINARY_OPTIONS_ORDER:
          serviceImpl.cancelBinaryOptionsOrder((com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrder) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrderResponse>) responseObserver);
          break;
        case METHODID_BATCH_CANCEL_BINARY_OPTIONS_ORDERS:
          serviceImpl.batchCancelBinaryOptionsOrders((com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrders) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrdersResponse>) responseObserver);
          break;
        case METHODID_SUBACCOUNT_TRANSFER:
          serviceImpl.subaccountTransfer((com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransfer) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransferResponse>) responseObserver);
          break;
        case METHODID_EXTERNAL_TRANSFER:
          serviceImpl.externalTransfer((com.injective.exchange.v1beta1.TxProto.MsgExternalTransfer) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgExternalTransferResponse>) responseObserver);
          break;
        case METHODID_LIQUIDATE_POSITION:
          serviceImpl.liquidatePosition((com.injective.exchange.v1beta1.TxProto.MsgLiquidatePosition) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgLiquidatePositionResponse>) responseObserver);
          break;
        case METHODID_INCREASE_POSITION_MARGIN:
          serviceImpl.increasePositionMargin((com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMargin) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMarginResponse>) responseObserver);
          break;
        case METHODID_REWARDS_OPT_OUT:
          serviceImpl.rewardsOptOut((com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOut) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOutResponse>) responseObserver);
          break;
        case METHODID_ADMIN_UPDATE_BINARY_OPTIONS_MARKET:
          serviceImpl.adminUpdateBinaryOptionsMarket((com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarket) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarketResponse>) responseObserver);
          break;
        case METHODID_RECLAIM_LOCKED_FUNDS:
          serviceImpl.reclaimLockedFunds((com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFunds) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFundsResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PARAMS:
          serviceImpl.updateParams((com.injective.exchange.v1beta1.TxProto.MsgUpdateParams) request,
              (io.grpc.stub.StreamObserver<com.injective.exchange.v1beta1.TxProto.MsgUpdateParamsResponse>) responseObserver);
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
          getDepositMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgDeposit,
              com.injective.exchange.v1beta1.TxProto.MsgDepositResponse>(
                service, METHODID_DEPOSIT)))
        .addMethod(
          getWithdrawMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgWithdraw,
              com.injective.exchange.v1beta1.TxProto.MsgWithdrawResponse>(
                service, METHODID_WITHDRAW)))
        .addMethod(
          getInstantSpotMarketLaunchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunch,
              com.injective.exchange.v1beta1.TxProto.MsgInstantSpotMarketLaunchResponse>(
                service, METHODID_INSTANT_SPOT_MARKET_LAUNCH)))
        .addMethod(
          getInstantPerpetualMarketLaunchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunch,
              com.injective.exchange.v1beta1.TxProto.MsgInstantPerpetualMarketLaunchResponse>(
                service, METHODID_INSTANT_PERPETUAL_MARKET_LAUNCH)))
        .addMethod(
          getInstantExpiryFuturesMarketLaunchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunch,
              com.injective.exchange.v1beta1.TxProto.MsgInstantExpiryFuturesMarketLaunchResponse>(
                service, METHODID_INSTANT_EXPIRY_FUTURES_MARKET_LAUNCH)))
        .addMethod(
          getCreateSpotLimitOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrder,
              com.injective.exchange.v1beta1.TxProto.MsgCreateSpotLimitOrderResponse>(
                service, METHODID_CREATE_SPOT_LIMIT_ORDER)))
        .addMethod(
          getBatchCreateSpotLimitOrdersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrders,
              com.injective.exchange.v1beta1.TxProto.MsgBatchCreateSpotLimitOrdersResponse>(
                service, METHODID_BATCH_CREATE_SPOT_LIMIT_ORDERS)))
        .addMethod(
          getCreateSpotMarketOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrder,
              com.injective.exchange.v1beta1.TxProto.MsgCreateSpotMarketOrderResponse>(
                service, METHODID_CREATE_SPOT_MARKET_ORDER)))
        .addMethod(
          getCancelSpotOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrder,
              com.injective.exchange.v1beta1.TxProto.MsgCancelSpotOrderResponse>(
                service, METHODID_CANCEL_SPOT_ORDER)))
        .addMethod(
          getBatchCancelSpotOrdersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrders,
              com.injective.exchange.v1beta1.TxProto.MsgBatchCancelSpotOrdersResponse>(
                service, METHODID_BATCH_CANCEL_SPOT_ORDERS)))
        .addMethod(
          getBatchUpdateOrdersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrders,
              com.injective.exchange.v1beta1.TxProto.MsgBatchUpdateOrdersResponse>(
                service, METHODID_BATCH_UPDATE_ORDERS)))
        .addMethod(
          getPrivilegedExecuteContractMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContract,
              com.injective.exchange.v1beta1.TxProto.MsgPrivilegedExecuteContractResponse>(
                service, METHODID_PRIVILEGED_EXECUTE_CONTRACT)))
        .addMethod(
          getCreateDerivativeLimitOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrder,
              com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeLimitOrderResponse>(
                service, METHODID_CREATE_DERIVATIVE_LIMIT_ORDER)))
        .addMethod(
          getBatchCreateDerivativeLimitOrdersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrders,
              com.injective.exchange.v1beta1.TxProto.MsgBatchCreateDerivativeLimitOrdersResponse>(
                service, METHODID_BATCH_CREATE_DERIVATIVE_LIMIT_ORDERS)))
        .addMethod(
          getCreateDerivativeMarketOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrder,
              com.injective.exchange.v1beta1.TxProto.MsgCreateDerivativeMarketOrderResponse>(
                service, METHODID_CREATE_DERIVATIVE_MARKET_ORDER)))
        .addMethod(
          getCancelDerivativeOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrder,
              com.injective.exchange.v1beta1.TxProto.MsgCancelDerivativeOrderResponse>(
                service, METHODID_CANCEL_DERIVATIVE_ORDER)))
        .addMethod(
          getBatchCancelDerivativeOrdersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrders,
              com.injective.exchange.v1beta1.TxProto.MsgBatchCancelDerivativeOrdersResponse>(
                service, METHODID_BATCH_CANCEL_DERIVATIVE_ORDERS)))
        .addMethod(
          getInstantBinaryOptionsMarketLaunchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunch,
              com.injective.exchange.v1beta1.TxProto.MsgInstantBinaryOptionsMarketLaunchResponse>(
                service, METHODID_INSTANT_BINARY_OPTIONS_MARKET_LAUNCH)))
        .addMethod(
          getCreateBinaryOptionsLimitOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrder,
              com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsLimitOrderResponse>(
                service, METHODID_CREATE_BINARY_OPTIONS_LIMIT_ORDER)))
        .addMethod(
          getCreateBinaryOptionsMarketOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrder,
              com.injective.exchange.v1beta1.TxProto.MsgCreateBinaryOptionsMarketOrderResponse>(
                service, METHODID_CREATE_BINARY_OPTIONS_MARKET_ORDER)))
        .addMethod(
          getCancelBinaryOptionsOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrder,
              com.injective.exchange.v1beta1.TxProto.MsgCancelBinaryOptionsOrderResponse>(
                service, METHODID_CANCEL_BINARY_OPTIONS_ORDER)))
        .addMethod(
          getBatchCancelBinaryOptionsOrdersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrders,
              com.injective.exchange.v1beta1.TxProto.MsgBatchCancelBinaryOptionsOrdersResponse>(
                service, METHODID_BATCH_CANCEL_BINARY_OPTIONS_ORDERS)))
        .addMethod(
          getSubaccountTransferMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransfer,
              com.injective.exchange.v1beta1.TxProto.MsgSubaccountTransferResponse>(
                service, METHODID_SUBACCOUNT_TRANSFER)))
        .addMethod(
          getExternalTransferMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgExternalTransfer,
              com.injective.exchange.v1beta1.TxProto.MsgExternalTransferResponse>(
                service, METHODID_EXTERNAL_TRANSFER)))
        .addMethod(
          getLiquidatePositionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgLiquidatePosition,
              com.injective.exchange.v1beta1.TxProto.MsgLiquidatePositionResponse>(
                service, METHODID_LIQUIDATE_POSITION)))
        .addMethod(
          getIncreasePositionMarginMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMargin,
              com.injective.exchange.v1beta1.TxProto.MsgIncreasePositionMarginResponse>(
                service, METHODID_INCREASE_POSITION_MARGIN)))
        .addMethod(
          getRewardsOptOutMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOut,
              com.injective.exchange.v1beta1.TxProto.MsgRewardsOptOutResponse>(
                service, METHODID_REWARDS_OPT_OUT)))
        .addMethod(
          getAdminUpdateBinaryOptionsMarketMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarket,
              com.injective.exchange.v1beta1.TxProto.MsgAdminUpdateBinaryOptionsMarketResponse>(
                service, METHODID_ADMIN_UPDATE_BINARY_OPTIONS_MARKET)))
        .addMethod(
          getReclaimLockedFundsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFunds,
              com.injective.exchange.v1beta1.TxProto.MsgReclaimLockedFundsResponse>(
                service, METHODID_RECLAIM_LOCKED_FUNDS)))
        .addMethod(
          getUpdateParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.exchange.v1beta1.TxProto.MsgUpdateParams,
              com.injective.exchange.v1beta1.TxProto.MsgUpdateParamsResponse>(
                service, METHODID_UPDATE_PARAMS)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.injective.exchange.v1beta1.TxProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Msg");
    }
  }

  private static final class MsgFileDescriptorSupplier
      extends MsgBaseDescriptorSupplier {
    MsgFileDescriptorSupplier() {}
  }

  private static final class MsgMethodDescriptorSupplier
      extends MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MsgMethodDescriptorSupplier(String methodName) {
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
      synchronized (MsgGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MsgFileDescriptorSupplier())
              .addMethod(getDepositMethod())
              .addMethod(getWithdrawMethod())
              .addMethod(getInstantSpotMarketLaunchMethod())
              .addMethod(getInstantPerpetualMarketLaunchMethod())
              .addMethod(getInstantExpiryFuturesMarketLaunchMethod())
              .addMethod(getCreateSpotLimitOrderMethod())
              .addMethod(getBatchCreateSpotLimitOrdersMethod())
              .addMethod(getCreateSpotMarketOrderMethod())
              .addMethod(getCancelSpotOrderMethod())
              .addMethod(getBatchCancelSpotOrdersMethod())
              .addMethod(getBatchUpdateOrdersMethod())
              .addMethod(getPrivilegedExecuteContractMethod())
              .addMethod(getCreateDerivativeLimitOrderMethod())
              .addMethod(getBatchCreateDerivativeLimitOrdersMethod())
              .addMethod(getCreateDerivativeMarketOrderMethod())
              .addMethod(getCancelDerivativeOrderMethod())
              .addMethod(getBatchCancelDerivativeOrdersMethod())
              .addMethod(getInstantBinaryOptionsMarketLaunchMethod())
              .addMethod(getCreateBinaryOptionsLimitOrderMethod())
              .addMethod(getCreateBinaryOptionsMarketOrderMethod())
              .addMethod(getCancelBinaryOptionsOrderMethod())
              .addMethod(getBatchCancelBinaryOptionsOrdersMethod())
              .addMethod(getSubaccountTransferMethod())
              .addMethod(getExternalTransferMethod())
              .addMethod(getLiquidatePositionMethod())
              .addMethod(getIncreasePositionMarginMethod())
              .addMethod(getRewardsOptOutMethod())
              .addMethod(getAdminUpdateBinaryOptionsMarketMethod())
              .addMethod(getReclaimLockedFundsMethod())
              .addMethod(getUpdateParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
