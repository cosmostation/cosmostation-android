package com.ibc.core.client.v2;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the ibc/client/v2 Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: ibc/core/client/v2/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "ibc.core.client.v2.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v2.TxProto.MsgRegisterCounterparty,
      com.ibc.core.client.v2.TxProto.MsgRegisterCounterpartyResponse> getRegisterCounterpartyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterCounterparty",
      requestType = com.ibc.core.client.v2.TxProto.MsgRegisterCounterparty.class,
      responseType = com.ibc.core.client.v2.TxProto.MsgRegisterCounterpartyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v2.TxProto.MsgRegisterCounterparty,
      com.ibc.core.client.v2.TxProto.MsgRegisterCounterpartyResponse> getRegisterCounterpartyMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v2.TxProto.MsgRegisterCounterparty, com.ibc.core.client.v2.TxProto.MsgRegisterCounterpartyResponse> getRegisterCounterpartyMethod;
    if ((getRegisterCounterpartyMethod = MsgGrpc.getRegisterCounterpartyMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRegisterCounterpartyMethod = MsgGrpc.getRegisterCounterpartyMethod) == null) {
          MsgGrpc.getRegisterCounterpartyMethod = getRegisterCounterpartyMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v2.TxProto.MsgRegisterCounterparty, com.ibc.core.client.v2.TxProto.MsgRegisterCounterpartyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterCounterparty"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v2.TxProto.MsgRegisterCounterparty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v2.TxProto.MsgRegisterCounterpartyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RegisterCounterparty"))
              .build();
        }
      }
    }
    return getRegisterCounterpartyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v2.TxProto.MsgUpdateClientConfig,
      com.ibc.core.client.v2.TxProto.MsgUpdateClientConfigResponse> getUpdateClientConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateClientConfig",
      requestType = com.ibc.core.client.v2.TxProto.MsgUpdateClientConfig.class,
      responseType = com.ibc.core.client.v2.TxProto.MsgUpdateClientConfigResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v2.TxProto.MsgUpdateClientConfig,
      com.ibc.core.client.v2.TxProto.MsgUpdateClientConfigResponse> getUpdateClientConfigMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v2.TxProto.MsgUpdateClientConfig, com.ibc.core.client.v2.TxProto.MsgUpdateClientConfigResponse> getUpdateClientConfigMethod;
    if ((getUpdateClientConfigMethod = MsgGrpc.getUpdateClientConfigMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateClientConfigMethod = MsgGrpc.getUpdateClientConfigMethod) == null) {
          MsgGrpc.getUpdateClientConfigMethod = getUpdateClientConfigMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v2.TxProto.MsgUpdateClientConfig, com.ibc.core.client.v2.TxProto.MsgUpdateClientConfigResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateClientConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v2.TxProto.MsgUpdateClientConfig.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v2.TxProto.MsgUpdateClientConfigResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateClientConfig"))
              .build();
        }
      }
    }
    return getUpdateClientConfigMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MsgStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgStub>() {
        @java.lang.Override
        public MsgStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgStub(channel, callOptions);
        }
      };
    return MsgStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MsgBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgBlockingStub>() {
        @java.lang.Override
        public MsgBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgBlockingStub(channel, callOptions);
        }
      };
    return MsgBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MsgFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgFutureStub>() {
        @java.lang.Override
        public MsgFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgFutureStub(channel, callOptions);
        }
      };
    return MsgFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Msg defines the ibc/client/v2 Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * RegisterCounterparty defines a rpc handler method for MsgRegisterCounterparty.
     * </pre>
     */
    default void registerCounterparty(com.ibc.core.client.v2.TxProto.MsgRegisterCounterparty request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v2.TxProto.MsgRegisterCounterpartyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisterCounterpartyMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateClientConfig defines a rpc handler method for MsgUpdateClientConfig.
     * </pre>
     */
    default void updateClientConfig(com.ibc.core.client.v2.TxProto.MsgUpdateClientConfig request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v2.TxProto.MsgUpdateClientConfigResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateClientConfigMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the ibc/client/v2 Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MsgGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the ibc/client/v2 Msg service.
   * </pre>
   */
  public static final class MsgStub
      extends io.grpc.stub.AbstractAsyncStub<MsgStub> {
    private MsgStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgStub(channel, callOptions);
    }

    /**
     * <pre>
     * RegisterCounterparty defines a rpc handler method for MsgRegisterCounterparty.
     * </pre>
     */
    public void registerCounterparty(com.ibc.core.client.v2.TxProto.MsgRegisterCounterparty request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v2.TxProto.MsgRegisterCounterpartyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisterCounterpartyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateClientConfig defines a rpc handler method for MsgUpdateClientConfig.
     * </pre>
     */
    public void updateClientConfig(com.ibc.core.client.v2.TxProto.MsgUpdateClientConfig request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v2.TxProto.MsgUpdateClientConfigResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateClientConfigMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the ibc/client/v2 Msg service.
   * </pre>
   */
  public static final class MsgBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MsgBlockingStub> {
    private MsgBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * RegisterCounterparty defines a rpc handler method for MsgRegisterCounterparty.
     * </pre>
     */
    public com.ibc.core.client.v2.TxProto.MsgRegisterCounterpartyResponse registerCounterparty(com.ibc.core.client.v2.TxProto.MsgRegisterCounterparty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisterCounterpartyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateClientConfig defines a rpc handler method for MsgUpdateClientConfig.
     * </pre>
     */
    public com.ibc.core.client.v2.TxProto.MsgUpdateClientConfigResponse updateClientConfig(com.ibc.core.client.v2.TxProto.MsgUpdateClientConfig request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateClientConfigMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the ibc/client/v2 Msg service.
   * </pre>
   */
  public static final class MsgFutureStub
      extends io.grpc.stub.AbstractFutureStub<MsgFutureStub> {
    private MsgFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * RegisterCounterparty defines a rpc handler method for MsgRegisterCounterparty.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v2.TxProto.MsgRegisterCounterpartyResponse> registerCounterparty(
        com.ibc.core.client.v2.TxProto.MsgRegisterCounterparty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisterCounterpartyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateClientConfig defines a rpc handler method for MsgUpdateClientConfig.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v2.TxProto.MsgUpdateClientConfigResponse> updateClientConfig(
        com.ibc.core.client.v2.TxProto.MsgUpdateClientConfig request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateClientConfigMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_COUNTERPARTY = 0;
  private static final int METHODID_UPDATE_CLIENT_CONFIG = 1;

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
        case METHODID_REGISTER_COUNTERPARTY:
          serviceImpl.registerCounterparty((com.ibc.core.client.v2.TxProto.MsgRegisterCounterparty) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v2.TxProto.MsgRegisterCounterpartyResponse>) responseObserver);
          break;
        case METHODID_UPDATE_CLIENT_CONFIG:
          serviceImpl.updateClientConfig((com.ibc.core.client.v2.TxProto.MsgUpdateClientConfig) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v2.TxProto.MsgUpdateClientConfigResponse>) responseObserver);
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
          getRegisterCounterpartyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v2.TxProto.MsgRegisterCounterparty,
              com.ibc.core.client.v2.TxProto.MsgRegisterCounterpartyResponse>(
                service, METHODID_REGISTER_COUNTERPARTY)))
        .addMethod(
          getUpdateClientConfigMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v2.TxProto.MsgUpdateClientConfig,
              com.ibc.core.client.v2.TxProto.MsgUpdateClientConfigResponse>(
                service, METHODID_UPDATE_CLIENT_CONFIG)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ibc.core.client.v2.TxProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Msg");
    }
  }

  private static final class MsgFileDescriptorSupplier
      extends MsgBaseDescriptorSupplier {
    MsgFileDescriptorSupplier() {}
  }

  private static final class MsgMethodDescriptorSupplier
      extends MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MsgMethodDescriptorSupplier(String methodName) {
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
      synchronized (MsgGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MsgFileDescriptorSupplier())
              .addMethod(getRegisterCounterpartyMethod())
              .addMethod(getUpdateClientConfigMethod())
              .build();
        }
      }
    }
    return result;
  }
}
