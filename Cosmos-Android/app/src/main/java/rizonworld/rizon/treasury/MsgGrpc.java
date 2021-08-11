package rizonworld.rizon.treasury;

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
 * Msg defines the treasury Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: treasury/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "rizonworld.rizon.treasury.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<rizonworld.rizon.treasury.Tx.MsgMintRequest,
      rizonworld.rizon.treasury.Tx.MsgMintResponse> getMintMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Mint",
      requestType = rizonworld.rizon.treasury.Tx.MsgMintRequest.class,
      responseType = rizonworld.rizon.treasury.Tx.MsgMintResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rizonworld.rizon.treasury.Tx.MsgMintRequest,
      rizonworld.rizon.treasury.Tx.MsgMintResponse> getMintMethod() {
    io.grpc.MethodDescriptor<rizonworld.rizon.treasury.Tx.MsgMintRequest, rizonworld.rizon.treasury.Tx.MsgMintResponse> getMintMethod;
    if ((getMintMethod = MsgGrpc.getMintMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMintMethod = MsgGrpc.getMintMethod) == null) {
          MsgGrpc.getMintMethod = getMintMethod =
              io.grpc.MethodDescriptor.<rizonworld.rizon.treasury.Tx.MsgMintRequest, rizonworld.rizon.treasury.Tx.MsgMintResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Mint"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.treasury.Tx.MsgMintRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.treasury.Tx.MsgMintResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Mint"))
              .build();
        }
      }
    }
    return getMintMethod;
  }

  private static volatile io.grpc.MethodDescriptor<rizonworld.rizon.treasury.Tx.MsgBurnRequest,
      rizonworld.rizon.treasury.Tx.MsgBurnResponse> getBurnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Burn",
      requestType = rizonworld.rizon.treasury.Tx.MsgBurnRequest.class,
      responseType = rizonworld.rizon.treasury.Tx.MsgBurnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rizonworld.rizon.treasury.Tx.MsgBurnRequest,
      rizonworld.rizon.treasury.Tx.MsgBurnResponse> getBurnMethod() {
    io.grpc.MethodDescriptor<rizonworld.rizon.treasury.Tx.MsgBurnRequest, rizonworld.rizon.treasury.Tx.MsgBurnResponse> getBurnMethod;
    if ((getBurnMethod = MsgGrpc.getBurnMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBurnMethod = MsgGrpc.getBurnMethod) == null) {
          MsgGrpc.getBurnMethod = getBurnMethod =
              io.grpc.MethodDescriptor.<rizonworld.rizon.treasury.Tx.MsgBurnRequest, rizonworld.rizon.treasury.Tx.MsgBurnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Burn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.treasury.Tx.MsgBurnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rizonworld.rizon.treasury.Tx.MsgBurnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Burn"))
              .build();
        }
      }
    }
    return getBurnMethod;
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
   * Msg defines the treasury Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Mint defines a method for minting coins
     * </pre>
     */
    public void mint(rizonworld.rizon.treasury.Tx.MsgMintRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.Tx.MsgMintResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMintMethod(), responseObserver);
    }

    /**
     * <pre>
     * Burn defines a method for burning coins
     * </pre>
     */
    public void burn(rizonworld.rizon.treasury.Tx.MsgBurnRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.Tx.MsgBurnResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBurnMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMintMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rizonworld.rizon.treasury.Tx.MsgMintRequest,
                rizonworld.rizon.treasury.Tx.MsgMintResponse>(
                  this, METHODID_MINT)))
          .addMethod(
            getBurnMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rizonworld.rizon.treasury.Tx.MsgBurnRequest,
                rizonworld.rizon.treasury.Tx.MsgBurnResponse>(
                  this, METHODID_BURN)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the treasury Msg service.
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
     * Mint defines a method for minting coins
     * </pre>
     */
    public void mint(rizonworld.rizon.treasury.Tx.MsgMintRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.Tx.MsgMintResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMintMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Burn defines a method for burning coins
     * </pre>
     */
    public void burn(rizonworld.rizon.treasury.Tx.MsgBurnRequest request,
        io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.Tx.MsgBurnResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBurnMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the treasury Msg service.
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
     * Mint defines a method for minting coins
     * </pre>
     */
    public rizonworld.rizon.treasury.Tx.MsgMintResponse mint(rizonworld.rizon.treasury.Tx.MsgMintRequest request) {
      return blockingUnaryCall(
          getChannel(), getMintMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Burn defines a method for burning coins
     * </pre>
     */
    public rizonworld.rizon.treasury.Tx.MsgBurnResponse burn(rizonworld.rizon.treasury.Tx.MsgBurnRequest request) {
      return blockingUnaryCall(
          getChannel(), getBurnMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the treasury Msg service.
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
     * Mint defines a method for minting coins
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rizonworld.rizon.treasury.Tx.MsgMintResponse> mint(
        rizonworld.rizon.treasury.Tx.MsgMintRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMintMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Burn defines a method for burning coins
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rizonworld.rizon.treasury.Tx.MsgBurnResponse> burn(
        rizonworld.rizon.treasury.Tx.MsgBurnRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBurnMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MINT = 0;
  private static final int METHODID_BURN = 1;

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
        case METHODID_MINT:
          serviceImpl.mint((rizonworld.rizon.treasury.Tx.MsgMintRequest) request,
              (io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.Tx.MsgMintResponse>) responseObserver);
          break;
        case METHODID_BURN:
          serviceImpl.burn((rizonworld.rizon.treasury.Tx.MsgBurnRequest) request,
              (io.grpc.stub.StreamObserver<rizonworld.rizon.treasury.Tx.MsgBurnResponse>) responseObserver);
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
      return rizonworld.rizon.treasury.Tx.getDescriptor();
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
              .addMethod(getMintMethod())
              .addMethod(getBurnMethod())
              .build();
        }
      }
    }
    return result;
  }
}
