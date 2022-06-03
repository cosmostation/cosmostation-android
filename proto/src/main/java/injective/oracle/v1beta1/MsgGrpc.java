package injective.oracle.v1beta1;

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
 * Msg defines the oracle Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: injective/oracle/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "injective.oracle.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPrice,
      injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPriceResponse> getRelayPriceFeedPriceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RelayPriceFeedPrice",
      requestType = injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPrice.class,
      responseType = injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPriceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPrice,
      injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPriceResponse> getRelayPriceFeedPriceMethod() {
    io.grpc.MethodDescriptor<injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPrice, injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPriceResponse> getRelayPriceFeedPriceMethod;
    if ((getRelayPriceFeedPriceMethod = MsgGrpc.getRelayPriceFeedPriceMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRelayPriceFeedPriceMethod = MsgGrpc.getRelayPriceFeedPriceMethod) == null) {
          MsgGrpc.getRelayPriceFeedPriceMethod = getRelayPriceFeedPriceMethod =
              io.grpc.MethodDescriptor.<injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPrice, injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPriceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RelayPriceFeedPrice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPrice.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPriceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RelayPriceFeedPrice"))
              .build();
        }
      }
    }
    return getRelayPriceFeedPriceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.oracle.v1beta1.Tx.MsgRelayBandRates,
      injective.oracle.v1beta1.Tx.MsgRelayBandRatesResponse> getRelayBandRatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RelayBandRates",
      requestType = injective.oracle.v1beta1.Tx.MsgRelayBandRates.class,
      responseType = injective.oracle.v1beta1.Tx.MsgRelayBandRatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.oracle.v1beta1.Tx.MsgRelayBandRates,
      injective.oracle.v1beta1.Tx.MsgRelayBandRatesResponse> getRelayBandRatesMethod() {
    io.grpc.MethodDescriptor<injective.oracle.v1beta1.Tx.MsgRelayBandRates, injective.oracle.v1beta1.Tx.MsgRelayBandRatesResponse> getRelayBandRatesMethod;
    if ((getRelayBandRatesMethod = MsgGrpc.getRelayBandRatesMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRelayBandRatesMethod = MsgGrpc.getRelayBandRatesMethod) == null) {
          MsgGrpc.getRelayBandRatesMethod = getRelayBandRatesMethod =
              io.grpc.MethodDescriptor.<injective.oracle.v1beta1.Tx.MsgRelayBandRates, injective.oracle.v1beta1.Tx.MsgRelayBandRatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RelayBandRates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.Tx.MsgRelayBandRates.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.Tx.MsgRelayBandRatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RelayBandRates"))
              .build();
        }
      }
    }
    return getRelayBandRatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.oracle.v1beta1.Tx.MsgRequestBandIBCRates,
      injective.oracle.v1beta1.Tx.MsgRequestBandIBCRatesResponse> getRequestBandIBCRatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestBandIBCRates",
      requestType = injective.oracle.v1beta1.Tx.MsgRequestBandIBCRates.class,
      responseType = injective.oracle.v1beta1.Tx.MsgRequestBandIBCRatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.oracle.v1beta1.Tx.MsgRequestBandIBCRates,
      injective.oracle.v1beta1.Tx.MsgRequestBandIBCRatesResponse> getRequestBandIBCRatesMethod() {
    io.grpc.MethodDescriptor<injective.oracle.v1beta1.Tx.MsgRequestBandIBCRates, injective.oracle.v1beta1.Tx.MsgRequestBandIBCRatesResponse> getRequestBandIBCRatesMethod;
    if ((getRequestBandIBCRatesMethod = MsgGrpc.getRequestBandIBCRatesMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRequestBandIBCRatesMethod = MsgGrpc.getRequestBandIBCRatesMethod) == null) {
          MsgGrpc.getRequestBandIBCRatesMethod = getRequestBandIBCRatesMethod =
              io.grpc.MethodDescriptor.<injective.oracle.v1beta1.Tx.MsgRequestBandIBCRates, injective.oracle.v1beta1.Tx.MsgRequestBandIBCRatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestBandIBCRates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.Tx.MsgRequestBandIBCRates.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.Tx.MsgRequestBandIBCRatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RequestBandIBCRates"))
              .build();
        }
      }
    }
    return getRequestBandIBCRatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessages,
      injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessagesResponse> getRelayCoinbaseMessagesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RelayCoinbaseMessages",
      requestType = injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessages.class,
      responseType = injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessagesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessages,
      injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessagesResponse> getRelayCoinbaseMessagesMethod() {
    io.grpc.MethodDescriptor<injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessages, injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessagesResponse> getRelayCoinbaseMessagesMethod;
    if ((getRelayCoinbaseMessagesMethod = MsgGrpc.getRelayCoinbaseMessagesMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRelayCoinbaseMessagesMethod = MsgGrpc.getRelayCoinbaseMessagesMethod) == null) {
          MsgGrpc.getRelayCoinbaseMessagesMethod = getRelayCoinbaseMessagesMethod =
              io.grpc.MethodDescriptor.<injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessages, injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessagesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RelayCoinbaseMessages"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessages.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessagesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RelayCoinbaseMessages"))
              .build();
        }
      }
    }
    return getRelayCoinbaseMessagesMethod;
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
   * Msg defines the oracle Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * RelayPriceFeedPrice defines a method for relaying a price for a price feeder-based oracle
     * </pre>
     */
    public void relayPriceFeedPrice(injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPrice request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPriceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRelayPriceFeedPriceMethod(), responseObserver);
    }

    /**
     * <pre>
     * RelayBandRates defines a method for relaying rates from Band
     * </pre>
     */
    public void relayBandRates(injective.oracle.v1beta1.Tx.MsgRelayBandRates request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.Tx.MsgRelayBandRatesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRelayBandRatesMethod(), responseObserver);
    }

    /**
     * <pre>
     * RequestBandIBCRates defines a method for fetching rates from Band ibc
     * </pre>
     */
    public void requestBandIBCRates(injective.oracle.v1beta1.Tx.MsgRequestBandIBCRates request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.Tx.MsgRequestBandIBCRatesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestBandIBCRatesMethod(), responseObserver);
    }

    /**
     * <pre>
     * RelayCoinbaseMessages defines a method for relaying price messages from Coinbase API
     * </pre>
     */
    public void relayCoinbaseMessages(injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessages request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessagesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRelayCoinbaseMessagesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRelayPriceFeedPriceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPrice,
                injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPriceResponse>(
                  this, METHODID_RELAY_PRICE_FEED_PRICE)))
          .addMethod(
            getRelayBandRatesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.oracle.v1beta1.Tx.MsgRelayBandRates,
                injective.oracle.v1beta1.Tx.MsgRelayBandRatesResponse>(
                  this, METHODID_RELAY_BAND_RATES)))
          .addMethod(
            getRequestBandIBCRatesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.oracle.v1beta1.Tx.MsgRequestBandIBCRates,
                injective.oracle.v1beta1.Tx.MsgRequestBandIBCRatesResponse>(
                  this, METHODID_REQUEST_BAND_IBCRATES)))
          .addMethod(
            getRelayCoinbaseMessagesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessages,
                injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessagesResponse>(
                  this, METHODID_RELAY_COINBASE_MESSAGES)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the oracle Msg service.
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
     * RelayPriceFeedPrice defines a method for relaying a price for a price feeder-based oracle
     * </pre>
     */
    public void relayPriceFeedPrice(injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPrice request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPriceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRelayPriceFeedPriceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RelayBandRates defines a method for relaying rates from Band
     * </pre>
     */
    public void relayBandRates(injective.oracle.v1beta1.Tx.MsgRelayBandRates request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.Tx.MsgRelayBandRatesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRelayBandRatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RequestBandIBCRates defines a method for fetching rates from Band ibc
     * </pre>
     */
    public void requestBandIBCRates(injective.oracle.v1beta1.Tx.MsgRequestBandIBCRates request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.Tx.MsgRequestBandIBCRatesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestBandIBCRatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RelayCoinbaseMessages defines a method for relaying price messages from Coinbase API
     * </pre>
     */
    public void relayCoinbaseMessages(injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessages request,
        io.grpc.stub.StreamObserver<injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessagesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRelayCoinbaseMessagesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the oracle Msg service.
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
     * RelayPriceFeedPrice defines a method for relaying a price for a price feeder-based oracle
     * </pre>
     */
    public injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPriceResponse relayPriceFeedPrice(injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPrice request) {
      return blockingUnaryCall(
          getChannel(), getRelayPriceFeedPriceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RelayBandRates defines a method for relaying rates from Band
     * </pre>
     */
    public injective.oracle.v1beta1.Tx.MsgRelayBandRatesResponse relayBandRates(injective.oracle.v1beta1.Tx.MsgRelayBandRates request) {
      return blockingUnaryCall(
          getChannel(), getRelayBandRatesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RequestBandIBCRates defines a method for fetching rates from Band ibc
     * </pre>
     */
    public injective.oracle.v1beta1.Tx.MsgRequestBandIBCRatesResponse requestBandIBCRates(injective.oracle.v1beta1.Tx.MsgRequestBandIBCRates request) {
      return blockingUnaryCall(
          getChannel(), getRequestBandIBCRatesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RelayCoinbaseMessages defines a method for relaying price messages from Coinbase API
     * </pre>
     */
    public injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessagesResponse relayCoinbaseMessages(injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessages request) {
      return blockingUnaryCall(
          getChannel(), getRelayCoinbaseMessagesMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the oracle Msg service.
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
     * RelayPriceFeedPrice defines a method for relaying a price for a price feeder-based oracle
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPriceResponse> relayPriceFeedPrice(
        injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPrice request) {
      return futureUnaryCall(
          getChannel().newCall(getRelayPriceFeedPriceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RelayBandRates defines a method for relaying rates from Band
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.oracle.v1beta1.Tx.MsgRelayBandRatesResponse> relayBandRates(
        injective.oracle.v1beta1.Tx.MsgRelayBandRates request) {
      return futureUnaryCall(
          getChannel().newCall(getRelayBandRatesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RequestBandIBCRates defines a method for fetching rates from Band ibc
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.oracle.v1beta1.Tx.MsgRequestBandIBCRatesResponse> requestBandIBCRates(
        injective.oracle.v1beta1.Tx.MsgRequestBandIBCRates request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestBandIBCRatesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RelayCoinbaseMessages defines a method for relaying price messages from Coinbase API
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessagesResponse> relayCoinbaseMessages(
        injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessages request) {
      return futureUnaryCall(
          getChannel().newCall(getRelayCoinbaseMessagesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RELAY_PRICE_FEED_PRICE = 0;
  private static final int METHODID_RELAY_BAND_RATES = 1;
  private static final int METHODID_REQUEST_BAND_IBCRATES = 2;
  private static final int METHODID_RELAY_COINBASE_MESSAGES = 3;

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
        case METHODID_RELAY_PRICE_FEED_PRICE:
          serviceImpl.relayPriceFeedPrice((injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPrice) request,
              (io.grpc.stub.StreamObserver<injective.oracle.v1beta1.Tx.MsgRelayPriceFeedPriceResponse>) responseObserver);
          break;
        case METHODID_RELAY_BAND_RATES:
          serviceImpl.relayBandRates((injective.oracle.v1beta1.Tx.MsgRelayBandRates) request,
              (io.grpc.stub.StreamObserver<injective.oracle.v1beta1.Tx.MsgRelayBandRatesResponse>) responseObserver);
          break;
        case METHODID_REQUEST_BAND_IBCRATES:
          serviceImpl.requestBandIBCRates((injective.oracle.v1beta1.Tx.MsgRequestBandIBCRates) request,
              (io.grpc.stub.StreamObserver<injective.oracle.v1beta1.Tx.MsgRequestBandIBCRatesResponse>) responseObserver);
          break;
        case METHODID_RELAY_COINBASE_MESSAGES:
          serviceImpl.relayCoinbaseMessages((injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessages) request,
              (io.grpc.stub.StreamObserver<injective.oracle.v1beta1.Tx.MsgRelayCoinbaseMessagesResponse>) responseObserver);
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
      return injective.oracle.v1beta1.Tx.getDescriptor();
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
              .addMethod(getRelayPriceFeedPriceMethod())
              .addMethod(getRelayBandRatesMethod())
              .addMethod(getRequestBandIBCRatesMethod())
              .addMethod(getRelayCoinbaseMessagesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
