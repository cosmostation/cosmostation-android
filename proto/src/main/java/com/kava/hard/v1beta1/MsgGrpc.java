package com.kava.hard.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the hard Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: kava/hard/v1beta1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.hard.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kava.hard.v1beta1.TxProto.MsgDeposit,
      com.kava.hard.v1beta1.TxProto.MsgDepositResponse> getDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposit",
      requestType = com.kava.hard.v1beta1.TxProto.MsgDeposit.class,
      responseType = com.kava.hard.v1beta1.TxProto.MsgDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.hard.v1beta1.TxProto.MsgDeposit,
      com.kava.hard.v1beta1.TxProto.MsgDepositResponse> getDepositMethod() {
    io.grpc.MethodDescriptor<com.kava.hard.v1beta1.TxProto.MsgDeposit, com.kava.hard.v1beta1.TxProto.MsgDepositResponse> getDepositMethod;
    if ((getDepositMethod = MsgGrpc.getDepositMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDepositMethod = MsgGrpc.getDepositMethod) == null) {
          MsgGrpc.getDepositMethod = getDepositMethod =
              io.grpc.MethodDescriptor.<com.kava.hard.v1beta1.TxProto.MsgDeposit, com.kava.hard.v1beta1.TxProto.MsgDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.TxProto.MsgDeposit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.TxProto.MsgDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Deposit"))
              .build();
        }
      }
    }
    return getDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.hard.v1beta1.TxProto.MsgWithdraw,
      com.kava.hard.v1beta1.TxProto.MsgWithdrawResponse> getWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Withdraw",
      requestType = com.kava.hard.v1beta1.TxProto.MsgWithdraw.class,
      responseType = com.kava.hard.v1beta1.TxProto.MsgWithdrawResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.hard.v1beta1.TxProto.MsgWithdraw,
      com.kava.hard.v1beta1.TxProto.MsgWithdrawResponse> getWithdrawMethod() {
    io.grpc.MethodDescriptor<com.kava.hard.v1beta1.TxProto.MsgWithdraw, com.kava.hard.v1beta1.TxProto.MsgWithdrawResponse> getWithdrawMethod;
    if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
          MsgGrpc.getWithdrawMethod = getWithdrawMethod =
              io.grpc.MethodDescriptor.<com.kava.hard.v1beta1.TxProto.MsgWithdraw, com.kava.hard.v1beta1.TxProto.MsgWithdrawResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Withdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.TxProto.MsgWithdraw.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.TxProto.MsgWithdrawResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Withdraw"))
              .build();
        }
      }
    }
    return getWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.hard.v1beta1.TxProto.MsgBorrow,
      com.kava.hard.v1beta1.TxProto.MsgBorrowResponse> getBorrowMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Borrow",
      requestType = com.kava.hard.v1beta1.TxProto.MsgBorrow.class,
      responseType = com.kava.hard.v1beta1.TxProto.MsgBorrowResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.hard.v1beta1.TxProto.MsgBorrow,
      com.kava.hard.v1beta1.TxProto.MsgBorrowResponse> getBorrowMethod() {
    io.grpc.MethodDescriptor<com.kava.hard.v1beta1.TxProto.MsgBorrow, com.kava.hard.v1beta1.TxProto.MsgBorrowResponse> getBorrowMethod;
    if ((getBorrowMethod = MsgGrpc.getBorrowMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBorrowMethod = MsgGrpc.getBorrowMethod) == null) {
          MsgGrpc.getBorrowMethod = getBorrowMethod =
              io.grpc.MethodDescriptor.<com.kava.hard.v1beta1.TxProto.MsgBorrow, com.kava.hard.v1beta1.TxProto.MsgBorrowResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Borrow"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.TxProto.MsgBorrow.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.TxProto.MsgBorrowResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Borrow"))
              .build();
        }
      }
    }
    return getBorrowMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.hard.v1beta1.TxProto.MsgRepay,
      com.kava.hard.v1beta1.TxProto.MsgRepayResponse> getRepayMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Repay",
      requestType = com.kava.hard.v1beta1.TxProto.MsgRepay.class,
      responseType = com.kava.hard.v1beta1.TxProto.MsgRepayResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.hard.v1beta1.TxProto.MsgRepay,
      com.kava.hard.v1beta1.TxProto.MsgRepayResponse> getRepayMethod() {
    io.grpc.MethodDescriptor<com.kava.hard.v1beta1.TxProto.MsgRepay, com.kava.hard.v1beta1.TxProto.MsgRepayResponse> getRepayMethod;
    if ((getRepayMethod = MsgGrpc.getRepayMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRepayMethod = MsgGrpc.getRepayMethod) == null) {
          MsgGrpc.getRepayMethod = getRepayMethod =
              io.grpc.MethodDescriptor.<com.kava.hard.v1beta1.TxProto.MsgRepay, com.kava.hard.v1beta1.TxProto.MsgRepayResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Repay"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.TxProto.MsgRepay.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.TxProto.MsgRepayResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Repay"))
              .build();
        }
      }
    }
    return getRepayMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.hard.v1beta1.TxProto.MsgLiquidate,
      com.kava.hard.v1beta1.TxProto.MsgLiquidateResponse> getLiquidateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Liquidate",
      requestType = com.kava.hard.v1beta1.TxProto.MsgLiquidate.class,
      responseType = com.kava.hard.v1beta1.TxProto.MsgLiquidateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.hard.v1beta1.TxProto.MsgLiquidate,
      com.kava.hard.v1beta1.TxProto.MsgLiquidateResponse> getLiquidateMethod() {
    io.grpc.MethodDescriptor<com.kava.hard.v1beta1.TxProto.MsgLiquidate, com.kava.hard.v1beta1.TxProto.MsgLiquidateResponse> getLiquidateMethod;
    if ((getLiquidateMethod = MsgGrpc.getLiquidateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLiquidateMethod = MsgGrpc.getLiquidateMethod) == null) {
          MsgGrpc.getLiquidateMethod = getLiquidateMethod =
              io.grpc.MethodDescriptor.<com.kava.hard.v1beta1.TxProto.MsgLiquidate, com.kava.hard.v1beta1.TxProto.MsgLiquidateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Liquidate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.TxProto.MsgLiquidate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.TxProto.MsgLiquidateResponse.getDefaultInstance()))
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
  public interface AsyncService {

    /**
     * <pre>
     * Deposit defines a method for depositing funds to hard liquidity pool.
     * </pre>
     */
    default void deposit(com.kava.hard.v1beta1.TxProto.MsgDeposit request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.TxProto.MsgDepositResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDepositMethod(), responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing funds from hard liquidity pool.
     * </pre>
     */
    default void withdraw(com.kava.hard.v1beta1.TxProto.MsgWithdraw request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.TxProto.MsgWithdrawResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWithdrawMethod(), responseObserver);
    }

    /**
     * <pre>
     * Borrow defines a method for borrowing funds from hard liquidity pool.
     * </pre>
     */
    default void borrow(com.kava.hard.v1beta1.TxProto.MsgBorrow request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.TxProto.MsgBorrowResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBorrowMethod(), responseObserver);
    }

    /**
     * <pre>
     * Repay defines a method for repaying funds borrowed from hard liquidity pool.
     * </pre>
     */
    default void repay(com.kava.hard.v1beta1.TxProto.MsgRepay request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.TxProto.MsgRepayResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRepayMethod(), responseObserver);
    }

    /**
     * <pre>
     * Liquidate defines a method for attempting to liquidate a borrower that is over their loan-to-value.
     * </pre>
     */
    default void liquidate(com.kava.hard.v1beta1.TxProto.MsgLiquidate request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.TxProto.MsgLiquidateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLiquidateMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the hard Msg service.
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
   * Msg defines the hard Msg service.
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
     * Deposit defines a method for depositing funds to hard liquidity pool.
     * </pre>
     */
    public void deposit(com.kava.hard.v1beta1.TxProto.MsgDeposit request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.TxProto.MsgDepositResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing funds from hard liquidity pool.
     * </pre>
     */
    public void withdraw(com.kava.hard.v1beta1.TxProto.MsgWithdraw request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.TxProto.MsgWithdrawResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Borrow defines a method for borrowing funds from hard liquidity pool.
     * </pre>
     */
    public void borrow(com.kava.hard.v1beta1.TxProto.MsgBorrow request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.TxProto.MsgBorrowResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBorrowMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Repay defines a method for repaying funds borrowed from hard liquidity pool.
     * </pre>
     */
    public void repay(com.kava.hard.v1beta1.TxProto.MsgRepay request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.TxProto.MsgRepayResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRepayMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Liquidate defines a method for attempting to liquidate a borrower that is over their loan-to-value.
     * </pre>
     */
    public void liquidate(com.kava.hard.v1beta1.TxProto.MsgLiquidate request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.TxProto.MsgLiquidateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLiquidateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the hard Msg service.
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
     * Deposit defines a method for depositing funds to hard liquidity pool.
     * </pre>
     */
    public com.kava.hard.v1beta1.TxProto.MsgDepositResponse deposit(com.kava.hard.v1beta1.TxProto.MsgDeposit request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDepositMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing funds from hard liquidity pool.
     * </pre>
     */
    public com.kava.hard.v1beta1.TxProto.MsgWithdrawResponse withdraw(com.kava.hard.v1beta1.TxProto.MsgWithdraw request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getWithdrawMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Borrow defines a method for borrowing funds from hard liquidity pool.
     * </pre>
     */
    public com.kava.hard.v1beta1.TxProto.MsgBorrowResponse borrow(com.kava.hard.v1beta1.TxProto.MsgBorrow request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBorrowMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Repay defines a method for repaying funds borrowed from hard liquidity pool.
     * </pre>
     */
    public com.kava.hard.v1beta1.TxProto.MsgRepayResponse repay(com.kava.hard.v1beta1.TxProto.MsgRepay request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRepayMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Liquidate defines a method for attempting to liquidate a borrower that is over their loan-to-value.
     * </pre>
     */
    public com.kava.hard.v1beta1.TxProto.MsgLiquidateResponse liquidate(com.kava.hard.v1beta1.TxProto.MsgLiquidate request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLiquidateMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the hard Msg service.
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
     * Deposit defines a method for depositing funds to hard liquidity pool.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.hard.v1beta1.TxProto.MsgDepositResponse> deposit(
        com.kava.hard.v1beta1.TxProto.MsgDeposit request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing funds from hard liquidity pool.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.hard.v1beta1.TxProto.MsgWithdrawResponse> withdraw(
        com.kava.hard.v1beta1.TxProto.MsgWithdraw request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Borrow defines a method for borrowing funds from hard liquidity pool.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.hard.v1beta1.TxProto.MsgBorrowResponse> borrow(
        com.kava.hard.v1beta1.TxProto.MsgBorrow request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBorrowMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Repay defines a method for repaying funds borrowed from hard liquidity pool.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.hard.v1beta1.TxProto.MsgRepayResponse> repay(
        com.kava.hard.v1beta1.TxProto.MsgRepay request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRepayMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Liquidate defines a method for attempting to liquidate a borrower that is over their loan-to-value.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.hard.v1beta1.TxProto.MsgLiquidateResponse> liquidate(
        com.kava.hard.v1beta1.TxProto.MsgLiquidate request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
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
        case METHODID_DEPOSIT:
          serviceImpl.deposit((com.kava.hard.v1beta1.TxProto.MsgDeposit) request,
              (io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.TxProto.MsgDepositResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW:
          serviceImpl.withdraw((com.kava.hard.v1beta1.TxProto.MsgWithdraw) request,
              (io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.TxProto.MsgWithdrawResponse>) responseObserver);
          break;
        case METHODID_BORROW:
          serviceImpl.borrow((com.kava.hard.v1beta1.TxProto.MsgBorrow) request,
              (io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.TxProto.MsgBorrowResponse>) responseObserver);
          break;
        case METHODID_REPAY:
          serviceImpl.repay((com.kava.hard.v1beta1.TxProto.MsgRepay) request,
              (io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.TxProto.MsgRepayResponse>) responseObserver);
          break;
        case METHODID_LIQUIDATE:
          serviceImpl.liquidate((com.kava.hard.v1beta1.TxProto.MsgLiquidate) request,
              (io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.TxProto.MsgLiquidateResponse>) responseObserver);
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
          getDepositMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.hard.v1beta1.TxProto.MsgDeposit,
              com.kava.hard.v1beta1.TxProto.MsgDepositResponse>(
                service, METHODID_DEPOSIT)))
        .addMethod(
          getWithdrawMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.hard.v1beta1.TxProto.MsgWithdraw,
              com.kava.hard.v1beta1.TxProto.MsgWithdrawResponse>(
                service, METHODID_WITHDRAW)))
        .addMethod(
          getBorrowMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.hard.v1beta1.TxProto.MsgBorrow,
              com.kava.hard.v1beta1.TxProto.MsgBorrowResponse>(
                service, METHODID_BORROW)))
        .addMethod(
          getRepayMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.hard.v1beta1.TxProto.MsgRepay,
              com.kava.hard.v1beta1.TxProto.MsgRepayResponse>(
                service, METHODID_REPAY)))
        .addMethod(
          getLiquidateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.hard.v1beta1.TxProto.MsgLiquidate,
              com.kava.hard.v1beta1.TxProto.MsgLiquidateResponse>(
                service, METHODID_LIQUIDATE)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kava.hard.v1beta1.TxProto.getDescriptor();
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
