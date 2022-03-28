package comdex.vault.v1beta1;

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
    comments = "Source: comdex/vault/v1beta1/querier.proto")
public final class QueryServiceGrpc {

  private QueryServiceGrpc() {}

  public static final String SERVICE_NAME = "comdex.vault.v1beta1.QueryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<comdex.vault.v1beta1.Querier.QueryVaultRequest,
      comdex.vault.v1beta1.Querier.QueryVaultResponse> getQueryVaultMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryVault",
      requestType = comdex.vault.v1beta1.Querier.QueryVaultRequest.class,
      responseType = comdex.vault.v1beta1.Querier.QueryVaultResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.vault.v1beta1.Querier.QueryVaultRequest,
      comdex.vault.v1beta1.Querier.QueryVaultResponse> getQueryVaultMethod() {
    io.grpc.MethodDescriptor<comdex.vault.v1beta1.Querier.QueryVaultRequest, comdex.vault.v1beta1.Querier.QueryVaultResponse> getQueryVaultMethod;
    if ((getQueryVaultMethod = QueryServiceGrpc.getQueryVaultMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryVaultMethod = QueryServiceGrpc.getQueryVaultMethod) == null) {
          QueryServiceGrpc.getQueryVaultMethod = getQueryVaultMethod =
              io.grpc.MethodDescriptor.<comdex.vault.v1beta1.Querier.QueryVaultRequest, comdex.vault.v1beta1.Querier.QueryVaultResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryVault"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Querier.QueryVaultRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Querier.QueryVaultResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryVault"))
              .build();
        }
      }
    }
    return getQueryVaultMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.vault.v1beta1.Querier.QueryVaultsRequest,
      comdex.vault.v1beta1.Querier.QueryVaultsResponse> getQueryVaultsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryVaults",
      requestType = comdex.vault.v1beta1.Querier.QueryVaultsRequest.class,
      responseType = comdex.vault.v1beta1.Querier.QueryVaultsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.vault.v1beta1.Querier.QueryVaultsRequest,
      comdex.vault.v1beta1.Querier.QueryVaultsResponse> getQueryVaultsMethod() {
    io.grpc.MethodDescriptor<comdex.vault.v1beta1.Querier.QueryVaultsRequest, comdex.vault.v1beta1.Querier.QueryVaultsResponse> getQueryVaultsMethod;
    if ((getQueryVaultsMethod = QueryServiceGrpc.getQueryVaultsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryVaultsMethod = QueryServiceGrpc.getQueryVaultsMethod) == null) {
          QueryServiceGrpc.getQueryVaultsMethod = getQueryVaultsMethod =
              io.grpc.MethodDescriptor.<comdex.vault.v1beta1.Querier.QueryVaultsRequest, comdex.vault.v1beta1.Querier.QueryVaultsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryVaults"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Querier.QueryVaultsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Querier.QueryVaultsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryVaults"))
              .build();
        }
      }
    }
    return getQueryVaultsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.vault.v1beta1.Querier.QueryAllVaultsRequest,
      comdex.vault.v1beta1.Querier.QueryAllVaultsResponse> getQueryAllVaultsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryAllVaults",
      requestType = comdex.vault.v1beta1.Querier.QueryAllVaultsRequest.class,
      responseType = comdex.vault.v1beta1.Querier.QueryAllVaultsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.vault.v1beta1.Querier.QueryAllVaultsRequest,
      comdex.vault.v1beta1.Querier.QueryAllVaultsResponse> getQueryAllVaultsMethod() {
    io.grpc.MethodDescriptor<comdex.vault.v1beta1.Querier.QueryAllVaultsRequest, comdex.vault.v1beta1.Querier.QueryAllVaultsResponse> getQueryAllVaultsMethod;
    if ((getQueryAllVaultsMethod = QueryServiceGrpc.getQueryAllVaultsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryAllVaultsMethod = QueryServiceGrpc.getQueryAllVaultsMethod) == null) {
          QueryServiceGrpc.getQueryAllVaultsMethod = getQueryAllVaultsMethod =
              io.grpc.MethodDescriptor.<comdex.vault.v1beta1.Querier.QueryAllVaultsRequest, comdex.vault.v1beta1.Querier.QueryAllVaultsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryAllVaults"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Querier.QueryAllVaultsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Querier.QueryAllVaultsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryAllVaults"))
              .build();
        }
      }
    }
    return getQueryAllVaultsMethod;
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
    public void queryVault(comdex.vault.v1beta1.Querier.QueryVaultRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Querier.QueryVaultResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryVaultMethod(), responseObserver);
    }

    /**
     */
    public void queryVaults(comdex.vault.v1beta1.Querier.QueryVaultsRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Querier.QueryVaultsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryVaultsMethod(), responseObserver);
    }

    /**
     */
    public void queryAllVaults(comdex.vault.v1beta1.Querier.QueryAllVaultsRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Querier.QueryAllVaultsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryAllVaultsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getQueryVaultMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.vault.v1beta1.Querier.QueryVaultRequest,
                comdex.vault.v1beta1.Querier.QueryVaultResponse>(
                  this, METHODID_QUERY_VAULT)))
          .addMethod(
            getQueryVaultsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.vault.v1beta1.Querier.QueryVaultsRequest,
                comdex.vault.v1beta1.Querier.QueryVaultsResponse>(
                  this, METHODID_QUERY_VAULTS)))
          .addMethod(
            getQueryAllVaultsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.vault.v1beta1.Querier.QueryAllVaultsRequest,
                comdex.vault.v1beta1.Querier.QueryAllVaultsResponse>(
                  this, METHODID_QUERY_ALL_VAULTS)))
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
    public void queryVault(comdex.vault.v1beta1.Querier.QueryVaultRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Querier.QueryVaultResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryVaultMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryVaults(comdex.vault.v1beta1.Querier.QueryVaultsRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Querier.QueryVaultsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryVaultsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryAllVaults(comdex.vault.v1beta1.Querier.QueryAllVaultsRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Querier.QueryAllVaultsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryAllVaultsMethod(), getCallOptions()), request, responseObserver);
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
    public comdex.vault.v1beta1.Querier.QueryVaultResponse queryVault(comdex.vault.v1beta1.Querier.QueryVaultRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryVaultMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.vault.v1beta1.Querier.QueryVaultsResponse queryVaults(comdex.vault.v1beta1.Querier.QueryVaultsRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryVaultsMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.vault.v1beta1.Querier.QueryAllVaultsResponse queryAllVaults(comdex.vault.v1beta1.Querier.QueryAllVaultsRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryAllVaultsMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<comdex.vault.v1beta1.Querier.QueryVaultResponse> queryVault(
        comdex.vault.v1beta1.Querier.QueryVaultRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryVaultMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.vault.v1beta1.Querier.QueryVaultsResponse> queryVaults(
        comdex.vault.v1beta1.Querier.QueryVaultsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryVaultsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.vault.v1beta1.Querier.QueryAllVaultsResponse> queryAllVaults(
        comdex.vault.v1beta1.Querier.QueryAllVaultsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryAllVaultsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_VAULT = 0;
  private static final int METHODID_QUERY_VAULTS = 1;
  private static final int METHODID_QUERY_ALL_VAULTS = 2;

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
        case METHODID_QUERY_VAULT:
          serviceImpl.queryVault((comdex.vault.v1beta1.Querier.QueryVaultRequest) request,
              (io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Querier.QueryVaultResponse>) responseObserver);
          break;
        case METHODID_QUERY_VAULTS:
          serviceImpl.queryVaults((comdex.vault.v1beta1.Querier.QueryVaultsRequest) request,
              (io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Querier.QueryVaultsResponse>) responseObserver);
          break;
        case METHODID_QUERY_ALL_VAULTS:
          serviceImpl.queryAllVaults((comdex.vault.v1beta1.Querier.QueryAllVaultsRequest) request,
              (io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Querier.QueryAllVaultsResponse>) responseObserver);
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
      return comdex.vault.v1beta1.Querier.getDescriptor();
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
              .addMethod(getQueryVaultMethod())
              .addMethod(getQueryVaultsMethod())
              .addMethod(getQueryAllVaultsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
