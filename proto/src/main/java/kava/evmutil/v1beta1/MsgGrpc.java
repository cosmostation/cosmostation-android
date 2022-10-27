package kava.evmutil.v1beta1;

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
 * Msg defines the evmutil Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/evmutil/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.evmutil.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20,
      kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20Response> getConvertCoinToERC20Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConvertCoinToERC20",
      requestType = kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20.class,
      responseType = kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20,
      kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20Response> getConvertCoinToERC20Method() {
    io.grpc.MethodDescriptor<kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20, kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20Response> getConvertCoinToERC20Method;
    if ((getConvertCoinToERC20Method = MsgGrpc.getConvertCoinToERC20Method) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConvertCoinToERC20Method = MsgGrpc.getConvertCoinToERC20Method) == null) {
          MsgGrpc.getConvertCoinToERC20Method = getConvertCoinToERC20Method =
              io.grpc.MethodDescriptor.<kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20, kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConvertCoinToERC20"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20Response.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConvertCoinToERC20"))
              .build();
        }
      }
    }
    return getConvertCoinToERC20Method;
  }

  private static volatile io.grpc.MethodDescriptor<kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoin,
      kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoinResponse> getConvertERC20ToCoinMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConvertERC20ToCoin",
      requestType = kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoin.class,
      responseType = kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoinResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoin,
      kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoinResponse> getConvertERC20ToCoinMethod() {
    io.grpc.MethodDescriptor<kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoin, kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoinResponse> getConvertERC20ToCoinMethod;
    if ((getConvertERC20ToCoinMethod = MsgGrpc.getConvertERC20ToCoinMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConvertERC20ToCoinMethod = MsgGrpc.getConvertERC20ToCoinMethod) == null) {
          MsgGrpc.getConvertERC20ToCoinMethod = getConvertERC20ToCoinMethod =
              io.grpc.MethodDescriptor.<kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoin, kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoinResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConvertERC20ToCoin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoin.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoinResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConvertERC20ToCoin"))
              .build();
        }
      }
    }
    return getConvertERC20ToCoinMethod;
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
   * Msg defines the evmutil Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * ConvertCoinToERC20 defines a method for converting sdk.Coin to Kava ERC20.
     * </pre>
     */
    public void convertCoinToERC20(kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20 request,
        io.grpc.stub.StreamObserver<kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20Response> responseObserver) {
      asyncUnimplementedUnaryCall(getConvertCoinToERC20Method(), responseObserver);
    }

    /**
     * <pre>
     * ConvertERC20ToCoin defines a method for converting Kava ERC20 to sdk.Coin.
     * </pre>
     */
    public void convertERC20ToCoin(kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoin request,
        io.grpc.stub.StreamObserver<kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoinResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConvertERC20ToCoinMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getConvertCoinToERC20Method(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20,
                kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20Response>(
                  this, METHODID_CONVERT_COIN_TO_ERC20)))
          .addMethod(
            getConvertERC20ToCoinMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoin,
                kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoinResponse>(
                  this, METHODID_CONVERT_ERC20TO_COIN)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the evmutil Msg service.
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
     * ConvertCoinToERC20 defines a method for converting sdk.Coin to Kava ERC20.
     * </pre>
     */
    public void convertCoinToERC20(kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20 request,
        io.grpc.stub.StreamObserver<kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConvertCoinToERC20Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConvertERC20ToCoin defines a method for converting Kava ERC20 to sdk.Coin.
     * </pre>
     */
    public void convertERC20ToCoin(kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoin request,
        io.grpc.stub.StreamObserver<kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoinResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConvertERC20ToCoinMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the evmutil Msg service.
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
     * ConvertCoinToERC20 defines a method for converting sdk.Coin to Kava ERC20.
     * </pre>
     */
    public kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20Response convertCoinToERC20(kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20 request) {
      return blockingUnaryCall(
          getChannel(), getConvertCoinToERC20Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConvertERC20ToCoin defines a method for converting Kava ERC20 to sdk.Coin.
     * </pre>
     */
    public kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoinResponse convertERC20ToCoin(kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoin request) {
      return blockingUnaryCall(
          getChannel(), getConvertERC20ToCoinMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the evmutil Msg service.
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
     * ConvertCoinToERC20 defines a method for converting sdk.Coin to Kava ERC20.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20Response> convertCoinToERC20(
        kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20 request) {
      return futureUnaryCall(
          getChannel().newCall(getConvertCoinToERC20Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConvertERC20ToCoin defines a method for converting Kava ERC20 to sdk.Coin.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoinResponse> convertERC20ToCoin(
        kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoin request) {
      return futureUnaryCall(
          getChannel().newCall(getConvertERC20ToCoinMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CONVERT_COIN_TO_ERC20 = 0;
  private static final int METHODID_CONVERT_ERC20TO_COIN = 1;

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
        case METHODID_CONVERT_COIN_TO_ERC20:
          serviceImpl.convertCoinToERC20((kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20) request,
              (io.grpc.stub.StreamObserver<kava.evmutil.v1beta1.Tx.MsgConvertCoinToERC20Response>) responseObserver);
          break;
        case METHODID_CONVERT_ERC20TO_COIN:
          serviceImpl.convertERC20ToCoin((kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoin) request,
              (io.grpc.stub.StreamObserver<kava.evmutil.v1beta1.Tx.MsgConvertERC20ToCoinResponse>) responseObserver);
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
      return kava.evmutil.v1beta1.Tx.getDescriptor();
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
              .addMethod(getConvertCoinToERC20Method())
              .addMethod(getConvertERC20ToCoinMethod())
              .build();
        }
      }
    }
    return result;
  }
}
