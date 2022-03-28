package axelar.bitcoin.v1beta1;

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
 * Msg defines the bitcoin Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: axelar/bitcoin/v1beta1/service.proto")
public final class MsgServiceGrpc {

  private MsgServiceGrpc() {}

  public static final String SERVICE_NAME = "axelar.bitcoin.v1beta1.MsgService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.LinkRequest,
      axelar.bitcoin.v1beta1.Tx.LinkResponse> getLinkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Link",
      requestType = axelar.bitcoin.v1beta1.Tx.LinkRequest.class,
      responseType = axelar.bitcoin.v1beta1.Tx.LinkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.LinkRequest,
      axelar.bitcoin.v1beta1.Tx.LinkResponse> getLinkMethod() {
    io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.LinkRequest, axelar.bitcoin.v1beta1.Tx.LinkResponse> getLinkMethod;
    if ((getLinkMethod = MsgServiceGrpc.getLinkMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getLinkMethod = MsgServiceGrpc.getLinkMethod) == null) {
          MsgServiceGrpc.getLinkMethod = getLinkMethod =
              io.grpc.MethodDescriptor.<axelar.bitcoin.v1beta1.Tx.LinkRequest, axelar.bitcoin.v1beta1.Tx.LinkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Link"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.bitcoin.v1beta1.Tx.LinkRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.bitcoin.v1beta1.Tx.LinkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("Link"))
              .build();
        }
      }
    }
    return getLinkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.ConfirmOutpointRequest,
      axelar.bitcoin.v1beta1.Tx.ConfirmOutpointResponse> getConfirmOutpointMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmOutpoint",
      requestType = axelar.bitcoin.v1beta1.Tx.ConfirmOutpointRequest.class,
      responseType = axelar.bitcoin.v1beta1.Tx.ConfirmOutpointResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.ConfirmOutpointRequest,
      axelar.bitcoin.v1beta1.Tx.ConfirmOutpointResponse> getConfirmOutpointMethod() {
    io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.ConfirmOutpointRequest, axelar.bitcoin.v1beta1.Tx.ConfirmOutpointResponse> getConfirmOutpointMethod;
    if ((getConfirmOutpointMethod = MsgServiceGrpc.getConfirmOutpointMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getConfirmOutpointMethod = MsgServiceGrpc.getConfirmOutpointMethod) == null) {
          MsgServiceGrpc.getConfirmOutpointMethod = getConfirmOutpointMethod =
              io.grpc.MethodDescriptor.<axelar.bitcoin.v1beta1.Tx.ConfirmOutpointRequest, axelar.bitcoin.v1beta1.Tx.ConfirmOutpointResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConfirmOutpoint"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.bitcoin.v1beta1.Tx.ConfirmOutpointRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.bitcoin.v1beta1.Tx.ConfirmOutpointResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("ConfirmOutpoint"))
              .build();
        }
      }
    }
    return getConfirmOutpointMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointRequest,
      axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointResponse> getVoteConfirmOutpointMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "VoteConfirmOutpoint",
      requestType = axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointRequest.class,
      responseType = axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointRequest,
      axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointResponse> getVoteConfirmOutpointMethod() {
    io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointRequest, axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointResponse> getVoteConfirmOutpointMethod;
    if ((getVoteConfirmOutpointMethod = MsgServiceGrpc.getVoteConfirmOutpointMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getVoteConfirmOutpointMethod = MsgServiceGrpc.getVoteConfirmOutpointMethod) == null) {
          MsgServiceGrpc.getVoteConfirmOutpointMethod = getVoteConfirmOutpointMethod =
              io.grpc.MethodDescriptor.<axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointRequest, axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "VoteConfirmOutpoint"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("VoteConfirmOutpoint"))
              .build();
        }
      }
    }
    return getVoteConfirmOutpointMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxRequest,
      axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxResponse> getCreatePendingTransfersTxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePendingTransfersTx",
      requestType = axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxRequest.class,
      responseType = axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxRequest,
      axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxResponse> getCreatePendingTransfersTxMethod() {
    io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxRequest, axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxResponse> getCreatePendingTransfersTxMethod;
    if ((getCreatePendingTransfersTxMethod = MsgServiceGrpc.getCreatePendingTransfersTxMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getCreatePendingTransfersTxMethod = MsgServiceGrpc.getCreatePendingTransfersTxMethod) == null) {
          MsgServiceGrpc.getCreatePendingTransfersTxMethod = getCreatePendingTransfersTxMethod =
              io.grpc.MethodDescriptor.<axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxRequest, axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePendingTransfersTx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("CreatePendingTransfersTx"))
              .build();
        }
      }
    }
    return getCreatePendingTransfersTxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.CreateMasterTxRequest,
      axelar.bitcoin.v1beta1.Tx.CreateMasterTxResponse> getCreateMasterTxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateMasterTx",
      requestType = axelar.bitcoin.v1beta1.Tx.CreateMasterTxRequest.class,
      responseType = axelar.bitcoin.v1beta1.Tx.CreateMasterTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.CreateMasterTxRequest,
      axelar.bitcoin.v1beta1.Tx.CreateMasterTxResponse> getCreateMasterTxMethod() {
    io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.CreateMasterTxRequest, axelar.bitcoin.v1beta1.Tx.CreateMasterTxResponse> getCreateMasterTxMethod;
    if ((getCreateMasterTxMethod = MsgServiceGrpc.getCreateMasterTxMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getCreateMasterTxMethod = MsgServiceGrpc.getCreateMasterTxMethod) == null) {
          MsgServiceGrpc.getCreateMasterTxMethod = getCreateMasterTxMethod =
              io.grpc.MethodDescriptor.<axelar.bitcoin.v1beta1.Tx.CreateMasterTxRequest, axelar.bitcoin.v1beta1.Tx.CreateMasterTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateMasterTx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.bitcoin.v1beta1.Tx.CreateMasterTxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.bitcoin.v1beta1.Tx.CreateMasterTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("CreateMasterTx"))
              .build();
        }
      }
    }
    return getCreateMasterTxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.CreateRescueTxRequest,
      axelar.bitcoin.v1beta1.Tx.CreateRescueTxResponse> getCreateRescueTxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateRescueTx",
      requestType = axelar.bitcoin.v1beta1.Tx.CreateRescueTxRequest.class,
      responseType = axelar.bitcoin.v1beta1.Tx.CreateRescueTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.CreateRescueTxRequest,
      axelar.bitcoin.v1beta1.Tx.CreateRescueTxResponse> getCreateRescueTxMethod() {
    io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.CreateRescueTxRequest, axelar.bitcoin.v1beta1.Tx.CreateRescueTxResponse> getCreateRescueTxMethod;
    if ((getCreateRescueTxMethod = MsgServiceGrpc.getCreateRescueTxMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getCreateRescueTxMethod = MsgServiceGrpc.getCreateRescueTxMethod) == null) {
          MsgServiceGrpc.getCreateRescueTxMethod = getCreateRescueTxMethod =
              io.grpc.MethodDescriptor.<axelar.bitcoin.v1beta1.Tx.CreateRescueTxRequest, axelar.bitcoin.v1beta1.Tx.CreateRescueTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateRescueTx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.bitcoin.v1beta1.Tx.CreateRescueTxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.bitcoin.v1beta1.Tx.CreateRescueTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("CreateRescueTx"))
              .build();
        }
      }
    }
    return getCreateRescueTxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.SignTxRequest,
      axelar.bitcoin.v1beta1.Tx.SignTxResponse> getSignTxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SignTx",
      requestType = axelar.bitcoin.v1beta1.Tx.SignTxRequest.class,
      responseType = axelar.bitcoin.v1beta1.Tx.SignTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.SignTxRequest,
      axelar.bitcoin.v1beta1.Tx.SignTxResponse> getSignTxMethod() {
    io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.SignTxRequest, axelar.bitcoin.v1beta1.Tx.SignTxResponse> getSignTxMethod;
    if ((getSignTxMethod = MsgServiceGrpc.getSignTxMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getSignTxMethod = MsgServiceGrpc.getSignTxMethod) == null) {
          MsgServiceGrpc.getSignTxMethod = getSignTxMethod =
              io.grpc.MethodDescriptor.<axelar.bitcoin.v1beta1.Tx.SignTxRequest, axelar.bitcoin.v1beta1.Tx.SignTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SignTx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.bitcoin.v1beta1.Tx.SignTxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.bitcoin.v1beta1.Tx.SignTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("SignTx"))
              .build();
        }
      }
    }
    return getSignTxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureRequest,
      axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureResponse> getSubmitExternalSignatureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitExternalSignature",
      requestType = axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureRequest.class,
      responseType = axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureRequest,
      axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureResponse> getSubmitExternalSignatureMethod() {
    io.grpc.MethodDescriptor<axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureRequest, axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureResponse> getSubmitExternalSignatureMethod;
    if ((getSubmitExternalSignatureMethod = MsgServiceGrpc.getSubmitExternalSignatureMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getSubmitExternalSignatureMethod = MsgServiceGrpc.getSubmitExternalSignatureMethod) == null) {
          MsgServiceGrpc.getSubmitExternalSignatureMethod = getSubmitExternalSignatureMethod =
              io.grpc.MethodDescriptor.<axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureRequest, axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubmitExternalSignature"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("SubmitExternalSignature"))
              .build();
        }
      }
    }
    return getSubmitExternalSignatureMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MsgServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceStub>() {
        @java.lang.Override
        public MsgServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceStub(channel, callOptions);
        }
      };
    return MsgServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MsgServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceBlockingStub>() {
        @java.lang.Override
        public MsgServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceBlockingStub(channel, callOptions);
        }
      };
    return MsgServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MsgServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceFutureStub>() {
        @java.lang.Override
        public MsgServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceFutureStub(channel, callOptions);
        }
      };
    return MsgServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Msg defines the bitcoin Msg service.
   * </pre>
   */
  public static abstract class MsgServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void link(axelar.bitcoin.v1beta1.Tx.LinkRequest request,
        io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.LinkResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLinkMethod(), responseObserver);
    }

    /**
     */
    public void confirmOutpoint(axelar.bitcoin.v1beta1.Tx.ConfirmOutpointRequest request,
        io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.ConfirmOutpointResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConfirmOutpointMethod(), responseObserver);
    }

    /**
     */
    public void voteConfirmOutpoint(axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointRequest request,
        io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVoteConfirmOutpointMethod(), responseObserver);
    }

    /**
     */
    public void createPendingTransfersTx(axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxRequest request,
        io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreatePendingTransfersTxMethod(), responseObserver);
    }

    /**
     */
    public void createMasterTx(axelar.bitcoin.v1beta1.Tx.CreateMasterTxRequest request,
        io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.CreateMasterTxResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateMasterTxMethod(), responseObserver);
    }

    /**
     */
    public void createRescueTx(axelar.bitcoin.v1beta1.Tx.CreateRescueTxRequest request,
        io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.CreateRescueTxResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateRescueTxMethod(), responseObserver);
    }

    /**
     */
    public void signTx(axelar.bitcoin.v1beta1.Tx.SignTxRequest request,
        io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.SignTxResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSignTxMethod(), responseObserver);
    }

    /**
     */
    public void submitExternalSignature(axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureRequest request,
        io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubmitExternalSignatureMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLinkMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.bitcoin.v1beta1.Tx.LinkRequest,
                axelar.bitcoin.v1beta1.Tx.LinkResponse>(
                  this, METHODID_LINK)))
          .addMethod(
            getConfirmOutpointMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.bitcoin.v1beta1.Tx.ConfirmOutpointRequest,
                axelar.bitcoin.v1beta1.Tx.ConfirmOutpointResponse>(
                  this, METHODID_CONFIRM_OUTPOINT)))
          .addMethod(
            getVoteConfirmOutpointMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointRequest,
                axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointResponse>(
                  this, METHODID_VOTE_CONFIRM_OUTPOINT)))
          .addMethod(
            getCreatePendingTransfersTxMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxRequest,
                axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxResponse>(
                  this, METHODID_CREATE_PENDING_TRANSFERS_TX)))
          .addMethod(
            getCreateMasterTxMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.bitcoin.v1beta1.Tx.CreateMasterTxRequest,
                axelar.bitcoin.v1beta1.Tx.CreateMasterTxResponse>(
                  this, METHODID_CREATE_MASTER_TX)))
          .addMethod(
            getCreateRescueTxMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.bitcoin.v1beta1.Tx.CreateRescueTxRequest,
                axelar.bitcoin.v1beta1.Tx.CreateRescueTxResponse>(
                  this, METHODID_CREATE_RESCUE_TX)))
          .addMethod(
            getSignTxMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.bitcoin.v1beta1.Tx.SignTxRequest,
                axelar.bitcoin.v1beta1.Tx.SignTxResponse>(
                  this, METHODID_SIGN_TX)))
          .addMethod(
            getSubmitExternalSignatureMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureRequest,
                axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureResponse>(
                  this, METHODID_SUBMIT_EXTERNAL_SIGNATURE)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the bitcoin Msg service.
   * </pre>
   */
  public static final class MsgServiceStub extends io.grpc.stub.AbstractAsyncStub<MsgServiceStub> {
    private MsgServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceStub(channel, callOptions);
    }

    /**
     */
    public void link(axelar.bitcoin.v1beta1.Tx.LinkRequest request,
        io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.LinkResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLinkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirmOutpoint(axelar.bitcoin.v1beta1.Tx.ConfirmOutpointRequest request,
        io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.ConfirmOutpointResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConfirmOutpointMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void voteConfirmOutpoint(axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointRequest request,
        io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVoteConfirmOutpointMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createPendingTransfersTx(axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxRequest request,
        io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreatePendingTransfersTxMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createMasterTx(axelar.bitcoin.v1beta1.Tx.CreateMasterTxRequest request,
        io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.CreateMasterTxResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateMasterTxMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createRescueTx(axelar.bitcoin.v1beta1.Tx.CreateRescueTxRequest request,
        io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.CreateRescueTxResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateRescueTxMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void signTx(axelar.bitcoin.v1beta1.Tx.SignTxRequest request,
        io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.SignTxResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSignTxMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void submitExternalSignature(axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureRequest request,
        io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubmitExternalSignatureMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the bitcoin Msg service.
   * </pre>
   */
  public static final class MsgServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<MsgServiceBlockingStub> {
    private MsgServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public axelar.bitcoin.v1beta1.Tx.LinkResponse link(axelar.bitcoin.v1beta1.Tx.LinkRequest request) {
      return blockingUnaryCall(
          getChannel(), getLinkMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.bitcoin.v1beta1.Tx.ConfirmOutpointResponse confirmOutpoint(axelar.bitcoin.v1beta1.Tx.ConfirmOutpointRequest request) {
      return blockingUnaryCall(
          getChannel(), getConfirmOutpointMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointResponse voteConfirmOutpoint(axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointRequest request) {
      return blockingUnaryCall(
          getChannel(), getVoteConfirmOutpointMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxResponse createPendingTransfersTx(axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreatePendingTransfersTxMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.bitcoin.v1beta1.Tx.CreateMasterTxResponse createMasterTx(axelar.bitcoin.v1beta1.Tx.CreateMasterTxRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateMasterTxMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.bitcoin.v1beta1.Tx.CreateRescueTxResponse createRescueTx(axelar.bitcoin.v1beta1.Tx.CreateRescueTxRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateRescueTxMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.bitcoin.v1beta1.Tx.SignTxResponse signTx(axelar.bitcoin.v1beta1.Tx.SignTxRequest request) {
      return blockingUnaryCall(
          getChannel(), getSignTxMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureResponse submitExternalSignature(axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureRequest request) {
      return blockingUnaryCall(
          getChannel(), getSubmitExternalSignatureMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the bitcoin Msg service.
   * </pre>
   */
  public static final class MsgServiceFutureStub extends io.grpc.stub.AbstractFutureStub<MsgServiceFutureStub> {
    private MsgServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.bitcoin.v1beta1.Tx.LinkResponse> link(
        axelar.bitcoin.v1beta1.Tx.LinkRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLinkMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.bitcoin.v1beta1.Tx.ConfirmOutpointResponse> confirmOutpoint(
        axelar.bitcoin.v1beta1.Tx.ConfirmOutpointRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getConfirmOutpointMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointResponse> voteConfirmOutpoint(
        axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVoteConfirmOutpointMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxResponse> createPendingTransfersTx(
        axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreatePendingTransfersTxMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.bitcoin.v1beta1.Tx.CreateMasterTxResponse> createMasterTx(
        axelar.bitcoin.v1beta1.Tx.CreateMasterTxRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateMasterTxMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.bitcoin.v1beta1.Tx.CreateRescueTxResponse> createRescueTx(
        axelar.bitcoin.v1beta1.Tx.CreateRescueTxRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateRescueTxMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.bitcoin.v1beta1.Tx.SignTxResponse> signTx(
        axelar.bitcoin.v1beta1.Tx.SignTxRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSignTxMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureResponse> submitExternalSignature(
        axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSubmitExternalSignatureMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LINK = 0;
  private static final int METHODID_CONFIRM_OUTPOINT = 1;
  private static final int METHODID_VOTE_CONFIRM_OUTPOINT = 2;
  private static final int METHODID_CREATE_PENDING_TRANSFERS_TX = 3;
  private static final int METHODID_CREATE_MASTER_TX = 4;
  private static final int METHODID_CREATE_RESCUE_TX = 5;
  private static final int METHODID_SIGN_TX = 6;
  private static final int METHODID_SUBMIT_EXTERNAL_SIGNATURE = 7;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MsgServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MsgServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LINK:
          serviceImpl.link((axelar.bitcoin.v1beta1.Tx.LinkRequest) request,
              (io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.LinkResponse>) responseObserver);
          break;
        case METHODID_CONFIRM_OUTPOINT:
          serviceImpl.confirmOutpoint((axelar.bitcoin.v1beta1.Tx.ConfirmOutpointRequest) request,
              (io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.ConfirmOutpointResponse>) responseObserver);
          break;
        case METHODID_VOTE_CONFIRM_OUTPOINT:
          serviceImpl.voteConfirmOutpoint((axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointRequest) request,
              (io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.VoteConfirmOutpointResponse>) responseObserver);
          break;
        case METHODID_CREATE_PENDING_TRANSFERS_TX:
          serviceImpl.createPendingTransfersTx((axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxRequest) request,
              (io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.CreatePendingTransfersTxResponse>) responseObserver);
          break;
        case METHODID_CREATE_MASTER_TX:
          serviceImpl.createMasterTx((axelar.bitcoin.v1beta1.Tx.CreateMasterTxRequest) request,
              (io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.CreateMasterTxResponse>) responseObserver);
          break;
        case METHODID_CREATE_RESCUE_TX:
          serviceImpl.createRescueTx((axelar.bitcoin.v1beta1.Tx.CreateRescueTxRequest) request,
              (io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.CreateRescueTxResponse>) responseObserver);
          break;
        case METHODID_SIGN_TX:
          serviceImpl.signTx((axelar.bitcoin.v1beta1.Tx.SignTxRequest) request,
              (io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.SignTxResponse>) responseObserver);
          break;
        case METHODID_SUBMIT_EXTERNAL_SIGNATURE:
          serviceImpl.submitExternalSignature((axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureRequest) request,
              (io.grpc.stub.StreamObserver<axelar.bitcoin.v1beta1.Tx.SubmitExternalSignatureResponse>) responseObserver);
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

  private static abstract class MsgServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return axelar.bitcoin.v1beta1.Service.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MsgService");
    }
  }

  private static final class MsgServiceFileDescriptorSupplier
      extends MsgServiceBaseDescriptorSupplier {
    MsgServiceFileDescriptorSupplier() {}
  }

  private static final class MsgServiceMethodDescriptorSupplier
      extends MsgServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MsgServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (MsgServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MsgServiceFileDescriptorSupplier())
              .addMethod(getLinkMethod())
              .addMethod(getConfirmOutpointMethod())
              .addMethod(getVoteConfirmOutpointMethod())
              .addMethod(getCreatePendingTransfersTxMethod())
              .addMethod(getCreateMasterTxMethod())
              .addMethod(getCreateRescueTxMethod())
              .addMethod(getSignTxMethod())
              .addMethod(getSubmitExternalSignatureMethod())
              .build();
        }
      }
    }
    return result;
  }
}
