package em.market.v1;

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
    comments = "Source: em/market/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "em.market.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<em.market.v1.Tx.MsgAddLimitOrder,
      em.market.v1.Tx.MsgAddLimitOrderResponse> getAddLimitOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddLimitOrder",
      requestType = em.market.v1.Tx.MsgAddLimitOrder.class,
      responseType = em.market.v1.Tx.MsgAddLimitOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.market.v1.Tx.MsgAddLimitOrder,
      em.market.v1.Tx.MsgAddLimitOrderResponse> getAddLimitOrderMethod() {
    io.grpc.MethodDescriptor<em.market.v1.Tx.MsgAddLimitOrder, em.market.v1.Tx.MsgAddLimitOrderResponse> getAddLimitOrderMethod;
    if ((getAddLimitOrderMethod = MsgGrpc.getAddLimitOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddLimitOrderMethod = MsgGrpc.getAddLimitOrderMethod) == null) {
          MsgGrpc.getAddLimitOrderMethod = getAddLimitOrderMethod =
              io.grpc.MethodDescriptor.<em.market.v1.Tx.MsgAddLimitOrder, em.market.v1.Tx.MsgAddLimitOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddLimitOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.market.v1.Tx.MsgAddLimitOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.market.v1.Tx.MsgAddLimitOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddLimitOrder"))
              .build();
        }
      }
    }
    return getAddLimitOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<em.market.v1.Tx.MsgAddMarketOrder,
      em.market.v1.Tx.MsgAddMarketOrderResponse> getAddMarketOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddMarketOrder",
      requestType = em.market.v1.Tx.MsgAddMarketOrder.class,
      responseType = em.market.v1.Tx.MsgAddMarketOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.market.v1.Tx.MsgAddMarketOrder,
      em.market.v1.Tx.MsgAddMarketOrderResponse> getAddMarketOrderMethod() {
    io.grpc.MethodDescriptor<em.market.v1.Tx.MsgAddMarketOrder, em.market.v1.Tx.MsgAddMarketOrderResponse> getAddMarketOrderMethod;
    if ((getAddMarketOrderMethod = MsgGrpc.getAddMarketOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddMarketOrderMethod = MsgGrpc.getAddMarketOrderMethod) == null) {
          MsgGrpc.getAddMarketOrderMethod = getAddMarketOrderMethod =
              io.grpc.MethodDescriptor.<em.market.v1.Tx.MsgAddMarketOrder, em.market.v1.Tx.MsgAddMarketOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddMarketOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.market.v1.Tx.MsgAddMarketOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.market.v1.Tx.MsgAddMarketOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddMarketOrder"))
              .build();
        }
      }
    }
    return getAddMarketOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<em.market.v1.Tx.MsgCancelOrder,
      em.market.v1.Tx.MsgCancelOrderResponse> getCancelOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelOrder",
      requestType = em.market.v1.Tx.MsgCancelOrder.class,
      responseType = em.market.v1.Tx.MsgCancelOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.market.v1.Tx.MsgCancelOrder,
      em.market.v1.Tx.MsgCancelOrderResponse> getCancelOrderMethod() {
    io.grpc.MethodDescriptor<em.market.v1.Tx.MsgCancelOrder, em.market.v1.Tx.MsgCancelOrderResponse> getCancelOrderMethod;
    if ((getCancelOrderMethod = MsgGrpc.getCancelOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelOrderMethod = MsgGrpc.getCancelOrderMethod) == null) {
          MsgGrpc.getCancelOrderMethod = getCancelOrderMethod =
              io.grpc.MethodDescriptor.<em.market.v1.Tx.MsgCancelOrder, em.market.v1.Tx.MsgCancelOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.market.v1.Tx.MsgCancelOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.market.v1.Tx.MsgCancelOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelOrder"))
              .build();
        }
      }
    }
    return getCancelOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<em.market.v1.Tx.MsgCancelReplaceLimitOrder,
      em.market.v1.Tx.MsgCancelReplaceLimitOrderResponse> getCancelReplaceLimitOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelReplaceLimitOrder",
      requestType = em.market.v1.Tx.MsgCancelReplaceLimitOrder.class,
      responseType = em.market.v1.Tx.MsgCancelReplaceLimitOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.market.v1.Tx.MsgCancelReplaceLimitOrder,
      em.market.v1.Tx.MsgCancelReplaceLimitOrderResponse> getCancelReplaceLimitOrderMethod() {
    io.grpc.MethodDescriptor<em.market.v1.Tx.MsgCancelReplaceLimitOrder, em.market.v1.Tx.MsgCancelReplaceLimitOrderResponse> getCancelReplaceLimitOrderMethod;
    if ((getCancelReplaceLimitOrderMethod = MsgGrpc.getCancelReplaceLimitOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelReplaceLimitOrderMethod = MsgGrpc.getCancelReplaceLimitOrderMethod) == null) {
          MsgGrpc.getCancelReplaceLimitOrderMethod = getCancelReplaceLimitOrderMethod =
              io.grpc.MethodDescriptor.<em.market.v1.Tx.MsgCancelReplaceLimitOrder, em.market.v1.Tx.MsgCancelReplaceLimitOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelReplaceLimitOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.market.v1.Tx.MsgCancelReplaceLimitOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.market.v1.Tx.MsgCancelReplaceLimitOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelReplaceLimitOrder"))
              .build();
        }
      }
    }
    return getCancelReplaceLimitOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<em.market.v1.Tx.MsgCancelReplaceMarketOrder,
      em.market.v1.Tx.MsgCancelReplaceMarketOrderResponse> getCancelReplaceMarketOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelReplaceMarketOrder",
      requestType = em.market.v1.Tx.MsgCancelReplaceMarketOrder.class,
      responseType = em.market.v1.Tx.MsgCancelReplaceMarketOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.market.v1.Tx.MsgCancelReplaceMarketOrder,
      em.market.v1.Tx.MsgCancelReplaceMarketOrderResponse> getCancelReplaceMarketOrderMethod() {
    io.grpc.MethodDescriptor<em.market.v1.Tx.MsgCancelReplaceMarketOrder, em.market.v1.Tx.MsgCancelReplaceMarketOrderResponse> getCancelReplaceMarketOrderMethod;
    if ((getCancelReplaceMarketOrderMethod = MsgGrpc.getCancelReplaceMarketOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelReplaceMarketOrderMethod = MsgGrpc.getCancelReplaceMarketOrderMethod) == null) {
          MsgGrpc.getCancelReplaceMarketOrderMethod = getCancelReplaceMarketOrderMethod =
              io.grpc.MethodDescriptor.<em.market.v1.Tx.MsgCancelReplaceMarketOrder, em.market.v1.Tx.MsgCancelReplaceMarketOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelReplaceMarketOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.market.v1.Tx.MsgCancelReplaceMarketOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.market.v1.Tx.MsgCancelReplaceMarketOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelReplaceMarketOrder"))
              .build();
        }
      }
    }
    return getCancelReplaceMarketOrderMethod;
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
    public void addLimitOrder(em.market.v1.Tx.MsgAddLimitOrder request,
        io.grpc.stub.StreamObserver<em.market.v1.Tx.MsgAddLimitOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddLimitOrderMethod(), responseObserver);
    }

    /**
     */
    public void addMarketOrder(em.market.v1.Tx.MsgAddMarketOrder request,
        io.grpc.stub.StreamObserver<em.market.v1.Tx.MsgAddMarketOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddMarketOrderMethod(), responseObserver);
    }

    /**
     */
    public void cancelOrder(em.market.v1.Tx.MsgCancelOrder request,
        io.grpc.stub.StreamObserver<em.market.v1.Tx.MsgCancelOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelOrderMethod(), responseObserver);
    }

    /**
     */
    public void cancelReplaceLimitOrder(em.market.v1.Tx.MsgCancelReplaceLimitOrder request,
        io.grpc.stub.StreamObserver<em.market.v1.Tx.MsgCancelReplaceLimitOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelReplaceLimitOrderMethod(), responseObserver);
    }

    /**
     */
    public void cancelReplaceMarketOrder(em.market.v1.Tx.MsgCancelReplaceMarketOrder request,
        io.grpc.stub.StreamObserver<em.market.v1.Tx.MsgCancelReplaceMarketOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelReplaceMarketOrderMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddLimitOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.market.v1.Tx.MsgAddLimitOrder,
                em.market.v1.Tx.MsgAddLimitOrderResponse>(
                  this, METHODID_ADD_LIMIT_ORDER)))
          .addMethod(
            getAddMarketOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.market.v1.Tx.MsgAddMarketOrder,
                em.market.v1.Tx.MsgAddMarketOrderResponse>(
                  this, METHODID_ADD_MARKET_ORDER)))
          .addMethod(
            getCancelOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.market.v1.Tx.MsgCancelOrder,
                em.market.v1.Tx.MsgCancelOrderResponse>(
                  this, METHODID_CANCEL_ORDER)))
          .addMethod(
            getCancelReplaceLimitOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.market.v1.Tx.MsgCancelReplaceLimitOrder,
                em.market.v1.Tx.MsgCancelReplaceLimitOrderResponse>(
                  this, METHODID_CANCEL_REPLACE_LIMIT_ORDER)))
          .addMethod(
            getCancelReplaceMarketOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.market.v1.Tx.MsgCancelReplaceMarketOrder,
                em.market.v1.Tx.MsgCancelReplaceMarketOrderResponse>(
                  this, METHODID_CANCEL_REPLACE_MARKET_ORDER)))
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
    public void addLimitOrder(em.market.v1.Tx.MsgAddLimitOrder request,
        io.grpc.stub.StreamObserver<em.market.v1.Tx.MsgAddLimitOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddLimitOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addMarketOrder(em.market.v1.Tx.MsgAddMarketOrder request,
        io.grpc.stub.StreamObserver<em.market.v1.Tx.MsgAddMarketOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddMarketOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void cancelOrder(em.market.v1.Tx.MsgCancelOrder request,
        io.grpc.stub.StreamObserver<em.market.v1.Tx.MsgCancelOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void cancelReplaceLimitOrder(em.market.v1.Tx.MsgCancelReplaceLimitOrder request,
        io.grpc.stub.StreamObserver<em.market.v1.Tx.MsgCancelReplaceLimitOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelReplaceLimitOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void cancelReplaceMarketOrder(em.market.v1.Tx.MsgCancelReplaceMarketOrder request,
        io.grpc.stub.StreamObserver<em.market.v1.Tx.MsgCancelReplaceMarketOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelReplaceMarketOrderMethod(), getCallOptions()), request, responseObserver);
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
    public em.market.v1.Tx.MsgAddLimitOrderResponse addLimitOrder(em.market.v1.Tx.MsgAddLimitOrder request) {
      return blockingUnaryCall(
          getChannel(), getAddLimitOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public em.market.v1.Tx.MsgAddMarketOrderResponse addMarketOrder(em.market.v1.Tx.MsgAddMarketOrder request) {
      return blockingUnaryCall(
          getChannel(), getAddMarketOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public em.market.v1.Tx.MsgCancelOrderResponse cancelOrder(em.market.v1.Tx.MsgCancelOrder request) {
      return blockingUnaryCall(
          getChannel(), getCancelOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public em.market.v1.Tx.MsgCancelReplaceLimitOrderResponse cancelReplaceLimitOrder(em.market.v1.Tx.MsgCancelReplaceLimitOrder request) {
      return blockingUnaryCall(
          getChannel(), getCancelReplaceLimitOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public em.market.v1.Tx.MsgCancelReplaceMarketOrderResponse cancelReplaceMarketOrder(em.market.v1.Tx.MsgCancelReplaceMarketOrder request) {
      return blockingUnaryCall(
          getChannel(), getCancelReplaceMarketOrderMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<em.market.v1.Tx.MsgAddLimitOrderResponse> addLimitOrder(
        em.market.v1.Tx.MsgAddLimitOrder request) {
      return futureUnaryCall(
          getChannel().newCall(getAddLimitOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<em.market.v1.Tx.MsgAddMarketOrderResponse> addMarketOrder(
        em.market.v1.Tx.MsgAddMarketOrder request) {
      return futureUnaryCall(
          getChannel().newCall(getAddMarketOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<em.market.v1.Tx.MsgCancelOrderResponse> cancelOrder(
        em.market.v1.Tx.MsgCancelOrder request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<em.market.v1.Tx.MsgCancelReplaceLimitOrderResponse> cancelReplaceLimitOrder(
        em.market.v1.Tx.MsgCancelReplaceLimitOrder request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelReplaceLimitOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<em.market.v1.Tx.MsgCancelReplaceMarketOrderResponse> cancelReplaceMarketOrder(
        em.market.v1.Tx.MsgCancelReplaceMarketOrder request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelReplaceMarketOrderMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_LIMIT_ORDER = 0;
  private static final int METHODID_ADD_MARKET_ORDER = 1;
  private static final int METHODID_CANCEL_ORDER = 2;
  private static final int METHODID_CANCEL_REPLACE_LIMIT_ORDER = 3;
  private static final int METHODID_CANCEL_REPLACE_MARKET_ORDER = 4;

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
        case METHODID_ADD_LIMIT_ORDER:
          serviceImpl.addLimitOrder((em.market.v1.Tx.MsgAddLimitOrder) request,
              (io.grpc.stub.StreamObserver<em.market.v1.Tx.MsgAddLimitOrderResponse>) responseObserver);
          break;
        case METHODID_ADD_MARKET_ORDER:
          serviceImpl.addMarketOrder((em.market.v1.Tx.MsgAddMarketOrder) request,
              (io.grpc.stub.StreamObserver<em.market.v1.Tx.MsgAddMarketOrderResponse>) responseObserver);
          break;
        case METHODID_CANCEL_ORDER:
          serviceImpl.cancelOrder((em.market.v1.Tx.MsgCancelOrder) request,
              (io.grpc.stub.StreamObserver<em.market.v1.Tx.MsgCancelOrderResponse>) responseObserver);
          break;
        case METHODID_CANCEL_REPLACE_LIMIT_ORDER:
          serviceImpl.cancelReplaceLimitOrder((em.market.v1.Tx.MsgCancelReplaceLimitOrder) request,
              (io.grpc.stub.StreamObserver<em.market.v1.Tx.MsgCancelReplaceLimitOrderResponse>) responseObserver);
          break;
        case METHODID_CANCEL_REPLACE_MARKET_ORDER:
          serviceImpl.cancelReplaceMarketOrder((em.market.v1.Tx.MsgCancelReplaceMarketOrder) request,
              (io.grpc.stub.StreamObserver<em.market.v1.Tx.MsgCancelReplaceMarketOrderResponse>) responseObserver);
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
      return em.market.v1.Tx.getDescriptor();
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
              .addMethod(getAddLimitOrderMethod())
              .addMethod(getAddMarketOrderMethod())
              .addMethod(getCancelOrderMethod())
              .addMethod(getCancelReplaceLimitOrderMethod())
              .addMethod(getCancelReplaceMarketOrderMethod())
              .build();
        }
      }
    }
    return result;
  }
}
