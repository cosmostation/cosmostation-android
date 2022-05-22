package injective.ocr.v1beta1;

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
 * Query defines the gRPC querier service for OCR module.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: injective/ocr/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "injective.ocr.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryParamsRequest,
      injective.ocr.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = injective.ocr.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = injective.ocr.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryParamsRequest,
      injective.ocr.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryParamsRequest, injective.ocr.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<injective.ocr.v1beta1.QueryOuterClass.QueryParamsRequest, injective.ocr.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.ocr.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.ocr.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigRequest,
      injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigResponse> getFeedConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FeedConfig",
      requestType = injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigRequest.class,
      responseType = injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigRequest,
      injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigResponse> getFeedConfigMethod() {
    io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigRequest, injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigResponse> getFeedConfigMethod;
    if ((getFeedConfigMethod = QueryGrpc.getFeedConfigMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFeedConfigMethod = QueryGrpc.getFeedConfigMethod) == null) {
          QueryGrpc.getFeedConfigMethod = getFeedConfigMethod =
              io.grpc.MethodDescriptor.<injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigRequest, injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FeedConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FeedConfig"))
              .build();
        }
      }
    }
    return getFeedConfigMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoRequest,
      injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoResponse> getFeedConfigInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FeedConfigInfo",
      requestType = injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoRequest.class,
      responseType = injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoRequest,
      injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoResponse> getFeedConfigInfoMethod() {
    io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoRequest, injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoResponse> getFeedConfigInfoMethod;
    if ((getFeedConfigInfoMethod = QueryGrpc.getFeedConfigInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFeedConfigInfoMethod = QueryGrpc.getFeedConfigInfoMethod) == null) {
          QueryGrpc.getFeedConfigInfoMethod = getFeedConfigInfoMethod =
              io.grpc.MethodDescriptor.<injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoRequest, injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FeedConfigInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FeedConfigInfo"))
              .build();
        }
      }
    }
    return getFeedConfigInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundRequest,
      injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundResponse> getLatestRoundMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LatestRound",
      requestType = injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundRequest.class,
      responseType = injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundRequest,
      injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundResponse> getLatestRoundMethod() {
    io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundRequest, injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundResponse> getLatestRoundMethod;
    if ((getLatestRoundMethod = QueryGrpc.getLatestRoundMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLatestRoundMethod = QueryGrpc.getLatestRoundMethod) == null) {
          QueryGrpc.getLatestRoundMethod = getLatestRoundMethod =
              io.grpc.MethodDescriptor.<injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundRequest, injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LatestRound"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LatestRound"))
              .build();
        }
      }
    }
    return getLatestRoundMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsRequest,
      injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsResponse> getLatestTransmissionDetailsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LatestTransmissionDetails",
      requestType = injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsRequest.class,
      responseType = injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsRequest,
      injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsResponse> getLatestTransmissionDetailsMethod() {
    io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsRequest, injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsResponse> getLatestTransmissionDetailsMethod;
    if ((getLatestTransmissionDetailsMethod = QueryGrpc.getLatestTransmissionDetailsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLatestTransmissionDetailsMethod = QueryGrpc.getLatestTransmissionDetailsMethod) == null) {
          QueryGrpc.getLatestTransmissionDetailsMethod = getLatestTransmissionDetailsMethod =
              io.grpc.MethodDescriptor.<injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsRequest, injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LatestTransmissionDetails"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LatestTransmissionDetails"))
              .build();
        }
      }
    }
    return getLatestTransmissionDetailsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountRequest,
      injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountResponse> getOwedAmountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OwedAmount",
      requestType = injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountRequest.class,
      responseType = injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountRequest,
      injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountResponse> getOwedAmountMethod() {
    io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountRequest, injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountResponse> getOwedAmountMethod;
    if ((getOwedAmountMethod = QueryGrpc.getOwedAmountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOwedAmountMethod = QueryGrpc.getOwedAmountMethod) == null) {
          QueryGrpc.getOwedAmountMethod = getOwedAmountMethod =
              io.grpc.MethodDescriptor.<injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountRequest, injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OwedAmount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OwedAmount"))
              .build();
        }
      }
    }
    return getOwedAmountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateRequest,
      injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateResponse> getOcrModuleStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OcrModuleState",
      requestType = injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateRequest.class,
      responseType = injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateRequest,
      injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateResponse> getOcrModuleStateMethod() {
    io.grpc.MethodDescriptor<injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateRequest, injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateResponse> getOcrModuleStateMethod;
    if ((getOcrModuleStateMethod = QueryGrpc.getOcrModuleStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOcrModuleStateMethod = QueryGrpc.getOcrModuleStateMethod) == null) {
          QueryGrpc.getOcrModuleStateMethod = getOcrModuleStateMethod =
              io.grpc.MethodDescriptor.<injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateRequest, injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OcrModuleState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateResponse.getDefaultInstance()))
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
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     */
    public void params(injective.ocr.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the OCR FeedConfig for a given FeedId
     * </pre>
     */
    public void feedConfig(injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigRequest request,
        io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFeedConfigMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the OCR FeedConfigInfo for a given FeedId
     * </pre>
     */
    public void feedConfigInfo(injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoRequest request,
        io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFeedConfigInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves latest round ID and data, including median answer for that round
     * </pre>
     */
    public void latestRound(injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundRequest request,
        io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLatestRoundMethod(), responseObserver);
    }

    /**
     * <pre>
     * LatestTransmissionDetails returns details about the latest trasmission recorded on chain for the given feed ID.
     * </pre>
     */
    public void latestTransmissionDetails(injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsRequest request,
        io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLatestTransmissionDetailsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves transmitter's owed amount
     * </pre>
     */
    public void owedAmount(injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountRequest request,
        io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOwedAmountMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire OCR module's state
     * </pre>
     */
    public void ocrModuleState(injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOcrModuleStateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.ocr.v1beta1.QueryOuterClass.QueryParamsRequest,
                injective.ocr.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getFeedConfigMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigRequest,
                injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigResponse>(
                  this, METHODID_FEED_CONFIG)))
          .addMethod(
            getFeedConfigInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoRequest,
                injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoResponse>(
                  this, METHODID_FEED_CONFIG_INFO)))
          .addMethod(
            getLatestRoundMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundRequest,
                injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundResponse>(
                  this, METHODID_LATEST_ROUND)))
          .addMethod(
            getLatestTransmissionDetailsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsRequest,
                injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsResponse>(
                  this, METHODID_LATEST_TRANSMISSION_DETAILS)))
          .addMethod(
            getOwedAmountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountRequest,
                injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountResponse>(
                  this, METHODID_OWED_AMOUNT)))
          .addMethod(
            getOcrModuleStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateRequest,
                injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateResponse>(
                  this, METHODID_OCR_MODULE_STATE)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for OCR module.
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
     */
    public void params(injective.ocr.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the OCR FeedConfig for a given FeedId
     * </pre>
     */
    public void feedConfig(injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigRequest request,
        io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFeedConfigMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the OCR FeedConfigInfo for a given FeedId
     * </pre>
     */
    public void feedConfigInfo(injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoRequest request,
        io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFeedConfigInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves latest round ID and data, including median answer for that round
     * </pre>
     */
    public void latestRound(injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundRequest request,
        io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLatestRoundMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * LatestTransmissionDetails returns details about the latest trasmission recorded on chain for the given feed ID.
     * </pre>
     */
    public void latestTransmissionDetails(injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsRequest request,
        io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLatestTransmissionDetailsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves transmitter's owed amount
     * </pre>
     */
    public void owedAmount(injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountRequest request,
        io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOwedAmountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire OCR module's state
     * </pre>
     */
    public void ocrModuleState(injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOcrModuleStateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for OCR module.
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
     */
    public injective.ocr.v1beta1.QueryOuterClass.QueryParamsResponse params(injective.ocr.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the OCR FeedConfig for a given FeedId
     * </pre>
     */
    public injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigResponse feedConfig(injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigRequest request) {
      return blockingUnaryCall(
          getChannel(), getFeedConfigMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the OCR FeedConfigInfo for a given FeedId
     * </pre>
     */
    public injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoResponse feedConfigInfo(injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoRequest request) {
      return blockingUnaryCall(
          getChannel(), getFeedConfigInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves latest round ID and data, including median answer for that round
     * </pre>
     */
    public injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundResponse latestRound(injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundRequest request) {
      return blockingUnaryCall(
          getChannel(), getLatestRoundMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * LatestTransmissionDetails returns details about the latest trasmission recorded on chain for the given feed ID.
     * </pre>
     */
    public injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsResponse latestTransmissionDetails(injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsRequest request) {
      return blockingUnaryCall(
          getChannel(), getLatestTransmissionDetailsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves transmitter's owed amount
     * </pre>
     */
    public injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountResponse owedAmount(injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountRequest request) {
      return blockingUnaryCall(
          getChannel(), getOwedAmountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the entire OCR module's state
     * </pre>
     */
    public injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateResponse ocrModuleState(injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateRequest request) {
      return blockingUnaryCall(
          getChannel(), getOcrModuleStateMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for OCR module.
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
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.ocr.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        injective.ocr.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the OCR FeedConfig for a given FeedId
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigResponse> feedConfig(
        injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFeedConfigMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the OCR FeedConfigInfo for a given FeedId
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoResponse> feedConfigInfo(
        injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFeedConfigInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves latest round ID and data, including median answer for that round
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundResponse> latestRound(
        injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLatestRoundMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * LatestTransmissionDetails returns details about the latest trasmission recorded on chain for the given feed ID.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsResponse> latestTransmissionDetails(
        injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLatestTransmissionDetailsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves transmitter's owed amount
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountResponse> owedAmount(
        injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOwedAmountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the entire OCR module's state
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateResponse> ocrModuleState(
        injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateRequest request) {
      return futureUnaryCall(
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
          serviceImpl.params((injective.ocr.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_FEED_CONFIG:
          serviceImpl.feedConfig((injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigRequest) request,
              (io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigResponse>) responseObserver);
          break;
        case METHODID_FEED_CONFIG_INFO:
          serviceImpl.feedConfigInfo((injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoRequest) request,
              (io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryFeedConfigInfoResponse>) responseObserver);
          break;
        case METHODID_LATEST_ROUND:
          serviceImpl.latestRound((injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundRequest) request,
              (io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryLatestRoundResponse>) responseObserver);
          break;
        case METHODID_LATEST_TRANSMISSION_DETAILS:
          serviceImpl.latestTransmissionDetails((injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsRequest) request,
              (io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryLatestTransmissionDetailsResponse>) responseObserver);
          break;
        case METHODID_OWED_AMOUNT:
          serviceImpl.owedAmount((injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountRequest) request,
              (io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryOwedAmountResponse>) responseObserver);
          break;
        case METHODID_OCR_MODULE_STATE:
          serviceImpl.ocrModuleState((injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateRequest) request,
              (io.grpc.stub.StreamObserver<injective.ocr.v1beta1.QueryOuterClass.QueryModuleStateResponse>) responseObserver);
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
      return injective.ocr.v1beta1.QueryOuterClass.getDescriptor();
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
