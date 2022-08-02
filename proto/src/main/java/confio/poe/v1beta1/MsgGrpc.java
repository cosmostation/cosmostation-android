package confio.poe.v1beta1;

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
 * Msg defines the staking Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: confio/poe/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "confio.poe.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<confio.poe.v1beta1.Tx.MsgCreateValidator,
      confio.poe.v1beta1.Tx.MsgCreateValidatorResponse> getCreateValidatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateValidator",
      requestType = confio.poe.v1beta1.Tx.MsgCreateValidator.class,
      responseType = confio.poe.v1beta1.Tx.MsgCreateValidatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<confio.poe.v1beta1.Tx.MsgCreateValidator,
      confio.poe.v1beta1.Tx.MsgCreateValidatorResponse> getCreateValidatorMethod() {
    io.grpc.MethodDescriptor<confio.poe.v1beta1.Tx.MsgCreateValidator, confio.poe.v1beta1.Tx.MsgCreateValidatorResponse> getCreateValidatorMethod;
    if ((getCreateValidatorMethod = MsgGrpc.getCreateValidatorMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateValidatorMethod = MsgGrpc.getCreateValidatorMethod) == null) {
          MsgGrpc.getCreateValidatorMethod = getCreateValidatorMethod =
              io.grpc.MethodDescriptor.<confio.poe.v1beta1.Tx.MsgCreateValidator, confio.poe.v1beta1.Tx.MsgCreateValidatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateValidator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.Tx.MsgCreateValidator.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.Tx.MsgCreateValidatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateValidator"))
              .build();
        }
      }
    }
    return getCreateValidatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<confio.poe.v1beta1.Tx.MsgUpdateValidator,
      confio.poe.v1beta1.Tx.MsgUpdateValidatorResponse> getUpdateValidatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateValidator",
      requestType = confio.poe.v1beta1.Tx.MsgUpdateValidator.class,
      responseType = confio.poe.v1beta1.Tx.MsgUpdateValidatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<confio.poe.v1beta1.Tx.MsgUpdateValidator,
      confio.poe.v1beta1.Tx.MsgUpdateValidatorResponse> getUpdateValidatorMethod() {
    io.grpc.MethodDescriptor<confio.poe.v1beta1.Tx.MsgUpdateValidator, confio.poe.v1beta1.Tx.MsgUpdateValidatorResponse> getUpdateValidatorMethod;
    if ((getUpdateValidatorMethod = MsgGrpc.getUpdateValidatorMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateValidatorMethod = MsgGrpc.getUpdateValidatorMethod) == null) {
          MsgGrpc.getUpdateValidatorMethod = getUpdateValidatorMethod =
              io.grpc.MethodDescriptor.<confio.poe.v1beta1.Tx.MsgUpdateValidator, confio.poe.v1beta1.Tx.MsgUpdateValidatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateValidator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.Tx.MsgUpdateValidator.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.Tx.MsgUpdateValidatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateValidator"))
              .build();
        }
      }
    }
    return getUpdateValidatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<confio.poe.v1beta1.Tx.MsgDelegate,
      confio.poe.v1beta1.Tx.MsgDelegateResponse> getDelegateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Delegate",
      requestType = confio.poe.v1beta1.Tx.MsgDelegate.class,
      responseType = confio.poe.v1beta1.Tx.MsgDelegateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<confio.poe.v1beta1.Tx.MsgDelegate,
      confio.poe.v1beta1.Tx.MsgDelegateResponse> getDelegateMethod() {
    io.grpc.MethodDescriptor<confio.poe.v1beta1.Tx.MsgDelegate, confio.poe.v1beta1.Tx.MsgDelegateResponse> getDelegateMethod;
    if ((getDelegateMethod = MsgGrpc.getDelegateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDelegateMethod = MsgGrpc.getDelegateMethod) == null) {
          MsgGrpc.getDelegateMethod = getDelegateMethod =
              io.grpc.MethodDescriptor.<confio.poe.v1beta1.Tx.MsgDelegate, confio.poe.v1beta1.Tx.MsgDelegateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Delegate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.Tx.MsgDelegate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.Tx.MsgDelegateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Delegate"))
              .build();
        }
      }
    }
    return getDelegateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<confio.poe.v1beta1.Tx.MsgUndelegate,
      confio.poe.v1beta1.Tx.MsgUndelegateResponse> getUndelegateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Undelegate",
      requestType = confio.poe.v1beta1.Tx.MsgUndelegate.class,
      responseType = confio.poe.v1beta1.Tx.MsgUndelegateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<confio.poe.v1beta1.Tx.MsgUndelegate,
      confio.poe.v1beta1.Tx.MsgUndelegateResponse> getUndelegateMethod() {
    io.grpc.MethodDescriptor<confio.poe.v1beta1.Tx.MsgUndelegate, confio.poe.v1beta1.Tx.MsgUndelegateResponse> getUndelegateMethod;
    if ((getUndelegateMethod = MsgGrpc.getUndelegateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUndelegateMethod = MsgGrpc.getUndelegateMethod) == null) {
          MsgGrpc.getUndelegateMethod = getUndelegateMethod =
              io.grpc.MethodDescriptor.<confio.poe.v1beta1.Tx.MsgUndelegate, confio.poe.v1beta1.Tx.MsgUndelegateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Undelegate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.Tx.MsgUndelegate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.Tx.MsgUndelegateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Undelegate"))
              .build();
        }
      }
    }
    return getUndelegateMethod;
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
   * Msg defines the staking Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreateValidator defines a method for creating a new validator.
     * </pre>
     */
    public void createValidator(confio.poe.v1beta1.Tx.MsgCreateValidator request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.Tx.MsgCreateValidatorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateValidatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * MsgCreateValidator defines a method for updating validator metadata
     * </pre>
     */
    public void updateValidator(confio.poe.v1beta1.Tx.MsgUpdateValidator request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.Tx.MsgUpdateValidatorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateValidatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * Delegate defines a method for performing a self delegation of coins
     * by a node operator
     * </pre>
     */
    public void delegate(confio.poe.v1beta1.Tx.MsgDelegate request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.Tx.MsgDelegateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDelegateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Undelegate defines a method for performing an undelegation from a
     * node operator
     * </pre>
     */
    public void undelegate(confio.poe.v1beta1.Tx.MsgUndelegate request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.Tx.MsgUndelegateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUndelegateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateValidatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                confio.poe.v1beta1.Tx.MsgCreateValidator,
                confio.poe.v1beta1.Tx.MsgCreateValidatorResponse>(
                  this, METHODID_CREATE_VALIDATOR)))
          .addMethod(
            getUpdateValidatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                confio.poe.v1beta1.Tx.MsgUpdateValidator,
                confio.poe.v1beta1.Tx.MsgUpdateValidatorResponse>(
                  this, METHODID_UPDATE_VALIDATOR)))
          .addMethod(
            getDelegateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                confio.poe.v1beta1.Tx.MsgDelegate,
                confio.poe.v1beta1.Tx.MsgDelegateResponse>(
                  this, METHODID_DELEGATE)))
          .addMethod(
            getUndelegateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                confio.poe.v1beta1.Tx.MsgUndelegate,
                confio.poe.v1beta1.Tx.MsgUndelegateResponse>(
                  this, METHODID_UNDELEGATE)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the staking Msg service.
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
     * CreateValidator defines a method for creating a new validator.
     * </pre>
     */
    public void createValidator(confio.poe.v1beta1.Tx.MsgCreateValidator request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.Tx.MsgCreateValidatorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateValidatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MsgCreateValidator defines a method for updating validator metadata
     * </pre>
     */
    public void updateValidator(confio.poe.v1beta1.Tx.MsgUpdateValidator request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.Tx.MsgUpdateValidatorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateValidatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Delegate defines a method for performing a self delegation of coins
     * by a node operator
     * </pre>
     */
    public void delegate(confio.poe.v1beta1.Tx.MsgDelegate request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.Tx.MsgDelegateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDelegateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Undelegate defines a method for performing an undelegation from a
     * node operator
     * </pre>
     */
    public void undelegate(confio.poe.v1beta1.Tx.MsgUndelegate request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.Tx.MsgUndelegateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUndelegateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the staking Msg service.
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
     * CreateValidator defines a method for creating a new validator.
     * </pre>
     */
    public confio.poe.v1beta1.Tx.MsgCreateValidatorResponse createValidator(confio.poe.v1beta1.Tx.MsgCreateValidator request) {
      return blockingUnaryCall(
          getChannel(), getCreateValidatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MsgCreateValidator defines a method for updating validator metadata
     * </pre>
     */
    public confio.poe.v1beta1.Tx.MsgUpdateValidatorResponse updateValidator(confio.poe.v1beta1.Tx.MsgUpdateValidator request) {
      return blockingUnaryCall(
          getChannel(), getUpdateValidatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Delegate defines a method for performing a self delegation of coins
     * by a node operator
     * </pre>
     */
    public confio.poe.v1beta1.Tx.MsgDelegateResponse delegate(confio.poe.v1beta1.Tx.MsgDelegate request) {
      return blockingUnaryCall(
          getChannel(), getDelegateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Undelegate defines a method for performing an undelegation from a
     * node operator
     * </pre>
     */
    public confio.poe.v1beta1.Tx.MsgUndelegateResponse undelegate(confio.poe.v1beta1.Tx.MsgUndelegate request) {
      return blockingUnaryCall(
          getChannel(), getUndelegateMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the staking Msg service.
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
     * CreateValidator defines a method for creating a new validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<confio.poe.v1beta1.Tx.MsgCreateValidatorResponse> createValidator(
        confio.poe.v1beta1.Tx.MsgCreateValidator request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateValidatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MsgCreateValidator defines a method for updating validator metadata
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<confio.poe.v1beta1.Tx.MsgUpdateValidatorResponse> updateValidator(
        confio.poe.v1beta1.Tx.MsgUpdateValidator request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateValidatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Delegate defines a method for performing a self delegation of coins
     * by a node operator
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<confio.poe.v1beta1.Tx.MsgDelegateResponse> delegate(
        confio.poe.v1beta1.Tx.MsgDelegate request) {
      return futureUnaryCall(
          getChannel().newCall(getDelegateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Undelegate defines a method for performing an undelegation from a
     * node operator
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<confio.poe.v1beta1.Tx.MsgUndelegateResponse> undelegate(
        confio.poe.v1beta1.Tx.MsgUndelegate request) {
      return futureUnaryCall(
          getChannel().newCall(getUndelegateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_VALIDATOR = 0;
  private static final int METHODID_UPDATE_VALIDATOR = 1;
  private static final int METHODID_DELEGATE = 2;
  private static final int METHODID_UNDELEGATE = 3;

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
        case METHODID_CREATE_VALIDATOR:
          serviceImpl.createValidator((confio.poe.v1beta1.Tx.MsgCreateValidator) request,
              (io.grpc.stub.StreamObserver<confio.poe.v1beta1.Tx.MsgCreateValidatorResponse>) responseObserver);
          break;
        case METHODID_UPDATE_VALIDATOR:
          serviceImpl.updateValidator((confio.poe.v1beta1.Tx.MsgUpdateValidator) request,
              (io.grpc.stub.StreamObserver<confio.poe.v1beta1.Tx.MsgUpdateValidatorResponse>) responseObserver);
          break;
        case METHODID_DELEGATE:
          serviceImpl.delegate((confio.poe.v1beta1.Tx.MsgDelegate) request,
              (io.grpc.stub.StreamObserver<confio.poe.v1beta1.Tx.MsgDelegateResponse>) responseObserver);
          break;
        case METHODID_UNDELEGATE:
          serviceImpl.undelegate((confio.poe.v1beta1.Tx.MsgUndelegate) request,
              (io.grpc.stub.StreamObserver<confio.poe.v1beta1.Tx.MsgUndelegateResponse>) responseObserver);
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
      return confio.poe.v1beta1.Tx.getDescriptor();
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
              .addMethod(getCreateValidatorMethod())
              .addMethod(getUpdateValidatorMethod())
              .addMethod(getDelegateMethod())
              .addMethod(getUndelegateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
