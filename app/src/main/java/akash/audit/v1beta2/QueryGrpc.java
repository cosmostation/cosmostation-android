package akash.audit.v1beta2;

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
    comments = "Source: akash/audit/v1beta2/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "akash.audit.v1beta2.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<akash.audit.v1beta2.QueryOuterClass.QueryAllProvidersAttributesRequest,
      akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> getAllProvidersAttributesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllProvidersAttributes",
      requestType = akash.audit.v1beta2.QueryOuterClass.QueryAllProvidersAttributesRequest.class,
      responseType = akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.audit.v1beta2.QueryOuterClass.QueryAllProvidersAttributesRequest,
      akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> getAllProvidersAttributesMethod() {
    io.grpc.MethodDescriptor<akash.audit.v1beta2.QueryOuterClass.QueryAllProvidersAttributesRequest, akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> getAllProvidersAttributesMethod;
    if ((getAllProvidersAttributesMethod = QueryGrpc.getAllProvidersAttributesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllProvidersAttributesMethod = QueryGrpc.getAllProvidersAttributesMethod) == null) {
          QueryGrpc.getAllProvidersAttributesMethod = getAllProvidersAttributesMethod =
              io.grpc.MethodDescriptor.<akash.audit.v1beta2.QueryOuterClass.QueryAllProvidersAttributesRequest, akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllProvidersAttributes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.audit.v1beta2.QueryOuterClass.QueryAllProvidersAttributesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllProvidersAttributes"))
              .build();
        }
      }
    }
    return getAllProvidersAttributesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.audit.v1beta2.QueryOuterClass.QueryProviderAttributesRequest,
      akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> getProviderAttributesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProviderAttributes",
      requestType = akash.audit.v1beta2.QueryOuterClass.QueryProviderAttributesRequest.class,
      responseType = akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.audit.v1beta2.QueryOuterClass.QueryProviderAttributesRequest,
      akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> getProviderAttributesMethod() {
    io.grpc.MethodDescriptor<akash.audit.v1beta2.QueryOuterClass.QueryProviderAttributesRequest, akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> getProviderAttributesMethod;
    if ((getProviderAttributesMethod = QueryGrpc.getProviderAttributesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProviderAttributesMethod = QueryGrpc.getProviderAttributesMethod) == null) {
          QueryGrpc.getProviderAttributesMethod = getProviderAttributesMethod =
              io.grpc.MethodDescriptor.<akash.audit.v1beta2.QueryOuterClass.QueryProviderAttributesRequest, akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ProviderAttributes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.audit.v1beta2.QueryOuterClass.QueryProviderAttributesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ProviderAttributes"))
              .build();
        }
      }
    }
    return getProviderAttributesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.audit.v1beta2.QueryOuterClass.QueryProviderAuditorRequest,
      akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> getProviderAuditorAttributesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProviderAuditorAttributes",
      requestType = akash.audit.v1beta2.QueryOuterClass.QueryProviderAuditorRequest.class,
      responseType = akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.audit.v1beta2.QueryOuterClass.QueryProviderAuditorRequest,
      akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> getProviderAuditorAttributesMethod() {
    io.grpc.MethodDescriptor<akash.audit.v1beta2.QueryOuterClass.QueryProviderAuditorRequest, akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> getProviderAuditorAttributesMethod;
    if ((getProviderAuditorAttributesMethod = QueryGrpc.getProviderAuditorAttributesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProviderAuditorAttributesMethod = QueryGrpc.getProviderAuditorAttributesMethod) == null) {
          QueryGrpc.getProviderAuditorAttributesMethod = getProviderAuditorAttributesMethod =
              io.grpc.MethodDescriptor.<akash.audit.v1beta2.QueryOuterClass.QueryProviderAuditorRequest, akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ProviderAuditorAttributes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.audit.v1beta2.QueryOuterClass.QueryProviderAuditorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ProviderAuditorAttributes"))
              .build();
        }
      }
    }
    return getProviderAuditorAttributesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.audit.v1beta2.QueryOuterClass.QueryAuditorAttributesRequest,
      akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> getAuditorAttributesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AuditorAttributes",
      requestType = akash.audit.v1beta2.QueryOuterClass.QueryAuditorAttributesRequest.class,
      responseType = akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.audit.v1beta2.QueryOuterClass.QueryAuditorAttributesRequest,
      akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> getAuditorAttributesMethod() {
    io.grpc.MethodDescriptor<akash.audit.v1beta2.QueryOuterClass.QueryAuditorAttributesRequest, akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> getAuditorAttributesMethod;
    if ((getAuditorAttributesMethod = QueryGrpc.getAuditorAttributesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAuditorAttributesMethod = QueryGrpc.getAuditorAttributesMethod) == null) {
          QueryGrpc.getAuditorAttributesMethod = getAuditorAttributesMethod =
              io.grpc.MethodDescriptor.<akash.audit.v1beta2.QueryOuterClass.QueryAuditorAttributesRequest, akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AuditorAttributes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.audit.v1beta2.QueryOuterClass.QueryAuditorAttributesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AuditorAttributes"))
              .build();
        }
      }
    }
    return getAuditorAttributesMethod;
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
     * AllProvidersAttributes queries all providers
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * </pre>
     */
    public void allProvidersAttributes(akash.audit.v1beta2.QueryOuterClass.QueryAllProvidersAttributesRequest request,
        io.grpc.stub.StreamObserver<akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAllProvidersAttributesMethod(), responseObserver);
    }

    /**
     * <pre>
     * ProviderAttributes queries all provider signed attributes
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * </pre>
     */
    public void providerAttributes(akash.audit.v1beta2.QueryOuterClass.QueryProviderAttributesRequest request,
        io.grpc.stub.StreamObserver<akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProviderAttributesMethod(), responseObserver);
    }

    /**
     * <pre>
     * ProviderAuditorAttributes queries provider signed attributes by specific auditor
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * </pre>
     */
    public void providerAuditorAttributes(akash.audit.v1beta2.QueryOuterClass.QueryProviderAuditorRequest request,
        io.grpc.stub.StreamObserver<akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProviderAuditorAttributesMethod(), responseObserver);
    }

    /**
     * <pre>
     * AuditorAttributes queries all providers signed by this auditor
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * </pre>
     */
    public void auditorAttributes(akash.audit.v1beta2.QueryOuterClass.QueryAuditorAttributesRequest request,
        io.grpc.stub.StreamObserver<akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAuditorAttributesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAllProvidersAttributesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.audit.v1beta2.QueryOuterClass.QueryAllProvidersAttributesRequest,
                akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse>(
                  this, METHODID_ALL_PROVIDERS_ATTRIBUTES)))
          .addMethod(
            getProviderAttributesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.audit.v1beta2.QueryOuterClass.QueryProviderAttributesRequest,
                akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse>(
                  this, METHODID_PROVIDER_ATTRIBUTES)))
          .addMethod(
            getProviderAuditorAttributesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.audit.v1beta2.QueryOuterClass.QueryProviderAuditorRequest,
                akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse>(
                  this, METHODID_PROVIDER_AUDITOR_ATTRIBUTES)))
          .addMethod(
            getAuditorAttributesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.audit.v1beta2.QueryOuterClass.QueryAuditorAttributesRequest,
                akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse>(
                  this, METHODID_AUDITOR_ATTRIBUTES)))
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
     * AllProvidersAttributes queries all providers
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * </pre>
     */
    public void allProvidersAttributes(akash.audit.v1beta2.QueryOuterClass.QueryAllProvidersAttributesRequest request,
        io.grpc.stub.StreamObserver<akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAllProvidersAttributesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ProviderAttributes queries all provider signed attributes
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * </pre>
     */
    public void providerAttributes(akash.audit.v1beta2.QueryOuterClass.QueryProviderAttributesRequest request,
        io.grpc.stub.StreamObserver<akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProviderAttributesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ProviderAuditorAttributes queries provider signed attributes by specific auditor
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * </pre>
     */
    public void providerAuditorAttributes(akash.audit.v1beta2.QueryOuterClass.QueryProviderAuditorRequest request,
        io.grpc.stub.StreamObserver<akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProviderAuditorAttributesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AuditorAttributes queries all providers signed by this auditor
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * </pre>
     */
    public void auditorAttributes(akash.audit.v1beta2.QueryOuterClass.QueryAuditorAttributesRequest request,
        io.grpc.stub.StreamObserver<akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAuditorAttributesMethod(), getCallOptions()), request, responseObserver);
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
     * AllProvidersAttributes queries all providers
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * </pre>
     */
    public akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse allProvidersAttributes(akash.audit.v1beta2.QueryOuterClass.QueryAllProvidersAttributesRequest request) {
      return blockingUnaryCall(
          getChannel(), getAllProvidersAttributesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ProviderAttributes queries all provider signed attributes
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * </pre>
     */
    public akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse providerAttributes(akash.audit.v1beta2.QueryOuterClass.QueryProviderAttributesRequest request) {
      return blockingUnaryCall(
          getChannel(), getProviderAttributesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ProviderAuditorAttributes queries provider signed attributes by specific auditor
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * </pre>
     */
    public akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse providerAuditorAttributes(akash.audit.v1beta2.QueryOuterClass.QueryProviderAuditorRequest request) {
      return blockingUnaryCall(
          getChannel(), getProviderAuditorAttributesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AuditorAttributes queries all providers signed by this auditor
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * </pre>
     */
    public akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse auditorAttributes(akash.audit.v1beta2.QueryOuterClass.QueryAuditorAttributesRequest request) {
      return blockingUnaryCall(
          getChannel(), getAuditorAttributesMethod(), getCallOptions(), request);
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
     * AllProvidersAttributes queries all providers
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> allProvidersAttributes(
        akash.audit.v1beta2.QueryOuterClass.QueryAllProvidersAttributesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAllProvidersAttributesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ProviderAttributes queries all provider signed attributes
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> providerAttributes(
        akash.audit.v1beta2.QueryOuterClass.QueryProviderAttributesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProviderAttributesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ProviderAuditorAttributes queries provider signed attributes by specific auditor
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> providerAuditorAttributes(
        akash.audit.v1beta2.QueryOuterClass.QueryProviderAuditorRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProviderAuditorAttributesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AuditorAttributes queries all providers signed by this auditor
     * buf:lint:ignore RPC_REQUEST_RESPONSE_UNIQUE
     * buf:lint:ignore RPC_RESPONSE_STANDARD_NAME
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse> auditorAttributes(
        akash.audit.v1beta2.QueryOuterClass.QueryAuditorAttributesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAuditorAttributesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ALL_PROVIDERS_ATTRIBUTES = 0;
  private static final int METHODID_PROVIDER_ATTRIBUTES = 1;
  private static final int METHODID_PROVIDER_AUDITOR_ATTRIBUTES = 2;
  private static final int METHODID_AUDITOR_ATTRIBUTES = 3;

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
        case METHODID_ALL_PROVIDERS_ATTRIBUTES:
          serviceImpl.allProvidersAttributes((akash.audit.v1beta2.QueryOuterClass.QueryAllProvidersAttributesRequest) request,
              (io.grpc.stub.StreamObserver<akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse>) responseObserver);
          break;
        case METHODID_PROVIDER_ATTRIBUTES:
          serviceImpl.providerAttributes((akash.audit.v1beta2.QueryOuterClass.QueryProviderAttributesRequest) request,
              (io.grpc.stub.StreamObserver<akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse>) responseObserver);
          break;
        case METHODID_PROVIDER_AUDITOR_ATTRIBUTES:
          serviceImpl.providerAuditorAttributes((akash.audit.v1beta2.QueryOuterClass.QueryProviderAuditorRequest) request,
              (io.grpc.stub.StreamObserver<akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse>) responseObserver);
          break;
        case METHODID_AUDITOR_ATTRIBUTES:
          serviceImpl.auditorAttributes((akash.audit.v1beta2.QueryOuterClass.QueryAuditorAttributesRequest) request,
              (io.grpc.stub.StreamObserver<akash.audit.v1beta2.QueryOuterClass.QueryProvidersResponse>) responseObserver);
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
      return akash.audit.v1beta2.QueryOuterClass.getDescriptor();
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
              .addMethod(getAllProvidersAttributesMethod())
              .addMethod(getProviderAttributesMethod())
              .addMethod(getProviderAuditorAttributesMethod())
              .addMethod(getAuditorAttributesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
