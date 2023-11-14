package com.kava.liquid.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service for liquid module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: kava/liquid/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "kava.liquid.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceRequest,
      com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceResponse> getDelegatedBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegatedBalance",
      requestType = com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceRequest.class,
      responseType = com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceRequest,
      com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceResponse> getDelegatedBalanceMethod() {
    io.grpc.MethodDescriptor<com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceRequest, com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceResponse> getDelegatedBalanceMethod;
    if ((getDelegatedBalanceMethod = QueryGrpc.getDelegatedBalanceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegatedBalanceMethod = QueryGrpc.getDelegatedBalanceMethod) == null) {
          QueryGrpc.getDelegatedBalanceMethod = getDelegatedBalanceMethod =
              io.grpc.MethodDescriptor.<com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceRequest, com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegatedBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegatedBalance"))
              .build();
        }
      }
    }
    return getDelegatedBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyRequest,
      com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyResponse> getTotalSupplyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalSupply",
      requestType = com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyRequest.class,
      responseType = com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyRequest,
      com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyResponse> getTotalSupplyMethod() {
    io.grpc.MethodDescriptor<com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyRequest, com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyResponse> getTotalSupplyMethod;
    if ((getTotalSupplyMethod = QueryGrpc.getTotalSupplyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalSupplyMethod = QueryGrpc.getTotalSupplyMethod) == null) {
          QueryGrpc.getTotalSupplyMethod = getTotalSupplyMethod =
              io.grpc.MethodDescriptor.<com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyRequest, com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalSupply"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalSupply"))
              .build();
        }
      }
    }
    return getTotalSupplyMethod;
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
   * Query defines the gRPC querier service for liquid module
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * DelegatedBalance returns an account's vesting and vested coins currently delegated to validators.
     * It ignores coins in unbonding delegations.
     * </pre>
     */
    default void delegatedBalance(com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceRequest request,
        io.grpc.stub.StreamObserver<com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDelegatedBalanceMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalSupply returns the total sum of all coins currently locked into the liquid module.
     * </pre>
     */
    default void totalSupply(com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyRequest request,
        io.grpc.stub.StreamObserver<com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTotalSupplyMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service for liquid module
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
   * Query defines the gRPC querier service for liquid module
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
     * DelegatedBalance returns an account's vesting and vested coins currently delegated to validators.
     * It ignores coins in unbonding delegations.
     * </pre>
     */
    public void delegatedBalance(com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceRequest request,
        io.grpc.stub.StreamObserver<com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDelegatedBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalSupply returns the total sum of all coins currently locked into the liquid module.
     * </pre>
     */
    public void totalSupply(com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyRequest request,
        io.grpc.stub.StreamObserver<com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTotalSupplyMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for liquid module
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
     * DelegatedBalance returns an account's vesting and vested coins currently delegated to validators.
     * It ignores coins in unbonding delegations.
     * </pre>
     */
    public com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceResponse delegatedBalance(com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDelegatedBalanceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalSupply returns the total sum of all coins currently locked into the liquid module.
     * </pre>
     */
    public com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyResponse totalSupply(com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTotalSupplyMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for liquid module
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
     * DelegatedBalance returns an account's vesting and vested coins currently delegated to validators.
     * It ignores coins in unbonding delegations.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceResponse> delegatedBalance(
        com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDelegatedBalanceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalSupply returns the total sum of all coins currently locked into the liquid module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyResponse> totalSupply(
        com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTotalSupplyMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DELEGATED_BALANCE = 0;
  private static final int METHODID_TOTAL_SUPPLY = 1;

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
        case METHODID_DELEGATED_BALANCE:
          serviceImpl.delegatedBalance((com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceResponse>) responseObserver);
          break;
        case METHODID_TOTAL_SUPPLY:
          serviceImpl.totalSupply((com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyResponse>) responseObserver);
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
          getDelegatedBalanceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceRequest,
              com.kava.liquid.v1beta1.QueryProto.QueryDelegatedBalanceResponse>(
                service, METHODID_DELEGATED_BALANCE)))
        .addMethod(
          getTotalSupplyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyRequest,
              com.kava.liquid.v1beta1.QueryProto.QueryTotalSupplyResponse>(
                service, METHODID_TOTAL_SUPPLY)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kava.liquid.v1beta1.QueryProto.getDescriptor();
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
              .addMethod(getDelegatedBalanceMethod())
              .addMethod(getTotalSupplyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
