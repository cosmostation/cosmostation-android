package gravity.v1;

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
 * Query defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: gravity/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "gravity.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryParamsRequest,
      gravity.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = gravity.v1.QueryOuterClass.QueryParamsRequest.class,
      responseType = gravity.v1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryParamsRequest,
      gravity.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryParamsRequest, gravity.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryParamsRequest, gravity.v1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryCurrentValsetRequest,
      gravity.v1.QueryOuterClass.QueryCurrentValsetResponse> getCurrentValsetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CurrentValset",
      requestType = gravity.v1.QueryOuterClass.QueryCurrentValsetRequest.class,
      responseType = gravity.v1.QueryOuterClass.QueryCurrentValsetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryCurrentValsetRequest,
      gravity.v1.QueryOuterClass.QueryCurrentValsetResponse> getCurrentValsetMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryCurrentValsetRequest, gravity.v1.QueryOuterClass.QueryCurrentValsetResponse> getCurrentValsetMethod;
    if ((getCurrentValsetMethod = QueryGrpc.getCurrentValsetMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCurrentValsetMethod = QueryGrpc.getCurrentValsetMethod) == null) {
          QueryGrpc.getCurrentValsetMethod = getCurrentValsetMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryCurrentValsetRequest, gravity.v1.QueryOuterClass.QueryCurrentValsetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CurrentValset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryCurrentValsetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryCurrentValsetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CurrentValset"))
              .build();
        }
      }
    }
    return getCurrentValsetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryValsetRequestRequest,
      gravity.v1.QueryOuterClass.QueryValsetRequestResponse> getValsetRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetRequest",
      requestType = gravity.v1.QueryOuterClass.QueryValsetRequestRequest.class,
      responseType = gravity.v1.QueryOuterClass.QueryValsetRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryValsetRequestRequest,
      gravity.v1.QueryOuterClass.QueryValsetRequestResponse> getValsetRequestMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryValsetRequestRequest, gravity.v1.QueryOuterClass.QueryValsetRequestResponse> getValsetRequestMethod;
    if ((getValsetRequestMethod = QueryGrpc.getValsetRequestMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValsetRequestMethod = QueryGrpc.getValsetRequestMethod) == null) {
          QueryGrpc.getValsetRequestMethod = getValsetRequestMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryValsetRequestRequest, gravity.v1.QueryOuterClass.QueryValsetRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryValsetRequestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryValsetRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValsetRequest"))
              .build();
        }
      }
    }
    return getValsetRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryValsetConfirmRequest,
      gravity.v1.QueryOuterClass.QueryValsetConfirmResponse> getValsetConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetConfirm",
      requestType = gravity.v1.QueryOuterClass.QueryValsetConfirmRequest.class,
      responseType = gravity.v1.QueryOuterClass.QueryValsetConfirmResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryValsetConfirmRequest,
      gravity.v1.QueryOuterClass.QueryValsetConfirmResponse> getValsetConfirmMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryValsetConfirmRequest, gravity.v1.QueryOuterClass.QueryValsetConfirmResponse> getValsetConfirmMethod;
    if ((getValsetConfirmMethod = QueryGrpc.getValsetConfirmMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValsetConfirmMethod = QueryGrpc.getValsetConfirmMethod) == null) {
          QueryGrpc.getValsetConfirmMethod = getValsetConfirmMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryValsetConfirmRequest, gravity.v1.QueryOuterClass.QueryValsetConfirmResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetConfirm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryValsetConfirmRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryValsetConfirmResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValsetConfirm"))
              .build();
        }
      }
    }
    return getValsetConfirmMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest,
      gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> getValsetConfirmsByNonceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetConfirmsByNonce",
      requestType = gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest.class,
      responseType = gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest,
      gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> getValsetConfirmsByNonceMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest, gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> getValsetConfirmsByNonceMethod;
    if ((getValsetConfirmsByNonceMethod = QueryGrpc.getValsetConfirmsByNonceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValsetConfirmsByNonceMethod = QueryGrpc.getValsetConfirmsByNonceMethod) == null) {
          QueryGrpc.getValsetConfirmsByNonceMethod = getValsetConfirmsByNonceMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest, gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetConfirmsByNonce"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValsetConfirmsByNonce"))
              .build();
        }
      }
    }
    return getValsetConfirmsByNonceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest,
      gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse> getLastValsetRequestsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastValsetRequests",
      requestType = gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest.class,
      responseType = gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest,
      gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse> getLastValsetRequestsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest, gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse> getLastValsetRequestsMethod;
    if ((getLastValsetRequestsMethod = QueryGrpc.getLastValsetRequestsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastValsetRequestsMethod = QueryGrpc.getLastValsetRequestsMethod) == null) {
          QueryGrpc.getLastValsetRequestsMethod = getLastValsetRequestsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest, gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastValsetRequests"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastValsetRequests"))
              .build();
        }
      }
    }
    return getLastValsetRequestsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest,
      gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> getLastPendingValsetRequestByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastPendingValsetRequestByAddr",
      requestType = gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest.class,
      responseType = gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest,
      gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> getLastPendingValsetRequestByAddrMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest, gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> getLastPendingValsetRequestByAddrMethod;
    if ((getLastPendingValsetRequestByAddrMethod = QueryGrpc.getLastPendingValsetRequestByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastPendingValsetRequestByAddrMethod = QueryGrpc.getLastPendingValsetRequestByAddrMethod) == null) {
          QueryGrpc.getLastPendingValsetRequestByAddrMethod = getLastPendingValsetRequestByAddrMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest, gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastPendingValsetRequestByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastPendingValsetRequestByAddr"))
              .build();
        }
      }
    }
    return getLastPendingValsetRequestByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest,
      gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> getLastPendingBatchRequestByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastPendingBatchRequestByAddr",
      requestType = gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest.class,
      responseType = gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest,
      gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> getLastPendingBatchRequestByAddrMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest, gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> getLastPendingBatchRequestByAddrMethod;
    if ((getLastPendingBatchRequestByAddrMethod = QueryGrpc.getLastPendingBatchRequestByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastPendingBatchRequestByAddrMethod = QueryGrpc.getLastPendingBatchRequestByAddrMethod) == null) {
          QueryGrpc.getLastPendingBatchRequestByAddrMethod = getLastPendingBatchRequestByAddrMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest, gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastPendingBatchRequestByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastPendingBatchRequestByAddr"))
              .build();
        }
      }
    }
    return getLastPendingBatchRequestByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrRequest,
      gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrResponse> getLastPendingLogicCallByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastPendingLogicCallByAddr",
      requestType = gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrRequest.class,
      responseType = gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrRequest,
      gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrResponse> getLastPendingLogicCallByAddrMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrRequest, gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrResponse> getLastPendingLogicCallByAddrMethod;
    if ((getLastPendingLogicCallByAddrMethod = QueryGrpc.getLastPendingLogicCallByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastPendingLogicCallByAddrMethod = QueryGrpc.getLastPendingLogicCallByAddrMethod) == null) {
          QueryGrpc.getLastPendingLogicCallByAddrMethod = getLastPendingLogicCallByAddrMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrRequest, gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastPendingLogicCallByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastPendingLogicCallByAddr"))
              .build();
        }
      }
    }
    return getLastPendingLogicCallByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest,
      gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> getLastEventNonceByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastEventNonceByAddr",
      requestType = gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest.class,
      responseType = gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest,
      gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> getLastEventNonceByAddrMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest, gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> getLastEventNonceByAddrMethod;
    if ((getLastEventNonceByAddrMethod = QueryGrpc.getLastEventNonceByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastEventNonceByAddrMethod = QueryGrpc.getLastEventNonceByAddrMethod) == null) {
          QueryGrpc.getLastEventNonceByAddrMethod = getLastEventNonceByAddrMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest, gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastEventNonceByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastEventNonceByAddr"))
              .build();
        }
      }
    }
    return getLastEventNonceByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryBatchFeeRequest,
      gravity.v1.QueryOuterClass.QueryBatchFeeResponse> getBatchFeesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchFees",
      requestType = gravity.v1.QueryOuterClass.QueryBatchFeeRequest.class,
      responseType = gravity.v1.QueryOuterClass.QueryBatchFeeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryBatchFeeRequest,
      gravity.v1.QueryOuterClass.QueryBatchFeeResponse> getBatchFeesMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryBatchFeeRequest, gravity.v1.QueryOuterClass.QueryBatchFeeResponse> getBatchFeesMethod;
    if ((getBatchFeesMethod = QueryGrpc.getBatchFeesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchFeesMethod = QueryGrpc.getBatchFeesMethod) == null) {
          QueryGrpc.getBatchFeesMethod = getBatchFeesMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryBatchFeeRequest, gravity.v1.QueryOuterClass.QueryBatchFeeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchFees"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryBatchFeeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryBatchFeeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchFees"))
              .build();
        }
      }
    }
    return getBatchFeesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest,
      gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> getOutgoingTxBatchesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OutgoingTxBatches",
      requestType = gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest.class,
      responseType = gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest,
      gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> getOutgoingTxBatchesMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest, gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> getOutgoingTxBatchesMethod;
    if ((getOutgoingTxBatchesMethod = QueryGrpc.getOutgoingTxBatchesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOutgoingTxBatchesMethod = QueryGrpc.getOutgoingTxBatchesMethod) == null) {
          QueryGrpc.getOutgoingTxBatchesMethod = getOutgoingTxBatchesMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest, gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OutgoingTxBatches"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OutgoingTxBatches"))
              .build();
        }
      }
    }
    return getOutgoingTxBatchesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsRequest,
      gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsResponse> getOutgoingLogicCallsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OutgoingLogicCalls",
      requestType = gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsRequest.class,
      responseType = gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsRequest,
      gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsResponse> getOutgoingLogicCallsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsRequest, gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsResponse> getOutgoingLogicCallsMethod;
    if ((getOutgoingLogicCallsMethod = QueryGrpc.getOutgoingLogicCallsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOutgoingLogicCallsMethod = QueryGrpc.getOutgoingLogicCallsMethod) == null) {
          QueryGrpc.getOutgoingLogicCallsMethod = getOutgoingLogicCallsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsRequest, gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OutgoingLogicCalls"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OutgoingLogicCalls"))
              .build();
        }
      }
    }
    return getOutgoingLogicCallsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest,
      gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> getBatchRequestByNonceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchRequestByNonce",
      requestType = gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest.class,
      responseType = gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest,
      gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> getBatchRequestByNonceMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest, gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> getBatchRequestByNonceMethod;
    if ((getBatchRequestByNonceMethod = QueryGrpc.getBatchRequestByNonceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchRequestByNonceMethod = QueryGrpc.getBatchRequestByNonceMethod) == null) {
          QueryGrpc.getBatchRequestByNonceMethod = getBatchRequestByNonceMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest, gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchRequestByNonce"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchRequestByNonce"))
              .build();
        }
      }
    }
    return getBatchRequestByNonceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest,
      gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse> getBatchConfirmsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchConfirms",
      requestType = gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest.class,
      responseType = gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest,
      gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse> getBatchConfirmsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest, gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse> getBatchConfirmsMethod;
    if ((getBatchConfirmsMethod = QueryGrpc.getBatchConfirmsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchConfirmsMethod = QueryGrpc.getBatchConfirmsMethod) == null) {
          QueryGrpc.getBatchConfirmsMethod = getBatchConfirmsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest, gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchConfirms"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchConfirms"))
              .build();
        }
      }
    }
    return getBatchConfirmsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLogicConfirmsRequest,
      gravity.v1.QueryOuterClass.QueryLogicConfirmsResponse> getLogicConfirmsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LogicConfirms",
      requestType = gravity.v1.QueryOuterClass.QueryLogicConfirmsRequest.class,
      responseType = gravity.v1.QueryOuterClass.QueryLogicConfirmsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLogicConfirmsRequest,
      gravity.v1.QueryOuterClass.QueryLogicConfirmsResponse> getLogicConfirmsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryLogicConfirmsRequest, gravity.v1.QueryOuterClass.QueryLogicConfirmsResponse> getLogicConfirmsMethod;
    if ((getLogicConfirmsMethod = QueryGrpc.getLogicConfirmsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLogicConfirmsMethod = QueryGrpc.getLogicConfirmsMethod) == null) {
          QueryGrpc.getLogicConfirmsMethod = getLogicConfirmsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryLogicConfirmsRequest, gravity.v1.QueryOuterClass.QueryLogicConfirmsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LogicConfirms"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryLogicConfirmsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryLogicConfirmsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LogicConfirms"))
              .build();
        }
      }
    }
    return getLogicConfirmsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest,
      gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse> getERC20ToDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ERC20ToDenom",
      requestType = gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest.class,
      responseType = gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest,
      gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse> getERC20ToDenomMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest, gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse> getERC20ToDenomMethod;
    if ((getERC20ToDenomMethod = QueryGrpc.getERC20ToDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getERC20ToDenomMethod = QueryGrpc.getERC20ToDenomMethod) == null) {
          QueryGrpc.getERC20ToDenomMethod = getERC20ToDenomMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest, gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ERC20ToDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ERC20ToDenom"))
              .build();
        }
      }
    }
    return getERC20ToDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryDenomToERC20Request,
      gravity.v1.QueryOuterClass.QueryDenomToERC20Response> getDenomToERC20Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomToERC20",
      requestType = gravity.v1.QueryOuterClass.QueryDenomToERC20Request.class,
      responseType = gravity.v1.QueryOuterClass.QueryDenomToERC20Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryDenomToERC20Request,
      gravity.v1.QueryOuterClass.QueryDenomToERC20Response> getDenomToERC20Method() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryDenomToERC20Request, gravity.v1.QueryOuterClass.QueryDenomToERC20Response> getDenomToERC20Method;
    if ((getDenomToERC20Method = QueryGrpc.getDenomToERC20Method) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomToERC20Method = QueryGrpc.getDenomToERC20Method) == null) {
          QueryGrpc.getDenomToERC20Method = getDenomToERC20Method =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryDenomToERC20Request, gravity.v1.QueryOuterClass.QueryDenomToERC20Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomToERC20"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryDenomToERC20Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryDenomToERC20Response.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomToERC20"))
              .build();
        }
      }
    }
    return getDenomToERC20Method;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress,
      gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse> getGetDelegateKeyByValidatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDelegateKeyByValidator",
      requestType = gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress.class,
      responseType = gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress,
      gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse> getGetDelegateKeyByValidatorMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress, gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse> getGetDelegateKeyByValidatorMethod;
    if ((getGetDelegateKeyByValidatorMethod = QueryGrpc.getGetDelegateKeyByValidatorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetDelegateKeyByValidatorMethod = QueryGrpc.getGetDelegateKeyByValidatorMethod) == null) {
          QueryGrpc.getGetDelegateKeyByValidatorMethod = getGetDelegateKeyByValidatorMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress, gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDelegateKeyByValidator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetDelegateKeyByValidator"))
              .build();
        }
      }
    }
    return getGetDelegateKeyByValidatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddress,
      gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse> getGetDelegateKeyByEthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDelegateKeyByEth",
      requestType = gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddress.class,
      responseType = gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddress,
      gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse> getGetDelegateKeyByEthMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddress, gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse> getGetDelegateKeyByEthMethod;
    if ((getGetDelegateKeyByEthMethod = QueryGrpc.getGetDelegateKeyByEthMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetDelegateKeyByEthMethod = QueryGrpc.getGetDelegateKeyByEthMethod) == null) {
          QueryGrpc.getGetDelegateKeyByEthMethod = getGetDelegateKeyByEthMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddress, gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDelegateKeyByEth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddress.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetDelegateKeyByEth"))
              .build();
        }
      }
    }
    return getGetDelegateKeyByEthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress,
      gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse> getGetDelegateKeyByOrchestratorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDelegateKeyByOrchestrator",
      requestType = gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress.class,
      responseType = gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress,
      gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse> getGetDelegateKeyByOrchestratorMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress, gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse> getGetDelegateKeyByOrchestratorMethod;
    if ((getGetDelegateKeyByOrchestratorMethod = QueryGrpc.getGetDelegateKeyByOrchestratorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetDelegateKeyByOrchestratorMethod = QueryGrpc.getGetDelegateKeyByOrchestratorMethod) == null) {
          QueryGrpc.getGetDelegateKeyByOrchestratorMethod = getGetDelegateKeyByOrchestratorMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress, gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDelegateKeyByOrchestrator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetDelegateKeyByOrchestrator"))
              .build();
        }
      }
    }
    return getGetDelegateKeyByOrchestratorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryPendingSendToEth,
      gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse> getGetPendingSendToEthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPendingSendToEth",
      requestType = gravity.v1.QueryOuterClass.QueryPendingSendToEth.class,
      responseType = gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryPendingSendToEth,
      gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse> getGetPendingSendToEthMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.QueryPendingSendToEth, gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse> getGetPendingSendToEthMethod;
    if ((getGetPendingSendToEthMethod = QueryGrpc.getGetPendingSendToEthMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetPendingSendToEthMethod = QueryGrpc.getGetPendingSendToEthMethod) == null) {
          QueryGrpc.getGetPendingSendToEthMethod = getGetPendingSendToEthMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.QueryPendingSendToEth, gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPendingSendToEth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryPendingSendToEth.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetPendingSendToEth"))
              .build();
        }
      }
    }
    return getGetPendingSendToEthMethod;
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
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Deployments queries deployments
     * </pre>
     */
    public void params(gravity.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     */
    public void currentValset(gravity.v1.QueryOuterClass.QueryCurrentValsetRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryCurrentValsetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCurrentValsetMethod(), responseObserver);
    }

    /**
     */
    public void valsetRequest(gravity.v1.QueryOuterClass.QueryValsetRequestRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryValsetRequestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValsetRequestMethod(), responseObserver);
    }

    /**
     */
    public void valsetConfirm(gravity.v1.QueryOuterClass.QueryValsetConfirmRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryValsetConfirmResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValsetConfirmMethod(), responseObserver);
    }

    /**
     */
    public void valsetConfirmsByNonce(gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValsetConfirmsByNonceMethod(), responseObserver);
    }

    /**
     */
    public void lastValsetRequests(gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLastValsetRequestsMethod(), responseObserver);
    }

    /**
     */
    public void lastPendingValsetRequestByAddr(gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLastPendingValsetRequestByAddrMethod(), responseObserver);
    }

    /**
     */
    public void lastPendingBatchRequestByAddr(gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLastPendingBatchRequestByAddrMethod(), responseObserver);
    }

    /**
     */
    public void lastPendingLogicCallByAddr(gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLastPendingLogicCallByAddrMethod(), responseObserver);
    }

    /**
     */
    public void lastEventNonceByAddr(gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLastEventNonceByAddrMethod(), responseObserver);
    }

    /**
     */
    public void batchFees(gravity.v1.QueryOuterClass.QueryBatchFeeRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryBatchFeeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchFeesMethod(), responseObserver);
    }

    /**
     */
    public void outgoingTxBatches(gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOutgoingTxBatchesMethod(), responseObserver);
    }

    /**
     */
    public void outgoingLogicCalls(gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOutgoingLogicCallsMethod(), responseObserver);
    }

    /**
     */
    public void batchRequestByNonce(gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchRequestByNonceMethod(), responseObserver);
    }

    /**
     */
    public void batchConfirms(gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchConfirmsMethod(), responseObserver);
    }

    /**
     */
    public void logicConfirms(gravity.v1.QueryOuterClass.QueryLogicConfirmsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLogicConfirmsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLogicConfirmsMethod(), responseObserver);
    }

    /**
     */
    public void eRC20ToDenom(gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getERC20ToDenomMethod(), responseObserver);
    }

    /**
     */
    public void denomToERC20(gravity.v1.QueryOuterClass.QueryDenomToERC20Request request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryDenomToERC20Response> responseObserver) {
      asyncUnimplementedUnaryCall(getDenomToERC20Method(), responseObserver);
    }

    /**
     */
    public void getDelegateKeyByValidator(gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDelegateKeyByValidatorMethod(), responseObserver);
    }

    /**
     */
    public void getDelegateKeyByEth(gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddress request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDelegateKeyByEthMethod(), responseObserver);
    }

    /**
     */
    public void getDelegateKeyByOrchestrator(gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDelegateKeyByOrchestratorMethod(), responseObserver);
    }

    /**
     */
    public void getPendingSendToEth(gravity.v1.QueryOuterClass.QueryPendingSendToEth request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPendingSendToEthMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryParamsRequest,
                gravity.v1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getCurrentValsetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryCurrentValsetRequest,
                gravity.v1.QueryOuterClass.QueryCurrentValsetResponse>(
                  this, METHODID_CURRENT_VALSET)))
          .addMethod(
            getValsetRequestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryValsetRequestRequest,
                gravity.v1.QueryOuterClass.QueryValsetRequestResponse>(
                  this, METHODID_VALSET_REQUEST)))
          .addMethod(
            getValsetConfirmMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryValsetConfirmRequest,
                gravity.v1.QueryOuterClass.QueryValsetConfirmResponse>(
                  this, METHODID_VALSET_CONFIRM)))
          .addMethod(
            getValsetConfirmsByNonceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest,
                gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse>(
                  this, METHODID_VALSET_CONFIRMS_BY_NONCE)))
          .addMethod(
            getLastValsetRequestsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest,
                gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse>(
                  this, METHODID_LAST_VALSET_REQUESTS)))
          .addMethod(
            getLastPendingValsetRequestByAddrMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest,
                gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse>(
                  this, METHODID_LAST_PENDING_VALSET_REQUEST_BY_ADDR)))
          .addMethod(
            getLastPendingBatchRequestByAddrMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest,
                gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse>(
                  this, METHODID_LAST_PENDING_BATCH_REQUEST_BY_ADDR)))
          .addMethod(
            getLastPendingLogicCallByAddrMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrRequest,
                gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrResponse>(
                  this, METHODID_LAST_PENDING_LOGIC_CALL_BY_ADDR)))
          .addMethod(
            getLastEventNonceByAddrMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest,
                gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse>(
                  this, METHODID_LAST_EVENT_NONCE_BY_ADDR)))
          .addMethod(
            getBatchFeesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryBatchFeeRequest,
                gravity.v1.QueryOuterClass.QueryBatchFeeResponse>(
                  this, METHODID_BATCH_FEES)))
          .addMethod(
            getOutgoingTxBatchesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest,
                gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse>(
                  this, METHODID_OUTGOING_TX_BATCHES)))
          .addMethod(
            getOutgoingLogicCallsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsRequest,
                gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsResponse>(
                  this, METHODID_OUTGOING_LOGIC_CALLS)))
          .addMethod(
            getBatchRequestByNonceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest,
                gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse>(
                  this, METHODID_BATCH_REQUEST_BY_NONCE)))
          .addMethod(
            getBatchConfirmsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest,
                gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse>(
                  this, METHODID_BATCH_CONFIRMS)))
          .addMethod(
            getLogicConfirmsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryLogicConfirmsRequest,
                gravity.v1.QueryOuterClass.QueryLogicConfirmsResponse>(
                  this, METHODID_LOGIC_CONFIRMS)))
          .addMethod(
            getERC20ToDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest,
                gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse>(
                  this, METHODID_ERC20TO_DENOM)))
          .addMethod(
            getDenomToERC20Method(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryDenomToERC20Request,
                gravity.v1.QueryOuterClass.QueryDenomToERC20Response>(
                  this, METHODID_DENOM_TO_ERC20)))
          .addMethod(
            getGetDelegateKeyByValidatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress,
                gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse>(
                  this, METHODID_GET_DELEGATE_KEY_BY_VALIDATOR)))
          .addMethod(
            getGetDelegateKeyByEthMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddress,
                gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse>(
                  this, METHODID_GET_DELEGATE_KEY_BY_ETH)))
          .addMethod(
            getGetDelegateKeyByOrchestratorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress,
                gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse>(
                  this, METHODID_GET_DELEGATE_KEY_BY_ORCHESTRATOR)))
          .addMethod(
            getGetPendingSendToEthMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.QueryPendingSendToEth,
                gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse>(
                  this, METHODID_GET_PENDING_SEND_TO_ETH)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     * Deployments queries deployments
     * </pre>
     */
    public void params(gravity.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void currentValset(gravity.v1.QueryOuterClass.QueryCurrentValsetRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryCurrentValsetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCurrentValsetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void valsetRequest(gravity.v1.QueryOuterClass.QueryValsetRequestRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryValsetRequestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValsetRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void valsetConfirm(gravity.v1.QueryOuterClass.QueryValsetConfirmRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryValsetConfirmResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValsetConfirmMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void valsetConfirmsByNonce(gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValsetConfirmsByNonceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastValsetRequests(gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLastValsetRequestsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastPendingValsetRequestByAddr(gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLastPendingValsetRequestByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastPendingBatchRequestByAddr(gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLastPendingBatchRequestByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastPendingLogicCallByAddr(gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLastPendingLogicCallByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastEventNonceByAddr(gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLastEventNonceByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchFees(gravity.v1.QueryOuterClass.QueryBatchFeeRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryBatchFeeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchFeesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void outgoingTxBatches(gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOutgoingTxBatchesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void outgoingLogicCalls(gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOutgoingLogicCallsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchRequestByNonce(gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchRequestByNonceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchConfirms(gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchConfirmsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void logicConfirms(gravity.v1.QueryOuterClass.QueryLogicConfirmsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLogicConfirmsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogicConfirmsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void eRC20ToDenom(gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getERC20ToDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void denomToERC20(gravity.v1.QueryOuterClass.QueryDenomToERC20Request request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryDenomToERC20Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDenomToERC20Method(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDelegateKeyByValidator(gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDelegateKeyByValidatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDelegateKeyByEth(gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddress request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDelegateKeyByEthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDelegateKeyByOrchestrator(gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDelegateKeyByOrchestratorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPendingSendToEth(gravity.v1.QueryOuterClass.QueryPendingSendToEth request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetPendingSendToEthMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     * Deployments queries deployments
     * </pre>
     */
    public gravity.v1.QueryOuterClass.QueryParamsResponse params(gravity.v1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryCurrentValsetResponse currentValset(gravity.v1.QueryOuterClass.QueryCurrentValsetRequest request) {
      return blockingUnaryCall(
          getChannel(), getCurrentValsetMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryValsetRequestResponse valsetRequest(gravity.v1.QueryOuterClass.QueryValsetRequestRequest request) {
      return blockingUnaryCall(
          getChannel(), getValsetRequestMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryValsetConfirmResponse valsetConfirm(gravity.v1.QueryOuterClass.QueryValsetConfirmRequest request) {
      return blockingUnaryCall(
          getChannel(), getValsetConfirmMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse valsetConfirmsByNonce(gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest request) {
      return blockingUnaryCall(
          getChannel(), getValsetConfirmsByNonceMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse lastValsetRequests(gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest request) {
      return blockingUnaryCall(
          getChannel(), getLastValsetRequestsMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse lastPendingValsetRequestByAddr(gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest request) {
      return blockingUnaryCall(
          getChannel(), getLastPendingValsetRequestByAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse lastPendingBatchRequestByAddr(gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest request) {
      return blockingUnaryCall(
          getChannel(), getLastPendingBatchRequestByAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrResponse lastPendingLogicCallByAddr(gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrRequest request) {
      return blockingUnaryCall(
          getChannel(), getLastPendingLogicCallByAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse lastEventNonceByAddr(gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest request) {
      return blockingUnaryCall(
          getChannel(), getLastEventNonceByAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryBatchFeeResponse batchFees(gravity.v1.QueryOuterClass.QueryBatchFeeRequest request) {
      return blockingUnaryCall(
          getChannel(), getBatchFeesMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse outgoingTxBatches(gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest request) {
      return blockingUnaryCall(
          getChannel(), getOutgoingTxBatchesMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsResponse outgoingLogicCalls(gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsRequest request) {
      return blockingUnaryCall(
          getChannel(), getOutgoingLogicCallsMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse batchRequestByNonce(gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest request) {
      return blockingUnaryCall(
          getChannel(), getBatchRequestByNonceMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse batchConfirms(gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest request) {
      return blockingUnaryCall(
          getChannel(), getBatchConfirmsMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryLogicConfirmsResponse logicConfirms(gravity.v1.QueryOuterClass.QueryLogicConfirmsRequest request) {
      return blockingUnaryCall(
          getChannel(), getLogicConfirmsMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse eRC20ToDenom(gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest request) {
      return blockingUnaryCall(
          getChannel(), getERC20ToDenomMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryDenomToERC20Response denomToERC20(gravity.v1.QueryOuterClass.QueryDenomToERC20Request request) {
      return blockingUnaryCall(
          getChannel(), getDenomToERC20Method(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse getDelegateKeyByValidator(gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress request) {
      return blockingUnaryCall(
          getChannel(), getGetDelegateKeyByValidatorMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse getDelegateKeyByEth(gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddress request) {
      return blockingUnaryCall(
          getChannel(), getGetDelegateKeyByEthMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse getDelegateKeyByOrchestrator(gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress request) {
      return blockingUnaryCall(
          getChannel(), getGetDelegateKeyByOrchestratorMethod(), getCallOptions(), request);
    }

    /**
     */
    public gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse getPendingSendToEth(gravity.v1.QueryOuterClass.QueryPendingSendToEth request) {
      return blockingUnaryCall(
          getChannel(), getGetPendingSendToEthMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     * Deployments queries deployments
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryParamsResponse> params(
        gravity.v1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryCurrentValsetResponse> currentValset(
        gravity.v1.QueryOuterClass.QueryCurrentValsetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCurrentValsetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryValsetRequestResponse> valsetRequest(
        gravity.v1.QueryOuterClass.QueryValsetRequestRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValsetRequestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryValsetConfirmResponse> valsetConfirm(
        gravity.v1.QueryOuterClass.QueryValsetConfirmRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValsetConfirmMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> valsetConfirmsByNonce(
        gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValsetConfirmsByNonceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse> lastValsetRequests(
        gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLastValsetRequestsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> lastPendingValsetRequestByAddr(
        gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLastPendingValsetRequestByAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> lastPendingBatchRequestByAddr(
        gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLastPendingBatchRequestByAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrResponse> lastPendingLogicCallByAddr(
        gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLastPendingLogicCallByAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> lastEventNonceByAddr(
        gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLastEventNonceByAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryBatchFeeResponse> batchFees(
        gravity.v1.QueryOuterClass.QueryBatchFeeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchFeesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> outgoingTxBatches(
        gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOutgoingTxBatchesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsResponse> outgoingLogicCalls(
        gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOutgoingLogicCallsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> batchRequestByNonce(
        gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchRequestByNonceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse> batchConfirms(
        gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchConfirmsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryLogicConfirmsResponse> logicConfirms(
        gravity.v1.QueryOuterClass.QueryLogicConfirmsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLogicConfirmsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse> eRC20ToDenom(
        gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getERC20ToDenomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryDenomToERC20Response> denomToERC20(
        gravity.v1.QueryOuterClass.QueryDenomToERC20Request request) {
      return futureUnaryCall(
          getChannel().newCall(getDenomToERC20Method(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse> getDelegateKeyByValidator(
        gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDelegateKeyByValidatorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse> getDelegateKeyByEth(
        gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddress request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDelegateKeyByEthMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse> getDelegateKeyByOrchestrator(
        gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDelegateKeyByOrchestratorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse> getPendingSendToEth(
        gravity.v1.QueryOuterClass.QueryPendingSendToEth request) {
      return futureUnaryCall(
          getChannel().newCall(getGetPendingSendToEthMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_CURRENT_VALSET = 1;
  private static final int METHODID_VALSET_REQUEST = 2;
  private static final int METHODID_VALSET_CONFIRM = 3;
  private static final int METHODID_VALSET_CONFIRMS_BY_NONCE = 4;
  private static final int METHODID_LAST_VALSET_REQUESTS = 5;
  private static final int METHODID_LAST_PENDING_VALSET_REQUEST_BY_ADDR = 6;
  private static final int METHODID_LAST_PENDING_BATCH_REQUEST_BY_ADDR = 7;
  private static final int METHODID_LAST_PENDING_LOGIC_CALL_BY_ADDR = 8;
  private static final int METHODID_LAST_EVENT_NONCE_BY_ADDR = 9;
  private static final int METHODID_BATCH_FEES = 10;
  private static final int METHODID_OUTGOING_TX_BATCHES = 11;
  private static final int METHODID_OUTGOING_LOGIC_CALLS = 12;
  private static final int METHODID_BATCH_REQUEST_BY_NONCE = 13;
  private static final int METHODID_BATCH_CONFIRMS = 14;
  private static final int METHODID_LOGIC_CONFIRMS = 15;
  private static final int METHODID_ERC20TO_DENOM = 16;
  private static final int METHODID_DENOM_TO_ERC20 = 17;
  private static final int METHODID_GET_DELEGATE_KEY_BY_VALIDATOR = 18;
  private static final int METHODID_GET_DELEGATE_KEY_BY_ETH = 19;
  private static final int METHODID_GET_DELEGATE_KEY_BY_ORCHESTRATOR = 20;
  private static final int METHODID_GET_PENDING_SEND_TO_ETH = 21;

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
          serviceImpl.params((gravity.v1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_CURRENT_VALSET:
          serviceImpl.currentValset((gravity.v1.QueryOuterClass.QueryCurrentValsetRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryCurrentValsetResponse>) responseObserver);
          break;
        case METHODID_VALSET_REQUEST:
          serviceImpl.valsetRequest((gravity.v1.QueryOuterClass.QueryValsetRequestRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryValsetRequestResponse>) responseObserver);
          break;
        case METHODID_VALSET_CONFIRM:
          serviceImpl.valsetConfirm((gravity.v1.QueryOuterClass.QueryValsetConfirmRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryValsetConfirmResponse>) responseObserver);
          break;
        case METHODID_VALSET_CONFIRMS_BY_NONCE:
          serviceImpl.valsetConfirmsByNonce((gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse>) responseObserver);
          break;
        case METHODID_LAST_VALSET_REQUESTS:
          serviceImpl.lastValsetRequests((gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse>) responseObserver);
          break;
        case METHODID_LAST_PENDING_VALSET_REQUEST_BY_ADDR:
          serviceImpl.lastPendingValsetRequestByAddr((gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse>) responseObserver);
          break;
        case METHODID_LAST_PENDING_BATCH_REQUEST_BY_ADDR:
          serviceImpl.lastPendingBatchRequestByAddr((gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse>) responseObserver);
          break;
        case METHODID_LAST_PENDING_LOGIC_CALL_BY_ADDR:
          serviceImpl.lastPendingLogicCallByAddr((gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLastPendingLogicCallByAddrResponse>) responseObserver);
          break;
        case METHODID_LAST_EVENT_NONCE_BY_ADDR:
          serviceImpl.lastEventNonceByAddr((gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse>) responseObserver);
          break;
        case METHODID_BATCH_FEES:
          serviceImpl.batchFees((gravity.v1.QueryOuterClass.QueryBatchFeeRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryBatchFeeResponse>) responseObserver);
          break;
        case METHODID_OUTGOING_TX_BATCHES:
          serviceImpl.outgoingTxBatches((gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse>) responseObserver);
          break;
        case METHODID_OUTGOING_LOGIC_CALLS:
          serviceImpl.outgoingLogicCalls((gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryOutgoingLogicCallsResponse>) responseObserver);
          break;
        case METHODID_BATCH_REQUEST_BY_NONCE:
          serviceImpl.batchRequestByNonce((gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse>) responseObserver);
          break;
        case METHODID_BATCH_CONFIRMS:
          serviceImpl.batchConfirms((gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse>) responseObserver);
          break;
        case METHODID_LOGIC_CONFIRMS:
          serviceImpl.logicConfirms((gravity.v1.QueryOuterClass.QueryLogicConfirmsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryLogicConfirmsResponse>) responseObserver);
          break;
        case METHODID_ERC20TO_DENOM:
          serviceImpl.eRC20ToDenom((gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse>) responseObserver);
          break;
        case METHODID_DENOM_TO_ERC20:
          serviceImpl.denomToERC20((gravity.v1.QueryOuterClass.QueryDenomToERC20Request) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryDenomToERC20Response>) responseObserver);
          break;
        case METHODID_GET_DELEGATE_KEY_BY_VALIDATOR:
          serviceImpl.getDelegateKeyByValidator((gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse>) responseObserver);
          break;
        case METHODID_GET_DELEGATE_KEY_BY_ETH:
          serviceImpl.getDelegateKeyByEth((gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddress) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse>) responseObserver);
          break;
        case METHODID_GET_DELEGATE_KEY_BY_ORCHESTRATOR:
          serviceImpl.getDelegateKeyByOrchestrator((gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse>) responseObserver);
          break;
        case METHODID_GET_PENDING_SEND_TO_ETH:
          serviceImpl.getPendingSendToEth((gravity.v1.QueryOuterClass.QueryPendingSendToEth) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse>) responseObserver);
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
      return gravity.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getLastPendingBatchRequestByAddrMethod())
              .addMethod(getLastPendingLogicCallByAddrMethod())
              .addMethod(getLastEventNonceByAddrMethod())
              .addMethod(getBatchFeesMethod())
              .addMethod(getOutgoingTxBatchesMethod())
              .addMethod(getOutgoingLogicCallsMethod())
              .addMethod(getBatchRequestByNonceMethod())
              .addMethod(getBatchConfirmsMethod())
              .addMethod(getLogicConfirmsMethod())
              .addMethod(getERC20ToDenomMethod())
              .addMethod(getDenomToERC20Method())
              .addMethod(getGetDelegateKeyByValidatorMethod())
              .addMethod(getGetDelegateKeyByEthMethod())
              .addMethod(getGetDelegateKeyByOrchestratorMethod())
              .addMethod(getGetPendingSendToEthMethod())
              .build();
        }
      }
    }
    return result;
  }
}
