package akash.deployment.v1beta1;

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
 * Query defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: akash/deployment/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "akash.deployment.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsRequest,
      akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsResponse> getDeploymentsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deployments",
      requestType = akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsRequest.class,
      responseType = akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsRequest,
      akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsResponse> getDeploymentsMethod() {
    io.grpc.MethodDescriptor<akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsRequest, akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsResponse> getDeploymentsMethod;
    if ((getDeploymentsMethod = QueryGrpc.getDeploymentsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDeploymentsMethod = QueryGrpc.getDeploymentsMethod) == null) {
          QueryGrpc.getDeploymentsMethod = getDeploymentsMethod =
              io.grpc.MethodDescriptor.<akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsRequest, akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deployments"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Deployments"))
              .build();
        }
      }
    }
    return getDeploymentsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentRequest,
      akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentResponse> getDeploymentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deployment",
      requestType = akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentRequest.class,
      responseType = akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentRequest,
      akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentResponse> getDeploymentMethod() {
    io.grpc.MethodDescriptor<akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentRequest, akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentResponse> getDeploymentMethod;
    if ((getDeploymentMethod = QueryGrpc.getDeploymentMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDeploymentMethod = QueryGrpc.getDeploymentMethod) == null) {
          QueryGrpc.getDeploymentMethod = getDeploymentMethod =
              io.grpc.MethodDescriptor.<akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentRequest, akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deployment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Deployment"))
              .build();
        }
      }
    }
    return getDeploymentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.deployment.v1beta1.QueryOuterClass.QueryGroupRequest,
      akash.deployment.v1beta1.QueryOuterClass.QueryGroupResponse> getGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Group",
      requestType = akash.deployment.v1beta1.QueryOuterClass.QueryGroupRequest.class,
      responseType = akash.deployment.v1beta1.QueryOuterClass.QueryGroupResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.deployment.v1beta1.QueryOuterClass.QueryGroupRequest,
      akash.deployment.v1beta1.QueryOuterClass.QueryGroupResponse> getGroupMethod() {
    io.grpc.MethodDescriptor<akash.deployment.v1beta1.QueryOuterClass.QueryGroupRequest, akash.deployment.v1beta1.QueryOuterClass.QueryGroupResponse> getGroupMethod;
    if ((getGroupMethod = QueryGrpc.getGroupMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGroupMethod = QueryGrpc.getGroupMethod) == null) {
          QueryGrpc.getGroupMethod = getGroupMethod =
              io.grpc.MethodDescriptor.<akash.deployment.v1beta1.QueryOuterClass.QueryGroupRequest, akash.deployment.v1beta1.QueryOuterClass.QueryGroupResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Group"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta1.QueryOuterClass.QueryGroupRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta1.QueryOuterClass.QueryGroupResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Group"))
              .build();
        }
      }
    }
    return getGroupMethod;
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
   * Query defines the gRPC querier service
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Deployments queries deployments
     * </pre>
     */
    public void deployments(akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsRequest request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeploymentsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Deployment queries deployment details
     * </pre>
     */
    public void deployment(akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentRequest request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeploymentMethod(), responseObserver);
    }

    /**
     * <pre>
     * Group queries group details
     * </pre>
     */
    public void group(akash.deployment.v1beta1.QueryOuterClass.QueryGroupRequest request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta1.QueryOuterClass.QueryGroupResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGroupMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDeploymentsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsRequest,
                akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsResponse>(
                  this, METHODID_DEPLOYMENTS)))
          .addMethod(
            getDeploymentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentRequest,
                akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentResponse>(
                  this, METHODID_DEPLOYMENT)))
          .addMethod(
            getGroupMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.deployment.v1beta1.QueryOuterClass.QueryGroupRequest,
                akash.deployment.v1beta1.QueryOuterClass.QueryGroupResponse>(
                  this, METHODID_GROUP)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     * Deployments queries deployments
     * </pre>
     */
    public void deployments(akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsRequest request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeploymentsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Deployment queries deployment details
     * </pre>
     */
    public void deployment(akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentRequest request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeploymentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Group queries group details
     * </pre>
     */
    public void group(akash.deployment.v1beta1.QueryOuterClass.QueryGroupRequest request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta1.QueryOuterClass.QueryGroupResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGroupMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     * Deployments queries deployments
     * </pre>
     */
    public akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsResponse deployments(akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeploymentsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Deployment queries deployment details
     * </pre>
     */
    public akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentResponse deployment(akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeploymentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Group queries group details
     * </pre>
     */
    public akash.deployment.v1beta1.QueryOuterClass.QueryGroupResponse group(akash.deployment.v1beta1.QueryOuterClass.QueryGroupRequest request) {
      return blockingUnaryCall(
          getChannel(), getGroupMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     * Deployments queries deployments
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsResponse> deployments(
        akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeploymentsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Deployment queries deployment details
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentResponse> deployment(
        akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeploymentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Group queries group details
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.deployment.v1beta1.QueryOuterClass.QueryGroupResponse> group(
        akash.deployment.v1beta1.QueryOuterClass.QueryGroupRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGroupMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DEPLOYMENTS = 0;
  private static final int METHODID_DEPLOYMENT = 1;
  private static final int METHODID_GROUP = 2;

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
        case METHODID_DEPLOYMENTS:
          serviceImpl.deployments((akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsRequest) request,
              (io.grpc.stub.StreamObserver<akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentsResponse>) responseObserver);
          break;
        case METHODID_DEPLOYMENT:
          serviceImpl.deployment((akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentRequest) request,
              (io.grpc.stub.StreamObserver<akash.deployment.v1beta1.QueryOuterClass.QueryDeploymentResponse>) responseObserver);
          break;
        case METHODID_GROUP:
          serviceImpl.group((akash.deployment.v1beta1.QueryOuterClass.QueryGroupRequest) request,
              (io.grpc.stub.StreamObserver<akash.deployment.v1beta1.QueryOuterClass.QueryGroupResponse>) responseObserver);
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
      return akash.deployment.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getDeploymentsMethod())
              .addMethod(getDeploymentMethod())
              .addMethod(getGroupMethod())
              .build();
        }
      }
    }
    return result;
  }
}
