package sifnode.tokenregistry.v1;

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
    comments = "Source: sifnode/tokenregistry/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "sifnode.tokenregistry.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sifnode.tokenregistry.v1.Tx.MsgRegister,
      sifnode.tokenregistry.v1.Tx.MsgRegisterResponse> getRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Register",
      requestType = sifnode.tokenregistry.v1.Tx.MsgRegister.class,
      responseType = sifnode.tokenregistry.v1.Tx.MsgRegisterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.tokenregistry.v1.Tx.MsgRegister,
      sifnode.tokenregistry.v1.Tx.MsgRegisterResponse> getRegisterMethod() {
    io.grpc.MethodDescriptor<sifnode.tokenregistry.v1.Tx.MsgRegister, sifnode.tokenregistry.v1.Tx.MsgRegisterResponse> getRegisterMethod;
    if ((getRegisterMethod = MsgGrpc.getRegisterMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRegisterMethod = MsgGrpc.getRegisterMethod) == null) {
          MsgGrpc.getRegisterMethod = getRegisterMethod =
              io.grpc.MethodDescriptor.<sifnode.tokenregistry.v1.Tx.MsgRegister, sifnode.tokenregistry.v1.Tx.MsgRegisterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.tokenregistry.v1.Tx.MsgRegister.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.tokenregistry.v1.Tx.MsgRegisterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Register"))
              .build();
        }
      }
    }
    return getRegisterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.tokenregistry.v1.Tx.MsgDeregister,
      sifnode.tokenregistry.v1.Tx.MsgDeregisterResponse> getDeregisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deregister",
      requestType = sifnode.tokenregistry.v1.Tx.MsgDeregister.class,
      responseType = sifnode.tokenregistry.v1.Tx.MsgDeregisterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.tokenregistry.v1.Tx.MsgDeregister,
      sifnode.tokenregistry.v1.Tx.MsgDeregisterResponse> getDeregisterMethod() {
    io.grpc.MethodDescriptor<sifnode.tokenregistry.v1.Tx.MsgDeregister, sifnode.tokenregistry.v1.Tx.MsgDeregisterResponse> getDeregisterMethod;
    if ((getDeregisterMethod = MsgGrpc.getDeregisterMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeregisterMethod = MsgGrpc.getDeregisterMethod) == null) {
          MsgGrpc.getDeregisterMethod = getDeregisterMethod =
              io.grpc.MethodDescriptor.<sifnode.tokenregistry.v1.Tx.MsgDeregister, sifnode.tokenregistry.v1.Tx.MsgDeregisterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deregister"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.tokenregistry.v1.Tx.MsgDeregister.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.tokenregistry.v1.Tx.MsgDeregisterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Deregister"))
              .build();
        }
      }
    }
    return getDeregisterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.tokenregistry.v1.Tx.MsgSetRegistry,
      sifnode.tokenregistry.v1.Tx.MsgSetRegistryResponse> getSetRegistryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetRegistry",
      requestType = sifnode.tokenregistry.v1.Tx.MsgSetRegistry.class,
      responseType = sifnode.tokenregistry.v1.Tx.MsgSetRegistryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.tokenregistry.v1.Tx.MsgSetRegistry,
      sifnode.tokenregistry.v1.Tx.MsgSetRegistryResponse> getSetRegistryMethod() {
    io.grpc.MethodDescriptor<sifnode.tokenregistry.v1.Tx.MsgSetRegistry, sifnode.tokenregistry.v1.Tx.MsgSetRegistryResponse> getSetRegistryMethod;
    if ((getSetRegistryMethod = MsgGrpc.getSetRegistryMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetRegistryMethod = MsgGrpc.getSetRegistryMethod) == null) {
          MsgGrpc.getSetRegistryMethod = getSetRegistryMethod =
              io.grpc.MethodDescriptor.<sifnode.tokenregistry.v1.Tx.MsgSetRegistry, sifnode.tokenregistry.v1.Tx.MsgSetRegistryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetRegistry"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.tokenregistry.v1.Tx.MsgSetRegistry.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.tokenregistry.v1.Tx.MsgSetRegistryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetRegistry"))
              .build();
        }
      }
    }
    return getSetRegistryMethod;
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
    public void register(sifnode.tokenregistry.v1.Tx.MsgRegister request,
        io.grpc.stub.StreamObserver<sifnode.tokenregistry.v1.Tx.MsgRegisterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
    }

    /**
     */
    public void deregister(sifnode.tokenregistry.v1.Tx.MsgDeregister request,
        io.grpc.stub.StreamObserver<sifnode.tokenregistry.v1.Tx.MsgDeregisterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeregisterMethod(), responseObserver);
    }

    /**
     */
    public void setRegistry(sifnode.tokenregistry.v1.Tx.MsgSetRegistry request,
        io.grpc.stub.StreamObserver<sifnode.tokenregistry.v1.Tx.MsgSetRegistryResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetRegistryMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.tokenregistry.v1.Tx.MsgRegister,
                sifnode.tokenregistry.v1.Tx.MsgRegisterResponse>(
                  this, METHODID_REGISTER)))
          .addMethod(
            getDeregisterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.tokenregistry.v1.Tx.MsgDeregister,
                sifnode.tokenregistry.v1.Tx.MsgDeregisterResponse>(
                  this, METHODID_DEREGISTER)))
          .addMethod(
            getSetRegistryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.tokenregistry.v1.Tx.MsgSetRegistry,
                sifnode.tokenregistry.v1.Tx.MsgSetRegistryResponse>(
                  this, METHODID_SET_REGISTRY)))
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
    public void register(sifnode.tokenregistry.v1.Tx.MsgRegister request,
        io.grpc.stub.StreamObserver<sifnode.tokenregistry.v1.Tx.MsgRegisterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deregister(sifnode.tokenregistry.v1.Tx.MsgDeregister request,
        io.grpc.stub.StreamObserver<sifnode.tokenregistry.v1.Tx.MsgDeregisterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeregisterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setRegistry(sifnode.tokenregistry.v1.Tx.MsgSetRegistry request,
        io.grpc.stub.StreamObserver<sifnode.tokenregistry.v1.Tx.MsgSetRegistryResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetRegistryMethod(), getCallOptions()), request, responseObserver);
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
    public sifnode.tokenregistry.v1.Tx.MsgRegisterResponse register(sifnode.tokenregistry.v1.Tx.MsgRegister request) {
      return blockingUnaryCall(
          getChannel(), getRegisterMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.tokenregistry.v1.Tx.MsgDeregisterResponse deregister(sifnode.tokenregistry.v1.Tx.MsgDeregister request) {
      return blockingUnaryCall(
          getChannel(), getDeregisterMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.tokenregistry.v1.Tx.MsgSetRegistryResponse setRegistry(sifnode.tokenregistry.v1.Tx.MsgSetRegistry request) {
      return blockingUnaryCall(
          getChannel(), getSetRegistryMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<sifnode.tokenregistry.v1.Tx.MsgRegisterResponse> register(
        sifnode.tokenregistry.v1.Tx.MsgRegister request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.tokenregistry.v1.Tx.MsgDeregisterResponse> deregister(
        sifnode.tokenregistry.v1.Tx.MsgDeregister request) {
      return futureUnaryCall(
          getChannel().newCall(getDeregisterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.tokenregistry.v1.Tx.MsgSetRegistryResponse> setRegistry(
        sifnode.tokenregistry.v1.Tx.MsgSetRegistry request) {
      return futureUnaryCall(
          getChannel().newCall(getSetRegistryMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER = 0;
  private static final int METHODID_DEREGISTER = 1;
  private static final int METHODID_SET_REGISTRY = 2;

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
        case METHODID_REGISTER:
          serviceImpl.register((sifnode.tokenregistry.v1.Tx.MsgRegister) request,
              (io.grpc.stub.StreamObserver<sifnode.tokenregistry.v1.Tx.MsgRegisterResponse>) responseObserver);
          break;
        case METHODID_DEREGISTER:
          serviceImpl.deregister((sifnode.tokenregistry.v1.Tx.MsgDeregister) request,
              (io.grpc.stub.StreamObserver<sifnode.tokenregistry.v1.Tx.MsgDeregisterResponse>) responseObserver);
          break;
        case METHODID_SET_REGISTRY:
          serviceImpl.setRegistry((sifnode.tokenregistry.v1.Tx.MsgSetRegistry) request,
              (io.grpc.stub.StreamObserver<sifnode.tokenregistry.v1.Tx.MsgSetRegistryResponse>) responseObserver);
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
      return sifnode.tokenregistry.v1.Tx.getDescriptor();
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
              .addMethod(getRegisterMethod())
              .addMethod(getDeregisterMethod())
              .addMethod(getSetRegistryMethod())
              .build();
        }
      }
    }
    return result;
  }
}
