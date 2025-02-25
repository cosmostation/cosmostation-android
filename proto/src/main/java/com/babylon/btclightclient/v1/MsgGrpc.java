package com.babylon.btclightclient.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: babylon/btclightclient/v1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "babylon.btclightclient.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.TxProto.MsgInsertHeaders,
      com.babylon.btclightclient.v1.TxProto.MsgInsertHeadersResponse> getInsertHeadersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InsertHeaders",
      requestType = com.babylon.btclightclient.v1.TxProto.MsgInsertHeaders.class,
      responseType = com.babylon.btclightclient.v1.TxProto.MsgInsertHeadersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.TxProto.MsgInsertHeaders,
      com.babylon.btclightclient.v1.TxProto.MsgInsertHeadersResponse> getInsertHeadersMethod() {
    io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.TxProto.MsgInsertHeaders, com.babylon.btclightclient.v1.TxProto.MsgInsertHeadersResponse> getInsertHeadersMethod;
    if ((getInsertHeadersMethod = MsgGrpc.getInsertHeadersMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getInsertHeadersMethod = MsgGrpc.getInsertHeadersMethod) == null) {
          MsgGrpc.getInsertHeadersMethod = getInsertHeadersMethod =
              io.grpc.MethodDescriptor.<com.babylon.btclightclient.v1.TxProto.MsgInsertHeaders, com.babylon.btclightclient.v1.TxProto.MsgInsertHeadersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InsertHeaders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.TxProto.MsgInsertHeaders.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.TxProto.MsgInsertHeadersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("InsertHeaders"))
              .build();
        }
      }
    }
    return getInsertHeadersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.TxProto.MsgUpdateParams,
      com.babylon.btclightclient.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateParams",
      requestType = com.babylon.btclightclient.v1.TxProto.MsgUpdateParams.class,
      responseType = com.babylon.btclightclient.v1.TxProto.MsgUpdateParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.TxProto.MsgUpdateParams,
      com.babylon.btclightclient.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod() {
    io.grpc.MethodDescriptor<com.babylon.btclightclient.v1.TxProto.MsgUpdateParams, com.babylon.btclightclient.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;
    if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
          MsgGrpc.getUpdateParamsMethod = getUpdateParamsMethod =
              io.grpc.MethodDescriptor.<com.babylon.btclightclient.v1.TxProto.MsgUpdateParams, com.babylon.btclightclient.v1.TxProto.MsgUpdateParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.TxProto.MsgUpdateParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btclightclient.v1.TxProto.MsgUpdateParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateParams"))
              .build();
        }
      }
    }
    return getUpdateParamsMethod;
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
  public interface AsyncService {

    /**
     * <pre>
     * InsertHeaders adds a batch of headers to the BTC light client chain
     * </pre>
     */
    default void insertHeaders(com.babylon.btclightclient.v1.TxProto.MsgInsertHeaders request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.TxProto.MsgInsertHeadersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInsertHeadersMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateParams defines a method for updating btc light client module parameters.
     * </pre>
     */
    default void updateParams(com.babylon.btclightclient.v1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateParamsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MsgGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the Msg service.
   * </pre>
   */
  public static final class MsgStub
      extends io.grpc.stub.AbstractAsyncStub<MsgStub> {
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
     * InsertHeaders adds a batch of headers to the BTC light client chain
     * </pre>
     */
    public void insertHeaders(com.babylon.btclightclient.v1.TxProto.MsgInsertHeaders request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.TxProto.MsgInsertHeadersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInsertHeadersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateParams defines a method for updating btc light client module parameters.
     * </pre>
     */
    public void updateParams(com.babylon.btclightclient.v1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the Msg service.
   * </pre>
   */
  public static final class MsgBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MsgBlockingStub> {
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
     * InsertHeaders adds a batch of headers to the BTC light client chain
     * </pre>
     */
    public com.babylon.btclightclient.v1.TxProto.MsgInsertHeadersResponse insertHeaders(com.babylon.btclightclient.v1.TxProto.MsgInsertHeaders request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInsertHeadersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateParams defines a method for updating btc light client module parameters.
     * </pre>
     */
    public com.babylon.btclightclient.v1.TxProto.MsgUpdateParamsResponse updateParams(com.babylon.btclightclient.v1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the Msg service.
   * </pre>
   */
  public static final class MsgFutureStub
      extends io.grpc.stub.AbstractFutureStub<MsgFutureStub> {
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
     * InsertHeaders adds a batch of headers to the BTC light client chain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btclightclient.v1.TxProto.MsgInsertHeadersResponse> insertHeaders(
        com.babylon.btclightclient.v1.TxProto.MsgInsertHeaders request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInsertHeadersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateParams defines a method for updating btc light client module parameters.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btclightclient.v1.TxProto.MsgUpdateParamsResponse> updateParams(
        com.babylon.btclightclient.v1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INSERT_HEADERS = 0;
  private static final int METHODID_UPDATE_PARAMS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_INSERT_HEADERS:
          serviceImpl.insertHeaders((com.babylon.btclightclient.v1.TxProto.MsgInsertHeaders) request,
              (io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.TxProto.MsgInsertHeadersResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PARAMS:
          serviceImpl.updateParams((com.babylon.btclightclient.v1.TxProto.MsgUpdateParams) request,
              (io.grpc.stub.StreamObserver<com.babylon.btclightclient.v1.TxProto.MsgUpdateParamsResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getInsertHeadersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btclightclient.v1.TxProto.MsgInsertHeaders,
              com.babylon.btclightclient.v1.TxProto.MsgInsertHeadersResponse>(
                service, METHODID_INSERT_HEADERS)))
        .addMethod(
          getUpdateParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btclightclient.v1.TxProto.MsgUpdateParams,
              com.babylon.btclightclient.v1.TxProto.MsgUpdateParamsResponse>(
                service, METHODID_UPDATE_PARAMS)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.babylon.btclightclient.v1.TxProto.getDescriptor();
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
              .addMethod(getInsertHeadersMethod())
              .addMethod(getUpdateParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
