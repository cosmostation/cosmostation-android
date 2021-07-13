package tendermint.liquidity.v1beta1;

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
 * Query defines the gRPC query service for the liquidity module.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: tendermint/liquidity/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "tendermint.liquidity.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsResponse> getLiquidityPoolsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiquidityPools",
      requestType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsRequest.class,
      responseType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsResponse> getLiquidityPoolsMethod() {
    io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsResponse> getLiquidityPoolsMethod;
    if ((getLiquidityPoolsMethod = QueryGrpc.getLiquidityPoolsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLiquidityPoolsMethod = QueryGrpc.getLiquidityPoolsMethod) == null) {
          QueryGrpc.getLiquidityPoolsMethod = getLiquidityPoolsMethod =
              io.grpc.MethodDescriptor.<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LiquidityPools"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LiquidityPools"))
              .build();
        }
      }
    }
    return getLiquidityPoolsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> getLiquidityPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiquidityPool",
      requestType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolRequest.class,
      responseType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> getLiquidityPoolMethod() {
    io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> getLiquidityPoolMethod;
    if ((getLiquidityPoolMethod = QueryGrpc.getLiquidityPoolMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLiquidityPoolMethod = QueryGrpc.getLiquidityPoolMethod) == null) {
          QueryGrpc.getLiquidityPoolMethod = getLiquidityPoolMethod =
              io.grpc.MethodDescriptor.<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LiquidityPool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LiquidityPool"))
              .build();
        }
      }
    }
    return getLiquidityPoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByPoolCoinDenomRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> getLiquidityPoolByPoolCoinDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiquidityPoolByPoolCoinDenom",
      requestType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByPoolCoinDenomRequest.class,
      responseType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByPoolCoinDenomRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> getLiquidityPoolByPoolCoinDenomMethod() {
    io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByPoolCoinDenomRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> getLiquidityPoolByPoolCoinDenomMethod;
    if ((getLiquidityPoolByPoolCoinDenomMethod = QueryGrpc.getLiquidityPoolByPoolCoinDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLiquidityPoolByPoolCoinDenomMethod = QueryGrpc.getLiquidityPoolByPoolCoinDenomMethod) == null) {
          QueryGrpc.getLiquidityPoolByPoolCoinDenomMethod = getLiquidityPoolByPoolCoinDenomMethod =
              io.grpc.MethodDescriptor.<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByPoolCoinDenomRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LiquidityPoolByPoolCoinDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByPoolCoinDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LiquidityPoolByPoolCoinDenom"))
              .build();
        }
      }
    }
    return getLiquidityPoolByPoolCoinDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByReserveAccRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> getLiquidityPoolByReserveAccMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiquidityPoolByReserveAcc",
      requestType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByReserveAccRequest.class,
      responseType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByReserveAccRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> getLiquidityPoolByReserveAccMethod() {
    io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByReserveAccRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> getLiquidityPoolByReserveAccMethod;
    if ((getLiquidityPoolByReserveAccMethod = QueryGrpc.getLiquidityPoolByReserveAccMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLiquidityPoolByReserveAccMethod = QueryGrpc.getLiquidityPoolByReserveAccMethod) == null) {
          QueryGrpc.getLiquidityPoolByReserveAccMethod = getLiquidityPoolByReserveAccMethod =
              io.grpc.MethodDescriptor.<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByReserveAccRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LiquidityPoolByReserveAcc"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByReserveAccRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LiquidityPoolByReserveAcc"))
              .build();
        }
      }
    }
    return getLiquidityPoolByReserveAccMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchResponse> getLiquidityPoolBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiquidityPoolBatch",
      requestType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchRequest.class,
      responseType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchResponse> getLiquidityPoolBatchMethod() {
    io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchResponse> getLiquidityPoolBatchMethod;
    if ((getLiquidityPoolBatchMethod = QueryGrpc.getLiquidityPoolBatchMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLiquidityPoolBatchMethod = QueryGrpc.getLiquidityPoolBatchMethod) == null) {
          QueryGrpc.getLiquidityPoolBatchMethod = getLiquidityPoolBatchMethod =
              io.grpc.MethodDescriptor.<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LiquidityPoolBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LiquidityPoolBatch"))
              .build();
        }
      }
    }
    return getLiquidityPoolBatchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsResponse> getPoolBatchSwapMsgsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PoolBatchSwapMsgs",
      requestType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsRequest.class,
      responseType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsResponse> getPoolBatchSwapMsgsMethod() {
    io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsResponse> getPoolBatchSwapMsgsMethod;
    if ((getPoolBatchSwapMsgsMethod = QueryGrpc.getPoolBatchSwapMsgsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolBatchSwapMsgsMethod = QueryGrpc.getPoolBatchSwapMsgsMethod) == null) {
          QueryGrpc.getPoolBatchSwapMsgsMethod = getPoolBatchSwapMsgsMethod =
              io.grpc.MethodDescriptor.<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PoolBatchSwapMsgs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PoolBatchSwapMsgs"))
              .build();
        }
      }
    }
    return getPoolBatchSwapMsgsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgResponse> getPoolBatchSwapMsgMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PoolBatchSwapMsg",
      requestType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgRequest.class,
      responseType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgResponse> getPoolBatchSwapMsgMethod() {
    io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgResponse> getPoolBatchSwapMsgMethod;
    if ((getPoolBatchSwapMsgMethod = QueryGrpc.getPoolBatchSwapMsgMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolBatchSwapMsgMethod = QueryGrpc.getPoolBatchSwapMsgMethod) == null) {
          QueryGrpc.getPoolBatchSwapMsgMethod = getPoolBatchSwapMsgMethod =
              io.grpc.MethodDescriptor.<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PoolBatchSwapMsg"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PoolBatchSwapMsg"))
              .build();
        }
      }
    }
    return getPoolBatchSwapMsgMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsResponse> getPoolBatchDepositMsgsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PoolBatchDepositMsgs",
      requestType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsRequest.class,
      responseType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsResponse> getPoolBatchDepositMsgsMethod() {
    io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsResponse> getPoolBatchDepositMsgsMethod;
    if ((getPoolBatchDepositMsgsMethod = QueryGrpc.getPoolBatchDepositMsgsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolBatchDepositMsgsMethod = QueryGrpc.getPoolBatchDepositMsgsMethod) == null) {
          QueryGrpc.getPoolBatchDepositMsgsMethod = getPoolBatchDepositMsgsMethod =
              io.grpc.MethodDescriptor.<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PoolBatchDepositMsgs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PoolBatchDepositMsgs"))
              .build();
        }
      }
    }
    return getPoolBatchDepositMsgsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgResponse> getPoolBatchDepositMsgMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PoolBatchDepositMsg",
      requestType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgRequest.class,
      responseType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgResponse> getPoolBatchDepositMsgMethod() {
    io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgResponse> getPoolBatchDepositMsgMethod;
    if ((getPoolBatchDepositMsgMethod = QueryGrpc.getPoolBatchDepositMsgMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolBatchDepositMsgMethod = QueryGrpc.getPoolBatchDepositMsgMethod) == null) {
          QueryGrpc.getPoolBatchDepositMsgMethod = getPoolBatchDepositMsgMethod =
              io.grpc.MethodDescriptor.<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PoolBatchDepositMsg"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PoolBatchDepositMsg"))
              .build();
        }
      }
    }
    return getPoolBatchDepositMsgMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsResponse> getPoolBatchWithdrawMsgsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PoolBatchWithdrawMsgs",
      requestType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsRequest.class,
      responseType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsResponse> getPoolBatchWithdrawMsgsMethod() {
    io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsResponse> getPoolBatchWithdrawMsgsMethod;
    if ((getPoolBatchWithdrawMsgsMethod = QueryGrpc.getPoolBatchWithdrawMsgsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolBatchWithdrawMsgsMethod = QueryGrpc.getPoolBatchWithdrawMsgsMethod) == null) {
          QueryGrpc.getPoolBatchWithdrawMsgsMethod = getPoolBatchWithdrawMsgsMethod =
              io.grpc.MethodDescriptor.<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PoolBatchWithdrawMsgs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PoolBatchWithdrawMsgs"))
              .build();
        }
      }
    }
    return getPoolBatchWithdrawMsgsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgResponse> getPoolBatchWithdrawMsgMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PoolBatchWithdrawMsg",
      requestType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgRequest.class,
      responseType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgResponse> getPoolBatchWithdrawMsgMethod() {
    io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgResponse> getPoolBatchWithdrawMsgMethod;
    if ((getPoolBatchWithdrawMsgMethod = QueryGrpc.getPoolBatchWithdrawMsgMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolBatchWithdrawMsgMethod = QueryGrpc.getPoolBatchWithdrawMsgMethod) == null) {
          QueryGrpc.getPoolBatchWithdrawMsgMethod = getPoolBatchWithdrawMsgMethod =
              io.grpc.MethodDescriptor.<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PoolBatchWithdrawMsg"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PoolBatchWithdrawMsg"))
              .build();
        }
      }
    }
    return getPoolBatchWithdrawMsgMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest,
      tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest, tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
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
   * Query defines the gRPC query service for the liquidity module.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Get existing liquidity pools.
     * </pre>
     */
    public void liquidityPools(tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLiquidityPoolsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get specific liquidity pool.
     * </pre>
     */
    public void liquidityPool(tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLiquidityPoolMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get specific liquidity pool corresponding to the pool_coin_denom.
     * </pre>
     */
    public void liquidityPoolByPoolCoinDenom(tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByPoolCoinDenomRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLiquidityPoolByPoolCoinDenomMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get specific liquidity pool corresponding to the reserve account.
     * </pre>
     */
    public void liquidityPoolByReserveAcc(tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByReserveAccRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLiquidityPoolByReserveAccMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get the pool's current batch.
     * </pre>
     */
    public void liquidityPoolBatch(tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLiquidityPoolBatchMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get all swap messages in the pool's current batch.
     * </pre>
     */
    public void poolBatchSwapMsgs(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolBatchSwapMsgsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get a specific swap message in the pool's current batch.
     * </pre>
     */
    public void poolBatchSwapMsg(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolBatchSwapMsgMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get all deposit messages in the pool's current batch.
     * </pre>
     */
    public void poolBatchDepositMsgs(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolBatchDepositMsgsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get a specific deposit message in the pool's current batch.
     * </pre>
     */
    public void poolBatchDepositMsg(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolBatchDepositMsgMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get all withdraw messages in the pool's current batch.
     * </pre>
     */
    public void poolBatchWithdrawMsgs(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolBatchWithdrawMsgsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get a specific withdraw message in the pool's current batch.
     * </pre>
     */
    public void poolBatchWithdrawMsg(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolBatchWithdrawMsgMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get all parameters of the liquidity module.
     * </pre>
     */
    public void params(tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLiquidityPoolsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsRequest,
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsResponse>(
                  this, METHODID_LIQUIDITY_POOLS)))
          .addMethod(
            getLiquidityPoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolRequest,
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse>(
                  this, METHODID_LIQUIDITY_POOL)))
          .addMethod(
            getLiquidityPoolByPoolCoinDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByPoolCoinDenomRequest,
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse>(
                  this, METHODID_LIQUIDITY_POOL_BY_POOL_COIN_DENOM)))
          .addMethod(
            getLiquidityPoolByReserveAccMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByReserveAccRequest,
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse>(
                  this, METHODID_LIQUIDITY_POOL_BY_RESERVE_ACC)))
          .addMethod(
            getLiquidityPoolBatchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchRequest,
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchResponse>(
                  this, METHODID_LIQUIDITY_POOL_BATCH)))
          .addMethod(
            getPoolBatchSwapMsgsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsRequest,
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsResponse>(
                  this, METHODID_POOL_BATCH_SWAP_MSGS)))
          .addMethod(
            getPoolBatchSwapMsgMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgRequest,
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgResponse>(
                  this, METHODID_POOL_BATCH_SWAP_MSG)))
          .addMethod(
            getPoolBatchDepositMsgsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsRequest,
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsResponse>(
                  this, METHODID_POOL_BATCH_DEPOSIT_MSGS)))
          .addMethod(
            getPoolBatchDepositMsgMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgRequest,
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgResponse>(
                  this, METHODID_POOL_BATCH_DEPOSIT_MSG)))
          .addMethod(
            getPoolBatchWithdrawMsgsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsRequest,
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsResponse>(
                  this, METHODID_POOL_BATCH_WITHDRAW_MSGS)))
          .addMethod(
            getPoolBatchWithdrawMsgMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgRequest,
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgResponse>(
                  this, METHODID_POOL_BATCH_WITHDRAW_MSG)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest,
                tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC query service for the liquidity module.
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
     * Get existing liquidity pools.
     * </pre>
     */
    public void liquidityPools(tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLiquidityPoolsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get specific liquidity pool.
     * </pre>
     */
    public void liquidityPool(tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLiquidityPoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get specific liquidity pool corresponding to the pool_coin_denom.
     * </pre>
     */
    public void liquidityPoolByPoolCoinDenom(tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByPoolCoinDenomRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLiquidityPoolByPoolCoinDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get specific liquidity pool corresponding to the reserve account.
     * </pre>
     */
    public void liquidityPoolByReserveAcc(tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByReserveAccRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLiquidityPoolByReserveAccMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get the pool's current batch.
     * </pre>
     */
    public void liquidityPoolBatch(tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLiquidityPoolBatchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get all swap messages in the pool's current batch.
     * </pre>
     */
    public void poolBatchSwapMsgs(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolBatchSwapMsgsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get a specific swap message in the pool's current batch.
     * </pre>
     */
    public void poolBatchSwapMsg(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolBatchSwapMsgMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get all deposit messages in the pool's current batch.
     * </pre>
     */
    public void poolBatchDepositMsgs(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolBatchDepositMsgsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get a specific deposit message in the pool's current batch.
     * </pre>
     */
    public void poolBatchDepositMsg(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolBatchDepositMsgMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get all withdraw messages in the pool's current batch.
     * </pre>
     */
    public void poolBatchWithdrawMsgs(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolBatchWithdrawMsgsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get a specific withdraw message in the pool's current batch.
     * </pre>
     */
    public void poolBatchWithdrawMsg(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolBatchWithdrawMsgMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get all parameters of the liquidity module.
     * </pre>
     */
    public void params(tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC query service for the liquidity module.
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
     * Get existing liquidity pools.
     * </pre>
     */
    public tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsResponse liquidityPools(tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsRequest request) {
      return blockingUnaryCall(
          getChannel(), getLiquidityPoolsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get specific liquidity pool.
     * </pre>
     */
    public tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse liquidityPool(tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolRequest request) {
      return blockingUnaryCall(
          getChannel(), getLiquidityPoolMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get specific liquidity pool corresponding to the pool_coin_denom.
     * </pre>
     */
    public tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse liquidityPoolByPoolCoinDenom(tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByPoolCoinDenomRequest request) {
      return blockingUnaryCall(
          getChannel(), getLiquidityPoolByPoolCoinDenomMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get specific liquidity pool corresponding to the reserve account.
     * </pre>
     */
    public tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse liquidityPoolByReserveAcc(tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByReserveAccRequest request) {
      return blockingUnaryCall(
          getChannel(), getLiquidityPoolByReserveAccMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get the pool's current batch.
     * </pre>
     */
    public tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchResponse liquidityPoolBatch(tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchRequest request) {
      return blockingUnaryCall(
          getChannel(), getLiquidityPoolBatchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get all swap messages in the pool's current batch.
     * </pre>
     */
    public tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsResponse poolBatchSwapMsgs(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolBatchSwapMsgsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get a specific swap message in the pool's current batch.
     * </pre>
     */
    public tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgResponse poolBatchSwapMsg(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolBatchSwapMsgMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get all deposit messages in the pool's current batch.
     * </pre>
     */
    public tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsResponse poolBatchDepositMsgs(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolBatchDepositMsgsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get a specific deposit message in the pool's current batch.
     * </pre>
     */
    public tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgResponse poolBatchDepositMsg(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolBatchDepositMsgMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get all withdraw messages in the pool's current batch.
     * </pre>
     */
    public tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsResponse poolBatchWithdrawMsgs(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolBatchWithdrawMsgsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get a specific withdraw message in the pool's current batch.
     * </pre>
     */
    public tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgResponse poolBatchWithdrawMsg(tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolBatchWithdrawMsgMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get all parameters of the liquidity module.
     * </pre>
     */
    public tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse params(tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC query service for the liquidity module.
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
     * Get existing liquidity pools.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsResponse> liquidityPools(
        tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLiquidityPoolsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get specific liquidity pool.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> liquidityPool(
        tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLiquidityPoolMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get specific liquidity pool corresponding to the pool_coin_denom.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> liquidityPoolByPoolCoinDenom(
        tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByPoolCoinDenomRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLiquidityPoolByPoolCoinDenomMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get specific liquidity pool corresponding to the reserve account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse> liquidityPoolByReserveAcc(
        tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByReserveAccRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLiquidityPoolByReserveAccMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get the pool's current batch.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchResponse> liquidityPoolBatch(
        tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLiquidityPoolBatchMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get all swap messages in the pool's current batch.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsResponse> poolBatchSwapMsgs(
        tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolBatchSwapMsgsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get a specific swap message in the pool's current batch.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgResponse> poolBatchSwapMsg(
        tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolBatchSwapMsgMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get all deposit messages in the pool's current batch.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsResponse> poolBatchDepositMsgs(
        tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolBatchDepositMsgsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get a specific deposit message in the pool's current batch.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgResponse> poolBatchDepositMsg(
        tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolBatchDepositMsgMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get all withdraw messages in the pool's current batch.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsResponse> poolBatchWithdrawMsgs(
        tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolBatchWithdrawMsgsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get a specific withdraw message in the pool's current batch.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgResponse> poolBatchWithdrawMsg(
        tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolBatchWithdrawMsgMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get all parameters of the liquidity module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIQUIDITY_POOLS = 0;
  private static final int METHODID_LIQUIDITY_POOL = 1;
  private static final int METHODID_LIQUIDITY_POOL_BY_POOL_COIN_DENOM = 2;
  private static final int METHODID_LIQUIDITY_POOL_BY_RESERVE_ACC = 3;
  private static final int METHODID_LIQUIDITY_POOL_BATCH = 4;
  private static final int METHODID_POOL_BATCH_SWAP_MSGS = 5;
  private static final int METHODID_POOL_BATCH_SWAP_MSG = 6;
  private static final int METHODID_POOL_BATCH_DEPOSIT_MSGS = 7;
  private static final int METHODID_POOL_BATCH_DEPOSIT_MSG = 8;
  private static final int METHODID_POOL_BATCH_WITHDRAW_MSGS = 9;
  private static final int METHODID_POOL_BATCH_WITHDRAW_MSG = 10;
  private static final int METHODID_PARAMS = 11;

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
        case METHODID_LIQUIDITY_POOLS:
          serviceImpl.liquidityPools((tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsRequest) request,
              (io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolsResponse>) responseObserver);
          break;
        case METHODID_LIQUIDITY_POOL:
          serviceImpl.liquidityPool((tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolRequest) request,
              (io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse>) responseObserver);
          break;
        case METHODID_LIQUIDITY_POOL_BY_POOL_COIN_DENOM:
          serviceImpl.liquidityPoolByPoolCoinDenom((tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByPoolCoinDenomRequest) request,
              (io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse>) responseObserver);
          break;
        case METHODID_LIQUIDITY_POOL_BY_RESERVE_ACC:
          serviceImpl.liquidityPoolByReserveAcc((tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolByReserveAccRequest) request,
              (io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolResponse>) responseObserver);
          break;
        case METHODID_LIQUIDITY_POOL_BATCH:
          serviceImpl.liquidityPoolBatch((tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchRequest) request,
              (io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryLiquidityPoolBatchResponse>) responseObserver);
          break;
        case METHODID_POOL_BATCH_SWAP_MSGS:
          serviceImpl.poolBatchSwapMsgs((tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsRequest) request,
              (io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgsResponse>) responseObserver);
          break;
        case METHODID_POOL_BATCH_SWAP_MSG:
          serviceImpl.poolBatchSwapMsg((tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgRequest) request,
              (io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchSwapMsgResponse>) responseObserver);
          break;
        case METHODID_POOL_BATCH_DEPOSIT_MSGS:
          serviceImpl.poolBatchDepositMsgs((tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsRequest) request,
              (io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgsResponse>) responseObserver);
          break;
        case METHODID_POOL_BATCH_DEPOSIT_MSG:
          serviceImpl.poolBatchDepositMsg((tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgRequest) request,
              (io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchDepositMsgResponse>) responseObserver);
          break;
        case METHODID_POOL_BATCH_WITHDRAW_MSGS:
          serviceImpl.poolBatchWithdrawMsgs((tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsRequest) request,
              (io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgsResponse>) responseObserver);
          break;
        case METHODID_POOL_BATCH_WITHDRAW_MSG:
          serviceImpl.poolBatchWithdrawMsg((tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgRequest) request,
              (io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryPoolBatchWithdrawMsgResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<tendermint.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
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
      return tendermint.liquidity.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getLiquidityPoolsMethod())
              .addMethod(getLiquidityPoolMethod())
              .addMethod(getLiquidityPoolByPoolCoinDenomMethod())
              .addMethod(getLiquidityPoolByReserveAccMethod())
              .addMethod(getLiquidityPoolBatchMethod())
              .addMethod(getPoolBatchSwapMsgsMethod())
              .addMethod(getPoolBatchSwapMsgMethod())
              .addMethod(getPoolBatchDepositMsgsMethod())
              .addMethod(getPoolBatchDepositMsgMethod())
              .addMethod(getPoolBatchWithdrawMsgsMethod())
              .addMethod(getPoolBatchWithdrawMsgMethod())
              .addMethod(getParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
