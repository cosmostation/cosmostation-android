package com.kava.hard.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service for bep3 module.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: kava/hard/v1beta1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "kava.hard.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryParamsRequest,
      com.kava.hard.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.kava.hard.v1beta1.QueryProto.QueryParamsRequest.class,
      responseType = com.kava.hard.v1beta1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryParamsRequest,
      com.kava.hard.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryParamsRequest, com.kava.hard.v1beta1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.kava.hard.v1beta1.QueryProto.QueryParamsRequest, com.kava.hard.v1beta1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryAccountsRequest,
      com.kava.hard.v1beta1.QueryProto.QueryAccountsResponse> getAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Accounts",
      requestType = com.kava.hard.v1beta1.QueryProto.QueryAccountsRequest.class,
      responseType = com.kava.hard.v1beta1.QueryProto.QueryAccountsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryAccountsRequest,
      com.kava.hard.v1beta1.QueryProto.QueryAccountsResponse> getAccountsMethod() {
    io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryAccountsRequest, com.kava.hard.v1beta1.QueryProto.QueryAccountsResponse> getAccountsMethod;
    if ((getAccountsMethod = QueryGrpc.getAccountsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountsMethod = QueryGrpc.getAccountsMethod) == null) {
          QueryGrpc.getAccountsMethod = getAccountsMethod =
              io.grpc.MethodDescriptor.<com.kava.hard.v1beta1.QueryProto.QueryAccountsRequest, com.kava.hard.v1beta1.QueryProto.QueryAccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Accounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryAccountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryAccountsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Accounts"))
              .build();
        }
      }
    }
    return getAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryDepositsRequest,
      com.kava.hard.v1beta1.QueryProto.QueryDepositsResponse> getDepositsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposits",
      requestType = com.kava.hard.v1beta1.QueryProto.QueryDepositsRequest.class,
      responseType = com.kava.hard.v1beta1.QueryProto.QueryDepositsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryDepositsRequest,
      com.kava.hard.v1beta1.QueryProto.QueryDepositsResponse> getDepositsMethod() {
    io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryDepositsRequest, com.kava.hard.v1beta1.QueryProto.QueryDepositsResponse> getDepositsMethod;
    if ((getDepositsMethod = QueryGrpc.getDepositsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositsMethod = QueryGrpc.getDepositsMethod) == null) {
          QueryGrpc.getDepositsMethod = getDepositsMethod =
              io.grpc.MethodDescriptor.<com.kava.hard.v1beta1.QueryProto.QueryDepositsRequest, com.kava.hard.v1beta1.QueryProto.QueryDepositsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposits"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryDepositsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryDepositsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Deposits"))
              .build();
        }
      }
    }
    return getDepositsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsRequest,
      com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsResponse> getUnsyncedDepositsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnsyncedDeposits",
      requestType = com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsRequest.class,
      responseType = com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsRequest,
      com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsResponse> getUnsyncedDepositsMethod() {
    io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsRequest, com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsResponse> getUnsyncedDepositsMethod;
    if ((getUnsyncedDepositsMethod = QueryGrpc.getUnsyncedDepositsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnsyncedDepositsMethod = QueryGrpc.getUnsyncedDepositsMethod) == null) {
          QueryGrpc.getUnsyncedDepositsMethod = getUnsyncedDepositsMethod =
              io.grpc.MethodDescriptor.<com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsRequest, com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnsyncedDeposits"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UnsyncedDeposits"))
              .build();
        }
      }
    }
    return getUnsyncedDepositsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedRequest,
      com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedResponse> getTotalDepositedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalDeposited",
      requestType = com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedRequest.class,
      responseType = com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedRequest,
      com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedResponse> getTotalDepositedMethod() {
    io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedRequest, com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedResponse> getTotalDepositedMethod;
    if ((getTotalDepositedMethod = QueryGrpc.getTotalDepositedMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalDepositedMethod = QueryGrpc.getTotalDepositedMethod) == null) {
          QueryGrpc.getTotalDepositedMethod = getTotalDepositedMethod =
              io.grpc.MethodDescriptor.<com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedRequest, com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalDeposited"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalDeposited"))
              .build();
        }
      }
    }
    return getTotalDepositedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryBorrowsRequest,
      com.kava.hard.v1beta1.QueryProto.QueryBorrowsResponse> getBorrowsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Borrows",
      requestType = com.kava.hard.v1beta1.QueryProto.QueryBorrowsRequest.class,
      responseType = com.kava.hard.v1beta1.QueryProto.QueryBorrowsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryBorrowsRequest,
      com.kava.hard.v1beta1.QueryProto.QueryBorrowsResponse> getBorrowsMethod() {
    io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryBorrowsRequest, com.kava.hard.v1beta1.QueryProto.QueryBorrowsResponse> getBorrowsMethod;
    if ((getBorrowsMethod = QueryGrpc.getBorrowsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBorrowsMethod = QueryGrpc.getBorrowsMethod) == null) {
          QueryGrpc.getBorrowsMethod = getBorrowsMethod =
              io.grpc.MethodDescriptor.<com.kava.hard.v1beta1.QueryProto.QueryBorrowsRequest, com.kava.hard.v1beta1.QueryProto.QueryBorrowsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Borrows"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryBorrowsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryBorrowsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Borrows"))
              .build();
        }
      }
    }
    return getBorrowsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsRequest,
      com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsResponse> getUnsyncedBorrowsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnsyncedBorrows",
      requestType = com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsRequest.class,
      responseType = com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsRequest,
      com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsResponse> getUnsyncedBorrowsMethod() {
    io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsRequest, com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsResponse> getUnsyncedBorrowsMethod;
    if ((getUnsyncedBorrowsMethod = QueryGrpc.getUnsyncedBorrowsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnsyncedBorrowsMethod = QueryGrpc.getUnsyncedBorrowsMethod) == null) {
          QueryGrpc.getUnsyncedBorrowsMethod = getUnsyncedBorrowsMethod =
              io.grpc.MethodDescriptor.<com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsRequest, com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnsyncedBorrows"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UnsyncedBorrows"))
              .build();
        }
      }
    }
    return getUnsyncedBorrowsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedRequest,
      com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedResponse> getTotalBorrowedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalBorrowed",
      requestType = com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedRequest.class,
      responseType = com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedRequest,
      com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedResponse> getTotalBorrowedMethod() {
    io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedRequest, com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedResponse> getTotalBorrowedMethod;
    if ((getTotalBorrowedMethod = QueryGrpc.getTotalBorrowedMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalBorrowedMethod = QueryGrpc.getTotalBorrowedMethod) == null) {
          QueryGrpc.getTotalBorrowedMethod = getTotalBorrowedMethod =
              io.grpc.MethodDescriptor.<com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedRequest, com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalBorrowed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalBorrowed"))
              .build();
        }
      }
    }
    return getTotalBorrowedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryInterestRateRequest,
      com.kava.hard.v1beta1.QueryProto.QueryInterestRateResponse> getInterestRateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InterestRate",
      requestType = com.kava.hard.v1beta1.QueryProto.QueryInterestRateRequest.class,
      responseType = com.kava.hard.v1beta1.QueryProto.QueryInterestRateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryInterestRateRequest,
      com.kava.hard.v1beta1.QueryProto.QueryInterestRateResponse> getInterestRateMethod() {
    io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryInterestRateRequest, com.kava.hard.v1beta1.QueryProto.QueryInterestRateResponse> getInterestRateMethod;
    if ((getInterestRateMethod = QueryGrpc.getInterestRateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getInterestRateMethod = QueryGrpc.getInterestRateMethod) == null) {
          QueryGrpc.getInterestRateMethod = getInterestRateMethod =
              io.grpc.MethodDescriptor.<com.kava.hard.v1beta1.QueryProto.QueryInterestRateRequest, com.kava.hard.v1beta1.QueryProto.QueryInterestRateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InterestRate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryInterestRateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryInterestRateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("InterestRate"))
              .build();
        }
      }
    }
    return getInterestRateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryReservesRequest,
      com.kava.hard.v1beta1.QueryProto.QueryReservesResponse> getReservesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Reserves",
      requestType = com.kava.hard.v1beta1.QueryProto.QueryReservesRequest.class,
      responseType = com.kava.hard.v1beta1.QueryProto.QueryReservesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryReservesRequest,
      com.kava.hard.v1beta1.QueryProto.QueryReservesResponse> getReservesMethod() {
    io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryReservesRequest, com.kava.hard.v1beta1.QueryProto.QueryReservesResponse> getReservesMethod;
    if ((getReservesMethod = QueryGrpc.getReservesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getReservesMethod = QueryGrpc.getReservesMethod) == null) {
          QueryGrpc.getReservesMethod = getReservesMethod =
              io.grpc.MethodDescriptor.<com.kava.hard.v1beta1.QueryProto.QueryReservesRequest, com.kava.hard.v1beta1.QueryProto.QueryReservesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Reserves"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryReservesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryReservesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Reserves"))
              .build();
        }
      }
    }
    return getReservesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsRequest,
      com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsResponse> getInterestFactorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InterestFactors",
      requestType = com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsRequest.class,
      responseType = com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsRequest,
      com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsResponse> getInterestFactorsMethod() {
    io.grpc.MethodDescriptor<com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsRequest, com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsResponse> getInterestFactorsMethod;
    if ((getInterestFactorsMethod = QueryGrpc.getInterestFactorsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getInterestFactorsMethod = QueryGrpc.getInterestFactorsMethod) == null) {
          QueryGrpc.getInterestFactorsMethod = getInterestFactorsMethod =
              io.grpc.MethodDescriptor.<com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsRequest, com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InterestFactors"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsResponse.getDefaultInstance()))
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
  public interface AsyncService {

    /**
     * <pre>
     * Params queries module params.
     * </pre>
     */
    default void params(com.kava.hard.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Accounts queries module accounts.
     * </pre>
     */
    default void accounts(com.kava.hard.v1beta1.QueryProto.QueryAccountsRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryAccountsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAccountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Deposits queries hard deposits.
     * </pre>
     */
    default void deposits(com.kava.hard.v1beta1.QueryProto.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryDepositsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDepositsMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnsyncedDeposits queries unsynced deposits.
     * </pre>
     */
    default void unsyncedDeposits(com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnsyncedDepositsMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalDeposited queries total coins deposited to hard liquidity pools.
     * </pre>
     */
    default void totalDeposited(com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTotalDepositedMethod(), responseObserver);
    }

    /**
     * <pre>
     * Borrows queries hard borrows.
     * </pre>
     */
    default void borrows(com.kava.hard.v1beta1.QueryProto.QueryBorrowsRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryBorrowsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBorrowsMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnsyncedBorrows queries unsynced borrows.
     * </pre>
     */
    default void unsyncedBorrows(com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnsyncedBorrowsMethod(), responseObserver);
    }

    /**
     * <pre>
     * TotalBorrowed queries total coins borrowed from hard liquidity pools.
     * </pre>
     */
    default void totalBorrowed(com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTotalBorrowedMethod(), responseObserver);
    }

    /**
     * <pre>
     * InterestRate queries the hard module interest rates.
     * </pre>
     */
    default void interestRate(com.kava.hard.v1beta1.QueryProto.QueryInterestRateRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryInterestRateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInterestRateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Reserves queries total hard reserve coins.
     * </pre>
     */
    default void reserves(com.kava.hard.v1beta1.QueryProto.QueryReservesRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryReservesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReservesMethod(), responseObserver);
    }

    /**
     * <pre>
     * InterestFactors queries hard module interest factors.
     * </pre>
     */
    default void interestFactors(com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInterestFactorsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service for bep3 module.
   * </pre>
   */
  public static abstract class QueryImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return QueryGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for bep3 module.
   * </pre>
   */
  public static final class QueryStub
      extends io.grpc.stub.AbstractAsyncStub<QueryStub> {
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
    public void params(com.kava.hard.v1beta1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Accounts queries module accounts.
     * </pre>
     */
    public void accounts(com.kava.hard.v1beta1.QueryProto.QueryAccountsRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryAccountsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Deposits queries hard deposits.
     * </pre>
     */
    public void deposits(com.kava.hard.v1beta1.QueryProto.QueryDepositsRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryDepositsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDepositsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnsyncedDeposits queries unsynced deposits.
     * </pre>
     */
    public void unsyncedDeposits(com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnsyncedDepositsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalDeposited queries total coins deposited to hard liquidity pools.
     * </pre>
     */
    public void totalDeposited(com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTotalDepositedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Borrows queries hard borrows.
     * </pre>
     */
    public void borrows(com.kava.hard.v1beta1.QueryProto.QueryBorrowsRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryBorrowsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBorrowsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnsyncedBorrows queries unsynced borrows.
     * </pre>
     */
    public void unsyncedBorrows(com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnsyncedBorrowsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TotalBorrowed queries total coins borrowed from hard liquidity pools.
     * </pre>
     */
    public void totalBorrowed(com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTotalBorrowedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * InterestRate queries the hard module interest rates.
     * </pre>
     */
    public void interestRate(com.kava.hard.v1beta1.QueryProto.QueryInterestRateRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryInterestRateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInterestRateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Reserves queries total hard reserve coins.
     * </pre>
     */
    public void reserves(com.kava.hard.v1beta1.QueryProto.QueryReservesRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryReservesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReservesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * InterestFactors queries hard module interest factors.
     * </pre>
     */
    public void interestFactors(com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsRequest request,
        io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInterestFactorsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for bep3 module.
   * </pre>
   */
  public static final class QueryBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<QueryBlockingStub> {
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
    public com.kava.hard.v1beta1.QueryProto.QueryParamsResponse params(com.kava.hard.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Accounts queries module accounts.
     * </pre>
     */
    public com.kava.hard.v1beta1.QueryProto.QueryAccountsResponse accounts(com.kava.hard.v1beta1.QueryProto.QueryAccountsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAccountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Deposits queries hard deposits.
     * </pre>
     */
    public com.kava.hard.v1beta1.QueryProto.QueryDepositsResponse deposits(com.kava.hard.v1beta1.QueryProto.QueryDepositsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDepositsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnsyncedDeposits queries unsynced deposits.
     * </pre>
     */
    public com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsResponse unsyncedDeposits(com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnsyncedDepositsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalDeposited queries total coins deposited to hard liquidity pools.
     * </pre>
     */
    public com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedResponse totalDeposited(com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTotalDepositedMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Borrows queries hard borrows.
     * </pre>
     */
    public com.kava.hard.v1beta1.QueryProto.QueryBorrowsResponse borrows(com.kava.hard.v1beta1.QueryProto.QueryBorrowsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBorrowsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnsyncedBorrows queries unsynced borrows.
     * </pre>
     */
    public com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsResponse unsyncedBorrows(com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnsyncedBorrowsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TotalBorrowed queries total coins borrowed from hard liquidity pools.
     * </pre>
     */
    public com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedResponse totalBorrowed(com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTotalBorrowedMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * InterestRate queries the hard module interest rates.
     * </pre>
     */
    public com.kava.hard.v1beta1.QueryProto.QueryInterestRateResponse interestRate(com.kava.hard.v1beta1.QueryProto.QueryInterestRateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInterestRateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Reserves queries total hard reserve coins.
     * </pre>
     */
    public com.kava.hard.v1beta1.QueryProto.QueryReservesResponse reserves(com.kava.hard.v1beta1.QueryProto.QueryReservesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReservesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * InterestFactors queries hard module interest factors.
     * </pre>
     */
    public com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsResponse interestFactors(com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInterestFactorsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service for bep3 module.
   * </pre>
   */
  public static final class QueryFutureStub
      extends io.grpc.stub.AbstractFutureStub<QueryFutureStub> {
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
    public com.google.common.util.concurrent.ListenableFuture<com.kava.hard.v1beta1.QueryProto.QueryParamsResponse> params(
        com.kava.hard.v1beta1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Accounts queries module accounts.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.hard.v1beta1.QueryProto.QueryAccountsResponse> accounts(
        com.kava.hard.v1beta1.QueryProto.QueryAccountsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Deposits queries hard deposits.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.hard.v1beta1.QueryProto.QueryDepositsResponse> deposits(
        com.kava.hard.v1beta1.QueryProto.QueryDepositsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDepositsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnsyncedDeposits queries unsynced deposits.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsResponse> unsyncedDeposits(
        com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnsyncedDepositsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalDeposited queries total coins deposited to hard liquidity pools.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedResponse> totalDeposited(
        com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTotalDepositedMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Borrows queries hard borrows.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.hard.v1beta1.QueryProto.QueryBorrowsResponse> borrows(
        com.kava.hard.v1beta1.QueryProto.QueryBorrowsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBorrowsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnsyncedBorrows queries unsynced borrows.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsResponse> unsyncedBorrows(
        com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnsyncedBorrowsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TotalBorrowed queries total coins borrowed from hard liquidity pools.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedResponse> totalBorrowed(
        com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTotalBorrowedMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * InterestRate queries the hard module interest rates.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.hard.v1beta1.QueryProto.QueryInterestRateResponse> interestRate(
        com.kava.hard.v1beta1.QueryProto.QueryInterestRateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInterestRateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Reserves queries total hard reserve coins.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.hard.v1beta1.QueryProto.QueryReservesResponse> reserves(
        com.kava.hard.v1beta1.QueryProto.QueryReservesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReservesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * InterestFactors queries hard module interest factors.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsResponse> interestFactors(
        com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
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
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PARAMS:
          serviceImpl.params((com.kava.hard.v1beta1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_ACCOUNTS:
          serviceImpl.accounts((com.kava.hard.v1beta1.QueryProto.QueryAccountsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryAccountsResponse>) responseObserver);
          break;
        case METHODID_DEPOSITS:
          serviceImpl.deposits((com.kava.hard.v1beta1.QueryProto.QueryDepositsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryDepositsResponse>) responseObserver);
          break;
        case METHODID_UNSYNCED_DEPOSITS:
          serviceImpl.unsyncedDeposits((com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsResponse>) responseObserver);
          break;
        case METHODID_TOTAL_DEPOSITED:
          serviceImpl.totalDeposited((com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedResponse>) responseObserver);
          break;
        case METHODID_BORROWS:
          serviceImpl.borrows((com.kava.hard.v1beta1.QueryProto.QueryBorrowsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryBorrowsResponse>) responseObserver);
          break;
        case METHODID_UNSYNCED_BORROWS:
          serviceImpl.unsyncedBorrows((com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsResponse>) responseObserver);
          break;
        case METHODID_TOTAL_BORROWED:
          serviceImpl.totalBorrowed((com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedResponse>) responseObserver);
          break;
        case METHODID_INTEREST_RATE:
          serviceImpl.interestRate((com.kava.hard.v1beta1.QueryProto.QueryInterestRateRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryInterestRateResponse>) responseObserver);
          break;
        case METHODID_RESERVES:
          serviceImpl.reserves((com.kava.hard.v1beta1.QueryProto.QueryReservesRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryReservesResponse>) responseObserver);
          break;
        case METHODID_INTEREST_FACTORS:
          serviceImpl.interestFactors((com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsRequest) request,
              (io.grpc.stub.StreamObserver<com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.hard.v1beta1.QueryProto.QueryParamsRequest,
              com.kava.hard.v1beta1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getAccountsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.hard.v1beta1.QueryProto.QueryAccountsRequest,
              com.kava.hard.v1beta1.QueryProto.QueryAccountsResponse>(
                service, METHODID_ACCOUNTS)))
        .addMethod(
          getDepositsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.hard.v1beta1.QueryProto.QueryDepositsRequest,
              com.kava.hard.v1beta1.QueryProto.QueryDepositsResponse>(
                service, METHODID_DEPOSITS)))
        .addMethod(
          getUnsyncedDepositsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsRequest,
              com.kava.hard.v1beta1.QueryProto.QueryUnsyncedDepositsResponse>(
                service, METHODID_UNSYNCED_DEPOSITS)))
        .addMethod(
          getTotalDepositedMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedRequest,
              com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedResponse>(
                service, METHODID_TOTAL_DEPOSITED)))
        .addMethod(
          getBorrowsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.hard.v1beta1.QueryProto.QueryBorrowsRequest,
              com.kava.hard.v1beta1.QueryProto.QueryBorrowsResponse>(
                service, METHODID_BORROWS)))
        .addMethod(
          getUnsyncedBorrowsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsRequest,
              com.kava.hard.v1beta1.QueryProto.QueryUnsyncedBorrowsResponse>(
                service, METHODID_UNSYNCED_BORROWS)))
        .addMethod(
          getTotalBorrowedMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedRequest,
              com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedResponse>(
                service, METHODID_TOTAL_BORROWED)))
        .addMethod(
          getInterestRateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.hard.v1beta1.QueryProto.QueryInterestRateRequest,
              com.kava.hard.v1beta1.QueryProto.QueryInterestRateResponse>(
                service, METHODID_INTEREST_RATE)))
        .addMethod(
          getReservesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.hard.v1beta1.QueryProto.QueryReservesRequest,
              com.kava.hard.v1beta1.QueryProto.QueryReservesResponse>(
                service, METHODID_RESERVES)))
        .addMethod(
          getInterestFactorsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsRequest,
              com.kava.hard.v1beta1.QueryProto.QueryInterestFactorsResponse>(
                service, METHODID_INTEREST_FACTORS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kava.hard.v1beta1.QueryProto.getDescriptor();
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
