package akash.provider.v1beta1;

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
    comments = "Source: akash/provider/v1beta1/provider.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "akash.provider.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<akash.provider.v1beta1.ProviderOuterClass.MsgCreateProvider,
      akash.provider.v1beta1.ProviderOuterClass.MsgCreateProviderResponse> getCreateProviderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateProvider",
      requestType = akash.provider.v1beta1.ProviderOuterClass.MsgCreateProvider.class,
      responseType = akash.provider.v1beta1.ProviderOuterClass.MsgCreateProviderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.provider.v1beta1.ProviderOuterClass.MsgCreateProvider,
      akash.provider.v1beta1.ProviderOuterClass.MsgCreateProviderResponse> getCreateProviderMethod() {
    io.grpc.MethodDescriptor<akash.provider.v1beta1.ProviderOuterClass.MsgCreateProvider, akash.provider.v1beta1.ProviderOuterClass.MsgCreateProviderResponse> getCreateProviderMethod;
    if ((getCreateProviderMethod = MsgGrpc.getCreateProviderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateProviderMethod = MsgGrpc.getCreateProviderMethod) == null) {
          MsgGrpc.getCreateProviderMethod = getCreateProviderMethod =
              io.grpc.MethodDescriptor.<akash.provider.v1beta1.ProviderOuterClass.MsgCreateProvider, akash.provider.v1beta1.ProviderOuterClass.MsgCreateProviderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateProvider"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.provider.v1beta1.ProviderOuterClass.MsgCreateProvider.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.provider.v1beta1.ProviderOuterClass.MsgCreateProviderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateProvider"))
              .build();
        }
      }
    }
    return getCreateProviderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProvider,
      akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProviderResponse> getUpdateProviderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateProvider",
      requestType = akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProvider.class,
      responseType = akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProviderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProvider,
      akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProviderResponse> getUpdateProviderMethod() {
    io.grpc.MethodDescriptor<akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProvider, akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProviderResponse> getUpdateProviderMethod;
    if ((getUpdateProviderMethod = MsgGrpc.getUpdateProviderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateProviderMethod = MsgGrpc.getUpdateProviderMethod) == null) {
          MsgGrpc.getUpdateProviderMethod = getUpdateProviderMethod =
              io.grpc.MethodDescriptor.<akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProvider, akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProviderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateProvider"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProvider.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProviderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateProvider"))
              .build();
        }
      }
    }
    return getUpdateProviderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProvider,
      akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProviderResponse> getDeleteProviderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteProvider",
      requestType = akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProvider.class,
      responseType = akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProviderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProvider,
      akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProviderResponse> getDeleteProviderMethod() {
    io.grpc.MethodDescriptor<akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProvider, akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProviderResponse> getDeleteProviderMethod;
    if ((getDeleteProviderMethod = MsgGrpc.getDeleteProviderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteProviderMethod = MsgGrpc.getDeleteProviderMethod) == null) {
          MsgGrpc.getDeleteProviderMethod = getDeleteProviderMethod =
              io.grpc.MethodDescriptor.<akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProvider, akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProviderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteProvider"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProvider.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProviderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteProvider"))
              .build();
        }
      }
    }
    return getDeleteProviderMethod;
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
     * CreateProvider defines a method that creates a provider given the proper inputs
     * </pre>
     */
    public void createProvider(akash.provider.v1beta1.ProviderOuterClass.MsgCreateProvider request,
        io.grpc.stub.StreamObserver<akash.provider.v1beta1.ProviderOuterClass.MsgCreateProviderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateProviderMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateProvider defines a method that updates a provider given the proper inputs
     * </pre>
     */
    public void updateProvider(akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProvider request,
        io.grpc.stub.StreamObserver<akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProviderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateProviderMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteProvider defines a method that deletes a provider given the proper inputs
     * </pre>
     */
    public void deleteProvider(akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProvider request,
        io.grpc.stub.StreamObserver<akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProviderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteProviderMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateProviderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.provider.v1beta1.ProviderOuterClass.MsgCreateProvider,
                akash.provider.v1beta1.ProviderOuterClass.MsgCreateProviderResponse>(
                  this, METHODID_CREATE_PROVIDER)))
          .addMethod(
            getUpdateProviderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProvider,
                akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProviderResponse>(
                  this, METHODID_UPDATE_PROVIDER)))
          .addMethod(
            getDeleteProviderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProvider,
                akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProviderResponse>(
                  this, METHODID_DELETE_PROVIDER)))
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
     * CreateProvider defines a method that creates a provider given the proper inputs
     * </pre>
     */
    public void createProvider(akash.provider.v1beta1.ProviderOuterClass.MsgCreateProvider request,
        io.grpc.stub.StreamObserver<akash.provider.v1beta1.ProviderOuterClass.MsgCreateProviderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateProviderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateProvider defines a method that updates a provider given the proper inputs
     * </pre>
     */
    public void updateProvider(akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProvider request,
        io.grpc.stub.StreamObserver<akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProviderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateProviderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteProvider defines a method that deletes a provider given the proper inputs
     * </pre>
     */
    public void deleteProvider(akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProvider request,
        io.grpc.stub.StreamObserver<akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProviderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteProviderMethod(), getCallOptions()), request, responseObserver);
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
     * CreateProvider defines a method that creates a provider given the proper inputs
     * </pre>
     */
    public akash.provider.v1beta1.ProviderOuterClass.MsgCreateProviderResponse createProvider(akash.provider.v1beta1.ProviderOuterClass.MsgCreateProvider request) {
      return blockingUnaryCall(
          getChannel(), getCreateProviderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateProvider defines a method that updates a provider given the proper inputs
     * </pre>
     */
    public akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProviderResponse updateProvider(akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProvider request) {
      return blockingUnaryCall(
          getChannel(), getUpdateProviderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteProvider defines a method that deletes a provider given the proper inputs
     * </pre>
     */
    public akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProviderResponse deleteProvider(akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProvider request) {
      return blockingUnaryCall(
          getChannel(), getDeleteProviderMethod(), getCallOptions(), request);
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
     * CreateProvider defines a method that creates a provider given the proper inputs
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.provider.v1beta1.ProviderOuterClass.MsgCreateProviderResponse> createProvider(
        akash.provider.v1beta1.ProviderOuterClass.MsgCreateProvider request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateProviderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateProvider defines a method that updates a provider given the proper inputs
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProviderResponse> updateProvider(
        akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProvider request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateProviderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteProvider defines a method that deletes a provider given the proper inputs
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProviderResponse> deleteProvider(
        akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProvider request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteProviderMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_PROVIDER = 0;
  private static final int METHODID_UPDATE_PROVIDER = 1;
  private static final int METHODID_DELETE_PROVIDER = 2;

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
        case METHODID_CREATE_PROVIDER:
          serviceImpl.createProvider((akash.provider.v1beta1.ProviderOuterClass.MsgCreateProvider) request,
              (io.grpc.stub.StreamObserver<akash.provider.v1beta1.ProviderOuterClass.MsgCreateProviderResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PROVIDER:
          serviceImpl.updateProvider((akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProvider) request,
              (io.grpc.stub.StreamObserver<akash.provider.v1beta1.ProviderOuterClass.MsgUpdateProviderResponse>) responseObserver);
          break;
        case METHODID_DELETE_PROVIDER:
          serviceImpl.deleteProvider((akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProvider) request,
              (io.grpc.stub.StreamObserver<akash.provider.v1beta1.ProviderOuterClass.MsgDeleteProviderResponse>) responseObserver);
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
      return akash.provider.v1beta1.ProviderOuterClass.getDescriptor();
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
              .addMethod(getCreateProviderMethod())
              .addMethod(getUpdateProviderMethod())
              .addMethod(getDeleteProviderMethod())
              .build();
        }
      }
    }
    return result;
  }
}
