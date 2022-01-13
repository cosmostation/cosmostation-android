package kava.hard.v1beta1;

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
 * Msg defines the hard Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/hard/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.hard.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.hard.v1beta1.Tx.MsgDeposit,
      kava.hard.v1beta1.Tx.MsgDepositResponse> getDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposit",
      requestType = kava.hard.v1beta1.Tx.MsgDeposit.class,
      responseType = kava.hard.v1beta1.Tx.MsgDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.hard.v1beta1.Tx.MsgDeposit,
      kava.hard.v1beta1.Tx.MsgDepositResponse> getDepositMethod() {
    io.grpc.MethodDescriptor<kava.hard.v1beta1.Tx.MsgDeposit, kava.hard.v1beta1.Tx.MsgDepositResponse> getDepositMethod;
    if ((getDepositMethod = MsgGrpc.getDepositMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDepositMethod = MsgGrpc.getDepositMethod) == null) {
          MsgGrpc.getDepositMethod = getDepositMethod =
              io.grpc.MethodDescriptor.<kava.hard.v1beta1.Tx.MsgDeposit, kava.hard.v1beta1.Tx.MsgDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.Tx.MsgDeposit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.Tx.MsgDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Deposit"))
              .build();
        }
      }
    }
    return getDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.hard.v1beta1.Tx.MsgWithdraw,
      kava.hard.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Withdraw",
      requestType = kava.hard.v1beta1.Tx.MsgWithdraw.class,
      responseType = kava.hard.v1beta1.Tx.MsgWithdrawResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.hard.v1beta1.Tx.MsgWithdraw,
      kava.hard.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod() {
    io.grpc.MethodDescriptor<kava.hard.v1beta1.Tx.MsgWithdraw, kava.hard.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod;
    if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
          MsgGrpc.getWithdrawMethod = getWithdrawMethod =
              io.grpc.MethodDescriptor.<kava.hard.v1beta1.Tx.MsgWithdraw, kava.hard.v1beta1.Tx.MsgWithdrawResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Withdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.Tx.MsgWithdraw.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.Tx.MsgWithdrawResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Withdraw"))
              .build();
        }
      }
    }
    return getWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.hard.v1beta1.Tx.MsgBorrow,
      kava.hard.v1beta1.Tx.MsgBorrowResponse> getBorrowMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Borrow",
      requestType = kava.hard.v1beta1.Tx.MsgBorrow.class,
      responseType = kava.hard.v1beta1.Tx.MsgBorrowResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.hard.v1beta1.Tx.MsgBorrow,
      kava.hard.v1beta1.Tx.MsgBorrowResponse> getBorrowMethod() {
    io.grpc.MethodDescriptor<kava.hard.v1beta1.Tx.MsgBorrow, kava.hard.v1beta1.Tx.MsgBorrowResponse> getBorrowMethod;
    if ((getBorrowMethod = MsgGrpc.getBorrowMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBorrowMethod = MsgGrpc.getBorrowMethod) == null) {
          MsgGrpc.getBorrowMethod = getBorrowMethod =
              io.grpc.MethodDescriptor.<kava.hard.v1beta1.Tx.MsgBorrow, kava.hard.v1beta1.Tx.MsgBorrowResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Borrow"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.Tx.MsgBorrow.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.Tx.MsgBorrowResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Borrow"))
              .build();
        }
      }
    }
    return getBorrowMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.hard.v1beta1.Tx.MsgRepay,
      kava.hard.v1beta1.Tx.MsgRepayResponse> getRepayMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Repay",
      requestType = kava.hard.v1beta1.Tx.MsgRepay.class,
      responseType = kava.hard.v1beta1.Tx.MsgRepayResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.hard.v1beta1.Tx.MsgRepay,
      kava.hard.v1beta1.Tx.MsgRepayResponse> getRepayMethod() {
    io.grpc.MethodDescriptor<kava.hard.v1beta1.Tx.MsgRepay, kava.hard.v1beta1.Tx.MsgRepayResponse> getRepayMethod;
    if ((getRepayMethod = MsgGrpc.getRepayMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRepayMethod = MsgGrpc.getRepayMethod) == null) {
          MsgGrpc.getRepayMethod = getRepayMethod =
              io.grpc.MethodDescriptor.<kava.hard.v1beta1.Tx.MsgRepay, kava.hard.v1beta1.Tx.MsgRepayResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Repay"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.Tx.MsgRepay.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.Tx.MsgRepayResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Repay"))
              .build();
        }
      }
    }
    return getRepayMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.hard.v1beta1.Tx.MsgLiquidate,
      kava.hard.v1beta1.Tx.MsgLiquidateResponse> getLiquidateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Liquidate",
      requestType = kava.hard.v1beta1.Tx.MsgLiquidate.class,
      responseType = kava.hard.v1beta1.Tx.MsgLiquidateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.hard.v1beta1.Tx.MsgLiquidate,
      kava.hard.v1beta1.Tx.MsgLiquidateResponse> getLiquidateMethod() {
    io.grpc.MethodDescriptor<kava.hard.v1beta1.Tx.MsgLiquidate, kava.hard.v1beta1.Tx.MsgLiquidateResponse> getLiquidateMethod;
    if ((getLiquidateMethod = MsgGrpc.getLiquidateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLiquidateMethod = MsgGrpc.getLiquidateMethod) == null) {
          MsgGrpc.getLiquidateMethod = getLiquidateMethod =
              io.grpc.MethodDescriptor.<kava.hard.v1beta1.Tx.MsgLiquidate, kava.hard.v1beta1.Tx.MsgLiquidateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Liquidate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.Tx.MsgLiquidate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.Tx.MsgLiquidateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Liquidate"))
              .build();
        }
      }
    }
    return getLiquidateMethod;
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
   * Msg defines the hard Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Deposit defines a method for depositing funds to hard liquidity pool.
     * </pre>
     */
    public void deposit(kava.hard.v1beta1.Tx.MsgDeposit request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.Tx.MsgDepositResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositMethod(), responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing funds from hard liquidity pool.
     * </pre>
     */
    public void withdraw(kava.hard.v1beta1.Tx.MsgWithdraw request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.Tx.MsgWithdrawResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawMethod(), responseObserver);
    }

    /**
     * <pre>
     * Borrow defines a method for borrowing funds from hard liquidity pool.
     * </pre>
     */
    public void borrow(kava.hard.v1beta1.Tx.MsgBorrow request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.Tx.MsgBorrowResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBorrowMethod(), responseObserver);
    }

    /**
     * <pre>
     * Repay defines a method for repaying funds borrowed from hard liquidity pool.
     * </pre>
     */
    public void repay(kava.hard.v1beta1.Tx.MsgRepay request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.Tx.MsgRepayResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRepayMethod(), responseObserver);
    }

    /**
     * <pre>
     * Liquidate defines a method for attempting to liquidate a borrower that is over their loan-to-value.
     * </pre>
     */
    public void liquidate(kava.hard.v1beta1.Tx.MsgLiquidate request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.Tx.MsgLiquidateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLiquidateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.hard.v1beta1.Tx.MsgDeposit,
                kava.hard.v1beta1.Tx.MsgDepositResponse>(
                  this, METHODID_DEPOSIT)))
          .addMethod(
            getWithdrawMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.hard.v1beta1.Tx.MsgWithdraw,
                kava.hard.v1beta1.Tx.MsgWithdrawResponse>(
                  this, METHODID_WITHDRAW)))
          .addMethod(
            getBorrowMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.hard.v1beta1.Tx.MsgBorrow,
                kava.hard.v1beta1.Tx.MsgBorrowResponse>(
                  this, METHODID_BORROW)))
          .addMethod(
            getRepayMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.hard.v1beta1.Tx.MsgRepay,
                kava.hard.v1beta1.Tx.MsgRepayResponse>(
                  this, METHODID_REPAY)))
          .addMethod(
            getLiquidateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.hard.v1beta1.Tx.MsgLiquidate,
                kava.hard.v1beta1.Tx.MsgLiquidateResponse>(
                  this, METHODID_LIQUIDATE)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the hard Msg service.
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
     * Deposit defines a method for depositing funds to hard liquidity pool.
     * </pre>
     */
    public void deposit(kava.hard.v1beta1.Tx.MsgDeposit request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.Tx.MsgDepositResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing funds from hard liquidity pool.
     * </pre>
     */
    public void withdraw(kava.hard.v1beta1.Tx.MsgWithdraw request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.Tx.MsgWithdrawResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Borrow defines a method for borrowing funds from hard liquidity pool.
     * </pre>
     */
    public void borrow(kava.hard.v1beta1.Tx.MsgBorrow request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.Tx.MsgBorrowResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBorrowMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Repay defines a method for repaying funds borrowed from hard liquidity pool.
     * </pre>
     */
    public void repay(kava.hard.v1beta1.Tx.MsgRepay request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.Tx.MsgRepayResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRepayMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Liquidate defines a method for attempting to liquidate a borrower that is over their loan-to-value.
     * </pre>
     */
    public void liquidate(kava.hard.v1beta1.Tx.MsgLiquidate request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.Tx.MsgLiquidateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLiquidateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the hard Msg service.
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
     * Deposit defines a method for depositing funds to hard liquidity pool.
     * </pre>
     */
    public kava.hard.v1beta1.Tx.MsgDepositResponse deposit(kava.hard.v1beta1.Tx.MsgDeposit request) {
      return blockingUnaryCall(
          getChannel(), getDepositMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing funds from hard liquidity pool.
     * </pre>
     */
    public kava.hard.v1beta1.Tx.MsgWithdrawResponse withdraw(kava.hard.v1beta1.Tx.MsgWithdraw request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Borrow defines a method for borrowing funds from hard liquidity pool.
     * </pre>
     */
    public kava.hard.v1beta1.Tx.MsgBorrowResponse borrow(kava.hard.v1beta1.Tx.MsgBorrow request) {
      return blockingUnaryCall(
          getChannel(), getBorrowMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Repay defines a method for repaying funds borrowed from hard liquidity pool.
     * </pre>
     */
    public kava.hard.v1beta1.Tx.MsgRepayResponse repay(kava.hard.v1beta1.Tx.MsgRepay request) {
      return blockingUnaryCall(
          getChannel(), getRepayMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Liquidate defines a method for attempting to liquidate a borrower that is over their loan-to-value.
     * </pre>
     */
    public kava.hard.v1beta1.Tx.MsgLiquidateResponse liquidate(kava.hard.v1beta1.Tx.MsgLiquidate request) {
      return blockingUnaryCall(
          getChannel(), getLiquidateMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the hard Msg service.
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
     * Deposit defines a method for depositing funds to hard liquidity pool.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.hard.v1beta1.Tx.MsgDepositResponse> deposit(
        kava.hard.v1beta1.Tx.MsgDeposit request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing funds from hard liquidity pool.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.hard.v1beta1.Tx.MsgWithdrawResponse> withdraw(
        kava.hard.v1beta1.Tx.MsgWithdraw request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Borrow defines a method for borrowing funds from hard liquidity pool.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.hard.v1beta1.Tx.MsgBorrowResponse> borrow(
        kava.hard.v1beta1.Tx.MsgBorrow request) {
      return futureUnaryCall(
          getChannel().newCall(getBorrowMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Repay defines a method for repaying funds borrowed from hard liquidity pool.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.hard.v1beta1.Tx.MsgRepayResponse> repay(
        kava.hard.v1beta1.Tx.MsgRepay request) {
      return futureUnaryCall(
          getChannel().newCall(getRepayMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Liquidate defines a method for attempting to liquidate a borrower that is over their loan-to-value.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.hard.v1beta1.Tx.MsgLiquidateResponse> liquidate(
        kava.hard.v1beta1.Tx.MsgLiquidate request) {
      return futureUnaryCall(
          getChannel().newCall(getLiquidateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DEPOSIT = 0;
  private static final int METHODID_WITHDRAW = 1;
  private static final int METHODID_BORROW = 2;
  private static final int METHODID_REPAY = 3;
  private static final int METHODID_LIQUIDATE = 4;

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
          serviceImpl.deposit((kava.hard.v1beta1.Tx.MsgDeposit) request,
              (io.grpc.stub.StreamObserver<kava.hard.v1beta1.Tx.MsgDepositResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW:
          serviceImpl.withdraw((kava.hard.v1beta1.Tx.MsgWithdraw) request,
              (io.grpc.stub.StreamObserver<kava.hard.v1beta1.Tx.MsgWithdrawResponse>) responseObserver);
          break;
        case METHODID_BORROW:
          serviceImpl.borrow((kava.hard.v1beta1.Tx.MsgBorrow) request,
              (io.grpc.stub.StreamObserver<kava.hard.v1beta1.Tx.MsgBorrowResponse>) responseObserver);
          break;
        case METHODID_REPAY:
          serviceImpl.repay((kava.hard.v1beta1.Tx.MsgRepay) request,
              (io.grpc.stub.StreamObserver<kava.hard.v1beta1.Tx.MsgRepayResponse>) responseObserver);
          break;
        case METHODID_LIQUIDATE:
          serviceImpl.liquidate((kava.hard.v1beta1.Tx.MsgLiquidate) request,
              (io.grpc.stub.StreamObserver<kava.hard.v1beta1.Tx.MsgLiquidateResponse>) responseObserver);
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
      return kava.hard.v1beta1.Tx.getDescriptor();
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
              .addMethod(getBorrowMethod())
              .addMethod(getRepayMethod())
              .addMethod(getLiquidateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
