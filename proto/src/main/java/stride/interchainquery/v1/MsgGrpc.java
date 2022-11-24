package stride.interchainquery.v1;

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
 * Msg defines the interchainquery Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: stride/interchainquery/v1/messages.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "stride.interchainquery.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<stride.interchainquery.v1.Messages.MsgSubmitQueryResponse,
      stride.interchainquery.v1.Messages.MsgSubmitQueryResponseResponse> getSubmitQueryResponseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitQueryResponse",
      requestType = stride.interchainquery.v1.Messages.MsgSubmitQueryResponse.class,
      responseType = stride.interchainquery.v1.Messages.MsgSubmitQueryResponseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.interchainquery.v1.Messages.MsgSubmitQueryResponse,
      stride.interchainquery.v1.Messages.MsgSubmitQueryResponseResponse> getSubmitQueryResponseMethod() {
    io.grpc.MethodDescriptor<stride.interchainquery.v1.Messages.MsgSubmitQueryResponse, stride.interchainquery.v1.Messages.MsgSubmitQueryResponseResponse> getSubmitQueryResponseMethod;
    if ((getSubmitQueryResponseMethod = MsgGrpc.getSubmitQueryResponseMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSubmitQueryResponseMethod = MsgGrpc.getSubmitQueryResponseMethod) == null) {
          MsgGrpc.getSubmitQueryResponseMethod = getSubmitQueryResponseMethod =
              io.grpc.MethodDescriptor.<stride.interchainquery.v1.Messages.MsgSubmitQueryResponse, stride.interchainquery.v1.Messages.MsgSubmitQueryResponseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubmitQueryResponse"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.interchainquery.v1.Messages.MsgSubmitQueryResponse.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.interchainquery.v1.Messages.MsgSubmitQueryResponseResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SubmitQueryResponse"))
              .build();
        }
      }
    }
    return getSubmitQueryResponseMethod;
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
   * Msg defines the interchainquery Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * SubmitQueryResponse defines a method for submit query responses.
     * </pre>
     */
    public void submitQueryResponse(stride.interchainquery.v1.Messages.MsgSubmitQueryResponse request,
        io.grpc.stub.StreamObserver<stride.interchainquery.v1.Messages.MsgSubmitQueryResponseResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubmitQueryResponseMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSubmitQueryResponseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.interchainquery.v1.Messages.MsgSubmitQueryResponse,
                stride.interchainquery.v1.Messages.MsgSubmitQueryResponseResponse>(
                  this, METHODID_SUBMIT_QUERY_RESPONSE)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the interchainquery Msg service.
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
     * SubmitQueryResponse defines a method for submit query responses.
     * </pre>
     */
    public void submitQueryResponse(stride.interchainquery.v1.Messages.MsgSubmitQueryResponse request,
        io.grpc.stub.StreamObserver<stride.interchainquery.v1.Messages.MsgSubmitQueryResponseResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubmitQueryResponseMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the interchainquery Msg service.
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
     * SubmitQueryResponse defines a method for submit query responses.
     * </pre>
     */
    public stride.interchainquery.v1.Messages.MsgSubmitQueryResponseResponse submitQueryResponse(stride.interchainquery.v1.Messages.MsgSubmitQueryResponse request) {
      return blockingUnaryCall(
          getChannel(), getSubmitQueryResponseMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the interchainquery Msg service.
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
     * SubmitQueryResponse defines a method for submit query responses.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.interchainquery.v1.Messages.MsgSubmitQueryResponseResponse> submitQueryResponse(
        stride.interchainquery.v1.Messages.MsgSubmitQueryResponse request) {
      return futureUnaryCall(
          getChannel().newCall(getSubmitQueryResponseMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SUBMIT_QUERY_RESPONSE = 0;

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
        case METHODID_SUBMIT_QUERY_RESPONSE:
          serviceImpl.submitQueryResponse((stride.interchainquery.v1.Messages.MsgSubmitQueryResponse) request,
              (io.grpc.stub.StreamObserver<stride.interchainquery.v1.Messages.MsgSubmitQueryResponseResponse>) responseObserver);
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
      return stride.interchainquery.v1.Messages.getDescriptor();
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
              .addMethod(getSubmitQueryResponseMethod())
              .build();
        }
      }
    }
    return result;
  }
}
