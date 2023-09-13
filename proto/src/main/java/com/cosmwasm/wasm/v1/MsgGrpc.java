package com.cosmwasm.wasm.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the wasm Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmwasm/wasm/v1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "cosmwasm.wasm.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgStoreCode,
      com.cosmwasm.wasm.v1.TxProto.MsgStoreCodeResponse> getStoreCodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StoreCode",
      requestType = com.cosmwasm.wasm.v1.TxProto.MsgStoreCode.class,
      responseType = com.cosmwasm.wasm.v1.TxProto.MsgStoreCodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgStoreCode,
      com.cosmwasm.wasm.v1.TxProto.MsgStoreCodeResponse> getStoreCodeMethod() {
    io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgStoreCode, com.cosmwasm.wasm.v1.TxProto.MsgStoreCodeResponse> getStoreCodeMethod;
    if ((getStoreCodeMethod = MsgGrpc.getStoreCodeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getStoreCodeMethod = MsgGrpc.getStoreCodeMethod) == null) {
          MsgGrpc.getStoreCodeMethod = getStoreCodeMethod =
              io.grpc.MethodDescriptor.<com.cosmwasm.wasm.v1.TxProto.MsgStoreCode, com.cosmwasm.wasm.v1.TxProto.MsgStoreCodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StoreCode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.TxProto.MsgStoreCode.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.TxProto.MsgStoreCodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("StoreCode"))
              .build();
        }
      }
    }
    return getStoreCodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract,
      com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContractResponse> getInstantiateContractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InstantiateContract",
      requestType = com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract.class,
      responseType = com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContractResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract,
      com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContractResponse> getInstantiateContractMethod() {
    io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract, com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContractResponse> getInstantiateContractMethod;
    if ((getInstantiateContractMethod = MsgGrpc.getInstantiateContractMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getInstantiateContractMethod = MsgGrpc.getInstantiateContractMethod) == null) {
          MsgGrpc.getInstantiateContractMethod = getInstantiateContractMethod =
              io.grpc.MethodDescriptor.<com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract, com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContractResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InstantiateContract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContractResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("InstantiateContract"))
              .build();
        }
      }
    }
    return getInstantiateContractMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2,
      com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2Response> getInstantiateContract2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InstantiateContract2",
      requestType = com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2.class,
      responseType = com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2,
      com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2Response> getInstantiateContract2Method() {
    io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2, com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2Response> getInstantiateContract2Method;
    if ((getInstantiateContract2Method = MsgGrpc.getInstantiateContract2Method) == null) {
      synchronized (MsgGrpc.class) {
        if ((getInstantiateContract2Method = MsgGrpc.getInstantiateContract2Method) == null) {
          MsgGrpc.getInstantiateContract2Method = getInstantiateContract2Method =
              io.grpc.MethodDescriptor.<com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2, com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InstantiateContract2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2Response.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("InstantiateContract2"))
              .build();
        }
      }
    }
    return getInstantiateContract2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract,
      com.cosmwasm.wasm.v1.TxProto.MsgExecuteContractResponse> getExecuteContractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExecuteContract",
      requestType = com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract.class,
      responseType = com.cosmwasm.wasm.v1.TxProto.MsgExecuteContractResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract,
      com.cosmwasm.wasm.v1.TxProto.MsgExecuteContractResponse> getExecuteContractMethod() {
    io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract, com.cosmwasm.wasm.v1.TxProto.MsgExecuteContractResponse> getExecuteContractMethod;
    if ((getExecuteContractMethod = MsgGrpc.getExecuteContractMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getExecuteContractMethod = MsgGrpc.getExecuteContractMethod) == null) {
          MsgGrpc.getExecuteContractMethod = getExecuteContractMethod =
              io.grpc.MethodDescriptor.<com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract, com.cosmwasm.wasm.v1.TxProto.MsgExecuteContractResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExecuteContract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.TxProto.MsgExecuteContractResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ExecuteContract"))
              .build();
        }
      }
    }
    return getExecuteContractMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgMigrateContract,
      com.cosmwasm.wasm.v1.TxProto.MsgMigrateContractResponse> getMigrateContractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MigrateContract",
      requestType = com.cosmwasm.wasm.v1.TxProto.MsgMigrateContract.class,
      responseType = com.cosmwasm.wasm.v1.TxProto.MsgMigrateContractResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgMigrateContract,
      com.cosmwasm.wasm.v1.TxProto.MsgMigrateContractResponse> getMigrateContractMethod() {
    io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgMigrateContract, com.cosmwasm.wasm.v1.TxProto.MsgMigrateContractResponse> getMigrateContractMethod;
    if ((getMigrateContractMethod = MsgGrpc.getMigrateContractMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMigrateContractMethod = MsgGrpc.getMigrateContractMethod) == null) {
          MsgGrpc.getMigrateContractMethod = getMigrateContractMethod =
              io.grpc.MethodDescriptor.<com.cosmwasm.wasm.v1.TxProto.MsgMigrateContract, com.cosmwasm.wasm.v1.TxProto.MsgMigrateContractResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MigrateContract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.TxProto.MsgMigrateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.TxProto.MsgMigrateContractResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("MigrateContract"))
              .build();
        }
      }
    }
    return getMigrateContractMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdmin,
      com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdminResponse> getUpdateAdminMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateAdmin",
      requestType = com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdmin.class,
      responseType = com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdminResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdmin,
      com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdminResponse> getUpdateAdminMethod() {
    io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdmin, com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdminResponse> getUpdateAdminMethod;
    if ((getUpdateAdminMethod = MsgGrpc.getUpdateAdminMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateAdminMethod = MsgGrpc.getUpdateAdminMethod) == null) {
          MsgGrpc.getUpdateAdminMethod = getUpdateAdminMethod =
              io.grpc.MethodDescriptor.<com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdmin, com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdminResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateAdmin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdmin.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdminResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateAdmin"))
              .build();
        }
      }
    }
    return getUpdateAdminMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgClearAdmin,
      com.cosmwasm.wasm.v1.TxProto.MsgClearAdminResponse> getClearAdminMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClearAdmin",
      requestType = com.cosmwasm.wasm.v1.TxProto.MsgClearAdmin.class,
      responseType = com.cosmwasm.wasm.v1.TxProto.MsgClearAdminResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgClearAdmin,
      com.cosmwasm.wasm.v1.TxProto.MsgClearAdminResponse> getClearAdminMethod() {
    io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.TxProto.MsgClearAdmin, com.cosmwasm.wasm.v1.TxProto.MsgClearAdminResponse> getClearAdminMethod;
    if ((getClearAdminMethod = MsgGrpc.getClearAdminMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClearAdminMethod = MsgGrpc.getClearAdminMethod) == null) {
          MsgGrpc.getClearAdminMethod = getClearAdminMethod =
              io.grpc.MethodDescriptor.<com.cosmwasm.wasm.v1.TxProto.MsgClearAdmin, com.cosmwasm.wasm.v1.TxProto.MsgClearAdminResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClearAdmin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.TxProto.MsgClearAdmin.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.TxProto.MsgClearAdminResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ClearAdmin"))
              .build();
        }
      }
    }
    return getClearAdminMethod;
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
   * Msg defines the wasm Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * StoreCode to submit Wasm code to the system
     * </pre>
     */
    default void storeCode(com.cosmwasm.wasm.v1.TxProto.MsgStoreCode request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgStoreCodeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStoreCodeMethod(), responseObserver);
    }

    /**
     * <pre>
     *  InstantiateContract creates a new smart contract instance for the given
     *  code id.
     * </pre>
     */
    default void instantiateContract(com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContractResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInstantiateContractMethod(), responseObserver);
    }

    /**
     * <pre>
     *  InstantiateContract2 creates a new smart contract instance for the given
     *  code id with a predictable address
     * </pre>
     */
    default void instantiateContract2(com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2 request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInstantiateContract2Method(), responseObserver);
    }

    /**
     * <pre>
     * Execute submits the given message data to a smart contract
     * </pre>
     */
    default void executeContract(com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgExecuteContractResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExecuteContractMethod(), responseObserver);
    }

    /**
     * <pre>
     * Migrate runs a code upgrade/ downgrade for a smart contract
     * </pre>
     */
    default void migrateContract(com.cosmwasm.wasm.v1.TxProto.MsgMigrateContract request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgMigrateContractResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMigrateContractMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateAdmin sets a new   admin for a smart contract
     * </pre>
     */
    default void updateAdmin(com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdmin request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdminResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateAdminMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClearAdmin removes any admin stored for a smart contract
     * </pre>
     */
    default void clearAdmin(com.cosmwasm.wasm.v1.TxProto.MsgClearAdmin request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgClearAdminResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClearAdminMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the wasm Msg service.
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
   * Msg defines the wasm Msg service.
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
     * StoreCode to submit Wasm code to the system
     * </pre>
     */
    public void storeCode(com.cosmwasm.wasm.v1.TxProto.MsgStoreCode request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgStoreCodeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStoreCodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *  InstantiateContract creates a new smart contract instance for the given
     *  code id.
     * </pre>
     */
    public void instantiateContract(com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContractResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInstantiateContractMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *  InstantiateContract2 creates a new smart contract instance for the given
     *  code id with a predictable address
     * </pre>
     */
    public void instantiateContract2(com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2 request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInstantiateContract2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Execute submits the given message data to a smart contract
     * </pre>
     */
    public void executeContract(com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgExecuteContractResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExecuteContractMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Migrate runs a code upgrade/ downgrade for a smart contract
     * </pre>
     */
    public void migrateContract(com.cosmwasm.wasm.v1.TxProto.MsgMigrateContract request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgMigrateContractResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMigrateContractMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateAdmin sets a new   admin for a smart contract
     * </pre>
     */
    public void updateAdmin(com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdmin request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdminResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateAdminMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClearAdmin removes any admin stored for a smart contract
     * </pre>
     */
    public void clearAdmin(com.cosmwasm.wasm.v1.TxProto.MsgClearAdmin request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgClearAdminResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClearAdminMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the wasm Msg service.
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
     * StoreCode to submit Wasm code to the system
     * </pre>
     */
    public com.cosmwasm.wasm.v1.TxProto.MsgStoreCodeResponse storeCode(com.cosmwasm.wasm.v1.TxProto.MsgStoreCode request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStoreCodeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *  InstantiateContract creates a new smart contract instance for the given
     *  code id.
     * </pre>
     */
    public com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContractResponse instantiateContract(com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInstantiateContractMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *  InstantiateContract2 creates a new smart contract instance for the given
     *  code id with a predictable address
     * </pre>
     */
    public com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2Response instantiateContract2(com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2 request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInstantiateContract2Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Execute submits the given message data to a smart contract
     * </pre>
     */
    public com.cosmwasm.wasm.v1.TxProto.MsgExecuteContractResponse executeContract(com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExecuteContractMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Migrate runs a code upgrade/ downgrade for a smart contract
     * </pre>
     */
    public com.cosmwasm.wasm.v1.TxProto.MsgMigrateContractResponse migrateContract(com.cosmwasm.wasm.v1.TxProto.MsgMigrateContract request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMigrateContractMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateAdmin sets a new   admin for a smart contract
     * </pre>
     */
    public com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdminResponse updateAdmin(com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdmin request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateAdminMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClearAdmin removes any admin stored for a smart contract
     * </pre>
     */
    public com.cosmwasm.wasm.v1.TxProto.MsgClearAdminResponse clearAdmin(com.cosmwasm.wasm.v1.TxProto.MsgClearAdmin request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClearAdminMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the wasm Msg service.
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
     * StoreCode to submit Wasm code to the system
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmwasm.wasm.v1.TxProto.MsgStoreCodeResponse> storeCode(
        com.cosmwasm.wasm.v1.TxProto.MsgStoreCode request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStoreCodeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *  InstantiateContract creates a new smart contract instance for the given
     *  code id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContractResponse> instantiateContract(
        com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInstantiateContractMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *  InstantiateContract2 creates a new smart contract instance for the given
     *  code id with a predictable address
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2Response> instantiateContract2(
        com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2 request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInstantiateContract2Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Execute submits the given message data to a smart contract
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmwasm.wasm.v1.TxProto.MsgExecuteContractResponse> executeContract(
        com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExecuteContractMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Migrate runs a code upgrade/ downgrade for a smart contract
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmwasm.wasm.v1.TxProto.MsgMigrateContractResponse> migrateContract(
        com.cosmwasm.wasm.v1.TxProto.MsgMigrateContract request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMigrateContractMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateAdmin sets a new   admin for a smart contract
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdminResponse> updateAdmin(
        com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdmin request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateAdminMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClearAdmin removes any admin stored for a smart contract
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmwasm.wasm.v1.TxProto.MsgClearAdminResponse> clearAdmin(
        com.cosmwasm.wasm.v1.TxProto.MsgClearAdmin request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClearAdminMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_STORE_CODE = 0;
  private static final int METHODID_INSTANTIATE_CONTRACT = 1;
  private static final int METHODID_INSTANTIATE_CONTRACT2 = 2;
  private static final int METHODID_EXECUTE_CONTRACT = 3;
  private static final int METHODID_MIGRATE_CONTRACT = 4;
  private static final int METHODID_UPDATE_ADMIN = 5;
  private static final int METHODID_CLEAR_ADMIN = 6;

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
          serviceImpl.storeCode((com.cosmwasm.wasm.v1.TxProto.MsgStoreCode) request,
              (io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgStoreCodeResponse>) responseObserver);
          break;
        case METHODID_INSTANTIATE_CONTRACT:
          serviceImpl.instantiateContract((com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract) request,
              (io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContractResponse>) responseObserver);
          break;
        case METHODID_INSTANTIATE_CONTRACT2:
          serviceImpl.instantiateContract2((com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2) request,
              (io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2Response>) responseObserver);
          break;
        case METHODID_EXECUTE_CONTRACT:
          serviceImpl.executeContract((com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract) request,
              (io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgExecuteContractResponse>) responseObserver);
          break;
        case METHODID_MIGRATE_CONTRACT:
          serviceImpl.migrateContract((com.cosmwasm.wasm.v1.TxProto.MsgMigrateContract) request,
              (io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgMigrateContractResponse>) responseObserver);
          break;
        case METHODID_UPDATE_ADMIN:
          serviceImpl.updateAdmin((com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdmin) request,
              (io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdminResponse>) responseObserver);
          break;
        case METHODID_CLEAR_ADMIN:
          serviceImpl.clearAdmin((com.cosmwasm.wasm.v1.TxProto.MsgClearAdmin) request,
              (io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.TxProto.MsgClearAdminResponse>) responseObserver);
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
              com.cosmwasm.wasm.v1.TxProto.MsgStoreCode,
              com.cosmwasm.wasm.v1.TxProto.MsgStoreCodeResponse>(
                service, METHODID_STORE_CODE)))
        .addMethod(
          getInstantiateContractMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract,
              com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContractResponse>(
                service, METHODID_INSTANTIATE_CONTRACT)))
        .addMethod(
          getInstantiateContract2Method(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2,
              com.cosmwasm.wasm.v1.TxProto.MsgInstantiateContract2Response>(
                service, METHODID_INSTANTIATE_CONTRACT2)))
        .addMethod(
          getExecuteContractMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract,
              com.cosmwasm.wasm.v1.TxProto.MsgExecuteContractResponse>(
                service, METHODID_EXECUTE_CONTRACT)))
        .addMethod(
          getMigrateContractMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmwasm.wasm.v1.TxProto.MsgMigrateContract,
              com.cosmwasm.wasm.v1.TxProto.MsgMigrateContractResponse>(
                service, METHODID_MIGRATE_CONTRACT)))
        .addMethod(
          getUpdateAdminMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdmin,
              com.cosmwasm.wasm.v1.TxProto.MsgUpdateAdminResponse>(
                service, METHODID_UPDATE_ADMIN)))
        .addMethod(
          getClearAdminMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmwasm.wasm.v1.TxProto.MsgClearAdmin,
              com.cosmwasm.wasm.v1.TxProto.MsgClearAdminResponse>(
                service, METHODID_CLEAR_ADMIN)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmwasm.wasm.v1.TxProto.getDescriptor();
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
              .addMethod(getInstantiateContractMethod())
              .addMethod(getInstantiateContract2Method())
              .addMethod(getExecuteContractMethod())
              .addMethod(getMigrateContractMethod())
              .addMethod(getUpdateAdminMethod())
              .addMethod(getClearAdminMethod())
              .build();
        }
      }
    }
    return result;
  }
}
