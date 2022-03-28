package akash.market.v1beta2;

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
 * Query defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: akash/market/v1beta2/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "akash.market.v1beta2.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryOrdersRequest,
      akash.market.v1beta2.QueryOuterClass.QueryOrdersResponse> getOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Orders",
      requestType = akash.market.v1beta2.QueryOuterClass.QueryOrdersRequest.class,
      responseType = akash.market.v1beta2.QueryOuterClass.QueryOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryOrdersRequest,
      akash.market.v1beta2.QueryOuterClass.QueryOrdersResponse> getOrdersMethod() {
    io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryOrdersRequest, akash.market.v1beta2.QueryOuterClass.QueryOrdersResponse> getOrdersMethod;
    if ((getOrdersMethod = QueryGrpc.getOrdersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOrdersMethod = QueryGrpc.getOrdersMethod) == null) {
          QueryGrpc.getOrdersMethod = getOrdersMethod =
              io.grpc.MethodDescriptor.<akash.market.v1beta2.QueryOuterClass.QueryOrdersRequest, akash.market.v1beta2.QueryOuterClass.QueryOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Orders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.QueryOuterClass.QueryOrdersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.QueryOuterClass.QueryOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Orders"))
              .build();
        }
      }
    }
    return getOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryOrderRequest,
      akash.market.v1beta2.QueryOuterClass.QueryOrderResponse> getOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Order",
      requestType = akash.market.v1beta2.QueryOuterClass.QueryOrderRequest.class,
      responseType = akash.market.v1beta2.QueryOuterClass.QueryOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryOrderRequest,
      akash.market.v1beta2.QueryOuterClass.QueryOrderResponse> getOrderMethod() {
    io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryOrderRequest, akash.market.v1beta2.QueryOuterClass.QueryOrderResponse> getOrderMethod;
    if ((getOrderMethod = QueryGrpc.getOrderMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOrderMethod = QueryGrpc.getOrderMethod) == null) {
          QueryGrpc.getOrderMethod = getOrderMethod =
              io.grpc.MethodDescriptor.<akash.market.v1beta2.QueryOuterClass.QueryOrderRequest, akash.market.v1beta2.QueryOuterClass.QueryOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Order"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.QueryOuterClass.QueryOrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.QueryOuterClass.QueryOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Order"))
              .build();
        }
      }
    }
    return getOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryBidsRequest,
      akash.market.v1beta2.QueryOuterClass.QueryBidsResponse> getBidsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Bids",
      requestType = akash.market.v1beta2.QueryOuterClass.QueryBidsRequest.class,
      responseType = akash.market.v1beta2.QueryOuterClass.QueryBidsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryBidsRequest,
      akash.market.v1beta2.QueryOuterClass.QueryBidsResponse> getBidsMethod() {
    io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryBidsRequest, akash.market.v1beta2.QueryOuterClass.QueryBidsResponse> getBidsMethod;
    if ((getBidsMethod = QueryGrpc.getBidsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBidsMethod = QueryGrpc.getBidsMethod) == null) {
          QueryGrpc.getBidsMethod = getBidsMethod =
              io.grpc.MethodDescriptor.<akash.market.v1beta2.QueryOuterClass.QueryBidsRequest, akash.market.v1beta2.QueryOuterClass.QueryBidsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Bids"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.QueryOuterClass.QueryBidsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.QueryOuterClass.QueryBidsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Bids"))
              .build();
        }
      }
    }
    return getBidsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryBidRequest,
      akash.market.v1beta2.QueryOuterClass.QueryBidResponse> getBidMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Bid",
      requestType = akash.market.v1beta2.QueryOuterClass.QueryBidRequest.class,
      responseType = akash.market.v1beta2.QueryOuterClass.QueryBidResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryBidRequest,
      akash.market.v1beta2.QueryOuterClass.QueryBidResponse> getBidMethod() {
    io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryBidRequest, akash.market.v1beta2.QueryOuterClass.QueryBidResponse> getBidMethod;
    if ((getBidMethod = QueryGrpc.getBidMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBidMethod = QueryGrpc.getBidMethod) == null) {
          QueryGrpc.getBidMethod = getBidMethod =
              io.grpc.MethodDescriptor.<akash.market.v1beta2.QueryOuterClass.QueryBidRequest, akash.market.v1beta2.QueryOuterClass.QueryBidResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Bid"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.QueryOuterClass.QueryBidRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.QueryOuterClass.QueryBidResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Bid"))
              .build();
        }
      }
    }
    return getBidMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryLeasesRequest,
      akash.market.v1beta2.QueryOuterClass.QueryLeasesResponse> getLeasesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Leases",
      requestType = akash.market.v1beta2.QueryOuterClass.QueryLeasesRequest.class,
      responseType = akash.market.v1beta2.QueryOuterClass.QueryLeasesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryLeasesRequest,
      akash.market.v1beta2.QueryOuterClass.QueryLeasesResponse> getLeasesMethod() {
    io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryLeasesRequest, akash.market.v1beta2.QueryOuterClass.QueryLeasesResponse> getLeasesMethod;
    if ((getLeasesMethod = QueryGrpc.getLeasesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLeasesMethod = QueryGrpc.getLeasesMethod) == null) {
          QueryGrpc.getLeasesMethod = getLeasesMethod =
              io.grpc.MethodDescriptor.<akash.market.v1beta2.QueryOuterClass.QueryLeasesRequest, akash.market.v1beta2.QueryOuterClass.QueryLeasesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Leases"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.QueryOuterClass.QueryLeasesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.QueryOuterClass.QueryLeasesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Leases"))
              .build();
        }
      }
    }
    return getLeasesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryLeaseRequest,
      akash.market.v1beta2.QueryOuterClass.QueryLeaseResponse> getLeaseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Lease",
      requestType = akash.market.v1beta2.QueryOuterClass.QueryLeaseRequest.class,
      responseType = akash.market.v1beta2.QueryOuterClass.QueryLeaseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryLeaseRequest,
      akash.market.v1beta2.QueryOuterClass.QueryLeaseResponse> getLeaseMethod() {
    io.grpc.MethodDescriptor<akash.market.v1beta2.QueryOuterClass.QueryLeaseRequest, akash.market.v1beta2.QueryOuterClass.QueryLeaseResponse> getLeaseMethod;
    if ((getLeaseMethod = QueryGrpc.getLeaseMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLeaseMethod = QueryGrpc.getLeaseMethod) == null) {
          QueryGrpc.getLeaseMethod = getLeaseMethod =
              io.grpc.MethodDescriptor.<akash.market.v1beta2.QueryOuterClass.QueryLeaseRequest, akash.market.v1beta2.QueryOuterClass.QueryLeaseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Lease"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.QueryOuterClass.QueryLeaseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.QueryOuterClass.QueryLeaseResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Lease"))
              .build();
        }
      }
    }
    return getLeaseMethod;
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
   * Query defines the gRPC querier service
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Orders queries orders with filters
     * </pre>
     */
    public void orders(akash.market.v1beta2.QueryOuterClass.QueryOrdersRequest request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryOrdersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * Order queries order details
     * </pre>
     */
    public void order(akash.market.v1beta2.QueryOuterClass.QueryOrderRequest request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * Bids queries bids with filters
     * </pre>
     */
    public void bids(akash.market.v1beta2.QueryOuterClass.QueryBidsRequest request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryBidsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBidsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Bid queries bid details
     * </pre>
     */
    public void bid(akash.market.v1beta2.QueryOuterClass.QueryBidRequest request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryBidResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBidMethod(), responseObserver);
    }

    /**
     * <pre>
     * Leases queries leases with filters
     * </pre>
     */
    public void leases(akash.market.v1beta2.QueryOuterClass.QueryLeasesRequest request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryLeasesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLeasesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Lease queries lease details
     * </pre>
     */
    public void lease(akash.market.v1beta2.QueryOuterClass.QueryLeaseRequest request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryLeaseResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLeaseMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getOrdersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.market.v1beta2.QueryOuterClass.QueryOrdersRequest,
                akash.market.v1beta2.QueryOuterClass.QueryOrdersResponse>(
                  this, METHODID_ORDERS)))
          .addMethod(
            getOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.market.v1beta2.QueryOuterClass.QueryOrderRequest,
                akash.market.v1beta2.QueryOuterClass.QueryOrderResponse>(
                  this, METHODID_ORDER)))
          .addMethod(
            getBidsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.market.v1beta2.QueryOuterClass.QueryBidsRequest,
                akash.market.v1beta2.QueryOuterClass.QueryBidsResponse>(
                  this, METHODID_BIDS)))
          .addMethod(
            getBidMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.market.v1beta2.QueryOuterClass.QueryBidRequest,
                akash.market.v1beta2.QueryOuterClass.QueryBidResponse>(
                  this, METHODID_BID)))
          .addMethod(
            getLeasesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.market.v1beta2.QueryOuterClass.QueryLeasesRequest,
                akash.market.v1beta2.QueryOuterClass.QueryLeasesResponse>(
                  this, METHODID_LEASES)))
          .addMethod(
            getLeaseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.market.v1beta2.QueryOuterClass.QueryLeaseRequest,
                akash.market.v1beta2.QueryOuterClass.QueryLeaseResponse>(
                  this, METHODID_LEASE)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     * Orders queries orders with filters
     * </pre>
     */
    public void orders(akash.market.v1beta2.QueryOuterClass.QueryOrdersRequest request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryOrdersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Order queries order details
     * </pre>
     */
    public void order(akash.market.v1beta2.QueryOuterClass.QueryOrderRequest request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Bids queries bids with filters
     * </pre>
     */
    public void bids(akash.market.v1beta2.QueryOuterClass.QueryBidsRequest request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryBidsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBidsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Bid queries bid details
     * </pre>
     */
    public void bid(akash.market.v1beta2.QueryOuterClass.QueryBidRequest request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryBidResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBidMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Leases queries leases with filters
     * </pre>
     */
    public void leases(akash.market.v1beta2.QueryOuterClass.QueryLeasesRequest request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryLeasesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLeasesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Lease queries lease details
     * </pre>
     */
    public void lease(akash.market.v1beta2.QueryOuterClass.QueryLeaseRequest request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryLeaseResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLeaseMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     * Orders queries orders with filters
     * </pre>
     */
    public akash.market.v1beta2.QueryOuterClass.QueryOrdersResponse orders(akash.market.v1beta2.QueryOuterClass.QueryOrdersRequest request) {
      return blockingUnaryCall(
          getChannel(), getOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Order queries order details
     * </pre>
     */
    public akash.market.v1beta2.QueryOuterClass.QueryOrderResponse order(akash.market.v1beta2.QueryOuterClass.QueryOrderRequest request) {
      return blockingUnaryCall(
          getChannel(), getOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Bids queries bids with filters
     * </pre>
     */
    public akash.market.v1beta2.QueryOuterClass.QueryBidsResponse bids(akash.market.v1beta2.QueryOuterClass.QueryBidsRequest request) {
      return blockingUnaryCall(
          getChannel(), getBidsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Bid queries bid details
     * </pre>
     */
    public akash.market.v1beta2.QueryOuterClass.QueryBidResponse bid(akash.market.v1beta2.QueryOuterClass.QueryBidRequest request) {
      return blockingUnaryCall(
          getChannel(), getBidMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Leases queries leases with filters
     * </pre>
     */
    public akash.market.v1beta2.QueryOuterClass.QueryLeasesResponse leases(akash.market.v1beta2.QueryOuterClass.QueryLeasesRequest request) {
      return blockingUnaryCall(
          getChannel(), getLeasesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Lease queries lease details
     * </pre>
     */
    public akash.market.v1beta2.QueryOuterClass.QueryLeaseResponse lease(akash.market.v1beta2.QueryOuterClass.QueryLeaseRequest request) {
      return blockingUnaryCall(
          getChannel(), getLeaseMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     * Orders queries orders with filters
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.market.v1beta2.QueryOuterClass.QueryOrdersResponse> orders(
        akash.market.v1beta2.QueryOuterClass.QueryOrdersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Order queries order details
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.market.v1beta2.QueryOuterClass.QueryOrderResponse> order(
        akash.market.v1beta2.QueryOuterClass.QueryOrderRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Bids queries bids with filters
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.market.v1beta2.QueryOuterClass.QueryBidsResponse> bids(
        akash.market.v1beta2.QueryOuterClass.QueryBidsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBidsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Bid queries bid details
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.market.v1beta2.QueryOuterClass.QueryBidResponse> bid(
        akash.market.v1beta2.QueryOuterClass.QueryBidRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBidMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Leases queries leases with filters
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.market.v1beta2.QueryOuterClass.QueryLeasesResponse> leases(
        akash.market.v1beta2.QueryOuterClass.QueryLeasesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLeasesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Lease queries lease details
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.market.v1beta2.QueryOuterClass.QueryLeaseResponse> lease(
        akash.market.v1beta2.QueryOuterClass.QueryLeaseRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLeaseMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ORDERS = 0;
  private static final int METHODID_ORDER = 1;
  private static final int METHODID_BIDS = 2;
  private static final int METHODID_BID = 3;
  private static final int METHODID_LEASES = 4;
  private static final int METHODID_LEASE = 5;

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
        case METHODID_ORDERS:
          serviceImpl.orders((akash.market.v1beta2.QueryOuterClass.QueryOrdersRequest) request,
              (io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryOrdersResponse>) responseObserver);
          break;
        case METHODID_ORDER:
          serviceImpl.order((akash.market.v1beta2.QueryOuterClass.QueryOrderRequest) request,
              (io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryOrderResponse>) responseObserver);
          break;
        case METHODID_BIDS:
          serviceImpl.bids((akash.market.v1beta2.QueryOuterClass.QueryBidsRequest) request,
              (io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryBidsResponse>) responseObserver);
          break;
        case METHODID_BID:
          serviceImpl.bid((akash.market.v1beta2.QueryOuterClass.QueryBidRequest) request,
              (io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryBidResponse>) responseObserver);
          break;
        case METHODID_LEASES:
          serviceImpl.leases((akash.market.v1beta2.QueryOuterClass.QueryLeasesRequest) request,
              (io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryLeasesResponse>) responseObserver);
          break;
        case METHODID_LEASE:
          serviceImpl.lease((akash.market.v1beta2.QueryOuterClass.QueryLeaseRequest) request,
              (io.grpc.stub.StreamObserver<akash.market.v1beta2.QueryOuterClass.QueryLeaseResponse>) responseObserver);
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
      return akash.market.v1beta2.QueryOuterClass.getDescriptor();
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
              .addMethod(getOrdersMethod())
              .addMethod(getOrderMethod())
              .addMethod(getBidsMethod())
              .addMethod(getBidMethod())
              .addMethod(getLeasesMethod())
              .addMethod(getLeaseMethod())
              .build();
        }
      }
    }
    return result;
  }
}
