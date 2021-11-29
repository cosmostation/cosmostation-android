package umeenetwork.umee.leverage.v1beta1;

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
 * Msg defines the x/leverage module's Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: umee/leverage/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "umeenetwork.umee.leverage.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAsset,
      umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAssetResponse> getLendAssetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LendAsset",
      requestType = umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAsset.class,
      responseType = umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAssetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAsset,
      umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAssetResponse> getLendAssetMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAsset, umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAssetResponse> getLendAssetMethod;
    if ((getLendAssetMethod = MsgGrpc.getLendAssetMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLendAssetMethod = MsgGrpc.getLendAssetMethod) == null) {
          MsgGrpc.getLendAssetMethod = getLendAssetMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAsset, umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAssetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LendAsset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAsset.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAssetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("LendAsset"))
              .build();
        }
      }
    }
    return getLendAssetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAsset,
      umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAssetResponse> getWithdrawAssetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawAsset",
      requestType = umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAsset.class,
      responseType = umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAssetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAsset,
      umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAssetResponse> getWithdrawAssetMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAsset, umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAssetResponse> getWithdrawAssetMethod;
    if ((getWithdrawAssetMethod = MsgGrpc.getWithdrawAssetMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawAssetMethod = MsgGrpc.getWithdrawAssetMethod) == null) {
          MsgGrpc.getWithdrawAssetMethod = getWithdrawAssetMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAsset, umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAssetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawAsset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAsset.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAssetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawAsset"))
              .build();
        }
      }
    }
    return getWithdrawAssetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateral,
      umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateralResponse> getSetCollateralMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetCollateral",
      requestType = umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateral.class,
      responseType = umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateralResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateral,
      umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateralResponse> getSetCollateralMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateral, umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateralResponse> getSetCollateralMethod;
    if ((getSetCollateralMethod = MsgGrpc.getSetCollateralMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetCollateralMethod = MsgGrpc.getSetCollateralMethod) == null) {
          MsgGrpc.getSetCollateralMethod = getSetCollateralMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateral, umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateralResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetCollateral"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateral.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateralResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetCollateral"))
              .build();
        }
      }
    }
    return getSetCollateralMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAsset,
      umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAssetResponse> getBorrowAssetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BorrowAsset",
      requestType = umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAsset.class,
      responseType = umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAssetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAsset,
      umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAssetResponse> getBorrowAssetMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAsset, umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAssetResponse> getBorrowAssetMethod;
    if ((getBorrowAssetMethod = MsgGrpc.getBorrowAssetMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBorrowAssetMethod = MsgGrpc.getBorrowAssetMethod) == null) {
          MsgGrpc.getBorrowAssetMethod = getBorrowAssetMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAsset, umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAssetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BorrowAsset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAsset.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAssetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BorrowAsset"))
              .build();
        }
      }
    }
    return getBorrowAssetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAsset,
      umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAssetResponse> getRepayAssetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RepayAsset",
      requestType = umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAsset.class,
      responseType = umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAssetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAsset,
      umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAssetResponse> getRepayAssetMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAsset, umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAssetResponse> getRepayAssetMethod;
    if ((getRepayAssetMethod = MsgGrpc.getRepayAssetMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRepayAssetMethod = MsgGrpc.getRepayAssetMethod) == null) {
          MsgGrpc.getRepayAssetMethod = getRepayAssetMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAsset, umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAssetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RepayAsset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAsset.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAssetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RepayAsset"))
              .build();
        }
      }
    }
    return getRepayAssetMethod;
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
   * Msg defines the x/leverage module's Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * LendAsset defines a method for lending coins to the capital facility.
     * </pre>
     */
    public void lendAsset(umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAsset request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAssetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLendAssetMethod(), responseObserver);
    }

    /**
     * <pre>
     * WithdrawAsset defines a method for withdrawing previously lent coins from
     * the capital facility.
     * </pre>
     */
    public void withdrawAsset(umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAsset request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAssetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawAssetMethod(), responseObserver);
    }

    /**
     * <pre>
     * SetCollateral defines a method for users to enable or disable a uToken
     * denomination in their wallet for use as collateral.
     * </pre>
     */
    public void setCollateral(umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateral request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateralResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetCollateralMethod(), responseObserver);
    }

    /**
     * <pre>
     * BorrowAsset defines a method for borrowing coins from the capital facility.
     * </pre>
     */
    public void borrowAsset(umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAsset request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAssetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBorrowAssetMethod(), responseObserver);
    }

    /**
     * <pre>
     * RepayAsset defines a method for repaying borrowed coins to the capital facility.
     * </pre>
     */
    public void repayAsset(umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAsset request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAssetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRepayAssetMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLendAssetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAsset,
                umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAssetResponse>(
                  this, METHODID_LEND_ASSET)))
          .addMethod(
            getWithdrawAssetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAsset,
                umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAssetResponse>(
                  this, METHODID_WITHDRAW_ASSET)))
          .addMethod(
            getSetCollateralMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateral,
                umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateralResponse>(
                  this, METHODID_SET_COLLATERAL)))
          .addMethod(
            getBorrowAssetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAsset,
                umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAssetResponse>(
                  this, METHODID_BORROW_ASSET)))
          .addMethod(
            getRepayAssetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAsset,
                umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAssetResponse>(
                  this, METHODID_REPAY_ASSET)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the x/leverage module's Msg service.
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
     * LendAsset defines a method for lending coins to the capital facility.
     * </pre>
     */
    public void lendAsset(umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAsset request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAssetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLendAssetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WithdrawAsset defines a method for withdrawing previously lent coins from
     * the capital facility.
     * </pre>
     */
    public void withdrawAsset(umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAsset request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAssetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawAssetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SetCollateral defines a method for users to enable or disable a uToken
     * denomination in their wallet for use as collateral.
     * </pre>
     */
    public void setCollateral(umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateral request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateralResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetCollateralMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BorrowAsset defines a method for borrowing coins from the capital facility.
     * </pre>
     */
    public void borrowAsset(umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAsset request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAssetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBorrowAssetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RepayAsset defines a method for repaying borrowed coins to the capital facility.
     * </pre>
     */
    public void repayAsset(umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAsset request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAssetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRepayAssetMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the x/leverage module's Msg service.
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
     * LendAsset defines a method for lending coins to the capital facility.
     * </pre>
     */
    public umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAssetResponse lendAsset(umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAsset request) {
      return blockingUnaryCall(
          getChannel(), getLendAssetMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WithdrawAsset defines a method for withdrawing previously lent coins from
     * the capital facility.
     * </pre>
     */
    public umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAssetResponse withdrawAsset(umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAsset request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawAssetMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SetCollateral defines a method for users to enable or disable a uToken
     * denomination in their wallet for use as collateral.
     * </pre>
     */
    public umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateralResponse setCollateral(umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateral request) {
      return blockingUnaryCall(
          getChannel(), getSetCollateralMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BorrowAsset defines a method for borrowing coins from the capital facility.
     * </pre>
     */
    public umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAssetResponse borrowAsset(umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAsset request) {
      return blockingUnaryCall(
          getChannel(), getBorrowAssetMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RepayAsset defines a method for repaying borrowed coins to the capital facility.
     * </pre>
     */
    public umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAssetResponse repayAsset(umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAsset request) {
      return blockingUnaryCall(
          getChannel(), getRepayAssetMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the x/leverage module's Msg service.
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
     * LendAsset defines a method for lending coins to the capital facility.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAssetResponse> lendAsset(
        umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAsset request) {
      return futureUnaryCall(
          getChannel().newCall(getLendAssetMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WithdrawAsset defines a method for withdrawing previously lent coins from
     * the capital facility.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAssetResponse> withdrawAsset(
        umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAsset request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawAssetMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SetCollateral defines a method for users to enable or disable a uToken
     * denomination in their wallet for use as collateral.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateralResponse> setCollateral(
        umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateral request) {
      return futureUnaryCall(
          getChannel().newCall(getSetCollateralMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BorrowAsset defines a method for borrowing coins from the capital facility.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAssetResponse> borrowAsset(
        umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAsset request) {
      return futureUnaryCall(
          getChannel().newCall(getBorrowAssetMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RepayAsset defines a method for repaying borrowed coins to the capital facility.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAssetResponse> repayAsset(
        umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAsset request) {
      return futureUnaryCall(
          getChannel().newCall(getRepayAssetMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LEND_ASSET = 0;
  private static final int METHODID_WITHDRAW_ASSET = 1;
  private static final int METHODID_SET_COLLATERAL = 2;
  private static final int METHODID_BORROW_ASSET = 3;
  private static final int METHODID_REPAY_ASSET = 4;

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
        case METHODID_LEND_ASSET:
          serviceImpl.lendAsset((umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAsset) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.Tx.MsgLendAssetResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_ASSET:
          serviceImpl.withdrawAsset((umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAsset) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.Tx.MsgWithdrawAssetResponse>) responseObserver);
          break;
        case METHODID_SET_COLLATERAL:
          serviceImpl.setCollateral((umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateral) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.Tx.MsgSetCollateralResponse>) responseObserver);
          break;
        case METHODID_BORROW_ASSET:
          serviceImpl.borrowAsset((umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAsset) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.Tx.MsgBorrowAssetResponse>) responseObserver);
          break;
        case METHODID_REPAY_ASSET:
          serviceImpl.repayAsset((umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAsset) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.leverage.v1beta1.Tx.MsgRepayAssetResponse>) responseObserver);
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
      return umeenetwork.umee.leverage.v1beta1.Tx.getDescriptor();
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
              .addMethod(getLendAssetMethod())
              .addMethod(getWithdrawAssetMethod())
              .addMethod(getSetCollateralMethod())
              .addMethod(getBorrowAssetMethod())
              .addMethod(getRepayAssetMethod())
              .build();
        }
      }
    }
    return result;
  }
}
