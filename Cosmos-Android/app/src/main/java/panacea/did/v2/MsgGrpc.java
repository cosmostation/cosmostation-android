package panacea.did.v2;

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
 * Msg defines the Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: panacea/did/v2/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "panacea.did.v2.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<panacea.did.v2.MsgCreateDID,
      panacea.did.v2.MsgCreateDIDResponse> getCreateDIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateDID",
      requestType = panacea.did.v2.MsgCreateDID.class,
      responseType = panacea.did.v2.MsgCreateDIDResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<panacea.did.v2.MsgCreateDID,
      panacea.did.v2.MsgCreateDIDResponse> getCreateDIDMethod() {
    io.grpc.MethodDescriptor<panacea.did.v2.MsgCreateDID, panacea.did.v2.MsgCreateDIDResponse> getCreateDIDMethod;
    if ((getCreateDIDMethod = MsgGrpc.getCreateDIDMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateDIDMethod = MsgGrpc.getCreateDIDMethod) == null) {
          MsgGrpc.getCreateDIDMethod = getCreateDIDMethod =
              io.grpc.MethodDescriptor.<panacea.did.v2.MsgCreateDID, panacea.did.v2.MsgCreateDIDResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateDID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.did.v2.MsgCreateDID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.did.v2.MsgCreateDIDResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateDID"))
              .build();
        }
      }
    }
    return getCreateDIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<panacea.did.v2.MsgUpdateDID,
      panacea.did.v2.MsgUpdateDIDResponse> getUpdateDIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateDID",
      requestType = panacea.did.v2.MsgUpdateDID.class,
      responseType = panacea.did.v2.MsgUpdateDIDResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<panacea.did.v2.MsgUpdateDID,
      panacea.did.v2.MsgUpdateDIDResponse> getUpdateDIDMethod() {
    io.grpc.MethodDescriptor<panacea.did.v2.MsgUpdateDID, panacea.did.v2.MsgUpdateDIDResponse> getUpdateDIDMethod;
    if ((getUpdateDIDMethod = MsgGrpc.getUpdateDIDMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateDIDMethod = MsgGrpc.getUpdateDIDMethod) == null) {
          MsgGrpc.getUpdateDIDMethod = getUpdateDIDMethod =
              io.grpc.MethodDescriptor.<panacea.did.v2.MsgUpdateDID, panacea.did.v2.MsgUpdateDIDResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateDID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.did.v2.MsgUpdateDID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.did.v2.MsgUpdateDIDResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateDID"))
              .build();
        }
      }
    }
    return getUpdateDIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<panacea.did.v2.MsgDeactivateDID,
      panacea.did.v2.MsgDeactivateDIDResponse> getDeactivateDIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeactivateDID",
      requestType = panacea.did.v2.MsgDeactivateDID.class,
      responseType = panacea.did.v2.MsgDeactivateDIDResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<panacea.did.v2.MsgDeactivateDID,
      panacea.did.v2.MsgDeactivateDIDResponse> getDeactivateDIDMethod() {
    io.grpc.MethodDescriptor<panacea.did.v2.MsgDeactivateDID, panacea.did.v2.MsgDeactivateDIDResponse> getDeactivateDIDMethod;
    if ((getDeactivateDIDMethod = MsgGrpc.getDeactivateDIDMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeactivateDIDMethod = MsgGrpc.getDeactivateDIDMethod) == null) {
          MsgGrpc.getDeactivateDIDMethod = getDeactivateDIDMethod =
              io.grpc.MethodDescriptor.<panacea.did.v2.MsgDeactivateDID, panacea.did.v2.MsgDeactivateDIDResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeactivateDID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.did.v2.MsgDeactivateDID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.did.v2.MsgDeactivateDIDResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeactivateDID"))
              .build();
        }
      }
    }
    return getDeactivateDIDMethod;
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
   * Msg defines the Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreateDID defines a method for creating a DID.
     * </pre>
     */
    public void createDID(panacea.did.v2.MsgCreateDID request,
        io.grpc.stub.StreamObserver<panacea.did.v2.MsgCreateDIDResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateDIDMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateDID defines a method for updating a DID.
     * </pre>
     */
    public void updateDID(panacea.did.v2.MsgUpdateDID request,
        io.grpc.stub.StreamObserver<panacea.did.v2.MsgUpdateDIDResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateDIDMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeactivateDID defines a method for deactivating a DID.
     * </pre>
     */
    public void deactivateDID(panacea.did.v2.MsgDeactivateDID request,
        io.grpc.stub.StreamObserver<panacea.did.v2.MsgDeactivateDIDResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeactivateDIDMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateDIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                panacea.did.v2.MsgCreateDID,
                panacea.did.v2.MsgCreateDIDResponse>(
                  this, METHODID_CREATE_DID)))
          .addMethod(
            getUpdateDIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                panacea.did.v2.MsgUpdateDID,
                panacea.did.v2.MsgUpdateDIDResponse>(
                  this, METHODID_UPDATE_DID)))
          .addMethod(
            getDeactivateDIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                panacea.did.v2.MsgDeactivateDID,
                panacea.did.v2.MsgDeactivateDIDResponse>(
                  this, METHODID_DEACTIVATE_DID)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
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
     * CreateDID defines a method for creating a DID.
     * </pre>
     */
    public void createDID(panacea.did.v2.MsgCreateDID request,
        io.grpc.stub.StreamObserver<panacea.did.v2.MsgCreateDIDResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateDIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateDID defines a method for updating a DID.
     * </pre>
     */
    public void updateDID(panacea.did.v2.MsgUpdateDID request,
        io.grpc.stub.StreamObserver<panacea.did.v2.MsgUpdateDIDResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateDIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeactivateDID defines a method for deactivating a DID.
     * </pre>
     */
    public void deactivateDID(panacea.did.v2.MsgDeactivateDID request,
        io.grpc.stub.StreamObserver<panacea.did.v2.MsgDeactivateDIDResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeactivateDIDMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
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
     * CreateDID defines a method for creating a DID.
     * </pre>
     */
    public panacea.did.v2.MsgCreateDIDResponse createDID(panacea.did.v2.MsgCreateDID request) {
      return blockingUnaryCall(
          getChannel(), getCreateDIDMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateDID defines a method for updating a DID.
     * </pre>
     */
    public panacea.did.v2.MsgUpdateDIDResponse updateDID(panacea.did.v2.MsgUpdateDID request) {
      return blockingUnaryCall(
          getChannel(), getUpdateDIDMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeactivateDID defines a method for deactivating a DID.
     * </pre>
     */
    public panacea.did.v2.MsgDeactivateDIDResponse deactivateDID(panacea.did.v2.MsgDeactivateDID request) {
      return blockingUnaryCall(
          getChannel(), getDeactivateDIDMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
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
     * CreateDID defines a method for creating a DID.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<panacea.did.v2.MsgCreateDIDResponse> createDID(
        panacea.did.v2.MsgCreateDID request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateDIDMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateDID defines a method for updating a DID.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<panacea.did.v2.MsgUpdateDIDResponse> updateDID(
        panacea.did.v2.MsgUpdateDID request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateDIDMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeactivateDID defines a method for deactivating a DID.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<panacea.did.v2.MsgDeactivateDIDResponse> deactivateDID(
        panacea.did.v2.MsgDeactivateDID request) {
      return futureUnaryCall(
          getChannel().newCall(getDeactivateDIDMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_DID = 0;
  private static final int METHODID_UPDATE_DID = 1;
  private static final int METHODID_DEACTIVATE_DID = 2;

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
        case METHODID_CREATE_DID:
          serviceImpl.createDID((panacea.did.v2.MsgCreateDID) request,
              (io.grpc.stub.StreamObserver<panacea.did.v2.MsgCreateDIDResponse>) responseObserver);
          break;
        case METHODID_UPDATE_DID:
          serviceImpl.updateDID((panacea.did.v2.MsgUpdateDID) request,
              (io.grpc.stub.StreamObserver<panacea.did.v2.MsgUpdateDIDResponse>) responseObserver);
          break;
        case METHODID_DEACTIVATE_DID:
          serviceImpl.deactivateDID((panacea.did.v2.MsgDeactivateDID) request,
              (io.grpc.stub.StreamObserver<panacea.did.v2.MsgDeactivateDIDResponse>) responseObserver);
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
      return panacea.did.v2.Tx.getDescriptor();
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
              .addMethod(getCreateDIDMethod())
              .addMethod(getUpdateDIDMethod())
              .addMethod(getDeactivateDIDMethod())
              .build();
        }
      }
    }
    return result;
  }
}
