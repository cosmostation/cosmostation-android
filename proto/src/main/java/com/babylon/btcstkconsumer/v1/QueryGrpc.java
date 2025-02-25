package com.babylon.btcstkconsumer.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: babylon/btcstkconsumer/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "babylon.btcstkconsumer.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsRequest,
      com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsRequest.class,
      responseType = com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsRequest,
      com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsRequest, com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsRequest, com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListRequest,
      com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListResponse> getConsumerRegistryListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConsumerRegistryList",
      requestType = com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListRequest.class,
      responseType = com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListRequest,
      com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListResponse> getConsumerRegistryListMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListRequest, com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListResponse> getConsumerRegistryListMethod;
    if ((getConsumerRegistryListMethod = QueryGrpc.getConsumerRegistryListMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getConsumerRegistryListMethod = QueryGrpc.getConsumerRegistryListMethod) == null) {
          QueryGrpc.getConsumerRegistryListMethod = getConsumerRegistryListMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListRequest, com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConsumerRegistryList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ConsumerRegistryList"))
              .build();
        }
      }
    }
    return getConsumerRegistryListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryRequest,
      com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryResponse> getConsumersRegistryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConsumersRegistry",
      requestType = com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryRequest.class,
      responseType = com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryRequest,
      com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryResponse> getConsumersRegistryMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryRequest, com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryResponse> getConsumersRegistryMethod;
    if ((getConsumersRegistryMethod = QueryGrpc.getConsumersRegistryMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getConsumersRegistryMethod = QueryGrpc.getConsumersRegistryMethod) == null) {
          QueryGrpc.getConsumersRegistryMethod = getConsumersRegistryMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryRequest, com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConsumersRegistry"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ConsumersRegistry"))
              .build();
        }
      }
    }
    return getConsumersRegistryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerRequest,
      com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerResponse> getFinalityProviderConsumerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FinalityProviderConsumer",
      requestType = com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerRequest.class,
      responseType = com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerRequest,
      com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerResponse> getFinalityProviderConsumerMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerRequest, com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerResponse> getFinalityProviderConsumerMethod;
    if ((getFinalityProviderConsumerMethod = QueryGrpc.getFinalityProviderConsumerMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFinalityProviderConsumerMethod = QueryGrpc.getFinalityProviderConsumerMethod) == null) {
          QueryGrpc.getFinalityProviderConsumerMethod = getFinalityProviderConsumerMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerRequest, com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FinalityProviderConsumer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FinalityProviderConsumer"))
              .build();
        }
      }
    }
    return getFinalityProviderConsumerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersRequest,
      com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersResponse> getFinalityProvidersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FinalityProviders",
      requestType = com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersRequest.class,
      responseType = com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersRequest,
      com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersResponse> getFinalityProvidersMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersRequest, com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersResponse> getFinalityProvidersMethod;
    if ((getFinalityProvidersMethod = QueryGrpc.getFinalityProvidersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFinalityProvidersMethod = QueryGrpc.getFinalityProvidersMethod) == null) {
          QueryGrpc.getFinalityProvidersMethod = getFinalityProvidersMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersRequest, com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FinalityProviders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FinalityProviders"))
              .build();
        }
      }
    }
    return getFinalityProvidersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderRequest,
      com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderResponse> getFinalityProviderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FinalityProvider",
      requestType = com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderRequest.class,
      responseType = com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderRequest,
      com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderResponse> getFinalityProviderMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderRequest, com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderResponse> getFinalityProviderMethod;
    if ((getFinalityProviderMethod = QueryGrpc.getFinalityProviderMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFinalityProviderMethod = QueryGrpc.getFinalityProviderMethod) == null) {
          QueryGrpc.getFinalityProviderMethod = getFinalityProviderMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderRequest, com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FinalityProvider"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FinalityProvider"))
              .build();
        }
      }
    }
    return getFinalityProviderMethod;
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
    default void params(com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConsumerRegistryList queries the list of consumers that are registered to Babylon
     * </pre>
     */
    default void consumerRegistryList(com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConsumerRegistryListMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConsumersRegistry queries the latest info for a given list of consumers in Babylon's view
     * </pre>
     */
    default void consumersRegistry(com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConsumersRegistryMethod(), responseObserver);
    }

    /**
     * <pre>
     * FinalityProviderConsumer info about one finality provider's consumer id
     * </pre>
     */
    default void finalityProviderConsumer(com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFinalityProviderConsumerMethod(), responseObserver);
    }

    /**
     * <pre>
     * FinalityProviders queries all finality providers for a given consumer
     * </pre>
     */
    default void finalityProviders(com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFinalityProvidersMethod(), responseObserver);
    }

    /**
     * <pre>
     * FinalityProvider info about one finality provider
     * </pre>
     */
    default void finalityProvider(com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFinalityProviderMethod(), responseObserver);
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
    public void params(com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConsumerRegistryList queries the list of consumers that are registered to Babylon
     * </pre>
     */
    public void consumerRegistryList(com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConsumerRegistryListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConsumersRegistry queries the latest info for a given list of consumers in Babylon's view
     * </pre>
     */
    public void consumersRegistry(com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConsumersRegistryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * FinalityProviderConsumer info about one finality provider's consumer id
     * </pre>
     */
    public void finalityProviderConsumer(com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFinalityProviderConsumerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * FinalityProviders queries all finality providers for a given consumer
     * </pre>
     */
    public void finalityProviders(com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFinalityProvidersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * FinalityProvider info about one finality provider
     * </pre>
     */
    public void finalityProvider(com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFinalityProviderMethod(), getCallOptions()), request, responseObserver);
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
    public com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsResponse params(com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConsumerRegistryList queries the list of consumers that are registered to Babylon
     * </pre>
     */
    public com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListResponse consumerRegistryList(com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConsumerRegistryListMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConsumersRegistry queries the latest info for a given list of consumers in Babylon's view
     * </pre>
     */
    public com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryResponse consumersRegistry(com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConsumersRegistryMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * FinalityProviderConsumer info about one finality provider's consumer id
     * </pre>
     */
    public com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerResponse finalityProviderConsumer(com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFinalityProviderConsumerMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * FinalityProviders queries all finality providers for a given consumer
     * </pre>
     */
    public com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersResponse finalityProviders(com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFinalityProvidersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * FinalityProvider info about one finality provider
     * </pre>
     */
    public com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderResponse finalityProvider(com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFinalityProviderMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsResponse> params(
        com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConsumerRegistryList queries the list of consumers that are registered to Babylon
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListResponse> consumerRegistryList(
        com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConsumerRegistryListMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConsumersRegistry queries the latest info for a given list of consumers in Babylon's view
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryResponse> consumersRegistry(
        com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConsumersRegistryMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * FinalityProviderConsumer info about one finality provider's consumer id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerResponse> finalityProviderConsumer(
        com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFinalityProviderConsumerMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * FinalityProviders queries all finality providers for a given consumer
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersResponse> finalityProviders(
        com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFinalityProvidersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * FinalityProvider info about one finality provider
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderResponse> finalityProvider(
        com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFinalityProviderMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_CONSUMER_REGISTRY_LIST = 1;
  private static final int METHODID_CONSUMERS_REGISTRY = 2;
  private static final int METHODID_FINALITY_PROVIDER_CONSUMER = 3;
  private static final int METHODID_FINALITY_PROVIDERS = 4;
  private static final int METHODID_FINALITY_PROVIDER = 5;

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
          serviceImpl.params((com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_CONSUMER_REGISTRY_LIST:
          serviceImpl.consumerRegistryList((com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListResponse>) responseObserver);
          break;
        case METHODID_CONSUMERS_REGISTRY:
          serviceImpl.consumersRegistry((com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryResponse>) responseObserver);
          break;
        case METHODID_FINALITY_PROVIDER_CONSUMER:
          serviceImpl.finalityProviderConsumer((com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerResponse>) responseObserver);
          break;
        case METHODID_FINALITY_PROVIDERS:
          serviceImpl.finalityProviders((com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersResponse>) responseObserver);
          break;
        case METHODID_FINALITY_PROVIDER:
          serviceImpl.finalityProvider((com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderResponse>) responseObserver);
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
              com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsRequest,
              com.babylon.btcstkconsumer.v1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getConsumerRegistryListMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListRequest,
              com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumerRegistryListResponse>(
                service, METHODID_CONSUMER_REGISTRY_LIST)))
        .addMethod(
          getConsumersRegistryMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryRequest,
              com.babylon.btcstkconsumer.v1.QueryProto.QueryConsumersRegistryResponse>(
                service, METHODID_CONSUMERS_REGISTRY)))
        .addMethod(
          getFinalityProviderConsumerMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerRequest,
              com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderConsumerResponse>(
                service, METHODID_FINALITY_PROVIDER_CONSUMER)))
        .addMethod(
          getFinalityProvidersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersRequest,
              com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProvidersResponse>(
                service, METHODID_FINALITY_PROVIDERS)))
        .addMethod(
          getFinalityProviderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderRequest,
              com.babylon.btcstkconsumer.v1.QueryProto.QueryFinalityProviderResponse>(
                service, METHODID_FINALITY_PROVIDER)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.babylon.btcstkconsumer.v1.QueryProto.getDescriptor();
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
              .addMethod(getConsumerRegistryListMethod())
              .addMethod(getConsumersRegistryMethod())
              .addMethod(getFinalityProviderConsumerMethod())
              .addMethod(getFinalityProvidersMethod())
              .addMethod(getFinalityProviderMethod())
              .build();
        }
      }
    }
    return result;
  }
}
