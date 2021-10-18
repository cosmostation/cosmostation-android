package irismod.coinswap;

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
 * Query creates service with coinswap as rpc
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: coinswap/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "irismod.coinswap.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<irismod.coinswap.QueryOuterClass.QueryLiquidityRequest,
      irismod.coinswap.QueryOuterClass.QueryLiquidityResponse> getLiquidityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Liquidity",
      requestType = irismod.coinswap.QueryOuterClass.QueryLiquidityRequest.class,
      responseType = irismod.coinswap.QueryOuterClass.QueryLiquidityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.coinswap.QueryOuterClass.QueryLiquidityRequest,
      irismod.coinswap.QueryOuterClass.QueryLiquidityResponse> getLiquidityMethod() {
    io.grpc.MethodDescriptor<irismod.coinswap.QueryOuterClass.QueryLiquidityRequest, irismod.coinswap.QueryOuterClass.QueryLiquidityResponse> getLiquidityMethod;
    if ((getLiquidityMethod = QueryGrpc.getLiquidityMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLiquidityMethod = QueryGrpc.getLiquidityMethod) == null) {
          QueryGrpc.getLiquidityMethod = getLiquidityMethod =
              io.grpc.MethodDescriptor.<irismod.coinswap.QueryOuterClass.QueryLiquidityRequest, irismod.coinswap.QueryOuterClass.QueryLiquidityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Liquidity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.coinswap.QueryOuterClass.QueryLiquidityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.coinswap.QueryOuterClass.QueryLiquidityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Liquidity"))
              .build();
        }
      }
    }
    return getLiquidityMethod;
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
   * Query creates service with coinswap as rpc
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Liquidity returns the total liquidity available for the provided denomination
     * </pre>
     */
    public void liquidity(irismod.coinswap.QueryOuterClass.QueryLiquidityRequest request,
        io.grpc.stub.StreamObserver<irismod.coinswap.QueryOuterClass.QueryLiquidityResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLiquidityMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLiquidityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.coinswap.QueryOuterClass.QueryLiquidityRequest,
                irismod.coinswap.QueryOuterClass.QueryLiquidityResponse>(
                  this, METHODID_LIQUIDITY)))
          .build();
    }
  }

  /**
   * <pre>
   * Query creates service with coinswap as rpc
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
     * Liquidity returns the total liquidity available for the provided denomination
     * </pre>
     */
    public void liquidity(irismod.coinswap.QueryOuterClass.QueryLiquidityRequest request,
        io.grpc.stub.StreamObserver<irismod.coinswap.QueryOuterClass.QueryLiquidityResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLiquidityMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query creates service with coinswap as rpc
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
     * Liquidity returns the total liquidity available for the provided denomination
     * </pre>
     */
    public irismod.coinswap.QueryOuterClass.QueryLiquidityResponse liquidity(irismod.coinswap.QueryOuterClass.QueryLiquidityRequest request) {
      return blockingUnaryCall(
          getChannel(), getLiquidityMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query creates service with coinswap as rpc
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
     * Liquidity returns the total liquidity available for the provided denomination
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.coinswap.QueryOuterClass.QueryLiquidityResponse> liquidity(
        irismod.coinswap.QueryOuterClass.QueryLiquidityRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLiquidityMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIQUIDITY = 0;

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
        case METHODID_LIQUIDITY:
          serviceImpl.liquidity((irismod.coinswap.QueryOuterClass.QueryLiquidityRequest) request,
              (io.grpc.stub.StreamObserver<irismod.coinswap.QueryOuterClass.QueryLiquidityResponse>) responseObserver);
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
      return irismod.coinswap.QueryOuterClass.getDescriptor();
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
              .addMethod(getLiquidityMethod())
              .build();
        }
      }
    }
    return result;
  }
}
