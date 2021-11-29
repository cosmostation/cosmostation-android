package BitCannaGlobal.bcna.bcna;

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
    comments = "Source: bcna/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "BitCannaGlobal.bcna.bcna.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidRequest,
      BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidResponse> getBitcannaidMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Bitcannaid",
      requestType = BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidRequest.class,
      responseType = BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidRequest,
      BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidResponse> getBitcannaidMethod() {
    io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidRequest, BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidResponse> getBitcannaidMethod;
    if ((getBitcannaidMethod = QueryGrpc.getBitcannaidMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBitcannaidMethod = QueryGrpc.getBitcannaidMethod) == null) {
          QueryGrpc.getBitcannaidMethod = getBitcannaidMethod =
              io.grpc.MethodDescriptor.<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidRequest, BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Bitcannaid"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Bitcannaid"))
              .build();
        }
      }
    }
    return getBitcannaidMethod;
  }

  private static volatile io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidRequest,
      BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidResponse> getBitcannaidAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BitcannaidAll",
      requestType = BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidRequest.class,
      responseType = BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidRequest,
      BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidResponse> getBitcannaidAllMethod() {
    io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidRequest, BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidResponse> getBitcannaidAllMethod;
    if ((getBitcannaidAllMethod = QueryGrpc.getBitcannaidAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBitcannaidAllMethod = QueryGrpc.getBitcannaidAllMethod) == null) {
          QueryGrpc.getBitcannaidAllMethod = getBitcannaidAllMethod =
              io.grpc.MethodDescriptor.<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidRequest, BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BitcannaidAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BitcannaidAll"))
              .build();
        }
      }
    }
    return getBitcannaidAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainRequest,
      BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainResponse> getSupplychainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Supplychain",
      requestType = BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainRequest.class,
      responseType = BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainRequest,
      BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainResponse> getSupplychainMethod() {
    io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainRequest, BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainResponse> getSupplychainMethod;
    if ((getSupplychainMethod = QueryGrpc.getSupplychainMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSupplychainMethod = QueryGrpc.getSupplychainMethod) == null) {
          QueryGrpc.getSupplychainMethod = getSupplychainMethod =
              io.grpc.MethodDescriptor.<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainRequest, BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Supplychain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Supplychain"))
              .build();
        }
      }
    }
    return getSupplychainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainRequest,
      BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainResponse> getSupplychainAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SupplychainAll",
      requestType = BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainRequest.class,
      responseType = BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainRequest,
      BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainResponse> getSupplychainAllMethod() {
    io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainRequest, BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainResponse> getSupplychainAllMethod;
    if ((getSupplychainAllMethod = QueryGrpc.getSupplychainAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSupplychainAllMethod = QueryGrpc.getSupplychainAllMethod) == null) {
          QueryGrpc.getSupplychainAllMethod = getSupplychainAllMethod =
              io.grpc.MethodDescriptor.<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainRequest, BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SupplychainAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SupplychainAll"))
              .build();
        }
      }
    }
    return getSupplychainAllMethod;
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
     * Queries a bitcannaid by id.
     * </pre>
     */
    public void bitcannaid(BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidRequest request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBitcannaidMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of bitcannaid items.
     * </pre>
     */
    public void bitcannaidAll(BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidRequest request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBitcannaidAllMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a supplychain by id.
     * </pre>
     */
    public void supplychain(BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainRequest request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSupplychainMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries a list of supplychain items.
     * </pre>
     */
    public void supplychainAll(BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainRequest request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSupplychainAllMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getBitcannaidMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidRequest,
                BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidResponse>(
                  this, METHODID_BITCANNAID)))
          .addMethod(
            getBitcannaidAllMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidRequest,
                BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidResponse>(
                  this, METHODID_BITCANNAID_ALL)))
          .addMethod(
            getSupplychainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainRequest,
                BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainResponse>(
                  this, METHODID_SUPPLYCHAIN)))
          .addMethod(
            getSupplychainAllMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainRequest,
                BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainResponse>(
                  this, METHODID_SUPPLYCHAIN_ALL)))
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
     * Queries a bitcannaid by id.
     * </pre>
     */
    public void bitcannaid(BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidRequest request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBitcannaidMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of bitcannaid items.
     * </pre>
     */
    public void bitcannaidAll(BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidRequest request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBitcannaidAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a supplychain by id.
     * </pre>
     */
    public void supplychain(BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainRequest request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSupplychainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries a list of supplychain items.
     * </pre>
     */
    public void supplychainAll(BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainRequest request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSupplychainAllMethod(), getCallOptions()), request, responseObserver);
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
     * Queries a bitcannaid by id.
     * </pre>
     */
    public BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidResponse bitcannaid(BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidRequest request) {
      return blockingUnaryCall(
          getChannel(), getBitcannaidMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of bitcannaid items.
     * </pre>
     */
    public BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidResponse bitcannaidAll(BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidRequest request) {
      return blockingUnaryCall(
          getChannel(), getBitcannaidAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a supplychain by id.
     * </pre>
     */
    public BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainResponse supplychain(BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainRequest request) {
      return blockingUnaryCall(
          getChannel(), getSupplychainMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries a list of supplychain items.
     * </pre>
     */
    public BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainResponse supplychainAll(BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainRequest request) {
      return blockingUnaryCall(
          getChannel(), getSupplychainAllMethod(), getCallOptions(), request);
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
     * Queries a bitcannaid by id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidResponse> bitcannaid(
        BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBitcannaidMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of bitcannaid items.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidResponse> bitcannaidAll(
        BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBitcannaidAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a supplychain by id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainResponse> supplychain(
        BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSupplychainMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries a list of supplychain items.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainResponse> supplychainAll(
        BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSupplychainAllMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BITCANNAID = 0;
  private static final int METHODID_BITCANNAID_ALL = 1;
  private static final int METHODID_SUPPLYCHAIN = 2;
  private static final int METHODID_SUPPLYCHAIN_ALL = 3;

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
        case METHODID_BITCANNAID:
          serviceImpl.bitcannaid((BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidRequest) request,
              (io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetBitcannaidResponse>) responseObserver);
          break;
        case METHODID_BITCANNAID_ALL:
          serviceImpl.bitcannaidAll((BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidRequest) request,
              (io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllBitcannaidResponse>) responseObserver);
          break;
        case METHODID_SUPPLYCHAIN:
          serviceImpl.supplychain((BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainRequest) request,
              (io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryGetSupplychainResponse>) responseObserver);
          break;
        case METHODID_SUPPLYCHAIN_ALL:
          serviceImpl.supplychainAll((BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainRequest) request,
              (io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.QueryOuterClass.QueryAllSupplychainResponse>) responseObserver);
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
      return BitCannaGlobal.bcna.bcna.QueryOuterClass.getDescriptor();
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
              .addMethod(getBitcannaidMethod())
              .addMethod(getBitcannaidAllMethod())
              .addMethod(getSupplychainMethod())
              .addMethod(getSupplychainAllMethod())
              .build();
        }
      }
    }
    return result;
  }
}
