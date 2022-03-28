package sentinel.provider.v1;

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
    comments = "Source: sentinel/provider/v1/querier.proto")
public final class QueryServiceGrpc {

  private QueryServiceGrpc() {}

  public static final String SERVICE_NAME = "sentinel.provider.v1.QueryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sentinel.provider.v1.Querier.QueryProvidersRequest,
      sentinel.provider.v1.Querier.QueryProvidersResponse> getQueryProvidersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryProviders",
      requestType = sentinel.provider.v1.Querier.QueryProvidersRequest.class,
      responseType = sentinel.provider.v1.Querier.QueryProvidersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.provider.v1.Querier.QueryProvidersRequest,
      sentinel.provider.v1.Querier.QueryProvidersResponse> getQueryProvidersMethod() {
    io.grpc.MethodDescriptor<sentinel.provider.v1.Querier.QueryProvidersRequest, sentinel.provider.v1.Querier.QueryProvidersResponse> getQueryProvidersMethod;
    if ((getQueryProvidersMethod = QueryServiceGrpc.getQueryProvidersMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryProvidersMethod = QueryServiceGrpc.getQueryProvidersMethod) == null) {
          QueryServiceGrpc.getQueryProvidersMethod = getQueryProvidersMethod =
              io.grpc.MethodDescriptor.<sentinel.provider.v1.Querier.QueryProvidersRequest, sentinel.provider.v1.Querier.QueryProvidersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryProviders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.provider.v1.Querier.QueryProvidersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.provider.v1.Querier.QueryProvidersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryProviders"))
              .build();
        }
      }
    }
    return getQueryProvidersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.provider.v1.Querier.QueryProviderRequest,
      sentinel.provider.v1.Querier.QueryProviderResponse> getQueryProviderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryProvider",
      requestType = sentinel.provider.v1.Querier.QueryProviderRequest.class,
      responseType = sentinel.provider.v1.Querier.QueryProviderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.provider.v1.Querier.QueryProviderRequest,
      sentinel.provider.v1.Querier.QueryProviderResponse> getQueryProviderMethod() {
    io.grpc.MethodDescriptor<sentinel.provider.v1.Querier.QueryProviderRequest, sentinel.provider.v1.Querier.QueryProviderResponse> getQueryProviderMethod;
    if ((getQueryProviderMethod = QueryServiceGrpc.getQueryProviderMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryProviderMethod = QueryServiceGrpc.getQueryProviderMethod) == null) {
          QueryServiceGrpc.getQueryProviderMethod = getQueryProviderMethod =
              io.grpc.MethodDescriptor.<sentinel.provider.v1.Querier.QueryProviderRequest, sentinel.provider.v1.Querier.QueryProviderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryProvider"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.provider.v1.Querier.QueryProviderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.provider.v1.Querier.QueryProviderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryProvider"))
              .build();
        }
      }
    }
    return getQueryProviderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.provider.v1.Querier.QueryParamsRequest,
      sentinel.provider.v1.Querier.QueryParamsResponse> getQueryParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryParams",
      requestType = sentinel.provider.v1.Querier.QueryParamsRequest.class,
      responseType = sentinel.provider.v1.Querier.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.provider.v1.Querier.QueryParamsRequest,
      sentinel.provider.v1.Querier.QueryParamsResponse> getQueryParamsMethod() {
    io.grpc.MethodDescriptor<sentinel.provider.v1.Querier.QueryParamsRequest, sentinel.provider.v1.Querier.QueryParamsResponse> getQueryParamsMethod;
    if ((getQueryParamsMethod = QueryServiceGrpc.getQueryParamsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryParamsMethod = QueryServiceGrpc.getQueryParamsMethod) == null) {
          QueryServiceGrpc.getQueryParamsMethod = getQueryParamsMethod =
              io.grpc.MethodDescriptor.<sentinel.provider.v1.Querier.QueryParamsRequest, sentinel.provider.v1.Querier.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.provider.v1.Querier.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.provider.v1.Querier.QueryParamsResponse.getDefaultInstance()))
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
    public void queryProviders(sentinel.provider.v1.Querier.QueryProvidersRequest request,
        io.grpc.stub.StreamObserver<sentinel.provider.v1.Querier.QueryProvidersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryProvidersMethod(), responseObserver);
    }

    /**
     */
    public void queryProvider(sentinel.provider.v1.Querier.QueryProviderRequest request,
        io.grpc.stub.StreamObserver<sentinel.provider.v1.Querier.QueryProviderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryProviderMethod(), responseObserver);
    }

    /**
     */
    public void queryParams(sentinel.provider.v1.Querier.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<sentinel.provider.v1.Querier.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getQueryProvidersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.provider.v1.Querier.QueryProvidersRequest,
                sentinel.provider.v1.Querier.QueryProvidersResponse>(
                  this, METHODID_QUERY_PROVIDERS)))
          .addMethod(
            getQueryProviderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.provider.v1.Querier.QueryProviderRequest,
                sentinel.provider.v1.Querier.QueryProviderResponse>(
                  this, METHODID_QUERY_PROVIDER)))
          .addMethod(
            getQueryParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sentinel.provider.v1.Querier.QueryParamsRequest,
                sentinel.provider.v1.Querier.QueryParamsResponse>(
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
    public void queryProviders(sentinel.provider.v1.Querier.QueryProvidersRequest request,
        io.grpc.stub.StreamObserver<sentinel.provider.v1.Querier.QueryProvidersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryProvidersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryProvider(sentinel.provider.v1.Querier.QueryProviderRequest request,
        io.grpc.stub.StreamObserver<sentinel.provider.v1.Querier.QueryProviderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryProviderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryParams(sentinel.provider.v1.Querier.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<sentinel.provider.v1.Querier.QueryParamsResponse> responseObserver) {
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
    public sentinel.provider.v1.Querier.QueryProvidersResponse queryProviders(sentinel.provider.v1.Querier.QueryProvidersRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryProvidersMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.provider.v1.Querier.QueryProviderResponse queryProvider(sentinel.provider.v1.Querier.QueryProviderRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryProviderMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.provider.v1.Querier.QueryParamsResponse queryParams(sentinel.provider.v1.Querier.QueryParamsRequest request) {
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
    public com.google.common.util.concurrent.ListenableFuture<sentinel.provider.v1.Querier.QueryProvidersResponse> queryProviders(
        sentinel.provider.v1.Querier.QueryProvidersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryProvidersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.provider.v1.Querier.QueryProviderResponse> queryProvider(
        sentinel.provider.v1.Querier.QueryProviderRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryProviderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.provider.v1.Querier.QueryParamsResponse> queryParams(
        sentinel.provider.v1.Querier.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_PROVIDERS = 0;
  private static final int METHODID_QUERY_PROVIDER = 1;
  private static final int METHODID_QUERY_PARAMS = 2;

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
        case METHODID_QUERY_PROVIDERS:
          serviceImpl.queryProviders((sentinel.provider.v1.Querier.QueryProvidersRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.provider.v1.Querier.QueryProvidersResponse>) responseObserver);
          break;
        case METHODID_QUERY_PROVIDER:
          serviceImpl.queryProvider((sentinel.provider.v1.Querier.QueryProviderRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.provider.v1.Querier.QueryProviderResponse>) responseObserver);
          break;
        case METHODID_QUERY_PARAMS:
          serviceImpl.queryParams((sentinel.provider.v1.Querier.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.provider.v1.Querier.QueryParamsResponse>) responseObserver);
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
      return sentinel.provider.v1.Querier.getDescriptor();
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
              .addMethod(getQueryProvidersMethod())
              .addMethod(getQueryProviderMethod())
              .addMethod(getQueryParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
