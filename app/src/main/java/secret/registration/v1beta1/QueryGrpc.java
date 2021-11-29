package secret.registration.v1beta1;

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
    comments = "Source: secret/registration/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "secret.registration.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      secret.registration.v1beta1.Msg.Key> getTxKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TxKey",
      requestType = com.google.protobuf.Empty.class,
      responseType = secret.registration.v1beta1.Msg.Key.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      secret.registration.v1beta1.Msg.Key> getTxKeyMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, secret.registration.v1beta1.Msg.Key> getTxKeyMethod;
    if ((getTxKeyMethod = QueryGrpc.getTxKeyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTxKeyMethod = QueryGrpc.getTxKeyMethod) == null) {
          QueryGrpc.getTxKeyMethod = getTxKeyMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, secret.registration.v1beta1.Msg.Key>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TxKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  secret.registration.v1beta1.Msg.Key.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TxKey"))
              .build();
        }
      }
    }
    return getTxKeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      secret.registration.v1beta1.Msg.Key> getRegistrationKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegistrationKey",
      requestType = com.google.protobuf.Empty.class,
      responseType = secret.registration.v1beta1.Msg.Key.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      secret.registration.v1beta1.Msg.Key> getRegistrationKeyMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, secret.registration.v1beta1.Msg.Key> getRegistrationKeyMethod;
    if ((getRegistrationKeyMethod = QueryGrpc.getRegistrationKeyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRegistrationKeyMethod = QueryGrpc.getRegistrationKeyMethod) == null) {
          QueryGrpc.getRegistrationKeyMethod = getRegistrationKeyMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, secret.registration.v1beta1.Msg.Key>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegistrationKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  secret.registration.v1beta1.Msg.Key.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RegistrationKey"))
              .build();
        }
      }
    }
    return getRegistrationKeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedRequest,
      secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedResponse> getEncryptedSeedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EncryptedSeed",
      requestType = secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedRequest.class,
      responseType = secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedRequest,
      secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedResponse> getEncryptedSeedMethod() {
    io.grpc.MethodDescriptor<secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedRequest, secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedResponse> getEncryptedSeedMethod;
    if ((getEncryptedSeedMethod = QueryGrpc.getEncryptedSeedMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEncryptedSeedMethod = QueryGrpc.getEncryptedSeedMethod) == null) {
          QueryGrpc.getEncryptedSeedMethod = getEncryptedSeedMethod =
              io.grpc.MethodDescriptor.<secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedRequest, secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EncryptedSeed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EncryptedSeed"))
              .build();
        }
      }
    }
    return getEncryptedSeedMethod;
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
     * Returns the key used for transactions
     * </pre>
     */
    public void txKey(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<secret.registration.v1beta1.Msg.Key> responseObserver) {
      asyncUnimplementedUnaryCall(getTxKeyMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns the key used for registration
     * </pre>
     */
    public void registrationKey(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<secret.registration.v1beta1.Msg.Key> responseObserver) {
      asyncUnimplementedUnaryCall(getRegistrationKeyMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns the encrypted seed for a registered node by public key
     * </pre>
     */
    public void encryptedSeed(secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedRequest request,
        io.grpc.stub.StreamObserver<secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEncryptedSeedMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getTxKeyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                secret.registration.v1beta1.Msg.Key>(
                  this, METHODID_TX_KEY)))
          .addMethod(
            getRegistrationKeyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                secret.registration.v1beta1.Msg.Key>(
                  this, METHODID_REGISTRATION_KEY)))
          .addMethod(
            getEncryptedSeedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedRequest,
                secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedResponse>(
                  this, METHODID_ENCRYPTED_SEED)))
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
     * Returns the key used for transactions
     * </pre>
     */
    public void txKey(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<secret.registration.v1beta1.Msg.Key> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTxKeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns the key used for registration
     * </pre>
     */
    public void registrationKey(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<secret.registration.v1beta1.Msg.Key> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegistrationKeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns the encrypted seed for a registered node by public key
     * </pre>
     */
    public void encryptedSeed(secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedRequest request,
        io.grpc.stub.StreamObserver<secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEncryptedSeedMethod(), getCallOptions()), request, responseObserver);
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
     * Returns the key used for transactions
     * </pre>
     */
    public secret.registration.v1beta1.Msg.Key txKey(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getTxKeyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns the key used for registration
     * </pre>
     */
    public secret.registration.v1beta1.Msg.Key registrationKey(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getRegistrationKeyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns the encrypted seed for a registered node by public key
     * </pre>
     */
    public secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedResponse encryptedSeed(secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedRequest request) {
      return blockingUnaryCall(
          getChannel(), getEncryptedSeedMethod(), getCallOptions(), request);
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
     * Returns the key used for transactions
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<secret.registration.v1beta1.Msg.Key> txKey(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getTxKeyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns the key used for registration
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<secret.registration.v1beta1.Msg.Key> registrationKey(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getRegistrationKeyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns the encrypted seed for a registered node by public key
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedResponse> encryptedSeed(
        secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEncryptedSeedMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TX_KEY = 0;
  private static final int METHODID_REGISTRATION_KEY = 1;
  private static final int METHODID_ENCRYPTED_SEED = 2;

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
        case METHODID_TX_KEY:
          serviceImpl.txKey((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<secret.registration.v1beta1.Msg.Key>) responseObserver);
          break;
        case METHODID_REGISTRATION_KEY:
          serviceImpl.registrationKey((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<secret.registration.v1beta1.Msg.Key>) responseObserver);
          break;
        case METHODID_ENCRYPTED_SEED:
          serviceImpl.encryptedSeed((secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedRequest) request,
              (io.grpc.stub.StreamObserver<secret.registration.v1beta1.QueryOuterClass.QueryEncryptedSeedResponse>) responseObserver);
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
      return secret.registration.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getTxKeyMethod())
              .addMethod(getRegistrationKeyMethod())
              .addMethod(getEncryptedSeedMethod())
              .build();
        }
      }
    }
    return result;
  }
}
