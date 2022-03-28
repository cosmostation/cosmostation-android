package io.provenance.name.v1;

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
 * Query defines the gRPC querier service for distribution module.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: provenance/name/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "provenance.name.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.provenance.name.v1.QueryParamsRequest,
      io.provenance.name.v1.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = io.provenance.name.v1.QueryParamsRequest.class,
      responseType = io.provenance.name.v1.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.name.v1.QueryParamsRequest,
      io.provenance.name.v1.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<io.provenance.name.v1.QueryParamsRequest, io.provenance.name.v1.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<io.provenance.name.v1.QueryParamsRequest, io.provenance.name.v1.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.name.v1.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.name.v1.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.name.v1.QueryResolveRequest,
      io.provenance.name.v1.QueryResolveResponse> getResolveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Resolve",
      requestType = io.provenance.name.v1.QueryResolveRequest.class,
      responseType = io.provenance.name.v1.QueryResolveResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.name.v1.QueryResolveRequest,
      io.provenance.name.v1.QueryResolveResponse> getResolveMethod() {
    io.grpc.MethodDescriptor<io.provenance.name.v1.QueryResolveRequest, io.provenance.name.v1.QueryResolveResponse> getResolveMethod;
    if ((getResolveMethod = QueryGrpc.getResolveMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getResolveMethod = QueryGrpc.getResolveMethod) == null) {
          QueryGrpc.getResolveMethod = getResolveMethod =
              io.grpc.MethodDescriptor.<io.provenance.name.v1.QueryResolveRequest, io.provenance.name.v1.QueryResolveResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Resolve"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.name.v1.QueryResolveRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.name.v1.QueryResolveResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Resolve"))
              .build();
        }
      }
    }
    return getResolveMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.name.v1.QueryReverseLookupRequest,
      io.provenance.name.v1.QueryReverseLookupResponse> getReverseLookupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReverseLookup",
      requestType = io.provenance.name.v1.QueryReverseLookupRequest.class,
      responseType = io.provenance.name.v1.QueryReverseLookupResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.name.v1.QueryReverseLookupRequest,
      io.provenance.name.v1.QueryReverseLookupResponse> getReverseLookupMethod() {
    io.grpc.MethodDescriptor<io.provenance.name.v1.QueryReverseLookupRequest, io.provenance.name.v1.QueryReverseLookupResponse> getReverseLookupMethod;
    if ((getReverseLookupMethod = QueryGrpc.getReverseLookupMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getReverseLookupMethod = QueryGrpc.getReverseLookupMethod) == null) {
          QueryGrpc.getReverseLookupMethod = getReverseLookupMethod =
              io.grpc.MethodDescriptor.<io.provenance.name.v1.QueryReverseLookupRequest, io.provenance.name.v1.QueryReverseLookupResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReverseLookup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.name.v1.QueryReverseLookupRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.name.v1.QueryReverseLookupResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ReverseLookup"))
              .build();
        }
      }
    }
    return getReverseLookupMethod;
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
   * Query defines the gRPC querier service for distribution module.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Params queries params of the name module.
     * </pre>
     */
    public void params(io.provenance.name.v1.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.name.v1.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Resolve queries for the address associated with a given name
     * </pre>
     */
    public void resolve(io.provenance.name.v1.QueryResolveRequest request,
        io.grpc.stub.StreamObserver<io.provenance.name.v1.QueryResolveResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getResolveMethod(), responseObserver);
    }

    /**
     * <pre>
     * ReverseLookup queries for all names bound against a given address
     * </pre>
     */
    public void reverseLookup(io.provenance.name.v1.QueryReverseLookupRequest request,
        io.grpc.stub.StreamObserver<io.provenance.name.v1.QueryReverseLookupResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReverseLookupMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.name.v1.QueryParamsRequest,
                io.provenance.name.v1.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getResolveMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.name.v1.QueryResolveRequest,
                io.provenance.name.v1.QueryResolveResponse>(
                  this, METHODID_RESOLVE)))
          .addMethod(
            getReverseLookupMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.name.v1.QueryReverseLookupRequest,
                io.provenance.name.v1.QueryReverseLookupResponse>(
                  this, METHODID_REVERSE_LOOKUP)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for distribution module.
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
     * Params queries params of the name module.
     * </pre>
     */
    public void params(io.provenance.name.v1.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.name.v1.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Resolve queries for the address associated with a given name
     * </pre>
     */
    public void resolve(io.provenance.name.v1.QueryResolveRequest request,
        io.grpc.stub.StreamObserver<io.provenance.name.v1.QueryResolveResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getResolveMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ReverseLookup queries for all names bound against a given address
     * </pre>
     */
    public void reverseLookup(io.provenance.name.v1.QueryReverseLookupRequest request,
        io.grpc.stub.StreamObserver<io.provenance.name.v1.QueryReverseLookupResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReverseLookupMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for distribution module.
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
     * Params queries params of the name module.
     * </pre>
     */
    public io.provenance.name.v1.QueryParamsResponse params(io.provenance.name.v1.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Resolve queries for the address associated with a given name
     * </pre>
     */
    public io.provenance.name.v1.QueryResolveResponse resolve(io.provenance.name.v1.QueryResolveRequest request) {
      return blockingUnaryCall(
          getChannel(), getResolveMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ReverseLookup queries for all names bound against a given address
     * </pre>
     */
    public io.provenance.name.v1.QueryReverseLookupResponse reverseLookup(io.provenance.name.v1.QueryReverseLookupRequest request) {
      return blockingUnaryCall(
          getChannel(), getReverseLookupMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for distribution module.
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
     * Params queries params of the name module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.name.v1.QueryParamsResponse> params(
        io.provenance.name.v1.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Resolve queries for the address associated with a given name
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.name.v1.QueryResolveResponse> resolve(
        io.provenance.name.v1.QueryResolveRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getResolveMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ReverseLookup queries for all names bound against a given address
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.name.v1.QueryReverseLookupResponse> reverseLookup(
        io.provenance.name.v1.QueryReverseLookupRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getReverseLookupMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_RESOLVE = 1;
  private static final int METHODID_REVERSE_LOOKUP = 2;

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
          serviceImpl.params((io.provenance.name.v1.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.name.v1.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_RESOLVE:
          serviceImpl.resolve((io.provenance.name.v1.QueryResolveRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.name.v1.QueryResolveResponse>) responseObserver);
          break;
        case METHODID_REVERSE_LOOKUP:
          serviceImpl.reverseLookup((io.provenance.name.v1.QueryReverseLookupRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.name.v1.QueryReverseLookupResponse>) responseObserver);
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
      return io.provenance.name.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getResolveMethod())
              .addMethod(getReverseLookupMethod())
              .build();
        }
      }
    }
    return result;
  }
}
