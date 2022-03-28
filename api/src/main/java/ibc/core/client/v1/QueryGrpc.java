package ibc.core.client.v1;

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
 * Query provides defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: ibc/core/client/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "ibc.core.client.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryClientStateRequest,
      ibc.core.client.v1.QueryOuterClass.QueryClientStateResponse> getClientStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClientState",
      requestType = ibc.core.client.v1.QueryOuterClass.QueryClientStateRequest.class,
      responseType = ibc.core.client.v1.QueryOuterClass.QueryClientStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryClientStateRequest,
      ibc.core.client.v1.QueryOuterClass.QueryClientStateResponse> getClientStateMethod() {
    io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryClientStateRequest, ibc.core.client.v1.QueryOuterClass.QueryClientStateResponse> getClientStateMethod;
    if ((getClientStateMethod = QueryGrpc.getClientStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClientStateMethod = QueryGrpc.getClientStateMethod) == null) {
          QueryGrpc.getClientStateMethod = getClientStateMethod =
              io.grpc.MethodDescriptor.<ibc.core.client.v1.QueryOuterClass.QueryClientStateRequest, ibc.core.client.v1.QueryOuterClass.QueryClientStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClientState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.QueryOuterClass.QueryClientStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.QueryOuterClass.QueryClientStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClientState"))
              .build();
        }
      }
    }
    return getClientStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryClientStatesRequest,
      ibc.core.client.v1.QueryOuterClass.QueryClientStatesResponse> getClientStatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClientStates",
      requestType = ibc.core.client.v1.QueryOuterClass.QueryClientStatesRequest.class,
      responseType = ibc.core.client.v1.QueryOuterClass.QueryClientStatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryClientStatesRequest,
      ibc.core.client.v1.QueryOuterClass.QueryClientStatesResponse> getClientStatesMethod() {
    io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryClientStatesRequest, ibc.core.client.v1.QueryOuterClass.QueryClientStatesResponse> getClientStatesMethod;
    if ((getClientStatesMethod = QueryGrpc.getClientStatesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClientStatesMethod = QueryGrpc.getClientStatesMethod) == null) {
          QueryGrpc.getClientStatesMethod = getClientStatesMethod =
              io.grpc.MethodDescriptor.<ibc.core.client.v1.QueryOuterClass.QueryClientStatesRequest, ibc.core.client.v1.QueryOuterClass.QueryClientStatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClientStates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.QueryOuterClass.QueryClientStatesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.QueryOuterClass.QueryClientStatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClientStates"))
              .build();
        }
      }
    }
    return getClientStatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryConsensusStateRequest,
      ibc.core.client.v1.QueryOuterClass.QueryConsensusStateResponse> getConsensusStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConsensusState",
      requestType = ibc.core.client.v1.QueryOuterClass.QueryConsensusStateRequest.class,
      responseType = ibc.core.client.v1.QueryOuterClass.QueryConsensusStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryConsensusStateRequest,
      ibc.core.client.v1.QueryOuterClass.QueryConsensusStateResponse> getConsensusStateMethod() {
    io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryConsensusStateRequest, ibc.core.client.v1.QueryOuterClass.QueryConsensusStateResponse> getConsensusStateMethod;
    if ((getConsensusStateMethod = QueryGrpc.getConsensusStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getConsensusStateMethod = QueryGrpc.getConsensusStateMethod) == null) {
          QueryGrpc.getConsensusStateMethod = getConsensusStateMethod =
              io.grpc.MethodDescriptor.<ibc.core.client.v1.QueryOuterClass.QueryConsensusStateRequest, ibc.core.client.v1.QueryOuterClass.QueryConsensusStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConsensusState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.QueryOuterClass.QueryConsensusStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.QueryOuterClass.QueryConsensusStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ConsensusState"))
              .build();
        }
      }
    }
    return getConsensusStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesRequest,
      ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesResponse> getConsensusStatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConsensusStates",
      requestType = ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesRequest.class,
      responseType = ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesRequest,
      ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesResponse> getConsensusStatesMethod() {
    io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesRequest, ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesResponse> getConsensusStatesMethod;
    if ((getConsensusStatesMethod = QueryGrpc.getConsensusStatesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getConsensusStatesMethod = QueryGrpc.getConsensusStatesMethod) == null) {
          QueryGrpc.getConsensusStatesMethod = getConsensusStatesMethod =
              io.grpc.MethodDescriptor.<ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesRequest, ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConsensusStates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ConsensusStates"))
              .build();
        }
      }
    }
    return getConsensusStatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryClientStatusRequest,
      ibc.core.client.v1.QueryOuterClass.QueryClientStatusResponse> getClientStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClientStatus",
      requestType = ibc.core.client.v1.QueryOuterClass.QueryClientStatusRequest.class,
      responseType = ibc.core.client.v1.QueryOuterClass.QueryClientStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryClientStatusRequest,
      ibc.core.client.v1.QueryOuterClass.QueryClientStatusResponse> getClientStatusMethod() {
    io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryClientStatusRequest, ibc.core.client.v1.QueryOuterClass.QueryClientStatusResponse> getClientStatusMethod;
    if ((getClientStatusMethod = QueryGrpc.getClientStatusMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClientStatusMethod = QueryGrpc.getClientStatusMethod) == null) {
          QueryGrpc.getClientStatusMethod = getClientStatusMethod =
              io.grpc.MethodDescriptor.<ibc.core.client.v1.QueryOuterClass.QueryClientStatusRequest, ibc.core.client.v1.QueryOuterClass.QueryClientStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClientStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.QueryOuterClass.QueryClientStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.QueryOuterClass.QueryClientStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClientStatus"))
              .build();
        }
      }
    }
    return getClientStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryClientParamsRequest,
      ibc.core.client.v1.QueryOuterClass.QueryClientParamsResponse> getClientParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClientParams",
      requestType = ibc.core.client.v1.QueryOuterClass.QueryClientParamsRequest.class,
      responseType = ibc.core.client.v1.QueryOuterClass.QueryClientParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryClientParamsRequest,
      ibc.core.client.v1.QueryOuterClass.QueryClientParamsResponse> getClientParamsMethod() {
    io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryClientParamsRequest, ibc.core.client.v1.QueryOuterClass.QueryClientParamsResponse> getClientParamsMethod;
    if ((getClientParamsMethod = QueryGrpc.getClientParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClientParamsMethod = QueryGrpc.getClientParamsMethod) == null) {
          QueryGrpc.getClientParamsMethod = getClientParamsMethod =
              io.grpc.MethodDescriptor.<ibc.core.client.v1.QueryOuterClass.QueryClientParamsRequest, ibc.core.client.v1.QueryOuterClass.QueryClientParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClientParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.QueryOuterClass.QueryClientParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.QueryOuterClass.QueryClientParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClientParams"))
              .build();
        }
      }
    }
    return getClientParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateRequest,
      ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateResponse> getUpgradedClientStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpgradedClientState",
      requestType = ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateRequest.class,
      responseType = ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateRequest,
      ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateResponse> getUpgradedClientStateMethod() {
    io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateRequest, ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateResponse> getUpgradedClientStateMethod;
    if ((getUpgradedClientStateMethod = QueryGrpc.getUpgradedClientStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUpgradedClientStateMethod = QueryGrpc.getUpgradedClientStateMethod) == null) {
          QueryGrpc.getUpgradedClientStateMethod = getUpgradedClientStateMethod =
              io.grpc.MethodDescriptor.<ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateRequest, ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpgradedClientState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UpgradedClientState"))
              .build();
        }
      }
    }
    return getUpgradedClientStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateRequest,
      ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateResponse> getUpgradedConsensusStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpgradedConsensusState",
      requestType = ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateRequest.class,
      responseType = ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateRequest,
      ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateResponse> getUpgradedConsensusStateMethod() {
    io.grpc.MethodDescriptor<ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateRequest, ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateResponse> getUpgradedConsensusStateMethod;
    if ((getUpgradedConsensusStateMethod = QueryGrpc.getUpgradedConsensusStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUpgradedConsensusStateMethod = QueryGrpc.getUpgradedConsensusStateMethod) == null) {
          QueryGrpc.getUpgradedConsensusStateMethod = getUpgradedConsensusStateMethod =
              io.grpc.MethodDescriptor.<ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateRequest, ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpgradedConsensusState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UpgradedConsensusState"))
              .build();
        }
      }
    }
    return getUpgradedConsensusStateMethod;
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
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * ClientState queries an IBC light client.
     * </pre>
     */
    public void clientState(ibc.core.client.v1.QueryOuterClass.QueryClientStateRequest request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryClientStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClientStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClientStates queries all the IBC light clients of a chain.
     * </pre>
     */
    public void clientStates(ibc.core.client.v1.QueryOuterClass.QueryClientStatesRequest request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryClientStatesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClientStatesMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConsensusState queries a consensus state associated with a client state at
     * a given height.
     * </pre>
     */
    public void consensusState(ibc.core.client.v1.QueryOuterClass.QueryConsensusStateRequest request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryConsensusStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConsensusStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * ConsensusStates queries all the consensus state associated with a given
     * client.
     * </pre>
     */
    public void consensusStates(ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesRequest request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConsensusStatesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Status queries the status of an IBC client.
     * </pre>
     */
    public void clientStatus(ibc.core.client.v1.QueryOuterClass.QueryClientStatusRequest request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryClientStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClientStatusMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClientParams queries all parameters of the ibc client.
     * </pre>
     */
    public void clientParams(ibc.core.client.v1.QueryOuterClass.QueryClientParamsRequest request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryClientParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClientParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpgradedClientState queries an Upgraded IBC light client.
     * </pre>
     */
    public void upgradedClientState(ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateRequest request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpgradedClientStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpgradedConsensusState queries an Upgraded IBC consensus state.
     * </pre>
     */
    public void upgradedConsensusState(ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateRequest request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpgradedConsensusStateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getClientStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.client.v1.QueryOuterClass.QueryClientStateRequest,
                ibc.core.client.v1.QueryOuterClass.QueryClientStateResponse>(
                  this, METHODID_CLIENT_STATE)))
          .addMethod(
            getClientStatesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.client.v1.QueryOuterClass.QueryClientStatesRequest,
                ibc.core.client.v1.QueryOuterClass.QueryClientStatesResponse>(
                  this, METHODID_CLIENT_STATES)))
          .addMethod(
            getConsensusStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.client.v1.QueryOuterClass.QueryConsensusStateRequest,
                ibc.core.client.v1.QueryOuterClass.QueryConsensusStateResponse>(
                  this, METHODID_CONSENSUS_STATE)))
          .addMethod(
            getConsensusStatesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesRequest,
                ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesResponse>(
                  this, METHODID_CONSENSUS_STATES)))
          .addMethod(
            getClientStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.client.v1.QueryOuterClass.QueryClientStatusRequest,
                ibc.core.client.v1.QueryOuterClass.QueryClientStatusResponse>(
                  this, METHODID_CLIENT_STATUS)))
          .addMethod(
            getClientParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.client.v1.QueryOuterClass.QueryClientParamsRequest,
                ibc.core.client.v1.QueryOuterClass.QueryClientParamsResponse>(
                  this, METHODID_CLIENT_PARAMS)))
          .addMethod(
            getUpgradedClientStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateRequest,
                ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateResponse>(
                  this, METHODID_UPGRADED_CLIENT_STATE)))
          .addMethod(
            getUpgradedConsensusStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateRequest,
                ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateResponse>(
                  this, METHODID_UPGRADED_CONSENSUS_STATE)))
          .build();
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service
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
     * ClientState queries an IBC light client.
     * </pre>
     */
    public void clientState(ibc.core.client.v1.QueryOuterClass.QueryClientStateRequest request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryClientStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClientStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClientStates queries all the IBC light clients of a chain.
     * </pre>
     */
    public void clientStates(ibc.core.client.v1.QueryOuterClass.QueryClientStatesRequest request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryClientStatesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClientStatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConsensusState queries a consensus state associated with a client state at
     * a given height.
     * </pre>
     */
    public void consensusState(ibc.core.client.v1.QueryOuterClass.QueryConsensusStateRequest request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryConsensusStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConsensusStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ConsensusStates queries all the consensus state associated with a given
     * client.
     * </pre>
     */
    public void consensusStates(ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesRequest request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConsensusStatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Status queries the status of an IBC client.
     * </pre>
     */
    public void clientStatus(ibc.core.client.v1.QueryOuterClass.QueryClientStatusRequest request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryClientStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClientStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClientParams queries all parameters of the ibc client.
     * </pre>
     */
    public void clientParams(ibc.core.client.v1.QueryOuterClass.QueryClientParamsRequest request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryClientParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClientParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpgradedClientState queries an Upgraded IBC light client.
     * </pre>
     */
    public void upgradedClientState(ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateRequest request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpgradedClientStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpgradedConsensusState queries an Upgraded IBC consensus state.
     * </pre>
     */
    public void upgradedConsensusState(ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateRequest request,
        io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpgradedConsensusStateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service
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
     * ClientState queries an IBC light client.
     * </pre>
     */
    public ibc.core.client.v1.QueryOuterClass.QueryClientStateResponse clientState(ibc.core.client.v1.QueryOuterClass.QueryClientStateRequest request) {
      return blockingUnaryCall(
          getChannel(), getClientStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClientStates queries all the IBC light clients of a chain.
     * </pre>
     */
    public ibc.core.client.v1.QueryOuterClass.QueryClientStatesResponse clientStates(ibc.core.client.v1.QueryOuterClass.QueryClientStatesRequest request) {
      return blockingUnaryCall(
          getChannel(), getClientStatesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConsensusState queries a consensus state associated with a client state at
     * a given height.
     * </pre>
     */
    public ibc.core.client.v1.QueryOuterClass.QueryConsensusStateResponse consensusState(ibc.core.client.v1.QueryOuterClass.QueryConsensusStateRequest request) {
      return blockingUnaryCall(
          getChannel(), getConsensusStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ConsensusStates queries all the consensus state associated with a given
     * client.
     * </pre>
     */
    public ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesResponse consensusStates(ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesRequest request) {
      return blockingUnaryCall(
          getChannel(), getConsensusStatesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Status queries the status of an IBC client.
     * </pre>
     */
    public ibc.core.client.v1.QueryOuterClass.QueryClientStatusResponse clientStatus(ibc.core.client.v1.QueryOuterClass.QueryClientStatusRequest request) {
      return blockingUnaryCall(
          getChannel(), getClientStatusMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClientParams queries all parameters of the ibc client.
     * </pre>
     */
    public ibc.core.client.v1.QueryOuterClass.QueryClientParamsResponse clientParams(ibc.core.client.v1.QueryOuterClass.QueryClientParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getClientParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpgradedClientState queries an Upgraded IBC light client.
     * </pre>
     */
    public ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateResponse upgradedClientState(ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpgradedClientStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpgradedConsensusState queries an Upgraded IBC consensus state.
     * </pre>
     */
    public ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateResponse upgradedConsensusState(ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpgradedConsensusStateMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query provides defines the gRPC querier service
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
     * ClientState queries an IBC light client.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.client.v1.QueryOuterClass.QueryClientStateResponse> clientState(
        ibc.core.client.v1.QueryOuterClass.QueryClientStateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getClientStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClientStates queries all the IBC light clients of a chain.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.client.v1.QueryOuterClass.QueryClientStatesResponse> clientStates(
        ibc.core.client.v1.QueryOuterClass.QueryClientStatesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getClientStatesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConsensusState queries a consensus state associated with a client state at
     * a given height.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.client.v1.QueryOuterClass.QueryConsensusStateResponse> consensusState(
        ibc.core.client.v1.QueryOuterClass.QueryConsensusStateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getConsensusStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ConsensusStates queries all the consensus state associated with a given
     * client.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesResponse> consensusStates(
        ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getConsensusStatesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Status queries the status of an IBC client.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.client.v1.QueryOuterClass.QueryClientStatusResponse> clientStatus(
        ibc.core.client.v1.QueryOuterClass.QueryClientStatusRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getClientStatusMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClientParams queries all parameters of the ibc client.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.client.v1.QueryOuterClass.QueryClientParamsResponse> clientParams(
        ibc.core.client.v1.QueryOuterClass.QueryClientParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getClientParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpgradedClientState queries an Upgraded IBC light client.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateResponse> upgradedClientState(
        ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpgradedClientStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpgradedConsensusState queries an Upgraded IBC consensus state.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateResponse> upgradedConsensusState(
        ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpgradedConsensusStateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CLIENT_STATE = 0;
  private static final int METHODID_CLIENT_STATES = 1;
  private static final int METHODID_CONSENSUS_STATE = 2;
  private static final int METHODID_CONSENSUS_STATES = 3;
  private static final int METHODID_CLIENT_STATUS = 4;
  private static final int METHODID_CLIENT_PARAMS = 5;
  private static final int METHODID_UPGRADED_CLIENT_STATE = 6;
  private static final int METHODID_UPGRADED_CONSENSUS_STATE = 7;

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
        case METHODID_CLIENT_STATE:
          serviceImpl.clientState((ibc.core.client.v1.QueryOuterClass.QueryClientStateRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryClientStateResponse>) responseObserver);
          break;
        case METHODID_CLIENT_STATES:
          serviceImpl.clientStates((ibc.core.client.v1.QueryOuterClass.QueryClientStatesRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryClientStatesResponse>) responseObserver);
          break;
        case METHODID_CONSENSUS_STATE:
          serviceImpl.consensusState((ibc.core.client.v1.QueryOuterClass.QueryConsensusStateRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryConsensusStateResponse>) responseObserver);
          break;
        case METHODID_CONSENSUS_STATES:
          serviceImpl.consensusStates((ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesResponse>) responseObserver);
          break;
        case METHODID_CLIENT_STATUS:
          serviceImpl.clientStatus((ibc.core.client.v1.QueryOuterClass.QueryClientStatusRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryClientStatusResponse>) responseObserver);
          break;
        case METHODID_CLIENT_PARAMS:
          serviceImpl.clientParams((ibc.core.client.v1.QueryOuterClass.QueryClientParamsRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryClientParamsResponse>) responseObserver);
          break;
        case METHODID_UPGRADED_CLIENT_STATE:
          serviceImpl.upgradedClientState((ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateResponse>) responseObserver);
          break;
        case METHODID_UPGRADED_CONSENSUS_STATE:
          serviceImpl.upgradedConsensusState((ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateRequest) request,
              (io.grpc.stub.StreamObserver<ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateResponse>) responseObserver);
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
      return ibc.core.client.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getClientStateMethod())
              .addMethod(getClientStatesMethod())
              .addMethod(getConsensusStateMethod())
              .addMethod(getConsensusStatesMethod())
              .addMethod(getClientStatusMethod())
              .addMethod(getClientParamsMethod())
              .addMethod(getUpgradedClientStateMethod())
              .addMethod(getUpgradedConsensusStateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
