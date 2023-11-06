package com.cosmos.store.streaming.abci;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * ABCIListenerService is the service for the BaseApp ABCIListener interface
 * Deprecated: Store v1 is deprecated as of v0.50.x, please use Store v2 types
 * instead.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmos/store/streaming/abci/grpc.proto")
@io.grpc.stub.annotations.GrpcGenerated
@java.lang.Deprecated
public final class ABCIListenerServiceGrpc {

  private ABCIListenerServiceGrpc() {}

  public static final String SERVICE_NAME = "cosmos.store.streaming.abci.ABCIListenerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockRequest,
      com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockResponse> getListenFinalizeBlockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListenFinalizeBlock",
      requestType = com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockRequest.class,
      responseType = com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockRequest,
      com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockResponse> getListenFinalizeBlockMethod() {
    io.grpc.MethodDescriptor<com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockRequest, com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockResponse> getListenFinalizeBlockMethod;
    if ((getListenFinalizeBlockMethod = ABCIListenerServiceGrpc.getListenFinalizeBlockMethod) == null) {
      synchronized (ABCIListenerServiceGrpc.class) {
        if ((getListenFinalizeBlockMethod = ABCIListenerServiceGrpc.getListenFinalizeBlockMethod) == null) {
          ABCIListenerServiceGrpc.getListenFinalizeBlockMethod = getListenFinalizeBlockMethod =
              io.grpc.MethodDescriptor.<com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockRequest, com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListenFinalizeBlock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIListenerServiceMethodDescriptorSupplier("ListenFinalizeBlock"))
              .build();
        }
      }
    }
    return getListenFinalizeBlockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.store.streaming.abci.GrpcProto.ListenCommitRequest,
      com.cosmos.store.streaming.abci.GrpcProto.ListenCommitResponse> getListenCommitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListenCommit",
      requestType = com.cosmos.store.streaming.abci.GrpcProto.ListenCommitRequest.class,
      responseType = com.cosmos.store.streaming.abci.GrpcProto.ListenCommitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.store.streaming.abci.GrpcProto.ListenCommitRequest,
      com.cosmos.store.streaming.abci.GrpcProto.ListenCommitResponse> getListenCommitMethod() {
    io.grpc.MethodDescriptor<com.cosmos.store.streaming.abci.GrpcProto.ListenCommitRequest, com.cosmos.store.streaming.abci.GrpcProto.ListenCommitResponse> getListenCommitMethod;
    if ((getListenCommitMethod = ABCIListenerServiceGrpc.getListenCommitMethod) == null) {
      synchronized (ABCIListenerServiceGrpc.class) {
        if ((getListenCommitMethod = ABCIListenerServiceGrpc.getListenCommitMethod) == null) {
          ABCIListenerServiceGrpc.getListenCommitMethod = getListenCommitMethod =
              io.grpc.MethodDescriptor.<com.cosmos.store.streaming.abci.GrpcProto.ListenCommitRequest, com.cosmos.store.streaming.abci.GrpcProto.ListenCommitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListenCommit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.store.streaming.abci.GrpcProto.ListenCommitRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.store.streaming.abci.GrpcProto.ListenCommitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIListenerServiceMethodDescriptorSupplier("ListenCommit"))
              .build();
        }
      }
    }
    return getListenCommitMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ABCIListenerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ABCIListenerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ABCIListenerServiceStub>() {
        @java.lang.Override
        public ABCIListenerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ABCIListenerServiceStub(channel, callOptions);
        }
      };
    return ABCIListenerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ABCIListenerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ABCIListenerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ABCIListenerServiceBlockingStub>() {
        @java.lang.Override
        public ABCIListenerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ABCIListenerServiceBlockingStub(channel, callOptions);
        }
      };
    return ABCIListenerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ABCIListenerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ABCIListenerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ABCIListenerServiceFutureStub>() {
        @java.lang.Override
        public ABCIListenerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ABCIListenerServiceFutureStub(channel, callOptions);
        }
      };
    return ABCIListenerServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * ABCIListenerService is the service for the BaseApp ABCIListener interface
   * Deprecated: Store v1 is deprecated as of v0.50.x, please use Store v2 types
   * instead.
   * </pre>
   */
  @java.lang.Deprecated
  public interface AsyncService {

    /**
     * <pre>
     * ListenFinalizeBlock is the corresponding endpoint for
     * ABCIListener.ListenEndBlock
     * </pre>
     */
    default void listenFinalizeBlock(com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListenFinalizeBlockMethod(), responseObserver);
    }

    /**
     * <pre>
     * ListenCommit is the corresponding endpoint for ABCIListener.ListenCommit
     * </pre>
     */
    default void listenCommit(com.cosmos.store.streaming.abci.GrpcProto.ListenCommitRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.store.streaming.abci.GrpcProto.ListenCommitResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListenCommitMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ABCIListenerService.
   * <pre>
   * ABCIListenerService is the service for the BaseApp ABCIListener interface
   * Deprecated: Store v1 is deprecated as of v0.50.x, please use Store v2 types
   * instead.
   * </pre>
   */
  @java.lang.Deprecated
  public static abstract class ABCIListenerServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ABCIListenerServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ABCIListenerService.
   * <pre>
   * ABCIListenerService is the service for the BaseApp ABCIListener interface
   * Deprecated: Store v1 is deprecated as of v0.50.x, please use Store v2 types
   * instead.
   * </pre>
   */
  @java.lang.Deprecated
  public static final class ABCIListenerServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ABCIListenerServiceStub> {
    private ABCIListenerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ABCIListenerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ABCIListenerServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * ListenFinalizeBlock is the corresponding endpoint for
     * ABCIListener.ListenEndBlock
     * </pre>
     */
    public void listenFinalizeBlock(com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListenFinalizeBlockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ListenCommit is the corresponding endpoint for ABCIListener.ListenCommit
     * </pre>
     */
    public void listenCommit(com.cosmos.store.streaming.abci.GrpcProto.ListenCommitRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.store.streaming.abci.GrpcProto.ListenCommitResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListenCommitMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ABCIListenerService.
   * <pre>
   * ABCIListenerService is the service for the BaseApp ABCIListener interface
   * Deprecated: Store v1 is deprecated as of v0.50.x, please use Store v2 types
   * instead.
   * </pre>
   */
  @java.lang.Deprecated
  public static final class ABCIListenerServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ABCIListenerServiceBlockingStub> {
    private ABCIListenerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ABCIListenerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ABCIListenerServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * ListenFinalizeBlock is the corresponding endpoint for
     * ABCIListener.ListenEndBlock
     * </pre>
     */
    public com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockResponse listenFinalizeBlock(com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListenFinalizeBlockMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ListenCommit is the corresponding endpoint for ABCIListener.ListenCommit
     * </pre>
     */
    public com.cosmos.store.streaming.abci.GrpcProto.ListenCommitResponse listenCommit(com.cosmos.store.streaming.abci.GrpcProto.ListenCommitRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListenCommitMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ABCIListenerService.
   * <pre>
   * ABCIListenerService is the service for the BaseApp ABCIListener interface
   * Deprecated: Store v1 is deprecated as of v0.50.x, please use Store v2 types
   * instead.
   * </pre>
   */
  @java.lang.Deprecated
  public static final class ABCIListenerServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ABCIListenerServiceFutureStub> {
    private ABCIListenerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ABCIListenerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ABCIListenerServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * ListenFinalizeBlock is the corresponding endpoint for
     * ABCIListener.ListenEndBlock
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockResponse> listenFinalizeBlock(
        com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListenFinalizeBlockMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ListenCommit is the corresponding endpoint for ABCIListener.ListenCommit
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.store.streaming.abci.GrpcProto.ListenCommitResponse> listenCommit(
        com.cosmos.store.streaming.abci.GrpcProto.ListenCommitRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListenCommitMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LISTEN_FINALIZE_BLOCK = 0;
  private static final int METHODID_LISTEN_COMMIT = 1;

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
        case METHODID_LISTEN_FINALIZE_BLOCK:
          serviceImpl.listenFinalizeBlock((com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockResponse>) responseObserver);
          break;
        case METHODID_LISTEN_COMMIT:
          serviceImpl.listenCommit((com.cosmos.store.streaming.abci.GrpcProto.ListenCommitRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.store.streaming.abci.GrpcProto.ListenCommitResponse>) responseObserver);
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
          getListenFinalizeBlockMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockRequest,
              com.cosmos.store.streaming.abci.GrpcProto.ListenFinalizeBlockResponse>(
                service, METHODID_LISTEN_FINALIZE_BLOCK)))
        .addMethod(
          getListenCommitMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.store.streaming.abci.GrpcProto.ListenCommitRequest,
              com.cosmos.store.streaming.abci.GrpcProto.ListenCommitResponse>(
                service, METHODID_LISTEN_COMMIT)))
        .build();
  }

  private static abstract class ABCIListenerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ABCIListenerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmos.store.streaming.abci.GrpcProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ABCIListenerService");
    }
  }

  private static final class ABCIListenerServiceFileDescriptorSupplier
      extends ABCIListenerServiceBaseDescriptorSupplier {
    ABCIListenerServiceFileDescriptorSupplier() {}
  }

  private static final class ABCIListenerServiceMethodDescriptorSupplier
      extends ABCIListenerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ABCIListenerServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ABCIListenerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ABCIListenerServiceFileDescriptorSupplier())
              .addMethod(getListenFinalizeBlockMethod())
              .addMethod(getListenCommitMethod())
              .build();
        }
      }
    }
    return result;
  }
}
