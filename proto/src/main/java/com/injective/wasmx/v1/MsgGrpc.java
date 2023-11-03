package com.injective.wasmx.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the wasmx Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: injective/wasmx/v1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "injective.wasmx.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgUpdateContract,
      com.injective.wasmx.v1.TxProto.MsgUpdateContractResponse> getUpdateRegistryContractParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateRegistryContractParams",
      requestType = com.injective.wasmx.v1.TxProto.MsgUpdateContract.class,
      responseType = com.injective.wasmx.v1.TxProto.MsgUpdateContractResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgUpdateContract,
      com.injective.wasmx.v1.TxProto.MsgUpdateContractResponse> getUpdateRegistryContractParamsMethod() {
    io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgUpdateContract, com.injective.wasmx.v1.TxProto.MsgUpdateContractResponse> getUpdateRegistryContractParamsMethod;
    if ((getUpdateRegistryContractParamsMethod = MsgGrpc.getUpdateRegistryContractParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateRegistryContractParamsMethod = MsgGrpc.getUpdateRegistryContractParamsMethod) == null) {
          MsgGrpc.getUpdateRegistryContractParamsMethod = getUpdateRegistryContractParamsMethod =
              io.grpc.MethodDescriptor.<com.injective.wasmx.v1.TxProto.MsgUpdateContract, com.injective.wasmx.v1.TxProto.MsgUpdateContractResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateRegistryContractParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.wasmx.v1.TxProto.MsgUpdateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.wasmx.v1.TxProto.MsgUpdateContractResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateRegistryContractParams"))
              .build();
        }
      }
    }
    return getUpdateRegistryContractParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgActivateContract,
      com.injective.wasmx.v1.TxProto.MsgActivateContractResponse> getActivateRegistryContractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ActivateRegistryContract",
      requestType = com.injective.wasmx.v1.TxProto.MsgActivateContract.class,
      responseType = com.injective.wasmx.v1.TxProto.MsgActivateContractResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgActivateContract,
      com.injective.wasmx.v1.TxProto.MsgActivateContractResponse> getActivateRegistryContractMethod() {
    io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgActivateContract, com.injective.wasmx.v1.TxProto.MsgActivateContractResponse> getActivateRegistryContractMethod;
    if ((getActivateRegistryContractMethod = MsgGrpc.getActivateRegistryContractMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getActivateRegistryContractMethod = MsgGrpc.getActivateRegistryContractMethod) == null) {
          MsgGrpc.getActivateRegistryContractMethod = getActivateRegistryContractMethod =
              io.grpc.MethodDescriptor.<com.injective.wasmx.v1.TxProto.MsgActivateContract, com.injective.wasmx.v1.TxProto.MsgActivateContractResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ActivateRegistryContract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.wasmx.v1.TxProto.MsgActivateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.wasmx.v1.TxProto.MsgActivateContractResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ActivateRegistryContract"))
              .build();
        }
      }
    }
    return getActivateRegistryContractMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgDeactivateContract,
      com.injective.wasmx.v1.TxProto.MsgDeactivateContractResponse> getDeactivateRegistryContractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeactivateRegistryContract",
      requestType = com.injective.wasmx.v1.TxProto.MsgDeactivateContract.class,
      responseType = com.injective.wasmx.v1.TxProto.MsgDeactivateContractResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgDeactivateContract,
      com.injective.wasmx.v1.TxProto.MsgDeactivateContractResponse> getDeactivateRegistryContractMethod() {
    io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgDeactivateContract, com.injective.wasmx.v1.TxProto.MsgDeactivateContractResponse> getDeactivateRegistryContractMethod;
    if ((getDeactivateRegistryContractMethod = MsgGrpc.getDeactivateRegistryContractMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeactivateRegistryContractMethod = MsgGrpc.getDeactivateRegistryContractMethod) == null) {
          MsgGrpc.getDeactivateRegistryContractMethod = getDeactivateRegistryContractMethod =
              io.grpc.MethodDescriptor.<com.injective.wasmx.v1.TxProto.MsgDeactivateContract, com.injective.wasmx.v1.TxProto.MsgDeactivateContractResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeactivateRegistryContract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.wasmx.v1.TxProto.MsgDeactivateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.wasmx.v1.TxProto.MsgDeactivateContractResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeactivateRegistryContract"))
              .build();
        }
      }
    }
    return getDeactivateRegistryContractMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgExecuteContractCompat,
      com.injective.wasmx.v1.TxProto.MsgExecuteContractCompatResponse> getExecuteContractCompatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExecuteContractCompat",
      requestType = com.injective.wasmx.v1.TxProto.MsgExecuteContractCompat.class,
      responseType = com.injective.wasmx.v1.TxProto.MsgExecuteContractCompatResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgExecuteContractCompat,
      com.injective.wasmx.v1.TxProto.MsgExecuteContractCompatResponse> getExecuteContractCompatMethod() {
    io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgExecuteContractCompat, com.injective.wasmx.v1.TxProto.MsgExecuteContractCompatResponse> getExecuteContractCompatMethod;
    if ((getExecuteContractCompatMethod = MsgGrpc.getExecuteContractCompatMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getExecuteContractCompatMethod = MsgGrpc.getExecuteContractCompatMethod) == null) {
          MsgGrpc.getExecuteContractCompatMethod = getExecuteContractCompatMethod =
              io.grpc.MethodDescriptor.<com.injective.wasmx.v1.TxProto.MsgExecuteContractCompat, com.injective.wasmx.v1.TxProto.MsgExecuteContractCompatResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExecuteContractCompat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.wasmx.v1.TxProto.MsgExecuteContractCompat.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.wasmx.v1.TxProto.MsgExecuteContractCompatResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ExecuteContractCompat"))
              .build();
        }
      }
    }
    return getExecuteContractCompatMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgUpdateParams,
      com.injective.wasmx.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateParams",
      requestType = com.injective.wasmx.v1.TxProto.MsgUpdateParams.class,
      responseType = com.injective.wasmx.v1.TxProto.MsgUpdateParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgUpdateParams,
      com.injective.wasmx.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod() {
    io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgUpdateParams, com.injective.wasmx.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;
    if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
          MsgGrpc.getUpdateParamsMethod = getUpdateParamsMethod =
              io.grpc.MethodDescriptor.<com.injective.wasmx.v1.TxProto.MsgUpdateParams, com.injective.wasmx.v1.TxProto.MsgUpdateParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.wasmx.v1.TxProto.MsgUpdateParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.wasmx.v1.TxProto.MsgUpdateParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateParams"))
              .build();
        }
      }
    }
    return getUpdateParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgRegisterContract,
      com.injective.wasmx.v1.TxProto.MsgRegisterContractResponse> getRegisterContractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterContract",
      requestType = com.injective.wasmx.v1.TxProto.MsgRegisterContract.class,
      responseType = com.injective.wasmx.v1.TxProto.MsgRegisterContractResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgRegisterContract,
      com.injective.wasmx.v1.TxProto.MsgRegisterContractResponse> getRegisterContractMethod() {
    io.grpc.MethodDescriptor<com.injective.wasmx.v1.TxProto.MsgRegisterContract, com.injective.wasmx.v1.TxProto.MsgRegisterContractResponse> getRegisterContractMethod;
    if ((getRegisterContractMethod = MsgGrpc.getRegisterContractMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRegisterContractMethod = MsgGrpc.getRegisterContractMethod) == null) {
          MsgGrpc.getRegisterContractMethod = getRegisterContractMethod =
              io.grpc.MethodDescriptor.<com.injective.wasmx.v1.TxProto.MsgRegisterContract, com.injective.wasmx.v1.TxProto.MsgRegisterContractResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterContract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.wasmx.v1.TxProto.MsgRegisterContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.wasmx.v1.TxProto.MsgRegisterContractResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RegisterContract"))
              .build();
        }
      }
    }
    return getRegisterContractMethod;
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
   * Msg defines the wasmx Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void updateRegistryContractParams(com.injective.wasmx.v1.TxProto.MsgUpdateContract request,
        io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgUpdateContractResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateRegistryContractParamsMethod(), responseObserver);
    }

    /**
     */
    default void activateRegistryContract(com.injective.wasmx.v1.TxProto.MsgActivateContract request,
        io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgActivateContractResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getActivateRegistryContractMethod(), responseObserver);
    }

    /**
     */
    default void deactivateRegistryContract(com.injective.wasmx.v1.TxProto.MsgDeactivateContract request,
        io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgDeactivateContractResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeactivateRegistryContractMethod(), responseObserver);
    }

    /**
     */
    default void executeContractCompat(com.injective.wasmx.v1.TxProto.MsgExecuteContractCompat request,
        io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgExecuteContractCompatResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExecuteContractCompatMethod(), responseObserver);
    }

    /**
     */
    default void updateParams(com.injective.wasmx.v1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateParamsMethod(), responseObserver);
    }

    /**
     */
    default void registerContract(com.injective.wasmx.v1.TxProto.MsgRegisterContract request,
        io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgRegisterContractResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisterContractMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the wasmx Msg service.
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
   * Msg defines the wasmx Msg service.
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
     */
    public void updateRegistryContractParams(com.injective.wasmx.v1.TxProto.MsgUpdateContract request,
        io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgUpdateContractResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateRegistryContractParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void activateRegistryContract(com.injective.wasmx.v1.TxProto.MsgActivateContract request,
        io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgActivateContractResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getActivateRegistryContractMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deactivateRegistryContract(com.injective.wasmx.v1.TxProto.MsgDeactivateContract request,
        io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgDeactivateContractResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeactivateRegistryContractMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void executeContractCompat(com.injective.wasmx.v1.TxProto.MsgExecuteContractCompat request,
        io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgExecuteContractCompatResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExecuteContractCompatMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateParams(com.injective.wasmx.v1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void registerContract(com.injective.wasmx.v1.TxProto.MsgRegisterContract request,
        io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgRegisterContractResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisterContractMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the wasmx Msg service.
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
     */
    public com.injective.wasmx.v1.TxProto.MsgUpdateContractResponse updateRegistryContractParams(com.injective.wasmx.v1.TxProto.MsgUpdateContract request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateRegistryContractParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.wasmx.v1.TxProto.MsgActivateContractResponse activateRegistryContract(com.injective.wasmx.v1.TxProto.MsgActivateContract request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getActivateRegistryContractMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.wasmx.v1.TxProto.MsgDeactivateContractResponse deactivateRegistryContract(com.injective.wasmx.v1.TxProto.MsgDeactivateContract request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeactivateRegistryContractMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.wasmx.v1.TxProto.MsgExecuteContractCompatResponse executeContractCompat(com.injective.wasmx.v1.TxProto.MsgExecuteContractCompat request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExecuteContractCompatMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.wasmx.v1.TxProto.MsgUpdateParamsResponse updateParams(com.injective.wasmx.v1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.wasmx.v1.TxProto.MsgRegisterContractResponse registerContract(com.injective.wasmx.v1.TxProto.MsgRegisterContract request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisterContractMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the wasmx Msg service.
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
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.wasmx.v1.TxProto.MsgUpdateContractResponse> updateRegistryContractParams(
        com.injective.wasmx.v1.TxProto.MsgUpdateContract request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateRegistryContractParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.wasmx.v1.TxProto.MsgActivateContractResponse> activateRegistryContract(
        com.injective.wasmx.v1.TxProto.MsgActivateContract request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getActivateRegistryContractMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.wasmx.v1.TxProto.MsgDeactivateContractResponse> deactivateRegistryContract(
        com.injective.wasmx.v1.TxProto.MsgDeactivateContract request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeactivateRegistryContractMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.wasmx.v1.TxProto.MsgExecuteContractCompatResponse> executeContractCompat(
        com.injective.wasmx.v1.TxProto.MsgExecuteContractCompat request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExecuteContractCompatMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.wasmx.v1.TxProto.MsgUpdateParamsResponse> updateParams(
        com.injective.wasmx.v1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.wasmx.v1.TxProto.MsgRegisterContractResponse> registerContract(
        com.injective.wasmx.v1.TxProto.MsgRegisterContract request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisterContractMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_UPDATE_REGISTRY_CONTRACT_PARAMS = 0;
  private static final int METHODID_ACTIVATE_REGISTRY_CONTRACT = 1;
  private static final int METHODID_DEACTIVATE_REGISTRY_CONTRACT = 2;
  private static final int METHODID_EXECUTE_CONTRACT_COMPAT = 3;
  private static final int METHODID_UPDATE_PARAMS = 4;
  private static final int METHODID_REGISTER_CONTRACT = 5;

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
        case METHODID_UPDATE_REGISTRY_CONTRACT_PARAMS:
          serviceImpl.updateRegistryContractParams((com.injective.wasmx.v1.TxProto.MsgUpdateContract) request,
              (io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgUpdateContractResponse>) responseObserver);
          break;
        case METHODID_ACTIVATE_REGISTRY_CONTRACT:
          serviceImpl.activateRegistryContract((com.injective.wasmx.v1.TxProto.MsgActivateContract) request,
              (io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgActivateContractResponse>) responseObserver);
          break;
        case METHODID_DEACTIVATE_REGISTRY_CONTRACT:
          serviceImpl.deactivateRegistryContract((com.injective.wasmx.v1.TxProto.MsgDeactivateContract) request,
              (io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgDeactivateContractResponse>) responseObserver);
          break;
        case METHODID_EXECUTE_CONTRACT_COMPAT:
          serviceImpl.executeContractCompat((com.injective.wasmx.v1.TxProto.MsgExecuteContractCompat) request,
              (io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgExecuteContractCompatResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PARAMS:
          serviceImpl.updateParams((com.injective.wasmx.v1.TxProto.MsgUpdateParams) request,
              (io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgUpdateParamsResponse>) responseObserver);
          break;
        case METHODID_REGISTER_CONTRACT:
          serviceImpl.registerContract((com.injective.wasmx.v1.TxProto.MsgRegisterContract) request,
              (io.grpc.stub.StreamObserver<com.injective.wasmx.v1.TxProto.MsgRegisterContractResponse>) responseObserver);
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
          getUpdateRegistryContractParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.wasmx.v1.TxProto.MsgUpdateContract,
              com.injective.wasmx.v1.TxProto.MsgUpdateContractResponse>(
                service, METHODID_UPDATE_REGISTRY_CONTRACT_PARAMS)))
        .addMethod(
          getActivateRegistryContractMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.wasmx.v1.TxProto.MsgActivateContract,
              com.injective.wasmx.v1.TxProto.MsgActivateContractResponse>(
                service, METHODID_ACTIVATE_REGISTRY_CONTRACT)))
        .addMethod(
          getDeactivateRegistryContractMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.wasmx.v1.TxProto.MsgDeactivateContract,
              com.injective.wasmx.v1.TxProto.MsgDeactivateContractResponse>(
                service, METHODID_DEACTIVATE_REGISTRY_CONTRACT)))
        .addMethod(
          getExecuteContractCompatMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.wasmx.v1.TxProto.MsgExecuteContractCompat,
              com.injective.wasmx.v1.TxProto.MsgExecuteContractCompatResponse>(
                service, METHODID_EXECUTE_CONTRACT_COMPAT)))
        .addMethod(
          getUpdateParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.wasmx.v1.TxProto.MsgUpdateParams,
              com.injective.wasmx.v1.TxProto.MsgUpdateParamsResponse>(
                service, METHODID_UPDATE_PARAMS)))
        .addMethod(
          getRegisterContractMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.wasmx.v1.TxProto.MsgRegisterContract,
              com.injective.wasmx.v1.TxProto.MsgRegisterContractResponse>(
                service, METHODID_REGISTER_CONTRACT)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.injective.wasmx.v1.TxProto.getDescriptor();
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
              .addMethod(getUpdateRegistryContractParamsMethod())
              .addMethod(getActivateRegistryContractMethod())
              .addMethod(getDeactivateRegistryContractMethod())
              .addMethod(getExecuteContractCompatMethod())
              .addMethod(getUpdateParamsMethod())
              .addMethod(getRegisterContractMethod())
              .build();
        }
      }
    }
    return result;
  }
}
