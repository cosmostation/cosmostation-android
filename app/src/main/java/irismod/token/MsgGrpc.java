package irismod.token;

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
    comments = "Source: token/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "irismod.token.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<irismod.token.Tx.MsgIssueToken,
      irismod.token.Tx.MsgIssueTokenResponse> getIssueTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IssueToken",
      requestType = irismod.token.Tx.MsgIssueToken.class,
      responseType = irismod.token.Tx.MsgIssueTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.token.Tx.MsgIssueToken,
      irismod.token.Tx.MsgIssueTokenResponse> getIssueTokenMethod() {
    io.grpc.MethodDescriptor<irismod.token.Tx.MsgIssueToken, irismod.token.Tx.MsgIssueTokenResponse> getIssueTokenMethod;
    if ((getIssueTokenMethod = MsgGrpc.getIssueTokenMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getIssueTokenMethod = MsgGrpc.getIssueTokenMethod) == null) {
          MsgGrpc.getIssueTokenMethod = getIssueTokenMethod =
              io.grpc.MethodDescriptor.<irismod.token.Tx.MsgIssueToken, irismod.token.Tx.MsgIssueTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IssueToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.Tx.MsgIssueToken.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.Tx.MsgIssueTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("IssueToken"))
              .build();
        }
      }
    }
    return getIssueTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.token.Tx.MsgEditToken,
      irismod.token.Tx.MsgEditTokenResponse> getEditTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditToken",
      requestType = irismod.token.Tx.MsgEditToken.class,
      responseType = irismod.token.Tx.MsgEditTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.token.Tx.MsgEditToken,
      irismod.token.Tx.MsgEditTokenResponse> getEditTokenMethod() {
    io.grpc.MethodDescriptor<irismod.token.Tx.MsgEditToken, irismod.token.Tx.MsgEditTokenResponse> getEditTokenMethod;
    if ((getEditTokenMethod = MsgGrpc.getEditTokenMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEditTokenMethod = MsgGrpc.getEditTokenMethod) == null) {
          MsgGrpc.getEditTokenMethod = getEditTokenMethod =
              io.grpc.MethodDescriptor.<irismod.token.Tx.MsgEditToken, irismod.token.Tx.MsgEditTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.Tx.MsgEditToken.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.Tx.MsgEditTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EditToken"))
              .build();
        }
      }
    }
    return getEditTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.token.Tx.MsgMintToken,
      irismod.token.Tx.MsgMintTokenResponse> getMintTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MintToken",
      requestType = irismod.token.Tx.MsgMintToken.class,
      responseType = irismod.token.Tx.MsgMintTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.token.Tx.MsgMintToken,
      irismod.token.Tx.MsgMintTokenResponse> getMintTokenMethod() {
    io.grpc.MethodDescriptor<irismod.token.Tx.MsgMintToken, irismod.token.Tx.MsgMintTokenResponse> getMintTokenMethod;
    if ((getMintTokenMethod = MsgGrpc.getMintTokenMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMintTokenMethod = MsgGrpc.getMintTokenMethod) == null) {
          MsgGrpc.getMintTokenMethod = getMintTokenMethod =
              io.grpc.MethodDescriptor.<irismod.token.Tx.MsgMintToken, irismod.token.Tx.MsgMintTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MintToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.Tx.MsgMintToken.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.Tx.MsgMintTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("MintToken"))
              .build();
        }
      }
    }
    return getMintTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.token.Tx.MsgBurnToken,
      irismod.token.Tx.MsgBurnTokenResponse> getBurnTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BurnToken",
      requestType = irismod.token.Tx.MsgBurnToken.class,
      responseType = irismod.token.Tx.MsgBurnTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.token.Tx.MsgBurnToken,
      irismod.token.Tx.MsgBurnTokenResponse> getBurnTokenMethod() {
    io.grpc.MethodDescriptor<irismod.token.Tx.MsgBurnToken, irismod.token.Tx.MsgBurnTokenResponse> getBurnTokenMethod;
    if ((getBurnTokenMethod = MsgGrpc.getBurnTokenMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBurnTokenMethod = MsgGrpc.getBurnTokenMethod) == null) {
          MsgGrpc.getBurnTokenMethod = getBurnTokenMethod =
              io.grpc.MethodDescriptor.<irismod.token.Tx.MsgBurnToken, irismod.token.Tx.MsgBurnTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BurnToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.Tx.MsgBurnToken.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.Tx.MsgBurnTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BurnToken"))
              .build();
        }
      }
    }
    return getBurnTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.token.Tx.MsgTransferTokenOwner,
      irismod.token.Tx.MsgTransferTokenOwnerResponse> getTransferTokenOwnerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TransferTokenOwner",
      requestType = irismod.token.Tx.MsgTransferTokenOwner.class,
      responseType = irismod.token.Tx.MsgTransferTokenOwnerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.token.Tx.MsgTransferTokenOwner,
      irismod.token.Tx.MsgTransferTokenOwnerResponse> getTransferTokenOwnerMethod() {
    io.grpc.MethodDescriptor<irismod.token.Tx.MsgTransferTokenOwner, irismod.token.Tx.MsgTransferTokenOwnerResponse> getTransferTokenOwnerMethod;
    if ((getTransferTokenOwnerMethod = MsgGrpc.getTransferTokenOwnerMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getTransferTokenOwnerMethod = MsgGrpc.getTransferTokenOwnerMethod) == null) {
          MsgGrpc.getTransferTokenOwnerMethod = getTransferTokenOwnerMethod =
              io.grpc.MethodDescriptor.<irismod.token.Tx.MsgTransferTokenOwner, irismod.token.Tx.MsgTransferTokenOwnerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TransferTokenOwner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.Tx.MsgTransferTokenOwner.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.token.Tx.MsgTransferTokenOwnerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("TransferTokenOwner"))
              .build();
        }
      }
    }
    return getTransferTokenOwnerMethod;
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
     * IssueToken defines a method for issuing a new token
     * </pre>
     */
    public void issueToken(irismod.token.Tx.MsgIssueToken request,
        io.grpc.stub.StreamObserver<irismod.token.Tx.MsgIssueTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getIssueTokenMethod(), responseObserver);
    }

    /**
     * <pre>
     * EditToken defines a method for editing a token
     * </pre>
     */
    public void editToken(irismod.token.Tx.MsgEditToken request,
        io.grpc.stub.StreamObserver<irismod.token.Tx.MsgEditTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEditTokenMethod(), responseObserver);
    }

    /**
     * <pre>
     * MintToken defines a method for minting some tokens
     * </pre>
     */
    public void mintToken(irismod.token.Tx.MsgMintToken request,
        io.grpc.stub.StreamObserver<irismod.token.Tx.MsgMintTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMintTokenMethod(), responseObserver);
    }

    /**
     * <pre>
     * BurnToken defines a method for burning some tokens
     * </pre>
     */
    public void burnToken(irismod.token.Tx.MsgBurnToken request,
        io.grpc.stub.StreamObserver<irismod.token.Tx.MsgBurnTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBurnTokenMethod(), responseObserver);
    }

    /**
     * <pre>
     * TransferTokenOwner defines a method for minting some tokens
     * </pre>
     */
    public void transferTokenOwner(irismod.token.Tx.MsgTransferTokenOwner request,
        io.grpc.stub.StreamObserver<irismod.token.Tx.MsgTransferTokenOwnerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTransferTokenOwnerMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getIssueTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.token.Tx.MsgIssueToken,
                irismod.token.Tx.MsgIssueTokenResponse>(
                  this, METHODID_ISSUE_TOKEN)))
          .addMethod(
            getEditTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.token.Tx.MsgEditToken,
                irismod.token.Tx.MsgEditTokenResponse>(
                  this, METHODID_EDIT_TOKEN)))
          .addMethod(
            getMintTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.token.Tx.MsgMintToken,
                irismod.token.Tx.MsgMintTokenResponse>(
                  this, METHODID_MINT_TOKEN)))
          .addMethod(
            getBurnTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.token.Tx.MsgBurnToken,
                irismod.token.Tx.MsgBurnTokenResponse>(
                  this, METHODID_BURN_TOKEN)))
          .addMethod(
            getTransferTokenOwnerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.token.Tx.MsgTransferTokenOwner,
                irismod.token.Tx.MsgTransferTokenOwnerResponse>(
                  this, METHODID_TRANSFER_TOKEN_OWNER)))
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
     * IssueToken defines a method for issuing a new token
     * </pre>
     */
    public void issueToken(irismod.token.Tx.MsgIssueToken request,
        io.grpc.stub.StreamObserver<irismod.token.Tx.MsgIssueTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIssueTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EditToken defines a method for editing a token
     * </pre>
     */
    public void editToken(irismod.token.Tx.MsgEditToken request,
        io.grpc.stub.StreamObserver<irismod.token.Tx.MsgEditTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEditTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MintToken defines a method for minting some tokens
     * </pre>
     */
    public void mintToken(irismod.token.Tx.MsgMintToken request,
        io.grpc.stub.StreamObserver<irismod.token.Tx.MsgMintTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMintTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BurnToken defines a method for burning some tokens
     * </pre>
     */
    public void burnToken(irismod.token.Tx.MsgBurnToken request,
        io.grpc.stub.StreamObserver<irismod.token.Tx.MsgBurnTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBurnTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TransferTokenOwner defines a method for minting some tokens
     * </pre>
     */
    public void transferTokenOwner(irismod.token.Tx.MsgTransferTokenOwner request,
        io.grpc.stub.StreamObserver<irismod.token.Tx.MsgTransferTokenOwnerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTransferTokenOwnerMethod(), getCallOptions()), request, responseObserver);
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
     * IssueToken defines a method for issuing a new token
     * </pre>
     */
    public irismod.token.Tx.MsgIssueTokenResponse issueToken(irismod.token.Tx.MsgIssueToken request) {
      return blockingUnaryCall(
          getChannel(), getIssueTokenMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EditToken defines a method for editing a token
     * </pre>
     */
    public irismod.token.Tx.MsgEditTokenResponse editToken(irismod.token.Tx.MsgEditToken request) {
      return blockingUnaryCall(
          getChannel(), getEditTokenMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MintToken defines a method for minting some tokens
     * </pre>
     */
    public irismod.token.Tx.MsgMintTokenResponse mintToken(irismod.token.Tx.MsgMintToken request) {
      return blockingUnaryCall(
          getChannel(), getMintTokenMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BurnToken defines a method for burning some tokens
     * </pre>
     */
    public irismod.token.Tx.MsgBurnTokenResponse burnToken(irismod.token.Tx.MsgBurnToken request) {
      return blockingUnaryCall(
          getChannel(), getBurnTokenMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TransferTokenOwner defines a method for minting some tokens
     * </pre>
     */
    public irismod.token.Tx.MsgTransferTokenOwnerResponse transferTokenOwner(irismod.token.Tx.MsgTransferTokenOwner request) {
      return blockingUnaryCall(
          getChannel(), getTransferTokenOwnerMethod(), getCallOptions(), request);
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
     * IssueToken defines a method for issuing a new token
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.token.Tx.MsgIssueTokenResponse> issueToken(
        irismod.token.Tx.MsgIssueToken request) {
      return futureUnaryCall(
          getChannel().newCall(getIssueTokenMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EditToken defines a method for editing a token
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.token.Tx.MsgEditTokenResponse> editToken(
        irismod.token.Tx.MsgEditToken request) {
      return futureUnaryCall(
          getChannel().newCall(getEditTokenMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MintToken defines a method for minting some tokens
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.token.Tx.MsgMintTokenResponse> mintToken(
        irismod.token.Tx.MsgMintToken request) {
      return futureUnaryCall(
          getChannel().newCall(getMintTokenMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BurnToken defines a method for burning some tokens
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.token.Tx.MsgBurnTokenResponse> burnToken(
        irismod.token.Tx.MsgBurnToken request) {
      return futureUnaryCall(
          getChannel().newCall(getBurnTokenMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TransferTokenOwner defines a method for minting some tokens
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.token.Tx.MsgTransferTokenOwnerResponse> transferTokenOwner(
        irismod.token.Tx.MsgTransferTokenOwner request) {
      return futureUnaryCall(
          getChannel().newCall(getTransferTokenOwnerMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ISSUE_TOKEN = 0;
  private static final int METHODID_EDIT_TOKEN = 1;
  private static final int METHODID_MINT_TOKEN = 2;
  private static final int METHODID_BURN_TOKEN = 3;
  private static final int METHODID_TRANSFER_TOKEN_OWNER = 4;

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
        case METHODID_ISSUE_TOKEN:
          serviceImpl.issueToken((irismod.token.Tx.MsgIssueToken) request,
              (io.grpc.stub.StreamObserver<irismod.token.Tx.MsgIssueTokenResponse>) responseObserver);
          break;
        case METHODID_EDIT_TOKEN:
          serviceImpl.editToken((irismod.token.Tx.MsgEditToken) request,
              (io.grpc.stub.StreamObserver<irismod.token.Tx.MsgEditTokenResponse>) responseObserver);
          break;
        case METHODID_MINT_TOKEN:
          serviceImpl.mintToken((irismod.token.Tx.MsgMintToken) request,
              (io.grpc.stub.StreamObserver<irismod.token.Tx.MsgMintTokenResponse>) responseObserver);
          break;
        case METHODID_BURN_TOKEN:
          serviceImpl.burnToken((irismod.token.Tx.MsgBurnToken) request,
              (io.grpc.stub.StreamObserver<irismod.token.Tx.MsgBurnTokenResponse>) responseObserver);
          break;
        case METHODID_TRANSFER_TOKEN_OWNER:
          serviceImpl.transferTokenOwner((irismod.token.Tx.MsgTransferTokenOwner) request,
              (io.grpc.stub.StreamObserver<irismod.token.Tx.MsgTransferTokenOwnerResponse>) responseObserver);
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
      return irismod.token.Tx.getDescriptor();
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
              .addMethod(getIssueTokenMethod())
              .addMethod(getEditTokenMethod())
              .addMethod(getMintTokenMethod())
              .addMethod(getBurnTokenMethod())
              .addMethod(getTransferTokenOwnerMethod())
              .build();
        }
      }
    }
    return result;
  }
}
