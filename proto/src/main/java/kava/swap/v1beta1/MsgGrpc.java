package kava.swap.v1beta1;

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
 * Msg defines the swap Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/swap/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.swap.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.swap.v1beta1.Tx.MsgDeposit,
      kava.swap.v1beta1.Tx.MsgDepositResponse> getDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposit",
      requestType = kava.swap.v1beta1.Tx.MsgDeposit.class,
      responseType = kava.swap.v1beta1.Tx.MsgDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.swap.v1beta1.Tx.MsgDeposit,
      kava.swap.v1beta1.Tx.MsgDepositResponse> getDepositMethod() {
    io.grpc.MethodDescriptor<kava.swap.v1beta1.Tx.MsgDeposit, kava.swap.v1beta1.Tx.MsgDepositResponse> getDepositMethod;
    if ((getDepositMethod = MsgGrpc.getDepositMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDepositMethod = MsgGrpc.getDepositMethod) == null) {
          MsgGrpc.getDepositMethod = getDepositMethod =
              io.grpc.MethodDescriptor.<kava.swap.v1beta1.Tx.MsgDeposit, kava.swap.v1beta1.Tx.MsgDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.swap.v1beta1.Tx.MsgDeposit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.swap.v1beta1.Tx.MsgDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Deposit"))
              .build();
        }
      }
    }
    return getDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.swap.v1beta1.Tx.MsgWithdraw,
      kava.swap.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Withdraw",
      requestType = kava.swap.v1beta1.Tx.MsgWithdraw.class,
      responseType = kava.swap.v1beta1.Tx.MsgWithdrawResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.swap.v1beta1.Tx.MsgWithdraw,
      kava.swap.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod() {
    io.grpc.MethodDescriptor<kava.swap.v1beta1.Tx.MsgWithdraw, kava.swap.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod;
    if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
          MsgGrpc.getWithdrawMethod = getWithdrawMethod =
              io.grpc.MethodDescriptor.<kava.swap.v1beta1.Tx.MsgWithdraw, kava.swap.v1beta1.Tx.MsgWithdrawResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Withdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.swap.v1beta1.Tx.MsgWithdraw.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.swap.v1beta1.Tx.MsgWithdrawResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Withdraw"))
              .build();
        }
      }
    }
    return getWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.swap.v1beta1.Tx.MsgSwapExactForTokens,
      kava.swap.v1beta1.Tx.MsgSwapExactForTokensResponse> getSwapExactForTokensMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SwapExactForTokens",
      requestType = kava.swap.v1beta1.Tx.MsgSwapExactForTokens.class,
      responseType = kava.swap.v1beta1.Tx.MsgSwapExactForTokensResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.swap.v1beta1.Tx.MsgSwapExactForTokens,
      kava.swap.v1beta1.Tx.MsgSwapExactForTokensResponse> getSwapExactForTokensMethod() {
    io.grpc.MethodDescriptor<kava.swap.v1beta1.Tx.MsgSwapExactForTokens, kava.swap.v1beta1.Tx.MsgSwapExactForTokensResponse> getSwapExactForTokensMethod;
    if ((getSwapExactForTokensMethod = MsgGrpc.getSwapExactForTokensMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSwapExactForTokensMethod = MsgGrpc.getSwapExactForTokensMethod) == null) {
          MsgGrpc.getSwapExactForTokensMethod = getSwapExactForTokensMethod =
              io.grpc.MethodDescriptor.<kava.swap.v1beta1.Tx.MsgSwapExactForTokens, kava.swap.v1beta1.Tx.MsgSwapExactForTokensResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SwapExactForTokens"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.swap.v1beta1.Tx.MsgSwapExactForTokens.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.swap.v1beta1.Tx.MsgSwapExactForTokensResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SwapExactForTokens"))
              .build();
        }
      }
    }
    return getSwapExactForTokensMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.swap.v1beta1.Tx.MsgSwapForExactTokens,
      kava.swap.v1beta1.Tx.MsgSwapForExactTokensResponse> getSwapForExactTokensMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SwapForExactTokens",
      requestType = kava.swap.v1beta1.Tx.MsgSwapForExactTokens.class,
      responseType = kava.swap.v1beta1.Tx.MsgSwapForExactTokensResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.swap.v1beta1.Tx.MsgSwapForExactTokens,
      kava.swap.v1beta1.Tx.MsgSwapForExactTokensResponse> getSwapForExactTokensMethod() {
    io.grpc.MethodDescriptor<kava.swap.v1beta1.Tx.MsgSwapForExactTokens, kava.swap.v1beta1.Tx.MsgSwapForExactTokensResponse> getSwapForExactTokensMethod;
    if ((getSwapForExactTokensMethod = MsgGrpc.getSwapForExactTokensMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSwapForExactTokensMethod = MsgGrpc.getSwapForExactTokensMethod) == null) {
          MsgGrpc.getSwapForExactTokensMethod = getSwapForExactTokensMethod =
              io.grpc.MethodDescriptor.<kava.swap.v1beta1.Tx.MsgSwapForExactTokens, kava.swap.v1beta1.Tx.MsgSwapForExactTokensResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SwapForExactTokens"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.swap.v1beta1.Tx.MsgSwapForExactTokens.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.swap.v1beta1.Tx.MsgSwapForExactTokensResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SwapForExactTokens"))
              .build();
        }
      }
    }
    return getSwapForExactTokensMethod;
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
   * Msg defines the swap Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Deposit defines a method for depositing liquidity into a pool
     * </pre>
     */
    public void deposit(kava.swap.v1beta1.Tx.MsgDeposit request,
        io.grpc.stub.StreamObserver<kava.swap.v1beta1.Tx.MsgDepositResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositMethod(), responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing liquidity into a pool
     * </pre>
     */
    public void withdraw(kava.swap.v1beta1.Tx.MsgWithdraw request,
        io.grpc.stub.StreamObserver<kava.swap.v1beta1.Tx.MsgWithdrawResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawMethod(), responseObserver);
    }

    /**
     * <pre>
     * SwapExactForTokens represents a message for trading exact coinA for coinB
     * </pre>
     */
    public void swapExactForTokens(kava.swap.v1beta1.Tx.MsgSwapExactForTokens request,
        io.grpc.stub.StreamObserver<kava.swap.v1beta1.Tx.MsgSwapExactForTokensResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwapExactForTokensMethod(), responseObserver);
    }

    /**
     * <pre>
     * SwapForExactTokens represents a message for trading coinA for an exact coinB
     * </pre>
     */
    public void swapForExactTokens(kava.swap.v1beta1.Tx.MsgSwapForExactTokens request,
        io.grpc.stub.StreamObserver<kava.swap.v1beta1.Tx.MsgSwapForExactTokensResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwapForExactTokensMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.swap.v1beta1.Tx.MsgDeposit,
                kava.swap.v1beta1.Tx.MsgDepositResponse>(
                  this, METHODID_DEPOSIT)))
          .addMethod(
            getWithdrawMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.swap.v1beta1.Tx.MsgWithdraw,
                kava.swap.v1beta1.Tx.MsgWithdrawResponse>(
                  this, METHODID_WITHDRAW)))
          .addMethod(
            getSwapExactForTokensMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.swap.v1beta1.Tx.MsgSwapExactForTokens,
                kava.swap.v1beta1.Tx.MsgSwapExactForTokensResponse>(
                  this, METHODID_SWAP_EXACT_FOR_TOKENS)))
          .addMethod(
            getSwapForExactTokensMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.swap.v1beta1.Tx.MsgSwapForExactTokens,
                kava.swap.v1beta1.Tx.MsgSwapForExactTokensResponse>(
                  this, METHODID_SWAP_FOR_EXACT_TOKENS)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the swap Msg service.
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
     * Deposit defines a method for depositing liquidity into a pool
     * </pre>
     */
    public void deposit(kava.swap.v1beta1.Tx.MsgDeposit request,
        io.grpc.stub.StreamObserver<kava.swap.v1beta1.Tx.MsgDepositResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing liquidity into a pool
     * </pre>
     */
    public void withdraw(kava.swap.v1beta1.Tx.MsgWithdraw request,
        io.grpc.stub.StreamObserver<kava.swap.v1beta1.Tx.MsgWithdrawResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SwapExactForTokens represents a message for trading exact coinA for coinB
     * </pre>
     */
    public void swapExactForTokens(kava.swap.v1beta1.Tx.MsgSwapExactForTokens request,
        io.grpc.stub.StreamObserver<kava.swap.v1beta1.Tx.MsgSwapExactForTokensResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwapExactForTokensMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SwapForExactTokens represents a message for trading coinA for an exact coinB
     * </pre>
     */
    public void swapForExactTokens(kava.swap.v1beta1.Tx.MsgSwapForExactTokens request,
        io.grpc.stub.StreamObserver<kava.swap.v1beta1.Tx.MsgSwapForExactTokensResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwapForExactTokensMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the swap Msg service.
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
     * Deposit defines a method for depositing liquidity into a pool
     * </pre>
     */
    public kava.swap.v1beta1.Tx.MsgDepositResponse deposit(kava.swap.v1beta1.Tx.MsgDeposit request) {
      return blockingUnaryCall(
          getChannel(), getDepositMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing liquidity into a pool
     * </pre>
     */
    public kava.swap.v1beta1.Tx.MsgWithdrawResponse withdraw(kava.swap.v1beta1.Tx.MsgWithdraw request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SwapExactForTokens represents a message for trading exact coinA for coinB
     * </pre>
     */
    public kava.swap.v1beta1.Tx.MsgSwapExactForTokensResponse swapExactForTokens(kava.swap.v1beta1.Tx.MsgSwapExactForTokens request) {
      return blockingUnaryCall(
          getChannel(), getSwapExactForTokensMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SwapForExactTokens represents a message for trading coinA for an exact coinB
     * </pre>
     */
    public kava.swap.v1beta1.Tx.MsgSwapForExactTokensResponse swapForExactTokens(kava.swap.v1beta1.Tx.MsgSwapForExactTokens request) {
      return blockingUnaryCall(
          getChannel(), getSwapForExactTokensMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the swap Msg service.
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
     * Deposit defines a method for depositing liquidity into a pool
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.swap.v1beta1.Tx.MsgDepositResponse> deposit(
        kava.swap.v1beta1.Tx.MsgDeposit request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing liquidity into a pool
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.swap.v1beta1.Tx.MsgWithdrawResponse> withdraw(
        kava.swap.v1beta1.Tx.MsgWithdraw request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SwapExactForTokens represents a message for trading exact coinA for coinB
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.swap.v1beta1.Tx.MsgSwapExactForTokensResponse> swapExactForTokens(
        kava.swap.v1beta1.Tx.MsgSwapExactForTokens request) {
      return futureUnaryCall(
          getChannel().newCall(getSwapExactForTokensMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SwapForExactTokens represents a message for trading coinA for an exact coinB
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.swap.v1beta1.Tx.MsgSwapForExactTokensResponse> swapForExactTokens(
        kava.swap.v1beta1.Tx.MsgSwapForExactTokens request) {
      return futureUnaryCall(
          getChannel().newCall(getSwapForExactTokensMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DEPOSIT = 0;
  private static final int METHODID_WITHDRAW = 1;
  private static final int METHODID_SWAP_EXACT_FOR_TOKENS = 2;
  private static final int METHODID_SWAP_FOR_EXACT_TOKENS = 3;

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
        case METHODID_DEPOSIT:
          serviceImpl.deposit((kava.swap.v1beta1.Tx.MsgDeposit) request,
              (io.grpc.stub.StreamObserver<kava.swap.v1beta1.Tx.MsgDepositResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW:
          serviceImpl.withdraw((kava.swap.v1beta1.Tx.MsgWithdraw) request,
              (io.grpc.stub.StreamObserver<kava.swap.v1beta1.Tx.MsgWithdrawResponse>) responseObserver);
          break;
        case METHODID_SWAP_EXACT_FOR_TOKENS:
          serviceImpl.swapExactForTokens((kava.swap.v1beta1.Tx.MsgSwapExactForTokens) request,
              (io.grpc.stub.StreamObserver<kava.swap.v1beta1.Tx.MsgSwapExactForTokensResponse>) responseObserver);
          break;
        case METHODID_SWAP_FOR_EXACT_TOKENS:
          serviceImpl.swapForExactTokens((kava.swap.v1beta1.Tx.MsgSwapForExactTokens) request,
              (io.grpc.stub.StreamObserver<kava.swap.v1beta1.Tx.MsgSwapForExactTokensResponse>) responseObserver);
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
      return kava.swap.v1beta1.Tx.getDescriptor();
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
              .addMethod(getDepositMethod())
              .addMethod(getWithdrawMethod())
              .addMethod(getSwapExactForTokensMethod())
              .addMethod(getSwapForExactTokensMethod())
              .build();
        }
      }
    }
    return result;
  }
}
