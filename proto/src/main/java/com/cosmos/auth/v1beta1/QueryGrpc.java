package com.cosmos.auth.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmos/auth/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "cosmos.auth.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryAccountsRequest,
      com.cosmos.auth.v1beta1.QueryProto.QueryAccountsResponse> getAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Accounts",
      requestType = com.cosmos.auth.v1beta1.QueryProto.QueryAccountsRequest.class,
      responseType = com.cosmos.auth.v1beta1.QueryProto.QueryAccountsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryAccountsRequest,
      com.cosmos.auth.v1beta1.QueryProto.QueryAccountsResponse> getAccountsMethod() {
    io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryAccountsRequest, com.cosmos.auth.v1beta1.QueryProto.QueryAccountsResponse> getAccountsMethod;
    if ((getAccountsMethod = QueryGrpc.getAccountsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountsMethod = QueryGrpc.getAccountsMethod) == null) {
          QueryGrpc.getAccountsMethod = getAccountsMethod =
              io.grpc.MethodDescriptor.<com.cosmos.auth.v1beta1.QueryProto.QueryAccountsRequest, com.cosmos.auth.v1beta1.QueryProto.QueryAccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Accounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.QueryAccountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.QueryAccountsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Accounts"))
              .build();
        }
      }
    }
    return getAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryAccountRequest,
      com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse> getAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Account",
      requestType = com.cosmos.auth.v1beta1.QueryProto.QueryAccountRequest.class,
      responseType = com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryAccountRequest,
      com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse> getAccountMethod() {
    io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryAccountRequest, com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse> getAccountMethod;
    if ((getAccountMethod = QueryGrpc.getAccountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountMethod = QueryGrpc.getAccountMethod) == null) {
          QueryGrpc.getAccountMethod = getAccountMethod =
              io.grpc.MethodDescriptor.<com.cosmos.auth.v1beta1.QueryProto.QueryAccountRequest, com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Account"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.QueryAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Account"))
              .build();
        }
      }
    }
    return getAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDRequest,
      com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDResponse> getAccountAddressByIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AccountAddressByID",
      requestType = com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDRequest.class,
      responseType = com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDRequest,
      com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDResponse> getAccountAddressByIDMethod() {
    io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDRequest, com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDResponse> getAccountAddressByIDMethod;
    if ((getAccountAddressByIDMethod = QueryGrpc.getAccountAddressByIDMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountAddressByIDMethod = QueryGrpc.getAccountAddressByIDMethod) == null) {
          QueryGrpc.getAccountAddressByIDMethod = getAccountAddressByIDMethod =
              io.grpc.MethodDescriptor.<com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDRequest, com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AccountAddressByID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AccountAddressByID"))
              .build();
        }
      }
    }
    return getAccountAddressByIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryParamsRequest,
      com.cosmos.auth.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.cosmos.auth.v1beta1.QueryProto.QueryParamsRequest.class,
      responseType = com.cosmos.auth.v1beta1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryParamsRequest,
      com.cosmos.auth.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryParamsRequest, com.cosmos.auth.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.cosmos.auth.v1beta1.QueryProto.QueryParamsRequest, com.cosmos.auth.v1beta1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsRequest,
      com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsResponse> getModuleAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ModuleAccounts",
      requestType = com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsRequest.class,
      responseType = com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsRequest,
      com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsResponse> getModuleAccountsMethod() {
    io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsRequest, com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsResponse> getModuleAccountsMethod;
    if ((getModuleAccountsMethod = QueryGrpc.getModuleAccountsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getModuleAccountsMethod = QueryGrpc.getModuleAccountsMethod) == null) {
          QueryGrpc.getModuleAccountsMethod = getModuleAccountsMethod =
              io.grpc.MethodDescriptor.<com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsRequest, com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ModuleAccounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ModuleAccounts"))
              .build();
        }
      }
    }
    return getModuleAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameRequest,
      com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameResponse> getModuleAccountByNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ModuleAccountByName",
      requestType = com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameRequest.class,
      responseType = com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameRequest,
      com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameResponse> getModuleAccountByNameMethod() {
    io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameRequest, com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameResponse> getModuleAccountByNameMethod;
    if ((getModuleAccountByNameMethod = QueryGrpc.getModuleAccountByNameMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getModuleAccountByNameMethod = QueryGrpc.getModuleAccountByNameMethod) == null) {
          QueryGrpc.getModuleAccountByNameMethod = getModuleAccountByNameMethod =
              io.grpc.MethodDescriptor.<com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameRequest, com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ModuleAccountByName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ModuleAccountByName"))
              .build();
        }
      }
    }
    return getModuleAccountByNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixRequest,
      com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixResponse> getBech32PrefixMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Bech32Prefix",
      requestType = com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixRequest.class,
      responseType = com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixRequest,
      com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixResponse> getBech32PrefixMethod() {
    io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixRequest, com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixResponse> getBech32PrefixMethod;
    if ((getBech32PrefixMethod = QueryGrpc.getBech32PrefixMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBech32PrefixMethod = QueryGrpc.getBech32PrefixMethod) == null) {
          QueryGrpc.getBech32PrefixMethod = getBech32PrefixMethod =
              io.grpc.MethodDescriptor.<com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixRequest, com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Bech32Prefix"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Bech32Prefix"))
              .build();
        }
      }
    }
    return getBech32PrefixMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringRequest,
      com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringResponse> getAddressBytesToStringMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddressBytesToString",
      requestType = com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringRequest.class,
      responseType = com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringRequest,
      com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringResponse> getAddressBytesToStringMethod() {
    io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringRequest, com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringResponse> getAddressBytesToStringMethod;
    if ((getAddressBytesToStringMethod = QueryGrpc.getAddressBytesToStringMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAddressBytesToStringMethod = QueryGrpc.getAddressBytesToStringMethod) == null) {
          QueryGrpc.getAddressBytesToStringMethod = getAddressBytesToStringMethod =
              io.grpc.MethodDescriptor.<com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringRequest, com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddressBytesToString"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AddressBytesToString"))
              .build();
        }
      }
    }
    return getAddressBytesToStringMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesRequest,
      com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesResponse> getAddressStringToBytesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddressStringToBytes",
      requestType = com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesRequest.class,
      responseType = com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesRequest,
      com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesResponse> getAddressStringToBytesMethod() {
    io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesRequest, com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesResponse> getAddressStringToBytesMethod;
    if ((getAddressStringToBytesMethod = QueryGrpc.getAddressStringToBytesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAddressStringToBytesMethod = QueryGrpc.getAddressStringToBytesMethod) == null) {
          QueryGrpc.getAddressStringToBytesMethod = getAddressStringToBytesMethod =
              io.grpc.MethodDescriptor.<com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesRequest, com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddressStringToBytes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AddressStringToBytes"))
              .build();
        }
      }
    }
    return getAddressStringToBytesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoRequest,
      com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoResponse> getAccountInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AccountInfo",
      requestType = com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoRequest.class,
      responseType = com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoRequest,
      com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoResponse> getAccountInfoMethod() {
    io.grpc.MethodDescriptor<com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoRequest, com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoResponse> getAccountInfoMethod;
    if ((getAccountInfoMethod = QueryGrpc.getAccountInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountInfoMethod = QueryGrpc.getAccountInfoMethod) == null) {
          QueryGrpc.getAccountInfoMethod = getAccountInfoMethod =
              io.grpc.MethodDescriptor.<com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoRequest, com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AccountInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AccountInfo"))
              .build();
        }
      }
    }
    return getAccountInfoMethod;
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
     * Accounts returns all the existing accounts.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * Since: cosmos-sdk 0.43
     * </pre>
     */
    default void accounts(com.cosmos.auth.v1beta1.QueryProto.QueryAccountsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryAccountsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAccountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Account returns account details based on address.
     * </pre>
     */
    default void account(com.cosmos.auth.v1beta1.QueryProto.QueryAccountRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * AccountAddressByID returns account address based on account number.
     * Since: cosmos-sdk 0.46.2
     * </pre>
     */
    default void accountAddressByID(com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAccountAddressByIDMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries all parameters.
     * </pre>
     */
    default void params(com.cosmos.auth.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ModuleAccounts returns all the existing module accounts.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    default void moduleAccounts(com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getModuleAccountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ModuleAccountByName returns the module account info by module name
     * </pre>
     */
    default void moduleAccountByName(com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getModuleAccountByNameMethod(), responseObserver);
    }

    /**
     * <pre>
     * Bech32Prefix queries bech32Prefix
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    default void bech32Prefix(com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBech32PrefixMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddressBytesToString converts Account Address bytes to string
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    default void addressBytesToString(com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddressBytesToStringMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddressStringToBytes converts Address string to bytes
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    default void addressStringToBytes(com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddressStringToBytesMethod(), responseObserver);
    }

    /**
     * <pre>
     * AccountInfo queries account info which is common to all account types.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    default void accountInfo(com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAccountInfoMethod(), responseObserver);
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
     * Accounts returns all the existing accounts.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * Since: cosmos-sdk 0.43
     * </pre>
     */
    public void accounts(com.cosmos.auth.v1beta1.QueryProto.QueryAccountsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryAccountsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Account returns account details based on address.
     * </pre>
     */
    public void account(com.cosmos.auth.v1beta1.QueryProto.QueryAccountRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AccountAddressByID returns account address based on account number.
     * Since: cosmos-sdk 0.46.2
     * </pre>
     */
    public void accountAddressByID(com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAccountAddressByIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries all parameters.
     * </pre>
     */
    public void params(com.cosmos.auth.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ModuleAccounts returns all the existing module accounts.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public void moduleAccounts(com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getModuleAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ModuleAccountByName returns the module account info by module name
     * </pre>
     */
    public void moduleAccountByName(com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getModuleAccountByNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Bech32Prefix queries bech32Prefix
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public void bech32Prefix(com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBech32PrefixMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddressBytesToString converts Account Address bytes to string
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public void addressBytesToString(com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddressBytesToStringMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddressStringToBytes converts Address string to bytes
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public void addressStringToBytes(com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddressStringToBytesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AccountInfo queries account info which is common to all account types.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public void accountInfo(com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAccountInfoMethod(), getCallOptions()), request, responseObserver);
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
     * Accounts returns all the existing accounts.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * Since: cosmos-sdk 0.43
     * </pre>
     */
    public com.cosmos.auth.v1beta1.QueryProto.QueryAccountsResponse accounts(com.cosmos.auth.v1beta1.QueryProto.QueryAccountsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAccountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Account returns account details based on address.
     * </pre>
     */
    public com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse account(com.cosmos.auth.v1beta1.QueryProto.QueryAccountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AccountAddressByID returns account address based on account number.
     * Since: cosmos-sdk 0.46.2
     * </pre>
     */
    public com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDResponse accountAddressByID(com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAccountAddressByIDMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries all parameters.
     * </pre>
     */
    public com.cosmos.auth.v1beta1.QueryProto.QueryParamsResponse params(com.cosmos.auth.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ModuleAccounts returns all the existing module accounts.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsResponse moduleAccounts(com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getModuleAccountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ModuleAccountByName returns the module account info by module name
     * </pre>
     */
    public com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameResponse moduleAccountByName(com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getModuleAccountByNameMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Bech32Prefix queries bech32Prefix
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixResponse bech32Prefix(com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBech32PrefixMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddressBytesToString converts Account Address bytes to string
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringResponse addressBytesToString(com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddressBytesToStringMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddressStringToBytes converts Address string to bytes
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesResponse addressStringToBytes(com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddressStringToBytesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AccountInfo queries account info which is common to all account types.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoResponse accountInfo(com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAccountInfoMethod(), getCallOptions(), request);
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
     * Accounts returns all the existing accounts.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * Since: cosmos-sdk 0.43
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.auth.v1beta1.QueryProto.QueryAccountsResponse> accounts(
        com.cosmos.auth.v1beta1.QueryProto.QueryAccountsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Account returns account details based on address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse> account(
        com.cosmos.auth.v1beta1.QueryProto.QueryAccountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AccountAddressByID returns account address based on account number.
     * Since: cosmos-sdk 0.46.2
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDResponse> accountAddressByID(
        com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAccountAddressByIDMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries all parameters.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.auth.v1beta1.QueryProto.QueryParamsResponse> params(
        com.cosmos.auth.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ModuleAccounts returns all the existing module accounts.
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsResponse> moduleAccounts(
        com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getModuleAccountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ModuleAccountByName returns the module account info by module name
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameResponse> moduleAccountByName(
        com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getModuleAccountByNameMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Bech32Prefix queries bech32Prefix
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixResponse> bech32Prefix(
        com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBech32PrefixMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddressBytesToString converts Account Address bytes to string
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringResponse> addressBytesToString(
        com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddressBytesToStringMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddressStringToBytes converts Address string to bytes
     * Since: cosmos-sdk 0.46
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesResponse> addressStringToBytes(
        com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddressStringToBytesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AccountInfo queries account info which is common to all account types.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoResponse> accountInfo(
        com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAccountInfoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ACCOUNTS = 0;
  private static final int METHODID_ACCOUNT = 1;
  private static final int METHODID_ACCOUNT_ADDRESS_BY_ID = 2;
  private static final int METHODID_PARAMS = 3;
  private static final int METHODID_MODULE_ACCOUNTS = 4;
  private static final int METHODID_MODULE_ACCOUNT_BY_NAME = 5;
  private static final int METHODID_BECH32PREFIX = 6;
  private static final int METHODID_ADDRESS_BYTES_TO_STRING = 7;
  private static final int METHODID_ADDRESS_STRING_TO_BYTES = 8;
  private static final int METHODID_ACCOUNT_INFO = 9;

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
        case METHODID_ACCOUNTS:
          serviceImpl.accounts((com.cosmos.auth.v1beta1.QueryProto.QueryAccountsRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryAccountsResponse>) responseObserver);
          break;
        case METHODID_ACCOUNT:
          serviceImpl.account((com.cosmos.auth.v1beta1.QueryProto.QueryAccountRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse>) responseObserver);
          break;
        case METHODID_ACCOUNT_ADDRESS_BY_ID:
          serviceImpl.accountAddressByID((com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((com.cosmos.auth.v1beta1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_MODULE_ACCOUNTS:
          serviceImpl.moduleAccounts((com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsResponse>) responseObserver);
          break;
        case METHODID_MODULE_ACCOUNT_BY_NAME:
          serviceImpl.moduleAccountByName((com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameResponse>) responseObserver);
          break;
        case METHODID_BECH32PREFIX:
          serviceImpl.bech32Prefix((com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixResponse>) responseObserver);
          break;
        case METHODID_ADDRESS_BYTES_TO_STRING:
          serviceImpl.addressBytesToString((com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringResponse>) responseObserver);
          break;
        case METHODID_ADDRESS_STRING_TO_BYTES:
          serviceImpl.addressStringToBytes((com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesResponse>) responseObserver);
          break;
        case METHODID_ACCOUNT_INFO:
          serviceImpl.accountInfo((com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoResponse>) responseObserver);
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
          getAccountsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.auth.v1beta1.QueryProto.QueryAccountsRequest,
              com.cosmos.auth.v1beta1.QueryProto.QueryAccountsResponse>(
                service, METHODID_ACCOUNTS)))
        .addMethod(
          getAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.auth.v1beta1.QueryProto.QueryAccountRequest,
              com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse>(
                service, METHODID_ACCOUNT)))
        .addMethod(
          getAccountAddressByIDMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDRequest,
              com.cosmos.auth.v1beta1.QueryProto.QueryAccountAddressByIDResponse>(
                service, METHODID_ACCOUNT_ADDRESS_BY_ID)))
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.auth.v1beta1.QueryProto.QueryParamsRequest,
              com.cosmos.auth.v1beta1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getModuleAccountsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsRequest,
              com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountsResponse>(
                service, METHODID_MODULE_ACCOUNTS)))
        .addMethod(
          getModuleAccountByNameMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameRequest,
              com.cosmos.auth.v1beta1.QueryProto.QueryModuleAccountByNameResponse>(
                service, METHODID_MODULE_ACCOUNT_BY_NAME)))
        .addMethod(
          getBech32PrefixMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixRequest,
              com.cosmos.auth.v1beta1.QueryProto.Bech32PrefixResponse>(
                service, METHODID_BECH32PREFIX)))
        .addMethod(
          getAddressBytesToStringMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringRequest,
              com.cosmos.auth.v1beta1.QueryProto.AddressBytesToStringResponse>(
                service, METHODID_ADDRESS_BYTES_TO_STRING)))
        .addMethod(
          getAddressStringToBytesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesRequest,
              com.cosmos.auth.v1beta1.QueryProto.AddressStringToBytesResponse>(
                service, METHODID_ADDRESS_STRING_TO_BYTES)))
        .addMethod(
          getAccountInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoRequest,
              com.cosmos.auth.v1beta1.QueryProto.QueryAccountInfoResponse>(
                service, METHODID_ACCOUNT_INFO)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmos.auth.v1beta1.QueryProto.getDescriptor();
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
              .addMethod(getAccountsMethod())
              .addMethod(getAccountMethod())
              .addMethod(getAccountAddressByIDMethod())
              .addMethod(getParamsMethod())
              .addMethod(getModuleAccountsMethod())
              .addMethod(getModuleAccountByNameMethod())
              .addMethod(getBech32PrefixMethod())
              .addMethod(getAddressBytesToStringMethod())
              .addMethod(getAddressStringToBytesMethod())
              .addMethod(getAccountInfoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
