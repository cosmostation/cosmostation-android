package io.provenance.msgfees.v1;

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
 * Query defines the gRPC querier service for marker module.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: provenance/msgfees/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "provenance.msgfees.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.provenance.msgfees.v1.QueryParamsRequest,
      io.provenance.msgfees.v1.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = io.provenance.msgfees.v1.QueryParamsRequest.class,
      responseType = io.provenance.msgfees.v1.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.msgfees.v1.QueryParamsRequest,
      io.provenance.msgfees.v1.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<io.provenance.msgfees.v1.QueryParamsRequest, io.provenance.msgfees.v1.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<io.provenance.msgfees.v1.QueryParamsRequest, io.provenance.msgfees.v1.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.msgfees.v1.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.msgfees.v1.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.msgfees.v1.QueryAllMsgFeesRequest,
      io.provenance.msgfees.v1.QueryAllMsgFeesResponse> getQueryAllMsgFeesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryAllMsgFees",
      requestType = io.provenance.msgfees.v1.QueryAllMsgFeesRequest.class,
      responseType = io.provenance.msgfees.v1.QueryAllMsgFeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.msgfees.v1.QueryAllMsgFeesRequest,
      io.provenance.msgfees.v1.QueryAllMsgFeesResponse> getQueryAllMsgFeesMethod() {
    io.grpc.MethodDescriptor<io.provenance.msgfees.v1.QueryAllMsgFeesRequest, io.provenance.msgfees.v1.QueryAllMsgFeesResponse> getQueryAllMsgFeesMethod;
    if ((getQueryAllMsgFeesMethod = QueryGrpc.getQueryAllMsgFeesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getQueryAllMsgFeesMethod = QueryGrpc.getQueryAllMsgFeesMethod) == null) {
          QueryGrpc.getQueryAllMsgFeesMethod = getQueryAllMsgFeesMethod =
              io.grpc.MethodDescriptor.<io.provenance.msgfees.v1.QueryAllMsgFeesRequest, io.provenance.msgfees.v1.QueryAllMsgFeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryAllMsgFees"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.msgfees.v1.QueryAllMsgFeesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.msgfees.v1.QueryAllMsgFeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("QueryAllMsgFees"))
              .build();
        }
      }
    }
    return getQueryAllMsgFeesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.msgfees.v1.CalculateTxFeesRequest,
      io.provenance.msgfees.v1.CalculateTxFeesResponse> getCalculateTxFeesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CalculateTxFees",
      requestType = io.provenance.msgfees.v1.CalculateTxFeesRequest.class,
      responseType = io.provenance.msgfees.v1.CalculateTxFeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.msgfees.v1.CalculateTxFeesRequest,
      io.provenance.msgfees.v1.CalculateTxFeesResponse> getCalculateTxFeesMethod() {
    io.grpc.MethodDescriptor<io.provenance.msgfees.v1.CalculateTxFeesRequest, io.provenance.msgfees.v1.CalculateTxFeesResponse> getCalculateTxFeesMethod;
    if ((getCalculateTxFeesMethod = QueryGrpc.getCalculateTxFeesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCalculateTxFeesMethod = QueryGrpc.getCalculateTxFeesMethod) == null) {
          QueryGrpc.getCalculateTxFeesMethod = getCalculateTxFeesMethod =
              io.grpc.MethodDescriptor.<io.provenance.msgfees.v1.CalculateTxFeesRequest, io.provenance.msgfees.v1.CalculateTxFeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CalculateTxFees"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.msgfees.v1.CalculateTxFeesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.msgfees.v1.CalculateTxFeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CalculateTxFees"))
              .build();
        }
      }
    }
    return getCalculateTxFeesMethod;
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
   * Query defines the gRPC querier service for marker module.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Params queries the parameters for x/msgfees
     * </pre>
     */
    public void params(io.provenance.msgfees.v1.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.msgfees.v1.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Query all Msgs which have fees associated with them.
     * </pre>
     */
    public void queryAllMsgFees(io.provenance.msgfees.v1.QueryAllMsgFeesRequest request,
        io.grpc.stub.StreamObserver<io.provenance.msgfees.v1.QueryAllMsgFeesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryAllMsgFeesMethod(), responseObserver);
    }

    /**
     * <pre>
     * CalculateTxFees simulates executing a transaction for estimating gas usage and additional fees.
     * </pre>
     */
    public void calculateTxFees(io.provenance.msgfees.v1.CalculateTxFeesRequest request,
        io.grpc.stub.StreamObserver<io.provenance.msgfees.v1.CalculateTxFeesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCalculateTxFeesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.msgfees.v1.QueryParamsRequest,
                io.provenance.msgfees.v1.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getQueryAllMsgFeesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.msgfees.v1.QueryAllMsgFeesRequest,
                io.provenance.msgfees.v1.QueryAllMsgFeesResponse>(
                  this, METHODID_QUERY_ALL_MSG_FEES)))
          .addMethod(
            getCalculateTxFeesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.msgfees.v1.CalculateTxFeesRequest,
                io.provenance.msgfees.v1.CalculateTxFeesResponse>(
                  this, METHODID_CALCULATE_TX_FEES)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for marker module.
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
     * Params queries the parameters for x/msgfees
     * </pre>
     */
    public void params(io.provenance.msgfees.v1.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.msgfees.v1.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Query all Msgs which have fees associated with them.
     * </pre>
     */
    public void queryAllMsgFees(io.provenance.msgfees.v1.QueryAllMsgFeesRequest request,
        io.grpc.stub.StreamObserver<io.provenance.msgfees.v1.QueryAllMsgFeesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryAllMsgFeesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CalculateTxFees simulates executing a transaction for estimating gas usage and additional fees.
     * </pre>
     */
    public void calculateTxFees(io.provenance.msgfees.v1.CalculateTxFeesRequest request,
        io.grpc.stub.StreamObserver<io.provenance.msgfees.v1.CalculateTxFeesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCalculateTxFeesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for marker module.
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
     * Params queries the parameters for x/msgfees
     * </pre>
     */
    public io.provenance.msgfees.v1.QueryParamsResponse params(io.provenance.msgfees.v1.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Query all Msgs which have fees associated with them.
     * </pre>
     */
    public io.provenance.msgfees.v1.QueryAllMsgFeesResponse queryAllMsgFees(io.provenance.msgfees.v1.QueryAllMsgFeesRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryAllMsgFeesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CalculateTxFees simulates executing a transaction for estimating gas usage and additional fees.
     * </pre>
     */
    public io.provenance.msgfees.v1.CalculateTxFeesResponse calculateTxFees(io.provenance.msgfees.v1.CalculateTxFeesRequest request) {
      return blockingUnaryCall(
          getChannel(), getCalculateTxFeesMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for marker module.
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
     * Params queries the parameters for x/msgfees
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.msgfees.v1.QueryParamsResponse> params(
        io.provenance.msgfees.v1.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Query all Msgs which have fees associated with them.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.msgfees.v1.QueryAllMsgFeesResponse> queryAllMsgFees(
        io.provenance.msgfees.v1.QueryAllMsgFeesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryAllMsgFeesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CalculateTxFees simulates executing a transaction for estimating gas usage and additional fees.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.msgfees.v1.CalculateTxFeesResponse> calculateTxFees(
        io.provenance.msgfees.v1.CalculateTxFeesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCalculateTxFeesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_QUERY_ALL_MSG_FEES = 1;
  private static final int METHODID_CALCULATE_TX_FEES = 2;

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
        case METHODID_PARAMS:
          serviceImpl.params((io.provenance.msgfees.v1.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.msgfees.v1.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_QUERY_ALL_MSG_FEES:
          serviceImpl.queryAllMsgFees((io.provenance.msgfees.v1.QueryAllMsgFeesRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.msgfees.v1.QueryAllMsgFeesResponse>) responseObserver);
          break;
        case METHODID_CALCULATE_TX_FEES:
          serviceImpl.calculateTxFees((io.provenance.msgfees.v1.CalculateTxFeesRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.msgfees.v1.CalculateTxFeesResponse>) responseObserver);
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
      return io.provenance.msgfees.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getParamsMethod())
              .addMethod(getQueryAllMsgFeesMethod())
              .addMethod(getCalculateTxFeesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
