package shentu.cert.v1alpha1;

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
 * Query defines the gRPC querier service for cert module.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: shentu/cert/v1alpha1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "shentu.cert.v1alpha1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierRequest,
      shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierResponse> getCertifierMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Certifier",
      requestType = shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierRequest.class,
      responseType = shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierRequest,
      shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierResponse> getCertifierMethod() {
    io.grpc.MethodDescriptor<shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierRequest, shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierResponse> getCertifierMethod;
    if ((getCertifierMethod = QueryGrpc.getCertifierMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCertifierMethod = QueryGrpc.getCertifierMethod) == null) {
          QueryGrpc.getCertifierMethod = getCertifierMethod =
              io.grpc.MethodDescriptor.<shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierRequest, shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Certifier"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Certifier"))
              .build();
        }
      }
    }
    return getCertifierMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersRequest,
      shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersResponse> getCertifiersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Certifiers",
      requestType = shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersRequest.class,
      responseType = shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersRequest,
      shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersResponse> getCertifiersMethod() {
    io.grpc.MethodDescriptor<shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersRequest, shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersResponse> getCertifiersMethod;
    if ((getCertifiersMethod = QueryGrpc.getCertifiersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCertifiersMethod = QueryGrpc.getCertifiersMethod) == null) {
          QueryGrpc.getCertifiersMethod = getCertifiersMethod =
              io.grpc.MethodDescriptor.<shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersRequest, shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Certifiers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Certifiers"))
              .build();
        }
      }
    }
    return getCertifiersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformRequest,
      shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformResponse> getPlatformMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Platform",
      requestType = shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformRequest.class,
      responseType = shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformRequest,
      shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformResponse> getPlatformMethod() {
    io.grpc.MethodDescriptor<shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformRequest, shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformResponse> getPlatformMethod;
    if ((getPlatformMethod = QueryGrpc.getPlatformMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPlatformMethod = QueryGrpc.getPlatformMethod) == null) {
          QueryGrpc.getPlatformMethod = getPlatformMethod =
              io.grpc.MethodDescriptor.<shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformRequest, shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Platform"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Platform"))
              .build();
        }
      }
    }
    return getPlatformMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateRequest,
      shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateResponse> getCertificateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Certificate",
      requestType = shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateRequest.class,
      responseType = shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateRequest,
      shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateResponse> getCertificateMethod() {
    io.grpc.MethodDescriptor<shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateRequest, shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateResponse> getCertificateMethod;
    if ((getCertificateMethod = QueryGrpc.getCertificateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCertificateMethod = QueryGrpc.getCertificateMethod) == null) {
          QueryGrpc.getCertificateMethod = getCertificateMethod =
              io.grpc.MethodDescriptor.<shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateRequest, shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Certificate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Certificate"))
              .build();
        }
      }
    }
    return getCertificateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesRequest,
      shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesResponse> getCertificatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Certificates",
      requestType = shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesRequest.class,
      responseType = shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesRequest,
      shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesResponse> getCertificatesMethod() {
    io.grpc.MethodDescriptor<shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesRequest, shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesResponse> getCertificatesMethod;
    if ((getCertificatesMethod = QueryGrpc.getCertificatesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCertificatesMethod = QueryGrpc.getCertificatesMethod) == null) {
          QueryGrpc.getCertificatesMethod = getCertificatesMethod =
              io.grpc.MethodDescriptor.<shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesRequest, shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Certificates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Certificates"))
              .build();
        }
      }
    }
    return getCertificatesMethod;
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
   * Query defines the gRPC querier service for cert module.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     */
    public void certifier(shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierRequest request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCertifierMethod(), responseObserver);
    }

    /**
     */
    public void certifiers(shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersRequest request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCertifiersMethod(), responseObserver);
    }

    /**
     */
    public void platform(shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformRequest request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPlatformMethod(), responseObserver);
    }

    /**
     */
    public void certificate(shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateRequest request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCertificateMethod(), responseObserver);
    }

    /**
     */
    public void certificates(shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesRequest request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCertificatesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCertifierMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierRequest,
                shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierResponse>(
                  this, METHODID_CERTIFIER)))
          .addMethod(
            getCertifiersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersRequest,
                shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersResponse>(
                  this, METHODID_CERTIFIERS)))
          .addMethod(
            getPlatformMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformRequest,
                shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformResponse>(
                  this, METHODID_PLATFORM)))
          .addMethod(
            getCertificateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateRequest,
                shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateResponse>(
                  this, METHODID_CERTIFICATE)))
          .addMethod(
            getCertificatesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesRequest,
                shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesResponse>(
                  this, METHODID_CERTIFICATES)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for cert module.
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
    public void certifier(shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierRequest request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCertifierMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void certifiers(shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersRequest request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCertifiersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void platform(shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformRequest request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPlatformMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void certificate(shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateRequest request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCertificateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void certificates(shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesRequest request,
        io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCertificatesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for cert module.
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
    public shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierResponse certifier(shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierRequest request) {
      return blockingUnaryCall(
          getChannel(), getCertifierMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersResponse certifiers(shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersRequest request) {
      return blockingUnaryCall(
          getChannel(), getCertifiersMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformResponse platform(shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformRequest request) {
      return blockingUnaryCall(
          getChannel(), getPlatformMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateResponse certificate(shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateRequest request) {
      return blockingUnaryCall(
          getChannel(), getCertificateMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesResponse certificates(shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesRequest request) {
      return blockingUnaryCall(
          getChannel(), getCertificatesMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for cert module.
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
    public com.google.common.util.concurrent.ListenableFuture<shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierResponse> certifier(
        shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCertifierMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersResponse> certifiers(
        shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCertifiersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformResponse> platform(
        shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPlatformMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateResponse> certificate(
        shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCertificateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesResponse> certificates(
        shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCertificatesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CERTIFIER = 0;
  private static final int METHODID_CERTIFIERS = 1;
  private static final int METHODID_PLATFORM = 2;
  private static final int METHODID_CERTIFICATE = 3;
  private static final int METHODID_CERTIFICATES = 4;

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
        case METHODID_CERTIFIER:
          serviceImpl.certifier((shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierRequest) request,
              (io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.QueryOuterClass.QueryCertifierResponse>) responseObserver);
          break;
        case METHODID_CERTIFIERS:
          serviceImpl.certifiers((shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersRequest) request,
              (io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.QueryOuterClass.QueryCertifiersResponse>) responseObserver);
          break;
        case METHODID_PLATFORM:
          serviceImpl.platform((shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformRequest) request,
              (io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.QueryOuterClass.QueryPlatformResponse>) responseObserver);
          break;
        case METHODID_CERTIFICATE:
          serviceImpl.certificate((shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateRequest) request,
              (io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.QueryOuterClass.QueryCertificateResponse>) responseObserver);
          break;
        case METHODID_CERTIFICATES:
          serviceImpl.certificates((shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesRequest) request,
              (io.grpc.stub.StreamObserver<shentu.cert.v1alpha1.QueryOuterClass.QueryCertificatesResponse>) responseObserver);
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
      return shentu.cert.v1alpha1.QueryOuterClass.getDescriptor();
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
              .addMethod(getCertifierMethod())
              .addMethod(getCertifiersMethod())
              .addMethod(getPlatformMethod())
              .addMethod(getCertificateMethod())
              .addMethod(getCertificatesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
