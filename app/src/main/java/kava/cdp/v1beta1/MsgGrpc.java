package kava.cdp.v1beta1;

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
 * Msg defines the cdp Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/cdp/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.cdp.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgCreateCDP,
      kava.cdp.v1beta1.Tx.MsgCreateCDPResponse> getCreateCDPMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateCDP",
      requestType = kava.cdp.v1beta1.Tx.MsgCreateCDP.class,
      responseType = kava.cdp.v1beta1.Tx.MsgCreateCDPResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgCreateCDP,
      kava.cdp.v1beta1.Tx.MsgCreateCDPResponse> getCreateCDPMethod() {
    io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgCreateCDP, kava.cdp.v1beta1.Tx.MsgCreateCDPResponse> getCreateCDPMethod;
    if ((getCreateCDPMethod = MsgGrpc.getCreateCDPMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateCDPMethod = MsgGrpc.getCreateCDPMethod) == null) {
          MsgGrpc.getCreateCDPMethod = getCreateCDPMethod =
              io.grpc.MethodDescriptor.<kava.cdp.v1beta1.Tx.MsgCreateCDP, kava.cdp.v1beta1.Tx.MsgCreateCDPResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateCDP"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.Tx.MsgCreateCDP.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.Tx.MsgCreateCDPResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateCDP"))
              .build();
        }
      }
    }
    return getCreateCDPMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgDeposit,
      kava.cdp.v1beta1.Tx.MsgDepositResponse> getDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposit",
      requestType = kava.cdp.v1beta1.Tx.MsgDeposit.class,
      responseType = kava.cdp.v1beta1.Tx.MsgDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgDeposit,
      kava.cdp.v1beta1.Tx.MsgDepositResponse> getDepositMethod() {
    io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgDeposit, kava.cdp.v1beta1.Tx.MsgDepositResponse> getDepositMethod;
    if ((getDepositMethod = MsgGrpc.getDepositMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDepositMethod = MsgGrpc.getDepositMethod) == null) {
          MsgGrpc.getDepositMethod = getDepositMethod =
              io.grpc.MethodDescriptor.<kava.cdp.v1beta1.Tx.MsgDeposit, kava.cdp.v1beta1.Tx.MsgDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.Tx.MsgDeposit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.Tx.MsgDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Deposit"))
              .build();
        }
      }
    }
    return getDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgWithdraw,
      kava.cdp.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Withdraw",
      requestType = kava.cdp.v1beta1.Tx.MsgWithdraw.class,
      responseType = kava.cdp.v1beta1.Tx.MsgWithdrawResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgWithdraw,
      kava.cdp.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod() {
    io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgWithdraw, kava.cdp.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod;
    if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
          MsgGrpc.getWithdrawMethod = getWithdrawMethod =
              io.grpc.MethodDescriptor.<kava.cdp.v1beta1.Tx.MsgWithdraw, kava.cdp.v1beta1.Tx.MsgWithdrawResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Withdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.Tx.MsgWithdraw.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.Tx.MsgWithdrawResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Withdraw"))
              .build();
        }
      }
    }
    return getWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgDrawDebt,
      kava.cdp.v1beta1.Tx.MsgDrawDebtResponse> getDrawDebtMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DrawDebt",
      requestType = kava.cdp.v1beta1.Tx.MsgDrawDebt.class,
      responseType = kava.cdp.v1beta1.Tx.MsgDrawDebtResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgDrawDebt,
      kava.cdp.v1beta1.Tx.MsgDrawDebtResponse> getDrawDebtMethod() {
    io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgDrawDebt, kava.cdp.v1beta1.Tx.MsgDrawDebtResponse> getDrawDebtMethod;
    if ((getDrawDebtMethod = MsgGrpc.getDrawDebtMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDrawDebtMethod = MsgGrpc.getDrawDebtMethod) == null) {
          MsgGrpc.getDrawDebtMethod = getDrawDebtMethod =
              io.grpc.MethodDescriptor.<kava.cdp.v1beta1.Tx.MsgDrawDebt, kava.cdp.v1beta1.Tx.MsgDrawDebtResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DrawDebt"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.Tx.MsgDrawDebt.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.Tx.MsgDrawDebtResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DrawDebt"))
              .build();
        }
      }
    }
    return getDrawDebtMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgRepayDebt,
      kava.cdp.v1beta1.Tx.MsgRepayDebtResponse> getRepayDebtMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RepayDebt",
      requestType = kava.cdp.v1beta1.Tx.MsgRepayDebt.class,
      responseType = kava.cdp.v1beta1.Tx.MsgRepayDebtResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgRepayDebt,
      kava.cdp.v1beta1.Tx.MsgRepayDebtResponse> getRepayDebtMethod() {
    io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgRepayDebt, kava.cdp.v1beta1.Tx.MsgRepayDebtResponse> getRepayDebtMethod;
    if ((getRepayDebtMethod = MsgGrpc.getRepayDebtMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRepayDebtMethod = MsgGrpc.getRepayDebtMethod) == null) {
          MsgGrpc.getRepayDebtMethod = getRepayDebtMethod =
              io.grpc.MethodDescriptor.<kava.cdp.v1beta1.Tx.MsgRepayDebt, kava.cdp.v1beta1.Tx.MsgRepayDebtResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RepayDebt"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.Tx.MsgRepayDebt.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.Tx.MsgRepayDebtResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RepayDebt"))
              .build();
        }
      }
    }
    return getRepayDebtMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgLiquidate,
      kava.cdp.v1beta1.Tx.MsgLiquidateResponse> getLiquidateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Liquidate",
      requestType = kava.cdp.v1beta1.Tx.MsgLiquidate.class,
      responseType = kava.cdp.v1beta1.Tx.MsgLiquidateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgLiquidate,
      kava.cdp.v1beta1.Tx.MsgLiquidateResponse> getLiquidateMethod() {
    io.grpc.MethodDescriptor<kava.cdp.v1beta1.Tx.MsgLiquidate, kava.cdp.v1beta1.Tx.MsgLiquidateResponse> getLiquidateMethod;
    if ((getLiquidateMethod = MsgGrpc.getLiquidateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLiquidateMethod = MsgGrpc.getLiquidateMethod) == null) {
          MsgGrpc.getLiquidateMethod = getLiquidateMethod =
              io.grpc.MethodDescriptor.<kava.cdp.v1beta1.Tx.MsgLiquidate, kava.cdp.v1beta1.Tx.MsgLiquidateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Liquidate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.Tx.MsgLiquidate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.Tx.MsgLiquidateResponse.getDefaultInstance()))
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
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreateCDP defines a method to create a new CDP.
     * </pre>
     */
    public void createCDP(kava.cdp.v1beta1.Tx.MsgCreateCDP request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgCreateCDPResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateCDPMethod(), responseObserver);
    }

    /**
     * <pre>
     * Deposit defines a method to deposit to a CDP.
     * </pre>
     */
    public void deposit(kava.cdp.v1beta1.Tx.MsgDeposit request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgDepositResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositMethod(), responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method to withdraw collateral from a CDP.
     * </pre>
     */
    public void withdraw(kava.cdp.v1beta1.Tx.MsgWithdraw request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgWithdrawResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawMethod(), responseObserver);
    }

    /**
     * <pre>
     * DrawDebt defines a method to draw debt from a CDP.
     * </pre>
     */
    public void drawDebt(kava.cdp.v1beta1.Tx.MsgDrawDebt request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgDrawDebtResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDrawDebtMethod(), responseObserver);
    }

    /**
     * <pre>
     * RepayDebt defines a method to repay debt from a CDP.
     * </pre>
     */
    public void repayDebt(kava.cdp.v1beta1.Tx.MsgRepayDebt request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgRepayDebtResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRepayDebtMethod(), responseObserver);
    }

    /**
     * <pre>
     * Liquidate defines a method to attempt to liquidate a CDP whos
     * collateralization ratio is under its liquidation ratio.
     * </pre>
     */
    public void liquidate(kava.cdp.v1beta1.Tx.MsgLiquidate request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgLiquidateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLiquidateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateCDPMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.cdp.v1beta1.Tx.MsgCreateCDP,
                kava.cdp.v1beta1.Tx.MsgCreateCDPResponse>(
                  this, METHODID_CREATE_CDP)))
          .addMethod(
            getDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.cdp.v1beta1.Tx.MsgDeposit,
                kava.cdp.v1beta1.Tx.MsgDepositResponse>(
                  this, METHODID_DEPOSIT)))
          .addMethod(
            getWithdrawMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.cdp.v1beta1.Tx.MsgWithdraw,
                kava.cdp.v1beta1.Tx.MsgWithdrawResponse>(
                  this, METHODID_WITHDRAW)))
          .addMethod(
            getDrawDebtMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.cdp.v1beta1.Tx.MsgDrawDebt,
                kava.cdp.v1beta1.Tx.MsgDrawDebtResponse>(
                  this, METHODID_DRAW_DEBT)))
          .addMethod(
            getRepayDebtMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.cdp.v1beta1.Tx.MsgRepayDebt,
                kava.cdp.v1beta1.Tx.MsgRepayDebtResponse>(
                  this, METHODID_REPAY_DEBT)))
          .addMethod(
            getLiquidateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.cdp.v1beta1.Tx.MsgLiquidate,
                kava.cdp.v1beta1.Tx.MsgLiquidateResponse>(
                  this, METHODID_LIQUIDATE)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the cdp Msg service.
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
     * CreateCDP defines a method to create a new CDP.
     * </pre>
     */
    public void createCDP(kava.cdp.v1beta1.Tx.MsgCreateCDP request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgCreateCDPResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateCDPMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Deposit defines a method to deposit to a CDP.
     * </pre>
     */
    public void deposit(kava.cdp.v1beta1.Tx.MsgDeposit request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgDepositResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method to withdraw collateral from a CDP.
     * </pre>
     */
    public void withdraw(kava.cdp.v1beta1.Tx.MsgWithdraw request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgWithdrawResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DrawDebt defines a method to draw debt from a CDP.
     * </pre>
     */
    public void drawDebt(kava.cdp.v1beta1.Tx.MsgDrawDebt request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgDrawDebtResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDrawDebtMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RepayDebt defines a method to repay debt from a CDP.
     * </pre>
     */
    public void repayDebt(kava.cdp.v1beta1.Tx.MsgRepayDebt request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgRepayDebtResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRepayDebtMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Liquidate defines a method to attempt to liquidate a CDP whos
     * collateralization ratio is under its liquidation ratio.
     * </pre>
     */
    public void liquidate(kava.cdp.v1beta1.Tx.MsgLiquidate request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgLiquidateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLiquidateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the cdp Msg service.
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
     * CreateCDP defines a method to create a new CDP.
     * </pre>
     */
    public kava.cdp.v1beta1.Tx.MsgCreateCDPResponse createCDP(kava.cdp.v1beta1.Tx.MsgCreateCDP request) {
      return blockingUnaryCall(
          getChannel(), getCreateCDPMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Deposit defines a method to deposit to a CDP.
     * </pre>
     */
    public kava.cdp.v1beta1.Tx.MsgDepositResponse deposit(kava.cdp.v1beta1.Tx.MsgDeposit request) {
      return blockingUnaryCall(
          getChannel(), getDepositMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Withdraw defines a method to withdraw collateral from a CDP.
     * </pre>
     */
    public kava.cdp.v1beta1.Tx.MsgWithdrawResponse withdraw(kava.cdp.v1beta1.Tx.MsgWithdraw request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DrawDebt defines a method to draw debt from a CDP.
     * </pre>
     */
    public kava.cdp.v1beta1.Tx.MsgDrawDebtResponse drawDebt(kava.cdp.v1beta1.Tx.MsgDrawDebt request) {
      return blockingUnaryCall(
          getChannel(), getDrawDebtMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RepayDebt defines a method to repay debt from a CDP.
     * </pre>
     */
    public kava.cdp.v1beta1.Tx.MsgRepayDebtResponse repayDebt(kava.cdp.v1beta1.Tx.MsgRepayDebt request) {
      return blockingUnaryCall(
          getChannel(), getRepayDebtMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Liquidate defines a method to attempt to liquidate a CDP whos
     * collateralization ratio is under its liquidation ratio.
     * </pre>
     */
    public kava.cdp.v1beta1.Tx.MsgLiquidateResponse liquidate(kava.cdp.v1beta1.Tx.MsgLiquidate request) {
      return blockingUnaryCall(
          getChannel(), getLiquidateMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the cdp Msg service.
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
     * CreateCDP defines a method to create a new CDP.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.cdp.v1beta1.Tx.MsgCreateCDPResponse> createCDP(
        kava.cdp.v1beta1.Tx.MsgCreateCDP request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateCDPMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Deposit defines a method to deposit to a CDP.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.cdp.v1beta1.Tx.MsgDepositResponse> deposit(
        kava.cdp.v1beta1.Tx.MsgDeposit request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Withdraw defines a method to withdraw collateral from a CDP.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.cdp.v1beta1.Tx.MsgWithdrawResponse> withdraw(
        kava.cdp.v1beta1.Tx.MsgWithdraw request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DrawDebt defines a method to draw debt from a CDP.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.cdp.v1beta1.Tx.MsgDrawDebtResponse> drawDebt(
        kava.cdp.v1beta1.Tx.MsgDrawDebt request) {
      return futureUnaryCall(
          getChannel().newCall(getDrawDebtMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RepayDebt defines a method to repay debt from a CDP.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.cdp.v1beta1.Tx.MsgRepayDebtResponse> repayDebt(
        kava.cdp.v1beta1.Tx.MsgRepayDebt request) {
      return futureUnaryCall(
          getChannel().newCall(getRepayDebtMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Liquidate defines a method to attempt to liquidate a CDP whos
     * collateralization ratio is under its liquidation ratio.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.cdp.v1beta1.Tx.MsgLiquidateResponse> liquidate(
        kava.cdp.v1beta1.Tx.MsgLiquidate request) {
      return futureUnaryCall(
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
        case METHODID_CREATE_CDP:
          serviceImpl.createCDP((kava.cdp.v1beta1.Tx.MsgCreateCDP) request,
              (io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgCreateCDPResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT:
          serviceImpl.deposit((kava.cdp.v1beta1.Tx.MsgDeposit) request,
              (io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgDepositResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW:
          serviceImpl.withdraw((kava.cdp.v1beta1.Tx.MsgWithdraw) request,
              (io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgWithdrawResponse>) responseObserver);
          break;
        case METHODID_DRAW_DEBT:
          serviceImpl.drawDebt((kava.cdp.v1beta1.Tx.MsgDrawDebt) request,
              (io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgDrawDebtResponse>) responseObserver);
          break;
        case METHODID_REPAY_DEBT:
          serviceImpl.repayDebt((kava.cdp.v1beta1.Tx.MsgRepayDebt) request,
              (io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgRepayDebtResponse>) responseObserver);
          break;
        case METHODID_LIQUIDATE:
          serviceImpl.liquidate((kava.cdp.v1beta1.Tx.MsgLiquidate) request,
              (io.grpc.stub.StreamObserver<kava.cdp.v1beta1.Tx.MsgLiquidateResponse>) responseObserver);
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
      return kava.cdp.v1beta1.Tx.getDescriptor();
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
