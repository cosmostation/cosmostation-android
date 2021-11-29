package bitsong.auction.v1beta1;

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
 * Msg defines the Auction Msg service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: bitsong/auction/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "bitsong.auction.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgOpenAuction,
      bitsong.auction.v1beta1.Tx.MsgOpenAuctionResponse> getOpenAuctionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OpenAuction",
      requestType = bitsong.auction.v1beta1.Tx.MsgOpenAuction.class,
      responseType = bitsong.auction.v1beta1.Tx.MsgOpenAuctionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgOpenAuction,
      bitsong.auction.v1beta1.Tx.MsgOpenAuctionResponse> getOpenAuctionMethod() {
    io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgOpenAuction, bitsong.auction.v1beta1.Tx.MsgOpenAuctionResponse> getOpenAuctionMethod;
    if ((getOpenAuctionMethod = MsgGrpc.getOpenAuctionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getOpenAuctionMethod = MsgGrpc.getOpenAuctionMethod) == null) {
          MsgGrpc.getOpenAuctionMethod = getOpenAuctionMethod =
              io.grpc.MethodDescriptor.<bitsong.auction.v1beta1.Tx.MsgOpenAuction, bitsong.auction.v1beta1.Tx.MsgOpenAuctionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OpenAuction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.Tx.MsgOpenAuction.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.Tx.MsgOpenAuctionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("OpenAuction"))
              .build();
        }
      }
    }
    return getOpenAuctionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgEditAuction,
      bitsong.auction.v1beta1.Tx.MsgEditAuctionResponse> getEditAuctionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditAuction",
      requestType = bitsong.auction.v1beta1.Tx.MsgEditAuction.class,
      responseType = bitsong.auction.v1beta1.Tx.MsgEditAuctionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgEditAuction,
      bitsong.auction.v1beta1.Tx.MsgEditAuctionResponse> getEditAuctionMethod() {
    io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgEditAuction, bitsong.auction.v1beta1.Tx.MsgEditAuctionResponse> getEditAuctionMethod;
    if ((getEditAuctionMethod = MsgGrpc.getEditAuctionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEditAuctionMethod = MsgGrpc.getEditAuctionMethod) == null) {
          MsgGrpc.getEditAuctionMethod = getEditAuctionMethod =
              io.grpc.MethodDescriptor.<bitsong.auction.v1beta1.Tx.MsgEditAuction, bitsong.auction.v1beta1.Tx.MsgEditAuctionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditAuction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.Tx.MsgEditAuction.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.Tx.MsgEditAuctionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EditAuction"))
              .build();
        }
      }
    }
    return getEditAuctionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgCancelAuction,
      bitsong.auction.v1beta1.Tx.MsgCancelAuctionResponse> getCancelAuctionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelAuction",
      requestType = bitsong.auction.v1beta1.Tx.MsgCancelAuction.class,
      responseType = bitsong.auction.v1beta1.Tx.MsgCancelAuctionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgCancelAuction,
      bitsong.auction.v1beta1.Tx.MsgCancelAuctionResponse> getCancelAuctionMethod() {
    io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgCancelAuction, bitsong.auction.v1beta1.Tx.MsgCancelAuctionResponse> getCancelAuctionMethod;
    if ((getCancelAuctionMethod = MsgGrpc.getCancelAuctionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelAuctionMethod = MsgGrpc.getCancelAuctionMethod) == null) {
          MsgGrpc.getCancelAuctionMethod = getCancelAuctionMethod =
              io.grpc.MethodDescriptor.<bitsong.auction.v1beta1.Tx.MsgCancelAuction, bitsong.auction.v1beta1.Tx.MsgCancelAuctionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelAuction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.Tx.MsgCancelAuction.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.Tx.MsgCancelAuctionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelAuction"))
              .build();
        }
      }
    }
    return getCancelAuctionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgOpenBid,
      bitsong.auction.v1beta1.Tx.MsgOpenBidResponse> getOpenBidMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OpenBid",
      requestType = bitsong.auction.v1beta1.Tx.MsgOpenBid.class,
      responseType = bitsong.auction.v1beta1.Tx.MsgOpenBidResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgOpenBid,
      bitsong.auction.v1beta1.Tx.MsgOpenBidResponse> getOpenBidMethod() {
    io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgOpenBid, bitsong.auction.v1beta1.Tx.MsgOpenBidResponse> getOpenBidMethod;
    if ((getOpenBidMethod = MsgGrpc.getOpenBidMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getOpenBidMethod = MsgGrpc.getOpenBidMethod) == null) {
          MsgGrpc.getOpenBidMethod = getOpenBidMethod =
              io.grpc.MethodDescriptor.<bitsong.auction.v1beta1.Tx.MsgOpenBid, bitsong.auction.v1beta1.Tx.MsgOpenBidResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OpenBid"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.Tx.MsgOpenBid.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.Tx.MsgOpenBidResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("OpenBid"))
              .build();
        }
      }
    }
    return getOpenBidMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgCancelBid,
      bitsong.auction.v1beta1.Tx.MsgCancelBidResponse> getCancelBidMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelBid",
      requestType = bitsong.auction.v1beta1.Tx.MsgCancelBid.class,
      responseType = bitsong.auction.v1beta1.Tx.MsgCancelBidResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgCancelBid,
      bitsong.auction.v1beta1.Tx.MsgCancelBidResponse> getCancelBidMethod() {
    io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgCancelBid, bitsong.auction.v1beta1.Tx.MsgCancelBidResponse> getCancelBidMethod;
    if ((getCancelBidMethod = MsgGrpc.getCancelBidMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelBidMethod = MsgGrpc.getCancelBidMethod) == null) {
          MsgGrpc.getCancelBidMethod = getCancelBidMethod =
              io.grpc.MethodDescriptor.<bitsong.auction.v1beta1.Tx.MsgCancelBid, bitsong.auction.v1beta1.Tx.MsgCancelBidResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelBid"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.Tx.MsgCancelBid.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.Tx.MsgCancelBidResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelBid"))
              .build();
        }
      }
    }
    return getCancelBidMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgWithdraw,
      bitsong.auction.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Withdraw",
      requestType = bitsong.auction.v1beta1.Tx.MsgWithdraw.class,
      responseType = bitsong.auction.v1beta1.Tx.MsgWithdrawResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgWithdraw,
      bitsong.auction.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod() {
    io.grpc.MethodDescriptor<bitsong.auction.v1beta1.Tx.MsgWithdraw, bitsong.auction.v1beta1.Tx.MsgWithdrawResponse> getWithdrawMethod;
    if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawMethod = MsgGrpc.getWithdrawMethod) == null) {
          MsgGrpc.getWithdrawMethod = getWithdrawMethod =
              io.grpc.MethodDescriptor.<bitsong.auction.v1beta1.Tx.MsgWithdraw, bitsong.auction.v1beta1.Tx.MsgWithdrawResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Withdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.Tx.MsgWithdraw.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.auction.v1beta1.Tx.MsgWithdrawResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Withdraw"))
              .build();
        }
      }
    }
    return getWithdrawMethod;
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
   * Msg defines the Auction Msg service
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * OpenAuction defines a method for opening an auction
     * </pre>
     */
    public void openAuction(bitsong.auction.v1beta1.Tx.MsgOpenAuction request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgOpenAuctionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOpenAuctionMethod(), responseObserver);
    }

    /**
     * <pre>
     * EditAuction defines a method for editting an auction
     * </pre>
     */
    public void editAuction(bitsong.auction.v1beta1.Tx.MsgEditAuction request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgEditAuctionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEditAuctionMethod(), responseObserver);
    }

    /**
     * <pre>
     * CancelAuction defines a method for cancelling an auction
     * </pre>
     */
    public void cancelAuction(bitsong.auction.v1beta1.Tx.MsgCancelAuction request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgCancelAuctionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelAuctionMethod(), responseObserver);
    }

    /**
     * <pre>
     * OpenBid defines a method for opening a bid for an auction
     * </pre>
     */
    public void openBid(bitsong.auction.v1beta1.Tx.MsgOpenBid request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgOpenBidResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOpenBidMethod(), responseObserver);
    }

    /**
     * <pre>
     * CancelBid defines a method for cancelling a bid for an auction
     * </pre>
     */
    public void cancelBid(bitsong.auction.v1beta1.Tx.MsgCancelBid request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgCancelBidResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelBidMethod(), responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing nft for an auction winner
     * </pre>
     */
    public void withdraw(bitsong.auction.v1beta1.Tx.MsgWithdraw request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgWithdrawResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getOpenAuctionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.auction.v1beta1.Tx.MsgOpenAuction,
                bitsong.auction.v1beta1.Tx.MsgOpenAuctionResponse>(
                  this, METHODID_OPEN_AUCTION)))
          .addMethod(
            getEditAuctionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.auction.v1beta1.Tx.MsgEditAuction,
                bitsong.auction.v1beta1.Tx.MsgEditAuctionResponse>(
                  this, METHODID_EDIT_AUCTION)))
          .addMethod(
            getCancelAuctionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.auction.v1beta1.Tx.MsgCancelAuction,
                bitsong.auction.v1beta1.Tx.MsgCancelAuctionResponse>(
                  this, METHODID_CANCEL_AUCTION)))
          .addMethod(
            getOpenBidMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.auction.v1beta1.Tx.MsgOpenBid,
                bitsong.auction.v1beta1.Tx.MsgOpenBidResponse>(
                  this, METHODID_OPEN_BID)))
          .addMethod(
            getCancelBidMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.auction.v1beta1.Tx.MsgCancelBid,
                bitsong.auction.v1beta1.Tx.MsgCancelBidResponse>(
                  this, METHODID_CANCEL_BID)))
          .addMethod(
            getWithdrawMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.auction.v1beta1.Tx.MsgWithdraw,
                bitsong.auction.v1beta1.Tx.MsgWithdrawResponse>(
                  this, METHODID_WITHDRAW)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the Auction Msg service
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
     * OpenAuction defines a method for opening an auction
     * </pre>
     */
    public void openAuction(bitsong.auction.v1beta1.Tx.MsgOpenAuction request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgOpenAuctionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOpenAuctionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EditAuction defines a method for editting an auction
     * </pre>
     */
    public void editAuction(bitsong.auction.v1beta1.Tx.MsgEditAuction request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgEditAuctionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEditAuctionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CancelAuction defines a method for cancelling an auction
     * </pre>
     */
    public void cancelAuction(bitsong.auction.v1beta1.Tx.MsgCancelAuction request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgCancelAuctionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelAuctionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * OpenBid defines a method for opening a bid for an auction
     * </pre>
     */
    public void openBid(bitsong.auction.v1beta1.Tx.MsgOpenBid request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgOpenBidResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOpenBidMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CancelBid defines a method for cancelling a bid for an auction
     * </pre>
     */
    public void cancelBid(bitsong.auction.v1beta1.Tx.MsgCancelBid request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgCancelBidResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelBidMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing nft for an auction winner
     * </pre>
     */
    public void withdraw(bitsong.auction.v1beta1.Tx.MsgWithdraw request,
        io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgWithdrawResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the Auction Msg service
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
     * OpenAuction defines a method for opening an auction
     * </pre>
     */
    public bitsong.auction.v1beta1.Tx.MsgOpenAuctionResponse openAuction(bitsong.auction.v1beta1.Tx.MsgOpenAuction request) {
      return blockingUnaryCall(
          getChannel(), getOpenAuctionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EditAuction defines a method for editting an auction
     * </pre>
     */
    public bitsong.auction.v1beta1.Tx.MsgEditAuctionResponse editAuction(bitsong.auction.v1beta1.Tx.MsgEditAuction request) {
      return blockingUnaryCall(
          getChannel(), getEditAuctionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CancelAuction defines a method for cancelling an auction
     * </pre>
     */
    public bitsong.auction.v1beta1.Tx.MsgCancelAuctionResponse cancelAuction(bitsong.auction.v1beta1.Tx.MsgCancelAuction request) {
      return blockingUnaryCall(
          getChannel(), getCancelAuctionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * OpenBid defines a method for opening a bid for an auction
     * </pre>
     */
    public bitsong.auction.v1beta1.Tx.MsgOpenBidResponse openBid(bitsong.auction.v1beta1.Tx.MsgOpenBid request) {
      return blockingUnaryCall(
          getChannel(), getOpenBidMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CancelBid defines a method for cancelling a bid for an auction
     * </pre>
     */
    public bitsong.auction.v1beta1.Tx.MsgCancelBidResponse cancelBid(bitsong.auction.v1beta1.Tx.MsgCancelBid request) {
      return blockingUnaryCall(
          getChannel(), getCancelBidMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing nft for an auction winner
     * </pre>
     */
    public bitsong.auction.v1beta1.Tx.MsgWithdrawResponse withdraw(bitsong.auction.v1beta1.Tx.MsgWithdraw request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the Auction Msg service
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
     * OpenAuction defines a method for opening an auction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.auction.v1beta1.Tx.MsgOpenAuctionResponse> openAuction(
        bitsong.auction.v1beta1.Tx.MsgOpenAuction request) {
      return futureUnaryCall(
          getChannel().newCall(getOpenAuctionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EditAuction defines a method for editting an auction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.auction.v1beta1.Tx.MsgEditAuctionResponse> editAuction(
        bitsong.auction.v1beta1.Tx.MsgEditAuction request) {
      return futureUnaryCall(
          getChannel().newCall(getEditAuctionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CancelAuction defines a method for cancelling an auction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.auction.v1beta1.Tx.MsgCancelAuctionResponse> cancelAuction(
        bitsong.auction.v1beta1.Tx.MsgCancelAuction request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelAuctionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * OpenBid defines a method for opening a bid for an auction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.auction.v1beta1.Tx.MsgOpenBidResponse> openBid(
        bitsong.auction.v1beta1.Tx.MsgOpenBid request) {
      return futureUnaryCall(
          getChannel().newCall(getOpenBidMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CancelBid defines a method for cancelling a bid for an auction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.auction.v1beta1.Tx.MsgCancelBidResponse> cancelBid(
        bitsong.auction.v1beta1.Tx.MsgCancelBid request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelBidMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Withdraw defines a method for withdrawing nft for an auction winner
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.auction.v1beta1.Tx.MsgWithdrawResponse> withdraw(
        bitsong.auction.v1beta1.Tx.MsgWithdraw request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_OPEN_AUCTION = 0;
  private static final int METHODID_EDIT_AUCTION = 1;
  private static final int METHODID_CANCEL_AUCTION = 2;
  private static final int METHODID_OPEN_BID = 3;
  private static final int METHODID_CANCEL_BID = 4;
  private static final int METHODID_WITHDRAW = 5;

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
        case METHODID_OPEN_AUCTION:
          serviceImpl.openAuction((bitsong.auction.v1beta1.Tx.MsgOpenAuction) request,
              (io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgOpenAuctionResponse>) responseObserver);
          break;
        case METHODID_EDIT_AUCTION:
          serviceImpl.editAuction((bitsong.auction.v1beta1.Tx.MsgEditAuction) request,
              (io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgEditAuctionResponse>) responseObserver);
          break;
        case METHODID_CANCEL_AUCTION:
          serviceImpl.cancelAuction((bitsong.auction.v1beta1.Tx.MsgCancelAuction) request,
              (io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgCancelAuctionResponse>) responseObserver);
          break;
        case METHODID_OPEN_BID:
          serviceImpl.openBid((bitsong.auction.v1beta1.Tx.MsgOpenBid) request,
              (io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgOpenBidResponse>) responseObserver);
          break;
        case METHODID_CANCEL_BID:
          serviceImpl.cancelBid((bitsong.auction.v1beta1.Tx.MsgCancelBid) request,
              (io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgCancelBidResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW:
          serviceImpl.withdraw((bitsong.auction.v1beta1.Tx.MsgWithdraw) request,
              (io.grpc.stub.StreamObserver<bitsong.auction.v1beta1.Tx.MsgWithdrawResponse>) responseObserver);
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
      return bitsong.auction.v1beta1.Tx.getDescriptor();
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
              .addMethod(getOpenAuctionMethod())
              .addMethod(getEditAuctionMethod())
              .addMethod(getCancelAuctionMethod())
              .addMethod(getOpenBidMethod())
              .addMethod(getCancelBidMethod())
              .addMethod(getWithdrawMethod())
              .build();
        }
      }
    }
    return result;
  }
}
