package sentinel.plan.v1;

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
    comments = "Source: sentinel/plan/v1/querier.proto")
public final class QueryServiceGrpc {

  private QueryServiceGrpc() {}

  public static final String SERVICE_NAME = "sentinel.plan.v1.QueryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sentinel.plan.v1.Querier.QueryPlansRequest,
      sentinel.plan.v1.Querier.QueryPlansResponse> getQueryPlansMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryPlans",
      requestType = sentinel.plan.v1.Querier.QueryPlansRequest.class,
      responseType = sentinel.plan.v1.Querier.QueryPlansResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.plan.v1.Querier.QueryPlansRequest,
      sentinel.plan.v1.Querier.QueryPlansResponse> getQueryPlansMethod() {
    io.grpc.MethodDescriptor<sentinel.plan.v1.Querier.QueryPlansRequest, sentinel.plan.v1.Querier.QueryPlansResponse> getQueryPlansMethod;
    if ((getQueryPlansMethod = QueryServiceGrpc.getQueryPlansMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryPlansMethod = QueryServiceGrpc.getQueryPlansMethod) == null) {
          QueryServiceGrpc.getQueryPlansMethod = getQueryPlansMethod =
              io.grpc.MethodDescriptor.<sentinel.plan.v1.Querier.QueryPlansRequest, sentinel.plan.v1.Querier.QueryPlansResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryPlans"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v1.Querier.QueryPlansRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v1.Querier.QueryPlansResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryPlans"))
              .build();
        }
      }
    }
    return getQueryPlansMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.plan.v1.Querier.QueryPlansForProviderRequest,
      sentinel.plan.v1.Querier.QueryPlansForProviderResponse> getQueryPlansForProviderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryPlansForProvider",
      requestType = sentinel.plan.v1.Querier.QueryPlansForProviderRequest.class,
      responseType = sentinel.plan.v1.Querier.QueryPlansForProviderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.plan.v1.Querier.QueryPlansForProviderRequest,
      sentinel.plan.v1.Querier.QueryPlansForProviderResponse> getQueryPlansForProviderMethod() {
    io.grpc.MethodDescriptor<sentinel.plan.v1.Querier.QueryPlansForProviderRequest, sentinel.plan.v1.Querier.QueryPlansForProviderResponse> getQueryPlansForProviderMethod;
    if ((getQueryPlansForProviderMethod = QueryServiceGrpc.getQueryPlansForProviderMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryPlansForProviderMethod = QueryServiceGrpc.getQueryPlansForProviderMethod) == null) {
          QueryServiceGrpc.getQueryPlansForProviderMethod = getQueryPlansForProviderMethod =
              io.grpc.MethodDescriptor.<sentinel.plan.v1.Querier.QueryPlansForProviderRequest, sentinel.plan.v1.Querier.QueryPlansForProviderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryPlansForProvider"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v1.Querier.QueryPlansForProviderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v1.Querier.QueryPlansForProviderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryPlansForProvider"))
              .build();
        }
      }
    }
    return getQueryPlansForProviderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.plan.v1.Querier.QueryPlanRequest,
      sentinel.plan.v1.Querier.QueryPlanResponse> getQueryPlanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryPlan",
      requestType = sentinel.plan.v1.Querier.QueryPlanRequest.class,
      responseType = sentinel.plan.v1.Querier.QueryPlanResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.plan.v1.Querier.QueryPlanRequest,
      sentinel.plan.v1.Querier.QueryPlanResponse> getQueryPlanMethod() {
    io.grpc.MethodDescriptor<sentinel.plan.v1.Querier.QueryPlanRequest, sentinel.plan.v1.Querier.QueryPlanResponse> getQueryPlanMethod;
    if ((getQueryPlanMethod = QueryServiceGrpc.getQueryPlanMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryPlanMethod = QueryServiceGrpc.getQueryPlanMethod) == null) {
          QueryServiceGrpc.getQueryPlanMethod = getQueryPlanMethod =
              io.grpc.MethodDescriptor.<sentinel.plan.v1.Querier.QueryPlanRequest, sentinel.plan.v1.Querier.QueryPlanResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryPlan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v1.Querier.QueryPlanRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v1.Querier.QueryPlanResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryPlan"))
              .build();
        }
      }
    }
    return getQueryPlanMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.plan.v1.Querier.QueryNodesForPlanRequest,
      sentinel.plan.v1.Querier.QueryNodesForPlanResponse> getQueryNodesForPlanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryNodesForPlan",
      requestType = sentinel.plan.v1.Querier.QueryNodesForPlanRequest.class,
      responseType = sentinel.plan.v1.Querier.QueryNodesForPlanResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.plan.v1.Querier.QueryNodesForPlanRequest,
      sentinel.plan.v1.Querier.QueryNodesForPlanResponse> getQueryNodesForPlanMethod() {
    io.grpc.MethodDescriptor<sentinel.plan.v1.Querier.QueryNodesForPlanRequest, sentinel.plan.v1.Querier.QueryNodesForPlanResponse> getQueryNodesForPlanMethod;
    if ((getQueryNodesForPlanMethod = QueryServiceGrpc.getQueryNodesForPlanMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryNodesForPlanMethod = QueryServiceGrpc.getQueryNodesForPlanMethod) == null) {
          QueryServiceGrpc.getQueryNodesForPlanMethod = getQueryNodesForPlanMethod =
              io.grpc.MethodDescriptor.<sentinel.plan.v1.Querier.QueryNodesForPlanRequest, sentinel.plan.v1.Querier.QueryNodesForPlanResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryNodesForPlan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v1.Querier.QueryNodesForPlanRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v1.Querier.QueryNodesForPlanResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryNodesForPlan"))
              .build();
        }
      }
    }
    return getQueryNodesForPlanMethod;
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
    public void queryPlans(sentinel.plan.v1.Querier.QueryPlansRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v1.Querier.QueryPlansResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryPlansMethod(), responseObserver);
    }

    /**
     */
    public void queryPlansForProvider(sentinel.plan.v1.Querier.QueryPlansForProviderRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v1.Querier.QueryPlansForProviderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryPlansForProviderMethod(), responseObserver);
    }

    /**
     */
    public void queryPlan(sentinel.plan.v1.Querier.QueryPlanRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v1.Querier.QueryPlanResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryPlanMethod(), responseObserver);
    }

    /**
     */
    public void queryNodesForPlan(sentinel.plan.v1.Querier.QueryNodesForPlanRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v1.Querier.QueryNodesForPlanResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryNodesForPlanMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getQueryPlansMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.plan.v1.Querier.QueryPlansRequest,
                sentinel.plan.v1.Querier.QueryPlansResponse>(
                  this, METHODID_QUERY_PLANS)))
          .addMethod(
            getQueryPlansForProviderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.plan.v1.Querier.QueryPlansForProviderRequest,
                sentinel.plan.v1.Querier.QueryPlansForProviderResponse>(
                  this, METHODID_QUERY_PLANS_FOR_PROVIDER)))
          .addMethod(
            getQueryPlanMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.plan.v1.Querier.QueryPlanRequest,
                sentinel.plan.v1.Querier.QueryPlanResponse>(
                  this, METHODID_QUERY_PLAN)))
          .addMethod(
            getQueryNodesForPlanMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.plan.v1.Querier.QueryNodesForPlanRequest,
                sentinel.plan.v1.Querier.QueryNodesForPlanResponse>(
                  this, METHODID_QUERY_NODES_FOR_PLAN)))
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
    public void queryPlans(sentinel.plan.v1.Querier.QueryPlansRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v1.Querier.QueryPlansResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryPlansMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryPlansForProvider(sentinel.plan.v1.Querier.QueryPlansForProviderRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v1.Querier.QueryPlansForProviderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryPlansForProviderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryPlan(sentinel.plan.v1.Querier.QueryPlanRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v1.Querier.QueryPlanResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryPlanMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryNodesForPlan(sentinel.plan.v1.Querier.QueryNodesForPlanRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v1.Querier.QueryNodesForPlanResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryNodesForPlanMethod(), getCallOptions()), request, responseObserver);
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
    public sentinel.plan.v1.Querier.QueryPlansResponse queryPlans(sentinel.plan.v1.Querier.QueryPlansRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryPlansMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.plan.v1.Querier.QueryPlansForProviderResponse queryPlansForProvider(sentinel.plan.v1.Querier.QueryPlansForProviderRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryPlansForProviderMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.plan.v1.Querier.QueryPlanResponse queryPlan(sentinel.plan.v1.Querier.QueryPlanRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryPlanMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.plan.v1.Querier.QueryNodesForPlanResponse queryNodesForPlan(sentinel.plan.v1.Querier.QueryNodesForPlanRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryNodesForPlanMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<sentinel.plan.v1.Querier.QueryPlansResponse> queryPlans(
        sentinel.plan.v1.Querier.QueryPlansRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryPlansMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.plan.v1.Querier.QueryPlansForProviderResponse> queryPlansForProvider(
        sentinel.plan.v1.Querier.QueryPlansForProviderRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryPlansForProviderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.plan.v1.Querier.QueryPlanResponse> queryPlan(
        sentinel.plan.v1.Querier.QueryPlanRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryPlanMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.plan.v1.Querier.QueryNodesForPlanResponse> queryNodesForPlan(
        sentinel.plan.v1.Querier.QueryNodesForPlanRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryNodesForPlanMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_PLANS = 0;
  private static final int METHODID_QUERY_PLANS_FOR_PROVIDER = 1;
  private static final int METHODID_QUERY_PLAN = 2;
  private static final int METHODID_QUERY_NODES_FOR_PLAN = 3;

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
        case METHODID_QUERY_PLANS:
          serviceImpl.queryPlans((sentinel.plan.v1.Querier.QueryPlansRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.plan.v1.Querier.QueryPlansResponse>) responseObserver);
          break;
        case METHODID_QUERY_PLANS_FOR_PROVIDER:
          serviceImpl.queryPlansForProvider((sentinel.plan.v1.Querier.QueryPlansForProviderRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.plan.v1.Querier.QueryPlansForProviderResponse>) responseObserver);
          break;
        case METHODID_QUERY_PLAN:
          serviceImpl.queryPlan((sentinel.plan.v1.Querier.QueryPlanRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.plan.v1.Querier.QueryPlanResponse>) responseObserver);
          break;
        case METHODID_QUERY_NODES_FOR_PLAN:
          serviceImpl.queryNodesForPlan((sentinel.plan.v1.Querier.QueryNodesForPlanRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.plan.v1.Querier.QueryNodesForPlanResponse>) responseObserver);
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
      return sentinel.plan.v1.Querier.getDescriptor();
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
              .addMethod(getQueryPlansMethod())
              .addMethod(getQueryPlansForProviderMethod())
              .addMethod(getQueryPlanMethod())
              .addMethod(getQueryNodesForPlanMethod())
              .build();
        }
      }
    }
    return result;
  }
}
