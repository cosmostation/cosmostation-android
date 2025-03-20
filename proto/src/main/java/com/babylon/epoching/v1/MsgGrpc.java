package com.babylon.epoching.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: babylon/epoching/v1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "babylon.epoching.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedDelegate,
      com.babylon.epoching.v1.TxProto.MsgWrappedDelegateResponse> getWrappedDelegateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WrappedDelegate",
      requestType = com.babylon.epoching.v1.TxProto.MsgWrappedDelegate.class,
      responseType = com.babylon.epoching.v1.TxProto.MsgWrappedDelegateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedDelegate,
      com.babylon.epoching.v1.TxProto.MsgWrappedDelegateResponse> getWrappedDelegateMethod() {
    io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedDelegate, com.babylon.epoching.v1.TxProto.MsgWrappedDelegateResponse> getWrappedDelegateMethod;
    if ((getWrappedDelegateMethod = MsgGrpc.getWrappedDelegateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWrappedDelegateMethod = MsgGrpc.getWrappedDelegateMethod) == null) {
          MsgGrpc.getWrappedDelegateMethod = getWrappedDelegateMethod =
              io.grpc.MethodDescriptor.<com.babylon.epoching.v1.TxProto.MsgWrappedDelegate, com.babylon.epoching.v1.TxProto.MsgWrappedDelegateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WrappedDelegate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.TxProto.MsgWrappedDelegate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.TxProto.MsgWrappedDelegateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WrappedDelegate"))
              .build();
        }
      }
    }
    return getWrappedDelegateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedUndelegate,
      com.babylon.epoching.v1.TxProto.MsgWrappedUndelegateResponse> getWrappedUndelegateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WrappedUndelegate",
      requestType = com.babylon.epoching.v1.TxProto.MsgWrappedUndelegate.class,
      responseType = com.babylon.epoching.v1.TxProto.MsgWrappedUndelegateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedUndelegate,
      com.babylon.epoching.v1.TxProto.MsgWrappedUndelegateResponse> getWrappedUndelegateMethod() {
    io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedUndelegate, com.babylon.epoching.v1.TxProto.MsgWrappedUndelegateResponse> getWrappedUndelegateMethod;
    if ((getWrappedUndelegateMethod = MsgGrpc.getWrappedUndelegateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWrappedUndelegateMethod = MsgGrpc.getWrappedUndelegateMethod) == null) {
          MsgGrpc.getWrappedUndelegateMethod = getWrappedUndelegateMethod =
              io.grpc.MethodDescriptor.<com.babylon.epoching.v1.TxProto.MsgWrappedUndelegate, com.babylon.epoching.v1.TxProto.MsgWrappedUndelegateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WrappedUndelegate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.TxProto.MsgWrappedUndelegate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.TxProto.MsgWrappedUndelegateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WrappedUndelegate"))
              .build();
        }
      }
    }
    return getWrappedUndelegateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegate,
      com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegateResponse> getWrappedBeginRedelegateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WrappedBeginRedelegate",
      requestType = com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegate.class,
      responseType = com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegate,
      com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegateResponse> getWrappedBeginRedelegateMethod() {
    io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegate, com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegateResponse> getWrappedBeginRedelegateMethod;
    if ((getWrappedBeginRedelegateMethod = MsgGrpc.getWrappedBeginRedelegateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWrappedBeginRedelegateMethod = MsgGrpc.getWrappedBeginRedelegateMethod) == null) {
          MsgGrpc.getWrappedBeginRedelegateMethod = getWrappedBeginRedelegateMethod =
              io.grpc.MethodDescriptor.<com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegate, com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WrappedBeginRedelegate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WrappedBeginRedelegate"))
              .build();
        }
      }
    }
    return getWrappedBeginRedelegateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegation,
      com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegationResponse> getWrappedCancelUnbondingDelegationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WrappedCancelUnbondingDelegation",
      requestType = com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegation.class,
      responseType = com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegation,
      com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegationResponse> getWrappedCancelUnbondingDelegationMethod() {
    io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegation, com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegationResponse> getWrappedCancelUnbondingDelegationMethod;
    if ((getWrappedCancelUnbondingDelegationMethod = MsgGrpc.getWrappedCancelUnbondingDelegationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWrappedCancelUnbondingDelegationMethod = MsgGrpc.getWrappedCancelUnbondingDelegationMethod) == null) {
          MsgGrpc.getWrappedCancelUnbondingDelegationMethod = getWrappedCancelUnbondingDelegationMethod =
              io.grpc.MethodDescriptor.<com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegation, com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WrappedCancelUnbondingDelegation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegation.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WrappedCancelUnbondingDelegation"))
              .build();
        }
      }
    }
    return getWrappedCancelUnbondingDelegationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedEditValidator,
      com.babylon.epoching.v1.TxProto.MsgWrappedEditValidatorResponse> getWrappedEditValidatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WrappedEditValidator",
      requestType = com.babylon.epoching.v1.TxProto.MsgWrappedEditValidator.class,
      responseType = com.babylon.epoching.v1.TxProto.MsgWrappedEditValidatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedEditValidator,
      com.babylon.epoching.v1.TxProto.MsgWrappedEditValidatorResponse> getWrappedEditValidatorMethod() {
    io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedEditValidator, com.babylon.epoching.v1.TxProto.MsgWrappedEditValidatorResponse> getWrappedEditValidatorMethod;
    if ((getWrappedEditValidatorMethod = MsgGrpc.getWrappedEditValidatorMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWrappedEditValidatorMethod = MsgGrpc.getWrappedEditValidatorMethod) == null) {
          MsgGrpc.getWrappedEditValidatorMethod = getWrappedEditValidatorMethod =
              io.grpc.MethodDescriptor.<com.babylon.epoching.v1.TxProto.MsgWrappedEditValidator, com.babylon.epoching.v1.TxProto.MsgWrappedEditValidatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WrappedEditValidator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.TxProto.MsgWrappedEditValidator.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.TxProto.MsgWrappedEditValidatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WrappedEditValidator"))
              .build();
        }
      }
    }
    return getWrappedEditValidatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParams,
      com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParamsResponse> getWrappedStakingUpdateParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WrappedStakingUpdateParams",
      requestType = com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParams.class,
      responseType = com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParams,
      com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParamsResponse> getWrappedStakingUpdateParamsMethod() {
    io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParams, com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParamsResponse> getWrappedStakingUpdateParamsMethod;
    if ((getWrappedStakingUpdateParamsMethod = MsgGrpc.getWrappedStakingUpdateParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWrappedStakingUpdateParamsMethod = MsgGrpc.getWrappedStakingUpdateParamsMethod) == null) {
          MsgGrpc.getWrappedStakingUpdateParamsMethod = getWrappedStakingUpdateParamsMethod =
              io.grpc.MethodDescriptor.<com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParams, com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WrappedStakingUpdateParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WrappedStakingUpdateParams"))
              .build();
        }
      }
    }
    return getWrappedStakingUpdateParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgUpdateParams,
      com.babylon.epoching.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateParams",
      requestType = com.babylon.epoching.v1.TxProto.MsgUpdateParams.class,
      responseType = com.babylon.epoching.v1.TxProto.MsgUpdateParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgUpdateParams,
      com.babylon.epoching.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod() {
    io.grpc.MethodDescriptor<com.babylon.epoching.v1.TxProto.MsgUpdateParams, com.babylon.epoching.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;
    if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
          MsgGrpc.getUpdateParamsMethod = getUpdateParamsMethod =
              io.grpc.MethodDescriptor.<com.babylon.epoching.v1.TxProto.MsgUpdateParams, com.babylon.epoching.v1.TxProto.MsgUpdateParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.TxProto.MsgUpdateParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.TxProto.MsgUpdateParamsResponse.getDefaultInstance()))
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
   * Msg defines the Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * WrappedDelegate defines a method for performing a delegation of coins from
     * a delegator to a validator.
     * </pre>
     */
    default void wrappedDelegate(com.babylon.epoching.v1.TxProto.MsgWrappedDelegate request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedDelegateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWrappedDelegateMethod(), responseObserver);
    }

    /**
     * <pre>
     * WrappedUndelegate defines a method for performing an undelegation from a
     * delegate and a validator.
     * </pre>
     */
    default void wrappedUndelegate(com.babylon.epoching.v1.TxProto.MsgWrappedUndelegate request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedUndelegateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWrappedUndelegateMethod(), responseObserver);
    }

    /**
     * <pre>
     * WrappedBeginRedelegate defines a method for performing a redelegation of
     * coins from a delegator and source validator to a destination validator.
     * </pre>
     */
    default void wrappedBeginRedelegate(com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegate request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWrappedBeginRedelegateMethod(), responseObserver);
    }

    /**
     * <pre>
     * WrappedCancelUnbondingDelegation defines a method for cancelling unbonding of
     * coins from a delegator and source validator to a destination validator.
     * </pre>
     */
    default void wrappedCancelUnbondingDelegation(com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegation request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWrappedCancelUnbondingDelegationMethod(), responseObserver);
    }

    /**
     * <pre>
     * WrappedEditValidator defines a method for editing the validator
     * information.
     * </pre>
     */
    default void wrappedEditValidator(com.babylon.epoching.v1.TxProto.MsgWrappedEditValidator request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedEditValidatorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWrappedEditValidatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * WrappedStakingUpdateParams defines a method for update the parameters
     * of the x/staking module.
     * </pre>
     */
    default void wrappedStakingUpdateParams(com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParams request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWrappedStakingUpdateParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateParams defines a method for updating epoching module parameters.
     * </pre>
     */
    default void updateParams(com.babylon.epoching.v1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateParamsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the Msg service.
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
   * Msg defines the Msg service.
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
     * WrappedDelegate defines a method for performing a delegation of coins from
     * a delegator to a validator.
     * </pre>
     */
    public void wrappedDelegate(com.babylon.epoching.v1.TxProto.MsgWrappedDelegate request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedDelegateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getWrappedDelegateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WrappedUndelegate defines a method for performing an undelegation from a
     * delegate and a validator.
     * </pre>
     */
    public void wrappedUndelegate(com.babylon.epoching.v1.TxProto.MsgWrappedUndelegate request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedUndelegateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getWrappedUndelegateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WrappedBeginRedelegate defines a method for performing a redelegation of
     * coins from a delegator and source validator to a destination validator.
     * </pre>
     */
    public void wrappedBeginRedelegate(com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegate request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getWrappedBeginRedelegateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WrappedCancelUnbondingDelegation defines a method for cancelling unbonding of
     * coins from a delegator and source validator to a destination validator.
     * </pre>
     */
    public void wrappedCancelUnbondingDelegation(com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegation request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getWrappedCancelUnbondingDelegationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WrappedEditValidator defines a method for editing the validator
     * information.
     * </pre>
     */
    public void wrappedEditValidator(com.babylon.epoching.v1.TxProto.MsgWrappedEditValidator request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedEditValidatorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getWrappedEditValidatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WrappedStakingUpdateParams defines a method for update the parameters
     * of the x/staking module.
     * </pre>
     */
    public void wrappedStakingUpdateParams(com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParams request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getWrappedStakingUpdateParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateParams defines a method for updating epoching module parameters.
     * </pre>
     */
    public void updateParams(com.babylon.epoching.v1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the Msg service.
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
     * WrappedDelegate defines a method for performing a delegation of coins from
     * a delegator to a validator.
     * </pre>
     */
    public com.babylon.epoching.v1.TxProto.MsgWrappedDelegateResponse wrappedDelegate(com.babylon.epoching.v1.TxProto.MsgWrappedDelegate request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getWrappedDelegateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WrappedUndelegate defines a method for performing an undelegation from a
     * delegate and a validator.
     * </pre>
     */
    public com.babylon.epoching.v1.TxProto.MsgWrappedUndelegateResponse wrappedUndelegate(com.babylon.epoching.v1.TxProto.MsgWrappedUndelegate request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getWrappedUndelegateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WrappedBeginRedelegate defines a method for performing a redelegation of
     * coins from a delegator and source validator to a destination validator.
     * </pre>
     */
    public com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegateResponse wrappedBeginRedelegate(com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegate request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getWrappedBeginRedelegateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WrappedCancelUnbondingDelegation defines a method for cancelling unbonding of
     * coins from a delegator and source validator to a destination validator.
     * </pre>
     */
    public com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegationResponse wrappedCancelUnbondingDelegation(com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegation request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getWrappedCancelUnbondingDelegationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WrappedEditValidator defines a method for editing the validator
     * information.
     * </pre>
     */
    public com.babylon.epoching.v1.TxProto.MsgWrappedEditValidatorResponse wrappedEditValidator(com.babylon.epoching.v1.TxProto.MsgWrappedEditValidator request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getWrappedEditValidatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WrappedStakingUpdateParams defines a method for update the parameters
     * of the x/staking module.
     * </pre>
     */
    public com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParamsResponse wrappedStakingUpdateParams(com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getWrappedStakingUpdateParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateParams defines a method for updating epoching module parameters.
     * </pre>
     */
    public com.babylon.epoching.v1.TxProto.MsgUpdateParamsResponse updateParams(com.babylon.epoching.v1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the Msg service.
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
     * WrappedDelegate defines a method for performing a delegation of coins from
     * a delegator to a validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.epoching.v1.TxProto.MsgWrappedDelegateResponse> wrappedDelegate(
        com.babylon.epoching.v1.TxProto.MsgWrappedDelegate request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getWrappedDelegateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WrappedUndelegate defines a method for performing an undelegation from a
     * delegate and a validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.epoching.v1.TxProto.MsgWrappedUndelegateResponse> wrappedUndelegate(
        com.babylon.epoching.v1.TxProto.MsgWrappedUndelegate request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getWrappedUndelegateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WrappedBeginRedelegate defines a method for performing a redelegation of
     * coins from a delegator and source validator to a destination validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegateResponse> wrappedBeginRedelegate(
        com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegate request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getWrappedBeginRedelegateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WrappedCancelUnbondingDelegation defines a method for cancelling unbonding of
     * coins from a delegator and source validator to a destination validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegationResponse> wrappedCancelUnbondingDelegation(
        com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegation request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getWrappedCancelUnbondingDelegationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WrappedEditValidator defines a method for editing the validator
     * information.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.epoching.v1.TxProto.MsgWrappedEditValidatorResponse> wrappedEditValidator(
        com.babylon.epoching.v1.TxProto.MsgWrappedEditValidator request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getWrappedEditValidatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WrappedStakingUpdateParams defines a method for update the parameters
     * of the x/staking module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParamsResponse> wrappedStakingUpdateParams(
        com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getWrappedStakingUpdateParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateParams defines a method for updating epoching module parameters.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.epoching.v1.TxProto.MsgUpdateParamsResponse> updateParams(
        com.babylon.epoching.v1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_WRAPPED_DELEGATE = 0;
  private static final int METHODID_WRAPPED_UNDELEGATE = 1;
  private static final int METHODID_WRAPPED_BEGIN_REDELEGATE = 2;
  private static final int METHODID_WRAPPED_CANCEL_UNBONDING_DELEGATION = 3;
  private static final int METHODID_WRAPPED_EDIT_VALIDATOR = 4;
  private static final int METHODID_WRAPPED_STAKING_UPDATE_PARAMS = 5;
  private static final int METHODID_UPDATE_PARAMS = 6;

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
        case METHODID_WRAPPED_DELEGATE:
          serviceImpl.wrappedDelegate((com.babylon.epoching.v1.TxProto.MsgWrappedDelegate) request,
              (io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedDelegateResponse>) responseObserver);
          break;
        case METHODID_WRAPPED_UNDELEGATE:
          serviceImpl.wrappedUndelegate((com.babylon.epoching.v1.TxProto.MsgWrappedUndelegate) request,
              (io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedUndelegateResponse>) responseObserver);
          break;
        case METHODID_WRAPPED_BEGIN_REDELEGATE:
          serviceImpl.wrappedBeginRedelegate((com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegate) request,
              (io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegateResponse>) responseObserver);
          break;
        case METHODID_WRAPPED_CANCEL_UNBONDING_DELEGATION:
          serviceImpl.wrappedCancelUnbondingDelegation((com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegation) request,
              (io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegationResponse>) responseObserver);
          break;
        case METHODID_WRAPPED_EDIT_VALIDATOR:
          serviceImpl.wrappedEditValidator((com.babylon.epoching.v1.TxProto.MsgWrappedEditValidator) request,
              (io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedEditValidatorResponse>) responseObserver);
          break;
        case METHODID_WRAPPED_STAKING_UPDATE_PARAMS:
          serviceImpl.wrappedStakingUpdateParams((com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParams) request,
              (io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParamsResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PARAMS:
          serviceImpl.updateParams((com.babylon.epoching.v1.TxProto.MsgUpdateParams) request,
              (io.grpc.stub.StreamObserver<com.babylon.epoching.v1.TxProto.MsgUpdateParamsResponse>) responseObserver);
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
          getWrappedDelegateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.epoching.v1.TxProto.MsgWrappedDelegate,
              com.babylon.epoching.v1.TxProto.MsgWrappedDelegateResponse>(
                service, METHODID_WRAPPED_DELEGATE)))
        .addMethod(
          getWrappedUndelegateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.epoching.v1.TxProto.MsgWrappedUndelegate,
              com.babylon.epoching.v1.TxProto.MsgWrappedUndelegateResponse>(
                service, METHODID_WRAPPED_UNDELEGATE)))
        .addMethod(
          getWrappedBeginRedelegateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegate,
              com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegateResponse>(
                service, METHODID_WRAPPED_BEGIN_REDELEGATE)))
        .addMethod(
          getWrappedCancelUnbondingDelegationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegation,
              com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegationResponse>(
                service, METHODID_WRAPPED_CANCEL_UNBONDING_DELEGATION)))
        .addMethod(
          getWrappedEditValidatorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.epoching.v1.TxProto.MsgWrappedEditValidator,
              com.babylon.epoching.v1.TxProto.MsgWrappedEditValidatorResponse>(
                service, METHODID_WRAPPED_EDIT_VALIDATOR)))
        .addMethod(
          getWrappedStakingUpdateParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParams,
              com.babylon.epoching.v1.TxProto.MsgWrappedStakingUpdateParamsResponse>(
                service, METHODID_WRAPPED_STAKING_UPDATE_PARAMS)))
        .addMethod(
          getUpdateParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.epoching.v1.TxProto.MsgUpdateParams,
              com.babylon.epoching.v1.TxProto.MsgUpdateParamsResponse>(
                service, METHODID_UPDATE_PARAMS)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.babylon.epoching.v1.TxProto.getDescriptor();
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
              .addMethod(getWrappedDelegateMethod())
              .addMethod(getWrappedUndelegateMethod())
              .addMethod(getWrappedBeginRedelegateMethod())
              .addMethod(getWrappedCancelUnbondingDelegationMethod())
              .addMethod(getWrappedEditValidatorMethod())
              .addMethod(getWrappedStakingUpdateParamsMethod())
              .addMethod(getUpdateParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
