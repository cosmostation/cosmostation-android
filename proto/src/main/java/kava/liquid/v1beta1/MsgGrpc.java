package kava.liquid.v1beta1;

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
 * Msg defines the liquid Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/liquid/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.liquid.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.liquid.v1beta1.Tx.MsgMintDerivative,
      kava.liquid.v1beta1.Tx.MsgMintDerivativeResponse> getMintDerivativeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MintDerivative",
      requestType = kava.liquid.v1beta1.Tx.MsgMintDerivative.class,
      responseType = kava.liquid.v1beta1.Tx.MsgMintDerivativeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.liquid.v1beta1.Tx.MsgMintDerivative,
      kava.liquid.v1beta1.Tx.MsgMintDerivativeResponse> getMintDerivativeMethod() {
    io.grpc.MethodDescriptor<kava.liquid.v1beta1.Tx.MsgMintDerivative, kava.liquid.v1beta1.Tx.MsgMintDerivativeResponse> getMintDerivativeMethod;
    if ((getMintDerivativeMethod = MsgGrpc.getMintDerivativeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMintDerivativeMethod = MsgGrpc.getMintDerivativeMethod) == null) {
          MsgGrpc.getMintDerivativeMethod = getMintDerivativeMethod =
              io.grpc.MethodDescriptor.<kava.liquid.v1beta1.Tx.MsgMintDerivative, kava.liquid.v1beta1.Tx.MsgMintDerivativeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MintDerivative"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.liquid.v1beta1.Tx.MsgMintDerivative.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.liquid.v1beta1.Tx.MsgMintDerivativeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("MintDerivative"))
              .build();
        }
      }
    }
    return getMintDerivativeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.liquid.v1beta1.Tx.MsgBurnDerivative,
      kava.liquid.v1beta1.Tx.MsgBurnDerivativeResponse> getBurnDerivativeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BurnDerivative",
      requestType = kava.liquid.v1beta1.Tx.MsgBurnDerivative.class,
      responseType = kava.liquid.v1beta1.Tx.MsgBurnDerivativeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.liquid.v1beta1.Tx.MsgBurnDerivative,
      kava.liquid.v1beta1.Tx.MsgBurnDerivativeResponse> getBurnDerivativeMethod() {
    io.grpc.MethodDescriptor<kava.liquid.v1beta1.Tx.MsgBurnDerivative, kava.liquid.v1beta1.Tx.MsgBurnDerivativeResponse> getBurnDerivativeMethod;
    if ((getBurnDerivativeMethod = MsgGrpc.getBurnDerivativeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBurnDerivativeMethod = MsgGrpc.getBurnDerivativeMethod) == null) {
          MsgGrpc.getBurnDerivativeMethod = getBurnDerivativeMethod =
              io.grpc.MethodDescriptor.<kava.liquid.v1beta1.Tx.MsgBurnDerivative, kava.liquid.v1beta1.Tx.MsgBurnDerivativeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BurnDerivative"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.liquid.v1beta1.Tx.MsgBurnDerivative.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.liquid.v1beta1.Tx.MsgBurnDerivativeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BurnDerivative"))
              .build();
        }
      }
    }
    return getBurnDerivativeMethod;
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
   * Msg defines the liquid Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * MintDerivative defines a method for converting a delegation into staking deriviatives.
     * </pre>
     */
    public void mintDerivative(kava.liquid.v1beta1.Tx.MsgMintDerivative request,
        io.grpc.stub.StreamObserver<kava.liquid.v1beta1.Tx.MsgMintDerivativeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMintDerivativeMethod(), responseObserver);
    }

    /**
     * <pre>
     * BurnDerivative defines a method for converting staking deriviatives into a delegation.
     * </pre>
     */
    public void burnDerivative(kava.liquid.v1beta1.Tx.MsgBurnDerivative request,
        io.grpc.stub.StreamObserver<kava.liquid.v1beta1.Tx.MsgBurnDerivativeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBurnDerivativeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMintDerivativeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.liquid.v1beta1.Tx.MsgMintDerivative,
                kava.liquid.v1beta1.Tx.MsgMintDerivativeResponse>(
                  this, METHODID_MINT_DERIVATIVE)))
          .addMethod(
            getBurnDerivativeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.liquid.v1beta1.Tx.MsgBurnDerivative,
                kava.liquid.v1beta1.Tx.MsgBurnDerivativeResponse>(
                  this, METHODID_BURN_DERIVATIVE)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the liquid Msg service.
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
     * MintDerivative defines a method for converting a delegation into staking deriviatives.
     * </pre>
     */
    public void mintDerivative(kava.liquid.v1beta1.Tx.MsgMintDerivative request,
        io.grpc.stub.StreamObserver<kava.liquid.v1beta1.Tx.MsgMintDerivativeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMintDerivativeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BurnDerivative defines a method for converting staking deriviatives into a delegation.
     * </pre>
     */
    public void burnDerivative(kava.liquid.v1beta1.Tx.MsgBurnDerivative request,
        io.grpc.stub.StreamObserver<kava.liquid.v1beta1.Tx.MsgBurnDerivativeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBurnDerivativeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the liquid Msg service.
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
     * MintDerivative defines a method for converting a delegation into staking deriviatives.
     * </pre>
     */
    public kava.liquid.v1beta1.Tx.MsgMintDerivativeResponse mintDerivative(kava.liquid.v1beta1.Tx.MsgMintDerivative request) {
      return blockingUnaryCall(
          getChannel(), getMintDerivativeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BurnDerivative defines a method for converting staking deriviatives into a delegation.
     * </pre>
     */
    public kava.liquid.v1beta1.Tx.MsgBurnDerivativeResponse burnDerivative(kava.liquid.v1beta1.Tx.MsgBurnDerivative request) {
      return blockingUnaryCall(
          getChannel(), getBurnDerivativeMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the liquid Msg service.
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
     * MintDerivative defines a method for converting a delegation into staking deriviatives.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.liquid.v1beta1.Tx.MsgMintDerivativeResponse> mintDerivative(
        kava.liquid.v1beta1.Tx.MsgMintDerivative request) {
      return futureUnaryCall(
          getChannel().newCall(getMintDerivativeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BurnDerivative defines a method for converting staking deriviatives into a delegation.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.liquid.v1beta1.Tx.MsgBurnDerivativeResponse> burnDerivative(
        kava.liquid.v1beta1.Tx.MsgBurnDerivative request) {
      return futureUnaryCall(
          getChannel().newCall(getBurnDerivativeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MINT_DERIVATIVE = 0;
  private static final int METHODID_BURN_DERIVATIVE = 1;

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
        case METHODID_MINT_DERIVATIVE:
          serviceImpl.mintDerivative((kava.liquid.v1beta1.Tx.MsgMintDerivative) request,
              (io.grpc.stub.StreamObserver<kava.liquid.v1beta1.Tx.MsgMintDerivativeResponse>) responseObserver);
          break;
        case METHODID_BURN_DERIVATIVE:
          serviceImpl.burnDerivative((kava.liquid.v1beta1.Tx.MsgBurnDerivative) request,
              (io.grpc.stub.StreamObserver<kava.liquid.v1beta1.Tx.MsgBurnDerivativeResponse>) responseObserver);
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
      return kava.liquid.v1beta1.Tx.getDescriptor();
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
              .addMethod(getMintDerivativeMethod())
              .addMethod(getBurnDerivativeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
