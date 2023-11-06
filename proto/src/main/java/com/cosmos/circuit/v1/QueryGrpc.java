package com.cosmos.circuit.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the circuit gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmos/circuit/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "cosmos.circuit.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmos.circuit.v1.QueryProto.QueryAccountRequest,
      com.cosmos.circuit.v1.QueryProto.AccountResponse> getAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Account",
      requestType = com.cosmos.circuit.v1.QueryProto.QueryAccountRequest.class,
      responseType = com.cosmos.circuit.v1.QueryProto.AccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.circuit.v1.QueryProto.QueryAccountRequest,
      com.cosmos.circuit.v1.QueryProto.AccountResponse> getAccountMethod() {
    io.grpc.MethodDescriptor<com.cosmos.circuit.v1.QueryProto.QueryAccountRequest, com.cosmos.circuit.v1.QueryProto.AccountResponse> getAccountMethod;
    if ((getAccountMethod = QueryGrpc.getAccountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountMethod = QueryGrpc.getAccountMethod) == null) {
          QueryGrpc.getAccountMethod = getAccountMethod =
              io.grpc.MethodDescriptor.<com.cosmos.circuit.v1.QueryProto.QueryAccountRequest, com.cosmos.circuit.v1.QueryProto.AccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Account"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.circuit.v1.QueryProto.QueryAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.circuit.v1.QueryProto.AccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Account"))
              .build();
        }
      }
    }
    return getAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.circuit.v1.QueryProto.QueryAccountsRequest,
      com.cosmos.circuit.v1.QueryProto.AccountsResponse> getAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Accounts",
      requestType = com.cosmos.circuit.v1.QueryProto.QueryAccountsRequest.class,
      responseType = com.cosmos.circuit.v1.QueryProto.AccountsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.circuit.v1.QueryProto.QueryAccountsRequest,
      com.cosmos.circuit.v1.QueryProto.AccountsResponse> getAccountsMethod() {
    io.grpc.MethodDescriptor<com.cosmos.circuit.v1.QueryProto.QueryAccountsRequest, com.cosmos.circuit.v1.QueryProto.AccountsResponse> getAccountsMethod;
    if ((getAccountsMethod = QueryGrpc.getAccountsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountsMethod = QueryGrpc.getAccountsMethod) == null) {
          QueryGrpc.getAccountsMethod = getAccountsMethod =
              io.grpc.MethodDescriptor.<com.cosmos.circuit.v1.QueryProto.QueryAccountsRequest, com.cosmos.circuit.v1.QueryProto.AccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Accounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.circuit.v1.QueryProto.QueryAccountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.circuit.v1.QueryProto.AccountsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Accounts"))
              .build();
        }
      }
    }
    return getAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.circuit.v1.QueryProto.QueryDisabledListRequest,
      com.cosmos.circuit.v1.QueryProto.DisabledListResponse> getDisabledListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DisabledList",
      requestType = com.cosmos.circuit.v1.QueryProto.QueryDisabledListRequest.class,
      responseType = com.cosmos.circuit.v1.QueryProto.DisabledListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.circuit.v1.QueryProto.QueryDisabledListRequest,
      com.cosmos.circuit.v1.QueryProto.DisabledListResponse> getDisabledListMethod() {
    io.grpc.MethodDescriptor<com.cosmos.circuit.v1.QueryProto.QueryDisabledListRequest, com.cosmos.circuit.v1.QueryProto.DisabledListResponse> getDisabledListMethod;
    if ((getDisabledListMethod = QueryGrpc.getDisabledListMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDisabledListMethod = QueryGrpc.getDisabledListMethod) == null) {
          QueryGrpc.getDisabledListMethod = getDisabledListMethod =
              io.grpc.MethodDescriptor.<com.cosmos.circuit.v1.QueryProto.QueryDisabledListRequest, com.cosmos.circuit.v1.QueryProto.DisabledListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DisabledList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.circuit.v1.QueryProto.QueryDisabledListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.circuit.v1.QueryProto.DisabledListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DisabledList"))
              .build();
        }
      }
    }
    return getDisabledListMethod;
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
   * Query defines the circuit gRPC querier service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Account returns account permissions.
     * </pre>
     */
    default void account(com.cosmos.circuit.v1.QueryProto.QueryAccountRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.circuit.v1.QueryProto.AccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * Account returns account permissions.
     * </pre>
     */
    default void accounts(com.cosmos.circuit.v1.QueryProto.QueryAccountsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.circuit.v1.QueryProto.AccountsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAccountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * DisabledList returns a list of disabled message urls
     * </pre>
     */
    default void disabledList(com.cosmos.circuit.v1.QueryProto.QueryDisabledListRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.circuit.v1.QueryProto.DisabledListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDisabledListMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the circuit gRPC querier service.
   * </pre>
   */
  public static abstract class QueryImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return QueryGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Query.
   * <pre>
   * Query defines the circuit gRPC querier service.
   * </pre>
   */
  public static final class QueryStub
      extends io.grpc.stub.AbstractAsyncStub<QueryStub> {
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
     * Account returns account permissions.
     * </pre>
     */
    public void account(com.cosmos.circuit.v1.QueryProto.QueryAccountRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.circuit.v1.QueryProto.AccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Account returns account permissions.
     * </pre>
     */
    public void accounts(com.cosmos.circuit.v1.QueryProto.QueryAccountsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.circuit.v1.QueryProto.AccountsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DisabledList returns a list of disabled message urls
     * </pre>
     */
    public void disabledList(com.cosmos.circuit.v1.QueryProto.QueryDisabledListRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.circuit.v1.QueryProto.DisabledListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDisabledListMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the circuit gRPC querier service.
   * </pre>
   */
  public static final class QueryBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<QueryBlockingStub> {
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
     * Account returns account permissions.
     * </pre>
     */
    public com.cosmos.circuit.v1.QueryProto.AccountResponse account(com.cosmos.circuit.v1.QueryProto.QueryAccountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Account returns account permissions.
     * </pre>
     */
    public com.cosmos.circuit.v1.QueryProto.AccountsResponse accounts(com.cosmos.circuit.v1.QueryProto.QueryAccountsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAccountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DisabledList returns a list of disabled message urls
     * </pre>
     */
    public com.cosmos.circuit.v1.QueryProto.DisabledListResponse disabledList(com.cosmos.circuit.v1.QueryProto.QueryDisabledListRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDisabledListMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the circuit gRPC querier service.
   * </pre>
   */
  public static final class QueryFutureStub
      extends io.grpc.stub.AbstractFutureStub<QueryFutureStub> {
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
     * Account returns account permissions.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.circuit.v1.QueryProto.AccountResponse> account(
        com.cosmos.circuit.v1.QueryProto.QueryAccountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Account returns account permissions.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.circuit.v1.QueryProto.AccountsResponse> accounts(
        com.cosmos.circuit.v1.QueryProto.QueryAccountsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DisabledList returns a list of disabled message urls
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.circuit.v1.QueryProto.DisabledListResponse> disabledList(
        com.cosmos.circuit.v1.QueryProto.QueryDisabledListRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDisabledListMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ACCOUNT = 0;
  private static final int METHODID_ACCOUNTS = 1;
  private static final int METHODID_DISABLED_LIST = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ACCOUNT:
          serviceImpl.account((com.cosmos.circuit.v1.QueryProto.QueryAccountRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.circuit.v1.QueryProto.AccountResponse>) responseObserver);
          break;
        case METHODID_ACCOUNTS:
          serviceImpl.accounts((com.cosmos.circuit.v1.QueryProto.QueryAccountsRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.circuit.v1.QueryProto.AccountsResponse>) responseObserver);
          break;
        case METHODID_DISABLED_LIST:
          serviceImpl.disabledList((com.cosmos.circuit.v1.QueryProto.QueryDisabledListRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.circuit.v1.QueryProto.DisabledListResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.circuit.v1.QueryProto.QueryAccountRequest,
              com.cosmos.circuit.v1.QueryProto.AccountResponse>(
                service, METHODID_ACCOUNT)))
        .addMethod(
          getAccountsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.circuit.v1.QueryProto.QueryAccountsRequest,
              com.cosmos.circuit.v1.QueryProto.AccountsResponse>(
                service, METHODID_ACCOUNTS)))
        .addMethod(
          getDisabledListMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.circuit.v1.QueryProto.QueryDisabledListRequest,
              com.cosmos.circuit.v1.QueryProto.DisabledListResponse>(
                service, METHODID_DISABLED_LIST)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmos.circuit.v1.QueryProto.getDescriptor();
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
              .addMethod(getAccountMethod())
              .addMethod(getAccountsMethod())
              .addMethod(getDisabledListMethod())
              .build();
        }
      }
    }
    return result;
  }
}
