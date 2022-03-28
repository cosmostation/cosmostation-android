package likechain.iscn;

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
 * Usage:
 * /iscn/api/endpoint?param1=blablabla&amp;param2=blablabla...
 * Example:
 * /iscn/records/id?iscn_id=iscn://likecoin-chain/btC7CJvMm4WLj9Tau9LAPTfGK7sfymTJW7ORcFdruCU&amp;from_version=2
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: iscn/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "likechain.iscn.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryRecordsByIdRequest,
      likechain.iscn.QueryOuterClass.QueryRecordsByIdResponse> getRecordsByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RecordsById",
      requestType = likechain.iscn.QueryOuterClass.QueryRecordsByIdRequest.class,
      responseType = likechain.iscn.QueryOuterClass.QueryRecordsByIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryRecordsByIdRequest,
      likechain.iscn.QueryOuterClass.QueryRecordsByIdResponse> getRecordsByIdMethod() {
    io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryRecordsByIdRequest, likechain.iscn.QueryOuterClass.QueryRecordsByIdResponse> getRecordsByIdMethod;
    if ((getRecordsByIdMethod = QueryGrpc.getRecordsByIdMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRecordsByIdMethod = QueryGrpc.getRecordsByIdMethod) == null) {
          QueryGrpc.getRecordsByIdMethod = getRecordsByIdMethod =
              io.grpc.MethodDescriptor.<likechain.iscn.QueryOuterClass.QueryRecordsByIdRequest, likechain.iscn.QueryOuterClass.QueryRecordsByIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RecordsById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  likechain.iscn.QueryOuterClass.QueryRecordsByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  likechain.iscn.QueryOuterClass.QueryRecordsByIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RecordsById"))
              .build();
        }
      }
    }
    return getRecordsByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintRequest,
      likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintResponse> getRecordsByFingerprintMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RecordsByFingerprint",
      requestType = likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintRequest.class,
      responseType = likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintRequest,
      likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintResponse> getRecordsByFingerprintMethod() {
    io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintRequest, likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintResponse> getRecordsByFingerprintMethod;
    if ((getRecordsByFingerprintMethod = QueryGrpc.getRecordsByFingerprintMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRecordsByFingerprintMethod = QueryGrpc.getRecordsByFingerprintMethod) == null) {
          QueryGrpc.getRecordsByFingerprintMethod = getRecordsByFingerprintMethod =
              io.grpc.MethodDescriptor.<likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintRequest, likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RecordsByFingerprint"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RecordsByFingerprint"))
              .build();
        }
      }
    }
    return getRecordsByFingerprintMethod;
  }

  private static volatile io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryRecordsByOwnerRequest,
      likechain.iscn.QueryOuterClass.QueryRecordsByOwnerResponse> getRecordsByOwnerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RecordsByOwner",
      requestType = likechain.iscn.QueryOuterClass.QueryRecordsByOwnerRequest.class,
      responseType = likechain.iscn.QueryOuterClass.QueryRecordsByOwnerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryRecordsByOwnerRequest,
      likechain.iscn.QueryOuterClass.QueryRecordsByOwnerResponse> getRecordsByOwnerMethod() {
    io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryRecordsByOwnerRequest, likechain.iscn.QueryOuterClass.QueryRecordsByOwnerResponse> getRecordsByOwnerMethod;
    if ((getRecordsByOwnerMethod = QueryGrpc.getRecordsByOwnerMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRecordsByOwnerMethod = QueryGrpc.getRecordsByOwnerMethod) == null) {
          QueryGrpc.getRecordsByOwnerMethod = getRecordsByOwnerMethod =
              io.grpc.MethodDescriptor.<likechain.iscn.QueryOuterClass.QueryRecordsByOwnerRequest, likechain.iscn.QueryOuterClass.QueryRecordsByOwnerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RecordsByOwner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  likechain.iscn.QueryOuterClass.QueryRecordsByOwnerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  likechain.iscn.QueryOuterClass.QueryRecordsByOwnerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RecordsByOwner"))
              .build();
        }
      }
    }
    return getRecordsByOwnerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryParamsRequest,
      likechain.iscn.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = likechain.iscn.QueryOuterClass.QueryParamsRequest.class,
      responseType = likechain.iscn.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryParamsRequest,
      likechain.iscn.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryParamsRequest, likechain.iscn.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<likechain.iscn.QueryOuterClass.QueryParamsRequest, likechain.iscn.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  likechain.iscn.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  likechain.iscn.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryGetCidRequest,
      likechain.iscn.QueryOuterClass.QueryGetCidResponse> getGetCidMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCid",
      requestType = likechain.iscn.QueryOuterClass.QueryGetCidRequest.class,
      responseType = likechain.iscn.QueryOuterClass.QueryGetCidResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryGetCidRequest,
      likechain.iscn.QueryOuterClass.QueryGetCidResponse> getGetCidMethod() {
    io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryGetCidRequest, likechain.iscn.QueryOuterClass.QueryGetCidResponse> getGetCidMethod;
    if ((getGetCidMethod = QueryGrpc.getGetCidMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetCidMethod = QueryGrpc.getGetCidMethod) == null) {
          QueryGrpc.getGetCidMethod = getGetCidMethod =
              io.grpc.MethodDescriptor.<likechain.iscn.QueryOuterClass.QueryGetCidRequest, likechain.iscn.QueryOuterClass.QueryGetCidResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCid"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  likechain.iscn.QueryOuterClass.QueryGetCidRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  likechain.iscn.QueryOuterClass.QueryGetCidResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetCid"))
              .build();
        }
      }
    }
    return getGetCidMethod;
  }

  private static volatile io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryHasCidRequest,
      likechain.iscn.QueryOuterClass.QueryHasCidResponse> getHasCidMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HasCid",
      requestType = likechain.iscn.QueryOuterClass.QueryHasCidRequest.class,
      responseType = likechain.iscn.QueryOuterClass.QueryHasCidResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryHasCidRequest,
      likechain.iscn.QueryOuterClass.QueryHasCidResponse> getHasCidMethod() {
    io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryHasCidRequest, likechain.iscn.QueryOuterClass.QueryHasCidResponse> getHasCidMethod;
    if ((getHasCidMethod = QueryGrpc.getHasCidMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHasCidMethod = QueryGrpc.getHasCidMethod) == null) {
          QueryGrpc.getHasCidMethod = getHasCidMethod =
              io.grpc.MethodDescriptor.<likechain.iscn.QueryOuterClass.QueryHasCidRequest, likechain.iscn.QueryOuterClass.QueryHasCidResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HasCid"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  likechain.iscn.QueryOuterClass.QueryHasCidRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  likechain.iscn.QueryOuterClass.QueryHasCidResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("HasCid"))
              .build();
        }
      }
    }
    return getHasCidMethod;
  }

  private static volatile io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryGetCidSizeRequest,
      likechain.iscn.QueryOuterClass.QueryGetCidSizeResponse> getGetCidSizeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCidSize",
      requestType = likechain.iscn.QueryOuterClass.QueryGetCidSizeRequest.class,
      responseType = likechain.iscn.QueryOuterClass.QueryGetCidSizeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryGetCidSizeRequest,
      likechain.iscn.QueryOuterClass.QueryGetCidSizeResponse> getGetCidSizeMethod() {
    io.grpc.MethodDescriptor<likechain.iscn.QueryOuterClass.QueryGetCidSizeRequest, likechain.iscn.QueryOuterClass.QueryGetCidSizeResponse> getGetCidSizeMethod;
    if ((getGetCidSizeMethod = QueryGrpc.getGetCidSizeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetCidSizeMethod = QueryGrpc.getGetCidSizeMethod) == null) {
          QueryGrpc.getGetCidSizeMethod = getGetCidSizeMethod =
              io.grpc.MethodDescriptor.<likechain.iscn.QueryOuterClass.QueryGetCidSizeRequest, likechain.iscn.QueryOuterClass.QueryGetCidSizeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCidSize"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  likechain.iscn.QueryOuterClass.QueryGetCidSizeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  likechain.iscn.QueryOuterClass.QueryGetCidSizeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetCidSize"))
              .build();
        }
      }
    }
    return getGetCidSizeMethod;
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
   * Usage:
   * /iscn/api/endpoint?param1=blablabla&amp;param2=blablabla...
   * Example:
   * /iscn/records/id?iscn_id=iscn://likecoin-chain/btC7CJvMm4WLj9Tau9LAPTfGK7sfymTJW7ORcFdruCU&amp;from_version=2
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     */
    public void recordsById(likechain.iscn.QueryOuterClass.QueryRecordsByIdRequest request,
        io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryRecordsByIdResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRecordsByIdMethod(), responseObserver);
    }

    /**
     */
    public void recordsByFingerprint(likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintRequest request,
        io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRecordsByFingerprintMethod(), responseObserver);
    }

    /**
     */
    public void recordsByOwner(likechain.iscn.QueryOuterClass.QueryRecordsByOwnerRequest request,
        io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryRecordsByOwnerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRecordsByOwnerMethod(), responseObserver);
    }

    /**
     */
    public void params(likechain.iscn.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     */
    public void getCid(likechain.iscn.QueryOuterClass.QueryGetCidRequest request,
        io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryGetCidResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetCidMethod(), responseObserver);
    }

    /**
     */
    public void hasCid(likechain.iscn.QueryOuterClass.QueryHasCidRequest request,
        io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryHasCidResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHasCidMethod(), responseObserver);
    }

    /**
     */
    public void getCidSize(likechain.iscn.QueryOuterClass.QueryGetCidSizeRequest request,
        io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryGetCidSizeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetCidSizeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRecordsByIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                likechain.iscn.QueryOuterClass.QueryRecordsByIdRequest,
                likechain.iscn.QueryOuterClass.QueryRecordsByIdResponse>(
                  this, METHODID_RECORDS_BY_ID)))
          .addMethod(
            getRecordsByFingerprintMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintRequest,
                likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintResponse>(
                  this, METHODID_RECORDS_BY_FINGERPRINT)))
          .addMethod(
            getRecordsByOwnerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                likechain.iscn.QueryOuterClass.QueryRecordsByOwnerRequest,
                likechain.iscn.QueryOuterClass.QueryRecordsByOwnerResponse>(
                  this, METHODID_RECORDS_BY_OWNER)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                likechain.iscn.QueryOuterClass.QueryParamsRequest,
                likechain.iscn.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getGetCidMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                likechain.iscn.QueryOuterClass.QueryGetCidRequest,
                likechain.iscn.QueryOuterClass.QueryGetCidResponse>(
                  this, METHODID_GET_CID)))
          .addMethod(
            getHasCidMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                likechain.iscn.QueryOuterClass.QueryHasCidRequest,
                likechain.iscn.QueryOuterClass.QueryHasCidResponse>(
                  this, METHODID_HAS_CID)))
          .addMethod(
            getGetCidSizeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                likechain.iscn.QueryOuterClass.QueryGetCidSizeRequest,
                likechain.iscn.QueryOuterClass.QueryGetCidSizeResponse>(
                  this, METHODID_GET_CID_SIZE)))
          .build();
    }
  }

  /**
   * <pre>
   * Usage:
   * /iscn/api/endpoint?param1=blablabla&amp;param2=blablabla...
   * Example:
   * /iscn/records/id?iscn_id=iscn://likecoin-chain/btC7CJvMm4WLj9Tau9LAPTfGK7sfymTJW7ORcFdruCU&amp;from_version=2
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
     */
    public void recordsById(likechain.iscn.QueryOuterClass.QueryRecordsByIdRequest request,
        io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryRecordsByIdResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRecordsByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void recordsByFingerprint(likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintRequest request,
        io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRecordsByFingerprintMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void recordsByOwner(likechain.iscn.QueryOuterClass.QueryRecordsByOwnerRequest request,
        io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryRecordsByOwnerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRecordsByOwnerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void params(likechain.iscn.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getCid(likechain.iscn.QueryOuterClass.QueryGetCidRequest request,
        io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryGetCidResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetCidMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void hasCid(likechain.iscn.QueryOuterClass.QueryHasCidRequest request,
        io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryHasCidResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHasCidMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getCidSize(likechain.iscn.QueryOuterClass.QueryGetCidSizeRequest request,
        io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryGetCidSizeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetCidSizeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Usage:
   * /iscn/api/endpoint?param1=blablabla&amp;param2=blablabla...
   * Example:
   * /iscn/records/id?iscn_id=iscn://likecoin-chain/btC7CJvMm4WLj9Tau9LAPTfGK7sfymTJW7ORcFdruCU&amp;from_version=2
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
     */
    public likechain.iscn.QueryOuterClass.QueryRecordsByIdResponse recordsById(likechain.iscn.QueryOuterClass.QueryRecordsByIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getRecordsByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintResponse recordsByFingerprint(likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintRequest request) {
      return blockingUnaryCall(
          getChannel(), getRecordsByFingerprintMethod(), getCallOptions(), request);
    }

    /**
     */
    public likechain.iscn.QueryOuterClass.QueryRecordsByOwnerResponse recordsByOwner(likechain.iscn.QueryOuterClass.QueryRecordsByOwnerRequest request) {
      return blockingUnaryCall(
          getChannel(), getRecordsByOwnerMethod(), getCallOptions(), request);
    }

    /**
     */
    public likechain.iscn.QueryOuterClass.QueryParamsResponse params(likechain.iscn.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public likechain.iscn.QueryOuterClass.QueryGetCidResponse getCid(likechain.iscn.QueryOuterClass.QueryGetCidRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetCidMethod(), getCallOptions(), request);
    }

    /**
     */
    public likechain.iscn.QueryOuterClass.QueryHasCidResponse hasCid(likechain.iscn.QueryOuterClass.QueryHasCidRequest request) {
      return blockingUnaryCall(
          getChannel(), getHasCidMethod(), getCallOptions(), request);
    }

    /**
     */
    public likechain.iscn.QueryOuterClass.QueryGetCidSizeResponse getCidSize(likechain.iscn.QueryOuterClass.QueryGetCidSizeRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetCidSizeMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Usage:
   * /iscn/api/endpoint?param1=blablabla&amp;param2=blablabla...
   * Example:
   * /iscn/records/id?iscn_id=iscn://likecoin-chain/btC7CJvMm4WLj9Tau9LAPTfGK7sfymTJW7ORcFdruCU&amp;from_version=2
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
     */
    public com.google.common.util.concurrent.ListenableFuture<likechain.iscn.QueryOuterClass.QueryRecordsByIdResponse> recordsById(
        likechain.iscn.QueryOuterClass.QueryRecordsByIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRecordsByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintResponse> recordsByFingerprint(
        likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRecordsByFingerprintMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<likechain.iscn.QueryOuterClass.QueryRecordsByOwnerResponse> recordsByOwner(
        likechain.iscn.QueryOuterClass.QueryRecordsByOwnerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRecordsByOwnerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<likechain.iscn.QueryOuterClass.QueryParamsResponse> params(
        likechain.iscn.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<likechain.iscn.QueryOuterClass.QueryGetCidResponse> getCid(
        likechain.iscn.QueryOuterClass.QueryGetCidRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetCidMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<likechain.iscn.QueryOuterClass.QueryHasCidResponse> hasCid(
        likechain.iscn.QueryOuterClass.QueryHasCidRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHasCidMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<likechain.iscn.QueryOuterClass.QueryGetCidSizeResponse> getCidSize(
        likechain.iscn.QueryOuterClass.QueryGetCidSizeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetCidSizeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RECORDS_BY_ID = 0;
  private static final int METHODID_RECORDS_BY_FINGERPRINT = 1;
  private static final int METHODID_RECORDS_BY_OWNER = 2;
  private static final int METHODID_PARAMS = 3;
  private static final int METHODID_GET_CID = 4;
  private static final int METHODID_HAS_CID = 5;
  private static final int METHODID_GET_CID_SIZE = 6;

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
        case METHODID_RECORDS_BY_ID:
          serviceImpl.recordsById((likechain.iscn.QueryOuterClass.QueryRecordsByIdRequest) request,
              (io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryRecordsByIdResponse>) responseObserver);
          break;
        case METHODID_RECORDS_BY_FINGERPRINT:
          serviceImpl.recordsByFingerprint((likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintRequest) request,
              (io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryRecordsByFingerprintResponse>) responseObserver);
          break;
        case METHODID_RECORDS_BY_OWNER:
          serviceImpl.recordsByOwner((likechain.iscn.QueryOuterClass.QueryRecordsByOwnerRequest) request,
              (io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryRecordsByOwnerResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((likechain.iscn.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_GET_CID:
          serviceImpl.getCid((likechain.iscn.QueryOuterClass.QueryGetCidRequest) request,
              (io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryGetCidResponse>) responseObserver);
          break;
        case METHODID_HAS_CID:
          serviceImpl.hasCid((likechain.iscn.QueryOuterClass.QueryHasCidRequest) request,
              (io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryHasCidResponse>) responseObserver);
          break;
        case METHODID_GET_CID_SIZE:
          serviceImpl.getCidSize((likechain.iscn.QueryOuterClass.QueryGetCidSizeRequest) request,
              (io.grpc.stub.StreamObserver<likechain.iscn.QueryOuterClass.QueryGetCidSizeResponse>) responseObserver);
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
      return likechain.iscn.QueryOuterClass.getDescriptor();
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
              .addMethod(getRecordsByIdMethod())
              .addMethod(getRecordsByFingerprintMethod())
              .addMethod(getRecordsByOwnerMethod())
              .addMethod(getParamsMethod())
              .addMethod(getGetCidMethod())
              .addMethod(getHasCidMethod())
              .addMethod(getGetCidSizeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
