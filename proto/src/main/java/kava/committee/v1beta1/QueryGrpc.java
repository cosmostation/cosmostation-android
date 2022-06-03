package kava.committee.v1beta1;

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
 * Query defines the gRPC querier service for committee module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/committee/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "kava.committee.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryCommitteesRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryCommitteesResponse> getCommitteesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Committees",
      requestType = kava.committee.v1beta1.QueryOuterClass.QueryCommitteesRequest.class,
      responseType = kava.committee.v1beta1.QueryOuterClass.QueryCommitteesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryCommitteesRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryCommitteesResponse> getCommitteesMethod() {
    io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryCommitteesRequest, kava.committee.v1beta1.QueryOuterClass.QueryCommitteesResponse> getCommitteesMethod;
    if ((getCommitteesMethod = QueryGrpc.getCommitteesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCommitteesMethod = QueryGrpc.getCommitteesMethod) == null) {
          QueryGrpc.getCommitteesMethod = getCommitteesMethod =
              io.grpc.MethodDescriptor.<kava.committee.v1beta1.QueryOuterClass.QueryCommitteesRequest, kava.committee.v1beta1.QueryOuterClass.QueryCommitteesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Committees"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryCommitteesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryCommitteesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Committees"))
              .build();
        }
      }
    }
    return getCommitteesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryCommitteeRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryCommitteeResponse> getCommitteeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Committee",
      requestType = kava.committee.v1beta1.QueryOuterClass.QueryCommitteeRequest.class,
      responseType = kava.committee.v1beta1.QueryOuterClass.QueryCommitteeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryCommitteeRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryCommitteeResponse> getCommitteeMethod() {
    io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryCommitteeRequest, kava.committee.v1beta1.QueryOuterClass.QueryCommitteeResponse> getCommitteeMethod;
    if ((getCommitteeMethod = QueryGrpc.getCommitteeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCommitteeMethod = QueryGrpc.getCommitteeMethod) == null) {
          QueryGrpc.getCommitteeMethod = getCommitteeMethod =
              io.grpc.MethodDescriptor.<kava.committee.v1beta1.QueryOuterClass.QueryCommitteeRequest, kava.committee.v1beta1.QueryOuterClass.QueryCommitteeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Committee"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryCommitteeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryCommitteeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Committee"))
              .build();
        }
      }
    }
    return getCommitteeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryProposalsRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryProposalsResponse> getProposalsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Proposals",
      requestType = kava.committee.v1beta1.QueryOuterClass.QueryProposalsRequest.class,
      responseType = kava.committee.v1beta1.QueryOuterClass.QueryProposalsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryProposalsRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryProposalsResponse> getProposalsMethod() {
    io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryProposalsRequest, kava.committee.v1beta1.QueryOuterClass.QueryProposalsResponse> getProposalsMethod;
    if ((getProposalsMethod = QueryGrpc.getProposalsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProposalsMethod = QueryGrpc.getProposalsMethod) == null) {
          QueryGrpc.getProposalsMethod = getProposalsMethod =
              io.grpc.MethodDescriptor.<kava.committee.v1beta1.QueryOuterClass.QueryProposalsRequest, kava.committee.v1beta1.QueryOuterClass.QueryProposalsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Proposals"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryProposalsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryProposalsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Proposals"))
              .build();
        }
      }
    }
    return getProposalsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryProposalRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryProposalResponse> getProposalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Proposal",
      requestType = kava.committee.v1beta1.QueryOuterClass.QueryProposalRequest.class,
      responseType = kava.committee.v1beta1.QueryOuterClass.QueryProposalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryProposalRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryProposalResponse> getProposalMethod() {
    io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryProposalRequest, kava.committee.v1beta1.QueryOuterClass.QueryProposalResponse> getProposalMethod;
    if ((getProposalMethod = QueryGrpc.getProposalMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProposalMethod = QueryGrpc.getProposalMethod) == null) {
          QueryGrpc.getProposalMethod = getProposalMethod =
              io.grpc.MethodDescriptor.<kava.committee.v1beta1.QueryOuterClass.QueryProposalRequest, kava.committee.v1beta1.QueryOuterClass.QueryProposalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Proposal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryProposalRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryProposalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Proposal"))
              .build();
        }
      }
    }
    return getProposalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDResponse> getNextProposalIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "NextProposalID",
      requestType = kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDRequest.class,
      responseType = kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDResponse> getNextProposalIDMethod() {
    io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDRequest, kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDResponse> getNextProposalIDMethod;
    if ((getNextProposalIDMethod = QueryGrpc.getNextProposalIDMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getNextProposalIDMethod = QueryGrpc.getNextProposalIDMethod) == null) {
          QueryGrpc.getNextProposalIDMethod = getNextProposalIDMethod =
              io.grpc.MethodDescriptor.<kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDRequest, kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NextProposalID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("NextProposalID"))
              .build();
        }
      }
    }
    return getNextProposalIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryVotesRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryVotesResponse> getVotesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Votes",
      requestType = kava.committee.v1beta1.QueryOuterClass.QueryVotesRequest.class,
      responseType = kava.committee.v1beta1.QueryOuterClass.QueryVotesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryVotesRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryVotesResponse> getVotesMethod() {
    io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryVotesRequest, kava.committee.v1beta1.QueryOuterClass.QueryVotesResponse> getVotesMethod;
    if ((getVotesMethod = QueryGrpc.getVotesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getVotesMethod = QueryGrpc.getVotesMethod) == null) {
          QueryGrpc.getVotesMethod = getVotesMethod =
              io.grpc.MethodDescriptor.<kava.committee.v1beta1.QueryOuterClass.QueryVotesRequest, kava.committee.v1beta1.QueryOuterClass.QueryVotesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Votes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryVotesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryVotesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Votes"))
              .build();
        }
      }
    }
    return getVotesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryVoteRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryVoteResponse> getVoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Vote",
      requestType = kava.committee.v1beta1.QueryOuterClass.QueryVoteRequest.class,
      responseType = kava.committee.v1beta1.QueryOuterClass.QueryVoteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryVoteRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryVoteResponse> getVoteMethod() {
    io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryVoteRequest, kava.committee.v1beta1.QueryOuterClass.QueryVoteResponse> getVoteMethod;
    if ((getVoteMethod = QueryGrpc.getVoteMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getVoteMethod = QueryGrpc.getVoteMethod) == null) {
          QueryGrpc.getVoteMethod = getVoteMethod =
              io.grpc.MethodDescriptor.<kava.committee.v1beta1.QueryOuterClass.QueryVoteRequest, kava.committee.v1beta1.QueryOuterClass.QueryVoteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Vote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryVoteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryVoteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Vote"))
              .build();
        }
      }
    }
    return getVoteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryTallyRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryTallyResponse> getTallyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Tally",
      requestType = kava.committee.v1beta1.QueryOuterClass.QueryTallyRequest.class,
      responseType = kava.committee.v1beta1.QueryOuterClass.QueryTallyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryTallyRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryTallyResponse> getTallyMethod() {
    io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryTallyRequest, kava.committee.v1beta1.QueryOuterClass.QueryTallyResponse> getTallyMethod;
    if ((getTallyMethod = QueryGrpc.getTallyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTallyMethod = QueryGrpc.getTallyMethod) == null) {
          QueryGrpc.getTallyMethod = getTallyMethod =
              io.grpc.MethodDescriptor.<kava.committee.v1beta1.QueryOuterClass.QueryTallyRequest, kava.committee.v1beta1.QueryOuterClass.QueryTallyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Tally"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryTallyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryTallyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Tally"))
              .build();
        }
      }
    }
    return getTallyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryRawParamsRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryRawParamsResponse> getRawParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RawParams",
      requestType = kava.committee.v1beta1.QueryOuterClass.QueryRawParamsRequest.class,
      responseType = kava.committee.v1beta1.QueryOuterClass.QueryRawParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryRawParamsRequest,
      kava.committee.v1beta1.QueryOuterClass.QueryRawParamsResponse> getRawParamsMethod() {
    io.grpc.MethodDescriptor<kava.committee.v1beta1.QueryOuterClass.QueryRawParamsRequest, kava.committee.v1beta1.QueryOuterClass.QueryRawParamsResponse> getRawParamsMethod;
    if ((getRawParamsMethod = QueryGrpc.getRawParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRawParamsMethod = QueryGrpc.getRawParamsMethod) == null) {
          QueryGrpc.getRawParamsMethod = getRawParamsMethod =
              io.grpc.MethodDescriptor.<kava.committee.v1beta1.QueryOuterClass.QueryRawParamsRequest, kava.committee.v1beta1.QueryOuterClass.QueryRawParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RawParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryRawParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.QueryOuterClass.QueryRawParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RawParams"))
              .build();
        }
      }
    }
    return getRawParamsMethod;
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
   * Query defines the gRPC querier service for committee module
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Committees queries all committess of the committee module.
     * </pre>
     */
    public void committees(kava.committee.v1beta1.QueryOuterClass.QueryCommitteesRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryCommitteesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCommitteesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Committee queries a committee based on committee ID.
     * </pre>
     */
    public void committee(kava.committee.v1beta1.QueryOuterClass.QueryCommitteeRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryCommitteeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCommitteeMethod(), responseObserver);
    }

    /**
     * <pre>
     * Proposals queries proposals based on committee ID.
     * </pre>
     */
    public void proposals(kava.committee.v1beta1.QueryOuterClass.QueryProposalsRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryProposalsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProposalsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Deposits queries a proposal based on proposal ID.
     * </pre>
     */
    public void proposal(kava.committee.v1beta1.QueryOuterClass.QueryProposalRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryProposalResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProposalMethod(), responseObserver);
    }

    /**
     * <pre>
     * NextProposalID queries the next proposal ID of the committee module.
     * </pre>
     */
    public void nextProposalID(kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNextProposalIDMethod(), responseObserver);
    }

    /**
     * <pre>
     * Votes queries all votes for a single proposal ID.
     * </pre>
     */
    public void votes(kava.committee.v1beta1.QueryOuterClass.QueryVotesRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryVotesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVotesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Vote queries the vote of a single voter for a single proposal ID.
     * </pre>
     */
    public void vote(kava.committee.v1beta1.QueryOuterClass.QueryVoteRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryVoteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVoteMethod(), responseObserver);
    }

    /**
     * <pre>
     * Tally queries the tally of a single proposal ID.
     * </pre>
     */
    public void tally(kava.committee.v1beta1.QueryOuterClass.QueryTallyRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryTallyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTallyMethod(), responseObserver);
    }

    /**
     * <pre>
     * RawParams queries the raw params data of any subspace and key.
     * </pre>
     */
    public void rawParams(kava.committee.v1beta1.QueryOuterClass.QueryRawParamsRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryRawParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRawParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCommitteesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.committee.v1beta1.QueryOuterClass.QueryCommitteesRequest,
                kava.committee.v1beta1.QueryOuterClass.QueryCommitteesResponse>(
                  this, METHODID_COMMITTEES)))
          .addMethod(
            getCommitteeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.committee.v1beta1.QueryOuterClass.QueryCommitteeRequest,
                kava.committee.v1beta1.QueryOuterClass.QueryCommitteeResponse>(
                  this, METHODID_COMMITTEE)))
          .addMethod(
            getProposalsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.committee.v1beta1.QueryOuterClass.QueryProposalsRequest,
                kava.committee.v1beta1.QueryOuterClass.QueryProposalsResponse>(
                  this, METHODID_PROPOSALS)))
          .addMethod(
            getProposalMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.committee.v1beta1.QueryOuterClass.QueryProposalRequest,
                kava.committee.v1beta1.QueryOuterClass.QueryProposalResponse>(
                  this, METHODID_PROPOSAL)))
          .addMethod(
            getNextProposalIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDRequest,
                kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDResponse>(
                  this, METHODID_NEXT_PROPOSAL_ID)))
          .addMethod(
            getVotesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.committee.v1beta1.QueryOuterClass.QueryVotesRequest,
                kava.committee.v1beta1.QueryOuterClass.QueryVotesResponse>(
                  this, METHODID_VOTES)))
          .addMethod(
            getVoteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.committee.v1beta1.QueryOuterClass.QueryVoteRequest,
                kava.committee.v1beta1.QueryOuterClass.QueryVoteResponse>(
                  this, METHODID_VOTE)))
          .addMethod(
            getTallyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.committee.v1beta1.QueryOuterClass.QueryTallyRequest,
                kava.committee.v1beta1.QueryOuterClass.QueryTallyResponse>(
                  this, METHODID_TALLY)))
          .addMethod(
            getRawParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.committee.v1beta1.QueryOuterClass.QueryRawParamsRequest,
                kava.committee.v1beta1.QueryOuterClass.QueryRawParamsResponse>(
                  this, METHODID_RAW_PARAMS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for committee module
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
     * Committees queries all committess of the committee module.
     * </pre>
     */
    public void committees(kava.committee.v1beta1.QueryOuterClass.QueryCommitteesRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryCommitteesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCommitteesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Committee queries a committee based on committee ID.
     * </pre>
     */
    public void committee(kava.committee.v1beta1.QueryOuterClass.QueryCommitteeRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryCommitteeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCommitteeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Proposals queries proposals based on committee ID.
     * </pre>
     */
    public void proposals(kava.committee.v1beta1.QueryOuterClass.QueryProposalsRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryProposalsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProposalsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Deposits queries a proposal based on proposal ID.
     * </pre>
     */
    public void proposal(kava.committee.v1beta1.QueryOuterClass.QueryProposalRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryProposalResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProposalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * NextProposalID queries the next proposal ID of the committee module.
     * </pre>
     */
    public void nextProposalID(kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNextProposalIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Votes queries all votes for a single proposal ID.
     * </pre>
     */
    public void votes(kava.committee.v1beta1.QueryOuterClass.QueryVotesRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryVotesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVotesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Vote queries the vote of a single voter for a single proposal ID.
     * </pre>
     */
    public void vote(kava.committee.v1beta1.QueryOuterClass.QueryVoteRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryVoteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVoteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Tally queries the tally of a single proposal ID.
     * </pre>
     */
    public void tally(kava.committee.v1beta1.QueryOuterClass.QueryTallyRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryTallyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTallyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RawParams queries the raw params data of any subspace and key.
     * </pre>
     */
    public void rawParams(kava.committee.v1beta1.QueryOuterClass.QueryRawParamsRequest request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryRawParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRawParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for committee module
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
     * Committees queries all committess of the committee module.
     * </pre>
     */
    public kava.committee.v1beta1.QueryOuterClass.QueryCommitteesResponse committees(kava.committee.v1beta1.QueryOuterClass.QueryCommitteesRequest request) {
      return blockingUnaryCall(
          getChannel(), getCommitteesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Committee queries a committee based on committee ID.
     * </pre>
     */
    public kava.committee.v1beta1.QueryOuterClass.QueryCommitteeResponse committee(kava.committee.v1beta1.QueryOuterClass.QueryCommitteeRequest request) {
      return blockingUnaryCall(
          getChannel(), getCommitteeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Proposals queries proposals based on committee ID.
     * </pre>
     */
    public kava.committee.v1beta1.QueryOuterClass.QueryProposalsResponse proposals(kava.committee.v1beta1.QueryOuterClass.QueryProposalsRequest request) {
      return blockingUnaryCall(
          getChannel(), getProposalsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Deposits queries a proposal based on proposal ID.
     * </pre>
     */
    public kava.committee.v1beta1.QueryOuterClass.QueryProposalResponse proposal(kava.committee.v1beta1.QueryOuterClass.QueryProposalRequest request) {
      return blockingUnaryCall(
          getChannel(), getProposalMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * NextProposalID queries the next proposal ID of the committee module.
     * </pre>
     */
    public kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDResponse nextProposalID(kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDRequest request) {
      return blockingUnaryCall(
          getChannel(), getNextProposalIDMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Votes queries all votes for a single proposal ID.
     * </pre>
     */
    public kava.committee.v1beta1.QueryOuterClass.QueryVotesResponse votes(kava.committee.v1beta1.QueryOuterClass.QueryVotesRequest request) {
      return blockingUnaryCall(
          getChannel(), getVotesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Vote queries the vote of a single voter for a single proposal ID.
     * </pre>
     */
    public kava.committee.v1beta1.QueryOuterClass.QueryVoteResponse vote(kava.committee.v1beta1.QueryOuterClass.QueryVoteRequest request) {
      return blockingUnaryCall(
          getChannel(), getVoteMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Tally queries the tally of a single proposal ID.
     * </pre>
     */
    public kava.committee.v1beta1.QueryOuterClass.QueryTallyResponse tally(kava.committee.v1beta1.QueryOuterClass.QueryTallyRequest request) {
      return blockingUnaryCall(
          getChannel(), getTallyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RawParams queries the raw params data of any subspace and key.
     * </pre>
     */
    public kava.committee.v1beta1.QueryOuterClass.QueryRawParamsResponse rawParams(kava.committee.v1beta1.QueryOuterClass.QueryRawParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getRawParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for committee module
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
     * Committees queries all committess of the committee module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.committee.v1beta1.QueryOuterClass.QueryCommitteesResponse> committees(
        kava.committee.v1beta1.QueryOuterClass.QueryCommitteesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCommitteesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Committee queries a committee based on committee ID.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.committee.v1beta1.QueryOuterClass.QueryCommitteeResponse> committee(
        kava.committee.v1beta1.QueryOuterClass.QueryCommitteeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCommitteeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Proposals queries proposals based on committee ID.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.committee.v1beta1.QueryOuterClass.QueryProposalsResponse> proposals(
        kava.committee.v1beta1.QueryOuterClass.QueryProposalsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProposalsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Deposits queries a proposal based on proposal ID.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.committee.v1beta1.QueryOuterClass.QueryProposalResponse> proposal(
        kava.committee.v1beta1.QueryOuterClass.QueryProposalRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProposalMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * NextProposalID queries the next proposal ID of the committee module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDResponse> nextProposalID(
        kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNextProposalIDMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Votes queries all votes for a single proposal ID.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.committee.v1beta1.QueryOuterClass.QueryVotesResponse> votes(
        kava.committee.v1beta1.QueryOuterClass.QueryVotesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVotesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Vote queries the vote of a single voter for a single proposal ID.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.committee.v1beta1.QueryOuterClass.QueryVoteResponse> vote(
        kava.committee.v1beta1.QueryOuterClass.QueryVoteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVoteMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Tally queries the tally of a single proposal ID.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.committee.v1beta1.QueryOuterClass.QueryTallyResponse> tally(
        kava.committee.v1beta1.QueryOuterClass.QueryTallyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTallyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RawParams queries the raw params data of any subspace and key.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.committee.v1beta1.QueryOuterClass.QueryRawParamsResponse> rawParams(
        kava.committee.v1beta1.QueryOuterClass.QueryRawParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRawParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_COMMITTEES = 0;
  private static final int METHODID_COMMITTEE = 1;
  private static final int METHODID_PROPOSALS = 2;
  private static final int METHODID_PROPOSAL = 3;
  private static final int METHODID_NEXT_PROPOSAL_ID = 4;
  private static final int METHODID_VOTES = 5;
  private static final int METHODID_VOTE = 6;
  private static final int METHODID_TALLY = 7;
  private static final int METHODID_RAW_PARAMS = 8;

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
        case METHODID_COMMITTEES:
          serviceImpl.committees((kava.committee.v1beta1.QueryOuterClass.QueryCommitteesRequest) request,
              (io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryCommitteesResponse>) responseObserver);
          break;
        case METHODID_COMMITTEE:
          serviceImpl.committee((kava.committee.v1beta1.QueryOuterClass.QueryCommitteeRequest) request,
              (io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryCommitteeResponse>) responseObserver);
          break;
        case METHODID_PROPOSALS:
          serviceImpl.proposals((kava.committee.v1beta1.QueryOuterClass.QueryProposalsRequest) request,
              (io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryProposalsResponse>) responseObserver);
          break;
        case METHODID_PROPOSAL:
          serviceImpl.proposal((kava.committee.v1beta1.QueryOuterClass.QueryProposalRequest) request,
              (io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryProposalResponse>) responseObserver);
          break;
        case METHODID_NEXT_PROPOSAL_ID:
          serviceImpl.nextProposalID((kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDRequest) request,
              (io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryNextProposalIDResponse>) responseObserver);
          break;
        case METHODID_VOTES:
          serviceImpl.votes((kava.committee.v1beta1.QueryOuterClass.QueryVotesRequest) request,
              (io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryVotesResponse>) responseObserver);
          break;
        case METHODID_VOTE:
          serviceImpl.vote((kava.committee.v1beta1.QueryOuterClass.QueryVoteRequest) request,
              (io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryVoteResponse>) responseObserver);
          break;
        case METHODID_TALLY:
          serviceImpl.tally((kava.committee.v1beta1.QueryOuterClass.QueryTallyRequest) request,
              (io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryTallyResponse>) responseObserver);
          break;
        case METHODID_RAW_PARAMS:
          serviceImpl.rawParams((kava.committee.v1beta1.QueryOuterClass.QueryRawParamsRequest) request,
              (io.grpc.stub.StreamObserver<kava.committee.v1beta1.QueryOuterClass.QueryRawParamsResponse>) responseObserver);
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
      return kava.committee.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getCommitteesMethod())
              .addMethod(getCommitteeMethod())
              .addMethod(getProposalsMethod())
              .addMethod(getProposalMethod())
              .addMethod(getNextProposalIDMethod())
              .addMethod(getVotesMethod())
              .addMethod(getVoteMethod())
              .addMethod(getTallyMethod())
              .addMethod(getRawParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
