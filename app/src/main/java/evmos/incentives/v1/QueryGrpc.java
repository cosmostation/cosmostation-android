package evmos.incentives.v1;

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
    comments = "Source: evmos/incentives/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "evmos.incentives.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryIncentivesRequest,
      evmos.incentives.v1.QueryOuterClass.QueryIncentivesResponse> getIncentivesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Incentives",
      requestType = evmos.incentives.v1.QueryOuterClass.QueryIncentivesRequest.class,
      responseType = evmos.incentives.v1.QueryOuterClass.QueryIncentivesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryIncentivesRequest,
      evmos.incentives.v1.QueryOuterClass.QueryIncentivesResponse> getIncentivesMethod() {
    io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryIncentivesRequest, evmos.incentives.v1.QueryOuterClass.QueryIncentivesResponse> getIncentivesMethod;
    if ((getIncentivesMethod = QueryGrpc.getIncentivesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getIncentivesMethod = QueryGrpc.getIncentivesMethod) == null) {
          QueryGrpc.getIncentivesMethod = getIncentivesMethod =
              io.grpc.MethodDescriptor.<evmos.incentives.v1.QueryOuterClass.QueryIncentivesRequest, evmos.incentives.v1.QueryOuterClass.QueryIncentivesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Incentives"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.incentives.v1.QueryOuterClass.QueryIncentivesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.incentives.v1.QueryOuterClass.QueryIncentivesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Incentives"))
              .build();
        }
      }
    }
    return getIncentivesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryIncentiveRequest,
      evmos.incentives.v1.QueryOuterClass.QueryIncentiveResponse> getIncentiveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Incentive",
      requestType = evmos.incentives.v1.QueryOuterClass.QueryIncentiveRequest.class,
      responseType = evmos.incentives.v1.QueryOuterClass.QueryIncentiveResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryIncentiveRequest,
      evmos.incentives.v1.QueryOuterClass.QueryIncentiveResponse> getIncentiveMethod() {
    io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryIncentiveRequest, evmos.incentives.v1.QueryOuterClass.QueryIncentiveResponse> getIncentiveMethod;
    if ((getIncentiveMethod = QueryGrpc.getIncentiveMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getIncentiveMethod = QueryGrpc.getIncentiveMethod) == null) {
          QueryGrpc.getIncentiveMethod = getIncentiveMethod =
              io.grpc.MethodDescriptor.<evmos.incentives.v1.QueryOuterClass.QueryIncentiveRequest, evmos.incentives.v1.QueryOuterClass.QueryIncentiveResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Incentive"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.incentives.v1.QueryOuterClass.QueryIncentiveRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.incentives.v1.QueryOuterClass.QueryIncentiveResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Incentive"))
              .build();
        }
      }
    }
    return getIncentiveMethod;
  }

  private static volatile io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryGasMetersRequest,
      evmos.incentives.v1.QueryOuterClass.QueryGasMetersResponse> getGasMetersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GasMeters",
      requestType = evmos.incentives.v1.QueryOuterClass.QueryGasMetersRequest.class,
      responseType = evmos.incentives.v1.QueryOuterClass.QueryGasMetersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryGasMetersRequest,
      evmos.incentives.v1.QueryOuterClass.QueryGasMetersResponse> getGasMetersMethod() {
    io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryGasMetersRequest, evmos.incentives.v1.QueryOuterClass.QueryGasMetersResponse> getGasMetersMethod;
    if ((getGasMetersMethod = QueryGrpc.getGasMetersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGasMetersMethod = QueryGrpc.getGasMetersMethod) == null) {
          QueryGrpc.getGasMetersMethod = getGasMetersMethod =
              io.grpc.MethodDescriptor.<evmos.incentives.v1.QueryOuterClass.QueryGasMetersRequest, evmos.incentives.v1.QueryOuterClass.QueryGasMetersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GasMeters"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.incentives.v1.QueryOuterClass.QueryGasMetersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.incentives.v1.QueryOuterClass.QueryGasMetersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GasMeters"))
              .build();
        }
      }
    }
    return getGasMetersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryGasMeterRequest,
      evmos.incentives.v1.QueryOuterClass.QueryGasMeterResponse> getGasMeterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GasMeter",
      requestType = evmos.incentives.v1.QueryOuterClass.QueryGasMeterRequest.class,
      responseType = evmos.incentives.v1.QueryOuterClass.QueryGasMeterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryGasMeterRequest,
      evmos.incentives.v1.QueryOuterClass.QueryGasMeterResponse> getGasMeterMethod() {
    io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryGasMeterRequest, evmos.incentives.v1.QueryOuterClass.QueryGasMeterResponse> getGasMeterMethod;
    if ((getGasMeterMethod = QueryGrpc.getGasMeterMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGasMeterMethod = QueryGrpc.getGasMeterMethod) == null) {
          QueryGrpc.getGasMeterMethod = getGasMeterMethod =
              io.grpc.MethodDescriptor.<evmos.incentives.v1.QueryOuterClass.QueryGasMeterRequest, evmos.incentives.v1.QueryOuterClass.QueryGasMeterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GasMeter"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.incentives.v1.QueryOuterClass.QueryGasMeterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.incentives.v1.QueryOuterClass.QueryGasMeterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GasMeter"))
              .build();
        }
      }
    }
    return getGasMeterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersRequest,
      evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersResponse> getAllocationMetersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllocationMeters",
      requestType = evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersRequest.class,
      responseType = evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersRequest,
      evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersResponse> getAllocationMetersMethod() {
    io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersRequest, evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersResponse> getAllocationMetersMethod;
    if ((getAllocationMetersMethod = QueryGrpc.getAllocationMetersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllocationMetersMethod = QueryGrpc.getAllocationMetersMethod) == null) {
          QueryGrpc.getAllocationMetersMethod = getAllocationMetersMethod =
              io.grpc.MethodDescriptor.<evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersRequest, evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllocationMeters"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllocationMeters"))
              .build();
        }
      }
    }
    return getAllocationMetersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterRequest,
      evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterResponse> getAllocationMeterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllocationMeter",
      requestType = evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterRequest.class,
      responseType = evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterRequest,
      evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterResponse> getAllocationMeterMethod() {
    io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterRequest, evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterResponse> getAllocationMeterMethod;
    if ((getAllocationMeterMethod = QueryGrpc.getAllocationMeterMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllocationMeterMethod = QueryGrpc.getAllocationMeterMethod) == null) {
          QueryGrpc.getAllocationMeterMethod = getAllocationMeterMethod =
              io.grpc.MethodDescriptor.<evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterRequest, evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllocationMeter"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllocationMeter"))
              .build();
        }
      }
    }
    return getAllocationMeterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryParamsRequest,
      evmos.incentives.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = evmos.incentives.v1.QueryOuterClass.QueryParamsRequest.class,
      responseType = evmos.incentives.v1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryParamsRequest,
      evmos.incentives.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<evmos.incentives.v1.QueryOuterClass.QueryParamsRequest, evmos.incentives.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<evmos.incentives.v1.QueryOuterClass.QueryParamsRequest, evmos.incentives.v1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.incentives.v1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.incentives.v1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
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
     * Incentives retrieves registered incentives
     * </pre>
     */
    public void incentives(evmos.incentives.v1.QueryOuterClass.QueryIncentivesRequest request,
        io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryIncentivesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getIncentivesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Incentive retrieves a registered incentive
     * </pre>
     */
    public void incentive(evmos.incentives.v1.QueryOuterClass.QueryIncentiveRequest request,
        io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryIncentiveResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getIncentiveMethod(), responseObserver);
    }

    /**
     * <pre>
     * GasMeters retrieves active gas meters for a given contract
     * </pre>
     */
    public void gasMeters(evmos.incentives.v1.QueryOuterClass.QueryGasMetersRequest request,
        io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryGasMetersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGasMetersMethod(), responseObserver);
    }

    /**
     * <pre>
     * GasMeter Retrieves a active gas meter
     * </pre>
     */
    public void gasMeter(evmos.incentives.v1.QueryOuterClass.QueryGasMeterRequest request,
        io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryGasMeterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGasMeterMethod(), responseObserver);
    }

    /**
     * <pre>
     * AllocationMeters retrieves active allocation meters for a given
     * denomination
     * </pre>
     */
    public void allocationMeters(evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersRequest request,
        io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAllocationMetersMethod(), responseObserver);
    }

    /**
     * <pre>
     * AllocationMeter Retrieves a active gas meter
     * </pre>
     */
    public void allocationMeter(evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterRequest request,
        io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAllocationMeterMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params retrieves the incentives module params
     * </pre>
     */
    public void params(evmos.incentives.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getIncentivesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                evmos.incentives.v1.QueryOuterClass.QueryIncentivesRequest,
                evmos.incentives.v1.QueryOuterClass.QueryIncentivesResponse>(
                  this, METHODID_INCENTIVES)))
          .addMethod(
            getIncentiveMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                evmos.incentives.v1.QueryOuterClass.QueryIncentiveRequest,
                evmos.incentives.v1.QueryOuterClass.QueryIncentiveResponse>(
                  this, METHODID_INCENTIVE)))
          .addMethod(
            getGasMetersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                evmos.incentives.v1.QueryOuterClass.QueryGasMetersRequest,
                evmos.incentives.v1.QueryOuterClass.QueryGasMetersResponse>(
                  this, METHODID_GAS_METERS)))
          .addMethod(
            getGasMeterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                evmos.incentives.v1.QueryOuterClass.QueryGasMeterRequest,
                evmos.incentives.v1.QueryOuterClass.QueryGasMeterResponse>(
                  this, METHODID_GAS_METER)))
          .addMethod(
            getAllocationMetersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersRequest,
                evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersResponse>(
                  this, METHODID_ALLOCATION_METERS)))
          .addMethod(
            getAllocationMeterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterRequest,
                evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterResponse>(
                  this, METHODID_ALLOCATION_METER)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                evmos.incentives.v1.QueryOuterClass.QueryParamsRequest,
                evmos.incentives.v1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
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
     * Incentives retrieves registered incentives
     * </pre>
     */
    public void incentives(evmos.incentives.v1.QueryOuterClass.QueryIncentivesRequest request,
        io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryIncentivesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIncentivesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Incentive retrieves a registered incentive
     * </pre>
     */
    public void incentive(evmos.incentives.v1.QueryOuterClass.QueryIncentiveRequest request,
        io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryIncentiveResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIncentiveMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GasMeters retrieves active gas meters for a given contract
     * </pre>
     */
    public void gasMeters(evmos.incentives.v1.QueryOuterClass.QueryGasMetersRequest request,
        io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryGasMetersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGasMetersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GasMeter Retrieves a active gas meter
     * </pre>
     */
    public void gasMeter(evmos.incentives.v1.QueryOuterClass.QueryGasMeterRequest request,
        io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryGasMeterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGasMeterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AllocationMeters retrieves active allocation meters for a given
     * denomination
     * </pre>
     */
    public void allocationMeters(evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersRequest request,
        io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAllocationMetersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AllocationMeter Retrieves a active gas meter
     * </pre>
     */
    public void allocationMeter(evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterRequest request,
        io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAllocationMeterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params retrieves the incentives module params
     * </pre>
     */
    public void params(evmos.incentives.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
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
     * Incentives retrieves registered incentives
     * </pre>
     */
    public evmos.incentives.v1.QueryOuterClass.QueryIncentivesResponse incentives(evmos.incentives.v1.QueryOuterClass.QueryIncentivesRequest request) {
      return blockingUnaryCall(
          getChannel(), getIncentivesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Incentive retrieves a registered incentive
     * </pre>
     */
    public evmos.incentives.v1.QueryOuterClass.QueryIncentiveResponse incentive(evmos.incentives.v1.QueryOuterClass.QueryIncentiveRequest request) {
      return blockingUnaryCall(
          getChannel(), getIncentiveMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GasMeters retrieves active gas meters for a given contract
     * </pre>
     */
    public evmos.incentives.v1.QueryOuterClass.QueryGasMetersResponse gasMeters(evmos.incentives.v1.QueryOuterClass.QueryGasMetersRequest request) {
      return blockingUnaryCall(
          getChannel(), getGasMetersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GasMeter Retrieves a active gas meter
     * </pre>
     */
    public evmos.incentives.v1.QueryOuterClass.QueryGasMeterResponse gasMeter(evmos.incentives.v1.QueryOuterClass.QueryGasMeterRequest request) {
      return blockingUnaryCall(
          getChannel(), getGasMeterMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AllocationMeters retrieves active allocation meters for a given
     * denomination
     * </pre>
     */
    public evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersResponse allocationMeters(evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersRequest request) {
      return blockingUnaryCall(
          getChannel(), getAllocationMetersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AllocationMeter Retrieves a active gas meter
     * </pre>
     */
    public evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterResponse allocationMeter(evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterRequest request) {
      return blockingUnaryCall(
          getChannel(), getAllocationMeterMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params retrieves the incentives module params
     * </pre>
     */
    public evmos.incentives.v1.QueryOuterClass.QueryParamsResponse params(evmos.incentives.v1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
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
     * Incentives retrieves registered incentives
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<evmos.incentives.v1.QueryOuterClass.QueryIncentivesResponse> incentives(
        evmos.incentives.v1.QueryOuterClass.QueryIncentivesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getIncentivesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Incentive retrieves a registered incentive
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<evmos.incentives.v1.QueryOuterClass.QueryIncentiveResponse> incentive(
        evmos.incentives.v1.QueryOuterClass.QueryIncentiveRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getIncentiveMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GasMeters retrieves active gas meters for a given contract
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<evmos.incentives.v1.QueryOuterClass.QueryGasMetersResponse> gasMeters(
        evmos.incentives.v1.QueryOuterClass.QueryGasMetersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGasMetersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GasMeter Retrieves a active gas meter
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<evmos.incentives.v1.QueryOuterClass.QueryGasMeterResponse> gasMeter(
        evmos.incentives.v1.QueryOuterClass.QueryGasMeterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGasMeterMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AllocationMeters retrieves active allocation meters for a given
     * denomination
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersResponse> allocationMeters(
        evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAllocationMetersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AllocationMeter Retrieves a active gas meter
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterResponse> allocationMeter(
        evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAllocationMeterMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params retrieves the incentives module params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<evmos.incentives.v1.QueryOuterClass.QueryParamsResponse> params(
        evmos.incentives.v1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INCENTIVES = 0;
  private static final int METHODID_INCENTIVE = 1;
  private static final int METHODID_GAS_METERS = 2;
  private static final int METHODID_GAS_METER = 3;
  private static final int METHODID_ALLOCATION_METERS = 4;
  private static final int METHODID_ALLOCATION_METER = 5;
  private static final int METHODID_PARAMS = 6;

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
        case METHODID_INCENTIVES:
          serviceImpl.incentives((evmos.incentives.v1.QueryOuterClass.QueryIncentivesRequest) request,
              (io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryIncentivesResponse>) responseObserver);
          break;
        case METHODID_INCENTIVE:
          serviceImpl.incentive((evmos.incentives.v1.QueryOuterClass.QueryIncentiveRequest) request,
              (io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryIncentiveResponse>) responseObserver);
          break;
        case METHODID_GAS_METERS:
          serviceImpl.gasMeters((evmos.incentives.v1.QueryOuterClass.QueryGasMetersRequest) request,
              (io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryGasMetersResponse>) responseObserver);
          break;
        case METHODID_GAS_METER:
          serviceImpl.gasMeter((evmos.incentives.v1.QueryOuterClass.QueryGasMeterRequest) request,
              (io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryGasMeterResponse>) responseObserver);
          break;
        case METHODID_ALLOCATION_METERS:
          serviceImpl.allocationMeters((evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersRequest) request,
              (io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryAllocationMetersResponse>) responseObserver);
          break;
        case METHODID_ALLOCATION_METER:
          serviceImpl.allocationMeter((evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterRequest) request,
              (io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryAllocationMeterResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((evmos.incentives.v1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<evmos.incentives.v1.QueryOuterClass.QueryParamsResponse>) responseObserver);
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
      return evmos.incentives.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getIncentivesMethod())
              .addMethod(getIncentiveMethod())
              .addMethod(getGasMetersMethod())
              .addMethod(getGasMeterMethod())
              .addMethod(getAllocationMetersMethod())
              .addMethod(getAllocationMeterMethod())
              .addMethod(getParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
