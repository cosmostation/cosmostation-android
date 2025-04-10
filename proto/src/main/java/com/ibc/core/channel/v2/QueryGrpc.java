package com.ibc.core.channel.v2;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query provides defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: ibc/core/channel/v2/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "ibc.core.channel.v2.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendRequest,
      com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendResponse> getNextSequenceSendMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "NextSequenceSend",
      requestType = com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendRequest.class,
      responseType = com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendRequest,
      com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendResponse> getNextSequenceSendMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendRequest, com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendResponse> getNextSequenceSendMethod;
    if ((getNextSequenceSendMethod = QueryGrpc.getNextSequenceSendMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getNextSequenceSendMethod = QueryGrpc.getNextSequenceSendMethod) == null) {
          QueryGrpc.getNextSequenceSendMethod = getNextSequenceSendMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendRequest, com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NextSequenceSend"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("NextSequenceSend"))
              .build();
        }
      }
    }
    return getNextSequenceSendMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentRequest,
      com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentResponse> getPacketCommitmentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PacketCommitment",
      requestType = com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentRequest.class,
      responseType = com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentRequest,
      com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentResponse> getPacketCommitmentMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentRequest, com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentResponse> getPacketCommitmentMethod;
    if ((getPacketCommitmentMethod = QueryGrpc.getPacketCommitmentMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPacketCommitmentMethod = QueryGrpc.getPacketCommitmentMethod) == null) {
          QueryGrpc.getPacketCommitmentMethod = getPacketCommitmentMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentRequest, com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PacketCommitment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PacketCommitment"))
              .build();
        }
      }
    }
    return getPacketCommitmentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsRequest,
      com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsResponse> getPacketCommitmentsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PacketCommitments",
      requestType = com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsRequest.class,
      responseType = com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsRequest,
      com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsResponse> getPacketCommitmentsMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsRequest, com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsResponse> getPacketCommitmentsMethod;
    if ((getPacketCommitmentsMethod = QueryGrpc.getPacketCommitmentsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPacketCommitmentsMethod = QueryGrpc.getPacketCommitmentsMethod) == null) {
          QueryGrpc.getPacketCommitmentsMethod = getPacketCommitmentsMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsRequest, com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PacketCommitments"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PacketCommitments"))
              .build();
        }
      }
    }
    return getPacketCommitmentsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementRequest,
      com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementResponse> getPacketAcknowledgementMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PacketAcknowledgement",
      requestType = com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementRequest.class,
      responseType = com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementRequest,
      com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementResponse> getPacketAcknowledgementMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementRequest, com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementResponse> getPacketAcknowledgementMethod;
    if ((getPacketAcknowledgementMethod = QueryGrpc.getPacketAcknowledgementMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPacketAcknowledgementMethod = QueryGrpc.getPacketAcknowledgementMethod) == null) {
          QueryGrpc.getPacketAcknowledgementMethod = getPacketAcknowledgementMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementRequest, com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PacketAcknowledgement"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PacketAcknowledgement"))
              .build();
        }
      }
    }
    return getPacketAcknowledgementMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsRequest,
      com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsResponse> getPacketAcknowledgementsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PacketAcknowledgements",
      requestType = com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsRequest.class,
      responseType = com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsRequest,
      com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsResponse> getPacketAcknowledgementsMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsRequest, com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsResponse> getPacketAcknowledgementsMethod;
    if ((getPacketAcknowledgementsMethod = QueryGrpc.getPacketAcknowledgementsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPacketAcknowledgementsMethod = QueryGrpc.getPacketAcknowledgementsMethod) == null) {
          QueryGrpc.getPacketAcknowledgementsMethod = getPacketAcknowledgementsMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsRequest, com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PacketAcknowledgements"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PacketAcknowledgements"))
              .build();
        }
      }
    }
    return getPacketAcknowledgementsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptRequest,
      com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptResponse> getPacketReceiptMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PacketReceipt",
      requestType = com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptRequest.class,
      responseType = com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptRequest,
      com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptResponse> getPacketReceiptMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptRequest, com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptResponse> getPacketReceiptMethod;
    if ((getPacketReceiptMethod = QueryGrpc.getPacketReceiptMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPacketReceiptMethod = QueryGrpc.getPacketReceiptMethod) == null) {
          QueryGrpc.getPacketReceiptMethod = getPacketReceiptMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptRequest, com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PacketReceipt"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PacketReceipt"))
              .build();
        }
      }
    }
    return getPacketReceiptMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsRequest,
      com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsResponse> getUnreceivedPacketsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnreceivedPackets",
      requestType = com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsRequest.class,
      responseType = com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsRequest,
      com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsResponse> getUnreceivedPacketsMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsRequest, com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsResponse> getUnreceivedPacketsMethod;
    if ((getUnreceivedPacketsMethod = QueryGrpc.getUnreceivedPacketsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnreceivedPacketsMethod = QueryGrpc.getUnreceivedPacketsMethod) == null) {
          QueryGrpc.getUnreceivedPacketsMethod = getUnreceivedPacketsMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsRequest, com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnreceivedPackets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UnreceivedPackets"))
              .build();
        }
      }
    }
    return getUnreceivedPacketsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksRequest,
      com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksResponse> getUnreceivedAcksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnreceivedAcks",
      requestType = com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksRequest.class,
      responseType = com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksRequest,
      com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksResponse> getUnreceivedAcksMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksRequest, com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksResponse> getUnreceivedAcksMethod;
    if ((getUnreceivedAcksMethod = QueryGrpc.getUnreceivedAcksMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnreceivedAcksMethod = QueryGrpc.getUnreceivedAcksMethod) == null) {
          QueryGrpc.getUnreceivedAcksMethod = getUnreceivedAcksMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksRequest, com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnreceivedAcks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UnreceivedAcks"))
              .build();
        }
      }
    }
    return getUnreceivedAcksMethod;
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
   * Query provides defines the gRPC querier service
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * NextSequenceSend returns the next send sequence for a given channel.
     * </pre>
     */
    default void nextSequenceSend(com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getNextSequenceSendMethod(), responseObserver);
    }

    /**
     * <pre>
     * PacketCommitment queries a stored packet commitment hash.
     * </pre>
     */
    default void packetCommitment(com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPacketCommitmentMethod(), responseObserver);
    }

    /**
     * <pre>
     * PacketCommitments queries a stored packet commitment hash.
     * </pre>
     */
    default void packetCommitments(com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPacketCommitmentsMethod(), responseObserver);
    }

    /**
     * <pre>
     * PacketAcknowledgement queries a stored acknowledgement commitment hash.
     * </pre>
     */
    default void packetAcknowledgement(com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPacketAcknowledgementMethod(), responseObserver);
    }

    /**
     * <pre>
     * PacketAcknowledgements returns all packet acknowledgements associated with a channel.
     * </pre>
     */
    default void packetAcknowledgements(com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPacketAcknowledgementsMethod(), responseObserver);
    }

    /**
     * <pre>
     * PacketReceipt queries a stored packet receipt.
     * </pre>
     */
    default void packetReceipt(com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPacketReceiptMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnreceivedPackets returns all the unreceived IBC packets associated with a channel and sequences.
     * </pre>
     */
    default void unreceivedPackets(com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnreceivedPacketsMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnreceivedAcks returns all the unreceived IBC acknowledgements associated with a channel and sequences.
     * </pre>
     */
    default void unreceivedAcks(com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnreceivedAcksMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query provides defines the gRPC querier service
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
   * Query provides defines the gRPC querier service
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
     * NextSequenceSend returns the next send sequence for a given channel.
     * </pre>
     */
    public void nextSequenceSend(com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getNextSequenceSendMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PacketCommitment queries a stored packet commitment hash.
     * </pre>
     */
    public void packetCommitment(com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPacketCommitmentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PacketCommitments queries a stored packet commitment hash.
     * </pre>
     */
    public void packetCommitments(com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPacketCommitmentsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PacketAcknowledgement queries a stored acknowledgement commitment hash.
     * </pre>
     */
    public void packetAcknowledgement(com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPacketAcknowledgementMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PacketAcknowledgements returns all packet acknowledgements associated with a channel.
     * </pre>
     */
    public void packetAcknowledgements(com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPacketAcknowledgementsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PacketReceipt queries a stored packet receipt.
     * </pre>
     */
    public void packetReceipt(com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPacketReceiptMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnreceivedPackets returns all the unreceived IBC packets associated with a channel and sequences.
     * </pre>
     */
    public void unreceivedPackets(com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnreceivedPacketsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnreceivedAcks returns all the unreceived IBC acknowledgements associated with a channel and sequences.
     * </pre>
     */
    public void unreceivedAcks(com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnreceivedAcksMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query provides defines the gRPC querier service
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
     * NextSequenceSend returns the next send sequence for a given channel.
     * </pre>
     */
    public com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendResponse nextSequenceSend(com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getNextSequenceSendMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PacketCommitment queries a stored packet commitment hash.
     * </pre>
     */
    public com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentResponse packetCommitment(com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPacketCommitmentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PacketCommitments queries a stored packet commitment hash.
     * </pre>
     */
    public com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsResponse packetCommitments(com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPacketCommitmentsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PacketAcknowledgement queries a stored acknowledgement commitment hash.
     * </pre>
     */
    public com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementResponse packetAcknowledgement(com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPacketAcknowledgementMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PacketAcknowledgements returns all packet acknowledgements associated with a channel.
     * </pre>
     */
    public com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsResponse packetAcknowledgements(com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPacketAcknowledgementsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PacketReceipt queries a stored packet receipt.
     * </pre>
     */
    public com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptResponse packetReceipt(com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPacketReceiptMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnreceivedPackets returns all the unreceived IBC packets associated with a channel and sequences.
     * </pre>
     */
    public com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsResponse unreceivedPackets(com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnreceivedPacketsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnreceivedAcks returns all the unreceived IBC acknowledgements associated with a channel and sequences.
     * </pre>
     */
    public com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksResponse unreceivedAcks(com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnreceivedAcksMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query provides defines the gRPC querier service
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
     * NextSequenceSend returns the next send sequence for a given channel.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendResponse> nextSequenceSend(
        com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getNextSequenceSendMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PacketCommitment queries a stored packet commitment hash.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentResponse> packetCommitment(
        com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPacketCommitmentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PacketCommitments queries a stored packet commitment hash.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsResponse> packetCommitments(
        com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPacketCommitmentsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PacketAcknowledgement queries a stored acknowledgement commitment hash.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementResponse> packetAcknowledgement(
        com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPacketAcknowledgementMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PacketAcknowledgements returns all packet acknowledgements associated with a channel.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsResponse> packetAcknowledgements(
        com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPacketAcknowledgementsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PacketReceipt queries a stored packet receipt.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptResponse> packetReceipt(
        com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPacketReceiptMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnreceivedPackets returns all the unreceived IBC packets associated with a channel and sequences.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsResponse> unreceivedPackets(
        com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnreceivedPacketsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnreceivedAcks returns all the unreceived IBC acknowledgements associated with a channel and sequences.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksResponse> unreceivedAcks(
        com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnreceivedAcksMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_NEXT_SEQUENCE_SEND = 0;
  private static final int METHODID_PACKET_COMMITMENT = 1;
  private static final int METHODID_PACKET_COMMITMENTS = 2;
  private static final int METHODID_PACKET_ACKNOWLEDGEMENT = 3;
  private static final int METHODID_PACKET_ACKNOWLEDGEMENTS = 4;
  private static final int METHODID_PACKET_RECEIPT = 5;
  private static final int METHODID_UNRECEIVED_PACKETS = 6;
  private static final int METHODID_UNRECEIVED_ACKS = 7;

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
        case METHODID_NEXT_SEQUENCE_SEND:
          serviceImpl.nextSequenceSend((com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendResponse>) responseObserver);
          break;
        case METHODID_PACKET_COMMITMENT:
          serviceImpl.packetCommitment((com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentResponse>) responseObserver);
          break;
        case METHODID_PACKET_COMMITMENTS:
          serviceImpl.packetCommitments((com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsResponse>) responseObserver);
          break;
        case METHODID_PACKET_ACKNOWLEDGEMENT:
          serviceImpl.packetAcknowledgement((com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementResponse>) responseObserver);
          break;
        case METHODID_PACKET_ACKNOWLEDGEMENTS:
          serviceImpl.packetAcknowledgements((com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsResponse>) responseObserver);
          break;
        case METHODID_PACKET_RECEIPT:
          serviceImpl.packetReceipt((com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptResponse>) responseObserver);
          break;
        case METHODID_UNRECEIVED_PACKETS:
          serviceImpl.unreceivedPackets((com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsResponse>) responseObserver);
          break;
        case METHODID_UNRECEIVED_ACKS:
          serviceImpl.unreceivedAcks((com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksResponse>) responseObserver);
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
          getNextSequenceSendMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendRequest,
              com.ibc.core.channel.v2.QueryProto.QueryNextSequenceSendResponse>(
                service, METHODID_NEXT_SEQUENCE_SEND)))
        .addMethod(
          getPacketCommitmentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentRequest,
              com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentResponse>(
                service, METHODID_PACKET_COMMITMENT)))
        .addMethod(
          getPacketCommitmentsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsRequest,
              com.ibc.core.channel.v2.QueryProto.QueryPacketCommitmentsResponse>(
                service, METHODID_PACKET_COMMITMENTS)))
        .addMethod(
          getPacketAcknowledgementMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementRequest,
              com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementResponse>(
                service, METHODID_PACKET_ACKNOWLEDGEMENT)))
        .addMethod(
          getPacketAcknowledgementsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsRequest,
              com.ibc.core.channel.v2.QueryProto.QueryPacketAcknowledgementsResponse>(
                service, METHODID_PACKET_ACKNOWLEDGEMENTS)))
        .addMethod(
          getPacketReceiptMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptRequest,
              com.ibc.core.channel.v2.QueryProto.QueryPacketReceiptResponse>(
                service, METHODID_PACKET_RECEIPT)))
        .addMethod(
          getUnreceivedPacketsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsRequest,
              com.ibc.core.channel.v2.QueryProto.QueryUnreceivedPacketsResponse>(
                service, METHODID_UNRECEIVED_PACKETS)))
        .addMethod(
          getUnreceivedAcksMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksRequest,
              com.ibc.core.channel.v2.QueryProto.QueryUnreceivedAcksResponse>(
                service, METHODID_UNRECEIVED_ACKS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ibc.core.channel.v2.QueryProto.getDescriptor();
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
              .addMethod(getNextSequenceSendMethod())
              .addMethod(getPacketCommitmentMethod())
              .addMethod(getPacketCommitmentsMethod())
              .addMethod(getPacketAcknowledgementMethod())
              .addMethod(getPacketAcknowledgementsMethod())
              .addMethod(getPacketReceiptMethod())
              .addMethod(getUnreceivedPacketsMethod())
              .addMethod(getUnreceivedAcksMethod())
              .build();
        }
      }
    }
    return result;
  }
}
