package kava.incentive.v1beta1;

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
 * Msg defines the incentive Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/incentive/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.incentive.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingReward,
      kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingRewardResponse> getClaimUSDXMintingRewardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimUSDXMintingReward",
      requestType = kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingReward.class,
      responseType = kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingRewardResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingReward,
      kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingRewardResponse> getClaimUSDXMintingRewardMethod() {
    io.grpc.MethodDescriptor<kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingReward, kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingRewardResponse> getClaimUSDXMintingRewardMethod;
    if ((getClaimUSDXMintingRewardMethod = MsgGrpc.getClaimUSDXMintingRewardMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClaimUSDXMintingRewardMethod = MsgGrpc.getClaimUSDXMintingRewardMethod) == null) {
          MsgGrpc.getClaimUSDXMintingRewardMethod = getClaimUSDXMintingRewardMethod =
              io.grpc.MethodDescriptor.<kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingReward, kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingRewardResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimUSDXMintingReward"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingReward.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingRewardResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ClaimUSDXMintingReward"))
              .build();
        }
      }
    }
    return getClaimUSDXMintingRewardMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.incentive.v1beta1.Tx.MsgClaimHardReward,
      kava.incentive.v1beta1.Tx.MsgClaimHardRewardResponse> getClaimHardRewardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimHardReward",
      requestType = kava.incentive.v1beta1.Tx.MsgClaimHardReward.class,
      responseType = kava.incentive.v1beta1.Tx.MsgClaimHardRewardResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.incentive.v1beta1.Tx.MsgClaimHardReward,
      kava.incentive.v1beta1.Tx.MsgClaimHardRewardResponse> getClaimHardRewardMethod() {
    io.grpc.MethodDescriptor<kava.incentive.v1beta1.Tx.MsgClaimHardReward, kava.incentive.v1beta1.Tx.MsgClaimHardRewardResponse> getClaimHardRewardMethod;
    if ((getClaimHardRewardMethod = MsgGrpc.getClaimHardRewardMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClaimHardRewardMethod = MsgGrpc.getClaimHardRewardMethod) == null) {
          MsgGrpc.getClaimHardRewardMethod = getClaimHardRewardMethod =
              io.grpc.MethodDescriptor.<kava.incentive.v1beta1.Tx.MsgClaimHardReward, kava.incentive.v1beta1.Tx.MsgClaimHardRewardResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimHardReward"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.incentive.v1beta1.Tx.MsgClaimHardReward.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.incentive.v1beta1.Tx.MsgClaimHardRewardResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ClaimHardReward"))
              .build();
        }
      }
    }
    return getClaimHardRewardMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.incentive.v1beta1.Tx.MsgClaimDelegatorReward,
      kava.incentive.v1beta1.Tx.MsgClaimDelegatorRewardResponse> getClaimDelegatorRewardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimDelegatorReward",
      requestType = kava.incentive.v1beta1.Tx.MsgClaimDelegatorReward.class,
      responseType = kava.incentive.v1beta1.Tx.MsgClaimDelegatorRewardResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.incentive.v1beta1.Tx.MsgClaimDelegatorReward,
      kava.incentive.v1beta1.Tx.MsgClaimDelegatorRewardResponse> getClaimDelegatorRewardMethod() {
    io.grpc.MethodDescriptor<kava.incentive.v1beta1.Tx.MsgClaimDelegatorReward, kava.incentive.v1beta1.Tx.MsgClaimDelegatorRewardResponse> getClaimDelegatorRewardMethod;
    if ((getClaimDelegatorRewardMethod = MsgGrpc.getClaimDelegatorRewardMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClaimDelegatorRewardMethod = MsgGrpc.getClaimDelegatorRewardMethod) == null) {
          MsgGrpc.getClaimDelegatorRewardMethod = getClaimDelegatorRewardMethod =
              io.grpc.MethodDescriptor.<kava.incentive.v1beta1.Tx.MsgClaimDelegatorReward, kava.incentive.v1beta1.Tx.MsgClaimDelegatorRewardResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimDelegatorReward"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.incentive.v1beta1.Tx.MsgClaimDelegatorReward.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.incentive.v1beta1.Tx.MsgClaimDelegatorRewardResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ClaimDelegatorReward"))
              .build();
        }
      }
    }
    return getClaimDelegatorRewardMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.incentive.v1beta1.Tx.MsgClaimSwapReward,
      kava.incentive.v1beta1.Tx.MsgClaimSwapRewardResponse> getClaimSwapRewardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimSwapReward",
      requestType = kava.incentive.v1beta1.Tx.MsgClaimSwapReward.class,
      responseType = kava.incentive.v1beta1.Tx.MsgClaimSwapRewardResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.incentive.v1beta1.Tx.MsgClaimSwapReward,
      kava.incentive.v1beta1.Tx.MsgClaimSwapRewardResponse> getClaimSwapRewardMethod() {
    io.grpc.MethodDescriptor<kava.incentive.v1beta1.Tx.MsgClaimSwapReward, kava.incentive.v1beta1.Tx.MsgClaimSwapRewardResponse> getClaimSwapRewardMethod;
    if ((getClaimSwapRewardMethod = MsgGrpc.getClaimSwapRewardMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClaimSwapRewardMethod = MsgGrpc.getClaimSwapRewardMethod) == null) {
          MsgGrpc.getClaimSwapRewardMethod = getClaimSwapRewardMethod =
              io.grpc.MethodDescriptor.<kava.incentive.v1beta1.Tx.MsgClaimSwapReward, kava.incentive.v1beta1.Tx.MsgClaimSwapRewardResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimSwapReward"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.incentive.v1beta1.Tx.MsgClaimSwapReward.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.incentive.v1beta1.Tx.MsgClaimSwapRewardResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ClaimSwapReward"))
              .build();
        }
      }
    }
    return getClaimSwapRewardMethod;
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
   * Msg defines the incentive Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * ClaimUSDXMintingReward is a message type used to claim USDX minting rewards
     * </pre>
     */
    public void claimUSDXMintingReward(kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingReward request,
        io.grpc.stub.StreamObserver<kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingRewardResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClaimUSDXMintingRewardMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClaimHardReward is a message type used to claim Hard liquidity provider rewards
     * </pre>
     */
    public void claimHardReward(kava.incentive.v1beta1.Tx.MsgClaimHardReward request,
        io.grpc.stub.StreamObserver<kava.incentive.v1beta1.Tx.MsgClaimHardRewardResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClaimHardRewardMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClaimDelegatorReward is a message type used to claim delegator rewards
     * </pre>
     */
    public void claimDelegatorReward(kava.incentive.v1beta1.Tx.MsgClaimDelegatorReward request,
        io.grpc.stub.StreamObserver<kava.incentive.v1beta1.Tx.MsgClaimDelegatorRewardResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClaimDelegatorRewardMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClaimSwapReward is a message type used to claim delegator rewards
     * </pre>
     */
    public void claimSwapReward(kava.incentive.v1beta1.Tx.MsgClaimSwapReward request,
        io.grpc.stub.StreamObserver<kava.incentive.v1beta1.Tx.MsgClaimSwapRewardResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClaimSwapRewardMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getClaimUSDXMintingRewardMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingReward,
                kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingRewardResponse>(
                  this, METHODID_CLAIM_USDXMINTING_REWARD)))
          .addMethod(
            getClaimHardRewardMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.incentive.v1beta1.Tx.MsgClaimHardReward,
                kava.incentive.v1beta1.Tx.MsgClaimHardRewardResponse>(
                  this, METHODID_CLAIM_HARD_REWARD)))
          .addMethod(
            getClaimDelegatorRewardMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.incentive.v1beta1.Tx.MsgClaimDelegatorReward,
                kava.incentive.v1beta1.Tx.MsgClaimDelegatorRewardResponse>(
                  this, METHODID_CLAIM_DELEGATOR_REWARD)))
          .addMethod(
            getClaimSwapRewardMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.incentive.v1beta1.Tx.MsgClaimSwapReward,
                kava.incentive.v1beta1.Tx.MsgClaimSwapRewardResponse>(
                  this, METHODID_CLAIM_SWAP_REWARD)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the incentive Msg service.
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
     * ClaimUSDXMintingReward is a message type used to claim USDX minting rewards
     * </pre>
     */
    public void claimUSDXMintingReward(kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingReward request,
        io.grpc.stub.StreamObserver<kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingRewardResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClaimUSDXMintingRewardMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClaimHardReward is a message type used to claim Hard liquidity provider rewards
     * </pre>
     */
    public void claimHardReward(kava.incentive.v1beta1.Tx.MsgClaimHardReward request,
        io.grpc.stub.StreamObserver<kava.incentive.v1beta1.Tx.MsgClaimHardRewardResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClaimHardRewardMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClaimDelegatorReward is a message type used to claim delegator rewards
     * </pre>
     */
    public void claimDelegatorReward(kava.incentive.v1beta1.Tx.MsgClaimDelegatorReward request,
        io.grpc.stub.StreamObserver<kava.incentive.v1beta1.Tx.MsgClaimDelegatorRewardResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClaimDelegatorRewardMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClaimSwapReward is a message type used to claim delegator rewards
     * </pre>
     */
    public void claimSwapReward(kava.incentive.v1beta1.Tx.MsgClaimSwapReward request,
        io.grpc.stub.StreamObserver<kava.incentive.v1beta1.Tx.MsgClaimSwapRewardResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClaimSwapRewardMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the incentive Msg service.
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
     * ClaimUSDXMintingReward is a message type used to claim USDX minting rewards
     * </pre>
     */
    public kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingRewardResponse claimUSDXMintingReward(kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingReward request) {
      return blockingUnaryCall(
          getChannel(), getClaimUSDXMintingRewardMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClaimHardReward is a message type used to claim Hard liquidity provider rewards
     * </pre>
     */
    public kava.incentive.v1beta1.Tx.MsgClaimHardRewardResponse claimHardReward(kava.incentive.v1beta1.Tx.MsgClaimHardReward request) {
      return blockingUnaryCall(
          getChannel(), getClaimHardRewardMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClaimDelegatorReward is a message type used to claim delegator rewards
     * </pre>
     */
    public kava.incentive.v1beta1.Tx.MsgClaimDelegatorRewardResponse claimDelegatorReward(kava.incentive.v1beta1.Tx.MsgClaimDelegatorReward request) {
      return blockingUnaryCall(
          getChannel(), getClaimDelegatorRewardMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClaimSwapReward is a message type used to claim delegator rewards
     * </pre>
     */
    public kava.incentive.v1beta1.Tx.MsgClaimSwapRewardResponse claimSwapReward(kava.incentive.v1beta1.Tx.MsgClaimSwapReward request) {
      return blockingUnaryCall(
          getChannel(), getClaimSwapRewardMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the incentive Msg service.
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
     * ClaimUSDXMintingReward is a message type used to claim USDX minting rewards
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingRewardResponse> claimUSDXMintingReward(
        kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingReward request) {
      return futureUnaryCall(
          getChannel().newCall(getClaimUSDXMintingRewardMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClaimHardReward is a message type used to claim Hard liquidity provider rewards
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.incentive.v1beta1.Tx.MsgClaimHardRewardResponse> claimHardReward(
        kava.incentive.v1beta1.Tx.MsgClaimHardReward request) {
      return futureUnaryCall(
          getChannel().newCall(getClaimHardRewardMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClaimDelegatorReward is a message type used to claim delegator rewards
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.incentive.v1beta1.Tx.MsgClaimDelegatorRewardResponse> claimDelegatorReward(
        kava.incentive.v1beta1.Tx.MsgClaimDelegatorReward request) {
      return futureUnaryCall(
          getChannel().newCall(getClaimDelegatorRewardMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClaimSwapReward is a message type used to claim delegator rewards
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.incentive.v1beta1.Tx.MsgClaimSwapRewardResponse> claimSwapReward(
        kava.incentive.v1beta1.Tx.MsgClaimSwapReward request) {
      return futureUnaryCall(
          getChannel().newCall(getClaimSwapRewardMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CLAIM_USDXMINTING_REWARD = 0;
  private static final int METHODID_CLAIM_HARD_REWARD = 1;
  private static final int METHODID_CLAIM_DELEGATOR_REWARD = 2;
  private static final int METHODID_CLAIM_SWAP_REWARD = 3;

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
        case METHODID_CLAIM_USDXMINTING_REWARD:
          serviceImpl.claimUSDXMintingReward((kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingReward) request,
              (io.grpc.stub.StreamObserver<kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingRewardResponse>) responseObserver);
          break;
        case METHODID_CLAIM_HARD_REWARD:
          serviceImpl.claimHardReward((kava.incentive.v1beta1.Tx.MsgClaimHardReward) request,
              (io.grpc.stub.StreamObserver<kava.incentive.v1beta1.Tx.MsgClaimHardRewardResponse>) responseObserver);
          break;
        case METHODID_CLAIM_DELEGATOR_REWARD:
          serviceImpl.claimDelegatorReward((kava.incentive.v1beta1.Tx.MsgClaimDelegatorReward) request,
              (io.grpc.stub.StreamObserver<kava.incentive.v1beta1.Tx.MsgClaimDelegatorRewardResponse>) responseObserver);
          break;
        case METHODID_CLAIM_SWAP_REWARD:
          serviceImpl.claimSwapReward((kava.incentive.v1beta1.Tx.MsgClaimSwapReward) request,
              (io.grpc.stub.StreamObserver<kava.incentive.v1beta1.Tx.MsgClaimSwapRewardResponse>) responseObserver);
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
      return kava.incentive.v1beta1.Tx.getDescriptor();
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
              .addMethod(getClaimUSDXMintingRewardMethod())
              .addMethod(getClaimHardRewardMethod())
              .addMethod(getClaimDelegatorRewardMethod())
              .addMethod(getClaimSwapRewardMethod())
              .build();
        }
      }
    }
    return result;
  }
}
