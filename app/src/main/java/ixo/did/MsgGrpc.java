package ixo.did;

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
 * Msg defines the did Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: ixo/did/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "did.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ixo.did.Tx.MsgAddDid,
      ixo.did.Tx.MsgAddDidResponse> getAddDidMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddDid",
      requestType = ixo.did.Tx.MsgAddDid.class,
      responseType = ixo.did.Tx.MsgAddDidResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ixo.did.Tx.MsgAddDid,
      ixo.did.Tx.MsgAddDidResponse> getAddDidMethod() {
    io.grpc.MethodDescriptor<ixo.did.Tx.MsgAddDid, ixo.did.Tx.MsgAddDidResponse> getAddDidMethod;
    if ((getAddDidMethod = MsgGrpc.getAddDidMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddDidMethod = MsgGrpc.getAddDidMethod) == null) {
          MsgGrpc.getAddDidMethod = getAddDidMethod =
              io.grpc.MethodDescriptor.<ixo.did.Tx.MsgAddDid, ixo.did.Tx.MsgAddDidResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddDid"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.did.Tx.MsgAddDid.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.did.Tx.MsgAddDidResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddDid"))
              .build();
        }
      }
    }
    return getAddDidMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ixo.did.Tx.MsgAddCredential,
      ixo.did.Tx.MsgAddCredentialResponse> getAddCredentialMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddCredential",
      requestType = ixo.did.Tx.MsgAddCredential.class,
      responseType = ixo.did.Tx.MsgAddCredentialResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ixo.did.Tx.MsgAddCredential,
      ixo.did.Tx.MsgAddCredentialResponse> getAddCredentialMethod() {
    io.grpc.MethodDescriptor<ixo.did.Tx.MsgAddCredential, ixo.did.Tx.MsgAddCredentialResponse> getAddCredentialMethod;
    if ((getAddCredentialMethod = MsgGrpc.getAddCredentialMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddCredentialMethod = MsgGrpc.getAddCredentialMethod) == null) {
          MsgGrpc.getAddCredentialMethod = getAddCredentialMethod =
              io.grpc.MethodDescriptor.<ixo.did.Tx.MsgAddCredential, ixo.did.Tx.MsgAddCredentialResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddCredential"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.did.Tx.MsgAddCredential.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.did.Tx.MsgAddCredentialResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddCredential"))
              .build();
        }
      }
    }
    return getAddCredentialMethod;
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
   * Msg defines the did Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * AddDid defines a method for adding a DID.
     * </pre>
     */
    public void addDid(ixo.did.Tx.MsgAddDid request,
        io.grpc.stub.StreamObserver<ixo.did.Tx.MsgAddDidResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddDidMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddCredential defines a method for adding a credential to the signer's DID.
     * </pre>
     */
    public void addCredential(ixo.did.Tx.MsgAddCredential request,
        io.grpc.stub.StreamObserver<ixo.did.Tx.MsgAddCredentialResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddCredentialMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddDidMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ixo.did.Tx.MsgAddDid,
                ixo.did.Tx.MsgAddDidResponse>(
                  this, METHODID_ADD_DID)))
          .addMethod(
            getAddCredentialMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ixo.did.Tx.MsgAddCredential,
                ixo.did.Tx.MsgAddCredentialResponse>(
                  this, METHODID_ADD_CREDENTIAL)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the did Msg service.
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
     * AddDid defines a method for adding a DID.
     * </pre>
     */
    public void addDid(ixo.did.Tx.MsgAddDid request,
        io.grpc.stub.StreamObserver<ixo.did.Tx.MsgAddDidResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddDidMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddCredential defines a method for adding a credential to the signer's DID.
     * </pre>
     */
    public void addCredential(ixo.did.Tx.MsgAddCredential request,
        io.grpc.stub.StreamObserver<ixo.did.Tx.MsgAddCredentialResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddCredentialMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the did Msg service.
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
     * AddDid defines a method for adding a DID.
     * </pre>
     */
    public ixo.did.Tx.MsgAddDidResponse addDid(ixo.did.Tx.MsgAddDid request) {
      return blockingUnaryCall(
          getChannel(), getAddDidMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddCredential defines a method for adding a credential to the signer's DID.
     * </pre>
     */
    public ixo.did.Tx.MsgAddCredentialResponse addCredential(ixo.did.Tx.MsgAddCredential request) {
      return blockingUnaryCall(
          getChannel(), getAddCredentialMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the did Msg service.
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
     * AddDid defines a method for adding a DID.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ixo.did.Tx.MsgAddDidResponse> addDid(
        ixo.did.Tx.MsgAddDid request) {
      return futureUnaryCall(
          getChannel().newCall(getAddDidMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddCredential defines a method for adding a credential to the signer's DID.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ixo.did.Tx.MsgAddCredentialResponse> addCredential(
        ixo.did.Tx.MsgAddCredential request) {
      return futureUnaryCall(
          getChannel().newCall(getAddCredentialMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_DID = 0;
  private static final int METHODID_ADD_CREDENTIAL = 1;

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
        case METHODID_ADD_DID:
          serviceImpl.addDid((ixo.did.Tx.MsgAddDid) request,
              (io.grpc.stub.StreamObserver<ixo.did.Tx.MsgAddDidResponse>) responseObserver);
          break;
        case METHODID_ADD_CREDENTIAL:
          serviceImpl.addCredential((ixo.did.Tx.MsgAddCredential) request,
              (io.grpc.stub.StreamObserver<ixo.did.Tx.MsgAddCredentialResponse>) responseObserver);
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
      return ixo.did.Tx.getDescriptor();
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
              .addMethod(getAddDidMethod())
              .addMethod(getAddCredentialMethod())
              .build();
        }
      }
    }
    return result;
  }
}
