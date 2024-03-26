package com.cosmos.bank.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmos/bank/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "cosmos.bank.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryBalanceRequest,
      com.cosmos.bank.v1beta1.QueryProto.QueryBalanceResponse> getBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Balance",
      requestType = com.cosmos.bank.v1beta1.QueryProto.QueryBalanceRequest.class,
      responseType = com.cosmos.bank.v1beta1.QueryProto.QueryBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryBalanceRequest,
      com.cosmos.bank.v1beta1.QueryProto.QueryBalanceResponse> getBalanceMethod() {
    io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryBalanceRequest, com.cosmos.bank.v1beta1.QueryProto.QueryBalanceResponse> getBalanceMethod;
    if ((getBalanceMethod = QueryGrpc.getBalanceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBalanceMethod = QueryGrpc.getBalanceMethod) == null) {
          QueryGrpc.getBalanceMethod = getBalanceMethod =
              io.grpc.MethodDescriptor.<com.cosmos.bank.v1beta1.QueryProto.QueryBalanceRequest, com.cosmos.bank.v1beta1.QueryProto.QueryBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Balance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QueryBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QueryBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Balance"))
              .build();
        }
      }
    }
    return getBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest,
      com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse> getAllBalancesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllBalances",
      requestType = com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest.class,
      responseType = com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest,
      com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse> getAllBalancesMethod() {
    io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest, com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse> getAllBalancesMethod;
    if ((getAllBalancesMethod = QueryGrpc.getAllBalancesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllBalancesMethod = QueryGrpc.getAllBalancesMethod) == null) {
          QueryGrpc.getAllBalancesMethod = getAllBalancesMethod =
              io.grpc.MethodDescriptor.<com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest, com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllBalances"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllBalances"))
              .build();
        }
      }
    }
    return getAllBalancesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesRequest,
      com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesResponse> getSpendableBalancesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SpendableBalances",
      requestType = com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesRequest.class,
      responseType = com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesRequest,
      com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesResponse> getSpendableBalancesMethod() {
    io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesRequest, com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesResponse> getSpendableBalancesMethod;
    if ((getSpendableBalancesMethod = QueryGrpc.getSpendableBalancesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSpendableBalancesMethod = QueryGrpc.getSpendableBalancesMethod) == null) {
          QueryGrpc.getSpendableBalancesMethod = getSpendableBalancesMethod =
              io.grpc.MethodDescriptor.<com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesRequest, com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SpendableBalances"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SpendableBalances"))
              .build();
        }
      }
    }
    return getSpendableBalancesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomRequest,
      com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomResponse> getSpendableBalanceByDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SpendableBalanceByDenom",
      requestType = com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomRequest.class,
      responseType = com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomRequest,
      com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomResponse> getSpendableBalanceByDenomMethod() {
    io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomRequest, com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomResponse> getSpendableBalanceByDenomMethod;
    if ((getSpendableBalanceByDenomMethod = QueryGrpc.getSpendableBalanceByDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSpendableBalanceByDenomMethod = QueryGrpc.getSpendableBalanceByDenomMethod) == null) {
          QueryGrpc.getSpendableBalanceByDenomMethod = getSpendableBalanceByDenomMethod =
              io.grpc.MethodDescriptor.<com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomRequest, com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SpendableBalanceByDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SpendableBalanceByDenom"))
              .build();
        }
      }
    }
    return getSpendableBalanceByDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyRequest,
      com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyResponse> getTotalSupplyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalSupply",
      requestType = com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyRequest.class,
      responseType = com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyRequest,
      com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyResponse> getTotalSupplyMethod() {
    io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyRequest, com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyResponse> getTotalSupplyMethod;
    if ((getTotalSupplyMethod = QueryGrpc.getTotalSupplyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalSupplyMethod = QueryGrpc.getTotalSupplyMethod) == null) {
          QueryGrpc.getTotalSupplyMethod = getTotalSupplyMethod =
              io.grpc.MethodDescriptor.<com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyRequest, com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalSupply"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalSupply"))
              .build();
        }
      }
    }
    return getTotalSupplyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfRequest,
      com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfResponse> getSupplyOfMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SupplyOf",
      requestType = com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfRequest.class,
      responseType = com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfRequest,
      com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfResponse> getSupplyOfMethod() {
    io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfRequest, com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfResponse> getSupplyOfMethod;
    if ((getSupplyOfMethod = QueryGrpc.getSupplyOfMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSupplyOfMethod = QueryGrpc.getSupplyOfMethod) == null) {
          QueryGrpc.getSupplyOfMethod = getSupplyOfMethod =
              io.grpc.MethodDescriptor.<com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfRequest, com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SupplyOf"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SupplyOf"))
              .build();
        }
      }
    }
    return getSupplyOfMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryParamsRequest,
      com.cosmos.bank.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.cosmos.bank.v1beta1.QueryProto.QueryParamsRequest.class,
      responseType = com.cosmos.bank.v1beta1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryParamsRequest,
      com.cosmos.bank.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryParamsRequest, com.cosmos.bank.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.cosmos.bank.v1beta1.QueryProto.QueryParamsRequest, com.cosmos.bank.v1beta1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataRequest,
      com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataResponse> getDenomMetadataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomMetadata",
      requestType = com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataRequest.class,
      responseType = com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataRequest,
      com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataResponse> getDenomMetadataMethod() {
    io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataRequest, com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataResponse> getDenomMetadataMethod;
    if ((getDenomMetadataMethod = QueryGrpc.getDenomMetadataMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomMetadataMethod = QueryGrpc.getDenomMetadataMethod) == null) {
          QueryGrpc.getDenomMetadataMethod = getDenomMetadataMethod =
              io.grpc.MethodDescriptor.<com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataRequest, com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomMetadata"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomMetadata"))
              .build();
        }
      }
    }
    return getDenomMetadataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataRequest,
      com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataResponse> getDenomsMetadataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomsMetadata",
      requestType = com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataRequest.class,
      responseType = com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataRequest,
      com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataResponse> getDenomsMetadataMethod() {
    io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataRequest, com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataResponse> getDenomsMetadataMethod;
    if ((getDenomsMetadataMethod = QueryGrpc.getDenomsMetadataMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomsMetadataMethod = QueryGrpc.getDenomsMetadataMethod) == null) {
          QueryGrpc.getDenomsMetadataMethod = getDenomsMetadataMethod =
              io.grpc.MethodDescriptor.<com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataRequest, com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomsMetadata"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomsMetadata"))
              .build();
        }
      }
    }
    return getDenomsMetadataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersRequest,
      com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersResponse> getDenomOwnersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomOwners",
      requestType = com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersRequest.class,
      responseType = com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersRequest,
      com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersResponse> getDenomOwnersMethod() {
    io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersRequest, com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersResponse> getDenomOwnersMethod;
    if ((getDenomOwnersMethod = QueryGrpc.getDenomOwnersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomOwnersMethod = QueryGrpc.getDenomOwnersMethod) == null) {
          QueryGrpc.getDenomOwnersMethod = getDenomOwnersMethod =
              io.grpc.MethodDescriptor.<com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersRequest, com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomOwners"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomOwners"))
              .build();
        }
      }
    }
    return getDenomOwnersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledRequest,
      com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledResponse> getSendEnabledMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendEnabled",
      requestType = com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledRequest.class,
      responseType = com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledRequest,
      com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledResponse> getSendEnabledMethod() {
    io.grpc.MethodDescriptor<com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledRequest, com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledResponse> getSendEnabledMethod;
    if ((getSendEnabledMethod = QueryGrpc.getSendEnabledMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSendEnabledMethod = QueryGrpc.getSendEnabledMethod) == null) {
          QueryGrpc.getSendEnabledMethod = getSendEnabledMethod =
              io.grpc.MethodDescriptor.<com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledRequest, com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendEnabled"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SendEnabled"))
              .build();
        }
      }
    }
    return getSendEnabledMethod;
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
     * Balance queries the balance of a single coin for a single account.
     * </pre>
     */
    default void balance(com.cosmos.bank.v1beta1.QueryProto.QueryBalanceRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryBalanceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBalanceMethod(), responseObserver);
    }

    /**
     * <pre>
     * AllBalances queries the balance of all coins for a single account.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    default void allBalances(com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAllBalancesMethod(), responseObserver);
    }

    /**
     * <pre>
     * SpendableBalances queries the spendable balance of all coins for a single
     * account.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    default void spendableBalances(com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSpendableBalancesMethod(), responseObserver);
    }

    /**
     * <pre>
     * SpendableBalanceByDenom queries the spendable balance of a single denom for
     * a single account.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    default void spendableBalanceByDenom(com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSpendableBalanceByDenomMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalSupply queries the total supply of all coins.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    default void totalSupply(com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTotalSupplyMethod(), responseObserver);
    }

    /**
     * <pre>
     * SupplyOf queries the supply of a single coin.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    default void supplyOf(com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSupplyOfMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries the parameters of x/bank module.
     * </pre>
     */
    default void params(com.cosmos.bank.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * DenomsMetadata queries the client metadata of a given coin denomination.
     * </pre>
     */
    default void denomMetadata(com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDenomMetadataMethod(), responseObserver);
    }

    /**
     * <pre>
     * DenomsMetadata queries the client metadata for all registered coin
     * denominations.
     * </pre>
     */
    default void denomsMetadata(com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDenomsMetadataMethod(), responseObserver);
    }

    /**
     * <pre>
     * DenomOwners queries for all account addresses that own a particular token
     * denomination.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    default void denomOwners(com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDenomOwnersMethod(), responseObserver);
    }

    /**
     * <pre>
     * SendEnabled queries for SendEnabled entries.
     * This query only returns denominations that have specific SendEnabled settings.
     * Any denomination that does not have a specific setting will use the default
     * params.default_send_enabled, and will not be returned by this query.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    default void sendEnabled(com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendEnabledMethod(), responseObserver);
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
     * Balance queries the balance of a single coin for a single account.
     * </pre>
     */
    public void balance(com.cosmos.bank.v1beta1.QueryProto.QueryBalanceRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryBalanceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AllBalances queries the balance of all coins for a single account.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public void allBalances(com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAllBalancesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SpendableBalances queries the spendable balance of all coins for a single
     * account.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public void spendableBalances(com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSpendableBalancesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SpendableBalanceByDenom queries the spendable balance of a single denom for
     * a single account.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public void spendableBalanceByDenom(com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSpendableBalanceByDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalSupply queries the total supply of all coins.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public void totalSupply(com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTotalSupplyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SupplyOf queries the supply of a single coin.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public void supplyOf(com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSupplyOfMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries the parameters of x/bank module.
     * </pre>
     */
    public void params(com.cosmos.bank.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DenomsMetadata queries the client metadata of a given coin denomination.
     * </pre>
     */
    public void denomMetadata(com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDenomMetadataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DenomsMetadata queries the client metadata for all registered coin
     * denominations.
     * </pre>
     */
    public void denomsMetadata(com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDenomsMetadataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DenomOwners queries for all account addresses that own a particular token
     * denomination.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public void denomOwners(com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDenomOwnersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SendEnabled queries for SendEnabled entries.
     * This query only returns denominations that have specific SendEnabled settings.
     * Any denomination that does not have a specific setting will use the default
     * params.default_send_enabled, and will not be returned by this query.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public void sendEnabled(com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendEnabledMethod(), getCallOptions()), request, responseObserver);
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
     * Balance queries the balance of a single coin for a single account.
     * </pre>
     */
    public com.cosmos.bank.v1beta1.QueryProto.QueryBalanceResponse balance(com.cosmos.bank.v1beta1.QueryProto.QueryBalanceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBalanceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AllBalances queries the balance of all coins for a single account.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse allBalances(com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAllBalancesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SpendableBalances queries the spendable balance of all coins for a single
     * account.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesResponse spendableBalances(com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSpendableBalancesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SpendableBalanceByDenom queries the spendable balance of a single denom for
     * a single account.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomResponse spendableBalanceByDenom(com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSpendableBalanceByDenomMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalSupply queries the total supply of all coins.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyResponse totalSupply(com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTotalSupplyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SupplyOf queries the supply of a single coin.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfResponse supplyOf(com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSupplyOfMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries the parameters of x/bank module.
     * </pre>
     */
    public com.cosmos.bank.v1beta1.QueryProto.QueryParamsResponse params(com.cosmos.bank.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DenomsMetadata queries the client metadata of a given coin denomination.
     * </pre>
     */
    public com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataResponse denomMetadata(com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDenomMetadataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DenomsMetadata queries the client metadata for all registered coin
     * denominations.
     * </pre>
     */
    public com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataResponse denomsMetadata(com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDenomsMetadataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DenomOwners queries for all account addresses that own a particular token
     * denomination.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersResponse denomOwners(com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDenomOwnersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SendEnabled queries for SendEnabled entries.
     * This query only returns denominations that have specific SendEnabled settings.
     * Any denomination that does not have a specific setting will use the default
     * params.default_send_enabled, and will not be returned by this query.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledResponse sendEnabled(com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendEnabledMethod(), getCallOptions(), request);
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
     * Balance queries the balance of a single coin for a single account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.bank.v1beta1.QueryProto.QueryBalanceResponse> balance(
        com.cosmos.bank.v1beta1.QueryProto.QueryBalanceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AllBalances queries the balance of all coins for a single account.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse> allBalances(
        com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAllBalancesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SpendableBalances queries the spendable balance of all coins for a single
     * account.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesResponse> spendableBalances(
        com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSpendableBalancesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SpendableBalanceByDenom queries the spendable balance of a single denom for
     * a single account.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomResponse> spendableBalanceByDenom(
        com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSpendableBalanceByDenomMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalSupply queries the total supply of all coins.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyResponse> totalSupply(
        com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTotalSupplyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SupplyOf queries the supply of a single coin.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfResponse> supplyOf(
        com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSupplyOfMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries the parameters of x/bank module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.bank.v1beta1.QueryProto.QueryParamsResponse> params(
        com.cosmos.bank.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DenomsMetadata queries the client metadata of a given coin denomination.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataResponse> denomMetadata(
        com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDenomMetadataMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DenomsMetadata queries the client metadata for all registered coin
     * denominations.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataResponse> denomsMetadata(
        com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDenomsMetadataMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DenomOwners queries for all account addresses that own a particular token
     * denomination.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersResponse> denomOwners(
        com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDenomOwnersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SendEnabled queries for SendEnabled entries.
     * This query only returns denominations that have specific SendEnabled settings.
     * Any denomination that does not have a specific setting will use the default
     * params.default_send_enabled, and will not be returned by this query.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledResponse> sendEnabled(
        com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendEnabledMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BALANCE = 0;
  private static final int METHODID_ALL_BALANCES = 1;
  private static final int METHODID_SPENDABLE_BALANCES = 2;
  private static final int METHODID_SPENDABLE_BALANCE_BY_DENOM = 3;
  private static final int METHODID_TOTAL_SUPPLY = 4;
  private static final int METHODID_SUPPLY_OF = 5;
  private static final int METHODID_PARAMS = 6;
  private static final int METHODID_DENOM_METADATA = 7;
  private static final int METHODID_DENOMS_METADATA = 8;
  private static final int METHODID_DENOM_OWNERS = 9;
  private static final int METHODID_SEND_ENABLED = 10;

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
        case METHODID_BALANCE:
          serviceImpl.balance((com.cosmos.bank.v1beta1.QueryProto.QueryBalanceRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryBalanceResponse>) responseObserver);
          break;
        case METHODID_ALL_BALANCES:
          serviceImpl.allBalances((com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse>) responseObserver);
          break;
        case METHODID_SPENDABLE_BALANCES:
          serviceImpl.spendableBalances((com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesResponse>) responseObserver);
          break;
        case METHODID_SPENDABLE_BALANCE_BY_DENOM:
          serviceImpl.spendableBalanceByDenom((com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomResponse>) responseObserver);
          break;
        case METHODID_TOTAL_SUPPLY:
          serviceImpl.totalSupply((com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyResponse>) responseObserver);
          break;
        case METHODID_SUPPLY_OF:
          serviceImpl.supplyOf((com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((com.cosmos.bank.v1beta1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_DENOM_METADATA:
          serviceImpl.denomMetadata((com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataResponse>) responseObserver);
          break;
        case METHODID_DENOMS_METADATA:
          serviceImpl.denomsMetadata((com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataResponse>) responseObserver);
          break;
        case METHODID_DENOM_OWNERS:
          serviceImpl.denomOwners((com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersResponse>) responseObserver);
          break;
        case METHODID_SEND_ENABLED:
          serviceImpl.sendEnabled((com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledResponse>) responseObserver);
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
          getBalanceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.bank.v1beta1.QueryProto.QueryBalanceRequest,
              com.cosmos.bank.v1beta1.QueryProto.QueryBalanceResponse>(
                service, METHODID_BALANCE)))
        .addMethod(
          getAllBalancesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest,
              com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse>(
                service, METHODID_ALL_BALANCES)))
        .addMethod(
          getSpendableBalancesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesRequest,
              com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalancesResponse>(
                service, METHODID_SPENDABLE_BALANCES)))
        .addMethod(
          getSpendableBalanceByDenomMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomRequest,
              com.cosmos.bank.v1beta1.QueryProto.QuerySpendableBalanceByDenomResponse>(
                service, METHODID_SPENDABLE_BALANCE_BY_DENOM)))
        .addMethod(
          getTotalSupplyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyRequest,
              com.cosmos.bank.v1beta1.QueryProto.QueryTotalSupplyResponse>(
                service, METHODID_TOTAL_SUPPLY)))
        .addMethod(
          getSupplyOfMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfRequest,
              com.cosmos.bank.v1beta1.QueryProto.QuerySupplyOfResponse>(
                service, METHODID_SUPPLY_OF)))
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.bank.v1beta1.QueryProto.QueryParamsRequest,
              com.cosmos.bank.v1beta1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getDenomMetadataMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataRequest,
              com.cosmos.bank.v1beta1.QueryProto.QueryDenomMetadataResponse>(
                service, METHODID_DENOM_METADATA)))
        .addMethod(
          getDenomsMetadataMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataRequest,
              com.cosmos.bank.v1beta1.QueryProto.QueryDenomsMetadataResponse>(
                service, METHODID_DENOMS_METADATA)))
        .addMethod(
          getDenomOwnersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersRequest,
              com.cosmos.bank.v1beta1.QueryProto.QueryDenomOwnersResponse>(
                service, METHODID_DENOM_OWNERS)))
        .addMethod(
          getSendEnabledMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledRequest,
              com.cosmos.bank.v1beta1.QueryProto.QuerySendEnabledResponse>(
                service, METHODID_SEND_ENABLED)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmos.bank.v1beta1.QueryProto.getDescriptor();
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
              .addMethod(getBalanceMethod())
              .addMethod(getAllBalancesMethod())
              .addMethod(getSpendableBalancesMethod())
              .addMethod(getSpendableBalanceByDenomMethod())
              .addMethod(getTotalSupplyMethod())
              .addMethod(getSupplyOfMethod())
              .addMethod(getParamsMethod())
              .addMethod(getDenomMetadataMethod())
              .addMethod(getDenomsMetadataMethod())
              .addMethod(getDenomOwnersMethod())
              .addMethod(getSendEnabledMethod())
              .build();
        }
      }
    }
    return result;
  }
}
