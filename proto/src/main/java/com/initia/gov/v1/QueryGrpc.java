package com.initia.gov.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service for gov module.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: initia/gov/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "initia.gov.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.initia.gov.v1.QueryProto.QueryParamsRequest,
      com.initia.gov.v1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.initia.gov.v1.QueryProto.QueryParamsRequest.class,
      responseType = com.initia.gov.v1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.gov.v1.QueryProto.QueryParamsRequest,
      com.initia.gov.v1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.initia.gov.v1.QueryProto.QueryParamsRequest, com.initia.gov.v1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.initia.gov.v1.QueryProto.QueryParamsRequest, com.initia.gov.v1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.gov.v1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.gov.v1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.gov.v1.QueryProto.QueryEmergencyProposalsRequest,
      com.initia.gov.v1.QueryProto.QueryEmergencyProposalsResponse> getEmergencyProposalsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EmergencyProposals",
      requestType = com.initia.gov.v1.QueryProto.QueryEmergencyProposalsRequest.class,
      responseType = com.initia.gov.v1.QueryProto.QueryEmergencyProposalsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.gov.v1.QueryProto.QueryEmergencyProposalsRequest,
      com.initia.gov.v1.QueryProto.QueryEmergencyProposalsResponse> getEmergencyProposalsMethod() {
    io.grpc.MethodDescriptor<com.initia.gov.v1.QueryProto.QueryEmergencyProposalsRequest, com.initia.gov.v1.QueryProto.QueryEmergencyProposalsResponse> getEmergencyProposalsMethod;
    if ((getEmergencyProposalsMethod = QueryGrpc.getEmergencyProposalsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEmergencyProposalsMethod = QueryGrpc.getEmergencyProposalsMethod) == null) {
          QueryGrpc.getEmergencyProposalsMethod = getEmergencyProposalsMethod =
              io.grpc.MethodDescriptor.<com.initia.gov.v1.QueryProto.QueryEmergencyProposalsRequest, com.initia.gov.v1.QueryProto.QueryEmergencyProposalsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EmergencyProposals"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.gov.v1.QueryProto.QueryEmergencyProposalsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.gov.v1.QueryProto.QueryEmergencyProposalsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EmergencyProposals"))
              .build();
        }
      }
    }
    return getEmergencyProposalsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.gov.v1.QueryProto.QueryProposalRequest,
      com.initia.gov.v1.QueryProto.QueryProposalResponse> getProposalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Proposal",
      requestType = com.initia.gov.v1.QueryProto.QueryProposalRequest.class,
      responseType = com.initia.gov.v1.QueryProto.QueryProposalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.gov.v1.QueryProto.QueryProposalRequest,
      com.initia.gov.v1.QueryProto.QueryProposalResponse> getProposalMethod() {
    io.grpc.MethodDescriptor<com.initia.gov.v1.QueryProto.QueryProposalRequest, com.initia.gov.v1.QueryProto.QueryProposalResponse> getProposalMethod;
    if ((getProposalMethod = QueryGrpc.getProposalMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProposalMethod = QueryGrpc.getProposalMethod) == null) {
          QueryGrpc.getProposalMethod = getProposalMethod =
              io.grpc.MethodDescriptor.<com.initia.gov.v1.QueryProto.QueryProposalRequest, com.initia.gov.v1.QueryProto.QueryProposalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Proposal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.gov.v1.QueryProto.QueryProposalRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.gov.v1.QueryProto.QueryProposalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Proposal"))
              .build();
        }
      }
    }
    return getProposalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.gov.v1.QueryProto.QueryProposalsRequest,
      com.initia.gov.v1.QueryProto.QueryProposalsResponse> getProposalsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Proposals",
      requestType = com.initia.gov.v1.QueryProto.QueryProposalsRequest.class,
      responseType = com.initia.gov.v1.QueryProto.QueryProposalsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.gov.v1.QueryProto.QueryProposalsRequest,
      com.initia.gov.v1.QueryProto.QueryProposalsResponse> getProposalsMethod() {
    io.grpc.MethodDescriptor<com.initia.gov.v1.QueryProto.QueryProposalsRequest, com.initia.gov.v1.QueryProto.QueryProposalsResponse> getProposalsMethod;
    if ((getProposalsMethod = QueryGrpc.getProposalsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProposalsMethod = QueryGrpc.getProposalsMethod) == null) {
          QueryGrpc.getProposalsMethod = getProposalsMethod =
              io.grpc.MethodDescriptor.<com.initia.gov.v1.QueryProto.QueryProposalsRequest, com.initia.gov.v1.QueryProto.QueryProposalsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Proposals"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.gov.v1.QueryProto.QueryProposalsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.gov.v1.QueryProto.QueryProposalsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Proposals"))
              .build();
        }
      }
    }
    return getProposalsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.gov.v1.QueryProto.QueryTallyResultRequest,
      com.initia.gov.v1.QueryProto.QueryTallyResultResponse> getTallyResultMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TallyResult",
      requestType = com.initia.gov.v1.QueryProto.QueryTallyResultRequest.class,
      responseType = com.initia.gov.v1.QueryProto.QueryTallyResultResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.gov.v1.QueryProto.QueryTallyResultRequest,
      com.initia.gov.v1.QueryProto.QueryTallyResultResponse> getTallyResultMethod() {
    io.grpc.MethodDescriptor<com.initia.gov.v1.QueryProto.QueryTallyResultRequest, com.initia.gov.v1.QueryProto.QueryTallyResultResponse> getTallyResultMethod;
    if ((getTallyResultMethod = QueryGrpc.getTallyResultMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTallyResultMethod = QueryGrpc.getTallyResultMethod) == null) {
          QueryGrpc.getTallyResultMethod = getTallyResultMethod =
              io.grpc.MethodDescriptor.<com.initia.gov.v1.QueryProto.QueryTallyResultRequest, com.initia.gov.v1.QueryProto.QueryTallyResultResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TallyResult"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.gov.v1.QueryProto.QueryTallyResultRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.gov.v1.QueryProto.QueryTallyResultResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TallyResult"))
              .build();
        }
      }
    }
    return getTallyResultMethod;
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
   * Query defines the gRPC querier service for gov module.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Params queries params of the gov module.
     * </pre>
     */
    default void params(com.initia.gov.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.initia.gov.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * EmergencyProposals queries emergency proposals.
     * </pre>
     */
    default void emergencyProposals(com.initia.gov.v1.QueryProto.QueryEmergencyProposalsRequest request,
        io.grpc.stub.StreamObserver<com.initia.gov.v1.QueryProto.QueryEmergencyProposalsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEmergencyProposalsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Proposal queries proposal details based on ProposalID.
     * </pre>
     */
    default void proposal(com.initia.gov.v1.QueryProto.QueryProposalRequest request,
        io.grpc.stub.StreamObserver<com.initia.gov.v1.QueryProto.QueryProposalResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getProposalMethod(), responseObserver);
    }

    /**
     * <pre>
     * Proposals queries all proposals based on given status.
     * </pre>
     */
    default void proposals(com.initia.gov.v1.QueryProto.QueryProposalsRequest request,
        io.grpc.stub.StreamObserver<com.initia.gov.v1.QueryProto.QueryProposalsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getProposalsMethod(), responseObserver);
    }

    /**
     * <pre>
     * TallyResult queries the tally of a proposal vote.
     * </pre>
     */
    default void tallyResult(com.initia.gov.v1.QueryProto.QueryTallyResultRequest request,
        io.grpc.stub.StreamObserver<com.initia.gov.v1.QueryProto.QueryTallyResultResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTallyResultMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service for gov module.
   * </pre>
   */
  public static abstract class QueryImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return QueryGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for gov module.
   * </pre>
   */
  public static final class QueryStub
      extends io.grpc.stub.AbstractAsyncStub<QueryStub> {
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
     * Params queries params of the gov module.
     * </pre>
     */
    public void params(com.initia.gov.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.initia.gov.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EmergencyProposals queries emergency proposals.
     * </pre>
     */
    public void emergencyProposals(com.initia.gov.v1.QueryProto.QueryEmergencyProposalsRequest request,
        io.grpc.stub.StreamObserver<com.initia.gov.v1.QueryProto.QueryEmergencyProposalsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEmergencyProposalsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Proposal queries proposal details based on ProposalID.
     * </pre>
     */
    public void proposal(com.initia.gov.v1.QueryProto.QueryProposalRequest request,
        io.grpc.stub.StreamObserver<com.initia.gov.v1.QueryProto.QueryProposalResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getProposalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Proposals queries all proposals based on given status.
     * </pre>
     */
    public void proposals(com.initia.gov.v1.QueryProto.QueryProposalsRequest request,
        io.grpc.stub.StreamObserver<com.initia.gov.v1.QueryProto.QueryProposalsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getProposalsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TallyResult queries the tally of a proposal vote.
     * </pre>
     */
    public void tallyResult(com.initia.gov.v1.QueryProto.QueryTallyResultRequest request,
        io.grpc.stub.StreamObserver<com.initia.gov.v1.QueryProto.QueryTallyResultResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTallyResultMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for gov module.
   * </pre>
   */
  public static final class QueryBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<QueryBlockingStub> {
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
     * Params queries params of the gov module.
     * </pre>
     */
    public com.initia.gov.v1.QueryProto.QueryParamsResponse params(com.initia.gov.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EmergencyProposals queries emergency proposals.
     * </pre>
     */
    public com.initia.gov.v1.QueryProto.QueryEmergencyProposalsResponse emergencyProposals(com.initia.gov.v1.QueryProto.QueryEmergencyProposalsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEmergencyProposalsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Proposal queries proposal details based on ProposalID.
     * </pre>
     */
    public com.initia.gov.v1.QueryProto.QueryProposalResponse proposal(com.initia.gov.v1.QueryProto.QueryProposalRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getProposalMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Proposals queries all proposals based on given status.
     * </pre>
     */
    public com.initia.gov.v1.QueryProto.QueryProposalsResponse proposals(com.initia.gov.v1.QueryProto.QueryProposalsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getProposalsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TallyResult queries the tally of a proposal vote.
     * </pre>
     */
    public com.initia.gov.v1.QueryProto.QueryTallyResultResponse tallyResult(com.initia.gov.v1.QueryProto.QueryTallyResultRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTallyResultMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for gov module.
   * </pre>
   */
  public static final class QueryFutureStub
      extends io.grpc.stub.AbstractFutureStub<QueryFutureStub> {
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
     * Params queries params of the gov module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.gov.v1.QueryProto.QueryParamsResponse> params(
        com.initia.gov.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EmergencyProposals queries emergency proposals.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.gov.v1.QueryProto.QueryEmergencyProposalsResponse> emergencyProposals(
        com.initia.gov.v1.QueryProto.QueryEmergencyProposalsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEmergencyProposalsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Proposal queries proposal details based on ProposalID.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.gov.v1.QueryProto.QueryProposalResponse> proposal(
        com.initia.gov.v1.QueryProto.QueryProposalRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getProposalMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Proposals queries all proposals based on given status.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.gov.v1.QueryProto.QueryProposalsResponse> proposals(
        com.initia.gov.v1.QueryProto.QueryProposalsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getProposalsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TallyResult queries the tally of a proposal vote.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.gov.v1.QueryProto.QueryTallyResultResponse> tallyResult(
        com.initia.gov.v1.QueryProto.QueryTallyResultRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTallyResultMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_EMERGENCY_PROPOSALS = 1;
  private static final int METHODID_PROPOSAL = 2;
  private static final int METHODID_PROPOSALS = 3;
  private static final int METHODID_TALLY_RESULT = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PARAMS:
          serviceImpl.params((com.initia.gov.v1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.gov.v1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_EMERGENCY_PROPOSALS:
          serviceImpl.emergencyProposals((com.initia.gov.v1.QueryProto.QueryEmergencyProposalsRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.gov.v1.QueryProto.QueryEmergencyProposalsResponse>) responseObserver);
          break;
        case METHODID_PROPOSAL:
          serviceImpl.proposal((com.initia.gov.v1.QueryProto.QueryProposalRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.gov.v1.QueryProto.QueryProposalResponse>) responseObserver);
          break;
        case METHODID_PROPOSALS:
          serviceImpl.proposals((com.initia.gov.v1.QueryProto.QueryProposalsRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.gov.v1.QueryProto.QueryProposalsResponse>) responseObserver);
          break;
        case METHODID_TALLY_RESULT:
          serviceImpl.tallyResult((com.initia.gov.v1.QueryProto.QueryTallyResultRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.gov.v1.QueryProto.QueryTallyResultResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.gov.v1.QueryProto.QueryParamsRequest,
              com.initia.gov.v1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getEmergencyProposalsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.gov.v1.QueryProto.QueryEmergencyProposalsRequest,
              com.initia.gov.v1.QueryProto.QueryEmergencyProposalsResponse>(
                service, METHODID_EMERGENCY_PROPOSALS)))
        .addMethod(
          getProposalMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.gov.v1.QueryProto.QueryProposalRequest,
              com.initia.gov.v1.QueryProto.QueryProposalResponse>(
                service, METHODID_PROPOSAL)))
        .addMethod(
          getProposalsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.gov.v1.QueryProto.QueryProposalsRequest,
              com.initia.gov.v1.QueryProto.QueryProposalsResponse>(
                service, METHODID_PROPOSALS)))
        .addMethod(
          getTallyResultMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.gov.v1.QueryProto.QueryTallyResultRequest,
              com.initia.gov.v1.QueryProto.QueryTallyResultResponse>(
                service, METHODID_TALLY_RESULT)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.initia.gov.v1.QueryProto.getDescriptor();
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
              .addMethod(getEmergencyProposalsMethod())
              .addMethod(getProposalMethod())
              .addMethod(getProposalsMethod())
              .addMethod(getTallyResultMethod())
              .build();
        }
      }
    }
    return result;
  }
}
