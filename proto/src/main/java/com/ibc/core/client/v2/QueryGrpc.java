package com.ibc.core.client.v2;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query provides defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: ibc/core/client/v2/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "ibc.core.client.v2.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoRequest,
      com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoResponse> getCounterpartyInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CounterpartyInfo",
      requestType = com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoRequest.class,
      responseType = com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoRequest,
      com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoResponse> getCounterpartyInfoMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoRequest, com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoResponse> getCounterpartyInfoMethod;
    if ((getCounterpartyInfoMethod = QueryGrpc.getCounterpartyInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCounterpartyInfoMethod = QueryGrpc.getCounterpartyInfoMethod) == null) {
          QueryGrpc.getCounterpartyInfoMethod = getCounterpartyInfoMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoRequest, com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CounterpartyInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CounterpartyInfo"))
              .build();
        }
      }
    }
    return getCounterpartyInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v2.QueryProto.QueryConfigRequest,
      com.ibc.core.client.v2.QueryProto.QueryConfigResponse> getConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Config",
      requestType = com.ibc.core.client.v2.QueryProto.QueryConfigRequest.class,
      responseType = com.ibc.core.client.v2.QueryProto.QueryConfigResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v2.QueryProto.QueryConfigRequest,
      com.ibc.core.client.v2.QueryProto.QueryConfigResponse> getConfigMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v2.QueryProto.QueryConfigRequest, com.ibc.core.client.v2.QueryProto.QueryConfigResponse> getConfigMethod;
    if ((getConfigMethod = QueryGrpc.getConfigMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getConfigMethod = QueryGrpc.getConfigMethod) == null) {
          QueryGrpc.getConfigMethod = getConfigMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v2.QueryProto.QueryConfigRequest, com.ibc.core.client.v2.QueryProto.QueryConfigResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Config"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v2.QueryProto.QueryConfigRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v2.QueryProto.QueryConfigResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Config"))
              .build();
        }
      }
    }
    return getConfigMethod;
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
   * Query provides defines the gRPC querier service
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * CounterpartyInfo queries an IBC light counter party info.
     * </pre>
     */
    default void counterpartyInfo(com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCounterpartyInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * Config queries the IBC client v2 configuration for a given client.
     * </pre>
     */
    default void config(com.ibc.core.client.v2.QueryProto.QueryConfigRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v2.QueryProto.QueryConfigResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConfigMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query provides defines the gRPC querier service
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
   * Query provides defines the gRPC querier service
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
     * CounterpartyInfo queries an IBC light counter party info.
     * </pre>
     */
    public void counterpartyInfo(com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCounterpartyInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Config queries the IBC client v2 configuration for a given client.
     * </pre>
     */
    public void config(com.ibc.core.client.v2.QueryProto.QueryConfigRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v2.QueryProto.QueryConfigResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConfigMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query provides defines the gRPC querier service
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
     * CounterpartyInfo queries an IBC light counter party info.
     * </pre>
     */
    public com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoResponse counterpartyInfo(com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCounterpartyInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Config queries the IBC client v2 configuration for a given client.
     * </pre>
     */
    public com.ibc.core.client.v2.QueryProto.QueryConfigResponse config(com.ibc.core.client.v2.QueryProto.QueryConfigRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConfigMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query provides defines the gRPC querier service
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
     * CounterpartyInfo queries an IBC light counter party info.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoResponse> counterpartyInfo(
        com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCounterpartyInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Config queries the IBC client v2 configuration for a given client.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v2.QueryProto.QueryConfigResponse> config(
        com.ibc.core.client.v2.QueryProto.QueryConfigRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConfigMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_COUNTERPARTY_INFO = 0;
  private static final int METHODID_CONFIG = 1;

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
        case METHODID_COUNTERPARTY_INFO:
          serviceImpl.counterpartyInfo((com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoResponse>) responseObserver);
          break;
        case METHODID_CONFIG:
          serviceImpl.config((com.ibc.core.client.v2.QueryProto.QueryConfigRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v2.QueryProto.QueryConfigResponse>) responseObserver);
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
          getCounterpartyInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoRequest,
              com.ibc.core.client.v2.QueryProto.QueryCounterpartyInfoResponse>(
                service, METHODID_COUNTERPARTY_INFO)))
        .addMethod(
          getConfigMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v2.QueryProto.QueryConfigRequest,
              com.ibc.core.client.v2.QueryProto.QueryConfigResponse>(
                service, METHODID_CONFIG)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ibc.core.client.v2.QueryProto.getDescriptor();
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
              .addMethod(getCounterpartyInfoMethod())
              .addMethod(getConfigMethod())
              .build();
        }
      }
    }
    return result;
  }
}
