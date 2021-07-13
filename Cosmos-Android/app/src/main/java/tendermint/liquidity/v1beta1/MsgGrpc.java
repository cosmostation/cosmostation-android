package tendermint.liquidity.v1beta1;

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
 * Msg defines the liquidity Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: tendermint/liquidity/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "tendermint.liquidity.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.Tx.MsgCreatePool,
      tendermint.liquidity.v1beta1.Tx.MsgCreatePoolResponse> getCreatePoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePool",
      requestType = tendermint.liquidity.v1beta1.Tx.MsgCreatePool.class,
      responseType = tendermint.liquidity.v1beta1.Tx.MsgCreatePoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.Tx.MsgCreatePool,
      tendermint.liquidity.v1beta1.Tx.MsgCreatePoolResponse> getCreatePoolMethod() {
    io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.Tx.MsgCreatePool, tendermint.liquidity.v1beta1.Tx.MsgCreatePoolResponse> getCreatePoolMethod;
    if ((getCreatePoolMethod = MsgGrpc.getCreatePoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreatePoolMethod = MsgGrpc.getCreatePoolMethod) == null) {
          MsgGrpc.getCreatePoolMethod = getCreatePoolMethod =
              io.grpc.MethodDescriptor.<tendermint.liquidity.v1beta1.Tx.MsgCreatePool, tendermint.liquidity.v1beta1.Tx.MsgCreatePoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.Tx.MsgCreatePool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.Tx.MsgCreatePoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreatePool"))
              .build();
        }
      }
    }
    return getCreatePoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch,
      tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatchResponse> getDepositWithinBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DepositWithinBatch",
      requestType = tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch.class,
      responseType = tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch,
      tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatchResponse> getDepositWithinBatchMethod() {
    io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch, tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatchResponse> getDepositWithinBatchMethod;
    if ((getDepositWithinBatchMethod = MsgGrpc.getDepositWithinBatchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDepositWithinBatchMethod = MsgGrpc.getDepositWithinBatchMethod) == null) {
          MsgGrpc.getDepositWithinBatchMethod = getDepositWithinBatchMethod =
              io.grpc.MethodDescriptor.<tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch, tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DepositWithinBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DepositWithinBatch"))
              .build();
        }
      }
    }
    return getDepositWithinBatchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch,
      tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatchResponse> getWithdrawWithinBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawWithinBatch",
      requestType = tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch.class,
      responseType = tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch,
      tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatchResponse> getWithdrawWithinBatchMethod() {
    io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch, tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatchResponse> getWithdrawWithinBatchMethod;
    if ((getWithdrawWithinBatchMethod = MsgGrpc.getWithdrawWithinBatchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawWithinBatchMethod = MsgGrpc.getWithdrawWithinBatchMethod) == null) {
          MsgGrpc.getWithdrawWithinBatchMethod = getWithdrawWithinBatchMethod =
              io.grpc.MethodDescriptor.<tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch, tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawWithinBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawWithinBatch"))
              .build();
        }
      }
    }
    return getWithdrawWithinBatchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch,
      tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatchResponse> getSwapMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Swap",
      requestType = tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch.class,
      responseType = tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch,
      tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatchResponse> getSwapMethod() {
    io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch, tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatchResponse> getSwapMethod;
    if ((getSwapMethod = MsgGrpc.getSwapMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSwapMethod = MsgGrpc.getSwapMethod) == null) {
          MsgGrpc.getSwapMethod = getSwapMethod =
              io.grpc.MethodDescriptor.<tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch, tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Swap"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Swap"))
              .build();
        }
      }
    }
    return getSwapMethod;
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
   * Msg defines the liquidity Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Submit a create liquidity pool message.
     * </pre>
     */
    public void createPool(tendermint.liquidity.v1beta1.Tx.MsgCreatePool request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.Tx.MsgCreatePoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreatePoolMethod(), responseObserver);
    }

    /**
     * <pre>
     * Submit a deposit to the liquidity pool batch.
     * </pre>
     */
    public void depositWithinBatch(tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositWithinBatchMethod(), responseObserver);
    }

    /**
     * <pre>
     * Submit a withdraw from the liquidity pool batch.
     * </pre>
     */
    public void withdrawWithinBatch(tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawWithinBatchMethod(), responseObserver);
    }

    /**
     * <pre>
     * Submit a swap to the liquidity pool batch.
     * </pre>
     */
    public void swap(tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwapMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreatePoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tendermint.liquidity.v1beta1.Tx.MsgCreatePool,
                tendermint.liquidity.v1beta1.Tx.MsgCreatePoolResponse>(
                  this, METHODID_CREATE_POOL)))
          .addMethod(
            getDepositWithinBatchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch,
                tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatchResponse>(
                  this, METHODID_DEPOSIT_WITHIN_BATCH)))
          .addMethod(
            getWithdrawWithinBatchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch,
                tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatchResponse>(
                  this, METHODID_WITHDRAW_WITHIN_BATCH)))
          .addMethod(
            getSwapMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch,
                tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatchResponse>(
                  this, METHODID_SWAP)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the liquidity Msg service.
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
     * Submit a create liquidity pool message.
     * </pre>
     */
    public void createPool(tendermint.liquidity.v1beta1.Tx.MsgCreatePool request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.Tx.MsgCreatePoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreatePoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Submit a deposit to the liquidity pool batch.
     * </pre>
     */
    public void depositWithinBatch(tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositWithinBatchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Submit a withdraw from the liquidity pool batch.
     * </pre>
     */
    public void withdrawWithinBatch(tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawWithinBatchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Submit a swap to the liquidity pool batch.
     * </pre>
     */
    public void swap(tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwapMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the liquidity Msg service.
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
     * Submit a create liquidity pool message.
     * </pre>
     */
    public tendermint.liquidity.v1beta1.Tx.MsgCreatePoolResponse createPool(tendermint.liquidity.v1beta1.Tx.MsgCreatePool request) {
      return blockingUnaryCall(
          getChannel(), getCreatePoolMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Submit a deposit to the liquidity pool batch.
     * </pre>
     */
    public tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatchResponse depositWithinBatch(tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch request) {
      return blockingUnaryCall(
          getChannel(), getDepositWithinBatchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Submit a withdraw from the liquidity pool batch.
     * </pre>
     */
    public tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatchResponse withdrawWithinBatch(tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawWithinBatchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Submit a swap to the liquidity pool batch.
     * </pre>
     */
    public tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatchResponse swap(tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch request) {
      return blockingUnaryCall(
          getChannel(), getSwapMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the liquidity Msg service.
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
     * Submit a create liquidity pool message.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tendermint.liquidity.v1beta1.Tx.MsgCreatePoolResponse> createPool(
        tendermint.liquidity.v1beta1.Tx.MsgCreatePool request) {
      return futureUnaryCall(
          getChannel().newCall(getCreatePoolMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Submit a deposit to the liquidity pool batch.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatchResponse> depositWithinBatch(
        tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositWithinBatchMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Submit a withdraw from the liquidity pool batch.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatchResponse> withdrawWithinBatch(
        tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawWithinBatchMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Submit a swap to the liquidity pool batch.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatchResponse> swap(
        tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch request) {
      return futureUnaryCall(
          getChannel().newCall(getSwapMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_POOL = 0;
  private static final int METHODID_DEPOSIT_WITHIN_BATCH = 1;
  private static final int METHODID_WITHDRAW_WITHIN_BATCH = 2;
  private static final int METHODID_SWAP = 3;

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
        case METHODID_CREATE_POOL:
          serviceImpl.createPool((tendermint.liquidity.v1beta1.Tx.MsgCreatePool) request,
              (io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.Tx.MsgCreatePoolResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT_WITHIN_BATCH:
          serviceImpl.depositWithinBatch((tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch) request,
              (io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatchResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_WITHIN_BATCH:
          serviceImpl.withdrawWithinBatch((tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch) request,
              (io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatchResponse>) responseObserver);
          break;
        case METHODID_SWAP:
          serviceImpl.swap((tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch) request,
              (io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatchResponse>) responseObserver);
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
      return tendermint.liquidity.v1beta1.Tx.getDescriptor();
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
              .addMethod(getCreatePoolMethod())
              .addMethod(getDepositWithinBatchMethod())
              .addMethod(getWithdrawWithinBatchMethod())
              .addMethod(getSwapMethod())
              .build();
        }
      }
    }
    return result;
  }
}
