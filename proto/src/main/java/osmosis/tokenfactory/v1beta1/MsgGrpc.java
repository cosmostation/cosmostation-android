package osmosis.tokenfactory.v1beta1;

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
    comments = "Source: osmosis/tokenfactory/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "osmosis.tokenfactory.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenom,
      osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenomResponse> getCreateDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateDenom",
      requestType = osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenom.class,
      responseType = osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenom,
      osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenomResponse> getCreateDenomMethod() {
    io.grpc.MethodDescriptor<osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenom, osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenomResponse> getCreateDenomMethod;
    if ((getCreateDenomMethod = MsgGrpc.getCreateDenomMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateDenomMethod = MsgGrpc.getCreateDenomMethod) == null) {
          MsgGrpc.getCreateDenomMethod = getCreateDenomMethod =
              io.grpc.MethodDescriptor.<osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenom, osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenom.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateDenom"))
              .build();
        }
      }
    }
    return getCreateDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.tokenfactory.v1beta1.Tx.MsgMint,
      osmosis.tokenfactory.v1beta1.Tx.MsgMintResponse> getMintMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Mint",
      requestType = osmosis.tokenfactory.v1beta1.Tx.MsgMint.class,
      responseType = osmosis.tokenfactory.v1beta1.Tx.MsgMintResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.tokenfactory.v1beta1.Tx.MsgMint,
      osmosis.tokenfactory.v1beta1.Tx.MsgMintResponse> getMintMethod() {
    io.grpc.MethodDescriptor<osmosis.tokenfactory.v1beta1.Tx.MsgMint, osmosis.tokenfactory.v1beta1.Tx.MsgMintResponse> getMintMethod;
    if ((getMintMethod = MsgGrpc.getMintMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMintMethod = MsgGrpc.getMintMethod) == null) {
          MsgGrpc.getMintMethod = getMintMethod =
              io.grpc.MethodDescriptor.<osmosis.tokenfactory.v1beta1.Tx.MsgMint, osmosis.tokenfactory.v1beta1.Tx.MsgMintResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Mint"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.tokenfactory.v1beta1.Tx.MsgMint.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.tokenfactory.v1beta1.Tx.MsgMintResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Mint"))
              .build();
        }
      }
    }
    return getMintMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.tokenfactory.v1beta1.Tx.MsgBurn,
      osmosis.tokenfactory.v1beta1.Tx.MsgBurnResponse> getBurnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Burn",
      requestType = osmosis.tokenfactory.v1beta1.Tx.MsgBurn.class,
      responseType = osmosis.tokenfactory.v1beta1.Tx.MsgBurnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.tokenfactory.v1beta1.Tx.MsgBurn,
      osmosis.tokenfactory.v1beta1.Tx.MsgBurnResponse> getBurnMethod() {
    io.grpc.MethodDescriptor<osmosis.tokenfactory.v1beta1.Tx.MsgBurn, osmosis.tokenfactory.v1beta1.Tx.MsgBurnResponse> getBurnMethod;
    if ((getBurnMethod = MsgGrpc.getBurnMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBurnMethod = MsgGrpc.getBurnMethod) == null) {
          MsgGrpc.getBurnMethod = getBurnMethod =
              io.grpc.MethodDescriptor.<osmosis.tokenfactory.v1beta1.Tx.MsgBurn, osmosis.tokenfactory.v1beta1.Tx.MsgBurnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Burn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.tokenfactory.v1beta1.Tx.MsgBurn.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.tokenfactory.v1beta1.Tx.MsgBurnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Burn"))
              .build();
        }
      }
    }
    return getBurnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdmin,
      osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdminResponse> getChangeAdminMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChangeAdmin",
      requestType = osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdmin.class,
      responseType = osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdminResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdmin,
      osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdminResponse> getChangeAdminMethod() {
    io.grpc.MethodDescriptor<osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdmin, osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdminResponse> getChangeAdminMethod;
    if ((getChangeAdminMethod = MsgGrpc.getChangeAdminMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getChangeAdminMethod = MsgGrpc.getChangeAdminMethod) == null) {
          MsgGrpc.getChangeAdminMethod = getChangeAdminMethod =
              io.grpc.MethodDescriptor.<osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdmin, osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdminResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ChangeAdmin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdmin.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdminResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ChangeAdmin"))
              .build();
        }
      }
    }
    return getChangeAdminMethod;
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
    public void createDenom(osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenom request,
        io.grpc.stub.StreamObserver<osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateDenomMethod(), responseObserver);
    }

    /**
     */
    public void mint(osmosis.tokenfactory.v1beta1.Tx.MsgMint request,
        io.grpc.stub.StreamObserver<osmosis.tokenfactory.v1beta1.Tx.MsgMintResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMintMethod(), responseObserver);
    }

    /**
     */
    public void burn(osmosis.tokenfactory.v1beta1.Tx.MsgBurn request,
        io.grpc.stub.StreamObserver<osmosis.tokenfactory.v1beta1.Tx.MsgBurnResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBurnMethod(), responseObserver);
    }

    /**
     * <pre>
     * ForceTransfer is deactivated for now because we need to think through edge
     * cases rpc ForceTransfer(MsgForceTransfer) returns
     * (MsgForceTransferResponse);
     * </pre>
     */
    public void changeAdmin(osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdmin request,
        io.grpc.stub.StreamObserver<osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdminResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getChangeAdminMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenom,
                osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenomResponse>(
                  this, METHODID_CREATE_DENOM)))
          .addMethod(
            getMintMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.tokenfactory.v1beta1.Tx.MsgMint,
                osmosis.tokenfactory.v1beta1.Tx.MsgMintResponse>(
                  this, METHODID_MINT)))
          .addMethod(
            getBurnMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.tokenfactory.v1beta1.Tx.MsgBurn,
                osmosis.tokenfactory.v1beta1.Tx.MsgBurnResponse>(
                  this, METHODID_BURN)))
          .addMethod(
            getChangeAdminMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdmin,
                osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdminResponse>(
                  this, METHODID_CHANGE_ADMIN)))
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
    public void createDenom(osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenom request,
        io.grpc.stub.StreamObserver<osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void mint(osmosis.tokenfactory.v1beta1.Tx.MsgMint request,
        io.grpc.stub.StreamObserver<osmosis.tokenfactory.v1beta1.Tx.MsgMintResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMintMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void burn(osmosis.tokenfactory.v1beta1.Tx.MsgBurn request,
        io.grpc.stub.StreamObserver<osmosis.tokenfactory.v1beta1.Tx.MsgBurnResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBurnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ForceTransfer is deactivated for now because we need to think through edge
     * cases rpc ForceTransfer(MsgForceTransfer) returns
     * (MsgForceTransferResponse);
     * </pre>
     */
    public void changeAdmin(osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdmin request,
        io.grpc.stub.StreamObserver<osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdminResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChangeAdminMethod(), getCallOptions()), request, responseObserver);
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
    public osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenomResponse createDenom(osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenom request) {
      return blockingUnaryCall(
          getChannel(), getCreateDenomMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.tokenfactory.v1beta1.Tx.MsgMintResponse mint(osmosis.tokenfactory.v1beta1.Tx.MsgMint request) {
      return blockingUnaryCall(
          getChannel(), getMintMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.tokenfactory.v1beta1.Tx.MsgBurnResponse burn(osmosis.tokenfactory.v1beta1.Tx.MsgBurn request) {
      return blockingUnaryCall(
          getChannel(), getBurnMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ForceTransfer is deactivated for now because we need to think through edge
     * cases rpc ForceTransfer(MsgForceTransfer) returns
     * (MsgForceTransferResponse);
     * </pre>
     */
    public osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdminResponse changeAdmin(osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdmin request) {
      return blockingUnaryCall(
          getChannel(), getChangeAdminMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenomResponse> createDenom(
        osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenom request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateDenomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.tokenfactory.v1beta1.Tx.MsgMintResponse> mint(
        osmosis.tokenfactory.v1beta1.Tx.MsgMint request) {
      return futureUnaryCall(
          getChannel().newCall(getMintMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.tokenfactory.v1beta1.Tx.MsgBurnResponse> burn(
        osmosis.tokenfactory.v1beta1.Tx.MsgBurn request) {
      return futureUnaryCall(
          getChannel().newCall(getBurnMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ForceTransfer is deactivated for now because we need to think through edge
     * cases rpc ForceTransfer(MsgForceTransfer) returns
     * (MsgForceTransferResponse);
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdminResponse> changeAdmin(
        osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdmin request) {
      return futureUnaryCall(
          getChannel().newCall(getChangeAdminMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_DENOM = 0;
  private static final int METHODID_MINT = 1;
  private static final int METHODID_BURN = 2;
  private static final int METHODID_CHANGE_ADMIN = 3;

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
        case METHODID_CREATE_DENOM:
          serviceImpl.createDenom((osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenom) request,
              (io.grpc.stub.StreamObserver<osmosis.tokenfactory.v1beta1.Tx.MsgCreateDenomResponse>) responseObserver);
          break;
        case METHODID_MINT:
          serviceImpl.mint((osmosis.tokenfactory.v1beta1.Tx.MsgMint) request,
              (io.grpc.stub.StreamObserver<osmosis.tokenfactory.v1beta1.Tx.MsgMintResponse>) responseObserver);
          break;
        case METHODID_BURN:
          serviceImpl.burn((osmosis.tokenfactory.v1beta1.Tx.MsgBurn) request,
              (io.grpc.stub.StreamObserver<osmosis.tokenfactory.v1beta1.Tx.MsgBurnResponse>) responseObserver);
          break;
        case METHODID_CHANGE_ADMIN:
          serviceImpl.changeAdmin((osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdmin) request,
              (io.grpc.stub.StreamObserver<osmosis.tokenfactory.v1beta1.Tx.MsgChangeAdminResponse>) responseObserver);
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
      return osmosis.tokenfactory.v1beta1.Tx.getDescriptor();
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
              .addMethod(getCreateDenomMethod())
              .addMethod(getMintMethod())
              .addMethod(getBurnMethod())
              .addMethod(getChangeAdminMethod())
              .build();
        }
      }
    }
    return result;
  }
}
