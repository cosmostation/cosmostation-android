package com.cosmos.base.node.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Service defines the gRPC querier service for node related queries.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmos/base/node/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ServiceGrpc {

  private ServiceGrpc() {}

  public static final String SERVICE_NAME = "cosmos.base.node.v1beta1.Service";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmos.base.node.v1beta1.QueryProto.ConfigRequest,
      com.cosmos.base.node.v1beta1.QueryProto.ConfigResponse> getConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Config",
      requestType = com.cosmos.base.node.v1beta1.QueryProto.ConfigRequest.class,
      responseType = com.cosmos.base.node.v1beta1.QueryProto.ConfigResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.base.node.v1beta1.QueryProto.ConfigRequest,
      com.cosmos.base.node.v1beta1.QueryProto.ConfigResponse> getConfigMethod() {
    io.grpc.MethodDescriptor<com.cosmos.base.node.v1beta1.QueryProto.ConfigRequest, com.cosmos.base.node.v1beta1.QueryProto.ConfigResponse> getConfigMethod;
    if ((getConfigMethod = ServiceGrpc.getConfigMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getConfigMethod = ServiceGrpc.getConfigMethod) == null) {
          ServiceGrpc.getConfigMethod = getConfigMethod =
              io.grpc.MethodDescriptor.<com.cosmos.base.node.v1beta1.QueryProto.ConfigRequest, com.cosmos.base.node.v1beta1.QueryProto.ConfigResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Config"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.node.v1beta1.QueryProto.ConfigRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.node.v1beta1.QueryProto.ConfigResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("Config"))
              .build();
        }
      }
    }
    return getConfigMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.base.node.v1beta1.QueryProto.StatusRequest,
      com.cosmos.base.node.v1beta1.QueryProto.StatusResponse> getStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Status",
      requestType = com.cosmos.base.node.v1beta1.QueryProto.StatusRequest.class,
      responseType = com.cosmos.base.node.v1beta1.QueryProto.StatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.base.node.v1beta1.QueryProto.StatusRequest,
      com.cosmos.base.node.v1beta1.QueryProto.StatusResponse> getStatusMethod() {
    io.grpc.MethodDescriptor<com.cosmos.base.node.v1beta1.QueryProto.StatusRequest, com.cosmos.base.node.v1beta1.QueryProto.StatusResponse> getStatusMethod;
    if ((getStatusMethod = ServiceGrpc.getStatusMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getStatusMethod = ServiceGrpc.getStatusMethod) == null) {
          ServiceGrpc.getStatusMethod = getStatusMethod =
              io.grpc.MethodDescriptor.<com.cosmos.base.node.v1beta1.QueryProto.StatusRequest, com.cosmos.base.node.v1beta1.QueryProto.StatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Status"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.node.v1beta1.QueryProto.StatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.node.v1beta1.QueryProto.StatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("Status"))
              .build();
        }
      }
    }
    return getStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServiceStub>() {
        @java.lang.Override
        public ServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServiceStub(channel, callOptions);
        }
      };
    return ServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServiceBlockingStub>() {
        @java.lang.Override
        public ServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServiceBlockingStub(channel, callOptions);
        }
      };
    return ServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServiceFutureStub>() {
        @java.lang.Override
        public ServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServiceFutureStub(channel, callOptions);
        }
      };
    return ServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Service defines the gRPC querier service for node related queries.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Config queries for the operator configuration.
     * </pre>
     */
    default void config(com.cosmos.base.node.v1beta1.QueryProto.ConfigRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.node.v1beta1.QueryProto.ConfigResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConfigMethod(), responseObserver);
    }

    /**
     * <pre>
     * Status queries for the node status.
     * </pre>
     */
    default void status(com.cosmos.base.node.v1beta1.QueryProto.StatusRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.node.v1beta1.QueryProto.StatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStatusMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Service.
   * <pre>
   * Service defines the gRPC querier service for node related queries.
   * </pre>
   */
  public static abstract class ServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Service.
   * <pre>
   * Service defines the gRPC querier service for node related queries.
   * </pre>
   */
  public static final class ServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ServiceStub> {
    private ServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Config queries for the operator configuration.
     * </pre>
     */
    public void config(com.cosmos.base.node.v1beta1.QueryProto.ConfigRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.node.v1beta1.QueryProto.ConfigResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConfigMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Status queries for the node status.
     * </pre>
     */
    public void status(com.cosmos.base.node.v1beta1.QueryProto.StatusRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.node.v1beta1.QueryProto.StatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Service.
   * <pre>
   * Service defines the gRPC querier service for node related queries.
   * </pre>
   */
  public static final class ServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ServiceBlockingStub> {
    private ServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Config queries for the operator configuration.
     * </pre>
     */
    public com.cosmos.base.node.v1beta1.QueryProto.ConfigResponse config(com.cosmos.base.node.v1beta1.QueryProto.ConfigRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConfigMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Status queries for the node status.
     * </pre>
     */
    public com.cosmos.base.node.v1beta1.QueryProto.StatusResponse status(com.cosmos.base.node.v1beta1.QueryProto.StatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Service.
   * <pre>
   * Service defines the gRPC querier service for node related queries.
   * </pre>
   */
  public static final class ServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ServiceFutureStub> {
    private ServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Config queries for the operator configuration.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.base.node.v1beta1.QueryProto.ConfigResponse> config(
        com.cosmos.base.node.v1beta1.QueryProto.ConfigRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConfigMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Status queries for the node status.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.base.node.v1beta1.QueryProto.StatusResponse> status(
        com.cosmos.base.node.v1beta1.QueryProto.StatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CONFIG = 0;
  private static final int METHODID_STATUS = 1;

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
        case METHODID_CONFIG:
          serviceImpl.config((com.cosmos.base.node.v1beta1.QueryProto.ConfigRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.base.node.v1beta1.QueryProto.ConfigResponse>) responseObserver);
          break;
        case METHODID_STATUS:
          serviceImpl.status((com.cosmos.base.node.v1beta1.QueryProto.StatusRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.base.node.v1beta1.QueryProto.StatusResponse>) responseObserver);
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
          getConfigMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.base.node.v1beta1.QueryProto.ConfigRequest,
              com.cosmos.base.node.v1beta1.QueryProto.ConfigResponse>(
                service, METHODID_CONFIG)))
        .addMethod(
          getStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.base.node.v1beta1.QueryProto.StatusRequest,
              com.cosmos.base.node.v1beta1.QueryProto.StatusResponse>(
                service, METHODID_STATUS)))
        .build();
  }

  private static abstract class ServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmos.base.node.v1beta1.QueryProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Service");
    }
  }

  private static final class ServiceFileDescriptorSupplier
      extends ServiceBaseDescriptorSupplier {
    ServiceFileDescriptorSupplier() {}
  }

  private static final class ServiceMethodDescriptorSupplier
      extends ServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ServiceFileDescriptorSupplier())
              .addMethod(getConfigMethod())
              .addMethod(getStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
