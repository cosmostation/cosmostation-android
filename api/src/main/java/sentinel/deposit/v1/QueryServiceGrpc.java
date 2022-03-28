package sentinel.deposit.v1;

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
    comments = "Source: sentinel/deposit/v1/querier.proto")
public final class QueryServiceGrpc {

  private QueryServiceGrpc() {}

  public static final String SERVICE_NAME = "sentinel.deposit.v1.QueryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sentinel.deposit.v1.Querier.QueryDepositsRequest,
      sentinel.deposit.v1.Querier.QueryDepositsResponse> getQueryDepositsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryDeposits",
      requestType = sentinel.deposit.v1.Querier.QueryDepositsRequest.class,
      responseType = sentinel.deposit.v1.Querier.QueryDepositsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.deposit.v1.Querier.QueryDepositsRequest,
      sentinel.deposit.v1.Querier.QueryDepositsResponse> getQueryDepositsMethod() {
    io.grpc.MethodDescriptor<sentinel.deposit.v1.Querier.QueryDepositsRequest, sentinel.deposit.v1.Querier.QueryDepositsResponse> getQueryDepositsMethod;
    if ((getQueryDepositsMethod = QueryServiceGrpc.getQueryDepositsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryDepositsMethod = QueryServiceGrpc.getQueryDepositsMethod) == null) {
          QueryServiceGrpc.getQueryDepositsMethod = getQueryDepositsMethod =
              io.grpc.MethodDescriptor.<sentinel.deposit.v1.Querier.QueryDepositsRequest, sentinel.deposit.v1.Querier.QueryDepositsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryDeposits"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.deposit.v1.Querier.QueryDepositsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.deposit.v1.Querier.QueryDepositsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryDeposits"))
              .build();
        }
      }
    }
    return getQueryDepositsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.deposit.v1.Querier.QueryDepositRequest,
      sentinel.deposit.v1.Querier.QueryDepositResponse> getQueryDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryDeposit",
      requestType = sentinel.deposit.v1.Querier.QueryDepositRequest.class,
      responseType = sentinel.deposit.v1.Querier.QueryDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.deposit.v1.Querier.QueryDepositRequest,
      sentinel.deposit.v1.Querier.QueryDepositResponse> getQueryDepositMethod() {
    io.grpc.MethodDescriptor<sentinel.deposit.v1.Querier.QueryDepositRequest, sentinel.deposit.v1.Querier.QueryDepositResponse> getQueryDepositMethod;
    if ((getQueryDepositMethod = QueryServiceGrpc.getQueryDepositMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryDepositMethod = QueryServiceGrpc.getQueryDepositMethod) == null) {
          QueryServiceGrpc.getQueryDepositMethod = getQueryDepositMethod =
              io.grpc.MethodDescriptor.<sentinel.deposit.v1.Querier.QueryDepositRequest, sentinel.deposit.v1.Querier.QueryDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryDeposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.deposit.v1.Querier.QueryDepositRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.deposit.v1.Querier.QueryDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryDeposit"))
              .build();
        }
      }
    }
    return getQueryDepositMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QueryServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceStub>() {
        @java.lang.Override
        public QueryServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceStub(channel, callOptions);
        }
      };
    return QueryServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QueryServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceBlockingStub>() {
        @java.lang.Override
        public QueryServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceBlockingStub(channel, callOptions);
        }
      };
    return QueryServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QueryServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceFutureStub>() {
        @java.lang.Override
        public QueryServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceFutureStub(channel, callOptions);
        }
      };
    return QueryServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class QueryServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void queryDeposits(sentinel.deposit.v1.Querier.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<sentinel.deposit.v1.Querier.QueryDepositsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryDepositsMethod(), responseObserver);
    }

    /**
     */
    public void queryDeposit(sentinel.deposit.v1.Querier.QueryDepositRequest request,
        io.grpc.stub.StreamObserver<sentinel.deposit.v1.Querier.QueryDepositResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryDepositMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getQueryDepositsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.deposit.v1.Querier.QueryDepositsRequest,
                sentinel.deposit.v1.Querier.QueryDepositsResponse>(
                  this, METHODID_QUERY_DEPOSITS)))
          .addMethod(
            getQueryDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.deposit.v1.Querier.QueryDepositRequest,
                sentinel.deposit.v1.Querier.QueryDepositResponse>(
                  this, METHODID_QUERY_DEPOSIT)))
          .build();
    }
  }

  /**
   */
  public static final class QueryServiceStub extends io.grpc.stub.AbstractAsyncStub<QueryServiceStub> {
    private QueryServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceStub(channel, callOptions);
    }

    /**
     */
    public void queryDeposits(sentinel.deposit.v1.Querier.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<sentinel.deposit.v1.Querier.QueryDepositsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryDepositsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryDeposit(sentinel.deposit.v1.Querier.QueryDepositRequest request,
        io.grpc.stub.StreamObserver<sentinel.deposit.v1.Querier.QueryDepositResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryDepositMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class QueryServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<QueryServiceBlockingStub> {
    private QueryServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public sentinel.deposit.v1.Querier.QueryDepositsResponse queryDeposits(sentinel.deposit.v1.Querier.QueryDepositsRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryDepositsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.deposit.v1.Querier.QueryDepositResponse queryDeposit(sentinel.deposit.v1.Querier.QueryDepositRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryDepositMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class QueryServiceFutureStub extends io.grpc.stub.AbstractFutureStub<QueryServiceFutureStub> {
    private QueryServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.deposit.v1.Querier.QueryDepositsResponse> queryDeposits(
        sentinel.deposit.v1.Querier.QueryDepositsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryDepositsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.deposit.v1.Querier.QueryDepositResponse> queryDeposit(
        sentinel.deposit.v1.Querier.QueryDepositRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryDepositMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_DEPOSITS = 0;
  private static final int METHODID_QUERY_DEPOSIT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final QueryServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(QueryServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_QUERY_DEPOSITS:
          serviceImpl.queryDeposits((sentinel.deposit.v1.Querier.QueryDepositsRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.deposit.v1.Querier.QueryDepositsResponse>) responseObserver);
          break;
        case METHODID_QUERY_DEPOSIT:
          serviceImpl.queryDeposit((sentinel.deposit.v1.Querier.QueryDepositRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.deposit.v1.Querier.QueryDepositResponse>) responseObserver);
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

  private static abstract class QueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sentinel.deposit.v1.Querier.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("QueryService");
    }
  }

  private static final class QueryServiceFileDescriptorSupplier
      extends QueryServiceBaseDescriptorSupplier {
    QueryServiceFileDescriptorSupplier() {}
  }

  private static final class QueryServiceMethodDescriptorSupplier
      extends QueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    QueryServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (QueryServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QueryServiceFileDescriptorSupplier())
              .addMethod(getQueryDepositsMethod())
              .addMethod(getQueryDepositMethod())
              .build();
        }
      }
    }
    return result;
  }
}
