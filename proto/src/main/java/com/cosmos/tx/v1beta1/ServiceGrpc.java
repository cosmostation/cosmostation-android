package com.cosmos.tx.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Service defines a gRPC service for interacting with transactions.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: cosmos/tx/v1beta1/service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ServiceGrpc {

  private ServiceGrpc() {}

  public static final String SERVICE_NAME = "cosmos.tx.v1beta1.Service";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.SimulateRequest,
      com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse> getSimulateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Simulate",
      requestType = com.cosmos.tx.v1beta1.ServiceProto.SimulateRequest.class,
      responseType = com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.SimulateRequest,
      com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse> getSimulateMethod() {
    io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.SimulateRequest, com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse> getSimulateMethod;
    if ((getSimulateMethod = ServiceGrpc.getSimulateMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getSimulateMethod = ServiceGrpc.getSimulateMethod) == null) {
          ServiceGrpc.getSimulateMethod = getSimulateMethod =
              io.grpc.MethodDescriptor.<com.cosmos.tx.v1beta1.ServiceProto.SimulateRequest, com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Simulate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.SimulateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("Simulate"))
              .build();
        }
      }
    }
    return getSimulateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.GetTxRequest,
      com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse> getGetTxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTx",
      requestType = com.cosmos.tx.v1beta1.ServiceProto.GetTxRequest.class,
      responseType = com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.GetTxRequest,
      com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse> getGetTxMethod() {
    io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.GetTxRequest, com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse> getGetTxMethod;
    if ((getGetTxMethod = ServiceGrpc.getGetTxMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getGetTxMethod = ServiceGrpc.getGetTxMethod) == null) {
          ServiceGrpc.getGetTxMethod = getGetTxMethod =
              io.grpc.MethodDescriptor.<com.cosmos.tx.v1beta1.ServiceProto.GetTxRequest, com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.GetTxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("GetTx"))
              .build();
        }
      }
    }
    return getGetTxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxRequest,
      com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxResponse> getBroadcastTxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BroadcastTx",
      requestType = com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxRequest.class,
      responseType = com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxRequest,
      com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxResponse> getBroadcastTxMethod() {
    io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxRequest, com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxResponse> getBroadcastTxMethod;
    if ((getBroadcastTxMethod = ServiceGrpc.getBroadcastTxMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getBroadcastTxMethod = ServiceGrpc.getBroadcastTxMethod) == null) {
          ServiceGrpc.getBroadcastTxMethod = getBroadcastTxMethod =
              io.grpc.MethodDescriptor.<com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxRequest, com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BroadcastTx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("BroadcastTx"))
              .build();
        }
      }
    }
    return getBroadcastTxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventRequest,
      com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventResponse> getGetTxsEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTxsEvent",
      requestType = com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventRequest.class,
      responseType = com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventRequest,
      com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventResponse> getGetTxsEventMethod() {
    io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventRequest, com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventResponse> getGetTxsEventMethod;
    if ((getGetTxsEventMethod = ServiceGrpc.getGetTxsEventMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getGetTxsEventMethod = ServiceGrpc.getGetTxsEventMethod) == null) {
          ServiceGrpc.getGetTxsEventMethod = getGetTxsEventMethod =
              io.grpc.MethodDescriptor.<com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventRequest, com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTxsEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("GetTxsEvent"))
              .build();
        }
      }
    }
    return getGetTxsEventMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsRequest,
      com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsResponse> getGetBlockWithTxsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBlockWithTxs",
      requestType = com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsRequest.class,
      responseType = com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsRequest,
      com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsResponse> getGetBlockWithTxsMethod() {
    io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsRequest, com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsResponse> getGetBlockWithTxsMethod;
    if ((getGetBlockWithTxsMethod = ServiceGrpc.getGetBlockWithTxsMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getGetBlockWithTxsMethod = ServiceGrpc.getGetBlockWithTxsMethod) == null) {
          ServiceGrpc.getGetBlockWithTxsMethod = getGetBlockWithTxsMethod =
              io.grpc.MethodDescriptor.<com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsRequest, com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBlockWithTxs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("GetBlockWithTxs"))
              .build();
        }
      }
    }
    return getGetBlockWithTxsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.TxDecodeRequest,
      com.cosmos.tx.v1beta1.ServiceProto.TxDecodeResponse> getTxDecodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TxDecode",
      requestType = com.cosmos.tx.v1beta1.ServiceProto.TxDecodeRequest.class,
      responseType = com.cosmos.tx.v1beta1.ServiceProto.TxDecodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.TxDecodeRequest,
      com.cosmos.tx.v1beta1.ServiceProto.TxDecodeResponse> getTxDecodeMethod() {
    io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.TxDecodeRequest, com.cosmos.tx.v1beta1.ServiceProto.TxDecodeResponse> getTxDecodeMethod;
    if ((getTxDecodeMethod = ServiceGrpc.getTxDecodeMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getTxDecodeMethod = ServiceGrpc.getTxDecodeMethod) == null) {
          ServiceGrpc.getTxDecodeMethod = getTxDecodeMethod =
              io.grpc.MethodDescriptor.<com.cosmos.tx.v1beta1.ServiceProto.TxDecodeRequest, com.cosmos.tx.v1beta1.ServiceProto.TxDecodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TxDecode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.TxDecodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.TxDecodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("TxDecode"))
              .build();
        }
      }
    }
    return getTxDecodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.TxEncodeRequest,
      com.cosmos.tx.v1beta1.ServiceProto.TxEncodeResponse> getTxEncodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TxEncode",
      requestType = com.cosmos.tx.v1beta1.ServiceProto.TxEncodeRequest.class,
      responseType = com.cosmos.tx.v1beta1.ServiceProto.TxEncodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.TxEncodeRequest,
      com.cosmos.tx.v1beta1.ServiceProto.TxEncodeResponse> getTxEncodeMethod() {
    io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.TxEncodeRequest, com.cosmos.tx.v1beta1.ServiceProto.TxEncodeResponse> getTxEncodeMethod;
    if ((getTxEncodeMethod = ServiceGrpc.getTxEncodeMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getTxEncodeMethod = ServiceGrpc.getTxEncodeMethod) == null) {
          ServiceGrpc.getTxEncodeMethod = getTxEncodeMethod =
              io.grpc.MethodDescriptor.<com.cosmos.tx.v1beta1.ServiceProto.TxEncodeRequest, com.cosmos.tx.v1beta1.ServiceProto.TxEncodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TxEncode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.TxEncodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.TxEncodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("TxEncode"))
              .build();
        }
      }
    }
    return getTxEncodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoRequest,
      com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoResponse> getTxEncodeAminoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TxEncodeAmino",
      requestType = com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoRequest.class,
      responseType = com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoRequest,
      com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoResponse> getTxEncodeAminoMethod() {
    io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoRequest, com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoResponse> getTxEncodeAminoMethod;
    if ((getTxEncodeAminoMethod = ServiceGrpc.getTxEncodeAminoMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getTxEncodeAminoMethod = ServiceGrpc.getTxEncodeAminoMethod) == null) {
          ServiceGrpc.getTxEncodeAminoMethod = getTxEncodeAminoMethod =
              io.grpc.MethodDescriptor.<com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoRequest, com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TxEncodeAmino"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("TxEncodeAmino"))
              .build();
        }
      }
    }
    return getTxEncodeAminoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoRequest,
      com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoResponse> getTxDecodeAminoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TxDecodeAmino",
      requestType = com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoRequest.class,
      responseType = com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoRequest,
      com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoResponse> getTxDecodeAminoMethod() {
    io.grpc.MethodDescriptor<com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoRequest, com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoResponse> getTxDecodeAminoMethod;
    if ((getTxDecodeAminoMethod = ServiceGrpc.getTxDecodeAminoMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getTxDecodeAminoMethod = ServiceGrpc.getTxDecodeAminoMethod) == null) {
          ServiceGrpc.getTxDecodeAminoMethod = getTxDecodeAminoMethod =
              io.grpc.MethodDescriptor.<com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoRequest, com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TxDecodeAmino"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceMethodDescriptorSupplier("TxDecodeAmino"))
              .build();
        }
      }
    }
    return getTxDecodeAminoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServiceStub>() {
        @java.lang.Override
        public ServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServiceStub(channel, callOptions);
        }
      };
    return ServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServiceBlockingStub>() {
        @java.lang.Override
        public ServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServiceBlockingStub(channel, callOptions);
        }
      };
    return ServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServiceFutureStub>() {
        @java.lang.Override
        public ServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServiceFutureStub(channel, callOptions);
        }
      };
    return ServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Service defines a gRPC service for interacting with transactions.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Simulate simulates executing a transaction for estimating gas usage.
     * </pre>
     */
    default void simulate(com.cosmos.tx.v1beta1.ServiceProto.SimulateRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSimulateMethod(), responseObserver);
    }

    /**
     * <pre>
     * GetTx fetches a tx by hash.
     * </pre>
     */
    default void getTx(com.cosmos.tx.v1beta1.ServiceProto.GetTxRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTxMethod(), responseObserver);
    }

    /**
     * <pre>
     * BroadcastTx broadcast transaction.
     * </pre>
     */
    default void broadcastTx(com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBroadcastTxMethod(), responseObserver);
    }

    /**
     * <pre>
     * GetTxsEvent fetches txs by event.
     * </pre>
     */
    default void getTxsEvent(com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTxsEventMethod(), responseObserver);
    }

    /**
     * <pre>
     * GetBlockWithTxs fetches a block with decoded txs.
     * Since: cosmos-sdk 0.45.2
     * </pre>
     */
    default void getBlockWithTxs(com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBlockWithTxsMethod(), responseObserver);
    }

    /**
     * <pre>
     * TxDecode decodes the transaction.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    default void txDecode(com.cosmos.tx.v1beta1.ServiceProto.TxDecodeRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.TxDecodeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTxDecodeMethod(), responseObserver);
    }

    /**
     * <pre>
     * TxEncode encodes the transaction.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    default void txEncode(com.cosmos.tx.v1beta1.ServiceProto.TxEncodeRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.TxEncodeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTxEncodeMethod(), responseObserver);
    }

    /**
     * <pre>
     * TxEncodeAmino encodes an Amino transaction from JSON to encoded bytes.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    default void txEncodeAmino(com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTxEncodeAminoMethod(), responseObserver);
    }

    /**
     * <pre>
     * TxDecodeAmino decodes an Amino transaction from encoded bytes to JSON.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    default void txDecodeAmino(com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTxDecodeAminoMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Service.
   * <pre>
   * Service defines a gRPC service for interacting with transactions.
   * </pre>
   */
  public static abstract class ServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Service.
   * <pre>
   * Service defines a gRPC service for interacting with transactions.
   * </pre>
   */
  public static final class ServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ServiceStub> {
    private ServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Simulate simulates executing a transaction for estimating gas usage.
     * </pre>
     */
    public void simulate(com.cosmos.tx.v1beta1.ServiceProto.SimulateRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSimulateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GetTx fetches a tx by hash.
     * </pre>
     */
    public void getTx(com.cosmos.tx.v1beta1.ServiceProto.GetTxRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTxMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BroadcastTx broadcast transaction.
     * </pre>
     */
    public void broadcastTx(com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBroadcastTxMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GetTxsEvent fetches txs by event.
     * </pre>
     */
    public void getTxsEvent(com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTxsEventMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GetBlockWithTxs fetches a block with decoded txs.
     * Since: cosmos-sdk 0.45.2
     * </pre>
     */
    public void getBlockWithTxs(com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBlockWithTxsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TxDecode decodes the transaction.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public void txDecode(com.cosmos.tx.v1beta1.ServiceProto.TxDecodeRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.TxDecodeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTxDecodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TxEncode encodes the transaction.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public void txEncode(com.cosmos.tx.v1beta1.ServiceProto.TxEncodeRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.TxEncodeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTxEncodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TxEncodeAmino encodes an Amino transaction from JSON to encoded bytes.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public void txEncodeAmino(com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTxEncodeAminoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TxDecodeAmino decodes an Amino transaction from encoded bytes to JSON.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public void txDecodeAmino(com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoRequest request,
        io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTxDecodeAminoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Service.
   * <pre>
   * Service defines a gRPC service for interacting with transactions.
   * </pre>
   */
  public static final class ServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ServiceBlockingStub> {
    private ServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Simulate simulates executing a transaction for estimating gas usage.
     * </pre>
     */
    public com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse simulate(com.cosmos.tx.v1beta1.ServiceProto.SimulateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSimulateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GetTx fetches a tx by hash.
     * </pre>
     */
    public com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse getTx(com.cosmos.tx.v1beta1.ServiceProto.GetTxRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTxMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BroadcastTx broadcast transaction.
     * </pre>
     */
    public com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxResponse broadcastTx(com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBroadcastTxMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GetTxsEvent fetches txs by event.
     * </pre>
     */
    public com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventResponse getTxsEvent(com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTxsEventMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GetBlockWithTxs fetches a block with decoded txs.
     * Since: cosmos-sdk 0.45.2
     * </pre>
     */
    public com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsResponse getBlockWithTxs(com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBlockWithTxsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TxDecode decodes the transaction.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.cosmos.tx.v1beta1.ServiceProto.TxDecodeResponse txDecode(com.cosmos.tx.v1beta1.ServiceProto.TxDecodeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTxDecodeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TxEncode encodes the transaction.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.cosmos.tx.v1beta1.ServiceProto.TxEncodeResponse txEncode(com.cosmos.tx.v1beta1.ServiceProto.TxEncodeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTxEncodeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TxEncodeAmino encodes an Amino transaction from JSON to encoded bytes.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoResponse txEncodeAmino(com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTxEncodeAminoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TxDecodeAmino decodes an Amino transaction from encoded bytes to JSON.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoResponse txDecodeAmino(com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTxDecodeAminoMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Service.
   * <pre>
   * Service defines a gRPC service for interacting with transactions.
   * </pre>
   */
  public static final class ServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ServiceFutureStub> {
    private ServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Simulate simulates executing a transaction for estimating gas usage.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse> simulate(
        com.cosmos.tx.v1beta1.ServiceProto.SimulateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSimulateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GetTx fetches a tx by hash.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse> getTx(
        com.cosmos.tx.v1beta1.ServiceProto.GetTxRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTxMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BroadcastTx broadcast transaction.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxResponse> broadcastTx(
        com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBroadcastTxMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GetTxsEvent fetches txs by event.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventResponse> getTxsEvent(
        com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTxsEventMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GetBlockWithTxs fetches a block with decoded txs.
     * Since: cosmos-sdk 0.45.2
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsResponse> getBlockWithTxs(
        com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBlockWithTxsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TxDecode decodes the transaction.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.tx.v1beta1.ServiceProto.TxDecodeResponse> txDecode(
        com.cosmos.tx.v1beta1.ServiceProto.TxDecodeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTxDecodeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TxEncode encodes the transaction.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.tx.v1beta1.ServiceProto.TxEncodeResponse> txEncode(
        com.cosmos.tx.v1beta1.ServiceProto.TxEncodeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTxEncodeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TxEncodeAmino encodes an Amino transaction from JSON to encoded bytes.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoResponse> txEncodeAmino(
        com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTxEncodeAminoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TxDecodeAmino decodes an Amino transaction from encoded bytes to JSON.
     * Since: cosmos-sdk 0.47
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoResponse> txDecodeAmino(
        com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTxDecodeAminoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SIMULATE = 0;
  private static final int METHODID_GET_TX = 1;
  private static final int METHODID_BROADCAST_TX = 2;
  private static final int METHODID_GET_TXS_EVENT = 3;
  private static final int METHODID_GET_BLOCK_WITH_TXS = 4;
  private static final int METHODID_TX_DECODE = 5;
  private static final int METHODID_TX_ENCODE = 6;
  private static final int METHODID_TX_ENCODE_AMINO = 7;
  private static final int METHODID_TX_DECODE_AMINO = 8;

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
        case METHODID_SIMULATE:
          serviceImpl.simulate((com.cosmos.tx.v1beta1.ServiceProto.SimulateRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse>) responseObserver);
          break;
        case METHODID_GET_TX:
          serviceImpl.getTx((com.cosmos.tx.v1beta1.ServiceProto.GetTxRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse>) responseObserver);
          break;
        case METHODID_BROADCAST_TX:
          serviceImpl.broadcastTx((com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxResponse>) responseObserver);
          break;
        case METHODID_GET_TXS_EVENT:
          serviceImpl.getTxsEvent((com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventResponse>) responseObserver);
          break;
        case METHODID_GET_BLOCK_WITH_TXS:
          serviceImpl.getBlockWithTxs((com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsResponse>) responseObserver);
          break;
        case METHODID_TX_DECODE:
          serviceImpl.txDecode((com.cosmos.tx.v1beta1.ServiceProto.TxDecodeRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.TxDecodeResponse>) responseObserver);
          break;
        case METHODID_TX_ENCODE:
          serviceImpl.txEncode((com.cosmos.tx.v1beta1.ServiceProto.TxEncodeRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.TxEncodeResponse>) responseObserver);
          break;
        case METHODID_TX_ENCODE_AMINO:
          serviceImpl.txEncodeAmino((com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoResponse>) responseObserver);
          break;
        case METHODID_TX_DECODE_AMINO:
          serviceImpl.txDecodeAmino((com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoRequest) request,
              (io.grpc.stub.StreamObserver<com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoResponse>) responseObserver);
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
          getSimulateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.tx.v1beta1.ServiceProto.SimulateRequest,
              com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse>(
                service, METHODID_SIMULATE)))
        .addMethod(
          getGetTxMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.tx.v1beta1.ServiceProto.GetTxRequest,
              com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse>(
                service, METHODID_GET_TX)))
        .addMethod(
          getBroadcastTxMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxRequest,
              com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxResponse>(
                service, METHODID_BROADCAST_TX)))
        .addMethod(
          getGetTxsEventMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventRequest,
              com.cosmos.tx.v1beta1.ServiceProto.GetTxsEventResponse>(
                service, METHODID_GET_TXS_EVENT)))
        .addMethod(
          getGetBlockWithTxsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsRequest,
              com.cosmos.tx.v1beta1.ServiceProto.GetBlockWithTxsResponse>(
                service, METHODID_GET_BLOCK_WITH_TXS)))
        .addMethod(
          getTxDecodeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.tx.v1beta1.ServiceProto.TxDecodeRequest,
              com.cosmos.tx.v1beta1.ServiceProto.TxDecodeResponse>(
                service, METHODID_TX_DECODE)))
        .addMethod(
          getTxEncodeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.tx.v1beta1.ServiceProto.TxEncodeRequest,
              com.cosmos.tx.v1beta1.ServiceProto.TxEncodeResponse>(
                service, METHODID_TX_ENCODE)))
        .addMethod(
          getTxEncodeAminoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoRequest,
              com.cosmos.tx.v1beta1.ServiceProto.TxEncodeAminoResponse>(
                service, METHODID_TX_ENCODE_AMINO)))
        .addMethod(
          getTxDecodeAminoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoRequest,
              com.cosmos.tx.v1beta1.ServiceProto.TxDecodeAminoResponse>(
                service, METHODID_TX_DECODE_AMINO)))
        .build();
  }

  private static abstract class ServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cosmos.tx.v1beta1.ServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Service");
    }
  }

  private static final class ServiceFileDescriptorSupplier
      extends ServiceBaseDescriptorSupplier {
    ServiceFileDescriptorSupplier() {}
  }

  private static final class ServiceMethodDescriptorSupplier
      extends ServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ServiceFileDescriptorSupplier())
              .addMethod(getSimulateMethod())
              .addMethod(getGetTxMethod())
              .addMethod(getBroadcastTxMethod())
              .addMethod(getGetTxsEventMethod())
              .addMethod(getGetBlockWithTxsMethod())
              .addMethod(getTxDecodeMethod())
              .addMethod(getTxEncodeMethod())
              .addMethod(getTxEncodeAminoMethod())
              .addMethod(getTxDecodeAminoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
