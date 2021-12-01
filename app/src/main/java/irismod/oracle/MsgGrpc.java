package irismod.oracle;

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
 * Msg defines the oracle Msg service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: iris_mod/oracle/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "irismod.oracle.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<irismod.oracle.Tx.MsgCreateFeed,
      irismod.oracle.Tx.MsgCreateFeedResponse> getCreateFeedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateFeed",
      requestType = irismod.oracle.Tx.MsgCreateFeed.class,
      responseType = irismod.oracle.Tx.MsgCreateFeedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.oracle.Tx.MsgCreateFeed,
      irismod.oracle.Tx.MsgCreateFeedResponse> getCreateFeedMethod() {
    io.grpc.MethodDescriptor<irismod.oracle.Tx.MsgCreateFeed, irismod.oracle.Tx.MsgCreateFeedResponse> getCreateFeedMethod;
    if ((getCreateFeedMethod = MsgGrpc.getCreateFeedMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateFeedMethod = MsgGrpc.getCreateFeedMethod) == null) {
          MsgGrpc.getCreateFeedMethod = getCreateFeedMethod =
              io.grpc.MethodDescriptor.<irismod.oracle.Tx.MsgCreateFeed, irismod.oracle.Tx.MsgCreateFeedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateFeed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.oracle.Tx.MsgCreateFeed.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.oracle.Tx.MsgCreateFeedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateFeed"))
              .build();
        }
      }
    }
    return getCreateFeedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.oracle.Tx.MsgEditFeed,
      irismod.oracle.Tx.MsgEditFeedResponse> getEditFeedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditFeed",
      requestType = irismod.oracle.Tx.MsgEditFeed.class,
      responseType = irismod.oracle.Tx.MsgEditFeedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.oracle.Tx.MsgEditFeed,
      irismod.oracle.Tx.MsgEditFeedResponse> getEditFeedMethod() {
    io.grpc.MethodDescriptor<irismod.oracle.Tx.MsgEditFeed, irismod.oracle.Tx.MsgEditFeedResponse> getEditFeedMethod;
    if ((getEditFeedMethod = MsgGrpc.getEditFeedMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEditFeedMethod = MsgGrpc.getEditFeedMethod) == null) {
          MsgGrpc.getEditFeedMethod = getEditFeedMethod =
              io.grpc.MethodDescriptor.<irismod.oracle.Tx.MsgEditFeed, irismod.oracle.Tx.MsgEditFeedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditFeed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.oracle.Tx.MsgEditFeed.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.oracle.Tx.MsgEditFeedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EditFeed"))
              .build();
        }
      }
    }
    return getEditFeedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.oracle.Tx.MsgStartFeed,
      irismod.oracle.Tx.MsgStartFeedResponse> getStartFeedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StartFeed",
      requestType = irismod.oracle.Tx.MsgStartFeed.class,
      responseType = irismod.oracle.Tx.MsgStartFeedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.oracle.Tx.MsgStartFeed,
      irismod.oracle.Tx.MsgStartFeedResponse> getStartFeedMethod() {
    io.grpc.MethodDescriptor<irismod.oracle.Tx.MsgStartFeed, irismod.oracle.Tx.MsgStartFeedResponse> getStartFeedMethod;
    if ((getStartFeedMethod = MsgGrpc.getStartFeedMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getStartFeedMethod = MsgGrpc.getStartFeedMethod) == null) {
          MsgGrpc.getStartFeedMethod = getStartFeedMethod =
              io.grpc.MethodDescriptor.<irismod.oracle.Tx.MsgStartFeed, irismod.oracle.Tx.MsgStartFeedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StartFeed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.oracle.Tx.MsgStartFeed.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.oracle.Tx.MsgStartFeedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("StartFeed"))
              .build();
        }
      }
    }
    return getStartFeedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.oracle.Tx.MsgPauseFeed,
      irismod.oracle.Tx.MsgPauseFeedResponse> getPauseFeedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PauseFeed",
      requestType = irismod.oracle.Tx.MsgPauseFeed.class,
      responseType = irismod.oracle.Tx.MsgPauseFeedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.oracle.Tx.MsgPauseFeed,
      irismod.oracle.Tx.MsgPauseFeedResponse> getPauseFeedMethod() {
    io.grpc.MethodDescriptor<irismod.oracle.Tx.MsgPauseFeed, irismod.oracle.Tx.MsgPauseFeedResponse> getPauseFeedMethod;
    if ((getPauseFeedMethod = MsgGrpc.getPauseFeedMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getPauseFeedMethod = MsgGrpc.getPauseFeedMethod) == null) {
          MsgGrpc.getPauseFeedMethod = getPauseFeedMethod =
              io.grpc.MethodDescriptor.<irismod.oracle.Tx.MsgPauseFeed, irismod.oracle.Tx.MsgPauseFeedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PauseFeed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.oracle.Tx.MsgPauseFeed.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.oracle.Tx.MsgPauseFeedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("PauseFeed"))
              .build();
        }
      }
    }
    return getPauseFeedMethod;
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
   * Msg defines the oracle Msg service
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreateFeed defines a method for creating a new feed
     * </pre>
     */
    public void createFeed(irismod.oracle.Tx.MsgCreateFeed request,
        io.grpc.stub.StreamObserver<irismod.oracle.Tx.MsgCreateFeedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateFeedMethod(), responseObserver);
    }

    /**
     * <pre>
     * EditFeed defines a method for editing a feed
     * </pre>
     */
    public void editFeed(irismod.oracle.Tx.MsgEditFeed request,
        io.grpc.stub.StreamObserver<irismod.oracle.Tx.MsgEditFeedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEditFeedMethod(), responseObserver);
    }

    /**
     * <pre>
     * StartFeed defines a method for starting a feed
     * </pre>
     */
    public void startFeed(irismod.oracle.Tx.MsgStartFeed request,
        io.grpc.stub.StreamObserver<irismod.oracle.Tx.MsgStartFeedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStartFeedMethod(), responseObserver);
    }

    /**
     * <pre>
     * PauseFeed defines a method for pausing a feed
     * </pre>
     */
    public void pauseFeed(irismod.oracle.Tx.MsgPauseFeed request,
        io.grpc.stub.StreamObserver<irismod.oracle.Tx.MsgPauseFeedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPauseFeedMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateFeedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.oracle.Tx.MsgCreateFeed,
                irismod.oracle.Tx.MsgCreateFeedResponse>(
                  this, METHODID_CREATE_FEED)))
          .addMethod(
            getEditFeedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.oracle.Tx.MsgEditFeed,
                irismod.oracle.Tx.MsgEditFeedResponse>(
                  this, METHODID_EDIT_FEED)))
          .addMethod(
            getStartFeedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.oracle.Tx.MsgStartFeed,
                irismod.oracle.Tx.MsgStartFeedResponse>(
                  this, METHODID_START_FEED)))
          .addMethod(
            getPauseFeedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.oracle.Tx.MsgPauseFeed,
                irismod.oracle.Tx.MsgPauseFeedResponse>(
                  this, METHODID_PAUSE_FEED)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the oracle Msg service
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
     * CreateFeed defines a method for creating a new feed
     * </pre>
     */
    public void createFeed(irismod.oracle.Tx.MsgCreateFeed request,
        io.grpc.stub.StreamObserver<irismod.oracle.Tx.MsgCreateFeedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateFeedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EditFeed defines a method for editing a feed
     * </pre>
     */
    public void editFeed(irismod.oracle.Tx.MsgEditFeed request,
        io.grpc.stub.StreamObserver<irismod.oracle.Tx.MsgEditFeedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEditFeedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * StartFeed defines a method for starting a feed
     * </pre>
     */
    public void startFeed(irismod.oracle.Tx.MsgStartFeed request,
        io.grpc.stub.StreamObserver<irismod.oracle.Tx.MsgStartFeedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStartFeedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PauseFeed defines a method for pausing a feed
     * </pre>
     */
    public void pauseFeed(irismod.oracle.Tx.MsgPauseFeed request,
        io.grpc.stub.StreamObserver<irismod.oracle.Tx.MsgPauseFeedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPauseFeedMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the oracle Msg service
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
     * CreateFeed defines a method for creating a new feed
     * </pre>
     */
    public irismod.oracle.Tx.MsgCreateFeedResponse createFeed(irismod.oracle.Tx.MsgCreateFeed request) {
      return blockingUnaryCall(
          getChannel(), getCreateFeedMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EditFeed defines a method for editing a feed
     * </pre>
     */
    public irismod.oracle.Tx.MsgEditFeedResponse editFeed(irismod.oracle.Tx.MsgEditFeed request) {
      return blockingUnaryCall(
          getChannel(), getEditFeedMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * StartFeed defines a method for starting a feed
     * </pre>
     */
    public irismod.oracle.Tx.MsgStartFeedResponse startFeed(irismod.oracle.Tx.MsgStartFeed request) {
      return blockingUnaryCall(
          getChannel(), getStartFeedMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PauseFeed defines a method for pausing a feed
     * </pre>
     */
    public irismod.oracle.Tx.MsgPauseFeedResponse pauseFeed(irismod.oracle.Tx.MsgPauseFeed request) {
      return blockingUnaryCall(
          getChannel(), getPauseFeedMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the oracle Msg service
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
     * CreateFeed defines a method for creating a new feed
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.oracle.Tx.MsgCreateFeedResponse> createFeed(
        irismod.oracle.Tx.MsgCreateFeed request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateFeedMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EditFeed defines a method for editing a feed
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.oracle.Tx.MsgEditFeedResponse> editFeed(
        irismod.oracle.Tx.MsgEditFeed request) {
      return futureUnaryCall(
          getChannel().newCall(getEditFeedMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * StartFeed defines a method for starting a feed
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.oracle.Tx.MsgStartFeedResponse> startFeed(
        irismod.oracle.Tx.MsgStartFeed request) {
      return futureUnaryCall(
          getChannel().newCall(getStartFeedMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PauseFeed defines a method for pausing a feed
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.oracle.Tx.MsgPauseFeedResponse> pauseFeed(
        irismod.oracle.Tx.MsgPauseFeed request) {
      return futureUnaryCall(
          getChannel().newCall(getPauseFeedMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_FEED = 0;
  private static final int METHODID_EDIT_FEED = 1;
  private static final int METHODID_START_FEED = 2;
  private static final int METHODID_PAUSE_FEED = 3;

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
        case METHODID_CREATE_FEED:
          serviceImpl.createFeed((irismod.oracle.Tx.MsgCreateFeed) request,
              (io.grpc.stub.StreamObserver<irismod.oracle.Tx.MsgCreateFeedResponse>) responseObserver);
          break;
        case METHODID_EDIT_FEED:
          serviceImpl.editFeed((irismod.oracle.Tx.MsgEditFeed) request,
              (io.grpc.stub.StreamObserver<irismod.oracle.Tx.MsgEditFeedResponse>) responseObserver);
          break;
        case METHODID_START_FEED:
          serviceImpl.startFeed((irismod.oracle.Tx.MsgStartFeed) request,
              (io.grpc.stub.StreamObserver<irismod.oracle.Tx.MsgStartFeedResponse>) responseObserver);
          break;
        case METHODID_PAUSE_FEED:
          serviceImpl.pauseFeed((irismod.oracle.Tx.MsgPauseFeed) request,
              (io.grpc.stub.StreamObserver<irismod.oracle.Tx.MsgPauseFeedResponse>) responseObserver);
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
      return irismod.oracle.Tx.getDescriptor();
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
              .addMethod(getCreateFeedMethod())
              .addMethod(getEditFeedMethod())
              .addMethod(getStartFeedMethod())
              .addMethod(getPauseFeedMethod())
              .build();
        }
      }
    }
    return result;
  }
}
