package kava.auction.v1beta1;

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
 * Query defines the gRPC querier service for auction module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/auction/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "kava.auction.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.auction.v1beta1.QueryOuterClass.QueryParamsRequest,
      kava.auction.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = kava.auction.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = kava.auction.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.auction.v1beta1.QueryOuterClass.QueryParamsRequest,
      kava.auction.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<kava.auction.v1beta1.QueryOuterClass.QueryParamsRequest, kava.auction.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<kava.auction.v1beta1.QueryOuterClass.QueryParamsRequest, kava.auction.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.auction.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.auction.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.auction.v1beta1.QueryOuterClass.QueryAuctionRequest,
      kava.auction.v1beta1.QueryOuterClass.QueryAuctionResponse> getAuctionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Auction",
      requestType = kava.auction.v1beta1.QueryOuterClass.QueryAuctionRequest.class,
      responseType = kava.auction.v1beta1.QueryOuterClass.QueryAuctionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.auction.v1beta1.QueryOuterClass.QueryAuctionRequest,
      kava.auction.v1beta1.QueryOuterClass.QueryAuctionResponse> getAuctionMethod() {
    io.grpc.MethodDescriptor<kava.auction.v1beta1.QueryOuterClass.QueryAuctionRequest, kava.auction.v1beta1.QueryOuterClass.QueryAuctionResponse> getAuctionMethod;
    if ((getAuctionMethod = QueryGrpc.getAuctionMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAuctionMethod = QueryGrpc.getAuctionMethod) == null) {
          QueryGrpc.getAuctionMethod = getAuctionMethod =
              io.grpc.MethodDescriptor.<kava.auction.v1beta1.QueryOuterClass.QueryAuctionRequest, kava.auction.v1beta1.QueryOuterClass.QueryAuctionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Auction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.auction.v1beta1.QueryOuterClass.QueryAuctionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.auction.v1beta1.QueryOuterClass.QueryAuctionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Auction"))
              .build();
        }
      }
    }
    return getAuctionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.auction.v1beta1.QueryOuterClass.QueryAuctionsRequest,
      kava.auction.v1beta1.QueryOuterClass.QueryAuctionsResponse> getAuctionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Auctions",
      requestType = kava.auction.v1beta1.QueryOuterClass.QueryAuctionsRequest.class,
      responseType = kava.auction.v1beta1.QueryOuterClass.QueryAuctionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.auction.v1beta1.QueryOuterClass.QueryAuctionsRequest,
      kava.auction.v1beta1.QueryOuterClass.QueryAuctionsResponse> getAuctionsMethod() {
    io.grpc.MethodDescriptor<kava.auction.v1beta1.QueryOuterClass.QueryAuctionsRequest, kava.auction.v1beta1.QueryOuterClass.QueryAuctionsResponse> getAuctionsMethod;
    if ((getAuctionsMethod = QueryGrpc.getAuctionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAuctionsMethod = QueryGrpc.getAuctionsMethod) == null) {
          QueryGrpc.getAuctionsMethod = getAuctionsMethod =
              io.grpc.MethodDescriptor.<kava.auction.v1beta1.QueryOuterClass.QueryAuctionsRequest, kava.auction.v1beta1.QueryOuterClass.QueryAuctionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Auctions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.auction.v1beta1.QueryOuterClass.QueryAuctionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.auction.v1beta1.QueryOuterClass.QueryAuctionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Auctions"))
              .build();
        }
      }
    }
    return getAuctionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDRequest,
      kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDResponse> getNextAuctionIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "NextAuctionID",
      requestType = kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDRequest.class,
      responseType = kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDRequest,
      kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDResponse> getNextAuctionIDMethod() {
    io.grpc.MethodDescriptor<kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDRequest, kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDResponse> getNextAuctionIDMethod;
    if ((getNextAuctionIDMethod = QueryGrpc.getNextAuctionIDMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getNextAuctionIDMethod = QueryGrpc.getNextAuctionIDMethod) == null) {
          QueryGrpc.getNextAuctionIDMethod = getNextAuctionIDMethod =
              io.grpc.MethodDescriptor.<kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDRequest, kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NextAuctionID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("NextAuctionID"))
              .build();
        }
      }
    }
    return getNextAuctionIDMethod;
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
   * Query defines the gRPC querier service for auction module
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Params queries all parameters of the auction module.
     * </pre>
     */
    public void params(kava.auction.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<kava.auction.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Auction queries an individual Auction by auction ID
     * </pre>
     */
    public void auction(kava.auction.v1beta1.QueryOuterClass.QueryAuctionRequest request,
        io.grpc.stub.StreamObserver<kava.auction.v1beta1.QueryOuterClass.QueryAuctionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAuctionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Auctions queries auctions filtered by asset denom, owner address, phase, and auction type
     * </pre>
     */
    public void auctions(kava.auction.v1beta1.QueryOuterClass.QueryAuctionsRequest request,
        io.grpc.stub.StreamObserver<kava.auction.v1beta1.QueryOuterClass.QueryAuctionsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAuctionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * NextAuctionID queries the next auction ID
     * </pre>
     */
    public void nextAuctionID(kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDRequest request,
        io.grpc.stub.StreamObserver<kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNextAuctionIDMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.auction.v1beta1.QueryOuterClass.QueryParamsRequest,
                kava.auction.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getAuctionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.auction.v1beta1.QueryOuterClass.QueryAuctionRequest,
                kava.auction.v1beta1.QueryOuterClass.QueryAuctionResponse>(
                  this, METHODID_AUCTION)))
          .addMethod(
            getAuctionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.auction.v1beta1.QueryOuterClass.QueryAuctionsRequest,
                kava.auction.v1beta1.QueryOuterClass.QueryAuctionsResponse>(
                  this, METHODID_AUCTIONS)))
          .addMethod(
            getNextAuctionIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDRequest,
                kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDResponse>(
                  this, METHODID_NEXT_AUCTION_ID)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for auction module
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
     * Params queries all parameters of the auction module.
     * </pre>
     */
    public void params(kava.auction.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<kava.auction.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Auction queries an individual Auction by auction ID
     * </pre>
     */
    public void auction(kava.auction.v1beta1.QueryOuterClass.QueryAuctionRequest request,
        io.grpc.stub.StreamObserver<kava.auction.v1beta1.QueryOuterClass.QueryAuctionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAuctionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Auctions queries auctions filtered by asset denom, owner address, phase, and auction type
     * </pre>
     */
    public void auctions(kava.auction.v1beta1.QueryOuterClass.QueryAuctionsRequest request,
        io.grpc.stub.StreamObserver<kava.auction.v1beta1.QueryOuterClass.QueryAuctionsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAuctionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * NextAuctionID queries the next auction ID
     * </pre>
     */
    public void nextAuctionID(kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDRequest request,
        io.grpc.stub.StreamObserver<kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNextAuctionIDMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for auction module
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
     * Params queries all parameters of the auction module.
     * </pre>
     */
    public kava.auction.v1beta1.QueryOuterClass.QueryParamsResponse params(kava.auction.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Auction queries an individual Auction by auction ID
     * </pre>
     */
    public kava.auction.v1beta1.QueryOuterClass.QueryAuctionResponse auction(kava.auction.v1beta1.QueryOuterClass.QueryAuctionRequest request) {
      return blockingUnaryCall(
          getChannel(), getAuctionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Auctions queries auctions filtered by asset denom, owner address, phase, and auction type
     * </pre>
     */
    public kava.auction.v1beta1.QueryOuterClass.QueryAuctionsResponse auctions(kava.auction.v1beta1.QueryOuterClass.QueryAuctionsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAuctionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * NextAuctionID queries the next auction ID
     * </pre>
     */
    public kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDResponse nextAuctionID(kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDRequest request) {
      return blockingUnaryCall(
          getChannel(), getNextAuctionIDMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for auction module
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
     * Params queries all parameters of the auction module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.auction.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        kava.auction.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Auction queries an individual Auction by auction ID
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.auction.v1beta1.QueryOuterClass.QueryAuctionResponse> auction(
        kava.auction.v1beta1.QueryOuterClass.QueryAuctionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAuctionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Auctions queries auctions filtered by asset denom, owner address, phase, and auction type
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.auction.v1beta1.QueryOuterClass.QueryAuctionsResponse> auctions(
        kava.auction.v1beta1.QueryOuterClass.QueryAuctionsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAuctionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * NextAuctionID queries the next auction ID
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDResponse> nextAuctionID(
        kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNextAuctionIDMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_AUCTION = 1;
  private static final int METHODID_AUCTIONS = 2;
  private static final int METHODID_NEXT_AUCTION_ID = 3;

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
        case METHODID_PARAMS:
          serviceImpl.params((kava.auction.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<kava.auction.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_AUCTION:
          serviceImpl.auction((kava.auction.v1beta1.QueryOuterClass.QueryAuctionRequest) request,
              (io.grpc.stub.StreamObserver<kava.auction.v1beta1.QueryOuterClass.QueryAuctionResponse>) responseObserver);
          break;
        case METHODID_AUCTIONS:
          serviceImpl.auctions((kava.auction.v1beta1.QueryOuterClass.QueryAuctionsRequest) request,
              (io.grpc.stub.StreamObserver<kava.auction.v1beta1.QueryOuterClass.QueryAuctionsResponse>) responseObserver);
          break;
        case METHODID_NEXT_AUCTION_ID:
          serviceImpl.nextAuctionID((kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDRequest) request,
              (io.grpc.stub.StreamObserver<kava.auction.v1beta1.QueryOuterClass.QueryNextAuctionIDResponse>) responseObserver);
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
      return kava.auction.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getParamsMethod())
              .addMethod(getAuctionMethod())
              .addMethod(getAuctionsMethod())
              .addMethod(getNextAuctionIDMethod())
              .build();
        }
      }
    }
    return result;
  }
}
