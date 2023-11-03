package com.injective.insurance.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: injective/insurance/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "injective.insurance.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsRequest,
      com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsResponse> getInsuranceParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InsuranceParams",
      requestType = com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsRequest.class,
      responseType = com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsRequest,
      com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsResponse> getInsuranceParamsMethod() {
    io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsRequest, com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsResponse> getInsuranceParamsMethod;
    if ((getInsuranceParamsMethod = QueryGrpc.getInsuranceParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getInsuranceParamsMethod = QueryGrpc.getInsuranceParamsMethod) == null) {
          QueryGrpc.getInsuranceParamsMethod = getInsuranceParamsMethod =
              io.grpc.MethodDescriptor.<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsRequest, com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InsuranceParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("InsuranceParams"))
              .build();
        }
      }
    }
    return getInsuranceParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundRequest,
      com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundResponse> getInsuranceFundMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InsuranceFund",
      requestType = com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundRequest.class,
      responseType = com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundRequest,
      com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundResponse> getInsuranceFundMethod() {
    io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundRequest, com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundResponse> getInsuranceFundMethod;
    if ((getInsuranceFundMethod = QueryGrpc.getInsuranceFundMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getInsuranceFundMethod = QueryGrpc.getInsuranceFundMethod) == null) {
          QueryGrpc.getInsuranceFundMethod = getInsuranceFundMethod =
              io.grpc.MethodDescriptor.<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundRequest, com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InsuranceFund"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("InsuranceFund"))
              .build();
        }
      }
    }
    return getInsuranceFundMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsRequest,
      com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsResponse> getInsuranceFundsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InsuranceFunds",
      requestType = com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsRequest.class,
      responseType = com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsRequest,
      com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsResponse> getInsuranceFundsMethod() {
    io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsRequest, com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsResponse> getInsuranceFundsMethod;
    if ((getInsuranceFundsMethod = QueryGrpc.getInsuranceFundsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getInsuranceFundsMethod = QueryGrpc.getInsuranceFundsMethod) == null) {
          QueryGrpc.getInsuranceFundsMethod = getInsuranceFundsMethod =
              io.grpc.MethodDescriptor.<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsRequest, com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InsuranceFunds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("InsuranceFunds"))
              .build();
        }
      }
    }
    return getInsuranceFundsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsRequest,
      com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsResponse> getEstimatedRedemptionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EstimatedRedemptions",
      requestType = com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsRequest.class,
      responseType = com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsRequest,
      com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsResponse> getEstimatedRedemptionsMethod() {
    io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsRequest, com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsResponse> getEstimatedRedemptionsMethod;
    if ((getEstimatedRedemptionsMethod = QueryGrpc.getEstimatedRedemptionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEstimatedRedemptionsMethod = QueryGrpc.getEstimatedRedemptionsMethod) == null) {
          QueryGrpc.getEstimatedRedemptionsMethod = getEstimatedRedemptionsMethod =
              io.grpc.MethodDescriptor.<com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsRequest, com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EstimatedRedemptions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EstimatedRedemptions"))
              .build();
        }
      }
    }
    return getEstimatedRedemptionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsRequest,
      com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsResponse> getPendingRedemptionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PendingRedemptions",
      requestType = com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsRequest.class,
      responseType = com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsRequest,
      com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsResponse> getPendingRedemptionsMethod() {
    io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsRequest, com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsResponse> getPendingRedemptionsMethod;
    if ((getPendingRedemptionsMethod = QueryGrpc.getPendingRedemptionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPendingRedemptionsMethod = QueryGrpc.getPendingRedemptionsMethod) == null) {
          QueryGrpc.getPendingRedemptionsMethod = getPendingRedemptionsMethod =
              io.grpc.MethodDescriptor.<com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsRequest, com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PendingRedemptions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PendingRedemptions"))
              .build();
        }
      }
    }
    return getPendingRedemptionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryModuleStateRequest,
      com.injective.insurance.v1beta1.QueryProto.QueryModuleStateResponse> getInsuranceModuleStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InsuranceModuleState",
      requestType = com.injective.insurance.v1beta1.QueryProto.QueryModuleStateRequest.class,
      responseType = com.injective.insurance.v1beta1.QueryProto.QueryModuleStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryModuleStateRequest,
      com.injective.insurance.v1beta1.QueryProto.QueryModuleStateResponse> getInsuranceModuleStateMethod() {
    io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.QueryProto.QueryModuleStateRequest, com.injective.insurance.v1beta1.QueryProto.QueryModuleStateResponse> getInsuranceModuleStateMethod;
    if ((getInsuranceModuleStateMethod = QueryGrpc.getInsuranceModuleStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getInsuranceModuleStateMethod = QueryGrpc.getInsuranceModuleStateMethod) == null) {
          QueryGrpc.getInsuranceModuleStateMethod = getInsuranceModuleStateMethod =
              io.grpc.MethodDescriptor.<com.injective.insurance.v1beta1.QueryProto.QueryModuleStateRequest, com.injective.insurance.v1beta1.QueryProto.QueryModuleStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InsuranceModuleState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.QueryProto.QueryModuleStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.QueryProto.QueryModuleStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("InsuranceModuleState"))
              .build();
        }
      }
    }
    return getInsuranceModuleStateMethod;
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
   * Query defines the gRPC querier service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Retrieves insurance params
     * </pre>
     */
    default void insuranceParams(com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsRequest request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInsuranceParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves individual insurance fund information from market id
     * </pre>
     */
    default void insuranceFund(com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundRequest request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInsuranceFundMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves all insurance funds
     * </pre>
     */
    default void insuranceFunds(com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsRequest request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInsuranceFundsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrives the value of insurance fund share token at current price (not
     * pending redemption)
     * </pre>
     */
    default void estimatedRedemptions(com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsRequest request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEstimatedRedemptionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves pending redemptions' share token at current price
     * </pre>
     */
    default void pendingRedemptions(com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsRequest request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPendingRedemptionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire insurance module's state
     * </pre>
     */
    default void insuranceModuleState(com.injective.insurance.v1beta1.QueryProto.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryModuleStateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInsuranceModuleStateMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service.
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
   * Query defines the gRPC querier service.
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
     * Retrieves insurance params
     * </pre>
     */
    public void insuranceParams(com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsRequest request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInsuranceParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves individual insurance fund information from market id
     * </pre>
     */
    public void insuranceFund(com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundRequest request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInsuranceFundMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves all insurance funds
     * </pre>
     */
    public void insuranceFunds(com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsRequest request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInsuranceFundsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrives the value of insurance fund share token at current price (not
     * pending redemption)
     * </pre>
     */
    public void estimatedRedemptions(com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsRequest request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEstimatedRedemptionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves pending redemptions' share token at current price
     * </pre>
     */
    public void pendingRedemptions(com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsRequest request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPendingRedemptionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire insurance module's state
     * </pre>
     */
    public void insuranceModuleState(com.injective.insurance.v1beta1.QueryProto.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryModuleStateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInsuranceModuleStateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service.
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
     * Retrieves insurance params
     * </pre>
     */
    public com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsResponse insuranceParams(com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInsuranceParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves individual insurance fund information from market id
     * </pre>
     */
    public com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundResponse insuranceFund(com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInsuranceFundMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves all insurance funds
     * </pre>
     */
    public com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsResponse insuranceFunds(com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInsuranceFundsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrives the value of insurance fund share token at current price (not
     * pending redemption)
     * </pre>
     */
    public com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsResponse estimatedRedemptions(com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEstimatedRedemptionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves pending redemptions' share token at current price
     * </pre>
     */
    public com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsResponse pendingRedemptions(com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPendingRedemptionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the entire insurance module's state
     * </pre>
     */
    public com.injective.insurance.v1beta1.QueryProto.QueryModuleStateResponse insuranceModuleState(com.injective.insurance.v1beta1.QueryProto.QueryModuleStateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInsuranceModuleStateMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service.
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
     * Retrieves insurance params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsResponse> insuranceParams(
        com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInsuranceParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves individual insurance fund information from market id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundResponse> insuranceFund(
        com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInsuranceFundMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves all insurance funds
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsResponse> insuranceFunds(
        com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInsuranceFundsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrives the value of insurance fund share token at current price (not
     * pending redemption)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsResponse> estimatedRedemptions(
        com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEstimatedRedemptionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves pending redemptions' share token at current price
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsResponse> pendingRedemptions(
        com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPendingRedemptionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the entire insurance module's state
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.insurance.v1beta1.QueryProto.QueryModuleStateResponse> insuranceModuleState(
        com.injective.insurance.v1beta1.QueryProto.QueryModuleStateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInsuranceModuleStateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INSURANCE_PARAMS = 0;
  private static final int METHODID_INSURANCE_FUND = 1;
  private static final int METHODID_INSURANCE_FUNDS = 2;
  private static final int METHODID_ESTIMATED_REDEMPTIONS = 3;
  private static final int METHODID_PENDING_REDEMPTIONS = 4;
  private static final int METHODID_INSURANCE_MODULE_STATE = 5;

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
        case METHODID_INSURANCE_PARAMS:
          serviceImpl.insuranceParams((com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsResponse>) responseObserver);
          break;
        case METHODID_INSURANCE_FUND:
          serviceImpl.insuranceFund((com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundResponse>) responseObserver);
          break;
        case METHODID_INSURANCE_FUNDS:
          serviceImpl.insuranceFunds((com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsResponse>) responseObserver);
          break;
        case METHODID_ESTIMATED_REDEMPTIONS:
          serviceImpl.estimatedRedemptions((com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsResponse>) responseObserver);
          break;
        case METHODID_PENDING_REDEMPTIONS:
          serviceImpl.pendingRedemptions((com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsResponse>) responseObserver);
          break;
        case METHODID_INSURANCE_MODULE_STATE:
          serviceImpl.insuranceModuleState((com.injective.insurance.v1beta1.QueryProto.QueryModuleStateRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.QueryProto.QueryModuleStateResponse>) responseObserver);
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
          getInsuranceParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsRequest,
              com.injective.insurance.v1beta1.QueryProto.QueryInsuranceParamsResponse>(
                service, METHODID_INSURANCE_PARAMS)))
        .addMethod(
          getInsuranceFundMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundRequest,
              com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundResponse>(
                service, METHODID_INSURANCE_FUND)))
        .addMethod(
          getInsuranceFundsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsRequest,
              com.injective.insurance.v1beta1.QueryProto.QueryInsuranceFundsResponse>(
                service, METHODID_INSURANCE_FUNDS)))
        .addMethod(
          getEstimatedRedemptionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsRequest,
              com.injective.insurance.v1beta1.QueryProto.QueryEstimatedRedemptionsResponse>(
                service, METHODID_ESTIMATED_REDEMPTIONS)))
        .addMethod(
          getPendingRedemptionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsRequest,
              com.injective.insurance.v1beta1.QueryProto.QueryPendingRedemptionsResponse>(
                service, METHODID_PENDING_REDEMPTIONS)))
        .addMethod(
          getInsuranceModuleStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.insurance.v1beta1.QueryProto.QueryModuleStateRequest,
              com.injective.insurance.v1beta1.QueryProto.QueryModuleStateResponse>(
                service, METHODID_INSURANCE_MODULE_STATE)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.injective.insurance.v1beta1.QueryProto.getDescriptor();
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
              .addMethod(getInsuranceParamsMethod())
              .addMethod(getInsuranceFundMethod())
              .addMethod(getInsuranceFundsMethod())
              .addMethod(getEstimatedRedemptionsMethod())
              .addMethod(getPendingRedemptionsMethod())
              .addMethod(getInsuranceModuleStateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
