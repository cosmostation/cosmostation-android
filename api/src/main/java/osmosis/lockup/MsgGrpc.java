package osmosis.lockup;

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
    comments = "Source: osmosis/lockup/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "osmosis.lockup.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<osmosis.lockup.Tx.MsgLockTokens,
      osmosis.lockup.Tx.MsgLockTokensResponse> getLockTokensMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LockTokens",
      requestType = osmosis.lockup.Tx.MsgLockTokens.class,
      responseType = osmosis.lockup.Tx.MsgLockTokensResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.lockup.Tx.MsgLockTokens,
      osmosis.lockup.Tx.MsgLockTokensResponse> getLockTokensMethod() {
    io.grpc.MethodDescriptor<osmosis.lockup.Tx.MsgLockTokens, osmosis.lockup.Tx.MsgLockTokensResponse> getLockTokensMethod;
    if ((getLockTokensMethod = MsgGrpc.getLockTokensMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLockTokensMethod = MsgGrpc.getLockTokensMethod) == null) {
          MsgGrpc.getLockTokensMethod = getLockTokensMethod =
              io.grpc.MethodDescriptor.<osmosis.lockup.Tx.MsgLockTokens, osmosis.lockup.Tx.MsgLockTokensResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LockTokens"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.Tx.MsgLockTokens.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.Tx.MsgLockTokensResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("LockTokens"))
              .build();
        }
      }
    }
    return getLockTokensMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.lockup.Tx.MsgBeginUnlockingAll,
      osmosis.lockup.Tx.MsgBeginUnlockingAllResponse> getBeginUnlockingAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BeginUnlockingAll",
      requestType = osmosis.lockup.Tx.MsgBeginUnlockingAll.class,
      responseType = osmosis.lockup.Tx.MsgBeginUnlockingAllResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.lockup.Tx.MsgBeginUnlockingAll,
      osmosis.lockup.Tx.MsgBeginUnlockingAllResponse> getBeginUnlockingAllMethod() {
    io.grpc.MethodDescriptor<osmosis.lockup.Tx.MsgBeginUnlockingAll, osmosis.lockup.Tx.MsgBeginUnlockingAllResponse> getBeginUnlockingAllMethod;
    if ((getBeginUnlockingAllMethod = MsgGrpc.getBeginUnlockingAllMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBeginUnlockingAllMethod = MsgGrpc.getBeginUnlockingAllMethod) == null) {
          MsgGrpc.getBeginUnlockingAllMethod = getBeginUnlockingAllMethod =
              io.grpc.MethodDescriptor.<osmosis.lockup.Tx.MsgBeginUnlockingAll, osmosis.lockup.Tx.MsgBeginUnlockingAllResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BeginUnlockingAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.Tx.MsgBeginUnlockingAll.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.Tx.MsgBeginUnlockingAllResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BeginUnlockingAll"))
              .build();
        }
      }
    }
    return getBeginUnlockingAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.lockup.Tx.MsgBeginUnlocking,
      osmosis.lockup.Tx.MsgBeginUnlockingResponse> getBeginUnlockingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BeginUnlocking",
      requestType = osmosis.lockup.Tx.MsgBeginUnlocking.class,
      responseType = osmosis.lockup.Tx.MsgBeginUnlockingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.lockup.Tx.MsgBeginUnlocking,
      osmosis.lockup.Tx.MsgBeginUnlockingResponse> getBeginUnlockingMethod() {
    io.grpc.MethodDescriptor<osmosis.lockup.Tx.MsgBeginUnlocking, osmosis.lockup.Tx.MsgBeginUnlockingResponse> getBeginUnlockingMethod;
    if ((getBeginUnlockingMethod = MsgGrpc.getBeginUnlockingMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBeginUnlockingMethod = MsgGrpc.getBeginUnlockingMethod) == null) {
          MsgGrpc.getBeginUnlockingMethod = getBeginUnlockingMethod =
              io.grpc.MethodDescriptor.<osmosis.lockup.Tx.MsgBeginUnlocking, osmosis.lockup.Tx.MsgBeginUnlockingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BeginUnlocking"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.Tx.MsgBeginUnlocking.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.Tx.MsgBeginUnlockingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BeginUnlocking"))
              .build();
        }
      }
    }
    return getBeginUnlockingMethod;
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
     * LockTokens lock tokens
     * </pre>
     */
    public void lockTokens(osmosis.lockup.Tx.MsgLockTokens request,
        io.grpc.stub.StreamObserver<osmosis.lockup.Tx.MsgLockTokensResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLockTokensMethod(), responseObserver);
    }

    /**
     * <pre>
     * BeginUnlockingAll begin unlocking all tokens
     * </pre>
     */
    public void beginUnlockingAll(osmosis.lockup.Tx.MsgBeginUnlockingAll request,
        io.grpc.stub.StreamObserver<osmosis.lockup.Tx.MsgBeginUnlockingAllResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBeginUnlockingAllMethod(), responseObserver);
    }

    /**
     * <pre>
     * MsgBeginUnlocking begins unlocking tokens by lock ID
     * </pre>
     */
    public void beginUnlocking(osmosis.lockup.Tx.MsgBeginUnlocking request,
        io.grpc.stub.StreamObserver<osmosis.lockup.Tx.MsgBeginUnlockingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBeginUnlockingMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLockTokensMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.lockup.Tx.MsgLockTokens,
                osmosis.lockup.Tx.MsgLockTokensResponse>(
                  this, METHODID_LOCK_TOKENS)))
          .addMethod(
            getBeginUnlockingAllMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.lockup.Tx.MsgBeginUnlockingAll,
                osmosis.lockup.Tx.MsgBeginUnlockingAllResponse>(
                  this, METHODID_BEGIN_UNLOCKING_ALL)))
          .addMethod(
            getBeginUnlockingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.lockup.Tx.MsgBeginUnlocking,
                osmosis.lockup.Tx.MsgBeginUnlockingResponse>(
                  this, METHODID_BEGIN_UNLOCKING)))
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
     * LockTokens lock tokens
     * </pre>
     */
    public void lockTokens(osmosis.lockup.Tx.MsgLockTokens request,
        io.grpc.stub.StreamObserver<osmosis.lockup.Tx.MsgLockTokensResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLockTokensMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BeginUnlockingAll begin unlocking all tokens
     * </pre>
     */
    public void beginUnlockingAll(osmosis.lockup.Tx.MsgBeginUnlockingAll request,
        io.grpc.stub.StreamObserver<osmosis.lockup.Tx.MsgBeginUnlockingAllResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBeginUnlockingAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MsgBeginUnlocking begins unlocking tokens by lock ID
     * </pre>
     */
    public void beginUnlocking(osmosis.lockup.Tx.MsgBeginUnlocking request,
        io.grpc.stub.StreamObserver<osmosis.lockup.Tx.MsgBeginUnlockingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBeginUnlockingMethod(), getCallOptions()), request, responseObserver);
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
     * LockTokens lock tokens
     * </pre>
     */
    public osmosis.lockup.Tx.MsgLockTokensResponse lockTokens(osmosis.lockup.Tx.MsgLockTokens request) {
      return blockingUnaryCall(
          getChannel(), getLockTokensMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BeginUnlockingAll begin unlocking all tokens
     * </pre>
     */
    public osmosis.lockup.Tx.MsgBeginUnlockingAllResponse beginUnlockingAll(osmosis.lockup.Tx.MsgBeginUnlockingAll request) {
      return blockingUnaryCall(
          getChannel(), getBeginUnlockingAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MsgBeginUnlocking begins unlocking tokens by lock ID
     * </pre>
     */
    public osmosis.lockup.Tx.MsgBeginUnlockingResponse beginUnlocking(osmosis.lockup.Tx.MsgBeginUnlocking request) {
      return blockingUnaryCall(
          getChannel(), getBeginUnlockingMethod(), getCallOptions(), request);
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
     * LockTokens lock tokens
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.lockup.Tx.MsgLockTokensResponse> lockTokens(
        osmosis.lockup.Tx.MsgLockTokens request) {
      return futureUnaryCall(
          getChannel().newCall(getLockTokensMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BeginUnlockingAll begin unlocking all tokens
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.lockup.Tx.MsgBeginUnlockingAllResponse> beginUnlockingAll(
        osmosis.lockup.Tx.MsgBeginUnlockingAll request) {
      return futureUnaryCall(
          getChannel().newCall(getBeginUnlockingAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MsgBeginUnlocking begins unlocking tokens by lock ID
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.lockup.Tx.MsgBeginUnlockingResponse> beginUnlocking(
        osmosis.lockup.Tx.MsgBeginUnlocking request) {
      return futureUnaryCall(
          getChannel().newCall(getBeginUnlockingMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOCK_TOKENS = 0;
  private static final int METHODID_BEGIN_UNLOCKING_ALL = 1;
  private static final int METHODID_BEGIN_UNLOCKING = 2;

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
        case METHODID_LOCK_TOKENS:
          serviceImpl.lockTokens((osmosis.lockup.Tx.MsgLockTokens) request,
              (io.grpc.stub.StreamObserver<osmosis.lockup.Tx.MsgLockTokensResponse>) responseObserver);
          break;
        case METHODID_BEGIN_UNLOCKING_ALL:
          serviceImpl.beginUnlockingAll((osmosis.lockup.Tx.MsgBeginUnlockingAll) request,
              (io.grpc.stub.StreamObserver<osmosis.lockup.Tx.MsgBeginUnlockingAllResponse>) responseObserver);
          break;
        case METHODID_BEGIN_UNLOCKING:
          serviceImpl.beginUnlocking((osmosis.lockup.Tx.MsgBeginUnlocking) request,
              (io.grpc.stub.StreamObserver<osmosis.lockup.Tx.MsgBeginUnlockingResponse>) responseObserver);
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
      return osmosis.lockup.Tx.getDescriptor();
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
              .addMethod(getLockTokensMethod())
              .addMethod(getBeginUnlockingAllMethod())
              .addMethod(getBeginUnlockingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
