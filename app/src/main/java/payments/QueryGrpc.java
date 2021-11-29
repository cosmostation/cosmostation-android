package payments;

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
    comments = "Source: payments/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "payments.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<payments.QueryOuterClass.QueryPaymentTemplateRequest,
      payments.QueryOuterClass.QueryPaymentTemplateResponse> getPaymentTemplateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PaymentTemplate",
      requestType = payments.QueryOuterClass.QueryPaymentTemplateRequest.class,
      responseType = payments.QueryOuterClass.QueryPaymentTemplateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<payments.QueryOuterClass.QueryPaymentTemplateRequest,
      payments.QueryOuterClass.QueryPaymentTemplateResponse> getPaymentTemplateMethod() {
    io.grpc.MethodDescriptor<payments.QueryOuterClass.QueryPaymentTemplateRequest, payments.QueryOuterClass.QueryPaymentTemplateResponse> getPaymentTemplateMethod;
    if ((getPaymentTemplateMethod = QueryGrpc.getPaymentTemplateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPaymentTemplateMethod = QueryGrpc.getPaymentTemplateMethod) == null) {
          QueryGrpc.getPaymentTemplateMethod = getPaymentTemplateMethod =
              io.grpc.MethodDescriptor.<payments.QueryOuterClass.QueryPaymentTemplateRequest, payments.QueryOuterClass.QueryPaymentTemplateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PaymentTemplate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  payments.QueryOuterClass.QueryPaymentTemplateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  payments.QueryOuterClass.QueryPaymentTemplateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PaymentTemplate"))
              .build();
        }
      }
    }
    return getPaymentTemplateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<payments.QueryOuterClass.QueryPaymentContractRequest,
      payments.QueryOuterClass.QueryPaymentContractResponse> getPaymentContractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PaymentContract",
      requestType = payments.QueryOuterClass.QueryPaymentContractRequest.class,
      responseType = payments.QueryOuterClass.QueryPaymentContractResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<payments.QueryOuterClass.QueryPaymentContractRequest,
      payments.QueryOuterClass.QueryPaymentContractResponse> getPaymentContractMethod() {
    io.grpc.MethodDescriptor<payments.QueryOuterClass.QueryPaymentContractRequest, payments.QueryOuterClass.QueryPaymentContractResponse> getPaymentContractMethod;
    if ((getPaymentContractMethod = QueryGrpc.getPaymentContractMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPaymentContractMethod = QueryGrpc.getPaymentContractMethod) == null) {
          QueryGrpc.getPaymentContractMethod = getPaymentContractMethod =
              io.grpc.MethodDescriptor.<payments.QueryOuterClass.QueryPaymentContractRequest, payments.QueryOuterClass.QueryPaymentContractResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PaymentContract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  payments.QueryOuterClass.QueryPaymentContractRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  payments.QueryOuterClass.QueryPaymentContractResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PaymentContract"))
              .build();
        }
      }
    }
    return getPaymentContractMethod;
  }

  private static volatile io.grpc.MethodDescriptor<payments.QueryOuterClass.QueryPaymentContractsByIdPrefixRequest,
      payments.QueryOuterClass.QueryPaymentContractsByIdPrefixResponse> getPaymentContractsByIdPrefixMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PaymentContractsByIdPrefix",
      requestType = payments.QueryOuterClass.QueryPaymentContractsByIdPrefixRequest.class,
      responseType = payments.QueryOuterClass.QueryPaymentContractsByIdPrefixResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<payments.QueryOuterClass.QueryPaymentContractsByIdPrefixRequest,
      payments.QueryOuterClass.QueryPaymentContractsByIdPrefixResponse> getPaymentContractsByIdPrefixMethod() {
    io.grpc.MethodDescriptor<payments.QueryOuterClass.QueryPaymentContractsByIdPrefixRequest, payments.QueryOuterClass.QueryPaymentContractsByIdPrefixResponse> getPaymentContractsByIdPrefixMethod;
    if ((getPaymentContractsByIdPrefixMethod = QueryGrpc.getPaymentContractsByIdPrefixMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPaymentContractsByIdPrefixMethod = QueryGrpc.getPaymentContractsByIdPrefixMethod) == null) {
          QueryGrpc.getPaymentContractsByIdPrefixMethod = getPaymentContractsByIdPrefixMethod =
              io.grpc.MethodDescriptor.<payments.QueryOuterClass.QueryPaymentContractsByIdPrefixRequest, payments.QueryOuterClass.QueryPaymentContractsByIdPrefixResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PaymentContractsByIdPrefix"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  payments.QueryOuterClass.QueryPaymentContractsByIdPrefixRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  payments.QueryOuterClass.QueryPaymentContractsByIdPrefixResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PaymentContractsByIdPrefix"))
              .build();
        }
      }
    }
    return getPaymentContractsByIdPrefixMethod;
  }

  private static volatile io.grpc.MethodDescriptor<payments.QueryOuterClass.QuerySubscriptionRequest,
      payments.QueryOuterClass.QuerySubscriptionResponse> getSubscriptionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Subscription",
      requestType = payments.QueryOuterClass.QuerySubscriptionRequest.class,
      responseType = payments.QueryOuterClass.QuerySubscriptionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<payments.QueryOuterClass.QuerySubscriptionRequest,
      payments.QueryOuterClass.QuerySubscriptionResponse> getSubscriptionMethod() {
    io.grpc.MethodDescriptor<payments.QueryOuterClass.QuerySubscriptionRequest, payments.QueryOuterClass.QuerySubscriptionResponse> getSubscriptionMethod;
    if ((getSubscriptionMethod = QueryGrpc.getSubscriptionMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSubscriptionMethod = QueryGrpc.getSubscriptionMethod) == null) {
          QueryGrpc.getSubscriptionMethod = getSubscriptionMethod =
              io.grpc.MethodDescriptor.<payments.QueryOuterClass.QuerySubscriptionRequest, payments.QueryOuterClass.QuerySubscriptionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Subscription"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  payments.QueryOuterClass.QuerySubscriptionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  payments.QueryOuterClass.QuerySubscriptionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Subscription"))
              .build();
        }
      }
    }
    return getSubscriptionMethod;
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
     * PaymentTemplate queries info of a specific payment template.
     * </pre>
     */
    public void paymentTemplate(payments.QueryOuterClass.QueryPaymentTemplateRequest request,
        io.grpc.stub.StreamObserver<payments.QueryOuterClass.QueryPaymentTemplateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPaymentTemplateMethod(), responseObserver);
    }

    /**
     * <pre>
     * PaymentContract queries info of a specific payment contract.
     * </pre>
     */
    public void paymentContract(payments.QueryOuterClass.QueryPaymentContractRequest request,
        io.grpc.stub.StreamObserver<payments.QueryOuterClass.QueryPaymentContractResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPaymentContractMethod(), responseObserver);
    }

    /**
     * <pre>
     * PaymentContractsByIdPrefix lists all payment contracts having an id with a specific prefix.
     * </pre>
     */
    public void paymentContractsByIdPrefix(payments.QueryOuterClass.QueryPaymentContractsByIdPrefixRequest request,
        io.grpc.stub.StreamObserver<payments.QueryOuterClass.QueryPaymentContractsByIdPrefixResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPaymentContractsByIdPrefixMethod(), responseObserver);
    }

    /**
     * <pre>
     * Subscription queries info of a specific Subscription.
     * </pre>
     */
    public void subscription(payments.QueryOuterClass.QuerySubscriptionRequest request,
        io.grpc.stub.StreamObserver<payments.QueryOuterClass.QuerySubscriptionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubscriptionMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPaymentTemplateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                payments.QueryOuterClass.QueryPaymentTemplateRequest,
                payments.QueryOuterClass.QueryPaymentTemplateResponse>(
                  this, METHODID_PAYMENT_TEMPLATE)))
          .addMethod(
            getPaymentContractMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                payments.QueryOuterClass.QueryPaymentContractRequest,
                payments.QueryOuterClass.QueryPaymentContractResponse>(
                  this, METHODID_PAYMENT_CONTRACT)))
          .addMethod(
            getPaymentContractsByIdPrefixMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                payments.QueryOuterClass.QueryPaymentContractsByIdPrefixRequest,
                payments.QueryOuterClass.QueryPaymentContractsByIdPrefixResponse>(
                  this, METHODID_PAYMENT_CONTRACTS_BY_ID_PREFIX)))
          .addMethod(
            getSubscriptionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                payments.QueryOuterClass.QuerySubscriptionRequest,
                payments.QueryOuterClass.QuerySubscriptionResponse>(
                  this, METHODID_SUBSCRIPTION)))
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
     * PaymentTemplate queries info of a specific payment template.
     * </pre>
     */
    public void paymentTemplate(payments.QueryOuterClass.QueryPaymentTemplateRequest request,
        io.grpc.stub.StreamObserver<payments.QueryOuterClass.QueryPaymentTemplateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPaymentTemplateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PaymentContract queries info of a specific payment contract.
     * </pre>
     */
    public void paymentContract(payments.QueryOuterClass.QueryPaymentContractRequest request,
        io.grpc.stub.StreamObserver<payments.QueryOuterClass.QueryPaymentContractResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPaymentContractMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PaymentContractsByIdPrefix lists all payment contracts having an id with a specific prefix.
     * </pre>
     */
    public void paymentContractsByIdPrefix(payments.QueryOuterClass.QueryPaymentContractsByIdPrefixRequest request,
        io.grpc.stub.StreamObserver<payments.QueryOuterClass.QueryPaymentContractsByIdPrefixResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPaymentContractsByIdPrefixMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Subscription queries info of a specific Subscription.
     * </pre>
     */
    public void subscription(payments.QueryOuterClass.QuerySubscriptionRequest request,
        io.grpc.stub.StreamObserver<payments.QueryOuterClass.QuerySubscriptionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubscriptionMethod(), getCallOptions()), request, responseObserver);
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
     * PaymentTemplate queries info of a specific payment template.
     * </pre>
     */
    public payments.QueryOuterClass.QueryPaymentTemplateResponse paymentTemplate(payments.QueryOuterClass.QueryPaymentTemplateRequest request) {
      return blockingUnaryCall(
          getChannel(), getPaymentTemplateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PaymentContract queries info of a specific payment contract.
     * </pre>
     */
    public payments.QueryOuterClass.QueryPaymentContractResponse paymentContract(payments.QueryOuterClass.QueryPaymentContractRequest request) {
      return blockingUnaryCall(
          getChannel(), getPaymentContractMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PaymentContractsByIdPrefix lists all payment contracts having an id with a specific prefix.
     * </pre>
     */
    public payments.QueryOuterClass.QueryPaymentContractsByIdPrefixResponse paymentContractsByIdPrefix(payments.QueryOuterClass.QueryPaymentContractsByIdPrefixRequest request) {
      return blockingUnaryCall(
          getChannel(), getPaymentContractsByIdPrefixMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Subscription queries info of a specific Subscription.
     * </pre>
     */
    public payments.QueryOuterClass.QuerySubscriptionResponse subscription(payments.QueryOuterClass.QuerySubscriptionRequest request) {
      return blockingUnaryCall(
          getChannel(), getSubscriptionMethod(), getCallOptions(), request);
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
     * PaymentTemplate queries info of a specific payment template.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<payments.QueryOuterClass.QueryPaymentTemplateResponse> paymentTemplate(
        payments.QueryOuterClass.QueryPaymentTemplateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPaymentTemplateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PaymentContract queries info of a specific payment contract.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<payments.QueryOuterClass.QueryPaymentContractResponse> paymentContract(
        payments.QueryOuterClass.QueryPaymentContractRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPaymentContractMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PaymentContractsByIdPrefix lists all payment contracts having an id with a specific prefix.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<payments.QueryOuterClass.QueryPaymentContractsByIdPrefixResponse> paymentContractsByIdPrefix(
        payments.QueryOuterClass.QueryPaymentContractsByIdPrefixRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPaymentContractsByIdPrefixMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Subscription queries info of a specific Subscription.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<payments.QueryOuterClass.QuerySubscriptionResponse> subscription(
        payments.QueryOuterClass.QuerySubscriptionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSubscriptionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PAYMENT_TEMPLATE = 0;
  private static final int METHODID_PAYMENT_CONTRACT = 1;
  private static final int METHODID_PAYMENT_CONTRACTS_BY_ID_PREFIX = 2;
  private static final int METHODID_SUBSCRIPTION = 3;

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
        case METHODID_PAYMENT_TEMPLATE:
          serviceImpl.paymentTemplate((payments.QueryOuterClass.QueryPaymentTemplateRequest) request,
              (io.grpc.stub.StreamObserver<payments.QueryOuterClass.QueryPaymentTemplateResponse>) responseObserver);
          break;
        case METHODID_PAYMENT_CONTRACT:
          serviceImpl.paymentContract((payments.QueryOuterClass.QueryPaymentContractRequest) request,
              (io.grpc.stub.StreamObserver<payments.QueryOuterClass.QueryPaymentContractResponse>) responseObserver);
          break;
        case METHODID_PAYMENT_CONTRACTS_BY_ID_PREFIX:
          serviceImpl.paymentContractsByIdPrefix((payments.QueryOuterClass.QueryPaymentContractsByIdPrefixRequest) request,
              (io.grpc.stub.StreamObserver<payments.QueryOuterClass.QueryPaymentContractsByIdPrefixResponse>) responseObserver);
          break;
        case METHODID_SUBSCRIPTION:
          serviceImpl.subscription((payments.QueryOuterClass.QuerySubscriptionRequest) request,
              (io.grpc.stub.StreamObserver<payments.QueryOuterClass.QuerySubscriptionResponse>) responseObserver);
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
      return payments.QueryOuterClass.getDescriptor();
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
              .addMethod(getPaymentTemplateMethod())
              .addMethod(getPaymentContractMethod())
              .addMethod(getPaymentContractsByIdPrefixMethod())
              .addMethod(getSubscriptionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
