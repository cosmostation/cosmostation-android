package irismod.random;

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
 * Query creates service with guardian as rpc
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: iris_mod/random/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "irismod.random.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<irismod.random.QueryOuterClass.QueryRandomRequest,
      irismod.random.QueryOuterClass.QueryRandomResponse> getRandomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Random",
      requestType = irismod.random.QueryOuterClass.QueryRandomRequest.class,
      responseType = irismod.random.QueryOuterClass.QueryRandomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.random.QueryOuterClass.QueryRandomRequest,
      irismod.random.QueryOuterClass.QueryRandomResponse> getRandomMethod() {
    io.grpc.MethodDescriptor<irismod.random.QueryOuterClass.QueryRandomRequest, irismod.random.QueryOuterClass.QueryRandomResponse> getRandomMethod;
    if ((getRandomMethod = QueryGrpc.getRandomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRandomMethod = QueryGrpc.getRandomMethod) == null) {
          QueryGrpc.getRandomMethod = getRandomMethod =
              io.grpc.MethodDescriptor.<irismod.random.QueryOuterClass.QueryRandomRequest, irismod.random.QueryOuterClass.QueryRandomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Random"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.random.QueryOuterClass.QueryRandomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.random.QueryOuterClass.QueryRandomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Random"))
              .build();
        }
      }
    }
    return getRandomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.random.QueryOuterClass.QueryRandomRequestQueueRequest,
      irismod.random.QueryOuterClass.QueryRandomRequestQueueResponse> getRandomRequestQueueMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RandomRequestQueue",
      requestType = irismod.random.QueryOuterClass.QueryRandomRequestQueueRequest.class,
      responseType = irismod.random.QueryOuterClass.QueryRandomRequestQueueResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.random.QueryOuterClass.QueryRandomRequestQueueRequest,
      irismod.random.QueryOuterClass.QueryRandomRequestQueueResponse> getRandomRequestQueueMethod() {
    io.grpc.MethodDescriptor<irismod.random.QueryOuterClass.QueryRandomRequestQueueRequest, irismod.random.QueryOuterClass.QueryRandomRequestQueueResponse> getRandomRequestQueueMethod;
    if ((getRandomRequestQueueMethod = QueryGrpc.getRandomRequestQueueMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRandomRequestQueueMethod = QueryGrpc.getRandomRequestQueueMethod) == null) {
          QueryGrpc.getRandomRequestQueueMethod = getRandomRequestQueueMethod =
              io.grpc.MethodDescriptor.<irismod.random.QueryOuterClass.QueryRandomRequestQueueRequest, irismod.random.QueryOuterClass.QueryRandomRequestQueueResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RandomRequestQueue"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.random.QueryOuterClass.QueryRandomRequestQueueRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.random.QueryOuterClass.QueryRandomRequestQueueResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RandomRequestQueue"))
              .build();
        }
      }
    }
    return getRandomRequestQueueMethod;
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
   * Query creates service with guardian as rpc
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Random queries the random result
     * </pre>
     */
    public void random(irismod.random.QueryOuterClass.QueryRandomRequest request,
        io.grpc.stub.StreamObserver<irismod.random.QueryOuterClass.QueryRandomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRandomMethod(), responseObserver);
    }

    /**
     * <pre>
     * RandomRequestQueue queries the random request queue
     * </pre>
     */
    public void randomRequestQueue(irismod.random.QueryOuterClass.QueryRandomRequestQueueRequest request,
        io.grpc.stub.StreamObserver<irismod.random.QueryOuterClass.QueryRandomRequestQueueResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRandomRequestQueueMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRandomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.random.QueryOuterClass.QueryRandomRequest,
                irismod.random.QueryOuterClass.QueryRandomResponse>(
                  this, METHODID_RANDOM)))
          .addMethod(
            getRandomRequestQueueMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.random.QueryOuterClass.QueryRandomRequestQueueRequest,
                irismod.random.QueryOuterClass.QueryRandomRequestQueueResponse>(
                  this, METHODID_RANDOM_REQUEST_QUEUE)))
          .build();
    }
  }

  /**
   * <pre>
   * Query creates service with guardian as rpc
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
     * Random queries the random result
     * </pre>
     */
    public void random(irismod.random.QueryOuterClass.QueryRandomRequest request,
        io.grpc.stub.StreamObserver<irismod.random.QueryOuterClass.QueryRandomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRandomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RandomRequestQueue queries the random request queue
     * </pre>
     */
    public void randomRequestQueue(irismod.random.QueryOuterClass.QueryRandomRequestQueueRequest request,
        io.grpc.stub.StreamObserver<irismod.random.QueryOuterClass.QueryRandomRequestQueueResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRandomRequestQueueMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query creates service with guardian as rpc
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
     * Random queries the random result
     * </pre>
     */
    public irismod.random.QueryOuterClass.QueryRandomResponse random(irismod.random.QueryOuterClass.QueryRandomRequest request) {
      return blockingUnaryCall(
          getChannel(), getRandomMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RandomRequestQueue queries the random request queue
     * </pre>
     */
    public irismod.random.QueryOuterClass.QueryRandomRequestQueueResponse randomRequestQueue(irismod.random.QueryOuterClass.QueryRandomRequestQueueRequest request) {
      return blockingUnaryCall(
          getChannel(), getRandomRequestQueueMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query creates service with guardian as rpc
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
     * Random queries the random result
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.random.QueryOuterClass.QueryRandomResponse> random(
        irismod.random.QueryOuterClass.QueryRandomRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRandomMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RandomRequestQueue queries the random request queue
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.random.QueryOuterClass.QueryRandomRequestQueueResponse> randomRequestQueue(
        irismod.random.QueryOuterClass.QueryRandomRequestQueueRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRandomRequestQueueMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RANDOM = 0;
  private static final int METHODID_RANDOM_REQUEST_QUEUE = 1;

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
        case METHODID_RANDOM:
          serviceImpl.random((irismod.random.QueryOuterClass.QueryRandomRequest) request,
              (io.grpc.stub.StreamObserver<irismod.random.QueryOuterClass.QueryRandomResponse>) responseObserver);
          break;
        case METHODID_RANDOM_REQUEST_QUEUE:
          serviceImpl.randomRequestQueue((irismod.random.QueryOuterClass.QueryRandomRequestQueueRequest) request,
              (io.grpc.stub.StreamObserver<irismod.random.QueryOuterClass.QueryRandomRequestQueueResponse>) responseObserver);
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
      return irismod.random.QueryOuterClass.getDescriptor();
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
              .addMethod(getRandomMethod())
              .addMethod(getRandomRequestQueueMethod())
              .build();
        }
      }
    }
    return result;
  }
}
