package com.artela.evm.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: artela/evm/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "artela.evm.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryAccountRequest,
      com.artela.evm.v1.QueryProto.QueryAccountResponse> getAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Account",
      requestType = com.artela.evm.v1.QueryProto.QueryAccountRequest.class,
      responseType = com.artela.evm.v1.QueryProto.QueryAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryAccountRequest,
      com.artela.evm.v1.QueryProto.QueryAccountResponse> getAccountMethod() {
    io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryAccountRequest, com.artela.evm.v1.QueryProto.QueryAccountResponse> getAccountMethod;
    if ((getAccountMethod = QueryGrpc.getAccountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountMethod = QueryGrpc.getAccountMethod) == null) {
          QueryGrpc.getAccountMethod = getAccountMethod =
              io.grpc.MethodDescriptor.<com.artela.evm.v1.QueryProto.QueryAccountRequest, com.artela.evm.v1.QueryProto.QueryAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Account"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Account"))
              .build();
        }
      }
    }
    return getAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryCosmosAccountRequest,
      com.artela.evm.v1.QueryProto.QueryCosmosAccountResponse> getCosmosAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CosmosAccount",
      requestType = com.artela.evm.v1.QueryProto.QueryCosmosAccountRequest.class,
      responseType = com.artela.evm.v1.QueryProto.QueryCosmosAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryCosmosAccountRequest,
      com.artela.evm.v1.QueryProto.QueryCosmosAccountResponse> getCosmosAccountMethod() {
    io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryCosmosAccountRequest, com.artela.evm.v1.QueryProto.QueryCosmosAccountResponse> getCosmosAccountMethod;
    if ((getCosmosAccountMethod = QueryGrpc.getCosmosAccountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCosmosAccountMethod = QueryGrpc.getCosmosAccountMethod) == null) {
          QueryGrpc.getCosmosAccountMethod = getCosmosAccountMethod =
              io.grpc.MethodDescriptor.<com.artela.evm.v1.QueryProto.QueryCosmosAccountRequest, com.artela.evm.v1.QueryProto.QueryCosmosAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CosmosAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryCosmosAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryCosmosAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CosmosAccount"))
              .build();
        }
      }
    }
    return getCosmosAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryValidatorAccountRequest,
      com.artela.evm.v1.QueryProto.QueryValidatorAccountResponse> getValidatorAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidatorAccount",
      requestType = com.artela.evm.v1.QueryProto.QueryValidatorAccountRequest.class,
      responseType = com.artela.evm.v1.QueryProto.QueryValidatorAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryValidatorAccountRequest,
      com.artela.evm.v1.QueryProto.QueryValidatorAccountResponse> getValidatorAccountMethod() {
    io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryValidatorAccountRequest, com.artela.evm.v1.QueryProto.QueryValidatorAccountResponse> getValidatorAccountMethod;
    if ((getValidatorAccountMethod = QueryGrpc.getValidatorAccountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorAccountMethod = QueryGrpc.getValidatorAccountMethod) == null) {
          QueryGrpc.getValidatorAccountMethod = getValidatorAccountMethod =
              io.grpc.MethodDescriptor.<com.artela.evm.v1.QueryProto.QueryValidatorAccountRequest, com.artela.evm.v1.QueryProto.QueryValidatorAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidatorAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryValidatorAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryValidatorAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValidatorAccount"))
              .build();
        }
      }
    }
    return getValidatorAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryBalanceRequest,
      com.artela.evm.v1.QueryProto.QueryBalanceResponse> getBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Balance",
      requestType = com.artela.evm.v1.QueryProto.QueryBalanceRequest.class,
      responseType = com.artela.evm.v1.QueryProto.QueryBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryBalanceRequest,
      com.artela.evm.v1.QueryProto.QueryBalanceResponse> getBalanceMethod() {
    io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryBalanceRequest, com.artela.evm.v1.QueryProto.QueryBalanceResponse> getBalanceMethod;
    if ((getBalanceMethod = QueryGrpc.getBalanceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBalanceMethod = QueryGrpc.getBalanceMethod) == null) {
          QueryGrpc.getBalanceMethod = getBalanceMethod =
              io.grpc.MethodDescriptor.<com.artela.evm.v1.QueryProto.QueryBalanceRequest, com.artela.evm.v1.QueryProto.QueryBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Balance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Balance"))
              .build();
        }
      }
    }
    return getBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryStorageRequest,
      com.artela.evm.v1.QueryProto.QueryStorageResponse> getStorageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Storage",
      requestType = com.artela.evm.v1.QueryProto.QueryStorageRequest.class,
      responseType = com.artela.evm.v1.QueryProto.QueryStorageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryStorageRequest,
      com.artela.evm.v1.QueryProto.QueryStorageResponse> getStorageMethod() {
    io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryStorageRequest, com.artela.evm.v1.QueryProto.QueryStorageResponse> getStorageMethod;
    if ((getStorageMethod = QueryGrpc.getStorageMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getStorageMethod = QueryGrpc.getStorageMethod) == null) {
          QueryGrpc.getStorageMethod = getStorageMethod =
              io.grpc.MethodDescriptor.<com.artela.evm.v1.QueryProto.QueryStorageRequest, com.artela.evm.v1.QueryProto.QueryStorageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Storage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryStorageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryStorageResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Storage"))
              .build();
        }
      }
    }
    return getStorageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryCodeRequest,
      com.artela.evm.v1.QueryProto.QueryCodeResponse> getCodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Code",
      requestType = com.artela.evm.v1.QueryProto.QueryCodeRequest.class,
      responseType = com.artela.evm.v1.QueryProto.QueryCodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryCodeRequest,
      com.artela.evm.v1.QueryProto.QueryCodeResponse> getCodeMethod() {
    io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryCodeRequest, com.artela.evm.v1.QueryProto.QueryCodeResponse> getCodeMethod;
    if ((getCodeMethod = QueryGrpc.getCodeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCodeMethod = QueryGrpc.getCodeMethod) == null) {
          QueryGrpc.getCodeMethod = getCodeMethod =
              io.grpc.MethodDescriptor.<com.artela.evm.v1.QueryProto.QueryCodeRequest, com.artela.evm.v1.QueryProto.QueryCodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Code"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryCodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryCodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Code"))
              .build();
        }
      }
    }
    return getCodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryParamsRequest,
      com.artela.evm.v1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.artela.evm.v1.QueryProto.QueryParamsRequest.class,
      responseType = com.artela.evm.v1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryParamsRequest,
      com.artela.evm.v1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryParamsRequest, com.artela.evm.v1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.artela.evm.v1.QueryProto.QueryParamsRequest, com.artela.evm.v1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.EthCallRequest,
      com.artela.evm.v1.TxProto.MsgEthereumTxResponse> getEthCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EthCall",
      requestType = com.artela.evm.v1.QueryProto.EthCallRequest.class,
      responseType = com.artela.evm.v1.TxProto.MsgEthereumTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.EthCallRequest,
      com.artela.evm.v1.TxProto.MsgEthereumTxResponse> getEthCallMethod() {
    io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.EthCallRequest, com.artela.evm.v1.TxProto.MsgEthereumTxResponse> getEthCallMethod;
    if ((getEthCallMethod = QueryGrpc.getEthCallMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEthCallMethod = QueryGrpc.getEthCallMethod) == null) {
          QueryGrpc.getEthCallMethod = getEthCallMethod =
              io.grpc.MethodDescriptor.<com.artela.evm.v1.QueryProto.EthCallRequest, com.artela.evm.v1.TxProto.MsgEthereumTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EthCall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.EthCallRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.TxProto.MsgEthereumTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EthCall"))
              .build();
        }
      }
    }
    return getEthCallMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.EthCallRequest,
      com.artela.evm.v1.QueryProto.EstimateGasResponse> getEstimateGasMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EstimateGas",
      requestType = com.artela.evm.v1.QueryProto.EthCallRequest.class,
      responseType = com.artela.evm.v1.QueryProto.EstimateGasResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.EthCallRequest,
      com.artela.evm.v1.QueryProto.EstimateGasResponse> getEstimateGasMethod() {
    io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.EthCallRequest, com.artela.evm.v1.QueryProto.EstimateGasResponse> getEstimateGasMethod;
    if ((getEstimateGasMethod = QueryGrpc.getEstimateGasMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEstimateGasMethod = QueryGrpc.getEstimateGasMethod) == null) {
          QueryGrpc.getEstimateGasMethod = getEstimateGasMethod =
              io.grpc.MethodDescriptor.<com.artela.evm.v1.QueryProto.EthCallRequest, com.artela.evm.v1.QueryProto.EstimateGasResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EstimateGas"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.EthCallRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.EstimateGasResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EstimateGas"))
              .build();
        }
      }
    }
    return getEstimateGasMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryTraceTxRequest,
      com.artela.evm.v1.QueryProto.QueryTraceTxResponse> getTraceTxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TraceTx",
      requestType = com.artela.evm.v1.QueryProto.QueryTraceTxRequest.class,
      responseType = com.artela.evm.v1.QueryProto.QueryTraceTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryTraceTxRequest,
      com.artela.evm.v1.QueryProto.QueryTraceTxResponse> getTraceTxMethod() {
    io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryTraceTxRequest, com.artela.evm.v1.QueryProto.QueryTraceTxResponse> getTraceTxMethod;
    if ((getTraceTxMethod = QueryGrpc.getTraceTxMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTraceTxMethod = QueryGrpc.getTraceTxMethod) == null) {
          QueryGrpc.getTraceTxMethod = getTraceTxMethod =
              io.grpc.MethodDescriptor.<com.artela.evm.v1.QueryProto.QueryTraceTxRequest, com.artela.evm.v1.QueryProto.QueryTraceTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TraceTx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryTraceTxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryTraceTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TraceTx"))
              .build();
        }
      }
    }
    return getTraceTxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryTraceBlockRequest,
      com.artela.evm.v1.QueryProto.QueryTraceBlockResponse> getTraceBlockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TraceBlock",
      requestType = com.artela.evm.v1.QueryProto.QueryTraceBlockRequest.class,
      responseType = com.artela.evm.v1.QueryProto.QueryTraceBlockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryTraceBlockRequest,
      com.artela.evm.v1.QueryProto.QueryTraceBlockResponse> getTraceBlockMethod() {
    io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryTraceBlockRequest, com.artela.evm.v1.QueryProto.QueryTraceBlockResponse> getTraceBlockMethod;
    if ((getTraceBlockMethod = QueryGrpc.getTraceBlockMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTraceBlockMethod = QueryGrpc.getTraceBlockMethod) == null) {
          QueryGrpc.getTraceBlockMethod = getTraceBlockMethod =
              io.grpc.MethodDescriptor.<com.artela.evm.v1.QueryProto.QueryTraceBlockRequest, com.artela.evm.v1.QueryProto.QueryTraceBlockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TraceBlock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryTraceBlockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryTraceBlockResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TraceBlock"))
              .build();
        }
      }
    }
    return getTraceBlockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryBaseFeeRequest,
      com.artela.evm.v1.QueryProto.QueryBaseFeeResponse> getBaseFeeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BaseFee",
      requestType = com.artela.evm.v1.QueryProto.QueryBaseFeeRequest.class,
      responseType = com.artela.evm.v1.QueryProto.QueryBaseFeeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryBaseFeeRequest,
      com.artela.evm.v1.QueryProto.QueryBaseFeeResponse> getBaseFeeMethod() {
    io.grpc.MethodDescriptor<com.artela.evm.v1.QueryProto.QueryBaseFeeRequest, com.artela.evm.v1.QueryProto.QueryBaseFeeResponse> getBaseFeeMethod;
    if ((getBaseFeeMethod = QueryGrpc.getBaseFeeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBaseFeeMethod = QueryGrpc.getBaseFeeMethod) == null) {
          QueryGrpc.getBaseFeeMethod = getBaseFeeMethod =
              io.grpc.MethodDescriptor.<com.artela.evm.v1.QueryProto.QueryBaseFeeRequest, com.artela.evm.v1.QueryProto.QueryBaseFeeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BaseFee"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryBaseFeeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.QueryBaseFeeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BaseFee"))
              .build();
        }
      }
    }
    return getBaseFeeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.artela.evm.v1.TxProto.MsgEthereumTx,
      com.artela.evm.v1.QueryProto.GetSenderResponse> getGetSenderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetSender",
      requestType = com.artela.evm.v1.TxProto.MsgEthereumTx.class,
      responseType = com.artela.evm.v1.QueryProto.GetSenderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.artela.evm.v1.TxProto.MsgEthereumTx,
      com.artela.evm.v1.QueryProto.GetSenderResponse> getGetSenderMethod() {
    io.grpc.MethodDescriptor<com.artela.evm.v1.TxProto.MsgEthereumTx, com.artela.evm.v1.QueryProto.GetSenderResponse> getGetSenderMethod;
    if ((getGetSenderMethod = QueryGrpc.getGetSenderMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetSenderMethod = QueryGrpc.getGetSenderMethod) == null) {
          QueryGrpc.getGetSenderMethod = getGetSenderMethod =
              io.grpc.MethodDescriptor.<com.artela.evm.v1.TxProto.MsgEthereumTx, com.artela.evm.v1.QueryProto.GetSenderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetSender"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.TxProto.MsgEthereumTx.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.artela.evm.v1.QueryProto.GetSenderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetSender"))
              .build();
        }
      }
    }
    return getGetSenderMethod;
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
  public interface AsyncService {

    /**
     * <pre>
     * Account queries an Ethereum account.
     * </pre>
     */
    default void account(com.artela.evm.v1.QueryProto.QueryAccountRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * CosmosAccount queries an Ethereum account's Cosmos Address.
     * </pre>
     */
    default void cosmosAccount(com.artela.evm.v1.QueryProto.QueryCosmosAccountRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryCosmosAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCosmosAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * ValidatorAccount queries an Ethereum account's from a validator consensus
     * Address.
     * </pre>
     */
    default void validatorAccount(com.artela.evm.v1.QueryProto.QueryValidatorAccountRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryValidatorAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidatorAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * Balance queries the balance of a the EVM denomination for a single
     * EthAccount.
     * </pre>
     */
    default void balance(com.artela.evm.v1.QueryProto.QueryBalanceRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryBalanceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBalanceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Storage queries a single slot of evm state for a single account.
     * </pre>
     */
    default void storage(com.artela.evm.v1.QueryProto.QueryStorageRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryStorageResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStorageMethod(), responseObserver);
    }

    /**
     * <pre>
     * Code queries the balance of all coins for a single account.
     * </pre>
     */
    default void code(com.artela.evm.v1.QueryProto.QueryCodeRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryCodeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCodeMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries the parameters of x/evm module.
     * </pre>
     */
    default void params(com.artela.evm.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * EthCall implements the `eth_call` rpc api
     * </pre>
     */
    default void ethCall(com.artela.evm.v1.QueryProto.EthCallRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.TxProto.MsgEthereumTxResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEthCallMethod(), responseObserver);
    }

    /**
     * <pre>
     * EstimateGas implements the `eth_estimateGas` rpc api
     * </pre>
     */
    default void estimateGas(com.artela.evm.v1.QueryProto.EthCallRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.EstimateGasResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEstimateGasMethod(), responseObserver);
    }

    /**
     * <pre>
     * TraceTx implements the `debug_traceTransaction` rpc api
     * </pre>
     */
    default void traceTx(com.artela.evm.v1.QueryProto.QueryTraceTxRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryTraceTxResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTraceTxMethod(), responseObserver);
    }

    /**
     * <pre>
     * TraceBlock implements the `debug_traceBlockByNumber` and `debug_traceBlockByHash` rpc api
     * </pre>
     */
    default void traceBlock(com.artela.evm.v1.QueryProto.QueryTraceBlockRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryTraceBlockResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTraceBlockMethod(), responseObserver);
    }

    /**
     * <pre>
     * BaseFee queries the base fee of the parent block of the current block,
     * it's similar to feemarket module's method, but also checks london hardfork status.
     * </pre>
     */
    default void baseFee(com.artela.evm.v1.QueryProto.QueryBaseFeeRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryBaseFeeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBaseFeeMethod(), responseObserver);
    }

    /**
     * <pre>
     * GetSender gets sender the tx
     * </pre>
     */
    default void getSender(com.artela.evm.v1.TxProto.MsgEthereumTx request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.GetSenderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSenderMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service.
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
   * Query defines the gRPC querier service.
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
     * Account queries an Ethereum account.
     * </pre>
     */
    public void account(com.artela.evm.v1.QueryProto.QueryAccountRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CosmosAccount queries an Ethereum account's Cosmos Address.
     * </pre>
     */
    public void cosmosAccount(com.artela.evm.v1.QueryProto.QueryCosmosAccountRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryCosmosAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCosmosAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ValidatorAccount queries an Ethereum account's from a validator consensus
     * Address.
     * </pre>
     */
    public void validatorAccount(com.artela.evm.v1.QueryProto.QueryValidatorAccountRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryValidatorAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidatorAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Balance queries the balance of a the EVM denomination for a single
     * EthAccount.
     * </pre>
     */
    public void balance(com.artela.evm.v1.QueryProto.QueryBalanceRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryBalanceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Storage queries a single slot of evm state for a single account.
     * </pre>
     */
    public void storage(com.artela.evm.v1.QueryProto.QueryStorageRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryStorageResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStorageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Code queries the balance of all coins for a single account.
     * </pre>
     */
    public void code(com.artela.evm.v1.QueryProto.QueryCodeRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryCodeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries the parameters of x/evm module.
     * </pre>
     */
    public void params(com.artela.evm.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EthCall implements the `eth_call` rpc api
     * </pre>
     */
    public void ethCall(com.artela.evm.v1.QueryProto.EthCallRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.TxProto.MsgEthereumTxResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEthCallMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EstimateGas implements the `eth_estimateGas` rpc api
     * </pre>
     */
    public void estimateGas(com.artela.evm.v1.QueryProto.EthCallRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.EstimateGasResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEstimateGasMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TraceTx implements the `debug_traceTransaction` rpc api
     * </pre>
     */
    public void traceTx(com.artela.evm.v1.QueryProto.QueryTraceTxRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryTraceTxResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTraceTxMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TraceBlock implements the `debug_traceBlockByNumber` and `debug_traceBlockByHash` rpc api
     * </pre>
     */
    public void traceBlock(com.artela.evm.v1.QueryProto.QueryTraceBlockRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryTraceBlockResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTraceBlockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BaseFee queries the base fee of the parent block of the current block,
     * it's similar to feemarket module's method, but also checks london hardfork status.
     * </pre>
     */
    public void baseFee(com.artela.evm.v1.QueryProto.QueryBaseFeeRequest request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryBaseFeeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBaseFeeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GetSender gets sender the tx
     * </pre>
     */
    public void getSender(com.artela.evm.v1.TxProto.MsgEthereumTx request,
        io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.GetSenderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetSenderMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service.
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
     * Account queries an Ethereum account.
     * </pre>
     */
    public com.artela.evm.v1.QueryProto.QueryAccountResponse account(com.artela.evm.v1.QueryProto.QueryAccountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CosmosAccount queries an Ethereum account's Cosmos Address.
     * </pre>
     */
    public com.artela.evm.v1.QueryProto.QueryCosmosAccountResponse cosmosAccount(com.artela.evm.v1.QueryProto.QueryCosmosAccountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCosmosAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ValidatorAccount queries an Ethereum account's from a validator consensus
     * Address.
     * </pre>
     */
    public com.artela.evm.v1.QueryProto.QueryValidatorAccountResponse validatorAccount(com.artela.evm.v1.QueryProto.QueryValidatorAccountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidatorAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Balance queries the balance of a the EVM denomination for a single
     * EthAccount.
     * </pre>
     */
    public com.artela.evm.v1.QueryProto.QueryBalanceResponse balance(com.artela.evm.v1.QueryProto.QueryBalanceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBalanceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Storage queries a single slot of evm state for a single account.
     * </pre>
     */
    public com.artela.evm.v1.QueryProto.QueryStorageResponse storage(com.artela.evm.v1.QueryProto.QueryStorageRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStorageMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Code queries the balance of all coins for a single account.
     * </pre>
     */
    public com.artela.evm.v1.QueryProto.QueryCodeResponse code(com.artela.evm.v1.QueryProto.QueryCodeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCodeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries the parameters of x/evm module.
     * </pre>
     */
    public com.artela.evm.v1.QueryProto.QueryParamsResponse params(com.artela.evm.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EthCall implements the `eth_call` rpc api
     * </pre>
     */
    public com.artela.evm.v1.TxProto.MsgEthereumTxResponse ethCall(com.artela.evm.v1.QueryProto.EthCallRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEthCallMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EstimateGas implements the `eth_estimateGas` rpc api
     * </pre>
     */
    public com.artela.evm.v1.QueryProto.EstimateGasResponse estimateGas(com.artela.evm.v1.QueryProto.EthCallRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEstimateGasMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TraceTx implements the `debug_traceTransaction` rpc api
     * </pre>
     */
    public com.artela.evm.v1.QueryProto.QueryTraceTxResponse traceTx(com.artela.evm.v1.QueryProto.QueryTraceTxRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTraceTxMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TraceBlock implements the `debug_traceBlockByNumber` and `debug_traceBlockByHash` rpc api
     * </pre>
     */
    public com.artela.evm.v1.QueryProto.QueryTraceBlockResponse traceBlock(com.artela.evm.v1.QueryProto.QueryTraceBlockRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTraceBlockMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BaseFee queries the base fee of the parent block of the current block,
     * it's similar to feemarket module's method, but also checks london hardfork status.
     * </pre>
     */
    public com.artela.evm.v1.QueryProto.QueryBaseFeeResponse baseFee(com.artela.evm.v1.QueryProto.QueryBaseFeeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBaseFeeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GetSender gets sender the tx
     * </pre>
     */
    public com.artela.evm.v1.QueryProto.GetSenderResponse getSender(com.artela.evm.v1.TxProto.MsgEthereumTx request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSenderMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service.
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
     * Account queries an Ethereum account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.artela.evm.v1.QueryProto.QueryAccountResponse> account(
        com.artela.evm.v1.QueryProto.QueryAccountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CosmosAccount queries an Ethereum account's Cosmos Address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.artela.evm.v1.QueryProto.QueryCosmosAccountResponse> cosmosAccount(
        com.artela.evm.v1.QueryProto.QueryCosmosAccountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCosmosAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ValidatorAccount queries an Ethereum account's from a validator consensus
     * Address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.artela.evm.v1.QueryProto.QueryValidatorAccountResponse> validatorAccount(
        com.artela.evm.v1.QueryProto.QueryValidatorAccountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidatorAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Balance queries the balance of a the EVM denomination for a single
     * EthAccount.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.artela.evm.v1.QueryProto.QueryBalanceResponse> balance(
        com.artela.evm.v1.QueryProto.QueryBalanceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Storage queries a single slot of evm state for a single account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.artela.evm.v1.QueryProto.QueryStorageResponse> storage(
        com.artela.evm.v1.QueryProto.QueryStorageRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStorageMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Code queries the balance of all coins for a single account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.artela.evm.v1.QueryProto.QueryCodeResponse> code(
        com.artela.evm.v1.QueryProto.QueryCodeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCodeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries the parameters of x/evm module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.artela.evm.v1.QueryProto.QueryParamsResponse> params(
        com.artela.evm.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EthCall implements the `eth_call` rpc api
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.artela.evm.v1.TxProto.MsgEthereumTxResponse> ethCall(
        com.artela.evm.v1.QueryProto.EthCallRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEthCallMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EstimateGas implements the `eth_estimateGas` rpc api
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.artela.evm.v1.QueryProto.EstimateGasResponse> estimateGas(
        com.artela.evm.v1.QueryProto.EthCallRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEstimateGasMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TraceTx implements the `debug_traceTransaction` rpc api
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.artela.evm.v1.QueryProto.QueryTraceTxResponse> traceTx(
        com.artela.evm.v1.QueryProto.QueryTraceTxRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTraceTxMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TraceBlock implements the `debug_traceBlockByNumber` and `debug_traceBlockByHash` rpc api
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.artela.evm.v1.QueryProto.QueryTraceBlockResponse> traceBlock(
        com.artela.evm.v1.QueryProto.QueryTraceBlockRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTraceBlockMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BaseFee queries the base fee of the parent block of the current block,
     * it's similar to feemarket module's method, but also checks london hardfork status.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.artela.evm.v1.QueryProto.QueryBaseFeeResponse> baseFee(
        com.artela.evm.v1.QueryProto.QueryBaseFeeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBaseFeeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GetSender gets sender the tx
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.artela.evm.v1.QueryProto.GetSenderResponse> getSender(
        com.artela.evm.v1.TxProto.MsgEthereumTx request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetSenderMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ACCOUNT = 0;
  private static final int METHODID_COSMOS_ACCOUNT = 1;
  private static final int METHODID_VALIDATOR_ACCOUNT = 2;
  private static final int METHODID_BALANCE = 3;
  private static final int METHODID_STORAGE = 4;
  private static final int METHODID_CODE = 5;
  private static final int METHODID_PARAMS = 6;
  private static final int METHODID_ETH_CALL = 7;
  private static final int METHODID_ESTIMATE_GAS = 8;
  private static final int METHODID_TRACE_TX = 9;
  private static final int METHODID_TRACE_BLOCK = 10;
  private static final int METHODID_BASE_FEE = 11;
  private static final int METHODID_GET_SENDER = 12;

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
        case METHODID_ACCOUNT:
          serviceImpl.account((com.artela.evm.v1.QueryProto.QueryAccountRequest) request,
              (io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryAccountResponse>) responseObserver);
          break;
        case METHODID_COSMOS_ACCOUNT:
          serviceImpl.cosmosAccount((com.artela.evm.v1.QueryProto.QueryCosmosAccountRequest) request,
              (io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryCosmosAccountResponse>) responseObserver);
          break;
        case METHODID_VALIDATOR_ACCOUNT:
          serviceImpl.validatorAccount((com.artela.evm.v1.QueryProto.QueryValidatorAccountRequest) request,
              (io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryValidatorAccountResponse>) responseObserver);
          break;
        case METHODID_BALANCE:
          serviceImpl.balance((com.artela.evm.v1.QueryProto.QueryBalanceRequest) request,
              (io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryBalanceResponse>) responseObserver);
          break;
        case METHODID_STORAGE:
          serviceImpl.storage((com.artela.evm.v1.QueryProto.QueryStorageRequest) request,
              (io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryStorageResponse>) responseObserver);
          break;
        case METHODID_CODE:
          serviceImpl.code((com.artela.evm.v1.QueryProto.QueryCodeRequest) request,
              (io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryCodeResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((com.artela.evm.v1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_ETH_CALL:
          serviceImpl.ethCall((com.artela.evm.v1.QueryProto.EthCallRequest) request,
              (io.grpc.stub.StreamObserver<com.artela.evm.v1.TxProto.MsgEthereumTxResponse>) responseObserver);
          break;
        case METHODID_ESTIMATE_GAS:
          serviceImpl.estimateGas((com.artela.evm.v1.QueryProto.EthCallRequest) request,
              (io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.EstimateGasResponse>) responseObserver);
          break;
        case METHODID_TRACE_TX:
          serviceImpl.traceTx((com.artela.evm.v1.QueryProto.QueryTraceTxRequest) request,
              (io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryTraceTxResponse>) responseObserver);
          break;
        case METHODID_TRACE_BLOCK:
          serviceImpl.traceBlock((com.artela.evm.v1.QueryProto.QueryTraceBlockRequest) request,
              (io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryTraceBlockResponse>) responseObserver);
          break;
        case METHODID_BASE_FEE:
          serviceImpl.baseFee((com.artela.evm.v1.QueryProto.QueryBaseFeeRequest) request,
              (io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.QueryBaseFeeResponse>) responseObserver);
          break;
        case METHODID_GET_SENDER:
          serviceImpl.getSender((com.artela.evm.v1.TxProto.MsgEthereumTx) request,
              (io.grpc.stub.StreamObserver<com.artela.evm.v1.QueryProto.GetSenderResponse>) responseObserver);
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
          getAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.artela.evm.v1.QueryProto.QueryAccountRequest,
              com.artela.evm.v1.QueryProto.QueryAccountResponse>(
                service, METHODID_ACCOUNT)))
        .addMethod(
          getCosmosAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.artela.evm.v1.QueryProto.QueryCosmosAccountRequest,
              com.artela.evm.v1.QueryProto.QueryCosmosAccountResponse>(
                service, METHODID_COSMOS_ACCOUNT)))
        .addMethod(
          getValidatorAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.artela.evm.v1.QueryProto.QueryValidatorAccountRequest,
              com.artela.evm.v1.QueryProto.QueryValidatorAccountResponse>(
                service, METHODID_VALIDATOR_ACCOUNT)))
        .addMethod(
          getBalanceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.artela.evm.v1.QueryProto.QueryBalanceRequest,
              com.artela.evm.v1.QueryProto.QueryBalanceResponse>(
                service, METHODID_BALANCE)))
        .addMethod(
          getStorageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.artela.evm.v1.QueryProto.QueryStorageRequest,
              com.artela.evm.v1.QueryProto.QueryStorageResponse>(
                service, METHODID_STORAGE)))
        .addMethod(
          getCodeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.artela.evm.v1.QueryProto.QueryCodeRequest,
              com.artela.evm.v1.QueryProto.QueryCodeResponse>(
                service, METHODID_CODE)))
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.artela.evm.v1.QueryProto.QueryParamsRequest,
              com.artela.evm.v1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getEthCallMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.artela.evm.v1.QueryProto.EthCallRequest,
              com.artela.evm.v1.TxProto.MsgEthereumTxResponse>(
                service, METHODID_ETH_CALL)))
        .addMethod(
          getEstimateGasMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.artela.evm.v1.QueryProto.EthCallRequest,
              com.artela.evm.v1.QueryProto.EstimateGasResponse>(
                service, METHODID_ESTIMATE_GAS)))
        .addMethod(
          getTraceTxMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.artela.evm.v1.QueryProto.QueryTraceTxRequest,
              com.artela.evm.v1.QueryProto.QueryTraceTxResponse>(
                service, METHODID_TRACE_TX)))
        .addMethod(
          getTraceBlockMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.artela.evm.v1.QueryProto.QueryTraceBlockRequest,
              com.artela.evm.v1.QueryProto.QueryTraceBlockResponse>(
                service, METHODID_TRACE_BLOCK)))
        .addMethod(
          getBaseFeeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.artela.evm.v1.QueryProto.QueryBaseFeeRequest,
              com.artela.evm.v1.QueryProto.QueryBaseFeeResponse>(
                service, METHODID_BASE_FEE)))
        .addMethod(
          getGetSenderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.artela.evm.v1.TxProto.MsgEthereumTx,
              com.artela.evm.v1.QueryProto.GetSenderResponse>(
                service, METHODID_GET_SENDER)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.artela.evm.v1.QueryProto.getDescriptor();
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
              .addMethod(getParamsMethod())
              .addMethod(getEthCallMethod())
              .addMethod(getEstimateGasMethod())
              .addMethod(getTraceTxMethod())
              .addMethod(getTraceBlockMethod())
              .addMethod(getBaseFeeMethod())
              .addMethod(getGetSenderMethod())
              .build();
        }
      }
    }
    return result;
  }
}
