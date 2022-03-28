package comdex.oracle.v1beta1;

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
    comments = "Source: comdex/oracle/v1beta1/msg.proto")
public final class MsgServiceGrpc {

  private MsgServiceGrpc() {}

  public static final String SERVICE_NAME = "comdex.oracle.v1beta1.MsgService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Msg.MsgAddMarketRequest,
      comdex.oracle.v1beta1.Msg.MsgAddMarketResponse> getMsgAddMarketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgAddMarket",
      requestType = comdex.oracle.v1beta1.Msg.MsgAddMarketRequest.class,
      responseType = comdex.oracle.v1beta1.Msg.MsgAddMarketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Msg.MsgAddMarketRequest,
      comdex.oracle.v1beta1.Msg.MsgAddMarketResponse> getMsgAddMarketMethod() {
    io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Msg.MsgAddMarketRequest, comdex.oracle.v1beta1.Msg.MsgAddMarketResponse> getMsgAddMarketMethod;
    if ((getMsgAddMarketMethod = MsgServiceGrpc.getMsgAddMarketMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgAddMarketMethod = MsgServiceGrpc.getMsgAddMarketMethod) == null) {
          MsgServiceGrpc.getMsgAddMarketMethod = getMsgAddMarketMethod =
              io.grpc.MethodDescriptor.<comdex.oracle.v1beta1.Msg.MsgAddMarketRequest, comdex.oracle.v1beta1.Msg.MsgAddMarketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgAddMarket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.oracle.v1beta1.Msg.MsgAddMarketRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.oracle.v1beta1.Msg.MsgAddMarketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgAddMarket"))
              .build();
        }
      }
    }
    return getMsgAddMarketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Msg.MsgUpdateMarketRequest,
      comdex.oracle.v1beta1.Msg.MsgUpdateMarketResponse> getMsgUpdateMarketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgUpdateMarket",
      requestType = comdex.oracle.v1beta1.Msg.MsgUpdateMarketRequest.class,
      responseType = comdex.oracle.v1beta1.Msg.MsgUpdateMarketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Msg.MsgUpdateMarketRequest,
      comdex.oracle.v1beta1.Msg.MsgUpdateMarketResponse> getMsgUpdateMarketMethod() {
    io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Msg.MsgUpdateMarketRequest, comdex.oracle.v1beta1.Msg.MsgUpdateMarketResponse> getMsgUpdateMarketMethod;
    if ((getMsgUpdateMarketMethod = MsgServiceGrpc.getMsgUpdateMarketMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgUpdateMarketMethod = MsgServiceGrpc.getMsgUpdateMarketMethod) == null) {
          MsgServiceGrpc.getMsgUpdateMarketMethod = getMsgUpdateMarketMethod =
              io.grpc.MethodDescriptor.<comdex.oracle.v1beta1.Msg.MsgUpdateMarketRequest, comdex.oracle.v1beta1.Msg.MsgUpdateMarketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgUpdateMarket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.oracle.v1beta1.Msg.MsgUpdateMarketRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.oracle.v1beta1.Msg.MsgUpdateMarketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgUpdateMarket"))
              .build();
        }
      }
    }
    return getMsgUpdateMarketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetRequest,
      comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetResponse> getMsgRemoveMarketForAssetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgRemoveMarketForAsset",
      requestType = comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetRequest.class,
      responseType = comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetRequest,
      comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetResponse> getMsgRemoveMarketForAssetMethod() {
    io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetRequest, comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetResponse> getMsgRemoveMarketForAssetMethod;
    if ((getMsgRemoveMarketForAssetMethod = MsgServiceGrpc.getMsgRemoveMarketForAssetMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgRemoveMarketForAssetMethod = MsgServiceGrpc.getMsgRemoveMarketForAssetMethod) == null) {
          MsgServiceGrpc.getMsgRemoveMarketForAssetMethod = getMsgRemoveMarketForAssetMethod =
              io.grpc.MethodDescriptor.<comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetRequest, comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgRemoveMarketForAsset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgRemoveMarketForAsset"))
              .build();
        }
      }
    }
    return getMsgRemoveMarketForAssetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Msg.MsgFetchPriceRequest,
      comdex.oracle.v1beta1.Msg.MsgFetchPriceResponse> getMsgFetchPriceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgFetchPrice",
      requestType = comdex.oracle.v1beta1.Msg.MsgFetchPriceRequest.class,
      responseType = comdex.oracle.v1beta1.Msg.MsgFetchPriceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Msg.MsgFetchPriceRequest,
      comdex.oracle.v1beta1.Msg.MsgFetchPriceResponse> getMsgFetchPriceMethod() {
    io.grpc.MethodDescriptor<comdex.oracle.v1beta1.Msg.MsgFetchPriceRequest, comdex.oracle.v1beta1.Msg.MsgFetchPriceResponse> getMsgFetchPriceMethod;
    if ((getMsgFetchPriceMethod = MsgServiceGrpc.getMsgFetchPriceMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgFetchPriceMethod = MsgServiceGrpc.getMsgFetchPriceMethod) == null) {
          MsgServiceGrpc.getMsgFetchPriceMethod = getMsgFetchPriceMethod =
              io.grpc.MethodDescriptor.<comdex.oracle.v1beta1.Msg.MsgFetchPriceRequest, comdex.oracle.v1beta1.Msg.MsgFetchPriceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgFetchPrice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.oracle.v1beta1.Msg.MsgFetchPriceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.oracle.v1beta1.Msg.MsgFetchPriceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgFetchPrice"))
              .build();
        }
      }
    }
    return getMsgFetchPriceMethod;
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
    public void msgAddMarket(comdex.oracle.v1beta1.Msg.MsgAddMarketRequest request,
        io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Msg.MsgAddMarketResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgAddMarketMethod(), responseObserver);
    }

    /**
     */
    public void msgUpdateMarket(comdex.oracle.v1beta1.Msg.MsgUpdateMarketRequest request,
        io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Msg.MsgUpdateMarketResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgUpdateMarketMethod(), responseObserver);
    }

    /**
     */
    public void msgRemoveMarketForAsset(comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetRequest request,
        io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgRemoveMarketForAssetMethod(), responseObserver);
    }

    /**
     */
    public void msgFetchPrice(comdex.oracle.v1beta1.Msg.MsgFetchPriceRequest request,
        io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Msg.MsgFetchPriceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgFetchPriceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMsgAddMarketMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.oracle.v1beta1.Msg.MsgAddMarketRequest,
                comdex.oracle.v1beta1.Msg.MsgAddMarketResponse>(
                  this, METHODID_MSG_ADD_MARKET)))
          .addMethod(
            getMsgUpdateMarketMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.oracle.v1beta1.Msg.MsgUpdateMarketRequest,
                comdex.oracle.v1beta1.Msg.MsgUpdateMarketResponse>(
                  this, METHODID_MSG_UPDATE_MARKET)))
          .addMethod(
            getMsgRemoveMarketForAssetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetRequest,
                comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetResponse>(
                  this, METHODID_MSG_REMOVE_MARKET_FOR_ASSET)))
          .addMethod(
            getMsgFetchPriceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.oracle.v1beta1.Msg.MsgFetchPriceRequest,
                comdex.oracle.v1beta1.Msg.MsgFetchPriceResponse>(
                  this, METHODID_MSG_FETCH_PRICE)))
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
    public void msgAddMarket(comdex.oracle.v1beta1.Msg.MsgAddMarketRequest request,
        io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Msg.MsgAddMarketResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgAddMarketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgUpdateMarket(comdex.oracle.v1beta1.Msg.MsgUpdateMarketRequest request,
        io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Msg.MsgUpdateMarketResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgUpdateMarketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgRemoveMarketForAsset(comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetRequest request,
        io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgRemoveMarketForAssetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgFetchPrice(comdex.oracle.v1beta1.Msg.MsgFetchPriceRequest request,
        io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Msg.MsgFetchPriceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgFetchPriceMethod(), getCallOptions()), request, responseObserver);
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
    public comdex.oracle.v1beta1.Msg.MsgAddMarketResponse msgAddMarket(comdex.oracle.v1beta1.Msg.MsgAddMarketRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgAddMarketMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.oracle.v1beta1.Msg.MsgUpdateMarketResponse msgUpdateMarket(comdex.oracle.v1beta1.Msg.MsgUpdateMarketRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgUpdateMarketMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetResponse msgRemoveMarketForAsset(comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgRemoveMarketForAssetMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.oracle.v1beta1.Msg.MsgFetchPriceResponse msgFetchPrice(comdex.oracle.v1beta1.Msg.MsgFetchPriceRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgFetchPriceMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<comdex.oracle.v1beta1.Msg.MsgAddMarketResponse> msgAddMarket(
        comdex.oracle.v1beta1.Msg.MsgAddMarketRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgAddMarketMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.oracle.v1beta1.Msg.MsgUpdateMarketResponse> msgUpdateMarket(
        comdex.oracle.v1beta1.Msg.MsgUpdateMarketRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgUpdateMarketMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetResponse> msgRemoveMarketForAsset(
        comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgRemoveMarketForAssetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.oracle.v1beta1.Msg.MsgFetchPriceResponse> msgFetchPrice(
        comdex.oracle.v1beta1.Msg.MsgFetchPriceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgFetchPriceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MSG_ADD_MARKET = 0;
  private static final int METHODID_MSG_UPDATE_MARKET = 1;
  private static final int METHODID_MSG_REMOVE_MARKET_FOR_ASSET = 2;
  private static final int METHODID_MSG_FETCH_PRICE = 3;

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
        case METHODID_MSG_ADD_MARKET:
          serviceImpl.msgAddMarket((comdex.oracle.v1beta1.Msg.MsgAddMarketRequest) request,
              (io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Msg.MsgAddMarketResponse>) responseObserver);
          break;
        case METHODID_MSG_UPDATE_MARKET:
          serviceImpl.msgUpdateMarket((comdex.oracle.v1beta1.Msg.MsgUpdateMarketRequest) request,
              (io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Msg.MsgUpdateMarketResponse>) responseObserver);
          break;
        case METHODID_MSG_REMOVE_MARKET_FOR_ASSET:
          serviceImpl.msgRemoveMarketForAsset((comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetRequest) request,
              (io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Msg.MsgRemoveMarketForAssetResponse>) responseObserver);
          break;
        case METHODID_MSG_FETCH_PRICE:
          serviceImpl.msgFetchPrice((comdex.oracle.v1beta1.Msg.MsgFetchPriceRequest) request,
              (io.grpc.stub.StreamObserver<comdex.oracle.v1beta1.Msg.MsgFetchPriceResponse>) responseObserver);
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
      return comdex.oracle.v1beta1.Msg.getDescriptor();
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
              .addMethod(getMsgAddMarketMethod())
              .addMethod(getMsgUpdateMarketMethod())
              .addMethod(getMsgRemoveMarketForAssetMethod())
              .addMethod(getMsgFetchPriceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
