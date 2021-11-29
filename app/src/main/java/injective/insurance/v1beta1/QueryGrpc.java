package injective.insurance.v1beta1;

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
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: injective/insurance/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "injective.insurance.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsRequest,
      injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsResponse> getInsuranceParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InsuranceParams",
      requestType = injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsRequest.class,
      responseType = injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsRequest,
      injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsResponse> getInsuranceParamsMethod() {
    io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsRequest, injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsResponse> getInsuranceParamsMethod;
    if ((getInsuranceParamsMethod = QueryGrpc.getInsuranceParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getInsuranceParamsMethod = QueryGrpc.getInsuranceParamsMethod) == null) {
          QueryGrpc.getInsuranceParamsMethod = getInsuranceParamsMethod =
              io.grpc.MethodDescriptor.<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsRequest, injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InsuranceParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("InsuranceParams"))
              .build();
        }
      }
    }
    return getInsuranceParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundRequest,
      injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundResponse> getInsuranceFundMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InsuranceFund",
      requestType = injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundRequest.class,
      responseType = injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundRequest,
      injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundResponse> getInsuranceFundMethod() {
    io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundRequest, injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundResponse> getInsuranceFundMethod;
    if ((getInsuranceFundMethod = QueryGrpc.getInsuranceFundMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getInsuranceFundMethod = QueryGrpc.getInsuranceFundMethod) == null) {
          QueryGrpc.getInsuranceFundMethod = getInsuranceFundMethod =
              io.grpc.MethodDescriptor.<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundRequest, injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InsuranceFund"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("InsuranceFund"))
              .build();
        }
      }
    }
    return getInsuranceFundMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsRequest,
      injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsResponse> getInsuranceFundsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InsuranceFunds",
      requestType = injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsRequest.class,
      responseType = injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsRequest,
      injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsResponse> getInsuranceFundsMethod() {
    io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsRequest, injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsResponse> getInsuranceFundsMethod;
    if ((getInsuranceFundsMethod = QueryGrpc.getInsuranceFundsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getInsuranceFundsMethod = QueryGrpc.getInsuranceFundsMethod) == null) {
          QueryGrpc.getInsuranceFundsMethod = getInsuranceFundsMethod =
              io.grpc.MethodDescriptor.<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsRequest, injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InsuranceFunds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("InsuranceFunds"))
              .build();
        }
      }
    }
    return getInsuranceFundsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsRequest,
      injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsResponse> getEstimatedRedemptionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EstimatedRedemptions",
      requestType = injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsRequest.class,
      responseType = injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsRequest,
      injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsResponse> getEstimatedRedemptionsMethod() {
    io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsRequest, injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsResponse> getEstimatedRedemptionsMethod;
    if ((getEstimatedRedemptionsMethod = QueryGrpc.getEstimatedRedemptionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEstimatedRedemptionsMethod = QueryGrpc.getEstimatedRedemptionsMethod) == null) {
          QueryGrpc.getEstimatedRedemptionsMethod = getEstimatedRedemptionsMethod =
              io.grpc.MethodDescriptor.<injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsRequest, injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EstimatedRedemptions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EstimatedRedemptions"))
              .build();
        }
      }
    }
    return getEstimatedRedemptionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsRequest,
      injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsResponse> getPendingRedemptionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PendingRedemptions",
      requestType = injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsRequest.class,
      responseType = injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsRequest,
      injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsResponse> getPendingRedemptionsMethod() {
    io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsRequest, injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsResponse> getPendingRedemptionsMethod;
    if ((getPendingRedemptionsMethod = QueryGrpc.getPendingRedemptionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPendingRedemptionsMethod = QueryGrpc.getPendingRedemptionsMethod) == null) {
          QueryGrpc.getPendingRedemptionsMethod = getPendingRedemptionsMethod =
              io.grpc.MethodDescriptor.<injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsRequest, injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PendingRedemptions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PendingRedemptions"))
              .build();
        }
      }
    }
    return getPendingRedemptionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateRequest,
      injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateResponse> getInsuranceModuleStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InsuranceModuleState",
      requestType = injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateRequest.class,
      responseType = injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateRequest,
      injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateResponse> getInsuranceModuleStateMethod() {
    io.grpc.MethodDescriptor<injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateRequest, injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateResponse> getInsuranceModuleStateMethod;
    if ((getInsuranceModuleStateMethod = QueryGrpc.getInsuranceModuleStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getInsuranceModuleStateMethod = QueryGrpc.getInsuranceModuleStateMethod) == null) {
          QueryGrpc.getInsuranceModuleStateMethod = getInsuranceModuleStateMethod =
              io.grpc.MethodDescriptor.<injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateRequest, injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InsuranceModuleState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateResponse.getDefaultInstance()))
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
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Retrieves insurance params
     * </pre>
     */
    public void insuranceParams(injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsRequest request,
        io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInsuranceParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves individual insurance fund information from market id
     * </pre>
     */
    public void insuranceFund(injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundRequest request,
        io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInsuranceFundMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves all insurance funds
     * </pre>
     */
    public void insuranceFunds(injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsRequest request,
        io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInsuranceFundsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrives the value of insurance fund share token at current price (not pending redemption)
     * </pre>
     */
    public void estimatedRedemptions(injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsRequest request,
        io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEstimatedRedemptionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves pending redemptions' share token at current price
     * </pre>
     */
    public void pendingRedemptions(injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsRequest request,
        io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPendingRedemptionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire insurance module's state
     * </pre>
     */
    public void insuranceModuleState(injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInsuranceModuleStateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getInsuranceParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsRequest,
                injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsResponse>(
                  this, METHODID_INSURANCE_PARAMS)))
          .addMethod(
            getInsuranceFundMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundRequest,
                injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundResponse>(
                  this, METHODID_INSURANCE_FUND)))
          .addMethod(
            getInsuranceFundsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsRequest,
                injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsResponse>(
                  this, METHODID_INSURANCE_FUNDS)))
          .addMethod(
            getEstimatedRedemptionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsRequest,
                injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsResponse>(
                  this, METHODID_ESTIMATED_REDEMPTIONS)))
          .addMethod(
            getPendingRedemptionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsRequest,
                injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsResponse>(
                  this, METHODID_PENDING_REDEMPTIONS)))
          .addMethod(
            getInsuranceModuleStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateRequest,
                injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateResponse>(
                  this, METHODID_INSURANCE_MODULE_STATE)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * Retrieves insurance params
     * </pre>
     */
    public void insuranceParams(injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsRequest request,
        io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInsuranceParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves individual insurance fund information from market id
     * </pre>
     */
    public void insuranceFund(injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundRequest request,
        io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInsuranceFundMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves all insurance funds
     * </pre>
     */
    public void insuranceFunds(injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsRequest request,
        io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInsuranceFundsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrives the value of insurance fund share token at current price (not pending redemption)
     * </pre>
     */
    public void estimatedRedemptions(injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsRequest request,
        io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEstimatedRedemptionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves pending redemptions' share token at current price
     * </pre>
     */
    public void pendingRedemptions(injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsRequest request,
        io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPendingRedemptionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire insurance module's state
     * </pre>
     */
    public void insuranceModuleState(injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInsuranceModuleStateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * Retrieves insurance params
     * </pre>
     */
    public injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsResponse insuranceParams(injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getInsuranceParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves individual insurance fund information from market id
     * </pre>
     */
    public injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundResponse insuranceFund(injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundRequest request) {
      return blockingUnaryCall(
          getChannel(), getInsuranceFundMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves all insurance funds
     * </pre>
     */
    public injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsResponse insuranceFunds(injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsRequest request) {
      return blockingUnaryCall(
          getChannel(), getInsuranceFundsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrives the value of insurance fund share token at current price (not pending redemption)
     * </pre>
     */
    public injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsResponse estimatedRedemptions(injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsRequest request) {
      return blockingUnaryCall(
          getChannel(), getEstimatedRedemptionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves pending redemptions' share token at current price
     * </pre>
     */
    public injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsResponse pendingRedemptions(injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPendingRedemptionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the entire insurance module's state
     * </pre>
     */
    public injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateResponse insuranceModuleState(injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateRequest request) {
      return blockingUnaryCall(
          getChannel(), getInsuranceModuleStateMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * Retrieves insurance params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsResponse> insuranceParams(
        injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getInsuranceParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves individual insurance fund information from market id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundResponse> insuranceFund(
        injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getInsuranceFundMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves all insurance funds
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsResponse> insuranceFunds(
        injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getInsuranceFundsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrives the value of insurance fund share token at current price (not pending redemption)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsResponse> estimatedRedemptions(
        injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEstimatedRedemptionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves pending redemptions' share token at current price
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsResponse> pendingRedemptions(
        injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPendingRedemptionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the entire insurance module's state
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateResponse> insuranceModuleState(
        injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateRequest request) {
      return futureUnaryCall(
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
        case METHODID_INSURANCE_PARAMS:
          serviceImpl.insuranceParams((injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsRequest) request,
              (io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceParamsResponse>) responseObserver);
          break;
        case METHODID_INSURANCE_FUND:
          serviceImpl.insuranceFund((injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundRequest) request,
              (io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundResponse>) responseObserver);
          break;
        case METHODID_INSURANCE_FUNDS:
          serviceImpl.insuranceFunds((injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsRequest) request,
              (io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryInsuranceFundsResponse>) responseObserver);
          break;
        case METHODID_ESTIMATED_REDEMPTIONS:
          serviceImpl.estimatedRedemptions((injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsRequest) request,
              (io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryEstimatedRedemptionsResponse>) responseObserver);
          break;
        case METHODID_PENDING_REDEMPTIONS:
          serviceImpl.pendingRedemptions((injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsRequest) request,
              (io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryPendingRedemptionsResponse>) responseObserver);
          break;
        case METHODID_INSURANCE_MODULE_STATE:
          serviceImpl.insuranceModuleState((injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateRequest) request,
              (io.grpc.stub.StreamObserver<injective.insurance.v1beta1.QueryOuterClass.QueryModuleStateResponse>) responseObserver);
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
      return injective.insurance.v1beta1.QueryOuterClass.getDescriptor();
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
