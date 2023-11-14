package com.kava.incentive.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the incentive Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: kava/incentive/v1beta1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.incentive.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingReward,
      com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingRewardResponse> getClaimUSDXMintingRewardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimUSDXMintingReward",
      requestType = com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingReward.class,
      responseType = com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingRewardResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingReward,
      com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingRewardResponse> getClaimUSDXMintingRewardMethod() {
    io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingReward, com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingRewardResponse> getClaimUSDXMintingRewardMethod;
    if ((getClaimUSDXMintingRewardMethod = MsgGrpc.getClaimUSDXMintingRewardMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClaimUSDXMintingRewardMethod = MsgGrpc.getClaimUSDXMintingRewardMethod) == null) {
          MsgGrpc.getClaimUSDXMintingRewardMethod = getClaimUSDXMintingRewardMethod =
              io.grpc.MethodDescriptor.<com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingReward, com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingRewardResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimUSDXMintingReward"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingReward.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingRewardResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ClaimUSDXMintingReward"))
              .build();
        }
      }
    }
    return getClaimUSDXMintingRewardMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimHardReward,
      com.kava.incentive.v1beta1.TxProto.MsgClaimHardRewardResponse> getClaimHardRewardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimHardReward",
      requestType = com.kava.incentive.v1beta1.TxProto.MsgClaimHardReward.class,
      responseType = com.kava.incentive.v1beta1.TxProto.MsgClaimHardRewardResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimHardReward,
      com.kava.incentive.v1beta1.TxProto.MsgClaimHardRewardResponse> getClaimHardRewardMethod() {
    io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimHardReward, com.kava.incentive.v1beta1.TxProto.MsgClaimHardRewardResponse> getClaimHardRewardMethod;
    if ((getClaimHardRewardMethod = MsgGrpc.getClaimHardRewardMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClaimHardRewardMethod = MsgGrpc.getClaimHardRewardMethod) == null) {
          MsgGrpc.getClaimHardRewardMethod = getClaimHardRewardMethod =
              io.grpc.MethodDescriptor.<com.kava.incentive.v1beta1.TxProto.MsgClaimHardReward, com.kava.incentive.v1beta1.TxProto.MsgClaimHardRewardResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimHardReward"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.TxProto.MsgClaimHardReward.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.TxProto.MsgClaimHardRewardResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ClaimHardReward"))
              .build();
        }
      }
    }
    return getClaimHardRewardMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorReward,
      com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorRewardResponse> getClaimDelegatorRewardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimDelegatorReward",
      requestType = com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorReward.class,
      responseType = com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorRewardResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorReward,
      com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorRewardResponse> getClaimDelegatorRewardMethod() {
    io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorReward, com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorRewardResponse> getClaimDelegatorRewardMethod;
    if ((getClaimDelegatorRewardMethod = MsgGrpc.getClaimDelegatorRewardMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClaimDelegatorRewardMethod = MsgGrpc.getClaimDelegatorRewardMethod) == null) {
          MsgGrpc.getClaimDelegatorRewardMethod = getClaimDelegatorRewardMethod =
              io.grpc.MethodDescriptor.<com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorReward, com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorRewardResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimDelegatorReward"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorReward.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorRewardResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ClaimDelegatorReward"))
              .build();
        }
      }
    }
    return getClaimDelegatorRewardMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimSwapReward,
      com.kava.incentive.v1beta1.TxProto.MsgClaimSwapRewardResponse> getClaimSwapRewardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimSwapReward",
      requestType = com.kava.incentive.v1beta1.TxProto.MsgClaimSwapReward.class,
      responseType = com.kava.incentive.v1beta1.TxProto.MsgClaimSwapRewardResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimSwapReward,
      com.kava.incentive.v1beta1.TxProto.MsgClaimSwapRewardResponse> getClaimSwapRewardMethod() {
    io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimSwapReward, com.kava.incentive.v1beta1.TxProto.MsgClaimSwapRewardResponse> getClaimSwapRewardMethod;
    if ((getClaimSwapRewardMethod = MsgGrpc.getClaimSwapRewardMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClaimSwapRewardMethod = MsgGrpc.getClaimSwapRewardMethod) == null) {
          MsgGrpc.getClaimSwapRewardMethod = getClaimSwapRewardMethod =
              io.grpc.MethodDescriptor.<com.kava.incentive.v1beta1.TxProto.MsgClaimSwapReward, com.kava.incentive.v1beta1.TxProto.MsgClaimSwapRewardResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimSwapReward"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.TxProto.MsgClaimSwapReward.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.TxProto.MsgClaimSwapRewardResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ClaimSwapReward"))
              .build();
        }
      }
    }
    return getClaimSwapRewardMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsReward,
      com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsRewardResponse> getClaimSavingsRewardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimSavingsReward",
      requestType = com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsReward.class,
      responseType = com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsRewardResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsReward,
      com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsRewardResponse> getClaimSavingsRewardMethod() {
    io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsReward, com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsRewardResponse> getClaimSavingsRewardMethod;
    if ((getClaimSavingsRewardMethod = MsgGrpc.getClaimSavingsRewardMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClaimSavingsRewardMethod = MsgGrpc.getClaimSavingsRewardMethod) == null) {
          MsgGrpc.getClaimSavingsRewardMethod = getClaimSavingsRewardMethod =
              io.grpc.MethodDescriptor.<com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsReward, com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsRewardResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimSavingsReward"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsReward.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsRewardResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ClaimSavingsReward"))
              .build();
        }
      }
    }
    return getClaimSavingsRewardMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimEarnReward,
      com.kava.incentive.v1beta1.TxProto.MsgClaimEarnRewardResponse> getClaimEarnRewardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimEarnReward",
      requestType = com.kava.incentive.v1beta1.TxProto.MsgClaimEarnReward.class,
      responseType = com.kava.incentive.v1beta1.TxProto.MsgClaimEarnRewardResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimEarnReward,
      com.kava.incentive.v1beta1.TxProto.MsgClaimEarnRewardResponse> getClaimEarnRewardMethod() {
    io.grpc.MethodDescriptor<com.kava.incentive.v1beta1.TxProto.MsgClaimEarnReward, com.kava.incentive.v1beta1.TxProto.MsgClaimEarnRewardResponse> getClaimEarnRewardMethod;
    if ((getClaimEarnRewardMethod = MsgGrpc.getClaimEarnRewardMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClaimEarnRewardMethod = MsgGrpc.getClaimEarnRewardMethod) == null) {
          MsgGrpc.getClaimEarnRewardMethod = getClaimEarnRewardMethod =
              io.grpc.MethodDescriptor.<com.kava.incentive.v1beta1.TxProto.MsgClaimEarnReward, com.kava.incentive.v1beta1.TxProto.MsgClaimEarnRewardResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimEarnReward"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.TxProto.MsgClaimEarnReward.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.incentive.v1beta1.TxProto.MsgClaimEarnRewardResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ClaimEarnReward"))
              .build();
        }
      }
    }
    return getClaimEarnRewardMethod;
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
  public interface AsyncService {

    /**
     * <pre>
     * ClaimUSDXMintingReward is a message type used to claim USDX minting rewards
     * </pre>
     */
    default void claimUSDXMintingReward(com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingReward request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingRewardResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClaimUSDXMintingRewardMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClaimHardReward is a message type used to claim Hard liquidity provider rewards
     * </pre>
     */
    default void claimHardReward(com.kava.incentive.v1beta1.TxProto.MsgClaimHardReward request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimHardRewardResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClaimHardRewardMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClaimDelegatorReward is a message type used to claim delegator rewards
     * </pre>
     */
    default void claimDelegatorReward(com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorReward request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorRewardResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClaimDelegatorRewardMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClaimSwapReward is a message type used to claim swap rewards
     * </pre>
     */
    default void claimSwapReward(com.kava.incentive.v1beta1.TxProto.MsgClaimSwapReward request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimSwapRewardResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClaimSwapRewardMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClaimSavingsReward is a message type used to claim savings rewards
     * </pre>
     */
    default void claimSavingsReward(com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsReward request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsRewardResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClaimSavingsRewardMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClaimEarnReward is a message type used to claim earn rewards
     * </pre>
     */
    default void claimEarnReward(com.kava.incentive.v1beta1.TxProto.MsgClaimEarnReward request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimEarnRewardResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClaimEarnRewardMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the incentive Msg service.
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
   * Msg defines the incentive Msg service.
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
     * ClaimUSDXMintingReward is a message type used to claim USDX minting rewards
     * </pre>
     */
    public void claimUSDXMintingReward(com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingReward request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingRewardResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClaimUSDXMintingRewardMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClaimHardReward is a message type used to claim Hard liquidity provider rewards
     * </pre>
     */
    public void claimHardReward(com.kava.incentive.v1beta1.TxProto.MsgClaimHardReward request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimHardRewardResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClaimHardRewardMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClaimDelegatorReward is a message type used to claim delegator rewards
     * </pre>
     */
    public void claimDelegatorReward(com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorReward request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorRewardResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClaimDelegatorRewardMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClaimSwapReward is a message type used to claim swap rewards
     * </pre>
     */
    public void claimSwapReward(com.kava.incentive.v1beta1.TxProto.MsgClaimSwapReward request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimSwapRewardResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClaimSwapRewardMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClaimSavingsReward is a message type used to claim savings rewards
     * </pre>
     */
    public void claimSavingsReward(com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsReward request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsRewardResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClaimSavingsRewardMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClaimEarnReward is a message type used to claim earn rewards
     * </pre>
     */
    public void claimEarnReward(com.kava.incentive.v1beta1.TxProto.MsgClaimEarnReward request,
        io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimEarnRewardResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClaimEarnRewardMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the incentive Msg service.
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
     * ClaimUSDXMintingReward is a message type used to claim USDX minting rewards
     * </pre>
     */
    public com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingRewardResponse claimUSDXMintingReward(com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingReward request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClaimUSDXMintingRewardMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClaimHardReward is a message type used to claim Hard liquidity provider rewards
     * </pre>
     */
    public com.kava.incentive.v1beta1.TxProto.MsgClaimHardRewardResponse claimHardReward(com.kava.incentive.v1beta1.TxProto.MsgClaimHardReward request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClaimHardRewardMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClaimDelegatorReward is a message type used to claim delegator rewards
     * </pre>
     */
    public com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorRewardResponse claimDelegatorReward(com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorReward request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClaimDelegatorRewardMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClaimSwapReward is a message type used to claim swap rewards
     * </pre>
     */
    public com.kava.incentive.v1beta1.TxProto.MsgClaimSwapRewardResponse claimSwapReward(com.kava.incentive.v1beta1.TxProto.MsgClaimSwapReward request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClaimSwapRewardMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClaimSavingsReward is a message type used to claim savings rewards
     * </pre>
     */
    public com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsRewardResponse claimSavingsReward(com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsReward request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClaimSavingsRewardMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClaimEarnReward is a message type used to claim earn rewards
     * </pre>
     */
    public com.kava.incentive.v1beta1.TxProto.MsgClaimEarnRewardResponse claimEarnReward(com.kava.incentive.v1beta1.TxProto.MsgClaimEarnReward request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClaimEarnRewardMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the incentive Msg service.
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
     * ClaimUSDXMintingReward is a message type used to claim USDX minting rewards
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingRewardResponse> claimUSDXMintingReward(
        com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingReward request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClaimUSDXMintingRewardMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClaimHardReward is a message type used to claim Hard liquidity provider rewards
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.incentive.v1beta1.TxProto.MsgClaimHardRewardResponse> claimHardReward(
        com.kava.incentive.v1beta1.TxProto.MsgClaimHardReward request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClaimHardRewardMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClaimDelegatorReward is a message type used to claim delegator rewards
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorRewardResponse> claimDelegatorReward(
        com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorReward request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClaimDelegatorRewardMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClaimSwapReward is a message type used to claim swap rewards
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.incentive.v1beta1.TxProto.MsgClaimSwapRewardResponse> claimSwapReward(
        com.kava.incentive.v1beta1.TxProto.MsgClaimSwapReward request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClaimSwapRewardMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClaimSavingsReward is a message type used to claim savings rewards
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsRewardResponse> claimSavingsReward(
        com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsReward request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClaimSavingsRewardMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClaimEarnReward is a message type used to claim earn rewards
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.incentive.v1beta1.TxProto.MsgClaimEarnRewardResponse> claimEarnReward(
        com.kava.incentive.v1beta1.TxProto.MsgClaimEarnReward request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClaimEarnRewardMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CLAIM_USDXMINTING_REWARD = 0;
  private static final int METHODID_CLAIM_HARD_REWARD = 1;
  private static final int METHODID_CLAIM_DELEGATOR_REWARD = 2;
  private static final int METHODID_CLAIM_SWAP_REWARD = 3;
  private static final int METHODID_CLAIM_SAVINGS_REWARD = 4;
  private static final int METHODID_CLAIM_EARN_REWARD = 5;

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
        case METHODID_CLAIM_USDXMINTING_REWARD:
          serviceImpl.claimUSDXMintingReward((com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingReward) request,
              (io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingRewardResponse>) responseObserver);
          break;
        case METHODID_CLAIM_HARD_REWARD:
          serviceImpl.claimHardReward((com.kava.incentive.v1beta1.TxProto.MsgClaimHardReward) request,
              (io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimHardRewardResponse>) responseObserver);
          break;
        case METHODID_CLAIM_DELEGATOR_REWARD:
          serviceImpl.claimDelegatorReward((com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorReward) request,
              (io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorRewardResponse>) responseObserver);
          break;
        case METHODID_CLAIM_SWAP_REWARD:
          serviceImpl.claimSwapReward((com.kava.incentive.v1beta1.TxProto.MsgClaimSwapReward) request,
              (io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimSwapRewardResponse>) responseObserver);
          break;
        case METHODID_CLAIM_SAVINGS_REWARD:
          serviceImpl.claimSavingsReward((com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsReward) request,
              (io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsRewardResponse>) responseObserver);
          break;
        case METHODID_CLAIM_EARN_REWARD:
          serviceImpl.claimEarnReward((com.kava.incentive.v1beta1.TxProto.MsgClaimEarnReward) request,
              (io.grpc.stub.StreamObserver<com.kava.incentive.v1beta1.TxProto.MsgClaimEarnRewardResponse>) responseObserver);
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
          getClaimUSDXMintingRewardMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingReward,
              com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingRewardResponse>(
                service, METHODID_CLAIM_USDXMINTING_REWARD)))
        .addMethod(
          getClaimHardRewardMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.incentive.v1beta1.TxProto.MsgClaimHardReward,
              com.kava.incentive.v1beta1.TxProto.MsgClaimHardRewardResponse>(
                service, METHODID_CLAIM_HARD_REWARD)))
        .addMethod(
          getClaimDelegatorRewardMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorReward,
              com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorRewardResponse>(
                service, METHODID_CLAIM_DELEGATOR_REWARD)))
        .addMethod(
          getClaimSwapRewardMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.incentive.v1beta1.TxProto.MsgClaimSwapReward,
              com.kava.incentive.v1beta1.TxProto.MsgClaimSwapRewardResponse>(
                service, METHODID_CLAIM_SWAP_REWARD)))
        .addMethod(
          getClaimSavingsRewardMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsReward,
              com.kava.incentive.v1beta1.TxProto.MsgClaimSavingsRewardResponse>(
                service, METHODID_CLAIM_SAVINGS_REWARD)))
        .addMethod(
          getClaimEarnRewardMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.incentive.v1beta1.TxProto.MsgClaimEarnReward,
              com.kava.incentive.v1beta1.TxProto.MsgClaimEarnRewardResponse>(
                service, METHODID_CLAIM_EARN_REWARD)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kava.incentive.v1beta1.TxProto.getDescriptor();
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
              .addMethod(getClaimSavingsRewardMethod())
              .addMethod(getClaimEarnRewardMethod())
              .build();
        }
      }
    }
    return result;
  }
}
