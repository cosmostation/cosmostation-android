package shentu.cvm.v1alpha1;

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
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: shentu/cvm/v1alpha1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "shentu.cvm.v1alpha1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeRequest,
      shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeResponse> getCodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Code",
      requestType = shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeRequest.class,
      responseType = shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeRequest,
      shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeResponse> getCodeMethod() {
    io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeRequest, shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeResponse> getCodeMethod;
    if ((getCodeMethod = QueryGrpc.getCodeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCodeMethod = QueryGrpc.getCodeMethod) == null) {
          QueryGrpc.getCodeMethod = getCodeMethod =
              io.grpc.MethodDescriptor.<shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeRequest, shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Code"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Code"))
              .build();
        }
      }
    }
    return getCodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiRequest,
      shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiResponse> getAbiMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Abi",
      requestType = shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiRequest.class,
      responseType = shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiRequest,
      shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiResponse> getAbiMethod() {
    io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiRequest, shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiResponse> getAbiMethod;
    if ((getAbiMethod = QueryGrpc.getAbiMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAbiMethod = QueryGrpc.getAbiMethod) == null) {
          QueryGrpc.getAbiMethod = getAbiMethod =
              io.grpc.MethodDescriptor.<shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiRequest, shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Abi"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Abi"))
              .build();
        }
      }
    }
    return getAbiMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageRequest,
      shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageResponse> getStorageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Storage",
      requestType = shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageRequest.class,
      responseType = shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageRequest,
      shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageResponse> getStorageMethod() {
    io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageRequest, shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageResponse> getStorageMethod;
    if ((getStorageMethod = QueryGrpc.getStorageMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getStorageMethod = QueryGrpc.getStorageMethod) == null) {
          QueryGrpc.getStorageMethod = getStorageMethod =
              io.grpc.MethodDescriptor.<shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageRequest, shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Storage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Storage"))
              .build();
        }
      }
    }
    return getStorageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaRequest,
      shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaResponse> getAddressMetaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddressMeta",
      requestType = shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaRequest.class,
      responseType = shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaRequest,
      shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaResponse> getAddressMetaMethod() {
    io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaRequest, shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaResponse> getAddressMetaMethod;
    if ((getAddressMetaMethod = QueryGrpc.getAddressMetaMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAddressMetaMethod = QueryGrpc.getAddressMetaMethod) == null) {
          QueryGrpc.getAddressMetaMethod = getAddressMetaMethod =
              io.grpc.MethodDescriptor.<shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaRequest, shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddressMeta"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AddressMeta"))
              .build();
        }
      }
    }
    return getAddressMetaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaRequest,
      shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaResponse> getMetaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Meta",
      requestType = shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaRequest.class,
      responseType = shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaRequest,
      shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaResponse> getMetaMethod() {
    io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaRequest, shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaResponse> getMetaMethod;
    if ((getMetaMethod = QueryGrpc.getMetaMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getMetaMethod = QueryGrpc.getMetaMethod) == null) {
          QueryGrpc.getMetaMethod = getMetaMethod =
              io.grpc.MethodDescriptor.<shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaRequest, shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Meta"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Meta"))
              .build();
        }
      }
    }
    return getMetaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryAccountRequest,
      acm.Acm.Account> getAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Account",
      requestType = shentu.cvm.v1alpha1.QueryOuterClass.QueryAccountRequest.class,
      responseType = acm.Acm.Account.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryAccountRequest,
      acm.Acm.Account> getAccountMethod() {
    io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryAccountRequest, acm.Acm.Account> getAccountMethod;
    if ((getAccountMethod = QueryGrpc.getAccountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountMethod = QueryGrpc.getAccountMethod) == null) {
          QueryGrpc.getAccountMethod = getAccountMethod =
              io.grpc.MethodDescriptor.<shentu.cvm.v1alpha1.QueryOuterClass.QueryAccountRequest, acm.Acm.Account>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Account"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cvm.v1alpha1.QueryOuterClass.QueryAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  acm.Acm.Account.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Account"))
              .build();
        }
      }
    }
    return getAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryViewRequest,
      shentu.cvm.v1alpha1.QueryOuterClass.QueryViewResponse> getViewMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "View",
      requestType = shentu.cvm.v1alpha1.QueryOuterClass.QueryViewRequest.class,
      responseType = shentu.cvm.v1alpha1.QueryOuterClass.QueryViewResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryViewRequest,
      shentu.cvm.v1alpha1.QueryOuterClass.QueryViewResponse> getViewMethod() {
    io.grpc.MethodDescriptor<shentu.cvm.v1alpha1.QueryOuterClass.QueryViewRequest, shentu.cvm.v1alpha1.QueryOuterClass.QueryViewResponse> getViewMethod;
    if ((getViewMethod = QueryGrpc.getViewMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getViewMethod = QueryGrpc.getViewMethod) == null) {
          QueryGrpc.getViewMethod = getViewMethod =
              io.grpc.MethodDescriptor.<shentu.cvm.v1alpha1.QueryOuterClass.QueryViewRequest, shentu.cvm.v1alpha1.QueryOuterClass.QueryViewResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "View"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cvm.v1alpha1.QueryOuterClass.QueryViewRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cvm.v1alpha1.QueryOuterClass.QueryViewResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("View"))
              .build();
        }
      }
    }
    return getViewMethod;
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
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     */
    public void code(shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeRequest request,
        io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCodeMethod(), responseObserver);
    }

    /**
     */
    public void abi(shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiRequest request,
        io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAbiMethod(), responseObserver);
    }

    /**
     */
    public void storage(shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageRequest request,
        io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStorageMethod(), responseObserver);
    }

    /**
     */
    public void addressMeta(shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaRequest request,
        io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddressMetaMethod(), responseObserver);
    }

    /**
     */
    public void meta(shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaRequest request,
        io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMetaMethod(), responseObserver);
    }

    /**
     */
    public void account(shentu.cvm.v1alpha1.QueryOuterClass.QueryAccountRequest request,
        io.grpc.stub.StreamObserver<acm.Acm.Account> responseObserver) {
      asyncUnimplementedUnaryCall(getAccountMethod(), responseObserver);
    }

    /**
     */
    public void view(shentu.cvm.v1alpha1.QueryOuterClass.QueryViewRequest request,
        io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryViewResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getViewMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeRequest,
                shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeResponse>(
                  this, METHODID_CODE)))
          .addMethod(
            getAbiMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiRequest,
                shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiResponse>(
                  this, METHODID_ABI)))
          .addMethod(
            getStorageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageRequest,
                shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageResponse>(
                  this, METHODID_STORAGE)))
          .addMethod(
            getAddressMetaMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaRequest,
                shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaResponse>(
                  this, METHODID_ADDRESS_META)))
          .addMethod(
            getMetaMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaRequest,
                shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaResponse>(
                  this, METHODID_META)))
          .addMethod(
            getAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cvm.v1alpha1.QueryOuterClass.QueryAccountRequest,
                acm.Acm.Account>(
                  this, METHODID_ACCOUNT)))
          .addMethod(
            getViewMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cvm.v1alpha1.QueryOuterClass.QueryViewRequest,
                shentu.cvm.v1alpha1.QueryOuterClass.QueryViewResponse>(
                  this, METHODID_VIEW)))
          .build();
    }
  }

  /**
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
    public void code(shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeRequest request,
        io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void abi(shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiRequest request,
        io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAbiMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void storage(shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageRequest request,
        io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStorageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addressMeta(shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaRequest request,
        io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddressMetaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void meta(shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaRequest request,
        io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMetaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void account(shentu.cvm.v1alpha1.QueryOuterClass.QueryAccountRequest request,
        io.grpc.stub.StreamObserver<acm.Acm.Account> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void view(shentu.cvm.v1alpha1.QueryOuterClass.QueryViewRequest request,
        io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryViewResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getViewMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
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
    public shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeResponse code(shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeRequest request) {
      return blockingUnaryCall(
          getChannel(), getCodeMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiResponse abi(shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiRequest request) {
      return blockingUnaryCall(
          getChannel(), getAbiMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageResponse storage(shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageRequest request) {
      return blockingUnaryCall(
          getChannel(), getStorageMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaResponse addressMeta(shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddressMetaMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaResponse meta(shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaRequest request) {
      return blockingUnaryCall(
          getChannel(), getMetaMethod(), getCallOptions(), request);
    }

    /**
     */
    public acm.Acm.Account account(shentu.cvm.v1alpha1.QueryOuterClass.QueryAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccountMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.cvm.v1alpha1.QueryOuterClass.QueryViewResponse view(shentu.cvm.v1alpha1.QueryOuterClass.QueryViewRequest request) {
      return blockingUnaryCall(
          getChannel(), getViewMethod(), getCallOptions(), request);
    }
  }

  /**
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
    public com.google.common.util.concurrent.ListenableFuture<shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeResponse> code(
        shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCodeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiResponse> abi(
        shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAbiMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageResponse> storage(
        shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStorageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaResponse> addressMeta(
        shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddressMetaMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaResponse> meta(
        shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMetaMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<acm.Acm.Account> account(
        shentu.cvm.v1alpha1.QueryOuterClass.QueryAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.cvm.v1alpha1.QueryOuterClass.QueryViewResponse> view(
        shentu.cvm.v1alpha1.QueryOuterClass.QueryViewRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getViewMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CODE = 0;
  private static final int METHODID_ABI = 1;
  private static final int METHODID_STORAGE = 2;
  private static final int METHODID_ADDRESS_META = 3;
  private static final int METHODID_META = 4;
  private static final int METHODID_ACCOUNT = 5;
  private static final int METHODID_VIEW = 6;

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
        case METHODID_CODE:
          serviceImpl.code((shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeRequest) request,
              (io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryCodeResponse>) responseObserver);
          break;
        case METHODID_ABI:
          serviceImpl.abi((shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiRequest) request,
              (io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryAbiResponse>) responseObserver);
          break;
        case METHODID_STORAGE:
          serviceImpl.storage((shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageRequest) request,
              (io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryStorageResponse>) responseObserver);
          break;
        case METHODID_ADDRESS_META:
          serviceImpl.addressMeta((shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaRequest) request,
              (io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryAddressMetaResponse>) responseObserver);
          break;
        case METHODID_META:
          serviceImpl.meta((shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaRequest) request,
              (io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryMetaResponse>) responseObserver);
          break;
        case METHODID_ACCOUNT:
          serviceImpl.account((shentu.cvm.v1alpha1.QueryOuterClass.QueryAccountRequest) request,
              (io.grpc.stub.StreamObserver<acm.Acm.Account>) responseObserver);
          break;
        case METHODID_VIEW:
          serviceImpl.view((shentu.cvm.v1alpha1.QueryOuterClass.QueryViewRequest) request,
              (io.grpc.stub.StreamObserver<shentu.cvm.v1alpha1.QueryOuterClass.QueryViewResponse>) responseObserver);
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
      return shentu.cvm.v1alpha1.QueryOuterClass.getDescriptor();
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
              .addMethod(getCodeMethod())
              .addMethod(getAbiMethod())
              .addMethod(getStorageMethod())
              .addMethod(getAddressMetaMethod())
              .addMethod(getMetaMethod())
              .addMethod(getAccountMethod())
              .addMethod(getViewMethod())
              .build();
        }
      }
    }
    return result;
  }
}
