package osmosis.incentives;

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
    comments = "Source: osmosis/incentives/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "osmosis.incentives.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsRequest,
      osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsResponse> getModuleToDistributeCoinsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ModuleToDistributeCoins",
      requestType = osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsRequest.class,
      responseType = osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsRequest,
      osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsResponse> getModuleToDistributeCoinsMethod() {
    io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsRequest, osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsResponse> getModuleToDistributeCoinsMethod;
    if ((getModuleToDistributeCoinsMethod = QueryGrpc.getModuleToDistributeCoinsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getModuleToDistributeCoinsMethod = QueryGrpc.getModuleToDistributeCoinsMethod) == null) {
          QueryGrpc.getModuleToDistributeCoinsMethod = getModuleToDistributeCoinsMethod =
              io.grpc.MethodDescriptor.<osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsRequest, osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ModuleToDistributeCoins"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ModuleToDistributeCoins"))
              .build();
        }
      }
    }
    return getModuleToDistributeCoinsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsRequest,
      osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsResponse> getModuleDistributedCoinsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ModuleDistributedCoins",
      requestType = osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsRequest.class,
      responseType = osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsRequest,
      osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsResponse> getModuleDistributedCoinsMethod() {
    io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsRequest, osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsResponse> getModuleDistributedCoinsMethod;
    if ((getModuleDistributedCoinsMethod = QueryGrpc.getModuleDistributedCoinsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getModuleDistributedCoinsMethod = QueryGrpc.getModuleDistributedCoinsMethod) == null) {
          QueryGrpc.getModuleDistributedCoinsMethod = getModuleDistributedCoinsMethod =
              io.grpc.MethodDescriptor.<osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsRequest, osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ModuleDistributedCoins"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ModuleDistributedCoins"))
              .build();
        }
      }
    }
    return getModuleDistributedCoinsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.GaugeByIDRequest,
      osmosis.incentives.QueryOuterClass.GaugeByIDResponse> getGaugeByIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GaugeByID",
      requestType = osmosis.incentives.QueryOuterClass.GaugeByIDRequest.class,
      responseType = osmosis.incentives.QueryOuterClass.GaugeByIDResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.GaugeByIDRequest,
      osmosis.incentives.QueryOuterClass.GaugeByIDResponse> getGaugeByIDMethod() {
    io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.GaugeByIDRequest, osmosis.incentives.QueryOuterClass.GaugeByIDResponse> getGaugeByIDMethod;
    if ((getGaugeByIDMethod = QueryGrpc.getGaugeByIDMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGaugeByIDMethod = QueryGrpc.getGaugeByIDMethod) == null) {
          QueryGrpc.getGaugeByIDMethod = getGaugeByIDMethod =
              io.grpc.MethodDescriptor.<osmosis.incentives.QueryOuterClass.GaugeByIDRequest, osmosis.incentives.QueryOuterClass.GaugeByIDResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GaugeByID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.GaugeByIDRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.GaugeByIDResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GaugeByID"))
              .build();
        }
      }
    }
    return getGaugeByIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.GaugesRequest,
      osmosis.incentives.QueryOuterClass.GaugesResponse> getGaugesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Gauges",
      requestType = osmosis.incentives.QueryOuterClass.GaugesRequest.class,
      responseType = osmosis.incentives.QueryOuterClass.GaugesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.GaugesRequest,
      osmosis.incentives.QueryOuterClass.GaugesResponse> getGaugesMethod() {
    io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.GaugesRequest, osmosis.incentives.QueryOuterClass.GaugesResponse> getGaugesMethod;
    if ((getGaugesMethod = QueryGrpc.getGaugesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGaugesMethod = QueryGrpc.getGaugesMethod) == null) {
          QueryGrpc.getGaugesMethod = getGaugesMethod =
              io.grpc.MethodDescriptor.<osmosis.incentives.QueryOuterClass.GaugesRequest, osmosis.incentives.QueryOuterClass.GaugesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Gauges"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.GaugesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.GaugesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Gauges"))
              .build();
        }
      }
    }
    return getGaugesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.ActiveGaugesRequest,
      osmosis.incentives.QueryOuterClass.ActiveGaugesResponse> getActiveGaugesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ActiveGauges",
      requestType = osmosis.incentives.QueryOuterClass.ActiveGaugesRequest.class,
      responseType = osmosis.incentives.QueryOuterClass.ActiveGaugesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.ActiveGaugesRequest,
      osmosis.incentives.QueryOuterClass.ActiveGaugesResponse> getActiveGaugesMethod() {
    io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.ActiveGaugesRequest, osmosis.incentives.QueryOuterClass.ActiveGaugesResponse> getActiveGaugesMethod;
    if ((getActiveGaugesMethod = QueryGrpc.getActiveGaugesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getActiveGaugesMethod = QueryGrpc.getActiveGaugesMethod) == null) {
          QueryGrpc.getActiveGaugesMethod = getActiveGaugesMethod =
              io.grpc.MethodDescriptor.<osmosis.incentives.QueryOuterClass.ActiveGaugesRequest, osmosis.incentives.QueryOuterClass.ActiveGaugesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ActiveGauges"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.ActiveGaugesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.ActiveGaugesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ActiveGauges"))
              .build();
        }
      }
    }
    return getActiveGaugesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomRequest,
      osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomResponse> getActiveGaugesPerDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ActiveGaugesPerDenom",
      requestType = osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomRequest.class,
      responseType = osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomRequest,
      osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomResponse> getActiveGaugesPerDenomMethod() {
    io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomRequest, osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomResponse> getActiveGaugesPerDenomMethod;
    if ((getActiveGaugesPerDenomMethod = QueryGrpc.getActiveGaugesPerDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getActiveGaugesPerDenomMethod = QueryGrpc.getActiveGaugesPerDenomMethod) == null) {
          QueryGrpc.getActiveGaugesPerDenomMethod = getActiveGaugesPerDenomMethod =
              io.grpc.MethodDescriptor.<osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomRequest, osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ActiveGaugesPerDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ActiveGaugesPerDenom"))
              .build();
        }
      }
    }
    return getActiveGaugesPerDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.UpcomingGaugesRequest,
      osmosis.incentives.QueryOuterClass.UpcomingGaugesResponse> getUpcomingGaugesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpcomingGauges",
      requestType = osmosis.incentives.QueryOuterClass.UpcomingGaugesRequest.class,
      responseType = osmosis.incentives.QueryOuterClass.UpcomingGaugesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.UpcomingGaugesRequest,
      osmosis.incentives.QueryOuterClass.UpcomingGaugesResponse> getUpcomingGaugesMethod() {
    io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.UpcomingGaugesRequest, osmosis.incentives.QueryOuterClass.UpcomingGaugesResponse> getUpcomingGaugesMethod;
    if ((getUpcomingGaugesMethod = QueryGrpc.getUpcomingGaugesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUpcomingGaugesMethod = QueryGrpc.getUpcomingGaugesMethod) == null) {
          QueryGrpc.getUpcomingGaugesMethod = getUpcomingGaugesMethod =
              io.grpc.MethodDescriptor.<osmosis.incentives.QueryOuterClass.UpcomingGaugesRequest, osmosis.incentives.QueryOuterClass.UpcomingGaugesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpcomingGauges"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.UpcomingGaugesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.UpcomingGaugesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UpcomingGauges"))
              .build();
        }
      }
    }
    return getUpcomingGaugesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.RewardsEstRequest,
      osmosis.incentives.QueryOuterClass.RewardsEstResponse> getRewardsEstMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RewardsEst",
      requestType = osmosis.incentives.QueryOuterClass.RewardsEstRequest.class,
      responseType = osmosis.incentives.QueryOuterClass.RewardsEstResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.RewardsEstRequest,
      osmosis.incentives.QueryOuterClass.RewardsEstResponse> getRewardsEstMethod() {
    io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.RewardsEstRequest, osmosis.incentives.QueryOuterClass.RewardsEstResponse> getRewardsEstMethod;
    if ((getRewardsEstMethod = QueryGrpc.getRewardsEstMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRewardsEstMethod = QueryGrpc.getRewardsEstMethod) == null) {
          QueryGrpc.getRewardsEstMethod = getRewardsEstMethod =
              io.grpc.MethodDescriptor.<osmosis.incentives.QueryOuterClass.RewardsEstRequest, osmosis.incentives.QueryOuterClass.RewardsEstResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RewardsEst"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.RewardsEstRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.RewardsEstResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RewardsEst"))
              .build();
        }
      }
    }
    return getRewardsEstMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.QueryLockableDurationsRequest,
      osmosis.incentives.QueryOuterClass.QueryLockableDurationsResponse> getLockableDurationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LockableDurations",
      requestType = osmosis.incentives.QueryOuterClass.QueryLockableDurationsRequest.class,
      responseType = osmosis.incentives.QueryOuterClass.QueryLockableDurationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.QueryLockableDurationsRequest,
      osmosis.incentives.QueryOuterClass.QueryLockableDurationsResponse> getLockableDurationsMethod() {
    io.grpc.MethodDescriptor<osmosis.incentives.QueryOuterClass.QueryLockableDurationsRequest, osmosis.incentives.QueryOuterClass.QueryLockableDurationsResponse> getLockableDurationsMethod;
    if ((getLockableDurationsMethod = QueryGrpc.getLockableDurationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLockableDurationsMethod = QueryGrpc.getLockableDurationsMethod) == null) {
          QueryGrpc.getLockableDurationsMethod = getLockableDurationsMethod =
              io.grpc.MethodDescriptor.<osmosis.incentives.QueryOuterClass.QueryLockableDurationsRequest, osmosis.incentives.QueryOuterClass.QueryLockableDurationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LockableDurations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.QueryLockableDurationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.incentives.QueryOuterClass.QueryLockableDurationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LockableDurations"))
              .build();
        }
      }
    }
    return getLockableDurationsMethod;
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
     * returns coins that is going to be distributed
     * </pre>
     */
    public void moduleToDistributeCoins(osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getModuleToDistributeCoinsMethod(), responseObserver);
    }

    /**
     * <pre>
     * returns coins that are distributed by module so far
     * </pre>
     */
    public void moduleDistributedCoins(osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getModuleDistributedCoinsMethod(), responseObserver);
    }

    /**
     * <pre>
     * returns Gauge by id
     * </pre>
     */
    public void gaugeByID(osmosis.incentives.QueryOuterClass.GaugeByIDRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.GaugeByIDResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGaugeByIDMethod(), responseObserver);
    }

    /**
     * <pre>
     * returns gauges both upcoming and active
     * </pre>
     */
    public void gauges(osmosis.incentives.QueryOuterClass.GaugesRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.GaugesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGaugesMethod(), responseObserver);
    }

    /**
     * <pre>
     * returns active gauges
     * </pre>
     */
    public void activeGauges(osmosis.incentives.QueryOuterClass.ActiveGaugesRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.ActiveGaugesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getActiveGaugesMethod(), responseObserver);
    }

    /**
     */
    public void activeGaugesPerDenom(osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getActiveGaugesPerDenomMethod(), responseObserver);
    }

    /**
     * <pre>
     * returns scheduled gauges
     * </pre>
     */
    public void upcomingGauges(osmosis.incentives.QueryOuterClass.UpcomingGaugesRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.UpcomingGaugesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpcomingGaugesMethod(), responseObserver);
    }

    /**
     * <pre>
     * RewardsEst returns an estimate of the rewards at a future specific time.
     * The querier either provides an address or a set of locks
     * for which they want to find the associated rewards.
     * </pre>
     */
    public void rewardsEst(osmosis.incentives.QueryOuterClass.RewardsEstRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.RewardsEstResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRewardsEstMethod(), responseObserver);
    }

    /**
     * <pre>
     * returns lockable durations that are valid to give incentives
     * </pre>
     */
    public void lockableDurations(osmosis.incentives.QueryOuterClass.QueryLockableDurationsRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.QueryLockableDurationsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLockableDurationsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getModuleToDistributeCoinsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsRequest,
                osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsResponse>(
                  this, METHODID_MODULE_TO_DISTRIBUTE_COINS)))
          .addMethod(
            getModuleDistributedCoinsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsRequest,
                osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsResponse>(
                  this, METHODID_MODULE_DISTRIBUTED_COINS)))
          .addMethod(
            getGaugeByIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.incentives.QueryOuterClass.GaugeByIDRequest,
                osmosis.incentives.QueryOuterClass.GaugeByIDResponse>(
                  this, METHODID_GAUGE_BY_ID)))
          .addMethod(
            getGaugesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.incentives.QueryOuterClass.GaugesRequest,
                osmosis.incentives.QueryOuterClass.GaugesResponse>(
                  this, METHODID_GAUGES)))
          .addMethod(
            getActiveGaugesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.incentives.QueryOuterClass.ActiveGaugesRequest,
                osmosis.incentives.QueryOuterClass.ActiveGaugesResponse>(
                  this, METHODID_ACTIVE_GAUGES)))
          .addMethod(
            getActiveGaugesPerDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomRequest,
                osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomResponse>(
                  this, METHODID_ACTIVE_GAUGES_PER_DENOM)))
          .addMethod(
            getUpcomingGaugesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.incentives.QueryOuterClass.UpcomingGaugesRequest,
                osmosis.incentives.QueryOuterClass.UpcomingGaugesResponse>(
                  this, METHODID_UPCOMING_GAUGES)))
          .addMethod(
            getRewardsEstMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.incentives.QueryOuterClass.RewardsEstRequest,
                osmosis.incentives.QueryOuterClass.RewardsEstResponse>(
                  this, METHODID_REWARDS_EST)))
          .addMethod(
            getLockableDurationsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.incentives.QueryOuterClass.QueryLockableDurationsRequest,
                osmosis.incentives.QueryOuterClass.QueryLockableDurationsResponse>(
                  this, METHODID_LOCKABLE_DURATIONS)))
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
     * returns coins that is going to be distributed
     * </pre>
     */
    public void moduleToDistributeCoins(osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getModuleToDistributeCoinsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * returns coins that are distributed by module so far
     * </pre>
     */
    public void moduleDistributedCoins(osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getModuleDistributedCoinsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * returns Gauge by id
     * </pre>
     */
    public void gaugeByID(osmosis.incentives.QueryOuterClass.GaugeByIDRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.GaugeByIDResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGaugeByIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * returns gauges both upcoming and active
     * </pre>
     */
    public void gauges(osmosis.incentives.QueryOuterClass.GaugesRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.GaugesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGaugesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * returns active gauges
     * </pre>
     */
    public void activeGauges(osmosis.incentives.QueryOuterClass.ActiveGaugesRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.ActiveGaugesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getActiveGaugesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void activeGaugesPerDenom(osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getActiveGaugesPerDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * returns scheduled gauges
     * </pre>
     */
    public void upcomingGauges(osmosis.incentives.QueryOuterClass.UpcomingGaugesRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.UpcomingGaugesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpcomingGaugesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RewardsEst returns an estimate of the rewards at a future specific time.
     * The querier either provides an address or a set of locks
     * for which they want to find the associated rewards.
     * </pre>
     */
    public void rewardsEst(osmosis.incentives.QueryOuterClass.RewardsEstRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.RewardsEstResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRewardsEstMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * returns lockable durations that are valid to give incentives
     * </pre>
     */
    public void lockableDurations(osmosis.incentives.QueryOuterClass.QueryLockableDurationsRequest request,
        io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.QueryLockableDurationsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLockableDurationsMethod(), getCallOptions()), request, responseObserver);
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
     * returns coins that is going to be distributed
     * </pre>
     */
    public osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsResponse moduleToDistributeCoins(osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsRequest request) {
      return blockingUnaryCall(
          getChannel(), getModuleToDistributeCoinsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * returns coins that are distributed by module so far
     * </pre>
     */
    public osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsResponse moduleDistributedCoins(osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsRequest request) {
      return blockingUnaryCall(
          getChannel(), getModuleDistributedCoinsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * returns Gauge by id
     * </pre>
     */
    public osmosis.incentives.QueryOuterClass.GaugeByIDResponse gaugeByID(osmosis.incentives.QueryOuterClass.GaugeByIDRequest request) {
      return blockingUnaryCall(
          getChannel(), getGaugeByIDMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * returns gauges both upcoming and active
     * </pre>
     */
    public osmosis.incentives.QueryOuterClass.GaugesResponse gauges(osmosis.incentives.QueryOuterClass.GaugesRequest request) {
      return blockingUnaryCall(
          getChannel(), getGaugesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * returns active gauges
     * </pre>
     */
    public osmosis.incentives.QueryOuterClass.ActiveGaugesResponse activeGauges(osmosis.incentives.QueryOuterClass.ActiveGaugesRequest request) {
      return blockingUnaryCall(
          getChannel(), getActiveGaugesMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomResponse activeGaugesPerDenom(osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomRequest request) {
      return blockingUnaryCall(
          getChannel(), getActiveGaugesPerDenomMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * returns scheduled gauges
     * </pre>
     */
    public osmosis.incentives.QueryOuterClass.UpcomingGaugesResponse upcomingGauges(osmosis.incentives.QueryOuterClass.UpcomingGaugesRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpcomingGaugesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RewardsEst returns an estimate of the rewards at a future specific time.
     * The querier either provides an address or a set of locks
     * for which they want to find the associated rewards.
     * </pre>
     */
    public osmosis.incentives.QueryOuterClass.RewardsEstResponse rewardsEst(osmosis.incentives.QueryOuterClass.RewardsEstRequest request) {
      return blockingUnaryCall(
          getChannel(), getRewardsEstMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * returns lockable durations that are valid to give incentives
     * </pre>
     */
    public osmosis.incentives.QueryOuterClass.QueryLockableDurationsResponse lockableDurations(osmosis.incentives.QueryOuterClass.QueryLockableDurationsRequest request) {
      return blockingUnaryCall(
          getChannel(), getLockableDurationsMethod(), getCallOptions(), request);
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
     * returns coins that is going to be distributed
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsResponse> moduleToDistributeCoins(
        osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getModuleToDistributeCoinsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * returns coins that are distributed by module so far
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsResponse> moduleDistributedCoins(
        osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getModuleDistributedCoinsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * returns Gauge by id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.incentives.QueryOuterClass.GaugeByIDResponse> gaugeByID(
        osmosis.incentives.QueryOuterClass.GaugeByIDRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGaugeByIDMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * returns gauges both upcoming and active
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.incentives.QueryOuterClass.GaugesResponse> gauges(
        osmosis.incentives.QueryOuterClass.GaugesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGaugesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * returns active gauges
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.incentives.QueryOuterClass.ActiveGaugesResponse> activeGauges(
        osmosis.incentives.QueryOuterClass.ActiveGaugesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getActiveGaugesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomResponse> activeGaugesPerDenom(
        osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getActiveGaugesPerDenomMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * returns scheduled gauges
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.incentives.QueryOuterClass.UpcomingGaugesResponse> upcomingGauges(
        osmosis.incentives.QueryOuterClass.UpcomingGaugesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpcomingGaugesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RewardsEst returns an estimate of the rewards at a future specific time.
     * The querier either provides an address or a set of locks
     * for which they want to find the associated rewards.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.incentives.QueryOuterClass.RewardsEstResponse> rewardsEst(
        osmosis.incentives.QueryOuterClass.RewardsEstRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRewardsEstMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * returns lockable durations that are valid to give incentives
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.incentives.QueryOuterClass.QueryLockableDurationsResponse> lockableDurations(
        osmosis.incentives.QueryOuterClass.QueryLockableDurationsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLockableDurationsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MODULE_TO_DISTRIBUTE_COINS = 0;
  private static final int METHODID_MODULE_DISTRIBUTED_COINS = 1;
  private static final int METHODID_GAUGE_BY_ID = 2;
  private static final int METHODID_GAUGES = 3;
  private static final int METHODID_ACTIVE_GAUGES = 4;
  private static final int METHODID_ACTIVE_GAUGES_PER_DENOM = 5;
  private static final int METHODID_UPCOMING_GAUGES = 6;
  private static final int METHODID_REWARDS_EST = 7;
  private static final int METHODID_LOCKABLE_DURATIONS = 8;

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
        case METHODID_MODULE_TO_DISTRIBUTE_COINS:
          serviceImpl.moduleToDistributeCoins((osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.ModuleToDistributeCoinsResponse>) responseObserver);
          break;
        case METHODID_MODULE_DISTRIBUTED_COINS:
          serviceImpl.moduleDistributedCoins((osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.ModuleDistributedCoinsResponse>) responseObserver);
          break;
        case METHODID_GAUGE_BY_ID:
          serviceImpl.gaugeByID((osmosis.incentives.QueryOuterClass.GaugeByIDRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.GaugeByIDResponse>) responseObserver);
          break;
        case METHODID_GAUGES:
          serviceImpl.gauges((osmosis.incentives.QueryOuterClass.GaugesRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.GaugesResponse>) responseObserver);
          break;
        case METHODID_ACTIVE_GAUGES:
          serviceImpl.activeGauges((osmosis.incentives.QueryOuterClass.ActiveGaugesRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.ActiveGaugesResponse>) responseObserver);
          break;
        case METHODID_ACTIVE_GAUGES_PER_DENOM:
          serviceImpl.activeGaugesPerDenom((osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.ActiveGaugesPerDenomResponse>) responseObserver);
          break;
        case METHODID_UPCOMING_GAUGES:
          serviceImpl.upcomingGauges((osmosis.incentives.QueryOuterClass.UpcomingGaugesRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.UpcomingGaugesResponse>) responseObserver);
          break;
        case METHODID_REWARDS_EST:
          serviceImpl.rewardsEst((osmosis.incentives.QueryOuterClass.RewardsEstRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.RewardsEstResponse>) responseObserver);
          break;
        case METHODID_LOCKABLE_DURATIONS:
          serviceImpl.lockableDurations((osmosis.incentives.QueryOuterClass.QueryLockableDurationsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.incentives.QueryOuterClass.QueryLockableDurationsResponse>) responseObserver);
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
      return osmosis.incentives.QueryOuterClass.getDescriptor();
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
              .addMethod(getModuleToDistributeCoinsMethod())
              .addMethod(getModuleDistributedCoinsMethod())
              .addMethod(getGaugeByIDMethod())
              .addMethod(getGaugesMethod())
              .addMethod(getActiveGaugesMethod())
              .addMethod(getActiveGaugesPerDenomMethod())
              .addMethod(getUpcomingGaugesMethod())
              .addMethod(getRewardsEstMethod())
              .addMethod(getLockableDurationsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
