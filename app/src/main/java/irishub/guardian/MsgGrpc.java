package irishub.guardian;

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
 * Msg defines the guardian Msg service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: guardian/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "irishub.guardian.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<irishub.guardian.Tx.MsgAddSuper,
      irishub.guardian.Tx.MsgAddSuperResponse> getAddSuperMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddSuper",
      requestType = irishub.guardian.Tx.MsgAddSuper.class,
      responseType = irishub.guardian.Tx.MsgAddSuperResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irishub.guardian.Tx.MsgAddSuper,
      irishub.guardian.Tx.MsgAddSuperResponse> getAddSuperMethod() {
    io.grpc.MethodDescriptor<irishub.guardian.Tx.MsgAddSuper, irishub.guardian.Tx.MsgAddSuperResponse> getAddSuperMethod;
    if ((getAddSuperMethod = MsgGrpc.getAddSuperMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddSuperMethod = MsgGrpc.getAddSuperMethod) == null) {
          MsgGrpc.getAddSuperMethod = getAddSuperMethod =
              io.grpc.MethodDescriptor.<irishub.guardian.Tx.MsgAddSuper, irishub.guardian.Tx.MsgAddSuperResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddSuper"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irishub.guardian.Tx.MsgAddSuper.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irishub.guardian.Tx.MsgAddSuperResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddSuper"))
              .build();
        }
      }
    }
    return getAddSuperMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irishub.guardian.Tx.MsgDeleteSuper,
      irishub.guardian.Tx.MsgDeleteSuperResponse> getDeleteSuperMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteSuper",
      requestType = irishub.guardian.Tx.MsgDeleteSuper.class,
      responseType = irishub.guardian.Tx.MsgDeleteSuperResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irishub.guardian.Tx.MsgDeleteSuper,
      irishub.guardian.Tx.MsgDeleteSuperResponse> getDeleteSuperMethod() {
    io.grpc.MethodDescriptor<irishub.guardian.Tx.MsgDeleteSuper, irishub.guardian.Tx.MsgDeleteSuperResponse> getDeleteSuperMethod;
    if ((getDeleteSuperMethod = MsgGrpc.getDeleteSuperMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteSuperMethod = MsgGrpc.getDeleteSuperMethod) == null) {
          MsgGrpc.getDeleteSuperMethod = getDeleteSuperMethod =
              io.grpc.MethodDescriptor.<irishub.guardian.Tx.MsgDeleteSuper, irishub.guardian.Tx.MsgDeleteSuperResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteSuper"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irishub.guardian.Tx.MsgDeleteSuper.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irishub.guardian.Tx.MsgDeleteSuperResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteSuper"))
              .build();
        }
      }
    }
    return getDeleteSuperMethod;
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
   * Msg defines the guardian Msg service
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * AddSuper defines a method for adding a super account
     * </pre>
     */
    public void addSuper(irishub.guardian.Tx.MsgAddSuper request,
        io.grpc.stub.StreamObserver<irishub.guardian.Tx.MsgAddSuperResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddSuperMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteSuper defines a method for deleting a super account
     * </pre>
     */
    public void deleteSuper(irishub.guardian.Tx.MsgDeleteSuper request,
        io.grpc.stub.StreamObserver<irishub.guardian.Tx.MsgDeleteSuperResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteSuperMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddSuperMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irishub.guardian.Tx.MsgAddSuper,
                irishub.guardian.Tx.MsgAddSuperResponse>(
                  this, METHODID_ADD_SUPER)))
          .addMethod(
            getDeleteSuperMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irishub.guardian.Tx.MsgDeleteSuper,
                irishub.guardian.Tx.MsgDeleteSuperResponse>(
                  this, METHODID_DELETE_SUPER)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the guardian Msg service
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
     * AddSuper defines a method for adding a super account
     * </pre>
     */
    public void addSuper(irishub.guardian.Tx.MsgAddSuper request,
        io.grpc.stub.StreamObserver<irishub.guardian.Tx.MsgAddSuperResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddSuperMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteSuper defines a method for deleting a super account
     * </pre>
     */
    public void deleteSuper(irishub.guardian.Tx.MsgDeleteSuper request,
        io.grpc.stub.StreamObserver<irishub.guardian.Tx.MsgDeleteSuperResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteSuperMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the guardian Msg service
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
     * AddSuper defines a method for adding a super account
     * </pre>
     */
    public irishub.guardian.Tx.MsgAddSuperResponse addSuper(irishub.guardian.Tx.MsgAddSuper request) {
      return blockingUnaryCall(
          getChannel(), getAddSuperMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteSuper defines a method for deleting a super account
     * </pre>
     */
    public irishub.guardian.Tx.MsgDeleteSuperResponse deleteSuper(irishub.guardian.Tx.MsgDeleteSuper request) {
      return blockingUnaryCall(
          getChannel(), getDeleteSuperMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the guardian Msg service
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
     * AddSuper defines a method for adding a super account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irishub.guardian.Tx.MsgAddSuperResponse> addSuper(
        irishub.guardian.Tx.MsgAddSuper request) {
      return futureUnaryCall(
          getChannel().newCall(getAddSuperMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteSuper defines a method for deleting a super account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irishub.guardian.Tx.MsgDeleteSuperResponse> deleteSuper(
        irishub.guardian.Tx.MsgDeleteSuper request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteSuperMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_SUPER = 0;
  private static final int METHODID_DELETE_SUPER = 1;

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
        case METHODID_ADD_SUPER:
          serviceImpl.addSuper((irishub.guardian.Tx.MsgAddSuper) request,
              (io.grpc.stub.StreamObserver<irishub.guardian.Tx.MsgAddSuperResponse>) responseObserver);
          break;
        case METHODID_DELETE_SUPER:
          serviceImpl.deleteSuper((irishub.guardian.Tx.MsgDeleteSuper) request,
              (io.grpc.stub.StreamObserver<irishub.guardian.Tx.MsgDeleteSuperResponse>) responseObserver);
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
      return irishub.guardian.Tx.getDescriptor();
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
              .addMethod(getAddSuperMethod())
              .addMethod(getDeleteSuperMethod())
              .build();
        }
      }
    }
    return result;
  }
}
