package com.zrchain.validation;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the staking Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: zrchain/validation/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "zrchain.validation.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgCreateValidator,
      com.zrchain.validation.TxProto.MsgCreateValidatorResponse> getCreateValidatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateValidator",
      requestType = com.zrchain.validation.TxProto.MsgCreateValidator.class,
      responseType = com.zrchain.validation.TxProto.MsgCreateValidatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgCreateValidator,
      com.zrchain.validation.TxProto.MsgCreateValidatorResponse> getCreateValidatorMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgCreateValidator, com.zrchain.validation.TxProto.MsgCreateValidatorResponse> getCreateValidatorMethod;
    if ((getCreateValidatorMethod = MsgGrpc.getCreateValidatorMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateValidatorMethod = MsgGrpc.getCreateValidatorMethod) == null) {
          MsgGrpc.getCreateValidatorMethod = getCreateValidatorMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.TxProto.MsgCreateValidator, com.zrchain.validation.TxProto.MsgCreateValidatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateValidator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.TxProto.MsgCreateValidator.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.TxProto.MsgCreateValidatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateValidator"))
              .build();
        }
      }
    }
    return getCreateValidatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgEditValidator,
      com.zrchain.validation.TxProto.MsgEditValidatorResponse> getEditValidatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditValidator",
      requestType = com.zrchain.validation.TxProto.MsgEditValidator.class,
      responseType = com.zrchain.validation.TxProto.MsgEditValidatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgEditValidator,
      com.zrchain.validation.TxProto.MsgEditValidatorResponse> getEditValidatorMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgEditValidator, com.zrchain.validation.TxProto.MsgEditValidatorResponse> getEditValidatorMethod;
    if ((getEditValidatorMethod = MsgGrpc.getEditValidatorMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEditValidatorMethod = MsgGrpc.getEditValidatorMethod) == null) {
          MsgGrpc.getEditValidatorMethod = getEditValidatorMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.TxProto.MsgEditValidator, com.zrchain.validation.TxProto.MsgEditValidatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditValidator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.TxProto.MsgEditValidator.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.TxProto.MsgEditValidatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EditValidator"))
              .build();
        }
      }
    }
    return getEditValidatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgDelegate,
      com.zrchain.validation.TxProto.MsgDelegateResponse> getDelegateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Delegate",
      requestType = com.zrchain.validation.TxProto.MsgDelegate.class,
      responseType = com.zrchain.validation.TxProto.MsgDelegateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgDelegate,
      com.zrchain.validation.TxProto.MsgDelegateResponse> getDelegateMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgDelegate, com.zrchain.validation.TxProto.MsgDelegateResponse> getDelegateMethod;
    if ((getDelegateMethod = MsgGrpc.getDelegateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDelegateMethod = MsgGrpc.getDelegateMethod) == null) {
          MsgGrpc.getDelegateMethod = getDelegateMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.TxProto.MsgDelegate, com.zrchain.validation.TxProto.MsgDelegateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Delegate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.TxProto.MsgDelegate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.TxProto.MsgDelegateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Delegate"))
              .build();
        }
      }
    }
    return getDelegateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgBeginRedelegate,
      com.zrchain.validation.TxProto.MsgBeginRedelegateResponse> getBeginRedelegateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BeginRedelegate",
      requestType = com.zrchain.validation.TxProto.MsgBeginRedelegate.class,
      responseType = com.zrchain.validation.TxProto.MsgBeginRedelegateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgBeginRedelegate,
      com.zrchain.validation.TxProto.MsgBeginRedelegateResponse> getBeginRedelegateMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgBeginRedelegate, com.zrchain.validation.TxProto.MsgBeginRedelegateResponse> getBeginRedelegateMethod;
    if ((getBeginRedelegateMethod = MsgGrpc.getBeginRedelegateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBeginRedelegateMethod = MsgGrpc.getBeginRedelegateMethod) == null) {
          MsgGrpc.getBeginRedelegateMethod = getBeginRedelegateMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.TxProto.MsgBeginRedelegate, com.zrchain.validation.TxProto.MsgBeginRedelegateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BeginRedelegate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.TxProto.MsgBeginRedelegate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.TxProto.MsgBeginRedelegateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BeginRedelegate"))
              .build();
        }
      }
    }
    return getBeginRedelegateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgUndelegate,
      com.zrchain.validation.TxProto.MsgUndelegateResponse> getUndelegateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Undelegate",
      requestType = com.zrchain.validation.TxProto.MsgUndelegate.class,
      responseType = com.zrchain.validation.TxProto.MsgUndelegateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgUndelegate,
      com.zrchain.validation.TxProto.MsgUndelegateResponse> getUndelegateMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgUndelegate, com.zrchain.validation.TxProto.MsgUndelegateResponse> getUndelegateMethod;
    if ((getUndelegateMethod = MsgGrpc.getUndelegateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUndelegateMethod = MsgGrpc.getUndelegateMethod) == null) {
          MsgGrpc.getUndelegateMethod = getUndelegateMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.TxProto.MsgUndelegate, com.zrchain.validation.TxProto.MsgUndelegateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Undelegate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.TxProto.MsgUndelegate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.TxProto.MsgUndelegateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Undelegate"))
              .build();
        }
      }
    }
    return getUndelegateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgCancelUnbondingDelegation,
      com.zrchain.validation.TxProto.MsgCancelUnbondingDelegationResponse> getCancelUnbondingDelegationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelUnbondingDelegation",
      requestType = com.zrchain.validation.TxProto.MsgCancelUnbondingDelegation.class,
      responseType = com.zrchain.validation.TxProto.MsgCancelUnbondingDelegationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgCancelUnbondingDelegation,
      com.zrchain.validation.TxProto.MsgCancelUnbondingDelegationResponse> getCancelUnbondingDelegationMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgCancelUnbondingDelegation, com.zrchain.validation.TxProto.MsgCancelUnbondingDelegationResponse> getCancelUnbondingDelegationMethod;
    if ((getCancelUnbondingDelegationMethod = MsgGrpc.getCancelUnbondingDelegationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelUnbondingDelegationMethod = MsgGrpc.getCancelUnbondingDelegationMethod) == null) {
          MsgGrpc.getCancelUnbondingDelegationMethod = getCancelUnbondingDelegationMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.TxProto.MsgCancelUnbondingDelegation, com.zrchain.validation.TxProto.MsgCancelUnbondingDelegationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelUnbondingDelegation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.TxProto.MsgCancelUnbondingDelegation.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.TxProto.MsgCancelUnbondingDelegationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelUnbondingDelegation"))
              .build();
        }
      }
    }
    return getCancelUnbondingDelegationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgUpdateParams,
      com.zrchain.validation.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateParams",
      requestType = com.zrchain.validation.TxProto.MsgUpdateParams.class,
      responseType = com.zrchain.validation.TxProto.MsgUpdateParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgUpdateParams,
      com.zrchain.validation.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgUpdateParams, com.zrchain.validation.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;
    if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
          MsgGrpc.getUpdateParamsMethod = getUpdateParamsMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.TxProto.MsgUpdateParams, com.zrchain.validation.TxProto.MsgUpdateParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.TxProto.MsgUpdateParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.TxProto.MsgUpdateParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateParams"))
              .build();
        }
      }
    }
    return getUpdateParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgUpdateHVParams,
      com.zrchain.validation.TxProto.MsgUpdateHVParamsResponse> getUpdateHVParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateHVParams",
      requestType = com.zrchain.validation.TxProto.MsgUpdateHVParams.class,
      responseType = com.zrchain.validation.TxProto.MsgUpdateHVParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgUpdateHVParams,
      com.zrchain.validation.TxProto.MsgUpdateHVParamsResponse> getUpdateHVParamsMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.TxProto.MsgUpdateHVParams, com.zrchain.validation.TxProto.MsgUpdateHVParamsResponse> getUpdateHVParamsMethod;
    if ((getUpdateHVParamsMethod = MsgGrpc.getUpdateHVParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateHVParamsMethod = MsgGrpc.getUpdateHVParamsMethod) == null) {
          MsgGrpc.getUpdateHVParamsMethod = getUpdateHVParamsMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.TxProto.MsgUpdateHVParams, com.zrchain.validation.TxProto.MsgUpdateHVParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateHVParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.TxProto.MsgUpdateHVParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.TxProto.MsgUpdateHVParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateHVParams"))
              .build();
        }
      }
    }
    return getUpdateHVParamsMethod;
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
   * Msg defines the staking Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * CreateValidator defines a method for creating a new validator.
     * </pre>
     */
    default void createValidator(com.zrchain.validation.TxProto.MsgCreateValidator request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgCreateValidatorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateValidatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * EditValidator defines a method for editing an existing validator.
     * </pre>
     */
    default void editValidator(com.zrchain.validation.TxProto.MsgEditValidator request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgEditValidatorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEditValidatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * Delegate defines a method for performing a delegation of coins
     * from a delegator to a validator.
     * </pre>
     */
    default void delegate(com.zrchain.validation.TxProto.MsgDelegate request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgDelegateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDelegateMethod(), responseObserver);
    }

    /**
     * <pre>
     * BeginRedelegate defines a method for performing a redelegation
     * of coins from a delegator and source validator to a destination validator.
     * </pre>
     */
    default void beginRedelegate(com.zrchain.validation.TxProto.MsgBeginRedelegate request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgBeginRedelegateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBeginRedelegateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Undelegate defines a method for performing an undelegation from a
     * delegate and a validator.
     * </pre>
     */
    default void undelegate(com.zrchain.validation.TxProto.MsgUndelegate request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgUndelegateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUndelegateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    default void cancelUnbondingDelegation(com.zrchain.validation.TxProto.MsgCancelUnbondingDelegation request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgCancelUnbondingDelegationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCancelUnbondingDelegationMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateParams defines an operation for updating the x/staking module
     * parameters.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    default void updateParams(com.zrchain.validation.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateParamsMethod(), responseObserver);
    }

    /**
     */
    default void updateHVParams(com.zrchain.validation.TxProto.MsgUpdateHVParams request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgUpdateHVParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateHVParamsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the staking Msg service.
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
   * Msg defines the staking Msg service.
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
     * CreateValidator defines a method for creating a new validator.
     * </pre>
     */
    public void createValidator(com.zrchain.validation.TxProto.MsgCreateValidator request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgCreateValidatorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateValidatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EditValidator defines a method for editing an existing validator.
     * </pre>
     */
    public void editValidator(com.zrchain.validation.TxProto.MsgEditValidator request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgEditValidatorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEditValidatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Delegate defines a method for performing a delegation of coins
     * from a delegator to a validator.
     * </pre>
     */
    public void delegate(com.zrchain.validation.TxProto.MsgDelegate request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgDelegateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDelegateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BeginRedelegate defines a method for performing a redelegation
     * of coins from a delegator and source validator to a destination validator.
     * </pre>
     */
    public void beginRedelegate(com.zrchain.validation.TxProto.MsgBeginRedelegate request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgBeginRedelegateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBeginRedelegateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Undelegate defines a method for performing an undelegation from a
     * delegate and a validator.
     * </pre>
     */
    public void undelegate(com.zrchain.validation.TxProto.MsgUndelegate request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgUndelegateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUndelegateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public void cancelUnbondingDelegation(com.zrchain.validation.TxProto.MsgCancelUnbondingDelegation request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgCancelUnbondingDelegationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCancelUnbondingDelegationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateParams defines an operation for updating the x/staking module
     * parameters.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public void updateParams(com.zrchain.validation.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateHVParams(com.zrchain.validation.TxProto.MsgUpdateHVParams request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgUpdateHVParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateHVParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the staking Msg service.
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
     * CreateValidator defines a method for creating a new validator.
     * </pre>
     */
    public com.zrchain.validation.TxProto.MsgCreateValidatorResponse createValidator(com.zrchain.validation.TxProto.MsgCreateValidator request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateValidatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EditValidator defines a method for editing an existing validator.
     * </pre>
     */
    public com.zrchain.validation.TxProto.MsgEditValidatorResponse editValidator(com.zrchain.validation.TxProto.MsgEditValidator request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEditValidatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Delegate defines a method for performing a delegation of coins
     * from a delegator to a validator.
     * </pre>
     */
    public com.zrchain.validation.TxProto.MsgDelegateResponse delegate(com.zrchain.validation.TxProto.MsgDelegate request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDelegateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BeginRedelegate defines a method for performing a redelegation
     * of coins from a delegator and source validator to a destination validator.
     * </pre>
     */
    public com.zrchain.validation.TxProto.MsgBeginRedelegateResponse beginRedelegate(com.zrchain.validation.TxProto.MsgBeginRedelegate request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBeginRedelegateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Undelegate defines a method for performing an undelegation from a
     * delegate and a validator.
     * </pre>
     */
    public com.zrchain.validation.TxProto.MsgUndelegateResponse undelegate(com.zrchain.validation.TxProto.MsgUndelegate request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUndelegateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.zrchain.validation.TxProto.MsgCancelUnbondingDelegationResponse cancelUnbondingDelegation(com.zrchain.validation.TxProto.MsgCancelUnbondingDelegation request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCancelUnbondingDelegationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateParams defines an operation for updating the x/staking module
     * parameters.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.zrchain.validation.TxProto.MsgUpdateParamsResponse updateParams(com.zrchain.validation.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.zrchain.validation.TxProto.MsgUpdateHVParamsResponse updateHVParams(com.zrchain.validation.TxProto.MsgUpdateHVParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateHVParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the staking Msg service.
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
     * CreateValidator defines a method for creating a new validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.TxProto.MsgCreateValidatorResponse> createValidator(
        com.zrchain.validation.TxProto.MsgCreateValidator request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateValidatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EditValidator defines a method for editing an existing validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.TxProto.MsgEditValidatorResponse> editValidator(
        com.zrchain.validation.TxProto.MsgEditValidator request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEditValidatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Delegate defines a method for performing a delegation of coins
     * from a delegator to a validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.TxProto.MsgDelegateResponse> delegate(
        com.zrchain.validation.TxProto.MsgDelegate request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDelegateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BeginRedelegate defines a method for performing a redelegation
     * of coins from a delegator and source validator to a destination validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.TxProto.MsgBeginRedelegateResponse> beginRedelegate(
        com.zrchain.validation.TxProto.MsgBeginRedelegate request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBeginRedelegateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Undelegate defines a method for performing an undelegation from a
     * delegate and a validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.TxProto.MsgUndelegateResponse> undelegate(
        com.zrchain.validation.TxProto.MsgUndelegate request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUndelegateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.TxProto.MsgCancelUnbondingDelegationResponse> cancelUnbondingDelegation(
        com.zrchain.validation.TxProto.MsgCancelUnbondingDelegation request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCancelUnbondingDelegationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateParams defines an operation for updating the x/staking module
     * parameters.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.TxProto.MsgUpdateParamsResponse> updateParams(
        com.zrchain.validation.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.TxProto.MsgUpdateHVParamsResponse> updateHVParams(
        com.zrchain.validation.TxProto.MsgUpdateHVParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateHVParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_VALIDATOR = 0;
  private static final int METHODID_EDIT_VALIDATOR = 1;
  private static final int METHODID_DELEGATE = 2;
  private static final int METHODID_BEGIN_REDELEGATE = 3;
  private static final int METHODID_UNDELEGATE = 4;
  private static final int METHODID_CANCEL_UNBONDING_DELEGATION = 5;
  private static final int METHODID_UPDATE_PARAMS = 6;
  private static final int METHODID_UPDATE_HVPARAMS = 7;

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
        case METHODID_CREATE_VALIDATOR:
          serviceImpl.createValidator((com.zrchain.validation.TxProto.MsgCreateValidator) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgCreateValidatorResponse>) responseObserver);
          break;
        case METHODID_EDIT_VALIDATOR:
          serviceImpl.editValidator((com.zrchain.validation.TxProto.MsgEditValidator) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgEditValidatorResponse>) responseObserver);
          break;
        case METHODID_DELEGATE:
          serviceImpl.delegate((com.zrchain.validation.TxProto.MsgDelegate) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgDelegateResponse>) responseObserver);
          break;
        case METHODID_BEGIN_REDELEGATE:
          serviceImpl.beginRedelegate((com.zrchain.validation.TxProto.MsgBeginRedelegate) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgBeginRedelegateResponse>) responseObserver);
          break;
        case METHODID_UNDELEGATE:
          serviceImpl.undelegate((com.zrchain.validation.TxProto.MsgUndelegate) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgUndelegateResponse>) responseObserver);
          break;
        case METHODID_CANCEL_UNBONDING_DELEGATION:
          serviceImpl.cancelUnbondingDelegation((com.zrchain.validation.TxProto.MsgCancelUnbondingDelegation) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgCancelUnbondingDelegationResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PARAMS:
          serviceImpl.updateParams((com.zrchain.validation.TxProto.MsgUpdateParams) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgUpdateParamsResponse>) responseObserver);
          break;
        case METHODID_UPDATE_HVPARAMS:
          serviceImpl.updateHVParams((com.zrchain.validation.TxProto.MsgUpdateHVParams) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.TxProto.MsgUpdateHVParamsResponse>) responseObserver);
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
          getCreateValidatorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.TxProto.MsgCreateValidator,
              com.zrchain.validation.TxProto.MsgCreateValidatorResponse>(
                service, METHODID_CREATE_VALIDATOR)))
        .addMethod(
          getEditValidatorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.TxProto.MsgEditValidator,
              com.zrchain.validation.TxProto.MsgEditValidatorResponse>(
                service, METHODID_EDIT_VALIDATOR)))
        .addMethod(
          getDelegateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.TxProto.MsgDelegate,
              com.zrchain.validation.TxProto.MsgDelegateResponse>(
                service, METHODID_DELEGATE)))
        .addMethod(
          getBeginRedelegateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.TxProto.MsgBeginRedelegate,
              com.zrchain.validation.TxProto.MsgBeginRedelegateResponse>(
                service, METHODID_BEGIN_REDELEGATE)))
        .addMethod(
          getUndelegateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.TxProto.MsgUndelegate,
              com.zrchain.validation.TxProto.MsgUndelegateResponse>(
                service, METHODID_UNDELEGATE)))
        .addMethod(
          getCancelUnbondingDelegationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.TxProto.MsgCancelUnbondingDelegation,
              com.zrchain.validation.TxProto.MsgCancelUnbondingDelegationResponse>(
                service, METHODID_CANCEL_UNBONDING_DELEGATION)))
        .addMethod(
          getUpdateParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.TxProto.MsgUpdateParams,
              com.zrchain.validation.TxProto.MsgUpdateParamsResponse>(
                service, METHODID_UPDATE_PARAMS)))
        .addMethod(
          getUpdateHVParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.TxProto.MsgUpdateHVParams,
              com.zrchain.validation.TxProto.MsgUpdateHVParamsResponse>(
                service, METHODID_UPDATE_HVPARAMS)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.zrchain.validation.TxProto.getDescriptor();
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
              .addMethod(getCreateValidatorMethod())
              .addMethod(getEditValidatorMethod())
              .addMethod(getDelegateMethod())
              .addMethod(getBeginRedelegateMethod())
              .addMethod(getUndelegateMethod())
              .addMethod(getCancelUnbondingDelegationMethod())
              .addMethod(getUpdateParamsMethod())
              .addMethod(getUpdateHVParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
