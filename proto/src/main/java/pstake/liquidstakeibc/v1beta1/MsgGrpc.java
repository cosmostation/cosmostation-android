package pstake.liquidstakeibc.v1beta1;

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
 * Msg defines the liquidstakeibc services.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: pstake/liquidstakeibc/v1beta1/msgs.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "pstake.liquidstakeibc.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChain,
      pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChainResponse> getRegisterHostChainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterHostChain",
      requestType = pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChain.class,
      responseType = pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChain,
      pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChainResponse> getRegisterHostChainMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChain, pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChainResponse> getRegisterHostChainMethod;
    if ((getRegisterHostChainMethod = MsgGrpc.getRegisterHostChainMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRegisterHostChainMethod = MsgGrpc.getRegisterHostChainMethod) == null) {
          MsgGrpc.getRegisterHostChainMethod = getRegisterHostChainMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChain, pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterHostChain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChain.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RegisterHostChain"))
              .build();
        }
      }
    }
    return getRegisterHostChainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChain,
      pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChainResponse> getUpdateHostChainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateHostChain",
      requestType = pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChain.class,
      responseType = pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChain,
      pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChainResponse> getUpdateHostChainMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChain, pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChainResponse> getUpdateHostChainMethod;
    if ((getUpdateHostChainMethod = MsgGrpc.getUpdateHostChainMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateHostChainMethod = MsgGrpc.getUpdateHostChainMethod) == null) {
          MsgGrpc.getUpdateHostChainMethod = getUpdateHostChainMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChain, pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateHostChain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChain.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateHostChain"))
              .build();
        }
      }
    }
    return getUpdateHostChainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStake,
      pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeResponse> getLiquidStakeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiquidStake",
      requestType = pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStake.class,
      responseType = pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStake,
      pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeResponse> getLiquidStakeMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStake, pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeResponse> getLiquidStakeMethod;
    if ((getLiquidStakeMethod = MsgGrpc.getLiquidStakeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLiquidStakeMethod = MsgGrpc.getLiquidStakeMethod) == null) {
          MsgGrpc.getLiquidStakeMethod = getLiquidStakeMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStake, pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LiquidStake"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStake.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("LiquidStake"))
              .build();
        }
      }
    }
    return getLiquidStakeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSM,
      pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSMResponse> getLiquidStakeLSMMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiquidStakeLSM",
      requestType = pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSM.class,
      responseType = pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSMResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSM,
      pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSMResponse> getLiquidStakeLSMMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSM, pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSMResponse> getLiquidStakeLSMMethod;
    if ((getLiquidStakeLSMMethod = MsgGrpc.getLiquidStakeLSMMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLiquidStakeLSMMethod = MsgGrpc.getLiquidStakeLSMMethod) == null) {
          MsgGrpc.getLiquidStakeLSMMethod = getLiquidStakeLSMMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSM, pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSMResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LiquidStakeLSM"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSM.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSMResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("LiquidStakeLSM"))
              .build();
        }
      }
    }
    return getLiquidStakeLSMMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstake,
      pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstakeResponse> getLiquidUnstakeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiquidUnstake",
      requestType = pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstake.class,
      responseType = pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstakeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstake,
      pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstakeResponse> getLiquidUnstakeMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstake, pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstakeResponse> getLiquidUnstakeMethod;
    if ((getLiquidUnstakeMethod = MsgGrpc.getLiquidUnstakeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLiquidUnstakeMethod = MsgGrpc.getLiquidUnstakeMethod) == null) {
          MsgGrpc.getLiquidUnstakeMethod = getLiquidUnstakeMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstake, pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstakeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LiquidUnstake"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstake.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstakeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("LiquidUnstake"))
              .build();
        }
      }
    }
    return getLiquidUnstakeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeem,
      pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeemResponse> getRedeemMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Redeem",
      requestType = pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeem.class,
      responseType = pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeemResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeem,
      pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeemResponse> getRedeemMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeem, pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeemResponse> getRedeemMethod;
    if ((getRedeemMethod = MsgGrpc.getRedeemMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRedeemMethod = MsgGrpc.getRedeemMethod) == null) {
          MsgGrpc.getRedeemMethod = getRedeemMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeem, pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeemResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Redeem"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeem.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeemResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Redeem"))
              .build();
        }
      }
    }
    return getRedeemMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParams,
      pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParamsResponse> getUpdateParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateParams",
      requestType = pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParams.class,
      responseType = pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParams,
      pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParamsResponse> getUpdateParamsMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParams, pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParamsResponse> getUpdateParamsMethod;
    if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
          MsgGrpc.getUpdateParamsMethod = getUpdateParamsMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParams, pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParamsResponse.getDefaultInstance()))
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
   * Msg defines the liquidstakeibc services.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     */
    public void registerHostChain(pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChain request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterHostChainMethod(), responseObserver);
    }

    /**
     */
    public void updateHostChain(pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChain request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateHostChainMethod(), responseObserver);
    }

    /**
     */
    public void liquidStake(pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStake request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLiquidStakeMethod(), responseObserver);
    }

    /**
     */
    public void liquidStakeLSM(pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSM request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSMResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLiquidStakeLSMMethod(), responseObserver);
    }

    /**
     */
    public void liquidUnstake(pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstake request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstakeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLiquidUnstakeMethod(), responseObserver);
    }

    /**
     */
    public void redeem(pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeem request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeemResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRedeemMethod(), responseObserver);
    }

    /**
     */
    public void updateParams(pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterHostChainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChain,
                pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChainResponse>(
                  this, METHODID_REGISTER_HOST_CHAIN)))
          .addMethod(
            getUpdateHostChainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChain,
                pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChainResponse>(
                  this, METHODID_UPDATE_HOST_CHAIN)))
          .addMethod(
            getLiquidStakeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStake,
                pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeResponse>(
                  this, METHODID_LIQUID_STAKE)))
          .addMethod(
            getLiquidStakeLSMMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSM,
                pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSMResponse>(
                  this, METHODID_LIQUID_STAKE_LSM)))
          .addMethod(
            getLiquidUnstakeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstake,
                pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstakeResponse>(
                  this, METHODID_LIQUID_UNSTAKE)))
          .addMethod(
            getRedeemMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeem,
                pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeemResponse>(
                  this, METHODID_REDEEM)))
          .addMethod(
            getUpdateParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParams,
                pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParamsResponse>(
                  this, METHODID_UPDATE_PARAMS)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the liquidstakeibc services.
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
    public void registerHostChain(pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChain request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterHostChainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateHostChain(pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChain request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateHostChainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void liquidStake(pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStake request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLiquidStakeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void liquidStakeLSM(pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSM request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSMResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLiquidStakeLSMMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void liquidUnstake(pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstake request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstakeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLiquidUnstakeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void redeem(pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeem request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeemResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRedeemMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateParams(pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the liquidstakeibc services.
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
    public pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChainResponse registerHostChain(pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChain request) {
      return blockingUnaryCall(
          getChannel(), getRegisterHostChainMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChainResponse updateHostChain(pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChain request) {
      return blockingUnaryCall(
          getChannel(), getUpdateHostChainMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeResponse liquidStake(pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStake request) {
      return blockingUnaryCall(
          getChannel(), getLiquidStakeMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSMResponse liquidStakeLSM(pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSM request) {
      return blockingUnaryCall(
          getChannel(), getLiquidStakeLSMMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstakeResponse liquidUnstake(pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstake request) {
      return blockingUnaryCall(
          getChannel(), getLiquidUnstakeMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeemResponse redeem(pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeem request) {
      return blockingUnaryCall(
          getChannel(), getRedeemMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParamsResponse updateParams(pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParams request) {
      return blockingUnaryCall(
          getChannel(), getUpdateParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the liquidstakeibc services.
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
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChainResponse> registerHostChain(
        pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChain request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterHostChainMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChainResponse> updateHostChain(
        pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChain request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateHostChainMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeResponse> liquidStake(
        pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStake request) {
      return futureUnaryCall(
          getChannel().newCall(getLiquidStakeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSMResponse> liquidStakeLSM(
        pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSM request) {
      return futureUnaryCall(
          getChannel().newCall(getLiquidStakeLSMMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstakeResponse> liquidUnstake(
        pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstake request) {
      return futureUnaryCall(
          getChannel().newCall(getLiquidUnstakeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeemResponse> redeem(
        pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeem request) {
      return futureUnaryCall(
          getChannel().newCall(getRedeemMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParamsResponse> updateParams(
        pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParams request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_HOST_CHAIN = 0;
  private static final int METHODID_UPDATE_HOST_CHAIN = 1;
  private static final int METHODID_LIQUID_STAKE = 2;
  private static final int METHODID_LIQUID_STAKE_LSM = 3;
  private static final int METHODID_LIQUID_UNSTAKE = 4;
  private static final int METHODID_REDEEM = 5;
  private static final int METHODID_UPDATE_PARAMS = 6;

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
        case METHODID_REGISTER_HOST_CHAIN:
          serviceImpl.registerHostChain((pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChain) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgRegisterHostChainResponse>) responseObserver);
          break;
        case METHODID_UPDATE_HOST_CHAIN:
          serviceImpl.updateHostChain((pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChain) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateHostChainResponse>) responseObserver);
          break;
        case METHODID_LIQUID_STAKE:
          serviceImpl.liquidStake((pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStake) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeResponse>) responseObserver);
          break;
        case METHODID_LIQUID_STAKE_LSM:
          serviceImpl.liquidStakeLSM((pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSM) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidStakeLSMResponse>) responseObserver);
          break;
        case METHODID_LIQUID_UNSTAKE:
          serviceImpl.liquidUnstake((pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstake) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgLiquidUnstakeResponse>) responseObserver);
          break;
        case METHODID_REDEEM:
          serviceImpl.redeem((pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeem) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgRedeemResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PARAMS:
          serviceImpl.updateParams((pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParams) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.Msgs.MsgUpdateParamsResponse>) responseObserver);
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
      return pstake.liquidstakeibc.v1beta1.Msgs.getDescriptor();
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
              .addMethod(getRegisterHostChainMethod())
              .addMethod(getUpdateHostChainMethod())
              .addMethod(getLiquidStakeMethod())
              .addMethod(getLiquidStakeLSMMethod())
              .addMethod(getLiquidUnstakeMethod())
              .addMethod(getRedeemMethod())
              .addMethod(getUpdateParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
