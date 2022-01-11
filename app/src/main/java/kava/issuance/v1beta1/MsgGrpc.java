package kava.issuance.v1beta1;

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
 * Msg defines the issuance Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/issuance/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.issuance.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.issuance.v1beta1.Tx.MsgIssueTokens,
      kava.issuance.v1beta1.Tx.MsgIssueTokensResponse> getIssueTokensMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IssueTokens",
      requestType = kava.issuance.v1beta1.Tx.MsgIssueTokens.class,
      responseType = kava.issuance.v1beta1.Tx.MsgIssueTokensResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.issuance.v1beta1.Tx.MsgIssueTokens,
      kava.issuance.v1beta1.Tx.MsgIssueTokensResponse> getIssueTokensMethod() {
    io.grpc.MethodDescriptor<kava.issuance.v1beta1.Tx.MsgIssueTokens, kava.issuance.v1beta1.Tx.MsgIssueTokensResponse> getIssueTokensMethod;
    if ((getIssueTokensMethod = MsgGrpc.getIssueTokensMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getIssueTokensMethod = MsgGrpc.getIssueTokensMethod) == null) {
          MsgGrpc.getIssueTokensMethod = getIssueTokensMethod =
              io.grpc.MethodDescriptor.<kava.issuance.v1beta1.Tx.MsgIssueTokens, kava.issuance.v1beta1.Tx.MsgIssueTokensResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IssueTokens"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.issuance.v1beta1.Tx.MsgIssueTokens.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.issuance.v1beta1.Tx.MsgIssueTokensResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("IssueTokens"))
              .build();
        }
      }
    }
    return getIssueTokensMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.issuance.v1beta1.Tx.MsgRedeemTokens,
      kava.issuance.v1beta1.Tx.MsgRedeemTokensResponse> getRedeemTokensMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RedeemTokens",
      requestType = kava.issuance.v1beta1.Tx.MsgRedeemTokens.class,
      responseType = kava.issuance.v1beta1.Tx.MsgRedeemTokensResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.issuance.v1beta1.Tx.MsgRedeemTokens,
      kava.issuance.v1beta1.Tx.MsgRedeemTokensResponse> getRedeemTokensMethod() {
    io.grpc.MethodDescriptor<kava.issuance.v1beta1.Tx.MsgRedeemTokens, kava.issuance.v1beta1.Tx.MsgRedeemTokensResponse> getRedeemTokensMethod;
    if ((getRedeemTokensMethod = MsgGrpc.getRedeemTokensMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRedeemTokensMethod = MsgGrpc.getRedeemTokensMethod) == null) {
          MsgGrpc.getRedeemTokensMethod = getRedeemTokensMethod =
              io.grpc.MethodDescriptor.<kava.issuance.v1beta1.Tx.MsgRedeemTokens, kava.issuance.v1beta1.Tx.MsgRedeemTokensResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RedeemTokens"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.issuance.v1beta1.Tx.MsgRedeemTokens.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.issuance.v1beta1.Tx.MsgRedeemTokensResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RedeemTokens"))
              .build();
        }
      }
    }
    return getRedeemTokensMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.issuance.v1beta1.Tx.MsgBlockAddress,
      kava.issuance.v1beta1.Tx.MsgBlockAddressResponse> getBlockAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BlockAddress",
      requestType = kava.issuance.v1beta1.Tx.MsgBlockAddress.class,
      responseType = kava.issuance.v1beta1.Tx.MsgBlockAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.issuance.v1beta1.Tx.MsgBlockAddress,
      kava.issuance.v1beta1.Tx.MsgBlockAddressResponse> getBlockAddressMethod() {
    io.grpc.MethodDescriptor<kava.issuance.v1beta1.Tx.MsgBlockAddress, kava.issuance.v1beta1.Tx.MsgBlockAddressResponse> getBlockAddressMethod;
    if ((getBlockAddressMethod = MsgGrpc.getBlockAddressMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBlockAddressMethod = MsgGrpc.getBlockAddressMethod) == null) {
          MsgGrpc.getBlockAddressMethod = getBlockAddressMethod =
              io.grpc.MethodDescriptor.<kava.issuance.v1beta1.Tx.MsgBlockAddress, kava.issuance.v1beta1.Tx.MsgBlockAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BlockAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.issuance.v1beta1.Tx.MsgBlockAddress.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.issuance.v1beta1.Tx.MsgBlockAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BlockAddress"))
              .build();
        }
      }
    }
    return getBlockAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.issuance.v1beta1.Tx.MsgUnblockAddress,
      kava.issuance.v1beta1.Tx.MsgUnblockAddressResponse> getUnblockAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnblockAddress",
      requestType = kava.issuance.v1beta1.Tx.MsgUnblockAddress.class,
      responseType = kava.issuance.v1beta1.Tx.MsgUnblockAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.issuance.v1beta1.Tx.MsgUnblockAddress,
      kava.issuance.v1beta1.Tx.MsgUnblockAddressResponse> getUnblockAddressMethod() {
    io.grpc.MethodDescriptor<kava.issuance.v1beta1.Tx.MsgUnblockAddress, kava.issuance.v1beta1.Tx.MsgUnblockAddressResponse> getUnblockAddressMethod;
    if ((getUnblockAddressMethod = MsgGrpc.getUnblockAddressMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUnblockAddressMethod = MsgGrpc.getUnblockAddressMethod) == null) {
          MsgGrpc.getUnblockAddressMethod = getUnblockAddressMethod =
              io.grpc.MethodDescriptor.<kava.issuance.v1beta1.Tx.MsgUnblockAddress, kava.issuance.v1beta1.Tx.MsgUnblockAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnblockAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.issuance.v1beta1.Tx.MsgUnblockAddress.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.issuance.v1beta1.Tx.MsgUnblockAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UnblockAddress"))
              .build();
        }
      }
    }
    return getUnblockAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.issuance.v1beta1.Tx.MsgSetPauseStatus,
      kava.issuance.v1beta1.Tx.MsgSetPauseStatusResponse> getSetPauseStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetPauseStatus",
      requestType = kava.issuance.v1beta1.Tx.MsgSetPauseStatus.class,
      responseType = kava.issuance.v1beta1.Tx.MsgSetPauseStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.issuance.v1beta1.Tx.MsgSetPauseStatus,
      kava.issuance.v1beta1.Tx.MsgSetPauseStatusResponse> getSetPauseStatusMethod() {
    io.grpc.MethodDescriptor<kava.issuance.v1beta1.Tx.MsgSetPauseStatus, kava.issuance.v1beta1.Tx.MsgSetPauseStatusResponse> getSetPauseStatusMethod;
    if ((getSetPauseStatusMethod = MsgGrpc.getSetPauseStatusMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetPauseStatusMethod = MsgGrpc.getSetPauseStatusMethod) == null) {
          MsgGrpc.getSetPauseStatusMethod = getSetPauseStatusMethod =
              io.grpc.MethodDescriptor.<kava.issuance.v1beta1.Tx.MsgSetPauseStatus, kava.issuance.v1beta1.Tx.MsgSetPauseStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetPauseStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.issuance.v1beta1.Tx.MsgSetPauseStatus.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.issuance.v1beta1.Tx.MsgSetPauseStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetPauseStatus"))
              .build();
        }
      }
    }
    return getSetPauseStatusMethod;
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
   * Msg defines the issuance Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * IssueTokens message type used by the issuer to issue new tokens
     * </pre>
     */
    public void issueTokens(kava.issuance.v1beta1.Tx.MsgIssueTokens request,
        io.grpc.stub.StreamObserver<kava.issuance.v1beta1.Tx.MsgIssueTokensResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getIssueTokensMethod(), responseObserver);
    }

    /**
     * <pre>
     * RedeemTokens message type used by the issuer to redeem (burn) tokens
     * </pre>
     */
    public void redeemTokens(kava.issuance.v1beta1.Tx.MsgRedeemTokens request,
        io.grpc.stub.StreamObserver<kava.issuance.v1beta1.Tx.MsgRedeemTokensResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRedeemTokensMethod(), responseObserver);
    }

    /**
     * <pre>
     * BlockAddress message type used by the issuer to block an address from holding or transferring tokens
     * </pre>
     */
    public void blockAddress(kava.issuance.v1beta1.Tx.MsgBlockAddress request,
        io.grpc.stub.StreamObserver<kava.issuance.v1beta1.Tx.MsgBlockAddressResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBlockAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnblockAddress message type used by the issuer to unblock an address from holding or transferring tokens
     * </pre>
     */
    public void unblockAddress(kava.issuance.v1beta1.Tx.MsgUnblockAddress request,
        io.grpc.stub.StreamObserver<kava.issuance.v1beta1.Tx.MsgUnblockAddressResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnblockAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * SetPauseStatus message type used to pause or unpause status
     * </pre>
     */
    public void setPauseStatus(kava.issuance.v1beta1.Tx.MsgSetPauseStatus request,
        io.grpc.stub.StreamObserver<kava.issuance.v1beta1.Tx.MsgSetPauseStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetPauseStatusMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getIssueTokensMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.issuance.v1beta1.Tx.MsgIssueTokens,
                kava.issuance.v1beta1.Tx.MsgIssueTokensResponse>(
                  this, METHODID_ISSUE_TOKENS)))
          .addMethod(
            getRedeemTokensMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.issuance.v1beta1.Tx.MsgRedeemTokens,
                kava.issuance.v1beta1.Tx.MsgRedeemTokensResponse>(
                  this, METHODID_REDEEM_TOKENS)))
          .addMethod(
            getBlockAddressMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.issuance.v1beta1.Tx.MsgBlockAddress,
                kava.issuance.v1beta1.Tx.MsgBlockAddressResponse>(
                  this, METHODID_BLOCK_ADDRESS)))
          .addMethod(
            getUnblockAddressMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.issuance.v1beta1.Tx.MsgUnblockAddress,
                kava.issuance.v1beta1.Tx.MsgUnblockAddressResponse>(
                  this, METHODID_UNBLOCK_ADDRESS)))
          .addMethod(
            getSetPauseStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.issuance.v1beta1.Tx.MsgSetPauseStatus,
                kava.issuance.v1beta1.Tx.MsgSetPauseStatusResponse>(
                  this, METHODID_SET_PAUSE_STATUS)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the issuance Msg service.
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
     * IssueTokens message type used by the issuer to issue new tokens
     * </pre>
     */
    public void issueTokens(kava.issuance.v1beta1.Tx.MsgIssueTokens request,
        io.grpc.stub.StreamObserver<kava.issuance.v1beta1.Tx.MsgIssueTokensResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIssueTokensMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RedeemTokens message type used by the issuer to redeem (burn) tokens
     * </pre>
     */
    public void redeemTokens(kava.issuance.v1beta1.Tx.MsgRedeemTokens request,
        io.grpc.stub.StreamObserver<kava.issuance.v1beta1.Tx.MsgRedeemTokensResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRedeemTokensMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BlockAddress message type used by the issuer to block an address from holding or transferring tokens
     * </pre>
     */
    public void blockAddress(kava.issuance.v1beta1.Tx.MsgBlockAddress request,
        io.grpc.stub.StreamObserver<kava.issuance.v1beta1.Tx.MsgBlockAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBlockAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnblockAddress message type used by the issuer to unblock an address from holding or transferring tokens
     * </pre>
     */
    public void unblockAddress(kava.issuance.v1beta1.Tx.MsgUnblockAddress request,
        io.grpc.stub.StreamObserver<kava.issuance.v1beta1.Tx.MsgUnblockAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnblockAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SetPauseStatus message type used to pause or unpause status
     * </pre>
     */
    public void setPauseStatus(kava.issuance.v1beta1.Tx.MsgSetPauseStatus request,
        io.grpc.stub.StreamObserver<kava.issuance.v1beta1.Tx.MsgSetPauseStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetPauseStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the issuance Msg service.
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
     * IssueTokens message type used by the issuer to issue new tokens
     * </pre>
     */
    public kava.issuance.v1beta1.Tx.MsgIssueTokensResponse issueTokens(kava.issuance.v1beta1.Tx.MsgIssueTokens request) {
      return blockingUnaryCall(
          getChannel(), getIssueTokensMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RedeemTokens message type used by the issuer to redeem (burn) tokens
     * </pre>
     */
    public kava.issuance.v1beta1.Tx.MsgRedeemTokensResponse redeemTokens(kava.issuance.v1beta1.Tx.MsgRedeemTokens request) {
      return blockingUnaryCall(
          getChannel(), getRedeemTokensMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BlockAddress message type used by the issuer to block an address from holding or transferring tokens
     * </pre>
     */
    public kava.issuance.v1beta1.Tx.MsgBlockAddressResponse blockAddress(kava.issuance.v1beta1.Tx.MsgBlockAddress request) {
      return blockingUnaryCall(
          getChannel(), getBlockAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnblockAddress message type used by the issuer to unblock an address from holding or transferring tokens
     * </pre>
     */
    public kava.issuance.v1beta1.Tx.MsgUnblockAddressResponse unblockAddress(kava.issuance.v1beta1.Tx.MsgUnblockAddress request) {
      return blockingUnaryCall(
          getChannel(), getUnblockAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SetPauseStatus message type used to pause or unpause status
     * </pre>
     */
    public kava.issuance.v1beta1.Tx.MsgSetPauseStatusResponse setPauseStatus(kava.issuance.v1beta1.Tx.MsgSetPauseStatus request) {
      return blockingUnaryCall(
          getChannel(), getSetPauseStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the issuance Msg service.
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
     * IssueTokens message type used by the issuer to issue new tokens
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.issuance.v1beta1.Tx.MsgIssueTokensResponse> issueTokens(
        kava.issuance.v1beta1.Tx.MsgIssueTokens request) {
      return futureUnaryCall(
          getChannel().newCall(getIssueTokensMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RedeemTokens message type used by the issuer to redeem (burn) tokens
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.issuance.v1beta1.Tx.MsgRedeemTokensResponse> redeemTokens(
        kava.issuance.v1beta1.Tx.MsgRedeemTokens request) {
      return futureUnaryCall(
          getChannel().newCall(getRedeemTokensMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BlockAddress message type used by the issuer to block an address from holding or transferring tokens
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.issuance.v1beta1.Tx.MsgBlockAddressResponse> blockAddress(
        kava.issuance.v1beta1.Tx.MsgBlockAddress request) {
      return futureUnaryCall(
          getChannel().newCall(getBlockAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnblockAddress message type used by the issuer to unblock an address from holding or transferring tokens
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.issuance.v1beta1.Tx.MsgUnblockAddressResponse> unblockAddress(
        kava.issuance.v1beta1.Tx.MsgUnblockAddress request) {
      return futureUnaryCall(
          getChannel().newCall(getUnblockAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SetPauseStatus message type used to pause or unpause status
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.issuance.v1beta1.Tx.MsgSetPauseStatusResponse> setPauseStatus(
        kava.issuance.v1beta1.Tx.MsgSetPauseStatus request) {
      return futureUnaryCall(
          getChannel().newCall(getSetPauseStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ISSUE_TOKENS = 0;
  private static final int METHODID_REDEEM_TOKENS = 1;
  private static final int METHODID_BLOCK_ADDRESS = 2;
  private static final int METHODID_UNBLOCK_ADDRESS = 3;
  private static final int METHODID_SET_PAUSE_STATUS = 4;

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
        case METHODID_ISSUE_TOKENS:
          serviceImpl.issueTokens((kava.issuance.v1beta1.Tx.MsgIssueTokens) request,
              (io.grpc.stub.StreamObserver<kava.issuance.v1beta1.Tx.MsgIssueTokensResponse>) responseObserver);
          break;
        case METHODID_REDEEM_TOKENS:
          serviceImpl.redeemTokens((kava.issuance.v1beta1.Tx.MsgRedeemTokens) request,
              (io.grpc.stub.StreamObserver<kava.issuance.v1beta1.Tx.MsgRedeemTokensResponse>) responseObserver);
          break;
        case METHODID_BLOCK_ADDRESS:
          serviceImpl.blockAddress((kava.issuance.v1beta1.Tx.MsgBlockAddress) request,
              (io.grpc.stub.StreamObserver<kava.issuance.v1beta1.Tx.MsgBlockAddressResponse>) responseObserver);
          break;
        case METHODID_UNBLOCK_ADDRESS:
          serviceImpl.unblockAddress((kava.issuance.v1beta1.Tx.MsgUnblockAddress) request,
              (io.grpc.stub.StreamObserver<kava.issuance.v1beta1.Tx.MsgUnblockAddressResponse>) responseObserver);
          break;
        case METHODID_SET_PAUSE_STATUS:
          serviceImpl.setPauseStatus((kava.issuance.v1beta1.Tx.MsgSetPauseStatus) request,
              (io.grpc.stub.StreamObserver<kava.issuance.v1beta1.Tx.MsgSetPauseStatusResponse>) responseObserver);
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
      return kava.issuance.v1beta1.Tx.getDescriptor();
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
              .addMethod(getIssueTokensMethod())
              .addMethod(getRedeemTokensMethod())
              .addMethod(getBlockAddressMethod())
              .addMethod(getUnblockAddressMethod())
              .addMethod(getSetPauseStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
