package confio.twasm.v1beta1;

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
    comments = "Source: confio/twasm/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "confio.twasm.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsRequest,
      confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsResponse> getPrivilegedContractsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PrivilegedContracts",
      requestType = confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsRequest.class,
      responseType = confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsRequest,
      confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsResponse> getPrivilegedContractsMethod() {
    io.grpc.MethodDescriptor<confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsRequest, confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsResponse> getPrivilegedContractsMethod;
    if ((getPrivilegedContractsMethod = QueryGrpc.getPrivilegedContractsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPrivilegedContractsMethod = QueryGrpc.getPrivilegedContractsMethod) == null) {
          QueryGrpc.getPrivilegedContractsMethod = getPrivilegedContractsMethod =
              io.grpc.MethodDescriptor.<confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsRequest, confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PrivilegedContracts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PrivilegedContracts"))
              .build();
        }
      }
    }
    return getPrivilegedContractsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeRequest,
      confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeResponse> getContractsByPrivilegeTypeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ContractsByPrivilegeType",
      requestType = confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeRequest.class,
      responseType = confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeRequest,
      confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeResponse> getContractsByPrivilegeTypeMethod() {
    io.grpc.MethodDescriptor<confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeRequest, confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeResponse> getContractsByPrivilegeTypeMethod;
    if ((getContractsByPrivilegeTypeMethod = QueryGrpc.getContractsByPrivilegeTypeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getContractsByPrivilegeTypeMethod = QueryGrpc.getContractsByPrivilegeTypeMethod) == null) {
          QueryGrpc.getContractsByPrivilegeTypeMethod = getContractsByPrivilegeTypeMethod =
              io.grpc.MethodDescriptor.<confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeRequest, confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ContractsByPrivilegeType"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ContractsByPrivilegeType"))
              .build();
        }
      }
    }
    return getContractsByPrivilegeTypeMethod;
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
     * PrivilegedContracts returns all privileged contracts
     * </pre>
     */
    public void privilegedContracts(confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsRequest request,
        io.grpc.stub.StreamObserver<confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPrivilegedContractsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ContractsByPrivilegeType returns all contracts that have registered for the
     * privilege type
     * </pre>
     */
    public void contractsByPrivilegeType(confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeRequest request,
        io.grpc.stub.StreamObserver<confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getContractsByPrivilegeTypeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPrivilegedContractsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsRequest,
                confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsResponse>(
                  this, METHODID_PRIVILEGED_CONTRACTS)))
          .addMethod(
            getContractsByPrivilegeTypeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeRequest,
                confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeResponse>(
                  this, METHODID_CONTRACTS_BY_PRIVILEGE_TYPE)))
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
     * PrivilegedContracts returns all privileged contracts
     * </pre>
     */
    public void privilegedContracts(confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsRequest request,
        io.grpc.stub.StreamObserver<confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPrivilegedContractsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ContractsByPrivilegeType returns all contracts that have registered for the
     * privilege type
     * </pre>
     */
    public void contractsByPrivilegeType(confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeRequest request,
        io.grpc.stub.StreamObserver<confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getContractsByPrivilegeTypeMethod(), getCallOptions()), request, responseObserver);
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
     * PrivilegedContracts returns all privileged contracts
     * </pre>
     */
    public confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsResponse privilegedContracts(confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPrivilegedContractsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ContractsByPrivilegeType returns all contracts that have registered for the
     * privilege type
     * </pre>
     */
    public confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeResponse contractsByPrivilegeType(confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeRequest request) {
      return blockingUnaryCall(
          getChannel(), getContractsByPrivilegeTypeMethod(), getCallOptions(), request);
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
     * PrivilegedContracts returns all privileged contracts
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsResponse> privilegedContracts(
        confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPrivilegedContractsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ContractsByPrivilegeType returns all contracts that have registered for the
     * privilege type
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeResponse> contractsByPrivilegeType(
        confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getContractsByPrivilegeTypeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PRIVILEGED_CONTRACTS = 0;
  private static final int METHODID_CONTRACTS_BY_PRIVILEGE_TYPE = 1;

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
        case METHODID_PRIVILEGED_CONTRACTS:
          serviceImpl.privilegedContracts((confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsRequest) request,
              (io.grpc.stub.StreamObserver<confio.twasm.v1beta1.QueryOuterClass.QueryPrivilegedContractsResponse>) responseObserver);
          break;
        case METHODID_CONTRACTS_BY_PRIVILEGE_TYPE:
          serviceImpl.contractsByPrivilegeType((confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeRequest) request,
              (io.grpc.stub.StreamObserver<confio.twasm.v1beta1.QueryOuterClass.QueryContractsByPrivilegeTypeResponse>) responseObserver);
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
      return confio.twasm.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getPrivilegedContractsMethod())
              .addMethod(getContractsByPrivilegeTypeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
