package com.cosmos.accounts.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the Query service for the x/accounts module.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmos/accounts/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "cosmos.accounts.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmos.accounts.v1.QueryProto.AccountQueryRequest,
      com.cosmos.accounts.v1.QueryProto.AccountQueryResponse> getAccountQueryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AccountQuery",
      requestType = com.cosmos.accounts.v1.QueryProto.AccountQueryRequest.class,
      responseType = com.cosmos.accounts.v1.QueryProto.AccountQueryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.accounts.v1.QueryProto.AccountQueryRequest,
      com.cosmos.accounts.v1.QueryProto.AccountQueryResponse> getAccountQueryMethod() {
    io.grpc.MethodDescriptor<com.cosmos.accounts.v1.QueryProto.AccountQueryRequest, com.cosmos.accounts.v1.QueryProto.AccountQueryResponse> getAccountQueryMethod;
    if ((getAccountQueryMethod = QueryGrpc.getAccountQueryMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountQueryMethod = QueryGrpc.getAccountQueryMethod) == null) {
          QueryGrpc.getAccountQueryMethod = getAccountQueryMethod =
              io.grpc.MethodDescriptor.<com.cosmos.accounts.v1.QueryProto.AccountQueryRequest, com.cosmos.accounts.v1.QueryProto.AccountQueryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AccountQuery"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.accounts.v1.QueryProto.AccountQueryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.accounts.v1.QueryProto.AccountQueryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AccountQuery"))
              .build();
        }
      }
    }
    return getAccountQueryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.accounts.v1.QueryProto.SchemaRequest,
      com.cosmos.accounts.v1.QueryProto.SchemaResponse> getSchemaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Schema",
      requestType = com.cosmos.accounts.v1.QueryProto.SchemaRequest.class,
      responseType = com.cosmos.accounts.v1.QueryProto.SchemaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.accounts.v1.QueryProto.SchemaRequest,
      com.cosmos.accounts.v1.QueryProto.SchemaResponse> getSchemaMethod() {
    io.grpc.MethodDescriptor<com.cosmos.accounts.v1.QueryProto.SchemaRequest, com.cosmos.accounts.v1.QueryProto.SchemaResponse> getSchemaMethod;
    if ((getSchemaMethod = QueryGrpc.getSchemaMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSchemaMethod = QueryGrpc.getSchemaMethod) == null) {
          QueryGrpc.getSchemaMethod = getSchemaMethod =
              io.grpc.MethodDescriptor.<com.cosmos.accounts.v1.QueryProto.SchemaRequest, com.cosmos.accounts.v1.QueryProto.SchemaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Schema"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.accounts.v1.QueryProto.SchemaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.accounts.v1.QueryProto.SchemaResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Schema"))
              .build();
        }
      }
    }
    return getSchemaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.accounts.v1.QueryProto.AccountTypeRequest,
      com.cosmos.accounts.v1.QueryProto.AccountTypeResponse> getAccountTypeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AccountType",
      requestType = com.cosmos.accounts.v1.QueryProto.AccountTypeRequest.class,
      responseType = com.cosmos.accounts.v1.QueryProto.AccountTypeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.accounts.v1.QueryProto.AccountTypeRequest,
      com.cosmos.accounts.v1.QueryProto.AccountTypeResponse> getAccountTypeMethod() {
    io.grpc.MethodDescriptor<com.cosmos.accounts.v1.QueryProto.AccountTypeRequest, com.cosmos.accounts.v1.QueryProto.AccountTypeResponse> getAccountTypeMethod;
    if ((getAccountTypeMethod = QueryGrpc.getAccountTypeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountTypeMethod = QueryGrpc.getAccountTypeMethod) == null) {
          QueryGrpc.getAccountTypeMethod = getAccountTypeMethod =
              io.grpc.MethodDescriptor.<com.cosmos.accounts.v1.QueryProto.AccountTypeRequest, com.cosmos.accounts.v1.QueryProto.AccountTypeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AccountType"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.accounts.v1.QueryProto.AccountTypeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.accounts.v1.QueryProto.AccountTypeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AccountType"))
              .build();
        }
      }
    }
    return getAccountTypeMethod;
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
   * Query defines the Query service for the x/accounts module.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * AccountQuery runs an account query.
     * </pre>
     */
    default void accountQuery(com.cosmos.accounts.v1.QueryProto.AccountQueryRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.accounts.v1.QueryProto.AccountQueryResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAccountQueryMethod(), responseObserver);
    }

    /**
     * <pre>
     * Schema returns an x/account schema. Unstable.
     * </pre>
     */
    default void schema(com.cosmos.accounts.v1.QueryProto.SchemaRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.accounts.v1.QueryProto.SchemaResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSchemaMethod(), responseObserver);
    }

    /**
     * <pre>
     * AccountType returns the account type for an address.
     * </pre>
     */
    default void accountType(com.cosmos.accounts.v1.QueryProto.AccountTypeRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.accounts.v1.QueryProto.AccountTypeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAccountTypeMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the Query service for the x/accounts module.
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
   * Query defines the Query service for the x/accounts module.
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
     * AccountQuery runs an account query.
     * </pre>
     */
    public void accountQuery(com.cosmos.accounts.v1.QueryProto.AccountQueryRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.accounts.v1.QueryProto.AccountQueryResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAccountQueryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Schema returns an x/account schema. Unstable.
     * </pre>
     */
    public void schema(com.cosmos.accounts.v1.QueryProto.SchemaRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.accounts.v1.QueryProto.SchemaResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSchemaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AccountType returns the account type for an address.
     * </pre>
     */
    public void accountType(com.cosmos.accounts.v1.QueryProto.AccountTypeRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.accounts.v1.QueryProto.AccountTypeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAccountTypeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the Query service for the x/accounts module.
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
     * AccountQuery runs an account query.
     * </pre>
     */
    public com.cosmos.accounts.v1.QueryProto.AccountQueryResponse accountQuery(com.cosmos.accounts.v1.QueryProto.AccountQueryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAccountQueryMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Schema returns an x/account schema. Unstable.
     * </pre>
     */
    public com.cosmos.accounts.v1.QueryProto.SchemaResponse schema(com.cosmos.accounts.v1.QueryProto.SchemaRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSchemaMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AccountType returns the account type for an address.
     * </pre>
     */
    public com.cosmos.accounts.v1.QueryProto.AccountTypeResponse accountType(com.cosmos.accounts.v1.QueryProto.AccountTypeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAccountTypeMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the Query service for the x/accounts module.
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
     * AccountQuery runs an account query.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.accounts.v1.QueryProto.AccountQueryResponse> accountQuery(
        com.cosmos.accounts.v1.QueryProto.AccountQueryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAccountQueryMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Schema returns an x/account schema. Unstable.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.accounts.v1.QueryProto.SchemaResponse> schema(
        com.cosmos.accounts.v1.QueryProto.SchemaRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSchemaMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AccountType returns the account type for an address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.accounts.v1.QueryProto.AccountTypeResponse> accountType(
        com.cosmos.accounts.v1.QueryProto.AccountTypeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAccountTypeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ACCOUNT_QUERY = 0;
  private static final int METHODID_SCHEMA = 1;
  private static final int METHODID_ACCOUNT_TYPE = 2;

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
        case METHODID_ACCOUNT_QUERY:
          serviceImpl.accountQuery((com.cosmos.accounts.v1.QueryProto.AccountQueryRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.accounts.v1.QueryProto.AccountQueryResponse>) responseObserver);
          break;
        case METHODID_SCHEMA:
          serviceImpl.schema((com.cosmos.accounts.v1.QueryProto.SchemaRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.accounts.v1.QueryProto.SchemaResponse>) responseObserver);
          break;
        case METHODID_ACCOUNT_TYPE:
          serviceImpl.accountType((com.cosmos.accounts.v1.QueryProto.AccountTypeRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.accounts.v1.QueryProto.AccountTypeResponse>) responseObserver);
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
          getAccountQueryMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.accounts.v1.QueryProto.AccountQueryRequest,
              com.cosmos.accounts.v1.QueryProto.AccountQueryResponse>(
                service, METHODID_ACCOUNT_QUERY)))
        .addMethod(
          getSchemaMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.accounts.v1.QueryProto.SchemaRequest,
              com.cosmos.accounts.v1.QueryProto.SchemaResponse>(
                service, METHODID_SCHEMA)))
        .addMethod(
          getAccountTypeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.accounts.v1.QueryProto.AccountTypeRequest,
              com.cosmos.accounts.v1.QueryProto.AccountTypeResponse>(
                service, METHODID_ACCOUNT_TYPE)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmos.accounts.v1.QueryProto.getDescriptor();
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
              .addMethod(getAccountQueryMethod())
              .addMethod(getSchemaMethod())
              .addMethod(getAccountTypeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
