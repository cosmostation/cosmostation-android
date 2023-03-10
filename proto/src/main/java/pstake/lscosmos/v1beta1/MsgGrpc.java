package pstake.lscosmos.v1beta1;

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
 *Msg defines the lsCosmos services.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: pstake/lscosmos/v1beta1/msgs.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "pstake.lscosmos.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgLiquidStake,
      pstake.lscosmos.v1beta1.Msgs.MsgLiquidStakeResponse> getLiquidStakeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiquidStake",
      requestType = pstake.lscosmos.v1beta1.Msgs.MsgLiquidStake.class,
      responseType = pstake.lscosmos.v1beta1.Msgs.MsgLiquidStakeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgLiquidStake,
      pstake.lscosmos.v1beta1.Msgs.MsgLiquidStakeResponse> getLiquidStakeMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgLiquidStake, pstake.lscosmos.v1beta1.Msgs.MsgLiquidStakeResponse> getLiquidStakeMethod;
    if ((getLiquidStakeMethod = MsgGrpc.getLiquidStakeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLiquidStakeMethod = MsgGrpc.getLiquidStakeMethod) == null) {
          MsgGrpc.getLiquidStakeMethod = getLiquidStakeMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.Msgs.MsgLiquidStake, pstake.lscosmos.v1beta1.Msgs.MsgLiquidStakeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LiquidStake"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.Msgs.MsgLiquidStake.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.Msgs.MsgLiquidStakeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("LiquidStake"))
              .build();
        }
      }
    }
    return getLiquidStakeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstake,
      pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstakeResponse> getLiquidUnstakeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiquidUnstake",
      requestType = pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstake.class,
      responseType = pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstakeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstake,
      pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstakeResponse> getLiquidUnstakeMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstake, pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstakeResponse> getLiquidUnstakeMethod;
    if ((getLiquidUnstakeMethod = MsgGrpc.getLiquidUnstakeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLiquidUnstakeMethod = MsgGrpc.getLiquidUnstakeMethod) == null) {
          MsgGrpc.getLiquidUnstakeMethod = getLiquidUnstakeMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstake, pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstakeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LiquidUnstake"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstake.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstakeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("LiquidUnstake"))
              .build();
        }
      }
    }
    return getLiquidUnstakeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgRedeem,
      pstake.lscosmos.v1beta1.Msgs.MsgRedeemResponse> getRedeemMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Redeem",
      requestType = pstake.lscosmos.v1beta1.Msgs.MsgRedeem.class,
      responseType = pstake.lscosmos.v1beta1.Msgs.MsgRedeemResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgRedeem,
      pstake.lscosmos.v1beta1.Msgs.MsgRedeemResponse> getRedeemMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgRedeem, pstake.lscosmos.v1beta1.Msgs.MsgRedeemResponse> getRedeemMethod;
    if ((getRedeemMethod = MsgGrpc.getRedeemMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRedeemMethod = MsgGrpc.getRedeemMethod) == null) {
          MsgGrpc.getRedeemMethod = getRedeemMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.Msgs.MsgRedeem, pstake.lscosmos.v1beta1.Msgs.MsgRedeemResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Redeem"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.Msgs.MsgRedeem.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.Msgs.MsgRedeemResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Redeem"))
              .build();
        }
      }
    }
    return getRedeemMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgClaim,
      pstake.lscosmos.v1beta1.Msgs.MsgClaimResponse> getClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Claim",
      requestType = pstake.lscosmos.v1beta1.Msgs.MsgClaim.class,
      responseType = pstake.lscosmos.v1beta1.Msgs.MsgClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgClaim,
      pstake.lscosmos.v1beta1.Msgs.MsgClaimResponse> getClaimMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgClaim, pstake.lscosmos.v1beta1.Msgs.MsgClaimResponse> getClaimMethod;
    if ((getClaimMethod = MsgGrpc.getClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClaimMethod = MsgGrpc.getClaimMethod) == null) {
          MsgGrpc.getClaimMethod = getClaimMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.Msgs.MsgClaim, pstake.lscosmos.v1beta1.Msgs.MsgClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Claim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.Msgs.MsgClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.Msgs.MsgClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Claim"))
              .build();
        }
      }
    }
    return getClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgRecreateICA,
      pstake.lscosmos.v1beta1.Msgs.MsgRecreateICAResponse> getRecreateICAMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RecreateICA",
      requestType = pstake.lscosmos.v1beta1.Msgs.MsgRecreateICA.class,
      responseType = pstake.lscosmos.v1beta1.Msgs.MsgRecreateICAResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgRecreateICA,
      pstake.lscosmos.v1beta1.Msgs.MsgRecreateICAResponse> getRecreateICAMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgRecreateICA, pstake.lscosmos.v1beta1.Msgs.MsgRecreateICAResponse> getRecreateICAMethod;
    if ((getRecreateICAMethod = MsgGrpc.getRecreateICAMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRecreateICAMethod = MsgGrpc.getRecreateICAMethod) == null) {
          MsgGrpc.getRecreateICAMethod = getRecreateICAMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.Msgs.MsgRecreateICA, pstake.lscosmos.v1beta1.Msgs.MsgRecreateICAResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RecreateICA"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.Msgs.MsgRecreateICA.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.Msgs.MsgRecreateICAResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RecreateICA"))
              .build();
        }
      }
    }
    return getRecreateICAMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgJumpStart,
      pstake.lscosmos.v1beta1.Msgs.MsgJumpStartResponse> getJumpStartMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "JumpStart",
      requestType = pstake.lscosmos.v1beta1.Msgs.MsgJumpStart.class,
      responseType = pstake.lscosmos.v1beta1.Msgs.MsgJumpStartResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgJumpStart,
      pstake.lscosmos.v1beta1.Msgs.MsgJumpStartResponse> getJumpStartMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgJumpStart, pstake.lscosmos.v1beta1.Msgs.MsgJumpStartResponse> getJumpStartMethod;
    if ((getJumpStartMethod = MsgGrpc.getJumpStartMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getJumpStartMethod = MsgGrpc.getJumpStartMethod) == null) {
          MsgGrpc.getJumpStartMethod = getJumpStartMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.Msgs.MsgJumpStart, pstake.lscosmos.v1beta1.Msgs.MsgJumpStartResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "JumpStart"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.Msgs.MsgJumpStart.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.Msgs.MsgJumpStartResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("JumpStart"))
              .build();
        }
      }
    }
    return getJumpStartMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleState,
      pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleStateResponse> getChangeModuleStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChangeModuleState",
      requestType = pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleState.class,
      responseType = pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleState,
      pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleStateResponse> getChangeModuleStateMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleState, pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleStateResponse> getChangeModuleStateMethod;
    if ((getChangeModuleStateMethod = MsgGrpc.getChangeModuleStateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getChangeModuleStateMethod = MsgGrpc.getChangeModuleStateMethod) == null) {
          MsgGrpc.getChangeModuleStateMethod = getChangeModuleStateMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleState, pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ChangeModuleState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleState.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ChangeModuleState"))
              .build();
        }
      }
    }
    return getChangeModuleStateMethod;
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
   *Msg defines the lsCosmos services.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     */
    public void liquidStake(pstake.lscosmos.v1beta1.Msgs.MsgLiquidStake request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgLiquidStakeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLiquidStakeMethod(), responseObserver);
    }

    /**
     */
    public void liquidUnstake(pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstake request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstakeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLiquidUnstakeMethod(), responseObserver);
    }

    /**
     */
    public void redeem(pstake.lscosmos.v1beta1.Msgs.MsgRedeem request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgRedeemResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRedeemMethod(), responseObserver);
    }

    /**
     */
    public void claim(pstake.lscosmos.v1beta1.Msgs.MsgClaim request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgClaimResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClaimMethod(), responseObserver);
    }

    /**
     */
    public void recreateICA(pstake.lscosmos.v1beta1.Msgs.MsgRecreateICA request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgRecreateICAResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRecreateICAMethod(), responseObserver);
    }

    /**
     */
    public void jumpStart(pstake.lscosmos.v1beta1.Msgs.MsgJumpStart request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgJumpStartResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getJumpStartMethod(), responseObserver);
    }

    /**
     */
    public void changeModuleState(pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleState request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getChangeModuleStateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLiquidStakeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.Msgs.MsgLiquidStake,
                pstake.lscosmos.v1beta1.Msgs.MsgLiquidStakeResponse>(
                  this, METHODID_LIQUID_STAKE)))
          .addMethod(
            getLiquidUnstakeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstake,
                pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstakeResponse>(
                  this, METHODID_LIQUID_UNSTAKE)))
          .addMethod(
            getRedeemMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.Msgs.MsgRedeem,
                pstake.lscosmos.v1beta1.Msgs.MsgRedeemResponse>(
                  this, METHODID_REDEEM)))
          .addMethod(
            getClaimMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.Msgs.MsgClaim,
                pstake.lscosmos.v1beta1.Msgs.MsgClaimResponse>(
                  this, METHODID_CLAIM)))
          .addMethod(
            getRecreateICAMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.Msgs.MsgRecreateICA,
                pstake.lscosmos.v1beta1.Msgs.MsgRecreateICAResponse>(
                  this, METHODID_RECREATE_ICA)))
          .addMethod(
            getJumpStartMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.Msgs.MsgJumpStart,
                pstake.lscosmos.v1beta1.Msgs.MsgJumpStartResponse>(
                  this, METHODID_JUMP_START)))
          .addMethod(
            getChangeModuleStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleState,
                pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleStateResponse>(
                  this, METHODID_CHANGE_MODULE_STATE)))
          .build();
    }
  }

  /**
   * <pre>
   *Msg defines the lsCosmos services.
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
    public void liquidStake(pstake.lscosmos.v1beta1.Msgs.MsgLiquidStake request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgLiquidStakeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLiquidStakeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void liquidUnstake(pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstake request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstakeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLiquidUnstakeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void redeem(pstake.lscosmos.v1beta1.Msgs.MsgRedeem request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgRedeemResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRedeemMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void claim(pstake.lscosmos.v1beta1.Msgs.MsgClaim request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgClaimResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void recreateICA(pstake.lscosmos.v1beta1.Msgs.MsgRecreateICA request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgRecreateICAResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRecreateICAMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void jumpStart(pstake.lscosmos.v1beta1.Msgs.MsgJumpStart request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgJumpStartResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getJumpStartMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void changeModuleState(pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleState request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChangeModuleStateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *Msg defines the lsCosmos services.
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
    public pstake.lscosmos.v1beta1.Msgs.MsgLiquidStakeResponse liquidStake(pstake.lscosmos.v1beta1.Msgs.MsgLiquidStake request) {
      return blockingUnaryCall(
          getChannel(), getLiquidStakeMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstakeResponse liquidUnstake(pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstake request) {
      return blockingUnaryCall(
          getChannel(), getLiquidUnstakeMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.Msgs.MsgRedeemResponse redeem(pstake.lscosmos.v1beta1.Msgs.MsgRedeem request) {
      return blockingUnaryCall(
          getChannel(), getRedeemMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.Msgs.MsgClaimResponse claim(pstake.lscosmos.v1beta1.Msgs.MsgClaim request) {
      return blockingUnaryCall(
          getChannel(), getClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.Msgs.MsgRecreateICAResponse recreateICA(pstake.lscosmos.v1beta1.Msgs.MsgRecreateICA request) {
      return blockingUnaryCall(
          getChannel(), getRecreateICAMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.Msgs.MsgJumpStartResponse jumpStart(pstake.lscosmos.v1beta1.Msgs.MsgJumpStart request) {
      return blockingUnaryCall(
          getChannel(), getJumpStartMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleStateResponse changeModuleState(pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleState request) {
      return blockingUnaryCall(
          getChannel(), getChangeModuleStateMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *Msg defines the lsCosmos services.
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
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.Msgs.MsgLiquidStakeResponse> liquidStake(
        pstake.lscosmos.v1beta1.Msgs.MsgLiquidStake request) {
      return futureUnaryCall(
          getChannel().newCall(getLiquidStakeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstakeResponse> liquidUnstake(
        pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstake request) {
      return futureUnaryCall(
          getChannel().newCall(getLiquidUnstakeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.Msgs.MsgRedeemResponse> redeem(
        pstake.lscosmos.v1beta1.Msgs.MsgRedeem request) {
      return futureUnaryCall(
          getChannel().newCall(getRedeemMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.Msgs.MsgClaimResponse> claim(
        pstake.lscosmos.v1beta1.Msgs.MsgClaim request) {
      return futureUnaryCall(
          getChannel().newCall(getClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.Msgs.MsgRecreateICAResponse> recreateICA(
        pstake.lscosmos.v1beta1.Msgs.MsgRecreateICA request) {
      return futureUnaryCall(
          getChannel().newCall(getRecreateICAMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.Msgs.MsgJumpStartResponse> jumpStart(
        pstake.lscosmos.v1beta1.Msgs.MsgJumpStart request) {
      return futureUnaryCall(
          getChannel().newCall(getJumpStartMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleStateResponse> changeModuleState(
        pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleState request) {
      return futureUnaryCall(
          getChannel().newCall(getChangeModuleStateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIQUID_STAKE = 0;
  private static final int METHODID_LIQUID_UNSTAKE = 1;
  private static final int METHODID_REDEEM = 2;
  private static final int METHODID_CLAIM = 3;
  private static final int METHODID_RECREATE_ICA = 4;
  private static final int METHODID_JUMP_START = 5;
  private static final int METHODID_CHANGE_MODULE_STATE = 6;

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
        case METHODID_LIQUID_STAKE:
          serviceImpl.liquidStake((pstake.lscosmos.v1beta1.Msgs.MsgLiquidStake) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgLiquidStakeResponse>) responseObserver);
          break;
        case METHODID_LIQUID_UNSTAKE:
          serviceImpl.liquidUnstake((pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstake) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgLiquidUnstakeResponse>) responseObserver);
          break;
        case METHODID_REDEEM:
          serviceImpl.redeem((pstake.lscosmos.v1beta1.Msgs.MsgRedeem) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgRedeemResponse>) responseObserver);
          break;
        case METHODID_CLAIM:
          serviceImpl.claim((pstake.lscosmos.v1beta1.Msgs.MsgClaim) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgClaimResponse>) responseObserver);
          break;
        case METHODID_RECREATE_ICA:
          serviceImpl.recreateICA((pstake.lscosmos.v1beta1.Msgs.MsgRecreateICA) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgRecreateICAResponse>) responseObserver);
          break;
        case METHODID_JUMP_START:
          serviceImpl.jumpStart((pstake.lscosmos.v1beta1.Msgs.MsgJumpStart) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgJumpStartResponse>) responseObserver);
          break;
        case METHODID_CHANGE_MODULE_STATE:
          serviceImpl.changeModuleState((pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleState) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.Msgs.MsgChangeModuleStateResponse>) responseObserver);
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
      return pstake.lscosmos.v1beta1.Msgs.getDescriptor();
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
              .addMethod(getLiquidStakeMethod())
              .addMethod(getLiquidUnstakeMethod())
              .addMethod(getRedeemMethod())
              .addMethod(getClaimMethod())
              .addMethod(getRecreateICAMethod())
              .addMethod(getJumpStartMethod())
              .addMethod(getChangeModuleStateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
