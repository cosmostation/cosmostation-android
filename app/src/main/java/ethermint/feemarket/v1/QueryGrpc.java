package ethermint.feemarket.v1;

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
    comments = "Source: ethermint/feemarket/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "ethermint.feemarket.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ethermint.feemarket.v1.QueryOuterClass.QueryParamsRequest,
      ethermint.feemarket.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = ethermint.feemarket.v1.QueryOuterClass.QueryParamsRequest.class,
      responseType = ethermint.feemarket.v1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ethermint.feemarket.v1.QueryOuterClass.QueryParamsRequest,
      ethermint.feemarket.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<ethermint.feemarket.v1.QueryOuterClass.QueryParamsRequest, ethermint.feemarket.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<ethermint.feemarket.v1.QueryOuterClass.QueryParamsRequest, ethermint.feemarket.v1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.feemarket.v1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.feemarket.v1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeRequest,
      ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeResponse> getBaseFeeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BaseFee",
      requestType = ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeRequest.class,
      responseType = ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeRequest,
      ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeResponse> getBaseFeeMethod() {
    io.grpc.MethodDescriptor<ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeRequest, ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeResponse> getBaseFeeMethod;
    if ((getBaseFeeMethod = QueryGrpc.getBaseFeeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBaseFeeMethod = QueryGrpc.getBaseFeeMethod) == null) {
          QueryGrpc.getBaseFeeMethod = getBaseFeeMethod =
              io.grpc.MethodDescriptor.<ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeRequest, ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BaseFee"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BaseFee"))
              .build();
        }
      }
    }
    return getBaseFeeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasRequest,
      ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasResponse> getBlockGasMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BlockGas",
      requestType = ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasRequest.class,
      responseType = ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasRequest,
      ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasResponse> getBlockGasMethod() {
    io.grpc.MethodDescriptor<ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasRequest, ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasResponse> getBlockGasMethod;
    if ((getBlockGasMethod = QueryGrpc.getBlockGasMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBlockGasMethod = QueryGrpc.getBlockGasMethod) == null) {
          QueryGrpc.getBlockGasMethod = getBlockGasMethod =
              io.grpc.MethodDescriptor.<ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasRequest, ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BlockGas"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BlockGas"))
              .build();
        }
      }
    }
    return getBlockGasMethod;
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
     * Params queries the parameters of x/feemarket module.
     * </pre>
     */
    public void params(ethermint.feemarket.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<ethermint.feemarket.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * BaseFee queries the base fee of the parent block of the current block.
     * </pre>
     */
    public void baseFee(ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeRequest request,
        io.grpc.stub.StreamObserver<ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBaseFeeMethod(), responseObserver);
    }

    /**
     * <pre>
     * BlockGas queries the gas used at a given block height
     * </pre>
     */
    public void blockGas(ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasRequest request,
        io.grpc.stub.StreamObserver<ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBlockGasMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ethermint.feemarket.v1.QueryOuterClass.QueryParamsRequest,
                ethermint.feemarket.v1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getBaseFeeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeRequest,
                ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeResponse>(
                  this, METHODID_BASE_FEE)))
          .addMethod(
            getBlockGasMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasRequest,
                ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasResponse>(
                  this, METHODID_BLOCK_GAS)))
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
     * Params queries the parameters of x/feemarket module.
     * </pre>
     */
    public void params(ethermint.feemarket.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<ethermint.feemarket.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BaseFee queries the base fee of the parent block of the current block.
     * </pre>
     */
    public void baseFee(ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeRequest request,
        io.grpc.stub.StreamObserver<ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBaseFeeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BlockGas queries the gas used at a given block height
     * </pre>
     */
    public void blockGas(ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasRequest request,
        io.grpc.stub.StreamObserver<ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBlockGasMethod(), getCallOptions()), request, responseObserver);
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
     * Params queries the parameters of x/feemarket module.
     * </pre>
     */
    public ethermint.feemarket.v1.QueryOuterClass.QueryParamsResponse params(ethermint.feemarket.v1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BaseFee queries the base fee of the parent block of the current block.
     * </pre>
     */
    public ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeResponse baseFee(ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeRequest request) {
      return blockingUnaryCall(
          getChannel(), getBaseFeeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BlockGas queries the gas used at a given block height
     * </pre>
     */
    public ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasResponse blockGas(ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasRequest request) {
      return blockingUnaryCall(
          getChannel(), getBlockGasMethod(), getCallOptions(), request);
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
     * Params queries the parameters of x/feemarket module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ethermint.feemarket.v1.QueryOuterClass.QueryParamsResponse> params(
        ethermint.feemarket.v1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BaseFee queries the base fee of the parent block of the current block.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeResponse> baseFee(
        ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBaseFeeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BlockGas queries the gas used at a given block height
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasResponse> blockGas(
        ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBlockGasMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_BASE_FEE = 1;
  private static final int METHODID_BLOCK_GAS = 2;

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
          serviceImpl.params((ethermint.feemarket.v1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<ethermint.feemarket.v1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_BASE_FEE:
          serviceImpl.baseFee((ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeRequest) request,
              (io.grpc.stub.StreamObserver<ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeResponse>) responseObserver);
          break;
        case METHODID_BLOCK_GAS:
          serviceImpl.blockGas((ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasRequest) request,
              (io.grpc.stub.StreamObserver<ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasResponse>) responseObserver);
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
      return ethermint.feemarket.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getBaseFeeMethod())
              .addMethod(getBlockGasMethod())
              .build();
        }
      }
    }
    return result;
  }
}
