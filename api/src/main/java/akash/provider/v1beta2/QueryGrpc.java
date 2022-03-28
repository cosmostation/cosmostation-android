package akash.provider.v1beta2;

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
    comments = "Source: akash/provider/v1beta2/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "akash.provider.v1beta2.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<akash.provider.v1beta2.QueryOuterClass.QueryProvidersRequest,
      akash.provider.v1beta2.QueryOuterClass.QueryProvidersResponse> getProvidersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Providers",
      requestType = akash.provider.v1beta2.QueryOuterClass.QueryProvidersRequest.class,
      responseType = akash.provider.v1beta2.QueryOuterClass.QueryProvidersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.provider.v1beta2.QueryOuterClass.QueryProvidersRequest,
      akash.provider.v1beta2.QueryOuterClass.QueryProvidersResponse> getProvidersMethod() {
    io.grpc.MethodDescriptor<akash.provider.v1beta2.QueryOuterClass.QueryProvidersRequest, akash.provider.v1beta2.QueryOuterClass.QueryProvidersResponse> getProvidersMethod;
    if ((getProvidersMethod = QueryGrpc.getProvidersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProvidersMethod = QueryGrpc.getProvidersMethod) == null) {
          QueryGrpc.getProvidersMethod = getProvidersMethod =
              io.grpc.MethodDescriptor.<akash.provider.v1beta2.QueryOuterClass.QueryProvidersRequest, akash.provider.v1beta2.QueryOuterClass.QueryProvidersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Providers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.provider.v1beta2.QueryOuterClass.QueryProvidersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.provider.v1beta2.QueryOuterClass.QueryProvidersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Providers"))
              .build();
        }
      }
    }
    return getProvidersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.provider.v1beta2.QueryOuterClass.QueryProviderRequest,
      akash.provider.v1beta2.QueryOuterClass.QueryProviderResponse> getProviderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Provider",
      requestType = akash.provider.v1beta2.QueryOuterClass.QueryProviderRequest.class,
      responseType = akash.provider.v1beta2.QueryOuterClass.QueryProviderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.provider.v1beta2.QueryOuterClass.QueryProviderRequest,
      akash.provider.v1beta2.QueryOuterClass.QueryProviderResponse> getProviderMethod() {
    io.grpc.MethodDescriptor<akash.provider.v1beta2.QueryOuterClass.QueryProviderRequest, akash.provider.v1beta2.QueryOuterClass.QueryProviderResponse> getProviderMethod;
    if ((getProviderMethod = QueryGrpc.getProviderMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProviderMethod = QueryGrpc.getProviderMethod) == null) {
          QueryGrpc.getProviderMethod = getProviderMethod =
              io.grpc.MethodDescriptor.<akash.provider.v1beta2.QueryOuterClass.QueryProviderRequest, akash.provider.v1beta2.QueryOuterClass.QueryProviderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Provider"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.provider.v1beta2.QueryOuterClass.QueryProviderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.provider.v1beta2.QueryOuterClass.QueryProviderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Provider"))
              .build();
        }
      }
    }
    return getProviderMethod;
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
     * Providers queries providers
     * </pre>
     */
    public void providers(akash.provider.v1beta2.QueryOuterClass.QueryProvidersRequest request,
        io.grpc.stub.StreamObserver<akash.provider.v1beta2.QueryOuterClass.QueryProvidersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProvidersMethod(), responseObserver);
    }

    /**
     * <pre>
     * Provider queries provider details
     * </pre>
     */
    public void provider(akash.provider.v1beta2.QueryOuterClass.QueryProviderRequest request,
        io.grpc.stub.StreamObserver<akash.provider.v1beta2.QueryOuterClass.QueryProviderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProviderMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getProvidersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.provider.v1beta2.QueryOuterClass.QueryProvidersRequest,
                akash.provider.v1beta2.QueryOuterClass.QueryProvidersResponse>(
                  this, METHODID_PROVIDERS)))
          .addMethod(
            getProviderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.provider.v1beta2.QueryOuterClass.QueryProviderRequest,
                akash.provider.v1beta2.QueryOuterClass.QueryProviderResponse>(
                  this, METHODID_PROVIDER)))
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
     * Providers queries providers
     * </pre>
     */
    public void providers(akash.provider.v1beta2.QueryOuterClass.QueryProvidersRequest request,
        io.grpc.stub.StreamObserver<akash.provider.v1beta2.QueryOuterClass.QueryProvidersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProvidersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Provider queries provider details
     * </pre>
     */
    public void provider(akash.provider.v1beta2.QueryOuterClass.QueryProviderRequest request,
        io.grpc.stub.StreamObserver<akash.provider.v1beta2.QueryOuterClass.QueryProviderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProviderMethod(), getCallOptions()), request, responseObserver);
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
     * Providers queries providers
     * </pre>
     */
    public akash.provider.v1beta2.QueryOuterClass.QueryProvidersResponse providers(akash.provider.v1beta2.QueryOuterClass.QueryProvidersRequest request) {
      return blockingUnaryCall(
          getChannel(), getProvidersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Provider queries provider details
     * </pre>
     */
    public akash.provider.v1beta2.QueryOuterClass.QueryProviderResponse provider(akash.provider.v1beta2.QueryOuterClass.QueryProviderRequest request) {
      return blockingUnaryCall(
          getChannel(), getProviderMethod(), getCallOptions(), request);
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
     * Providers queries providers
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.provider.v1beta2.QueryOuterClass.QueryProvidersResponse> providers(
        akash.provider.v1beta2.QueryOuterClass.QueryProvidersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProvidersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Provider queries provider details
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.provider.v1beta2.QueryOuterClass.QueryProviderResponse> provider(
        akash.provider.v1beta2.QueryOuterClass.QueryProviderRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProviderMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PROVIDERS = 0;
  private static final int METHODID_PROVIDER = 1;

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
        case METHODID_PROVIDERS:
          serviceImpl.providers((akash.provider.v1beta2.QueryOuterClass.QueryProvidersRequest) request,
              (io.grpc.stub.StreamObserver<akash.provider.v1beta2.QueryOuterClass.QueryProvidersResponse>) responseObserver);
          break;
        case METHODID_PROVIDER:
          serviceImpl.provider((akash.provider.v1beta2.QueryOuterClass.QueryProviderRequest) request,
              (io.grpc.stub.StreamObserver<akash.provider.v1beta2.QueryOuterClass.QueryProviderResponse>) responseObserver);
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
      return akash.provider.v1beta2.QueryOuterClass.getDescriptor();
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
              .addMethod(getProvidersMethod())
              .addMethod(getProviderMethod())
              .build();
        }
      }
    }
    return result;
  }
}
