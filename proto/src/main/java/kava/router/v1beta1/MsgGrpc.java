package kava.router.v1beta1;

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
 * Msg defines the router Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/router/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.router.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.router.v1beta1.Tx.MsgMintDeposit,
      kava.router.v1beta1.Tx.MsgMintDepositResponse> getMintDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MintDeposit",
      requestType = kava.router.v1beta1.Tx.MsgMintDeposit.class,
      responseType = kava.router.v1beta1.Tx.MsgMintDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.router.v1beta1.Tx.MsgMintDeposit,
      kava.router.v1beta1.Tx.MsgMintDepositResponse> getMintDepositMethod() {
    io.grpc.MethodDescriptor<kava.router.v1beta1.Tx.MsgMintDeposit, kava.router.v1beta1.Tx.MsgMintDepositResponse> getMintDepositMethod;
    if ((getMintDepositMethod = MsgGrpc.getMintDepositMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMintDepositMethod = MsgGrpc.getMintDepositMethod) == null) {
          MsgGrpc.getMintDepositMethod = getMintDepositMethod =
              io.grpc.MethodDescriptor.<kava.router.v1beta1.Tx.MsgMintDeposit, kava.router.v1beta1.Tx.MsgMintDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MintDeposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.router.v1beta1.Tx.MsgMintDeposit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.router.v1beta1.Tx.MsgMintDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("MintDeposit"))
              .build();
        }
      }
    }
    return getMintDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.router.v1beta1.Tx.MsgDelegateMintDeposit,
      kava.router.v1beta1.Tx.MsgDelegateMintDepositResponse> getDelegateMintDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegateMintDeposit",
      requestType = kava.router.v1beta1.Tx.MsgDelegateMintDeposit.class,
      responseType = kava.router.v1beta1.Tx.MsgDelegateMintDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.router.v1beta1.Tx.MsgDelegateMintDeposit,
      kava.router.v1beta1.Tx.MsgDelegateMintDepositResponse> getDelegateMintDepositMethod() {
    io.grpc.MethodDescriptor<kava.router.v1beta1.Tx.MsgDelegateMintDeposit, kava.router.v1beta1.Tx.MsgDelegateMintDepositResponse> getDelegateMintDepositMethod;
    if ((getDelegateMintDepositMethod = MsgGrpc.getDelegateMintDepositMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDelegateMintDepositMethod = MsgGrpc.getDelegateMintDepositMethod) == null) {
          MsgGrpc.getDelegateMintDepositMethod = getDelegateMintDepositMethod =
              io.grpc.MethodDescriptor.<kava.router.v1beta1.Tx.MsgDelegateMintDeposit, kava.router.v1beta1.Tx.MsgDelegateMintDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegateMintDeposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.router.v1beta1.Tx.MsgDelegateMintDeposit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.router.v1beta1.Tx.MsgDelegateMintDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DelegateMintDeposit"))
              .build();
        }
      }
    }
    return getDelegateMintDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.router.v1beta1.Tx.MsgWithdrawBurn,
      kava.router.v1beta1.Tx.MsgWithdrawBurnResponse> getWithdrawBurnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawBurn",
      requestType = kava.router.v1beta1.Tx.MsgWithdrawBurn.class,
      responseType = kava.router.v1beta1.Tx.MsgWithdrawBurnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.router.v1beta1.Tx.MsgWithdrawBurn,
      kava.router.v1beta1.Tx.MsgWithdrawBurnResponse> getWithdrawBurnMethod() {
    io.grpc.MethodDescriptor<kava.router.v1beta1.Tx.MsgWithdrawBurn, kava.router.v1beta1.Tx.MsgWithdrawBurnResponse> getWithdrawBurnMethod;
    if ((getWithdrawBurnMethod = MsgGrpc.getWithdrawBurnMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawBurnMethod = MsgGrpc.getWithdrawBurnMethod) == null) {
          MsgGrpc.getWithdrawBurnMethod = getWithdrawBurnMethod =
              io.grpc.MethodDescriptor.<kava.router.v1beta1.Tx.MsgWithdrawBurn, kava.router.v1beta1.Tx.MsgWithdrawBurnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawBurn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.router.v1beta1.Tx.MsgWithdrawBurn.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.router.v1beta1.Tx.MsgWithdrawBurnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawBurn"))
              .build();
        }
      }
    }
    return getWithdrawBurnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegate,
      kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegateResponse> getWithdrawBurnUndelegateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawBurnUndelegate",
      requestType = kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegate.class,
      responseType = kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegate,
      kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegateResponse> getWithdrawBurnUndelegateMethod() {
    io.grpc.MethodDescriptor<kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegate, kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegateResponse> getWithdrawBurnUndelegateMethod;
    if ((getWithdrawBurnUndelegateMethod = MsgGrpc.getWithdrawBurnUndelegateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawBurnUndelegateMethod = MsgGrpc.getWithdrawBurnUndelegateMethod) == null) {
          MsgGrpc.getWithdrawBurnUndelegateMethod = getWithdrawBurnUndelegateMethod =
              io.grpc.MethodDescriptor.<kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegate, kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawBurnUndelegate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegateResponse.getDefaultInstance()))
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
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * MintDeposit converts a delegation into staking derivatives and deposits it all into an earn vault.
     * </pre>
     */
    public void mintDeposit(kava.router.v1beta1.Tx.MsgMintDeposit request,
        io.grpc.stub.StreamObserver<kava.router.v1beta1.Tx.MsgMintDepositResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMintDepositMethod(), responseObserver);
    }

    /**
     * <pre>
     * DelegateMintDeposit delegates tokens to a validator, then converts them into staking derivatives,
     * then deposits to an earn vault.
     * </pre>
     */
    public void delegateMintDeposit(kava.router.v1beta1.Tx.MsgDelegateMintDeposit request,
        io.grpc.stub.StreamObserver<kava.router.v1beta1.Tx.MsgDelegateMintDepositResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDelegateMintDepositMethod(), responseObserver);
    }

    /**
     * <pre>
     * WithdrawBurn removes staking derivatives from an earn vault and converts them back to a staking delegation.
     * </pre>
     */
    public void withdrawBurn(kava.router.v1beta1.Tx.MsgWithdrawBurn request,
        io.grpc.stub.StreamObserver<kava.router.v1beta1.Tx.MsgWithdrawBurnResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawBurnMethod(), responseObserver);
    }

    /**
     * <pre>
     * WithdrawBurnUndelegate removes staking derivatives from an earn vault, converts them to a staking delegation,
     * then undelegates them from their validator.
     * </pre>
     */
    public void withdrawBurnUndelegate(kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegate request,
        io.grpc.stub.StreamObserver<kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawBurnUndelegateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMintDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.router.v1beta1.Tx.MsgMintDeposit,
                kava.router.v1beta1.Tx.MsgMintDepositResponse>(
                  this, METHODID_MINT_DEPOSIT)))
          .addMethod(
            getDelegateMintDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.router.v1beta1.Tx.MsgDelegateMintDeposit,
                kava.router.v1beta1.Tx.MsgDelegateMintDepositResponse>(
                  this, METHODID_DELEGATE_MINT_DEPOSIT)))
          .addMethod(
            getWithdrawBurnMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.router.v1beta1.Tx.MsgWithdrawBurn,
                kava.router.v1beta1.Tx.MsgWithdrawBurnResponse>(
                  this, METHODID_WITHDRAW_BURN)))
          .addMethod(
            getWithdrawBurnUndelegateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegate,
                kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegateResponse>(
                  this, METHODID_WITHDRAW_BURN_UNDELEGATE)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the router Msg service.
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
     * MintDeposit converts a delegation into staking derivatives and deposits it all into an earn vault.
     * </pre>
     */
    public void mintDeposit(kava.router.v1beta1.Tx.MsgMintDeposit request,
        io.grpc.stub.StreamObserver<kava.router.v1beta1.Tx.MsgMintDepositResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMintDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DelegateMintDeposit delegates tokens to a validator, then converts them into staking derivatives,
     * then deposits to an earn vault.
     * </pre>
     */
    public void delegateMintDeposit(kava.router.v1beta1.Tx.MsgDelegateMintDeposit request,
        io.grpc.stub.StreamObserver<kava.router.v1beta1.Tx.MsgDelegateMintDepositResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDelegateMintDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WithdrawBurn removes staking derivatives from an earn vault and converts them back to a staking delegation.
     * </pre>
     */
    public void withdrawBurn(kava.router.v1beta1.Tx.MsgWithdrawBurn request,
        io.grpc.stub.StreamObserver<kava.router.v1beta1.Tx.MsgWithdrawBurnResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawBurnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WithdrawBurnUndelegate removes staking derivatives from an earn vault, converts them to a staking delegation,
     * then undelegates them from their validator.
     * </pre>
     */
    public void withdrawBurnUndelegate(kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegate request,
        io.grpc.stub.StreamObserver<kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawBurnUndelegateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the router Msg service.
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
     * MintDeposit converts a delegation into staking derivatives and deposits it all into an earn vault.
     * </pre>
     */
    public kava.router.v1beta1.Tx.MsgMintDepositResponse mintDeposit(kava.router.v1beta1.Tx.MsgMintDeposit request) {
      return blockingUnaryCall(
          getChannel(), getMintDepositMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DelegateMintDeposit delegates tokens to a validator, then converts them into staking derivatives,
     * then deposits to an earn vault.
     * </pre>
     */
    public kava.router.v1beta1.Tx.MsgDelegateMintDepositResponse delegateMintDeposit(kava.router.v1beta1.Tx.MsgDelegateMintDeposit request) {
      return blockingUnaryCall(
          getChannel(), getDelegateMintDepositMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WithdrawBurn removes staking derivatives from an earn vault and converts them back to a staking delegation.
     * </pre>
     */
    public kava.router.v1beta1.Tx.MsgWithdrawBurnResponse withdrawBurn(kava.router.v1beta1.Tx.MsgWithdrawBurn request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawBurnMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WithdrawBurnUndelegate removes staking derivatives from an earn vault, converts them to a staking delegation,
     * then undelegates them from their validator.
     * </pre>
     */
    public kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegateResponse withdrawBurnUndelegate(kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegate request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawBurnUndelegateMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the router Msg service.
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
     * MintDeposit converts a delegation into staking derivatives and deposits it all into an earn vault.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.router.v1beta1.Tx.MsgMintDepositResponse> mintDeposit(
        kava.router.v1beta1.Tx.MsgMintDeposit request) {
      return futureUnaryCall(
          getChannel().newCall(getMintDepositMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DelegateMintDeposit delegates tokens to a validator, then converts them into staking derivatives,
     * then deposits to an earn vault.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.router.v1beta1.Tx.MsgDelegateMintDepositResponse> delegateMintDeposit(
        kava.router.v1beta1.Tx.MsgDelegateMintDeposit request) {
      return futureUnaryCall(
          getChannel().newCall(getDelegateMintDepositMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WithdrawBurn removes staking derivatives from an earn vault and converts them back to a staking delegation.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.router.v1beta1.Tx.MsgWithdrawBurnResponse> withdrawBurn(
        kava.router.v1beta1.Tx.MsgWithdrawBurn request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawBurnMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WithdrawBurnUndelegate removes staking derivatives from an earn vault, converts them to a staking delegation,
     * then undelegates them from their validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegateResponse> withdrawBurnUndelegate(
        kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegate request) {
      return futureUnaryCall(
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
        case METHODID_MINT_DEPOSIT:
          serviceImpl.mintDeposit((kava.router.v1beta1.Tx.MsgMintDeposit) request,
              (io.grpc.stub.StreamObserver<kava.router.v1beta1.Tx.MsgMintDepositResponse>) responseObserver);
          break;
        case METHODID_DELEGATE_MINT_DEPOSIT:
          serviceImpl.delegateMintDeposit((kava.router.v1beta1.Tx.MsgDelegateMintDeposit) request,
              (io.grpc.stub.StreamObserver<kava.router.v1beta1.Tx.MsgDelegateMintDepositResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_BURN:
          serviceImpl.withdrawBurn((kava.router.v1beta1.Tx.MsgWithdrawBurn) request,
              (io.grpc.stub.StreamObserver<kava.router.v1beta1.Tx.MsgWithdrawBurnResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_BURN_UNDELEGATE:
          serviceImpl.withdrawBurnUndelegate((kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegate) request,
              (io.grpc.stub.StreamObserver<kava.router.v1beta1.Tx.MsgWithdrawBurnUndelegateResponse>) responseObserver);
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
      return kava.router.v1beta1.Tx.getDescriptor();
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
