package kava.hard.v1beta1;

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
 * Query defines the gRPC querier service for bep3 module.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/hard/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "kava.hard.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryParamsRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = kava.hard.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = kava.hard.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryParamsRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryParamsRequest, kava.hard.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<kava.hard.v1beta1.QueryOuterClass.QueryParamsRequest, kava.hard.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryAccountsRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryAccountsResponse> getAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Accounts",
      requestType = kava.hard.v1beta1.QueryOuterClass.QueryAccountsRequest.class,
      responseType = kava.hard.v1beta1.QueryOuterClass.QueryAccountsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryAccountsRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryAccountsResponse> getAccountsMethod() {
    io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryAccountsRequest, kava.hard.v1beta1.QueryOuterClass.QueryAccountsResponse> getAccountsMethod;
    if ((getAccountsMethod = QueryGrpc.getAccountsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountsMethod = QueryGrpc.getAccountsMethod) == null) {
          QueryGrpc.getAccountsMethod = getAccountsMethod =
              io.grpc.MethodDescriptor.<kava.hard.v1beta1.QueryOuterClass.QueryAccountsRequest, kava.hard.v1beta1.QueryOuterClass.QueryAccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Accounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryAccountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryAccountsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Accounts"))
              .build();
        }
      }
    }
    return getAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryDepositsRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryDepositsResponse> getDepositsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposits",
      requestType = kava.hard.v1beta1.QueryOuterClass.QueryDepositsRequest.class,
      responseType = kava.hard.v1beta1.QueryOuterClass.QueryDepositsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryDepositsRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryDepositsResponse> getDepositsMethod() {
    io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryDepositsRequest, kava.hard.v1beta1.QueryOuterClass.QueryDepositsResponse> getDepositsMethod;
    if ((getDepositsMethod = QueryGrpc.getDepositsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositsMethod = QueryGrpc.getDepositsMethod) == null) {
          QueryGrpc.getDepositsMethod = getDepositsMethod =
              io.grpc.MethodDescriptor.<kava.hard.v1beta1.QueryOuterClass.QueryDepositsRequest, kava.hard.v1beta1.QueryOuterClass.QueryDepositsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposits"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryDepositsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryDepositsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Deposits"))
              .build();
        }
      }
    }
    return getDepositsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsResponse> getUnsyncedDepositsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnsyncedDeposits",
      requestType = kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsRequest.class,
      responseType = kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsResponse> getUnsyncedDepositsMethod() {
    io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsRequest, kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsResponse> getUnsyncedDepositsMethod;
    if ((getUnsyncedDepositsMethod = QueryGrpc.getUnsyncedDepositsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnsyncedDepositsMethod = QueryGrpc.getUnsyncedDepositsMethod) == null) {
          QueryGrpc.getUnsyncedDepositsMethod = getUnsyncedDepositsMethod =
              io.grpc.MethodDescriptor.<kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsRequest, kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnsyncedDeposits"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UnsyncedDeposits"))
              .build();
        }
      }
    }
    return getUnsyncedDepositsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedResponse> getTotalDepositedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalDeposited",
      requestType = kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedRequest.class,
      responseType = kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedResponse> getTotalDepositedMethod() {
    io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedRequest, kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedResponse> getTotalDepositedMethod;
    if ((getTotalDepositedMethod = QueryGrpc.getTotalDepositedMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalDepositedMethod = QueryGrpc.getTotalDepositedMethod) == null) {
          QueryGrpc.getTotalDepositedMethod = getTotalDepositedMethod =
              io.grpc.MethodDescriptor.<kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedRequest, kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalDeposited"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalDeposited"))
              .build();
        }
      }
    }
    return getTotalDepositedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryBorrowsRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryBorrowsResponse> getBorrowsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Borrows",
      requestType = kava.hard.v1beta1.QueryOuterClass.QueryBorrowsRequest.class,
      responseType = kava.hard.v1beta1.QueryOuterClass.QueryBorrowsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryBorrowsRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryBorrowsResponse> getBorrowsMethod() {
    io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryBorrowsRequest, kava.hard.v1beta1.QueryOuterClass.QueryBorrowsResponse> getBorrowsMethod;
    if ((getBorrowsMethod = QueryGrpc.getBorrowsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBorrowsMethod = QueryGrpc.getBorrowsMethod) == null) {
          QueryGrpc.getBorrowsMethod = getBorrowsMethod =
              io.grpc.MethodDescriptor.<kava.hard.v1beta1.QueryOuterClass.QueryBorrowsRequest, kava.hard.v1beta1.QueryOuterClass.QueryBorrowsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Borrows"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryBorrowsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryBorrowsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Borrows"))
              .build();
        }
      }
    }
    return getBorrowsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsResponse> getUnsyncedBorrowsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnsyncedBorrows",
      requestType = kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsRequest.class,
      responseType = kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsResponse> getUnsyncedBorrowsMethod() {
    io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsRequest, kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsResponse> getUnsyncedBorrowsMethod;
    if ((getUnsyncedBorrowsMethod = QueryGrpc.getUnsyncedBorrowsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnsyncedBorrowsMethod = QueryGrpc.getUnsyncedBorrowsMethod) == null) {
          QueryGrpc.getUnsyncedBorrowsMethod = getUnsyncedBorrowsMethod =
              io.grpc.MethodDescriptor.<kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsRequest, kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnsyncedBorrows"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UnsyncedBorrows"))
              .build();
        }
      }
    }
    return getUnsyncedBorrowsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedResponse> getTotalBorrowedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalBorrowed",
      requestType = kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedRequest.class,
      responseType = kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedResponse> getTotalBorrowedMethod() {
    io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedRequest, kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedResponse> getTotalBorrowedMethod;
    if ((getTotalBorrowedMethod = QueryGrpc.getTotalBorrowedMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalBorrowedMethod = QueryGrpc.getTotalBorrowedMethod) == null) {
          QueryGrpc.getTotalBorrowedMethod = getTotalBorrowedMethod =
              io.grpc.MethodDescriptor.<kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedRequest, kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalBorrowed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalBorrowed"))
              .build();
        }
      }
    }
    return getTotalBorrowedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryInterestRateRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryInterestRateResponse> getInterestRateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InterestRate",
      requestType = kava.hard.v1beta1.QueryOuterClass.QueryInterestRateRequest.class,
      responseType = kava.hard.v1beta1.QueryOuterClass.QueryInterestRateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryInterestRateRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryInterestRateResponse> getInterestRateMethod() {
    io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryInterestRateRequest, kava.hard.v1beta1.QueryOuterClass.QueryInterestRateResponse> getInterestRateMethod;
    if ((getInterestRateMethod = QueryGrpc.getInterestRateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getInterestRateMethod = QueryGrpc.getInterestRateMethod) == null) {
          QueryGrpc.getInterestRateMethod = getInterestRateMethod =
              io.grpc.MethodDescriptor.<kava.hard.v1beta1.QueryOuterClass.QueryInterestRateRequest, kava.hard.v1beta1.QueryOuterClass.QueryInterestRateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InterestRate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryInterestRateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryInterestRateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("InterestRate"))
              .build();
        }
      }
    }
    return getInterestRateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryReservesRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryReservesResponse> getReservesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Reserves",
      requestType = kava.hard.v1beta1.QueryOuterClass.QueryReservesRequest.class,
      responseType = kava.hard.v1beta1.QueryOuterClass.QueryReservesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryReservesRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryReservesResponse> getReservesMethod() {
    io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryReservesRequest, kava.hard.v1beta1.QueryOuterClass.QueryReservesResponse> getReservesMethod;
    if ((getReservesMethod = QueryGrpc.getReservesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getReservesMethod = QueryGrpc.getReservesMethod) == null) {
          QueryGrpc.getReservesMethod = getReservesMethod =
              io.grpc.MethodDescriptor.<kava.hard.v1beta1.QueryOuterClass.QueryReservesRequest, kava.hard.v1beta1.QueryOuterClass.QueryReservesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Reserves"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryReservesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryReservesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Reserves"))
              .build();
        }
      }
    }
    return getReservesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsResponse> getInterestFactorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InterestFactors",
      requestType = kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsRequest.class,
      responseType = kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsRequest,
      kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsResponse> getInterestFactorsMethod() {
    io.grpc.MethodDescriptor<kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsRequest, kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsResponse> getInterestFactorsMethod;
    if ((getInterestFactorsMethod = QueryGrpc.getInterestFactorsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getInterestFactorsMethod = QueryGrpc.getInterestFactorsMethod) == null) {
          QueryGrpc.getInterestFactorsMethod = getInterestFactorsMethod =
              io.grpc.MethodDescriptor.<kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsRequest, kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InterestFactors"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("InterestFactors"))
              .build();
        }
      }
    }
    return getInterestFactorsMethod;
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
   * Query defines the gRPC querier service for bep3 module.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Params queries module params.
     * </pre>
     */
    public void params(kava.hard.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Accounts queries module accounts.
     * </pre>
     */
    public void accounts(kava.hard.v1beta1.QueryOuterClass.QueryAccountsRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryAccountsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAccountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Deposits queries hard deposits.
     * </pre>
     */
    public void deposits(kava.hard.v1beta1.QueryOuterClass.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryDepositsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositsMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnsyncedDeposits queries unsynced deposits.
     * </pre>
     */
    public void unsyncedDeposits(kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnsyncedDepositsMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalDeposited queries total coins deposited to hard liquidity pools.
     * </pre>
     */
    public void totalDeposited(kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTotalDepositedMethod(), responseObserver);
    }

    /**
     * <pre>
     * Borrows queries hard borrows.
     * </pre>
     */
    public void borrows(kava.hard.v1beta1.QueryOuterClass.QueryBorrowsRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryBorrowsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBorrowsMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnsyncedBorrows queries unsynced borrows.
     * </pre>
     */
    public void unsyncedBorrows(kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnsyncedBorrowsMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalBorrowed queries total coins borrowed from hard liquidity pools.
     * </pre>
     */
    public void totalBorrowed(kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTotalBorrowedMethod(), responseObserver);
    }

    /**
     * <pre>
     * InterestRate queries the hard module interest rates.
     * </pre>
     */
    public void interestRate(kava.hard.v1beta1.QueryOuterClass.QueryInterestRateRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryInterestRateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInterestRateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Reserves queries total hard reserve coins.
     * </pre>
     */
    public void reserves(kava.hard.v1beta1.QueryOuterClass.QueryReservesRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryReservesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReservesMethod(), responseObserver);
    }

    /**
     * <pre>
     * InterestFactors queries hard module interest factors.
     * </pre>
     */
    public void interestFactors(kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInterestFactorsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.hard.v1beta1.QueryOuterClass.QueryParamsRequest,
                kava.hard.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getAccountsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.hard.v1beta1.QueryOuterClass.QueryAccountsRequest,
                kava.hard.v1beta1.QueryOuterClass.QueryAccountsResponse>(
                  this, METHODID_ACCOUNTS)))
          .addMethod(
            getDepositsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.hard.v1beta1.QueryOuterClass.QueryDepositsRequest,
                kava.hard.v1beta1.QueryOuterClass.QueryDepositsResponse>(
                  this, METHODID_DEPOSITS)))
          .addMethod(
            getUnsyncedDepositsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsRequest,
                kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsResponse>(
                  this, METHODID_UNSYNCED_DEPOSITS)))
          .addMethod(
            getTotalDepositedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedRequest,
                kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedResponse>(
                  this, METHODID_TOTAL_DEPOSITED)))
          .addMethod(
            getBorrowsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.hard.v1beta1.QueryOuterClass.QueryBorrowsRequest,
                kava.hard.v1beta1.QueryOuterClass.QueryBorrowsResponse>(
                  this, METHODID_BORROWS)))
          .addMethod(
            getUnsyncedBorrowsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsRequest,
                kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsResponse>(
                  this, METHODID_UNSYNCED_BORROWS)))
          .addMethod(
            getTotalBorrowedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedRequest,
                kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedResponse>(
                  this, METHODID_TOTAL_BORROWED)))
          .addMethod(
            getInterestRateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.hard.v1beta1.QueryOuterClass.QueryInterestRateRequest,
                kava.hard.v1beta1.QueryOuterClass.QueryInterestRateResponse>(
                  this, METHODID_INTEREST_RATE)))
          .addMethod(
            getReservesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.hard.v1beta1.QueryOuterClass.QueryReservesRequest,
                kava.hard.v1beta1.QueryOuterClass.QueryReservesResponse>(
                  this, METHODID_RESERVES)))
          .addMethod(
            getInterestFactorsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsRequest,
                kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsResponse>(
                  this, METHODID_INTEREST_FACTORS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for bep3 module.
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
     * Params queries module params.
     * </pre>
     */
    public void params(kava.hard.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Accounts queries module accounts.
     * </pre>
     */
    public void accounts(kava.hard.v1beta1.QueryOuterClass.QueryAccountsRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryAccountsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Deposits queries hard deposits.
     * </pre>
     */
    public void deposits(kava.hard.v1beta1.QueryOuterClass.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryDepositsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnsyncedDeposits queries unsynced deposits.
     * </pre>
     */
    public void unsyncedDeposits(kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnsyncedDepositsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalDeposited queries total coins deposited to hard liquidity pools.
     * </pre>
     */
    public void totalDeposited(kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTotalDepositedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Borrows queries hard borrows.
     * </pre>
     */
    public void borrows(kava.hard.v1beta1.QueryOuterClass.QueryBorrowsRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryBorrowsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBorrowsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnsyncedBorrows queries unsynced borrows.
     * </pre>
     */
    public void unsyncedBorrows(kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnsyncedBorrowsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalBorrowed queries total coins borrowed from hard liquidity pools.
     * </pre>
     */
    public void totalBorrowed(kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTotalBorrowedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * InterestRate queries the hard module interest rates.
     * </pre>
     */
    public void interestRate(kava.hard.v1beta1.QueryOuterClass.QueryInterestRateRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryInterestRateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInterestRateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Reserves queries total hard reserve coins.
     * </pre>
     */
    public void reserves(kava.hard.v1beta1.QueryOuterClass.QueryReservesRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryReservesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReservesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * InterestFactors queries hard module interest factors.
     * </pre>
     */
    public void interestFactors(kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsRequest request,
        io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInterestFactorsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for bep3 module.
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
     * Params queries module params.
     * </pre>
     */
    public kava.hard.v1beta1.QueryOuterClass.QueryParamsResponse params(kava.hard.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Accounts queries module accounts.
     * </pre>
     */
    public kava.hard.v1beta1.QueryOuterClass.QueryAccountsResponse accounts(kava.hard.v1beta1.QueryOuterClass.QueryAccountsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Deposits queries hard deposits.
     * </pre>
     */
    public kava.hard.v1beta1.QueryOuterClass.QueryDepositsResponse deposits(kava.hard.v1beta1.QueryOuterClass.QueryDepositsRequest request) {
      return blockingUnaryCall(
          getChannel(), getDepositsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnsyncedDeposits queries unsynced deposits.
     * </pre>
     */
    public kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsResponse unsyncedDeposits(kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnsyncedDepositsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalDeposited queries total coins deposited to hard liquidity pools.
     * </pre>
     */
    public kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedResponse totalDeposited(kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedRequest request) {
      return blockingUnaryCall(
          getChannel(), getTotalDepositedMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Borrows queries hard borrows.
     * </pre>
     */
    public kava.hard.v1beta1.QueryOuterClass.QueryBorrowsResponse borrows(kava.hard.v1beta1.QueryOuterClass.QueryBorrowsRequest request) {
      return blockingUnaryCall(
          getChannel(), getBorrowsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnsyncedBorrows queries unsynced borrows.
     * </pre>
     */
    public kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsResponse unsyncedBorrows(kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnsyncedBorrowsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalBorrowed queries total coins borrowed from hard liquidity pools.
     * </pre>
     */
    public kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedResponse totalBorrowed(kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedRequest request) {
      return blockingUnaryCall(
          getChannel(), getTotalBorrowedMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * InterestRate queries the hard module interest rates.
     * </pre>
     */
    public kava.hard.v1beta1.QueryOuterClass.QueryInterestRateResponse interestRate(kava.hard.v1beta1.QueryOuterClass.QueryInterestRateRequest request) {
      return blockingUnaryCall(
          getChannel(), getInterestRateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Reserves queries total hard reserve coins.
     * </pre>
     */
    public kava.hard.v1beta1.QueryOuterClass.QueryReservesResponse reserves(kava.hard.v1beta1.QueryOuterClass.QueryReservesRequest request) {
      return blockingUnaryCall(
          getChannel(), getReservesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * InterestFactors queries hard module interest factors.
     * </pre>
     */
    public kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsResponse interestFactors(kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsRequest request) {
      return blockingUnaryCall(
          getChannel(), getInterestFactorsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for bep3 module.
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
     * Params queries module params.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.hard.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        kava.hard.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Accounts queries module accounts.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.hard.v1beta1.QueryOuterClass.QueryAccountsResponse> accounts(
        kava.hard.v1beta1.QueryOuterClass.QueryAccountsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Deposits queries hard deposits.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.hard.v1beta1.QueryOuterClass.QueryDepositsResponse> deposits(
        kava.hard.v1beta1.QueryOuterClass.QueryDepositsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnsyncedDeposits queries unsynced deposits.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsResponse> unsyncedDeposits(
        kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnsyncedDepositsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalDeposited queries total coins deposited to hard liquidity pools.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedResponse> totalDeposited(
        kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTotalDepositedMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Borrows queries hard borrows.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.hard.v1beta1.QueryOuterClass.QueryBorrowsResponse> borrows(
        kava.hard.v1beta1.QueryOuterClass.QueryBorrowsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBorrowsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnsyncedBorrows queries unsynced borrows.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsResponse> unsyncedBorrows(
        kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnsyncedBorrowsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalBorrowed queries total coins borrowed from hard liquidity pools.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedResponse> totalBorrowed(
        kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTotalBorrowedMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * InterestRate queries the hard module interest rates.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.hard.v1beta1.QueryOuterClass.QueryInterestRateResponse> interestRate(
        kava.hard.v1beta1.QueryOuterClass.QueryInterestRateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getInterestRateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Reserves queries total hard reserve coins.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.hard.v1beta1.QueryOuterClass.QueryReservesResponse> reserves(
        kava.hard.v1beta1.QueryOuterClass.QueryReservesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getReservesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * InterestFactors queries hard module interest factors.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsResponse> interestFactors(
        kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getInterestFactorsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_ACCOUNTS = 1;
  private static final int METHODID_DEPOSITS = 2;
  private static final int METHODID_UNSYNCED_DEPOSITS = 3;
  private static final int METHODID_TOTAL_DEPOSITED = 4;
  private static final int METHODID_BORROWS = 5;
  private static final int METHODID_UNSYNCED_BORROWS = 6;
  private static final int METHODID_TOTAL_BORROWED = 7;
  private static final int METHODID_INTEREST_RATE = 8;
  private static final int METHODID_RESERVES = 9;
  private static final int METHODID_INTEREST_FACTORS = 10;

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
          serviceImpl.params((kava.hard.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_ACCOUNTS:
          serviceImpl.accounts((kava.hard.v1beta1.QueryOuterClass.QueryAccountsRequest) request,
              (io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryAccountsResponse>) responseObserver);
          break;
        case METHODID_DEPOSITS:
          serviceImpl.deposits((kava.hard.v1beta1.QueryOuterClass.QueryDepositsRequest) request,
              (io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryDepositsResponse>) responseObserver);
          break;
        case METHODID_UNSYNCED_DEPOSITS:
          serviceImpl.unsyncedDeposits((kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsRequest) request,
              (io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedDepositsResponse>) responseObserver);
          break;
        case METHODID_TOTAL_DEPOSITED:
          serviceImpl.totalDeposited((kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedRequest) request,
              (io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryTotalDepositedResponse>) responseObserver);
          break;
        case METHODID_BORROWS:
          serviceImpl.borrows((kava.hard.v1beta1.QueryOuterClass.QueryBorrowsRequest) request,
              (io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryBorrowsResponse>) responseObserver);
          break;
        case METHODID_UNSYNCED_BORROWS:
          serviceImpl.unsyncedBorrows((kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsRequest) request,
              (io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryUnsyncedBorrowsResponse>) responseObserver);
          break;
        case METHODID_TOTAL_BORROWED:
          serviceImpl.totalBorrowed((kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedRequest) request,
              (io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryTotalBorrowedResponse>) responseObserver);
          break;
        case METHODID_INTEREST_RATE:
          serviceImpl.interestRate((kava.hard.v1beta1.QueryOuterClass.QueryInterestRateRequest) request,
              (io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryInterestRateResponse>) responseObserver);
          break;
        case METHODID_RESERVES:
          serviceImpl.reserves((kava.hard.v1beta1.QueryOuterClass.QueryReservesRequest) request,
              (io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryReservesResponse>) responseObserver);
          break;
        case METHODID_INTEREST_FACTORS:
          serviceImpl.interestFactors((kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsRequest) request,
              (io.grpc.stub.StreamObserver<kava.hard.v1beta1.QueryOuterClass.QueryInterestFactorsResponse>) responseObserver);
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
      return kava.hard.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getDepositsMethod())
              .addMethod(getUnsyncedDepositsMethod())
              .addMethod(getTotalDepositedMethod())
              .addMethod(getBorrowsMethod())
              .addMethod(getUnsyncedBorrowsMethod())
              .addMethod(getTotalBorrowedMethod())
              .addMethod(getInterestRateMethod())
              .addMethod(getReservesMethod())
              .addMethod(getInterestFactorsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
