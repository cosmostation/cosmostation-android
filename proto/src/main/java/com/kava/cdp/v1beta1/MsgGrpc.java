package com.kava.cdp.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the cdp Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: kava/cdp/v1beta1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.cdp.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgCreateCDP,
      com.kava.cdp.v1beta1.TxProto.MsgCreateCDPResponse> getCreateCDPMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateCDP",
      requestType = com.kava.cdp.v1beta1.TxProto.MsgCreateCDP.class,
      responseType = com.kava.cdp.v1beta1.TxProto.MsgCreateCDPResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgCreateCDP,
      com.kava.cdp.v1beta1.TxProto.MsgCreateCDPResponse> getCreateCDPMethod() {
    io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgCreateCDP, com.kava.cdp.v1beta1.TxProto.MsgCreateCDPResponse> getCreateCDPMethod;
    if ((getCreateCDPMethod = MsgGrpc.getCreateCDPMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateCDPMethod = MsgGrpc.getCreateCDPMethod) == null) {
          MsgGrpc.getCreateCDPMethod = getCreateCDPMethod =
              io.grpc.MethodDescriptor.<com.kava.cdp.v1beta1.TxProto.MsgCreateCDP, com.kava.cdp.v1beta1.TxProto.MsgCreateCDPResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateCDP"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.TxProto.MsgCreateCDP.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.TxProto.MsgCreateCDPResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateCDP"))
              .build();
        }
      }
    }
    return getCreateCDPMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgDeposit,
      com.kava.cdp.v1beta1.TxProto.MsgDepositResponse> getDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposit",
      requestType = com.kava.cdp.v1beta1.TxProto.MsgDeposit.class,
      responseType = com.kava.cdp.v1beta1.TxProto.MsgDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgDeposit,
      com.kava.cdp.v1beta1.TxProto.MsgDepositResponse> getDepositMethod() {
    io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgDeposit, com.kava.cdp.v1beta1.TxProto.MsgDepositResponse> getDepositMethod;
    if ((getDepositMethod = MsgGrpc.getDepositMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDepositMethod = MsgGrpc.getDepositMethod) == null) {
          MsgGrpc.getDepositMethod = getDepositMethod =
              io.grpc.MethodDescriptor.<com.kava.cdp.v1beta1.TxProto.MsgDeposit, com.kava.cdp.v1beta1.TxProto.MsgDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.TxProto.MsgDeposit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.TxProto.MsgDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Deposit"))
              .build();
        }
      }
    }
    return getDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgWithdraw,
      com.kava.cdp.v1beta1.TxProto.MsgWithdrawResponse> getWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Withdraw",
      requestType = com.kava.cdp.v1beta1.TxProto.MsgWithdraw.class,
      responseType = com.kava.cdp.v1beta1.TxProto.MsgWithdrawResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgWithdraw,
      com.kava.cdp.v1beta1.TxProto.MsgWithdrawResponse> getWithdrawMethod() {
    io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgWithdraw, com.kava.cdp.v1beta1.TxProto.MsgWithdrawResponse> getWithdrawMethod;
    if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
          MsgGrpc.getWithdrawMethod = getWithdrawMethod =
              io.grpc.MethodDescriptor.<com.kava.cdp.v1beta1.TxProto.MsgWithdraw, com.kava.cdp.v1beta1.TxProto.MsgWithdrawResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Withdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.TxProto.MsgWithdraw.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.TxProto.MsgWithdrawResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Withdraw"))
              .build();
        }
      }
    }
    return getWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgDrawDebt,
      com.kava.cdp.v1beta1.TxProto.MsgDrawDebtResponse> getDrawDebtMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DrawDebt",
      requestType = com.kava.cdp.v1beta1.TxProto.MsgDrawDebt.class,
      responseType = com.kava.cdp.v1beta1.TxProto.MsgDrawDebtResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgDrawDebt,
      com.kava.cdp.v1beta1.TxProto.MsgDrawDebtResponse> getDrawDebtMethod() {
    io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgDrawDebt, com.kava.cdp.v1beta1.TxProto.MsgDrawDebtResponse> getDrawDebtMethod;
    if ((getDrawDebtMethod = MsgGrpc.getDrawDebtMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDrawDebtMethod = MsgGrpc.getDrawDebtMethod) == null) {
          MsgGrpc.getDrawDebtMethod = getDrawDebtMethod =
              io.grpc.MethodDescriptor.<com.kava.cdp.v1beta1.TxProto.MsgDrawDebt, com.kava.cdp.v1beta1.TxProto.MsgDrawDebtResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DrawDebt"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.TxProto.MsgDrawDebt.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.TxProto.MsgDrawDebtResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DrawDebt"))
              .build();
        }
      }
    }
    return getDrawDebtMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgRepayDebt,
      com.kava.cdp.v1beta1.TxProto.MsgRepayDebtResponse> getRepayDebtMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RepayDebt",
      requestType = com.kava.cdp.v1beta1.TxProto.MsgRepayDebt.class,
      responseType = com.kava.cdp.v1beta1.TxProto.MsgRepayDebtResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgRepayDebt,
      com.kava.cdp.v1beta1.TxProto.MsgRepayDebtResponse> getRepayDebtMethod() {
    io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgRepayDebt, com.kava.cdp.v1beta1.TxProto.MsgRepayDebtResponse> getRepayDebtMethod;
    if ((getRepayDebtMethod = MsgGrpc.getRepayDebtMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRepayDebtMethod = MsgGrpc.getRepayDebtMethod) == null) {
          MsgGrpc.getRepayDebtMethod = getRepayDebtMethod =
              io.grpc.MethodDescriptor.<com.kava.cdp.v1beta1.TxProto.MsgRepayDebt, com.kava.cdp.v1beta1.TxProto.MsgRepayDebtResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RepayDebt"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.TxProto.MsgRepayDebt.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.TxProto.MsgRepayDebtResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RepayDebt"))
              .build();
        }
      }
    }
    return getRepayDebtMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgLiquidate,
      com.kava.cdp.v1beta1.TxProto.MsgLiquidateResponse> getLiquidateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Liquidate",
      requestType = com.kava.cdp.v1beta1.TxProto.MsgLiquidate.class,
      responseType = com.kava.cdp.v1beta1.TxProto.MsgLiquidateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgLiquidate,
      com.kava.cdp.v1beta1.TxProto.MsgLiquidateResponse> getLiquidateMethod() {
    io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.TxProto.MsgLiquidate, com.kava.cdp.v1beta1.TxProto.MsgLiquidateResponse> getLiquidateMethod;
    if ((getLiquidateMethod = MsgGrpc.getLiquidateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLiquidateMethod = MsgGrpc.getLiquidateMethod) == null) {
          MsgGrpc.getLiquidateMethod = getLiquidateMethod =
              io.grpc.MethodDescriptor.<com.kava.cdp.v1beta1.TxProto.MsgLiquidate, com.kava.cdp.v1beta1.TxProto.MsgLiquidateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Liquidate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.TxProto.MsgLiquidate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.TxProto.MsgLiquidateResponse.getDefaultInstance()))
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
   * Msg defines the cdp Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * CreateCDP defines a method to create a new CDP.
     * </pre>
     */
    default void createCDP(com.kava.cdp.v1beta1.TxProto.MsgCreateCDP request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgCreateCDPResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateCDPMethod(), responseObserver);
    }

    /**
     * <pre>
     * Deposit defines a method to deposit to a CDP.
     * </pre>
     */
    default void deposit(com.kava.cdp.v1beta1.TxProto.MsgDeposit request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgDepositResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDepositMethod(), responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method to withdraw collateral from a CDP.
     * </pre>
     */
    default void withdraw(com.kava.cdp.v1beta1.TxProto.MsgWithdraw request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgWithdrawResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWithdrawMethod(), responseObserver);
    }

    /**
     * <pre>
     * DrawDebt defines a method to draw debt from a CDP.
     * </pre>
     */
    default void drawDebt(com.kava.cdp.v1beta1.TxProto.MsgDrawDebt request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgDrawDebtResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDrawDebtMethod(), responseObserver);
    }

    /**
     * <pre>
     * RepayDebt defines a method to repay debt from a CDP.
     * </pre>
     */
    default void repayDebt(com.kava.cdp.v1beta1.TxProto.MsgRepayDebt request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgRepayDebtResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRepayDebtMethod(), responseObserver);
    }

    /**
     * <pre>
     * Liquidate defines a method to attempt to liquidate a CDP whos
     * collateralization ratio is under its liquidation ratio.
     * </pre>
     */
    default void liquidate(com.kava.cdp.v1beta1.TxProto.MsgLiquidate request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgLiquidateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLiquidateMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the cdp Msg service.
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
   * Msg defines the cdp Msg service.
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
     * CreateCDP defines a method to create a new CDP.
     * </pre>
     */
    public void createCDP(com.kava.cdp.v1beta1.TxProto.MsgCreateCDP request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgCreateCDPResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateCDPMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Deposit defines a method to deposit to a CDP.
     * </pre>
     */
    public void deposit(com.kava.cdp.v1beta1.TxProto.MsgDeposit request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgDepositResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method to withdraw collateral from a CDP.
     * </pre>
     */
    public void withdraw(com.kava.cdp.v1beta1.TxProto.MsgWithdraw request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgWithdrawResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DrawDebt defines a method to draw debt from a CDP.
     * </pre>
     */
    public void drawDebt(com.kava.cdp.v1beta1.TxProto.MsgDrawDebt request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgDrawDebtResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDrawDebtMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RepayDebt defines a method to repay debt from a CDP.
     * </pre>
     */
    public void repayDebt(com.kava.cdp.v1beta1.TxProto.MsgRepayDebt request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgRepayDebtResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRepayDebtMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Liquidate defines a method to attempt to liquidate a CDP whos
     * collateralization ratio is under its liquidation ratio.
     * </pre>
     */
    public void liquidate(com.kava.cdp.v1beta1.TxProto.MsgLiquidate request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgLiquidateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLiquidateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the cdp Msg service.
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
     * CreateCDP defines a method to create a new CDP.
     * </pre>
     */
    public com.kava.cdp.v1beta1.TxProto.MsgCreateCDPResponse createCDP(com.kava.cdp.v1beta1.TxProto.MsgCreateCDP request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateCDPMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Deposit defines a method to deposit to a CDP.
     * </pre>
     */
    public com.kava.cdp.v1beta1.TxProto.MsgDepositResponse deposit(com.kava.cdp.v1beta1.TxProto.MsgDeposit request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDepositMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Withdraw defines a method to withdraw collateral from a CDP.
     * </pre>
     */
    public com.kava.cdp.v1beta1.TxProto.MsgWithdrawResponse withdraw(com.kava.cdp.v1beta1.TxProto.MsgWithdraw request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getWithdrawMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DrawDebt defines a method to draw debt from a CDP.
     * </pre>
     */
    public com.kava.cdp.v1beta1.TxProto.MsgDrawDebtResponse drawDebt(com.kava.cdp.v1beta1.TxProto.MsgDrawDebt request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDrawDebtMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RepayDebt defines a method to repay debt from a CDP.
     * </pre>
     */
    public com.kava.cdp.v1beta1.TxProto.MsgRepayDebtResponse repayDebt(com.kava.cdp.v1beta1.TxProto.MsgRepayDebt request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRepayDebtMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Liquidate defines a method to attempt to liquidate a CDP whos
     * collateralization ratio is under its liquidation ratio.
     * </pre>
     */
    public com.kava.cdp.v1beta1.TxProto.MsgLiquidateResponse liquidate(com.kava.cdp.v1beta1.TxProto.MsgLiquidate request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLiquidateMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the cdp Msg service.
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
     * CreateCDP defines a method to create a new CDP.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.cdp.v1beta1.TxProto.MsgCreateCDPResponse> createCDP(
        com.kava.cdp.v1beta1.TxProto.MsgCreateCDP request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateCDPMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Deposit defines a method to deposit to a CDP.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.cdp.v1beta1.TxProto.MsgDepositResponse> deposit(
        com.kava.cdp.v1beta1.TxProto.MsgDeposit request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Withdraw defines a method to withdraw collateral from a CDP.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.cdp.v1beta1.TxProto.MsgWithdrawResponse> withdraw(
        com.kava.cdp.v1beta1.TxProto.MsgWithdraw request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DrawDebt defines a method to draw debt from a CDP.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.cdp.v1beta1.TxProto.MsgDrawDebtResponse> drawDebt(
        com.kava.cdp.v1beta1.TxProto.MsgDrawDebt request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDrawDebtMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RepayDebt defines a method to repay debt from a CDP.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.cdp.v1beta1.TxProto.MsgRepayDebtResponse> repayDebt(
        com.kava.cdp.v1beta1.TxProto.MsgRepayDebt request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRepayDebtMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Liquidate defines a method to attempt to liquidate a CDP whos
     * collateralization ratio is under its liquidation ratio.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.cdp.v1beta1.TxProto.MsgLiquidateResponse> liquidate(
        com.kava.cdp.v1beta1.TxProto.MsgLiquidate request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLiquidateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_CDP = 0;
  private static final int METHODID_DEPOSIT = 1;
  private static final int METHODID_WITHDRAW = 2;
  private static final int METHODID_DRAW_DEBT = 3;
  private static final int METHODID_REPAY_DEBT = 4;
  private static final int METHODID_LIQUIDATE = 5;

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
        case METHODID_CREATE_CDP:
          serviceImpl.createCDP((com.kava.cdp.v1beta1.TxProto.MsgCreateCDP) request,
              (io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgCreateCDPResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT:
          serviceImpl.deposit((com.kava.cdp.v1beta1.TxProto.MsgDeposit) request,
              (io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgDepositResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW:
          serviceImpl.withdraw((com.kava.cdp.v1beta1.TxProto.MsgWithdraw) request,
              (io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgWithdrawResponse>) responseObserver);
          break;
        case METHODID_DRAW_DEBT:
          serviceImpl.drawDebt((com.kava.cdp.v1beta1.TxProto.MsgDrawDebt) request,
              (io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgDrawDebtResponse>) responseObserver);
          break;
        case METHODID_REPAY_DEBT:
          serviceImpl.repayDebt((com.kava.cdp.v1beta1.TxProto.MsgRepayDebt) request,
              (io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgRepayDebtResponse>) responseObserver);
          break;
        case METHODID_LIQUIDATE:
          serviceImpl.liquidate((com.kava.cdp.v1beta1.TxProto.MsgLiquidate) request,
              (io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.TxProto.MsgLiquidateResponse>) responseObserver);
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
          getCreateCDPMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.cdp.v1beta1.TxProto.MsgCreateCDP,
              com.kava.cdp.v1beta1.TxProto.MsgCreateCDPResponse>(
                service, METHODID_CREATE_CDP)))
        .addMethod(
          getDepositMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.cdp.v1beta1.TxProto.MsgDeposit,
              com.kava.cdp.v1beta1.TxProto.MsgDepositResponse>(
                service, METHODID_DEPOSIT)))
        .addMethod(
          getWithdrawMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.cdp.v1beta1.TxProto.MsgWithdraw,
              com.kava.cdp.v1beta1.TxProto.MsgWithdrawResponse>(
                service, METHODID_WITHDRAW)))
        .addMethod(
          getDrawDebtMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.cdp.v1beta1.TxProto.MsgDrawDebt,
              com.kava.cdp.v1beta1.TxProto.MsgDrawDebtResponse>(
                service, METHODID_DRAW_DEBT)))
        .addMethod(
          getRepayDebtMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.cdp.v1beta1.TxProto.MsgRepayDebt,
              com.kava.cdp.v1beta1.TxProto.MsgRepayDebtResponse>(
                service, METHODID_REPAY_DEBT)))
        .addMethod(
          getLiquidateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.cdp.v1beta1.TxProto.MsgLiquidate,
              com.kava.cdp.v1beta1.TxProto.MsgLiquidateResponse>(
                service, METHODID_LIQUIDATE)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kava.cdp.v1beta1.TxProto.getDescriptor();
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
              .addMethod(getCreateCDPMethod())
              .addMethod(getDepositMethod())
              .addMethod(getWithdrawMethod())
              .addMethod(getDrawDebtMethod())
              .addMethod(getRepayDebtMethod())
              .addMethod(getLiquidateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
