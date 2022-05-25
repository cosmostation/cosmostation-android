package kava.bep3.v1beta1;

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
 * Msg defines the bep3 Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/bep3/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.bep3.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.bep3.v1beta1.Tx.MsgCreateAtomicSwap,
      kava.bep3.v1beta1.Tx.MsgCreateAtomicSwapResponse> getCreateAtomicSwapMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateAtomicSwap",
      requestType = kava.bep3.v1beta1.Tx.MsgCreateAtomicSwap.class,
      responseType = kava.bep3.v1beta1.Tx.MsgCreateAtomicSwapResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.bep3.v1beta1.Tx.MsgCreateAtomicSwap,
      kava.bep3.v1beta1.Tx.MsgCreateAtomicSwapResponse> getCreateAtomicSwapMethod() {
    io.grpc.MethodDescriptor<kava.bep3.v1beta1.Tx.MsgCreateAtomicSwap, kava.bep3.v1beta1.Tx.MsgCreateAtomicSwapResponse> getCreateAtomicSwapMethod;
    if ((getCreateAtomicSwapMethod = MsgGrpc.getCreateAtomicSwapMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateAtomicSwapMethod = MsgGrpc.getCreateAtomicSwapMethod) == null) {
          MsgGrpc.getCreateAtomicSwapMethod = getCreateAtomicSwapMethod =
              io.grpc.MethodDescriptor.<kava.bep3.v1beta1.Tx.MsgCreateAtomicSwap, kava.bep3.v1beta1.Tx.MsgCreateAtomicSwapResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateAtomicSwap"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.bep3.v1beta1.Tx.MsgCreateAtomicSwap.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.bep3.v1beta1.Tx.MsgCreateAtomicSwapResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateAtomicSwap"))
              .build();
        }
      }
    }
    return getCreateAtomicSwapMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.bep3.v1beta1.Tx.MsgClaimAtomicSwap,
      kava.bep3.v1beta1.Tx.MsgClaimAtomicSwapResponse> getClaimAtomicSwapMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimAtomicSwap",
      requestType = kava.bep3.v1beta1.Tx.MsgClaimAtomicSwap.class,
      responseType = kava.bep3.v1beta1.Tx.MsgClaimAtomicSwapResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.bep3.v1beta1.Tx.MsgClaimAtomicSwap,
      kava.bep3.v1beta1.Tx.MsgClaimAtomicSwapResponse> getClaimAtomicSwapMethod() {
    io.grpc.MethodDescriptor<kava.bep3.v1beta1.Tx.MsgClaimAtomicSwap, kava.bep3.v1beta1.Tx.MsgClaimAtomicSwapResponse> getClaimAtomicSwapMethod;
    if ((getClaimAtomicSwapMethod = MsgGrpc.getClaimAtomicSwapMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClaimAtomicSwapMethod = MsgGrpc.getClaimAtomicSwapMethod) == null) {
          MsgGrpc.getClaimAtomicSwapMethod = getClaimAtomicSwapMethod =
              io.grpc.MethodDescriptor.<kava.bep3.v1beta1.Tx.MsgClaimAtomicSwap, kava.bep3.v1beta1.Tx.MsgClaimAtomicSwapResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimAtomicSwap"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.bep3.v1beta1.Tx.MsgClaimAtomicSwap.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.bep3.v1beta1.Tx.MsgClaimAtomicSwapResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ClaimAtomicSwap"))
              .build();
        }
      }
    }
    return getClaimAtomicSwapMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.bep3.v1beta1.Tx.MsgRefundAtomicSwap,
      kava.bep3.v1beta1.Tx.MsgRefundAtomicSwapResponse> getRefundAtomicSwapMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RefundAtomicSwap",
      requestType = kava.bep3.v1beta1.Tx.MsgRefundAtomicSwap.class,
      responseType = kava.bep3.v1beta1.Tx.MsgRefundAtomicSwapResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.bep3.v1beta1.Tx.MsgRefundAtomicSwap,
      kava.bep3.v1beta1.Tx.MsgRefundAtomicSwapResponse> getRefundAtomicSwapMethod() {
    io.grpc.MethodDescriptor<kava.bep3.v1beta1.Tx.MsgRefundAtomicSwap, kava.bep3.v1beta1.Tx.MsgRefundAtomicSwapResponse> getRefundAtomicSwapMethod;
    if ((getRefundAtomicSwapMethod = MsgGrpc.getRefundAtomicSwapMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRefundAtomicSwapMethod = MsgGrpc.getRefundAtomicSwapMethod) == null) {
          MsgGrpc.getRefundAtomicSwapMethod = getRefundAtomicSwapMethod =
              io.grpc.MethodDescriptor.<kava.bep3.v1beta1.Tx.MsgRefundAtomicSwap, kava.bep3.v1beta1.Tx.MsgRefundAtomicSwapResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RefundAtomicSwap"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.bep3.v1beta1.Tx.MsgRefundAtomicSwap.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.bep3.v1beta1.Tx.MsgRefundAtomicSwapResponse.getDefaultInstance()))
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
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreateAtomicSwap defines a method for creating an atomic swap
     * </pre>
     */
    public void createAtomicSwap(kava.bep3.v1beta1.Tx.MsgCreateAtomicSwap request,
        io.grpc.stub.StreamObserver<kava.bep3.v1beta1.Tx.MsgCreateAtomicSwapResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateAtomicSwapMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClaimAtomicSwap defines a method for claiming an atomic swap
     * </pre>
     */
    public void claimAtomicSwap(kava.bep3.v1beta1.Tx.MsgClaimAtomicSwap request,
        io.grpc.stub.StreamObserver<kava.bep3.v1beta1.Tx.MsgClaimAtomicSwapResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClaimAtomicSwapMethod(), responseObserver);
    }

    /**
     * <pre>
     * RefundAtomicSwap defines a method for refunding an atomic swap
     * </pre>
     */
    public void refundAtomicSwap(kava.bep3.v1beta1.Tx.MsgRefundAtomicSwap request,
        io.grpc.stub.StreamObserver<kava.bep3.v1beta1.Tx.MsgRefundAtomicSwapResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRefundAtomicSwapMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateAtomicSwapMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.bep3.v1beta1.Tx.MsgCreateAtomicSwap,
                kava.bep3.v1beta1.Tx.MsgCreateAtomicSwapResponse>(
                  this, METHODID_CREATE_ATOMIC_SWAP)))
          .addMethod(
            getClaimAtomicSwapMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.bep3.v1beta1.Tx.MsgClaimAtomicSwap,
                kava.bep3.v1beta1.Tx.MsgClaimAtomicSwapResponse>(
                  this, METHODID_CLAIM_ATOMIC_SWAP)))
          .addMethod(
            getRefundAtomicSwapMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.bep3.v1beta1.Tx.MsgRefundAtomicSwap,
                kava.bep3.v1beta1.Tx.MsgRefundAtomicSwapResponse>(
                  this, METHODID_REFUND_ATOMIC_SWAP)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the bep3 Msg service.
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
     * CreateAtomicSwap defines a method for creating an atomic swap
     * </pre>
     */
    public void createAtomicSwap(kava.bep3.v1beta1.Tx.MsgCreateAtomicSwap request,
        io.grpc.stub.StreamObserver<kava.bep3.v1beta1.Tx.MsgCreateAtomicSwapResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateAtomicSwapMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClaimAtomicSwap defines a method for claiming an atomic swap
     * </pre>
     */
    public void claimAtomicSwap(kava.bep3.v1beta1.Tx.MsgClaimAtomicSwap request,
        io.grpc.stub.StreamObserver<kava.bep3.v1beta1.Tx.MsgClaimAtomicSwapResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClaimAtomicSwapMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RefundAtomicSwap defines a method for refunding an atomic swap
     * </pre>
     */
    public void refundAtomicSwap(kava.bep3.v1beta1.Tx.MsgRefundAtomicSwap request,
        io.grpc.stub.StreamObserver<kava.bep3.v1beta1.Tx.MsgRefundAtomicSwapResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRefundAtomicSwapMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the bep3 Msg service.
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
     * CreateAtomicSwap defines a method for creating an atomic swap
     * </pre>
     */
    public kava.bep3.v1beta1.Tx.MsgCreateAtomicSwapResponse createAtomicSwap(kava.bep3.v1beta1.Tx.MsgCreateAtomicSwap request) {
      return blockingUnaryCall(
          getChannel(), getCreateAtomicSwapMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClaimAtomicSwap defines a method for claiming an atomic swap
     * </pre>
     */
    public kava.bep3.v1beta1.Tx.MsgClaimAtomicSwapResponse claimAtomicSwap(kava.bep3.v1beta1.Tx.MsgClaimAtomicSwap request) {
      return blockingUnaryCall(
          getChannel(), getClaimAtomicSwapMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RefundAtomicSwap defines a method for refunding an atomic swap
     * </pre>
     */
    public kava.bep3.v1beta1.Tx.MsgRefundAtomicSwapResponse refundAtomicSwap(kava.bep3.v1beta1.Tx.MsgRefundAtomicSwap request) {
      return blockingUnaryCall(
          getChannel(), getRefundAtomicSwapMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the bep3 Msg service.
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
     * CreateAtomicSwap defines a method for creating an atomic swap
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.bep3.v1beta1.Tx.MsgCreateAtomicSwapResponse> createAtomicSwap(
        kava.bep3.v1beta1.Tx.MsgCreateAtomicSwap request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateAtomicSwapMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClaimAtomicSwap defines a method for claiming an atomic swap
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.bep3.v1beta1.Tx.MsgClaimAtomicSwapResponse> claimAtomicSwap(
        kava.bep3.v1beta1.Tx.MsgClaimAtomicSwap request) {
      return futureUnaryCall(
          getChannel().newCall(getClaimAtomicSwapMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RefundAtomicSwap defines a method for refunding an atomic swap
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.bep3.v1beta1.Tx.MsgRefundAtomicSwapResponse> refundAtomicSwap(
        kava.bep3.v1beta1.Tx.MsgRefundAtomicSwap request) {
      return futureUnaryCall(
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
        case METHODID_CREATE_ATOMIC_SWAP:
          serviceImpl.createAtomicSwap((kava.bep3.v1beta1.Tx.MsgCreateAtomicSwap) request,
              (io.grpc.stub.StreamObserver<kava.bep3.v1beta1.Tx.MsgCreateAtomicSwapResponse>) responseObserver);
          break;
        case METHODID_CLAIM_ATOMIC_SWAP:
          serviceImpl.claimAtomicSwap((kava.bep3.v1beta1.Tx.MsgClaimAtomicSwap) request,
              (io.grpc.stub.StreamObserver<kava.bep3.v1beta1.Tx.MsgClaimAtomicSwapResponse>) responseObserver);
          break;
        case METHODID_REFUND_ATOMIC_SWAP:
          serviceImpl.refundAtomicSwap((kava.bep3.v1beta1.Tx.MsgRefundAtomicSwap) request,
              (io.grpc.stub.StreamObserver<kava.bep3.v1beta1.Tx.MsgRefundAtomicSwapResponse>) responseObserver);
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
      return kava.bep3.v1beta1.Tx.getDescriptor();
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
