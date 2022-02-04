package axelar.nexus.v1beta1;

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
 * Msg defines the nexus Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: axelar/nexus/v1beta1/service.proto")
public final class MsgServiceGrpc {

  private MsgServiceGrpc() {}

  public static final String SERVICE_NAME = "axelar.nexus.v1beta1.MsgService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Tx.RegisterChainMaintainerRequest,
      axelar.nexus.v1beta1.Tx.RegisterChainMaintainerResponse> getRegisterChainMaintainerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterChainMaintainer",
      requestType = axelar.nexus.v1beta1.Tx.RegisterChainMaintainerRequest.class,
      responseType = axelar.nexus.v1beta1.Tx.RegisterChainMaintainerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Tx.RegisterChainMaintainerRequest,
      axelar.nexus.v1beta1.Tx.RegisterChainMaintainerResponse> getRegisterChainMaintainerMethod() {
    io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Tx.RegisterChainMaintainerRequest, axelar.nexus.v1beta1.Tx.RegisterChainMaintainerResponse> getRegisterChainMaintainerMethod;
    if ((getRegisterChainMaintainerMethod = MsgServiceGrpc.getRegisterChainMaintainerMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getRegisterChainMaintainerMethod = MsgServiceGrpc.getRegisterChainMaintainerMethod) == null) {
          MsgServiceGrpc.getRegisterChainMaintainerMethod = getRegisterChainMaintainerMethod =
              io.grpc.MethodDescriptor.<axelar.nexus.v1beta1.Tx.RegisterChainMaintainerRequest, axelar.nexus.v1beta1.Tx.RegisterChainMaintainerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterChainMaintainer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.nexus.v1beta1.Tx.RegisterChainMaintainerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.nexus.v1beta1.Tx.RegisterChainMaintainerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("RegisterChainMaintainer"))
              .build();
        }
      }
    }
    return getRegisterChainMaintainerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerRequest,
      axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerResponse> getDeregisterChainMaintainerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeregisterChainMaintainer",
      requestType = axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerRequest.class,
      responseType = axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerRequest,
      axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerResponse> getDeregisterChainMaintainerMethod() {
    io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerRequest, axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerResponse> getDeregisterChainMaintainerMethod;
    if ((getDeregisterChainMaintainerMethod = MsgServiceGrpc.getDeregisterChainMaintainerMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getDeregisterChainMaintainerMethod = MsgServiceGrpc.getDeregisterChainMaintainerMethod) == null) {
          MsgServiceGrpc.getDeregisterChainMaintainerMethod = getDeregisterChainMaintainerMethod =
              io.grpc.MethodDescriptor.<axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerRequest, axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeregisterChainMaintainer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("DeregisterChainMaintainer"))
              .build();
        }
      }
    }
    return getDeregisterChainMaintainerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Tx.ActivateChainRequest,
      axelar.nexus.v1beta1.Tx.ActivateChainResponse> getActivateChainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ActivateChain",
      requestType = axelar.nexus.v1beta1.Tx.ActivateChainRequest.class,
      responseType = axelar.nexus.v1beta1.Tx.ActivateChainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Tx.ActivateChainRequest,
      axelar.nexus.v1beta1.Tx.ActivateChainResponse> getActivateChainMethod() {
    io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Tx.ActivateChainRequest, axelar.nexus.v1beta1.Tx.ActivateChainResponse> getActivateChainMethod;
    if ((getActivateChainMethod = MsgServiceGrpc.getActivateChainMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getActivateChainMethod = MsgServiceGrpc.getActivateChainMethod) == null) {
          MsgServiceGrpc.getActivateChainMethod = getActivateChainMethod =
              io.grpc.MethodDescriptor.<axelar.nexus.v1beta1.Tx.ActivateChainRequest, axelar.nexus.v1beta1.Tx.ActivateChainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ActivateChain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.nexus.v1beta1.Tx.ActivateChainRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.nexus.v1beta1.Tx.ActivateChainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("ActivateChain"))
              .build();
        }
      }
    }
    return getActivateChainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Tx.DeactivateChainRequest,
      axelar.nexus.v1beta1.Tx.DeactivateChainResponse> getDeactivateChainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeactivateChain",
      requestType = axelar.nexus.v1beta1.Tx.DeactivateChainRequest.class,
      responseType = axelar.nexus.v1beta1.Tx.DeactivateChainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Tx.DeactivateChainRequest,
      axelar.nexus.v1beta1.Tx.DeactivateChainResponse> getDeactivateChainMethod() {
    io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Tx.DeactivateChainRequest, axelar.nexus.v1beta1.Tx.DeactivateChainResponse> getDeactivateChainMethod;
    if ((getDeactivateChainMethod = MsgServiceGrpc.getDeactivateChainMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getDeactivateChainMethod = MsgServiceGrpc.getDeactivateChainMethod) == null) {
          MsgServiceGrpc.getDeactivateChainMethod = getDeactivateChainMethod =
              io.grpc.MethodDescriptor.<axelar.nexus.v1beta1.Tx.DeactivateChainRequest, axelar.nexus.v1beta1.Tx.DeactivateChainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeactivateChain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.nexus.v1beta1.Tx.DeactivateChainRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.nexus.v1beta1.Tx.DeactivateChainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("DeactivateChain"))
              .build();
        }
      }
    }
    return getDeactivateChainMethod;
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
   * Msg defines the nexus Msg service.
   * </pre>
   */
  public static abstract class MsgServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void registerChainMaintainer(axelar.nexus.v1beta1.Tx.RegisterChainMaintainerRequest request,
        io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Tx.RegisterChainMaintainerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterChainMaintainerMethod(), responseObserver);
    }

    /**
     */
    public void deregisterChainMaintainer(axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerRequest request,
        io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeregisterChainMaintainerMethod(), responseObserver);
    }

    /**
     */
    public void activateChain(axelar.nexus.v1beta1.Tx.ActivateChainRequest request,
        io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Tx.ActivateChainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getActivateChainMethod(), responseObserver);
    }

    /**
     */
    public void deactivateChain(axelar.nexus.v1beta1.Tx.DeactivateChainRequest request,
        io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Tx.DeactivateChainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeactivateChainMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterChainMaintainerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.nexus.v1beta1.Tx.RegisterChainMaintainerRequest,
                axelar.nexus.v1beta1.Tx.RegisterChainMaintainerResponse>(
                  this, METHODID_REGISTER_CHAIN_MAINTAINER)))
          .addMethod(
            getDeregisterChainMaintainerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerRequest,
                axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerResponse>(
                  this, METHODID_DEREGISTER_CHAIN_MAINTAINER)))
          .addMethod(
            getActivateChainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.nexus.v1beta1.Tx.ActivateChainRequest,
                axelar.nexus.v1beta1.Tx.ActivateChainResponse>(
                  this, METHODID_ACTIVATE_CHAIN)))
          .addMethod(
            getDeactivateChainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.nexus.v1beta1.Tx.DeactivateChainRequest,
                axelar.nexus.v1beta1.Tx.DeactivateChainResponse>(
                  this, METHODID_DEACTIVATE_CHAIN)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the nexus Msg service.
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
     */
    public void registerChainMaintainer(axelar.nexus.v1beta1.Tx.RegisterChainMaintainerRequest request,
        io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Tx.RegisterChainMaintainerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterChainMaintainerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deregisterChainMaintainer(axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerRequest request,
        io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeregisterChainMaintainerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void activateChain(axelar.nexus.v1beta1.Tx.ActivateChainRequest request,
        io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Tx.ActivateChainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getActivateChainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deactivateChain(axelar.nexus.v1beta1.Tx.DeactivateChainRequest request,
        io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Tx.DeactivateChainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeactivateChainMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the nexus Msg service.
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
     */
    public axelar.nexus.v1beta1.Tx.RegisterChainMaintainerResponse registerChainMaintainer(axelar.nexus.v1beta1.Tx.RegisterChainMaintainerRequest request) {
      return blockingUnaryCall(
          getChannel(), getRegisterChainMaintainerMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerResponse deregisterChainMaintainer(axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeregisterChainMaintainerMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.nexus.v1beta1.Tx.ActivateChainResponse activateChain(axelar.nexus.v1beta1.Tx.ActivateChainRequest request) {
      return blockingUnaryCall(
          getChannel(), getActivateChainMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.nexus.v1beta1.Tx.DeactivateChainResponse deactivateChain(axelar.nexus.v1beta1.Tx.DeactivateChainRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeactivateChainMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the nexus Msg service.
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
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.nexus.v1beta1.Tx.RegisterChainMaintainerResponse> registerChainMaintainer(
        axelar.nexus.v1beta1.Tx.RegisterChainMaintainerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterChainMaintainerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerResponse> deregisterChainMaintainer(
        axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeregisterChainMaintainerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.nexus.v1beta1.Tx.ActivateChainResponse> activateChain(
        axelar.nexus.v1beta1.Tx.ActivateChainRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getActivateChainMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.nexus.v1beta1.Tx.DeactivateChainResponse> deactivateChain(
        axelar.nexus.v1beta1.Tx.DeactivateChainRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeactivateChainMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_CHAIN_MAINTAINER = 0;
  private static final int METHODID_DEREGISTER_CHAIN_MAINTAINER = 1;
  private static final int METHODID_ACTIVATE_CHAIN = 2;
  private static final int METHODID_DEACTIVATE_CHAIN = 3;

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
        case METHODID_REGISTER_CHAIN_MAINTAINER:
          serviceImpl.registerChainMaintainer((axelar.nexus.v1beta1.Tx.RegisterChainMaintainerRequest) request,
              (io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Tx.RegisterChainMaintainerResponse>) responseObserver);
          break;
        case METHODID_DEREGISTER_CHAIN_MAINTAINER:
          serviceImpl.deregisterChainMaintainer((axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerRequest) request,
              (io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Tx.DeregisterChainMaintainerResponse>) responseObserver);
          break;
        case METHODID_ACTIVATE_CHAIN:
          serviceImpl.activateChain((axelar.nexus.v1beta1.Tx.ActivateChainRequest) request,
              (io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Tx.ActivateChainResponse>) responseObserver);
          break;
        case METHODID_DEACTIVATE_CHAIN:
          serviceImpl.deactivateChain((axelar.nexus.v1beta1.Tx.DeactivateChainRequest) request,
              (io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Tx.DeactivateChainResponse>) responseObserver);
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
      return axelar.nexus.v1beta1.Service.getDescriptor();
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
              .addMethod(getRegisterChainMaintainerMethod())
              .addMethod(getDeregisterChainMaintainerMethod())
              .addMethod(getActivateChainMethod())
              .addMethod(getDeactivateChainMethod())
              .build();
        }
      }
    }
    return result;
  }
}
