package shentu.shield.v1alpha1;

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
 * Msg defines the shield Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: shentu/shield/v1alpha1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "shentu.shield.v1alpha1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgCreatePool,
      shentu.shield.v1alpha1.Tx.MsgCreatePoolResponse> getCreatePoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePool",
      requestType = shentu.shield.v1alpha1.Tx.MsgCreatePool.class,
      responseType = shentu.shield.v1alpha1.Tx.MsgCreatePoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgCreatePool,
      shentu.shield.v1alpha1.Tx.MsgCreatePoolResponse> getCreatePoolMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgCreatePool, shentu.shield.v1alpha1.Tx.MsgCreatePoolResponse> getCreatePoolMethod;
    if ((getCreatePoolMethod = MsgGrpc.getCreatePoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreatePoolMethod = MsgGrpc.getCreatePoolMethod) == null) {
          MsgGrpc.getCreatePoolMethod = getCreatePoolMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.Tx.MsgCreatePool, shentu.shield.v1alpha1.Tx.MsgCreatePoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgCreatePool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgCreatePoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreatePool"))
              .build();
        }
      }
    }
    return getCreatePoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgUpdatePool,
      shentu.shield.v1alpha1.Tx.MsgUpdatePoolResponse> getUpdatePoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdatePool",
      requestType = shentu.shield.v1alpha1.Tx.MsgUpdatePool.class,
      responseType = shentu.shield.v1alpha1.Tx.MsgUpdatePoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgUpdatePool,
      shentu.shield.v1alpha1.Tx.MsgUpdatePoolResponse> getUpdatePoolMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgUpdatePool, shentu.shield.v1alpha1.Tx.MsgUpdatePoolResponse> getUpdatePoolMethod;
    if ((getUpdatePoolMethod = MsgGrpc.getUpdatePoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdatePoolMethod = MsgGrpc.getUpdatePoolMethod) == null) {
          MsgGrpc.getUpdatePoolMethod = getUpdatePoolMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.Tx.MsgUpdatePool, shentu.shield.v1alpha1.Tx.MsgUpdatePoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdatePool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgUpdatePool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgUpdatePoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdatePool"))
              .build();
        }
      }
    }
    return getUpdatePoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgPausePool,
      shentu.shield.v1alpha1.Tx.MsgPausePoolResponse> getPausePoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PausePool",
      requestType = shentu.shield.v1alpha1.Tx.MsgPausePool.class,
      responseType = shentu.shield.v1alpha1.Tx.MsgPausePoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgPausePool,
      shentu.shield.v1alpha1.Tx.MsgPausePoolResponse> getPausePoolMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgPausePool, shentu.shield.v1alpha1.Tx.MsgPausePoolResponse> getPausePoolMethod;
    if ((getPausePoolMethod = MsgGrpc.getPausePoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getPausePoolMethod = MsgGrpc.getPausePoolMethod) == null) {
          MsgGrpc.getPausePoolMethod = getPausePoolMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.Tx.MsgPausePool, shentu.shield.v1alpha1.Tx.MsgPausePoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PausePool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgPausePool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgPausePoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("PausePool"))
              .build();
        }
      }
    }
    return getPausePoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgResumePool,
      shentu.shield.v1alpha1.Tx.MsgResumePoolResponse> getResumePoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ResumePool",
      requestType = shentu.shield.v1alpha1.Tx.MsgResumePool.class,
      responseType = shentu.shield.v1alpha1.Tx.MsgResumePoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgResumePool,
      shentu.shield.v1alpha1.Tx.MsgResumePoolResponse> getResumePoolMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgResumePool, shentu.shield.v1alpha1.Tx.MsgResumePoolResponse> getResumePoolMethod;
    if ((getResumePoolMethod = MsgGrpc.getResumePoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getResumePoolMethod = MsgGrpc.getResumePoolMethod) == null) {
          MsgGrpc.getResumePoolMethod = getResumePoolMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.Tx.MsgResumePool, shentu.shield.v1alpha1.Tx.MsgResumePoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ResumePool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgResumePool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgResumePoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ResumePool"))
              .build();
        }
      }
    }
    return getResumePoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgDepositCollateral,
      shentu.shield.v1alpha1.Tx.MsgDepositCollateralResponse> getDepositCollateralMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DepositCollateral",
      requestType = shentu.shield.v1alpha1.Tx.MsgDepositCollateral.class,
      responseType = shentu.shield.v1alpha1.Tx.MsgDepositCollateralResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgDepositCollateral,
      shentu.shield.v1alpha1.Tx.MsgDepositCollateralResponse> getDepositCollateralMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgDepositCollateral, shentu.shield.v1alpha1.Tx.MsgDepositCollateralResponse> getDepositCollateralMethod;
    if ((getDepositCollateralMethod = MsgGrpc.getDepositCollateralMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDepositCollateralMethod = MsgGrpc.getDepositCollateralMethod) == null) {
          MsgGrpc.getDepositCollateralMethod = getDepositCollateralMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.Tx.MsgDepositCollateral, shentu.shield.v1alpha1.Tx.MsgDepositCollateralResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DepositCollateral"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgDepositCollateral.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgDepositCollateralResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DepositCollateral"))
              .build();
        }
      }
    }
    return getDepositCollateralMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgWithdrawCollateral,
      shentu.shield.v1alpha1.Tx.MsgWithdrawCollateralResponse> getWithdrawCollateralMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawCollateral",
      requestType = shentu.shield.v1alpha1.Tx.MsgWithdrawCollateral.class,
      responseType = shentu.shield.v1alpha1.Tx.MsgWithdrawCollateralResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgWithdrawCollateral,
      shentu.shield.v1alpha1.Tx.MsgWithdrawCollateralResponse> getWithdrawCollateralMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgWithdrawCollateral, shentu.shield.v1alpha1.Tx.MsgWithdrawCollateralResponse> getWithdrawCollateralMethod;
    if ((getWithdrawCollateralMethod = MsgGrpc.getWithdrawCollateralMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawCollateralMethod = MsgGrpc.getWithdrawCollateralMethod) == null) {
          MsgGrpc.getWithdrawCollateralMethod = getWithdrawCollateralMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.Tx.MsgWithdrawCollateral, shentu.shield.v1alpha1.Tx.MsgWithdrawCollateralResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawCollateral"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgWithdrawCollateral.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgWithdrawCollateralResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawCollateral"))
              .build();
        }
      }
    }
    return getWithdrawCollateralMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgWithdrawRewards,
      shentu.shield.v1alpha1.Tx.MsgWithdrawRewardsResponse> getWithdrawRewardsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawRewards",
      requestType = shentu.shield.v1alpha1.Tx.MsgWithdrawRewards.class,
      responseType = shentu.shield.v1alpha1.Tx.MsgWithdrawRewardsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgWithdrawRewards,
      shentu.shield.v1alpha1.Tx.MsgWithdrawRewardsResponse> getWithdrawRewardsMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgWithdrawRewards, shentu.shield.v1alpha1.Tx.MsgWithdrawRewardsResponse> getWithdrawRewardsMethod;
    if ((getWithdrawRewardsMethod = MsgGrpc.getWithdrawRewardsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawRewardsMethod = MsgGrpc.getWithdrawRewardsMethod) == null) {
          MsgGrpc.getWithdrawRewardsMethod = getWithdrawRewardsMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.Tx.MsgWithdrawRewards, shentu.shield.v1alpha1.Tx.MsgWithdrawRewardsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawRewards"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgWithdrawRewards.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgWithdrawRewardsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawRewards"))
              .build();
        }
      }
    }
    return getWithdrawRewardsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewards,
      shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewardsResponse> getWithdrawForeignRewardsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawForeignRewards",
      requestType = shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewards.class,
      responseType = shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewardsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewards,
      shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewardsResponse> getWithdrawForeignRewardsMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewards, shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewardsResponse> getWithdrawForeignRewardsMethod;
    if ((getWithdrawForeignRewardsMethod = MsgGrpc.getWithdrawForeignRewardsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawForeignRewardsMethod = MsgGrpc.getWithdrawForeignRewardsMethod) == null) {
          MsgGrpc.getWithdrawForeignRewardsMethod = getWithdrawForeignRewardsMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewards, shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewardsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawForeignRewards"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewards.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewardsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawForeignRewards"))
              .build();
        }
      }
    }
    return getWithdrawForeignRewardsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgPurchaseShield,
      shentu.shield.v1alpha1.Tx.MsgPurchaseShieldResponse> getPurchaseShieldMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PurchaseShield",
      requestType = shentu.shield.v1alpha1.Tx.MsgPurchaseShield.class,
      responseType = shentu.shield.v1alpha1.Tx.MsgPurchaseShieldResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgPurchaseShield,
      shentu.shield.v1alpha1.Tx.MsgPurchaseShieldResponse> getPurchaseShieldMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgPurchaseShield, shentu.shield.v1alpha1.Tx.MsgPurchaseShieldResponse> getPurchaseShieldMethod;
    if ((getPurchaseShieldMethod = MsgGrpc.getPurchaseShieldMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getPurchaseShieldMethod = MsgGrpc.getPurchaseShieldMethod) == null) {
          MsgGrpc.getPurchaseShieldMethod = getPurchaseShieldMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.Tx.MsgPurchaseShield, shentu.shield.v1alpha1.Tx.MsgPurchaseShieldResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PurchaseShield"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgPurchaseShield.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgPurchaseShieldResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("PurchaseShield"))
              .build();
        }
      }
    }
    return getPurchaseShieldMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursement,
      shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursementResponse> getWithdrawReimbursementMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawReimbursement",
      requestType = shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursement.class,
      responseType = shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursementResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursement,
      shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursementResponse> getWithdrawReimbursementMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursement, shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursementResponse> getWithdrawReimbursementMethod;
    if ((getWithdrawReimbursementMethod = MsgGrpc.getWithdrawReimbursementMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawReimbursementMethod = MsgGrpc.getWithdrawReimbursementMethod) == null) {
          MsgGrpc.getWithdrawReimbursementMethod = getWithdrawReimbursementMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursement, shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursementResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawReimbursement"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursement.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursementResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawReimbursement"))
              .build();
        }
      }
    }
    return getWithdrawReimbursementMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgUpdateSponsor,
      shentu.shield.v1alpha1.Tx.MsgUpdateSponsorResponse> getUpdateSponsorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateSponsor",
      requestType = shentu.shield.v1alpha1.Tx.MsgUpdateSponsor.class,
      responseType = shentu.shield.v1alpha1.Tx.MsgUpdateSponsorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgUpdateSponsor,
      shentu.shield.v1alpha1.Tx.MsgUpdateSponsorResponse> getUpdateSponsorMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgUpdateSponsor, shentu.shield.v1alpha1.Tx.MsgUpdateSponsorResponse> getUpdateSponsorMethod;
    if ((getUpdateSponsorMethod = MsgGrpc.getUpdateSponsorMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateSponsorMethod = MsgGrpc.getUpdateSponsorMethod) == null) {
          MsgGrpc.getUpdateSponsorMethod = getUpdateSponsorMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.Tx.MsgUpdateSponsor, shentu.shield.v1alpha1.Tx.MsgUpdateSponsorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateSponsor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgUpdateSponsor.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgUpdateSponsorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateSponsor"))
              .build();
        }
      }
    }
    return getUpdateSponsorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgStakeForShield,
      shentu.shield.v1alpha1.Tx.MsgStakeForShieldResponse> getStakeForShieldMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StakeForShield",
      requestType = shentu.shield.v1alpha1.Tx.MsgStakeForShield.class,
      responseType = shentu.shield.v1alpha1.Tx.MsgStakeForShieldResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgStakeForShield,
      shentu.shield.v1alpha1.Tx.MsgStakeForShieldResponse> getStakeForShieldMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgStakeForShield, shentu.shield.v1alpha1.Tx.MsgStakeForShieldResponse> getStakeForShieldMethod;
    if ((getStakeForShieldMethod = MsgGrpc.getStakeForShieldMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getStakeForShieldMethod = MsgGrpc.getStakeForShieldMethod) == null) {
          MsgGrpc.getStakeForShieldMethod = getStakeForShieldMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.Tx.MsgStakeForShield, shentu.shield.v1alpha1.Tx.MsgStakeForShieldResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StakeForShield"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgStakeForShield.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgStakeForShieldResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("StakeForShield"))
              .build();
        }
      }
    }
    return getStakeForShieldMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgUnstakeFromShield,
      shentu.shield.v1alpha1.Tx.MsgUnstakeFromShieldResponse> getUnstakeFromShieldMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnstakeFromShield",
      requestType = shentu.shield.v1alpha1.Tx.MsgUnstakeFromShield.class,
      responseType = shentu.shield.v1alpha1.Tx.MsgUnstakeFromShieldResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgUnstakeFromShield,
      shentu.shield.v1alpha1.Tx.MsgUnstakeFromShieldResponse> getUnstakeFromShieldMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.Tx.MsgUnstakeFromShield, shentu.shield.v1alpha1.Tx.MsgUnstakeFromShieldResponse> getUnstakeFromShieldMethod;
    if ((getUnstakeFromShieldMethod = MsgGrpc.getUnstakeFromShieldMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUnstakeFromShieldMethod = MsgGrpc.getUnstakeFromShieldMethod) == null) {
          MsgGrpc.getUnstakeFromShieldMethod = getUnstakeFromShieldMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.Tx.MsgUnstakeFromShield, shentu.shield.v1alpha1.Tx.MsgUnstakeFromShieldResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnstakeFromShield"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgUnstakeFromShield.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.Tx.MsgUnstakeFromShieldResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UnstakeFromShield"))
              .build();
        }
      }
    }
    return getUnstakeFromShieldMethod;
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
   * Msg defines the shield Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     */
    public void createPool(shentu.shield.v1alpha1.Tx.MsgCreatePool request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgCreatePoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreatePoolMethod(), responseObserver);
    }

    /**
     */
    public void updatePool(shentu.shield.v1alpha1.Tx.MsgUpdatePool request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgUpdatePoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdatePoolMethod(), responseObserver);
    }

    /**
     */
    public void pausePool(shentu.shield.v1alpha1.Tx.MsgPausePool request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgPausePoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPausePoolMethod(), responseObserver);
    }

    /**
     */
    public void resumePool(shentu.shield.v1alpha1.Tx.MsgResumePool request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgResumePoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getResumePoolMethod(), responseObserver);
    }

    /**
     */
    public void depositCollateral(shentu.shield.v1alpha1.Tx.MsgDepositCollateral request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgDepositCollateralResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositCollateralMethod(), responseObserver);
    }

    /**
     */
    public void withdrawCollateral(shentu.shield.v1alpha1.Tx.MsgWithdrawCollateral request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgWithdrawCollateralResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawCollateralMethod(), responseObserver);
    }

    /**
     */
    public void withdrawRewards(shentu.shield.v1alpha1.Tx.MsgWithdrawRewards request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgWithdrawRewardsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawRewardsMethod(), responseObserver);
    }

    /**
     */
    public void withdrawForeignRewards(shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewards request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewardsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawForeignRewardsMethod(), responseObserver);
    }

    /**
     */
    public void purchaseShield(shentu.shield.v1alpha1.Tx.MsgPurchaseShield request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgPurchaseShieldResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPurchaseShieldMethod(), responseObserver);
    }

    /**
     */
    public void withdrawReimbursement(shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursement request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursementResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawReimbursementMethod(), responseObserver);
    }

    /**
     */
    public void updateSponsor(shentu.shield.v1alpha1.Tx.MsgUpdateSponsor request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgUpdateSponsorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateSponsorMethod(), responseObserver);
    }

    /**
     */
    public void stakeForShield(shentu.shield.v1alpha1.Tx.MsgStakeForShield request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgStakeForShieldResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStakeForShieldMethod(), responseObserver);
    }

    /**
     */
    public void unstakeFromShield(shentu.shield.v1alpha1.Tx.MsgUnstakeFromShield request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgUnstakeFromShieldResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnstakeFromShieldMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreatePoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.Tx.MsgCreatePool,
                shentu.shield.v1alpha1.Tx.MsgCreatePoolResponse>(
                  this, METHODID_CREATE_POOL)))
          .addMethod(
            getUpdatePoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.Tx.MsgUpdatePool,
                shentu.shield.v1alpha1.Tx.MsgUpdatePoolResponse>(
                  this, METHODID_UPDATE_POOL)))
          .addMethod(
            getPausePoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.Tx.MsgPausePool,
                shentu.shield.v1alpha1.Tx.MsgPausePoolResponse>(
                  this, METHODID_PAUSE_POOL)))
          .addMethod(
            getResumePoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.Tx.MsgResumePool,
                shentu.shield.v1alpha1.Tx.MsgResumePoolResponse>(
                  this, METHODID_RESUME_POOL)))
          .addMethod(
            getDepositCollateralMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.Tx.MsgDepositCollateral,
                shentu.shield.v1alpha1.Tx.MsgDepositCollateralResponse>(
                  this, METHODID_DEPOSIT_COLLATERAL)))
          .addMethod(
            getWithdrawCollateralMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.Tx.MsgWithdrawCollateral,
                shentu.shield.v1alpha1.Tx.MsgWithdrawCollateralResponse>(
                  this, METHODID_WITHDRAW_COLLATERAL)))
          .addMethod(
            getWithdrawRewardsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.Tx.MsgWithdrawRewards,
                shentu.shield.v1alpha1.Tx.MsgWithdrawRewardsResponse>(
                  this, METHODID_WITHDRAW_REWARDS)))
          .addMethod(
            getWithdrawForeignRewardsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewards,
                shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewardsResponse>(
                  this, METHODID_WITHDRAW_FOREIGN_REWARDS)))
          .addMethod(
            getPurchaseShieldMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.Tx.MsgPurchaseShield,
                shentu.shield.v1alpha1.Tx.MsgPurchaseShieldResponse>(
                  this, METHODID_PURCHASE_SHIELD)))
          .addMethod(
            getWithdrawReimbursementMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursement,
                shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursementResponse>(
                  this, METHODID_WITHDRAW_REIMBURSEMENT)))
          .addMethod(
            getUpdateSponsorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.Tx.MsgUpdateSponsor,
                shentu.shield.v1alpha1.Tx.MsgUpdateSponsorResponse>(
                  this, METHODID_UPDATE_SPONSOR)))
          .addMethod(
            getStakeForShieldMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.Tx.MsgStakeForShield,
                shentu.shield.v1alpha1.Tx.MsgStakeForShieldResponse>(
                  this, METHODID_STAKE_FOR_SHIELD)))
          .addMethod(
            getUnstakeFromShieldMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.Tx.MsgUnstakeFromShield,
                shentu.shield.v1alpha1.Tx.MsgUnstakeFromShieldResponse>(
                  this, METHODID_UNSTAKE_FROM_SHIELD)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the shield Msg service.
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
     */
    public void createPool(shentu.shield.v1alpha1.Tx.MsgCreatePool request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgCreatePoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreatePoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updatePool(shentu.shield.v1alpha1.Tx.MsgUpdatePool request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgUpdatePoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdatePoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void pausePool(shentu.shield.v1alpha1.Tx.MsgPausePool request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgPausePoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPausePoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void resumePool(shentu.shield.v1alpha1.Tx.MsgResumePool request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgResumePoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getResumePoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void depositCollateral(shentu.shield.v1alpha1.Tx.MsgDepositCollateral request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgDepositCollateralResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositCollateralMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void withdrawCollateral(shentu.shield.v1alpha1.Tx.MsgWithdrawCollateral request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgWithdrawCollateralResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawCollateralMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void withdrawRewards(shentu.shield.v1alpha1.Tx.MsgWithdrawRewards request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgWithdrawRewardsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawRewardsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void withdrawForeignRewards(shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewards request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewardsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawForeignRewardsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void purchaseShield(shentu.shield.v1alpha1.Tx.MsgPurchaseShield request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgPurchaseShieldResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPurchaseShieldMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void withdrawReimbursement(shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursement request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursementResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawReimbursementMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateSponsor(shentu.shield.v1alpha1.Tx.MsgUpdateSponsor request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgUpdateSponsorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateSponsorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void stakeForShield(shentu.shield.v1alpha1.Tx.MsgStakeForShield request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgStakeForShieldResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStakeForShieldMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void unstakeFromShield(shentu.shield.v1alpha1.Tx.MsgUnstakeFromShield request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgUnstakeFromShieldResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnstakeFromShieldMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the shield Msg service.
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
     */
    public shentu.shield.v1alpha1.Tx.MsgCreatePoolResponse createPool(shentu.shield.v1alpha1.Tx.MsgCreatePool request) {
      return blockingUnaryCall(
          getChannel(), getCreatePoolMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.Tx.MsgUpdatePoolResponse updatePool(shentu.shield.v1alpha1.Tx.MsgUpdatePool request) {
      return blockingUnaryCall(
          getChannel(), getUpdatePoolMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.Tx.MsgPausePoolResponse pausePool(shentu.shield.v1alpha1.Tx.MsgPausePool request) {
      return blockingUnaryCall(
          getChannel(), getPausePoolMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.Tx.MsgResumePoolResponse resumePool(shentu.shield.v1alpha1.Tx.MsgResumePool request) {
      return blockingUnaryCall(
          getChannel(), getResumePoolMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.Tx.MsgDepositCollateralResponse depositCollateral(shentu.shield.v1alpha1.Tx.MsgDepositCollateral request) {
      return blockingUnaryCall(
          getChannel(), getDepositCollateralMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.Tx.MsgWithdrawCollateralResponse withdrawCollateral(shentu.shield.v1alpha1.Tx.MsgWithdrawCollateral request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawCollateralMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.Tx.MsgWithdrawRewardsResponse withdrawRewards(shentu.shield.v1alpha1.Tx.MsgWithdrawRewards request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawRewardsMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewardsResponse withdrawForeignRewards(shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewards request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawForeignRewardsMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.Tx.MsgPurchaseShieldResponse purchaseShield(shentu.shield.v1alpha1.Tx.MsgPurchaseShield request) {
      return blockingUnaryCall(
          getChannel(), getPurchaseShieldMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursementResponse withdrawReimbursement(shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursement request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawReimbursementMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.Tx.MsgUpdateSponsorResponse updateSponsor(shentu.shield.v1alpha1.Tx.MsgUpdateSponsor request) {
      return blockingUnaryCall(
          getChannel(), getUpdateSponsorMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.Tx.MsgStakeForShieldResponse stakeForShield(shentu.shield.v1alpha1.Tx.MsgStakeForShield request) {
      return blockingUnaryCall(
          getChannel(), getStakeForShieldMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.Tx.MsgUnstakeFromShieldResponse unstakeFromShield(shentu.shield.v1alpha1.Tx.MsgUnstakeFromShield request) {
      return blockingUnaryCall(
          getChannel(), getUnstakeFromShieldMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the shield Msg service.
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
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.Tx.MsgCreatePoolResponse> createPool(
        shentu.shield.v1alpha1.Tx.MsgCreatePool request) {
      return futureUnaryCall(
          getChannel().newCall(getCreatePoolMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.Tx.MsgUpdatePoolResponse> updatePool(
        shentu.shield.v1alpha1.Tx.MsgUpdatePool request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdatePoolMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.Tx.MsgPausePoolResponse> pausePool(
        shentu.shield.v1alpha1.Tx.MsgPausePool request) {
      return futureUnaryCall(
          getChannel().newCall(getPausePoolMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.Tx.MsgResumePoolResponse> resumePool(
        shentu.shield.v1alpha1.Tx.MsgResumePool request) {
      return futureUnaryCall(
          getChannel().newCall(getResumePoolMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.Tx.MsgDepositCollateralResponse> depositCollateral(
        shentu.shield.v1alpha1.Tx.MsgDepositCollateral request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositCollateralMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.Tx.MsgWithdrawCollateralResponse> withdrawCollateral(
        shentu.shield.v1alpha1.Tx.MsgWithdrawCollateral request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawCollateralMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.Tx.MsgWithdrawRewardsResponse> withdrawRewards(
        shentu.shield.v1alpha1.Tx.MsgWithdrawRewards request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawRewardsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewardsResponse> withdrawForeignRewards(
        shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewards request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawForeignRewardsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.Tx.MsgPurchaseShieldResponse> purchaseShield(
        shentu.shield.v1alpha1.Tx.MsgPurchaseShield request) {
      return futureUnaryCall(
          getChannel().newCall(getPurchaseShieldMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursementResponse> withdrawReimbursement(
        shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursement request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawReimbursementMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.Tx.MsgUpdateSponsorResponse> updateSponsor(
        shentu.shield.v1alpha1.Tx.MsgUpdateSponsor request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateSponsorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.Tx.MsgStakeForShieldResponse> stakeForShield(
        shentu.shield.v1alpha1.Tx.MsgStakeForShield request) {
      return futureUnaryCall(
          getChannel().newCall(getStakeForShieldMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.Tx.MsgUnstakeFromShieldResponse> unstakeFromShield(
        shentu.shield.v1alpha1.Tx.MsgUnstakeFromShield request) {
      return futureUnaryCall(
          getChannel().newCall(getUnstakeFromShieldMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_POOL = 0;
  private static final int METHODID_UPDATE_POOL = 1;
  private static final int METHODID_PAUSE_POOL = 2;
  private static final int METHODID_RESUME_POOL = 3;
  private static final int METHODID_DEPOSIT_COLLATERAL = 4;
  private static final int METHODID_WITHDRAW_COLLATERAL = 5;
  private static final int METHODID_WITHDRAW_REWARDS = 6;
  private static final int METHODID_WITHDRAW_FOREIGN_REWARDS = 7;
  private static final int METHODID_PURCHASE_SHIELD = 8;
  private static final int METHODID_WITHDRAW_REIMBURSEMENT = 9;
  private static final int METHODID_UPDATE_SPONSOR = 10;
  private static final int METHODID_STAKE_FOR_SHIELD = 11;
  private static final int METHODID_UNSTAKE_FROM_SHIELD = 12;

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
        case METHODID_CREATE_POOL:
          serviceImpl.createPool((shentu.shield.v1alpha1.Tx.MsgCreatePool) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgCreatePoolResponse>) responseObserver);
          break;
        case METHODID_UPDATE_POOL:
          serviceImpl.updatePool((shentu.shield.v1alpha1.Tx.MsgUpdatePool) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgUpdatePoolResponse>) responseObserver);
          break;
        case METHODID_PAUSE_POOL:
          serviceImpl.pausePool((shentu.shield.v1alpha1.Tx.MsgPausePool) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgPausePoolResponse>) responseObserver);
          break;
        case METHODID_RESUME_POOL:
          serviceImpl.resumePool((shentu.shield.v1alpha1.Tx.MsgResumePool) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgResumePoolResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT_COLLATERAL:
          serviceImpl.depositCollateral((shentu.shield.v1alpha1.Tx.MsgDepositCollateral) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgDepositCollateralResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_COLLATERAL:
          serviceImpl.withdrawCollateral((shentu.shield.v1alpha1.Tx.MsgWithdrawCollateral) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgWithdrawCollateralResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_REWARDS:
          serviceImpl.withdrawRewards((shentu.shield.v1alpha1.Tx.MsgWithdrawRewards) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgWithdrawRewardsResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_FOREIGN_REWARDS:
          serviceImpl.withdrawForeignRewards((shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewards) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgWithdrawForeignRewardsResponse>) responseObserver);
          break;
        case METHODID_PURCHASE_SHIELD:
          serviceImpl.purchaseShield((shentu.shield.v1alpha1.Tx.MsgPurchaseShield) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgPurchaseShieldResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_REIMBURSEMENT:
          serviceImpl.withdrawReimbursement((shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursement) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgWithdrawReimbursementResponse>) responseObserver);
          break;
        case METHODID_UPDATE_SPONSOR:
          serviceImpl.updateSponsor((shentu.shield.v1alpha1.Tx.MsgUpdateSponsor) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgUpdateSponsorResponse>) responseObserver);
          break;
        case METHODID_STAKE_FOR_SHIELD:
          serviceImpl.stakeForShield((shentu.shield.v1alpha1.Tx.MsgStakeForShield) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgStakeForShieldResponse>) responseObserver);
          break;
        case METHODID_UNSTAKE_FROM_SHIELD:
          serviceImpl.unstakeFromShield((shentu.shield.v1alpha1.Tx.MsgUnstakeFromShield) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.Tx.MsgUnstakeFromShieldResponse>) responseObserver);
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
      return shentu.shield.v1alpha1.Tx.getDescriptor();
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
              .addMethod(getCreatePoolMethod())
              .addMethod(getUpdatePoolMethod())
              .addMethod(getPausePoolMethod())
              .addMethod(getResumePoolMethod())
              .addMethod(getDepositCollateralMethod())
              .addMethod(getWithdrawCollateralMethod())
              .addMethod(getWithdrawRewardsMethod())
              .addMethod(getWithdrawForeignRewardsMethod())
              .addMethod(getPurchaseShieldMethod())
              .addMethod(getWithdrawReimbursementMethod())
              .addMethod(getUpdateSponsorMethod())
              .addMethod(getStakeForShieldMethod())
              .addMethod(getUnstakeFromShieldMethod())
              .build();
        }
      }
    }
    return result;
  }
}
