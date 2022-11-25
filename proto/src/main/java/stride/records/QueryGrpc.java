package stride.records;

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
    comments = "Source: stride/records/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "stride.records.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryParamsRequest,
      stride.records.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = stride.records.QueryOuterClass.QueryParamsRequest.class,
      responseType = stride.records.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryParamsRequest,
      stride.records.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryParamsRequest, stride.records.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<stride.records.QueryOuterClass.QueryParamsRequest, stride.records.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.records.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.records.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryGetUserRedemptionRecordRequest,
      stride.records.QueryOuterClass.QueryGetUserRedemptionRecordResponse> getUserRedemptionRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UserRedemptionRecord",
      requestType = stride.records.QueryOuterClass.QueryGetUserRedemptionRecordRequest.class,
      responseType = stride.records.QueryOuterClass.QueryGetUserRedemptionRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryGetUserRedemptionRecordRequest,
      stride.records.QueryOuterClass.QueryGetUserRedemptionRecordResponse> getUserRedemptionRecordMethod() {
    io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryGetUserRedemptionRecordRequest, stride.records.QueryOuterClass.QueryGetUserRedemptionRecordResponse> getUserRedemptionRecordMethod;
    if ((getUserRedemptionRecordMethod = QueryGrpc.getUserRedemptionRecordMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUserRedemptionRecordMethod = QueryGrpc.getUserRedemptionRecordMethod) == null) {
          QueryGrpc.getUserRedemptionRecordMethod = getUserRedemptionRecordMethod =
              io.grpc.MethodDescriptor.<stride.records.QueryOuterClass.QueryGetUserRedemptionRecordRequest, stride.records.QueryOuterClass.QueryGetUserRedemptionRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UserRedemptionRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.records.QueryOuterClass.QueryGetUserRedemptionRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.records.QueryOuterClass.QueryGetUserRedemptionRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UserRedemptionRecord"))
              .build();
        }
      }
    }
    return getUserRedemptionRecordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryAllUserRedemptionRecordRequest,
      stride.records.QueryOuterClass.QueryAllUserRedemptionRecordResponse> getUserRedemptionRecordAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UserRedemptionRecordAll",
      requestType = stride.records.QueryOuterClass.QueryAllUserRedemptionRecordRequest.class,
      responseType = stride.records.QueryOuterClass.QueryAllUserRedemptionRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryAllUserRedemptionRecordRequest,
      stride.records.QueryOuterClass.QueryAllUserRedemptionRecordResponse> getUserRedemptionRecordAllMethod() {
    io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryAllUserRedemptionRecordRequest, stride.records.QueryOuterClass.QueryAllUserRedemptionRecordResponse> getUserRedemptionRecordAllMethod;
    if ((getUserRedemptionRecordAllMethod = QueryGrpc.getUserRedemptionRecordAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUserRedemptionRecordAllMethod = QueryGrpc.getUserRedemptionRecordAllMethod) == null) {
          QueryGrpc.getUserRedemptionRecordAllMethod = getUserRedemptionRecordAllMethod =
              io.grpc.MethodDescriptor.<stride.records.QueryOuterClass.QueryAllUserRedemptionRecordRequest, stride.records.QueryOuterClass.QueryAllUserRedemptionRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UserRedemptionRecordAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.records.QueryOuterClass.QueryAllUserRedemptionRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.records.QueryOuterClass.QueryAllUserRedemptionRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UserRedemptionRecordAll"))
              .build();
        }
      }
    }
    return getUserRedemptionRecordAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserRequest,
      stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserResponse> getUserRedemptionRecordForUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UserRedemptionRecordForUser",
      requestType = stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserRequest.class,
      responseType = stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserRequest,
      stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserResponse> getUserRedemptionRecordForUserMethod() {
    io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserRequest, stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserResponse> getUserRedemptionRecordForUserMethod;
    if ((getUserRedemptionRecordForUserMethod = QueryGrpc.getUserRedemptionRecordForUserMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUserRedemptionRecordForUserMethod = QueryGrpc.getUserRedemptionRecordForUserMethod) == null) {
          QueryGrpc.getUserRedemptionRecordForUserMethod = getUserRedemptionRecordForUserMethod =
              io.grpc.MethodDescriptor.<stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserRequest, stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UserRedemptionRecordForUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UserRedemptionRecordForUser"))
              .build();
        }
      }
    }
    return getUserRedemptionRecordForUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordRequest,
      stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordResponse> getEpochUnbondingRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochUnbondingRecord",
      requestType = stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordRequest.class,
      responseType = stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordRequest,
      stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordResponse> getEpochUnbondingRecordMethod() {
    io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordRequest, stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordResponse> getEpochUnbondingRecordMethod;
    if ((getEpochUnbondingRecordMethod = QueryGrpc.getEpochUnbondingRecordMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochUnbondingRecordMethod = QueryGrpc.getEpochUnbondingRecordMethod) == null) {
          QueryGrpc.getEpochUnbondingRecordMethod = getEpochUnbondingRecordMethod =
              io.grpc.MethodDescriptor.<stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordRequest, stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochUnbondingRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochUnbondingRecord"))
              .build();
        }
      }
    }
    return getEpochUnbondingRecordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordRequest,
      stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordResponse> getEpochUnbondingRecordAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochUnbondingRecordAll",
      requestType = stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordRequest.class,
      responseType = stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordRequest,
      stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordResponse> getEpochUnbondingRecordAllMethod() {
    io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordRequest, stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordResponse> getEpochUnbondingRecordAllMethod;
    if ((getEpochUnbondingRecordAllMethod = QueryGrpc.getEpochUnbondingRecordAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochUnbondingRecordAllMethod = QueryGrpc.getEpochUnbondingRecordAllMethod) == null) {
          QueryGrpc.getEpochUnbondingRecordAllMethod = getEpochUnbondingRecordAllMethod =
              io.grpc.MethodDescriptor.<stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordRequest, stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochUnbondingRecordAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochUnbondingRecordAll"))
              .build();
        }
      }
    }
    return getEpochUnbondingRecordAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryGetDepositRecordRequest,
      stride.records.QueryOuterClass.QueryGetDepositRecordResponse> getDepositRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DepositRecord",
      requestType = stride.records.QueryOuterClass.QueryGetDepositRecordRequest.class,
      responseType = stride.records.QueryOuterClass.QueryGetDepositRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryGetDepositRecordRequest,
      stride.records.QueryOuterClass.QueryGetDepositRecordResponse> getDepositRecordMethod() {
    io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryGetDepositRecordRequest, stride.records.QueryOuterClass.QueryGetDepositRecordResponse> getDepositRecordMethod;
    if ((getDepositRecordMethod = QueryGrpc.getDepositRecordMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositRecordMethod = QueryGrpc.getDepositRecordMethod) == null) {
          QueryGrpc.getDepositRecordMethod = getDepositRecordMethod =
              io.grpc.MethodDescriptor.<stride.records.QueryOuterClass.QueryGetDepositRecordRequest, stride.records.QueryOuterClass.QueryGetDepositRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DepositRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.records.QueryOuterClass.QueryGetDepositRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.records.QueryOuterClass.QueryGetDepositRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DepositRecord"))
              .build();
        }
      }
    }
    return getDepositRecordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryAllDepositRecordRequest,
      stride.records.QueryOuterClass.QueryAllDepositRecordResponse> getDepositRecordAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DepositRecordAll",
      requestType = stride.records.QueryOuterClass.QueryAllDepositRecordRequest.class,
      responseType = stride.records.QueryOuterClass.QueryAllDepositRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryAllDepositRecordRequest,
      stride.records.QueryOuterClass.QueryAllDepositRecordResponse> getDepositRecordAllMethod() {
    io.grpc.MethodDescriptor<stride.records.QueryOuterClass.QueryAllDepositRecordRequest, stride.records.QueryOuterClass.QueryAllDepositRecordResponse> getDepositRecordAllMethod;
    if ((getDepositRecordAllMethod = QueryGrpc.getDepositRecordAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositRecordAllMethod = QueryGrpc.getDepositRecordAllMethod) == null) {
          QueryGrpc.getDepositRecordAllMethod = getDepositRecordAllMethod =
              io.grpc.MethodDescriptor.<stride.records.QueryOuterClass.QueryAllDepositRecordRequest, stride.records.QueryOuterClass.QueryAllDepositRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DepositRecordAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.records.QueryOuterClass.QueryAllDepositRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.records.QueryOuterClass.QueryAllDepositRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DepositRecordAll"))
              .build();
        }
      }
    }
    return getDepositRecordAllMethod;
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public void params(stride.records.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a UserRedemptionRecord by id.
     * </pre>
     */
    public void userRedemptionRecord(stride.records.QueryOuterClass.QueryGetUserRedemptionRecordRequest request,
        io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryGetUserRedemptionRecordResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUserRedemptionRecordMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of UserRedemptionRecord items.
     * </pre>
     */
    public void userRedemptionRecordAll(stride.records.QueryOuterClass.QueryAllUserRedemptionRecordRequest request,
        io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryAllUserRedemptionRecordResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUserRedemptionRecordAllMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of UserRedemptionRecord items by chainId / userId pair.
     * </pre>
     */
    public void userRedemptionRecordForUser(stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserRequest request,
        io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUserRedemptionRecordForUserMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a EpochUnbondingRecord by id.
     * </pre>
     */
    public void epochUnbondingRecord(stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordRequest request,
        io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEpochUnbondingRecordMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of EpochUnbondingRecord items.
     * </pre>
     */
    public void epochUnbondingRecordAll(stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordRequest request,
        io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEpochUnbondingRecordAllMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a DepositRecord by id.
     * </pre>
     */
    public void depositRecord(stride.records.QueryOuterClass.QueryGetDepositRecordRequest request,
        io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryGetDepositRecordResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositRecordMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of DepositRecord items.
     * </pre>
     */
    public void depositRecordAll(stride.records.QueryOuterClass.QueryAllDepositRecordRequest request,
        io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryAllDepositRecordResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositRecordAllMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.records.QueryOuterClass.QueryParamsRequest,
                stride.records.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getUserRedemptionRecordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.records.QueryOuterClass.QueryGetUserRedemptionRecordRequest,
                stride.records.QueryOuterClass.QueryGetUserRedemptionRecordResponse>(
                  this, METHODID_USER_REDEMPTION_RECORD)))
          .addMethod(
            getUserRedemptionRecordAllMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.records.QueryOuterClass.QueryAllUserRedemptionRecordRequest,
                stride.records.QueryOuterClass.QueryAllUserRedemptionRecordResponse>(
                  this, METHODID_USER_REDEMPTION_RECORD_ALL)))
          .addMethod(
            getUserRedemptionRecordForUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserRequest,
                stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserResponse>(
                  this, METHODID_USER_REDEMPTION_RECORD_FOR_USER)))
          .addMethod(
            getEpochUnbondingRecordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordRequest,
                stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordResponse>(
                  this, METHODID_EPOCH_UNBONDING_RECORD)))
          .addMethod(
            getEpochUnbondingRecordAllMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordRequest,
                stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordResponse>(
                  this, METHODID_EPOCH_UNBONDING_RECORD_ALL)))
          .addMethod(
            getDepositRecordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.records.QueryOuterClass.QueryGetDepositRecordRequest,
                stride.records.QueryOuterClass.QueryGetDepositRecordResponse>(
                  this, METHODID_DEPOSIT_RECORD)))
          .addMethod(
            getDepositRecordAllMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.records.QueryOuterClass.QueryAllDepositRecordRequest,
                stride.records.QueryOuterClass.QueryAllDepositRecordResponse>(
                  this, METHODID_DEPOSIT_RECORD_ALL)))
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public void params(stride.records.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a UserRedemptionRecord by id.
     * </pre>
     */
    public void userRedemptionRecord(stride.records.QueryOuterClass.QueryGetUserRedemptionRecordRequest request,
        io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryGetUserRedemptionRecordResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUserRedemptionRecordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of UserRedemptionRecord items.
     * </pre>
     */
    public void userRedemptionRecordAll(stride.records.QueryOuterClass.QueryAllUserRedemptionRecordRequest request,
        io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryAllUserRedemptionRecordResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUserRedemptionRecordAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of UserRedemptionRecord items by chainId / userId pair.
     * </pre>
     */
    public void userRedemptionRecordForUser(stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserRequest request,
        io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUserRedemptionRecordForUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a EpochUnbondingRecord by id.
     * </pre>
     */
    public void epochUnbondingRecord(stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordRequest request,
        io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEpochUnbondingRecordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of EpochUnbondingRecord items.
     * </pre>
     */
    public void epochUnbondingRecordAll(stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordRequest request,
        io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEpochUnbondingRecordAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a DepositRecord by id.
     * </pre>
     */
    public void depositRecord(stride.records.QueryOuterClass.QueryGetDepositRecordRequest request,
        io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryGetDepositRecordResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositRecordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of DepositRecord items.
     * </pre>
     */
    public void depositRecordAll(stride.records.QueryOuterClass.QueryAllDepositRecordRequest request,
        io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryAllDepositRecordResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositRecordAllMethod(), getCallOptions()), request, responseObserver);
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public stride.records.QueryOuterClass.QueryParamsResponse params(stride.records.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a UserRedemptionRecord by id.
     * </pre>
     */
    public stride.records.QueryOuterClass.QueryGetUserRedemptionRecordResponse userRedemptionRecord(stride.records.QueryOuterClass.QueryGetUserRedemptionRecordRequest request) {
      return blockingUnaryCall(
          getChannel(), getUserRedemptionRecordMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of UserRedemptionRecord items.
     * </pre>
     */
    public stride.records.QueryOuterClass.QueryAllUserRedemptionRecordResponse userRedemptionRecordAll(stride.records.QueryOuterClass.QueryAllUserRedemptionRecordRequest request) {
      return blockingUnaryCall(
          getChannel(), getUserRedemptionRecordAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of UserRedemptionRecord items by chainId / userId pair.
     * </pre>
     */
    public stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserResponse userRedemptionRecordForUser(stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserRequest request) {
      return blockingUnaryCall(
          getChannel(), getUserRedemptionRecordForUserMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a EpochUnbondingRecord by id.
     * </pre>
     */
    public stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordResponse epochUnbondingRecord(stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordRequest request) {
      return blockingUnaryCall(
          getChannel(), getEpochUnbondingRecordMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of EpochUnbondingRecord items.
     * </pre>
     */
    public stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordResponse epochUnbondingRecordAll(stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordRequest request) {
      return blockingUnaryCall(
          getChannel(), getEpochUnbondingRecordAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a DepositRecord by id.
     * </pre>
     */
    public stride.records.QueryOuterClass.QueryGetDepositRecordResponse depositRecord(stride.records.QueryOuterClass.QueryGetDepositRecordRequest request) {
      return blockingUnaryCall(
          getChannel(), getDepositRecordMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of DepositRecord items.
     * </pre>
     */
    public stride.records.QueryOuterClass.QueryAllDepositRecordResponse depositRecordAll(stride.records.QueryOuterClass.QueryAllDepositRecordRequest request) {
      return blockingUnaryCall(
          getChannel(), getDepositRecordAllMethod(), getCallOptions(), request);
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.records.QueryOuterClass.QueryParamsResponse> params(
        stride.records.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a UserRedemptionRecord by id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.records.QueryOuterClass.QueryGetUserRedemptionRecordResponse> userRedemptionRecord(
        stride.records.QueryOuterClass.QueryGetUserRedemptionRecordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUserRedemptionRecordMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of UserRedemptionRecord items.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.records.QueryOuterClass.QueryAllUserRedemptionRecordResponse> userRedemptionRecordAll(
        stride.records.QueryOuterClass.QueryAllUserRedemptionRecordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUserRedemptionRecordAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of UserRedemptionRecord items by chainId / userId pair.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserResponse> userRedemptionRecordForUser(
        stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUserRedemptionRecordForUserMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a EpochUnbondingRecord by id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordResponse> epochUnbondingRecord(
        stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEpochUnbondingRecordMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of EpochUnbondingRecord items.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordResponse> epochUnbondingRecordAll(
        stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEpochUnbondingRecordAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a DepositRecord by id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.records.QueryOuterClass.QueryGetDepositRecordResponse> depositRecord(
        stride.records.QueryOuterClass.QueryGetDepositRecordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositRecordMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of DepositRecord items.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.records.QueryOuterClass.QueryAllDepositRecordResponse> depositRecordAll(
        stride.records.QueryOuterClass.QueryAllDepositRecordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositRecordAllMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_USER_REDEMPTION_RECORD = 1;
  private static final int METHODID_USER_REDEMPTION_RECORD_ALL = 2;
  private static final int METHODID_USER_REDEMPTION_RECORD_FOR_USER = 3;
  private static final int METHODID_EPOCH_UNBONDING_RECORD = 4;
  private static final int METHODID_EPOCH_UNBONDING_RECORD_ALL = 5;
  private static final int METHODID_DEPOSIT_RECORD = 6;
  private static final int METHODID_DEPOSIT_RECORD_ALL = 7;

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
          serviceImpl.params((stride.records.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_USER_REDEMPTION_RECORD:
          serviceImpl.userRedemptionRecord((stride.records.QueryOuterClass.QueryGetUserRedemptionRecordRequest) request,
              (io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryGetUserRedemptionRecordResponse>) responseObserver);
          break;
        case METHODID_USER_REDEMPTION_RECORD_ALL:
          serviceImpl.userRedemptionRecordAll((stride.records.QueryOuterClass.QueryAllUserRedemptionRecordRequest) request,
              (io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryAllUserRedemptionRecordResponse>) responseObserver);
          break;
        case METHODID_USER_REDEMPTION_RECORD_FOR_USER:
          serviceImpl.userRedemptionRecordForUser((stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserRequest) request,
              (io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserResponse>) responseObserver);
          break;
        case METHODID_EPOCH_UNBONDING_RECORD:
          serviceImpl.epochUnbondingRecord((stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordRequest) request,
              (io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryGetEpochUnbondingRecordResponse>) responseObserver);
          break;
        case METHODID_EPOCH_UNBONDING_RECORD_ALL:
          serviceImpl.epochUnbondingRecordAll((stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordRequest) request,
              (io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryAllEpochUnbondingRecordResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT_RECORD:
          serviceImpl.depositRecord((stride.records.QueryOuterClass.QueryGetDepositRecordRequest) request,
              (io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryGetDepositRecordResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT_RECORD_ALL:
          serviceImpl.depositRecordAll((stride.records.QueryOuterClass.QueryAllDepositRecordRequest) request,
              (io.grpc.stub.StreamObserver<stride.records.QueryOuterClass.QueryAllDepositRecordResponse>) responseObserver);
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
      return stride.records.QueryOuterClass.getDescriptor();
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
              .addMethod(getUserRedemptionRecordMethod())
              .addMethod(getUserRedemptionRecordAllMethod())
              .addMethod(getUserRedemptionRecordForUserMethod())
              .addMethod(getEpochUnbondingRecordMethod())
              .addMethod(getEpochUnbondingRecordAllMethod())
              .addMethod(getDepositRecordMethod())
              .addMethod(getDepositRecordAllMethod())
              .build();
        }
      }
    }
    return result;
  }
}
