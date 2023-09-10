package com.stride.claim;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: stride/claim/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "stride.claim.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryDistributorAccountBalanceRequest,
      com.stride.claim.QueryProto.QueryDistributorAccountBalanceResponse> getDistributorAccountBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DistributorAccountBalance",
      requestType = com.stride.claim.QueryProto.QueryDistributorAccountBalanceRequest.class,
      responseType = com.stride.claim.QueryProto.QueryDistributorAccountBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryDistributorAccountBalanceRequest,
      com.stride.claim.QueryProto.QueryDistributorAccountBalanceResponse> getDistributorAccountBalanceMethod() {
    io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryDistributorAccountBalanceRequest, com.stride.claim.QueryProto.QueryDistributorAccountBalanceResponse> getDistributorAccountBalanceMethod;
    if ((getDistributorAccountBalanceMethod = QueryGrpc.getDistributorAccountBalanceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDistributorAccountBalanceMethod = QueryGrpc.getDistributorAccountBalanceMethod) == null) {
          QueryGrpc.getDistributorAccountBalanceMethod = getDistributorAccountBalanceMethod =
              io.grpc.MethodDescriptor.<com.stride.claim.QueryProto.QueryDistributorAccountBalanceRequest, com.stride.claim.QueryProto.QueryDistributorAccountBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DistributorAccountBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.QueryProto.QueryDistributorAccountBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.QueryProto.QueryDistributorAccountBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DistributorAccountBalance"))
              .build();
        }
      }
    }
    return getDistributorAccountBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryParamsRequest,
      com.stride.claim.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.stride.claim.QueryProto.QueryParamsRequest.class,
      responseType = com.stride.claim.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryParamsRequest,
      com.stride.claim.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryParamsRequest, com.stride.claim.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.stride.claim.QueryProto.QueryParamsRequest, com.stride.claim.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryClaimRecordRequest,
      com.stride.claim.QueryProto.QueryClaimRecordResponse> getClaimRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimRecord",
      requestType = com.stride.claim.QueryProto.QueryClaimRecordRequest.class,
      responseType = com.stride.claim.QueryProto.QueryClaimRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryClaimRecordRequest,
      com.stride.claim.QueryProto.QueryClaimRecordResponse> getClaimRecordMethod() {
    io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryClaimRecordRequest, com.stride.claim.QueryProto.QueryClaimRecordResponse> getClaimRecordMethod;
    if ((getClaimRecordMethod = QueryGrpc.getClaimRecordMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClaimRecordMethod = QueryGrpc.getClaimRecordMethod) == null) {
          QueryGrpc.getClaimRecordMethod = getClaimRecordMethod =
              io.grpc.MethodDescriptor.<com.stride.claim.QueryProto.QueryClaimRecordRequest, com.stride.claim.QueryProto.QueryClaimRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.QueryProto.QueryClaimRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.QueryProto.QueryClaimRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClaimRecord"))
              .build();
        }
      }
    }
    return getClaimRecordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryClaimableForActionRequest,
      com.stride.claim.QueryProto.QueryClaimableForActionResponse> getClaimableForActionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimableForAction",
      requestType = com.stride.claim.QueryProto.QueryClaimableForActionRequest.class,
      responseType = com.stride.claim.QueryProto.QueryClaimableForActionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryClaimableForActionRequest,
      com.stride.claim.QueryProto.QueryClaimableForActionResponse> getClaimableForActionMethod() {
    io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryClaimableForActionRequest, com.stride.claim.QueryProto.QueryClaimableForActionResponse> getClaimableForActionMethod;
    if ((getClaimableForActionMethod = QueryGrpc.getClaimableForActionMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClaimableForActionMethod = QueryGrpc.getClaimableForActionMethod) == null) {
          QueryGrpc.getClaimableForActionMethod = getClaimableForActionMethod =
              io.grpc.MethodDescriptor.<com.stride.claim.QueryProto.QueryClaimableForActionRequest, com.stride.claim.QueryProto.QueryClaimableForActionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimableForAction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.QueryProto.QueryClaimableForActionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.QueryProto.QueryClaimableForActionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClaimableForAction"))
              .build();
        }
      }
    }
    return getClaimableForActionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryTotalClaimableRequest,
      com.stride.claim.QueryProto.QueryTotalClaimableResponse> getTotalClaimableMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalClaimable",
      requestType = com.stride.claim.QueryProto.QueryTotalClaimableRequest.class,
      responseType = com.stride.claim.QueryProto.QueryTotalClaimableResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryTotalClaimableRequest,
      com.stride.claim.QueryProto.QueryTotalClaimableResponse> getTotalClaimableMethod() {
    io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryTotalClaimableRequest, com.stride.claim.QueryProto.QueryTotalClaimableResponse> getTotalClaimableMethod;
    if ((getTotalClaimableMethod = QueryGrpc.getTotalClaimableMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalClaimableMethod = QueryGrpc.getTotalClaimableMethod) == null) {
          QueryGrpc.getTotalClaimableMethod = getTotalClaimableMethod =
              io.grpc.MethodDescriptor.<com.stride.claim.QueryProto.QueryTotalClaimableRequest, com.stride.claim.QueryProto.QueryTotalClaimableResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalClaimable"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.QueryProto.QueryTotalClaimableRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.QueryProto.QueryTotalClaimableResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalClaimable"))
              .build();
        }
      }
    }
    return getTotalClaimableMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryUserVestingsRequest,
      com.stride.claim.QueryProto.QueryUserVestingsResponse> getUserVestingsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UserVestings",
      requestType = com.stride.claim.QueryProto.QueryUserVestingsRequest.class,
      responseType = com.stride.claim.QueryProto.QueryUserVestingsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryUserVestingsRequest,
      com.stride.claim.QueryProto.QueryUserVestingsResponse> getUserVestingsMethod() {
    io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryUserVestingsRequest, com.stride.claim.QueryProto.QueryUserVestingsResponse> getUserVestingsMethod;
    if ((getUserVestingsMethod = QueryGrpc.getUserVestingsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUserVestingsMethod = QueryGrpc.getUserVestingsMethod) == null) {
          QueryGrpc.getUserVestingsMethod = getUserVestingsMethod =
              io.grpc.MethodDescriptor.<com.stride.claim.QueryProto.QueryUserVestingsRequest, com.stride.claim.QueryProto.QueryUserVestingsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UserVestings"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.QueryProto.QueryUserVestingsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.QueryProto.QueryUserVestingsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UserVestings"))
              .build();
        }
      }
    }
    return getUserVestingsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryClaimStatusRequest,
      com.stride.claim.QueryProto.QueryClaimStatusResponse> getClaimStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimStatus",
      requestType = com.stride.claim.QueryProto.QueryClaimStatusRequest.class,
      responseType = com.stride.claim.QueryProto.QueryClaimStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryClaimStatusRequest,
      com.stride.claim.QueryProto.QueryClaimStatusResponse> getClaimStatusMethod() {
    io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryClaimStatusRequest, com.stride.claim.QueryProto.QueryClaimStatusResponse> getClaimStatusMethod;
    if ((getClaimStatusMethod = QueryGrpc.getClaimStatusMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClaimStatusMethod = QueryGrpc.getClaimStatusMethod) == null) {
          QueryGrpc.getClaimStatusMethod = getClaimStatusMethod =
              io.grpc.MethodDescriptor.<com.stride.claim.QueryProto.QueryClaimStatusRequest, com.stride.claim.QueryProto.QueryClaimStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.QueryProto.QueryClaimStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.QueryProto.QueryClaimStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClaimStatus"))
              .build();
        }
      }
    }
    return getClaimStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryClaimMetadataRequest,
      com.stride.claim.QueryProto.QueryClaimMetadataResponse> getClaimMetadataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimMetadata",
      requestType = com.stride.claim.QueryProto.QueryClaimMetadataRequest.class,
      responseType = com.stride.claim.QueryProto.QueryClaimMetadataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryClaimMetadataRequest,
      com.stride.claim.QueryProto.QueryClaimMetadataResponse> getClaimMetadataMethod() {
    io.grpc.MethodDescriptor<com.stride.claim.QueryProto.QueryClaimMetadataRequest, com.stride.claim.QueryProto.QueryClaimMetadataResponse> getClaimMetadataMethod;
    if ((getClaimMetadataMethod = QueryGrpc.getClaimMetadataMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClaimMetadataMethod = QueryGrpc.getClaimMetadataMethod) == null) {
          QueryGrpc.getClaimMetadataMethod = getClaimMetadataMethod =
              io.grpc.MethodDescriptor.<com.stride.claim.QueryProto.QueryClaimMetadataRequest, com.stride.claim.QueryProto.QueryClaimMetadataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimMetadata"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.QueryProto.QueryClaimMetadataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.QueryProto.QueryClaimMetadataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClaimMetadata"))
              .build();
        }
      }
    }
    return getClaimMetadataMethod;
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
     */
    default void distributorAccountBalance(com.stride.claim.QueryProto.QueryDistributorAccountBalanceRequest request,
        io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryDistributorAccountBalanceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDistributorAccountBalanceMethod(), responseObserver);
    }

    /**
     */
    default void params(com.stride.claim.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     */
    default void claimRecord(com.stride.claim.QueryProto.QueryClaimRecordRequest request,
        io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryClaimRecordResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClaimRecordMethod(), responseObserver);
    }

    /**
     */
    default void claimableForAction(com.stride.claim.QueryProto.QueryClaimableForActionRequest request,
        io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryClaimableForActionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClaimableForActionMethod(), responseObserver);
    }

    /**
     */
    default void totalClaimable(com.stride.claim.QueryProto.QueryTotalClaimableRequest request,
        io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryTotalClaimableResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTotalClaimableMethod(), responseObserver);
    }

    /**
     */
    default void userVestings(com.stride.claim.QueryProto.QueryUserVestingsRequest request,
        io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryUserVestingsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUserVestingsMethod(), responseObserver);
    }

    /**
     */
    default void claimStatus(com.stride.claim.QueryProto.QueryClaimStatusRequest request,
        io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryClaimStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClaimStatusMethod(), responseObserver);
    }

    /**
     */
    default void claimMetadata(com.stride.claim.QueryProto.QueryClaimMetadataRequest request,
        io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryClaimMetadataResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClaimMetadataMethod(), responseObserver);
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
     */
    public void distributorAccountBalance(com.stride.claim.QueryProto.QueryDistributorAccountBalanceRequest request,
        io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryDistributorAccountBalanceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDistributorAccountBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void params(com.stride.claim.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void claimRecord(com.stride.claim.QueryProto.QueryClaimRecordRequest request,
        io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryClaimRecordResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClaimRecordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void claimableForAction(com.stride.claim.QueryProto.QueryClaimableForActionRequest request,
        io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryClaimableForActionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClaimableForActionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void totalClaimable(com.stride.claim.QueryProto.QueryTotalClaimableRequest request,
        io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryTotalClaimableResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTotalClaimableMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void userVestings(com.stride.claim.QueryProto.QueryUserVestingsRequest request,
        io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryUserVestingsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUserVestingsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void claimStatus(com.stride.claim.QueryProto.QueryClaimStatusRequest request,
        io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryClaimStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClaimStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void claimMetadata(com.stride.claim.QueryProto.QueryClaimMetadataRequest request,
        io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryClaimMetadataResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClaimMetadataMethod(), getCallOptions()), request, responseObserver);
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
     */
    public com.stride.claim.QueryProto.QueryDistributorAccountBalanceResponse distributorAccountBalance(com.stride.claim.QueryProto.QueryDistributorAccountBalanceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDistributorAccountBalanceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.stride.claim.QueryProto.QueryParamsResponse params(com.stride.claim.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.stride.claim.QueryProto.QueryClaimRecordResponse claimRecord(com.stride.claim.QueryProto.QueryClaimRecordRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClaimRecordMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.stride.claim.QueryProto.QueryClaimableForActionResponse claimableForAction(com.stride.claim.QueryProto.QueryClaimableForActionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClaimableForActionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.stride.claim.QueryProto.QueryTotalClaimableResponse totalClaimable(com.stride.claim.QueryProto.QueryTotalClaimableRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTotalClaimableMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.stride.claim.QueryProto.QueryUserVestingsResponse userVestings(com.stride.claim.QueryProto.QueryUserVestingsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUserVestingsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.stride.claim.QueryProto.QueryClaimStatusResponse claimStatus(com.stride.claim.QueryProto.QueryClaimStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClaimStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.stride.claim.QueryProto.QueryClaimMetadataResponse claimMetadata(com.stride.claim.QueryProto.QueryClaimMetadataRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClaimMetadataMethod(), getCallOptions(), request);
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
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.claim.QueryProto.QueryDistributorAccountBalanceResponse> distributorAccountBalance(
        com.stride.claim.QueryProto.QueryDistributorAccountBalanceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDistributorAccountBalanceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.claim.QueryProto.QueryParamsResponse> params(
        com.stride.claim.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.claim.QueryProto.QueryClaimRecordResponse> claimRecord(
        com.stride.claim.QueryProto.QueryClaimRecordRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClaimRecordMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.claim.QueryProto.QueryClaimableForActionResponse> claimableForAction(
        com.stride.claim.QueryProto.QueryClaimableForActionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClaimableForActionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.claim.QueryProto.QueryTotalClaimableResponse> totalClaimable(
        com.stride.claim.QueryProto.QueryTotalClaimableRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTotalClaimableMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.claim.QueryProto.QueryUserVestingsResponse> userVestings(
        com.stride.claim.QueryProto.QueryUserVestingsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUserVestingsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.claim.QueryProto.QueryClaimStatusResponse> claimStatus(
        com.stride.claim.QueryProto.QueryClaimStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClaimStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.claim.QueryProto.QueryClaimMetadataResponse> claimMetadata(
        com.stride.claim.QueryProto.QueryClaimMetadataRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClaimMetadataMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DISTRIBUTOR_ACCOUNT_BALANCE = 0;
  private static final int METHODID_PARAMS = 1;
  private static final int METHODID_CLAIM_RECORD = 2;
  private static final int METHODID_CLAIMABLE_FOR_ACTION = 3;
  private static final int METHODID_TOTAL_CLAIMABLE = 4;
  private static final int METHODID_USER_VESTINGS = 5;
  private static final int METHODID_CLAIM_STATUS = 6;
  private static final int METHODID_CLAIM_METADATA = 7;

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
        case METHODID_DISTRIBUTOR_ACCOUNT_BALANCE:
          serviceImpl.distributorAccountBalance((com.stride.claim.QueryProto.QueryDistributorAccountBalanceRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryDistributorAccountBalanceResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((com.stride.claim.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_CLAIM_RECORD:
          serviceImpl.claimRecord((com.stride.claim.QueryProto.QueryClaimRecordRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryClaimRecordResponse>) responseObserver);
          break;
        case METHODID_CLAIMABLE_FOR_ACTION:
          serviceImpl.claimableForAction((com.stride.claim.QueryProto.QueryClaimableForActionRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryClaimableForActionResponse>) responseObserver);
          break;
        case METHODID_TOTAL_CLAIMABLE:
          serviceImpl.totalClaimable((com.stride.claim.QueryProto.QueryTotalClaimableRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryTotalClaimableResponse>) responseObserver);
          break;
        case METHODID_USER_VESTINGS:
          serviceImpl.userVestings((com.stride.claim.QueryProto.QueryUserVestingsRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryUserVestingsResponse>) responseObserver);
          break;
        case METHODID_CLAIM_STATUS:
          serviceImpl.claimStatus((com.stride.claim.QueryProto.QueryClaimStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryClaimStatusResponse>) responseObserver);
          break;
        case METHODID_CLAIM_METADATA:
          serviceImpl.claimMetadata((com.stride.claim.QueryProto.QueryClaimMetadataRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.claim.QueryProto.QueryClaimMetadataResponse>) responseObserver);
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
          getDistributorAccountBalanceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.claim.QueryProto.QueryDistributorAccountBalanceRequest,
              com.stride.claim.QueryProto.QueryDistributorAccountBalanceResponse>(
                service, METHODID_DISTRIBUTOR_ACCOUNT_BALANCE)))
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.claim.QueryProto.QueryParamsRequest,
              com.stride.claim.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getClaimRecordMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.claim.QueryProto.QueryClaimRecordRequest,
              com.stride.claim.QueryProto.QueryClaimRecordResponse>(
                service, METHODID_CLAIM_RECORD)))
        .addMethod(
          getClaimableForActionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.claim.QueryProto.QueryClaimableForActionRequest,
              com.stride.claim.QueryProto.QueryClaimableForActionResponse>(
                service, METHODID_CLAIMABLE_FOR_ACTION)))
        .addMethod(
          getTotalClaimableMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.claim.QueryProto.QueryTotalClaimableRequest,
              com.stride.claim.QueryProto.QueryTotalClaimableResponse>(
                service, METHODID_TOTAL_CLAIMABLE)))
        .addMethod(
          getUserVestingsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.claim.QueryProto.QueryUserVestingsRequest,
              com.stride.claim.QueryProto.QueryUserVestingsResponse>(
                service, METHODID_USER_VESTINGS)))
        .addMethod(
          getClaimStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.claim.QueryProto.QueryClaimStatusRequest,
              com.stride.claim.QueryProto.QueryClaimStatusResponse>(
                service, METHODID_CLAIM_STATUS)))
        .addMethod(
          getClaimMetadataMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.claim.QueryProto.QueryClaimMetadataRequest,
              com.stride.claim.QueryProto.QueryClaimMetadataResponse>(
                service, METHODID_CLAIM_METADATA)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.stride.claim.QueryProto.getDescriptor();
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
              .addMethod(getDistributorAccountBalanceMethod())
              .addMethod(getParamsMethod())
              .addMethod(getClaimRecordMethod())
              .addMethod(getClaimableForActionMethod())
              .addMethod(getTotalClaimableMethod())
              .addMethod(getUserVestingsMethod())
              .addMethod(getClaimStatusMethod())
              .addMethod(getClaimMetadataMethod())
              .build();
        }
      }
    }
    return result;
  }
}
