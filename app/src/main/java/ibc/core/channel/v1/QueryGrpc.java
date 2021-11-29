package ibc.core.channel.v1;

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
 * Query provides defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: ibc/core/channel/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "ibc.core.channel.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryChannelRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryChannelResponse> getChannelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Channel",
      requestType = ibc.core.channel.v1.QueryOuterClass.QueryChannelRequest.class,
      responseType = ibc.core.channel.v1.QueryOuterClass.QueryChannelResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryChannelRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryChannelResponse> getChannelMethod() {
    io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryChannelRequest, ibc.core.channel.v1.QueryOuterClass.QueryChannelResponse> getChannelMethod;
    if ((getChannelMethod = QueryGrpc.getChannelMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getChannelMethod = QueryGrpc.getChannelMethod) == null) {
          QueryGrpc.getChannelMethod = getChannelMethod =
              io.grpc.MethodDescriptor.<ibc.core.channel.v1.QueryOuterClass.QueryChannelRequest, ibc.core.channel.v1.QueryOuterClass.QueryChannelResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Channel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryChannelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryChannelResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Channel"))
              .build();
        }
      }
    }
    return getChannelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryChannelsRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryChannelsResponse> getChannelsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Channels",
      requestType = ibc.core.channel.v1.QueryOuterClass.QueryChannelsRequest.class,
      responseType = ibc.core.channel.v1.QueryOuterClass.QueryChannelsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryChannelsRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryChannelsResponse> getChannelsMethod() {
    io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryChannelsRequest, ibc.core.channel.v1.QueryOuterClass.QueryChannelsResponse> getChannelsMethod;
    if ((getChannelsMethod = QueryGrpc.getChannelsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getChannelsMethod = QueryGrpc.getChannelsMethod) == null) {
          QueryGrpc.getChannelsMethod = getChannelsMethod =
              io.grpc.MethodDescriptor.<ibc.core.channel.v1.QueryOuterClass.QueryChannelsRequest, ibc.core.channel.v1.QueryOuterClass.QueryChannelsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Channels"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryChannelsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryChannelsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Channels"))
              .build();
        }
      }
    }
    return getChannelsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsResponse> getConnectionChannelsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConnectionChannels",
      requestType = ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsRequest.class,
      responseType = ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsResponse> getConnectionChannelsMethod() {
    io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsRequest, ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsResponse> getConnectionChannelsMethod;
    if ((getConnectionChannelsMethod = QueryGrpc.getConnectionChannelsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getConnectionChannelsMethod = QueryGrpc.getConnectionChannelsMethod) == null) {
          QueryGrpc.getConnectionChannelsMethod = getConnectionChannelsMethod =
              io.grpc.MethodDescriptor.<ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsRequest, ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConnectionChannels"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ConnectionChannels"))
              .build();
        }
      }
    }
    return getConnectionChannelsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateResponse> getChannelClientStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChannelClientState",
      requestType = ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest.class,
      responseType = ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateResponse> getChannelClientStateMethod() {
    io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest, ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateResponse> getChannelClientStateMethod;
    if ((getChannelClientStateMethod = QueryGrpc.getChannelClientStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getChannelClientStateMethod = QueryGrpc.getChannelClientStateMethod) == null) {
          QueryGrpc.getChannelClientStateMethod = getChannelClientStateMethod =
              io.grpc.MethodDescriptor.<ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest, ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ChannelClientState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ChannelClientState"))
              .build();
        }
      }
    }
    return getChannelClientStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateResponse> getChannelConsensusStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChannelConsensusState",
      requestType = ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateRequest.class,
      responseType = ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateResponse> getChannelConsensusStateMethod() {
    io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateRequest, ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateResponse> getChannelConsensusStateMethod;
    if ((getChannelConsensusStateMethod = QueryGrpc.getChannelConsensusStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getChannelConsensusStateMethod = QueryGrpc.getChannelConsensusStateMethod) == null) {
          QueryGrpc.getChannelConsensusStateMethod = getChannelConsensusStateMethod =
              io.grpc.MethodDescriptor.<ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateRequest, ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ChannelConsensusState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ChannelConsensusState"))
              .build();
        }
      }
    }
    return getChannelConsensusStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentResponse> getPacketCommitmentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PacketCommitment",
      requestType = ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentRequest.class,
      responseType = ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentResponse> getPacketCommitmentMethod() {
    io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentRequest, ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentResponse> getPacketCommitmentMethod;
    if ((getPacketCommitmentMethod = QueryGrpc.getPacketCommitmentMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPacketCommitmentMethod = QueryGrpc.getPacketCommitmentMethod) == null) {
          QueryGrpc.getPacketCommitmentMethod = getPacketCommitmentMethod =
              io.grpc.MethodDescriptor.<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentRequest, ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PacketCommitment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PacketCommitment"))
              .build();
        }
      }
    }
    return getPacketCommitmentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsResponse> getPacketCommitmentsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PacketCommitments",
      requestType = ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsRequest.class,
      responseType = ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsResponse> getPacketCommitmentsMethod() {
    io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsRequest, ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsResponse> getPacketCommitmentsMethod;
    if ((getPacketCommitmentsMethod = QueryGrpc.getPacketCommitmentsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPacketCommitmentsMethod = QueryGrpc.getPacketCommitmentsMethod) == null) {
          QueryGrpc.getPacketCommitmentsMethod = getPacketCommitmentsMethod =
              io.grpc.MethodDescriptor.<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsRequest, ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PacketCommitments"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PacketCommitments"))
              .build();
        }
      }
    }
    return getPacketCommitmentsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptResponse> getPacketReceiptMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PacketReceipt",
      requestType = ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptRequest.class,
      responseType = ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptResponse> getPacketReceiptMethod() {
    io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptRequest, ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptResponse> getPacketReceiptMethod;
    if ((getPacketReceiptMethod = QueryGrpc.getPacketReceiptMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPacketReceiptMethod = QueryGrpc.getPacketReceiptMethod) == null) {
          QueryGrpc.getPacketReceiptMethod = getPacketReceiptMethod =
              io.grpc.MethodDescriptor.<ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptRequest, ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PacketReceipt"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PacketReceipt"))
              .build();
        }
      }
    }
    return getPacketReceiptMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementResponse> getPacketAcknowledgementMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PacketAcknowledgement",
      requestType = ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementRequest.class,
      responseType = ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementResponse> getPacketAcknowledgementMethod() {
    io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementRequest, ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementResponse> getPacketAcknowledgementMethod;
    if ((getPacketAcknowledgementMethod = QueryGrpc.getPacketAcknowledgementMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPacketAcknowledgementMethod = QueryGrpc.getPacketAcknowledgementMethod) == null) {
          QueryGrpc.getPacketAcknowledgementMethod = getPacketAcknowledgementMethod =
              io.grpc.MethodDescriptor.<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementRequest, ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PacketAcknowledgement"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PacketAcknowledgement"))
              .build();
        }
      }
    }
    return getPacketAcknowledgementMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsResponse> getPacketAcknowledgementsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PacketAcknowledgements",
      requestType = ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsRequest.class,
      responseType = ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsResponse> getPacketAcknowledgementsMethod() {
    io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsRequest, ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsResponse> getPacketAcknowledgementsMethod;
    if ((getPacketAcknowledgementsMethod = QueryGrpc.getPacketAcknowledgementsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPacketAcknowledgementsMethod = QueryGrpc.getPacketAcknowledgementsMethod) == null) {
          QueryGrpc.getPacketAcknowledgementsMethod = getPacketAcknowledgementsMethod =
              io.grpc.MethodDescriptor.<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsRequest, ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PacketAcknowledgements"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PacketAcknowledgements"))
              .build();
        }
      }
    }
    return getPacketAcknowledgementsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsResponse> getUnreceivedPacketsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnreceivedPackets",
      requestType = ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsRequest.class,
      responseType = ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsResponse> getUnreceivedPacketsMethod() {
    io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsRequest, ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsResponse> getUnreceivedPacketsMethod;
    if ((getUnreceivedPacketsMethod = QueryGrpc.getUnreceivedPacketsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnreceivedPacketsMethod = QueryGrpc.getUnreceivedPacketsMethod) == null) {
          QueryGrpc.getUnreceivedPacketsMethod = getUnreceivedPacketsMethod =
              io.grpc.MethodDescriptor.<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsRequest, ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnreceivedPackets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UnreceivedPackets"))
              .build();
        }
      }
    }
    return getUnreceivedPacketsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksResponse> getUnreceivedAcksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnreceivedAcks",
      requestType = ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksRequest.class,
      responseType = ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksResponse> getUnreceivedAcksMethod() {
    io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksRequest, ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksResponse> getUnreceivedAcksMethod;
    if ((getUnreceivedAcksMethod = QueryGrpc.getUnreceivedAcksMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnreceivedAcksMethod = QueryGrpc.getUnreceivedAcksMethod) == null) {
          QueryGrpc.getUnreceivedAcksMethod = getUnreceivedAcksMethod =
              io.grpc.MethodDescriptor.<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksRequest, ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnreceivedAcks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UnreceivedAcks"))
              .build();
        }
      }
    }
    return getUnreceivedAcksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveResponse> getNextSequenceReceiveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "NextSequenceReceive",
      requestType = ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveRequest.class,
      responseType = ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveRequest,
      ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveResponse> getNextSequenceReceiveMethod() {
    io.grpc.MethodDescriptor<ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveRequest, ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveResponse> getNextSequenceReceiveMethod;
    if ((getNextSequenceReceiveMethod = QueryGrpc.getNextSequenceReceiveMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getNextSequenceReceiveMethod = QueryGrpc.getNextSequenceReceiveMethod) == null) {
          QueryGrpc.getNextSequenceReceiveMethod = getNextSequenceReceiveMethod =
              io.grpc.MethodDescriptor.<ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveRequest, ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NextSequenceReceive"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("NextSequenceReceive"))
              .build();
        }
      }
    }
    return getNextSequenceReceiveMethod;
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
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Channel queries an IBC Channel.
     * </pre>
     */
    public void channel(ibc.core.channel.v1.QueryOuterClass.QueryChannelRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryChannelResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getChannelMethod(), responseObserver);
    }

    /**
     * <pre>
     * Channels queries all the IBC channels of a chain.
     * </pre>
     */
    public void channels(ibc.core.channel.v1.QueryOuterClass.QueryChannelsRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryChannelsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getChannelsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConnectionChannels queries all the channels associated with a connection
     * end.
     * </pre>
     */
    public void connectionChannels(ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConnectionChannelsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ChannelClientState queries for the client state for the channel associated
     * with the provided channel identifiers.
     * </pre>
     */
    public void channelClientState(ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getChannelClientStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * ChannelConsensusState queries for the consensus state for the channel
     * associated with the provided channel identifiers.
     * </pre>
     */
    public void channelConsensusState(ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getChannelConsensusStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * PacketCommitment queries a stored packet commitment hash.
     * </pre>
     */
    public void packetCommitment(ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPacketCommitmentMethod(), responseObserver);
    }

    /**
     * <pre>
     * PacketCommitments returns all the packet commitments hashes associated
     * with a channel.
     * </pre>
     */
    public void packetCommitments(ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPacketCommitmentsMethod(), responseObserver);
    }

    /**
     * <pre>
     * PacketReceipt queries if a given packet sequence has been received on the
     * queried chain
     * </pre>
     */
    public void packetReceipt(ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPacketReceiptMethod(), responseObserver);
    }

    /**
     * <pre>
     * PacketAcknowledgement queries a stored packet acknowledgement hash.
     * </pre>
     */
    public void packetAcknowledgement(ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPacketAcknowledgementMethod(), responseObserver);
    }

    /**
     * <pre>
     * PacketAcknowledgements returns all the packet acknowledgements associated
     * with a channel.
     * </pre>
     */
    public void packetAcknowledgements(ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPacketAcknowledgementsMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnreceivedPackets returns all the unreceived IBC packets associated with a
     * channel and sequences.
     * </pre>
     */
    public void unreceivedPackets(ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnreceivedPacketsMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnreceivedAcks returns all the unreceived IBC acknowledgements associated
     * with a channel and sequences.
     * </pre>
     */
    public void unreceivedAcks(ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnreceivedAcksMethod(), responseObserver);
    }

    /**
     * <pre>
     * NextSequenceReceive returns the next receive sequence for a given channel.
     * </pre>
     */
    public void nextSequenceReceive(ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNextSequenceReceiveMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getChannelMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.channel.v1.QueryOuterClass.QueryChannelRequest,
                ibc.core.channel.v1.QueryOuterClass.QueryChannelResponse>(
                  this, METHODID_CHANNEL)))
          .addMethod(
            getChannelsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.channel.v1.QueryOuterClass.QueryChannelsRequest,
                ibc.core.channel.v1.QueryOuterClass.QueryChannelsResponse>(
                  this, METHODID_CHANNELS)))
          .addMethod(
            getConnectionChannelsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsRequest,
                ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsResponse>(
                  this, METHODID_CONNECTION_CHANNELS)))
          .addMethod(
            getChannelClientStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest,
                ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateResponse>(
                  this, METHODID_CHANNEL_CLIENT_STATE)))
          .addMethod(
            getChannelConsensusStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateRequest,
                ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateResponse>(
                  this, METHODID_CHANNEL_CONSENSUS_STATE)))
          .addMethod(
            getPacketCommitmentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentRequest,
                ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentResponse>(
                  this, METHODID_PACKET_COMMITMENT)))
          .addMethod(
            getPacketCommitmentsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsRequest,
                ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsResponse>(
                  this, METHODID_PACKET_COMMITMENTS)))
          .addMethod(
            getPacketReceiptMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptRequest,
                ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptResponse>(
                  this, METHODID_PACKET_RECEIPT)))
          .addMethod(
            getPacketAcknowledgementMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementRequest,
                ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementResponse>(
                  this, METHODID_PACKET_ACKNOWLEDGEMENT)))
          .addMethod(
            getPacketAcknowledgementsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsRequest,
                ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsResponse>(
                  this, METHODID_PACKET_ACKNOWLEDGEMENTS)))
          .addMethod(
            getUnreceivedPacketsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsRequest,
                ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsResponse>(
                  this, METHODID_UNRECEIVED_PACKETS)))
          .addMethod(
            getUnreceivedAcksMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksRequest,
                ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksResponse>(
                  this, METHODID_UNRECEIVED_ACKS)))
          .addMethod(
            getNextSequenceReceiveMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveRequest,
                ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveResponse>(
                  this, METHODID_NEXT_SEQUENCE_RECEIVE)))
          .build();
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service
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
     * Channel queries an IBC Channel.
     * </pre>
     */
    public void channel(ibc.core.channel.v1.QueryOuterClass.QueryChannelRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryChannelResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChannelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Channels queries all the IBC channels of a chain.
     * </pre>
     */
    public void channels(ibc.core.channel.v1.QueryOuterClass.QueryChannelsRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryChannelsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChannelsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConnectionChannels queries all the channels associated with a connection
     * end.
     * </pre>
     */
    public void connectionChannels(ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConnectionChannelsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ChannelClientState queries for the client state for the channel associated
     * with the provided channel identifiers.
     * </pre>
     */
    public void channelClientState(ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChannelClientStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ChannelConsensusState queries for the consensus state for the channel
     * associated with the provided channel identifiers.
     * </pre>
     */
    public void channelConsensusState(ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChannelConsensusStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PacketCommitment queries a stored packet commitment hash.
     * </pre>
     */
    public void packetCommitment(ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPacketCommitmentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PacketCommitments returns all the packet commitments hashes associated
     * with a channel.
     * </pre>
     */
    public void packetCommitments(ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPacketCommitmentsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PacketReceipt queries if a given packet sequence has been received on the
     * queried chain
     * </pre>
     */
    public void packetReceipt(ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPacketReceiptMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PacketAcknowledgement queries a stored packet acknowledgement hash.
     * </pre>
     */
    public void packetAcknowledgement(ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPacketAcknowledgementMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PacketAcknowledgements returns all the packet acknowledgements associated
     * with a channel.
     * </pre>
     */
    public void packetAcknowledgements(ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPacketAcknowledgementsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnreceivedPackets returns all the unreceived IBC packets associated with a
     * channel and sequences.
     * </pre>
     */
    public void unreceivedPackets(ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnreceivedPacketsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnreceivedAcks returns all the unreceived IBC acknowledgements associated
     * with a channel and sequences.
     * </pre>
     */
    public void unreceivedAcks(ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnreceivedAcksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * NextSequenceReceive returns the next receive sequence for a given channel.
     * </pre>
     */
    public void nextSequenceReceive(ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveRequest request,
        io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNextSequenceReceiveMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service
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
     * Channel queries an IBC Channel.
     * </pre>
     */
    public ibc.core.channel.v1.QueryOuterClass.QueryChannelResponse channel(ibc.core.channel.v1.QueryOuterClass.QueryChannelRequest request) {
      return blockingUnaryCall(
          getChannel(), getChannelMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Channels queries all the IBC channels of a chain.
     * </pre>
     */
    public ibc.core.channel.v1.QueryOuterClass.QueryChannelsResponse channels(ibc.core.channel.v1.QueryOuterClass.QueryChannelsRequest request) {
      return blockingUnaryCall(
          getChannel(), getChannelsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConnectionChannels queries all the channels associated with a connection
     * end.
     * </pre>
     */
    public ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsResponse connectionChannels(ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsRequest request) {
      return blockingUnaryCall(
          getChannel(), getConnectionChannelsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ChannelClientState queries for the client state for the channel associated
     * with the provided channel identifiers.
     * </pre>
     */
    public ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateResponse channelClientState(ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest request) {
      return blockingUnaryCall(
          getChannel(), getChannelClientStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ChannelConsensusState queries for the consensus state for the channel
     * associated with the provided channel identifiers.
     * </pre>
     */
    public ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateResponse channelConsensusState(ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateRequest request) {
      return blockingUnaryCall(
          getChannel(), getChannelConsensusStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PacketCommitment queries a stored packet commitment hash.
     * </pre>
     */
    public ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentResponse packetCommitment(ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentRequest request) {
      return blockingUnaryCall(
          getChannel(), getPacketCommitmentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PacketCommitments returns all the packet commitments hashes associated
     * with a channel.
     * </pre>
     */
    public ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsResponse packetCommitments(ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPacketCommitmentsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PacketReceipt queries if a given packet sequence has been received on the
     * queried chain
     * </pre>
     */
    public ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptResponse packetReceipt(ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptRequest request) {
      return blockingUnaryCall(
          getChannel(), getPacketReceiptMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PacketAcknowledgement queries a stored packet acknowledgement hash.
     * </pre>
     */
    public ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementResponse packetAcknowledgement(ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementRequest request) {
      return blockingUnaryCall(
          getChannel(), getPacketAcknowledgementMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PacketAcknowledgements returns all the packet acknowledgements associated
     * with a channel.
     * </pre>
     */
    public ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsResponse packetAcknowledgements(ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPacketAcknowledgementsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnreceivedPackets returns all the unreceived IBC packets associated with a
     * channel and sequences.
     * </pre>
     */
    public ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsResponse unreceivedPackets(ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnreceivedPacketsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnreceivedAcks returns all the unreceived IBC acknowledgements associated
     * with a channel and sequences.
     * </pre>
     */
    public ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksResponse unreceivedAcks(ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnreceivedAcksMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * NextSequenceReceive returns the next receive sequence for a given channel.
     * </pre>
     */
    public ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveResponse nextSequenceReceive(ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveRequest request) {
      return blockingUnaryCall(
          getChannel(), getNextSequenceReceiveMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service
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
     * Channel queries an IBC Channel.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryChannelResponse> channel(
        ibc.core.channel.v1.QueryOuterClass.QueryChannelRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getChannelMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Channels queries all the IBC channels of a chain.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryChannelsResponse> channels(
        ibc.core.channel.v1.QueryOuterClass.QueryChannelsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getChannelsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConnectionChannels queries all the channels associated with a connection
     * end.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsResponse> connectionChannels(
        ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getConnectionChannelsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ChannelClientState queries for the client state for the channel associated
     * with the provided channel identifiers.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateResponse> channelClientState(
        ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getChannelClientStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ChannelConsensusState queries for the consensus state for the channel
     * associated with the provided channel identifiers.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateResponse> channelConsensusState(
        ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getChannelConsensusStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PacketCommitment queries a stored packet commitment hash.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentResponse> packetCommitment(
        ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPacketCommitmentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PacketCommitments returns all the packet commitments hashes associated
     * with a channel.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsResponse> packetCommitments(
        ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPacketCommitmentsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PacketReceipt queries if a given packet sequence has been received on the
     * queried chain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptResponse> packetReceipt(
        ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPacketReceiptMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PacketAcknowledgement queries a stored packet acknowledgement hash.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementResponse> packetAcknowledgement(
        ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPacketAcknowledgementMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PacketAcknowledgements returns all the packet acknowledgements associated
     * with a channel.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsResponse> packetAcknowledgements(
        ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPacketAcknowledgementsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnreceivedPackets returns all the unreceived IBC packets associated with a
     * channel and sequences.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsResponse> unreceivedPackets(
        ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnreceivedPacketsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnreceivedAcks returns all the unreceived IBC acknowledgements associated
     * with a channel and sequences.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksResponse> unreceivedAcks(
        ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnreceivedAcksMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * NextSequenceReceive returns the next receive sequence for a given channel.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveResponse> nextSequenceReceive(
        ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNextSequenceReceiveMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHANNEL = 0;
  private static final int METHODID_CHANNELS = 1;
  private static final int METHODID_CONNECTION_CHANNELS = 2;
  private static final int METHODID_CHANNEL_CLIENT_STATE = 3;
  private static final int METHODID_CHANNEL_CONSENSUS_STATE = 4;
  private static final int METHODID_PACKET_COMMITMENT = 5;
  private static final int METHODID_PACKET_COMMITMENTS = 6;
  private static final int METHODID_PACKET_RECEIPT = 7;
  private static final int METHODID_PACKET_ACKNOWLEDGEMENT = 8;
  private static final int METHODID_PACKET_ACKNOWLEDGEMENTS = 9;
  private static final int METHODID_UNRECEIVED_PACKETS = 10;
  private static final int METHODID_UNRECEIVED_ACKS = 11;
  private static final int METHODID_NEXT_SEQUENCE_RECEIVE = 12;

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
        case METHODID_CHANNEL:
          serviceImpl.channel((ibc.core.channel.v1.QueryOuterClass.QueryChannelRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryChannelResponse>) responseObserver);
          break;
        case METHODID_CHANNELS:
          serviceImpl.channels((ibc.core.channel.v1.QueryOuterClass.QueryChannelsRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryChannelsResponse>) responseObserver);
          break;
        case METHODID_CONNECTION_CHANNELS:
          serviceImpl.connectionChannels((ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsResponse>) responseObserver);
          break;
        case METHODID_CHANNEL_CLIENT_STATE:
          serviceImpl.channelClientState((ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateResponse>) responseObserver);
          break;
        case METHODID_CHANNEL_CONSENSUS_STATE:
          serviceImpl.channelConsensusState((ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateResponse>) responseObserver);
          break;
        case METHODID_PACKET_COMMITMENT:
          serviceImpl.packetCommitment((ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentResponse>) responseObserver);
          break;
        case METHODID_PACKET_COMMITMENTS:
          serviceImpl.packetCommitments((ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsResponse>) responseObserver);
          break;
        case METHODID_PACKET_RECEIPT:
          serviceImpl.packetReceipt((ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptResponse>) responseObserver);
          break;
        case METHODID_PACKET_ACKNOWLEDGEMENT:
          serviceImpl.packetAcknowledgement((ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementResponse>) responseObserver);
          break;
        case METHODID_PACKET_ACKNOWLEDGEMENTS:
          serviceImpl.packetAcknowledgements((ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsResponse>) responseObserver);
          break;
        case METHODID_UNRECEIVED_PACKETS:
          serviceImpl.unreceivedPackets((ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsResponse>) responseObserver);
          break;
        case METHODID_UNRECEIVED_ACKS:
          serviceImpl.unreceivedAcks((ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksResponse>) responseObserver);
          break;
        case METHODID_NEXT_SEQUENCE_RECEIVE:
          serviceImpl.nextSequenceReceive((ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveResponse>) responseObserver);
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
      return ibc.core.channel.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getChannelMethod())
              .addMethod(getChannelsMethod())
              .addMethod(getConnectionChannelsMethod())
              .addMethod(getChannelClientStateMethod())
              .addMethod(getChannelConsensusStateMethod())
              .addMethod(getPacketCommitmentMethod())
              .addMethod(getPacketCommitmentsMethod())
              .addMethod(getPacketReceiptMethod())
              .addMethod(getPacketAcknowledgementMethod())
              .addMethod(getPacketAcknowledgementsMethod())
              .addMethod(getUnreceivedPacketsMethod())
              .addMethod(getUnreceivedAcksMethod())
              .addMethod(getNextSequenceReceiveMethod())
              .build();
        }
      }
    }
    return result;
  }
}
