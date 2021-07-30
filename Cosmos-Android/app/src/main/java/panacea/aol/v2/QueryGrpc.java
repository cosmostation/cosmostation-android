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
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: panacea/aol/v2/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "panacea.aol.v2.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<panacea.aol.v2.QueryTopicRequest,
      panacea.aol.v2.QueryTopicResponse> getTopicMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Topic",
      requestType = panacea.aol.v2.QueryTopicRequest.class,
      responseType = panacea.aol.v2.QueryTopicResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<panacea.aol.v2.QueryTopicRequest,
      panacea.aol.v2.QueryTopicResponse> getTopicMethod() {
    io.grpc.MethodDescriptor<panacea.aol.v2.QueryTopicRequest, panacea.aol.v2.QueryTopicResponse> getTopicMethod;
    if ((getTopicMethod = QueryGrpc.getTopicMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTopicMethod = QueryGrpc.getTopicMethod) == null) {
          QueryGrpc.getTopicMethod = getTopicMethod =
              io.grpc.MethodDescriptor.<panacea.aol.v2.QueryTopicRequest, panacea.aol.v2.QueryTopicResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Topic"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.QueryTopicRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.QueryTopicResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Topic"))
              .build();
        }
      }
    }
    return getTopicMethod;
  }

  private static volatile io.grpc.MethodDescriptor<panacea.aol.v2.QueryTopicsRequest,
      panacea.aol.v2.QueryTopicsResponse> getTopicsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Topics",
      requestType = panacea.aol.v2.QueryTopicsRequest.class,
      responseType = panacea.aol.v2.QueryTopicsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<panacea.aol.v2.QueryTopicsRequest,
      panacea.aol.v2.QueryTopicsResponse> getTopicsMethod() {
    io.grpc.MethodDescriptor<panacea.aol.v2.QueryTopicsRequest, panacea.aol.v2.QueryTopicsResponse> getTopicsMethod;
    if ((getTopicsMethod = QueryGrpc.getTopicsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTopicsMethod = QueryGrpc.getTopicsMethod) == null) {
          QueryGrpc.getTopicsMethod = getTopicsMethod =
              io.grpc.MethodDescriptor.<panacea.aol.v2.QueryTopicsRequest, panacea.aol.v2.QueryTopicsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Topics"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.QueryTopicsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.QueryTopicsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Topics"))
              .build();
        }
      }
    }
    return getTopicsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<panacea.aol.v2.QueryWriterRequest,
      panacea.aol.v2.QueryWriterResponse> getWriterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Writer",
      requestType = panacea.aol.v2.QueryWriterRequest.class,
      responseType = panacea.aol.v2.QueryWriterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<panacea.aol.v2.QueryWriterRequest,
      panacea.aol.v2.QueryWriterResponse> getWriterMethod() {
    io.grpc.MethodDescriptor<panacea.aol.v2.QueryWriterRequest, panacea.aol.v2.QueryWriterResponse> getWriterMethod;
    if ((getWriterMethod = QueryGrpc.getWriterMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getWriterMethod = QueryGrpc.getWriterMethod) == null) {
          QueryGrpc.getWriterMethod = getWriterMethod =
              io.grpc.MethodDescriptor.<panacea.aol.v2.QueryWriterRequest, panacea.aol.v2.QueryWriterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Writer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.QueryWriterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.QueryWriterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Writer"))
              .build();
        }
      }
    }
    return getWriterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<panacea.aol.v2.QueryWritersRequest,
      panacea.aol.v2.QueryWritersResponse> getWritersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Writers",
      requestType = panacea.aol.v2.QueryWritersRequest.class,
      responseType = panacea.aol.v2.QueryWritersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<panacea.aol.v2.QueryWritersRequest,
      panacea.aol.v2.QueryWritersResponse> getWritersMethod() {
    io.grpc.MethodDescriptor<panacea.aol.v2.QueryWritersRequest, panacea.aol.v2.QueryWritersResponse> getWritersMethod;
    if ((getWritersMethod = QueryGrpc.getWritersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getWritersMethod = QueryGrpc.getWritersMethod) == null) {
          QueryGrpc.getWritersMethod = getWritersMethod =
              io.grpc.MethodDescriptor.<panacea.aol.v2.QueryWritersRequest, panacea.aol.v2.QueryWritersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Writers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.QueryWritersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.QueryWritersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Writers"))
              .build();
        }
      }
    }
    return getWritersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<panacea.aol.v2.QueryRecordRequest,
      panacea.aol.v2.QueryRecordResponse> getRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Record",
      requestType = panacea.aol.v2.QueryRecordRequest.class,
      responseType = panacea.aol.v2.QueryRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<panacea.aol.v2.QueryRecordRequest,
      panacea.aol.v2.QueryRecordResponse> getRecordMethod() {
    io.grpc.MethodDescriptor<panacea.aol.v2.QueryRecordRequest, panacea.aol.v2.QueryRecordResponse> getRecordMethod;
    if ((getRecordMethod = QueryGrpc.getRecordMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRecordMethod = QueryGrpc.getRecordMethod) == null) {
          QueryGrpc.getRecordMethod = getRecordMethod =
              io.grpc.MethodDescriptor.<panacea.aol.v2.QueryRecordRequest, panacea.aol.v2.QueryRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Record"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.QueryRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  panacea.aol.v2.QueryRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Record"))
              .build();
        }
      }
    }
    return getRecordMethod;
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
     * Topic returns topic details.
     * </pre>
     */
    public void topic(panacea.aol.v2.QueryTopicRequest request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.QueryTopicResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTopicMethod(), responseObserver);
    }

    /**
     * <pre>
     * Topics returns topic names.
     * </pre>
     */
    public void topics(panacea.aol.v2.QueryTopicsRequest request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.QueryTopicsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTopicsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Writer returns writer details.
     * </pre>
     */
    public void writer(panacea.aol.v2.QueryWriterRequest request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.QueryWriterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWriterMethod(), responseObserver);
    }

    /**
     * <pre>
     * Writers returns writer addresses.
     * </pre>
     */
    public void writers(panacea.aol.v2.QueryWritersRequest request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.QueryWritersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWritersMethod(), responseObserver);
    }

    /**
     * <pre>
     * Record returns record details.
     * </pre>
     */
    public void record(panacea.aol.v2.QueryRecordRequest request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.QueryRecordResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRecordMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getTopicMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                panacea.aol.v2.QueryTopicRequest,
                panacea.aol.v2.QueryTopicResponse>(
                  this, METHODID_TOPIC)))
          .addMethod(
            getTopicsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                panacea.aol.v2.QueryTopicsRequest,
                panacea.aol.v2.QueryTopicsResponse>(
                  this, METHODID_TOPICS)))
          .addMethod(
            getWriterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                panacea.aol.v2.QueryWriterRequest,
                panacea.aol.v2.QueryWriterResponse>(
                  this, METHODID_WRITER)))
          .addMethod(
            getWritersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                panacea.aol.v2.QueryWritersRequest,
                panacea.aol.v2.QueryWritersResponse>(
                  this, METHODID_WRITERS)))
          .addMethod(
            getRecordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                panacea.aol.v2.QueryRecordRequest,
                panacea.aol.v2.QueryRecordResponse>(
                  this, METHODID_RECORD)))
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
     * Topic returns topic details.
     * </pre>
     */
    public void topic(panacea.aol.v2.QueryTopicRequest request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.QueryTopicResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTopicMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Topics returns topic names.
     * </pre>
     */
    public void topics(panacea.aol.v2.QueryTopicsRequest request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.QueryTopicsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTopicsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Writer returns writer details.
     * </pre>
     */
    public void writer(panacea.aol.v2.QueryWriterRequest request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.QueryWriterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWriterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Writers returns writer addresses.
     * </pre>
     */
    public void writers(panacea.aol.v2.QueryWritersRequest request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.QueryWritersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWritersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Record returns record details.
     * </pre>
     */
    public void record(panacea.aol.v2.QueryRecordRequest request,
        io.grpc.stub.StreamObserver<panacea.aol.v2.QueryRecordResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRecordMethod(), getCallOptions()), request, responseObserver);
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
     * Topic returns topic details.
     * </pre>
     */
    public panacea.aol.v2.QueryTopicResponse topic(panacea.aol.v2.QueryTopicRequest request) {
      return blockingUnaryCall(
          getChannel(), getTopicMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Topics returns topic names.
     * </pre>
     */
    public panacea.aol.v2.QueryTopicsResponse topics(panacea.aol.v2.QueryTopicsRequest request) {
      return blockingUnaryCall(
          getChannel(), getTopicsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Writer returns writer details.
     * </pre>
     */
    public panacea.aol.v2.QueryWriterResponse writer(panacea.aol.v2.QueryWriterRequest request) {
      return blockingUnaryCall(
          getChannel(), getWriterMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Writers returns writer addresses.
     * </pre>
     */
    public panacea.aol.v2.QueryWritersResponse writers(panacea.aol.v2.QueryWritersRequest request) {
      return blockingUnaryCall(
          getChannel(), getWritersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Record returns record details.
     * </pre>
     */
    public panacea.aol.v2.QueryRecordResponse record(panacea.aol.v2.QueryRecordRequest request) {
      return blockingUnaryCall(
          getChannel(), getRecordMethod(), getCallOptions(), request);
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
     * Topic returns topic details.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<panacea.aol.v2.QueryTopicResponse> topic(
        panacea.aol.v2.QueryTopicRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTopicMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Topics returns topic names.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<panacea.aol.v2.QueryTopicsResponse> topics(
        panacea.aol.v2.QueryTopicsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTopicsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Writer returns writer details.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<panacea.aol.v2.QueryWriterResponse> writer(
        panacea.aol.v2.QueryWriterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWriterMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Writers returns writer addresses.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<panacea.aol.v2.QueryWritersResponse> writers(
        panacea.aol.v2.QueryWritersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWritersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Record returns record details.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<panacea.aol.v2.QueryRecordResponse> record(
        panacea.aol.v2.QueryRecordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRecordMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TOPIC = 0;
  private static final int METHODID_TOPICS = 1;
  private static final int METHODID_WRITER = 2;
  private static final int METHODID_WRITERS = 3;
  private static final int METHODID_RECORD = 4;

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
        case METHODID_TOPIC:
          serviceImpl.topic((panacea.aol.v2.QueryTopicRequest) request,
              (io.grpc.stub.StreamObserver<panacea.aol.v2.QueryTopicResponse>) responseObserver);
          break;
        case METHODID_TOPICS:
          serviceImpl.topics((panacea.aol.v2.QueryTopicsRequest) request,
              (io.grpc.stub.StreamObserver<panacea.aol.v2.QueryTopicsResponse>) responseObserver);
          break;
        case METHODID_WRITER:
          serviceImpl.writer((panacea.aol.v2.QueryWriterRequest) request,
              (io.grpc.stub.StreamObserver<panacea.aol.v2.QueryWriterResponse>) responseObserver);
          break;
        case METHODID_WRITERS:
          serviceImpl.writers((panacea.aol.v2.QueryWritersRequest) request,
              (io.grpc.stub.StreamObserver<panacea.aol.v2.QueryWritersResponse>) responseObserver);
          break;
        case METHODID_RECORD:
          serviceImpl.record((panacea.aol.v2.QueryRecordRequest) request,
              (io.grpc.stub.StreamObserver<panacea.aol.v2.QueryRecordResponse>) responseObserver);
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
      return panacea.aol.v2.QueryOuterClass.getDescriptor();
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
              .addMethod(getTopicMethod())
              .addMethod(getTopicsMethod())
              .addMethod(getWriterMethod())
              .addMethod(getWritersMethod())
              .addMethod(getRecordMethod())
              .build();
        }
      }
    }
    return result;
  }
}
