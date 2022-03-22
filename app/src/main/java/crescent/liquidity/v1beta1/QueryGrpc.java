package crescent.liquidity.v1beta1;

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
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: crescent/liquidity/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "crescent.liquidity.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsResponse> getPoolsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Pools",
      requestType = crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsRequest.class,
      responseType = crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsResponse> getPoolsMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsResponse> getPoolsMethod;
    if ((getPoolsMethod = QueryGrpc.getPoolsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolsMethod = QueryGrpc.getPoolsMethod) == null) {
          QueryGrpc.getPoolsMethod = getPoolsMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Pools"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Pools"))
              .build();
        }
      }
    }
    return getPoolsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> getPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Pool",
      requestType = crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolRequest.class,
      responseType = crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> getPoolMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> getPoolMethod;
    if ((getPoolMethod = QueryGrpc.getPoolMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolMethod = QueryGrpc.getPoolMethod) == null) {
          QueryGrpc.getPoolMethod = getPoolMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Pool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Pool"))
              .build();
        }
      }
    }
    return getPoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByReserveAddressRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> getPoolByReserveAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PoolByReserveAddress",
      requestType = crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByReserveAddressRequest.class,
      responseType = crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByReserveAddressRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> getPoolByReserveAddressMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByReserveAddressRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> getPoolByReserveAddressMethod;
    if ((getPoolByReserveAddressMethod = QueryGrpc.getPoolByReserveAddressMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolByReserveAddressMethod = QueryGrpc.getPoolByReserveAddressMethod) == null) {
          QueryGrpc.getPoolByReserveAddressMethod = getPoolByReserveAddressMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByReserveAddressRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PoolByReserveAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByReserveAddressRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PoolByReserveAddress"))
              .build();
        }
      }
    }
    return getPoolByReserveAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByPoolCoinDenomRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> getPoolByPoolCoinDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PoolByPoolCoinDenom",
      requestType = crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByPoolCoinDenomRequest.class,
      responseType = crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByPoolCoinDenomRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> getPoolByPoolCoinDenomMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByPoolCoinDenomRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> getPoolByPoolCoinDenomMethod;
    if ((getPoolByPoolCoinDenomMethod = QueryGrpc.getPoolByPoolCoinDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolByPoolCoinDenomMethod = QueryGrpc.getPoolByPoolCoinDenomMethod) == null) {
          QueryGrpc.getPoolByPoolCoinDenomMethod = getPoolByPoolCoinDenomMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByPoolCoinDenomRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PoolByPoolCoinDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByPoolCoinDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PoolByPoolCoinDenom"))
              .build();
        }
      }
    }
    return getPoolByPoolCoinDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsResponse> getPairsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Pairs",
      requestType = crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsRequest.class,
      responseType = crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsResponse> getPairsMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsResponse> getPairsMethod;
    if ((getPairsMethod = QueryGrpc.getPairsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPairsMethod = QueryGrpc.getPairsMethod) == null) {
          QueryGrpc.getPairsMethod = getPairsMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Pairs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Pairs"))
              .build();
        }
      }
    }
    return getPairsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPairRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryPairResponse> getPairMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Pair",
      requestType = crescent.liquidity.v1beta1.QueryOuterClass.QueryPairRequest.class,
      responseType = crescent.liquidity.v1beta1.QueryOuterClass.QueryPairResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPairRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryPairResponse> getPairMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryPairRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryPairResponse> getPairMethod;
    if ((getPairMethod = QueryGrpc.getPairMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPairMethod = QueryGrpc.getPairMethod) == null) {
          QueryGrpc.getPairMethod = getPairMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.QueryOuterClass.QueryPairRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryPairResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Pair"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryPairRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryPairResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Pair"))
              .build();
        }
      }
    }
    return getPairMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsResponse> getDepositRequestsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DepositRequests",
      requestType = crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsRequest.class,
      responseType = crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsResponse> getDepositRequestsMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsResponse> getDepositRequestsMethod;
    if ((getDepositRequestsMethod = QueryGrpc.getDepositRequestsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositRequestsMethod = QueryGrpc.getDepositRequestsMethod) == null) {
          QueryGrpc.getDepositRequestsMethod = getDepositRequestsMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DepositRequests"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DepositRequests"))
              .build();
        }
      }
    }
    return getDepositRequestsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestResponse> getDepositRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DepositRequest",
      requestType = crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestRequest.class,
      responseType = crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestResponse> getDepositRequestMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestResponse> getDepositRequestMethod;
    if ((getDepositRequestMethod = QueryGrpc.getDepositRequestMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositRequestMethod = QueryGrpc.getDepositRequestMethod) == null) {
          QueryGrpc.getDepositRequestMethod = getDepositRequestMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DepositRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DepositRequest"))
              .build();
        }
      }
    }
    return getDepositRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsResponse> getWithdrawRequestsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawRequests",
      requestType = crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsRequest.class,
      responseType = crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsResponse> getWithdrawRequestsMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsResponse> getWithdrawRequestsMethod;
    if ((getWithdrawRequestsMethod = QueryGrpc.getWithdrawRequestsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getWithdrawRequestsMethod = QueryGrpc.getWithdrawRequestsMethod) == null) {
          QueryGrpc.getWithdrawRequestsMethod = getWithdrawRequestsMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawRequests"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("WithdrawRequests"))
              .build();
        }
      }
    }
    return getWithdrawRequestsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestResponse> getWithdrawRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawRequest",
      requestType = crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestRequest.class,
      responseType = crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestResponse> getWithdrawRequestMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestResponse> getWithdrawRequestMethod;
    if ((getWithdrawRequestMethod = QueryGrpc.getWithdrawRequestMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getWithdrawRequestMethod = QueryGrpc.getWithdrawRequestMethod) == null) {
          QueryGrpc.getWithdrawRequestMethod = getWithdrawRequestMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("WithdrawRequest"))
              .build();
        }
      }
    }
    return getWithdrawRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse> getOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Orders",
      requestType = crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersRequest.class,
      responseType = crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse> getOrdersMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse> getOrdersMethod;
    if ((getOrdersMethod = QueryGrpc.getOrdersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOrdersMethod = QueryGrpc.getOrdersMethod) == null) {
          QueryGrpc.getOrdersMethod = getOrdersMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Orders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Orders"))
              .build();
        }
      }
    }
    return getOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderResponse> getOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Order",
      requestType = crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderRequest.class,
      responseType = crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderResponse> getOrderMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderResponse> getOrderMethod;
    if ((getOrderMethod = QueryGrpc.getOrderMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOrderMethod = QueryGrpc.getOrderMethod) == null) {
          QueryGrpc.getOrderMethod = getOrderMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Order"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Order"))
              .build();
        }
      }
    }
    return getOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersByOrdererRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse> getOrdersByOrdererMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OrdersByOrderer",
      requestType = crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersByOrdererRequest.class,
      responseType = crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersByOrdererRequest,
      crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse> getOrdersByOrdererMethod() {
    io.grpc.MethodDescriptor<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersByOrdererRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse> getOrdersByOrdererMethod;
    if ((getOrdersByOrdererMethod = QueryGrpc.getOrdersByOrdererMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOrdersByOrdererMethod = QueryGrpc.getOrdersByOrdererMethod) == null) {
          QueryGrpc.getOrdersByOrdererMethod = getOrdersByOrdererMethod =
              io.grpc.MethodDescriptor.<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersByOrdererRequest, crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OrdersByOrderer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersByOrdererRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OrdersByOrderer"))
              .build();
        }
      }
    }
    return getOrdersByOrdererMethod;
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
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Params returns parameters of the module.
     * </pre>
     */
    public void params(crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Pools returns all liquidity pools.
     * </pre>
     */
    public void pools(crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Pool returns the specific liquidity pool.
     * </pre>
     */
    public void pool(crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolMethod(), responseObserver);
    }

    /**
     * <pre>
     * PoolByReserveAddress returns all pools that correspond to the reserve account.
     * </pre>
     */
    public void poolByReserveAddress(crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByReserveAddressRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolByReserveAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * PoolByPoolCoinDenom returns all pools that correspond to the pool coin denom.
     * </pre>
     */
    public void poolByPoolCoinDenom(crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByPoolCoinDenomRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolByPoolCoinDenomMethod(), responseObserver);
    }

    /**
     * <pre>
     * Pairs returns all liquidity pairs.
     * </pre>
     */
    public void pairs(crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPairsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Pair returns the specific pair.
     * </pre>
     */
    public void pair(crescent.liquidity.v1beta1.QueryOuterClass.QueryPairRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPairResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPairMethod(), responseObserver);
    }

    /**
     * <pre>
     * DepositRequests returns all deposit requests.
     * </pre>
     */
    public void depositRequests(crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositRequestsMethod(), responseObserver);
    }

    /**
     * <pre>
     * DepositRequest returns the specific deposit request.
     * </pre>
     */
    public void depositRequest(crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositRequestMethod(), responseObserver);
    }

    /**
     * <pre>
     * WithdrawRequests returns all withdraw requests.
     * </pre>
     */
    public void withdrawRequests(crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawRequestsMethod(), responseObserver);
    }

    /**
     * <pre>
     * WithdrawRequest returns the specific withdraw request.
     * </pre>
     */
    public void withdrawRequest(crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawRequestMethod(), responseObserver);
    }

    /**
     * <pre>
     * Orders returns all orders within the pair.
     * </pre>
     */
    public void orders(crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * Order returns the specific order.
     * </pre>
     */
    public void order(crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * OrdersByOrderer returns orders made by an orderer.
     * </pre>
     */
    public void ordersByOrderer(crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersByOrdererRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOrdersByOrdererMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest,
                crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getPoolsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsRequest,
                crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsResponse>(
                  this, METHODID_POOLS)))
          .addMethod(
            getPoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolRequest,
                crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse>(
                  this, METHODID_POOL)))
          .addMethod(
            getPoolByReserveAddressMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByReserveAddressRequest,
                crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse>(
                  this, METHODID_POOL_BY_RESERVE_ADDRESS)))
          .addMethod(
            getPoolByPoolCoinDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByPoolCoinDenomRequest,
                crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse>(
                  this, METHODID_POOL_BY_POOL_COIN_DENOM)))
          .addMethod(
            getPairsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsRequest,
                crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsResponse>(
                  this, METHODID_PAIRS)))
          .addMethod(
            getPairMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.QueryOuterClass.QueryPairRequest,
                crescent.liquidity.v1beta1.QueryOuterClass.QueryPairResponse>(
                  this, METHODID_PAIR)))
          .addMethod(
            getDepositRequestsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsRequest,
                crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsResponse>(
                  this, METHODID_DEPOSIT_REQUESTS)))
          .addMethod(
            getDepositRequestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestRequest,
                crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestResponse>(
                  this, METHODID_DEPOSIT_REQUEST)))
          .addMethod(
            getWithdrawRequestsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsRequest,
                crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsResponse>(
                  this, METHODID_WITHDRAW_REQUESTS)))
          .addMethod(
            getWithdrawRequestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestRequest,
                crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestResponse>(
                  this, METHODID_WITHDRAW_REQUEST)))
          .addMethod(
            getOrdersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersRequest,
                crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse>(
                  this, METHODID_ORDERS)))
          .addMethod(
            getOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderRequest,
                crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderResponse>(
                  this, METHODID_ORDER)))
          .addMethod(
            getOrdersByOrdererMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersByOrdererRequest,
                crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse>(
                  this, METHODID_ORDERS_BY_ORDERER)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * Params returns parameters of the module.
     * </pre>
     */
    public void params(crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Pools returns all liquidity pools.
     * </pre>
     */
    public void pools(crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Pool returns the specific liquidity pool.
     * </pre>
     */
    public void pool(crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PoolByReserveAddress returns all pools that correspond to the reserve account.
     * </pre>
     */
    public void poolByReserveAddress(crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByReserveAddressRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolByReserveAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PoolByPoolCoinDenom returns all pools that correspond to the pool coin denom.
     * </pre>
     */
    public void poolByPoolCoinDenom(crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByPoolCoinDenomRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolByPoolCoinDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Pairs returns all liquidity pairs.
     * </pre>
     */
    public void pairs(crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPairsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Pair returns the specific pair.
     * </pre>
     */
    public void pair(crescent.liquidity.v1beta1.QueryOuterClass.QueryPairRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPairResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPairMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DepositRequests returns all deposit requests.
     * </pre>
     */
    public void depositRequests(crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositRequestsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DepositRequest returns the specific deposit request.
     * </pre>
     */
    public void depositRequest(crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WithdrawRequests returns all withdraw requests.
     * </pre>
     */
    public void withdrawRequests(crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawRequestsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WithdrawRequest returns the specific withdraw request.
     * </pre>
     */
    public void withdrawRequest(crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Orders returns all orders within the pair.
     * </pre>
     */
    public void orders(crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Order returns the specific order.
     * </pre>
     */
    public void order(crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * OrdersByOrderer returns orders made by an orderer.
     * </pre>
     */
    public void ordersByOrderer(crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersByOrdererRequest request,
        io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOrdersByOrdererMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * Params returns parameters of the module.
     * </pre>
     */
    public crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse params(crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Pools returns all liquidity pools.
     * </pre>
     */
    public crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsResponse pools(crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Pool returns the specific liquidity pool.
     * </pre>
     */
    public crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse pool(crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PoolByReserveAddress returns all pools that correspond to the reserve account.
     * </pre>
     */
    public crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse poolByReserveAddress(crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByReserveAddressRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolByReserveAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PoolByPoolCoinDenom returns all pools that correspond to the pool coin denom.
     * </pre>
     */
    public crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse poolByPoolCoinDenom(crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByPoolCoinDenomRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolByPoolCoinDenomMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Pairs returns all liquidity pairs.
     * </pre>
     */
    public crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsResponse pairs(crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPairsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Pair returns the specific pair.
     * </pre>
     */
    public crescent.liquidity.v1beta1.QueryOuterClass.QueryPairResponse pair(crescent.liquidity.v1beta1.QueryOuterClass.QueryPairRequest request) {
      return blockingUnaryCall(
          getChannel(), getPairMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DepositRequests returns all deposit requests.
     * </pre>
     */
    public crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsResponse depositRequests(crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsRequest request) {
      return blockingUnaryCall(
          getChannel(), getDepositRequestsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DepositRequest returns the specific deposit request.
     * </pre>
     */
    public crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestResponse depositRequest(crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestRequest request) {
      return blockingUnaryCall(
          getChannel(), getDepositRequestMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WithdrawRequests returns all withdraw requests.
     * </pre>
     */
    public crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsResponse withdrawRequests(crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsRequest request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawRequestsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WithdrawRequest returns the specific withdraw request.
     * </pre>
     */
    public crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestResponse withdrawRequest(crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestRequest request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawRequestMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Orders returns all orders within the pair.
     * </pre>
     */
    public crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse orders(crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersRequest request) {
      return blockingUnaryCall(
          getChannel(), getOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Order returns the specific order.
     * </pre>
     */
    public crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderResponse order(crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderRequest request) {
      return blockingUnaryCall(
          getChannel(), getOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * OrdersByOrderer returns orders made by an orderer.
     * </pre>
     */
    public crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse ordersByOrderer(crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersByOrdererRequest request) {
      return blockingUnaryCall(
          getChannel(), getOrdersByOrdererMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * Params returns parameters of the module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Pools returns all liquidity pools.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsResponse> pools(
        crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Pool returns the specific liquidity pool.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> pool(
        crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PoolByReserveAddress returns all pools that correspond to the reserve account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> poolByReserveAddress(
        crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByReserveAddressRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolByReserveAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PoolByPoolCoinDenom returns all pools that correspond to the pool coin denom.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse> poolByPoolCoinDenom(
        crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByPoolCoinDenomRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolByPoolCoinDenomMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Pairs returns all liquidity pairs.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsResponse> pairs(
        crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPairsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Pair returns the specific pair.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.QueryOuterClass.QueryPairResponse> pair(
        crescent.liquidity.v1beta1.QueryOuterClass.QueryPairRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPairMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DepositRequests returns all deposit requests.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsResponse> depositRequests(
        crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositRequestsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DepositRequest returns the specific deposit request.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestResponse> depositRequest(
        crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositRequestMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WithdrawRequests returns all withdraw requests.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsResponse> withdrawRequests(
        crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawRequestsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WithdrawRequest returns the specific withdraw request.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestResponse> withdrawRequest(
        crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawRequestMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Orders returns all orders within the pair.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse> orders(
        crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Order returns the specific order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderResponse> order(
        crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * OrdersByOrderer returns orders made by an orderer.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse> ordersByOrderer(
        crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersByOrdererRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOrdersByOrdererMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_POOLS = 1;
  private static final int METHODID_POOL = 2;
  private static final int METHODID_POOL_BY_RESERVE_ADDRESS = 3;
  private static final int METHODID_POOL_BY_POOL_COIN_DENOM = 4;
  private static final int METHODID_PAIRS = 5;
  private static final int METHODID_PAIR = 6;
  private static final int METHODID_DEPOSIT_REQUESTS = 7;
  private static final int METHODID_DEPOSIT_REQUEST = 8;
  private static final int METHODID_WITHDRAW_REQUESTS = 9;
  private static final int METHODID_WITHDRAW_REQUEST = 10;
  private static final int METHODID_ORDERS = 11;
  private static final int METHODID_ORDER = 12;
  private static final int METHODID_ORDERS_BY_ORDERER = 13;

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
          serviceImpl.params((crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_POOLS:
          serviceImpl.pools((crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolsResponse>) responseObserver);
          break;
        case METHODID_POOL:
          serviceImpl.pool((crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse>) responseObserver);
          break;
        case METHODID_POOL_BY_RESERVE_ADDRESS:
          serviceImpl.poolByReserveAddress((crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByReserveAddressRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse>) responseObserver);
          break;
        case METHODID_POOL_BY_POOL_COIN_DENOM:
          serviceImpl.poolByPoolCoinDenom((crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolByPoolCoinDenomRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPoolResponse>) responseObserver);
          break;
        case METHODID_PAIRS:
          serviceImpl.pairs((crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPairsResponse>) responseObserver);
          break;
        case METHODID_PAIR:
          serviceImpl.pair((crescent.liquidity.v1beta1.QueryOuterClass.QueryPairRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryPairResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT_REQUESTS:
          serviceImpl.depositRequests((crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestsResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT_REQUEST:
          serviceImpl.depositRequest((crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryDepositRequestResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_REQUESTS:
          serviceImpl.withdrawRequests((crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestsResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_REQUEST:
          serviceImpl.withdrawRequest((crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryWithdrawRequestResponse>) responseObserver);
          break;
        case METHODID_ORDERS:
          serviceImpl.orders((crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse>) responseObserver);
          break;
        case METHODID_ORDER:
          serviceImpl.order((crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrderResponse>) responseObserver);
          break;
        case METHODID_ORDERS_BY_ORDERER:
          serviceImpl.ordersByOrderer((crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersByOrdererRequest) request,
              (io.grpc.stub.StreamObserver<crescent.liquidity.v1beta1.QueryOuterClass.QueryOrdersResponse>) responseObserver);
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
      return crescent.liquidity.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getPoolsMethod())
              .addMethod(getPoolMethod())
              .addMethod(getPoolByReserveAddressMethod())
              .addMethod(getPoolByPoolCoinDenomMethod())
              .addMethod(getPairsMethod())
              .addMethod(getPairMethod())
              .addMethod(getDepositRequestsMethod())
              .addMethod(getDepositRequestMethod())
              .addMethod(getWithdrawRequestsMethod())
              .addMethod(getWithdrawRequestMethod())
              .addMethod(getOrdersMethod())
              .addMethod(getOrderMethod())
              .addMethod(getOrdersByOrdererMethod())
              .build();
        }
      }
    }
    return result;
  }
}
