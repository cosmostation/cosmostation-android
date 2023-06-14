package com.cosmos.vesting.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the bank Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmos/vesting/v1beta1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "cosmos.vesting.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccount,
      com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccountResponse> getCreateVestingAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateVestingAccount",
      requestType = com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccount.class,
      responseType = com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccount,
      com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccountResponse> getCreateVestingAccountMethod() {
    io.grpc.MethodDescriptor<com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccount, com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccountResponse> getCreateVestingAccountMethod;
    if ((getCreateVestingAccountMethod = MsgGrpc.getCreateVestingAccountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateVestingAccountMethod = MsgGrpc.getCreateVestingAccountMethod) == null) {
          MsgGrpc.getCreateVestingAccountMethod = getCreateVestingAccountMethod =
              io.grpc.MethodDescriptor.<com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccount, com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateVestingAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateVestingAccount"))
              .build();
        }
      }
    }
    return getCreateVestingAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccount,
      com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccountResponse> getCreatePermanentLockedAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePermanentLockedAccount",
      requestType = com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccount.class,
      responseType = com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccount,
      com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccountResponse> getCreatePermanentLockedAccountMethod() {
    io.grpc.MethodDescriptor<com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccount, com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccountResponse> getCreatePermanentLockedAccountMethod;
    if ((getCreatePermanentLockedAccountMethod = MsgGrpc.getCreatePermanentLockedAccountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreatePermanentLockedAccountMethod = MsgGrpc.getCreatePermanentLockedAccountMethod) == null) {
          MsgGrpc.getCreatePermanentLockedAccountMethod = getCreatePermanentLockedAccountMethod =
              io.grpc.MethodDescriptor.<com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccount, com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePermanentLockedAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreatePermanentLockedAccount"))
              .build();
        }
      }
    }
    return getCreatePermanentLockedAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccount,
      com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccountResponse> getCreatePeriodicVestingAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePeriodicVestingAccount",
      requestType = com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccount.class,
      responseType = com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccount,
      com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccountResponse> getCreatePeriodicVestingAccountMethod() {
    io.grpc.MethodDescriptor<com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccount, com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccountResponse> getCreatePeriodicVestingAccountMethod;
    if ((getCreatePeriodicVestingAccountMethod = MsgGrpc.getCreatePeriodicVestingAccountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreatePeriodicVestingAccountMethod = MsgGrpc.getCreatePeriodicVestingAccountMethod) == null) {
          MsgGrpc.getCreatePeriodicVestingAccountMethod = getCreatePeriodicVestingAccountMethod =
              io.grpc.MethodDescriptor.<com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccount, com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePeriodicVestingAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreatePeriodicVestingAccount"))
              .build();
        }
      }
    }
    return getCreatePeriodicVestingAccountMethod;
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
   * Msg defines the bank Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * CreateVestingAccount defines a method that enables creating a vesting
     * account.
     * </pre>
     */
    default void createVestingAccount(com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccount request,
        io.grpc.stub.StreamObserver<com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateVestingAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreatePermanentLockedAccount defines a method that enables creating a permanent
     * locked account.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    default void createPermanentLockedAccount(com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccount request,
        io.grpc.stub.StreamObserver<com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreatePermanentLockedAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreatePeriodicVestingAccount defines a method that enables creating a
     * periodic vesting account.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    default void createPeriodicVestingAccount(com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccount request,
        io.grpc.stub.StreamObserver<com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreatePeriodicVestingAccountMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the bank Msg service.
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
   * Msg defines the bank Msg service.
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
     * CreateVestingAccount defines a method that enables creating a vesting
     * account.
     * </pre>
     */
    public void createVestingAccount(com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccount request,
        io.grpc.stub.StreamObserver<com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateVestingAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreatePermanentLockedAccount defines a method that enables creating a permanent
     * locked account.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public void createPermanentLockedAccount(com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccount request,
        io.grpc.stub.StreamObserver<com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreatePermanentLockedAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreatePeriodicVestingAccount defines a method that enables creating a
     * periodic vesting account.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public void createPeriodicVestingAccount(com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccount request,
        io.grpc.stub.StreamObserver<com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreatePeriodicVestingAccountMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the bank Msg service.
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
     * CreateVestingAccount defines a method that enables creating a vesting
     * account.
     * </pre>
     */
    public com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccountResponse createVestingAccount(com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccount request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateVestingAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreatePermanentLockedAccount defines a method that enables creating a permanent
     * locked account.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccountResponse createPermanentLockedAccount(com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccount request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreatePermanentLockedAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreatePeriodicVestingAccount defines a method that enables creating a
     * periodic vesting account.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccountResponse createPeriodicVestingAccount(com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccount request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreatePeriodicVestingAccountMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the bank Msg service.
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
     * CreateVestingAccount defines a method that enables creating a vesting
     * account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccountResponse> createVestingAccount(
        com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccount request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateVestingAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreatePermanentLockedAccount defines a method that enables creating a permanent
     * locked account.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccountResponse> createPermanentLockedAccount(
        com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccount request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreatePermanentLockedAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreatePeriodicVestingAccount defines a method that enables creating a
     * periodic vesting account.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccountResponse> createPeriodicVestingAccount(
        com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccount request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreatePeriodicVestingAccountMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_VESTING_ACCOUNT = 0;
  private static final int METHODID_CREATE_PERMANENT_LOCKED_ACCOUNT = 1;
  private static final int METHODID_CREATE_PERIODIC_VESTING_ACCOUNT = 2;

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
        case METHODID_CREATE_VESTING_ACCOUNT:
          serviceImpl.createVestingAccount((com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccount) request,
              (io.grpc.stub.StreamObserver<com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccountResponse>) responseObserver);
          break;
        case METHODID_CREATE_PERMANENT_LOCKED_ACCOUNT:
          serviceImpl.createPermanentLockedAccount((com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccount) request,
              (io.grpc.stub.StreamObserver<com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccountResponse>) responseObserver);
          break;
        case METHODID_CREATE_PERIODIC_VESTING_ACCOUNT:
          serviceImpl.createPeriodicVestingAccount((com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccount) request,
              (io.grpc.stub.StreamObserver<com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccountResponse>) responseObserver);
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
          getCreateVestingAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccount,
              com.cosmos.vesting.v1beta1.TxProto.MsgCreateVestingAccountResponse>(
                service, METHODID_CREATE_VESTING_ACCOUNT)))
        .addMethod(
          getCreatePermanentLockedAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccount,
              com.cosmos.vesting.v1beta1.TxProto.MsgCreatePermanentLockedAccountResponse>(
                service, METHODID_CREATE_PERMANENT_LOCKED_ACCOUNT)))
        .addMethod(
          getCreatePeriodicVestingAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccount,
              com.cosmos.vesting.v1beta1.TxProto.MsgCreatePeriodicVestingAccountResponse>(
                service, METHODID_CREATE_PERIODIC_VESTING_ACCOUNT)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmos.vesting.v1beta1.TxProto.getDescriptor();
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
              .addMethod(getCreateVestingAccountMethod())
              .addMethod(getCreatePermanentLockedAccountMethod())
              .addMethod(getCreatePeriodicVestingAccountMethod())
              .build();
        }
      }
    }
    return result;
  }
}
