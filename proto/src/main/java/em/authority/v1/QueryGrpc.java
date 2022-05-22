package em.authority.v1;

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
    comments = "Source: em/authority/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "em.authority.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<em.authority.v1.QueryOuterClass.QueryGasPricesRequest,
      em.authority.v1.QueryOuterClass.QueryGasPricesResponse> getGasPricesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GasPrices",
      requestType = em.authority.v1.QueryOuterClass.QueryGasPricesRequest.class,
      responseType = em.authority.v1.QueryOuterClass.QueryGasPricesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.authority.v1.QueryOuterClass.QueryGasPricesRequest,
      em.authority.v1.QueryOuterClass.QueryGasPricesResponse> getGasPricesMethod() {
    io.grpc.MethodDescriptor<em.authority.v1.QueryOuterClass.QueryGasPricesRequest, em.authority.v1.QueryOuterClass.QueryGasPricesResponse> getGasPricesMethod;
    if ((getGasPricesMethod = QueryGrpc.getGasPricesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGasPricesMethod = QueryGrpc.getGasPricesMethod) == null) {
          QueryGrpc.getGasPricesMethod = getGasPricesMethod =
              io.grpc.MethodDescriptor.<em.authority.v1.QueryOuterClass.QueryGasPricesRequest, em.authority.v1.QueryOuterClass.QueryGasPricesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GasPrices"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.authority.v1.QueryOuterClass.QueryGasPricesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.authority.v1.QueryOuterClass.QueryGasPricesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GasPrices"))
              .build();
        }
      }
    }
    return getGasPricesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<em.authority.v1.QueryOuterClass.QueryUpgradePlanRequest,
      em.authority.v1.QueryOuterClass.QueryUpgradePlanResponse> getUpgradePlanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpgradePlan",
      requestType = em.authority.v1.QueryOuterClass.QueryUpgradePlanRequest.class,
      responseType = em.authority.v1.QueryOuterClass.QueryUpgradePlanResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.authority.v1.QueryOuterClass.QueryUpgradePlanRequest,
      em.authority.v1.QueryOuterClass.QueryUpgradePlanResponse> getUpgradePlanMethod() {
    io.grpc.MethodDescriptor<em.authority.v1.QueryOuterClass.QueryUpgradePlanRequest, em.authority.v1.QueryOuterClass.QueryUpgradePlanResponse> getUpgradePlanMethod;
    if ((getUpgradePlanMethod = QueryGrpc.getUpgradePlanMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUpgradePlanMethod = QueryGrpc.getUpgradePlanMethod) == null) {
          QueryGrpc.getUpgradePlanMethod = getUpgradePlanMethod =
              io.grpc.MethodDescriptor.<em.authority.v1.QueryOuterClass.QueryUpgradePlanRequest, em.authority.v1.QueryOuterClass.QueryUpgradePlanResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpgradePlan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.authority.v1.QueryOuterClass.QueryUpgradePlanRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.authority.v1.QueryOuterClass.QueryUpgradePlanResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UpgradePlan"))
              .build();
        }
      }
    }
    return getUpgradePlanMethod;
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
     */
    public void gasPrices(em.authority.v1.QueryOuterClass.QueryGasPricesRequest request,
        io.grpc.stub.StreamObserver<em.authority.v1.QueryOuterClass.QueryGasPricesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGasPricesMethod(), responseObserver);
    }

    /**
     */
    public void upgradePlan(em.authority.v1.QueryOuterClass.QueryUpgradePlanRequest request,
        io.grpc.stub.StreamObserver<em.authority.v1.QueryOuterClass.QueryUpgradePlanResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpgradePlanMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGasPricesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.authority.v1.QueryOuterClass.QueryGasPricesRequest,
                em.authority.v1.QueryOuterClass.QueryGasPricesResponse>(
                  this, METHODID_GAS_PRICES)))
          .addMethod(
            getUpgradePlanMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.authority.v1.QueryOuterClass.QueryUpgradePlanRequest,
                em.authority.v1.QueryOuterClass.QueryUpgradePlanResponse>(
                  this, METHODID_UPGRADE_PLAN)))
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
     */
    public void gasPrices(em.authority.v1.QueryOuterClass.QueryGasPricesRequest request,
        io.grpc.stub.StreamObserver<em.authority.v1.QueryOuterClass.QueryGasPricesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGasPricesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void upgradePlan(em.authority.v1.QueryOuterClass.QueryUpgradePlanRequest request,
        io.grpc.stub.StreamObserver<em.authority.v1.QueryOuterClass.QueryUpgradePlanResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpgradePlanMethod(), getCallOptions()), request, responseObserver);
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
     */
    public em.authority.v1.QueryOuterClass.QueryGasPricesResponse gasPrices(em.authority.v1.QueryOuterClass.QueryGasPricesRequest request) {
      return blockingUnaryCall(
          getChannel(), getGasPricesMethod(), getCallOptions(), request);
    }

    /**
     */
    public em.authority.v1.QueryOuterClass.QueryUpgradePlanResponse upgradePlan(em.authority.v1.QueryOuterClass.QueryUpgradePlanRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpgradePlanMethod(), getCallOptions(), request);
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
     */
    public com.google.common.util.concurrent.ListenableFuture<em.authority.v1.QueryOuterClass.QueryGasPricesResponse> gasPrices(
        em.authority.v1.QueryOuterClass.QueryGasPricesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGasPricesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<em.authority.v1.QueryOuterClass.QueryUpgradePlanResponse> upgradePlan(
        em.authority.v1.QueryOuterClass.QueryUpgradePlanRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpgradePlanMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GAS_PRICES = 0;
  private static final int METHODID_UPGRADE_PLAN = 1;

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
        case METHODID_GAS_PRICES:
          serviceImpl.gasPrices((em.authority.v1.QueryOuterClass.QueryGasPricesRequest) request,
              (io.grpc.stub.StreamObserver<em.authority.v1.QueryOuterClass.QueryGasPricesResponse>) responseObserver);
          break;
        case METHODID_UPGRADE_PLAN:
          serviceImpl.upgradePlan((em.authority.v1.QueryOuterClass.QueryUpgradePlanRequest) request,
              (io.grpc.stub.StreamObserver<em.authority.v1.QueryOuterClass.QueryUpgradePlanResponse>) responseObserver);
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
      return em.authority.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getGasPricesMethod())
              .addMethod(getUpgradePlanMethod())
              .build();
        }
      }
    }
    return result;
  }
}
