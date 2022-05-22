package osmosis.epochs.v1beta1;

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
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: osmosis/epochs/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "osmosis.epochs.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoRequest,
      osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoResponse> getEpochInfosMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochInfos",
      requestType = osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoRequest.class,
      responseType = osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoRequest,
      osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoResponse> getEpochInfosMethod() {
    io.grpc.MethodDescriptor<osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoRequest, osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoResponse> getEpochInfosMethod;
    if ((getEpochInfosMethod = QueryGrpc.getEpochInfosMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochInfosMethod = QueryGrpc.getEpochInfosMethod) == null) {
          QueryGrpc.getEpochInfosMethod = getEpochInfosMethod =
              io.grpc.MethodDescriptor.<osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoRequest, osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochInfos"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochInfos"))
              .build();
        }
      }
    }
    return getEpochInfosMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochRequest,
      osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochResponse> getCurrentEpochMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CurrentEpoch",
      requestType = osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochRequest.class,
      responseType = osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochRequest,
      osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochResponse> getCurrentEpochMethod() {
    io.grpc.MethodDescriptor<osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochRequest, osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochResponse> getCurrentEpochMethod;
    if ((getCurrentEpochMethod = QueryGrpc.getCurrentEpochMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCurrentEpochMethod = QueryGrpc.getCurrentEpochMethod) == null) {
          QueryGrpc.getCurrentEpochMethod = getCurrentEpochMethod =
              io.grpc.MethodDescriptor.<osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochRequest, osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CurrentEpoch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CurrentEpoch"))
              .build();
        }
      }
    }
    return getCurrentEpochMethod;
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
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * EpochInfos provide running epochInfos
     * </pre>
     */
    public void epochInfos(osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoRequest request,
        io.grpc.stub.StreamObserver<osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEpochInfosMethod(), responseObserver);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public void currentEpoch(osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochRequest request,
        io.grpc.stub.StreamObserver<osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCurrentEpochMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getEpochInfosMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoRequest,
                osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoResponse>(
                  this, METHODID_EPOCH_INFOS)))
          .addMethod(
            getCurrentEpochMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochRequest,
                osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochResponse>(
                  this, METHODID_CURRENT_EPOCH)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * EpochInfos provide running epochInfos
     * </pre>
     */
    public void epochInfos(osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoRequest request,
        io.grpc.stub.StreamObserver<osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEpochInfosMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public void currentEpoch(osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochRequest request,
        io.grpc.stub.StreamObserver<osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCurrentEpochMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * EpochInfos provide running epochInfos
     * </pre>
     */
    public osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoResponse epochInfos(osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoRequest request) {
      return blockingUnaryCall(
          getChannel(), getEpochInfosMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochResponse currentEpoch(osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochRequest request) {
      return blockingUnaryCall(
          getChannel(), getCurrentEpochMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * EpochInfos provide running epochInfos
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoResponse> epochInfos(
        osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEpochInfosMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochResponse> currentEpoch(
        osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCurrentEpochMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EPOCH_INFOS = 0;
  private static final int METHODID_CURRENT_EPOCH = 1;

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
        case METHODID_EPOCH_INFOS:
          serviceImpl.epochInfos((osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.epochs.v1beta1.QueryOuterClass.QueryEpochsInfoResponse>) responseObserver);
          break;
        case METHODID_CURRENT_EPOCH:
          serviceImpl.currentEpoch((osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.epochs.v1beta1.QueryOuterClass.QueryCurrentEpochResponse>) responseObserver);
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
      return osmosis.epochs.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getEpochInfosMethod())
              .addMethod(getCurrentEpochMethod())
              .build();
        }
      }
    }
    return result;
  }
}
