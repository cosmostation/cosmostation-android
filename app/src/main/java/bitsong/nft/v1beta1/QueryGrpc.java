package bitsong.nft.v1beta1;

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
 * Query defines the gRPC querier service for NFT module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: bitsong/nft/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "bitsong.nft.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyRequest,
      bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyResponse> getSupplyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Supply",
      requestType = bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyRequest.class,
      responseType = bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyRequest,
      bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyResponse> getSupplyMethod() {
    io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyRequest, bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyResponse> getSupplyMethod;
    if ((getSupplyMethod = QueryGrpc.getSupplyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSupplyMethod = QueryGrpc.getSupplyMethod) == null) {
          QueryGrpc.getSupplyMethod = getSupplyMethod =
              io.grpc.MethodDescriptor.<bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyRequest, bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Supply"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Supply"))
              .build();
        }
      }
    }
    return getSupplyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerRequest,
      bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerResponse> getOwnerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Owner",
      requestType = bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerRequest.class,
      responseType = bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerRequest,
      bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerResponse> getOwnerMethod() {
    io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerRequest, bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerResponse> getOwnerMethod;
    if ((getOwnerMethod = QueryGrpc.getOwnerMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOwnerMethod = QueryGrpc.getOwnerMethod) == null) {
          QueryGrpc.getOwnerMethod = getOwnerMethod =
              io.grpc.MethodDescriptor.<bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerRequest, bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Owner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Owner"))
              .build();
        }
      }
    }
    return getOwnerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionRequest,
      bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionResponse> getCollectionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Collection",
      requestType = bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionRequest.class,
      responseType = bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionRequest,
      bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionResponse> getCollectionMethod() {
    io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionRequest, bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionResponse> getCollectionMethod;
    if ((getCollectionMethod = QueryGrpc.getCollectionMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCollectionMethod = QueryGrpc.getCollectionMethod) == null) {
          QueryGrpc.getCollectionMethod = getCollectionMethod =
              io.grpc.MethodDescriptor.<bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionRequest, bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Collection"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Collection"))
              .build();
        }
      }
    }
    return getCollectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomRequest,
      bitsong.nft.v1beta1.QueryOuterClass.QueryDenomResponse> getDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Denom",
      requestType = bitsong.nft.v1beta1.QueryOuterClass.QueryDenomRequest.class,
      responseType = bitsong.nft.v1beta1.QueryOuterClass.QueryDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomRequest,
      bitsong.nft.v1beta1.QueryOuterClass.QueryDenomResponse> getDenomMethod() {
    io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomRequest, bitsong.nft.v1beta1.QueryOuterClass.QueryDenomResponse> getDenomMethod;
    if ((getDenomMethod = QueryGrpc.getDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomMethod = QueryGrpc.getDenomMethod) == null) {
          QueryGrpc.getDenomMethod = getDenomMethod =
              io.grpc.MethodDescriptor.<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomRequest, bitsong.nft.v1beta1.QueryOuterClass.QueryDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Denom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.nft.v1beta1.QueryOuterClass.QueryDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.nft.v1beta1.QueryOuterClass.QueryDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Denom"))
              .build();
        }
      }
    }
    return getDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameRequest,
      bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameResponse> getDenomByNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomByName",
      requestType = bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameRequest.class,
      responseType = bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameRequest,
      bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameResponse> getDenomByNameMethod() {
    io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameRequest, bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameResponse> getDenomByNameMethod;
    if ((getDenomByNameMethod = QueryGrpc.getDenomByNameMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomByNameMethod = QueryGrpc.getDenomByNameMethod) == null) {
          QueryGrpc.getDenomByNameMethod = getDenomByNameMethod =
              io.grpc.MethodDescriptor.<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameRequest, bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomByName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomByName"))
              .build();
        }
      }
    }
    return getDenomByNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsRequest,
      bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsResponse> getDenomsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Denoms",
      requestType = bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsRequest.class,
      responseType = bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsRequest,
      bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsResponse> getDenomsMethod() {
    io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsRequest, bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsResponse> getDenomsMethod;
    if ((getDenomsMethod = QueryGrpc.getDenomsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomsMethod = QueryGrpc.getDenomsMethod) == null) {
          QueryGrpc.getDenomsMethod = getDenomsMethod =
              io.grpc.MethodDescriptor.<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsRequest, bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Denoms"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Denoms"))
              .build();
        }
      }
    }
    return getDenomsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryNFTRequest,
      bitsong.nft.v1beta1.QueryOuterClass.QueryNFTResponse> getNFTMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "NFT",
      requestType = bitsong.nft.v1beta1.QueryOuterClass.QueryNFTRequest.class,
      responseType = bitsong.nft.v1beta1.QueryOuterClass.QueryNFTResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryNFTRequest,
      bitsong.nft.v1beta1.QueryOuterClass.QueryNFTResponse> getNFTMethod() {
    io.grpc.MethodDescriptor<bitsong.nft.v1beta1.QueryOuterClass.QueryNFTRequest, bitsong.nft.v1beta1.QueryOuterClass.QueryNFTResponse> getNFTMethod;
    if ((getNFTMethod = QueryGrpc.getNFTMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getNFTMethod = QueryGrpc.getNFTMethod) == null) {
          QueryGrpc.getNFTMethod = getNFTMethod =
              io.grpc.MethodDescriptor.<bitsong.nft.v1beta1.QueryOuterClass.QueryNFTRequest, bitsong.nft.v1beta1.QueryOuterClass.QueryNFTResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NFT"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.nft.v1beta1.QueryOuterClass.QueryNFTRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bitsong.nft.v1beta1.QueryOuterClass.QueryNFTResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("NFT"))
              .build();
        }
      }
    }
    return getNFTMethod;
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
   * Query defines the gRPC querier service for NFT module
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Supply queries the total supply of a given denom or owner
     * </pre>
     */
    public void supply(bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyRequest request,
        io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSupplyMethod(), responseObserver);
    }

    /**
     * <pre>
     * Owner queries the NFTs of the specified owner
     * </pre>
     */
    public void owner(bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerRequest request,
        io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOwnerMethod(), responseObserver);
    }

    /**
     * <pre>
     * Collection queries the NFTs of the specified denom
     * </pre>
     */
    public void collection(bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionRequest request,
        io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCollectionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Denom queries the definition of a given denom
     * </pre>
     */
    public void denom(bitsong.nft.v1beta1.QueryOuterClass.QueryDenomRequest request,
        io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDenomMethod(), responseObserver);
    }

    /**
     * <pre>
     * DenomByName queries the definition of a given denom by name
     * </pre>
     */
    public void denomByName(bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameRequest request,
        io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDenomByNameMethod(), responseObserver);
    }

    /**
     * <pre>
     * Denoms queries all the denoms
     * </pre>
     */
    public void denoms(bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsRequest request,
        io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDenomsMethod(), responseObserver);
    }

    /**
     * <pre>
     * NFT queries the NFT for the given denom and token ID
     * </pre>
     */
    public void nFT(bitsong.nft.v1beta1.QueryOuterClass.QueryNFTRequest request,
        io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryNFTResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNFTMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSupplyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyRequest,
                bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyResponse>(
                  this, METHODID_SUPPLY)))
          .addMethod(
            getOwnerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerRequest,
                bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerResponse>(
                  this, METHODID_OWNER)))
          .addMethod(
            getCollectionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionRequest,
                bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionResponse>(
                  this, METHODID_COLLECTION)))
          .addMethod(
            getDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.nft.v1beta1.QueryOuterClass.QueryDenomRequest,
                bitsong.nft.v1beta1.QueryOuterClass.QueryDenomResponse>(
                  this, METHODID_DENOM)))
          .addMethod(
            getDenomByNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameRequest,
                bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameResponse>(
                  this, METHODID_DENOM_BY_NAME)))
          .addMethod(
            getDenomsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsRequest,
                bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsResponse>(
                  this, METHODID_DENOMS)))
          .addMethod(
            getNFTMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bitsong.nft.v1beta1.QueryOuterClass.QueryNFTRequest,
                bitsong.nft.v1beta1.QueryOuterClass.QueryNFTResponse>(
                  this, METHODID_NFT)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for NFT module
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
     * Supply queries the total supply of a given denom or owner
     * </pre>
     */
    public void supply(bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyRequest request,
        io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSupplyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Owner queries the NFTs of the specified owner
     * </pre>
     */
    public void owner(bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerRequest request,
        io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOwnerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Collection queries the NFTs of the specified denom
     * </pre>
     */
    public void collection(bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionRequest request,
        io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCollectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Denom queries the definition of a given denom
     * </pre>
     */
    public void denom(bitsong.nft.v1beta1.QueryOuterClass.QueryDenomRequest request,
        io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DenomByName queries the definition of a given denom by name
     * </pre>
     */
    public void denomByName(bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameRequest request,
        io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDenomByNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Denoms queries all the denoms
     * </pre>
     */
    public void denoms(bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsRequest request,
        io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDenomsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * NFT queries the NFT for the given denom and token ID
     * </pre>
     */
    public void nFT(bitsong.nft.v1beta1.QueryOuterClass.QueryNFTRequest request,
        io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryNFTResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNFTMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for NFT module
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
     * Supply queries the total supply of a given denom or owner
     * </pre>
     */
    public bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyResponse supply(bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyRequest request) {
      return blockingUnaryCall(
          getChannel(), getSupplyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Owner queries the NFTs of the specified owner
     * </pre>
     */
    public bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerResponse owner(bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerRequest request) {
      return blockingUnaryCall(
          getChannel(), getOwnerMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Collection queries the NFTs of the specified denom
     * </pre>
     */
    public bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionResponse collection(bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionRequest request) {
      return blockingUnaryCall(
          getChannel(), getCollectionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Denom queries the definition of a given denom
     * </pre>
     */
    public bitsong.nft.v1beta1.QueryOuterClass.QueryDenomResponse denom(bitsong.nft.v1beta1.QueryOuterClass.QueryDenomRequest request) {
      return blockingUnaryCall(
          getChannel(), getDenomMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DenomByName queries the definition of a given denom by name
     * </pre>
     */
    public bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameResponse denomByName(bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameRequest request) {
      return blockingUnaryCall(
          getChannel(), getDenomByNameMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Denoms queries all the denoms
     * </pre>
     */
    public bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsResponse denoms(bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsRequest request) {
      return blockingUnaryCall(
          getChannel(), getDenomsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * NFT queries the NFT for the given denom and token ID
     * </pre>
     */
    public bitsong.nft.v1beta1.QueryOuterClass.QueryNFTResponse nFT(bitsong.nft.v1beta1.QueryOuterClass.QueryNFTRequest request) {
      return blockingUnaryCall(
          getChannel(), getNFTMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for NFT module
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
     * Supply queries the total supply of a given denom or owner
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyResponse> supply(
        bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSupplyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Owner queries the NFTs of the specified owner
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerResponse> owner(
        bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOwnerMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Collection queries the NFTs of the specified denom
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionResponse> collection(
        bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCollectionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Denom queries the definition of a given denom
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomResponse> denom(
        bitsong.nft.v1beta1.QueryOuterClass.QueryDenomRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDenomMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DenomByName queries the definition of a given denom by name
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameResponse> denomByName(
        bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDenomByNameMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Denoms queries all the denoms
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsResponse> denoms(
        bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDenomsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * NFT queries the NFT for the given denom and token ID
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<bitsong.nft.v1beta1.QueryOuterClass.QueryNFTResponse> nFT(
        bitsong.nft.v1beta1.QueryOuterClass.QueryNFTRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNFTMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SUPPLY = 0;
  private static final int METHODID_OWNER = 1;
  private static final int METHODID_COLLECTION = 2;
  private static final int METHODID_DENOM = 3;
  private static final int METHODID_DENOM_BY_NAME = 4;
  private static final int METHODID_DENOMS = 5;
  private static final int METHODID_NFT = 6;

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
        case METHODID_SUPPLY:
          serviceImpl.supply((bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyRequest) request,
              (io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QuerySupplyResponse>) responseObserver);
          break;
        case METHODID_OWNER:
          serviceImpl.owner((bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerRequest) request,
              (io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryOwnerResponse>) responseObserver);
          break;
        case METHODID_COLLECTION:
          serviceImpl.collection((bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionRequest) request,
              (io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryCollectionResponse>) responseObserver);
          break;
        case METHODID_DENOM:
          serviceImpl.denom((bitsong.nft.v1beta1.QueryOuterClass.QueryDenomRequest) request,
              (io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomResponse>) responseObserver);
          break;
        case METHODID_DENOM_BY_NAME:
          serviceImpl.denomByName((bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameRequest) request,
              (io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomByNameResponse>) responseObserver);
          break;
        case METHODID_DENOMS:
          serviceImpl.denoms((bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsRequest) request,
              (io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryDenomsResponse>) responseObserver);
          break;
        case METHODID_NFT:
          serviceImpl.nFT((bitsong.nft.v1beta1.QueryOuterClass.QueryNFTRequest) request,
              (io.grpc.stub.StreamObserver<bitsong.nft.v1beta1.QueryOuterClass.QueryNFTResponse>) responseObserver);
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
      return bitsong.nft.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getSupplyMethod())
              .addMethod(getOwnerMethod())
              .addMethod(getCollectionMethod())
              .addMethod(getDenomMethod())
              .addMethod(getDenomByNameMethod())
              .addMethod(getDenomsMethod())
              .addMethod(getNFTMethod())
              .build();
        }
      }
    }
    return result;
  }
}
