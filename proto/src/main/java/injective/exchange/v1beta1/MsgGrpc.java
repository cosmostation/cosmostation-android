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
 * Msg defines the staking Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: injective/exchange/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "injective.exchange.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgDeposit,
      injective.exchange.v1beta1.Tx.MsgDepositResponse> getDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposit",
      requestType = injective.exchange.v1beta1.Tx.MsgDeposit.class,
      responseType = injective.exchange.v1beta1.Tx.MsgDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgDeposit,
      injective.exchange.v1beta1.Tx.MsgDepositResponse> getDepositMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgDeposit, injective.exchange.v1beta1.Tx.MsgDepositResponse> getDepositMethod;
    if ((getDepositMethod = MsgGrpc.getDepositMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDepositMethod = MsgGrpc.getDepositMethod) == null) {
          MsgGrpc.getDepositMethod = getDepositMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgDeposit, injective.exchange.v1beta1.Tx.MsgDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgDeposit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Deposit"))
              .build();
        }
      }
    }
    return getDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgWithdraw,
      injective.exchange.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Withdraw",
      requestType = injective.exchange.v1beta1.Tx.MsgWithdraw.class,
      responseType = injective.exchange.v1beta1.Tx.MsgWithdrawResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgWithdraw,
      injective.exchange.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgWithdraw, injective.exchange.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod;
    if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
          MsgGrpc.getWithdrawMethod = getWithdrawMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgWithdraw, injective.exchange.v1beta1.Tx.MsgWithdrawResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Withdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgWithdraw.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgWithdrawResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Withdraw"))
              .build();
        }
      }
    }
    return getWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunch,
      injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunchResponse> getInstantSpotMarketLaunchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InstantSpotMarketLaunch",
      requestType = injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunch.class,
      responseType = injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunch,
      injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunchResponse> getInstantSpotMarketLaunchMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunch, injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunchResponse> getInstantSpotMarketLaunchMethod;
    if ((getInstantSpotMarketLaunchMethod = MsgGrpc.getInstantSpotMarketLaunchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getInstantSpotMarketLaunchMethod = MsgGrpc.getInstantSpotMarketLaunchMethod) == null) {
          MsgGrpc.getInstantSpotMarketLaunchMethod = getInstantSpotMarketLaunchMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunch, injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InstantSpotMarketLaunch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("InstantSpotMarketLaunch"))
              .build();
        }
      }
    }
    return getInstantSpotMarketLaunchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunch,
      injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunchResponse> getInstantPerpetualMarketLaunchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InstantPerpetualMarketLaunch",
      requestType = injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunch.class,
      responseType = injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunch,
      injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunchResponse> getInstantPerpetualMarketLaunchMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunch, injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunchResponse> getInstantPerpetualMarketLaunchMethod;
    if ((getInstantPerpetualMarketLaunchMethod = MsgGrpc.getInstantPerpetualMarketLaunchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getInstantPerpetualMarketLaunchMethod = MsgGrpc.getInstantPerpetualMarketLaunchMethod) == null) {
          MsgGrpc.getInstantPerpetualMarketLaunchMethod = getInstantPerpetualMarketLaunchMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunch, injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InstantPerpetualMarketLaunch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("InstantPerpetualMarketLaunch"))
              .build();
        }
      }
    }
    return getInstantPerpetualMarketLaunchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunch,
      injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunchResponse> getInstantExpiryFuturesMarketLaunchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InstantExpiryFuturesMarketLaunch",
      requestType = injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunch.class,
      responseType = injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunch,
      injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunchResponse> getInstantExpiryFuturesMarketLaunchMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunch, injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunchResponse> getInstantExpiryFuturesMarketLaunchMethod;
    if ((getInstantExpiryFuturesMarketLaunchMethod = MsgGrpc.getInstantExpiryFuturesMarketLaunchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getInstantExpiryFuturesMarketLaunchMethod = MsgGrpc.getInstantExpiryFuturesMarketLaunchMethod) == null) {
          MsgGrpc.getInstantExpiryFuturesMarketLaunchMethod = getInstantExpiryFuturesMarketLaunchMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunch, injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InstantExpiryFuturesMarketLaunch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("InstantExpiryFuturesMarketLaunch"))
              .build();
        }
      }
    }
    return getInstantExpiryFuturesMarketLaunchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrder,
      injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrderResponse> getCreateSpotLimitOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateSpotLimitOrder",
      requestType = injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrder.class,
      responseType = injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrder,
      injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrderResponse> getCreateSpotLimitOrderMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrder, injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrderResponse> getCreateSpotLimitOrderMethod;
    if ((getCreateSpotLimitOrderMethod = MsgGrpc.getCreateSpotLimitOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateSpotLimitOrderMethod = MsgGrpc.getCreateSpotLimitOrderMethod) == null) {
          MsgGrpc.getCreateSpotLimitOrderMethod = getCreateSpotLimitOrderMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrder, injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateSpotLimitOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateSpotLimitOrder"))
              .build();
        }
      }
    }
    return getCreateSpotLimitOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrders,
      injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrdersResponse> getBatchCreateSpotLimitOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchCreateSpotLimitOrders",
      requestType = injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrders.class,
      responseType = injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrders,
      injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrdersResponse> getBatchCreateSpotLimitOrdersMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrders, injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrdersResponse> getBatchCreateSpotLimitOrdersMethod;
    if ((getBatchCreateSpotLimitOrdersMethod = MsgGrpc.getBatchCreateSpotLimitOrdersMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBatchCreateSpotLimitOrdersMethod = MsgGrpc.getBatchCreateSpotLimitOrdersMethod) == null) {
          MsgGrpc.getBatchCreateSpotLimitOrdersMethod = getBatchCreateSpotLimitOrdersMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrders, injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchCreateSpotLimitOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrders.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BatchCreateSpotLimitOrders"))
              .build();
        }
      }
    }
    return getBatchCreateSpotLimitOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrder,
      injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrderResponse> getCreateSpotMarketOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateSpotMarketOrder",
      requestType = injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrder.class,
      responseType = injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrder,
      injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrderResponse> getCreateSpotMarketOrderMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrder, injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrderResponse> getCreateSpotMarketOrderMethod;
    if ((getCreateSpotMarketOrderMethod = MsgGrpc.getCreateSpotMarketOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateSpotMarketOrderMethod = MsgGrpc.getCreateSpotMarketOrderMethod) == null) {
          MsgGrpc.getCreateSpotMarketOrderMethod = getCreateSpotMarketOrderMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrder, injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateSpotMarketOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateSpotMarketOrder"))
              .build();
        }
      }
    }
    return getCreateSpotMarketOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCancelSpotOrder,
      injective.exchange.v1beta1.Tx.MsgCancelSpotOrderResponse> getCancelSpotOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelSpotOrder",
      requestType = injective.exchange.v1beta1.Tx.MsgCancelSpotOrder.class,
      responseType = injective.exchange.v1beta1.Tx.MsgCancelSpotOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCancelSpotOrder,
      injective.exchange.v1beta1.Tx.MsgCancelSpotOrderResponse> getCancelSpotOrderMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCancelSpotOrder, injective.exchange.v1beta1.Tx.MsgCancelSpotOrderResponse> getCancelSpotOrderMethod;
    if ((getCancelSpotOrderMethod = MsgGrpc.getCancelSpotOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelSpotOrderMethod = MsgGrpc.getCancelSpotOrderMethod) == null) {
          MsgGrpc.getCancelSpotOrderMethod = getCancelSpotOrderMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgCancelSpotOrder, injective.exchange.v1beta1.Tx.MsgCancelSpotOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelSpotOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgCancelSpotOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgCancelSpotOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelSpotOrder"))
              .build();
        }
      }
    }
    return getCancelSpotOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrders,
      injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrdersResponse> getBatchCancelSpotOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchCancelSpotOrders",
      requestType = injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrders.class,
      responseType = injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrders,
      injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrdersResponse> getBatchCancelSpotOrdersMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrders, injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrdersResponse> getBatchCancelSpotOrdersMethod;
    if ((getBatchCancelSpotOrdersMethod = MsgGrpc.getBatchCancelSpotOrdersMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBatchCancelSpotOrdersMethod = MsgGrpc.getBatchCancelSpotOrdersMethod) == null) {
          MsgGrpc.getBatchCancelSpotOrdersMethod = getBatchCancelSpotOrdersMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrders, injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchCancelSpotOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrders.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BatchCancelSpotOrders"))
              .build();
        }
      }
    }
    return getBatchCancelSpotOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrder,
      injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrderResponse> getCreateDerivativeLimitOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateDerivativeLimitOrder",
      requestType = injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrder.class,
      responseType = injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrder,
      injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrderResponse> getCreateDerivativeLimitOrderMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrder, injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrderResponse> getCreateDerivativeLimitOrderMethod;
    if ((getCreateDerivativeLimitOrderMethod = MsgGrpc.getCreateDerivativeLimitOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateDerivativeLimitOrderMethod = MsgGrpc.getCreateDerivativeLimitOrderMethod) == null) {
          MsgGrpc.getCreateDerivativeLimitOrderMethod = getCreateDerivativeLimitOrderMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrder, injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateDerivativeLimitOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateDerivativeLimitOrder"))
              .build();
        }
      }
    }
    return getCreateDerivativeLimitOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrders,
      injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrdersResponse> getBatchCreateDerivativeLimitOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchCreateDerivativeLimitOrders",
      requestType = injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrders.class,
      responseType = injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrders,
      injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrdersResponse> getBatchCreateDerivativeLimitOrdersMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrders, injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrdersResponse> getBatchCreateDerivativeLimitOrdersMethod;
    if ((getBatchCreateDerivativeLimitOrdersMethod = MsgGrpc.getBatchCreateDerivativeLimitOrdersMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBatchCreateDerivativeLimitOrdersMethod = MsgGrpc.getBatchCreateDerivativeLimitOrdersMethod) == null) {
          MsgGrpc.getBatchCreateDerivativeLimitOrdersMethod = getBatchCreateDerivativeLimitOrdersMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrders, injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchCreateDerivativeLimitOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrders.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BatchCreateDerivativeLimitOrders"))
              .build();
        }
      }
    }
    return getBatchCreateDerivativeLimitOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrder,
      injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrderResponse> getCreateDerivativeMarketOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateDerivativeMarketOrder",
      requestType = injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrder.class,
      responseType = injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrder,
      injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrderResponse> getCreateDerivativeMarketOrderMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrder, injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrderResponse> getCreateDerivativeMarketOrderMethod;
    if ((getCreateDerivativeMarketOrderMethod = MsgGrpc.getCreateDerivativeMarketOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateDerivativeMarketOrderMethod = MsgGrpc.getCreateDerivativeMarketOrderMethod) == null) {
          MsgGrpc.getCreateDerivativeMarketOrderMethod = getCreateDerivativeMarketOrderMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrder, injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateDerivativeMarketOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateDerivativeMarketOrder"))
              .build();
        }
      }
    }
    return getCreateDerivativeMarketOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrder,
      injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrderResponse> getCancelDerivativeOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelDerivativeOrder",
      requestType = injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrder.class,
      responseType = injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrder,
      injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrderResponse> getCancelDerivativeOrderMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrder, injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrderResponse> getCancelDerivativeOrderMethod;
    if ((getCancelDerivativeOrderMethod = MsgGrpc.getCancelDerivativeOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelDerivativeOrderMethod = MsgGrpc.getCancelDerivativeOrderMethod) == null) {
          MsgGrpc.getCancelDerivativeOrderMethod = getCancelDerivativeOrderMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrder, injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelDerivativeOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelDerivativeOrder"))
              .build();
        }
      }
    }
    return getCancelDerivativeOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrders,
      injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrdersResponse> getBatchCancelDerivativeOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchCancelDerivativeOrders",
      requestType = injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrders.class,
      responseType = injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrders,
      injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrdersResponse> getBatchCancelDerivativeOrdersMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrders, injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrdersResponse> getBatchCancelDerivativeOrdersMethod;
    if ((getBatchCancelDerivativeOrdersMethod = MsgGrpc.getBatchCancelDerivativeOrdersMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBatchCancelDerivativeOrdersMethod = MsgGrpc.getBatchCancelDerivativeOrdersMethod) == null) {
          MsgGrpc.getBatchCancelDerivativeOrdersMethod = getBatchCancelDerivativeOrdersMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrders, injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchCancelDerivativeOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrders.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BatchCancelDerivativeOrders"))
              .build();
        }
      }
    }
    return getBatchCancelDerivativeOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgSubaccountTransfer,
      injective.exchange.v1beta1.Tx.MsgSubaccountTransferResponse> getSubaccountTransferMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubaccountTransfer",
      requestType = injective.exchange.v1beta1.Tx.MsgSubaccountTransfer.class,
      responseType = injective.exchange.v1beta1.Tx.MsgSubaccountTransferResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgSubaccountTransfer,
      injective.exchange.v1beta1.Tx.MsgSubaccountTransferResponse> getSubaccountTransferMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgSubaccountTransfer, injective.exchange.v1beta1.Tx.MsgSubaccountTransferResponse> getSubaccountTransferMethod;
    if ((getSubaccountTransferMethod = MsgGrpc.getSubaccountTransferMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSubaccountTransferMethod = MsgGrpc.getSubaccountTransferMethod) == null) {
          MsgGrpc.getSubaccountTransferMethod = getSubaccountTransferMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgSubaccountTransfer, injective.exchange.v1beta1.Tx.MsgSubaccountTransferResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubaccountTransfer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgSubaccountTransfer.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgSubaccountTransferResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SubaccountTransfer"))
              .build();
        }
      }
    }
    return getSubaccountTransferMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgExternalTransfer,
      injective.exchange.v1beta1.Tx.MsgExternalTransferResponse> getExternalTransferMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExternalTransfer",
      requestType = injective.exchange.v1beta1.Tx.MsgExternalTransfer.class,
      responseType = injective.exchange.v1beta1.Tx.MsgExternalTransferResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgExternalTransfer,
      injective.exchange.v1beta1.Tx.MsgExternalTransferResponse> getExternalTransferMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgExternalTransfer, injective.exchange.v1beta1.Tx.MsgExternalTransferResponse> getExternalTransferMethod;
    if ((getExternalTransferMethod = MsgGrpc.getExternalTransferMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getExternalTransferMethod = MsgGrpc.getExternalTransferMethod) == null) {
          MsgGrpc.getExternalTransferMethod = getExternalTransferMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgExternalTransfer, injective.exchange.v1beta1.Tx.MsgExternalTransferResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExternalTransfer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgExternalTransfer.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgExternalTransferResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ExternalTransfer"))
              .build();
        }
      }
    }
    return getExternalTransferMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgLiquidatePosition,
      injective.exchange.v1beta1.Tx.MsgLiquidatePositionResponse> getLiquidatePositionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiquidatePosition",
      requestType = injective.exchange.v1beta1.Tx.MsgLiquidatePosition.class,
      responseType = injective.exchange.v1beta1.Tx.MsgLiquidatePositionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgLiquidatePosition,
      injective.exchange.v1beta1.Tx.MsgLiquidatePositionResponse> getLiquidatePositionMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgLiquidatePosition, injective.exchange.v1beta1.Tx.MsgLiquidatePositionResponse> getLiquidatePositionMethod;
    if ((getLiquidatePositionMethod = MsgGrpc.getLiquidatePositionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLiquidatePositionMethod = MsgGrpc.getLiquidatePositionMethod) == null) {
          MsgGrpc.getLiquidatePositionMethod = getLiquidatePositionMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgLiquidatePosition, injective.exchange.v1beta1.Tx.MsgLiquidatePositionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LiquidatePosition"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgLiquidatePosition.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgLiquidatePositionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("LiquidatePosition"))
              .build();
        }
      }
    }
    return getLiquidatePositionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgIncreasePositionMargin,
      injective.exchange.v1beta1.Tx.MsgIncreasePositionMarginResponse> getIncreasePositionMarginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IncreasePositionMargin",
      requestType = injective.exchange.v1beta1.Tx.MsgIncreasePositionMargin.class,
      responseType = injective.exchange.v1beta1.Tx.MsgIncreasePositionMarginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgIncreasePositionMargin,
      injective.exchange.v1beta1.Tx.MsgIncreasePositionMarginResponse> getIncreasePositionMarginMethod() {
    io.grpc.MethodDescriptor<injective.exchange.v1beta1.Tx.MsgIncreasePositionMargin, injective.exchange.v1beta1.Tx.MsgIncreasePositionMarginResponse> getIncreasePositionMarginMethod;
    if ((getIncreasePositionMarginMethod = MsgGrpc.getIncreasePositionMarginMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getIncreasePositionMarginMethod = MsgGrpc.getIncreasePositionMarginMethod) == null) {
          MsgGrpc.getIncreasePositionMarginMethod = getIncreasePositionMarginMethod =
              io.grpc.MethodDescriptor.<injective.exchange.v1beta1.Tx.MsgIncreasePositionMargin, injective.exchange.v1beta1.Tx.MsgIncreasePositionMarginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IncreasePositionMargin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgIncreasePositionMargin.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.exchange.v1beta1.Tx.MsgIncreasePositionMarginResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("IncreasePositionMargin"))
              .build();
        }
      }
    }
    return getIncreasePositionMarginMethod;
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
   * Msg defines the staking Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Deposit defines a method for transferring coins from the sender's bank balance into the subaccount's exchange deposits
     * </pre>
     */
    public void deposit(injective.exchange.v1beta1.Tx.MsgDeposit request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgDepositResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositMethod(), responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing coins from a subaccount's deposits to the user's bank balance
     * </pre>
     */
    public void withdraw(injective.exchange.v1beta1.Tx.MsgWithdraw request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgWithdrawResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawMethod(), responseObserver);
    }

    /**
     * <pre>
     * InstantSpotMarketLaunch defines method for creating a spot market by paying listing fee without governance
     * </pre>
     */
    public void instantSpotMarketLaunch(injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunch request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInstantSpotMarketLaunchMethod(), responseObserver);
    }

    /**
     * <pre>
     * InstantPerpetualMarketLaunch defines a method for creating a new perpetual futures market by paying listing fee without governance
     * </pre>
     */
    public void instantPerpetualMarketLaunch(injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunch request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInstantPerpetualMarketLaunchMethod(), responseObserver);
    }

    /**
     * <pre>
     * InstantExpiryFuturesMarketLaunch defines a method for creating a new expiry futures market by paying listing fee without governance
     * </pre>
     */
    public void instantExpiryFuturesMarketLaunch(injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunch request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInstantExpiryFuturesMarketLaunchMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateSpotLimitOrder defines a method for creating a new spot limit order.
     * </pre>
     */
    public void createSpotLimitOrder(injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrder request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateSpotLimitOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * BatchCreateSpotLimitOrder defines a method for creating a new batch of spot limit orders.
     * </pre>
     */
    public void batchCreateSpotLimitOrders(injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrders request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrdersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchCreateSpotLimitOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateSpotMarketOrder defines a method for creating a new spot market order.
     * </pre>
     */
    public void createSpotMarketOrder(injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrder request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateSpotMarketOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * MsgCancelSpotOrder defines a method for cancelling a spot order.
     * </pre>
     */
    public void cancelSpotOrder(injective.exchange.v1beta1.Tx.MsgCancelSpotOrder request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCancelSpotOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelSpotOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * BatchCancelSpotOrders defines a method for cancelling a batch of spot orders in a given market.
     * </pre>
     */
    public void batchCancelSpotOrders(injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrders request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrdersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchCancelSpotOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateDerivativeLimitOrder defines a method for creating a new derivative limit order.
     * </pre>
     */
    public void createDerivativeLimitOrder(injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrder request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateDerivativeLimitOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * BatchCreateDerivativeLimitOrders defines a method for creating a new batch of derivative limit orders.
     * </pre>
     */
    public void batchCreateDerivativeLimitOrders(injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrders request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrdersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchCreateDerivativeLimitOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * MsgCreateDerivativeLimitOrder defines a method for creating a new derivative market order.
     * </pre>
     */
    public void createDerivativeMarketOrder(injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrder request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateDerivativeMarketOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * MsgCancelDerivativeOrder defines a method for cancelling a derivative order.
     * </pre>
     */
    public void cancelDerivativeOrder(injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrder request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelDerivativeOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * MsgBatchCancelDerivativeOrders defines a method for cancelling a batch of derivative limit orders.
     * </pre>
     */
    public void batchCancelDerivativeOrders(injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrders request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrdersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchCancelDerivativeOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * SubaccountTransfer defines a method for transfer between subaccounts
     * </pre>
     */
    public void subaccountTransfer(injective.exchange.v1beta1.Tx.MsgSubaccountTransfer request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgSubaccountTransferResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubaccountTransferMethod(), responseObserver);
    }

    /**
     * <pre>
     * ExternalTransfer defines a method for transfer between external accounts
     * </pre>
     */
    public void externalTransfer(injective.exchange.v1beta1.Tx.MsgExternalTransfer request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgExternalTransferResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getExternalTransferMethod(), responseObserver);
    }

    /**
     * <pre>
     * LiquidatePosition defines a method for liquidating a position
     * </pre>
     */
    public void liquidatePosition(injective.exchange.v1beta1.Tx.MsgLiquidatePosition request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgLiquidatePositionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLiquidatePositionMethod(), responseObserver);
    }

    /**
     * <pre>
     * IncreasePositionMargin defines a method for increasing margin of a position
     * </pre>
     */
    public void increasePositionMargin(injective.exchange.v1beta1.Tx.MsgIncreasePositionMargin request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgIncreasePositionMarginResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getIncreasePositionMarginMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgDeposit,
                injective.exchange.v1beta1.Tx.MsgDepositResponse>(
                  this, METHODID_DEPOSIT)))
          .addMethod(
            getWithdrawMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgWithdraw,
                injective.exchange.v1beta1.Tx.MsgWithdrawResponse>(
                  this, METHODID_WITHDRAW)))
          .addMethod(
            getInstantSpotMarketLaunchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunch,
                injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunchResponse>(
                  this, METHODID_INSTANT_SPOT_MARKET_LAUNCH)))
          .addMethod(
            getInstantPerpetualMarketLaunchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunch,
                injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunchResponse>(
                  this, METHODID_INSTANT_PERPETUAL_MARKET_LAUNCH)))
          .addMethod(
            getInstantExpiryFuturesMarketLaunchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunch,
                injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunchResponse>(
                  this, METHODID_INSTANT_EXPIRY_FUTURES_MARKET_LAUNCH)))
          .addMethod(
            getCreateSpotLimitOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrder,
                injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrderResponse>(
                  this, METHODID_CREATE_SPOT_LIMIT_ORDER)))
          .addMethod(
            getBatchCreateSpotLimitOrdersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrders,
                injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrdersResponse>(
                  this, METHODID_BATCH_CREATE_SPOT_LIMIT_ORDERS)))
          .addMethod(
            getCreateSpotMarketOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrder,
                injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrderResponse>(
                  this, METHODID_CREATE_SPOT_MARKET_ORDER)))
          .addMethod(
            getCancelSpotOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgCancelSpotOrder,
                injective.exchange.v1beta1.Tx.MsgCancelSpotOrderResponse>(
                  this, METHODID_CANCEL_SPOT_ORDER)))
          .addMethod(
            getBatchCancelSpotOrdersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrders,
                injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrdersResponse>(
                  this, METHODID_BATCH_CANCEL_SPOT_ORDERS)))
          .addMethod(
            getCreateDerivativeLimitOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrder,
                injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrderResponse>(
                  this, METHODID_CREATE_DERIVATIVE_LIMIT_ORDER)))
          .addMethod(
            getBatchCreateDerivativeLimitOrdersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrders,
                injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrdersResponse>(
                  this, METHODID_BATCH_CREATE_DERIVATIVE_LIMIT_ORDERS)))
          .addMethod(
            getCreateDerivativeMarketOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrder,
                injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrderResponse>(
                  this, METHODID_CREATE_DERIVATIVE_MARKET_ORDER)))
          .addMethod(
            getCancelDerivativeOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrder,
                injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrderResponse>(
                  this, METHODID_CANCEL_DERIVATIVE_ORDER)))
          .addMethod(
            getBatchCancelDerivativeOrdersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrders,
                injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrdersResponse>(
                  this, METHODID_BATCH_CANCEL_DERIVATIVE_ORDERS)))
          .addMethod(
            getSubaccountTransferMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgSubaccountTransfer,
                injective.exchange.v1beta1.Tx.MsgSubaccountTransferResponse>(
                  this, METHODID_SUBACCOUNT_TRANSFER)))
          .addMethod(
            getExternalTransferMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgExternalTransfer,
                injective.exchange.v1beta1.Tx.MsgExternalTransferResponse>(
                  this, METHODID_EXTERNAL_TRANSFER)))
          .addMethod(
            getLiquidatePositionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgLiquidatePosition,
                injective.exchange.v1beta1.Tx.MsgLiquidatePositionResponse>(
                  this, METHODID_LIQUIDATE_POSITION)))
          .addMethod(
            getIncreasePositionMarginMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.exchange.v1beta1.Tx.MsgIncreasePositionMargin,
                injective.exchange.v1beta1.Tx.MsgIncreasePositionMarginResponse>(
                  this, METHODID_INCREASE_POSITION_MARGIN)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the staking Msg service.
   * </pre>
   */
  public static final class MsgStub extends io.grpc.stub.AbstractAsyncStub<MsgStub> {
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
     * Deposit defines a method for transferring coins from the sender's bank balance into the subaccount's exchange deposits
     * </pre>
     */
    public void deposit(injective.exchange.v1beta1.Tx.MsgDeposit request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgDepositResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing coins from a subaccount's deposits to the user's bank balance
     * </pre>
     */
    public void withdraw(injective.exchange.v1beta1.Tx.MsgWithdraw request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgWithdrawResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * InstantSpotMarketLaunch defines method for creating a spot market by paying listing fee without governance
     * </pre>
     */
    public void instantSpotMarketLaunch(injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunch request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInstantSpotMarketLaunchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * InstantPerpetualMarketLaunch defines a method for creating a new perpetual futures market by paying listing fee without governance
     * </pre>
     */
    public void instantPerpetualMarketLaunch(injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunch request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInstantPerpetualMarketLaunchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * InstantExpiryFuturesMarketLaunch defines a method for creating a new expiry futures market by paying listing fee without governance
     * </pre>
     */
    public void instantExpiryFuturesMarketLaunch(injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunch request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInstantExpiryFuturesMarketLaunchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateSpotLimitOrder defines a method for creating a new spot limit order.
     * </pre>
     */
    public void createSpotLimitOrder(injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrder request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateSpotLimitOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BatchCreateSpotLimitOrder defines a method for creating a new batch of spot limit orders.
     * </pre>
     */
    public void batchCreateSpotLimitOrders(injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrders request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrdersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchCreateSpotLimitOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateSpotMarketOrder defines a method for creating a new spot market order.
     * </pre>
     */
    public void createSpotMarketOrder(injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrder request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateSpotMarketOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MsgCancelSpotOrder defines a method for cancelling a spot order.
     * </pre>
     */
    public void cancelSpotOrder(injective.exchange.v1beta1.Tx.MsgCancelSpotOrder request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCancelSpotOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelSpotOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BatchCancelSpotOrders defines a method for cancelling a batch of spot orders in a given market.
     * </pre>
     */
    public void batchCancelSpotOrders(injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrders request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrdersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchCancelSpotOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateDerivativeLimitOrder defines a method for creating a new derivative limit order.
     * </pre>
     */
    public void createDerivativeLimitOrder(injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrder request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateDerivativeLimitOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BatchCreateDerivativeLimitOrders defines a method for creating a new batch of derivative limit orders.
     * </pre>
     */
    public void batchCreateDerivativeLimitOrders(injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrders request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrdersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchCreateDerivativeLimitOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MsgCreateDerivativeLimitOrder defines a method for creating a new derivative market order.
     * </pre>
     */
    public void createDerivativeMarketOrder(injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrder request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateDerivativeMarketOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MsgCancelDerivativeOrder defines a method for cancelling a derivative order.
     * </pre>
     */
    public void cancelDerivativeOrder(injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrder request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelDerivativeOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MsgBatchCancelDerivativeOrders defines a method for cancelling a batch of derivative limit orders.
     * </pre>
     */
    public void batchCancelDerivativeOrders(injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrders request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrdersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchCancelDerivativeOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SubaccountTransfer defines a method for transfer between subaccounts
     * </pre>
     */
    public void subaccountTransfer(injective.exchange.v1beta1.Tx.MsgSubaccountTransfer request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgSubaccountTransferResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubaccountTransferMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ExternalTransfer defines a method for transfer between external accounts
     * </pre>
     */
    public void externalTransfer(injective.exchange.v1beta1.Tx.MsgExternalTransfer request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgExternalTransferResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExternalTransferMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * LiquidatePosition defines a method for liquidating a position
     * </pre>
     */
    public void liquidatePosition(injective.exchange.v1beta1.Tx.MsgLiquidatePosition request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgLiquidatePositionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLiquidatePositionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * IncreasePositionMargin defines a method for increasing margin of a position
     * </pre>
     */
    public void increasePositionMargin(injective.exchange.v1beta1.Tx.MsgIncreasePositionMargin request,
        io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgIncreasePositionMarginResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIncreasePositionMarginMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the staking Msg service.
   * </pre>
   */
  public static final class MsgBlockingStub extends io.grpc.stub.AbstractBlockingStub<MsgBlockingStub> {
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
     * Deposit defines a method for transferring coins from the sender's bank balance into the subaccount's exchange deposits
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgDepositResponse deposit(injective.exchange.v1beta1.Tx.MsgDeposit request) {
      return blockingUnaryCall(
          getChannel(), getDepositMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing coins from a subaccount's deposits to the user's bank balance
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgWithdrawResponse withdraw(injective.exchange.v1beta1.Tx.MsgWithdraw request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * InstantSpotMarketLaunch defines method for creating a spot market by paying listing fee without governance
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunchResponse instantSpotMarketLaunch(injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunch request) {
      return blockingUnaryCall(
          getChannel(), getInstantSpotMarketLaunchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * InstantPerpetualMarketLaunch defines a method for creating a new perpetual futures market by paying listing fee without governance
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunchResponse instantPerpetualMarketLaunch(injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunch request) {
      return blockingUnaryCall(
          getChannel(), getInstantPerpetualMarketLaunchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * InstantExpiryFuturesMarketLaunch defines a method for creating a new expiry futures market by paying listing fee without governance
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunchResponse instantExpiryFuturesMarketLaunch(injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunch request) {
      return blockingUnaryCall(
          getChannel(), getInstantExpiryFuturesMarketLaunchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateSpotLimitOrder defines a method for creating a new spot limit order.
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrderResponse createSpotLimitOrder(injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrder request) {
      return blockingUnaryCall(
          getChannel(), getCreateSpotLimitOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BatchCreateSpotLimitOrder defines a method for creating a new batch of spot limit orders.
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrdersResponse batchCreateSpotLimitOrders(injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrders request) {
      return blockingUnaryCall(
          getChannel(), getBatchCreateSpotLimitOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateSpotMarketOrder defines a method for creating a new spot market order.
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrderResponse createSpotMarketOrder(injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrder request) {
      return blockingUnaryCall(
          getChannel(), getCreateSpotMarketOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MsgCancelSpotOrder defines a method for cancelling a spot order.
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgCancelSpotOrderResponse cancelSpotOrder(injective.exchange.v1beta1.Tx.MsgCancelSpotOrder request) {
      return blockingUnaryCall(
          getChannel(), getCancelSpotOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BatchCancelSpotOrders defines a method for cancelling a batch of spot orders in a given market.
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrdersResponse batchCancelSpotOrders(injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrders request) {
      return blockingUnaryCall(
          getChannel(), getBatchCancelSpotOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateDerivativeLimitOrder defines a method for creating a new derivative limit order.
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrderResponse createDerivativeLimitOrder(injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrder request) {
      return blockingUnaryCall(
          getChannel(), getCreateDerivativeLimitOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BatchCreateDerivativeLimitOrders defines a method for creating a new batch of derivative limit orders.
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrdersResponse batchCreateDerivativeLimitOrders(injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrders request) {
      return blockingUnaryCall(
          getChannel(), getBatchCreateDerivativeLimitOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MsgCreateDerivativeLimitOrder defines a method for creating a new derivative market order.
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrderResponse createDerivativeMarketOrder(injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrder request) {
      return blockingUnaryCall(
          getChannel(), getCreateDerivativeMarketOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MsgCancelDerivativeOrder defines a method for cancelling a derivative order.
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrderResponse cancelDerivativeOrder(injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrder request) {
      return blockingUnaryCall(
          getChannel(), getCancelDerivativeOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MsgBatchCancelDerivativeOrders defines a method for cancelling a batch of derivative limit orders.
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrdersResponse batchCancelDerivativeOrders(injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrders request) {
      return blockingUnaryCall(
          getChannel(), getBatchCancelDerivativeOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SubaccountTransfer defines a method for transfer between subaccounts
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgSubaccountTransferResponse subaccountTransfer(injective.exchange.v1beta1.Tx.MsgSubaccountTransfer request) {
      return blockingUnaryCall(
          getChannel(), getSubaccountTransferMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ExternalTransfer defines a method for transfer between external accounts
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgExternalTransferResponse externalTransfer(injective.exchange.v1beta1.Tx.MsgExternalTransfer request) {
      return blockingUnaryCall(
          getChannel(), getExternalTransferMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * LiquidatePosition defines a method for liquidating a position
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgLiquidatePositionResponse liquidatePosition(injective.exchange.v1beta1.Tx.MsgLiquidatePosition request) {
      return blockingUnaryCall(
          getChannel(), getLiquidatePositionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * IncreasePositionMargin defines a method for increasing margin of a position
     * </pre>
     */
    public injective.exchange.v1beta1.Tx.MsgIncreasePositionMarginResponse increasePositionMargin(injective.exchange.v1beta1.Tx.MsgIncreasePositionMargin request) {
      return blockingUnaryCall(
          getChannel(), getIncreasePositionMarginMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the staking Msg service.
   * </pre>
   */
  public static final class MsgFutureStub extends io.grpc.stub.AbstractFutureStub<MsgFutureStub> {
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
     * Deposit defines a method for transferring coins from the sender's bank balance into the subaccount's exchange deposits
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgDepositResponse> deposit(
        injective.exchange.v1beta1.Tx.MsgDeposit request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing coins from a subaccount's deposits to the user's bank balance
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgWithdrawResponse> withdraw(
        injective.exchange.v1beta1.Tx.MsgWithdraw request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * InstantSpotMarketLaunch defines method for creating a spot market by paying listing fee without governance
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunchResponse> instantSpotMarketLaunch(
        injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunch request) {
      return futureUnaryCall(
          getChannel().newCall(getInstantSpotMarketLaunchMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * InstantPerpetualMarketLaunch defines a method for creating a new perpetual futures market by paying listing fee without governance
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunchResponse> instantPerpetualMarketLaunch(
        injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunch request) {
      return futureUnaryCall(
          getChannel().newCall(getInstantPerpetualMarketLaunchMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * InstantExpiryFuturesMarketLaunch defines a method for creating a new expiry futures market by paying listing fee without governance
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunchResponse> instantExpiryFuturesMarketLaunch(
        injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunch request) {
      return futureUnaryCall(
          getChannel().newCall(getInstantExpiryFuturesMarketLaunchMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateSpotLimitOrder defines a method for creating a new spot limit order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrderResponse> createSpotLimitOrder(
        injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrder request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateSpotLimitOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BatchCreateSpotLimitOrder defines a method for creating a new batch of spot limit orders.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrdersResponse> batchCreateSpotLimitOrders(
        injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrders request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchCreateSpotLimitOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateSpotMarketOrder defines a method for creating a new spot market order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrderResponse> createSpotMarketOrder(
        injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrder request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateSpotMarketOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MsgCancelSpotOrder defines a method for cancelling a spot order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgCancelSpotOrderResponse> cancelSpotOrder(
        injective.exchange.v1beta1.Tx.MsgCancelSpotOrder request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelSpotOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BatchCancelSpotOrders defines a method for cancelling a batch of spot orders in a given market.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrdersResponse> batchCancelSpotOrders(
        injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrders request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchCancelSpotOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateDerivativeLimitOrder defines a method for creating a new derivative limit order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrderResponse> createDerivativeLimitOrder(
        injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrder request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateDerivativeLimitOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BatchCreateDerivativeLimitOrders defines a method for creating a new batch of derivative limit orders.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrdersResponse> batchCreateDerivativeLimitOrders(
        injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrders request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchCreateDerivativeLimitOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MsgCreateDerivativeLimitOrder defines a method for creating a new derivative market order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrderResponse> createDerivativeMarketOrder(
        injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrder request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateDerivativeMarketOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MsgCancelDerivativeOrder defines a method for cancelling a derivative order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrderResponse> cancelDerivativeOrder(
        injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrder request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelDerivativeOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MsgBatchCancelDerivativeOrders defines a method for cancelling a batch of derivative limit orders.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrdersResponse> batchCancelDerivativeOrders(
        injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrders request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchCancelDerivativeOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SubaccountTransfer defines a method for transfer between subaccounts
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgSubaccountTransferResponse> subaccountTransfer(
        injective.exchange.v1beta1.Tx.MsgSubaccountTransfer request) {
      return futureUnaryCall(
          getChannel().newCall(getSubaccountTransferMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ExternalTransfer defines a method for transfer between external accounts
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgExternalTransferResponse> externalTransfer(
        injective.exchange.v1beta1.Tx.MsgExternalTransfer request) {
      return futureUnaryCall(
          getChannel().newCall(getExternalTransferMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * LiquidatePosition defines a method for liquidating a position
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgLiquidatePositionResponse> liquidatePosition(
        injective.exchange.v1beta1.Tx.MsgLiquidatePosition request) {
      return futureUnaryCall(
          getChannel().newCall(getLiquidatePositionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * IncreasePositionMargin defines a method for increasing margin of a position
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.exchange.v1beta1.Tx.MsgIncreasePositionMarginResponse> increasePositionMargin(
        injective.exchange.v1beta1.Tx.MsgIncreasePositionMargin request) {
      return futureUnaryCall(
          getChannel().newCall(getIncreasePositionMarginMethod(), getCallOptions()), request);
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
  private static final int METHODID_CREATE_DERIVATIVE_LIMIT_ORDER = 10;
  private static final int METHODID_BATCH_CREATE_DERIVATIVE_LIMIT_ORDERS = 11;
  private static final int METHODID_CREATE_DERIVATIVE_MARKET_ORDER = 12;
  private static final int METHODID_CANCEL_DERIVATIVE_ORDER = 13;
  private static final int METHODID_BATCH_CANCEL_DERIVATIVE_ORDERS = 14;
  private static final int METHODID_SUBACCOUNT_TRANSFER = 15;
  private static final int METHODID_EXTERNAL_TRANSFER = 16;
  private static final int METHODID_LIQUIDATE_POSITION = 17;
  private static final int METHODID_INCREASE_POSITION_MARGIN = 18;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MsgImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MsgImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DEPOSIT:
          serviceImpl.deposit((injective.exchange.v1beta1.Tx.MsgDeposit) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgDepositResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW:
          serviceImpl.withdraw((injective.exchange.v1beta1.Tx.MsgWithdraw) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgWithdrawResponse>) responseObserver);
          break;
        case METHODID_INSTANT_SPOT_MARKET_LAUNCH:
          serviceImpl.instantSpotMarketLaunch((injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunch) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgInstantSpotMarketLaunchResponse>) responseObserver);
          break;
        case METHODID_INSTANT_PERPETUAL_MARKET_LAUNCH:
          serviceImpl.instantPerpetualMarketLaunch((injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunch) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgInstantPerpetualMarketLaunchResponse>) responseObserver);
          break;
        case METHODID_INSTANT_EXPIRY_FUTURES_MARKET_LAUNCH:
          serviceImpl.instantExpiryFuturesMarketLaunch((injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunch) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgInstantExpiryFuturesMarketLaunchResponse>) responseObserver);
          break;
        case METHODID_CREATE_SPOT_LIMIT_ORDER:
          serviceImpl.createSpotLimitOrder((injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrder) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCreateSpotLimitOrderResponse>) responseObserver);
          break;
        case METHODID_BATCH_CREATE_SPOT_LIMIT_ORDERS:
          serviceImpl.batchCreateSpotLimitOrders((injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrders) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgBatchCreateSpotLimitOrdersResponse>) responseObserver);
          break;
        case METHODID_CREATE_SPOT_MARKET_ORDER:
          serviceImpl.createSpotMarketOrder((injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrder) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCreateSpotMarketOrderResponse>) responseObserver);
          break;
        case METHODID_CANCEL_SPOT_ORDER:
          serviceImpl.cancelSpotOrder((injective.exchange.v1beta1.Tx.MsgCancelSpotOrder) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCancelSpotOrderResponse>) responseObserver);
          break;
        case METHODID_BATCH_CANCEL_SPOT_ORDERS:
          serviceImpl.batchCancelSpotOrders((injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrders) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgBatchCancelSpotOrdersResponse>) responseObserver);
          break;
        case METHODID_CREATE_DERIVATIVE_LIMIT_ORDER:
          serviceImpl.createDerivativeLimitOrder((injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrder) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCreateDerivativeLimitOrderResponse>) responseObserver);
          break;
        case METHODID_BATCH_CREATE_DERIVATIVE_LIMIT_ORDERS:
          serviceImpl.batchCreateDerivativeLimitOrders((injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrders) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgBatchCreateDerivativeLimitOrdersResponse>) responseObserver);
          break;
        case METHODID_CREATE_DERIVATIVE_MARKET_ORDER:
          serviceImpl.createDerivativeMarketOrder((injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrder) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCreateDerivativeMarketOrderResponse>) responseObserver);
          break;
        case METHODID_CANCEL_DERIVATIVE_ORDER:
          serviceImpl.cancelDerivativeOrder((injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrder) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgCancelDerivativeOrderResponse>) responseObserver);
          break;
        case METHODID_BATCH_CANCEL_DERIVATIVE_ORDERS:
          serviceImpl.batchCancelDerivativeOrders((injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrders) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgBatchCancelDerivativeOrdersResponse>) responseObserver);
          break;
        case METHODID_SUBACCOUNT_TRANSFER:
          serviceImpl.subaccountTransfer((injective.exchange.v1beta1.Tx.MsgSubaccountTransfer) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgSubaccountTransferResponse>) responseObserver);
          break;
        case METHODID_EXTERNAL_TRANSFER:
          serviceImpl.externalTransfer((injective.exchange.v1beta1.Tx.MsgExternalTransfer) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgExternalTransferResponse>) responseObserver);
          break;
        case METHODID_LIQUIDATE_POSITION:
          serviceImpl.liquidatePosition((injective.exchange.v1beta1.Tx.MsgLiquidatePosition) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgLiquidatePositionResponse>) responseObserver);
          break;
        case METHODID_INCREASE_POSITION_MARGIN:
          serviceImpl.increasePositionMargin((injective.exchange.v1beta1.Tx.MsgIncreasePositionMargin) request,
              (io.grpc.stub.StreamObserver<injective.exchange.v1beta1.Tx.MsgIncreasePositionMarginResponse>) responseObserver);
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

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return injective.exchange.v1beta1.Tx.getDescriptor();
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
              .addMethod(getCreateDerivativeLimitOrderMethod())
              .addMethod(getBatchCreateDerivativeLimitOrdersMethod())
              .addMethod(getCreateDerivativeMarketOrderMethod())
              .addMethod(getCancelDerivativeOrderMethod())
              .addMethod(getBatchCancelDerivativeOrdersMethod())
              .addMethod(getSubaccountTransferMethod())
              .addMethod(getExternalTransferMethod())
              .addMethod(getLiquidatePositionMethod())
              .addMethod(getIncreasePositionMarginMethod())
              .build();
        }
      }
    }
    return result;
  }
}
