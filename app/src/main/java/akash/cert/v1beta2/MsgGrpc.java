package akash.cert.v1beta2;

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
 * Msg defines the provider Msg service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: akash/cert/v1beta2/cert.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "akash.cert.v1beta2.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<akash.cert.v1beta2.Cert.MsgCreateCertificate,
      akash.cert.v1beta2.Cert.MsgCreateCertificateResponse> getCreateCertificateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateCertificate",
      requestType = akash.cert.v1beta2.Cert.MsgCreateCertificate.class,
      responseType = akash.cert.v1beta2.Cert.MsgCreateCertificateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.cert.v1beta2.Cert.MsgCreateCertificate,
      akash.cert.v1beta2.Cert.MsgCreateCertificateResponse> getCreateCertificateMethod() {
    io.grpc.MethodDescriptor<akash.cert.v1beta2.Cert.MsgCreateCertificate, akash.cert.v1beta2.Cert.MsgCreateCertificateResponse> getCreateCertificateMethod;
    if ((getCreateCertificateMethod = MsgGrpc.getCreateCertificateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateCertificateMethod = MsgGrpc.getCreateCertificateMethod) == null) {
          MsgGrpc.getCreateCertificateMethod = getCreateCertificateMethod =
              io.grpc.MethodDescriptor.<akash.cert.v1beta2.Cert.MsgCreateCertificate, akash.cert.v1beta2.Cert.MsgCreateCertificateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateCertificate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.cert.v1beta2.Cert.MsgCreateCertificate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.cert.v1beta2.Cert.MsgCreateCertificateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateCertificate"))
              .build();
        }
      }
    }
    return getCreateCertificateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.cert.v1beta2.Cert.MsgRevokeCertificate,
      akash.cert.v1beta2.Cert.MsgRevokeCertificateResponse> getRevokeCertificateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RevokeCertificate",
      requestType = akash.cert.v1beta2.Cert.MsgRevokeCertificate.class,
      responseType = akash.cert.v1beta2.Cert.MsgRevokeCertificateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.cert.v1beta2.Cert.MsgRevokeCertificate,
      akash.cert.v1beta2.Cert.MsgRevokeCertificateResponse> getRevokeCertificateMethod() {
    io.grpc.MethodDescriptor<akash.cert.v1beta2.Cert.MsgRevokeCertificate, akash.cert.v1beta2.Cert.MsgRevokeCertificateResponse> getRevokeCertificateMethod;
    if ((getRevokeCertificateMethod = MsgGrpc.getRevokeCertificateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRevokeCertificateMethod = MsgGrpc.getRevokeCertificateMethod) == null) {
          MsgGrpc.getRevokeCertificateMethod = getRevokeCertificateMethod =
              io.grpc.MethodDescriptor.<akash.cert.v1beta2.Cert.MsgRevokeCertificate, akash.cert.v1beta2.Cert.MsgRevokeCertificateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RevokeCertificate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.cert.v1beta2.Cert.MsgRevokeCertificate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.cert.v1beta2.Cert.MsgRevokeCertificateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RevokeCertificate"))
              .build();
        }
      }
    }
    return getRevokeCertificateMethod;
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
   * Msg defines the provider Msg service
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreateCertificate defines a method to create new certificate given proper inputs.
     * </pre>
     */
    public void createCertificate(akash.cert.v1beta2.Cert.MsgCreateCertificate request,
        io.grpc.stub.StreamObserver<akash.cert.v1beta2.Cert.MsgCreateCertificateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateCertificateMethod(), responseObserver);
    }

    /**
     * <pre>
     * RevokeCertificate defines a method to revoke the certificate
     * </pre>
     */
    public void revokeCertificate(akash.cert.v1beta2.Cert.MsgRevokeCertificate request,
        io.grpc.stub.StreamObserver<akash.cert.v1beta2.Cert.MsgRevokeCertificateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRevokeCertificateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateCertificateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.cert.v1beta2.Cert.MsgCreateCertificate,
                akash.cert.v1beta2.Cert.MsgCreateCertificateResponse>(
                  this, METHODID_CREATE_CERTIFICATE)))
          .addMethod(
            getRevokeCertificateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.cert.v1beta2.Cert.MsgRevokeCertificate,
                akash.cert.v1beta2.Cert.MsgRevokeCertificateResponse>(
                  this, METHODID_REVOKE_CERTIFICATE)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the provider Msg service
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
     * CreateCertificate defines a method to create new certificate given proper inputs.
     * </pre>
     */
    public void createCertificate(akash.cert.v1beta2.Cert.MsgCreateCertificate request,
        io.grpc.stub.StreamObserver<akash.cert.v1beta2.Cert.MsgCreateCertificateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateCertificateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RevokeCertificate defines a method to revoke the certificate
     * </pre>
     */
    public void revokeCertificate(akash.cert.v1beta2.Cert.MsgRevokeCertificate request,
        io.grpc.stub.StreamObserver<akash.cert.v1beta2.Cert.MsgRevokeCertificateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRevokeCertificateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the provider Msg service
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
     * CreateCertificate defines a method to create new certificate given proper inputs.
     * </pre>
     */
    public akash.cert.v1beta2.Cert.MsgCreateCertificateResponse createCertificate(akash.cert.v1beta2.Cert.MsgCreateCertificate request) {
      return blockingUnaryCall(
          getChannel(), getCreateCertificateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RevokeCertificate defines a method to revoke the certificate
     * </pre>
     */
    public akash.cert.v1beta2.Cert.MsgRevokeCertificateResponse revokeCertificate(akash.cert.v1beta2.Cert.MsgRevokeCertificate request) {
      return blockingUnaryCall(
          getChannel(), getRevokeCertificateMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the provider Msg service
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
     * CreateCertificate defines a method to create new certificate given proper inputs.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.cert.v1beta2.Cert.MsgCreateCertificateResponse> createCertificate(
        akash.cert.v1beta2.Cert.MsgCreateCertificate request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateCertificateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RevokeCertificate defines a method to revoke the certificate
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.cert.v1beta2.Cert.MsgRevokeCertificateResponse> revokeCertificate(
        akash.cert.v1beta2.Cert.MsgRevokeCertificate request) {
      return futureUnaryCall(
          getChannel().newCall(getRevokeCertificateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_CERTIFICATE = 0;
  private static final int METHODID_REVOKE_CERTIFICATE = 1;

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
        case METHODID_CREATE_CERTIFICATE:
          serviceImpl.createCertificate((akash.cert.v1beta2.Cert.MsgCreateCertificate) request,
              (io.grpc.stub.StreamObserver<akash.cert.v1beta2.Cert.MsgCreateCertificateResponse>) responseObserver);
          break;
        case METHODID_REVOKE_CERTIFICATE:
          serviceImpl.revokeCertificate((akash.cert.v1beta2.Cert.MsgRevokeCertificate) request,
              (io.grpc.stub.StreamObserver<akash.cert.v1beta2.Cert.MsgRevokeCertificateResponse>) responseObserver);
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
      return akash.cert.v1beta2.Cert.getDescriptor();
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
              .addMethod(getCreateCertificateMethod())
              .addMethod(getRevokeCertificateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
