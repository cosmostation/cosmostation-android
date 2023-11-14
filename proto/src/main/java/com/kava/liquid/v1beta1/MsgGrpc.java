package com.kava.liquid.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the liquid Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: kava/liquid/v1beta1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.liquid.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kava.liquid.v1beta1.TxProto.MsgMintDerivative,
      com.kava.liquid.v1beta1.TxProto.MsgMintDerivativeResponse> getMintDerivativeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MintDerivative",
      requestType = com.kava.liquid.v1beta1.TxProto.MsgMintDerivative.class,
      responseType = com.kava.liquid.v1beta1.TxProto.MsgMintDerivativeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.liquid.v1beta1.TxProto.MsgMintDerivative,
      com.kava.liquid.v1beta1.TxProto.MsgMintDerivativeResponse> getMintDerivativeMethod() {
    io.grpc.MethodDescriptor<com.kava.liquid.v1beta1.TxProto.MsgMintDerivative, com.kava.liquid.v1beta1.TxProto.MsgMintDerivativeResponse> getMintDerivativeMethod;
    if ((getMintDerivativeMethod = MsgGrpc.getMintDerivativeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMintDerivativeMethod = MsgGrpc.getMintDerivativeMethod) == null) {
          MsgGrpc.getMintDerivativeMethod = getMintDerivativeMethod =
              io.grpc.MethodDescriptor.<com.kava.liquid.v1beta1.TxProto.MsgMintDerivative, com.kava.liquid.v1beta1.TxProto.MsgMintDerivativeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MintDerivative"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.liquid.v1beta1.TxProto.MsgMintDerivative.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.liquid.v1beta1.TxProto.MsgMintDerivativeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("MintDerivative"))
              .build();
        }
      }
    }
    return getMintDerivativeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.liquid.v1beta1.TxProto.MsgBurnDerivative,
      com.kava.liquid.v1beta1.TxProto.MsgBurnDerivativeResponse> getBurnDerivativeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BurnDerivative",
      requestType = com.kava.liquid.v1beta1.TxProto.MsgBurnDerivative.class,
      responseType = com.kava.liquid.v1beta1.TxProto.MsgBurnDerivativeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.liquid.v1beta1.TxProto.MsgBurnDerivative,
      com.kava.liquid.v1beta1.TxProto.MsgBurnDerivativeResponse> getBurnDerivativeMethod() {
    io.grpc.MethodDescriptor<com.kava.liquid.v1beta1.TxProto.MsgBurnDerivative, com.kava.liquid.v1beta1.TxProto.MsgBurnDerivativeResponse> getBurnDerivativeMethod;
    if ((getBurnDerivativeMethod = MsgGrpc.getBurnDerivativeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBurnDerivativeMethod = MsgGrpc.getBurnDerivativeMethod) == null) {
          MsgGrpc.getBurnDerivativeMethod = getBurnDerivativeMethod =
              io.grpc.MethodDescriptor.<com.kava.liquid.v1beta1.TxProto.MsgBurnDerivative, com.kava.liquid.v1beta1.TxProto.MsgBurnDerivativeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BurnDerivative"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.liquid.v1beta1.TxProto.MsgBurnDerivative.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.liquid.v1beta1.TxProto.MsgBurnDerivativeResponse.getDefaultInstance()))
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
  public interface AsyncService {

    /**
     * <pre>
     * MintDerivative defines a method for converting a delegation into staking deriviatives.
     * </pre>
     */
    default void mintDerivative(com.kava.liquid.v1beta1.TxProto.MsgMintDerivative request,
        io.grpc.stub.StreamObserver<com.kava.liquid.v1beta1.TxProto.MsgMintDerivativeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMintDerivativeMethod(), responseObserver);
    }

    /**
     * <pre>
     * BurnDerivative defines a method for converting staking deriviatives into a delegation.
     * </pre>
     */
    default void burnDerivative(com.kava.liquid.v1beta1.TxProto.MsgBurnDerivative request,
        io.grpc.stub.StreamObserver<com.kava.liquid.v1beta1.TxProto.MsgBurnDerivativeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBurnDerivativeMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the liquid Msg service.
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
   * Msg defines the liquid Msg service.
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
     * MintDerivative defines a method for converting a delegation into staking deriviatives.
     * </pre>
     */
    public void mintDerivative(com.kava.liquid.v1beta1.TxProto.MsgMintDerivative request,
        io.grpc.stub.StreamObserver<com.kava.liquid.v1beta1.TxProto.MsgMintDerivativeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMintDerivativeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BurnDerivative defines a method for converting staking deriviatives into a delegation.
     * </pre>
     */
    public void burnDerivative(com.kava.liquid.v1beta1.TxProto.MsgBurnDerivative request,
        io.grpc.stub.StreamObserver<com.kava.liquid.v1beta1.TxProto.MsgBurnDerivativeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBurnDerivativeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the liquid Msg service.
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
     * MintDerivative defines a method for converting a delegation into staking deriviatives.
     * </pre>
     */
    public com.kava.liquid.v1beta1.TxProto.MsgMintDerivativeResponse mintDerivative(com.kava.liquid.v1beta1.TxProto.MsgMintDerivative request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMintDerivativeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BurnDerivative defines a method for converting staking deriviatives into a delegation.
     * </pre>
     */
    public com.kava.liquid.v1beta1.TxProto.MsgBurnDerivativeResponse burnDerivative(com.kava.liquid.v1beta1.TxProto.MsgBurnDerivative request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBurnDerivativeMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the liquid Msg service.
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
     * MintDerivative defines a method for converting a delegation into staking deriviatives.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.liquid.v1beta1.TxProto.MsgMintDerivativeResponse> mintDerivative(
        com.kava.liquid.v1beta1.TxProto.MsgMintDerivative request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMintDerivativeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BurnDerivative defines a method for converting staking deriviatives into a delegation.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.liquid.v1beta1.TxProto.MsgBurnDerivativeResponse> burnDerivative(
        com.kava.liquid.v1beta1.TxProto.MsgBurnDerivative request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
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
        case METHODID_MINT_DERIVATIVE:
          serviceImpl.mintDerivative((com.kava.liquid.v1beta1.TxProto.MsgMintDerivative) request,
              (io.grpc.stub.StreamObserver<com.kava.liquid.v1beta1.TxProto.MsgMintDerivativeResponse>) responseObserver);
          break;
        case METHODID_BURN_DERIVATIVE:
          serviceImpl.burnDerivative((com.kava.liquid.v1beta1.TxProto.MsgBurnDerivative) request,
              (io.grpc.stub.StreamObserver<com.kava.liquid.v1beta1.TxProto.MsgBurnDerivativeResponse>) responseObserver);
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
          getMintDerivativeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.liquid.v1beta1.TxProto.MsgMintDerivative,
              com.kava.liquid.v1beta1.TxProto.MsgMintDerivativeResponse>(
                service, METHODID_MINT_DERIVATIVE)))
        .addMethod(
          getBurnDerivativeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.liquid.v1beta1.TxProto.MsgBurnDerivative,
              com.kava.liquid.v1beta1.TxProto.MsgBurnDerivativeResponse>(
                service, METHODID_BURN_DERIVATIVE)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kava.liquid.v1beta1.TxProto.getDescriptor();
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
