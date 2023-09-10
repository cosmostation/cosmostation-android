package com.stride.claim;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: stride/claim/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "stride.claim.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.stride.claim.TxProto.MsgSetAirdropAllocations,
      com.stride.claim.TxProto.MsgSetAirdropAllocationsResponse> getSetAirdropAllocationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetAirdropAllocations",
      requestType = com.stride.claim.TxProto.MsgSetAirdropAllocations.class,
      responseType = com.stride.claim.TxProto.MsgSetAirdropAllocationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.claim.TxProto.MsgSetAirdropAllocations,
      com.stride.claim.TxProto.MsgSetAirdropAllocationsResponse> getSetAirdropAllocationsMethod() {
    io.grpc.MethodDescriptor<com.stride.claim.TxProto.MsgSetAirdropAllocations, com.stride.claim.TxProto.MsgSetAirdropAllocationsResponse> getSetAirdropAllocationsMethod;
    if ((getSetAirdropAllocationsMethod = MsgGrpc.getSetAirdropAllocationsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetAirdropAllocationsMethod = MsgGrpc.getSetAirdropAllocationsMethod) == null) {
          MsgGrpc.getSetAirdropAllocationsMethod = getSetAirdropAllocationsMethod =
              io.grpc.MethodDescriptor.<com.stride.claim.TxProto.MsgSetAirdropAllocations, com.stride.claim.TxProto.MsgSetAirdropAllocationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetAirdropAllocations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.TxProto.MsgSetAirdropAllocations.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.TxProto.MsgSetAirdropAllocationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetAirdropAllocations"))
              .build();
        }
      }
    }
    return getSetAirdropAllocationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.claim.TxProto.MsgClaimFreeAmount,
      com.stride.claim.TxProto.MsgClaimFreeAmountResponse> getClaimFreeAmountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimFreeAmount",
      requestType = com.stride.claim.TxProto.MsgClaimFreeAmount.class,
      responseType = com.stride.claim.TxProto.MsgClaimFreeAmountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.claim.TxProto.MsgClaimFreeAmount,
      com.stride.claim.TxProto.MsgClaimFreeAmountResponse> getClaimFreeAmountMethod() {
    io.grpc.MethodDescriptor<com.stride.claim.TxProto.MsgClaimFreeAmount, com.stride.claim.TxProto.MsgClaimFreeAmountResponse> getClaimFreeAmountMethod;
    if ((getClaimFreeAmountMethod = MsgGrpc.getClaimFreeAmountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClaimFreeAmountMethod = MsgGrpc.getClaimFreeAmountMethod) == null) {
          MsgGrpc.getClaimFreeAmountMethod = getClaimFreeAmountMethod =
              io.grpc.MethodDescriptor.<com.stride.claim.TxProto.MsgClaimFreeAmount, com.stride.claim.TxProto.MsgClaimFreeAmountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimFreeAmount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.TxProto.MsgClaimFreeAmount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.TxProto.MsgClaimFreeAmountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ClaimFreeAmount"))
              .build();
        }
      }
    }
    return getClaimFreeAmountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.claim.TxProto.MsgCreateAirdrop,
      com.stride.claim.TxProto.MsgCreateAirdropResponse> getCreateAirdropMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateAirdrop",
      requestType = com.stride.claim.TxProto.MsgCreateAirdrop.class,
      responseType = com.stride.claim.TxProto.MsgCreateAirdropResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.claim.TxProto.MsgCreateAirdrop,
      com.stride.claim.TxProto.MsgCreateAirdropResponse> getCreateAirdropMethod() {
    io.grpc.MethodDescriptor<com.stride.claim.TxProto.MsgCreateAirdrop, com.stride.claim.TxProto.MsgCreateAirdropResponse> getCreateAirdropMethod;
    if ((getCreateAirdropMethod = MsgGrpc.getCreateAirdropMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateAirdropMethod = MsgGrpc.getCreateAirdropMethod) == null) {
          MsgGrpc.getCreateAirdropMethod = getCreateAirdropMethod =
              io.grpc.MethodDescriptor.<com.stride.claim.TxProto.MsgCreateAirdrop, com.stride.claim.TxProto.MsgCreateAirdropResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateAirdrop"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.TxProto.MsgCreateAirdrop.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.TxProto.MsgCreateAirdropResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateAirdrop"))
              .build();
        }
      }
    }
    return getCreateAirdropMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.claim.TxProto.MsgDeleteAirdrop,
      com.stride.claim.TxProto.MsgDeleteAirdropResponse> getDeleteAirdropMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteAirdrop",
      requestType = com.stride.claim.TxProto.MsgDeleteAirdrop.class,
      responseType = com.stride.claim.TxProto.MsgDeleteAirdropResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.claim.TxProto.MsgDeleteAirdrop,
      com.stride.claim.TxProto.MsgDeleteAirdropResponse> getDeleteAirdropMethod() {
    io.grpc.MethodDescriptor<com.stride.claim.TxProto.MsgDeleteAirdrop, com.stride.claim.TxProto.MsgDeleteAirdropResponse> getDeleteAirdropMethod;
    if ((getDeleteAirdropMethod = MsgGrpc.getDeleteAirdropMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteAirdropMethod = MsgGrpc.getDeleteAirdropMethod) == null) {
          MsgGrpc.getDeleteAirdropMethod = getDeleteAirdropMethod =
              io.grpc.MethodDescriptor.<com.stride.claim.TxProto.MsgDeleteAirdrop, com.stride.claim.TxProto.MsgDeleteAirdropResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteAirdrop"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.TxProto.MsgDeleteAirdrop.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.claim.TxProto.MsgDeleteAirdropResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteAirdrop"))
              .build();
        }
      }
    }
    return getDeleteAirdropMethod;
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
     */
    default void setAirdropAllocations(com.stride.claim.TxProto.MsgSetAirdropAllocations request,
        io.grpc.stub.StreamObserver<com.stride.claim.TxProto.MsgSetAirdropAllocationsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetAirdropAllocationsMethod(), responseObserver);
    }

    /**
     */
    default void claimFreeAmount(com.stride.claim.TxProto.MsgClaimFreeAmount request,
        io.grpc.stub.StreamObserver<com.stride.claim.TxProto.MsgClaimFreeAmountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClaimFreeAmountMethod(), responseObserver);
    }

    /**
     */
    default void createAirdrop(com.stride.claim.TxProto.MsgCreateAirdrop request,
        io.grpc.stub.StreamObserver<com.stride.claim.TxProto.MsgCreateAirdropResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateAirdropMethod(), responseObserver);
    }

    /**
     */
    default void deleteAirdrop(com.stride.claim.TxProto.MsgDeleteAirdrop request,
        io.grpc.stub.StreamObserver<com.stride.claim.TxProto.MsgDeleteAirdropResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteAirdropMethod(), responseObserver);
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
     */
    public void setAirdropAllocations(com.stride.claim.TxProto.MsgSetAirdropAllocations request,
        io.grpc.stub.StreamObserver<com.stride.claim.TxProto.MsgSetAirdropAllocationsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetAirdropAllocationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void claimFreeAmount(com.stride.claim.TxProto.MsgClaimFreeAmount request,
        io.grpc.stub.StreamObserver<com.stride.claim.TxProto.MsgClaimFreeAmountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClaimFreeAmountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createAirdrop(com.stride.claim.TxProto.MsgCreateAirdrop request,
        io.grpc.stub.StreamObserver<com.stride.claim.TxProto.MsgCreateAirdropResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateAirdropMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteAirdrop(com.stride.claim.TxProto.MsgDeleteAirdrop request,
        io.grpc.stub.StreamObserver<com.stride.claim.TxProto.MsgDeleteAirdropResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteAirdropMethod(), getCallOptions()), request, responseObserver);
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
     */
    public com.stride.claim.TxProto.MsgSetAirdropAllocationsResponse setAirdropAllocations(com.stride.claim.TxProto.MsgSetAirdropAllocations request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetAirdropAllocationsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.stride.claim.TxProto.MsgClaimFreeAmountResponse claimFreeAmount(com.stride.claim.TxProto.MsgClaimFreeAmount request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClaimFreeAmountMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.stride.claim.TxProto.MsgCreateAirdropResponse createAirdrop(com.stride.claim.TxProto.MsgCreateAirdrop request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateAirdropMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.stride.claim.TxProto.MsgDeleteAirdropResponse deleteAirdrop(com.stride.claim.TxProto.MsgDeleteAirdrop request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteAirdropMethod(), getCallOptions(), request);
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
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.claim.TxProto.MsgSetAirdropAllocationsResponse> setAirdropAllocations(
        com.stride.claim.TxProto.MsgSetAirdropAllocations request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetAirdropAllocationsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.claim.TxProto.MsgClaimFreeAmountResponse> claimFreeAmount(
        com.stride.claim.TxProto.MsgClaimFreeAmount request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClaimFreeAmountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.claim.TxProto.MsgCreateAirdropResponse> createAirdrop(
        com.stride.claim.TxProto.MsgCreateAirdrop request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateAirdropMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.claim.TxProto.MsgDeleteAirdropResponse> deleteAirdrop(
        com.stride.claim.TxProto.MsgDeleteAirdrop request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteAirdropMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_AIRDROP_ALLOCATIONS = 0;
  private static final int METHODID_CLAIM_FREE_AMOUNT = 1;
  private static final int METHODID_CREATE_AIRDROP = 2;
  private static final int METHODID_DELETE_AIRDROP = 3;

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
        case METHODID_SET_AIRDROP_ALLOCATIONS:
          serviceImpl.setAirdropAllocations((com.stride.claim.TxProto.MsgSetAirdropAllocations) request,
              (io.grpc.stub.StreamObserver<com.stride.claim.TxProto.MsgSetAirdropAllocationsResponse>) responseObserver);
          break;
        case METHODID_CLAIM_FREE_AMOUNT:
          serviceImpl.claimFreeAmount((com.stride.claim.TxProto.MsgClaimFreeAmount) request,
              (io.grpc.stub.StreamObserver<com.stride.claim.TxProto.MsgClaimFreeAmountResponse>) responseObserver);
          break;
        case METHODID_CREATE_AIRDROP:
          serviceImpl.createAirdrop((com.stride.claim.TxProto.MsgCreateAirdrop) request,
              (io.grpc.stub.StreamObserver<com.stride.claim.TxProto.MsgCreateAirdropResponse>) responseObserver);
          break;
        case METHODID_DELETE_AIRDROP:
          serviceImpl.deleteAirdrop((com.stride.claim.TxProto.MsgDeleteAirdrop) request,
              (io.grpc.stub.StreamObserver<com.stride.claim.TxProto.MsgDeleteAirdropResponse>) responseObserver);
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
          getSetAirdropAllocationsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.claim.TxProto.MsgSetAirdropAllocations,
              com.stride.claim.TxProto.MsgSetAirdropAllocationsResponse>(
                service, METHODID_SET_AIRDROP_ALLOCATIONS)))
        .addMethod(
          getClaimFreeAmountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.claim.TxProto.MsgClaimFreeAmount,
              com.stride.claim.TxProto.MsgClaimFreeAmountResponse>(
                service, METHODID_CLAIM_FREE_AMOUNT)))
        .addMethod(
          getCreateAirdropMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.claim.TxProto.MsgCreateAirdrop,
              com.stride.claim.TxProto.MsgCreateAirdropResponse>(
                service, METHODID_CREATE_AIRDROP)))
        .addMethod(
          getDeleteAirdropMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.claim.TxProto.MsgDeleteAirdrop,
              com.stride.claim.TxProto.MsgDeleteAirdropResponse>(
                service, METHODID_DELETE_AIRDROP)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.stride.claim.TxProto.getDescriptor();
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
              .addMethod(getSetAirdropAllocationsMethod())
              .addMethod(getClaimFreeAmountMethod())
              .addMethod(getCreateAirdropMethod())
              .addMethod(getDeleteAirdropMethod())
              .build();
        }
      }
    }
    return result;
  }
}
