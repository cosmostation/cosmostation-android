package starnamed.x.escrow.v1beta1;

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
 * Query provides defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: iov/escrow/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "starnamed.x.escrow.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowRequest,
      starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowResponse> getEscrowMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Escrow",
      requestType = starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowRequest.class,
      responseType = starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowRequest,
      starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowResponse> getEscrowMethod() {
    io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowRequest, starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowResponse> getEscrowMethod;
    if ((getEscrowMethod = QueryGrpc.getEscrowMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEscrowMethod = QueryGrpc.getEscrowMethod) == null) {
          QueryGrpc.getEscrowMethod = getEscrowMethod =
              io.grpc.MethodDescriptor.<starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowRequest, starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Escrow"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Escrow"))
              .build();
        }
      }
    }
    return getEscrowMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsRequest,
      starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsResponse> getEscrowsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Escrows",
      requestType = starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsRequest.class,
      responseType = starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsRequest,
      starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsResponse> getEscrowsMethod() {
    io.grpc.MethodDescriptor<starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsRequest, starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsResponse> getEscrowsMethod;
    if ((getEscrowsMethod = QueryGrpc.getEscrowsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEscrowsMethod = QueryGrpc.getEscrowsMethod) == null) {
          QueryGrpc.getEscrowsMethod = getEscrowsMethod =
              io.grpc.MethodDescriptor.<starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsRequest, starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Escrows"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Escrows"))
              .build();
        }
      }
    }
    return getEscrowsMethod;
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
   * Query provides defines the gRPC querier service
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Escrow queries the escrow by the specified id
     * </pre>
     */
    public void escrow(starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEscrowMethod(), responseObserver);
    }

    /**
     * <pre>
     * Escrows queries escrows by the specified key-value pairs
     * </pre>
     */
    public void escrows(starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEscrowsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getEscrowMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowRequest,
                starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowResponse>(
                  this, METHODID_ESCROW)))
          .addMethod(
            getEscrowsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsRequest,
                starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsResponse>(
                  this, METHODID_ESCROWS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service
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
     * Escrow queries the escrow by the specified id
     * </pre>
     */
    public void escrow(starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEscrowMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Escrows queries escrows by the specified key-value pairs
     * </pre>
     */
    public void escrows(starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEscrowsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service
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
     * Escrow queries the escrow by the specified id
     * </pre>
     */
    public starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowResponse escrow(starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowRequest request) {
      return blockingUnaryCall(
          getChannel(), getEscrowMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Escrows queries escrows by the specified key-value pairs
     * </pre>
     */
    public starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsResponse escrows(starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsRequest request) {
      return blockingUnaryCall(
          getChannel(), getEscrowsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service
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
     * Escrow queries the escrow by the specified id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowResponse> escrow(
        starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEscrowMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Escrows queries escrows by the specified key-value pairs
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsResponse> escrows(
        starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEscrowsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ESCROW = 0;
  private static final int METHODID_ESCROWS = 1;

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
        case METHODID_ESCROW:
          serviceImpl.escrow((starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowRequest) request,
              (io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowResponse>) responseObserver);
          break;
        case METHODID_ESCROWS:
          serviceImpl.escrows((starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsRequest) request,
              (io.grpc.stub.StreamObserver<starnamed.x.escrow.v1beta1.QueryOuterClass.QueryEscrowsResponse>) responseObserver);
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
      return starnamed.x.escrow.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getEscrowMethod())
              .addMethod(getEscrowsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
