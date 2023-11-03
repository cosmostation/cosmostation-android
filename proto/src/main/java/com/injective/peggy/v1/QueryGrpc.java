package com.injective.peggy.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: injective/peggy/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "injective.peggy.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryParamsRequest,
      com.injective.peggy.v1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.injective.peggy.v1.QueryProto.QueryParamsRequest.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryParamsRequest,
      com.injective.peggy.v1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryParamsRequest, com.injective.peggy.v1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryParamsRequest, com.injective.peggy.v1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryCurrentValsetRequest,
      com.injective.peggy.v1.QueryProto.QueryCurrentValsetResponse> getCurrentValsetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CurrentValset",
      requestType = com.injective.peggy.v1.QueryProto.QueryCurrentValsetRequest.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryCurrentValsetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryCurrentValsetRequest,
      com.injective.peggy.v1.QueryProto.QueryCurrentValsetResponse> getCurrentValsetMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryCurrentValsetRequest, com.injective.peggy.v1.QueryProto.QueryCurrentValsetResponse> getCurrentValsetMethod;
    if ((getCurrentValsetMethod = QueryGrpc.getCurrentValsetMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCurrentValsetMethod = QueryGrpc.getCurrentValsetMethod) == null) {
          QueryGrpc.getCurrentValsetMethod = getCurrentValsetMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryCurrentValsetRequest, com.injective.peggy.v1.QueryProto.QueryCurrentValsetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CurrentValset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryCurrentValsetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryCurrentValsetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CurrentValset"))
              .build();
        }
      }
    }
    return getCurrentValsetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryValsetRequestRequest,
      com.injective.peggy.v1.QueryProto.QueryValsetRequestResponse> getValsetRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetRequest",
      requestType = com.injective.peggy.v1.QueryProto.QueryValsetRequestRequest.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryValsetRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryValsetRequestRequest,
      com.injective.peggy.v1.QueryProto.QueryValsetRequestResponse> getValsetRequestMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryValsetRequestRequest, com.injective.peggy.v1.QueryProto.QueryValsetRequestResponse> getValsetRequestMethod;
    if ((getValsetRequestMethod = QueryGrpc.getValsetRequestMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValsetRequestMethod = QueryGrpc.getValsetRequestMethod) == null) {
          QueryGrpc.getValsetRequestMethod = getValsetRequestMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryValsetRequestRequest, com.injective.peggy.v1.QueryProto.QueryValsetRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryValsetRequestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryValsetRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValsetRequest"))
              .build();
        }
      }
    }
    return getValsetRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryValsetConfirmRequest,
      com.injective.peggy.v1.QueryProto.QueryValsetConfirmResponse> getValsetConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetConfirm",
      requestType = com.injective.peggy.v1.QueryProto.QueryValsetConfirmRequest.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryValsetConfirmResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryValsetConfirmRequest,
      com.injective.peggy.v1.QueryProto.QueryValsetConfirmResponse> getValsetConfirmMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryValsetConfirmRequest, com.injective.peggy.v1.QueryProto.QueryValsetConfirmResponse> getValsetConfirmMethod;
    if ((getValsetConfirmMethod = QueryGrpc.getValsetConfirmMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValsetConfirmMethod = QueryGrpc.getValsetConfirmMethod) == null) {
          QueryGrpc.getValsetConfirmMethod = getValsetConfirmMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryValsetConfirmRequest, com.injective.peggy.v1.QueryProto.QueryValsetConfirmResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetConfirm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryValsetConfirmRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryValsetConfirmResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValsetConfirm"))
              .build();
        }
      }
    }
    return getValsetConfirmMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceRequest,
      com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceResponse> getValsetConfirmsByNonceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetConfirmsByNonce",
      requestType = com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceRequest.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceRequest,
      com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceResponse> getValsetConfirmsByNonceMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceRequest, com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceResponse> getValsetConfirmsByNonceMethod;
    if ((getValsetConfirmsByNonceMethod = QueryGrpc.getValsetConfirmsByNonceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValsetConfirmsByNonceMethod = QueryGrpc.getValsetConfirmsByNonceMethod) == null) {
          QueryGrpc.getValsetConfirmsByNonceMethod = getValsetConfirmsByNonceMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceRequest, com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetConfirmsByNonce"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValsetConfirmsByNonce"))
              .build();
        }
      }
    }
    return getValsetConfirmsByNonceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsRequest,
      com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsResponse> getLastValsetRequestsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastValsetRequests",
      requestType = com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsRequest.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsRequest,
      com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsResponse> getLastValsetRequestsMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsRequest, com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsResponse> getLastValsetRequestsMethod;
    if ((getLastValsetRequestsMethod = QueryGrpc.getLastValsetRequestsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastValsetRequestsMethod = QueryGrpc.getLastValsetRequestsMethod) == null) {
          QueryGrpc.getLastValsetRequestsMethod = getLastValsetRequestsMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsRequest, com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastValsetRequests"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastValsetRequests"))
              .build();
        }
      }
    }
    return getLastValsetRequestsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrRequest,
      com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrResponse> getLastPendingValsetRequestByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastPendingValsetRequestByAddr",
      requestType = com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrRequest.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrRequest,
      com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrResponse> getLastPendingValsetRequestByAddrMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrRequest, com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrResponse> getLastPendingValsetRequestByAddrMethod;
    if ((getLastPendingValsetRequestByAddrMethod = QueryGrpc.getLastPendingValsetRequestByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastPendingValsetRequestByAddrMethod = QueryGrpc.getLastPendingValsetRequestByAddrMethod) == null) {
          QueryGrpc.getLastPendingValsetRequestByAddrMethod = getLastPendingValsetRequestByAddrMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrRequest, com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastPendingValsetRequestByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastPendingValsetRequestByAddr"))
              .build();
        }
      }
    }
    return getLastPendingValsetRequestByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryLastEventByAddrRequest,
      com.injective.peggy.v1.QueryProto.QueryLastEventByAddrResponse> getLastEventByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastEventByAddr",
      requestType = com.injective.peggy.v1.QueryProto.QueryLastEventByAddrRequest.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryLastEventByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryLastEventByAddrRequest,
      com.injective.peggy.v1.QueryProto.QueryLastEventByAddrResponse> getLastEventByAddrMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryLastEventByAddrRequest, com.injective.peggy.v1.QueryProto.QueryLastEventByAddrResponse> getLastEventByAddrMethod;
    if ((getLastEventByAddrMethod = QueryGrpc.getLastEventByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastEventByAddrMethod = QueryGrpc.getLastEventByAddrMethod) == null) {
          QueryGrpc.getLastEventByAddrMethod = getLastEventByAddrMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryLastEventByAddrRequest, com.injective.peggy.v1.QueryProto.QueryLastEventByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastEventByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryLastEventByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryLastEventByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastEventByAddr"))
              .build();
        }
      }
    }
    return getLastEventByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryPendingSendToEth,
      com.injective.peggy.v1.QueryProto.QueryPendingSendToEthResponse> getGetPendingSendToEthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPendingSendToEth",
      requestType = com.injective.peggy.v1.QueryProto.QueryPendingSendToEth.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryPendingSendToEthResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryPendingSendToEth,
      com.injective.peggy.v1.QueryProto.QueryPendingSendToEthResponse> getGetPendingSendToEthMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryPendingSendToEth, com.injective.peggy.v1.QueryProto.QueryPendingSendToEthResponse> getGetPendingSendToEthMethod;
    if ((getGetPendingSendToEthMethod = QueryGrpc.getGetPendingSendToEthMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetPendingSendToEthMethod = QueryGrpc.getGetPendingSendToEthMethod) == null) {
          QueryGrpc.getGetPendingSendToEthMethod = getGetPendingSendToEthMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryPendingSendToEth, com.injective.peggy.v1.QueryProto.QueryPendingSendToEthResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPendingSendToEth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryPendingSendToEth.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryPendingSendToEthResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetPendingSendToEth"))
              .build();
        }
      }
    }
    return getGetPendingSendToEthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryBatchFeeRequest,
      com.injective.peggy.v1.QueryProto.QueryBatchFeeResponse> getBatchFeesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchFees",
      requestType = com.injective.peggy.v1.QueryProto.QueryBatchFeeRequest.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryBatchFeeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryBatchFeeRequest,
      com.injective.peggy.v1.QueryProto.QueryBatchFeeResponse> getBatchFeesMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryBatchFeeRequest, com.injective.peggy.v1.QueryProto.QueryBatchFeeResponse> getBatchFeesMethod;
    if ((getBatchFeesMethod = QueryGrpc.getBatchFeesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchFeesMethod = QueryGrpc.getBatchFeesMethod) == null) {
          QueryGrpc.getBatchFeesMethod = getBatchFeesMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryBatchFeeRequest, com.injective.peggy.v1.QueryProto.QueryBatchFeeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchFees"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryBatchFeeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryBatchFeeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchFees"))
              .build();
        }
      }
    }
    return getBatchFeesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesRequest,
      com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesResponse> getOutgoingTxBatchesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OutgoingTxBatches",
      requestType = com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesRequest.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesRequest,
      com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesResponse> getOutgoingTxBatchesMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesRequest, com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesResponse> getOutgoingTxBatchesMethod;
    if ((getOutgoingTxBatchesMethod = QueryGrpc.getOutgoingTxBatchesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOutgoingTxBatchesMethod = QueryGrpc.getOutgoingTxBatchesMethod) == null) {
          QueryGrpc.getOutgoingTxBatchesMethod = getOutgoingTxBatchesMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesRequest, com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OutgoingTxBatches"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OutgoingTxBatches"))
              .build();
        }
      }
    }
    return getOutgoingTxBatchesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrRequest,
      com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrResponse> getLastPendingBatchRequestByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastPendingBatchRequestByAddr",
      requestType = com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrRequest.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrRequest,
      com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrResponse> getLastPendingBatchRequestByAddrMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrRequest, com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrResponse> getLastPendingBatchRequestByAddrMethod;
    if ((getLastPendingBatchRequestByAddrMethod = QueryGrpc.getLastPendingBatchRequestByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastPendingBatchRequestByAddrMethod = QueryGrpc.getLastPendingBatchRequestByAddrMethod) == null) {
          QueryGrpc.getLastPendingBatchRequestByAddrMethod = getLastPendingBatchRequestByAddrMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrRequest, com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastPendingBatchRequestByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastPendingBatchRequestByAddr"))
              .build();
        }
      }
    }
    return getLastPendingBatchRequestByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceRequest,
      com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceResponse> getBatchRequestByNonceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchRequestByNonce",
      requestType = com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceRequest.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceRequest,
      com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceResponse> getBatchRequestByNonceMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceRequest, com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceResponse> getBatchRequestByNonceMethod;
    if ((getBatchRequestByNonceMethod = QueryGrpc.getBatchRequestByNonceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchRequestByNonceMethod = QueryGrpc.getBatchRequestByNonceMethod) == null) {
          QueryGrpc.getBatchRequestByNonceMethod = getBatchRequestByNonceMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceRequest, com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchRequestByNonce"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchRequestByNonce"))
              .build();
        }
      }
    }
    return getBatchRequestByNonceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryBatchConfirmsRequest,
      com.injective.peggy.v1.QueryProto.QueryBatchConfirmsResponse> getBatchConfirmsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchConfirms",
      requestType = com.injective.peggy.v1.QueryProto.QueryBatchConfirmsRequest.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryBatchConfirmsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryBatchConfirmsRequest,
      com.injective.peggy.v1.QueryProto.QueryBatchConfirmsResponse> getBatchConfirmsMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryBatchConfirmsRequest, com.injective.peggy.v1.QueryProto.QueryBatchConfirmsResponse> getBatchConfirmsMethod;
    if ((getBatchConfirmsMethod = QueryGrpc.getBatchConfirmsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchConfirmsMethod = QueryGrpc.getBatchConfirmsMethod) == null) {
          QueryGrpc.getBatchConfirmsMethod = getBatchConfirmsMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryBatchConfirmsRequest, com.injective.peggy.v1.QueryProto.QueryBatchConfirmsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchConfirms"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryBatchConfirmsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryBatchConfirmsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchConfirms"))
              .build();
        }
      }
    }
    return getBatchConfirmsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryERC20ToDenomRequest,
      com.injective.peggy.v1.QueryProto.QueryERC20ToDenomResponse> getERC20ToDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ERC20ToDenom",
      requestType = com.injective.peggy.v1.QueryProto.QueryERC20ToDenomRequest.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryERC20ToDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryERC20ToDenomRequest,
      com.injective.peggy.v1.QueryProto.QueryERC20ToDenomResponse> getERC20ToDenomMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryERC20ToDenomRequest, com.injective.peggy.v1.QueryProto.QueryERC20ToDenomResponse> getERC20ToDenomMethod;
    if ((getERC20ToDenomMethod = QueryGrpc.getERC20ToDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getERC20ToDenomMethod = QueryGrpc.getERC20ToDenomMethod) == null) {
          QueryGrpc.getERC20ToDenomMethod = getERC20ToDenomMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryERC20ToDenomRequest, com.injective.peggy.v1.QueryProto.QueryERC20ToDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ERC20ToDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryERC20ToDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryERC20ToDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ERC20ToDenom"))
              .build();
        }
      }
    }
    return getERC20ToDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryDenomToERC20Request,
      com.injective.peggy.v1.QueryProto.QueryDenomToERC20Response> getDenomToERC20Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomToERC20",
      requestType = com.injective.peggy.v1.QueryProto.QueryDenomToERC20Request.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryDenomToERC20Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryDenomToERC20Request,
      com.injective.peggy.v1.QueryProto.QueryDenomToERC20Response> getDenomToERC20Method() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryDenomToERC20Request, com.injective.peggy.v1.QueryProto.QueryDenomToERC20Response> getDenomToERC20Method;
    if ((getDenomToERC20Method = QueryGrpc.getDenomToERC20Method) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomToERC20Method = QueryGrpc.getDenomToERC20Method) == null) {
          QueryGrpc.getDenomToERC20Method = getDenomToERC20Method =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryDenomToERC20Request, com.injective.peggy.v1.QueryProto.QueryDenomToERC20Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomToERC20"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryDenomToERC20Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryDenomToERC20Response.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomToERC20"))
              .build();
        }
      }
    }
    return getDenomToERC20Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddress,
      com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddressResponse> getGetDelegateKeyByValidatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDelegateKeyByValidator",
      requestType = com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddress.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddress,
      com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddressResponse> getGetDelegateKeyByValidatorMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddress, com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddressResponse> getGetDelegateKeyByValidatorMethod;
    if ((getGetDelegateKeyByValidatorMethod = QueryGrpc.getGetDelegateKeyByValidatorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetDelegateKeyByValidatorMethod = QueryGrpc.getGetDelegateKeyByValidatorMethod) == null) {
          QueryGrpc.getGetDelegateKeyByValidatorMethod = getGetDelegateKeyByValidatorMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddress, com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDelegateKeyByValidator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddress.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetDelegateKeyByValidator"))
              .build();
        }
      }
    }
    return getGetDelegateKeyByValidatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddress,
      com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddressResponse> getGetDelegateKeyByEthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDelegateKeyByEth",
      requestType = com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddress.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddress,
      com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddressResponse> getGetDelegateKeyByEthMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddress, com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddressResponse> getGetDelegateKeyByEthMethod;
    if ((getGetDelegateKeyByEthMethod = QueryGrpc.getGetDelegateKeyByEthMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetDelegateKeyByEthMethod = QueryGrpc.getGetDelegateKeyByEthMethod) == null) {
          QueryGrpc.getGetDelegateKeyByEthMethod = getGetDelegateKeyByEthMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddress, com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDelegateKeyByEth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddress.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetDelegateKeyByEth"))
              .build();
        }
      }
    }
    return getGetDelegateKeyByEthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddress,
      com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddressResponse> getGetDelegateKeyByOrchestratorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDelegateKeyByOrchestrator",
      requestType = com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddress.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddress,
      com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddressResponse> getGetDelegateKeyByOrchestratorMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddress, com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddressResponse> getGetDelegateKeyByOrchestratorMethod;
    if ((getGetDelegateKeyByOrchestratorMethod = QueryGrpc.getGetDelegateKeyByOrchestratorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetDelegateKeyByOrchestratorMethod = QueryGrpc.getGetDelegateKeyByOrchestratorMethod) == null) {
          QueryGrpc.getGetDelegateKeyByOrchestratorMethod = getGetDelegateKeyByOrchestratorMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddress, com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDelegateKeyByOrchestrator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddress.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetDelegateKeyByOrchestrator"))
              .build();
        }
      }
    }
    return getGetDelegateKeyByOrchestratorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryModuleStateRequest,
      com.injective.peggy.v1.QueryProto.QueryModuleStateResponse> getPeggyModuleStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PeggyModuleState",
      requestType = com.injective.peggy.v1.QueryProto.QueryModuleStateRequest.class,
      responseType = com.injective.peggy.v1.QueryProto.QueryModuleStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryModuleStateRequest,
      com.injective.peggy.v1.QueryProto.QueryModuleStateResponse> getPeggyModuleStateMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.QueryModuleStateRequest, com.injective.peggy.v1.QueryProto.QueryModuleStateResponse> getPeggyModuleStateMethod;
    if ((getPeggyModuleStateMethod = QueryGrpc.getPeggyModuleStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPeggyModuleStateMethod = QueryGrpc.getPeggyModuleStateMethod) == null) {
          QueryGrpc.getPeggyModuleStateMethod = getPeggyModuleStateMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.QueryModuleStateRequest, com.injective.peggy.v1.QueryProto.QueryModuleStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PeggyModuleState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryModuleStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.QueryModuleStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PeggyModuleState"))
              .build();
        }
      }
    }
    return getPeggyModuleStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.MissingNoncesRequest,
      com.injective.peggy.v1.QueryProto.MissingNoncesResponse> getMissingPeggoNoncesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MissingPeggoNonces",
      requestType = com.injective.peggy.v1.QueryProto.MissingNoncesRequest.class,
      responseType = com.injective.peggy.v1.QueryProto.MissingNoncesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.MissingNoncesRequest,
      com.injective.peggy.v1.QueryProto.MissingNoncesResponse> getMissingPeggoNoncesMethod() {
    io.grpc.MethodDescriptor<com.injective.peggy.v1.QueryProto.MissingNoncesRequest, com.injective.peggy.v1.QueryProto.MissingNoncesResponse> getMissingPeggoNoncesMethod;
    if ((getMissingPeggoNoncesMethod = QueryGrpc.getMissingPeggoNoncesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getMissingPeggoNoncesMethod = QueryGrpc.getMissingPeggoNoncesMethod) == null) {
          QueryGrpc.getMissingPeggoNoncesMethod = getMissingPeggoNoncesMethod =
              io.grpc.MethodDescriptor.<com.injective.peggy.v1.QueryProto.MissingNoncesRequest, com.injective.peggy.v1.QueryProto.MissingNoncesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MissingPeggoNonces"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.MissingNoncesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.peggy.v1.QueryProto.MissingNoncesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("MissingPeggoNonces"))
              .build();
        }
      }
    }
    return getMissingPeggoNoncesMethod;
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
   * Query defines the gRPC querier service
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Deployments queries deployments
     * </pre>
     */
    default void params(com.injective.peggy.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * valset
     * </pre>
     */
    default void currentValset(com.injective.peggy.v1.QueryProto.QueryCurrentValsetRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryCurrentValsetResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCurrentValsetMethod(), responseObserver);
    }

    /**
     */
    default void valsetRequest(com.injective.peggy.v1.QueryProto.QueryValsetRequestRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryValsetRequestResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValsetRequestMethod(), responseObserver);
    }

    /**
     */
    default void valsetConfirm(com.injective.peggy.v1.QueryProto.QueryValsetConfirmRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryValsetConfirmResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValsetConfirmMethod(), responseObserver);
    }

    /**
     */
    default void valsetConfirmsByNonce(com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValsetConfirmsByNonceMethod(), responseObserver);
    }

    /**
     */
    default void lastValsetRequests(com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastValsetRequestsMethod(), responseObserver);
    }

    /**
     */
    default void lastPendingValsetRequestByAddr(com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastPendingValsetRequestByAddrMethod(), responseObserver);
    }

    /**
     * <pre>
     * claim
     * </pre>
     */
    default void lastEventByAddr(com.injective.peggy.v1.QueryProto.QueryLastEventByAddrRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryLastEventByAddrResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastEventByAddrMethod(), responseObserver);
    }

    /**
     * <pre>
     * batch
     * </pre>
     */
    default void getPendingSendToEth(com.injective.peggy.v1.QueryProto.QueryPendingSendToEth request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryPendingSendToEthResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPendingSendToEthMethod(), responseObserver);
    }

    /**
     */
    default void batchFees(com.injective.peggy.v1.QueryProto.QueryBatchFeeRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryBatchFeeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchFeesMethod(), responseObserver);
    }

    /**
     */
    default void outgoingTxBatches(com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOutgoingTxBatchesMethod(), responseObserver);
    }

    /**
     */
    default void lastPendingBatchRequestByAddr(com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLastPendingBatchRequestByAddrMethod(), responseObserver);
    }

    /**
     */
    default void batchRequestByNonce(com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchRequestByNonceMethod(), responseObserver);
    }

    /**
     */
    default void batchConfirms(com.injective.peggy.v1.QueryProto.QueryBatchConfirmsRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryBatchConfirmsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchConfirmsMethod(), responseObserver);
    }

    /**
     */
    default void eRC20ToDenom(com.injective.peggy.v1.QueryProto.QueryERC20ToDenomRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryERC20ToDenomResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getERC20ToDenomMethod(), responseObserver);
    }

    /**
     */
    default void denomToERC20(com.injective.peggy.v1.QueryProto.QueryDenomToERC20Request request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryDenomToERC20Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDenomToERC20Method(), responseObserver);
    }

    /**
     */
    default void getDelegateKeyByValidator(com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddress request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddressResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDelegateKeyByValidatorMethod(), responseObserver);
    }

    /**
     */
    default void getDelegateKeyByEth(com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddress request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddressResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDelegateKeyByEthMethod(), responseObserver);
    }

    /**
     */
    default void getDelegateKeyByOrchestrator(com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddress request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddressResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDelegateKeyByOrchestratorMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire peggy module's state
     * </pre>
     */
    default void peggyModuleState(com.injective.peggy.v1.QueryProto.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryModuleStateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPeggyModuleStateMethod(), responseObserver);
    }

    /**
     */
    default void missingPeggoNonces(com.injective.peggy.v1.QueryProto.MissingNoncesRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.MissingNoncesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMissingPeggoNoncesMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service
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
   * Query defines the gRPC querier service
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
     * Deployments queries deployments
     * </pre>
     */
    public void params(com.injective.peggy.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * valset
     * </pre>
     */
    public void currentValset(com.injective.peggy.v1.QueryProto.QueryCurrentValsetRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryCurrentValsetResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCurrentValsetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void valsetRequest(com.injective.peggy.v1.QueryProto.QueryValsetRequestRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryValsetRequestResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValsetRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void valsetConfirm(com.injective.peggy.v1.QueryProto.QueryValsetConfirmRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryValsetConfirmResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValsetConfirmMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void valsetConfirmsByNonce(com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValsetConfirmsByNonceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastValsetRequests(com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastValsetRequestsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastPendingValsetRequestByAddr(com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastPendingValsetRequestByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * claim
     * </pre>
     */
    public void lastEventByAddr(com.injective.peggy.v1.QueryProto.QueryLastEventByAddrRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryLastEventByAddrResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastEventByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * batch
     * </pre>
     */
    public void getPendingSendToEth(com.injective.peggy.v1.QueryProto.QueryPendingSendToEth request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryPendingSendToEthResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPendingSendToEthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchFees(com.injective.peggy.v1.QueryProto.QueryBatchFeeRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryBatchFeeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchFeesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void outgoingTxBatches(com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOutgoingTxBatchesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastPendingBatchRequestByAddr(com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLastPendingBatchRequestByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchRequestByNonce(com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchRequestByNonceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchConfirms(com.injective.peggy.v1.QueryProto.QueryBatchConfirmsRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryBatchConfirmsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchConfirmsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void eRC20ToDenom(com.injective.peggy.v1.QueryProto.QueryERC20ToDenomRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryERC20ToDenomResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getERC20ToDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void denomToERC20(com.injective.peggy.v1.QueryProto.QueryDenomToERC20Request request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryDenomToERC20Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDenomToERC20Method(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDelegateKeyByValidator(com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddress request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddressResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetDelegateKeyByValidatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDelegateKeyByEth(com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddress request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddressResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetDelegateKeyByEthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDelegateKeyByOrchestrator(com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddress request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddressResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetDelegateKeyByOrchestratorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire peggy module's state
     * </pre>
     */
    public void peggyModuleState(com.injective.peggy.v1.QueryProto.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryModuleStateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPeggyModuleStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void missingPeggoNonces(com.injective.peggy.v1.QueryProto.MissingNoncesRequest request,
        io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.MissingNoncesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMissingPeggoNoncesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service
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
     * Deployments queries deployments
     * </pre>
     */
    public com.injective.peggy.v1.QueryProto.QueryParamsResponse params(com.injective.peggy.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * valset
     * </pre>
     */
    public com.injective.peggy.v1.QueryProto.QueryCurrentValsetResponse currentValset(com.injective.peggy.v1.QueryProto.QueryCurrentValsetRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCurrentValsetMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.QueryProto.QueryValsetRequestResponse valsetRequest(com.injective.peggy.v1.QueryProto.QueryValsetRequestRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValsetRequestMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.QueryProto.QueryValsetConfirmResponse valsetConfirm(com.injective.peggy.v1.QueryProto.QueryValsetConfirmRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValsetConfirmMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceResponse valsetConfirmsByNonce(com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValsetConfirmsByNonceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsResponse lastValsetRequests(com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastValsetRequestsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrResponse lastPendingValsetRequestByAddr(com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastPendingValsetRequestByAddrMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * claim
     * </pre>
     */
    public com.injective.peggy.v1.QueryProto.QueryLastEventByAddrResponse lastEventByAddr(com.injective.peggy.v1.QueryProto.QueryLastEventByAddrRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastEventByAddrMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * batch
     * </pre>
     */
    public com.injective.peggy.v1.QueryProto.QueryPendingSendToEthResponse getPendingSendToEth(com.injective.peggy.v1.QueryProto.QueryPendingSendToEth request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPendingSendToEthMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.QueryProto.QueryBatchFeeResponse batchFees(com.injective.peggy.v1.QueryProto.QueryBatchFeeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchFeesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesResponse outgoingTxBatches(com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOutgoingTxBatchesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrResponse lastPendingBatchRequestByAddr(com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLastPendingBatchRequestByAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceResponse batchRequestByNonce(com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchRequestByNonceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.QueryProto.QueryBatchConfirmsResponse batchConfirms(com.injective.peggy.v1.QueryProto.QueryBatchConfirmsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchConfirmsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.QueryProto.QueryERC20ToDenomResponse eRC20ToDenom(com.injective.peggy.v1.QueryProto.QueryERC20ToDenomRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getERC20ToDenomMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.QueryProto.QueryDenomToERC20Response denomToERC20(com.injective.peggy.v1.QueryProto.QueryDenomToERC20Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDenomToERC20Method(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddressResponse getDelegateKeyByValidator(com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddress request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetDelegateKeyByValidatorMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddressResponse getDelegateKeyByEth(com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddress request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetDelegateKeyByEthMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddressResponse getDelegateKeyByOrchestrator(com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddress request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetDelegateKeyByOrchestratorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the entire peggy module's state
     * </pre>
     */
    public com.injective.peggy.v1.QueryProto.QueryModuleStateResponse peggyModuleState(com.injective.peggy.v1.QueryProto.QueryModuleStateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPeggyModuleStateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.peggy.v1.QueryProto.MissingNoncesResponse missingPeggoNonces(com.injective.peggy.v1.QueryProto.MissingNoncesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMissingPeggoNoncesMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service
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
     * Deployments queries deployments
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryParamsResponse> params(
        com.injective.peggy.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * valset
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryCurrentValsetResponse> currentValset(
        com.injective.peggy.v1.QueryProto.QueryCurrentValsetRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCurrentValsetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryValsetRequestResponse> valsetRequest(
        com.injective.peggy.v1.QueryProto.QueryValsetRequestRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValsetRequestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryValsetConfirmResponse> valsetConfirm(
        com.injective.peggy.v1.QueryProto.QueryValsetConfirmRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValsetConfirmMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceResponse> valsetConfirmsByNonce(
        com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValsetConfirmsByNonceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsResponse> lastValsetRequests(
        com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastValsetRequestsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrResponse> lastPendingValsetRequestByAddr(
        com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastPendingValsetRequestByAddrMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * claim
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryLastEventByAddrResponse> lastEventByAddr(
        com.injective.peggy.v1.QueryProto.QueryLastEventByAddrRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastEventByAddrMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * batch
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryPendingSendToEthResponse> getPendingSendToEth(
        com.injective.peggy.v1.QueryProto.QueryPendingSendToEth request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPendingSendToEthMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryBatchFeeResponse> batchFees(
        com.injective.peggy.v1.QueryProto.QueryBatchFeeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchFeesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesResponse> outgoingTxBatches(
        com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOutgoingTxBatchesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrResponse> lastPendingBatchRequestByAddr(
        com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLastPendingBatchRequestByAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceResponse> batchRequestByNonce(
        com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchRequestByNonceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryBatchConfirmsResponse> batchConfirms(
        com.injective.peggy.v1.QueryProto.QueryBatchConfirmsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchConfirmsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryERC20ToDenomResponse> eRC20ToDenom(
        com.injective.peggy.v1.QueryProto.QueryERC20ToDenomRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getERC20ToDenomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryDenomToERC20Response> denomToERC20(
        com.injective.peggy.v1.QueryProto.QueryDenomToERC20Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDenomToERC20Method(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddressResponse> getDelegateKeyByValidator(
        com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddress request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetDelegateKeyByValidatorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddressResponse> getDelegateKeyByEth(
        com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddress request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetDelegateKeyByEthMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddressResponse> getDelegateKeyByOrchestrator(
        com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddress request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetDelegateKeyByOrchestratorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the entire peggy module's state
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.QueryModuleStateResponse> peggyModuleState(
        com.injective.peggy.v1.QueryProto.QueryModuleStateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPeggyModuleStateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.peggy.v1.QueryProto.MissingNoncesResponse> missingPeggoNonces(
        com.injective.peggy.v1.QueryProto.MissingNoncesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMissingPeggoNoncesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_CURRENT_VALSET = 1;
  private static final int METHODID_VALSET_REQUEST = 2;
  private static final int METHODID_VALSET_CONFIRM = 3;
  private static final int METHODID_VALSET_CONFIRMS_BY_NONCE = 4;
  private static final int METHODID_LAST_VALSET_REQUESTS = 5;
  private static final int METHODID_LAST_PENDING_VALSET_REQUEST_BY_ADDR = 6;
  private static final int METHODID_LAST_EVENT_BY_ADDR = 7;
  private static final int METHODID_GET_PENDING_SEND_TO_ETH = 8;
  private static final int METHODID_BATCH_FEES = 9;
  private static final int METHODID_OUTGOING_TX_BATCHES = 10;
  private static final int METHODID_LAST_PENDING_BATCH_REQUEST_BY_ADDR = 11;
  private static final int METHODID_BATCH_REQUEST_BY_NONCE = 12;
  private static final int METHODID_BATCH_CONFIRMS = 13;
  private static final int METHODID_ERC20TO_DENOM = 14;
  private static final int METHODID_DENOM_TO_ERC20 = 15;
  private static final int METHODID_GET_DELEGATE_KEY_BY_VALIDATOR = 16;
  private static final int METHODID_GET_DELEGATE_KEY_BY_ETH = 17;
  private static final int METHODID_GET_DELEGATE_KEY_BY_ORCHESTRATOR = 18;
  private static final int METHODID_PEGGY_MODULE_STATE = 19;
  private static final int METHODID_MISSING_PEGGO_NONCES = 20;

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
          serviceImpl.params((com.injective.peggy.v1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_CURRENT_VALSET:
          serviceImpl.currentValset((com.injective.peggy.v1.QueryProto.QueryCurrentValsetRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryCurrentValsetResponse>) responseObserver);
          break;
        case METHODID_VALSET_REQUEST:
          serviceImpl.valsetRequest((com.injective.peggy.v1.QueryProto.QueryValsetRequestRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryValsetRequestResponse>) responseObserver);
          break;
        case METHODID_VALSET_CONFIRM:
          serviceImpl.valsetConfirm((com.injective.peggy.v1.QueryProto.QueryValsetConfirmRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryValsetConfirmResponse>) responseObserver);
          break;
        case METHODID_VALSET_CONFIRMS_BY_NONCE:
          serviceImpl.valsetConfirmsByNonce((com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceResponse>) responseObserver);
          break;
        case METHODID_LAST_VALSET_REQUESTS:
          serviceImpl.lastValsetRequests((com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsResponse>) responseObserver);
          break;
        case METHODID_LAST_PENDING_VALSET_REQUEST_BY_ADDR:
          serviceImpl.lastPendingValsetRequestByAddr((com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrResponse>) responseObserver);
          break;
        case METHODID_LAST_EVENT_BY_ADDR:
          serviceImpl.lastEventByAddr((com.injective.peggy.v1.QueryProto.QueryLastEventByAddrRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryLastEventByAddrResponse>) responseObserver);
          break;
        case METHODID_GET_PENDING_SEND_TO_ETH:
          serviceImpl.getPendingSendToEth((com.injective.peggy.v1.QueryProto.QueryPendingSendToEth) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryPendingSendToEthResponse>) responseObserver);
          break;
        case METHODID_BATCH_FEES:
          serviceImpl.batchFees((com.injective.peggy.v1.QueryProto.QueryBatchFeeRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryBatchFeeResponse>) responseObserver);
          break;
        case METHODID_OUTGOING_TX_BATCHES:
          serviceImpl.outgoingTxBatches((com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesResponse>) responseObserver);
          break;
        case METHODID_LAST_PENDING_BATCH_REQUEST_BY_ADDR:
          serviceImpl.lastPendingBatchRequestByAddr((com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrResponse>) responseObserver);
          break;
        case METHODID_BATCH_REQUEST_BY_NONCE:
          serviceImpl.batchRequestByNonce((com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceResponse>) responseObserver);
          break;
        case METHODID_BATCH_CONFIRMS:
          serviceImpl.batchConfirms((com.injective.peggy.v1.QueryProto.QueryBatchConfirmsRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryBatchConfirmsResponse>) responseObserver);
          break;
        case METHODID_ERC20TO_DENOM:
          serviceImpl.eRC20ToDenom((com.injective.peggy.v1.QueryProto.QueryERC20ToDenomRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryERC20ToDenomResponse>) responseObserver);
          break;
        case METHODID_DENOM_TO_ERC20:
          serviceImpl.denomToERC20((com.injective.peggy.v1.QueryProto.QueryDenomToERC20Request) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryDenomToERC20Response>) responseObserver);
          break;
        case METHODID_GET_DELEGATE_KEY_BY_VALIDATOR:
          serviceImpl.getDelegateKeyByValidator((com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddress) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddressResponse>) responseObserver);
          break;
        case METHODID_GET_DELEGATE_KEY_BY_ETH:
          serviceImpl.getDelegateKeyByEth((com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddress) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddressResponse>) responseObserver);
          break;
        case METHODID_GET_DELEGATE_KEY_BY_ORCHESTRATOR:
          serviceImpl.getDelegateKeyByOrchestrator((com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddress) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddressResponse>) responseObserver);
          break;
        case METHODID_PEGGY_MODULE_STATE:
          serviceImpl.peggyModuleState((com.injective.peggy.v1.QueryProto.QueryModuleStateRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.QueryModuleStateResponse>) responseObserver);
          break;
        case METHODID_MISSING_PEGGO_NONCES:
          serviceImpl.missingPeggoNonces((com.injective.peggy.v1.QueryProto.MissingNoncesRequest) request,
              (io.grpc.stub.StreamObserver<com.injective.peggy.v1.QueryProto.MissingNoncesResponse>) responseObserver);
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
              com.injective.peggy.v1.QueryProto.QueryParamsRequest,
              com.injective.peggy.v1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getCurrentValsetMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryCurrentValsetRequest,
              com.injective.peggy.v1.QueryProto.QueryCurrentValsetResponse>(
                service, METHODID_CURRENT_VALSET)))
        .addMethod(
          getValsetRequestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryValsetRequestRequest,
              com.injective.peggy.v1.QueryProto.QueryValsetRequestResponse>(
                service, METHODID_VALSET_REQUEST)))
        .addMethod(
          getValsetConfirmMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryValsetConfirmRequest,
              com.injective.peggy.v1.QueryProto.QueryValsetConfirmResponse>(
                service, METHODID_VALSET_CONFIRM)))
        .addMethod(
          getValsetConfirmsByNonceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceRequest,
              com.injective.peggy.v1.QueryProto.QueryValsetConfirmsByNonceResponse>(
                service, METHODID_VALSET_CONFIRMS_BY_NONCE)))
        .addMethod(
          getLastValsetRequestsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsRequest,
              com.injective.peggy.v1.QueryProto.QueryLastValsetRequestsResponse>(
                service, METHODID_LAST_VALSET_REQUESTS)))
        .addMethod(
          getLastPendingValsetRequestByAddrMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrRequest,
              com.injective.peggy.v1.QueryProto.QueryLastPendingValsetRequestByAddrResponse>(
                service, METHODID_LAST_PENDING_VALSET_REQUEST_BY_ADDR)))
        .addMethod(
          getLastEventByAddrMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryLastEventByAddrRequest,
              com.injective.peggy.v1.QueryProto.QueryLastEventByAddrResponse>(
                service, METHODID_LAST_EVENT_BY_ADDR)))
        .addMethod(
          getGetPendingSendToEthMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryPendingSendToEth,
              com.injective.peggy.v1.QueryProto.QueryPendingSendToEthResponse>(
                service, METHODID_GET_PENDING_SEND_TO_ETH)))
        .addMethod(
          getBatchFeesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryBatchFeeRequest,
              com.injective.peggy.v1.QueryProto.QueryBatchFeeResponse>(
                service, METHODID_BATCH_FEES)))
        .addMethod(
          getOutgoingTxBatchesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesRequest,
              com.injective.peggy.v1.QueryProto.QueryOutgoingTxBatchesResponse>(
                service, METHODID_OUTGOING_TX_BATCHES)))
        .addMethod(
          getLastPendingBatchRequestByAddrMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrRequest,
              com.injective.peggy.v1.QueryProto.QueryLastPendingBatchRequestByAddrResponse>(
                service, METHODID_LAST_PENDING_BATCH_REQUEST_BY_ADDR)))
        .addMethod(
          getBatchRequestByNonceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceRequest,
              com.injective.peggy.v1.QueryProto.QueryBatchRequestByNonceResponse>(
                service, METHODID_BATCH_REQUEST_BY_NONCE)))
        .addMethod(
          getBatchConfirmsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryBatchConfirmsRequest,
              com.injective.peggy.v1.QueryProto.QueryBatchConfirmsResponse>(
                service, METHODID_BATCH_CONFIRMS)))
        .addMethod(
          getERC20ToDenomMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryERC20ToDenomRequest,
              com.injective.peggy.v1.QueryProto.QueryERC20ToDenomResponse>(
                service, METHODID_ERC20TO_DENOM)))
        .addMethod(
          getDenomToERC20Method(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryDenomToERC20Request,
              com.injective.peggy.v1.QueryProto.QueryDenomToERC20Response>(
                service, METHODID_DENOM_TO_ERC20)))
        .addMethod(
          getGetDelegateKeyByValidatorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddress,
              com.injective.peggy.v1.QueryProto.QueryDelegateKeysByValidatorAddressResponse>(
                service, METHODID_GET_DELEGATE_KEY_BY_VALIDATOR)))
        .addMethod(
          getGetDelegateKeyByEthMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddress,
              com.injective.peggy.v1.QueryProto.QueryDelegateKeysByEthAddressResponse>(
                service, METHODID_GET_DELEGATE_KEY_BY_ETH)))
        .addMethod(
          getGetDelegateKeyByOrchestratorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddress,
              com.injective.peggy.v1.QueryProto.QueryDelegateKeysByOrchestratorAddressResponse>(
                service, METHODID_GET_DELEGATE_KEY_BY_ORCHESTRATOR)))
        .addMethod(
          getPeggyModuleStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.QueryModuleStateRequest,
              com.injective.peggy.v1.QueryProto.QueryModuleStateResponse>(
                service, METHODID_PEGGY_MODULE_STATE)))
        .addMethod(
          getMissingPeggoNoncesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.peggy.v1.QueryProto.MissingNoncesRequest,
              com.injective.peggy.v1.QueryProto.MissingNoncesResponse>(
                service, METHODID_MISSING_PEGGO_NONCES)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.injective.peggy.v1.QueryProto.getDescriptor();
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
              .addMethod(getCurrentValsetMethod())
              .addMethod(getValsetRequestMethod())
              .addMethod(getValsetConfirmMethod())
              .addMethod(getValsetConfirmsByNonceMethod())
              .addMethod(getLastValsetRequestsMethod())
              .addMethod(getLastPendingValsetRequestByAddrMethod())
              .addMethod(getLastEventByAddrMethod())
              .addMethod(getGetPendingSendToEthMethod())
              .addMethod(getBatchFeesMethod())
              .addMethod(getOutgoingTxBatchesMethod())
              .addMethod(getLastPendingBatchRequestByAddrMethod())
              .addMethod(getBatchRequestByNonceMethod())
              .addMethod(getBatchConfirmsMethod())
              .addMethod(getERC20ToDenomMethod())
              .addMethod(getDenomToERC20Method())
              .addMethod(getGetDelegateKeyByValidatorMethod())
              .addMethod(getGetDelegateKeyByEthMethod())
              .addMethod(getGetDelegateKeyByOrchestratorMethod())
              .addMethod(getPeggyModuleStateMethod())
              .addMethod(getMissingPeggoNoncesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
