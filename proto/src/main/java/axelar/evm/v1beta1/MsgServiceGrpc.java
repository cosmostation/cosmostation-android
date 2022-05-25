package axelar.evm.v1beta1;

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
 * Msg defines the evm Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: axelar/evm/v1beta1/service.proto")
public final class MsgServiceGrpc {

  private MsgServiceGrpc() {}

  public static final String SERVICE_NAME = "axelar.evm.v1beta1.MsgService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.LinkRequest,
      axelar.evm.v1beta1.Tx.LinkResponse> getLinkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Link",
      requestType = axelar.evm.v1beta1.Tx.LinkRequest.class,
      responseType = axelar.evm.v1beta1.Tx.LinkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.LinkRequest,
      axelar.evm.v1beta1.Tx.LinkResponse> getLinkMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.LinkRequest, axelar.evm.v1beta1.Tx.LinkResponse> getLinkMethod;
    if ((getLinkMethod = MsgServiceGrpc.getLinkMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getLinkMethod = MsgServiceGrpc.getLinkMethod) == null) {
          MsgServiceGrpc.getLinkMethod = getLinkMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.LinkRequest, axelar.evm.v1beta1.Tx.LinkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Link"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.LinkRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.LinkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("Link"))
              .build();
        }
      }
    }
    return getLinkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.ConfirmChainRequest,
      axelar.evm.v1beta1.Tx.ConfirmChainResponse> getConfirmChainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmChain",
      requestType = axelar.evm.v1beta1.Tx.ConfirmChainRequest.class,
      responseType = axelar.evm.v1beta1.Tx.ConfirmChainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.ConfirmChainRequest,
      axelar.evm.v1beta1.Tx.ConfirmChainResponse> getConfirmChainMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.ConfirmChainRequest, axelar.evm.v1beta1.Tx.ConfirmChainResponse> getConfirmChainMethod;
    if ((getConfirmChainMethod = MsgServiceGrpc.getConfirmChainMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getConfirmChainMethod = MsgServiceGrpc.getConfirmChainMethod) == null) {
          MsgServiceGrpc.getConfirmChainMethod = getConfirmChainMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.ConfirmChainRequest, axelar.evm.v1beta1.Tx.ConfirmChainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConfirmChain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.ConfirmChainRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.ConfirmChainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("ConfirmChain"))
              .build();
        }
      }
    }
    return getConfirmChainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentRequest,
      axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentResponse> getConfirmGatewayDeploymentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmGatewayDeployment",
      requestType = axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentRequest.class,
      responseType = axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentRequest,
      axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentResponse> getConfirmGatewayDeploymentMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentRequest, axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentResponse> getConfirmGatewayDeploymentMethod;
    if ((getConfirmGatewayDeploymentMethod = MsgServiceGrpc.getConfirmGatewayDeploymentMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getConfirmGatewayDeploymentMethod = MsgServiceGrpc.getConfirmGatewayDeploymentMethod) == null) {
          MsgServiceGrpc.getConfirmGatewayDeploymentMethod = getConfirmGatewayDeploymentMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentRequest, axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConfirmGatewayDeployment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("ConfirmGatewayDeployment"))
              .build();
        }
      }
    }
    return getConfirmGatewayDeploymentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.ConfirmTokenRequest,
      axelar.evm.v1beta1.Tx.ConfirmTokenResponse> getConfirmTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmToken",
      requestType = axelar.evm.v1beta1.Tx.ConfirmTokenRequest.class,
      responseType = axelar.evm.v1beta1.Tx.ConfirmTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.ConfirmTokenRequest,
      axelar.evm.v1beta1.Tx.ConfirmTokenResponse> getConfirmTokenMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.ConfirmTokenRequest, axelar.evm.v1beta1.Tx.ConfirmTokenResponse> getConfirmTokenMethod;
    if ((getConfirmTokenMethod = MsgServiceGrpc.getConfirmTokenMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getConfirmTokenMethod = MsgServiceGrpc.getConfirmTokenMethod) == null) {
          MsgServiceGrpc.getConfirmTokenMethod = getConfirmTokenMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.ConfirmTokenRequest, axelar.evm.v1beta1.Tx.ConfirmTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConfirmToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.ConfirmTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.ConfirmTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("ConfirmToken"))
              .build();
        }
      }
    }
    return getConfirmTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.ConfirmDepositRequest,
      axelar.evm.v1beta1.Tx.ConfirmDepositResponse> getConfirmDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmDeposit",
      requestType = axelar.evm.v1beta1.Tx.ConfirmDepositRequest.class,
      responseType = axelar.evm.v1beta1.Tx.ConfirmDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.ConfirmDepositRequest,
      axelar.evm.v1beta1.Tx.ConfirmDepositResponse> getConfirmDepositMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.ConfirmDepositRequest, axelar.evm.v1beta1.Tx.ConfirmDepositResponse> getConfirmDepositMethod;
    if ((getConfirmDepositMethod = MsgServiceGrpc.getConfirmDepositMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getConfirmDepositMethod = MsgServiceGrpc.getConfirmDepositMethod) == null) {
          MsgServiceGrpc.getConfirmDepositMethod = getConfirmDepositMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.ConfirmDepositRequest, axelar.evm.v1beta1.Tx.ConfirmDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConfirmDeposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.ConfirmDepositRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.ConfirmDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("ConfirmDeposit"))
              .build();
        }
      }
    }
    return getConfirmDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.ConfirmTransferKeyRequest,
      axelar.evm.v1beta1.Tx.ConfirmTransferKeyResponse> getConfirmTransferKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmTransferKey",
      requestType = axelar.evm.v1beta1.Tx.ConfirmTransferKeyRequest.class,
      responseType = axelar.evm.v1beta1.Tx.ConfirmTransferKeyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.ConfirmTransferKeyRequest,
      axelar.evm.v1beta1.Tx.ConfirmTransferKeyResponse> getConfirmTransferKeyMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.ConfirmTransferKeyRequest, axelar.evm.v1beta1.Tx.ConfirmTransferKeyResponse> getConfirmTransferKeyMethod;
    if ((getConfirmTransferKeyMethod = MsgServiceGrpc.getConfirmTransferKeyMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getConfirmTransferKeyMethod = MsgServiceGrpc.getConfirmTransferKeyMethod) == null) {
          MsgServiceGrpc.getConfirmTransferKeyMethod = getConfirmTransferKeyMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.ConfirmTransferKeyRequest, axelar.evm.v1beta1.Tx.ConfirmTransferKeyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConfirmTransferKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.ConfirmTransferKeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.ConfirmTransferKeyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("ConfirmTransferKey"))
              .build();
        }
      }
    }
    return getConfirmTransferKeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.VoteConfirmChainRequest,
      axelar.evm.v1beta1.Tx.VoteConfirmChainResponse> getVoteConfirmChainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "VoteConfirmChain",
      requestType = axelar.evm.v1beta1.Tx.VoteConfirmChainRequest.class,
      responseType = axelar.evm.v1beta1.Tx.VoteConfirmChainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.VoteConfirmChainRequest,
      axelar.evm.v1beta1.Tx.VoteConfirmChainResponse> getVoteConfirmChainMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.VoteConfirmChainRequest, axelar.evm.v1beta1.Tx.VoteConfirmChainResponse> getVoteConfirmChainMethod;
    if ((getVoteConfirmChainMethod = MsgServiceGrpc.getVoteConfirmChainMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getVoteConfirmChainMethod = MsgServiceGrpc.getVoteConfirmChainMethod) == null) {
          MsgServiceGrpc.getVoteConfirmChainMethod = getVoteConfirmChainMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.VoteConfirmChainRequest, axelar.evm.v1beta1.Tx.VoteConfirmChainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "VoteConfirmChain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.VoteConfirmChainRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.VoteConfirmChainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("VoteConfirmChain"))
              .build();
        }
      }
    }
    return getVoteConfirmChainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentRequest,
      axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentResponse> getVoteConfirmGatewayDeploymentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "VoteConfirmGatewayDeployment",
      requestType = axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentRequest.class,
      responseType = axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentRequest,
      axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentResponse> getVoteConfirmGatewayDeploymentMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentRequest, axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentResponse> getVoteConfirmGatewayDeploymentMethod;
    if ((getVoteConfirmGatewayDeploymentMethod = MsgServiceGrpc.getVoteConfirmGatewayDeploymentMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getVoteConfirmGatewayDeploymentMethod = MsgServiceGrpc.getVoteConfirmGatewayDeploymentMethod) == null) {
          MsgServiceGrpc.getVoteConfirmGatewayDeploymentMethod = getVoteConfirmGatewayDeploymentMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentRequest, axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "VoteConfirmGatewayDeployment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("VoteConfirmGatewayDeployment"))
              .build();
        }
      }
    }
    return getVoteConfirmGatewayDeploymentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.VoteConfirmDepositRequest,
      axelar.evm.v1beta1.Tx.VoteConfirmDepositResponse> getVoteConfirmDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "VoteConfirmDeposit",
      requestType = axelar.evm.v1beta1.Tx.VoteConfirmDepositRequest.class,
      responseType = axelar.evm.v1beta1.Tx.VoteConfirmDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.VoteConfirmDepositRequest,
      axelar.evm.v1beta1.Tx.VoteConfirmDepositResponse> getVoteConfirmDepositMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.VoteConfirmDepositRequest, axelar.evm.v1beta1.Tx.VoteConfirmDepositResponse> getVoteConfirmDepositMethod;
    if ((getVoteConfirmDepositMethod = MsgServiceGrpc.getVoteConfirmDepositMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getVoteConfirmDepositMethod = MsgServiceGrpc.getVoteConfirmDepositMethod) == null) {
          MsgServiceGrpc.getVoteConfirmDepositMethod = getVoteConfirmDepositMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.VoteConfirmDepositRequest, axelar.evm.v1beta1.Tx.VoteConfirmDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "VoteConfirmDeposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.VoteConfirmDepositRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.VoteConfirmDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("VoteConfirmDeposit"))
              .build();
        }
      }
    }
    return getVoteConfirmDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.VoteConfirmTokenRequest,
      axelar.evm.v1beta1.Tx.VoteConfirmTokenResponse> getVoteConfirmTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "VoteConfirmToken",
      requestType = axelar.evm.v1beta1.Tx.VoteConfirmTokenRequest.class,
      responseType = axelar.evm.v1beta1.Tx.VoteConfirmTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.VoteConfirmTokenRequest,
      axelar.evm.v1beta1.Tx.VoteConfirmTokenResponse> getVoteConfirmTokenMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.VoteConfirmTokenRequest, axelar.evm.v1beta1.Tx.VoteConfirmTokenResponse> getVoteConfirmTokenMethod;
    if ((getVoteConfirmTokenMethod = MsgServiceGrpc.getVoteConfirmTokenMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getVoteConfirmTokenMethod = MsgServiceGrpc.getVoteConfirmTokenMethod) == null) {
          MsgServiceGrpc.getVoteConfirmTokenMethod = getVoteConfirmTokenMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.VoteConfirmTokenRequest, axelar.evm.v1beta1.Tx.VoteConfirmTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "VoteConfirmToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.VoteConfirmTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.VoteConfirmTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("VoteConfirmToken"))
              .build();
        }
      }
    }
    return getVoteConfirmTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyRequest,
      axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyResponse> getVoteConfirmTransferKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "VoteConfirmTransferKey",
      requestType = axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyRequest.class,
      responseType = axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyRequest,
      axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyResponse> getVoteConfirmTransferKeyMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyRequest, axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyResponse> getVoteConfirmTransferKeyMethod;
    if ((getVoteConfirmTransferKeyMethod = MsgServiceGrpc.getVoteConfirmTransferKeyMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getVoteConfirmTransferKeyMethod = MsgServiceGrpc.getVoteConfirmTransferKeyMethod) == null) {
          MsgServiceGrpc.getVoteConfirmTransferKeyMethod = getVoteConfirmTransferKeyMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyRequest, axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "VoteConfirmTransferKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("VoteConfirmTransferKey"))
              .build();
        }
      }
    }
    return getVoteConfirmTransferKeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.CreateDeployTokenRequest,
      axelar.evm.v1beta1.Tx.CreateDeployTokenResponse> getCreateDeployTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateDeployToken",
      requestType = axelar.evm.v1beta1.Tx.CreateDeployTokenRequest.class,
      responseType = axelar.evm.v1beta1.Tx.CreateDeployTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.CreateDeployTokenRequest,
      axelar.evm.v1beta1.Tx.CreateDeployTokenResponse> getCreateDeployTokenMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.CreateDeployTokenRequest, axelar.evm.v1beta1.Tx.CreateDeployTokenResponse> getCreateDeployTokenMethod;
    if ((getCreateDeployTokenMethod = MsgServiceGrpc.getCreateDeployTokenMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getCreateDeployTokenMethod = MsgServiceGrpc.getCreateDeployTokenMethod) == null) {
          MsgServiceGrpc.getCreateDeployTokenMethod = getCreateDeployTokenMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.CreateDeployTokenRequest, axelar.evm.v1beta1.Tx.CreateDeployTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateDeployToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.CreateDeployTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.CreateDeployTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("CreateDeployToken"))
              .build();
        }
      }
    }
    return getCreateDeployTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.CreateBurnTokensRequest,
      axelar.evm.v1beta1.Tx.CreateBurnTokensResponse> getCreateBurnTokensMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateBurnTokens",
      requestType = axelar.evm.v1beta1.Tx.CreateBurnTokensRequest.class,
      responseType = axelar.evm.v1beta1.Tx.CreateBurnTokensResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.CreateBurnTokensRequest,
      axelar.evm.v1beta1.Tx.CreateBurnTokensResponse> getCreateBurnTokensMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.CreateBurnTokensRequest, axelar.evm.v1beta1.Tx.CreateBurnTokensResponse> getCreateBurnTokensMethod;
    if ((getCreateBurnTokensMethod = MsgServiceGrpc.getCreateBurnTokensMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getCreateBurnTokensMethod = MsgServiceGrpc.getCreateBurnTokensMethod) == null) {
          MsgServiceGrpc.getCreateBurnTokensMethod = getCreateBurnTokensMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.CreateBurnTokensRequest, axelar.evm.v1beta1.Tx.CreateBurnTokensResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateBurnTokens"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.CreateBurnTokensRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.CreateBurnTokensResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("CreateBurnTokens"))
              .build();
        }
      }
    }
    return getCreateBurnTokensMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.CreatePendingTransfersRequest,
      axelar.evm.v1beta1.Tx.CreatePendingTransfersResponse> getCreatePendingTransfersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePendingTransfers",
      requestType = axelar.evm.v1beta1.Tx.CreatePendingTransfersRequest.class,
      responseType = axelar.evm.v1beta1.Tx.CreatePendingTransfersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.CreatePendingTransfersRequest,
      axelar.evm.v1beta1.Tx.CreatePendingTransfersResponse> getCreatePendingTransfersMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.CreatePendingTransfersRequest, axelar.evm.v1beta1.Tx.CreatePendingTransfersResponse> getCreatePendingTransfersMethod;
    if ((getCreatePendingTransfersMethod = MsgServiceGrpc.getCreatePendingTransfersMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getCreatePendingTransfersMethod = MsgServiceGrpc.getCreatePendingTransfersMethod) == null) {
          MsgServiceGrpc.getCreatePendingTransfersMethod = getCreatePendingTransfersMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.CreatePendingTransfersRequest, axelar.evm.v1beta1.Tx.CreatePendingTransfersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePendingTransfers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.CreatePendingTransfersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.CreatePendingTransfersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("CreatePendingTransfers"))
              .build();
        }
      }
    }
    return getCreatePendingTransfersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.CreateTransferOwnershipRequest,
      axelar.evm.v1beta1.Tx.CreateTransferOwnershipResponse> getCreateTransferOwnershipMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateTransferOwnership",
      requestType = axelar.evm.v1beta1.Tx.CreateTransferOwnershipRequest.class,
      responseType = axelar.evm.v1beta1.Tx.CreateTransferOwnershipResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.CreateTransferOwnershipRequest,
      axelar.evm.v1beta1.Tx.CreateTransferOwnershipResponse> getCreateTransferOwnershipMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.CreateTransferOwnershipRequest, axelar.evm.v1beta1.Tx.CreateTransferOwnershipResponse> getCreateTransferOwnershipMethod;
    if ((getCreateTransferOwnershipMethod = MsgServiceGrpc.getCreateTransferOwnershipMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getCreateTransferOwnershipMethod = MsgServiceGrpc.getCreateTransferOwnershipMethod) == null) {
          MsgServiceGrpc.getCreateTransferOwnershipMethod = getCreateTransferOwnershipMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.CreateTransferOwnershipRequest, axelar.evm.v1beta1.Tx.CreateTransferOwnershipResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateTransferOwnership"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.CreateTransferOwnershipRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.CreateTransferOwnershipResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("CreateTransferOwnership"))
              .build();
        }
      }
    }
    return getCreateTransferOwnershipMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.CreateTransferOperatorshipRequest,
      axelar.evm.v1beta1.Tx.CreateTransferOperatorshipResponse> getCreateTransferOperatorshipMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateTransferOperatorship",
      requestType = axelar.evm.v1beta1.Tx.CreateTransferOperatorshipRequest.class,
      responseType = axelar.evm.v1beta1.Tx.CreateTransferOperatorshipResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.CreateTransferOperatorshipRequest,
      axelar.evm.v1beta1.Tx.CreateTransferOperatorshipResponse> getCreateTransferOperatorshipMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.CreateTransferOperatorshipRequest, axelar.evm.v1beta1.Tx.CreateTransferOperatorshipResponse> getCreateTransferOperatorshipMethod;
    if ((getCreateTransferOperatorshipMethod = MsgServiceGrpc.getCreateTransferOperatorshipMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getCreateTransferOperatorshipMethod = MsgServiceGrpc.getCreateTransferOperatorshipMethod) == null) {
          MsgServiceGrpc.getCreateTransferOperatorshipMethod = getCreateTransferOperatorshipMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.CreateTransferOperatorshipRequest, axelar.evm.v1beta1.Tx.CreateTransferOperatorshipResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateTransferOperatorship"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.CreateTransferOperatorshipRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.CreateTransferOperatorshipResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("CreateTransferOperatorship"))
              .build();
        }
      }
    }
    return getCreateTransferOperatorshipMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.SignCommandsRequest,
      axelar.evm.v1beta1.Tx.SignCommandsResponse> getSignCommandsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SignCommands",
      requestType = axelar.evm.v1beta1.Tx.SignCommandsRequest.class,
      responseType = axelar.evm.v1beta1.Tx.SignCommandsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.SignCommandsRequest,
      axelar.evm.v1beta1.Tx.SignCommandsResponse> getSignCommandsMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.SignCommandsRequest, axelar.evm.v1beta1.Tx.SignCommandsResponse> getSignCommandsMethod;
    if ((getSignCommandsMethod = MsgServiceGrpc.getSignCommandsMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getSignCommandsMethod = MsgServiceGrpc.getSignCommandsMethod) == null) {
          MsgServiceGrpc.getSignCommandsMethod = getSignCommandsMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.SignCommandsRequest, axelar.evm.v1beta1.Tx.SignCommandsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SignCommands"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.SignCommandsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.SignCommandsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("SignCommands"))
              .build();
        }
      }
    }
    return getSignCommandsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.AddChainRequest,
      axelar.evm.v1beta1.Tx.AddChainResponse> getAddChainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddChain",
      requestType = axelar.evm.v1beta1.Tx.AddChainRequest.class,
      responseType = axelar.evm.v1beta1.Tx.AddChainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.AddChainRequest,
      axelar.evm.v1beta1.Tx.AddChainResponse> getAddChainMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Tx.AddChainRequest, axelar.evm.v1beta1.Tx.AddChainResponse> getAddChainMethod;
    if ((getAddChainMethod = MsgServiceGrpc.getAddChainMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getAddChainMethod = MsgServiceGrpc.getAddChainMethod) == null) {
          MsgServiceGrpc.getAddChainMethod = getAddChainMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Tx.AddChainRequest, axelar.evm.v1beta1.Tx.AddChainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddChain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.AddChainRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Tx.AddChainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("AddChain"))
              .build();
        }
      }
    }
    return getAddChainMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MsgServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceStub>() {
        @java.lang.Override
        public MsgServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceStub(channel, callOptions);
        }
      };
    return MsgServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MsgServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceBlockingStub>() {
        @java.lang.Override
        public MsgServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceBlockingStub(channel, callOptions);
        }
      };
    return MsgServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MsgServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceFutureStub>() {
        @java.lang.Override
        public MsgServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceFutureStub(channel, callOptions);
        }
      };
    return MsgServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Msg defines the evm Msg service.
   * </pre>
   */
  public static abstract class MsgServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void link(axelar.evm.v1beta1.Tx.LinkRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.LinkResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLinkMethod(), responseObserver);
    }

    /**
     */
    public void confirmChain(axelar.evm.v1beta1.Tx.ConfirmChainRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.ConfirmChainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConfirmChainMethod(), responseObserver);
    }

    /**
     */
    public void confirmGatewayDeployment(axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConfirmGatewayDeploymentMethod(), responseObserver);
    }

    /**
     */
    public void confirmToken(axelar.evm.v1beta1.Tx.ConfirmTokenRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.ConfirmTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConfirmTokenMethod(), responseObserver);
    }

    /**
     */
    public void confirmDeposit(axelar.evm.v1beta1.Tx.ConfirmDepositRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.ConfirmDepositResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConfirmDepositMethod(), responseObserver);
    }

    /**
     */
    public void confirmTransferKey(axelar.evm.v1beta1.Tx.ConfirmTransferKeyRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.ConfirmTransferKeyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConfirmTransferKeyMethod(), responseObserver);
    }

    /**
     */
    public void voteConfirmChain(axelar.evm.v1beta1.Tx.VoteConfirmChainRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.VoteConfirmChainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVoteConfirmChainMethod(), responseObserver);
    }

    /**
     */
    public void voteConfirmGatewayDeployment(axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVoteConfirmGatewayDeploymentMethod(), responseObserver);
    }

    /**
     */
    public void voteConfirmDeposit(axelar.evm.v1beta1.Tx.VoteConfirmDepositRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.VoteConfirmDepositResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVoteConfirmDepositMethod(), responseObserver);
    }

    /**
     */
    public void voteConfirmToken(axelar.evm.v1beta1.Tx.VoteConfirmTokenRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.VoteConfirmTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVoteConfirmTokenMethod(), responseObserver);
    }

    /**
     */
    public void voteConfirmTransferKey(axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVoteConfirmTransferKeyMethod(), responseObserver);
    }

    /**
     */
    public void createDeployToken(axelar.evm.v1beta1.Tx.CreateDeployTokenRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.CreateDeployTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateDeployTokenMethod(), responseObserver);
    }

    /**
     */
    public void createBurnTokens(axelar.evm.v1beta1.Tx.CreateBurnTokensRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.CreateBurnTokensResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateBurnTokensMethod(), responseObserver);
    }

    /**
     */
    public void createPendingTransfers(axelar.evm.v1beta1.Tx.CreatePendingTransfersRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.CreatePendingTransfersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreatePendingTransfersMethod(), responseObserver);
    }

    /**
     */
    public void createTransferOwnership(axelar.evm.v1beta1.Tx.CreateTransferOwnershipRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.CreateTransferOwnershipResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateTransferOwnershipMethod(), responseObserver);
    }

    /**
     */
    public void createTransferOperatorship(axelar.evm.v1beta1.Tx.CreateTransferOperatorshipRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.CreateTransferOperatorshipResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateTransferOperatorshipMethod(), responseObserver);
    }

    /**
     */
    public void signCommands(axelar.evm.v1beta1.Tx.SignCommandsRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.SignCommandsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSignCommandsMethod(), responseObserver);
    }

    /**
     */
    public void addChain(axelar.evm.v1beta1.Tx.AddChainRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.AddChainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddChainMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLinkMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.LinkRequest,
                axelar.evm.v1beta1.Tx.LinkResponse>(
                  this, METHODID_LINK)))
          .addMethod(
            getConfirmChainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.ConfirmChainRequest,
                axelar.evm.v1beta1.Tx.ConfirmChainResponse>(
                  this, METHODID_CONFIRM_CHAIN)))
          .addMethod(
            getConfirmGatewayDeploymentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentRequest,
                axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentResponse>(
                  this, METHODID_CONFIRM_GATEWAY_DEPLOYMENT)))
          .addMethod(
            getConfirmTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.ConfirmTokenRequest,
                axelar.evm.v1beta1.Tx.ConfirmTokenResponse>(
                  this, METHODID_CONFIRM_TOKEN)))
          .addMethod(
            getConfirmDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.ConfirmDepositRequest,
                axelar.evm.v1beta1.Tx.ConfirmDepositResponse>(
                  this, METHODID_CONFIRM_DEPOSIT)))
          .addMethod(
            getConfirmTransferKeyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.ConfirmTransferKeyRequest,
                axelar.evm.v1beta1.Tx.ConfirmTransferKeyResponse>(
                  this, METHODID_CONFIRM_TRANSFER_KEY)))
          .addMethod(
            getVoteConfirmChainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.VoteConfirmChainRequest,
                axelar.evm.v1beta1.Tx.VoteConfirmChainResponse>(
                  this, METHODID_VOTE_CONFIRM_CHAIN)))
          .addMethod(
            getVoteConfirmGatewayDeploymentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentRequest,
                axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentResponse>(
                  this, METHODID_VOTE_CONFIRM_GATEWAY_DEPLOYMENT)))
          .addMethod(
            getVoteConfirmDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.VoteConfirmDepositRequest,
                axelar.evm.v1beta1.Tx.VoteConfirmDepositResponse>(
                  this, METHODID_VOTE_CONFIRM_DEPOSIT)))
          .addMethod(
            getVoteConfirmTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.VoteConfirmTokenRequest,
                axelar.evm.v1beta1.Tx.VoteConfirmTokenResponse>(
                  this, METHODID_VOTE_CONFIRM_TOKEN)))
          .addMethod(
            getVoteConfirmTransferKeyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyRequest,
                axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyResponse>(
                  this, METHODID_VOTE_CONFIRM_TRANSFER_KEY)))
          .addMethod(
            getCreateDeployTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.CreateDeployTokenRequest,
                axelar.evm.v1beta1.Tx.CreateDeployTokenResponse>(
                  this, METHODID_CREATE_DEPLOY_TOKEN)))
          .addMethod(
            getCreateBurnTokensMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.CreateBurnTokensRequest,
                axelar.evm.v1beta1.Tx.CreateBurnTokensResponse>(
                  this, METHODID_CREATE_BURN_TOKENS)))
          .addMethod(
            getCreatePendingTransfersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.CreatePendingTransfersRequest,
                axelar.evm.v1beta1.Tx.CreatePendingTransfersResponse>(
                  this, METHODID_CREATE_PENDING_TRANSFERS)))
          .addMethod(
            getCreateTransferOwnershipMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.CreateTransferOwnershipRequest,
                axelar.evm.v1beta1.Tx.CreateTransferOwnershipResponse>(
                  this, METHODID_CREATE_TRANSFER_OWNERSHIP)))
          .addMethod(
            getCreateTransferOperatorshipMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.CreateTransferOperatorshipRequest,
                axelar.evm.v1beta1.Tx.CreateTransferOperatorshipResponse>(
                  this, METHODID_CREATE_TRANSFER_OPERATORSHIP)))
          .addMethod(
            getSignCommandsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.SignCommandsRequest,
                axelar.evm.v1beta1.Tx.SignCommandsResponse>(
                  this, METHODID_SIGN_COMMANDS)))
          .addMethod(
            getAddChainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Tx.AddChainRequest,
                axelar.evm.v1beta1.Tx.AddChainResponse>(
                  this, METHODID_ADD_CHAIN)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the evm Msg service.
   * </pre>
   */
  public static final class MsgServiceStub extends io.grpc.stub.AbstractAsyncStub<MsgServiceStub> {
    private MsgServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceStub(channel, callOptions);
    }

    /**
     */
    public void link(axelar.evm.v1beta1.Tx.LinkRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.LinkResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLinkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirmChain(axelar.evm.v1beta1.Tx.ConfirmChainRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.ConfirmChainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConfirmChainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirmGatewayDeployment(axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConfirmGatewayDeploymentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirmToken(axelar.evm.v1beta1.Tx.ConfirmTokenRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.ConfirmTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConfirmTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirmDeposit(axelar.evm.v1beta1.Tx.ConfirmDepositRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.ConfirmDepositResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConfirmDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirmTransferKey(axelar.evm.v1beta1.Tx.ConfirmTransferKeyRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.ConfirmTransferKeyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConfirmTransferKeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void voteConfirmChain(axelar.evm.v1beta1.Tx.VoteConfirmChainRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.VoteConfirmChainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVoteConfirmChainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void voteConfirmGatewayDeployment(axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVoteConfirmGatewayDeploymentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void voteConfirmDeposit(axelar.evm.v1beta1.Tx.VoteConfirmDepositRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.VoteConfirmDepositResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVoteConfirmDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void voteConfirmToken(axelar.evm.v1beta1.Tx.VoteConfirmTokenRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.VoteConfirmTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVoteConfirmTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void voteConfirmTransferKey(axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVoteConfirmTransferKeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createDeployToken(axelar.evm.v1beta1.Tx.CreateDeployTokenRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.CreateDeployTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateDeployTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createBurnTokens(axelar.evm.v1beta1.Tx.CreateBurnTokensRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.CreateBurnTokensResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateBurnTokensMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createPendingTransfers(axelar.evm.v1beta1.Tx.CreatePendingTransfersRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.CreatePendingTransfersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreatePendingTransfersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createTransferOwnership(axelar.evm.v1beta1.Tx.CreateTransferOwnershipRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.CreateTransferOwnershipResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateTransferOwnershipMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createTransferOperatorship(axelar.evm.v1beta1.Tx.CreateTransferOperatorshipRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.CreateTransferOperatorshipResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateTransferOperatorshipMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void signCommands(axelar.evm.v1beta1.Tx.SignCommandsRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.SignCommandsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSignCommandsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addChain(axelar.evm.v1beta1.Tx.AddChainRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.AddChainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddChainMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the evm Msg service.
   * </pre>
   */
  public static final class MsgServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<MsgServiceBlockingStub> {
    private MsgServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.LinkResponse link(axelar.evm.v1beta1.Tx.LinkRequest request) {
      return blockingUnaryCall(
          getChannel(), getLinkMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.ConfirmChainResponse confirmChain(axelar.evm.v1beta1.Tx.ConfirmChainRequest request) {
      return blockingUnaryCall(
          getChannel(), getConfirmChainMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentResponse confirmGatewayDeployment(axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentRequest request) {
      return blockingUnaryCall(
          getChannel(), getConfirmGatewayDeploymentMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.ConfirmTokenResponse confirmToken(axelar.evm.v1beta1.Tx.ConfirmTokenRequest request) {
      return blockingUnaryCall(
          getChannel(), getConfirmTokenMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.ConfirmDepositResponse confirmDeposit(axelar.evm.v1beta1.Tx.ConfirmDepositRequest request) {
      return blockingUnaryCall(
          getChannel(), getConfirmDepositMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.ConfirmTransferKeyResponse confirmTransferKey(axelar.evm.v1beta1.Tx.ConfirmTransferKeyRequest request) {
      return blockingUnaryCall(
          getChannel(), getConfirmTransferKeyMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.VoteConfirmChainResponse voteConfirmChain(axelar.evm.v1beta1.Tx.VoteConfirmChainRequest request) {
      return blockingUnaryCall(
          getChannel(), getVoteConfirmChainMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentResponse voteConfirmGatewayDeployment(axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentRequest request) {
      return blockingUnaryCall(
          getChannel(), getVoteConfirmGatewayDeploymentMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.VoteConfirmDepositResponse voteConfirmDeposit(axelar.evm.v1beta1.Tx.VoteConfirmDepositRequest request) {
      return blockingUnaryCall(
          getChannel(), getVoteConfirmDepositMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.VoteConfirmTokenResponse voteConfirmToken(axelar.evm.v1beta1.Tx.VoteConfirmTokenRequest request) {
      return blockingUnaryCall(
          getChannel(), getVoteConfirmTokenMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyResponse voteConfirmTransferKey(axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyRequest request) {
      return blockingUnaryCall(
          getChannel(), getVoteConfirmTransferKeyMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.CreateDeployTokenResponse createDeployToken(axelar.evm.v1beta1.Tx.CreateDeployTokenRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateDeployTokenMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.CreateBurnTokensResponse createBurnTokens(axelar.evm.v1beta1.Tx.CreateBurnTokensRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateBurnTokensMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.CreatePendingTransfersResponse createPendingTransfers(axelar.evm.v1beta1.Tx.CreatePendingTransfersRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreatePendingTransfersMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.CreateTransferOwnershipResponse createTransferOwnership(axelar.evm.v1beta1.Tx.CreateTransferOwnershipRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateTransferOwnershipMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.CreateTransferOperatorshipResponse createTransferOperatorship(axelar.evm.v1beta1.Tx.CreateTransferOperatorshipRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateTransferOperatorshipMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.SignCommandsResponse signCommands(axelar.evm.v1beta1.Tx.SignCommandsRequest request) {
      return blockingUnaryCall(
          getChannel(), getSignCommandsMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.evm.v1beta1.Tx.AddChainResponse addChain(axelar.evm.v1beta1.Tx.AddChainRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddChainMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the evm Msg service.
   * </pre>
   */
  public static final class MsgServiceFutureStub extends io.grpc.stub.AbstractFutureStub<MsgServiceFutureStub> {
    private MsgServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.LinkResponse> link(
        axelar.evm.v1beta1.Tx.LinkRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLinkMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.ConfirmChainResponse> confirmChain(
        axelar.evm.v1beta1.Tx.ConfirmChainRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getConfirmChainMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentResponse> confirmGatewayDeployment(
        axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getConfirmGatewayDeploymentMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.ConfirmTokenResponse> confirmToken(
        axelar.evm.v1beta1.Tx.ConfirmTokenRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getConfirmTokenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.ConfirmDepositResponse> confirmDeposit(
        axelar.evm.v1beta1.Tx.ConfirmDepositRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getConfirmDepositMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.ConfirmTransferKeyResponse> confirmTransferKey(
        axelar.evm.v1beta1.Tx.ConfirmTransferKeyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getConfirmTransferKeyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.VoteConfirmChainResponse> voteConfirmChain(
        axelar.evm.v1beta1.Tx.VoteConfirmChainRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVoteConfirmChainMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentResponse> voteConfirmGatewayDeployment(
        axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVoteConfirmGatewayDeploymentMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.VoteConfirmDepositResponse> voteConfirmDeposit(
        axelar.evm.v1beta1.Tx.VoteConfirmDepositRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVoteConfirmDepositMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.VoteConfirmTokenResponse> voteConfirmToken(
        axelar.evm.v1beta1.Tx.VoteConfirmTokenRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVoteConfirmTokenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyResponse> voteConfirmTransferKey(
        axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVoteConfirmTransferKeyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.CreateDeployTokenResponse> createDeployToken(
        axelar.evm.v1beta1.Tx.CreateDeployTokenRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateDeployTokenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.CreateBurnTokensResponse> createBurnTokens(
        axelar.evm.v1beta1.Tx.CreateBurnTokensRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateBurnTokensMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.CreatePendingTransfersResponse> createPendingTransfers(
        axelar.evm.v1beta1.Tx.CreatePendingTransfersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreatePendingTransfersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.CreateTransferOwnershipResponse> createTransferOwnership(
        axelar.evm.v1beta1.Tx.CreateTransferOwnershipRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateTransferOwnershipMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.CreateTransferOperatorshipResponse> createTransferOperatorship(
        axelar.evm.v1beta1.Tx.CreateTransferOperatorshipRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateTransferOperatorshipMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.SignCommandsResponse> signCommands(
        axelar.evm.v1beta1.Tx.SignCommandsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSignCommandsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Tx.AddChainResponse> addChain(
        axelar.evm.v1beta1.Tx.AddChainRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddChainMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LINK = 0;
  private static final int METHODID_CONFIRM_CHAIN = 1;
  private static final int METHODID_CONFIRM_GATEWAY_DEPLOYMENT = 2;
  private static final int METHODID_CONFIRM_TOKEN = 3;
  private static final int METHODID_CONFIRM_DEPOSIT = 4;
  private static final int METHODID_CONFIRM_TRANSFER_KEY = 5;
  private static final int METHODID_VOTE_CONFIRM_CHAIN = 6;
  private static final int METHODID_VOTE_CONFIRM_GATEWAY_DEPLOYMENT = 7;
  private static final int METHODID_VOTE_CONFIRM_DEPOSIT = 8;
  private static final int METHODID_VOTE_CONFIRM_TOKEN = 9;
  private static final int METHODID_VOTE_CONFIRM_TRANSFER_KEY = 10;
  private static final int METHODID_CREATE_DEPLOY_TOKEN = 11;
  private static final int METHODID_CREATE_BURN_TOKENS = 12;
  private static final int METHODID_CREATE_PENDING_TRANSFERS = 13;
  private static final int METHODID_CREATE_TRANSFER_OWNERSHIP = 14;
  private static final int METHODID_CREATE_TRANSFER_OPERATORSHIP = 15;
  private static final int METHODID_SIGN_COMMANDS = 16;
  private static final int METHODID_ADD_CHAIN = 17;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MsgServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MsgServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LINK:
          serviceImpl.link((axelar.evm.v1beta1.Tx.LinkRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.LinkResponse>) responseObserver);
          break;
        case METHODID_CONFIRM_CHAIN:
          serviceImpl.confirmChain((axelar.evm.v1beta1.Tx.ConfirmChainRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.ConfirmChainResponse>) responseObserver);
          break;
        case METHODID_CONFIRM_GATEWAY_DEPLOYMENT:
          serviceImpl.confirmGatewayDeployment((axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.ConfirmGatewayDeploymentResponse>) responseObserver);
          break;
        case METHODID_CONFIRM_TOKEN:
          serviceImpl.confirmToken((axelar.evm.v1beta1.Tx.ConfirmTokenRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.ConfirmTokenResponse>) responseObserver);
          break;
        case METHODID_CONFIRM_DEPOSIT:
          serviceImpl.confirmDeposit((axelar.evm.v1beta1.Tx.ConfirmDepositRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.ConfirmDepositResponse>) responseObserver);
          break;
        case METHODID_CONFIRM_TRANSFER_KEY:
          serviceImpl.confirmTransferKey((axelar.evm.v1beta1.Tx.ConfirmTransferKeyRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.ConfirmTransferKeyResponse>) responseObserver);
          break;
        case METHODID_VOTE_CONFIRM_CHAIN:
          serviceImpl.voteConfirmChain((axelar.evm.v1beta1.Tx.VoteConfirmChainRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.VoteConfirmChainResponse>) responseObserver);
          break;
        case METHODID_VOTE_CONFIRM_GATEWAY_DEPLOYMENT:
          serviceImpl.voteConfirmGatewayDeployment((axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.VoteConfirmGatewayDeploymentResponse>) responseObserver);
          break;
        case METHODID_VOTE_CONFIRM_DEPOSIT:
          serviceImpl.voteConfirmDeposit((axelar.evm.v1beta1.Tx.VoteConfirmDepositRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.VoteConfirmDepositResponse>) responseObserver);
          break;
        case METHODID_VOTE_CONFIRM_TOKEN:
          serviceImpl.voteConfirmToken((axelar.evm.v1beta1.Tx.VoteConfirmTokenRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.VoteConfirmTokenResponse>) responseObserver);
          break;
        case METHODID_VOTE_CONFIRM_TRANSFER_KEY:
          serviceImpl.voteConfirmTransferKey((axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.VoteConfirmTransferKeyResponse>) responseObserver);
          break;
        case METHODID_CREATE_DEPLOY_TOKEN:
          serviceImpl.createDeployToken((axelar.evm.v1beta1.Tx.CreateDeployTokenRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.CreateDeployTokenResponse>) responseObserver);
          break;
        case METHODID_CREATE_BURN_TOKENS:
          serviceImpl.createBurnTokens((axelar.evm.v1beta1.Tx.CreateBurnTokensRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.CreateBurnTokensResponse>) responseObserver);
          break;
        case METHODID_CREATE_PENDING_TRANSFERS:
          serviceImpl.createPendingTransfers((axelar.evm.v1beta1.Tx.CreatePendingTransfersRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.CreatePendingTransfersResponse>) responseObserver);
          break;
        case METHODID_CREATE_TRANSFER_OWNERSHIP:
          serviceImpl.createTransferOwnership((axelar.evm.v1beta1.Tx.CreateTransferOwnershipRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.CreateTransferOwnershipResponse>) responseObserver);
          break;
        case METHODID_CREATE_TRANSFER_OPERATORSHIP:
          serviceImpl.createTransferOperatorship((axelar.evm.v1beta1.Tx.CreateTransferOperatorshipRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.CreateTransferOperatorshipResponse>) responseObserver);
          break;
        case METHODID_SIGN_COMMANDS:
          serviceImpl.signCommands((axelar.evm.v1beta1.Tx.SignCommandsRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.SignCommandsResponse>) responseObserver);
          break;
        case METHODID_ADD_CHAIN:
          serviceImpl.addChain((axelar.evm.v1beta1.Tx.AddChainRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Tx.AddChainResponse>) responseObserver);
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

  private static abstract class MsgServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return axelar.evm.v1beta1.Service.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MsgService");
    }
  }

  private static final class MsgServiceFileDescriptorSupplier
      extends MsgServiceBaseDescriptorSupplier {
    MsgServiceFileDescriptorSupplier() {}
  }

  private static final class MsgServiceMethodDescriptorSupplier
      extends MsgServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MsgServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (MsgServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MsgServiceFileDescriptorSupplier())
              .addMethod(getLinkMethod())
              .addMethod(getConfirmChainMethod())
              .addMethod(getConfirmGatewayDeploymentMethod())
              .addMethod(getConfirmTokenMethod())
              .addMethod(getConfirmDepositMethod())
              .addMethod(getConfirmTransferKeyMethod())
              .addMethod(getVoteConfirmChainMethod())
              .addMethod(getVoteConfirmGatewayDeploymentMethod())
              .addMethod(getVoteConfirmDepositMethod())
              .addMethod(getVoteConfirmTokenMethod())
              .addMethod(getVoteConfirmTransferKeyMethod())
              .addMethod(getCreateDeployTokenMethod())
              .addMethod(getCreateBurnTokensMethod())
              .addMethod(getCreatePendingTransfersMethod())
              .addMethod(getCreateTransferOwnershipMethod())
              .addMethod(getCreateTransferOperatorshipMethod())
              .addMethod(getSignCommandsMethod())
              .addMethod(getAddChainMethod())
              .build();
        }
      }
    }
    return result;
  }
}
