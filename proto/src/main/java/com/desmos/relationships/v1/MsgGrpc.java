package com.desmos.relationships.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the relationships Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: desmos/relationships/v1/msgs.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "desmos.relationships.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.desmos.relationships.v1.MsgsProto.MsgCreateRelationship,
      com.desmos.relationships.v1.MsgsProto.MsgCreateRelationshipResponse> getCreateRelationshipMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateRelationship",
      requestType = com.desmos.relationships.v1.MsgsProto.MsgCreateRelationship.class,
      responseType = com.desmos.relationships.v1.MsgsProto.MsgCreateRelationshipResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.relationships.v1.MsgsProto.MsgCreateRelationship,
      com.desmos.relationships.v1.MsgsProto.MsgCreateRelationshipResponse> getCreateRelationshipMethod() {
    io.grpc.MethodDescriptor<com.desmos.relationships.v1.MsgsProto.MsgCreateRelationship, com.desmos.relationships.v1.MsgsProto.MsgCreateRelationshipResponse> getCreateRelationshipMethod;
    if ((getCreateRelationshipMethod = MsgGrpc.getCreateRelationshipMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateRelationshipMethod = MsgGrpc.getCreateRelationshipMethod) == null) {
          MsgGrpc.getCreateRelationshipMethod = getCreateRelationshipMethod =
              io.grpc.MethodDescriptor.<com.desmos.relationships.v1.MsgsProto.MsgCreateRelationship, com.desmos.relationships.v1.MsgsProto.MsgCreateRelationshipResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateRelationship"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.relationships.v1.MsgsProto.MsgCreateRelationship.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.relationships.v1.MsgsProto.MsgCreateRelationshipResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateRelationship"))
              .build();
        }
      }
    }
    return getCreateRelationshipMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationship,
      com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationshipResponse> getDeleteRelationshipMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteRelationship",
      requestType = com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationship.class,
      responseType = com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationshipResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationship,
      com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationshipResponse> getDeleteRelationshipMethod() {
    io.grpc.MethodDescriptor<com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationship, com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationshipResponse> getDeleteRelationshipMethod;
    if ((getDeleteRelationshipMethod = MsgGrpc.getDeleteRelationshipMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteRelationshipMethod = MsgGrpc.getDeleteRelationshipMethod) == null) {
          MsgGrpc.getDeleteRelationshipMethod = getDeleteRelationshipMethod =
              io.grpc.MethodDescriptor.<com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationship, com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationshipResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteRelationship"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationship.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationshipResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteRelationship"))
              .build();
        }
      }
    }
    return getDeleteRelationshipMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.relationships.v1.MsgsProto.MsgBlockUser,
      com.desmos.relationships.v1.MsgsProto.MsgBlockUserResponse> getBlockUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BlockUser",
      requestType = com.desmos.relationships.v1.MsgsProto.MsgBlockUser.class,
      responseType = com.desmos.relationships.v1.MsgsProto.MsgBlockUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.relationships.v1.MsgsProto.MsgBlockUser,
      com.desmos.relationships.v1.MsgsProto.MsgBlockUserResponse> getBlockUserMethod() {
    io.grpc.MethodDescriptor<com.desmos.relationships.v1.MsgsProto.MsgBlockUser, com.desmos.relationships.v1.MsgsProto.MsgBlockUserResponse> getBlockUserMethod;
    if ((getBlockUserMethod = MsgGrpc.getBlockUserMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBlockUserMethod = MsgGrpc.getBlockUserMethod) == null) {
          MsgGrpc.getBlockUserMethod = getBlockUserMethod =
              io.grpc.MethodDescriptor.<com.desmos.relationships.v1.MsgsProto.MsgBlockUser, com.desmos.relationships.v1.MsgsProto.MsgBlockUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BlockUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.relationships.v1.MsgsProto.MsgBlockUser.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.relationships.v1.MsgsProto.MsgBlockUserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BlockUser"))
              .build();
        }
      }
    }
    return getBlockUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.relationships.v1.MsgsProto.MsgUnblockUser,
      com.desmos.relationships.v1.MsgsProto.MsgUnblockUserResponse> getUnblockUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnblockUser",
      requestType = com.desmos.relationships.v1.MsgsProto.MsgUnblockUser.class,
      responseType = com.desmos.relationships.v1.MsgsProto.MsgUnblockUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.relationships.v1.MsgsProto.MsgUnblockUser,
      com.desmos.relationships.v1.MsgsProto.MsgUnblockUserResponse> getUnblockUserMethod() {
    io.grpc.MethodDescriptor<com.desmos.relationships.v1.MsgsProto.MsgUnblockUser, com.desmos.relationships.v1.MsgsProto.MsgUnblockUserResponse> getUnblockUserMethod;
    if ((getUnblockUserMethod = MsgGrpc.getUnblockUserMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUnblockUserMethod = MsgGrpc.getUnblockUserMethod) == null) {
          MsgGrpc.getUnblockUserMethod = getUnblockUserMethod =
              io.grpc.MethodDescriptor.<com.desmos.relationships.v1.MsgsProto.MsgUnblockUser, com.desmos.relationships.v1.MsgsProto.MsgUnblockUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnblockUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.relationships.v1.MsgsProto.MsgUnblockUser.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.relationships.v1.MsgsProto.MsgUnblockUserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UnblockUser"))
              .build();
        }
      }
    }
    return getUnblockUserMethod;
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
   * Msg defines the relationships Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * CreateRelationship defines a method for creating a new relationship
     * </pre>
     */
    default void createRelationship(com.desmos.relationships.v1.MsgsProto.MsgCreateRelationship request,
        io.grpc.stub.StreamObserver<com.desmos.relationships.v1.MsgsProto.MsgCreateRelationshipResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateRelationshipMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteRelationship defines a method for deleting a relationship
     * </pre>
     */
    default void deleteRelationship(com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationship request,
        io.grpc.stub.StreamObserver<com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationshipResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteRelationshipMethod(), responseObserver);
    }

    /**
     * <pre>
     * BlockUser defines a method for blocking a user
     * </pre>
     */
    default void blockUser(com.desmos.relationships.v1.MsgsProto.MsgBlockUser request,
        io.grpc.stub.StreamObserver<com.desmos.relationships.v1.MsgsProto.MsgBlockUserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBlockUserMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnblockUser defines a method for unblocking a user
     * </pre>
     */
    default void unblockUser(com.desmos.relationships.v1.MsgsProto.MsgUnblockUser request,
        io.grpc.stub.StreamObserver<com.desmos.relationships.v1.MsgsProto.MsgUnblockUserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnblockUserMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the relationships Msg service.
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
   * Msg defines the relationships Msg service.
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
     * CreateRelationship defines a method for creating a new relationship
     * </pre>
     */
    public void createRelationship(com.desmos.relationships.v1.MsgsProto.MsgCreateRelationship request,
        io.grpc.stub.StreamObserver<com.desmos.relationships.v1.MsgsProto.MsgCreateRelationshipResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateRelationshipMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteRelationship defines a method for deleting a relationship
     * </pre>
     */
    public void deleteRelationship(com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationship request,
        io.grpc.stub.StreamObserver<com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationshipResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteRelationshipMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BlockUser defines a method for blocking a user
     * </pre>
     */
    public void blockUser(com.desmos.relationships.v1.MsgsProto.MsgBlockUser request,
        io.grpc.stub.StreamObserver<com.desmos.relationships.v1.MsgsProto.MsgBlockUserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBlockUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnblockUser defines a method for unblocking a user
     * </pre>
     */
    public void unblockUser(com.desmos.relationships.v1.MsgsProto.MsgUnblockUser request,
        io.grpc.stub.StreamObserver<com.desmos.relationships.v1.MsgsProto.MsgUnblockUserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnblockUserMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the relationships Msg service.
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
     * CreateRelationship defines a method for creating a new relationship
     * </pre>
     */
    public com.desmos.relationships.v1.MsgsProto.MsgCreateRelationshipResponse createRelationship(com.desmos.relationships.v1.MsgsProto.MsgCreateRelationship request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateRelationshipMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteRelationship defines a method for deleting a relationship
     * </pre>
     */
    public com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationshipResponse deleteRelationship(com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationship request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteRelationshipMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BlockUser defines a method for blocking a user
     * </pre>
     */
    public com.desmos.relationships.v1.MsgsProto.MsgBlockUserResponse blockUser(com.desmos.relationships.v1.MsgsProto.MsgBlockUser request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBlockUserMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnblockUser defines a method for unblocking a user
     * </pre>
     */
    public com.desmos.relationships.v1.MsgsProto.MsgUnblockUserResponse unblockUser(com.desmos.relationships.v1.MsgsProto.MsgUnblockUser request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnblockUserMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the relationships Msg service.
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
     * CreateRelationship defines a method for creating a new relationship
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.relationships.v1.MsgsProto.MsgCreateRelationshipResponse> createRelationship(
        com.desmos.relationships.v1.MsgsProto.MsgCreateRelationship request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateRelationshipMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteRelationship defines a method for deleting a relationship
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationshipResponse> deleteRelationship(
        com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationship request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteRelationshipMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BlockUser defines a method for blocking a user
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.relationships.v1.MsgsProto.MsgBlockUserResponse> blockUser(
        com.desmos.relationships.v1.MsgsProto.MsgBlockUser request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBlockUserMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnblockUser defines a method for unblocking a user
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.relationships.v1.MsgsProto.MsgUnblockUserResponse> unblockUser(
        com.desmos.relationships.v1.MsgsProto.MsgUnblockUser request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnblockUserMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_RELATIONSHIP = 0;
  private static final int METHODID_DELETE_RELATIONSHIP = 1;
  private static final int METHODID_BLOCK_USER = 2;
  private static final int METHODID_UNBLOCK_USER = 3;

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
        case METHODID_CREATE_RELATIONSHIP:
          serviceImpl.createRelationship((com.desmos.relationships.v1.MsgsProto.MsgCreateRelationship) request,
              (io.grpc.stub.StreamObserver<com.desmos.relationships.v1.MsgsProto.MsgCreateRelationshipResponse>) responseObserver);
          break;
        case METHODID_DELETE_RELATIONSHIP:
          serviceImpl.deleteRelationship((com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationship) request,
              (io.grpc.stub.StreamObserver<com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationshipResponse>) responseObserver);
          break;
        case METHODID_BLOCK_USER:
          serviceImpl.blockUser((com.desmos.relationships.v1.MsgsProto.MsgBlockUser) request,
              (io.grpc.stub.StreamObserver<com.desmos.relationships.v1.MsgsProto.MsgBlockUserResponse>) responseObserver);
          break;
        case METHODID_UNBLOCK_USER:
          serviceImpl.unblockUser((com.desmos.relationships.v1.MsgsProto.MsgUnblockUser) request,
              (io.grpc.stub.StreamObserver<com.desmos.relationships.v1.MsgsProto.MsgUnblockUserResponse>) responseObserver);
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
          getCreateRelationshipMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.relationships.v1.MsgsProto.MsgCreateRelationship,
              com.desmos.relationships.v1.MsgsProto.MsgCreateRelationshipResponse>(
                service, METHODID_CREATE_RELATIONSHIP)))
        .addMethod(
          getDeleteRelationshipMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationship,
              com.desmos.relationships.v1.MsgsProto.MsgDeleteRelationshipResponse>(
                service, METHODID_DELETE_RELATIONSHIP)))
        .addMethod(
          getBlockUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.relationships.v1.MsgsProto.MsgBlockUser,
              com.desmos.relationships.v1.MsgsProto.MsgBlockUserResponse>(
                service, METHODID_BLOCK_USER)))
        .addMethod(
          getUnblockUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.relationships.v1.MsgsProto.MsgUnblockUser,
              com.desmos.relationships.v1.MsgsProto.MsgUnblockUserResponse>(
                service, METHODID_UNBLOCK_USER)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.desmos.relationships.v1.MsgsProto.getDescriptor();
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
              .addMethod(getCreateRelationshipMethod())
              .addMethod(getDeleteRelationshipMethod())
              .addMethod(getBlockUserMethod())
              .addMethod(getUnblockUserMethod())
              .build();
        }
      }
    }
    return result;
  }
}
