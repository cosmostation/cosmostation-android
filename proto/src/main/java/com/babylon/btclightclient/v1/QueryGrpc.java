package com.babylon.btclightclient.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: babylon/btclightclient/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "babylon.btclightclient.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryParamsRequest,
      com.babylon.btclightclient.v1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.babylon.btclightclient.v1.QueryProto.QueryParamsRequest.class,
      responseType = com.babylon.btclightclient.v1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryParamsRequest,
      com.babylon.btclightclient.v1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryParamsRequest, com.babylon.btclightclient.v1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.babylon.btclightclient.v1.QueryProto.QueryParamsRequest, com.babylon.btclightclient.v1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryHashesRequest,
      com.babylon.btclightclient.v1.QueryProto.QueryHashesResponse> getHashesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Hashes",
      requestType = com.babylon.btclightclient.v1.QueryProto.QueryHashesRequest.class,
      responseType = com.babylon.btclightclient.v1.QueryProto.QueryHashesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryHashesRequest,
      com.babylon.btclightclient.v1.QueryProto.QueryHashesResponse> getHashesMethod() {
    io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryHashesRequest, com.babylon.btclightclient.v1.QueryProto.QueryHashesResponse> getHashesMethod;
    if ((getHashesMethod = QueryGrpc.getHashesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHashesMethod = QueryGrpc.getHashesMethod) == null) {
          QueryGrpc.getHashesMethod = getHashesMethod =
              io.grpc.MethodDescriptor.<com.babylon.btclightclient.v1.QueryProto.QueryHashesRequest, com.babylon.btclightclient.v1.QueryProto.QueryHashesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Hashes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.QueryProto.QueryHashesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.QueryProto.QueryHashesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Hashes"))
              .build();
        }
      }
    }
    return getHashesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryContainsRequest,
      com.babylon.btclightclient.v1.QueryProto.QueryContainsResponse> getContainsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Contains",
      requestType = com.babylon.btclightclient.v1.QueryProto.QueryContainsRequest.class,
      responseType = com.babylon.btclightclient.v1.QueryProto.QueryContainsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryContainsRequest,
      com.babylon.btclightclient.v1.QueryProto.QueryContainsResponse> getContainsMethod() {
    io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryContainsRequest, com.babylon.btclightclient.v1.QueryProto.QueryContainsResponse> getContainsMethod;
    if ((getContainsMethod = QueryGrpc.getContainsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getContainsMethod = QueryGrpc.getContainsMethod) == null) {
          QueryGrpc.getContainsMethod = getContainsMethod =
              io.grpc.MethodDescriptor.<com.babylon.btclightclient.v1.QueryProto.QueryContainsRequest, com.babylon.btclightclient.v1.QueryProto.QueryContainsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Contains"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.QueryProto.QueryContainsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.QueryProto.QueryContainsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Contains"))
              .build();
        }
      }
    }
    return getContainsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesRequest,
      com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesResponse> getContainsBytesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ContainsBytes",
      requestType = com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesRequest.class,
      responseType = com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesRequest,
      com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesResponse> getContainsBytesMethod() {
    io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesRequest, com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesResponse> getContainsBytesMethod;
    if ((getContainsBytesMethod = QueryGrpc.getContainsBytesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getContainsBytesMethod = QueryGrpc.getContainsBytesMethod) == null) {
          QueryGrpc.getContainsBytesMethod = getContainsBytesMethod =
              io.grpc.MethodDescriptor.<com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesRequest, com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ContainsBytes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ContainsBytes"))
              .build();
        }
      }
    }
    return getContainsBytesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryMainChainRequest,
      com.babylon.btclightclient.v1.QueryProto.QueryMainChainResponse> getMainChainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MainChain",
      requestType = com.babylon.btclightclient.v1.QueryProto.QueryMainChainRequest.class,
      responseType = com.babylon.btclightclient.v1.QueryProto.QueryMainChainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryMainChainRequest,
      com.babylon.btclightclient.v1.QueryProto.QueryMainChainResponse> getMainChainMethod() {
    io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryMainChainRequest, com.babylon.btclightclient.v1.QueryProto.QueryMainChainResponse> getMainChainMethod;
    if ((getMainChainMethod = QueryGrpc.getMainChainMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getMainChainMethod = QueryGrpc.getMainChainMethod) == null) {
          QueryGrpc.getMainChainMethod = getMainChainMethod =
              io.grpc.MethodDescriptor.<com.babylon.btclightclient.v1.QueryProto.QueryMainChainRequest, com.babylon.btclightclient.v1.QueryProto.QueryMainChainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MainChain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.QueryProto.QueryMainChainRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.QueryProto.QueryMainChainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("MainChain"))
              .build();
        }
      }
    }
    return getMainChainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryTipRequest,
      com.babylon.btclightclient.v1.QueryProto.QueryTipResponse> getTipMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Tip",
      requestType = com.babylon.btclightclient.v1.QueryProto.QueryTipRequest.class,
      responseType = com.babylon.btclightclient.v1.QueryProto.QueryTipResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryTipRequest,
      com.babylon.btclightclient.v1.QueryProto.QueryTipResponse> getTipMethod() {
    io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryTipRequest, com.babylon.btclightclient.v1.QueryProto.QueryTipResponse> getTipMethod;
    if ((getTipMethod = QueryGrpc.getTipMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTipMethod = QueryGrpc.getTipMethod) == null) {
          QueryGrpc.getTipMethod = getTipMethod =
              io.grpc.MethodDescriptor.<com.babylon.btclightclient.v1.QueryProto.QueryTipRequest, com.babylon.btclightclient.v1.QueryProto.QueryTipResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Tip"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.QueryProto.QueryTipRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.QueryProto.QueryTipResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Tip"))
              .build();
        }
      }
    }
    return getTipMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderRequest,
      com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderResponse> getBaseHeaderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BaseHeader",
      requestType = com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderRequest.class,
      responseType = com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderRequest,
      com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderResponse> getBaseHeaderMethod() {
    io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderRequest, com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderResponse> getBaseHeaderMethod;
    if ((getBaseHeaderMethod = QueryGrpc.getBaseHeaderMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBaseHeaderMethod = QueryGrpc.getBaseHeaderMethod) == null) {
          QueryGrpc.getBaseHeaderMethod = getBaseHeaderMethod =
              io.grpc.MethodDescriptor.<com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderRequest, com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BaseHeader"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BaseHeader"))
              .build();
        }
      }
    }
    return getBaseHeaderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthRequest,
      com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthResponse> getHeaderDepthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HeaderDepth",
      requestType = com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthRequest.class,
      responseType = com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthRequest,
      com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthResponse> getHeaderDepthMethod() {
    io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthRequest, com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthResponse> getHeaderDepthMethod;
    if ((getHeaderDepthMethod = QueryGrpc.getHeaderDepthMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHeaderDepthMethod = QueryGrpc.getHeaderDepthMethod) == null) {
          QueryGrpc.getHeaderDepthMethod = getHeaderDepthMethod =
              io.grpc.MethodDescriptor.<com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthRequest, com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HeaderDepth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("HeaderDepth"))
              .build();
        }
      }
    }
    return getHeaderDepthMethod;
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
    default void params(com.babylon.btclightclient.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Hashes retrieves the hashes maintained by the module.
     * </pre>
     */
    default void hashes(com.babylon.btclightclient.v1.QueryProto.QueryHashesRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryHashesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHashesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Contains checks whether a hash is maintained by the module.
     * </pre>
     */
    default void contains(com.babylon.btclightclient.v1.QueryProto.QueryContainsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryContainsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getContainsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ContainsBytes is a temporary method that
     * checks whether a hash is maintained by the module.
     * See discussion at https://github.com/babylonlabs-io/babylon/pull/132
     * for more details.
     * </pre>
     */
    default void containsBytes(com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getContainsBytesMethod(), responseObserver);
    }

    /**
     * <pre>
     * MainChain returns the canonical chain
     * </pre>
     */
    default void mainChain(com.babylon.btclightclient.v1.QueryProto.QueryMainChainRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryMainChainResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMainChainMethod(), responseObserver);
    }

    /**
     * <pre>
     * Tip return best header on canonical chain
     * </pre>
     */
    default void tip(com.babylon.btclightclient.v1.QueryProto.QueryTipRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryTipResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTipMethod(), responseObserver);
    }

    /**
     * <pre>
     * BaseHeader returns the base BTC header of the chain. This header is defined
     * on genesis.
     * </pre>
     */
    default void baseHeader(com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBaseHeaderMethod(), responseObserver);
    }

    /**
     * <pre>
     * HeaderDepth returns the depth of the header in main chain or error if the
     * block is not found or it exists on fork
     * </pre>
     */
    default void headerDepth(com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHeaderDepthMethod(), responseObserver);
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
    public void params(com.babylon.btclightclient.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Hashes retrieves the hashes maintained by the module.
     * </pre>
     */
    public void hashes(com.babylon.btclightclient.v1.QueryProto.QueryHashesRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryHashesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHashesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Contains checks whether a hash is maintained by the module.
     * </pre>
     */
    public void contains(com.babylon.btclightclient.v1.QueryProto.QueryContainsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryContainsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getContainsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ContainsBytes is a temporary method that
     * checks whether a hash is maintained by the module.
     * See discussion at https://github.com/babylonlabs-io/babylon/pull/132
     * for more details.
     * </pre>
     */
    public void containsBytes(com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getContainsBytesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MainChain returns the canonical chain
     * </pre>
     */
    public void mainChain(com.babylon.btclightclient.v1.QueryProto.QueryMainChainRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryMainChainResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMainChainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Tip return best header on canonical chain
     * </pre>
     */
    public void tip(com.babylon.btclightclient.v1.QueryProto.QueryTipRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryTipResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTipMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BaseHeader returns the base BTC header of the chain. This header is defined
     * on genesis.
     * </pre>
     */
    public void baseHeader(com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBaseHeaderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * HeaderDepth returns the depth of the header in main chain or error if the
     * block is not found or it exists on fork
     * </pre>
     */
    public void headerDepth(com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthRequest request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHeaderDepthMethod(), getCallOptions()), request, responseObserver);
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
    public com.babylon.btclightclient.v1.QueryProto.QueryParamsResponse params(com.babylon.btclightclient.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Hashes retrieves the hashes maintained by the module.
     * </pre>
     */
    public com.babylon.btclightclient.v1.QueryProto.QueryHashesResponse hashes(com.babylon.btclightclient.v1.QueryProto.QueryHashesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHashesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Contains checks whether a hash is maintained by the module.
     * </pre>
     */
    public com.babylon.btclightclient.v1.QueryProto.QueryContainsResponse contains(com.babylon.btclightclient.v1.QueryProto.QueryContainsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getContainsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ContainsBytes is a temporary method that
     * checks whether a hash is maintained by the module.
     * See discussion at https://github.com/babylonlabs-io/babylon/pull/132
     * for more details.
     * </pre>
     */
    public com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesResponse containsBytes(com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getContainsBytesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MainChain returns the canonical chain
     * </pre>
     */
    public com.babylon.btclightclient.v1.QueryProto.QueryMainChainResponse mainChain(com.babylon.btclightclient.v1.QueryProto.QueryMainChainRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMainChainMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Tip return best header on canonical chain
     * </pre>
     */
    public com.babylon.btclightclient.v1.QueryProto.QueryTipResponse tip(com.babylon.btclightclient.v1.QueryProto.QueryTipRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTipMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BaseHeader returns the base BTC header of the chain. This header is defined
     * on genesis.
     * </pre>
     */
    public com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderResponse baseHeader(com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBaseHeaderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * HeaderDepth returns the depth of the header in main chain or error if the
     * block is not found or it exists on fork
     * </pre>
     */
    public com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthResponse headerDepth(com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHeaderDepthMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btclightclient.v1.QueryProto.QueryParamsResponse> params(
        com.babylon.btclightclient.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Hashes retrieves the hashes maintained by the module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btclightclient.v1.QueryProto.QueryHashesResponse> hashes(
        com.babylon.btclightclient.v1.QueryProto.QueryHashesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHashesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Contains checks whether a hash is maintained by the module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btclightclient.v1.QueryProto.QueryContainsResponse> contains(
        com.babylon.btclightclient.v1.QueryProto.QueryContainsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getContainsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ContainsBytes is a temporary method that
     * checks whether a hash is maintained by the module.
     * See discussion at https://github.com/babylonlabs-io/babylon/pull/132
     * for more details.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesResponse> containsBytes(
        com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getContainsBytesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MainChain returns the canonical chain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btclightclient.v1.QueryProto.QueryMainChainResponse> mainChain(
        com.babylon.btclightclient.v1.QueryProto.QueryMainChainRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMainChainMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Tip return best header on canonical chain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btclightclient.v1.QueryProto.QueryTipResponse> tip(
        com.babylon.btclightclient.v1.QueryProto.QueryTipRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTipMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BaseHeader returns the base BTC header of the chain. This header is defined
     * on genesis.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderResponse> baseHeader(
        com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBaseHeaderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * HeaderDepth returns the depth of the header in main chain or error if the
     * block is not found or it exists on fork
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthResponse> headerDepth(
        com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHeaderDepthMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_HASHES = 1;
  private static final int METHODID_CONTAINS = 2;
  private static final int METHODID_CONTAINS_BYTES = 3;
  private static final int METHODID_MAIN_CHAIN = 4;
  private static final int METHODID_TIP = 5;
  private static final int METHODID_BASE_HEADER = 6;
  private static final int METHODID_HEADER_DEPTH = 7;

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
          serviceImpl.params((com.babylon.btclightclient.v1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_HASHES:
          serviceImpl.hashes((com.babylon.btclightclient.v1.QueryProto.QueryHashesRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryHashesResponse>) responseObserver);
          break;
        case METHODID_CONTAINS:
          serviceImpl.contains((com.babylon.btclightclient.v1.QueryProto.QueryContainsRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryContainsResponse>) responseObserver);
          break;
        case METHODID_CONTAINS_BYTES:
          serviceImpl.containsBytes((com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesResponse>) responseObserver);
          break;
        case METHODID_MAIN_CHAIN:
          serviceImpl.mainChain((com.babylon.btclightclient.v1.QueryProto.QueryMainChainRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryMainChainResponse>) responseObserver);
          break;
        case METHODID_TIP:
          serviceImpl.tip((com.babylon.btclightclient.v1.QueryProto.QueryTipRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryTipResponse>) responseObserver);
          break;
        case METHODID_BASE_HEADER:
          serviceImpl.baseHeader((com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderResponse>) responseObserver);
          break;
        case METHODID_HEADER_DEPTH:
          serviceImpl.headerDepth((com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthResponse>) responseObserver);
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
              com.babylon.btclightclient.v1.QueryProto.QueryParamsRequest,
              com.babylon.btclightclient.v1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getHashesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btclightclient.v1.QueryProto.QueryHashesRequest,
              com.babylon.btclightclient.v1.QueryProto.QueryHashesResponse>(
                service, METHODID_HASHES)))
        .addMethod(
          getContainsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btclightclient.v1.QueryProto.QueryContainsRequest,
              com.babylon.btclightclient.v1.QueryProto.QueryContainsResponse>(
                service, METHODID_CONTAINS)))
        .addMethod(
          getContainsBytesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesRequest,
              com.babylon.btclightclient.v1.QueryProto.QueryContainsBytesResponse>(
                service, METHODID_CONTAINS_BYTES)))
        .addMethod(
          getMainChainMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btclightclient.v1.QueryProto.QueryMainChainRequest,
              com.babylon.btclightclient.v1.QueryProto.QueryMainChainResponse>(
                service, METHODID_MAIN_CHAIN)))
        .addMethod(
          getTipMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btclightclient.v1.QueryProto.QueryTipRequest,
              com.babylon.btclightclient.v1.QueryProto.QueryTipResponse>(
                service, METHODID_TIP)))
        .addMethod(
          getBaseHeaderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderRequest,
              com.babylon.btclightclient.v1.QueryProto.QueryBaseHeaderResponse>(
                service, METHODID_BASE_HEADER)))
        .addMethod(
          getHeaderDepthMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthRequest,
              com.babylon.btclightclient.v1.QueryProto.QueryHeaderDepthResponse>(
                service, METHODID_HEADER_DEPTH)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.babylon.btclightclient.v1.QueryProto.getDescriptor();
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
              .addMethod(getHashesMethod())
              .addMethod(getContainsMethod())
              .addMethod(getContainsBytesMethod())
              .addMethod(getMainChainMethod())
              .addMethod(getTipMethod())
              .addMethod(getBaseHeaderMethod())
              .addMethod(getHeaderDepthMethod())
              .build();
        }
      }
    }
    return result;
  }
}
