package axelar.tss.v1beta1;

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
 * Msg defines the tss Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: axelar/tss/v1beta1/service.proto")
public final class MsgServiceGrpc {

  private MsgServiceGrpc() {}

  public static final String SERVICE_NAME = "axelar.tss.v1beta1.MsgService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.RegisterExternalKeysRequest,
      axelar.tss.v1beta1.Tx.RegisterExternalKeysResponse> getRegisterExternalKeysMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterExternalKeys",
      requestType = axelar.tss.v1beta1.Tx.RegisterExternalKeysRequest.class,
      responseType = axelar.tss.v1beta1.Tx.RegisterExternalKeysResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.RegisterExternalKeysRequest,
      axelar.tss.v1beta1.Tx.RegisterExternalKeysResponse> getRegisterExternalKeysMethod() {
    io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.RegisterExternalKeysRequest, axelar.tss.v1beta1.Tx.RegisterExternalKeysResponse> getRegisterExternalKeysMethod;
    if ((getRegisterExternalKeysMethod = MsgServiceGrpc.getRegisterExternalKeysMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getRegisterExternalKeysMethod = MsgServiceGrpc.getRegisterExternalKeysMethod) == null) {
          MsgServiceGrpc.getRegisterExternalKeysMethod = getRegisterExternalKeysMethod =
              io.grpc.MethodDescriptor.<axelar.tss.v1beta1.Tx.RegisterExternalKeysRequest, axelar.tss.v1beta1.Tx.RegisterExternalKeysResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterExternalKeys"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.RegisterExternalKeysRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.RegisterExternalKeysResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("RegisterExternalKeys"))
              .build();
        }
      }
    }
    return getRegisterExternalKeysMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.HeartBeatRequest,
      axelar.tss.v1beta1.Tx.HeartBeatResponse> getHeartBeatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HeartBeat",
      requestType = axelar.tss.v1beta1.Tx.HeartBeatRequest.class,
      responseType = axelar.tss.v1beta1.Tx.HeartBeatResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.HeartBeatRequest,
      axelar.tss.v1beta1.Tx.HeartBeatResponse> getHeartBeatMethod() {
    io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.HeartBeatRequest, axelar.tss.v1beta1.Tx.HeartBeatResponse> getHeartBeatMethod;
    if ((getHeartBeatMethod = MsgServiceGrpc.getHeartBeatMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getHeartBeatMethod = MsgServiceGrpc.getHeartBeatMethod) == null) {
          MsgServiceGrpc.getHeartBeatMethod = getHeartBeatMethod =
              io.grpc.MethodDescriptor.<axelar.tss.v1beta1.Tx.HeartBeatRequest, axelar.tss.v1beta1.Tx.HeartBeatResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HeartBeat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.HeartBeatRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.HeartBeatResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("HeartBeat"))
              .build();
        }
      }
    }
    return getHeartBeatMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.StartKeygenRequest,
      axelar.tss.v1beta1.Tx.StartKeygenResponse> getStartKeygenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StartKeygen",
      requestType = axelar.tss.v1beta1.Tx.StartKeygenRequest.class,
      responseType = axelar.tss.v1beta1.Tx.StartKeygenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.StartKeygenRequest,
      axelar.tss.v1beta1.Tx.StartKeygenResponse> getStartKeygenMethod() {
    io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.StartKeygenRequest, axelar.tss.v1beta1.Tx.StartKeygenResponse> getStartKeygenMethod;
    if ((getStartKeygenMethod = MsgServiceGrpc.getStartKeygenMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getStartKeygenMethod = MsgServiceGrpc.getStartKeygenMethod) == null) {
          MsgServiceGrpc.getStartKeygenMethod = getStartKeygenMethod =
              io.grpc.MethodDescriptor.<axelar.tss.v1beta1.Tx.StartKeygenRequest, axelar.tss.v1beta1.Tx.StartKeygenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StartKeygen"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.StartKeygenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.StartKeygenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("StartKeygen"))
              .build();
        }
      }
    }
    return getStartKeygenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.ProcessKeygenTrafficRequest,
      axelar.tss.v1beta1.Tx.ProcessKeygenTrafficResponse> getProcessKeygenTrafficMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProcessKeygenTraffic",
      requestType = axelar.tss.v1beta1.Tx.ProcessKeygenTrafficRequest.class,
      responseType = axelar.tss.v1beta1.Tx.ProcessKeygenTrafficResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.ProcessKeygenTrafficRequest,
      axelar.tss.v1beta1.Tx.ProcessKeygenTrafficResponse> getProcessKeygenTrafficMethod() {
    io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.ProcessKeygenTrafficRequest, axelar.tss.v1beta1.Tx.ProcessKeygenTrafficResponse> getProcessKeygenTrafficMethod;
    if ((getProcessKeygenTrafficMethod = MsgServiceGrpc.getProcessKeygenTrafficMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getProcessKeygenTrafficMethod = MsgServiceGrpc.getProcessKeygenTrafficMethod) == null) {
          MsgServiceGrpc.getProcessKeygenTrafficMethod = getProcessKeygenTrafficMethod =
              io.grpc.MethodDescriptor.<axelar.tss.v1beta1.Tx.ProcessKeygenTrafficRequest, axelar.tss.v1beta1.Tx.ProcessKeygenTrafficResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ProcessKeygenTraffic"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.ProcessKeygenTrafficRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.ProcessKeygenTrafficResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("ProcessKeygenTraffic"))
              .build();
        }
      }
    }
    return getProcessKeygenTrafficMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.RotateKeyRequest,
      axelar.tss.v1beta1.Tx.RotateKeyResponse> getRotateKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RotateKey",
      requestType = axelar.tss.v1beta1.Tx.RotateKeyRequest.class,
      responseType = axelar.tss.v1beta1.Tx.RotateKeyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.RotateKeyRequest,
      axelar.tss.v1beta1.Tx.RotateKeyResponse> getRotateKeyMethod() {
    io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.RotateKeyRequest, axelar.tss.v1beta1.Tx.RotateKeyResponse> getRotateKeyMethod;
    if ((getRotateKeyMethod = MsgServiceGrpc.getRotateKeyMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getRotateKeyMethod = MsgServiceGrpc.getRotateKeyMethod) == null) {
          MsgServiceGrpc.getRotateKeyMethod = getRotateKeyMethod =
              io.grpc.MethodDescriptor.<axelar.tss.v1beta1.Tx.RotateKeyRequest, axelar.tss.v1beta1.Tx.RotateKeyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RotateKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.RotateKeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.RotateKeyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("RotateKey"))
              .build();
        }
      }
    }
    return getRotateKeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.VotePubKeyRequest,
      axelar.tss.v1beta1.Tx.VotePubKeyResponse> getVotePubKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "VotePubKey",
      requestType = axelar.tss.v1beta1.Tx.VotePubKeyRequest.class,
      responseType = axelar.tss.v1beta1.Tx.VotePubKeyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.VotePubKeyRequest,
      axelar.tss.v1beta1.Tx.VotePubKeyResponse> getVotePubKeyMethod() {
    io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.VotePubKeyRequest, axelar.tss.v1beta1.Tx.VotePubKeyResponse> getVotePubKeyMethod;
    if ((getVotePubKeyMethod = MsgServiceGrpc.getVotePubKeyMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getVotePubKeyMethod = MsgServiceGrpc.getVotePubKeyMethod) == null) {
          MsgServiceGrpc.getVotePubKeyMethod = getVotePubKeyMethod =
              io.grpc.MethodDescriptor.<axelar.tss.v1beta1.Tx.VotePubKeyRequest, axelar.tss.v1beta1.Tx.VotePubKeyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "VotePubKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.VotePubKeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.VotePubKeyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("VotePubKey"))
              .build();
        }
      }
    }
    return getVotePubKeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.ProcessSignTrafficRequest,
      axelar.tss.v1beta1.Tx.ProcessSignTrafficResponse> getProcessSignTrafficMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProcessSignTraffic",
      requestType = axelar.tss.v1beta1.Tx.ProcessSignTrafficRequest.class,
      responseType = axelar.tss.v1beta1.Tx.ProcessSignTrafficResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.ProcessSignTrafficRequest,
      axelar.tss.v1beta1.Tx.ProcessSignTrafficResponse> getProcessSignTrafficMethod() {
    io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.ProcessSignTrafficRequest, axelar.tss.v1beta1.Tx.ProcessSignTrafficResponse> getProcessSignTrafficMethod;
    if ((getProcessSignTrafficMethod = MsgServiceGrpc.getProcessSignTrafficMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getProcessSignTrafficMethod = MsgServiceGrpc.getProcessSignTrafficMethod) == null) {
          MsgServiceGrpc.getProcessSignTrafficMethod = getProcessSignTrafficMethod =
              io.grpc.MethodDescriptor.<axelar.tss.v1beta1.Tx.ProcessSignTrafficRequest, axelar.tss.v1beta1.Tx.ProcessSignTrafficResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ProcessSignTraffic"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.ProcessSignTrafficRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.ProcessSignTrafficResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("ProcessSignTraffic"))
              .build();
        }
      }
    }
    return getProcessSignTrafficMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.VoteSigRequest,
      axelar.tss.v1beta1.Tx.VoteSigResponse> getVoteSigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "VoteSig",
      requestType = axelar.tss.v1beta1.Tx.VoteSigRequest.class,
      responseType = axelar.tss.v1beta1.Tx.VoteSigResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.VoteSigRequest,
      axelar.tss.v1beta1.Tx.VoteSigResponse> getVoteSigMethod() {
    io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.VoteSigRequest, axelar.tss.v1beta1.Tx.VoteSigResponse> getVoteSigMethod;
    if ((getVoteSigMethod = MsgServiceGrpc.getVoteSigMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getVoteSigMethod = MsgServiceGrpc.getVoteSigMethod) == null) {
          MsgServiceGrpc.getVoteSigMethod = getVoteSigMethod =
              io.grpc.MethodDescriptor.<axelar.tss.v1beta1.Tx.VoteSigRequest, axelar.tss.v1beta1.Tx.VoteSigResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "VoteSig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.VoteSigRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.VoteSigResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("VoteSig"))
              .build();
        }
      }
    }
    return getVoteSigMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysRequest,
      axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysResponse> getSubmitMultisigPubKeysMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitMultisigPubKeys",
      requestType = axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysRequest.class,
      responseType = axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysRequest,
      axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysResponse> getSubmitMultisigPubKeysMethod() {
    io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysRequest, axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysResponse> getSubmitMultisigPubKeysMethod;
    if ((getSubmitMultisigPubKeysMethod = MsgServiceGrpc.getSubmitMultisigPubKeysMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getSubmitMultisigPubKeysMethod = MsgServiceGrpc.getSubmitMultisigPubKeysMethod) == null) {
          MsgServiceGrpc.getSubmitMultisigPubKeysMethod = getSubmitMultisigPubKeysMethod =
              io.grpc.MethodDescriptor.<axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysRequest, axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubmitMultisigPubKeys"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("SubmitMultisigPubKeys"))
              .build();
        }
      }
    }
    return getSubmitMultisigPubKeysMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesRequest,
      axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesResponse> getSubmitMultisigSignaturesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitMultisigSignatures",
      requestType = axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesRequest.class,
      responseType = axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesRequest,
      axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesResponse> getSubmitMultisigSignaturesMethod() {
    io.grpc.MethodDescriptor<axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesRequest, axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesResponse> getSubmitMultisigSignaturesMethod;
    if ((getSubmitMultisigSignaturesMethod = MsgServiceGrpc.getSubmitMultisigSignaturesMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getSubmitMultisigSignaturesMethod = MsgServiceGrpc.getSubmitMultisigSignaturesMethod) == null) {
          MsgServiceGrpc.getSubmitMultisigSignaturesMethod = getSubmitMultisigSignaturesMethod =
              io.grpc.MethodDescriptor.<axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesRequest, axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubmitMultisigSignatures"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("SubmitMultisigSignatures"))
              .build();
        }
      }
    }
    return getSubmitMultisigSignaturesMethod;
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
   * Msg defines the tss Msg service.
   * </pre>
   */
  public static abstract class MsgServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void registerExternalKeys(axelar.tss.v1beta1.Tx.RegisterExternalKeysRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.RegisterExternalKeysResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterExternalKeysMethod(), responseObserver);
    }

    /**
     */
    public void heartBeat(axelar.tss.v1beta1.Tx.HeartBeatRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.HeartBeatResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHeartBeatMethod(), responseObserver);
    }

    /**
     */
    public void startKeygen(axelar.tss.v1beta1.Tx.StartKeygenRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.StartKeygenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStartKeygenMethod(), responseObserver);
    }

    /**
     */
    public void processKeygenTraffic(axelar.tss.v1beta1.Tx.ProcessKeygenTrafficRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.ProcessKeygenTrafficResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProcessKeygenTrafficMethod(), responseObserver);
    }

    /**
     */
    public void rotateKey(axelar.tss.v1beta1.Tx.RotateKeyRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.RotateKeyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRotateKeyMethod(), responseObserver);
    }

    /**
     */
    public void votePubKey(axelar.tss.v1beta1.Tx.VotePubKeyRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.VotePubKeyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVotePubKeyMethod(), responseObserver);
    }

    /**
     */
    public void processSignTraffic(axelar.tss.v1beta1.Tx.ProcessSignTrafficRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.ProcessSignTrafficResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProcessSignTrafficMethod(), responseObserver);
    }

    /**
     */
    public void voteSig(axelar.tss.v1beta1.Tx.VoteSigRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.VoteSigResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVoteSigMethod(), responseObserver);
    }

    /**
     */
    public void submitMultisigPubKeys(axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubmitMultisigPubKeysMethod(), responseObserver);
    }

    /**
     */
    public void submitMultisigSignatures(axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubmitMultisigSignaturesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterExternalKeysMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.tss.v1beta1.Tx.RegisterExternalKeysRequest,
                axelar.tss.v1beta1.Tx.RegisterExternalKeysResponse>(
                  this, METHODID_REGISTER_EXTERNAL_KEYS)))
          .addMethod(
            getHeartBeatMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.tss.v1beta1.Tx.HeartBeatRequest,
                axelar.tss.v1beta1.Tx.HeartBeatResponse>(
                  this, METHODID_HEART_BEAT)))
          .addMethod(
            getStartKeygenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.tss.v1beta1.Tx.StartKeygenRequest,
                axelar.tss.v1beta1.Tx.StartKeygenResponse>(
                  this, METHODID_START_KEYGEN)))
          .addMethod(
            getProcessKeygenTrafficMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.tss.v1beta1.Tx.ProcessKeygenTrafficRequest,
                axelar.tss.v1beta1.Tx.ProcessKeygenTrafficResponse>(
                  this, METHODID_PROCESS_KEYGEN_TRAFFIC)))
          .addMethod(
            getRotateKeyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.tss.v1beta1.Tx.RotateKeyRequest,
                axelar.tss.v1beta1.Tx.RotateKeyResponse>(
                  this, METHODID_ROTATE_KEY)))
          .addMethod(
            getVotePubKeyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.tss.v1beta1.Tx.VotePubKeyRequest,
                axelar.tss.v1beta1.Tx.VotePubKeyResponse>(
                  this, METHODID_VOTE_PUB_KEY)))
          .addMethod(
            getProcessSignTrafficMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.tss.v1beta1.Tx.ProcessSignTrafficRequest,
                axelar.tss.v1beta1.Tx.ProcessSignTrafficResponse>(
                  this, METHODID_PROCESS_SIGN_TRAFFIC)))
          .addMethod(
            getVoteSigMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.tss.v1beta1.Tx.VoteSigRequest,
                axelar.tss.v1beta1.Tx.VoteSigResponse>(
                  this, METHODID_VOTE_SIG)))
          .addMethod(
            getSubmitMultisigPubKeysMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysRequest,
                axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysResponse>(
                  this, METHODID_SUBMIT_MULTISIG_PUB_KEYS)))
          .addMethod(
            getSubmitMultisigSignaturesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesRequest,
                axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesResponse>(
                  this, METHODID_SUBMIT_MULTISIG_SIGNATURES)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the tss Msg service.
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
    public void registerExternalKeys(axelar.tss.v1beta1.Tx.RegisterExternalKeysRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.RegisterExternalKeysResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterExternalKeysMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void heartBeat(axelar.tss.v1beta1.Tx.HeartBeatRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.HeartBeatResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHeartBeatMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void startKeygen(axelar.tss.v1beta1.Tx.StartKeygenRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.StartKeygenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStartKeygenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void processKeygenTraffic(axelar.tss.v1beta1.Tx.ProcessKeygenTrafficRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.ProcessKeygenTrafficResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProcessKeygenTrafficMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rotateKey(axelar.tss.v1beta1.Tx.RotateKeyRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.RotateKeyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRotateKeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void votePubKey(axelar.tss.v1beta1.Tx.VotePubKeyRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.VotePubKeyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVotePubKeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void processSignTraffic(axelar.tss.v1beta1.Tx.ProcessSignTrafficRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.ProcessSignTrafficResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProcessSignTrafficMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void voteSig(axelar.tss.v1beta1.Tx.VoteSigRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.VoteSigResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVoteSigMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void submitMultisigPubKeys(axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubmitMultisigPubKeysMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void submitMultisigSignatures(axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesRequest request,
        io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubmitMultisigSignaturesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the tss Msg service.
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
    public axelar.tss.v1beta1.Tx.RegisterExternalKeysResponse registerExternalKeys(axelar.tss.v1beta1.Tx.RegisterExternalKeysRequest request) {
      return blockingUnaryCall(
          getChannel(), getRegisterExternalKeysMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.tss.v1beta1.Tx.HeartBeatResponse heartBeat(axelar.tss.v1beta1.Tx.HeartBeatRequest request) {
      return blockingUnaryCall(
          getChannel(), getHeartBeatMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.tss.v1beta1.Tx.StartKeygenResponse startKeygen(axelar.tss.v1beta1.Tx.StartKeygenRequest request) {
      return blockingUnaryCall(
          getChannel(), getStartKeygenMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.tss.v1beta1.Tx.ProcessKeygenTrafficResponse processKeygenTraffic(axelar.tss.v1beta1.Tx.ProcessKeygenTrafficRequest request) {
      return blockingUnaryCall(
          getChannel(), getProcessKeygenTrafficMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.tss.v1beta1.Tx.RotateKeyResponse rotateKey(axelar.tss.v1beta1.Tx.RotateKeyRequest request) {
      return blockingUnaryCall(
          getChannel(), getRotateKeyMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.tss.v1beta1.Tx.VotePubKeyResponse votePubKey(axelar.tss.v1beta1.Tx.VotePubKeyRequest request) {
      return blockingUnaryCall(
          getChannel(), getVotePubKeyMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.tss.v1beta1.Tx.ProcessSignTrafficResponse processSignTraffic(axelar.tss.v1beta1.Tx.ProcessSignTrafficRequest request) {
      return blockingUnaryCall(
          getChannel(), getProcessSignTrafficMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.tss.v1beta1.Tx.VoteSigResponse voteSig(axelar.tss.v1beta1.Tx.VoteSigRequest request) {
      return blockingUnaryCall(
          getChannel(), getVoteSigMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysResponse submitMultisigPubKeys(axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysRequest request) {
      return blockingUnaryCall(
          getChannel(), getSubmitMultisigPubKeysMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesResponse submitMultisigSignatures(axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesRequest request) {
      return blockingUnaryCall(
          getChannel(), getSubmitMultisigSignaturesMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the tss Msg service.
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
    public com.google.common.util.concurrent.ListenableFuture<axelar.tss.v1beta1.Tx.RegisterExternalKeysResponse> registerExternalKeys(
        axelar.tss.v1beta1.Tx.RegisterExternalKeysRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterExternalKeysMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.tss.v1beta1.Tx.HeartBeatResponse> heartBeat(
        axelar.tss.v1beta1.Tx.HeartBeatRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHeartBeatMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.tss.v1beta1.Tx.StartKeygenResponse> startKeygen(
        axelar.tss.v1beta1.Tx.StartKeygenRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStartKeygenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.tss.v1beta1.Tx.ProcessKeygenTrafficResponse> processKeygenTraffic(
        axelar.tss.v1beta1.Tx.ProcessKeygenTrafficRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProcessKeygenTrafficMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.tss.v1beta1.Tx.RotateKeyResponse> rotateKey(
        axelar.tss.v1beta1.Tx.RotateKeyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRotateKeyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.tss.v1beta1.Tx.VotePubKeyResponse> votePubKey(
        axelar.tss.v1beta1.Tx.VotePubKeyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVotePubKeyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.tss.v1beta1.Tx.ProcessSignTrafficResponse> processSignTraffic(
        axelar.tss.v1beta1.Tx.ProcessSignTrafficRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProcessSignTrafficMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.tss.v1beta1.Tx.VoteSigResponse> voteSig(
        axelar.tss.v1beta1.Tx.VoteSigRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVoteSigMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysResponse> submitMultisigPubKeys(
        axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSubmitMultisigPubKeysMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesResponse> submitMultisigSignatures(
        axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSubmitMultisigSignaturesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_EXTERNAL_KEYS = 0;
  private static final int METHODID_HEART_BEAT = 1;
  private static final int METHODID_START_KEYGEN = 2;
  private static final int METHODID_PROCESS_KEYGEN_TRAFFIC = 3;
  private static final int METHODID_ROTATE_KEY = 4;
  private static final int METHODID_VOTE_PUB_KEY = 5;
  private static final int METHODID_PROCESS_SIGN_TRAFFIC = 6;
  private static final int METHODID_VOTE_SIG = 7;
  private static final int METHODID_SUBMIT_MULTISIG_PUB_KEYS = 8;
  private static final int METHODID_SUBMIT_MULTISIG_SIGNATURES = 9;

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
        case METHODID_REGISTER_EXTERNAL_KEYS:
          serviceImpl.registerExternalKeys((axelar.tss.v1beta1.Tx.RegisterExternalKeysRequest) request,
              (io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.RegisterExternalKeysResponse>) responseObserver);
          break;
        case METHODID_HEART_BEAT:
          serviceImpl.heartBeat((axelar.tss.v1beta1.Tx.HeartBeatRequest) request,
              (io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.HeartBeatResponse>) responseObserver);
          break;
        case METHODID_START_KEYGEN:
          serviceImpl.startKeygen((axelar.tss.v1beta1.Tx.StartKeygenRequest) request,
              (io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.StartKeygenResponse>) responseObserver);
          break;
        case METHODID_PROCESS_KEYGEN_TRAFFIC:
          serviceImpl.processKeygenTraffic((axelar.tss.v1beta1.Tx.ProcessKeygenTrafficRequest) request,
              (io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.ProcessKeygenTrafficResponse>) responseObserver);
          break;
        case METHODID_ROTATE_KEY:
          serviceImpl.rotateKey((axelar.tss.v1beta1.Tx.RotateKeyRequest) request,
              (io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.RotateKeyResponse>) responseObserver);
          break;
        case METHODID_VOTE_PUB_KEY:
          serviceImpl.votePubKey((axelar.tss.v1beta1.Tx.VotePubKeyRequest) request,
              (io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.VotePubKeyResponse>) responseObserver);
          break;
        case METHODID_PROCESS_SIGN_TRAFFIC:
          serviceImpl.processSignTraffic((axelar.tss.v1beta1.Tx.ProcessSignTrafficRequest) request,
              (io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.ProcessSignTrafficResponse>) responseObserver);
          break;
        case METHODID_VOTE_SIG:
          serviceImpl.voteSig((axelar.tss.v1beta1.Tx.VoteSigRequest) request,
              (io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.VoteSigResponse>) responseObserver);
          break;
        case METHODID_SUBMIT_MULTISIG_PUB_KEYS:
          serviceImpl.submitMultisigPubKeys((axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysRequest) request,
              (io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.SubmitMultisigPubKeysResponse>) responseObserver);
          break;
        case METHODID_SUBMIT_MULTISIG_SIGNATURES:
          serviceImpl.submitMultisigSignatures((axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesRequest) request,
              (io.grpc.stub.StreamObserver<axelar.tss.v1beta1.Tx.SubmitMultisigSignaturesResponse>) responseObserver);
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
      return axelar.tss.v1beta1.Service.getDescriptor();
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
              .addMethod(getRegisterExternalKeysMethod())
              .addMethod(getHeartBeatMethod())
              .addMethod(getStartKeygenMethod())
              .addMethod(getProcessKeygenTrafficMethod())
              .addMethod(getRotateKeyMethod())
              .addMethod(getVotePubKeyMethod())
              .addMethod(getProcessSignTrafficMethod())
              .addMethod(getVoteSigMethod())
              .addMethod(getSubmitMultisigPubKeysMethod())
              .addMethod(getSubmitMultisigSignaturesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
