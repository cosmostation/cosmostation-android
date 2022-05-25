package starnamed.x.escrow.v1beta1;

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
 * Msg defines the escrow Msg service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: starname/iov/escrow/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "starnamed.x.escrow.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrow,
      starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrowResponse> getCreateEscrowMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateEscrow",
      requestType = starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrow.class,
      responseType = starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrowResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrow,
      starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrowResponse> getCreateEscrowMethod() {
    io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrow, starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrowResponse> getCreateEscrowMethod;
    if ((getCreateEscrowMethod = MsgGrpc.getCreateEscrowMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateEscrowMethod = MsgGrpc.getCreateEscrowMethod) == null) {
          MsgGrpc.getCreateEscrowMethod = getCreateEscrowMethod =
              io.grpc.MethodDescriptor.<starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrow, starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrowResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateEscrow"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrow.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrowResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateEscrow"))
              .build();
        }
      }
    }
    return getCreateEscrowMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrow,
      starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrowResponse> getUpdateEscrowMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateEscrow",
      requestType = starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrow.class,
      responseType = starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrowResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrow,
      starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrowResponse> getUpdateEscrowMethod() {
    io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrow, starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrowResponse> getUpdateEscrowMethod;
    if ((getUpdateEscrowMethod = MsgGrpc.getUpdateEscrowMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateEscrowMethod = MsgGrpc.getUpdateEscrowMethod) == null) {
          MsgGrpc.getUpdateEscrowMethod = getUpdateEscrowMethod =
              io.grpc.MethodDescriptor.<starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrow, starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrowResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateEscrow"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrow.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrowResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateEscrow"))
              .build();
        }
      }
    }
    return getUpdateEscrowMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrow,
      starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrowResponse> getTransferToEscrowMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TransferToEscrow",
      requestType = starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrow.class,
      responseType = starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrowResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrow,
      starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrowResponse> getTransferToEscrowMethod() {
    io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrow, starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrowResponse> getTransferToEscrowMethod;
    if ((getTransferToEscrowMethod = MsgGrpc.getTransferToEscrowMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getTransferToEscrowMethod = MsgGrpc.getTransferToEscrowMethod) == null) {
          MsgGrpc.getTransferToEscrowMethod = getTransferToEscrowMethod =
              io.grpc.MethodDescriptor.<starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrow, starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrowResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TransferToEscrow"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrow.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrowResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("TransferToEscrow"))
              .build();
        }
      }
    }
    return getTransferToEscrowMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrow,
      starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrowResponse> getRefundEscrowMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RefundEscrow",
      requestType = starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrow.class,
      responseType = starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrowResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrow,
      starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrowResponse> getRefundEscrowMethod() {
    io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrow, starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrowResponse> getRefundEscrowMethod;
    if ((getRefundEscrowMethod = MsgGrpc.getRefundEscrowMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRefundEscrowMethod = MsgGrpc.getRefundEscrowMethod) == null) {
          MsgGrpc.getRefundEscrowMethod = getRefundEscrowMethod =
              io.grpc.MethodDescriptor.<starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrow, starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrowResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RefundEscrow"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrow.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrowResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RefundEscrow"))
              .build();
        }
      }
    }
    return getRefundEscrowMethod;
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
   * Msg defines the escrow Msg service
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreateEscrow defines a method for creating an escrow
     * </pre>
     */
    public void createEscrow(starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrow request,
        io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrowResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateEscrowMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateEscrow defines a method for updating an escrow
     * </pre>
     */
    public void updateEscrow(starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrow request,
        io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrowResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateEscrowMethod(), responseObserver);
    }

    /**
     * <pre>
     * TransferToEscrow defines a method for a buyer to transfer funds to the escrow
     * </pre>
     */
    public void transferToEscrow(starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrow request,
        io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrowResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTransferToEscrowMethod(), responseObserver);
    }

    /**
     * <pre>
     * RefundEscrow defines a method for the seller to return the assets locked in the escrow
     * </pre>
     */
    public void refundEscrow(starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrow request,
        io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrowResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRefundEscrowMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateEscrowMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrow,
                starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrowResponse>(
                  this, METHODID_CREATE_ESCROW)))
          .addMethod(
            getUpdateEscrowMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrow,
                starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrowResponse>(
                  this, METHODID_UPDATE_ESCROW)))
          .addMethod(
            getTransferToEscrowMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrow,
                starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrowResponse>(
                  this, METHODID_TRANSFER_TO_ESCROW)))
          .addMethod(
            getRefundEscrowMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrow,
                starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrowResponse>(
                  this, METHODID_REFUND_ESCROW)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the escrow Msg service
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
     * CreateEscrow defines a method for creating an escrow
     * </pre>
     */
    public void createEscrow(starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrow request,
        io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrowResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateEscrowMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateEscrow defines a method for updating an escrow
     * </pre>
     */
    public void updateEscrow(starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrow request,
        io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrowResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateEscrowMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TransferToEscrow defines a method for a buyer to transfer funds to the escrow
     * </pre>
     */
    public void transferToEscrow(starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrow request,
        io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrowResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTransferToEscrowMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RefundEscrow defines a method for the seller to return the assets locked in the escrow
     * </pre>
     */
    public void refundEscrow(starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrow request,
        io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrowResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRefundEscrowMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the escrow Msg service
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
     * CreateEscrow defines a method for creating an escrow
     * </pre>
     */
    public starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrowResponse createEscrow(starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrow request) {
      return blockingUnaryCall(
          getChannel(), getCreateEscrowMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateEscrow defines a method for updating an escrow
     * </pre>
     */
    public starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrowResponse updateEscrow(starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrow request) {
      return blockingUnaryCall(
          getChannel(), getUpdateEscrowMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TransferToEscrow defines a method for a buyer to transfer funds to the escrow
     * </pre>
     */
    public starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrowResponse transferToEscrow(starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrow request) {
      return blockingUnaryCall(
          getChannel(), getTransferToEscrowMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RefundEscrow defines a method for the seller to return the assets locked in the escrow
     * </pre>
     */
    public starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrowResponse refundEscrow(starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrow request) {
      return blockingUnaryCall(
          getChannel(), getRefundEscrowMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the escrow Msg service
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
     * CreateEscrow defines a method for creating an escrow
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrowResponse> createEscrow(
        starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrow request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateEscrowMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateEscrow defines a method for updating an escrow
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrowResponse> updateEscrow(
        starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrow request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateEscrowMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TransferToEscrow defines a method for a buyer to transfer funds to the escrow
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrowResponse> transferToEscrow(
        starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrow request) {
      return futureUnaryCall(
          getChannel().newCall(getTransferToEscrowMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RefundEscrow defines a method for the seller to return the assets locked in the escrow
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrowResponse> refundEscrow(
        starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrow request) {
      return futureUnaryCall(
          getChannel().newCall(getRefundEscrowMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_ESCROW = 0;
  private static final int METHODID_UPDATE_ESCROW = 1;
  private static final int METHODID_TRANSFER_TO_ESCROW = 2;
  private static final int METHODID_REFUND_ESCROW = 3;

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
        case METHODID_CREATE_ESCROW:
          serviceImpl.createEscrow((starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrow) request,
              (io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.Tx.MsgCreateEscrowResponse>) responseObserver);
          break;
        case METHODID_UPDATE_ESCROW:
          serviceImpl.updateEscrow((starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrow) request,
              (io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.Tx.MsgUpdateEscrowResponse>) responseObserver);
          break;
        case METHODID_TRANSFER_TO_ESCROW:
          serviceImpl.transferToEscrow((starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrow) request,
              (io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.Tx.MsgTransferToEscrowResponse>) responseObserver);
          break;
        case METHODID_REFUND_ESCROW:
          serviceImpl.refundEscrow((starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrow) request,
              (io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.Tx.MsgRefundEscrowResponse>) responseObserver);
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
      return starnamed.x.escrow.v1beta1.Tx.getDescriptor();
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
              .addMethod(getCreateEscrowMethod())
              .addMethod(getUpdateEscrowMethod())
              .addMethod(getTransferToEscrowMethod())
              .addMethod(getRefundEscrowMethod())
              .build();
        }
      }
    }
    return result;
  }
}
