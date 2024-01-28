package com.desmos.reports.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: desmos/reports/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "desmos.reports.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.desmos.reports.v1.QueryProto.QueryReportsRequest,
      com.desmos.reports.v1.QueryProto.QueryReportsResponse> getReportsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Reports",
      requestType = com.desmos.reports.v1.QueryProto.QueryReportsRequest.class,
      responseType = com.desmos.reports.v1.QueryProto.QueryReportsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reports.v1.QueryProto.QueryReportsRequest,
      com.desmos.reports.v1.QueryProto.QueryReportsResponse> getReportsMethod() {
    io.grpc.MethodDescriptor<com.desmos.reports.v1.QueryProto.QueryReportsRequest, com.desmos.reports.v1.QueryProto.QueryReportsResponse> getReportsMethod;
    if ((getReportsMethod = QueryGrpc.getReportsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getReportsMethod = QueryGrpc.getReportsMethod) == null) {
          QueryGrpc.getReportsMethod = getReportsMethod =
              io.grpc.MethodDescriptor.<com.desmos.reports.v1.QueryProto.QueryReportsRequest, com.desmos.reports.v1.QueryProto.QueryReportsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Reports"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.QueryProto.QueryReportsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.QueryProto.QueryReportsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Reports"))
              .build();
        }
      }
    }
    return getReportsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reports.v1.QueryProto.QueryReportRequest,
      com.desmos.reports.v1.QueryProto.QueryReportResponse> getReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Report",
      requestType = com.desmos.reports.v1.QueryProto.QueryReportRequest.class,
      responseType = com.desmos.reports.v1.QueryProto.QueryReportResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reports.v1.QueryProto.QueryReportRequest,
      com.desmos.reports.v1.QueryProto.QueryReportResponse> getReportMethod() {
    io.grpc.MethodDescriptor<com.desmos.reports.v1.QueryProto.QueryReportRequest, com.desmos.reports.v1.QueryProto.QueryReportResponse> getReportMethod;
    if ((getReportMethod = QueryGrpc.getReportMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getReportMethod = QueryGrpc.getReportMethod) == null) {
          QueryGrpc.getReportMethod = getReportMethod =
              io.grpc.MethodDescriptor.<com.desmos.reports.v1.QueryProto.QueryReportRequest, com.desmos.reports.v1.QueryProto.QueryReportResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Report"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.QueryProto.QueryReportRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.QueryProto.QueryReportResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Report"))
              .build();
        }
      }
    }
    return getReportMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reports.v1.QueryProto.QueryReasonsRequest,
      com.desmos.reports.v1.QueryProto.QueryReasonsResponse> getReasonsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Reasons",
      requestType = com.desmos.reports.v1.QueryProto.QueryReasonsRequest.class,
      responseType = com.desmos.reports.v1.QueryProto.QueryReasonsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reports.v1.QueryProto.QueryReasonsRequest,
      com.desmos.reports.v1.QueryProto.QueryReasonsResponse> getReasonsMethod() {
    io.grpc.MethodDescriptor<com.desmos.reports.v1.QueryProto.QueryReasonsRequest, com.desmos.reports.v1.QueryProto.QueryReasonsResponse> getReasonsMethod;
    if ((getReasonsMethod = QueryGrpc.getReasonsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getReasonsMethod = QueryGrpc.getReasonsMethod) == null) {
          QueryGrpc.getReasonsMethod = getReasonsMethod =
              io.grpc.MethodDescriptor.<com.desmos.reports.v1.QueryProto.QueryReasonsRequest, com.desmos.reports.v1.QueryProto.QueryReasonsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Reasons"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.QueryProto.QueryReasonsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.QueryProto.QueryReasonsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Reasons"))
              .build();
        }
      }
    }
    return getReasonsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reports.v1.QueryProto.QueryReasonRequest,
      com.desmos.reports.v1.QueryProto.QueryReasonResponse> getReasonMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Reason",
      requestType = com.desmos.reports.v1.QueryProto.QueryReasonRequest.class,
      responseType = com.desmos.reports.v1.QueryProto.QueryReasonResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reports.v1.QueryProto.QueryReasonRequest,
      com.desmos.reports.v1.QueryProto.QueryReasonResponse> getReasonMethod() {
    io.grpc.MethodDescriptor<com.desmos.reports.v1.QueryProto.QueryReasonRequest, com.desmos.reports.v1.QueryProto.QueryReasonResponse> getReasonMethod;
    if ((getReasonMethod = QueryGrpc.getReasonMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getReasonMethod = QueryGrpc.getReasonMethod) == null) {
          QueryGrpc.getReasonMethod = getReasonMethod =
              io.grpc.MethodDescriptor.<com.desmos.reports.v1.QueryProto.QueryReasonRequest, com.desmos.reports.v1.QueryProto.QueryReasonResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Reason"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.QueryProto.QueryReasonRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.QueryProto.QueryReasonResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Reason"))
              .build();
        }
      }
    }
    return getReasonMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reports.v1.QueryProto.QueryParamsRequest,
      com.desmos.reports.v1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.desmos.reports.v1.QueryProto.QueryParamsRequest.class,
      responseType = com.desmos.reports.v1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reports.v1.QueryProto.QueryParamsRequest,
      com.desmos.reports.v1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.desmos.reports.v1.QueryProto.QueryParamsRequest, com.desmos.reports.v1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.desmos.reports.v1.QueryProto.QueryParamsRequest, com.desmos.reports.v1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
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
     * Reports allows to query the reports for a specific target
     * </pre>
     */
    default void reports(com.desmos.reports.v1.QueryProto.QueryReportsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.QueryProto.QueryReportsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReportsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Report allows to query the report having the given id
     * </pre>
     */
    default void report(com.desmos.reports.v1.QueryProto.QueryReportRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.QueryProto.QueryReportResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReportMethod(), responseObserver);
    }

    /**
     * <pre>
     * Reasons allows to query the supported reporting reasons for a subspace
     * </pre>
     */
    default void reasons(com.desmos.reports.v1.QueryProto.QueryReasonsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.QueryProto.QueryReasonsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReasonsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Reason allows to query the reason having the given id
     * </pre>
     */
    default void reason(com.desmos.reports.v1.QueryProto.QueryReasonRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.QueryProto.QueryReasonResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReasonMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params allows to query the module parameters
     * </pre>
     */
    default void params(com.desmos.reports.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
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
     * Reports allows to query the reports for a specific target
     * </pre>
     */
    public void reports(com.desmos.reports.v1.QueryProto.QueryReportsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.QueryProto.QueryReportsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReportsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Report allows to query the report having the given id
     * </pre>
     */
    public void report(com.desmos.reports.v1.QueryProto.QueryReportRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.QueryProto.QueryReportResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReportMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Reasons allows to query the supported reporting reasons for a subspace
     * </pre>
     */
    public void reasons(com.desmos.reports.v1.QueryProto.QueryReasonsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.QueryProto.QueryReasonsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReasonsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Reason allows to query the reason having the given id
     * </pre>
     */
    public void reason(com.desmos.reports.v1.QueryProto.QueryReasonRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.QueryProto.QueryReasonResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReasonMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params allows to query the module parameters
     * </pre>
     */
    public void params(com.desmos.reports.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
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
     * Reports allows to query the reports for a specific target
     * </pre>
     */
    public com.desmos.reports.v1.QueryProto.QueryReportsResponse reports(com.desmos.reports.v1.QueryProto.QueryReportsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReportsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Report allows to query the report having the given id
     * </pre>
     */
    public com.desmos.reports.v1.QueryProto.QueryReportResponse report(com.desmos.reports.v1.QueryProto.QueryReportRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReportMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Reasons allows to query the supported reporting reasons for a subspace
     * </pre>
     */
    public com.desmos.reports.v1.QueryProto.QueryReasonsResponse reasons(com.desmos.reports.v1.QueryProto.QueryReasonsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReasonsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Reason allows to query the reason having the given id
     * </pre>
     */
    public com.desmos.reports.v1.QueryProto.QueryReasonResponse reason(com.desmos.reports.v1.QueryProto.QueryReasonRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReasonMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params allows to query the module parameters
     * </pre>
     */
    public com.desmos.reports.v1.QueryProto.QueryParamsResponse params(com.desmos.reports.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
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
     * Reports allows to query the reports for a specific target
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reports.v1.QueryProto.QueryReportsResponse> reports(
        com.desmos.reports.v1.QueryProto.QueryReportsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReportsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Report allows to query the report having the given id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reports.v1.QueryProto.QueryReportResponse> report(
        com.desmos.reports.v1.QueryProto.QueryReportRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReportMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Reasons allows to query the supported reporting reasons for a subspace
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reports.v1.QueryProto.QueryReasonsResponse> reasons(
        com.desmos.reports.v1.QueryProto.QueryReasonsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReasonsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Reason allows to query the reason having the given id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reports.v1.QueryProto.QueryReasonResponse> reason(
        com.desmos.reports.v1.QueryProto.QueryReasonRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReasonMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params allows to query the module parameters
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reports.v1.QueryProto.QueryParamsResponse> params(
        com.desmos.reports.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REPORTS = 0;
  private static final int METHODID_REPORT = 1;
  private static final int METHODID_REASONS = 2;
  private static final int METHODID_REASON = 3;
  private static final int METHODID_PARAMS = 4;

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
        case METHODID_REPORTS:
          serviceImpl.reports((com.desmos.reports.v1.QueryProto.QueryReportsRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.reports.v1.QueryProto.QueryReportsResponse>) responseObserver);
          break;
        case METHODID_REPORT:
          serviceImpl.report((com.desmos.reports.v1.QueryProto.QueryReportRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.reports.v1.QueryProto.QueryReportResponse>) responseObserver);
          break;
        case METHODID_REASONS:
          serviceImpl.reasons((com.desmos.reports.v1.QueryProto.QueryReasonsRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.reports.v1.QueryProto.QueryReasonsResponse>) responseObserver);
          break;
        case METHODID_REASON:
          serviceImpl.reason((com.desmos.reports.v1.QueryProto.QueryReasonRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.reports.v1.QueryProto.QueryReasonResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((com.desmos.reports.v1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.reports.v1.QueryProto.QueryParamsResponse>) responseObserver);
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
          getReportsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reports.v1.QueryProto.QueryReportsRequest,
              com.desmos.reports.v1.QueryProto.QueryReportsResponse>(
                service, METHODID_REPORTS)))
        .addMethod(
          getReportMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reports.v1.QueryProto.QueryReportRequest,
              com.desmos.reports.v1.QueryProto.QueryReportResponse>(
                service, METHODID_REPORT)))
        .addMethod(
          getReasonsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reports.v1.QueryProto.QueryReasonsRequest,
              com.desmos.reports.v1.QueryProto.QueryReasonsResponse>(
                service, METHODID_REASONS)))
        .addMethod(
          getReasonMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reports.v1.QueryProto.QueryReasonRequest,
              com.desmos.reports.v1.QueryProto.QueryReasonResponse>(
                service, METHODID_REASON)))
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reports.v1.QueryProto.QueryParamsRequest,
              com.desmos.reports.v1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.desmos.reports.v1.QueryProto.getDescriptor();
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
              .addMethod(getReportsMethod())
              .addMethod(getReportMethod())
              .addMethod(getReasonsMethod())
              .addMethod(getReasonMethod())
              .addMethod(getParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
