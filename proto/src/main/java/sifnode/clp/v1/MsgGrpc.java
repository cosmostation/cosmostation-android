package sifnode.clp.v1;

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
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: sifnode/clp/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "sifnode.clp.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgRemoveLiquidity,
      sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse> getRemoveLiquidityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveLiquidity",
      requestType = sifnode.clp.v1.Tx.MsgRemoveLiquidity.class,
      responseType = sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgRemoveLiquidity,
      sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse> getRemoveLiquidityMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgRemoveLiquidity, sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse> getRemoveLiquidityMethod;
    if ((getRemoveLiquidityMethod = MsgGrpc.getRemoveLiquidityMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRemoveLiquidityMethod = MsgGrpc.getRemoveLiquidityMethod) == null) {
          MsgGrpc.getRemoveLiquidityMethod = getRemoveLiquidityMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgRemoveLiquidity, sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveLiquidity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgRemoveLiquidity.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RemoveLiquidity"))
              .build();
        }
      }
    }
    return getRemoveLiquidityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgRemoveLiquidityUnits,
      sifnode.clp.v1.Tx.MsgRemoveLiquidityUnitsResponse> getRemoveLiquidityUnitsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveLiquidityUnits",
      requestType = sifnode.clp.v1.Tx.MsgRemoveLiquidityUnits.class,
      responseType = sifnode.clp.v1.Tx.MsgRemoveLiquidityUnitsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgRemoveLiquidityUnits,
      sifnode.clp.v1.Tx.MsgRemoveLiquidityUnitsResponse> getRemoveLiquidityUnitsMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgRemoveLiquidityUnits, sifnode.clp.v1.Tx.MsgRemoveLiquidityUnitsResponse> getRemoveLiquidityUnitsMethod;
    if ((getRemoveLiquidityUnitsMethod = MsgGrpc.getRemoveLiquidityUnitsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRemoveLiquidityUnitsMethod = MsgGrpc.getRemoveLiquidityUnitsMethod) == null) {
          MsgGrpc.getRemoveLiquidityUnitsMethod = getRemoveLiquidityUnitsMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgRemoveLiquidityUnits, sifnode.clp.v1.Tx.MsgRemoveLiquidityUnitsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveLiquidityUnits"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgRemoveLiquidityUnits.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgRemoveLiquidityUnitsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RemoveLiquidityUnits"))
              .build();
        }
      }
    }
    return getRemoveLiquidityUnitsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgCreatePool,
      sifnode.clp.v1.Tx.MsgCreatePoolResponse> getCreatePoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePool",
      requestType = sifnode.clp.v1.Tx.MsgCreatePool.class,
      responseType = sifnode.clp.v1.Tx.MsgCreatePoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgCreatePool,
      sifnode.clp.v1.Tx.MsgCreatePoolResponse> getCreatePoolMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgCreatePool, sifnode.clp.v1.Tx.MsgCreatePoolResponse> getCreatePoolMethod;
    if ((getCreatePoolMethod = MsgGrpc.getCreatePoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreatePoolMethod = MsgGrpc.getCreatePoolMethod) == null) {
          MsgGrpc.getCreatePoolMethod = getCreatePoolMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgCreatePool, sifnode.clp.v1.Tx.MsgCreatePoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgCreatePool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgCreatePoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreatePool"))
              .build();
        }
      }
    }
    return getCreatePoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgAddLiquidity,
      sifnode.clp.v1.Tx.MsgAddLiquidityResponse> getAddLiquidityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddLiquidity",
      requestType = sifnode.clp.v1.Tx.MsgAddLiquidity.class,
      responseType = sifnode.clp.v1.Tx.MsgAddLiquidityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgAddLiquidity,
      sifnode.clp.v1.Tx.MsgAddLiquidityResponse> getAddLiquidityMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgAddLiquidity, sifnode.clp.v1.Tx.MsgAddLiquidityResponse> getAddLiquidityMethod;
    if ((getAddLiquidityMethod = MsgGrpc.getAddLiquidityMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddLiquidityMethod = MsgGrpc.getAddLiquidityMethod) == null) {
          MsgGrpc.getAddLiquidityMethod = getAddLiquidityMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgAddLiquidity, sifnode.clp.v1.Tx.MsgAddLiquidityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddLiquidity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgAddLiquidity.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgAddLiquidityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddLiquidity"))
              .build();
        }
      }
    }
    return getAddLiquidityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgSwap,
      sifnode.clp.v1.Tx.MsgSwapResponse> getSwapMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Swap",
      requestType = sifnode.clp.v1.Tx.MsgSwap.class,
      responseType = sifnode.clp.v1.Tx.MsgSwapResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgSwap,
      sifnode.clp.v1.Tx.MsgSwapResponse> getSwapMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgSwap, sifnode.clp.v1.Tx.MsgSwapResponse> getSwapMethod;
    if ((getSwapMethod = MsgGrpc.getSwapMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSwapMethod = MsgGrpc.getSwapMethod) == null) {
          MsgGrpc.getSwapMethod = getSwapMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgSwap, sifnode.clp.v1.Tx.MsgSwapResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Swap"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgSwap.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgSwapResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Swap"))
              .build();
        }
      }
    }
    return getSwapMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgDecommissionPool,
      sifnode.clp.v1.Tx.MsgDecommissionPoolResponse> getDecommissionPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DecommissionPool",
      requestType = sifnode.clp.v1.Tx.MsgDecommissionPool.class,
      responseType = sifnode.clp.v1.Tx.MsgDecommissionPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgDecommissionPool,
      sifnode.clp.v1.Tx.MsgDecommissionPoolResponse> getDecommissionPoolMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgDecommissionPool, sifnode.clp.v1.Tx.MsgDecommissionPoolResponse> getDecommissionPoolMethod;
    if ((getDecommissionPoolMethod = MsgGrpc.getDecommissionPoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDecommissionPoolMethod = MsgGrpc.getDecommissionPoolMethod) == null) {
          MsgGrpc.getDecommissionPoolMethod = getDecommissionPoolMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgDecommissionPool, sifnode.clp.v1.Tx.MsgDecommissionPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DecommissionPool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgDecommissionPool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgDecommissionPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DecommissionPool"))
              .build();
        }
      }
    }
    return getDecommissionPoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgUnlockLiquidityRequest,
      sifnode.clp.v1.Tx.MsgUnlockLiquidityResponse> getUnlockLiquidityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnlockLiquidity",
      requestType = sifnode.clp.v1.Tx.MsgUnlockLiquidityRequest.class,
      responseType = sifnode.clp.v1.Tx.MsgUnlockLiquidityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgUnlockLiquidityRequest,
      sifnode.clp.v1.Tx.MsgUnlockLiquidityResponse> getUnlockLiquidityMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgUnlockLiquidityRequest, sifnode.clp.v1.Tx.MsgUnlockLiquidityResponse> getUnlockLiquidityMethod;
    if ((getUnlockLiquidityMethod = MsgGrpc.getUnlockLiquidityMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUnlockLiquidityMethod = MsgGrpc.getUnlockLiquidityMethod) == null) {
          MsgGrpc.getUnlockLiquidityMethod = getUnlockLiquidityMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgUnlockLiquidityRequest, sifnode.clp.v1.Tx.MsgUnlockLiquidityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnlockLiquidity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgUnlockLiquidityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgUnlockLiquidityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UnlockLiquidity"))
              .build();
        }
      }
    }
    return getUnlockLiquidityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgUpdateRewardsParamsRequest,
      sifnode.clp.v1.Tx.MsgUpdateRewardsParamsResponse> getUpdateRewardsParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateRewardsParams",
      requestType = sifnode.clp.v1.Tx.MsgUpdateRewardsParamsRequest.class,
      responseType = sifnode.clp.v1.Tx.MsgUpdateRewardsParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgUpdateRewardsParamsRequest,
      sifnode.clp.v1.Tx.MsgUpdateRewardsParamsResponse> getUpdateRewardsParamsMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgUpdateRewardsParamsRequest, sifnode.clp.v1.Tx.MsgUpdateRewardsParamsResponse> getUpdateRewardsParamsMethod;
    if ((getUpdateRewardsParamsMethod = MsgGrpc.getUpdateRewardsParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateRewardsParamsMethod = MsgGrpc.getUpdateRewardsParamsMethod) == null) {
          MsgGrpc.getUpdateRewardsParamsMethod = getUpdateRewardsParamsMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgUpdateRewardsParamsRequest, sifnode.clp.v1.Tx.MsgUpdateRewardsParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateRewardsParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgUpdateRewardsParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgUpdateRewardsParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateRewardsParams"))
              .build();
        }
      }
    }
    return getUpdateRewardsParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgAddRewardPeriodRequest,
      sifnode.clp.v1.Tx.MsgAddRewardPeriodResponse> getAddRewardPeriodMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddRewardPeriod",
      requestType = sifnode.clp.v1.Tx.MsgAddRewardPeriodRequest.class,
      responseType = sifnode.clp.v1.Tx.MsgAddRewardPeriodResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgAddRewardPeriodRequest,
      sifnode.clp.v1.Tx.MsgAddRewardPeriodResponse> getAddRewardPeriodMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgAddRewardPeriodRequest, sifnode.clp.v1.Tx.MsgAddRewardPeriodResponse> getAddRewardPeriodMethod;
    if ((getAddRewardPeriodMethod = MsgGrpc.getAddRewardPeriodMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddRewardPeriodMethod = MsgGrpc.getAddRewardPeriodMethod) == null) {
          MsgGrpc.getAddRewardPeriodMethod = getAddRewardPeriodMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgAddRewardPeriodRequest, sifnode.clp.v1.Tx.MsgAddRewardPeriodResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddRewardPeriod"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgAddRewardPeriodRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgAddRewardPeriodResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddRewardPeriod"))
              .build();
        }
      }
    }
    return getAddRewardPeriodMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgModifyPmtpRates,
      sifnode.clp.v1.Tx.MsgModifyPmtpRatesResponse> getModifyPmtpRatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ModifyPmtpRates",
      requestType = sifnode.clp.v1.Tx.MsgModifyPmtpRates.class,
      responseType = sifnode.clp.v1.Tx.MsgModifyPmtpRatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgModifyPmtpRates,
      sifnode.clp.v1.Tx.MsgModifyPmtpRatesResponse> getModifyPmtpRatesMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgModifyPmtpRates, sifnode.clp.v1.Tx.MsgModifyPmtpRatesResponse> getModifyPmtpRatesMethod;
    if ((getModifyPmtpRatesMethod = MsgGrpc.getModifyPmtpRatesMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getModifyPmtpRatesMethod = MsgGrpc.getModifyPmtpRatesMethod) == null) {
          MsgGrpc.getModifyPmtpRatesMethod = getModifyPmtpRatesMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgModifyPmtpRates, sifnode.clp.v1.Tx.MsgModifyPmtpRatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ModifyPmtpRates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgModifyPmtpRates.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgModifyPmtpRatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ModifyPmtpRates"))
              .build();
        }
      }
    }
    return getModifyPmtpRatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgUpdatePmtpParams,
      sifnode.clp.v1.Tx.MsgUpdatePmtpParamsResponse> getUpdatePmtpParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdatePmtpParams",
      requestType = sifnode.clp.v1.Tx.MsgUpdatePmtpParams.class,
      responseType = sifnode.clp.v1.Tx.MsgUpdatePmtpParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgUpdatePmtpParams,
      sifnode.clp.v1.Tx.MsgUpdatePmtpParamsResponse> getUpdatePmtpParamsMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgUpdatePmtpParams, sifnode.clp.v1.Tx.MsgUpdatePmtpParamsResponse> getUpdatePmtpParamsMethod;
    if ((getUpdatePmtpParamsMethod = MsgGrpc.getUpdatePmtpParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdatePmtpParamsMethod = MsgGrpc.getUpdatePmtpParamsMethod) == null) {
          MsgGrpc.getUpdatePmtpParamsMethod = getUpdatePmtpParamsMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgUpdatePmtpParams, sifnode.clp.v1.Tx.MsgUpdatePmtpParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdatePmtpParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgUpdatePmtpParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgUpdatePmtpParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdatePmtpParams"))
              .build();
        }
      }
    }
    return getUpdatePmtpParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgUpdateStakingRewardParams,
      sifnode.clp.v1.Tx.MsgUpdateStakingRewardParamsResponse> getUpdateStakingRewardParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateStakingRewardParams",
      requestType = sifnode.clp.v1.Tx.MsgUpdateStakingRewardParams.class,
      responseType = sifnode.clp.v1.Tx.MsgUpdateStakingRewardParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgUpdateStakingRewardParams,
      sifnode.clp.v1.Tx.MsgUpdateStakingRewardParamsResponse> getUpdateStakingRewardParamsMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgUpdateStakingRewardParams, sifnode.clp.v1.Tx.MsgUpdateStakingRewardParamsResponse> getUpdateStakingRewardParamsMethod;
    if ((getUpdateStakingRewardParamsMethod = MsgGrpc.getUpdateStakingRewardParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateStakingRewardParamsMethod = MsgGrpc.getUpdateStakingRewardParamsMethod) == null) {
          MsgGrpc.getUpdateStakingRewardParamsMethod = getUpdateStakingRewardParamsMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgUpdateStakingRewardParams, sifnode.clp.v1.Tx.MsgUpdateStakingRewardParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateStakingRewardParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgUpdateStakingRewardParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgUpdateStakingRewardParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateStakingRewardParams"))
              .build();
        }
      }
    }
    return getUpdateStakingRewardParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgSetSymmetryThreshold,
      sifnode.clp.v1.Tx.MsgSetSymmetryThresholdResponse> getSetSymmetryThresholdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetSymmetryThreshold",
      requestType = sifnode.clp.v1.Tx.MsgSetSymmetryThreshold.class,
      responseType = sifnode.clp.v1.Tx.MsgSetSymmetryThresholdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgSetSymmetryThreshold,
      sifnode.clp.v1.Tx.MsgSetSymmetryThresholdResponse> getSetSymmetryThresholdMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgSetSymmetryThreshold, sifnode.clp.v1.Tx.MsgSetSymmetryThresholdResponse> getSetSymmetryThresholdMethod;
    if ((getSetSymmetryThresholdMethod = MsgGrpc.getSetSymmetryThresholdMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetSymmetryThresholdMethod = MsgGrpc.getSetSymmetryThresholdMethod) == null) {
          MsgGrpc.getSetSymmetryThresholdMethod = getSetSymmetryThresholdMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgSetSymmetryThreshold, sifnode.clp.v1.Tx.MsgSetSymmetryThresholdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetSymmetryThreshold"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgSetSymmetryThreshold.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgSetSymmetryThresholdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetSymmetryThreshold"))
              .build();
        }
      }
    }
    return getSetSymmetryThresholdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgCancelUnlock,
      sifnode.clp.v1.Tx.MsgCancelUnlockResponse> getCancelUnlockLiquidityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelUnlockLiquidity",
      requestType = sifnode.clp.v1.Tx.MsgCancelUnlock.class,
      responseType = sifnode.clp.v1.Tx.MsgCancelUnlockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgCancelUnlock,
      sifnode.clp.v1.Tx.MsgCancelUnlockResponse> getCancelUnlockLiquidityMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgCancelUnlock, sifnode.clp.v1.Tx.MsgCancelUnlockResponse> getCancelUnlockLiquidityMethod;
    if ((getCancelUnlockLiquidityMethod = MsgGrpc.getCancelUnlockLiquidityMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelUnlockLiquidityMethod = MsgGrpc.getCancelUnlockLiquidityMethod) == null) {
          MsgGrpc.getCancelUnlockLiquidityMethod = getCancelUnlockLiquidityMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgCancelUnlock, sifnode.clp.v1.Tx.MsgCancelUnlockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelUnlockLiquidity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgCancelUnlock.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgCancelUnlockResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelUnlockLiquidity"))
              .build();
        }
      }
    }
    return getCancelUnlockLiquidityMethod;
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
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     */
    public void removeLiquidity(sifnode.clp.v1.Tx.MsgRemoveLiquidity request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveLiquidityMethod(), responseObserver);
    }

    /**
     */
    public void removeLiquidityUnits(sifnode.clp.v1.Tx.MsgRemoveLiquidityUnits request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgRemoveLiquidityUnitsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveLiquidityUnitsMethod(), responseObserver);
    }

    /**
     */
    public void createPool(sifnode.clp.v1.Tx.MsgCreatePool request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgCreatePoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreatePoolMethod(), responseObserver);
    }

    /**
     */
    public void addLiquidity(sifnode.clp.v1.Tx.MsgAddLiquidity request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgAddLiquidityResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddLiquidityMethod(), responseObserver);
    }

    /**
     */
    public void swap(sifnode.clp.v1.Tx.MsgSwap request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgSwapResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwapMethod(), responseObserver);
    }

    /**
     */
    public void decommissionPool(sifnode.clp.v1.Tx.MsgDecommissionPool request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgDecommissionPoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDecommissionPoolMethod(), responseObserver);
    }

    /**
     */
    public void unlockLiquidity(sifnode.clp.v1.Tx.MsgUnlockLiquidityRequest request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgUnlockLiquidityResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnlockLiquidityMethod(), responseObserver);
    }

    /**
     */
    public void updateRewardsParams(sifnode.clp.v1.Tx.MsgUpdateRewardsParamsRequest request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgUpdateRewardsParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateRewardsParamsMethod(), responseObserver);
    }

    /**
     */
    public void addRewardPeriod(sifnode.clp.v1.Tx.MsgAddRewardPeriodRequest request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgAddRewardPeriodResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddRewardPeriodMethod(), responseObserver);
    }

    /**
     */
    public void modifyPmtpRates(sifnode.clp.v1.Tx.MsgModifyPmtpRates request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgModifyPmtpRatesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getModifyPmtpRatesMethod(), responseObserver);
    }

    /**
     */
    public void updatePmtpParams(sifnode.clp.v1.Tx.MsgUpdatePmtpParams request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgUpdatePmtpParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdatePmtpParamsMethod(), responseObserver);
    }

    /**
     */
    public void updateStakingRewardParams(sifnode.clp.v1.Tx.MsgUpdateStakingRewardParams request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgUpdateStakingRewardParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateStakingRewardParamsMethod(), responseObserver);
    }

    /**
     */
    public void setSymmetryThreshold(sifnode.clp.v1.Tx.MsgSetSymmetryThreshold request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgSetSymmetryThresholdResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetSymmetryThresholdMethod(), responseObserver);
    }

    /**
     */
    public void cancelUnlockLiquidity(sifnode.clp.v1.Tx.MsgCancelUnlock request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgCancelUnlockResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelUnlockLiquidityMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRemoveLiquidityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgRemoveLiquidity,
                sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse>(
                  this, METHODID_REMOVE_LIQUIDITY)))
          .addMethod(
            getRemoveLiquidityUnitsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgRemoveLiquidityUnits,
                sifnode.clp.v1.Tx.MsgRemoveLiquidityUnitsResponse>(
                  this, METHODID_REMOVE_LIQUIDITY_UNITS)))
          .addMethod(
            getCreatePoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgCreatePool,
                sifnode.clp.v1.Tx.MsgCreatePoolResponse>(
                  this, METHODID_CREATE_POOL)))
          .addMethod(
            getAddLiquidityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgAddLiquidity,
                sifnode.clp.v1.Tx.MsgAddLiquidityResponse>(
                  this, METHODID_ADD_LIQUIDITY)))
          .addMethod(
            getSwapMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgSwap,
                sifnode.clp.v1.Tx.MsgSwapResponse>(
                  this, METHODID_SWAP)))
          .addMethod(
            getDecommissionPoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgDecommissionPool,
                sifnode.clp.v1.Tx.MsgDecommissionPoolResponse>(
                  this, METHODID_DECOMMISSION_POOL)))
          .addMethod(
            getUnlockLiquidityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgUnlockLiquidityRequest,
                sifnode.clp.v1.Tx.MsgUnlockLiquidityResponse>(
                  this, METHODID_UNLOCK_LIQUIDITY)))
          .addMethod(
            getUpdateRewardsParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgUpdateRewardsParamsRequest,
                sifnode.clp.v1.Tx.MsgUpdateRewardsParamsResponse>(
                  this, METHODID_UPDATE_REWARDS_PARAMS)))
          .addMethod(
            getAddRewardPeriodMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgAddRewardPeriodRequest,
                sifnode.clp.v1.Tx.MsgAddRewardPeriodResponse>(
                  this, METHODID_ADD_REWARD_PERIOD)))
          .addMethod(
            getModifyPmtpRatesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgModifyPmtpRates,
                sifnode.clp.v1.Tx.MsgModifyPmtpRatesResponse>(
                  this, METHODID_MODIFY_PMTP_RATES)))
          .addMethod(
            getUpdatePmtpParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgUpdatePmtpParams,
                sifnode.clp.v1.Tx.MsgUpdatePmtpParamsResponse>(
                  this, METHODID_UPDATE_PMTP_PARAMS)))
          .addMethod(
            getUpdateStakingRewardParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgUpdateStakingRewardParams,
                sifnode.clp.v1.Tx.MsgUpdateStakingRewardParamsResponse>(
                  this, METHODID_UPDATE_STAKING_REWARD_PARAMS)))
          .addMethod(
            getSetSymmetryThresholdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgSetSymmetryThreshold,
                sifnode.clp.v1.Tx.MsgSetSymmetryThresholdResponse>(
                  this, METHODID_SET_SYMMETRY_THRESHOLD)))
          .addMethod(
            getCancelUnlockLiquidityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgCancelUnlock,
                sifnode.clp.v1.Tx.MsgCancelUnlockResponse>(
                  this, METHODID_CANCEL_UNLOCK_LIQUIDITY)))
          .build();
    }
  }

  /**
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
     */
    public void removeLiquidity(sifnode.clp.v1.Tx.MsgRemoveLiquidity request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveLiquidityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeLiquidityUnits(sifnode.clp.v1.Tx.MsgRemoveLiquidityUnits request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgRemoveLiquidityUnitsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveLiquidityUnitsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createPool(sifnode.clp.v1.Tx.MsgCreatePool request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgCreatePoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreatePoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addLiquidity(sifnode.clp.v1.Tx.MsgAddLiquidity request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgAddLiquidityResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddLiquidityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void swap(sifnode.clp.v1.Tx.MsgSwap request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgSwapResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwapMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void decommissionPool(sifnode.clp.v1.Tx.MsgDecommissionPool request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgDecommissionPoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDecommissionPoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void unlockLiquidity(sifnode.clp.v1.Tx.MsgUnlockLiquidityRequest request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgUnlockLiquidityResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnlockLiquidityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateRewardsParams(sifnode.clp.v1.Tx.MsgUpdateRewardsParamsRequest request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgUpdateRewardsParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateRewardsParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addRewardPeriod(sifnode.clp.v1.Tx.MsgAddRewardPeriodRequest request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgAddRewardPeriodResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddRewardPeriodMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void modifyPmtpRates(sifnode.clp.v1.Tx.MsgModifyPmtpRates request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgModifyPmtpRatesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getModifyPmtpRatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updatePmtpParams(sifnode.clp.v1.Tx.MsgUpdatePmtpParams request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgUpdatePmtpParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdatePmtpParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateStakingRewardParams(sifnode.clp.v1.Tx.MsgUpdateStakingRewardParams request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgUpdateStakingRewardParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateStakingRewardParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setSymmetryThreshold(sifnode.clp.v1.Tx.MsgSetSymmetryThreshold request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgSetSymmetryThresholdResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetSymmetryThresholdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void cancelUnlockLiquidity(sifnode.clp.v1.Tx.MsgCancelUnlock request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgCancelUnlockResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelUnlockLiquidityMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
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
     */
    public sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse removeLiquidity(sifnode.clp.v1.Tx.MsgRemoveLiquidity request) {
      return blockingUnaryCall(
          getChannel(), getRemoveLiquidityMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Tx.MsgRemoveLiquidityUnitsResponse removeLiquidityUnits(sifnode.clp.v1.Tx.MsgRemoveLiquidityUnits request) {
      return blockingUnaryCall(
          getChannel(), getRemoveLiquidityUnitsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Tx.MsgCreatePoolResponse createPool(sifnode.clp.v1.Tx.MsgCreatePool request) {
      return blockingUnaryCall(
          getChannel(), getCreatePoolMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Tx.MsgAddLiquidityResponse addLiquidity(sifnode.clp.v1.Tx.MsgAddLiquidity request) {
      return blockingUnaryCall(
          getChannel(), getAddLiquidityMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Tx.MsgSwapResponse swap(sifnode.clp.v1.Tx.MsgSwap request) {
      return blockingUnaryCall(
          getChannel(), getSwapMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Tx.MsgDecommissionPoolResponse decommissionPool(sifnode.clp.v1.Tx.MsgDecommissionPool request) {
      return blockingUnaryCall(
          getChannel(), getDecommissionPoolMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Tx.MsgUnlockLiquidityResponse unlockLiquidity(sifnode.clp.v1.Tx.MsgUnlockLiquidityRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnlockLiquidityMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Tx.MsgUpdateRewardsParamsResponse updateRewardsParams(sifnode.clp.v1.Tx.MsgUpdateRewardsParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpdateRewardsParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Tx.MsgAddRewardPeriodResponse addRewardPeriod(sifnode.clp.v1.Tx.MsgAddRewardPeriodRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddRewardPeriodMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Tx.MsgModifyPmtpRatesResponse modifyPmtpRates(sifnode.clp.v1.Tx.MsgModifyPmtpRates request) {
      return blockingUnaryCall(
          getChannel(), getModifyPmtpRatesMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Tx.MsgUpdatePmtpParamsResponse updatePmtpParams(sifnode.clp.v1.Tx.MsgUpdatePmtpParams request) {
      return blockingUnaryCall(
          getChannel(), getUpdatePmtpParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Tx.MsgUpdateStakingRewardParamsResponse updateStakingRewardParams(sifnode.clp.v1.Tx.MsgUpdateStakingRewardParams request) {
      return blockingUnaryCall(
          getChannel(), getUpdateStakingRewardParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Tx.MsgSetSymmetryThresholdResponse setSymmetryThreshold(sifnode.clp.v1.Tx.MsgSetSymmetryThreshold request) {
      return blockingUnaryCall(
          getChannel(), getSetSymmetryThresholdMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Tx.MsgCancelUnlockResponse cancelUnlockLiquidity(sifnode.clp.v1.Tx.MsgCancelUnlock request) {
      return blockingUnaryCall(
          getChannel(), getCancelUnlockLiquidityMethod(), getCallOptions(), request);
    }
  }

  /**
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
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse> removeLiquidity(
        sifnode.clp.v1.Tx.MsgRemoveLiquidity request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveLiquidityMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgRemoveLiquidityUnitsResponse> removeLiquidityUnits(
        sifnode.clp.v1.Tx.MsgRemoveLiquidityUnits request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveLiquidityUnitsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgCreatePoolResponse> createPool(
        sifnode.clp.v1.Tx.MsgCreatePool request) {
      return futureUnaryCall(
          getChannel().newCall(getCreatePoolMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgAddLiquidityResponse> addLiquidity(
        sifnode.clp.v1.Tx.MsgAddLiquidity request) {
      return futureUnaryCall(
          getChannel().newCall(getAddLiquidityMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgSwapResponse> swap(
        sifnode.clp.v1.Tx.MsgSwap request) {
      return futureUnaryCall(
          getChannel().newCall(getSwapMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgDecommissionPoolResponse> decommissionPool(
        sifnode.clp.v1.Tx.MsgDecommissionPool request) {
      return futureUnaryCall(
          getChannel().newCall(getDecommissionPoolMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgUnlockLiquidityResponse> unlockLiquidity(
        sifnode.clp.v1.Tx.MsgUnlockLiquidityRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnlockLiquidityMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgUpdateRewardsParamsResponse> updateRewardsParams(
        sifnode.clp.v1.Tx.MsgUpdateRewardsParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateRewardsParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgAddRewardPeriodResponse> addRewardPeriod(
        sifnode.clp.v1.Tx.MsgAddRewardPeriodRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddRewardPeriodMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgModifyPmtpRatesResponse> modifyPmtpRates(
        sifnode.clp.v1.Tx.MsgModifyPmtpRates request) {
      return futureUnaryCall(
          getChannel().newCall(getModifyPmtpRatesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgUpdatePmtpParamsResponse> updatePmtpParams(
        sifnode.clp.v1.Tx.MsgUpdatePmtpParams request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdatePmtpParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgUpdateStakingRewardParamsResponse> updateStakingRewardParams(
        sifnode.clp.v1.Tx.MsgUpdateStakingRewardParams request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateStakingRewardParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgSetSymmetryThresholdResponse> setSymmetryThreshold(
        sifnode.clp.v1.Tx.MsgSetSymmetryThreshold request) {
      return futureUnaryCall(
          getChannel().newCall(getSetSymmetryThresholdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgCancelUnlockResponse> cancelUnlockLiquidity(
        sifnode.clp.v1.Tx.MsgCancelUnlock request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelUnlockLiquidityMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REMOVE_LIQUIDITY = 0;
  private static final int METHODID_REMOVE_LIQUIDITY_UNITS = 1;
  private static final int METHODID_CREATE_POOL = 2;
  private static final int METHODID_ADD_LIQUIDITY = 3;
  private static final int METHODID_SWAP = 4;
  private static final int METHODID_DECOMMISSION_POOL = 5;
  private static final int METHODID_UNLOCK_LIQUIDITY = 6;
  private static final int METHODID_UPDATE_REWARDS_PARAMS = 7;
  private static final int METHODID_ADD_REWARD_PERIOD = 8;
  private static final int METHODID_MODIFY_PMTP_RATES = 9;
  private static final int METHODID_UPDATE_PMTP_PARAMS = 10;
  private static final int METHODID_UPDATE_STAKING_REWARD_PARAMS = 11;
  private static final int METHODID_SET_SYMMETRY_THRESHOLD = 12;
  private static final int METHODID_CANCEL_UNLOCK_LIQUIDITY = 13;

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
        case METHODID_REMOVE_LIQUIDITY:
          serviceImpl.removeLiquidity((sifnode.clp.v1.Tx.MsgRemoveLiquidity) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse>) responseObserver);
          break;
        case METHODID_REMOVE_LIQUIDITY_UNITS:
          serviceImpl.removeLiquidityUnits((sifnode.clp.v1.Tx.MsgRemoveLiquidityUnits) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgRemoveLiquidityUnitsResponse>) responseObserver);
          break;
        case METHODID_CREATE_POOL:
          serviceImpl.createPool((sifnode.clp.v1.Tx.MsgCreatePool) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgCreatePoolResponse>) responseObserver);
          break;
        case METHODID_ADD_LIQUIDITY:
          serviceImpl.addLiquidity((sifnode.clp.v1.Tx.MsgAddLiquidity) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgAddLiquidityResponse>) responseObserver);
          break;
        case METHODID_SWAP:
          serviceImpl.swap((sifnode.clp.v1.Tx.MsgSwap) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgSwapResponse>) responseObserver);
          break;
        case METHODID_DECOMMISSION_POOL:
          serviceImpl.decommissionPool((sifnode.clp.v1.Tx.MsgDecommissionPool) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgDecommissionPoolResponse>) responseObserver);
          break;
        case METHODID_UNLOCK_LIQUIDITY:
          serviceImpl.unlockLiquidity((sifnode.clp.v1.Tx.MsgUnlockLiquidityRequest) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgUnlockLiquidityResponse>) responseObserver);
          break;
        case METHODID_UPDATE_REWARDS_PARAMS:
          serviceImpl.updateRewardsParams((sifnode.clp.v1.Tx.MsgUpdateRewardsParamsRequest) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgUpdateRewardsParamsResponse>) responseObserver);
          break;
        case METHODID_ADD_REWARD_PERIOD:
          serviceImpl.addRewardPeriod((sifnode.clp.v1.Tx.MsgAddRewardPeriodRequest) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgAddRewardPeriodResponse>) responseObserver);
          break;
        case METHODID_MODIFY_PMTP_RATES:
          serviceImpl.modifyPmtpRates((sifnode.clp.v1.Tx.MsgModifyPmtpRates) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgModifyPmtpRatesResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PMTP_PARAMS:
          serviceImpl.updatePmtpParams((sifnode.clp.v1.Tx.MsgUpdatePmtpParams) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgUpdatePmtpParamsResponse>) responseObserver);
          break;
        case METHODID_UPDATE_STAKING_REWARD_PARAMS:
          serviceImpl.updateStakingRewardParams((sifnode.clp.v1.Tx.MsgUpdateStakingRewardParams) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgUpdateStakingRewardParamsResponse>) responseObserver);
          break;
        case METHODID_SET_SYMMETRY_THRESHOLD:
          serviceImpl.setSymmetryThreshold((sifnode.clp.v1.Tx.MsgSetSymmetryThreshold) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgSetSymmetryThresholdResponse>) responseObserver);
          break;
        case METHODID_CANCEL_UNLOCK_LIQUIDITY:
          serviceImpl.cancelUnlockLiquidity((sifnode.clp.v1.Tx.MsgCancelUnlock) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgCancelUnlockResponse>) responseObserver);
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
      return sifnode.clp.v1.Tx.getDescriptor();
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
              .addMethod(getRemoveLiquidityMethod())
              .addMethod(getRemoveLiquidityUnitsMethod())
              .addMethod(getCreatePoolMethod())
              .addMethod(getAddLiquidityMethod())
              .addMethod(getSwapMethod())
              .addMethod(getDecommissionPoolMethod())
              .addMethod(getUnlockLiquidityMethod())
              .addMethod(getUpdateRewardsParamsMethod())
              .addMethod(getAddRewardPeriodMethod())
              .addMethod(getModifyPmtpRatesMethod())
              .addMethod(getUpdatePmtpParamsMethod())
              .addMethod(getUpdateStakingRewardParamsMethod())
              .addMethod(getSetSymmetryThresholdMethod())
              .addMethod(getCancelUnlockLiquidityMethod())
              .build();
        }
      }
    }
    return result;
  }
}
