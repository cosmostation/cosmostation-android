package sifnode.dispensation.v1;

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
    comments = "Source: sifnode/dispensation/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "sifnode.dispensation.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsRequest,
      sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsResponse> getAllDistributionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllDistributions",
      requestType = sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsRequest.class,
      responseType = sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsRequest,
      sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsResponse> getAllDistributionsMethod() {
    io.grpc.MethodDescriptor<sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsRequest, sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsResponse> getAllDistributionsMethod;
    if ((getAllDistributionsMethod = QueryGrpc.getAllDistributionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllDistributionsMethod = QueryGrpc.getAllDistributionsMethod) == null) {
          QueryGrpc.getAllDistributionsMethod = getAllDistributionsMethod =
              io.grpc.MethodDescriptor.<sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsRequest, sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllDistributions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllDistributions"))
              .build();
        }
      }
    }
    return getAllDistributionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameRequest,
      sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameResponse> getRecordsByDistributionNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RecordsByDistributionName",
      requestType = sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameRequest.class,
      responseType = sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameRequest,
      sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameResponse> getRecordsByDistributionNameMethod() {
    io.grpc.MethodDescriptor<sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameRequest, sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameResponse> getRecordsByDistributionNameMethod;
    if ((getRecordsByDistributionNameMethod = QueryGrpc.getRecordsByDistributionNameMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRecordsByDistributionNameMethod = QueryGrpc.getRecordsByDistributionNameMethod) == null) {
          QueryGrpc.getRecordsByDistributionNameMethod = getRecordsByDistributionNameMethod =
              io.grpc.MethodDescriptor.<sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameRequest, sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RecordsByDistributionName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RecordsByDistributionName"))
              .build();
        }
      }
    }
    return getRecordsByDistributionNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrRequest,
      sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrResponse> getRecordsByRecipientMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RecordsByRecipient",
      requestType = sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrRequest.class,
      responseType = sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrRequest,
      sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrResponse> getRecordsByRecipientMethod() {
    io.grpc.MethodDescriptor<sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrRequest, sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrResponse> getRecordsByRecipientMethod;
    if ((getRecordsByRecipientMethod = QueryGrpc.getRecordsByRecipientMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRecordsByRecipientMethod = QueryGrpc.getRecordsByRecipientMethod) == null) {
          QueryGrpc.getRecordsByRecipientMethod = getRecordsByRecipientMethod =
              io.grpc.MethodDescriptor.<sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrRequest, sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RecordsByRecipient"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RecordsByRecipient"))
              .build();
        }
      }
    }
    return getRecordsByRecipientMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.dispensation.v1.QueryOuterClass.QueryClaimsByTypeRequest,
      sifnode.dispensation.v1.QueryOuterClass.QueryClaimsResponse> getClaimsByTypeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimsByType",
      requestType = sifnode.dispensation.v1.QueryOuterClass.QueryClaimsByTypeRequest.class,
      responseType = sifnode.dispensation.v1.QueryOuterClass.QueryClaimsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.dispensation.v1.QueryOuterClass.QueryClaimsByTypeRequest,
      sifnode.dispensation.v1.QueryOuterClass.QueryClaimsResponse> getClaimsByTypeMethod() {
    io.grpc.MethodDescriptor<sifnode.dispensation.v1.QueryOuterClass.QueryClaimsByTypeRequest, sifnode.dispensation.v1.QueryOuterClass.QueryClaimsResponse> getClaimsByTypeMethod;
    if ((getClaimsByTypeMethod = QueryGrpc.getClaimsByTypeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClaimsByTypeMethod = QueryGrpc.getClaimsByTypeMethod) == null) {
          QueryGrpc.getClaimsByTypeMethod = getClaimsByTypeMethod =
              io.grpc.MethodDescriptor.<sifnode.dispensation.v1.QueryOuterClass.QueryClaimsByTypeRequest, sifnode.dispensation.v1.QueryOuterClass.QueryClaimsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimsByType"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.dispensation.v1.QueryOuterClass.QueryClaimsByTypeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.dispensation.v1.QueryOuterClass.QueryClaimsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClaimsByType"))
              .build();
        }
      }
    }
    return getClaimsByTypeMethod;
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
    public void allDistributions(sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsRequest request,
        io.grpc.stub.StreamObserver<sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAllDistributionsMethod(), responseObserver);
    }

    /**
     */
    public void recordsByDistributionName(sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameRequest request,
        io.grpc.stub.StreamObserver<sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRecordsByDistributionNameMethod(), responseObserver);
    }

    /**
     */
    public void recordsByRecipient(sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrRequest request,
        io.grpc.stub.StreamObserver<sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRecordsByRecipientMethod(), responseObserver);
    }

    /**
     */
    public void claimsByType(sifnode.dispensation.v1.QueryOuterClass.QueryClaimsByTypeRequest request,
        io.grpc.stub.StreamObserver<sifnode.dispensation.v1.QueryOuterClass.QueryClaimsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClaimsByTypeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAllDistributionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsRequest,
                sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsResponse>(
                  this, METHODID_ALL_DISTRIBUTIONS)))
          .addMethod(
            getRecordsByDistributionNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameRequest,
                sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameResponse>(
                  this, METHODID_RECORDS_BY_DISTRIBUTION_NAME)))
          .addMethod(
            getRecordsByRecipientMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrRequest,
                sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrResponse>(
                  this, METHODID_RECORDS_BY_RECIPIENT)))
          .addMethod(
            getClaimsByTypeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.dispensation.v1.QueryOuterClass.QueryClaimsByTypeRequest,
                sifnode.dispensation.v1.QueryOuterClass.QueryClaimsResponse>(
                  this, METHODID_CLAIMS_BY_TYPE)))
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
    public void allDistributions(sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsRequest request,
        io.grpc.stub.StreamObserver<sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAllDistributionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void recordsByDistributionName(sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameRequest request,
        io.grpc.stub.StreamObserver<sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRecordsByDistributionNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void recordsByRecipient(sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrRequest request,
        io.grpc.stub.StreamObserver<sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRecordsByRecipientMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void claimsByType(sifnode.dispensation.v1.QueryOuterClass.QueryClaimsByTypeRequest request,
        io.grpc.stub.StreamObserver<sifnode.dispensation.v1.QueryOuterClass.QueryClaimsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClaimsByTypeMethod(), getCallOptions()), request, responseObserver);
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
    public sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsResponse allDistributions(sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAllDistributionsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameResponse recordsByDistributionName(sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameRequest request) {
      return blockingUnaryCall(
          getChannel(), getRecordsByDistributionNameMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrResponse recordsByRecipient(sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrRequest request) {
      return blockingUnaryCall(
          getChannel(), getRecordsByRecipientMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.dispensation.v1.QueryOuterClass.QueryClaimsResponse claimsByType(sifnode.dispensation.v1.QueryOuterClass.QueryClaimsByTypeRequest request) {
      return blockingUnaryCall(
          getChannel(), getClaimsByTypeMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsResponse> allDistributions(
        sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAllDistributionsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameResponse> recordsByDistributionName(
        sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRecordsByDistributionNameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrResponse> recordsByRecipient(
        sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRecordsByRecipientMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.dispensation.v1.QueryOuterClass.QueryClaimsResponse> claimsByType(
        sifnode.dispensation.v1.QueryOuterClass.QueryClaimsByTypeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getClaimsByTypeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ALL_DISTRIBUTIONS = 0;
  private static final int METHODID_RECORDS_BY_DISTRIBUTION_NAME = 1;
  private static final int METHODID_RECORDS_BY_RECIPIENT = 2;
  private static final int METHODID_CLAIMS_BY_TYPE = 3;

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
        case METHODID_ALL_DISTRIBUTIONS:
          serviceImpl.allDistributions((sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsRequest) request,
              (io.grpc.stub.StreamObserver<sifnode.dispensation.v1.QueryOuterClass.QueryAllDistributionsResponse>) responseObserver);
          break;
        case METHODID_RECORDS_BY_DISTRIBUTION_NAME:
          serviceImpl.recordsByDistributionName((sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameRequest) request,
              (io.grpc.stub.StreamObserver<sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByDistributionNameResponse>) responseObserver);
          break;
        case METHODID_RECORDS_BY_RECIPIENT:
          serviceImpl.recordsByRecipient((sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrRequest) request,
              (io.grpc.stub.StreamObserver<sifnode.dispensation.v1.QueryOuterClass.QueryRecordsByRecipientAddrResponse>) responseObserver);
          break;
        case METHODID_CLAIMS_BY_TYPE:
          serviceImpl.claimsByType((sifnode.dispensation.v1.QueryOuterClass.QueryClaimsByTypeRequest) request,
              (io.grpc.stub.StreamObserver<sifnode.dispensation.v1.QueryOuterClass.QueryClaimsResponse>) responseObserver);
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
      return sifnode.dispensation.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getAllDistributionsMethod())
              .addMethod(getRecordsByDistributionNameMethod())
              .addMethod(getRecordsByRecipientMethod())
              .addMethod(getClaimsByTypeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
