package sentinel.plan.v1;

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
    comments = "Source: sentinel/plan/v1/msg.proto")
public final class MsgServiceGrpc {

  private MsgServiceGrpc() {}

  public static final String SERVICE_NAME = "sentinel.plan.v1.MsgService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sentinel.plan.v1.Msg.MsgAddRequest,
      sentinel.plan.v1.Msg.MsgAddResponse> getMsgAddMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgAdd",
      requestType = sentinel.plan.v1.Msg.MsgAddRequest.class,
      responseType = sentinel.plan.v1.Msg.MsgAddResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.plan.v1.Msg.MsgAddRequest,
      sentinel.plan.v1.Msg.MsgAddResponse> getMsgAddMethod() {
    io.grpc.MethodDescriptor<sentinel.plan.v1.Msg.MsgAddRequest, sentinel.plan.v1.Msg.MsgAddResponse> getMsgAddMethod;
    if ((getMsgAddMethod = MsgServiceGrpc.getMsgAddMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgAddMethod = MsgServiceGrpc.getMsgAddMethod) == null) {
          MsgServiceGrpc.getMsgAddMethod = getMsgAddMethod =
              io.grpc.MethodDescriptor.<sentinel.plan.v1.Msg.MsgAddRequest, sentinel.plan.v1.Msg.MsgAddResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgAdd"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v1.Msg.MsgAddRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v1.Msg.MsgAddResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgAdd"))
              .build();
        }
      }
    }
    return getMsgAddMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.plan.v1.Msg.MsgSetStatusRequest,
      sentinel.plan.v1.Msg.MsgSetStatusResponse> getMsgSetStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgSetStatus",
      requestType = sentinel.plan.v1.Msg.MsgSetStatusRequest.class,
      responseType = sentinel.plan.v1.Msg.MsgSetStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.plan.v1.Msg.MsgSetStatusRequest,
      sentinel.plan.v1.Msg.MsgSetStatusResponse> getMsgSetStatusMethod() {
    io.grpc.MethodDescriptor<sentinel.plan.v1.Msg.MsgSetStatusRequest, sentinel.plan.v1.Msg.MsgSetStatusResponse> getMsgSetStatusMethod;
    if ((getMsgSetStatusMethod = MsgServiceGrpc.getMsgSetStatusMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgSetStatusMethod = MsgServiceGrpc.getMsgSetStatusMethod) == null) {
          MsgServiceGrpc.getMsgSetStatusMethod = getMsgSetStatusMethod =
              io.grpc.MethodDescriptor.<sentinel.plan.v1.Msg.MsgSetStatusRequest, sentinel.plan.v1.Msg.MsgSetStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgSetStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v1.Msg.MsgSetStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v1.Msg.MsgSetStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgSetStatus"))
              .build();
        }
      }
    }
    return getMsgSetStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.plan.v1.Msg.MsgAddNodeRequest,
      sentinel.plan.v1.Msg.MsgAddNodeResponse> getMsgAddNodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgAddNode",
      requestType = sentinel.plan.v1.Msg.MsgAddNodeRequest.class,
      responseType = sentinel.plan.v1.Msg.MsgAddNodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.plan.v1.Msg.MsgAddNodeRequest,
      sentinel.plan.v1.Msg.MsgAddNodeResponse> getMsgAddNodeMethod() {
    io.grpc.MethodDescriptor<sentinel.plan.v1.Msg.MsgAddNodeRequest, sentinel.plan.v1.Msg.MsgAddNodeResponse> getMsgAddNodeMethod;
    if ((getMsgAddNodeMethod = MsgServiceGrpc.getMsgAddNodeMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgAddNodeMethod = MsgServiceGrpc.getMsgAddNodeMethod) == null) {
          MsgServiceGrpc.getMsgAddNodeMethod = getMsgAddNodeMethod =
              io.grpc.MethodDescriptor.<sentinel.plan.v1.Msg.MsgAddNodeRequest, sentinel.plan.v1.Msg.MsgAddNodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgAddNode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v1.Msg.MsgAddNodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v1.Msg.MsgAddNodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgAddNode"))
              .build();
        }
      }
    }
    return getMsgAddNodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.plan.v1.Msg.MsgRemoveNodeRequest,
      sentinel.plan.v1.Msg.MsgRemoveNodeResponse> getMsgRemoveNodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgRemoveNode",
      requestType = sentinel.plan.v1.Msg.MsgRemoveNodeRequest.class,
      responseType = sentinel.plan.v1.Msg.MsgRemoveNodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.plan.v1.Msg.MsgRemoveNodeRequest,
      sentinel.plan.v1.Msg.MsgRemoveNodeResponse> getMsgRemoveNodeMethod() {
    io.grpc.MethodDescriptor<sentinel.plan.v1.Msg.MsgRemoveNodeRequest, sentinel.plan.v1.Msg.MsgRemoveNodeResponse> getMsgRemoveNodeMethod;
    if ((getMsgRemoveNodeMethod = MsgServiceGrpc.getMsgRemoveNodeMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgRemoveNodeMethod = MsgServiceGrpc.getMsgRemoveNodeMethod) == null) {
          MsgServiceGrpc.getMsgRemoveNodeMethod = getMsgRemoveNodeMethod =
              io.grpc.MethodDescriptor.<sentinel.plan.v1.Msg.MsgRemoveNodeRequest, sentinel.plan.v1.Msg.MsgRemoveNodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgRemoveNode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v1.Msg.MsgRemoveNodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v1.Msg.MsgRemoveNodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgRemoveNode"))
              .build();
        }
      }
    }
    return getMsgRemoveNodeMethod;
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
    public void msgAdd(sentinel.plan.v1.Msg.MsgAddRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v1.Msg.MsgAddResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgAddMethod(), responseObserver);
    }

    /**
     */
    public void msgSetStatus(sentinel.plan.v1.Msg.MsgSetStatusRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v1.Msg.MsgSetStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgSetStatusMethod(), responseObserver);
    }

    /**
     */
    public void msgAddNode(sentinel.plan.v1.Msg.MsgAddNodeRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v1.Msg.MsgAddNodeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgAddNodeMethod(), responseObserver);
    }

    /**
     */
    public void msgRemoveNode(sentinel.plan.v1.Msg.MsgRemoveNodeRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v1.Msg.MsgRemoveNodeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgRemoveNodeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMsgAddMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.plan.v1.Msg.MsgAddRequest,
                sentinel.plan.v1.Msg.MsgAddResponse>(
                  this, METHODID_MSG_ADD)))
          .addMethod(
            getMsgSetStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.plan.v1.Msg.MsgSetStatusRequest,
                sentinel.plan.v1.Msg.MsgSetStatusResponse>(
                  this, METHODID_MSG_SET_STATUS)))
          .addMethod(
            getMsgAddNodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.plan.v1.Msg.MsgAddNodeRequest,
                sentinel.plan.v1.Msg.MsgAddNodeResponse>(
                  this, METHODID_MSG_ADD_NODE)))
          .addMethod(
            getMsgRemoveNodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.plan.v1.Msg.MsgRemoveNodeRequest,
                sentinel.plan.v1.Msg.MsgRemoveNodeResponse>(
                  this, METHODID_MSG_REMOVE_NODE)))
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
    public void msgAdd(sentinel.plan.v1.Msg.MsgAddRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v1.Msg.MsgAddResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgAddMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgSetStatus(sentinel.plan.v1.Msg.MsgSetStatusRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v1.Msg.MsgSetStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgSetStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgAddNode(sentinel.plan.v1.Msg.MsgAddNodeRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v1.Msg.MsgAddNodeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgAddNodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgRemoveNode(sentinel.plan.v1.Msg.MsgRemoveNodeRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v1.Msg.MsgRemoveNodeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgRemoveNodeMethod(), getCallOptions()), request, responseObserver);
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
    public sentinel.plan.v1.Msg.MsgAddResponse msgAdd(sentinel.plan.v1.Msg.MsgAddRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgAddMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.plan.v1.Msg.MsgSetStatusResponse msgSetStatus(sentinel.plan.v1.Msg.MsgSetStatusRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgSetStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.plan.v1.Msg.MsgAddNodeResponse msgAddNode(sentinel.plan.v1.Msg.MsgAddNodeRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgAddNodeMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.plan.v1.Msg.MsgRemoveNodeResponse msgRemoveNode(sentinel.plan.v1.Msg.MsgRemoveNodeRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgRemoveNodeMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<sentinel.plan.v1.Msg.MsgAddResponse> msgAdd(
        sentinel.plan.v1.Msg.MsgAddRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgAddMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.plan.v1.Msg.MsgSetStatusResponse> msgSetStatus(
        sentinel.plan.v1.Msg.MsgSetStatusRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgSetStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.plan.v1.Msg.MsgAddNodeResponse> msgAddNode(
        sentinel.plan.v1.Msg.MsgAddNodeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgAddNodeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.plan.v1.Msg.MsgRemoveNodeResponse> msgRemoveNode(
        sentinel.plan.v1.Msg.MsgRemoveNodeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgRemoveNodeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MSG_ADD = 0;
  private static final int METHODID_MSG_SET_STATUS = 1;
  private static final int METHODID_MSG_ADD_NODE = 2;
  private static final int METHODID_MSG_REMOVE_NODE = 3;

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
        case METHODID_MSG_ADD:
          serviceImpl.msgAdd((sentinel.plan.v1.Msg.MsgAddRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.plan.v1.Msg.MsgAddResponse>) responseObserver);
          break;
        case METHODID_MSG_SET_STATUS:
          serviceImpl.msgSetStatus((sentinel.plan.v1.Msg.MsgSetStatusRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.plan.v1.Msg.MsgSetStatusResponse>) responseObserver);
          break;
        case METHODID_MSG_ADD_NODE:
          serviceImpl.msgAddNode((sentinel.plan.v1.Msg.MsgAddNodeRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.plan.v1.Msg.MsgAddNodeResponse>) responseObserver);
          break;
        case METHODID_MSG_REMOVE_NODE:
          serviceImpl.msgRemoveNode((sentinel.plan.v1.Msg.MsgRemoveNodeRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.plan.v1.Msg.MsgRemoveNodeResponse>) responseObserver);
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
      return sentinel.plan.v1.Msg.getDescriptor();
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
              .addMethod(getMsgAddMethod())
              .addMethod(getMsgSetStatusMethod())
              .addMethod(getMsgAddNodeMethod())
              .addMethod(getMsgRemoveNodeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
