package com.stride.records;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: stride/records/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "stride.records.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryParamsRequest,
      com.stride.records.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.stride.records.QueryProto.QueryParamsRequest.class,
      responseType = com.stride.records.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryParamsRequest,
      com.stride.records.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryParamsRequest, com.stride.records.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.stride.records.QueryProto.QueryParamsRequest, com.stride.records.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryGetUserRedemptionRecordRequest,
      com.stride.records.QueryProto.QueryGetUserRedemptionRecordResponse> getUserRedemptionRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UserRedemptionRecord",
      requestType = com.stride.records.QueryProto.QueryGetUserRedemptionRecordRequest.class,
      responseType = com.stride.records.QueryProto.QueryGetUserRedemptionRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryGetUserRedemptionRecordRequest,
      com.stride.records.QueryProto.QueryGetUserRedemptionRecordResponse> getUserRedemptionRecordMethod() {
    io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryGetUserRedemptionRecordRequest, com.stride.records.QueryProto.QueryGetUserRedemptionRecordResponse> getUserRedemptionRecordMethod;
    if ((getUserRedemptionRecordMethod = QueryGrpc.getUserRedemptionRecordMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUserRedemptionRecordMethod = QueryGrpc.getUserRedemptionRecordMethod) == null) {
          QueryGrpc.getUserRedemptionRecordMethod = getUserRedemptionRecordMethod =
              io.grpc.MethodDescriptor.<com.stride.records.QueryProto.QueryGetUserRedemptionRecordRequest, com.stride.records.QueryProto.QueryGetUserRedemptionRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UserRedemptionRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryGetUserRedemptionRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryGetUserRedemptionRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UserRedemptionRecord"))
              .build();
        }
      }
    }
    return getUserRedemptionRecordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryAllUserRedemptionRecordRequest,
      com.stride.records.QueryProto.QueryAllUserRedemptionRecordResponse> getUserRedemptionRecordAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UserRedemptionRecordAll",
      requestType = com.stride.records.QueryProto.QueryAllUserRedemptionRecordRequest.class,
      responseType = com.stride.records.QueryProto.QueryAllUserRedemptionRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryAllUserRedemptionRecordRequest,
      com.stride.records.QueryProto.QueryAllUserRedemptionRecordResponse> getUserRedemptionRecordAllMethod() {
    io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryAllUserRedemptionRecordRequest, com.stride.records.QueryProto.QueryAllUserRedemptionRecordResponse> getUserRedemptionRecordAllMethod;
    if ((getUserRedemptionRecordAllMethod = QueryGrpc.getUserRedemptionRecordAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUserRedemptionRecordAllMethod = QueryGrpc.getUserRedemptionRecordAllMethod) == null) {
          QueryGrpc.getUserRedemptionRecordAllMethod = getUserRedemptionRecordAllMethod =
              io.grpc.MethodDescriptor.<com.stride.records.QueryProto.QueryAllUserRedemptionRecordRequest, com.stride.records.QueryProto.QueryAllUserRedemptionRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UserRedemptionRecordAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryAllUserRedemptionRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryAllUserRedemptionRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UserRedemptionRecordAll"))
              .build();
        }
      }
    }
    return getUserRedemptionRecordAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserRequest,
      com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserResponse> getUserRedemptionRecordForUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UserRedemptionRecordForUser",
      requestType = com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserRequest.class,
      responseType = com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserRequest,
      com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserResponse> getUserRedemptionRecordForUserMethod() {
    io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserRequest, com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserResponse> getUserRedemptionRecordForUserMethod;
    if ((getUserRedemptionRecordForUserMethod = QueryGrpc.getUserRedemptionRecordForUserMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUserRedemptionRecordForUserMethod = QueryGrpc.getUserRedemptionRecordForUserMethod) == null) {
          QueryGrpc.getUserRedemptionRecordForUserMethod = getUserRedemptionRecordForUserMethod =
              io.grpc.MethodDescriptor.<com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserRequest, com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UserRedemptionRecordForUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UserRedemptionRecordForUser"))
              .build();
        }
      }
    }
    return getUserRedemptionRecordForUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryGetEpochUnbondingRecordRequest,
      com.stride.records.QueryProto.QueryGetEpochUnbondingRecordResponse> getEpochUnbondingRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochUnbondingRecord",
      requestType = com.stride.records.QueryProto.QueryGetEpochUnbondingRecordRequest.class,
      responseType = com.stride.records.QueryProto.QueryGetEpochUnbondingRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryGetEpochUnbondingRecordRequest,
      com.stride.records.QueryProto.QueryGetEpochUnbondingRecordResponse> getEpochUnbondingRecordMethod() {
    io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryGetEpochUnbondingRecordRequest, com.stride.records.QueryProto.QueryGetEpochUnbondingRecordResponse> getEpochUnbondingRecordMethod;
    if ((getEpochUnbondingRecordMethod = QueryGrpc.getEpochUnbondingRecordMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochUnbondingRecordMethod = QueryGrpc.getEpochUnbondingRecordMethod) == null) {
          QueryGrpc.getEpochUnbondingRecordMethod = getEpochUnbondingRecordMethod =
              io.grpc.MethodDescriptor.<com.stride.records.QueryProto.QueryGetEpochUnbondingRecordRequest, com.stride.records.QueryProto.QueryGetEpochUnbondingRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochUnbondingRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryGetEpochUnbondingRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryGetEpochUnbondingRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochUnbondingRecord"))
              .build();
        }
      }
    }
    return getEpochUnbondingRecordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryAllEpochUnbondingRecordRequest,
      com.stride.records.QueryProto.QueryAllEpochUnbondingRecordResponse> getEpochUnbondingRecordAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochUnbondingRecordAll",
      requestType = com.stride.records.QueryProto.QueryAllEpochUnbondingRecordRequest.class,
      responseType = com.stride.records.QueryProto.QueryAllEpochUnbondingRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryAllEpochUnbondingRecordRequest,
      com.stride.records.QueryProto.QueryAllEpochUnbondingRecordResponse> getEpochUnbondingRecordAllMethod() {
    io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryAllEpochUnbondingRecordRequest, com.stride.records.QueryProto.QueryAllEpochUnbondingRecordResponse> getEpochUnbondingRecordAllMethod;
    if ((getEpochUnbondingRecordAllMethod = QueryGrpc.getEpochUnbondingRecordAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochUnbondingRecordAllMethod = QueryGrpc.getEpochUnbondingRecordAllMethod) == null) {
          QueryGrpc.getEpochUnbondingRecordAllMethod = getEpochUnbondingRecordAllMethod =
              io.grpc.MethodDescriptor.<com.stride.records.QueryProto.QueryAllEpochUnbondingRecordRequest, com.stride.records.QueryProto.QueryAllEpochUnbondingRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochUnbondingRecordAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryAllEpochUnbondingRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryAllEpochUnbondingRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochUnbondingRecordAll"))
              .build();
        }
      }
    }
    return getEpochUnbondingRecordAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryGetDepositRecordRequest,
      com.stride.records.QueryProto.QueryGetDepositRecordResponse> getDepositRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DepositRecord",
      requestType = com.stride.records.QueryProto.QueryGetDepositRecordRequest.class,
      responseType = com.stride.records.QueryProto.QueryGetDepositRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryGetDepositRecordRequest,
      com.stride.records.QueryProto.QueryGetDepositRecordResponse> getDepositRecordMethod() {
    io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryGetDepositRecordRequest, com.stride.records.QueryProto.QueryGetDepositRecordResponse> getDepositRecordMethod;
    if ((getDepositRecordMethod = QueryGrpc.getDepositRecordMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositRecordMethod = QueryGrpc.getDepositRecordMethod) == null) {
          QueryGrpc.getDepositRecordMethod = getDepositRecordMethod =
              io.grpc.MethodDescriptor.<com.stride.records.QueryProto.QueryGetDepositRecordRequest, com.stride.records.QueryProto.QueryGetDepositRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DepositRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryGetDepositRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryGetDepositRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DepositRecord"))
              .build();
        }
      }
    }
    return getDepositRecordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryAllDepositRecordRequest,
      com.stride.records.QueryProto.QueryAllDepositRecordResponse> getDepositRecordAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DepositRecordAll",
      requestType = com.stride.records.QueryProto.QueryAllDepositRecordRequest.class,
      responseType = com.stride.records.QueryProto.QueryAllDepositRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryAllDepositRecordRequest,
      com.stride.records.QueryProto.QueryAllDepositRecordResponse> getDepositRecordAllMethod() {
    io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryAllDepositRecordRequest, com.stride.records.QueryProto.QueryAllDepositRecordResponse> getDepositRecordAllMethod;
    if ((getDepositRecordAllMethod = QueryGrpc.getDepositRecordAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositRecordAllMethod = QueryGrpc.getDepositRecordAllMethod) == null) {
          QueryGrpc.getDepositRecordAllMethod = getDepositRecordAllMethod =
              io.grpc.MethodDescriptor.<com.stride.records.QueryProto.QueryAllDepositRecordRequest, com.stride.records.QueryProto.QueryAllDepositRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DepositRecordAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryAllDepositRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryAllDepositRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DepositRecordAll"))
              .build();
        }
      }
    }
    return getDepositRecordAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryDepositRecordByHostRequest,
      com.stride.records.QueryProto.QueryDepositRecordByHostResponse> getDepositRecordByHostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DepositRecordByHost",
      requestType = com.stride.records.QueryProto.QueryDepositRecordByHostRequest.class,
      responseType = com.stride.records.QueryProto.QueryDepositRecordByHostResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryDepositRecordByHostRequest,
      com.stride.records.QueryProto.QueryDepositRecordByHostResponse> getDepositRecordByHostMethod() {
    io.grpc.MethodDescriptor<com.stride.records.QueryProto.QueryDepositRecordByHostRequest, com.stride.records.QueryProto.QueryDepositRecordByHostResponse> getDepositRecordByHostMethod;
    if ((getDepositRecordByHostMethod = QueryGrpc.getDepositRecordByHostMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositRecordByHostMethod = QueryGrpc.getDepositRecordByHostMethod) == null) {
          QueryGrpc.getDepositRecordByHostMethod = getDepositRecordByHostMethod =
              io.grpc.MethodDescriptor.<com.stride.records.QueryProto.QueryDepositRecordByHostRequest, com.stride.records.QueryProto.QueryDepositRecordByHostResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DepositRecordByHost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryDepositRecordByHostRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.records.QueryProto.QueryDepositRecordByHostResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DepositRecordByHost"))
              .build();
        }
      }
    }
    return getDepositRecordByHostMethod;
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    default void params(com.stride.records.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a UserRedemptionRecord by id.
     * </pre>
     */
    default void userRedemptionRecord(com.stride.records.QueryProto.QueryGetUserRedemptionRecordRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryGetUserRedemptionRecordResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUserRedemptionRecordMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of UserRedemptionRecord items.
     * </pre>
     */
    default void userRedemptionRecordAll(com.stride.records.QueryProto.QueryAllUserRedemptionRecordRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryAllUserRedemptionRecordResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUserRedemptionRecordAllMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of UserRedemptionRecord items by chainId / userId pair.
     * </pre>
     */
    default void userRedemptionRecordForUser(com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUserRedemptionRecordForUserMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a EpochUnbondingRecord by id.
     * </pre>
     */
    default void epochUnbondingRecord(com.stride.records.QueryProto.QueryGetEpochUnbondingRecordRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryGetEpochUnbondingRecordResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEpochUnbondingRecordMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of EpochUnbondingRecord items.
     * </pre>
     */
    default void epochUnbondingRecordAll(com.stride.records.QueryProto.QueryAllEpochUnbondingRecordRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryAllEpochUnbondingRecordResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEpochUnbondingRecordAllMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a DepositRecord by id.
     * </pre>
     */
    default void depositRecord(com.stride.records.QueryProto.QueryGetDepositRecordRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryGetDepositRecordResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDepositRecordMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of DepositRecord items.
     * </pre>
     */
    default void depositRecordAll(com.stride.records.QueryProto.QueryAllDepositRecordRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryAllDepositRecordResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDepositRecordAllMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of DepositRecord items for a given host zone
     * </pre>
     */
    default void depositRecordByHost(com.stride.records.QueryProto.QueryDepositRecordByHostRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryDepositRecordByHostResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDepositRecordByHostMethod(), responseObserver);
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public void params(com.stride.records.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a UserRedemptionRecord by id.
     * </pre>
     */
    public void userRedemptionRecord(com.stride.records.QueryProto.QueryGetUserRedemptionRecordRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryGetUserRedemptionRecordResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUserRedemptionRecordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of UserRedemptionRecord items.
     * </pre>
     */
    public void userRedemptionRecordAll(com.stride.records.QueryProto.QueryAllUserRedemptionRecordRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryAllUserRedemptionRecordResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUserRedemptionRecordAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of UserRedemptionRecord items by chainId / userId pair.
     * </pre>
     */
    public void userRedemptionRecordForUser(com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUserRedemptionRecordForUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a EpochUnbondingRecord by id.
     * </pre>
     */
    public void epochUnbondingRecord(com.stride.records.QueryProto.QueryGetEpochUnbondingRecordRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryGetEpochUnbondingRecordResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEpochUnbondingRecordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of EpochUnbondingRecord items.
     * </pre>
     */
    public void epochUnbondingRecordAll(com.stride.records.QueryProto.QueryAllEpochUnbondingRecordRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryAllEpochUnbondingRecordResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEpochUnbondingRecordAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a DepositRecord by id.
     * </pre>
     */
    public void depositRecord(com.stride.records.QueryProto.QueryGetDepositRecordRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryGetDepositRecordResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDepositRecordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of DepositRecord items.
     * </pre>
     */
    public void depositRecordAll(com.stride.records.QueryProto.QueryAllDepositRecordRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryAllDepositRecordResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDepositRecordAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of DepositRecord items for a given host zone
     * </pre>
     */
    public void depositRecordByHost(com.stride.records.QueryProto.QueryDepositRecordByHostRequest request,
        io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryDepositRecordByHostResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDepositRecordByHostMethod(), getCallOptions()), request, responseObserver);
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public com.stride.records.QueryProto.QueryParamsResponse params(com.stride.records.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a UserRedemptionRecord by id.
     * </pre>
     */
    public com.stride.records.QueryProto.QueryGetUserRedemptionRecordResponse userRedemptionRecord(com.stride.records.QueryProto.QueryGetUserRedemptionRecordRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUserRedemptionRecordMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of UserRedemptionRecord items.
     * </pre>
     */
    public com.stride.records.QueryProto.QueryAllUserRedemptionRecordResponse userRedemptionRecordAll(com.stride.records.QueryProto.QueryAllUserRedemptionRecordRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUserRedemptionRecordAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of UserRedemptionRecord items by chainId / userId pair.
     * </pre>
     */
    public com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserResponse userRedemptionRecordForUser(com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUserRedemptionRecordForUserMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a EpochUnbondingRecord by id.
     * </pre>
     */
    public com.stride.records.QueryProto.QueryGetEpochUnbondingRecordResponse epochUnbondingRecord(com.stride.records.QueryProto.QueryGetEpochUnbondingRecordRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEpochUnbondingRecordMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of EpochUnbondingRecord items.
     * </pre>
     */
    public com.stride.records.QueryProto.QueryAllEpochUnbondingRecordResponse epochUnbondingRecordAll(com.stride.records.QueryProto.QueryAllEpochUnbondingRecordRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEpochUnbondingRecordAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a DepositRecord by id.
     * </pre>
     */
    public com.stride.records.QueryProto.QueryGetDepositRecordResponse depositRecord(com.stride.records.QueryProto.QueryGetDepositRecordRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDepositRecordMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of DepositRecord items.
     * </pre>
     */
    public com.stride.records.QueryProto.QueryAllDepositRecordResponse depositRecordAll(com.stride.records.QueryProto.QueryAllDepositRecordRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDepositRecordAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of DepositRecord items for a given host zone
     * </pre>
     */
    public com.stride.records.QueryProto.QueryDepositRecordByHostResponse depositRecordByHost(com.stride.records.QueryProto.QueryDepositRecordByHostRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDepositRecordByHostMethod(), getCallOptions(), request);
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.records.QueryProto.QueryParamsResponse> params(
        com.stride.records.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a UserRedemptionRecord by id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.records.QueryProto.QueryGetUserRedemptionRecordResponse> userRedemptionRecord(
        com.stride.records.QueryProto.QueryGetUserRedemptionRecordRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUserRedemptionRecordMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of UserRedemptionRecord items.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.records.QueryProto.QueryAllUserRedemptionRecordResponse> userRedemptionRecordAll(
        com.stride.records.QueryProto.QueryAllUserRedemptionRecordRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUserRedemptionRecordAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of UserRedemptionRecord items by chainId / userId pair.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserResponse> userRedemptionRecordForUser(
        com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUserRedemptionRecordForUserMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a EpochUnbondingRecord by id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.records.QueryProto.QueryGetEpochUnbondingRecordResponse> epochUnbondingRecord(
        com.stride.records.QueryProto.QueryGetEpochUnbondingRecordRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEpochUnbondingRecordMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of EpochUnbondingRecord items.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.records.QueryProto.QueryAllEpochUnbondingRecordResponse> epochUnbondingRecordAll(
        com.stride.records.QueryProto.QueryAllEpochUnbondingRecordRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEpochUnbondingRecordAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a DepositRecord by id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.records.QueryProto.QueryGetDepositRecordResponse> depositRecord(
        com.stride.records.QueryProto.QueryGetDepositRecordRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDepositRecordMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of DepositRecord items.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.records.QueryProto.QueryAllDepositRecordResponse> depositRecordAll(
        com.stride.records.QueryProto.QueryAllDepositRecordRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDepositRecordAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of DepositRecord items for a given host zone
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.records.QueryProto.QueryDepositRecordByHostResponse> depositRecordByHost(
        com.stride.records.QueryProto.QueryDepositRecordByHostRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDepositRecordByHostMethod(), getCallOptions()), request);
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
  private static final int METHODID_DEPOSIT_RECORD_BY_HOST = 8;

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
        case METHODID_PARAMS:
          serviceImpl.params((com.stride.records.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_USER_REDEMPTION_RECORD:
          serviceImpl.userRedemptionRecord((com.stride.records.QueryProto.QueryGetUserRedemptionRecordRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryGetUserRedemptionRecordResponse>) responseObserver);
          break;
        case METHODID_USER_REDEMPTION_RECORD_ALL:
          serviceImpl.userRedemptionRecordAll((com.stride.records.QueryProto.QueryAllUserRedemptionRecordRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryAllUserRedemptionRecordResponse>) responseObserver);
          break;
        case METHODID_USER_REDEMPTION_RECORD_FOR_USER:
          serviceImpl.userRedemptionRecordForUser((com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserResponse>) responseObserver);
          break;
        case METHODID_EPOCH_UNBONDING_RECORD:
          serviceImpl.epochUnbondingRecord((com.stride.records.QueryProto.QueryGetEpochUnbondingRecordRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryGetEpochUnbondingRecordResponse>) responseObserver);
          break;
        case METHODID_EPOCH_UNBONDING_RECORD_ALL:
          serviceImpl.epochUnbondingRecordAll((com.stride.records.QueryProto.QueryAllEpochUnbondingRecordRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryAllEpochUnbondingRecordResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT_RECORD:
          serviceImpl.depositRecord((com.stride.records.QueryProto.QueryGetDepositRecordRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryGetDepositRecordResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT_RECORD_ALL:
          serviceImpl.depositRecordAll((com.stride.records.QueryProto.QueryAllDepositRecordRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryAllDepositRecordResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT_RECORD_BY_HOST:
          serviceImpl.depositRecordByHost((com.stride.records.QueryProto.QueryDepositRecordByHostRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.records.QueryProto.QueryDepositRecordByHostResponse>) responseObserver);
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
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.records.QueryProto.QueryParamsRequest,
              com.stride.records.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getUserRedemptionRecordMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.records.QueryProto.QueryGetUserRedemptionRecordRequest,
              com.stride.records.QueryProto.QueryGetUserRedemptionRecordResponse>(
                service, METHODID_USER_REDEMPTION_RECORD)))
        .addMethod(
          getUserRedemptionRecordAllMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.records.QueryProto.QueryAllUserRedemptionRecordRequest,
              com.stride.records.QueryProto.QueryAllUserRedemptionRecordResponse>(
                service, METHODID_USER_REDEMPTION_RECORD_ALL)))
        .addMethod(
          getUserRedemptionRecordForUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserRequest,
              com.stride.records.QueryProto.QueryAllUserRedemptionRecordForUserResponse>(
                service, METHODID_USER_REDEMPTION_RECORD_FOR_USER)))
        .addMethod(
          getEpochUnbondingRecordMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.records.QueryProto.QueryGetEpochUnbondingRecordRequest,
              com.stride.records.QueryProto.QueryGetEpochUnbondingRecordResponse>(
                service, METHODID_EPOCH_UNBONDING_RECORD)))
        .addMethod(
          getEpochUnbondingRecordAllMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.records.QueryProto.QueryAllEpochUnbondingRecordRequest,
              com.stride.records.QueryProto.QueryAllEpochUnbondingRecordResponse>(
                service, METHODID_EPOCH_UNBONDING_RECORD_ALL)))
        .addMethod(
          getDepositRecordMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.records.QueryProto.QueryGetDepositRecordRequest,
              com.stride.records.QueryProto.QueryGetDepositRecordResponse>(
                service, METHODID_DEPOSIT_RECORD)))
        .addMethod(
          getDepositRecordAllMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.records.QueryProto.QueryAllDepositRecordRequest,
              com.stride.records.QueryProto.QueryAllDepositRecordResponse>(
                service, METHODID_DEPOSIT_RECORD_ALL)))
        .addMethod(
          getDepositRecordByHostMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.records.QueryProto.QueryDepositRecordByHostRequest,
              com.stride.records.QueryProto.QueryDepositRecordByHostResponse>(
                service, METHODID_DEPOSIT_RECORD_BY_HOST)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.stride.records.QueryProto.getDescriptor();
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
              .addMethod(getDepositRecordByHostMethod())
              .build();
        }
      }
    }
    return result;
  }
}
