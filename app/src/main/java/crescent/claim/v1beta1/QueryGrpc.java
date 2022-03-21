package crescent.claim.v1beta1;

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
    comments = "Source: crescent/claim/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "crescent.claim.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsRequest,
      crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsResponse> getAirdropsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Airdrops",
      requestType = crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsRequest.class,
      responseType = crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsRequest,
      crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsResponse> getAirdropsMethod() {
    io.grpc.MethodDescriptor<crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsRequest, crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsResponse> getAirdropsMethod;
    if ((getAirdropsMethod = QueryGrpc.getAirdropsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAirdropsMethod = QueryGrpc.getAirdropsMethod) == null) {
          QueryGrpc.getAirdropsMethod = getAirdropsMethod =
              io.grpc.MethodDescriptor.<crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsRequest, crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Airdrops"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Airdrops"))
              .build();
        }
      }
    }
    return getAirdropsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.claim.v1beta1.QueryOuterClass.QueryAirdropRequest,
      crescent.claim.v1beta1.QueryOuterClass.QueryAirdropResponse> getAirdropMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Airdrop",
      requestType = crescent.claim.v1beta1.QueryOuterClass.QueryAirdropRequest.class,
      responseType = crescent.claim.v1beta1.QueryOuterClass.QueryAirdropResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.claim.v1beta1.QueryOuterClass.QueryAirdropRequest,
      crescent.claim.v1beta1.QueryOuterClass.QueryAirdropResponse> getAirdropMethod() {
    io.grpc.MethodDescriptor<crescent.claim.v1beta1.QueryOuterClass.QueryAirdropRequest, crescent.claim.v1beta1.QueryOuterClass.QueryAirdropResponse> getAirdropMethod;
    if ((getAirdropMethod = QueryGrpc.getAirdropMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAirdropMethod = QueryGrpc.getAirdropMethod) == null) {
          QueryGrpc.getAirdropMethod = getAirdropMethod =
              io.grpc.MethodDescriptor.<crescent.claim.v1beta1.QueryOuterClass.QueryAirdropRequest, crescent.claim.v1beta1.QueryOuterClass.QueryAirdropResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Airdrop"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.claim.v1beta1.QueryOuterClass.QueryAirdropRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.claim.v1beta1.QueryOuterClass.QueryAirdropResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Airdrop"))
              .build();
        }
      }
    }
    return getAirdropMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest,
      crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse> getClaimRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimRecord",
      requestType = crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest.class,
      responseType = crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest,
      crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse> getClaimRecordMethod() {
    io.grpc.MethodDescriptor<crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest, crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse> getClaimRecordMethod;
    if ((getClaimRecordMethod = QueryGrpc.getClaimRecordMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClaimRecordMethod = QueryGrpc.getClaimRecordMethod) == null) {
          QueryGrpc.getClaimRecordMethod = getClaimRecordMethod =
              io.grpc.MethodDescriptor.<crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest, crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClaimRecord"))
              .build();
        }
      }
    }
    return getClaimRecordMethod;
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
     * Airdrops returns all airdrops.
     * </pre>
     */
    public void airdrops(crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsRequest request,
        io.grpc.stub.StreamObserver<crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAirdropsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Airdrop returns the specific airdrop.
     * </pre>
     */
    public void airdrop(crescent.claim.v1beta1.QueryOuterClass.QueryAirdropRequest request,
        io.grpc.stub.StreamObserver<crescent.claim.v1beta1.QueryOuterClass.QueryAirdropResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAirdropMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClaimRecord returns the claim record for the recipient address.
     * </pre>
     */
    public void claimRecord(crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest request,
        io.grpc.stub.StreamObserver<crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClaimRecordMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAirdropsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsRequest,
                crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsResponse>(
                  this, METHODID_AIRDROPS)))
          .addMethod(
            getAirdropMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.claim.v1beta1.QueryOuterClass.QueryAirdropRequest,
                crescent.claim.v1beta1.QueryOuterClass.QueryAirdropResponse>(
                  this, METHODID_AIRDROP)))
          .addMethod(
            getClaimRecordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest,
                crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse>(
                  this, METHODID_CLAIM_RECORD)))
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
     * Airdrops returns all airdrops.
     * </pre>
     */
    public void airdrops(crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsRequest request,
        io.grpc.stub.StreamObserver<crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAirdropsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Airdrop returns the specific airdrop.
     * </pre>
     */
    public void airdrop(crescent.claim.v1beta1.QueryOuterClass.QueryAirdropRequest request,
        io.grpc.stub.StreamObserver<crescent.claim.v1beta1.QueryOuterClass.QueryAirdropResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAirdropMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClaimRecord returns the claim record for the recipient address.
     * </pre>
     */
    public void claimRecord(crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest request,
        io.grpc.stub.StreamObserver<crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClaimRecordMethod(), getCallOptions()), request, responseObserver);
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
     * Airdrops returns all airdrops.
     * </pre>
     */
    public crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsResponse airdrops(crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAirdropsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Airdrop returns the specific airdrop.
     * </pre>
     */
    public crescent.claim.v1beta1.QueryOuterClass.QueryAirdropResponse airdrop(crescent.claim.v1beta1.QueryOuterClass.QueryAirdropRequest request) {
      return blockingUnaryCall(
          getChannel(), getAirdropMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClaimRecord returns the claim record for the recipient address.
     * </pre>
     */
    public crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse claimRecord(crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest request) {
      return blockingUnaryCall(
          getChannel(), getClaimRecordMethod(), getCallOptions(), request);
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
     * Airdrops returns all airdrops.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsResponse> airdrops(
        crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAirdropsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Airdrop returns the specific airdrop.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.claim.v1beta1.QueryOuterClass.QueryAirdropResponse> airdrop(
        crescent.claim.v1beta1.QueryOuterClass.QueryAirdropRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAirdropMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClaimRecord returns the claim record for the recipient address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse> claimRecord(
        crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getClaimRecordMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_AIRDROPS = 0;
  private static final int METHODID_AIRDROP = 1;
  private static final int METHODID_CLAIM_RECORD = 2;

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
        case METHODID_AIRDROPS:
          serviceImpl.airdrops((crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsRequest) request,
              (io.grpc.stub.StreamObserver<crescent.claim.v1beta1.QueryOuterClass.QueryAirdropsResponse>) responseObserver);
          break;
        case METHODID_AIRDROP:
          serviceImpl.airdrop((crescent.claim.v1beta1.QueryOuterClass.QueryAirdropRequest) request,
              (io.grpc.stub.StreamObserver<crescent.claim.v1beta1.QueryOuterClass.QueryAirdropResponse>) responseObserver);
          break;
        case METHODID_CLAIM_RECORD:
          serviceImpl.claimRecord((crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordRequest) request,
              (io.grpc.stub.StreamObserver<crescent.claim.v1beta1.QueryOuterClass.QueryClaimRecordResponse>) responseObserver);
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
      return crescent.claim.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getAirdropsMethod())
              .addMethod(getAirdropMethod())
              .addMethod(getClaimRecordMethod())
              .build();
        }
      }
    }
    return result;
  }
}
