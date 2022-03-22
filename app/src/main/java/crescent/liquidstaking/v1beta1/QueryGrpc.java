package crescent.liquidstaking.v1beta1;

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
 * Query defines the gRPC query service for the liquidstaking module.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: crescent/liquidstaking/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "crescent.liquidstaking.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsRequest,
      crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsRequest,
      crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsRequest, crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsRequest, crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsRequest,
      crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsResponse> getLiquidValidatorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiquidValidators",
      requestType = crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsRequest.class,
      responseType = crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsRequest,
      crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsResponse> getLiquidValidatorsMethod() {
    io.grpc.MethodDescriptor<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsRequest, crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsResponse> getLiquidValidatorsMethod;
    if ((getLiquidValidatorsMethod = QueryGrpc.getLiquidValidatorsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLiquidValidatorsMethod = QueryGrpc.getLiquidValidatorsMethod) == null) {
          QueryGrpc.getLiquidValidatorsMethod = getLiquidValidatorsMethod =
              io.grpc.MethodDescriptor.<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsRequest, crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LiquidValidators"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LiquidValidators"))
              .build();
        }
      }
    }
    return getLiquidValidatorsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerRequest,
      crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerResponse> getVotingPowerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "VotingPower",
      requestType = crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerRequest.class,
      responseType = crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerRequest,
      crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerResponse> getVotingPowerMethod() {
    io.grpc.MethodDescriptor<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerRequest, crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerResponse> getVotingPowerMethod;
    if ((getVotingPowerMethod = QueryGrpc.getVotingPowerMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getVotingPowerMethod = QueryGrpc.getVotingPowerMethod) == null) {
          QueryGrpc.getVotingPowerMethod = getVotingPowerMethod =
              io.grpc.MethodDescriptor.<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerRequest, crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "VotingPower"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("VotingPower"))
              .build();
        }
      }
    }
    return getVotingPowerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesRequest,
      crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesResponse> getStatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "States",
      requestType = crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesRequest.class,
      responseType = crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesRequest,
      crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesResponse> getStatesMethod() {
    io.grpc.MethodDescriptor<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesRequest, crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesResponse> getStatesMethod;
    if ((getStatesMethod = QueryGrpc.getStatesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getStatesMethod = QueryGrpc.getStatesMethod) == null) {
          QueryGrpc.getStatesMethod = getStatesMethod =
              io.grpc.MethodDescriptor.<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesRequest, crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "States"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("States"))
              .build();
        }
      }
    }
    return getStatesMethod;
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
   * Query defines the gRPC query service for the liquidstaking module.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Params returns parameters of the liquidstaking module.
     * </pre>
     */
    public void params(crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * LiquidValidators returns liquid validators with states of the liquidstaking module.
     * </pre>
     */
    public void liquidValidators(crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLiquidValidatorsMethod(), responseObserver);
    }

    /**
     * <pre>
     * VotingPower returns voting power of staking and liquid staking module's of the voter that can be exercised.
     * </pre>
     */
    public void votingPower(crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVotingPowerMethod(), responseObserver);
    }

    /**
     * <pre>
     * States returns states of the liquidstaking module.
     * </pre>
     */
    public void states(crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStatesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsRequest,
                crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getLiquidValidatorsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsRequest,
                crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsResponse>(
                  this, METHODID_LIQUID_VALIDATORS)))
          .addMethod(
            getVotingPowerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerRequest,
                crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerResponse>(
                  this, METHODID_VOTING_POWER)))
          .addMethod(
            getStatesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesRequest,
                crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesResponse>(
                  this, METHODID_STATES)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC query service for the liquidstaking module.
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
     * Params returns parameters of the liquidstaking module.
     * </pre>
     */
    public void params(crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * LiquidValidators returns liquid validators with states of the liquidstaking module.
     * </pre>
     */
    public void liquidValidators(crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLiquidValidatorsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * VotingPower returns voting power of staking and liquid staking module's of the voter that can be exercised.
     * </pre>
     */
    public void votingPower(crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVotingPowerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * States returns states of the liquidstaking module.
     * </pre>
     */
    public void states(crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStatesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC query service for the liquidstaking module.
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
     * Params returns parameters of the liquidstaking module.
     * </pre>
     */
    public crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsResponse params(crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * LiquidValidators returns liquid validators with states of the liquidstaking module.
     * </pre>
     */
    public crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsResponse liquidValidators(crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsRequest request) {
      return blockingUnaryCall(
          getChannel(), getLiquidValidatorsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * VotingPower returns voting power of staking and liquid staking module's of the voter that can be exercised.
     * </pre>
     */
    public crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerResponse votingPower(crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerRequest request) {
      return blockingUnaryCall(
          getChannel(), getVotingPowerMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * States returns states of the liquidstaking module.
     * </pre>
     */
    public crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesResponse states(crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesRequest request) {
      return blockingUnaryCall(
          getChannel(), getStatesMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC query service for the liquidstaking module.
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
     * Params returns parameters of the liquidstaking module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * LiquidValidators returns liquid validators with states of the liquidstaking module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsResponse> liquidValidators(
        crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLiquidValidatorsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * VotingPower returns voting power of staking and liquid staking module's of the voter that can be exercised.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerResponse> votingPower(
        crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVotingPowerMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * States returns states of the liquidstaking module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesResponse> states(
        crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStatesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_LIQUID_VALIDATORS = 1;
  private static final int METHODID_VOTING_POWER = 2;
  private static final int METHODID_STATES = 3;

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
          serviceImpl.params((crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_LIQUID_VALIDATORS:
          serviceImpl.liquidValidators((crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryLiquidValidatorsResponse>) responseObserver);
          break;
        case METHODID_VOTING_POWER:
          serviceImpl.votingPower((crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryVotingPowerResponse>) responseObserver);
          break;
        case METHODID_STATES:
          serviceImpl.states((crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidstaking.v1beta1.QueryOuterClass.QueryStatesResponse>) responseObserver);
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
      return crescent.liquidstaking.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getLiquidValidatorsMethod())
              .addMethod(getVotingPowerMethod())
              .addMethod(getStatesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
