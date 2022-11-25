package stride.epochs;

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
    comments = "Source: stride/epochs/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "stride.epochs.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<stride.epochs.QueryOuterClass.QueryEpochsInfoRequest,
      stride.epochs.QueryOuterClass.QueryEpochsInfoResponse> getEpochInfosMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochInfos",
      requestType = stride.epochs.QueryOuterClass.QueryEpochsInfoRequest.class,
      responseType = stride.epochs.QueryOuterClass.QueryEpochsInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.epochs.QueryOuterClass.QueryEpochsInfoRequest,
      stride.epochs.QueryOuterClass.QueryEpochsInfoResponse> getEpochInfosMethod() {
    io.grpc.MethodDescriptor<stride.epochs.QueryOuterClass.QueryEpochsInfoRequest, stride.epochs.QueryOuterClass.QueryEpochsInfoResponse> getEpochInfosMethod;
    if ((getEpochInfosMethod = QueryGrpc.getEpochInfosMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochInfosMethod = QueryGrpc.getEpochInfosMethod) == null) {
          QueryGrpc.getEpochInfosMethod = getEpochInfosMethod =
              io.grpc.MethodDescriptor.<stride.epochs.QueryOuterClass.QueryEpochsInfoRequest, stride.epochs.QueryOuterClass.QueryEpochsInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochInfos"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.epochs.QueryOuterClass.QueryEpochsInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.epochs.QueryOuterClass.QueryEpochsInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochInfos"))
              .build();
        }
      }
    }
    return getEpochInfosMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.epochs.QueryOuterClass.QueryCurrentEpochRequest,
      stride.epochs.QueryOuterClass.QueryCurrentEpochResponse> getCurrentEpochMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CurrentEpoch",
      requestType = stride.epochs.QueryOuterClass.QueryCurrentEpochRequest.class,
      responseType = stride.epochs.QueryOuterClass.QueryCurrentEpochResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.epochs.QueryOuterClass.QueryCurrentEpochRequest,
      stride.epochs.QueryOuterClass.QueryCurrentEpochResponse> getCurrentEpochMethod() {
    io.grpc.MethodDescriptor<stride.epochs.QueryOuterClass.QueryCurrentEpochRequest, stride.epochs.QueryOuterClass.QueryCurrentEpochResponse> getCurrentEpochMethod;
    if ((getCurrentEpochMethod = QueryGrpc.getCurrentEpochMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCurrentEpochMethod = QueryGrpc.getCurrentEpochMethod) == null) {
          QueryGrpc.getCurrentEpochMethod = getCurrentEpochMethod =
              io.grpc.MethodDescriptor.<stride.epochs.QueryOuterClass.QueryCurrentEpochRequest, stride.epochs.QueryOuterClass.QueryCurrentEpochResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CurrentEpoch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.epochs.QueryOuterClass.QueryCurrentEpochRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.epochs.QueryOuterClass.QueryCurrentEpochResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CurrentEpoch"))
              .build();
        }
      }
    }
    return getCurrentEpochMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.epochs.QueryOuterClass.QueryEpochInfoRequest,
      stride.epochs.QueryOuterClass.QueryEpochInfoResponse> getEpochInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochInfo",
      requestType = stride.epochs.QueryOuterClass.QueryEpochInfoRequest.class,
      responseType = stride.epochs.QueryOuterClass.QueryEpochInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.epochs.QueryOuterClass.QueryEpochInfoRequest,
      stride.epochs.QueryOuterClass.QueryEpochInfoResponse> getEpochInfoMethod() {
    io.grpc.MethodDescriptor<stride.epochs.QueryOuterClass.QueryEpochInfoRequest, stride.epochs.QueryOuterClass.QueryEpochInfoResponse> getEpochInfoMethod;
    if ((getEpochInfoMethod = QueryGrpc.getEpochInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochInfoMethod = QueryGrpc.getEpochInfoMethod) == null) {
          QueryGrpc.getEpochInfoMethod = getEpochInfoMethod =
              io.grpc.MethodDescriptor.<stride.epochs.QueryOuterClass.QueryEpochInfoRequest, stride.epochs.QueryOuterClass.QueryEpochInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.epochs.QueryOuterClass.QueryEpochInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.epochs.QueryOuterClass.QueryEpochInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochInfo"))
              .build();
        }
      }
    }
    return getEpochInfoMethod;
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
    public void epochInfos(stride.epochs.QueryOuterClass.QueryEpochsInfoRequest request,
        io.grpc.stub.StreamObserver<stride.epochs.QueryOuterClass.QueryEpochsInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEpochInfosMethod(), responseObserver);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public void currentEpoch(stride.epochs.QueryOuterClass.QueryCurrentEpochRequest request,
        io.grpc.stub.StreamObserver<stride.epochs.QueryOuterClass.QueryCurrentEpochResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCurrentEpochMethod(), responseObserver);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public void epochInfo(stride.epochs.QueryOuterClass.QueryEpochInfoRequest request,
        io.grpc.stub.StreamObserver<stride.epochs.QueryOuterClass.QueryEpochInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEpochInfoMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getEpochInfosMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.epochs.QueryOuterClass.QueryEpochsInfoRequest,
                stride.epochs.QueryOuterClass.QueryEpochsInfoResponse>(
                  this, METHODID_EPOCH_INFOS)))
          .addMethod(
            getCurrentEpochMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.epochs.QueryOuterClass.QueryCurrentEpochRequest,
                stride.epochs.QueryOuterClass.QueryCurrentEpochResponse>(
                  this, METHODID_CURRENT_EPOCH)))
          .addMethod(
            getEpochInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.epochs.QueryOuterClass.QueryEpochInfoRequest,
                stride.epochs.QueryOuterClass.QueryEpochInfoResponse>(
                  this, METHODID_EPOCH_INFO)))
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
    public void epochInfos(stride.epochs.QueryOuterClass.QueryEpochsInfoRequest request,
        io.grpc.stub.StreamObserver<stride.epochs.QueryOuterClass.QueryEpochsInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEpochInfosMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public void currentEpoch(stride.epochs.QueryOuterClass.QueryCurrentEpochRequest request,
        io.grpc.stub.StreamObserver<stride.epochs.QueryOuterClass.QueryCurrentEpochResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCurrentEpochMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public void epochInfo(stride.epochs.QueryOuterClass.QueryEpochInfoRequest request,
        io.grpc.stub.StreamObserver<stride.epochs.QueryOuterClass.QueryEpochInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEpochInfoMethod(), getCallOptions()), request, responseObserver);
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
    public stride.epochs.QueryOuterClass.QueryEpochsInfoResponse epochInfos(stride.epochs.QueryOuterClass.QueryEpochsInfoRequest request) {
      return blockingUnaryCall(
          getChannel(), getEpochInfosMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public stride.epochs.QueryOuterClass.QueryCurrentEpochResponse currentEpoch(stride.epochs.QueryOuterClass.QueryCurrentEpochRequest request) {
      return blockingUnaryCall(
          getChannel(), getCurrentEpochMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public stride.epochs.QueryOuterClass.QueryEpochInfoResponse epochInfo(stride.epochs.QueryOuterClass.QueryEpochInfoRequest request) {
      return blockingUnaryCall(
          getChannel(), getEpochInfoMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<stride.epochs.QueryOuterClass.QueryEpochsInfoResponse> epochInfos(
        stride.epochs.QueryOuterClass.QueryEpochsInfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEpochInfosMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.epochs.QueryOuterClass.QueryCurrentEpochResponse> currentEpoch(
        stride.epochs.QueryOuterClass.QueryCurrentEpochRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCurrentEpochMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CurrentEpoch provide current epoch of specified identifier
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.epochs.QueryOuterClass.QueryEpochInfoResponse> epochInfo(
        stride.epochs.QueryOuterClass.QueryEpochInfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEpochInfoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EPOCH_INFOS = 0;
  private static final int METHODID_CURRENT_EPOCH = 1;
  private static final int METHODID_EPOCH_INFO = 2;

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
          serviceImpl.epochInfos((stride.epochs.QueryOuterClass.QueryEpochsInfoRequest) request,
              (io.grpc.stub.StreamObserver<stride.epochs.QueryOuterClass.QueryEpochsInfoResponse>) responseObserver);
          break;
        case METHODID_CURRENT_EPOCH:
          serviceImpl.currentEpoch((stride.epochs.QueryOuterClass.QueryCurrentEpochRequest) request,
              (io.grpc.stub.StreamObserver<stride.epochs.QueryOuterClass.QueryCurrentEpochResponse>) responseObserver);
          break;
        case METHODID_EPOCH_INFO:
          serviceImpl.epochInfo((stride.epochs.QueryOuterClass.QueryEpochInfoRequest) request,
              (io.grpc.stub.StreamObserver<stride.epochs.QueryOuterClass.QueryEpochInfoResponse>) responseObserver);
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
      return stride.epochs.QueryOuterClass.getDescriptor();
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
              .addMethod(getEpochInfoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
