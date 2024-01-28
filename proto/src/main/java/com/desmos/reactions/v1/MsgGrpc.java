package com.desmos.reactions.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the reactions Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: desmos/reactions/v1/msgs.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "desmos.reactions.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgAddReaction,
      com.desmos.reactions.v1.MsgsProto.MsgAddReactionResponse> getAddReactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddReaction",
      requestType = com.desmos.reactions.v1.MsgsProto.MsgAddReaction.class,
      responseType = com.desmos.reactions.v1.MsgsProto.MsgAddReactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgAddReaction,
      com.desmos.reactions.v1.MsgsProto.MsgAddReactionResponse> getAddReactionMethod() {
    io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgAddReaction, com.desmos.reactions.v1.MsgsProto.MsgAddReactionResponse> getAddReactionMethod;
    if ((getAddReactionMethod = MsgGrpc.getAddReactionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddReactionMethod = MsgGrpc.getAddReactionMethod) == null) {
          MsgGrpc.getAddReactionMethod = getAddReactionMethod =
              io.grpc.MethodDescriptor.<com.desmos.reactions.v1.MsgsProto.MsgAddReaction, com.desmos.reactions.v1.MsgsProto.MsgAddReactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddReaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.MsgsProto.MsgAddReaction.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.MsgsProto.MsgAddReactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddReaction"))
              .build();
        }
      }
    }
    return getAddReactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgRemoveReaction,
      com.desmos.reactions.v1.MsgsProto.MsgRemoveReactionResponse> getRemoveReactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveReaction",
      requestType = com.desmos.reactions.v1.MsgsProto.MsgRemoveReaction.class,
      responseType = com.desmos.reactions.v1.MsgsProto.MsgRemoveReactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgRemoveReaction,
      com.desmos.reactions.v1.MsgsProto.MsgRemoveReactionResponse> getRemoveReactionMethod() {
    io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgRemoveReaction, com.desmos.reactions.v1.MsgsProto.MsgRemoveReactionResponse> getRemoveReactionMethod;
    if ((getRemoveReactionMethod = MsgGrpc.getRemoveReactionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRemoveReactionMethod = MsgGrpc.getRemoveReactionMethod) == null) {
          MsgGrpc.getRemoveReactionMethod = getRemoveReactionMethod =
              io.grpc.MethodDescriptor.<com.desmos.reactions.v1.MsgsProto.MsgRemoveReaction, com.desmos.reactions.v1.MsgsProto.MsgRemoveReactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveReaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.MsgsProto.MsgRemoveReaction.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.MsgsProto.MsgRemoveReactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RemoveReaction"))
              .build();
        }
      }
    }
    return getRemoveReactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReaction,
      com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReactionResponse> getAddRegisteredReactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddRegisteredReaction",
      requestType = com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReaction.class,
      responseType = com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReaction,
      com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReactionResponse> getAddRegisteredReactionMethod() {
    io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReaction, com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReactionResponse> getAddRegisteredReactionMethod;
    if ((getAddRegisteredReactionMethod = MsgGrpc.getAddRegisteredReactionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddRegisteredReactionMethod = MsgGrpc.getAddRegisteredReactionMethod) == null) {
          MsgGrpc.getAddRegisteredReactionMethod = getAddRegisteredReactionMethod =
              io.grpc.MethodDescriptor.<com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReaction, com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddRegisteredReaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReaction.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddRegisteredReaction"))
              .build();
        }
      }
    }
    return getAddRegisteredReactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReaction,
      com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReactionResponse> getEditRegisteredReactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditRegisteredReaction",
      requestType = com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReaction.class,
      responseType = com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReaction,
      com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReactionResponse> getEditRegisteredReactionMethod() {
    io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReaction, com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReactionResponse> getEditRegisteredReactionMethod;
    if ((getEditRegisteredReactionMethod = MsgGrpc.getEditRegisteredReactionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEditRegisteredReactionMethod = MsgGrpc.getEditRegisteredReactionMethod) == null) {
          MsgGrpc.getEditRegisteredReactionMethod = getEditRegisteredReactionMethod =
              io.grpc.MethodDescriptor.<com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReaction, com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditRegisteredReaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReaction.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EditRegisteredReaction"))
              .build();
        }
      }
    }
    return getEditRegisteredReactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReaction,
      com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReactionResponse> getRemoveRegisteredReactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveRegisteredReaction",
      requestType = com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReaction.class,
      responseType = com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReaction,
      com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReactionResponse> getRemoveRegisteredReactionMethod() {
    io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReaction, com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReactionResponse> getRemoveRegisteredReactionMethod;
    if ((getRemoveRegisteredReactionMethod = MsgGrpc.getRemoveRegisteredReactionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRemoveRegisteredReactionMethod = MsgGrpc.getRemoveRegisteredReactionMethod) == null) {
          MsgGrpc.getRemoveRegisteredReactionMethod = getRemoveRegisteredReactionMethod =
              io.grpc.MethodDescriptor.<com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReaction, com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveRegisteredReaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReaction.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RemoveRegisteredReaction"))
              .build();
        }
      }
    }
    return getRemoveRegisteredReactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParams,
      com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParamsResponse> getSetReactionsParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetReactionsParams",
      requestType = com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParams.class,
      responseType = com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParams,
      com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParamsResponse> getSetReactionsParamsMethod() {
    io.grpc.MethodDescriptor<com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParams, com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParamsResponse> getSetReactionsParamsMethod;
    if ((getSetReactionsParamsMethod = MsgGrpc.getSetReactionsParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetReactionsParamsMethod = MsgGrpc.getSetReactionsParamsMethod) == null) {
          MsgGrpc.getSetReactionsParamsMethod = getSetReactionsParamsMethod =
              io.grpc.MethodDescriptor.<com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParams, com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetReactionsParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetReactionsParams"))
              .build();
        }
      }
    }
    return getSetReactionsParamsMethod;
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
   * Msg defines the reactions Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * AddReaction allows to add a post reaction
     * </pre>
     */
    default void addReaction(com.desmos.reactions.v1.MsgsProto.MsgAddReaction request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgAddReactionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddReactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * RemoveReaction allows to remove an existing post reaction
     * </pre>
     */
    default void removeReaction(com.desmos.reactions.v1.MsgsProto.MsgRemoveReaction request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgRemoveReactionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRemoveReactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddRegisteredReaction allows to registered a new supported reaction
     * </pre>
     */
    default void addRegisteredReaction(com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReaction request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReactionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddRegisteredReactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * EditRegisteredReaction allows to edit a registered reaction
     * </pre>
     */
    default void editRegisteredReaction(com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReaction request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReactionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEditRegisteredReactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * RemoveRegisteredReaction allows to remove an existing supported reaction
     * </pre>
     */
    default void removeRegisteredReaction(com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReaction request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReactionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRemoveRegisteredReactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * SetReactionsParams allows to set the reactions params
     * </pre>
     */
    default void setReactionsParams(com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParams request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetReactionsParamsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the reactions Msg service.
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
   * Msg defines the reactions Msg service.
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
     * AddReaction allows to add a post reaction
     * </pre>
     */
    public void addReaction(com.desmos.reactions.v1.MsgsProto.MsgAddReaction request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgAddReactionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddReactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RemoveReaction allows to remove an existing post reaction
     * </pre>
     */
    public void removeReaction(com.desmos.reactions.v1.MsgsProto.MsgRemoveReaction request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgRemoveReactionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRemoveReactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddRegisteredReaction allows to registered a new supported reaction
     * </pre>
     */
    public void addRegisteredReaction(com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReaction request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReactionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddRegisteredReactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EditRegisteredReaction allows to edit a registered reaction
     * </pre>
     */
    public void editRegisteredReaction(com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReaction request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReactionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEditRegisteredReactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RemoveRegisteredReaction allows to remove an existing supported reaction
     * </pre>
     */
    public void removeRegisteredReaction(com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReaction request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReactionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRemoveRegisteredReactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SetReactionsParams allows to set the reactions params
     * </pre>
     */
    public void setReactionsParams(com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParams request,
        io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetReactionsParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the reactions Msg service.
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
     * AddReaction allows to add a post reaction
     * </pre>
     */
    public com.desmos.reactions.v1.MsgsProto.MsgAddReactionResponse addReaction(com.desmos.reactions.v1.MsgsProto.MsgAddReaction request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddReactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RemoveReaction allows to remove an existing post reaction
     * </pre>
     */
    public com.desmos.reactions.v1.MsgsProto.MsgRemoveReactionResponse removeReaction(com.desmos.reactions.v1.MsgsProto.MsgRemoveReaction request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRemoveReactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddRegisteredReaction allows to registered a new supported reaction
     * </pre>
     */
    public com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReactionResponse addRegisteredReaction(com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReaction request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddRegisteredReactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EditRegisteredReaction allows to edit a registered reaction
     * </pre>
     */
    public com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReactionResponse editRegisteredReaction(com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReaction request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEditRegisteredReactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RemoveRegisteredReaction allows to remove an existing supported reaction
     * </pre>
     */
    public com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReactionResponse removeRegisteredReaction(com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReaction request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRemoveRegisteredReactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SetReactionsParams allows to set the reactions params
     * </pre>
     */
    public com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParamsResponse setReactionsParams(com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetReactionsParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the reactions Msg service.
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
     * AddReaction allows to add a post reaction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reactions.v1.MsgsProto.MsgAddReactionResponse> addReaction(
        com.desmos.reactions.v1.MsgsProto.MsgAddReaction request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddReactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RemoveReaction allows to remove an existing post reaction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reactions.v1.MsgsProto.MsgRemoveReactionResponse> removeReaction(
        com.desmos.reactions.v1.MsgsProto.MsgRemoveReaction request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRemoveReactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddRegisteredReaction allows to registered a new supported reaction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReactionResponse> addRegisteredReaction(
        com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReaction request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddRegisteredReactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EditRegisteredReaction allows to edit a registered reaction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReactionResponse> editRegisteredReaction(
        com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReaction request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEditRegisteredReactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RemoveRegisteredReaction allows to remove an existing supported reaction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReactionResponse> removeRegisteredReaction(
        com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReaction request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRemoveRegisteredReactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SetReactionsParams allows to set the reactions params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParamsResponse> setReactionsParams(
        com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetReactionsParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_REACTION = 0;
  private static final int METHODID_REMOVE_REACTION = 1;
  private static final int METHODID_ADD_REGISTERED_REACTION = 2;
  private static final int METHODID_EDIT_REGISTERED_REACTION = 3;
  private static final int METHODID_REMOVE_REGISTERED_REACTION = 4;
  private static final int METHODID_SET_REACTIONS_PARAMS = 5;

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
        case METHODID_ADD_REACTION:
          serviceImpl.addReaction((com.desmos.reactions.v1.MsgsProto.MsgAddReaction) request,
              (io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgAddReactionResponse>) responseObserver);
          break;
        case METHODID_REMOVE_REACTION:
          serviceImpl.removeReaction((com.desmos.reactions.v1.MsgsProto.MsgRemoveReaction) request,
              (io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgRemoveReactionResponse>) responseObserver);
          break;
        case METHODID_ADD_REGISTERED_REACTION:
          serviceImpl.addRegisteredReaction((com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReaction) request,
              (io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReactionResponse>) responseObserver);
          break;
        case METHODID_EDIT_REGISTERED_REACTION:
          serviceImpl.editRegisteredReaction((com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReaction) request,
              (io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReactionResponse>) responseObserver);
          break;
        case METHODID_REMOVE_REGISTERED_REACTION:
          serviceImpl.removeRegisteredReaction((com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReaction) request,
              (io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReactionResponse>) responseObserver);
          break;
        case METHODID_SET_REACTIONS_PARAMS:
          serviceImpl.setReactionsParams((com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParams) request,
              (io.grpc.stub.StreamObserver<com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParamsResponse>) responseObserver);
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
          getAddReactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reactions.v1.MsgsProto.MsgAddReaction,
              com.desmos.reactions.v1.MsgsProto.MsgAddReactionResponse>(
                service, METHODID_ADD_REACTION)))
        .addMethod(
          getRemoveReactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reactions.v1.MsgsProto.MsgRemoveReaction,
              com.desmos.reactions.v1.MsgsProto.MsgRemoveReactionResponse>(
                service, METHODID_REMOVE_REACTION)))
        .addMethod(
          getAddRegisteredReactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReaction,
              com.desmos.reactions.v1.MsgsProto.MsgAddRegisteredReactionResponse>(
                service, METHODID_ADD_REGISTERED_REACTION)))
        .addMethod(
          getEditRegisteredReactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReaction,
              com.desmos.reactions.v1.MsgsProto.MsgEditRegisteredReactionResponse>(
                service, METHODID_EDIT_REGISTERED_REACTION)))
        .addMethod(
          getRemoveRegisteredReactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReaction,
              com.desmos.reactions.v1.MsgsProto.MsgRemoveRegisteredReactionResponse>(
                service, METHODID_REMOVE_REGISTERED_REACTION)))
        .addMethod(
          getSetReactionsParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParams,
              com.desmos.reactions.v1.MsgsProto.MsgSetReactionsParamsResponse>(
                service, METHODID_SET_REACTIONS_PARAMS)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.desmos.reactions.v1.MsgsProto.getDescriptor();
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
              .addMethod(getAddReactionMethod())
              .addMethod(getRemoveReactionMethod())
              .addMethod(getAddRegisteredReactionMethod())
              .addMethod(getEditRegisteredReactionMethod())
              .addMethod(getRemoveRegisteredReactionMethod())
              .addMethod(getSetReactionsParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
