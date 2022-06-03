package em.liquidityprovider.v1;

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
    comments = "Source: em/liquidityprovider/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "em.liquidityprovider.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<em.liquidityprovider.v1.Tx.MsgMintTokens,
      em.liquidityprovider.v1.Tx.MsgMintTokensResponse> getMintTokensMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MintTokens",
      requestType = em.liquidityprovider.v1.Tx.MsgMintTokens.class,
      responseType = em.liquidityprovider.v1.Tx.MsgMintTokensResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.liquidityprovider.v1.Tx.MsgMintTokens,
      em.liquidityprovider.v1.Tx.MsgMintTokensResponse> getMintTokensMethod() {
    io.grpc.MethodDescriptor<em.liquidityprovider.v1.Tx.MsgMintTokens, em.liquidityprovider.v1.Tx.MsgMintTokensResponse> getMintTokensMethod;
    if ((getMintTokensMethod = MsgGrpc.getMintTokensMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMintTokensMethod = MsgGrpc.getMintTokensMethod) == null) {
          MsgGrpc.getMintTokensMethod = getMintTokensMethod =
              io.grpc.MethodDescriptor.<em.liquidityprovider.v1.Tx.MsgMintTokens, em.liquidityprovider.v1.Tx.MsgMintTokensResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MintTokens"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.liquidityprovider.v1.Tx.MsgMintTokens.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.liquidityprovider.v1.Tx.MsgMintTokensResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("MintTokens"))
              .build();
        }
      }
    }
    return getMintTokensMethod;
  }

  private static volatile io.grpc.MethodDescriptor<em.liquidityprovider.v1.Tx.MsgBurnTokens,
      em.liquidityprovider.v1.Tx.MsgBurnTokensResponse> getBurnTokensMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BurnTokens",
      requestType = em.liquidityprovider.v1.Tx.MsgBurnTokens.class,
      responseType = em.liquidityprovider.v1.Tx.MsgBurnTokensResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.liquidityprovider.v1.Tx.MsgBurnTokens,
      em.liquidityprovider.v1.Tx.MsgBurnTokensResponse> getBurnTokensMethod() {
    io.grpc.MethodDescriptor<em.liquidityprovider.v1.Tx.MsgBurnTokens, em.liquidityprovider.v1.Tx.MsgBurnTokensResponse> getBurnTokensMethod;
    if ((getBurnTokensMethod = MsgGrpc.getBurnTokensMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBurnTokensMethod = MsgGrpc.getBurnTokensMethod) == null) {
          MsgGrpc.getBurnTokensMethod = getBurnTokensMethod =
              io.grpc.MethodDescriptor.<em.liquidityprovider.v1.Tx.MsgBurnTokens, em.liquidityprovider.v1.Tx.MsgBurnTokensResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BurnTokens"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.liquidityprovider.v1.Tx.MsgBurnTokens.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.liquidityprovider.v1.Tx.MsgBurnTokensResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BurnTokens"))
              .build();
        }
      }
    }
    return getBurnTokensMethod;
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
    public void mintTokens(em.liquidityprovider.v1.Tx.MsgMintTokens request,
        io.grpc.stub.StreamObserver<em.liquidityprovider.v1.Tx.MsgMintTokensResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMintTokensMethod(), responseObserver);
    }

    /**
     */
    public void burnTokens(em.liquidityprovider.v1.Tx.MsgBurnTokens request,
        io.grpc.stub.StreamObserver<em.liquidityprovider.v1.Tx.MsgBurnTokensResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBurnTokensMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMintTokensMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.liquidityprovider.v1.Tx.MsgMintTokens,
                em.liquidityprovider.v1.Tx.MsgMintTokensResponse>(
                  this, METHODID_MINT_TOKENS)))
          .addMethod(
            getBurnTokensMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.liquidityprovider.v1.Tx.MsgBurnTokens,
                em.liquidityprovider.v1.Tx.MsgBurnTokensResponse>(
                  this, METHODID_BURN_TOKENS)))
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
    public void mintTokens(em.liquidityprovider.v1.Tx.MsgMintTokens request,
        io.grpc.stub.StreamObserver<em.liquidityprovider.v1.Tx.MsgMintTokensResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMintTokensMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void burnTokens(em.liquidityprovider.v1.Tx.MsgBurnTokens request,
        io.grpc.stub.StreamObserver<em.liquidityprovider.v1.Tx.MsgBurnTokensResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBurnTokensMethod(), getCallOptions()), request, responseObserver);
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
    public em.liquidityprovider.v1.Tx.MsgMintTokensResponse mintTokens(em.liquidityprovider.v1.Tx.MsgMintTokens request) {
      return blockingUnaryCall(
          getChannel(), getMintTokensMethod(), getCallOptions(), request);
    }

    /**
     */
    public em.liquidityprovider.v1.Tx.MsgBurnTokensResponse burnTokens(em.liquidityprovider.v1.Tx.MsgBurnTokens request) {
      return blockingUnaryCall(
          getChannel(), getBurnTokensMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<em.liquidityprovider.v1.Tx.MsgMintTokensResponse> mintTokens(
        em.liquidityprovider.v1.Tx.MsgMintTokens request) {
      return futureUnaryCall(
          getChannel().newCall(getMintTokensMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<em.liquidityprovider.v1.Tx.MsgBurnTokensResponse> burnTokens(
        em.liquidityprovider.v1.Tx.MsgBurnTokens request) {
      return futureUnaryCall(
          getChannel().newCall(getBurnTokensMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MINT_TOKENS = 0;
  private static final int METHODID_BURN_TOKENS = 1;

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
        case METHODID_MINT_TOKENS:
          serviceImpl.mintTokens((em.liquidityprovider.v1.Tx.MsgMintTokens) request,
              (io.grpc.stub.StreamObserver<em.liquidityprovider.v1.Tx.MsgMintTokensResponse>) responseObserver);
          break;
        case METHODID_BURN_TOKENS:
          serviceImpl.burnTokens((em.liquidityprovider.v1.Tx.MsgBurnTokens) request,
              (io.grpc.stub.StreamObserver<em.liquidityprovider.v1.Tx.MsgBurnTokensResponse>) responseObserver);
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
      return em.liquidityprovider.v1.Tx.getDescriptor();
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
              .addMethod(getMintTokensMethod())
              .addMethod(getBurnTokensMethod())
              .build();
        }
      }
    }
    return result;
  }
}
