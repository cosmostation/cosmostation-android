package akash.escrow.v1beta2;

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
    comments = "Source: akash/escrow/v1beta2/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "akash.escrow.v1beta2.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<akash.escrow.v1beta2.QueryOuterClass.QueryAccountsRequest,
      akash.escrow.v1beta2.QueryOuterClass.QueryAccountsResponse> getAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Accounts",
      requestType = akash.escrow.v1beta2.QueryOuterClass.QueryAccountsRequest.class,
      responseType = akash.escrow.v1beta2.QueryOuterClass.QueryAccountsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.escrow.v1beta2.QueryOuterClass.QueryAccountsRequest,
      akash.escrow.v1beta2.QueryOuterClass.QueryAccountsResponse> getAccountsMethod() {
    io.grpc.MethodDescriptor<akash.escrow.v1beta2.QueryOuterClass.QueryAccountsRequest, akash.escrow.v1beta2.QueryOuterClass.QueryAccountsResponse> getAccountsMethod;
    if ((getAccountsMethod = QueryGrpc.getAccountsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountsMethod = QueryGrpc.getAccountsMethod) == null) {
          QueryGrpc.getAccountsMethod = getAccountsMethod =
              io.grpc.MethodDescriptor.<akash.escrow.v1beta2.QueryOuterClass.QueryAccountsRequest, akash.escrow.v1beta2.QueryOuterClass.QueryAccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Accounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.escrow.v1beta2.QueryOuterClass.QueryAccountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.escrow.v1beta2.QueryOuterClass.QueryAccountsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Accounts"))
              .build();
        }
      }
    }
    return getAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsRequest,
      akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsResponse> getPaymentsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Payments",
      requestType = akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsRequest.class,
      responseType = akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsRequest,
      akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsResponse> getPaymentsMethod() {
    io.grpc.MethodDescriptor<akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsRequest, akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsResponse> getPaymentsMethod;
    if ((getPaymentsMethod = QueryGrpc.getPaymentsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPaymentsMethod = QueryGrpc.getPaymentsMethod) == null) {
          QueryGrpc.getPaymentsMethod = getPaymentsMethod =
              io.grpc.MethodDescriptor.<akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsRequest, akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Payments"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Payments"))
              .build();
        }
      }
    }
    return getPaymentsMethod;
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
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * Accounts queries all accounts
     * </pre>
     */
    public void accounts(akash.escrow.v1beta2.QueryOuterClass.QueryAccountsRequest request,
        io.grpc.stub.StreamObserver<akash.escrow.v1beta2.QueryOuterClass.QueryAccountsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAccountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * Payments queries all payments
     * </pre>
     */
    public void payments(akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsRequest request,
        io.grpc.stub.StreamObserver<akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPaymentsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAccountsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.escrow.v1beta2.QueryOuterClass.QueryAccountsRequest,
                akash.escrow.v1beta2.QueryOuterClass.QueryAccountsResponse>(
                  this, METHODID_ACCOUNTS)))
          .addMethod(
            getPaymentsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsRequest,
                akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsResponse>(
                  this, METHODID_PAYMENTS)))
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
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * Accounts queries all accounts
     * </pre>
     */
    public void accounts(akash.escrow.v1beta2.QueryOuterClass.QueryAccountsRequest request,
        io.grpc.stub.StreamObserver<akash.escrow.v1beta2.QueryOuterClass.QueryAccountsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * Payments queries all payments
     * </pre>
     */
    public void payments(akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsRequest request,
        io.grpc.stub.StreamObserver<akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPaymentsMethod(), getCallOptions()), request, responseObserver);
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
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * Accounts queries all accounts
     * </pre>
     */
    public akash.escrow.v1beta2.QueryOuterClass.QueryAccountsResponse accounts(akash.escrow.v1beta2.QueryOuterClass.QueryAccountsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * Payments queries all payments
     * </pre>
     */
    public akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsResponse payments(akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPaymentsMethod(), getCallOptions(), request);
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
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * Accounts queries all accounts
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.escrow.v1beta2.QueryOuterClass.QueryAccountsResponse> accounts(
        akash.escrow.v1beta2.QueryOuterClass.QueryAccountsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * Payments queries all payments
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsResponse> payments(
        akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPaymentsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ACCOUNTS = 0;
  private static final int METHODID_PAYMENTS = 1;

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
        case METHODID_ACCOUNTS:
          serviceImpl.accounts((akash.escrow.v1beta2.QueryOuterClass.QueryAccountsRequest) request,
              (io.grpc.stub.StreamObserver<akash.escrow.v1beta2.QueryOuterClass.QueryAccountsResponse>) responseObserver);
          break;
        case METHODID_PAYMENTS:
          serviceImpl.payments((akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsRequest) request,
              (io.grpc.stub.StreamObserver<akash.escrow.v1beta2.QueryOuterClass.QueryPaymentsResponse>) responseObserver);
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
      return akash.escrow.v1beta2.QueryOuterClass.getDescriptor();
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
              .addMethod(getAccountsMethod())
              .addMethod(getPaymentsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
