package lum.network.beam;

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
    comments = "Source: lum/beam/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "lum.network.beam.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<lum.network.beam.QueryOuterClass.QueryGetBeamRequest,
      lum.network.beam.QueryOuterClass.QueryGetBeamResponse> getBeamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Beam",
      requestType = lum.network.beam.QueryOuterClass.QueryGetBeamRequest.class,
      responseType = lum.network.beam.QueryOuterClass.QueryGetBeamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<lum.network.beam.QueryOuterClass.QueryGetBeamRequest,
      lum.network.beam.QueryOuterClass.QueryGetBeamResponse> getBeamMethod() {
    io.grpc.MethodDescriptor<lum.network.beam.QueryOuterClass.QueryGetBeamRequest, lum.network.beam.QueryOuterClass.QueryGetBeamResponse> getBeamMethod;
    if ((getBeamMethod = QueryGrpc.getBeamMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBeamMethod = QueryGrpc.getBeamMethod) == null) {
          QueryGrpc.getBeamMethod = getBeamMethod =
              io.grpc.MethodDescriptor.<lum.network.beam.QueryOuterClass.QueryGetBeamRequest, lum.network.beam.QueryOuterClass.QueryGetBeamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Beam"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  lum.network.beam.QueryOuterClass.QueryGetBeamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  lum.network.beam.QueryOuterClass.QueryGetBeamResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Beam"))
              .build();
        }
      }
    }
    return getBeamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<lum.network.beam.QueryOuterClass.QueryFetchBeamsRequest,
      lum.network.beam.QueryOuterClass.QueryFetchBeamsResponse> getBeamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Beams",
      requestType = lum.network.beam.QueryOuterClass.QueryFetchBeamsRequest.class,
      responseType = lum.network.beam.QueryOuterClass.QueryFetchBeamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<lum.network.beam.QueryOuterClass.QueryFetchBeamsRequest,
      lum.network.beam.QueryOuterClass.QueryFetchBeamsResponse> getBeamsMethod() {
    io.grpc.MethodDescriptor<lum.network.beam.QueryOuterClass.QueryFetchBeamsRequest, lum.network.beam.QueryOuterClass.QueryFetchBeamsResponse> getBeamsMethod;
    if ((getBeamsMethod = QueryGrpc.getBeamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBeamsMethod = QueryGrpc.getBeamsMethod) == null) {
          QueryGrpc.getBeamsMethod = getBeamsMethod =
              io.grpc.MethodDescriptor.<lum.network.beam.QueryOuterClass.QueryFetchBeamsRequest, lum.network.beam.QueryOuterClass.QueryFetchBeamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Beams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  lum.network.beam.QueryOuterClass.QueryFetchBeamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  lum.network.beam.QueryOuterClass.QueryFetchBeamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Beams"))
              .build();
        }
      }
    }
    return getBeamsMethod;
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
     */
    public void beam(lum.network.beam.QueryOuterClass.QueryGetBeamRequest request,
        io.grpc.stub.StreamObserver<lum.network.beam.QueryOuterClass.QueryGetBeamResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBeamMethod(), responseObserver);
    }

    /**
     */
    public void beams(lum.network.beam.QueryOuterClass.QueryFetchBeamsRequest request,
        io.grpc.stub.StreamObserver<lum.network.beam.QueryOuterClass.QueryFetchBeamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBeamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getBeamMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                lum.network.beam.QueryOuterClass.QueryGetBeamRequest,
                lum.network.beam.QueryOuterClass.QueryGetBeamResponse>(
                  this, METHODID_BEAM)))
          .addMethod(
            getBeamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                lum.network.beam.QueryOuterClass.QueryFetchBeamsRequest,
                lum.network.beam.QueryOuterClass.QueryFetchBeamsResponse>(
                  this, METHODID_BEAMS)))
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
     */
    public void beam(lum.network.beam.QueryOuterClass.QueryGetBeamRequest request,
        io.grpc.stub.StreamObserver<lum.network.beam.QueryOuterClass.QueryGetBeamResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBeamMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void beams(lum.network.beam.QueryOuterClass.QueryFetchBeamsRequest request,
        io.grpc.stub.StreamObserver<lum.network.beam.QueryOuterClass.QueryFetchBeamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBeamsMethod(), getCallOptions()), request, responseObserver);
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
     */
    public lum.network.beam.QueryOuterClass.QueryGetBeamResponse beam(lum.network.beam.QueryOuterClass.QueryGetBeamRequest request) {
      return blockingUnaryCall(
          getChannel(), getBeamMethod(), getCallOptions(), request);
    }

    /**
     */
    public lum.network.beam.QueryOuterClass.QueryFetchBeamsResponse beams(lum.network.beam.QueryOuterClass.QueryFetchBeamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getBeamsMethod(), getCallOptions(), request);
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
     */
    public com.google.common.util.concurrent.ListenableFuture<lum.network.beam.QueryOuterClass.QueryGetBeamResponse> beam(
        lum.network.beam.QueryOuterClass.QueryGetBeamRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBeamMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<lum.network.beam.QueryOuterClass.QueryFetchBeamsResponse> beams(
        lum.network.beam.QueryOuterClass.QueryFetchBeamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBeamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BEAM = 0;
  private static final int METHODID_BEAMS = 1;

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
        case METHODID_BEAM:
          serviceImpl.beam((lum.network.beam.QueryOuterClass.QueryGetBeamRequest) request,
              (io.grpc.stub.StreamObserver<lum.network.beam.QueryOuterClass.QueryGetBeamResponse>) responseObserver);
          break;
        case METHODID_BEAMS:
          serviceImpl.beams((lum.network.beam.QueryOuterClass.QueryFetchBeamsRequest) request,
              (io.grpc.stub.StreamObserver<lum.network.beam.QueryOuterClass.QueryFetchBeamsResponse>) responseObserver);
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
      return lum.network.beam.QueryOuterClass.getDescriptor();
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
              .addMethod(getBeamMethod())
              .addMethod(getBeamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
