package ibc.core.connection.v1;

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
 * Msg defines the ibc/connection Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: ibc/core/connection/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "ibc.core.connection.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ibc.core.connection.v1.Tx.MsgConnectionOpenInit,
      ibc.core.connection.v1.Tx.MsgConnectionOpenInitResponse> getConnectionOpenInitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConnectionOpenInit",
      requestType = ibc.core.connection.v1.Tx.MsgConnectionOpenInit.class,
      responseType = ibc.core.connection.v1.Tx.MsgConnectionOpenInitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.connection.v1.Tx.MsgConnectionOpenInit,
      ibc.core.connection.v1.Tx.MsgConnectionOpenInitResponse> getConnectionOpenInitMethod() {
    io.grpc.MethodDescriptor<ibc.core.connection.v1.Tx.MsgConnectionOpenInit, ibc.core.connection.v1.Tx.MsgConnectionOpenInitResponse> getConnectionOpenInitMethod;
    if ((getConnectionOpenInitMethod = MsgGrpc.getConnectionOpenInitMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConnectionOpenInitMethod = MsgGrpc.getConnectionOpenInitMethod) == null) {
          MsgGrpc.getConnectionOpenInitMethod = getConnectionOpenInitMethod =
              io.grpc.MethodDescriptor.<ibc.core.connection.v1.Tx.MsgConnectionOpenInit, ibc.core.connection.v1.Tx.MsgConnectionOpenInitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConnectionOpenInit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.connection.v1.Tx.MsgConnectionOpenInit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.connection.v1.Tx.MsgConnectionOpenInitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConnectionOpenInit"))
              .build();
        }
      }
    }
    return getConnectionOpenInitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.connection.v1.Tx.MsgConnectionOpenTry,
      ibc.core.connection.v1.Tx.MsgConnectionOpenTryResponse> getConnectionOpenTryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConnectionOpenTry",
      requestType = ibc.core.connection.v1.Tx.MsgConnectionOpenTry.class,
      responseType = ibc.core.connection.v1.Tx.MsgConnectionOpenTryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.connection.v1.Tx.MsgConnectionOpenTry,
      ibc.core.connection.v1.Tx.MsgConnectionOpenTryResponse> getConnectionOpenTryMethod() {
    io.grpc.MethodDescriptor<ibc.core.connection.v1.Tx.MsgConnectionOpenTry, ibc.core.connection.v1.Tx.MsgConnectionOpenTryResponse> getConnectionOpenTryMethod;
    if ((getConnectionOpenTryMethod = MsgGrpc.getConnectionOpenTryMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConnectionOpenTryMethod = MsgGrpc.getConnectionOpenTryMethod) == null) {
          MsgGrpc.getConnectionOpenTryMethod = getConnectionOpenTryMethod =
              io.grpc.MethodDescriptor.<ibc.core.connection.v1.Tx.MsgConnectionOpenTry, ibc.core.connection.v1.Tx.MsgConnectionOpenTryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConnectionOpenTry"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.connection.v1.Tx.MsgConnectionOpenTry.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.connection.v1.Tx.MsgConnectionOpenTryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConnectionOpenTry"))
              .build();
        }
      }
    }
    return getConnectionOpenTryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.connection.v1.Tx.MsgConnectionOpenAck,
      ibc.core.connection.v1.Tx.MsgConnectionOpenAckResponse> getConnectionOpenAckMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConnectionOpenAck",
      requestType = ibc.core.connection.v1.Tx.MsgConnectionOpenAck.class,
      responseType = ibc.core.connection.v1.Tx.MsgConnectionOpenAckResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.connection.v1.Tx.MsgConnectionOpenAck,
      ibc.core.connection.v1.Tx.MsgConnectionOpenAckResponse> getConnectionOpenAckMethod() {
    io.grpc.MethodDescriptor<ibc.core.connection.v1.Tx.MsgConnectionOpenAck, ibc.core.connection.v1.Tx.MsgConnectionOpenAckResponse> getConnectionOpenAckMethod;
    if ((getConnectionOpenAckMethod = MsgGrpc.getConnectionOpenAckMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConnectionOpenAckMethod = MsgGrpc.getConnectionOpenAckMethod) == null) {
          MsgGrpc.getConnectionOpenAckMethod = getConnectionOpenAckMethod =
              io.grpc.MethodDescriptor.<ibc.core.connection.v1.Tx.MsgConnectionOpenAck, ibc.core.connection.v1.Tx.MsgConnectionOpenAckResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConnectionOpenAck"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.connection.v1.Tx.MsgConnectionOpenAck.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.connection.v1.Tx.MsgConnectionOpenAckResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConnectionOpenAck"))
              .build();
        }
      }
    }
    return getConnectionOpenAckMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.connection.v1.Tx.MsgConnectionOpenConfirm,
      ibc.core.connection.v1.Tx.MsgConnectionOpenConfirmResponse> getConnectionOpenConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConnectionOpenConfirm",
      requestType = ibc.core.connection.v1.Tx.MsgConnectionOpenConfirm.class,
      responseType = ibc.core.connection.v1.Tx.MsgConnectionOpenConfirmResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.connection.v1.Tx.MsgConnectionOpenConfirm,
      ibc.core.connection.v1.Tx.MsgConnectionOpenConfirmResponse> getConnectionOpenConfirmMethod() {
    io.grpc.MethodDescriptor<ibc.core.connection.v1.Tx.MsgConnectionOpenConfirm, ibc.core.connection.v1.Tx.MsgConnectionOpenConfirmResponse> getConnectionOpenConfirmMethod;
    if ((getConnectionOpenConfirmMethod = MsgGrpc.getConnectionOpenConfirmMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getConnectionOpenConfirmMethod = MsgGrpc.getConnectionOpenConfirmMethod) == null) {
          MsgGrpc.getConnectionOpenConfirmMethod = getConnectionOpenConfirmMethod =
              io.grpc.MethodDescriptor.<ibc.core.connection.v1.Tx.MsgConnectionOpenConfirm, ibc.core.connection.v1.Tx.MsgConnectionOpenConfirmResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConnectionOpenConfirm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.connection.v1.Tx.MsgConnectionOpenConfirm.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.connection.v1.Tx.MsgConnectionOpenConfirmResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ConnectionOpenConfirm"))
              .build();
        }
      }
    }
    return getConnectionOpenConfirmMethod;
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
   * Msg defines the ibc/connection Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * ConnectionOpenInit defines a rpc handler method for MsgConnectionOpenInit.
     * </pre>
     */
    public void connectionOpenInit(ibc.core.connection.v1.Tx.MsgConnectionOpenInit request,
        io.grpc.stub.StreamObserver<ibc.core.connection.v1.Tx.MsgConnectionOpenInitResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConnectionOpenInitMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConnectionOpenTry defines a rpc handler method for MsgConnectionOpenTry.
     * </pre>
     */
    public void connectionOpenTry(ibc.core.connection.v1.Tx.MsgConnectionOpenTry request,
        io.grpc.stub.StreamObserver<ibc.core.connection.v1.Tx.MsgConnectionOpenTryResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConnectionOpenTryMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConnectionOpenAck defines a rpc handler method for MsgConnectionOpenAck.
     * </pre>
     */
    public void connectionOpenAck(ibc.core.connection.v1.Tx.MsgConnectionOpenAck request,
        io.grpc.stub.StreamObserver<ibc.core.connection.v1.Tx.MsgConnectionOpenAckResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConnectionOpenAckMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConnectionOpenConfirm defines a rpc handler method for
     * MsgConnectionOpenConfirm.
     * </pre>
     */
    public void connectionOpenConfirm(ibc.core.connection.v1.Tx.MsgConnectionOpenConfirm request,
        io.grpc.stub.StreamObserver<ibc.core.connection.v1.Tx.MsgConnectionOpenConfirmResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConnectionOpenConfirmMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getConnectionOpenInitMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.connection.v1.Tx.MsgConnectionOpenInit,
                ibc.core.connection.v1.Tx.MsgConnectionOpenInitResponse>(
                  this, METHODID_CONNECTION_OPEN_INIT)))
          .addMethod(
            getConnectionOpenTryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.connection.v1.Tx.MsgConnectionOpenTry,
                ibc.core.connection.v1.Tx.MsgConnectionOpenTryResponse>(
                  this, METHODID_CONNECTION_OPEN_TRY)))
          .addMethod(
            getConnectionOpenAckMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.connection.v1.Tx.MsgConnectionOpenAck,
                ibc.core.connection.v1.Tx.MsgConnectionOpenAckResponse>(
                  this, METHODID_CONNECTION_OPEN_ACK)))
          .addMethod(
            getConnectionOpenConfirmMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.connection.v1.Tx.MsgConnectionOpenConfirm,
                ibc.core.connection.v1.Tx.MsgConnectionOpenConfirmResponse>(
                  this, METHODID_CONNECTION_OPEN_CONFIRM)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the ibc/connection Msg service.
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
     * ConnectionOpenInit defines a rpc handler method for MsgConnectionOpenInit.
     * </pre>
     */
    public void connectionOpenInit(ibc.core.connection.v1.Tx.MsgConnectionOpenInit request,
        io.grpc.stub.StreamObserver<ibc.core.connection.v1.Tx.MsgConnectionOpenInitResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConnectionOpenInitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConnectionOpenTry defines a rpc handler method for MsgConnectionOpenTry.
     * </pre>
     */
    public void connectionOpenTry(ibc.core.connection.v1.Tx.MsgConnectionOpenTry request,
        io.grpc.stub.StreamObserver<ibc.core.connection.v1.Tx.MsgConnectionOpenTryResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConnectionOpenTryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConnectionOpenAck defines a rpc handler method for MsgConnectionOpenAck.
     * </pre>
     */
    public void connectionOpenAck(ibc.core.connection.v1.Tx.MsgConnectionOpenAck request,
        io.grpc.stub.StreamObserver<ibc.core.connection.v1.Tx.MsgConnectionOpenAckResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConnectionOpenAckMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConnectionOpenConfirm defines a rpc handler method for
     * MsgConnectionOpenConfirm.
     * </pre>
     */
    public void connectionOpenConfirm(ibc.core.connection.v1.Tx.MsgConnectionOpenConfirm request,
        io.grpc.stub.StreamObserver<ibc.core.connection.v1.Tx.MsgConnectionOpenConfirmResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConnectionOpenConfirmMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the ibc/connection Msg service.
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
     * ConnectionOpenInit defines a rpc handler method for MsgConnectionOpenInit.
     * </pre>
     */
    public ibc.core.connection.v1.Tx.MsgConnectionOpenInitResponse connectionOpenInit(ibc.core.connection.v1.Tx.MsgConnectionOpenInit request) {
      return blockingUnaryCall(
          getChannel(), getConnectionOpenInitMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConnectionOpenTry defines a rpc handler method for MsgConnectionOpenTry.
     * </pre>
     */
    public ibc.core.connection.v1.Tx.MsgConnectionOpenTryResponse connectionOpenTry(ibc.core.connection.v1.Tx.MsgConnectionOpenTry request) {
      return blockingUnaryCall(
          getChannel(), getConnectionOpenTryMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConnectionOpenAck defines a rpc handler method for MsgConnectionOpenAck.
     * </pre>
     */
    public ibc.core.connection.v1.Tx.MsgConnectionOpenAckResponse connectionOpenAck(ibc.core.connection.v1.Tx.MsgConnectionOpenAck request) {
      return blockingUnaryCall(
          getChannel(), getConnectionOpenAckMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConnectionOpenConfirm defines a rpc handler method for
     * MsgConnectionOpenConfirm.
     * </pre>
     */
    public ibc.core.connection.v1.Tx.MsgConnectionOpenConfirmResponse connectionOpenConfirm(ibc.core.connection.v1.Tx.MsgConnectionOpenConfirm request) {
      return blockingUnaryCall(
          getChannel(), getConnectionOpenConfirmMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the ibc/connection Msg service.
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
     * ConnectionOpenInit defines a rpc handler method for MsgConnectionOpenInit.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.connection.v1.Tx.MsgConnectionOpenInitResponse> connectionOpenInit(
        ibc.core.connection.v1.Tx.MsgConnectionOpenInit request) {
      return futureUnaryCall(
          getChannel().newCall(getConnectionOpenInitMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConnectionOpenTry defines a rpc handler method for MsgConnectionOpenTry.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.connection.v1.Tx.MsgConnectionOpenTryResponse> connectionOpenTry(
        ibc.core.connection.v1.Tx.MsgConnectionOpenTry request) {
      return futureUnaryCall(
          getChannel().newCall(getConnectionOpenTryMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConnectionOpenAck defines a rpc handler method for MsgConnectionOpenAck.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.connection.v1.Tx.MsgConnectionOpenAckResponse> connectionOpenAck(
        ibc.core.connection.v1.Tx.MsgConnectionOpenAck request) {
      return futureUnaryCall(
          getChannel().newCall(getConnectionOpenAckMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConnectionOpenConfirm defines a rpc handler method for
     * MsgConnectionOpenConfirm.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.connection.v1.Tx.MsgConnectionOpenConfirmResponse> connectionOpenConfirm(
        ibc.core.connection.v1.Tx.MsgConnectionOpenConfirm request) {
      return futureUnaryCall(
          getChannel().newCall(getConnectionOpenConfirmMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CONNECTION_OPEN_INIT = 0;
  private static final int METHODID_CONNECTION_OPEN_TRY = 1;
  private static final int METHODID_CONNECTION_OPEN_ACK = 2;
  private static final int METHODID_CONNECTION_OPEN_CONFIRM = 3;

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
        case METHODID_CONNECTION_OPEN_INIT:
          serviceImpl.connectionOpenInit((ibc.core.connection.v1.Tx.MsgConnectionOpenInit) request,
              (io.grpc.stub.StreamObserver<ibc.core.connection.v1.Tx.MsgConnectionOpenInitResponse>) responseObserver);
          break;
        case METHODID_CONNECTION_OPEN_TRY:
          serviceImpl.connectionOpenTry((ibc.core.connection.v1.Tx.MsgConnectionOpenTry) request,
              (io.grpc.stub.StreamObserver<ibc.core.connection.v1.Tx.MsgConnectionOpenTryResponse>) responseObserver);
          break;
        case METHODID_CONNECTION_OPEN_ACK:
          serviceImpl.connectionOpenAck((ibc.core.connection.v1.Tx.MsgConnectionOpenAck) request,
              (io.grpc.stub.StreamObserver<ibc.core.connection.v1.Tx.MsgConnectionOpenAckResponse>) responseObserver);
          break;
        case METHODID_CONNECTION_OPEN_CONFIRM:
          serviceImpl.connectionOpenConfirm((ibc.core.connection.v1.Tx.MsgConnectionOpenConfirm) request,
              (io.grpc.stub.StreamObserver<ibc.core.connection.v1.Tx.MsgConnectionOpenConfirmResponse>) responseObserver);
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
      return ibc.core.connection.v1.Tx.getDescriptor();
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
              .addMethod(getConnectionOpenInitMethod())
              .addMethod(getConnectionOpenTryMethod())
              .addMethod(getConnectionOpenAckMethod())
              .addMethod(getConnectionOpenConfirmMethod())
              .build();
        }
      }
    }
    return result;
  }
}
