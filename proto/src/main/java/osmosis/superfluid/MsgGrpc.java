package osmosis.superfluid;

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
 * Msg defines the Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: osmosis/superfluid/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "osmosis.superfluid.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<osmosis.superfluid.Tx.MsgSuperfluidDelegate,
      osmosis.superfluid.Tx.MsgSuperfluidDelegateResponse> getSuperfluidDelegateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SuperfluidDelegate",
      requestType = osmosis.superfluid.Tx.MsgSuperfluidDelegate.class,
      responseType = osmosis.superfluid.Tx.MsgSuperfluidDelegateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.superfluid.Tx.MsgSuperfluidDelegate,
      osmosis.superfluid.Tx.MsgSuperfluidDelegateResponse> getSuperfluidDelegateMethod() {
    io.grpc.MethodDescriptor<osmosis.superfluid.Tx.MsgSuperfluidDelegate, osmosis.superfluid.Tx.MsgSuperfluidDelegateResponse> getSuperfluidDelegateMethod;
    if ((getSuperfluidDelegateMethod = MsgGrpc.getSuperfluidDelegateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSuperfluidDelegateMethod = MsgGrpc.getSuperfluidDelegateMethod) == null) {
          MsgGrpc.getSuperfluidDelegateMethod = getSuperfluidDelegateMethod =
              io.grpc.MethodDescriptor.<osmosis.superfluid.Tx.MsgSuperfluidDelegate, osmosis.superfluid.Tx.MsgSuperfluidDelegateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SuperfluidDelegate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.Tx.MsgSuperfluidDelegate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.Tx.MsgSuperfluidDelegateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SuperfluidDelegate"))
              .build();
        }
      }
    }
    return getSuperfluidDelegateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.superfluid.Tx.MsgSuperfluidUndelegate,
      osmosis.superfluid.Tx.MsgSuperfluidUndelegateResponse> getSuperfluidUndelegateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SuperfluidUndelegate",
      requestType = osmosis.superfluid.Tx.MsgSuperfluidUndelegate.class,
      responseType = osmosis.superfluid.Tx.MsgSuperfluidUndelegateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.superfluid.Tx.MsgSuperfluidUndelegate,
      osmosis.superfluid.Tx.MsgSuperfluidUndelegateResponse> getSuperfluidUndelegateMethod() {
    io.grpc.MethodDescriptor<osmosis.superfluid.Tx.MsgSuperfluidUndelegate, osmosis.superfluid.Tx.MsgSuperfluidUndelegateResponse> getSuperfluidUndelegateMethod;
    if ((getSuperfluidUndelegateMethod = MsgGrpc.getSuperfluidUndelegateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSuperfluidUndelegateMethod = MsgGrpc.getSuperfluidUndelegateMethod) == null) {
          MsgGrpc.getSuperfluidUndelegateMethod = getSuperfluidUndelegateMethod =
              io.grpc.MethodDescriptor.<osmosis.superfluid.Tx.MsgSuperfluidUndelegate, osmosis.superfluid.Tx.MsgSuperfluidUndelegateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SuperfluidUndelegate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.Tx.MsgSuperfluidUndelegate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.Tx.MsgSuperfluidUndelegateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SuperfluidUndelegate"))
              .build();
        }
      }
    }
    return getSuperfluidUndelegateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.superfluid.Tx.MsgSuperfluidUnbondLock,
      osmosis.superfluid.Tx.MsgSuperfluidUnbondLockResponse> getSuperfluidUnbondLockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SuperfluidUnbondLock",
      requestType = osmosis.superfluid.Tx.MsgSuperfluidUnbondLock.class,
      responseType = osmosis.superfluid.Tx.MsgSuperfluidUnbondLockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.superfluid.Tx.MsgSuperfluidUnbondLock,
      osmosis.superfluid.Tx.MsgSuperfluidUnbondLockResponse> getSuperfluidUnbondLockMethod() {
    io.grpc.MethodDescriptor<osmosis.superfluid.Tx.MsgSuperfluidUnbondLock, osmosis.superfluid.Tx.MsgSuperfluidUnbondLockResponse> getSuperfluidUnbondLockMethod;
    if ((getSuperfluidUnbondLockMethod = MsgGrpc.getSuperfluidUnbondLockMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSuperfluidUnbondLockMethod = MsgGrpc.getSuperfluidUnbondLockMethod) == null) {
          MsgGrpc.getSuperfluidUnbondLockMethod = getSuperfluidUnbondLockMethod =
              io.grpc.MethodDescriptor.<osmosis.superfluid.Tx.MsgSuperfluidUnbondLock, osmosis.superfluid.Tx.MsgSuperfluidUnbondLockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SuperfluidUnbondLock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.Tx.MsgSuperfluidUnbondLock.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.Tx.MsgSuperfluidUnbondLockResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SuperfluidUnbondLock"))
              .build();
        }
      }
    }
    return getSuperfluidUnbondLockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegate,
      osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegateResponse> getLockAndSuperfluidDelegateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LockAndSuperfluidDelegate",
      requestType = osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegate.class,
      responseType = osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegate,
      osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegateResponse> getLockAndSuperfluidDelegateMethod() {
    io.grpc.MethodDescriptor<osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegate, osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegateResponse> getLockAndSuperfluidDelegateMethod;
    if ((getLockAndSuperfluidDelegateMethod = MsgGrpc.getLockAndSuperfluidDelegateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLockAndSuperfluidDelegateMethod = MsgGrpc.getLockAndSuperfluidDelegateMethod) == null) {
          MsgGrpc.getLockAndSuperfluidDelegateMethod = getLockAndSuperfluidDelegateMethod =
              io.grpc.MethodDescriptor.<osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegate, osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LockAndSuperfluidDelegate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("LockAndSuperfluidDelegate"))
              .build();
        }
      }
    }
    return getLockAndSuperfluidDelegateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.superfluid.Tx.MsgUnPoolWhitelistedPool,
      osmosis.superfluid.Tx.MsgUnPoolWhitelistedPoolResponse> getUnPoolWhitelistedPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnPoolWhitelistedPool",
      requestType = osmosis.superfluid.Tx.MsgUnPoolWhitelistedPool.class,
      responseType = osmosis.superfluid.Tx.MsgUnPoolWhitelistedPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.superfluid.Tx.MsgUnPoolWhitelistedPool,
      osmosis.superfluid.Tx.MsgUnPoolWhitelistedPoolResponse> getUnPoolWhitelistedPoolMethod() {
    io.grpc.MethodDescriptor<osmosis.superfluid.Tx.MsgUnPoolWhitelistedPool, osmosis.superfluid.Tx.MsgUnPoolWhitelistedPoolResponse> getUnPoolWhitelistedPoolMethod;
    if ((getUnPoolWhitelistedPoolMethod = MsgGrpc.getUnPoolWhitelistedPoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUnPoolWhitelistedPoolMethod = MsgGrpc.getUnPoolWhitelistedPoolMethod) == null) {
          MsgGrpc.getUnPoolWhitelistedPoolMethod = getUnPoolWhitelistedPoolMethod =
              io.grpc.MethodDescriptor.<osmosis.superfluid.Tx.MsgUnPoolWhitelistedPool, osmosis.superfluid.Tx.MsgUnPoolWhitelistedPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnPoolWhitelistedPool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.Tx.MsgUnPoolWhitelistedPool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.Tx.MsgUnPoolWhitelistedPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UnPoolWhitelistedPool"))
              .build();
        }
      }
    }
    return getUnPoolWhitelistedPoolMethod;
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
   * Msg defines the Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Execute superfluid delegation for a lockup
     * </pre>
     */
    public void superfluidDelegate(osmosis.superfluid.Tx.MsgSuperfluidDelegate request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.Tx.MsgSuperfluidDelegateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSuperfluidDelegateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Execute superfluid undelegation for a lockup
     * </pre>
     */
    public void superfluidUndelegate(osmosis.superfluid.Tx.MsgSuperfluidUndelegate request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.Tx.MsgSuperfluidUndelegateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSuperfluidUndelegateMethod(), responseObserver);
    }

    /**
     * <pre>
     * For a given lock that is being superfluidly undelegated,
     * also unbond the underlying lock.
     * </pre>
     */
    public void superfluidUnbondLock(osmosis.superfluid.Tx.MsgSuperfluidUnbondLock request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.Tx.MsgSuperfluidUnbondLockResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSuperfluidUnbondLockMethod(), responseObserver);
    }

    /**
     * <pre>
     * Execute lockup lock and superfluid delegation in a single msg
     * </pre>
     */
    public void lockAndSuperfluidDelegate(osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegate request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLockAndSuperfluidDelegateMethod(), responseObserver);
    }

    /**
     */
    public void unPoolWhitelistedPool(osmosis.superfluid.Tx.MsgUnPoolWhitelistedPool request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.Tx.MsgUnPoolWhitelistedPoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnPoolWhitelistedPoolMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSuperfluidDelegateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.superfluid.Tx.MsgSuperfluidDelegate,
                osmosis.superfluid.Tx.MsgSuperfluidDelegateResponse>(
                  this, METHODID_SUPERFLUID_DELEGATE)))
          .addMethod(
            getSuperfluidUndelegateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.superfluid.Tx.MsgSuperfluidUndelegate,
                osmosis.superfluid.Tx.MsgSuperfluidUndelegateResponse>(
                  this, METHODID_SUPERFLUID_UNDELEGATE)))
          .addMethod(
            getSuperfluidUnbondLockMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.superfluid.Tx.MsgSuperfluidUnbondLock,
                osmosis.superfluid.Tx.MsgSuperfluidUnbondLockResponse>(
                  this, METHODID_SUPERFLUID_UNBOND_LOCK)))
          .addMethod(
            getLockAndSuperfluidDelegateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegate,
                osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegateResponse>(
                  this, METHODID_LOCK_AND_SUPERFLUID_DELEGATE)))
          .addMethod(
            getUnPoolWhitelistedPoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.superfluid.Tx.MsgUnPoolWhitelistedPool,
                osmosis.superfluid.Tx.MsgUnPoolWhitelistedPoolResponse>(
                  this, METHODID_UN_POOL_WHITELISTED_POOL)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
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
     * Execute superfluid delegation for a lockup
     * </pre>
     */
    public void superfluidDelegate(osmosis.superfluid.Tx.MsgSuperfluidDelegate request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.Tx.MsgSuperfluidDelegateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSuperfluidDelegateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Execute superfluid undelegation for a lockup
     * </pre>
     */
    public void superfluidUndelegate(osmosis.superfluid.Tx.MsgSuperfluidUndelegate request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.Tx.MsgSuperfluidUndelegateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSuperfluidUndelegateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * For a given lock that is being superfluidly undelegated,
     * also unbond the underlying lock.
     * </pre>
     */
    public void superfluidUnbondLock(osmosis.superfluid.Tx.MsgSuperfluidUnbondLock request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.Tx.MsgSuperfluidUnbondLockResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSuperfluidUnbondLockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Execute lockup lock and superfluid delegation in a single msg
     * </pre>
     */
    public void lockAndSuperfluidDelegate(osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegate request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLockAndSuperfluidDelegateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void unPoolWhitelistedPool(osmosis.superfluid.Tx.MsgUnPoolWhitelistedPool request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.Tx.MsgUnPoolWhitelistedPoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnPoolWhitelistedPoolMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
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
     * Execute superfluid delegation for a lockup
     * </pre>
     */
    public osmosis.superfluid.Tx.MsgSuperfluidDelegateResponse superfluidDelegate(osmosis.superfluid.Tx.MsgSuperfluidDelegate request) {
      return blockingUnaryCall(
          getChannel(), getSuperfluidDelegateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Execute superfluid undelegation for a lockup
     * </pre>
     */
    public osmosis.superfluid.Tx.MsgSuperfluidUndelegateResponse superfluidUndelegate(osmosis.superfluid.Tx.MsgSuperfluidUndelegate request) {
      return blockingUnaryCall(
          getChannel(), getSuperfluidUndelegateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * For a given lock that is being superfluidly undelegated,
     * also unbond the underlying lock.
     * </pre>
     */
    public osmosis.superfluid.Tx.MsgSuperfluidUnbondLockResponse superfluidUnbondLock(osmosis.superfluid.Tx.MsgSuperfluidUnbondLock request) {
      return blockingUnaryCall(
          getChannel(), getSuperfluidUnbondLockMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Execute lockup lock and superfluid delegation in a single msg
     * </pre>
     */
    public osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegateResponse lockAndSuperfluidDelegate(osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegate request) {
      return blockingUnaryCall(
          getChannel(), getLockAndSuperfluidDelegateMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.superfluid.Tx.MsgUnPoolWhitelistedPoolResponse unPoolWhitelistedPool(osmosis.superfluid.Tx.MsgUnPoolWhitelistedPool request) {
      return blockingUnaryCall(
          getChannel(), getUnPoolWhitelistedPoolMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
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
     * Execute superfluid delegation for a lockup
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.superfluid.Tx.MsgSuperfluidDelegateResponse> superfluidDelegate(
        osmosis.superfluid.Tx.MsgSuperfluidDelegate request) {
      return futureUnaryCall(
          getChannel().newCall(getSuperfluidDelegateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Execute superfluid undelegation for a lockup
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.superfluid.Tx.MsgSuperfluidUndelegateResponse> superfluidUndelegate(
        osmosis.superfluid.Tx.MsgSuperfluidUndelegate request) {
      return futureUnaryCall(
          getChannel().newCall(getSuperfluidUndelegateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * For a given lock that is being superfluidly undelegated,
     * also unbond the underlying lock.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.superfluid.Tx.MsgSuperfluidUnbondLockResponse> superfluidUnbondLock(
        osmosis.superfluid.Tx.MsgSuperfluidUnbondLock request) {
      return futureUnaryCall(
          getChannel().newCall(getSuperfluidUnbondLockMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Execute lockup lock and superfluid delegation in a single msg
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegateResponse> lockAndSuperfluidDelegate(
        osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegate request) {
      return futureUnaryCall(
          getChannel().newCall(getLockAndSuperfluidDelegateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.superfluid.Tx.MsgUnPoolWhitelistedPoolResponse> unPoolWhitelistedPool(
        osmosis.superfluid.Tx.MsgUnPoolWhitelistedPool request) {
      return futureUnaryCall(
          getChannel().newCall(getUnPoolWhitelistedPoolMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SUPERFLUID_DELEGATE = 0;
  private static final int METHODID_SUPERFLUID_UNDELEGATE = 1;
  private static final int METHODID_SUPERFLUID_UNBOND_LOCK = 2;
  private static final int METHODID_LOCK_AND_SUPERFLUID_DELEGATE = 3;
  private static final int METHODID_UN_POOL_WHITELISTED_POOL = 4;

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
        case METHODID_SUPERFLUID_DELEGATE:
          serviceImpl.superfluidDelegate((osmosis.superfluid.Tx.MsgSuperfluidDelegate) request,
              (io.grpc.stub.StreamObserver<osmosis.superfluid.Tx.MsgSuperfluidDelegateResponse>) responseObserver);
          break;
        case METHODID_SUPERFLUID_UNDELEGATE:
          serviceImpl.superfluidUndelegate((osmosis.superfluid.Tx.MsgSuperfluidUndelegate) request,
              (io.grpc.stub.StreamObserver<osmosis.superfluid.Tx.MsgSuperfluidUndelegateResponse>) responseObserver);
          break;
        case METHODID_SUPERFLUID_UNBOND_LOCK:
          serviceImpl.superfluidUnbondLock((osmosis.superfluid.Tx.MsgSuperfluidUnbondLock) request,
              (io.grpc.stub.StreamObserver<osmosis.superfluid.Tx.MsgSuperfluidUnbondLockResponse>) responseObserver);
          break;
        case METHODID_LOCK_AND_SUPERFLUID_DELEGATE:
          serviceImpl.lockAndSuperfluidDelegate((osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegate) request,
              (io.grpc.stub.StreamObserver<osmosis.superfluid.Tx.MsgLockAndSuperfluidDelegateResponse>) responseObserver);
          break;
        case METHODID_UN_POOL_WHITELISTED_POOL:
          serviceImpl.unPoolWhitelistedPool((osmosis.superfluid.Tx.MsgUnPoolWhitelistedPool) request,
              (io.grpc.stub.StreamObserver<osmosis.superfluid.Tx.MsgUnPoolWhitelistedPoolResponse>) responseObserver);
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
      return osmosis.superfluid.Tx.getDescriptor();
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
              .addMethod(getSuperfluidDelegateMethod())
              .addMethod(getSuperfluidUndelegateMethod())
              .addMethod(getSuperfluidUnbondLockMethod())
              .addMethod(getLockAndSuperfluidDelegateMethod())
              .addMethod(getUnPoolWhitelistedPoolMethod())
              .build();
        }
      }
    }
    return result;
  }
}
