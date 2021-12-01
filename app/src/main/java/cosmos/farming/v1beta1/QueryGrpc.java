package cosmos.farming.v1beta1;

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
 * Query defines the gRPC query service for the farming module.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: tendermint/farming/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "cosmos.farming.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryParamsRequest,
      cosmos.farming.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = cosmos.farming.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = cosmos.farming.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryParamsRequest,
      cosmos.farming.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryParamsRequest, cosmos.farming.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<cosmos.farming.v1beta1.QueryOuterClass.QueryParamsRequest, cosmos.farming.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryPlansRequest,
      cosmos.farming.v1beta1.QueryOuterClass.QueryPlansResponse> getPlansMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Plans",
      requestType = cosmos.farming.v1beta1.QueryOuterClass.QueryPlansRequest.class,
      responseType = cosmos.farming.v1beta1.QueryOuterClass.QueryPlansResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryPlansRequest,
      cosmos.farming.v1beta1.QueryOuterClass.QueryPlansResponse> getPlansMethod() {
    io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryPlansRequest, cosmos.farming.v1beta1.QueryOuterClass.QueryPlansResponse> getPlansMethod;
    if ((getPlansMethod = QueryGrpc.getPlansMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPlansMethod = QueryGrpc.getPlansMethod) == null) {
          QueryGrpc.getPlansMethod = getPlansMethod =
              io.grpc.MethodDescriptor.<cosmos.farming.v1beta1.QueryOuterClass.QueryPlansRequest, cosmos.farming.v1beta1.QueryOuterClass.QueryPlansResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Plans"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.QueryOuterClass.QueryPlansRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.QueryOuterClass.QueryPlansResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Plans"))
              .build();
        }
      }
    }
    return getPlansMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryPlanRequest,
      cosmos.farming.v1beta1.QueryOuterClass.QueryPlanResponse> getPlanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Plan",
      requestType = cosmos.farming.v1beta1.QueryOuterClass.QueryPlanRequest.class,
      responseType = cosmos.farming.v1beta1.QueryOuterClass.QueryPlanResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryPlanRequest,
      cosmos.farming.v1beta1.QueryOuterClass.QueryPlanResponse> getPlanMethod() {
    io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryPlanRequest, cosmos.farming.v1beta1.QueryOuterClass.QueryPlanResponse> getPlanMethod;
    if ((getPlanMethod = QueryGrpc.getPlanMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPlanMethod = QueryGrpc.getPlanMethod) == null) {
          QueryGrpc.getPlanMethod = getPlanMethod =
              io.grpc.MethodDescriptor.<cosmos.farming.v1beta1.QueryOuterClass.QueryPlanRequest, cosmos.farming.v1beta1.QueryOuterClass.QueryPlanResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Plan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.QueryOuterClass.QueryPlanRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.QueryOuterClass.QueryPlanResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Plan"))
              .build();
        }
      }
    }
    return getPlanMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsRequest,
      cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsResponse> getStakingsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Stakings",
      requestType = cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsRequest.class,
      responseType = cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsRequest,
      cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsResponse> getStakingsMethod() {
    io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsRequest, cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsResponse> getStakingsMethod;
    if ((getStakingsMethod = QueryGrpc.getStakingsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getStakingsMethod = QueryGrpc.getStakingsMethod) == null) {
          QueryGrpc.getStakingsMethod = getStakingsMethod =
              io.grpc.MethodDescriptor.<cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsRequest, cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Stakings"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Stakings"))
              .build();
        }
      }
    }
    return getStakingsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsRequest,
      cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsResponse> getTotalStakingsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalStakings",
      requestType = cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsRequest.class,
      responseType = cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsRequest,
      cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsResponse> getTotalStakingsMethod() {
    io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsRequest, cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsResponse> getTotalStakingsMethod;
    if ((getTotalStakingsMethod = QueryGrpc.getTotalStakingsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalStakingsMethod = QueryGrpc.getTotalStakingsMethod) == null) {
          QueryGrpc.getTotalStakingsMethod = getTotalStakingsMethod =
              io.grpc.MethodDescriptor.<cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsRequest, cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalStakings"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalStakings"))
              .build();
        }
      }
    }
    return getTotalStakingsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsRequest,
      cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsResponse> getRewardsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Rewards",
      requestType = cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsRequest.class,
      responseType = cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsRequest,
      cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsResponse> getRewardsMethod() {
    io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsRequest, cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsResponse> getRewardsMethod;
    if ((getRewardsMethod = QueryGrpc.getRewardsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRewardsMethod = QueryGrpc.getRewardsMethod) == null) {
          QueryGrpc.getRewardsMethod = getRewardsMethod =
              io.grpc.MethodDescriptor.<cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsRequest, cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Rewards"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Rewards"))
              .build();
        }
      }
    }
    return getRewardsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysRequest,
      cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysResponse> getCurrentEpochDaysMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CurrentEpochDays",
      requestType = cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysRequest.class,
      responseType = cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysRequest,
      cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysResponse> getCurrentEpochDaysMethod() {
    io.grpc.MethodDescriptor<cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysRequest, cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysResponse> getCurrentEpochDaysMethod;
    if ((getCurrentEpochDaysMethod = QueryGrpc.getCurrentEpochDaysMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCurrentEpochDaysMethod = QueryGrpc.getCurrentEpochDaysMethod) == null) {
          QueryGrpc.getCurrentEpochDaysMethod = getCurrentEpochDaysMethod =
              io.grpc.MethodDescriptor.<cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysRequest, cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CurrentEpochDays"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CurrentEpochDays"))
              .build();
        }
      }
    }
    return getCurrentEpochDaysMethod;
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
   * Query defines the gRPC query service for the farming module.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Params returns parameters of the farming module.
     * </pre>
     */
    public void params(cosmos.farming.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Plans returns all plans.
     * </pre>
     */
    public void plans(cosmos.farming.v1beta1.QueryOuterClass.QueryPlansRequest request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryPlansResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPlansMethod(), responseObserver);
    }

    /**
     * <pre>
     * Plan returns a specific plan.
     * </pre>
     */
    public void plan(cosmos.farming.v1beta1.QueryOuterClass.QueryPlanRequest request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryPlanResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPlanMethod(), responseObserver);
    }

    /**
     * <pre>
     * Stakings returns all stakings by a farmer.
     * </pre>
     */
    public void stakings(cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsRequest request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStakingsMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalStakings returns total staking amount for a staking coin denom
     * </pre>
     */
    public void totalStakings(cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsRequest request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTotalStakingsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Rewards returns rewards for a farmer
     * </pre>
     */
    public void rewards(cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsRequest request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRewardsMethod(), responseObserver);
    }

    /**
     * <pre>
     * CurrentEpochDays returns current epoch days.
     * </pre>
     */
    public void currentEpochDays(cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysRequest request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCurrentEpochDaysMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.farming.v1beta1.QueryOuterClass.QueryParamsRequest,
                cosmos.farming.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getPlansMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.farming.v1beta1.QueryOuterClass.QueryPlansRequest,
                cosmos.farming.v1beta1.QueryOuterClass.QueryPlansResponse>(
                  this, METHODID_PLANS)))
          .addMethod(
            getPlanMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.farming.v1beta1.QueryOuterClass.QueryPlanRequest,
                cosmos.farming.v1beta1.QueryOuterClass.QueryPlanResponse>(
                  this, METHODID_PLAN)))
          .addMethod(
            getStakingsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsRequest,
                cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsResponse>(
                  this, METHODID_STAKINGS)))
          .addMethod(
            getTotalStakingsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsRequest,
                cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsResponse>(
                  this, METHODID_TOTAL_STAKINGS)))
          .addMethod(
            getRewardsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsRequest,
                cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsResponse>(
                  this, METHODID_REWARDS)))
          .addMethod(
            getCurrentEpochDaysMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysRequest,
                cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysResponse>(
                  this, METHODID_CURRENT_EPOCH_DAYS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC query service for the farming module.
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
     * Params returns parameters of the farming module.
     * </pre>
     */
    public void params(cosmos.farming.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Plans returns all plans.
     * </pre>
     */
    public void plans(cosmos.farming.v1beta1.QueryOuterClass.QueryPlansRequest request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryPlansResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPlansMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Plan returns a specific plan.
     * </pre>
     */
    public void plan(cosmos.farming.v1beta1.QueryOuterClass.QueryPlanRequest request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryPlanResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPlanMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Stakings returns all stakings by a farmer.
     * </pre>
     */
    public void stakings(cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsRequest request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStakingsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalStakings returns total staking amount for a staking coin denom
     * </pre>
     */
    public void totalStakings(cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsRequest request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTotalStakingsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Rewards returns rewards for a farmer
     * </pre>
     */
    public void rewards(cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsRequest request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRewardsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CurrentEpochDays returns current epoch days.
     * </pre>
     */
    public void currentEpochDays(cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysRequest request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCurrentEpochDaysMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC query service for the farming module.
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
     * Params returns parameters of the farming module.
     * </pre>
     */
    public cosmos.farming.v1beta1.QueryOuterClass.QueryParamsResponse params(cosmos.farming.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Plans returns all plans.
     * </pre>
     */
    public cosmos.farming.v1beta1.QueryOuterClass.QueryPlansResponse plans(cosmos.farming.v1beta1.QueryOuterClass.QueryPlansRequest request) {
      return blockingUnaryCall(
          getChannel(), getPlansMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Plan returns a specific plan.
     * </pre>
     */
    public cosmos.farming.v1beta1.QueryOuterClass.QueryPlanResponse plan(cosmos.farming.v1beta1.QueryOuterClass.QueryPlanRequest request) {
      return blockingUnaryCall(
          getChannel(), getPlanMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Stakings returns all stakings by a farmer.
     * </pre>
     */
    public cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsResponse stakings(cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsRequest request) {
      return blockingUnaryCall(
          getChannel(), getStakingsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalStakings returns total staking amount for a staking coin denom
     * </pre>
     */
    public cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsResponse totalStakings(cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsRequest request) {
      return blockingUnaryCall(
          getChannel(), getTotalStakingsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Rewards returns rewards for a farmer
     * </pre>
     */
    public cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsResponse rewards(cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsRequest request) {
      return blockingUnaryCall(
          getChannel(), getRewardsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CurrentEpochDays returns current epoch days.
     * </pre>
     */
    public cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysResponse currentEpochDays(cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysRequest request) {
      return blockingUnaryCall(
          getChannel(), getCurrentEpochDaysMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC query service for the farming module.
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
     * Params returns parameters of the farming module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.farming.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        cosmos.farming.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Plans returns all plans.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.farming.v1beta1.QueryOuterClass.QueryPlansResponse> plans(
        cosmos.farming.v1beta1.QueryOuterClass.QueryPlansRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPlansMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Plan returns a specific plan.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.farming.v1beta1.QueryOuterClass.QueryPlanResponse> plan(
        cosmos.farming.v1beta1.QueryOuterClass.QueryPlanRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPlanMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Stakings returns all stakings by a farmer.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsResponse> stakings(
        cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStakingsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalStakings returns total staking amount for a staking coin denom
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsResponse> totalStakings(
        cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTotalStakingsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Rewards returns rewards for a farmer
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsResponse> rewards(
        cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRewardsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CurrentEpochDays returns current epoch days.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysResponse> currentEpochDays(
        cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCurrentEpochDaysMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_PLANS = 1;
  private static final int METHODID_PLAN = 2;
  private static final int METHODID_STAKINGS = 3;
  private static final int METHODID_TOTAL_STAKINGS = 4;
  private static final int METHODID_REWARDS = 5;
  private static final int METHODID_CURRENT_EPOCH_DAYS = 6;

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
        case METHODID_PARAMS:
          serviceImpl.params((cosmos.farming.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_PLANS:
          serviceImpl.plans((cosmos.farming.v1beta1.QueryOuterClass.QueryPlansRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryPlansResponse>) responseObserver);
          break;
        case METHODID_PLAN:
          serviceImpl.plan((cosmos.farming.v1beta1.QueryOuterClass.QueryPlanRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryPlanResponse>) responseObserver);
          break;
        case METHODID_STAKINGS:
          serviceImpl.stakings((cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryStakingsResponse>) responseObserver);
          break;
        case METHODID_TOTAL_STAKINGS:
          serviceImpl.totalStakings((cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryTotalStakingsResponse>) responseObserver);
          break;
        case METHODID_REWARDS:
          serviceImpl.rewards((cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryRewardsResponse>) responseObserver);
          break;
        case METHODID_CURRENT_EPOCH_DAYS:
          serviceImpl.currentEpochDays((cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.QueryOuterClass.QueryCurrentEpochDaysResponse>) responseObserver);
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
      return cosmos.farming.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getParamsMethod())
              .addMethod(getPlansMethod())
              .addMethod(getPlanMethod())
              .addMethod(getStakingsMethod())
              .addMethod(getTotalStakingsMethod())
              .addMethod(getRewardsMethod())
              .addMethod(getCurrentEpochDaysMethod())
              .build();
        }
      }
    }
    return result;
  }
}
