package kava.cdp.v1beta1;

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
 * Query defines the gRPC querier service for cdp module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/cdp/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "kava.cdp.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryParamsRequest,
      kava.cdp.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = kava.cdp.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = kava.cdp.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryParamsRequest,
      kava.cdp.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryParamsRequest, kava.cdp.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<kava.cdp.v1beta1.QueryOuterClass.QueryParamsRequest, kava.cdp.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryAccountsRequest,
      kava.cdp.v1beta1.QueryOuterClass.QueryAccountsResponse> getAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Accounts",
      requestType = kava.cdp.v1beta1.QueryOuterClass.QueryAccountsRequest.class,
      responseType = kava.cdp.v1beta1.QueryOuterClass.QueryAccountsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryAccountsRequest,
      kava.cdp.v1beta1.QueryOuterClass.QueryAccountsResponse> getAccountsMethod() {
    io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryAccountsRequest, kava.cdp.v1beta1.QueryOuterClass.QueryAccountsResponse> getAccountsMethod;
    if ((getAccountsMethod = QueryGrpc.getAccountsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountsMethod = QueryGrpc.getAccountsMethod) == null) {
          QueryGrpc.getAccountsMethod = getAccountsMethod =
              io.grpc.MethodDescriptor.<kava.cdp.v1beta1.QueryOuterClass.QueryAccountsRequest, kava.cdp.v1beta1.QueryOuterClass.QueryAccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Accounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.QueryOuterClass.QueryAccountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.QueryOuterClass.QueryAccountsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Accounts"))
              .build();
        }
      }
    }
    return getAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalRequest,
      kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalResponse> getTotalPrincipalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalPrincipal",
      requestType = kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalRequest.class,
      responseType = kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalRequest,
      kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalResponse> getTotalPrincipalMethod() {
    io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalRequest, kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalResponse> getTotalPrincipalMethod;
    if ((getTotalPrincipalMethod = QueryGrpc.getTotalPrincipalMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalPrincipalMethod = QueryGrpc.getTotalPrincipalMethod) == null) {
          QueryGrpc.getTotalPrincipalMethod = getTotalPrincipalMethod =
              io.grpc.MethodDescriptor.<kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalRequest, kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalPrincipal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalPrincipal"))
              .build();
        }
      }
    }
    return getTotalPrincipalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralRequest,
      kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralResponse> getTotalCollateralMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalCollateral",
      requestType = kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralRequest.class,
      responseType = kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralRequest,
      kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralResponse> getTotalCollateralMethod() {
    io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralRequest, kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralResponse> getTotalCollateralMethod;
    if ((getTotalCollateralMethod = QueryGrpc.getTotalCollateralMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalCollateralMethod = QueryGrpc.getTotalCollateralMethod) == null) {
          QueryGrpc.getTotalCollateralMethod = getTotalCollateralMethod =
              io.grpc.MethodDescriptor.<kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralRequest, kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalCollateral"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalCollateral"))
              .build();
        }
      }
    }
    return getTotalCollateralMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryCdpsRequest,
      kava.cdp.v1beta1.QueryOuterClass.QueryCdpsResponse> getCdpsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Cdps",
      requestType = kava.cdp.v1beta1.QueryOuterClass.QueryCdpsRequest.class,
      responseType = kava.cdp.v1beta1.QueryOuterClass.QueryCdpsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryCdpsRequest,
      kava.cdp.v1beta1.QueryOuterClass.QueryCdpsResponse> getCdpsMethod() {
    io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryCdpsRequest, kava.cdp.v1beta1.QueryOuterClass.QueryCdpsResponse> getCdpsMethod;
    if ((getCdpsMethod = QueryGrpc.getCdpsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCdpsMethod = QueryGrpc.getCdpsMethod) == null) {
          QueryGrpc.getCdpsMethod = getCdpsMethod =
              io.grpc.MethodDescriptor.<kava.cdp.v1beta1.QueryOuterClass.QueryCdpsRequest, kava.cdp.v1beta1.QueryOuterClass.QueryCdpsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Cdps"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.QueryOuterClass.QueryCdpsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.QueryOuterClass.QueryCdpsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Cdps"))
              .build();
        }
      }
    }
    return getCdpsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryCdpRequest,
      kava.cdp.v1beta1.QueryOuterClass.QueryCdpResponse> getCdpMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Cdp",
      requestType = kava.cdp.v1beta1.QueryOuterClass.QueryCdpRequest.class,
      responseType = kava.cdp.v1beta1.QueryOuterClass.QueryCdpResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryCdpRequest,
      kava.cdp.v1beta1.QueryOuterClass.QueryCdpResponse> getCdpMethod() {
    io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryCdpRequest, kava.cdp.v1beta1.QueryOuterClass.QueryCdpResponse> getCdpMethod;
    if ((getCdpMethod = QueryGrpc.getCdpMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCdpMethod = QueryGrpc.getCdpMethod) == null) {
          QueryGrpc.getCdpMethod = getCdpMethod =
              io.grpc.MethodDescriptor.<kava.cdp.v1beta1.QueryOuterClass.QueryCdpRequest, kava.cdp.v1beta1.QueryOuterClass.QueryCdpResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Cdp"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.QueryOuterClass.QueryCdpRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.QueryOuterClass.QueryCdpResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Cdp"))
              .build();
        }
      }
    }
    return getCdpMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryDepositsRequest,
      kava.cdp.v1beta1.QueryOuterClass.QueryDepositsResponse> getDepositsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposits",
      requestType = kava.cdp.v1beta1.QueryOuterClass.QueryDepositsRequest.class,
      responseType = kava.cdp.v1beta1.QueryOuterClass.QueryDepositsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryDepositsRequest,
      kava.cdp.v1beta1.QueryOuterClass.QueryDepositsResponse> getDepositsMethod() {
    io.grpc.MethodDescriptor<kava.cdp.v1beta1.QueryOuterClass.QueryDepositsRequest, kava.cdp.v1beta1.QueryOuterClass.QueryDepositsResponse> getDepositsMethod;
    if ((getDepositsMethod = QueryGrpc.getDepositsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositsMethod = QueryGrpc.getDepositsMethod) == null) {
          QueryGrpc.getDepositsMethod = getDepositsMethod =
              io.grpc.MethodDescriptor.<kava.cdp.v1beta1.QueryOuterClass.QueryDepositsRequest, kava.cdp.v1beta1.QueryOuterClass.QueryDepositsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposits"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.QueryOuterClass.QueryDepositsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.cdp.v1beta1.QueryOuterClass.QueryDepositsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Deposits"))
              .build();
        }
      }
    }
    return getDepositsMethod;
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
   * Query defines the gRPC querier service for cdp module
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Params queries all parameters of the cdp module.
     * </pre>
     */
    public void params(kava.cdp.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Accounts queries the CDP module accounts.
     * </pre>
     */
    public void accounts(kava.cdp.v1beta1.QueryOuterClass.QueryAccountsRequest request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryAccountsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAccountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalPrincipal queries the total principal of a given collateral type.
     * </pre>
     */
    public void totalPrincipal(kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalRequest request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTotalPrincipalMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalCollateral queries the total collateral of a given collateral type.
     * </pre>
     */
    public void totalCollateral(kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralRequest request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTotalCollateralMethod(), responseObserver);
    }

    /**
     * <pre>
     * Cdps queries all active CDPs.
     * </pre>
     */
    public void cdps(kava.cdp.v1beta1.QueryOuterClass.QueryCdpsRequest request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryCdpsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCdpsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Cdp queries a CDP with the input owner address and collateral type.
     * </pre>
     */
    public void cdp(kava.cdp.v1beta1.QueryOuterClass.QueryCdpRequest request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryCdpResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCdpMethod(), responseObserver);
    }

    /**
     * <pre>
     * Deposits queries deposits associated with the CDP owned by an address for a collateral type.
     * </pre>
     */
    public void deposits(kava.cdp.v1beta1.QueryOuterClass.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryDepositsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.cdp.v1beta1.QueryOuterClass.QueryParamsRequest,
                kava.cdp.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getAccountsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.cdp.v1beta1.QueryOuterClass.QueryAccountsRequest,
                kava.cdp.v1beta1.QueryOuterClass.QueryAccountsResponse>(
                  this, METHODID_ACCOUNTS)))
          .addMethod(
            getTotalPrincipalMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalRequest,
                kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalResponse>(
                  this, METHODID_TOTAL_PRINCIPAL)))
          .addMethod(
            getTotalCollateralMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralRequest,
                kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralResponse>(
                  this, METHODID_TOTAL_COLLATERAL)))
          .addMethod(
            getCdpsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.cdp.v1beta1.QueryOuterClass.QueryCdpsRequest,
                kava.cdp.v1beta1.QueryOuterClass.QueryCdpsResponse>(
                  this, METHODID_CDPS)))
          .addMethod(
            getCdpMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.cdp.v1beta1.QueryOuterClass.QueryCdpRequest,
                kava.cdp.v1beta1.QueryOuterClass.QueryCdpResponse>(
                  this, METHODID_CDP)))
          .addMethod(
            getDepositsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.cdp.v1beta1.QueryOuterClass.QueryDepositsRequest,
                kava.cdp.v1beta1.QueryOuterClass.QueryDepositsResponse>(
                  this, METHODID_DEPOSITS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for cdp module
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
     * Params queries all parameters of the cdp module.
     * </pre>
     */
    public void params(kava.cdp.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Accounts queries the CDP module accounts.
     * </pre>
     */
    public void accounts(kava.cdp.v1beta1.QueryOuterClass.QueryAccountsRequest request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryAccountsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalPrincipal queries the total principal of a given collateral type.
     * </pre>
     */
    public void totalPrincipal(kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalRequest request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTotalPrincipalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalCollateral queries the total collateral of a given collateral type.
     * </pre>
     */
    public void totalCollateral(kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralRequest request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTotalCollateralMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Cdps queries all active CDPs.
     * </pre>
     */
    public void cdps(kava.cdp.v1beta1.QueryOuterClass.QueryCdpsRequest request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryCdpsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCdpsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Cdp queries a CDP with the input owner address and collateral type.
     * </pre>
     */
    public void cdp(kava.cdp.v1beta1.QueryOuterClass.QueryCdpRequest request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryCdpResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCdpMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Deposits queries deposits associated with the CDP owned by an address for a collateral type.
     * </pre>
     */
    public void deposits(kava.cdp.v1beta1.QueryOuterClass.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryDepositsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for cdp module
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
     * Params queries all parameters of the cdp module.
     * </pre>
     */
    public kava.cdp.v1beta1.QueryOuterClass.QueryParamsResponse params(kava.cdp.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Accounts queries the CDP module accounts.
     * </pre>
     */
    public kava.cdp.v1beta1.QueryOuterClass.QueryAccountsResponse accounts(kava.cdp.v1beta1.QueryOuterClass.QueryAccountsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalPrincipal queries the total principal of a given collateral type.
     * </pre>
     */
    public kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalResponse totalPrincipal(kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalRequest request) {
      return blockingUnaryCall(
          getChannel(), getTotalPrincipalMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalCollateral queries the total collateral of a given collateral type.
     * </pre>
     */
    public kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralResponse totalCollateral(kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralRequest request) {
      return blockingUnaryCall(
          getChannel(), getTotalCollateralMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Cdps queries all active CDPs.
     * </pre>
     */
    public kava.cdp.v1beta1.QueryOuterClass.QueryCdpsResponse cdps(kava.cdp.v1beta1.QueryOuterClass.QueryCdpsRequest request) {
      return blockingUnaryCall(
          getChannel(), getCdpsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Cdp queries a CDP with the input owner address and collateral type.
     * </pre>
     */
    public kava.cdp.v1beta1.QueryOuterClass.QueryCdpResponse cdp(kava.cdp.v1beta1.QueryOuterClass.QueryCdpRequest request) {
      return blockingUnaryCall(
          getChannel(), getCdpMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Deposits queries deposits associated with the CDP owned by an address for a collateral type.
     * </pre>
     */
    public kava.cdp.v1beta1.QueryOuterClass.QueryDepositsResponse deposits(kava.cdp.v1beta1.QueryOuterClass.QueryDepositsRequest request) {
      return blockingUnaryCall(
          getChannel(), getDepositsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for cdp module
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
     * Params queries all parameters of the cdp module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.cdp.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        kava.cdp.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Accounts queries the CDP module accounts.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.cdp.v1beta1.QueryOuterClass.QueryAccountsResponse> accounts(
        kava.cdp.v1beta1.QueryOuterClass.QueryAccountsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalPrincipal queries the total principal of a given collateral type.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalResponse> totalPrincipal(
        kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTotalPrincipalMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalCollateral queries the total collateral of a given collateral type.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralResponse> totalCollateral(
        kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTotalCollateralMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Cdps queries all active CDPs.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.cdp.v1beta1.QueryOuterClass.QueryCdpsResponse> cdps(
        kava.cdp.v1beta1.QueryOuterClass.QueryCdpsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCdpsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Cdp queries a CDP with the input owner address and collateral type.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.cdp.v1beta1.QueryOuterClass.QueryCdpResponse> cdp(
        kava.cdp.v1beta1.QueryOuterClass.QueryCdpRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCdpMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Deposits queries deposits associated with the CDP owned by an address for a collateral type.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.cdp.v1beta1.QueryOuterClass.QueryDepositsResponse> deposits(
        kava.cdp.v1beta1.QueryOuterClass.QueryDepositsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_ACCOUNTS = 1;
  private static final int METHODID_TOTAL_PRINCIPAL = 2;
  private static final int METHODID_TOTAL_COLLATERAL = 3;
  private static final int METHODID_CDPS = 4;
  private static final int METHODID_CDP = 5;
  private static final int METHODID_DEPOSITS = 6;

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
        case METHODID_PARAMS:
          serviceImpl.params((kava.cdp.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_ACCOUNTS:
          serviceImpl.accounts((kava.cdp.v1beta1.QueryOuterClass.QueryAccountsRequest) request,
              (io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryAccountsResponse>) responseObserver);
          break;
        case METHODID_TOTAL_PRINCIPAL:
          serviceImpl.totalPrincipal((kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalRequest) request,
              (io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryTotalPrincipalResponse>) responseObserver);
          break;
        case METHODID_TOTAL_COLLATERAL:
          serviceImpl.totalCollateral((kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralRequest) request,
              (io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryTotalCollateralResponse>) responseObserver);
          break;
        case METHODID_CDPS:
          serviceImpl.cdps((kava.cdp.v1beta1.QueryOuterClass.QueryCdpsRequest) request,
              (io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryCdpsResponse>) responseObserver);
          break;
        case METHODID_CDP:
          serviceImpl.cdp((kava.cdp.v1beta1.QueryOuterClass.QueryCdpRequest) request,
              (io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryCdpResponse>) responseObserver);
          break;
        case METHODID_DEPOSITS:
          serviceImpl.deposits((kava.cdp.v1beta1.QueryOuterClass.QueryDepositsRequest) request,
              (io.grpc.stub.StreamObserver<kava.cdp.v1beta1.QueryOuterClass.QueryDepositsResponse>) responseObserver);
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
      return kava.cdp.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getParamsMethod())
              .addMethod(getAccountsMethod())
              .addMethod(getTotalPrincipalMethod())
              .addMethod(getTotalCollateralMethod())
              .addMethod(getCdpsMethod())
              .addMethod(getCdpMethod())
              .addMethod(getDepositsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
