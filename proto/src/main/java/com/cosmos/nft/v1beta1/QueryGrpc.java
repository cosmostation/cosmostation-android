package com.cosmos.nft.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmos/nft/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "cosmos.nft.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryBalanceRequest,
      com.cosmos.nft.v1beta1.QueryProto.QueryBalanceResponse> getBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Balance",
      requestType = com.cosmos.nft.v1beta1.QueryProto.QueryBalanceRequest.class,
      responseType = com.cosmos.nft.v1beta1.QueryProto.QueryBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryBalanceRequest,
      com.cosmos.nft.v1beta1.QueryProto.QueryBalanceResponse> getBalanceMethod() {
    io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryBalanceRequest, com.cosmos.nft.v1beta1.QueryProto.QueryBalanceResponse> getBalanceMethod;
    if ((getBalanceMethod = QueryGrpc.getBalanceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBalanceMethod = QueryGrpc.getBalanceMethod) == null) {
          QueryGrpc.getBalanceMethod = getBalanceMethod =
              io.grpc.MethodDescriptor.<com.cosmos.nft.v1beta1.QueryProto.QueryBalanceRequest, com.cosmos.nft.v1beta1.QueryProto.QueryBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Balance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.nft.v1beta1.QueryProto.QueryBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.nft.v1beta1.QueryProto.QueryBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Balance"))
              .build();
        }
      }
    }
    return getBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryOwnerRequest,
      com.cosmos.nft.v1beta1.QueryProto.QueryOwnerResponse> getOwnerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Owner",
      requestType = com.cosmos.nft.v1beta1.QueryProto.QueryOwnerRequest.class,
      responseType = com.cosmos.nft.v1beta1.QueryProto.QueryOwnerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryOwnerRequest,
      com.cosmos.nft.v1beta1.QueryProto.QueryOwnerResponse> getOwnerMethod() {
    io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryOwnerRequest, com.cosmos.nft.v1beta1.QueryProto.QueryOwnerResponse> getOwnerMethod;
    if ((getOwnerMethod = QueryGrpc.getOwnerMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOwnerMethod = QueryGrpc.getOwnerMethod) == null) {
          QueryGrpc.getOwnerMethod = getOwnerMethod =
              io.grpc.MethodDescriptor.<com.cosmos.nft.v1beta1.QueryProto.QueryOwnerRequest, com.cosmos.nft.v1beta1.QueryProto.QueryOwnerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Owner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.nft.v1beta1.QueryProto.QueryOwnerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.nft.v1beta1.QueryProto.QueryOwnerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Owner"))
              .build();
        }
      }
    }
    return getOwnerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QuerySupplyRequest,
      com.cosmos.nft.v1beta1.QueryProto.QuerySupplyResponse> getSupplyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Supply",
      requestType = com.cosmos.nft.v1beta1.QueryProto.QuerySupplyRequest.class,
      responseType = com.cosmos.nft.v1beta1.QueryProto.QuerySupplyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QuerySupplyRequest,
      com.cosmos.nft.v1beta1.QueryProto.QuerySupplyResponse> getSupplyMethod() {
    io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QuerySupplyRequest, com.cosmos.nft.v1beta1.QueryProto.QuerySupplyResponse> getSupplyMethod;
    if ((getSupplyMethod = QueryGrpc.getSupplyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSupplyMethod = QueryGrpc.getSupplyMethod) == null) {
          QueryGrpc.getSupplyMethod = getSupplyMethod =
              io.grpc.MethodDescriptor.<com.cosmos.nft.v1beta1.QueryProto.QuerySupplyRequest, com.cosmos.nft.v1beta1.QueryProto.QuerySupplyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Supply"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.nft.v1beta1.QueryProto.QuerySupplyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.nft.v1beta1.QueryProto.QuerySupplyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Supply"))
              .build();
        }
      }
    }
    return getSupplyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryNFTsRequest,
      com.cosmos.nft.v1beta1.QueryProto.QueryNFTsResponse> getNFTsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "NFTs",
      requestType = com.cosmos.nft.v1beta1.QueryProto.QueryNFTsRequest.class,
      responseType = com.cosmos.nft.v1beta1.QueryProto.QueryNFTsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryNFTsRequest,
      com.cosmos.nft.v1beta1.QueryProto.QueryNFTsResponse> getNFTsMethod() {
    io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryNFTsRequest, com.cosmos.nft.v1beta1.QueryProto.QueryNFTsResponse> getNFTsMethod;
    if ((getNFTsMethod = QueryGrpc.getNFTsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getNFTsMethod = QueryGrpc.getNFTsMethod) == null) {
          QueryGrpc.getNFTsMethod = getNFTsMethod =
              io.grpc.MethodDescriptor.<com.cosmos.nft.v1beta1.QueryProto.QueryNFTsRequest, com.cosmos.nft.v1beta1.QueryProto.QueryNFTsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NFTs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.nft.v1beta1.QueryProto.QueryNFTsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.nft.v1beta1.QueryProto.QueryNFTsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("NFTs"))
              .build();
        }
      }
    }
    return getNFTsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryNFTRequest,
      com.cosmos.nft.v1beta1.QueryProto.QueryNFTResponse> getNFTMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "NFT",
      requestType = com.cosmos.nft.v1beta1.QueryProto.QueryNFTRequest.class,
      responseType = com.cosmos.nft.v1beta1.QueryProto.QueryNFTResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryNFTRequest,
      com.cosmos.nft.v1beta1.QueryProto.QueryNFTResponse> getNFTMethod() {
    io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryNFTRequest, com.cosmos.nft.v1beta1.QueryProto.QueryNFTResponse> getNFTMethod;
    if ((getNFTMethod = QueryGrpc.getNFTMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getNFTMethod = QueryGrpc.getNFTMethod) == null) {
          QueryGrpc.getNFTMethod = getNFTMethod =
              io.grpc.MethodDescriptor.<com.cosmos.nft.v1beta1.QueryProto.QueryNFTRequest, com.cosmos.nft.v1beta1.QueryProto.QueryNFTResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NFT"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.nft.v1beta1.QueryProto.QueryNFTRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.nft.v1beta1.QueryProto.QueryNFTResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("NFT"))
              .build();
        }
      }
    }
    return getNFTMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryClassRequest,
      com.cosmos.nft.v1beta1.QueryProto.QueryClassResponse> getClassMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Class",
      requestType = com.cosmos.nft.v1beta1.QueryProto.QueryClassRequest.class,
      responseType = com.cosmos.nft.v1beta1.QueryProto.QueryClassResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryClassRequest,
      com.cosmos.nft.v1beta1.QueryProto.QueryClassResponse> getClassMethod() {
    io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryClassRequest, com.cosmos.nft.v1beta1.QueryProto.QueryClassResponse> getClassMethod;
    if ((getClassMethod = QueryGrpc.getClassMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClassMethod = QueryGrpc.getClassMethod) == null) {
          QueryGrpc.getClassMethod = getClassMethod =
              io.grpc.MethodDescriptor.<com.cosmos.nft.v1beta1.QueryProto.QueryClassRequest, com.cosmos.nft.v1beta1.QueryProto.QueryClassResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Class"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.nft.v1beta1.QueryProto.QueryClassRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.nft.v1beta1.QueryProto.QueryClassResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Class"))
              .build();
        }
      }
    }
    return getClassMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryClassesRequest,
      com.cosmos.nft.v1beta1.QueryProto.QueryClassesResponse> getClassesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Classes",
      requestType = com.cosmos.nft.v1beta1.QueryProto.QueryClassesRequest.class,
      responseType = com.cosmos.nft.v1beta1.QueryProto.QueryClassesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryClassesRequest,
      com.cosmos.nft.v1beta1.QueryProto.QueryClassesResponse> getClassesMethod() {
    io.grpc.MethodDescriptor<com.cosmos.nft.v1beta1.QueryProto.QueryClassesRequest, com.cosmos.nft.v1beta1.QueryProto.QueryClassesResponse> getClassesMethod;
    if ((getClassesMethod = QueryGrpc.getClassesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClassesMethod = QueryGrpc.getClassesMethod) == null) {
          QueryGrpc.getClassesMethod = getClassesMethod =
              io.grpc.MethodDescriptor.<com.cosmos.nft.v1beta1.QueryProto.QueryClassesRequest, com.cosmos.nft.v1beta1.QueryProto.QueryClassesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Classes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.nft.v1beta1.QueryProto.QueryClassesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.nft.v1beta1.QueryProto.QueryClassesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Classes"))
              .build();
        }
      }
    }
    return getClassesMethod;
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
     * Balance queries the number of NFTs of a given class owned by the owner, same as balanceOf in ERC721
     * </pre>
     */
    default void balance(com.cosmos.nft.v1beta1.QueryProto.QueryBalanceRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryBalanceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBalanceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Owner queries the owner of the NFT based on its class and id, same as ownerOf in ERC721
     * </pre>
     */
    default void owner(com.cosmos.nft.v1beta1.QueryProto.QueryOwnerRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryOwnerResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOwnerMethod(), responseObserver);
    }

    /**
     * <pre>
     * Supply queries the number of NFTs from the given class, same as totalSupply of ERC721.
     * </pre>
     */
    default void supply(com.cosmos.nft.v1beta1.QueryProto.QuerySupplyRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QuerySupplyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSupplyMethod(), responseObserver);
    }

    /**
     * <pre>
     * NFTs queries all NFTs of a given class or owner,choose at least one of the two, similar to tokenByIndex in
     * ERC721Enumerable
     * </pre>
     */
    default void nFTs(com.cosmos.nft.v1beta1.QueryProto.QueryNFTsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryNFTsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getNFTsMethod(), responseObserver);
    }

    /**
     * <pre>
     * NFT queries an NFT based on its class and id.
     * </pre>
     */
    default void nFT(com.cosmos.nft.v1beta1.QueryProto.QueryNFTRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryNFTResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getNFTMethod(), responseObserver);
    }

    /**
     * <pre>
     * Class queries an NFT class based on its id
     * </pre>
     */
    default void class_(com.cosmos.nft.v1beta1.QueryProto.QueryClassRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryClassResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClassMethod(), responseObserver);
    }

    /**
     * <pre>
     * Classes queries all NFT classes
     * </pre>
     */
    default void classes(com.cosmos.nft.v1beta1.QueryProto.QueryClassesRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryClassesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClassesMethod(), responseObserver);
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
     * Balance queries the number of NFTs of a given class owned by the owner, same as balanceOf in ERC721
     * </pre>
     */
    public void balance(com.cosmos.nft.v1beta1.QueryProto.QueryBalanceRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryBalanceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Owner queries the owner of the NFT based on its class and id, same as ownerOf in ERC721
     * </pre>
     */
    public void owner(com.cosmos.nft.v1beta1.QueryProto.QueryOwnerRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryOwnerResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOwnerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Supply queries the number of NFTs from the given class, same as totalSupply of ERC721.
     * </pre>
     */
    public void supply(com.cosmos.nft.v1beta1.QueryProto.QuerySupplyRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QuerySupplyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSupplyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * NFTs queries all NFTs of a given class or owner,choose at least one of the two, similar to tokenByIndex in
     * ERC721Enumerable
     * </pre>
     */
    public void nFTs(com.cosmos.nft.v1beta1.QueryProto.QueryNFTsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryNFTsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getNFTsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * NFT queries an NFT based on its class and id.
     * </pre>
     */
    public void nFT(com.cosmos.nft.v1beta1.QueryProto.QueryNFTRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryNFTResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getNFTMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Class queries an NFT class based on its id
     * </pre>
     */
    public void class_(com.cosmos.nft.v1beta1.QueryProto.QueryClassRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryClassResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClassMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Classes queries all NFT classes
     * </pre>
     */
    public void classes(com.cosmos.nft.v1beta1.QueryProto.QueryClassesRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryClassesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClassesMethod(), getCallOptions()), request, responseObserver);
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
     * Balance queries the number of NFTs of a given class owned by the owner, same as balanceOf in ERC721
     * </pre>
     */
    public com.cosmos.nft.v1beta1.QueryProto.QueryBalanceResponse balance(com.cosmos.nft.v1beta1.QueryProto.QueryBalanceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBalanceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Owner queries the owner of the NFT based on its class and id, same as ownerOf in ERC721
     * </pre>
     */
    public com.cosmos.nft.v1beta1.QueryProto.QueryOwnerResponse owner(com.cosmos.nft.v1beta1.QueryProto.QueryOwnerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOwnerMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Supply queries the number of NFTs from the given class, same as totalSupply of ERC721.
     * </pre>
     */
    public com.cosmos.nft.v1beta1.QueryProto.QuerySupplyResponse supply(com.cosmos.nft.v1beta1.QueryProto.QuerySupplyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSupplyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * NFTs queries all NFTs of a given class or owner,choose at least one of the two, similar to tokenByIndex in
     * ERC721Enumerable
     * </pre>
     */
    public com.cosmos.nft.v1beta1.QueryProto.QueryNFTsResponse nFTs(com.cosmos.nft.v1beta1.QueryProto.QueryNFTsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getNFTsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * NFT queries an NFT based on its class and id.
     * </pre>
     */
    public com.cosmos.nft.v1beta1.QueryProto.QueryNFTResponse nFT(com.cosmos.nft.v1beta1.QueryProto.QueryNFTRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getNFTMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Class queries an NFT class based on its id
     * </pre>
     */
    public com.cosmos.nft.v1beta1.QueryProto.QueryClassResponse class_(com.cosmos.nft.v1beta1.QueryProto.QueryClassRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClassMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Classes queries all NFT classes
     * </pre>
     */
    public com.cosmos.nft.v1beta1.QueryProto.QueryClassesResponse classes(com.cosmos.nft.v1beta1.QueryProto.QueryClassesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClassesMethod(), getCallOptions(), request);
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
     * Balance queries the number of NFTs of a given class owned by the owner, same as balanceOf in ERC721
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.nft.v1beta1.QueryProto.QueryBalanceResponse> balance(
        com.cosmos.nft.v1beta1.QueryProto.QueryBalanceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Owner queries the owner of the NFT based on its class and id, same as ownerOf in ERC721
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.nft.v1beta1.QueryProto.QueryOwnerResponse> owner(
        com.cosmos.nft.v1beta1.QueryProto.QueryOwnerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOwnerMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Supply queries the number of NFTs from the given class, same as totalSupply of ERC721.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.nft.v1beta1.QueryProto.QuerySupplyResponse> supply(
        com.cosmos.nft.v1beta1.QueryProto.QuerySupplyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSupplyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * NFTs queries all NFTs of a given class or owner,choose at least one of the two, similar to tokenByIndex in
     * ERC721Enumerable
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.nft.v1beta1.QueryProto.QueryNFTsResponse> nFTs(
        com.cosmos.nft.v1beta1.QueryProto.QueryNFTsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getNFTsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * NFT queries an NFT based on its class and id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.nft.v1beta1.QueryProto.QueryNFTResponse> nFT(
        com.cosmos.nft.v1beta1.QueryProto.QueryNFTRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getNFTMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Class queries an NFT class based on its id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.nft.v1beta1.QueryProto.QueryClassResponse> class_(
        com.cosmos.nft.v1beta1.QueryProto.QueryClassRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClassMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Classes queries all NFT classes
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.nft.v1beta1.QueryProto.QueryClassesResponse> classes(
        com.cosmos.nft.v1beta1.QueryProto.QueryClassesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClassesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BALANCE = 0;
  private static final int METHODID_OWNER = 1;
  private static final int METHODID_SUPPLY = 2;
  private static final int METHODID_NFTS = 3;
  private static final int METHODID_NFT = 4;
  private static final int METHODID_CLASS = 5;
  private static final int METHODID_CLASSES = 6;

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
          serviceImpl.balance((com.cosmos.nft.v1beta1.QueryProto.QueryBalanceRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryBalanceResponse>) responseObserver);
          break;
        case METHODID_OWNER:
          serviceImpl.owner((com.cosmos.nft.v1beta1.QueryProto.QueryOwnerRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryOwnerResponse>) responseObserver);
          break;
        case METHODID_SUPPLY:
          serviceImpl.supply((com.cosmos.nft.v1beta1.QueryProto.QuerySupplyRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QuerySupplyResponse>) responseObserver);
          break;
        case METHODID_NFTS:
          serviceImpl.nFTs((com.cosmos.nft.v1beta1.QueryProto.QueryNFTsRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryNFTsResponse>) responseObserver);
          break;
        case METHODID_NFT:
          serviceImpl.nFT((com.cosmos.nft.v1beta1.QueryProto.QueryNFTRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryNFTResponse>) responseObserver);
          break;
        case METHODID_CLASS:
          serviceImpl.class_((com.cosmos.nft.v1beta1.QueryProto.QueryClassRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryClassResponse>) responseObserver);
          break;
        case METHODID_CLASSES:
          serviceImpl.classes((com.cosmos.nft.v1beta1.QueryProto.QueryClassesRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.nft.v1beta1.QueryProto.QueryClassesResponse>) responseObserver);
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
              com.cosmos.nft.v1beta1.QueryProto.QueryBalanceRequest,
              com.cosmos.nft.v1beta1.QueryProto.QueryBalanceResponse>(
                service, METHODID_BALANCE)))
        .addMethod(
          getOwnerMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.nft.v1beta1.QueryProto.QueryOwnerRequest,
              com.cosmos.nft.v1beta1.QueryProto.QueryOwnerResponse>(
                service, METHODID_OWNER)))
        .addMethod(
          getSupplyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.nft.v1beta1.QueryProto.QuerySupplyRequest,
              com.cosmos.nft.v1beta1.QueryProto.QuerySupplyResponse>(
                service, METHODID_SUPPLY)))
        .addMethod(
          getNFTsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.nft.v1beta1.QueryProto.QueryNFTsRequest,
              com.cosmos.nft.v1beta1.QueryProto.QueryNFTsResponse>(
                service, METHODID_NFTS)))
        .addMethod(
          getNFTMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.nft.v1beta1.QueryProto.QueryNFTRequest,
              com.cosmos.nft.v1beta1.QueryProto.QueryNFTResponse>(
                service, METHODID_NFT)))
        .addMethod(
          getClassMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.nft.v1beta1.QueryProto.QueryClassRequest,
              com.cosmos.nft.v1beta1.QueryProto.QueryClassResponse>(
                service, METHODID_CLASS)))
        .addMethod(
          getClassesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.nft.v1beta1.QueryProto.QueryClassesRequest,
              com.cosmos.nft.v1beta1.QueryProto.QueryClassesResponse>(
                service, METHODID_CLASSES)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmos.nft.v1beta1.QueryProto.getDescriptor();
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
              .addMethod(getOwnerMethod())
              .addMethod(getSupplyMethod())
              .addMethod(getNFTsMethod())
              .addMethod(getNFTMethod())
              .addMethod(getClassMethod())
              .addMethod(getClassesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
