package osmosis.poolincentives.v1beta1;

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
    comments = "Source: osmosis/pool-incentives/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "osmosis.poolincentives.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsRequest,
      osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsResponse> getGaugeIdsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GaugeIds",
      requestType = osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsRequest.class,
      responseType = osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsRequest,
      osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsResponse> getGaugeIdsMethod() {
    io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsRequest, osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsResponse> getGaugeIdsMethod;
    if ((getGaugeIdsMethod = QueryGrpc.getGaugeIdsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGaugeIdsMethod = QueryGrpc.getGaugeIdsMethod) == null) {
          QueryGrpc.getGaugeIdsMethod = getGaugeIdsMethod =
              io.grpc.MethodDescriptor.<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsRequest, osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GaugeIds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GaugeIds"))
              .build();
        }
      }
    }
    return getGaugeIdsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoRequest,
      osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoResponse> getDistrInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DistrInfo",
      requestType = osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoRequest.class,
      responseType = osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoRequest,
      osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoResponse> getDistrInfoMethod() {
    io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoRequest, osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoResponse> getDistrInfoMethod;
    if ((getDistrInfoMethod = QueryGrpc.getDistrInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDistrInfoMethod = QueryGrpc.getDistrInfoMethod) == null) {
          QueryGrpc.getDistrInfoMethod = getDistrInfoMethod =
              io.grpc.MethodDescriptor.<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoRequest, osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DistrInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DistrInfo"))
              .build();
        }
      }
    }
    return getDistrInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsRequest,
      osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsRequest,
      osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsRequest, osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsRequest, osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsRequest,
      osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsResponse> getLockableDurationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LockableDurations",
      requestType = osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsRequest.class,
      responseType = osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsRequest,
      osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsResponse> getLockableDurationsMethod() {
    io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsRequest, osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsResponse> getLockableDurationsMethod;
    if ((getLockableDurationsMethod = QueryGrpc.getLockableDurationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLockableDurationsMethod = QueryGrpc.getLockableDurationsMethod) == null) {
          QueryGrpc.getLockableDurationsMethod = getLockableDurationsMethod =
              io.grpc.MethodDescriptor.<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsRequest, osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LockableDurations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LockableDurations"))
              .build();
        }
      }
    }
    return getLockableDurationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsRequest,
      osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsResponse> getIncentivizedPoolsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IncentivizedPools",
      requestType = osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsRequest.class,
      responseType = osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsRequest,
      osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsResponse> getIncentivizedPoolsMethod() {
    io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsRequest, osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsResponse> getIncentivizedPoolsMethod;
    if ((getIncentivizedPoolsMethod = QueryGrpc.getIncentivizedPoolsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getIncentivizedPoolsMethod = QueryGrpc.getIncentivizedPoolsMethod) == null) {
          QueryGrpc.getIncentivizedPoolsMethod = getIncentivizedPoolsMethod =
              io.grpc.MethodDescriptor.<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsRequest, osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IncentivizedPools"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("IncentivizedPools"))
              .build();
        }
      }
    }
    return getIncentivizedPoolsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesRequest,
      osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesResponse> getExternalIncentiveGaugesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExternalIncentiveGauges",
      requestType = osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesRequest.class,
      responseType = osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesRequest,
      osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesResponse> getExternalIncentiveGaugesMethod() {
    io.grpc.MethodDescriptor<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesRequest, osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesResponse> getExternalIncentiveGaugesMethod;
    if ((getExternalIncentiveGaugesMethod = QueryGrpc.getExternalIncentiveGaugesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getExternalIncentiveGaugesMethod = QueryGrpc.getExternalIncentiveGaugesMethod) == null) {
          QueryGrpc.getExternalIncentiveGaugesMethod = getExternalIncentiveGaugesMethod =
              io.grpc.MethodDescriptor.<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesRequest, osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExternalIncentiveGauges"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ExternalIncentiveGauges"))
              .build();
        }
      }
    }
    return getExternalIncentiveGaugesMethod;
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
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * GaugeIds takes the pool id and returns the matching gauge ids and durations
     * </pre>
     */
    public void gaugeIds(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsRequest request,
        io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGaugeIdsMethod(), responseObserver);
    }

    /**
     */
    public void distrInfo(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoRequest request,
        io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDistrInfoMethod(), responseObserver);
    }

    /**
     */
    public void params(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     */
    public void lockableDurations(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsRequest request,
        io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLockableDurationsMethod(), responseObserver);
    }

    /**
     */
    public void incentivizedPools(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsRequest request,
        io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getIncentivizedPoolsMethod(), responseObserver);
    }

    /**
     */
    public void externalIncentiveGauges(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesRequest request,
        io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getExternalIncentiveGaugesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGaugeIdsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsRequest,
                osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsResponse>(
                  this, METHODID_GAUGE_IDS)))
          .addMethod(
            getDistrInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoRequest,
                osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoResponse>(
                  this, METHODID_DISTR_INFO)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsRequest,
                osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getLockableDurationsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsRequest,
                osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsResponse>(
                  this, METHODID_LOCKABLE_DURATIONS)))
          .addMethod(
            getIncentivizedPoolsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsRequest,
                osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsResponse>(
                  this, METHODID_INCENTIVIZED_POOLS)))
          .addMethod(
            getExternalIncentiveGaugesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesRequest,
                osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesResponse>(
                  this, METHODID_EXTERNAL_INCENTIVE_GAUGES)))
          .build();
    }
  }

  /**
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
     * GaugeIds takes the pool id and returns the matching gauge ids and durations
     * </pre>
     */
    public void gaugeIds(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsRequest request,
        io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGaugeIdsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void distrInfo(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoRequest request,
        io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDistrInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void params(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lockableDurations(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsRequest request,
        io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLockableDurationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void incentivizedPools(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsRequest request,
        io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIncentivizedPoolsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void externalIncentiveGauges(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesRequest request,
        io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExternalIncentiveGaugesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
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
     * GaugeIds takes the pool id and returns the matching gauge ids and durations
     * </pre>
     */
    public osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsResponse gaugeIds(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGaugeIdsMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoResponse distrInfo(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoRequest request) {
      return blockingUnaryCall(
          getChannel(), getDistrInfoMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsResponse params(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsResponse lockableDurations(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsRequest request) {
      return blockingUnaryCall(
          getChannel(), getLockableDurationsMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsResponse incentivizedPools(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsRequest request) {
      return blockingUnaryCall(
          getChannel(), getIncentivizedPoolsMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesResponse externalIncentiveGauges(osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesRequest request) {
      return blockingUnaryCall(
          getChannel(), getExternalIncentiveGaugesMethod(), getCallOptions(), request);
    }
  }

  /**
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
     * GaugeIds takes the pool id and returns the matching gauge ids and durations
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsResponse> gaugeIds(
        osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGaugeIdsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoResponse> distrInfo(
        osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDistrInfoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsResponse> lockableDurations(
        osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLockableDurationsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsResponse> incentivizedPools(
        osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getIncentivizedPoolsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesResponse> externalIncentiveGauges(
        osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getExternalIncentiveGaugesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GAUGE_IDS = 0;
  private static final int METHODID_DISTR_INFO = 1;
  private static final int METHODID_PARAMS = 2;
  private static final int METHODID_LOCKABLE_DURATIONS = 3;
  private static final int METHODID_INCENTIVIZED_POOLS = 4;
  private static final int METHODID_EXTERNAL_INCENTIVE_GAUGES = 5;

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
        case METHODID_GAUGE_IDS:
          serviceImpl.gaugeIds((osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryGaugeIdsResponse>) responseObserver);
          break;
        case METHODID_DISTR_INFO:
          serviceImpl.distrInfo((osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryDistrInfoResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_LOCKABLE_DURATIONS:
          serviceImpl.lockableDurations((osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryLockableDurationsResponse>) responseObserver);
          break;
        case METHODID_INCENTIVIZED_POOLS:
          serviceImpl.incentivizedPools((osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryIncentivizedPoolsResponse>) responseObserver);
          break;
        case METHODID_EXTERNAL_INCENTIVE_GAUGES:
          serviceImpl.externalIncentiveGauges((osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.poolincentives.v1beta1.QueryOuterClass.QueryExternalIncentiveGaugesResponse>) responseObserver);
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
      return osmosis.poolincentives.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getGaugeIdsMethod())
              .addMethod(getDistrInfoMethod())
              .addMethod(getParamsMethod())
              .addMethod(getLockableDurationsMethod())
              .addMethod(getIncentivizedPoolsMethod())
              .addMethod(getExternalIncentiveGaugesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
