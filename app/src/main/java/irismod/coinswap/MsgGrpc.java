package irismod.coinswap;

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
 * Msg defines the coinswap Msg service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: coinswap/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "irismod.coinswap.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<irismod.coinswap.Tx.MsgAddLiquidity,
      irismod.coinswap.Tx.MsgAddLiquidityResponse> getAddLiquidityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddLiquidity",
      requestType = irismod.coinswap.Tx.MsgAddLiquidity.class,
      responseType = irismod.coinswap.Tx.MsgAddLiquidityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.coinswap.Tx.MsgAddLiquidity,
      irismod.coinswap.Tx.MsgAddLiquidityResponse> getAddLiquidityMethod() {
    io.grpc.MethodDescriptor<irismod.coinswap.Tx.MsgAddLiquidity, irismod.coinswap.Tx.MsgAddLiquidityResponse> getAddLiquidityMethod;
    if ((getAddLiquidityMethod = MsgGrpc.getAddLiquidityMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddLiquidityMethod = MsgGrpc.getAddLiquidityMethod) == null) {
          MsgGrpc.getAddLiquidityMethod = getAddLiquidityMethod =
              io.grpc.MethodDescriptor.<irismod.coinswap.Tx.MsgAddLiquidity, irismod.coinswap.Tx.MsgAddLiquidityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddLiquidity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.coinswap.Tx.MsgAddLiquidity.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.coinswap.Tx.MsgAddLiquidityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddLiquidity"))
              .build();
        }
      }
    }
    return getAddLiquidityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.coinswap.Tx.MsgRemoveLiquidity,
      irismod.coinswap.Tx.MsgRemoveLiquidityResponse> getRemoveLiquidityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveLiquidity",
      requestType = irismod.coinswap.Tx.MsgRemoveLiquidity.class,
      responseType = irismod.coinswap.Tx.MsgRemoveLiquidityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.coinswap.Tx.MsgRemoveLiquidity,
      irismod.coinswap.Tx.MsgRemoveLiquidityResponse> getRemoveLiquidityMethod() {
    io.grpc.MethodDescriptor<irismod.coinswap.Tx.MsgRemoveLiquidity, irismod.coinswap.Tx.MsgRemoveLiquidityResponse> getRemoveLiquidityMethod;
    if ((getRemoveLiquidityMethod = MsgGrpc.getRemoveLiquidityMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRemoveLiquidityMethod = MsgGrpc.getRemoveLiquidityMethod) == null) {
          MsgGrpc.getRemoveLiquidityMethod = getRemoveLiquidityMethod =
              io.grpc.MethodDescriptor.<irismod.coinswap.Tx.MsgRemoveLiquidity, irismod.coinswap.Tx.MsgRemoveLiquidityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveLiquidity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.coinswap.Tx.MsgRemoveLiquidity.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.coinswap.Tx.MsgRemoveLiquidityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RemoveLiquidity"))
              .build();
        }
      }
    }
    return getRemoveLiquidityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.coinswap.Tx.MsgSwapOrder,
      irismod.coinswap.Tx.MsgSwapCoinResponse> getSwapCoinMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SwapCoin",
      requestType = irismod.coinswap.Tx.MsgSwapOrder.class,
      responseType = irismod.coinswap.Tx.MsgSwapCoinResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.coinswap.Tx.MsgSwapOrder,
      irismod.coinswap.Tx.MsgSwapCoinResponse> getSwapCoinMethod() {
    io.grpc.MethodDescriptor<irismod.coinswap.Tx.MsgSwapOrder, irismod.coinswap.Tx.MsgSwapCoinResponse> getSwapCoinMethod;
    if ((getSwapCoinMethod = MsgGrpc.getSwapCoinMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSwapCoinMethod = MsgGrpc.getSwapCoinMethod) == null) {
          MsgGrpc.getSwapCoinMethod = getSwapCoinMethod =
              io.grpc.MethodDescriptor.<irismod.coinswap.Tx.MsgSwapOrder, irismod.coinswap.Tx.MsgSwapCoinResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SwapCoin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.coinswap.Tx.MsgSwapOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.coinswap.Tx.MsgSwapCoinResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SwapCoin"))
              .build();
        }
      }
    }
    return getSwapCoinMethod;
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
   * Msg defines the coinswap Msg service
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * AddLiquidity defines a method for depositing some tokens to the liquidity pool
     * </pre>
     */
    public void addLiquidity(irismod.coinswap.Tx.MsgAddLiquidity request,
        io.grpc.stub.StreamObserver<irismod.coinswap.Tx.MsgAddLiquidityResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddLiquidityMethod(), responseObserver);
    }

    /**
     * <pre>
     * RemoveLiquidity defines a method for withdraw some tokens from the liquidity pool
     * </pre>
     */
    public void removeLiquidity(irismod.coinswap.Tx.MsgRemoveLiquidity request,
        io.grpc.stub.StreamObserver<irismod.coinswap.Tx.MsgRemoveLiquidityResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveLiquidityMethod(), responseObserver);
    }

    /**
     * <pre>
     * SwapCoin defines a method for swapping a token with the other token from the liquidity pool
     * </pre>
     */
    public void swapCoin(irismod.coinswap.Tx.MsgSwapOrder request,
        io.grpc.stub.StreamObserver<irismod.coinswap.Tx.MsgSwapCoinResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwapCoinMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddLiquidityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.coinswap.Tx.MsgAddLiquidity,
                irismod.coinswap.Tx.MsgAddLiquidityResponse>(
                  this, METHODID_ADD_LIQUIDITY)))
          .addMethod(
            getRemoveLiquidityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.coinswap.Tx.MsgRemoveLiquidity,
                irismod.coinswap.Tx.MsgRemoveLiquidityResponse>(
                  this, METHODID_REMOVE_LIQUIDITY)))
          .addMethod(
            getSwapCoinMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.coinswap.Tx.MsgSwapOrder,
                irismod.coinswap.Tx.MsgSwapCoinResponse>(
                  this, METHODID_SWAP_COIN)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the coinswap Msg service
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
     * AddLiquidity defines a method for depositing some tokens to the liquidity pool
     * </pre>
     */
    public void addLiquidity(irismod.coinswap.Tx.MsgAddLiquidity request,
        io.grpc.stub.StreamObserver<irismod.coinswap.Tx.MsgAddLiquidityResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddLiquidityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RemoveLiquidity defines a method for withdraw some tokens from the liquidity pool
     * </pre>
     */
    public void removeLiquidity(irismod.coinswap.Tx.MsgRemoveLiquidity request,
        io.grpc.stub.StreamObserver<irismod.coinswap.Tx.MsgRemoveLiquidityResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveLiquidityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SwapCoin defines a method for swapping a token with the other token from the liquidity pool
     * </pre>
     */
    public void swapCoin(irismod.coinswap.Tx.MsgSwapOrder request,
        io.grpc.stub.StreamObserver<irismod.coinswap.Tx.MsgSwapCoinResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwapCoinMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the coinswap Msg service
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
     * AddLiquidity defines a method for depositing some tokens to the liquidity pool
     * </pre>
     */
    public irismod.coinswap.Tx.MsgAddLiquidityResponse addLiquidity(irismod.coinswap.Tx.MsgAddLiquidity request) {
      return blockingUnaryCall(
          getChannel(), getAddLiquidityMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RemoveLiquidity defines a method for withdraw some tokens from the liquidity pool
     * </pre>
     */
    public irismod.coinswap.Tx.MsgRemoveLiquidityResponse removeLiquidity(irismod.coinswap.Tx.MsgRemoveLiquidity request) {
      return blockingUnaryCall(
          getChannel(), getRemoveLiquidityMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SwapCoin defines a method for swapping a token with the other token from the liquidity pool
     * </pre>
     */
    public irismod.coinswap.Tx.MsgSwapCoinResponse swapCoin(irismod.coinswap.Tx.MsgSwapOrder request) {
      return blockingUnaryCall(
          getChannel(), getSwapCoinMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the coinswap Msg service
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
     * AddLiquidity defines a method for depositing some tokens to the liquidity pool
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.coinswap.Tx.MsgAddLiquidityResponse> addLiquidity(
        irismod.coinswap.Tx.MsgAddLiquidity request) {
      return futureUnaryCall(
          getChannel().newCall(getAddLiquidityMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RemoveLiquidity defines a method for withdraw some tokens from the liquidity pool
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.coinswap.Tx.MsgRemoveLiquidityResponse> removeLiquidity(
        irismod.coinswap.Tx.MsgRemoveLiquidity request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveLiquidityMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SwapCoin defines a method for swapping a token with the other token from the liquidity pool
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.coinswap.Tx.MsgSwapCoinResponse> swapCoin(
        irismod.coinswap.Tx.MsgSwapOrder request) {
      return futureUnaryCall(
          getChannel().newCall(getSwapCoinMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_LIQUIDITY = 0;
  private static final int METHODID_REMOVE_LIQUIDITY = 1;
  private static final int METHODID_SWAP_COIN = 2;

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
        case METHODID_ADD_LIQUIDITY:
          serviceImpl.addLiquidity((irismod.coinswap.Tx.MsgAddLiquidity) request,
              (io.grpc.stub.StreamObserver<irismod.coinswap.Tx.MsgAddLiquidityResponse>) responseObserver);
          break;
        case METHODID_REMOVE_LIQUIDITY:
          serviceImpl.removeLiquidity((irismod.coinswap.Tx.MsgRemoveLiquidity) request,
              (io.grpc.stub.StreamObserver<irismod.coinswap.Tx.MsgRemoveLiquidityResponse>) responseObserver);
          break;
        case METHODID_SWAP_COIN:
          serviceImpl.swapCoin((irismod.coinswap.Tx.MsgSwapOrder) request,
              (io.grpc.stub.StreamObserver<irismod.coinswap.Tx.MsgSwapCoinResponse>) responseObserver);
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
      return irismod.coinswap.Tx.getDescriptor();
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
              .addMethod(getAddLiquidityMethod())
              .addMethod(getRemoveLiquidityMethod())
              .addMethod(getSwapCoinMethod())
              .build();
        }
      }
    }
    return result;
  }
}
