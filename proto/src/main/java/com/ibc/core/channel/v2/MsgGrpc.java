package com.ibc.core.channel.v2;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the ibc/channel/v2 Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: ibc/core/channel/v2/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "ibc.core.channel.v2.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ibc.core.channel.v2.TxProto.MsgSendPacket,
      com.ibc.core.channel.v2.TxProto.MsgSendPacketResponse> getSendPacketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendPacket",
      requestType = com.ibc.core.channel.v2.TxProto.MsgSendPacket.class,
      responseType = com.ibc.core.channel.v2.TxProto.MsgSendPacketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.channel.v2.TxProto.MsgSendPacket,
      com.ibc.core.channel.v2.TxProto.MsgSendPacketResponse> getSendPacketMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.channel.v2.TxProto.MsgSendPacket, com.ibc.core.channel.v2.TxProto.MsgSendPacketResponse> getSendPacketMethod;
    if ((getSendPacketMethod = MsgGrpc.getSendPacketMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSendPacketMethod = MsgGrpc.getSendPacketMethod) == null) {
          MsgGrpc.getSendPacketMethod = getSendPacketMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.channel.v2.TxProto.MsgSendPacket, com.ibc.core.channel.v2.TxProto.MsgSendPacketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendPacket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.TxProto.MsgSendPacket.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.TxProto.MsgSendPacketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SendPacket"))
              .build();
        }
      }
    }
    return getSendPacketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.channel.v2.TxProto.MsgRecvPacket,
      com.ibc.core.channel.v2.TxProto.MsgRecvPacketResponse> getRecvPacketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RecvPacket",
      requestType = com.ibc.core.channel.v2.TxProto.MsgRecvPacket.class,
      responseType = com.ibc.core.channel.v2.TxProto.MsgRecvPacketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.channel.v2.TxProto.MsgRecvPacket,
      com.ibc.core.channel.v2.TxProto.MsgRecvPacketResponse> getRecvPacketMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.channel.v2.TxProto.MsgRecvPacket, com.ibc.core.channel.v2.TxProto.MsgRecvPacketResponse> getRecvPacketMethod;
    if ((getRecvPacketMethod = MsgGrpc.getRecvPacketMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRecvPacketMethod = MsgGrpc.getRecvPacketMethod) == null) {
          MsgGrpc.getRecvPacketMethod = getRecvPacketMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.channel.v2.TxProto.MsgRecvPacket, com.ibc.core.channel.v2.TxProto.MsgRecvPacketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RecvPacket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.TxProto.MsgRecvPacket.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.TxProto.MsgRecvPacketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RecvPacket"))
              .build();
        }
      }
    }
    return getRecvPacketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.channel.v2.TxProto.MsgTimeout,
      com.ibc.core.channel.v2.TxProto.MsgTimeoutResponse> getTimeoutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Timeout",
      requestType = com.ibc.core.channel.v2.TxProto.MsgTimeout.class,
      responseType = com.ibc.core.channel.v2.TxProto.MsgTimeoutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.channel.v2.TxProto.MsgTimeout,
      com.ibc.core.channel.v2.TxProto.MsgTimeoutResponse> getTimeoutMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.channel.v2.TxProto.MsgTimeout, com.ibc.core.channel.v2.TxProto.MsgTimeoutResponse> getTimeoutMethod;
    if ((getTimeoutMethod = MsgGrpc.getTimeoutMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getTimeoutMethod = MsgGrpc.getTimeoutMethod) == null) {
          MsgGrpc.getTimeoutMethod = getTimeoutMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.channel.v2.TxProto.MsgTimeout, com.ibc.core.channel.v2.TxProto.MsgTimeoutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Timeout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.TxProto.MsgTimeout.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.TxProto.MsgTimeoutResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Timeout"))
              .build();
        }
      }
    }
    return getTimeoutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.channel.v2.TxProto.MsgAcknowledgement,
      com.ibc.core.channel.v2.TxProto.MsgAcknowledgementResponse> getAcknowledgementMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Acknowledgement",
      requestType = com.ibc.core.channel.v2.TxProto.MsgAcknowledgement.class,
      responseType = com.ibc.core.channel.v2.TxProto.MsgAcknowledgementResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.channel.v2.TxProto.MsgAcknowledgement,
      com.ibc.core.channel.v2.TxProto.MsgAcknowledgementResponse> getAcknowledgementMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.channel.v2.TxProto.MsgAcknowledgement, com.ibc.core.channel.v2.TxProto.MsgAcknowledgementResponse> getAcknowledgementMethod;
    if ((getAcknowledgementMethod = MsgGrpc.getAcknowledgementMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAcknowledgementMethod = MsgGrpc.getAcknowledgementMethod) == null) {
          MsgGrpc.getAcknowledgementMethod = getAcknowledgementMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.channel.v2.TxProto.MsgAcknowledgement, com.ibc.core.channel.v2.TxProto.MsgAcknowledgementResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Acknowledgement"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.TxProto.MsgAcknowledgement.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.TxProto.MsgAcknowledgementResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Acknowledgement"))
              .build();
        }
      }
    }
    return getAcknowledgementMethod;
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
   * Msg defines the ibc/channel/v2 Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * SendPacket defines a rpc handler method for MsgSendPacket.
     * </pre>
     */
    default void sendPacket(com.ibc.core.channel.v2.TxProto.MsgSendPacket request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.TxProto.MsgSendPacketResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendPacketMethod(), responseObserver);
    }

    /**
     * <pre>
     * RecvPacket defines a rpc handler method for MsgRecvPacket.
     * </pre>
     */
    default void recvPacket(com.ibc.core.channel.v2.TxProto.MsgRecvPacket request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.TxProto.MsgRecvPacketResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRecvPacketMethod(), responseObserver);
    }

    /**
     * <pre>
     * Timeout defines a rpc handler method for MsgTimeout.
     * </pre>
     */
    default void timeout(com.ibc.core.channel.v2.TxProto.MsgTimeout request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.TxProto.MsgTimeoutResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTimeoutMethod(), responseObserver);
    }

    /**
     * <pre>
     * Acknowledgement defines a rpc handler method for MsgAcknowledgement.
     * </pre>
     */
    default void acknowledgement(com.ibc.core.channel.v2.TxProto.MsgAcknowledgement request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.TxProto.MsgAcknowledgementResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAcknowledgementMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the ibc/channel/v2 Msg service.
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
   * Msg defines the ibc/channel/v2 Msg service.
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
     * SendPacket defines a rpc handler method for MsgSendPacket.
     * </pre>
     */
    public void sendPacket(com.ibc.core.channel.v2.TxProto.MsgSendPacket request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.TxProto.MsgSendPacketResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendPacketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RecvPacket defines a rpc handler method for MsgRecvPacket.
     * </pre>
     */
    public void recvPacket(com.ibc.core.channel.v2.TxProto.MsgRecvPacket request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.TxProto.MsgRecvPacketResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRecvPacketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Timeout defines a rpc handler method for MsgTimeout.
     * </pre>
     */
    public void timeout(com.ibc.core.channel.v2.TxProto.MsgTimeout request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.TxProto.MsgTimeoutResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTimeoutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Acknowledgement defines a rpc handler method for MsgAcknowledgement.
     * </pre>
     */
    public void acknowledgement(com.ibc.core.channel.v2.TxProto.MsgAcknowledgement request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.TxProto.MsgAcknowledgementResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAcknowledgementMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the ibc/channel/v2 Msg service.
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
     * SendPacket defines a rpc handler method for MsgSendPacket.
     * </pre>
     */
    public com.ibc.core.channel.v2.TxProto.MsgSendPacketResponse sendPacket(com.ibc.core.channel.v2.TxProto.MsgSendPacket request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendPacketMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RecvPacket defines a rpc handler method for MsgRecvPacket.
     * </pre>
     */
    public com.ibc.core.channel.v2.TxProto.MsgRecvPacketResponse recvPacket(com.ibc.core.channel.v2.TxProto.MsgRecvPacket request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRecvPacketMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Timeout defines a rpc handler method for MsgTimeout.
     * </pre>
     */
    public com.ibc.core.channel.v2.TxProto.MsgTimeoutResponse timeout(com.ibc.core.channel.v2.TxProto.MsgTimeout request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTimeoutMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Acknowledgement defines a rpc handler method for MsgAcknowledgement.
     * </pre>
     */
    public com.ibc.core.channel.v2.TxProto.MsgAcknowledgementResponse acknowledgement(com.ibc.core.channel.v2.TxProto.MsgAcknowledgement request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAcknowledgementMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the ibc/channel/v2 Msg service.
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
     * SendPacket defines a rpc handler method for MsgSendPacket.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.channel.v2.TxProto.MsgSendPacketResponse> sendPacket(
        com.ibc.core.channel.v2.TxProto.MsgSendPacket request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendPacketMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RecvPacket defines a rpc handler method for MsgRecvPacket.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.channel.v2.TxProto.MsgRecvPacketResponse> recvPacket(
        com.ibc.core.channel.v2.TxProto.MsgRecvPacket request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRecvPacketMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Timeout defines a rpc handler method for MsgTimeout.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.channel.v2.TxProto.MsgTimeoutResponse> timeout(
        com.ibc.core.channel.v2.TxProto.MsgTimeout request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTimeoutMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Acknowledgement defines a rpc handler method for MsgAcknowledgement.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.channel.v2.TxProto.MsgAcknowledgementResponse> acknowledgement(
        com.ibc.core.channel.v2.TxProto.MsgAcknowledgement request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAcknowledgementMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_PACKET = 0;
  private static final int METHODID_RECV_PACKET = 1;
  private static final int METHODID_TIMEOUT = 2;
  private static final int METHODID_ACKNOWLEDGEMENT = 3;

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
        case METHODID_SEND_PACKET:
          serviceImpl.sendPacket((com.ibc.core.channel.v2.TxProto.MsgSendPacket) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.TxProto.MsgSendPacketResponse>) responseObserver);
          break;
        case METHODID_RECV_PACKET:
          serviceImpl.recvPacket((com.ibc.core.channel.v2.TxProto.MsgRecvPacket) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.TxProto.MsgRecvPacketResponse>) responseObserver);
          break;
        case METHODID_TIMEOUT:
          serviceImpl.timeout((com.ibc.core.channel.v2.TxProto.MsgTimeout) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.TxProto.MsgTimeoutResponse>) responseObserver);
          break;
        case METHODID_ACKNOWLEDGEMENT:
          serviceImpl.acknowledgement((com.ibc.core.channel.v2.TxProto.MsgAcknowledgement) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.TxProto.MsgAcknowledgementResponse>) responseObserver);
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
          getSendPacketMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.channel.v2.TxProto.MsgSendPacket,
              com.ibc.core.channel.v2.TxProto.MsgSendPacketResponse>(
                service, METHODID_SEND_PACKET)))
        .addMethod(
          getRecvPacketMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.channel.v2.TxProto.MsgRecvPacket,
              com.ibc.core.channel.v2.TxProto.MsgRecvPacketResponse>(
                service, METHODID_RECV_PACKET)))
        .addMethod(
          getTimeoutMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.channel.v2.TxProto.MsgTimeout,
              com.ibc.core.channel.v2.TxProto.MsgTimeoutResponse>(
                service, METHODID_TIMEOUT)))
        .addMethod(
          getAcknowledgementMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.channel.v2.TxProto.MsgAcknowledgement,
              com.ibc.core.channel.v2.TxProto.MsgAcknowledgementResponse>(
                service, METHODID_ACKNOWLEDGEMENT)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ibc.core.channel.v2.TxProto.getDescriptor();
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
              .addMethod(getSendPacketMethod())
              .addMethod(getRecvPacketMethod())
              .addMethod(getTimeoutMethod())
              .addMethod(getAcknowledgementMethod())
              .build();
        }
      }
    }
    return result;
  }
}
