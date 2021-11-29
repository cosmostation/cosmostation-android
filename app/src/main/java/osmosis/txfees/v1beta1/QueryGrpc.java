package osmosis.txfees.v1beta1;

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
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: osmosis/txfees/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "osmosis.txfees.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensRequest,
      osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensResponse> getFeeTokensMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FeeTokens",
      requestType = osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensRequest.class,
      responseType = osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensRequest,
      osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensResponse> getFeeTokensMethod() {
    io.grpc.MethodDescriptor<osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensRequest, osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensResponse> getFeeTokensMethod;
    if ((getFeeTokensMethod = QueryGrpc.getFeeTokensMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFeeTokensMethod = QueryGrpc.getFeeTokensMethod) == null) {
          QueryGrpc.getFeeTokensMethod = getFeeTokensMethod =
              io.grpc.MethodDescriptor.<osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensRequest, osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FeeTokens"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FeeTokens"))
              .build();
        }
      }
    }
    return getFeeTokensMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdRequest,
      osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdResponse> getDenomPoolIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomPoolId",
      requestType = osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdRequest.class,
      responseType = osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdRequest,
      osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdResponse> getDenomPoolIdMethod() {
    io.grpc.MethodDescriptor<osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdRequest, osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdResponse> getDenomPoolIdMethod;
    if ((getDenomPoolIdMethod = QueryGrpc.getDenomPoolIdMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomPoolIdMethod = QueryGrpc.getDenomPoolIdMethod) == null) {
          QueryGrpc.getDenomPoolIdMethod = getDenomPoolIdMethod =
              io.grpc.MethodDescriptor.<osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdRequest, osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomPoolId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomPoolId"))
              .build();
        }
      }
    }
    return getDenomPoolIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomRequest,
      osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomResponse> getBaseDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BaseDenom",
      requestType = osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomRequest.class,
      responseType = osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomRequest,
      osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomResponse> getBaseDenomMethod() {
    io.grpc.MethodDescriptor<osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomRequest, osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomResponse> getBaseDenomMethod;
    if ((getBaseDenomMethod = QueryGrpc.getBaseDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBaseDenomMethod = QueryGrpc.getBaseDenomMethod) == null) {
          QueryGrpc.getBaseDenomMethod = getBaseDenomMethod =
              io.grpc.MethodDescriptor.<osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomRequest, osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BaseDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BaseDenom"))
              .build();
        }
      }
    }
    return getBaseDenomMethod;
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
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * FeeTokens returns a list of all the whitelisted fee tokens and their
     * corresponding pools It does not include the BaseDenom, which has its own
     * query endpoint
     * </pre>
     */
    public void feeTokens(osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensRequest request,
        io.grpc.stub.StreamObserver<osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFeeTokensMethod(), responseObserver);
    }

    /**
     */
    public void denomPoolId(osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdRequest request,
        io.grpc.stub.StreamObserver<osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDenomPoolIdMethod(), responseObserver);
    }

    /**
     */
    public void baseDenom(osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomRequest request,
        io.grpc.stub.StreamObserver<osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBaseDenomMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFeeTokensMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensRequest,
                osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensResponse>(
                  this, METHODID_FEE_TOKENS)))
          .addMethod(
            getDenomPoolIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdRequest,
                osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdResponse>(
                  this, METHODID_DENOM_POOL_ID)))
          .addMethod(
            getBaseDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomRequest,
                osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomResponse>(
                  this, METHODID_BASE_DENOM)))
          .build();
    }
  }

  /**
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
     * FeeTokens returns a list of all the whitelisted fee tokens and their
     * corresponding pools It does not include the BaseDenom, which has its own
     * query endpoint
     * </pre>
     */
    public void feeTokens(osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensRequest request,
        io.grpc.stub.StreamObserver<osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFeeTokensMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void denomPoolId(osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdRequest request,
        io.grpc.stub.StreamObserver<osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDenomPoolIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void baseDenom(osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomRequest request,
        io.grpc.stub.StreamObserver<osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBaseDenomMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
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
     * FeeTokens returns a list of all the whitelisted fee tokens and their
     * corresponding pools It does not include the BaseDenom, which has its own
     * query endpoint
     * </pre>
     */
    public osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensResponse feeTokens(osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensRequest request) {
      return blockingUnaryCall(
          getChannel(), getFeeTokensMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdResponse denomPoolId(osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getDenomPoolIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomResponse baseDenom(osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomRequest request) {
      return blockingUnaryCall(
          getChannel(), getBaseDenomMethod(), getCallOptions(), request);
    }
  }

  /**
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
     * FeeTokens returns a list of all the whitelisted fee tokens and their
     * corresponding pools It does not include the BaseDenom, which has its own
     * query endpoint
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensResponse> feeTokens(
        osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFeeTokensMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdResponse> denomPoolId(
        osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDenomPoolIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomResponse> baseDenom(
        osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBaseDenomMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FEE_TOKENS = 0;
  private static final int METHODID_DENOM_POOL_ID = 1;
  private static final int METHODID_BASE_DENOM = 2;

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
        case METHODID_FEE_TOKENS:
          serviceImpl.feeTokens((osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.txfees.v1beta1.QueryOuterClass.QueryFeeTokensResponse>) responseObserver);
          break;
        case METHODID_DENOM_POOL_ID:
          serviceImpl.denomPoolId((osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.txfees.v1beta1.QueryOuterClass.QueryDenomPoolIdResponse>) responseObserver);
          break;
        case METHODID_BASE_DENOM:
          serviceImpl.baseDenom((osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.txfees.v1beta1.QueryOuterClass.QueryBaseDenomResponse>) responseObserver);
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
      return osmosis.txfees.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getFeeTokensMethod())
              .addMethod(getDenomPoolIdMethod())
              .addMethod(getBaseDenomMethod())
              .build();
        }
      }
    }
    return result;
  }
}
