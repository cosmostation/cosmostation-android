package axelar.snapshot.v1beta1;

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
 * Msg defines the snapshot Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: axelar/snapshot/v1beta1/service.proto")
public final class MsgServiceGrpc {

  private MsgServiceGrpc() {}

  public static final String SERVICE_NAME = "axelar.snapshot.v1beta1.MsgService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<axelar.snapshot.v1beta1.Tx.RegisterProxyRequest,
      axelar.snapshot.v1beta1.Tx.RegisterProxyResponse> getRegisterProxyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterProxy",
      requestType = axelar.snapshot.v1beta1.Tx.RegisterProxyRequest.class,
      responseType = axelar.snapshot.v1beta1.Tx.RegisterProxyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.snapshot.v1beta1.Tx.RegisterProxyRequest,
      axelar.snapshot.v1beta1.Tx.RegisterProxyResponse> getRegisterProxyMethod() {
    io.grpc.MethodDescriptor<axelar.snapshot.v1beta1.Tx.RegisterProxyRequest, axelar.snapshot.v1beta1.Tx.RegisterProxyResponse> getRegisterProxyMethod;
    if ((getRegisterProxyMethod = MsgServiceGrpc.getRegisterProxyMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getRegisterProxyMethod = MsgServiceGrpc.getRegisterProxyMethod) == null) {
          MsgServiceGrpc.getRegisterProxyMethod = getRegisterProxyMethod =
              io.grpc.MethodDescriptor.<axelar.snapshot.v1beta1.Tx.RegisterProxyRequest, axelar.snapshot.v1beta1.Tx.RegisterProxyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterProxy"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.snapshot.v1beta1.Tx.RegisterProxyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.snapshot.v1beta1.Tx.RegisterProxyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("RegisterProxy"))
              .build();
        }
      }
    }
    return getRegisterProxyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.snapshot.v1beta1.Tx.DeactivateProxyRequest,
      axelar.snapshot.v1beta1.Tx.DeactivateProxyResponse> getDeactivateProxyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeactivateProxy",
      requestType = axelar.snapshot.v1beta1.Tx.DeactivateProxyRequest.class,
      responseType = axelar.snapshot.v1beta1.Tx.DeactivateProxyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.snapshot.v1beta1.Tx.DeactivateProxyRequest,
      axelar.snapshot.v1beta1.Tx.DeactivateProxyResponse> getDeactivateProxyMethod() {
    io.grpc.MethodDescriptor<axelar.snapshot.v1beta1.Tx.DeactivateProxyRequest, axelar.snapshot.v1beta1.Tx.DeactivateProxyResponse> getDeactivateProxyMethod;
    if ((getDeactivateProxyMethod = MsgServiceGrpc.getDeactivateProxyMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getDeactivateProxyMethod = MsgServiceGrpc.getDeactivateProxyMethod) == null) {
          MsgServiceGrpc.getDeactivateProxyMethod = getDeactivateProxyMethod =
              io.grpc.MethodDescriptor.<axelar.snapshot.v1beta1.Tx.DeactivateProxyRequest, axelar.snapshot.v1beta1.Tx.DeactivateProxyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeactivateProxy"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.snapshot.v1beta1.Tx.DeactivateProxyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.snapshot.v1beta1.Tx.DeactivateProxyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("DeactivateProxy"))
              .build();
        }
      }
    }
    return getDeactivateProxyMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MsgServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceStub>() {
        @java.lang.Override
        public MsgServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceStub(channel, callOptions);
        }
      };
    return MsgServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MsgServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceBlockingStub>() {
        @java.lang.Override
        public MsgServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceBlockingStub(channel, callOptions);
        }
      };
    return MsgServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MsgServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceFutureStub>() {
        @java.lang.Override
        public MsgServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceFutureStub(channel, callOptions);
        }
      };
    return MsgServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Msg defines the snapshot Msg service.
   * </pre>
   */
  public static abstract class MsgServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * RegisterProxy defines a method for registering a proxy account that can act
     * in a validator account's stead.
     * </pre>
     */
    public void registerProxy(axelar.snapshot.v1beta1.Tx.RegisterProxyRequest request,
        io.grpc.stub.StreamObserver<axelar.snapshot.v1beta1.Tx.RegisterProxyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterProxyMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeactivateProxy defines a method for deregistering a proxy account.
     * </pre>
     */
    public void deactivateProxy(axelar.snapshot.v1beta1.Tx.DeactivateProxyRequest request,
        io.grpc.stub.StreamObserver<axelar.snapshot.v1beta1.Tx.DeactivateProxyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeactivateProxyMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterProxyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.snapshot.v1beta1.Tx.RegisterProxyRequest,
                axelar.snapshot.v1beta1.Tx.RegisterProxyResponse>(
                  this, METHODID_REGISTER_PROXY)))
          .addMethod(
            getDeactivateProxyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.snapshot.v1beta1.Tx.DeactivateProxyRequest,
                axelar.snapshot.v1beta1.Tx.DeactivateProxyResponse>(
                  this, METHODID_DEACTIVATE_PROXY)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the snapshot Msg service.
   * </pre>
   */
  public static final class MsgServiceStub extends io.grpc.stub.AbstractAsyncStub<MsgServiceStub> {
    private MsgServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * RegisterProxy defines a method for registering a proxy account that can act
     * in a validator account's stead.
     * </pre>
     */
    public void registerProxy(axelar.snapshot.v1beta1.Tx.RegisterProxyRequest request,
        io.grpc.stub.StreamObserver<axelar.snapshot.v1beta1.Tx.RegisterProxyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterProxyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeactivateProxy defines a method for deregistering a proxy account.
     * </pre>
     */
    public void deactivateProxy(axelar.snapshot.v1beta1.Tx.DeactivateProxyRequest request,
        io.grpc.stub.StreamObserver<axelar.snapshot.v1beta1.Tx.DeactivateProxyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeactivateProxyMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the snapshot Msg service.
   * </pre>
   */
  public static final class MsgServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<MsgServiceBlockingStub> {
    private MsgServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * RegisterProxy defines a method for registering a proxy account that can act
     * in a validator account's stead.
     * </pre>
     */
    public axelar.snapshot.v1beta1.Tx.RegisterProxyResponse registerProxy(axelar.snapshot.v1beta1.Tx.RegisterProxyRequest request) {
      return blockingUnaryCall(
          getChannel(), getRegisterProxyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeactivateProxy defines a method for deregistering a proxy account.
     * </pre>
     */
    public axelar.snapshot.v1beta1.Tx.DeactivateProxyResponse deactivateProxy(axelar.snapshot.v1beta1.Tx.DeactivateProxyRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeactivateProxyMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the snapshot Msg service.
   * </pre>
   */
  public static final class MsgServiceFutureStub extends io.grpc.stub.AbstractFutureStub<MsgServiceFutureStub> {
    private MsgServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * RegisterProxy defines a method for registering a proxy account that can act
     * in a validator account's stead.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.snapshot.v1beta1.Tx.RegisterProxyResponse> registerProxy(
        axelar.snapshot.v1beta1.Tx.RegisterProxyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterProxyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeactivateProxy defines a method for deregistering a proxy account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.snapshot.v1beta1.Tx.DeactivateProxyResponse> deactivateProxy(
        axelar.snapshot.v1beta1.Tx.DeactivateProxyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeactivateProxyMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_PROXY = 0;
  private static final int METHODID_DEACTIVATE_PROXY = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MsgServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MsgServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER_PROXY:
          serviceImpl.registerProxy((axelar.snapshot.v1beta1.Tx.RegisterProxyRequest) request,
              (io.grpc.stub.StreamObserver<axelar.snapshot.v1beta1.Tx.RegisterProxyResponse>) responseObserver);
          break;
        case METHODID_DEACTIVATE_PROXY:
          serviceImpl.deactivateProxy((axelar.snapshot.v1beta1.Tx.DeactivateProxyRequest) request,
              (io.grpc.stub.StreamObserver<axelar.snapshot.v1beta1.Tx.DeactivateProxyResponse>) responseObserver);
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

  private static abstract class MsgServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return axelar.snapshot.v1beta1.Service.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MsgService");
    }
  }

  private static final class MsgServiceFileDescriptorSupplier
      extends MsgServiceBaseDescriptorSupplier {
    MsgServiceFileDescriptorSupplier() {}
  }

  private static final class MsgServiceMethodDescriptorSupplier
      extends MsgServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MsgServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (MsgServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MsgServiceFileDescriptorSupplier())
              .addMethod(getRegisterProxyMethod())
              .addMethod(getDeactivateProxyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
