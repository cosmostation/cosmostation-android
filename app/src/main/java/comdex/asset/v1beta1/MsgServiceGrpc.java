package comdex.asset.v1beta1;

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
    comments = "Source: comdex/asset/v1beta1/msg.proto")
public final class MsgServiceGrpc {

  private MsgServiceGrpc() {}

  public static final String SERVICE_NAME = "comdex.asset.v1beta1.MsgService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<comdex.asset.v1beta1.Msg.MsgAddAssetRequest,
      comdex.asset.v1beta1.Msg.MsgAddAssetResponse> getMsgAddAssetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgAddAsset",
      requestType = comdex.asset.v1beta1.Msg.MsgAddAssetRequest.class,
      responseType = comdex.asset.v1beta1.Msg.MsgAddAssetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.asset.v1beta1.Msg.MsgAddAssetRequest,
      comdex.asset.v1beta1.Msg.MsgAddAssetResponse> getMsgAddAssetMethod() {
    io.grpc.MethodDescriptor<comdex.asset.v1beta1.Msg.MsgAddAssetRequest, comdex.asset.v1beta1.Msg.MsgAddAssetResponse> getMsgAddAssetMethod;
    if ((getMsgAddAssetMethod = MsgServiceGrpc.getMsgAddAssetMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgAddAssetMethod = MsgServiceGrpc.getMsgAddAssetMethod) == null) {
          MsgServiceGrpc.getMsgAddAssetMethod = getMsgAddAssetMethod =
              io.grpc.MethodDescriptor.<comdex.asset.v1beta1.Msg.MsgAddAssetRequest, comdex.asset.v1beta1.Msg.MsgAddAssetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgAddAsset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Msg.MsgAddAssetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Msg.MsgAddAssetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgAddAsset"))
              .build();
        }
      }
    }
    return getMsgAddAssetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.asset.v1beta1.Msg.MsgUpdateAssetRequest,
      comdex.asset.v1beta1.Msg.MsgUpdateAssetResponse> getMsgUpdateAssetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgUpdateAsset",
      requestType = comdex.asset.v1beta1.Msg.MsgUpdateAssetRequest.class,
      responseType = comdex.asset.v1beta1.Msg.MsgUpdateAssetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.asset.v1beta1.Msg.MsgUpdateAssetRequest,
      comdex.asset.v1beta1.Msg.MsgUpdateAssetResponse> getMsgUpdateAssetMethod() {
    io.grpc.MethodDescriptor<comdex.asset.v1beta1.Msg.MsgUpdateAssetRequest, comdex.asset.v1beta1.Msg.MsgUpdateAssetResponse> getMsgUpdateAssetMethod;
    if ((getMsgUpdateAssetMethod = MsgServiceGrpc.getMsgUpdateAssetMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgUpdateAssetMethod = MsgServiceGrpc.getMsgUpdateAssetMethod) == null) {
          MsgServiceGrpc.getMsgUpdateAssetMethod = getMsgUpdateAssetMethod =
              io.grpc.MethodDescriptor.<comdex.asset.v1beta1.Msg.MsgUpdateAssetRequest, comdex.asset.v1beta1.Msg.MsgUpdateAssetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgUpdateAsset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Msg.MsgUpdateAssetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Msg.MsgUpdateAssetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgUpdateAsset"))
              .build();
        }
      }
    }
    return getMsgUpdateAssetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.asset.v1beta1.Msg.MsgAddPairRequest,
      comdex.asset.v1beta1.Msg.MsgAddPairResponse> getMsgAddPairMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgAddPair",
      requestType = comdex.asset.v1beta1.Msg.MsgAddPairRequest.class,
      responseType = comdex.asset.v1beta1.Msg.MsgAddPairResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.asset.v1beta1.Msg.MsgAddPairRequest,
      comdex.asset.v1beta1.Msg.MsgAddPairResponse> getMsgAddPairMethod() {
    io.grpc.MethodDescriptor<comdex.asset.v1beta1.Msg.MsgAddPairRequest, comdex.asset.v1beta1.Msg.MsgAddPairResponse> getMsgAddPairMethod;
    if ((getMsgAddPairMethod = MsgServiceGrpc.getMsgAddPairMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgAddPairMethod = MsgServiceGrpc.getMsgAddPairMethod) == null) {
          MsgServiceGrpc.getMsgAddPairMethod = getMsgAddPairMethod =
              io.grpc.MethodDescriptor.<comdex.asset.v1beta1.Msg.MsgAddPairRequest, comdex.asset.v1beta1.Msg.MsgAddPairResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgAddPair"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Msg.MsgAddPairRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Msg.MsgAddPairResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgAddPair"))
              .build();
        }
      }
    }
    return getMsgAddPairMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.asset.v1beta1.Msg.MsgUpdatePairRequest,
      comdex.asset.v1beta1.Msg.MsgUpdatePairResponse> getMsgUpdatePairMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgUpdatePair",
      requestType = comdex.asset.v1beta1.Msg.MsgUpdatePairRequest.class,
      responseType = comdex.asset.v1beta1.Msg.MsgUpdatePairResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.asset.v1beta1.Msg.MsgUpdatePairRequest,
      comdex.asset.v1beta1.Msg.MsgUpdatePairResponse> getMsgUpdatePairMethod() {
    io.grpc.MethodDescriptor<comdex.asset.v1beta1.Msg.MsgUpdatePairRequest, comdex.asset.v1beta1.Msg.MsgUpdatePairResponse> getMsgUpdatePairMethod;
    if ((getMsgUpdatePairMethod = MsgServiceGrpc.getMsgUpdatePairMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgUpdatePairMethod = MsgServiceGrpc.getMsgUpdatePairMethod) == null) {
          MsgServiceGrpc.getMsgUpdatePairMethod = getMsgUpdatePairMethod =
              io.grpc.MethodDescriptor.<comdex.asset.v1beta1.Msg.MsgUpdatePairRequest, comdex.asset.v1beta1.Msg.MsgUpdatePairResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgUpdatePair"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Msg.MsgUpdatePairRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.asset.v1beta1.Msg.MsgUpdatePairResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgUpdatePair"))
              .build();
        }
      }
    }
    return getMsgUpdatePairMethod;
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
    public void msgAddAsset(comdex.asset.v1beta1.Msg.MsgAddAssetRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Msg.MsgAddAssetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgAddAssetMethod(), responseObserver);
    }

    /**
     */
    public void msgUpdateAsset(comdex.asset.v1beta1.Msg.MsgUpdateAssetRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Msg.MsgUpdateAssetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgUpdateAssetMethod(), responseObserver);
    }

    /**
     */
    public void msgAddPair(comdex.asset.v1beta1.Msg.MsgAddPairRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Msg.MsgAddPairResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgAddPairMethod(), responseObserver);
    }

    /**
     */
    public void msgUpdatePair(comdex.asset.v1beta1.Msg.MsgUpdatePairRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Msg.MsgUpdatePairResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgUpdatePairMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMsgAddAssetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.asset.v1beta1.Msg.MsgAddAssetRequest,
                comdex.asset.v1beta1.Msg.MsgAddAssetResponse>(
                  this, METHODID_MSG_ADD_ASSET)))
          .addMethod(
            getMsgUpdateAssetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.asset.v1beta1.Msg.MsgUpdateAssetRequest,
                comdex.asset.v1beta1.Msg.MsgUpdateAssetResponse>(
                  this, METHODID_MSG_UPDATE_ASSET)))
          .addMethod(
            getMsgAddPairMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.asset.v1beta1.Msg.MsgAddPairRequest,
                comdex.asset.v1beta1.Msg.MsgAddPairResponse>(
                  this, METHODID_MSG_ADD_PAIR)))
          .addMethod(
            getMsgUpdatePairMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.asset.v1beta1.Msg.MsgUpdatePairRequest,
                comdex.asset.v1beta1.Msg.MsgUpdatePairResponse>(
                  this, METHODID_MSG_UPDATE_PAIR)))
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
    public void msgAddAsset(comdex.asset.v1beta1.Msg.MsgAddAssetRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Msg.MsgAddAssetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgAddAssetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgUpdateAsset(comdex.asset.v1beta1.Msg.MsgUpdateAssetRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Msg.MsgUpdateAssetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgUpdateAssetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgAddPair(comdex.asset.v1beta1.Msg.MsgAddPairRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Msg.MsgAddPairResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgAddPairMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgUpdatePair(comdex.asset.v1beta1.Msg.MsgUpdatePairRequest request,
        io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Msg.MsgUpdatePairResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgUpdatePairMethod(), getCallOptions()), request, responseObserver);
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
    public comdex.asset.v1beta1.Msg.MsgAddAssetResponse msgAddAsset(comdex.asset.v1beta1.Msg.MsgAddAssetRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgAddAssetMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.asset.v1beta1.Msg.MsgUpdateAssetResponse msgUpdateAsset(comdex.asset.v1beta1.Msg.MsgUpdateAssetRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgUpdateAssetMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.asset.v1beta1.Msg.MsgAddPairResponse msgAddPair(comdex.asset.v1beta1.Msg.MsgAddPairRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgAddPairMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.asset.v1beta1.Msg.MsgUpdatePairResponse msgUpdatePair(comdex.asset.v1beta1.Msg.MsgUpdatePairRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgUpdatePairMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<comdex.asset.v1beta1.Msg.MsgAddAssetResponse> msgAddAsset(
        comdex.asset.v1beta1.Msg.MsgAddAssetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgAddAssetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.asset.v1beta1.Msg.MsgUpdateAssetResponse> msgUpdateAsset(
        comdex.asset.v1beta1.Msg.MsgUpdateAssetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgUpdateAssetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.asset.v1beta1.Msg.MsgAddPairResponse> msgAddPair(
        comdex.asset.v1beta1.Msg.MsgAddPairRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgAddPairMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.asset.v1beta1.Msg.MsgUpdatePairResponse> msgUpdatePair(
        comdex.asset.v1beta1.Msg.MsgUpdatePairRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgUpdatePairMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MSG_ADD_ASSET = 0;
  private static final int METHODID_MSG_UPDATE_ASSET = 1;
  private static final int METHODID_MSG_ADD_PAIR = 2;
  private static final int METHODID_MSG_UPDATE_PAIR = 3;

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
        case METHODID_MSG_ADD_ASSET:
          serviceImpl.msgAddAsset((comdex.asset.v1beta1.Msg.MsgAddAssetRequest) request,
              (io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Msg.MsgAddAssetResponse>) responseObserver);
          break;
        case METHODID_MSG_UPDATE_ASSET:
          serviceImpl.msgUpdateAsset((comdex.asset.v1beta1.Msg.MsgUpdateAssetRequest) request,
              (io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Msg.MsgUpdateAssetResponse>) responseObserver);
          break;
        case METHODID_MSG_ADD_PAIR:
          serviceImpl.msgAddPair((comdex.asset.v1beta1.Msg.MsgAddPairRequest) request,
              (io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Msg.MsgAddPairResponse>) responseObserver);
          break;
        case METHODID_MSG_UPDATE_PAIR:
          serviceImpl.msgUpdatePair((comdex.asset.v1beta1.Msg.MsgUpdatePairRequest) request,
              (io.grpc.stub.StreamObserver<comdex.asset.v1beta1.Msg.MsgUpdatePairResponse>) responseObserver);
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
      return comdex.asset.v1beta1.Msg.getDescriptor();
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
              .addMethod(getMsgAddAssetMethod())
              .addMethod(getMsgUpdateAssetMethod())
              .addMethod(getMsgAddPairMethod())
              .addMethod(getMsgUpdatePairMethod())
              .build();
        }
      }
    }
    return result;
  }
}
