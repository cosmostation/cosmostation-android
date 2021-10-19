package starnamed.x.configuration.v1beta1;

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
 * Query provides defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: iov/configuration/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "starnamed.x.configuration.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigRequest,
      starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigResponse> getConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Config",
      requestType = starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigRequest.class,
      responseType = starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigRequest,
      starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigResponse> getConfigMethod() {
    io.grpc.MethodDescriptor<starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigRequest, starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigResponse> getConfigMethod;
    if ((getConfigMethod = QueryGrpc.getConfigMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getConfigMethod = QueryGrpc.getConfigMethod) == null) {
          QueryGrpc.getConfigMethod = getConfigMethod =
              io.grpc.MethodDescriptor.<starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigRequest, starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Config"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Config"))
              .build();
        }
      }
    }
    return getConfigMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesRequest,
      starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesResponse> getFeesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Fees",
      requestType = starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesRequest.class,
      responseType = starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesRequest,
      starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesResponse> getFeesMethod() {
    io.grpc.MethodDescriptor<starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesRequest, starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesResponse> getFeesMethod;
    if ((getFeesMethod = QueryGrpc.getFeesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFeesMethod = QueryGrpc.getFeesMethod) == null) {
          QueryGrpc.getFeesMethod = getFeesMethod =
              io.grpc.MethodDescriptor.<starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesRequest, starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Fees"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Fees"))
              .build();
        }
      }
    }
    return getFeesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QueryStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryStub>() {
        @java.lang.Override
        public QueryStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryStub(channel, callOptions);
        }
      };
    return QueryStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QueryBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryBlockingStub>() {
        @java.lang.Override
        public QueryBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryBlockingStub(channel, callOptions);
        }
      };
    return QueryBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QueryFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryFutureStub>() {
        @java.lang.Override
        public QueryFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryFutureStub(channel, callOptions);
        }
      };
    return QueryFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Config gets starname configuration.
     * </pre>
     */
    public void config(starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConfigMethod(), responseObserver);
    }

    /**
     * <pre>
     * Fees gets starname product fees.
     * </pre>
     */
    public void fees(starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFeesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getConfigMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigRequest,
                starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigResponse>(
                  this, METHODID_CONFIG)))
          .addMethod(
            getFeesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesRequest,
                starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesResponse>(
                  this, METHODID_FEES)))
          .build();
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryStub extends io.grpc.stub.AbstractAsyncStub<QueryStub> {
    private QueryStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryStub(channel, callOptions);
    }

    /**
     * <pre>
     * Config gets starname configuration.
     * </pre>
     */
    public void config(starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConfigMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Fees gets starname product fees.
     * </pre>
     */
    public void fees(starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFeesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryBlockingStub extends io.grpc.stub.AbstractBlockingStub<QueryBlockingStub> {
    private QueryBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Config gets starname configuration.
     * </pre>
     */
    public starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigResponse config(starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigRequest request) {
      return blockingUnaryCall(
          getChannel(), getConfigMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Fees gets starname product fees.
     * </pre>
     */
    public starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesResponse fees(starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesRequest request) {
      return blockingUnaryCall(
          getChannel(), getFeesMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryFutureStub extends io.grpc.stub.AbstractFutureStub<QueryFutureStub> {
    private QueryFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Config gets starname configuration.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigResponse> config(
        starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getConfigMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Fees gets starname product fees.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesResponse> fees(
        starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFeesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CONFIG = 0;
  private static final int METHODID_FEES = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final QueryImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(QueryImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CONFIG:
          serviceImpl.config((starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigRequest) request,
              (io.grpc.stub.StreamObserver<starnamed.x.configuration.v1beta1.QueryOuterClass.QueryConfigResponse>) responseObserver);
          break;
        case METHODID_FEES:
          serviceImpl.fees((starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesRequest) request,
              (io.grpc.stub.StreamObserver<starnamed.x.configuration.v1beta1.QueryOuterClass.QueryFeesResponse>) responseObserver);
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

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return starnamed.x.configuration.v1beta1.QueryOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Query");
    }
  }

  private static final class QueryFileDescriptorSupplier
      extends QueryBaseDescriptorSupplier {
    QueryFileDescriptorSupplier() {}
  }

  private static final class QueryMethodDescriptorSupplier
      extends QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    QueryMethodDescriptorSupplier(String methodName) {
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
      synchronized (QueryGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QueryFileDescriptorSupplier())
              .addMethod(getConfigMethod())
              .addMethod(getFeesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
