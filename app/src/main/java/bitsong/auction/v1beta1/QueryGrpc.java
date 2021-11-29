package bitsong.auction.v1beta1;

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
 * Query defines the gRPC querier service for Auction Module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: bitsong/auction/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "bitsong.auction.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionRequest,
      bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionResponse> getAuctionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Auction",
      requestType = bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionRequest.class,
      responseType = bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionRequest,
      bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionResponse> getAuctionMethod() {
    io.grpc.MethodDescriptor<bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionRequest, bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionResponse> getAuctionMethod;
    if ((getAuctionMethod = QueryGrpc.getAuctionMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAuctionMethod = QueryGrpc.getAuctionMethod) == null) {
          QueryGrpc.getAuctionMethod = getAuctionMethod =
              io.grpc.MethodDescriptor.<bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionRequest, bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Auction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Auction"))
              .build();
        }
      }
    }
    return getAuctionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsRequest,
      bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsResponse> getAllAuctionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllAuctions",
      requestType = bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsRequest.class,
      responseType = bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsRequest,
      bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsResponse> getAllAuctionsMethod() {
    io.grpc.MethodDescriptor<bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsRequest, bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsResponse> getAllAuctionsMethod;
    if ((getAllAuctionsMethod = QueryGrpc.getAllAuctionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllAuctionsMethod = QueryGrpc.getAllAuctionsMethod) == null) {
          QueryGrpc.getAllAuctionsMethod = getAllAuctionsMethod =
              io.grpc.MethodDescriptor.<bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsRequest, bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllAuctions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllAuctions"))
              .build();
        }
      }
    }
    return getAllAuctionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerRequest,
      bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerResponse> getAuctionsByOwnerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AuctionsByOwner",
      requestType = bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerRequest.class,
      responseType = bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerRequest,
      bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerResponse> getAuctionsByOwnerMethod() {
    io.grpc.MethodDescriptor<bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerRequest, bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerResponse> getAuctionsByOwnerMethod;
    if ((getAuctionsByOwnerMethod = QueryGrpc.getAuctionsByOwnerMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAuctionsByOwnerMethod = QueryGrpc.getAuctionsByOwnerMethod) == null) {
          QueryGrpc.getAuctionsByOwnerMethod = getAuctionsByOwnerMethod =
              io.grpc.MethodDescriptor.<bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerRequest, bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AuctionsByOwner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AuctionsByOwner"))
              .build();
        }
      }
    }
    return getAuctionsByOwnerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.auction.v1beta1.QueryOuterClass.QueryBidsRequest,
      bitsong.auction.v1beta1.QueryOuterClass.QueryBidsResponse> getBidsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Bids",
      requestType = bitsong.auction.v1beta1.QueryOuterClass.QueryBidsRequest.class,
      responseType = bitsong.auction.v1beta1.QueryOuterClass.QueryBidsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.auction.v1beta1.QueryOuterClass.QueryBidsRequest,
      bitsong.auction.v1beta1.QueryOuterClass.QueryBidsResponse> getBidsMethod() {
    io.grpc.MethodDescriptor<bitsong.auction.v1beta1.QueryOuterClass.QueryBidsRequest, bitsong.auction.v1beta1.QueryOuterClass.QueryBidsResponse> getBidsMethod;
    if ((getBidsMethod = QueryGrpc.getBidsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBidsMethod = QueryGrpc.getBidsMethod) == null) {
          QueryGrpc.getBidsMethod = getBidsMethod =
              io.grpc.MethodDescriptor.<bitsong.auction.v1beta1.QueryOuterClass.QueryBidsRequest, bitsong.auction.v1beta1.QueryOuterClass.QueryBidsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Bids"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.QueryOuterClass.QueryBidsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.QueryOuterClass.QueryBidsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Bids"))
              .build();
        }
      }
    }
    return getBidsMethod;
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
   * Query defines the gRPC querier service for Auction Module
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Auction queries the Auction for the given id
     * </pre>
     */
    public void auction(bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionRequest request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAuctionMethod(), responseObserver);
    }

    /**
     * <pre>
     * AllAuctions queries the all Auctions
     * </pre>
     */
    public void allAuctions(bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsRequest request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAllAuctionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * AuctionsByOwner queries the Auctions of the given owner
     * </pre>
     */
    public void auctionsByOwner(bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerRequest request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAuctionsByOwnerMethod(), responseObserver);
    }

    /**
     * <pre>
     * Bids queries the bids of the given auction id
     * </pre>
     */
    public void bids(bitsong.auction.v1beta1.QueryOuterClass.QueryBidsRequest request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.QueryOuterClass.QueryBidsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBidsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAuctionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionRequest,
                bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionResponse>(
                  this, METHODID_AUCTION)))
          .addMethod(
            getAllAuctionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsRequest,
                bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsResponse>(
                  this, METHODID_ALL_AUCTIONS)))
          .addMethod(
            getAuctionsByOwnerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerRequest,
                bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerResponse>(
                  this, METHODID_AUCTIONS_BY_OWNER)))
          .addMethod(
            getBidsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.auction.v1beta1.QueryOuterClass.QueryBidsRequest,
                bitsong.auction.v1beta1.QueryOuterClass.QueryBidsResponse>(
                  this, METHODID_BIDS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for Auction Module
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
     * Auction queries the Auction for the given id
     * </pre>
     */
    public void auction(bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionRequest request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAuctionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AllAuctions queries the all Auctions
     * </pre>
     */
    public void allAuctions(bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsRequest request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAllAuctionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AuctionsByOwner queries the Auctions of the given owner
     * </pre>
     */
    public void auctionsByOwner(bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerRequest request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAuctionsByOwnerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Bids queries the bids of the given auction id
     * </pre>
     */
    public void bids(bitsong.auction.v1beta1.QueryOuterClass.QueryBidsRequest request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.QueryOuterClass.QueryBidsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBidsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for Auction Module
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
     * Auction queries the Auction for the given id
     * </pre>
     */
    public bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionResponse auction(bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionRequest request) {
      return blockingUnaryCall(
          getChannel(), getAuctionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AllAuctions queries the all Auctions
     * </pre>
     */
    public bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsResponse allAuctions(bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAllAuctionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AuctionsByOwner queries the Auctions of the given owner
     * </pre>
     */
    public bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerResponse auctionsByOwner(bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerRequest request) {
      return blockingUnaryCall(
          getChannel(), getAuctionsByOwnerMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Bids queries the bids of the given auction id
     * </pre>
     */
    public bitsong.auction.v1beta1.QueryOuterClass.QueryBidsResponse bids(bitsong.auction.v1beta1.QueryOuterClass.QueryBidsRequest request) {
      return blockingUnaryCall(
          getChannel(), getBidsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for Auction Module
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
     * Auction queries the Auction for the given id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionResponse> auction(
        bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAuctionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AllAuctions queries the all Auctions
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsResponse> allAuctions(
        bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAllAuctionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AuctionsByOwner queries the Auctions of the given owner
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerResponse> auctionsByOwner(
        bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAuctionsByOwnerMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Bids queries the bids of the given auction id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.auction.v1beta1.QueryOuterClass.QueryBidsResponse> bids(
        bitsong.auction.v1beta1.QueryOuterClass.QueryBidsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBidsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_AUCTION = 0;
  private static final int METHODID_ALL_AUCTIONS = 1;
  private static final int METHODID_AUCTIONS_BY_OWNER = 2;
  private static final int METHODID_BIDS = 3;

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
        case METHODID_AUCTION:
          serviceImpl.auction((bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionRequest) request,
              (io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionResponse>) responseObserver);
          break;
        case METHODID_ALL_AUCTIONS:
          serviceImpl.allAuctions((bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsRequest) request,
              (io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.QueryOuterClass.QueryAllAuctionsResponse>) responseObserver);
          break;
        case METHODID_AUCTIONS_BY_OWNER:
          serviceImpl.auctionsByOwner((bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerRequest) request,
              (io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.QueryOuterClass.QueryAuctionsByOwnerResponse>) responseObserver);
          break;
        case METHODID_BIDS:
          serviceImpl.bids((bitsong.auction.v1beta1.QueryOuterClass.QueryBidsRequest) request,
              (io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.QueryOuterClass.QueryBidsResponse>) responseObserver);
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
      return bitsong.auction.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getAuctionMethod())
              .addMethod(getAllAuctionsMethod())
              .addMethod(getAuctionsByOwnerMethod())
              .addMethod(getBidsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
