package desmos.profiles.v3;

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
 * Msg defines the relationships Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: desmos/profiles/v3/msg_server.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "desmos.profiles.v3.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsProfile.MsgSaveProfile,
      desmos.profiles.v3.MsgsProfile.MsgSaveProfileResponse> getSaveProfileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SaveProfile",
      requestType = desmos.profiles.v3.MsgsProfile.MsgSaveProfile.class,
      responseType = desmos.profiles.v3.MsgsProfile.MsgSaveProfileResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsProfile.MsgSaveProfile,
      desmos.profiles.v3.MsgsProfile.MsgSaveProfileResponse> getSaveProfileMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsProfile.MsgSaveProfile, desmos.profiles.v3.MsgsProfile.MsgSaveProfileResponse> getSaveProfileMethod;
    if ((getSaveProfileMethod = MsgGrpc.getSaveProfileMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSaveProfileMethod = MsgGrpc.getSaveProfileMethod) == null) {
          MsgGrpc.getSaveProfileMethod = getSaveProfileMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.MsgsProfile.MsgSaveProfile, desmos.profiles.v3.MsgsProfile.MsgSaveProfileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SaveProfile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsProfile.MsgSaveProfile.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsProfile.MsgSaveProfileResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SaveProfile"))
              .build();
        }
      }
    }
    return getSaveProfileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsProfile.MsgDeleteProfile,
      desmos.profiles.v3.MsgsProfile.MsgDeleteProfileResponse> getDeleteProfileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteProfile",
      requestType = desmos.profiles.v3.MsgsProfile.MsgDeleteProfile.class,
      responseType = desmos.profiles.v3.MsgsProfile.MsgDeleteProfileResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsProfile.MsgDeleteProfile,
      desmos.profiles.v3.MsgsProfile.MsgDeleteProfileResponse> getDeleteProfileMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsProfile.MsgDeleteProfile, desmos.profiles.v3.MsgsProfile.MsgDeleteProfileResponse> getDeleteProfileMethod;
    if ((getDeleteProfileMethod = MsgGrpc.getDeleteProfileMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteProfileMethod = MsgGrpc.getDeleteProfileMethod) == null) {
          MsgGrpc.getDeleteProfileMethod = getDeleteProfileMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.MsgsProfile.MsgDeleteProfile, desmos.profiles.v3.MsgsProfile.MsgDeleteProfileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteProfile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsProfile.MsgDeleteProfile.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsProfile.MsgDeleteProfileResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteProfile"))
              .build();
        }
      }
    }
    return getDeleteProfileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransfer,
      desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransferResponse> getRequestDTagTransferMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestDTagTransfer",
      requestType = desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransfer.class,
      responseType = desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransferResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransfer,
      desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransferResponse> getRequestDTagTransferMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransfer, desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransferResponse> getRequestDTagTransferMethod;
    if ((getRequestDTagTransferMethod = MsgGrpc.getRequestDTagTransferMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRequestDTagTransferMethod = MsgGrpc.getRequestDTagTransferMethod) == null) {
          MsgGrpc.getRequestDTagTransferMethod = getRequestDTagTransferMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransfer, desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransferResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestDTagTransfer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransfer.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransferResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RequestDTagTransfer"))
              .build();
        }
      }
    }
    return getRequestDTagTransferMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequest,
      desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequestResponse> getCancelDTagTransferRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelDTagTransferRequest",
      requestType = desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequest.class,
      responseType = desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequest,
      desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequestResponse> getCancelDTagTransferRequestMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequest, desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequestResponse> getCancelDTagTransferRequestMethod;
    if ((getCancelDTagTransferRequestMethod = MsgGrpc.getCancelDTagTransferRequestMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelDTagTransferRequestMethod = MsgGrpc.getCancelDTagTransferRequestMethod) == null) {
          MsgGrpc.getCancelDTagTransferRequestMethod = getCancelDTagTransferRequestMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequest, desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelDTagTransferRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelDTagTransferRequest"))
              .build();
        }
      }
    }
    return getCancelDTagTransferRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequest,
      desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequestResponse> getAcceptDTagTransferRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AcceptDTagTransferRequest",
      requestType = desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequest.class,
      responseType = desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequest,
      desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequestResponse> getAcceptDTagTransferRequestMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequest, desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequestResponse> getAcceptDTagTransferRequestMethod;
    if ((getAcceptDTagTransferRequestMethod = MsgGrpc.getAcceptDTagTransferRequestMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAcceptDTagTransferRequestMethod = MsgGrpc.getAcceptDTagTransferRequestMethod) == null) {
          MsgGrpc.getAcceptDTagTransferRequestMethod = getAcceptDTagTransferRequestMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequest, desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AcceptDTagTransferRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AcceptDTagTransferRequest"))
              .build();
        }
      }
    }
    return getAcceptDTagTransferRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequest,
      desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequestResponse> getRefuseDTagTransferRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RefuseDTagTransferRequest",
      requestType = desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequest.class,
      responseType = desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequest,
      desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequestResponse> getRefuseDTagTransferRequestMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequest, desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequestResponse> getRefuseDTagTransferRequestMethod;
    if ((getRefuseDTagTransferRequestMethod = MsgGrpc.getRefuseDTagTransferRequestMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRefuseDTagTransferRequestMethod = MsgGrpc.getRefuseDTagTransferRequestMethod) == null) {
          MsgGrpc.getRefuseDTagTransferRequestMethod = getRefuseDTagTransferRequestMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequest, desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RefuseDTagTransferRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RefuseDTagTransferRequest"))
              .build();
        }
      }
    }
    return getRefuseDTagTransferRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccount,
      desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccountResponse> getLinkChainAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LinkChainAccount",
      requestType = desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccount.class,
      responseType = desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccount,
      desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccountResponse> getLinkChainAccountMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccount, desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccountResponse> getLinkChainAccountMethod;
    if ((getLinkChainAccountMethod = MsgGrpc.getLinkChainAccountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLinkChainAccountMethod = MsgGrpc.getLinkChainAccountMethod) == null) {
          MsgGrpc.getLinkChainAccountMethod = getLinkChainAccountMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccount, desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LinkChainAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("LinkChainAccount"))
              .build();
        }
      }
    }
    return getLinkChainAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccount,
      desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccountResponse> getUnlinkChainAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnlinkChainAccount",
      requestType = desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccount.class,
      responseType = desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccount,
      desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccountResponse> getUnlinkChainAccountMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccount, desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccountResponse> getUnlinkChainAccountMethod;
    if ((getUnlinkChainAccountMethod = MsgGrpc.getUnlinkChainAccountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUnlinkChainAccountMethod = MsgGrpc.getUnlinkChainAccountMethod) == null) {
          MsgGrpc.getUnlinkChainAccountMethod = getUnlinkChainAccountMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccount, desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnlinkChainAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UnlinkChainAccount"))
              .build();
        }
      }
    }
    return getUnlinkChainAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddress,
      desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddressResponse> getSetDefaultExternalAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetDefaultExternalAddress",
      requestType = desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddress.class,
      responseType = desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddress,
      desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddressResponse> getSetDefaultExternalAddressMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddress, desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddressResponse> getSetDefaultExternalAddressMethod;
    if ((getSetDefaultExternalAddressMethod = MsgGrpc.getSetDefaultExternalAddressMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetDefaultExternalAddressMethod = MsgGrpc.getSetDefaultExternalAddressMethod) == null) {
          MsgGrpc.getSetDefaultExternalAddressMethod = getSetDefaultExternalAddressMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddress, desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetDefaultExternalAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddress.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetDefaultExternalAddress"))
              .build();
        }
      }
    }
    return getSetDefaultExternalAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsAppLinks.MsgLinkApplication,
      desmos.profiles.v3.MsgsAppLinks.MsgLinkApplicationResponse> getLinkApplicationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LinkApplication",
      requestType = desmos.profiles.v3.MsgsAppLinks.MsgLinkApplication.class,
      responseType = desmos.profiles.v3.MsgsAppLinks.MsgLinkApplicationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsAppLinks.MsgLinkApplication,
      desmos.profiles.v3.MsgsAppLinks.MsgLinkApplicationResponse> getLinkApplicationMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsAppLinks.MsgLinkApplication, desmos.profiles.v3.MsgsAppLinks.MsgLinkApplicationResponse> getLinkApplicationMethod;
    if ((getLinkApplicationMethod = MsgGrpc.getLinkApplicationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLinkApplicationMethod = MsgGrpc.getLinkApplicationMethod) == null) {
          MsgGrpc.getLinkApplicationMethod = getLinkApplicationMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.MsgsAppLinks.MsgLinkApplication, desmos.profiles.v3.MsgsAppLinks.MsgLinkApplicationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LinkApplication"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsAppLinks.MsgLinkApplication.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsAppLinks.MsgLinkApplicationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("LinkApplication"))
              .build();
        }
      }
    }
    return getLinkApplicationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplication,
      desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplicationResponse> getUnlinkApplicationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnlinkApplication",
      requestType = desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplication.class,
      responseType = desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplicationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplication,
      desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplicationResponse> getUnlinkApplicationMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplication, desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplicationResponse> getUnlinkApplicationMethod;
    if ((getUnlinkApplicationMethod = MsgGrpc.getUnlinkApplicationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUnlinkApplicationMethod = MsgGrpc.getUnlinkApplicationMethod) == null) {
          MsgGrpc.getUnlinkApplicationMethod = getUnlinkApplicationMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplication, desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplicationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnlinkApplication"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplication.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplicationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UnlinkApplication"))
              .build();
        }
      }
    }
    return getUnlinkApplicationMethod;
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
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * SaveProfile defines the method to save a profile
     * </pre>
     */
    public void saveProfile(desmos.profiles.v3.MsgsProfile.MsgSaveProfile request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsProfile.MsgSaveProfileResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSaveProfileMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteProfile defines the method to delete an existing profile
     * </pre>
     */
    public void deleteProfile(desmos.profiles.v3.MsgsProfile.MsgDeleteProfile request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsProfile.MsgDeleteProfileResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteProfileMethod(), responseObserver);
    }

    /**
     * <pre>
     * RequestDTagTransfer defines the method to request another user to transfer
     * their DTag to you
     * </pre>
     */
    public void requestDTagTransfer(desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransfer request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransferResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestDTagTransferMethod(), responseObserver);
    }

    /**
     * <pre>
     * CancelDTagTransferRequest defines the method to cancel an outgoing DTag
     * transfer request
     * </pre>
     */
    public void cancelDTagTransferRequest(desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelDTagTransferRequestMethod(), responseObserver);
    }

    /**
     * <pre>
     * AcceptDTagTransferRequest defines the method to accept an incoming DTag
     * transfer request
     * </pre>
     */
    public void acceptDTagTransferRequest(desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAcceptDTagTransferRequestMethod(), responseObserver);
    }

    /**
     * <pre>
     * RefuseDTagTransferRequest defines the method to refuse an incoming DTag
     * transfer request
     * </pre>
     */
    public void refuseDTagTransferRequest(desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRefuseDTagTransferRequestMethod(), responseObserver);
    }

    /**
     * <pre>
     * LinkChainAccount defines a method to link an external chain account to a
     * profile
     * </pre>
     */
    public void linkChainAccount(desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccount request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLinkChainAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnlinkChainAccount defines a method to unlink an external chain account
     * from a profile
     * </pre>
     */
    public void unlinkChainAccount(desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccount request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnlinkChainAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * SetDefaultExternalAddress allows to set a specific external address as the
     * default one for a given chain
     * </pre>
     */
    public void setDefaultExternalAddress(desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddress request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddressResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetDefaultExternalAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * LinkApplication defines a method to create a centralized application
     * link
     * </pre>
     */
    public void linkApplication(desmos.profiles.v3.MsgsAppLinks.MsgLinkApplication request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsAppLinks.MsgLinkApplicationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLinkApplicationMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnlinkApplication defines a method to remove a centralized application
     * </pre>
     */
    public void unlinkApplication(desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplication request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplicationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnlinkApplicationMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSaveProfileMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.MsgsProfile.MsgSaveProfile,
                desmos.profiles.v3.MsgsProfile.MsgSaveProfileResponse>(
                  this, METHODID_SAVE_PROFILE)))
          .addMethod(
            getDeleteProfileMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.MsgsProfile.MsgDeleteProfile,
                desmos.profiles.v3.MsgsProfile.MsgDeleteProfileResponse>(
                  this, METHODID_DELETE_PROFILE)))
          .addMethod(
            getRequestDTagTransferMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransfer,
                desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransferResponse>(
                  this, METHODID_REQUEST_DTAG_TRANSFER)))
          .addMethod(
            getCancelDTagTransferRequestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequest,
                desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequestResponse>(
                  this, METHODID_CANCEL_DTAG_TRANSFER_REQUEST)))
          .addMethod(
            getAcceptDTagTransferRequestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequest,
                desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequestResponse>(
                  this, METHODID_ACCEPT_DTAG_TRANSFER_REQUEST)))
          .addMethod(
            getRefuseDTagTransferRequestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequest,
                desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequestResponse>(
                  this, METHODID_REFUSE_DTAG_TRANSFER_REQUEST)))
          .addMethod(
            getLinkChainAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccount,
                desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccountResponse>(
                  this, METHODID_LINK_CHAIN_ACCOUNT)))
          .addMethod(
            getUnlinkChainAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccount,
                desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccountResponse>(
                  this, METHODID_UNLINK_CHAIN_ACCOUNT)))
          .addMethod(
            getSetDefaultExternalAddressMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddress,
                desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddressResponse>(
                  this, METHODID_SET_DEFAULT_EXTERNAL_ADDRESS)))
          .addMethod(
            getLinkApplicationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.MsgsAppLinks.MsgLinkApplication,
                desmos.profiles.v3.MsgsAppLinks.MsgLinkApplicationResponse>(
                  this, METHODID_LINK_APPLICATION)))
          .addMethod(
            getUnlinkApplicationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplication,
                desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplicationResponse>(
                  this, METHODID_UNLINK_APPLICATION)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the relationships Msg service.
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
     * SaveProfile defines the method to save a profile
     * </pre>
     */
    public void saveProfile(desmos.profiles.v3.MsgsProfile.MsgSaveProfile request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsProfile.MsgSaveProfileResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSaveProfileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteProfile defines the method to delete an existing profile
     * </pre>
     */
    public void deleteProfile(desmos.profiles.v3.MsgsProfile.MsgDeleteProfile request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsProfile.MsgDeleteProfileResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteProfileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RequestDTagTransfer defines the method to request another user to transfer
     * their DTag to you
     * </pre>
     */
    public void requestDTagTransfer(desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransfer request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransferResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestDTagTransferMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CancelDTagTransferRequest defines the method to cancel an outgoing DTag
     * transfer request
     * </pre>
     */
    public void cancelDTagTransferRequest(desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelDTagTransferRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AcceptDTagTransferRequest defines the method to accept an incoming DTag
     * transfer request
     * </pre>
     */
    public void acceptDTagTransferRequest(desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAcceptDTagTransferRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RefuseDTagTransferRequest defines the method to refuse an incoming DTag
     * transfer request
     * </pre>
     */
    public void refuseDTagTransferRequest(desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRefuseDTagTransferRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * LinkChainAccount defines a method to link an external chain account to a
     * profile
     * </pre>
     */
    public void linkChainAccount(desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccount request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLinkChainAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnlinkChainAccount defines a method to unlink an external chain account
     * from a profile
     * </pre>
     */
    public void unlinkChainAccount(desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccount request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnlinkChainAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SetDefaultExternalAddress allows to set a specific external address as the
     * default one for a given chain
     * </pre>
     */
    public void setDefaultExternalAddress(desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddress request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetDefaultExternalAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * LinkApplication defines a method to create a centralized application
     * link
     * </pre>
     */
    public void linkApplication(desmos.profiles.v3.MsgsAppLinks.MsgLinkApplication request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsAppLinks.MsgLinkApplicationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLinkApplicationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnlinkApplication defines a method to remove a centralized application
     * </pre>
     */
    public void unlinkApplication(desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplication request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplicationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnlinkApplicationMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the relationships Msg service.
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
     * SaveProfile defines the method to save a profile
     * </pre>
     */
    public desmos.profiles.v3.MsgsProfile.MsgSaveProfileResponse saveProfile(desmos.profiles.v3.MsgsProfile.MsgSaveProfile request) {
      return blockingUnaryCall(
          getChannel(), getSaveProfileMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteProfile defines the method to delete an existing profile
     * </pre>
     */
    public desmos.profiles.v3.MsgsProfile.MsgDeleteProfileResponse deleteProfile(desmos.profiles.v3.MsgsProfile.MsgDeleteProfile request) {
      return blockingUnaryCall(
          getChannel(), getDeleteProfileMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RequestDTagTransfer defines the method to request another user to transfer
     * their DTag to you
     * </pre>
     */
    public desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransferResponse requestDTagTransfer(desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransfer request) {
      return blockingUnaryCall(
          getChannel(), getRequestDTagTransferMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CancelDTagTransferRequest defines the method to cancel an outgoing DTag
     * transfer request
     * </pre>
     */
    public desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequestResponse cancelDTagTransferRequest(desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequest request) {
      return blockingUnaryCall(
          getChannel(), getCancelDTagTransferRequestMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AcceptDTagTransferRequest defines the method to accept an incoming DTag
     * transfer request
     * </pre>
     */
    public desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequestResponse acceptDTagTransferRequest(desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequest request) {
      return blockingUnaryCall(
          getChannel(), getAcceptDTagTransferRequestMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RefuseDTagTransferRequest defines the method to refuse an incoming DTag
     * transfer request
     * </pre>
     */
    public desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequestResponse refuseDTagTransferRequest(desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequest request) {
      return blockingUnaryCall(
          getChannel(), getRefuseDTagTransferRequestMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * LinkChainAccount defines a method to link an external chain account to a
     * profile
     * </pre>
     */
    public desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccountResponse linkChainAccount(desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccount request) {
      return blockingUnaryCall(
          getChannel(), getLinkChainAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnlinkChainAccount defines a method to unlink an external chain account
     * from a profile
     * </pre>
     */
    public desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccountResponse unlinkChainAccount(desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccount request) {
      return blockingUnaryCall(
          getChannel(), getUnlinkChainAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SetDefaultExternalAddress allows to set a specific external address as the
     * default one for a given chain
     * </pre>
     */
    public desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddressResponse setDefaultExternalAddress(desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddress request) {
      return blockingUnaryCall(
          getChannel(), getSetDefaultExternalAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * LinkApplication defines a method to create a centralized application
     * link
     * </pre>
     */
    public desmos.profiles.v3.MsgsAppLinks.MsgLinkApplicationResponse linkApplication(desmos.profiles.v3.MsgsAppLinks.MsgLinkApplication request) {
      return blockingUnaryCall(
          getChannel(), getLinkApplicationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnlinkApplication defines a method to remove a centralized application
     * </pre>
     */
    public desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplicationResponse unlinkApplication(desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplication request) {
      return blockingUnaryCall(
          getChannel(), getUnlinkApplicationMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the relationships Msg service.
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
     * SaveProfile defines the method to save a profile
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.MsgsProfile.MsgSaveProfileResponse> saveProfile(
        desmos.profiles.v3.MsgsProfile.MsgSaveProfile request) {
      return futureUnaryCall(
          getChannel().newCall(getSaveProfileMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteProfile defines the method to delete an existing profile
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.MsgsProfile.MsgDeleteProfileResponse> deleteProfile(
        desmos.profiles.v3.MsgsProfile.MsgDeleteProfile request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteProfileMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RequestDTagTransfer defines the method to request another user to transfer
     * their DTag to you
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransferResponse> requestDTagTransfer(
        desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransfer request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestDTagTransferMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CancelDTagTransferRequest defines the method to cancel an outgoing DTag
     * transfer request
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequestResponse> cancelDTagTransferRequest(
        desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelDTagTransferRequestMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AcceptDTagTransferRequest defines the method to accept an incoming DTag
     * transfer request
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequestResponse> acceptDTagTransferRequest(
        desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAcceptDTagTransferRequestMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RefuseDTagTransferRequest defines the method to refuse an incoming DTag
     * transfer request
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequestResponse> refuseDTagTransferRequest(
        desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRefuseDTagTransferRequestMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * LinkChainAccount defines a method to link an external chain account to a
     * profile
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccountResponse> linkChainAccount(
        desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccount request) {
      return futureUnaryCall(
          getChannel().newCall(getLinkChainAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnlinkChainAccount defines a method to unlink an external chain account
     * from a profile
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccountResponse> unlinkChainAccount(
        desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccount request) {
      return futureUnaryCall(
          getChannel().newCall(getUnlinkChainAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SetDefaultExternalAddress allows to set a specific external address as the
     * default one for a given chain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddressResponse> setDefaultExternalAddress(
        desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddress request) {
      return futureUnaryCall(
          getChannel().newCall(getSetDefaultExternalAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * LinkApplication defines a method to create a centralized application
     * link
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.MsgsAppLinks.MsgLinkApplicationResponse> linkApplication(
        desmos.profiles.v3.MsgsAppLinks.MsgLinkApplication request) {
      return futureUnaryCall(
          getChannel().newCall(getLinkApplicationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnlinkApplication defines a method to remove a centralized application
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplicationResponse> unlinkApplication(
        desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplication request) {
      return futureUnaryCall(
          getChannel().newCall(getUnlinkApplicationMethod(), getCallOptions()), request);
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
        case METHODID_SAVE_PROFILE:
          serviceImpl.saveProfile((desmos.profiles.v3.MsgsProfile.MsgSaveProfile) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsProfile.MsgSaveProfileResponse>) responseObserver);
          break;
        case METHODID_DELETE_PROFILE:
          serviceImpl.deleteProfile((desmos.profiles.v3.MsgsProfile.MsgDeleteProfile) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsProfile.MsgDeleteProfileResponse>) responseObserver);
          break;
        case METHODID_REQUEST_DTAG_TRANSFER:
          serviceImpl.requestDTagTransfer((desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransfer) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsDtagRequests.MsgRequestDTagTransferResponse>) responseObserver);
          break;
        case METHODID_CANCEL_DTAG_TRANSFER_REQUEST:
          serviceImpl.cancelDTagTransferRequest((desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsDtagRequests.MsgCancelDTagTransferRequestResponse>) responseObserver);
          break;
        case METHODID_ACCEPT_DTAG_TRANSFER_REQUEST:
          serviceImpl.acceptDTagTransferRequest((desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsDtagRequests.MsgAcceptDTagTransferRequestResponse>) responseObserver);
          break;
        case METHODID_REFUSE_DTAG_TRANSFER_REQUEST:
          serviceImpl.refuseDTagTransferRequest((desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsDtagRequests.MsgRefuseDTagTransferRequestResponse>) responseObserver);
          break;
        case METHODID_LINK_CHAIN_ACCOUNT:
          serviceImpl.linkChainAccount((desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccount) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsChainLinks.MsgLinkChainAccountResponse>) responseObserver);
          break;
        case METHODID_UNLINK_CHAIN_ACCOUNT:
          serviceImpl.unlinkChainAccount((desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccount) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsChainLinks.MsgUnlinkChainAccountResponse>) responseObserver);
          break;
        case METHODID_SET_DEFAULT_EXTERNAL_ADDRESS:
          serviceImpl.setDefaultExternalAddress((desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddress) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsChainLinks.MsgSetDefaultExternalAddressResponse>) responseObserver);
          break;
        case METHODID_LINK_APPLICATION:
          serviceImpl.linkApplication((desmos.profiles.v3.MsgsAppLinks.MsgLinkApplication) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsAppLinks.MsgLinkApplicationResponse>) responseObserver);
          break;
        case METHODID_UNLINK_APPLICATION:
          serviceImpl.unlinkApplication((desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplication) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.MsgsAppLinks.MsgUnlinkApplicationResponse>) responseObserver);
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
      return desmos.profiles.v3.MsgServer.getDescriptor();
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
              .build();
        }
      }
    }
    return result;
  }
}
