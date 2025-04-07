package com.ibc.core.client.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query provides defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: ibc/core/client/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "ibc.core.client.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryClientStateRequest,
      com.ibc.core.client.v1.QueryProto.QueryClientStateResponse> getClientStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClientState",
      requestType = com.ibc.core.client.v1.QueryProto.QueryClientStateRequest.class,
      responseType = com.ibc.core.client.v1.QueryProto.QueryClientStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryClientStateRequest,
      com.ibc.core.client.v1.QueryProto.QueryClientStateResponse> getClientStateMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryClientStateRequest, com.ibc.core.client.v1.QueryProto.QueryClientStateResponse> getClientStateMethod;
    if ((getClientStateMethod = QueryGrpc.getClientStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClientStateMethod = QueryGrpc.getClientStateMethod) == null) {
          QueryGrpc.getClientStateMethod = getClientStateMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v1.QueryProto.QueryClientStateRequest, com.ibc.core.client.v1.QueryProto.QueryClientStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClientState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryClientStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryClientStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClientState"))
              .build();
        }
      }
    }
    return getClientStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryClientStatesRequest,
      com.ibc.core.client.v1.QueryProto.QueryClientStatesResponse> getClientStatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClientStates",
      requestType = com.ibc.core.client.v1.QueryProto.QueryClientStatesRequest.class,
      responseType = com.ibc.core.client.v1.QueryProto.QueryClientStatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryClientStatesRequest,
      com.ibc.core.client.v1.QueryProto.QueryClientStatesResponse> getClientStatesMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryClientStatesRequest, com.ibc.core.client.v1.QueryProto.QueryClientStatesResponse> getClientStatesMethod;
    if ((getClientStatesMethod = QueryGrpc.getClientStatesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClientStatesMethod = QueryGrpc.getClientStatesMethod) == null) {
          QueryGrpc.getClientStatesMethod = getClientStatesMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v1.QueryProto.QueryClientStatesRequest, com.ibc.core.client.v1.QueryProto.QueryClientStatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClientStates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryClientStatesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryClientStatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClientStates"))
              .build();
        }
      }
    }
    return getClientStatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryConsensusStateRequest,
      com.ibc.core.client.v1.QueryProto.QueryConsensusStateResponse> getConsensusStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConsensusState",
      requestType = com.ibc.core.client.v1.QueryProto.QueryConsensusStateRequest.class,
      responseType = com.ibc.core.client.v1.QueryProto.QueryConsensusStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryConsensusStateRequest,
      com.ibc.core.client.v1.QueryProto.QueryConsensusStateResponse> getConsensusStateMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryConsensusStateRequest, com.ibc.core.client.v1.QueryProto.QueryConsensusStateResponse> getConsensusStateMethod;
    if ((getConsensusStateMethod = QueryGrpc.getConsensusStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getConsensusStateMethod = QueryGrpc.getConsensusStateMethod) == null) {
          QueryGrpc.getConsensusStateMethod = getConsensusStateMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v1.QueryProto.QueryConsensusStateRequest, com.ibc.core.client.v1.QueryProto.QueryConsensusStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConsensusState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryConsensusStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryConsensusStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ConsensusState"))
              .build();
        }
      }
    }
    return getConsensusStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryConsensusStatesRequest,
      com.ibc.core.client.v1.QueryProto.QueryConsensusStatesResponse> getConsensusStatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConsensusStates",
      requestType = com.ibc.core.client.v1.QueryProto.QueryConsensusStatesRequest.class,
      responseType = com.ibc.core.client.v1.QueryProto.QueryConsensusStatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryConsensusStatesRequest,
      com.ibc.core.client.v1.QueryProto.QueryConsensusStatesResponse> getConsensusStatesMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryConsensusStatesRequest, com.ibc.core.client.v1.QueryProto.QueryConsensusStatesResponse> getConsensusStatesMethod;
    if ((getConsensusStatesMethod = QueryGrpc.getConsensusStatesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getConsensusStatesMethod = QueryGrpc.getConsensusStatesMethod) == null) {
          QueryGrpc.getConsensusStatesMethod = getConsensusStatesMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v1.QueryProto.QueryConsensusStatesRequest, com.ibc.core.client.v1.QueryProto.QueryConsensusStatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConsensusStates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryConsensusStatesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryConsensusStatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ConsensusStates"))
              .build();
        }
      }
    }
    return getConsensusStatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsRequest,
      com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsResponse> getConsensusStateHeightsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConsensusStateHeights",
      requestType = com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsRequest.class,
      responseType = com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsRequest,
      com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsResponse> getConsensusStateHeightsMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsRequest, com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsResponse> getConsensusStateHeightsMethod;
    if ((getConsensusStateHeightsMethod = QueryGrpc.getConsensusStateHeightsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getConsensusStateHeightsMethod = QueryGrpc.getConsensusStateHeightsMethod) == null) {
          QueryGrpc.getConsensusStateHeightsMethod = getConsensusStateHeightsMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsRequest, com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConsensusStateHeights"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ConsensusStateHeights"))
              .build();
        }
      }
    }
    return getConsensusStateHeightsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryClientStatusRequest,
      com.ibc.core.client.v1.QueryProto.QueryClientStatusResponse> getClientStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClientStatus",
      requestType = com.ibc.core.client.v1.QueryProto.QueryClientStatusRequest.class,
      responseType = com.ibc.core.client.v1.QueryProto.QueryClientStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryClientStatusRequest,
      com.ibc.core.client.v1.QueryProto.QueryClientStatusResponse> getClientStatusMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryClientStatusRequest, com.ibc.core.client.v1.QueryProto.QueryClientStatusResponse> getClientStatusMethod;
    if ((getClientStatusMethod = QueryGrpc.getClientStatusMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClientStatusMethod = QueryGrpc.getClientStatusMethod) == null) {
          QueryGrpc.getClientStatusMethod = getClientStatusMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v1.QueryProto.QueryClientStatusRequest, com.ibc.core.client.v1.QueryProto.QueryClientStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClientStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryClientStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryClientStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClientStatus"))
              .build();
        }
      }
    }
    return getClientStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryClientParamsRequest,
      com.ibc.core.client.v1.QueryProto.QueryClientParamsResponse> getClientParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClientParams",
      requestType = com.ibc.core.client.v1.QueryProto.QueryClientParamsRequest.class,
      responseType = com.ibc.core.client.v1.QueryProto.QueryClientParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryClientParamsRequest,
      com.ibc.core.client.v1.QueryProto.QueryClientParamsResponse> getClientParamsMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryClientParamsRequest, com.ibc.core.client.v1.QueryProto.QueryClientParamsResponse> getClientParamsMethod;
    if ((getClientParamsMethod = QueryGrpc.getClientParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClientParamsMethod = QueryGrpc.getClientParamsMethod) == null) {
          QueryGrpc.getClientParamsMethod = getClientParamsMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v1.QueryProto.QueryClientParamsRequest, com.ibc.core.client.v1.QueryProto.QueryClientParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClientParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryClientParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryClientParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClientParams"))
              .build();
        }
      }
    }
    return getClientParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryClientCreatorRequest,
      com.ibc.core.client.v1.QueryProto.QueryClientCreatorResponse> getClientCreatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClientCreator",
      requestType = com.ibc.core.client.v1.QueryProto.QueryClientCreatorRequest.class,
      responseType = com.ibc.core.client.v1.QueryProto.QueryClientCreatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryClientCreatorRequest,
      com.ibc.core.client.v1.QueryProto.QueryClientCreatorResponse> getClientCreatorMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryClientCreatorRequest, com.ibc.core.client.v1.QueryProto.QueryClientCreatorResponse> getClientCreatorMethod;
    if ((getClientCreatorMethod = QueryGrpc.getClientCreatorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClientCreatorMethod = QueryGrpc.getClientCreatorMethod) == null) {
          QueryGrpc.getClientCreatorMethod = getClientCreatorMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v1.QueryProto.QueryClientCreatorRequest, com.ibc.core.client.v1.QueryProto.QueryClientCreatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClientCreator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryClientCreatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryClientCreatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClientCreator"))
              .build();
        }
      }
    }
    return getClientCreatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateRequest,
      com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateResponse> getUpgradedClientStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpgradedClientState",
      requestType = com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateRequest.class,
      responseType = com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateRequest,
      com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateResponse> getUpgradedClientStateMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateRequest, com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateResponse> getUpgradedClientStateMethod;
    if ((getUpgradedClientStateMethod = QueryGrpc.getUpgradedClientStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUpgradedClientStateMethod = QueryGrpc.getUpgradedClientStateMethod) == null) {
          QueryGrpc.getUpgradedClientStateMethod = getUpgradedClientStateMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateRequest, com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpgradedClientState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UpgradedClientState"))
              .build();
        }
      }
    }
    return getUpgradedClientStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateRequest,
      com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateResponse> getUpgradedConsensusStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpgradedConsensusState",
      requestType = com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateRequest.class,
      responseType = com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateRequest,
      com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateResponse> getUpgradedConsensusStateMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateRequest, com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateResponse> getUpgradedConsensusStateMethod;
    if ((getUpgradedConsensusStateMethod = QueryGrpc.getUpgradedConsensusStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUpgradedConsensusStateMethod = QueryGrpc.getUpgradedConsensusStateMethod) == null) {
          QueryGrpc.getUpgradedConsensusStateMethod = getUpgradedConsensusStateMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateRequest, com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpgradedConsensusState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UpgradedConsensusState"))
              .build();
        }
      }
    }
    return getUpgradedConsensusStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipRequest,
      com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipResponse> getVerifyMembershipMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "VerifyMembership",
      requestType = com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipRequest.class,
      responseType = com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipRequest,
      com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipResponse> getVerifyMembershipMethod() {
    io.grpc.MethodDescriptor<com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipRequest, com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipResponse> getVerifyMembershipMethod;
    if ((getVerifyMembershipMethod = QueryGrpc.getVerifyMembershipMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getVerifyMembershipMethod = QueryGrpc.getVerifyMembershipMethod) == null) {
          QueryGrpc.getVerifyMembershipMethod = getVerifyMembershipMethod =
              io.grpc.MethodDescriptor.<com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipRequest, com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "VerifyMembership"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("VerifyMembership"))
              .build();
        }
      }
    }
    return getVerifyMembershipMethod;
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
     * ClientState queries an IBC light client.
     * </pre>
     */
    default void clientState(com.ibc.core.client.v1.QueryProto.QueryClientStateRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryClientStateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClientStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClientStates queries all the IBC light clients of a chain.
     * </pre>
     */
    default void clientStates(com.ibc.core.client.v1.QueryProto.QueryClientStatesRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryClientStatesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClientStatesMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConsensusState queries a consensus state associated with a client state at
     * a given height.
     * </pre>
     */
    default void consensusState(com.ibc.core.client.v1.QueryProto.QueryConsensusStateRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryConsensusStateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConsensusStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConsensusStates queries all the consensus state associated with a given
     * client.
     * </pre>
     */
    default void consensusStates(com.ibc.core.client.v1.QueryProto.QueryConsensusStatesRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryConsensusStatesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConsensusStatesMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConsensusStateHeights queries the height of every consensus states associated with a given client.
     * </pre>
     */
    default void consensusStateHeights(com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConsensusStateHeightsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Status queries the status of an IBC client.
     * </pre>
     */
    default void clientStatus(com.ibc.core.client.v1.QueryProto.QueryClientStatusRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryClientStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClientStatusMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClientParams queries all parameters of the ibc client submodule.
     * </pre>
     */
    default void clientParams(com.ibc.core.client.v1.QueryProto.QueryClientParamsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryClientParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClientParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClientCreator queries the creator of a given client.
     * </pre>
     */
    default void clientCreator(com.ibc.core.client.v1.QueryProto.QueryClientCreatorRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryClientCreatorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClientCreatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpgradedClientState queries an Upgraded IBC light client.
     * </pre>
     */
    default void upgradedClientState(com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpgradedClientStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpgradedConsensusState queries an Upgraded IBC consensus state.
     * </pre>
     */
    default void upgradedConsensusState(com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpgradedConsensusStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * VerifyMembership queries an IBC light client for proof verification of a value at a given key path.
     * </pre>
     */
    default void verifyMembership(com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getVerifyMembershipMethod(), responseObserver);
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
     * ClientState queries an IBC light client.
     * </pre>
     */
    public void clientState(com.ibc.core.client.v1.QueryProto.QueryClientStateRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryClientStateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClientStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClientStates queries all the IBC light clients of a chain.
     * </pre>
     */
    public void clientStates(com.ibc.core.client.v1.QueryProto.QueryClientStatesRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryClientStatesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClientStatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConsensusState queries a consensus state associated with a client state at
     * a given height.
     * </pre>
     */
    public void consensusState(com.ibc.core.client.v1.QueryProto.QueryConsensusStateRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryConsensusStateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConsensusStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConsensusStates queries all the consensus state associated with a given
     * client.
     * </pre>
     */
    public void consensusStates(com.ibc.core.client.v1.QueryProto.QueryConsensusStatesRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryConsensusStatesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConsensusStatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConsensusStateHeights queries the height of every consensus states associated with a given client.
     * </pre>
     */
    public void consensusStateHeights(com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConsensusStateHeightsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Status queries the status of an IBC client.
     * </pre>
     */
    public void clientStatus(com.ibc.core.client.v1.QueryProto.QueryClientStatusRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryClientStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClientStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClientParams queries all parameters of the ibc client submodule.
     * </pre>
     */
    public void clientParams(com.ibc.core.client.v1.QueryProto.QueryClientParamsRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryClientParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClientParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClientCreator queries the creator of a given client.
     * </pre>
     */
    public void clientCreator(com.ibc.core.client.v1.QueryProto.QueryClientCreatorRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryClientCreatorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClientCreatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpgradedClientState queries an Upgraded IBC light client.
     * </pre>
     */
    public void upgradedClientState(com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpgradedClientStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpgradedConsensusState queries an Upgraded IBC consensus state.
     * </pre>
     */
    public void upgradedConsensusState(com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpgradedConsensusStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * VerifyMembership queries an IBC light client for proof verification of a value at a given key path.
     * </pre>
     */
    public void verifyMembership(com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipRequest request,
        io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getVerifyMembershipMethod(), getCallOptions()), request, responseObserver);
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
     * ClientState queries an IBC light client.
     * </pre>
     */
    public com.ibc.core.client.v1.QueryProto.QueryClientStateResponse clientState(com.ibc.core.client.v1.QueryProto.QueryClientStateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClientStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClientStates queries all the IBC light clients of a chain.
     * </pre>
     */
    public com.ibc.core.client.v1.QueryProto.QueryClientStatesResponse clientStates(com.ibc.core.client.v1.QueryProto.QueryClientStatesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClientStatesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConsensusState queries a consensus state associated with a client state at
     * a given height.
     * </pre>
     */
    public com.ibc.core.client.v1.QueryProto.QueryConsensusStateResponse consensusState(com.ibc.core.client.v1.QueryProto.QueryConsensusStateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConsensusStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConsensusStates queries all the consensus state associated with a given
     * client.
     * </pre>
     */
    public com.ibc.core.client.v1.QueryProto.QueryConsensusStatesResponse consensusStates(com.ibc.core.client.v1.QueryProto.QueryConsensusStatesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConsensusStatesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConsensusStateHeights queries the height of every consensus states associated with a given client.
     * </pre>
     */
    public com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsResponse consensusStateHeights(com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConsensusStateHeightsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Status queries the status of an IBC client.
     * </pre>
     */
    public com.ibc.core.client.v1.QueryProto.QueryClientStatusResponse clientStatus(com.ibc.core.client.v1.QueryProto.QueryClientStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClientStatusMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClientParams queries all parameters of the ibc client submodule.
     * </pre>
     */
    public com.ibc.core.client.v1.QueryProto.QueryClientParamsResponse clientParams(com.ibc.core.client.v1.QueryProto.QueryClientParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClientParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClientCreator queries the creator of a given client.
     * </pre>
     */
    public com.ibc.core.client.v1.QueryProto.QueryClientCreatorResponse clientCreator(com.ibc.core.client.v1.QueryProto.QueryClientCreatorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClientCreatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpgradedClientState queries an Upgraded IBC light client.
     * </pre>
     */
    public com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateResponse upgradedClientState(com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpgradedClientStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpgradedConsensusState queries an Upgraded IBC consensus state.
     * </pre>
     */
    public com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateResponse upgradedConsensusState(com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpgradedConsensusStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * VerifyMembership queries an IBC light client for proof verification of a value at a given key path.
     * </pre>
     */
    public com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipResponse verifyMembership(com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getVerifyMembershipMethod(), getCallOptions(), request);
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
     * ClientState queries an IBC light client.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v1.QueryProto.QueryClientStateResponse> clientState(
        com.ibc.core.client.v1.QueryProto.QueryClientStateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClientStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClientStates queries all the IBC light clients of a chain.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v1.QueryProto.QueryClientStatesResponse> clientStates(
        com.ibc.core.client.v1.QueryProto.QueryClientStatesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClientStatesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConsensusState queries a consensus state associated with a client state at
     * a given height.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v1.QueryProto.QueryConsensusStateResponse> consensusState(
        com.ibc.core.client.v1.QueryProto.QueryConsensusStateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConsensusStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConsensusStates queries all the consensus state associated with a given
     * client.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v1.QueryProto.QueryConsensusStatesResponse> consensusStates(
        com.ibc.core.client.v1.QueryProto.QueryConsensusStatesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConsensusStatesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConsensusStateHeights queries the height of every consensus states associated with a given client.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsResponse> consensusStateHeights(
        com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConsensusStateHeightsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Status queries the status of an IBC client.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v1.QueryProto.QueryClientStatusResponse> clientStatus(
        com.ibc.core.client.v1.QueryProto.QueryClientStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClientStatusMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClientParams queries all parameters of the ibc client submodule.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v1.QueryProto.QueryClientParamsResponse> clientParams(
        com.ibc.core.client.v1.QueryProto.QueryClientParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClientParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClientCreator queries the creator of a given client.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v1.QueryProto.QueryClientCreatorResponse> clientCreator(
        com.ibc.core.client.v1.QueryProto.QueryClientCreatorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClientCreatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpgradedClientState queries an Upgraded IBC light client.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateResponse> upgradedClientState(
        com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpgradedClientStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpgradedConsensusState queries an Upgraded IBC consensus state.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateResponse> upgradedConsensusState(
        com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpgradedConsensusStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * VerifyMembership queries an IBC light client for proof verification of a value at a given key path.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipResponse> verifyMembership(
        com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getVerifyMembershipMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CLIENT_STATE = 0;
  private static final int METHODID_CLIENT_STATES = 1;
  private static final int METHODID_CONSENSUS_STATE = 2;
  private static final int METHODID_CONSENSUS_STATES = 3;
  private static final int METHODID_CONSENSUS_STATE_HEIGHTS = 4;
  private static final int METHODID_CLIENT_STATUS = 5;
  private static final int METHODID_CLIENT_PARAMS = 6;
  private static final int METHODID_CLIENT_CREATOR = 7;
  private static final int METHODID_UPGRADED_CLIENT_STATE = 8;
  private static final int METHODID_UPGRADED_CONSENSUS_STATE = 9;
  private static final int METHODID_VERIFY_MEMBERSHIP = 10;

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
        case METHODID_CLIENT_STATE:
          serviceImpl.clientState((com.ibc.core.client.v1.QueryProto.QueryClientStateRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryClientStateResponse>) responseObserver);
          break;
        case METHODID_CLIENT_STATES:
          serviceImpl.clientStates((com.ibc.core.client.v1.QueryProto.QueryClientStatesRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryClientStatesResponse>) responseObserver);
          break;
        case METHODID_CONSENSUS_STATE:
          serviceImpl.consensusState((com.ibc.core.client.v1.QueryProto.QueryConsensusStateRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryConsensusStateResponse>) responseObserver);
          break;
        case METHODID_CONSENSUS_STATES:
          serviceImpl.consensusStates((com.ibc.core.client.v1.QueryProto.QueryConsensusStatesRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryConsensusStatesResponse>) responseObserver);
          break;
        case METHODID_CONSENSUS_STATE_HEIGHTS:
          serviceImpl.consensusStateHeights((com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsResponse>) responseObserver);
          break;
        case METHODID_CLIENT_STATUS:
          serviceImpl.clientStatus((com.ibc.core.client.v1.QueryProto.QueryClientStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryClientStatusResponse>) responseObserver);
          break;
        case METHODID_CLIENT_PARAMS:
          serviceImpl.clientParams((com.ibc.core.client.v1.QueryProto.QueryClientParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryClientParamsResponse>) responseObserver);
          break;
        case METHODID_CLIENT_CREATOR:
          serviceImpl.clientCreator((com.ibc.core.client.v1.QueryProto.QueryClientCreatorRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryClientCreatorResponse>) responseObserver);
          break;
        case METHODID_UPGRADED_CLIENT_STATE:
          serviceImpl.upgradedClientState((com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateResponse>) responseObserver);
          break;
        case METHODID_UPGRADED_CONSENSUS_STATE:
          serviceImpl.upgradedConsensusState((com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateResponse>) responseObserver);
          break;
        case METHODID_VERIFY_MEMBERSHIP:
          serviceImpl.verifyMembership((com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipRequest) request,
              (io.grpc.stub.StreamObserver<com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipResponse>) responseObserver);
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
          getClientStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v1.QueryProto.QueryClientStateRequest,
              com.ibc.core.client.v1.QueryProto.QueryClientStateResponse>(
                service, METHODID_CLIENT_STATE)))
        .addMethod(
          getClientStatesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v1.QueryProto.QueryClientStatesRequest,
              com.ibc.core.client.v1.QueryProto.QueryClientStatesResponse>(
                service, METHODID_CLIENT_STATES)))
        .addMethod(
          getConsensusStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v1.QueryProto.QueryConsensusStateRequest,
              com.ibc.core.client.v1.QueryProto.QueryConsensusStateResponse>(
                service, METHODID_CONSENSUS_STATE)))
        .addMethod(
          getConsensusStatesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v1.QueryProto.QueryConsensusStatesRequest,
              com.ibc.core.client.v1.QueryProto.QueryConsensusStatesResponse>(
                service, METHODID_CONSENSUS_STATES)))
        .addMethod(
          getConsensusStateHeightsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsRequest,
              com.ibc.core.client.v1.QueryProto.QueryConsensusStateHeightsResponse>(
                service, METHODID_CONSENSUS_STATE_HEIGHTS)))
        .addMethod(
          getClientStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v1.QueryProto.QueryClientStatusRequest,
              com.ibc.core.client.v1.QueryProto.QueryClientStatusResponse>(
                service, METHODID_CLIENT_STATUS)))
        .addMethod(
          getClientParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v1.QueryProto.QueryClientParamsRequest,
              com.ibc.core.client.v1.QueryProto.QueryClientParamsResponse>(
                service, METHODID_CLIENT_PARAMS)))
        .addMethod(
          getClientCreatorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v1.QueryProto.QueryClientCreatorRequest,
              com.ibc.core.client.v1.QueryProto.QueryClientCreatorResponse>(
                service, METHODID_CLIENT_CREATOR)))
        .addMethod(
          getUpgradedClientStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateRequest,
              com.ibc.core.client.v1.QueryProto.QueryUpgradedClientStateResponse>(
                service, METHODID_UPGRADED_CLIENT_STATE)))
        .addMethod(
          getUpgradedConsensusStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateRequest,
              com.ibc.core.client.v1.QueryProto.QueryUpgradedConsensusStateResponse>(
                service, METHODID_UPGRADED_CONSENSUS_STATE)))
        .addMethod(
          getVerifyMembershipMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipRequest,
              com.ibc.core.client.v1.QueryProto.QueryVerifyMembershipResponse>(
                service, METHODID_VERIFY_MEMBERSHIP)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ibc.core.client.v1.QueryProto.getDescriptor();
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
              .addMethod(getClientStateMethod())
              .addMethod(getClientStatesMethod())
              .addMethod(getConsensusStateMethod())
              .addMethod(getConsensusStatesMethod())
              .addMethod(getConsensusStateHeightsMethod())
              .addMethod(getClientStatusMethod())
              .addMethod(getClientParamsMethod())
              .addMethod(getClientCreatorMethod())
              .addMethod(getUpgradedClientStateMethod())
              .addMethod(getUpgradedConsensusStateMethod())
              .addMethod(getVerifyMembershipMethod())
              .build();
        }
      }
    }
    return result;
  }
}
