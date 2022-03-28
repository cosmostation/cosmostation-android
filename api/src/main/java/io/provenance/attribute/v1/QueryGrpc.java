package io.provenance.attribute.v1;

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
 * Query defines the gRPC querier service for attribute module.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: provenance/attribute/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "provenance.attribute.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.provenance.attribute.v1.QueryParamsRequest,
      io.provenance.attribute.v1.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = io.provenance.attribute.v1.QueryParamsRequest.class,
      responseType = io.provenance.attribute.v1.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.attribute.v1.QueryParamsRequest,
      io.provenance.attribute.v1.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<io.provenance.attribute.v1.QueryParamsRequest, io.provenance.attribute.v1.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<io.provenance.attribute.v1.QueryParamsRequest, io.provenance.attribute.v1.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.attribute.v1.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.attribute.v1.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.attribute.v1.QueryAttributeRequest,
      io.provenance.attribute.v1.QueryAttributeResponse> getAttributeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Attribute",
      requestType = io.provenance.attribute.v1.QueryAttributeRequest.class,
      responseType = io.provenance.attribute.v1.QueryAttributeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.attribute.v1.QueryAttributeRequest,
      io.provenance.attribute.v1.QueryAttributeResponse> getAttributeMethod() {
    io.grpc.MethodDescriptor<io.provenance.attribute.v1.QueryAttributeRequest, io.provenance.attribute.v1.QueryAttributeResponse> getAttributeMethod;
    if ((getAttributeMethod = QueryGrpc.getAttributeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAttributeMethod = QueryGrpc.getAttributeMethod) == null) {
          QueryGrpc.getAttributeMethod = getAttributeMethod =
              io.grpc.MethodDescriptor.<io.provenance.attribute.v1.QueryAttributeRequest, io.provenance.attribute.v1.QueryAttributeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Attribute"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.attribute.v1.QueryAttributeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.attribute.v1.QueryAttributeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Attribute"))
              .build();
        }
      }
    }
    return getAttributeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.attribute.v1.QueryAttributesRequest,
      io.provenance.attribute.v1.QueryAttributesResponse> getAttributesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Attributes",
      requestType = io.provenance.attribute.v1.QueryAttributesRequest.class,
      responseType = io.provenance.attribute.v1.QueryAttributesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.attribute.v1.QueryAttributesRequest,
      io.provenance.attribute.v1.QueryAttributesResponse> getAttributesMethod() {
    io.grpc.MethodDescriptor<io.provenance.attribute.v1.QueryAttributesRequest, io.provenance.attribute.v1.QueryAttributesResponse> getAttributesMethod;
    if ((getAttributesMethod = QueryGrpc.getAttributesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAttributesMethod = QueryGrpc.getAttributesMethod) == null) {
          QueryGrpc.getAttributesMethod = getAttributesMethod =
              io.grpc.MethodDescriptor.<io.provenance.attribute.v1.QueryAttributesRequest, io.provenance.attribute.v1.QueryAttributesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Attributes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.attribute.v1.QueryAttributesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.attribute.v1.QueryAttributesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Attributes"))
              .build();
        }
      }
    }
    return getAttributesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.attribute.v1.QueryScanRequest,
      io.provenance.attribute.v1.QueryScanResponse> getScanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Scan",
      requestType = io.provenance.attribute.v1.QueryScanRequest.class,
      responseType = io.provenance.attribute.v1.QueryScanResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.attribute.v1.QueryScanRequest,
      io.provenance.attribute.v1.QueryScanResponse> getScanMethod() {
    io.grpc.MethodDescriptor<io.provenance.attribute.v1.QueryScanRequest, io.provenance.attribute.v1.QueryScanResponse> getScanMethod;
    if ((getScanMethod = QueryGrpc.getScanMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getScanMethod = QueryGrpc.getScanMethod) == null) {
          QueryGrpc.getScanMethod = getScanMethod =
              io.grpc.MethodDescriptor.<io.provenance.attribute.v1.QueryScanRequest, io.provenance.attribute.v1.QueryScanResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Scan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.attribute.v1.QueryScanRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.attribute.v1.QueryScanResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Scan"))
              .build();
        }
      }
    }
    return getScanMethod;
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
   * Query defines the gRPC querier service for attribute module.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Params queries params of the attribute module.
     * </pre>
     */
    public void params(io.provenance.attribute.v1.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.attribute.v1.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Attribute queries attributes on a given account (address) for one (or more) with the given name
     * </pre>
     */
    public void attribute(io.provenance.attribute.v1.QueryAttributeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.attribute.v1.QueryAttributeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAttributeMethod(), responseObserver);
    }

    /**
     * <pre>
     * Attributes queries attributes on a given account (address) for any defined attributes
     * </pre>
     */
    public void attributes(io.provenance.attribute.v1.QueryAttributesRequest request,
        io.grpc.stub.StreamObserver<io.provenance.attribute.v1.QueryAttributesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAttributesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Scan queries attributes on a given account (address) for any that match the provided suffix
     * </pre>
     */
    public void scan(io.provenance.attribute.v1.QueryScanRequest request,
        io.grpc.stub.StreamObserver<io.provenance.attribute.v1.QueryScanResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getScanMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.attribute.v1.QueryParamsRequest,
                io.provenance.attribute.v1.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getAttributeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.attribute.v1.QueryAttributeRequest,
                io.provenance.attribute.v1.QueryAttributeResponse>(
                  this, METHODID_ATTRIBUTE)))
          .addMethod(
            getAttributesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.attribute.v1.QueryAttributesRequest,
                io.provenance.attribute.v1.QueryAttributesResponse>(
                  this, METHODID_ATTRIBUTES)))
          .addMethod(
            getScanMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.attribute.v1.QueryScanRequest,
                io.provenance.attribute.v1.QueryScanResponse>(
                  this, METHODID_SCAN)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for attribute module.
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
     * <pre>
     * Params queries params of the attribute module.
     * </pre>
     */
    public void params(io.provenance.attribute.v1.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.attribute.v1.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Attribute queries attributes on a given account (address) for one (or more) with the given name
     * </pre>
     */
    public void attribute(io.provenance.attribute.v1.QueryAttributeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.attribute.v1.QueryAttributeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAttributeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Attributes queries attributes on a given account (address) for any defined attributes
     * </pre>
     */
    public void attributes(io.provenance.attribute.v1.QueryAttributesRequest request,
        io.grpc.stub.StreamObserver<io.provenance.attribute.v1.QueryAttributesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAttributesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Scan queries attributes on a given account (address) for any that match the provided suffix
     * </pre>
     */
    public void scan(io.provenance.attribute.v1.QueryScanRequest request,
        io.grpc.stub.StreamObserver<io.provenance.attribute.v1.QueryScanResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getScanMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for attribute module.
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
     * <pre>
     * Params queries params of the attribute module.
     * </pre>
     */
    public io.provenance.attribute.v1.QueryParamsResponse params(io.provenance.attribute.v1.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Attribute queries attributes on a given account (address) for one (or more) with the given name
     * </pre>
     */
    public io.provenance.attribute.v1.QueryAttributeResponse attribute(io.provenance.attribute.v1.QueryAttributeRequest request) {
      return blockingUnaryCall(
          getChannel(), getAttributeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Attributes queries attributes on a given account (address) for any defined attributes
     * </pre>
     */
    public io.provenance.attribute.v1.QueryAttributesResponse attributes(io.provenance.attribute.v1.QueryAttributesRequest request) {
      return blockingUnaryCall(
          getChannel(), getAttributesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Scan queries attributes on a given account (address) for any that match the provided suffix
     * </pre>
     */
    public io.provenance.attribute.v1.QueryScanResponse scan(io.provenance.attribute.v1.QueryScanRequest request) {
      return blockingUnaryCall(
          getChannel(), getScanMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for attribute module.
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
     * <pre>
     * Params queries params of the attribute module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.attribute.v1.QueryParamsResponse> params(
        io.provenance.attribute.v1.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Attribute queries attributes on a given account (address) for one (or more) with the given name
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.attribute.v1.QueryAttributeResponse> attribute(
        io.provenance.attribute.v1.QueryAttributeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAttributeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Attributes queries attributes on a given account (address) for any defined attributes
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.attribute.v1.QueryAttributesResponse> attributes(
        io.provenance.attribute.v1.QueryAttributesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAttributesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Scan queries attributes on a given account (address) for any that match the provided suffix
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.attribute.v1.QueryScanResponse> scan(
        io.provenance.attribute.v1.QueryScanRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getScanMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_ATTRIBUTE = 1;
  private static final int METHODID_ATTRIBUTES = 2;
  private static final int METHODID_SCAN = 3;

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
          serviceImpl.params((io.provenance.attribute.v1.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.attribute.v1.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_ATTRIBUTE:
          serviceImpl.attribute((io.provenance.attribute.v1.QueryAttributeRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.attribute.v1.QueryAttributeResponse>) responseObserver);
          break;
        case METHODID_ATTRIBUTES:
          serviceImpl.attributes((io.provenance.attribute.v1.QueryAttributesRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.attribute.v1.QueryAttributesResponse>) responseObserver);
          break;
        case METHODID_SCAN:
          serviceImpl.scan((io.provenance.attribute.v1.QueryScanRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.attribute.v1.QueryScanResponse>) responseObserver);
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
      return io.provenance.attribute.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getAttributeMethod())
              .addMethod(getAttributesMethod())
              .addMethod(getScanMethod())
              .build();
        }
      }
    }
    return result;
  }
}
