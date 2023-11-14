package com.kava.auction.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service for auction module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: kava/auction/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "kava.auction.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kava.auction.v1beta1.QueryProto.QueryParamsRequest,
      com.kava.auction.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.kava.auction.v1beta1.QueryProto.QueryParamsRequest.class,
      responseType = com.kava.auction.v1beta1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.auction.v1beta1.QueryProto.QueryParamsRequest,
      com.kava.auction.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.kava.auction.v1beta1.QueryProto.QueryParamsRequest, com.kava.auction.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.kava.auction.v1beta1.QueryProto.QueryParamsRequest, com.kava.auction.v1beta1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.auction.v1beta1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.auction.v1beta1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.auction.v1beta1.QueryProto.QueryAuctionRequest,
      com.kava.auction.v1beta1.QueryProto.QueryAuctionResponse> getAuctionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Auction",
      requestType = com.kava.auction.v1beta1.QueryProto.QueryAuctionRequest.class,
      responseType = com.kava.auction.v1beta1.QueryProto.QueryAuctionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.auction.v1beta1.QueryProto.QueryAuctionRequest,
      com.kava.auction.v1beta1.QueryProto.QueryAuctionResponse> getAuctionMethod() {
    io.grpc.MethodDescriptor<com.kava.auction.v1beta1.QueryProto.QueryAuctionRequest, com.kava.auction.v1beta1.QueryProto.QueryAuctionResponse> getAuctionMethod;
    if ((getAuctionMethod = QueryGrpc.getAuctionMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAuctionMethod = QueryGrpc.getAuctionMethod) == null) {
          QueryGrpc.getAuctionMethod = getAuctionMethod =
              io.grpc.MethodDescriptor.<com.kava.auction.v1beta1.QueryProto.QueryAuctionRequest, com.kava.auction.v1beta1.QueryProto.QueryAuctionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Auction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.auction.v1beta1.QueryProto.QueryAuctionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.auction.v1beta1.QueryProto.QueryAuctionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Auction"))
              .build();
        }
      }
    }
    return getAuctionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.auction.v1beta1.QueryProto.QueryAuctionsRequest,
      com.kava.auction.v1beta1.QueryProto.QueryAuctionsResponse> getAuctionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Auctions",
      requestType = com.kava.auction.v1beta1.QueryProto.QueryAuctionsRequest.class,
      responseType = com.kava.auction.v1beta1.QueryProto.QueryAuctionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.auction.v1beta1.QueryProto.QueryAuctionsRequest,
      com.kava.auction.v1beta1.QueryProto.QueryAuctionsResponse> getAuctionsMethod() {
    io.grpc.MethodDescriptor<com.kava.auction.v1beta1.QueryProto.QueryAuctionsRequest, com.kava.auction.v1beta1.QueryProto.QueryAuctionsResponse> getAuctionsMethod;
    if ((getAuctionsMethod = QueryGrpc.getAuctionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAuctionsMethod = QueryGrpc.getAuctionsMethod) == null) {
          QueryGrpc.getAuctionsMethod = getAuctionsMethod =
              io.grpc.MethodDescriptor.<com.kava.auction.v1beta1.QueryProto.QueryAuctionsRequest, com.kava.auction.v1beta1.QueryProto.QueryAuctionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Auctions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.auction.v1beta1.QueryProto.QueryAuctionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.auction.v1beta1.QueryProto.QueryAuctionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Auctions"))
              .build();
        }
      }
    }
    return getAuctionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDRequest,
      com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDResponse> getNextAuctionIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "NextAuctionID",
      requestType = com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDRequest.class,
      responseType = com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDRequest,
      com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDResponse> getNextAuctionIDMethod() {
    io.grpc.MethodDescriptor<com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDRequest, com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDResponse> getNextAuctionIDMethod;
    if ((getNextAuctionIDMethod = QueryGrpc.getNextAuctionIDMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getNextAuctionIDMethod = QueryGrpc.getNextAuctionIDMethod) == null) {
          QueryGrpc.getNextAuctionIDMethod = getNextAuctionIDMethod =
              io.grpc.MethodDescriptor.<com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDRequest, com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NextAuctionID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDResponse.getDefaultInstance()))
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
  public interface AsyncService {

    /**
     * <pre>
     * Params queries all parameters of the auction module.
     * </pre>
     */
    default void params(com.kava.auction.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.kava.auction.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Auction queries an individual Auction by auction ID
     * </pre>
     */
    default void auction(com.kava.auction.v1beta1.QueryProto.QueryAuctionRequest request,
        io.grpc.stub.StreamObserver<com.kava.auction.v1beta1.QueryProto.QueryAuctionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAuctionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Auctions queries auctions filtered by asset denom, owner address, phase, and auction type
     * </pre>
     */
    default void auctions(com.kava.auction.v1beta1.QueryProto.QueryAuctionsRequest request,
        io.grpc.stub.StreamObserver<com.kava.auction.v1beta1.QueryProto.QueryAuctionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAuctionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * NextAuctionID queries the next auction ID
     * </pre>
     */
    default void nextAuctionID(com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDRequest request,
        io.grpc.stub.StreamObserver<com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getNextAuctionIDMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service for auction module
   * </pre>
   */
  public static abstract class QueryImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return QueryGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for auction module
   * </pre>
   */
  public static final class QueryStub
      extends io.grpc.stub.AbstractAsyncStub<QueryStub> {
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
    public void params(com.kava.auction.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.kava.auction.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Auction queries an individual Auction by auction ID
     * </pre>
     */
    public void auction(com.kava.auction.v1beta1.QueryProto.QueryAuctionRequest request,
        io.grpc.stub.StreamObserver<com.kava.auction.v1beta1.QueryProto.QueryAuctionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAuctionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Auctions queries auctions filtered by asset denom, owner address, phase, and auction type
     * </pre>
     */
    public void auctions(com.kava.auction.v1beta1.QueryProto.QueryAuctionsRequest request,
        io.grpc.stub.StreamObserver<com.kava.auction.v1beta1.QueryProto.QueryAuctionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAuctionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * NextAuctionID queries the next auction ID
     * </pre>
     */
    public void nextAuctionID(com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDRequest request,
        io.grpc.stub.StreamObserver<com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getNextAuctionIDMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for auction module
   * </pre>
   */
  public static final class QueryBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<QueryBlockingStub> {
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
    public com.kava.auction.v1beta1.QueryProto.QueryParamsResponse params(com.kava.auction.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Auction queries an individual Auction by auction ID
     * </pre>
     */
    public com.kava.auction.v1beta1.QueryProto.QueryAuctionResponse auction(com.kava.auction.v1beta1.QueryProto.QueryAuctionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAuctionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Auctions queries auctions filtered by asset denom, owner address, phase, and auction type
     * </pre>
     */
    public com.kava.auction.v1beta1.QueryProto.QueryAuctionsResponse auctions(com.kava.auction.v1beta1.QueryProto.QueryAuctionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAuctionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * NextAuctionID queries the next auction ID
     * </pre>
     */
    public com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDResponse nextAuctionID(com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getNextAuctionIDMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for auction module
   * </pre>
   */
  public static final class QueryFutureStub
      extends io.grpc.stub.AbstractFutureStub<QueryFutureStub> {
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
    public com.google.common.util.concurrent.ListenableFuture<com.kava.auction.v1beta1.QueryProto.QueryParamsResponse> params(
        com.kava.auction.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Auction queries an individual Auction by auction ID
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.auction.v1beta1.QueryProto.QueryAuctionResponse> auction(
        com.kava.auction.v1beta1.QueryProto.QueryAuctionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAuctionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Auctions queries auctions filtered by asset denom, owner address, phase, and auction type
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.auction.v1beta1.QueryProto.QueryAuctionsResponse> auctions(
        com.kava.auction.v1beta1.QueryProto.QueryAuctionsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAuctionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * NextAuctionID queries the next auction ID
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDResponse> nextAuctionID(
        com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
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
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PARAMS:
          serviceImpl.params((com.kava.auction.v1beta1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.auction.v1beta1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_AUCTION:
          serviceImpl.auction((com.kava.auction.v1beta1.QueryProto.QueryAuctionRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.auction.v1beta1.QueryProto.QueryAuctionResponse>) responseObserver);
          break;
        case METHODID_AUCTIONS:
          serviceImpl.auctions((com.kava.auction.v1beta1.QueryProto.QueryAuctionsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.auction.v1beta1.QueryProto.QueryAuctionsResponse>) responseObserver);
          break;
        case METHODID_NEXT_AUCTION_ID:
          serviceImpl.nextAuctionID((com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.auction.v1beta1.QueryProto.QueryParamsRequest,
              com.kava.auction.v1beta1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getAuctionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.auction.v1beta1.QueryProto.QueryAuctionRequest,
              com.kava.auction.v1beta1.QueryProto.QueryAuctionResponse>(
                service, METHODID_AUCTION)))
        .addMethod(
          getAuctionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.auction.v1beta1.QueryProto.QueryAuctionsRequest,
              com.kava.auction.v1beta1.QueryProto.QueryAuctionsResponse>(
                service, METHODID_AUCTIONS)))
        .addMethod(
          getNextAuctionIDMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDRequest,
              com.kava.auction.v1beta1.QueryProto.QueryNextAuctionIDResponse>(
                service, METHODID_NEXT_AUCTION_ID)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kava.auction.v1beta1.QueryProto.getDescriptor();
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
