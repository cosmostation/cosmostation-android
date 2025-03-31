package com.babylon.epoching.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: babylon/epoching/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "babylon.epoching.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryParamsRequest,
      com.babylon.epoching.v1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.babylon.epoching.v1.QueryProto.QueryParamsRequest.class,
      responseType = com.babylon.epoching.v1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryParamsRequest,
      com.babylon.epoching.v1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryParamsRequest, com.babylon.epoching.v1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.babylon.epoching.v1.QueryProto.QueryParamsRequest, com.babylon.epoching.v1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryEpochInfoRequest,
      com.babylon.epoching.v1.QueryProto.QueryEpochInfoResponse> getEpochInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochInfo",
      requestType = com.babylon.epoching.v1.QueryProto.QueryEpochInfoRequest.class,
      responseType = com.babylon.epoching.v1.QueryProto.QueryEpochInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryEpochInfoRequest,
      com.babylon.epoching.v1.QueryProto.QueryEpochInfoResponse> getEpochInfoMethod() {
    io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryEpochInfoRequest, com.babylon.epoching.v1.QueryProto.QueryEpochInfoResponse> getEpochInfoMethod;
    if ((getEpochInfoMethod = QueryGrpc.getEpochInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochInfoMethod = QueryGrpc.getEpochInfoMethod) == null) {
          QueryGrpc.getEpochInfoMethod = getEpochInfoMethod =
              io.grpc.MethodDescriptor.<com.babylon.epoching.v1.QueryProto.QueryEpochInfoRequest, com.babylon.epoching.v1.QueryProto.QueryEpochInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryEpochInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryEpochInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochInfo"))
              .build();
        }
      }
    }
    return getEpochInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryEpochsInfoRequest,
      com.babylon.epoching.v1.QueryProto.QueryEpochsInfoResponse> getEpochsInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochsInfo",
      requestType = com.babylon.epoching.v1.QueryProto.QueryEpochsInfoRequest.class,
      responseType = com.babylon.epoching.v1.QueryProto.QueryEpochsInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryEpochsInfoRequest,
      com.babylon.epoching.v1.QueryProto.QueryEpochsInfoResponse> getEpochsInfoMethod() {
    io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryEpochsInfoRequest, com.babylon.epoching.v1.QueryProto.QueryEpochsInfoResponse> getEpochsInfoMethod;
    if ((getEpochsInfoMethod = QueryGrpc.getEpochsInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochsInfoMethod = QueryGrpc.getEpochsInfoMethod) == null) {
          QueryGrpc.getEpochsInfoMethod = getEpochsInfoMethod =
              io.grpc.MethodDescriptor.<com.babylon.epoching.v1.QueryProto.QueryEpochsInfoRequest, com.babylon.epoching.v1.QueryProto.QueryEpochsInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochsInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryEpochsInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryEpochsInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochsInfo"))
              .build();
        }
      }
    }
    return getEpochsInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryCurrentEpochRequest,
      com.babylon.epoching.v1.QueryProto.QueryCurrentEpochResponse> getCurrentEpochMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CurrentEpoch",
      requestType = com.babylon.epoching.v1.QueryProto.QueryCurrentEpochRequest.class,
      responseType = com.babylon.epoching.v1.QueryProto.QueryCurrentEpochResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryCurrentEpochRequest,
      com.babylon.epoching.v1.QueryProto.QueryCurrentEpochResponse> getCurrentEpochMethod() {
    io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryCurrentEpochRequest, com.babylon.epoching.v1.QueryProto.QueryCurrentEpochResponse> getCurrentEpochMethod;
    if ((getCurrentEpochMethod = QueryGrpc.getCurrentEpochMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCurrentEpochMethod = QueryGrpc.getCurrentEpochMethod) == null) {
          QueryGrpc.getCurrentEpochMethod = getCurrentEpochMethod =
              io.grpc.MethodDescriptor.<com.babylon.epoching.v1.QueryProto.QueryCurrentEpochRequest, com.babylon.epoching.v1.QueryProto.QueryCurrentEpochResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CurrentEpoch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryCurrentEpochRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryCurrentEpochResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CurrentEpoch"))
              .build();
        }
      }
    }
    return getCurrentEpochMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryEpochMsgsRequest,
      com.babylon.epoching.v1.QueryProto.QueryEpochMsgsResponse> getEpochMsgsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochMsgs",
      requestType = com.babylon.epoching.v1.QueryProto.QueryEpochMsgsRequest.class,
      responseType = com.babylon.epoching.v1.QueryProto.QueryEpochMsgsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryEpochMsgsRequest,
      com.babylon.epoching.v1.QueryProto.QueryEpochMsgsResponse> getEpochMsgsMethod() {
    io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryEpochMsgsRequest, com.babylon.epoching.v1.QueryProto.QueryEpochMsgsResponse> getEpochMsgsMethod;
    if ((getEpochMsgsMethod = QueryGrpc.getEpochMsgsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochMsgsMethod = QueryGrpc.getEpochMsgsMethod) == null) {
          QueryGrpc.getEpochMsgsMethod = getEpochMsgsMethod =
              io.grpc.MethodDescriptor.<com.babylon.epoching.v1.QueryProto.QueryEpochMsgsRequest, com.babylon.epoching.v1.QueryProto.QueryEpochMsgsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochMsgs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryEpochMsgsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryEpochMsgsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochMsgs"))
              .build();
        }
      }
    }
    return getEpochMsgsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsRequest,
      com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsResponse> getLatestEpochMsgsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LatestEpochMsgs",
      requestType = com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsRequest.class,
      responseType = com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsRequest,
      com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsResponse> getLatestEpochMsgsMethod() {
    io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsRequest, com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsResponse> getLatestEpochMsgsMethod;
    if ((getLatestEpochMsgsMethod = QueryGrpc.getLatestEpochMsgsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLatestEpochMsgsMethod = QueryGrpc.getLatestEpochMsgsMethod) == null) {
          QueryGrpc.getLatestEpochMsgsMethod = getLatestEpochMsgsMethod =
              io.grpc.MethodDescriptor.<com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsRequest, com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LatestEpochMsgs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LatestEpochMsgs"))
              .build();
        }
      }
    }
    return getLatestEpochMsgsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleRequest,
      com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleResponse> getValidatorLifecycleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidatorLifecycle",
      requestType = com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleRequest.class,
      responseType = com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleRequest,
      com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleResponse> getValidatorLifecycleMethod() {
    io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleRequest, com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleResponse> getValidatorLifecycleMethod;
    if ((getValidatorLifecycleMethod = QueryGrpc.getValidatorLifecycleMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorLifecycleMethod = QueryGrpc.getValidatorLifecycleMethod) == null) {
          QueryGrpc.getValidatorLifecycleMethod = getValidatorLifecycleMethod =
              io.grpc.MethodDescriptor.<com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleRequest, com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidatorLifecycle"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValidatorLifecycle"))
              .build();
        }
      }
    }
    return getValidatorLifecycleMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleRequest,
      com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleResponse> getDelegationLifecycleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegationLifecycle",
      requestType = com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleRequest.class,
      responseType = com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleRequest,
      com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleResponse> getDelegationLifecycleMethod() {
    io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleRequest, com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleResponse> getDelegationLifecycleMethod;
    if ((getDelegationLifecycleMethod = QueryGrpc.getDelegationLifecycleMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegationLifecycleMethod = QueryGrpc.getDelegationLifecycleMethod) == null) {
          QueryGrpc.getDelegationLifecycleMethod = getDelegationLifecycleMethod =
              io.grpc.MethodDescriptor.<com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleRequest, com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegationLifecycle"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegationLifecycle"))
              .build();
        }
      }
    }
    return getDelegationLifecycleMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryEpochValSetRequest,
      com.babylon.epoching.v1.QueryProto.QueryEpochValSetResponse> getEpochValSetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochValSet",
      requestType = com.babylon.epoching.v1.QueryProto.QueryEpochValSetRequest.class,
      responseType = com.babylon.epoching.v1.QueryProto.QueryEpochValSetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryEpochValSetRequest,
      com.babylon.epoching.v1.QueryProto.QueryEpochValSetResponse> getEpochValSetMethod() {
    io.grpc.MethodDescriptor<com.babylon.epoching.v1.QueryProto.QueryEpochValSetRequest, com.babylon.epoching.v1.QueryProto.QueryEpochValSetResponse> getEpochValSetMethod;
    if ((getEpochValSetMethod = QueryGrpc.getEpochValSetMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochValSetMethod = QueryGrpc.getEpochValSetMethod) == null) {
          QueryGrpc.getEpochValSetMethod = getEpochValSetMethod =
              io.grpc.MethodDescriptor.<com.babylon.epoching.v1.QueryProto.QueryEpochValSetRequest, com.babylon.epoching.v1.QueryProto.QueryEpochValSetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochValSet"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryEpochValSetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.epoching.v1.QueryProto.QueryEpochValSetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochValSet"))
              .build();
        }
      }
    }
    return getEpochValSetMethod;
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
     * Params queries the parameters of the module.
     * </pre>
     */
    default void params(com.babylon.epoching.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * EpochInfo queries the information of a given epoch
     * </pre>
     */
    default void epochInfo(com.babylon.epoching.v1.QueryProto.QueryEpochInfoRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryEpochInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEpochInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * EpochsInfo queries the metadata of epochs in a given range, depending on
     * the parameters in the pagination request. Th main use case will be querying
     * the latest epochs in time order.
     * </pre>
     */
    default void epochsInfo(com.babylon.epoching.v1.QueryProto.QueryEpochsInfoRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryEpochsInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEpochsInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * CurrentEpoch queries the current epoch
     * </pre>
     */
    default void currentEpoch(com.babylon.epoching.v1.QueryProto.QueryCurrentEpochRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryCurrentEpochResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCurrentEpochMethod(), responseObserver);
    }

    /**
     * <pre>
     * EpochMsgs queries the messages of a given epoch
     * </pre>
     */
    default void epochMsgs(com.babylon.epoching.v1.QueryProto.QueryEpochMsgsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryEpochMsgsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEpochMsgsMethod(), responseObserver);
    }

    /**
     * <pre>
     * LatestEpochMsgs queries the messages within a given number of most recent
     * epochs
     * </pre>
     */
    default void latestEpochMsgs(com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLatestEpochMsgsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ValidatorLifecycle queries the lifecycle of a given validator
     * </pre>
     */
    default void validatorLifecycle(com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidatorLifecycleMethod(), responseObserver);
    }

    /**
     * <pre>
     * DelegationLifecycle queries the lifecycle of a given delegation
     * </pre>
     */
    default void delegationLifecycle(com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDelegationLifecycleMethod(), responseObserver);
    }

    /**
     * <pre>
     * EpochValSet queries the validator set of a given epoch
     * </pre>
     */
    default void epochValSet(com.babylon.epoching.v1.QueryProto.QueryEpochValSetRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryEpochValSetResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEpochValSetMethod(), responseObserver);
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
     * Params queries the parameters of the module.
     * </pre>
     */
    public void params(com.babylon.epoching.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EpochInfo queries the information of a given epoch
     * </pre>
     */
    public void epochInfo(com.babylon.epoching.v1.QueryProto.QueryEpochInfoRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryEpochInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEpochInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EpochsInfo queries the metadata of epochs in a given range, depending on
     * the parameters in the pagination request. Th main use case will be querying
     * the latest epochs in time order.
     * </pre>
     */
    public void epochsInfo(com.babylon.epoching.v1.QueryProto.QueryEpochsInfoRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryEpochsInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEpochsInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CurrentEpoch queries the current epoch
     * </pre>
     */
    public void currentEpoch(com.babylon.epoching.v1.QueryProto.QueryCurrentEpochRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryCurrentEpochResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCurrentEpochMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EpochMsgs queries the messages of a given epoch
     * </pre>
     */
    public void epochMsgs(com.babylon.epoching.v1.QueryProto.QueryEpochMsgsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryEpochMsgsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEpochMsgsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * LatestEpochMsgs queries the messages within a given number of most recent
     * epochs
     * </pre>
     */
    public void latestEpochMsgs(com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLatestEpochMsgsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ValidatorLifecycle queries the lifecycle of a given validator
     * </pre>
     */
    public void validatorLifecycle(com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidatorLifecycleMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DelegationLifecycle queries the lifecycle of a given delegation
     * </pre>
     */
    public void delegationLifecycle(com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDelegationLifecycleMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EpochValSet queries the validator set of a given epoch
     * </pre>
     */
    public void epochValSet(com.babylon.epoching.v1.QueryProto.QueryEpochValSetRequest request,
        io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryEpochValSetResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEpochValSetMethod(), getCallOptions()), request, responseObserver);
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
     * Params queries the parameters of the module.
     * </pre>
     */
    public com.babylon.epoching.v1.QueryProto.QueryParamsResponse params(com.babylon.epoching.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EpochInfo queries the information of a given epoch
     * </pre>
     */
    public com.babylon.epoching.v1.QueryProto.QueryEpochInfoResponse epochInfo(com.babylon.epoching.v1.QueryProto.QueryEpochInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEpochInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EpochsInfo queries the metadata of epochs in a given range, depending on
     * the parameters in the pagination request. Th main use case will be querying
     * the latest epochs in time order.
     * </pre>
     */
    public com.babylon.epoching.v1.QueryProto.QueryEpochsInfoResponse epochsInfo(com.babylon.epoching.v1.QueryProto.QueryEpochsInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEpochsInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CurrentEpoch queries the current epoch
     * </pre>
     */
    public com.babylon.epoching.v1.QueryProto.QueryCurrentEpochResponse currentEpoch(com.babylon.epoching.v1.QueryProto.QueryCurrentEpochRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCurrentEpochMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EpochMsgs queries the messages of a given epoch
     * </pre>
     */
    public com.babylon.epoching.v1.QueryProto.QueryEpochMsgsResponse epochMsgs(com.babylon.epoching.v1.QueryProto.QueryEpochMsgsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEpochMsgsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * LatestEpochMsgs queries the messages within a given number of most recent
     * epochs
     * </pre>
     */
    public com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsResponse latestEpochMsgs(com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLatestEpochMsgsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ValidatorLifecycle queries the lifecycle of a given validator
     * </pre>
     */
    public com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleResponse validatorLifecycle(com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidatorLifecycleMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DelegationLifecycle queries the lifecycle of a given delegation
     * </pre>
     */
    public com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleResponse delegationLifecycle(com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDelegationLifecycleMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EpochValSet queries the validator set of a given epoch
     * </pre>
     */
    public com.babylon.epoching.v1.QueryProto.QueryEpochValSetResponse epochValSet(com.babylon.epoching.v1.QueryProto.QueryEpochValSetRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEpochValSetMethod(), getCallOptions(), request);
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
     * Params queries the parameters of the module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.epoching.v1.QueryProto.QueryParamsResponse> params(
        com.babylon.epoching.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EpochInfo queries the information of a given epoch
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.epoching.v1.QueryProto.QueryEpochInfoResponse> epochInfo(
        com.babylon.epoching.v1.QueryProto.QueryEpochInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEpochInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EpochsInfo queries the metadata of epochs in a given range, depending on
     * the parameters in the pagination request. Th main use case will be querying
     * the latest epochs in time order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.epoching.v1.QueryProto.QueryEpochsInfoResponse> epochsInfo(
        com.babylon.epoching.v1.QueryProto.QueryEpochsInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEpochsInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CurrentEpoch queries the current epoch
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.epoching.v1.QueryProto.QueryCurrentEpochResponse> currentEpoch(
        com.babylon.epoching.v1.QueryProto.QueryCurrentEpochRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCurrentEpochMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EpochMsgs queries the messages of a given epoch
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.epoching.v1.QueryProto.QueryEpochMsgsResponse> epochMsgs(
        com.babylon.epoching.v1.QueryProto.QueryEpochMsgsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEpochMsgsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * LatestEpochMsgs queries the messages within a given number of most recent
     * epochs
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsResponse> latestEpochMsgs(
        com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLatestEpochMsgsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ValidatorLifecycle queries the lifecycle of a given validator
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleResponse> validatorLifecycle(
        com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidatorLifecycleMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DelegationLifecycle queries the lifecycle of a given delegation
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleResponse> delegationLifecycle(
        com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDelegationLifecycleMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EpochValSet queries the validator set of a given epoch
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.epoching.v1.QueryProto.QueryEpochValSetResponse> epochValSet(
        com.babylon.epoching.v1.QueryProto.QueryEpochValSetRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEpochValSetMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_EPOCH_INFO = 1;
  private static final int METHODID_EPOCHS_INFO = 2;
  private static final int METHODID_CURRENT_EPOCH = 3;
  private static final int METHODID_EPOCH_MSGS = 4;
  private static final int METHODID_LATEST_EPOCH_MSGS = 5;
  private static final int METHODID_VALIDATOR_LIFECYCLE = 6;
  private static final int METHODID_DELEGATION_LIFECYCLE = 7;
  private static final int METHODID_EPOCH_VAL_SET = 8;

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
          serviceImpl.params((com.babylon.epoching.v1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_EPOCH_INFO:
          serviceImpl.epochInfo((com.babylon.epoching.v1.QueryProto.QueryEpochInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryEpochInfoResponse>) responseObserver);
          break;
        case METHODID_EPOCHS_INFO:
          serviceImpl.epochsInfo((com.babylon.epoching.v1.QueryProto.QueryEpochsInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryEpochsInfoResponse>) responseObserver);
          break;
        case METHODID_CURRENT_EPOCH:
          serviceImpl.currentEpoch((com.babylon.epoching.v1.QueryProto.QueryCurrentEpochRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryCurrentEpochResponse>) responseObserver);
          break;
        case METHODID_EPOCH_MSGS:
          serviceImpl.epochMsgs((com.babylon.epoching.v1.QueryProto.QueryEpochMsgsRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryEpochMsgsResponse>) responseObserver);
          break;
        case METHODID_LATEST_EPOCH_MSGS:
          serviceImpl.latestEpochMsgs((com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsResponse>) responseObserver);
          break;
        case METHODID_VALIDATOR_LIFECYCLE:
          serviceImpl.validatorLifecycle((com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleResponse>) responseObserver);
          break;
        case METHODID_DELEGATION_LIFECYCLE:
          serviceImpl.delegationLifecycle((com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleResponse>) responseObserver);
          break;
        case METHODID_EPOCH_VAL_SET:
          serviceImpl.epochValSet((com.babylon.epoching.v1.QueryProto.QueryEpochValSetRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.epoching.v1.QueryProto.QueryEpochValSetResponse>) responseObserver);
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
              com.babylon.epoching.v1.QueryProto.QueryParamsRequest,
              com.babylon.epoching.v1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getEpochInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.epoching.v1.QueryProto.QueryEpochInfoRequest,
              com.babylon.epoching.v1.QueryProto.QueryEpochInfoResponse>(
                service, METHODID_EPOCH_INFO)))
        .addMethod(
          getEpochsInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.epoching.v1.QueryProto.QueryEpochsInfoRequest,
              com.babylon.epoching.v1.QueryProto.QueryEpochsInfoResponse>(
                service, METHODID_EPOCHS_INFO)))
        .addMethod(
          getCurrentEpochMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.epoching.v1.QueryProto.QueryCurrentEpochRequest,
              com.babylon.epoching.v1.QueryProto.QueryCurrentEpochResponse>(
                service, METHODID_CURRENT_EPOCH)))
        .addMethod(
          getEpochMsgsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.epoching.v1.QueryProto.QueryEpochMsgsRequest,
              com.babylon.epoching.v1.QueryProto.QueryEpochMsgsResponse>(
                service, METHODID_EPOCH_MSGS)))
        .addMethod(
          getLatestEpochMsgsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsRequest,
              com.babylon.epoching.v1.QueryProto.QueryLatestEpochMsgsResponse>(
                service, METHODID_LATEST_EPOCH_MSGS)))
        .addMethod(
          getValidatorLifecycleMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleRequest,
              com.babylon.epoching.v1.QueryProto.QueryValidatorLifecycleResponse>(
                service, METHODID_VALIDATOR_LIFECYCLE)))
        .addMethod(
          getDelegationLifecycleMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleRequest,
              com.babylon.epoching.v1.QueryProto.QueryDelegationLifecycleResponse>(
                service, METHODID_DELEGATION_LIFECYCLE)))
        .addMethod(
          getEpochValSetMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.epoching.v1.QueryProto.QueryEpochValSetRequest,
              com.babylon.epoching.v1.QueryProto.QueryEpochValSetResponse>(
                service, METHODID_EPOCH_VAL_SET)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.babylon.epoching.v1.QueryProto.getDescriptor();
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
              .addMethod(getEpochInfoMethod())
              .addMethod(getEpochsInfoMethod())
              .addMethod(getCurrentEpochMethod())
              .addMethod(getEpochMsgsMethod())
              .addMethod(getLatestEpochMsgsMethod())
              .addMethod(getValidatorLifecycleMethod())
              .addMethod(getDelegationLifecycleMethod())
              .addMethod(getEpochValSetMethod())
              .build();
        }
      }
    }
    return result;
  }
}
