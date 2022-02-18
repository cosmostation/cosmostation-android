package io.provenance.marker.v1;

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
 * Msg defines the Marker Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: provenance/marker/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "provenance.marker.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgFinalizeRequest,
      io.provenance.marker.v1.MsgFinalizeResponse> getFinalizeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Finalize",
      requestType = io.provenance.marker.v1.MsgFinalizeRequest.class,
      responseType = io.provenance.marker.v1.MsgFinalizeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgFinalizeRequest,
      io.provenance.marker.v1.MsgFinalizeResponse> getFinalizeMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgFinalizeRequest, io.provenance.marker.v1.MsgFinalizeResponse> getFinalizeMethod;
    if ((getFinalizeMethod = MsgGrpc.getFinalizeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getFinalizeMethod = MsgGrpc.getFinalizeMethod) == null) {
          MsgGrpc.getFinalizeMethod = getFinalizeMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.MsgFinalizeRequest, io.provenance.marker.v1.MsgFinalizeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Finalize"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgFinalizeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgFinalizeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Finalize"))
              .build();
        }
      }
    }
    return getFinalizeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgActivateRequest,
      io.provenance.marker.v1.MsgActivateResponse> getActivateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Activate",
      requestType = io.provenance.marker.v1.MsgActivateRequest.class,
      responseType = io.provenance.marker.v1.MsgActivateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgActivateRequest,
      io.provenance.marker.v1.MsgActivateResponse> getActivateMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgActivateRequest, io.provenance.marker.v1.MsgActivateResponse> getActivateMethod;
    if ((getActivateMethod = MsgGrpc.getActivateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getActivateMethod = MsgGrpc.getActivateMethod) == null) {
          MsgGrpc.getActivateMethod = getActivateMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.MsgActivateRequest, io.provenance.marker.v1.MsgActivateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Activate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgActivateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgActivateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Activate"))
              .build();
        }
      }
    }
    return getActivateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgCancelRequest,
      io.provenance.marker.v1.MsgCancelResponse> getCancelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Cancel",
      requestType = io.provenance.marker.v1.MsgCancelRequest.class,
      responseType = io.provenance.marker.v1.MsgCancelResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgCancelRequest,
      io.provenance.marker.v1.MsgCancelResponse> getCancelMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgCancelRequest, io.provenance.marker.v1.MsgCancelResponse> getCancelMethod;
    if ((getCancelMethod = MsgGrpc.getCancelMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelMethod = MsgGrpc.getCancelMethod) == null) {
          MsgGrpc.getCancelMethod = getCancelMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.MsgCancelRequest, io.provenance.marker.v1.MsgCancelResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Cancel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgCancelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgCancelResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Cancel"))
              .build();
        }
      }
    }
    return getCancelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgDeleteRequest,
      io.provenance.marker.v1.MsgDeleteResponse> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Delete",
      requestType = io.provenance.marker.v1.MsgDeleteRequest.class,
      responseType = io.provenance.marker.v1.MsgDeleteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgDeleteRequest,
      io.provenance.marker.v1.MsgDeleteResponse> getDeleteMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgDeleteRequest, io.provenance.marker.v1.MsgDeleteResponse> getDeleteMethod;
    if ((getDeleteMethod = MsgGrpc.getDeleteMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteMethod = MsgGrpc.getDeleteMethod) == null) {
          MsgGrpc.getDeleteMethod = getDeleteMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.MsgDeleteRequest, io.provenance.marker.v1.MsgDeleteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgDeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgDeleteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Delete"))
              .build();
        }
      }
    }
    return getDeleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgMintRequest,
      io.provenance.marker.v1.MsgMintResponse> getMintMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Mint",
      requestType = io.provenance.marker.v1.MsgMintRequest.class,
      responseType = io.provenance.marker.v1.MsgMintResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgMintRequest,
      io.provenance.marker.v1.MsgMintResponse> getMintMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgMintRequest, io.provenance.marker.v1.MsgMintResponse> getMintMethod;
    if ((getMintMethod = MsgGrpc.getMintMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMintMethod = MsgGrpc.getMintMethod) == null) {
          MsgGrpc.getMintMethod = getMintMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.MsgMintRequest, io.provenance.marker.v1.MsgMintResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Mint"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgMintRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgMintResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Mint"))
              .build();
        }
      }
    }
    return getMintMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgBurnRequest,
      io.provenance.marker.v1.MsgBurnResponse> getBurnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Burn",
      requestType = io.provenance.marker.v1.MsgBurnRequest.class,
      responseType = io.provenance.marker.v1.MsgBurnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgBurnRequest,
      io.provenance.marker.v1.MsgBurnResponse> getBurnMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgBurnRequest, io.provenance.marker.v1.MsgBurnResponse> getBurnMethod;
    if ((getBurnMethod = MsgGrpc.getBurnMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBurnMethod = MsgGrpc.getBurnMethod) == null) {
          MsgGrpc.getBurnMethod = getBurnMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.MsgBurnRequest, io.provenance.marker.v1.MsgBurnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Burn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgBurnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgBurnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Burn"))
              .build();
        }
      }
    }
    return getBurnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgAddAccessRequest,
      io.provenance.marker.v1.MsgAddAccessResponse> getAddAccessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddAccess",
      requestType = io.provenance.marker.v1.MsgAddAccessRequest.class,
      responseType = io.provenance.marker.v1.MsgAddAccessResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgAddAccessRequest,
      io.provenance.marker.v1.MsgAddAccessResponse> getAddAccessMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgAddAccessRequest, io.provenance.marker.v1.MsgAddAccessResponse> getAddAccessMethod;
    if ((getAddAccessMethod = MsgGrpc.getAddAccessMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddAccessMethod = MsgGrpc.getAddAccessMethod) == null) {
          MsgGrpc.getAddAccessMethod = getAddAccessMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.MsgAddAccessRequest, io.provenance.marker.v1.MsgAddAccessResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddAccess"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgAddAccessRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgAddAccessResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddAccess"))
              .build();
        }
      }
    }
    return getAddAccessMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgDeleteAccessRequest,
      io.provenance.marker.v1.MsgDeleteAccessResponse> getDeleteAccessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteAccess",
      requestType = io.provenance.marker.v1.MsgDeleteAccessRequest.class,
      responseType = io.provenance.marker.v1.MsgDeleteAccessResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgDeleteAccessRequest,
      io.provenance.marker.v1.MsgDeleteAccessResponse> getDeleteAccessMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgDeleteAccessRequest, io.provenance.marker.v1.MsgDeleteAccessResponse> getDeleteAccessMethod;
    if ((getDeleteAccessMethod = MsgGrpc.getDeleteAccessMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteAccessMethod = MsgGrpc.getDeleteAccessMethod) == null) {
          MsgGrpc.getDeleteAccessMethod = getDeleteAccessMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.MsgDeleteAccessRequest, io.provenance.marker.v1.MsgDeleteAccessResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteAccess"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgDeleteAccessRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgDeleteAccessResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteAccess"))
              .build();
        }
      }
    }
    return getDeleteAccessMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgWithdrawRequest,
      io.provenance.marker.v1.MsgWithdrawResponse> getWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Withdraw",
      requestType = io.provenance.marker.v1.MsgWithdrawRequest.class,
      responseType = io.provenance.marker.v1.MsgWithdrawResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgWithdrawRequest,
      io.provenance.marker.v1.MsgWithdrawResponse> getWithdrawMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgWithdrawRequest, io.provenance.marker.v1.MsgWithdrawResponse> getWithdrawMethod;
    if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
          MsgGrpc.getWithdrawMethod = getWithdrawMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.MsgWithdrawRequest, io.provenance.marker.v1.MsgWithdrawResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Withdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgWithdrawRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgWithdrawResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Withdraw"))
              .build();
        }
      }
    }
    return getWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgAddMarkerRequest,
      io.provenance.marker.v1.MsgAddMarkerResponse> getAddMarkerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddMarker",
      requestType = io.provenance.marker.v1.MsgAddMarkerRequest.class,
      responseType = io.provenance.marker.v1.MsgAddMarkerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgAddMarkerRequest,
      io.provenance.marker.v1.MsgAddMarkerResponse> getAddMarkerMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgAddMarkerRequest, io.provenance.marker.v1.MsgAddMarkerResponse> getAddMarkerMethod;
    if ((getAddMarkerMethod = MsgGrpc.getAddMarkerMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddMarkerMethod = MsgGrpc.getAddMarkerMethod) == null) {
          MsgGrpc.getAddMarkerMethod = getAddMarkerMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.MsgAddMarkerRequest, io.provenance.marker.v1.MsgAddMarkerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddMarker"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgAddMarkerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgAddMarkerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddMarker"))
              .build();
        }
      }
    }
    return getAddMarkerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgTransferRequest,
      io.provenance.marker.v1.MsgTransferResponse> getTransferMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Transfer",
      requestType = io.provenance.marker.v1.MsgTransferRequest.class,
      responseType = io.provenance.marker.v1.MsgTransferResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgTransferRequest,
      io.provenance.marker.v1.MsgTransferResponse> getTransferMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgTransferRequest, io.provenance.marker.v1.MsgTransferResponse> getTransferMethod;
    if ((getTransferMethod = MsgGrpc.getTransferMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getTransferMethod = MsgGrpc.getTransferMethod) == null) {
          MsgGrpc.getTransferMethod = getTransferMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.MsgTransferRequest, io.provenance.marker.v1.MsgTransferResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Transfer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgTransferRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgTransferResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Transfer"))
              .build();
        }
      }
    }
    return getTransferMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgSetDenomMetadataRequest,
      io.provenance.marker.v1.MsgSetDenomMetadataResponse> getSetDenomMetadataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetDenomMetadata",
      requestType = io.provenance.marker.v1.MsgSetDenomMetadataRequest.class,
      responseType = io.provenance.marker.v1.MsgSetDenomMetadataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgSetDenomMetadataRequest,
      io.provenance.marker.v1.MsgSetDenomMetadataResponse> getSetDenomMetadataMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.MsgSetDenomMetadataRequest, io.provenance.marker.v1.MsgSetDenomMetadataResponse> getSetDenomMetadataMethod;
    if ((getSetDenomMetadataMethod = MsgGrpc.getSetDenomMetadataMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetDenomMetadataMethod = MsgGrpc.getSetDenomMetadataMethod) == null) {
          MsgGrpc.getSetDenomMetadataMethod = getSetDenomMetadataMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.MsgSetDenomMetadataRequest, io.provenance.marker.v1.MsgSetDenomMetadataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetDenomMetadata"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgSetDenomMetadataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.MsgSetDenomMetadataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetDenomMetadata"))
              .build();
        }
      }
    }
    return getSetDenomMetadataMethod;
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
   * Msg defines the Marker Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Finalize
     * </pre>
     */
    public void finalize(io.provenance.marker.v1.MsgFinalizeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgFinalizeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFinalizeMethod(), responseObserver);
    }

    /**
     * <pre>
     * Activate
     * </pre>
     */
    public void activate(io.provenance.marker.v1.MsgActivateRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgActivateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getActivateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Cancel
     * </pre>
     */
    public void cancel(io.provenance.marker.v1.MsgCancelRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgCancelResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelMethod(), responseObserver);
    }

    /**
     * <pre>
     * Delete
     * </pre>
     */
    public void delete(io.provenance.marker.v1.MsgDeleteRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgDeleteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }

    /**
     * <pre>
     * Mint
     * </pre>
     */
    public void mint(io.provenance.marker.v1.MsgMintRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgMintResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMintMethod(), responseObserver);
    }

    /**
     * <pre>
     * Burn
     * </pre>
     */
    public void burn(io.provenance.marker.v1.MsgBurnRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgBurnResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBurnMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddAccess
     * </pre>
     */
    public void addAccess(io.provenance.marker.v1.MsgAddAccessRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgAddAccessResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddAccessMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteAccess
     * </pre>
     */
    public void deleteAccess(io.provenance.marker.v1.MsgDeleteAccessRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgDeleteAccessResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteAccessMethod(), responseObserver);
    }

    /**
     * <pre>
     * Withdraw
     * </pre>
     */
    public void withdraw(io.provenance.marker.v1.MsgWithdrawRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgWithdrawResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddMarker
     * </pre>
     */
    public void addMarker(io.provenance.marker.v1.MsgAddMarkerRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgAddMarkerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddMarkerMethod(), responseObserver);
    }

    /**
     * <pre>
     * Transfer marker denominated coin between accounts
     * </pre>
     */
    public void transfer(io.provenance.marker.v1.MsgTransferRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgTransferResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTransferMethod(), responseObserver);
    }

    /**
     * <pre>
     * Allows Denom Metadata (see bank module) to be set for the Marker's Denom
     * </pre>
     */
    public void setDenomMetadata(io.provenance.marker.v1.MsgSetDenomMetadataRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgSetDenomMetadataResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetDenomMetadataMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFinalizeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.MsgFinalizeRequest,
                io.provenance.marker.v1.MsgFinalizeResponse>(
                  this, METHODID_FINALIZE)))
          .addMethod(
            getActivateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.MsgActivateRequest,
                io.provenance.marker.v1.MsgActivateResponse>(
                  this, METHODID_ACTIVATE)))
          .addMethod(
            getCancelMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.MsgCancelRequest,
                io.provenance.marker.v1.MsgCancelResponse>(
                  this, METHODID_CANCEL)))
          .addMethod(
            getDeleteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.MsgDeleteRequest,
                io.provenance.marker.v1.MsgDeleteResponse>(
                  this, METHODID_DELETE)))
          .addMethod(
            getMintMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.MsgMintRequest,
                io.provenance.marker.v1.MsgMintResponse>(
                  this, METHODID_MINT)))
          .addMethod(
            getBurnMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.MsgBurnRequest,
                io.provenance.marker.v1.MsgBurnResponse>(
                  this, METHODID_BURN)))
          .addMethod(
            getAddAccessMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.MsgAddAccessRequest,
                io.provenance.marker.v1.MsgAddAccessResponse>(
                  this, METHODID_ADD_ACCESS)))
          .addMethod(
            getDeleteAccessMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.MsgDeleteAccessRequest,
                io.provenance.marker.v1.MsgDeleteAccessResponse>(
                  this, METHODID_DELETE_ACCESS)))
          .addMethod(
            getWithdrawMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.MsgWithdrawRequest,
                io.provenance.marker.v1.MsgWithdrawResponse>(
                  this, METHODID_WITHDRAW)))
          .addMethod(
            getAddMarkerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.MsgAddMarkerRequest,
                io.provenance.marker.v1.MsgAddMarkerResponse>(
                  this, METHODID_ADD_MARKER)))
          .addMethod(
            getTransferMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.MsgTransferRequest,
                io.provenance.marker.v1.MsgTransferResponse>(
                  this, METHODID_TRANSFER)))
          .addMethod(
            getSetDenomMetadataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.MsgSetDenomMetadataRequest,
                io.provenance.marker.v1.MsgSetDenomMetadataResponse>(
                  this, METHODID_SET_DENOM_METADATA)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the Marker Msg service.
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
     * Finalize
     * </pre>
     */
    public void finalize(io.provenance.marker.v1.MsgFinalizeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgFinalizeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFinalizeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Activate
     * </pre>
     */
    public void activate(io.provenance.marker.v1.MsgActivateRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgActivateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getActivateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Cancel
     * </pre>
     */
    public void cancel(io.provenance.marker.v1.MsgCancelRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgCancelResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Delete
     * </pre>
     */
    public void delete(io.provenance.marker.v1.MsgDeleteRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgDeleteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Mint
     * </pre>
     */
    public void mint(io.provenance.marker.v1.MsgMintRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgMintResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMintMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Burn
     * </pre>
     */
    public void burn(io.provenance.marker.v1.MsgBurnRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgBurnResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBurnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddAccess
     * </pre>
     */
    public void addAccess(io.provenance.marker.v1.MsgAddAccessRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgAddAccessResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddAccessMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteAccess
     * </pre>
     */
    public void deleteAccess(io.provenance.marker.v1.MsgDeleteAccessRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgDeleteAccessResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteAccessMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Withdraw
     * </pre>
     */
    public void withdraw(io.provenance.marker.v1.MsgWithdrawRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgWithdrawResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddMarker
     * </pre>
     */
    public void addMarker(io.provenance.marker.v1.MsgAddMarkerRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgAddMarkerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddMarkerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Transfer marker denominated coin between accounts
     * </pre>
     */
    public void transfer(io.provenance.marker.v1.MsgTransferRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgTransferResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Allows Denom Metadata (see bank module) to be set for the Marker's Denom
     * </pre>
     */
    public void setDenomMetadata(io.provenance.marker.v1.MsgSetDenomMetadataRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgSetDenomMetadataResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetDenomMetadataMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the Marker Msg service.
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
     * Finalize
     * </pre>
     */
    public io.provenance.marker.v1.MsgFinalizeResponse finalize(io.provenance.marker.v1.MsgFinalizeRequest request) {
      return blockingUnaryCall(
          getChannel(), getFinalizeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Activate
     * </pre>
     */
    public io.provenance.marker.v1.MsgActivateResponse activate(io.provenance.marker.v1.MsgActivateRequest request) {
      return blockingUnaryCall(
          getChannel(), getActivateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Cancel
     * </pre>
     */
    public io.provenance.marker.v1.MsgCancelResponse cancel(io.provenance.marker.v1.MsgCancelRequest request) {
      return blockingUnaryCall(
          getChannel(), getCancelMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Delete
     * </pre>
     */
    public io.provenance.marker.v1.MsgDeleteResponse delete(io.provenance.marker.v1.MsgDeleteRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Mint
     * </pre>
     */
    public io.provenance.marker.v1.MsgMintResponse mint(io.provenance.marker.v1.MsgMintRequest request) {
      return blockingUnaryCall(
          getChannel(), getMintMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Burn
     * </pre>
     */
    public io.provenance.marker.v1.MsgBurnResponse burn(io.provenance.marker.v1.MsgBurnRequest request) {
      return blockingUnaryCall(
          getChannel(), getBurnMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddAccess
     * </pre>
     */
    public io.provenance.marker.v1.MsgAddAccessResponse addAccess(io.provenance.marker.v1.MsgAddAccessRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddAccessMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteAccess
     * </pre>
     */
    public io.provenance.marker.v1.MsgDeleteAccessResponse deleteAccess(io.provenance.marker.v1.MsgDeleteAccessRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteAccessMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Withdraw
     * </pre>
     */
    public io.provenance.marker.v1.MsgWithdrawResponse withdraw(io.provenance.marker.v1.MsgWithdrawRequest request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddMarker
     * </pre>
     */
    public io.provenance.marker.v1.MsgAddMarkerResponse addMarker(io.provenance.marker.v1.MsgAddMarkerRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddMarkerMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Transfer marker denominated coin between accounts
     * </pre>
     */
    public io.provenance.marker.v1.MsgTransferResponse transfer(io.provenance.marker.v1.MsgTransferRequest request) {
      return blockingUnaryCall(
          getChannel(), getTransferMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Allows Denom Metadata (see bank module) to be set for the Marker's Denom
     * </pre>
     */
    public io.provenance.marker.v1.MsgSetDenomMetadataResponse setDenomMetadata(io.provenance.marker.v1.MsgSetDenomMetadataRequest request) {
      return blockingUnaryCall(
          getChannel(), getSetDenomMetadataMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the Marker Msg service.
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
     * Finalize
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.MsgFinalizeResponse> finalize(
        io.provenance.marker.v1.MsgFinalizeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFinalizeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Activate
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.MsgActivateResponse> activate(
        io.provenance.marker.v1.MsgActivateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getActivateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Cancel
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.MsgCancelResponse> cancel(
        io.provenance.marker.v1.MsgCancelRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Delete
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.MsgDeleteResponse> delete(
        io.provenance.marker.v1.MsgDeleteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Mint
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.MsgMintResponse> mint(
        io.provenance.marker.v1.MsgMintRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMintMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Burn
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.MsgBurnResponse> burn(
        io.provenance.marker.v1.MsgBurnRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBurnMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddAccess
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.MsgAddAccessResponse> addAccess(
        io.provenance.marker.v1.MsgAddAccessRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddAccessMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteAccess
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.MsgDeleteAccessResponse> deleteAccess(
        io.provenance.marker.v1.MsgDeleteAccessRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteAccessMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Withdraw
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.MsgWithdrawResponse> withdraw(
        io.provenance.marker.v1.MsgWithdrawRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddMarker
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.MsgAddMarkerResponse> addMarker(
        io.provenance.marker.v1.MsgAddMarkerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddMarkerMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Transfer marker denominated coin between accounts
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.MsgTransferResponse> transfer(
        io.provenance.marker.v1.MsgTransferRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Allows Denom Metadata (see bank module) to be set for the Marker's Denom
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.MsgSetDenomMetadataResponse> setDenomMetadata(
        io.provenance.marker.v1.MsgSetDenomMetadataRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSetDenomMetadataMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FINALIZE = 0;
  private static final int METHODID_ACTIVATE = 1;
  private static final int METHODID_CANCEL = 2;
  private static final int METHODID_DELETE = 3;
  private static final int METHODID_MINT = 4;
  private static final int METHODID_BURN = 5;
  private static final int METHODID_ADD_ACCESS = 6;
  private static final int METHODID_DELETE_ACCESS = 7;
  private static final int METHODID_WITHDRAW = 8;
  private static final int METHODID_ADD_MARKER = 9;
  private static final int METHODID_TRANSFER = 10;
  private static final int METHODID_SET_DENOM_METADATA = 11;

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
        case METHODID_FINALIZE:
          serviceImpl.finalize((io.provenance.marker.v1.MsgFinalizeRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgFinalizeResponse>) responseObserver);
          break;
        case METHODID_ACTIVATE:
          serviceImpl.activate((io.provenance.marker.v1.MsgActivateRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgActivateResponse>) responseObserver);
          break;
        case METHODID_CANCEL:
          serviceImpl.cancel((io.provenance.marker.v1.MsgCancelRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgCancelResponse>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((io.provenance.marker.v1.MsgDeleteRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgDeleteResponse>) responseObserver);
          break;
        case METHODID_MINT:
          serviceImpl.mint((io.provenance.marker.v1.MsgMintRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgMintResponse>) responseObserver);
          break;
        case METHODID_BURN:
          serviceImpl.burn((io.provenance.marker.v1.MsgBurnRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgBurnResponse>) responseObserver);
          break;
        case METHODID_ADD_ACCESS:
          serviceImpl.addAccess((io.provenance.marker.v1.MsgAddAccessRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgAddAccessResponse>) responseObserver);
          break;
        case METHODID_DELETE_ACCESS:
          serviceImpl.deleteAccess((io.provenance.marker.v1.MsgDeleteAccessRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgDeleteAccessResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW:
          serviceImpl.withdraw((io.provenance.marker.v1.MsgWithdrawRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgWithdrawResponse>) responseObserver);
          break;
        case METHODID_ADD_MARKER:
          serviceImpl.addMarker((io.provenance.marker.v1.MsgAddMarkerRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgAddMarkerResponse>) responseObserver);
          break;
        case METHODID_TRANSFER:
          serviceImpl.transfer((io.provenance.marker.v1.MsgTransferRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgTransferResponse>) responseObserver);
          break;
        case METHODID_SET_DENOM_METADATA:
          serviceImpl.setDenomMetadata((io.provenance.marker.v1.MsgSetDenomMetadataRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.MsgSetDenomMetadataResponse>) responseObserver);
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
      return io.provenance.marker.v1.Tx.getDescriptor();
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
              .addMethod(getFinalizeMethod())
              .addMethod(getActivateMethod())
              .addMethod(getCancelMethod())
              .addMethod(getDeleteMethod())
              .addMethod(getMintMethod())
              .addMethod(getBurnMethod())
              .addMethod(getAddAccessMethod())
              .addMethod(getDeleteAccessMethod())
              .addMethod(getWithdrawMethod())
              .addMethod(getAddMarkerMethod())
              .addMethod(getTransferMethod())
              .addMethod(getSetDenomMetadataMethod())
              .build();
        }
      }
    }
    return result;
  }
}
