package com.babylon.btccheckpoint.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: babylon/btccheckpoint/v1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "babylon.btccheckpoint.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProof,
      com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProofResponse> getInsertBTCSpvProofMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InsertBTCSpvProof",
      requestType = com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProof.class,
      responseType = com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProofResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProof,
      com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProofResponse> getInsertBTCSpvProofMethod() {
    io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProof, com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProofResponse> getInsertBTCSpvProofMethod;
    if ((getInsertBTCSpvProofMethod = MsgGrpc.getInsertBTCSpvProofMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getInsertBTCSpvProofMethod = MsgGrpc.getInsertBTCSpvProofMethod) == null) {
          MsgGrpc.getInsertBTCSpvProofMethod = getInsertBTCSpvProofMethod =
              io.grpc.MethodDescriptor.<com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProof, com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProofResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InsertBTCSpvProof"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProof.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProofResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("InsertBTCSpvProof"))
              .build();
        }
      }
    }
    return getInsertBTCSpvProofMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParams,
      com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateParams",
      requestType = com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParams.class,
      responseType = com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParams,
      com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod() {
    io.grpc.MethodDescriptor<com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParams, com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;
    if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
          MsgGrpc.getUpdateParamsMethod = getUpdateParamsMethod =
              io.grpc.MethodDescriptor.<com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParams, com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateParams"))
              .build();
        }
      }
    }
    return getUpdateParamsMethod;
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
   * Msg defines the Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * InsertBTCSpvProof tries to insert a new checkpoint into the store.
     * </pre>
     */
    default void insertBTCSpvProof(com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProof request,
        io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProofResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInsertBTCSpvProofMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateParams updates the btccheckpoint module parameters.
     * </pre>
     */
    default void updateParams(com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateParamsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the Msg service.
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
   * Msg defines the Msg service.
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
     * InsertBTCSpvProof tries to insert a new checkpoint into the store.
     * </pre>
     */
    public void insertBTCSpvProof(com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProof request,
        io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProofResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInsertBTCSpvProofMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateParams updates the btccheckpoint module parameters.
     * </pre>
     */
    public void updateParams(com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the Msg service.
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
     * InsertBTCSpvProof tries to insert a new checkpoint into the store.
     * </pre>
     */
    public com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProofResponse insertBTCSpvProof(com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProof request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInsertBTCSpvProofMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateParams updates the btccheckpoint module parameters.
     * </pre>
     */
    public com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParamsResponse updateParams(com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the Msg service.
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
     * InsertBTCSpvProof tries to insert a new checkpoint into the store.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProofResponse> insertBTCSpvProof(
        com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProof request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInsertBTCSpvProofMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateParams updates the btccheckpoint module parameters.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParamsResponse> updateParams(
        com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INSERT_BTCSPV_PROOF = 0;
  private static final int METHODID_UPDATE_PARAMS = 1;

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
        case METHODID_INSERT_BTCSPV_PROOF:
          serviceImpl.insertBTCSpvProof((com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProof) request,
              (io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProofResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PARAMS:
          serviceImpl.updateParams((com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParams) request,
              (io.grpc.stub.StreamObserver<com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParamsResponse>) responseObserver);
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
          getInsertBTCSpvProofMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProof,
              com.babylon.btccheckpoint.v1.TxProto.MsgInsertBTCSpvProofResponse>(
                service, METHODID_INSERT_BTCSPV_PROOF)))
        .addMethod(
          getUpdateParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParams,
              com.babylon.btccheckpoint.v1.TxProto.MsgUpdateParamsResponse>(
                service, METHODID_UPDATE_PARAMS)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.babylon.btccheckpoint.v1.TxProto.getDescriptor();
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
              .addMethod(getInsertBTCSpvProofMethod())
              .addMethod(getUpdateParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
