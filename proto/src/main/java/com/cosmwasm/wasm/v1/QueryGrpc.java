package com.cosmwasm.wasm.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query provides defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmwasm/wasm/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "cosmwasm.wasm.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoResponse> getContractInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ContractInfo",
      requestType = com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoRequest.class,
      responseType = com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoResponse> getContractInfoMethod() {
    io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoRequest, com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoResponse> getContractInfoMethod;
    if ((getContractInfoMethod = QueryGrpc.getContractInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getContractInfoMethod = QueryGrpc.getContractInfoMethod) == null) {
          QueryGrpc.getContractInfoMethod = getContractInfoMethod =
              io.grpc.MethodDescriptor.<com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoRequest, com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ContractInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ContractInfo"))
              .build();
        }
      }
    }
    return getContractInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryResponse> getContractHistoryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ContractHistory",
      requestType = com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryRequest.class,
      responseType = com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryResponse> getContractHistoryMethod() {
    io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryRequest, com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryResponse> getContractHistoryMethod;
    if ((getContractHistoryMethod = QueryGrpc.getContractHistoryMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getContractHistoryMethod = QueryGrpc.getContractHistoryMethod) == null) {
          QueryGrpc.getContractHistoryMethod = getContractHistoryMethod =
              io.grpc.MethodDescriptor.<com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryRequest, com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ContractHistory"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ContractHistory"))
              .build();
        }
      }
    }
    return getContractHistoryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeResponse> getContractsByCodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ContractsByCode",
      requestType = com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeRequest.class,
      responseType = com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeResponse> getContractsByCodeMethod() {
    io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeRequest, com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeResponse> getContractsByCodeMethod;
    if ((getContractsByCodeMethod = QueryGrpc.getContractsByCodeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getContractsByCodeMethod = QueryGrpc.getContractsByCodeMethod) == null) {
          QueryGrpc.getContractsByCodeMethod = getContractsByCodeMethod =
              io.grpc.MethodDescriptor.<com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeRequest, com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ContractsByCode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ContractsByCode"))
              .build();
        }
      }
    }
    return getContractsByCodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateResponse> getAllContractStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllContractState",
      requestType = com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateRequest.class,
      responseType = com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateResponse> getAllContractStateMethod() {
    io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateRequest, com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateResponse> getAllContractStateMethod;
    if ((getAllContractStateMethod = QueryGrpc.getAllContractStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllContractStateMethod = QueryGrpc.getAllContractStateMethod) == null) {
          QueryGrpc.getAllContractStateMethod = getAllContractStateMethod =
              io.grpc.MethodDescriptor.<com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateRequest, com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllContractState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllContractState"))
              .build();
        }
      }
    }
    return getAllContractStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateResponse> getRawContractStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RawContractState",
      requestType = com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateRequest.class,
      responseType = com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateResponse> getRawContractStateMethod() {
    io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateRequest, com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateResponse> getRawContractStateMethod;
    if ((getRawContractStateMethod = QueryGrpc.getRawContractStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRawContractStateMethod = QueryGrpc.getRawContractStateMethod) == null) {
          QueryGrpc.getRawContractStateMethod = getRawContractStateMethod =
              io.grpc.MethodDescriptor.<com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateRequest, com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RawContractState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RawContractState"))
              .build();
        }
      }
    }
    return getRawContractStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest,
      com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse> getSmartContractStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SmartContractState",
      requestType = com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest.class,
      responseType = com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest,
      com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse> getSmartContractStateMethod() {
    io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest, com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse> getSmartContractStateMethod;
    if ((getSmartContractStateMethod = QueryGrpc.getSmartContractStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSmartContractStateMethod = QueryGrpc.getSmartContractStateMethod) == null) {
          QueryGrpc.getSmartContractStateMethod = getSmartContractStateMethod =
              io.grpc.MethodDescriptor.<com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest, com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SmartContractState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SmartContractState"))
              .build();
        }
      }
    }
    return getSmartContractStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryCodeRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryCodeResponse> getCodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Code",
      requestType = com.cosmwasm.wasm.v1.QueryProto.QueryCodeRequest.class,
      responseType = com.cosmwasm.wasm.v1.QueryProto.QueryCodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryCodeRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryCodeResponse> getCodeMethod() {
    io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryCodeRequest, com.cosmwasm.wasm.v1.QueryProto.QueryCodeResponse> getCodeMethod;
    if ((getCodeMethod = QueryGrpc.getCodeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCodeMethod = QueryGrpc.getCodeMethod) == null) {
          QueryGrpc.getCodeMethod = getCodeMethod =
              io.grpc.MethodDescriptor.<com.cosmwasm.wasm.v1.QueryProto.QueryCodeRequest, com.cosmwasm.wasm.v1.QueryProto.QueryCodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Code"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryCodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryCodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Code"))
              .build();
        }
      }
    }
    return getCodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryCodesRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryCodesResponse> getCodesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Codes",
      requestType = com.cosmwasm.wasm.v1.QueryProto.QueryCodesRequest.class,
      responseType = com.cosmwasm.wasm.v1.QueryProto.QueryCodesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryCodesRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryCodesResponse> getCodesMethod() {
    io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryCodesRequest, com.cosmwasm.wasm.v1.QueryProto.QueryCodesResponse> getCodesMethod;
    if ((getCodesMethod = QueryGrpc.getCodesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCodesMethod = QueryGrpc.getCodesMethod) == null) {
          QueryGrpc.getCodesMethod = getCodesMethod =
              io.grpc.MethodDescriptor.<com.cosmwasm.wasm.v1.QueryProto.QueryCodesRequest, com.cosmwasm.wasm.v1.QueryProto.QueryCodesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Codes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryCodesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryCodesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Codes"))
              .build();
        }
      }
    }
    return getCodesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesResponse> getPinnedCodesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PinnedCodes",
      requestType = com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesRequest.class,
      responseType = com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesResponse> getPinnedCodesMethod() {
    io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesRequest, com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesResponse> getPinnedCodesMethod;
    if ((getPinnedCodesMethod = QueryGrpc.getPinnedCodesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPinnedCodesMethod = QueryGrpc.getPinnedCodesMethod) == null) {
          QueryGrpc.getPinnedCodesMethod = getPinnedCodesMethod =
              io.grpc.MethodDescriptor.<com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesRequest, com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PinnedCodes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PinnedCodes"))
              .build();
        }
      }
    }
    return getPinnedCodesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryParamsRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.cosmwasm.wasm.v1.QueryProto.QueryParamsRequest.class,
      responseType = com.cosmwasm.wasm.v1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryParamsRequest,
      com.cosmwasm.wasm.v1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.cosmwasm.wasm.v1.QueryProto.QueryParamsRequest, com.cosmwasm.wasm.v1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.cosmwasm.wasm.v1.QueryProto.QueryParamsRequest, com.cosmwasm.wasm.v1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmwasm.wasm.v1.QueryProto.QueryParamsResponse.getDefaultInstance()))
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
   * Query provides defines the gRPC querier service
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * ContractInfo gets the contract meta data
     * </pre>
     */
    default void contractInfo(com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getContractInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * ContractHistory gets the contract code history
     * </pre>
     */
    default void contractHistory(com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getContractHistoryMethod(), responseObserver);
    }

    /**
     * <pre>
     * ContractsByCode lists all smart contracts for a code id
     * </pre>
     */
    default void contractsByCode(com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getContractsByCodeMethod(), responseObserver);
    }

    /**
     * <pre>
     * AllContractState gets all raw store data for a single contract
     * </pre>
     */
    default void allContractState(com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAllContractStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * RawContractState gets single key from the raw store data of a contract
     * </pre>
     */
    default void rawContractState(com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRawContractStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * SmartContractState get smart query result from the contract
     * </pre>
     */
    default void smartContractState(com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSmartContractStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Code gets the binary code and metadata for a singe wasm code
     * </pre>
     */
    default void code(com.cosmwasm.wasm.v1.QueryProto.QueryCodeRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryCodeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCodeMethod(), responseObserver);
    }

    /**
     * <pre>
     * Codes gets the metadata for all stored wasm codes
     * </pre>
     */
    default void codes(com.cosmwasm.wasm.v1.QueryProto.QueryCodesRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryCodesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCodesMethod(), responseObserver);
    }

    /**
     * <pre>
     * PinnedCodes gets the pinned code ids
     * </pre>
     */
    default void pinnedCodes(com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPinnedCodesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params gets the module params
     * </pre>
     */
    default void params(com.cosmwasm.wasm.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
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
     * ContractInfo gets the contract meta data
     * </pre>
     */
    public void contractInfo(com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getContractInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ContractHistory gets the contract code history
     * </pre>
     */
    public void contractHistory(com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getContractHistoryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ContractsByCode lists all smart contracts for a code id
     * </pre>
     */
    public void contractsByCode(com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getContractsByCodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AllContractState gets all raw store data for a single contract
     * </pre>
     */
    public void allContractState(com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAllContractStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RawContractState gets single key from the raw store data of a contract
     * </pre>
     */
    public void rawContractState(com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRawContractStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SmartContractState get smart query result from the contract
     * </pre>
     */
    public void smartContractState(com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSmartContractStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Code gets the binary code and metadata for a singe wasm code
     * </pre>
     */
    public void code(com.cosmwasm.wasm.v1.QueryProto.QueryCodeRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryCodeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Codes gets the metadata for all stored wasm codes
     * </pre>
     */
    public void codes(com.cosmwasm.wasm.v1.QueryProto.QueryCodesRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryCodesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCodesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PinnedCodes gets the pinned code ids
     * </pre>
     */
    public void pinnedCodes(com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPinnedCodesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params gets the module params
     * </pre>
     */
    public void params(com.cosmwasm.wasm.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
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
     * ContractInfo gets the contract meta data
     * </pre>
     */
    public com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoResponse contractInfo(com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getContractInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ContractHistory gets the contract code history
     * </pre>
     */
    public com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryResponse contractHistory(com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getContractHistoryMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ContractsByCode lists all smart contracts for a code id
     * </pre>
     */
    public com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeResponse contractsByCode(com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getContractsByCodeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AllContractState gets all raw store data for a single contract
     * </pre>
     */
    public com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateResponse allContractState(com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAllContractStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RawContractState gets single key from the raw store data of a contract
     * </pre>
     */
    public com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateResponse rawContractState(com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRawContractStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SmartContractState get smart query result from the contract
     * </pre>
     */
    public com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse smartContractState(com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSmartContractStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Code gets the binary code and metadata for a singe wasm code
     * </pre>
     */
    public com.cosmwasm.wasm.v1.QueryProto.QueryCodeResponse code(com.cosmwasm.wasm.v1.QueryProto.QueryCodeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCodeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Codes gets the metadata for all stored wasm codes
     * </pre>
     */
    public com.cosmwasm.wasm.v1.QueryProto.QueryCodesResponse codes(com.cosmwasm.wasm.v1.QueryProto.QueryCodesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCodesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PinnedCodes gets the pinned code ids
     * </pre>
     */
    public com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesResponse pinnedCodes(com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPinnedCodesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params gets the module params
     * </pre>
     */
    public com.cosmwasm.wasm.v1.QueryProto.QueryParamsResponse params(com.cosmwasm.wasm.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
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
     * ContractInfo gets the contract meta data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoResponse> contractInfo(
        com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getContractInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ContractHistory gets the contract code history
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryResponse> contractHistory(
        com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getContractHistoryMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ContractsByCode lists all smart contracts for a code id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeResponse> contractsByCode(
        com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getContractsByCodeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AllContractState gets all raw store data for a single contract
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateResponse> allContractState(
        com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAllContractStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RawContractState gets single key from the raw store data of a contract
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateResponse> rawContractState(
        com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRawContractStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SmartContractState get smart query result from the contract
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse> smartContractState(
        com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSmartContractStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Code gets the binary code and metadata for a singe wasm code
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmwasm.wasm.v1.QueryProto.QueryCodeResponse> code(
        com.cosmwasm.wasm.v1.QueryProto.QueryCodeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCodeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Codes gets the metadata for all stored wasm codes
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmwasm.wasm.v1.QueryProto.QueryCodesResponse> codes(
        com.cosmwasm.wasm.v1.QueryProto.QueryCodesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCodesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PinnedCodes gets the pinned code ids
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesResponse> pinnedCodes(
        com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPinnedCodesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params gets the module params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmwasm.wasm.v1.QueryProto.QueryParamsResponse> params(
        com.cosmwasm.wasm.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CONTRACT_INFO = 0;
  private static final int METHODID_CONTRACT_HISTORY = 1;
  private static final int METHODID_CONTRACTS_BY_CODE = 2;
  private static final int METHODID_ALL_CONTRACT_STATE = 3;
  private static final int METHODID_RAW_CONTRACT_STATE = 4;
  private static final int METHODID_SMART_CONTRACT_STATE = 5;
  private static final int METHODID_CODE = 6;
  private static final int METHODID_CODES = 7;
  private static final int METHODID_PINNED_CODES = 8;
  private static final int METHODID_PARAMS = 9;

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
        case METHODID_CONTRACT_INFO:
          serviceImpl.contractInfo((com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoResponse>) responseObserver);
          break;
        case METHODID_CONTRACT_HISTORY:
          serviceImpl.contractHistory((com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryResponse>) responseObserver);
          break;
        case METHODID_CONTRACTS_BY_CODE:
          serviceImpl.contractsByCode((com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeResponse>) responseObserver);
          break;
        case METHODID_ALL_CONTRACT_STATE:
          serviceImpl.allContractState((com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateResponse>) responseObserver);
          break;
        case METHODID_RAW_CONTRACT_STATE:
          serviceImpl.rawContractState((com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateResponse>) responseObserver);
          break;
        case METHODID_SMART_CONTRACT_STATE:
          serviceImpl.smartContractState((com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse>) responseObserver);
          break;
        case METHODID_CODE:
          serviceImpl.code((com.cosmwasm.wasm.v1.QueryProto.QueryCodeRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryCodeResponse>) responseObserver);
          break;
        case METHODID_CODES:
          serviceImpl.codes((com.cosmwasm.wasm.v1.QueryProto.QueryCodesRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryCodesResponse>) responseObserver);
          break;
        case METHODID_PINNED_CODES:
          serviceImpl.pinnedCodes((com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((com.cosmwasm.wasm.v1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmwasm.wasm.v1.QueryProto.QueryParamsResponse>) responseObserver);
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
          getContractInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoRequest,
              com.cosmwasm.wasm.v1.QueryProto.QueryContractInfoResponse>(
                service, METHODID_CONTRACT_INFO)))
        .addMethod(
          getContractHistoryMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryRequest,
              com.cosmwasm.wasm.v1.QueryProto.QueryContractHistoryResponse>(
                service, METHODID_CONTRACT_HISTORY)))
        .addMethod(
          getContractsByCodeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeRequest,
              com.cosmwasm.wasm.v1.QueryProto.QueryContractsByCodeResponse>(
                service, METHODID_CONTRACTS_BY_CODE)))
        .addMethod(
          getAllContractStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateRequest,
              com.cosmwasm.wasm.v1.QueryProto.QueryAllContractStateResponse>(
                service, METHODID_ALL_CONTRACT_STATE)))
        .addMethod(
          getRawContractStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateRequest,
              com.cosmwasm.wasm.v1.QueryProto.QueryRawContractStateResponse>(
                service, METHODID_RAW_CONTRACT_STATE)))
        .addMethod(
          getSmartContractStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest,
              com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse>(
                service, METHODID_SMART_CONTRACT_STATE)))
        .addMethod(
          getCodeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmwasm.wasm.v1.QueryProto.QueryCodeRequest,
              com.cosmwasm.wasm.v1.QueryProto.QueryCodeResponse>(
                service, METHODID_CODE)))
        .addMethod(
          getCodesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmwasm.wasm.v1.QueryProto.QueryCodesRequest,
              com.cosmwasm.wasm.v1.QueryProto.QueryCodesResponse>(
                service, METHODID_CODES)))
        .addMethod(
          getPinnedCodesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesRequest,
              com.cosmwasm.wasm.v1.QueryProto.QueryPinnedCodesResponse>(
                service, METHODID_PINNED_CODES)))
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmwasm.wasm.v1.QueryProto.QueryParamsRequest,
              com.cosmwasm.wasm.v1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmwasm.wasm.v1.QueryProto.getDescriptor();
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
              .addMethod(getContractInfoMethod())
              .addMethod(getContractHistoryMethod())
              .addMethod(getContractsByCodeMethod())
              .addMethod(getAllContractStateMethod())
              .addMethod(getRawContractStateMethod())
              .addMethod(getSmartContractStateMethod())
              .addMethod(getCodeMethod())
              .addMethod(getCodesMethod())
              .addMethod(getPinnedCodesMethod())
              .addMethod(getParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
