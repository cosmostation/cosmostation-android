package em.buyback.v1;

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
    comments = "Source: em/buyback/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "em.buyback.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<em.buyback.v1.QueryOuterClass.QueryBalanceRequest,
      em.buyback.v1.QueryOuterClass.QueryBalanceResponse> getBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Balance",
      requestType = em.buyback.v1.QueryOuterClass.QueryBalanceRequest.class,
      responseType = em.buyback.v1.QueryOuterClass.QueryBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.buyback.v1.QueryOuterClass.QueryBalanceRequest,
      em.buyback.v1.QueryOuterClass.QueryBalanceResponse> getBalanceMethod() {
    io.grpc.MethodDescriptor<em.buyback.v1.QueryOuterClass.QueryBalanceRequest, em.buyback.v1.QueryOuterClass.QueryBalanceResponse> getBalanceMethod;
    if ((getBalanceMethod = QueryGrpc.getBalanceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBalanceMethod = QueryGrpc.getBalanceMethod) == null) {
          QueryGrpc.getBalanceMethod = getBalanceMethod =
              io.grpc.MethodDescriptor.<em.buyback.v1.QueryOuterClass.QueryBalanceRequest, em.buyback.v1.QueryOuterClass.QueryBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Balance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.buyback.v1.QueryOuterClass.QueryBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.buyback.v1.QueryOuterClass.QueryBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Balance"))
              .build();
        }
      }
    }
    return getBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<em.buyback.v1.QueryOuterClass.QueryBuybackTimeRequest,
      em.buyback.v1.QueryOuterClass.QueryBuybackTimeResponse> getBuybackTimeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BuybackTime",
      requestType = em.buyback.v1.QueryOuterClass.QueryBuybackTimeRequest.class,
      responseType = em.buyback.v1.QueryOuterClass.QueryBuybackTimeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.buyback.v1.QueryOuterClass.QueryBuybackTimeRequest,
      em.buyback.v1.QueryOuterClass.QueryBuybackTimeResponse> getBuybackTimeMethod() {
    io.grpc.MethodDescriptor<em.buyback.v1.QueryOuterClass.QueryBuybackTimeRequest, em.buyback.v1.QueryOuterClass.QueryBuybackTimeResponse> getBuybackTimeMethod;
    if ((getBuybackTimeMethod = QueryGrpc.getBuybackTimeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBuybackTimeMethod = QueryGrpc.getBuybackTimeMethod) == null) {
          QueryGrpc.getBuybackTimeMethod = getBuybackTimeMethod =
              io.grpc.MethodDescriptor.<em.buyback.v1.QueryOuterClass.QueryBuybackTimeRequest, em.buyback.v1.QueryOuterClass.QueryBuybackTimeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BuybackTime"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.buyback.v1.QueryOuterClass.QueryBuybackTimeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.buyback.v1.QueryOuterClass.QueryBuybackTimeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BuybackTime"))
              .build();
        }
      }
    }
    return getBuybackTimeMethod;
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
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Query for the current buyback balance
     * </pre>
     */
    public void balance(em.buyback.v1.QueryOuterClass.QueryBalanceRequest request,
        io.grpc.stub.StreamObserver<em.buyback.v1.QueryOuterClass.QueryBalanceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBalanceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Query for buyback time periods
     * </pre>
     */
    public void buybackTime(em.buyback.v1.QueryOuterClass.QueryBuybackTimeRequest request,
        io.grpc.stub.StreamObserver<em.buyback.v1.QueryOuterClass.QueryBuybackTimeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBuybackTimeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.buyback.v1.QueryOuterClass.QueryBalanceRequest,
                em.buyback.v1.QueryOuterClass.QueryBalanceResponse>(
                  this, METHODID_BALANCE)))
          .addMethod(
            getBuybackTimeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.buyback.v1.QueryOuterClass.QueryBuybackTimeRequest,
                em.buyback.v1.QueryOuterClass.QueryBuybackTimeResponse>(
                  this, METHODID_BUYBACK_TIME)))
          .build();
    }
  }

  /**
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
     * Query for the current buyback balance
     * </pre>
     */
    public void balance(em.buyback.v1.QueryOuterClass.QueryBalanceRequest request,
        io.grpc.stub.StreamObserver<em.buyback.v1.QueryOuterClass.QueryBalanceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Query for buyback time periods
     * </pre>
     */
    public void buybackTime(em.buyback.v1.QueryOuterClass.QueryBuybackTimeRequest request,
        io.grpc.stub.StreamObserver<em.buyback.v1.QueryOuterClass.QueryBuybackTimeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBuybackTimeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
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
     * Query for the current buyback balance
     * </pre>
     */
    public em.buyback.v1.QueryOuterClass.QueryBalanceResponse balance(em.buyback.v1.QueryOuterClass.QueryBalanceRequest request) {
      return blockingUnaryCall(
          getChannel(), getBalanceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Query for buyback time periods
     * </pre>
     */
    public em.buyback.v1.QueryOuterClass.QueryBuybackTimeResponse buybackTime(em.buyback.v1.QueryOuterClass.QueryBuybackTimeRequest request) {
      return blockingUnaryCall(
          getChannel(), getBuybackTimeMethod(), getCallOptions(), request);
    }
  }

  /**
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
     * Query for the current buyback balance
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<em.buyback.v1.QueryOuterClass.QueryBalanceResponse> balance(
        em.buyback.v1.QueryOuterClass.QueryBalanceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Query for buyback time periods
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<em.buyback.v1.QueryOuterClass.QueryBuybackTimeResponse> buybackTime(
        em.buyback.v1.QueryOuterClass.QueryBuybackTimeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBuybackTimeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BALANCE = 0;
  private static final int METHODID_BUYBACK_TIME = 1;

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
        case METHODID_BALANCE:
          serviceImpl.balance((em.buyback.v1.QueryOuterClass.QueryBalanceRequest) request,
              (io.grpc.stub.StreamObserver<em.buyback.v1.QueryOuterClass.QueryBalanceResponse>) responseObserver);
          break;
        case METHODID_BUYBACK_TIME:
          serviceImpl.buybackTime((em.buyback.v1.QueryOuterClass.QueryBuybackTimeRequest) request,
              (io.grpc.stub.StreamObserver<em.buyback.v1.QueryOuterClass.QueryBuybackTimeResponse>) responseObserver);
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
      return em.buyback.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getBalanceMethod())
              .addMethod(getBuybackTimeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
