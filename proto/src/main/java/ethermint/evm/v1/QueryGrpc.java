package ethermint.evm.v1;

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
    comments = "Source: ethermint/evm/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "ethermint.evm.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryAccountRequest,
      ethermint.evm.v1.QueryOuterClass.QueryAccountResponse> getAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Account",
      requestType = ethermint.evm.v1.QueryOuterClass.QueryAccountRequest.class,
      responseType = ethermint.evm.v1.QueryOuterClass.QueryAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryAccountRequest,
      ethermint.evm.v1.QueryOuterClass.QueryAccountResponse> getAccountMethod() {
    io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryAccountRequest, ethermint.evm.v1.QueryOuterClass.QueryAccountResponse> getAccountMethod;
    if ((getAccountMethod = QueryGrpc.getAccountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountMethod = QueryGrpc.getAccountMethod) == null) {
          QueryGrpc.getAccountMethod = getAccountMethod =
              io.grpc.MethodDescriptor.<ethermint.evm.v1.QueryOuterClass.QueryAccountRequest, ethermint.evm.v1.QueryOuterClass.QueryAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Account"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Account"))
              .build();
        }
      }
    }
    return getAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountRequest,
      ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountResponse> getCosmosAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CosmosAccount",
      requestType = ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountRequest.class,
      responseType = ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountRequest,
      ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountResponse> getCosmosAccountMethod() {
    io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountRequest, ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountResponse> getCosmosAccountMethod;
    if ((getCosmosAccountMethod = QueryGrpc.getCosmosAccountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCosmosAccountMethod = QueryGrpc.getCosmosAccountMethod) == null) {
          QueryGrpc.getCosmosAccountMethod = getCosmosAccountMethod =
              io.grpc.MethodDescriptor.<ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountRequest, ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CosmosAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CosmosAccount"))
              .build();
        }
      }
    }
    return getCosmosAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountRequest,
      ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountResponse> getValidatorAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidatorAccount",
      requestType = ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountRequest.class,
      responseType = ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountRequest,
      ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountResponse> getValidatorAccountMethod() {
    io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountRequest, ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountResponse> getValidatorAccountMethod;
    if ((getValidatorAccountMethod = QueryGrpc.getValidatorAccountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorAccountMethod = QueryGrpc.getValidatorAccountMethod) == null) {
          QueryGrpc.getValidatorAccountMethod = getValidatorAccountMethod =
              io.grpc.MethodDescriptor.<ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountRequest, ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidatorAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValidatorAccount"))
              .build();
        }
      }
    }
    return getValidatorAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryBalanceRequest,
      ethermint.evm.v1.QueryOuterClass.QueryBalanceResponse> getBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Balance",
      requestType = ethermint.evm.v1.QueryOuterClass.QueryBalanceRequest.class,
      responseType = ethermint.evm.v1.QueryOuterClass.QueryBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryBalanceRequest,
      ethermint.evm.v1.QueryOuterClass.QueryBalanceResponse> getBalanceMethod() {
    io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryBalanceRequest, ethermint.evm.v1.QueryOuterClass.QueryBalanceResponse> getBalanceMethod;
    if ((getBalanceMethod = QueryGrpc.getBalanceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBalanceMethod = QueryGrpc.getBalanceMethod) == null) {
          QueryGrpc.getBalanceMethod = getBalanceMethod =
              io.grpc.MethodDescriptor.<ethermint.evm.v1.QueryOuterClass.QueryBalanceRequest, ethermint.evm.v1.QueryOuterClass.QueryBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Balance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Balance"))
              .build();
        }
      }
    }
    return getBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryStorageRequest,
      ethermint.evm.v1.QueryOuterClass.QueryStorageResponse> getStorageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Storage",
      requestType = ethermint.evm.v1.QueryOuterClass.QueryStorageRequest.class,
      responseType = ethermint.evm.v1.QueryOuterClass.QueryStorageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryStorageRequest,
      ethermint.evm.v1.QueryOuterClass.QueryStorageResponse> getStorageMethod() {
    io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryStorageRequest, ethermint.evm.v1.QueryOuterClass.QueryStorageResponse> getStorageMethod;
    if ((getStorageMethod = QueryGrpc.getStorageMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getStorageMethod = QueryGrpc.getStorageMethod) == null) {
          QueryGrpc.getStorageMethod = getStorageMethod =
              io.grpc.MethodDescriptor.<ethermint.evm.v1.QueryOuterClass.QueryStorageRequest, ethermint.evm.v1.QueryOuterClass.QueryStorageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Storage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryStorageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryStorageResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Storage"))
              .build();
        }
      }
    }
    return getStorageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryCodeRequest,
      ethermint.evm.v1.QueryOuterClass.QueryCodeResponse> getCodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Code",
      requestType = ethermint.evm.v1.QueryOuterClass.QueryCodeRequest.class,
      responseType = ethermint.evm.v1.QueryOuterClass.QueryCodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryCodeRequest,
      ethermint.evm.v1.QueryOuterClass.QueryCodeResponse> getCodeMethod() {
    io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryCodeRequest, ethermint.evm.v1.QueryOuterClass.QueryCodeResponse> getCodeMethod;
    if ((getCodeMethod = QueryGrpc.getCodeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCodeMethod = QueryGrpc.getCodeMethod) == null) {
          QueryGrpc.getCodeMethod = getCodeMethod =
              io.grpc.MethodDescriptor.<ethermint.evm.v1.QueryOuterClass.QueryCodeRequest, ethermint.evm.v1.QueryOuterClass.QueryCodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Code"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryCodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryCodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Code"))
              .build();
        }
      }
    }
    return getCodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryTxLogsRequest,
      ethermint.evm.v1.QueryOuterClass.QueryTxLogsResponse> getTxLogsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TxLogs",
      requestType = ethermint.evm.v1.QueryOuterClass.QueryTxLogsRequest.class,
      responseType = ethermint.evm.v1.QueryOuterClass.QueryTxLogsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryTxLogsRequest,
      ethermint.evm.v1.QueryOuterClass.QueryTxLogsResponse> getTxLogsMethod() {
    io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryTxLogsRequest, ethermint.evm.v1.QueryOuterClass.QueryTxLogsResponse> getTxLogsMethod;
    if ((getTxLogsMethod = QueryGrpc.getTxLogsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTxLogsMethod = QueryGrpc.getTxLogsMethod) == null) {
          QueryGrpc.getTxLogsMethod = getTxLogsMethod =
              io.grpc.MethodDescriptor.<ethermint.evm.v1.QueryOuterClass.QueryTxLogsRequest, ethermint.evm.v1.QueryOuterClass.QueryTxLogsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TxLogs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryTxLogsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryTxLogsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TxLogs"))
              .build();
        }
      }
    }
    return getTxLogsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryBlockLogsRequest,
      ethermint.evm.v1.QueryOuterClass.QueryBlockLogsResponse> getBlockLogsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BlockLogs",
      requestType = ethermint.evm.v1.QueryOuterClass.QueryBlockLogsRequest.class,
      responseType = ethermint.evm.v1.QueryOuterClass.QueryBlockLogsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryBlockLogsRequest,
      ethermint.evm.v1.QueryOuterClass.QueryBlockLogsResponse> getBlockLogsMethod() {
    io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryBlockLogsRequest, ethermint.evm.v1.QueryOuterClass.QueryBlockLogsResponse> getBlockLogsMethod;
    if ((getBlockLogsMethod = QueryGrpc.getBlockLogsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBlockLogsMethod = QueryGrpc.getBlockLogsMethod) == null) {
          QueryGrpc.getBlockLogsMethod = getBlockLogsMethod =
              io.grpc.MethodDescriptor.<ethermint.evm.v1.QueryOuterClass.QueryBlockLogsRequest, ethermint.evm.v1.QueryOuterClass.QueryBlockLogsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BlockLogs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryBlockLogsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryBlockLogsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BlockLogs"))
              .build();
        }
      }
    }
    return getBlockLogsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryBlockBloomRequest,
      ethermint.evm.v1.QueryOuterClass.QueryBlockBloomResponse> getBlockBloomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BlockBloom",
      requestType = ethermint.evm.v1.QueryOuterClass.QueryBlockBloomRequest.class,
      responseType = ethermint.evm.v1.QueryOuterClass.QueryBlockBloomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryBlockBloomRequest,
      ethermint.evm.v1.QueryOuterClass.QueryBlockBloomResponse> getBlockBloomMethod() {
    io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryBlockBloomRequest, ethermint.evm.v1.QueryOuterClass.QueryBlockBloomResponse> getBlockBloomMethod;
    if ((getBlockBloomMethod = QueryGrpc.getBlockBloomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBlockBloomMethod = QueryGrpc.getBlockBloomMethod) == null) {
          QueryGrpc.getBlockBloomMethod = getBlockBloomMethod =
              io.grpc.MethodDescriptor.<ethermint.evm.v1.QueryOuterClass.QueryBlockBloomRequest, ethermint.evm.v1.QueryOuterClass.QueryBlockBloomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BlockBloom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryBlockBloomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryBlockBloomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BlockBloom"))
              .build();
        }
      }
    }
    return getBlockBloomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryParamsRequest,
      ethermint.evm.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = ethermint.evm.v1.QueryOuterClass.QueryParamsRequest.class,
      responseType = ethermint.evm.v1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryParamsRequest,
      ethermint.evm.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.QueryParamsRequest, ethermint.evm.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<ethermint.evm.v1.QueryOuterClass.QueryParamsRequest, ethermint.evm.v1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.EthCallRequest,
      ethermint.evm.v1.Tx.MsgEthereumTxResponse> getEthCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EthCall",
      requestType = ethermint.evm.v1.QueryOuterClass.EthCallRequest.class,
      responseType = ethermint.evm.v1.Tx.MsgEthereumTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.EthCallRequest,
      ethermint.evm.v1.Tx.MsgEthereumTxResponse> getEthCallMethod() {
    io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.EthCallRequest, ethermint.evm.v1.Tx.MsgEthereumTxResponse> getEthCallMethod;
    if ((getEthCallMethod = QueryGrpc.getEthCallMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEthCallMethod = QueryGrpc.getEthCallMethod) == null) {
          QueryGrpc.getEthCallMethod = getEthCallMethod =
              io.grpc.MethodDescriptor.<ethermint.evm.v1.QueryOuterClass.EthCallRequest, ethermint.evm.v1.Tx.MsgEthereumTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EthCall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.EthCallRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.Tx.MsgEthereumTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EthCall"))
              .build();
        }
      }
    }
    return getEthCallMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.EthCallRequest,
      ethermint.evm.v1.QueryOuterClass.EstimateGasResponse> getEstimateGasMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EstimateGas",
      requestType = ethermint.evm.v1.QueryOuterClass.EthCallRequest.class,
      responseType = ethermint.evm.v1.QueryOuterClass.EstimateGasResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.EthCallRequest,
      ethermint.evm.v1.QueryOuterClass.EstimateGasResponse> getEstimateGasMethod() {
    io.grpc.MethodDescriptor<ethermint.evm.v1.QueryOuterClass.EthCallRequest, ethermint.evm.v1.QueryOuterClass.EstimateGasResponse> getEstimateGasMethod;
    if ((getEstimateGasMethod = QueryGrpc.getEstimateGasMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEstimateGasMethod = QueryGrpc.getEstimateGasMethod) == null) {
          QueryGrpc.getEstimateGasMethod = getEstimateGasMethod =
              io.grpc.MethodDescriptor.<ethermint.evm.v1.QueryOuterClass.EthCallRequest, ethermint.evm.v1.QueryOuterClass.EstimateGasResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EstimateGas"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.EthCallRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ethermint.evm.v1.QueryOuterClass.EstimateGasResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EstimateGas"))
              .build();
        }
      }
    }
    return getEstimateGasMethod;
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
     * Account queries an Ethereum account.
     * </pre>
     */
    public void account(ethermint.evm.v1.QueryOuterClass.QueryAccountRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * CosmosAccount queries an Ethereum account's Cosmos Address.
     * </pre>
     */
    public void cosmosAccount(ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCosmosAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * ValidatorAccount queries an Ethereum account's from a validator consensus
     * Address.
     * </pre>
     */
    public void validatorAccount(ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValidatorAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * Balance queries the balance of a the EVM denomination for a single
     * EthAccount.
     * </pre>
     */
    public void balance(ethermint.evm.v1.QueryOuterClass.QueryBalanceRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryBalanceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBalanceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Storage queries the balance of all coins for a single account.
     * </pre>
     */
    public void storage(ethermint.evm.v1.QueryOuterClass.QueryStorageRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryStorageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStorageMethod(), responseObserver);
    }

    /**
     * <pre>
     * Code queries the balance of all coins for a single account.
     * </pre>
     */
    public void code(ethermint.evm.v1.QueryOuterClass.QueryCodeRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryCodeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCodeMethod(), responseObserver);
    }

    /**
     * <pre>
     * TxLogs queries ethereum logs from a transaction.
     * </pre>
     */
    public void txLogs(ethermint.evm.v1.QueryOuterClass.QueryTxLogsRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryTxLogsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTxLogsMethod(), responseObserver);
    }

    /**
     * <pre>
     * BlockLogs queries all the ethereum logs for a given block hash.
     * </pre>
     */
    public void blockLogs(ethermint.evm.v1.QueryOuterClass.QueryBlockLogsRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryBlockLogsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBlockLogsMethod(), responseObserver);
    }

    /**
     * <pre>
     * BlockBloom queries the block bloom filter bytes at a given height.
     * </pre>
     */
    public void blockBloom(ethermint.evm.v1.QueryOuterClass.QueryBlockBloomRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryBlockBloomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBlockBloomMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries the parameters of x/evm module.
     * </pre>
     */
    public void params(ethermint.evm.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * EthCall implements the `eth_call` rpc api
     * </pre>
     */
    public void ethCall(ethermint.evm.v1.QueryOuterClass.EthCallRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.Tx.MsgEthereumTxResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEthCallMethod(), responseObserver);
    }

    /**
     * <pre>
     * EstimateGas implements the `eth_estimateGas` rpc api
     * </pre>
     */
    public void estimateGas(ethermint.evm.v1.QueryOuterClass.EthCallRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.EstimateGasResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEstimateGasMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ethermint.evm.v1.QueryOuterClass.QueryAccountRequest,
                ethermint.evm.v1.QueryOuterClass.QueryAccountResponse>(
                  this, METHODID_ACCOUNT)))
          .addMethod(
            getCosmosAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountRequest,
                ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountResponse>(
                  this, METHODID_COSMOS_ACCOUNT)))
          .addMethod(
            getValidatorAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountRequest,
                ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountResponse>(
                  this, METHODID_VALIDATOR_ACCOUNT)))
          .addMethod(
            getBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ethermint.evm.v1.QueryOuterClass.QueryBalanceRequest,
                ethermint.evm.v1.QueryOuterClass.QueryBalanceResponse>(
                  this, METHODID_BALANCE)))
          .addMethod(
            getStorageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ethermint.evm.v1.QueryOuterClass.QueryStorageRequest,
                ethermint.evm.v1.QueryOuterClass.QueryStorageResponse>(
                  this, METHODID_STORAGE)))
          .addMethod(
            getCodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ethermint.evm.v1.QueryOuterClass.QueryCodeRequest,
                ethermint.evm.v1.QueryOuterClass.QueryCodeResponse>(
                  this, METHODID_CODE)))
          .addMethod(
            getTxLogsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ethermint.evm.v1.QueryOuterClass.QueryTxLogsRequest,
                ethermint.evm.v1.QueryOuterClass.QueryTxLogsResponse>(
                  this, METHODID_TX_LOGS)))
          .addMethod(
            getBlockLogsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ethermint.evm.v1.QueryOuterClass.QueryBlockLogsRequest,
                ethermint.evm.v1.QueryOuterClass.QueryBlockLogsResponse>(
                  this, METHODID_BLOCK_LOGS)))
          .addMethod(
            getBlockBloomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ethermint.evm.v1.QueryOuterClass.QueryBlockBloomRequest,
                ethermint.evm.v1.QueryOuterClass.QueryBlockBloomResponse>(
                  this, METHODID_BLOCK_BLOOM)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ethermint.evm.v1.QueryOuterClass.QueryParamsRequest,
                ethermint.evm.v1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getEthCallMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ethermint.evm.v1.QueryOuterClass.EthCallRequest,
                ethermint.evm.v1.Tx.MsgEthereumTxResponse>(
                  this, METHODID_ETH_CALL)))
          .addMethod(
            getEstimateGasMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ethermint.evm.v1.QueryOuterClass.EthCallRequest,
                ethermint.evm.v1.QueryOuterClass.EstimateGasResponse>(
                  this, METHODID_ESTIMATE_GAS)))
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
     * Account queries an Ethereum account.
     * </pre>
     */
    public void account(ethermint.evm.v1.QueryOuterClass.QueryAccountRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CosmosAccount queries an Ethereum account's Cosmos Address.
     * </pre>
     */
    public void cosmosAccount(ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCosmosAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ValidatorAccount queries an Ethereum account's from a validator consensus
     * Address.
     * </pre>
     */
    public void validatorAccount(ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValidatorAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Balance queries the balance of a the EVM denomination for a single
     * EthAccount.
     * </pre>
     */
    public void balance(ethermint.evm.v1.QueryOuterClass.QueryBalanceRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryBalanceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Storage queries the balance of all coins for a single account.
     * </pre>
     */
    public void storage(ethermint.evm.v1.QueryOuterClass.QueryStorageRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryStorageResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStorageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Code queries the balance of all coins for a single account.
     * </pre>
     */
    public void code(ethermint.evm.v1.QueryOuterClass.QueryCodeRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryCodeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TxLogs queries ethereum logs from a transaction.
     * </pre>
     */
    public void txLogs(ethermint.evm.v1.QueryOuterClass.QueryTxLogsRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryTxLogsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTxLogsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BlockLogs queries all the ethereum logs for a given block hash.
     * </pre>
     */
    public void blockLogs(ethermint.evm.v1.QueryOuterClass.QueryBlockLogsRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryBlockLogsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBlockLogsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BlockBloom queries the block bloom filter bytes at a given height.
     * </pre>
     */
    public void blockBloom(ethermint.evm.v1.QueryOuterClass.QueryBlockBloomRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryBlockBloomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBlockBloomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries the parameters of x/evm module.
     * </pre>
     */
    public void params(ethermint.evm.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EthCall implements the `eth_call` rpc api
     * </pre>
     */
    public void ethCall(ethermint.evm.v1.QueryOuterClass.EthCallRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.Tx.MsgEthereumTxResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEthCallMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EstimateGas implements the `eth_estimateGas` rpc api
     * </pre>
     */
    public void estimateGas(ethermint.evm.v1.QueryOuterClass.EthCallRequest request,
        io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.EstimateGasResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEstimateGasMethod(), getCallOptions()), request, responseObserver);
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
     * Account queries an Ethereum account.
     * </pre>
     */
    public ethermint.evm.v1.QueryOuterClass.QueryAccountResponse account(ethermint.evm.v1.QueryOuterClass.QueryAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CosmosAccount queries an Ethereum account's Cosmos Address.
     * </pre>
     */
    public ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountResponse cosmosAccount(ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getCosmosAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ValidatorAccount queries an Ethereum account's from a validator consensus
     * Address.
     * </pre>
     */
    public ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountResponse validatorAccount(ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getValidatorAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Balance queries the balance of a the EVM denomination for a single
     * EthAccount.
     * </pre>
     */
    public ethermint.evm.v1.QueryOuterClass.QueryBalanceResponse balance(ethermint.evm.v1.QueryOuterClass.QueryBalanceRequest request) {
      return blockingUnaryCall(
          getChannel(), getBalanceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Storage queries the balance of all coins for a single account.
     * </pre>
     */
    public ethermint.evm.v1.QueryOuterClass.QueryStorageResponse storage(ethermint.evm.v1.QueryOuterClass.QueryStorageRequest request) {
      return blockingUnaryCall(
          getChannel(), getStorageMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Code queries the balance of all coins for a single account.
     * </pre>
     */
    public ethermint.evm.v1.QueryOuterClass.QueryCodeResponse code(ethermint.evm.v1.QueryOuterClass.QueryCodeRequest request) {
      return blockingUnaryCall(
          getChannel(), getCodeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TxLogs queries ethereum logs from a transaction.
     * </pre>
     */
    public ethermint.evm.v1.QueryOuterClass.QueryTxLogsResponse txLogs(ethermint.evm.v1.QueryOuterClass.QueryTxLogsRequest request) {
      return blockingUnaryCall(
          getChannel(), getTxLogsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BlockLogs queries all the ethereum logs for a given block hash.
     * </pre>
     */
    public ethermint.evm.v1.QueryOuterClass.QueryBlockLogsResponse blockLogs(ethermint.evm.v1.QueryOuterClass.QueryBlockLogsRequest request) {
      return blockingUnaryCall(
          getChannel(), getBlockLogsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BlockBloom queries the block bloom filter bytes at a given height.
     * </pre>
     */
    public ethermint.evm.v1.QueryOuterClass.QueryBlockBloomResponse blockBloom(ethermint.evm.v1.QueryOuterClass.QueryBlockBloomRequest request) {
      return blockingUnaryCall(
          getChannel(), getBlockBloomMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries the parameters of x/evm module.
     * </pre>
     */
    public ethermint.evm.v1.QueryOuterClass.QueryParamsResponse params(ethermint.evm.v1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EthCall implements the `eth_call` rpc api
     * </pre>
     */
    public ethermint.evm.v1.Tx.MsgEthereumTxResponse ethCall(ethermint.evm.v1.QueryOuterClass.EthCallRequest request) {
      return blockingUnaryCall(
          getChannel(), getEthCallMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EstimateGas implements the `eth_estimateGas` rpc api
     * </pre>
     */
    public ethermint.evm.v1.QueryOuterClass.EstimateGasResponse estimateGas(ethermint.evm.v1.QueryOuterClass.EthCallRequest request) {
      return blockingUnaryCall(
          getChannel(), getEstimateGasMethod(), getCallOptions(), request);
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
     * Account queries an Ethereum account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryAccountResponse> account(
        ethermint.evm.v1.QueryOuterClass.QueryAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CosmosAccount queries an Ethereum account's Cosmos Address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountResponse> cosmosAccount(
        ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCosmosAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ValidatorAccount queries an Ethereum account's from a validator consensus
     * Address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountResponse> validatorAccount(
        ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValidatorAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Balance queries the balance of a the EVM denomination for a single
     * EthAccount.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryBalanceResponse> balance(
        ethermint.evm.v1.QueryOuterClass.QueryBalanceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Storage queries the balance of all coins for a single account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryStorageResponse> storage(
        ethermint.evm.v1.QueryOuterClass.QueryStorageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStorageMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Code queries the balance of all coins for a single account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryCodeResponse> code(
        ethermint.evm.v1.QueryOuterClass.QueryCodeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCodeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TxLogs queries ethereum logs from a transaction.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryTxLogsResponse> txLogs(
        ethermint.evm.v1.QueryOuterClass.QueryTxLogsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTxLogsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BlockLogs queries all the ethereum logs for a given block hash.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryBlockLogsResponse> blockLogs(
        ethermint.evm.v1.QueryOuterClass.QueryBlockLogsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBlockLogsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BlockBloom queries the block bloom filter bytes at a given height.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryBlockBloomResponse> blockBloom(
        ethermint.evm.v1.QueryOuterClass.QueryBlockBloomRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBlockBloomMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries the parameters of x/evm module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryParamsResponse> params(
        ethermint.evm.v1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EthCall implements the `eth_call` rpc api
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ethermint.evm.v1.Tx.MsgEthereumTxResponse> ethCall(
        ethermint.evm.v1.QueryOuterClass.EthCallRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEthCallMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EstimateGas implements the `eth_estimateGas` rpc api
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ethermint.evm.v1.QueryOuterClass.EstimateGasResponse> estimateGas(
        ethermint.evm.v1.QueryOuterClass.EthCallRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEstimateGasMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ACCOUNT = 0;
  private static final int METHODID_COSMOS_ACCOUNT = 1;
  private static final int METHODID_VALIDATOR_ACCOUNT = 2;
  private static final int METHODID_BALANCE = 3;
  private static final int METHODID_STORAGE = 4;
  private static final int METHODID_CODE = 5;
  private static final int METHODID_TX_LOGS = 6;
  private static final int METHODID_BLOCK_LOGS = 7;
  private static final int METHODID_BLOCK_BLOOM = 8;
  private static final int METHODID_PARAMS = 9;
  private static final int METHODID_ETH_CALL = 10;
  private static final int METHODID_ESTIMATE_GAS = 11;

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
        case METHODID_ACCOUNT:
          serviceImpl.account((ethermint.evm.v1.QueryOuterClass.QueryAccountRequest) request,
              (io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryAccountResponse>) responseObserver);
          break;
        case METHODID_COSMOS_ACCOUNT:
          serviceImpl.cosmosAccount((ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountRequest) request,
              (io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountResponse>) responseObserver);
          break;
        case METHODID_VALIDATOR_ACCOUNT:
          serviceImpl.validatorAccount((ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountRequest) request,
              (io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountResponse>) responseObserver);
          break;
        case METHODID_BALANCE:
          serviceImpl.balance((ethermint.evm.v1.QueryOuterClass.QueryBalanceRequest) request,
              (io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryBalanceResponse>) responseObserver);
          break;
        case METHODID_STORAGE:
          serviceImpl.storage((ethermint.evm.v1.QueryOuterClass.QueryStorageRequest) request,
              (io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryStorageResponse>) responseObserver);
          break;
        case METHODID_CODE:
          serviceImpl.code((ethermint.evm.v1.QueryOuterClass.QueryCodeRequest) request,
              (io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryCodeResponse>) responseObserver);
          break;
        case METHODID_TX_LOGS:
          serviceImpl.txLogs((ethermint.evm.v1.QueryOuterClass.QueryTxLogsRequest) request,
              (io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryTxLogsResponse>) responseObserver);
          break;
        case METHODID_BLOCK_LOGS:
          serviceImpl.blockLogs((ethermint.evm.v1.QueryOuterClass.QueryBlockLogsRequest) request,
              (io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryBlockLogsResponse>) responseObserver);
          break;
        case METHODID_BLOCK_BLOOM:
          serviceImpl.blockBloom((ethermint.evm.v1.QueryOuterClass.QueryBlockBloomRequest) request,
              (io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryBlockBloomResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((ethermint.evm.v1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_ETH_CALL:
          serviceImpl.ethCall((ethermint.evm.v1.QueryOuterClass.EthCallRequest) request,
              (io.grpc.stub.StreamObserver<ethermint.evm.v1.Tx.MsgEthereumTxResponse>) responseObserver);
          break;
        case METHODID_ESTIMATE_GAS:
          serviceImpl.estimateGas((ethermint.evm.v1.QueryOuterClass.EthCallRequest) request,
              (io.grpc.stub.StreamObserver<ethermint.evm.v1.QueryOuterClass.EstimateGasResponse>) responseObserver);
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
      return ethermint.evm.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getAccountMethod())
              .addMethod(getCosmosAccountMethod())
              .addMethod(getValidatorAccountMethod())
              .addMethod(getBalanceMethod())
              .addMethod(getStorageMethod())
              .addMethod(getCodeMethod())
              .addMethod(getTxLogsMethod())
              .addMethod(getBlockLogsMethod())
              .addMethod(getBlockBloomMethod())
              .addMethod(getParamsMethod())
              .addMethod(getEthCallMethod())
              .addMethod(getEstimateGasMethod())
              .build();
        }
      }
    }
    return result;
  }
}
