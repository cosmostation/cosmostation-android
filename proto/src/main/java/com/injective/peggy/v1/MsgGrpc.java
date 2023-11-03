package com.injective.peggy.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: injective/peggy/v1/msgs.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "injective.peggy.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgValsetConfirm,
      com.injective.peggy.v1.MsgsProto.MsgValsetConfirmResponse> getValsetConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetConfirm",
      requestType = com.injective.peggy.v1.MsgsProto.MsgValsetConfirm.class,
      responseType = com.injective.peggy.v1.MsgsProto.MsgValsetConfirmResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgValsetConfirm,
      com.injective.peggy.v1.MsgsProto.MsgValsetConfirmResponse> getValsetConfirmMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgValsetConfirm, com.injective.peggy.v1.MsgsProto.MsgValsetConfirmResponse> getValsetConfirmMethod;
    if ((getValsetConfirmMethod = MsgGrpc.getValsetConfirmMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getValsetConfirmMethod = MsgGrpc.getValsetConfirmMethod) == null) {
          MsgGrpc.getValsetConfirmMethod = getValsetConfirmMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.MsgsProto.MsgValsetConfirm, com.injective.peggy.v1.MsgsProto.MsgValsetConfirmResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetConfirm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgValsetConfirm.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgValsetConfirmResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ValsetConfirm"))
              .build();
        }
      }
    }
    return getValsetConfirmMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgSendToEth,
      com.injective.peggy.v1.MsgsProto.MsgSendToEthResponse> getSendToEthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendToEth",
      requestType = com.injective.peggy.v1.MsgsProto.MsgSendToEth.class,
      responseType = com.injective.peggy.v1.MsgsProto.MsgSendToEthResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgSendToEth,
      com.injective.peggy.v1.MsgsProto.MsgSendToEthResponse> getSendToEthMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgSendToEth, com.injective.peggy.v1.MsgsProto.MsgSendToEthResponse> getSendToEthMethod;
    if ((getSendToEthMethod = MsgGrpc.getSendToEthMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSendToEthMethod = MsgGrpc.getSendToEthMethod) == null) {
          MsgGrpc.getSendToEthMethod = getSendToEthMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.MsgsProto.MsgSendToEth, com.injective.peggy.v1.MsgsProto.MsgSendToEthResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendToEth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgSendToEth.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgSendToEthResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SendToEth"))
              .build();
        }
      }
    }
    return getSendToEthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgRequestBatch,
      com.injective.peggy.v1.MsgsProto.MsgRequestBatchResponse> getRequestBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestBatch",
      requestType = com.injective.peggy.v1.MsgsProto.MsgRequestBatch.class,
      responseType = com.injective.peggy.v1.MsgsProto.MsgRequestBatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgRequestBatch,
      com.injective.peggy.v1.MsgsProto.MsgRequestBatchResponse> getRequestBatchMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgRequestBatch, com.injective.peggy.v1.MsgsProto.MsgRequestBatchResponse> getRequestBatchMethod;
    if ((getRequestBatchMethod = MsgGrpc.getRequestBatchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRequestBatchMethod = MsgGrpc.getRequestBatchMethod) == null) {
          MsgGrpc.getRequestBatchMethod = getRequestBatchMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.MsgsProto.MsgRequestBatch, com.injective.peggy.v1.MsgsProto.MsgRequestBatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgRequestBatch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgRequestBatchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RequestBatch"))
              .build();
        }
      }
    }
    return getRequestBatchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgConfirmBatch,
      com.injective.peggy.v1.MsgsProto.MsgConfirmBatchResponse> getConfirmBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmBatch",
      requestType = com.injective.peggy.v1.MsgsProto.MsgConfirmBatch.class,
      responseType = com.injective.peggy.v1.MsgsProto.MsgConfirmBatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgConfirmBatch,
      com.injective.peggy.v1.MsgsProto.MsgConfirmBatchResponse> getConfirmBatchMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgConfirmBatch, com.injective.peggy.v1.MsgsProto.MsgConfirmBatchResponse> getConfirmBatchMethod;
    if ((getConfirmBatchMethod = MsgGrpc.getConfirmBatchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConfirmBatchMethod = MsgGrpc.getConfirmBatchMethod) == null) {
          MsgGrpc.getConfirmBatchMethod = getConfirmBatchMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.MsgsProto.MsgConfirmBatch, com.injective.peggy.v1.MsgsProto.MsgConfirmBatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConfirmBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgConfirmBatch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgConfirmBatchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConfirmBatch"))
              .build();
        }
      }
    }
    return getConfirmBatchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgDepositClaim,
      com.injective.peggy.v1.MsgsProto.MsgDepositClaimResponse> getDepositClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DepositClaim",
      requestType = com.injective.peggy.v1.MsgsProto.MsgDepositClaim.class,
      responseType = com.injective.peggy.v1.MsgsProto.MsgDepositClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgDepositClaim,
      com.injective.peggy.v1.MsgsProto.MsgDepositClaimResponse> getDepositClaimMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgDepositClaim, com.injective.peggy.v1.MsgsProto.MsgDepositClaimResponse> getDepositClaimMethod;
    if ((getDepositClaimMethod = MsgGrpc.getDepositClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDepositClaimMethod = MsgGrpc.getDepositClaimMethod) == null) {
          MsgGrpc.getDepositClaimMethod = getDepositClaimMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.MsgsProto.MsgDepositClaim, com.injective.peggy.v1.MsgsProto.MsgDepositClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DepositClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgDepositClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgDepositClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DepositClaim"))
              .build();
        }
      }
    }
    return getDepositClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgWithdrawClaim,
      com.injective.peggy.v1.MsgsProto.MsgWithdrawClaimResponse> getWithdrawClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawClaim",
      requestType = com.injective.peggy.v1.MsgsProto.MsgWithdrawClaim.class,
      responseType = com.injective.peggy.v1.MsgsProto.MsgWithdrawClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgWithdrawClaim,
      com.injective.peggy.v1.MsgsProto.MsgWithdrawClaimResponse> getWithdrawClaimMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgWithdrawClaim, com.injective.peggy.v1.MsgsProto.MsgWithdrawClaimResponse> getWithdrawClaimMethod;
    if ((getWithdrawClaimMethod = MsgGrpc.getWithdrawClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawClaimMethod = MsgGrpc.getWithdrawClaimMethod) == null) {
          MsgGrpc.getWithdrawClaimMethod = getWithdrawClaimMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.MsgsProto.MsgWithdrawClaim, com.injective.peggy.v1.MsgsProto.MsgWithdrawClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgWithdrawClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgWithdrawClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawClaim"))
              .build();
        }
      }
    }
    return getWithdrawClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaim,
      com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaimResponse> getValsetUpdateClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetUpdateClaim",
      requestType = com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaim.class,
      responseType = com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaim,
      com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaimResponse> getValsetUpdateClaimMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaim, com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaimResponse> getValsetUpdateClaimMethod;
    if ((getValsetUpdateClaimMethod = MsgGrpc.getValsetUpdateClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getValsetUpdateClaimMethod = MsgGrpc.getValsetUpdateClaimMethod) == null) {
          MsgGrpc.getValsetUpdateClaimMethod = getValsetUpdateClaimMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaim, com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetUpdateClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ValsetUpdateClaim"))
              .build();
        }
      }
    }
    return getValsetUpdateClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaim,
      com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaimResponse> getERC20DeployedClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ERC20DeployedClaim",
      requestType = com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaim.class,
      responseType = com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaim,
      com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaimResponse> getERC20DeployedClaimMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaim, com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaimResponse> getERC20DeployedClaimMethod;
    if ((getERC20DeployedClaimMethod = MsgGrpc.getERC20DeployedClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getERC20DeployedClaimMethod = MsgGrpc.getERC20DeployedClaimMethod) == null) {
          MsgGrpc.getERC20DeployedClaimMethod = getERC20DeployedClaimMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaim, com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ERC20DeployedClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ERC20DeployedClaim"))
              .build();
        }
      }
    }
    return getERC20DeployedClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddresses,
      com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddressesResponse> getSetOrchestratorAddressesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetOrchestratorAddresses",
      requestType = com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddresses.class,
      responseType = com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddressesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddresses,
      com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddressesResponse> getSetOrchestratorAddressesMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddresses, com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddressesResponse> getSetOrchestratorAddressesMethod;
    if ((getSetOrchestratorAddressesMethod = MsgGrpc.getSetOrchestratorAddressesMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetOrchestratorAddressesMethod = MsgGrpc.getSetOrchestratorAddressesMethod) == null) {
          MsgGrpc.getSetOrchestratorAddressesMethod = getSetOrchestratorAddressesMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddresses, com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddressesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetOrchestratorAddresses"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddresses.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddressesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetOrchestratorAddresses"))
              .build();
        }
      }
    }
    return getSetOrchestratorAddressesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgCancelSendToEth,
      com.injective.peggy.v1.MsgsProto.MsgCancelSendToEthResponse> getCancelSendToEthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelSendToEth",
      requestType = com.injective.peggy.v1.MsgsProto.MsgCancelSendToEth.class,
      responseType = com.injective.peggy.v1.MsgsProto.MsgCancelSendToEthResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgCancelSendToEth,
      com.injective.peggy.v1.MsgsProto.MsgCancelSendToEthResponse> getCancelSendToEthMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgCancelSendToEth, com.injective.peggy.v1.MsgsProto.MsgCancelSendToEthResponse> getCancelSendToEthMethod;
    if ((getCancelSendToEthMethod = MsgGrpc.getCancelSendToEthMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelSendToEthMethod = MsgGrpc.getCancelSendToEthMethod) == null) {
          MsgGrpc.getCancelSendToEthMethod = getCancelSendToEthMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.MsgsProto.MsgCancelSendToEth, com.injective.peggy.v1.MsgsProto.MsgCancelSendToEthResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelSendToEth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgCancelSendToEth.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgCancelSendToEthResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelSendToEth"))
              .build();
        }
      }
    }
    return getCancelSendToEthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidence,
      com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidenceResponse> getSubmitBadSignatureEvidenceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitBadSignatureEvidence",
      requestType = com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidence.class,
      responseType = com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidenceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidence,
      com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidenceResponse> getSubmitBadSignatureEvidenceMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidence, com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidenceResponse> getSubmitBadSignatureEvidenceMethod;
    if ((getSubmitBadSignatureEvidenceMethod = MsgGrpc.getSubmitBadSignatureEvidenceMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSubmitBadSignatureEvidenceMethod = MsgGrpc.getSubmitBadSignatureEvidenceMethod) == null) {
          MsgGrpc.getSubmitBadSignatureEvidenceMethod = getSubmitBadSignatureEvidenceMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidence, com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidenceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubmitBadSignatureEvidence"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidence.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidenceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SubmitBadSignatureEvidence"))
              .build();
        }
      }
    }
    return getSubmitBadSignatureEvidenceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgUpdateParams,
      com.injective.peggy.v1.MsgsProto.MsgUpdateParamsResponse> getUpdateParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateParams",
      requestType = com.injective.peggy.v1.MsgsProto.MsgUpdateParams.class,
      responseType = com.injective.peggy.v1.MsgsProto.MsgUpdateParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgUpdateParams,
      com.injective.peggy.v1.MsgsProto.MsgUpdateParamsResponse> getUpdateParamsMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.MsgsProto.MsgUpdateParams, com.injective.peggy.v1.MsgsProto.MsgUpdateParamsResponse> getUpdateParamsMethod;
    if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
          MsgGrpc.getUpdateParamsMethod = getUpdateParamsMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.MsgsProto.MsgUpdateParams, com.injective.peggy.v1.MsgsProto.MsgUpdateParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgUpdateParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.MsgsProto.MsgUpdateParamsResponse.getDefaultInstance()))
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
   */
  public interface AsyncService {

    /**
     */
    default void valsetConfirm(com.injective.peggy.v1.MsgsProto.MsgValsetConfirm request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgValsetConfirmResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValsetConfirmMethod(), responseObserver);
    }

    /**
     */
    default void sendToEth(com.injective.peggy.v1.MsgsProto.MsgSendToEth request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgSendToEthResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendToEthMethod(), responseObserver);
    }

    /**
     */
    default void requestBatch(com.injective.peggy.v1.MsgsProto.MsgRequestBatch request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgRequestBatchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRequestBatchMethod(), responseObserver);
    }

    /**
     */
    default void confirmBatch(com.injective.peggy.v1.MsgsProto.MsgConfirmBatch request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgConfirmBatchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConfirmBatchMethod(), responseObserver);
    }

    /**
     */
    default void depositClaim(com.injective.peggy.v1.MsgsProto.MsgDepositClaim request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgDepositClaimResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDepositClaimMethod(), responseObserver);
    }

    /**
     */
    default void withdrawClaim(com.injective.peggy.v1.MsgsProto.MsgWithdrawClaim request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgWithdrawClaimResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWithdrawClaimMethod(), responseObserver);
    }

    /**
     */
    default void valsetUpdateClaim(com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaim request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaimResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValsetUpdateClaimMethod(), responseObserver);
    }

    /**
     */
    default void eRC20DeployedClaim(com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaim request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaimResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getERC20DeployedClaimMethod(), responseObserver);
    }

    /**
     */
    default void setOrchestratorAddresses(com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddresses request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddressesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetOrchestratorAddressesMethod(), responseObserver);
    }

    /**
     */
    default void cancelSendToEth(com.injective.peggy.v1.MsgsProto.MsgCancelSendToEth request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgCancelSendToEthResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCancelSendToEthMethod(), responseObserver);
    }

    /**
     */
    default void submitBadSignatureEvidence(com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidence request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidenceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubmitBadSignatureEvidenceMethod(), responseObserver);
    }

    /**
     */
    default void updateParams(com.injective.peggy.v1.MsgsProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateParamsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   */
  public static abstract class MsgImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MsgGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Msg.
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
     */
    public void valsetConfirm(com.injective.peggy.v1.MsgsProto.MsgValsetConfirm request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgValsetConfirmResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValsetConfirmMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendToEth(com.injective.peggy.v1.MsgsProto.MsgSendToEth request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgSendToEthResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendToEthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void requestBatch(com.injective.peggy.v1.MsgsProto.MsgRequestBatch request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgRequestBatchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRequestBatchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirmBatch(com.injective.peggy.v1.MsgsProto.MsgConfirmBatch request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgConfirmBatchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConfirmBatchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void depositClaim(com.injective.peggy.v1.MsgsProto.MsgDepositClaim request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgDepositClaimResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDepositClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void withdrawClaim(com.injective.peggy.v1.MsgsProto.MsgWithdrawClaim request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgWithdrawClaimResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getWithdrawClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void valsetUpdateClaim(com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaim request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaimResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValsetUpdateClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void eRC20DeployedClaim(com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaim request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaimResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getERC20DeployedClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setOrchestratorAddresses(com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddresses request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddressesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetOrchestratorAddressesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void cancelSendToEth(com.injective.peggy.v1.MsgsProto.MsgCancelSendToEth request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgCancelSendToEthResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCancelSendToEthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void submitBadSignatureEvidence(com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidence request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidenceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSubmitBadSignatureEvidenceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateParams(com.injective.peggy.v1.MsgsProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
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
     */
    public com.injective.peggy.v1.MsgsProto.MsgValsetConfirmResponse valsetConfirm(com.injective.peggy.v1.MsgsProto.MsgValsetConfirm request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValsetConfirmMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.MsgsProto.MsgSendToEthResponse sendToEth(com.injective.peggy.v1.MsgsProto.MsgSendToEth request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendToEthMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.MsgsProto.MsgRequestBatchResponse requestBatch(com.injective.peggy.v1.MsgsProto.MsgRequestBatch request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRequestBatchMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.MsgsProto.MsgConfirmBatchResponse confirmBatch(com.injective.peggy.v1.MsgsProto.MsgConfirmBatch request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConfirmBatchMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.MsgsProto.MsgDepositClaimResponse depositClaim(com.injective.peggy.v1.MsgsProto.MsgDepositClaim request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDepositClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.MsgsProto.MsgWithdrawClaimResponse withdrawClaim(com.injective.peggy.v1.MsgsProto.MsgWithdrawClaim request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getWithdrawClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaimResponse valsetUpdateClaim(com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaim request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValsetUpdateClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaimResponse eRC20DeployedClaim(com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaim request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getERC20DeployedClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddressesResponse setOrchestratorAddresses(com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddresses request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetOrchestratorAddressesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.MsgsProto.MsgCancelSendToEthResponse cancelSendToEth(com.injective.peggy.v1.MsgsProto.MsgCancelSendToEth request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCancelSendToEthMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidenceResponse submitBadSignatureEvidence(com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidence request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSubmitBadSignatureEvidenceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.MsgsProto.MsgUpdateParamsResponse updateParams(com.injective.peggy.v1.MsgsProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
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
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.MsgsProto.MsgValsetConfirmResponse> valsetConfirm(
        com.injective.peggy.v1.MsgsProto.MsgValsetConfirm request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValsetConfirmMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.MsgsProto.MsgSendToEthResponse> sendToEth(
        com.injective.peggy.v1.MsgsProto.MsgSendToEth request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendToEthMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.MsgsProto.MsgRequestBatchResponse> requestBatch(
        com.injective.peggy.v1.MsgsProto.MsgRequestBatch request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRequestBatchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.MsgsProto.MsgConfirmBatchResponse> confirmBatch(
        com.injective.peggy.v1.MsgsProto.MsgConfirmBatch request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConfirmBatchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.MsgsProto.MsgDepositClaimResponse> depositClaim(
        com.injective.peggy.v1.MsgsProto.MsgDepositClaim request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDepositClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.MsgsProto.MsgWithdrawClaimResponse> withdrawClaim(
        com.injective.peggy.v1.MsgsProto.MsgWithdrawClaim request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getWithdrawClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaimResponse> valsetUpdateClaim(
        com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaim request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValsetUpdateClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaimResponse> eRC20DeployedClaim(
        com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaim request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getERC20DeployedClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddressesResponse> setOrchestratorAddresses(
        com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddresses request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetOrchestratorAddressesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.MsgsProto.MsgCancelSendToEthResponse> cancelSendToEth(
        com.injective.peggy.v1.MsgsProto.MsgCancelSendToEth request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCancelSendToEthMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidenceResponse> submitBadSignatureEvidence(
        com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidence request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSubmitBadSignatureEvidenceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.MsgsProto.MsgUpdateParamsResponse> updateParams(
        com.injective.peggy.v1.MsgsProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request);
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
  private static final int METHODID_UPDATE_PARAMS = 11;

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
        case METHODID_VALSET_CONFIRM:
          serviceImpl.valsetConfirm((com.injective.peggy.v1.MsgsProto.MsgValsetConfirm) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgValsetConfirmResponse>) responseObserver);
          break;
        case METHODID_SEND_TO_ETH:
          serviceImpl.sendToEth((com.injective.peggy.v1.MsgsProto.MsgSendToEth) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgSendToEthResponse>) responseObserver);
          break;
        case METHODID_REQUEST_BATCH:
          serviceImpl.requestBatch((com.injective.peggy.v1.MsgsProto.MsgRequestBatch) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgRequestBatchResponse>) responseObserver);
          break;
        case METHODID_CONFIRM_BATCH:
          serviceImpl.confirmBatch((com.injective.peggy.v1.MsgsProto.MsgConfirmBatch) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgConfirmBatchResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT_CLAIM:
          serviceImpl.depositClaim((com.injective.peggy.v1.MsgsProto.MsgDepositClaim) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgDepositClaimResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_CLAIM:
          serviceImpl.withdrawClaim((com.injective.peggy.v1.MsgsProto.MsgWithdrawClaim) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgWithdrawClaimResponse>) responseObserver);
          break;
        case METHODID_VALSET_UPDATE_CLAIM:
          serviceImpl.valsetUpdateClaim((com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaim) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaimResponse>) responseObserver);
          break;
        case METHODID_ERC20DEPLOYED_CLAIM:
          serviceImpl.eRC20DeployedClaim((com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaim) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaimResponse>) responseObserver);
          break;
        case METHODID_SET_ORCHESTRATOR_ADDRESSES:
          serviceImpl.setOrchestratorAddresses((com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddresses) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddressesResponse>) responseObserver);
          break;
        case METHODID_CANCEL_SEND_TO_ETH:
          serviceImpl.cancelSendToEth((com.injective.peggy.v1.MsgsProto.MsgCancelSendToEth) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgCancelSendToEthResponse>) responseObserver);
          break;
        case METHODID_SUBMIT_BAD_SIGNATURE_EVIDENCE:
          serviceImpl.submitBadSignatureEvidence((com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidence) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidenceResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PARAMS:
          serviceImpl.updateParams((com.injective.peggy.v1.MsgsProto.MsgUpdateParams) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.MsgsProto.MsgUpdateParamsResponse>) responseObserver);
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
          getValsetConfirmMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.MsgsProto.MsgValsetConfirm,
              com.injective.peggy.v1.MsgsProto.MsgValsetConfirmResponse>(
                service, METHODID_VALSET_CONFIRM)))
        .addMethod(
          getSendToEthMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.MsgsProto.MsgSendToEth,
              com.injective.peggy.v1.MsgsProto.MsgSendToEthResponse>(
                service, METHODID_SEND_TO_ETH)))
        .addMethod(
          getRequestBatchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.MsgsProto.MsgRequestBatch,
              com.injective.peggy.v1.MsgsProto.MsgRequestBatchResponse>(
                service, METHODID_REQUEST_BATCH)))
        .addMethod(
          getConfirmBatchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.MsgsProto.MsgConfirmBatch,
              com.injective.peggy.v1.MsgsProto.MsgConfirmBatchResponse>(
                service, METHODID_CONFIRM_BATCH)))
        .addMethod(
          getDepositClaimMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.MsgsProto.MsgDepositClaim,
              com.injective.peggy.v1.MsgsProto.MsgDepositClaimResponse>(
                service, METHODID_DEPOSIT_CLAIM)))
        .addMethod(
          getWithdrawClaimMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.MsgsProto.MsgWithdrawClaim,
              com.injective.peggy.v1.MsgsProto.MsgWithdrawClaimResponse>(
                service, METHODID_WITHDRAW_CLAIM)))
        .addMethod(
          getValsetUpdateClaimMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaim,
              com.injective.peggy.v1.MsgsProto.MsgValsetUpdatedClaimResponse>(
                service, METHODID_VALSET_UPDATE_CLAIM)))
        .addMethod(
          getERC20DeployedClaimMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaim,
              com.injective.peggy.v1.MsgsProto.MsgERC20DeployedClaimResponse>(
                service, METHODID_ERC20DEPLOYED_CLAIM)))
        .addMethod(
          getSetOrchestratorAddressesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddresses,
              com.injective.peggy.v1.MsgsProto.MsgSetOrchestratorAddressesResponse>(
                service, METHODID_SET_ORCHESTRATOR_ADDRESSES)))
        .addMethod(
          getCancelSendToEthMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.MsgsProto.MsgCancelSendToEth,
              com.injective.peggy.v1.MsgsProto.MsgCancelSendToEthResponse>(
                service, METHODID_CANCEL_SEND_TO_ETH)))
        .addMethod(
          getSubmitBadSignatureEvidenceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidence,
              com.injective.peggy.v1.MsgsProto.MsgSubmitBadSignatureEvidenceResponse>(
                service, METHODID_SUBMIT_BAD_SIGNATURE_EVIDENCE)))
        .addMethod(
          getUpdateParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.MsgsProto.MsgUpdateParams,
              com.injective.peggy.v1.MsgsProto.MsgUpdateParamsResponse>(
                service, METHODID_UPDATE_PARAMS)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.injective.peggy.v1.MsgsProto.getDescriptor();
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
              .addMethod(getUpdateParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
