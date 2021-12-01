package irismod.farm;

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
 * Msg defines the farm Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: iris_mod/farm/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "irismod.farm.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<irismod.farm.Tx.MsgCreatePool,
      irismod.farm.Tx.MsgCreatePoolResponse> getCreatePoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePool",
      requestType = irismod.farm.Tx.MsgCreatePool.class,
      responseType = irismod.farm.Tx.MsgCreatePoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.farm.Tx.MsgCreatePool,
      irismod.farm.Tx.MsgCreatePoolResponse> getCreatePoolMethod() {
    io.grpc.MethodDescriptor<irismod.farm.Tx.MsgCreatePool, irismod.farm.Tx.MsgCreatePoolResponse> getCreatePoolMethod;
    if ((getCreatePoolMethod = MsgGrpc.getCreatePoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreatePoolMethod = MsgGrpc.getCreatePoolMethod) == null) {
          MsgGrpc.getCreatePoolMethod = getCreatePoolMethod =
              io.grpc.MethodDescriptor.<irismod.farm.Tx.MsgCreatePool, irismod.farm.Tx.MsgCreatePoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.farm.Tx.MsgCreatePool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.farm.Tx.MsgCreatePoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreatePool"))
              .build();
        }
      }
    }
    return getCreatePoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.farm.Tx.MsgDestroyPool,
      irismod.farm.Tx.MsgDestroyPoolResponse> getDestroyPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DestroyPool",
      requestType = irismod.farm.Tx.MsgDestroyPool.class,
      responseType = irismod.farm.Tx.MsgDestroyPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.farm.Tx.MsgDestroyPool,
      irismod.farm.Tx.MsgDestroyPoolResponse> getDestroyPoolMethod() {
    io.grpc.MethodDescriptor<irismod.farm.Tx.MsgDestroyPool, irismod.farm.Tx.MsgDestroyPoolResponse> getDestroyPoolMethod;
    if ((getDestroyPoolMethod = MsgGrpc.getDestroyPoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDestroyPoolMethod = MsgGrpc.getDestroyPoolMethod) == null) {
          MsgGrpc.getDestroyPoolMethod = getDestroyPoolMethod =
              io.grpc.MethodDescriptor.<irismod.farm.Tx.MsgDestroyPool, irismod.farm.Tx.MsgDestroyPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DestroyPool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.farm.Tx.MsgDestroyPool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.farm.Tx.MsgDestroyPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DestroyPool"))
              .build();
        }
      }
    }
    return getDestroyPoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.farm.Tx.MsgAdjustPool,
      irismod.farm.Tx.MsgAdjustPoolResponse> getAdjustPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AdjustPool",
      requestType = irismod.farm.Tx.MsgAdjustPool.class,
      responseType = irismod.farm.Tx.MsgAdjustPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.farm.Tx.MsgAdjustPool,
      irismod.farm.Tx.MsgAdjustPoolResponse> getAdjustPoolMethod() {
    io.grpc.MethodDescriptor<irismod.farm.Tx.MsgAdjustPool, irismod.farm.Tx.MsgAdjustPoolResponse> getAdjustPoolMethod;
    if ((getAdjustPoolMethod = MsgGrpc.getAdjustPoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAdjustPoolMethod = MsgGrpc.getAdjustPoolMethod) == null) {
          MsgGrpc.getAdjustPoolMethod = getAdjustPoolMethod =
              io.grpc.MethodDescriptor.<irismod.farm.Tx.MsgAdjustPool, irismod.farm.Tx.MsgAdjustPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AdjustPool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.farm.Tx.MsgAdjustPool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.farm.Tx.MsgAdjustPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AdjustPool"))
              .build();
        }
      }
    }
    return getAdjustPoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.farm.Tx.MsgStake,
      irismod.farm.Tx.MsgStakeResponse> getStakeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Stake",
      requestType = irismod.farm.Tx.MsgStake.class,
      responseType = irismod.farm.Tx.MsgStakeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.farm.Tx.MsgStake,
      irismod.farm.Tx.MsgStakeResponse> getStakeMethod() {
    io.grpc.MethodDescriptor<irismod.farm.Tx.MsgStake, irismod.farm.Tx.MsgStakeResponse> getStakeMethod;
    if ((getStakeMethod = MsgGrpc.getStakeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getStakeMethod = MsgGrpc.getStakeMethod) == null) {
          MsgGrpc.getStakeMethod = getStakeMethod =
              io.grpc.MethodDescriptor.<irismod.farm.Tx.MsgStake, irismod.farm.Tx.MsgStakeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Stake"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.farm.Tx.MsgStake.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.farm.Tx.MsgStakeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Stake"))
              .build();
        }
      }
    }
    return getStakeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.farm.Tx.MsgUnstake,
      irismod.farm.Tx.MsgUnstakeResponse> getUnstakeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Unstake",
      requestType = irismod.farm.Tx.MsgUnstake.class,
      responseType = irismod.farm.Tx.MsgUnstakeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.farm.Tx.MsgUnstake,
      irismod.farm.Tx.MsgUnstakeResponse> getUnstakeMethod() {
    io.grpc.MethodDescriptor<irismod.farm.Tx.MsgUnstake, irismod.farm.Tx.MsgUnstakeResponse> getUnstakeMethod;
    if ((getUnstakeMethod = MsgGrpc.getUnstakeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUnstakeMethod = MsgGrpc.getUnstakeMethod) == null) {
          MsgGrpc.getUnstakeMethod = getUnstakeMethod =
              io.grpc.MethodDescriptor.<irismod.farm.Tx.MsgUnstake, irismod.farm.Tx.MsgUnstakeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Unstake"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.farm.Tx.MsgUnstake.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.farm.Tx.MsgUnstakeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Unstake"))
              .build();
        }
      }
    }
    return getUnstakeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.farm.Tx.MsgHarvest,
      irismod.farm.Tx.MsgHarvestResponse> getHarvestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Harvest",
      requestType = irismod.farm.Tx.MsgHarvest.class,
      responseType = irismod.farm.Tx.MsgHarvestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.farm.Tx.MsgHarvest,
      irismod.farm.Tx.MsgHarvestResponse> getHarvestMethod() {
    io.grpc.MethodDescriptor<irismod.farm.Tx.MsgHarvest, irismod.farm.Tx.MsgHarvestResponse> getHarvestMethod;
    if ((getHarvestMethod = MsgGrpc.getHarvestMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getHarvestMethod = MsgGrpc.getHarvestMethod) == null) {
          MsgGrpc.getHarvestMethod = getHarvestMethod =
              io.grpc.MethodDescriptor.<irismod.farm.Tx.MsgHarvest, irismod.farm.Tx.MsgHarvestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Harvest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.farm.Tx.MsgHarvest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.farm.Tx.MsgHarvestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Harvest"))
              .build();
        }
      }
    }
    return getHarvestMethod;
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
   * Msg defines the farm Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreatePool defines a method for creating a new farm pool
     * </pre>
     */
    public void createPool(irismod.farm.Tx.MsgCreatePool request,
        io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgCreatePoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreatePoolMethod(), responseObserver);
    }

    /**
     * <pre>
     * DestroyPool defines a method for destroying a existed farm pool
     * </pre>
     */
    public void destroyPool(irismod.farm.Tx.MsgDestroyPool request,
        io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgDestroyPoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDestroyPoolMethod(), responseObserver);
    }

    /**
     * <pre>
     * AdjustPool defines a method for adjusting the farm pool params
     * </pre>
     */
    public void adjustPool(irismod.farm.Tx.MsgAdjustPool request,
        io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgAdjustPoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAdjustPoolMethod(), responseObserver);
    }

    /**
     * <pre>
     * Stake defines a method for staking some lp token to a farm pool
     * </pre>
     */
    public void stake(irismod.farm.Tx.MsgStake request,
        io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgStakeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStakeMethod(), responseObserver);
    }

    /**
     * <pre>
     * Unstake defines a method for unstaking some lp token from a farm pool and
     * withdraw some reward
     * </pre>
     */
    public void unstake(irismod.farm.Tx.MsgUnstake request,
        io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgUnstakeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnstakeMethod(), responseObserver);
    }

    /**
     * <pre>
     * Harvest defines a method withdraw some reward from a farm pool
     * </pre>
     */
    public void harvest(irismod.farm.Tx.MsgHarvest request,
        io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgHarvestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHarvestMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreatePoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.farm.Tx.MsgCreatePool,
                irismod.farm.Tx.MsgCreatePoolResponse>(
                  this, METHODID_CREATE_POOL)))
          .addMethod(
            getDestroyPoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.farm.Tx.MsgDestroyPool,
                irismod.farm.Tx.MsgDestroyPoolResponse>(
                  this, METHODID_DESTROY_POOL)))
          .addMethod(
            getAdjustPoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.farm.Tx.MsgAdjustPool,
                irismod.farm.Tx.MsgAdjustPoolResponse>(
                  this, METHODID_ADJUST_POOL)))
          .addMethod(
            getStakeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.farm.Tx.MsgStake,
                irismod.farm.Tx.MsgStakeResponse>(
                  this, METHODID_STAKE)))
          .addMethod(
            getUnstakeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.farm.Tx.MsgUnstake,
                irismod.farm.Tx.MsgUnstakeResponse>(
                  this, METHODID_UNSTAKE)))
          .addMethod(
            getHarvestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.farm.Tx.MsgHarvest,
                irismod.farm.Tx.MsgHarvestResponse>(
                  this, METHODID_HARVEST)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the farm Msg service.
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
     * CreatePool defines a method for creating a new farm pool
     * </pre>
     */
    public void createPool(irismod.farm.Tx.MsgCreatePool request,
        io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgCreatePoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreatePoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DestroyPool defines a method for destroying a existed farm pool
     * </pre>
     */
    public void destroyPool(irismod.farm.Tx.MsgDestroyPool request,
        io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgDestroyPoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDestroyPoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AdjustPool defines a method for adjusting the farm pool params
     * </pre>
     */
    public void adjustPool(irismod.farm.Tx.MsgAdjustPool request,
        io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgAdjustPoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAdjustPoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Stake defines a method for staking some lp token to a farm pool
     * </pre>
     */
    public void stake(irismod.farm.Tx.MsgStake request,
        io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgStakeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStakeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Unstake defines a method for unstaking some lp token from a farm pool and
     * withdraw some reward
     * </pre>
     */
    public void unstake(irismod.farm.Tx.MsgUnstake request,
        io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgUnstakeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnstakeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Harvest defines a method withdraw some reward from a farm pool
     * </pre>
     */
    public void harvest(irismod.farm.Tx.MsgHarvest request,
        io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgHarvestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHarvestMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the farm Msg service.
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
     * CreatePool defines a method for creating a new farm pool
     * </pre>
     */
    public irismod.farm.Tx.MsgCreatePoolResponse createPool(irismod.farm.Tx.MsgCreatePool request) {
      return blockingUnaryCall(
          getChannel(), getCreatePoolMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DestroyPool defines a method for destroying a existed farm pool
     * </pre>
     */
    public irismod.farm.Tx.MsgDestroyPoolResponse destroyPool(irismod.farm.Tx.MsgDestroyPool request) {
      return blockingUnaryCall(
          getChannel(), getDestroyPoolMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AdjustPool defines a method for adjusting the farm pool params
     * </pre>
     */
    public irismod.farm.Tx.MsgAdjustPoolResponse adjustPool(irismod.farm.Tx.MsgAdjustPool request) {
      return blockingUnaryCall(
          getChannel(), getAdjustPoolMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Stake defines a method for staking some lp token to a farm pool
     * </pre>
     */
    public irismod.farm.Tx.MsgStakeResponse stake(irismod.farm.Tx.MsgStake request) {
      return blockingUnaryCall(
          getChannel(), getStakeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Unstake defines a method for unstaking some lp token from a farm pool and
     * withdraw some reward
     * </pre>
     */
    public irismod.farm.Tx.MsgUnstakeResponse unstake(irismod.farm.Tx.MsgUnstake request) {
      return blockingUnaryCall(
          getChannel(), getUnstakeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Harvest defines a method withdraw some reward from a farm pool
     * </pre>
     */
    public irismod.farm.Tx.MsgHarvestResponse harvest(irismod.farm.Tx.MsgHarvest request) {
      return blockingUnaryCall(
          getChannel(), getHarvestMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the farm Msg service.
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
     * CreatePool defines a method for creating a new farm pool
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.farm.Tx.MsgCreatePoolResponse> createPool(
        irismod.farm.Tx.MsgCreatePool request) {
      return futureUnaryCall(
          getChannel().newCall(getCreatePoolMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DestroyPool defines a method for destroying a existed farm pool
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.farm.Tx.MsgDestroyPoolResponse> destroyPool(
        irismod.farm.Tx.MsgDestroyPool request) {
      return futureUnaryCall(
          getChannel().newCall(getDestroyPoolMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AdjustPool defines a method for adjusting the farm pool params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.farm.Tx.MsgAdjustPoolResponse> adjustPool(
        irismod.farm.Tx.MsgAdjustPool request) {
      return futureUnaryCall(
          getChannel().newCall(getAdjustPoolMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Stake defines a method for staking some lp token to a farm pool
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.farm.Tx.MsgStakeResponse> stake(
        irismod.farm.Tx.MsgStake request) {
      return futureUnaryCall(
          getChannel().newCall(getStakeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Unstake defines a method for unstaking some lp token from a farm pool and
     * withdraw some reward
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.farm.Tx.MsgUnstakeResponse> unstake(
        irismod.farm.Tx.MsgUnstake request) {
      return futureUnaryCall(
          getChannel().newCall(getUnstakeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Harvest defines a method withdraw some reward from a farm pool
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.farm.Tx.MsgHarvestResponse> harvest(
        irismod.farm.Tx.MsgHarvest request) {
      return futureUnaryCall(
          getChannel().newCall(getHarvestMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_POOL = 0;
  private static final int METHODID_DESTROY_POOL = 1;
  private static final int METHODID_ADJUST_POOL = 2;
  private static final int METHODID_STAKE = 3;
  private static final int METHODID_UNSTAKE = 4;
  private static final int METHODID_HARVEST = 5;

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
          serviceImpl.createPool((irismod.farm.Tx.MsgCreatePool) request,
              (io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgCreatePoolResponse>) responseObserver);
          break;
        case METHODID_DESTROY_POOL:
          serviceImpl.destroyPool((irismod.farm.Tx.MsgDestroyPool) request,
              (io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgDestroyPoolResponse>) responseObserver);
          break;
        case METHODID_ADJUST_POOL:
          serviceImpl.adjustPool((irismod.farm.Tx.MsgAdjustPool) request,
              (io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgAdjustPoolResponse>) responseObserver);
          break;
        case METHODID_STAKE:
          serviceImpl.stake((irismod.farm.Tx.MsgStake) request,
              (io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgStakeResponse>) responseObserver);
          break;
        case METHODID_UNSTAKE:
          serviceImpl.unstake((irismod.farm.Tx.MsgUnstake) request,
              (io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgUnstakeResponse>) responseObserver);
          break;
        case METHODID_HARVEST:
          serviceImpl.harvest((irismod.farm.Tx.MsgHarvest) request,
              (io.grpc.stub.StreamObserver<irismod.farm.Tx.MsgHarvestResponse>) responseObserver);
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
      return irismod.farm.Tx.getDescriptor();
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
              .addMethod(getDestroyPoolMethod())
              .addMethod(getAdjustPoolMethod())
              .addMethod(getStakeMethod())
              .addMethod(getUnstakeMethod())
              .addMethod(getHarvestMethod())
              .build();
        }
      }
    }
    return result;
  }
}
