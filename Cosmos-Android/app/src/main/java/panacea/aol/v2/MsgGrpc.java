package panacea.aol.v2;

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
    comments = "Source: panacea/aol/v2/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "panacea.aol.v2.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<panacea.aol.v2.MsgCreateTopic,
      panacea.aol.v2.MsgCreateTopicResponse> getCreateTopicMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateTopic",
      requestType = panacea.aol.v2.MsgCreateTopic.class,
      responseType = panacea.aol.v2.MsgCreateTopicResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<panacea.aol.v2.MsgCreateTopic,
      panacea.aol.v2.MsgCreateTopicResponse> getCreateTopicMethod() {
    io.grpc.MethodDescriptor<panacea.aol.v2.MsgCreateTopic, panacea.aol.v2.MsgCreateTopicResponse> getCreateTopicMethod;
    if ((getCreateTopicMethod = MsgGrpc.getCreateTopicMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateTopicMethod = MsgGrpc.getCreateTopicMethod) == null) {
          MsgGrpc.getCreateTopicMethod = getCreateTopicMethod =
              io.grpc.MethodDescriptor.<panacea.aol.v2.MsgCreateTopic, panacea.aol.v2.MsgCreateTopicResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateTopic"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.MsgCreateTopic.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.MsgCreateTopicResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateTopic"))
              .build();
        }
      }
    }
    return getCreateTopicMethod;
  }

  private static volatile io.grpc.MethodDescriptor<panacea.aol.v2.MsgAddWriter,
      panacea.aol.v2.MsgAddWriterResponse> getAddWriterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddWriter",
      requestType = panacea.aol.v2.MsgAddWriter.class,
      responseType = panacea.aol.v2.MsgAddWriterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<panacea.aol.v2.MsgAddWriter,
      panacea.aol.v2.MsgAddWriterResponse> getAddWriterMethod() {
    io.grpc.MethodDescriptor<panacea.aol.v2.MsgAddWriter, panacea.aol.v2.MsgAddWriterResponse> getAddWriterMethod;
    if ((getAddWriterMethod = MsgGrpc.getAddWriterMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddWriterMethod = MsgGrpc.getAddWriterMethod) == null) {
          MsgGrpc.getAddWriterMethod = getAddWriterMethod =
              io.grpc.MethodDescriptor.<panacea.aol.v2.MsgAddWriter, panacea.aol.v2.MsgAddWriterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddWriter"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.MsgAddWriter.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.MsgAddWriterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddWriter"))
              .build();
        }
      }
    }
    return getAddWriterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<panacea.aol.v2.MsgDeleteWriter,
      panacea.aol.v2.MsgDeleteWriterResponse> getDeleteWriterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteWriter",
      requestType = panacea.aol.v2.MsgDeleteWriter.class,
      responseType = panacea.aol.v2.MsgDeleteWriterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<panacea.aol.v2.MsgDeleteWriter,
      panacea.aol.v2.MsgDeleteWriterResponse> getDeleteWriterMethod() {
    io.grpc.MethodDescriptor<panacea.aol.v2.MsgDeleteWriter, panacea.aol.v2.MsgDeleteWriterResponse> getDeleteWriterMethod;
    if ((getDeleteWriterMethod = MsgGrpc.getDeleteWriterMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteWriterMethod = MsgGrpc.getDeleteWriterMethod) == null) {
          MsgGrpc.getDeleteWriterMethod = getDeleteWriterMethod =
              io.grpc.MethodDescriptor.<panacea.aol.v2.MsgDeleteWriter, panacea.aol.v2.MsgDeleteWriterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteWriter"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.MsgDeleteWriter.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.MsgDeleteWriterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteWriter"))
              .build();
        }
      }
    }
    return getDeleteWriterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<panacea.aol.v2.MsgAddRecord,
      panacea.aol.v2.MsgAddRecordResponse> getAddRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddRecord",
      requestType = panacea.aol.v2.MsgAddRecord.class,
      responseType = panacea.aol.v2.MsgAddRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<panacea.aol.v2.MsgAddRecord,
      panacea.aol.v2.MsgAddRecordResponse> getAddRecordMethod() {
    io.grpc.MethodDescriptor<panacea.aol.v2.MsgAddRecord, panacea.aol.v2.MsgAddRecordResponse> getAddRecordMethod;
    if ((getAddRecordMethod = MsgGrpc.getAddRecordMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddRecordMethod = MsgGrpc.getAddRecordMethod) == null) {
          MsgGrpc.getAddRecordMethod = getAddRecordMethod =
              io.grpc.MethodDescriptor.<panacea.aol.v2.MsgAddRecord, panacea.aol.v2.MsgAddRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.MsgAddRecord.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.MsgAddRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddRecord"))
              .build();
        }
      }
    }
    return getAddRecordMethod;
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
     * CreateTopic defines a method for creating a topic.
     * </pre>
     */
    public void createTopic(panacea.aol.v2.MsgCreateTopic request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.MsgCreateTopicResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateTopicMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddWriter defines a method for adding a writer to the topic.
     * </pre>
     */
    public void addWriter(panacea.aol.v2.MsgAddWriter request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.MsgAddWriterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddWriterMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteWriter defines a method for deleting a writer to the topic.
     * </pre>
     */
    public void deleteWriter(panacea.aol.v2.MsgDeleteWriter request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.MsgDeleteWriterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteWriterMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddRecord defines a method for adding a record to the topic with the writer.
     * </pre>
     */
    public void addRecord(panacea.aol.v2.MsgAddRecord request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.MsgAddRecordResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddRecordMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateTopicMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                panacea.aol.v2.MsgCreateTopic,
                panacea.aol.v2.MsgCreateTopicResponse>(
                  this, METHODID_CREATE_TOPIC)))
          .addMethod(
            getAddWriterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                panacea.aol.v2.MsgAddWriter,
                panacea.aol.v2.MsgAddWriterResponse>(
                  this, METHODID_ADD_WRITER)))
          .addMethod(
            getDeleteWriterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                panacea.aol.v2.MsgDeleteWriter,
                panacea.aol.v2.MsgDeleteWriterResponse>(
                  this, METHODID_DELETE_WRITER)))
          .addMethod(
            getAddRecordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                panacea.aol.v2.MsgAddRecord,
                panacea.aol.v2.MsgAddRecordResponse>(
                  this, METHODID_ADD_RECORD)))
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
     * CreateTopic defines a method for creating a topic.
     * </pre>
     */
    public void createTopic(panacea.aol.v2.MsgCreateTopic request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.MsgCreateTopicResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateTopicMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddWriter defines a method for adding a writer to the topic.
     * </pre>
     */
    public void addWriter(panacea.aol.v2.MsgAddWriter request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.MsgAddWriterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddWriterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteWriter defines a method for deleting a writer to the topic.
     * </pre>
     */
    public void deleteWriter(panacea.aol.v2.MsgDeleteWriter request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.MsgDeleteWriterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteWriterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddRecord defines a method for adding a record to the topic with the writer.
     * </pre>
     */
    public void addRecord(panacea.aol.v2.MsgAddRecord request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.MsgAddRecordResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddRecordMethod(), getCallOptions()), request, responseObserver);
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
     * CreateTopic defines a method for creating a topic.
     * </pre>
     */
    public panacea.aol.v2.MsgCreateTopicResponse createTopic(panacea.aol.v2.MsgCreateTopic request) {
      return blockingUnaryCall(
          getChannel(), getCreateTopicMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddWriter defines a method for adding a writer to the topic.
     * </pre>
     */
    public panacea.aol.v2.MsgAddWriterResponse addWriter(panacea.aol.v2.MsgAddWriter request) {
      return blockingUnaryCall(
          getChannel(), getAddWriterMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteWriter defines a method for deleting a writer to the topic.
     * </pre>
     */
    public panacea.aol.v2.MsgDeleteWriterResponse deleteWriter(panacea.aol.v2.MsgDeleteWriter request) {
      return blockingUnaryCall(
          getChannel(), getDeleteWriterMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddRecord defines a method for adding a record to the topic with the writer.
     * </pre>
     */
    public panacea.aol.v2.MsgAddRecordResponse addRecord(panacea.aol.v2.MsgAddRecord request) {
      return blockingUnaryCall(
          getChannel(), getAddRecordMethod(), getCallOptions(), request);
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
     * CreateTopic defines a method for creating a topic.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<panacea.aol.v2.MsgCreateTopicResponse> createTopic(
        panacea.aol.v2.MsgCreateTopic request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateTopicMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddWriter defines a method for adding a writer to the topic.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<panacea.aol.v2.MsgAddWriterResponse> addWriter(
        panacea.aol.v2.MsgAddWriter request) {
      return futureUnaryCall(
          getChannel().newCall(getAddWriterMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteWriter defines a method for deleting a writer to the topic.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<panacea.aol.v2.MsgDeleteWriterResponse> deleteWriter(
        panacea.aol.v2.MsgDeleteWriter request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteWriterMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddRecord defines a method for adding a record to the topic with the writer.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<panacea.aol.v2.MsgAddRecordResponse> addRecord(
        panacea.aol.v2.MsgAddRecord request) {
      return futureUnaryCall(
          getChannel().newCall(getAddRecordMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_TOPIC = 0;
  private static final int METHODID_ADD_WRITER = 1;
  private static final int METHODID_DELETE_WRITER = 2;
  private static final int METHODID_ADD_RECORD = 3;

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
        case METHODID_CREATE_TOPIC:
          serviceImpl.createTopic((panacea.aol.v2.MsgCreateTopic) request,
              (io.grpc.stub.StreamObserver<panacea.aol.v2.MsgCreateTopicResponse>) responseObserver);
          break;
        case METHODID_ADD_WRITER:
          serviceImpl.addWriter((panacea.aol.v2.MsgAddWriter) request,
              (io.grpc.stub.StreamObserver<panacea.aol.v2.MsgAddWriterResponse>) responseObserver);
          break;
        case METHODID_DELETE_WRITER:
          serviceImpl.deleteWriter((panacea.aol.v2.MsgDeleteWriter) request,
              (io.grpc.stub.StreamObserver<panacea.aol.v2.MsgDeleteWriterResponse>) responseObserver);
          break;
        case METHODID_ADD_RECORD:
          serviceImpl.addRecord((panacea.aol.v2.MsgAddRecord) request,
              (io.grpc.stub.StreamObserver<panacea.aol.v2.MsgAddRecordResponse>) responseObserver);
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
      return panacea.aol.v2.Tx.getDescriptor();
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
              .addMethod(getCreateTopicMethod())
              .addMethod(getAddWriterMethod())
              .addMethod(getDeleteWriterMethod())
              .addMethod(getAddRecordMethod())
              .build();
        }
      }
    }
    return result;
  }
}
