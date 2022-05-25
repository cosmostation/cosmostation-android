package ibc.core.client.v1;

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
 * Msg defines the ibc/client Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: ibc/core/client/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "ibc.core.client.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ibc.core.client.v1.Tx.MsgCreateClient,
      ibc.core.client.v1.Tx.MsgCreateClientResponse> getCreateClientMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateClient",
      requestType = ibc.core.client.v1.Tx.MsgCreateClient.class,
      responseType = ibc.core.client.v1.Tx.MsgCreateClientResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.client.v1.Tx.MsgCreateClient,
      ibc.core.client.v1.Tx.MsgCreateClientResponse> getCreateClientMethod() {
    io.grpc.MethodDescriptor<ibc.core.client.v1.Tx.MsgCreateClient, ibc.core.client.v1.Tx.MsgCreateClientResponse> getCreateClientMethod;
    if ((getCreateClientMethod = MsgGrpc.getCreateClientMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateClientMethod = MsgGrpc.getCreateClientMethod) == null) {
          MsgGrpc.getCreateClientMethod = getCreateClientMethod =
              io.grpc.MethodDescriptor.<ibc.core.client.v1.Tx.MsgCreateClient, ibc.core.client.v1.Tx.MsgCreateClientResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateClient"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.Tx.MsgCreateClient.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.Tx.MsgCreateClientResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateClient"))
              .build();
        }
      }
    }
    return getCreateClientMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.client.v1.Tx.MsgUpdateClient,
      ibc.core.client.v1.Tx.MsgUpdateClientResponse> getUpdateClientMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateClient",
      requestType = ibc.core.client.v1.Tx.MsgUpdateClient.class,
      responseType = ibc.core.client.v1.Tx.MsgUpdateClientResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.client.v1.Tx.MsgUpdateClient,
      ibc.core.client.v1.Tx.MsgUpdateClientResponse> getUpdateClientMethod() {
    io.grpc.MethodDescriptor<ibc.core.client.v1.Tx.MsgUpdateClient, ibc.core.client.v1.Tx.MsgUpdateClientResponse> getUpdateClientMethod;
    if ((getUpdateClientMethod = MsgGrpc.getUpdateClientMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateClientMethod = MsgGrpc.getUpdateClientMethod) == null) {
          MsgGrpc.getUpdateClientMethod = getUpdateClientMethod =
              io.grpc.MethodDescriptor.<ibc.core.client.v1.Tx.MsgUpdateClient, ibc.core.client.v1.Tx.MsgUpdateClientResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateClient"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.Tx.MsgUpdateClient.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.Tx.MsgUpdateClientResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateClient"))
              .build();
        }
      }
    }
    return getUpdateClientMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.client.v1.Tx.MsgUpgradeClient,
      ibc.core.client.v1.Tx.MsgUpgradeClientResponse> getUpgradeClientMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpgradeClient",
      requestType = ibc.core.client.v1.Tx.MsgUpgradeClient.class,
      responseType = ibc.core.client.v1.Tx.MsgUpgradeClientResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.client.v1.Tx.MsgUpgradeClient,
      ibc.core.client.v1.Tx.MsgUpgradeClientResponse> getUpgradeClientMethod() {
    io.grpc.MethodDescriptor<ibc.core.client.v1.Tx.MsgUpgradeClient, ibc.core.client.v1.Tx.MsgUpgradeClientResponse> getUpgradeClientMethod;
    if ((getUpgradeClientMethod = MsgGrpc.getUpgradeClientMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpgradeClientMethod = MsgGrpc.getUpgradeClientMethod) == null) {
          MsgGrpc.getUpgradeClientMethod = getUpgradeClientMethod =
              io.grpc.MethodDescriptor.<ibc.core.client.v1.Tx.MsgUpgradeClient, ibc.core.client.v1.Tx.MsgUpgradeClientResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpgradeClient"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.Tx.MsgUpgradeClient.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.Tx.MsgUpgradeClientResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpgradeClient"))
              .build();
        }
      }
    }
    return getUpgradeClientMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.client.v1.Tx.MsgSubmitMisbehaviour,
      ibc.core.client.v1.Tx.MsgSubmitMisbehaviourResponse> getSubmitMisbehaviourMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitMisbehaviour",
      requestType = ibc.core.client.v1.Tx.MsgSubmitMisbehaviour.class,
      responseType = ibc.core.client.v1.Tx.MsgSubmitMisbehaviourResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.client.v1.Tx.MsgSubmitMisbehaviour,
      ibc.core.client.v1.Tx.MsgSubmitMisbehaviourResponse> getSubmitMisbehaviourMethod() {
    io.grpc.MethodDescriptor<ibc.core.client.v1.Tx.MsgSubmitMisbehaviour, ibc.core.client.v1.Tx.MsgSubmitMisbehaviourResponse> getSubmitMisbehaviourMethod;
    if ((getSubmitMisbehaviourMethod = MsgGrpc.getSubmitMisbehaviourMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSubmitMisbehaviourMethod = MsgGrpc.getSubmitMisbehaviourMethod) == null) {
          MsgGrpc.getSubmitMisbehaviourMethod = getSubmitMisbehaviourMethod =
              io.grpc.MethodDescriptor.<ibc.core.client.v1.Tx.MsgSubmitMisbehaviour, ibc.core.client.v1.Tx.MsgSubmitMisbehaviourResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubmitMisbehaviour"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.Tx.MsgSubmitMisbehaviour.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.Tx.MsgSubmitMisbehaviourResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SubmitMisbehaviour"))
              .build();
        }
      }
    }
    return getSubmitMisbehaviourMethod;
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
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreateClient defines a rpc handler method for MsgCreateClient.
     * </pre>
     */
    public void createClient(ibc.core.client.v1.Tx.MsgCreateClient request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.Tx.MsgCreateClientResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateClientMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateClient defines a rpc handler method for MsgUpdateClient.
     * </pre>
     */
    public void updateClient(ibc.core.client.v1.Tx.MsgUpdateClient request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.Tx.MsgUpdateClientResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateClientMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpgradeClient defines a rpc handler method for MsgUpgradeClient.
     * </pre>
     */
    public void upgradeClient(ibc.core.client.v1.Tx.MsgUpgradeClient request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.Tx.MsgUpgradeClientResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpgradeClientMethod(), responseObserver);
    }

    /**
     * <pre>
     * SubmitMisbehaviour defines a rpc handler method for MsgSubmitMisbehaviour.
     * </pre>
     */
    public void submitMisbehaviour(ibc.core.client.v1.Tx.MsgSubmitMisbehaviour request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.Tx.MsgSubmitMisbehaviourResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubmitMisbehaviourMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateClientMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.client.v1.Tx.MsgCreateClient,
                ibc.core.client.v1.Tx.MsgCreateClientResponse>(
                  this, METHODID_CREATE_CLIENT)))
          .addMethod(
            getUpdateClientMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.client.v1.Tx.MsgUpdateClient,
                ibc.core.client.v1.Tx.MsgUpdateClientResponse>(
                  this, METHODID_UPDATE_CLIENT)))
          .addMethod(
            getUpgradeClientMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.client.v1.Tx.MsgUpgradeClient,
                ibc.core.client.v1.Tx.MsgUpgradeClientResponse>(
                  this, METHODID_UPGRADE_CLIENT)))
          .addMethod(
            getSubmitMisbehaviourMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.client.v1.Tx.MsgSubmitMisbehaviour,
                ibc.core.client.v1.Tx.MsgSubmitMisbehaviourResponse>(
                  this, METHODID_SUBMIT_MISBEHAVIOUR)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the ibc/client Msg service.
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
     * CreateClient defines a rpc handler method for MsgCreateClient.
     * </pre>
     */
    public void createClient(ibc.core.client.v1.Tx.MsgCreateClient request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.Tx.MsgCreateClientResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateClientMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateClient defines a rpc handler method for MsgUpdateClient.
     * </pre>
     */
    public void updateClient(ibc.core.client.v1.Tx.MsgUpdateClient request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.Tx.MsgUpdateClientResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateClientMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpgradeClient defines a rpc handler method for MsgUpgradeClient.
     * </pre>
     */
    public void upgradeClient(ibc.core.client.v1.Tx.MsgUpgradeClient request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.Tx.MsgUpgradeClientResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpgradeClientMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SubmitMisbehaviour defines a rpc handler method for MsgSubmitMisbehaviour.
     * </pre>
     */
    public void submitMisbehaviour(ibc.core.client.v1.Tx.MsgSubmitMisbehaviour request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.Tx.MsgSubmitMisbehaviourResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubmitMisbehaviourMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the ibc/client Msg service.
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
     * CreateClient defines a rpc handler method for MsgCreateClient.
     * </pre>
     */
    public ibc.core.client.v1.Tx.MsgCreateClientResponse createClient(ibc.core.client.v1.Tx.MsgCreateClient request) {
      return blockingUnaryCall(
          getChannel(), getCreateClientMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateClient defines a rpc handler method for MsgUpdateClient.
     * </pre>
     */
    public ibc.core.client.v1.Tx.MsgUpdateClientResponse updateClient(ibc.core.client.v1.Tx.MsgUpdateClient request) {
      return blockingUnaryCall(
          getChannel(), getUpdateClientMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpgradeClient defines a rpc handler method for MsgUpgradeClient.
     * </pre>
     */
    public ibc.core.client.v1.Tx.MsgUpgradeClientResponse upgradeClient(ibc.core.client.v1.Tx.MsgUpgradeClient request) {
      return blockingUnaryCall(
          getChannel(), getUpgradeClientMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SubmitMisbehaviour defines a rpc handler method for MsgSubmitMisbehaviour.
     * </pre>
     */
    public ibc.core.client.v1.Tx.MsgSubmitMisbehaviourResponse submitMisbehaviour(ibc.core.client.v1.Tx.MsgSubmitMisbehaviour request) {
      return blockingUnaryCall(
          getChannel(), getSubmitMisbehaviourMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the ibc/client Msg service.
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
     * CreateClient defines a rpc handler method for MsgCreateClient.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.client.v1.Tx.MsgCreateClientResponse> createClient(
        ibc.core.client.v1.Tx.MsgCreateClient request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateClientMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateClient defines a rpc handler method for MsgUpdateClient.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.client.v1.Tx.MsgUpdateClientResponse> updateClient(
        ibc.core.client.v1.Tx.MsgUpdateClient request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateClientMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpgradeClient defines a rpc handler method for MsgUpgradeClient.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.client.v1.Tx.MsgUpgradeClientResponse> upgradeClient(
        ibc.core.client.v1.Tx.MsgUpgradeClient request) {
      return futureUnaryCall(
          getChannel().newCall(getUpgradeClientMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SubmitMisbehaviour defines a rpc handler method for MsgSubmitMisbehaviour.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.client.v1.Tx.MsgSubmitMisbehaviourResponse> submitMisbehaviour(
        ibc.core.client.v1.Tx.MsgSubmitMisbehaviour request) {
      return futureUnaryCall(
          getChannel().newCall(getSubmitMisbehaviourMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_CLIENT = 0;
  private static final int METHODID_UPDATE_CLIENT = 1;
  private static final int METHODID_UPGRADE_CLIENT = 2;
  private static final int METHODID_SUBMIT_MISBEHAVIOUR = 3;

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
        case METHODID_CREATE_CLIENT:
          serviceImpl.createClient((ibc.core.client.v1.Tx.MsgCreateClient) request,
              (io.grpc.stub.StreamObserver<ibc.core.client.v1.Tx.MsgCreateClientResponse>) responseObserver);
          break;
        case METHODID_UPDATE_CLIENT:
          serviceImpl.updateClient((ibc.core.client.v1.Tx.MsgUpdateClient) request,
              (io.grpc.stub.StreamObserver<ibc.core.client.v1.Tx.MsgUpdateClientResponse>) responseObserver);
          break;
        case METHODID_UPGRADE_CLIENT:
          serviceImpl.upgradeClient((ibc.core.client.v1.Tx.MsgUpgradeClient) request,
              (io.grpc.stub.StreamObserver<ibc.core.client.v1.Tx.MsgUpgradeClientResponse>) responseObserver);
          break;
        case METHODID_SUBMIT_MISBEHAVIOUR:
          serviceImpl.submitMisbehaviour((ibc.core.client.v1.Tx.MsgSubmitMisbehaviour) request,
              (io.grpc.stub.StreamObserver<ibc.core.client.v1.Tx.MsgSubmitMisbehaviourResponse>) responseObserver);
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
      return ibc.core.client.v1.Tx.getDescriptor();
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
              .build();
        }
      }
    }
    return result;
  }
}
