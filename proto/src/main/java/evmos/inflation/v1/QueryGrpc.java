package evmos.inflation.v1;

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
    comments = "Source: evmos/inflation/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "evmos.inflation.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<evmos.inflation.v1.QueryOuterClass.QueryPeriodRequest,
      evmos.inflation.v1.QueryOuterClass.QueryPeriodResponse> getPeriodMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Period",
      requestType = evmos.inflation.v1.QueryOuterClass.QueryPeriodRequest.class,
      responseType = evmos.inflation.v1.QueryOuterClass.QueryPeriodResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<evmos.inflation.v1.QueryOuterClass.QueryPeriodRequest,
      evmos.inflation.v1.QueryOuterClass.QueryPeriodResponse> getPeriodMethod() {
    io.grpc.MethodDescriptor<evmos.inflation.v1.QueryOuterClass.QueryPeriodRequest, evmos.inflation.v1.QueryOuterClass.QueryPeriodResponse> getPeriodMethod;
    if ((getPeriodMethod = QueryGrpc.getPeriodMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPeriodMethod = QueryGrpc.getPeriodMethod) == null) {
          QueryGrpc.getPeriodMethod = getPeriodMethod =
              io.grpc.MethodDescriptor.<evmos.inflation.v1.QueryOuterClass.QueryPeriodRequest, evmos.inflation.v1.QueryOuterClass.QueryPeriodResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Period"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.inflation.v1.QueryOuterClass.QueryPeriodRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.inflation.v1.QueryOuterClass.QueryPeriodResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Period"))
              .build();
        }
      }
    }
    return getPeriodMethod;
  }

  private static volatile io.grpc.MethodDescriptor<evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionRequest,
      evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionResponse> getEpochMintProvisionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochMintProvision",
      requestType = evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionRequest.class,
      responseType = evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionRequest,
      evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionResponse> getEpochMintProvisionMethod() {
    io.grpc.MethodDescriptor<evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionRequest, evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionResponse> getEpochMintProvisionMethod;
    if ((getEpochMintProvisionMethod = QueryGrpc.getEpochMintProvisionMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochMintProvisionMethod = QueryGrpc.getEpochMintProvisionMethod) == null) {
          QueryGrpc.getEpochMintProvisionMethod = getEpochMintProvisionMethod =
              io.grpc.MethodDescriptor.<evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionRequest, evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochMintProvision"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochMintProvision"))
              .build();
        }
      }
    }
    return getEpochMintProvisionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<evmos.inflation.v1.QueryOuterClass.QueryParamsRequest,
      evmos.inflation.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = evmos.inflation.v1.QueryOuterClass.QueryParamsRequest.class,
      responseType = evmos.inflation.v1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<evmos.inflation.v1.QueryOuterClass.QueryParamsRequest,
      evmos.inflation.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<evmos.inflation.v1.QueryOuterClass.QueryParamsRequest, evmos.inflation.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<evmos.inflation.v1.QueryOuterClass.QueryParamsRequest, evmos.inflation.v1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.inflation.v1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  evmos.inflation.v1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
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
   * Query provides defines the gRPC querier service.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Period retrieves current period.
     * </pre>
     */
    public void period(evmos.inflation.v1.QueryOuterClass.QueryPeriodRequest request,
        io.grpc.stub.StreamObserver<evmos.inflation.v1.QueryOuterClass.QueryPeriodResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPeriodMethod(), responseObserver);
    }

    /**
     * <pre>
     * EpochMintProvision retrieves current minting epoch provision value.
     * </pre>
     */
    public void epochMintProvision(evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionRequest request,
        io.grpc.stub.StreamObserver<evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEpochMintProvisionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params retrieves the total set of minting parameters.
     * </pre>
     */
    public void params(evmos.inflation.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<evmos.inflation.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPeriodMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                evmos.inflation.v1.QueryOuterClass.QueryPeriodRequest,
                evmos.inflation.v1.QueryOuterClass.QueryPeriodResponse>(
                  this, METHODID_PERIOD)))
          .addMethod(
            getEpochMintProvisionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionRequest,
                evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionResponse>(
                  this, METHODID_EPOCH_MINT_PROVISION)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                evmos.inflation.v1.QueryOuterClass.QueryParamsRequest,
                evmos.inflation.v1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
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
     * Period retrieves current period.
     * </pre>
     */
    public void period(evmos.inflation.v1.QueryOuterClass.QueryPeriodRequest request,
        io.grpc.stub.StreamObserver<evmos.inflation.v1.QueryOuterClass.QueryPeriodResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPeriodMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EpochMintProvision retrieves current minting epoch provision value.
     * </pre>
     */
    public void epochMintProvision(evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionRequest request,
        io.grpc.stub.StreamObserver<evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEpochMintProvisionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params retrieves the total set of minting parameters.
     * </pre>
     */
    public void params(evmos.inflation.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<evmos.inflation.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
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
     * Period retrieves current period.
     * </pre>
     */
    public evmos.inflation.v1.QueryOuterClass.QueryPeriodResponse period(evmos.inflation.v1.QueryOuterClass.QueryPeriodRequest request) {
      return blockingUnaryCall(
          getChannel(), getPeriodMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EpochMintProvision retrieves current minting epoch provision value.
     * </pre>
     */
    public evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionResponse epochMintProvision(evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionRequest request) {
      return blockingUnaryCall(
          getChannel(), getEpochMintProvisionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params retrieves the total set of minting parameters.
     * </pre>
     */
    public evmos.inflation.v1.QueryOuterClass.QueryParamsResponse params(evmos.inflation.v1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
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
     * Period retrieves current period.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<evmos.inflation.v1.QueryOuterClass.QueryPeriodResponse> period(
        evmos.inflation.v1.QueryOuterClass.QueryPeriodRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPeriodMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EpochMintProvision retrieves current minting epoch provision value.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionResponse> epochMintProvision(
        evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEpochMintProvisionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params retrieves the total set of minting parameters.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<evmos.inflation.v1.QueryOuterClass.QueryParamsResponse> params(
        evmos.inflation.v1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PERIOD = 0;
  private static final int METHODID_EPOCH_MINT_PROVISION = 1;
  private static final int METHODID_PARAMS = 2;

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
        case METHODID_PERIOD:
          serviceImpl.period((evmos.inflation.v1.QueryOuterClass.QueryPeriodRequest) request,
              (io.grpc.stub.StreamObserver<evmos.inflation.v1.QueryOuterClass.QueryPeriodResponse>) responseObserver);
          break;
        case METHODID_EPOCH_MINT_PROVISION:
          serviceImpl.epochMintProvision((evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionRequest) request,
              (io.grpc.stub.StreamObserver<evmos.inflation.v1.QueryOuterClass.QueryEpochMintProvisionResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((evmos.inflation.v1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<evmos.inflation.v1.QueryOuterClass.QueryParamsResponse>) responseObserver);
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
      return evmos.inflation.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getPeriodMethod())
              .addMethod(getEpochMintProvisionMethod())
              .addMethod(getParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
