package bitsong.fantoken;

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
 * Msg defines the oracle Msg service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: bitsong/fantoken/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "bitsong.fantoken.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<bitsong.fantoken.Tx.MsgIssueFanToken,
      bitsong.fantoken.Tx.MsgIssueFanTokenResponse> getIssueFanTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IssueFanToken",
      requestType = bitsong.fantoken.Tx.MsgIssueFanToken.class,
      responseType = bitsong.fantoken.Tx.MsgIssueFanTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.fantoken.Tx.MsgIssueFanToken,
      bitsong.fantoken.Tx.MsgIssueFanTokenResponse> getIssueFanTokenMethod() {
    io.grpc.MethodDescriptor<bitsong.fantoken.Tx.MsgIssueFanToken, bitsong.fantoken.Tx.MsgIssueFanTokenResponse> getIssueFanTokenMethod;
    if ((getIssueFanTokenMethod = MsgGrpc.getIssueFanTokenMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getIssueFanTokenMethod = MsgGrpc.getIssueFanTokenMethod) == null) {
          MsgGrpc.getIssueFanTokenMethod = getIssueFanTokenMethod =
              io.grpc.MethodDescriptor.<bitsong.fantoken.Tx.MsgIssueFanToken, bitsong.fantoken.Tx.MsgIssueFanTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IssueFanToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.Tx.MsgIssueFanToken.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.Tx.MsgIssueFanTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("IssueFanToken"))
              .build();
        }
      }
    }
    return getIssueFanTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.fantoken.Tx.MsgEditFanToken,
      bitsong.fantoken.Tx.MsgEditFanTokenResponse> getEditFanTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditFanToken",
      requestType = bitsong.fantoken.Tx.MsgEditFanToken.class,
      responseType = bitsong.fantoken.Tx.MsgEditFanTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.fantoken.Tx.MsgEditFanToken,
      bitsong.fantoken.Tx.MsgEditFanTokenResponse> getEditFanTokenMethod() {
    io.grpc.MethodDescriptor<bitsong.fantoken.Tx.MsgEditFanToken, bitsong.fantoken.Tx.MsgEditFanTokenResponse> getEditFanTokenMethod;
    if ((getEditFanTokenMethod = MsgGrpc.getEditFanTokenMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEditFanTokenMethod = MsgGrpc.getEditFanTokenMethod) == null) {
          MsgGrpc.getEditFanTokenMethod = getEditFanTokenMethod =
              io.grpc.MethodDescriptor.<bitsong.fantoken.Tx.MsgEditFanToken, bitsong.fantoken.Tx.MsgEditFanTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditFanToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.Tx.MsgEditFanToken.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.Tx.MsgEditFanTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EditFanToken"))
              .build();
        }
      }
    }
    return getEditFanTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.fantoken.Tx.MsgMintFanToken,
      bitsong.fantoken.Tx.MsgMintFanTokenResponse> getMintFanTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MintFanToken",
      requestType = bitsong.fantoken.Tx.MsgMintFanToken.class,
      responseType = bitsong.fantoken.Tx.MsgMintFanTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.fantoken.Tx.MsgMintFanToken,
      bitsong.fantoken.Tx.MsgMintFanTokenResponse> getMintFanTokenMethod() {
    io.grpc.MethodDescriptor<bitsong.fantoken.Tx.MsgMintFanToken, bitsong.fantoken.Tx.MsgMintFanTokenResponse> getMintFanTokenMethod;
    if ((getMintFanTokenMethod = MsgGrpc.getMintFanTokenMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMintFanTokenMethod = MsgGrpc.getMintFanTokenMethod) == null) {
          MsgGrpc.getMintFanTokenMethod = getMintFanTokenMethod =
              io.grpc.MethodDescriptor.<bitsong.fantoken.Tx.MsgMintFanToken, bitsong.fantoken.Tx.MsgMintFanTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MintFanToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.Tx.MsgMintFanToken.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.Tx.MsgMintFanTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("MintFanToken"))
              .build();
        }
      }
    }
    return getMintFanTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.fantoken.Tx.MsgBurnFanToken,
      bitsong.fantoken.Tx.MsgBurnFanTokenResponse> getBurnFanTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BurnFanToken",
      requestType = bitsong.fantoken.Tx.MsgBurnFanToken.class,
      responseType = bitsong.fantoken.Tx.MsgBurnFanTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.fantoken.Tx.MsgBurnFanToken,
      bitsong.fantoken.Tx.MsgBurnFanTokenResponse> getBurnFanTokenMethod() {
    io.grpc.MethodDescriptor<bitsong.fantoken.Tx.MsgBurnFanToken, bitsong.fantoken.Tx.MsgBurnFanTokenResponse> getBurnFanTokenMethod;
    if ((getBurnFanTokenMethod = MsgGrpc.getBurnFanTokenMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBurnFanTokenMethod = MsgGrpc.getBurnFanTokenMethod) == null) {
          MsgGrpc.getBurnFanTokenMethod = getBurnFanTokenMethod =
              io.grpc.MethodDescriptor.<bitsong.fantoken.Tx.MsgBurnFanToken, bitsong.fantoken.Tx.MsgBurnFanTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BurnFanToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.Tx.MsgBurnFanToken.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.Tx.MsgBurnFanTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BurnFanToken"))
              .build();
        }
      }
    }
    return getBurnFanTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.fantoken.Tx.MsgTransferFanTokenOwner,
      bitsong.fantoken.Tx.MsgTransferFanTokenOwnerResponse> getTransferFanTokenOwnerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TransferFanTokenOwner",
      requestType = bitsong.fantoken.Tx.MsgTransferFanTokenOwner.class,
      responseType = bitsong.fantoken.Tx.MsgTransferFanTokenOwnerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.fantoken.Tx.MsgTransferFanTokenOwner,
      bitsong.fantoken.Tx.MsgTransferFanTokenOwnerResponse> getTransferFanTokenOwnerMethod() {
    io.grpc.MethodDescriptor<bitsong.fantoken.Tx.MsgTransferFanTokenOwner, bitsong.fantoken.Tx.MsgTransferFanTokenOwnerResponse> getTransferFanTokenOwnerMethod;
    if ((getTransferFanTokenOwnerMethod = MsgGrpc.getTransferFanTokenOwnerMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getTransferFanTokenOwnerMethod = MsgGrpc.getTransferFanTokenOwnerMethod) == null) {
          MsgGrpc.getTransferFanTokenOwnerMethod = getTransferFanTokenOwnerMethod =
              io.grpc.MethodDescriptor.<bitsong.fantoken.Tx.MsgTransferFanTokenOwner, bitsong.fantoken.Tx.MsgTransferFanTokenOwnerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TransferFanTokenOwner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.Tx.MsgTransferFanTokenOwner.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.fantoken.Tx.MsgTransferFanTokenOwnerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("TransferFanTokenOwner"))
              .build();
        }
      }
    }
    return getTransferFanTokenOwnerMethod;
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
   * Msg defines the oracle Msg service
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * IssueFanToken defines a method for issuing a new fan token
     * </pre>
     */
    public void issueFanToken(bitsong.fantoken.Tx.MsgIssueFanToken request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.Tx.MsgIssueFanTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getIssueFanTokenMethod(), responseObserver);
    }

    /**
     * <pre>
     * EditFanToken defines a method for editing a fantoken
     * </pre>
     */
    public void editFanToken(bitsong.fantoken.Tx.MsgEditFanToken request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.Tx.MsgEditFanTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEditFanTokenMethod(), responseObserver);
    }

    /**
     * <pre>
     * MintFanToken defines a method for minting some fan tokens
     * </pre>
     */
    public void mintFanToken(bitsong.fantoken.Tx.MsgMintFanToken request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.Tx.MsgMintFanTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMintFanTokenMethod(), responseObserver);
    }

    /**
     * <pre>
     * BurnFanToken defines a method for burning some fan tokens
     * </pre>
     */
    public void burnFanToken(bitsong.fantoken.Tx.MsgBurnFanToken request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.Tx.MsgBurnFanTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBurnFanTokenMethod(), responseObserver);
    }

    /**
     * <pre>
     * TransferFanTokenOwner defines a method for minting some fan tokens
     * </pre>
     */
    public void transferFanTokenOwner(bitsong.fantoken.Tx.MsgTransferFanTokenOwner request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.Tx.MsgTransferFanTokenOwnerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTransferFanTokenOwnerMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getIssueFanTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.fantoken.Tx.MsgIssueFanToken,
                bitsong.fantoken.Tx.MsgIssueFanTokenResponse>(
                  this, METHODID_ISSUE_FAN_TOKEN)))
          .addMethod(
            getEditFanTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.fantoken.Tx.MsgEditFanToken,
                bitsong.fantoken.Tx.MsgEditFanTokenResponse>(
                  this, METHODID_EDIT_FAN_TOKEN)))
          .addMethod(
            getMintFanTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.fantoken.Tx.MsgMintFanToken,
                bitsong.fantoken.Tx.MsgMintFanTokenResponse>(
                  this, METHODID_MINT_FAN_TOKEN)))
          .addMethod(
            getBurnFanTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.fantoken.Tx.MsgBurnFanToken,
                bitsong.fantoken.Tx.MsgBurnFanTokenResponse>(
                  this, METHODID_BURN_FAN_TOKEN)))
          .addMethod(
            getTransferFanTokenOwnerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.fantoken.Tx.MsgTransferFanTokenOwner,
                bitsong.fantoken.Tx.MsgTransferFanTokenOwnerResponse>(
                  this, METHODID_TRANSFER_FAN_TOKEN_OWNER)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the oracle Msg service
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
     * IssueFanToken defines a method for issuing a new fan token
     * </pre>
     */
    public void issueFanToken(bitsong.fantoken.Tx.MsgIssueFanToken request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.Tx.MsgIssueFanTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIssueFanTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EditFanToken defines a method for editing a fantoken
     * </pre>
     */
    public void editFanToken(bitsong.fantoken.Tx.MsgEditFanToken request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.Tx.MsgEditFanTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEditFanTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MintFanToken defines a method for minting some fan tokens
     * </pre>
     */
    public void mintFanToken(bitsong.fantoken.Tx.MsgMintFanToken request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.Tx.MsgMintFanTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMintFanTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BurnFanToken defines a method for burning some fan tokens
     * </pre>
     */
    public void burnFanToken(bitsong.fantoken.Tx.MsgBurnFanToken request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.Tx.MsgBurnFanTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBurnFanTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TransferFanTokenOwner defines a method for minting some fan tokens
     * </pre>
     */
    public void transferFanTokenOwner(bitsong.fantoken.Tx.MsgTransferFanTokenOwner request,
        io.grpc.stub.StreamObserver<bitsong.fantoken.Tx.MsgTransferFanTokenOwnerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTransferFanTokenOwnerMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the oracle Msg service
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
     * IssueFanToken defines a method for issuing a new fan token
     * </pre>
     */
    public bitsong.fantoken.Tx.MsgIssueFanTokenResponse issueFanToken(bitsong.fantoken.Tx.MsgIssueFanToken request) {
      return blockingUnaryCall(
          getChannel(), getIssueFanTokenMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EditFanToken defines a method for editing a fantoken
     * </pre>
     */
    public bitsong.fantoken.Tx.MsgEditFanTokenResponse editFanToken(bitsong.fantoken.Tx.MsgEditFanToken request) {
      return blockingUnaryCall(
          getChannel(), getEditFanTokenMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MintFanToken defines a method for minting some fan tokens
     * </pre>
     */
    public bitsong.fantoken.Tx.MsgMintFanTokenResponse mintFanToken(bitsong.fantoken.Tx.MsgMintFanToken request) {
      return blockingUnaryCall(
          getChannel(), getMintFanTokenMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BurnFanToken defines a method for burning some fan tokens
     * </pre>
     */
    public bitsong.fantoken.Tx.MsgBurnFanTokenResponse burnFanToken(bitsong.fantoken.Tx.MsgBurnFanToken request) {
      return blockingUnaryCall(
          getChannel(), getBurnFanTokenMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TransferFanTokenOwner defines a method for minting some fan tokens
     * </pre>
     */
    public bitsong.fantoken.Tx.MsgTransferFanTokenOwnerResponse transferFanTokenOwner(bitsong.fantoken.Tx.MsgTransferFanTokenOwner request) {
      return blockingUnaryCall(
          getChannel(), getTransferFanTokenOwnerMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the oracle Msg service
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
     * IssueFanToken defines a method for issuing a new fan token
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.fantoken.Tx.MsgIssueFanTokenResponse> issueFanToken(
        bitsong.fantoken.Tx.MsgIssueFanToken request) {
      return futureUnaryCall(
          getChannel().newCall(getIssueFanTokenMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EditFanToken defines a method for editing a fantoken
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.fantoken.Tx.MsgEditFanTokenResponse> editFanToken(
        bitsong.fantoken.Tx.MsgEditFanToken request) {
      return futureUnaryCall(
          getChannel().newCall(getEditFanTokenMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MintFanToken defines a method for minting some fan tokens
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.fantoken.Tx.MsgMintFanTokenResponse> mintFanToken(
        bitsong.fantoken.Tx.MsgMintFanToken request) {
      return futureUnaryCall(
          getChannel().newCall(getMintFanTokenMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BurnFanToken defines a method for burning some fan tokens
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.fantoken.Tx.MsgBurnFanTokenResponse> burnFanToken(
        bitsong.fantoken.Tx.MsgBurnFanToken request) {
      return futureUnaryCall(
          getChannel().newCall(getBurnFanTokenMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TransferFanTokenOwner defines a method for minting some fan tokens
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.fantoken.Tx.MsgTransferFanTokenOwnerResponse> transferFanTokenOwner(
        bitsong.fantoken.Tx.MsgTransferFanTokenOwner request) {
      return futureUnaryCall(
          getChannel().newCall(getTransferFanTokenOwnerMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ISSUE_FAN_TOKEN = 0;
  private static final int METHODID_EDIT_FAN_TOKEN = 1;
  private static final int METHODID_MINT_FAN_TOKEN = 2;
  private static final int METHODID_BURN_FAN_TOKEN = 3;
  private static final int METHODID_TRANSFER_FAN_TOKEN_OWNER = 4;

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
        case METHODID_ISSUE_FAN_TOKEN:
          serviceImpl.issueFanToken((bitsong.fantoken.Tx.MsgIssueFanToken) request,
              (io.grpc.stub.StreamObserver<bitsong.fantoken.Tx.MsgIssueFanTokenResponse>) responseObserver);
          break;
        case METHODID_EDIT_FAN_TOKEN:
          serviceImpl.editFanToken((bitsong.fantoken.Tx.MsgEditFanToken) request,
              (io.grpc.stub.StreamObserver<bitsong.fantoken.Tx.MsgEditFanTokenResponse>) responseObserver);
          break;
        case METHODID_MINT_FAN_TOKEN:
          serviceImpl.mintFanToken((bitsong.fantoken.Tx.MsgMintFanToken) request,
              (io.grpc.stub.StreamObserver<bitsong.fantoken.Tx.MsgMintFanTokenResponse>) responseObserver);
          break;
        case METHODID_BURN_FAN_TOKEN:
          serviceImpl.burnFanToken((bitsong.fantoken.Tx.MsgBurnFanToken) request,
              (io.grpc.stub.StreamObserver<bitsong.fantoken.Tx.MsgBurnFanTokenResponse>) responseObserver);
          break;
        case METHODID_TRANSFER_FAN_TOKEN_OWNER:
          serviceImpl.transferFanTokenOwner((bitsong.fantoken.Tx.MsgTransferFanTokenOwner) request,
              (io.grpc.stub.StreamObserver<bitsong.fantoken.Tx.MsgTransferFanTokenOwnerResponse>) responseObserver);
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
      return bitsong.fantoken.Tx.getDescriptor();
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
              .addMethod(getIssueFanTokenMethod())
              .addMethod(getEditFanTokenMethod())
              .addMethod(getMintFanTokenMethod())
              .addMethod(getBurnFanTokenMethod())
              .addMethod(getTransferFanTokenOwnerMethod())
              .build();
        }
      }
    }
    return result;
  }
}
