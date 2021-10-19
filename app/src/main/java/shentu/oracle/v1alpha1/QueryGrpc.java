package shentu.oracle.v1alpha1;

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
 * Query defines the gRPC querier service for oracle module.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: shentu/oracle/v1alpha1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "shentu.oracle.v1alpha1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorRequest,
      shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorResponse> getOperatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Operator",
      requestType = shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorRequest.class,
      responseType = shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorRequest,
      shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorResponse> getOperatorMethod() {
    io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorRequest, shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorResponse> getOperatorMethod;
    if ((getOperatorMethod = QueryGrpc.getOperatorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOperatorMethod = QueryGrpc.getOperatorMethod) == null) {
          QueryGrpc.getOperatorMethod = getOperatorMethod =
              io.grpc.MethodDescriptor.<shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorRequest, shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Operator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Operator"))
              .build();
        }
      }
    }
    return getOperatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsRequest,
      shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsResponse> getOperatorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Operators",
      requestType = shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsRequest.class,
      responseType = shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsRequest,
      shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsResponse> getOperatorsMethod() {
    io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsRequest, shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsResponse> getOperatorsMethod;
    if ((getOperatorsMethod = QueryGrpc.getOperatorsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOperatorsMethod = QueryGrpc.getOperatorsMethod) == null) {
          QueryGrpc.getOperatorsMethod = getOperatorsMethod =
              io.grpc.MethodDescriptor.<shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsRequest, shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Operators"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Operators"))
              .build();
        }
      }
    }
    return getOperatorsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsRequest,
      shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsResponse> getWithdrawsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Withdraws",
      requestType = shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsRequest.class,
      responseType = shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsRequest,
      shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsResponse> getWithdrawsMethod() {
    io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsRequest, shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsResponse> getWithdrawsMethod;
    if ((getWithdrawsMethod = QueryGrpc.getWithdrawsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getWithdrawsMethod = QueryGrpc.getWithdrawsMethod) == null) {
          QueryGrpc.getWithdrawsMethod = getWithdrawsMethod =
              io.grpc.MethodDescriptor.<shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsRequest, shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Withdraws"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Withdraws"))
              .build();
        }
      }
    }
    return getWithdrawsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskRequest,
      shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskResponse> getTaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Task",
      requestType = shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskRequest.class,
      responseType = shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskRequest,
      shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskResponse> getTaskMethod() {
    io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskRequest, shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskResponse> getTaskMethod;
    if ((getTaskMethod = QueryGrpc.getTaskMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTaskMethod = QueryGrpc.getTaskMethod) == null) {
          QueryGrpc.getTaskMethod = getTaskMethod =
              io.grpc.MethodDescriptor.<shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskRequest, shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Task"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Task"))
              .build();
        }
      }
    }
    return getTaskMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseRequest,
      shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseResponse> getResponseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Response",
      requestType = shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseRequest.class,
      responseType = shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseRequest,
      shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseResponse> getResponseMethod() {
    io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseRequest, shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseResponse> getResponseMethod;
    if ((getResponseMethod = QueryGrpc.getResponseMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getResponseMethod = QueryGrpc.getResponseMethod) == null) {
          QueryGrpc.getResponseMethod = getResponseMethod =
              io.grpc.MethodDescriptor.<shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseRequest, shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Response"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Response"))
              .build();
        }
      }
    }
    return getResponseMethod;
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
   * Query defines the gRPC querier service for oracle module.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     */
    public void operator(shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorRequest request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOperatorMethod(), responseObserver);
    }

    /**
     */
    public void operators(shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsRequest request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOperatorsMethod(), responseObserver);
    }

    /**
     */
    public void withdraws(shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsRequest request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawsMethod(), responseObserver);
    }

    /**
     */
    public void task(shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskRequest request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTaskMethod(), responseObserver);
    }

    /**
     */
    public void response(shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseRequest request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getResponseMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getOperatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorRequest,
                shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorResponse>(
                  this, METHODID_OPERATOR)))
          .addMethod(
            getOperatorsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsRequest,
                shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsResponse>(
                  this, METHODID_OPERATORS)))
          .addMethod(
            getWithdrawsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsRequest,
                shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsResponse>(
                  this, METHODID_WITHDRAWS)))
          .addMethod(
            getTaskMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskRequest,
                shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskResponse>(
                  this, METHODID_TASK)))
          .addMethod(
            getResponseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseRequest,
                shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseResponse>(
                  this, METHODID_RESPONSE)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for oracle module.
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
     */
    public void operator(shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorRequest request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOperatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void operators(shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsRequest request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOperatorsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void withdraws(shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsRequest request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void task(shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskRequest request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTaskMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void response(shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseRequest request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getResponseMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for oracle module.
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
     */
    public shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorResponse operator(shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorRequest request) {
      return blockingUnaryCall(
          getChannel(), getOperatorMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsResponse operators(shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsRequest request) {
      return blockingUnaryCall(
          getChannel(), getOperatorsMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsResponse withdraws(shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsRequest request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawsMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskResponse task(shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskRequest request) {
      return blockingUnaryCall(
          getChannel(), getTaskMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseResponse response(shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseRequest request) {
      return blockingUnaryCall(
          getChannel(), getResponseMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for oracle module.
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
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorResponse> operator(
        shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOperatorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsResponse> operators(
        shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOperatorsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsResponse> withdraws(
        shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskResponse> task(
        shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTaskMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseResponse> response(
        shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getResponseMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_OPERATOR = 0;
  private static final int METHODID_OPERATORS = 1;
  private static final int METHODID_WITHDRAWS = 2;
  private static final int METHODID_TASK = 3;
  private static final int METHODID_RESPONSE = 4;

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
        case METHODID_OPERATOR:
          serviceImpl.operator((shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorRequest) request,
              (io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorResponse>) responseObserver);
          break;
        case METHODID_OPERATORS:
          serviceImpl.operators((shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsRequest) request,
              (io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.QueryOuterClass.QueryOperatorsResponse>) responseObserver);
          break;
        case METHODID_WITHDRAWS:
          serviceImpl.withdraws((shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsRequest) request,
              (io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.QueryOuterClass.QueryWithdrawsResponse>) responseObserver);
          break;
        case METHODID_TASK:
          serviceImpl.task((shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskRequest) request,
              (io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.QueryOuterClass.QueryTaskResponse>) responseObserver);
          break;
        case METHODID_RESPONSE:
          serviceImpl.response((shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseRequest) request,
              (io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.QueryOuterClass.QueryResponseResponse>) responseObserver);
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
      return shentu.oracle.v1alpha1.QueryOuterClass.getDescriptor();
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
              .addMethod(getOperatorMethod())
              .addMethod(getOperatorsMethod())
              .addMethod(getWithdrawsMethod())
              .addMethod(getTaskMethod())
              .addMethod(getResponseMethod())
              .build();
        }
      }
    }
    return result;
  }
}
