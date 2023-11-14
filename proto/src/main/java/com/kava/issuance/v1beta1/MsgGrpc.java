package com.kava.issuance.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the issuance Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: kava/issuance/v1beta1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.issuance.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kava.issuance.v1beta1.TxProto.MsgIssueTokens,
      com.kava.issuance.v1beta1.TxProto.MsgIssueTokensResponse> getIssueTokensMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IssueTokens",
      requestType = com.kava.issuance.v1beta1.TxProto.MsgIssueTokens.class,
      responseType = com.kava.issuance.v1beta1.TxProto.MsgIssueTokensResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.issuance.v1beta1.TxProto.MsgIssueTokens,
      com.kava.issuance.v1beta1.TxProto.MsgIssueTokensResponse> getIssueTokensMethod() {
    io.grpc.MethodDescriptor<com.kava.issuance.v1beta1.TxProto.MsgIssueTokens, com.kava.issuance.v1beta1.TxProto.MsgIssueTokensResponse> getIssueTokensMethod;
    if ((getIssueTokensMethod = MsgGrpc.getIssueTokensMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getIssueTokensMethod = MsgGrpc.getIssueTokensMethod) == null) {
          MsgGrpc.getIssueTokensMethod = getIssueTokensMethod =
              io.grpc.MethodDescriptor.<com.kava.issuance.v1beta1.TxProto.MsgIssueTokens, com.kava.issuance.v1beta1.TxProto.MsgIssueTokensResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IssueTokens"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.issuance.v1beta1.TxProto.MsgIssueTokens.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.issuance.v1beta1.TxProto.MsgIssueTokensResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("IssueTokens"))
              .build();
        }
      }
    }
    return getIssueTokensMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.issuance.v1beta1.TxProto.MsgRedeemTokens,
      com.kava.issuance.v1beta1.TxProto.MsgRedeemTokensResponse> getRedeemTokensMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RedeemTokens",
      requestType = com.kava.issuance.v1beta1.TxProto.MsgRedeemTokens.class,
      responseType = com.kava.issuance.v1beta1.TxProto.MsgRedeemTokensResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.issuance.v1beta1.TxProto.MsgRedeemTokens,
      com.kava.issuance.v1beta1.TxProto.MsgRedeemTokensResponse> getRedeemTokensMethod() {
    io.grpc.MethodDescriptor<com.kava.issuance.v1beta1.TxProto.MsgRedeemTokens, com.kava.issuance.v1beta1.TxProto.MsgRedeemTokensResponse> getRedeemTokensMethod;
    if ((getRedeemTokensMethod = MsgGrpc.getRedeemTokensMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRedeemTokensMethod = MsgGrpc.getRedeemTokensMethod) == null) {
          MsgGrpc.getRedeemTokensMethod = getRedeemTokensMethod =
              io.grpc.MethodDescriptor.<com.kava.issuance.v1beta1.TxProto.MsgRedeemTokens, com.kava.issuance.v1beta1.TxProto.MsgRedeemTokensResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RedeemTokens"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.issuance.v1beta1.TxProto.MsgRedeemTokens.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.issuance.v1beta1.TxProto.MsgRedeemTokensResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RedeemTokens"))
              .build();
        }
      }
    }
    return getRedeemTokensMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.issuance.v1beta1.TxProto.MsgBlockAddress,
      com.kava.issuance.v1beta1.TxProto.MsgBlockAddressResponse> getBlockAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BlockAddress",
      requestType = com.kava.issuance.v1beta1.TxProto.MsgBlockAddress.class,
      responseType = com.kava.issuance.v1beta1.TxProto.MsgBlockAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.issuance.v1beta1.TxProto.MsgBlockAddress,
      com.kava.issuance.v1beta1.TxProto.MsgBlockAddressResponse> getBlockAddressMethod() {
    io.grpc.MethodDescriptor<com.kava.issuance.v1beta1.TxProto.MsgBlockAddress, com.kava.issuance.v1beta1.TxProto.MsgBlockAddressResponse> getBlockAddressMethod;
    if ((getBlockAddressMethod = MsgGrpc.getBlockAddressMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBlockAddressMethod = MsgGrpc.getBlockAddressMethod) == null) {
          MsgGrpc.getBlockAddressMethod = getBlockAddressMethod =
              io.grpc.MethodDescriptor.<com.kava.issuance.v1beta1.TxProto.MsgBlockAddress, com.kava.issuance.v1beta1.TxProto.MsgBlockAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BlockAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.issuance.v1beta1.TxProto.MsgBlockAddress.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.issuance.v1beta1.TxProto.MsgBlockAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BlockAddress"))
              .build();
        }
      }
    }
    return getBlockAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.issuance.v1beta1.TxProto.MsgUnblockAddress,
      com.kava.issuance.v1beta1.TxProto.MsgUnblockAddressResponse> getUnblockAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnblockAddress",
      requestType = com.kava.issuance.v1beta1.TxProto.MsgUnblockAddress.class,
      responseType = com.kava.issuance.v1beta1.TxProto.MsgUnblockAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.issuance.v1beta1.TxProto.MsgUnblockAddress,
      com.kava.issuance.v1beta1.TxProto.MsgUnblockAddressResponse> getUnblockAddressMethod() {
    io.grpc.MethodDescriptor<com.kava.issuance.v1beta1.TxProto.MsgUnblockAddress, com.kava.issuance.v1beta1.TxProto.MsgUnblockAddressResponse> getUnblockAddressMethod;
    if ((getUnblockAddressMethod = MsgGrpc.getUnblockAddressMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUnblockAddressMethod = MsgGrpc.getUnblockAddressMethod) == null) {
          MsgGrpc.getUnblockAddressMethod = getUnblockAddressMethod =
              io.grpc.MethodDescriptor.<com.kava.issuance.v1beta1.TxProto.MsgUnblockAddress, com.kava.issuance.v1beta1.TxProto.MsgUnblockAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnblockAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.issuance.v1beta1.TxProto.MsgUnblockAddress.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.issuance.v1beta1.TxProto.MsgUnblockAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UnblockAddress"))
              .build();
        }
      }
    }
    return getUnblockAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatus,
      com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatusResponse> getSetPauseStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetPauseStatus",
      requestType = com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatus.class,
      responseType = com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatus,
      com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatusResponse> getSetPauseStatusMethod() {
    io.grpc.MethodDescriptor<com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatus, com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatusResponse> getSetPauseStatusMethod;
    if ((getSetPauseStatusMethod = MsgGrpc.getSetPauseStatusMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetPauseStatusMethod = MsgGrpc.getSetPauseStatusMethod) == null) {
          MsgGrpc.getSetPauseStatusMethod = getSetPauseStatusMethod =
              io.grpc.MethodDescriptor.<com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatus, com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetPauseStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatus.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatusResponse.getDefaultInstance()))
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
  public interface AsyncService {

    /**
     * <pre>
     * IssueTokens message type used by the issuer to issue new tokens
     * </pre>
     */
    default void issueTokens(com.kava.issuance.v1beta1.TxProto.MsgIssueTokens request,
        io.grpc.stub.StreamObserver<com.kava.issuance.v1beta1.TxProto.MsgIssueTokensResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getIssueTokensMethod(), responseObserver);
    }

    /**
     * <pre>
     * RedeemTokens message type used by the issuer to redeem (burn) tokens
     * </pre>
     */
    default void redeemTokens(com.kava.issuance.v1beta1.TxProto.MsgRedeemTokens request,
        io.grpc.stub.StreamObserver<com.kava.issuance.v1beta1.TxProto.MsgRedeemTokensResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRedeemTokensMethod(), responseObserver);
    }

    /**
     * <pre>
     * BlockAddress message type used by the issuer to block an address from holding or transferring tokens
     * </pre>
     */
    default void blockAddress(com.kava.issuance.v1beta1.TxProto.MsgBlockAddress request,
        io.grpc.stub.StreamObserver<com.kava.issuance.v1beta1.TxProto.MsgBlockAddressResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBlockAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnblockAddress message type used by the issuer to unblock an address from holding or transferring tokens
     * </pre>
     */
    default void unblockAddress(com.kava.issuance.v1beta1.TxProto.MsgUnblockAddress request,
        io.grpc.stub.StreamObserver<com.kava.issuance.v1beta1.TxProto.MsgUnblockAddressResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnblockAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * SetPauseStatus message type used to pause or unpause status
     * </pre>
     */
    default void setPauseStatus(com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatus request,
        io.grpc.stub.StreamObserver<com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetPauseStatusMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the issuance Msg service.
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
   * Msg defines the issuance Msg service.
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
     * IssueTokens message type used by the issuer to issue new tokens
     * </pre>
     */
    public void issueTokens(com.kava.issuance.v1beta1.TxProto.MsgIssueTokens request,
        io.grpc.stub.StreamObserver<com.kava.issuance.v1beta1.TxProto.MsgIssueTokensResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getIssueTokensMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RedeemTokens message type used by the issuer to redeem (burn) tokens
     * </pre>
     */
    public void redeemTokens(com.kava.issuance.v1beta1.TxProto.MsgRedeemTokens request,
        io.grpc.stub.StreamObserver<com.kava.issuance.v1beta1.TxProto.MsgRedeemTokensResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRedeemTokensMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BlockAddress message type used by the issuer to block an address from holding or transferring tokens
     * </pre>
     */
    public void blockAddress(com.kava.issuance.v1beta1.TxProto.MsgBlockAddress request,
        io.grpc.stub.StreamObserver<com.kava.issuance.v1beta1.TxProto.MsgBlockAddressResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBlockAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnblockAddress message type used by the issuer to unblock an address from holding or transferring tokens
     * </pre>
     */
    public void unblockAddress(com.kava.issuance.v1beta1.TxProto.MsgUnblockAddress request,
        io.grpc.stub.StreamObserver<com.kava.issuance.v1beta1.TxProto.MsgUnblockAddressResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnblockAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SetPauseStatus message type used to pause or unpause status
     * </pre>
     */
    public void setPauseStatus(com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatus request,
        io.grpc.stub.StreamObserver<com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetPauseStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the issuance Msg service.
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
     * IssueTokens message type used by the issuer to issue new tokens
     * </pre>
     */
    public com.kava.issuance.v1beta1.TxProto.MsgIssueTokensResponse issueTokens(com.kava.issuance.v1beta1.TxProto.MsgIssueTokens request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getIssueTokensMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RedeemTokens message type used by the issuer to redeem (burn) tokens
     * </pre>
     */
    public com.kava.issuance.v1beta1.TxProto.MsgRedeemTokensResponse redeemTokens(com.kava.issuance.v1beta1.TxProto.MsgRedeemTokens request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRedeemTokensMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BlockAddress message type used by the issuer to block an address from holding or transferring tokens
     * </pre>
     */
    public com.kava.issuance.v1beta1.TxProto.MsgBlockAddressResponse blockAddress(com.kava.issuance.v1beta1.TxProto.MsgBlockAddress request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBlockAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnblockAddress message type used by the issuer to unblock an address from holding or transferring tokens
     * </pre>
     */
    public com.kava.issuance.v1beta1.TxProto.MsgUnblockAddressResponse unblockAddress(com.kava.issuance.v1beta1.TxProto.MsgUnblockAddress request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnblockAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SetPauseStatus message type used to pause or unpause status
     * </pre>
     */
    public com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatusResponse setPauseStatus(com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatus request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetPauseStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the issuance Msg service.
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
     * IssueTokens message type used by the issuer to issue new tokens
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.issuance.v1beta1.TxProto.MsgIssueTokensResponse> issueTokens(
        com.kava.issuance.v1beta1.TxProto.MsgIssueTokens request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getIssueTokensMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RedeemTokens message type used by the issuer to redeem (burn) tokens
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.issuance.v1beta1.TxProto.MsgRedeemTokensResponse> redeemTokens(
        com.kava.issuance.v1beta1.TxProto.MsgRedeemTokens request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRedeemTokensMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BlockAddress message type used by the issuer to block an address from holding or transferring tokens
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.issuance.v1beta1.TxProto.MsgBlockAddressResponse> blockAddress(
        com.kava.issuance.v1beta1.TxProto.MsgBlockAddress request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBlockAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnblockAddress message type used by the issuer to unblock an address from holding or transferring tokens
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.issuance.v1beta1.TxProto.MsgUnblockAddressResponse> unblockAddress(
        com.kava.issuance.v1beta1.TxProto.MsgUnblockAddress request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnblockAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SetPauseStatus message type used to pause or unpause status
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatusResponse> setPauseStatus(
        com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatus request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
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
        case METHODID_ISSUE_TOKENS:
          serviceImpl.issueTokens((com.kava.issuance.v1beta1.TxProto.MsgIssueTokens) request,
              (io.grpc.stub.StreamObserver<com.kava.issuance.v1beta1.TxProto.MsgIssueTokensResponse>) responseObserver);
          break;
        case METHODID_REDEEM_TOKENS:
          serviceImpl.redeemTokens((com.kava.issuance.v1beta1.TxProto.MsgRedeemTokens) request,
              (io.grpc.stub.StreamObserver<com.kava.issuance.v1beta1.TxProto.MsgRedeemTokensResponse>) responseObserver);
          break;
        case METHODID_BLOCK_ADDRESS:
          serviceImpl.blockAddress((com.kava.issuance.v1beta1.TxProto.MsgBlockAddress) request,
              (io.grpc.stub.StreamObserver<com.kava.issuance.v1beta1.TxProto.MsgBlockAddressResponse>) responseObserver);
          break;
        case METHODID_UNBLOCK_ADDRESS:
          serviceImpl.unblockAddress((com.kava.issuance.v1beta1.TxProto.MsgUnblockAddress) request,
              (io.grpc.stub.StreamObserver<com.kava.issuance.v1beta1.TxProto.MsgUnblockAddressResponse>) responseObserver);
          break;
        case METHODID_SET_PAUSE_STATUS:
          serviceImpl.setPauseStatus((com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatus) request,
              (io.grpc.stub.StreamObserver<com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatusResponse>) responseObserver);
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
          getIssueTokensMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.issuance.v1beta1.TxProto.MsgIssueTokens,
              com.kava.issuance.v1beta1.TxProto.MsgIssueTokensResponse>(
                service, METHODID_ISSUE_TOKENS)))
        .addMethod(
          getRedeemTokensMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.issuance.v1beta1.TxProto.MsgRedeemTokens,
              com.kava.issuance.v1beta1.TxProto.MsgRedeemTokensResponse>(
                service, METHODID_REDEEM_TOKENS)))
        .addMethod(
          getBlockAddressMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.issuance.v1beta1.TxProto.MsgBlockAddress,
              com.kava.issuance.v1beta1.TxProto.MsgBlockAddressResponse>(
                service, METHODID_BLOCK_ADDRESS)))
        .addMethod(
          getUnblockAddressMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.issuance.v1beta1.TxProto.MsgUnblockAddress,
              com.kava.issuance.v1beta1.TxProto.MsgUnblockAddressResponse>(
                service, METHODID_UNBLOCK_ADDRESS)))
        .addMethod(
          getSetPauseStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatus,
              com.kava.issuance.v1beta1.TxProto.MsgSetPauseStatusResponse>(
                service, METHODID_SET_PAUSE_STATUS)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kava.issuance.v1beta1.TxProto.getDescriptor();
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
