package com.babylon.zoneconcierge.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: babylon/zoneconcierge/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "babylon.zoneconcierge.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryParamsRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.babylon.zoneconcierge.v1.QueryProto.QueryParamsRequest.class,
      responseType = com.babylon.zoneconcierge.v1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryParamsRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryParamsRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.babylon.zoneconcierge.v1.QueryProto.QueryParamsRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderResponse> getHeaderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Header",
      requestType = com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderRequest.class,
      responseType = com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderResponse> getHeaderMethod() {
    io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderResponse> getHeaderMethod;
    if ((getHeaderMethod = QueryGrpc.getHeaderMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHeaderMethod = QueryGrpc.getHeaderMethod) == null) {
          QueryGrpc.getHeaderMethod = getHeaderMethod =
              io.grpc.MethodDescriptor.<com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Header"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Header"))
              .build();
        }
      }
    }
    return getHeaderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryChainListRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryChainListResponse> getChainListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChainList",
      requestType = com.babylon.zoneconcierge.v1.QueryProto.QueryChainListRequest.class,
      responseType = com.babylon.zoneconcierge.v1.QueryProto.QueryChainListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryChainListRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryChainListResponse> getChainListMethod() {
    io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryChainListRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryChainListResponse> getChainListMethod;
    if ((getChainListMethod = QueryGrpc.getChainListMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getChainListMethod = QueryGrpc.getChainListMethod) == null) {
          QueryGrpc.getChainListMethod = getChainListMethod =
              io.grpc.MethodDescriptor.<com.babylon.zoneconcierge.v1.QueryProto.QueryChainListRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryChainListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ChainList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryChainListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryChainListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ChainList"))
              .build();
        }
      }
    }
    return getChainListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoResponse> getChainsInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChainsInfo",
      requestType = com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoRequest.class,
      responseType = com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoResponse> getChainsInfoMethod() {
    io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoResponse> getChainsInfoMethod;
    if ((getChainsInfoMethod = QueryGrpc.getChainsInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getChainsInfoMethod = QueryGrpc.getChainsInfoMethod) == null) {
          QueryGrpc.getChainsInfoMethod = getChainsInfoMethod =
              io.grpc.MethodDescriptor.<com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ChainsInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ChainsInfo"))
              .build();
        }
      }
    }
    return getChainsInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoResponse> getEpochChainsInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochChainsInfo",
      requestType = com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoRequest.class,
      responseType = com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoResponse> getEpochChainsInfoMethod() {
    io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoResponse> getEpochChainsInfoMethod;
    if ((getEpochChainsInfoMethod = QueryGrpc.getEpochChainsInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochChainsInfoMethod = QueryGrpc.getEpochChainsInfoMethod) == null) {
          QueryGrpc.getEpochChainsInfoMethod = getEpochChainsInfoMethod =
              io.grpc.MethodDescriptor.<com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochChainsInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochChainsInfo"))
              .build();
        }
      }
    }
    return getEpochChainsInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersResponse> getListHeadersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListHeaders",
      requestType = com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersRequest.class,
      responseType = com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersResponse> getListHeadersMethod() {
    io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersResponse> getListHeadersMethod;
    if ((getListHeadersMethod = QueryGrpc.getListHeadersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getListHeadersMethod = QueryGrpc.getListHeadersMethod) == null) {
          QueryGrpc.getListHeadersMethod = getListHeadersMethod =
              io.grpc.MethodDescriptor.<com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListHeaders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ListHeaders"))
              .build();
        }
      }
    }
    return getListHeadersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersResponse> getListEpochHeadersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListEpochHeaders",
      requestType = com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersRequest.class,
      responseType = com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersResponse> getListEpochHeadersMethod() {
    io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersResponse> getListEpochHeadersMethod;
    if ((getListEpochHeadersMethod = QueryGrpc.getListEpochHeadersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getListEpochHeadersMethod = QueryGrpc.getListEpochHeadersMethod) == null) {
          QueryGrpc.getListEpochHeadersMethod = getListEpochHeadersMethod =
              io.grpc.MethodDescriptor.<com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListEpochHeaders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ListEpochHeaders"))
              .build();
        }
      }
    }
    return getListEpochHeadersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoResponse> getFinalizedChainsInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FinalizedChainsInfo",
      requestType = com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoRequest.class,
      responseType = com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoResponse> getFinalizedChainsInfoMethod() {
    io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoResponse> getFinalizedChainsInfoMethod;
    if ((getFinalizedChainsInfoMethod = QueryGrpc.getFinalizedChainsInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFinalizedChainsInfoMethod = QueryGrpc.getFinalizedChainsInfoMethod) == null) {
          QueryGrpc.getFinalizedChainsInfoMethod = getFinalizedChainsInfoMethod =
              io.grpc.MethodDescriptor.<com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FinalizedChainsInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FinalizedChainsInfo"))
              .build();
        }
      }
    }
    return getFinalizedChainsInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightResponse> getFinalizedChainInfoUntilHeightMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FinalizedChainInfoUntilHeight",
      requestType = com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightRequest.class,
      responseType = com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightRequest,
      com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightResponse> getFinalizedChainInfoUntilHeightMethod() {
    io.grpc.MethodDescriptor<com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightResponse> getFinalizedChainInfoUntilHeightMethod;
    if ((getFinalizedChainInfoUntilHeightMethod = QueryGrpc.getFinalizedChainInfoUntilHeightMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFinalizedChainInfoUntilHeightMethod = QueryGrpc.getFinalizedChainInfoUntilHeightMethod) == null) {
          QueryGrpc.getFinalizedChainInfoUntilHeightMethod = getFinalizedChainInfoUntilHeightMethod =
              io.grpc.MethodDescriptor.<com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightRequest, com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FinalizedChainInfoUntilHeight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FinalizedChainInfoUntilHeight"))
              .build();
        }
      }
    }
    return getFinalizedChainInfoUntilHeightMethod;
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
    default void params(com.babylon.zoneconcierge.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Header queries the CZ header and fork headers at a given height.
     * </pre>
     */
    default void header(com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHeaderMethod(), responseObserver);
    }

    /**
     * <pre>
     * ChainList queries the list of chains that checkpoint to Babylon
     * </pre>
     */
    default void chainList(com.babylon.zoneconcierge.v1.QueryProto.QueryChainListRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryChainListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getChainListMethod(), responseObserver);
    }

    /**
     * <pre>
     * ChainsInfo queries the latest info for a given list of chains in Babylon's view
     * </pre>
     */
    default void chainsInfo(com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getChainsInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * EpochChainsInfo queries the latest info for a list of chains
     * in a given epoch in Babylon's view
     * </pre>
     */
    default void epochChainsInfo(com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEpochChainsInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * ListHeaders queries the headers of a chain in Babylon's view, with
     * pagination support
     * </pre>
     */
    default void listHeaders(com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListHeadersMethod(), responseObserver);
    }

    /**
     * <pre>
     * ListEpochHeaders queries the headers of a chain timestamped in a given
     * epoch of Babylon, with pagination support
     * </pre>
     */
    default void listEpochHeaders(com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListEpochHeadersMethod(), responseObserver);
    }

    /**
     * <pre>
     * FinalizedChainsInfo queries the BTC-finalised info of chains with given IDs, with proofs
     * </pre>
     */
    default void finalizedChainsInfo(com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFinalizedChainsInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * FinalizedChainInfoUntilHeight queries the BTC-finalised info no later than
     * the provided CZ height, with proofs
     * </pre>
     */
    default void finalizedChainInfoUntilHeight(com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFinalizedChainInfoUntilHeightMethod(), responseObserver);
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
    public void params(com.babylon.zoneconcierge.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Header queries the CZ header and fork headers at a given height.
     * </pre>
     */
    public void header(com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHeaderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ChainList queries the list of chains that checkpoint to Babylon
     * </pre>
     */
    public void chainList(com.babylon.zoneconcierge.v1.QueryProto.QueryChainListRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryChainListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getChainListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ChainsInfo queries the latest info for a given list of chains in Babylon's view
     * </pre>
     */
    public void chainsInfo(com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getChainsInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EpochChainsInfo queries the latest info for a list of chains
     * in a given epoch in Babylon's view
     * </pre>
     */
    public void epochChainsInfo(com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEpochChainsInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ListHeaders queries the headers of a chain in Babylon's view, with
     * pagination support
     * </pre>
     */
    public void listHeaders(com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListHeadersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ListEpochHeaders queries the headers of a chain timestamped in a given
     * epoch of Babylon, with pagination support
     * </pre>
     */
    public void listEpochHeaders(com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListEpochHeadersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * FinalizedChainsInfo queries the BTC-finalised info of chains with given IDs, with proofs
     * </pre>
     */
    public void finalizedChainsInfo(com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFinalizedChainsInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * FinalizedChainInfoUntilHeight queries the BTC-finalised info no later than
     * the provided CZ height, with proofs
     * </pre>
     */
    public void finalizedChainInfoUntilHeight(com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightRequest request,
        io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFinalizedChainInfoUntilHeightMethod(), getCallOptions()), request, responseObserver);
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
    public com.babylon.zoneconcierge.v1.QueryProto.QueryParamsResponse params(com.babylon.zoneconcierge.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Header queries the CZ header and fork headers at a given height.
     * </pre>
     */
    public com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderResponse header(com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHeaderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ChainList queries the list of chains that checkpoint to Babylon
     * </pre>
     */
    public com.babylon.zoneconcierge.v1.QueryProto.QueryChainListResponse chainList(com.babylon.zoneconcierge.v1.QueryProto.QueryChainListRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getChainListMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ChainsInfo queries the latest info for a given list of chains in Babylon's view
     * </pre>
     */
    public com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoResponse chainsInfo(com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getChainsInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EpochChainsInfo queries the latest info for a list of chains
     * in a given epoch in Babylon's view
     * </pre>
     */
    public com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoResponse epochChainsInfo(com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEpochChainsInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ListHeaders queries the headers of a chain in Babylon's view, with
     * pagination support
     * </pre>
     */
    public com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersResponse listHeaders(com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListHeadersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ListEpochHeaders queries the headers of a chain timestamped in a given
     * epoch of Babylon, with pagination support
     * </pre>
     */
    public com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersResponse listEpochHeaders(com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListEpochHeadersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * FinalizedChainsInfo queries the BTC-finalised info of chains with given IDs, with proofs
     * </pre>
     */
    public com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoResponse finalizedChainsInfo(com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFinalizedChainsInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * FinalizedChainInfoUntilHeight queries the BTC-finalised info no later than
     * the provided CZ height, with proofs
     * </pre>
     */
    public com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightResponse finalizedChainInfoUntilHeight(com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFinalizedChainInfoUntilHeightMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.zoneconcierge.v1.QueryProto.QueryParamsResponse> params(
        com.babylon.zoneconcierge.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Header queries the CZ header and fork headers at a given height.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderResponse> header(
        com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHeaderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ChainList queries the list of chains that checkpoint to Babylon
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.zoneconcierge.v1.QueryProto.QueryChainListResponse> chainList(
        com.babylon.zoneconcierge.v1.QueryProto.QueryChainListRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getChainListMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ChainsInfo queries the latest info for a given list of chains in Babylon's view
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoResponse> chainsInfo(
        com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getChainsInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EpochChainsInfo queries the latest info for a list of chains
     * in a given epoch in Babylon's view
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoResponse> epochChainsInfo(
        com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEpochChainsInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ListHeaders queries the headers of a chain in Babylon's view, with
     * pagination support
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersResponse> listHeaders(
        com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListHeadersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ListEpochHeaders queries the headers of a chain timestamped in a given
     * epoch of Babylon, with pagination support
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersResponse> listEpochHeaders(
        com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListEpochHeadersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * FinalizedChainsInfo queries the BTC-finalised info of chains with given IDs, with proofs
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoResponse> finalizedChainsInfo(
        com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFinalizedChainsInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * FinalizedChainInfoUntilHeight queries the BTC-finalised info no later than
     * the provided CZ height, with proofs
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightResponse> finalizedChainInfoUntilHeight(
        com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFinalizedChainInfoUntilHeightMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_HEADER = 1;
  private static final int METHODID_CHAIN_LIST = 2;
  private static final int METHODID_CHAINS_INFO = 3;
  private static final int METHODID_EPOCH_CHAINS_INFO = 4;
  private static final int METHODID_LIST_HEADERS = 5;
  private static final int METHODID_LIST_EPOCH_HEADERS = 6;
  private static final int METHODID_FINALIZED_CHAINS_INFO = 7;
  private static final int METHODID_FINALIZED_CHAIN_INFO_UNTIL_HEIGHT = 8;

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
          serviceImpl.params((com.babylon.zoneconcierge.v1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_HEADER:
          serviceImpl.header((com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderResponse>) responseObserver);
          break;
        case METHODID_CHAIN_LIST:
          serviceImpl.chainList((com.babylon.zoneconcierge.v1.QueryProto.QueryChainListRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryChainListResponse>) responseObserver);
          break;
        case METHODID_CHAINS_INFO:
          serviceImpl.chainsInfo((com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoResponse>) responseObserver);
          break;
        case METHODID_EPOCH_CHAINS_INFO:
          serviceImpl.epochChainsInfo((com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoResponse>) responseObserver);
          break;
        case METHODID_LIST_HEADERS:
          serviceImpl.listHeaders((com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersResponse>) responseObserver);
          break;
        case METHODID_LIST_EPOCH_HEADERS:
          serviceImpl.listEpochHeaders((com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersResponse>) responseObserver);
          break;
        case METHODID_FINALIZED_CHAINS_INFO:
          serviceImpl.finalizedChainsInfo((com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoResponse>) responseObserver);
          break;
        case METHODID_FINALIZED_CHAIN_INFO_UNTIL_HEIGHT:
          serviceImpl.finalizedChainInfoUntilHeight((com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightResponse>) responseObserver);
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
              com.babylon.zoneconcierge.v1.QueryProto.QueryParamsRequest,
              com.babylon.zoneconcierge.v1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getHeaderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderRequest,
              com.babylon.zoneconcierge.v1.QueryProto.QueryHeaderResponse>(
                service, METHODID_HEADER)))
        .addMethod(
          getChainListMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.zoneconcierge.v1.QueryProto.QueryChainListRequest,
              com.babylon.zoneconcierge.v1.QueryProto.QueryChainListResponse>(
                service, METHODID_CHAIN_LIST)))
        .addMethod(
          getChainsInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoRequest,
              com.babylon.zoneconcierge.v1.QueryProto.QueryChainsInfoResponse>(
                service, METHODID_CHAINS_INFO)))
        .addMethod(
          getEpochChainsInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoRequest,
              com.babylon.zoneconcierge.v1.QueryProto.QueryEpochChainsInfoResponse>(
                service, METHODID_EPOCH_CHAINS_INFO)))
        .addMethod(
          getListHeadersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersRequest,
              com.babylon.zoneconcierge.v1.QueryProto.QueryListHeadersResponse>(
                service, METHODID_LIST_HEADERS)))
        .addMethod(
          getListEpochHeadersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersRequest,
              com.babylon.zoneconcierge.v1.QueryProto.QueryListEpochHeadersResponse>(
                service, METHODID_LIST_EPOCH_HEADERS)))
        .addMethod(
          getFinalizedChainsInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoRequest,
              com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainsInfoResponse>(
                service, METHODID_FINALIZED_CHAINS_INFO)))
        .addMethod(
          getFinalizedChainInfoUntilHeightMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightRequest,
              com.babylon.zoneconcierge.v1.QueryProto.QueryFinalizedChainInfoUntilHeightResponse>(
                service, METHODID_FINALIZED_CHAIN_INFO_UNTIL_HEIGHT)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.babylon.zoneconcierge.v1.QueryProto.getDescriptor();
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
              .addMethod(getHeaderMethod())
              .addMethod(getChainListMethod())
              .addMethod(getChainsInfoMethod())
              .addMethod(getEpochChainsInfoMethod())
              .addMethod(getListHeadersMethod())
              .addMethod(getListEpochHeadersMethod())
              .addMethod(getFinalizedChainsInfoMethod())
              .addMethod(getFinalizedChainInfoUntilHeightMethod())
              .build();
        }
      }
    }
    return result;
  }
}
