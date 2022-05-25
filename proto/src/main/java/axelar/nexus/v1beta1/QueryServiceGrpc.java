package axelar.nexus.v1beta1;

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
 * QueryService defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: axelar/nexus/v1beta1/service.proto")
public final class QueryServiceGrpc {

  private QueryServiceGrpc() {}

  public static final String SERVICE_NAME = "axelar.nexus.v1beta1.QueryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Query.LatestDepositAddressRequest,
      axelar.nexus.v1beta1.Query.LatestDepositAddressResponse> getLatestDepositAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LatestDepositAddress",
      requestType = axelar.nexus.v1beta1.Query.LatestDepositAddressRequest.class,
      responseType = axelar.nexus.v1beta1.Query.LatestDepositAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Query.LatestDepositAddressRequest,
      axelar.nexus.v1beta1.Query.LatestDepositAddressResponse> getLatestDepositAddressMethod() {
    io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Query.LatestDepositAddressRequest, axelar.nexus.v1beta1.Query.LatestDepositAddressResponse> getLatestDepositAddressMethod;
    if ((getLatestDepositAddressMethod = QueryServiceGrpc.getLatestDepositAddressMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getLatestDepositAddressMethod = QueryServiceGrpc.getLatestDepositAddressMethod) == null) {
          QueryServiceGrpc.getLatestDepositAddressMethod = getLatestDepositAddressMethod =
              io.grpc.MethodDescriptor.<axelar.nexus.v1beta1.Query.LatestDepositAddressRequest, axelar.nexus.v1beta1.Query.LatestDepositAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LatestDepositAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.nexus.v1beta1.Query.LatestDepositAddressRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.nexus.v1beta1.Query.LatestDepositAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("LatestDepositAddress"))
              .build();
        }
      }
    }
    return getLatestDepositAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Query.TransfersForChainRequest,
      axelar.nexus.v1beta1.Query.TransfersForChainResponse> getTransfersForChainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TransfersForChain",
      requestType = axelar.nexus.v1beta1.Query.TransfersForChainRequest.class,
      responseType = axelar.nexus.v1beta1.Query.TransfersForChainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Query.TransfersForChainRequest,
      axelar.nexus.v1beta1.Query.TransfersForChainResponse> getTransfersForChainMethod() {
    io.grpc.MethodDescriptor<axelar.nexus.v1beta1.Query.TransfersForChainRequest, axelar.nexus.v1beta1.Query.TransfersForChainResponse> getTransfersForChainMethod;
    if ((getTransfersForChainMethod = QueryServiceGrpc.getTransfersForChainMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getTransfersForChainMethod = QueryServiceGrpc.getTransfersForChainMethod) == null) {
          QueryServiceGrpc.getTransfersForChainMethod = getTransfersForChainMethod =
              io.grpc.MethodDescriptor.<axelar.nexus.v1beta1.Query.TransfersForChainRequest, axelar.nexus.v1beta1.Query.TransfersForChainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TransfersForChain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.nexus.v1beta1.Query.TransfersForChainRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.nexus.v1beta1.Query.TransfersForChainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("TransfersForChain"))
              .build();
        }
      }
    }
    return getTransfersForChainMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QueryServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceStub>() {
        @java.lang.Override
        public QueryServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceStub(channel, callOptions);
        }
      };
    return QueryServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QueryServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceBlockingStub>() {
        @java.lang.Override
        public QueryServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceBlockingStub(channel, callOptions);
        }
      };
    return QueryServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QueryServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceFutureStub>() {
        @java.lang.Override
        public QueryServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceFutureStub(channel, callOptions);
        }
      };
    return QueryServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * QueryService defines the gRPC querier service.
   * </pre>
   */
  public static abstract class QueryServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * LatestDepositAddress queries the a deposit address by recipient
     * </pre>
     */
    public void latestDepositAddress(axelar.nexus.v1beta1.Query.LatestDepositAddressRequest request,
        io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Query.LatestDepositAddressResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLatestDepositAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * TransfersForChain queries transfers by chain
     * </pre>
     */
    public void transfersForChain(axelar.nexus.v1beta1.Query.TransfersForChainRequest request,
        io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Query.TransfersForChainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTransfersForChainMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLatestDepositAddressMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.nexus.v1beta1.Query.LatestDepositAddressRequest,
                axelar.nexus.v1beta1.Query.LatestDepositAddressResponse>(
                  this, METHODID_LATEST_DEPOSIT_ADDRESS)))
          .addMethod(
            getTransfersForChainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.nexus.v1beta1.Query.TransfersForChainRequest,
                axelar.nexus.v1beta1.Query.TransfersForChainResponse>(
                  this, METHODID_TRANSFERS_FOR_CHAIN)))
          .build();
    }
  }

  /**
   * <pre>
   * QueryService defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryServiceStub extends io.grpc.stub.AbstractAsyncStub<QueryServiceStub> {
    private QueryServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * LatestDepositAddress queries the a deposit address by recipient
     * </pre>
     */
    public void latestDepositAddress(axelar.nexus.v1beta1.Query.LatestDepositAddressRequest request,
        io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Query.LatestDepositAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLatestDepositAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TransfersForChain queries transfers by chain
     * </pre>
     */
    public void transfersForChain(axelar.nexus.v1beta1.Query.TransfersForChainRequest request,
        io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Query.TransfersForChainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTransfersForChainMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * QueryService defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<QueryServiceBlockingStub> {
    private QueryServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * LatestDepositAddress queries the a deposit address by recipient
     * </pre>
     */
    public axelar.nexus.v1beta1.Query.LatestDepositAddressResponse latestDepositAddress(axelar.nexus.v1beta1.Query.LatestDepositAddressRequest request) {
      return blockingUnaryCall(
          getChannel(), getLatestDepositAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TransfersForChain queries transfers by chain
     * </pre>
     */
    public axelar.nexus.v1beta1.Query.TransfersForChainResponse transfersForChain(axelar.nexus.v1beta1.Query.TransfersForChainRequest request) {
      return blockingUnaryCall(
          getChannel(), getTransfersForChainMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * QueryService defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryServiceFutureStub extends io.grpc.stub.AbstractFutureStub<QueryServiceFutureStub> {
    private QueryServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * LatestDepositAddress queries the a deposit address by recipient
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.nexus.v1beta1.Query.LatestDepositAddressResponse> latestDepositAddress(
        axelar.nexus.v1beta1.Query.LatestDepositAddressRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLatestDepositAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TransfersForChain queries transfers by chain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.nexus.v1beta1.Query.TransfersForChainResponse> transfersForChain(
        axelar.nexus.v1beta1.Query.TransfersForChainRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTransfersForChainMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LATEST_DEPOSIT_ADDRESS = 0;
  private static final int METHODID_TRANSFERS_FOR_CHAIN = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final QueryServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(QueryServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LATEST_DEPOSIT_ADDRESS:
          serviceImpl.latestDepositAddress((axelar.nexus.v1beta1.Query.LatestDepositAddressRequest) request,
              (io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Query.LatestDepositAddressResponse>) responseObserver);
          break;
        case METHODID_TRANSFERS_FOR_CHAIN:
          serviceImpl.transfersForChain((axelar.nexus.v1beta1.Query.TransfersForChainRequest) request,
              (io.grpc.stub.StreamObserver<axelar.nexus.v1beta1.Query.TransfersForChainResponse>) responseObserver);
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

  private static abstract class QueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return axelar.nexus.v1beta1.Service.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("QueryService");
    }
  }

  private static final class QueryServiceFileDescriptorSupplier
      extends QueryServiceBaseDescriptorSupplier {
    QueryServiceFileDescriptorSupplier() {}
  }

  private static final class QueryServiceMethodDescriptorSupplier
      extends QueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    QueryServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (QueryServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QueryServiceFileDescriptorSupplier())
              .addMethod(getLatestDepositAddressMethod())
              .addMethod(getTransfersForChainMethod())
              .build();
        }
      }
    }
    return result;
  }
}
