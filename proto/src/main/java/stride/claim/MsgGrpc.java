package stride.claim;

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
 * Msg defines the Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: stride/claim/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "stride.claim.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<stride.claim.Tx.MsgSetAirdropAllocations,
      stride.claim.Tx.MsgSetAirdropAllocationsResponse> getSetAirdropAllocationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetAirdropAllocations",
      requestType = stride.claim.Tx.MsgSetAirdropAllocations.class,
      responseType = stride.claim.Tx.MsgSetAirdropAllocationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.claim.Tx.MsgSetAirdropAllocations,
      stride.claim.Tx.MsgSetAirdropAllocationsResponse> getSetAirdropAllocationsMethod() {
    io.grpc.MethodDescriptor<stride.claim.Tx.MsgSetAirdropAllocations, stride.claim.Tx.MsgSetAirdropAllocationsResponse> getSetAirdropAllocationsMethod;
    if ((getSetAirdropAllocationsMethod = MsgGrpc.getSetAirdropAllocationsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetAirdropAllocationsMethod = MsgGrpc.getSetAirdropAllocationsMethod) == null) {
          MsgGrpc.getSetAirdropAllocationsMethod = getSetAirdropAllocationsMethod =
              io.grpc.MethodDescriptor.<stride.claim.Tx.MsgSetAirdropAllocations, stride.claim.Tx.MsgSetAirdropAllocationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetAirdropAllocations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.Tx.MsgSetAirdropAllocations.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.Tx.MsgSetAirdropAllocationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetAirdropAllocations"))
              .build();
        }
      }
    }
    return getSetAirdropAllocationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.claim.Tx.MsgClaimFreeAmount,
      stride.claim.Tx.MsgClaimFreeAmountResponse> getClaimFreeAmountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimFreeAmount",
      requestType = stride.claim.Tx.MsgClaimFreeAmount.class,
      responseType = stride.claim.Tx.MsgClaimFreeAmountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.claim.Tx.MsgClaimFreeAmount,
      stride.claim.Tx.MsgClaimFreeAmountResponse> getClaimFreeAmountMethod() {
    io.grpc.MethodDescriptor<stride.claim.Tx.MsgClaimFreeAmount, stride.claim.Tx.MsgClaimFreeAmountResponse> getClaimFreeAmountMethod;
    if ((getClaimFreeAmountMethod = MsgGrpc.getClaimFreeAmountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClaimFreeAmountMethod = MsgGrpc.getClaimFreeAmountMethod) == null) {
          MsgGrpc.getClaimFreeAmountMethod = getClaimFreeAmountMethod =
              io.grpc.MethodDescriptor.<stride.claim.Tx.MsgClaimFreeAmount, stride.claim.Tx.MsgClaimFreeAmountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimFreeAmount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.Tx.MsgClaimFreeAmount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.Tx.MsgClaimFreeAmountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ClaimFreeAmount"))
              .build();
        }
      }
    }
    return getClaimFreeAmountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.claim.Tx.MsgCreateAirdrop,
      stride.claim.Tx.MsgCreateAirdropResponse> getCreateAirdropMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateAirdrop",
      requestType = stride.claim.Tx.MsgCreateAirdrop.class,
      responseType = stride.claim.Tx.MsgCreateAirdropResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.claim.Tx.MsgCreateAirdrop,
      stride.claim.Tx.MsgCreateAirdropResponse> getCreateAirdropMethod() {
    io.grpc.MethodDescriptor<stride.claim.Tx.MsgCreateAirdrop, stride.claim.Tx.MsgCreateAirdropResponse> getCreateAirdropMethod;
    if ((getCreateAirdropMethod = MsgGrpc.getCreateAirdropMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateAirdropMethod = MsgGrpc.getCreateAirdropMethod) == null) {
          MsgGrpc.getCreateAirdropMethod = getCreateAirdropMethod =
              io.grpc.MethodDescriptor.<stride.claim.Tx.MsgCreateAirdrop, stride.claim.Tx.MsgCreateAirdropResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateAirdrop"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.Tx.MsgCreateAirdrop.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.Tx.MsgCreateAirdropResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateAirdrop"))
              .build();
        }
      }
    }
    return getCreateAirdropMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.claim.Tx.MsgDeleteAirdrop,
      stride.claim.Tx.MsgDeleteAirdropResponse> getDeleteAirdropMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteAirdrop",
      requestType = stride.claim.Tx.MsgDeleteAirdrop.class,
      responseType = stride.claim.Tx.MsgDeleteAirdropResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.claim.Tx.MsgDeleteAirdrop,
      stride.claim.Tx.MsgDeleteAirdropResponse> getDeleteAirdropMethod() {
    io.grpc.MethodDescriptor<stride.claim.Tx.MsgDeleteAirdrop, stride.claim.Tx.MsgDeleteAirdropResponse> getDeleteAirdropMethod;
    if ((getDeleteAirdropMethod = MsgGrpc.getDeleteAirdropMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteAirdropMethod = MsgGrpc.getDeleteAirdropMethod) == null) {
          MsgGrpc.getDeleteAirdropMethod = getDeleteAirdropMethod =
              io.grpc.MethodDescriptor.<stride.claim.Tx.MsgDeleteAirdrop, stride.claim.Tx.MsgDeleteAirdropResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteAirdrop"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.Tx.MsgDeleteAirdrop.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.claim.Tx.MsgDeleteAirdropResponse.getDefaultInstance()))
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
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     */
    public void setAirdropAllocations(stride.claim.Tx.MsgSetAirdropAllocations request,
        io.grpc.stub.StreamObserver<stride.claim.Tx.MsgSetAirdropAllocationsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetAirdropAllocationsMethod(), responseObserver);
    }

    /**
     */
    public void claimFreeAmount(stride.claim.Tx.MsgClaimFreeAmount request,
        io.grpc.stub.StreamObserver<stride.claim.Tx.MsgClaimFreeAmountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClaimFreeAmountMethod(), responseObserver);
    }

    /**
     */
    public void createAirdrop(stride.claim.Tx.MsgCreateAirdrop request,
        io.grpc.stub.StreamObserver<stride.claim.Tx.MsgCreateAirdropResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateAirdropMethod(), responseObserver);
    }

    /**
     */
    public void deleteAirdrop(stride.claim.Tx.MsgDeleteAirdrop request,
        io.grpc.stub.StreamObserver<stride.claim.Tx.MsgDeleteAirdropResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteAirdropMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSetAirdropAllocationsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.claim.Tx.MsgSetAirdropAllocations,
                stride.claim.Tx.MsgSetAirdropAllocationsResponse>(
                  this, METHODID_SET_AIRDROP_ALLOCATIONS)))
          .addMethod(
            getClaimFreeAmountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.claim.Tx.MsgClaimFreeAmount,
                stride.claim.Tx.MsgClaimFreeAmountResponse>(
                  this, METHODID_CLAIM_FREE_AMOUNT)))
          .addMethod(
            getCreateAirdropMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.claim.Tx.MsgCreateAirdrop,
                stride.claim.Tx.MsgCreateAirdropResponse>(
                  this, METHODID_CREATE_AIRDROP)))
          .addMethod(
            getDeleteAirdropMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.claim.Tx.MsgDeleteAirdrop,
                stride.claim.Tx.MsgDeleteAirdropResponse>(
                  this, METHODID_DELETE_AIRDROP)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
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
     */
    public void setAirdropAllocations(stride.claim.Tx.MsgSetAirdropAllocations request,
        io.grpc.stub.StreamObserver<stride.claim.Tx.MsgSetAirdropAllocationsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetAirdropAllocationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void claimFreeAmount(stride.claim.Tx.MsgClaimFreeAmount request,
        io.grpc.stub.StreamObserver<stride.claim.Tx.MsgClaimFreeAmountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClaimFreeAmountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createAirdrop(stride.claim.Tx.MsgCreateAirdrop request,
        io.grpc.stub.StreamObserver<stride.claim.Tx.MsgCreateAirdropResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateAirdropMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteAirdrop(stride.claim.Tx.MsgDeleteAirdrop request,
        io.grpc.stub.StreamObserver<stride.claim.Tx.MsgDeleteAirdropResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteAirdropMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
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
     */
    public stride.claim.Tx.MsgSetAirdropAllocationsResponse setAirdropAllocations(stride.claim.Tx.MsgSetAirdropAllocations request) {
      return blockingUnaryCall(
          getChannel(), getSetAirdropAllocationsMethod(), getCallOptions(), request);
    }

    /**
     */
    public stride.claim.Tx.MsgClaimFreeAmountResponse claimFreeAmount(stride.claim.Tx.MsgClaimFreeAmount request) {
      return blockingUnaryCall(
          getChannel(), getClaimFreeAmountMethod(), getCallOptions(), request);
    }

    /**
     */
    public stride.claim.Tx.MsgCreateAirdropResponse createAirdrop(stride.claim.Tx.MsgCreateAirdrop request) {
      return blockingUnaryCall(
          getChannel(), getCreateAirdropMethod(), getCallOptions(), request);
    }

    /**
     */
    public stride.claim.Tx.MsgDeleteAirdropResponse deleteAirdrop(stride.claim.Tx.MsgDeleteAirdrop request) {
      return blockingUnaryCall(
          getChannel(), getDeleteAirdropMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
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
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.claim.Tx.MsgSetAirdropAllocationsResponse> setAirdropAllocations(
        stride.claim.Tx.MsgSetAirdropAllocations request) {
      return futureUnaryCall(
          getChannel().newCall(getSetAirdropAllocationsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.claim.Tx.MsgClaimFreeAmountResponse> claimFreeAmount(
        stride.claim.Tx.MsgClaimFreeAmount request) {
      return futureUnaryCall(
          getChannel().newCall(getClaimFreeAmountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.claim.Tx.MsgCreateAirdropResponse> createAirdrop(
        stride.claim.Tx.MsgCreateAirdrop request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateAirdropMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.claim.Tx.MsgDeleteAirdropResponse> deleteAirdrop(
        stride.claim.Tx.MsgDeleteAirdrop request) {
      return futureUnaryCall(
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
        case METHODID_SET_AIRDROP_ALLOCATIONS:
          serviceImpl.setAirdropAllocations((stride.claim.Tx.MsgSetAirdropAllocations) request,
              (io.grpc.stub.StreamObserver<stride.claim.Tx.MsgSetAirdropAllocationsResponse>) responseObserver);
          break;
        case METHODID_CLAIM_FREE_AMOUNT:
          serviceImpl.claimFreeAmount((stride.claim.Tx.MsgClaimFreeAmount) request,
              (io.grpc.stub.StreamObserver<stride.claim.Tx.MsgClaimFreeAmountResponse>) responseObserver);
          break;
        case METHODID_CREATE_AIRDROP:
          serviceImpl.createAirdrop((stride.claim.Tx.MsgCreateAirdrop) request,
              (io.grpc.stub.StreamObserver<stride.claim.Tx.MsgCreateAirdropResponse>) responseObserver);
          break;
        case METHODID_DELETE_AIRDROP:
          serviceImpl.deleteAirdrop((stride.claim.Tx.MsgDeleteAirdrop) request,
              (io.grpc.stub.StreamObserver<stride.claim.Tx.MsgDeleteAirdropResponse>) responseObserver);
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
      return stride.claim.Tx.getDescriptor();
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
