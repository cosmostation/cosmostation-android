package com.desmos.profiles.v3;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: desmos/profiles/v3/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "desmos.profiles.v3.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryProfileProto.QueryProfileRequest,
      com.desmos.profiles.v3.QueryProfileProto.QueryProfileResponse> getProfileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Profile",
      requestType = com.desmos.profiles.v3.QueryProfileProto.QueryProfileRequest.class,
      responseType = com.desmos.profiles.v3.QueryProfileProto.QueryProfileResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryProfileProto.QueryProfileRequest,
      com.desmos.profiles.v3.QueryProfileProto.QueryProfileResponse> getProfileMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryProfileProto.QueryProfileRequest, com.desmos.profiles.v3.QueryProfileProto.QueryProfileResponse> getProfileMethod;
    if ((getProfileMethod = QueryGrpc.getProfileMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProfileMethod = QueryGrpc.getProfileMethod) == null) {
          QueryGrpc.getProfileMethod = getProfileMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.QueryProfileProto.QueryProfileRequest, com.desmos.profiles.v3.QueryProfileProto.QueryProfileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Profile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryProfileProto.QueryProfileRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryProfileProto.QueryProfileResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Profile"))
              .build();
        }
      }
    }
    return getProfileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsRequest,
      com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsResponse> getIncomingDTagTransferRequestsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IncomingDTagTransferRequests",
      requestType = com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsRequest.class,
      responseType = com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsRequest,
      com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsResponse> getIncomingDTagTransferRequestsMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsRequest, com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsResponse> getIncomingDTagTransferRequestsMethod;
    if ((getIncomingDTagTransferRequestsMethod = QueryGrpc.getIncomingDTagTransferRequestsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getIncomingDTagTransferRequestsMethod = QueryGrpc.getIncomingDTagTransferRequestsMethod) == null) {
          QueryGrpc.getIncomingDTagTransferRequestsMethod = getIncomingDTagTransferRequestsMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsRequest, com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IncomingDTagTransferRequests"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("IncomingDTagTransferRequests"))
              .build();
        }
      }
    }
    return getIncomingDTagTransferRequestsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksRequest,
      com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksResponse> getChainLinksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChainLinks",
      requestType = com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksRequest.class,
      responseType = com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksRequest,
      com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksResponse> getChainLinksMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksRequest, com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksResponse> getChainLinksMethod;
    if ((getChainLinksMethod = QueryGrpc.getChainLinksMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getChainLinksMethod = QueryGrpc.getChainLinksMethod) == null) {
          QueryGrpc.getChainLinksMethod = getChainLinksMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksRequest, com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ChainLinks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ChainLinks"))
              .build();
        }
      }
    }
    return getChainLinksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersRequest,
      com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersResponse> getChainLinkOwnersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChainLinkOwners",
      requestType = com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersRequest.class,
      responseType = com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersRequest,
      com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersResponse> getChainLinkOwnersMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersRequest, com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersResponse> getChainLinkOwnersMethod;
    if ((getChainLinkOwnersMethod = QueryGrpc.getChainLinkOwnersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getChainLinkOwnersMethod = QueryGrpc.getChainLinkOwnersMethod) == null) {
          QueryGrpc.getChainLinkOwnersMethod = getChainLinkOwnersMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersRequest, com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ChainLinkOwners"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ChainLinkOwners"))
              .build();
        }
      }
    }
    return getChainLinkOwnersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesRequest,
      com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesResponse> getDefaultExternalAddressesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DefaultExternalAddresses",
      requestType = com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesRequest.class,
      responseType = com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesRequest,
      com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesResponse> getDefaultExternalAddressesMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesRequest, com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesResponse> getDefaultExternalAddressesMethod;
    if ((getDefaultExternalAddressesMethod = QueryGrpc.getDefaultExternalAddressesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDefaultExternalAddressesMethod = QueryGrpc.getDefaultExternalAddressesMethod) == null) {
          QueryGrpc.getDefaultExternalAddressesMethod = getDefaultExternalAddressesMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesRequest, com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DefaultExternalAddresses"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DefaultExternalAddresses"))
              .build();
        }
      }
    }
    return getDefaultExternalAddressesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksRequest,
      com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksResponse> getApplicationLinksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ApplicationLinks",
      requestType = com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksRequest.class,
      responseType = com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksRequest,
      com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksResponse> getApplicationLinksMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksRequest, com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksResponse> getApplicationLinksMethod;
    if ((getApplicationLinksMethod = QueryGrpc.getApplicationLinksMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getApplicationLinksMethod = QueryGrpc.getApplicationLinksMethod) == null) {
          QueryGrpc.getApplicationLinksMethod = getApplicationLinksMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksRequest, com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ApplicationLinks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ApplicationLinks"))
              .build();
        }
      }
    }
    return getApplicationLinksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDRequest,
      com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDResponse> getApplicationLinkByClientIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ApplicationLinkByClientID",
      requestType = com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDRequest.class,
      responseType = com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDRequest,
      com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDResponse> getApplicationLinkByClientIDMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDRequest, com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDResponse> getApplicationLinkByClientIDMethod;
    if ((getApplicationLinkByClientIDMethod = QueryGrpc.getApplicationLinkByClientIDMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getApplicationLinkByClientIDMethod = QueryGrpc.getApplicationLinkByClientIDMethod) == null) {
          QueryGrpc.getApplicationLinkByClientIDMethod = getApplicationLinkByClientIDMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDRequest, com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ApplicationLinkByClientID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ApplicationLinkByClientID"))
              .build();
        }
      }
    }
    return getApplicationLinkByClientIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersRequest,
      com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersResponse> getApplicationLinkOwnersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ApplicationLinkOwners",
      requestType = com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersRequest.class,
      responseType = com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersRequest,
      com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersResponse> getApplicationLinkOwnersMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersRequest, com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersResponse> getApplicationLinkOwnersMethod;
    if ((getApplicationLinkOwnersMethod = QueryGrpc.getApplicationLinkOwnersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getApplicationLinkOwnersMethod = QueryGrpc.getApplicationLinkOwnersMethod) == null) {
          QueryGrpc.getApplicationLinkOwnersMethod = getApplicationLinkOwnersMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersRequest, com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ApplicationLinkOwners"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ApplicationLinkOwners"))
              .build();
        }
      }
    }
    return getApplicationLinkOwnersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryParamsProto.QueryParamsRequest,
      com.desmos.profiles.v3.QueryParamsProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.desmos.profiles.v3.QueryParamsProto.QueryParamsRequest.class,
      responseType = com.desmos.profiles.v3.QueryParamsProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryParamsProto.QueryParamsRequest,
      com.desmos.profiles.v3.QueryParamsProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.desmos.profiles.v3.QueryParamsProto.QueryParamsRequest, com.desmos.profiles.v3.QueryParamsProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.desmos.profiles.v3.QueryParamsProto.QueryParamsRequest, com.desmos.profiles.v3.QueryParamsProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryParamsProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.profiles.v3.QueryParamsProto.QueryParamsResponse.getDefaultInstance()))
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
   * Query defines the gRPC querier service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Profile queries the profile of a specific user given their DTag or address.
     * If the queried user does not have a profile, the returned response will
     * contain a null profile.
     * </pre>
     */
    default void profile(com.desmos.profiles.v3.QueryProfileProto.QueryProfileRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryProfileProto.QueryProfileResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getProfileMethod(), responseObserver);
    }

    /**
     * <pre>
     * IncomingDTagTransferRequests queries all the DTag transfers requests that
     * have been made towards the user with the given address
     * </pre>
     */
    default void incomingDTagTransferRequests(com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getIncomingDTagTransferRequestsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ChainLinks queries the chain links associated to the given user, if
     * provided. Otherwise it queries all the chain links stored.
     * </pre>
     */
    default void chainLinks(com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getChainLinksMethod(), responseObserver);
    }

    /**
     * <pre>
     * ChainLinkOwners queries for the owners of chain links, optionally searching
     * for a specific chain name and external address
     * </pre>
     */
    default void chainLinkOwners(com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getChainLinkOwnersMethod(), responseObserver);
    }

    /**
     * <pre>
     * DefaultExternalAddresses queries the default addresses associated to the
     * given user and (optionally) chain name
     * </pre>
     */
    default void defaultExternalAddresses(com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDefaultExternalAddressesMethod(), responseObserver);
    }

    /**
     * <pre>
     * ApplicationLinks queries the applications links associated to the given
     * user, if provided. Otherwise, it queries all the application links stored.
     * </pre>
     */
    default void applicationLinks(com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getApplicationLinksMethod(), responseObserver);
    }

    /**
     * <pre>
     * ApplicationLinkByClientID queries a single application link for a given
     * client id.
     * </pre>
     */
    default void applicationLinkByClientID(com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getApplicationLinkByClientIDMethod(), responseObserver);
    }

    /**
     * <pre>
     * ApplicationLinkOwners queries for the owners of applications links,
     * optionally searching for a specific application and username.
     * </pre>
     */
    default void applicationLinkOwners(com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getApplicationLinkOwnersMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries the profiles module params
     * </pre>
     */
    default void params(com.desmos.profiles.v3.QueryParamsProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryParamsProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
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
     * Profile queries the profile of a specific user given their DTag or address.
     * If the queried user does not have a profile, the returned response will
     * contain a null profile.
     * </pre>
     */
    public void profile(com.desmos.profiles.v3.QueryProfileProto.QueryProfileRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryProfileProto.QueryProfileResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getProfileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * IncomingDTagTransferRequests queries all the DTag transfers requests that
     * have been made towards the user with the given address
     * </pre>
     */
    public void incomingDTagTransferRequests(com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getIncomingDTagTransferRequestsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ChainLinks queries the chain links associated to the given user, if
     * provided. Otherwise it queries all the chain links stored.
     * </pre>
     */
    public void chainLinks(com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getChainLinksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ChainLinkOwners queries for the owners of chain links, optionally searching
     * for a specific chain name and external address
     * </pre>
     */
    public void chainLinkOwners(com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getChainLinkOwnersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DefaultExternalAddresses queries the default addresses associated to the
     * given user and (optionally) chain name
     * </pre>
     */
    public void defaultExternalAddresses(com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDefaultExternalAddressesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ApplicationLinks queries the applications links associated to the given
     * user, if provided. Otherwise, it queries all the application links stored.
     * </pre>
     */
    public void applicationLinks(com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getApplicationLinksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ApplicationLinkByClientID queries a single application link for a given
     * client id.
     * </pre>
     */
    public void applicationLinkByClientID(com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getApplicationLinkByClientIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ApplicationLinkOwners queries for the owners of applications links,
     * optionally searching for a specific application and username.
     * </pre>
     */
    public void applicationLinkOwners(com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getApplicationLinkOwnersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries the profiles module params
     * </pre>
     */
    public void params(com.desmos.profiles.v3.QueryParamsProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryParamsProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
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
     * Profile queries the profile of a specific user given their DTag or address.
     * If the queried user does not have a profile, the returned response will
     * contain a null profile.
     * </pre>
     */
    public com.desmos.profiles.v3.QueryProfileProto.QueryProfileResponse profile(com.desmos.profiles.v3.QueryProfileProto.QueryProfileRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getProfileMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * IncomingDTagTransferRequests queries all the DTag transfers requests that
     * have been made towards the user with the given address
     * </pre>
     */
    public com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsResponse incomingDTagTransferRequests(com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getIncomingDTagTransferRequestsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ChainLinks queries the chain links associated to the given user, if
     * provided. Otherwise it queries all the chain links stored.
     * </pre>
     */
    public com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksResponse chainLinks(com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getChainLinksMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ChainLinkOwners queries for the owners of chain links, optionally searching
     * for a specific chain name and external address
     * </pre>
     */
    public com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersResponse chainLinkOwners(com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getChainLinkOwnersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DefaultExternalAddresses queries the default addresses associated to the
     * given user and (optionally) chain name
     * </pre>
     */
    public com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesResponse defaultExternalAddresses(com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDefaultExternalAddressesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ApplicationLinks queries the applications links associated to the given
     * user, if provided. Otherwise, it queries all the application links stored.
     * </pre>
     */
    public com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksResponse applicationLinks(com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getApplicationLinksMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ApplicationLinkByClientID queries a single application link for a given
     * client id.
     * </pre>
     */
    public com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDResponse applicationLinkByClientID(com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getApplicationLinkByClientIDMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ApplicationLinkOwners queries for the owners of applications links,
     * optionally searching for a specific application and username.
     * </pre>
     */
    public com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersResponse applicationLinkOwners(com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getApplicationLinkOwnersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries the profiles module params
     * </pre>
     */
    public com.desmos.profiles.v3.QueryParamsProto.QueryParamsResponse params(com.desmos.profiles.v3.QueryParamsProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
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
     * Profile queries the profile of a specific user given their DTag or address.
     * If the queried user does not have a profile, the returned response will
     * contain a null profile.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.QueryProfileProto.QueryProfileResponse> profile(
        com.desmos.profiles.v3.QueryProfileProto.QueryProfileRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getProfileMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * IncomingDTagTransferRequests queries all the DTag transfers requests that
     * have been made towards the user with the given address
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsResponse> incomingDTagTransferRequests(
        com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getIncomingDTagTransferRequestsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ChainLinks queries the chain links associated to the given user, if
     * provided. Otherwise it queries all the chain links stored.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksResponse> chainLinks(
        com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getChainLinksMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ChainLinkOwners queries for the owners of chain links, optionally searching
     * for a specific chain name and external address
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersResponse> chainLinkOwners(
        com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getChainLinkOwnersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DefaultExternalAddresses queries the default addresses associated to the
     * given user and (optionally) chain name
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesResponse> defaultExternalAddresses(
        com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDefaultExternalAddressesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ApplicationLinks queries the applications links associated to the given
     * user, if provided. Otherwise, it queries all the application links stored.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksResponse> applicationLinks(
        com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getApplicationLinksMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ApplicationLinkByClientID queries a single application link for a given
     * client id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDResponse> applicationLinkByClientID(
        com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getApplicationLinkByClientIDMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ApplicationLinkOwners queries for the owners of applications links,
     * optionally searching for a specific application and username.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersResponse> applicationLinkOwners(
        com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getApplicationLinkOwnersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries the profiles module params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.profiles.v3.QueryParamsProto.QueryParamsResponse> params(
        com.desmos.profiles.v3.QueryParamsProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PROFILE = 0;
  private static final int METHODID_INCOMING_DTAG_TRANSFER_REQUESTS = 1;
  private static final int METHODID_CHAIN_LINKS = 2;
  private static final int METHODID_CHAIN_LINK_OWNERS = 3;
  private static final int METHODID_DEFAULT_EXTERNAL_ADDRESSES = 4;
  private static final int METHODID_APPLICATION_LINKS = 5;
  private static final int METHODID_APPLICATION_LINK_BY_CLIENT_ID = 6;
  private static final int METHODID_APPLICATION_LINK_OWNERS = 7;
  private static final int METHODID_PARAMS = 8;

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
        case METHODID_PROFILE:
          serviceImpl.profile((com.desmos.profiles.v3.QueryProfileProto.QueryProfileRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryProfileProto.QueryProfileResponse>) responseObserver);
          break;
        case METHODID_INCOMING_DTAG_TRANSFER_REQUESTS:
          serviceImpl.incomingDTagTransferRequests((com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsResponse>) responseObserver);
          break;
        case METHODID_CHAIN_LINKS:
          serviceImpl.chainLinks((com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksResponse>) responseObserver);
          break;
        case METHODID_CHAIN_LINK_OWNERS:
          serviceImpl.chainLinkOwners((com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersResponse>) responseObserver);
          break;
        case METHODID_DEFAULT_EXTERNAL_ADDRESSES:
          serviceImpl.defaultExternalAddresses((com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesResponse>) responseObserver);
          break;
        case METHODID_APPLICATION_LINKS:
          serviceImpl.applicationLinks((com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksResponse>) responseObserver);
          break;
        case METHODID_APPLICATION_LINK_BY_CLIENT_ID:
          serviceImpl.applicationLinkByClientID((com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDResponse>) responseObserver);
          break;
        case METHODID_APPLICATION_LINK_OWNERS:
          serviceImpl.applicationLinkOwners((com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((com.desmos.profiles.v3.QueryParamsProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.profiles.v3.QueryParamsProto.QueryParamsResponse>) responseObserver);
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
          getProfileMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.QueryProfileProto.QueryProfileRequest,
              com.desmos.profiles.v3.QueryProfileProto.QueryProfileResponse>(
                service, METHODID_PROFILE)))
        .addMethod(
          getIncomingDTagTransferRequestsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsRequest,
              com.desmos.profiles.v3.QueryDtagRequestsProto.QueryIncomingDTagTransferRequestsResponse>(
                service, METHODID_INCOMING_DTAG_TRANSFER_REQUESTS)))
        .addMethod(
          getChainLinksMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksRequest,
              com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinksResponse>(
                service, METHODID_CHAIN_LINKS)))
        .addMethod(
          getChainLinkOwnersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersRequest,
              com.desmos.profiles.v3.QueryChainLinksProto.QueryChainLinkOwnersResponse>(
                service, METHODID_CHAIN_LINK_OWNERS)))
        .addMethod(
          getDefaultExternalAddressesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesRequest,
              com.desmos.profiles.v3.QueryChainLinksProto.QueryDefaultExternalAddressesResponse>(
                service, METHODID_DEFAULT_EXTERNAL_ADDRESSES)))
        .addMethod(
          getApplicationLinksMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksRequest,
              com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinksResponse>(
                service, METHODID_APPLICATION_LINKS)))
        .addMethod(
          getApplicationLinkByClientIDMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDRequest,
              com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkByClientIDResponse>(
                service, METHODID_APPLICATION_LINK_BY_CLIENT_ID)))
        .addMethod(
          getApplicationLinkOwnersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersRequest,
              com.desmos.profiles.v3.QueryAppLinksProto.QueryApplicationLinkOwnersResponse>(
                service, METHODID_APPLICATION_LINK_OWNERS)))
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.profiles.v3.QueryParamsProto.QueryParamsRequest,
              com.desmos.profiles.v3.QueryParamsProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.desmos.profiles.v3.QueryProto.getDescriptor();
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
              .addMethod(getProfileMethod())
              .addMethod(getIncomingDTagTransferRequestsMethod())
              .addMethod(getChainLinksMethod())
              .addMethod(getChainLinkOwnersMethod())
              .addMethod(getDefaultExternalAddressesMethod())
              .addMethod(getApplicationLinksMethod())
              .addMethod(getApplicationLinkByClientIDMethod())
              .addMethod(getApplicationLinkOwnersMethod())
              .addMethod(getParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
