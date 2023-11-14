package com.kava.evmutil.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the evmutil Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: kava/evmutil/v1beta1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.evmutil.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20,
      com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20Response> getConvertCoinToERC20Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConvertCoinToERC20",
      requestType = com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20.class,
      responseType = com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20,
      com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20Response> getConvertCoinToERC20Method() {
    io.grpc.MethodDescriptor<com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20, com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20Response> getConvertCoinToERC20Method;
    if ((getConvertCoinToERC20Method = MsgGrpc.getConvertCoinToERC20Method) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConvertCoinToERC20Method = MsgGrpc.getConvertCoinToERC20Method) == null) {
          MsgGrpc.getConvertCoinToERC20Method = getConvertCoinToERC20Method =
              io.grpc.MethodDescriptor.<com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20, com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConvertCoinToERC20"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20Response.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConvertCoinToERC20"))
              .build();
        }
      }
    }
    return getConvertCoinToERC20Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoin,
      com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoinResponse> getConvertERC20ToCoinMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConvertERC20ToCoin",
      requestType = com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoin.class,
      responseType = com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoinResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoin,
      com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoinResponse> getConvertERC20ToCoinMethod() {
    io.grpc.MethodDescriptor<com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoin, com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoinResponse> getConvertERC20ToCoinMethod;
    if ((getConvertERC20ToCoinMethod = MsgGrpc.getConvertERC20ToCoinMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConvertERC20ToCoinMethod = MsgGrpc.getConvertERC20ToCoinMethod) == null) {
          MsgGrpc.getConvertERC20ToCoinMethod = getConvertERC20ToCoinMethod =
              io.grpc.MethodDescriptor.<com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoin, com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoinResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConvertERC20ToCoin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoin.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoinResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConvertERC20ToCoin"))
              .build();
        }
      }
    }
    return getConvertERC20ToCoinMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20,
      com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20Response> getConvertCosmosCoinToERC20Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConvertCosmosCoinToERC20",
      requestType = com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20.class,
      responseType = com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20,
      com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20Response> getConvertCosmosCoinToERC20Method() {
    io.grpc.MethodDescriptor<com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20, com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20Response> getConvertCosmosCoinToERC20Method;
    if ((getConvertCosmosCoinToERC20Method = MsgGrpc.getConvertCosmosCoinToERC20Method) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConvertCosmosCoinToERC20Method = MsgGrpc.getConvertCosmosCoinToERC20Method) == null) {
          MsgGrpc.getConvertCosmosCoinToERC20Method = getConvertCosmosCoinToERC20Method =
              io.grpc.MethodDescriptor.<com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20, com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConvertCosmosCoinToERC20"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20Response.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConvertCosmosCoinToERC20"))
              .build();
        }
      }
    }
    return getConvertCosmosCoinToERC20Method;
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
  public interface AsyncService {

    /**
     * <pre>
     * ConvertCoinToERC20 defines a method for converting sdk.Coin to Kava ERC20.
     * </pre>
     */
    default void convertCoinToERC20(com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20 request,
        io.grpc.stub.StreamObserver<com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConvertCoinToERC20Method(), responseObserver);
    }

    /**
     * <pre>
     * ConvertERC20ToCoin defines a method for converting Kava ERC20 to sdk.Coin.
     * </pre>
     */
    default void convertERC20ToCoin(com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoin request,
        io.grpc.stub.StreamObserver<com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoinResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConvertERC20ToCoinMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConvertCosmosCoinToERC20 defines a method for converting a cosmos sdk.Coin to an ERC20.
     * </pre>
     */
    default void convertCosmosCoinToERC20(com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20 request,
        io.grpc.stub.StreamObserver<com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConvertCosmosCoinToERC20Method(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the evmutil Msg service.
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
   * Msg defines the evmutil Msg service.
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
     * ConvertCoinToERC20 defines a method for converting sdk.Coin to Kava ERC20.
     * </pre>
     */
    public void convertCoinToERC20(com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20 request,
        io.grpc.stub.StreamObserver<com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConvertCoinToERC20Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConvertERC20ToCoin defines a method for converting Kava ERC20 to sdk.Coin.
     * </pre>
     */
    public void convertERC20ToCoin(com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoin request,
        io.grpc.stub.StreamObserver<com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoinResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConvertERC20ToCoinMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConvertCosmosCoinToERC20 defines a method for converting a cosmos sdk.Coin to an ERC20.
     * </pre>
     */
    public void convertCosmosCoinToERC20(com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20 request,
        io.grpc.stub.StreamObserver<com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConvertCosmosCoinToERC20Method(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the evmutil Msg service.
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
     * ConvertCoinToERC20 defines a method for converting sdk.Coin to Kava ERC20.
     * </pre>
     */
    public com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20Response convertCoinToERC20(com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20 request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConvertCoinToERC20Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConvertERC20ToCoin defines a method for converting Kava ERC20 to sdk.Coin.
     * </pre>
     */
    public com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoinResponse convertERC20ToCoin(com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoin request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConvertERC20ToCoinMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConvertCosmosCoinToERC20 defines a method for converting a cosmos sdk.Coin to an ERC20.
     * </pre>
     */
    public com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20Response convertCosmosCoinToERC20(com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20 request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConvertCosmosCoinToERC20Method(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the evmutil Msg service.
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
     * ConvertCoinToERC20 defines a method for converting sdk.Coin to Kava ERC20.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20Response> convertCoinToERC20(
        com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20 request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConvertCoinToERC20Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConvertERC20ToCoin defines a method for converting Kava ERC20 to sdk.Coin.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoinResponse> convertERC20ToCoin(
        com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoin request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConvertERC20ToCoinMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConvertCosmosCoinToERC20 defines a method for converting a cosmos sdk.Coin to an ERC20.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20Response> convertCosmosCoinToERC20(
        com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20 request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConvertCosmosCoinToERC20Method(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CONVERT_COIN_TO_ERC20 = 0;
  private static final int METHODID_CONVERT_ERC20TO_COIN = 1;
  private static final int METHODID_CONVERT_COSMOS_COIN_TO_ERC20 = 2;

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
        case METHODID_CONVERT_COIN_TO_ERC20:
          serviceImpl.convertCoinToERC20((com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20) request,
              (io.grpc.stub.StreamObserver<com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20Response>) responseObserver);
          break;
        case METHODID_CONVERT_ERC20TO_COIN:
          serviceImpl.convertERC20ToCoin((com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoin) request,
              (io.grpc.stub.StreamObserver<com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoinResponse>) responseObserver);
          break;
        case METHODID_CONVERT_COSMOS_COIN_TO_ERC20:
          serviceImpl.convertCosmosCoinToERC20((com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20) request,
              (io.grpc.stub.StreamObserver<com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20Response>) responseObserver);
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
          getConvertCoinToERC20Method(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20,
              com.kava.evmutil.v1beta1.TxProto.MsgConvertCoinToERC20Response>(
                service, METHODID_CONVERT_COIN_TO_ERC20)))
        .addMethod(
          getConvertERC20ToCoinMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoin,
              com.kava.evmutil.v1beta1.TxProto.MsgConvertERC20ToCoinResponse>(
                service, METHODID_CONVERT_ERC20TO_COIN)))
        .addMethod(
          getConvertCosmosCoinToERC20Method(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20,
              com.kava.evmutil.v1beta1.TxProto.MsgConvertCosmosCoinToERC20Response>(
                service, METHODID_CONVERT_COSMOS_COIN_TO_ERC20)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kava.evmutil.v1beta1.TxProto.getDescriptor();
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
              .addMethod(getConvertCosmosCoinToERC20Method())
              .build();
        }
      }
    }
    return result;
  }
}
