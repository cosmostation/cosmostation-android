package umeenetwork.umee.peggy.v1;

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
    comments = "Source: umee/peggy/v1/msgs.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "umeenetwork.umee.peggy.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirm,
      umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirmResponse> getValsetConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetConfirm",
      requestType = umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirm.class,
      responseType = umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirmResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirm,
      umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirmResponse> getValsetConfirmMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirm, umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirmResponse> getValsetConfirmMethod;
    if ((getValsetConfirmMethod = MsgGrpc.getValsetConfirmMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getValsetConfirmMethod = MsgGrpc.getValsetConfirmMethod) == null) {
          MsgGrpc.getValsetConfirmMethod = getValsetConfirmMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirm, umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirmResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetConfirm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirm.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirmResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ValsetConfirm"))
              .build();
        }
      }
    }
    return getValsetConfirmMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgSendToEth,
      umeenetwork.umee.peggy.v1.Msgs.MsgSendToEthResponse> getSendToEthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendToEth",
      requestType = umeenetwork.umee.peggy.v1.Msgs.MsgSendToEth.class,
      responseType = umeenetwork.umee.peggy.v1.Msgs.MsgSendToEthResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgSendToEth,
      umeenetwork.umee.peggy.v1.Msgs.MsgSendToEthResponse> getSendToEthMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgSendToEth, umeenetwork.umee.peggy.v1.Msgs.MsgSendToEthResponse> getSendToEthMethod;
    if ((getSendToEthMethod = MsgGrpc.getSendToEthMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSendToEthMethod = MsgGrpc.getSendToEthMethod) == null) {
          MsgGrpc.getSendToEthMethod = getSendToEthMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.Msgs.MsgSendToEth, umeenetwork.umee.peggy.v1.Msgs.MsgSendToEthResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendToEth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgSendToEth.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgSendToEthResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SendToEth"))
              .build();
        }
      }
    }
    return getSendToEthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatch,
      umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatchResponse> getRequestBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestBatch",
      requestType = umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatch.class,
      responseType = umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatch,
      umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatchResponse> getRequestBatchMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatch, umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatchResponse> getRequestBatchMethod;
    if ((getRequestBatchMethod = MsgGrpc.getRequestBatchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRequestBatchMethod = MsgGrpc.getRequestBatchMethod) == null) {
          MsgGrpc.getRequestBatchMethod = getRequestBatchMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatch, umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RequestBatch"))
              .build();
        }
      }
    }
    return getRequestBatchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatch,
      umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatchResponse> getConfirmBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmBatch",
      requestType = umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatch.class,
      responseType = umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatch,
      umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatchResponse> getConfirmBatchMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatch, umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatchResponse> getConfirmBatchMethod;
    if ((getConfirmBatchMethod = MsgGrpc.getConfirmBatchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConfirmBatchMethod = MsgGrpc.getConfirmBatchMethod) == null) {
          MsgGrpc.getConfirmBatchMethod = getConfirmBatchMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatch, umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConfirmBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConfirmBatch"))
              .build();
        }
      }
    }
    return getConfirmBatchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaim,
      umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaimResponse> getDepositClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DepositClaim",
      requestType = umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaim.class,
      responseType = umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaim,
      umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaimResponse> getDepositClaimMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaim, umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaimResponse> getDepositClaimMethod;
    if ((getDepositClaimMethod = MsgGrpc.getDepositClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDepositClaimMethod = MsgGrpc.getDepositClaimMethod) == null) {
          MsgGrpc.getDepositClaimMethod = getDepositClaimMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaim, umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DepositClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DepositClaim"))
              .build();
        }
      }
    }
    return getDepositClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaim,
      umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaimResponse> getWithdrawClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawClaim",
      requestType = umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaim.class,
      responseType = umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaim,
      umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaimResponse> getWithdrawClaimMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaim, umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaimResponse> getWithdrawClaimMethod;
    if ((getWithdrawClaimMethod = MsgGrpc.getWithdrawClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawClaimMethod = MsgGrpc.getWithdrawClaimMethod) == null) {
          MsgGrpc.getWithdrawClaimMethod = getWithdrawClaimMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaim, umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawClaim"))
              .build();
        }
      }
    }
    return getWithdrawClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaim,
      umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaimResponse> getValsetUpdateClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetUpdateClaim",
      requestType = umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaim.class,
      responseType = umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaim,
      umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaimResponse> getValsetUpdateClaimMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaim, umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaimResponse> getValsetUpdateClaimMethod;
    if ((getValsetUpdateClaimMethod = MsgGrpc.getValsetUpdateClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getValsetUpdateClaimMethod = MsgGrpc.getValsetUpdateClaimMethod) == null) {
          MsgGrpc.getValsetUpdateClaimMethod = getValsetUpdateClaimMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaim, umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetUpdateClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ValsetUpdateClaim"))
              .build();
        }
      }
    }
    return getValsetUpdateClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaim,
      umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaimResponse> getERC20DeployedClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ERC20DeployedClaim",
      requestType = umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaim.class,
      responseType = umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaim,
      umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaimResponse> getERC20DeployedClaimMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaim, umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaimResponse> getERC20DeployedClaimMethod;
    if ((getERC20DeployedClaimMethod = MsgGrpc.getERC20DeployedClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getERC20DeployedClaimMethod = MsgGrpc.getERC20DeployedClaimMethod) == null) {
          MsgGrpc.getERC20DeployedClaimMethod = getERC20DeployedClaimMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaim, umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ERC20DeployedClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ERC20DeployedClaim"))
              .build();
        }
      }
    }
    return getERC20DeployedClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddresses,
      umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddressesResponse> getSetOrchestratorAddressesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetOrchestratorAddresses",
      requestType = umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddresses.class,
      responseType = umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddressesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddresses,
      umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddressesResponse> getSetOrchestratorAddressesMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddresses, umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddressesResponse> getSetOrchestratorAddressesMethod;
    if ((getSetOrchestratorAddressesMethod = MsgGrpc.getSetOrchestratorAddressesMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetOrchestratorAddressesMethod = MsgGrpc.getSetOrchestratorAddressesMethod) == null) {
          MsgGrpc.getSetOrchestratorAddressesMethod = getSetOrchestratorAddressesMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddresses, umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddressesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetOrchestratorAddresses"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddresses.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddressesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetOrchestratorAddresses"))
              .build();
        }
      }
    }
    return getSetOrchestratorAddressesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEth,
      umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEthResponse> getCancelSendToEthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelSendToEth",
      requestType = umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEth.class,
      responseType = umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEthResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEth,
      umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEthResponse> getCancelSendToEthMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEth, umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEthResponse> getCancelSendToEthMethod;
    if ((getCancelSendToEthMethod = MsgGrpc.getCancelSendToEthMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelSendToEthMethod = MsgGrpc.getCancelSendToEthMethod) == null) {
          MsgGrpc.getCancelSendToEthMethod = getCancelSendToEthMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEth, umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEthResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelSendToEth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEth.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEthResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelSendToEth"))
              .build();
        }
      }
    }
    return getCancelSendToEthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidence,
      umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidenceResponse> getSubmitBadSignatureEvidenceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitBadSignatureEvidence",
      requestType = umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidence.class,
      responseType = umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidenceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidence,
      umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidenceResponse> getSubmitBadSignatureEvidenceMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidence, umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidenceResponse> getSubmitBadSignatureEvidenceMethod;
    if ((getSubmitBadSignatureEvidenceMethod = MsgGrpc.getSubmitBadSignatureEvidenceMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSubmitBadSignatureEvidenceMethod = MsgGrpc.getSubmitBadSignatureEvidenceMethod) == null) {
          MsgGrpc.getSubmitBadSignatureEvidenceMethod = getSubmitBadSignatureEvidenceMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidence, umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidenceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubmitBadSignatureEvidence"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidence.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidenceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SubmitBadSignatureEvidence"))
              .build();
        }
      }
    }
    return getSubmitBadSignatureEvidenceMethod;
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
    public void valsetConfirm(umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirm request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirmResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValsetConfirmMethod(), responseObserver);
    }

    /**
     */
    public void sendToEth(umeenetwork.umee.peggy.v1.Msgs.MsgSendToEth request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgSendToEthResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendToEthMethod(), responseObserver);
    }

    /**
     */
    public void requestBatch(umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatch request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestBatchMethod(), responseObserver);
    }

    /**
     */
    public void confirmBatch(umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatch request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConfirmBatchMethod(), responseObserver);
    }

    /**
     */
    public void depositClaim(umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaim request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaimResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositClaimMethod(), responseObserver);
    }

    /**
     */
    public void withdrawClaim(umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaim request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaimResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawClaimMethod(), responseObserver);
    }

    /**
     */
    public void valsetUpdateClaim(umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaim request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaimResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValsetUpdateClaimMethod(), responseObserver);
    }

    /**
     */
    public void eRC20DeployedClaim(umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaim request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaimResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getERC20DeployedClaimMethod(), responseObserver);
    }

    /**
     */
    public void setOrchestratorAddresses(umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddresses request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddressesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetOrchestratorAddressesMethod(), responseObserver);
    }

    /**
     */
    public void cancelSendToEth(umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEth request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEthResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelSendToEthMethod(), responseObserver);
    }

    /**
     */
    public void submitBadSignatureEvidence(umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidence request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidenceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubmitBadSignatureEvidenceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getValsetConfirmMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirm,
                umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirmResponse>(
                  this, METHODID_VALSET_CONFIRM)))
          .addMethod(
            getSendToEthMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.Msgs.MsgSendToEth,
                umeenetwork.umee.peggy.v1.Msgs.MsgSendToEthResponse>(
                  this, METHODID_SEND_TO_ETH)))
          .addMethod(
            getRequestBatchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatch,
                umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatchResponse>(
                  this, METHODID_REQUEST_BATCH)))
          .addMethod(
            getConfirmBatchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatch,
                umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatchResponse>(
                  this, METHODID_CONFIRM_BATCH)))
          .addMethod(
            getDepositClaimMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaim,
                umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaimResponse>(
                  this, METHODID_DEPOSIT_CLAIM)))
          .addMethod(
            getWithdrawClaimMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaim,
                umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaimResponse>(
                  this, METHODID_WITHDRAW_CLAIM)))
          .addMethod(
            getValsetUpdateClaimMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaim,
                umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaimResponse>(
                  this, METHODID_VALSET_UPDATE_CLAIM)))
          .addMethod(
            getERC20DeployedClaimMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaim,
                umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaimResponse>(
                  this, METHODID_ERC20DEPLOYED_CLAIM)))
          .addMethod(
            getSetOrchestratorAddressesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddresses,
                umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddressesResponse>(
                  this, METHODID_SET_ORCHESTRATOR_ADDRESSES)))
          .addMethod(
            getCancelSendToEthMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEth,
                umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEthResponse>(
                  this, METHODID_CANCEL_SEND_TO_ETH)))
          .addMethod(
            getSubmitBadSignatureEvidenceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidence,
                umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidenceResponse>(
                  this, METHODID_SUBMIT_BAD_SIGNATURE_EVIDENCE)))
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
    public void valsetConfirm(umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirm request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirmResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValsetConfirmMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendToEth(umeenetwork.umee.peggy.v1.Msgs.MsgSendToEth request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgSendToEthResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendToEthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void requestBatch(umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatch request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestBatchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirmBatch(umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatch request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConfirmBatchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void depositClaim(umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaim request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaimResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void withdrawClaim(umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaim request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaimResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void valsetUpdateClaim(umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaim request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaimResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValsetUpdateClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void eRC20DeployedClaim(umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaim request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaimResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getERC20DeployedClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setOrchestratorAddresses(umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddresses request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddressesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetOrchestratorAddressesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void cancelSendToEth(umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEth request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEthResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelSendToEthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void submitBadSignatureEvidence(umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidence request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidenceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubmitBadSignatureEvidenceMethod(), getCallOptions()), request, responseObserver);
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
    public umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirmResponse valsetConfirm(umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirm request) {
      return blockingUnaryCall(
          getChannel(), getValsetConfirmMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.Msgs.MsgSendToEthResponse sendToEth(umeenetwork.umee.peggy.v1.Msgs.MsgSendToEth request) {
      return blockingUnaryCall(
          getChannel(), getSendToEthMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatchResponse requestBatch(umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatch request) {
      return blockingUnaryCall(
          getChannel(), getRequestBatchMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatchResponse confirmBatch(umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatch request) {
      return blockingUnaryCall(
          getChannel(), getConfirmBatchMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaimResponse depositClaim(umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaim request) {
      return blockingUnaryCall(
          getChannel(), getDepositClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaimResponse withdrawClaim(umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaim request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaimResponse valsetUpdateClaim(umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaim request) {
      return blockingUnaryCall(
          getChannel(), getValsetUpdateClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaimResponse eRC20DeployedClaim(umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaim request) {
      return blockingUnaryCall(
          getChannel(), getERC20DeployedClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddressesResponse setOrchestratorAddresses(umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddresses request) {
      return blockingUnaryCall(
          getChannel(), getSetOrchestratorAddressesMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEthResponse cancelSendToEth(umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEth request) {
      return blockingUnaryCall(
          getChannel(), getCancelSendToEthMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidenceResponse submitBadSignatureEvidence(umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidence request) {
      return blockingUnaryCall(
          getChannel(), getSubmitBadSignatureEvidenceMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirmResponse> valsetConfirm(
        umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirm request) {
      return futureUnaryCall(
          getChannel().newCall(getValsetConfirmMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.Msgs.MsgSendToEthResponse> sendToEth(
        umeenetwork.umee.peggy.v1.Msgs.MsgSendToEth request) {
      return futureUnaryCall(
          getChannel().newCall(getSendToEthMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatchResponse> requestBatch(
        umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatch request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestBatchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatchResponse> confirmBatch(
        umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatch request) {
      return futureUnaryCall(
          getChannel().newCall(getConfirmBatchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaimResponse> depositClaim(
        umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaim request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaimResponse> withdrawClaim(
        umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaim request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaimResponse> valsetUpdateClaim(
        umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaim request) {
      return futureUnaryCall(
          getChannel().newCall(getValsetUpdateClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaimResponse> eRC20DeployedClaim(
        umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaim request) {
      return futureUnaryCall(
          getChannel().newCall(getERC20DeployedClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddressesResponse> setOrchestratorAddresses(
        umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddresses request) {
      return futureUnaryCall(
          getChannel().newCall(getSetOrchestratorAddressesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEthResponse> cancelSendToEth(
        umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEth request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelSendToEthMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidenceResponse> submitBadSignatureEvidence(
        umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidence request) {
      return futureUnaryCall(
          getChannel().newCall(getSubmitBadSignatureEvidenceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_VALSET_CONFIRM = 0;
  private static final int METHODID_SEND_TO_ETH = 1;
  private static final int METHODID_REQUEST_BATCH = 2;
  private static final int METHODID_CONFIRM_BATCH = 3;
  private static final int METHODID_DEPOSIT_CLAIM = 4;
  private static final int METHODID_WITHDRAW_CLAIM = 5;
  private static final int METHODID_VALSET_UPDATE_CLAIM = 6;
  private static final int METHODID_ERC20DEPLOYED_CLAIM = 7;
  private static final int METHODID_SET_ORCHESTRATOR_ADDRESSES = 8;
  private static final int METHODID_CANCEL_SEND_TO_ETH = 9;
  private static final int METHODID_SUBMIT_BAD_SIGNATURE_EVIDENCE = 10;

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
        case METHODID_VALSET_CONFIRM:
          serviceImpl.valsetConfirm((umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirm) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgValsetConfirmResponse>) responseObserver);
          break;
        case METHODID_SEND_TO_ETH:
          serviceImpl.sendToEth((umeenetwork.umee.peggy.v1.Msgs.MsgSendToEth) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgSendToEthResponse>) responseObserver);
          break;
        case METHODID_REQUEST_BATCH:
          serviceImpl.requestBatch((umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatch) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgRequestBatchResponse>) responseObserver);
          break;
        case METHODID_CONFIRM_BATCH:
          serviceImpl.confirmBatch((umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatch) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgConfirmBatchResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT_CLAIM:
          serviceImpl.depositClaim((umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaim) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgDepositClaimResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_CLAIM:
          serviceImpl.withdrawClaim((umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaim) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgWithdrawClaimResponse>) responseObserver);
          break;
        case METHODID_VALSET_UPDATE_CLAIM:
          serviceImpl.valsetUpdateClaim((umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaim) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgValsetUpdatedClaimResponse>) responseObserver);
          break;
        case METHODID_ERC20DEPLOYED_CLAIM:
          serviceImpl.eRC20DeployedClaim((umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaim) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgERC20DeployedClaimResponse>) responseObserver);
          break;
        case METHODID_SET_ORCHESTRATOR_ADDRESSES:
          serviceImpl.setOrchestratorAddresses((umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddresses) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgSetOrchestratorAddressesResponse>) responseObserver);
          break;
        case METHODID_CANCEL_SEND_TO_ETH:
          serviceImpl.cancelSendToEth((umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEth) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgCancelSendToEthResponse>) responseObserver);
          break;
        case METHODID_SUBMIT_BAD_SIGNATURE_EVIDENCE:
          serviceImpl.submitBadSignatureEvidence((umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidence) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.Msgs.MsgSubmitBadSignatureEvidenceResponse>) responseObserver);
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
      return umeenetwork.umee.peggy.v1.Msgs.getDescriptor();
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
              .addMethod(getValsetConfirmMethod())
              .addMethod(getSendToEthMethod())
              .addMethod(getRequestBatchMethod())
              .addMethod(getConfirmBatchMethod())
              .addMethod(getDepositClaimMethod())
              .addMethod(getWithdrawClaimMethod())
              .addMethod(getValsetUpdateClaimMethod())
              .addMethod(getERC20DeployedClaimMethod())
              .addMethod(getSetOrchestratorAddressesMethod())
              .addMethod(getCancelSendToEthMethod())
              .addMethod(getSubmitBadSignatureEvidenceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
