package sentinel.subscription.v1;

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
    comments = "Source: sentinel/subscription/v1/msg.proto")
public final class MsgServiceGrpc {

  private MsgServiceGrpc() {}

  public static final String SERVICE_NAME = "sentinel.subscription.v1.MsgService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sentinel.subscription.v1.Msg.MsgSubscribeToNodeRequest,
      sentinel.subscription.v1.Msg.MsgSubscribeToNodeResponse> getMsgSubscribeToNodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgSubscribeToNode",
      requestType = sentinel.subscription.v1.Msg.MsgSubscribeToNodeRequest.class,
      responseType = sentinel.subscription.v1.Msg.MsgSubscribeToNodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.subscription.v1.Msg.MsgSubscribeToNodeRequest,
      sentinel.subscription.v1.Msg.MsgSubscribeToNodeResponse> getMsgSubscribeToNodeMethod() {
    io.grpc.MethodDescriptor<sentinel.subscription.v1.Msg.MsgSubscribeToNodeRequest, sentinel.subscription.v1.Msg.MsgSubscribeToNodeResponse> getMsgSubscribeToNodeMethod;
    if ((getMsgSubscribeToNodeMethod = MsgServiceGrpc.getMsgSubscribeToNodeMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgSubscribeToNodeMethod = MsgServiceGrpc.getMsgSubscribeToNodeMethod) == null) {
          MsgServiceGrpc.getMsgSubscribeToNodeMethod = getMsgSubscribeToNodeMethod =
              io.grpc.MethodDescriptor.<sentinel.subscription.v1.Msg.MsgSubscribeToNodeRequest, sentinel.subscription.v1.Msg.MsgSubscribeToNodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgSubscribeToNode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v1.Msg.MsgSubscribeToNodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v1.Msg.MsgSubscribeToNodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgSubscribeToNode"))
              .build();
        }
      }
    }
    return getMsgSubscribeToNodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.subscription.v1.Msg.MsgSubscribeToPlanRequest,
      sentinel.subscription.v1.Msg.MsgSubscribeToPlanResponse> getMsgSubscribeToPlanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgSubscribeToPlan",
      requestType = sentinel.subscription.v1.Msg.MsgSubscribeToPlanRequest.class,
      responseType = sentinel.subscription.v1.Msg.MsgSubscribeToPlanResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.subscription.v1.Msg.MsgSubscribeToPlanRequest,
      sentinel.subscription.v1.Msg.MsgSubscribeToPlanResponse> getMsgSubscribeToPlanMethod() {
    io.grpc.MethodDescriptor<sentinel.subscription.v1.Msg.MsgSubscribeToPlanRequest, sentinel.subscription.v1.Msg.MsgSubscribeToPlanResponse> getMsgSubscribeToPlanMethod;
    if ((getMsgSubscribeToPlanMethod = MsgServiceGrpc.getMsgSubscribeToPlanMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgSubscribeToPlanMethod = MsgServiceGrpc.getMsgSubscribeToPlanMethod) == null) {
          MsgServiceGrpc.getMsgSubscribeToPlanMethod = getMsgSubscribeToPlanMethod =
              io.grpc.MethodDescriptor.<sentinel.subscription.v1.Msg.MsgSubscribeToPlanRequest, sentinel.subscription.v1.Msg.MsgSubscribeToPlanResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgSubscribeToPlan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v1.Msg.MsgSubscribeToPlanRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v1.Msg.MsgSubscribeToPlanResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgSubscribeToPlan"))
              .build();
        }
      }
    }
    return getMsgSubscribeToPlanMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.subscription.v1.Msg.MsgCancelRequest,
      sentinel.subscription.v1.Msg.MsgCancelResponse> getMsgCancelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgCancel",
      requestType = sentinel.subscription.v1.Msg.MsgCancelRequest.class,
      responseType = sentinel.subscription.v1.Msg.MsgCancelResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.subscription.v1.Msg.MsgCancelRequest,
      sentinel.subscription.v1.Msg.MsgCancelResponse> getMsgCancelMethod() {
    io.grpc.MethodDescriptor<sentinel.subscription.v1.Msg.MsgCancelRequest, sentinel.subscription.v1.Msg.MsgCancelResponse> getMsgCancelMethod;
    if ((getMsgCancelMethod = MsgServiceGrpc.getMsgCancelMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgCancelMethod = MsgServiceGrpc.getMsgCancelMethod) == null) {
          MsgServiceGrpc.getMsgCancelMethod = getMsgCancelMethod =
              io.grpc.MethodDescriptor.<sentinel.subscription.v1.Msg.MsgCancelRequest, sentinel.subscription.v1.Msg.MsgCancelResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgCancel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v1.Msg.MsgCancelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v1.Msg.MsgCancelResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgCancel"))
              .build();
        }
      }
    }
    return getMsgCancelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.subscription.v1.Msg.MsgAddQuotaRequest,
      sentinel.subscription.v1.Msg.MsgAddQuotaResponse> getMsgAddQuotaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgAddQuota",
      requestType = sentinel.subscription.v1.Msg.MsgAddQuotaRequest.class,
      responseType = sentinel.subscription.v1.Msg.MsgAddQuotaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.subscription.v1.Msg.MsgAddQuotaRequest,
      sentinel.subscription.v1.Msg.MsgAddQuotaResponse> getMsgAddQuotaMethod() {
    io.grpc.MethodDescriptor<sentinel.subscription.v1.Msg.MsgAddQuotaRequest, sentinel.subscription.v1.Msg.MsgAddQuotaResponse> getMsgAddQuotaMethod;
    if ((getMsgAddQuotaMethod = MsgServiceGrpc.getMsgAddQuotaMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgAddQuotaMethod = MsgServiceGrpc.getMsgAddQuotaMethod) == null) {
          MsgServiceGrpc.getMsgAddQuotaMethod = getMsgAddQuotaMethod =
              io.grpc.MethodDescriptor.<sentinel.subscription.v1.Msg.MsgAddQuotaRequest, sentinel.subscription.v1.Msg.MsgAddQuotaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgAddQuota"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v1.Msg.MsgAddQuotaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v1.Msg.MsgAddQuotaResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgAddQuota"))
              .build();
        }
      }
    }
    return getMsgAddQuotaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.subscription.v1.Msg.MsgUpdateQuotaRequest,
      sentinel.subscription.v1.Msg.MsgUpdateQuotaResponse> getMsgUpdateQuotaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgUpdateQuota",
      requestType = sentinel.subscription.v1.Msg.MsgUpdateQuotaRequest.class,
      responseType = sentinel.subscription.v1.Msg.MsgUpdateQuotaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.subscription.v1.Msg.MsgUpdateQuotaRequest,
      sentinel.subscription.v1.Msg.MsgUpdateQuotaResponse> getMsgUpdateQuotaMethod() {
    io.grpc.MethodDescriptor<sentinel.subscription.v1.Msg.MsgUpdateQuotaRequest, sentinel.subscription.v1.Msg.MsgUpdateQuotaResponse> getMsgUpdateQuotaMethod;
    if ((getMsgUpdateQuotaMethod = MsgServiceGrpc.getMsgUpdateQuotaMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgUpdateQuotaMethod = MsgServiceGrpc.getMsgUpdateQuotaMethod) == null) {
          MsgServiceGrpc.getMsgUpdateQuotaMethod = getMsgUpdateQuotaMethod =
              io.grpc.MethodDescriptor.<sentinel.subscription.v1.Msg.MsgUpdateQuotaRequest, sentinel.subscription.v1.Msg.MsgUpdateQuotaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgUpdateQuota"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v1.Msg.MsgUpdateQuotaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v1.Msg.MsgUpdateQuotaResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgUpdateQuota"))
              .build();
        }
      }
    }
    return getMsgUpdateQuotaMethod;
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
    public void msgSubscribeToNode(sentinel.subscription.v1.Msg.MsgSubscribeToNodeRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v1.Msg.MsgSubscribeToNodeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgSubscribeToNodeMethod(), responseObserver);
    }

    /**
     */
    public void msgSubscribeToPlan(sentinel.subscription.v1.Msg.MsgSubscribeToPlanRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v1.Msg.MsgSubscribeToPlanResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgSubscribeToPlanMethod(), responseObserver);
    }

    /**
     */
    public void msgCancel(sentinel.subscription.v1.Msg.MsgCancelRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v1.Msg.MsgCancelResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgCancelMethod(), responseObserver);
    }

    /**
     */
    public void msgAddQuota(sentinel.subscription.v1.Msg.MsgAddQuotaRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v1.Msg.MsgAddQuotaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgAddQuotaMethod(), responseObserver);
    }

    /**
     */
    public void msgUpdateQuota(sentinel.subscription.v1.Msg.MsgUpdateQuotaRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v1.Msg.MsgUpdateQuotaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgUpdateQuotaMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMsgSubscribeToNodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.subscription.v1.Msg.MsgSubscribeToNodeRequest,
                sentinel.subscription.v1.Msg.MsgSubscribeToNodeResponse>(
                  this, METHODID_MSG_SUBSCRIBE_TO_NODE)))
          .addMethod(
            getMsgSubscribeToPlanMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.subscription.v1.Msg.MsgSubscribeToPlanRequest,
                sentinel.subscription.v1.Msg.MsgSubscribeToPlanResponse>(
                  this, METHODID_MSG_SUBSCRIBE_TO_PLAN)))
          .addMethod(
            getMsgCancelMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.subscription.v1.Msg.MsgCancelRequest,
                sentinel.subscription.v1.Msg.MsgCancelResponse>(
                  this, METHODID_MSG_CANCEL)))
          .addMethod(
            getMsgAddQuotaMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.subscription.v1.Msg.MsgAddQuotaRequest,
                sentinel.subscription.v1.Msg.MsgAddQuotaResponse>(
                  this, METHODID_MSG_ADD_QUOTA)))
          .addMethod(
            getMsgUpdateQuotaMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.subscription.v1.Msg.MsgUpdateQuotaRequest,
                sentinel.subscription.v1.Msg.MsgUpdateQuotaResponse>(
                  this, METHODID_MSG_UPDATE_QUOTA)))
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
    public void msgSubscribeToNode(sentinel.subscription.v1.Msg.MsgSubscribeToNodeRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v1.Msg.MsgSubscribeToNodeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgSubscribeToNodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgSubscribeToPlan(sentinel.subscription.v1.Msg.MsgSubscribeToPlanRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v1.Msg.MsgSubscribeToPlanResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgSubscribeToPlanMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgCancel(sentinel.subscription.v1.Msg.MsgCancelRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v1.Msg.MsgCancelResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgCancelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgAddQuota(sentinel.subscription.v1.Msg.MsgAddQuotaRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v1.Msg.MsgAddQuotaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgAddQuotaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgUpdateQuota(sentinel.subscription.v1.Msg.MsgUpdateQuotaRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v1.Msg.MsgUpdateQuotaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgUpdateQuotaMethod(), getCallOptions()), request, responseObserver);
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
    public sentinel.subscription.v1.Msg.MsgSubscribeToNodeResponse msgSubscribeToNode(sentinel.subscription.v1.Msg.MsgSubscribeToNodeRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgSubscribeToNodeMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.subscription.v1.Msg.MsgSubscribeToPlanResponse msgSubscribeToPlan(sentinel.subscription.v1.Msg.MsgSubscribeToPlanRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgSubscribeToPlanMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.subscription.v1.Msg.MsgCancelResponse msgCancel(sentinel.subscription.v1.Msg.MsgCancelRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgCancelMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.subscription.v1.Msg.MsgAddQuotaResponse msgAddQuota(sentinel.subscription.v1.Msg.MsgAddQuotaRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgAddQuotaMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.subscription.v1.Msg.MsgUpdateQuotaResponse msgUpdateQuota(sentinel.subscription.v1.Msg.MsgUpdateQuotaRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgUpdateQuotaMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<sentinel.subscription.v1.Msg.MsgSubscribeToNodeResponse> msgSubscribeToNode(
        sentinel.subscription.v1.Msg.MsgSubscribeToNodeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgSubscribeToNodeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.subscription.v1.Msg.MsgSubscribeToPlanResponse> msgSubscribeToPlan(
        sentinel.subscription.v1.Msg.MsgSubscribeToPlanRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgSubscribeToPlanMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.subscription.v1.Msg.MsgCancelResponse> msgCancel(
        sentinel.subscription.v1.Msg.MsgCancelRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgCancelMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.subscription.v1.Msg.MsgAddQuotaResponse> msgAddQuota(
        sentinel.subscription.v1.Msg.MsgAddQuotaRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgAddQuotaMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.subscription.v1.Msg.MsgUpdateQuotaResponse> msgUpdateQuota(
        sentinel.subscription.v1.Msg.MsgUpdateQuotaRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgUpdateQuotaMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MSG_SUBSCRIBE_TO_NODE = 0;
  private static final int METHODID_MSG_SUBSCRIBE_TO_PLAN = 1;
  private static final int METHODID_MSG_CANCEL = 2;
  private static final int METHODID_MSG_ADD_QUOTA = 3;
  private static final int METHODID_MSG_UPDATE_QUOTA = 4;

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
        case METHODID_MSG_SUBSCRIBE_TO_NODE:
          serviceImpl.msgSubscribeToNode((sentinel.subscription.v1.Msg.MsgSubscribeToNodeRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.subscription.v1.Msg.MsgSubscribeToNodeResponse>) responseObserver);
          break;
        case METHODID_MSG_SUBSCRIBE_TO_PLAN:
          serviceImpl.msgSubscribeToPlan((sentinel.subscription.v1.Msg.MsgSubscribeToPlanRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.subscription.v1.Msg.MsgSubscribeToPlanResponse>) responseObserver);
          break;
        case METHODID_MSG_CANCEL:
          serviceImpl.msgCancel((sentinel.subscription.v1.Msg.MsgCancelRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.subscription.v1.Msg.MsgCancelResponse>) responseObserver);
          break;
        case METHODID_MSG_ADD_QUOTA:
          serviceImpl.msgAddQuota((sentinel.subscription.v1.Msg.MsgAddQuotaRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.subscription.v1.Msg.MsgAddQuotaResponse>) responseObserver);
          break;
        case METHODID_MSG_UPDATE_QUOTA:
          serviceImpl.msgUpdateQuota((sentinel.subscription.v1.Msg.MsgUpdateQuotaRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.subscription.v1.Msg.MsgUpdateQuotaResponse>) responseObserver);
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
      return sentinel.subscription.v1.Msg.getDescriptor();
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
              .addMethod(getMsgSubscribeToNodeMethod())
              .addMethod(getMsgSubscribeToPlanMethod())
              .addMethod(getMsgCancelMethod())
              .addMethod(getMsgAddQuotaMethod())
              .addMethod(getMsgUpdateQuotaMethod())
              .build();
        }
      }
    }
    return result;
  }
}
