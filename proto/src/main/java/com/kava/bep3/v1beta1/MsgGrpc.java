package com.kava.bep3.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the bep3 Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: kava/bep3/v1beta1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.bep3.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwap,
      com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwapResponse> getCreateAtomicSwapMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateAtomicSwap",
      requestType = com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwap.class,
      responseType = com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwapResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwap,
      com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwapResponse> getCreateAtomicSwapMethod() {
    io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwap, com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwapResponse> getCreateAtomicSwapMethod;
    if ((getCreateAtomicSwapMethod = MsgGrpc.getCreateAtomicSwapMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateAtomicSwapMethod = MsgGrpc.getCreateAtomicSwapMethod) == null) {
          MsgGrpc.getCreateAtomicSwapMethod = getCreateAtomicSwapMethod =
              io.grpc.MethodDescriptor.<com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwap, com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwapResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateAtomicSwap"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwap.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwapResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateAtomicSwap"))
              .build();
        }
      }
    }
    return getCreateAtomicSwapMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwap,
      com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwapResponse> getClaimAtomicSwapMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimAtomicSwap",
      requestType = com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwap.class,
      responseType = com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwapResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwap,
      com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwapResponse> getClaimAtomicSwapMethod() {
    io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwap, com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwapResponse> getClaimAtomicSwapMethod;
    if ((getClaimAtomicSwapMethod = MsgGrpc.getClaimAtomicSwapMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClaimAtomicSwapMethod = MsgGrpc.getClaimAtomicSwapMethod) == null) {
          MsgGrpc.getClaimAtomicSwapMethod = getClaimAtomicSwapMethod =
              io.grpc.MethodDescriptor.<com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwap, com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwapResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimAtomicSwap"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwap.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwapResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ClaimAtomicSwap"))
              .build();
        }
      }
    }
    return getClaimAtomicSwapMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwap,
      com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwapResponse> getRefundAtomicSwapMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RefundAtomicSwap",
      requestType = com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwap.class,
      responseType = com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwapResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwap,
      com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwapResponse> getRefundAtomicSwapMethod() {
    io.grpc.MethodDescriptor<com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwap, com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwapResponse> getRefundAtomicSwapMethod;
    if ((getRefundAtomicSwapMethod = MsgGrpc.getRefundAtomicSwapMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRefundAtomicSwapMethod = MsgGrpc.getRefundAtomicSwapMethod) == null) {
          MsgGrpc.getRefundAtomicSwapMethod = getRefundAtomicSwapMethod =
              io.grpc.MethodDescriptor.<com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwap, com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwapResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RefundAtomicSwap"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwap.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwapResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RefundAtomicSwap"))
              .build();
        }
      }
    }
    return getRefundAtomicSwapMethod;
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
   * Msg defines the bep3 Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * CreateAtomicSwap defines a method for creating an atomic swap
     * </pre>
     */
    default void createAtomicSwap(com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwap request,
        io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwapResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateAtomicSwapMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClaimAtomicSwap defines a method for claiming an atomic swap
     * </pre>
     */
    default void claimAtomicSwap(com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwap request,
        io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwapResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClaimAtomicSwapMethod(), responseObserver);
    }

    /**
     * <pre>
     * RefundAtomicSwap defines a method for refunding an atomic swap
     * </pre>
     */
    default void refundAtomicSwap(com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwap request,
        io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwapResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRefundAtomicSwapMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the bep3 Msg service.
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
   * Msg defines the bep3 Msg service.
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
     * CreateAtomicSwap defines a method for creating an atomic swap
     * </pre>
     */
    public void createAtomicSwap(com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwap request,
        io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwapResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateAtomicSwapMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClaimAtomicSwap defines a method for claiming an atomic swap
     * </pre>
     */
    public void claimAtomicSwap(com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwap request,
        io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwapResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClaimAtomicSwapMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RefundAtomicSwap defines a method for refunding an atomic swap
     * </pre>
     */
    public void refundAtomicSwap(com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwap request,
        io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwapResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRefundAtomicSwapMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the bep3 Msg service.
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
     * CreateAtomicSwap defines a method for creating an atomic swap
     * </pre>
     */
    public com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwapResponse createAtomicSwap(com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwap request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateAtomicSwapMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClaimAtomicSwap defines a method for claiming an atomic swap
     * </pre>
     */
    public com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwapResponse claimAtomicSwap(com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwap request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClaimAtomicSwapMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RefundAtomicSwap defines a method for refunding an atomic swap
     * </pre>
     */
    public com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwapResponse refundAtomicSwap(com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwap request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRefundAtomicSwapMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the bep3 Msg service.
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
     * CreateAtomicSwap defines a method for creating an atomic swap
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwapResponse> createAtomicSwap(
        com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwap request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateAtomicSwapMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClaimAtomicSwap defines a method for claiming an atomic swap
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwapResponse> claimAtomicSwap(
        com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwap request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClaimAtomicSwapMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RefundAtomicSwap defines a method for refunding an atomic swap
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwapResponse> refundAtomicSwap(
        com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwap request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRefundAtomicSwapMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_ATOMIC_SWAP = 0;
  private static final int METHODID_CLAIM_ATOMIC_SWAP = 1;
  private static final int METHODID_REFUND_ATOMIC_SWAP = 2;

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
        case METHODID_CREATE_ATOMIC_SWAP:
          serviceImpl.createAtomicSwap((com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwap) request,
              (io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwapResponse>) responseObserver);
          break;
        case METHODID_CLAIM_ATOMIC_SWAP:
          serviceImpl.claimAtomicSwap((com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwap) request,
              (io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwapResponse>) responseObserver);
          break;
        case METHODID_REFUND_ATOMIC_SWAP:
          serviceImpl.refundAtomicSwap((com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwap) request,
              (io.grpc.stub.StreamObserver<com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwapResponse>) responseObserver);
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
          getCreateAtomicSwapMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwap,
              com.kava.bep3.v1beta1.TxProto.MsgCreateAtomicSwapResponse>(
                service, METHODID_CREATE_ATOMIC_SWAP)))
        .addMethod(
          getClaimAtomicSwapMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwap,
              com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwapResponse>(
                service, METHODID_CLAIM_ATOMIC_SWAP)))
        .addMethod(
          getRefundAtomicSwapMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwap,
              com.kava.bep3.v1beta1.TxProto.MsgRefundAtomicSwapResponse>(
                service, METHODID_REFUND_ATOMIC_SWAP)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kava.bep3.v1beta1.TxProto.getDescriptor();
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
              .addMethod(getCreateAtomicSwapMethod())
              .addMethod(getClaimAtomicSwapMethod())
              .addMethod(getRefundAtomicSwapMethod())
              .build();
        }
      }
    }
    return result;
  }
}
