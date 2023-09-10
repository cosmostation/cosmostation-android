package com.stride.stakeibc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: stride/stakeibc/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "stride.stakeibc.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryParamsRequest,
      com.stride.stakeibc.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.stride.stakeibc.QueryProto.QueryParamsRequest.class,
      responseType = com.stride.stakeibc.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryParamsRequest,
      com.stride.stakeibc.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryParamsRequest, com.stride.stakeibc.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.stride.stakeibc.QueryProto.QueryParamsRequest, com.stride.stakeibc.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryGetValidatorsRequest,
      com.stride.stakeibc.QueryProto.QueryGetValidatorsResponse> getValidatorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Validators",
      requestType = com.stride.stakeibc.QueryProto.QueryGetValidatorsRequest.class,
      responseType = com.stride.stakeibc.QueryProto.QueryGetValidatorsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryGetValidatorsRequest,
      com.stride.stakeibc.QueryProto.QueryGetValidatorsResponse> getValidatorsMethod() {
    io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryGetValidatorsRequest, com.stride.stakeibc.QueryProto.QueryGetValidatorsResponse> getValidatorsMethod;
    if ((getValidatorsMethod = QueryGrpc.getValidatorsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorsMethod = QueryGrpc.getValidatorsMethod) == null) {
          QueryGrpc.getValidatorsMethod = getValidatorsMethod =
              io.grpc.MethodDescriptor.<com.stride.stakeibc.QueryProto.QueryGetValidatorsRequest, com.stride.stakeibc.QueryProto.QueryGetValidatorsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Validators"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryGetValidatorsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryGetValidatorsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Validators"))
              .build();
        }
      }
    }
    return getValidatorsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryGetHostZoneRequest,
      com.stride.stakeibc.QueryProto.QueryGetHostZoneResponse> getHostZoneMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HostZone",
      requestType = com.stride.stakeibc.QueryProto.QueryGetHostZoneRequest.class,
      responseType = com.stride.stakeibc.QueryProto.QueryGetHostZoneResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryGetHostZoneRequest,
      com.stride.stakeibc.QueryProto.QueryGetHostZoneResponse> getHostZoneMethod() {
    io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryGetHostZoneRequest, com.stride.stakeibc.QueryProto.QueryGetHostZoneResponse> getHostZoneMethod;
    if ((getHostZoneMethod = QueryGrpc.getHostZoneMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHostZoneMethod = QueryGrpc.getHostZoneMethod) == null) {
          QueryGrpc.getHostZoneMethod = getHostZoneMethod =
              io.grpc.MethodDescriptor.<com.stride.stakeibc.QueryProto.QueryGetHostZoneRequest, com.stride.stakeibc.QueryProto.QueryGetHostZoneResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HostZone"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryGetHostZoneRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryGetHostZoneResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("HostZone"))
              .build();
        }
      }
    }
    return getHostZoneMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryAllHostZoneRequest,
      com.stride.stakeibc.QueryProto.QueryAllHostZoneResponse> getHostZoneAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HostZoneAll",
      requestType = com.stride.stakeibc.QueryProto.QueryAllHostZoneRequest.class,
      responseType = com.stride.stakeibc.QueryProto.QueryAllHostZoneResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryAllHostZoneRequest,
      com.stride.stakeibc.QueryProto.QueryAllHostZoneResponse> getHostZoneAllMethod() {
    io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryAllHostZoneRequest, com.stride.stakeibc.QueryProto.QueryAllHostZoneResponse> getHostZoneAllMethod;
    if ((getHostZoneAllMethod = QueryGrpc.getHostZoneAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHostZoneAllMethod = QueryGrpc.getHostZoneAllMethod) == null) {
          QueryGrpc.getHostZoneAllMethod = getHostZoneAllMethod =
              io.grpc.MethodDescriptor.<com.stride.stakeibc.QueryProto.QueryAllHostZoneRequest, com.stride.stakeibc.QueryProto.QueryAllHostZoneResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HostZoneAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryAllHostZoneRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryAllHostZoneResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("HostZoneAll"))
              .build();
        }
      }
    }
    return getHostZoneAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryModuleAddressRequest,
      com.stride.stakeibc.QueryProto.QueryModuleAddressResponse> getModuleAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ModuleAddress",
      requestType = com.stride.stakeibc.QueryProto.QueryModuleAddressRequest.class,
      responseType = com.stride.stakeibc.QueryProto.QueryModuleAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryModuleAddressRequest,
      com.stride.stakeibc.QueryProto.QueryModuleAddressResponse> getModuleAddressMethod() {
    io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryModuleAddressRequest, com.stride.stakeibc.QueryProto.QueryModuleAddressResponse> getModuleAddressMethod;
    if ((getModuleAddressMethod = QueryGrpc.getModuleAddressMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getModuleAddressMethod = QueryGrpc.getModuleAddressMethod) == null) {
          QueryGrpc.getModuleAddressMethod = getModuleAddressMethod =
              io.grpc.MethodDescriptor.<com.stride.stakeibc.QueryProto.QueryModuleAddressRequest, com.stride.stakeibc.QueryProto.QueryModuleAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ModuleAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryModuleAddressRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryModuleAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ModuleAddress"))
              .build();
        }
      }
    }
    return getModuleAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressRequest,
      com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressResponse> getInterchainAccountFromAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InterchainAccountFromAddress",
      requestType = com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressRequest.class,
      responseType = com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressRequest,
      com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressResponse> getInterchainAccountFromAddressMethod() {
    io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressRequest, com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressResponse> getInterchainAccountFromAddressMethod;
    if ((getInterchainAccountFromAddressMethod = QueryGrpc.getInterchainAccountFromAddressMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getInterchainAccountFromAddressMethod = QueryGrpc.getInterchainAccountFromAddressMethod) == null) {
          QueryGrpc.getInterchainAccountFromAddressMethod = getInterchainAccountFromAddressMethod =
              io.grpc.MethodDescriptor.<com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressRequest, com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InterchainAccountFromAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("InterchainAccountFromAddress"))
              .build();
        }
      }
    }
    return getInterchainAccountFromAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryGetEpochTrackerRequest,
      com.stride.stakeibc.QueryProto.QueryGetEpochTrackerResponse> getEpochTrackerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochTracker",
      requestType = com.stride.stakeibc.QueryProto.QueryGetEpochTrackerRequest.class,
      responseType = com.stride.stakeibc.QueryProto.QueryGetEpochTrackerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryGetEpochTrackerRequest,
      com.stride.stakeibc.QueryProto.QueryGetEpochTrackerResponse> getEpochTrackerMethod() {
    io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryGetEpochTrackerRequest, com.stride.stakeibc.QueryProto.QueryGetEpochTrackerResponse> getEpochTrackerMethod;
    if ((getEpochTrackerMethod = QueryGrpc.getEpochTrackerMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochTrackerMethod = QueryGrpc.getEpochTrackerMethod) == null) {
          QueryGrpc.getEpochTrackerMethod = getEpochTrackerMethod =
              io.grpc.MethodDescriptor.<com.stride.stakeibc.QueryProto.QueryGetEpochTrackerRequest, com.stride.stakeibc.QueryProto.QueryGetEpochTrackerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochTracker"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryGetEpochTrackerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryGetEpochTrackerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochTracker"))
              .build();
        }
      }
    }
    return getEpochTrackerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryAllEpochTrackerRequest,
      com.stride.stakeibc.QueryProto.QueryAllEpochTrackerResponse> getEpochTrackerAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochTrackerAll",
      requestType = com.stride.stakeibc.QueryProto.QueryAllEpochTrackerRequest.class,
      responseType = com.stride.stakeibc.QueryProto.QueryAllEpochTrackerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryAllEpochTrackerRequest,
      com.stride.stakeibc.QueryProto.QueryAllEpochTrackerResponse> getEpochTrackerAllMethod() {
    io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryAllEpochTrackerRequest, com.stride.stakeibc.QueryProto.QueryAllEpochTrackerResponse> getEpochTrackerAllMethod;
    if ((getEpochTrackerAllMethod = QueryGrpc.getEpochTrackerAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochTrackerAllMethod = QueryGrpc.getEpochTrackerAllMethod) == null) {
          QueryGrpc.getEpochTrackerAllMethod = getEpochTrackerAllMethod =
              io.grpc.MethodDescriptor.<com.stride.stakeibc.QueryProto.QueryAllEpochTrackerRequest, com.stride.stakeibc.QueryProto.QueryAllEpochTrackerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochTrackerAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryAllEpochTrackerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryAllEpochTrackerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochTrackerAll"))
              .build();
        }
      }
    }
    return getEpochTrackerAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceRequest,
      com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceResponse> getNextPacketSequenceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "NextPacketSequence",
      requestType = com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceRequest.class,
      responseType = com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceRequest,
      com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceResponse> getNextPacketSequenceMethod() {
    io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceRequest, com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceResponse> getNextPacketSequenceMethod;
    if ((getNextPacketSequenceMethod = QueryGrpc.getNextPacketSequenceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getNextPacketSequenceMethod = QueryGrpc.getNextPacketSequenceMethod) == null) {
          QueryGrpc.getNextPacketSequenceMethod = getNextPacketSequenceMethod =
              io.grpc.MethodDescriptor.<com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceRequest, com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NextPacketSequence"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("NextPacketSequence"))
              .build();
        }
      }
    }
    return getNextPacketSequenceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryAddressUnbondings,
      com.stride.stakeibc.QueryProto.QueryAddressUnbondingsResponse> getAddressUnbondingsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddressUnbondings",
      requestType = com.stride.stakeibc.QueryProto.QueryAddressUnbondings.class,
      responseType = com.stride.stakeibc.QueryProto.QueryAddressUnbondingsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryAddressUnbondings,
      com.stride.stakeibc.QueryProto.QueryAddressUnbondingsResponse> getAddressUnbondingsMethod() {
    io.grpc.MethodDescriptor<com.stride.stakeibc.QueryProto.QueryAddressUnbondings, com.stride.stakeibc.QueryProto.QueryAddressUnbondingsResponse> getAddressUnbondingsMethod;
    if ((getAddressUnbondingsMethod = QueryGrpc.getAddressUnbondingsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAddressUnbondingsMethod = QueryGrpc.getAddressUnbondingsMethod) == null) {
          QueryGrpc.getAddressUnbondingsMethod = getAddressUnbondingsMethod =
              io.grpc.MethodDescriptor.<com.stride.stakeibc.QueryProto.QueryAddressUnbondings, com.stride.stakeibc.QueryProto.QueryAddressUnbondingsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddressUnbondings"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryAddressUnbondings.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.stride.stakeibc.QueryProto.QueryAddressUnbondingsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AddressUnbondings"))
              .build();
        }
      }
    }
    return getAddressUnbondingsMethod;
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    default void params(com.stride.stakeibc.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a Validator by host zone.
     * </pre>
     */
    default void validators(com.stride.stakeibc.QueryProto.QueryGetValidatorsRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryGetValidatorsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidatorsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a HostZone by id.
     * </pre>
     */
    default void hostZone(com.stride.stakeibc.QueryProto.QueryGetHostZoneRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryGetHostZoneResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHostZoneMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of HostZone items.
     * </pre>
     */
    default void hostZoneAll(com.stride.stakeibc.QueryProto.QueryAllHostZoneRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryAllHostZoneResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHostZoneAllMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of ModuleAddress items.
     * </pre>
     */
    default void moduleAddress(com.stride.stakeibc.QueryProto.QueryModuleAddressRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryModuleAddressResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getModuleAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * QueryInterchainAccountFromAddress returns the interchain account for given
     * owner address on a given connection pair
     * </pre>
     */
    default void interchainAccountFromAddress(com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInterchainAccountFromAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a EpochTracker by index.
     * </pre>
     */
    default void epochTracker(com.stride.stakeibc.QueryProto.QueryGetEpochTrackerRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryGetEpochTrackerResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEpochTrackerMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of EpochTracker items.
     * </pre>
     */
    default void epochTrackerAll(com.stride.stakeibc.QueryProto.QueryAllEpochTrackerRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryAllEpochTrackerResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEpochTrackerAllMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries the next packet sequence for one for a given channel
     * </pre>
     */
    default void nextPacketSequence(com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getNextPacketSequenceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries an address's unbondings
     * </pre>
     */
    default void addressUnbondings(com.stride.stakeibc.QueryProto.QueryAddressUnbondings request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryAddressUnbondingsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddressUnbondingsMethod(), responseObserver);
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public void params(com.stride.stakeibc.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a Validator by host zone.
     * </pre>
     */
    public void validators(com.stride.stakeibc.QueryProto.QueryGetValidatorsRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryGetValidatorsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidatorsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a HostZone by id.
     * </pre>
     */
    public void hostZone(com.stride.stakeibc.QueryProto.QueryGetHostZoneRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryGetHostZoneResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHostZoneMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of HostZone items.
     * </pre>
     */
    public void hostZoneAll(com.stride.stakeibc.QueryProto.QueryAllHostZoneRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryAllHostZoneResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHostZoneAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of ModuleAddress items.
     * </pre>
     */
    public void moduleAddress(com.stride.stakeibc.QueryProto.QueryModuleAddressRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryModuleAddressResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getModuleAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * QueryInterchainAccountFromAddress returns the interchain account for given
     * owner address on a given connection pair
     * </pre>
     */
    public void interchainAccountFromAddress(com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInterchainAccountFromAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a EpochTracker by index.
     * </pre>
     */
    public void epochTracker(com.stride.stakeibc.QueryProto.QueryGetEpochTrackerRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryGetEpochTrackerResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEpochTrackerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of EpochTracker items.
     * </pre>
     */
    public void epochTrackerAll(com.stride.stakeibc.QueryProto.QueryAllEpochTrackerRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryAllEpochTrackerResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEpochTrackerAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries the next packet sequence for one for a given channel
     * </pre>
     */
    public void nextPacketSequence(com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceRequest request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getNextPacketSequenceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries an address's unbondings
     * </pre>
     */
    public void addressUnbondings(com.stride.stakeibc.QueryProto.QueryAddressUnbondings request,
        io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryAddressUnbondingsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddressUnbondingsMethod(), getCallOptions()), request, responseObserver);
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public com.stride.stakeibc.QueryProto.QueryParamsResponse params(com.stride.stakeibc.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a Validator by host zone.
     * </pre>
     */
    public com.stride.stakeibc.QueryProto.QueryGetValidatorsResponse validators(com.stride.stakeibc.QueryProto.QueryGetValidatorsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidatorsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a HostZone by id.
     * </pre>
     */
    public com.stride.stakeibc.QueryProto.QueryGetHostZoneResponse hostZone(com.stride.stakeibc.QueryProto.QueryGetHostZoneRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHostZoneMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of HostZone items.
     * </pre>
     */
    public com.stride.stakeibc.QueryProto.QueryAllHostZoneResponse hostZoneAll(com.stride.stakeibc.QueryProto.QueryAllHostZoneRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHostZoneAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of ModuleAddress items.
     * </pre>
     */
    public com.stride.stakeibc.QueryProto.QueryModuleAddressResponse moduleAddress(com.stride.stakeibc.QueryProto.QueryModuleAddressRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getModuleAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * QueryInterchainAccountFromAddress returns the interchain account for given
     * owner address on a given connection pair
     * </pre>
     */
    public com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressResponse interchainAccountFromAddress(com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInterchainAccountFromAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a EpochTracker by index.
     * </pre>
     */
    public com.stride.stakeibc.QueryProto.QueryGetEpochTrackerResponse epochTracker(com.stride.stakeibc.QueryProto.QueryGetEpochTrackerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEpochTrackerMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of EpochTracker items.
     * </pre>
     */
    public com.stride.stakeibc.QueryProto.QueryAllEpochTrackerResponse epochTrackerAll(com.stride.stakeibc.QueryProto.QueryAllEpochTrackerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEpochTrackerAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries the next packet sequence for one for a given channel
     * </pre>
     */
    public com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceResponse nextPacketSequence(com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getNextPacketSequenceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries an address's unbondings
     * </pre>
     */
    public com.stride.stakeibc.QueryProto.QueryAddressUnbondingsResponse addressUnbondings(com.stride.stakeibc.QueryProto.QueryAddressUnbondings request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddressUnbondingsMethod(), getCallOptions(), request);
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.stakeibc.QueryProto.QueryParamsResponse> params(
        com.stride.stakeibc.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a Validator by host zone.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.stakeibc.QueryProto.QueryGetValidatorsResponse> validators(
        com.stride.stakeibc.QueryProto.QueryGetValidatorsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidatorsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a HostZone by id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.stakeibc.QueryProto.QueryGetHostZoneResponse> hostZone(
        com.stride.stakeibc.QueryProto.QueryGetHostZoneRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHostZoneMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of HostZone items.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.stakeibc.QueryProto.QueryAllHostZoneResponse> hostZoneAll(
        com.stride.stakeibc.QueryProto.QueryAllHostZoneRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHostZoneAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of ModuleAddress items.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.stakeibc.QueryProto.QueryModuleAddressResponse> moduleAddress(
        com.stride.stakeibc.QueryProto.QueryModuleAddressRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getModuleAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * QueryInterchainAccountFromAddress returns the interchain account for given
     * owner address on a given connection pair
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressResponse> interchainAccountFromAddress(
        com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInterchainAccountFromAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a EpochTracker by index.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.stakeibc.QueryProto.QueryGetEpochTrackerResponse> epochTracker(
        com.stride.stakeibc.QueryProto.QueryGetEpochTrackerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEpochTrackerMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of EpochTracker items.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.stakeibc.QueryProto.QueryAllEpochTrackerResponse> epochTrackerAll(
        com.stride.stakeibc.QueryProto.QueryAllEpochTrackerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEpochTrackerAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries the next packet sequence for one for a given channel
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceResponse> nextPacketSequence(
        com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getNextPacketSequenceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries an address's unbondings
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.stride.stakeibc.QueryProto.QueryAddressUnbondingsResponse> addressUnbondings(
        com.stride.stakeibc.QueryProto.QueryAddressUnbondings request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddressUnbondingsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_VALIDATORS = 1;
  private static final int METHODID_HOST_ZONE = 2;
  private static final int METHODID_HOST_ZONE_ALL = 3;
  private static final int METHODID_MODULE_ADDRESS = 4;
  private static final int METHODID_INTERCHAIN_ACCOUNT_FROM_ADDRESS = 5;
  private static final int METHODID_EPOCH_TRACKER = 6;
  private static final int METHODID_EPOCH_TRACKER_ALL = 7;
  private static final int METHODID_NEXT_PACKET_SEQUENCE = 8;
  private static final int METHODID_ADDRESS_UNBONDINGS = 9;

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
        case METHODID_PARAMS:
          serviceImpl.params((com.stride.stakeibc.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_VALIDATORS:
          serviceImpl.validators((com.stride.stakeibc.QueryProto.QueryGetValidatorsRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryGetValidatorsResponse>) responseObserver);
          break;
        case METHODID_HOST_ZONE:
          serviceImpl.hostZone((com.stride.stakeibc.QueryProto.QueryGetHostZoneRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryGetHostZoneResponse>) responseObserver);
          break;
        case METHODID_HOST_ZONE_ALL:
          serviceImpl.hostZoneAll((com.stride.stakeibc.QueryProto.QueryAllHostZoneRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryAllHostZoneResponse>) responseObserver);
          break;
        case METHODID_MODULE_ADDRESS:
          serviceImpl.moduleAddress((com.stride.stakeibc.QueryProto.QueryModuleAddressRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryModuleAddressResponse>) responseObserver);
          break;
        case METHODID_INTERCHAIN_ACCOUNT_FROM_ADDRESS:
          serviceImpl.interchainAccountFromAddress((com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressResponse>) responseObserver);
          break;
        case METHODID_EPOCH_TRACKER:
          serviceImpl.epochTracker((com.stride.stakeibc.QueryProto.QueryGetEpochTrackerRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryGetEpochTrackerResponse>) responseObserver);
          break;
        case METHODID_EPOCH_TRACKER_ALL:
          serviceImpl.epochTrackerAll((com.stride.stakeibc.QueryProto.QueryAllEpochTrackerRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryAllEpochTrackerResponse>) responseObserver);
          break;
        case METHODID_NEXT_PACKET_SEQUENCE:
          serviceImpl.nextPacketSequence((com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceRequest) request,
              (io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceResponse>) responseObserver);
          break;
        case METHODID_ADDRESS_UNBONDINGS:
          serviceImpl.addressUnbondings((com.stride.stakeibc.QueryProto.QueryAddressUnbondings) request,
              (io.grpc.stub.StreamObserver<com.stride.stakeibc.QueryProto.QueryAddressUnbondingsResponse>) responseObserver);
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
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.stakeibc.QueryProto.QueryParamsRequest,
              com.stride.stakeibc.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getValidatorsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.stakeibc.QueryProto.QueryGetValidatorsRequest,
              com.stride.stakeibc.QueryProto.QueryGetValidatorsResponse>(
                service, METHODID_VALIDATORS)))
        .addMethod(
          getHostZoneMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.stakeibc.QueryProto.QueryGetHostZoneRequest,
              com.stride.stakeibc.QueryProto.QueryGetHostZoneResponse>(
                service, METHODID_HOST_ZONE)))
        .addMethod(
          getHostZoneAllMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.stakeibc.QueryProto.QueryAllHostZoneRequest,
              com.stride.stakeibc.QueryProto.QueryAllHostZoneResponse>(
                service, METHODID_HOST_ZONE_ALL)))
        .addMethod(
          getModuleAddressMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.stakeibc.QueryProto.QueryModuleAddressRequest,
              com.stride.stakeibc.QueryProto.QueryModuleAddressResponse>(
                service, METHODID_MODULE_ADDRESS)))
        .addMethod(
          getInterchainAccountFromAddressMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressRequest,
              com.stride.stakeibc.QueryProto.QueryInterchainAccountFromAddressResponse>(
                service, METHODID_INTERCHAIN_ACCOUNT_FROM_ADDRESS)))
        .addMethod(
          getEpochTrackerMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.stakeibc.QueryProto.QueryGetEpochTrackerRequest,
              com.stride.stakeibc.QueryProto.QueryGetEpochTrackerResponse>(
                service, METHODID_EPOCH_TRACKER)))
        .addMethod(
          getEpochTrackerAllMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.stakeibc.QueryProto.QueryAllEpochTrackerRequest,
              com.stride.stakeibc.QueryProto.QueryAllEpochTrackerResponse>(
                service, METHODID_EPOCH_TRACKER_ALL)))
        .addMethod(
          getNextPacketSequenceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceRequest,
              com.stride.stakeibc.QueryProto.QueryGetNextPacketSequenceResponse>(
                service, METHODID_NEXT_PACKET_SEQUENCE)))
        .addMethod(
          getAddressUnbondingsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.stride.stakeibc.QueryProto.QueryAddressUnbondings,
              com.stride.stakeibc.QueryProto.QueryAddressUnbondingsResponse>(
                service, METHODID_ADDRESS_UNBONDINGS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.stride.stakeibc.QueryProto.getDescriptor();
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
              .addMethod(getValidatorsMethod())
              .addMethod(getHostZoneMethod())
              .addMethod(getHostZoneAllMethod())
              .addMethod(getModuleAddressMethod())
              .addMethod(getInterchainAccountFromAddressMethod())
              .addMethod(getEpochTrackerMethod())
              .addMethod(getEpochTrackerAllMethod())
              .addMethod(getNextPacketSequenceMethod())
              .addMethod(getAddressUnbondingsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
