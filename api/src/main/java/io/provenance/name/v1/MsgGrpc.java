package io.provenance.name.v1;

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
    comments = "Source: provenance/name/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "provenance.name.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.provenance.name.v1.MsgBindNameRequest,
      io.provenance.name.v1.MsgBindNameResponse> getBindNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BindName",
      requestType = io.provenance.name.v1.MsgBindNameRequest.class,
      responseType = io.provenance.name.v1.MsgBindNameResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.name.v1.MsgBindNameRequest,
      io.provenance.name.v1.MsgBindNameResponse> getBindNameMethod() {
    io.grpc.MethodDescriptor<io.provenance.name.v1.MsgBindNameRequest, io.provenance.name.v1.MsgBindNameResponse> getBindNameMethod;
    if ((getBindNameMethod = MsgGrpc.getBindNameMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBindNameMethod = MsgGrpc.getBindNameMethod) == null) {
          MsgGrpc.getBindNameMethod = getBindNameMethod =
              io.grpc.MethodDescriptor.<io.provenance.name.v1.MsgBindNameRequest, io.provenance.name.v1.MsgBindNameResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BindName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.name.v1.MsgBindNameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.name.v1.MsgBindNameResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BindName"))
              .build();
        }
      }
    }
    return getBindNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.name.v1.MsgDeleteNameRequest,
      io.provenance.name.v1.MsgDeleteNameResponse> getDeleteNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteName",
      requestType = io.provenance.name.v1.MsgDeleteNameRequest.class,
      responseType = io.provenance.name.v1.MsgDeleteNameResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.name.v1.MsgDeleteNameRequest,
      io.provenance.name.v1.MsgDeleteNameResponse> getDeleteNameMethod() {
    io.grpc.MethodDescriptor<io.provenance.name.v1.MsgDeleteNameRequest, io.provenance.name.v1.MsgDeleteNameResponse> getDeleteNameMethod;
    if ((getDeleteNameMethod = MsgGrpc.getDeleteNameMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteNameMethod = MsgGrpc.getDeleteNameMethod) == null) {
          MsgGrpc.getDeleteNameMethod = getDeleteNameMethod =
              io.grpc.MethodDescriptor.<io.provenance.name.v1.MsgDeleteNameRequest, io.provenance.name.v1.MsgDeleteNameResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.name.v1.MsgDeleteNameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.name.v1.MsgDeleteNameResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteName"))
              .build();
        }
      }
    }
    return getDeleteNameMethod;
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
     * BindName binds a name to an address under a root name.
     * </pre>
     */
    public void bindName(io.provenance.name.v1.MsgBindNameRequest request,
        io.grpc.stub.StreamObserver<io.provenance.name.v1.MsgBindNameResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBindNameMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteName defines a method to verify a particular invariance.
     * </pre>
     */
    public void deleteName(io.provenance.name.v1.MsgDeleteNameRequest request,
        io.grpc.stub.StreamObserver<io.provenance.name.v1.MsgDeleteNameResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteNameMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getBindNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.name.v1.MsgBindNameRequest,
                io.provenance.name.v1.MsgBindNameResponse>(
                  this, METHODID_BIND_NAME)))
          .addMethod(
            getDeleteNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.name.v1.MsgDeleteNameRequest,
                io.provenance.name.v1.MsgDeleteNameResponse>(
                  this, METHODID_DELETE_NAME)))
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
     * BindName binds a name to an address under a root name.
     * </pre>
     */
    public void bindName(io.provenance.name.v1.MsgBindNameRequest request,
        io.grpc.stub.StreamObserver<io.provenance.name.v1.MsgBindNameResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBindNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteName defines a method to verify a particular invariance.
     * </pre>
     */
    public void deleteName(io.provenance.name.v1.MsgDeleteNameRequest request,
        io.grpc.stub.StreamObserver<io.provenance.name.v1.MsgDeleteNameResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteNameMethod(), getCallOptions()), request, responseObserver);
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
     * BindName binds a name to an address under a root name.
     * </pre>
     */
    public io.provenance.name.v1.MsgBindNameResponse bindName(io.provenance.name.v1.MsgBindNameRequest request) {
      return blockingUnaryCall(
          getChannel(), getBindNameMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteName defines a method to verify a particular invariance.
     * </pre>
     */
    public io.provenance.name.v1.MsgDeleteNameResponse deleteName(io.provenance.name.v1.MsgDeleteNameRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteNameMethod(), getCallOptions(), request);
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
     * BindName binds a name to an address under a root name.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.name.v1.MsgBindNameResponse> bindName(
        io.provenance.name.v1.MsgBindNameRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBindNameMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteName defines a method to verify a particular invariance.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.name.v1.MsgDeleteNameResponse> deleteName(
        io.provenance.name.v1.MsgDeleteNameRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteNameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BIND_NAME = 0;
  private static final int METHODID_DELETE_NAME = 1;

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
        case METHODID_BIND_NAME:
          serviceImpl.bindName((io.provenance.name.v1.MsgBindNameRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.name.v1.MsgBindNameResponse>) responseObserver);
          break;
        case METHODID_DELETE_NAME:
          serviceImpl.deleteName((io.provenance.name.v1.MsgDeleteNameRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.name.v1.MsgDeleteNameResponse>) responseObserver);
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
      return io.provenance.name.v1.Tx.getDescriptor();
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
              .addMethod(getBindNameMethod())
              .addMethod(getDeleteNameMethod())
              .build();
        }
      }
    }
    return result;
  }
}
