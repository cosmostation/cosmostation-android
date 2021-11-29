package em.issuer.v1;

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
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: em/issuer/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "em.issuer.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<em.issuer.v1.Tx.MsgIncreaseMintable,
      em.issuer.v1.Tx.MsgIncreaseMintableResponse> getIncreaseMintableMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IncreaseMintable",
      requestType = em.issuer.v1.Tx.MsgIncreaseMintable.class,
      responseType = em.issuer.v1.Tx.MsgIncreaseMintableResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.issuer.v1.Tx.MsgIncreaseMintable,
      em.issuer.v1.Tx.MsgIncreaseMintableResponse> getIncreaseMintableMethod() {
    io.grpc.MethodDescriptor<em.issuer.v1.Tx.MsgIncreaseMintable, em.issuer.v1.Tx.MsgIncreaseMintableResponse> getIncreaseMintableMethod;
    if ((getIncreaseMintableMethod = MsgGrpc.getIncreaseMintableMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getIncreaseMintableMethod = MsgGrpc.getIncreaseMintableMethod) == null) {
          MsgGrpc.getIncreaseMintableMethod = getIncreaseMintableMethod =
              io.grpc.MethodDescriptor.<em.issuer.v1.Tx.MsgIncreaseMintable, em.issuer.v1.Tx.MsgIncreaseMintableResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IncreaseMintable"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.issuer.v1.Tx.MsgIncreaseMintable.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.issuer.v1.Tx.MsgIncreaseMintableResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("IncreaseMintable"))
              .build();
        }
      }
    }
    return getIncreaseMintableMethod;
  }

  private static volatile io.grpc.MethodDescriptor<em.issuer.v1.Tx.MsgDecreaseMintable,
      em.issuer.v1.Tx.MsgDecreaseMintableResponse> getDecreaseMintableMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DecreaseMintable",
      requestType = em.issuer.v1.Tx.MsgDecreaseMintable.class,
      responseType = em.issuer.v1.Tx.MsgDecreaseMintableResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.issuer.v1.Tx.MsgDecreaseMintable,
      em.issuer.v1.Tx.MsgDecreaseMintableResponse> getDecreaseMintableMethod() {
    io.grpc.MethodDescriptor<em.issuer.v1.Tx.MsgDecreaseMintable, em.issuer.v1.Tx.MsgDecreaseMintableResponse> getDecreaseMintableMethod;
    if ((getDecreaseMintableMethod = MsgGrpc.getDecreaseMintableMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDecreaseMintableMethod = MsgGrpc.getDecreaseMintableMethod) == null) {
          MsgGrpc.getDecreaseMintableMethod = getDecreaseMintableMethod =
              io.grpc.MethodDescriptor.<em.issuer.v1.Tx.MsgDecreaseMintable, em.issuer.v1.Tx.MsgDecreaseMintableResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DecreaseMintable"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.issuer.v1.Tx.MsgDecreaseMintable.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.issuer.v1.Tx.MsgDecreaseMintableResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DecreaseMintable"))
              .build();
        }
      }
    }
    return getDecreaseMintableMethod;
  }

  private static volatile io.grpc.MethodDescriptor<em.issuer.v1.Tx.MsgRevokeLiquidityProvider,
      em.issuer.v1.Tx.MsgRevokeLiquidityProviderResponse> getRevokeLiquidityProviderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RevokeLiquidityProvider",
      requestType = em.issuer.v1.Tx.MsgRevokeLiquidityProvider.class,
      responseType = em.issuer.v1.Tx.MsgRevokeLiquidityProviderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.issuer.v1.Tx.MsgRevokeLiquidityProvider,
      em.issuer.v1.Tx.MsgRevokeLiquidityProviderResponse> getRevokeLiquidityProviderMethod() {
    io.grpc.MethodDescriptor<em.issuer.v1.Tx.MsgRevokeLiquidityProvider, em.issuer.v1.Tx.MsgRevokeLiquidityProviderResponse> getRevokeLiquidityProviderMethod;
    if ((getRevokeLiquidityProviderMethod = MsgGrpc.getRevokeLiquidityProviderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRevokeLiquidityProviderMethod = MsgGrpc.getRevokeLiquidityProviderMethod) == null) {
          MsgGrpc.getRevokeLiquidityProviderMethod = getRevokeLiquidityProviderMethod =
              io.grpc.MethodDescriptor.<em.issuer.v1.Tx.MsgRevokeLiquidityProvider, em.issuer.v1.Tx.MsgRevokeLiquidityProviderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RevokeLiquidityProvider"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.issuer.v1.Tx.MsgRevokeLiquidityProvider.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.issuer.v1.Tx.MsgRevokeLiquidityProviderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RevokeLiquidityProvider"))
              .build();
        }
      }
    }
    return getRevokeLiquidityProviderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<em.issuer.v1.Tx.MsgSetInflation,
      em.issuer.v1.Tx.MsgSetInflationResponse> getSetInflationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetInflation",
      requestType = em.issuer.v1.Tx.MsgSetInflation.class,
      responseType = em.issuer.v1.Tx.MsgSetInflationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.issuer.v1.Tx.MsgSetInflation,
      em.issuer.v1.Tx.MsgSetInflationResponse> getSetInflationMethod() {
    io.grpc.MethodDescriptor<em.issuer.v1.Tx.MsgSetInflation, em.issuer.v1.Tx.MsgSetInflationResponse> getSetInflationMethod;
    if ((getSetInflationMethod = MsgGrpc.getSetInflationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetInflationMethod = MsgGrpc.getSetInflationMethod) == null) {
          MsgGrpc.getSetInflationMethod = getSetInflationMethod =
              io.grpc.MethodDescriptor.<em.issuer.v1.Tx.MsgSetInflation, em.issuer.v1.Tx.MsgSetInflationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetInflation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.issuer.v1.Tx.MsgSetInflation.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.issuer.v1.Tx.MsgSetInflationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetInflation"))
              .build();
        }
      }
    }
    return getSetInflationMethod;
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
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     */
    public void increaseMintable(em.issuer.v1.Tx.MsgIncreaseMintable request,
        io.grpc.stub.StreamObserver<em.issuer.v1.Tx.MsgIncreaseMintableResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getIncreaseMintableMethod(), responseObserver);
    }

    /**
     */
    public void decreaseMintable(em.issuer.v1.Tx.MsgDecreaseMintable request,
        io.grpc.stub.StreamObserver<em.issuer.v1.Tx.MsgDecreaseMintableResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDecreaseMintableMethod(), responseObserver);
    }

    /**
     */
    public void revokeLiquidityProvider(em.issuer.v1.Tx.MsgRevokeLiquidityProvider request,
        io.grpc.stub.StreamObserver<em.issuer.v1.Tx.MsgRevokeLiquidityProviderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRevokeLiquidityProviderMethod(), responseObserver);
    }

    /**
     */
    public void setInflation(em.issuer.v1.Tx.MsgSetInflation request,
        io.grpc.stub.StreamObserver<em.issuer.v1.Tx.MsgSetInflationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetInflationMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getIncreaseMintableMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.issuer.v1.Tx.MsgIncreaseMintable,
                em.issuer.v1.Tx.MsgIncreaseMintableResponse>(
                  this, METHODID_INCREASE_MINTABLE)))
          .addMethod(
            getDecreaseMintableMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.issuer.v1.Tx.MsgDecreaseMintable,
                em.issuer.v1.Tx.MsgDecreaseMintableResponse>(
                  this, METHODID_DECREASE_MINTABLE)))
          .addMethod(
            getRevokeLiquidityProviderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.issuer.v1.Tx.MsgRevokeLiquidityProvider,
                em.issuer.v1.Tx.MsgRevokeLiquidityProviderResponse>(
                  this, METHODID_REVOKE_LIQUIDITY_PROVIDER)))
          .addMethod(
            getSetInflationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.issuer.v1.Tx.MsgSetInflation,
                em.issuer.v1.Tx.MsgSetInflationResponse>(
                  this, METHODID_SET_INFLATION)))
          .build();
    }
  }

  /**
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
    public void increaseMintable(em.issuer.v1.Tx.MsgIncreaseMintable request,
        io.grpc.stub.StreamObserver<em.issuer.v1.Tx.MsgIncreaseMintableResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIncreaseMintableMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void decreaseMintable(em.issuer.v1.Tx.MsgDecreaseMintable request,
        io.grpc.stub.StreamObserver<em.issuer.v1.Tx.MsgDecreaseMintableResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDecreaseMintableMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void revokeLiquidityProvider(em.issuer.v1.Tx.MsgRevokeLiquidityProvider request,
        io.grpc.stub.StreamObserver<em.issuer.v1.Tx.MsgRevokeLiquidityProviderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRevokeLiquidityProviderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setInflation(em.issuer.v1.Tx.MsgSetInflation request,
        io.grpc.stub.StreamObserver<em.issuer.v1.Tx.MsgSetInflationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetInflationMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
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
    public em.issuer.v1.Tx.MsgIncreaseMintableResponse increaseMintable(em.issuer.v1.Tx.MsgIncreaseMintable request) {
      return blockingUnaryCall(
          getChannel(), getIncreaseMintableMethod(), getCallOptions(), request);
    }

    /**
     */
    public em.issuer.v1.Tx.MsgDecreaseMintableResponse decreaseMintable(em.issuer.v1.Tx.MsgDecreaseMintable request) {
      return blockingUnaryCall(
          getChannel(), getDecreaseMintableMethod(), getCallOptions(), request);
    }

    /**
     */
    public em.issuer.v1.Tx.MsgRevokeLiquidityProviderResponse revokeLiquidityProvider(em.issuer.v1.Tx.MsgRevokeLiquidityProvider request) {
      return blockingUnaryCall(
          getChannel(), getRevokeLiquidityProviderMethod(), getCallOptions(), request);
    }

    /**
     */
    public em.issuer.v1.Tx.MsgSetInflationResponse setInflation(em.issuer.v1.Tx.MsgSetInflation request) {
      return blockingUnaryCall(
          getChannel(), getSetInflationMethod(), getCallOptions(), request);
    }
  }

  /**
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
    public com.google.common.util.concurrent.ListenableFuture<em.issuer.v1.Tx.MsgIncreaseMintableResponse> increaseMintable(
        em.issuer.v1.Tx.MsgIncreaseMintable request) {
      return futureUnaryCall(
          getChannel().newCall(getIncreaseMintableMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<em.issuer.v1.Tx.MsgDecreaseMintableResponse> decreaseMintable(
        em.issuer.v1.Tx.MsgDecreaseMintable request) {
      return futureUnaryCall(
          getChannel().newCall(getDecreaseMintableMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<em.issuer.v1.Tx.MsgRevokeLiquidityProviderResponse> revokeLiquidityProvider(
        em.issuer.v1.Tx.MsgRevokeLiquidityProvider request) {
      return futureUnaryCall(
          getChannel().newCall(getRevokeLiquidityProviderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<em.issuer.v1.Tx.MsgSetInflationResponse> setInflation(
        em.issuer.v1.Tx.MsgSetInflation request) {
      return futureUnaryCall(
          getChannel().newCall(getSetInflationMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INCREASE_MINTABLE = 0;
  private static final int METHODID_DECREASE_MINTABLE = 1;
  private static final int METHODID_REVOKE_LIQUIDITY_PROVIDER = 2;
  private static final int METHODID_SET_INFLATION = 3;

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
        case METHODID_INCREASE_MINTABLE:
          serviceImpl.increaseMintable((em.issuer.v1.Tx.MsgIncreaseMintable) request,
              (io.grpc.stub.StreamObserver<em.issuer.v1.Tx.MsgIncreaseMintableResponse>) responseObserver);
          break;
        case METHODID_DECREASE_MINTABLE:
          serviceImpl.decreaseMintable((em.issuer.v1.Tx.MsgDecreaseMintable) request,
              (io.grpc.stub.StreamObserver<em.issuer.v1.Tx.MsgDecreaseMintableResponse>) responseObserver);
          break;
        case METHODID_REVOKE_LIQUIDITY_PROVIDER:
          serviceImpl.revokeLiquidityProvider((em.issuer.v1.Tx.MsgRevokeLiquidityProvider) request,
              (io.grpc.stub.StreamObserver<em.issuer.v1.Tx.MsgRevokeLiquidityProviderResponse>) responseObserver);
          break;
        case METHODID_SET_INFLATION:
          serviceImpl.setInflation((em.issuer.v1.Tx.MsgSetInflation) request,
              (io.grpc.stub.StreamObserver<em.issuer.v1.Tx.MsgSetInflationResponse>) responseObserver);
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
      return em.issuer.v1.Tx.getDescriptor();
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
              .addMethod(getIncreaseMintableMethod())
              .addMethod(getDecreaseMintableMethod())
              .addMethod(getRevokeLiquidityProviderMethod())
              .addMethod(getSetInflationMethod())
              .build();
        }
      }
    }
    return result;
  }
}
