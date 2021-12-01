package ixo.did;

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
    comments = "Source: ixo/did/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "did.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ixo.did.QueryOuterClass.QueryDidDocRequest,
      ixo.did.QueryOuterClass.QueryDidDocResponse> getDidDocMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DidDoc",
      requestType = ixo.did.QueryOuterClass.QueryDidDocRequest.class,
      responseType = ixo.did.QueryOuterClass.QueryDidDocResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ixo.did.QueryOuterClass.QueryDidDocRequest,
      ixo.did.QueryOuterClass.QueryDidDocResponse> getDidDocMethod() {
    io.grpc.MethodDescriptor<ixo.did.QueryOuterClass.QueryDidDocRequest, ixo.did.QueryOuterClass.QueryDidDocResponse> getDidDocMethod;
    if ((getDidDocMethod = QueryGrpc.getDidDocMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDidDocMethod = QueryGrpc.getDidDocMethod) == null) {
          QueryGrpc.getDidDocMethod = getDidDocMethod =
              io.grpc.MethodDescriptor.<ixo.did.QueryOuterClass.QueryDidDocRequest, ixo.did.QueryOuterClass.QueryDidDocResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DidDoc"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.did.QueryOuterClass.QueryDidDocRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.did.QueryOuterClass.QueryDidDocResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DidDoc"))
              .build();
        }
      }
    }
    return getDidDocMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ixo.did.QueryOuterClass.QueryAllDidsRequest,
      ixo.did.QueryOuterClass.QueryAllDidsResponse> getAllDidsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllDids",
      requestType = ixo.did.QueryOuterClass.QueryAllDidsRequest.class,
      responseType = ixo.did.QueryOuterClass.QueryAllDidsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ixo.did.QueryOuterClass.QueryAllDidsRequest,
      ixo.did.QueryOuterClass.QueryAllDidsResponse> getAllDidsMethod() {
    io.grpc.MethodDescriptor<ixo.did.QueryOuterClass.QueryAllDidsRequest, ixo.did.QueryOuterClass.QueryAllDidsResponse> getAllDidsMethod;
    if ((getAllDidsMethod = QueryGrpc.getAllDidsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllDidsMethod = QueryGrpc.getAllDidsMethod) == null) {
          QueryGrpc.getAllDidsMethod = getAllDidsMethod =
              io.grpc.MethodDescriptor.<ixo.did.QueryOuterClass.QueryAllDidsRequest, ixo.did.QueryOuterClass.QueryAllDidsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllDids"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.did.QueryOuterClass.QueryAllDidsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.did.QueryOuterClass.QueryAllDidsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllDids"))
              .build();
        }
      }
    }
    return getAllDidsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ixo.did.QueryOuterClass.QueryAllDidDocsRequest,
      ixo.did.QueryOuterClass.QueryAllDidDocsResponse> getAllDidDocsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllDidDocs",
      requestType = ixo.did.QueryOuterClass.QueryAllDidDocsRequest.class,
      responseType = ixo.did.QueryOuterClass.QueryAllDidDocsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ixo.did.QueryOuterClass.QueryAllDidDocsRequest,
      ixo.did.QueryOuterClass.QueryAllDidDocsResponse> getAllDidDocsMethod() {
    io.grpc.MethodDescriptor<ixo.did.QueryOuterClass.QueryAllDidDocsRequest, ixo.did.QueryOuterClass.QueryAllDidDocsResponse> getAllDidDocsMethod;
    if ((getAllDidDocsMethod = QueryGrpc.getAllDidDocsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllDidDocsMethod = QueryGrpc.getAllDidDocsMethod) == null) {
          QueryGrpc.getAllDidDocsMethod = getAllDidDocsMethod =
              io.grpc.MethodDescriptor.<ixo.did.QueryOuterClass.QueryAllDidDocsRequest, ixo.did.QueryOuterClass.QueryAllDidDocsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllDidDocs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.did.QueryOuterClass.QueryAllDidDocsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.did.QueryOuterClass.QueryAllDidDocsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllDidDocs"))
              .build();
        }
      }
    }
    return getAllDidDocsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ixo.did.QueryOuterClass.QueryAddressFromDidRequest,
      ixo.did.QueryOuterClass.QueryAddressFromDidResponse> getAddressFromDidMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddressFromDid",
      requestType = ixo.did.QueryOuterClass.QueryAddressFromDidRequest.class,
      responseType = ixo.did.QueryOuterClass.QueryAddressFromDidResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ixo.did.QueryOuterClass.QueryAddressFromDidRequest,
      ixo.did.QueryOuterClass.QueryAddressFromDidResponse> getAddressFromDidMethod() {
    io.grpc.MethodDescriptor<ixo.did.QueryOuterClass.QueryAddressFromDidRequest, ixo.did.QueryOuterClass.QueryAddressFromDidResponse> getAddressFromDidMethod;
    if ((getAddressFromDidMethod = QueryGrpc.getAddressFromDidMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAddressFromDidMethod = QueryGrpc.getAddressFromDidMethod) == null) {
          QueryGrpc.getAddressFromDidMethod = getAddressFromDidMethod =
              io.grpc.MethodDescriptor.<ixo.did.QueryOuterClass.QueryAddressFromDidRequest, ixo.did.QueryOuterClass.QueryAddressFromDidResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddressFromDid"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.did.QueryOuterClass.QueryAddressFromDidRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.did.QueryOuterClass.QueryAddressFromDidResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AddressFromDid"))
              .build();
        }
      }
    }
    return getAddressFromDidMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyRequest,
      ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyResponse> getAddressFromBase58EncodedPubkeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddressFromBase58EncodedPubkey",
      requestType = ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyRequest.class,
      responseType = ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyRequest,
      ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyResponse> getAddressFromBase58EncodedPubkeyMethod() {
    io.grpc.MethodDescriptor<ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyRequest, ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyResponse> getAddressFromBase58EncodedPubkeyMethod;
    if ((getAddressFromBase58EncodedPubkeyMethod = QueryGrpc.getAddressFromBase58EncodedPubkeyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAddressFromBase58EncodedPubkeyMethod = QueryGrpc.getAddressFromBase58EncodedPubkeyMethod) == null) {
          QueryGrpc.getAddressFromBase58EncodedPubkeyMethod = getAddressFromBase58EncodedPubkeyMethod =
              io.grpc.MethodDescriptor.<ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyRequest, ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddressFromBase58EncodedPubkey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AddressFromBase58EncodedPubkey"))
              .build();
        }
      }
    }
    return getAddressFromBase58EncodedPubkeyMethod;
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
     * DidDoc queries info of a specific DID's DidDoc.
     * </pre>
     */
    public void didDoc(ixo.did.QueryOuterClass.QueryDidDocRequest request,
        io.grpc.stub.StreamObserver<ixo.did.QueryOuterClass.QueryDidDocResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDidDocMethod(), responseObserver);
    }

    /**
     * <pre>
     * AllDids returns a list of all existing DIDs.
     * </pre>
     */
    public void allDids(ixo.did.QueryOuterClass.QueryAllDidsRequest request,
        io.grpc.stub.StreamObserver<ixo.did.QueryOuterClass.QueryAllDidsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAllDidsMethod(), responseObserver);
    }

    /**
     * <pre>
     * AllDidDocs returns a list of all existing DidDocs (i.e. all DIDs along with their DidDoc info).
     * </pre>
     */
    public void allDidDocs(ixo.did.QueryOuterClass.QueryAllDidDocsRequest request,
        io.grpc.stub.StreamObserver<ixo.did.QueryOuterClass.QueryAllDidDocsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAllDidDocsMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddressFromDid retrieves the cosmos address associated to an ixo DID.
     * </pre>
     */
    public void addressFromDid(ixo.did.QueryOuterClass.QueryAddressFromDidRequest request,
        io.grpc.stub.StreamObserver<ixo.did.QueryOuterClass.QueryAddressFromDidResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddressFromDidMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddressFromBase58EncodedPubkey retrieves the cosmos address associated to an ixo DID's pubkey.
     * </pre>
     */
    public void addressFromBase58EncodedPubkey(ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyRequest request,
        io.grpc.stub.StreamObserver<ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddressFromBase58EncodedPubkeyMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDidDocMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ixo.did.QueryOuterClass.QueryDidDocRequest,
                ixo.did.QueryOuterClass.QueryDidDocResponse>(
                  this, METHODID_DID_DOC)))
          .addMethod(
            getAllDidsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ixo.did.QueryOuterClass.QueryAllDidsRequest,
                ixo.did.QueryOuterClass.QueryAllDidsResponse>(
                  this, METHODID_ALL_DIDS)))
          .addMethod(
            getAllDidDocsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ixo.did.QueryOuterClass.QueryAllDidDocsRequest,
                ixo.did.QueryOuterClass.QueryAllDidDocsResponse>(
                  this, METHODID_ALL_DID_DOCS)))
          .addMethod(
            getAddressFromDidMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ixo.did.QueryOuterClass.QueryAddressFromDidRequest,
                ixo.did.QueryOuterClass.QueryAddressFromDidResponse>(
                  this, METHODID_ADDRESS_FROM_DID)))
          .addMethod(
            getAddressFromBase58EncodedPubkeyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyRequest,
                ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyResponse>(
                  this, METHODID_ADDRESS_FROM_BASE58ENCODED_PUBKEY)))
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
     * DidDoc queries info of a specific DID's DidDoc.
     * </pre>
     */
    public void didDoc(ixo.did.QueryOuterClass.QueryDidDocRequest request,
        io.grpc.stub.StreamObserver<ixo.did.QueryOuterClass.QueryDidDocResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDidDocMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AllDids returns a list of all existing DIDs.
     * </pre>
     */
    public void allDids(ixo.did.QueryOuterClass.QueryAllDidsRequest request,
        io.grpc.stub.StreamObserver<ixo.did.QueryOuterClass.QueryAllDidsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAllDidsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AllDidDocs returns a list of all existing DidDocs (i.e. all DIDs along with their DidDoc info).
     * </pre>
     */
    public void allDidDocs(ixo.did.QueryOuterClass.QueryAllDidDocsRequest request,
        io.grpc.stub.StreamObserver<ixo.did.QueryOuterClass.QueryAllDidDocsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAllDidDocsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddressFromDid retrieves the cosmos address associated to an ixo DID.
     * </pre>
     */
    public void addressFromDid(ixo.did.QueryOuterClass.QueryAddressFromDidRequest request,
        io.grpc.stub.StreamObserver<ixo.did.QueryOuterClass.QueryAddressFromDidResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddressFromDidMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddressFromBase58EncodedPubkey retrieves the cosmos address associated to an ixo DID's pubkey.
     * </pre>
     */
    public void addressFromBase58EncodedPubkey(ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyRequest request,
        io.grpc.stub.StreamObserver<ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddressFromBase58EncodedPubkeyMethod(), getCallOptions()), request, responseObserver);
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
     * DidDoc queries info of a specific DID's DidDoc.
     * </pre>
     */
    public ixo.did.QueryOuterClass.QueryDidDocResponse didDoc(ixo.did.QueryOuterClass.QueryDidDocRequest request) {
      return blockingUnaryCall(
          getChannel(), getDidDocMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AllDids returns a list of all existing DIDs.
     * </pre>
     */
    public ixo.did.QueryOuterClass.QueryAllDidsResponse allDids(ixo.did.QueryOuterClass.QueryAllDidsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAllDidsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AllDidDocs returns a list of all existing DidDocs (i.e. all DIDs along with their DidDoc info).
     * </pre>
     */
    public ixo.did.QueryOuterClass.QueryAllDidDocsResponse allDidDocs(ixo.did.QueryOuterClass.QueryAllDidDocsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAllDidDocsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddressFromDid retrieves the cosmos address associated to an ixo DID.
     * </pre>
     */
    public ixo.did.QueryOuterClass.QueryAddressFromDidResponse addressFromDid(ixo.did.QueryOuterClass.QueryAddressFromDidRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddressFromDidMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddressFromBase58EncodedPubkey retrieves the cosmos address associated to an ixo DID's pubkey.
     * </pre>
     */
    public ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyResponse addressFromBase58EncodedPubkey(ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddressFromBase58EncodedPubkeyMethod(), getCallOptions(), request);
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
     * DidDoc queries info of a specific DID's DidDoc.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ixo.did.QueryOuterClass.QueryDidDocResponse> didDoc(
        ixo.did.QueryOuterClass.QueryDidDocRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDidDocMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AllDids returns a list of all existing DIDs.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ixo.did.QueryOuterClass.QueryAllDidsResponse> allDids(
        ixo.did.QueryOuterClass.QueryAllDidsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAllDidsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AllDidDocs returns a list of all existing DidDocs (i.e. all DIDs along with their DidDoc info).
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ixo.did.QueryOuterClass.QueryAllDidDocsResponse> allDidDocs(
        ixo.did.QueryOuterClass.QueryAllDidDocsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAllDidDocsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddressFromDid retrieves the cosmos address associated to an ixo DID.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ixo.did.QueryOuterClass.QueryAddressFromDidResponse> addressFromDid(
        ixo.did.QueryOuterClass.QueryAddressFromDidRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddressFromDidMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddressFromBase58EncodedPubkey retrieves the cosmos address associated to an ixo DID's pubkey.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyResponse> addressFromBase58EncodedPubkey(
        ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddressFromBase58EncodedPubkeyMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DID_DOC = 0;
  private static final int METHODID_ALL_DIDS = 1;
  private static final int METHODID_ALL_DID_DOCS = 2;
  private static final int METHODID_ADDRESS_FROM_DID = 3;
  private static final int METHODID_ADDRESS_FROM_BASE58ENCODED_PUBKEY = 4;

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
        case METHODID_DID_DOC:
          serviceImpl.didDoc((ixo.did.QueryOuterClass.QueryDidDocRequest) request,
              (io.grpc.stub.StreamObserver<ixo.did.QueryOuterClass.QueryDidDocResponse>) responseObserver);
          break;
        case METHODID_ALL_DIDS:
          serviceImpl.allDids((ixo.did.QueryOuterClass.QueryAllDidsRequest) request,
              (io.grpc.stub.StreamObserver<ixo.did.QueryOuterClass.QueryAllDidsResponse>) responseObserver);
          break;
        case METHODID_ALL_DID_DOCS:
          serviceImpl.allDidDocs((ixo.did.QueryOuterClass.QueryAllDidDocsRequest) request,
              (io.grpc.stub.StreamObserver<ixo.did.QueryOuterClass.QueryAllDidDocsResponse>) responseObserver);
          break;
        case METHODID_ADDRESS_FROM_DID:
          serviceImpl.addressFromDid((ixo.did.QueryOuterClass.QueryAddressFromDidRequest) request,
              (io.grpc.stub.StreamObserver<ixo.did.QueryOuterClass.QueryAddressFromDidResponse>) responseObserver);
          break;
        case METHODID_ADDRESS_FROM_BASE58ENCODED_PUBKEY:
          serviceImpl.addressFromBase58EncodedPubkey((ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyRequest) request,
              (io.grpc.stub.StreamObserver<ixo.did.QueryOuterClass.QueryAddressFromBase58EncodedPubkeyResponse>) responseObserver);
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
      return ixo.did.QueryOuterClass.getDescriptor();
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
              .addMethod(getDidDocMethod())
              .addMethod(getAllDidsMethod())
              .addMethod(getAllDidDocsMethod())
              .addMethod(getAddressFromDidMethod())
              .addMethod(getAddressFromBase58EncodedPubkeyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
