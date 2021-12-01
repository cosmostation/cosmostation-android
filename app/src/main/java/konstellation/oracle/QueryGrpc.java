package konstellation.oracle;

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
    comments = "Source: konstellation/oracle/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "konstellation.oracle.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<konstellation.oracle.QueryOuterClass.QueryAllAdminAddrRequest,
      konstellation.oracle.QueryOuterClass.QueryAllAdminAddrResponse> getAdminAddrAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AdminAddrAll",
      requestType = konstellation.oracle.QueryOuterClass.QueryAllAdminAddrRequest.class,
      responseType = konstellation.oracle.QueryOuterClass.QueryAllAdminAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<konstellation.oracle.QueryOuterClass.QueryAllAdminAddrRequest,
      konstellation.oracle.QueryOuterClass.QueryAllAdminAddrResponse> getAdminAddrAllMethod() {
    io.grpc.MethodDescriptor<konstellation.oracle.QueryOuterClass.QueryAllAdminAddrRequest, konstellation.oracle.QueryOuterClass.QueryAllAdminAddrResponse> getAdminAddrAllMethod;
    if ((getAdminAddrAllMethod = QueryGrpc.getAdminAddrAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAdminAddrAllMethod = QueryGrpc.getAdminAddrAllMethod) == null) {
          QueryGrpc.getAdminAddrAllMethod = getAdminAddrAllMethod =
              io.grpc.MethodDescriptor.<konstellation.oracle.QueryOuterClass.QueryAllAdminAddrRequest, konstellation.oracle.QueryOuterClass.QueryAllAdminAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AdminAddrAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  konstellation.oracle.QueryOuterClass.QueryAllAdminAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  konstellation.oracle.QueryOuterClass.QueryAllAdminAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AdminAddrAll"))
              .build();
        }
      }
    }
    return getAdminAddrAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<konstellation.oracle.QueryOuterClass.QueryExchangeRateRequest,
      konstellation.oracle.QueryOuterClass.QueryExchangeRateResponse> getExchangeRateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExchangeRate",
      requestType = konstellation.oracle.QueryOuterClass.QueryExchangeRateRequest.class,
      responseType = konstellation.oracle.QueryOuterClass.QueryExchangeRateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<konstellation.oracle.QueryOuterClass.QueryExchangeRateRequest,
      konstellation.oracle.QueryOuterClass.QueryExchangeRateResponse> getExchangeRateMethod() {
    io.grpc.MethodDescriptor<konstellation.oracle.QueryOuterClass.QueryExchangeRateRequest, konstellation.oracle.QueryOuterClass.QueryExchangeRateResponse> getExchangeRateMethod;
    if ((getExchangeRateMethod = QueryGrpc.getExchangeRateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getExchangeRateMethod = QueryGrpc.getExchangeRateMethod) == null) {
          QueryGrpc.getExchangeRateMethod = getExchangeRateMethod =
              io.grpc.MethodDescriptor.<konstellation.oracle.QueryOuterClass.QueryExchangeRateRequest, konstellation.oracle.QueryOuterClass.QueryExchangeRateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExchangeRate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  konstellation.oracle.QueryOuterClass.QueryExchangeRateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  konstellation.oracle.QueryOuterClass.QueryExchangeRateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ExchangeRate"))
              .build();
        }
      }
    }
    return getExchangeRateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesRequest,
      konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesResponse> getAllExchangeRatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllExchangeRates",
      requestType = konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesRequest.class,
      responseType = konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesRequest,
      konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesResponse> getAllExchangeRatesMethod() {
    io.grpc.MethodDescriptor<konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesRequest, konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesResponse> getAllExchangeRatesMethod;
    if ((getAllExchangeRatesMethod = QueryGrpc.getAllExchangeRatesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllExchangeRatesMethod = QueryGrpc.getAllExchangeRatesMethod) == null) {
          QueryGrpc.getAllExchangeRatesMethod = getAllExchangeRatesMethod =
              io.grpc.MethodDescriptor.<konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesRequest, konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllExchangeRates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllExchangeRates"))
              .build();
        }
      }
    }
    return getAllExchangeRatesMethod;
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
     *	// Queries a params by id.
     *	rpc Params(QueryGetParamsRequest) returns (QueryGetParamsResponse) {
     *		option (google.api.http).get = "/konstellation/konstellation/x/oracle/params/{id}";
     *	}
     *	// Queries a list of params items.
     *	rpc ParamsAll(QueryAllParamsRequest) returns (QueryAllParamsResponse) {
     *		option (google.api.http).get = "/konstellation/konstellation/x/oracle/params";
     *	}
     *	// Queries a adminAddr by id.
     *	rpc AdminAddr(QueryGetAdminAddrRequest) returns (QueryGetAdminAddrResponse) {
     *		option (google.api.http).get = "/konstellation/konstellation/x/oracle/adminAddr/{id}";
     *	}
     * Queries a list of adminAddr items.
     * </pre>
     */
    public void adminAddrAll(konstellation.oracle.QueryOuterClass.QueryAllAdminAddrRequest request,
        io.grpc.stub.StreamObserver<konstellation.oracle.QueryOuterClass.QueryAllAdminAddrResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAdminAddrAllMethod(), responseObserver);
    }

    /**
     */
    public void exchangeRate(konstellation.oracle.QueryOuterClass.QueryExchangeRateRequest request,
        io.grpc.stub.StreamObserver<konstellation.oracle.QueryOuterClass.QueryExchangeRateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getExchangeRateMethod(), responseObserver);
    }

    /**
     */
    public void allExchangeRates(konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesRequest request,
        io.grpc.stub.StreamObserver<konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAllExchangeRatesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAdminAddrAllMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                konstellation.oracle.QueryOuterClass.QueryAllAdminAddrRequest,
                konstellation.oracle.QueryOuterClass.QueryAllAdminAddrResponse>(
                  this, METHODID_ADMIN_ADDR_ALL)))
          .addMethod(
            getExchangeRateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                konstellation.oracle.QueryOuterClass.QueryExchangeRateRequest,
                konstellation.oracle.QueryOuterClass.QueryExchangeRateResponse>(
                  this, METHODID_EXCHANGE_RATE)))
          .addMethod(
            getAllExchangeRatesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesRequest,
                konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesResponse>(
                  this, METHODID_ALL_EXCHANGE_RATES)))
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
     *	// Queries a params by id.
     *	rpc Params(QueryGetParamsRequest) returns (QueryGetParamsResponse) {
     *		option (google.api.http).get = "/konstellation/konstellation/x/oracle/params/{id}";
     *	}
     *	// Queries a list of params items.
     *	rpc ParamsAll(QueryAllParamsRequest) returns (QueryAllParamsResponse) {
     *		option (google.api.http).get = "/konstellation/konstellation/x/oracle/params";
     *	}
     *	// Queries a adminAddr by id.
     *	rpc AdminAddr(QueryGetAdminAddrRequest) returns (QueryGetAdminAddrResponse) {
     *		option (google.api.http).get = "/konstellation/konstellation/x/oracle/adminAddr/{id}";
     *	}
     * Queries a list of adminAddr items.
     * </pre>
     */
    public void adminAddrAll(konstellation.oracle.QueryOuterClass.QueryAllAdminAddrRequest request,
        io.grpc.stub.StreamObserver<konstellation.oracle.QueryOuterClass.QueryAllAdminAddrResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAdminAddrAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void exchangeRate(konstellation.oracle.QueryOuterClass.QueryExchangeRateRequest request,
        io.grpc.stub.StreamObserver<konstellation.oracle.QueryOuterClass.QueryExchangeRateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExchangeRateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void allExchangeRates(konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesRequest request,
        io.grpc.stub.StreamObserver<konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAllExchangeRatesMethod(), getCallOptions()), request, responseObserver);
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
     *	// Queries a params by id.
     *	rpc Params(QueryGetParamsRequest) returns (QueryGetParamsResponse) {
     *		option (google.api.http).get = "/konstellation/konstellation/x/oracle/params/{id}";
     *	}
     *	// Queries a list of params items.
     *	rpc ParamsAll(QueryAllParamsRequest) returns (QueryAllParamsResponse) {
     *		option (google.api.http).get = "/konstellation/konstellation/x/oracle/params";
     *	}
     *	// Queries a adminAddr by id.
     *	rpc AdminAddr(QueryGetAdminAddrRequest) returns (QueryGetAdminAddrResponse) {
     *		option (google.api.http).get = "/konstellation/konstellation/x/oracle/adminAddr/{id}";
     *	}
     * Queries a list of adminAddr items.
     * </pre>
     */
    public konstellation.oracle.QueryOuterClass.QueryAllAdminAddrResponse adminAddrAll(konstellation.oracle.QueryOuterClass.QueryAllAdminAddrRequest request) {
      return blockingUnaryCall(
          getChannel(), getAdminAddrAllMethod(), getCallOptions(), request);
    }

    /**
     */
    public konstellation.oracle.QueryOuterClass.QueryExchangeRateResponse exchangeRate(konstellation.oracle.QueryOuterClass.QueryExchangeRateRequest request) {
      return blockingUnaryCall(
          getChannel(), getExchangeRateMethod(), getCallOptions(), request);
    }

    /**
     */
    public konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesResponse allExchangeRates(konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesRequest request) {
      return blockingUnaryCall(
          getChannel(), getAllExchangeRatesMethod(), getCallOptions(), request);
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
     *	// Queries a params by id.
     *	rpc Params(QueryGetParamsRequest) returns (QueryGetParamsResponse) {
     *		option (google.api.http).get = "/konstellation/konstellation/x/oracle/params/{id}";
     *	}
     *	// Queries a list of params items.
     *	rpc ParamsAll(QueryAllParamsRequest) returns (QueryAllParamsResponse) {
     *		option (google.api.http).get = "/konstellation/konstellation/x/oracle/params";
     *	}
     *	// Queries a adminAddr by id.
     *	rpc AdminAddr(QueryGetAdminAddrRequest) returns (QueryGetAdminAddrResponse) {
     *		option (google.api.http).get = "/konstellation/konstellation/x/oracle/adminAddr/{id}";
     *	}
     * Queries a list of adminAddr items.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<konstellation.oracle.QueryOuterClass.QueryAllAdminAddrResponse> adminAddrAll(
        konstellation.oracle.QueryOuterClass.QueryAllAdminAddrRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAdminAddrAllMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<konstellation.oracle.QueryOuterClass.QueryExchangeRateResponse> exchangeRate(
        konstellation.oracle.QueryOuterClass.QueryExchangeRateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getExchangeRateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesResponse> allExchangeRates(
        konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAllExchangeRatesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADMIN_ADDR_ALL = 0;
  private static final int METHODID_EXCHANGE_RATE = 1;
  private static final int METHODID_ALL_EXCHANGE_RATES = 2;

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
        case METHODID_ADMIN_ADDR_ALL:
          serviceImpl.adminAddrAll((konstellation.oracle.QueryOuterClass.QueryAllAdminAddrRequest) request,
              (io.grpc.stub.StreamObserver<konstellation.oracle.QueryOuterClass.QueryAllAdminAddrResponse>) responseObserver);
          break;
        case METHODID_EXCHANGE_RATE:
          serviceImpl.exchangeRate((konstellation.oracle.QueryOuterClass.QueryExchangeRateRequest) request,
              (io.grpc.stub.StreamObserver<konstellation.oracle.QueryOuterClass.QueryExchangeRateResponse>) responseObserver);
          break;
        case METHODID_ALL_EXCHANGE_RATES:
          serviceImpl.allExchangeRates((konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesRequest) request,
              (io.grpc.stub.StreamObserver<konstellation.oracle.QueryOuterClass.QueryAllExchangeRatesResponse>) responseObserver);
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
      return konstellation.oracle.QueryOuterClass.getDescriptor();
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
              .addMethod(getAdminAddrAllMethod())
              .addMethod(getExchangeRateMethod())
              .addMethod(getAllExchangeRatesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
