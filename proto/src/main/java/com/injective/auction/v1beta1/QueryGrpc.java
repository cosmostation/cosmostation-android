package com.injective.auction.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: injective/auction/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "injective.auction.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsRequest,
      com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsResponse> getAuctionParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AuctionParams",
      requestType = com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsRequest.class,
      responseType = com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsRequest,
      com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsResponse> getAuctionParamsMethod() {
    io.grpc.MethodDescriptor<com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsRequest, com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsResponse> getAuctionParamsMethod;
    if ((getAuctionParamsMethod = QueryGrpc.getAuctionParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAuctionParamsMethod = QueryGrpc.getAuctionParamsMethod) == null) {
          QueryGrpc.getAuctionParamsMethod = getAuctionParamsMethod =
              io.grpc.MethodDescriptor.<com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsRequest, com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AuctionParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AuctionParams"))
              .build();
        }
      }
    }
    return getAuctionParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketRequest,
      com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketResponse> getCurrentAuctionBasketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CurrentAuctionBasket",
      requestType = com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketRequest.class,
      responseType = com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketRequest,
      com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketResponse> getCurrentAuctionBasketMethod() {
    io.grpc.MethodDescriptor<com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketRequest, com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketResponse> getCurrentAuctionBasketMethod;
    if ((getCurrentAuctionBasketMethod = QueryGrpc.getCurrentAuctionBasketMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCurrentAuctionBasketMethod = QueryGrpc.getCurrentAuctionBasketMethod) == null) {
          QueryGrpc.getCurrentAuctionBasketMethod = getCurrentAuctionBasketMethod =
              io.grpc.MethodDescriptor.<com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketRequest, com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CurrentAuctionBasket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CurrentAuctionBasket"))
              .build();
        }
      }
    }
    return getCurrentAuctionBasketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.auction.v1beta1.QueryProto.QueryModuleStateRequest,
      com.injective.auction.v1beta1.QueryProto.QueryModuleStateResponse> getAuctionModuleStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AuctionModuleState",
      requestType = com.injective.auction.v1beta1.QueryProto.QueryModuleStateRequest.class,
      responseType = com.injective.auction.v1beta1.QueryProto.QueryModuleStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.auction.v1beta1.QueryProto.QueryModuleStateRequest,
      com.injective.auction.v1beta1.QueryProto.QueryModuleStateResponse> getAuctionModuleStateMethod() {
    io.grpc.MethodDescriptor<com.injective.auction.v1beta1.QueryProto.QueryModuleStateRequest, com.injective.auction.v1beta1.QueryProto.QueryModuleStateResponse> getAuctionModuleStateMethod;
    if ((getAuctionModuleStateMethod = QueryGrpc.getAuctionModuleStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAuctionModuleStateMethod = QueryGrpc.getAuctionModuleStateMethod) == null) {
          QueryGrpc.getAuctionModuleStateMethod = getAuctionModuleStateMethod =
              io.grpc.MethodDescriptor.<com.injective.auction.v1beta1.QueryProto.QueryModuleStateRequest, com.injective.auction.v1beta1.QueryProto.QueryModuleStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AuctionModuleState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.auction.v1beta1.QueryProto.QueryModuleStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.auction.v1beta1.QueryProto.QueryModuleStateResponse.getDefaultInstance()))
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
  public interface AsyncService {

    /**
     * <pre>
     * Retrieves auction params
     * </pre>
     */
    default void auctionParams(com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsRequest request,
        io.grpc.stub.StreamObserver<com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAuctionParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves current auction basket with current highest bid and bidder
     * </pre>
     */
    default void currentAuctionBasket(com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketRequest request,
        io.grpc.stub.StreamObserver<com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCurrentAuctionBasketMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire auction module's state
     * </pre>
     */
    default void auctionModuleState(com.injective.auction.v1beta1.QueryProto.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<com.injective.auction.v1beta1.QueryProto.QueryModuleStateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAuctionModuleStateMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service.
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
   * Query defines the gRPC querier service.
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
     * Retrieves auction params
     * </pre>
     */
    public void auctionParams(com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsRequest request,
        io.grpc.stub.StreamObserver<com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAuctionParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves current auction basket with current highest bid and bidder
     * </pre>
     */
    public void currentAuctionBasket(com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketRequest request,
        io.grpc.stub.StreamObserver<com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCurrentAuctionBasketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire auction module's state
     * </pre>
     */
    public void auctionModuleState(com.injective.auction.v1beta1.QueryProto.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<com.injective.auction.v1beta1.QueryProto.QueryModuleStateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAuctionModuleStateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service.
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
     * Retrieves auction params
     * </pre>
     */
    public com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsResponse auctionParams(com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAuctionParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves current auction basket with current highest bid and bidder
     * </pre>
     */
    public com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketResponse currentAuctionBasket(com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCurrentAuctionBasketMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the entire auction module's state
     * </pre>
     */
    public com.injective.auction.v1beta1.QueryProto.QueryModuleStateResponse auctionModuleState(com.injective.auction.v1beta1.QueryProto.QueryModuleStateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAuctionModuleStateMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service.
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
     * Retrieves auction params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsResponse> auctionParams(
        com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAuctionParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves current auction basket with current highest bid and bidder
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketResponse> currentAuctionBasket(
        com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCurrentAuctionBasketMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the entire auction module's state
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.auction.v1beta1.QueryProto.QueryModuleStateResponse> auctionModuleState(
        com.injective.auction.v1beta1.QueryProto.QueryModuleStateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
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
        case METHODID_AUCTION_PARAMS:
          serviceImpl.auctionParams((com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsResponse>) responseObserver);
          break;
        case METHODID_CURRENT_AUCTION_BASKET:
          serviceImpl.currentAuctionBasket((com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketResponse>) responseObserver);
          break;
        case METHODID_AUCTION_MODULE_STATE:
          serviceImpl.auctionModuleState((com.injective.auction.v1beta1.QueryProto.QueryModuleStateRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.auction.v1beta1.QueryProto.QueryModuleStateResponse>) responseObserver);
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
          getAuctionParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsRequest,
              com.injective.auction.v1beta1.QueryProto.QueryAuctionParamsResponse>(
                service, METHODID_AUCTION_PARAMS)))
        .addMethod(
          getCurrentAuctionBasketMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketRequest,
              com.injective.auction.v1beta1.QueryProto.QueryCurrentAuctionBasketResponse>(
                service, METHODID_CURRENT_AUCTION_BASKET)))
        .addMethod(
          getAuctionModuleStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.auction.v1beta1.QueryProto.QueryModuleStateRequest,
              com.injective.auction.v1beta1.QueryProto.QueryModuleStateResponse>(
                service, METHODID_AUCTION_MODULE_STATE)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.injective.auction.v1beta1.QueryProto.getDescriptor();
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
