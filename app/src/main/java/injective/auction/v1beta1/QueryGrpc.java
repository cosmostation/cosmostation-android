package injective.auction.v1beta1;

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
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: injective/auction/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "injective.auction.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsRequest,
      injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsResponse> getAuctionParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AuctionParams",
      requestType = injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsRequest.class,
      responseType = injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsRequest,
      injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsResponse> getAuctionParamsMethod() {
    io.grpc.MethodDescriptor<injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsRequest, injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsResponse> getAuctionParamsMethod;
    if ((getAuctionParamsMethod = QueryGrpc.getAuctionParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAuctionParamsMethod = QueryGrpc.getAuctionParamsMethod) == null) {
          QueryGrpc.getAuctionParamsMethod = getAuctionParamsMethod =
              io.grpc.MethodDescriptor.<injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsRequest, injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AuctionParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AuctionParams"))
              .build();
        }
      }
    }
    return getAuctionParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketRequest,
      injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketResponse> getCurrentAuctionBasketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CurrentAuctionBasket",
      requestType = injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketRequest.class,
      responseType = injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketRequest,
      injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketResponse> getCurrentAuctionBasketMethod() {
    io.grpc.MethodDescriptor<injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketRequest, injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketResponse> getCurrentAuctionBasketMethod;
    if ((getCurrentAuctionBasketMethod = QueryGrpc.getCurrentAuctionBasketMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCurrentAuctionBasketMethod = QueryGrpc.getCurrentAuctionBasketMethod) == null) {
          QueryGrpc.getCurrentAuctionBasketMethod = getCurrentAuctionBasketMethod =
              io.grpc.MethodDescriptor.<injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketRequest, injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CurrentAuctionBasket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CurrentAuctionBasket"))
              .build();
        }
      }
    }
    return getCurrentAuctionBasketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.auction.v1beta1.QueryOuterClass.QueryModuleStateRequest,
      injective.auction.v1beta1.QueryOuterClass.QueryModuleStateResponse> getAuctionModuleStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AuctionModuleState",
      requestType = injective.auction.v1beta1.QueryOuterClass.QueryModuleStateRequest.class,
      responseType = injective.auction.v1beta1.QueryOuterClass.QueryModuleStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.auction.v1beta1.QueryOuterClass.QueryModuleStateRequest,
      injective.auction.v1beta1.QueryOuterClass.QueryModuleStateResponse> getAuctionModuleStateMethod() {
    io.grpc.MethodDescriptor<injective.auction.v1beta1.QueryOuterClass.QueryModuleStateRequest, injective.auction.v1beta1.QueryOuterClass.QueryModuleStateResponse> getAuctionModuleStateMethod;
    if ((getAuctionModuleStateMethod = QueryGrpc.getAuctionModuleStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAuctionModuleStateMethod = QueryGrpc.getAuctionModuleStateMethod) == null) {
          QueryGrpc.getAuctionModuleStateMethod = getAuctionModuleStateMethod =
              io.grpc.MethodDescriptor.<injective.auction.v1beta1.QueryOuterClass.QueryModuleStateRequest, injective.auction.v1beta1.QueryOuterClass.QueryModuleStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AuctionModuleState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.auction.v1beta1.QueryOuterClass.QueryModuleStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.auction.v1beta1.QueryOuterClass.QueryModuleStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AuctionModuleState"))
              .build();
        }
      }
    }
    return getAuctionModuleStateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QueryStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryStub>() {
        @java.lang.Override
        public QueryStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryStub(channel, callOptions);
        }
      };
    return QueryStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QueryBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryBlockingStub>() {
        @java.lang.Override
        public QueryBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryBlockingStub(channel, callOptions);
        }
      };
    return QueryBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QueryFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryFutureStub>() {
        @java.lang.Override
        public QueryFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryFutureStub(channel, callOptions);
        }
      };
    return QueryFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Retrieves auction params
     * </pre>
     */
    public void auctionParams(injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsRequest request,
        io.grpc.stub.StreamObserver<injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAuctionParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves current auction basket with current highest bid and bidder
     * </pre>
     */
    public void currentAuctionBasket(injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketRequest request,
        io.grpc.stub.StreamObserver<injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCurrentAuctionBasketMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire auction module's state
     * </pre>
     */
    public void auctionModuleState(injective.auction.v1beta1.QueryOuterClass.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<injective.auction.v1beta1.QueryOuterClass.QueryModuleStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAuctionModuleStateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAuctionParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsRequest,
                injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsResponse>(
                  this, METHODID_AUCTION_PARAMS)))
          .addMethod(
            getCurrentAuctionBasketMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketRequest,
                injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketResponse>(
                  this, METHODID_CURRENT_AUCTION_BASKET)))
          .addMethod(
            getAuctionModuleStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.auction.v1beta1.QueryOuterClass.QueryModuleStateRequest,
                injective.auction.v1beta1.QueryOuterClass.QueryModuleStateResponse>(
                  this, METHODID_AUCTION_MODULE_STATE)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryStub extends io.grpc.stub.AbstractAsyncStub<QueryStub> {
    private QueryStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryStub(channel, callOptions);
    }

    /**
     * <pre>
     * Retrieves auction params
     * </pre>
     */
    public void auctionParams(injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsRequest request,
        io.grpc.stub.StreamObserver<injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAuctionParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves current auction basket with current highest bid and bidder
     * </pre>
     */
    public void currentAuctionBasket(injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketRequest request,
        io.grpc.stub.StreamObserver<injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCurrentAuctionBasketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire auction module's state
     * </pre>
     */
    public void auctionModuleState(injective.auction.v1beta1.QueryOuterClass.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<injective.auction.v1beta1.QueryOuterClass.QueryModuleStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAuctionModuleStateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryBlockingStub extends io.grpc.stub.AbstractBlockingStub<QueryBlockingStub> {
    private QueryBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Retrieves auction params
     * </pre>
     */
    public injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsResponse auctionParams(injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAuctionParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves current auction basket with current highest bid and bidder
     * </pre>
     */
    public injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketResponse currentAuctionBasket(injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketRequest request) {
      return blockingUnaryCall(
          getChannel(), getCurrentAuctionBasketMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the entire auction module's state
     * </pre>
     */
    public injective.auction.v1beta1.QueryOuterClass.QueryModuleStateResponse auctionModuleState(injective.auction.v1beta1.QueryOuterClass.QueryModuleStateRequest request) {
      return blockingUnaryCall(
          getChannel(), getAuctionModuleStateMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryFutureStub extends io.grpc.stub.AbstractFutureStub<QueryFutureStub> {
    private QueryFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Retrieves auction params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsResponse> auctionParams(
        injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAuctionParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves current auction basket with current highest bid and bidder
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketResponse> currentAuctionBasket(
        injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCurrentAuctionBasketMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the entire auction module's state
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.auction.v1beta1.QueryOuterClass.QueryModuleStateResponse> auctionModuleState(
        injective.auction.v1beta1.QueryOuterClass.QueryModuleStateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAuctionModuleStateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_AUCTION_PARAMS = 0;
  private static final int METHODID_CURRENT_AUCTION_BASKET = 1;
  private static final int METHODID_AUCTION_MODULE_STATE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final QueryImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(QueryImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AUCTION_PARAMS:
          serviceImpl.auctionParams((injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsRequest) request,
              (io.grpc.stub.StreamObserver<injective.auction.v1beta1.QueryOuterClass.QueryAuctionParamsResponse>) responseObserver);
          break;
        case METHODID_CURRENT_AUCTION_BASKET:
          serviceImpl.currentAuctionBasket((injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketRequest) request,
              (io.grpc.stub.StreamObserver<injective.auction.v1beta1.QueryOuterClass.QueryCurrentAuctionBasketResponse>) responseObserver);
          break;
        case METHODID_AUCTION_MODULE_STATE:
          serviceImpl.auctionModuleState((injective.auction.v1beta1.QueryOuterClass.QueryModuleStateRequest) request,
              (io.grpc.stub.StreamObserver<injective.auction.v1beta1.QueryOuterClass.QueryModuleStateResponse>) responseObserver);
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

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return injective.auction.v1beta1.QueryOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Query");
    }
  }

  private static final class QueryFileDescriptorSupplier
      extends QueryBaseDescriptorSupplier {
    QueryFileDescriptorSupplier() {}
  }

  private static final class QueryMethodDescriptorSupplier
      extends QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    QueryMethodDescriptorSupplier(String methodName) {
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
      synchronized (QueryGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QueryFileDescriptorSupplier())
              .addMethod(getAuctionParamsMethod())
              .addMethod(getCurrentAuctionBasketMethod())
              .addMethod(getAuctionModuleStateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
