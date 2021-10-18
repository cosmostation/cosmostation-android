package shentu.cvm.v1alpha1;

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
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: shentu/cvm/v1alpha1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "shentu.cvm.v1alpha1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.Tx.MsgCall,
      shentu.cvm.v1alpha1.Tx.MsgCallResponse> getCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Call",
      requestType = shentu.cvm.v1alpha1.Tx.MsgCall.class,
      responseType = shentu.cvm.v1alpha1.Tx.MsgCallResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.Tx.MsgCall,
      shentu.cvm.v1alpha1.Tx.MsgCallResponse> getCallMethod() {
    io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.Tx.MsgCall, shentu.cvm.v1alpha1.Tx.MsgCallResponse> getCallMethod;
    if ((getCallMethod = MsgGrpc.getCallMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCallMethod = MsgGrpc.getCallMethod) == null) {
          MsgGrpc.getCallMethod = getCallMethod =
              io.grpc.MethodDescriptor.<shentu.cvm.v1alpha1.Tx.MsgCall, shentu.cvm.v1alpha1.Tx.MsgCallResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Call"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cvm.v1alpha1.Tx.MsgCall.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cvm.v1alpha1.Tx.MsgCallResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Call"))
              .build();
        }
      }
    }
    return getCallMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.Tx.MsgDeploy,
      shentu.cvm.v1alpha1.Tx.MsgDeployResponse> getDeployMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deploy",
      requestType = shentu.cvm.v1alpha1.Tx.MsgDeploy.class,
      responseType = shentu.cvm.v1alpha1.Tx.MsgDeployResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.Tx.MsgDeploy,
      shentu.cvm.v1alpha1.Tx.MsgDeployResponse> getDeployMethod() {
    io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.Tx.MsgDeploy, shentu.cvm.v1alpha1.Tx.MsgDeployResponse> getDeployMethod;
    if ((getDeployMethod = MsgGrpc.getDeployMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeployMethod = MsgGrpc.getDeployMethod) == null) {
          MsgGrpc.getDeployMethod = getDeployMethod =
              io.grpc.MethodDescriptor.<shentu.cvm.v1alpha1.Tx.MsgDeploy, shentu.cvm.v1alpha1.Tx.MsgDeployResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deploy"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cvm.v1alpha1.Tx.MsgDeploy.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cvm.v1alpha1.Tx.MsgDeployResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Deploy"))
              .build();
        }
      }
    }
    return getDeployMethod;
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
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     */
    public void call(shentu.cvm.v1alpha1.Tx.MsgCall request,
        io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.Tx.MsgCallResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCallMethod(), responseObserver);
    }

    /**
     */
    public void deploy(shentu.cvm.v1alpha1.Tx.MsgDeploy request,
        io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.Tx.MsgDeployResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeployMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCallMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cvm.v1alpha1.Tx.MsgCall,
                shentu.cvm.v1alpha1.Tx.MsgCallResponse>(
                  this, METHODID_CALL)))
          .addMethod(
            getDeployMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cvm.v1alpha1.Tx.MsgDeploy,
                shentu.cvm.v1alpha1.Tx.MsgDeployResponse>(
                  this, METHODID_DEPLOY)))
          .build();
    }
  }

  /**
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
    public void call(shentu.cvm.v1alpha1.Tx.MsgCall request,
        io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.Tx.MsgCallResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCallMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deploy(shentu.cvm.v1alpha1.Tx.MsgDeploy request,
        io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.Tx.MsgDeployResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeployMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
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
    public shentu.cvm.v1alpha1.Tx.MsgCallResponse call(shentu.cvm.v1alpha1.Tx.MsgCall request) {
      return blockingUnaryCall(
          getChannel(), getCallMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.cvm.v1alpha1.Tx.MsgDeployResponse deploy(shentu.cvm.v1alpha1.Tx.MsgDeploy request) {
      return blockingUnaryCall(
          getChannel(), getDeployMethod(), getCallOptions(), request);
    }
  }

  /**
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
    public com.google.common.util.concurrent.ListenableFuture<shentu.cvm.v1alpha1.Tx.MsgCallResponse> call(
        shentu.cvm.v1alpha1.Tx.MsgCall request) {
      return futureUnaryCall(
          getChannel().newCall(getCallMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.cvm.v1alpha1.Tx.MsgDeployResponse> deploy(
        shentu.cvm.v1alpha1.Tx.MsgDeploy request) {
      return futureUnaryCall(
          getChannel().newCall(getDeployMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CALL = 0;
  private static final int METHODID_DEPLOY = 1;

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
        case METHODID_CALL:
          serviceImpl.call((shentu.cvm.v1alpha1.Tx.MsgCall) request,
              (io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.Tx.MsgCallResponse>) responseObserver);
          break;
        case METHODID_DEPLOY:
          serviceImpl.deploy((shentu.cvm.v1alpha1.Tx.MsgDeploy) request,
              (io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.Tx.MsgDeployResponse>) responseObserver);
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
      return shentu.cvm.v1alpha1.Tx.getDescriptor();
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
              .addMethod(getCallMethod())
              .addMethod(getDeployMethod())
              .build();
        }
      }
    }
    return result;
  }
}
