package com.cosmos.evidence.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmos/evidence/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "cosmos.evidence.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceRequest,
      com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceResponse> getEvidenceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Evidence",
      requestType = com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceRequest.class,
      responseType = com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceRequest,
      com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceResponse> getEvidenceMethod() {
    io.grpc.MethodDescriptor<com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceRequest, com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceResponse> getEvidenceMethod;
    if ((getEvidenceMethod = QueryGrpc.getEvidenceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEvidenceMethod = QueryGrpc.getEvidenceMethod) == null) {
          QueryGrpc.getEvidenceMethod = getEvidenceMethod =
              io.grpc.MethodDescriptor.<com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceRequest, com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Evidence"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Evidence"))
              .build();
        }
      }
    }
    return getEvidenceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceRequest,
      com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceResponse> getAllEvidenceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllEvidence",
      requestType = com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceRequest.class,
      responseType = com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceRequest,
      com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceResponse> getAllEvidenceMethod() {
    io.grpc.MethodDescriptor<com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceRequest, com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceResponse> getAllEvidenceMethod;
    if ((getAllEvidenceMethod = QueryGrpc.getAllEvidenceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllEvidenceMethod = QueryGrpc.getAllEvidenceMethod) == null) {
          QueryGrpc.getAllEvidenceMethod = getAllEvidenceMethod =
              io.grpc.MethodDescriptor.<com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceRequest, com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllEvidence"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllEvidence"))
              .build();
        }
      }
    }
    return getAllEvidenceMethod;
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
     * Evidence queries evidence based on evidence hash.
     * </pre>
     */
    default void evidence(com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEvidenceMethod(), responseObserver);
    }

    /**
     * <pre>
     * AllEvidence queries all evidence.
     * </pre>
     */
    default void allEvidence(com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAllEvidenceMethod(), responseObserver);
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
     * Evidence queries evidence based on evidence hash.
     * </pre>
     */
    public void evidence(com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEvidenceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AllEvidence queries all evidence.
     * </pre>
     */
    public void allEvidence(com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAllEvidenceMethod(), getCallOptions()), request, responseObserver);
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
     * Evidence queries evidence based on evidence hash.
     * </pre>
     */
    public com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceResponse evidence(com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEvidenceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AllEvidence queries all evidence.
     * </pre>
     */
    public com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceResponse allEvidence(com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAllEvidenceMethod(), getCallOptions(), request);
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
     * Evidence queries evidence based on evidence hash.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceResponse> evidence(
        com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEvidenceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AllEvidence queries all evidence.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceResponse> allEvidence(
        com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAllEvidenceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EVIDENCE = 0;
  private static final int METHODID_ALL_EVIDENCE = 1;

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
        case METHODID_EVIDENCE:
          serviceImpl.evidence((com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceResponse>) responseObserver);
          break;
        case METHODID_ALL_EVIDENCE:
          serviceImpl.allEvidence((com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceResponse>) responseObserver);
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
          getEvidenceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceRequest,
              com.cosmos.evidence.v1beta1.QueryProto.QueryEvidenceResponse>(
                service, METHODID_EVIDENCE)))
        .addMethod(
          getAllEvidenceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceRequest,
              com.cosmos.evidence.v1beta1.QueryProto.QueryAllEvidenceResponse>(
                service, METHODID_ALL_EVIDENCE)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmos.evidence.v1beta1.QueryProto.getDescriptor();
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
              .addMethod(getEvidenceMethod())
              .addMethod(getAllEvidenceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
