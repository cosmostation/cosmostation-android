package com.ibc.applications.fee.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the ICS29 gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: ibc/applications/fee/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "ibc.applications.fee.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsResponse> getIncentivizedPacketsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IncentivizedPackets",
      requestType = com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsRequest.class,
      responseType = com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsResponse> getIncentivizedPacketsMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsRequest, com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsResponse> getIncentivizedPacketsMethod;
    if ((getIncentivizedPacketsMethod = QueryGrpc.getIncentivizedPacketsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getIncentivizedPacketsMethod = QueryGrpc.getIncentivizedPacketsMethod) == null) {
          QueryGrpc.getIncentivizedPacketsMethod = getIncentivizedPacketsMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsRequest, com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IncentivizedPackets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("IncentivizedPackets"))
              .build();
        }
      }
    }
    return getIncentivizedPacketsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketResponse> getIncentivizedPacketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IncentivizedPacket",
      requestType = com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketRequest.class,
      responseType = com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketResponse> getIncentivizedPacketMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketRequest, com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketResponse> getIncentivizedPacketMethod;
    if ((getIncentivizedPacketMethod = QueryGrpc.getIncentivizedPacketMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getIncentivizedPacketMethod = QueryGrpc.getIncentivizedPacketMethod) == null) {
          QueryGrpc.getIncentivizedPacketMethod = getIncentivizedPacketMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketRequest, com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IncentivizedPacket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("IncentivizedPacket"))
              .build();
        }
      }
    }
    return getIncentivizedPacketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelResponse> getIncentivizedPacketsForChannelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IncentivizedPacketsForChannel",
      requestType = com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelRequest.class,
      responseType = com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelResponse> getIncentivizedPacketsForChannelMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelRequest, com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelResponse> getIncentivizedPacketsForChannelMethod;
    if ((getIncentivizedPacketsForChannelMethod = QueryGrpc.getIncentivizedPacketsForChannelMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getIncentivizedPacketsForChannelMethod = QueryGrpc.getIncentivizedPacketsForChannelMethod) == null) {
          QueryGrpc.getIncentivizedPacketsForChannelMethod = getIncentivizedPacketsForChannelMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelRequest, com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IncentivizedPacketsForChannel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("IncentivizedPacketsForChannel"))
              .build();
        }
      }
    }
    return getIncentivizedPacketsForChannelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesResponse> getTotalRecvFeesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalRecvFees",
      requestType = com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesRequest.class,
      responseType = com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesResponse> getTotalRecvFeesMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesRequest, com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesResponse> getTotalRecvFeesMethod;
    if ((getTotalRecvFeesMethod = QueryGrpc.getTotalRecvFeesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalRecvFeesMethod = QueryGrpc.getTotalRecvFeesMethod) == null) {
          QueryGrpc.getTotalRecvFeesMethod = getTotalRecvFeesMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesRequest, com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalRecvFees"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalRecvFees"))
              .build();
        }
      }
    }
    return getTotalRecvFeesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesResponse> getTotalAckFeesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalAckFees",
      requestType = com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesRequest.class,
      responseType = com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesResponse> getTotalAckFeesMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesRequest, com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesResponse> getTotalAckFeesMethod;
    if ((getTotalAckFeesMethod = QueryGrpc.getTotalAckFeesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalAckFeesMethod = QueryGrpc.getTotalAckFeesMethod) == null) {
          QueryGrpc.getTotalAckFeesMethod = getTotalAckFeesMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesRequest, com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalAckFees"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalAckFees"))
              .build();
        }
      }
    }
    return getTotalAckFeesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesResponse> getTotalTimeoutFeesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalTimeoutFees",
      requestType = com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesRequest.class,
      responseType = com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesResponse> getTotalTimeoutFeesMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesRequest, com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesResponse> getTotalTimeoutFeesMethod;
    if ((getTotalTimeoutFeesMethod = QueryGrpc.getTotalTimeoutFeesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalTimeoutFeesMethod = QueryGrpc.getTotalTimeoutFeesMethod) == null) {
          QueryGrpc.getTotalTimeoutFeesMethod = getTotalTimeoutFeesMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesRequest, com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalTimeoutFees"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalTimeoutFees"))
              .build();
        }
      }
    }
    return getTotalTimeoutFeesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryPayeeRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryPayeeResponse> getPayeeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Payee",
      requestType = com.ibc.applications.fee.v1.QueryProto.QueryPayeeRequest.class,
      responseType = com.ibc.applications.fee.v1.QueryProto.QueryPayeeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryPayeeRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryPayeeResponse> getPayeeMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryPayeeRequest, com.ibc.applications.fee.v1.QueryProto.QueryPayeeResponse> getPayeeMethod;
    if ((getPayeeMethod = QueryGrpc.getPayeeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPayeeMethod = QueryGrpc.getPayeeMethod) == null) {
          QueryGrpc.getPayeeMethod = getPayeeMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.fee.v1.QueryProto.QueryPayeeRequest, com.ibc.applications.fee.v1.QueryProto.QueryPayeeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Payee"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryPayeeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryPayeeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Payee"))
              .build();
        }
      }
    }
    return getPayeeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeResponse> getCounterpartyPayeeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CounterpartyPayee",
      requestType = com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeRequest.class,
      responseType = com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeResponse> getCounterpartyPayeeMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeRequest, com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeResponse> getCounterpartyPayeeMethod;
    if ((getCounterpartyPayeeMethod = QueryGrpc.getCounterpartyPayeeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCounterpartyPayeeMethod = QueryGrpc.getCounterpartyPayeeMethod) == null) {
          QueryGrpc.getCounterpartyPayeeMethod = getCounterpartyPayeeMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeRequest, com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CounterpartyPayee"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CounterpartyPayee"))
              .build();
        }
      }
    }
    return getCounterpartyPayeeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsResponse> getFeeEnabledChannelsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FeeEnabledChannels",
      requestType = com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsRequest.class,
      responseType = com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsResponse> getFeeEnabledChannelsMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsRequest, com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsResponse> getFeeEnabledChannelsMethod;
    if ((getFeeEnabledChannelsMethod = QueryGrpc.getFeeEnabledChannelsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFeeEnabledChannelsMethod = QueryGrpc.getFeeEnabledChannelsMethod) == null) {
          QueryGrpc.getFeeEnabledChannelsMethod = getFeeEnabledChannelsMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsRequest, com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FeeEnabledChannels"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FeeEnabledChannels"))
              .build();
        }
      }
    }
    return getFeeEnabledChannelsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelResponse> getFeeEnabledChannelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FeeEnabledChannel",
      requestType = com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelRequest.class,
      responseType = com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelRequest,
      com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelResponse> getFeeEnabledChannelMethod() {
    io.grpc.MethodDescriptor<com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelRequest, com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelResponse> getFeeEnabledChannelMethod;
    if ((getFeeEnabledChannelMethod = QueryGrpc.getFeeEnabledChannelMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFeeEnabledChannelMethod = QueryGrpc.getFeeEnabledChannelMethod) == null) {
          QueryGrpc.getFeeEnabledChannelMethod = getFeeEnabledChannelMethod =
              io.grpc.MethodDescriptor.<com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelRequest, com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FeeEnabledChannel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FeeEnabledChannel"))
              .build();
        }
      }
    }
    return getFeeEnabledChannelMethod;
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
   * Query defines the ICS29 gRPC querier service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * IncentivizedPackets returns all incentivized packets and their associated fees
     * </pre>
     */
    default void incentivizedPackets(com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getIncentivizedPacketsMethod(), responseObserver);
    }

    /**
     * <pre>
     * IncentivizedPacket returns all packet fees for a packet given its identifier
     * </pre>
     */
    default void incentivizedPacket(com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getIncentivizedPacketMethod(), responseObserver);
    }

    /**
     * <pre>
     * Gets all incentivized packets for a specific channel
     * </pre>
     */
    default void incentivizedPacketsForChannel(com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getIncentivizedPacketsForChannelMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalRecvFees returns the total receive fees for a packet given its identifier
     * </pre>
     */
    default void totalRecvFees(com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTotalRecvFeesMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalAckFees returns the total acknowledgement fees for a packet given its identifier
     * </pre>
     */
    default void totalAckFees(com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTotalAckFeesMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalTimeoutFees returns the total timeout fees for a packet given its identifier
     * </pre>
     */
    default void totalTimeoutFees(com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTotalTimeoutFeesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Payee returns the registered payee address for a specific channel given the relayer address
     * </pre>
     */
    default void payee(com.ibc.applications.fee.v1.QueryProto.QueryPayeeRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryPayeeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPayeeMethod(), responseObserver);
    }

    /**
     * <pre>
     * CounterpartyPayee returns the registered counterparty payee for forward relaying
     * </pre>
     */
    default void counterpartyPayee(com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCounterpartyPayeeMethod(), responseObserver);
    }

    /**
     * <pre>
     * FeeEnabledChannels returns a list of all fee enabled channels
     * </pre>
     */
    default void feeEnabledChannels(com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFeeEnabledChannelsMethod(), responseObserver);
    }

    /**
     * <pre>
     * FeeEnabledChannel returns true if the provided port and channel identifiers belong to a fee enabled channel
     * </pre>
     */
    default void feeEnabledChannel(com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFeeEnabledChannelMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the ICS29 gRPC querier service.
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
   * Query defines the ICS29 gRPC querier service.
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
     * IncentivizedPackets returns all incentivized packets and their associated fees
     * </pre>
     */
    public void incentivizedPackets(com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getIncentivizedPacketsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * IncentivizedPacket returns all packet fees for a packet given its identifier
     * </pre>
     */
    public void incentivizedPacket(com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getIncentivizedPacketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Gets all incentivized packets for a specific channel
     * </pre>
     */
    public void incentivizedPacketsForChannel(com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getIncentivizedPacketsForChannelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalRecvFees returns the total receive fees for a packet given its identifier
     * </pre>
     */
    public void totalRecvFees(com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTotalRecvFeesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalAckFees returns the total acknowledgement fees for a packet given its identifier
     * </pre>
     */
    public void totalAckFees(com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTotalAckFeesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalTimeoutFees returns the total timeout fees for a packet given its identifier
     * </pre>
     */
    public void totalTimeoutFees(com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTotalTimeoutFeesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Payee returns the registered payee address for a specific channel given the relayer address
     * </pre>
     */
    public void payee(com.ibc.applications.fee.v1.QueryProto.QueryPayeeRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryPayeeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPayeeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CounterpartyPayee returns the registered counterparty payee for forward relaying
     * </pre>
     */
    public void counterpartyPayee(com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCounterpartyPayeeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * FeeEnabledChannels returns a list of all fee enabled channels
     * </pre>
     */
    public void feeEnabledChannels(com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFeeEnabledChannelsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * FeeEnabledChannel returns true if the provided port and channel identifiers belong to a fee enabled channel
     * </pre>
     */
    public void feeEnabledChannel(com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelRequest request,
        io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFeeEnabledChannelMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the ICS29 gRPC querier service.
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
     * IncentivizedPackets returns all incentivized packets and their associated fees
     * </pre>
     */
    public com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsResponse incentivizedPackets(com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getIncentivizedPacketsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * IncentivizedPacket returns all packet fees for a packet given its identifier
     * </pre>
     */
    public com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketResponse incentivizedPacket(com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getIncentivizedPacketMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Gets all incentivized packets for a specific channel
     * </pre>
     */
    public com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelResponse incentivizedPacketsForChannel(com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getIncentivizedPacketsForChannelMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalRecvFees returns the total receive fees for a packet given its identifier
     * </pre>
     */
    public com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesResponse totalRecvFees(com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTotalRecvFeesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalAckFees returns the total acknowledgement fees for a packet given its identifier
     * </pre>
     */
    public com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesResponse totalAckFees(com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTotalAckFeesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalTimeoutFees returns the total timeout fees for a packet given its identifier
     * </pre>
     */
    public com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesResponse totalTimeoutFees(com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTotalTimeoutFeesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Payee returns the registered payee address for a specific channel given the relayer address
     * </pre>
     */
    public com.ibc.applications.fee.v1.QueryProto.QueryPayeeResponse payee(com.ibc.applications.fee.v1.QueryProto.QueryPayeeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPayeeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CounterpartyPayee returns the registered counterparty payee for forward relaying
     * </pre>
     */
    public com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeResponse counterpartyPayee(com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCounterpartyPayeeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * FeeEnabledChannels returns a list of all fee enabled channels
     * </pre>
     */
    public com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsResponse feeEnabledChannels(com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFeeEnabledChannelsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * FeeEnabledChannel returns true if the provided port and channel identifiers belong to a fee enabled channel
     * </pre>
     */
    public com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelResponse feeEnabledChannel(com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFeeEnabledChannelMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the ICS29 gRPC querier service.
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
     * IncentivizedPackets returns all incentivized packets and their associated fees
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsResponse> incentivizedPackets(
        com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getIncentivizedPacketsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * IncentivizedPacket returns all packet fees for a packet given its identifier
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketResponse> incentivizedPacket(
        com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getIncentivizedPacketMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Gets all incentivized packets for a specific channel
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelResponse> incentivizedPacketsForChannel(
        com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getIncentivizedPacketsForChannelMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalRecvFees returns the total receive fees for a packet given its identifier
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesResponse> totalRecvFees(
        com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTotalRecvFeesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalAckFees returns the total acknowledgement fees for a packet given its identifier
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesResponse> totalAckFees(
        com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTotalAckFeesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalTimeoutFees returns the total timeout fees for a packet given its identifier
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesResponse> totalTimeoutFees(
        com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTotalTimeoutFeesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Payee returns the registered payee address for a specific channel given the relayer address
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.fee.v1.QueryProto.QueryPayeeResponse> payee(
        com.ibc.applications.fee.v1.QueryProto.QueryPayeeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPayeeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CounterpartyPayee returns the registered counterparty payee for forward relaying
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeResponse> counterpartyPayee(
        com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCounterpartyPayeeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * FeeEnabledChannels returns a list of all fee enabled channels
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsResponse> feeEnabledChannels(
        com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFeeEnabledChannelsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * FeeEnabledChannel returns true if the provided port and channel identifiers belong to a fee enabled channel
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelResponse> feeEnabledChannel(
        com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFeeEnabledChannelMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INCENTIVIZED_PACKETS = 0;
  private static final int METHODID_INCENTIVIZED_PACKET = 1;
  private static final int METHODID_INCENTIVIZED_PACKETS_FOR_CHANNEL = 2;
  private static final int METHODID_TOTAL_RECV_FEES = 3;
  private static final int METHODID_TOTAL_ACK_FEES = 4;
  private static final int METHODID_TOTAL_TIMEOUT_FEES = 5;
  private static final int METHODID_PAYEE = 6;
  private static final int METHODID_COUNTERPARTY_PAYEE = 7;
  private static final int METHODID_FEE_ENABLED_CHANNELS = 8;
  private static final int METHODID_FEE_ENABLED_CHANNEL = 9;

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
        case METHODID_INCENTIVIZED_PACKETS:
          serviceImpl.incentivizedPackets((com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsResponse>) responseObserver);
          break;
        case METHODID_INCENTIVIZED_PACKET:
          serviceImpl.incentivizedPacket((com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketResponse>) responseObserver);
          break;
        case METHODID_INCENTIVIZED_PACKETS_FOR_CHANNEL:
          serviceImpl.incentivizedPacketsForChannel((com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelResponse>) responseObserver);
          break;
        case METHODID_TOTAL_RECV_FEES:
          serviceImpl.totalRecvFees((com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesResponse>) responseObserver);
          break;
        case METHODID_TOTAL_ACK_FEES:
          serviceImpl.totalAckFees((com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesResponse>) responseObserver);
          break;
        case METHODID_TOTAL_TIMEOUT_FEES:
          serviceImpl.totalTimeoutFees((com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesResponse>) responseObserver);
          break;
        case METHODID_PAYEE:
          serviceImpl.payee((com.ibc.applications.fee.v1.QueryProto.QueryPayeeRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryPayeeResponse>) responseObserver);
          break;
        case METHODID_COUNTERPARTY_PAYEE:
          serviceImpl.counterpartyPayee((com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeResponse>) responseObserver);
          break;
        case METHODID_FEE_ENABLED_CHANNELS:
          serviceImpl.feeEnabledChannels((com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsResponse>) responseObserver);
          break;
        case METHODID_FEE_ENABLED_CHANNEL:
          serviceImpl.feeEnabledChannel((com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelResponse>) responseObserver);
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
          getIncentivizedPacketsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsRequest,
              com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsResponse>(
                service, METHODID_INCENTIVIZED_PACKETS)))
        .addMethod(
          getIncentivizedPacketMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketRequest,
              com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketResponse>(
                service, METHODID_INCENTIVIZED_PACKET)))
        .addMethod(
          getIncentivizedPacketsForChannelMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelRequest,
              com.ibc.applications.fee.v1.QueryProto.QueryIncentivizedPacketsForChannelResponse>(
                service, METHODID_INCENTIVIZED_PACKETS_FOR_CHANNEL)))
        .addMethod(
          getTotalRecvFeesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesRequest,
              com.ibc.applications.fee.v1.QueryProto.QueryTotalRecvFeesResponse>(
                service, METHODID_TOTAL_RECV_FEES)))
        .addMethod(
          getTotalAckFeesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesRequest,
              com.ibc.applications.fee.v1.QueryProto.QueryTotalAckFeesResponse>(
                service, METHODID_TOTAL_ACK_FEES)))
        .addMethod(
          getTotalTimeoutFeesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesRequest,
              com.ibc.applications.fee.v1.QueryProto.QueryTotalTimeoutFeesResponse>(
                service, METHODID_TOTAL_TIMEOUT_FEES)))
        .addMethod(
          getPayeeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.fee.v1.QueryProto.QueryPayeeRequest,
              com.ibc.applications.fee.v1.QueryProto.QueryPayeeResponse>(
                service, METHODID_PAYEE)))
        .addMethod(
          getCounterpartyPayeeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeRequest,
              com.ibc.applications.fee.v1.QueryProto.QueryCounterpartyPayeeResponse>(
                service, METHODID_COUNTERPARTY_PAYEE)))
        .addMethod(
          getFeeEnabledChannelsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsRequest,
              com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelsResponse>(
                service, METHODID_FEE_ENABLED_CHANNELS)))
        .addMethod(
          getFeeEnabledChannelMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelRequest,
              com.ibc.applications.fee.v1.QueryProto.QueryFeeEnabledChannelResponse>(
                service, METHODID_FEE_ENABLED_CHANNEL)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ibc.applications.fee.v1.QueryProto.getDescriptor();
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
              .addMethod(getIncentivizedPacketsMethod())
              .addMethod(getIncentivizedPacketMethod())
              .addMethod(getIncentivizedPacketsForChannelMethod())
              .addMethod(getTotalRecvFeesMethod())
              .addMethod(getTotalAckFeesMethod())
              .addMethod(getTotalTimeoutFeesMethod())
              .addMethod(getPayeeMethod())
              .addMethod(getCounterpartyPayeeMethod())
              .addMethod(getFeeEnabledChannelsMethod())
              .addMethod(getFeeEnabledChannelMethod())
              .build();
        }
      }
    }
    return result;
  }
}
