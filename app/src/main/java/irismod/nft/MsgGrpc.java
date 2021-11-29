package irismod.nft;

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
 * Msg defines the nft Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: nft/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "irismod.nft.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<irismod.nft.Tx.MsgIssueDenom,
      irismod.nft.Tx.MsgIssueDenomResponse> getIssueDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IssueDenom",
      requestType = irismod.nft.Tx.MsgIssueDenom.class,
      responseType = irismod.nft.Tx.MsgIssueDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.nft.Tx.MsgIssueDenom,
      irismod.nft.Tx.MsgIssueDenomResponse> getIssueDenomMethod() {
    io.grpc.MethodDescriptor<irismod.nft.Tx.MsgIssueDenom, irismod.nft.Tx.MsgIssueDenomResponse> getIssueDenomMethod;
    if ((getIssueDenomMethod = MsgGrpc.getIssueDenomMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getIssueDenomMethod = MsgGrpc.getIssueDenomMethod) == null) {
          MsgGrpc.getIssueDenomMethod = getIssueDenomMethod =
              io.grpc.MethodDescriptor.<irismod.nft.Tx.MsgIssueDenom, irismod.nft.Tx.MsgIssueDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IssueDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.nft.Tx.MsgIssueDenom.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.nft.Tx.MsgIssueDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("IssueDenom"))
              .build();
        }
      }
    }
    return getIssueDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.nft.Tx.MsgMintNFT,
      irismod.nft.Tx.MsgMintNFTResponse> getMintNFTMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MintNFT",
      requestType = irismod.nft.Tx.MsgMintNFT.class,
      responseType = irismod.nft.Tx.MsgMintNFTResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.nft.Tx.MsgMintNFT,
      irismod.nft.Tx.MsgMintNFTResponse> getMintNFTMethod() {
    io.grpc.MethodDescriptor<irismod.nft.Tx.MsgMintNFT, irismod.nft.Tx.MsgMintNFTResponse> getMintNFTMethod;
    if ((getMintNFTMethod = MsgGrpc.getMintNFTMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMintNFTMethod = MsgGrpc.getMintNFTMethod) == null) {
          MsgGrpc.getMintNFTMethod = getMintNFTMethod =
              io.grpc.MethodDescriptor.<irismod.nft.Tx.MsgMintNFT, irismod.nft.Tx.MsgMintNFTResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MintNFT"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.nft.Tx.MsgMintNFT.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.nft.Tx.MsgMintNFTResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("MintNFT"))
              .build();
        }
      }
    }
    return getMintNFTMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.nft.Tx.MsgEditNFT,
      irismod.nft.Tx.MsgEditNFTResponse> getEditNFTMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditNFT",
      requestType = irismod.nft.Tx.MsgEditNFT.class,
      responseType = irismod.nft.Tx.MsgEditNFTResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.nft.Tx.MsgEditNFT,
      irismod.nft.Tx.MsgEditNFTResponse> getEditNFTMethod() {
    io.grpc.MethodDescriptor<irismod.nft.Tx.MsgEditNFT, irismod.nft.Tx.MsgEditNFTResponse> getEditNFTMethod;
    if ((getEditNFTMethod = MsgGrpc.getEditNFTMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEditNFTMethod = MsgGrpc.getEditNFTMethod) == null) {
          MsgGrpc.getEditNFTMethod = getEditNFTMethod =
              io.grpc.MethodDescriptor.<irismod.nft.Tx.MsgEditNFT, irismod.nft.Tx.MsgEditNFTResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditNFT"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.nft.Tx.MsgEditNFT.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.nft.Tx.MsgEditNFTResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EditNFT"))
              .build();
        }
      }
    }
    return getEditNFTMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.nft.Tx.MsgTransferNFT,
      irismod.nft.Tx.MsgTransferNFTResponse> getTransferNFTMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TransferNFT",
      requestType = irismod.nft.Tx.MsgTransferNFT.class,
      responseType = irismod.nft.Tx.MsgTransferNFTResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.nft.Tx.MsgTransferNFT,
      irismod.nft.Tx.MsgTransferNFTResponse> getTransferNFTMethod() {
    io.grpc.MethodDescriptor<irismod.nft.Tx.MsgTransferNFT, irismod.nft.Tx.MsgTransferNFTResponse> getTransferNFTMethod;
    if ((getTransferNFTMethod = MsgGrpc.getTransferNFTMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getTransferNFTMethod = MsgGrpc.getTransferNFTMethod) == null) {
          MsgGrpc.getTransferNFTMethod = getTransferNFTMethod =
              io.grpc.MethodDescriptor.<irismod.nft.Tx.MsgTransferNFT, irismod.nft.Tx.MsgTransferNFTResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TransferNFT"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.nft.Tx.MsgTransferNFT.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.nft.Tx.MsgTransferNFTResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("TransferNFT"))
              .build();
        }
      }
    }
    return getTransferNFTMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.nft.Tx.MsgBurnNFT,
      irismod.nft.Tx.MsgBurnNFTResponse> getBurnNFTMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BurnNFT",
      requestType = irismod.nft.Tx.MsgBurnNFT.class,
      responseType = irismod.nft.Tx.MsgBurnNFTResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.nft.Tx.MsgBurnNFT,
      irismod.nft.Tx.MsgBurnNFTResponse> getBurnNFTMethod() {
    io.grpc.MethodDescriptor<irismod.nft.Tx.MsgBurnNFT, irismod.nft.Tx.MsgBurnNFTResponse> getBurnNFTMethod;
    if ((getBurnNFTMethod = MsgGrpc.getBurnNFTMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBurnNFTMethod = MsgGrpc.getBurnNFTMethod) == null) {
          MsgGrpc.getBurnNFTMethod = getBurnNFTMethod =
              io.grpc.MethodDescriptor.<irismod.nft.Tx.MsgBurnNFT, irismod.nft.Tx.MsgBurnNFTResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BurnNFT"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.nft.Tx.MsgBurnNFT.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.nft.Tx.MsgBurnNFTResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BurnNFT"))
              .build();
        }
      }
    }
    return getBurnNFTMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.nft.Tx.MsgTransferDenom,
      irismod.nft.Tx.MsgTransferDenomResponse> getTransferDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TransferDenom",
      requestType = irismod.nft.Tx.MsgTransferDenom.class,
      responseType = irismod.nft.Tx.MsgTransferDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.nft.Tx.MsgTransferDenom,
      irismod.nft.Tx.MsgTransferDenomResponse> getTransferDenomMethod() {
    io.grpc.MethodDescriptor<irismod.nft.Tx.MsgTransferDenom, irismod.nft.Tx.MsgTransferDenomResponse> getTransferDenomMethod;
    if ((getTransferDenomMethod = MsgGrpc.getTransferDenomMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getTransferDenomMethod = MsgGrpc.getTransferDenomMethod) == null) {
          MsgGrpc.getTransferDenomMethod = getTransferDenomMethod =
              io.grpc.MethodDescriptor.<irismod.nft.Tx.MsgTransferDenom, irismod.nft.Tx.MsgTransferDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TransferDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.nft.Tx.MsgTransferDenom.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.nft.Tx.MsgTransferDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("TransferDenom"))
              .build();
        }
      }
    }
    return getTransferDenomMethod;
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
   * Msg defines the nft Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * IssueDenom defines a method for issue a denom.
     * </pre>
     */
    public void issueDenom(irismod.nft.Tx.MsgIssueDenom request,
        io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgIssueDenomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getIssueDenomMethod(), responseObserver);
    }

    /**
     * <pre>
     * MintNFT defines a method for mint a new nft
     * </pre>
     */
    public void mintNFT(irismod.nft.Tx.MsgMintNFT request,
        io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgMintNFTResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMintNFTMethod(), responseObserver);
    }

    /**
     * <pre>
     * RefundHTLC defines a method for editing a nft.
     * </pre>
     */
    public void editNFT(irismod.nft.Tx.MsgEditNFT request,
        io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgEditNFTResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEditNFTMethod(), responseObserver);
    }

    /**
     * <pre>
     * TransferNFT defines a method for transferring a nft.
     * </pre>
     */
    public void transferNFT(irismod.nft.Tx.MsgTransferNFT request,
        io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgTransferNFTResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTransferNFTMethod(), responseObserver);
    }

    /**
     * <pre>
     * BurnNFT defines a method for burning a nft.
     * </pre>
     */
    public void burnNFT(irismod.nft.Tx.MsgBurnNFT request,
        io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgBurnNFTResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBurnNFTMethod(), responseObserver);
    }

    /**
     * <pre>
     * TransferDenom defines a method for transferring a denom.
     * </pre>
     */
    public void transferDenom(irismod.nft.Tx.MsgTransferDenom request,
        io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgTransferDenomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTransferDenomMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getIssueDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.nft.Tx.MsgIssueDenom,
                irismod.nft.Tx.MsgIssueDenomResponse>(
                  this, METHODID_ISSUE_DENOM)))
          .addMethod(
            getMintNFTMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.nft.Tx.MsgMintNFT,
                irismod.nft.Tx.MsgMintNFTResponse>(
                  this, METHODID_MINT_NFT)))
          .addMethod(
            getEditNFTMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.nft.Tx.MsgEditNFT,
                irismod.nft.Tx.MsgEditNFTResponse>(
                  this, METHODID_EDIT_NFT)))
          .addMethod(
            getTransferNFTMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.nft.Tx.MsgTransferNFT,
                irismod.nft.Tx.MsgTransferNFTResponse>(
                  this, METHODID_TRANSFER_NFT)))
          .addMethod(
            getBurnNFTMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.nft.Tx.MsgBurnNFT,
                irismod.nft.Tx.MsgBurnNFTResponse>(
                  this, METHODID_BURN_NFT)))
          .addMethod(
            getTransferDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.nft.Tx.MsgTransferDenom,
                irismod.nft.Tx.MsgTransferDenomResponse>(
                  this, METHODID_TRANSFER_DENOM)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the nft Msg service.
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
     * IssueDenom defines a method for issue a denom.
     * </pre>
     */
    public void issueDenom(irismod.nft.Tx.MsgIssueDenom request,
        io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgIssueDenomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIssueDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MintNFT defines a method for mint a new nft
     * </pre>
     */
    public void mintNFT(irismod.nft.Tx.MsgMintNFT request,
        io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgMintNFTResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMintNFTMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RefundHTLC defines a method for editing a nft.
     * </pre>
     */
    public void editNFT(irismod.nft.Tx.MsgEditNFT request,
        io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgEditNFTResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEditNFTMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TransferNFT defines a method for transferring a nft.
     * </pre>
     */
    public void transferNFT(irismod.nft.Tx.MsgTransferNFT request,
        io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgTransferNFTResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTransferNFTMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BurnNFT defines a method for burning a nft.
     * </pre>
     */
    public void burnNFT(irismod.nft.Tx.MsgBurnNFT request,
        io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgBurnNFTResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBurnNFTMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TransferDenom defines a method for transferring a denom.
     * </pre>
     */
    public void transferDenom(irismod.nft.Tx.MsgTransferDenom request,
        io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgTransferDenomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTransferDenomMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the nft Msg service.
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
     * IssueDenom defines a method for issue a denom.
     * </pre>
     */
    public irismod.nft.Tx.MsgIssueDenomResponse issueDenom(irismod.nft.Tx.MsgIssueDenom request) {
      return blockingUnaryCall(
          getChannel(), getIssueDenomMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MintNFT defines a method for mint a new nft
     * </pre>
     */
    public irismod.nft.Tx.MsgMintNFTResponse mintNFT(irismod.nft.Tx.MsgMintNFT request) {
      return blockingUnaryCall(
          getChannel(), getMintNFTMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RefundHTLC defines a method for editing a nft.
     * </pre>
     */
    public irismod.nft.Tx.MsgEditNFTResponse editNFT(irismod.nft.Tx.MsgEditNFT request) {
      return blockingUnaryCall(
          getChannel(), getEditNFTMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TransferNFT defines a method for transferring a nft.
     * </pre>
     */
    public irismod.nft.Tx.MsgTransferNFTResponse transferNFT(irismod.nft.Tx.MsgTransferNFT request) {
      return blockingUnaryCall(
          getChannel(), getTransferNFTMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BurnNFT defines a method for burning a nft.
     * </pre>
     */
    public irismod.nft.Tx.MsgBurnNFTResponse burnNFT(irismod.nft.Tx.MsgBurnNFT request) {
      return blockingUnaryCall(
          getChannel(), getBurnNFTMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TransferDenom defines a method for transferring a denom.
     * </pre>
     */
    public irismod.nft.Tx.MsgTransferDenomResponse transferDenom(irismod.nft.Tx.MsgTransferDenom request) {
      return blockingUnaryCall(
          getChannel(), getTransferDenomMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the nft Msg service.
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
     * IssueDenom defines a method for issue a denom.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.nft.Tx.MsgIssueDenomResponse> issueDenom(
        irismod.nft.Tx.MsgIssueDenom request) {
      return futureUnaryCall(
          getChannel().newCall(getIssueDenomMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MintNFT defines a method for mint a new nft
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.nft.Tx.MsgMintNFTResponse> mintNFT(
        irismod.nft.Tx.MsgMintNFT request) {
      return futureUnaryCall(
          getChannel().newCall(getMintNFTMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RefundHTLC defines a method for editing a nft.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.nft.Tx.MsgEditNFTResponse> editNFT(
        irismod.nft.Tx.MsgEditNFT request) {
      return futureUnaryCall(
          getChannel().newCall(getEditNFTMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TransferNFT defines a method for transferring a nft.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.nft.Tx.MsgTransferNFTResponse> transferNFT(
        irismod.nft.Tx.MsgTransferNFT request) {
      return futureUnaryCall(
          getChannel().newCall(getTransferNFTMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BurnNFT defines a method for burning a nft.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.nft.Tx.MsgBurnNFTResponse> burnNFT(
        irismod.nft.Tx.MsgBurnNFT request) {
      return futureUnaryCall(
          getChannel().newCall(getBurnNFTMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TransferDenom defines a method for transferring a denom.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.nft.Tx.MsgTransferDenomResponse> transferDenom(
        irismod.nft.Tx.MsgTransferDenom request) {
      return futureUnaryCall(
          getChannel().newCall(getTransferDenomMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ISSUE_DENOM = 0;
  private static final int METHODID_MINT_NFT = 1;
  private static final int METHODID_EDIT_NFT = 2;
  private static final int METHODID_TRANSFER_NFT = 3;
  private static final int METHODID_BURN_NFT = 4;
  private static final int METHODID_TRANSFER_DENOM = 5;

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
        case METHODID_ISSUE_DENOM:
          serviceImpl.issueDenom((irismod.nft.Tx.MsgIssueDenom) request,
              (io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgIssueDenomResponse>) responseObserver);
          break;
        case METHODID_MINT_NFT:
          serviceImpl.mintNFT((irismod.nft.Tx.MsgMintNFT) request,
              (io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgMintNFTResponse>) responseObserver);
          break;
        case METHODID_EDIT_NFT:
          serviceImpl.editNFT((irismod.nft.Tx.MsgEditNFT) request,
              (io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgEditNFTResponse>) responseObserver);
          break;
        case METHODID_TRANSFER_NFT:
          serviceImpl.transferNFT((irismod.nft.Tx.MsgTransferNFT) request,
              (io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgTransferNFTResponse>) responseObserver);
          break;
        case METHODID_BURN_NFT:
          serviceImpl.burnNFT((irismod.nft.Tx.MsgBurnNFT) request,
              (io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgBurnNFTResponse>) responseObserver);
          break;
        case METHODID_TRANSFER_DENOM:
          serviceImpl.transferDenom((irismod.nft.Tx.MsgTransferDenom) request,
              (io.grpc.stub.StreamObserver<irismod.nft.Tx.MsgTransferDenomResponse>) responseObserver);
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
      return irismod.nft.Tx.getDescriptor();
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
              .addMethod(getIssueDenomMethod())
              .addMethod(getMintNFTMethod())
              .addMethod(getEditNFTMethod())
              .addMethod(getTransferNFTMethod())
              .addMethod(getBurnNFTMethod())
              .addMethod(getTransferDenomMethod())
              .build();
        }
      }
    }
    return result;
  }
}
