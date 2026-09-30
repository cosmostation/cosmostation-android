package com.sui.rpc.v2;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: sui/rpc/v2/ledger_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class LedgerServiceGrpc {

  private LedgerServiceGrpc() {}

  public static final String SERVICE_NAME = "sui.rpc.v2.LedgerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoRequest,
      com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoResponse> getGetServiceInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetServiceInfo",
      requestType = com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoRequest.class,
      responseType = com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoRequest,
      com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoResponse> getGetServiceInfoMethod() {
    io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoRequest, com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoResponse> getGetServiceInfoMethod;
    if ((getGetServiceInfoMethod = LedgerServiceGrpc.getGetServiceInfoMethod) == null) {
      synchronized (LedgerServiceGrpc.class) {
        if ((getGetServiceInfoMethod = LedgerServiceGrpc.getGetServiceInfoMethod) == null) {
          LedgerServiceGrpc.getGetServiceInfoMethod = getGetServiceInfoMethod =
              io.grpc.MethodDescriptor.<com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoRequest, com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetServiceInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerServiceMethodDescriptorSupplier("GetServiceInfo"))
              .build();
        }
      }
    }
    return getGetServiceInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.GetObjectRequest,
      com.sui.rpc.v2.LedgerServiceProto.GetObjectResponse> getGetObjectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetObject",
      requestType = com.sui.rpc.v2.LedgerServiceProto.GetObjectRequest.class,
      responseType = com.sui.rpc.v2.LedgerServiceProto.GetObjectResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.GetObjectRequest,
      com.sui.rpc.v2.LedgerServiceProto.GetObjectResponse> getGetObjectMethod() {
    io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.GetObjectRequest, com.sui.rpc.v2.LedgerServiceProto.GetObjectResponse> getGetObjectMethod;
    if ((getGetObjectMethod = LedgerServiceGrpc.getGetObjectMethod) == null) {
      synchronized (LedgerServiceGrpc.class) {
        if ((getGetObjectMethod = LedgerServiceGrpc.getGetObjectMethod) == null) {
          LedgerServiceGrpc.getGetObjectMethod = getGetObjectMethod =
              io.grpc.MethodDescriptor.<com.sui.rpc.v2.LedgerServiceProto.GetObjectRequest, com.sui.rpc.v2.LedgerServiceProto.GetObjectResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetObject"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.GetObjectRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.GetObjectResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerServiceMethodDescriptorSupplier("GetObject"))
              .build();
        }
      }
    }
    return getGetObjectMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsRequest,
      com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsResponse> getBatchGetObjectsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchGetObjects",
      requestType = com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsRequest.class,
      responseType = com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsRequest,
      com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsResponse> getBatchGetObjectsMethod() {
    io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsRequest, com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsResponse> getBatchGetObjectsMethod;
    if ((getBatchGetObjectsMethod = LedgerServiceGrpc.getBatchGetObjectsMethod) == null) {
      synchronized (LedgerServiceGrpc.class) {
        if ((getBatchGetObjectsMethod = LedgerServiceGrpc.getBatchGetObjectsMethod) == null) {
          LedgerServiceGrpc.getBatchGetObjectsMethod = getBatchGetObjectsMethod =
              io.grpc.MethodDescriptor.<com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsRequest, com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchGetObjects"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerServiceMethodDescriptorSupplier("BatchGetObjects"))
              .build();
        }
      }
    }
    return getBatchGetObjectsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.GetTransactionRequest,
      com.sui.rpc.v2.LedgerServiceProto.GetTransactionResponse> getGetTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTransaction",
      requestType = com.sui.rpc.v2.LedgerServiceProto.GetTransactionRequest.class,
      responseType = com.sui.rpc.v2.LedgerServiceProto.GetTransactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.GetTransactionRequest,
      com.sui.rpc.v2.LedgerServiceProto.GetTransactionResponse> getGetTransactionMethod() {
    io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.GetTransactionRequest, com.sui.rpc.v2.LedgerServiceProto.GetTransactionResponse> getGetTransactionMethod;
    if ((getGetTransactionMethod = LedgerServiceGrpc.getGetTransactionMethod) == null) {
      synchronized (LedgerServiceGrpc.class) {
        if ((getGetTransactionMethod = LedgerServiceGrpc.getGetTransactionMethod) == null) {
          LedgerServiceGrpc.getGetTransactionMethod = getGetTransactionMethod =
              io.grpc.MethodDescriptor.<com.sui.rpc.v2.LedgerServiceProto.GetTransactionRequest, com.sui.rpc.v2.LedgerServiceProto.GetTransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.GetTransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.GetTransactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerServiceMethodDescriptorSupplier("GetTransaction"))
              .build();
        }
      }
    }
    return getGetTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsRequest,
      com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsResponse> getBatchGetTransactionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchGetTransactions",
      requestType = com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsRequest.class,
      responseType = com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsRequest,
      com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsResponse> getBatchGetTransactionsMethod() {
    io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsRequest, com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsResponse> getBatchGetTransactionsMethod;
    if ((getBatchGetTransactionsMethod = LedgerServiceGrpc.getBatchGetTransactionsMethod) == null) {
      synchronized (LedgerServiceGrpc.class) {
        if ((getBatchGetTransactionsMethod = LedgerServiceGrpc.getBatchGetTransactionsMethod) == null) {
          LedgerServiceGrpc.getBatchGetTransactionsMethod = getBatchGetTransactionsMethod =
              io.grpc.MethodDescriptor.<com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsRequest, com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchGetTransactions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerServiceMethodDescriptorSupplier("BatchGetTransactions"))
              .build();
        }
      }
    }
    return getBatchGetTransactionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.GetCheckpointRequest,
      com.sui.rpc.v2.LedgerServiceProto.GetCheckpointResponse> getGetCheckpointMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCheckpoint",
      requestType = com.sui.rpc.v2.LedgerServiceProto.GetCheckpointRequest.class,
      responseType = com.sui.rpc.v2.LedgerServiceProto.GetCheckpointResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.GetCheckpointRequest,
      com.sui.rpc.v2.LedgerServiceProto.GetCheckpointResponse> getGetCheckpointMethod() {
    io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.GetCheckpointRequest, com.sui.rpc.v2.LedgerServiceProto.GetCheckpointResponse> getGetCheckpointMethod;
    if ((getGetCheckpointMethod = LedgerServiceGrpc.getGetCheckpointMethod) == null) {
      synchronized (LedgerServiceGrpc.class) {
        if ((getGetCheckpointMethod = LedgerServiceGrpc.getGetCheckpointMethod) == null) {
          LedgerServiceGrpc.getGetCheckpointMethod = getGetCheckpointMethod =
              io.grpc.MethodDescriptor.<com.sui.rpc.v2.LedgerServiceProto.GetCheckpointRequest, com.sui.rpc.v2.LedgerServiceProto.GetCheckpointResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCheckpoint"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.GetCheckpointRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.GetCheckpointResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerServiceMethodDescriptorSupplier("GetCheckpoint"))
              .build();
        }
      }
    }
    return getGetCheckpointMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.GetEpochRequest,
      com.sui.rpc.v2.LedgerServiceProto.GetEpochResponse> getGetEpochMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEpoch",
      requestType = com.sui.rpc.v2.LedgerServiceProto.GetEpochRequest.class,
      responseType = com.sui.rpc.v2.LedgerServiceProto.GetEpochResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.GetEpochRequest,
      com.sui.rpc.v2.LedgerServiceProto.GetEpochResponse> getGetEpochMethod() {
    io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.GetEpochRequest, com.sui.rpc.v2.LedgerServiceProto.GetEpochResponse> getGetEpochMethod;
    if ((getGetEpochMethod = LedgerServiceGrpc.getGetEpochMethod) == null) {
      synchronized (LedgerServiceGrpc.class) {
        if ((getGetEpochMethod = LedgerServiceGrpc.getGetEpochMethod) == null) {
          LedgerServiceGrpc.getGetEpochMethod = getGetEpochMethod =
              io.grpc.MethodDescriptor.<com.sui.rpc.v2.LedgerServiceProto.GetEpochRequest, com.sui.rpc.v2.LedgerServiceProto.GetEpochResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEpoch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.GetEpochRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.GetEpochResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerServiceMethodDescriptorSupplier("GetEpoch"))
              .build();
        }
      }
    }
    return getGetEpochMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsRequest,
      com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsResponse> getListCheckpointsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListCheckpoints",
      requestType = com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsRequest.class,
      responseType = com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsRequest,
      com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsResponse> getListCheckpointsMethod() {
    io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsRequest, com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsResponse> getListCheckpointsMethod;
    if ((getListCheckpointsMethod = LedgerServiceGrpc.getListCheckpointsMethod) == null) {
      synchronized (LedgerServiceGrpc.class) {
        if ((getListCheckpointsMethod = LedgerServiceGrpc.getListCheckpointsMethod) == null) {
          LedgerServiceGrpc.getListCheckpointsMethod = getListCheckpointsMethod =
              io.grpc.MethodDescriptor.<com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsRequest, com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListCheckpoints"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerServiceMethodDescriptorSupplier("ListCheckpoints"))
              .build();
        }
      }
    }
    return getListCheckpointsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.ListTransactionsRequest,
      com.sui.rpc.v2.LedgerServiceProto.ListTransactionsResponse> getListTransactionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListTransactions",
      requestType = com.sui.rpc.v2.LedgerServiceProto.ListTransactionsRequest.class,
      responseType = com.sui.rpc.v2.LedgerServiceProto.ListTransactionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.ListTransactionsRequest,
      com.sui.rpc.v2.LedgerServiceProto.ListTransactionsResponse> getListTransactionsMethod() {
    io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.ListTransactionsRequest, com.sui.rpc.v2.LedgerServiceProto.ListTransactionsResponse> getListTransactionsMethod;
    if ((getListTransactionsMethod = LedgerServiceGrpc.getListTransactionsMethod) == null) {
      synchronized (LedgerServiceGrpc.class) {
        if ((getListTransactionsMethod = LedgerServiceGrpc.getListTransactionsMethod) == null) {
          LedgerServiceGrpc.getListTransactionsMethod = getListTransactionsMethod =
              io.grpc.MethodDescriptor.<com.sui.rpc.v2.LedgerServiceProto.ListTransactionsRequest, com.sui.rpc.v2.LedgerServiceProto.ListTransactionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListTransactions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.ListTransactionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.ListTransactionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerServiceMethodDescriptorSupplier("ListTransactions"))
              .build();
        }
      }
    }
    return getListTransactionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.ListEventsRequest,
      com.sui.rpc.v2.LedgerServiceProto.ListEventsResponse> getListEventsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListEvents",
      requestType = com.sui.rpc.v2.LedgerServiceProto.ListEventsRequest.class,
      responseType = com.sui.rpc.v2.LedgerServiceProto.ListEventsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.ListEventsRequest,
      com.sui.rpc.v2.LedgerServiceProto.ListEventsResponse> getListEventsMethod() {
    io.grpc.MethodDescriptor<com.sui.rpc.v2.LedgerServiceProto.ListEventsRequest, com.sui.rpc.v2.LedgerServiceProto.ListEventsResponse> getListEventsMethod;
    if ((getListEventsMethod = LedgerServiceGrpc.getListEventsMethod) == null) {
      synchronized (LedgerServiceGrpc.class) {
        if ((getListEventsMethod = LedgerServiceGrpc.getListEventsMethod) == null) {
          LedgerServiceGrpc.getListEventsMethod = getListEventsMethod =
              io.grpc.MethodDescriptor.<com.sui.rpc.v2.LedgerServiceProto.ListEventsRequest, com.sui.rpc.v2.LedgerServiceProto.ListEventsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListEvents"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.ListEventsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sui.rpc.v2.LedgerServiceProto.ListEventsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerServiceMethodDescriptorSupplier("ListEvents"))
              .build();
        }
      }
    }
    return getListEventsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LedgerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LedgerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LedgerServiceStub>() {
        @java.lang.Override
        public LedgerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LedgerServiceStub(channel, callOptions);
        }
      };
    return LedgerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LedgerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LedgerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LedgerServiceBlockingStub>() {
        @java.lang.Override
        public LedgerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LedgerServiceBlockingStub(channel, callOptions);
        }
      };
    return LedgerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LedgerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LedgerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LedgerServiceFutureStub>() {
        @java.lang.Override
        public LedgerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LedgerServiceFutureStub(channel, callOptions);
        }
      };
    return LedgerServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Query the service for general information about its current state.
     * </pre>
     */
    default void getServiceInfo(com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetServiceInfoMethod(), responseObserver);
    }

    /**
     */
    default void getObject(com.sui.rpc.v2.LedgerServiceProto.GetObjectRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.GetObjectResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetObjectMethod(), responseObserver);
    }

    /**
     */
    default void batchGetObjects(com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchGetObjectsMethod(), responseObserver);
    }

    /**
     */
    default void getTransaction(com.sui.rpc.v2.LedgerServiceProto.GetTransactionRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.GetTransactionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTransactionMethod(), responseObserver);
    }

    /**
     */
    default void batchGetTransactions(com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBatchGetTransactionsMethod(), responseObserver);
    }

    /**
     */
    default void getCheckpoint(com.sui.rpc.v2.LedgerServiceProto.GetCheckpointRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.GetCheckpointResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCheckpointMethod(), responseObserver);
    }

    /**
     */
    default void getEpoch(com.sui.rpc.v2.LedgerServiceProto.GetEpochRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.GetEpochResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEpochMethod(), responseObserver);
    }

    /**
     * <pre>
     * List checkpoints matching the provided filters.
     * Checkpoints are returned in ascending or descending checkpoint sequence
     * number order according to the query options ordering.
     * A checkpoint matches if any transaction it contains satisfies the filter.
     * </pre>
     */
    default void listCheckpoints(com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListCheckpointsMethod(), responseObserver);
    }

    /**
     * <pre>
     * List transactions matching the provided filters.
     * Transactions are returned in ascending or descending transaction sequence
     * order according to the query options ordering.
     * </pre>
     */
    default void listTransactions(com.sui.rpc.v2.LedgerServiceProto.ListTransactionsRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.ListTransactionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListTransactionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * List events matching the provided filters.
     * Events are returned in ascending or descending packed event sequence order
     * according to the query options ordering.
     * </pre>
     */
    default void listEvents(com.sui.rpc.v2.LedgerServiceProto.ListEventsRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.ListEventsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListEventsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service LedgerService.
   */
  public static abstract class LedgerServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return LedgerServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service LedgerService.
   */
  public static final class LedgerServiceStub
      extends io.grpc.stub.AbstractAsyncStub<LedgerServiceStub> {
    private LedgerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LedgerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LedgerServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Query the service for general information about its current state.
     * </pre>
     */
    public void getServiceInfo(com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetServiceInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getObject(com.sui.rpc.v2.LedgerServiceProto.GetObjectRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.GetObjectResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetObjectMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchGetObjects(com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchGetObjectsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTransaction(com.sui.rpc.v2.LedgerServiceProto.GetTransactionRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.GetTransactionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchGetTransactions(com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBatchGetTransactionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getCheckpoint(com.sui.rpc.v2.LedgerServiceProto.GetCheckpointRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.GetCheckpointResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetCheckpointMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEpoch(com.sui.rpc.v2.LedgerServiceProto.GetEpochRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.GetEpochResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEpochMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * List checkpoints matching the provided filters.
     * Checkpoints are returned in ascending or descending checkpoint sequence
     * number order according to the query options ordering.
     * A checkpoint matches if any transaction it contains satisfies the filter.
     * </pre>
     */
    public void listCheckpoints(com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getListCheckpointsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * List transactions matching the provided filters.
     * Transactions are returned in ascending or descending transaction sequence
     * order according to the query options ordering.
     * </pre>
     */
    public void listTransactions(com.sui.rpc.v2.LedgerServiceProto.ListTransactionsRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.ListTransactionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getListTransactionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * List events matching the provided filters.
     * Events are returned in ascending or descending packed event sequence order
     * according to the query options ordering.
     * </pre>
     */
    public void listEvents(com.sui.rpc.v2.LedgerServiceProto.ListEventsRequest request,
        io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.ListEventsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getListEventsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service LedgerService.
   */
  public static final class LedgerServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<LedgerServiceBlockingStub> {
    private LedgerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LedgerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LedgerServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Query the service for general information about its current state.
     * </pre>
     */
    public com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoResponse getServiceInfo(com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetServiceInfoMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sui.rpc.v2.LedgerServiceProto.GetObjectResponse getObject(com.sui.rpc.v2.LedgerServiceProto.GetObjectRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetObjectMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsResponse batchGetObjects(com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchGetObjectsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sui.rpc.v2.LedgerServiceProto.GetTransactionResponse getTransaction(com.sui.rpc.v2.LedgerServiceProto.GetTransactionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTransactionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsResponse batchGetTransactions(com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBatchGetTransactionsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sui.rpc.v2.LedgerServiceProto.GetCheckpointResponse getCheckpoint(com.sui.rpc.v2.LedgerServiceProto.GetCheckpointRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetCheckpointMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sui.rpc.v2.LedgerServiceProto.GetEpochResponse getEpoch(com.sui.rpc.v2.LedgerServiceProto.GetEpochRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEpochMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * List checkpoints matching the provided filters.
     * Checkpoints are returned in ascending or descending checkpoint sequence
     * number order according to the query options ordering.
     * A checkpoint matches if any transaction it contains satisfies the filter.
     * </pre>
     */
    public java.util.Iterator<com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsResponse> listCheckpoints(
        com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getListCheckpointsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * List transactions matching the provided filters.
     * Transactions are returned in ascending or descending transaction sequence
     * order according to the query options ordering.
     * </pre>
     */
    public java.util.Iterator<com.sui.rpc.v2.LedgerServiceProto.ListTransactionsResponse> listTransactions(
        com.sui.rpc.v2.LedgerServiceProto.ListTransactionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getListTransactionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * List events matching the provided filters.
     * Events are returned in ascending or descending packed event sequence order
     * according to the query options ordering.
     * </pre>
     */
    public java.util.Iterator<com.sui.rpc.v2.LedgerServiceProto.ListEventsResponse> listEvents(
        com.sui.rpc.v2.LedgerServiceProto.ListEventsRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getListEventsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service LedgerService.
   */
  public static final class LedgerServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<LedgerServiceFutureStub> {
    private LedgerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LedgerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LedgerServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Query the service for general information about its current state.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoResponse> getServiceInfo(
        com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetServiceInfoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sui.rpc.v2.LedgerServiceProto.GetObjectResponse> getObject(
        com.sui.rpc.v2.LedgerServiceProto.GetObjectRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetObjectMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsResponse> batchGetObjects(
        com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchGetObjectsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sui.rpc.v2.LedgerServiceProto.GetTransactionResponse> getTransaction(
        com.sui.rpc.v2.LedgerServiceProto.GetTransactionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTransactionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsResponse> batchGetTransactions(
        com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBatchGetTransactionsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sui.rpc.v2.LedgerServiceProto.GetCheckpointResponse> getCheckpoint(
        com.sui.rpc.v2.LedgerServiceProto.GetCheckpointRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetCheckpointMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sui.rpc.v2.LedgerServiceProto.GetEpochResponse> getEpoch(
        com.sui.rpc.v2.LedgerServiceProto.GetEpochRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEpochMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SERVICE_INFO = 0;
  private static final int METHODID_GET_OBJECT = 1;
  private static final int METHODID_BATCH_GET_OBJECTS = 2;
  private static final int METHODID_GET_TRANSACTION = 3;
  private static final int METHODID_BATCH_GET_TRANSACTIONS = 4;
  private static final int METHODID_GET_CHECKPOINT = 5;
  private static final int METHODID_GET_EPOCH = 6;
  private static final int METHODID_LIST_CHECKPOINTS = 7;
  private static final int METHODID_LIST_TRANSACTIONS = 8;
  private static final int METHODID_LIST_EVENTS = 9;

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
        case METHODID_GET_SERVICE_INFO:
          serviceImpl.getServiceInfo((com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoResponse>) responseObserver);
          break;
        case METHODID_GET_OBJECT:
          serviceImpl.getObject((com.sui.rpc.v2.LedgerServiceProto.GetObjectRequest) request,
              (io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.GetObjectResponse>) responseObserver);
          break;
        case METHODID_BATCH_GET_OBJECTS:
          serviceImpl.batchGetObjects((com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsRequest) request,
              (io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsResponse>) responseObserver);
          break;
        case METHODID_GET_TRANSACTION:
          serviceImpl.getTransaction((com.sui.rpc.v2.LedgerServiceProto.GetTransactionRequest) request,
              (io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.GetTransactionResponse>) responseObserver);
          break;
        case METHODID_BATCH_GET_TRANSACTIONS:
          serviceImpl.batchGetTransactions((com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsRequest) request,
              (io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsResponse>) responseObserver);
          break;
        case METHODID_GET_CHECKPOINT:
          serviceImpl.getCheckpoint((com.sui.rpc.v2.LedgerServiceProto.GetCheckpointRequest) request,
              (io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.GetCheckpointResponse>) responseObserver);
          break;
        case METHODID_GET_EPOCH:
          serviceImpl.getEpoch((com.sui.rpc.v2.LedgerServiceProto.GetEpochRequest) request,
              (io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.GetEpochResponse>) responseObserver);
          break;
        case METHODID_LIST_CHECKPOINTS:
          serviceImpl.listCheckpoints((com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsRequest) request,
              (io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsResponse>) responseObserver);
          break;
        case METHODID_LIST_TRANSACTIONS:
          serviceImpl.listTransactions((com.sui.rpc.v2.LedgerServiceProto.ListTransactionsRequest) request,
              (io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.ListTransactionsResponse>) responseObserver);
          break;
        case METHODID_LIST_EVENTS:
          serviceImpl.listEvents((com.sui.rpc.v2.LedgerServiceProto.ListEventsRequest) request,
              (io.grpc.stub.StreamObserver<com.sui.rpc.v2.LedgerServiceProto.ListEventsResponse>) responseObserver);
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
          getGetServiceInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoRequest,
              com.sui.rpc.v2.LedgerServiceProto.GetServiceInfoResponse>(
                service, METHODID_GET_SERVICE_INFO)))
        .addMethod(
          getGetObjectMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sui.rpc.v2.LedgerServiceProto.GetObjectRequest,
              com.sui.rpc.v2.LedgerServiceProto.GetObjectResponse>(
                service, METHODID_GET_OBJECT)))
        .addMethod(
          getBatchGetObjectsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsRequest,
              com.sui.rpc.v2.LedgerServiceProto.BatchGetObjectsResponse>(
                service, METHODID_BATCH_GET_OBJECTS)))
        .addMethod(
          getGetTransactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sui.rpc.v2.LedgerServiceProto.GetTransactionRequest,
              com.sui.rpc.v2.LedgerServiceProto.GetTransactionResponse>(
                service, METHODID_GET_TRANSACTION)))
        .addMethod(
          getBatchGetTransactionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsRequest,
              com.sui.rpc.v2.LedgerServiceProto.BatchGetTransactionsResponse>(
                service, METHODID_BATCH_GET_TRANSACTIONS)))
        .addMethod(
          getGetCheckpointMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sui.rpc.v2.LedgerServiceProto.GetCheckpointRequest,
              com.sui.rpc.v2.LedgerServiceProto.GetCheckpointResponse>(
                service, METHODID_GET_CHECKPOINT)))
        .addMethod(
          getGetEpochMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sui.rpc.v2.LedgerServiceProto.GetEpochRequest,
              com.sui.rpc.v2.LedgerServiceProto.GetEpochResponse>(
                service, METHODID_GET_EPOCH)))
        .addMethod(
          getListCheckpointsMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsRequest,
              com.sui.rpc.v2.LedgerServiceProto.ListCheckpointsResponse>(
                service, METHODID_LIST_CHECKPOINTS)))
        .addMethod(
          getListTransactionsMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.sui.rpc.v2.LedgerServiceProto.ListTransactionsRequest,
              com.sui.rpc.v2.LedgerServiceProto.ListTransactionsResponse>(
                service, METHODID_LIST_TRANSACTIONS)))
        .addMethod(
          getListEventsMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.sui.rpc.v2.LedgerServiceProto.ListEventsRequest,
              com.sui.rpc.v2.LedgerServiceProto.ListEventsResponse>(
                service, METHODID_LIST_EVENTS)))
        .build();
  }

  private static abstract class LedgerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LedgerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.sui.rpc.v2.LedgerServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LedgerService");
    }
  }

  private static final class LedgerServiceFileDescriptorSupplier
      extends LedgerServiceBaseDescriptorSupplier {
    LedgerServiceFileDescriptorSupplier() {}
  }

  private static final class LedgerServiceMethodDescriptorSupplier
      extends LedgerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LedgerServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (LedgerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LedgerServiceFileDescriptorSupplier())
              .addMethod(getGetServiceInfoMethod())
              .addMethod(getGetObjectMethod())
              .addMethod(getBatchGetObjectsMethod())
              .addMethod(getGetTransactionMethod())
              .addMethod(getBatchGetTransactionsMethod())
              .addMethod(getGetCheckpointMethod())
              .addMethod(getGetEpochMethod())
              .addMethod(getListCheckpointsMethod())
              .addMethod(getListTransactionsMethod())
              .addMethod(getListEventsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
