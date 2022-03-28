package sentinel.node.v1;

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
    comments = "Source: sentinel/node/v1/querier.proto")
public final class QueryServiceGrpc {

  private QueryServiceGrpc() {}

  public static final String SERVICE_NAME = "sentinel.node.v1.QueryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sentinel.node.v1.Querier.QueryNodesRequest,
      sentinel.node.v1.Querier.QueryNodesResponse> getQueryNodesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryNodes",
      requestType = sentinel.node.v1.Querier.QueryNodesRequest.class,
      responseType = sentinel.node.v1.Querier.QueryNodesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.node.v1.Querier.QueryNodesRequest,
      sentinel.node.v1.Querier.QueryNodesResponse> getQueryNodesMethod() {
    io.grpc.MethodDescriptor<sentinel.node.v1.Querier.QueryNodesRequest, sentinel.node.v1.Querier.QueryNodesResponse> getQueryNodesMethod;
    if ((getQueryNodesMethod = QueryServiceGrpc.getQueryNodesMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryNodesMethod = QueryServiceGrpc.getQueryNodesMethod) == null) {
          QueryServiceGrpc.getQueryNodesMethod = getQueryNodesMethod =
              io.grpc.MethodDescriptor.<sentinel.node.v1.Querier.QueryNodesRequest, sentinel.node.v1.Querier.QueryNodesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryNodes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.node.v1.Querier.QueryNodesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.node.v1.Querier.QueryNodesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryNodes"))
              .build();
        }
      }
    }
    return getQueryNodesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.node.v1.Querier.QueryNodesForProviderRequest,
      sentinel.node.v1.Querier.QueryNodesForProviderResponse> getQueryNodesForProviderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryNodesForProvider",
      requestType = sentinel.node.v1.Querier.QueryNodesForProviderRequest.class,
      responseType = sentinel.node.v1.Querier.QueryNodesForProviderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.node.v1.Querier.QueryNodesForProviderRequest,
      sentinel.node.v1.Querier.QueryNodesForProviderResponse> getQueryNodesForProviderMethod() {
    io.grpc.MethodDescriptor<sentinel.node.v1.Querier.QueryNodesForProviderRequest, sentinel.node.v1.Querier.QueryNodesForProviderResponse> getQueryNodesForProviderMethod;
    if ((getQueryNodesForProviderMethod = QueryServiceGrpc.getQueryNodesForProviderMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryNodesForProviderMethod = QueryServiceGrpc.getQueryNodesForProviderMethod) == null) {
          QueryServiceGrpc.getQueryNodesForProviderMethod = getQueryNodesForProviderMethod =
              io.grpc.MethodDescriptor.<sentinel.node.v1.Querier.QueryNodesForProviderRequest, sentinel.node.v1.Querier.QueryNodesForProviderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryNodesForProvider"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.node.v1.Querier.QueryNodesForProviderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.node.v1.Querier.QueryNodesForProviderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryNodesForProvider"))
              .build();
        }
      }
    }
    return getQueryNodesForProviderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.node.v1.Querier.QueryNodeRequest,
      sentinel.node.v1.Querier.QueryNodeResponse> getQueryNodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryNode",
      requestType = sentinel.node.v1.Querier.QueryNodeRequest.class,
      responseType = sentinel.node.v1.Querier.QueryNodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.node.v1.Querier.QueryNodeRequest,
      sentinel.node.v1.Querier.QueryNodeResponse> getQueryNodeMethod() {
    io.grpc.MethodDescriptor<sentinel.node.v1.Querier.QueryNodeRequest, sentinel.node.v1.Querier.QueryNodeResponse> getQueryNodeMethod;
    if ((getQueryNodeMethod = QueryServiceGrpc.getQueryNodeMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryNodeMethod = QueryServiceGrpc.getQueryNodeMethod) == null) {
          QueryServiceGrpc.getQueryNodeMethod = getQueryNodeMethod =
              io.grpc.MethodDescriptor.<sentinel.node.v1.Querier.QueryNodeRequest, sentinel.node.v1.Querier.QueryNodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryNode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.node.v1.Querier.QueryNodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.node.v1.Querier.QueryNodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryNode"))
              .build();
        }
      }
    }
    return getQueryNodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.node.v1.Querier.QueryParamsRequest,
      sentinel.node.v1.Querier.QueryParamsResponse> getQueryParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryParams",
      requestType = sentinel.node.v1.Querier.QueryParamsRequest.class,
      responseType = sentinel.node.v1.Querier.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.node.v1.Querier.QueryParamsRequest,
      sentinel.node.v1.Querier.QueryParamsResponse> getQueryParamsMethod() {
    io.grpc.MethodDescriptor<sentinel.node.v1.Querier.QueryParamsRequest, sentinel.node.v1.Querier.QueryParamsResponse> getQueryParamsMethod;
    if ((getQueryParamsMethod = QueryServiceGrpc.getQueryParamsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryParamsMethod = QueryServiceGrpc.getQueryParamsMethod) == null) {
          QueryServiceGrpc.getQueryParamsMethod = getQueryParamsMethod =
              io.grpc.MethodDescriptor.<sentinel.node.v1.Querier.QueryParamsRequest, sentinel.node.v1.Querier.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.node.v1.Querier.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.node.v1.Querier.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryParams"))
              .build();
        }
      }
    }
    return getQueryParamsMethod;
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
    public void queryNodes(sentinel.node.v1.Querier.QueryNodesRequest request,
        io.grpc.stub.StreamObserver<sentinel.node.v1.Querier.QueryNodesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryNodesMethod(), responseObserver);
    }

    /**
     */
    public void queryNodesForProvider(sentinel.node.v1.Querier.QueryNodesForProviderRequest request,
        io.grpc.stub.StreamObserver<sentinel.node.v1.Querier.QueryNodesForProviderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryNodesForProviderMethod(), responseObserver);
    }

    /**
     */
    public void queryNode(sentinel.node.v1.Querier.QueryNodeRequest request,
        io.grpc.stub.StreamObserver<sentinel.node.v1.Querier.QueryNodeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryNodeMethod(), responseObserver);
    }

    /**
     */
    public void queryParams(sentinel.node.v1.Querier.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<sentinel.node.v1.Querier.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getQueryNodesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.node.v1.Querier.QueryNodesRequest,
                sentinel.node.v1.Querier.QueryNodesResponse>(
                  this, METHODID_QUERY_NODES)))
          .addMethod(
            getQueryNodesForProviderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.node.v1.Querier.QueryNodesForProviderRequest,
                sentinel.node.v1.Querier.QueryNodesForProviderResponse>(
                  this, METHODID_QUERY_NODES_FOR_PROVIDER)))
          .addMethod(
            getQueryNodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.node.v1.Querier.QueryNodeRequest,
                sentinel.node.v1.Querier.QueryNodeResponse>(
                  this, METHODID_QUERY_NODE)))
          .addMethod(
            getQueryParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.node.v1.Querier.QueryParamsRequest,
                sentinel.node.v1.Querier.QueryParamsResponse>(
                  this, METHODID_QUERY_PARAMS)))
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
    public void queryNodes(sentinel.node.v1.Querier.QueryNodesRequest request,
        io.grpc.stub.StreamObserver<sentinel.node.v1.Querier.QueryNodesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryNodesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryNodesForProvider(sentinel.node.v1.Querier.QueryNodesForProviderRequest request,
        io.grpc.stub.StreamObserver<sentinel.node.v1.Querier.QueryNodesForProviderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryNodesForProviderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryNode(sentinel.node.v1.Querier.QueryNodeRequest request,
        io.grpc.stub.StreamObserver<sentinel.node.v1.Querier.QueryNodeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryNodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryParams(sentinel.node.v1.Querier.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<sentinel.node.v1.Querier.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryParamsMethod(), getCallOptions()), request, responseObserver);
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
    public sentinel.node.v1.Querier.QueryNodesResponse queryNodes(sentinel.node.v1.Querier.QueryNodesRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryNodesMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.node.v1.Querier.QueryNodesForProviderResponse queryNodesForProvider(sentinel.node.v1.Querier.QueryNodesForProviderRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryNodesForProviderMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.node.v1.Querier.QueryNodeResponse queryNode(sentinel.node.v1.Querier.QueryNodeRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryNodeMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.node.v1.Querier.QueryParamsResponse queryParams(sentinel.node.v1.Querier.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryParamsMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<sentinel.node.v1.Querier.QueryNodesResponse> queryNodes(
        sentinel.node.v1.Querier.QueryNodesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryNodesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.node.v1.Querier.QueryNodesForProviderResponse> queryNodesForProvider(
        sentinel.node.v1.Querier.QueryNodesForProviderRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryNodesForProviderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.node.v1.Querier.QueryNodeResponse> queryNode(
        sentinel.node.v1.Querier.QueryNodeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryNodeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.node.v1.Querier.QueryParamsResponse> queryParams(
        sentinel.node.v1.Querier.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_NODES = 0;
  private static final int METHODID_QUERY_NODES_FOR_PROVIDER = 1;
  private static final int METHODID_QUERY_NODE = 2;
  private static final int METHODID_QUERY_PARAMS = 3;

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
        case METHODID_QUERY_NODES:
          serviceImpl.queryNodes((sentinel.node.v1.Querier.QueryNodesRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.node.v1.Querier.QueryNodesResponse>) responseObserver);
          break;
        case METHODID_QUERY_NODES_FOR_PROVIDER:
          serviceImpl.queryNodesForProvider((sentinel.node.v1.Querier.QueryNodesForProviderRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.node.v1.Querier.QueryNodesForProviderResponse>) responseObserver);
          break;
        case METHODID_QUERY_NODE:
          serviceImpl.queryNode((sentinel.node.v1.Querier.QueryNodeRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.node.v1.Querier.QueryNodeResponse>) responseObserver);
          break;
        case METHODID_QUERY_PARAMS:
          serviceImpl.queryParams((sentinel.node.v1.Querier.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.node.v1.Querier.QueryParamsResponse>) responseObserver);
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
      return sentinel.node.v1.Querier.getDescriptor();
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
              .addMethod(getQueryNodesMethod())
              .addMethod(getQueryNodesForProviderMethod())
              .addMethod(getQueryNodeMethod())
              .addMethod(getQueryParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
