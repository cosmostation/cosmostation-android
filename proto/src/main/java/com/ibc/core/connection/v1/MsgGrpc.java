package com.ibc.core.connection.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the ibc/connection Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: ibc/core/connection/v1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "ibc.core.connection.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInit,
      com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInitResponse> getConnectionOpenInitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConnectionOpenInit",
      requestType = com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInit.class,
      responseType = com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInit,
      com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInitResponse> getConnectionOpenInitMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInit, com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInitResponse> getConnectionOpenInitMethod;
    if ((getConnectionOpenInitMethod = MsgGrpc.getConnectionOpenInitMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConnectionOpenInitMethod = MsgGrpc.getConnectionOpenInitMethod) == null) {
          MsgGrpc.getConnectionOpenInitMethod = getConnectionOpenInitMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInit, com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConnectionOpenInit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConnectionOpenInit"))
              .build();
        }
      }
    }
    return getConnectionOpenInitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTry,
      com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTryResponse> getConnectionOpenTryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConnectionOpenTry",
      requestType = com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTry.class,
      responseType = com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTry,
      com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTryResponse> getConnectionOpenTryMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTry, com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTryResponse> getConnectionOpenTryMethod;
    if ((getConnectionOpenTryMethod = MsgGrpc.getConnectionOpenTryMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConnectionOpenTryMethod = MsgGrpc.getConnectionOpenTryMethod) == null) {
          MsgGrpc.getConnectionOpenTryMethod = getConnectionOpenTryMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTry, com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConnectionOpenTry"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTry.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConnectionOpenTry"))
              .build();
        }
      }
    }
    return getConnectionOpenTryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAck,
      com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAckResponse> getConnectionOpenAckMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConnectionOpenAck",
      requestType = com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAck.class,
      responseType = com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAckResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAck,
      com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAckResponse> getConnectionOpenAckMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAck, com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAckResponse> getConnectionOpenAckMethod;
    if ((getConnectionOpenAckMethod = MsgGrpc.getConnectionOpenAckMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConnectionOpenAckMethod = MsgGrpc.getConnectionOpenAckMethod) == null) {
          MsgGrpc.getConnectionOpenAckMethod = getConnectionOpenAckMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAck, com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAckResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConnectionOpenAck"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAck.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAckResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConnectionOpenAck"))
              .build();
        }
      }
    }
    return getConnectionOpenAckMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirm,
      com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirmResponse> getConnectionOpenConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConnectionOpenConfirm",
      requestType = com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirm.class,
      responseType = com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirmResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirm,
      com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirmResponse> getConnectionOpenConfirmMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirm, com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirmResponse> getConnectionOpenConfirmMethod;
    if ((getConnectionOpenConfirmMethod = MsgGrpc.getConnectionOpenConfirmMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConnectionOpenConfirmMethod = MsgGrpc.getConnectionOpenConfirmMethod) == null) {
          MsgGrpc.getConnectionOpenConfirmMethod = getConnectionOpenConfirmMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirm, com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirmResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConnectionOpenConfirm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirm.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirmResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConnectionOpenConfirm"))
              .build();
        }
      }
    }
    return getConnectionOpenConfirmMethod;
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
   * Msg defines the ibc/connection Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * ConnectionOpenInit defines a rpc handler method for MsgConnectionOpenInit.
     * </pre>
     */
    default void connectionOpenInit(com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInit request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInitResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConnectionOpenInitMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConnectionOpenTry defines a rpc handler method for MsgConnectionOpenTry.
     * </pre>
     */
    default void connectionOpenTry(com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTry request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTryResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConnectionOpenTryMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConnectionOpenAck defines a rpc handler method for MsgConnectionOpenAck.
     * </pre>
     */
    default void connectionOpenAck(com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAck request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAckResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConnectionOpenAckMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConnectionOpenConfirm defines a rpc handler method for
     * MsgConnectionOpenConfirm.
     * </pre>
     */
    default void connectionOpenConfirm(com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirm request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirmResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConnectionOpenConfirmMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the ibc/connection Msg service.
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
   * Msg defines the ibc/connection Msg service.
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
     * ConnectionOpenInit defines a rpc handler method for MsgConnectionOpenInit.
     * </pre>
     */
    public void connectionOpenInit(com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInit request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInitResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConnectionOpenInitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConnectionOpenTry defines a rpc handler method for MsgConnectionOpenTry.
     * </pre>
     */
    public void connectionOpenTry(com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTry request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTryResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConnectionOpenTryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConnectionOpenAck defines a rpc handler method for MsgConnectionOpenAck.
     * </pre>
     */
    public void connectionOpenAck(com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAck request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAckResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConnectionOpenAckMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConnectionOpenConfirm defines a rpc handler method for
     * MsgConnectionOpenConfirm.
     * </pre>
     */
    public void connectionOpenConfirm(com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirm request,
        io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirmResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConnectionOpenConfirmMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the ibc/connection Msg service.
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
     * ConnectionOpenInit defines a rpc handler method for MsgConnectionOpenInit.
     * </pre>
     */
    public com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInitResponse connectionOpenInit(com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInit request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConnectionOpenInitMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConnectionOpenTry defines a rpc handler method for MsgConnectionOpenTry.
     * </pre>
     */
    public com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTryResponse connectionOpenTry(com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTry request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConnectionOpenTryMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConnectionOpenAck defines a rpc handler method for MsgConnectionOpenAck.
     * </pre>
     */
    public com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAckResponse connectionOpenAck(com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAck request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConnectionOpenAckMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConnectionOpenConfirm defines a rpc handler method for
     * MsgConnectionOpenConfirm.
     * </pre>
     */
    public com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirmResponse connectionOpenConfirm(com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirm request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConnectionOpenConfirmMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the ibc/connection Msg service.
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
     * ConnectionOpenInit defines a rpc handler method for MsgConnectionOpenInit.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInitResponse> connectionOpenInit(
        com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInit request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConnectionOpenInitMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConnectionOpenTry defines a rpc handler method for MsgConnectionOpenTry.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTryResponse> connectionOpenTry(
        com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTry request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConnectionOpenTryMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConnectionOpenAck defines a rpc handler method for MsgConnectionOpenAck.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAckResponse> connectionOpenAck(
        com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAck request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConnectionOpenAckMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConnectionOpenConfirm defines a rpc handler method for
     * MsgConnectionOpenConfirm.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirmResponse> connectionOpenConfirm(
        com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirm request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConnectionOpenConfirmMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CONNECTION_OPEN_INIT = 0;
  private static final int METHODID_CONNECTION_OPEN_TRY = 1;
  private static final int METHODID_CONNECTION_OPEN_ACK = 2;
  private static final int METHODID_CONNECTION_OPEN_CONFIRM = 3;

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
        case METHODID_CONNECTION_OPEN_INIT:
          serviceImpl.connectionOpenInit((com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInit) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInitResponse>) responseObserver);
          break;
        case METHODID_CONNECTION_OPEN_TRY:
          serviceImpl.connectionOpenTry((com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTry) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTryResponse>) responseObserver);
          break;
        case METHODID_CONNECTION_OPEN_ACK:
          serviceImpl.connectionOpenAck((com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAck) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAckResponse>) responseObserver);
          break;
        case METHODID_CONNECTION_OPEN_CONFIRM:
          serviceImpl.connectionOpenConfirm((com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirm) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirmResponse>) responseObserver);
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
          getConnectionOpenInitMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInit,
              com.ibc.core.connection.v1.TxProto.MsgConnectionOpenInitResponse>(
                service, METHODID_CONNECTION_OPEN_INIT)))
        .addMethod(
          getConnectionOpenTryMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTry,
              com.ibc.core.connection.v1.TxProto.MsgConnectionOpenTryResponse>(
                service, METHODID_CONNECTION_OPEN_TRY)))
        .addMethod(
          getConnectionOpenAckMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAck,
              com.ibc.core.connection.v1.TxProto.MsgConnectionOpenAckResponse>(
                service, METHODID_CONNECTION_OPEN_ACK)))
        .addMethod(
          getConnectionOpenConfirmMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirm,
              com.ibc.core.connection.v1.TxProto.MsgConnectionOpenConfirmResponse>(
                service, METHODID_CONNECTION_OPEN_CONFIRM)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ibc.core.connection.v1.TxProto.getDescriptor();
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
              .addMethod(getConnectionOpenInitMethod())
              .addMethod(getConnectionOpenTryMethod())
              .addMethod(getConnectionOpenAckMethod())
              .addMethod(getConnectionOpenConfirmMethod())
              .build();
        }
      }
    }
    return result;
  }
}
