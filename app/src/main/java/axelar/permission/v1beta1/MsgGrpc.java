package axelar.permission.v1beta1;

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
 * Msg defines the gov Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: axelar/permission/v1beta1/service.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "axelar.permission.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<axelar.permission.v1beta1.Tx.RegisterControllerRequest,
      axelar.permission.v1beta1.Tx.RegisterControllerResponse> getRegisterControllerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterController",
      requestType = axelar.permission.v1beta1.Tx.RegisterControllerRequest.class,
      responseType = axelar.permission.v1beta1.Tx.RegisterControllerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.permission.v1beta1.Tx.RegisterControllerRequest,
      axelar.permission.v1beta1.Tx.RegisterControllerResponse> getRegisterControllerMethod() {
    io.grpc.MethodDescriptor<axelar.permission.v1beta1.Tx.RegisterControllerRequest, axelar.permission.v1beta1.Tx.RegisterControllerResponse> getRegisterControllerMethod;
    if ((getRegisterControllerMethod = MsgGrpc.getRegisterControllerMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRegisterControllerMethod = MsgGrpc.getRegisterControllerMethod) == null) {
          MsgGrpc.getRegisterControllerMethod = getRegisterControllerMethod =
              io.grpc.MethodDescriptor.<axelar.permission.v1beta1.Tx.RegisterControllerRequest, axelar.permission.v1beta1.Tx.RegisterControllerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterController"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.permission.v1beta1.Tx.RegisterControllerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.permission.v1beta1.Tx.RegisterControllerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RegisterController"))
              .build();
        }
      }
    }
    return getRegisterControllerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.permission.v1beta1.Tx.DeregisterControllerRequest,
      axelar.permission.v1beta1.Tx.DeregisterControllerResponse> getDeregisterControllerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeregisterController",
      requestType = axelar.permission.v1beta1.Tx.DeregisterControllerRequest.class,
      responseType = axelar.permission.v1beta1.Tx.DeregisterControllerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.permission.v1beta1.Tx.DeregisterControllerRequest,
      axelar.permission.v1beta1.Tx.DeregisterControllerResponse> getDeregisterControllerMethod() {
    io.grpc.MethodDescriptor<axelar.permission.v1beta1.Tx.DeregisterControllerRequest, axelar.permission.v1beta1.Tx.DeregisterControllerResponse> getDeregisterControllerMethod;
    if ((getDeregisterControllerMethod = MsgGrpc.getDeregisterControllerMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeregisterControllerMethod = MsgGrpc.getDeregisterControllerMethod) == null) {
          MsgGrpc.getDeregisterControllerMethod = getDeregisterControllerMethod =
              io.grpc.MethodDescriptor.<axelar.permission.v1beta1.Tx.DeregisterControllerRequest, axelar.permission.v1beta1.Tx.DeregisterControllerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeregisterController"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.permission.v1beta1.Tx.DeregisterControllerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.permission.v1beta1.Tx.DeregisterControllerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeregisterController"))
              .build();
        }
      }
    }
    return getDeregisterControllerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.permission.v1beta1.Tx.UpdateGovernanceKeyRequest,
      axelar.permission.v1beta1.Tx.UpdateGovernanceKeyResponse> getUpdateGovernanceKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateGovernanceKey",
      requestType = axelar.permission.v1beta1.Tx.UpdateGovernanceKeyRequest.class,
      responseType = axelar.permission.v1beta1.Tx.UpdateGovernanceKeyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.permission.v1beta1.Tx.UpdateGovernanceKeyRequest,
      axelar.permission.v1beta1.Tx.UpdateGovernanceKeyResponse> getUpdateGovernanceKeyMethod() {
    io.grpc.MethodDescriptor<axelar.permission.v1beta1.Tx.UpdateGovernanceKeyRequest, axelar.permission.v1beta1.Tx.UpdateGovernanceKeyResponse> getUpdateGovernanceKeyMethod;
    if ((getUpdateGovernanceKeyMethod = MsgGrpc.getUpdateGovernanceKeyMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateGovernanceKeyMethod = MsgGrpc.getUpdateGovernanceKeyMethod) == null) {
          MsgGrpc.getUpdateGovernanceKeyMethod = getUpdateGovernanceKeyMethod =
              io.grpc.MethodDescriptor.<axelar.permission.v1beta1.Tx.UpdateGovernanceKeyRequest, axelar.permission.v1beta1.Tx.UpdateGovernanceKeyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateGovernanceKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.permission.v1beta1.Tx.UpdateGovernanceKeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.permission.v1beta1.Tx.UpdateGovernanceKeyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateGovernanceKey"))
              .build();
        }
      }
    }
    return getUpdateGovernanceKeyMethod;
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
   * Msg defines the gov Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     */
    public void registerController(axelar.permission.v1beta1.Tx.RegisterControllerRequest request,
        io.grpc.stub.StreamObserver<axelar.permission.v1beta1.Tx.RegisterControllerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterControllerMethod(), responseObserver);
    }

    /**
     */
    public void deregisterController(axelar.permission.v1beta1.Tx.DeregisterControllerRequest request,
        io.grpc.stub.StreamObserver<axelar.permission.v1beta1.Tx.DeregisterControllerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeregisterControllerMethod(), responseObserver);
    }

    /**
     */
    public void updateGovernanceKey(axelar.permission.v1beta1.Tx.UpdateGovernanceKeyRequest request,
        io.grpc.stub.StreamObserver<axelar.permission.v1beta1.Tx.UpdateGovernanceKeyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateGovernanceKeyMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterControllerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.permission.v1beta1.Tx.RegisterControllerRequest,
                axelar.permission.v1beta1.Tx.RegisterControllerResponse>(
                  this, METHODID_REGISTER_CONTROLLER)))
          .addMethod(
            getDeregisterControllerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.permission.v1beta1.Tx.DeregisterControllerRequest,
                axelar.permission.v1beta1.Tx.DeregisterControllerResponse>(
                  this, METHODID_DEREGISTER_CONTROLLER)))
          .addMethod(
            getUpdateGovernanceKeyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.permission.v1beta1.Tx.UpdateGovernanceKeyRequest,
                axelar.permission.v1beta1.Tx.UpdateGovernanceKeyResponse>(
                  this, METHODID_UPDATE_GOVERNANCE_KEY)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the gov Msg service.
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
     */
    public void registerController(axelar.permission.v1beta1.Tx.RegisterControllerRequest request,
        io.grpc.stub.StreamObserver<axelar.permission.v1beta1.Tx.RegisterControllerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterControllerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deregisterController(axelar.permission.v1beta1.Tx.DeregisterControllerRequest request,
        io.grpc.stub.StreamObserver<axelar.permission.v1beta1.Tx.DeregisterControllerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeregisterControllerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateGovernanceKey(axelar.permission.v1beta1.Tx.UpdateGovernanceKeyRequest request,
        io.grpc.stub.StreamObserver<axelar.permission.v1beta1.Tx.UpdateGovernanceKeyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateGovernanceKeyMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the gov Msg service.
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
     */
    public axelar.permission.v1beta1.Tx.RegisterControllerResponse registerController(axelar.permission.v1beta1.Tx.RegisterControllerRequest request) {
      return blockingUnaryCall(
          getChannel(), getRegisterControllerMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.permission.v1beta1.Tx.DeregisterControllerResponse deregisterController(axelar.permission.v1beta1.Tx.DeregisterControllerRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeregisterControllerMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.permission.v1beta1.Tx.UpdateGovernanceKeyResponse updateGovernanceKey(axelar.permission.v1beta1.Tx.UpdateGovernanceKeyRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpdateGovernanceKeyMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the gov Msg service.
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
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.permission.v1beta1.Tx.RegisterControllerResponse> registerController(
        axelar.permission.v1beta1.Tx.RegisterControllerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterControllerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.permission.v1beta1.Tx.DeregisterControllerResponse> deregisterController(
        axelar.permission.v1beta1.Tx.DeregisterControllerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeregisterControllerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.permission.v1beta1.Tx.UpdateGovernanceKeyResponse> updateGovernanceKey(
        axelar.permission.v1beta1.Tx.UpdateGovernanceKeyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateGovernanceKeyMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_CONTROLLER = 0;
  private static final int METHODID_DEREGISTER_CONTROLLER = 1;
  private static final int METHODID_UPDATE_GOVERNANCE_KEY = 2;

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
        case METHODID_REGISTER_CONTROLLER:
          serviceImpl.registerController((axelar.permission.v1beta1.Tx.RegisterControllerRequest) request,
              (io.grpc.stub.StreamObserver<axelar.permission.v1beta1.Tx.RegisterControllerResponse>) responseObserver);
          break;
        case METHODID_DEREGISTER_CONTROLLER:
          serviceImpl.deregisterController((axelar.permission.v1beta1.Tx.DeregisterControllerRequest) request,
              (io.grpc.stub.StreamObserver<axelar.permission.v1beta1.Tx.DeregisterControllerResponse>) responseObserver);
          break;
        case METHODID_UPDATE_GOVERNANCE_KEY:
          serviceImpl.updateGovernanceKey((axelar.permission.v1beta1.Tx.UpdateGovernanceKeyRequest) request,
              (io.grpc.stub.StreamObserver<axelar.permission.v1beta1.Tx.UpdateGovernanceKeyResponse>) responseObserver);
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
      return axelar.permission.v1beta1.Service.getDescriptor();
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
              .addMethod(getRegisterControllerMethod())
              .addMethod(getDeregisterControllerMethod())
              .addMethod(getUpdateGovernanceKeyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
