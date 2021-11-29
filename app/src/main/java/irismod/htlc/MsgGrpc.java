package irismod.htlc;

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
 * Msg defines the HTLC Msg service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: htlc/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "irismod.htlc.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<irismod.htlc.Tx.MsgCreateHTLC,
      irismod.htlc.Tx.MsgCreateHTLCResponse> getCreateHTLCMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateHTLC",
      requestType = irismod.htlc.Tx.MsgCreateHTLC.class,
      responseType = irismod.htlc.Tx.MsgCreateHTLCResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.htlc.Tx.MsgCreateHTLC,
      irismod.htlc.Tx.MsgCreateHTLCResponse> getCreateHTLCMethod() {
    io.grpc.MethodDescriptor<irismod.htlc.Tx.MsgCreateHTLC, irismod.htlc.Tx.MsgCreateHTLCResponse> getCreateHTLCMethod;
    if ((getCreateHTLCMethod = MsgGrpc.getCreateHTLCMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateHTLCMethod = MsgGrpc.getCreateHTLCMethod) == null) {
          MsgGrpc.getCreateHTLCMethod = getCreateHTLCMethod =
              io.grpc.MethodDescriptor.<irismod.htlc.Tx.MsgCreateHTLC, irismod.htlc.Tx.MsgCreateHTLCResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateHTLC"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.htlc.Tx.MsgCreateHTLC.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.htlc.Tx.MsgCreateHTLCResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateHTLC"))
              .build();
        }
      }
    }
    return getCreateHTLCMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.htlc.Tx.MsgClaimHTLC,
      irismod.htlc.Tx.MsgClaimHTLCResponse> getClaimHTLCMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimHTLC",
      requestType = irismod.htlc.Tx.MsgClaimHTLC.class,
      responseType = irismod.htlc.Tx.MsgClaimHTLCResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.htlc.Tx.MsgClaimHTLC,
      irismod.htlc.Tx.MsgClaimHTLCResponse> getClaimHTLCMethod() {
    io.grpc.MethodDescriptor<irismod.htlc.Tx.MsgClaimHTLC, irismod.htlc.Tx.MsgClaimHTLCResponse> getClaimHTLCMethod;
    if ((getClaimHTLCMethod = MsgGrpc.getClaimHTLCMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClaimHTLCMethod = MsgGrpc.getClaimHTLCMethod) == null) {
          MsgGrpc.getClaimHTLCMethod = getClaimHTLCMethod =
              io.grpc.MethodDescriptor.<irismod.htlc.Tx.MsgClaimHTLC, irismod.htlc.Tx.MsgClaimHTLCResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimHTLC"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.htlc.Tx.MsgClaimHTLC.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.htlc.Tx.MsgClaimHTLCResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ClaimHTLC"))
              .build();
        }
      }
    }
    return getClaimHTLCMethod;
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
   * Msg defines the HTLC Msg service
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreateHTLC defines a method for creating a HTLC
     * </pre>
     */
    public void createHTLC(irismod.htlc.Tx.MsgCreateHTLC request,
        io.grpc.stub.StreamObserver<irismod.htlc.Tx.MsgCreateHTLCResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateHTLCMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClaimHTLC defines a method for claiming a HTLC
     * </pre>
     */
    public void claimHTLC(irismod.htlc.Tx.MsgClaimHTLC request,
        io.grpc.stub.StreamObserver<irismod.htlc.Tx.MsgClaimHTLCResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClaimHTLCMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateHTLCMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.htlc.Tx.MsgCreateHTLC,
                irismod.htlc.Tx.MsgCreateHTLCResponse>(
                  this, METHODID_CREATE_HTLC)))
          .addMethod(
            getClaimHTLCMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.htlc.Tx.MsgClaimHTLC,
                irismod.htlc.Tx.MsgClaimHTLCResponse>(
                  this, METHODID_CLAIM_HTLC)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the HTLC Msg service
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
     * CreateHTLC defines a method for creating a HTLC
     * </pre>
     */
    public void createHTLC(irismod.htlc.Tx.MsgCreateHTLC request,
        io.grpc.stub.StreamObserver<irismod.htlc.Tx.MsgCreateHTLCResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateHTLCMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClaimHTLC defines a method for claiming a HTLC
     * </pre>
     */
    public void claimHTLC(irismod.htlc.Tx.MsgClaimHTLC request,
        io.grpc.stub.StreamObserver<irismod.htlc.Tx.MsgClaimHTLCResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClaimHTLCMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the HTLC Msg service
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
     * CreateHTLC defines a method for creating a HTLC
     * </pre>
     */
    public irismod.htlc.Tx.MsgCreateHTLCResponse createHTLC(irismod.htlc.Tx.MsgCreateHTLC request) {
      return blockingUnaryCall(
          getChannel(), getCreateHTLCMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClaimHTLC defines a method for claiming a HTLC
     * </pre>
     */
    public irismod.htlc.Tx.MsgClaimHTLCResponse claimHTLC(irismod.htlc.Tx.MsgClaimHTLC request) {
      return blockingUnaryCall(
          getChannel(), getClaimHTLCMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the HTLC Msg service
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
     * CreateHTLC defines a method for creating a HTLC
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.htlc.Tx.MsgCreateHTLCResponse> createHTLC(
        irismod.htlc.Tx.MsgCreateHTLC request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateHTLCMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClaimHTLC defines a method for claiming a HTLC
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.htlc.Tx.MsgClaimHTLCResponse> claimHTLC(
        irismod.htlc.Tx.MsgClaimHTLC request) {
      return futureUnaryCall(
          getChannel().newCall(getClaimHTLCMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_HTLC = 0;
  private static final int METHODID_CLAIM_HTLC = 1;

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
        case METHODID_CREATE_HTLC:
          serviceImpl.createHTLC((irismod.htlc.Tx.MsgCreateHTLC) request,
              (io.grpc.stub.StreamObserver<irismod.htlc.Tx.MsgCreateHTLCResponse>) responseObserver);
          break;
        case METHODID_CLAIM_HTLC:
          serviceImpl.claimHTLC((irismod.htlc.Tx.MsgClaimHTLC) request,
              (io.grpc.stub.StreamObserver<irismod.htlc.Tx.MsgClaimHTLCResponse>) responseObserver);
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
      return irismod.htlc.Tx.getDescriptor();
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
              .addMethod(getCreateHTLCMethod())
              .addMethod(getClaimHTLCMethod())
              .build();
        }
      }
    }
    return result;
  }
}
