package crescent.liquidity.v1beta1;

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
 * Msg defines the Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: crescent/liquidity/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "crescent.liquidity.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgCreatePair,
      crescent.liquidity.v1beta1.Tx.MsgCreatePairResponse> getCreatePairMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePair",
      requestType = crescent.liquidity.v1beta1.Tx.MsgCreatePair.class,
      responseType = crescent.liquidity.v1beta1.Tx.MsgCreatePairResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgCreatePair,
      crescent.liquidity.v1beta1.Tx.MsgCreatePairResponse> getCreatePairMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgCreatePair, crescent.liquidity.v1beta1.Tx.MsgCreatePairResponse> getCreatePairMethod;
    if ((getCreatePairMethod = MsgGrpc.getCreatePairMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreatePairMethod = MsgGrpc.getCreatePairMethod) == null) {
          MsgGrpc.getCreatePairMethod = getCreatePairMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.Tx.MsgCreatePair, crescent.liquidity.v1beta1.Tx.MsgCreatePairResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePair"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.Tx.MsgCreatePair.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.Tx.MsgCreatePairResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreatePair"))
              .build();
        }
      }
    }
    return getCreatePairMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgCreatePool,
      crescent.liquidity.v1beta1.Tx.MsgCreatePoolResponse> getCreatePoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePool",
      requestType = crescent.liquidity.v1beta1.Tx.MsgCreatePool.class,
      responseType = crescent.liquidity.v1beta1.Tx.MsgCreatePoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgCreatePool,
      crescent.liquidity.v1beta1.Tx.MsgCreatePoolResponse> getCreatePoolMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgCreatePool, crescent.liquidity.v1beta1.Tx.MsgCreatePoolResponse> getCreatePoolMethod;
    if ((getCreatePoolMethod = MsgGrpc.getCreatePoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreatePoolMethod = MsgGrpc.getCreatePoolMethod) == null) {
          MsgGrpc.getCreatePoolMethod = getCreatePoolMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.Tx.MsgCreatePool, crescent.liquidity.v1beta1.Tx.MsgCreatePoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.Tx.MsgCreatePool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.Tx.MsgCreatePoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreatePool"))
              .build();
        }
      }
    }
    return getCreatePoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgDeposit,
      crescent.liquidity.v1beta1.Tx.MsgDepositResponse> getDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposit",
      requestType = crescent.liquidity.v1beta1.Tx.MsgDeposit.class,
      responseType = crescent.liquidity.v1beta1.Tx.MsgDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgDeposit,
      crescent.liquidity.v1beta1.Tx.MsgDepositResponse> getDepositMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgDeposit, crescent.liquidity.v1beta1.Tx.MsgDepositResponse> getDepositMethod;
    if ((getDepositMethod = MsgGrpc.getDepositMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDepositMethod = MsgGrpc.getDepositMethod) == null) {
          MsgGrpc.getDepositMethod = getDepositMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.Tx.MsgDeposit, crescent.liquidity.v1beta1.Tx.MsgDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.Tx.MsgDeposit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.Tx.MsgDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Deposit"))
              .build();
        }
      }
    }
    return getDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgWithdraw,
      crescent.liquidity.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Withdraw",
      requestType = crescent.liquidity.v1beta1.Tx.MsgWithdraw.class,
      responseType = crescent.liquidity.v1beta1.Tx.MsgWithdrawResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgWithdraw,
      crescent.liquidity.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgWithdraw, crescent.liquidity.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod;
    if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
          MsgGrpc.getWithdrawMethod = getWithdrawMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.Tx.MsgWithdraw, crescent.liquidity.v1beta1.Tx.MsgWithdrawResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Withdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.Tx.MsgWithdraw.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.Tx.MsgWithdrawResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Withdraw"))
              .build();
        }
      }
    }
    return getWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgLimitOrder,
      crescent.liquidity.v1beta1.Tx.MsgLimitOrderResponse> getLimitOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LimitOrder",
      requestType = crescent.liquidity.v1beta1.Tx.MsgLimitOrder.class,
      responseType = crescent.liquidity.v1beta1.Tx.MsgLimitOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgLimitOrder,
      crescent.liquidity.v1beta1.Tx.MsgLimitOrderResponse> getLimitOrderMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgLimitOrder, crescent.liquidity.v1beta1.Tx.MsgLimitOrderResponse> getLimitOrderMethod;
    if ((getLimitOrderMethod = MsgGrpc.getLimitOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getLimitOrderMethod = MsgGrpc.getLimitOrderMethod) == null) {
          MsgGrpc.getLimitOrderMethod = getLimitOrderMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.Tx.MsgLimitOrder, crescent.liquidity.v1beta1.Tx.MsgLimitOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LimitOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.Tx.MsgLimitOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.Tx.MsgLimitOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("LimitOrder"))
              .build();
        }
      }
    }
    return getLimitOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgMarketOrder,
      crescent.liquidity.v1beta1.Tx.MsgMarketOrderResponse> getMarketOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MarketOrder",
      requestType = crescent.liquidity.v1beta1.Tx.MsgMarketOrder.class,
      responseType = crescent.liquidity.v1beta1.Tx.MsgMarketOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgMarketOrder,
      crescent.liquidity.v1beta1.Tx.MsgMarketOrderResponse> getMarketOrderMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgMarketOrder, crescent.liquidity.v1beta1.Tx.MsgMarketOrderResponse> getMarketOrderMethod;
    if ((getMarketOrderMethod = MsgGrpc.getMarketOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMarketOrderMethod = MsgGrpc.getMarketOrderMethod) == null) {
          MsgGrpc.getMarketOrderMethod = getMarketOrderMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.Tx.MsgMarketOrder, crescent.liquidity.v1beta1.Tx.MsgMarketOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MarketOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.Tx.MsgMarketOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.Tx.MsgMarketOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("MarketOrder"))
              .build();
        }
      }
    }
    return getMarketOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgCancelOrder,
      crescent.liquidity.v1beta1.Tx.MsgCancelOrderResponse> getCancelOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelOrder",
      requestType = crescent.liquidity.v1beta1.Tx.MsgCancelOrder.class,
      responseType = crescent.liquidity.v1beta1.Tx.MsgCancelOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgCancelOrder,
      crescent.liquidity.v1beta1.Tx.MsgCancelOrderResponse> getCancelOrderMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgCancelOrder, crescent.liquidity.v1beta1.Tx.MsgCancelOrderResponse> getCancelOrderMethod;
    if ((getCancelOrderMethod = MsgGrpc.getCancelOrderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelOrderMethod = MsgGrpc.getCancelOrderMethod) == null) {
          MsgGrpc.getCancelOrderMethod = getCancelOrderMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.Tx.MsgCancelOrder, crescent.liquidity.v1beta1.Tx.MsgCancelOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.Tx.MsgCancelOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.Tx.MsgCancelOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelOrder"))
              .build();
        }
      }
    }
    return getCancelOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgCancelAllOrders,
      crescent.liquidity.v1beta1.Tx.MsgCancelAllOrdersResponse> getCancelAllOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelAllOrders",
      requestType = crescent.liquidity.v1beta1.Tx.MsgCancelAllOrders.class,
      responseType = crescent.liquidity.v1beta1.Tx.MsgCancelAllOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgCancelAllOrders,
      crescent.liquidity.v1beta1.Tx.MsgCancelAllOrdersResponse> getCancelAllOrdersMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.Tx.MsgCancelAllOrders, crescent.liquidity.v1beta1.Tx.MsgCancelAllOrdersResponse> getCancelAllOrdersMethod;
    if ((getCancelAllOrdersMethod = MsgGrpc.getCancelAllOrdersMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelAllOrdersMethod = MsgGrpc.getCancelAllOrdersMethod) == null) {
          MsgGrpc.getCancelAllOrdersMethod = getCancelAllOrdersMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.Tx.MsgCancelAllOrders, crescent.liquidity.v1beta1.Tx.MsgCancelAllOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelAllOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.Tx.MsgCancelAllOrders.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.Tx.MsgCancelAllOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelAllOrders"))
              .build();
        }
      }
    }
    return getCancelAllOrdersMethod;
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
   * Msg defines the Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreatePair defines a method for creating a pair
     * </pre>
     */
    public void createPair(crescent.liquidity.v1beta1.Tx.MsgCreatePair request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgCreatePairResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreatePairMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreatePool defines a method for creating a pool
     * </pre>
     */
    public void createPool(crescent.liquidity.v1beta1.Tx.MsgCreatePool request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgCreatePoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreatePoolMethod(), responseObserver);
    }

    /**
     * <pre>
     * Deposit defines a method for depositing coins to the pool
     * </pre>
     */
    public void deposit(crescent.liquidity.v1beta1.Tx.MsgDeposit request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgDepositResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositMethod(), responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing pool coin from the pool
     * </pre>
     */
    public void withdraw(crescent.liquidity.v1beta1.Tx.MsgWithdraw request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgWithdrawResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawMethod(), responseObserver);
    }

    /**
     * <pre>
     * LimitOrder defines a method for making a limit order
     * </pre>
     */
    public void limitOrder(crescent.liquidity.v1beta1.Tx.MsgLimitOrder request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgLimitOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLimitOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * MarketOrder defines a method for making a market order
     * </pre>
     */
    public void marketOrder(crescent.liquidity.v1beta1.Tx.MsgMarketOrder request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgMarketOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMarketOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * CancelOrder defines a method for cancelling an order
     * </pre>
     */
    public void cancelOrder(crescent.liquidity.v1beta1.Tx.MsgCancelOrder request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgCancelOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * CancelAllOrders defines a method for cancelling all orders
     * </pre>
     */
    public void cancelAllOrders(crescent.liquidity.v1beta1.Tx.MsgCancelAllOrders request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgCancelAllOrdersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelAllOrdersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreatePairMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.Tx.MsgCreatePair,
                crescent.liquidity.v1beta1.Tx.MsgCreatePairResponse>(
                  this, METHODID_CREATE_PAIR)))
          .addMethod(
            getCreatePoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.Tx.MsgCreatePool,
                crescent.liquidity.v1beta1.Tx.MsgCreatePoolResponse>(
                  this, METHODID_CREATE_POOL)))
          .addMethod(
            getDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.Tx.MsgDeposit,
                crescent.liquidity.v1beta1.Tx.MsgDepositResponse>(
                  this, METHODID_DEPOSIT)))
          .addMethod(
            getWithdrawMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.Tx.MsgWithdraw,
                crescent.liquidity.v1beta1.Tx.MsgWithdrawResponse>(
                  this, METHODID_WITHDRAW)))
          .addMethod(
            getLimitOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.Tx.MsgLimitOrder,
                crescent.liquidity.v1beta1.Tx.MsgLimitOrderResponse>(
                  this, METHODID_LIMIT_ORDER)))
          .addMethod(
            getMarketOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.Tx.MsgMarketOrder,
                crescent.liquidity.v1beta1.Tx.MsgMarketOrderResponse>(
                  this, METHODID_MARKET_ORDER)))
          .addMethod(
            getCancelOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.Tx.MsgCancelOrder,
                crescent.liquidity.v1beta1.Tx.MsgCancelOrderResponse>(
                  this, METHODID_CANCEL_ORDER)))
          .addMethod(
            getCancelAllOrdersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.Tx.MsgCancelAllOrders,
                crescent.liquidity.v1beta1.Tx.MsgCancelAllOrdersResponse>(
                  this, METHODID_CANCEL_ALL_ORDERS)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
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
     * <pre>
     * CreatePair defines a method for creating a pair
     * </pre>
     */
    public void createPair(crescent.liquidity.v1beta1.Tx.MsgCreatePair request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgCreatePairResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreatePairMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreatePool defines a method for creating a pool
     * </pre>
     */
    public void createPool(crescent.liquidity.v1beta1.Tx.MsgCreatePool request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgCreatePoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreatePoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Deposit defines a method for depositing coins to the pool
     * </pre>
     */
    public void deposit(crescent.liquidity.v1beta1.Tx.MsgDeposit request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgDepositResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing pool coin from the pool
     * </pre>
     */
    public void withdraw(crescent.liquidity.v1beta1.Tx.MsgWithdraw request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgWithdrawResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * LimitOrder defines a method for making a limit order
     * </pre>
     */
    public void limitOrder(crescent.liquidity.v1beta1.Tx.MsgLimitOrder request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgLimitOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLimitOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MarketOrder defines a method for making a market order
     * </pre>
     */
    public void marketOrder(crescent.liquidity.v1beta1.Tx.MsgMarketOrder request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgMarketOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMarketOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CancelOrder defines a method for cancelling an order
     * </pre>
     */
    public void cancelOrder(crescent.liquidity.v1beta1.Tx.MsgCancelOrder request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgCancelOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CancelAllOrders defines a method for cancelling all orders
     * </pre>
     */
    public void cancelAllOrders(crescent.liquidity.v1beta1.Tx.MsgCancelAllOrders request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgCancelAllOrdersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelAllOrdersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
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
     * <pre>
     * CreatePair defines a method for creating a pair
     * </pre>
     */
    public crescent.liquidity.v1beta1.Tx.MsgCreatePairResponse createPair(crescent.liquidity.v1beta1.Tx.MsgCreatePair request) {
      return blockingUnaryCall(
          getChannel(), getCreatePairMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreatePool defines a method for creating a pool
     * </pre>
     */
    public crescent.liquidity.v1beta1.Tx.MsgCreatePoolResponse createPool(crescent.liquidity.v1beta1.Tx.MsgCreatePool request) {
      return blockingUnaryCall(
          getChannel(), getCreatePoolMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Deposit defines a method for depositing coins to the pool
     * </pre>
     */
    public crescent.liquidity.v1beta1.Tx.MsgDepositResponse deposit(crescent.liquidity.v1beta1.Tx.MsgDeposit request) {
      return blockingUnaryCall(
          getChannel(), getDepositMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing pool coin from the pool
     * </pre>
     */
    public crescent.liquidity.v1beta1.Tx.MsgWithdrawResponse withdraw(crescent.liquidity.v1beta1.Tx.MsgWithdraw request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * LimitOrder defines a method for making a limit order
     * </pre>
     */
    public crescent.liquidity.v1beta1.Tx.MsgLimitOrderResponse limitOrder(crescent.liquidity.v1beta1.Tx.MsgLimitOrder request) {
      return blockingUnaryCall(
          getChannel(), getLimitOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MarketOrder defines a method for making a market order
     * </pre>
     */
    public crescent.liquidity.v1beta1.Tx.MsgMarketOrderResponse marketOrder(crescent.liquidity.v1beta1.Tx.MsgMarketOrder request) {
      return blockingUnaryCall(
          getChannel(), getMarketOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CancelOrder defines a method for cancelling an order
     * </pre>
     */
    public crescent.liquidity.v1beta1.Tx.MsgCancelOrderResponse cancelOrder(crescent.liquidity.v1beta1.Tx.MsgCancelOrder request) {
      return blockingUnaryCall(
          getChannel(), getCancelOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CancelAllOrders defines a method for cancelling all orders
     * </pre>
     */
    public crescent.liquidity.v1beta1.Tx.MsgCancelAllOrdersResponse cancelAllOrders(crescent.liquidity.v1beta1.Tx.MsgCancelAllOrders request) {
      return blockingUnaryCall(
          getChannel(), getCancelAllOrdersMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
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
     * <pre>
     * CreatePair defines a method for creating a pair
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.Tx.MsgCreatePairResponse> createPair(
        crescent.liquidity.v1beta1.Tx.MsgCreatePair request) {
      return futureUnaryCall(
          getChannel().newCall(getCreatePairMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreatePool defines a method for creating a pool
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.Tx.MsgCreatePoolResponse> createPool(
        crescent.liquidity.v1beta1.Tx.MsgCreatePool request) {
      return futureUnaryCall(
          getChannel().newCall(getCreatePoolMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Deposit defines a method for depositing coins to the pool
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.Tx.MsgDepositResponse> deposit(
        crescent.liquidity.v1beta1.Tx.MsgDeposit request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing pool coin from the pool
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.Tx.MsgWithdrawResponse> withdraw(
        crescent.liquidity.v1beta1.Tx.MsgWithdraw request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * LimitOrder defines a method for making a limit order
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.Tx.MsgLimitOrderResponse> limitOrder(
        crescent.liquidity.v1beta1.Tx.MsgLimitOrder request) {
      return futureUnaryCall(
          getChannel().newCall(getLimitOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MarketOrder defines a method for making a market order
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.Tx.MsgMarketOrderResponse> marketOrder(
        crescent.liquidity.v1beta1.Tx.MsgMarketOrder request) {
      return futureUnaryCall(
          getChannel().newCall(getMarketOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CancelOrder defines a method for cancelling an order
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.Tx.MsgCancelOrderResponse> cancelOrder(
        crescent.liquidity.v1beta1.Tx.MsgCancelOrder request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CancelAllOrders defines a method for cancelling all orders
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.Tx.MsgCancelAllOrdersResponse> cancelAllOrders(
        crescent.liquidity.v1beta1.Tx.MsgCancelAllOrders request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelAllOrdersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_PAIR = 0;
  private static final int METHODID_CREATE_POOL = 1;
  private static final int METHODID_DEPOSIT = 2;
  private static final int METHODID_WITHDRAW = 3;
  private static final int METHODID_LIMIT_ORDER = 4;
  private static final int METHODID_MARKET_ORDER = 5;
  private static final int METHODID_CANCEL_ORDER = 6;
  private static final int METHODID_CANCEL_ALL_ORDERS = 7;

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
        case METHODID_CREATE_PAIR:
          serviceImpl.createPair((crescent.liquidity.v1beta1.Tx.MsgCreatePair) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgCreatePairResponse>) responseObserver);
          break;
        case METHODID_CREATE_POOL:
          serviceImpl.createPool((crescent.liquidity.v1beta1.Tx.MsgCreatePool) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgCreatePoolResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT:
          serviceImpl.deposit((crescent.liquidity.v1beta1.Tx.MsgDeposit) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgDepositResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW:
          serviceImpl.withdraw((crescent.liquidity.v1beta1.Tx.MsgWithdraw) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgWithdrawResponse>) responseObserver);
          break;
        case METHODID_LIMIT_ORDER:
          serviceImpl.limitOrder((crescent.liquidity.v1beta1.Tx.MsgLimitOrder) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgLimitOrderResponse>) responseObserver);
          break;
        case METHODID_MARKET_ORDER:
          serviceImpl.marketOrder((crescent.liquidity.v1beta1.Tx.MsgMarketOrder) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgMarketOrderResponse>) responseObserver);
          break;
        case METHODID_CANCEL_ORDER:
          serviceImpl.cancelOrder((crescent.liquidity.v1beta1.Tx.MsgCancelOrder) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgCancelOrderResponse>) responseObserver);
          break;
        case METHODID_CANCEL_ALL_ORDERS:
          serviceImpl.cancelAllOrders((crescent.liquidity.v1beta1.Tx.MsgCancelAllOrders) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.Tx.MsgCancelAllOrdersResponse>) responseObserver);
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
      return crescent.liquidity.v1beta1.Tx.getDescriptor();
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
              .addMethod(getCreatePairMethod())
              .addMethod(getCreatePoolMethod())
              .addMethod(getDepositMethod())
              .addMethod(getWithdrawMethod())
              .addMethod(getLimitOrderMethod())
              .addMethod(getMarketOrderMethod())
              .addMethod(getCancelOrderMethod())
              .addMethod(getCancelAllOrdersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
