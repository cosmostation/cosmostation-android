package pstake.liquidstakeibc.v1beta1;

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
    comments = "Source: pstake/liquidstakeibc/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "pstake.liquidstakeibc.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainResponse> getHostChainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HostChain",
      requestType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainRequest.class,
      responseType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainResponse> getHostChainMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainResponse> getHostChainMethod;
    if ((getHostChainMethod = QueryGrpc.getHostChainMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHostChainMethod = QueryGrpc.getHostChainMethod) == null) {
          QueryGrpc.getHostChainMethod = getHostChainMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HostChain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("HostChain"))
              .build();
        }
      }
    }
    return getHostChainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsResponse> getHostChainsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HostChains",
      requestType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsRequest.class,
      responseType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsResponse> getHostChainsMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsResponse> getHostChainsMethod;
    if ((getHostChainsMethod = QueryGrpc.getHostChainsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHostChainsMethod = QueryGrpc.getHostChainsMethod) == null) {
          QueryGrpc.getHostChainsMethod = getHostChainsMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HostChains"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("HostChains"))
              .build();
        }
      }
    }
    return getHostChainsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsResponse> getDepositsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposits",
      requestType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsRequest.class,
      responseType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsResponse> getDepositsMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsResponse> getDepositsMethod;
    if ((getDepositsMethod = QueryGrpc.getDepositsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositsMethod = QueryGrpc.getDepositsMethod) == null) {
          QueryGrpc.getDepositsMethod = getDepositsMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposits"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Deposits"))
              .build();
        }
      }
    }
    return getDepositsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsResponse> getLSMDepositsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LSMDeposits",
      requestType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsRequest.class,
      responseType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsResponse> getLSMDepositsMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsResponse> getLSMDepositsMethod;
    if ((getLSMDepositsMethod = QueryGrpc.getLSMDepositsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLSMDepositsMethod = QueryGrpc.getLSMDepositsMethod) == null) {
          QueryGrpc.getLSMDepositsMethod = getLSMDepositsMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LSMDeposits"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LSMDeposits"))
              .build();
        }
      }
    }
    return getLSMDepositsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsResponse> getUnbondingsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Unbondings",
      requestType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsRequest.class,
      responseType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsResponse> getUnbondingsMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsResponse> getUnbondingsMethod;
    if ((getUnbondingsMethod = QueryGrpc.getUnbondingsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnbondingsMethod = QueryGrpc.getUnbondingsMethod) == null) {
          QueryGrpc.getUnbondingsMethod = getUnbondingsMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Unbondings"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Unbondings"))
              .build();
        }
      }
    }
    return getUnbondingsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingResponse> getUnbondingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Unbonding",
      requestType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingRequest.class,
      responseType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingResponse> getUnbondingMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingResponse> getUnbondingMethod;
    if ((getUnbondingMethod = QueryGrpc.getUnbondingMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnbondingMethod = QueryGrpc.getUnbondingMethod) == null) {
          QueryGrpc.getUnbondingMethod = getUnbondingMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Unbonding"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Unbonding"))
              .build();
        }
      }
    }
    return getUnbondingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsResponse> getUserUnbondingsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UserUnbondings",
      requestType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsRequest.class,
      responseType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsResponse> getUserUnbondingsMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsResponse> getUserUnbondingsMethod;
    if ((getUserUnbondingsMethod = QueryGrpc.getUserUnbondingsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUserUnbondingsMethod = QueryGrpc.getUserUnbondingsMethod) == null) {
          QueryGrpc.getUserUnbondingsMethod = getUserUnbondingsMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UserUnbondings"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UserUnbondings"))
              .build();
        }
      }
    }
    return getUserUnbondingsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingResponse> getValidatorUnbondingsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidatorUnbondings",
      requestType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingRequest.class,
      responseType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingResponse> getValidatorUnbondingsMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingResponse> getValidatorUnbondingsMethod;
    if ((getValidatorUnbondingsMethod = QueryGrpc.getValidatorUnbondingsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorUnbondingsMethod = QueryGrpc.getValidatorUnbondingsMethod) == null) {
          QueryGrpc.getValidatorUnbondingsMethod = getValidatorUnbondingsMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidatorUnbondings"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValidatorUnbondings"))
              .build();
        }
      }
    }
    return getValidatorUnbondingsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceResponse> getDepositAccountBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DepositAccountBalance",
      requestType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceRequest.class,
      responseType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceResponse> getDepositAccountBalanceMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceResponse> getDepositAccountBalanceMethod;
    if ((getDepositAccountBalanceMethod = QueryGrpc.getDepositAccountBalanceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositAccountBalanceMethod = QueryGrpc.getDepositAccountBalanceMethod) == null) {
          QueryGrpc.getDepositAccountBalanceMethod = getDepositAccountBalanceMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DepositAccountBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DepositAccountBalance"))
              .build();
        }
      }
    }
    return getDepositAccountBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateResponse> getExchangeRateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExchangeRate",
      requestType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateRequest.class,
      responseType = pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateRequest,
      pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateResponse> getExchangeRateMethod() {
    io.grpc.MethodDescriptor<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateResponse> getExchangeRateMethod;
    if ((getExchangeRateMethod = QueryGrpc.getExchangeRateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getExchangeRateMethod = QueryGrpc.getExchangeRateMethod) == null) {
          QueryGrpc.getExchangeRateMethod = getExchangeRateMethod =
              io.grpc.MethodDescriptor.<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateRequest, pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExchangeRate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ExchangeRate"))
              .build();
        }
      }
    }
    return getExchangeRateMethod;
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
     * Queries the parameters of the module.
     * </pre>
     */
    public void params(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a HostChain by id.
     * </pre>
     */
    public void hostChain(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHostChainMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries for all the HostChains.
     * </pre>
     */
    public void hostChains(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHostChainsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries for all the deposits for a host chain.
     * </pre>
     */
    public void deposits(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries for all the deposits for a host chain.
     * </pre>
     */
    public void lSMDeposits(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLSMDepositsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries all unbondings for a host chain.
     * </pre>
     */
    public void unbondings(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnbondingsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries an unbonding for a host chain.
     * </pre>
     */
    public void unbonding(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnbondingMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries all unbondings for a delegator address.
     * </pre>
     */
    public void userUnbondings(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUserUnbondingsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries all validator unbondings for a host chain.
     * </pre>
     */
    public void validatorUnbondings(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValidatorUnbondingsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries for a host chain deposit account balance.
     * </pre>
     */
    public void depositAccountBalance(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositAccountBalanceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries for a host chain exchange rate between the host token and the stk token.
     * </pre>
     */
    public void exchangeRate(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getExchangeRateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsRequest,
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getHostChainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainRequest,
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainResponse>(
                  this, METHODID_HOST_CHAIN)))
          .addMethod(
            getHostChainsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsRequest,
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsResponse>(
                  this, METHODID_HOST_CHAINS)))
          .addMethod(
            getDepositsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsRequest,
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsResponse>(
                  this, METHODID_DEPOSITS)))
          .addMethod(
            getLSMDepositsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsRequest,
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsResponse>(
                  this, METHODID_LSMDEPOSITS)))
          .addMethod(
            getUnbondingsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsRequest,
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsResponse>(
                  this, METHODID_UNBONDINGS)))
          .addMethod(
            getUnbondingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingRequest,
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingResponse>(
                  this, METHODID_UNBONDING)))
          .addMethod(
            getUserUnbondingsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsRequest,
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsResponse>(
                  this, METHODID_USER_UNBONDINGS)))
          .addMethod(
            getValidatorUnbondingsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingRequest,
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingResponse>(
                  this, METHODID_VALIDATOR_UNBONDINGS)))
          .addMethod(
            getDepositAccountBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceRequest,
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceResponse>(
                  this, METHODID_DEPOSIT_ACCOUNT_BALANCE)))
          .addMethod(
            getExchangeRateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateRequest,
                pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateResponse>(
                  this, METHODID_EXCHANGE_RATE)))
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
     * Queries the parameters of the module.
     * </pre>
     */
    public void params(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a HostChain by id.
     * </pre>
     */
    public void hostChain(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHostChainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries for all the HostChains.
     * </pre>
     */
    public void hostChains(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHostChainsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries for all the deposits for a host chain.
     * </pre>
     */
    public void deposits(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries for all the deposits for a host chain.
     * </pre>
     */
    public void lSMDeposits(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLSMDepositsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries all unbondings for a host chain.
     * </pre>
     */
    public void unbondings(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnbondingsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries an unbonding for a host chain.
     * </pre>
     */
    public void unbonding(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnbondingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries all unbondings for a delegator address.
     * </pre>
     */
    public void userUnbondings(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUserUnbondingsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries all validator unbondings for a host chain.
     * </pre>
     */
    public void validatorUnbondings(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValidatorUnbondingsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries for a host chain deposit account balance.
     * </pre>
     */
    public void depositAccountBalance(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositAccountBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries for a host chain exchange rate between the host token and the stk token.
     * </pre>
     */
    public void exchangeRate(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateRequest request,
        io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExchangeRateMethod(), getCallOptions()), request, responseObserver);
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
     * Queries the parameters of the module.
     * </pre>
     */
    public pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsResponse params(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a HostChain by id.
     * </pre>
     */
    public pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainResponse hostChain(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainRequest request) {
      return blockingUnaryCall(
          getChannel(), getHostChainMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries for all the HostChains.
     * </pre>
     */
    public pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsResponse hostChains(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsRequest request) {
      return blockingUnaryCall(
          getChannel(), getHostChainsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries for all the deposits for a host chain.
     * </pre>
     */
    public pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsResponse deposits(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsRequest request) {
      return blockingUnaryCall(
          getChannel(), getDepositsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries for all the deposits for a host chain.
     * </pre>
     */
    public pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsResponse lSMDeposits(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsRequest request) {
      return blockingUnaryCall(
          getChannel(), getLSMDepositsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries all unbondings for a host chain.
     * </pre>
     */
    public pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsResponse unbondings(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnbondingsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries an unbonding for a host chain.
     * </pre>
     */
    public pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingResponse unbonding(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnbondingMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries all unbondings for a delegator address.
     * </pre>
     */
    public pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsResponse userUnbondings(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsRequest request) {
      return blockingUnaryCall(
          getChannel(), getUserUnbondingsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries all validator unbondings for a host chain.
     * </pre>
     */
    public pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingResponse validatorUnbondings(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingRequest request) {
      return blockingUnaryCall(
          getChannel(), getValidatorUnbondingsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries for a host chain deposit account balance.
     * </pre>
     */
    public pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceResponse depositAccountBalance(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceRequest request) {
      return blockingUnaryCall(
          getChannel(), getDepositAccountBalanceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries for a host chain exchange rate between the host token and the stk token.
     * </pre>
     */
    public pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateResponse exchangeRate(pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateRequest request) {
      return blockingUnaryCall(
          getChannel(), getExchangeRateMethod(), getCallOptions(), request);
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
     * Queries the parameters of the module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a HostChain by id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainResponse> hostChain(
        pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHostChainMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries for all the HostChains.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsResponse> hostChains(
        pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHostChainsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries for all the deposits for a host chain.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsResponse> deposits(
        pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries for all the deposits for a host chain.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsResponse> lSMDeposits(
        pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLSMDepositsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries all unbondings for a host chain.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsResponse> unbondings(
        pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnbondingsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries an unbonding for a host chain.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingResponse> unbonding(
        pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnbondingMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries all unbondings for a delegator address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsResponse> userUnbondings(
        pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUserUnbondingsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries all validator unbondings for a host chain.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingResponse> validatorUnbondings(
        pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValidatorUnbondingsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries for a host chain deposit account balance.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceResponse> depositAccountBalance(
        pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositAccountBalanceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries for a host chain exchange rate between the host token and the stk token.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateResponse> exchangeRate(
        pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getExchangeRateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_HOST_CHAIN = 1;
  private static final int METHODID_HOST_CHAINS = 2;
  private static final int METHODID_DEPOSITS = 3;
  private static final int METHODID_LSMDEPOSITS = 4;
  private static final int METHODID_UNBONDINGS = 5;
  private static final int METHODID_UNBONDING = 6;
  private static final int METHODID_USER_UNBONDINGS = 7;
  private static final int METHODID_VALIDATOR_UNBONDINGS = 8;
  private static final int METHODID_DEPOSIT_ACCOUNT_BALANCE = 9;
  private static final int METHODID_EXCHANGE_RATE = 10;

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
          serviceImpl.params((pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_HOST_CHAIN:
          serviceImpl.hostChain((pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainRequest) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainResponse>) responseObserver);
          break;
        case METHODID_HOST_CHAINS:
          serviceImpl.hostChains((pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsRequest) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryHostChainsResponse>) responseObserver);
          break;
        case METHODID_DEPOSITS:
          serviceImpl.deposits((pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsRequest) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositsResponse>) responseObserver);
          break;
        case METHODID_LSMDEPOSITS:
          serviceImpl.lSMDeposits((pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsRequest) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryLSMDepositsResponse>) responseObserver);
          break;
        case METHODID_UNBONDINGS:
          serviceImpl.unbondings((pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsRequest) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingsResponse>) responseObserver);
          break;
        case METHODID_UNBONDING:
          serviceImpl.unbonding((pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingRequest) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUnbondingResponse>) responseObserver);
          break;
        case METHODID_USER_UNBONDINGS:
          serviceImpl.userUnbondings((pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsRequest) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryUserUnbondingsResponse>) responseObserver);
          break;
        case METHODID_VALIDATOR_UNBONDINGS:
          serviceImpl.validatorUnbondings((pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingRequest) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryValidatorUnbondingResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT_ACCOUNT_BALANCE:
          serviceImpl.depositAccountBalance((pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceRequest) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryDepositAccountBalanceResponse>) responseObserver);
          break;
        case METHODID_EXCHANGE_RATE:
          serviceImpl.exchangeRate((pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateRequest) request,
              (io.grpc.stub.StreamObserver<pstake.liquidstakeibc.v1beta1.QueryOuterClass.QueryExchangeRateResponse>) responseObserver);
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
      return pstake.liquidstakeibc.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getHostChainMethod())
              .addMethod(getHostChainsMethod())
              .addMethod(getDepositsMethod())
              .addMethod(getLSMDepositsMethod())
              .addMethod(getUnbondingsMethod())
              .addMethod(getUnbondingMethod())
              .addMethod(getUserUnbondingsMethod())
              .addMethod(getValidatorUnbondingsMethod())
              .addMethod(getDepositAccountBalanceMethod())
              .addMethod(getExchangeRateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
