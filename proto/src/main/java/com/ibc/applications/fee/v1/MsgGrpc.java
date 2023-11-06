package com.ibc.applications.fee.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the ICS29 Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: ibc/applications/fee/v1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "ibc.applications.fee.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.TxProto.MsgRegisterPayee,
      com.ibc.applications.fee.v1.TxProto.MsgRegisterPayeeResponse> getRegisterPayeeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterPayee",
      requestType = com.ibc.applications.fee.v1.TxProto.MsgRegisterPayee.class,
      responseType = com.ibc.applications.fee.v1.TxProto.MsgRegisterPayeeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.TxProto.MsgRegisterPayee,
      com.ibc.applications.fee.v1.TxProto.MsgRegisterPayeeResponse> getRegisterPayeeMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.TxProto.MsgRegisterPayee, com.ibc.applications.fee.v1.TxProto.MsgRegisterPayeeResponse> getRegisterPayeeMethod;
    if ((getRegisterPayeeMethod = MsgGrpc.getRegisterPayeeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRegisterPayeeMethod = MsgGrpc.getRegisterPayeeMethod) == null) {
          MsgGrpc.getRegisterPayeeMethod = getRegisterPayeeMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.fee.v1.TxProto.MsgRegisterPayee, com.ibc.applications.fee.v1.TxProto.MsgRegisterPayeeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterPayee"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.TxProto.MsgRegisterPayee.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.TxProto.MsgRegisterPayeeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RegisterPayee"))
              .build();
        }
      }
    }
    return getRegisterPayeeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayee,
      com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayeeResponse> getRegisterCounterpartyPayeeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterCounterpartyPayee",
      requestType = com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayee.class,
      responseType = com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayeeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayee,
      com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayeeResponse> getRegisterCounterpartyPayeeMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayee, com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayeeResponse> getRegisterCounterpartyPayeeMethod;
    if ((getRegisterCounterpartyPayeeMethod = MsgGrpc.getRegisterCounterpartyPayeeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRegisterCounterpartyPayeeMethod = MsgGrpc.getRegisterCounterpartyPayeeMethod) == null) {
          MsgGrpc.getRegisterCounterpartyPayeeMethod = getRegisterCounterpartyPayeeMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayee, com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayeeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterCounterpartyPayee"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayee.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayeeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RegisterCounterpartyPayee"))
              .build();
        }
      }
    }
    return getRegisterCounterpartyPayeeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.TxProto.MsgPayPacketFee,
      com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeResponse> getPayPacketFeeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PayPacketFee",
      requestType = com.ibc.applications.fee.v1.TxProto.MsgPayPacketFee.class,
      responseType = com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.TxProto.MsgPayPacketFee,
      com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeResponse> getPayPacketFeeMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.TxProto.MsgPayPacketFee, com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeResponse> getPayPacketFeeMethod;
    if ((getPayPacketFeeMethod = MsgGrpc.getPayPacketFeeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getPayPacketFeeMethod = MsgGrpc.getPayPacketFeeMethod) == null) {
          MsgGrpc.getPayPacketFeeMethod = getPayPacketFeeMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.fee.v1.TxProto.MsgPayPacketFee, com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PayPacketFee"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.TxProto.MsgPayPacketFee.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("PayPacketFee"))
              .build();
        }
      }
    }
    return getPayPacketFeeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsync,
      com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsyncResponse> getPayPacketFeeAsyncMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PayPacketFeeAsync",
      requestType = com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsync.class,
      responseType = com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsyncResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsync,
      com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsyncResponse> getPayPacketFeeAsyncMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsync, com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsyncResponse> getPayPacketFeeAsyncMethod;
    if ((getPayPacketFeeAsyncMethod = MsgGrpc.getPayPacketFeeAsyncMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getPayPacketFeeAsyncMethod = MsgGrpc.getPayPacketFeeAsyncMethod) == null) {
          MsgGrpc.getPayPacketFeeAsyncMethod = getPayPacketFeeAsyncMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsync, com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsyncResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PayPacketFeeAsync"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsync.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsyncResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("PayPacketFeeAsync"))
              .build();
        }
      }
    }
    return getPayPacketFeeAsyncMethod;
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
   * Msg defines the ICS29 Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * RegisterPayee defines a rpc handler method for MsgRegisterPayee
     * RegisterPayee is called by the relayer on each channelEnd and allows them to set an optional
     * payee to which reverse and timeout relayer packet fees will be paid out. The payee should be registered on
     * the source chain from which packets originate as this is where fee distribution takes place. This function may be
     * called more than once by a relayer, in which case, the latest payee is always used.
     * </pre>
     */
    default void registerPayee(com.ibc.applications.fee.v1.TxProto.MsgRegisterPayee request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.TxProto.MsgRegisterPayeeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisterPayeeMethod(), responseObserver);
    }

    /**
     * <pre>
     * RegisterCounterpartyPayee defines a rpc handler method for MsgRegisterCounterpartyPayee
     * RegisterCounterpartyPayee is called by the relayer on each channelEnd and allows them to specify the counterparty
     * payee address before relaying. This ensures they will be properly compensated for forward relaying since
     * the destination chain must include the registered counterparty payee address in the acknowledgement. This function
     * may be called more than once by a relayer, in which case, the latest counterparty payee address is always used.
     * </pre>
     */
    default void registerCounterpartyPayee(com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayee request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayeeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisterCounterpartyPayeeMethod(), responseObserver);
    }

    /**
     * <pre>
     * PayPacketFee defines a rpc handler method for MsgPayPacketFee
     * PayPacketFee is an open callback that may be called by any module/user that wishes to escrow funds in order to
     * incentivize the relaying of the packet at the next sequence
     * NOTE: This method is intended to be used within a multi msg transaction, where the subsequent msg that follows
     * initiates the lifecycle of the incentivized packet
     * </pre>
     */
    default void payPacketFee(com.ibc.applications.fee.v1.TxProto.MsgPayPacketFee request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPayPacketFeeMethod(), responseObserver);
    }

    /**
     * <pre>
     * PayPacketFeeAsync defines a rpc handler method for MsgPayPacketFeeAsync
     * PayPacketFeeAsync is an open callback that may be called by any module/user that wishes to escrow funds in order to
     * incentivize the relaying of a known packet (i.e. at a particular sequence)
     * </pre>
     */
    default void payPacketFeeAsync(com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsync request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsyncResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPayPacketFeeAsyncMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the ICS29 Msg service.
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
   * Msg defines the ICS29 Msg service.
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
     * RegisterPayee defines a rpc handler method for MsgRegisterPayee
     * RegisterPayee is called by the relayer on each channelEnd and allows them to set an optional
     * payee to which reverse and timeout relayer packet fees will be paid out. The payee should be registered on
     * the source chain from which packets originate as this is where fee distribution takes place. This function may be
     * called more than once by a relayer, in which case, the latest payee is always used.
     * </pre>
     */
    public void registerPayee(com.ibc.applications.fee.v1.TxProto.MsgRegisterPayee request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.TxProto.MsgRegisterPayeeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisterPayeeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RegisterCounterpartyPayee defines a rpc handler method for MsgRegisterCounterpartyPayee
     * RegisterCounterpartyPayee is called by the relayer on each channelEnd and allows them to specify the counterparty
     * payee address before relaying. This ensures they will be properly compensated for forward relaying since
     * the destination chain must include the registered counterparty payee address in the acknowledgement. This function
     * may be called more than once by a relayer, in which case, the latest counterparty payee address is always used.
     * </pre>
     */
    public void registerCounterpartyPayee(com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayee request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayeeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisterCounterpartyPayeeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PayPacketFee defines a rpc handler method for MsgPayPacketFee
     * PayPacketFee is an open callback that may be called by any module/user that wishes to escrow funds in order to
     * incentivize the relaying of the packet at the next sequence
     * NOTE: This method is intended to be used within a multi msg transaction, where the subsequent msg that follows
     * initiates the lifecycle of the incentivized packet
     * </pre>
     */
    public void payPacketFee(com.ibc.applications.fee.v1.TxProto.MsgPayPacketFee request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPayPacketFeeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PayPacketFeeAsync defines a rpc handler method for MsgPayPacketFeeAsync
     * PayPacketFeeAsync is an open callback that may be called by any module/user that wishes to escrow funds in order to
     * incentivize the relaying of a known packet (i.e. at a particular sequence)
     * </pre>
     */
    public void payPacketFeeAsync(com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsync request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsyncResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPayPacketFeeAsyncMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the ICS29 Msg service.
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
     * RegisterPayee defines a rpc handler method for MsgRegisterPayee
     * RegisterPayee is called by the relayer on each channelEnd and allows them to set an optional
     * payee to which reverse and timeout relayer packet fees will be paid out. The payee should be registered on
     * the source chain from which packets originate as this is where fee distribution takes place. This function may be
     * called more than once by a relayer, in which case, the latest payee is always used.
     * </pre>
     */
    public com.ibc.applications.fee.v1.TxProto.MsgRegisterPayeeResponse registerPayee(com.ibc.applications.fee.v1.TxProto.MsgRegisterPayee request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisterPayeeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RegisterCounterpartyPayee defines a rpc handler method for MsgRegisterCounterpartyPayee
     * RegisterCounterpartyPayee is called by the relayer on each channelEnd and allows them to specify the counterparty
     * payee address before relaying. This ensures they will be properly compensated for forward relaying since
     * the destination chain must include the registered counterparty payee address in the acknowledgement. This function
     * may be called more than once by a relayer, in which case, the latest counterparty payee address is always used.
     * </pre>
     */
    public com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayeeResponse registerCounterpartyPayee(com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayee request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisterCounterpartyPayeeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PayPacketFee defines a rpc handler method for MsgPayPacketFee
     * PayPacketFee is an open callback that may be called by any module/user that wishes to escrow funds in order to
     * incentivize the relaying of the packet at the next sequence
     * NOTE: This method is intended to be used within a multi msg transaction, where the subsequent msg that follows
     * initiates the lifecycle of the incentivized packet
     * </pre>
     */
    public com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeResponse payPacketFee(com.ibc.applications.fee.v1.TxProto.MsgPayPacketFee request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPayPacketFeeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PayPacketFeeAsync defines a rpc handler method for MsgPayPacketFeeAsync
     * PayPacketFeeAsync is an open callback that may be called by any module/user that wishes to escrow funds in order to
     * incentivize the relaying of a known packet (i.e. at a particular sequence)
     * </pre>
     */
    public com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsyncResponse payPacketFeeAsync(com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsync request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPayPacketFeeAsyncMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the ICS29 Msg service.
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
     * RegisterPayee defines a rpc handler method for MsgRegisterPayee
     * RegisterPayee is called by the relayer on each channelEnd and allows them to set an optional
     * payee to which reverse and timeout relayer packet fees will be paid out. The payee should be registered on
     * the source chain from which packets originate as this is where fee distribution takes place. This function may be
     * called more than once by a relayer, in which case, the latest payee is always used.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.fee.v1.TxProto.MsgRegisterPayeeResponse> registerPayee(
        com.ibc.applications.fee.v1.TxProto.MsgRegisterPayee request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisterPayeeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RegisterCounterpartyPayee defines a rpc handler method for MsgRegisterCounterpartyPayee
     * RegisterCounterpartyPayee is called by the relayer on each channelEnd and allows them to specify the counterparty
     * payee address before relaying. This ensures they will be properly compensated for forward relaying since
     * the destination chain must include the registered counterparty payee address in the acknowledgement. This function
     * may be called more than once by a relayer, in which case, the latest counterparty payee address is always used.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayeeResponse> registerCounterpartyPayee(
        com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayee request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisterCounterpartyPayeeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PayPacketFee defines a rpc handler method for MsgPayPacketFee
     * PayPacketFee is an open callback that may be called by any module/user that wishes to escrow funds in order to
     * incentivize the relaying of the packet at the next sequence
     * NOTE: This method is intended to be used within a multi msg transaction, where the subsequent msg that follows
     * initiates the lifecycle of the incentivized packet
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeResponse> payPacketFee(
        com.ibc.applications.fee.v1.TxProto.MsgPayPacketFee request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPayPacketFeeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PayPacketFeeAsync defines a rpc handler method for MsgPayPacketFeeAsync
     * PayPacketFeeAsync is an open callback that may be called by any module/user that wishes to escrow funds in order to
     * incentivize the relaying of a known packet (i.e. at a particular sequence)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsyncResponse> payPacketFeeAsync(
        com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsync request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPayPacketFeeAsyncMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_PAYEE = 0;
  private static final int METHODID_REGISTER_COUNTERPARTY_PAYEE = 1;
  private static final int METHODID_PAY_PACKET_FEE = 2;
  private static final int METHODID_PAY_PACKET_FEE_ASYNC = 3;

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
        case METHODID_REGISTER_PAYEE:
          serviceImpl.registerPayee((com.ibc.applications.fee.v1.TxProto.MsgRegisterPayee) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.TxProto.MsgRegisterPayeeResponse>) responseObserver);
          break;
        case METHODID_REGISTER_COUNTERPARTY_PAYEE:
          serviceImpl.registerCounterpartyPayee((com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayee) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayeeResponse>) responseObserver);
          break;
        case METHODID_PAY_PACKET_FEE:
          serviceImpl.payPacketFee((com.ibc.applications.fee.v1.TxProto.MsgPayPacketFee) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeResponse>) responseObserver);
          break;
        case METHODID_PAY_PACKET_FEE_ASYNC:
          serviceImpl.payPacketFeeAsync((com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsync) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsyncResponse>) responseObserver);
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
          getRegisterPayeeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.fee.v1.TxProto.MsgRegisterPayee,
              com.ibc.applications.fee.v1.TxProto.MsgRegisterPayeeResponse>(
                service, METHODID_REGISTER_PAYEE)))
        .addMethod(
          getRegisterCounterpartyPayeeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayee,
              com.ibc.applications.fee.v1.TxProto.MsgRegisterCounterpartyPayeeResponse>(
                service, METHODID_REGISTER_COUNTERPARTY_PAYEE)))
        .addMethod(
          getPayPacketFeeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.fee.v1.TxProto.MsgPayPacketFee,
              com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeResponse>(
                service, METHODID_PAY_PACKET_FEE)))
        .addMethod(
          getPayPacketFeeAsyncMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsync,
              com.ibc.applications.fee.v1.TxProto.MsgPayPacketFeeAsyncResponse>(
                service, METHODID_PAY_PACKET_FEE_ASYNC)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ibc.applications.fee.v1.TxProto.getDescriptor();
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
              .addMethod(getRegisterPayeeMethod())
              .addMethod(getRegisterCounterpartyPayeeMethod())
              .addMethod(getPayPacketFeeMethod())
              .addMethod(getPayPacketFeeAsyncMethod())
              .build();
        }
      }
    }
    return result;
  }
}
