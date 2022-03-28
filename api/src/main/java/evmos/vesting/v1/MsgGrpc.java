package evmos.vesting.v1;

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
 * Msg defines the bank Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: evmos/vesting/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "evmos.vesting.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccount,
      evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccountResponse> getCreateClawbackVestingAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateClawbackVestingAccount",
      requestType = evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccount.class,
      responseType = evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccount,
      evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccountResponse> getCreateClawbackVestingAccountMethod() {
    io.grpc.MethodDescriptor<evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccount, evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccountResponse> getCreateClawbackVestingAccountMethod;
    if ((getCreateClawbackVestingAccountMethod = MsgGrpc.getCreateClawbackVestingAccountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateClawbackVestingAccountMethod = MsgGrpc.getCreateClawbackVestingAccountMethod) == null) {
          MsgGrpc.getCreateClawbackVestingAccountMethod = getCreateClawbackVestingAccountMethod =
              io.grpc.MethodDescriptor.<evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccount, evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateClawbackVestingAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateClawbackVestingAccount"))
              .build();
        }
      }
    }
    return getCreateClawbackVestingAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<evmos.vesting.v1.Tx.MsgClawback,
      evmos.vesting.v1.Tx.MsgClawbackResponse> getClawbackMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Clawback",
      requestType = evmos.vesting.v1.Tx.MsgClawback.class,
      responseType = evmos.vesting.v1.Tx.MsgClawbackResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<evmos.vesting.v1.Tx.MsgClawback,
      evmos.vesting.v1.Tx.MsgClawbackResponse> getClawbackMethod() {
    io.grpc.MethodDescriptor<evmos.vesting.v1.Tx.MsgClawback, evmos.vesting.v1.Tx.MsgClawbackResponse> getClawbackMethod;
    if ((getClawbackMethod = MsgGrpc.getClawbackMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getClawbackMethod = MsgGrpc.getClawbackMethod) == null) {
          MsgGrpc.getClawbackMethod = getClawbackMethod =
              io.grpc.MethodDescriptor.<evmos.vesting.v1.Tx.MsgClawback, evmos.vesting.v1.Tx.MsgClawbackResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Clawback"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.vesting.v1.Tx.MsgClawback.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.vesting.v1.Tx.MsgClawbackResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Clawback"))
              .build();
        }
      }
    }
    return getClawbackMethod;
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
   * Msg defines the bank Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreateClawbackVestingAccount defines a method that enables creating a
     * vesting account that is subject to clawback.
     * </pre>
     */
    public void createClawbackVestingAccount(evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccount request,
        io.grpc.stub.StreamObserver<evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateClawbackVestingAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * Clawback removes the unvested tokens from a ClawbackVestingAccount.
     * </pre>
     */
    public void clawback(evmos.vesting.v1.Tx.MsgClawback request,
        io.grpc.stub.StreamObserver<evmos.vesting.v1.Tx.MsgClawbackResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClawbackMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateClawbackVestingAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccount,
                evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccountResponse>(
                  this, METHODID_CREATE_CLAWBACK_VESTING_ACCOUNT)))
          .addMethod(
            getClawbackMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                evmos.vesting.v1.Tx.MsgClawback,
                evmos.vesting.v1.Tx.MsgClawbackResponse>(
                  this, METHODID_CLAWBACK)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the bank Msg service.
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
     * CreateClawbackVestingAccount defines a method that enables creating a
     * vesting account that is subject to clawback.
     * </pre>
     */
    public void createClawbackVestingAccount(evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccount request,
        io.grpc.stub.StreamObserver<evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateClawbackVestingAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Clawback removes the unvested tokens from a ClawbackVestingAccount.
     * </pre>
     */
    public void clawback(evmos.vesting.v1.Tx.MsgClawback request,
        io.grpc.stub.StreamObserver<evmos.vesting.v1.Tx.MsgClawbackResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClawbackMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the bank Msg service.
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
     * CreateClawbackVestingAccount defines a method that enables creating a
     * vesting account that is subject to clawback.
     * </pre>
     */
    public evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccountResponse createClawbackVestingAccount(evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccount request) {
      return blockingUnaryCall(
          getChannel(), getCreateClawbackVestingAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Clawback removes the unvested tokens from a ClawbackVestingAccount.
     * </pre>
     */
    public evmos.vesting.v1.Tx.MsgClawbackResponse clawback(evmos.vesting.v1.Tx.MsgClawback request) {
      return blockingUnaryCall(
          getChannel(), getClawbackMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the bank Msg service.
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
     * CreateClawbackVestingAccount defines a method that enables creating a
     * vesting account that is subject to clawback.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccountResponse> createClawbackVestingAccount(
        evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccount request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateClawbackVestingAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Clawback removes the unvested tokens from a ClawbackVestingAccount.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<evmos.vesting.v1.Tx.MsgClawbackResponse> clawback(
        evmos.vesting.v1.Tx.MsgClawback request) {
      return futureUnaryCall(
          getChannel().newCall(getClawbackMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_CLAWBACK_VESTING_ACCOUNT = 0;
  private static final int METHODID_CLAWBACK = 1;

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
        case METHODID_CREATE_CLAWBACK_VESTING_ACCOUNT:
          serviceImpl.createClawbackVestingAccount((evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccount) request,
              (io.grpc.stub.StreamObserver<evmos.vesting.v1.Tx.MsgCreateClawbackVestingAccountResponse>) responseObserver);
          break;
        case METHODID_CLAWBACK:
          serviceImpl.clawback((evmos.vesting.v1.Tx.MsgClawback) request,
              (io.grpc.stub.StreamObserver<evmos.vesting.v1.Tx.MsgClawbackResponse>) responseObserver);
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
      return evmos.vesting.v1.Tx.getDescriptor();
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
              .addMethod(getCreateClawbackVestingAccountMethod())
              .addMethod(getClawbackMethod())
              .build();
        }
      }
    }
    return result;
  }
}
