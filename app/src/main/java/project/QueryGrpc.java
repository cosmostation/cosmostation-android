package project;

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
    comments = "Source: project/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "project.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<project.QueryOuterClass.QueryProjectDocRequest,
      project.QueryOuterClass.QueryProjectDocResponse> getProjectDocMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProjectDoc",
      requestType = project.QueryOuterClass.QueryProjectDocRequest.class,
      responseType = project.QueryOuterClass.QueryProjectDocResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<project.QueryOuterClass.QueryProjectDocRequest,
      project.QueryOuterClass.QueryProjectDocResponse> getProjectDocMethod() {
    io.grpc.MethodDescriptor<project.QueryOuterClass.QueryProjectDocRequest, project.QueryOuterClass.QueryProjectDocResponse> getProjectDocMethod;
    if ((getProjectDocMethod = QueryGrpc.getProjectDocMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProjectDocMethod = QueryGrpc.getProjectDocMethod) == null) {
          QueryGrpc.getProjectDocMethod = getProjectDocMethod =
              io.grpc.MethodDescriptor.<project.QueryOuterClass.QueryProjectDocRequest, project.QueryOuterClass.QueryProjectDocResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ProjectDoc"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.QueryOuterClass.QueryProjectDocRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.QueryOuterClass.QueryProjectDocResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ProjectDoc"))
              .build();
        }
      }
    }
    return getProjectDocMethod;
  }

  private static volatile io.grpc.MethodDescriptor<project.QueryOuterClass.QueryProjectAccountsRequest,
      project.QueryOuterClass.QueryProjectAccountsResponse> getProjectAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProjectAccounts",
      requestType = project.QueryOuterClass.QueryProjectAccountsRequest.class,
      responseType = project.QueryOuterClass.QueryProjectAccountsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<project.QueryOuterClass.QueryProjectAccountsRequest,
      project.QueryOuterClass.QueryProjectAccountsResponse> getProjectAccountsMethod() {
    io.grpc.MethodDescriptor<project.QueryOuterClass.QueryProjectAccountsRequest, project.QueryOuterClass.QueryProjectAccountsResponse> getProjectAccountsMethod;
    if ((getProjectAccountsMethod = QueryGrpc.getProjectAccountsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProjectAccountsMethod = QueryGrpc.getProjectAccountsMethod) == null) {
          QueryGrpc.getProjectAccountsMethod = getProjectAccountsMethod =
              io.grpc.MethodDescriptor.<project.QueryOuterClass.QueryProjectAccountsRequest, project.QueryOuterClass.QueryProjectAccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ProjectAccounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.QueryOuterClass.QueryProjectAccountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.QueryOuterClass.QueryProjectAccountsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ProjectAccounts"))
              .build();
        }
      }
    }
    return getProjectAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<project.QueryOuterClass.QueryProjectTxRequest,
      project.QueryOuterClass.QueryProjectTxResponse> getProjectTxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProjectTx",
      requestType = project.QueryOuterClass.QueryProjectTxRequest.class,
      responseType = project.QueryOuterClass.QueryProjectTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<project.QueryOuterClass.QueryProjectTxRequest,
      project.QueryOuterClass.QueryProjectTxResponse> getProjectTxMethod() {
    io.grpc.MethodDescriptor<project.QueryOuterClass.QueryProjectTxRequest, project.QueryOuterClass.QueryProjectTxResponse> getProjectTxMethod;
    if ((getProjectTxMethod = QueryGrpc.getProjectTxMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProjectTxMethod = QueryGrpc.getProjectTxMethod) == null) {
          QueryGrpc.getProjectTxMethod = getProjectTxMethod =
              io.grpc.MethodDescriptor.<project.QueryOuterClass.QueryProjectTxRequest, project.QueryOuterClass.QueryProjectTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ProjectTx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.QueryOuterClass.QueryProjectTxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.QueryOuterClass.QueryProjectTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ProjectTx"))
              .build();
        }
      }
    }
    return getProjectTxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<project.QueryOuterClass.QueryParamsRequest,
      project.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = project.QueryOuterClass.QueryParamsRequest.class,
      responseType = project.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<project.QueryOuterClass.QueryParamsRequest,
      project.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<project.QueryOuterClass.QueryParamsRequest, project.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<project.QueryOuterClass.QueryParamsRequest, project.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
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
     * ProjectDoc queries info of a specific project.
     * </pre>
     */
    public void projectDoc(project.QueryOuterClass.QueryProjectDocRequest request,
        io.grpc.stub.StreamObserver<project.QueryOuterClass.QueryProjectDocResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProjectDocMethod(), responseObserver);
    }

    /**
     * <pre>
     * ProjectAccounts lists a specific project's accounts.
     * </pre>
     */
    public void projectAccounts(project.QueryOuterClass.QueryProjectAccountsRequest request,
        io.grpc.stub.StreamObserver<project.QueryOuterClass.QueryProjectAccountsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProjectAccountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ProjectTx lists a specific project's transactions.
     * </pre>
     */
    public void projectTx(project.QueryOuterClass.QueryProjectTxRequest request,
        io.grpc.stub.StreamObserver<project.QueryOuterClass.QueryProjectTxResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProjectTxMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries the paramaters of x/project module.
     * </pre>
     */
    public void params(project.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<project.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getProjectDocMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                project.QueryOuterClass.QueryProjectDocRequest,
                project.QueryOuterClass.QueryProjectDocResponse>(
                  this, METHODID_PROJECT_DOC)))
          .addMethod(
            getProjectAccountsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                project.QueryOuterClass.QueryProjectAccountsRequest,
                project.QueryOuterClass.QueryProjectAccountsResponse>(
                  this, METHODID_PROJECT_ACCOUNTS)))
          .addMethod(
            getProjectTxMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                project.QueryOuterClass.QueryProjectTxRequest,
                project.QueryOuterClass.QueryProjectTxResponse>(
                  this, METHODID_PROJECT_TX)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                project.QueryOuterClass.QueryParamsRequest,
                project.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
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
     * ProjectDoc queries info of a specific project.
     * </pre>
     */
    public void projectDoc(project.QueryOuterClass.QueryProjectDocRequest request,
        io.grpc.stub.StreamObserver<project.QueryOuterClass.QueryProjectDocResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProjectDocMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ProjectAccounts lists a specific project's accounts.
     * </pre>
     */
    public void projectAccounts(project.QueryOuterClass.QueryProjectAccountsRequest request,
        io.grpc.stub.StreamObserver<project.QueryOuterClass.QueryProjectAccountsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProjectAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ProjectTx lists a specific project's transactions.
     * </pre>
     */
    public void projectTx(project.QueryOuterClass.QueryProjectTxRequest request,
        io.grpc.stub.StreamObserver<project.QueryOuterClass.QueryProjectTxResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProjectTxMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries the paramaters of x/project module.
     * </pre>
     */
    public void params(project.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<project.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
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
     * ProjectDoc queries info of a specific project.
     * </pre>
     */
    public project.QueryOuterClass.QueryProjectDocResponse projectDoc(project.QueryOuterClass.QueryProjectDocRequest request) {
      return blockingUnaryCall(
          getChannel(), getProjectDocMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ProjectAccounts lists a specific project's accounts.
     * </pre>
     */
    public project.QueryOuterClass.QueryProjectAccountsResponse projectAccounts(project.QueryOuterClass.QueryProjectAccountsRequest request) {
      return blockingUnaryCall(
          getChannel(), getProjectAccountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ProjectTx lists a specific project's transactions.
     * </pre>
     */
    public project.QueryOuterClass.QueryProjectTxResponse projectTx(project.QueryOuterClass.QueryProjectTxRequest request) {
      return blockingUnaryCall(
          getChannel(), getProjectTxMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries the paramaters of x/project module.
     * </pre>
     */
    public project.QueryOuterClass.QueryParamsResponse params(project.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
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
     * ProjectDoc queries info of a specific project.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<project.QueryOuterClass.QueryProjectDocResponse> projectDoc(
        project.QueryOuterClass.QueryProjectDocRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProjectDocMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ProjectAccounts lists a specific project's accounts.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<project.QueryOuterClass.QueryProjectAccountsResponse> projectAccounts(
        project.QueryOuterClass.QueryProjectAccountsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProjectAccountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ProjectTx lists a specific project's transactions.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<project.QueryOuterClass.QueryProjectTxResponse> projectTx(
        project.QueryOuterClass.QueryProjectTxRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProjectTxMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries the paramaters of x/project module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<project.QueryOuterClass.QueryParamsResponse> params(
        project.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PROJECT_DOC = 0;
  private static final int METHODID_PROJECT_ACCOUNTS = 1;
  private static final int METHODID_PROJECT_TX = 2;
  private static final int METHODID_PARAMS = 3;

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
        case METHODID_PROJECT_DOC:
          serviceImpl.projectDoc((project.QueryOuterClass.QueryProjectDocRequest) request,
              (io.grpc.stub.StreamObserver<project.QueryOuterClass.QueryProjectDocResponse>) responseObserver);
          break;
        case METHODID_PROJECT_ACCOUNTS:
          serviceImpl.projectAccounts((project.QueryOuterClass.QueryProjectAccountsRequest) request,
              (io.grpc.stub.StreamObserver<project.QueryOuterClass.QueryProjectAccountsResponse>) responseObserver);
          break;
        case METHODID_PROJECT_TX:
          serviceImpl.projectTx((project.QueryOuterClass.QueryProjectTxRequest) request,
              (io.grpc.stub.StreamObserver<project.QueryOuterClass.QueryProjectTxResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((project.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<project.QueryOuterClass.QueryParamsResponse>) responseObserver);
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
      return project.QueryOuterClass.getDescriptor();
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
              .addMethod(getProjectDocMethod())
              .addMethod(getProjectAccountsMethod())
              .addMethod(getProjectTxMethod())
              .addMethod(getParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
