package com.ibc.lightclients.wasm.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the ibc/08-wasm Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: ibc/lightclients/wasm/v1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "ibc.lightclients.wasm.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCode,
      com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCodeResponse> getStoreCodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StoreCode",
      requestType = com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCode.class,
      responseType = com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCode,
      com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCodeResponse> getStoreCodeMethod() {
    io.grpc.MethodDescriptor<com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCode, com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCodeResponse> getStoreCodeMethod;
    if ((getStoreCodeMethod = MsgGrpc.getStoreCodeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getStoreCodeMethod = MsgGrpc.getStoreCodeMethod) == null) {
          MsgGrpc.getStoreCodeMethod = getStoreCodeMethod =
              io.grpc.MethodDescriptor.<com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCode, com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StoreCode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCode.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("StoreCode"))
              .build();
        }
      }
    }
    return getStoreCodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksum,
      com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksumResponse> getRemoveChecksumMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveChecksum",
      requestType = com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksum.class,
      responseType = com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksumResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksum,
      com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksumResponse> getRemoveChecksumMethod() {
    io.grpc.MethodDescriptor<com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksum, com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksumResponse> getRemoveChecksumMethod;
    if ((getRemoveChecksumMethod = MsgGrpc.getRemoveChecksumMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRemoveChecksumMethod = MsgGrpc.getRemoveChecksumMethod) == null) {
          MsgGrpc.getRemoveChecksumMethod = getRemoveChecksumMethod =
              io.grpc.MethodDescriptor.<com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksum, com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksumResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveChecksum"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksum.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksumResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RemoveChecksum"))
              .build();
        }
      }
    }
    return getRemoveChecksumMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContract,
      com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContractResponse> getMigrateContractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MigrateContract",
      requestType = com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContract.class,
      responseType = com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContractResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContract,
      com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContractResponse> getMigrateContractMethod() {
    io.grpc.MethodDescriptor<com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContract, com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContractResponse> getMigrateContractMethod;
    if ((getMigrateContractMethod = MsgGrpc.getMigrateContractMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMigrateContractMethod = MsgGrpc.getMigrateContractMethod) == null) {
          MsgGrpc.getMigrateContractMethod = getMigrateContractMethod =
              io.grpc.MethodDescriptor.<com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContract, com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContractResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MigrateContract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContractResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("MigrateContract"))
              .build();
        }
      }
    }
    return getMigrateContractMethod;
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
   * Msg defines the ibc/08-wasm Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * StoreCode defines a rpc handler method for MsgStoreCode.
     * </pre>
     */
    default void storeCode(com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCode request,
        io.grpc.stub.StreamObserver<com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCodeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStoreCodeMethod(), responseObserver);
    }

    /**
     * <pre>
     * RemoveChecksum defines a rpc handler method for MsgRemoveChecksum.
     * </pre>
     */
    default void removeChecksum(com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksum request,
        io.grpc.stub.StreamObserver<com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksumResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRemoveChecksumMethod(), responseObserver);
    }

    /**
     * <pre>
     * MigrateContract defines a rpc handler method for MsgMigrateContract.
     * </pre>
     */
    default void migrateContract(com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContract request,
        io.grpc.stub.StreamObserver<com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContractResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMigrateContractMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the ibc/08-wasm Msg service.
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
   * Msg defines the ibc/08-wasm Msg service.
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
     * StoreCode defines a rpc handler method for MsgStoreCode.
     * </pre>
     */
    public void storeCode(com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCode request,
        io.grpc.stub.StreamObserver<com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCodeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStoreCodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RemoveChecksum defines a rpc handler method for MsgRemoveChecksum.
     * </pre>
     */
    public void removeChecksum(com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksum request,
        io.grpc.stub.StreamObserver<com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksumResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRemoveChecksumMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MigrateContract defines a rpc handler method for MsgMigrateContract.
     * </pre>
     */
    public void migrateContract(com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContract request,
        io.grpc.stub.StreamObserver<com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContractResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMigrateContractMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the ibc/08-wasm Msg service.
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
     * StoreCode defines a rpc handler method for MsgStoreCode.
     * </pre>
     */
    public com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCodeResponse storeCode(com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCode request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStoreCodeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RemoveChecksum defines a rpc handler method for MsgRemoveChecksum.
     * </pre>
     */
    public com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksumResponse removeChecksum(com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksum request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRemoveChecksumMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MigrateContract defines a rpc handler method for MsgMigrateContract.
     * </pre>
     */
    public com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContractResponse migrateContract(com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContract request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMigrateContractMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the ibc/08-wasm Msg service.
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
     * StoreCode defines a rpc handler method for MsgStoreCode.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCodeResponse> storeCode(
        com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCode request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStoreCodeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RemoveChecksum defines a rpc handler method for MsgRemoveChecksum.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksumResponse> removeChecksum(
        com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksum request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRemoveChecksumMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MigrateContract defines a rpc handler method for MsgMigrateContract.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContractResponse> migrateContract(
        com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContract request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMigrateContractMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_STORE_CODE = 0;
  private static final int METHODID_REMOVE_CHECKSUM = 1;
  private static final int METHODID_MIGRATE_CONTRACT = 2;

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
        case METHODID_STORE_CODE:
          serviceImpl.storeCode((com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCode) request,
              (io.grpc.stub.StreamObserver<com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCodeResponse>) responseObserver);
          break;
        case METHODID_REMOVE_CHECKSUM:
          serviceImpl.removeChecksum((com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksum) request,
              (io.grpc.stub.StreamObserver<com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksumResponse>) responseObserver);
          break;
        case METHODID_MIGRATE_CONTRACT:
          serviceImpl.migrateContract((com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContract) request,
              (io.grpc.stub.StreamObserver<com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContractResponse>) responseObserver);
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
          getStoreCodeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCode,
              com.ibc.lightclients.wasm.v1.TxProto.MsgStoreCodeResponse>(
                service, METHODID_STORE_CODE)))
        .addMethod(
          getRemoveChecksumMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksum,
              com.ibc.lightclients.wasm.v1.TxProto.MsgRemoveChecksumResponse>(
                service, METHODID_REMOVE_CHECKSUM)))
        .addMethod(
          getMigrateContractMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContract,
              com.ibc.lightclients.wasm.v1.TxProto.MsgMigrateContractResponse>(
                service, METHODID_MIGRATE_CONTRACT)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ibc.lightclients.wasm.v1.TxProto.getDescriptor();
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
              .addMethod(getStoreCodeMethod())
              .addMethod(getRemoveChecksumMethod())
              .addMethod(getMigrateContractMethod())
              .build();
        }
      }
    }
    return result;
  }
}
