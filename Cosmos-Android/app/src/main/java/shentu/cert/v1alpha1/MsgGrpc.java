package shentu.cert.v1alpha1;

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
 * Msg defines the shield Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: shentu/cert/v1alpha1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "shentu.cert.v1alpha1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<shentu.cert.v1alpha1.Tx.MsgProposeCertifier,
      shentu.cert.v1alpha1.Tx.MsgProposeCertifierResponse> getProposeCertifierMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProposeCertifier",
      requestType = shentu.cert.v1alpha1.Tx.MsgProposeCertifier.class,
      responseType = shentu.cert.v1alpha1.Tx.MsgProposeCertifierResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cert.v1alpha1.Tx.MsgProposeCertifier,
      shentu.cert.v1alpha1.Tx.MsgProposeCertifierResponse> getProposeCertifierMethod() {
    io.grpc.MethodDescriptor<shentu.cert.v1alpha1.Tx.MsgProposeCertifier, shentu.cert.v1alpha1.Tx.MsgProposeCertifierResponse> getProposeCertifierMethod;
    if ((getProposeCertifierMethod = MsgGrpc.getProposeCertifierMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getProposeCertifierMethod = MsgGrpc.getProposeCertifierMethod) == null) {
          MsgGrpc.getProposeCertifierMethod = getProposeCertifierMethod =
              io.grpc.MethodDescriptor.<shentu.cert.v1alpha1.Tx.MsgProposeCertifier, shentu.cert.v1alpha1.Tx.MsgProposeCertifierResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ProposeCertifier"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.Tx.MsgProposeCertifier.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.Tx.MsgProposeCertifierResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ProposeCertifier"))
              .build();
        }
      }
    }
    return getProposeCertifierMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.cert.v1alpha1.Tx.MsgIssueCertificate,
      shentu.cert.v1alpha1.Tx.MsgIssueCertificateResponse> getIssueCertificateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IssueCertificate",
      requestType = shentu.cert.v1alpha1.Tx.MsgIssueCertificate.class,
      responseType = shentu.cert.v1alpha1.Tx.MsgIssueCertificateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cert.v1alpha1.Tx.MsgIssueCertificate,
      shentu.cert.v1alpha1.Tx.MsgIssueCertificateResponse> getIssueCertificateMethod() {
    io.grpc.MethodDescriptor<shentu.cert.v1alpha1.Tx.MsgIssueCertificate, shentu.cert.v1alpha1.Tx.MsgIssueCertificateResponse> getIssueCertificateMethod;
    if ((getIssueCertificateMethod = MsgGrpc.getIssueCertificateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getIssueCertificateMethod = MsgGrpc.getIssueCertificateMethod) == null) {
          MsgGrpc.getIssueCertificateMethod = getIssueCertificateMethod =
              io.grpc.MethodDescriptor.<shentu.cert.v1alpha1.Tx.MsgIssueCertificate, shentu.cert.v1alpha1.Tx.MsgIssueCertificateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IssueCertificate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.Tx.MsgIssueCertificate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.Tx.MsgIssueCertificateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("IssueCertificate"))
              .build();
        }
      }
    }
    return getIssueCertificateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.cert.v1alpha1.Tx.MsgRevokeCertificate,
      shentu.cert.v1alpha1.Tx.MsgRevokeCertificateResponse> getRevokeCertificateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RevokeCertificate",
      requestType = shentu.cert.v1alpha1.Tx.MsgRevokeCertificate.class,
      responseType = shentu.cert.v1alpha1.Tx.MsgRevokeCertificateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cert.v1alpha1.Tx.MsgRevokeCertificate,
      shentu.cert.v1alpha1.Tx.MsgRevokeCertificateResponse> getRevokeCertificateMethod() {
    io.grpc.MethodDescriptor<shentu.cert.v1alpha1.Tx.MsgRevokeCertificate, shentu.cert.v1alpha1.Tx.MsgRevokeCertificateResponse> getRevokeCertificateMethod;
    if ((getRevokeCertificateMethod = MsgGrpc.getRevokeCertificateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRevokeCertificateMethod = MsgGrpc.getRevokeCertificateMethod) == null) {
          MsgGrpc.getRevokeCertificateMethod = getRevokeCertificateMethod =
              io.grpc.MethodDescriptor.<shentu.cert.v1alpha1.Tx.MsgRevokeCertificate, shentu.cert.v1alpha1.Tx.MsgRevokeCertificateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RevokeCertificate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.Tx.MsgRevokeCertificate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.Tx.MsgRevokeCertificateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RevokeCertificate"))
              .build();
        }
      }
    }
    return getRevokeCertificateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.cert.v1alpha1.Tx.MsgCertifyPlatform,
      shentu.cert.v1alpha1.Tx.MsgCertifyPlatformResponse> getCertifyPlatformMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CertifyPlatform",
      requestType = shentu.cert.v1alpha1.Tx.MsgCertifyPlatform.class,
      responseType = shentu.cert.v1alpha1.Tx.MsgCertifyPlatformResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cert.v1alpha1.Tx.MsgCertifyPlatform,
      shentu.cert.v1alpha1.Tx.MsgCertifyPlatformResponse> getCertifyPlatformMethod() {
    io.grpc.MethodDescriptor<shentu.cert.v1alpha1.Tx.MsgCertifyPlatform, shentu.cert.v1alpha1.Tx.MsgCertifyPlatformResponse> getCertifyPlatformMethod;
    if ((getCertifyPlatformMethod = MsgGrpc.getCertifyPlatformMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCertifyPlatformMethod = MsgGrpc.getCertifyPlatformMethod) == null) {
          MsgGrpc.getCertifyPlatformMethod = getCertifyPlatformMethod =
              io.grpc.MethodDescriptor.<shentu.cert.v1alpha1.Tx.MsgCertifyPlatform, shentu.cert.v1alpha1.Tx.MsgCertifyPlatformResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CertifyPlatform"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.Tx.MsgCertifyPlatform.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.Tx.MsgCertifyPlatformResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CertifyPlatform"))
              .build();
        }
      }
    }
    return getCertifyPlatformMethod;
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
   * Msg defines the shield Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     */
    public void proposeCertifier(shentu.cert.v1alpha1.Tx.MsgProposeCertifier request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.Tx.MsgProposeCertifierResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProposeCertifierMethod(), responseObserver);
    }

    /**
     */
    public void issueCertificate(shentu.cert.v1alpha1.Tx.MsgIssueCertificate request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.Tx.MsgIssueCertificateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getIssueCertificateMethod(), responseObserver);
    }

    /**
     */
    public void revokeCertificate(shentu.cert.v1alpha1.Tx.MsgRevokeCertificate request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.Tx.MsgRevokeCertificateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRevokeCertificateMethod(), responseObserver);
    }

    /**
     */
    public void certifyPlatform(shentu.cert.v1alpha1.Tx.MsgCertifyPlatform request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.Tx.MsgCertifyPlatformResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCertifyPlatformMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getProposeCertifierMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cert.v1alpha1.Tx.MsgProposeCertifier,
                shentu.cert.v1alpha1.Tx.MsgProposeCertifierResponse>(
                  this, METHODID_PROPOSE_CERTIFIER)))
          .addMethod(
            getIssueCertificateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cert.v1alpha1.Tx.MsgIssueCertificate,
                shentu.cert.v1alpha1.Tx.MsgIssueCertificateResponse>(
                  this, METHODID_ISSUE_CERTIFICATE)))
          .addMethod(
            getRevokeCertificateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cert.v1alpha1.Tx.MsgRevokeCertificate,
                shentu.cert.v1alpha1.Tx.MsgRevokeCertificateResponse>(
                  this, METHODID_REVOKE_CERTIFICATE)))
          .addMethod(
            getCertifyPlatformMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cert.v1alpha1.Tx.MsgCertifyPlatform,
                shentu.cert.v1alpha1.Tx.MsgCertifyPlatformResponse>(
                  this, METHODID_CERTIFY_PLATFORM)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the shield Msg service.
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
     */
    public void proposeCertifier(shentu.cert.v1alpha1.Tx.MsgProposeCertifier request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.Tx.MsgProposeCertifierResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProposeCertifierMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void issueCertificate(shentu.cert.v1alpha1.Tx.MsgIssueCertificate request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.Tx.MsgIssueCertificateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIssueCertificateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void revokeCertificate(shentu.cert.v1alpha1.Tx.MsgRevokeCertificate request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.Tx.MsgRevokeCertificateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRevokeCertificateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void certifyPlatform(shentu.cert.v1alpha1.Tx.MsgCertifyPlatform request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.Tx.MsgCertifyPlatformResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCertifyPlatformMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the shield Msg service.
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
     */
    public shentu.cert.v1alpha1.Tx.MsgProposeCertifierResponse proposeCertifier(shentu.cert.v1alpha1.Tx.MsgProposeCertifier request) {
      return blockingUnaryCall(
          getChannel(), getProposeCertifierMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.cert.v1alpha1.Tx.MsgIssueCertificateResponse issueCertificate(shentu.cert.v1alpha1.Tx.MsgIssueCertificate request) {
      return blockingUnaryCall(
          getChannel(), getIssueCertificateMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.cert.v1alpha1.Tx.MsgRevokeCertificateResponse revokeCertificate(shentu.cert.v1alpha1.Tx.MsgRevokeCertificate request) {
      return blockingUnaryCall(
          getChannel(), getRevokeCertificateMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.cert.v1alpha1.Tx.MsgCertifyPlatformResponse certifyPlatform(shentu.cert.v1alpha1.Tx.MsgCertifyPlatform request) {
      return blockingUnaryCall(
          getChannel(), getCertifyPlatformMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the shield Msg service.
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
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.cert.v1alpha1.Tx.MsgProposeCertifierResponse> proposeCertifier(
        shentu.cert.v1alpha1.Tx.MsgProposeCertifier request) {
      return futureUnaryCall(
          getChannel().newCall(getProposeCertifierMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.cert.v1alpha1.Tx.MsgIssueCertificateResponse> issueCertificate(
        shentu.cert.v1alpha1.Tx.MsgIssueCertificate request) {
      return futureUnaryCall(
          getChannel().newCall(getIssueCertificateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.cert.v1alpha1.Tx.MsgRevokeCertificateResponse> revokeCertificate(
        shentu.cert.v1alpha1.Tx.MsgRevokeCertificate request) {
      return futureUnaryCall(
          getChannel().newCall(getRevokeCertificateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.cert.v1alpha1.Tx.MsgCertifyPlatformResponse> certifyPlatform(
        shentu.cert.v1alpha1.Tx.MsgCertifyPlatform request) {
      return futureUnaryCall(
          getChannel().newCall(getCertifyPlatformMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PROPOSE_CERTIFIER = 0;
  private static final int METHODID_ISSUE_CERTIFICATE = 1;
  private static final int METHODID_REVOKE_CERTIFICATE = 2;
  private static final int METHODID_CERTIFY_PLATFORM = 3;

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
        case METHODID_PROPOSE_CERTIFIER:
          serviceImpl.proposeCertifier((shentu.cert.v1alpha1.Tx.MsgProposeCertifier) request,
              (io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.Tx.MsgProposeCertifierResponse>) responseObserver);
          break;
        case METHODID_ISSUE_CERTIFICATE:
          serviceImpl.issueCertificate((shentu.cert.v1alpha1.Tx.MsgIssueCertificate) request,
              (io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.Tx.MsgIssueCertificateResponse>) responseObserver);
          break;
        case METHODID_REVOKE_CERTIFICATE:
          serviceImpl.revokeCertificate((shentu.cert.v1alpha1.Tx.MsgRevokeCertificate) request,
              (io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.Tx.MsgRevokeCertificateResponse>) responseObserver);
          break;
        case METHODID_CERTIFY_PLATFORM:
          serviceImpl.certifyPlatform((shentu.cert.v1alpha1.Tx.MsgCertifyPlatform) request,
              (io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.Tx.MsgCertifyPlatformResponse>) responseObserver);
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
      return shentu.cert.v1alpha1.Tx.getDescriptor();
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
              .addMethod(getProposeCertifierMethod())
              .addMethod(getIssueCertificateMethod())
              .addMethod(getRevokeCertificateMethod())
              .addMethod(getCertifyPlatformMethod())
              .build();
        }
      }
    }
    return result;
  }
}
