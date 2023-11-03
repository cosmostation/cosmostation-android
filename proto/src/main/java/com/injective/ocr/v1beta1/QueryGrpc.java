package com.injective.ocr.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service for OCR module.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: injective/ocr/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "injective.ocr.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryParamsRequest,
      com.injective.ocr.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.injective.ocr.v1beta1.QueryProto.QueryParamsRequest.class,
      responseType = com.injective.ocr.v1beta1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryParamsRequest,
      com.injective.ocr.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryParamsRequest, com.injective.ocr.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.injective.ocr.v1beta1.QueryProto.QueryParamsRequest, com.injective.ocr.v1beta1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.ocr.v1beta1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.ocr.v1beta1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigRequest,
      com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigResponse> getFeedConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FeedConfig",
      requestType = com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigRequest.class,
      responseType = com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigRequest,
      com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigResponse> getFeedConfigMethod() {
    io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigRequest, com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigResponse> getFeedConfigMethod;
    if ((getFeedConfigMethod = QueryGrpc.getFeedConfigMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFeedConfigMethod = QueryGrpc.getFeedConfigMethod) == null) {
          QueryGrpc.getFeedConfigMethod = getFeedConfigMethod =
              io.grpc.MethodDescriptor.<com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigRequest, com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FeedConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FeedConfig"))
              .build();
        }
      }
    }
    return getFeedConfigMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoRequest,
      com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoResponse> getFeedConfigInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FeedConfigInfo",
      requestType = com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoRequest.class,
      responseType = com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoRequest,
      com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoResponse> getFeedConfigInfoMethod() {
    io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoRequest, com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoResponse> getFeedConfigInfoMethod;
    if ((getFeedConfigInfoMethod = QueryGrpc.getFeedConfigInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFeedConfigInfoMethod = QueryGrpc.getFeedConfigInfoMethod) == null) {
          QueryGrpc.getFeedConfigInfoMethod = getFeedConfigInfoMethod =
              io.grpc.MethodDescriptor.<com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoRequest, com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FeedConfigInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FeedConfigInfo"))
              .build();
        }
      }
    }
    return getFeedConfigInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundRequest,
      com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundResponse> getLatestRoundMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LatestRound",
      requestType = com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundRequest.class,
      responseType = com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundRequest,
      com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundResponse> getLatestRoundMethod() {
    io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundRequest, com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundResponse> getLatestRoundMethod;
    if ((getLatestRoundMethod = QueryGrpc.getLatestRoundMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLatestRoundMethod = QueryGrpc.getLatestRoundMethod) == null) {
          QueryGrpc.getLatestRoundMethod = getLatestRoundMethod =
              io.grpc.MethodDescriptor.<com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundRequest, com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LatestRound"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LatestRound"))
              .build();
        }
      }
    }
    return getLatestRoundMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsRequest,
      com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsResponse> getLatestTransmissionDetailsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LatestTransmissionDetails",
      requestType = com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsRequest.class,
      responseType = com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsRequest,
      com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsResponse> getLatestTransmissionDetailsMethod() {
    io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsRequest, com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsResponse> getLatestTransmissionDetailsMethod;
    if ((getLatestTransmissionDetailsMethod = QueryGrpc.getLatestTransmissionDetailsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLatestTransmissionDetailsMethod = QueryGrpc.getLatestTransmissionDetailsMethod) == null) {
          QueryGrpc.getLatestTransmissionDetailsMethod = getLatestTransmissionDetailsMethod =
              io.grpc.MethodDescriptor.<com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsRequest, com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LatestTransmissionDetails"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LatestTransmissionDetails"))
              .build();
        }
      }
    }
    return getLatestTransmissionDetailsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountRequest,
      com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountResponse> getOwedAmountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OwedAmount",
      requestType = com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountRequest.class,
      responseType = com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountRequest,
      com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountResponse> getOwedAmountMethod() {
    io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountRequest, com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountResponse> getOwedAmountMethod;
    if ((getOwedAmountMethod = QueryGrpc.getOwedAmountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOwedAmountMethod = QueryGrpc.getOwedAmountMethod) == null) {
          QueryGrpc.getOwedAmountMethod = getOwedAmountMethod =
              io.grpc.MethodDescriptor.<com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountRequest, com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OwedAmount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OwedAmount"))
              .build();
        }
      }
    }
    return getOwedAmountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryModuleStateRequest,
      com.injective.ocr.v1beta1.QueryProto.QueryModuleStateResponse> getOcrModuleStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OcrModuleState",
      requestType = com.injective.ocr.v1beta1.QueryProto.QueryModuleStateRequest.class,
      responseType = com.injective.ocr.v1beta1.QueryProto.QueryModuleStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryModuleStateRequest,
      com.injective.ocr.v1beta1.QueryProto.QueryModuleStateResponse> getOcrModuleStateMethod() {
    io.grpc.MethodDescriptor<com.injective.ocr.v1beta1.QueryProto.QueryModuleStateRequest, com.injective.ocr.v1beta1.QueryProto.QueryModuleStateResponse> getOcrModuleStateMethod;
    if ((getOcrModuleStateMethod = QueryGrpc.getOcrModuleStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOcrModuleStateMethod = QueryGrpc.getOcrModuleStateMethod) == null) {
          QueryGrpc.getOcrModuleStateMethod = getOcrModuleStateMethod =
              io.grpc.MethodDescriptor.<com.injective.ocr.v1beta1.QueryProto.QueryModuleStateRequest, com.injective.ocr.v1beta1.QueryProto.QueryModuleStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OcrModuleState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.ocr.v1beta1.QueryProto.QueryModuleStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.ocr.v1beta1.QueryProto.QueryModuleStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OcrModuleState"))
              .build();
        }
      }
    }
    return getOcrModuleStateMethod;
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
   * Query defines the gRPC querier service for OCR module.
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void params(com.injective.ocr.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the OCR FeedConfig for a given FeedId
     * </pre>
     */
    default void feedConfig(com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigRequest request,
        io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFeedConfigMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the OCR FeedConfigInfo for a given FeedId
     * </pre>
     */
    default void feedConfigInfo(com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoRequest request,
        io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFeedConfigInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves latest round ID and data, including median answer for that round
     * </pre>
     */
    default void latestRound(com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundRequest request,
        io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLatestRoundMethod(), responseObserver);
    }

    /**
     * <pre>
     * LatestTransmissionDetails returns details about the latest trasmission
     * recorded on chain for the given feed ID.
     * </pre>
     */
    default void latestTransmissionDetails(com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsRequest request,
        io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLatestTransmissionDetailsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves transmitter's owed amount
     * </pre>
     */
    default void owedAmount(com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountRequest request,
        io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOwedAmountMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire OCR module's state
     * </pre>
     */
    default void ocrModuleState(com.injective.ocr.v1beta1.QueryProto.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryModuleStateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOcrModuleStateMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service for OCR module.
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
   * Query defines the gRPC querier service for OCR module.
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
    public void params(com.injective.ocr.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the OCR FeedConfig for a given FeedId
     * </pre>
     */
    public void feedConfig(com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigRequest request,
        io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFeedConfigMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the OCR FeedConfigInfo for a given FeedId
     * </pre>
     */
    public void feedConfigInfo(com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoRequest request,
        io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFeedConfigInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves latest round ID and data, including median answer for that round
     * </pre>
     */
    public void latestRound(com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundRequest request,
        io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLatestRoundMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * LatestTransmissionDetails returns details about the latest trasmission
     * recorded on chain for the given feed ID.
     * </pre>
     */
    public void latestTransmissionDetails(com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsRequest request,
        io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLatestTransmissionDetailsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves transmitter's owed amount
     * </pre>
     */
    public void owedAmount(com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountRequest request,
        io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOwedAmountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire OCR module's state
     * </pre>
     */
    public void ocrModuleState(com.injective.ocr.v1beta1.QueryProto.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryModuleStateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOcrModuleStateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for OCR module.
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
    public com.injective.ocr.v1beta1.QueryProto.QueryParamsResponse params(com.injective.ocr.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the OCR FeedConfig for a given FeedId
     * </pre>
     */
    public com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigResponse feedConfig(com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFeedConfigMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the OCR FeedConfigInfo for a given FeedId
     * </pre>
     */
    public com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoResponse feedConfigInfo(com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFeedConfigInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves latest round ID and data, including median answer for that round
     * </pre>
     */
    public com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundResponse latestRound(com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLatestRoundMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * LatestTransmissionDetails returns details about the latest trasmission
     * recorded on chain for the given feed ID.
     * </pre>
     */
    public com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsResponse latestTransmissionDetails(com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLatestTransmissionDetailsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves transmitter's owed amount
     * </pre>
     */
    public com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountResponse owedAmount(com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOwedAmountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the entire OCR module's state
     * </pre>
     */
    public com.injective.ocr.v1beta1.QueryProto.QueryModuleStateResponse ocrModuleState(com.injective.ocr.v1beta1.QueryProto.QueryModuleStateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOcrModuleStateMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for OCR module.
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
    public com.google.common.util.concurrent.ListenableFuture<com.injective.ocr.v1beta1.QueryProto.QueryParamsResponse> params(
        com.injective.ocr.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the OCR FeedConfig for a given FeedId
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigResponse> feedConfig(
        com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFeedConfigMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the OCR FeedConfigInfo for a given FeedId
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoResponse> feedConfigInfo(
        com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFeedConfigInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves latest round ID and data, including median answer for that round
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundResponse> latestRound(
        com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLatestRoundMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * LatestTransmissionDetails returns details about the latest trasmission
     * recorded on chain for the given feed ID.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsResponse> latestTransmissionDetails(
        com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLatestTransmissionDetailsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves transmitter's owed amount
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountResponse> owedAmount(
        com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOwedAmountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the entire OCR module's state
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.ocr.v1beta1.QueryProto.QueryModuleStateResponse> ocrModuleState(
        com.injective.ocr.v1beta1.QueryProto.QueryModuleStateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOcrModuleStateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_FEED_CONFIG = 1;
  private static final int METHODID_FEED_CONFIG_INFO = 2;
  private static final int METHODID_LATEST_ROUND = 3;
  private static final int METHODID_LATEST_TRANSMISSION_DETAILS = 4;
  private static final int METHODID_OWED_AMOUNT = 5;
  private static final int METHODID_OCR_MODULE_STATE = 6;

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
          serviceImpl.params((com.injective.ocr.v1beta1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_FEED_CONFIG:
          serviceImpl.feedConfig((com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigResponse>) responseObserver);
          break;
        case METHODID_FEED_CONFIG_INFO:
          serviceImpl.feedConfigInfo((com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoResponse>) responseObserver);
          break;
        case METHODID_LATEST_ROUND:
          serviceImpl.latestRound((com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundResponse>) responseObserver);
          break;
        case METHODID_LATEST_TRANSMISSION_DETAILS:
          serviceImpl.latestTransmissionDetails((com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsResponse>) responseObserver);
          break;
        case METHODID_OWED_AMOUNT:
          serviceImpl.owedAmount((com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountResponse>) responseObserver);
          break;
        case METHODID_OCR_MODULE_STATE:
          serviceImpl.ocrModuleState((com.injective.ocr.v1beta1.QueryProto.QueryModuleStateRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.ocr.v1beta1.QueryProto.QueryModuleStateResponse>) responseObserver);
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
              com.injective.ocr.v1beta1.QueryProto.QueryParamsRequest,
              com.injective.ocr.v1beta1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getFeedConfigMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigRequest,
              com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigResponse>(
                service, METHODID_FEED_CONFIG)))
        .addMethod(
          getFeedConfigInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoRequest,
              com.injective.ocr.v1beta1.QueryProto.QueryFeedConfigInfoResponse>(
                service, METHODID_FEED_CONFIG_INFO)))
        .addMethod(
          getLatestRoundMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundRequest,
              com.injective.ocr.v1beta1.QueryProto.QueryLatestRoundResponse>(
                service, METHODID_LATEST_ROUND)))
        .addMethod(
          getLatestTransmissionDetailsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsRequest,
              com.injective.ocr.v1beta1.QueryProto.QueryLatestTransmissionDetailsResponse>(
                service, METHODID_LATEST_TRANSMISSION_DETAILS)))
        .addMethod(
          getOwedAmountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountRequest,
              com.injective.ocr.v1beta1.QueryProto.QueryOwedAmountResponse>(
                service, METHODID_OWED_AMOUNT)))
        .addMethod(
          getOcrModuleStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.ocr.v1beta1.QueryProto.QueryModuleStateRequest,
              com.injective.ocr.v1beta1.QueryProto.QueryModuleStateResponse>(
                service, METHODID_OCR_MODULE_STATE)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.injective.ocr.v1beta1.QueryProto.getDescriptor();
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
              .addMethod(getFeedConfigMethod())
              .addMethod(getFeedConfigInfoMethod())
              .addMethod(getLatestRoundMethod())
              .addMethod(getLatestTransmissionDetailsMethod())
              .addMethod(getOwedAmountMethod())
              .addMethod(getOcrModuleStateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
