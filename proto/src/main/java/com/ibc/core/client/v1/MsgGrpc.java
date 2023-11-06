package com.ibc.core.client.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the ibc/client Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: ibc/core/client/v1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "ibc.core.client.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v1.TxProto.MsgCreateClient,
      com.ibc.core.client.v1.TxProto.MsgCreateClientResponse> getCreateClientMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateClient",
      requestType = com.ibc.core.client.v1.TxProto.MsgCreateClient.class,
      responseType = com.ibc.core.client.v1.TxProto.MsgCreateClientResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v1.TxProto.MsgCreateClient,
      com.ibc.core.client.v1.TxProto.MsgCreateClientResponse> getCreateClientMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v1.TxProto.MsgCreateClient, com.ibc.core.client.v1.TxProto.MsgCreateClientResponse> getCreateClientMethod;
    if ((getCreateClientMethod = MsgGrpc.getCreateClientMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateClientMethod = MsgGrpc.getCreateClientMethod) == null) {
          MsgGrpc.getCreateClientMethod = getCreateClientMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v1.TxProto.MsgCreateClient, com.ibc.core.client.v1.TxProto.MsgCreateClientResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateClient"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.TxProto.MsgCreateClient.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.TxProto.MsgCreateClientResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateClient"))
              .build();
        }
      }
    }
    return getCreateClientMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v1.TxProto.MsgUpdateClient,
      com.ibc.core.client.v1.TxProto.MsgUpdateClientResponse> getUpdateClientMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateClient",
      requestType = com.ibc.core.client.v1.TxProto.MsgUpdateClient.class,
      responseType = com.ibc.core.client.v1.TxProto.MsgUpdateClientResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v1.TxProto.MsgUpdateClient,
      com.ibc.core.client.v1.TxProto.MsgUpdateClientResponse> getUpdateClientMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v1.TxProto.MsgUpdateClient, com.ibc.core.client.v1.TxProto.MsgUpdateClientResponse> getUpdateClientMethod;
    if ((getUpdateClientMethod = MsgGrpc.getUpdateClientMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateClientMethod = MsgGrpc.getUpdateClientMethod) == null) {
          MsgGrpc.getUpdateClientMethod = getUpdateClientMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v1.TxProto.MsgUpdateClient, com.ibc.core.client.v1.TxProto.MsgUpdateClientResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateClient"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.TxProto.MsgUpdateClient.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.TxProto.MsgUpdateClientResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateClient"))
              .build();
        }
      }
    }
    return getUpdateClientMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v1.TxProto.MsgUpgradeClient,
      com.ibc.core.client.v1.TxProto.MsgUpgradeClientResponse> getUpgradeClientMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpgradeClient",
      requestType = com.ibc.core.client.v1.TxProto.MsgUpgradeClient.class,
      responseType = com.ibc.core.client.v1.TxProto.MsgUpgradeClientResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v1.TxProto.MsgUpgradeClient,
      com.ibc.core.client.v1.TxProto.MsgUpgradeClientResponse> getUpgradeClientMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v1.TxProto.MsgUpgradeClient, com.ibc.core.client.v1.TxProto.MsgUpgradeClientResponse> getUpgradeClientMethod;
    if ((getUpgradeClientMethod = MsgGrpc.getUpgradeClientMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpgradeClientMethod = MsgGrpc.getUpgradeClientMethod) == null) {
          MsgGrpc.getUpgradeClientMethod = getUpgradeClientMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v1.TxProto.MsgUpgradeClient, com.ibc.core.client.v1.TxProto.MsgUpgradeClientResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpgradeClient"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.TxProto.MsgUpgradeClient.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.TxProto.MsgUpgradeClientResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpgradeClient"))
              .build();
        }
      }
    }
    return getUpgradeClientMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviour,
      com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviourResponse> getSubmitMisbehaviourMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitMisbehaviour",
      requestType = com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviour.class,
      responseType = com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviourResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviour,
      com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviourResponse> getSubmitMisbehaviourMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviour, com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviourResponse> getSubmitMisbehaviourMethod;
    if ((getSubmitMisbehaviourMethod = MsgGrpc.getSubmitMisbehaviourMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSubmitMisbehaviourMethod = MsgGrpc.getSubmitMisbehaviourMethod) == null) {
          MsgGrpc.getSubmitMisbehaviourMethod = getSubmitMisbehaviourMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviour, com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviourResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubmitMisbehaviour"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviour.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviourResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SubmitMisbehaviour"))
              .build();
        }
      }
    }
    return getSubmitMisbehaviourMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v1.TxProto.MsgUpdateParams,
      com.ibc.core.client.v1.TxProto.MsgUpdateParamsResponse> getUpdateClientParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateClientParams",
      requestType = com.ibc.core.client.v1.TxProto.MsgUpdateParams.class,
      responseType = com.ibc.core.client.v1.TxProto.MsgUpdateParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v1.TxProto.MsgUpdateParams,
      com.ibc.core.client.v1.TxProto.MsgUpdateParamsResponse> getUpdateClientParamsMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v1.TxProto.MsgUpdateParams, com.ibc.core.client.v1.TxProto.MsgUpdateParamsResponse> getUpdateClientParamsMethod;
    if ((getUpdateClientParamsMethod = MsgGrpc.getUpdateClientParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateClientParamsMethod = MsgGrpc.getUpdateClientParamsMethod) == null) {
          MsgGrpc.getUpdateClientParamsMethod = getUpdateClientParamsMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v1.TxProto.MsgUpdateParams, com.ibc.core.client.v1.TxProto.MsgUpdateParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateClientParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.TxProto.MsgUpdateParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.TxProto.MsgUpdateParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateClientParams"))
              .build();
        }
      }
    }
    return getUpdateClientParamsMethod;
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
   * Msg defines the ibc/client Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * CreateClient defines a rpc handler method for MsgCreateClient.
     * </pre>
     */
    default void createClient(com.ibc.core.client.v1.TxProto.MsgCreateClient request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.TxProto.MsgCreateClientResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateClientMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateClient defines a rpc handler method for MsgUpdateClient.
     * </pre>
     */
    default void updateClient(com.ibc.core.client.v1.TxProto.MsgUpdateClient request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.TxProto.MsgUpdateClientResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateClientMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpgradeClient defines a rpc handler method for MsgUpgradeClient.
     * </pre>
     */
    default void upgradeClient(com.ibc.core.client.v1.TxProto.MsgUpgradeClient request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.TxProto.MsgUpgradeClientResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpgradeClientMethod(), responseObserver);
    }

    /**
     * <pre>
     * SubmitMisbehaviour defines a rpc handler method for MsgSubmitMisbehaviour.
     * </pre>
     */
    default void submitMisbehaviour(com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviour request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviourResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubmitMisbehaviourMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateClientParams defines a rpc handler method for MsgUpdateParams.
     * </pre>
     */
    default void updateClientParams(com.ibc.core.client.v1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateClientParamsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the ibc/client Msg service.
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
   * Msg defines the ibc/client Msg service.
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
     * CreateClient defines a rpc handler method for MsgCreateClient.
     * </pre>
     */
    public void createClient(com.ibc.core.client.v1.TxProto.MsgCreateClient request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.TxProto.MsgCreateClientResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateClientMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateClient defines a rpc handler method for MsgUpdateClient.
     * </pre>
     */
    public void updateClient(com.ibc.core.client.v1.TxProto.MsgUpdateClient request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.TxProto.MsgUpdateClientResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateClientMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpgradeClient defines a rpc handler method for MsgUpgradeClient.
     * </pre>
     */
    public void upgradeClient(com.ibc.core.client.v1.TxProto.MsgUpgradeClient request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.TxProto.MsgUpgradeClientResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpgradeClientMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SubmitMisbehaviour defines a rpc handler method for MsgSubmitMisbehaviour.
     * </pre>
     */
    public void submitMisbehaviour(com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviour request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviourResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSubmitMisbehaviourMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateClientParams defines a rpc handler method for MsgUpdateParams.
     * </pre>
     */
    public void updateClientParams(com.ibc.core.client.v1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateClientParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the ibc/client Msg service.
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
     * CreateClient defines a rpc handler method for MsgCreateClient.
     * </pre>
     */
    public com.ibc.core.client.v1.TxProto.MsgCreateClientResponse createClient(com.ibc.core.client.v1.TxProto.MsgCreateClient request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateClientMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateClient defines a rpc handler method for MsgUpdateClient.
     * </pre>
     */
    public com.ibc.core.client.v1.TxProto.MsgUpdateClientResponse updateClient(com.ibc.core.client.v1.TxProto.MsgUpdateClient request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateClientMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpgradeClient defines a rpc handler method for MsgUpgradeClient.
     * </pre>
     */
    public com.ibc.core.client.v1.TxProto.MsgUpgradeClientResponse upgradeClient(com.ibc.core.client.v1.TxProto.MsgUpgradeClient request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpgradeClientMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SubmitMisbehaviour defines a rpc handler method for MsgSubmitMisbehaviour.
     * </pre>
     */
    public com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviourResponse submitMisbehaviour(com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviour request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSubmitMisbehaviourMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateClientParams defines a rpc handler method for MsgUpdateParams.
     * </pre>
     */
    public com.ibc.core.client.v1.TxProto.MsgUpdateParamsResponse updateClientParams(com.ibc.core.client.v1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateClientParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the ibc/client Msg service.
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
     * CreateClient defines a rpc handler method for MsgCreateClient.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v1.TxProto.MsgCreateClientResponse> createClient(
        com.ibc.core.client.v1.TxProto.MsgCreateClient request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateClientMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateClient defines a rpc handler method for MsgUpdateClient.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v1.TxProto.MsgUpdateClientResponse> updateClient(
        com.ibc.core.client.v1.TxProto.MsgUpdateClient request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateClientMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpgradeClient defines a rpc handler method for MsgUpgradeClient.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v1.TxProto.MsgUpgradeClientResponse> upgradeClient(
        com.ibc.core.client.v1.TxProto.MsgUpgradeClient request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpgradeClientMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SubmitMisbehaviour defines a rpc handler method for MsgSubmitMisbehaviour.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviourResponse> submitMisbehaviour(
        com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviour request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSubmitMisbehaviourMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateClientParams defines a rpc handler method for MsgUpdateParams.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v1.TxProto.MsgUpdateParamsResponse> updateClientParams(
        com.ibc.core.client.v1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateClientParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_CLIENT = 0;
  private static final int METHODID_UPDATE_CLIENT = 1;
  private static final int METHODID_UPGRADE_CLIENT = 2;
  private static final int METHODID_SUBMIT_MISBEHAVIOUR = 3;
  private static final int METHODID_UPDATE_CLIENT_PARAMS = 4;

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
        case METHODID_CREATE_CLIENT:
          serviceImpl.createClient((com.ibc.core.client.v1.TxProto.MsgCreateClient) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v1.TxProto.MsgCreateClientResponse>) responseObserver);
          break;
        case METHODID_UPDATE_CLIENT:
          serviceImpl.updateClient((com.ibc.core.client.v1.TxProto.MsgUpdateClient) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v1.TxProto.MsgUpdateClientResponse>) responseObserver);
          break;
        case METHODID_UPGRADE_CLIENT:
          serviceImpl.upgradeClient((com.ibc.core.client.v1.TxProto.MsgUpgradeClient) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v1.TxProto.MsgUpgradeClientResponse>) responseObserver);
          break;
        case METHODID_SUBMIT_MISBEHAVIOUR:
          serviceImpl.submitMisbehaviour((com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviour) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviourResponse>) responseObserver);
          break;
        case METHODID_UPDATE_CLIENT_PARAMS:
          serviceImpl.updateClientParams((com.ibc.core.client.v1.TxProto.MsgUpdateParams) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v1.TxProto.MsgUpdateParamsResponse>) responseObserver);
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
          getCreateClientMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v1.TxProto.MsgCreateClient,
              com.ibc.core.client.v1.TxProto.MsgCreateClientResponse>(
                service, METHODID_CREATE_CLIENT)))
        .addMethod(
          getUpdateClientMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v1.TxProto.MsgUpdateClient,
              com.ibc.core.client.v1.TxProto.MsgUpdateClientResponse>(
                service, METHODID_UPDATE_CLIENT)))
        .addMethod(
          getUpgradeClientMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v1.TxProto.MsgUpgradeClient,
              com.ibc.core.client.v1.TxProto.MsgUpgradeClientResponse>(
                service, METHODID_UPGRADE_CLIENT)))
        .addMethod(
          getSubmitMisbehaviourMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviour,
              com.ibc.core.client.v1.TxProto.MsgSubmitMisbehaviourResponse>(
                service, METHODID_SUBMIT_MISBEHAVIOUR)))
        .addMethod(
          getUpdateClientParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v1.TxProto.MsgUpdateParams,
              com.ibc.core.client.v1.TxProto.MsgUpdateParamsResponse>(
                service, METHODID_UPDATE_CLIENT_PARAMS)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ibc.core.client.v1.TxProto.getDescriptor();
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
              .addMethod(getCreateClientMethod())
              .addMethod(getUpdateClientMethod())
              .addMethod(getUpgradeClientMethod())
              .addMethod(getSubmitMisbehaviourMethod())
              .addMethod(getUpdateClientParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
