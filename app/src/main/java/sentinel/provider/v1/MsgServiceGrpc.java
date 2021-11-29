package sentinel.provider.v1;

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
    comments = "Source: sentinel/provider/v1/msg.proto")
public final class MsgServiceGrpc {

  private MsgServiceGrpc() {}

  public static final String SERVICE_NAME = "sentinel.provider.v1.MsgService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sentinel.provider.v1.Msg.MsgRegisterRequest,
      sentinel.provider.v1.Msg.MsgRegisterResponse> getMsgRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgRegister",
      requestType = sentinel.provider.v1.Msg.MsgRegisterRequest.class,
      responseType = sentinel.provider.v1.Msg.MsgRegisterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.provider.v1.Msg.MsgRegisterRequest,
      sentinel.provider.v1.Msg.MsgRegisterResponse> getMsgRegisterMethod() {
    io.grpc.MethodDescriptor<sentinel.provider.v1.Msg.MsgRegisterRequest, sentinel.provider.v1.Msg.MsgRegisterResponse> getMsgRegisterMethod;
    if ((getMsgRegisterMethod = MsgServiceGrpc.getMsgRegisterMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgRegisterMethod = MsgServiceGrpc.getMsgRegisterMethod) == null) {
          MsgServiceGrpc.getMsgRegisterMethod = getMsgRegisterMethod =
              io.grpc.MethodDescriptor.<sentinel.provider.v1.Msg.MsgRegisterRequest, sentinel.provider.v1.Msg.MsgRegisterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgRegister"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.provider.v1.Msg.MsgRegisterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.provider.v1.Msg.MsgRegisterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgRegister"))
              .build();
        }
      }
    }
    return getMsgRegisterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.provider.v1.Msg.MsgUpdateRequest,
      sentinel.provider.v1.Msg.MsgUpdateResponse> getMsgUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgUpdate",
      requestType = sentinel.provider.v1.Msg.MsgUpdateRequest.class,
      responseType = sentinel.provider.v1.Msg.MsgUpdateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.provider.v1.Msg.MsgUpdateRequest,
      sentinel.provider.v1.Msg.MsgUpdateResponse> getMsgUpdateMethod() {
    io.grpc.MethodDescriptor<sentinel.provider.v1.Msg.MsgUpdateRequest, sentinel.provider.v1.Msg.MsgUpdateResponse> getMsgUpdateMethod;
    if ((getMsgUpdateMethod = MsgServiceGrpc.getMsgUpdateMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgUpdateMethod = MsgServiceGrpc.getMsgUpdateMethod) == null) {
          MsgServiceGrpc.getMsgUpdateMethod = getMsgUpdateMethod =
              io.grpc.MethodDescriptor.<sentinel.provider.v1.Msg.MsgUpdateRequest, sentinel.provider.v1.Msg.MsgUpdateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgUpdate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.provider.v1.Msg.MsgUpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.provider.v1.Msg.MsgUpdateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgUpdate"))
              .build();
        }
      }
    }
    return getMsgUpdateMethod;
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
   */
  public static abstract class MsgServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void msgRegister(sentinel.provider.v1.Msg.MsgRegisterRequest request,
        io.grpc.stub.StreamObserver<sentinel.provider.v1.Msg.MsgRegisterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgRegisterMethod(), responseObserver);
    }

    /**
     */
    public void msgUpdate(sentinel.provider.v1.Msg.MsgUpdateRequest request,
        io.grpc.stub.StreamObserver<sentinel.provider.v1.Msg.MsgUpdateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgUpdateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMsgRegisterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.provider.v1.Msg.MsgRegisterRequest,
                sentinel.provider.v1.Msg.MsgRegisterResponse>(
                  this, METHODID_MSG_REGISTER)))
          .addMethod(
            getMsgUpdateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.provider.v1.Msg.MsgUpdateRequest,
                sentinel.provider.v1.Msg.MsgUpdateResponse>(
                  this, METHODID_MSG_UPDATE)))
          .build();
    }
  }

  /**
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
    public void msgRegister(sentinel.provider.v1.Msg.MsgRegisterRequest request,
        io.grpc.stub.StreamObserver<sentinel.provider.v1.Msg.MsgRegisterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgRegisterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgUpdate(sentinel.provider.v1.Msg.MsgUpdateRequest request,
        io.grpc.stub.StreamObserver<sentinel.provider.v1.Msg.MsgUpdateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgUpdateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
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
    public sentinel.provider.v1.Msg.MsgRegisterResponse msgRegister(sentinel.provider.v1.Msg.MsgRegisterRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgRegisterMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.provider.v1.Msg.MsgUpdateResponse msgUpdate(sentinel.provider.v1.Msg.MsgUpdateRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgUpdateMethod(), getCallOptions(), request);
    }
  }

  /**
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
    public com.google.common.util.concurrent.ListenableFuture<sentinel.provider.v1.Msg.MsgRegisterResponse> msgRegister(
        sentinel.provider.v1.Msg.MsgRegisterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgRegisterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.provider.v1.Msg.MsgUpdateResponse> msgUpdate(
        sentinel.provider.v1.Msg.MsgUpdateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgUpdateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MSG_REGISTER = 0;
  private static final int METHODID_MSG_UPDATE = 1;

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
        case METHODID_MSG_REGISTER:
          serviceImpl.msgRegister((sentinel.provider.v1.Msg.MsgRegisterRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.provider.v1.Msg.MsgRegisterResponse>) responseObserver);
          break;
        case METHODID_MSG_UPDATE:
          serviceImpl.msgUpdate((sentinel.provider.v1.Msg.MsgUpdateRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.provider.v1.Msg.MsgUpdateResponse>) responseObserver);
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
      return sentinel.provider.v1.Msg.getDescriptor();
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
              .addMethod(getMsgRegisterMethod())
              .addMethod(getMsgUpdateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
