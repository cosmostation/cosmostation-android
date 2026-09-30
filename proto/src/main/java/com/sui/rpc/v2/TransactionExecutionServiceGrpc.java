package com.sui.rpc.v2;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: sui/rpc/v2/transaction_execution_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TransactionExecutionServiceGrpc {

  private TransactionExecutionServiceGrpc() {}

  public static final String SERVICE_NAME = "sui.rpc.v2.TransactionExecutionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionRequest,
      com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionResponse> getExecuteTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExecuteTransaction",
      requestType = com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionRequest.class,
      responseType = com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionRequest,
      com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionResponse> getExecuteTransactionMethod() {
    io.grpc.MethodDescriptor<com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionRequest, com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionResponse> getExecuteTransactionMethod;
    if ((getExecuteTransactionMethod = TransactionExecutionServiceGrpc.getExecuteTransactionMethod) == null) {
      synchronized (TransactionExecutionServiceGrpc.class) {
        if ((getExecuteTransactionMethod = TransactionExecutionServiceGrpc.getExecuteTransactionMethod) == null) {
          TransactionExecutionServiceGrpc.getExecuteTransactionMethod = getExecuteTransactionMethod =
              io.grpc.MethodDescriptor.<com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionRequest, com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExecuteTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransactionExecutionServiceMethodDescriptorSupplier("ExecuteTransaction"))
              .build();
        }
      }
    }
    return getExecuteTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionRequest,
      com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionResponse> getSimulateTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SimulateTransaction",
      requestType = com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionRequest.class,
      responseType = com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionRequest,
      com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionResponse> getSimulateTransactionMethod() {
    io.grpc.MethodDescriptor<com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionRequest, com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionResponse> getSimulateTransactionMethod;
    if ((getSimulateTransactionMethod = TransactionExecutionServiceGrpc.getSimulateTransactionMethod) == null) {
      synchronized (TransactionExecutionServiceGrpc.class) {
        if ((getSimulateTransactionMethod = TransactionExecutionServiceGrpc.getSimulateTransactionMethod) == null) {
          TransactionExecutionServiceGrpc.getSimulateTransactionMethod = getSimulateTransactionMethod =
              io.grpc.MethodDescriptor.<com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionRequest, com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SimulateTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransactionExecutionServiceMethodDescriptorSupplier("SimulateTransaction"))
              .build();
        }
      }
    }
    return getSimulateTransactionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TransactionExecutionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransactionExecutionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransactionExecutionServiceStub>() {
        @java.lang.Override
        public TransactionExecutionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransactionExecutionServiceStub(channel, callOptions);
        }
      };
    return TransactionExecutionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TransactionExecutionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransactionExecutionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransactionExecutionServiceBlockingStub>() {
        @java.lang.Override
        public TransactionExecutionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransactionExecutionServiceBlockingStub(channel, callOptions);
        }
      };
    return TransactionExecutionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TransactionExecutionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransactionExecutionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransactionExecutionServiceFutureStub>() {
        @java.lang.Override
        public TransactionExecutionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransactionExecutionServiceFutureStub(channel, callOptions);
        }
      };
    return TransactionExecutionServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void executeTransaction(com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExecuteTransactionMethod(), responseObserver);
    }

    /**
     */
    default void simulateTransaction(com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSimulateTransactionMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service TransactionExecutionService.
   */
  public static abstract class TransactionExecutionServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return TransactionExecutionServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service TransactionExecutionService.
   */
  public static final class TransactionExecutionServiceStub
      extends io.grpc.stub.AbstractAsyncStub<TransactionExecutionServiceStub> {
    private TransactionExecutionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionExecutionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransactionExecutionServiceStub(channel, callOptions);
    }

    /**
     */
    public void executeTransaction(com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExecuteTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void simulateTransaction(com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSimulateTransactionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service TransactionExecutionService.
   */
  public static final class TransactionExecutionServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<TransactionExecutionServiceBlockingStub> {
    private TransactionExecutionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionExecutionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransactionExecutionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionResponse executeTransaction(com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExecuteTransactionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionResponse simulateTransaction(com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSimulateTransactionMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service TransactionExecutionService.
   */
  public static final class TransactionExecutionServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<TransactionExecutionServiceFutureStub> {
    private TransactionExecutionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionExecutionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransactionExecutionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionResponse> executeTransaction(
        com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExecuteTransactionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionResponse> simulateTransaction(
        com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSimulateTransactionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EXECUTE_TRANSACTION = 0;
  private static final int METHODID_SIMULATE_TRANSACTION = 1;

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
        case METHODID_EXECUTE_TRANSACTION:
          serviceImpl.executeTransaction((com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionRequest) request,
              (io.grpc.stub.StreamObserver<com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionResponse>) responseObserver);
          break;
        case METHODID_SIMULATE_TRANSACTION:
          serviceImpl.simulateTransaction((com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionRequest) request,
              (io.grpc.stub.StreamObserver<com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionResponse>) responseObserver);
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
          getExecuteTransactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionRequest,
              com.sui.rpc.v2.TransactionExecutionServiceProto.ExecuteTransactionResponse>(
                service, METHODID_EXECUTE_TRANSACTION)))
        .addMethod(
          getSimulateTransactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionRequest,
              com.sui.rpc.v2.TransactionExecutionServiceProto.SimulateTransactionResponse>(
                service, METHODID_SIMULATE_TRANSACTION)))
        .build();
  }

  private static abstract class TransactionExecutionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TransactionExecutionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.sui.rpc.v2.TransactionExecutionServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TransactionExecutionService");
    }
  }

  private static final class TransactionExecutionServiceFileDescriptorSupplier
      extends TransactionExecutionServiceBaseDescriptorSupplier {
    TransactionExecutionServiceFileDescriptorSupplier() {}
  }

  private static final class TransactionExecutionServiceMethodDescriptorSupplier
      extends TransactionExecutionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TransactionExecutionServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TransactionExecutionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TransactionExecutionServiceFileDescriptorSupplier())
              .addMethod(getExecuteTransactionMethod())
              .addMethod(getSimulateTransactionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
