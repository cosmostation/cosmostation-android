package com.ibc.lightclients.wasm.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query service for wasm module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: ibc/lightclients/wasm/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "ibc.lightclients.wasm.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsRequest,
      com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsResponse> getChecksumsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Checksums",
      requestType = com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsRequest.class,
      responseType = com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsRequest,
      com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsResponse> getChecksumsMethod() {
    io.grpc.MethodDescriptor<com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsRequest, com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsResponse> getChecksumsMethod;
    if ((getChecksumsMethod = QueryGrpc.getChecksumsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getChecksumsMethod = QueryGrpc.getChecksumsMethod) == null) {
          QueryGrpc.getChecksumsMethod = getChecksumsMethod =
              io.grpc.MethodDescriptor.<com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsRequest, com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Checksums"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Checksums"))
              .build();
        }
      }
    }
    return getChecksumsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeRequest,
      com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeResponse> getCodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Code",
      requestType = com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeRequest.class,
      responseType = com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeRequest,
      com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeResponse> getCodeMethod() {
    io.grpc.MethodDescriptor<com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeRequest, com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeResponse> getCodeMethod;
    if ((getCodeMethod = QueryGrpc.getCodeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCodeMethod = QueryGrpc.getCodeMethod) == null) {
          QueryGrpc.getCodeMethod = getCodeMethod =
              io.grpc.MethodDescriptor.<com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeRequest, com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Code"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Code"))
              .build();
        }
      }
    }
    return getCodeMethod;
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
   * Query service for wasm module
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Get all Wasm checksums
     * </pre>
     */
    default void checksums(com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getChecksumsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get Wasm code for given checksum
     * </pre>
     */
    default void code(com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeRequest request,
        io.grpc.stub.StreamObserver<com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCodeMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query service for wasm module
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
   * Query service for wasm module
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
     * Get all Wasm checksums
     * </pre>
     */
    public void checksums(com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getChecksumsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get Wasm code for given checksum
     * </pre>
     */
    public void code(com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeRequest request,
        io.grpc.stub.StreamObserver<com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCodeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query service for wasm module
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
     * Get all Wasm checksums
     * </pre>
     */
    public com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsResponse checksums(com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getChecksumsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get Wasm code for given checksum
     * </pre>
     */
    public com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeResponse code(com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCodeMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query service for wasm module
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
     * Get all Wasm checksums
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsResponse> checksums(
        com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getChecksumsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get Wasm code for given checksum
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeResponse> code(
        com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCodeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECKSUMS = 0;
  private static final int METHODID_CODE = 1;

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
        case METHODID_CHECKSUMS:
          serviceImpl.checksums((com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsResponse>) responseObserver);
          break;
        case METHODID_CODE:
          serviceImpl.code((com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeResponse>) responseObserver);
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
          getChecksumsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsRequest,
              com.ibc.lightclients.wasm.v1.QueryProto.QueryChecksumsResponse>(
                service, METHODID_CHECKSUMS)))
        .addMethod(
          getCodeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeRequest,
              com.ibc.lightclients.wasm.v1.QueryProto.QueryCodeResponse>(
                service, METHODID_CODE)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ibc.lightclients.wasm.v1.QueryProto.getDescriptor();
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
              .addMethod(getChecksumsMethod())
              .addMethod(getCodeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
