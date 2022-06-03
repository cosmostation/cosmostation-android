package cosmwasm.wasm.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Msg defines the wasm Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: cosmwasm/wasm/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "cosmwasm.wasm.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgStoreCode,
      cosmwasm.wasm.v1.Tx.MsgStoreCodeResponse> getStoreCodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StoreCode",
      requestType = cosmwasm.wasm.v1.Tx.MsgStoreCode.class,
      responseType = cosmwasm.wasm.v1.Tx.MsgStoreCodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgStoreCode,
      cosmwasm.wasm.v1.Tx.MsgStoreCodeResponse> getStoreCodeMethod() {
    io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgStoreCode, cosmwasm.wasm.v1.Tx.MsgStoreCodeResponse> getStoreCodeMethod;
    if ((getStoreCodeMethod = MsgGrpc.getStoreCodeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getStoreCodeMethod = MsgGrpc.getStoreCodeMethod) == null) {
          MsgGrpc.getStoreCodeMethod = getStoreCodeMethod =
              io.grpc.MethodDescriptor.<cosmwasm.wasm.v1.Tx.MsgStoreCode, cosmwasm.wasm.v1.Tx.MsgStoreCodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StoreCode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmwasm.wasm.v1.Tx.MsgStoreCode.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmwasm.wasm.v1.Tx.MsgStoreCodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("StoreCode"))
              .build();
        }
      }
    }
    return getStoreCodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgInstantiateContract,
      cosmwasm.wasm.v1.Tx.MsgInstantiateContractResponse> getInstantiateContractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InstantiateContract",
      requestType = cosmwasm.wasm.v1.Tx.MsgInstantiateContract.class,
      responseType = cosmwasm.wasm.v1.Tx.MsgInstantiateContractResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgInstantiateContract,
      cosmwasm.wasm.v1.Tx.MsgInstantiateContractResponse> getInstantiateContractMethod() {
    io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgInstantiateContract, cosmwasm.wasm.v1.Tx.MsgInstantiateContractResponse> getInstantiateContractMethod;
    if ((getInstantiateContractMethod = MsgGrpc.getInstantiateContractMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getInstantiateContractMethod = MsgGrpc.getInstantiateContractMethod) == null) {
          MsgGrpc.getInstantiateContractMethod = getInstantiateContractMethod =
              io.grpc.MethodDescriptor.<cosmwasm.wasm.v1.Tx.MsgInstantiateContract, cosmwasm.wasm.v1.Tx.MsgInstantiateContractResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InstantiateContract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmwasm.wasm.v1.Tx.MsgInstantiateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmwasm.wasm.v1.Tx.MsgInstantiateContractResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("InstantiateContract"))
              .build();
        }
      }
    }
    return getInstantiateContractMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgExecuteContract,
      cosmwasm.wasm.v1.Tx.MsgExecuteContractResponse> getExecuteContractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExecuteContract",
      requestType = cosmwasm.wasm.v1.Tx.MsgExecuteContract.class,
      responseType = cosmwasm.wasm.v1.Tx.MsgExecuteContractResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgExecuteContract,
      cosmwasm.wasm.v1.Tx.MsgExecuteContractResponse> getExecuteContractMethod() {
    io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgExecuteContract, cosmwasm.wasm.v1.Tx.MsgExecuteContractResponse> getExecuteContractMethod;
    if ((getExecuteContractMethod = MsgGrpc.getExecuteContractMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getExecuteContractMethod = MsgGrpc.getExecuteContractMethod) == null) {
          MsgGrpc.getExecuteContractMethod = getExecuteContractMethod =
              io.grpc.MethodDescriptor.<cosmwasm.wasm.v1.Tx.MsgExecuteContract, cosmwasm.wasm.v1.Tx.MsgExecuteContractResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExecuteContract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmwasm.wasm.v1.Tx.MsgExecuteContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmwasm.wasm.v1.Tx.MsgExecuteContractResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ExecuteContract"))
              .build();
        }
      }
    }
    return getExecuteContractMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgMigrateContract,
      cosmwasm.wasm.v1.Tx.MsgMigrateContractResponse> getMigrateContractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MigrateContract",
      requestType = cosmwasm.wasm.v1.Tx.MsgMigrateContract.class,
      responseType = cosmwasm.wasm.v1.Tx.MsgMigrateContractResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgMigrateContract,
      cosmwasm.wasm.v1.Tx.MsgMigrateContractResponse> getMigrateContractMethod() {
    io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgMigrateContract, cosmwasm.wasm.v1.Tx.MsgMigrateContractResponse> getMigrateContractMethod;
    if ((getMigrateContractMethod = MsgGrpc.getMigrateContractMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMigrateContractMethod = MsgGrpc.getMigrateContractMethod) == null) {
          MsgGrpc.getMigrateContractMethod = getMigrateContractMethod =
              io.grpc.MethodDescriptor.<cosmwasm.wasm.v1.Tx.MsgMigrateContract, cosmwasm.wasm.v1.Tx.MsgMigrateContractResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MigrateContract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmwasm.wasm.v1.Tx.MsgMigrateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmwasm.wasm.v1.Tx.MsgMigrateContractResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("MigrateContract"))
              .build();
        }
      }
    }
    return getMigrateContractMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgUpdateAdmin,
      cosmwasm.wasm.v1.Tx.MsgUpdateAdminResponse> getUpdateAdminMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateAdmin",
      requestType = cosmwasm.wasm.v1.Tx.MsgUpdateAdmin.class,
      responseType = cosmwasm.wasm.v1.Tx.MsgUpdateAdminResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgUpdateAdmin,
      cosmwasm.wasm.v1.Tx.MsgUpdateAdminResponse> getUpdateAdminMethod() {
    io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgUpdateAdmin, cosmwasm.wasm.v1.Tx.MsgUpdateAdminResponse> getUpdateAdminMethod;
    if ((getUpdateAdminMethod = MsgGrpc.getUpdateAdminMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateAdminMethod = MsgGrpc.getUpdateAdminMethod) == null) {
          MsgGrpc.getUpdateAdminMethod = getUpdateAdminMethod =
              io.grpc.MethodDescriptor.<cosmwasm.wasm.v1.Tx.MsgUpdateAdmin, cosmwasm.wasm.v1.Tx.MsgUpdateAdminResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateAdmin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmwasm.wasm.v1.Tx.MsgUpdateAdmin.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmwasm.wasm.v1.Tx.MsgUpdateAdminResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateAdmin"))
              .build();
        }
      }
    }
    return getUpdateAdminMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgClearAdmin,
      cosmwasm.wasm.v1.Tx.MsgClearAdminResponse> getClearAdminMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClearAdmin",
      requestType = cosmwasm.wasm.v1.Tx.MsgClearAdmin.class,
      responseType = cosmwasm.wasm.v1.Tx.MsgClearAdminResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgClearAdmin,
      cosmwasm.wasm.v1.Tx.MsgClearAdminResponse> getClearAdminMethod() {
    io.grpc.MethodDescriptor<cosmwasm.wasm.v1.Tx.MsgClearAdmin, cosmwasm.wasm.v1.Tx.MsgClearAdminResponse> getClearAdminMethod;
    if ((getClearAdminMethod = MsgGrpc.getClearAdminMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClearAdminMethod = MsgGrpc.getClearAdminMethod) == null) {
          MsgGrpc.getClearAdminMethod = getClearAdminMethod =
              io.grpc.MethodDescriptor.<cosmwasm.wasm.v1.Tx.MsgClearAdmin, cosmwasm.wasm.v1.Tx.MsgClearAdminResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClearAdmin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmwasm.wasm.v1.Tx.MsgClearAdmin.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmwasm.wasm.v1.Tx.MsgClearAdminResponse.getDefaultInstance()))
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
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * StoreCode to submit Wasm code to the system
     * </pre>
     */
    public void storeCode(cosmwasm.wasm.v1.Tx.MsgStoreCode request,
        io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgStoreCodeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStoreCodeMethod(), responseObserver);
    }

    /**
     * <pre>
     *  Instantiate creates a new smart contract instance for the given code id.
     * </pre>
     */
    public void instantiateContract(cosmwasm.wasm.v1.Tx.MsgInstantiateContract request,
        io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgInstantiateContractResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInstantiateContractMethod(), responseObserver);
    }

    /**
     * <pre>
     * Execute submits the given message data to a smart contract
     * </pre>
     */
    public void executeContract(cosmwasm.wasm.v1.Tx.MsgExecuteContract request,
        io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgExecuteContractResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getExecuteContractMethod(), responseObserver);
    }

    /**
     * <pre>
     * Migrate runs a code upgrade/ downgrade for a smart contract
     * </pre>
     */
    public void migrateContract(cosmwasm.wasm.v1.Tx.MsgMigrateContract request,
        io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgMigrateContractResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMigrateContractMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateAdmin sets a new   admin for a smart contract
     * </pre>
     */
    public void updateAdmin(cosmwasm.wasm.v1.Tx.MsgUpdateAdmin request,
        io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgUpdateAdminResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateAdminMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClearAdmin removes any admin stored for a smart contract
     * </pre>
     */
    public void clearAdmin(cosmwasm.wasm.v1.Tx.MsgClearAdmin request,
        io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgClearAdminResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClearAdminMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getStoreCodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmwasm.wasm.v1.Tx.MsgStoreCode,
                cosmwasm.wasm.v1.Tx.MsgStoreCodeResponse>(
                  this, METHODID_STORE_CODE)))
          .addMethod(
            getInstantiateContractMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmwasm.wasm.v1.Tx.MsgInstantiateContract,
                cosmwasm.wasm.v1.Tx.MsgInstantiateContractResponse>(
                  this, METHODID_INSTANTIATE_CONTRACT)))
          .addMethod(
            getExecuteContractMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmwasm.wasm.v1.Tx.MsgExecuteContract,
                cosmwasm.wasm.v1.Tx.MsgExecuteContractResponse>(
                  this, METHODID_EXECUTE_CONTRACT)))
          .addMethod(
            getMigrateContractMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmwasm.wasm.v1.Tx.MsgMigrateContract,
                cosmwasm.wasm.v1.Tx.MsgMigrateContractResponse>(
                  this, METHODID_MIGRATE_CONTRACT)))
          .addMethod(
            getUpdateAdminMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmwasm.wasm.v1.Tx.MsgUpdateAdmin,
                cosmwasm.wasm.v1.Tx.MsgUpdateAdminResponse>(
                  this, METHODID_UPDATE_ADMIN)))
          .addMethod(
            getClearAdminMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmwasm.wasm.v1.Tx.MsgClearAdmin,
                cosmwasm.wasm.v1.Tx.MsgClearAdminResponse>(
                  this, METHODID_CLEAR_ADMIN)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the wasm Msg service.
   * </pre>
   */
  public static final class MsgStub extends io.grpc.stub.AbstractAsyncStub<MsgStub> {
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
    public void storeCode(cosmwasm.wasm.v1.Tx.MsgStoreCode request,
        io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgStoreCodeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStoreCodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *  Instantiate creates a new smart contract instance for the given code id.
     * </pre>
     */
    public void instantiateContract(cosmwasm.wasm.v1.Tx.MsgInstantiateContract request,
        io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgInstantiateContractResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInstantiateContractMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Execute submits the given message data to a smart contract
     * </pre>
     */
    public void executeContract(cosmwasm.wasm.v1.Tx.MsgExecuteContract request,
        io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgExecuteContractResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExecuteContractMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Migrate runs a code upgrade/ downgrade for a smart contract
     * </pre>
     */
    public void migrateContract(cosmwasm.wasm.v1.Tx.MsgMigrateContract request,
        io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgMigrateContractResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMigrateContractMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateAdmin sets a new   admin for a smart contract
     * </pre>
     */
    public void updateAdmin(cosmwasm.wasm.v1.Tx.MsgUpdateAdmin request,
        io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgUpdateAdminResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateAdminMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClearAdmin removes any admin stored for a smart contract
     * </pre>
     */
    public void clearAdmin(cosmwasm.wasm.v1.Tx.MsgClearAdmin request,
        io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgClearAdminResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClearAdminMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the wasm Msg service.
   * </pre>
   */
  public static final class MsgBlockingStub extends io.grpc.stub.AbstractBlockingStub<MsgBlockingStub> {
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
    public cosmwasm.wasm.v1.Tx.MsgStoreCodeResponse storeCode(cosmwasm.wasm.v1.Tx.MsgStoreCode request) {
      return blockingUnaryCall(
          getChannel(), getStoreCodeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *  Instantiate creates a new smart contract instance for the given code id.
     * </pre>
     */
    public cosmwasm.wasm.v1.Tx.MsgInstantiateContractResponse instantiateContract(cosmwasm.wasm.v1.Tx.MsgInstantiateContract request) {
      return blockingUnaryCall(
          getChannel(), getInstantiateContractMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Execute submits the given message data to a smart contract
     * </pre>
     */
    public cosmwasm.wasm.v1.Tx.MsgExecuteContractResponse executeContract(cosmwasm.wasm.v1.Tx.MsgExecuteContract request) {
      return blockingUnaryCall(
          getChannel(), getExecuteContractMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Migrate runs a code upgrade/ downgrade for a smart contract
     * </pre>
     */
    public cosmwasm.wasm.v1.Tx.MsgMigrateContractResponse migrateContract(cosmwasm.wasm.v1.Tx.MsgMigrateContract request) {
      return blockingUnaryCall(
          getChannel(), getMigrateContractMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateAdmin sets a new   admin for a smart contract
     * </pre>
     */
    public cosmwasm.wasm.v1.Tx.MsgUpdateAdminResponse updateAdmin(cosmwasm.wasm.v1.Tx.MsgUpdateAdmin request) {
      return blockingUnaryCall(
          getChannel(), getUpdateAdminMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClearAdmin removes any admin stored for a smart contract
     * </pre>
     */
    public cosmwasm.wasm.v1.Tx.MsgClearAdminResponse clearAdmin(cosmwasm.wasm.v1.Tx.MsgClearAdmin request) {
      return blockingUnaryCall(
          getChannel(), getClearAdminMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the wasm Msg service.
   * </pre>
   */
  public static final class MsgFutureStub extends io.grpc.stub.AbstractFutureStub<MsgFutureStub> {
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
    public com.google.common.util.concurrent.ListenableFuture<cosmwasm.wasm.v1.Tx.MsgStoreCodeResponse> storeCode(
        cosmwasm.wasm.v1.Tx.MsgStoreCode request) {
      return futureUnaryCall(
          getChannel().newCall(getStoreCodeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *  Instantiate creates a new smart contract instance for the given code id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmwasm.wasm.v1.Tx.MsgInstantiateContractResponse> instantiateContract(
        cosmwasm.wasm.v1.Tx.MsgInstantiateContract request) {
      return futureUnaryCall(
          getChannel().newCall(getInstantiateContractMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Execute submits the given message data to a smart contract
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmwasm.wasm.v1.Tx.MsgExecuteContractResponse> executeContract(
        cosmwasm.wasm.v1.Tx.MsgExecuteContract request) {
      return futureUnaryCall(
          getChannel().newCall(getExecuteContractMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Migrate runs a code upgrade/ downgrade for a smart contract
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmwasm.wasm.v1.Tx.MsgMigrateContractResponse> migrateContract(
        cosmwasm.wasm.v1.Tx.MsgMigrateContract request) {
      return futureUnaryCall(
          getChannel().newCall(getMigrateContractMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateAdmin sets a new   admin for a smart contract
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmwasm.wasm.v1.Tx.MsgUpdateAdminResponse> updateAdmin(
        cosmwasm.wasm.v1.Tx.MsgUpdateAdmin request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateAdminMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClearAdmin removes any admin stored for a smart contract
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmwasm.wasm.v1.Tx.MsgClearAdminResponse> clearAdmin(
        cosmwasm.wasm.v1.Tx.MsgClearAdmin request) {
      return futureUnaryCall(
          getChannel().newCall(getClearAdminMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_STORE_CODE = 0;
  private static final int METHODID_INSTANTIATE_CONTRACT = 1;
  private static final int METHODID_EXECUTE_CONTRACT = 2;
  private static final int METHODID_MIGRATE_CONTRACT = 3;
  private static final int METHODID_UPDATE_ADMIN = 4;
  private static final int METHODID_CLEAR_ADMIN = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MsgImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MsgImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STORE_CODE:
          serviceImpl.storeCode((cosmwasm.wasm.v1.Tx.MsgStoreCode) request,
              (io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgStoreCodeResponse>) responseObserver);
          break;
        case METHODID_INSTANTIATE_CONTRACT:
          serviceImpl.instantiateContract((cosmwasm.wasm.v1.Tx.MsgInstantiateContract) request,
              (io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgInstantiateContractResponse>) responseObserver);
          break;
        case METHODID_EXECUTE_CONTRACT:
          serviceImpl.executeContract((cosmwasm.wasm.v1.Tx.MsgExecuteContract) request,
              (io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgExecuteContractResponse>) responseObserver);
          break;
        case METHODID_MIGRATE_CONTRACT:
          serviceImpl.migrateContract((cosmwasm.wasm.v1.Tx.MsgMigrateContract) request,
              (io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgMigrateContractResponse>) responseObserver);
          break;
        case METHODID_UPDATE_ADMIN:
          serviceImpl.updateAdmin((cosmwasm.wasm.v1.Tx.MsgUpdateAdmin) request,
              (io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgUpdateAdminResponse>) responseObserver);
          break;
        case METHODID_CLEAR_ADMIN:
          serviceImpl.clearAdmin((cosmwasm.wasm.v1.Tx.MsgClearAdmin) request,
              (io.grpc.stub.StreamObserver<cosmwasm.wasm.v1.Tx.MsgClearAdminResponse>) responseObserver);
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

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return cosmwasm.wasm.v1.Tx.getDescriptor();
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
