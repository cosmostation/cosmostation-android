package axelar.evm.v1beta1;

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
    comments = "Source: axelar/evm/v1beta1/service.proto")
public final class QueryServiceGrpc {

  private QueryServiceGrpc() {}

  public static final String SERVICE_NAME = "axelar.evm.v1beta1.QueryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<axelar.evm.v1beta1.Query.BurnerInfoRequest,
      axelar.evm.v1beta1.Query.BurnerInfoResponse> getBurnerInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BurnerInfo",
      requestType = axelar.evm.v1beta1.Query.BurnerInfoRequest.class,
      responseType = axelar.evm.v1beta1.Query.BurnerInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.evm.v1beta1.Query.BurnerInfoRequest,
      axelar.evm.v1beta1.Query.BurnerInfoResponse> getBurnerInfoMethod() {
    io.grpc.MethodDescriptor<axelar.evm.v1beta1.Query.BurnerInfoRequest, axelar.evm.v1beta1.Query.BurnerInfoResponse> getBurnerInfoMethod;
    if ((getBurnerInfoMethod = QueryServiceGrpc.getBurnerInfoMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getBurnerInfoMethod = QueryServiceGrpc.getBurnerInfoMethod) == null) {
          QueryServiceGrpc.getBurnerInfoMethod = getBurnerInfoMethod =
              io.grpc.MethodDescriptor.<axelar.evm.v1beta1.Query.BurnerInfoRequest, axelar.evm.v1beta1.Query.BurnerInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BurnerInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Query.BurnerInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.evm.v1beta1.Query.BurnerInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("BurnerInfo"))
              .build();
        }
      }
    }
    return getBurnerInfoMethod;
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
     * BurnerInfo queries the burner info for the specified address
     * </pre>
     */
    public void burnerInfo(axelar.evm.v1beta1.Query.BurnerInfoRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Query.BurnerInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBurnerInfoMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getBurnerInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.evm.v1beta1.Query.BurnerInfoRequest,
                axelar.evm.v1beta1.Query.BurnerInfoResponse>(
                  this, METHODID_BURNER_INFO)))
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
     * BurnerInfo queries the burner info for the specified address
     * </pre>
     */
    public void burnerInfo(axelar.evm.v1beta1.Query.BurnerInfoRequest request,
        io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Query.BurnerInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBurnerInfoMethod(), getCallOptions()), request, responseObserver);
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
     * BurnerInfo queries the burner info for the specified address
     * </pre>
     */
    public axelar.evm.v1beta1.Query.BurnerInfoResponse burnerInfo(axelar.evm.v1beta1.Query.BurnerInfoRequest request) {
      return blockingUnaryCall(
          getChannel(), getBurnerInfoMethod(), getCallOptions(), request);
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
     * BurnerInfo queries the burner info for the specified address
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.evm.v1beta1.Query.BurnerInfoResponse> burnerInfo(
        axelar.evm.v1beta1.Query.BurnerInfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBurnerInfoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BURNER_INFO = 0;

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
        case METHODID_BURNER_INFO:
          serviceImpl.burnerInfo((axelar.evm.v1beta1.Query.BurnerInfoRequest) request,
              (io.grpc.stub.StreamObserver<axelar.evm.v1beta1.Query.BurnerInfoResponse>) responseObserver);
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
      return axelar.evm.v1beta1.Service.getDescriptor();
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
              .addMethod(getBurnerInfoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
