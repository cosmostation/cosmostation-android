package gravity.v1;

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
 * Msg defines the state transitions possible within gravity
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: gravity/v1/msgs.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "gravity.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgSendToEthereum,
      gravity.v1.Msgs.MsgSendToEthereumResponse> getSendToEthereumMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendToEthereum",
      requestType = gravity.v1.Msgs.MsgSendToEthereum.class,
      responseType = gravity.v1.Msgs.MsgSendToEthereumResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgSendToEthereum,
      gravity.v1.Msgs.MsgSendToEthereumResponse> getSendToEthereumMethod() {
    io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgSendToEthereum, gravity.v1.Msgs.MsgSendToEthereumResponse> getSendToEthereumMethod;
    if ((getSendToEthereumMethod = MsgGrpc.getSendToEthereumMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSendToEthereumMethod = MsgGrpc.getSendToEthereumMethod) == null) {
          MsgGrpc.getSendToEthereumMethod = getSendToEthereumMethod =
              io.grpc.MethodDescriptor.<gravity.v1.Msgs.MsgSendToEthereum, gravity.v1.Msgs.MsgSendToEthereumResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendToEthereum"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.Msgs.MsgSendToEthereum.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.Msgs.MsgSendToEthereumResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SendToEthereum"))
              .build();
        }
      }
    }
    return getSendToEthereumMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgCancelSendToEthereum,
      gravity.v1.Msgs.MsgCancelSendToEthereumResponse> getCancelSendToEthereumMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelSendToEthereum",
      requestType = gravity.v1.Msgs.MsgCancelSendToEthereum.class,
      responseType = gravity.v1.Msgs.MsgCancelSendToEthereumResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgCancelSendToEthereum,
      gravity.v1.Msgs.MsgCancelSendToEthereumResponse> getCancelSendToEthereumMethod() {
    io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgCancelSendToEthereum, gravity.v1.Msgs.MsgCancelSendToEthereumResponse> getCancelSendToEthereumMethod;
    if ((getCancelSendToEthereumMethod = MsgGrpc.getCancelSendToEthereumMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelSendToEthereumMethod = MsgGrpc.getCancelSendToEthereumMethod) == null) {
          MsgGrpc.getCancelSendToEthereumMethod = getCancelSendToEthereumMethod =
              io.grpc.MethodDescriptor.<gravity.v1.Msgs.MsgCancelSendToEthereum, gravity.v1.Msgs.MsgCancelSendToEthereumResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelSendToEthereum"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.Msgs.MsgCancelSendToEthereum.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.Msgs.MsgCancelSendToEthereumResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CancelSendToEthereum"))
              .build();
        }
      }
    }
    return getCancelSendToEthereumMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgRequestBatchTx,
      gravity.v1.Msgs.MsgRequestBatchTxResponse> getRequestBatchTxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestBatchTx",
      requestType = gravity.v1.Msgs.MsgRequestBatchTx.class,
      responseType = gravity.v1.Msgs.MsgRequestBatchTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgRequestBatchTx,
      gravity.v1.Msgs.MsgRequestBatchTxResponse> getRequestBatchTxMethod() {
    io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgRequestBatchTx, gravity.v1.Msgs.MsgRequestBatchTxResponse> getRequestBatchTxMethod;
    if ((getRequestBatchTxMethod = MsgGrpc.getRequestBatchTxMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRequestBatchTxMethod = MsgGrpc.getRequestBatchTxMethod) == null) {
          MsgGrpc.getRequestBatchTxMethod = getRequestBatchTxMethod =
              io.grpc.MethodDescriptor.<gravity.v1.Msgs.MsgRequestBatchTx, gravity.v1.Msgs.MsgRequestBatchTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestBatchTx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.Msgs.MsgRequestBatchTx.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.Msgs.MsgRequestBatchTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RequestBatchTx"))
              .build();
        }
      }
    }
    return getRequestBatchTxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgSubmitEthereumTxConfirmation,
      gravity.v1.Msgs.MsgSubmitEthereumTxConfirmationResponse> getSubmitEthereumTxConfirmationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitEthereumTxConfirmation",
      requestType = gravity.v1.Msgs.MsgSubmitEthereumTxConfirmation.class,
      responseType = gravity.v1.Msgs.MsgSubmitEthereumTxConfirmationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgSubmitEthereumTxConfirmation,
      gravity.v1.Msgs.MsgSubmitEthereumTxConfirmationResponse> getSubmitEthereumTxConfirmationMethod() {
    io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgSubmitEthereumTxConfirmation, gravity.v1.Msgs.MsgSubmitEthereumTxConfirmationResponse> getSubmitEthereumTxConfirmationMethod;
    if ((getSubmitEthereumTxConfirmationMethod = MsgGrpc.getSubmitEthereumTxConfirmationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSubmitEthereumTxConfirmationMethod = MsgGrpc.getSubmitEthereumTxConfirmationMethod) == null) {
          MsgGrpc.getSubmitEthereumTxConfirmationMethod = getSubmitEthereumTxConfirmationMethod =
              io.grpc.MethodDescriptor.<gravity.v1.Msgs.MsgSubmitEthereumTxConfirmation, gravity.v1.Msgs.MsgSubmitEthereumTxConfirmationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubmitEthereumTxConfirmation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.Msgs.MsgSubmitEthereumTxConfirmation.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.Msgs.MsgSubmitEthereumTxConfirmationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SubmitEthereumTxConfirmation"))
              .build();
        }
      }
    }
    return getSubmitEthereumTxConfirmationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgSubmitEthereumEvent,
      gravity.v1.Msgs.MsgSubmitEthereumEventResponse> getSubmitEthereumEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitEthereumEvent",
      requestType = gravity.v1.Msgs.MsgSubmitEthereumEvent.class,
      responseType = gravity.v1.Msgs.MsgSubmitEthereumEventResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgSubmitEthereumEvent,
      gravity.v1.Msgs.MsgSubmitEthereumEventResponse> getSubmitEthereumEventMethod() {
    io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgSubmitEthereumEvent, gravity.v1.Msgs.MsgSubmitEthereumEventResponse> getSubmitEthereumEventMethod;
    if ((getSubmitEthereumEventMethod = MsgGrpc.getSubmitEthereumEventMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSubmitEthereumEventMethod = MsgGrpc.getSubmitEthereumEventMethod) == null) {
          MsgGrpc.getSubmitEthereumEventMethod = getSubmitEthereumEventMethod =
              io.grpc.MethodDescriptor.<gravity.v1.Msgs.MsgSubmitEthereumEvent, gravity.v1.Msgs.MsgSubmitEthereumEventResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubmitEthereumEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.Msgs.MsgSubmitEthereumEvent.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.Msgs.MsgSubmitEthereumEventResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SubmitEthereumEvent"))
              .build();
        }
      }
    }
    return getSubmitEthereumEventMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgDelegateKeys,
      gravity.v1.Msgs.MsgDelegateKeysResponse> getSetDelegateKeysMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetDelegateKeys",
      requestType = gravity.v1.Msgs.MsgDelegateKeys.class,
      responseType = gravity.v1.Msgs.MsgDelegateKeysResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgDelegateKeys,
      gravity.v1.Msgs.MsgDelegateKeysResponse> getSetDelegateKeysMethod() {
    io.grpc.MethodDescriptor<gravity.v1.Msgs.MsgDelegateKeys, gravity.v1.Msgs.MsgDelegateKeysResponse> getSetDelegateKeysMethod;
    if ((getSetDelegateKeysMethod = MsgGrpc.getSetDelegateKeysMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetDelegateKeysMethod = MsgGrpc.getSetDelegateKeysMethod) == null) {
          MsgGrpc.getSetDelegateKeysMethod = getSetDelegateKeysMethod =
              io.grpc.MethodDescriptor.<gravity.v1.Msgs.MsgDelegateKeys, gravity.v1.Msgs.MsgDelegateKeysResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetDelegateKeys"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.Msgs.MsgDelegateKeys.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.Msgs.MsgDelegateKeysResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetDelegateKeys"))
              .build();
        }
      }
    }
    return getSetDelegateKeysMethod;
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
   * Msg defines the state transitions possible within gravity
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/send_to_ethereum";
     * </pre>
     */
    public void sendToEthereum(gravity.v1.Msgs.MsgSendToEthereum request,
        io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgSendToEthereumResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendToEthereumMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/send_to_ethereum/cancel";
     * </pre>
     */
    public void cancelSendToEthereum(gravity.v1.Msgs.MsgCancelSendToEthereum request,
        io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgCancelSendToEthereumResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelSendToEthereumMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/batchtx/request";
     * </pre>
     */
    public void requestBatchTx(gravity.v1.Msgs.MsgRequestBatchTx request,
        io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgRequestBatchTxResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestBatchTxMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/ethereum_signature";
     * </pre>
     */
    public void submitEthereumTxConfirmation(gravity.v1.Msgs.MsgSubmitEthereumTxConfirmation request,
        io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgSubmitEthereumTxConfirmationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubmitEthereumTxConfirmationMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/ethereum_event";
     * </pre>
     */
    public void submitEthereumEvent(gravity.v1.Msgs.MsgSubmitEthereumEvent request,
        io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgSubmitEthereumEventResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubmitEthereumEventMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/delegate_keys";
     * </pre>
     */
    public void setDelegateKeys(gravity.v1.Msgs.MsgDelegateKeys request,
        io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgDelegateKeysResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetDelegateKeysMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendToEthereumMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.Msgs.MsgSendToEthereum,
                gravity.v1.Msgs.MsgSendToEthereumResponse>(
                  this, METHODID_SEND_TO_ETHEREUM)))
          .addMethod(
            getCancelSendToEthereumMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.Msgs.MsgCancelSendToEthereum,
                gravity.v1.Msgs.MsgCancelSendToEthereumResponse>(
                  this, METHODID_CANCEL_SEND_TO_ETHEREUM)))
          .addMethod(
            getRequestBatchTxMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.Msgs.MsgRequestBatchTx,
                gravity.v1.Msgs.MsgRequestBatchTxResponse>(
                  this, METHODID_REQUEST_BATCH_TX)))
          .addMethod(
            getSubmitEthereumTxConfirmationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.Msgs.MsgSubmitEthereumTxConfirmation,
                gravity.v1.Msgs.MsgSubmitEthereumTxConfirmationResponse>(
                  this, METHODID_SUBMIT_ETHEREUM_TX_CONFIRMATION)))
          .addMethod(
            getSubmitEthereumEventMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.Msgs.MsgSubmitEthereumEvent,
                gravity.v1.Msgs.MsgSubmitEthereumEventResponse>(
                  this, METHODID_SUBMIT_ETHEREUM_EVENT)))
          .addMethod(
            getSetDelegateKeysMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.Msgs.MsgDelegateKeys,
                gravity.v1.Msgs.MsgDelegateKeysResponse>(
                  this, METHODID_SET_DELEGATE_KEYS)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the state transitions possible within gravity
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
     * option (google.api.http).post = "/gravity/v1/send_to_ethereum";
     * </pre>
     */
    public void sendToEthereum(gravity.v1.Msgs.MsgSendToEthereum request,
        io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgSendToEthereumResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendToEthereumMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/send_to_ethereum/cancel";
     * </pre>
     */
    public void cancelSendToEthereum(gravity.v1.Msgs.MsgCancelSendToEthereum request,
        io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgCancelSendToEthereumResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelSendToEthereumMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/batchtx/request";
     * </pre>
     */
    public void requestBatchTx(gravity.v1.Msgs.MsgRequestBatchTx request,
        io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgRequestBatchTxResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestBatchTxMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/ethereum_signature";
     * </pre>
     */
    public void submitEthereumTxConfirmation(gravity.v1.Msgs.MsgSubmitEthereumTxConfirmation request,
        io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgSubmitEthereumTxConfirmationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubmitEthereumTxConfirmationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/ethereum_event";
     * </pre>
     */
    public void submitEthereumEvent(gravity.v1.Msgs.MsgSubmitEthereumEvent request,
        io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgSubmitEthereumEventResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubmitEthereumEventMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/delegate_keys";
     * </pre>
     */
    public void setDelegateKeys(gravity.v1.Msgs.MsgDelegateKeys request,
        io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgDelegateKeysResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetDelegateKeysMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the state transitions possible within gravity
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
     * option (google.api.http).post = "/gravity/v1/send_to_ethereum";
     * </pre>
     */
    public gravity.v1.Msgs.MsgSendToEthereumResponse sendToEthereum(gravity.v1.Msgs.MsgSendToEthereum request) {
      return blockingUnaryCall(
          getChannel(), getSendToEthereumMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/send_to_ethereum/cancel";
     * </pre>
     */
    public gravity.v1.Msgs.MsgCancelSendToEthereumResponse cancelSendToEthereum(gravity.v1.Msgs.MsgCancelSendToEthereum request) {
      return blockingUnaryCall(
          getChannel(), getCancelSendToEthereumMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/batchtx/request";
     * </pre>
     */
    public gravity.v1.Msgs.MsgRequestBatchTxResponse requestBatchTx(gravity.v1.Msgs.MsgRequestBatchTx request) {
      return blockingUnaryCall(
          getChannel(), getRequestBatchTxMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/ethereum_signature";
     * </pre>
     */
    public gravity.v1.Msgs.MsgSubmitEthereumTxConfirmationResponse submitEthereumTxConfirmation(gravity.v1.Msgs.MsgSubmitEthereumTxConfirmation request) {
      return blockingUnaryCall(
          getChannel(), getSubmitEthereumTxConfirmationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/ethereum_event";
     * </pre>
     */
    public gravity.v1.Msgs.MsgSubmitEthereumEventResponse submitEthereumEvent(gravity.v1.Msgs.MsgSubmitEthereumEvent request) {
      return blockingUnaryCall(
          getChannel(), getSubmitEthereumEventMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/delegate_keys";
     * </pre>
     */
    public gravity.v1.Msgs.MsgDelegateKeysResponse setDelegateKeys(gravity.v1.Msgs.MsgDelegateKeys request) {
      return blockingUnaryCall(
          getChannel(), getSetDelegateKeysMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the state transitions possible within gravity
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
     * option (google.api.http).post = "/gravity/v1/send_to_ethereum";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.Msgs.MsgSendToEthereumResponse> sendToEthereum(
        gravity.v1.Msgs.MsgSendToEthereum request) {
      return futureUnaryCall(
          getChannel().newCall(getSendToEthereumMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/send_to_ethereum/cancel";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.Msgs.MsgCancelSendToEthereumResponse> cancelSendToEthereum(
        gravity.v1.Msgs.MsgCancelSendToEthereum request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelSendToEthereumMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/batchtx/request";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.Msgs.MsgRequestBatchTxResponse> requestBatchTx(
        gravity.v1.Msgs.MsgRequestBatchTx request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestBatchTxMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/ethereum_signature";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.Msgs.MsgSubmitEthereumTxConfirmationResponse> submitEthereumTxConfirmation(
        gravity.v1.Msgs.MsgSubmitEthereumTxConfirmation request) {
      return futureUnaryCall(
          getChannel().newCall(getSubmitEthereumTxConfirmationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/ethereum_event";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.Msgs.MsgSubmitEthereumEventResponse> submitEthereumEvent(
        gravity.v1.Msgs.MsgSubmitEthereumEvent request) {
      return futureUnaryCall(
          getChannel().newCall(getSubmitEthereumEventMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).post = "/gravity/v1/delegate_keys";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.Msgs.MsgDelegateKeysResponse> setDelegateKeys(
        gravity.v1.Msgs.MsgDelegateKeys request) {
      return futureUnaryCall(
          getChannel().newCall(getSetDelegateKeysMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_TO_ETHEREUM = 0;
  private static final int METHODID_CANCEL_SEND_TO_ETHEREUM = 1;
  private static final int METHODID_REQUEST_BATCH_TX = 2;
  private static final int METHODID_SUBMIT_ETHEREUM_TX_CONFIRMATION = 3;
  private static final int METHODID_SUBMIT_ETHEREUM_EVENT = 4;
  private static final int METHODID_SET_DELEGATE_KEYS = 5;

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
        case METHODID_SEND_TO_ETHEREUM:
          serviceImpl.sendToEthereum((gravity.v1.Msgs.MsgSendToEthereum) request,
              (io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgSendToEthereumResponse>) responseObserver);
          break;
        case METHODID_CANCEL_SEND_TO_ETHEREUM:
          serviceImpl.cancelSendToEthereum((gravity.v1.Msgs.MsgCancelSendToEthereum) request,
              (io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgCancelSendToEthereumResponse>) responseObserver);
          break;
        case METHODID_REQUEST_BATCH_TX:
          serviceImpl.requestBatchTx((gravity.v1.Msgs.MsgRequestBatchTx) request,
              (io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgRequestBatchTxResponse>) responseObserver);
          break;
        case METHODID_SUBMIT_ETHEREUM_TX_CONFIRMATION:
          serviceImpl.submitEthereumTxConfirmation((gravity.v1.Msgs.MsgSubmitEthereumTxConfirmation) request,
              (io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgSubmitEthereumTxConfirmationResponse>) responseObserver);
          break;
        case METHODID_SUBMIT_ETHEREUM_EVENT:
          serviceImpl.submitEthereumEvent((gravity.v1.Msgs.MsgSubmitEthereumEvent) request,
              (io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgSubmitEthereumEventResponse>) responseObserver);
          break;
        case METHODID_SET_DELEGATE_KEYS:
          serviceImpl.setDelegateKeys((gravity.v1.Msgs.MsgDelegateKeys) request,
              (io.grpc.stub.StreamObserver<gravity.v1.Msgs.MsgDelegateKeysResponse>) responseObserver);
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
      return gravity.v1.Msgs.getDescriptor();
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
              .addMethod(getSendToEthereumMethod())
              .addMethod(getCancelSendToEthereumMethod())
              .addMethod(getRequestBatchTxMethod())
              .addMethod(getSubmitEthereumTxConfirmationMethod())
              .addMethod(getSubmitEthereumEventMethod())
              .addMethod(getSetDelegateKeysMethod())
              .build();
        }
      }
    }
    return result;
  }
}
