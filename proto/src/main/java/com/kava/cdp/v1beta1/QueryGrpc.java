package com.kava.cdp.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service for cdp module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: kava/cdp/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "kava.cdp.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryParamsRequest,
      com.kava.cdp.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.kava.cdp.v1beta1.QueryProto.QueryParamsRequest.class,
      responseType = com.kava.cdp.v1beta1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryParamsRequest,
      com.kava.cdp.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryParamsRequest, com.kava.cdp.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.kava.cdp.v1beta1.QueryProto.QueryParamsRequest, com.kava.cdp.v1beta1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryAccountsRequest,
      com.kava.cdp.v1beta1.QueryProto.QueryAccountsResponse> getAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Accounts",
      requestType = com.kava.cdp.v1beta1.QueryProto.QueryAccountsRequest.class,
      responseType = com.kava.cdp.v1beta1.QueryProto.QueryAccountsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryAccountsRequest,
      com.kava.cdp.v1beta1.QueryProto.QueryAccountsResponse> getAccountsMethod() {
    io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryAccountsRequest, com.kava.cdp.v1beta1.QueryProto.QueryAccountsResponse> getAccountsMethod;
    if ((getAccountsMethod = QueryGrpc.getAccountsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountsMethod = QueryGrpc.getAccountsMethod) == null) {
          QueryGrpc.getAccountsMethod = getAccountsMethod =
              io.grpc.MethodDescriptor.<com.kava.cdp.v1beta1.QueryProto.QueryAccountsRequest, com.kava.cdp.v1beta1.QueryProto.QueryAccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Accounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.QueryProto.QueryAccountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.QueryProto.QueryAccountsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Accounts"))
              .build();
        }
      }
    }
    return getAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalRequest,
      com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalResponse> getTotalPrincipalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalPrincipal",
      requestType = com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalRequest.class,
      responseType = com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalRequest,
      com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalResponse> getTotalPrincipalMethod() {
    io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalRequest, com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalResponse> getTotalPrincipalMethod;
    if ((getTotalPrincipalMethod = QueryGrpc.getTotalPrincipalMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalPrincipalMethod = QueryGrpc.getTotalPrincipalMethod) == null) {
          QueryGrpc.getTotalPrincipalMethod = getTotalPrincipalMethod =
              io.grpc.MethodDescriptor.<com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalRequest, com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalPrincipal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalPrincipal"))
              .build();
        }
      }
    }
    return getTotalPrincipalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralRequest,
      com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralResponse> getTotalCollateralMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalCollateral",
      requestType = com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralRequest.class,
      responseType = com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralRequest,
      com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralResponse> getTotalCollateralMethod() {
    io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralRequest, com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralResponse> getTotalCollateralMethod;
    if ((getTotalCollateralMethod = QueryGrpc.getTotalCollateralMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalCollateralMethod = QueryGrpc.getTotalCollateralMethod) == null) {
          QueryGrpc.getTotalCollateralMethod = getTotalCollateralMethod =
              io.grpc.MethodDescriptor.<com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralRequest, com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalCollateral"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalCollateral"))
              .build();
        }
      }
    }
    return getTotalCollateralMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryCdpsRequest,
      com.kava.cdp.v1beta1.QueryProto.QueryCdpsResponse> getCdpsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Cdps",
      requestType = com.kava.cdp.v1beta1.QueryProto.QueryCdpsRequest.class,
      responseType = com.kava.cdp.v1beta1.QueryProto.QueryCdpsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryCdpsRequest,
      com.kava.cdp.v1beta1.QueryProto.QueryCdpsResponse> getCdpsMethod() {
    io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryCdpsRequest, com.kava.cdp.v1beta1.QueryProto.QueryCdpsResponse> getCdpsMethod;
    if ((getCdpsMethod = QueryGrpc.getCdpsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCdpsMethod = QueryGrpc.getCdpsMethod) == null) {
          QueryGrpc.getCdpsMethod = getCdpsMethod =
              io.grpc.MethodDescriptor.<com.kava.cdp.v1beta1.QueryProto.QueryCdpsRequest, com.kava.cdp.v1beta1.QueryProto.QueryCdpsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Cdps"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.QueryProto.QueryCdpsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.QueryProto.QueryCdpsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Cdps"))
              .build();
        }
      }
    }
    return getCdpsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryCdpRequest,
      com.kava.cdp.v1beta1.QueryProto.QueryCdpResponse> getCdpMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Cdp",
      requestType = com.kava.cdp.v1beta1.QueryProto.QueryCdpRequest.class,
      responseType = com.kava.cdp.v1beta1.QueryProto.QueryCdpResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryCdpRequest,
      com.kava.cdp.v1beta1.QueryProto.QueryCdpResponse> getCdpMethod() {
    io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryCdpRequest, com.kava.cdp.v1beta1.QueryProto.QueryCdpResponse> getCdpMethod;
    if ((getCdpMethod = QueryGrpc.getCdpMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCdpMethod = QueryGrpc.getCdpMethod) == null) {
          QueryGrpc.getCdpMethod = getCdpMethod =
              io.grpc.MethodDescriptor.<com.kava.cdp.v1beta1.QueryProto.QueryCdpRequest, com.kava.cdp.v1beta1.QueryProto.QueryCdpResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Cdp"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.QueryProto.QueryCdpRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.QueryProto.QueryCdpResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Cdp"))
              .build();
        }
      }
    }
    return getCdpMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryDepositsRequest,
      com.kava.cdp.v1beta1.QueryProto.QueryDepositsResponse> getDepositsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposits",
      requestType = com.kava.cdp.v1beta1.QueryProto.QueryDepositsRequest.class,
      responseType = com.kava.cdp.v1beta1.QueryProto.QueryDepositsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryDepositsRequest,
      com.kava.cdp.v1beta1.QueryProto.QueryDepositsResponse> getDepositsMethod() {
    io.grpc.MethodDescriptor<com.kava.cdp.v1beta1.QueryProto.QueryDepositsRequest, com.kava.cdp.v1beta1.QueryProto.QueryDepositsResponse> getDepositsMethod;
    if ((getDepositsMethod = QueryGrpc.getDepositsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositsMethod = QueryGrpc.getDepositsMethod) == null) {
          QueryGrpc.getDepositsMethod = getDepositsMethod =
              io.grpc.MethodDescriptor.<com.kava.cdp.v1beta1.QueryProto.QueryDepositsRequest, com.kava.cdp.v1beta1.QueryProto.QueryDepositsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposits"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.QueryProto.QueryDepositsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.cdp.v1beta1.QueryProto.QueryDepositsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Deposits"))
              .build();
        }
      }
    }
    return getDepositsMethod;
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
   * Query defines the gRPC querier service for cdp module
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Params queries all parameters of the cdp module.
     * </pre>
     */
    default void params(com.kava.cdp.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Accounts queries the CDP module accounts.
     * </pre>
     */
    default void accounts(com.kava.cdp.v1beta1.QueryProto.QueryAccountsRequest request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryAccountsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAccountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalPrincipal queries the total principal of a given collateral type.
     * </pre>
     */
    default void totalPrincipal(com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalRequest request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTotalPrincipalMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalCollateral queries the total collateral of a given collateral type.
     * </pre>
     */
    default void totalCollateral(com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralRequest request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTotalCollateralMethod(), responseObserver);
    }

    /**
     * <pre>
     * Cdps queries all active CDPs.
     * </pre>
     */
    default void cdps(com.kava.cdp.v1beta1.QueryProto.QueryCdpsRequest request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryCdpsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCdpsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Cdp queries a CDP with the input owner address and collateral type.
     * </pre>
     */
    default void cdp(com.kava.cdp.v1beta1.QueryProto.QueryCdpRequest request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryCdpResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCdpMethod(), responseObserver);
    }

    /**
     * <pre>
     * Deposits queries deposits associated with the CDP owned by an address for a collateral type.
     * </pre>
     */
    default void deposits(com.kava.cdp.v1beta1.QueryProto.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryDepositsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDepositsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service for cdp module
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
   * Query defines the gRPC querier service for cdp module
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
     * Params queries all parameters of the cdp module.
     * </pre>
     */
    public void params(com.kava.cdp.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Accounts queries the CDP module accounts.
     * </pre>
     */
    public void accounts(com.kava.cdp.v1beta1.QueryProto.QueryAccountsRequest request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryAccountsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalPrincipal queries the total principal of a given collateral type.
     * </pre>
     */
    public void totalPrincipal(com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalRequest request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTotalPrincipalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalCollateral queries the total collateral of a given collateral type.
     * </pre>
     */
    public void totalCollateral(com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralRequest request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTotalCollateralMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Cdps queries all active CDPs.
     * </pre>
     */
    public void cdps(com.kava.cdp.v1beta1.QueryProto.QueryCdpsRequest request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryCdpsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCdpsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Cdp queries a CDP with the input owner address and collateral type.
     * </pre>
     */
    public void cdp(com.kava.cdp.v1beta1.QueryProto.QueryCdpRequest request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryCdpResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCdpMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Deposits queries deposits associated with the CDP owned by an address for a collateral type.
     * </pre>
     */
    public void deposits(com.kava.cdp.v1beta1.QueryProto.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryDepositsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDepositsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for cdp module
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
     * Params queries all parameters of the cdp module.
     * </pre>
     */
    public com.kava.cdp.v1beta1.QueryProto.QueryParamsResponse params(com.kava.cdp.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Accounts queries the CDP module accounts.
     * </pre>
     */
    public com.kava.cdp.v1beta1.QueryProto.QueryAccountsResponse accounts(com.kava.cdp.v1beta1.QueryProto.QueryAccountsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAccountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalPrincipal queries the total principal of a given collateral type.
     * </pre>
     */
    public com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalResponse totalPrincipal(com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTotalPrincipalMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalCollateral queries the total collateral of a given collateral type.
     * </pre>
     */
    public com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralResponse totalCollateral(com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTotalCollateralMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Cdps queries all active CDPs.
     * </pre>
     */
    public com.kava.cdp.v1beta1.QueryProto.QueryCdpsResponse cdps(com.kava.cdp.v1beta1.QueryProto.QueryCdpsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCdpsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Cdp queries a CDP with the input owner address and collateral type.
     * </pre>
     */
    public com.kava.cdp.v1beta1.QueryProto.QueryCdpResponse cdp(com.kava.cdp.v1beta1.QueryProto.QueryCdpRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCdpMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Deposits queries deposits associated with the CDP owned by an address for a collateral type.
     * </pre>
     */
    public com.kava.cdp.v1beta1.QueryProto.QueryDepositsResponse deposits(com.kava.cdp.v1beta1.QueryProto.QueryDepositsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDepositsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for cdp module
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
     * Params queries all parameters of the cdp module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.cdp.v1beta1.QueryProto.QueryParamsResponse> params(
        com.kava.cdp.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Accounts queries the CDP module accounts.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.cdp.v1beta1.QueryProto.QueryAccountsResponse> accounts(
        com.kava.cdp.v1beta1.QueryProto.QueryAccountsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalPrincipal queries the total principal of a given collateral type.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalResponse> totalPrincipal(
        com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTotalPrincipalMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalCollateral queries the total collateral of a given collateral type.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralResponse> totalCollateral(
        com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTotalCollateralMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Cdps queries all active CDPs.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.cdp.v1beta1.QueryProto.QueryCdpsResponse> cdps(
        com.kava.cdp.v1beta1.QueryProto.QueryCdpsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCdpsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Cdp queries a CDP with the input owner address and collateral type.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.cdp.v1beta1.QueryProto.QueryCdpResponse> cdp(
        com.kava.cdp.v1beta1.QueryProto.QueryCdpRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCdpMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Deposits queries deposits associated with the CDP owned by an address for a collateral type.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.cdp.v1beta1.QueryProto.QueryDepositsResponse> deposits(
        com.kava.cdp.v1beta1.QueryProto.QueryDepositsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDepositsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_ACCOUNTS = 1;
  private static final int METHODID_TOTAL_PRINCIPAL = 2;
  private static final int METHODID_TOTAL_COLLATERAL = 3;
  private static final int METHODID_CDPS = 4;
  private static final int METHODID_CDP = 5;
  private static final int METHODID_DEPOSITS = 6;

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
          serviceImpl.params((com.kava.cdp.v1beta1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_ACCOUNTS:
          serviceImpl.accounts((com.kava.cdp.v1beta1.QueryProto.QueryAccountsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryAccountsResponse>) responseObserver);
          break;
        case METHODID_TOTAL_PRINCIPAL:
          serviceImpl.totalPrincipal((com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalResponse>) responseObserver);
          break;
        case METHODID_TOTAL_COLLATERAL:
          serviceImpl.totalCollateral((com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralResponse>) responseObserver);
          break;
        case METHODID_CDPS:
          serviceImpl.cdps((com.kava.cdp.v1beta1.QueryProto.QueryCdpsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryCdpsResponse>) responseObserver);
          break;
        case METHODID_CDP:
          serviceImpl.cdp((com.kava.cdp.v1beta1.QueryProto.QueryCdpRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryCdpResponse>) responseObserver);
          break;
        case METHODID_DEPOSITS:
          serviceImpl.deposits((com.kava.cdp.v1beta1.QueryProto.QueryDepositsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.cdp.v1beta1.QueryProto.QueryDepositsResponse>) responseObserver);
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
              com.kava.cdp.v1beta1.QueryProto.QueryParamsRequest,
              com.kava.cdp.v1beta1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getAccountsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.cdp.v1beta1.QueryProto.QueryAccountsRequest,
              com.kava.cdp.v1beta1.QueryProto.QueryAccountsResponse>(
                service, METHODID_ACCOUNTS)))
        .addMethod(
          getTotalPrincipalMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalRequest,
              com.kava.cdp.v1beta1.QueryProto.QueryTotalPrincipalResponse>(
                service, METHODID_TOTAL_PRINCIPAL)))
        .addMethod(
          getTotalCollateralMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralRequest,
              com.kava.cdp.v1beta1.QueryProto.QueryTotalCollateralResponse>(
                service, METHODID_TOTAL_COLLATERAL)))
        .addMethod(
          getCdpsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.cdp.v1beta1.QueryProto.QueryCdpsRequest,
              com.kava.cdp.v1beta1.QueryProto.QueryCdpsResponse>(
                service, METHODID_CDPS)))
        .addMethod(
          getCdpMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.cdp.v1beta1.QueryProto.QueryCdpRequest,
              com.kava.cdp.v1beta1.QueryProto.QueryCdpResponse>(
                service, METHODID_CDP)))
        .addMethod(
          getDepositsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.cdp.v1beta1.QueryProto.QueryDepositsRequest,
              com.kava.cdp.v1beta1.QueryProto.QueryDepositsResponse>(
                service, METHODID_DEPOSITS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kava.cdp.v1beta1.QueryProto.getDescriptor();
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
              .addMethod(getAccountsMethod())
              .addMethod(getTotalPrincipalMethod())
              .addMethod(getTotalCollateralMethod())
              .addMethod(getCdpsMethod())
              .addMethod(getCdpMethod())
              .addMethod(getDepositsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
