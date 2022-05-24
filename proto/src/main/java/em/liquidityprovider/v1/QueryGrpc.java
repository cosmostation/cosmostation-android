package em.liquidityprovider.v1;

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
    comments = "Source: em/liquidityprovider/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "em.liquidityprovider.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<em.liquidityprovider.v1.QueryOuterClass.QueryListRequest,
      em.liquidityprovider.v1.QueryOuterClass.QueryListResponse> getListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "List",
      requestType = em.liquidityprovider.v1.QueryOuterClass.QueryListRequest.class,
      responseType = em.liquidityprovider.v1.QueryOuterClass.QueryListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.liquidityprovider.v1.QueryOuterClass.QueryListRequest,
      em.liquidityprovider.v1.QueryOuterClass.QueryListResponse> getListMethod() {
    io.grpc.MethodDescriptor<em.liquidityprovider.v1.QueryOuterClass.QueryListRequest, em.liquidityprovider.v1.QueryOuterClass.QueryListResponse> getListMethod;
    if ((getListMethod = QueryGrpc.getListMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getListMethod = QueryGrpc.getListMethod) == null) {
          QueryGrpc.getListMethod = getListMethod =
              io.grpc.MethodDescriptor.<em.liquidityprovider.v1.QueryOuterClass.QueryListRequest, em.liquidityprovider.v1.QueryOuterClass.QueryListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "List"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.liquidityprovider.v1.QueryOuterClass.QueryListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.liquidityprovider.v1.QueryOuterClass.QueryListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("List"))
              .build();
        }
      }
    }
    return getListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<em.liquidityprovider.v1.QueryOuterClass.QueryMintableRequest,
      em.liquidityprovider.v1.QueryOuterClass.QueryMintableResponse> getMintableMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Mintable",
      requestType = em.liquidityprovider.v1.QueryOuterClass.QueryMintableRequest.class,
      responseType = em.liquidityprovider.v1.QueryOuterClass.QueryMintableResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.liquidityprovider.v1.QueryOuterClass.QueryMintableRequest,
      em.liquidityprovider.v1.QueryOuterClass.QueryMintableResponse> getMintableMethod() {
    io.grpc.MethodDescriptor<em.liquidityprovider.v1.QueryOuterClass.QueryMintableRequest, em.liquidityprovider.v1.QueryOuterClass.QueryMintableResponse> getMintableMethod;
    if ((getMintableMethod = QueryGrpc.getMintableMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getMintableMethod = QueryGrpc.getMintableMethod) == null) {
          QueryGrpc.getMintableMethod = getMintableMethod =
              io.grpc.MethodDescriptor.<em.liquidityprovider.v1.QueryOuterClass.QueryMintableRequest, em.liquidityprovider.v1.QueryOuterClass.QueryMintableResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Mintable"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.liquidityprovider.v1.QueryOuterClass.QueryMintableRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.liquidityprovider.v1.QueryOuterClass.QueryMintableResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Mintable"))
              .build();
        }
      }
    }
    return getMintableMethod;
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
    public void list(em.liquidityprovider.v1.QueryOuterClass.QueryListRequest request,
        io.grpc.stub.StreamObserver<em.liquidityprovider.v1.QueryOuterClass.QueryListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getListMethod(), responseObserver);
    }

    /**
     */
    public void mintable(em.liquidityprovider.v1.QueryOuterClass.QueryMintableRequest request,
        io.grpc.stub.StreamObserver<em.liquidityprovider.v1.QueryOuterClass.QueryMintableResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMintableMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.liquidityprovider.v1.QueryOuterClass.QueryListRequest,
                em.liquidityprovider.v1.QueryOuterClass.QueryListResponse>(
                  this, METHODID_LIST)))
          .addMethod(
            getMintableMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.liquidityprovider.v1.QueryOuterClass.QueryMintableRequest,
                em.liquidityprovider.v1.QueryOuterClass.QueryMintableResponse>(
                  this, METHODID_MINTABLE)))
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
    public void list(em.liquidityprovider.v1.QueryOuterClass.QueryListRequest request,
        io.grpc.stub.StreamObserver<em.liquidityprovider.v1.QueryOuterClass.QueryListResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void mintable(em.liquidityprovider.v1.QueryOuterClass.QueryMintableRequest request,
        io.grpc.stub.StreamObserver<em.liquidityprovider.v1.QueryOuterClass.QueryMintableResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMintableMethod(), getCallOptions()), request, responseObserver);
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
    public em.liquidityprovider.v1.QueryOuterClass.QueryListResponse list(em.liquidityprovider.v1.QueryOuterClass.QueryListRequest request) {
      return blockingUnaryCall(
          getChannel(), getListMethod(), getCallOptions(), request);
    }

    /**
     */
    public em.liquidityprovider.v1.QueryOuterClass.QueryMintableResponse mintable(em.liquidityprovider.v1.QueryOuterClass.QueryMintableRequest request) {
      return blockingUnaryCall(
          getChannel(), getMintableMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<em.liquidityprovider.v1.QueryOuterClass.QueryListResponse> list(
        em.liquidityprovider.v1.QueryOuterClass.QueryListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getListMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<em.liquidityprovider.v1.QueryOuterClass.QueryMintableResponse> mintable(
        em.liquidityprovider.v1.QueryOuterClass.QueryMintableRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMintableMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIST = 0;
  private static final int METHODID_MINTABLE = 1;

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
        case METHODID_LIST:
          serviceImpl.list((em.liquidityprovider.v1.QueryOuterClass.QueryListRequest) request,
              (io.grpc.stub.StreamObserver<em.liquidityprovider.v1.QueryOuterClass.QueryListResponse>) responseObserver);
          break;
        case METHODID_MINTABLE:
          serviceImpl.mintable((em.liquidityprovider.v1.QueryOuterClass.QueryMintableRequest) request,
              (io.grpc.stub.StreamObserver<em.liquidityprovider.v1.QueryOuterClass.QueryMintableResponse>) responseObserver);
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
      return em.liquidityprovider.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getListMethod())
              .addMethod(getMintableMethod())
              .build();
        }
      }
    }
    return result;
  }
}
