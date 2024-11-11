package com.initia.intertx.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the intertx Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: initia/intertx/v1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "initia.intertx.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.initia.intertx.v1.TxProto.MsgRegisterAccount,
      com.initia.intertx.v1.TxProto.MsgRegisterAccountResponse> getRegisterAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterAccount",
      requestType = com.initia.intertx.v1.TxProto.MsgRegisterAccount.class,
      responseType = com.initia.intertx.v1.TxProto.MsgRegisterAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.intertx.v1.TxProto.MsgRegisterAccount,
      com.initia.intertx.v1.TxProto.MsgRegisterAccountResponse> getRegisterAccountMethod() {
    io.grpc.MethodDescriptor<com.initia.intertx.v1.TxProto.MsgRegisterAccount, com.initia.intertx.v1.TxProto.MsgRegisterAccountResponse> getRegisterAccountMethod;
    if ((getRegisterAccountMethod = MsgGrpc.getRegisterAccountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRegisterAccountMethod = MsgGrpc.getRegisterAccountMethod) == null) {
          MsgGrpc.getRegisterAccountMethod = getRegisterAccountMethod =
              io.grpc.MethodDescriptor.<com.initia.intertx.v1.TxProto.MsgRegisterAccount, com.initia.intertx.v1.TxProto.MsgRegisterAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.intertx.v1.TxProto.MsgRegisterAccount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.intertx.v1.TxProto.MsgRegisterAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RegisterAccount"))
              .build();
        }
      }
    }
    return getRegisterAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.intertx.v1.TxProto.MsgSubmitTx,
      com.initia.intertx.v1.TxProto.MsgSubmitTxResponse> getSubmitTxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitTx",
      requestType = com.initia.intertx.v1.TxProto.MsgSubmitTx.class,
      responseType = com.initia.intertx.v1.TxProto.MsgSubmitTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.intertx.v1.TxProto.MsgSubmitTx,
      com.initia.intertx.v1.TxProto.MsgSubmitTxResponse> getSubmitTxMethod() {
    io.grpc.MethodDescriptor<com.initia.intertx.v1.TxProto.MsgSubmitTx, com.initia.intertx.v1.TxProto.MsgSubmitTxResponse> getSubmitTxMethod;
    if ((getSubmitTxMethod = MsgGrpc.getSubmitTxMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSubmitTxMethod = MsgGrpc.getSubmitTxMethod) == null) {
          MsgGrpc.getSubmitTxMethod = getSubmitTxMethod =
              io.grpc.MethodDescriptor.<com.initia.intertx.v1.TxProto.MsgSubmitTx, com.initia.intertx.v1.TxProto.MsgSubmitTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubmitTx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.intertx.v1.TxProto.MsgSubmitTx.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.intertx.v1.TxProto.MsgSubmitTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SubmitTx"))
              .build();
        }
      }
    }
    return getSubmitTxMethod;
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
   * Msg defines the intertx Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Register defines a rpc handler for MsgRegisterAccount
     * </pre>
     */
    default void registerAccount(com.initia.intertx.v1.TxProto.MsgRegisterAccount request,
        io.grpc.stub.StreamObserver<com.initia.intertx.v1.TxProto.MsgRegisterAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisterAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * SubmitTx defines a rpc handler for MsgSubmitTx
     * </pre>
     */
    default void submitTx(com.initia.intertx.v1.TxProto.MsgSubmitTx request,
        io.grpc.stub.StreamObserver<com.initia.intertx.v1.TxProto.MsgSubmitTxResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubmitTxMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the intertx Msg service.
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
   * Msg defines the intertx Msg service.
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
     * Register defines a rpc handler for MsgRegisterAccount
     * </pre>
     */
    public void registerAccount(com.initia.intertx.v1.TxProto.MsgRegisterAccount request,
        io.grpc.stub.StreamObserver<com.initia.intertx.v1.TxProto.MsgRegisterAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisterAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SubmitTx defines a rpc handler for MsgSubmitTx
     * </pre>
     */
    public void submitTx(com.initia.intertx.v1.TxProto.MsgSubmitTx request,
        io.grpc.stub.StreamObserver<com.initia.intertx.v1.TxProto.MsgSubmitTxResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSubmitTxMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the intertx Msg service.
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
     * Register defines a rpc handler for MsgRegisterAccount
     * </pre>
     */
    public com.initia.intertx.v1.TxProto.MsgRegisterAccountResponse registerAccount(com.initia.intertx.v1.TxProto.MsgRegisterAccount request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisterAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SubmitTx defines a rpc handler for MsgSubmitTx
     * </pre>
     */
    public com.initia.intertx.v1.TxProto.MsgSubmitTxResponse submitTx(com.initia.intertx.v1.TxProto.MsgSubmitTx request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSubmitTxMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the intertx Msg service.
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
     * Register defines a rpc handler for MsgRegisterAccount
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.intertx.v1.TxProto.MsgRegisterAccountResponse> registerAccount(
        com.initia.intertx.v1.TxProto.MsgRegisterAccount request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisterAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SubmitTx defines a rpc handler for MsgSubmitTx
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.intertx.v1.TxProto.MsgSubmitTxResponse> submitTx(
        com.initia.intertx.v1.TxProto.MsgSubmitTx request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSubmitTxMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_ACCOUNT = 0;
  private static final int METHODID_SUBMIT_TX = 1;

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
        case METHODID_REGISTER_ACCOUNT:
          serviceImpl.registerAccount((com.initia.intertx.v1.TxProto.MsgRegisterAccount) request,
              (io.grpc.stub.StreamObserver<com.initia.intertx.v1.TxProto.MsgRegisterAccountResponse>) responseObserver);
          break;
        case METHODID_SUBMIT_TX:
          serviceImpl.submitTx((com.initia.intertx.v1.TxProto.MsgSubmitTx) request,
              (io.grpc.stub.StreamObserver<com.initia.intertx.v1.TxProto.MsgSubmitTxResponse>) responseObserver);
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
          getRegisterAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.intertx.v1.TxProto.MsgRegisterAccount,
              com.initia.intertx.v1.TxProto.MsgRegisterAccountResponse>(
                service, METHODID_REGISTER_ACCOUNT)))
        .addMethod(
          getSubmitTxMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.intertx.v1.TxProto.MsgSubmitTx,
              com.initia.intertx.v1.TxProto.MsgSubmitTxResponse>(
                service, METHODID_SUBMIT_TX)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.initia.intertx.v1.TxProto.getDescriptor();
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
              .addMethod(getRegisterAccountMethod())
              .addMethod(getSubmitTxMethod())
              .build();
        }
      }
    }
    return result;
  }
}
