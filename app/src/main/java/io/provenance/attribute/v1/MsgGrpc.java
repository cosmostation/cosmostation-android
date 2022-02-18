package io.provenance.attribute.v1;

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
    comments = "Source: provenance/attribute/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "provenance.attribute.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.provenance.attribute.v1.MsgAddAttributeRequest,
      io.provenance.attribute.v1.MsgAddAttributeResponse> getAddAttributeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddAttribute",
      requestType = io.provenance.attribute.v1.MsgAddAttributeRequest.class,
      responseType = io.provenance.attribute.v1.MsgAddAttributeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.attribute.v1.MsgAddAttributeRequest,
      io.provenance.attribute.v1.MsgAddAttributeResponse> getAddAttributeMethod() {
    io.grpc.MethodDescriptor<io.provenance.attribute.v1.MsgAddAttributeRequest, io.provenance.attribute.v1.MsgAddAttributeResponse> getAddAttributeMethod;
    if ((getAddAttributeMethod = MsgGrpc.getAddAttributeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddAttributeMethod = MsgGrpc.getAddAttributeMethod) == null) {
          MsgGrpc.getAddAttributeMethod = getAddAttributeMethod =
              io.grpc.MethodDescriptor.<io.provenance.attribute.v1.MsgAddAttributeRequest, io.provenance.attribute.v1.MsgAddAttributeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddAttribute"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.attribute.v1.MsgAddAttributeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.attribute.v1.MsgAddAttributeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddAttribute"))
              .build();
        }
      }
    }
    return getAddAttributeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.attribute.v1.MsgUpdateAttributeRequest,
      io.provenance.attribute.v1.MsgUpdateAttributeResponse> getUpdateAttributeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateAttribute",
      requestType = io.provenance.attribute.v1.MsgUpdateAttributeRequest.class,
      responseType = io.provenance.attribute.v1.MsgUpdateAttributeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.attribute.v1.MsgUpdateAttributeRequest,
      io.provenance.attribute.v1.MsgUpdateAttributeResponse> getUpdateAttributeMethod() {
    io.grpc.MethodDescriptor<io.provenance.attribute.v1.MsgUpdateAttributeRequest, io.provenance.attribute.v1.MsgUpdateAttributeResponse> getUpdateAttributeMethod;
    if ((getUpdateAttributeMethod = MsgGrpc.getUpdateAttributeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateAttributeMethod = MsgGrpc.getUpdateAttributeMethod) == null) {
          MsgGrpc.getUpdateAttributeMethod = getUpdateAttributeMethod =
              io.grpc.MethodDescriptor.<io.provenance.attribute.v1.MsgUpdateAttributeRequest, io.provenance.attribute.v1.MsgUpdateAttributeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateAttribute"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.attribute.v1.MsgUpdateAttributeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.attribute.v1.MsgUpdateAttributeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateAttribute"))
              .build();
        }
      }
    }
    return getUpdateAttributeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.attribute.v1.MsgDeleteAttributeRequest,
      io.provenance.attribute.v1.MsgDeleteAttributeResponse> getDeleteAttributeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteAttribute",
      requestType = io.provenance.attribute.v1.MsgDeleteAttributeRequest.class,
      responseType = io.provenance.attribute.v1.MsgDeleteAttributeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.attribute.v1.MsgDeleteAttributeRequest,
      io.provenance.attribute.v1.MsgDeleteAttributeResponse> getDeleteAttributeMethod() {
    io.grpc.MethodDescriptor<io.provenance.attribute.v1.MsgDeleteAttributeRequest, io.provenance.attribute.v1.MsgDeleteAttributeResponse> getDeleteAttributeMethod;
    if ((getDeleteAttributeMethod = MsgGrpc.getDeleteAttributeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteAttributeMethod = MsgGrpc.getDeleteAttributeMethod) == null) {
          MsgGrpc.getDeleteAttributeMethod = getDeleteAttributeMethod =
              io.grpc.MethodDescriptor.<io.provenance.attribute.v1.MsgDeleteAttributeRequest, io.provenance.attribute.v1.MsgDeleteAttributeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteAttribute"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.attribute.v1.MsgDeleteAttributeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.attribute.v1.MsgDeleteAttributeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteAttribute"))
              .build();
        }
      }
    }
    return getDeleteAttributeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.attribute.v1.MsgDeleteDistinctAttributeRequest,
      io.provenance.attribute.v1.MsgDeleteDistinctAttributeResponse> getDeleteDistinctAttributeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteDistinctAttribute",
      requestType = io.provenance.attribute.v1.MsgDeleteDistinctAttributeRequest.class,
      responseType = io.provenance.attribute.v1.MsgDeleteDistinctAttributeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.attribute.v1.MsgDeleteDistinctAttributeRequest,
      io.provenance.attribute.v1.MsgDeleteDistinctAttributeResponse> getDeleteDistinctAttributeMethod() {
    io.grpc.MethodDescriptor<io.provenance.attribute.v1.MsgDeleteDistinctAttributeRequest, io.provenance.attribute.v1.MsgDeleteDistinctAttributeResponse> getDeleteDistinctAttributeMethod;
    if ((getDeleteDistinctAttributeMethod = MsgGrpc.getDeleteDistinctAttributeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteDistinctAttributeMethod = MsgGrpc.getDeleteDistinctAttributeMethod) == null) {
          MsgGrpc.getDeleteDistinctAttributeMethod = getDeleteDistinctAttributeMethod =
              io.grpc.MethodDescriptor.<io.provenance.attribute.v1.MsgDeleteDistinctAttributeRequest, io.provenance.attribute.v1.MsgDeleteDistinctAttributeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteDistinctAttribute"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.attribute.v1.MsgDeleteDistinctAttributeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.attribute.v1.MsgDeleteDistinctAttributeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteDistinctAttribute"))
              .build();
        }
      }
    }
    return getDeleteDistinctAttributeMethod;
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
     * AddAttribute defines a method to verify a particular invariance.
     * </pre>
     */
    public void addAttribute(io.provenance.attribute.v1.MsgAddAttributeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.attribute.v1.MsgAddAttributeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddAttributeMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateAttribute defines a method to verify a particular invariance.
     * </pre>
     */
    public void updateAttribute(io.provenance.attribute.v1.MsgUpdateAttributeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.attribute.v1.MsgUpdateAttributeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateAttributeMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteAttribute defines a method to verify a particular invariance.
     * </pre>
     */
    public void deleteAttribute(io.provenance.attribute.v1.MsgDeleteAttributeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.attribute.v1.MsgDeleteAttributeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteAttributeMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteDistinctAttribute defines a method to verify a particular invariance.
     * </pre>
     */
    public void deleteDistinctAttribute(io.provenance.attribute.v1.MsgDeleteDistinctAttributeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.attribute.v1.MsgDeleteDistinctAttributeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteDistinctAttributeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddAttributeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.attribute.v1.MsgAddAttributeRequest,
                io.provenance.attribute.v1.MsgAddAttributeResponse>(
                  this, METHODID_ADD_ATTRIBUTE)))
          .addMethod(
            getUpdateAttributeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.attribute.v1.MsgUpdateAttributeRequest,
                io.provenance.attribute.v1.MsgUpdateAttributeResponse>(
                  this, METHODID_UPDATE_ATTRIBUTE)))
          .addMethod(
            getDeleteAttributeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.attribute.v1.MsgDeleteAttributeRequest,
                io.provenance.attribute.v1.MsgDeleteAttributeResponse>(
                  this, METHODID_DELETE_ATTRIBUTE)))
          .addMethod(
            getDeleteDistinctAttributeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.attribute.v1.MsgDeleteDistinctAttributeRequest,
                io.provenance.attribute.v1.MsgDeleteDistinctAttributeResponse>(
                  this, METHODID_DELETE_DISTINCT_ATTRIBUTE)))
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
     * AddAttribute defines a method to verify a particular invariance.
     * </pre>
     */
    public void addAttribute(io.provenance.attribute.v1.MsgAddAttributeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.attribute.v1.MsgAddAttributeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddAttributeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateAttribute defines a method to verify a particular invariance.
     * </pre>
     */
    public void updateAttribute(io.provenance.attribute.v1.MsgUpdateAttributeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.attribute.v1.MsgUpdateAttributeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateAttributeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteAttribute defines a method to verify a particular invariance.
     * </pre>
     */
    public void deleteAttribute(io.provenance.attribute.v1.MsgDeleteAttributeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.attribute.v1.MsgDeleteAttributeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteAttributeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteDistinctAttribute defines a method to verify a particular invariance.
     * </pre>
     */
    public void deleteDistinctAttribute(io.provenance.attribute.v1.MsgDeleteDistinctAttributeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.attribute.v1.MsgDeleteDistinctAttributeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteDistinctAttributeMethod(), getCallOptions()), request, responseObserver);
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
     * AddAttribute defines a method to verify a particular invariance.
     * </pre>
     */
    public io.provenance.attribute.v1.MsgAddAttributeResponse addAttribute(io.provenance.attribute.v1.MsgAddAttributeRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddAttributeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateAttribute defines a method to verify a particular invariance.
     * </pre>
     */
    public io.provenance.attribute.v1.MsgUpdateAttributeResponse updateAttribute(io.provenance.attribute.v1.MsgUpdateAttributeRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpdateAttributeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteAttribute defines a method to verify a particular invariance.
     * </pre>
     */
    public io.provenance.attribute.v1.MsgDeleteAttributeResponse deleteAttribute(io.provenance.attribute.v1.MsgDeleteAttributeRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteAttributeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteDistinctAttribute defines a method to verify a particular invariance.
     * </pre>
     */
    public io.provenance.attribute.v1.MsgDeleteDistinctAttributeResponse deleteDistinctAttribute(io.provenance.attribute.v1.MsgDeleteDistinctAttributeRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteDistinctAttributeMethod(), getCallOptions(), request);
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
     * AddAttribute defines a method to verify a particular invariance.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.attribute.v1.MsgAddAttributeResponse> addAttribute(
        io.provenance.attribute.v1.MsgAddAttributeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddAttributeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateAttribute defines a method to verify a particular invariance.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.attribute.v1.MsgUpdateAttributeResponse> updateAttribute(
        io.provenance.attribute.v1.MsgUpdateAttributeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateAttributeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteAttribute defines a method to verify a particular invariance.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.attribute.v1.MsgDeleteAttributeResponse> deleteAttribute(
        io.provenance.attribute.v1.MsgDeleteAttributeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteAttributeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteDistinctAttribute defines a method to verify a particular invariance.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.attribute.v1.MsgDeleteDistinctAttributeResponse> deleteDistinctAttribute(
        io.provenance.attribute.v1.MsgDeleteDistinctAttributeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteDistinctAttributeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_ATTRIBUTE = 0;
  private static final int METHODID_UPDATE_ATTRIBUTE = 1;
  private static final int METHODID_DELETE_ATTRIBUTE = 2;
  private static final int METHODID_DELETE_DISTINCT_ATTRIBUTE = 3;

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
        case METHODID_ADD_ATTRIBUTE:
          serviceImpl.addAttribute((io.provenance.attribute.v1.MsgAddAttributeRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.attribute.v1.MsgAddAttributeResponse>) responseObserver);
          break;
        case METHODID_UPDATE_ATTRIBUTE:
          serviceImpl.updateAttribute((io.provenance.attribute.v1.MsgUpdateAttributeRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.attribute.v1.MsgUpdateAttributeResponse>) responseObserver);
          break;
        case METHODID_DELETE_ATTRIBUTE:
          serviceImpl.deleteAttribute((io.provenance.attribute.v1.MsgDeleteAttributeRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.attribute.v1.MsgDeleteAttributeResponse>) responseObserver);
          break;
        case METHODID_DELETE_DISTINCT_ATTRIBUTE:
          serviceImpl.deleteDistinctAttribute((io.provenance.attribute.v1.MsgDeleteDistinctAttributeRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.attribute.v1.MsgDeleteDistinctAttributeResponse>) responseObserver);
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
      return io.provenance.attribute.v1.Tx.getDescriptor();
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
              .addMethod(getAddAttributeMethod())
              .addMethod(getUpdateAttributeMethod())
              .addMethod(getDeleteAttributeMethod())
              .addMethod(getDeleteDistinctAttributeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
