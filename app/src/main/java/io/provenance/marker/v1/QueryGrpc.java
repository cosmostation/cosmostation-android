package io.provenance.marker.v1;

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
 * Query defines the gRPC querier service for marker module.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: provenance/marker/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "provenance.marker.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryParamsRequest,
      io.provenance.marker.v1.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = io.provenance.marker.v1.QueryParamsRequest.class,
      responseType = io.provenance.marker.v1.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryParamsRequest,
      io.provenance.marker.v1.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryParamsRequest, io.provenance.marker.v1.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.QueryParamsRequest, io.provenance.marker.v1.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryAllMarkersRequest,
      io.provenance.marker.v1.QueryAllMarkersResponse> getAllMarkersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllMarkers",
      requestType = io.provenance.marker.v1.QueryAllMarkersRequest.class,
      responseType = io.provenance.marker.v1.QueryAllMarkersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryAllMarkersRequest,
      io.provenance.marker.v1.QueryAllMarkersResponse> getAllMarkersMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryAllMarkersRequest, io.provenance.marker.v1.QueryAllMarkersResponse> getAllMarkersMethod;
    if ((getAllMarkersMethod = QueryGrpc.getAllMarkersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllMarkersMethod = QueryGrpc.getAllMarkersMethod) == null) {
          QueryGrpc.getAllMarkersMethod = getAllMarkersMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.QueryAllMarkersRequest, io.provenance.marker.v1.QueryAllMarkersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllMarkers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.QueryAllMarkersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.QueryAllMarkersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllMarkers"))
              .build();
        }
      }
    }
    return getAllMarkersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryMarkerRequest,
      io.provenance.marker.v1.QueryMarkerResponse> getMarkerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Marker",
      requestType = io.provenance.marker.v1.QueryMarkerRequest.class,
      responseType = io.provenance.marker.v1.QueryMarkerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryMarkerRequest,
      io.provenance.marker.v1.QueryMarkerResponse> getMarkerMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryMarkerRequest, io.provenance.marker.v1.QueryMarkerResponse> getMarkerMethod;
    if ((getMarkerMethod = QueryGrpc.getMarkerMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getMarkerMethod = QueryGrpc.getMarkerMethod) == null) {
          QueryGrpc.getMarkerMethod = getMarkerMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.QueryMarkerRequest, io.provenance.marker.v1.QueryMarkerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Marker"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.QueryMarkerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.QueryMarkerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Marker"))
              .build();
        }
      }
    }
    return getMarkerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryHoldingRequest,
      io.provenance.marker.v1.QueryHoldingResponse> getHoldingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Holding",
      requestType = io.provenance.marker.v1.QueryHoldingRequest.class,
      responseType = io.provenance.marker.v1.QueryHoldingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryHoldingRequest,
      io.provenance.marker.v1.QueryHoldingResponse> getHoldingMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryHoldingRequest, io.provenance.marker.v1.QueryHoldingResponse> getHoldingMethod;
    if ((getHoldingMethod = QueryGrpc.getHoldingMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHoldingMethod = QueryGrpc.getHoldingMethod) == null) {
          QueryGrpc.getHoldingMethod = getHoldingMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.QueryHoldingRequest, io.provenance.marker.v1.QueryHoldingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Holding"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.QueryHoldingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.QueryHoldingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Holding"))
              .build();
        }
      }
    }
    return getHoldingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.QuerySupplyRequest,
      io.provenance.marker.v1.QuerySupplyResponse> getSupplyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Supply",
      requestType = io.provenance.marker.v1.QuerySupplyRequest.class,
      responseType = io.provenance.marker.v1.QuerySupplyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.QuerySupplyRequest,
      io.provenance.marker.v1.QuerySupplyResponse> getSupplyMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.QuerySupplyRequest, io.provenance.marker.v1.QuerySupplyResponse> getSupplyMethod;
    if ((getSupplyMethod = QueryGrpc.getSupplyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSupplyMethod = QueryGrpc.getSupplyMethod) == null) {
          QueryGrpc.getSupplyMethod = getSupplyMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.QuerySupplyRequest, io.provenance.marker.v1.QuerySupplyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Supply"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.QuerySupplyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.QuerySupplyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Supply"))
              .build();
        }
      }
    }
    return getSupplyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryEscrowRequest,
      io.provenance.marker.v1.QueryEscrowResponse> getEscrowMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Escrow",
      requestType = io.provenance.marker.v1.QueryEscrowRequest.class,
      responseType = io.provenance.marker.v1.QueryEscrowResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryEscrowRequest,
      io.provenance.marker.v1.QueryEscrowResponse> getEscrowMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryEscrowRequest, io.provenance.marker.v1.QueryEscrowResponse> getEscrowMethod;
    if ((getEscrowMethod = QueryGrpc.getEscrowMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEscrowMethod = QueryGrpc.getEscrowMethod) == null) {
          QueryGrpc.getEscrowMethod = getEscrowMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.QueryEscrowRequest, io.provenance.marker.v1.QueryEscrowResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Escrow"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.QueryEscrowRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.QueryEscrowResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Escrow"))
              .build();
        }
      }
    }
    return getEscrowMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryAccessRequest,
      io.provenance.marker.v1.QueryAccessResponse> getAccessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Access",
      requestType = io.provenance.marker.v1.QueryAccessRequest.class,
      responseType = io.provenance.marker.v1.QueryAccessResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryAccessRequest,
      io.provenance.marker.v1.QueryAccessResponse> getAccessMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryAccessRequest, io.provenance.marker.v1.QueryAccessResponse> getAccessMethod;
    if ((getAccessMethod = QueryGrpc.getAccessMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccessMethod = QueryGrpc.getAccessMethod) == null) {
          QueryGrpc.getAccessMethod = getAccessMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.QueryAccessRequest, io.provenance.marker.v1.QueryAccessResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Access"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.QueryAccessRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.QueryAccessResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Access"))
              .build();
        }
      }
    }
    return getAccessMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryDenomMetadataRequest,
      io.provenance.marker.v1.QueryDenomMetadataResponse> getDenomMetadataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomMetadata",
      requestType = io.provenance.marker.v1.QueryDenomMetadataRequest.class,
      responseType = io.provenance.marker.v1.QueryDenomMetadataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryDenomMetadataRequest,
      io.provenance.marker.v1.QueryDenomMetadataResponse> getDenomMetadataMethod() {
    io.grpc.MethodDescriptor<io.provenance.marker.v1.QueryDenomMetadataRequest, io.provenance.marker.v1.QueryDenomMetadataResponse> getDenomMetadataMethod;
    if ((getDenomMetadataMethod = QueryGrpc.getDenomMetadataMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomMetadataMethod = QueryGrpc.getDenomMetadataMethod) == null) {
          QueryGrpc.getDenomMetadataMethod = getDenomMetadataMethod =
              io.grpc.MethodDescriptor.<io.provenance.marker.v1.QueryDenomMetadataRequest, io.provenance.marker.v1.QueryDenomMetadataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomMetadata"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.QueryDenomMetadataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.marker.v1.QueryDenomMetadataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomMetadata"))
              .build();
        }
      }
    }
    return getDenomMetadataMethod;
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
   * Query defines the gRPC querier service for marker module.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Params queries the parameters of x/bank module.
     * </pre>
     */
    public void params(io.provenance.marker.v1.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns a list of all markers on the blockchain
     * </pre>
     */
    public void allMarkers(io.provenance.marker.v1.QueryAllMarkersRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryAllMarkersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAllMarkersMethod(), responseObserver);
    }

    /**
     * <pre>
     * query for a single marker by denom or address
     * </pre>
     */
    public void marker(io.provenance.marker.v1.QueryMarkerRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryMarkerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMarkerMethod(), responseObserver);
    }

    /**
     * <pre>
     * query for all accounts holding the given marker coins
     * </pre>
     */
    public void holding(io.provenance.marker.v1.QueryHoldingRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryHoldingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHoldingMethod(), responseObserver);
    }

    /**
     * <pre>
     * query for supply of coin on a marker account
     * </pre>
     */
    public void supply(io.provenance.marker.v1.QuerySupplyRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.QuerySupplyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSupplyMethod(), responseObserver);
    }

    /**
     * <pre>
     * query for coins on a marker account
     * </pre>
     */
    public void escrow(io.provenance.marker.v1.QueryEscrowRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryEscrowResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEscrowMethod(), responseObserver);
    }

    /**
     * <pre>
     * query for access records on an account
     * </pre>
     */
    public void access(io.provenance.marker.v1.QueryAccessRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryAccessResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAccessMethod(), responseObserver);
    }

    /**
     * <pre>
     * query for access records on an account
     * </pre>
     */
    public void denomMetadata(io.provenance.marker.v1.QueryDenomMetadataRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryDenomMetadataResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDenomMetadataMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.QueryParamsRequest,
                io.provenance.marker.v1.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getAllMarkersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.QueryAllMarkersRequest,
                io.provenance.marker.v1.QueryAllMarkersResponse>(
                  this, METHODID_ALL_MARKERS)))
          .addMethod(
            getMarkerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.QueryMarkerRequest,
                io.provenance.marker.v1.QueryMarkerResponse>(
                  this, METHODID_MARKER)))
          .addMethod(
            getHoldingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.QueryHoldingRequest,
                io.provenance.marker.v1.QueryHoldingResponse>(
                  this, METHODID_HOLDING)))
          .addMethod(
            getSupplyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.QuerySupplyRequest,
                io.provenance.marker.v1.QuerySupplyResponse>(
                  this, METHODID_SUPPLY)))
          .addMethod(
            getEscrowMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.QueryEscrowRequest,
                io.provenance.marker.v1.QueryEscrowResponse>(
                  this, METHODID_ESCROW)))
          .addMethod(
            getAccessMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.QueryAccessRequest,
                io.provenance.marker.v1.QueryAccessResponse>(
                  this, METHODID_ACCESS)))
          .addMethod(
            getDenomMetadataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.marker.v1.QueryDenomMetadataRequest,
                io.provenance.marker.v1.QueryDenomMetadataResponse>(
                  this, METHODID_DENOM_METADATA)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for marker module.
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
     * Params queries the parameters of x/bank module.
     * </pre>
     */
    public void params(io.provenance.marker.v1.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns a list of all markers on the blockchain
     * </pre>
     */
    public void allMarkers(io.provenance.marker.v1.QueryAllMarkersRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryAllMarkersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAllMarkersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * query for a single marker by denom or address
     * </pre>
     */
    public void marker(io.provenance.marker.v1.QueryMarkerRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryMarkerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMarkerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * query for all accounts holding the given marker coins
     * </pre>
     */
    public void holding(io.provenance.marker.v1.QueryHoldingRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryHoldingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHoldingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * query for supply of coin on a marker account
     * </pre>
     */
    public void supply(io.provenance.marker.v1.QuerySupplyRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.QuerySupplyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSupplyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * query for coins on a marker account
     * </pre>
     */
    public void escrow(io.provenance.marker.v1.QueryEscrowRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryEscrowResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEscrowMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * query for access records on an account
     * </pre>
     */
    public void access(io.provenance.marker.v1.QueryAccessRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryAccessResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccessMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * query for access records on an account
     * </pre>
     */
    public void denomMetadata(io.provenance.marker.v1.QueryDenomMetadataRequest request,
        io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryDenomMetadataResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDenomMetadataMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for marker module.
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
     * Params queries the parameters of x/bank module.
     * </pre>
     */
    public io.provenance.marker.v1.QueryParamsResponse params(io.provenance.marker.v1.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns a list of all markers on the blockchain
     * </pre>
     */
    public io.provenance.marker.v1.QueryAllMarkersResponse allMarkers(io.provenance.marker.v1.QueryAllMarkersRequest request) {
      return blockingUnaryCall(
          getChannel(), getAllMarkersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * query for a single marker by denom or address
     * </pre>
     */
    public io.provenance.marker.v1.QueryMarkerResponse marker(io.provenance.marker.v1.QueryMarkerRequest request) {
      return blockingUnaryCall(
          getChannel(), getMarkerMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * query for all accounts holding the given marker coins
     * </pre>
     */
    public io.provenance.marker.v1.QueryHoldingResponse holding(io.provenance.marker.v1.QueryHoldingRequest request) {
      return blockingUnaryCall(
          getChannel(), getHoldingMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * query for supply of coin on a marker account
     * </pre>
     */
    public io.provenance.marker.v1.QuerySupplyResponse supply(io.provenance.marker.v1.QuerySupplyRequest request) {
      return blockingUnaryCall(
          getChannel(), getSupplyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * query for coins on a marker account
     * </pre>
     */
    public io.provenance.marker.v1.QueryEscrowResponse escrow(io.provenance.marker.v1.QueryEscrowRequest request) {
      return blockingUnaryCall(
          getChannel(), getEscrowMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * query for access records on an account
     * </pre>
     */
    public io.provenance.marker.v1.QueryAccessResponse access(io.provenance.marker.v1.QueryAccessRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccessMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * query for access records on an account
     * </pre>
     */
    public io.provenance.marker.v1.QueryDenomMetadataResponse denomMetadata(io.provenance.marker.v1.QueryDenomMetadataRequest request) {
      return blockingUnaryCall(
          getChannel(), getDenomMetadataMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for marker module.
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
     * Params queries the parameters of x/bank module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.QueryParamsResponse> params(
        io.provenance.marker.v1.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns a list of all markers on the blockchain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.QueryAllMarkersResponse> allMarkers(
        io.provenance.marker.v1.QueryAllMarkersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAllMarkersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * query for a single marker by denom or address
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.QueryMarkerResponse> marker(
        io.provenance.marker.v1.QueryMarkerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMarkerMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * query for all accounts holding the given marker coins
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.QueryHoldingResponse> holding(
        io.provenance.marker.v1.QueryHoldingRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHoldingMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * query for supply of coin on a marker account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.QuerySupplyResponse> supply(
        io.provenance.marker.v1.QuerySupplyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSupplyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * query for coins on a marker account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.QueryEscrowResponse> escrow(
        io.provenance.marker.v1.QueryEscrowRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEscrowMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * query for access records on an account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.QueryAccessResponse> access(
        io.provenance.marker.v1.QueryAccessRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccessMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * query for access records on an account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.marker.v1.QueryDenomMetadataResponse> denomMetadata(
        io.provenance.marker.v1.QueryDenomMetadataRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDenomMetadataMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_ALL_MARKERS = 1;
  private static final int METHODID_MARKER = 2;
  private static final int METHODID_HOLDING = 3;
  private static final int METHODID_SUPPLY = 4;
  private static final int METHODID_ESCROW = 5;
  private static final int METHODID_ACCESS = 6;
  private static final int METHODID_DENOM_METADATA = 7;

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
          serviceImpl.params((io.provenance.marker.v1.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_ALL_MARKERS:
          serviceImpl.allMarkers((io.provenance.marker.v1.QueryAllMarkersRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryAllMarkersResponse>) responseObserver);
          break;
        case METHODID_MARKER:
          serviceImpl.marker((io.provenance.marker.v1.QueryMarkerRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryMarkerResponse>) responseObserver);
          break;
        case METHODID_HOLDING:
          serviceImpl.holding((io.provenance.marker.v1.QueryHoldingRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryHoldingResponse>) responseObserver);
          break;
        case METHODID_SUPPLY:
          serviceImpl.supply((io.provenance.marker.v1.QuerySupplyRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.QuerySupplyResponse>) responseObserver);
          break;
        case METHODID_ESCROW:
          serviceImpl.escrow((io.provenance.marker.v1.QueryEscrowRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryEscrowResponse>) responseObserver);
          break;
        case METHODID_ACCESS:
          serviceImpl.access((io.provenance.marker.v1.QueryAccessRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryAccessResponse>) responseObserver);
          break;
        case METHODID_DENOM_METADATA:
          serviceImpl.denomMetadata((io.provenance.marker.v1.QueryDenomMetadataRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.marker.v1.QueryDenomMetadataResponse>) responseObserver);
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
      return io.provenance.marker.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getAllMarkersMethod())
              .addMethod(getMarkerMethod())
              .addMethod(getHoldingMethod())
              .addMethod(getSupplyMethod())
              .addMethod(getEscrowMethod())
              .addMethod(getAccessMethod())
              .addMethod(getDenomMetadataMethod())
              .build();
        }
      }
    }
    return result;
  }
}
