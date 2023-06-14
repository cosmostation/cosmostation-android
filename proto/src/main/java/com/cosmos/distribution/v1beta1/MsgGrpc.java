package com.cosmos.distribution.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the distribution Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmos/distribution/v1beta1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "cosmos.distribution.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress,
      com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddressResponse> getSetWithdrawAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetWithdrawAddress",
      requestType = com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress.class,
      responseType = com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress,
      com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddressResponse> getSetWithdrawAddressMethod() {
    io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress, com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddressResponse> getSetWithdrawAddressMethod;
    if ((getSetWithdrawAddressMethod = MsgGrpc.getSetWithdrawAddressMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetWithdrawAddressMethod = MsgGrpc.getSetWithdrawAddressMethod) == null) {
          MsgGrpc.getSetWithdrawAddressMethod = getSetWithdrawAddressMethod =
              io.grpc.MethodDescriptor.<com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress, com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetWithdrawAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetWithdrawAddress"))
              .build();
        }
      }
    }
    return getSetWithdrawAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorReward,
      com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorRewardResponse> getWithdrawDelegatorRewardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawDelegatorReward",
      requestType = com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorReward.class,
      responseType = com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorRewardResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorReward,
      com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorRewardResponse> getWithdrawDelegatorRewardMethod() {
    io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorReward, com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorRewardResponse> getWithdrawDelegatorRewardMethod;
    if ((getWithdrawDelegatorRewardMethod = MsgGrpc.getWithdrawDelegatorRewardMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawDelegatorRewardMethod = MsgGrpc.getWithdrawDelegatorRewardMethod) == null) {
          MsgGrpc.getWithdrawDelegatorRewardMethod = getWithdrawDelegatorRewardMethod =
              io.grpc.MethodDescriptor.<com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorReward, com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorRewardResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawDelegatorReward"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorReward.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorRewardResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawDelegatorReward"))
              .build();
        }
      }
    }
    return getWithdrawDelegatorRewardMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommission,
      com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommissionResponse> getWithdrawValidatorCommissionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawValidatorCommission",
      requestType = com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommission.class,
      responseType = com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommissionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommission,
      com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommissionResponse> getWithdrawValidatorCommissionMethod() {
    io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommission, com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommissionResponse> getWithdrawValidatorCommissionMethod;
    if ((getWithdrawValidatorCommissionMethod = MsgGrpc.getWithdrawValidatorCommissionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawValidatorCommissionMethod = MsgGrpc.getWithdrawValidatorCommissionMethod) == null) {
          MsgGrpc.getWithdrawValidatorCommissionMethod = getWithdrawValidatorCommissionMethod =
              io.grpc.MethodDescriptor.<com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommission, com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommissionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawValidatorCommission"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommission.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommissionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawValidatorCommission"))
              .build();
        }
      }
    }
    return getWithdrawValidatorCommissionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPool,
      com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPoolResponse> getFundCommunityPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FundCommunityPool",
      requestType = com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPool.class,
      responseType = com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPool,
      com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPoolResponse> getFundCommunityPoolMethod() {
    io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPool, com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPoolResponse> getFundCommunityPoolMethod;
    if ((getFundCommunityPoolMethod = MsgGrpc.getFundCommunityPoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getFundCommunityPoolMethod = MsgGrpc.getFundCommunityPoolMethod) == null) {
          MsgGrpc.getFundCommunityPoolMethod = getFundCommunityPoolMethod =
              io.grpc.MethodDescriptor.<com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPool, com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FundCommunityPool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("FundCommunityPool"))
              .build();
        }
      }
    }
    return getFundCommunityPoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParams,
      com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateParams",
      requestType = com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParams.class,
      responseType = com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParams,
      com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod() {
    io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParams, com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;
    if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
          MsgGrpc.getUpdateParamsMethod = getUpdateParamsMethod =
              io.grpc.MethodDescriptor.<com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParams, com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateParams"))
              .build();
        }
      }
    }
    return getUpdateParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpend,
      com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpendResponse> getCommunityPoolSpendMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CommunityPoolSpend",
      requestType = com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpend.class,
      responseType = com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpendResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpend,
      com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpendResponse> getCommunityPoolSpendMethod() {
    io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpend, com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpendResponse> getCommunityPoolSpendMethod;
    if ((getCommunityPoolSpendMethod = MsgGrpc.getCommunityPoolSpendMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCommunityPoolSpendMethod = MsgGrpc.getCommunityPoolSpendMethod) == null) {
          MsgGrpc.getCommunityPoolSpendMethod = getCommunityPoolSpendMethod =
              io.grpc.MethodDescriptor.<com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpend, com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpendResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CommunityPoolSpend"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpend.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpendResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CommunityPoolSpend"))
              .build();
        }
      }
    }
    return getCommunityPoolSpendMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPool,
      com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPoolResponse> getDepositValidatorRewardsPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DepositValidatorRewardsPool",
      requestType = com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPool.class,
      responseType = com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPool,
      com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPoolResponse> getDepositValidatorRewardsPoolMethod() {
    io.grpc.MethodDescriptor<com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPool, com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPoolResponse> getDepositValidatorRewardsPoolMethod;
    if ((getDepositValidatorRewardsPoolMethod = MsgGrpc.getDepositValidatorRewardsPoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDepositValidatorRewardsPoolMethod = MsgGrpc.getDepositValidatorRewardsPoolMethod) == null) {
          MsgGrpc.getDepositValidatorRewardsPoolMethod = getDepositValidatorRewardsPoolMethod =
              io.grpc.MethodDescriptor.<com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPool, com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DepositValidatorRewardsPool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DepositValidatorRewardsPool"))
              .build();
        }
      }
    }
    return getDepositValidatorRewardsPoolMethod;
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
   * Msg defines the distribution Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * SetWithdrawAddress defines a method to change the withdraw address
     * for a delegator (or validator self-delegation).
     * </pre>
     */
    default void setWithdrawAddress(com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress request,
        io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddressResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetWithdrawAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * WithdrawDelegatorReward defines a method to withdraw rewards of delegator
     * from a single validator.
     * </pre>
     */
    default void withdrawDelegatorReward(com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorReward request,
        io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorRewardResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWithdrawDelegatorRewardMethod(), responseObserver);
    }

    /**
     * <pre>
     * WithdrawValidatorCommission defines a method to withdraw the
     * full commission to the validator address.
     * </pre>
     */
    default void withdrawValidatorCommission(com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommission request,
        io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommissionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWithdrawValidatorCommissionMethod(), responseObserver);
    }

    /**
     * <pre>
     * FundCommunityPool defines a method to allow an account to directly
     * fund the community pool.
     * </pre>
     */
    default void fundCommunityPool(com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPool request,
        io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPoolResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFundCommunityPoolMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateParams defines a governance operation for updating the x/distribution
     * module parameters. The authority is defined in the keeper.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    default void updateParams(com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * CommunityPoolSpend defines a governance operation for sending tokens from
     * the community pool in the x/distribution module to another account, which
     * could be the governance module itself. The authority is defined in the
     * keeper.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    default void communityPoolSpend(com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpend request,
        io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpendResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCommunityPoolSpendMethod(), responseObserver);
    }

    /**
     * <pre>
     * DepositValidatorRewardsPool defines a method to provide additional rewards
     * to delegators to a specific validator.
     * Since: cosmos-sdk 0.50
     * </pre>
     */
    default void depositValidatorRewardsPool(com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPool request,
        io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPoolResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDepositValidatorRewardsPoolMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the distribution Msg service.
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
   * Msg defines the distribution Msg service.
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
     * SetWithdrawAddress defines a method to change the withdraw address
     * for a delegator (or validator self-delegation).
     * </pre>
     */
    public void setWithdrawAddress(com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress request,
        io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddressResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetWithdrawAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WithdrawDelegatorReward defines a method to withdraw rewards of delegator
     * from a single validator.
     * </pre>
     */
    public void withdrawDelegatorReward(com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorReward request,
        io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorRewardResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getWithdrawDelegatorRewardMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WithdrawValidatorCommission defines a method to withdraw the
     * full commission to the validator address.
     * </pre>
     */
    public void withdrawValidatorCommission(com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommission request,
        io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommissionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getWithdrawValidatorCommissionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * FundCommunityPool defines a method to allow an account to directly
     * fund the community pool.
     * </pre>
     */
    public void fundCommunityPool(com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPool request,
        io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPoolResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFundCommunityPoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateParams defines a governance operation for updating the x/distribution
     * module parameters. The authority is defined in the keeper.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public void updateParams(com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CommunityPoolSpend defines a governance operation for sending tokens from
     * the community pool in the x/distribution module to another account, which
     * could be the governance module itself. The authority is defined in the
     * keeper.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public void communityPoolSpend(com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpend request,
        io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpendResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCommunityPoolSpendMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DepositValidatorRewardsPool defines a method to provide additional rewards
     * to delegators to a specific validator.
     * Since: cosmos-sdk 0.50
     * </pre>
     */
    public void depositValidatorRewardsPool(com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPool request,
        io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPoolResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDepositValidatorRewardsPoolMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the distribution Msg service.
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
     * SetWithdrawAddress defines a method to change the withdraw address
     * for a delegator (or validator self-delegation).
     * </pre>
     */
    public com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddressResponse setWithdrawAddress(com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetWithdrawAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WithdrawDelegatorReward defines a method to withdraw rewards of delegator
     * from a single validator.
     * </pre>
     */
    public com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorRewardResponse withdrawDelegatorReward(com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorReward request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getWithdrawDelegatorRewardMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WithdrawValidatorCommission defines a method to withdraw the
     * full commission to the validator address.
     * </pre>
     */
    public com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommissionResponse withdrawValidatorCommission(com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommission request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getWithdrawValidatorCommissionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * FundCommunityPool defines a method to allow an account to directly
     * fund the community pool.
     * </pre>
     */
    public com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPoolResponse fundCommunityPool(com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPool request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFundCommunityPoolMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateParams defines a governance operation for updating the x/distribution
     * module parameters. The authority is defined in the keeper.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParamsResponse updateParams(com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CommunityPoolSpend defines a governance operation for sending tokens from
     * the community pool in the x/distribution module to another account, which
     * could be the governance module itself. The authority is defined in the
     * keeper.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpendResponse communityPoolSpend(com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpend request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCommunityPoolSpendMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DepositValidatorRewardsPool defines a method to provide additional rewards
     * to delegators to a specific validator.
     * Since: cosmos-sdk 0.50
     * </pre>
     */
    public com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPoolResponse depositValidatorRewardsPool(com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPool request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDepositValidatorRewardsPoolMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the distribution Msg service.
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
     * SetWithdrawAddress defines a method to change the withdraw address
     * for a delegator (or validator self-delegation).
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddressResponse> setWithdrawAddress(
        com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetWithdrawAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WithdrawDelegatorReward defines a method to withdraw rewards of delegator
     * from a single validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorRewardResponse> withdrawDelegatorReward(
        com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorReward request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getWithdrawDelegatorRewardMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WithdrawValidatorCommission defines a method to withdraw the
     * full commission to the validator address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommissionResponse> withdrawValidatorCommission(
        com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommission request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getWithdrawValidatorCommissionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * FundCommunityPool defines a method to allow an account to directly
     * fund the community pool.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPoolResponse> fundCommunityPool(
        com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPool request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFundCommunityPoolMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateParams defines a governance operation for updating the x/distribution
     * module parameters. The authority is defined in the keeper.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParamsResponse> updateParams(
        com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CommunityPoolSpend defines a governance operation for sending tokens from
     * the community pool in the x/distribution module to another account, which
     * could be the governance module itself. The authority is defined in the
     * keeper.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpendResponse> communityPoolSpend(
        com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpend request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCommunityPoolSpendMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DepositValidatorRewardsPool defines a method to provide additional rewards
     * to delegators to a specific validator.
     * Since: cosmos-sdk 0.50
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPoolResponse> depositValidatorRewardsPool(
        com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPool request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDepositValidatorRewardsPoolMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_WITHDRAW_ADDRESS = 0;
  private static final int METHODID_WITHDRAW_DELEGATOR_REWARD = 1;
  private static final int METHODID_WITHDRAW_VALIDATOR_COMMISSION = 2;
  private static final int METHODID_FUND_COMMUNITY_POOL = 3;
  private static final int METHODID_UPDATE_PARAMS = 4;
  private static final int METHODID_COMMUNITY_POOL_SPEND = 5;
  private static final int METHODID_DEPOSIT_VALIDATOR_REWARDS_POOL = 6;

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
        case METHODID_SET_WITHDRAW_ADDRESS:
          serviceImpl.setWithdrawAddress((com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress) request,
              (io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddressResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_DELEGATOR_REWARD:
          serviceImpl.withdrawDelegatorReward((com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorReward) request,
              (io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorRewardResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_VALIDATOR_COMMISSION:
          serviceImpl.withdrawValidatorCommission((com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommission) request,
              (io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommissionResponse>) responseObserver);
          break;
        case METHODID_FUND_COMMUNITY_POOL:
          serviceImpl.fundCommunityPool((com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPool) request,
              (io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPoolResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PARAMS:
          serviceImpl.updateParams((com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParams) request,
              (io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParamsResponse>) responseObserver);
          break;
        case METHODID_COMMUNITY_POOL_SPEND:
          serviceImpl.communityPoolSpend((com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpend) request,
              (io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpendResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT_VALIDATOR_REWARDS_POOL:
          serviceImpl.depositValidatorRewardsPool((com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPool) request,
              (io.grpc.stub.StreamObserver<com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPoolResponse>) responseObserver);
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
          getSetWithdrawAddressMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress,
              com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddressResponse>(
                service, METHODID_SET_WITHDRAW_ADDRESS)))
        .addMethod(
          getWithdrawDelegatorRewardMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorReward,
              com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorRewardResponse>(
                service, METHODID_WITHDRAW_DELEGATOR_REWARD)))
        .addMethod(
          getWithdrawValidatorCommissionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommission,
              com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawValidatorCommissionResponse>(
                service, METHODID_WITHDRAW_VALIDATOR_COMMISSION)))
        .addMethod(
          getFundCommunityPoolMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPool,
              com.cosmos.distribution.v1beta1.TxProto.MsgFundCommunityPoolResponse>(
                service, METHODID_FUND_COMMUNITY_POOL)))
        .addMethod(
          getUpdateParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParams,
              com.cosmos.distribution.v1beta1.TxProto.MsgUpdateParamsResponse>(
                service, METHODID_UPDATE_PARAMS)))
        .addMethod(
          getCommunityPoolSpendMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpend,
              com.cosmos.distribution.v1beta1.TxProto.MsgCommunityPoolSpendResponse>(
                service, METHODID_COMMUNITY_POOL_SPEND)))
        .addMethod(
          getDepositValidatorRewardsPoolMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPool,
              com.cosmos.distribution.v1beta1.TxProto.MsgDepositValidatorRewardsPoolResponse>(
                service, METHODID_DEPOSIT_VALIDATOR_REWARDS_POOL)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmos.distribution.v1beta1.TxProto.getDescriptor();
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
              .addMethod(getSetWithdrawAddressMethod())
              .addMethod(getWithdrawDelegatorRewardMethod())
              .addMethod(getWithdrawValidatorCommissionMethod())
              .addMethod(getFundCommunityPoolMethod())
              .addMethod(getUpdateParamsMethod())
              .addMethod(getCommunityPoolSpendMethod())
              .addMethod(getDepositValidatorRewardsPoolMethod())
              .build();
        }
      }
    }
    return result;
  }
}
