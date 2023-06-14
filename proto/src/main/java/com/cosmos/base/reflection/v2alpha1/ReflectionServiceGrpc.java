package com.cosmos.base.reflection.v2alpha1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * ReflectionService defines a service for application reflection.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmos/base/reflection/v2alpha1/reflection.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ReflectionServiceGrpc {

  private ReflectionServiceGrpc() {}

  public static final String SERVICE_NAME = "cosmos.base.reflection.v2alpha1.ReflectionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorRequest,
      com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorResponse> getGetAuthnDescriptorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAuthnDescriptor",
      requestType = com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorRequest.class,
      responseType = com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorRequest,
      com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorResponse> getGetAuthnDescriptorMethod() {
    io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorRequest, com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorResponse> getGetAuthnDescriptorMethod;
    if ((getGetAuthnDescriptorMethod = ReflectionServiceGrpc.getGetAuthnDescriptorMethod) == null) {
      synchronized (ReflectionServiceGrpc.class) {
        if ((getGetAuthnDescriptorMethod = ReflectionServiceGrpc.getGetAuthnDescriptorMethod) == null) {
          ReflectionServiceGrpc.getGetAuthnDescriptorMethod = getGetAuthnDescriptorMethod =
              io.grpc.MethodDescriptor.<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorRequest, com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAuthnDescriptor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReflectionServiceMethodDescriptorSupplier("GetAuthnDescriptor"))
              .build();
        }
      }
    }
    return getGetAuthnDescriptorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorRequest,
      com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorResponse> getGetChainDescriptorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetChainDescriptor",
      requestType = com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorRequest.class,
      responseType = com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorRequest,
      com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorResponse> getGetChainDescriptorMethod() {
    io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorRequest, com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorResponse> getGetChainDescriptorMethod;
    if ((getGetChainDescriptorMethod = ReflectionServiceGrpc.getGetChainDescriptorMethod) == null) {
      synchronized (ReflectionServiceGrpc.class) {
        if ((getGetChainDescriptorMethod = ReflectionServiceGrpc.getGetChainDescriptorMethod) == null) {
          ReflectionServiceGrpc.getGetChainDescriptorMethod = getGetChainDescriptorMethod =
              io.grpc.MethodDescriptor.<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorRequest, com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetChainDescriptor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReflectionServiceMethodDescriptorSupplier("GetChainDescriptor"))
              .build();
        }
      }
    }
    return getGetChainDescriptorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorRequest,
      com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorResponse> getGetCodecDescriptorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCodecDescriptor",
      requestType = com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorRequest.class,
      responseType = com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorRequest,
      com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorResponse> getGetCodecDescriptorMethod() {
    io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorRequest, com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorResponse> getGetCodecDescriptorMethod;
    if ((getGetCodecDescriptorMethod = ReflectionServiceGrpc.getGetCodecDescriptorMethod) == null) {
      synchronized (ReflectionServiceGrpc.class) {
        if ((getGetCodecDescriptorMethod = ReflectionServiceGrpc.getGetCodecDescriptorMethod) == null) {
          ReflectionServiceGrpc.getGetCodecDescriptorMethod = getGetCodecDescriptorMethod =
              io.grpc.MethodDescriptor.<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorRequest, com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCodecDescriptor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReflectionServiceMethodDescriptorSupplier("GetCodecDescriptor"))
              .build();
        }
      }
    }
    return getGetCodecDescriptorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorRequest,
      com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorResponse> getGetConfigurationDescriptorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetConfigurationDescriptor",
      requestType = com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorRequest.class,
      responseType = com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorRequest,
      com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorResponse> getGetConfigurationDescriptorMethod() {
    io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorRequest, com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorResponse> getGetConfigurationDescriptorMethod;
    if ((getGetConfigurationDescriptorMethod = ReflectionServiceGrpc.getGetConfigurationDescriptorMethod) == null) {
      synchronized (ReflectionServiceGrpc.class) {
        if ((getGetConfigurationDescriptorMethod = ReflectionServiceGrpc.getGetConfigurationDescriptorMethod) == null) {
          ReflectionServiceGrpc.getGetConfigurationDescriptorMethod = getGetConfigurationDescriptorMethod =
              io.grpc.MethodDescriptor.<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorRequest, com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetConfigurationDescriptor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReflectionServiceMethodDescriptorSupplier("GetConfigurationDescriptor"))
              .build();
        }
      }
    }
    return getGetConfigurationDescriptorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorRequest,
      com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorResponse> getGetQueryServicesDescriptorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetQueryServicesDescriptor",
      requestType = com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorRequest.class,
      responseType = com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorRequest,
      com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorResponse> getGetQueryServicesDescriptorMethod() {
    io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorRequest, com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorResponse> getGetQueryServicesDescriptorMethod;
    if ((getGetQueryServicesDescriptorMethod = ReflectionServiceGrpc.getGetQueryServicesDescriptorMethod) == null) {
      synchronized (ReflectionServiceGrpc.class) {
        if ((getGetQueryServicesDescriptorMethod = ReflectionServiceGrpc.getGetQueryServicesDescriptorMethod) == null) {
          ReflectionServiceGrpc.getGetQueryServicesDescriptorMethod = getGetQueryServicesDescriptorMethod =
              io.grpc.MethodDescriptor.<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorRequest, com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetQueryServicesDescriptor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReflectionServiceMethodDescriptorSupplier("GetQueryServicesDescriptor"))
              .build();
        }
      }
    }
    return getGetQueryServicesDescriptorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorRequest,
      com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorResponse> getGetTxDescriptorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTxDescriptor",
      requestType = com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorRequest.class,
      responseType = com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorRequest,
      com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorResponse> getGetTxDescriptorMethod() {
    io.grpc.MethodDescriptor<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorRequest, com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorResponse> getGetTxDescriptorMethod;
    if ((getGetTxDescriptorMethod = ReflectionServiceGrpc.getGetTxDescriptorMethod) == null) {
      synchronized (ReflectionServiceGrpc.class) {
        if ((getGetTxDescriptorMethod = ReflectionServiceGrpc.getGetTxDescriptorMethod) == null) {
          ReflectionServiceGrpc.getGetTxDescriptorMethod = getGetTxDescriptorMethod =
              io.grpc.MethodDescriptor.<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorRequest, com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTxDescriptor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReflectionServiceMethodDescriptorSupplier("GetTxDescriptor"))
              .build();
        }
      }
    }
    return getGetTxDescriptorMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ReflectionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReflectionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReflectionServiceStub>() {
        @java.lang.Override
        public ReflectionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReflectionServiceStub(channel, callOptions);
        }
      };
    return ReflectionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ReflectionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReflectionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReflectionServiceBlockingStub>() {
        @java.lang.Override
        public ReflectionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReflectionServiceBlockingStub(channel, callOptions);
        }
      };
    return ReflectionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ReflectionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReflectionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReflectionServiceFutureStub>() {
        @java.lang.Override
        public ReflectionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReflectionServiceFutureStub(channel, callOptions);
        }
      };
    return ReflectionServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * ReflectionService defines a service for application reflection.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * GetAuthnDescriptor returns information on how to authenticate transactions in the application
     * NOTE: this RPC is still experimental and might be subject to breaking changes or removal in
     * future releases of the cosmos-sdk.
     * </pre>
     */
    default void getAuthnDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAuthnDescriptorMethod(), responseObserver);
    }

    /**
     * <pre>
     * GetChainDescriptor returns the description of the chain
     * </pre>
     */
    default void getChainDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetChainDescriptorMethod(), responseObserver);
    }

    /**
     * <pre>
     * GetCodecDescriptor returns the descriptor of the codec of the application
     * </pre>
     */
    default void getCodecDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCodecDescriptorMethod(), responseObserver);
    }

    /**
     * <pre>
     * GetConfigurationDescriptor returns the descriptor for the sdk.Config of the application
     * </pre>
     */
    default void getConfigurationDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetConfigurationDescriptorMethod(), responseObserver);
    }

    /**
     * <pre>
     * GetQueryServicesDescriptor returns the available gRPC queryable services of the application
     * </pre>
     */
    default void getQueryServicesDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetQueryServicesDescriptorMethod(), responseObserver);
    }

    /**
     * <pre>
     * GetTxDescriptor returns information on the used transaction object and available msgs that can be used
     * </pre>
     */
    default void getTxDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTxDescriptorMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ReflectionService.
   * <pre>
   * ReflectionService defines a service for application reflection.
   * </pre>
   */
  public static abstract class ReflectionServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ReflectionServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ReflectionService.
   * <pre>
   * ReflectionService defines a service for application reflection.
   * </pre>
   */
  public static final class ReflectionServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ReflectionServiceStub> {
    private ReflectionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReflectionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReflectionServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * GetAuthnDescriptor returns information on how to authenticate transactions in the application
     * NOTE: this RPC is still experimental and might be subject to breaking changes or removal in
     * future releases of the cosmos-sdk.
     * </pre>
     */
    public void getAuthnDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAuthnDescriptorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GetChainDescriptor returns the description of the chain
     * </pre>
     */
    public void getChainDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetChainDescriptorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GetCodecDescriptor returns the descriptor of the codec of the application
     * </pre>
     */
    public void getCodecDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetCodecDescriptorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GetConfigurationDescriptor returns the descriptor for the sdk.Config of the application
     * </pre>
     */
    public void getConfigurationDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetConfigurationDescriptorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GetQueryServicesDescriptor returns the available gRPC queryable services of the application
     * </pre>
     */
    public void getQueryServicesDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetQueryServicesDescriptorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GetTxDescriptor returns information on the used transaction object and available msgs that can be used
     * </pre>
     */
    public void getTxDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTxDescriptorMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ReflectionService.
   * <pre>
   * ReflectionService defines a service for application reflection.
   * </pre>
   */
  public static final class ReflectionServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ReflectionServiceBlockingStub> {
    private ReflectionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReflectionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReflectionServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * GetAuthnDescriptor returns information on how to authenticate transactions in the application
     * NOTE: this RPC is still experimental and might be subject to breaking changes or removal in
     * future releases of the cosmos-sdk.
     * </pre>
     */
    public com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorResponse getAuthnDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAuthnDescriptorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GetChainDescriptor returns the description of the chain
     * </pre>
     */
    public com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorResponse getChainDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetChainDescriptorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GetCodecDescriptor returns the descriptor of the codec of the application
     * </pre>
     */
    public com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorResponse getCodecDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetCodecDescriptorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GetConfigurationDescriptor returns the descriptor for the sdk.Config of the application
     * </pre>
     */
    public com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorResponse getConfigurationDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetConfigurationDescriptorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GetQueryServicesDescriptor returns the available gRPC queryable services of the application
     * </pre>
     */
    public com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorResponse getQueryServicesDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetQueryServicesDescriptorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GetTxDescriptor returns information on the used transaction object and available msgs that can be used
     * </pre>
     */
    public com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorResponse getTxDescriptor(com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTxDescriptorMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ReflectionService.
   * <pre>
   * ReflectionService defines a service for application reflection.
   * </pre>
   */
  public static final class ReflectionServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ReflectionServiceFutureStub> {
    private ReflectionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReflectionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReflectionServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * GetAuthnDescriptor returns information on how to authenticate transactions in the application
     * NOTE: this RPC is still experimental and might be subject to breaking changes or removal in
     * future releases of the cosmos-sdk.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorResponse> getAuthnDescriptor(
        com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAuthnDescriptorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GetChainDescriptor returns the description of the chain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorResponse> getChainDescriptor(
        com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetChainDescriptorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GetCodecDescriptor returns the descriptor of the codec of the application
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorResponse> getCodecDescriptor(
        com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetCodecDescriptorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GetConfigurationDescriptor returns the descriptor for the sdk.Config of the application
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorResponse> getConfigurationDescriptor(
        com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetConfigurationDescriptorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GetQueryServicesDescriptor returns the available gRPC queryable services of the application
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorResponse> getQueryServicesDescriptor(
        com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetQueryServicesDescriptorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GetTxDescriptor returns information on the used transaction object and available msgs that can be used
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorResponse> getTxDescriptor(
        com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTxDescriptorMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_AUTHN_DESCRIPTOR = 0;
  private static final int METHODID_GET_CHAIN_DESCRIPTOR = 1;
  private static final int METHODID_GET_CODEC_DESCRIPTOR = 2;
  private static final int METHODID_GET_CONFIGURATION_DESCRIPTOR = 3;
  private static final int METHODID_GET_QUERY_SERVICES_DESCRIPTOR = 4;
  private static final int METHODID_GET_TX_DESCRIPTOR = 5;

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
        case METHODID_GET_AUTHN_DESCRIPTOR:
          serviceImpl.getAuthnDescriptor((com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorResponse>) responseObserver);
          break;
        case METHODID_GET_CHAIN_DESCRIPTOR:
          serviceImpl.getChainDescriptor((com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorResponse>) responseObserver);
          break;
        case METHODID_GET_CODEC_DESCRIPTOR:
          serviceImpl.getCodecDescriptor((com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorResponse>) responseObserver);
          break;
        case METHODID_GET_CONFIGURATION_DESCRIPTOR:
          serviceImpl.getConfigurationDescriptor((com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorResponse>) responseObserver);
          break;
        case METHODID_GET_QUERY_SERVICES_DESCRIPTOR:
          serviceImpl.getQueryServicesDescriptor((com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorResponse>) responseObserver);
          break;
        case METHODID_GET_TX_DESCRIPTOR:
          serviceImpl.getTxDescriptor((com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorResponse>) responseObserver);
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
          getGetAuthnDescriptorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorRequest,
              com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetAuthnDescriptorResponse>(
                service, METHODID_GET_AUTHN_DESCRIPTOR)))
        .addMethod(
          getGetChainDescriptorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorRequest,
              com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetChainDescriptorResponse>(
                service, METHODID_GET_CHAIN_DESCRIPTOR)))
        .addMethod(
          getGetCodecDescriptorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorRequest,
              com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetCodecDescriptorResponse>(
                service, METHODID_GET_CODEC_DESCRIPTOR)))
        .addMethod(
          getGetConfigurationDescriptorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorRequest,
              com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetConfigurationDescriptorResponse>(
                service, METHODID_GET_CONFIGURATION_DESCRIPTOR)))
        .addMethod(
          getGetQueryServicesDescriptorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorRequest,
              com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetQueryServicesDescriptorResponse>(
                service, METHODID_GET_QUERY_SERVICES_DESCRIPTOR)))
        .addMethod(
          getGetTxDescriptorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorRequest,
              com.cosmos.base.reflection.v2alpha1.ReflectionProto.GetTxDescriptorResponse>(
                service, METHODID_GET_TX_DESCRIPTOR)))
        .build();
  }

  private static abstract class ReflectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ReflectionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmos.base.reflection.v2alpha1.ReflectionProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ReflectionService");
    }
  }

  private static final class ReflectionServiceFileDescriptorSupplier
      extends ReflectionServiceBaseDescriptorSupplier {
    ReflectionServiceFileDescriptorSupplier() {}
  }

  private static final class ReflectionServiceMethodDescriptorSupplier
      extends ReflectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ReflectionServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ReflectionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ReflectionServiceFileDescriptorSupplier())
              .addMethod(getGetAuthnDescriptorMethod())
              .addMethod(getGetChainDescriptorMethod())
              .addMethod(getGetCodecDescriptorMethod())
              .addMethod(getGetConfigurationDescriptorMethod())
              .addMethod(getGetQueryServicesDescriptorMethod())
              .addMethod(getGetTxDescriptorMethod())
              .build();
        }
      }
    }
    return result;
  }
}
