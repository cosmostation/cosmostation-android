package starnamed.x.starname.v1beta1;

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
 * Msg defines the starname Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: starname/iov/starname/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "starnamed.x.starname.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificate,
      starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificateResponse> getAddAccountCertificateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddAccountCertificate",
      requestType = starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificate.class,
      responseType = starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificate,
      starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificateResponse> getAddAccountCertificateMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificate, starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificateResponse> getAddAccountCertificateMethod;
    if ((getAddAccountCertificateMethod = MsgGrpc.getAddAccountCertificateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddAccountCertificateMethod = MsgGrpc.getAddAccountCertificateMethod) == null) {
          MsgGrpc.getAddAccountCertificateMethod = getAddAccountCertificateMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificate, starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddAccountCertificate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddAccountCertificate"))
              .build();
        }
      }
    }
    return getAddAccountCertificateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount,
      starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountResponse> getDeleteAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteAccount",
      requestType = starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount.class,
      responseType = starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount,
      starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountResponse> getDeleteAccountMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount, starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountResponse> getDeleteAccountMethod;
    if ((getDeleteAccountMethod = MsgGrpc.getDeleteAccountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteAccountMethod = MsgGrpc.getDeleteAccountMethod) == null) {
          MsgGrpc.getDeleteAccountMethod = getDeleteAccountMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount, starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteAccount"))
              .build();
        }
      }
    }
    return getDeleteAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificate,
      starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificateResponse> getDeleteAccountCertificateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteAccountCertificate",
      requestType = starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificate.class,
      responseType = starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificate,
      starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificateResponse> getDeleteAccountCertificateMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificate, starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificateResponse> getDeleteAccountCertificateMethod;
    if ((getDeleteAccountCertificateMethod = MsgGrpc.getDeleteAccountCertificateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteAccountCertificateMethod = MsgGrpc.getDeleteAccountCertificateMethod) == null) {
          MsgGrpc.getDeleteAccountCertificateMethod = getDeleteAccountCertificateMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificate, starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteAccountCertificate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteAccountCertificate"))
              .build();
        }
      }
    }
    return getDeleteAccountCertificateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain,
      starnamed.x.starname.v1beta1.Tx.MsgDeleteDomainResponse> getDeleteDomainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteDomain",
      requestType = starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain.class,
      responseType = starnamed.x.starname.v1beta1.Tx.MsgDeleteDomainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain,
      starnamed.x.starname.v1beta1.Tx.MsgDeleteDomainResponse> getDeleteDomainMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain, starnamed.x.starname.v1beta1.Tx.MsgDeleteDomainResponse> getDeleteDomainMethod;
    if ((getDeleteDomainMethod = MsgGrpc.getDeleteDomainMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteDomainMethod = MsgGrpc.getDeleteDomainMethod) == null) {
          MsgGrpc.getDeleteDomainMethod = getDeleteDomainMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain, starnamed.x.starname.v1beta1.Tx.MsgDeleteDomainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteDomain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgDeleteDomainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteDomain"))
              .build();
        }
      }
    }
    return getDeleteDomainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount,
      starnamed.x.starname.v1beta1.Tx.MsgRegisterAccountResponse> getRegisterAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterAccount",
      requestType = starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount.class,
      responseType = starnamed.x.starname.v1beta1.Tx.MsgRegisterAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount,
      starnamed.x.starname.v1beta1.Tx.MsgRegisterAccountResponse> getRegisterAccountMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount, starnamed.x.starname.v1beta1.Tx.MsgRegisterAccountResponse> getRegisterAccountMethod;
    if ((getRegisterAccountMethod = MsgGrpc.getRegisterAccountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRegisterAccountMethod = MsgGrpc.getRegisterAccountMethod) == null) {
          MsgGrpc.getRegisterAccountMethod = getRegisterAccountMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount, starnamed.x.starname.v1beta1.Tx.MsgRegisterAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgRegisterAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RegisterAccount"))
              .build();
        }
      }
    }
    return getRegisterAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain,
      starnamed.x.starname.v1beta1.Tx.MsgRegisterDomainResponse> getRegisterDomainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterDomain",
      requestType = starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain.class,
      responseType = starnamed.x.starname.v1beta1.Tx.MsgRegisterDomainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain,
      starnamed.x.starname.v1beta1.Tx.MsgRegisterDomainResponse> getRegisterDomainMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain, starnamed.x.starname.v1beta1.Tx.MsgRegisterDomainResponse> getRegisterDomainMethod;
    if ((getRegisterDomainMethod = MsgGrpc.getRegisterDomainMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRegisterDomainMethod = MsgGrpc.getRegisterDomainMethod) == null) {
          MsgGrpc.getRegisterDomainMethod = getRegisterDomainMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain, starnamed.x.starname.v1beta1.Tx.MsgRegisterDomainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterDomain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgRegisterDomainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RegisterDomain"))
              .build();
        }
      }
    }
    return getRegisterDomainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgRenewAccount,
      starnamed.x.starname.v1beta1.Tx.MsgRenewAccountResponse> getRenewAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RenewAccount",
      requestType = starnamed.x.starname.v1beta1.Tx.MsgRenewAccount.class,
      responseType = starnamed.x.starname.v1beta1.Tx.MsgRenewAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgRenewAccount,
      starnamed.x.starname.v1beta1.Tx.MsgRenewAccountResponse> getRenewAccountMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgRenewAccount, starnamed.x.starname.v1beta1.Tx.MsgRenewAccountResponse> getRenewAccountMethod;
    if ((getRenewAccountMethod = MsgGrpc.getRenewAccountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRenewAccountMethod = MsgGrpc.getRenewAccountMethod) == null) {
          MsgGrpc.getRenewAccountMethod = getRenewAccountMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.Tx.MsgRenewAccount, starnamed.x.starname.v1beta1.Tx.MsgRenewAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RenewAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgRenewAccount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgRenewAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RenewAccount"))
              .build();
        }
      }
    }
    return getRenewAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgRenewDomain,
      starnamed.x.starname.v1beta1.Tx.MsgRenewDomainResponse> getRenewDomainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RenewDomain",
      requestType = starnamed.x.starname.v1beta1.Tx.MsgRenewDomain.class,
      responseType = starnamed.x.starname.v1beta1.Tx.MsgRenewDomainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgRenewDomain,
      starnamed.x.starname.v1beta1.Tx.MsgRenewDomainResponse> getRenewDomainMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgRenewDomain, starnamed.x.starname.v1beta1.Tx.MsgRenewDomainResponse> getRenewDomainMethod;
    if ((getRenewDomainMethod = MsgGrpc.getRenewDomainMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRenewDomainMethod = MsgGrpc.getRenewDomainMethod) == null) {
          MsgGrpc.getRenewDomainMethod = getRenewDomainMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.Tx.MsgRenewDomain, starnamed.x.starname.v1beta1.Tx.MsgRenewDomainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RenewDomain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgRenewDomain.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgRenewDomainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RenewDomain"))
              .build();
        }
      }
    }
    return getRenewDomainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadata,
      starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadataResponse> getReplaceAccountMetadataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReplaceAccountMetadata",
      requestType = starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadata.class,
      responseType = starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadata,
      starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadataResponse> getReplaceAccountMetadataMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadata, starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadataResponse> getReplaceAccountMetadataMethod;
    if ((getReplaceAccountMetadataMethod = MsgGrpc.getReplaceAccountMetadataMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getReplaceAccountMetadataMethod = MsgGrpc.getReplaceAccountMetadataMethod) == null) {
          MsgGrpc.getReplaceAccountMetadataMethod = getReplaceAccountMetadataMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadata, starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReplaceAccountMetadata"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadata.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ReplaceAccountMetadata"))
              .build();
        }
      }
    }
    return getReplaceAccountMetadataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources,
      starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResourcesResponse> getReplaceAccountResourcesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReplaceAccountResources",
      requestType = starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources.class,
      responseType = starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResourcesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources,
      starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResourcesResponse> getReplaceAccountResourcesMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources, starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResourcesResponse> getReplaceAccountResourcesMethod;
    if ((getReplaceAccountResourcesMethod = MsgGrpc.getReplaceAccountResourcesMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getReplaceAccountResourcesMethod = MsgGrpc.getReplaceAccountResourcesMethod) == null) {
          MsgGrpc.getReplaceAccountResourcesMethod = getReplaceAccountResourcesMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources, starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResourcesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReplaceAccountResources"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResourcesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ReplaceAccountResources"))
              .build();
        }
      }
    }
    return getReplaceAccountResourcesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgTransferAccount,
      starnamed.x.starname.v1beta1.Tx.MsgTransferAccountResponse> getTransferAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TransferAccount",
      requestType = starnamed.x.starname.v1beta1.Tx.MsgTransferAccount.class,
      responseType = starnamed.x.starname.v1beta1.Tx.MsgTransferAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgTransferAccount,
      starnamed.x.starname.v1beta1.Tx.MsgTransferAccountResponse> getTransferAccountMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgTransferAccount, starnamed.x.starname.v1beta1.Tx.MsgTransferAccountResponse> getTransferAccountMethod;
    if ((getTransferAccountMethod = MsgGrpc.getTransferAccountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getTransferAccountMethod = MsgGrpc.getTransferAccountMethod) == null) {
          MsgGrpc.getTransferAccountMethod = getTransferAccountMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.Tx.MsgTransferAccount, starnamed.x.starname.v1beta1.Tx.MsgTransferAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TransferAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgTransferAccount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgTransferAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("TransferAccount"))
              .build();
        }
      }
    }
    return getTransferAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgTransferDomain,
      starnamed.x.starname.v1beta1.Tx.MsgTransferDomainResponse> getTransferDomainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TransferDomain",
      requestType = starnamed.x.starname.v1beta1.Tx.MsgTransferDomain.class,
      responseType = starnamed.x.starname.v1beta1.Tx.MsgTransferDomainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgTransferDomain,
      starnamed.x.starname.v1beta1.Tx.MsgTransferDomainResponse> getTransferDomainMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.Tx.MsgTransferDomain, starnamed.x.starname.v1beta1.Tx.MsgTransferDomainResponse> getTransferDomainMethod;
    if ((getTransferDomainMethod = MsgGrpc.getTransferDomainMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getTransferDomainMethod = MsgGrpc.getTransferDomainMethod) == null) {
          MsgGrpc.getTransferDomainMethod = getTransferDomainMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.Tx.MsgTransferDomain, starnamed.x.starname.v1beta1.Tx.MsgTransferDomainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TransferDomain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgTransferDomain.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.Tx.MsgTransferDomainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("TransferDomain"))
              .build();
        }
      }
    }
    return getTransferDomainMethod;
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
   * Msg defines the starname Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * AddAccountCertificate adds a certificate to an Account
     * </pre>
     */
    public void addAccountCertificate(starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificate request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddAccountCertificateMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteAccount registers a Domain
     * </pre>
     */
    public void deleteAccount(starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteAccountCertificate deletes a certificate from an account
     * </pre>
     */
    public void deleteAccountCertificate(starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificate request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteAccountCertificateMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteDomain registers a Domain
     * </pre>
     */
    public void deleteDomain(starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgDeleteDomainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteDomainMethod(), responseObserver);
    }

    /**
     * <pre>
     * RegisterAccount registers an Account
     * </pre>
     */
    public void registerAccount(starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgRegisterAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * RegisterDomain registers a Domain
     * </pre>
     */
    public void registerDomain(starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgRegisterDomainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterDomainMethod(), responseObserver);
    }

    /**
     * <pre>
     * RenewAccount registers a Domain
     * </pre>
     */
    public void renewAccount(starnamed.x.starname.v1beta1.Tx.MsgRenewAccount request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgRenewAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRenewAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * RenewDomain registers a Domain
     * </pre>
     */
    public void renewDomain(starnamed.x.starname.v1beta1.Tx.MsgRenewDomain request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgRenewDomainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRenewDomainMethod(), responseObserver);
    }

    /**
     * <pre>
     * ReplaceAccountMetadata registers a Domain
     * </pre>
     */
    public void replaceAccountMetadata(starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadata request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadataResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReplaceAccountMetadataMethod(), responseObserver);
    }

    /**
     * <pre>
     * ReplaceAccountResources registers a Domain
     * </pre>
     */
    public void replaceAccountResources(starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResourcesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReplaceAccountResourcesMethod(), responseObserver);
    }

    /**
     * <pre>
     * TransferAccount registers a Domain
     * </pre>
     */
    public void transferAccount(starnamed.x.starname.v1beta1.Tx.MsgTransferAccount request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgTransferAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTransferAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * TransferDomain registers a Domain
     * </pre>
     */
    public void transferDomain(starnamed.x.starname.v1beta1.Tx.MsgTransferDomain request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgTransferDomainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTransferDomainMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddAccountCertificateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificate,
                starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificateResponse>(
                  this, METHODID_ADD_ACCOUNT_CERTIFICATE)))
          .addMethod(
            getDeleteAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount,
                starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountResponse>(
                  this, METHODID_DELETE_ACCOUNT)))
          .addMethod(
            getDeleteAccountCertificateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificate,
                starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificateResponse>(
                  this, METHODID_DELETE_ACCOUNT_CERTIFICATE)))
          .addMethod(
            getDeleteDomainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain,
                starnamed.x.starname.v1beta1.Tx.MsgDeleteDomainResponse>(
                  this, METHODID_DELETE_DOMAIN)))
          .addMethod(
            getRegisterAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount,
                starnamed.x.starname.v1beta1.Tx.MsgRegisterAccountResponse>(
                  this, METHODID_REGISTER_ACCOUNT)))
          .addMethod(
            getRegisterDomainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain,
                starnamed.x.starname.v1beta1.Tx.MsgRegisterDomainResponse>(
                  this, METHODID_REGISTER_DOMAIN)))
          .addMethod(
            getRenewAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.Tx.MsgRenewAccount,
                starnamed.x.starname.v1beta1.Tx.MsgRenewAccountResponse>(
                  this, METHODID_RENEW_ACCOUNT)))
          .addMethod(
            getRenewDomainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.Tx.MsgRenewDomain,
                starnamed.x.starname.v1beta1.Tx.MsgRenewDomainResponse>(
                  this, METHODID_RENEW_DOMAIN)))
          .addMethod(
            getReplaceAccountMetadataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadata,
                starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadataResponse>(
                  this, METHODID_REPLACE_ACCOUNT_METADATA)))
          .addMethod(
            getReplaceAccountResourcesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources,
                starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResourcesResponse>(
                  this, METHODID_REPLACE_ACCOUNT_RESOURCES)))
          .addMethod(
            getTransferAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.Tx.MsgTransferAccount,
                starnamed.x.starname.v1beta1.Tx.MsgTransferAccountResponse>(
                  this, METHODID_TRANSFER_ACCOUNT)))
          .addMethod(
            getTransferDomainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.Tx.MsgTransferDomain,
                starnamed.x.starname.v1beta1.Tx.MsgTransferDomainResponse>(
                  this, METHODID_TRANSFER_DOMAIN)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the starname Msg service.
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
     * AddAccountCertificate adds a certificate to an Account
     * </pre>
     */
    public void addAccountCertificate(starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificate request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddAccountCertificateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteAccount registers a Domain
     * </pre>
     */
    public void deleteAccount(starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteAccountCertificate deletes a certificate from an account
     * </pre>
     */
    public void deleteAccountCertificate(starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificate request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteAccountCertificateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteDomain registers a Domain
     * </pre>
     */
    public void deleteDomain(starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgDeleteDomainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteDomainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RegisterAccount registers an Account
     * </pre>
     */
    public void registerAccount(starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgRegisterAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RegisterDomain registers a Domain
     * </pre>
     */
    public void registerDomain(starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgRegisterDomainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterDomainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RenewAccount registers a Domain
     * </pre>
     */
    public void renewAccount(starnamed.x.starname.v1beta1.Tx.MsgRenewAccount request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgRenewAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRenewAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RenewDomain registers a Domain
     * </pre>
     */
    public void renewDomain(starnamed.x.starname.v1beta1.Tx.MsgRenewDomain request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgRenewDomainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRenewDomainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ReplaceAccountMetadata registers a Domain
     * </pre>
     */
    public void replaceAccountMetadata(starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadata request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadataResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReplaceAccountMetadataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ReplaceAccountResources registers a Domain
     * </pre>
     */
    public void replaceAccountResources(starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResourcesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReplaceAccountResourcesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TransferAccount registers a Domain
     * </pre>
     */
    public void transferAccount(starnamed.x.starname.v1beta1.Tx.MsgTransferAccount request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgTransferAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTransferAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TransferDomain registers a Domain
     * </pre>
     */
    public void transferDomain(starnamed.x.starname.v1beta1.Tx.MsgTransferDomain request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgTransferDomainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTransferDomainMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the starname Msg service.
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
     * AddAccountCertificate adds a certificate to an Account
     * </pre>
     */
    public starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificateResponse addAccountCertificate(starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificate request) {
      return blockingUnaryCall(
          getChannel(), getAddAccountCertificateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteAccount registers a Domain
     * </pre>
     */
    public starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountResponse deleteAccount(starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount request) {
      return blockingUnaryCall(
          getChannel(), getDeleteAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteAccountCertificate deletes a certificate from an account
     * </pre>
     */
    public starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificateResponse deleteAccountCertificate(starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificate request) {
      return blockingUnaryCall(
          getChannel(), getDeleteAccountCertificateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteDomain registers a Domain
     * </pre>
     */
    public starnamed.x.starname.v1beta1.Tx.MsgDeleteDomainResponse deleteDomain(starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain request) {
      return blockingUnaryCall(
          getChannel(), getDeleteDomainMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RegisterAccount registers an Account
     * </pre>
     */
    public starnamed.x.starname.v1beta1.Tx.MsgRegisterAccountResponse registerAccount(starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount request) {
      return blockingUnaryCall(
          getChannel(), getRegisterAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RegisterDomain registers a Domain
     * </pre>
     */
    public starnamed.x.starname.v1beta1.Tx.MsgRegisterDomainResponse registerDomain(starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain request) {
      return blockingUnaryCall(
          getChannel(), getRegisterDomainMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RenewAccount registers a Domain
     * </pre>
     */
    public starnamed.x.starname.v1beta1.Tx.MsgRenewAccountResponse renewAccount(starnamed.x.starname.v1beta1.Tx.MsgRenewAccount request) {
      return blockingUnaryCall(
          getChannel(), getRenewAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RenewDomain registers a Domain
     * </pre>
     */
    public starnamed.x.starname.v1beta1.Tx.MsgRenewDomainResponse renewDomain(starnamed.x.starname.v1beta1.Tx.MsgRenewDomain request) {
      return blockingUnaryCall(
          getChannel(), getRenewDomainMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ReplaceAccountMetadata registers a Domain
     * </pre>
     */
    public starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadataResponse replaceAccountMetadata(starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadata request) {
      return blockingUnaryCall(
          getChannel(), getReplaceAccountMetadataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ReplaceAccountResources registers a Domain
     * </pre>
     */
    public starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResourcesResponse replaceAccountResources(starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources request) {
      return blockingUnaryCall(
          getChannel(), getReplaceAccountResourcesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TransferAccount registers a Domain
     * </pre>
     */
    public starnamed.x.starname.v1beta1.Tx.MsgTransferAccountResponse transferAccount(starnamed.x.starname.v1beta1.Tx.MsgTransferAccount request) {
      return blockingUnaryCall(
          getChannel(), getTransferAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TransferDomain registers a Domain
     * </pre>
     */
    public starnamed.x.starname.v1beta1.Tx.MsgTransferDomainResponse transferDomain(starnamed.x.starname.v1beta1.Tx.MsgTransferDomain request) {
      return blockingUnaryCall(
          getChannel(), getTransferDomainMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the starname Msg service.
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
     * AddAccountCertificate adds a certificate to an Account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificateResponse> addAccountCertificate(
        starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificate request) {
      return futureUnaryCall(
          getChannel().newCall(getAddAccountCertificateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteAccount registers a Domain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountResponse> deleteAccount(
        starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteAccountCertificate deletes a certificate from an account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificateResponse> deleteAccountCertificate(
        starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificate request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteAccountCertificateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteDomain registers a Domain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.Tx.MsgDeleteDomainResponse> deleteDomain(
        starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteDomainMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RegisterAccount registers an Account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.Tx.MsgRegisterAccountResponse> registerAccount(
        starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RegisterDomain registers a Domain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.Tx.MsgRegisterDomainResponse> registerDomain(
        starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterDomainMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RenewAccount registers a Domain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.Tx.MsgRenewAccountResponse> renewAccount(
        starnamed.x.starname.v1beta1.Tx.MsgRenewAccount request) {
      return futureUnaryCall(
          getChannel().newCall(getRenewAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RenewDomain registers a Domain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.Tx.MsgRenewDomainResponse> renewDomain(
        starnamed.x.starname.v1beta1.Tx.MsgRenewDomain request) {
      return futureUnaryCall(
          getChannel().newCall(getRenewDomainMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ReplaceAccountMetadata registers a Domain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadataResponse> replaceAccountMetadata(
        starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadata request) {
      return futureUnaryCall(
          getChannel().newCall(getReplaceAccountMetadataMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ReplaceAccountResources registers a Domain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResourcesResponse> replaceAccountResources(
        starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources request) {
      return futureUnaryCall(
          getChannel().newCall(getReplaceAccountResourcesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TransferAccount registers a Domain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.Tx.MsgTransferAccountResponse> transferAccount(
        starnamed.x.starname.v1beta1.Tx.MsgTransferAccount request) {
      return futureUnaryCall(
          getChannel().newCall(getTransferAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TransferDomain registers a Domain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.Tx.MsgTransferDomainResponse> transferDomain(
        starnamed.x.starname.v1beta1.Tx.MsgTransferDomain request) {
      return futureUnaryCall(
          getChannel().newCall(getTransferDomainMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_ACCOUNT_CERTIFICATE = 0;
  private static final int METHODID_DELETE_ACCOUNT = 1;
  private static final int METHODID_DELETE_ACCOUNT_CERTIFICATE = 2;
  private static final int METHODID_DELETE_DOMAIN = 3;
  private static final int METHODID_REGISTER_ACCOUNT = 4;
  private static final int METHODID_REGISTER_DOMAIN = 5;
  private static final int METHODID_RENEW_ACCOUNT = 6;
  private static final int METHODID_RENEW_DOMAIN = 7;
  private static final int METHODID_REPLACE_ACCOUNT_METADATA = 8;
  private static final int METHODID_REPLACE_ACCOUNT_RESOURCES = 9;
  private static final int METHODID_TRANSFER_ACCOUNT = 10;
  private static final int METHODID_TRANSFER_DOMAIN = 11;

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
        case METHODID_ADD_ACCOUNT_CERTIFICATE:
          serviceImpl.addAccountCertificate((starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificate) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgAddAccountCertificateResponse>) responseObserver);
          break;
        case METHODID_DELETE_ACCOUNT:
          serviceImpl.deleteAccount((starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountResponse>) responseObserver);
          break;
        case METHODID_DELETE_ACCOUNT_CERTIFICATE:
          serviceImpl.deleteAccountCertificate((starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificate) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgDeleteAccountCertificateResponse>) responseObserver);
          break;
        case METHODID_DELETE_DOMAIN:
          serviceImpl.deleteDomain((starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgDeleteDomainResponse>) responseObserver);
          break;
        case METHODID_REGISTER_ACCOUNT:
          serviceImpl.registerAccount((starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgRegisterAccountResponse>) responseObserver);
          break;
        case METHODID_REGISTER_DOMAIN:
          serviceImpl.registerDomain((starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgRegisterDomainResponse>) responseObserver);
          break;
        case METHODID_RENEW_ACCOUNT:
          serviceImpl.renewAccount((starnamed.x.starname.v1beta1.Tx.MsgRenewAccount) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgRenewAccountResponse>) responseObserver);
          break;
        case METHODID_RENEW_DOMAIN:
          serviceImpl.renewDomain((starnamed.x.starname.v1beta1.Tx.MsgRenewDomain) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgRenewDomainResponse>) responseObserver);
          break;
        case METHODID_REPLACE_ACCOUNT_METADATA:
          serviceImpl.replaceAccountMetadata((starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadata) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountMetadataResponse>) responseObserver);
          break;
        case METHODID_REPLACE_ACCOUNT_RESOURCES:
          serviceImpl.replaceAccountResources((starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResourcesResponse>) responseObserver);
          break;
        case METHODID_TRANSFER_ACCOUNT:
          serviceImpl.transferAccount((starnamed.x.starname.v1beta1.Tx.MsgTransferAccount) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgTransferAccountResponse>) responseObserver);
          break;
        case METHODID_TRANSFER_DOMAIN:
          serviceImpl.transferDomain((starnamed.x.starname.v1beta1.Tx.MsgTransferDomain) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.Tx.MsgTransferDomainResponse>) responseObserver);
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
      return starnamed.x.starname.v1beta1.Tx.getDescriptor();
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
              .addMethod(getAddAccountCertificateMethod())
              .addMethod(getDeleteAccountMethod())
              .addMethod(getDeleteAccountCertificateMethod())
              .addMethod(getDeleteDomainMethod())
              .addMethod(getRegisterAccountMethod())
              .addMethod(getRegisterDomainMethod())
              .addMethod(getRenewAccountMethod())
              .addMethod(getRenewDomainMethod())
              .addMethod(getReplaceAccountMetadataMethod())
              .addMethod(getReplaceAccountResourcesMethod())
              .addMethod(getTransferAccountMethod())
              .addMethod(getTransferDomainMethod())
              .build();
        }
      }
    }
    return result;
  }
}
