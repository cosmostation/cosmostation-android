package juno.mint;

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
 * Query provides defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: juno/mint/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "CosmosContracts.juno.mint.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<juno.mint.QueryOuterClass.QueryParamsRequest,
      juno.mint.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = juno.mint.QueryOuterClass.QueryParamsRequest.class,
      responseType = juno.mint.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<juno.mint.QueryOuterClass.QueryParamsRequest,
      juno.mint.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<juno.mint.QueryOuterClass.QueryParamsRequest, juno.mint.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<juno.mint.QueryOuterClass.QueryParamsRequest, juno.mint.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  juno.mint.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  juno.mint.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<juno.mint.QueryOuterClass.QueryInflationRequest,
      juno.mint.QueryOuterClass.QueryInflationResponse> getInflationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Inflation",
      requestType = juno.mint.QueryOuterClass.QueryInflationRequest.class,
      responseType = juno.mint.QueryOuterClass.QueryInflationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<juno.mint.QueryOuterClass.QueryInflationRequest,
      juno.mint.QueryOuterClass.QueryInflationResponse> getInflationMethod() {
    io.grpc.MethodDescriptor<juno.mint.QueryOuterClass.QueryInflationRequest, juno.mint.QueryOuterClass.QueryInflationResponse> getInflationMethod;
    if ((getInflationMethod = QueryGrpc.getInflationMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getInflationMethod = QueryGrpc.getInflationMethod) == null) {
          QueryGrpc.getInflationMethod = getInflationMethod =
              io.grpc.MethodDescriptor.<juno.mint.QueryOuterClass.QueryInflationRequest, juno.mint.QueryOuterClass.QueryInflationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Inflation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  juno.mint.QueryOuterClass.QueryInflationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  juno.mint.QueryOuterClass.QueryInflationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Inflation"))
              .build();
        }
      }
    }
    return getInflationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<juno.mint.QueryOuterClass.QueryAnnualProvisionsRequest,
      juno.mint.QueryOuterClass.QueryAnnualProvisionsResponse> getAnnualProvisionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AnnualProvisions",
      requestType = juno.mint.QueryOuterClass.QueryAnnualProvisionsRequest.class,
      responseType = juno.mint.QueryOuterClass.QueryAnnualProvisionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<juno.mint.QueryOuterClass.QueryAnnualProvisionsRequest,
      juno.mint.QueryOuterClass.QueryAnnualProvisionsResponse> getAnnualProvisionsMethod() {
    io.grpc.MethodDescriptor<juno.mint.QueryOuterClass.QueryAnnualProvisionsRequest, juno.mint.QueryOuterClass.QueryAnnualProvisionsResponse> getAnnualProvisionsMethod;
    if ((getAnnualProvisionsMethod = QueryGrpc.getAnnualProvisionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAnnualProvisionsMethod = QueryGrpc.getAnnualProvisionsMethod) == null) {
          QueryGrpc.getAnnualProvisionsMethod = getAnnualProvisionsMethod =
              io.grpc.MethodDescriptor.<juno.mint.QueryOuterClass.QueryAnnualProvisionsRequest, juno.mint.QueryOuterClass.QueryAnnualProvisionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AnnualProvisions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  juno.mint.QueryOuterClass.QueryAnnualProvisionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  juno.mint.QueryOuterClass.QueryAnnualProvisionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AnnualProvisions"))
              .build();
        }
      }
    }
    return getAnnualProvisionsMethod;
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
   * Query provides defines the gRPC querier service.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Params returns the total set of minting parameters.
     * </pre>
     */
    public void params(juno.mint.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<juno.mint.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Inflation returns the current minting inflation value.
     * </pre>
     */
    public void inflation(juno.mint.QueryOuterClass.QueryInflationRequest request,
        io.grpc.stub.StreamObserver<juno.mint.QueryOuterClass.QueryInflationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInflationMethod(), responseObserver);
    }

    /**
     * <pre>
     * AnnualProvisions current minting annual provisions value.
     * </pre>
     */
    public void annualProvisions(juno.mint.QueryOuterClass.QueryAnnualProvisionsRequest request,
        io.grpc.stub.StreamObserver<juno.mint.QueryOuterClass.QueryAnnualProvisionsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAnnualProvisionsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                juno.mint.QueryOuterClass.QueryParamsRequest,
                juno.mint.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getInflationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                juno.mint.QueryOuterClass.QueryInflationRequest,
                juno.mint.QueryOuterClass.QueryInflationResponse>(
                  this, METHODID_INFLATION)))
          .addMethod(
            getAnnualProvisionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                juno.mint.QueryOuterClass.QueryAnnualProvisionsRequest,
                juno.mint.QueryOuterClass.QueryAnnualProvisionsResponse>(
                  this, METHODID_ANNUAL_PROVISIONS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service.
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
     * Params returns the total set of minting parameters.
     * </pre>
     */
    public void params(juno.mint.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<juno.mint.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Inflation returns the current minting inflation value.
     * </pre>
     */
    public void inflation(juno.mint.QueryOuterClass.QueryInflationRequest request,
        io.grpc.stub.StreamObserver<juno.mint.QueryOuterClass.QueryInflationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInflationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AnnualProvisions current minting annual provisions value.
     * </pre>
     */
    public void annualProvisions(juno.mint.QueryOuterClass.QueryAnnualProvisionsRequest request,
        io.grpc.stub.StreamObserver<juno.mint.QueryOuterClass.QueryAnnualProvisionsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAnnualProvisionsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service.
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
     * Params returns the total set of minting parameters.
     * </pre>
     */
    public juno.mint.QueryOuterClass.QueryParamsResponse params(juno.mint.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Inflation returns the current minting inflation value.
     * </pre>
     */
    public juno.mint.QueryOuterClass.QueryInflationResponse inflation(juno.mint.QueryOuterClass.QueryInflationRequest request) {
      return blockingUnaryCall(
          getChannel(), getInflationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AnnualProvisions current minting annual provisions value.
     * </pre>
     */
    public juno.mint.QueryOuterClass.QueryAnnualProvisionsResponse annualProvisions(juno.mint.QueryOuterClass.QueryAnnualProvisionsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAnnualProvisionsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service.
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
     * Params returns the total set of minting parameters.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<juno.mint.QueryOuterClass.QueryParamsResponse> params(
        juno.mint.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Inflation returns the current minting inflation value.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<juno.mint.QueryOuterClass.QueryInflationResponse> inflation(
        juno.mint.QueryOuterClass.QueryInflationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getInflationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AnnualProvisions current minting annual provisions value.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<juno.mint.QueryOuterClass.QueryAnnualProvisionsResponse> annualProvisions(
        juno.mint.QueryOuterClass.QueryAnnualProvisionsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAnnualProvisionsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_INFLATION = 1;
  private static final int METHODID_ANNUAL_PROVISIONS = 2;

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
          serviceImpl.params((juno.mint.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<juno.mint.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_INFLATION:
          serviceImpl.inflation((juno.mint.QueryOuterClass.QueryInflationRequest) request,
              (io.grpc.stub.StreamObserver<juno.mint.QueryOuterClass.QueryInflationResponse>) responseObserver);
          break;
        case METHODID_ANNUAL_PROVISIONS:
          serviceImpl.annualProvisions((juno.mint.QueryOuterClass.QueryAnnualProvisionsRequest) request,
              (io.grpc.stub.StreamObserver<juno.mint.QueryOuterClass.QueryAnnualProvisionsResponse>) responseObserver);
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
      return juno.mint.QueryOuterClass.getDescriptor();
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
              .addMethod(getInflationMethod())
              .addMethod(getAnnualProvisionsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
