package com.desmos.profiles.v3;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the relationships Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: desmos/profiles/v3/msg_server.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "desmos.profiles.v3.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfile,
      com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfileResponse> getSaveProfileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SaveProfile",
      requestType = com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfile.class,
      responseType = com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfileResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfile,
      com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfileResponse> getSaveProfileMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfile, com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfileResponse> getSaveProfileMethod;
    if ((getSaveProfileMethod = MsgGrpc.getSaveProfileMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSaveProfileMethod = MsgGrpc.getSaveProfileMethod) == null) {
          MsgGrpc.getSaveProfileMethod = getSaveProfileMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfile, com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SaveProfile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfile.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfileResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SaveProfile"))
              .build();
        }
      }
    }
    return getSaveProfileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfile,
      com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfileResponse> getDeleteProfileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteProfile",
      requestType = com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfile.class,
      responseType = com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfileResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfile,
      com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfileResponse> getDeleteProfileMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfile, com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfileResponse> getDeleteProfileMethod;
    if ((getDeleteProfileMethod = MsgGrpc.getDeleteProfileMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteProfileMethod = MsgGrpc.getDeleteProfileMethod) == null) {
          MsgGrpc.getDeleteProfileMethod = getDeleteProfileMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfile, com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteProfile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfile.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfileResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteProfile"))
              .build();
        }
      }
    }
    return getDeleteProfileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransfer,
      com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransferResponse> getRequestDTagTransferMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestDTagTransfer",
      requestType = com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransfer.class,
      responseType = com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransferResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransfer,
      com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransferResponse> getRequestDTagTransferMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransfer, com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransferResponse> getRequestDTagTransferMethod;
    if ((getRequestDTagTransferMethod = MsgGrpc.getRequestDTagTransferMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRequestDTagTransferMethod = MsgGrpc.getRequestDTagTransferMethod) == null) {
          MsgGrpc.getRequestDTagTransferMethod = getRequestDTagTransferMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransfer, com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransferResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestDTagTransfer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransfer.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransferResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RequestDTagTransfer"))
              .build();
        }
      }
    }
    return getRequestDTagTransferMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequest,
      com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequestResponse> getCancelDTagTransferRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelDTagTransferRequest",
      requestType = com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequest.class,
      responseType = com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequest,
      com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequestResponse> getCancelDTagTransferRequestMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequest, com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequestResponse> getCancelDTagTransferRequestMethod;
    if ((getCancelDTagTransferRequestMethod = MsgGrpc.getCancelDTagTransferRequestMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelDTagTransferRequestMethod = MsgGrpc.getCancelDTagTransferRequestMethod) == null) {
          MsgGrpc.getCancelDTagTransferRequestMethod = getCancelDTagTransferRequestMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequest, com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelDTagTransferRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelDTagTransferRequest"))
              .build();
        }
      }
    }
    return getCancelDTagTransferRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequest,
      com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequestResponse> getAcceptDTagTransferRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AcceptDTagTransferRequest",
      requestType = com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequest.class,
      responseType = com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequest,
      com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequestResponse> getAcceptDTagTransferRequestMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequest, com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequestResponse> getAcceptDTagTransferRequestMethod;
    if ((getAcceptDTagTransferRequestMethod = MsgGrpc.getAcceptDTagTransferRequestMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAcceptDTagTransferRequestMethod = MsgGrpc.getAcceptDTagTransferRequestMethod) == null) {
          MsgGrpc.getAcceptDTagTransferRequestMethod = getAcceptDTagTransferRequestMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequest, com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AcceptDTagTransferRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AcceptDTagTransferRequest"))
              .build();
        }
      }
    }
    return getAcceptDTagTransferRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequest,
      com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequestResponse> getRefuseDTagTransferRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RefuseDTagTransferRequest",
      requestType = com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequest.class,
      responseType = com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequest,
      com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequestResponse> getRefuseDTagTransferRequestMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequest, com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequestResponse> getRefuseDTagTransferRequestMethod;
    if ((getRefuseDTagTransferRequestMethod = MsgGrpc.getRefuseDTagTransferRequestMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRefuseDTagTransferRequestMethod = MsgGrpc.getRefuseDTagTransferRequestMethod) == null) {
          MsgGrpc.getRefuseDTagTransferRequestMethod = getRefuseDTagTransferRequestMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequest, com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RefuseDTagTransferRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RefuseDTagTransferRequest"))
              .build();
        }
      }
    }
    return getRefuseDTagTransferRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccount,
      com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccountResponse> getLinkChainAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LinkChainAccount",
      requestType = com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccount.class,
      responseType = com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccount,
      com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccountResponse> getLinkChainAccountMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccount, com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccountResponse> getLinkChainAccountMethod;
    if ((getLinkChainAccountMethod = MsgGrpc.getLinkChainAccountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLinkChainAccountMethod = MsgGrpc.getLinkChainAccountMethod) == null) {
          MsgGrpc.getLinkChainAccountMethod = getLinkChainAccountMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccount, com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LinkChainAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("LinkChainAccount"))
              .build();
        }
      }
    }
    return getLinkChainAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccount,
      com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccountResponse> getUnlinkChainAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnlinkChainAccount",
      requestType = com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccount.class,
      responseType = com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccount,
      com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccountResponse> getUnlinkChainAccountMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccount, com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccountResponse> getUnlinkChainAccountMethod;
    if ((getUnlinkChainAccountMethod = MsgGrpc.getUnlinkChainAccountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUnlinkChainAccountMethod = MsgGrpc.getUnlinkChainAccountMethod) == null) {
          MsgGrpc.getUnlinkChainAccountMethod = getUnlinkChainAccountMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccount, com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnlinkChainAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UnlinkChainAccount"))
              .build();
        }
      }
    }
    return getUnlinkChainAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddress,
      com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddressResponse> getSetDefaultExternalAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetDefaultExternalAddress",
      requestType = com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddress.class,
      responseType = com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddress,
      com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddressResponse> getSetDefaultExternalAddressMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddress, com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddressResponse> getSetDefaultExternalAddressMethod;
    if ((getSetDefaultExternalAddressMethod = MsgGrpc.getSetDefaultExternalAddressMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetDefaultExternalAddressMethod = MsgGrpc.getSetDefaultExternalAddressMethod) == null) {
          MsgGrpc.getSetDefaultExternalAddressMethod = getSetDefaultExternalAddressMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddress, com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetDefaultExternalAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddress.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetDefaultExternalAddress"))
              .build();
        }
      }
    }
    return getSetDefaultExternalAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplication,
      com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplicationResponse> getLinkApplicationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LinkApplication",
      requestType = com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplication.class,
      responseType = com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplicationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplication,
      com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplicationResponse> getLinkApplicationMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplication, com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplicationResponse> getLinkApplicationMethod;
    if ((getLinkApplicationMethod = MsgGrpc.getLinkApplicationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLinkApplicationMethod = MsgGrpc.getLinkApplicationMethod) == null) {
          MsgGrpc.getLinkApplicationMethod = getLinkApplicationMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplication, com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplicationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LinkApplication"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplication.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplicationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("LinkApplication"))
              .build();
        }
      }
    }
    return getLinkApplicationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplication,
      com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplicationResponse> getUnlinkApplicationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnlinkApplication",
      requestType = com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplication.class,
      responseType = com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplicationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplication,
      com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplicationResponse> getUnlinkApplicationMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplication, com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplicationResponse> getUnlinkApplicationMethod;
    if ((getUnlinkApplicationMethod = MsgGrpc.getUnlinkApplicationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUnlinkApplicationMethod = MsgGrpc.getUnlinkApplicationMethod) == null) {
          MsgGrpc.getUnlinkApplicationMethod = getUnlinkApplicationMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplication, com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplicationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnlinkApplication"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplication.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplicationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UnlinkApplication"))
              .build();
        }
      }
    }
    return getUnlinkApplicationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParams,
      com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParamsResponse> getUpdateParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateParams",
      requestType = com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParams.class,
      responseType = com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParams,
      com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParamsResponse> getUpdateParamsMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParams, com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParamsResponse> getUpdateParamsMethod;
    if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
          MsgGrpc.getUpdateParamsMethod = getUpdateParamsMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParams, com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParamsResponse.getDefaultInstance()))
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
   * Msg defines the relationships Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * SaveProfile defines the method to save a profile
     * </pre>
     */
    default void saveProfile(com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfile request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfileResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSaveProfileMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteProfile defines the method to delete an existing profile
     * </pre>
     */
    default void deleteProfile(com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfile request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfileResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteProfileMethod(), responseObserver);
    }

    /**
     * <pre>
     * RequestDTagTransfer defines the method to request another user to transfer
     * their DTag to you
     * </pre>
     */
    default void requestDTagTransfer(com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransfer request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransferResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRequestDTagTransferMethod(), responseObserver);
    }

    /**
     * <pre>
     * CancelDTagTransferRequest defines the method to cancel an outgoing DTag
     * transfer request
     * </pre>
     */
    default void cancelDTagTransferRequest(com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequestResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCancelDTagTransferRequestMethod(), responseObserver);
    }

    /**
     * <pre>
     * AcceptDTagTransferRequest defines the method to accept an incoming DTag
     * transfer request
     * </pre>
     */
    default void acceptDTagTransferRequest(com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequestResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAcceptDTagTransferRequestMethod(), responseObserver);
    }

    /**
     * <pre>
     * RefuseDTagTransferRequest defines the method to refuse an incoming DTag
     * transfer request
     * </pre>
     */
    default void refuseDTagTransferRequest(com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequestResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRefuseDTagTransferRequestMethod(), responseObserver);
    }

    /**
     * <pre>
     * LinkChainAccount defines a method to link an external chain account to a
     * profile
     * </pre>
     */
    default void linkChainAccount(com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccount request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLinkChainAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnlinkChainAccount defines a method to unlink an external chain account
     * from a profile
     * </pre>
     */
    default void unlinkChainAccount(com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccount request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnlinkChainAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * SetDefaultExternalAddress allows to set a specific external address as the
     * default one for a given chain
     * </pre>
     */
    default void setDefaultExternalAddress(com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddress request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddressResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetDefaultExternalAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * LinkApplication defines a method to create a centralized application
     * link
     * </pre>
     */
    default void linkApplication(com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplication request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplicationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLinkApplicationMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnlinkApplication defines a method to remove a centralized application
     * </pre>
     */
    default void unlinkApplication(com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplication request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplicationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnlinkApplicationMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateParams defines a (governance) operation for updating the module
     * parameters.
     * The authority defaults to the x/gov module account.
     * Since: Desmos 5.0.0
     * </pre>
     */
    default void updateParams(com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateParamsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the relationships Msg service.
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
   * Msg defines the relationships Msg service.
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
     * SaveProfile defines the method to save a profile
     * </pre>
     */
    public void saveProfile(com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfile request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfileResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSaveProfileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteProfile defines the method to delete an existing profile
     * </pre>
     */
    public void deleteProfile(com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfile request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfileResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteProfileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RequestDTagTransfer defines the method to request another user to transfer
     * their DTag to you
     * </pre>
     */
    public void requestDTagTransfer(com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransfer request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransferResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRequestDTagTransferMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CancelDTagTransferRequest defines the method to cancel an outgoing DTag
     * transfer request
     * </pre>
     */
    public void cancelDTagTransferRequest(com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequestResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCancelDTagTransferRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AcceptDTagTransferRequest defines the method to accept an incoming DTag
     * transfer request
     * </pre>
     */
    public void acceptDTagTransferRequest(com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequestResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAcceptDTagTransferRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RefuseDTagTransferRequest defines the method to refuse an incoming DTag
     * transfer request
     * </pre>
     */
    public void refuseDTagTransferRequest(com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequestResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRefuseDTagTransferRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * LinkChainAccount defines a method to link an external chain account to a
     * profile
     * </pre>
     */
    public void linkChainAccount(com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccount request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLinkChainAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnlinkChainAccount defines a method to unlink an external chain account
     * from a profile
     * </pre>
     */
    public void unlinkChainAccount(com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccount request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnlinkChainAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SetDefaultExternalAddress allows to set a specific external address as the
     * default one for a given chain
     * </pre>
     */
    public void setDefaultExternalAddress(com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddress request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddressResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetDefaultExternalAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * LinkApplication defines a method to create a centralized application
     * link
     * </pre>
     */
    public void linkApplication(com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplication request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplicationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLinkApplicationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnlinkApplication defines a method to remove a centralized application
     * </pre>
     */
    public void unlinkApplication(com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplication request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplicationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnlinkApplicationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateParams defines a (governance) operation for updating the module
     * parameters.
     * The authority defaults to the x/gov module account.
     * Since: Desmos 5.0.0
     * </pre>
     */
    public void updateParams(com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the relationships Msg service.
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
     * SaveProfile defines the method to save a profile
     * </pre>
     */
    public com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfileResponse saveProfile(com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfile request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSaveProfileMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteProfile defines the method to delete an existing profile
     * </pre>
     */
    public com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfileResponse deleteProfile(com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfile request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteProfileMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RequestDTagTransfer defines the method to request another user to transfer
     * their DTag to you
     * </pre>
     */
    public com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransferResponse requestDTagTransfer(com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransfer request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRequestDTagTransferMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CancelDTagTransferRequest defines the method to cancel an outgoing DTag
     * transfer request
     * </pre>
     */
    public com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequestResponse cancelDTagTransferRequest(com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCancelDTagTransferRequestMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AcceptDTagTransferRequest defines the method to accept an incoming DTag
     * transfer request
     * </pre>
     */
    public com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequestResponse acceptDTagTransferRequest(com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAcceptDTagTransferRequestMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RefuseDTagTransferRequest defines the method to refuse an incoming DTag
     * transfer request
     * </pre>
     */
    public com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequestResponse refuseDTagTransferRequest(com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRefuseDTagTransferRequestMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * LinkChainAccount defines a method to link an external chain account to a
     * profile
     * </pre>
     */
    public com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccountResponse linkChainAccount(com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccount request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLinkChainAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnlinkChainAccount defines a method to unlink an external chain account
     * from a profile
     * </pre>
     */
    public com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccountResponse unlinkChainAccount(com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccount request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnlinkChainAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SetDefaultExternalAddress allows to set a specific external address as the
     * default one for a given chain
     * </pre>
     */
    public com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddressResponse setDefaultExternalAddress(com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddress request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetDefaultExternalAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * LinkApplication defines a method to create a centralized application
     * link
     * </pre>
     */
    public com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplicationResponse linkApplication(com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplication request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLinkApplicationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnlinkApplication defines a method to remove a centralized application
     * </pre>
     */
    public com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplicationResponse unlinkApplication(com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplication request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnlinkApplicationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateParams defines a (governance) operation for updating the module
     * parameters.
     * The authority defaults to the x/gov module account.
     * Since: Desmos 5.0.0
     * </pre>
     */
    public com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParamsResponse updateParams(com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the relationships Msg service.
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
     * SaveProfile defines the method to save a profile
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfileResponse> saveProfile(
        com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfile request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSaveProfileMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteProfile defines the method to delete an existing profile
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfileResponse> deleteProfile(
        com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfile request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteProfileMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RequestDTagTransfer defines the method to request another user to transfer
     * their DTag to you
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransferResponse> requestDTagTransfer(
        com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransfer request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRequestDTagTransferMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CancelDTagTransferRequest defines the method to cancel an outgoing DTag
     * transfer request
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequestResponse> cancelDTagTransferRequest(
        com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCancelDTagTransferRequestMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AcceptDTagTransferRequest defines the method to accept an incoming DTag
     * transfer request
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequestResponse> acceptDTagTransferRequest(
        com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAcceptDTagTransferRequestMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RefuseDTagTransferRequest defines the method to refuse an incoming DTag
     * transfer request
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequestResponse> refuseDTagTransferRequest(
        com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRefuseDTagTransferRequestMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * LinkChainAccount defines a method to link an external chain account to a
     * profile
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccountResponse> linkChainAccount(
        com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccount request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLinkChainAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnlinkChainAccount defines a method to unlink an external chain account
     * from a profile
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccountResponse> unlinkChainAccount(
        com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccount request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnlinkChainAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SetDefaultExternalAddress allows to set a specific external address as the
     * default one for a given chain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddressResponse> setDefaultExternalAddress(
        com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddress request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetDefaultExternalAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * LinkApplication defines a method to create a centralized application
     * link
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplicationResponse> linkApplication(
        com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplication request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLinkApplicationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnlinkApplication defines a method to remove a centralized application
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplicationResponse> unlinkApplication(
        com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplication request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnlinkApplicationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateParams defines a (governance) operation for updating the module
     * parameters.
     * The authority defaults to the x/gov module account.
     * Since: Desmos 5.0.0
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParamsResponse> updateParams(
        com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAVE_PROFILE = 0;
  private static final int METHODID_DELETE_PROFILE = 1;
  private static final int METHODID_REQUEST_DTAG_TRANSFER = 2;
  private static final int METHODID_CANCEL_DTAG_TRANSFER_REQUEST = 3;
  private static final int METHODID_ACCEPT_DTAG_TRANSFER_REQUEST = 4;
  private static final int METHODID_REFUSE_DTAG_TRANSFER_REQUEST = 5;
  private static final int METHODID_LINK_CHAIN_ACCOUNT = 6;
  private static final int METHODID_UNLINK_CHAIN_ACCOUNT = 7;
  private static final int METHODID_SET_DEFAULT_EXTERNAL_ADDRESS = 8;
  private static final int METHODID_LINK_APPLICATION = 9;
  private static final int METHODID_UNLINK_APPLICATION = 10;
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
        case METHODID_SAVE_PROFILE:
          serviceImpl.saveProfile((com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfile) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfileResponse>) responseObserver);
          break;
        case METHODID_DELETE_PROFILE:
          serviceImpl.deleteProfile((com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfile) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfileResponse>) responseObserver);
          break;
        case METHODID_REQUEST_DTAG_TRANSFER:
          serviceImpl.requestDTagTransfer((com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransfer) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransferResponse>) responseObserver);
          break;
        case METHODID_CANCEL_DTAG_TRANSFER_REQUEST:
          serviceImpl.cancelDTagTransferRequest((com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequestResponse>) responseObserver);
          break;
        case METHODID_ACCEPT_DTAG_TRANSFER_REQUEST:
          serviceImpl.acceptDTagTransferRequest((com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequestResponse>) responseObserver);
          break;
        case METHODID_REFUSE_DTAG_TRANSFER_REQUEST:
          serviceImpl.refuseDTagTransferRequest((com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequestResponse>) responseObserver);
          break;
        case METHODID_LINK_CHAIN_ACCOUNT:
          serviceImpl.linkChainAccount((com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccount) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccountResponse>) responseObserver);
          break;
        case METHODID_UNLINK_CHAIN_ACCOUNT:
          serviceImpl.unlinkChainAccount((com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccount) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccountResponse>) responseObserver);
          break;
        case METHODID_SET_DEFAULT_EXTERNAL_ADDRESS:
          serviceImpl.setDefaultExternalAddress((com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddress) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddressResponse>) responseObserver);
          break;
        case METHODID_LINK_APPLICATION:
          serviceImpl.linkApplication((com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplication) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplicationResponse>) responseObserver);
          break;
        case METHODID_UNLINK_APPLICATION:
          serviceImpl.unlinkApplication((com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplication) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplicationResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PARAMS:
          serviceImpl.updateParams((com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParams) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParamsResponse>) responseObserver);
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
          getSaveProfileMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfile,
              com.desmos.profiles.v3.MsgsProfileProto.MsgSaveProfileResponse>(
                service, METHODID_SAVE_PROFILE)))
        .addMethod(
          getDeleteProfileMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfile,
              com.desmos.profiles.v3.MsgsProfileProto.MsgDeleteProfileResponse>(
                service, METHODID_DELETE_PROFILE)))
        .addMethod(
          getRequestDTagTransferMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransfer,
              com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRequestDTagTransferResponse>(
                service, METHODID_REQUEST_DTAG_TRANSFER)))
        .addMethod(
          getCancelDTagTransferRequestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequest,
              com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgCancelDTagTransferRequestResponse>(
                service, METHODID_CANCEL_DTAG_TRANSFER_REQUEST)))
        .addMethod(
          getAcceptDTagTransferRequestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequest,
              com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgAcceptDTagTransferRequestResponse>(
                service, METHODID_ACCEPT_DTAG_TRANSFER_REQUEST)))
        .addMethod(
          getRefuseDTagTransferRequestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequest,
              com.desmos.profiles.v3.MsgsDtagRequestsProto.MsgRefuseDTagTransferRequestResponse>(
                service, METHODID_REFUSE_DTAG_TRANSFER_REQUEST)))
        .addMethod(
          getLinkChainAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccount,
              com.desmos.profiles.v3.MsgsChainLinksProto.MsgLinkChainAccountResponse>(
                service, METHODID_LINK_CHAIN_ACCOUNT)))
        .addMethod(
          getUnlinkChainAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccount,
              com.desmos.profiles.v3.MsgsChainLinksProto.MsgUnlinkChainAccountResponse>(
                service, METHODID_UNLINK_CHAIN_ACCOUNT)))
        .addMethod(
          getSetDefaultExternalAddressMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddress,
              com.desmos.profiles.v3.MsgsChainLinksProto.MsgSetDefaultExternalAddressResponse>(
                service, METHODID_SET_DEFAULT_EXTERNAL_ADDRESS)))
        .addMethod(
          getLinkApplicationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplication,
              com.desmos.profiles.v3.MsgsAppLinksProto.MsgLinkApplicationResponse>(
                service, METHODID_LINK_APPLICATION)))
        .addMethod(
          getUnlinkApplicationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplication,
              com.desmos.profiles.v3.MsgsAppLinksProto.MsgUnlinkApplicationResponse>(
                service, METHODID_UNLINK_APPLICATION)))
        .addMethod(
          getUpdateParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParams,
              com.desmos.profiles.v3.MsgsParamsProto.MsgUpdateParamsResponse>(
                service, METHODID_UPDATE_PARAMS)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.desmos.profiles.v3.MsgServerProto.getDescriptor();
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
              .addMethod(getSaveProfileMethod())
              .addMethod(getDeleteProfileMethod())
              .addMethod(getRequestDTagTransferMethod())
              .addMethod(getCancelDTagTransferRequestMethod())
              .addMethod(getAcceptDTagTransferRequestMethod())
              .addMethod(getRefuseDTagTransferRequestMethod())
              .addMethod(getLinkChainAccountMethod())
              .addMethod(getUnlinkChainAccountMethod())
              .addMethod(getSetDefaultExternalAddressMethod())
              .addMethod(getLinkApplicationMethod())
              .addMethod(getUnlinkApplicationMethod())
              .addMethod(getUpdateParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
