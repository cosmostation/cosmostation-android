package com.stratos.evm.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the evm Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: stratos/evm/v1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "stratos.evm.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.stratos.evm.v1.TxProto.MsgEthereumTx,
      com.stratos.evm.v1.TxProto.MsgEthereumTxResponse> getEthereumTxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EthereumTx",
      requestType = com.stratos.evm.v1.TxProto.MsgEthereumTx.class,
      responseType = com.stratos.evm.v1.TxProto.MsgEthereumTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stratos.evm.v1.TxProto.MsgEthereumTx,
      com.stratos.evm.v1.TxProto.MsgEthereumTxResponse> getEthereumTxMethod() {
    io.grpc.MethodDescriptor<com.stratos.evm.v1.TxProto.MsgEthereumTx, com.stratos.evm.v1.TxProto.MsgEthereumTxResponse> getEthereumTxMethod;
    if ((getEthereumTxMethod = MsgGrpc.getEthereumTxMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEthereumTxMethod = MsgGrpc.getEthereumTxMethod) == null) {
          MsgGrpc.getEthereumTxMethod = getEthereumTxMethod =
              io.grpc.MethodDescriptor.<com.stratos.evm.v1.TxProto.MsgEthereumTx, com.stratos.evm.v1.TxProto.MsgEthereumTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EthereumTx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stratos.evm.v1.TxProto.MsgEthereumTx.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stratos.evm.v1.TxProto.MsgEthereumTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EthereumTx"))
              .build();
        }
      }
    }
    return getEthereumTxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stratos.evm.v1.TxProto.MsgUpdateParams,
      com.stratos.evm.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateParams",
      requestType = com.stratos.evm.v1.TxProto.MsgUpdateParams.class,
      responseType = com.stratos.evm.v1.TxProto.MsgUpdateParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stratos.evm.v1.TxProto.MsgUpdateParams,
      com.stratos.evm.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod() {
    io.grpc.MethodDescriptor<com.stratos.evm.v1.TxProto.MsgUpdateParams, com.stratos.evm.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;
    if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
          MsgGrpc.getUpdateParamsMethod = getUpdateParamsMethod =
              io.grpc.MethodDescriptor.<com.stratos.evm.v1.TxProto.MsgUpdateParams, com.stratos.evm.v1.TxProto.MsgUpdateParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stratos.evm.v1.TxProto.MsgUpdateParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stratos.evm.v1.TxProto.MsgUpdateParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateParams"))
              .build();
        }
      }
    }
    return getUpdateParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposal,
      com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposalResponse> getUpdateImplmentationProposalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateImplmentationProposal",
      requestType = com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposal.class,
      responseType = com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposal,
      com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposalResponse> getUpdateImplmentationProposalMethod() {
    io.grpc.MethodDescriptor<com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposal, com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposalResponse> getUpdateImplmentationProposalMethod;
    if ((getUpdateImplmentationProposalMethod = MsgGrpc.getUpdateImplmentationProposalMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateImplmentationProposalMethod = MsgGrpc.getUpdateImplmentationProposalMethod) == null) {
          MsgGrpc.getUpdateImplmentationProposalMethod = getUpdateImplmentationProposalMethod =
              io.grpc.MethodDescriptor.<com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposal, com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateImplmentationProposal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposal.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateImplmentationProposal"))
              .build();
        }
      }
    }
    return getUpdateImplmentationProposalMethod;
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
   * Msg defines the evm Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * EthereumTx defines a method submitting Ethereum transactions.
     * </pre>
     */
    default void ethereumTx(com.stratos.evm.v1.TxProto.MsgEthereumTx request,
        io.grpc.stub.StreamObserver<com.stratos.evm.v1.TxProto.MsgEthereumTxResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEthereumTxMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateParams defined a governance operation for updating the x/evm module parameters.
     * The authority is hard-coded to the Cosmos SDK x/gov module account
     * </pre>
     */
    default void updateParams(com.stratos.evm.v1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.stratos.evm.v1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateImplmentationProposal defines a method for the new implemntation update for proposal.
     * </pre>
     */
    default void updateImplmentationProposal(com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposal request,
        io.grpc.stub.StreamObserver<com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposalResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateImplmentationProposalMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the evm Msg service.
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
   * Msg defines the evm Msg service.
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
     * EthereumTx defines a method submitting Ethereum transactions.
     * </pre>
     */
    public void ethereumTx(com.stratos.evm.v1.TxProto.MsgEthereumTx request,
        io.grpc.stub.StreamObserver<com.stratos.evm.v1.TxProto.MsgEthereumTxResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEthereumTxMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateParams defined a governance operation for updating the x/evm module parameters.
     * The authority is hard-coded to the Cosmos SDK x/gov module account
     * </pre>
     */
    public void updateParams(com.stratos.evm.v1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.stratos.evm.v1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateImplmentationProposal defines a method for the new implemntation update for proposal.
     * </pre>
     */
    public void updateImplmentationProposal(com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposal request,
        io.grpc.stub.StreamObserver<com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposalResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateImplmentationProposalMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the evm Msg service.
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
     * EthereumTx defines a method submitting Ethereum transactions.
     * </pre>
     */
    public com.stratos.evm.v1.TxProto.MsgEthereumTxResponse ethereumTx(com.stratos.evm.v1.TxProto.MsgEthereumTx request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEthereumTxMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateParams defined a governance operation for updating the x/evm module parameters.
     * The authority is hard-coded to the Cosmos SDK x/gov module account
     * </pre>
     */
    public com.stratos.evm.v1.TxProto.MsgUpdateParamsResponse updateParams(com.stratos.evm.v1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateImplmentationProposal defines a method for the new implemntation update for proposal.
     * </pre>
     */
    public com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposalResponse updateImplmentationProposal(com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposal request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateImplmentationProposalMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the evm Msg service.
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
     * EthereumTx defines a method submitting Ethereum transactions.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stratos.evm.v1.TxProto.MsgEthereumTxResponse> ethereumTx(
        com.stratos.evm.v1.TxProto.MsgEthereumTx request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEthereumTxMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateParams defined a governance operation for updating the x/evm module parameters.
     * The authority is hard-coded to the Cosmos SDK x/gov module account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stratos.evm.v1.TxProto.MsgUpdateParamsResponse> updateParams(
        com.stratos.evm.v1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateImplmentationProposal defines a method for the new implemntation update for proposal.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposalResponse> updateImplmentationProposal(
        com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposal request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateImplmentationProposalMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ETHEREUM_TX = 0;
  private static final int METHODID_UPDATE_PARAMS = 1;
  private static final int METHODID_UPDATE_IMPLMENTATION_PROPOSAL = 2;

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
        case METHODID_ETHEREUM_TX:
          serviceImpl.ethereumTx((com.stratos.evm.v1.TxProto.MsgEthereumTx) request,
              (io.grpc.stub.StreamObserver<com.stratos.evm.v1.TxProto.MsgEthereumTxResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PARAMS:
          serviceImpl.updateParams((com.stratos.evm.v1.TxProto.MsgUpdateParams) request,
              (io.grpc.stub.StreamObserver<com.stratos.evm.v1.TxProto.MsgUpdateParamsResponse>) responseObserver);
          break;
        case METHODID_UPDATE_IMPLMENTATION_PROPOSAL:
          serviceImpl.updateImplmentationProposal((com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposal) request,
              (io.grpc.stub.StreamObserver<com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposalResponse>) responseObserver);
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
          getEthereumTxMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stratos.evm.v1.TxProto.MsgEthereumTx,
              com.stratos.evm.v1.TxProto.MsgEthereumTxResponse>(
                service, METHODID_ETHEREUM_TX)))
        .addMethod(
          getUpdateParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stratos.evm.v1.TxProto.MsgUpdateParams,
              com.stratos.evm.v1.TxProto.MsgUpdateParamsResponse>(
                service, METHODID_UPDATE_PARAMS)))
        .addMethod(
          getUpdateImplmentationProposalMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposal,
              com.stratos.evm.v1.TxProto.MsgUpdateImplmentationProposalResponse>(
                service, METHODID_UPDATE_IMPLMENTATION_PROPOSAL)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.stratos.evm.v1.TxProto.getDescriptor();
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
              .addMethod(getEthereumTxMethod())
              .addMethod(getUpdateParamsMethod())
              .addMethod(getUpdateImplmentationProposalMethod())
              .build();
        }
      }
    }
    return result;
  }
}
