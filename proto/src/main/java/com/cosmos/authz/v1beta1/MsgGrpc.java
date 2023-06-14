package com.cosmos.authz.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the authz Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmos/authz/v1beta1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "cosmos.authz.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.TxProto.MsgGrant,
      com.cosmos.authz.v1beta1.TxProto.MsgGrantResponse> getGrantMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Grant",
      requestType = com.cosmos.authz.v1beta1.TxProto.MsgGrant.class,
      responseType = com.cosmos.authz.v1beta1.TxProto.MsgGrantResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.TxProto.MsgGrant,
      com.cosmos.authz.v1beta1.TxProto.MsgGrantResponse> getGrantMethod() {
    io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.TxProto.MsgGrant, com.cosmos.authz.v1beta1.TxProto.MsgGrantResponse> getGrantMethod;
    if ((getGrantMethod = MsgGrpc.getGrantMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getGrantMethod = MsgGrpc.getGrantMethod) == null) {
          MsgGrpc.getGrantMethod = getGrantMethod =
              io.grpc.MethodDescriptor.<com.cosmos.authz.v1beta1.TxProto.MsgGrant, com.cosmos.authz.v1beta1.TxProto.MsgGrantResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Grant"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.authz.v1beta1.TxProto.MsgGrant.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.authz.v1beta1.TxProto.MsgGrantResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Grant"))
              .build();
        }
      }
    }
    return getGrantMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.TxProto.MsgExec,
      com.cosmos.authz.v1beta1.TxProto.MsgExecResponse> getExecMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Exec",
      requestType = com.cosmos.authz.v1beta1.TxProto.MsgExec.class,
      responseType = com.cosmos.authz.v1beta1.TxProto.MsgExecResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.TxProto.MsgExec,
      com.cosmos.authz.v1beta1.TxProto.MsgExecResponse> getExecMethod() {
    io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.TxProto.MsgExec, com.cosmos.authz.v1beta1.TxProto.MsgExecResponse> getExecMethod;
    if ((getExecMethod = MsgGrpc.getExecMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getExecMethod = MsgGrpc.getExecMethod) == null) {
          MsgGrpc.getExecMethod = getExecMethod =
              io.grpc.MethodDescriptor.<com.cosmos.authz.v1beta1.TxProto.MsgExec, com.cosmos.authz.v1beta1.TxProto.MsgExecResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Exec"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.authz.v1beta1.TxProto.MsgExec.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.authz.v1beta1.TxProto.MsgExecResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Exec"))
              .build();
        }
      }
    }
    return getExecMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.TxProto.MsgRevoke,
      com.cosmos.authz.v1beta1.TxProto.MsgRevokeResponse> getRevokeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Revoke",
      requestType = com.cosmos.authz.v1beta1.TxProto.MsgRevoke.class,
      responseType = com.cosmos.authz.v1beta1.TxProto.MsgRevokeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.TxProto.MsgRevoke,
      com.cosmos.authz.v1beta1.TxProto.MsgRevokeResponse> getRevokeMethod() {
    io.grpc.MethodDescriptor<com.cosmos.authz.v1beta1.TxProto.MsgRevoke, com.cosmos.authz.v1beta1.TxProto.MsgRevokeResponse> getRevokeMethod;
    if ((getRevokeMethod = MsgGrpc.getRevokeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRevokeMethod = MsgGrpc.getRevokeMethod) == null) {
          MsgGrpc.getRevokeMethod = getRevokeMethod =
              io.grpc.MethodDescriptor.<com.cosmos.authz.v1beta1.TxProto.MsgRevoke, com.cosmos.authz.v1beta1.TxProto.MsgRevokeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Revoke"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.authz.v1beta1.TxProto.MsgRevoke.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.authz.v1beta1.TxProto.MsgRevokeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Revoke"))
              .build();
        }
      }
    }
    return getRevokeMethod;
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
   * Msg defines the authz Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Grant grants the provided authorization to the grantee on the granter's
     * account with the provided expiration time. If there is already a grant
     * for the given (granter, grantee, Authorization) triple, then the grant
     * will be overwritten.
     * </pre>
     */
    default void grant(com.cosmos.authz.v1beta1.TxProto.MsgGrant request,
        io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.TxProto.MsgGrantResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGrantMethod(), responseObserver);
    }

    /**
     * <pre>
     * Exec attempts to execute the provided messages using
     * authorizations granted to the grantee. Each message should have only
     * one signer corresponding to the granter of the authorization.
     * </pre>
     */
    default void exec(com.cosmos.authz.v1beta1.TxProto.MsgExec request,
        io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.TxProto.MsgExecResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExecMethod(), responseObserver);
    }

    /**
     * <pre>
     * Revoke revokes any authorization corresponding to the provided method name on the
     * granter's account that has been granted to the grantee.
     * </pre>
     */
    default void revoke(com.cosmos.authz.v1beta1.TxProto.MsgRevoke request,
        io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.TxProto.MsgRevokeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRevokeMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the authz Msg service.
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
   * Msg defines the authz Msg service.
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
     * Grant grants the provided authorization to the grantee on the granter's
     * account with the provided expiration time. If there is already a grant
     * for the given (granter, grantee, Authorization) triple, then the grant
     * will be overwritten.
     * </pre>
     */
    public void grant(com.cosmos.authz.v1beta1.TxProto.MsgGrant request,
        io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.TxProto.MsgGrantResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGrantMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Exec attempts to execute the provided messages using
     * authorizations granted to the grantee. Each message should have only
     * one signer corresponding to the granter of the authorization.
     * </pre>
     */
    public void exec(com.cosmos.authz.v1beta1.TxProto.MsgExec request,
        io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.TxProto.MsgExecResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExecMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Revoke revokes any authorization corresponding to the provided method name on the
     * granter's account that has been granted to the grantee.
     * </pre>
     */
    public void revoke(com.cosmos.authz.v1beta1.TxProto.MsgRevoke request,
        io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.TxProto.MsgRevokeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRevokeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the authz Msg service.
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
     * Grant grants the provided authorization to the grantee on the granter's
     * account with the provided expiration time. If there is already a grant
     * for the given (granter, grantee, Authorization) triple, then the grant
     * will be overwritten.
     * </pre>
     */
    public com.cosmos.authz.v1beta1.TxProto.MsgGrantResponse grant(com.cosmos.authz.v1beta1.TxProto.MsgGrant request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGrantMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Exec attempts to execute the provided messages using
     * authorizations granted to the grantee. Each message should have only
     * one signer corresponding to the granter of the authorization.
     * </pre>
     */
    public com.cosmos.authz.v1beta1.TxProto.MsgExecResponse exec(com.cosmos.authz.v1beta1.TxProto.MsgExec request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExecMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Revoke revokes any authorization corresponding to the provided method name on the
     * granter's account that has been granted to the grantee.
     * </pre>
     */
    public com.cosmos.authz.v1beta1.TxProto.MsgRevokeResponse revoke(com.cosmos.authz.v1beta1.TxProto.MsgRevoke request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRevokeMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the authz Msg service.
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
     * Grant grants the provided authorization to the grantee on the granter's
     * account with the provided expiration time. If there is already a grant
     * for the given (granter, grantee, Authorization) triple, then the grant
     * will be overwritten.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.authz.v1beta1.TxProto.MsgGrantResponse> grant(
        com.cosmos.authz.v1beta1.TxProto.MsgGrant request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGrantMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Exec attempts to execute the provided messages using
     * authorizations granted to the grantee. Each message should have only
     * one signer corresponding to the granter of the authorization.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.authz.v1beta1.TxProto.MsgExecResponse> exec(
        com.cosmos.authz.v1beta1.TxProto.MsgExec request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExecMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Revoke revokes any authorization corresponding to the provided method name on the
     * granter's account that has been granted to the grantee.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.authz.v1beta1.TxProto.MsgRevokeResponse> revoke(
        com.cosmos.authz.v1beta1.TxProto.MsgRevoke request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRevokeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GRANT = 0;
  private static final int METHODID_EXEC = 1;
  private static final int METHODID_REVOKE = 2;

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
        case METHODID_GRANT:
          serviceImpl.grant((com.cosmos.authz.v1beta1.TxProto.MsgGrant) request,
              (io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.TxProto.MsgGrantResponse>) responseObserver);
          break;
        case METHODID_EXEC:
          serviceImpl.exec((com.cosmos.authz.v1beta1.TxProto.MsgExec) request,
              (io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.TxProto.MsgExecResponse>) responseObserver);
          break;
        case METHODID_REVOKE:
          serviceImpl.revoke((com.cosmos.authz.v1beta1.TxProto.MsgRevoke) request,
              (io.grpc.stub.StreamObserver<com.cosmos.authz.v1beta1.TxProto.MsgRevokeResponse>) responseObserver);
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
          getGrantMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.authz.v1beta1.TxProto.MsgGrant,
              com.cosmos.authz.v1beta1.TxProto.MsgGrantResponse>(
                service, METHODID_GRANT)))
        .addMethod(
          getExecMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.authz.v1beta1.TxProto.MsgExec,
              com.cosmos.authz.v1beta1.TxProto.MsgExecResponse>(
                service, METHODID_EXEC)))
        .addMethod(
          getRevokeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.authz.v1beta1.TxProto.MsgRevoke,
              com.cosmos.authz.v1beta1.TxProto.MsgRevokeResponse>(
                service, METHODID_REVOKE)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmos.authz.v1beta1.TxProto.getDescriptor();
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
              .addMethod(getGrantMethod())
              .addMethod(getExecMethod())
              .addMethod(getRevokeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
