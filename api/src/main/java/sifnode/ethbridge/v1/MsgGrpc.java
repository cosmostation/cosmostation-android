package sifnode.ethbridge.v1;

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
 * Msg service for messages
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: sifnode/ethbridge/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "sifnode.ethbridge.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgLock,
      sifnode.ethbridge.v1.Tx.MsgLockResponse> getLockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Lock",
      requestType = sifnode.ethbridge.v1.Tx.MsgLock.class,
      responseType = sifnode.ethbridge.v1.Tx.MsgLockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgLock,
      sifnode.ethbridge.v1.Tx.MsgLockResponse> getLockMethod() {
    io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgLock, sifnode.ethbridge.v1.Tx.MsgLockResponse> getLockMethod;
    if ((getLockMethod = MsgGrpc.getLockMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLockMethod = MsgGrpc.getLockMethod) == null) {
          MsgGrpc.getLockMethod = getLockMethod =
              io.grpc.MethodDescriptor.<sifnode.ethbridge.v1.Tx.MsgLock, sifnode.ethbridge.v1.Tx.MsgLockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Lock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.ethbridge.v1.Tx.MsgLock.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.ethbridge.v1.Tx.MsgLockResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Lock"))
              .build();
        }
      }
    }
    return getLockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgBurn,
      sifnode.ethbridge.v1.Tx.MsgBurnResponse> getBurnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Burn",
      requestType = sifnode.ethbridge.v1.Tx.MsgBurn.class,
      responseType = sifnode.ethbridge.v1.Tx.MsgBurnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgBurn,
      sifnode.ethbridge.v1.Tx.MsgBurnResponse> getBurnMethod() {
    io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgBurn, sifnode.ethbridge.v1.Tx.MsgBurnResponse> getBurnMethod;
    if ((getBurnMethod = MsgGrpc.getBurnMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBurnMethod = MsgGrpc.getBurnMethod) == null) {
          MsgGrpc.getBurnMethod = getBurnMethod =
              io.grpc.MethodDescriptor.<sifnode.ethbridge.v1.Tx.MsgBurn, sifnode.ethbridge.v1.Tx.MsgBurnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Burn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.ethbridge.v1.Tx.MsgBurn.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.ethbridge.v1.Tx.MsgBurnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Burn"))
              .build();
        }
      }
    }
    return getBurnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaim,
      sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaimResponse> getCreateEthBridgeClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateEthBridgeClaim",
      requestType = sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaim.class,
      responseType = sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaim,
      sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaimResponse> getCreateEthBridgeClaimMethod() {
    io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaim, sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaimResponse> getCreateEthBridgeClaimMethod;
    if ((getCreateEthBridgeClaimMethod = MsgGrpc.getCreateEthBridgeClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateEthBridgeClaimMethod = MsgGrpc.getCreateEthBridgeClaimMethod) == null) {
          MsgGrpc.getCreateEthBridgeClaimMethod = getCreateEthBridgeClaimMethod =
              io.grpc.MethodDescriptor.<sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaim, sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateEthBridgeClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateEthBridgeClaim"))
              .build();
        }
      }
    }
    return getCreateEthBridgeClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidator,
      sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidatorResponse> getUpdateWhiteListValidatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateWhiteListValidator",
      requestType = sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidator.class,
      responseType = sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidator,
      sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidatorResponse> getUpdateWhiteListValidatorMethod() {
    io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidator, sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidatorResponse> getUpdateWhiteListValidatorMethod;
    if ((getUpdateWhiteListValidatorMethod = MsgGrpc.getUpdateWhiteListValidatorMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateWhiteListValidatorMethod = MsgGrpc.getUpdateWhiteListValidatorMethod) == null) {
          MsgGrpc.getUpdateWhiteListValidatorMethod = getUpdateWhiteListValidatorMethod =
              io.grpc.MethodDescriptor.<sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidator, sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateWhiteListValidator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidator.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateWhiteListValidator"))
              .build();
        }
      }
    }
    return getUpdateWhiteListValidatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccount,
      sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccountResponse> getUpdateCethReceiverAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateCethReceiverAccount",
      requestType = sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccount.class,
      responseType = sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccount,
      sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccountResponse> getUpdateCethReceiverAccountMethod() {
    io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccount, sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccountResponse> getUpdateCethReceiverAccountMethod;
    if ((getUpdateCethReceiverAccountMethod = MsgGrpc.getUpdateCethReceiverAccountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateCethReceiverAccountMethod = MsgGrpc.getUpdateCethReceiverAccountMethod) == null) {
          MsgGrpc.getUpdateCethReceiverAccountMethod = getUpdateCethReceiverAccountMethod =
              io.grpc.MethodDescriptor.<sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccount, sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateCethReceiverAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateCethReceiverAccount"))
              .build();
        }
      }
    }
    return getUpdateCethReceiverAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgRescueCeth,
      sifnode.ethbridge.v1.Tx.MsgRescueCethResponse> getRescueCethMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RescueCeth",
      requestType = sifnode.ethbridge.v1.Tx.MsgRescueCeth.class,
      responseType = sifnode.ethbridge.v1.Tx.MsgRescueCethResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgRescueCeth,
      sifnode.ethbridge.v1.Tx.MsgRescueCethResponse> getRescueCethMethod() {
    io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgRescueCeth, sifnode.ethbridge.v1.Tx.MsgRescueCethResponse> getRescueCethMethod;
    if ((getRescueCethMethod = MsgGrpc.getRescueCethMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRescueCethMethod = MsgGrpc.getRescueCethMethod) == null) {
          MsgGrpc.getRescueCethMethod = getRescueCethMethod =
              io.grpc.MethodDescriptor.<sifnode.ethbridge.v1.Tx.MsgRescueCeth, sifnode.ethbridge.v1.Tx.MsgRescueCethResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RescueCeth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.ethbridge.v1.Tx.MsgRescueCeth.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.ethbridge.v1.Tx.MsgRescueCethResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RescueCeth"))
              .build();
        }
      }
    }
    return getRescueCethMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgSetBlacklist,
      sifnode.ethbridge.v1.Tx.MsgSetBlacklistResponse> getSetBlacklistMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetBlacklist",
      requestType = sifnode.ethbridge.v1.Tx.MsgSetBlacklist.class,
      responseType = sifnode.ethbridge.v1.Tx.MsgSetBlacklistResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgSetBlacklist,
      sifnode.ethbridge.v1.Tx.MsgSetBlacklistResponse> getSetBlacklistMethod() {
    io.grpc.MethodDescriptor<sifnode.ethbridge.v1.Tx.MsgSetBlacklist, sifnode.ethbridge.v1.Tx.MsgSetBlacklistResponse> getSetBlacklistMethod;
    if ((getSetBlacklistMethod = MsgGrpc.getSetBlacklistMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetBlacklistMethod = MsgGrpc.getSetBlacklistMethod) == null) {
          MsgGrpc.getSetBlacklistMethod = getSetBlacklistMethod =
              io.grpc.MethodDescriptor.<sifnode.ethbridge.v1.Tx.MsgSetBlacklist, sifnode.ethbridge.v1.Tx.MsgSetBlacklistResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetBlacklist"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.ethbridge.v1.Tx.MsgSetBlacklist.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.ethbridge.v1.Tx.MsgSetBlacklistResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetBlacklist"))
              .build();
        }
      }
    }
    return getSetBlacklistMethod;
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
   * Msg service for messages
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     */
    public void lock(sifnode.ethbridge.v1.Tx.MsgLock request,
        io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgLockResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLockMethod(), responseObserver);
    }

    /**
     */
    public void burn(sifnode.ethbridge.v1.Tx.MsgBurn request,
        io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgBurnResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBurnMethod(), responseObserver);
    }

    /**
     */
    public void createEthBridgeClaim(sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaim request,
        io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaimResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateEthBridgeClaimMethod(), responseObserver);
    }

    /**
     */
    public void updateWhiteListValidator(sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidator request,
        io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidatorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateWhiteListValidatorMethod(), responseObserver);
    }

    /**
     */
    public void updateCethReceiverAccount(sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccount request,
        io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateCethReceiverAccountMethod(), responseObserver);
    }

    /**
     */
    public void rescueCeth(sifnode.ethbridge.v1.Tx.MsgRescueCeth request,
        io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgRescueCethResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRescueCethMethod(), responseObserver);
    }

    /**
     */
    public void setBlacklist(sifnode.ethbridge.v1.Tx.MsgSetBlacklist request,
        io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgSetBlacklistResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetBlacklistMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLockMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.ethbridge.v1.Tx.MsgLock,
                sifnode.ethbridge.v1.Tx.MsgLockResponse>(
                  this, METHODID_LOCK)))
          .addMethod(
            getBurnMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.ethbridge.v1.Tx.MsgBurn,
                sifnode.ethbridge.v1.Tx.MsgBurnResponse>(
                  this, METHODID_BURN)))
          .addMethod(
            getCreateEthBridgeClaimMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaim,
                sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaimResponse>(
                  this, METHODID_CREATE_ETH_BRIDGE_CLAIM)))
          .addMethod(
            getUpdateWhiteListValidatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidator,
                sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidatorResponse>(
                  this, METHODID_UPDATE_WHITE_LIST_VALIDATOR)))
          .addMethod(
            getUpdateCethReceiverAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccount,
                sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccountResponse>(
                  this, METHODID_UPDATE_CETH_RECEIVER_ACCOUNT)))
          .addMethod(
            getRescueCethMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.ethbridge.v1.Tx.MsgRescueCeth,
                sifnode.ethbridge.v1.Tx.MsgRescueCethResponse>(
                  this, METHODID_RESCUE_CETH)))
          .addMethod(
            getSetBlacklistMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.ethbridge.v1.Tx.MsgSetBlacklist,
                sifnode.ethbridge.v1.Tx.MsgSetBlacklistResponse>(
                  this, METHODID_SET_BLACKLIST)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg service for messages
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
    public void lock(sifnode.ethbridge.v1.Tx.MsgLock request,
        io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgLockResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void burn(sifnode.ethbridge.v1.Tx.MsgBurn request,
        io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgBurnResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBurnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createEthBridgeClaim(sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaim request,
        io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaimResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateEthBridgeClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateWhiteListValidator(sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidator request,
        io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidatorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateWhiteListValidatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateCethReceiverAccount(sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccount request,
        io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateCethReceiverAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rescueCeth(sifnode.ethbridge.v1.Tx.MsgRescueCeth request,
        io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgRescueCethResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRescueCethMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setBlacklist(sifnode.ethbridge.v1.Tx.MsgSetBlacklist request,
        io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgSetBlacklistResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetBlacklistMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg service for messages
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
    public sifnode.ethbridge.v1.Tx.MsgLockResponse lock(sifnode.ethbridge.v1.Tx.MsgLock request) {
      return blockingUnaryCall(
          getChannel(), getLockMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.ethbridge.v1.Tx.MsgBurnResponse burn(sifnode.ethbridge.v1.Tx.MsgBurn request) {
      return blockingUnaryCall(
          getChannel(), getBurnMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaimResponse createEthBridgeClaim(sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaim request) {
      return blockingUnaryCall(
          getChannel(), getCreateEthBridgeClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidatorResponse updateWhiteListValidator(sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidator request) {
      return blockingUnaryCall(
          getChannel(), getUpdateWhiteListValidatorMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccountResponse updateCethReceiverAccount(sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccount request) {
      return blockingUnaryCall(
          getChannel(), getUpdateCethReceiverAccountMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.ethbridge.v1.Tx.MsgRescueCethResponse rescueCeth(sifnode.ethbridge.v1.Tx.MsgRescueCeth request) {
      return blockingUnaryCall(
          getChannel(), getRescueCethMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.ethbridge.v1.Tx.MsgSetBlacklistResponse setBlacklist(sifnode.ethbridge.v1.Tx.MsgSetBlacklist request) {
      return blockingUnaryCall(
          getChannel(), getSetBlacklistMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg service for messages
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
    public com.google.common.util.concurrent.ListenableFuture<sifnode.ethbridge.v1.Tx.MsgLockResponse> lock(
        sifnode.ethbridge.v1.Tx.MsgLock request) {
      return futureUnaryCall(
          getChannel().newCall(getLockMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.ethbridge.v1.Tx.MsgBurnResponse> burn(
        sifnode.ethbridge.v1.Tx.MsgBurn request) {
      return futureUnaryCall(
          getChannel().newCall(getBurnMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaimResponse> createEthBridgeClaim(
        sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaim request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateEthBridgeClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidatorResponse> updateWhiteListValidator(
        sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidator request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateWhiteListValidatorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccountResponse> updateCethReceiverAccount(
        sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccount request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateCethReceiverAccountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.ethbridge.v1.Tx.MsgRescueCethResponse> rescueCeth(
        sifnode.ethbridge.v1.Tx.MsgRescueCeth request) {
      return futureUnaryCall(
          getChannel().newCall(getRescueCethMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.ethbridge.v1.Tx.MsgSetBlacklistResponse> setBlacklist(
        sifnode.ethbridge.v1.Tx.MsgSetBlacklist request) {
      return futureUnaryCall(
          getChannel().newCall(getSetBlacklistMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOCK = 0;
  private static final int METHODID_BURN = 1;
  private static final int METHODID_CREATE_ETH_BRIDGE_CLAIM = 2;
  private static final int METHODID_UPDATE_WHITE_LIST_VALIDATOR = 3;
  private static final int METHODID_UPDATE_CETH_RECEIVER_ACCOUNT = 4;
  private static final int METHODID_RESCUE_CETH = 5;
  private static final int METHODID_SET_BLACKLIST = 6;

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
        case METHODID_LOCK:
          serviceImpl.lock((sifnode.ethbridge.v1.Tx.MsgLock) request,
              (io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgLockResponse>) responseObserver);
          break;
        case METHODID_BURN:
          serviceImpl.burn((sifnode.ethbridge.v1.Tx.MsgBurn) request,
              (io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgBurnResponse>) responseObserver);
          break;
        case METHODID_CREATE_ETH_BRIDGE_CLAIM:
          serviceImpl.createEthBridgeClaim((sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaim) request,
              (io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaimResponse>) responseObserver);
          break;
        case METHODID_UPDATE_WHITE_LIST_VALIDATOR:
          serviceImpl.updateWhiteListValidator((sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidator) request,
              (io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgUpdateWhiteListValidatorResponse>) responseObserver);
          break;
        case METHODID_UPDATE_CETH_RECEIVER_ACCOUNT:
          serviceImpl.updateCethReceiverAccount((sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccount) request,
              (io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgUpdateCethReceiverAccountResponse>) responseObserver);
          break;
        case METHODID_RESCUE_CETH:
          serviceImpl.rescueCeth((sifnode.ethbridge.v1.Tx.MsgRescueCeth) request,
              (io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgRescueCethResponse>) responseObserver);
          break;
        case METHODID_SET_BLACKLIST:
          serviceImpl.setBlacklist((sifnode.ethbridge.v1.Tx.MsgSetBlacklist) request,
              (io.grpc.stub.StreamObserver<sifnode.ethbridge.v1.Tx.MsgSetBlacklistResponse>) responseObserver);
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
      return sifnode.ethbridge.v1.Tx.getDescriptor();
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
              .addMethod(getLockMethod())
              .addMethod(getBurnMethod())
              .addMethod(getCreateEthBridgeClaimMethod())
              .addMethod(getUpdateWhiteListValidatorMethod())
              .addMethod(getUpdateCethReceiverAccountMethod())
              .addMethod(getRescueCethMethod())
              .addMethod(getSetBlacklistMethod())
              .build();
        }
      }
    }
    return result;
  }
}
