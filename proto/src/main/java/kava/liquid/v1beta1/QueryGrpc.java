package kava.liquid.v1beta1;

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
 * Query defines the gRPC querier service for liquid module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/liquid/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "kava.liquid.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceRequest,
      kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceResponse> getDelegatedBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegatedBalance",
      requestType = kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceRequest.class,
      responseType = kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceRequest,
      kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceResponse> getDelegatedBalanceMethod() {
    io.grpc.MethodDescriptor<kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceRequest, kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceResponse> getDelegatedBalanceMethod;
    if ((getDelegatedBalanceMethod = QueryGrpc.getDelegatedBalanceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegatedBalanceMethod = QueryGrpc.getDelegatedBalanceMethod) == null) {
          QueryGrpc.getDelegatedBalanceMethod = getDelegatedBalanceMethod =
              io.grpc.MethodDescriptor.<kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceRequest, kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegatedBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegatedBalance"))
              .build();
        }
      }
    }
    return getDelegatedBalanceMethod;
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
   * Query defines the gRPC querier service for liquid module
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * DelegatedBalance returns an account's vesting and vested coins currently delegated to validators.
     * It ignores coins in unbonding delegations.
     * </pre>
     */
    public void delegatedBalance(kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceRequest request,
        io.grpc.stub.StreamObserver<kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDelegatedBalanceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDelegatedBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceRequest,
                kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceResponse>(
                  this, METHODID_DELEGATED_BALANCE)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for liquid module
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
     * DelegatedBalance returns an account's vesting and vested coins currently delegated to validators.
     * It ignores coins in unbonding delegations.
     * </pre>
     */
    public void delegatedBalance(kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceRequest request,
        io.grpc.stub.StreamObserver<kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDelegatedBalanceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for liquid module
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
     * DelegatedBalance returns an account's vesting and vested coins currently delegated to validators.
     * It ignores coins in unbonding delegations.
     * </pre>
     */
    public kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceResponse delegatedBalance(kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceRequest request) {
      return blockingUnaryCall(
          getChannel(), getDelegatedBalanceMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for liquid module
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
     * DelegatedBalance returns an account's vesting and vested coins currently delegated to validators.
     * It ignores coins in unbonding delegations.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceResponse> delegatedBalance(
        kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDelegatedBalanceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DELEGATED_BALANCE = 0;

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
        case METHODID_DELEGATED_BALANCE:
          serviceImpl.delegatedBalance((kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceRequest) request,
              (io.grpc.stub.StreamObserver<kava.liquid.v1beta1.QueryOuterClass.QueryDelegatedBalanceResponse>) responseObserver);
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
      return kava.liquid.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getDelegatedBalanceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
