package com.babylon.btcstaking.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: babylon/btcstaking/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "babylon.btcstaking.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryParamsRequest,
      com.babylon.btcstaking.v1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.babylon.btcstaking.v1.QueryProto.QueryParamsRequest.class,
      responseType = com.babylon.btcstaking.v1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryParamsRequest,
      com.babylon.btcstaking.v1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryParamsRequest, com.babylon.btcstaking.v1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstaking.v1.QueryProto.QueryParamsRequest, com.babylon.btcstaking.v1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionRequest,
      com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionResponse> getParamsByVersionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ParamsByVersion",
      requestType = com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionRequest.class,
      responseType = com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionRequest,
      com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionResponse> getParamsByVersionMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionRequest, com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionResponse> getParamsByVersionMethod;
    if ((getParamsByVersionMethod = QueryGrpc.getParamsByVersionMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsByVersionMethod = QueryGrpc.getParamsByVersionMethod) == null) {
          QueryGrpc.getParamsByVersionMethod = getParamsByVersionMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionRequest, com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ParamsByVersion"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ParamsByVersion"))
              .build();
        }
      }
    }
    return getParamsByVersionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightRequest,
      com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightResponse> getParamsByBTCHeightMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ParamsByBTCHeight",
      requestType = com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightRequest.class,
      responseType = com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightRequest,
      com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightResponse> getParamsByBTCHeightMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightRequest, com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightResponse> getParamsByBTCHeightMethod;
    if ((getParamsByBTCHeightMethod = QueryGrpc.getParamsByBTCHeightMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsByBTCHeightMethod = QueryGrpc.getParamsByBTCHeightMethod) == null) {
          QueryGrpc.getParamsByBTCHeightMethod = getParamsByBTCHeightMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightRequest, com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ParamsByBTCHeight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ParamsByBTCHeight"))
              .build();
        }
      }
    }
    return getParamsByBTCHeightMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersRequest,
      com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersResponse> getFinalityProvidersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FinalityProviders",
      requestType = com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersRequest.class,
      responseType = com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersRequest,
      com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersResponse> getFinalityProvidersMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersRequest, com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersResponse> getFinalityProvidersMethod;
    if ((getFinalityProvidersMethod = QueryGrpc.getFinalityProvidersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFinalityProvidersMethod = QueryGrpc.getFinalityProvidersMethod) == null) {
          QueryGrpc.getFinalityProvidersMethod = getFinalityProvidersMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersRequest, com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FinalityProviders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FinalityProviders"))
              .build();
        }
      }
    }
    return getFinalityProvidersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderRequest,
      com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderResponse> getFinalityProviderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FinalityProvider",
      requestType = com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderRequest.class,
      responseType = com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderRequest,
      com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderResponse> getFinalityProviderMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderRequest, com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderResponse> getFinalityProviderMethod;
    if ((getFinalityProviderMethod = QueryGrpc.getFinalityProviderMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFinalityProviderMethod = QueryGrpc.getFinalityProviderMethod) == null) {
          QueryGrpc.getFinalityProviderMethod = getFinalityProviderMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderRequest, com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FinalityProvider"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FinalityProvider"))
              .build();
        }
      }
    }
    return getFinalityProviderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsRequest,
      com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsResponse> getBTCDelegationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BTCDelegations",
      requestType = com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsRequest.class,
      responseType = com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsRequest,
      com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsResponse> getBTCDelegationsMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsRequest, com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsResponse> getBTCDelegationsMethod;
    if ((getBTCDelegationsMethod = QueryGrpc.getBTCDelegationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBTCDelegationsMethod = QueryGrpc.getBTCDelegationsMethod) == null) {
          QueryGrpc.getBTCDelegationsMethod = getBTCDelegationsMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsRequest, com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BTCDelegations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BTCDelegations"))
              .build();
        }
      }
    }
    return getBTCDelegationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsRequest,
      com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsResponse> getFinalityProviderDelegationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FinalityProviderDelegations",
      requestType = com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsRequest.class,
      responseType = com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsRequest,
      com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsResponse> getFinalityProviderDelegationsMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsRequest, com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsResponse> getFinalityProviderDelegationsMethod;
    if ((getFinalityProviderDelegationsMethod = QueryGrpc.getFinalityProviderDelegationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFinalityProviderDelegationsMethod = QueryGrpc.getFinalityProviderDelegationsMethod) == null) {
          QueryGrpc.getFinalityProviderDelegationsMethod = getFinalityProviderDelegationsMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsRequest, com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FinalityProviderDelegations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FinalityProviderDelegations"))
              .build();
        }
      }
    }
    return getFinalityProviderDelegationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationRequest,
      com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationResponse> getBTCDelegationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BTCDelegation",
      requestType = com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationRequest.class,
      responseType = com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationRequest,
      com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationResponse> getBTCDelegationMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationRequest, com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationResponse> getBTCDelegationMethod;
    if ((getBTCDelegationMethod = QueryGrpc.getBTCDelegationMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBTCDelegationMethod = QueryGrpc.getBTCDelegationMethod) == null) {
          QueryGrpc.getBTCDelegationMethod = getBTCDelegationMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationRequest, com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BTCDelegation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BTCDelegation"))
              .build();
        }
      }
    }
    return getBTCDelegationMethod;
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
    default void params(com.babylon.btcstaking.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ParamsByVersion queries the parameters of the module for a specific version of past params.
     * </pre>
     */
    default void paramsByVersion(com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsByVersionMethod(), responseObserver);
    }

    /**
     * <pre>
     * ParamsByBTCHeight queries the parameters of the module for a specific BTC height
     * </pre>
     */
    default void paramsByBTCHeight(com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsByBTCHeightMethod(), responseObserver);
    }

    /**
     * <pre>
     * FinalityProviders queries all finality providers
     * </pre>
     */
    default void finalityProviders(com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFinalityProvidersMethod(), responseObserver);
    }

    /**
     * <pre>
     * FinalityProvider info about one finality provider
     * </pre>
     */
    default void finalityProvider(com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFinalityProviderMethod(), responseObserver);
    }

    /**
     * <pre>
     * BTCDelegations queries all BTC delegations under a given status
     * </pre>
     */
    default void bTCDelegations(com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBTCDelegationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * FinalityProviderDelegations queries all BTC delegations of the given finality provider
     * </pre>
     */
    default void finalityProviderDelegations(com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFinalityProviderDelegationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * BTCDelegation retrieves delegation by corresponding staking tx hash
     * </pre>
     */
    default void bTCDelegation(com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBTCDelegationMethod(), responseObserver);
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
    public void params(com.babylon.btcstaking.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ParamsByVersion queries the parameters of the module for a specific version of past params.
     * </pre>
     */
    public void paramsByVersion(com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsByVersionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ParamsByBTCHeight queries the parameters of the module for a specific BTC height
     * </pre>
     */
    public void paramsByBTCHeight(com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsByBTCHeightMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * FinalityProviders queries all finality providers
     * </pre>
     */
    public void finalityProviders(com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFinalityProvidersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * FinalityProvider info about one finality provider
     * </pre>
     */
    public void finalityProvider(com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFinalityProviderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BTCDelegations queries all BTC delegations under a given status
     * </pre>
     */
    public void bTCDelegations(com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBTCDelegationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * FinalityProviderDelegations queries all BTC delegations of the given finality provider
     * </pre>
     */
    public void finalityProviderDelegations(com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFinalityProviderDelegationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BTCDelegation retrieves delegation by corresponding staking tx hash
     * </pre>
     */
    public void bTCDelegation(com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBTCDelegationMethod(), getCallOptions()), request, responseObserver);
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
    public com.babylon.btcstaking.v1.QueryProto.QueryParamsResponse params(com.babylon.btcstaking.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ParamsByVersion queries the parameters of the module for a specific version of past params.
     * </pre>
     */
    public com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionResponse paramsByVersion(com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsByVersionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ParamsByBTCHeight queries the parameters of the module for a specific BTC height
     * </pre>
     */
    public com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightResponse paramsByBTCHeight(com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsByBTCHeightMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * FinalityProviders queries all finality providers
     * </pre>
     */
    public com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersResponse finalityProviders(com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFinalityProvidersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * FinalityProvider info about one finality provider
     * </pre>
     */
    public com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderResponse finalityProvider(com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFinalityProviderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BTCDelegations queries all BTC delegations under a given status
     * </pre>
     */
    public com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsResponse bTCDelegations(com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBTCDelegationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * FinalityProviderDelegations queries all BTC delegations of the given finality provider
     * </pre>
     */
    public com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsResponse finalityProviderDelegations(com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFinalityProviderDelegationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BTCDelegation retrieves delegation by corresponding staking tx hash
     * </pre>
     */
    public com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationResponse bTCDelegation(com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBTCDelegationMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstaking.v1.QueryProto.QueryParamsResponse> params(
        com.babylon.btcstaking.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ParamsByVersion queries the parameters of the module for a specific version of past params.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionResponse> paramsByVersion(
        com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsByVersionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ParamsByBTCHeight queries the parameters of the module for a specific BTC height
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightResponse> paramsByBTCHeight(
        com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsByBTCHeightMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * FinalityProviders queries all finality providers
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersResponse> finalityProviders(
        com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFinalityProvidersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * FinalityProvider info about one finality provider
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderResponse> finalityProvider(
        com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFinalityProviderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BTCDelegations queries all BTC delegations under a given status
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsResponse> bTCDelegations(
        com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBTCDelegationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * FinalityProviderDelegations queries all BTC delegations of the given finality provider
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsResponse> finalityProviderDelegations(
        com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFinalityProviderDelegationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BTCDelegation retrieves delegation by corresponding staking tx hash
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationResponse> bTCDelegation(
        com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBTCDelegationMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_PARAMS_BY_VERSION = 1;
  private static final int METHODID_PARAMS_BY_BTCHEIGHT = 2;
  private static final int METHODID_FINALITY_PROVIDERS = 3;
  private static final int METHODID_FINALITY_PROVIDER = 4;
  private static final int METHODID_BTCDELEGATIONS = 5;
  private static final int METHODID_FINALITY_PROVIDER_DELEGATIONS = 6;
  private static final int METHODID_BTCDELEGATION = 7;

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
          serviceImpl.params((com.babylon.btcstaking.v1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_PARAMS_BY_VERSION:
          serviceImpl.paramsByVersion((com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionResponse>) responseObserver);
          break;
        case METHODID_PARAMS_BY_BTCHEIGHT:
          serviceImpl.paramsByBTCHeight((com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightResponse>) responseObserver);
          break;
        case METHODID_FINALITY_PROVIDERS:
          serviceImpl.finalityProviders((com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersResponse>) responseObserver);
          break;
        case METHODID_FINALITY_PROVIDER:
          serviceImpl.finalityProvider((com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderResponse>) responseObserver);
          break;
        case METHODID_BTCDELEGATIONS:
          serviceImpl.bTCDelegations((com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsResponse>) responseObserver);
          break;
        case METHODID_FINALITY_PROVIDER_DELEGATIONS:
          serviceImpl.finalityProviderDelegations((com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsResponse>) responseObserver);
          break;
        case METHODID_BTCDELEGATION:
          serviceImpl.bTCDelegation((com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationResponse>) responseObserver);
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
              com.babylon.btcstaking.v1.QueryProto.QueryParamsRequest,
              com.babylon.btcstaking.v1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getParamsByVersionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionRequest,
              com.babylon.btcstaking.v1.QueryProto.QueryParamsByVersionResponse>(
                service, METHODID_PARAMS_BY_VERSION)))
        .addMethod(
          getParamsByBTCHeightMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightRequest,
              com.babylon.btcstaking.v1.QueryProto.QueryParamsByBTCHeightResponse>(
                service, METHODID_PARAMS_BY_BTCHEIGHT)))
        .addMethod(
          getFinalityProvidersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersRequest,
              com.babylon.btcstaking.v1.QueryProto.QueryFinalityProvidersResponse>(
                service, METHODID_FINALITY_PROVIDERS)))
        .addMethod(
          getFinalityProviderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderRequest,
              com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderResponse>(
                service, METHODID_FINALITY_PROVIDER)))
        .addMethod(
          getBTCDelegationsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsRequest,
              com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationsResponse>(
                service, METHODID_BTCDELEGATIONS)))
        .addMethod(
          getFinalityProviderDelegationsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsRequest,
              com.babylon.btcstaking.v1.QueryProto.QueryFinalityProviderDelegationsResponse>(
                service, METHODID_FINALITY_PROVIDER_DELEGATIONS)))
        .addMethod(
          getBTCDelegationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationRequest,
              com.babylon.btcstaking.v1.QueryProto.QueryBTCDelegationResponse>(
                service, METHODID_BTCDELEGATION)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.babylon.btcstaking.v1.QueryProto.getDescriptor();
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
              .addMethod(getParamsByVersionMethod())
              .addMethod(getParamsByBTCHeightMethod())
              .addMethod(getFinalityProvidersMethod())
              .addMethod(getFinalityProviderMethod())
              .addMethod(getBTCDelegationsMethod())
              .addMethod(getFinalityProviderDelegationsMethod())
              .addMethod(getBTCDelegationMethod())
              .build();
        }
      }
    }
    return result;
  }
}
