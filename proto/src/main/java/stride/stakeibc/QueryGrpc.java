package stride.stakeibc;

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
    comments = "Source: stride/stakeibc/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "stride.stakeibc.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryParamsRequest,
      stride.stakeibc.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = stride.stakeibc.QueryOuterClass.QueryParamsRequest.class,
      responseType = stride.stakeibc.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryParamsRequest,
      stride.stakeibc.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryParamsRequest, stride.stakeibc.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<stride.stakeibc.QueryOuterClass.QueryParamsRequest, stride.stakeibc.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryGetValidatorsRequest,
      stride.stakeibc.QueryOuterClass.QueryGetValidatorsResponse> getValidatorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Validators",
      requestType = stride.stakeibc.QueryOuterClass.QueryGetValidatorsRequest.class,
      responseType = stride.stakeibc.QueryOuterClass.QueryGetValidatorsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryGetValidatorsRequest,
      stride.stakeibc.QueryOuterClass.QueryGetValidatorsResponse> getValidatorsMethod() {
    io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryGetValidatorsRequest, stride.stakeibc.QueryOuterClass.QueryGetValidatorsResponse> getValidatorsMethod;
    if ((getValidatorsMethod = QueryGrpc.getValidatorsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorsMethod = QueryGrpc.getValidatorsMethod) == null) {
          QueryGrpc.getValidatorsMethod = getValidatorsMethod =
              io.grpc.MethodDescriptor.<stride.stakeibc.QueryOuterClass.QueryGetValidatorsRequest, stride.stakeibc.QueryOuterClass.QueryGetValidatorsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Validators"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryGetValidatorsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryGetValidatorsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Validators"))
              .build();
        }
      }
    }
    return getValidatorsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryGetICAAccountRequest,
      stride.stakeibc.QueryOuterClass.QueryGetICAAccountResponse> getICAAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ICAAccount",
      requestType = stride.stakeibc.QueryOuterClass.QueryGetICAAccountRequest.class,
      responseType = stride.stakeibc.QueryOuterClass.QueryGetICAAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryGetICAAccountRequest,
      stride.stakeibc.QueryOuterClass.QueryGetICAAccountResponse> getICAAccountMethod() {
    io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryGetICAAccountRequest, stride.stakeibc.QueryOuterClass.QueryGetICAAccountResponse> getICAAccountMethod;
    if ((getICAAccountMethod = QueryGrpc.getICAAccountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getICAAccountMethod = QueryGrpc.getICAAccountMethod) == null) {
          QueryGrpc.getICAAccountMethod = getICAAccountMethod =
              io.grpc.MethodDescriptor.<stride.stakeibc.QueryOuterClass.QueryGetICAAccountRequest, stride.stakeibc.QueryOuterClass.QueryGetICAAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ICAAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryGetICAAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryGetICAAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ICAAccount"))
              .build();
        }
      }
    }
    return getICAAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryGetHostZoneRequest,
      stride.stakeibc.QueryOuterClass.QueryGetHostZoneResponse> getHostZoneMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HostZone",
      requestType = stride.stakeibc.QueryOuterClass.QueryGetHostZoneRequest.class,
      responseType = stride.stakeibc.QueryOuterClass.QueryGetHostZoneResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryGetHostZoneRequest,
      stride.stakeibc.QueryOuterClass.QueryGetHostZoneResponse> getHostZoneMethod() {
    io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryGetHostZoneRequest, stride.stakeibc.QueryOuterClass.QueryGetHostZoneResponse> getHostZoneMethod;
    if ((getHostZoneMethod = QueryGrpc.getHostZoneMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHostZoneMethod = QueryGrpc.getHostZoneMethod) == null) {
          QueryGrpc.getHostZoneMethod = getHostZoneMethod =
              io.grpc.MethodDescriptor.<stride.stakeibc.QueryOuterClass.QueryGetHostZoneRequest, stride.stakeibc.QueryOuterClass.QueryGetHostZoneResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HostZone"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryGetHostZoneRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryGetHostZoneResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("HostZone"))
              .build();
        }
      }
    }
    return getHostZoneMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryAllHostZoneRequest,
      stride.stakeibc.QueryOuterClass.QueryAllHostZoneResponse> getHostZoneAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HostZoneAll",
      requestType = stride.stakeibc.QueryOuterClass.QueryAllHostZoneRequest.class,
      responseType = stride.stakeibc.QueryOuterClass.QueryAllHostZoneResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryAllHostZoneRequest,
      stride.stakeibc.QueryOuterClass.QueryAllHostZoneResponse> getHostZoneAllMethod() {
    io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryAllHostZoneRequest, stride.stakeibc.QueryOuterClass.QueryAllHostZoneResponse> getHostZoneAllMethod;
    if ((getHostZoneAllMethod = QueryGrpc.getHostZoneAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHostZoneAllMethod = QueryGrpc.getHostZoneAllMethod) == null) {
          QueryGrpc.getHostZoneAllMethod = getHostZoneAllMethod =
              io.grpc.MethodDescriptor.<stride.stakeibc.QueryOuterClass.QueryAllHostZoneRequest, stride.stakeibc.QueryOuterClass.QueryAllHostZoneResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HostZoneAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryAllHostZoneRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryAllHostZoneResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("HostZoneAll"))
              .build();
        }
      }
    }
    return getHostZoneAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryModuleAddressRequest,
      stride.stakeibc.QueryOuterClass.QueryModuleAddressResponse> getModuleAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ModuleAddress",
      requestType = stride.stakeibc.QueryOuterClass.QueryModuleAddressRequest.class,
      responseType = stride.stakeibc.QueryOuterClass.QueryModuleAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryModuleAddressRequest,
      stride.stakeibc.QueryOuterClass.QueryModuleAddressResponse> getModuleAddressMethod() {
    io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryModuleAddressRequest, stride.stakeibc.QueryOuterClass.QueryModuleAddressResponse> getModuleAddressMethod;
    if ((getModuleAddressMethod = QueryGrpc.getModuleAddressMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getModuleAddressMethod = QueryGrpc.getModuleAddressMethod) == null) {
          QueryGrpc.getModuleAddressMethod = getModuleAddressMethod =
              io.grpc.MethodDescriptor.<stride.stakeibc.QueryOuterClass.QueryModuleAddressRequest, stride.stakeibc.QueryOuterClass.QueryModuleAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ModuleAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryModuleAddressRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryModuleAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ModuleAddress"))
              .build();
        }
      }
    }
    return getModuleAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressRequest,
      stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressResponse> getInterchainAccountFromAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InterchainAccountFromAddress",
      requestType = stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressRequest.class,
      responseType = stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressRequest,
      stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressResponse> getInterchainAccountFromAddressMethod() {
    io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressRequest, stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressResponse> getInterchainAccountFromAddressMethod;
    if ((getInterchainAccountFromAddressMethod = QueryGrpc.getInterchainAccountFromAddressMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getInterchainAccountFromAddressMethod = QueryGrpc.getInterchainAccountFromAddressMethod) == null) {
          QueryGrpc.getInterchainAccountFromAddressMethod = getInterchainAccountFromAddressMethod =
              io.grpc.MethodDescriptor.<stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressRequest, stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InterchainAccountFromAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("InterchainAccountFromAddress"))
              .build();
        }
      }
    }
    return getInterchainAccountFromAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerRequest,
      stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerResponse> getEpochTrackerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochTracker",
      requestType = stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerRequest.class,
      responseType = stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerRequest,
      stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerResponse> getEpochTrackerMethod() {
    io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerRequest, stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerResponse> getEpochTrackerMethod;
    if ((getEpochTrackerMethod = QueryGrpc.getEpochTrackerMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochTrackerMethod = QueryGrpc.getEpochTrackerMethod) == null) {
          QueryGrpc.getEpochTrackerMethod = getEpochTrackerMethod =
              io.grpc.MethodDescriptor.<stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerRequest, stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochTracker"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochTracker"))
              .build();
        }
      }
    }
    return getEpochTrackerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerRequest,
      stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerResponse> getEpochTrackerAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EpochTrackerAll",
      requestType = stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerRequest.class,
      responseType = stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerRequest,
      stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerResponse> getEpochTrackerAllMethod() {
    io.grpc.MethodDescriptor<stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerRequest, stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerResponse> getEpochTrackerAllMethod;
    if ((getEpochTrackerAllMethod = QueryGrpc.getEpochTrackerAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEpochTrackerAllMethod = QueryGrpc.getEpochTrackerAllMethod) == null) {
          QueryGrpc.getEpochTrackerAllMethod = getEpochTrackerAllMethod =
              io.grpc.MethodDescriptor.<stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerRequest, stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EpochTrackerAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EpochTrackerAll"))
              .build();
        }
      }
    }
    return getEpochTrackerAllMethod;
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public void params(stride.stakeibc.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a Validator by host zone.
     * </pre>
     */
    public void validators(stride.stakeibc.QueryOuterClass.QueryGetValidatorsRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryGetValidatorsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValidatorsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a ICAAccount by index.
     * </pre>
     */
    public void iCAAccount(stride.stakeibc.QueryOuterClass.QueryGetICAAccountRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryGetICAAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getICAAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a HostZone by id.
     * </pre>
     */
    public void hostZone(stride.stakeibc.QueryOuterClass.QueryGetHostZoneRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryGetHostZoneResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHostZoneMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of HostZone items.
     * </pre>
     */
    public void hostZoneAll(stride.stakeibc.QueryOuterClass.QueryAllHostZoneRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryAllHostZoneResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHostZoneAllMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of ModuleAddress items.
     * </pre>
     */
    public void moduleAddress(stride.stakeibc.QueryOuterClass.QueryModuleAddressRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryModuleAddressResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getModuleAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * QueryInterchainAccountFromAddress returns the interchain account for given
     * owner address on a given connection pair
     * </pre>
     */
    public void interchainAccountFromAddress(stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInterchainAccountFromAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a EpochTracker by index.
     * </pre>
     */
    public void epochTracker(stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEpochTrackerMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of EpochTracker items.
     * </pre>
     */
    public void epochTrackerAll(stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEpochTrackerAllMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.stakeibc.QueryOuterClass.QueryParamsRequest,
                stride.stakeibc.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getValidatorsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.stakeibc.QueryOuterClass.QueryGetValidatorsRequest,
                stride.stakeibc.QueryOuterClass.QueryGetValidatorsResponse>(
                  this, METHODID_VALIDATORS)))
          .addMethod(
            getICAAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.stakeibc.QueryOuterClass.QueryGetICAAccountRequest,
                stride.stakeibc.QueryOuterClass.QueryGetICAAccountResponse>(
                  this, METHODID_ICAACCOUNT)))
          .addMethod(
            getHostZoneMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.stakeibc.QueryOuterClass.QueryGetHostZoneRequest,
                stride.stakeibc.QueryOuterClass.QueryGetHostZoneResponse>(
                  this, METHODID_HOST_ZONE)))
          .addMethod(
            getHostZoneAllMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.stakeibc.QueryOuterClass.QueryAllHostZoneRequest,
                stride.stakeibc.QueryOuterClass.QueryAllHostZoneResponse>(
                  this, METHODID_HOST_ZONE_ALL)))
          .addMethod(
            getModuleAddressMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.stakeibc.QueryOuterClass.QueryModuleAddressRequest,
                stride.stakeibc.QueryOuterClass.QueryModuleAddressResponse>(
                  this, METHODID_MODULE_ADDRESS)))
          .addMethod(
            getInterchainAccountFromAddressMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressRequest,
                stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressResponse>(
                  this, METHODID_INTERCHAIN_ACCOUNT_FROM_ADDRESS)))
          .addMethod(
            getEpochTrackerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerRequest,
                stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerResponse>(
                  this, METHODID_EPOCH_TRACKER)))
          .addMethod(
            getEpochTrackerAllMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerRequest,
                stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerResponse>(
                  this, METHODID_EPOCH_TRACKER_ALL)))
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public void params(stride.stakeibc.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a Validator by host zone.
     * </pre>
     */
    public void validators(stride.stakeibc.QueryOuterClass.QueryGetValidatorsRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryGetValidatorsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValidatorsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a ICAAccount by index.
     * </pre>
     */
    public void iCAAccount(stride.stakeibc.QueryOuterClass.QueryGetICAAccountRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryGetICAAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getICAAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a HostZone by id.
     * </pre>
     */
    public void hostZone(stride.stakeibc.QueryOuterClass.QueryGetHostZoneRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryGetHostZoneResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHostZoneMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of HostZone items.
     * </pre>
     */
    public void hostZoneAll(stride.stakeibc.QueryOuterClass.QueryAllHostZoneRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryAllHostZoneResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHostZoneAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of ModuleAddress items.
     * </pre>
     */
    public void moduleAddress(stride.stakeibc.QueryOuterClass.QueryModuleAddressRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryModuleAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getModuleAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * QueryInterchainAccountFromAddress returns the interchain account for given
     * owner address on a given connection pair
     * </pre>
     */
    public void interchainAccountFromAddress(stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInterchainAccountFromAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a EpochTracker by index.
     * </pre>
     */
    public void epochTracker(stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEpochTrackerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of EpochTracker items.
     * </pre>
     */
    public void epochTrackerAll(stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerRequest request,
        io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEpochTrackerAllMethod(), getCallOptions()), request, responseObserver);
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public stride.stakeibc.QueryOuterClass.QueryParamsResponse params(stride.stakeibc.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a Validator by host zone.
     * </pre>
     */
    public stride.stakeibc.QueryOuterClass.QueryGetValidatorsResponse validators(stride.stakeibc.QueryOuterClass.QueryGetValidatorsRequest request) {
      return blockingUnaryCall(
          getChannel(), getValidatorsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a ICAAccount by index.
     * </pre>
     */
    public stride.stakeibc.QueryOuterClass.QueryGetICAAccountResponse iCAAccount(stride.stakeibc.QueryOuterClass.QueryGetICAAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getICAAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a HostZone by id.
     * </pre>
     */
    public stride.stakeibc.QueryOuterClass.QueryGetHostZoneResponse hostZone(stride.stakeibc.QueryOuterClass.QueryGetHostZoneRequest request) {
      return blockingUnaryCall(
          getChannel(), getHostZoneMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of HostZone items.
     * </pre>
     */
    public stride.stakeibc.QueryOuterClass.QueryAllHostZoneResponse hostZoneAll(stride.stakeibc.QueryOuterClass.QueryAllHostZoneRequest request) {
      return blockingUnaryCall(
          getChannel(), getHostZoneAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of ModuleAddress items.
     * </pre>
     */
    public stride.stakeibc.QueryOuterClass.QueryModuleAddressResponse moduleAddress(stride.stakeibc.QueryOuterClass.QueryModuleAddressRequest request) {
      return blockingUnaryCall(
          getChannel(), getModuleAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * QueryInterchainAccountFromAddress returns the interchain account for given
     * owner address on a given connection pair
     * </pre>
     */
    public stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressResponse interchainAccountFromAddress(stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressRequest request) {
      return blockingUnaryCall(
          getChannel(), getInterchainAccountFromAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a EpochTracker by index.
     * </pre>
     */
    public stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerResponse epochTracker(stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerRequest request) {
      return blockingUnaryCall(
          getChannel(), getEpochTrackerMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of EpochTracker items.
     * </pre>
     */
    public stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerResponse epochTrackerAll(stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerRequest request) {
      return blockingUnaryCall(
          getChannel(), getEpochTrackerAllMethod(), getCallOptions(), request);
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
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.stakeibc.QueryOuterClass.QueryParamsResponse> params(
        stride.stakeibc.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a Validator by host zone.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.stakeibc.QueryOuterClass.QueryGetValidatorsResponse> validators(
        stride.stakeibc.QueryOuterClass.QueryGetValidatorsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValidatorsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a ICAAccount by index.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.stakeibc.QueryOuterClass.QueryGetICAAccountResponse> iCAAccount(
        stride.stakeibc.QueryOuterClass.QueryGetICAAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getICAAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a HostZone by id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.stakeibc.QueryOuterClass.QueryGetHostZoneResponse> hostZone(
        stride.stakeibc.QueryOuterClass.QueryGetHostZoneRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHostZoneMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of HostZone items.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.stakeibc.QueryOuterClass.QueryAllHostZoneResponse> hostZoneAll(
        stride.stakeibc.QueryOuterClass.QueryAllHostZoneRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHostZoneAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of ModuleAddress items.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.stakeibc.QueryOuterClass.QueryModuleAddressResponse> moduleAddress(
        stride.stakeibc.QueryOuterClass.QueryModuleAddressRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getModuleAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * QueryInterchainAccountFromAddress returns the interchain account for given
     * owner address on a given connection pair
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressResponse> interchainAccountFromAddress(
        stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getInterchainAccountFromAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a EpochTracker by index.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerResponse> epochTracker(
        stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEpochTrackerMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of EpochTracker items.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerResponse> epochTrackerAll(
        stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEpochTrackerAllMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_VALIDATORS = 1;
  private static final int METHODID_ICAACCOUNT = 2;
  private static final int METHODID_HOST_ZONE = 3;
  private static final int METHODID_HOST_ZONE_ALL = 4;
  private static final int METHODID_MODULE_ADDRESS = 5;
  private static final int METHODID_INTERCHAIN_ACCOUNT_FROM_ADDRESS = 6;
  private static final int METHODID_EPOCH_TRACKER = 7;
  private static final int METHODID_EPOCH_TRACKER_ALL = 8;

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
          serviceImpl.params((stride.stakeibc.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_VALIDATORS:
          serviceImpl.validators((stride.stakeibc.QueryOuterClass.QueryGetValidatorsRequest) request,
              (io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryGetValidatorsResponse>) responseObserver);
          break;
        case METHODID_ICAACCOUNT:
          serviceImpl.iCAAccount((stride.stakeibc.QueryOuterClass.QueryGetICAAccountRequest) request,
              (io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryGetICAAccountResponse>) responseObserver);
          break;
        case METHODID_HOST_ZONE:
          serviceImpl.hostZone((stride.stakeibc.QueryOuterClass.QueryGetHostZoneRequest) request,
              (io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryGetHostZoneResponse>) responseObserver);
          break;
        case METHODID_HOST_ZONE_ALL:
          serviceImpl.hostZoneAll((stride.stakeibc.QueryOuterClass.QueryAllHostZoneRequest) request,
              (io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryAllHostZoneResponse>) responseObserver);
          break;
        case METHODID_MODULE_ADDRESS:
          serviceImpl.moduleAddress((stride.stakeibc.QueryOuterClass.QueryModuleAddressRequest) request,
              (io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryModuleAddressResponse>) responseObserver);
          break;
        case METHODID_INTERCHAIN_ACCOUNT_FROM_ADDRESS:
          serviceImpl.interchainAccountFromAddress((stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressRequest) request,
              (io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryInterchainAccountFromAddressResponse>) responseObserver);
          break;
        case METHODID_EPOCH_TRACKER:
          serviceImpl.epochTracker((stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerRequest) request,
              (io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryGetEpochTrackerResponse>) responseObserver);
          break;
        case METHODID_EPOCH_TRACKER_ALL:
          serviceImpl.epochTrackerAll((stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerRequest) request,
              (io.grpc.stub.StreamObserver<stride.stakeibc.QueryOuterClass.QueryAllEpochTrackerResponse>) responseObserver);
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
      return stride.stakeibc.QueryOuterClass.getDescriptor();
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
              .addMethod(getICAAccountMethod())
              .addMethod(getHostZoneMethod())
              .addMethod(getHostZoneAllMethod())
              .addMethod(getModuleAddressMethod())
              .addMethod(getInterchainAccountFromAddressMethod())
              .addMethod(getEpochTrackerMethod())
              .addMethod(getEpochTrackerAllMethod())
              .build();
        }
      }
    }
    return result;
  }
}
