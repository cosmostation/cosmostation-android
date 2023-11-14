package com.kava.router.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the router Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: kava/router/v1beta1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.router.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kava.router.v1beta1.TxProto.MsgMintDeposit,
      com.kava.router.v1beta1.TxProto.MsgMintDepositResponse> getMintDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MintDeposit",
      requestType = com.kava.router.v1beta1.TxProto.MsgMintDeposit.class,
      responseType = com.kava.router.v1beta1.TxProto.MsgMintDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.router.v1beta1.TxProto.MsgMintDeposit,
      com.kava.router.v1beta1.TxProto.MsgMintDepositResponse> getMintDepositMethod() {
    io.grpc.MethodDescriptor<com.kava.router.v1beta1.TxProto.MsgMintDeposit, com.kava.router.v1beta1.TxProto.MsgMintDepositResponse> getMintDepositMethod;
    if ((getMintDepositMethod = MsgGrpc.getMintDepositMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMintDepositMethod = MsgGrpc.getMintDepositMethod) == null) {
          MsgGrpc.getMintDepositMethod = getMintDepositMethod =
              io.grpc.MethodDescriptor.<com.kava.router.v1beta1.TxProto.MsgMintDeposit, com.kava.router.v1beta1.TxProto.MsgMintDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MintDeposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.router.v1beta1.TxProto.MsgMintDeposit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.router.v1beta1.TxProto.MsgMintDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("MintDeposit"))
              .build();
        }
      }
    }
    return getMintDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit,
      com.kava.router.v1beta1.TxProto.MsgDelegateMintDepositResponse> getDelegateMintDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegateMintDeposit",
      requestType = com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit.class,
      responseType = com.kava.router.v1beta1.TxProto.MsgDelegateMintDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit,
      com.kava.router.v1beta1.TxProto.MsgDelegateMintDepositResponse> getDelegateMintDepositMethod() {
    io.grpc.MethodDescriptor<com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit, com.kava.router.v1beta1.TxProto.MsgDelegateMintDepositResponse> getDelegateMintDepositMethod;
    if ((getDelegateMintDepositMethod = MsgGrpc.getDelegateMintDepositMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDelegateMintDepositMethod = MsgGrpc.getDelegateMintDepositMethod) == null) {
          MsgGrpc.getDelegateMintDepositMethod = getDelegateMintDepositMethod =
              io.grpc.MethodDescriptor.<com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit, com.kava.router.v1beta1.TxProto.MsgDelegateMintDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegateMintDeposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.router.v1beta1.TxProto.MsgDelegateMintDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DelegateMintDeposit"))
              .build();
        }
      }
    }
    return getDelegateMintDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.router.v1beta1.TxProto.MsgWithdrawBurn,
      com.kava.router.v1beta1.TxProto.MsgWithdrawBurnResponse> getWithdrawBurnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawBurn",
      requestType = com.kava.router.v1beta1.TxProto.MsgWithdrawBurn.class,
      responseType = com.kava.router.v1beta1.TxProto.MsgWithdrawBurnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.router.v1beta1.TxProto.MsgWithdrawBurn,
      com.kava.router.v1beta1.TxProto.MsgWithdrawBurnResponse> getWithdrawBurnMethod() {
    io.grpc.MethodDescriptor<com.kava.router.v1beta1.TxProto.MsgWithdrawBurn, com.kava.router.v1beta1.TxProto.MsgWithdrawBurnResponse> getWithdrawBurnMethod;
    if ((getWithdrawBurnMethod = MsgGrpc.getWithdrawBurnMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawBurnMethod = MsgGrpc.getWithdrawBurnMethod) == null) {
          MsgGrpc.getWithdrawBurnMethod = getWithdrawBurnMethod =
              io.grpc.MethodDescriptor.<com.kava.router.v1beta1.TxProto.MsgWithdrawBurn, com.kava.router.v1beta1.TxProto.MsgWithdrawBurnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawBurn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.router.v1beta1.TxProto.MsgWithdrawBurn.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.router.v1beta1.TxProto.MsgWithdrawBurnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawBurn"))
              .build();
        }
      }
    }
    return getWithdrawBurnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegate,
      com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegateResponse> getWithdrawBurnUndelegateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawBurnUndelegate",
      requestType = com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegate.class,
      responseType = com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegate,
      com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegateResponse> getWithdrawBurnUndelegateMethod() {
    io.grpc.MethodDescriptor<com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegate, com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegateResponse> getWithdrawBurnUndelegateMethod;
    if ((getWithdrawBurnUndelegateMethod = MsgGrpc.getWithdrawBurnUndelegateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawBurnUndelegateMethod = MsgGrpc.getWithdrawBurnUndelegateMethod) == null) {
          MsgGrpc.getWithdrawBurnUndelegateMethod = getWithdrawBurnUndelegateMethod =
              io.grpc.MethodDescriptor.<com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegate, com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawBurnUndelegate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawBurnUndelegate"))
              .build();
        }
      }
    }
    return getWithdrawBurnUndelegateMethod;
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
   * Msg defines the router Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * MintDeposit converts a delegation into staking derivatives and deposits it all into an earn vault.
     * </pre>
     */
    default void mintDeposit(com.kava.router.v1beta1.TxProto.MsgMintDeposit request,
        io.grpc.stub.StreamObserver<com.kava.router.v1beta1.TxProto.MsgMintDepositResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMintDepositMethod(), responseObserver);
    }

    /**
     * <pre>
     * DelegateMintDeposit delegates tokens to a validator, then converts them into staking derivatives,
     * then deposits to an earn vault.
     * </pre>
     */
    default void delegateMintDeposit(com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit request,
        io.grpc.stub.StreamObserver<com.kava.router.v1beta1.TxProto.MsgDelegateMintDepositResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDelegateMintDepositMethod(), responseObserver);
    }

    /**
     * <pre>
     * WithdrawBurn removes staking derivatives from an earn vault and converts them back to a staking delegation.
     * </pre>
     */
    default void withdrawBurn(com.kava.router.v1beta1.TxProto.MsgWithdrawBurn request,
        io.grpc.stub.StreamObserver<com.kava.router.v1beta1.TxProto.MsgWithdrawBurnResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWithdrawBurnMethod(), responseObserver);
    }

    /**
     * <pre>
     * WithdrawBurnUndelegate removes staking derivatives from an earn vault, converts them to a staking delegation,
     * then undelegates them from their validator.
     * </pre>
     */
    default void withdrawBurnUndelegate(com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegate request,
        io.grpc.stub.StreamObserver<com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWithdrawBurnUndelegateMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the router Msg service.
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
   * Msg defines the router Msg service.
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
     * MintDeposit converts a delegation into staking derivatives and deposits it all into an earn vault.
     * </pre>
     */
    public void mintDeposit(com.kava.router.v1beta1.TxProto.MsgMintDeposit request,
        io.grpc.stub.StreamObserver<com.kava.router.v1beta1.TxProto.MsgMintDepositResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMintDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DelegateMintDeposit delegates tokens to a validator, then converts them into staking derivatives,
     * then deposits to an earn vault.
     * </pre>
     */
    public void delegateMintDeposit(com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit request,
        io.grpc.stub.StreamObserver<com.kava.router.v1beta1.TxProto.MsgDelegateMintDepositResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDelegateMintDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WithdrawBurn removes staking derivatives from an earn vault and converts them back to a staking delegation.
     * </pre>
     */
    public void withdrawBurn(com.kava.router.v1beta1.TxProto.MsgWithdrawBurn request,
        io.grpc.stub.StreamObserver<com.kava.router.v1beta1.TxProto.MsgWithdrawBurnResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getWithdrawBurnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WithdrawBurnUndelegate removes staking derivatives from an earn vault, converts them to a staking delegation,
     * then undelegates them from their validator.
     * </pre>
     */
    public void withdrawBurnUndelegate(com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegate request,
        io.grpc.stub.StreamObserver<com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getWithdrawBurnUndelegateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the router Msg service.
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
     * MintDeposit converts a delegation into staking derivatives and deposits it all into an earn vault.
     * </pre>
     */
    public com.kava.router.v1beta1.TxProto.MsgMintDepositResponse mintDeposit(com.kava.router.v1beta1.TxProto.MsgMintDeposit request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMintDepositMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DelegateMintDeposit delegates tokens to a validator, then converts them into staking derivatives,
     * then deposits to an earn vault.
     * </pre>
     */
    public com.kava.router.v1beta1.TxProto.MsgDelegateMintDepositResponse delegateMintDeposit(com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDelegateMintDepositMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WithdrawBurn removes staking derivatives from an earn vault and converts them back to a staking delegation.
     * </pre>
     */
    public com.kava.router.v1beta1.TxProto.MsgWithdrawBurnResponse withdrawBurn(com.kava.router.v1beta1.TxProto.MsgWithdrawBurn request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getWithdrawBurnMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WithdrawBurnUndelegate removes staking derivatives from an earn vault, converts them to a staking delegation,
     * then undelegates them from their validator.
     * </pre>
     */
    public com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegateResponse withdrawBurnUndelegate(com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegate request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getWithdrawBurnUndelegateMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the router Msg service.
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
     * MintDeposit converts a delegation into staking derivatives and deposits it all into an earn vault.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.router.v1beta1.TxProto.MsgMintDepositResponse> mintDeposit(
        com.kava.router.v1beta1.TxProto.MsgMintDeposit request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMintDepositMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DelegateMintDeposit delegates tokens to a validator, then converts them into staking derivatives,
     * then deposits to an earn vault.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.router.v1beta1.TxProto.MsgDelegateMintDepositResponse> delegateMintDeposit(
        com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDelegateMintDepositMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WithdrawBurn removes staking derivatives from an earn vault and converts them back to a staking delegation.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.router.v1beta1.TxProto.MsgWithdrawBurnResponse> withdrawBurn(
        com.kava.router.v1beta1.TxProto.MsgWithdrawBurn request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getWithdrawBurnMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WithdrawBurnUndelegate removes staking derivatives from an earn vault, converts them to a staking delegation,
     * then undelegates them from their validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegateResponse> withdrawBurnUndelegate(
        com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegate request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getWithdrawBurnUndelegateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MINT_DEPOSIT = 0;
  private static final int METHODID_DELEGATE_MINT_DEPOSIT = 1;
  private static final int METHODID_WITHDRAW_BURN = 2;
  private static final int METHODID_WITHDRAW_BURN_UNDELEGATE = 3;

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
        case METHODID_MINT_DEPOSIT:
          serviceImpl.mintDeposit((com.kava.router.v1beta1.TxProto.MsgMintDeposit) request,
              (io.grpc.stub.StreamObserver<com.kava.router.v1beta1.TxProto.MsgMintDepositResponse>) responseObserver);
          break;
        case METHODID_DELEGATE_MINT_DEPOSIT:
          serviceImpl.delegateMintDeposit((com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit) request,
              (io.grpc.stub.StreamObserver<com.kava.router.v1beta1.TxProto.MsgDelegateMintDepositResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_BURN:
          serviceImpl.withdrawBurn((com.kava.router.v1beta1.TxProto.MsgWithdrawBurn) request,
              (io.grpc.stub.StreamObserver<com.kava.router.v1beta1.TxProto.MsgWithdrawBurnResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_BURN_UNDELEGATE:
          serviceImpl.withdrawBurnUndelegate((com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegate) request,
              (io.grpc.stub.StreamObserver<com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegateResponse>) responseObserver);
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
          getMintDepositMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.router.v1beta1.TxProto.MsgMintDeposit,
              com.kava.router.v1beta1.TxProto.MsgMintDepositResponse>(
                service, METHODID_MINT_DEPOSIT)))
        .addMethod(
          getDelegateMintDepositMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit,
              com.kava.router.v1beta1.TxProto.MsgDelegateMintDepositResponse>(
                service, METHODID_DELEGATE_MINT_DEPOSIT)))
        .addMethod(
          getWithdrawBurnMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.router.v1beta1.TxProto.MsgWithdrawBurn,
              com.kava.router.v1beta1.TxProto.MsgWithdrawBurnResponse>(
                service, METHODID_WITHDRAW_BURN)))
        .addMethod(
          getWithdrawBurnUndelegateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegate,
              com.kava.router.v1beta1.TxProto.MsgWithdrawBurnUndelegateResponse>(
                service, METHODID_WITHDRAW_BURN_UNDELEGATE)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kava.router.v1beta1.TxProto.getDescriptor();
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
              .addMethod(getMintDepositMethod())
              .addMethod(getDelegateMintDepositMethod())
              .addMethod(getWithdrawBurnMethod())
              .addMethod(getWithdrawBurnUndelegateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
