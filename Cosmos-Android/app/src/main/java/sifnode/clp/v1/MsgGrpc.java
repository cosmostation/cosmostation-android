package sifnode.clp.v1;

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
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: sifnode/clp/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "sifnode.clp.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgRemoveLiquidity,
      sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse> getRemoveLiquidityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveLiquidity",
      requestType = sifnode.clp.v1.Tx.MsgRemoveLiquidity.class,
      responseType = sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgRemoveLiquidity,
      sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse> getRemoveLiquidityMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgRemoveLiquidity, sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse> getRemoveLiquidityMethod;
    if ((getRemoveLiquidityMethod = MsgGrpc.getRemoveLiquidityMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRemoveLiquidityMethod = MsgGrpc.getRemoveLiquidityMethod) == null) {
          MsgGrpc.getRemoveLiquidityMethod = getRemoveLiquidityMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgRemoveLiquidity, sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveLiquidity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgRemoveLiquidity.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RemoveLiquidity"))
              .build();
        }
      }
    }
    return getRemoveLiquidityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgCreatePool,
      sifnode.clp.v1.Tx.MsgCreatePoolResponse> getCreatePoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePool",
      requestType = sifnode.clp.v1.Tx.MsgCreatePool.class,
      responseType = sifnode.clp.v1.Tx.MsgCreatePoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgCreatePool,
      sifnode.clp.v1.Tx.MsgCreatePoolResponse> getCreatePoolMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgCreatePool, sifnode.clp.v1.Tx.MsgCreatePoolResponse> getCreatePoolMethod;
    if ((getCreatePoolMethod = MsgGrpc.getCreatePoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreatePoolMethod = MsgGrpc.getCreatePoolMethod) == null) {
          MsgGrpc.getCreatePoolMethod = getCreatePoolMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgCreatePool, sifnode.clp.v1.Tx.MsgCreatePoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgCreatePool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgCreatePoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreatePool"))
              .build();
        }
      }
    }
    return getCreatePoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgAddLiquidity,
      sifnode.clp.v1.Tx.MsgAddLiquidityResponse> getAddLiquidityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddLiquidity",
      requestType = sifnode.clp.v1.Tx.MsgAddLiquidity.class,
      responseType = sifnode.clp.v1.Tx.MsgAddLiquidityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgAddLiquidity,
      sifnode.clp.v1.Tx.MsgAddLiquidityResponse> getAddLiquidityMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgAddLiquidity, sifnode.clp.v1.Tx.MsgAddLiquidityResponse> getAddLiquidityMethod;
    if ((getAddLiquidityMethod = MsgGrpc.getAddLiquidityMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddLiquidityMethod = MsgGrpc.getAddLiquidityMethod) == null) {
          MsgGrpc.getAddLiquidityMethod = getAddLiquidityMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgAddLiquidity, sifnode.clp.v1.Tx.MsgAddLiquidityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddLiquidity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgAddLiquidity.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgAddLiquidityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddLiquidity"))
              .build();
        }
      }
    }
    return getAddLiquidityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgSwap,
      sifnode.clp.v1.Tx.MsgSwapResponse> getSwapMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Swap",
      requestType = sifnode.clp.v1.Tx.MsgSwap.class,
      responseType = sifnode.clp.v1.Tx.MsgSwapResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgSwap,
      sifnode.clp.v1.Tx.MsgSwapResponse> getSwapMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgSwap, sifnode.clp.v1.Tx.MsgSwapResponse> getSwapMethod;
    if ((getSwapMethod = MsgGrpc.getSwapMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSwapMethod = MsgGrpc.getSwapMethod) == null) {
          MsgGrpc.getSwapMethod = getSwapMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgSwap, sifnode.clp.v1.Tx.MsgSwapResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Swap"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgSwap.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgSwapResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Swap"))
              .build();
        }
      }
    }
    return getSwapMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgDecommissionPool,
      sifnode.clp.v1.Tx.MsgDecommissionPoolResponse> getDecommissionPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DecommissionPool",
      requestType = sifnode.clp.v1.Tx.MsgDecommissionPool.class,
      responseType = sifnode.clp.v1.Tx.MsgDecommissionPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgDecommissionPool,
      sifnode.clp.v1.Tx.MsgDecommissionPoolResponse> getDecommissionPoolMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Tx.MsgDecommissionPool, sifnode.clp.v1.Tx.MsgDecommissionPoolResponse> getDecommissionPoolMethod;
    if ((getDecommissionPoolMethod = MsgGrpc.getDecommissionPoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDecommissionPoolMethod = MsgGrpc.getDecommissionPoolMethod) == null) {
          MsgGrpc.getDecommissionPoolMethod = getDecommissionPoolMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Tx.MsgDecommissionPool, sifnode.clp.v1.Tx.MsgDecommissionPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DecommissionPool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgDecommissionPool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Tx.MsgDecommissionPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DecommissionPool"))
              .build();
        }
      }
    }
    return getDecommissionPoolMethod;
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
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     */
    public void removeLiquidity(sifnode.clp.v1.Tx.MsgRemoveLiquidity request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveLiquidityMethod(), responseObserver);
    }

    /**
     */
    public void createPool(sifnode.clp.v1.Tx.MsgCreatePool request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgCreatePoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreatePoolMethod(), responseObserver);
    }

    /**
     */
    public void addLiquidity(sifnode.clp.v1.Tx.MsgAddLiquidity request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgAddLiquidityResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddLiquidityMethod(), responseObserver);
    }

    /**
     */
    public void swap(sifnode.clp.v1.Tx.MsgSwap request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgSwapResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwapMethod(), responseObserver);
    }

    /**
     */
    public void decommissionPool(sifnode.clp.v1.Tx.MsgDecommissionPool request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgDecommissionPoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDecommissionPoolMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRemoveLiquidityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgRemoveLiquidity,
                sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse>(
                  this, METHODID_REMOVE_LIQUIDITY)))
          .addMethod(
            getCreatePoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgCreatePool,
                sifnode.clp.v1.Tx.MsgCreatePoolResponse>(
                  this, METHODID_CREATE_POOL)))
          .addMethod(
            getAddLiquidityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgAddLiquidity,
                sifnode.clp.v1.Tx.MsgAddLiquidityResponse>(
                  this, METHODID_ADD_LIQUIDITY)))
          .addMethod(
            getSwapMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgSwap,
                sifnode.clp.v1.Tx.MsgSwapResponse>(
                  this, METHODID_SWAP)))
          .addMethod(
            getDecommissionPoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Tx.MsgDecommissionPool,
                sifnode.clp.v1.Tx.MsgDecommissionPoolResponse>(
                  this, METHODID_DECOMMISSION_POOL)))
          .build();
    }
  }

  /**
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
     */
    public void removeLiquidity(sifnode.clp.v1.Tx.MsgRemoveLiquidity request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveLiquidityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createPool(sifnode.clp.v1.Tx.MsgCreatePool request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgCreatePoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreatePoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addLiquidity(sifnode.clp.v1.Tx.MsgAddLiquidity request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgAddLiquidityResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddLiquidityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void swap(sifnode.clp.v1.Tx.MsgSwap request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgSwapResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwapMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void decommissionPool(sifnode.clp.v1.Tx.MsgDecommissionPool request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgDecommissionPoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDecommissionPoolMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
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
     */
    public sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse removeLiquidity(sifnode.clp.v1.Tx.MsgRemoveLiquidity request) {
      return blockingUnaryCall(
          getChannel(), getRemoveLiquidityMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Tx.MsgCreatePoolResponse createPool(sifnode.clp.v1.Tx.MsgCreatePool request) {
      return blockingUnaryCall(
          getChannel(), getCreatePoolMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Tx.MsgAddLiquidityResponse addLiquidity(sifnode.clp.v1.Tx.MsgAddLiquidity request) {
      return blockingUnaryCall(
          getChannel(), getAddLiquidityMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Tx.MsgSwapResponse swap(sifnode.clp.v1.Tx.MsgSwap request) {
      return blockingUnaryCall(
          getChannel(), getSwapMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Tx.MsgDecommissionPoolResponse decommissionPool(sifnode.clp.v1.Tx.MsgDecommissionPool request) {
      return blockingUnaryCall(
          getChannel(), getDecommissionPoolMethod(), getCallOptions(), request);
    }
  }

  /**
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
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse> removeLiquidity(
        sifnode.clp.v1.Tx.MsgRemoveLiquidity request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveLiquidityMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgCreatePoolResponse> createPool(
        sifnode.clp.v1.Tx.MsgCreatePool request) {
      return futureUnaryCall(
          getChannel().newCall(getCreatePoolMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgAddLiquidityResponse> addLiquidity(
        sifnode.clp.v1.Tx.MsgAddLiquidity request) {
      return futureUnaryCall(
          getChannel().newCall(getAddLiquidityMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgSwapResponse> swap(
        sifnode.clp.v1.Tx.MsgSwap request) {
      return futureUnaryCall(
          getChannel().newCall(getSwapMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Tx.MsgDecommissionPoolResponse> decommissionPool(
        sifnode.clp.v1.Tx.MsgDecommissionPool request) {
      return futureUnaryCall(
          getChannel().newCall(getDecommissionPoolMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REMOVE_LIQUIDITY = 0;
  private static final int METHODID_CREATE_POOL = 1;
  private static final int METHODID_ADD_LIQUIDITY = 2;
  private static final int METHODID_SWAP = 3;
  private static final int METHODID_DECOMMISSION_POOL = 4;

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
        case METHODID_REMOVE_LIQUIDITY:
          serviceImpl.removeLiquidity((sifnode.clp.v1.Tx.MsgRemoveLiquidity) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgRemoveLiquidityResponse>) responseObserver);
          break;
        case METHODID_CREATE_POOL:
          serviceImpl.createPool((sifnode.clp.v1.Tx.MsgCreatePool) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgCreatePoolResponse>) responseObserver);
          break;
        case METHODID_ADD_LIQUIDITY:
          serviceImpl.addLiquidity((sifnode.clp.v1.Tx.MsgAddLiquidity) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgAddLiquidityResponse>) responseObserver);
          break;
        case METHODID_SWAP:
          serviceImpl.swap((sifnode.clp.v1.Tx.MsgSwap) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgSwapResponse>) responseObserver);
          break;
        case METHODID_DECOMMISSION_POOL:
          serviceImpl.decommissionPool((sifnode.clp.v1.Tx.MsgDecommissionPool) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Tx.MsgDecommissionPoolResponse>) responseObserver);
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
      return sifnode.clp.v1.Tx.getDescriptor();
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
              .addMethod(getRemoveLiquidityMethod())
              .addMethod(getCreatePoolMethod())
              .addMethod(getAddLiquidityMethod())
              .addMethod(getSwapMethod())
              .addMethod(getDecommissionPoolMethod())
              .build();
        }
      }
    }
    return result;
  }
}
